package br.gov.mt.sefaz.itc.model.tabelabasica.iptuprefeitura;

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

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;


public class IPTUPrefeituraBe extends AbstractBe
{
   public IPTUPrefeituraBe() throws SQLException
   {
      super();
   }

   public IPTUPrefeituraBe(Connection conexao) throws SQLException
   {
      super(conexao);
   }
   
   public synchronized void incluirPrefeituraIPTU(final IPTUPrefeituraVo iptuPrefeituraVo)
       throws ObjetoObrigatorioException,  
           RegistroExistenteException, ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, 
           AnotacaoException, PersistenciaException
   {
   
      {
         Validador.validaObjeto(iptuPrefeituraVo);
         try
         {
            try
            {
               synchronized (IPTUPrefeituraVo.class)
               {
                  incluir(iptuPrefeituraVo);
                  commit();                  
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

   }
   
   public synchronized void alterarPrefeituraIPTU(final IPTUPrefeituraVo iptuPrefeituraVo)
       throws ObjetoObrigatorioException,  
           RegistroExistenteException, ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, 
           AnotacaoException, PersistenciaException
   {
   
      {
         Validador.validaObjeto(iptuPrefeituraVo);
         try
         {
            try
            {
               synchronized (IPTUPrefeituraVo.class)
               {
                  alterar(iptuPrefeituraVo);
                  commit();                  
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

   }
   
   
   private synchronized void incluir(final IPTUPrefeituraVo iptuPrefeituraVo) throws ObjetoObrigatorioException, LogException, 
           AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(iptuPrefeituraVo);      
      new GenericoLogAnotacaoDao(conn).insert(iptuPrefeituraVo , IPTUPrefeituraVo.class);
   }
   
   private synchronized void alterar(final IPTUPrefeituraVo iptuPrefeituraVo) throws ObjetoObrigatorioException, LogException, 
           AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(iptuPrefeituraVo);      
      new GenericoLogAnotacaoDao(conn).update(iptuPrefeituraVo , IPTUPrefeituraVo.class);
   }
   
   public IPTUPrefeituraVo listarIPTUPrefeitura(final IPTUPrefeituraVo iptuPrefeituraVo) throws ConsultaException, ObjetoObrigatorioException, 
           ParametroObrigatorioException, IOException, Exception
   {
      Validador.validaObjeto(iptuPrefeituraVo);
      (new IPTUPrefeituraDao(conn)).listIptuPrefeitura(iptuPrefeituraVo);
      return iptuPrefeituraVo;
   }
   
   public IPTUPrefeituraVo consultarIPTUPrefeitura(final IPTUPrefeituraVo iptuPrefeituraVo) throws ConsultaException, ObjetoObrigatorioException, 
           ParametroObrigatorioException, IOException
   {
      Validador.validaObjeto(iptuPrefeituraVo);
      (new IPTUPrefeituraDao(conn)).finIptuPrefeitura(iptuPrefeituraVo);
      return iptuPrefeituraVo;
   }
   
   
   
}
   