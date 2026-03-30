package br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.log.log.LogITCDVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAvaliacao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;

import sefaz.mt.util.SefazDisplay;

/**
 * Classe de manipulação de consultas no banco de dados sobre o Status da GIA-ITCD
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
public class StatusGIAITCDQDao extends AbstractDao implements TabelasITC, CamposStatusGIAITCD, SequencesITC
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public StatusGIAITCDQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método que monta o VO do Status da GIA-ITCD com os valores do Result Set
	 * @param rs
	 * @param statusGIAITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private StatusGIAITCDVo getStatusGIAITCD(final ResultSet rs, final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(statusGIAITCDVo);
		statusGIAITCDVo.setCodigo(rs.getLong(CAMPO_CODIGO_STATUS_ITCD));
		statusGIAITCDVo.getServidor().setNumrMatricula(new Long(rs.getLong(CAMPO_SGPTB06_NUMERO_MATRICULA_SERVIDOR)));
		statusGIAITCDVo.getGiaITCDVo().setCodigo(rs.getLong(CAMPO_ITCTB14_CODIGO_ITCD));
		statusGIAITCDVo.setStatusGIAITCD(new DomnStatusGIAITCD(rs.getInt(CAMPO_STAT_ITCD)));
		statusGIAITCDVo.setDataAtualizacao(rs.getTimestamp(CAMPO_DATA_ATUALIZACAO_BD));
		statusGIAITCDVo.setDataPermissao(rs.getDate(CAMPO_DATA_PERMISSAO));
		statusGIAITCDVo.setObservacao(rs.getString(CAMPO_OBSERVACAO));
		statusGIAITCDVo.setProtocoloParcelamento(rs.getLong(CAMPO_PROTOCOLO_PARCELAMENTO));
		statusGIAITCDVo.setNumeroParcelas(rs.getInt(CAMPO_NUMERO_PARCELAS));
		statusGIAITCDVo.setDataParcelamento(rs.getDate(CAMPO_DATA_PARCELAMENTO));
		statusGIAITCDVo.setProtocoloImpugnacao(rs.getLong(CAMPO_PROTOCOLO_IMPUGNACAO));
		statusGIAITCDVo.setDataImpugnacao(rs.getDate(CAMPO_DATA_IMPUGNACAO));
		statusGIAITCDVo.setDataRatificacao(rs.getDate(CAMPO_DATA_RATIFICACAO));
		statusGIAITCDVo.setNumeroDARRatificacao(rs.getLong(CAMPO_NUMERO_DAR_RATIFICACAO));
		statusGIAITCDVo.setDataEmissaoSegundaRetificacao(rs.getDate(CAMPO_DATA_EMISSAO_SEGUNDA_RETIFICACAO));
		statusGIAITCDVo.setNumeroDARSegundaRetificacao(rs.getLong(CAMPO_NUMERO_DAR_SEGUNDA_RETIFICACAO));
		statusGIAITCDVo.setDataCienciaRetificacao(rs.getDate(CAMPO_DATA_CIENCIA_RETIFICACAO));
		statusGIAITCDVo.setDataEnvioDividaAtiva(rs.getDate(CAMPO_DATA_ENVIO_DIVIDA_ATIVA));
		statusGIAITCDVo.setDataCienciaSegundaRetificacao(rs.getDate(CAMPO_DATA_CIENCIA_SEGUNDA_RETIFICACAO));
		statusGIAITCDVo.setProtocolo(rs.getLong(CAMPO_PROTOCOLO));
		statusGIAITCDVo.setDataProtocolo(rs.getDate(CAMPO_DATA_PROTOCOLO));
		statusGIAITCDVo.setCodigoAgenfa(rs.getInt(CAMPO_CODIGO_AGENFA_PROTOCOLO));
		statusGIAITCDVo.setProtocoloDesistencia(rs.getLong(CAMPO_PROTOCOLO_DESISTENCIA));
		statusGIAITCDVo.setDataDesistencia(rs.getDate(CAMPO_DATA_DESISTENCIA));
		statusGIAITCDVo.setDataNotificacao(rs.getDate(CAMPO_DATA_NOTIFICACAO));
		statusGIAITCDVo.setDataEmissaoRetificacao(rs.getDate(CAMPO_DATA_EMISSAO_RETIFICACAO));
		statusGIAITCDVo.setJustificativaReativacao(rs.getString(CAMPO_JUSTIFICATIVA_REATIVACAO));
	   statusGIAITCDVo.setTipoAvaliacao(new DomnTipoAvaliacao(rs.getInt(CAMPO_TIPO_AVALIACAO)));
		statusGIAITCDVo.setCodigoUnidadeAvaliacao(rs.getInt(CAMPO_CODIGO_UNIDADE_AVALIACAO));
		statusGIAITCDVo.setDataCadastroAvaliacao(rs.getDate(CAMPO_DATA_CADASTRO_AVALIACAO));
		statusGIAITCDVo.getGiaTemporaria().setCodigo(rs.getLong(CAMPO_CODIGO_GIA_TEMPORARIA));
		statusGIAITCDVo.setDataCienciaNotificacao(rs.getDate(CAMPO_DATA_CIENCIA_NOTIFICACAO));
		statusGIAITCDVo.setDataQuitacao(rs.getDate(CAMPO_DATA_QUITACAO));
		statusGIAITCDVo.getGiaITCDDarVo().getDarEmitido().setNumrDarSeqc(rs.getLong(CAMPO_NUMR_DAR_QUITACAO));
		statusGIAITCDVo.setJustificativaEnvioDividaAtiva(rs.getString(CAMPO_JUSTIFICATIVA_ENVIO_DIVIDA_ATIVA));
		statusGIAITCDVo.setDataEnvioInscricaoDividaAtiva(rs.getDate(CAMPO_DATA_ENVIO_INSCRICAO_DIVIDA_ATIVA));
		statusGIAITCDVo.setJustificativaEnvioInscricaoDividaAtiva(rs.getString(CAMPO_JUSTIFICATIVA_ENVIO_INSCRICAO_DIVIDA_ATIVA));
		statusGIAITCDVo.setMotivoImpugnacao(rs.getString(CAMPO_MOTIVO_IMPUGNACAO));
		statusGIAITCDVo.setValorImposto(rs.getDouble(CAMPO_VALOR_IMPOSTO));
		statusGIAITCDVo.setDataCienciaRatificacao(rs.getDate(CAMPO_DATA_CIENCIA_RATIFICACAO));
		statusGIAITCDVo.setJustificativaReparcelamento(rs.getString(CAMPO_JUSTIFICATIVA_REPARCELAMENTO));
		statusGIAITCDVo.getServidorSefazResponsavelAlteracao().setNumrMatricula(rs.getLong(CAMPO_SERVIDOR_SEFAZ_RESPONSAVEL_ALTERACAO));
		statusGIAITCDVo.setNumeroGiaSubstituta(rs.getLong(CAMPO_NUMERO_GIA_SUBSTITUTA));
	   statusGIAITCDVo.setDataEnvioCCF(rs.getDate(CAMPO_DATA_ENVIO_CCF));
	   statusGIAITCDVo.setProtocoloCCF(rs.getLong(CAMPO_PROTOCOLO_CCF));
	   statusGIAITCDVo.setJustificativaAlteracao(rs.getString(CAMPO_JUSTIFICATIVA_ALTERACAO));
      statusGIAITCDVo.setLogITCDVo(new LogITCDVo(rs.getLong(CAMPO_ITCTB48_NUMR_LOG_SEQ)));
		return statusGIAITCDVo;
	}
	
	private String getSQLBase()
	{
	   StringBuilder sql = new StringBuilder();
	   sql.append(" SELECT STATUS.").append(CAMPO_CODIGO_STATUS_ITCD);
	   sql.append(" , STATUS.").append(CAMPO_SGPTB06_NUMERO_MATRICULA_SERVIDOR);
	   sql.append(" , STATUS.").append(CAMPO_ITCTB14_CODIGO_ITCD);
	   sql.append(" , STATUS.").append(CAMPO_STAT_ITCD);
	   sql.append(" , STATUS.").append(CAMPO_DATA_ATUALIZACAO_BD);
	   sql.append(" , STATUS.").append(CAMPO_DATA_PERMISSAO);
	   sql.append(" , STATUS.").append(CAMPO_OBSERVACAO);
	   sql.append(" , STATUS.").append(CAMPO_PROTOCOLO_PARCELAMENTO);
	   sql.append(" , STATUS.").append(CAMPO_NUMERO_PARCELAS);
	   sql.append(" , STATUS.").append(CAMPO_DATA_PARCELAMENTO);
	   sql.append(" , STATUS.").append(CAMPO_PROTOCOLO_IMPUGNACAO);
	   sql.append(" , STATUS.").append(CAMPO_DATA_IMPUGNACAO);
	   sql.append(" , STATUS.").append(CAMPO_DATA_RATIFICACAO);
	   sql.append(" , STATUS.").append(CAMPO_NUMERO_DAR_RATIFICACAO);
	   sql.append(" , STATUS.").append(CAMPO_DATA_EMISSAO_SEGUNDA_RETIFICACAO);
	   sql.append(" , STATUS.").append(CAMPO_NUMERO_DAR_SEGUNDA_RETIFICACAO);
	   sql.append(" , STATUS.").append(CAMPO_DATA_CIENCIA_RETIFICACAO);
	   sql.append(" , STATUS.").append(CAMPO_DATA_ENVIO_DIVIDA_ATIVA);
	   sql.append(" , STATUS.").append(CAMPO_DATA_CIENCIA_SEGUNDA_RETIFICACAO);
	   sql.append(" , STATUS.").append(CAMPO_PROTOCOLO);
	   sql.append(" , STATUS.").append(CAMPO_DATA_PROTOCOLO);
	   sql.append(" , STATUS.").append(CAMPO_CODIGO_AGENFA_PROTOCOLO);
	   sql.append(" , STATUS.").append(CAMPO_PROTOCOLO_DESISTENCIA);
	   sql.append(" , STATUS.").append(CAMPO_DATA_DESISTENCIA);
	   sql.append(" , STATUS.").append(CAMPO_DATA_NOTIFICACAO);
	   sql.append(" , STATUS.").append(CAMPO_DATA_EMISSAO_RETIFICACAO);
	   sql.append(" , STATUS.").append(CAMPO_JUSTIFICATIVA_REATIVACAO);
	   sql.append(" , STATUS.").append(CAMPO_TIPO_AVALIACAO);      
	   sql.append(" , STATUS.").append(CAMPO_CODIGO_UNIDADE_AVALIACAO);
	   sql.append(" , STATUS.").append(CAMPO_DATA_CADASTRO_AVALIACAO);
	   sql.append(" , STATUS.").append(CAMPO_CODIGO_GIA_TEMPORARIA);
	   sql.append(" , STATUS.").append(CAMPO_DATA_CIENCIA_NOTIFICACAO);
	   sql.append(" , STATUS.").append(CAMPO_DATA_QUITACAO);
	   sql.append(" , STATUS.").append(CAMPO_NUMR_DAR_QUITACAO);
	   sql.append(" , STATUS.").append(CAMPO_JUSTIFICATIVA_ENVIO_DIVIDA_ATIVA);
	   sql.append(" , STATUS.").append(CAMPO_DATA_ENVIO_INSCRICAO_DIVIDA_ATIVA);
	   sql.append(" , STATUS.").append(CAMPO_JUSTIFICATIVA_ENVIO_INSCRICAO_DIVIDA_ATIVA);
	   sql.append(" , STATUS.").append(CAMPO_MOTIVO_IMPUGNACAO);
	   sql.append(" , STATUS.").append(CAMPO_VALOR_IMPOSTO);
	   sql.append(" , STATUS.").append(CAMPO_DATA_CIENCIA_RATIFICACAO);
		sql.append(" , STATUS.").append(CAMPO_JUSTIFICATIVA_REPARCELAMENTO);
		sql.append(" , STATUS.").append(CAMPO_SERVIDOR_SEFAZ_RESPONSAVEL_ALTERACAO);
		sql.append(" , STATUS.").append(CAMPO_NUMERO_GIA_SUBSTITUTA);
	   sql.append(" , STATUS.").append(CAMPO_DATA_ENVIO_CCF);
	   sql.append(" , STATUS.").append(CAMPO_PROTOCOLO_CCF);
	   sql.append(" , STATUS.").append(CAMPO_JUSTIFICATIVA_ALTERACAO);
      sql.append(" , STATUS.").append(CAMPO_ITCTB48_NUMR_LOG_SEQ);
	   sql.append(" FROM ").append("ITC."+TABELA_STATUS_GIA_ITCD).append(" STATUS ");		
		return sql.toString();
	}

	/**
	 * Método que efetua a consulta de um Status da GIA-ITCD
	 * @param statusGIAITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public StatusGIAITCDVo findStatusGIAITCD(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(statusGIAITCDVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindStatusGIAITCD(statusGIAITCDVo));
			prepareStatementFindStatusGIAITCD(ps, statusGIAITCDVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getStatusGIAITCD(rs, statusGIAITCDVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_STATUS_GIA_ITCD);
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
		return statusGIAITCDVo;
	}

	/**
	 * Método que monta o Prepare Statement com os dados válidos do Status da GIA-ITCD
	 * @param ps
	 * @param statusGIAITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindStatusGIAITCD(final PreparedStatement ps, final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(statusGIAITCDVo);
		int contador = 0;
		if (statusGIAITCDVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, (statusGIAITCDVo.getParametroConsulta()).getCodigo());
			}
			// MATRICULA SERVIDOR
			if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getServidor().getNumrMatricula()))
			{
				ps.setLong(++contador, ( statusGIAITCDVo.getParametroConsulta()).getServidor().getNumrMatricula().longValue());
			}
			// CODIGO GIA
			if(Validador.isNumericoValido(statusGIAITCDVo.getParametroConsulta().getGiaITCDVo().getCodigo()) && Validador.isNumericoValido(statusGIAITCDVo.getParametroConsulta().getGiaTemporaria().getCodigo()))
			{
				ps.setLong(++contador, statusGIAITCDVo.getParametroConsulta().getGiaITCDVo().getCodigo());
			   ps.setLong(++contador, ( statusGIAITCDVo.getParametroConsulta()).getGiaTemporaria().getCodigo());
			}
			else if(Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
			{
			   ps.setLong(++contador, statusGIAITCDVo.getParametroConsulta().getGiaITCDVo().getCodigo());
			}
			else if(Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getGiaTemporaria().getCodigo()))
			{
			   ps.setLong(++contador, ( statusGIAITCDVo.getParametroConsulta()).getGiaTemporaria().getCodigo());
			}			// STATUS
			if (Validador.isDominioNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getStatusGIAITCD()))
			{
				ps.setInt(++contador, ( statusGIAITCDVo.getParametroConsulta()).getStatusGIAITCD().getValorCorrente());
			}
			// DATA PERMISSAO
			if (Validador.isDataValida((statusGIAITCDVo.getParametroConsulta()).getDataPermissao()))
			{
				ps.setDate(++contador, new Date(( statusGIAITCDVo.getParametroConsulta()).getDataPermissao().getTime()));
			}
			// OBSERVACAO
			if (Validador.isStringValida(( statusGIAITCDVo.getParametroConsulta()).getObservacao()))
			{
				ps.setString(++contador, ( statusGIAITCDVo.getParametroConsulta()).getObservacao());
			}
			// PROTOLOCO PARCELAMENTO
			if (Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getProtocoloParcelamento()))
			{
				ps.setLong(++contador, ( statusGIAITCDVo.getParametroConsulta()).getProtocoloParcelamento());
			}
			// NUMERO PARCELAS
			if (Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getNumeroParcelas()))
			{
				ps.setInt(++contador, ( statusGIAITCDVo.getParametroConsulta()).getNumeroParcelas());
			}
			// DATA PARCELAMENTO
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataParcelamento()))
			{
				ps.setDate(++contador, new Date(( statusGIAITCDVo.getParametroConsulta()).getDataParcelamento().getTime()));
			}
			// PROTOCOLO IMPUGNACAO
			if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getProtocoloImpugnacao()))
			{
				ps.setLong(++contador, ( statusGIAITCDVo.getParametroConsulta()).getProtocoloImpugnacao());
			}
			// DATA IMPUGNACAO
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataImpugnacao()))
			{
				ps.setDate(++contador, new Date(( statusGIAITCDVo.getParametroConsulta()).getDataImpugnacao().getTime()));
			}
			// DATA RATIFICACAO
			if (Validador.isDataValida((statusGIAITCDVo.getParametroConsulta()).getDataRatificacao()))
			{
				ps.setDate(++contador, new Date(( statusGIAITCDVo.getParametroConsulta()).getDataRatificacao().getTime()));
			}
			// NUMERO DAR RATIFICACAO
			if (Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getNumeroDARRatificacao()))
			{
				ps.setLong(++contador, ( statusGIAITCDVo.getParametroConsulta()).getNumeroDARRatificacao());
			}
			// DATA SEGUNDA RETIFICACAO
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataEmissaoSegundaRetificacao()))
			{
				ps.setDate(++contador, new Date((statusGIAITCDVo.getParametroConsulta()).getDataEmissaoSegundaRetificacao().getTime()));
			}
			// NUMERO DAR SEGUNDA RETIFICACAO
			if (Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getNumeroDARSegundaRetificacao()))
			{
				ps.setLong(++contador, ( statusGIAITCDVo.getParametroConsulta()).getNumeroDARSegundaRetificacao());
			}
			// DATA CIENCIA RETIFICACAO
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataCienciaRetificacao()))
			{
				ps.setDate(++contador, new Date(( statusGIAITCDVo.getParametroConsulta()).getDataCienciaRetificacao().getTime()));
			}
			// DATA ENVIO DIVIDA ATIVA
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataEnvioDividaAtiva()))
			{
				ps.setDate(++contador, new Date(( statusGIAITCDVo.getParametroConsulta()).getDataEnvioDividaAtiva().getTime()));
			}
			// DATA CIENCIA SEGUNDA RETIFICACAO
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataCienciaSegundaRetificacao()))
			{
				ps.setDate(++contador, new Date(( statusGIAITCDVo.getParametroConsulta()).getDataCienciaSegundaRetificacao().getTime()));
			}
			// NUMERO PROTOCOLO
			if (Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getProtocolo()))
			{
				ps.setLong(++contador, ( statusGIAITCDVo.getParametroConsulta()).getProtocolo());
			}
			// DATA PROTOCOLO
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataProtocolo()))
			{
				ps.setDate(++contador, new Date(( statusGIAITCDVo.getParametroConsulta()).getDataProtocolo().getTime()));
			}
			// CODIGO AGENFA
			if (Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getCodigoAgenfa()))
			{
				ps.setInt(++contador, ( statusGIAITCDVo.getParametroConsulta()).getCodigoAgenfa());
			}
			// PROTOCOLO DESISTENCIA
			if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getProtocoloDesistencia()))
			{
				ps.setLong(++contador, ( statusGIAITCDVo.getParametroConsulta()).getProtocoloDesistencia());
			}
			// DATA DESISTENCIA
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataDesistencia()))
			{
				ps.setDate(++contador, new Date(( statusGIAITCDVo.getParametroConsulta()).getDataDesistencia().getTime()));
			}
			// DATA NOTIFICACAO
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataNotificacao()))
			{
				ps.setDate(++contador, new Date(( statusGIAITCDVo.getParametroConsulta()).getDataNotificacao().getTime()));
			}
			// DATA EMISSAO RETIFICACAO
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataEmissaoRetificacao()))
			{
				ps.setDate(++contador, new Date(( statusGIAITCDVo.getParametroConsulta()).getDataEmissaoRetificacao().getTime()));
			}
			// CODIGO DA UNIDADE DE AVALIACAO
		   if (Validador.isDominioNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getTipoAvaliacao()))
		   {
		      ps.setInt(++contador, ( statusGIAITCDVo.getParametroConsulta()).getTipoAvaliacao().getValorCorrente());
		   }			
			//DATA DE CADASTRO DA AVALIACAO
			 if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataCadastroAvaliacao()))
			 {
			    ps.setDate(++contador, new Date(( statusGIAITCDVo.getParametroConsulta()).getDataCadastroAvaliacao().getTime()));
			 }			 
		}	   
	}

	/**
	 * Método que monta SQL de consulta de um Status da GIA-ITCD
	 * @param statusGIAITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindStatusGIAITCD(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(statusGIAITCDVo);
	   StringBuilder sql = new StringBuilder();
		sql.append(getSQLBase());
		sql.append(" WHERE 1 = 1 ");
		if (statusGIAITCDVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND STATUS.").append(CAMPO_CODIGO_STATUS_ITCD).append(" = ? ");
			}
			// MATRICULA SERVIDOR
			if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getServidor().getNumrMatricula()))
			{
				sql.append(" AND STATUS.").append(CAMPO_SGPTB06_NUMERO_MATRICULA_SERVIDOR).append(" = ? ");
			}
			if(Validador.isNumericoValido(statusGIAITCDVo.getParametroConsulta().getGiaITCDVo().getCodigo()) && Validador.isNumericoValido(statusGIAITCDVo.getParametroConsulta().getGiaTemporaria().getCodigo()))
			{
			   sql.append(" AND (STATUS.").append(CAMPO_ITCTB14_CODIGO_ITCD).append(" = ? OR STATUS.").append(CAMPO_CODIGO_GIA_TEMPORARIA).append(" = ? )");
			}
			else if(Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
			{
			   sql.append(" AND STATUS.").append(CAMPO_ITCTB14_CODIGO_ITCD).append(" = ? ");
			}
			else if(Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getGiaTemporaria().getCodigo()))
			{
				sql.append(" AND STATUS.").append(CAMPO_CODIGO_GIA_TEMPORARIA).append(" = ? ");
			}
			// STATUS
			if (Validador.isDominioNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getStatusGIAITCD()))
			{
				sql.append(" AND STATUS.").append(CAMPO_STAT_ITCD).append(" = ? ");
			}
			// DATA PERMISSAO
			if (Validador.isDataValida((statusGIAITCDVo.getParametroConsulta()).getDataPermissao()))
			{
				sql.append(" AND STATUS.").append(CAMPO_DATA_PERMISSAO).append(" = ? ");
			}
			// OBSERVACAO
			if (Validador.isStringValida(( statusGIAITCDVo.getParametroConsulta()).getObservacao()))
			{
				sql.append(" AND UPPPER(STATUS.").append(CAMPO_OBSERVACAO).append(") LIKE (UPPER('%?%')) ");
			}
			// PROTOLOCO PARCELAMENTO
			if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getProtocoloParcelamento()))
			{
				sql.append(" AND STATUS.").append(CAMPO_PROTOCOLO_PARCELAMENTO).append(" = ? ");
			}
			// NUMERO PARCELAS
			if (Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getNumeroParcelas()))
			{
				sql.append(" AND STATUS.").append(CAMPO_NUMERO_PARCELAS).append(" = ? ");
			}
			// DATA PARCELAMENTO
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataParcelamento()))
			{
				sql.append(" AND STATUS.").append(CAMPO_DATA_PARCELAMENTO).append(" = ? ");
			}
			// PROTOCOLO IMPUGNACAO
			if (Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getProtocoloImpugnacao()))
			{
				sql.append(" AND STATUS.").append(CAMPO_PROTOCOLO_IMPUGNACAO).append(" = ? ");
			}
			// DATA IMPUGNACAO
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataImpugnacao()))
			{
				sql.append(" AND STATUS.").append(CAMPO_DATA_IMPUGNACAO).append(" = ? ");
			}
			// DATA RATIFICACAO
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataRatificacao()))
			{
				sql.append(" AND STATUS.").append(CAMPO_DATA_RATIFICACAO).append(" = ? ");
			}
			// NUMERO DAR RATIFICACAO
			if (Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getNumeroDARRatificacao()))
			{
				sql.append(" AND STATUS.").append(CAMPO_NUMERO_DAR_RATIFICACAO).append(" = ? ");
			}
			// DATA EMISSAO SEGUNDA RETIFICACAO
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataEmissaoSegundaRetificacao()))
			{
				sql.append(" AND STATUS.").append(CAMPO_DATA_EMISSAO_SEGUNDA_RETIFICACAO).append(" = ? ");
			}
			// NUMERO DAR SEGUNDA RETIFICACAO
			if (Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getNumeroDARSegundaRetificacao()))
			{
				sql.append(" AND STATUS.").append(CAMPO_NUMERO_DAR_SEGUNDA_RETIFICACAO).append(" = ? ");
			}
			// DATA CIENCIA RETIFICACAO
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataCienciaRetificacao()))
			{
				sql.append(" AND STATUS.").append(CAMPO_DATA_CIENCIA_RETIFICACAO).append(" = ? ");
			}
			// DATA ENVIO DIVIDA ATIVA
			if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataEnvioDividaAtiva()))
			{
				sql.append(" AND STATUS.").append(CAMPO_DATA_ENVIO_DIVIDA_ATIVA).append(" = ? ");
			}
			// DATA CIENCIA SEGUNDA RETIFICACAO
			if (Validador.isDataValida((statusGIAITCDVo.getParametroConsulta()).getDataCienciaSegundaRetificacao()))
			{
				sql.append(" AND STATUS.").append(CAMPO_DATA_CIENCIA_SEGUNDA_RETIFICACAO).append(" = ? ");
			}
			// NUMERO PROTOCOLO
			if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getProtocolo()))
			{
				sql.append(" AND STATUS.").append(CAMPO_PROTOCOLO).append(" = ? ");
			}
			// DATA PROCOTOLO
			if (Validador.isDataValida((statusGIAITCDVo.getParametroConsulta()).getDataProtocolo()))
			{
				sql.append(" AND STATUS.").append(CAMPO_DATA_PROTOCOLO).append(" = ? ");
			}
			// CODIGO AGENFA
			if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getCodigoAgenfa()))
			{
				sql.append(" AND STATUS.").append(CAMPO_CODIGO_AGENFA_PROTOCOLO).append(" = ? ");
			}
			// PROTOCOLO DESISTENCIA
			if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getProtocoloDesistencia()))
			{
				sql.append(" AND STATUS.").append(CAMPO_PROTOCOLO_DESISTENCIA).append(" = ? ");
			}
			// DATA DESISTENCIA
			if (Validador.isDataValida((statusGIAITCDVo.getParametroConsulta()).getDataDesistencia()))
			{
				sql.append(" AND STATUS.").append(CAMPO_DATA_DESISTENCIA).append(" = ? ");
			}
			// DATA NOTIFICACAO
			if (Validador.isDataValida((statusGIAITCDVo.getParametroConsulta()).getDataNotificacao()))
			{
				sql.append(" AND STATUS.").append(CAMPO_DATA_NOTIFICACAO).append(" = ? ");
			}
			// DATA EMISSAO RETIFICACAO
			if (Validador.isDataValida((statusGIAITCDVo.getParametroConsulta()).getDataEmissaoRetificacao()))
			{
				sql.append(" AND STATUS.").append(CAMPO_DATA_EMISSAO_RETIFICACAO).append(" = ? ");
			}
			// JUSTIFICATIVA REATIVAÇÃO
			if (Validador.isStringValida((statusGIAITCDVo.getParametroConsulta()).getJustificativaReativacao()))
			{
				sql.append(" AND UPPER(STATUS.").append(CAMPO_JUSTIFICATIVA_REATIVACAO).append(") LIKE (UPPER('%" + 
								  (statusGIAITCDVo.getParametroConsulta()).getJustificativaReativacao() + 
								  "%')) ");
			}
			//TIPO AVALIACAO
		   if (Validador.isDominioNumericoValido((statusGIAITCDVo.getParametroConsulta()).getTipoAvaliacao()))
		   {
		      sql.append(" AND STATUS.").append(CAMPO_TIPO_AVALIACAO).append(" = ? ");
		   }	
			//CODIGO DA UNIDADE DE AVALICAO
			if(Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getCodigoUnidadeAvaliacao()))
			{
			   sql.append(" AND STATUS.").append(CAMPO_CODIGO_UNIDADE_AVALIACAO).append(" = ? ");
			}
			//DATA CADASTRO DA AVALICAO
			 if (Validador.isDataValida(( statusGIAITCDVo.getParametroConsulta()).getDataCadastroAvaliacao()))
			 {
			    sql.append(" AND STATUS.").append(CAMPO_DATA_CADASTRO_AVALIACAO).append(" = ? ");
			 }
		   // CODIGO LOG
		   //if (Validador.isObjetoValido((statusGIAITCDVo.getParametroConsulta()).getLogITCDVo().getCodigo()))
		   //{
		   //   sql.append(" AND STATUS.").append(CAMPO_ITCTB48_NUMR_LOG_SEQ).append(" = ? ");
		   //}
          
          
		}
		sql.append(" ORDER BY STATUS.").append(CAMPO_CODIGO_STATUS_ITCD).append(" DESC ");
		return sql.toString();
	}

	/**
	 * Método que monta o SQL de consulta de status para consulta usando um grupo (array) de dominios e o código da Agenfa de Protocolo
	 * @param arrayStatus
	 * @param codigoAgenfa
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindStatusGIAITCDByStatusGroupAgenfa(DomnStatusGIAITCD[] arrayStatus, int codigoAgenfa)
	{
	   StringBuilder sql = new StringBuilder();
		sql.append(getSQLBase());
		sql.append(" WHERE STATUS.").append(CAMPO_CODIGO_STATUS_ITCD).append(" = ");
		sql.append(" (SELECT MAX(STAT.CODG_STATUS_ITCD) FROM ITC.ITCTB27_STATUS_ITCD STAT WHERE STAT.ITCTB14_CODG_ITCD = STATUS.ITCTB14_CODG_ITCD) ");
		sql.append(" AND STATUS.").append(CAMPO_STAT_ITCD).append(" in (");
		for (int i = 0; i < arrayStatus.length; i++)
		{
			if(i+1 >= arrayStatus.length)
			{
			   sql.append(arrayStatus[i].getValorCorrente());
			   sql.append(") ");				
			}
			else
			{
			   sql.append(arrayStatus[i].getValorCorrente());
			   sql.append(",");				
			}
		}
		if (Validador.isNumericoValido(codigoAgenfa))
		{
			sql.append(" AND STATUS.").append(CAMPO_CODIGO_AGENFA_PROTOCOLO).append(" = ").append(codigoAgenfa);
		}
		sql.append(" ORDER BY STATUS.").append(CAMPO_ITCTB14_CODIGO_ITCD);
      //System.out.println("\n\n SQL : StatusPorGrupoAgenfa : "+sql.toString());
		return sql.toString();
	}

	/**
	 * Método para listar os Status da GIA de acordo com um array de dominio de status e o código da Agenfa para Protocolo
	 * @param arrayStatus
	 * @param codigoAgenfa
	 * @return
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	public StatusGIAITCDVo listStatusGIAITCDByStatusGroupAgenfa(DomnStatusGIAITCD[] arrayStatus, int codigoAgenfa) throws ConsultaException, 
			  ObjetoObrigatorioException
	{
		StatusGIAITCDVo statusGIAITCDVo = new StatusGIAITCDVo();
		PreparedStatement ps = null;
		ResultSet rs = null;
      String querySql = "";
		try
		{
		   querySql = getSQLFindStatusGIAITCDByStatusGroupAgenfa(arrayStatus, codigoAgenfa);
			ps = conn.prepareStatement(querySql);
			rs = ps.executeQuery();
		   statusGIAITCDVo.setCollVO(new LinkedList<StatusGIAITCDVo>());
			while (rs.next())
			{
				StatusGIAITCDVo statusGIAITCDAtualVo = new StatusGIAITCDVo();
				getStatusGIAITCD(rs, statusGIAITCDAtualVo);
				statusGIAITCDVo.getCollVO().add(statusGIAITCDAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_STATUS_GIA_ITCD +"\n\n"+ querySql);
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
		return statusGIAITCDVo;
	}

	/**
	 * Método que retorna a lista de status de uma GIA, ordenada de forma decrescente de acordo com a chave primária da tabela de Status.<br>
	 * Exemplo:<br>
	 * 
	 * 	statusGIAITCDvo.getCollVO().get(0) = Status atual.
	 *    statusGIAITCDvo.getCollVO().get(1) = Último Status da GIA.
	 * 
	 * @param statusGIAITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return StatusGIAITCDVo
	 * @implemented by Daniel Balieiro
	 */
	public StatusGIAITCDVo listStatusGIAITCD(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(statusGIAITCDVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindStatusGIAITCD(statusGIAITCDVo));
			prepareStatementFindStatusGIAITCD(ps, statusGIAITCDVo);
			rs = ps.executeQuery();
			statusGIAITCDVo.setCollVO(new LinkedList());
			while (rs.next())
			{
				StatusGIAITCDVo statusGIAITCDAtualVo = new StatusGIAITCDVo();
				getStatusGIAITCD(rs, statusGIAITCDAtualVo);
				statusGIAITCDVo.getCollVO().add(statusGIAITCDAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_STATUS_GIA_ITCD);
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
		return statusGIAITCDVo;
	}

	/**
	 * Método que retorna o Status anterior desta GIA-ITCD
	 * @param statusGIAITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public StatusGIAITCDVo obterStatusGIAITCDAnterior(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(statusGIAITCDVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		StatusGIAITCDVo retorno = new StatusGIAITCDVo();
		try
		{
			ps = conn.prepareStatement(getSQLFindStatusGIAITCDAnterior(statusGIAITCDVo));
			prepareStatementFindStatusGIAITCDAnterior(ps, statusGIAITCDVo);
			rs = ps.executeQuery();
			int contador = statusGIAITCDVo.getOrdemAparicaoStatus();
			do
			{
			   if (rs.next())
			   {
			      getStatusGIAITCD(rs, retorno);
					contador--;
			   }				
			}while(contador > 0);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_STATUS_GIA_ITCD);
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
		return retorno;
	}

	/**
	 * Método que monta a SQL de consulta do Status anterior da GIA-ITCD 
	 * @param statusGIAITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindStatusGIAITCDAnterior(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(statusGIAITCDVo);
	   StringBuilder sql = new StringBuilder();
		sql.append(getSQLBase());
		sql.append(" WHERE 1 = 1 ");
		if (statusGIAITCDVo.isConsultaParametrizada())
		{
			// CODIGO GIA
			if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
			{
				sql.append(" AND STATUS.").append(CAMPO_ITCTB14_CODIGO_ITCD).append(" = ? ");
			}
			if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getGiaTemporaria().getCodigo()))
			{
				sql.append(" AND STATUS.").append(CAMPO_CODIGO_GIA_TEMPORARIA).append(" = ? ");
			}			
		   // STATUS
		   if (Validador.isDominioNumericoValido((statusGIAITCDVo.getParametroConsulta()).getStatusGIAITCD()))
		   {
				sql.append("AND STATUS.").append(CAMPO_CODIGO_STATUS_ITCD).append(" < (");
					sql.append("SELECT MAX(STAT.").append(CAMPO_CODIGO_STATUS_ITCD).append(") FROM ").append(TabelasITC.TABELA_STATUS_GIA_ITCD).append(" STAT ");
					sql.append(" WHERE 1=1 ");
					if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
					{
						sql.append(" AND STAT.").append(CAMPO_ITCTB14_CODIGO_ITCD).append(" = ? ");
					}
					if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getGiaTemporaria().getCodigo()))
					{
						sql.append(" AND STAT.").append(CAMPO_CODIGO_GIA_TEMPORARIA).append(" = ? ");
					}   					
					sql.append(" AND STAT.").append(CAMPO_STAT_ITCD).append(" = ? ");					
				sql.append(")");
		   }
			
		}
		sql.append(" ORDER BY STATUS.").append(CAMPO_CODIGO_STATUS_ITCD).append(" DESC ");
		return sql.toString();
	}

	/**
	 * Método que monta o Prepare Statement de consulta do Status Anterior da GIA-ITCD 
	 * @param ps
	 * @param statusGIAITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindStatusGIAITCDAnterior(final PreparedStatement ps, final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(statusGIAITCDVo);
		int contador = 0;
		if (statusGIAITCDVo.isConsultaParametrizada())
		{
			// CODIGO GIA
			if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
			{
				ps.setLong(++contador, ( statusGIAITCDVo.getParametroConsulta()).getGiaITCDVo().getCodigo());
			}
		   if (Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getGiaTemporaria().getCodigo()))
		   {
		      ps.setLong(++contador, ( statusGIAITCDVo.getParametroConsulta()).getGiaTemporaria().getCodigo());
		   }
		   if (Validador.isDominioNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getStatusGIAITCD()))
		   {
		      if (Validador.isNumericoValido((statusGIAITCDVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
		      {
		         ps.setLong(++contador, ( statusGIAITCDVo.getParametroConsulta()).getGiaITCDVo().getCodigo());
		      }
		      if (Validador.isNumericoValido(( statusGIAITCDVo.getParametroConsulta()).getGiaTemporaria().getCodigo()))
		      {
		         ps.setLong(++contador, ( statusGIAITCDVo.getParametroConsulta()).getGiaTemporaria().getCodigo());
		      }                 			
		      ps.setInt(++contador, ( statusGIAITCDVo.getParametroConsulta()).getStatusGIAITCD().getValorCorrente());
		   }
		}
	}
   
   private String getSQLFindStatusGIAITCDByStatusGroup(DomnStatusGIAITCD[] arrayStatus){
      StringBuilder sql = new StringBuilder();
      sql.append(getSQLBase());
      sql.append(" WHERE STATUS.").append(CAMPO_CODIGO_STATUS_ITCD).append(" = ");
      sql.append(" (SELECT MAX(STAT.CODG_STATUS_ITCD) FROM ITC.ITCTB27_STATUS_ITCD STAT WHERE STAT.ITCTB14_CODG_ITCD = STATUS.ITCTB14_CODG_ITCD) ");
      sql.append(" AND STATUS.").append(CAMPO_STAT_ITCD).append(" in (");
      for (int i = 0; i < arrayStatus.length; i++){
         if(i+1 >= arrayStatus.length){
            sql.append(arrayStatus[i].getValorCorrente());
            sql.append(") ");          
         }else{
            sql.append(arrayStatus[i].getValorCorrente());
            sql.append(",");           
         }
      }
      sql.append(" AND STATUS.").append(CAMPO_DATA_ATUALIZACAO_BD).append(" > TO_DATE('31/12/2023','dd/MM/yyyy')"); //Alterar para '31/12/2023', antes de subir para produção
      sql.append(" ORDER BY STATUS.").append(CAMPO_ITCTB14_CODIGO_ITCD);
      return sql.toString();
   }   
     
     public StatusGIAITCDVo listStatusGIAITCDByStatusGroup(DomnStatusGIAITCD[] arrayStatus) throws ConsultaException, ObjetoObrigatorioException {
         StatusGIAITCDVo statusGIAITCDVo = new StatusGIAITCDVo();
         PreparedStatement ps = null;
         ResultSet rs = null;
         String querySql = "";
         try{
            querySql = getSQLFindStatusGIAITCDByStatusGroup(arrayStatus);
            ps = conn.prepareStatement(querySql);
            rs = ps.executeQuery();
            statusGIAITCDVo.setCollVO(new LinkedList());
            while (rs.next()){
               StatusGIAITCDVo statusGIAITCDAtualVo = new StatusGIAITCDVo();
               getStatusGIAITCD(rs, statusGIAITCDAtualVo);
               statusGIAITCDVo.getCollVO().add(statusGIAITCDAtualVo);
              }
         }
         catch (SQLException e){
            ExibirLOG.exibirLogSimplificado("Erro - SQL: " + e.getMessage());
            throw new ConsultaException(MensagemErro.CONSULTAR_STATUS_GIA_ITCD +"\n\n"+ querySql);
         }
         finally{
            try{
               close(ps, rs);
               ps = null;
               rs = null;
              }
            catch (SQLException e)
            {
               ExibirLOG.exibirLogSimplificado("Erro - SQL: " + e.getMessage());
            }
         }
         return statusGIAITCDVo;
      }   
}
