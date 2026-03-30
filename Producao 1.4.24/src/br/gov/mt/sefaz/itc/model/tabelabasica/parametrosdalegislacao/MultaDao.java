package br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposParametroLegislacaoMulta;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.log.util.excecao.LogSefazException;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.ExcluiException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


/**
 * Classe de acesso a dados (Data Access Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class MultaDao extends AbstractDao implements TabelasITC, SequencesITC, CamposParametroLegislacaoMulta
{

	/**
	 * Construtor que recebe a conexão quer será utilizada para a manutenção dos dados
	 * @param conexao Conexão com o Banco de Dados
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public MultaDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_PARAMETRO_LEGISLACAO_MULTA);
	}

	/**
	 * Retorna os Campos da Tabela de Multa
	 * @return String[] - Campos da Tabela de Multa
	 * @implemented by Daniel Balieiro
	 */
	public String[] getCamposMulta()
	{
		return new String[] { CAMPO_CODIGO_MULTA, CAMPO_ITCTB09_CODIGO_PARAMETRO_LEGISLACAO, CAMPO_QUANTIDADE_DIAS_INICIAL, CAMPO_QUANTIDADE_DIAS_FINAL, CAMPO_PERCENTUAL_MULTA };
	}

	/**
	 * Inclui um Multa no Banco de dados
	 * @param multaVo MultaVo que será incluido ao banco de dados
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertMulta(final MultaVo multaVo) throws ObjetoObrigatorioException, IncluiException
	{
		Validador.validaObjeto(multaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCamposMulta());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			multaVo.setCodigo(sequence.next(SEQUENCE_PARAMETRO_LEGISLACAO_MULTA));
			GeradorLogSefazMT.gerar(multaVo, DomnOperacao.OPERACAO_INSERT, multaVo.getNumeroParticao(), multaVo.getCodigoTransacao(), multaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertMulta(ps, multaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_PARAMETRO_LEGISLACAO_MULTA);
		}
		//catch (LogSefazException e)
		//{
		//	e.printStackTrace();
		//	throw new IncluiException(MensagemErro.INCLUIR_PARAMETRO_LEGISLACAO_MULTA);
		//}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_PARAMETRO_LEGISLACAO_MULTA);
		}
		finally
		{
			if (ps != null)
			{
				try
				{
					close(ps);
					ps = null;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Alimenta o PreparedStatement para a inclusão do Multa
	 * @param ps PreparedStatement que será alimentado
	 * @param multaVo Que contém a informação para alimentar o PreparedStatement
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertMulta(final PreparedStatement ps, final MultaVo multaVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(multaVo);
		try
		{
			int contador = 0;
			// CODIGO
			if (Validador.isNumericoValido(multaVo.getCodigo()))
			{
				ps.setLong(++contador, multaVo.getCodigo());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// CODIGO PARAMETRO LEGISLACAO
			if (Validador.isNumericoValido(multaVo.getCodigoParametroLegislacao()))
			{
				ps.setLong(++contador, multaVo.getCodigoParametroLegislacao());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// QUANTIDADE DIAS INICIAL
			if (Validador.isNumericoValido(multaVo.getQuantidadeDiasInicial()))
			{
				ps.setInt(++contador, multaVo.getQuantidadeDiasInicial());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// QUANTIDADE DIAS FINAL
			if (Validador.isNumericoValido(multaVo.getQuantidadeDiasFinal()))
			{
				ps.setInt(++contador, multaVo.getQuantidadeDiasFinal());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// PERCENTUAL MULTA
			if (Validador.isNumericoValido(multaVo.getPercentualMulta()))
			{
				ps.setDouble(++contador, multaVo.getPercentualMulta());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}
	
	/**
	 * Alimenta o PreparedStatement para a alteração da Multa
	 * @param ps PreparedStatement que será alimentado
	 * @param multaVo Que contém a informação para alimentar o PreparedStatement
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateMulta(final PreparedStatement ps, final MultaVo multaVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(multaVo);
		try
		{
			int contador = 0;
			// CODIGO PARAMETRO LEGISLACAO
			if (Validador.isNumericoValido(multaVo.getCodigoParametroLegislacao()))
			{
				ps.setLong(++contador, multaVo.getCodigoParametroLegislacao());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// QUANTIDADE DIAS INICIAL
			if (Validador.isNumericoValido(multaVo.getQuantidadeDiasInicial()))
			{
				ps.setInt(++contador, multaVo.getQuantidadeDiasInicial());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// QUANTIDADE DIAS FINAL
			if (Validador.isNumericoValido(multaVo.getQuantidadeDiasFinal()))
			{
				ps.setInt(++contador, multaVo.getQuantidadeDiasFinal());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// PERCENTUAL MULTA
			if (Validador.isNumericoValido(multaVo.getPercentualMulta()))
			{
				ps.setDouble(++contador, multaVo.getPercentualMulta());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// CODIGO PK
			if (Validador.isNumericoValido(multaVo.getCodigo()))
			{
				ps.setLong(++contador, multaVo.getCodigo());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}
	
	/**
	 * Atualiza uma Multa no Banco de dados
	 * @param multaVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Fábio Vanzella
	 */
	public void updateMulta(final MultaVo multaVo) throws ObjetoObrigatorioException, AlteraException
	{
		Validador.validaObjeto(multaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCamposMulta(), new String[]{CAMPO_CODIGO_MULTA});
		try
		{
			GeradorLogSefazMT.gerar(multaVo, DomnOperacao.OPERACAO_UPDATE, multaVo.getNumeroParticao(), multaVo.getCodigoTransacao(), multaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateMulta(ps, multaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_PARAMETRO_LEGISLACAO_MULTA);
		}
		//catch (LogSefazException e)
		//{
		//	e.printStackTrace();
		//	throw new IncluiException(MensagemErro.ALTERAR_PARAMETRO_LEGISLACAO_MULTA);
		//}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_PARAMETRO_LEGISLACAO_MULTA);
		}
		finally
		{
			if (ps != null)
			{
				try
				{
					close(ps);
					ps = null;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Método para deletar uma Multa do Banco de dados
	 * @param multaVo MultaVo que será deletada
	 * @throws ObjetoObrigatorioException
	 * @throws ExcluiException
	 * @throws LogSefazException
	 * @implemented by Daniel Balieiro
	 */
	public void deleteMulta(final MultaVo multaVo) throws ObjetoObrigatorioException, ExcluiException, LogSefazException
	{
		Validador.validaObjeto(multaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_CODIGO_MULTA });
		try
		{
			GeradorLogSefazMT.gerar(multaVo, DomnOperacao.OPERACAO_DELETE, multaVo.getNumeroParticao(), multaVo.getCodigoTransacao(), multaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			prepareStatementDeleteMulta(ps, multaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.EXCLUIR_PARAMETRO_LEGISLACAO_MULTA);
		}
		//catch (LogSefazException e)
		//{
		//  e.printStackTrace();
		// throw new ExcluiException(MensagemErro.EXCLUIR_PARAMETRO_LEGISLACAO_MULTA);
		//}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.EXCLUIR_PARAMETRO_LEGISLACAO_MULTA);
		}
		finally
		{
			if (ps != null)
			{
				try
				{
					close(ps);
					ps = null;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Alimenta o PreparedStatement com os dados o MultaVo
	 * @param ps PreparedStatement que será alimentado
	 * @param multaVo MultaVo que contém os dados para alimentar o PreparedStatement
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementDeleteMulta(final PreparedStatement ps, final MultaVo multaVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(multaVo);
		Validador.validaObjeto(ps);
		try
		{
			int contador = 0;
			if (Validador.isNumericoValido(multaVo.getCodigo()))
			{
				ps.setLong(++contador, multaVo.getCodigo());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}
}
