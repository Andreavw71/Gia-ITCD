/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDInventarioArrolamentoBeneficiarioQDao.java
 * Revisão:
 * Data revisão:
 * $Id: GIAITCDInventarioArrolamentoBeneficiarioQDao.java,v 1.2 2009/03/13 14:15:29 ricardo.moraes Exp $
 */
package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDInventarioArrolamentoBeneficiario;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author  Leandro Dorileo
 * @version $Revision: 1.2 $
 */
public class GIAITCDInventarioArrolamentoBeneficiarioQDao extends AbstractDao implements CamposGIAITCDInventarioArrolamentoBeneficiario, TabelasITC
{

	/**
	 * Construtor público recebendo conexao
	 * @param conexao		conexao a ser usada
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Configura um objeto do tipo <code>GIAITCDInventarioArrolamentoBeneficiarioVo</code> com os valores
	 * recebidos no <code>ResultSet</code>
	 *
	 * @param rs				ResultSet
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo	objeto a ser configurado
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 */
	private void getGIAITCDInventarioArrolamentoBeneficiario(final ResultSet rs, final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		giaITCDInventarioArrolamentoBeneficiarioVo.setCodigo(rs.getLong(CAMPO_ITCTB05_CODIGO_BENEFICIARIO));
		giaITCDInventarioArrolamentoBeneficiarioVo.setInfoQuantidadeUpf(rs.getInt(CAMPO_INFO_QTD_UPF));
		giaITCDInventarioArrolamentoBeneficiarioVo.setValorRecolher(rs.getDouble(CAMPO_VALR_RECOLHER));
		giaITCDInventarioArrolamentoBeneficiarioVo.setFlagBeneficiarioMeeiro(new DomnSimNao(rs.getInt(CAMPO_FLAG_BENEF_MEEIRO)));
		giaITCDInventarioArrolamentoBeneficiarioVo.setValorITCDBeneficiario(rs.getDouble(CAMPO_VALOR_ITCD_BENEFICIARIO));
		giaITCDInventarioArrolamentoBeneficiarioVo.setValorMultaRecolher(rs.getDouble(CAMPO_VALOR_MULTA_RECOLHER));
	   giaITCDInventarioArrolamentoBeneficiarioVo.setInfoDispensaRecolhimento(rs.getInt(CAMPO_INFO_DSPE_RCLH));
	   giaITCDInventarioArrolamentoBeneficiarioVo.setInfoIsencao(rs.getInt(CAMPO_INFO_ISENCAO));
	}

