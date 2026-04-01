package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralBenfeitoria;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe para manipular consultas no banco de dados da Ficha Imóvel Rural Benfeitoria
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.3 $
 */
public class FichaImovelRuralBenfeitoriaQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposFichaImovelRuralBenfeitoria
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBenfeitoriaQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método que monta a FichaImovelRuralBenfeitoriaVo com os dados da ResultSet
	 * 
	 * @param rs
	 * @param fichaImovelRuralBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @return FichaImovelRuralBenfeitoriaVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBenfeitoriaVo getFichaImovelRuralBenfeitoria(final ResultSet rs, final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
		fichaImovelRuralBenfeitoriaVo.setCodigo(rs.getLong(CAMPO_CODIGO_IMOVEL_RURAL_BENFEITORIA));
		fichaImovelRuralBenfeitoriaVo.getFichaImovelRuralVo().setCodigo(rs.getLong(CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL));
		fichaImovelRuralBenfeitoriaVo.getBenfeitoriaVo().setCodigo(rs.getLong(CAMPO_ITCTB07_CODIGO_BENFEITORIA));
		fichaImovelRuralBenfeitoriaVo.setDescricaoComplementarBenfeitoria(rs.getString(CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA));
		return fichaImovelRuralBenfeitoriaVo;
	}

	/**
	 * Método que monta o PreparedStatement de acordo com os dados válidos do FichaImovelRuralBenfeitoriaVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindFichaImovelRuralBenfeitoria(final PreparedStatement ps, final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
		int contador = 0;
		if (fichaImovelRuralBenfeitoriaVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralBenfeitoriaVo) fichaImovelRuralBenfeitoriaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralBenfeitoriaVo) fichaImovelRuralBenfeitoriaVo.getParametroConsulta()).getCodigo());
			}
			// IMOVEL RURAL
			if (Validador.isNumericoValido(((FichaImovelRuralBenfeitoriaVo) fichaImovelRuralBenfeitoriaVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralBenfeitoriaVo) fichaImovelRuralBenfeitoriaVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo());
			}
			// BENFEITORIA
			if (Validador.isNumericoValido(((FichaImovelRuralBenfeitoriaVo) fichaImovelRuralBenfeitoriaVo.getParametroConsulta()).getBenfeitoriaVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralBenfeitoriaVo) fichaImovelRuralBenfeitoriaVo.getParametroConsulta()).getBenfeitoriaVo().getCodigo());
			}
		}
	}

	/**
	 * Método para consultar uma Ficha Imóvel Rura Benfeitoria.<br>
	 * Se mais de uma Ficha for encontrada, será retornada a de menor código.<br><br>
	 * 
	 * Os atributos de consulta são:<br>
	 * <b>- fichaImovelRuralBenfeitoriaVo.codigo</b><br>
	 * <b>- fichaImovelRuralBenfeitoriaVo.fichaImovelRuralVo.codigo</b><br>
	 * <b>- fichaImovelRuralBenfeitoriaVo.benfeitoriaVo.codigo</b><br>
	 * 
	 * @param fichaImovelRuralBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return FichaImovelRuralBenfeitoriaVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBenfeitoriaVo findFichaImovelRuralBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindFichaImovelRuralBenfeitoria(fichaImovelRuralBenfeitoriaVo));
			prepareStatementFindFichaImovelRuralBenfeitoria(ps, fichaImovelRuralBenfeitoriaVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getFichaImovelRuralBenfeitoria(rs, fichaImovelRuralBenfeitoriaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_FICHA_IMOVEL_RURAL_BENFEITORIA);
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
		return fichaImovelRuralBenfeitoriaVo;
	}

	/**
	 * Método que monsta a consulta SQL do Ficha Imóvel Rural Benfeitoria 
	 * 
	 * @param fichaImovelRuralBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindFichaImovelRuralBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT FIRBEN." + CAMPO_CODIGO_IMOVEL_RURAL_BENFEITORIA + " ");
		sql.append(" , FIRBEN." + CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL + " ");
		sql.append(" , FIRBEN." + CAMPO_ITCTB07_CODIGO_BENFEITORIA + " ");
		sql.append(" , FIRBEN." + CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA + " ");
		sql.append(" FROM " + TABELA_FICHA_IMOVEL_RURAL_BENFEITORIA + " FIRBEN ");
		sql.append(" WHERE 1 = 1 ");
		if (fichaImovelRuralBenfeitoriaVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralBenfeitoriaVo) fichaImovelRuralBenfeitoriaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND FIRBEN." + CAMPO_CODIGO_IMOVEL_RURAL_BENFEITORIA + " = ? ");
			}
			// IMOVEL RURAL
			if (Validador.isNumericoValido(((FichaImovelRuralBenfeitoriaVo) fichaImovelRuralBenfeitoriaVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo()))
			{
				sql.append("   AND FIRBEN." + CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL + " = ? ");
			}
			// BENFEITORIA
			if (Validador.isNumericoValido(((FichaImovelRuralBenfeitoriaVo) fichaImovelRuralBenfeitoriaVo.getParametroConsulta()).getBenfeitoriaVo().getCodigo()))
			{
				sql.append("   AND FIRBEN." + CAMPO_ITCTB07_CODIGO_BENFEITORIA + " = ? ");
			}
		}
		sql.append(" ORDER BY FIRBEN." + CAMPO_CODIGO_IMOVEL_RURAL_BENFEITORIA + " ");
		return sql.toString();
	}

	/**
	 * Método para listar Ficha Imóvel Rura Benfeitoria.<br><br>
	 * 
	 * Os atributos de consulta são:<br>
	 * <b>- fichaImovelRuralBenfeitoriaVo.codigo</b><br>
	 * <b>- fichaImovelRuralBenfeitoriaVo.fichaImovelRuralVo.codigo</b><br>
	 * <b>- fichaImovelRuralBenfeitoriaVo.benfeitoriaVo.codigo</b><br>	 * 
	 * 
	 * @param fichaImovelRuralBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBenfeitoriaVo listFichaImovelRuralBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindFichaImovelRuralBenfeitoria(fichaImovelRuralBenfeitoriaVo));
			prepareStatementFindFichaImovelRuralBenfeitoria(ps, fichaImovelRuralBenfeitoriaVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaAtualVo = new FichaImovelRuralBenfeitoriaVo();
				getFichaImovelRuralBenfeitoria(rs, fichaImovelRuralBenfeitoriaAtualVo);
				fichaImovelRuralBenfeitoriaVo.getCollVO().add(fichaImovelRuralBenfeitoriaAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_FICHA_IMOVEL_RURAL_BENFEITORIA);
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
		return fichaImovelRuralBenfeitoriaVo;
	}
}
