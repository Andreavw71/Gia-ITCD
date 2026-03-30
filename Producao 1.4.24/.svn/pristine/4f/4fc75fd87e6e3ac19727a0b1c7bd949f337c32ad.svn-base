/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDDarQDao.java
 * Revisão:
 * Data revisão:
 * $Id: GIAITCDDarQDao.java,v 1.2 2008/07/30 21:27:27 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.giaitcddar;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDDar;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe de consulta/listagem de dados para a tabela de ligação de uma GIAITCD com um DAR(Documento de Arrecadação).
 * @author Leandro Dorileo
 */
public class GIAITCDDarQDao extends AbstractDao implements CamposGIAITCDDar, TabelasITC, SequencesITC
{

	/**
	 * Construtor que recebe a Conexão com o Banco de Dados.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Este metodo recebe um <b>ResultSet</b> e configura um GIAITCDDarVo com os valores
	 * recebidos.
	 *
	 * @param rs					resultado da consulta
	 * @param giaITCDDarVo		objeto do tipo GIAITCDDarVo a ser configurado com os valores do ResultSet
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 */
	private GIAITCDDarVo getGIAITCDar(final ResultSet rs, final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(giaITCDDarVo);
		giaITCDDarVo.setCodigo(rs.getLong(CAMPO_CODG_ITCD_DAR));
		giaITCDDarVo.getGia().setCodigo(rs.getLong(CAMPO_CODG_ITCD));
		giaITCDDarVo.getDarEmitido().setNumrDarSeqc(new Long(rs.getLong(CAMPO_NUMR_DAR_SEQUENCIAL)));
		giaITCDDarVo.setSubstituido(new DomnSimNao(rs.getInt(CAMPO_FLAG_SUBSTITUIDO)));
		giaITCDDarVo.setDataAtualizacao(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
		giaITCDDarVo.setNumeroParcela(rs.getInt(CAMPO_NUMR_PARCELA_DAR));
		giaITCDDarVo.getStatusGIAITCDVo().setCodigo(rs.getLong(CAMPO_CODG_STATUS_GIA_ITCD));
		return giaITCDDarVo;
	}

	/**
	 * Este metodo constroi o sql da consulta de acordo com os parametros informados
	 *
	 * @param giaITCDDarVo				parametros da consulta
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 */
	private String getSQLFindGIAITCDDar(final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(giaITCDDarVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT GIADAR.").append(CAMPO_CODG_ITCD_DAR);
		sql.append(" , GIADAR.").append(CAMPO_CODG_ITCD);
		sql.append(" , GIADAR.").append(CAMPO_NUMR_DAR_SEQUENCIAL);
		sql.append(" , GIADAR.").append(CAMPO_FLAG_SUBSTITUIDO);
		sql.append(" , GIADAR.").append(CAMPO_DATA_ATUALIZACAO_BD);
	   sql.append(" , GIADAR.").append(CAMPO_NUMR_PARCELA_DAR);
	   sql.append(" , GIADAR.").append(CAMPO_CODG_STATUS_GIA_ITCD);
		sql.append(" FROM ").append(TABELA_GIA_ITCD_DAR).append(" GIADAR ");
		sql.append(" WHERE 1 = 1 ");
		if (giaITCDDarVo.isConsultaParametrizada())
		{
			// CHAVE PRIMARIA
			if (Validador.isNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getCodigo()))
				sql.append("   AND GIADAR.").append(CAMPO_CODG_ITCD_DAR).append(" = ? ");
			// CODIGO DA GIA
			if (Validador.isNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getGia().getCodigo()))
				sql.append("   AND GIADAR.").append(CAMPO_CODG_ITCD).append(" = ? ");
			// NUMERO DO DAR
			if (Validador.isNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getDarEmitido().getNumrDarSeqc()))
				sql.append("   AND GIADAR.").append(CAMPO_NUMR_DAR_SEQUENCIAL).append(" = ? ");
			// FLAG SUBSTITUIDO
			if (Validador.isDominioNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getSubstituido()))
				sql.append("   AND GIADAR.").append(CAMPO_FLAG_SUBSTITUIDO).append(" = ? ");
			if (Validador.isNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getStatusGIAITCDVo().getCodigo()))
				sql.append("   AND GIADAR.").append(CAMPO_CODG_STATUS_GIA_ITCD).append(" = ? ");
		}
		sql.append(" ORDER BY GIADAR." + CAMPO_CODG_ITCD_DAR + " ");
		return sql.toString();
	}

	/**
	 * Este metodo constroi o sql para a consulta do ultimo dar gerado para uma determinada gia
	 *
	 * @param giaITCDDarVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 */
	private String getSQLFindUltimoGIAITCDDar(final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT GIADAR.").append(CAMPO_CODG_ITCD_DAR);
		sql.append(" , GIADAR.").append(CAMPO_CODG_ITCD);
		sql.append(" , GIADAR.").append(CAMPO_NUMR_DAR_SEQUENCIAL);
		sql.append(" , GIADAR.").append(CAMPO_FLAG_SUBSTITUIDO);
		sql.append(" , GIADAR.").append(CAMPO_DATA_ATUALIZACAO_BD);
	   sql.append(" , GIADAR.").append(CAMPO_NUMR_PARCELA_DAR);
		sql.append(" , GIADAR.").append(CAMPO_CODG_STATUS_GIA_ITCD);		
		sql.append(" FROM ").append(TABELA_GIA_ITCD_DAR).append(" GIADAR ");
		sql.append(" WHERE GIADAR.").append(CAMPO_CODG_ITCD_DAR).append(" = ");
		sql.append("    (  SELECT MAX(GIADAR_INNER.").append(CAMPO_CODG_ITCD_DAR).append(") ");
		sql.append("        FROM ").append(TABELA_GIA_ITCD_DAR).append(" GIADAR_INNER ");
		sql.append("        WHERE GIADAR_INNER.").append(CAMPO_CODG_ITCD).append(" = ? ");
		if (Validador.isNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getDarEmitido().getNumrDarSeqc()))
		{
			sql.append("        AND GIADAR_INNER.").append(CAMPO_NUMR_DAR_SEQUENCIAL).append(" = ? ");
		}
		sql.append("    )");
		return sql.toString();
	}

	/**
	 * Este metodo consulta no banco de dados o ultima dar da gia itcd
	 *
	 * @param giaITCDDarVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 */
	public GIAITCDDarVo findUltimoGIAITCDDar(final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDDarVo);
		if (!Validador.isNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getGia().getCodigo()))
		{
			throw new ObjetoObrigatorioException();
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindUltimoGIAITCDDar(giaITCDDarVo));
			prepareStatementFindUltimoGIAITCDDar(ps, giaITCDDarVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getGIAITCDar(rs, giaITCDDarVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_TEMPORARIO);
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
		return giaITCDDarVo;
	}

	/**
	 * Este metodo passa os parametros para o prepared statement da consulta do ultimo dar
	 * de uma determinada GIA.
	 *
	 * @param ps
	 * @param giaITCDDarVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 */
	private void prepareStatementFindUltimoGIAITCDDar(final PreparedStatement ps, final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		int contador = 0;
		if (giaITCDDarVo.isConsultaParametrizada())
		{
			// CODIGO DA GIAITCD
			if (Validador.isNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getGia().getCodigo()))
			{
				ps.setLong(++contador, ((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getGia().getCodigo());
			}
			// NUMERO DO DAR
			if (Validador.isNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getDarEmitido().getNumrDarSeqc()))
			{
				ps.setLong(++contador, ((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getDarEmitido().getNumrDarSeqc().longValue());
			}
		}
	}

	/**
	 * Este metodo procura no banco de dados um determinada dar para uma determinada gia
	 * de acordo com os parametros oferecidos.
	 *
	 * @param giaITCDDarVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 */
	public GIAITCDDarVo findGIAITCDDar(final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDDarVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindGIAITCDDar(giaITCDDarVo));
			prepareStatementFindGIAITCDDar(ps, giaITCDDarVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getGIAITCDar(rs, giaITCDDarVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_TEMPORARIO);
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
		return giaITCDDarVo;
	}

	/**
	 * Este metodo trata os parametros da consulta - <b>findGIAITCDDar</b> -  de dar e entao os passa para um
	 * prepared statement.
	 *
	 * @param ps
	 * @param giaITCDDarVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 */
	private void prepareStatementFindGIAITCDDar(final PreparedStatement ps, final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDDarVo);
		int contador = 0;
		if (giaITCDDarVo.isConsultaParametrizada())
		{
			// CHAVE PRIMARIA
			if (Validador.isNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getCodigo()))
				ps.setLong(++contador, ((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getCodigo());
			// CODIGO DA GIAITCD
			if (Validador.isNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getGia().getCodigo()))
				ps.setLong(++contador, ((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getGia().getCodigo());
			// NUMERO DO DAR
			if (Validador.isNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getDarEmitido().getNumrDarSeqc()))
				ps.setLong(++contador, ((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getDarEmitido().getNumrDarSeqc().longValue());
			// FLAG SUBSTITUIDO
			if (Validador.isDominioNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getSubstituido()))
				ps.setLong(++contador, ((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getSubstituido().getValorCorrente());
		   if (Validador.isNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getStatusGIAITCDVo().getCodigo()))
		      ps.setLong(++contador, ((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getStatusGIAITCDVo().getCodigo());

		}
	}

	/**
	 * Este metodo lista todos os dar's de uma determinada gia
	 *
	 * @param giaITCDDarVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 */
	public GIAITCDDarVo listGIAITCDDar(final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDDarVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindGIAITCDDar(giaITCDDarVo));
			prepareStatementFindGIAITCDDar(ps, giaITCDDarVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				GIAITCDDarVo giaITCDDarAtualVo = new GIAITCDDarVo();
				getGIAITCDar(rs, giaITCDDarAtualVo);
				giaITCDDarVo.getCollVO().add(giaITCDDarAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_TEMPORARIO);
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
		return giaITCDDarVo;
	}
}
