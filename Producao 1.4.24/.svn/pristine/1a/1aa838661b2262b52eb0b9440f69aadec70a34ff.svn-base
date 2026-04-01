/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ModuloAjudaQDao.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: ModuloAjudaQDao.java,v 1.1.1.1 2008/05/28 17:55:06 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.moduloajuda;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposModuloAjuda;
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
public class ModuloAjudaQDao extends AbstractDao implements TabelasITC, CamposModuloAjuda
{
	/**
	 * Construtor da classe.
	 * @param conexao  objeto de conexão com o banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ModuloAjudaQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metodo responsavel por criar um ModuloAjudaVo apartir de um ResultSet.
	 * @param rs (java.sql.ResultSet).
	 * @param moduloAjudaVo Módulo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.	
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void getModuloAjuda(final ResultSet rs, final ModuloAjudaVo moduloAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(moduloAjudaVo);
		moduloAjudaVo.setCodigo(rs.getLong(CAMPO_CODIGO_MODULO_AJUDA));
		moduloAjudaVo.setSubModuloAjuda(new ModuloAjudaVo(rs.getLong(CAMPO_CODIGO_SUBMODULO_AJUDA)));
		moduloAjudaVo.setDescricaoModuloAjuda(rs.getString(CAMPO_DESCRICAO_MODULO_AJUDA));
		moduloAjudaVo.setStatusModuloAjuda(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_MODULO_AJUDA)));
		moduloAjudaVo.setCodigoOrdenacao(rs.getLong(CAMPO_CODIGO_ORDENACAO));
	}

	/**
	 * Metodo responsavel por criar um ModuloAjudaVo apartir de um ResultSet.
	 * @param rs (java.sql.ResultSet).
	 * @param subModuloAjudaVo Sub-Módulo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.	
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void getSubModuloAjuda(final ResultSet rs, final ModuloAjudaVo subModuloAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(subModuloAjudaVo);
		subModuloAjudaVo.setCodigo(rs.getLong(CAMPO_CODIGO_MODULO_AJUDA));
		subModuloAjudaVo.setSubModuloAjuda(new ModuloAjudaVo(rs.getLong(CAMPO_CODIGO_SUBMODULO_AJUDA)));
		subModuloAjudaVo.setDescricaoModuloAjuda(rs.getString(CAMPO_DESCRICAO_MODULO_AJUDA));
		subModuloAjudaVo.setStatusModuloAjuda(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_MODULO_AJUDA)));
		subModuloAjudaVo.setCodigoOrdenacao(rs.getLong(CAMPO_CODIGO_ORDENACAO));
	}

	/**
	 * Método utilizado para buscar dados de um  Módulo Ajuda.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * o Módulo Ajuda de menor código cadastrado no banco de dados.
	 * A pesquisa parametrizada pode ser: e/ou pelo código do módulo ajuda, e/ou pelo código do submódulo ajuda, e/ou pela descrição, e/ou pelo status, e/ou pelo código ordenação.
	 * @param moduloAjudaVo Módulo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ModuloAjudaVo findModuloAjuda(final ModuloAjudaVo moduloAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(moduloAjudaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindModuloAjuda(moduloAjudaVo));
			prepareStatementFindModuloAjuda(ps, moduloAjudaVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getModuloAjuda(rs, moduloAjudaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_MODULO_AJUDA);
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
		return moduloAjudaVo;
	}

	/**	
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento da consulta de um Módulo Ajuda.
	 * @param ps (java.sql.PreparedStatement).
	 * @param moduloAjudaVo Módulo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementFindModuloAjuda(final PreparedStatement ps, final ModuloAjudaVo moduloAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(moduloAjudaVo);
		int contador = 0;
		if (moduloAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getSubModuloAjuda().getCodigo()))
			{
				ps.setLong(++contador, ((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getSubModuloAjuda().getCodigo());
			}
			if (Validador.isStringValida(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getDescricaoModuloAjuda()))
			{
				ps.setString(++contador, ((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getDescricaoModuloAjuda());
			}
			if (Validador.isDominioNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getStatusModuloAjuda()))
			{
				ps.setInt(++contador, ((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getStatusModuloAjuda().getValorCorrente());
			}
			if (Validador.isNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getCodigoOrdenacao()))
			{
				ps.setLong(++contador, ((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getCodigoOrdenacao());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta do Módulo Ajuda.
	 * @param moduloAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String getSQLFindModuloAjuda(final ModuloAjudaVo moduloAjudaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(moduloAjudaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT ");
		sql.append(" MODULOAJUDA ." + CAMPO_CODIGO_MODULO_AJUDA + " ");
		sql.append(" , MODULOAJUDA ." + CAMPO_CODIGO_SUBMODULO_AJUDA + " ");
		sql.append(" , MODULOAJUDA ." + CAMPO_DESCRICAO_MODULO_AJUDA + " ");
		sql.append(" , MODULOAJUDA ." + CAMPO_STATUS_MODULO_AJUDA + " ");
		sql.append(" , MODULOAJUDA ." + CAMPO_CODIGO_ORDENACAO + " ");
		sql.append(" FROM " + TABELA_MODULO_AJUDA + " MODULOAJUDA ");
		sql.append(" WHERE 1 = 1 ");
		if (moduloAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_CODIGO_MODULO_AJUDA + " = ? ");
			}
			if (Validador.isNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getSubModuloAjuda().getCodigo()))
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_CODIGO_SUBMODULO_AJUDA + " = ? ");
			}
			else
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_CODIGO_SUBMODULO_AJUDA + " is null ");
			}
			if (Validador.isStringValida(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getDescricaoModuloAjuda()))
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_DESCRICAO_MODULO_AJUDA + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getStatusModuloAjuda()))
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_STATUS_MODULO_AJUDA + " = ? ");
			}
			if (Validador.isNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getCodigoOrdenacao()))
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_CODIGO_ORDENACAO + " = ? ");
			}
		}
		sql.append(" ORDER BY MODULOAJUDA ." + CAMPO_CODIGO_MODULO_AJUDA + " ");
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de uma coleção de Módulo Ajuda.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todos os Módulo Ajuda cadastrados no sistema.
	 * A pesquisa parametrizada pode ser: e/ou pelo código do módulo, e/ou pelo código do submódulo, e/ou pela descrição completa, e/ou pelo status, e/ou pelo código ordenação.
	 * @param moduloAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta consultar um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ModuloAjudaVo listModuloAjuda(final ModuloAjudaVo moduloAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(moduloAjudaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListModuloAjuda(moduloAjudaVo));
			prepareStatementListModuloAjuda(ps, moduloAjudaVo);
			for (rs = ps.executeQuery(); rs.next(); )
			{
				ModuloAjudaVo moduloAjudaVoAtual = new ModuloAjudaVo();
				getModuloAjuda(rs, moduloAjudaVoAtual);
				moduloAjudaVo.getCollVO().add(moduloAjudaVoAtual);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_MODULO_AJUDA);
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
		return moduloAjudaVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento de listar Módulo Ajuda.
	 * @param ps (PreparedStatement).
	 * @param moduloAjudaVo Módulo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementListModuloAjuda(final PreparedStatement ps, final ModuloAjudaVo moduloAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(moduloAjudaVo);
		int contador = 0;
		if (moduloAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getSubModuloAjuda().getCodigo()))
			{
				ps.setLong(++contador, ((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getSubModuloAjuda().getCodigo());
			}
			if (Validador.isStringValida(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getDescricaoModuloAjuda()))
			{
				ps.setString(++contador, ((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getDescricaoModuloAjuda());
			}
			if (Validador.isDominioNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getStatusModuloAjuda()))
			{
				ps.setInt(++contador, ((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getStatusModuloAjuda().getValorCorrente());
			}
			if (Validador.isNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getCodigoOrdenacao()))
			{
				ps.setLong(++contador, ((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getCodigoOrdenacao());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta do Módulo Ajuda.
	 * @param moduloAjudaVo Módulo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String getSQLListModuloAjuda(final ModuloAjudaVo moduloAjudaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(moduloAjudaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT ");
		sql.append("  MODULOAJUDA ." + CAMPO_CODIGO_MODULO_AJUDA + " ");
		sql.append(" , MODULOAJUDA ." + CAMPO_CODIGO_SUBMODULO_AJUDA + " ");
		sql.append(" , MODULOAJUDA ." + CAMPO_DESCRICAO_MODULO_AJUDA + " ");
		sql.append(" , MODULOAJUDA ." + CAMPO_STATUS_MODULO_AJUDA + " ");
		sql.append(" , MODULOAJUDA ." + CAMPO_CODIGO_ORDENACAO + " ");
		sql.append(" FROM " + TABELA_MODULO_AJUDA + " MODULOAJUDA ");
		sql.append(" WHERE 1 = 1 ");
		if (moduloAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_CODIGO_MODULO_AJUDA + " = ? ");
			}
			sql.append("   AND MODULOAJUDA ." + CAMPO_CODIGO_SUBMODULO_AJUDA + " IS NULL ");
			if (Validador.isStringValida(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getDescricaoModuloAjuda()))
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_DESCRICAO_MODULO_AJUDA + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getStatusModuloAjuda()))
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_STATUS_MODULO_AJUDA + " = ? ");
			}
			if (Validador.isNumericoValido(((ModuloAjudaVo) moduloAjudaVo.getParametroConsulta()).getCodigoOrdenacao()))
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_CODIGO_ORDENACAO + " = ? ");
			}
		}
		sql.append(" ORDER BY MODULOAJUDA ." + CAMPO_CODIGO_MODULO_AJUDA + " ");
		return sql.toString();
	}

	/**
	 * Método responsável por listar os submódulos da ajuda.
	 * @param subModuloAjudaVo Sub-Módulo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	public ModuloAjudaVo listSubModuloAjuda(final ModuloAjudaVo subModuloAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(subModuloAjudaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListSubModuloAjuda(subModuloAjudaVo));
			prepareStatementListSubModuloAjuda(ps, subModuloAjudaVo);
			for (rs = ps.executeQuery(); rs.next(); )
			{
				ModuloAjudaVo subModuloAjudaVoAtual = new ModuloAjudaVo();
				getSubModuloAjuda(rs, subModuloAjudaVoAtual);
				subModuloAjudaVo.getCollVO().add(subModuloAjudaVoAtual);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_MODULO_AJUDA);
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
		return subModuloAjudaVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento de listar SUBMódulo Ajuda.
	 * @param ps (PreparedStatement).
	 * @param subModuloAjudaVo Sub-Módulo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementListSubModuloAjuda(final PreparedStatement ps, final ModuloAjudaVo subModuloAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(subModuloAjudaVo);
		int contador = 0;
		if (subModuloAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((ModuloAjudaVo) subModuloAjudaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((ModuloAjudaVo) subModuloAjudaVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isStringValida(((ModuloAjudaVo) subModuloAjudaVo.getParametroConsulta()).getDescricaoModuloAjuda()))
			{
				ps.setString(++contador, ((ModuloAjudaVo) subModuloAjudaVo.getParametroConsulta()).getDescricaoModuloAjuda());
			}
			if (Validador.isDominioNumericoValido(((ModuloAjudaVo) subModuloAjudaVo.getParametroConsulta()).getStatusModuloAjuda()))
			{
				ps.setInt(++contador, ((ModuloAjudaVo) subModuloAjudaVo.getParametroConsulta()).getStatusModuloAjuda().getValorCorrente());
			}
			if (Validador.isNumericoValido(((ModuloAjudaVo) subModuloAjudaVo.getParametroConsulta()).getCodigoOrdenacao()))
			{
				ps.setLong(++contador, ((ModuloAjudaVo) subModuloAjudaVo.getParametroConsulta()).getCodigoOrdenacao());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta do SUBMódulo Ajuda.
	 * @param subModuloAjudaVo Sub-Módulo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String getSQLListSubModuloAjuda(final ModuloAjudaVo subModuloAjudaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(subModuloAjudaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT ");
		sql.append("   MODULOAJUDA ." + CAMPO_CODIGO_MODULO_AJUDA + " ");
		sql.append(" , MODULOAJUDA ." + CAMPO_CODIGO_SUBMODULO_AJUDA + " ");
		sql.append(" , MODULOAJUDA ." + CAMPO_DESCRICAO_MODULO_AJUDA + " ");
		sql.append(" , MODULOAJUDA ." + CAMPO_STATUS_MODULO_AJUDA + " ");
		sql.append(" , MODULOAJUDA ." + CAMPO_CODIGO_ORDENACAO + " ");
		sql.append(" FROM " + TABELA_MODULO_AJUDA + " MODULOAJUDA ");
		sql.append(" WHERE 1 = 1 ");
		if (subModuloAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((ModuloAjudaVo) subModuloAjudaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_CODIGO_SUBMODULO_AJUDA + " = ? ");
			}
			if (Validador.isStringValida(((ModuloAjudaVo) subModuloAjudaVo.getParametroConsulta()).getDescricaoModuloAjuda()))
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_DESCRICAO_MODULO_AJUDA + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((ModuloAjudaVo) subModuloAjudaVo.getParametroConsulta()).getStatusModuloAjuda()))
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_STATUS_MODULO_AJUDA + " = ? ");
			}
			if (Validador.isNumericoValido(((ModuloAjudaVo) subModuloAjudaVo.getParametroConsulta()).getCodigoOrdenacao()))
			{
				sql.append("   AND MODULOAJUDA ." + CAMPO_CODIGO_ORDENACAO + " = ? ");
			}
		}
		sql.append(" ORDER BY MODULOAJUDA ." + CAMPO_CODIGO_SUBMODULO_AJUDA + " ");
		return sql.toString();
	}
}
