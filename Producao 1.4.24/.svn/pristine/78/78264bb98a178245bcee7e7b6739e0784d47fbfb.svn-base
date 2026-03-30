package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralCultura;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Método para manipular consultas de Ficha Imóvel Rural Cultura no banco de dados
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
public class FichaImovelRuralCulturaQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposFichaImovelRuralCultura
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método para montar o FichaImovelRuralCulturaVo com os dados do ResultSet
	 * 
	 * @param rs
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private FichaImovelRuralCulturaVo getFichaImovelRuralCultura(final ResultSet rs, final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		fichaImovelRuralCulturaVo.setCodigo(rs.getLong(CAMPO_CODIGO_IMOVEL_RURAL_CULTURA));
		fichaImovelRuralCulturaVo.getCulturaVo().setCodigo(rs.getLong(CAMPO_ITCTB08_CODIGO_CULTURA));
		fichaImovelRuralCulturaVo.getFichaImovelRuralVo().setCodigo(rs.getLong(CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL));
		fichaImovelRuralCulturaVo.setDescricaoComplementarCultura(rs.getString(CAMPO_DESCRICAO_COMPLEMENTAR_CULTURA));
		fichaImovelRuralCulturaVo.setValorHectare(rs.getDouble(CAMPO_VALOR_HECTARE));
		fichaImovelRuralCulturaVo.setAreaCultivada(rs.getDouble(CAMPO_AREA_CULTIVADA));
		fichaImovelRuralCulturaVo.setValorMercado(rs.getDouble(CAMPO_VALOR_MERCADO));
		return fichaImovelRuralCulturaVo;
	}

	/**
	 * Metodo que monta o PreparedStatement de acordo com os dados válidos do FichaImovelRuralCulturaVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindFichaImovelRuralCultura(final PreparedStatement ps, final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		int contador = 0;
		if (fichaImovelRuralCulturaVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getCodigo());
			}
			// CULTURA
			if (Validador.isNumericoValido(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getCulturaVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getCulturaVo().getCodigo());
			}
			// FICHA IMOVEL RURAL
			if (Validador.isNumericoValido(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo());
			}
			//DESCRICAO COPLEMENTAR CULTURA
			if(Validador.isStringValida(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getDescricaoComplementarCultura()))
			{
				ps.setString(++contador, ((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getDescricaoComplementarCultura());
			}
			// AREA CULTIVADA
			if (Validador.isNumericoValido(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getAreaCultivada()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getAreaCultivada());
			}
			//VALOR DO HECTARE
			if(Validador.isNumericoValido(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getValorHectare()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getValorHectare());
			}
			// VALOR MERCADO
			if (Validador.isNumericoValido(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getValorMercado()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getValorMercado());
			}
		}
	}

	/**
	 * Método para consultar uma Ficha Imóvel Rural Cultura.<br>
	 * Se mais de uma Ficha for encontrada, será retornada a de menor código.<br><br>
	 * Os atributos para consulta são:<br>
	 * <b>-fichaImovelRuralCulturaVo.codigo</b><br>
	 * <b>-fichaImovelRuralCulturaVo.fichaImovelRuralVo.codigo</b><br>
	 * <b>-fichaImovelRuralCulturaVo.culturaVo.codigo</b><br>
	 * 
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return FichaImovelRuralCulturaVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaVo findFichaImovelRuralCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindFichaImovelRuralCultura(fichaImovelRuralCulturaVo));
			prepareStatementFindFichaImovelRuralCultura(ps, fichaImovelRuralCulturaVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getFichaImovelRuralCultura(rs, fichaImovelRuralCulturaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_FICHA_IMOVEL_RURAL_CULTURA);
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
		return fichaImovelRuralCulturaVo;
	}

	/**
	 * Método que monta a SQL de consulta de uma Ficha Imóvel Rural Cultura
	 * 
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindFichaImovelRuralCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT FIRCUL." + CAMPO_CODIGO_IMOVEL_RURAL_CULTURA + " ");
		sql.append(" , FIRCUL." + CAMPO_ITCTB08_CODIGO_CULTURA + " ");
		sql.append(" , FIRCUL." + CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL + " ");
		sql.append(" , FIRCUL." + CAMPO_DESCRICAO_COMPLEMENTAR_CULTURA + " ");
		sql.append(" , FIRCUL." + CAMPO_AREA_CULTIVADA + " ");
		sql.append(" , FIRCUL." + CAMPO_VALOR_HECTARE + " ");
		sql.append(" , FIRCUL." + CAMPO_VALOR_MERCADO + " ");
		sql.append(" FROM " + TABELA_FICHA_IMOVEL_RURAL_CULTURA + " FIRCUL ");
		sql.append(" WHERE 1 = 1 ");
		if (fichaImovelRuralCulturaVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND FIRCUL." + CAMPO_CODIGO_IMOVEL_RURAL_CULTURA + " = ? ");
			}
			// CULTURA
			if (Validador.isNumericoValido(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getCulturaVo().getCodigo()))
			{
				sql.append("   AND FIRCUL." + CAMPO_ITCTB08_CODIGO_CULTURA + " = ? ");
			}
			// IMOVEL RURAL
			if (Validador.isNumericoValido(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo()))
			{
				sql.append("   AND FIRCUL." + CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL + " = ? ");
			}
			//DESCRICAO COMPLEMENTAR CULTURA
			 if(Validador.isStringValida(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getDescricaoComplementarCultura()))
			{
				sql.append(" AND FIRCUL." + CAMPO_DESCRICAO_COMPLEMENTAR_CULTURA + " = ?");
			}
			// AREA CULTIVADA
			if (Validador.isNumericoValido(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getAreaCultivada()))
			{
				sql.append("   AND FIRCUL." + CAMPO_AREA_CULTIVADA + " = ? ");
			}
			//VALOR HECTARE
			 if(Validador.isNumericoValido(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getValorHectare()))
			{
				sql.append(" AND FIRCUL." + CAMPO_VALOR_HECTARE + " = ?");
			}
			// VALOR MERCADO
			if (Validador.isNumericoValido(((FichaImovelRuralCulturaVo) fichaImovelRuralCulturaVo.getParametroConsulta()).getValorMercado()))
			{
				sql.append("   AND FIRCUL." + CAMPO_VALOR_MERCADO + " = ? ");
			}
		}
		sql.append(" ORDER BY FIRCUL." + CAMPO_CODIGO_IMOVEL_RURAL_CULTURA + " ");
		return sql.toString();
	}

	/**
	 * Método para listar as Fichas Imóvel Rural Cultura.<br><br>
	 * Os atributos para a consulta são:<br>
	 * <b>-fichaImovelRuralCulturaVo.codigo</b><br>
	 * <b>-fichaImovelRuralCulturaVo.culturaVo.codigo</b><br>
	 * <b>-fichaImovelRuralCulturaVo.fichaImovelRuralVo.codigo</b><br>
	 * 
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return FichaImovelRuralCulturaVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaVo listFichaImovelRuralCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindFichaImovelRuralCultura(fichaImovelRuralCulturaVo));
			prepareStatementFindFichaImovelRuralCultura(ps, fichaImovelRuralCulturaVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				FichaImovelRuralCulturaVo fichaImovelRuralCulturaAtualVo = new FichaImovelRuralCulturaVo();
				getFichaImovelRuralCultura(rs, fichaImovelRuralCulturaAtualVo);
				fichaImovelRuralCulturaVo.getCollVO().add(fichaImovelRuralCulturaAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_FICHA_IMOVEL_RURAL_CULTURA);
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
		return fichaImovelRuralCulturaVo;
	}
}
