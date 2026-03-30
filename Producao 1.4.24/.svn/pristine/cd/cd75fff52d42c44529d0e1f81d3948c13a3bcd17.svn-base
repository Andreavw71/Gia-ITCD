package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.aliquota;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaVo;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Iterator;


public class GIAITCDDoacaoBeneficiarioAliquotaBe extends AbstractBe
{
   
   /**
    * Contrutor padrão vazio
    * @implemented by Lucas Nascimento
    */
   public GIAITCDDoacaoBeneficiarioAliquotaBe() throws SQLException
   {
      super();
   }

   /**
    * Contrutor recebendo uma conexão
    * @implemented by Lucas Nascimento
    */
   public GIAITCDDoacaoBeneficiarioAliquotaBe(Connection conn) throws SQLException
   {
      super(conn);
   }
   
   /**
    * @param giaItcdDoacaoBeneficiarioAliquotaVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void incluir(final GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
           LogException, AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(giaItcdDoacaoBeneficiarioAliquotaVo);
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(giaItcdDoacaoBeneficiarioAliquotaVo);
   }

   /**
    * Inclui no banco de dados uma Aliquota de um determinado beneficiario de uma gia do tipo Doação
    * 
    * @param giaItcdDoacaoBeneficiarioAliquotaVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws ConexaoException
    * @implemented by Lucas Nascimento
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void incluirGiaItcdDoacaoBeneficiarioAliquota(final GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
           LogException, PersistenciaException, AnotacaoException, ConexaoException
   {
      Validador.validaObjeto(giaItcdDoacaoBeneficiarioAliquotaVo);
      try
      {
         try
         {
            synchronized (GIAITCDDoacaoBeneficiarioAliquotaVo.class)
            {
               incluir(giaItcdDoacaoBeneficiarioAliquotaVo);
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
    * @param giaItcdDoacaoBeneficiarioAliquotaVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void alterar(final GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
           LogException, AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(giaItcdDoacaoBeneficiarioAliquotaVo);
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(giaItcdDoacaoBeneficiarioAliquotaVo);
   }

   /**
    * Altera os dados persistidos de uma determinada aliquota de um determinado beneficiário de uma determinada
    * GIA do tipo Doação
    * 
    * @param giaItcdDoacaoBeneficiarioAliquotaVo     dados a serem modificados
    * @throws ObjetoObrigatorioException
    */
   public synchronized void alterarGiaItcdDoacaoBeneficiarioAliquota(final GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
           LogException, PersistenciaException, AnotacaoException, ConexaoException
   {
      Validador.validaObjeto(giaItcdDoacaoBeneficiarioAliquotaVo);
      try
      {
         try
         {
            synchronized (GIAITCDDoacaoBeneficiarioAliquotaVo.class)
            {
               alterar(giaItcdDoacaoBeneficiarioAliquotaVo);
               giaItcdDoacaoBeneficiarioAliquotaVo.setMensagem(MensagemSucesso.ALTERAR);
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
    * @param giaItcdDoacoBeneficiarioAliquotaVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws ConexaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void excluirGiaItcdDoacaoBeneficiarioAliquota(final GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
           LogException, PersistenciaException, AnotacaoException, ConexaoException
   {
      Validador.validaObjeto(giaItcdDoacoBeneficiarioAliquotaVo);
      try
      {
         try
         {
            synchronized (GIAITCDDoacaoBeneficiarioAliquotaVo.class)
            {
               excluir(giaItcdDoacoBeneficiarioAliquotaVo);
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
    * @param giaItcdDoacaoBeneficiarioAliquotaVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void excluir(final GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
           LogException, AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(giaItcdDoacaoBeneficiarioAliquotaVo);   
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(giaItcdDoacaoBeneficiarioAliquotaVo);
   }

   /**
    * Consulta uma aliquota para o beneficiario
    * @param giaItcdDoacaoBeneficiarioAliquotaVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @implemented by Lucas Nascimento
    */
   public GIAITCDDoacaoBeneficiarioAliquotaVo consultarGiaItcdDoacoaBeneficiarioAliquota(final GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(giaItcdDoacaoBeneficiarioAliquotaVo);
      (new GIAITCDDoacaoBeneficiarioAliquotaQDao(conn)).findGiaItcdDoacaoBeneficiarioAliquota(giaItcdDoacaoBeneficiarioAliquotaVo);
      return giaItcdDoacaoBeneficiarioAliquotaVo;
   }
   
   public GIAITCDDoacaoBeneficiarioVo consultaAliquotaDoacaoBeneficiarioGiaPermanente(final GIAITCDDoacaoBeneficiarioVo giaDoacaoBeneficiario)throws ObjetoObrigatorioException, 
           ConsultaException{
      (new GIAITCDDoacaoBeneficiarioAliquotaQDao(conn)).findGiaItcdDoacaoBeneficiarioAliquotaBeneficiarioPermanente(giaDoacaoBeneficiario);
      return giaDoacaoBeneficiario;
   }
   
   /**
    * Consulta uma aliquota a partir do numero da gia.
    * @param giaItcdDoacaoBeneficiarioAliquotaVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @implemented by Lucas Nascimento
    */
   public void consultarGiaItcdAliquotaDoacaoSucessiva(final GIAITCDDoacaoSucessivaVo giaItcdDoacaoSucessiva, final Long numrPessoaBenef) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      (new GIAITCDDoacaoBeneficiarioAliquotaQDao(conn)).findGiaItcdDoacaoBeneficiarioAliquotaDoacaoSucessiva(giaItcdDoacaoSucessiva, numrPessoaBenef);
   }

   /**
    * Retorna uma lista de aliquotas de uma determinado beneficiario de Doação
    * @param giaItcdDoacaoBeneficiarioAliquotaVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @implemented by Lucas Nascimento
    */
   public GIAITCDDoacaoBeneficiarioAliquotaVo listarGiaItcdDoacaoBeneficiarioAliquota(final GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(giaItcdDoacaoBeneficiarioAliquotaVo);
      (new GIAITCDDoacaoBeneficiarioAliquotaQDao(conn)).listGiaItcdDoacaoBeneficiarioAliquota(giaItcdDoacaoBeneficiarioAliquotaVo);
      return giaItcdDoacaoBeneficiarioAliquotaVo;
   }

   /**
    * @param giaItcdDoacaoBeneficiarioAliquotaVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws ConexaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void alterarGiaItcdDoacaoBeneficiarioAliquotaAlterarGiaItcdDoacaoBeneficiario(GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
           ConsultaException, LogException, PersistenciaException, AnotacaoException, ConexaoException
   {
      Validador.validaObjeto(giaItcdDoacaoBeneficiarioAliquotaVo);
      try
      {
         try
         {
            synchronized (GIAITCDDoacaoBeneficiarioAliquotaVo.class)
            {
               GIAITCDDoacaoBeneficiarioAliquotaVo consulta = new GIAITCDDoacaoBeneficiarioAliquotaVo();
               GIAITCDDoacaoBeneficiarioAliquotaVo parametro = new GIAITCDDoacaoBeneficiarioAliquotaVo();
               parametro.getGiaItcdDoacaoBeneficiarioVo().setCodigo(giaItcdDoacaoBeneficiarioAliquotaVo.getGiaItcdDoacaoBeneficiarioVo().getCodigo());
               consulta.setParametroConsulta(parametro);
               consulta.setConsultaCompleta(true);
               consulta = new GIAITCDDoacaoBeneficiarioAliquotaBe(conn).listarGiaItcdDoacaoBeneficiarioAliquota(consulta);
               for (Iterator ite = consulta.getCollVO().iterator(); ite.hasNext(); )
               {
                  GIAITCDDoacaoBeneficiarioAliquotaVo aliquotaAtual = (GIAITCDDoacaoBeneficiarioAliquotaVo) ite.next();
                  aliquotaAtual.setGiaItcdDoacaoBeneficiarioVo(giaItcdDoacaoBeneficiarioAliquotaVo.getGiaItcdDoacaoBeneficiarioVo());
                  aliquotaAtual.setLogSefazVo(giaItcdDoacaoBeneficiarioAliquotaVo.getGiaItcdDoacaoBeneficiarioVo().getLogSefazVo());
                  excluir(aliquotaAtual);
               }                             
               for (Iterator ite = giaItcdDoacaoBeneficiarioAliquotaVo.getCollVO().iterator(); ite.hasNext(); )
               {
                  GIAITCDDoacaoBeneficiarioAliquotaVo aliquotaAtual = (GIAITCDDoacaoBeneficiarioAliquotaVo) ite.next();
                  if(aliquotaAtual.getCodigoAliquota() != 0)
                  {
                     aliquotaAtual.setGiaItcdDoacaoBeneficiarioVo(giaItcdDoacaoBeneficiarioAliquotaVo.getGiaItcdDoacaoBeneficiarioVo());
                     aliquotaAtual.setLogSefazVo(giaItcdDoacaoBeneficiarioAliquotaVo.getGiaItcdDoacaoBeneficiarioVo().getLogSefazVo());
                     incluirGiaItcdDoacaoBeneficiarioAliquota(aliquotaAtual);                   
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
   
   
   
}
