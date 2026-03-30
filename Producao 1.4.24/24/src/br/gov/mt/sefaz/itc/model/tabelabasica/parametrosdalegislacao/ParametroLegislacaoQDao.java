package br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposParametroLegislacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;


/**
 * Classe para consultas da entidade Parametro Legislação
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class ParametroLegislacaoQDao extends AbstractDao implements TabelasITC, CamposParametroLegislacao
{
	/**
	 * Construtor padrão
	 * @param conexao Conexão com o Banco de Dados
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método para criar alimentar um ParametroLegislacaoVo com os dados do ResultSet
	 * @param rs ResultSet que contém os dados.
	 * @param parametroLegislacaoVo ParametroLegislacaoVo que será alimentado
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void getParametroLegislacao(final ResultSet rs, final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		try
		{
			Validador.validaObjeto(rs);
			Validador.validaObjeto(parametroLegislacaoVo);
			parametroLegislacaoVo.setCodigo(rs.getLong(CAMPO_CODIGO_PARAMETRO_LEGISLACAO));
			parametroLegislacaoVo.setNumeroLegislacao(rs.getInt(CAMPO_NUMERO_PARAMETRO_LEGISLACAO));
			parametroLegislacaoVo.setAnoLegislacao(rs.getInt(CAMPO_ANO_LEGISLACAO));
			parametroLegislacaoVo.setDataInicioVigencia(rs.getDate(CAMPO_DATA_INICIO_VIGENCIA));
			parametroLegislacaoVo.setDataFimVigencia(rs.getDate(CAMPO_DATA_FINAL_VIGENCIA));
			parametroLegislacaoVo.setStatusLegislacao(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_PARAMETRO_LEGISLACAO)));
			parametroLegislacaoVo.setDataAtualizacao(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
			parametroLegislacaoVo.getCalculoCascata().setValorCorrente(rs.getInt(CAMPO_CALCULO_CASCATA));
			parametroLegislacaoVo.setNumeroLegislacaoPrincipal(rs.getInt(CAMPO_NUMERO_LEGISLACAO_PRINCIPAL));
			parametroLegislacaoVo.setAnoLegislacaoPrincipal(rs.getInt(CAMPO_ANO_LEGISLACAO_PRINCIPAL));
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método utilizado para buscar dados de uma determinada Parametro Legislação.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * o Parametro Legislação de menor código cadastrado no banco de dados.
	 * <br>Esta pesquisa pode ser realizada pelos seguintes filtros:
	 * <br>- parametroLegislacaoVo.codigo
	 * @param parametroLegislacaoVo Com os dados a serem considerados na Consulta
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return ParametroLegislacaoVo - Encontrada
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoVo findParametroLegislacao(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListFindParametroLegislacao(parametroLegislacaoVo));
			prepareStatementListFindParametroLegislacao(ps, parametroLegislacaoVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getParametroLegislacao(rs, parametroLegislacaoVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_PARAMETRO_LEGISLACAO);
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
		return parametroLegislacaoVo;
	}

	/**
	 * Método utilizado para buscar dados de uma coleção de Parametro Legislação.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * todos os Parametro Legislação do banco de dados.
	 * <br>Esta pesquisa pode ser realizada pelos seguintes filtros:
	 * <br>- parametroLegislacaoVo.codigo
	 * @param parametroLegislacaoVo Com os dados a serem considerados na Consulta
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return ParametroLegislacaoVo - Encontrada
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoVo listParametroLegislacao(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListFindParametroLegislacao(parametroLegislacaoVo));
			prepareStatementListFindParametroLegislacao(ps, parametroLegislacaoVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				ParametroLegislacaoVo parametroLegislacaoAtualVo = new ParametroLegislacaoVo();
				getParametroLegislacao(rs, parametroLegislacaoAtualVo);
				parametroLegislacaoVo.getCollVO().add(parametroLegislacaoAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_PARAMETRO_LEGISLACAO);
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
		return parametroLegislacaoVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento da consulta de um Parametro Legislação
	 * @param ps PreparedStatement que será alimentado para a consulta.
	 * @param parametroLegislacaoVo ParametroLegislacaoVo que contém os parametros de consulta.
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementListFindParametroLegislacao(final PreparedStatement ps, final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(parametroLegislacaoVo);
		int contador = 0;
		try
		{
			if (parametroLegislacaoVo.isConsultaParametrizada())
			{
				// CODIGO
				if (Validador.isNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getCodigo()))
				{
					ps.setLong(++contador, ((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getCodigo());
				}
				// NUMERO PARAMETRO LEGISLACAO
				if (Validador.isNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getNumeroLegislacao()))
				{
					ps.setInt(++contador, ((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getNumeroLegislacao());
				}
				// ANO PARAMETRO LEGISLACAO
				if (Validador.isNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getAnoLegislacao()))
				{
					ps.setInt(++contador, ((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getAnoLegislacao());
				}
				// DATA INICIO VIGENCIA
				if (Validador.isDataValida(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getDataInicioVigencia()))
				{
					ps.setDate(++contador, new java.sql.Date(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getDataInicioVigencia().getTime()));
				}
				// DATA FIM VIGENCIA
				if (Validador.isDataValida(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getDataFimVigencia()))
				{
					ps.setDate(++contador, new java.sql.Date(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getDataFimVigencia().getTime()));
				}
				// STATUS PARAMETRO LEGISLACAO
				if (Validador.isDominioNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getStatusLegislacao()))
				{
					ps.setInt(++contador, ((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getStatusLegislacao().getValorCorrente());
				}
				// STATUS GIA
				if (Validador.isDominioNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getStatusGia()))
				{
					ps.setInt(++contador, ((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getStatusGia().getValorCorrente());
				}
				// CALCULO CASCATA
				if (Validador.isDominioNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getCalculoCascata()))
				{
					ps.setInt(++contador, ((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getCalculoCascata().getValorCorrente());
				}
				// NUMERO PARAMETRO LEGISLACAO PRINCIPAL
				if (Validador.isNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getNumeroLegislacaoPrincipal()))
				{
					ps.setInt(++contador, ((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getNumeroLegislacaoPrincipal());
				}
				// ANO PARAMETRO LEGISLACAO PRINCIPAL
				if (Validador.isNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getAnoLegislacaoPrincipal()))
				{
					ps.setInt(++contador, ((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getAnoLegislacaoPrincipal());
				}
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método para gerar a SQL de consulta de um ou mais Parametro Legislação
	 * @param parametroLegislacaoVo Parametro Legislação que será utilizado para a consulta.
	 * @throws ObjetoObrigatorioException
	 * @return String - SQL Gerada
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListFindParametroLegislacao(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT PL.").append(CAMPO_CODIGO_PARAMETRO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_NUMERO_PARAMETRO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_ANO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_DATA_INICIO_VIGENCIA);
		sql.append(", PL.").append(CAMPO_DATA_FINAL_VIGENCIA);
		sql.append(", PL.").append(CAMPO_STATUS_PARAMETRO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_DATA_ATUALIZACAO_BD);
		sql.append(", PL.").append(CAMPO_STATUS_GIA);
		sql.append(", PL.").append(CAMPO_CALCULO_CASCATA);
		sql.append(", PL.").append(CAMPO_NUMERO_LEGISLACAO_PRINCIPAL);
		sql.append(" ,PL.").append(CAMPO_ANO_LEGISLACAO_PRINCIPAL);
		sql.append(" FROM ").append(TABELA_PARAMETRO_LEGISLACAO).append(" PL ");
		sql.append(" WHERE 1=1 ");
		if (parametroLegislacaoVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND PL.").append(CAMPO_CODIGO_PARAMETRO_LEGISLACAO).append(" = ? ");
			}
			if (Validador.isNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getNumeroLegislacao()))
			{
				sql.append(" AND PL.").append(CAMPO_NUMERO_PARAMETRO_LEGISLACAO).append(" = ? ");
			}
			if (Validador.isNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getAnoLegislacao()))
			{
				sql.append(" AND PL.").append(CAMPO_ANO_LEGISLACAO).append(" = ? ");
			}
			if (Validador.isDataValida(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getDataInicioVigencia()))
			{
				sql.append(" AND PL.").append(CAMPO_DATA_INICIO_VIGENCIA).append(" = ? ");
			}
			if (Validador.isDataValida(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getDataFimVigencia()))
			{
				sql.append(" AND PL.").append(CAMPO_DATA_FINAL_VIGENCIA).append(" = ? ");
			}
			if (Validador.isDominioNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getStatusLegislacao()))
			{
				sql.append(" AND PL.").append(CAMPO_STATUS_PARAMETRO_LEGISLACAO).append(" = ? ");
			}
			if (Validador.isDominioNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getStatusGia()))
			{
				sql.append(" AND PL.").append(CAMPO_STATUS_GIA).append(" = ? ");
			}
			if (Validador.isDominioNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getCalculoCascata()))
			{
				sql.append(" AND PL.").append(CAMPO_CALCULO_CASCATA).append(" = ? ");
			}
			if (Validador.isNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getNumeroLegislacaoPrincipal()))
			{
				sql.append(" AND PL.").append(CAMPO_NUMERO_LEGISLACAO_PRINCIPAL).append(" = ? ");
			}
			if (Validador.isNumericoValido(((ParametroLegislacaoVo) parametroLegislacaoVo.getParametroConsulta()).getAnoLegislacaoPrincipal()))
			{
				sql.append(" AND PL.").append(CAMPO_ANO_LEGISLACAO_PRINCIPAL).append(" = ? ");
			}
		}
		sql.append(" ORDER BY PL.").append(CAMPO_CODIGO_PARAMETRO_LEGISLACAO);
		return sql.toString();
	}

	/**
	 * Consulta o Parametro de Legislação Atual
	 * @param parametroLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoVo findParametroLegislacaoAtual(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListFindParametroLegislacaoAtual(parametroLegislacaoVo));
			rs = ps.executeQuery();
			if (rs.next())
			{
				getParametroLegislacao(rs, parametroLegislacaoVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_PARAMETRO_LEGISLACAO);
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
		return parametroLegislacaoVo;
	}

	/**
	 * Monta a SQL de consulta de Parametro Legislação atual
	 * @param parametroLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListFindParametroLegislacaoAtual(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT PL.").append(CAMPO_CODIGO_PARAMETRO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_NUMERO_PARAMETRO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_ANO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_DATA_INICIO_VIGENCIA);
		sql.append(", PL.").append(CAMPO_DATA_FINAL_VIGENCIA);
		sql.append(", PL.").append(CAMPO_STATUS_PARAMETRO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_DATA_ATUALIZACAO_BD);
		sql.append(", PL.").append(CAMPO_STATUS_GIA);
		sql.append(", PL.").append(CAMPO_CALCULO_CASCATA);
		sql.append(", PL.").append(CAMPO_NUMERO_LEGISLACAO_PRINCIPAL);
		sql.append(", PL.").append(CAMPO_ANO_LEGISLACAO_PRINCIPAL);
		sql.append(" FROM ").append(TABELA_PARAMETRO_LEGISLACAO).append(" PL ");
		sql.append(" WHERE ( ");
		sql.append("				(PL.").append(CAMPO_DATA_FINAL_VIGENCIA).append(" is not null and ");
		sql.append(" 					(sysdate BETWEEN PL.").append(CAMPO_DATA_INICIO_VIGENCIA).append(" AND PL.").append(CAMPO_DATA_FINAL_VIGENCIA).append(" ) ");
		sql.append("				) ");
		sql.append(" 			or ");
		sql.append(" 				(PL.").append(CAMPO_DATA_INICIO_VIGENCIA).append(" < sysdate and PL.").append(CAMPO_DATA_FINAL_VIGENCIA).append(" is null ) ");
		sql.append("		 ) ");
		sql.append(" AND PL.").append(CAMPO_STATUS_PARAMETRO_LEGISLACAO).append(" = ").append(DomnStatusRegistro.ATIVO);
		return sql.toString();
	}

	/**
	 * Efetua a consulta do ultimo Parametro Legislação
	 * @param parametroLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoVo findParametroLegislacaoUltima(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListFindParametroLegislacaoUltima());
			rs = ps.executeQuery();
			if (rs.next())
			{
				getParametroLegislacao(rs, parametroLegislacaoVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_PARAMETRO_LEGISLACAO);
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
		return parametroLegislacaoVo;
	}

	/**
	 * Monsta a SQL de consulta do ultimo Parametro de Legislação
	 * @throws ObjetoObrigatorioException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListFindParametroLegislacaoUltima() throws ObjetoObrigatorioException
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT PL.").append(CAMPO_CODIGO_PARAMETRO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_NUMERO_PARAMETRO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_ANO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_DATA_INICIO_VIGENCIA);
		sql.append(", PL.").append(CAMPO_DATA_FINAL_VIGENCIA);
		sql.append(", PL.").append(CAMPO_STATUS_PARAMETRO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_DATA_ATUALIZACAO_BD);
		sql.append(", PL.").append(CAMPO_STATUS_GIA);
		sql.append(", PL.").append(CAMPO_CALCULO_CASCATA);
		sql.append(", PL.").append(CAMPO_NUMERO_LEGISLACAO_PRINCIPAL);
		sql.append(", PL.").append(CAMPO_ANO_LEGISLACAO_PRINCIPAL);
		sql.append(" FROM ").append(TABELA_PARAMETRO_LEGISLACAO).append(" PL ");
		sql.append(" WHERE ").append(CAMPO_CODIGO_PARAMETRO_LEGISLACAO).append(" = ");
		sql.append(" (SELECT MAX(").append(CAMPO_CODIGO_PARAMETRO_LEGISLACAO).append(") FROM ").append(TABELA_PARAMETRO_LEGISLACAO).append(" ) ");
		return sql.toString();
	}

	/**
	 * Método que consulta uma Parametro Legislação vigente em uma determinada data
	 * @param data
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 */
	public ParametroLegislacaoVo findParametroLegislacaoAtualByData(final Date data) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		Validador.validaObjeto(data);
		PreparedStatement ps = null;
		ResultSet rs = null;
		ParametroLegislacaoVo parametroLegislacaoVo = new ParametroLegislacaoVo();
		try
		{
			ps = conn.prepareStatement(getSQLFindParametroLegislacaoAtualByData(data));
			prepareStatementFindParametroLegislacaoAtualByData(ps, data);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getParametroLegislacao(rs, parametroLegislacaoVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_PARAMETRO_LEGISLACAO);
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
		return parametroLegislacaoVo;
	}

	/**
	 * Monta a SQL de consulta do Parametro Legislação por data
	 * @param data
	 * @throws ObjetoObrigatorioException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindParametroLegislacaoAtualByData(final Date data) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(data);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT PL.").append(CAMPO_CODIGO_PARAMETRO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_NUMERO_PARAMETRO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_ANO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_DATA_INICIO_VIGENCIA);
		sql.append(", PL.").append(CAMPO_DATA_FINAL_VIGENCIA);
		sql.append(", PL.").append(CAMPO_STATUS_PARAMETRO_LEGISLACAO);
		sql.append(", PL.").append(CAMPO_DATA_ATUALIZACAO_BD);
		sql.append(", PL.").append(CAMPO_STATUS_GIA);
		sql.append(", PL.").append(CAMPO_CALCULO_CASCATA);
		sql.append(", PL.").append(CAMPO_NUMERO_LEGISLACAO_PRINCIPAL);
		sql.append(", PL.").append(CAMPO_ANO_LEGISLACAO_PRINCIPAL);
		sql.append(" FROM ").append(TABELA_PARAMETRO_LEGISLACAO).append(" PL ");
		sql.append(" WHERE ( ");
		sql.append("            (PL.").append(CAMPO_DATA_FINAL_VIGENCIA).append(" is not null and ");
		sql.append("               (? BETWEEN PL.").append(CAMPO_DATA_INICIO_VIGENCIA).append(" AND PL.").append(CAMPO_DATA_FINAL_VIGENCIA).append(" ) ");
		sql.append("            ) ");
		sql.append("         or ");
		sql.append("            ( ? >= PL.").append(CAMPO_DATA_INICIO_VIGENCIA).append(" and PL.").append(CAMPO_DATA_FINAL_VIGENCIA).append(" is null ) ");
		sql.append("       ) ");
		sql.append(" AND PL.").append(CAMPO_STATUS_PARAMETRO_LEGISLACAO).append(" = ").append(DomnStatusRegistro.ATIVO);
		return sql.toString();
	}

	/**
	 * Monta o Prepared Statement de consulta do Parametro de Legislação por data
	 * @param ps
	 * @param data
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindParametroLegislacaoAtualByData(final PreparedStatement ps, final Date data) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		int contador = 0;
		try
		{
			if (Validador.isDataValida(data))
			{
				ps.setDate(++contador, new java.sql.Date(data.getTime()));
				ps.setDate(++contador, new java.sql.Date(data.getTime()));
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}
}
