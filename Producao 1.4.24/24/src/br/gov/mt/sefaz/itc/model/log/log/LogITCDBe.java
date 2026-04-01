package br.gov.mt.sefaz.itc.model.log.log;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.logsefaz.LogSefazVo;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.model.log.LogUtilComparador;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogBe;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.model.log.itemhistorico.ItemHistoricoBe;
import br.gov.mt.sefaz.itc.model.log.itemhistorico.ItemHistoricoVo;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;
import java.util.List;


public class LogITCDBe extends AbstractBe
{

   public LogITCDBe(Connection connection)
   {
      super(connection);
   }

   /**
    * <b>Objetivo : </b> este método tem por objetivo ser o ponto de início das rotinas de validação, processamento e armazenamento
    * de LOG no banco de dados.
    * @param giaITCDVoAtual GIA em que está sendo realizada as alterações com estado anterior ao commit no banco de dados.
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public void rotinaLog(GIAITCDVo giaITCDVoAtual) throws SQLException
   {
      //giaTemp criada apenas para fazer a consulta no BD.
      GIAITCDVo giaTemp = new GIAITCDVo();
      giaTemp.setCodigo(giaITCDVoAtual.getCodigo());
      giaTemp.setParametroConsulta(giaITCDVoAtual.getParametroConsulta());
      giaTemp.setConsultaCompleta(true);
      //giaITCDLog criada com os dados retornados do DB para ser comparada com a giaITCDVoAtual e verificar as alterações realizadas
      GIAITCDVo giaITCDLog = null;

      // Inicio da compração de objetos para verificar as alterações.
      new HistoricoLogBe(conn).compararAlteracao(giaITCDVoAtual, giaITCDLog);


   }

   //------------------------------------------------------- Rotina Processmanto de LOG ------------------------------------------------------------------------
   /**
    * <b>Objetivo : </b>
    * 
    * 
    * 
    * @param giaITCDVoModificada
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    */
   public void rotinaProcessamentoLOG(GIAITCDVo giaITCDVoModificada) throws LogException, 
                                                                            AnotacaoException, 
                                                                            PersistenciaException, 
             IOException

   {
      //giaTemp criada apenas para fazer a consulta no BD.
      GIAITCDVo giaTemp = new GIAITCDVo();
      giaTemp.setCodigo(giaITCDVoModificada.getCodigo());
      giaTemp.setParametroConsulta(giaITCDVoModificada.getParametroConsulta());
      giaTemp.setConsultaCompleta(true);
      //giaITCDLog criada com os dados retornados do DB para ser comparada com a giaITCDVoAtual e verificar as alterações realizadas
      GIAITCDVo giaITCDVoOriginal;

      try
      {
         // faz uma consulta no BD para retornar GIA com o mesmo Código da atual
         // mas sem as alterações.
         //Neste Be não é passada a conexão devido a necessidade de consulta do registro antes da alteração
         // sendo necessário portanto outra transação.
         GIAITCDBe giaBe = null;
         try
         {
            giaBe = new GIAITCDBe();
            giaITCDVoOriginal = giaBe.consultaGiaPermanente(giaTemp);

         } finally
         {
            if (Validador.isObjetoValido(giaBe))
            {
               giaBe.close();
            }

         }


         // Inicia o rotina de processamento de LOG para identificar as alterações.
         LogITCDVo logITCDVo = new LogUtilComparador().rotinaProcessamentoLogITCD(giaITCDVoModificada, giaITCDVoOriginal);


         // Inicio da rotina de gravação de HistoricoLogITCDVo no BD.
         for (HistoricoLogVo historicoLogVo: logITCDVo.getHistoricoLogVo().getCollVO())
         {
            historicoLogVo.setLogITCDVo(giaITCDVoModificada.getStatusVo().getLogITCDVo());
            new HistoricoLogBe(conn).persistirHistoricoLogITCDVo(historicoLogVo);

            // Inicio da rotina de gravação ItemHistoricoVo
            for (ItemHistoricoVo itemHistoricoVo: historicoLogVo.getItemLog().getCollVO())
            {
               itemHistoricoVo.setHistoricoLogVo(historicoLogVo);
               new ItemHistoricoBe(conn).persistirItemHistoricoVo(itemHistoricoVo);
            }
         }

      } catch (ConexaoException e)
      {
         e.printStackTrace();
      } catch (ConsultaException e)
      {
         e.printStackTrace();
      } catch (IntegracaoException e)
      {
         e.printStackTrace();
      } catch (ParametroObrigatorioException e)
      {
         e.printStackTrace();
      } catch (ObjetoObrigatorioException e)
      {
         e.printStackTrace();
      } catch (SQLException e)
      {
         e.printStackTrace();
      }
   }