	/**
	 * Encontra "um" determinado Beneficiario do tipo Inventario e Arrolamento
	 *
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioVo findGIAITCDInventarioArrolamentoGIAITCDBeneficiario(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = 
	  conn.prepareStatement(getSQLFindGIAITCDInventarioArrolamentoBeneficiario(giaITCDInventarioArrolamentoBeneficiarioVo));
			prepareStatementFindGIAITCDInventarioArrolamentoBeneficiario(ps, giaITCDInventarioArrolamentoBeneficiarioVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getGIAITCDInventarioArrolamentoBeneficiario(rs, giaITCDInventarioArrolamentoBeneficiarioVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_INVENTARIO_ARROMANENTO_BENEFICIARIO);
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
		return giaITCDInventarioArrolamentoBeneficiarioVo;
	}

	private void prepareStatementFindGIAITCDInventarioArrolamentoBeneficiario(final PreparedStatement ps, final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		int contador = 0;
		if (giaITCDInventarioArrolamentoBeneficiarioVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getCodigo());
			}
			//INFO QUANTIDADE UPD
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getInfoQuantidadeUpf()))
			{
				ps.setInt(++contador, ((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getInfoQuantidadeUpf());
			}
			//VALOR RECOLHER
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getValorRecolher()))
			{
				ps.setDouble(++contador, ((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getValorRecolher());
			}
			//PERC BEM RECEBIDO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getPercentualBemRecebido()))
			{
				ps.setDouble(++contador, ((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getPercentualBemRecebido());
			}
		}
	}

	private String getSQLFindGIAITCDInventarioArrolamentoBeneficiario(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT GIA.").append(CAMPO_ITCTB05_CODIGO_BENEFICIARIO).append(" ");
		sql.append(" , GIA.").append(CAMPO_INFO_QTD_UPF).append(" ");
		sql.append(" , GIA.").append(CAMPO_VALR_RECOLHER).append(" ");
		sql.append(" , GIA.").append(CAMPO_FLAG_BENEF_MEEIRO).append(" ");
		sql.append(" , GIA.").append(CAMPO_VALOR_ITCD_BENEFICIARIO).append(" ");
		sql.append(" , GIA.").append(CAMPO_VALOR_MULTA_RECOLHER).append(" ");	
	   sql.append(" , GIA.").append(CAMPO_INFO_ISENCAO).append(" ");  
	   sql.append(" , GIA.").append(CAMPO_INFO_DSPE_RCLH).append(" ");
		sql.append(" FROM ").append(TABELA_GIA_ITCD_INVENTARIO_BENEFICIARIO).append(" GIA ");
		sql.append(" WHERE 1 = 1 ");
		if (giaITCDInventarioArrolamentoBeneficiarioVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND GIA.").append(CAMPO_ITCTB05_CODIGO_BENEFICIARIO).append(" = ? ");
			}
			//INFO QUANTIDADE UPF
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getInfoQuantidadeUpf()))
			{
				sql.append("   AND GIA.").append(CAMPO_INFO_QTD_UPF).append(" = ? ");
			}
			//VALOR RECOLHER
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getValorRecolher()))
			{
				sql.append("   AND GIA.").append(CAMPO_VALR_RECOLHER).append(" = ? ");
			}
		}
		sql.append(" ORDER BY GIA.").append(CAMPO_ITCTB05_CODIGO_BENEFICIARIO).append(" ");
		return sql.toString();
	}

	public GIAITCDInventarioArrolamentoBeneficiarioVo listGIAITCDInventarioArrolamentoBeneficiario(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = 
	  conn.prepareStatement(getSQLFindListInventarioArrolamentoBeneficiario(giaITCDInventarioArrolamentoBeneficiarioVo));
			prepareStatementListGIAITCDInventarioArrolamentoBeneficiario(ps, giaITCDInventarioArrolamentoBeneficiarioVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				getGIAITCDInventarioArrolamentoBeneficiario(rs, giaITCDInventarioArrolamentoBeneficiarioVo);
				giaITCDInventarioArrolamentoBeneficiarioVo.getCollVO().add(giaITCDInventarioArrolamentoBeneficiarioVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_INVENTARIO_ARROMANENTO_BENEFICIARIO);
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
		return giaITCDInventarioArrolamentoBeneficiarioVo;
	}

	/**
	 * Monta o Prepare Statement para listagem de GIA-ITCD Inventário Arrolamento
	 * @param ps
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementListGIAITCDInventarioArrolamentoBeneficiario(final PreparedStatement ps, final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		int contador = 0;
		if (giaITCDInventarioArrolamentoBeneficiarioVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getCodigo());
			}
			//INFO QUANTIDADE UPF
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getInfoQuantidadeUpf()))
			{
				ps.setInt(++contador, ((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getInfoQuantidadeUpf());
			}
			//VALOR RECOLHER
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getValorRecolher()))
			{
				ps.setDouble(++contador, ((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getValorRecolher());
			}
			//PERC BEM RECEBIDO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getPercentualBemRecebido()))
			{
				ps.setDouble(++contador, ((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getPercentualBemRecebido());
			}
		}
	}

	/**
	 * Monta o SQL de Consulta e Listagem de GIA-ITCD
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindListInventarioArrolamentoBeneficiario(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT GIA.").append(CAMPO_ITCTB05_CODIGO_BENEFICIARIO).append(" ");
		sql.append(" , GIA.").append(CAMPO_INFO_QTD_UPF).append(" ");
		sql.append(" , GIA.").append(CAMPO_VALR_RECOLHER).append(" ");
	   sql.append(" , GIA.").append(CAMPO_FLAG_BENEF_MEEIRO).append(" ");
	   sql.append(" , GIA.").append(CAMPO_VALOR_ITCD_BENEFICIARIO).append(" ");
	   sql.append(" , GIA.").append(CAMPO_VALOR_MULTA_RECOLHER).append(" ");      		
		sql.append(" FROM ").append(TABELA_GIA_ITCD_INVENTARIO_BENEFICIARIO).append(" GIA ");
		sql.append(" WHERE 1 = 1 ");
		if (giaITCDInventarioArrolamentoBeneficiarioVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND GIA.").append(CAMPO_ITCTB05_CODIGO_BENEFICIARIO).append(" = ? ");
			}
			//INFO QUANTIDADE UPD
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getInfoQuantidadeUpf()))
			{
				sql.append("   AND GIA.").append(CAMPO_INFO_QTD_UPF).append(" = ? ");
			}
			//VALOR RECOLHER
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioVo) giaITCDInventarioArrolamentoBeneficiarioVo.getParametroConsulta()).getValorRecolher()))
			{
				sql.append("   AND GIA.").append(CAMPO_VALR_RECOLHER).append(" = ? ");
			}
		}
		sql.append(" ORDER BY GIA.").append(CAMPO_ITCTB05_CODIGO_BENEFICIARIO).append(" ");
		return sql.toString();
	}
}
