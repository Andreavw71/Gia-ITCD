/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: MultaDeMoraBe.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 12/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.multademora;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;


/**
 * Classe de negócio da entidade Multa de Mora
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
public class MultaDeMoraBe extends AbstractBe
{
	/**
	 * Construtor Padrão
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public MultaDeMoraBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a conexão com o Banco de Dados
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public MultaDeMoraBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Valida os Parametros pra Incluir uma Multa de Mota
	 * @param multaDeMoraVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosIncluirMultaDeMora(final MultaDeMoraVo multaDeMoraVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(multaDeMoraVo);
		if (multaDeMoraVo.getPercentualMultaMora() < 0)
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_MENOR_ZERO_MULTA_MORA);
		}
		else if (!Validador.isNumericoValido(multaDeMoraVo.getPercentualMultaMora()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_MULTA_MORA);
		}
		else if (multaDeMoraVo.getPercentualMultaMora() < 0)
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_MULTA_MORA);
		}
		if (multaDeMoraVo.getPercentualMaximoMultaMora() < 0)
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PERCENTUAL_MULTA_MORA_MENOR_ZERO);
		}
		else if (!Validador.isNumericoValido(multaDeMoraVo.getPercentualMaximoMultaMora()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PERCENTUAL_MULTA_MORA);
		}
		else if (multaDeMoraVo.getPercentualMultaMora() > multaDeMoraVo.getPercentualMaximoMultaMora())
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_MULTA_MORA_MENOR_PERCENTUAL);
		}
	}

	/**
	 * Efetua a consulta de uma Multa de Mora
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public synchronized MultaDeMoraVo consultaMultaDeMora() throws ObjetoObrigatorioException, ConexaoException, 
			  ConsultaException
	{
		MultaDeMoraVo retorno = new MultaDeMoraVo();
		try
		{
			try
			{
				synchronized (MultaDeMoraVo.class)
				{
					MultaDeMoraQDao qdao = new MultaDeMoraQDao(conn);
				   MultaDeMoraVo multaParamVo = new MultaDeMoraVo();
				   multaParamVo.setStatusMultaMora(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
				   MultaDeMoraVo multaConsultaVo = new MultaDeMoraVo(multaParamVo);
					retorno = qdao.findMultaDeMora( multaConsultaVo );
				}
			}
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch (ConexaoException e)
			{
				conn.rollback();
				throw e;
			}
			catch (ConsultaException e)
			{
				conn.rollback();
				throw e;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_MULTA_MORA);
		}
		return retorno;
	}

	/**
	 * Método para incluir a Multa de Mora
	 * @param multaDeMoraVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirMultaDeMora(final MultaDeMoraVo multaDeMoraVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ParametroObrigatorioException, ConexaoException, LogException, AnotacaoException, 
			  PersistenciaException
	{
		Validador.validaObjeto(multaDeMoraVo);
		try
		{
			try
			{
				synchronized (MultaDeMoraVo.class)
				{
					validaParametrosIncluirMultaDeMora(multaDeMoraVo);
					MultaDeMoraVo multaDeMoraConsultaVo = new MultaDeMoraVo();
					multaDeMoraConsultaVo.setStatusMultaMora(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
//					multaDeMoraConsultaVo.setPercentualMultaMora(multaDeMoraVo.getPercentualMultaMora());
//					multaDeMoraConsultaVo.setPercentualMaximoMultaMora(multaDeMoraVo.getPercentualMaximoMultaMora());
					multaDeMoraConsultaVo = new MultaDeMoraVo(multaDeMoraConsultaVo);					
					(new MultaDeMoraQDao(conn)).findMultaDeMora(multaDeMoraConsultaVo);
					if (multaDeMoraConsultaVo.isExiste())
					{
						multaDeMoraConsultaVo.setStatusMultaMora(new DomnStatusRegistro(DomnStatusRegistro.INATIVO));
						multaDeMoraConsultaVo.setDataAtualizacao(new Date());
						multaDeMoraConsultaVo.setLogSefazVo(multaDeMoraVo.getLogSefazVo());
						alterar(multaDeMoraConsultaVo);
					}
					multaDeMoraVo.setStatusMultaMora(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
					multaDeMoraVo.setDataAtualizacao(new Date());
					incluir(multaDeMoraVo);
					commit();
					multaDeMoraVo.setMensagem(MensagemSucesso.INCLUIR);
				}
			}
			catch (ConsultaException ce)
			{
				conn.rollback();
				throw ce;
			}
			catch (ParametroObrigatorioException poe)
			{
				conn.rollback();
				throw poe;
			}
			catch (ObjetoObrigatorioException ooe)
			{
				conn.rollback();
				throw ooe;
			}
			catch(LogException e)
			{
				conn.rollback();
				throw e;
			}
			catch(AnotacaoException e)
			{
				conn.rollback();
				throw e;
			}
			catch(PersistenciaException e)
			{
				conn.rollback();
				throw e;
			}
		}
		catch (SQLException se)
		{
			se.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * @param multaMoraVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final MultaDeMoraVo multaMoraVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(multaMoraVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(multaMoraVo);
	}

	/**
	 * @param multaMoraVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final MultaDeMoraVo multaMoraVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(multaMoraVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(multaMoraVo);
	}
}
