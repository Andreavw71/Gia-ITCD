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
public class AliquotaBe extends AbstractBe
{
	public AliquotaBe() throws SQLException
	{
		super();
	}
	
	public AliquotaBe(Connection conn)
	{
		super(conn);
	}

	/**
	 * @param aliquotaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void incluirAliquota(final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, ConexaoException, 
			  LogException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(aliquotaVo);
		try
		{
			synchronized(AliquotaVo.class)
			{
			   try
			   {
			      incluir(aliquotaVo);
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
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * @param aliquotaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(aliquotaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(aliquotaVo);
	}

	/**
	 * @param aliquotaVo
	 * @throws ConexaoException
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void excluirAliquota(final AliquotaVo aliquotaVo) throws ConexaoException, ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(aliquotaVo);
		try
		{
			synchronized(AliquotaVo.class)
			{
				try
				{
					if(!Validador.isNumericoValido(aliquotaVo.getCodigo()))
					{
						throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_PARAMETRO_LEGISLACAO_ALIQUOTA_OBRIGATORIO);
					}
					excluir(aliquotaVo);
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
	 * @param aliquotaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(aliquotaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(aliquotaVo);
	}
}
