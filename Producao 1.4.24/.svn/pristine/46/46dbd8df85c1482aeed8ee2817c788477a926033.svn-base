package br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu;

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
import br.gov.mt.sefaz.itc.util.dominio.DomnGeracaoServico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.Date;


public class ImportacaoIPTUBe extends AbstractBe
{
   public ImportacaoIPTUBe() throws SQLException
   {
      super();
   }

   public ImportacaoIPTUBe(Connection conexao) throws SQLException
   {
      super(conexao);
   }
   
   public synchronized void incluirArquivoIPTU(final ImportacaoIPTUVo importacaoIPTUVo)
       throws ObjetoObrigatorioException,  
			  RegistroExistenteException, ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, 
			  AnotacaoException, PersistenciaException
   {
   
	   {
	      Validador.validaObjeto(importacaoIPTUVo);
	      try
	      {
	         try
	         {
	            synchronized (ImportacaoIPTUVo.class)
	            {
	               incluir(importacaoIPTUVo);
	               commit();
	               importacaoIPTUVo.getArquivoIPTU().delete();
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
   public synchronized void alterarArquivoIPTU(final ImportacaoIPTUVo importacaoIPTUVo)
       throws ObjetoObrigatorioException,  
           RegistroExistenteException, ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, 
           AnotacaoException, PersistenciaException
   {
   
      {
         Validador.validaObjeto(importacaoIPTUVo);
         try
         {
            try
            {
               synchronized (ImportacaoIPTUVo.class)
               {
                  alterar(importacaoIPTUVo);
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
   
   private synchronized void incluir(final ImportacaoIPTUVo importacaoIPTUVo) throws ObjetoObrigatorioException, LogException, 
           AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(importacaoIPTUVo);      
      new GenericoLogAnotacaoDao(conn , true).insert(importacaoIPTUVo , ImportacaoIPTUVo.class);
   }
   
   private synchronized void alterar(final ImportacaoIPTUVo importacaoIPTUVo) throws ObjetoObrigatorioException, LogException, 
           AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(importacaoIPTUVo);      
      new GenericoLogAnotacaoDao(conn).update(importacaoIPTUVo , ImportacaoIPTUVo.class);
   }
   
   public ImportacaoIPTUVo preparaIncluirArquivoIPTU(ImportacaoIPTUVo importacaoIPTUVo)
      throws Exception
   {
      try
      {  
         importacaoIPTUVo.setCodgUsuarioSeq(importacaoIPTUVo.getLogSefazVo().getUsuario().getCodigo());
         importacaoIPTUVo.setDescNomeArquivoAntigo(importacaoIPTUVo.getArquivoIPTU().getName());
         importacaoIPTUVo.setStatusImportacao(new DomnGeracaoServico(DomnGeracaoServico.NAO_PROCESSADO));
         importacaoIPTUVo.setDataHoraUpload(new Date());         
         renomearArquivoIPTUdiretorio(importacaoIPTUVo);        
      }
      catch(Exception erro)
      {
         throw erro;
      }
      return importacaoIPTUVo;
   }

   public ImportacaoIPTUVo renomearArquivoIPTUdiretorio(ImportacaoIPTUVo importacaoIPTUVo)
   {
      Date data = new Date();
      String extensaoArquivo = 
         importacaoIPTUVo.getArquivoIPTU().getName().substring(importacaoIPTUVo.getArquivoIPTU().getName().lastIndexOf("."), 
                                                               importacaoIPTUVo.getArquivoIPTU().getName().length());
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
      String dataFormatada = sdf.format(data);

      if (Validador.isObjetoValido(importacaoIPTUVo.getArquivoIPTU()))
      {
         File arquivoNovo =  new File(importacaoIPTUVo.getArquivoIPTU().getParent(), 
                     importacaoIPTUVo.getMunicipioVo().getNomeMunicipio() + 
                     "-" + importacaoIPTUVo.getLogSefazVo().getUsuario().getIdentificacao() + "-" + 
                     dataFormatada + "" + extensaoArquivo);
                     
         importacaoIPTUVo.getArquivoIPTU().renameTo(arquivoNovo);
         importacaoIPTUVo.setArquivoIPTU(arquivoNovo);
         importacaoIPTUVo.setDescNomeArquivoNovo(arquivoNovo.getName());
      }
      return importacaoIPTUVo;
   }

   public ImportacaoIPTUVo buscarArquivoIPTUdiretorio(ImportacaoIPTUVo importacaoIPTUVo)
      throws FileNotFoundException, IOException
   {
      InputStream is;
      byte[] bytes;
      int offset = 0;
      int numRead = 0;

      if (Validador.isObjetoValido(importacaoIPTUVo.getArquivoIPTU()))
      {
         try
         {
            is = new FileInputStream(importacaoIPTUVo.getArquivoIPTU());
            bytes = new byte[(int) importacaoIPTUVo.getArquivoIPTU().length()];
            while (offset < bytes.length && 
                   (numRead = is.read(bytes, offset, bytes.length - offset)) >= 
                   0)
            {
               offset += numRead;
            }
            is.close();
         }
         catch (FileNotFoundException fileNotFoundException)
         {
            throw fileNotFoundException;
         }
         catch (IOException ioException)
         {
            throw ioException;
         }
         importacaoIPTUVo.setArqvIptu(bytes);

      }
      return importacaoIPTUVo;
   }
   public ImportacaoIPTUVo listImportacao(final ImportacaoIPTUVo importacaoIPTUVo) throws ConsultaException, ObjetoObrigatorioException, 
           ParametroObrigatorioException, IOException, Exception
   {
      Validador.validaObjeto(importacaoIPTUVo);
      (new ImportacaoIPTUDao(conn)).listImportacao(importacaoIPTUVo);
      return importacaoIPTUVo;
   }
   
   public ImportacaoIPTUVo consultarImportacao(final ImportacaoIPTUVo importacaoIPTUVo) throws ConsultaException, ObjetoObrigatorioException, 
           ParametroObrigatorioException, IOException, Exception
   {
      Validador.validaObjeto(importacaoIPTUVo);
      (new ImportacaoIPTUDao(conn)).consultarImportacao(importacaoIPTUVo);
      return importacaoIPTUVo;
   }

}
