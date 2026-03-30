/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: TelaCampoAjudaQDao.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: TelaCampoAjudaQDao.java,v 1.2 2008/06/27 14:43:09 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.campoajuda.CampoAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telaajuda.TelaAjudaVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposCampoAjuda;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposTelaCampoAjuda;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe de acesso a dados (Data Access Object).
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.2 $
 */
public class TelaCampoAjudaQDao extends AbstractDao implements TabelasITC, CamposTelaCampoAjuda
{
	/**
	 * Construtor da classe.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaCampoAjudaQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metodo responsavel por criar um TelaCampoAjudaVo apartir de um ResultSet.
	 * @param rs (java.sql.ResultSet).
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.	
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void getTelaCampoAjuda(final ResultSet rs, final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(telaCampoAjudaVo);
		CampoAjudaVo campoAjudaVo = new CampoAjudaVo(rs.getLong(CAMPO_CODIGO_CAMPO_AJUDA));
		campoAjudaVo.setDescricaoRotulo(rs.getString(CamposCampoAjuda.CAMPO_DESCRICAO_ROTULO));
		TelaAjudaVo telaAjudaVo = new TelaAjudaVo();
		telaAjudaVo.setCodigo(rs.getLong(CAMPO_CODIGO_TELA_AJUDA));
		telaCampoAjudaVo.setTelaAjudaVo(telaAjudaVo);
		telaCampoAjudaVo.setCampoAjudaVo(campoAjudaVo);
		telaCampoAjudaVo.setCodigo(rs.getLong(CAMPO_CODIGO_CAMPO_AJUDA));
		telaCampoAjudaVo.setCodigoOrdenacao(rs.getInt(CAMPO_CODIGO_ORDENACAO));
		telaCampoAjudaVo.setDescricaoAjudaCampo(rs.getString(CAMPO_DESCRICAO_AJUDA_CAMPO));
		telaCampoAjudaVo.setStatusTelaCampo(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_TELA_CAMPO_AJUDA)));
	}

	/**
	 * Método utilizado para buscar dados de uma Tela Campo Ajuda.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * a Tela Campo Ajuda de menor código cadastrado no banco de dados.
	 * A pesquisa parametrizada pode ser: ou pelo código do campo ajuda, e/ou pelo código da tela ajuda.
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaCampoAjudaVo findTelaCampoAjuda(final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(telaCampoAjudaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindTelaCampoAjuda(telaCampoAjudaVo));
			prepareStatementFindTelaCampoAjuda(ps, telaCampoAjudaVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getTelaCampoAjuda(rs, telaCampoAjudaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_TELA_CAMPO_AJUDA);
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
		return telaCampoAjudaVo;
	}

	/**	
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento da consulta de uma Tela Campo Ajuda.
	 * @param ps (java.sql.PreparedStatement).
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementFindTelaCampoAjuda(final PreparedStatement ps, final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(telaCampoAjudaVo);
		int contador = 0;
		if (telaCampoAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getCampoAjudaVo().getCodigo()))
			{
				ps.setLong(++contador, ((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getCampoAjudaVo().getCodigo());
			}
			if (Validador.isNumericoValido(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getTelaAjudaVo().getCodigo()))
			{
				ps.setLong(++contador, ((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getTelaAjudaVo().getCodigo());
			}
			//adicionei no  dia 14/12
			if (Validador.isNumericoValido(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isStringValida(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getDescricaoAjudaCampo()))
			{
				ps.setString(++contador, ((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getDescricaoAjudaCampo());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Tela Campo Ajuda.
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String getSQLFindTelaCampoAjuda(final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(telaCampoAjudaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT ");
		sql.append(" TELACAMPOAJUDA." + CAMPO_CODIGO_CAMPO_AJUDA + " ");
		sql.append(" , TELACAMPOAJUDA." + CAMPO_CODIGO_TELA_AJUDA + " ");
		sql.append(" , TELACAMPOAJUDA." + CAMPO_DESCRICAO_AJUDA_CAMPO + " ");
		sql.append(" , TELA_CAMPO_AJUDA." + CAMPO_STATUS_TELA_CAMPO_AJUDA + " ");
		sql.append(" FROM " + TABELA_TELA_AJUDA + " TELACAMPOAJUDA ");
		sql.append(" WHERE 1 = 1 ");
		if (telaCampoAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getCampoAjudaVo().getCodigo()))
			{
				sql.append("   AND TELACAMPOAJUDA." + CAMPO_CODIGO_CAMPO_AJUDA + " = ? ");
			}
			if (Validador.isNumericoValido(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getTelaAjudaVo().getCodigo()))
			{
				sql.append("   AND TELACAMPOAJUDA." + CAMPO_CODIGO_TELA_AJUDA + " = ? ");
			}
			if (Validador.isStringValida(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getDescricaoAjudaCampo()))
			{
				sql.append("   AND TELACAMPOAJUDA." + CAMPO_DESCRICAO_AJUDA_CAMPO + " = ? ");
			}
		}
		sql.append(" ORDER BY TELACAMPOAJUDA." + CAMPO_CODIGO_CAMPO_AJUDA + " ");
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de uma coleção de Tela Campo  Ajuda.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todas as Tela Campo Ajuda cadastrados no sistema.
	 * A pesquisa parametrizada pode ser: e/ou pelo código do campo ajuda, e/ou pelo código tela ajuda.
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta consultar um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaCampoAjudaVo listTelaCampoAjuda(final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(telaCampoAjudaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListTelaCampoAjuda(telaCampoAjudaVo));
			prepareStatementListTelaCampoAjuda(ps, telaCampoAjudaVo);
			for (rs = ps.executeQuery(); rs.next(); )
			{
				TelaCampoAjudaVo telaCampoAjudaVoAtual = new TelaCampoAjudaVo();
				getTelaCampoAjuda(rs, telaCampoAjudaVoAtual);
				telaCampoAjudaVo.getCollVO().add(telaCampoAjudaVoAtual);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_TELA_CAMPO_AJUDA);
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
		return telaCampoAjudaVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento de listar Tela Campo Ajuda.
	 * @param ps (java.sql.PreparedStatement).
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementListTelaCampoAjuda(final PreparedStatement ps, final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(telaCampoAjudaVo);
		int contador = 0;
		if (telaCampoAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getCampoAjudaVo().getCodigo()))
			{
				ps.setLong(++contador, ((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getCampoAjudaVo().getCodigo());
			}
			if (Validador.isNumericoValido(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getTelaAjudaVo().getCodigo()))
			{
				ps.setLong(++contador, ((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getTelaAjudaVo().getCodigo());
			}
			//adicionei 14/12
			if (Validador.isNumericoValido(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isStringValida(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getDescricaoAjudaCampo()))
			{
				ps.setString(++contador, ((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getDescricaoAjudaCampo());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Tela Campo Ajuda.
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String getSQLListTelaCampoAjuda(final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(telaCampoAjudaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT ");
		sql.append(" TELA_CAMPO_AJUDA." + CAMPO_CODIGO_CAMPO_AJUDA + " ");
		sql.append(" , TELA_CAMPO_AJUDA." + CAMPO_CODIGO_TELA_AJUDA + " ");
		sql.append(" , TELA_CAMPO_AJUDA." + CAMPO_DESCRICAO_AJUDA_CAMPO + " ");
		sql.append(" , TELA_CAMPO_AJUDA." + CAMPO_STATUS_TELA_CAMPO_AJUDA + " ");
		sql.append(" , TELA_CAMPO_AJUDA." + CAMPO_CODIGO_ORDENACAO + " ");
		if (telaCampoAjudaVo.isConsultaCompleta())
		{
			sql.append(" , CAMPO_AJUDA." + CamposCampoAjuda.CAMPO_DESCRICAO_ROTULO + " ");
		}
		sql.append(" FROM " + TABELA_TELA_CAMPO_AJUDA + " TELA_CAMPO_AJUDA ");
		if (telaCampoAjudaVo.isConsultaCompleta())
		{
			sql.append(" INNER JOIN " + TABELA_CAMPO_AJUDA + " CAMPO_AJUDA ");
			sql.append(" ON TELA_CAMPO_AJUDA." + CAMPO_CODIGO_CAMPO_AJUDA + " = " + "CAMPO_AJUDA." + 
							CamposCampoAjuda.CAMPO_CODIGO_CAMPO_AJUDA);
		}
		sql.append(" WHERE 1 = 1 ");
		if (telaCampoAjudaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getCampoAjudaVo().getCodigo()))
			{
				sql.append("   AND TELA_CAMPO_AJUDA." + CAMPO_CODIGO_CAMPO_AJUDA + " = ? ");
			}
			else
			{
				sql.append("   AND TELA_CAMPO_AJUDA." + CAMPO_CODIGO_CAMPO_AJUDA + " > 0 ");
			}
			if (Validador.isNumericoValido(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getTelaAjudaVo().getCodigo()))
			{
				sql.append("   AND TELA_CAMPO_AJUDA." + CAMPO_CODIGO_TELA_AJUDA + " = ? ");
			}
			else
			{
				sql.append("   AND TELA_CAMPO_AJUDA." + CAMPO_CODIGO_TELA_AJUDA + " > 0 ");
			}
			if (Validador.isStringValida(((TelaCampoAjudaVo) telaCampoAjudaVo.getParametroConsulta()).getDescricaoAjudaCampo()))
			{
				sql.append("   AND TELA_CAMPO_AJUDA." + CAMPO_DESCRICAO_AJUDA_CAMPO + " = ? ");
			}
		}
		sql.append(" ORDER BY TELA_CAMPO_AJUDA." + CAMPO_CODIGO_ORDENACAO + " ");
		return sql.toString();
	}
}
