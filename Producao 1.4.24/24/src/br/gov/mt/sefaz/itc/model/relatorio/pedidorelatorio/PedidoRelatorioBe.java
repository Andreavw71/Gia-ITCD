package br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;

import br.gov.mt.sefaz.itc.model.relatorio.parametrorelatorio.ParametroRelatorioBe;
import br.gov.mt.sefaz.itc.model.relatorio.parametrorelatorio.ParametroRelatorioQDao;
import br.gov.mt.sefaz.itc.model.relatorio.parametrorelatorio.ParametroRelatorioVo;

import br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu.ImportacaoIPTUVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSituacaoProcessamento;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;

import br.gov.mt.sefaz.itc.util.integracao.acessoweb.AcessoWebBe;
import br.gov.mt.sefaz.itc.util.integracao.acessoweb.UsuarioIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.GestaoPessoasBe;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PedidoRelatorioBe extends AbstractBe
{
   public PedidoRelatorioBe() throws SQLException
   {
      super();
   }

   public PedidoRelatorioBe(Connection conexao) throws SQLException
   {
      super(conexao);
   }

   /* ---------------------------------------------------------------------------------------------
   *                                Métodos Validacao PedidoRelatorioBe
   * --------------------------------------------------------------------------------------------- */

   private void validarDadosParaInclusao(PedidoRelatorioVo pedidoRelatorioVo)
   {
   }

   /* ---------------------------------------------------------------------------------------------
    *                                Métodos PedidoRelatorio DAO
    * --------------------------------------------------------------------------------------------- */

   /**
    * 
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public synchronized void incluirPedidoRelatorio(final PedidoRelatorioVo pedidoRelatorioVo) throws LogException, 
         ObjetoObrigatorioException, AnotacaoException, PersistenciaException, SQLException, ParametroObrigatorioException, 
         RegistroExistenteException, ConexaoException, ConsultaException
   {
      try
      {
         validarDadosParaInclusao(pedidoRelatorioVo);
         pedidoRelatorioVo.setDataSolicitacao(new Date());
         pedidoRelatorioVo.setSituacaoProcessamento(new DomnSituacaoProcessamento(DomnSituacaoProcessamento.NAO_PROCESSADO));
         incluir(pedidoRelatorioVo);

         ParametroRelatorioBe parametroRelatorioBe = null;
         if (Validador.isCollectionValida(pedidoRelatorioVo.getParametroRelatorioVo().getCollVO()))
         {
            parametroRelatorioBe = new ParametroRelatorioBe(conn);
            for (ParametroRelatorioVo prVo: pedidoRelatorioVo.getParametroRelatorioVo().getCollVO())
            {
               prVo.setLogSefazVo(pedidoRelatorioVo.getLogSefazVo());
               prVo.setPedidoRelatorio(pedidoRelatorioVo);
               parametroRelatorioBe.incluirParametroRelatorio(prVo);
            }
         }
         commit();
      }
      catch (ObjetoObrigatorioException e)
      {
         conn.rollback();
         throw e;
      }
      catch (LogException e)
      {
         conn.rollback();
         throw e;
      }
      catch (AnotacaoException e)
      {
         conn.rollback();
         throw e;
      }
      catch (PersistenciaException e)
      {
         conn.rollback();
         throw e;
      }
   }

   /**
    * 
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public void alterarPedidoRelatorio(final PedidoRelatorioVo pedidoRelatorioVo) throws RegistroExistenteException, 
         ObjetoObrigatorioException, ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, 
         AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(pedidoRelatorioVo);
      try
      {
         try
         {
            synchronized (PedidoRelatorioVo.class)
            {
               alterar(pedidoRelatorioVo);
               commit();
               pedidoRelatorioVo.setMensagem(MensagemSucesso.ALTERAR);
            }
         }
         catch (ObjetoObrigatorioException e)
         {
            conn.rollback();
            throw e;
         }
         catch (LogException e)
         {
            conn.rollback();
            throw e;
         }
         catch (AnotacaoException e)
         {
            conn.rollback();
            throw e;
         }
         catch (PersistenciaException e)
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

   /* ---------------------------------------------------------------------------------------------
    *                                Métodos GENERIC LOG DAO
    * --------------------------------------------------------------------------------------------- */

   /**
    * 
    * @param pedidoRelatorioVo
    * @return
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    */
   public PedidoRelatorioVo listarPedidoRelatorio(final PedidoRelatorioVo pedidoRelatorioVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException, Exception
   {
      (new PedidoRelatorioQDao(conn)).listarPedidoRelatorio(pedidoRelatorioVo);
      Validador.validaObjeto(pedidoRelatorioVo);
      Validador.isCollectionValida(pedidoRelatorioVo.getCollVO());
      obterDadosServidor(pedidoRelatorioVo);
      return pedidoRelatorioVo;

   }


   /**
    * 
    * 
    * @param pedidoRelatorioVo
    * @return
    * @throws Exception
    */
   private PedidoRelatorioVo obterDadosServidor(PedidoRelatorioVo pedidoRelatorioVo)
      throws Exception
   {
      AcessoWebBe acessoWebBe = new AcessoWebBe();
      UsuarioIntegracaoVo usuarioIntegracaoVo = null;
      Validador.isObjetoValido(pedidoRelatorioVo);
      try
      {
         Iterator ite = pedidoRelatorioVo.getCollVO().iterator();        
         while (ite.hasNext())
         {
            PedidoRelatorioVo pedidoRelatorioVoAtual = (PedidoRelatorioVo) ite.next();        
            //Preparando Dados Para Consulta::..
            usuarioIntegracaoVo = new UsuarioIntegracaoVo();
            usuarioIntegracaoVo.setCodigo((int)pedidoRelatorioVoAtual.getUsuarioSolicitante().getCodigo());
            usuarioIntegracaoVo.setParametroConsulta(usuarioIntegracaoVo);
            // Consultando Dados do Contribuinte no Acesso WEB::..
            usuarioIntegracaoVo = acessoWebBe.obterUsuarioPorCodigo(usuarioIntegracaoVo);         
            pedidoRelatorioVoAtual.getUsuarioSolicitante().setNome(usuarioIntegracaoVo.getNome());
           
         }
      }
      catch (Exception erro)
      {
         throw erro;
      }
      finally
      {
         if ( Validador.isObjetoValido(acessoWebBe))
         {
            acessoWebBe.close();
         }
      }
      return pedidoRelatorioVo;
   }

   /**
    * 
    * @param pedidoRelatorioVo
    * @return
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    */
   public PedidoRelatorioVo listarPedidoRelatorioCompleto(final PedidoRelatorioVo pedidoRelatorioVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException
   {
      (new PedidoRelatorioQDao(conn)).listarPedidoRelatorio(pedidoRelatorioVo);
      Validador.validaObjeto(pedidoRelatorioVo);
      Validador.isCollectionValida(pedidoRelatorioVo.getCollVO());

      for (PedidoRelatorioVo pedido: pedidoRelatorioVo.getCollVO())
      {
         ParametroRelatorioVo parametro = new ParametroRelatorioVo();
         parametro.setPedidoRelatorio(pedido);

         ParametroRelatorioVo parametroRelatorioVo = new ParametroRelatorioVo(parametro);
         (new ParametroRelatorioQDao(conn)).listarParametrosRelatorio(parametroRelatorioVo);
         pedido.setParametroRelatorioVo(parametroRelatorioVo);
      }

      return pedidoRelatorioVo;
   }

   /**
    * 
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   private synchronized void incluir(final PedidoRelatorioVo pedidoRelatorioVo) throws LogException, 
         ObjetoObrigatorioException, AnotacaoException, PersistenciaException
   {
      Validador.isObjetoValido(pedidoRelatorioVo);
      new GenericoLogAnotacaoDao(conn, false).insert(pedidoRelatorioVo);
   }

   /**
    * 
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   private synchronized void alterar(final PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         LogException, AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(pedidoRelatorioVo);
      //ConfiguracaoITCD.NAO_GERA_LOG;
      new GenericoLogAnotacaoDao(conn, true).update(pedidoRelatorioVo);
   }

   /**
    * 
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public PedidoRelatorioVo consultarPedidoRelatorio(final PedidoRelatorioVo pedidoRelatorioVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException
   {
      Validador.validaObjeto(pedidoRelatorioVo);
      (new PedidoRelatorioQDao(conn)).findPedidoRelatorio(pedidoRelatorioVo);
      return pedidoRelatorioVo;
   }

}
