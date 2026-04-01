package br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author Ricardo Vitor de Oliveira Moraes
 * @version
 */
public class MultaBe extends AbstractBe
{
	public MultaBe() throws SQLException
	{
		super();
	}
	
	public MultaBe(Connection conn)
	{
		super(conn);
	}

	/**
	 * @param multaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws LogException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void incluirMulta(final MultaVo multaVo) throws ObjetoObrigatorioException, ConexaoException, 
			  PersistenciaException, AnotacaoException, LogException
	{
		Validador.validaObjeto(multaVo);
		try
		{
			synchronized(MultaVo.class)
			{
				try
				{
					incluir(multaVo);
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
				catch(LogException e)
				{
					conn.rollback();
					throw e;
				}
				catch(ObjetoObrigatorioException e)
				{
					conn.rollback();
					throw e;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * @param multaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final MultaVo multaVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(multaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(multaVo);
	}

	/**
	 * @param multaVo
	 * @throws ConexaoException
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void excluirMulta(final MultaVo multaVo) throws ConexaoException, ObjetoObrigatorioException, LogException, 
			  PersistenciaException, AnotacaoException, DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(multaVo);
		try
		{
			synchronized(MultaVo.class)
			{
				try
				{
					if(!Validador.isNumericoValido(multaVo.getCodigo()))
					{
						throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_PARAMETRO_LEGISLACAO_MULTA_OBRIGATORIO);
					}
					excluir(multaVo);
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
				catch(ObjetoObrigatorioException e)
				{
					conn.rollback();
					throw e;
				}
				catch(DadoNecessarioInexistenteException e)
				{
					conn.rollback();
					throw e;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * @param multaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final MultaVo multaVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(multaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(multaVo);
	}
}
