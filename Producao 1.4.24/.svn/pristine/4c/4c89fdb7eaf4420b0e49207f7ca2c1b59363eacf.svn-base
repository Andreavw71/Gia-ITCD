/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AvaliacaoBemTributavelQDao.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: AvaliacaoBemTributavelQDao.java,v 1.2 2008/07/07 12:04:56 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.avaliacao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposAvaliacaoBemtributavel;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposNaturezaOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe para acesso ao banco de dados, responsável com consultas sobre Avaliação de Bem Tributável
 *
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
public class AvaliacaoBemTributavelQDao extends AbstractDao implements CamposAvaliacaoBemtributavel, TabelasITC
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 *
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método que monta o VO da avaliação com os dados encontrados no banco de dados
	 *
	 * @param rs
	 * @param avaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private AvaliacaoBemTributavelVo getAvaliacao(ResultSet rs, AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		avaliacaoBemTributavelVo.setCodigo(rs.getLong(CAMPO_AVALIACAO_BEMTRIBUTAVEL_CODIGO));
		avaliacaoBemTributavelVo.setBemTributavel(new BemTributavelVo(rs.getLong(CAMPO_ITCTB18_CODIGO_BEMTRIBUTAVEL)));
		avaliacaoBemTributavelVo.setAvaliacaoJudicial(new DomnSimNao(rs.getInt(CAMPO_AVALIACAO_JUDICIAL)));
		avaliacaoBemTributavelVo.setValorAvaliacao(rs.getDouble(CAMPO_VALOR_AVALIACAO));
		avaliacaoBemTributavelVo.setDataAvaliacao(rs.getDate(CAMPO_DATA_AVALIACAO));
		avaliacaoBemTributavelVo.setDataCadastro(rs.getDate(CAMPO_DATA_CADASTRO));
		avaliacaoBemTributavelVo.setObservacao(rs.getString(CAMPO_OBSERVACAO));
		avaliacaoBemTributavelVo.setIsento(new DomnSimNao(rs.getInt(CAMPO_INFO_ISENTO)));
		avaliacaoBemTributavelVo.setDataAtualizacao(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
      avaliacaoBemTributavelVo.setStatusAvaliacao(new DomnAtivoInativo(rs.getInt(CAMPO_STATUS_AVALIACAO)));
	   avaliacaoBemTributavelVo.setAvaliacaoImpressa(new DomnSimNao(rs.getInt(CAMPO_AVALIACAO_IMPRESSA)));
	   avaliacaoBemTributavelVo.setValorReabertura(rs.getDouble(CAMPO_VALOR_REABERTURA));
		return avaliacaoBemTributavelVo;
	}

	/**
	 * Método que constroe a SQL de consulta
	 *
	 * @param avaliacaoBemTributavelVo
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListAvaliacaoBemTributavel(AvaliacaoBemTributavelVo avaliacaoBemTributavelVo)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT AVAL.").append(CAMPO_AVALIACAO_BEMTRIBUTAVEL_CODIGO);
		sql.append(" , AVAL.").append(CAMPO_ITCTB18_CODIGO_BEMTRIBUTAVEL);
		sql.append(" , AVAL.").append(CAMPO_AVALIACAO_JUDICIAL);
		sql.append(" , AVAL.").append(CAMPO_VALOR_AVALIACAO);
		sql.append(" , AVAL.").append(CAMPO_DATA_AVALIACAO);
		sql.append(" , AVAL.").append(CAMPO_DATA_CADASTRO);
		sql.append(" , AVAL.").append(CAMPO_OBSERVACAO);
		sql.append(" , AVAL.").append(CAMPO_INFO_ISENTO);
		sql.append(" , AVAL.").append(CAMPO_DATA_ATUALIZACAO_BD);
	   sql.append(" , AVAL.").append(CAMPO_STATUS_AVALIACAO);
	   sql.append(" , AVAL.").append(CAMPO_AVALIACAO_IMPRESSA);
	   sql.append(" , AVAL.").append(CAMPO_VALOR_REABERTURA);
		sql.append(" FROM ").append(TABELA_AVALIACAO_BEMTRIBUTAVEL).append(" AVAL ");
		sql.append(" WHERE 1 = 1 ");
		if (avaliacaoBemTributavelVo != null && avaliacaoBemTributavelVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND AVAL.").append(CAMPO_AVALIACAO_BEMTRIBUTAVEL_CODIGO).append(" = ? ");
			}
			// BEM TRIBUTAVEL
			if (Validador.isNumericoValido(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getBemTributavel().getCodigo()))
			{
				sql.append(" AND AVAL.").append(CAMPO_ITCTB18_CODIGO_BEMTRIBUTAVEL).append(" = ? ");
			}
			// AVALIACAO JUDICIAL
			if (Validador.isDominioNumericoValido(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getAvaliacaoJudicial()))
			{
				sql.append(" AND AVAL.").append(CAMPO_AVALIACAO_JUDICIAL).append(" = ? ");
			}
			// VALOR AVALIACAO
			if (Validador.isNumericoValido(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getValorAvaliacao()))
			{
				sql.append(" AND AVAL.").append(CAMPO_VALOR_AVALIACAO).append(" = ?");
			}
			// DATA AVALIACAO
			if (Validador.isDataValida(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getDataAvaliacao()))
			{
				sql.append(" AND AVAL.").append(CAMPO_DATA_AVALIACAO).append(" = ?");
			}
			// DATA CADASTRO
			if (Validador.isDataValida(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getDataCadastro()))
			{
				sql.append(" AND AVAL.").append(CAMPO_DATA_CADASTRO).append(" = ?");
			}
			// OBSERVACAO
			if (Validador.isStringValida(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getObservacao()))
			{
				sql.append(" AND UPPER(AVAL.").append(CAMPO_OBSERVACAO).append(") LIKE (UPPER('%'?'%')) ");
			}
			// ISENTO
			if (Validador.isDominioNumericoValido(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getIsento()))
			{
				sql.append(" AND AVAL.").append(CAMPO_INFO_ISENTO).append(" = ? ");
			}
         // STATUS AVALIACAO
         if ( Validador.isDominioNumericoValido( ((AvaliacaoBemTributavelVo)avaliacaoBemTributavelVo.getParametroConsulta()).getStatusAvaliacao()))
         {
            sql.append(" AND AVAL.").append( CAMPO_STATUS_AVALIACAO).append(" = ? ");
         }
		   // AVALIACAO IMPRESSA
		   if ( Validador.isDominioNumericoValido( ((AvaliacaoBemTributavelVo)avaliacaoBemTributavelVo.getParametroConsulta()).getAvaliacaoImpressa()))
		   {
		      //sql.append(" AND AVAL.").append( CAMPO_AVALIACAO_IMPRESSA).append(" = ? ");
		   }
		}
		sql.append(" ORDER BY AVAL.").append(CAMPO_AVALIACAO_BEMTRIBUTAVEL_CODIGO);
		return sql.toString();
	}

	/**
	 * Método que monta o Prapare Statement com os valores válidos da Avaliação
	 *
	 * @param ps
	 * @param avaliacaoBemTributavelVo
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementListAvaliacaoBemTributavel(PreparedStatement ps, AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws SQLException
	{
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getCodigo()))
		{
			ps.setLong(++contador, ((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getCodigo());
		}
		// BEM TRIBUTAVEL	
		if (Validador.isNumericoValido(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getBemTributavel().getCodigo()))
		{
			ps.setLong(++contador, ((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getBemTributavel().getCodigo());
		}
		// AVALIACAO JUDICIAL
		if (Validador.isDominioNumericoValido(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getAvaliacaoJudicial()))
		{
			ps.setInt(++contador, ((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getAvaliacaoJudicial().getValorCorrente());
		}
		// VALOR AVALIACAO
		if (Validador.isNumericoValido(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getValorAvaliacao()))
		{
			ps.setDouble(++contador, ((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getValorAvaliacao());
		}
		// DATA AVALIACAO
		if (Validador.isDataValida(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getDataAvaliacao()))
		{
			ps.setDate(++contador, new Date(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getDataAvaliacao().getTime()));
		}
		// DATA CADASTRO
		if (Validador.isDataValida(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getDataCadastro()))
		{
			ps.setDate(++contador, new Date(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getDataCadastro().getTime()));
		}
		// OBSERVACAO
		if (Validador.isStringValida(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getObservacao()))
		{
			ps.setString(++contador, ((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getObservacao());
		}
		// ISENTO
		if (Validador.isDominioNumericoValido(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getIsento()))
		{
			ps.setInt(++contador, ((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getIsento().getValorCorrente());
		}
	   // STATUS AVALIACAO
	   if (Validador.isDominioNumericoValido(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getStatusAvaliacao()))
	   {
	      ps.setInt(++contador, ((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getStatusAvaliacao().getValorCorrente());
	   }
	   // AVALIACAO IMPRESSA
	   if (Validador.isDominioNumericoValido(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getAvaliacaoImpressa()))
	   {
	      //ps.setInt(++contador, ((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getAvaliacaoImpressa().getValorCorrente());
	   }
	}

	/**
	 * Método para listar as Avaliações
	 *
	 * @param avaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelVo listAvaliacaoBemTributavell(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListAvaliacaoBemTributavel(avaliacaoBemTributavelVo));
			prepareStatementListAvaliacaoBemTributavel(ps, avaliacaoBemTributavelVo);
			for (rs = ps.executeQuery(); rs.next(); )
			{
				AvaliacaoBemTributavelVo avaliacaoBemTributavelAtualVo = new AvaliacaoBemTributavelVo();
				getAvaliacao(rs, avaliacaoBemTributavelAtualVo);
				avaliacaoBemTributavelVo.getCollVO().add(avaliacaoBemTributavelAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_AVALIACAO_BEM_TRIBUTAVEL);
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
		return avaliacaoBemTributavelVo;
	}

	private void prepareStatementListAvaliacaoBemTributavelByStatusPeriodo(PreparedStatement ps, AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws SQLException
	{
		int contador = 0;
		AvaliacaoBemTributavelVo parametroConsulta = (AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta();
		if(Validador.isNumericoValido(parametroConsulta.getBemTributavel().getGiaITCDVo().getStatusVo().getCodigoUnidadeAvaliacao()))
		{
			ps.setInt(++contador, parametroConsulta.getBemTributavel().getGiaITCDVo().getStatusVo().getCodigoUnidadeAvaliacao());			
		}
		if(Validador.isDataValida(parametroConsulta.getDataInicial()))
		{
			ps.setTimestamp(++contador, new SefazDataHora(parametroConsulta.getDataInicial()).toSqlTimestamp());	
		}
	   if(Validador.isDataValida(parametroConsulta.getDataFinal()))
	   {
	      ps.setTimestamp(++contador, new SefazDataHora(parametroConsulta.getDataFinal()).toSqlTimestamp());  
	   }
		if(Validador.isNumericoValido(parametroConsulta.getBemTributavel().getGiaITCDVo().getResponsavelVo().getNumrContribuinte()))
		{
			ps.setLong(++contador, parametroConsulta.getBemTributavel().getGiaITCDVo().getResponsavelVo().getNumrContribuinte().longValue());	
		}
	   // STATUS AVALIACAO
	   if (Validador.isDominioNumericoValido(((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getStatusAvaliacao()))
	   {
	    //  ps.setInt(++contador, ((AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta()).getStatusAvaliacao().getValorCorrente());
	   }
		ps.setInt(++contador, DomnStatusGIAITCD.AVALIADO);		
	}

	private String getSQLListAvaliacaoBemTributavelByStatusPeriodo(AvaliacaoBemTributavelVo avaliacaoBemTributavelVo)
	{
	   AvaliacaoBemTributavelVo parametroConsulta = (AvaliacaoBemTributavelVo) avaliacaoBemTributavelVo.getParametroConsulta();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT ");
		sql.append(" ITCD.").append(CamposGIAITCD.CAMPO_TIPO_ITCD).append(", ");
		sql.append(" ITCD.").append(CamposGIAITCD.CAMPO_CODIGO_GIA_ITCD).append(", ");
		sql.append(" PESS.NOME_PESSOA, ");
	   sql.append(" NAT.").append(CamposNaturezaOperacao.CAMPO_TIPO_PROCESSO).append(", ");
	   sql.append(" STATUS.").append(CamposStatusGIAITCD.CAMPO_DATA_CADASTRO_AVALIACAO).append(", ");
	   sql.append(" STATUS.").append(CamposStatusGIAITCD.CAMPO_STAT_ITCD).append(" ");
	   sql.append(" FROM ").append(TABELA_STATUS_GIA_ITCD).append(" STATUS ");
		sql.append(" INNER JOIN ").append(TABELA_GIA_ITCD).append(" ITCD ON ");
		sql.append(" ITCD.").append(CamposGIAITCD.CAMPO_CODIGO_GIA_ITCD).append(" = STATUS.").append(CamposStatusGIAITCD.CAMPO_ITCTB14_CODIGO_ITCD);
		sql.append(" INNER JOIN ACCTB01_PESSOA PESS ON");
		sql.append(" PESS.NUMR_PESSOA = ITCD.").append(CamposGIAITCD.CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL);
		sql.append(" INNER JOIN ").append(TABELA_NATUREZA_OPERACAO).append(" NAT ON ");
		sql.append(" NAT.").append(CamposNaturezaOperacao.CAMPO_CODIGO_NATUREZA_OPERACAO).append(" = ITCD.").append(CamposGIAITCD.CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO);
		sql.append(" WHERE 1=1 ");
		if(Validador.isNumericoValido(parametroConsulta.getBemTributavel().getGiaITCDVo().getStatusVo().getCodigoUnidadeAvaliacao()))
		{
			sql.append(" AND STATUS.").append(CamposStatusGIAITCD.CAMPO_CODIGO_UNIDADE_AVALIACAO).append(" = ? ");	
		}
	   if(Validador.isDataValida(parametroConsulta.getDataInicial()) && Validador.isDataValida(parametroConsulta.getDataFinal()))
	   {
	      sql.append(" AND STATUS.").append(CamposStatusGIAITCD.CAMPO_DATA_CADASTRO_AVALIACAO).append(" BETWEEN ? AND ? ");
	   }
	   if(Validador.isNumericoValido(parametroConsulta.getBemTributavel().getGiaITCDVo().getResponsavelVo().getNumrContribuinte()))
	   {
	      sql.append(" AND ITCD.").append(CamposGIAITCD.CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL).append(" = ? ");
	   }  		
		sql.append(" AND STATUS.").append(CamposStatusGIAITCD.CAMPO_ITCTB14_CODIGO_ITCD).append(" IN ");
	   sql.append(" (");
			sql.append(" SELECT ").append(CamposStatusGIAITCD.CAMPO_ITCTB14_CODIGO_ITCD).append(" FROM ").append(TabelasITC.TABELA_STATUS_GIA_ITCD);
			sql.append(" WHERE ").append(CamposStatusGIAITCD.CAMPO_STAT_ITCD).append(" = ? ");
		sql.append(" )");
		sql.append(" AND STATUS.").append(CamposStatusGIAITCD.CAMPO_CODIGO_STATUS_ITCD).append(" = ");
		sql.append(" ( ");
			sql.append(" SELECT MAX(").append(CamposStatusGIAITCD.CAMPO_CODIGO_STATUS_ITCD).append(") ");
			sql.append(" FROM ").append(TABELA_STATUS_GIA_ITCD);
			sql.append(" WHERE ").append(CamposStatusGIAITCD.CAMPO_ITCTB14_CODIGO_ITCD).append(" = ITCD.").append(CamposGIAITCD.CAMPO_CODIGO_GIA_ITCD);
		sql.append(" ) ");			
		sql.append(" ORDER BY ITCD.").append(CamposGIAITCD.CAMPO_CODIGO_GIA_ITCD);
      System.out.println("------SQL : "+sql.toString());
		return sql.toString();
	}

	public AvaliacaoBemTributavelVo listAvaliacaoBemTributavelByStatusPeriodo(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ConsultaException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListAvaliacaoBemTributavelByStatusPeriodo(avaliacaoBemTributavelVo));
			prepareStatementListAvaliacaoBemTributavelByStatusPeriodo(ps, avaliacaoBemTributavelVo);
			rs = ps.executeQuery();
			while(rs.next())
			{
				AvaliacaoBemTributavelVo avaliacao = new 	AvaliacaoBemTributavelVo();
				BemTributavelVo bemTributavel = new BemTributavelVo();
				GIAITCDVo gia = new GIAITCDVo();
				NaturezaOperacaoVo natureza = new NaturezaOperacaoVo();
				ContribuinteIntegracaoVo responsavel = new ContribuinteIntegracaoVo();
				gia.setTipoGIA(new DomnTipoGIA(rs.getInt(CamposGIAITCD.CAMPO_TIPO_ITCD)));
				gia.setCodigo(rs.getInt(CamposGIAITCD.CAMPO_CODIGO_GIA_ITCD));
				gia.getStatusVo().setDataCadastroAvaliacao(rs.getDate(CamposStatusGIAITCD.CAMPO_DATA_CADASTRO_AVALIACAO));
				gia.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(rs.getInt(CamposStatusGIAITCD.CAMPO_STAT_ITCD)));
				responsavel.setNomeContribuinte(rs.getString("NOME_PESSOA"));
				natureza.setTipoProcesso(new DomnTipoProcesso(rs.getInt(CamposNaturezaOperacao.CAMPO_TIPO_PROCESSO)));
				bemTributavel.setGiaITCDVo(gia);
				avaliacao.setBemTributavel(bemTributavel);
				gia.setNaturezaOperacaoVo(natureza);
				gia.setResponsavelVo(responsavel);
			   avaliacaoBemTributavelVo.getCollVO().add(avaliacao);
			}
		}
		catch (SQLException e)
		{
		   e.printStackTrace();
		   throw new ConsultaException(MensagemErro.LISTAR_AVALIACAO_BEM_TRIBUTAVEL);
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
		return avaliacaoBemTributavelVo;
	}

	/**
	 * Método para efetuar uma consulta de Avaliação
	 *
	 * @param avaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelVo findAvaliacaoBemTributavell(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListAvaliacaoBemTributavel(avaliacaoBemTributavelVo));
			prepareStatementListAvaliacaoBemTributavel(ps, avaliacaoBemTributavelVo);
			rs = ps.executeQuery();
			//if (rs.next())
			while(rs.next())
         {
				getAvaliacao(rs, avaliacaoBemTributavelVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_AVALIACAO_BEM_TRIBUTAVEL);
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
		return avaliacaoBemTributavelVo;
	}
}
