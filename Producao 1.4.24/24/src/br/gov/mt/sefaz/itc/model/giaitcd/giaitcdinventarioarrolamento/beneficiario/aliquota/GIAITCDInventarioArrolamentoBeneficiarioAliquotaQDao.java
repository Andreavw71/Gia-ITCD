package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe de acesso a dados (Data Acces Object)
 * @author Lucas Nascimento
 */
public class GIAITCDInventarioArrolamentoBeneficiarioAliquotaQDao extends AbstractDao implements CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota, TabelasITC
{
	/**
	 * Contrutor padrão recebendo uma Conexão
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioAliquotaQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Configura um objeto do tipo <code>GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo</code> com os valores
	 * recebidos no <code>ResultSet</code>
	 *
	 * @param rs            ResultSet
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo  objeto a ser configurado
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 */
	private void getGIAITCDInventarioArrolamentoBeneficiarioAliquota(final ResultSet rs, final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getGiaITCDIventarioArrolamentoBeneficiarioVo().setCodigo(rs.getLong(CAMPO_CODIGO_BENEFICIARIO));
		giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setCodigoAliquota(rs.getLong(CAMPO_CODIGO_ALIQUOTA));
		giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setPercentualAliquota(rs.getDouble(CAMPO_PERCENTUAL_ALIQUOTA));
		giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setValorRecolher(rs.getDouble(CAMPO_VALOR_RECOLHER));
		giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setValorBaseCalculo(rs.getDouble(CAMPO_BASE_CALCULO));
	}

	/**
	 * Encontra "um" determinado Beneficiario do tipo Inventario e Arrolamento
	 *
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo findGIAITCDInventarioArrolamentoBeneficiarioAliquota(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = 
	  conn.prepareStatement(getSQLFindGIAITCDInventarioArrolamentoBeneficiarioAliquota(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo));
			prepareStatementFindGIAITCDInventarioArrolamentoBeneficiarioAliquota(ps, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getGIAITCDInventarioArrolamentoBeneficiarioAliquota(rs, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTA_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA);
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
		return giaITCDInventarioArrolamentoBeneficiarioAliquotaVo;
	}

	/**
	 * Encontra "um" determinado Beneficiario do tipo Inventario e Arrolamento
	 *
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo listGIAITCDInventarioArrolamentoBeneficiarioAliquota(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = 
	  conn.prepareStatement(getSQLFindGIAITCDInventarioArrolamentoBeneficiarioAliquota(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo));
			prepareStatementFindGIAITCDInventarioArrolamentoBeneficiarioAliquota(ps, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
			   GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquotaAtual = new GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo();
				getGIAITCDInventarioArrolamentoBeneficiarioAliquota(rs, aliquotaAtual);
				giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getCollVO().add(aliquotaAtual);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTA_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA);
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
		return giaITCDInventarioArrolamentoBeneficiarioAliquotaVo;
	}

	/**
	 * Método responsável por retornar a String de Consulta (SELECT) SQL 
	 * 
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	private String getSQLFindGIAITCDInventarioArrolamentoBeneficiarioAliquota(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT G.").append(CAMPO_CODIGO_BENEFICIARIO).append(" ");
		sql.append(" , G.").append(CAMPO_CODIGO_ALIQUOTA).append(" ");
		sql.append(" , G.").append(CAMPO_PERCENTUAL_ALIQUOTA).append(" ");
		sql.append(" , G.").append(CAMPO_VALOR_RECOLHER).append(" ");
		sql.append(" , G.").append(CAMPO_BASE_CALCULO).append(" ");
		sql.append(" FROM ").append(TABELA_GIA_ITCD_IVENTARIO_BENEFICIARIO_ALIQUOTA).append(" G ");
		sql.append(" WHERE 1 = 1 ");
		if (giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.isConsultaParametrizada())
		{
			// CAMPO_CODIGO_BENEFICIARIO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getGiaITCDIventarioArrolamentoBeneficiarioVo().getCodigo()))
			{
				sql.append("   AND G.").append(CAMPO_CODIGO_BENEFICIARIO).append(" = ? ");
			}
			//CAMPO_CODIGO_ALIQUOTA
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getCodigoAliquota()))
			{
				sql.append("   AND G.").append(CAMPO_CODIGO_ALIQUOTA).append(" = ? ");
			}
			//CAMPO_PERCENTUAL_ALIQUOTA
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getPercentualAliquota()))
			{
				sql.append("   AND G.").append(CAMPO_PERCENTUAL_ALIQUOTA).append(" = ? ");
			}
			//CAMPO_VALOR_RECOLHER
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getValorRecolher()))
			{
				sql.append("   AND G.").append(CAMPO_VALOR_RECOLHER).append(" = ? ");
			}
			//CAMPO_BASE_CALCULO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getValorBaseCalculo()))
			{
				sql.append("   AND G.").append(CAMPO_BASE_CALCULO).append(" = ? ");
			}
		}
		sql.append(" ORDER BY G.").append(CAMPO_CODIGO_BENEFICIARIO).append(" ");
		return sql.toString();
	}

	/**
	 * Método responável por adicionar os parâmetros válidos na instrução SQL (java.sql.PreparedStatement)
	 * @param ps (java.sql.PreparedStatement)
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo (Value Object)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Lucas Nascimento
	 */
	private void prepareStatementFindGIAITCDInventarioArrolamentoBeneficiarioAliquota(final PreparedStatement ps, final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		int contador = 0;
		if (giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.isConsultaParametrizada())
		{
			// CAMPO_CODIGO_BENEFICIARIO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getGiaITCDIventarioArrolamentoBeneficiarioVo().getCodigo()))
			{
				ps.setLong(++contador, ((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getGiaITCDIventarioArrolamentoBeneficiarioVo().getCodigo());
			}
			//CAMPO_CODIGO_ALIQUOTA
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getCodigoAliquota()))
			{
				ps.setDouble(++contador, ((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getCodigoAliquota());
			}
			//CAMPO_PERCENTUAL_ALIQUOTA
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getPercentualAliquota()))
			{
				ps.setDouble(++contador, ((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getPercentualAliquota());
			}
			//CAMPO_VALOR_RECOLHER
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getValorRecolher()))
			{
				ps.setDouble(++contador, ((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getValorRecolher());
			}
			//CAMPO_BASE_CALCULO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getValorBaseCalculo()))
			{
				ps.setDouble(++contador, ((GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getParametroConsulta()).getValorBaseCalculo());
			}
		}
	}
}
