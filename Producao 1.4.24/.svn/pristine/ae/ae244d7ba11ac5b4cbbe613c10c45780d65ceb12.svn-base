package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralRebanho;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Método que efetua consultas de Ficha Imóvel Rural Rebanho
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
public class FichaImovelRuralRebanhoQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposFichaImovelRuralRebanho
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralRebanhoQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método que monta o FichaImovelRuralRebanhoVo de acordo com os dados do ResultSet
	 * 
	 * @param rs
	 * @param fichaImovelRuralRebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @return FichaImovelRuralRebanhoVo
	 * @implemented by Daniel Balieiro
	 */
	private FichaImovelRuralRebanhoVo getFichaImovelRuralRebanho(final ResultSet rs, final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(fichaImovelRuralRebanhoVo);
		fichaImovelRuralRebanhoVo.setCodigo(rs.getLong(CAMPO_CODIGO_IMOVEL_RURAL_REBANHO));
		fichaImovelRuralRebanhoVo.getFichaImovelRuralVo().setCodigo((rs.getLong(CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL)));
		fichaImovelRuralRebanhoVo.getRebanhoVo().setCodigo(rs.getLong(CAMPO_ITCTB10_CODG_REBANHO));
		fichaImovelRuralRebanhoVo.setQuantidadeRebanho(rs.getLong(CAMPO_QUANTIDADE_REBANHO));
		fichaImovelRuralRebanhoVo.setDescricaoRebanho(rs.getString(CAMPO_DESCRICAO_REBANHO));
		fichaImovelRuralRebanhoVo.setValorMercado(rs.getDouble(CAMPO_VALOR_MERCADO));
		fichaImovelRuralRebanhoVo.setValorTotal(fichaImovelRuralRebanhoVo.getValorMercado() * fichaImovelRuralRebanhoVo.getQuantidadeRebanho());
		return fichaImovelRuralRebanhoVo;
	}

	/**
	 * Método que monta o PreparedStatement com os dados válidos do FichaImovelRuralRebanhoVo
	 * @param ps
	 * @param fichaImovelRuralRebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindFichaImovelRuralRebanho(final PreparedStatement ps, final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralRebanhoVo);
		int contador = 0;
		if (fichaImovelRuralRebanhoVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getCodigo());
			}
			// FICHA IMOVEL RURAL
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo());
			}
			// REBANHO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getRebanhoVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getRebanhoVo().getCodigo());
			}
			// QUANTIDADE REBANHO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getQuantidadeRebanho()))
			{
				ps.setLong(++contador, ((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getQuantidadeRebanho());
			}
			// DESCRICAO REBANHO
			if (Validador.isStringValida(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getDescricaoRebanho()))
			{
				ps.setString(++contador, ((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getDescricaoRebanho());
			}
			// VALOR MERCADO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getValorMercado()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getValorMercado());
			}
		}
	}

	/**
	 * Método que efetua consulta de uma Ficha Imóvel Rural Rebanho.<br>
	 * Se mais de uma for encontrada, será retornada a de menor código.<br><br>
	 * 
	 * Os atributos para a consulta são:<br>
	 * <b>-fichaImovelRuralRebanhoVo.codigo</b><br>
	 * <b>-fichaImovelRuralRebanhoVo.rebanhoVo.codigo</b><br>
	 * <b>-fichaImovelRuralRebanhoVo.fichaImovelRuralVo.codigo</b><br>
	 * 
	 * @param fichaImovelRuralRebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return FichaImovelRuralRebanhoVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralRebanhoVo findFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelRuralRebanhoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindFichaImovelRuralRebanho(fichaImovelRuralRebanhoVo));
			prepareStatementFindFichaImovelRuralRebanho(ps, fichaImovelRuralRebanhoVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getFichaImovelRuralRebanho(rs, fichaImovelRuralRebanhoVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_FICHA_IMOVEL_RURAL_REBANHO);
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
		return fichaImovelRuralRebanhoVo;
	}

	/**
	 * Método que monta a SQL de consulta da Ficha Imóvel Rural Rebanho
	 * 
	 * @param fichaImovelRuralRebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralRebanhoVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT FIRREB." + CAMPO_CODIGO_IMOVEL_RURAL_REBANHO + " ");
		sql.append(" , FIRREB." + CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL + " ");
		sql.append(" , FIRREB." + CAMPO_ITCTB10_CODG_REBANHO + " ");
		sql.append(" , FIRREB." + CAMPO_QUANTIDADE_REBANHO + " ");
		sql.append(" , FIRREB." + CAMPO_DESCRICAO_REBANHO + " ");
		sql.append(" , FIRREB." + CAMPO_VALOR_MERCADO + " ");
		sql.append(" FROM " + TABELA_FICHA_IMOVEL_RURAL_REBANHO + " FIRREB ");
		sql.append(" WHERE 1 = 1 ");
		if (fichaImovelRuralRebanhoVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND FIRREB." + CAMPO_CODIGO_IMOVEL_RURAL_REBANHO + " = ? ");
			}
			// IMOVEL RURAL
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo()))
			{
				sql.append("   AND FIRREB." + CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL + " = ? ");
			}
			// REBANHO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getRebanhoVo().getCodigo()))
			{
				sql.append("   AND FIRREB." + CAMPO_ITCTB10_CODG_REBANHO + " = ? ");
			}
			// QUANTIDADE REBANHO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getQuantidadeRebanho()))
			{
				sql.append("   AND FIRREB." + CAMPO_QUANTIDADE_REBANHO + " = ? ");
			}
			// DESCRICAO REBANHO
			if (Validador.isStringValida(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getDescricaoRebanho()))
			{
				sql.append("   AND UPPER(FIRREB." + CAMPO_DESCRICAO_REBANHO + ") = UPPER(?) ");
			}
			// VALOR MERCADO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getValorMercado()))
			{
				sql.append("   AND FIRREB." + CAMPO_VALOR_MERCADO + " = ? ");
			}
		}
		sql.append(" ORDER BY FIRREB." + CAMPO_CODIGO_IMOVEL_RURAL_REBANHO + " ");
		return sql.toString();
	}

	/**
	 * Método que monta o PreparedStatement com os dados válidos do FichaImovelRuralRebanho
	 * 
	 * @param ps
	 * @param fichaImovelRuralRebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementListFichaImovelRuralRebanho(final PreparedStatement ps, final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralRebanhoVo);
		int contador = 0;
		if (fichaImovelRuralRebanhoVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getCodigo());
			}
			// FICHA IMOVEL RURAL
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo());
			}
			// REBANHO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getRebanhoVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getRebanhoVo().getCodigo());
			}
			// QUANTIDADE REBANHO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getQuantidadeRebanho()))
			{
				ps.setLong(++contador, ((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getQuantidadeRebanho());
			}
			// VALOR MERCADO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getValorMercado()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getValorMercado());
			}
		}
	}

	/**
	 * Método que efetua consulta de uma Ficha Imóvel Rural Rebanho.<br><br>
	 * 
	 * Os atributos para a consulta são:<br>
	 * <b>-fichaImovelRuralRebanhoVo.codigo</b><br>
	 * <b>-fichaImovelRuralRebanhoVo.rebanhoVo.codigo</b><br>
	 * <b>-fichaImovelRuralRebanhoVo.fichaImovelRuralVo.codigo</b><br>
	 * @param fichaImovelRuralRebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralRebanhoVo listFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelRuralRebanhoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListFichaImovelRuralRebanho(fichaImovelRuralRebanhoVo));
			prepareStatementListFichaImovelRuralRebanho(ps, fichaImovelRuralRebanhoVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoAtualVo = new FichaImovelRuralRebanhoVo();
				getFichaImovelRuralRebanho(rs, fichaImovelRuralRebanhoAtualVo);
				fichaImovelRuralRebanhoVo.getCollVO().add(fichaImovelRuralRebanhoAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_FICHA_IMOVEL_RURAL_REBANHO);
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
		return fichaImovelRuralRebanhoVo;
	}

	/**
	 * Método que monta a SQL de listagem do Ficha Imóvel Rural Rebanho
	 * @param fichaImovelRuralRebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @return FichaImovelRuralRebanhoVo
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralRebanhoVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT FIRREB." + CAMPO_CODIGO_IMOVEL_RURAL_REBANHO + " ");
		sql.append(" , FIRREB." + CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL + " ");
		sql.append(" , FIRREB." + CAMPO_ITCTB10_CODG_REBANHO + " ");
		sql.append(" , FIRREB." + CAMPO_QUANTIDADE_REBANHO + " ");
		sql.append(" , FIRREB." + CAMPO_DESCRICAO_REBANHO + " ");
		sql.append(" , FIRREB." + CAMPO_VALOR_MERCADO + " ");
		sql.append(" FROM " + TABELA_FICHA_IMOVEL_RURAL_REBANHO + " FIRREB ");
		sql.append(" WHERE 1 = 1 ");
		if (fichaImovelRuralRebanhoVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND FIRREB." + CAMPO_CODIGO_IMOVEL_RURAL_REBANHO + " = ? ");
			}
			// IMOVEL RURAL
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo()))
			{
				sql.append("   AND FIRREB." + CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL + " = ? ");
			}
			// REBANHO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getRebanhoVo().getCodigo()))
			{
				sql.append("   AND FIRREB." + CAMPO_ITCTB10_CODG_REBANHO + " = ? ");
			}
			// QUANTIDADE REBANHO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getQuantidadeRebanho()))
			{
				sql.append("   AND FIRREB." + CAMPO_QUANTIDADE_REBANHO + " = ? ");
			}
			// DESCRICAO REBANHO
			if (Validador.isStringValida(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getDescricaoRebanho()))
			{
				sql.append(" AND UPPER(FIRREB." + CAMPO_DESCRICAO_REBANHO + ") LIKE (UPPER('%" + 
								  ((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getDescricaoRebanho() + 
								  "%'))");
			}
			// VALOR MERCADO
			if (Validador.isNumericoValido(((FichaImovelRuralRebanhoVo) fichaImovelRuralRebanhoVo.getParametroConsulta()).getValorMercado()))
			{
				sql.append("   AND FIRREB." + CAMPO_VALOR_MERCADO + " = ? ");
			}
		}
		sql.append(" ORDER BY FIRREB." + CAMPO_CODIGO_IMOVEL_RURAL_REBANHO + " ");
		return sql.toString();
	}
}
