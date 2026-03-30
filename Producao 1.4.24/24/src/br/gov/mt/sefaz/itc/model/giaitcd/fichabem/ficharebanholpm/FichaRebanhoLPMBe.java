/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: RabanhoLPMBe.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data revisão: 14/11/2015
 */
package br.gov.mt.sefaz.itc.model.giaitcd.fichabem.ficharebanholpm;

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
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.integracao.lpm.ProdutoNcmBe;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;


public class FichaRebanhoLPMBe extends AbstractBe
{
   public FichaRebanhoLPMBe() throws SQLException
   {
      super();
   }

   public FichaRebanhoLPMBe(Connection conexao)
   {
      super(conexao);
   }
   /* ---------------------------------------------------------------------------------------------
   *                                Métodos Validacao RabanhoLPMBe
   * --------------------------------------------------------------------------------------------- */

   private void validarDadosParaInclusao(FichaRebanhoLPMVo RebanhoLpmVo)
   {
   }
   /* ---------------------------------------------------------------------------------------------
     *                                Métodos RabanhoLPMBe DAO
     * --------------------------------------------------------------------------------------------- */

   public synchronized void incluirRebanho(final FichaRebanhoLPMVo rebanhoLpmVo) throws LogException, ObjetoObrigatorioException, 
         AnotacaoException, PersistenciaException, SQLException, RegistroExistenteException, ConexaoException, 
         ConsultaException
   {
      try
      {
         validarDadosParaInclusao(rebanhoLpmVo);
         rebanhoLpmVo.setDataAtualizacaoBD(new Date());
         incluir(rebanhoLpmVo);
         /**
          * Não comita porque será 
          * inserido como parte de outra transação.
          *
          */
         //commit();
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

   public void alterarRebanho(final FichaRebanhoLPMVo rebanhoLpmVo) throws RegistroExistenteException, ObjetoObrigatorioException, 
         ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, AnotacaoException, 
         PersistenciaException
   {
      Validador.validaObjeto(rebanhoLpmVo);
      try
      {
         try
         {
            synchronized (FichaRebanhoLPMVo.class)
            {
               alterar(rebanhoLpmVo);
               rebanhoLpmVo.setDataAtualizacaoBD(new Date());
               commit();
               rebanhoLpmVo.setMensagem(MensagemSucesso.ALTERAR);
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

   /**
    * 
    * @param rebanhoLpmVo
    * @return
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    */
   public FichaRebanhoLPMVo listarRebanho(final FichaRebanhoLPMVo rebanhoLpmVo) throws ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      (new FichaRebanhoLPMQDao(conn)).listarRebanho(rebanhoLpmVo);
      Validador.validaObjeto(rebanhoLpmVo);
      Validador.isCollectionValida(rebanhoLpmVo.getCollVO());
      return rebanhoLpmVo;
   }

   /* ---------------------------------------------------------------------------------------------
     *                                Métodos GENERIC LOG DAO
     * --------------------------------------------------------------------------------------------- */

   private synchronized void incluir(final FichaRebanhoLPMVo rebanhoLpmVo) throws LogException, ObjetoObrigatorioException, 
         AnotacaoException, PersistenciaException
   {
      Validador.isObjetoValido(rebanhoLpmVo);
      ExibirLOG.exibirLog("Gravando dados do Rebanho");
      ExibirLOG.exibirLogSimplificado("Código: "+rebanhoLpmVo.getProdutoNcmIntegracaoVo().getCodigo());
      ExibirLOG.exibirLogSimplificado("Descricao: "+rebanhoLpmVo.getDescricao());
      new GenericoLogAnotacaoDao(conn, true).insert(rebanhoLpmVo);
   }

   private synchronized void alterar(final FichaRebanhoLPMVo rebanhoLpmVo) throws ObjetoObrigatorioException, LogException, 
         AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(rebanhoLpmVo);
      new GenericoLogAnotacaoDao(conn, false).update(rebanhoLpmVo);
   }

   public FichaRebanhoLPMVo consultarRebanhoLPMVo(final FichaRebanhoLPMVo rebanhoLpmVo) throws ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      Validador.validaObjeto(rebanhoLpmVo);
      (new FichaRebanhoLPMQDao(conn)).findRebanho(rebanhoLpmVo);
      return rebanhoLpmVo;
   }
   
   public FichaRebanhoLPMVo listarProdutoAnimalVivoPorUnidadeMedida(FichaRebanhoLPMVo fichaRebanhoLPMVo)
      throws Exception
   {
      ProdutoNcmBe produtoNcmBe = null;
      Validador.isObjetoValido(fichaRebanhoLPMVo);
      try 
      {
       produtoNcmBe =  new ProdutoNcmBe(conn);
       produtoNcmBe.listarProdutoNcmAnimaisVivosPorUnidadeDeMedida(fichaRebanhoLPMVo.getProdutoNcmIntegracaoVo());
      }
      catch(Exception erro)
      {
         throw erro;
      }
      finally 
      {
         if(produtoNcmBe != null)
         {
            produtoNcmBe.close();
         }
      }
      return fichaRebanhoLPMVo;
   }
   
}
