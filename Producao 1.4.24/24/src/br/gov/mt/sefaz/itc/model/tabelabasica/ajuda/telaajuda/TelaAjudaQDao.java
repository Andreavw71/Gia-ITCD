/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: TelaAjudaQDao.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: TelaAjudaQDao.java,v 1.1.1.1 2008/05/28 17:55:06 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telaajuda;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda.TelaCampoAjudaVo;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposTelaAjuda;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe de acesso a dados (Data Access Object).
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.1.1.1 $
 */
public class TelaAjudaQDao extends AbstractDao implements TabelasITC, CamposTelaAjuda
{
	/**
	 * Construtor da classe.
	 * @param conexao  objeto de conexão com o banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaAjudaQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metodo responsavel por criar um TelaAjudaVo apartir de um ResultSet.
	 * @param rs (Result Set).
	 * @param telaAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.	
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void getTelaAjuda(final ResultSet rs, final TelaAjudaVo telaAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(telaAjudaVo);
		telaAjudaVo.setCodigo(rs.getLong(CAMPO_CODIGO_TELA_AJUDA));
		telaAjudaVo.setDescricaoTelaAjuda(rs.getString(CAMPO_DESCRICAO_TELA_AJUDA));
		//adicionei 14/12
		TelaCampoAjudaVo telaCampoAjudaVo = new TelaCampoAjudaVo();
		telaAjudaVo.setTelaCampoAjudaVo(telaCampoAjudaVo);
	}

	/**
	 * Método utilizado para buscar dados de uma tela ajuda.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * a Tela Ajuda de menor código cadastrado no banco de dados.
	 * A pesquisa parametrizada pode ser: ou pelo código, e/ou pela descrição da funcionalidade.
	 * @param telaAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaAjudaVo findTelaAjuda(final TelaAjudaVo telaAjudaVo) throws ObjetoObrigatorioException, ConsultaException
	{
		Validador.validaObjeto(telaAjudaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindTelaAjuda(telaAjudaVo));
			prepareStatementFindTelaAjuda(ps, telaAjudaVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getTelaAjuda(rs, telaAjudaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_TELA_AJUDA);
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
		return telaAjudaVo;
	}

	/**	
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento da consulta de uma Tela Ajuda.
	 * @param ps (PreparedStatement).
	 * @param telaAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementFindTelaAjuda(final PreparedStatement ps, final TelaAjudaVo telaAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(telaAjudaVo);
		int contador = 0;
		if (telaAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((TelaAjudaVo) telaAjudaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((TelaAjudaVo) telaAjudaVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isStringValida(((TelaAjudaVo) telaAjudaVo.getParametroConsulta()).getDescricaoTelaAjuda()))
			{
				ps.setString(++contador, ((TelaAjudaVo) telaAjudaVo.getParametroConsulta()).getDescricaoTelaAjuda());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Tela Ajuda.
	 * @param telaAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String getSQLFindTelaAjuda(final TelaAjudaVo telaAjudaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(telaAjudaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT ");
		sql.append(" TELAAJUDA." + CAMPO_CODIGO_TELA_AJUDA + " ");
		sql.append(" , TELAAJUDA." + CAMPO_DESCRICAO_TELA_AJUDA + " ");
		sql.append(" FROM " + TABELA_TELA_AJUDA + " TELAAJUDA ");
		sql.append(" WHERE 1 = 1 ");
		if (telaAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((TelaAjudaVo) telaAjudaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND TELAAJUDA." + CAMPO_CODIGO_TELA_AJUDA + " = ? ");
			}
			if (Validador.isStringValida(((TelaAjudaVo) telaAjudaVo.getParametroConsulta()).getDescricaoTelaAjuda()))
			{
				sql.append("   AND TELAAJUDA." + CAMPO_DESCRICAO_TELA_AJUDA + " = ? ");
			}
		}
		sql.append(" ORDER BY TELAAJUDA." + CAMPO_CODIGO_TELA_AJUDA + " ");
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de uma coleção de Tela Ajuda.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todas as Tela Ajuda cadastrados no sistema.
	 * A pesquisa parametrizada pode ser: ou pelo código, e/ou pelo código da tela ajuda, e/ou pela descrição completa.
	 * @param telaAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta consultar um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaAjudaVo listTelaAjuda(final TelaAjudaVo telaAjudaVo) throws ObjetoObrigatorioException, ConsultaException
	{
		Validador.validaObjeto(telaAjudaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListTelaAjuda(telaAjudaVo));
			prepareStatementListTelaAjuda(ps, telaAjudaVo);
			for (rs = ps.executeQuery(); rs.next(); )
			{
				TelaAjudaVo telaAjudaVoAtual = new TelaAjudaVo();
				getTelaAjuda(rs, telaAjudaVoAtual);
				telaAjudaVo.getCollVO().add(telaAjudaVoAtual);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_TELA_AJUDA);
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
		return telaAjudaVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento de listar Tela Ajuda.
	 * @param ps (PreparedStatement).
	 * @param telaAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementListTelaAjuda(final PreparedStatement ps, final TelaAjudaVo telaAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(telaAjudaVo);
		int contador = 0;
		if (telaAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((TelaAjudaVo) telaAjudaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((TelaAjudaVo) telaAjudaVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isStringValida(((TelaAjudaVo) telaAjudaVo.getParametroConsulta()).getDescricaoTelaAjuda()))
			{
				ps.setString(++contador, ((TelaAjudaVo) telaAjudaVo.getParametroConsulta()).getDescricaoTelaAjuda());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Tela Ajuda.
	 * @param telaAjudaVo
	 * @throws ObjetoObrigatorioException
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String getSQLListTelaAjuda(final TelaAjudaVo telaAjudaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(telaAjudaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT ");
		sql.append(" TELAAJUDA." + CAMPO_CODIGO_TELA_AJUDA + " ");
		sql.append(" , TELAAJUDA." + CAMPO_DESCRICAO_TELA_AJUDA + " ");
		sql.append(" FROM " + TABELA_TELA_AJUDA + " TELAAJUDA ");
		sql.append(" WHERE 1 = 1 ");
		if (telaAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((TelaAjudaVo) telaAjudaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND TELAAJUDA." + CAMPO_CODIGO_TELA_AJUDA + " = ? ");
			}
			if (Validador.isStringValida(((TelaAjudaVo) telaAjudaVo.getParametroConsulta()).getDescricaoTelaAjuda()))
			{
				sql.append("   AND TELAAJUDA." + CAMPO_DESCRICAO_TELA_AJUDA + " = ? ");
			}
		}
		sql.append(" ORDER BY TELAAJUDA." + CAMPO_CODIGO_TELA_AJUDA + " ");
		return sql.toString();
	}
}
