package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDSeparacaoDivorcio;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe para manipular consultas da GIA ITCD Separação Divórcio no banco de dados
 * 
 * @author Thiago de Castilho Pacheco
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.2 $
 */
public class GIAITCDSeparacaoDivorcioQDao extends AbstractDao implements TabelasITC, CamposGIAITCDSeparacaoDivorcio
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 * @implemented by Marlo Eichenberg Motta
	 */
	public GIAITCDSeparacaoDivorcioQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metódo que constroi uma GIAITCDSeparacaoDivorcioVo com os dados do ResultSet
	 * 
	 * @param rs
	 * @param giaITCDSeparacaoDivorcioVO
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void getGIAITCDSeparacaoDivorcio(final ResultSet rs, final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVO) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVO);
		giaITCDSeparacaoDivorcioVO.setCodigo(rs.getLong(CAMPO_ITCBTB14_CODIGO_ITCD));
		giaITCDSeparacaoDivorcioVO.setRegimeCasamento(new DomnRegimeCasamento(rs.getInt(CAMPO_REGIME_CASAMENTO)));
		giaITCDSeparacaoDivorcioVO.setNumeroProcesso(rs.getLong(CAMPO_NUMERO_PROCESSO));
		giaITCDSeparacaoDivorcioVO.setDataSeparacao(rs.getDate(CAMPO_DATA_SEPARACAO));
		giaITCDSeparacaoDivorcioVO.getPessoaConjuge1().setNumrContribuinte(new Long(rs.getLong(CAMPO_PESSOA_CONJUGE1)));
		giaITCDSeparacaoDivorcioVO.getPessoaConjuge2().setNumrContribuinte(new Long(rs.getLong(CAMPO_PESSOA_CONJUGE2)));
		giaITCDSeparacaoDivorcioVO.setValorAliquota(new Double(rs.getDouble(CAMPO_VALOR_ALIQUOTA)).doubleValue());
		giaITCDSeparacaoDivorcioVO.setValorIncidencia(new Double(rs.getDouble(CAMPO_VALOR_INCIDENCIA)).doubleValue());
		giaITCDSeparacaoDivorcioVO.setDataCasamento(rs.getDate(CAMPO_DATA_CASAMENTO));
	}

	/**
	 * Método para montar a SQL de consulta de uma GIA ITCD Separação Divócio
	 * 
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 * @return String
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String getSQLGIAITCDSeparacaoDivorcio(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT ");
		sql.append(" GIA." + CAMPO_ITCBTB14_CODIGO_ITCD + " ");
		sql.append(" , GIA." + CAMPO_REGIME_CASAMENTO + " ");
		sql.append(" , GIA." + CAMPO_NUMERO_PROCESSO + " ");
		sql.append(" , GIA." + CAMPO_DATA_SEPARACAO + " ");
		sql.append(" , GIA." + CAMPO_PESSOA_CONJUGE1 + " ");
		sql.append(" , GIA." + CAMPO_PESSOA_CONJUGE2 + " ");
		sql.append(" , GIA." + CAMPO_VALOR_ALIQUOTA + " ");
		sql.append(" , GIA." + CAMPO_VALOR_TOTAL_CONJUGE1 + " ");
		sql.append(" , GIA." + CAMPO_VALOR_TOTAL_CONJUGE2 + " ");
		sql.append(" , GIA." + CAMPO_VALOR_INCIDENCIA + " ");
		sql.append(" , GIA." + CAMPO_DATA_CASAMENTO + " ");
		sql.append(" FROM " + TABELA_GIA_ITCD_SEPARACAO_DIVORCIO + " GIA ");
		sql.append(" WHERE 1 = 1 ");
		if (giaITCDSeparacaoDivorcioVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND GIA." + CAMPO_ITCBTB14_CODIGO_ITCD + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getRegimeCasamento()))
			{
				sql.append(" AND GIA." + CAMPO_REGIME_CASAMENTO + " = ? ");
			}
			if (Validador.isNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getNumeroProcesso()))
			{
				sql.append(" AND GIA." + CAMPO_NUMERO_PROCESSO + " = ? ");
			}
			if (Validador.isDataValida(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getDataSeparacao()))
			{
				sql.append(" AND GIA." + CAMPO_DATA_SEPARACAO + " = ? ");
			}
			if (Validador.isNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getPessoaConjuge1().getNumrContribuinte()))
			{
				sql.append(" AND GIA." + CAMPO_PESSOA_CONJUGE1 + " = ? ");
			}
			if (Validador.isNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getPessoaConjuge2().getNumrContribuinte()))
			{
				sql.append(" AND GIA." + CAMPO_PESSOA_CONJUGE2 + " = ? ");
			}
			if (Validador.isNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getValorAliquota()))
			{
				sql.append(" AND GIA." + CAMPO_VALOR_ALIQUOTA + " = ? ");
			}
			if (Validador.isNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getValorIncidencia()))
			{
				sql.append(" AND GIA." + CAMPO_VALOR_INCIDENCIA + " = ? ");
			}
			if(Validador.isDataValida(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getDataCasamento()))
			{
				sql.append(" AND GIA." + CAMPO_DATA_CASAMENTO + " = ? ");
			}
		}
		sql.append(" ORDER BY GIA." + CAMPO_ITCBTB14_CODIGO_ITCD + " ");
		return sql.toString();
	}

	/**
	 * Método que monta o PreparedStatement de acordo com os dados válidos da GIAITCDSeparacaoDivorcioVo
	 * 
	 * @param ps
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void prepareStatementGIAITCDSeparacaoDivorcio(final PreparedStatement ps, final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		int contador = 0;
		if (giaITCDSeparacaoDivorcioVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isDominioNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getRegimeCasamento()))
			{
				ps.setInt(++contador, ((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getRegimeCasamento().getValorCorrente());
			}
			if (Validador.isNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getNumeroProcesso()))
			{
				ps.setLong(++contador, ((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getNumeroProcesso());
			}
			if (Validador.isDataValida(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getDataSeparacao()))
			{
				ps.setDate(++contador, new java.sql.Date(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getDataSeparacao().getTime()));
			}
			if (Validador.isNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getPessoaConjuge1().getNumrContribuinte()))
			{
				ps.setLong(++contador, ((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getPessoaConjuge1().getNumrContribuinte().longValue());
			}
			if (Validador.isNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getPessoaConjuge2().getNumrContribuinte()))
			{
				ps.setLong(++contador, ((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getPessoaConjuge2().getNumrContribuinte().longValue());
			}
			if (Validador.isNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getValorAliquota()))
			{
				ps.setDouble(++contador, ((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getValorAliquota());
			}
			if (Validador.isNumericoValido(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getValorIncidencia()))
			{
				ps.setDouble(++contador, ((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getValorIncidencia());
			}
			if(Validador.isDataValida(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getDataCasamento()))
			{
				ps.setDate(++contador, new java.sql.Date(((GIAITCDSeparacaoDivorcioVo) giaITCDSeparacaoDivorcioVo.getParametroConsulta()).getDataCasamento().getTime()));
			}
		}
	}

	/**
	 * Método de consulta de uma GIA ITCD Separação Divórcio. <br>
	 * Caso mais de uma GIA ITCD Separação Divórcio seja encontrada, será retornada a de menor código.<br><br>
	 * Os dados possíveis para consulta são:<br>
	 * <b>- giaITCDSeparacaoDivorcioVo.codigo</b><br>
	 * <b>- giaITCDSeparacaoDivorcioVo.regimeCasamento</b><br>
	 * <b>- giaITCDSeparacaoDivorcioVo.numeroProcesso</b><br>
	 * <b>- giaITCDSeparacaoDivorcioVo.dataSeparacao </b><br>
	 * <b>- giaITCDSeparacaoDivorcioVo.valorPorConjuge </b><br>
	 * 
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return GIAITCDSeparacaoDivorcioVo
	 * @implemented by Thiago de Castilho Pacheco
	 * @implemented by Marlo Eichenberg Motta
	 */
	public GIAITCDSeparacaoDivorcioVo findGIAITCDSeparacaoDivorcio(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLGIAITCDSeparacaoDivorcio(giaITCDSeparacaoDivorcioVo));
			prepareStatementGIAITCDSeparacaoDivorcio(ps, giaITCDSeparacaoDivorcioVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getGIAITCDSeparacaoDivorcio(rs, giaITCDSeparacaoDivorcioVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_SEPARACAO_DIVORCIO);
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
		return giaITCDSeparacaoDivorcioVo;
	}

	/**
	 * Método para listar uma GIA ITCD Separação Divórcio. <br>
	 * Os dados possíveis para consulta são:<br>
	 * <b>- giaITCDSeparacaoDivorcioVo.codigo</b><br>
	 * <b>- giaITCDSeparacaoDivorcioVo.regimeCasamento</b><br>
	 * <b>- giaITCDSeparacaoDivorcioVo.numeroProcesso</b><br>
	 * <b>- giaITCDSeparacaoDivorcioVo.dataSeparacao </b><br>
	 * <b>- giaITCDSeparacaoDivorcioVo.valorPorConjuge </b><br>
	 * 
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return GIAITCDSeparacaoDivorcioVo
	 * @implemented by Thiago de Castilho Pacheco
	 * @implemented by Marlo Eichenberg Motta
	 */
	public GIAITCDSeparacaoDivorcioVo listGIAITCDSeparacaoDivorcio(GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLGIAITCDSeparacaoDivorcio(giaITCDSeparacaoDivorcioVo));
			prepareStatementGIAITCDSeparacaoDivorcio(ps, giaITCDSeparacaoDivorcioVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVoAtual = new GIAITCDSeparacaoDivorcioVo();
				getGIAITCDSeparacaoDivorcio(rs, giaITCDSeparacaoDivorcioVoAtual);
				giaITCDSeparacaoDivorcioVo.getCollVO().add(giaITCDSeparacaoDivorcioVoAtual);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_GIA_ITCD_SEPARACAO_DIVORCIO);
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
		return giaITCDSeparacaoDivorcioVo;
	}
}
