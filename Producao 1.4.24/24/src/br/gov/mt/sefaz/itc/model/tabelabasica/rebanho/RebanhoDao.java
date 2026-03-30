/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: RebanhoDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 03/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.rebanho;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposRebanho;
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
 * Classe de acesso a dados (Data Access Object).
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
public class RebanhoDao extends AbstractDao implements CamposRebanho, TabelasITC, SequencesITC
{

	/**
	 * Construtor da Classe.
	 * @param conexao Conexão com o banco de dados que será utilizada para a manutenção dos dados.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public RebanhoDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_REBANHO);
	}

	/**
	 * Retorna os campos da tabela de Rebanhos (ITCTB10_REBANHO).
	 * @return String[] Campos da Tabela de Rebanhos 
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCamposRebanho()
	{
		return new String[] { CAMPO_CODIGO_REBANHO, CAMPO_DESCRICAO_REBANHO, CAMPO_STATUS_REBANHO, CAMPO_DATA_ATUALIZACAO_BD };
	}

	/**
	 * Inclui informaçãos sobre uma Rebanho.
	 * @param rebanhoVo - RebanhoVo que será inseria no banco de dados.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void insertRebanho(final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, IncluiException
	{
		Validador.validaObjeto(rebanhoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCamposRebanho());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			rebanhoVo.setCodigo(sequence.next(SEQUENCE_REBANHO));
			GeradorLogSefazMT.gerar(rebanhoVo, DomnOperacao.OPERACAO_INSERT, rebanhoVo.getNumeroParticao(), 
			rebanhoVo.getCodigoTransacao(), rebanhoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertRebanho(ps, rebanhoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_REBANHO);
		}
		catch (LogSefazException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_REBANHO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_REBANHO);
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
	 * Método responsável por adicionar os valores válidos no PrepareStatement.
	 * @param ps PreparedStatement que será alimentada para a consulta.
	 * @param rebanhoVo RebanhoVo que contém os dados para alimentar o PreparedStatement.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertRebanho(final PreparedStatement ps, final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(rebanhoVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(rebanhoVo.getCodigo()))
		{
			ps.setLong(++contador, rebanhoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DESCRICAO
		if (Validador.isStringValida(rebanhoVo.getDescricaoRebanho()))
		{
			ps.setString(++contador, rebanhoVo.getDescricaoRebanho());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// STATUS REBANHO
		if (Validador.isDominioNumericoValido(rebanhoVo.getStatusRebanho()))
		{
			ps.setInt(++contador, rebanhoVo.getStatusRebanho().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
	}

	/**
	 * Atualiza informaçãos sobre um Rebanho.
	 * @param rebanhoVo RebanhoVo que será atulizada no banco de dados.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void updateRebanho(final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, AlteraException
	{
		Validador.validaObjeto(rebanhoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCamposRebanho(), new String[] { CAMPO_CODIGO_REBANHO });
		try
		{
			GeradorLogSefazMT.gerar(rebanhoVo, DomnOperacao.OPERACAO_UPDATE, rebanhoVo.getNumeroParticao(), 
			rebanhoVo.getCodigoTransacao(), rebanhoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateRebanho(ps, rebanhoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_REBANHO);
		}
		catch (LogSefazException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_REBANHO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_REBANHO);
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
	 * Método responsável por adicionar os valores válidos no PrepareStatement.
	 * @param ps PreparedStatement que será alimentada para a consulta.
	 * @param rebanhoVo RebanhoVo que contém os dados para alimentar o PreparedStatement.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateRebanho(final PreparedStatement ps, final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(rebanhoVo);
		int contador = 0;
		// DESCRICAO
		if (Validador.isStringValida(rebanhoVo.getDescricaoRebanho()))
		{
			ps.setString(++contador, rebanhoVo.getDescricaoRebanho());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// STATUS REBANHO
		if (Validador.isDominioNumericoValido(rebanhoVo.getStatusRebanho()))
		{
			ps.setInt(++contador, rebanhoVo.getStatusRebanho().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
		// CODIGO
		if (Validador.isNumericoValido(rebanhoVo.getCodigo()))
		{
			ps.setLong(++contador, rebanhoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}
}
