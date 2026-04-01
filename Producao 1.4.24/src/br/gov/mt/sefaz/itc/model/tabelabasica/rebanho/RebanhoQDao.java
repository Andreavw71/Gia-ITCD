/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: RebanhoQDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 03/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.rebanho;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposRebanho;
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
public class RebanhoQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposRebanho
{

	/**
	 * Construtor da classe.
	 * @param conexao - Conexão com o banco de dados que será usada durante a manutenção dos dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public RebanhoQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metodo responsavel por criar um RebanhoVo apartir de um ResultSet.
	 * @param rs ResultSet que contém os dados de retorno da consulta.
	 * @param rebanhoVo RebanhoVo que será alimentada com os dados.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void getRebanho(final ResultSet rs, final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(rebanhoVo);
		rebanhoVo.setCodigo(rs.getLong(CAMPO_CODIGO_REBANHO));
		rebanhoVo.setDataAtualizacao(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
		rebanhoVo.setDescricaoRebanho(rs.getString(CAMPO_DESCRICAO_REBANHO));
		rebanhoVo.setStatusRebanho(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_REBANHO)));
	}

	/**
	 * Método utilizado para buscar dados de uma determinada Rebanho.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * o Rebanho de menor código cadastrado no banco de dados.
	 * A pesquisa parametrizada pode ser: ou pelo código, e/ou pela descrição completa, e/ou pelo status da Rebanho.
	 * @param rebanhoVo RebanhoVo que contém os parametros de consulta.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return RebanhoVo com os dados encontrados.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public RebanhoVo findRebanho(final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, ConsultaException
	{
		Validador.validaObjeto(rebanhoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindRebanho(rebanhoVo));
			prepareStatementFindRebanho(ps, rebanhoVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getRebanho(rs, rebanhoVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_REBANHO);
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
		return rebanhoVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento da consulta de uma Rebanho.
	 * @param ps PreparedStatement que será alimentado para a consulta.
	 * @param rebanhoVo RebanhoVo que contém os parametros de consulta.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindRebanho(final PreparedStatement ps, final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(rebanhoVo);
		int contador = 0;
		if (rebanhoVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((RebanhoVo) rebanhoVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((RebanhoVo) rebanhoVo.getParametroConsulta()).getCodigo());
			}
			// DESCRICAO
			if (Validador.isStringValida(((RebanhoVo) rebanhoVo.getParametroConsulta()).getDescricaoRebanho()))
			{
				ps.setString(++contador, ((RebanhoVo) rebanhoVo.getParametroConsulta()).getDescricaoRebanho());
			}
			// STATUS REBANHO
			if (Validador.isDominioNumericoValido(((RebanhoVo) rebanhoVo.getParametroConsulta()).getStatusRebanho()))
			{
				ps.setInt(++contador, ((RebanhoVo) rebanhoVo.getParametroConsulta()).getStatusRebanho().getValorCorrente());
			}
		}
	}

	/**
	 * Cria SQL de Consulta da Rebanho.
	 * @param rebanhoVo RebanhoVo que contém as informações para a geração do SQL de consulta.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @return String SQL Gerada.
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindRebanho(final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, ClassCastException
	{
		Validador.validaObjeto(rebanhoVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT REBANHO." + CAMPO_CODIGO_REBANHO + " ");
		sql.append(" , REBANHO." + CAMPO_DATA_ATUALIZACAO_BD + " ");
		sql.append(" , REBANHO." + CAMPO_DESCRICAO_REBANHO + " ");
		sql.append(" , REBANHO." + CAMPO_STATUS_REBANHO + " ");
		sql.append("  FROM " + TABELA_REBANHO + " REBANHO ");
		sql.append("  WHERE 1 = 1 ");
		if (rebanhoVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((RebanhoVo) rebanhoVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("  AND REBANHO." + CAMPO_CODIGO_REBANHO + " = ? ");
			}
			if (Validador.isStringValida(((RebanhoVo) rebanhoVo.getParametroConsulta()).getDescricaoRebanho()))
			{
				sql.append(" AND UPPER(REBANHO." + CAMPO_DESCRICAO_REBANHO + ") = (UPPER(?)) ");
			}
			if (Validador.isDominioNumericoValido(((RebanhoVo) rebanhoVo.getParametroConsulta()).getStatusRebanho()))
			{
				sql.append("  AND REBANHO." + CAMPO_STATUS_REBANHO + " = ? ");
			}
		}
		sql.append(" ORDER BY REBANHO." + CAMPO_CODIGO_REBANHO + " ");
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de uma coleção de Rebanhos.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todos as Rebanhos cadastrados no sistema.
	 * A pesquisa parametrizada pode ser: ou pelo código, e/ou pela descrição completa, e/ou pelo status da Rebanho.
	 * @param rebanhoVo RebanhoVo que contém os parametros de consulta.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return RebanhoVo com os dados encontrados.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public RebanhoVo listRebanho(final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, ConsultaException
	{
		Validador.validaObjeto(rebanhoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListarRebanho(rebanhoVo));
			prepareStatementListRebanho(ps, rebanhoVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				RebanhoVo rebanhoAtualVo = new RebanhoVo();
				getRebanho(rs, rebanhoAtualVo);
				rebanhoVo.getCollVO().add(rebanhoAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_REBANHO);
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
		return rebanhoVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos para listar Rebanhos.
	 * @param ps PreparedStatement que será alimentado para a consulta.
	 * @param rebanhoVo RebanhoVo que contém os parametros de consulta.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.\\
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementListRebanho(final PreparedStatement ps, final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(rebanhoVo);
		int contador = 0;
		if (rebanhoVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((RebanhoVo) rebanhoVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((RebanhoVo) rebanhoVo.getParametroConsulta()).getCodigo());
			}
			// STATUS REBANHO
			if (Validador.isDominioNumericoValido(((RebanhoVo) rebanhoVo.getParametroConsulta()).getStatusRebanho()))
			{
				ps.setInt(++contador, ((RebanhoVo) rebanhoVo.getParametroConsulta()).getStatusRebanho().getValorCorrente());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Rebanho.
	 * @param rebanhoVo Rebanho (Value Object)
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListarRebanho(final RebanhoVo rebanhoVo)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  REBANHO." + CAMPO_CODIGO_REBANHO + " ");
		sql.append(" , REBANHO." + CAMPO_DATA_ATUALIZACAO_BD + " ");
		sql.append(" , REBANHO." + CAMPO_DESCRICAO_REBANHO + " ");
		sql.append(" , REBANHO." + CAMPO_STATUS_REBANHO + " ");
		sql.append(" FROM " + TABELA_REBANHO + " REBANHO ");
		sql.append(" WHERE 1 = 1 ");
		if (rebanhoVo != null && rebanhoVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((RebanhoVo) rebanhoVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND REBANHO." + CAMPO_CODIGO_REBANHO + " = ? ");
			}
			if (Validador.isStringValida(((RebanhoVo) rebanhoVo.getParametroConsulta()).getDescricaoRebanho()))
			{
				sql.append(" AND UPPER(REBANHO." + CAMPO_DESCRICAO_REBANHO + ") LIKE (UPPER('%" + 
								  ((RebanhoVo) rebanhoVo.getParametroConsulta()).getDescricaoRebanho() + "%')) ");
			}
			if (Validador.isDominioNumericoValido(((RebanhoVo) rebanhoVo.getParametroConsulta()).getStatusRebanho()))
			{
				sql.append("   AND REBANHO." + CAMPO_STATUS_REBANHO + " = ? ");
			}
		}
		sql.append(" ORDER BY REBANHO." + CAMPO_DESCRICAO_REBANHO + " ");
		return sql.toString();
	}
}
