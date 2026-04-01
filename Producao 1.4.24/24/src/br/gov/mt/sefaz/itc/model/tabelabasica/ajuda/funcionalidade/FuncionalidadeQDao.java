/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: FuncionalidadeQDao.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: FuncionalidadeQDao.java,v 1.1.1.1 2008/05/28 17:55:06 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.funcionalidade;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.moduloajuda.ModuloAjudaVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFuncionalidade;
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
public class FuncionalidadeQDao extends AbstractDao implements TabelasITC, CamposFuncionalidade
{
	/**
	 * Construtor da classe.
	 * @param conexao  objeto de conexão com o banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public FuncionalidadeQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metodo responsavel por criar um FuncionalidadeVo apartir de um ResultSet.
	 * @param rs (java.sql.ResultSet).
	 * @param funcionalidadeVo Funcionalidade (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.	
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void getFuncionalidade(final ResultSet rs, final FuncionalidadeVo funcionalidadeVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(funcionalidadeVo);
		funcionalidadeVo.setCodigo(rs.getLong(CAMPO_CODIGO_FUNCIONALIDADE));
		//Alterações necessárias para chamada do subModulo, considerando que o Modulo não tem funcionalidades
		ModuloAjudaVo moduloAjudaVo = new ModuloAjudaVo(rs.getLong(CAMPO_CODIGO_MODULO_AJUDA));
		funcionalidadeVo.setModuloAjudaVo(moduloAjudaVo.getSubModuloAjuda());
		funcionalidadeVo.setDescricaoFuncionalidade(rs.getString(CAMPO_DESCRICAO_FUNCIONALIDADE));
		funcionalidadeVo.setStatusFuncionalidade(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_FUNCIONALIDADE)));
	}

	/**
	 * Método utilizado para buscar dados de uma funcionalidade.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * a Funcionalidade de menor código cadastrado no banco de dados.
	 * A pesquisa parametrizada pode ser: ou pelo código, e/ou pela código do módulo ajuda, e/ou pela descrição da funcionalidade, e/ou pelo status.
	 * @param funcionalidadeVo Funcionalidade (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public FuncionalidadeVo findFuncionalidade(final FuncionalidadeVo funcionalidadeVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(funcionalidadeVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindFuncionalidade(funcionalidadeVo));
			prepareStatementFindFuncionalidade(ps, funcionalidadeVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getFuncionalidade(rs, funcionalidadeVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_FUNCIONALIDADE);
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
		return funcionalidadeVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento da consulta de uma Funcionalidade.
	 * @param ps (java.sql.PreparedStatement).
	 * @param funcionalidadeVo Funcionalidade (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementFindFuncionalidade(final PreparedStatement ps, final FuncionalidadeVo funcionalidadeVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(funcionalidadeVo);
		int contador = 0;
		if (funcionalidadeVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isNumericoValido(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getModuloAjudaVo().getSubModuloAjuda().getCodigo()))
			{
				ps.setLong(++contador, ((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getModuloAjudaVo().getSubModuloAjuda().getCodigo());
			}
			if (Validador.isStringValida(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getDescricaoFuncionalidade()))
			{
				ps.setString(++contador, ((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getDescricaoFuncionalidade());
			}
			if (Validador.isDominioNumericoValido(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getStatusFuncionalidade()))
			{
				ps.setInt(++contador, ((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getStatusFuncionalidade().getValorCorrente());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Funcionalidade.
	 * @param funcionalidadeVo Funcionalidade (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String getSQLFindFuncionalidade(final FuncionalidadeVo funcionalidadeVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(funcionalidadeVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT ");
		sql.append(" FUNCIONALIDADE." + CAMPO_CODIGO_FUNCIONALIDADE + " ");
		sql.append(" , FUNCIONALIDADE." + CAMPO_CODIGO_MODULO_AJUDA + " ");
		sql.append(" , FUNCIONALIDADE." + CAMPO_DESCRICAO_FUNCIONALIDADE + " ");
		sql.append(" , FUNCIONALIDADE." + CAMPO_STATUS_FUNCIONALIDADE + " ");
		sql.append(" FROM " + TABELA_FUNCIONALIDADE + " FUNCIONALIDADE ");
		sql.append(" WHERE 1 = 1 ");
		if (funcionalidadeVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND FUNCIONALIDADE." + CAMPO_CODIGO_FUNCIONALIDADE + " = ? ");
			}
			if (Validador.isNumericoValido(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getModuloAjudaVo().getSubModuloAjuda().getCodigo()))
			{
				sql.append("   AND FUNCIONALIDADE." + CAMPO_CODIGO_MODULO_AJUDA + " = ? ");
			}
			if (Validador.isStringValida(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getDescricaoFuncionalidade()))
			{
				sql.append("   AND FUNCIONALIDADE." + CAMPO_DESCRICAO_FUNCIONALIDADE + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getStatusFuncionalidade()))
			{
				sql.append("   AND FUNCIONALIDADE." + CAMPO_STATUS_FUNCIONALIDADE + " = ? ");
			}
		}
		sql.append(" ORDER BY FUNCIONALIDADE." + CAMPO_CODIGO_FUNCIONALIDADE + " ");
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de uma coleção de Funcionalidade.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todas as Funcionalidades cadastrados no sistema.
	 * A pesquisa parametrizada pode ser: ou pelo código, e/ou pelo código do módulo ajuda, e/ou pela descrição completa, e/ou pelo status.
	 * @param funcionalidadeVo Funcionalidade (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta consultar um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public FuncionalidadeVo listFuncionalidade(final FuncionalidadeVo funcionalidadeVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(funcionalidadeVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListFuncionalidade(funcionalidadeVo));
			prepareStatementListFuncionalidade(ps, funcionalidadeVo);
			for (rs = ps.executeQuery(); rs.next(); )
			{
				FuncionalidadeVo funcionalidadeVoAtual = new FuncionalidadeVo();
				getFuncionalidade(rs, funcionalidadeVoAtual);
				funcionalidadeVo.getCollVO().add(funcionalidadeVoAtual);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_FUNCIONALIDADE);
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
		return funcionalidadeVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento de listar Funcionalidade.
	 * @param ps (java.sql.PreparedStatement).
	 * @param funcionalidadeVo Funcionalidade (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementListFuncionalidade(final PreparedStatement ps, final FuncionalidadeVo funcionalidadeVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(funcionalidadeVo);
		int contador = 0;
		if (funcionalidadeVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isNumericoValido(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getModuloAjudaVo().getSubModuloAjuda().getCodigo()))
			{
				ps.setLong(++contador, ((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getModuloAjudaVo().getSubModuloAjuda().getCodigo());
			}
			if (Validador.isStringValida(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getDescricaoFuncionalidade()))
			{
				ps.setString(++contador, ((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getDescricaoFuncionalidade());
			}
			if (Validador.isDominioNumericoValido(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getStatusFuncionalidade()))
			{
				ps.setInt(++contador, ((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getStatusFuncionalidade().getValorCorrente());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Funcionalidade.
	 * @param funcionalidadeVo Funcionalidade (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String getSQLListFuncionalidade(final FuncionalidadeVo funcionalidadeVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(funcionalidadeVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT ");
		sql.append(" FUNCIONALIDADE." + CAMPO_CODIGO_FUNCIONALIDADE + " ");
		sql.append(" , FUNCIONALIDADE." + CAMPO_CODIGO_MODULO_AJUDA + " ");
		sql.append(" , FUNCIONALIDADE." + CAMPO_DESCRICAO_FUNCIONALIDADE + " ");
		sql.append(" , FUNCIONALIDADE." + CAMPO_STATUS_FUNCIONALIDADE + " ");
		sql.append(" FROM " + TABELA_FUNCIONALIDADE + " FUNCIONALIDADE ");
		sql.append(" WHERE 1 = 1 ");
		if (funcionalidadeVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND FUNCIONALIDADE." + CAMPO_CODIGO_FUNCIONALIDADE + " = ? ");
			}
			if (Validador.isNumericoValido(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getModuloAjudaVo().getSubModuloAjuda().getCodigo())) //Alteração necessária para o funcionamento. Considerando que Apenas SubModulo tem Funcionalidades.
			{
				sql.append("   AND FUNCIONALIDADE." + CAMPO_CODIGO_MODULO_AJUDA + " = ? ");
			}
			if (Validador.isStringValida(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getDescricaoFuncionalidade()))
			{
				sql.append("   AND FUNCIONALIDADE." + CAMPO_DESCRICAO_FUNCIONALIDADE + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((FuncionalidadeVo) funcionalidadeVo.getParametroConsulta()).getStatusFuncionalidade()))
			{
				sql.append("   AND FUNCIONALIDADE." + CAMPO_STATUS_FUNCIONALIDADE + " = ? ");
			}
		}
		sql.append(" ORDER BY FUNCIONALIDADE." + CAMPO_CODIGO_FUNCIONALIDADE + " ");
		return sql.toString();
	}
}
