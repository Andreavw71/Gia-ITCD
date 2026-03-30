/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AvaliacaoBemTributavelServidorBe.java
 * Revisão:
 * Data revisão:
 * $Id: AvaliacaoBemTributavelServidorBe.java,v 1.1.1.1 2008/05/28 17:55:04 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.avaliacao.servidor;

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

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.GestaoPessoasBe;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Iterator;


/**
 * Classe para gerencia das regras de negócio da Avaliação Bem Tributável - Servidor
 *
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class AvaliacaoBemTributavelServidorBe extends AbstractBe
{
	/**
	 * Construtor vazio
	 *
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelServidorBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe uma conexão com o banco de dados
	 *
	 * @param conn
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelServidorBe(Connection conn)
	{
		super(conn);
	}

	/**
	 * Método para consultar uma Avaliação Bem Tributável - Servidor
	 * 
	 * @param avaliacaoBemTributavelServidorVo
	 * @return AvaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelServidorVo consultarAvaliacaoBemTributavelServidor(final AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, ParametroObrigatorioException
	{
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		(new AvaliacaoBemTributavelServidorQDao(conn)).findAvaliacaoBemTributavelServidor(avaliacaoBemTributavelServidorVo);
		if (avaliacaoBemTributavelServidorVo.isConsultaCompleta() && avaliacaoBemTributavelServidorVo.isExiste())
		{
			ServidorSefazIntegracaoVo servidorVo = new ServidorSefazIntegracaoVo();
			servidorVo.setNumrMatricula(avaliacaoBemTributavelServidorVo.getServidorSefazVo().getNumrMatricula());
			GestaoPessoasBe gestaoPessoas = new GestaoPessoasBe(conn);
			avaliacaoBemTributavelServidorVo.setServidorSefazVo(gestaoPessoas.buscarServidorSefazPorNumrMatricula(servidorVo));
		}
		return avaliacaoBemTributavelServidorVo;
	}

	/**
	 * Método para listar as Avaliações Bem Tributável - Servidor
	 * 
	 * @param avaliacaoBemTributavelServidorVo
	 * @return AvaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public AvaliacaoBemTributavelServidorVo listarAvaliacaoBemTributavelServidor(final AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, ParametroObrigatorioException
	{
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		(new AvaliacaoBemTributavelServidorQDao(conn)).listAvaliacaoBemTributavelServidor(avaliacaoBemTributavelServidorVo);
		if (avaliacaoBemTributavelServidorVo.isExisteCollVO() && avaliacaoBemTributavelServidorVo.isConsultaCompleta())
		{
			for (Iterator iteAvalServidor = 
							avaliacaoBemTributavelServidorVo.getCollVO().iterator(); iteAvalServidor.hasNext(); )
			{
				AvaliacaoBemTributavelServidorVo avalServAtual = (AvaliacaoBemTributavelServidorVo) iteAvalServidor.next();
				ServidorSefazIntegracaoVo servidorVo = new ServidorSefazIntegracaoVo();
				servidorVo.setNumrMatricula(avalServAtual.getServidorSefazVo().getNumrMatricula());
				GestaoPessoasBe gestaoPessoas = new GestaoPessoasBe(conn);
				avalServAtual.setServidorSefazVo(gestaoPessoas.buscarServidorSefazPorNumrMatricula(servidorVo));
			}
		}
		return avaliacaoBemTributavelServidorVo;
	}

	/**
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(avaliacaoBemTributavelServidorVo);
	}

	/**
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(avaliacaoBemTributavelServidorVo);
	}	

	/**
	 * ´Método para inserir uma nova Avaliação Bem Tributável - Servidor
	 * 
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirAvaliacaoBemTributavelServidor(final AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		try
		{
			try
			{
				synchronized (AvaliacaoBemTributavelServidorVo.class)
				{
					incluir(avaliacaoBemTributavelServidorVo);
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
	 * Método para alterar uma Avaliação Bem Tributável - Servidor
	 * 
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarAvaliacaoBemTributavelServidor(final AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		try
		{
			try
			{
				synchronized (AvaliacaoBemTributavelServidorVo.class)
				{
					alterar(avaliacaoBemTributavelServidorVo);
					conn.commit();
					avaliacaoBemTributavelServidorVo.setMensagem(MensagemSucesso.INCLUIR);
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
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(avaliacaoBemTributavelServidorVo);
	}

	/**
	 * Método para apagar uma Avaliação Bem Tributável - Servidor
	 * 
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void apagarAvaliacaoBemTributavelServidorPorAvaliacao(final AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		try
		{
			try
			{
				synchronized (AvaliacaoBemTributavelServidorVo.class)
				{
					excluir(avaliacaoBemTributavelServidorVo);
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
}
