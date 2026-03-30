/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConstrucaoDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 12/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.construcao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposConstrucao;
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
public class ConstrucaoDao extends AbstractDao implements CamposConstrucao, TabelasITC, SequencesITC
{
	/**
	 * Construtor da Classe.
	 * @param conexao Conexão com o banco de dados que será utilizada para a manutenção dos dados.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public ConstrucaoDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_CONSTRUCAO);
	}

	/**
	 * Retorna os Campos da Tabela de Construções (ITCTB13_CONSTRUCAO).
	 * @return String[] Campos da Tabela de Construções 
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCamposConstrucao()
	{
		return new String[] { CAMPO_CODIGO_CONSTRUCAO, CAMPO_DESCRICAO_CONSTRUCAO, CAMPO_STATUS_CONSTRUCAO, CAMPO_DATA_ATUALIZACAO_BD, CAMPO_PERMITE_FICHA_RURAL, CAMPO_PERMITE_FICHA_URBANO };
	}

	/**
	 * Inclui informaçãos sobre uma Construção.
	 * @param construcaoVo - ConstrucaoVo que será inseria no banco de dados.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void insertConstrucao(final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, IncluiException
	{
		Validador.validaObjeto(construcaoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCamposConstrucao());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			construcaoVo.setCodigo(sequence.next(SEQUENCE_CONSTRUCAO));
			GeradorLogSefazMT.gerar(construcaoVo, DomnOperacao.OPERACAO_INSERT, construcaoVo.getNumeroParticao(), construcaoVo.getCodigoTransacao(), construcaoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertConstrucao(ps, construcaoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_CONSTRUCAO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_CONSTRUCAO);
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
	 * @param construcaoVo ConstrucaoVo que contém os dados para alimentar o PreparedStatement.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertConstrucao(final PreparedStatement ps, final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(construcaoVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(construcaoVo.getCodigo()))
		{
			ps.setLong(++contador, construcaoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DESCRICAO
		if (Validador.isStringValida(construcaoVo.getDescricaoConstrucao()))
		{
			ps.setString(++contador, construcaoVo.getDescricaoConstrucao());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// STATUS CONSTRUCAO
		if (Validador.isDominioNumericoValido(construcaoVo.getStatusConstrucao()))
		{
			ps.setInt(++contador, construcaoVo.getStatusConstrucao().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
		// PERMITE FICHA RURAL
		if (Validador.isDominioNumericoValido(construcaoVo.getPermiteFichaRural()))
		{
			ps.setInt(++contador, construcaoVo.getPermiteFichaRural().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// PERMITE FICHA URBANO
		if (Validador.isDominioNumericoValido(construcaoVo.getPermiteFichaUrbano()))
		{
			ps.setInt(++contador, construcaoVo.getPermiteFichaUrbano().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Atualiza informaçãos sobre uma Construção.
	 * @param construcaoVo ConstrucaoVo que será atulizada no banco de dados.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void updateConstrucao(final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, AlteraException
	{
		Validador.validaObjeto(construcaoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCamposConstrucao(), new String[] { CAMPO_CODIGO_CONSTRUCAO });
		try
		{
			GeradorLogSefazMT.gerar(construcaoVo, DomnOperacao.OPERACAO_UPDATE, construcaoVo.getNumeroParticao(), construcaoVo.getCodigoTransacao(), construcaoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateConstrucao(ps, construcaoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_CONSTRUCAO);
		}
		/*catch (LogSefazException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_CONSTRUCAO);
		}*/
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_CONSTRUCAO);
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
	 * @param construcaoVo ConstrucaoVo que contém os dados para alimentar o PreparedStatement.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateConstrucao(final PreparedStatement ps, final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(construcaoVo);
		int contador = 0;
		// DESCRICAO
		if (Validador.isStringValida(construcaoVo.getDescricaoConstrucao()))
		{
			ps.setString(++contador, construcaoVo.getDescricaoConstrucao());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// STATUS CONSTRUCAO
		if (Validador.isDominioNumericoValido(construcaoVo.getStatusConstrucao()))
		{
			ps.setInt(++contador, construcaoVo.getStatusConstrucao().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
		// PERMITE FICHA RURAL
		if (Validador.isDominioNumericoValido(construcaoVo.getPermiteFichaRural()))
		{
			ps.setInt(++contador, construcaoVo.getPermiteFichaRural().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// PERMITE FICHA URBANO
		if (Validador.isDominioNumericoValido(construcaoVo.getPermiteFichaUrbano()))
		{
			ps.setInt(++contador, construcaoVo.getPermiteFichaUrbano().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CODIGO
		if (Validador.isNumericoValido(construcaoVo.getCodigo()))
		{
			ps.setLong(++contador, construcaoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}
}
