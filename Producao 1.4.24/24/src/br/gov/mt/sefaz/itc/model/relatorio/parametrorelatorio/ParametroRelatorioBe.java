package br.gov.mt.sefaz.itc.model.relatorio.parametrorelatorio;

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

public class ParametroRelatorioBe extends AbstractBe
{
   public ParametroRelatorioBe() throws SQLException
   {
      super();
   }

   public ParametroRelatorioBe(Connection conexao) throws SQLException
   {
      super(conexao);
   }

   /* ---------------------------------------------------------------------------------------------
   *                                Métodos Validacao
   * --------------------------------------------------------------------------------------------- */

   private void validarDadosParaInclusao(ParametroRelatorioVo parametroRelatorioVo)
   {
   }

   /* ---------------------------------------------------------------------------------------------
   *                                Métodos DAO
   * --------------------------------------------------------------------------------------------- */

   /**
    * 
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public synchronized void incluirParametroRelatorio(final ParametroRelatorioVo parametroRelatorioVo) throws LogException, 
         ObjetoObrigatorioException, AnotacaoException, PersistenciaException, SQLException, ParametroObrigatorioException, 
         RegistroExistenteException, ConexaoException, ConsultaException
   {
      try
      {
         validarDadosParaInclusao(parametroRelatorioVo);
         incluir(parametroRelatorioVo);
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
   public void alterarParametroRelatorio(final ParametroRelatorioVo parametroRelatorioVo) throws RegistroExistenteException, 
         ObjetoObrigatorioException, ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, 
         AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(parametroRelatorioVo);
      try
      {
         try
         {
            synchronized (ParametroRelatorioVo.class)
            {
               alterar(parametroRelatorioVo);
               commit();
               parametroRelatorioVo.setMensagem(MensagemSucesso.ALTERAR);
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
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   private synchronized void incluir(final ParametroRelatorioVo parametroRelatorioVo) throws LogException, 
         ObjetoObrigatorioException, AnotacaoException, PersistenciaException
   {
      Validador.isObjetoValido(parametroRelatorioVo);
      new GenericoLogAnotacaoDao(conn, false).insert(parametroRelatorioVo);
   }

   /**
    * 
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   private synchronized void alterar(final ParametroRelatorioVo parametroRelatorioVo) throws ObjetoObrigatorioException, 
         LogException, AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(parametroRelatorioVo);
      //ConfiguracaoITCD.NAO_GERA_LOG;
      new GenericoLogAnotacaoDao(conn, true).update(parametroRelatorioVo);
   }

   /**
    * 
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public ParametroRelatorioVo consultarParametroRelatorio(final ParametroRelatorioVo parametroRelatorioVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException
   {
      Validador.validaObjeto(parametroRelatorioVo);
      (new ParametroRelatorioQDao(conn)).findParametroRelatorio(parametroRelatorioVo);
      return parametroRelatorioVo;
   }

}
