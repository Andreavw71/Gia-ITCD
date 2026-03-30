package br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposNaturezaOperacao;
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
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.SefazDataHora;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


/**
 * Classe utilizada para realizar manutenção no banco de dados
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class NaturezaOperacaoDao extends AbstractDao implements TabelasITC, SequencesITC, CamposNaturezaOperacao
{
	/**
	 * Construtor da Classe.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public NaturezaOperacaoDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_NATUREZA_OPERACAO);
	}

	/**
	 * Retorna os Campos da Tabela de NaturezaOperacao (ITCTB03_NATR_OPRC)
	 * @return String[] 
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCamposNaturezaOperacao()
	{
		return new String[] { CAMPO_CODIGO_NATUREZA_OPERACAO, CAMPO_DESCRICAO_NATUREZA_OPERACAO, CAMPO_PERCENTUAL_NATUREZA_OPERACAO, CAMPO_TIPO_GIA, CAMPO_TIPO_BASE_CALCULO, CAMPO_TIPO_PROCESSO, CAMPO_STATUS_NATUREZA_OPERACAO, CAMPO_DATA_ATUALIZACAO_BD };
	}

	/**
	 * Inclui informaçãos sobre um NaturezaOperacao no banco de dados.
	 * @param naturezaOperacaoVo NaturezaOperacao (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void insertNaturezaOperacao(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCamposNaturezaOperacao());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			naturezaOperacaoVo.setCodigo(sequence.next(SEQUENCE_NATUREZA_OPERACAO));
			GeradorLogSefazMT.gerar(naturezaOperacaoVo, DomnOperacao.OPERACAO_INSERT, naturezaOperacaoVo.getNumeroParticao(), naturezaOperacaoVo.getCodigoTransacao(), naturezaOperacaoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertNaturezaOperacao(ps, naturezaOperacaoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_NATUREZA_OPERACAO);
		}
		catch (LogSefazException e)
		{
		  e.printStackTrace();
		 throw new IncluiException(MensagemErro.INCLUIR_NATUREZA_OPERACAO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_NATUREZA_OPERACAO);
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
	 * Método responsável por adicionar os parâmetros válidos na instrução SQL (java.sql.PrepareStatement).
	 * @param ps (java.sql.PreparedStatement).
	 * @param naturezaOperacaoVo NaturezaOperacao (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertNaturezaOperacao(final PreparedStatement ps, final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(naturezaOperacaoVo);
		int contador = 0;
		try
		{
			// CODIGO
			if (Validador.isNumericoValido(naturezaOperacaoVo.getCodigo()))
			{
				ps.setLong(++contador, naturezaOperacaoVo.getCodigo());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// DESCRICAO
			if (Validador.isStringValida(naturezaOperacaoVo.getDescricaoNaturezaOperacao()))
			{
				ps.setString(++contador, naturezaOperacaoVo.getDescricaoNaturezaOperacao());
			}
			else
			{
				ps.setNull(++contador, Types.VARCHAR);
			}
			// PERCENTUAL 
			if (Validador.isNumericoValido(naturezaOperacaoVo.getPercentualBaseCalculo()))
			{
				ps.setDouble(++contador, naturezaOperacaoVo.getPercentualBaseCalculo());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// TIPO GIA
			if (Validador.isDominioNumericoValido(naturezaOperacaoVo.getTipoGIA()))
			{
				ps.setInt(++contador, naturezaOperacaoVo.getTipoGIA().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// TIPO BASE CALCULO
			if (Validador.isDominioNumericoValido(naturezaOperacaoVo.getTipoBaseCalculo()))
			{
				ps.setInt(++contador, naturezaOperacaoVo.getTipoBaseCalculo().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// TIPO PROCESSO
			if (Validador.isDominioNumericoValido(naturezaOperacaoVo.getTipoProcesso()))
			{
				ps.setInt(++contador, naturezaOperacaoVo.getTipoProcesso().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// STATUS
			if (Validador.isDominioNumericoValido(naturezaOperacaoVo.getStatusNaturezaOperacao()))
			{
				ps.setInt(++contador, naturezaOperacaoVo.getStatusNaturezaOperacao().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// DATA ATUALIZACAO
			ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Atualiza informações sobre um NaturezaOperacao no banco de dados.
	 * @param naturezaOperacaoVo NaturezaOperacao (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void updateNaturezaOperacao(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCamposNaturezaOperacao(), new String[] { CAMPO_CODIGO_NATUREZA_OPERACAO });
		try
		{
			GeradorLogSefazMT.gerar(naturezaOperacaoVo, DomnOperacao.OPERACAO_UPDATE, naturezaOperacaoVo.getNumeroParticao(), naturezaOperacaoVo.getCodigoTransacao(), naturezaOperacaoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateNaturezaOperacao(ps, naturezaOperacaoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_NATUREZA_OPERACAO);
		}
		//catch (LogSefazException e)
		//{
		//  e.printStackTrace();
		// throw new AlteraException(MensagemErro.ALTERAR_NATUREZA_OPERACAO);
		//}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_NATUREZA_OPERACAO);
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
	 * Método responsável por adicionar os parâmetros válidos na instrução SQL (java.sql.PrepareStatement).
	 * @param ps (java.sql.PreparedStatement).
	 * @param naturezaOperacaoVo NaturezaOperacao (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateNaturezaOperacao(final PreparedStatement ps, final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(naturezaOperacaoVo);
		int contador = 0;
		try
		{
			// DESCRICAO
			if (Validador.isStringValida(naturezaOperacaoVo.getDescricaoNaturezaOperacao()))
			{
				ps.setString(++contador, naturezaOperacaoVo.getDescricaoNaturezaOperacao());
			}
			else
			{
				ps.setNull(++contador, Types.VARCHAR);
			}
			// PERCENTUAL 
			if (Validador.isNumericoValido(naturezaOperacaoVo.getPercentualBaseCalculo()))
			{
				ps.setDouble(++contador, naturezaOperacaoVo.getPercentualBaseCalculo());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// TIPO GIA
			if (Validador.isDominioNumericoValido(naturezaOperacaoVo.getTipoGIA()))
			{
				ps.setInt(++contador, naturezaOperacaoVo.getTipoGIA().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// TIPO BASE CALCULO
			if (Validador.isDominioNumericoValido(naturezaOperacaoVo.getTipoBaseCalculo()))
			{
				ps.setInt(++contador, naturezaOperacaoVo.getTipoBaseCalculo().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// TIPO PROCESSO
			if (Validador.isDominioNumericoValido(naturezaOperacaoVo.getTipoProcesso()))
			{
				ps.setInt(++contador, naturezaOperacaoVo.getTipoProcesso().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// STATUS
			if (Validador.isDominioNumericoValido(naturezaOperacaoVo.getStatusNaturezaOperacao()))
			{
				ps.setInt(++contador, naturezaOperacaoVo.getStatusNaturezaOperacao().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// DATA ATUALIZACAO
			ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
			// CODIGO
			if (Validador.isNumericoValido(naturezaOperacaoVo.getCodigo()))
			{
				ps.setLong(++contador, naturezaOperacaoVo.getCodigo());
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
