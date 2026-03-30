/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BenfeitoriaQDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 16/10/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBenfeitoria;
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
public class BenfeitoriaQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposBenfeitoria
{

	/**
	 * Construtor da classe.
	 * @param conexao - Conexão com o banco de dados que será usada durante a manutenção dos dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public BenfeitoriaQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metodo responsavel por criar um BenfeitoriaVo apartir de um ResultSet
	 * @param (java.sql.ResultSet).
	 * @param benfeitoriaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void getBenfeitoria(final ResultSet rs, final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(benfeitoriaVo);
		benfeitoriaVo.setCodigo(rs.getLong(CAMPO_CODIGO_BENFEITORIA));
		benfeitoriaVo.setDataAtualizacao(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
		benfeitoriaVo.setDescricaoBenfeitoria(rs.getString(CAMPO_DESCRICAO_BENFEITORIA));
		benfeitoriaVo.setStatusBenfeitoria(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_BENFEITORIA)));
	}

	/**
	 * Método utilizado para buscar dados de uma determinada Benfeitoria.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * a Benfeitoria de menor código cadastrado no banco de dados. 
	 * @param benfeitoriaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return BenfeitoriaVo com os dados encontrados.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public BenfeitoriaVo findBenfeitoria(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(benfeitoriaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindBenfeitoria(benfeitoriaVo));
			prepareStatementFindBenfeitoria(ps, benfeitoriaVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getBenfeitoria(rs, benfeitoriaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_BENFEITORIA);
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
		return benfeitoriaVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento da consulta de uma Benfeitoria
	 * @param ps PreparedStatement que será alimentado para a consulta.
	 * @param benfeitoriaVo BenfeitoriaVo que contém os parametros de consulta.
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindBenfeitoria(final PreparedStatement ps, final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(benfeitoriaVo);
		int contador = 0;
		if (benfeitoriaVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getCodigo());
			}
			// DESCRICAO
			if (Validador.isStringValida(((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getDescricaoBenfeitoria()))
			{
				ps.setString(++contador, ((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getDescricaoBenfeitoria());
			}
			// STATUS BENFEITORIA
			if (Validador.isDominioNumericoValido(((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getStatusBenfeitoria()))
			{
				ps.setInt(++contador, ((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getStatusBenfeitoria().getValorCorrente());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Benfeitoria.
	 * @param benfeitoriaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @return String SQL Gerada.
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindBenfeitoria(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(benfeitoriaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT BENFEITORIA." + CAMPO_CODIGO_BENFEITORIA + " ");
		sql.append(" , BENFEITORIA." + CAMPO_DATA_ATUALIZACAO_BD + " ");
		sql.append(" , BENFEITORIA." + CAMPO_DESCRICAO_BENFEITORIA + " ");
		sql.append(" , BENFEITORIA." + CAMPO_STATUS_BENFEITORIA + " ");
		sql.append("  FROM " + TABELA_BENFEITORIA + " BENFEITORIA ");
		sql.append("  WHERE 1 = 1 ");
		if (benfeitoriaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("  AND BENFEITORIA." + CAMPO_CODIGO_BENFEITORIA + " = ? ");
			}
			if (Validador.isStringValida(((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getDescricaoBenfeitoria()))
			{
				sql.append(" AND UPPER(BENFEITORIA." + CAMPO_DESCRICAO_BENFEITORIA + ") = (UPPER(?)) ");
			}
			if (Validador.isDominioNumericoValido(((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getStatusBenfeitoria()))
			{
				sql.append("  AND BENFEITORIA." + CAMPO_STATUS_BENFEITORIA + " = ? ");
			}
		}
		sql.append(" ORDER BY BENFEITORIA." + CAMPO_CODIGO_BENFEITORIA + " ");
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de uma coleção de Benfeitorias.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todos as Benfeitorias cadastrados no sistema.
	 * @param benfeitoriaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return BenfeitoriaVo com os dados encontrados.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public BenfeitoriaVo listBenfeitoria(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(benfeitoriaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListarBenfeitoria(benfeitoriaVo));
			prepareStatementListBenfeitoria(ps, benfeitoriaVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				BenfeitoriaVo benfeitoriaAtualVo = new BenfeitoriaVo();
				getBenfeitoria(rs, benfeitoriaAtualVo);
				benfeitoriaVo.getCollVO().add(benfeitoriaAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_BENFEITORIA);
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
		return benfeitoriaVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos para listar Benfeitorias
	 * @param ps (java.sql.PreparedStatement).
	 * @param benfeitoriaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementListBenfeitoria(final PreparedStatement ps, final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(benfeitoriaVo);
		int contador = 0;
		if (benfeitoriaVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getCodigo());
			}
			// STATUS BENFEITORIA
			if (Validador.isDominioNumericoValido(((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getStatusBenfeitoria()))
			{
				ps.setInt(++contador, ((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getStatusBenfeitoria().getValorCorrente());
			}
		}
	}

	/**
	 * Cria a SQL de listagem de uma ou mais Benfeitorias.
	 * @param benfeitoriaVo (Value Object).
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListarBenfeitoria(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(benfeitoriaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  BENFEITORIA." + CAMPO_CODIGO_BENFEITORIA + " ");
		sql.append(" , BENFEITORIA." + CAMPO_DATA_ATUALIZACAO_BD + " ");
		sql.append(" , BENFEITORIA." + CAMPO_DESCRICAO_BENFEITORIA + " ");
		sql.append(" , BENFEITORIA." + CAMPO_STATUS_BENFEITORIA + " ");
		sql.append(" FROM " + TABELA_BENFEITORIA + " BENFEITORIA ");
		sql.append(" WHERE 1 = 1 ");
		if (benfeitoriaVo != null && benfeitoriaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND BENFEITORIA." + CAMPO_CODIGO_BENFEITORIA + " = ? ");
			}
			if (Validador.isStringValida(((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getDescricaoBenfeitoria()))
			{
				sql.append(" AND UPPER(BENFEITORIA." + CAMPO_DESCRICAO_BENFEITORIA + ") LIKE (UPPER('%" + 
								  ((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getDescricaoBenfeitoria() + "%')) ");
			}
			if (Validador.isDominioNumericoValido(((BenfeitoriaVo) benfeitoriaVo.getParametroConsulta()).getStatusBenfeitoria()))
			{
				sql.append("   AND BENFEITORIA." + CAMPO_STATUS_BENFEITORIA + " = ? ");
			}
		}
		sql.append(" ORDER BY BENFEITORIA." + CAMPO_DESCRICAO_BENFEITORIA + " ");
		return sql.toString();
	}
}
