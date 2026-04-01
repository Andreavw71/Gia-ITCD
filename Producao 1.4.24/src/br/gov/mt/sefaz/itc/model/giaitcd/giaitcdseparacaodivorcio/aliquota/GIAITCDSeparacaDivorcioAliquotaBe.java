package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.aliquota;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;

import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;

import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Iterator;

public class GIAITCDSeparacaDivorcioAliquotaBe extends AbstractBe
{
   
   /**
    * Contrutor padrão vazio
    * @implemented by Lucas Nascimento
    */
   public GIAITCDSeparacaDivorcioAliquotaBe()throws SQLException
   {
      super();
   }
   
   /**
    * Contrutor padrão vazio
    * @implemented by Lucas Nascimento
    */
   public GIAITCDSeparacaDivorcioAliquotaBe(Connection conn) throws SQLException
   {
      super(conn);
   }
   
   /**
    * @param giaItcdSeparacaoDivorcioAliquotaVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Dherkyan Ribeiro da Silva
    */
   private synchronized void incluir(final GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException, 
           LogException, AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(giaItcdSeparacaoDivorcioAliquotaVo);
   }
   
   
   /**
    * Inclui no banco de dados uma Aliquota de um determinado beneficiario de uma gia do tipo Separação
    * 
    * @param giaItcdSeparacaoDivorcioAliquotaVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws ConexaoException
    * @implemented by Lucas Nascimento
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void incluirGiaItcdSeparacaoAliquota(final GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException, 
           LogException, PersistenciaException, AnotacaoException, ConexaoException
   {
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);
      try
      {
         try
         {
            synchronized (GIAITCDSeparacaoDivorcioAliquotaVo.class)
            {
               incluir(giaItcdSeparacaoDivorcioAliquotaVo);
            }
         }        
         catch (ObjetoObrigatorioException e)
         {
            conn.rollback();
            throw e;
         }
         catch(LogException e)
         {
            conn.rollback();
            throw e;
         }
         catch(PersistenciaException e)
         {
            conn.rollback();
            throw e;
         }
         catch(AnotacaoException e)
         {
            conn.rollback();
            throw e;
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }
   
   /**
    * @param giaItcdSeparacaoDivorcioAliquotaVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void alterar(final GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException, 
           LogException, AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(giaItcdSeparacaoDivorcioAliquotaVo);
   }
   
   /**
    * Altera os dados persistidos de uma determinada aliquota de um determinado beneficiário de uma determinada
    * GIA do tipo Doação
    * 
    * @param giaItcdSeparacaoDivorcioAliquotaVo     dados a serem modificados
    * @throws ObjetoObrigatorioException
    */
   public synchronized void alterarGiaItcdSeparacaoAliquota(final GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException, 
           LogException, PersistenciaException, AnotacaoException, ConexaoException
   {
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);
      try
      {
         try
         {
            synchronized (GIAITCDSeparacaoDivorcioAliquotaVo.class)
            {
               alterar(giaItcdSeparacaoDivorcioAliquotaVo);
               giaItcdSeparacaoDivorcioAliquotaVo.setMensagem(MensagemSucesso.ALTERAR);
            }
         }
         catch (ObjetoObrigatorioException e)
         {
            conn.rollback();
            throw e;
         }
         catch(LogException e)
         {
            conn.rollback();
            throw e;
         }
         catch(PersistenciaException e)
         {
            conn.rollback();
            throw e;
         }
         catch(AnotacaoException e)
         {
            conn.rollback();
            throw e;
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }
   
   /**
    * Exclui uma determinada aliquota de um determinado beneficiário de uma determinada
    * GIA do tipo Doação
    * 
    * @param giaItcdSeparacaoDivorcioAliquotaVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws ConexaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void excluirGiaItcdSeparacaoAliquota(final GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException, 
           LogException, PersistenciaException, AnotacaoException, ConexaoException
   {
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);
      try
      {
         try
         {
            synchronized (GIAITCDSeparacaoDivorcioAliquotaVo.class)
            {
               excluir(giaItcdSeparacaoDivorcioAliquotaVo);
            }
         }
         catch (ObjetoObrigatorioException e)
         {
            conn.rollback();
            throw e;
         }
         catch(LogException e)
         {
            conn.rollback();
            throw e;
         }
         catch(PersistenciaException e)
         {
            conn.rollback();
            throw e;
         }
         catch(AnotacaoException e)
         {
            conn.rollback();
            throw e;
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }
   
   /**
    * @param giaItcdSeparacaoDivorcioAliquotaVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void excluir(final GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException, 
           LogException, AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);   
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(giaItcdSeparacaoDivorcioAliquotaVo);
   }
   
   /**
    * Consulta uma aliquota para o beneficiario
    * @param giaItcdSeparacaoDivorcioAliquotaVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @implemented by Lucas Nascimento
    */
   public GIAITCDSeparacaoDivorcioAliquotaVo consultarGiaItcdSeparacaoAliquota(final GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);
      (new GIAITCDSeparacaoDivorcioAliquotaQDao(conn)).findGiaItcdSeparacaoAliquota(giaItcdSeparacaoDivorcioAliquotaVo);
      return giaItcdSeparacaoDivorcioAliquotaVo;
   }
   
   /**
    * Retorna uma lista de aliquotas de uma determinado beneficiario de Doação
    * @param giaItcdSeparacaoDivorcioAliquotaVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @implemented by Lucas Nascimento
    */
   public GIAITCDSeparacaoDivorcioAliquotaVo listarGiaItcdSeparacaoAliquota(final GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);
      (new GIAITCDSeparacaoDivorcioAliquotaQDao(conn)).listGiaItcdSeparacaoAliquota(giaItcdSeparacaoDivorcioAliquotaVo);
      return giaItcdSeparacaoDivorcioAliquotaVo;
   }
   
