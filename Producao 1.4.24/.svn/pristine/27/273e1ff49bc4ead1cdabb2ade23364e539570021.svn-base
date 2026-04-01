/*
* Secretaria de Estado de Fazenda de Mato Grosso – Sefaz-MT
* Arquivo : GIAITCDTemporarioBe.java
* Criação : Novembro de 2007
* Revisão :
* Log :
*/
package br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ImpossivelCriptografarException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;
import br.com.abaco.util.seguranca.Seguranca;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioQDao;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.util.GeraCodigoAutenticacao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.util.Iterator;

import sefaz.mt.util.IncluiException;


/**
 * Classe de negócio para a gravação do GIAITCD Temporário.
 * @author Daniel Balieiro
 */
public class GIAITCDTemporarioBe extends AbstractBe
{
	/**
	 *Construtor público padrão
	 * @throws SQLException
	 */
	public GIAITCDTemporarioBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor público recebendo uma conexao.
	 * @param conexao		conexao a ser usada para a persistencia dos dados
	 */
	public GIAITCDTemporarioBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Consulta uma GIAITCD temporária de acordo com os parametros de consulta
	 * informados.
	 * 
	 * @param giaITCDTemporarioVo			parametros de consulta
	 * @return									gia itcd temporária requerida
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 */
	public GIAITCDTemporarioVo consultaGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		(new GIAITCDTemporarioQDao(conn)).findGIAITCDTemporario(giaITCDTemporarioVo);
		return giaITCDTemporarioVo;
	}

	/**
	 * Método para Listar GIA-ITCD
	 * 
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return GIAITCDTemporarioVo
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDTemporarioVo listarGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		(new GIAITCDTemporarioQDao(conn)).listGIAITCDTemporario(giaITCDTemporarioVo);
		return giaITCDTemporarioVo;
	}
   
   /**
    * Método para Listar GIA-ITCD sem os campos BLOB e XML.
    * 
    * @param giaITCDTemporarioVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @return GIAITCDTemporarioVo
    * @implemented by Daniel Balieiro
    */
   public GIAITCDTemporarioVo listarDadosBasicosGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(giaITCDTemporarioVo);
      (new GIAITCDTemporarioQDao(conn)).listDadosBasicosGIAITCDTemporario(giaITCDTemporarioVo);
      return giaITCDTemporarioVo;
   }

	/**
	 * Método para efetuar uma listagem de GIA-ITCD Temporárias Ativas
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDTemporarioVo listarGIAITCDTemporarioAtivas(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		(new GIAITCDTemporarioQDao(conn)).listGIAITCDTemporarioAtivas(giaITCDTemporarioVo);
		if(Validador.isCollectionValida(giaITCDTemporarioVo.getCollVO()))
		{
			for(Iterator it = giaITCDTemporarioVo.getCollVO().iterator(); it.hasNext(); )
			{
				GIAITCDTemporarioVo atual = (GIAITCDTemporarioVo) it.next();
			   GIAITCDTemporarioVo parametro = new GIAITCDTemporarioVo(atual.getCodigo());
				atual.setParametroConsulta(parametro);
				new GIAITCDTemporarioQDao(conn).findGIAITCDTemporario(atual);
			}
		}
		return giaITCDTemporarioVo;
	}
   
   
   /**
    * Método para efetuar uma listagem de códigos de GIA-ITCD Temporárias Ativas
    * @param giaITCDTemporarioVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public GIAITCDTemporarioVo listarCodigoGIAITCDTemporarioAtivas(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(giaITCDTemporarioVo);
      (new GIAITCDTemporarioQDao(conn)).listGIAITCDTemporarioAtivas(giaITCDTemporarioVo);
      return giaITCDTemporarioVo;
   }

	/**
	 * Inclui um GIAITCD temporário.
	 * 
	 * @param giaITCDTemporarioVo giaitcd a ser incluido
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ImpossivelCriptografarException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException, ImpossivelCriptografarException, ConexaoException, 
			  RegistroNaoPodeSerUtilizadoException, IntegracaoException, ParametroObrigatorioException, ConsultaException, 
			  RegistroExistenteException, IOException
   {
		Validador.validaObjeto(giaITCDTemporarioVo);
		Validador.validaObjeto(giaITCDTemporarioVo.getGiaitcdVo());
		try
		{
			try
			{
				synchronized (GIAITCDTemporarioVo.class)
				{
					StatusGIAITCDVo statusGia = new StatusGIAITCDVo();
					statusGia.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.EM_ELABORACAO));
					statusGia.setGiaTemporaria(giaITCDTemporarioVo);
					statusGia.setDataAtualizacao(new Date());
					statusGia.setLogSefazVo(giaITCDTemporarioVo.getLogSefazVo());
					giaITCDTemporarioVo.setCodigoAutenticidade(new GeraCodigoAutenticacao().geraCodgAutenticacao(giaITCDTemporarioVo.getCodigo()));
				   giaITCDTemporarioVo.getGiaitcdVo().setCodigoAutenticidade(giaITCDTemporarioVo.getCodigoAutenticidade());
					giaITCDTemporarioVo.setDataGIAITCDTemporario(new Date());
					giaITCDTemporarioVo.setSenhaGIAITCD(Seguranca.criptografar(giaITCDTemporarioVo.getSenhaGIAITCD(), MensagemErro.VALIDAR_SENHA));
					giaITCDTemporarioVo.getGiaitcdVo().setSenha(giaITCDTemporarioVo.getSenhaGIAITCD());
				   giaITCDTemporarioVo.setCodigoResponsavel(giaITCDTemporarioVo.getGiaitcdVo().getResponsavelVo().getNumrContribuinte().longValue());
					giaITCDTemporarioVo.setDataAtualizacao(new Date());	
					giaITCDTemporarioVo.setStatusITCD(statusGia);
					giaITCDTemporarioVo.getGiaitcdVo().setStatusVo(statusGia);
					incluir(giaITCDTemporarioVo);
				   statusGia.setGiaTemporaria(giaITCDTemporarioVo);
					new StatusGIAITCDBe(conn).incluirStatusGIAITCD(statusGia);
				}
			}
		   catch (IncluiException e)//TODO XML
		   {
		      conn.rollback();
            e.printStackTrace();
            throw new PersistenciaException( MensagemErro.INCLUIR_GIA_ITCD_TEMPORARIO );
		   }
			catch (ImpossivelCriptografarException e)
			{
				conn.rollback();
				throw e;
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
		   catch (PersistenciaException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch (AnotacaoException e)
		   {
		      conn.rollback();
		      throw e;
		   }		
		   catch (RegistroExistenteException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch (ConsultaException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch (ParametroObrigatorioException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch (IntegracaoException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch(ConexaoException e)
		   {
		      conn.rollback();
		      throw e;          
		   }     
		   catch(RegistroNaoPodeSerUtilizadoException e)
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
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(giaITCDTemporarioVo);
	}

	/**
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException, IncluiException
   {
		Validador.validaObjeto(giaITCDTemporarioVo);		
		//new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(giaITCDTemporarioVo);
	   new GIAITCDTemporarioDao(conn).insertGIAITCDTemporario(giaITCDTemporarioVo);
	}

	/**
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		alterar(giaITCDTemporarioVo, ConfiguracaoITCD.NAO_GERA_LOG);
	}	

	private synchronized void alterar(final GIAITCDTemporarioVo giaITCDTemporarioVo, boolean naoGravaLog) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		
	   //TESTE
	    try
	    {
	       GIAITCDTemporarioVo giaParamVo = new GIAITCDTemporarioVo();
	       giaParamVo.setCodigo( giaITCDTemporarioVo.getCodigo() ); 
	       GIAITCDTemporarioVo giaConsultaVo = new GIAITCDTemporarioVo( giaParamVo );       
	       consultaGIAITCDTemporario( giaConsultaVo  );
	       new GIAITCDTemporarioDao(conn).updateGIAITCDTemporario( giaITCDTemporarioVo ); //TODO XML - Comentar o update genérico abaixo.
	       if ( !giaConsultaVo.getSenhaGIAITCD().equals( giaITCDTemporarioVo.getSenhaGIAITCD() ) )
	       {
	          ExibirLOG.exibirLogSimplificado( "As senhas são diferentes" );
	          ExibirLOG.exibirLog( "Senha no Banco de Dados: " + giaConsultaVo.getSenhaGIAITCD() + "Senha Atual: " + giaITCDTemporarioVo.getSenhaGIAITCD() );
	       }
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
		//new GenericoLogAnotacaoDao(conn, naoGravaLog).update(giaITCDTemporarioVo);
	}	

	/**
	 * Método para remover uma GIA-ITCD Temporária
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void removerGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		try
		{
			try
			{
				if (Validador.isNumericoValido(giaITCDTemporarioVo.getCodigo()))
				{
					synchronized (GIAITCDTemporarioVo.class)
					{
						excluir(giaITCDTemporarioVo);
					}
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
		   catch (PersistenciaException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch (AnotacaoException e)
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
	 * Altera um GIAITCD temporário.
	 * 
	 * @param giaITCDTemporarioVo GIAITCD temporário a ser modificado
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException, ConsultaException, ConexaoException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		Validador.validaObjeto(giaITCDTemporarioVo.getGiaitcdVo());
		try
		{
			try
			{
				if (Validador.isNumericoValido(giaITCDTemporarioVo.getCodigo()))
				{
					synchronized (GIAITCDTemporarioVo.class)
					{
						GIAITCDTemporarioVo giaITCDTemporarioAtualVo = new GIAITCDTemporarioVo(giaITCDTemporarioVo.getCodigo());
						giaITCDTemporarioAtualVo = new GIAITCDTemporarioVo(giaITCDTemporarioAtualVo);
						consultaGIAITCDTemporario(giaITCDTemporarioAtualVo);
						if (giaITCDTemporarioAtualVo.isExiste())
						{	                     
						   giaITCDTemporarioVo.setDataAtualizacao(new Date());
							alterar(giaITCDTemporarioVo);
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
		   catch (LogException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch (PersistenciaException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch (AnotacaoException e)
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

	public synchronized void alterarGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo, boolean naoGravaLog) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException, ConsultaException, ConexaoException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		Validador.validaObjeto(giaITCDTemporarioVo.getGiaitcdVo());
		try
		{
			try
			{
				if (Validador.isNumericoValido(giaITCDTemporarioVo.getCodigo()))
				{
					synchronized (GIAITCDTemporarioVo.class)
					{
						GIAITCDTemporarioVo giaITCDTemporarioAtualVo = new GIAITCDTemporarioVo(giaITCDTemporarioVo.getCodigo());
						giaITCDTemporarioAtualVo = new GIAITCDTemporarioVo(giaITCDTemporarioAtualVo);
						consultaGIAITCDTemporario(giaITCDTemporarioAtualVo);
						if (giaITCDTemporarioAtualVo.isExiste())
						{                    
							giaITCDTemporarioVo.setDataAtualizacao(new Date());
							alterar(giaITCDTemporarioVo, naoGravaLog);
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
			catch (LogException e)
			{
				conn.rollback();
				throw e;
			}
			catch (PersistenciaException e)
			{
				conn.rollback();
				throw e;
			}
			catch (AnotacaoException e)
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
   
   public GIAITCDTemporarioVo listarDadosBasicosGIAITCDTemporarioPorIntervaloDeTempoDataCriacao(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
     return (new GIAITCDTemporarioQDao(conn)).listarDadosBasicosGIAITCDTemporarioPorIntervaloDeTempoDataCriacao(giaITCDTemporarioVo);
   }
   
   
   
   /**
    * Altera um GIAITCD temporário.
    * 
    * @param giaITCDTemporarioVo GIAITCD temporário a ser modificado
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @throws ConsultaException
    * @throws ConexaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void alterarGIAITCDTemporarioSituacao(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
           LogException, AnotacaoException, PersistenciaException, ConsultaException, ConexaoException
   {
      Validador.validaObjeto(giaITCDTemporarioVo);
      Validador.validaObjeto(giaITCDTemporarioVo.getGiaitcdVo());
      try
      {
         try
         {
            if (Validador.isNumericoValido(giaITCDTemporarioVo.getCodigo()))
            {
               synchronized (GIAITCDTemporarioVo.class)
               {
                  GIAITCDTemporarioVo giaITCDTemporarioAtualVo = new GIAITCDTemporarioVo(giaITCDTemporarioVo.getCodigo());
                  giaITCDTemporarioAtualVo = new GIAITCDTemporarioVo(giaITCDTemporarioAtualVo);
                  consultaGIAITCDTemporario(giaITCDTemporarioAtualVo);
                  if (giaITCDTemporarioAtualVo.isExiste())
                  {                       
                     giaITCDTemporarioVo.setDataAtualizacao(new Date());
                     alterarSituacao(giaITCDTemporarioVo);
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
         catch (LogException e)
         {
            conn.rollback();
            throw e;
         }
         catch (PersistenciaException e)
         {
            conn.rollback();
            throw e;
         }
         catch (AnotacaoException e)
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
   
   
   private synchronized void alterarSituacao(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
           LogException, AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(giaITCDTemporarioVo);
       try
       {
          //GIAITCDTemporarioVo giaParamVo = new GIAITCDTemporarioVo();
          //giaParamVo.setCodigo( giaITCDTemporarioVo.getCodigo() ); 
          //GIAITCDTemporarioVo giaConsultaVo = new GIAITCDTemporarioVo( giaParamVo );       
          //consultaGIAITCDTemporario( giaConsultaVo  );
          new GIAITCDTemporarioDao(conn).updateGIAITCDTemporarioSituacao( giaITCDTemporarioVo );
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
      //new GenericoLogAnotacaoDao(conn, naoGravaLog).update(giaITCDTemporarioVo);
   }  
   
}
