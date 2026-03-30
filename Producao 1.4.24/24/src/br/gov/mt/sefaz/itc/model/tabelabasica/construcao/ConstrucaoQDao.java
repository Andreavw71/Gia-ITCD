/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConstrucaoQDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 12/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.construcao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposConstrucao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe de acesso a dados (Data Access Object).
 * @author Marlo Eichenberg Motta	
 * @version $Revision: 1.1.1.1 $
 */
public class ConstrucaoQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposConstrucao
{

	/**
	 * Construtor da classe.
	 * @param conexao - Conexão com o banco de dados que será usada durante a manutenção dos dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public ConstrucaoQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metodo responsavel por criar um ConstrucaoVo apartir de um ResultSet.
	 * @param rs ResultSet que contém os dados de retorno da consulta.
	 * @param construcaoVo ConstrucaoVo que será alimentada com os dados.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void getConstrucao(final ResultSet rs, final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(construcaoVo);
		construcaoVo.setCodigo(rs.getLong(CAMPO_CODIGO_CONSTRUCAO));
		construcaoVo.setDataAtualizacao(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
		construcaoVo.setDescricaoConstrucao(rs.getString(CAMPO_DESCRICAO_CONSTRUCAO));
		construcaoVo.setStatusConstrucao(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_CONSTRUCAO)));
		construcaoVo.setPermiteFichaRural(new DomnSimNao(rs.getInt(CAMPO_PERMITE_FICHA_RURAL)));
		construcaoVo.setPermiteFichaUrbano(new DomnSimNao(rs.getInt(CAMPO_PERMITE_FICHA_URBANO)));
	}

	/**
	 * Método utilizado para buscar dados de uma determinada Construção.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * a Construção de menor código cadastrado no banco de dados.
	 * A pesquisa parametrizada pode ser: ou pelo código, e/ou pela descrição completa, e/ou pelo status da Construção.
	 * @param construcaoVo ConstrucaoVo que contém os parametros de consulta.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return ConstrucaoVo com os dados encontrados.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public ConstrucaoVo findConstrucao(final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(construcaoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindConstrucao(construcaoVo));
			prepareStatementFindConstrucao(ps, construcaoVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getConstrucao(rs, construcaoVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_CONSTRUCAO);
		}
		finally
		{
			try
			{
				close(ps, rs);
				ps = null;
				rs = null;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return construcaoVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento da consulta de uma Construção.
	 * @param ps PreparedStatement que será alimentado para a consulta.
	 * @param construcaoVo ConstrucaoVo que contém os parametros de consulta.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindConstrucao(final PreparedStatement ps, final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(construcaoVo);
		int contador = 0;
		if (construcaoVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((ConstrucaoVo) construcaoVo.getParametroConsulta()).getCodigo());
			}
			// DESCRICAO
			if (Validador.isStringValida(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getDescricaoConstrucao()))
			{
				ps.setString(++contador, ((ConstrucaoVo) construcaoVo.getParametroConsulta()).getDescricaoConstrucao());
			}
			// STATUS CONSTRUCAO
			if (Validador.isDominioNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getStatusConstrucao()))
			{
				ps.setInt(++contador, ((ConstrucaoVo) construcaoVo.getParametroConsulta()).getStatusConstrucao().getValorCorrente());
			}
			// PERMITE FICHA RURAL
			if (Validador.isDominioNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getPermiteFichaRural()))
			{
				ps.setInt(++contador, ((ConstrucaoVo) construcaoVo.getParametroConsulta()).getPermiteFichaRural().getValorCorrente());
			}
			// PERMITE FICHA URBANO
			if (Validador.isDominioNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getPermiteFichaUrbano()))
			{
				ps.setInt(++contador, ((ConstrucaoVo) construcaoVo.getParametroConsulta()).getPermiteFichaUrbano().getValorCorrente());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Construção.
	 * @param construcaoVo ConstrucaoVo que contém as informações para a geração do SQL de consulta.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @return String SQL Gerada.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindConstrucao(final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(construcaoVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT CONSTRUCAO.").append(CAMPO_CODIGO_CONSTRUCAO);
		sql.append(" , CONSTRUCAO.").append(CAMPO_DATA_ATUALIZACAO_BD);
		sql.append(" , CONSTRUCAO.").append(CAMPO_DESCRICAO_CONSTRUCAO);
		sql.append(" , CONSTRUCAO.").append(CAMPO_STATUS_CONSTRUCAO);
		sql.append(",  CONSTRUCAO.").append(CAMPO_PERMITE_FICHA_RURAL);
		sql.append(", CONSTRUCAO.").append(CAMPO_PERMITE_FICHA_URBANO);
		sql.append("  FROM ").append(TABELA_CONSTRUCAO).append(" CONSTRUCAO ");
		sql.append("  WHERE 1 = 1 ");
		if (construcaoVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("  AND CONSTRUCAO.").append(CAMPO_CODIGO_CONSTRUCAO).append(" = ? ");
			}
			if (Validador.isStringValida(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getDescricaoConstrucao()))
			{
				sql.append(" AND UPPER(CONSTRUCAO.").append(CAMPO_DESCRICAO_CONSTRUCAO).append(") = (UPPER(?)) ");
			}
			if (Validador.isDominioNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getStatusConstrucao()))
			{
				sql.append("  AND CONSTRUCAO.").append(CAMPO_STATUS_CONSTRUCAO).append(" = ? ");
			}
			if (Validador.isDominioNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getPermiteFichaRural()))
			{
				sql.append("  AND CONSTRUCAO.").append(CAMPO_PERMITE_FICHA_RURAL).append(" = ? ");
			}
			if (Validador.isDominioNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getPermiteFichaUrbano()))
			{
				sql.append("  AND CONSTRUCAO.").append(CAMPO_PERMITE_FICHA_URBANO).append(" = ? ");
			}
		}
		sql.append(" ORDER BY CONSTRUCAO.").append(CAMPO_CODIGO_CONSTRUCAO);
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de uma coleção de Construções.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todos as Construções cadastrados no sistema.
	 * A pesquisa parametrizada pode ser: ou pelo código, e/ou pela descrição completa, e/ou pelo status da Construção.
	 * @param construcaoVo ConstrucaoVo que contém os parametros de consulta.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return ConstrucaoVo com os dados encontrados.`
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public ConstrucaoVo listConstrucao(final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(construcaoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListarConstrucao(construcaoVo));
			prepareStatementListConstrucao(ps, construcaoVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				ConstrucaoVo construcaoAtualVo = new ConstrucaoVo();
				getConstrucao(rs, construcaoAtualVo);
				construcaoVo.getCollVO().add(construcaoAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_CONSTRUCAO);
		}
		finally
		{
			try
			{
				close(ps, rs);
				ps = null;
				rs = null;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return construcaoVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos para listar Construções.
	 * @param ps PreparedStatement que será alimentado para a consulta.
	 * @param construcaoVo ConstrucaoVo que contém os parametros de consulta.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementListConstrucao(final PreparedStatement ps, final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(construcaoVo);
		int contador = 0;
		if (construcaoVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((ConstrucaoVo) construcaoVo.getParametroConsulta()).getCodigo());
			}
			// STATUS CONSTRUCAO
			if (Validador.isDominioNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getStatusConstrucao()))
			{
				ps.setInt(++contador, ((ConstrucaoVo) construcaoVo.getParametroConsulta()).getStatusConstrucao().getValorCorrente());
			}
			// PERMITE FICHA RURAL
			if (Validador.isDominioNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getPermiteFichaRural()))
			{
				ps.setInt(++contador, ((ConstrucaoVo) construcaoVo.getParametroConsulta()).getPermiteFichaRural().getValorCorrente());
			}
			// PERMITE FICHA URBANO
			if (Validador.isDominioNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getPermiteFichaUrbano()))
			{
				ps.setInt(++contador, ((ConstrucaoVo) construcaoVo.getParametroConsulta()).getPermiteFichaUrbano().getValorCorrente());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Construção.
	 * @param construcaoVo Construcao (Value Object).
	 * @return String
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListarConstrucao(final ConstrucaoVo construcaoVo)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  CONSTRUCAO.").append(CAMPO_CODIGO_CONSTRUCAO);
		sql.append(" , CONSTRUCAO.").append(CAMPO_DATA_ATUALIZACAO_BD);
		sql.append(" , CONSTRUCAO.").append(CAMPO_DESCRICAO_CONSTRUCAO);
		sql.append(" , CONSTRUCAO.").append(CAMPO_STATUS_CONSTRUCAO);
		sql.append(",  CONSTRUCAO.").append(CAMPO_PERMITE_FICHA_RURAL);
		sql.append(", CONSTRUCAO.").append(CAMPO_PERMITE_FICHA_URBANO);
		sql.append(" FROM ").append(TABELA_CONSTRUCAO).append(" CONSTRUCAO ");
		sql.append(" WHERE 1 = 1 ");
		if (construcaoVo != null && construcaoVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND CONSTRUCAO.").append(CAMPO_CODIGO_CONSTRUCAO).append(" = ? ");
			}
			if (Validador.isStringValida(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getDescricaoConstrucao()))
			{
				sql.append(" AND UPPER(CONSTRUCAO.").append(CAMPO_DESCRICAO_CONSTRUCAO).append(") LIKE (UPPER('%").append(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getDescricaoConstrucao()).append("%')) ");
			}
			if (Validador.isDominioNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getStatusConstrucao()))
			{
				sql.append("   AND CONSTRUCAO.").append(CAMPO_STATUS_CONSTRUCAO).append(" = ? ");
			}
			if (Validador.isDominioNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getPermiteFichaRural()))
			{
				sql.append("  AND CONSTRUCAO.").append(CAMPO_PERMITE_FICHA_RURAL).append(" = ? ");
			}
			if (Validador.isDominioNumericoValido(((ConstrucaoVo) construcaoVo.getParametroConsulta()).getPermiteFichaUrbano()))
			{
				sql.append("  AND CONSTRUCAO.").append(CAMPO_PERMITE_FICHA_URBANO).append(" = ? ");
			}
		}
		sql.append(" ORDER BY CONSTRUCAO.").append(CAMPO_DESCRICAO_CONSTRUCAO);
		return sql.toString();
	}
}