   /**
    * @param giaItcdSeparacaoDivorcioAliquotaVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws ConexaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void alterarGiaItcdSeparacaoAliquotaAlterarGiaItcd(GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException, 
           ConsultaException, LogException, PersistenciaException, AnotacaoException, ConexaoException
   {
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);
      try
      {
         try
         {
            synchronized (GIAITCDSeparacaoDivorcioAliquotaVo.class)
            {
               GIAITCDSeparacaoDivorcioAliquotaVo consulta = new GIAITCDSeparacaoDivorcioAliquotaVo();
               GIAITCDSeparacaoDivorcioAliquotaVo parametro = new GIAITCDSeparacaoDivorcioAliquotaVo();
               parametro.getGiaItcdSeparacaoDivorcioVo().setCodigo(giaItcdSeparacaoDivorcioAliquotaVo.getGiaItcdSeparacaoDivorcioVo().getCodigo());
               consulta.setParametroConsulta(parametro);
               consulta.setConsultaCompleta(true);
               consulta = new GIAITCDSeparacaDivorcioAliquotaBe(conn).listarGiaItcdSeparacaoAliquota(consulta);
               for (Iterator ite = consulta.getCollVO().iterator(); ite.hasNext(); )
               {
                  GIAITCDSeparacaoDivorcioAliquotaVo aliquotaAtual = (GIAITCDSeparacaoDivorcioAliquotaVo) ite.next();
                  aliquotaAtual.setGiaItcdSeparacaoDivorcioVo(giaItcdSeparacaoDivorcioAliquotaVo.getGiaItcdSeparacaoDivorcioVo());
                  aliquotaAtual.setLogSefazVo(giaItcdSeparacaoDivorcioAliquotaVo.getGiaItcdSeparacaoDivorcioVo().getLogSefazVo());
                  excluir(aliquotaAtual);
               }                             
               for (Iterator ite = giaItcdSeparacaoDivorcioAliquotaVo.getCollVO().iterator(); ite.hasNext(); )
               {
                  GIAITCDSeparacaoDivorcioAliquotaVo aliquotaAtual = (GIAITCDSeparacaoDivorcioAliquotaVo) ite.next();
                  if(aliquotaAtual.getCodigoAliquota() != 0)
                  {
                     aliquotaAtual.setGiaItcdSeparacaoDivorcioVo(giaItcdSeparacaoDivorcioAliquotaVo.getGiaItcdSeparacaoDivorcioVo());
                     aliquotaAtual.setLogSefazVo(giaItcdSeparacaoDivorcioAliquotaVo.getGiaItcdSeparacaoDivorcioVo().getLogSefazVo());
                     incluirGiaItcdSeparacaoAliquota(aliquotaAtual);                   
                  }
               }                 
            }
         }
         catch (ConsultaException e)
         {
            conn.rollback();
            throw e;
         }
         catch (ObjetoObrigatorioException e)
         {
            conn.rollback();
            throw e;
         }
         catch(LogException e)
         {
            conn.rollback();
            throw e;
         }
         catch(PersistenciaException e)
         {
            conn.rollback();
            throw e;
         }
         catch(AnotacaoException e)
         {
            conn.rollback();
            throw e;
         }
         catch(ConexaoException e)
         {
            conn.rollback();
            throw e;
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }  
   }
   
   /**
    * Faz alteração da aliquota da GIA seguindo as seguinte rotina
    * 1 - Lista as aliquotas da GIA informada e exclui
    * 2 - Inclui as novas aliquotas.
    * 
    * @param giaItcdSeparacaoDivorcioVo
    */
   public void rotinaAlterarAliquota(GIAITCDSeparacaoDivorcioVo giaItcdSeparacaoDivorcioVo)
      throws ObjetoObrigatorioException, ConsultaException, SQLException, 
             LogException, PersistenciaException, AnotacaoException, 
             ConexaoException
   {
      if (!Validador.isNumericoValido(giaItcdSeparacaoDivorcioVo.getCodigo()))
      {
         throw new ConsultaException("Não foi possível consultar as aliquotas pois o nº da GIA é inválido.");
      }
         
      // Rotina de exlusão.
      GIAITCDSeparacaoDivorcioAliquotaVo aliquotaConsulta = new GIAITCDSeparacaoDivorcioAliquotaVo();
      aliquotaConsulta.setGiaItcdSeparacaoDivorcioVo(giaItcdSeparacaoDivorcioVo);
      aliquotaConsulta.setParametroConsulta(aliquotaConsulta);
         
      listarGiaItcdSeparacaoAliquota(aliquotaConsulta);
      
      for(GIAITCDSeparacaoDivorcioAliquotaVo aliquota : aliquotaConsulta.getListVo())
      {
         if (Validador.isNumericoValido(aliquota.getGiaItcdSeparacaoDivorcioVo().getCodigo()) & Validador.isNumericoValido(aliquota.getCodigoAliquota()))
         {
            GIAITCDSeparacaoDivorcioAliquotaVo aliquotaExcluir = new GIAITCDSeparacaoDivorcioAliquotaVo(aliquota.getCodigoAliquota());
            aliquotaExcluir.setCodigoAliquota(aliquota.getCodigoAliquota() );
            aliquotaExcluir.setGiaItcdSeparacaoDivorcioVo(aliquota.getGiaItcdSeparacaoDivorcioVo());
            excluirGiaItcdSeparacaoAliquota(aliquotaExcluir);
         }
      }
      
      // Rotina de Inclusão
       for(GIAITCDSeparacaoDivorcioAliquotaVo aliquota : giaItcdSeparacaoDivorcioVo.getGiaItcdSeparacaoDivorcioAliquotaVo().getListVo())
       {
          if(Validador.isNumericoValido(aliquota.getCodigoAliquota()))
          {
               aliquota.setLogSefazVo(giaItcdSeparacaoDivorcioVo.getLogSefazVo());
               incluirGiaItcdSeparacaoAliquota(aliquota);
          }
       }
   }
   
}
