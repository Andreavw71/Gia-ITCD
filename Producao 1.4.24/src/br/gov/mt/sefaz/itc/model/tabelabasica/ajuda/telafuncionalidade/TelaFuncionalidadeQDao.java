/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: TelaFuncionalidadeQDao.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: TelaFuncionalidadeQDao.java,v 1.1.1.1 2008/05/28 17:55:06 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telafuncionalidade;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.funcionalidade.FuncionalidadeVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telaajuda.TelaAjudaVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposTelaAjuda;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposTelaFuncionalidade;
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
public class TelaFuncionalidadeQDao extends AbstractDao implements TabelasITC, CamposTelaFuncionalidade
{
	/**
	 * Construtor da classe.
	 * @param conexao  objeto de conexão com o banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaFuncionalidadeQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metodo responsavel por criar um TelaFuncionalidadeVo apartir de um ResultSet.
	 * @param rs (java.sql.ResultSet).
	 * @param telaFuncionalidadeVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.	
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void getTelaFuncionalidade(final ResultSet rs, final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(telaFuncionalidadeVo);
		telaFuncionalidadeVo.setCodigo(rs.getLong(CAMPO_CODIGO_FUNCIONALIDADE)); //inserindo codigo
		telaFuncionalidadeVo.setFuncionalidadeVo(new FuncionalidadeVo(rs.getLong(CAMPO_CODIGO_FUNCIONALIDADE)));
		telaFuncionalidadeVo.setTelaAjudaVo(new TelaAjudaVo(rs.getLong(CAMPO_CODIGO_TELA_AJUDA)));
		telaFuncionalidadeVo.setDescricaoTelaFuncionalidade(rs.getString(CAMPO_DESCRICAO_TELA_FUNCIONALIDADE));
		telaFuncionalidadeVo.setInformacaoTituloTelaFuncionalidade(rs.getString(CAMPO_INFORMACAO_TITULO_TELA_FUNCIONALIDADE));
		telaFuncionalidadeVo.setStatusTelaFuncionalidade(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_TELA_FUNCIONALIDADE)));
		telaFuncionalidadeVo.setCodigoOrdenacao(rs.getInt(CAMPO_CODIGO_ORDENACAO));
	}

	/**
	 * Método utilizado para buscar dados de uma  Tela Funcionalidade.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * a Tela Funcionalidade de menor código cadastrado no banco de dados.
	 * A pesquisa parametrizada pode ser: e/ou pelo código da funcionalidade, e/ou pelo código do tela ajuda.
	 * @param telaFuncionalidadeVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaFuncionalidadeVo findTelaFuncionalidade(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindTelaFuncionalidade(telaFuncionalidadeVo));
			prepareStatementFindTelaFuncionalidade(ps, telaFuncionalidadeVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getTelaFuncionalidade(rs, telaFuncionalidadeVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_TELA_FUNCIONALIDADE);
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
		return telaFuncionalidadeVo;
	}

	/**	
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento da consulta de uma Tela Funcionalidade.
	 * @param ps (PreparedStatement).
	 * @param telaFuncionalidadeVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementFindTelaFuncionalidade(final PreparedStatement ps, final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(telaFuncionalidadeVo);
		int contador = 0;
		if (telaFuncionalidadeVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getFuncionalidadeVo().getCodigo()))
			{
				ps.setLong(++contador, ((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getFuncionalidadeVo().getCodigo());
			}
			if (Validador.isNumericoValido(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getTelaAjudaVo().getCodigo()))
			{
				ps.setLong(++contador, ((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getTelaAjudaVo().getCodigo());
			}
			if (Validador.isStringValida(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getDescricaoTelaFuncionalidade()))
			{
				ps.setString(++contador, ((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getDescricaoTelaFuncionalidade());
			}
			if (Validador.isStringValida(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getInformacaoTituloTelaFuncionalidade()))
			{
				ps.setString(++contador, ((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getInformacaoTituloTelaFuncionalidade());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Tela Funcionalidade.
	 * @param telaFuncionalidadeVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String getSQLFindTelaFuncionalidade(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" TELA_FUNCIONALIDADE." + CAMPO_CODIGO_FUNCIONALIDADE + " ");
		sql.append(" , TELA_FUNCIONALIDADE." + CAMPO_CODIGO_TELA_AJUDA + " ");
		sql.append(" , TELA_FUNCIONALIDADE." + CAMPO_DESCRICAO_TELA_FUNCIONALIDADE + " ");
		sql.append(" , TELA_FUNCIONALIDADE." + CAMPO_INFORMACAO_TITULO_TELA_FUNCIONALIDADE + " ");
		sql.append(" , TELA_FUNCIONALIDADE." + CAMPO_STATUS_TELA_FUNCIONALIDADE + " ");
		sql.append(" FROM " + TABELA_TELA_FUNCIONALIDADE + " TELA_FUNCIONALIDADE ");
		sql.append(" WHERE 1 = 1 ");
		if (telaFuncionalidadeVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getFuncionalidadeVo().getCodigo()))
			{
				sql.append("   AND TELA_FUNCIONALIDADE." + CAMPO_CODIGO_FUNCIONALIDADE + " = ? ");
			}
			if (Validador.isNumericoValido(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getTelaAjudaVo().getCodigo()))
			{
				sql.append("   AND TELA_FUNCIONALIDADE." + CAMPO_CODIGO_TELA_AJUDA + " = ? ");
			}
			if (Validador.isStringValida(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getDescricaoTelaFuncionalidade()))
			{
				sql.append("   AND TELA_FUNCIONALIDADE." + CAMPO_DESCRICAO_TELA_FUNCIONALIDADE + " = ? ");
			}
			if (Validador.isStringValida(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getInformacaoTituloTelaFuncionalidade()))
			{
				sql.append("   AND TELA_FUNCIONALIDADE." + CAMPO_INFORMACAO_TITULO_TELA_FUNCIONALIDADE + " = ? ");
			}
		}
		sql.append(" ORDER BY TELA_FUNCIONALIDADE." + CAMPO_CODIGO_FUNCIONALIDADE + " ");
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de uma coleção de Tela Funcionalidade.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todos os Tela Funcionalidade cadastrados no sistema.
	 * A pesquisa parametrizada pode ser: e/ou pelo código da funcionalidade, e/ou pelo código da tela de ajuda.
	 * @param telaFuncionalidadeVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta consultar um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaFuncionalidadeVo listTelaFuncionalidade(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListTelaFuncionalidade(telaFuncionalidadeVo));
			prepareStatementListTelaFuncionalidade(ps, telaFuncionalidadeVo);
			for (rs = ps.executeQuery(); rs.next(); )
			{
				TelaFuncionalidadeVo telaFuncionalidadeVoAtual = new TelaFuncionalidadeVo();
				getTelaFuncionalidade(rs, telaFuncionalidadeVoAtual);
				telaFuncionalidadeVo.getCollVO().add(telaFuncionalidadeVoAtual);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_TELA_FUNCIONALIDADE);
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
		return telaFuncionalidadeVo;
	}

	/**
	 * Método utilizado para buscar dados de uma coleção de Tela Funcionalidade para ser apresentado ao usuário.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todos os Tela Funcionalidade cadastrados no sistema.
	 * A pesquisa parametrizada pode ser: e/ou pelo código da funcionalidade, e/ou pelo código da tela de ajuda.
	 * @param telaFuncionalidadeVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta consultar um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Wendell Pereira de Farias
	 */
	public TelaFuncionalidadeVo listTelaFuncionalidadeUsuario(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListTelaFuncionalidade(telaFuncionalidadeVo));
			prepareStatementListTelaFuncionalidade(ps, telaFuncionalidadeVo);
			for (rs = ps.executeQuery(); rs.next(); )
			{
				TelaFuncionalidadeVo telaFuncionalidadeVoAtual = new TelaFuncionalidadeVo();
				//Condição utilizada para verificar a integridade do TelaFuncionalidade.
				if (telaFuncionalidadeVo.getCodigo() == 0)
				{
					getTelaFuncionalidade(rs, telaFuncionalidadeVo);
				}
				getTelaFuncionalidade(rs, telaFuncionalidadeVoAtual);
				telaFuncionalidadeVo.getCollVO().add(telaFuncionalidadeVoAtual);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_TELA_FUNCIONALIDADE);
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
		return telaFuncionalidadeVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento de listar Tela Funcionalidade.
	 * @param ps (PreparedStatement).
	 * @param telaFuncionalidadeVo
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementListTelaFuncionalidade(final PreparedStatement ps, final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(telaFuncionalidadeVo);
		int contador = 0;
		if (telaFuncionalidadeVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getFuncionalidadeVo().getCodigo()))
			{
				ps.setLong(++contador, ((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getFuncionalidadeVo().getCodigo());
			}
			if (Validador.isNumericoValido(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getTelaAjudaVo().getCodigo()))
			{
				ps.setLong(++contador, ((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getTelaAjudaVo().getCodigo());
			}
			if (Validador.isStringValida(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getDescricaoTelaFuncionalidade()))
			{
				ps.setString(++contador, ((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getDescricaoTelaFuncionalidade());
			}
			if (((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getCodigo() != 0)
			{
				if (Validador.isStringValida(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getInformacaoTituloTelaFuncionalidade()))
				{
					ps.setString(++contador, ((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getInformacaoTituloTelaFuncionalidade());
				}
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Tela Funcionalidade.
	 * @param telaFuncionalidadeVo Tela/Funcionalidade (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	private String getSQLListTelaFuncionalidade(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT DISTINCT ");
		sql.append(" TELA_FUNCIONALIDADE." + CAMPO_CODIGO_FUNCIONALIDADE + " ");
		sql.append(" , TELA_FUNCIONALIDADE." + CAMPO_CODIGO_TELA_AJUDA + " ");
		sql.append(" , TELA_FUNCIONALIDADE." + CAMPO_DESCRICAO_TELA_FUNCIONALIDADE + " ");
		sql.append(" , TELA_FUNCIONALIDADE." + CAMPO_INFORMACAO_TITULO_TELA_FUNCIONALIDADE + " ");
		sql.append(" , TELA_FUNCIONALIDADE." + CAMPO_STATUS_TELA_FUNCIONALIDADE + " ");
	   sql.append(" , TELA_FUNCIONALIDADE." + CAMPO_CODIGO_ORDENACAO + " ");
		sql.append(" FROM ");
		sql.append("  " + TABELA_TELA_FUNCIONALIDADE + " TELA_FUNCIONALIDADE ");
		sql.append("  , " + TABELA_TELA_AJUDA + " TELA_AJUDA ");
		sql.append(" WHERE 1 = 1 ");
		if (telaFuncionalidadeVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getFuncionalidadeVo().getCodigo()))
			{
				sql.append("   AND TELA_FUNCIONALIDADE." + CAMPO_CODIGO_FUNCIONALIDADE + " = ? ");
			}
			if (Validador.isNumericoValido(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getTelaAjudaVo().getCodigo()))
			{
				sql.append("   AND TELA_FUNCIONALIDADE." + CAMPO_CODIGO_TELA_AJUDA + " = ? ");
			}
			if (Validador.isStringValida(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getDescricaoTelaFuncionalidade()))
			{
				sql.append("   AND TELA_FUNCIONALIDADE." + CAMPO_DESCRICAO_TELA_FUNCIONALIDADE + " = ? ");
			}
			if (((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getCodigo() == 0)
			{
				if (Validador.isStringValida(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getInformacaoTituloTelaFuncionalidade()))
				{
					sql.append("   AND TELA_FUNCIONALIDADE." + CAMPO_INFORMACAO_TITULO_TELA_FUNCIONALIDADE + " LIKE (UPPER('%" + 
										 ((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getInformacaoTituloTelaFuncionalidade() + 
										 "%')) ");
				}
			}
			else if (Validador.isStringValida(((TelaFuncionalidadeVo) telaFuncionalidadeVo.getParametroConsulta()).getInformacaoTituloTelaFuncionalidade()))
			{
				sql.append("   AND TELA_FUNCIONALIDADE." + CAMPO_INFORMACAO_TITULO_TELA_FUNCIONALIDADE + " = ? ");
			}
			sql.append("  AND TELA_FUNCIONALIDADE." + CAMPO_CODIGO_TELA_AJUDA + " =  " + " TELA_AJUDA." + 
							CamposTelaAjuda.CAMPO_CODIGO_TELA_AJUDA + " ");
		}
		sql.append(" ORDER BY TELA_FUNCIONALIDADE." + CAMPO_CODIGO_ORDENACAO + " ");
		return sql.toString();
	}
}
