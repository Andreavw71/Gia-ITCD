/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: VeiculoBe.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data revisão: 14/11/2015
 */
package br.gov.mt.sefaz.itc.model.giaitcd.fichabem.fichaveiculo;

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

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;

/**
 * @author Dherkyan Ribeiro da Silva
 * @version 1.2 $
 */
public class FichaVeiculoBe extends AbstractBe
{
   public FichaVeiculoBe() throws SQLException
   {
      super();
   }

   public FichaVeiculoBe(Connection conexao)
   {
      super(conexao);
   }
   /* ---------------------------------------------------------------------------------------------
   *                                Métodos Validacao VeiculoBe
   * --------------------------------------------------------------------------------------------- */

   private void validarDadosParaInclusao(FichaVeiculoVo veiculoVo)
   {
   }
   /* ---------------------------------------------------------------------------------------------
     *                                Métodos VeiculoBe DAO
     * --------------------------------------------------------------------------------------------- */

   public synchronized void incluirVeiculo(final FichaVeiculoVo veiculoVo) throws LogException, ObjetoObrigatorioException, 
         AnotacaoException, PersistenciaException, SQLException, RegistroExistenteException, ConexaoException, 
         ConsultaException
   {
      try
      {
         validarDadosParaInclusao(veiculoVo);
         veiculoVo.setDataAtualizacaoBD(new Date());
         incluir(veiculoVo);
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

   public void alterarVeiculo(final FichaVeiculoVo veiculoVo) throws RegistroExistenteException, ObjetoObrigatorioException, 
         ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, AnotacaoException, 
         PersistenciaException
   {
      Validador.validaObjeto(veiculoVo);
      try
      {
         try
         {
            synchronized (FichaVeiculoVo.class)
            {
               alterar(veiculoVo);
               veiculoVo.setDataAtualizacaoBD(new Date());
               commit();
               veiculoVo.setMensagem(MensagemSucesso.ALTERAR);
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
    * @param veiculoVo
    * @return
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    */
   public FichaVeiculoVo listarVeiculo(final FichaVeiculoVo veiculoVo) throws ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      (new FichaVeiculoQDao(conn)).listarVeiculo(veiculoVo);
      Validador.validaObjeto(veiculoVo);
      Validador.isCollectionValida(veiculoVo.getCollVO());
      return veiculoVo;
   }

   /* ---------------------------------------------------------------------------------------------
     *                                Métodos GENERIC LOG DAO
     * --------------------------------------------------------------------------------------------- */

   private synchronized void incluir(final FichaVeiculoVo veiculoVo) throws LogException, ObjetoObrigatorioException, 
         AnotacaoException, PersistenciaException
   {
      Validador.isObjetoValido(veiculoVo);
      new GenericoLogAnotacaoDao(conn, true).insert(veiculoVo);
   }

   private synchronized void alterar(final FichaVeiculoVo veiculoVo) throws ObjetoObrigatorioException, LogException, 
         AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(veiculoVo);
      new GenericoLogAnotacaoDao(conn, false).update(veiculoVo);
   }

   public FichaVeiculoVo consultarVeiculo(final FichaVeiculoVo veiculoVo) throws ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      Validador.validaObjeto(veiculoVo);
      (new FichaVeiculoQDao(conn)).findVeiculo(veiculoVo);
      return veiculoVo;
   }
}
