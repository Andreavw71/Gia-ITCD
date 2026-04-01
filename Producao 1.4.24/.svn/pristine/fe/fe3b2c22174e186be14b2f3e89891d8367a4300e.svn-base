package br.gov.mt.sefaz.itc.model.log.historicolog;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorBemTributavelVo;
import br.gov.mt.sefaz.itc.model.log.log.LogITCDVo;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;


public class HistoricoLogBe extends AbstractBe
{

   public HistoricoLogBe()throws SQLException
   {
   }
   
   public HistoricoLogBe(Connection connection)
   {
      super(connection);
   }

   public void compararAlteracao(GIAITCDVo giaITCDVo, GIAITCDVo giaITCDVoOriginal) throws SQLException
   {
      HistoricoLogVo historicoLogVo = new HistoricoLogVo();
      iniciarRotinaDeComparadores(giaITCDVo, giaITCDVoOriginal, historicoLogVo);

      if (Validador.isCollectionValida(historicoLogVo.getCollVO()))
      {
         adicionarNumeroTrasacao(historicoLogVo, giaITCDVo.getStatusVo().getLogITCDVo());
      }
   }


   private void adicionarNumeroTrasacao(HistoricoLogVo historicoLogVo, LogITCDVo logITCDVo) throws SQLException
   {

      if (Validador.isCollectionValida(historicoLogVo.getCollVO()))
      {
         if (Validador.isObjetoValido(logITCDVo))
         {
            for (HistoricoLogVo temp: historicoLogVo.getCollVO())
            {
               temp.setLogITCDVo(logITCDVo);
               persistirHistoricoLogITCDVo(temp);
            }
         }

      }
   }


   public synchronized void persistirHistoricoLogITCDVo(final HistoricoLogVo historicoLogVo) throws SQLException
   {
      try
      {
         // chama o metodo para criar uma chave.
         historicoLogVo.setCodigo(new HistoricoLogDao(conn).getProximoValorSequence());
         salvar(historicoLogVo);
      } catch (LogException e)
      {
         e.printStackTrace();
      } catch (ObjetoObrigatorioException e)
      {
         e.printStackTrace();
      } catch (AnotacaoException e)
      {
         e.printStackTrace();
      } catch (PersistenciaException e)
      {
         e.printStackTrace();
      }
   }


   private synchronized void salvar(final HistoricoLogVo historicoLogVo) throws ObjetoObrigatorioException, 
                                                                                LogException, 
                                                                                AnotacaoException, 
                                                                                PersistenciaException
   {
      Validador.validaObjeto(historicoLogVo);
      new GenericoLogAnotacaoDao(conn, true).insert(historicoLogVo, HistoricoLogVo.class);
   }


   /**
    * Verifica se os objetos da GIA são validos para poder
    * iniciar a rotina de comparação de cada tipo de objeto.
    * O objeto é considerado valido quando tiver valor diferente
    * de <b>NULL</b>.
    * Logica : Teste os objetos das duas GIAs e inicia a rotina de comparação.
    * 
    * 
    * @param giaITCDVo
    * @param giaITCDVoOriginal
    * @param historicoLogVo
    * 
    */
   private void iniciarRotinaDeComparadores(GIAITCDVo giaITCDVo, GIAITCDVo giaITCDVoOriginal, HistoricoLogVo historicoLogVo)
   {
      if (Validador.isObjetoValido(giaITCDVo) & Validador.isObjetoValido(giaITCDVoOriginal))
      {

         if (giaITCDVo instanceof GIAITCDInventarioArrolamentoVo & giaITCDVoOriginal instanceof GIAITCDInventarioArrolamentoVo)
         {
            GIAITCDInventarioArrolamentoVo giaInventarioArrolamentoVo = (GIAITCDInventarioArrolamentoVo) giaITCDVo;
            GIAITCDInventarioArrolamentoVo giaInventarioArrolamentoVoOriginal = (GIAITCDInventarioArrolamentoVo) giaITCDVoOriginal;
            //new ComparadorGIAITCDInventarioArrolamentoVo().comparar(giaInventarioArrolamentoVo, giaInventarioArrolamentoVoOriginal);
         }
      }
      if (Validador.isObjetoValido(giaITCDVo.getBemTributavelVo()) & Validador.isObjetoValido(giaITCDVoOriginal.getBemTributavelVo()))
      {
         new ComparadorBemTributavelVo().comparar(giaITCDVo.getBemTributavelVo(), giaITCDVoOriginal.getBemTributavelVo());
      }

      if (giaITCDVo instanceof GIAITCDDoacaoVo)
      {
         System.out.println("GIA DOACAO");
         GIAITCDDoacaoVo giaITCDDoacaoVo = (GIAITCDDoacaoVo) giaITCDVo;
      }

      if (giaITCDVo instanceof GIAITCDSeparacaoDivorcioVo)
      {
         System.out.println("GIA DIVORCIO");
      }
      
      if (Validador.isObjetoValido(giaITCDVo.getBeneficiarioVo()) & Validador.isObjetoValido(giaITCDVoOriginal.getBeneficiarioVo()))
      {
         
      }

   }

   public HistoricoLogVo obterHistoricoLogVoPorStatusITCD(StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
                                                                                               ConsultaException
   {
      HistoricoLogVo historicoLogVo = null;
      HistoricoLogVo historicoLogVoParametrizado = null;

      List<StatusGIAITCDVo> statusCollection = (List<StatusGIAITCDVo>) statusGIAITCDVo.getCollVO();

      for (StatusGIAITCDVo status: statusCollection)
      {
         historicoLogVoParametrizado = new HistoricoLogVo();
         historicoLogVoParametrizado.setLogITCDVo(status.getLogITCDVo());

         if (historicoLogVoParametrizado.getLogITCDVo().getCodigo() != 0)
         {
            historicoLogVo = new HistoricoLogVo();
            historicoLogVo.setParametroConsulta(historicoLogVoParametrizado);
            new HistoricoLogQDao(conn).findHistoricoLogVo(historicoLogVo);
            if(Validador.isCollectionValida(historicoLogVo.getCollVO()))
            {
               status.getLogITCDVo().setHistoricoLogVo(historicoLogVo);
            }
         }
      }
      return historicoLogVo;
   }

}// fim classe(HistoricoLogBe).