   /**
    * <b>Objetivo : </b> este método tem por objetivo criar um objeto do tipo br.gov.mt.sefaz.itc.model.log.log.LogITCDVo
    * que será utilizado pelo StatusGIAITCDVo
    * 
    * 
    * @return LogITCDVo retorna uma objeto do tipo
    * br.gov.mt.sefaz.itc.model.log.log.LogITCDVo
    */
   public LogITCDVo criarResgistroLogITCD(LogSefazVo logSefazVo) throws SQLException, 
                                                                        LogException, 
                                                                        ObjetoObrigatorioException, 
                                                                        AnotacaoException, 
                                                                        PersistenciaException
   {
      LogITCDVo logITCDVoTemp = new LogITCDVo(new LogITCDDao(conn).getProximoValorSequence());
      logITCDVoTemp.setDataTransacao(new Date());
      if (Validador.isStringValida(logSefazVo.getInfoClienteBean().getIpAddress()))
      {
         logITCDVoTemp.setEnderecoIP(logSefazVo.getInfoClienteBean().getIpAddress());
      } else
      {
         logITCDVoTemp.setEnderecoIP(logSefazVo.getInfoClienteBean().getHostName());
      }

      salvarLogITCD(logITCDVoTemp);
      return logITCDVoTemp;
   }


   /**
    * <b>Objetivo : </b>este método tem por objetivo criar um objeto do tipo Log
    * para armazenar os itens alterados ou deletados
    * caso tenha ocorrido alguma alteração nas informações
    * do registro
    * 
    * 
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   private boolean criarRegistroLogITCD()
   {
      boolean dadosAlterados = false;


      return dadosAlterados;
   }

   /**
    * <b>Objetivo : </b>
    * Este método tem por objetivo validar e invocar o método salvar 
    * <br>
    * <br>
    * <b>Validação : </b>
    * <ol>
    * <li>Valida se o objetivo é != null</li>
    * </ol>
    * 
    * @param logITCDVo
    * @throws SQLException
    * @throws LogException
    * @throws ObjetoObrigatorioException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemted by by Dherkyan Ribeiro da Silva.
    */
   private void salvarLogITCD(LogITCDVo logITCDVo) throws SQLException, 
                                                          LogException, 
                                                          ObjetoObrigatorioException, 
                                                          AnotacaoException, 
                                                          PersistenciaException
   {

      if (Validador.isObjetoValido(logITCDVo))
      {
         salvar(logITCDVo);
      }
   }


   /**
    * <b>Objetivo : </b> este método tem por objetivo invocar a biblioteca abacologdao
    * para efetuar a operação inclusão de um registro no DB
    * 
    * @param logITCDVo objeto representado a tabela ITCTB48_LOG
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   private synchronized void salvar(LogITCDVo logITCDVo) throws LogException, 
                                                                ObjetoObrigatorioException, 
                                                                AnotacaoException, 
                                                                PersistenciaException
   {
      try
      {
         new GenericoLogAnotacaoDao(conn, true).insert(logITCDVo, LogITCDVo.class);
      } catch (LogException e)
      {
         e.printStackTrace();
         throw e;
      } catch (ObjetoObrigatorioException e)
      {
         e.printStackTrace();
         throw e;
      } catch (AnotacaoException e)
      {
         e.printStackTrace();
         throw e;
      } catch (PersistenciaException e)
      {
         e.printStackTrace();
         throw e;
      }
   }


   /**
    * 
    * <b>Objetivo : </b> Obtem o LOG referente a cada Status registrado
    * e adiciona ao Objeto Status.
    * 
    * 
    * 
    * @param statusGIAITCDVo
    * @return
    */
   public LogITCDVo obterLogPorStatus(StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
                                                                              ConsultaException
   {
      LogITCDVo logITCDVo = null;
      List<StatusGIAITCDVo> statusCollection = (List<StatusGIAITCDVo>) statusGIAITCDVo.getCollVO();

      for (StatusGIAITCDVo temp: statusCollection)
      {
         logITCDVo = new LogITCDVo(temp.getLogITCDVo());

         if (logITCDVo.getParametroConsulta().getCodigo() != 0)
         {
            new LogITCDQDao(conn).findLogITCDVo(logITCDVo);
            temp.setLogITCDVo(logITCDVo);
         }
      }

      return logITCDVo;
   }


}// fim classe(LogITCDBe).
