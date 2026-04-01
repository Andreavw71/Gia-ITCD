package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.benfeitoria;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelUrbanoBenfeitoria;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Classe de manipulação de consultas de Ficha Imóvel Urbano - Benfeitoria
 * 
 * @author Lucas Nascimento
 * @version $Revision: 1.2 $
 */
public class FichaImovelUrbanoBenfeitoriaQDao extends AbstractDao implements CamposFichaImovelUrbanoBenfeitoria, TabelasITC
{

	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelUrbanoBenfeitoriaQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Médoto que preenche os dados de uma Ficha Imóvel Urbano a partir do Result Set
	 * 
	 * @param rs Result Set
	 * @param fichaImovelUrbanoBenfeitoriaVo Ficha Imovel Urbano Benfeitoria - Value Object VO
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	private void getFichaImovelUrbanoBenfeitoria(final ResultSet rs, final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		fichaImovelUrbanoBenfeitoriaVo.setCodigo(rs.getLong(CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA));
		fichaImovelUrbanoBenfeitoriaVo.getFichaImovelUrbanoVo().setCodigo(rs.getLong(CAMPO_ITCTB19_CODIGO_IMOVEL_URBANO));
		fichaImovelUrbanoBenfeitoriaVo.getBenfeitoriaVo().setCodigo(rs.getLong(CAMPO_ITCTB07_CODIGO_BENFEITORIA));
		fichaImovelUrbanoBenfeitoriaVo.setDescricaoComplementarBenfeitoria(rs.getString(CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA));
	}

	/**
	 * Médoto para pesquisa de uma Ficha de Imóvel Urbano
	 * 
	 * @param fichaImovelUrbanoBenfeitoriaVo Ficha Imóvel Urbano - Value Object
	 * @return FichaImovelUrbanoVo Value Object	 * 
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoBenfeitoriaVo findFichaImovelUrbanoBenfeitoria(FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindFichaImovelUrbanoBenfeitoria(fichaImovelUrbanoBenfeitoriaVo));
			preparedStatementFindFichaImovelUrbanoBenfeitoria(ps, fichaImovelUrbanoBenfeitoriaVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getFichaImovelUrbanoBenfeitoria(rs, fichaImovelUrbanoBenfeitoriaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_IMOVEL_URBANO_BENFEITORIA);
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
		return fichaImovelUrbanoBenfeitoriaVo;
	}

	/**
	 * Método usado para buscar/listar um grupo de registros de Imóveis Urbanos.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método 
	 * retornará todos os Imóveis Urbanos cadastrados no sistema.
	 * A pesquisa parametrizada pode ser: pelo código ou por qualquer outro atributo de um Imóvel Urbano.
	 * 
	 * @param fichaImovelUrbanoBenfeitoriaVo VO de Ficha Imóvel Urbano Benfeitoria(Value Object) - usado para passar os critérios da consulta
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return BemTributavelVo
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoBenfeitoriaVo listFichaImovelUrbanoBenfeitoria(FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindFichaImovelUrbanoBenfeitoria(fichaImovelUrbanoBenfeitoriaVo));
			preparedStatementFindFichaImovelUrbanoBenfeitoria(ps, fichaImovelUrbanoBenfeitoriaVo);
			Collection listaFichaImovelUrbanoBenfeitoria = new ArrayList();
			for (rs = ps.executeQuery(); rs.next(); )
			{
				FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVoAtual = new FichaImovelUrbanoBenfeitoriaVo();
				getFichaImovelUrbanoBenfeitoria(rs, fichaImovelUrbanoBenfeitoriaVoAtual);
				listaFichaImovelUrbanoBenfeitoria.add(fichaImovelUrbanoBenfeitoriaVoAtual);
			}
			if (Validador.isCollectionValida(listaFichaImovelUrbanoBenfeitoria))
			{
				fichaImovelUrbanoBenfeitoriaVo.setCollVO(listaFichaImovelUrbanoBenfeitoria);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_IMOVEL_URBANO_BENFEITORIA);
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
		return fichaImovelUrbanoBenfeitoriaVo;
	}

	/**
	 * Método para construir a SQL de consulta do Ficha Imóvel Urbano - Benfeitoria
	 * 
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @return String
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	private String getSQLFindFichaImovelUrbanoBenfeitoria(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT FICHAB.").append(CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA).append(" , FICHAB.").append(CAMPO_ITCTB19_CODIGO_IMOVEL_URBANO).append(" , FICHAB.").append(CAMPO_ITCTB07_CODIGO_BENFEITORIA).append(" , FICHAB.").append(CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA).append(" FROM ").append(TABELA_IMOVEL_URBANO_BENFEITORIA).append(" FICHAB ").append(" WHERE 1 = 1 ");
		if (fichaImovelUrbanoBenfeitoriaVo != null && fichaImovelUrbanoBenfeitoriaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((FichaImovelUrbanoBenfeitoriaVo) fichaImovelUrbanoBenfeitoriaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND FICHAB.").append(CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA).append(" = ? ");
			}
			if (Validador.isNumericoValido(((FichaImovelUrbanoBenfeitoriaVo) fichaImovelUrbanoBenfeitoriaVo.getParametroConsulta()).getFichaImovelUrbanoVo().getCodigo()))
			{
				sql.append(" AND FICHAB.").append(CAMPO_ITCTB19_CODIGO_IMOVEL_URBANO).append(" = ? ");
			}
			if (Validador.isNumericoValido(((FichaImovelUrbanoBenfeitoriaVo) fichaImovelUrbanoBenfeitoriaVo.getParametroConsulta()).getBenfeitoriaVo().getCodigo()))
			{
				sql.append(" AND FICHAB.").append(CAMPO_ITCTB07_CODIGO_BENFEITORIA).append(" = ? ");
			}
		}
		sql.append(" ORDER BY FICHAB.").append(CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA);
		return sql.toString();
	}

	/**
	 * Método que monta o PreparedStatement de acordo com os dados válidos do FichaImovelUrbanoBenfeitoriaVo
	 * 
	 * @param ps
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementFindFichaImovelUrbanoBenfeitoria(final PreparedStatement ps, final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		Validador.validaObjeto(ps);
		int contador = 0;
		if (fichaImovelUrbanoBenfeitoriaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((FichaImovelUrbanoBenfeitoriaVo) fichaImovelUrbanoBenfeitoriaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoBenfeitoriaVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isNumericoValido(((FichaImovelUrbanoBenfeitoriaVo) fichaImovelUrbanoBenfeitoriaVo.getParametroConsulta()).getFichaImovelUrbanoVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelUrbanoBenfeitoriaVo) fichaImovelUrbanoBenfeitoriaVo.getParametroConsulta()).getFichaImovelUrbanoVo().getCodigo());
			}
			if (Validador.isNumericoValido(((FichaImovelUrbanoBenfeitoriaVo) fichaImovelUrbanoBenfeitoriaVo.getParametroConsulta()).getBenfeitoriaVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelUrbanoBenfeitoriaVo) fichaImovelUrbanoBenfeitoriaVo.getParametroConsulta()).getBenfeitoriaVo().getCodigo());
			}
		}
	}
}
