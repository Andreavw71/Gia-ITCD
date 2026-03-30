package br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso;

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
import br.com.abaco.util.exceptions.RegistroExistenteException;

import br.gov.mt.sefaz.eprocess.integracao.IntegracaoEProcess;
import br.gov.mt.sefaz.eprocess.integracao.TipoProcessoVO;
import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.distancia.DistanciaQDao;
import br.gov.mt.sefaz.itc.model.tabelabasica.distancia.DistanciaVo;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.integracao.eprocess.EprocessBe;
import br.gov.mt.sefaz.itc.util.integracao.eprocess.TipoProcessoIntegracaoVo;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Collection;


public class TipoProcessoBe extends AbstractBe
{
   public TipoProcessoBe()
      throws SQLException
   {
      super();
   }
   
   public TipoProcessoBe(Connection conexao) 
   {
      super(conexao);
   }
   
   /**
    * <b>Objetivo: </b> Este método tem por objetivo listar todos
    * os tipos de processos do sistema E-Process que estão ativos e possuem
    * integração com o ITCD.
    * 
    * 
    * @param tipoProcessoIntegracaoVo
    * @return tipoProcessoIntegracaoVo
    * @implemented by Dherkyan Ribeiro
    */
   public TipoProcessoIntegracaoVo listarTipoProcessoAtivoEprocessComITCD(TipoProcessoIntegracaoVo tipoProcessoIntegracaoVo) throws IntegracaoException
   {
      return new EprocessBe(conn).listarTipoProcessoAtivoEprocessComITCD(tipoProcessoIntegracaoVo);
   }
   
   public TipoProcessoVo buscarTipoProcesso(final TipoProcessoVo tipoProcessoVo)
      throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.validaObjeto(tipoProcessoVo);
      new TipoProcessoQDao(conn).findTipoProcesso(tipoProcessoVo);
      Validador.validaObjeto(tipoProcessoVo);
      return tipoProcessoVo;
   }
   
   public synchronized void incluirTipoProcesso(final TipoProcessoVo tipoProcessoVo) throws ObjetoObrigatorioException,  
           RegistroExistenteException, ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, 
           AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(tipoProcessoVo);
      try
      {
         try
         {
            synchronized(TipoProcessoVo.class)
            {
               validaParametrosIncluirTipoProcesso(tipoProcessoVo);
               TipoProcessoVo tipoProcessoConsultaVo = new TipoProcessoVo();
               //tipoProcessoConsultaVo.setCodigo(tipoProcessoConsultaVo.getCodigo());
               //tipoProcessoConsultaVo = new TipoProcessoVo(tipoProcessoConsultaVo);
               //tipoProcessoConsultaVo = buscarTipoProcesso(tipoProcessoConsultaVo);
               //Verifica se existe alguma descrição já gravada em banco para não duplicar informações
               if (tipoProcessoConsultaVo.isExiste())
               {
                  throw new RegistroExistenteException(MensagemErro.TIPO_PROCESSO_EPROCESS_ERRO_REGISTRO_EXISTE);
               }
               else
               {
                  incluir(tipoProcessoVo);
                  commit();
                  tipoProcessoConsultaVo.setMensagem(MensagemSucesso.INCLUIR);
               }
            }
         }
         catch (RegistroExistenteException e)
         {
            conn.rollback();
            throw e;
         }
         catch (ParametroObrigatorioException e)
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
         catch(AnotacaoException e)
         {
            conn.rollback();
            throw e;
         }
         catch(PersistenciaException e)
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
   
   public void validaParametrosIncluirTipoProcesso(final TipoProcessoVo tipoProcessoVo)
      throws ObjetoObrigatorioException, ParametroObrigatorioException
   {
      Validador.validaObjeto(tipoProcessoVo);
      
      if(!Validador.isNumericoValido(tipoProcessoVo.getTipoProcessoIntegracaoVo().getCodgTipoProcesso()))
      {
         throw new ParametroObrigatorioException(MensagemErro.TIPO_PROCESSO_EPROCESS_ERRO_CODG_FK);
      }
      if(!Validador.isStringValida(tipoProcessoVo.getDescTipoProcesso())) 
      {
         throw new ParametroObrigatorioException(MensagemErro.TIPO_PROCESSO_EPROCESS_ERRO_DESC_TIPO);
      }
      if(!Validador.isDominioNumericoValido(tipoProcessoVo.getDomnTipoEprocess())) 
      {
         throw new ParametroObrigatorioException(MensagemErro.TIPO_PROCESSO_EPROCESS_ERRO_DOMN);
      }
   }
   
   public TipoProcessoVo listarTipoProcesso(final TipoProcessoVo tipoProcessoVo) throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.validaObjeto(tipoProcessoVo);
      return new TipoProcessoQDao(conn).listGIAITCDTipoProcesso(tipoProcessoVo);
   }
   
   public TipoProcessoVo retornarTipoProcessoPorCodigo(final TipoProcessoVo tipoProcessoVo)
      throws ObjetoObrigatorioException, Exception
   {
      Validador.validaObjeto(tipoProcessoVo);
      IntegracaoEProcess integracaoEprocess = null;
      integracaoEprocess = new IntegracaoEProcess(conn);
      Collection<TipoProcessoVO> colecaoTipoProcesso = integracaoEprocess.consultarRelacaoTipoProcessoAtivoComIntgGiaItcd();
         
      for(TipoProcessoVO tipoProcessoVO : colecaoTipoProcesso) 
      {
         if(tipoProcessoVO.getCodigo() == tipoProcessoVo.getTipoProcessoIntegracaoVo().getCodgTipoProcesso()) 
         {
            tipoProcessoVo.setTipoProcessoIntegracaoVo(new TipoProcessoIntegracaoVo(tipoProcessoVO));
            break;
         }
      }
      return tipoProcessoVo;
   }
   
   
   /* ---------------------------------------------------------------------------------------------
    *                                Métodos GENERIC LOG DAO
    * --------------------------------------------------------------------------------------------- */
   /**
    * 
    * @param tipoProcessoVo
    * @throws LogException
    * @throws ObjetoObrigatorioException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Dherkyan Ribeiro
    */
   private synchronized void incluir(final TipoProcessoVo tipoProcessoVo) throws LogException, ObjetoObrigatorioException, 
         AnotacaoException, PersistenciaException
   {
      Validador.isObjetoValido(tipoProcessoVo);
      new GenericoLogAnotacaoDao(conn, false).insert(tipoProcessoVo);
   }

   /**
    * 
    * @param tipoProcessoVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Dherkyan Ribeiro
    */
   private synchronized void alterar(final TipoProcessoVo tipoProcessoVo) throws ObjetoObrigatorioException, LogException, 
         AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(tipoProcessoVo);
      //ConfiguracaoITCD.NAO_GERA_LOG;
      new GenericoLogAnotacaoDao(conn, true).update(tipoProcessoVo);
   }

   /**
    * 
    * @param tipoProcessoVo
    * @return
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @implemented by Dherkyan Ribeiro
    */
   public TipoProcessoVo consultarTipoProcesso(final TipoProcessoVo tipoProcessoVo) throws ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      Validador.validaObjeto(tipoProcessoVo);
      (new TipoProcessoQDao(conn)).findTipoProcesso(tipoProcessoVo);
      return tipoProcessoVo;
   }
   
   
   
   
}