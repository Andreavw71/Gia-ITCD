/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CulturaDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 01/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.cultura;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposCultura;
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
public class CulturaDao extends AbstractDao implements TabelasITC, SequencesITC, CamposCultura
{
	/**
	 * Construtor da classe.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public CulturaDao(Connection conexao) throws SQLException
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_CULTURA);
	}

	/**
	 * Retorna os Campos da Tabela Cultura (ITCTB08_CULTURA).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	private String[] getCamposCultura()
	{
		return new String[] { CAMPO_CODIGO_CULTURA, CAMPO_DESCRICAO_CULTURA, CAMPO_STATUS_CULTURA, CAMPO_DATA_ATUALIZACAO_BD };
	}

	/**
	 * Inclui informações sobre uma Cultura.
	 * @param culturaVo Cultura (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void insertCultura(final CulturaVo culturaVo) throws ObjetoObrigatorioException, IncluiException
	{
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCamposCultura());
		Validador.validaObjeto(culturaVo);
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			culturaVo.setCodigo(sequence.next(SEQUENCE_CULTURA));
			GeradorLogSefazMT.gerar(culturaVo, DomnOperacao.OPERACAO_INSERT, culturaVo.getNumeroParticao(), 
			culturaVo.getCodigoTransacao(), culturaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertCultura(ps, culturaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_CULTURA);
		}
		catch (LogSefazException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_CULTURA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_CULTURA);
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
	 * @param ps (java.sql.PreparedStatement).
	 * @param culturaVo Cultura (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void preparedStatementInsertCultura(final PreparedStatement ps, final CulturaVo culturaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(culturaVo);
		int contador = 0;
		//CODIGO
		if (Validador.isNumericoValido(culturaVo.getCodigo()))
		{
			ps.setLong(++contador, culturaVo.getCodigo());
		}
		else
		{
			ps.setLong(++contador, Types.NUMERIC);
		}
		//DESCRICAO
		if (Validador.isStringValida(culturaVo.getDescricaoCultura()))
		{
			ps.setString(++contador, culturaVo.getDescricaoCultura());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		//STATUS CULTURA
		if (Validador.isNumericoValido(culturaVo.getStatusCultura().getValorCorrente()))
		{
			ps.setInt(++contador, culturaVo.getStatusCultura().getValorCorrente());
		}
		else
		{
			ps.setInt(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
	}

	/**
	 * Atualiza as informações de uma Cultura.
	 * @param culturaVo Cultura (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void updateCultura(final CulturaVo culturaVo) throws ObjetoObrigatorioException, AlteraException
	{
		Validador.validaObjeto(culturaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCamposCultura(), new String[] { CAMPO_CODIGO_CULTURA });
		try
		{
			GeradorLogSefazMT.gerar(culturaVo, DomnOperacao.OPERACAO_UPDATE, culturaVo.getNumeroParticao(), 
			culturaVo.getCodigoTransacao(), culturaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateCultura(ps, culturaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_CULTURA);
		}
			   catch (LogSefazException e)
			   {
					e.printStackTrace();
			      throw new AlteraException(MensagemErro.ALTERAR_CULTURA);
			   }
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_CULTURA);
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
	 * @param ps (java.sql.PreparedStatement).
	 * @param culturaVo Cultura (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void preparedStatementUpdateCultura(final PreparedStatement ps, final CulturaVo culturaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(culturaVo);
		int contador = 0;
		//DESCRICAO
		if (Validador.isStringValida(culturaVo.getDescricaoCultura()))
		{
			ps.setString(++contador, culturaVo.getDescricaoCultura());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		//STATUS CULTURA
		if (Validador.isNumericoValido(culturaVo.getStatusCultura().getValorCorrente()))
		{
			ps.setInt(++contador, culturaVo.getStatusCultura().getValorCorrente());
		}
		else
		{
			ps.setInt(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
		//CODIGO
		if (Validador.isNumericoValido(culturaVo.getCodigo()))
		{
			ps.setLong(++contador, culturaVo.getCodigo());
		}
		else
		{
			ps.setLong(++contador, Types.NUMERIC);
		}
	}
}
