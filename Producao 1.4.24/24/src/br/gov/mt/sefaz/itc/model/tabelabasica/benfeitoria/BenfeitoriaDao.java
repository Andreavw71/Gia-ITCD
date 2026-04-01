/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BenfeitoriaDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 09/10/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBenfeitoria;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
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
public class BenfeitoriaDao extends AbstractDao implements CamposBenfeitoria, TabelasITC, SequencesITC
{

	/**
	 * Construtor da Classe.
	 * @param conexao Conexão com o banco de dados que será utilizada para a manutenção dos dados.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public BenfeitoriaDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_BENFEITORIA);
	}

	/**
	 * Retorna os Campos da Tabela de Benfeitoria (ITCTB07_BENFEITORIA).
	 * Retorna um array de string contendo os campos da tabela de benfeitoriaVo.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCamposBenfeitoria()
	{
		return new String[] { CAMPO_CODIGO_BENFEITORIA, CAMPO_DESCRICAO_BENFEITORIA, CAMPO_STATUS_BENFEITORIA, CAMPO_DATA_ATUALIZACAO_BD };
	}

	/**
	 * Inclui informaçãos sobre uma Benfeitoria no banco de dados.
	 * @param benfeitoriaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void insertBenfeitoria(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, IncluiException
	{
		Validador.validaObjeto(benfeitoriaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCamposBenfeitoria());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			benfeitoriaVo.setCodigo(sequence.next(SEQUENCE_BENFEITORIA));
			GeradorLogSefazMT.gerar(benfeitoriaVo, DomnOperacao.OPERACAO_INSERT, benfeitoriaVo.getNumeroParticao(), benfeitoriaVo.getCodigoTransacao(), benfeitoriaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertBenfeitoria(ps, benfeitoriaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_BENFEITORIA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_BENFEITORIA);
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
	 * @param benfeitoriaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertBenfeitoria(final PreparedStatement ps, final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(benfeitoriaVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(benfeitoriaVo.getCodigo()))
		{
			ps.setLong(++contador, benfeitoriaVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DESCRICAO
		if (Validador.isStringValida(benfeitoriaVo.getDescricaoBenfeitoria()))
		{
			ps.setString(++contador, benfeitoriaVo.getDescricaoBenfeitoria());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// STATUS BENFEITORIA
		if (Validador.isDominioNumericoValido(benfeitoriaVo.getStatusBenfeitoria()))
		{
			ps.setInt(++contador, benfeitoriaVo.getStatusBenfeitoria().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
	}

	/**
	 * Atualiza informaçãos sobre uma Befeitoria no banco de dados.
	 * @param benfeitoriaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void updateBenfeitoria(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, AlteraException
	{
		Validador.validaObjeto(benfeitoriaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCamposBenfeitoria(), new String[] { CAMPO_CODIGO_BENFEITORIA });
		try
		{
			GeradorLogSefazMT.gerar(benfeitoriaVo, DomnOperacao.OPERACAO_UPDATE, benfeitoriaVo.getNumeroParticao(), benfeitoriaVo.getCodigoTransacao(), benfeitoriaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateBenfeitoria(ps, benfeitoriaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_BENFEITORIA);
		}
		//	   catch (LogSefazException e)
		//	   {
		//	      e.printStackTrace();
		//	      throw new AlteraException(MensagemErro.ALTERAR_BENFEITORIA);
		//	   }
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_BENFEITORIA);
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
	 * @param benfeitoriaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateBenfeitoria(final PreparedStatement ps, final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(benfeitoriaVo);
		int contador = 0;
		// DESCRICAO
		if (Validador.isStringValida(benfeitoriaVo.getDescricaoBenfeitoria()))
		{
			ps.setString(++contador, benfeitoriaVo.getDescricaoBenfeitoria());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// STATUS BENFEITORIA
		if (Validador.isDominioNumericoValido(benfeitoriaVo.getStatusBenfeitoria()))
		{
			ps.setInt(++contador, benfeitoriaVo.getStatusBenfeitoria().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
		// CODIGO
		if (Validador.isNumericoValido(benfeitoriaVo.getCodigo()))
		{
			ps.setLong(++contador, benfeitoriaVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}
}
