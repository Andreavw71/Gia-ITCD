/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CampoAjudaQDao.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: CampoAjudaQDao.java,v 1.1.1.1 2008/05/28 17:55:05 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.campoajuda;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposCampoAjuda;
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
public class CampoAjudaQDao extends AbstractDao implements TabelasITC, CamposCampoAjuda
{
	/**
	 * Construtor da classe.
	 * @param conexao  objeto de conexão com o banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public CampoAjudaQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metodo responsavel por criar um CampoAjudaVo apartir de um ResultSet.
	 * @param (java.sql.ResultSet).
	 * @param campoAjudaVo Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.	
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void getCampoAjuda(final ResultSet rs, final CampoAjudaVo campoAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(campoAjudaVo);
		campoAjudaVo.setCodigo(rs.getLong(CAMPO_CODIGO_CAMPO_AJUDA));
		campoAjudaVo.setDescricaoRotulo(rs.getString(CAMPO_DESCRICAO_ROTULO));
	}

	/**
	 * Método utilizado para buscar dados de um  Campo Ajuda.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * o Campo Ajuda de menor código cadastrado no banco de dados.
	 * A pesquisa parametrizada pode ser: ou pelo código do campo ajuda, e/ou pela descrição do rótulo.
	 * @param campoAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public CampoAjudaVo findCampoAjuda(final CampoAjudaVo campoAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(campoAjudaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindCampoAjuda(campoAjudaVo));
			prepareStatementFindCampoAjuda(ps, campoAjudaVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getCampoAjuda(rs, campoAjudaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_CAMPO_AJUDA);
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
		return campoAjudaVo;
	}

	/**	
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento da consulta de um Campo Ajuda.
	 * @param ps (PreparedStatement).
	 * @param campoAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementFindCampoAjuda(final PreparedStatement ps, final CampoAjudaVo campoAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(campoAjudaVo);
		int contador = 0;
		if (campoAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((CampoAjudaVo) campoAjudaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((CampoAjudaVo) campoAjudaVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isStringValida(((CampoAjudaVo) campoAjudaVo.getParametroConsulta()).getDescricaoRotulo()))
			{
				ps.setString(++contador, ((CampoAjudaVo) campoAjudaVo.getParametroConsulta()).getDescricaoRotulo());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta do Campo Ajuda.
	 * @param campoAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String getSQLFindCampoAjuda(final CampoAjudaVo campoAjudaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(campoAjudaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT ");
		sql.append(" CAMPOAJUDA." + CAMPO_CODIGO_CAMPO_AJUDA + " ");
		sql.append(" , CAMPOAJUDA." + CAMPO_DESCRICAO_ROTULO + " ");
		sql.append(" FROM " + TABELA_CAMPO_AJUDA + " CAMPOAJUDA ");
		sql.append(" WHERE 1 = 1 ");
		if (campoAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((CampoAjudaVo) campoAjudaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND CAMPOAJUDA." + CAMPO_CODIGO_CAMPO_AJUDA + " = ? ");
			}
			if (Validador.isStringValida(((CampoAjudaVo) campoAjudaVo.getParametroConsulta()).getDescricaoRotulo()))
			{
				sql.append("   AND CAMPOAJUDA." + CAMPO_DESCRICAO_ROTULO + " = ? ");
			}
		}
		sql.append(" ");
		sql.append(" ORDER BY CAMPOAJUDA." + CAMPO_CODIGO_CAMPO_AJUDA + " ");
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de uma coleção de Campo  Ajuda.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todos os Campo Ajuda cadastrados no sistema.
	 * A pesquisa parametrizada pode ser: e/ou pelo código do campo ajuda, e/ou pela descrição completa.
	 * @param campoAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta consultar um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public CampoAjudaVo listCampoAjuda(final CampoAjudaVo campoAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(campoAjudaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListCampoAjuda(campoAjudaVo));
			prepareStatementListCampoAjuda(ps, campoAjudaVo);
			for (rs = ps.executeQuery(); rs.next(); )
			{
				CampoAjudaVo campoAjudaVoAtual = new CampoAjudaVo();
				getCampoAjuda(rs, campoAjudaVoAtual);
				campoAjudaVo.getCollVO().add(campoAjudaVoAtual);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_CAMPO_AJUDA);
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
		return campoAjudaVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento de listar Campo Ajuda.
	 * @param ps (PreparedStatement).
	 * @param campoAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementListCampoAjuda(final PreparedStatement ps, final CampoAjudaVo campoAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(campoAjudaVo);
		int contador = 0;
		if (campoAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((CampoAjudaVo) campoAjudaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, (((CampoAjudaVo) campoAjudaVo.getParametroConsulta()).getCodigo()));
			}
			if (Validador.isStringValida(((CampoAjudaVo) campoAjudaVo.getParametroConsulta()).getDescricaoRotulo()))
			{
				ps.setString(++contador, (((CampoAjudaVo) campoAjudaVo.getParametroConsulta()).getDescricaoRotulo()));
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Campo Ajuda.
	 * @param campoAjudaVo
	 * @throws ObjetoObrigatorioException
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String getSQLListCampoAjuda(final CampoAjudaVo campoAjudaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(campoAjudaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT ");
		sql.append(" CAMPOAJUDA." + CAMPO_CODIGO_CAMPO_AJUDA + " ");
		sql.append(" , CAMPOAJUDA." + CAMPO_DESCRICAO_ROTULO + " ");
		sql.append(" FROM " + TABELA_CAMPO_AJUDA + " CAMPOAJUDA ");
		sql.append(" WHERE 1 =1 ");
		if (campoAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((CampoAjudaVo) campoAjudaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND CAMPOAJUDA." + CAMPO_CODIGO_CAMPO_AJUDA + " = ? ");
			}
			if (Validador.isStringValida(((CampoAjudaVo) campoAjudaVo.getParametroConsulta()).getDescricaoRotulo()))
			{
				sql.append("   AND CAMPOAJUDA." + CAMPO_DESCRICAO_ROTULO + " = ? ");
			}
		}
		sql.append(" ORDER BY CAMPOAJUDA." + CAMPO_CODIGO_CAMPO_AJUDA + " ");
		return sql.toString();
	}
}
