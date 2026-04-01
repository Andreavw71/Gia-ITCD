package br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposParametroLegislacaoMulta;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *  Classe de acesso a dados (Data Access Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class MultaQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposParametroLegislacaoMulta
{
	/**
	 * Construtora da Classe
	 * @param conexao Conexão que será utilizada para a manutenção dos dados, junto ao banco de dados
	 * @implemented by Daniel Balieiro
	 */
	public MultaQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Alimenta o MultaVo com os dados o ResultSet
	 * @param rs Contém os dados para alimentar o MultaVo
	 * @param multaVo VO que será alimentado
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public void getMultaVo(final ResultSet rs, final MultaVo multaVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		try
		{
			Validador.validaObjeto(rs);
			Validador.validaObjeto(multaVo);
			multaVo.setCodigo(rs.getLong(CAMPO_CODIGO_MULTA));
			multaVo.setCodigoParametroLegislacao(rs.getLong(CAMPO_ITCTB09_CODIGO_PARAMETRO_LEGISLACAO));
			multaVo.setQuantidadeDiasInicial(rs.getInt(CAMPO_QUANTIDADE_DIAS_INICIAL));
			multaVo.setQuantidadeDiasFinal(rs.getInt(CAMPO_QUANTIDADE_DIAS_FINAL));
			multaVo.setPercentualMulta(rs.getDouble(CAMPO_PERCENTUAL_MULTA));
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método utilizado para buscar dados de uma determinada Multa.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * a Multa de menor código cadastrado no banco de dados.
	 * <br>Esta pesquisa pode ser realizada pelos seguintes filtros:
	 * <br>- multaVo.codigo
	 * <br>- multaVo.codigoParametroLegislacao
	 * @param multaVo Com os dados a serem considerados na Consulta
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return MultaVo - Encontrada
	 * @implemented by Daniel Balieiro
	 */
	public MultaVo findMultaVo(final MultaVo multaVo) throws ObjetoObrigatorioException, ConsultaException, 
			  ConexaoException
	{
		Validador.validaObjeto(multaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListFindMultaVo(multaVo));
			prepareStatemantListFindMultaVo(ps, multaVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getMultaVo(rs, multaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_PARAMETRO_LEGISLACAO_MULTA);
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
		return multaVo;
	}

	/**
	 * Método utilizado para buscar dados de várias Multas.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todas as Multas do banco de dados.
	 * <br>Esta pesquisa pode ser realizada pelos seguintes filtros:
	 * <br>- multaVo.codigo
	 * <br>- multaVo.codigoParametroLegislacao
	 * @param multaVo Com os dados a serem considerados na Consulta
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return MultaVo - Encontrada
	 * @implemented by Daniel Balieiro
	 */
	public MultaVo listMultaVo(final MultaVo multaVo) throws ObjetoObrigatorioException, ConsultaException, 
			  ConexaoException
	{
		Validador.validaObjeto(multaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListFindMultaVo(multaVo));
			prepareStatemantListFindMultaVo(ps, multaVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				MultaVo multaAtualVo = new MultaVo();
				getMultaVo(rs, multaAtualVo);
				multaVo.getCollVO().add(multaAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_PARAMETRO_LEGISLACAO_MULTA);
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
		return multaVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement os parâmetros de pesquisa possíveis ao Consultar MultaVo
	 * @param ps PreparedStatement que receberá as informações
	 * @param multaVo Que contém os dados
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatemantListFindMultaVo(final PreparedStatement ps, final MultaVo multaVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(multaVo);
		try
		{
			int contador = 0;
			if (multaVo.isConsultaParametrizada())
			{
				if (Validador.isNumericoValido(((MultaVo) multaVo.getParametroConsulta()).getCodigo()))
				{
					ps.setLong(++contador, ((MultaVo) multaVo.getParametroConsulta()).getCodigo());
				}
				if (Validador.isNumericoValido(((MultaVo) multaVo.getParametroConsulta()).getCodigoParametroLegislacao()))
				{
					ps.setLong(++contador, ((MultaVo) multaVo.getParametroConsulta()).getCodigoParametroLegislacao());
				}
				if (Validador.isNumericoValido(((MultaVo) multaVo.getParametroConsulta()).getQuantidadeDiasInicial()))
				{
					ps.setInt(++contador, ((MultaVo) multaVo.getParametroConsulta()).getQuantidadeDiasInicial());
				}
				if (Validador.isNumericoValido(((MultaVo) multaVo.getParametroConsulta()).getQuantidadeDiasFinal()))
				{
					ps.setInt(++contador, ((MultaVo) multaVo.getParametroConsulta()).getQuantidadeDiasFinal());
				}
				if (Validador.isNumericoValido(((MultaVo) multaVo.getParametroConsulta()).getPercentualMulta()))
				{
					ps.setDouble(++contador, ((MultaVo) multaVo.getParametroConsulta()).getPercentualMulta());
				}
				if (Validador.isNumericoValido(((MultaVo) multaVo.getParametroConsulta()).getQuantidadeDiasConsulta()))
				{
					ps.setInt(++contador, ((MultaVo) multaVo.getParametroConsulta()).getQuantidadeDiasConsulta());
				}
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método para gerar o SQL de consulta apartir de um MultaVo
	 * @param multaVo MultaVo que será consultado para gerar o SQL
	 * @throws ObjetoObrigatorioException
	 * @return String - SQL Gerada
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListFindMultaVo(final MultaVo multaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(multaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  MULTA." + CAMPO_CODIGO_MULTA + ", ");
		sql.append(" MULTA." + CAMPO_ITCTB09_CODIGO_PARAMETRO_LEGISLACAO + ", ");
		sql.append(" MULTA." + CAMPO_QUANTIDADE_DIAS_INICIAL + ", ");
		sql.append(" MULTA." + CAMPO_QUANTIDADE_DIAS_FINAL + ", ");
		sql.append(" MULTA." + CAMPO_PERCENTUAL_MULTA + " ");
		sql.append(" FROM " + TABELA_PARAMETRO_LEGISLACAO_MULTA + " MULTA ");
		sql.append(" WHERE 1=1 ");
		if (multaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((MultaVo) multaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND MULTA." + CAMPO_CODIGO_MULTA + " = ? ");
			}
			if (Validador.isNumericoValido(((MultaVo) multaVo.getParametroConsulta()).getCodigoParametroLegislacao()))
			{
				sql.append(" AND MULTA." + CAMPO_ITCTB09_CODIGO_PARAMETRO_LEGISLACAO + " = ? ");
			}
			if (Validador.isNumericoValido(((MultaVo) multaVo.getParametroConsulta()).getQuantidadeDiasInicial()))
			{
				sql.append(" AND MULTA." + CAMPO_QUANTIDADE_DIAS_INICIAL + " = ? ");
			}
			if (Validador.isNumericoValido(((MultaVo) multaVo.getParametroConsulta()).getQuantidadeDiasFinal()))
			{
				sql.append(" AND MULTA." + CAMPO_QUANTIDADE_DIAS_FINAL + " = ? ");
			}
			if (Validador.isNumericoValido(((MultaVo) multaVo.getParametroConsulta()).getPercentualMulta()))
			{
				sql.append(" AND MULTA." + CAMPO_PERCENTUAL_MULTA + " = ? ");
			}
			if (Validador.isNumericoValido(((MultaVo) multaVo.getParametroConsulta()).getQuantidadeDiasConsulta()))
			{
				sql.append(" AND ? BETWEEN MULTA." + CAMPO_QUANTIDADE_DIAS_INICIAL + " AND MULTA." + CAMPO_QUANTIDADE_DIAS_FINAL + 
								  " ");
			}
			sql.append(" ORDER BY MULTA." + CAMPO_CODIGO_MULTA);
		}
		return sql.toString();
	}
}
