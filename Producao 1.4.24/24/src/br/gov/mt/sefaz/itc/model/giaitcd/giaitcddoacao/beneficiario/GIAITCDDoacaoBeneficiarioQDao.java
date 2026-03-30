package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBeneficiario;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDDoacaoBeneficiario;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe para Consulta de GIA-ITCD Doação Beneficiário
 * @author Daniel Balieiro
 * @implemented by Daniel Balieiro
 */
public class GIAITCDDoacaoBeneficiarioQDao extends AbstractDao implements CamposGIAITCDDoacaoBeneficiario, CamposBeneficiario, TabelasITC
{
	/**
	 * Construtor que recebe a Conexão com o banco de dados
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBeneficiarioQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Monta o Vo com os dados do Result Set
	 * @param rs
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void getGIAITCDDoacaoBeneficiario(final ResultSet rs, final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		giaITCDDoacaoBeneficiarioVo.setCodigo(rs.getLong(CAMPO_ITCTB05_CODIGO_BENEFICIARIO));
		giaITCDDoacaoBeneficiarioVo.setPercentualAliquota(rs.getDouble(CAMPO_PERCENTUAL_ALIQUOTA));
		giaITCDDoacaoBeneficiarioVo.setPercentualBemRecebido(rs.getDouble(CAMPO_PERCENTUAL_BEM_RECEBIDO));
		giaITCDDoacaoBeneficiarioVo.setValorRecolher(rs.getDouble(CAMPO_VALOR_RECOLHER));
	   giaITCDDoacaoBeneficiarioVo.setInfoDispensaRecolhimento(rs.getInt(CAMPO_INFO_DSPE_RCLH));
	   giaITCDDoacaoBeneficiarioVo.setInfoIsencao(rs.getInt(CAMPO_INFO_ISENCAO));
	}

	/**
	 * Método que efetua a consulta de uma GIA-ITCD Doação Beneficiário
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBeneficiarioVo findGIAITCDDoacaoGIAITCDDoacaoBeneficiario(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindGIAITCDDoacaoBeneficiario(giaITCDDoacaoBeneficiarioVo));
			prepareStatementFindGIAITCDDoacaoBeneficiario(ps, giaITCDDoacaoBeneficiarioVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getGIAITCDDoacaoBeneficiario(rs, giaITCDDoacaoBeneficiarioVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_DOACAO_BENEFICIARIO);
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
		return giaITCDDoacaoBeneficiarioVo;
	}

	/**
	 * Monta o Prepared Statement de acordo com os dados válidos do Vo
	 * @param ps
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindGIAITCDDoacaoBeneficiario(final PreparedStatement ps, final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		int contador = 0;
		if (giaITCDDoacaoBeneficiarioVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getCodigo());
			}
			// PERCENTUAL ALIQUOTA
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getPercentualAliquota()))
			{
				ps.setDouble(++contador, ((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getPercentualAliquota());
			}
			// PERCENTUAL BEM RECEBIDO
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getPercentualBemRecebido()))
			{
				ps.setDouble(++contador, ((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getPercentualBemRecebido());
			}
			// VALOR RECOLHER
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getValorRecolher()))
			{
				ps.setDouble(++contador, ((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getValorRecolher());
			}
		}
	}

	/**
	 * Monta o SQL de consulta de GIA-ITCD Doação Beneficiário
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindGIAITCDDoacaoBeneficiario(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT GIA.").append(CAMPO_ITCTB05_CODIGO_BENEFICIARIO).append(" ");
		sql.append(" , GIA.").append(CAMPO_PERCENTUAL_ALIQUOTA).append(" ");
		sql.append(" , GIA.").append(CAMPO_PERCENTUAL_BEM_RECEBIDO).append(" ");
		sql.append(" , GIA.").append(CAMPO_VALOR_RECOLHER).append(" ");
	   sql.append(" , GIA.").append(CAMPO_INFO_DSPE_RCLH).append(" ");
	   sql.append(" , GIA.").append(CAMPO_INFO_ISENCAO).append(" ");
		sql.append(" FROM ").append(TABELA_GIA_ITCD_DOACAO_BENEFICIARIO).append(" GIA ");
		sql.append(" WHERE 1 = 1 ");
		if (giaITCDDoacaoBeneficiarioVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND GIA.").append(CAMPO_ITCTB05_CODIGO_BENEFICIARIO).append(" = ? ");
			}
			// PERCENTUAL ALIQUOTA
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getPercentualAliquota()))
			{
				sql.append("   AND GIA.").append(CAMPO_PERCENTUAL_ALIQUOTA).append(" = ? ");
			}
			// PERCENTUAL BEM RECEBIDO
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getPercentualBemRecebido()))
			{
				sql.append("   AND GIA.").append(CAMPO_PERCENTUAL_BEM_RECEBIDO).append(" = ? ");
			}
			// VALOR RECOLHER
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getValorRecolher()))
			{
				sql.append("   AND GIA.").append(CAMPO_VALOR_RECOLHER).append(" = ? ");
			}
		}
		sql.append(" ORDER BY GIA.").append(CAMPO_ITCTB05_CODIGO_BENEFICIARIO).append(" ");
		return sql.toString();
	}

	/**
	 * Método que efetua uma listagem de GIA-ITCD Doação Beneficiário
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBeneficiarioVo listGIAITCDDoacaoBeneficiario(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListGIAITCDDoacaoBeneficiario(giaITCDDoacaoBeneficiarioVo));
			prepareStatementListGIAITCDDoacaoBeneficiario(ps, giaITCDDoacaoBeneficiarioVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioAtualVo = new GIAITCDDoacaoBeneficiarioVo();
				getGIAITCDDoacaoBeneficiario(rs, giaITCDDoacaoBeneficiarioAtualVo);
				giaITCDDoacaoBeneficiarioVo.getCollVO().add(giaITCDDoacaoBeneficiarioAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_DOACAO_BENEFICIARIO);
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
		return giaITCDDoacaoBeneficiarioVo;
	}

	/**
	 * Monta o Prepared Statement com os valores válidos do Vo
	 * @param ps
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementListGIAITCDDoacaoBeneficiario(final PreparedStatement ps, final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		int contador = 0;
		if (giaITCDDoacaoBeneficiarioVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getCodigo());
			}
			// PERCENTUAL ALIQUOTA
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getPercentualAliquota()))
			{
				ps.setDouble(++contador, ((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getPercentualAliquota());
			}
			// PERCENTUAL BEM RECEBIDO
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getPercentualBemRecebido()))
			{
				ps.setDouble(++contador, ((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getPercentualBemRecebido());
			}
			// VALOR RECOLHER
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getValorRecolher()))
			{
				ps.setDouble(++contador, ((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getValorRecolher());
			}
			//BENEFICIARIO
			 if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getCodigo()))
			 {
			    ps.setLong(++contador, ((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getCodigo());
			 }       
			 if(Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
			 {
			    ps.setLong(++contador, ((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getGiaITCDVo().getCodigo());
			 }
		}
	}

	/**
	 * Monta o SQL de listagem da GIA-ITCD Doação Beneficiário
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListGIAITCDDoacaoBeneficiario(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT GIA.").append(CAMPO_ITCTB05_CODIGO_BENEFICIARIO).append(" ");
		sql.append(" , GIA.").append(CAMPO_PERCENTUAL_ALIQUOTA).append(" ");
		sql.append(" , GIA.").append(CAMPO_PERCENTUAL_BEM_RECEBIDO).append(" ");
		sql.append(" , GIA.").append(CAMPO_VALOR_RECOLHER).append(" ");
		sql.append(" FROM ").append(TABELA_GIA_ITCD_DOACAO_BENEFICIARIO).append(" GIA ");
		if(Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
		{
		   sql.append(" INNER JOIN ");
		   sql.append(TABELA_BENEFICIARIO + " BENEFICIARIO ");
		   sql.append(" ON ");
		   sql.append(" GIA." + CamposGIAITCDDoacaoBeneficiario.CAMPO_ITCTB05_CODIGO_BENEFICIARIO);
		   sql.append(" = ");
		   sql.append(" BENEFICIARIO." + CamposBeneficiario.CAMPO_CODIGO_BENEFICIARIO);			
		}
		sql.append(" WHERE 1 = 1 ");
		if (giaITCDDoacaoBeneficiarioVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND GIA.").append(CAMPO_ITCTB05_CODIGO_BENEFICIARIO).append(" = ? ");
			}
			// PERCENTUAL ALIQUOTA
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getPercentualAliquota()))
			{
				sql.append("   AND GIA.").append(CAMPO_PERCENTUAL_ALIQUOTA).append(" = ? ");
			}
			// PERCENTUAL BEM RECEBIDO
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getPercentualBemRecebido()))
			{
				sql.append("   AND GIA.").append(CAMPO_PERCENTUAL_BEM_RECEBIDO).append(" = ? ");
			}
			// VALOR RECOLHER
			if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getValorRecolher()))
			{
				sql.append("   AND GIA.").append(CAMPO_VALOR_RECOLHER).append(" = ? ");
			}
			//BENEFICIARIO
			 if (Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getCodigo()))
			 {
			    sql.append(" AND BENEFICIARIO." + CamposBeneficiario.CAMPO_CODIGO_BENEFICIARIO + " = ? ");
			 }			
			 if(Validador.isNumericoValido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoBeneficiarioVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
			 {
				 sql.append(" AND BENEFICIARIO." + CamposBeneficiario.CAMPO_ITCTB14_CODIGO_GIA_ITCD + " = ?");
			 }
		}
		sql.append(" ORDER BY GIA.").append(CAMPO_ITCTB05_CODIGO_BENEFICIARIO).append(" ");
		return sql.toString();
	}
}
