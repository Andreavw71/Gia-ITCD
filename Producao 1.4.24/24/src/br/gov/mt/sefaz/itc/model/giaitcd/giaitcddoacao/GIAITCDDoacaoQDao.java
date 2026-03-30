package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDoacao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDDoacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe de manipulação de consultas no banco de dados
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class GIAITCDDoacaoQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposGIAITCDDoacao
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conn
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoQDao(Connection conn)
	{
		super(conn);
	}

	/**
	 * Monta o GIAITCDDoacaoVo com os dados do ResultSet
	 * 
	 * @param rs
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void getGIAITCDDoacao(final ResultSet rs, final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(giaITCDDoacaoVo);
		giaITCDDoacaoVo.setCodigo(rs.getLong(CAMPO_ITCBTB14_CODIGO_ITCD));
		giaITCDDoacaoVo.setFracaoIdeal(rs.getDouble(CAMPO_FRAC_IDEAL));
		giaITCDDoacaoVo.setBaseCalculoReduzida(rs.getDouble(CAMPO_BASE_CALCULO_REDUZIDA));
		giaITCDDoacaoVo.setTipoDoacao(new DomnTipoDoacao(rs.getInt(CAMPO_TIPO_DOACAO)));
	}

	/**
	 * Monta a SQL de consulta de GIA ITCD Doação
	 * 
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLGIAITCDDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT GIA.").append(CAMPO_ITCBTB14_CODIGO_ITCD).append(" ");
		sql.append(" , GIA.").append(CAMPO_FRAC_IDEAL).append(" ");
		sql.append(" , GIA.").append(CAMPO_BASE_CALCULO_REDUZIDA).append(" ");
		sql.append(" , GIA.").append(CAMPO_TIPO_DOACAO).append(" ");
		sql.append(" FROM ").append(TABELA_GIA_ITCD_DOACAO).append(" GIA ");
		sql.append(" WHERE 1 = 1 ");
		if (giaITCDDoacaoVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((GIAITCDDoacaoVo) giaITCDDoacaoVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND GIA.").append(CAMPO_ITCBTB14_CODIGO_ITCD).append(" = ? ");
			}
			if (Validador.isNumericoValido(((GIAITCDDoacaoVo) giaITCDDoacaoVo.getParametroConsulta()).getFracaoIdeal()))
			{
				sql.append("   AND GIA.").append(CAMPO_FRAC_IDEAL).append(" = ? ");
			}
			if (Validador.isNumericoValido(((GIAITCDDoacaoVo) giaITCDDoacaoVo.getParametroConsulta()).getBaseCalculoReduzida()))
			{
				sql.append("   AND GIA.").append(CAMPO_FRAC_IDEAL).append(" = ? ");
			}
			if (Validador.isDominioNumericoValido(((GIAITCDDoacaoVo) giaITCDDoacaoVo.getParametroConsulta()).getTipoDoacao()))
			{
				sql.append("   AND GIA.").append(CAMPO_FRAC_IDEAL).append(" = ? ");
			}
		}
		sql.append(" ORDER BY GIA.").append(CAMPO_ITCBTB14_CODIGO_ITCD);
		return sql.toString();
	}

	/**
	 * Método para consultar uma GIA ITCD Doação
	 * 
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return GIAITCDDoacaoVo
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoVo findGIAITCDDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLGIAITCDDoacao(giaITCDDoacaoVo));
			prepareStatementGIAITCDDoacao(ps, giaITCDDoacaoVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getGIAITCDDoacao(rs, giaITCDDoacaoVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_DOACAO);
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
		return giaITCDDoacaoVo;
	}

	/**
	 * Método para montar o PreparedStatement de acordo com os dados válidos do GIAITCDDoacaoVo
	 * 
	 * @param ps
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementGIAITCDDoacao(final PreparedStatement ps, final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDDoacaoVo);
		int contador = 0;
		if (giaITCDDoacaoVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((GIAITCDDoacaoVo) giaITCDDoacaoVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((GIAITCDDoacaoVo) giaITCDDoacaoVo.getParametroConsulta()).getCodigo());
			}
			// FRACAO IDEAL
			if (Validador.isNumericoValido(((GIAITCDDoacaoVo) giaITCDDoacaoVo.getParametroConsulta()).getFracaoIdeal()))
			{
				ps.setDouble(++contador, ((GIAITCDDoacaoVo) giaITCDDoacaoVo.getParametroConsulta()).getFracaoIdeal());
			}
			// BASE CALCULO REDUZIDA
			if (Validador.isNumericoValido(((GIAITCDDoacaoVo) giaITCDDoacaoVo.getParametroConsulta()).getBaseCalculoReduzida()))
			{
				ps.setDouble(++contador, ((GIAITCDDoacaoVo) giaITCDDoacaoVo.getParametroConsulta()).getBaseCalculoReduzida());
			}
			// TIPO DOACAO
			if (Validador.isDominioNumericoValido(((GIAITCDDoacaoVo) giaITCDDoacaoVo.getParametroConsulta()).getTipoDoacao()))
			{
				ps.setInt(++contador, ((GIAITCDDoacaoVo) giaITCDDoacaoVo.getParametroConsulta()).getTipoDoacao().getValorCorrente());
			}
		}
	}

	/**
	 * Método para listar GIA ITCD Doação
	 * 
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return GIAITCDDoacaoVo
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoVo listGIAITCDDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLGIAITCDDoacao(giaITCDDoacaoVo));
			prepareStatementGIAITCDDoacao(ps, giaITCDDoacaoVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				GIAITCDDoacaoVo giaITCDDoacaoAtualVo = new GIAITCDDoacaoVo();
				getGIAITCDDoacao(rs, giaITCDDoacaoAtualVo);
				giaITCDDoacaoVo.getCollVO().add(giaITCDDoacaoAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_GIA_ITCD_DOACAO);
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
		return giaITCDDoacaoVo;
	}
}
