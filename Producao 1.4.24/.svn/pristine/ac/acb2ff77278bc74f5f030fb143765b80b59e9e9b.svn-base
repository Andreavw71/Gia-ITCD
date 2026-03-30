package br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.SefazDataHora;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


/**
 * Classe para manutenção de dados do Status da GIA no banco de dados
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
public class StatusGIAITCDDao extends AbstractDao implements TabelasITC, CamposStatusGIAITCD, SequencesITC
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public StatusGIAITCDDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_STATUS_GIA_ITCD);
	}

	/**
	 * Retorna os campos da tabela de Status da GIA
	 * 
	 * @return String[]
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{
		return new String[] 
		{ 
			CAMPO_CODIGO_STATUS_ITCD
			, CAMPO_ITCTB14_CODIGO_ITCD
			, CAMPO_SGPTB06_NUMERO_MATRICULA_SERVIDOR
			, CAMPO_STAT_ITCD
			, CAMPO_DATA_ATUALIZACAO_BD, CAMPO_DATA_PERMISSAO
			, CAMPO_OBSERVACAO, CAMPO_PROTOCOLO_PARCELAMENTO
			, CAMPO_NUMERO_PARCELAS, CAMPO_DATA_PARCELAMENTO
			, CAMPO_PROTOCOLO_IMPUGNACAO, CAMPO_DATA_IMPUGNACAO
			, CAMPO_DATA_RATIFICACAO, CAMPO_NUMERO_DAR_RATIFICACAO
			, CAMPO_DATA_EMISSAO_SEGUNDA_RETIFICACAO
			, CAMPO_NUMERO_DAR_SEGUNDA_RETIFICACAO
			, CAMPO_DATA_CIENCIA_RETIFICACAO
			, CAMPO_DATA_ENVIO_DIVIDA_ATIVA
			, CAMPO_DATA_CIENCIA_SEGUNDA_RETIFICACAO
			, CAMPO_PROTOCOLO
			, CAMPO_DATA_PROTOCOLO
			, CAMPO_CODIGO_AGENFA_PROTOCOLO
			, CAMPO_PROTOCOLO_DESISTENCIA, CAMPO_DATA_DESISTENCIA
			, CAMPO_DATA_NOTIFICACAO
			, CAMPO_DATA_EMISSAO_RETIFICACAO
			, CAMPO_JUSTIFICATIVA_REATIVACAO
			, CAMPO_TIPO_AVALIACAO
			, CAMPO_CODIGO_UNIDADE_AVALIACAO
			, CAMPO_DATA_CADASTRO_AVALIACAO
			};
	}

	/**
	 * Método para inserir um Status da GIA no banco de dados
	 * 
	 * @param statusGIAITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertStatusGIAITCD(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(statusGIAITCDVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			GeradorLogSefazMT.gerar(statusGIAITCDVo, DomnOperacao.OPERACAO_INSERT, statusGIAITCDVo.getNumeroParticao(), statusGIAITCDVo.getCodigoTransacao(), statusGIAITCDVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			SefazSequencia sequence = new SefazSequencia(conn);
			statusGIAITCDVo.setCodigo(sequence.next(SEQUENCE_STATUS_GIA_ITCD));
			ps = conn.prepareStatement(sql);
			preparedStatementInsertStatusGIAITCD(ps, statusGIAITCDVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_STATUS_GIA_ITCD);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_STATUS_GIA_ITCD);
		}
		finally
		{
			if (ps != null)
			{
				try
				{
					close(ps);
					ps = null;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Método para montar o PreparedStatement com os dados válidos do StatusGIAITCDVo
	 * 
	 * @param ps
	 * @param statusGIAITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertStatusGIAITCD(final PreparedStatement ps, final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
//		StringBuffer sb = new StringBuffer("(");
		Validador.validaObjeto(ps);
		Validador.validaObjeto(statusGIAITCDVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(statusGIAITCDVo.getCodigo()))
		{
			ps.setLong(++contador, statusGIAITCDVo.getCodigo());
//		   sb.append(statusGIAITCDVo.getCodigo()).append(", ");
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
//		   sb.append("null, ");
		}
		// CODIGO ITCD
		if (Validador.isNumericoValido(statusGIAITCDVo.getGiaITCDVo().getCodigo()))
		{
			ps.setLong(++contador, statusGIAITCDVo.getGiaITCDVo().getCodigo());
//		   sb.append(statusGIAITCDVo.getGiaITCDVo().getCodigo()).append(", ");
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
//		   sb.append("null, ");
		}
		// NUMERO MATRICULA DO SERVIDOR
		if (Validador.isNumericoValido(statusGIAITCDVo.getServidor().getNumrMatricula()))
		{
			ps.setLong(++contador, statusGIAITCDVo.getServidor().getNumrMatricula().longValue());
//		   sb.append(statusGIAITCDVo.getServidor().getNumrMatricula().longValue()).append(", ");
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
//		   sb.append("null, ");
		}
		// STATUS
		if (Validador.isDominioNumericoValido(statusGIAITCDVo.getStatusGIAITCD()))
		{
			ps.setInt(++contador, statusGIAITCDVo.getStatusGIAITCD().getValorCorrente());
//		   sb.append(statusGIAITCDVo.getStatusGIAITCD().getValorCorrente()).append(", '");
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
//		   sb.append("null, '");
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
//	   sb.append(new java.sql.Date(SefazDataHora.getDataHoraAtual().getTime()).toString()).append("', '");
		// DATA PERMISSAO
		if (Validador.isDataValida(statusGIAITCDVo.getDataPermissao()))
		{
			ps.setDate(++contador, new java.sql.Date(statusGIAITCDVo.getDataPermissao().getTime()));
//		   sb.append(new java.sql.Date(statusGIAITCDVo.getDataPermissao().getTime()).toString()).append("', '");
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
//		   sb.append("null, '");
		}
		// OBSERVACAO
		if (Validador.isStringValida(statusGIAITCDVo.getObservacao()))
		{
			ps.setString(++contador, statusGIAITCDVo.getObservacao());
//		   sb.append(statusGIAITCDVo.getObservacao()).append("', ");
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
//		   sb.append("null, ");
		}
		// NUMERO PROTOCOLO PARCELAMENTO
		if (Validador.isNumericoValido(statusGIAITCDVo.getProtocoloParcelamento()))
		{
			ps.setLong(++contador, statusGIAITCDVo.getProtocoloParcelamento());
//		   sb.append(statusGIAITCDVo.getProtocoloParcelamento()).append(", ");
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
//		   sb.append("null, ");
		}
		// NUMERO PARCELAS
		if (Validador.isNumericoValido(statusGIAITCDVo.getNumeroParcelas()))
		{
			ps.setInt(++contador, statusGIAITCDVo.getNumeroParcelas());
//		   sb.append(statusGIAITCDVo.getNumeroParcelas()).append(", '");
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
//		   sb.append("null, '");
		}
		// DATA PARCELAMENTO
		if (Validador.isDataValida(statusGIAITCDVo.getDataParcelamento()))
		{
			ps.setDate(++contador, new java.sql.Date(statusGIAITCDVo.getDataParcelamento().getTime()));
//		   sb.append(new java.sql.Date(statusGIAITCDVo.getDataParcelamento().getTime()).toString()).append("', ");
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
//		   sb.append("null, ");
		}
		// PROTOCOLO IMPUGNACAO
		if (Validador.isNumericoValido(statusGIAITCDVo.getProtocoloImpugnacao()))
		{
			ps.setLong(++contador, statusGIAITCDVo.getProtocoloImpugnacao());
//		   sb.append(statusGIAITCDVo.getProtocoloImpugnacao()).append(", '");
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
//		   sb.append("null, '");
		}
		// DATA IMPUGNACAO
		if (Validador.isDataValida(statusGIAITCDVo.getDataImpugnacao()))
		{
			ps.setDate(++contador, new java.sql.Date(statusGIAITCDVo.getDataImpugnacao().getTime()));
//		   sb.append(new java.sql.Date(statusGIAITCDVo.getDataImpugnacao().getTime())).append("', '");
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
//		   sb.append("null, '");
		}
		// DATA RATIFICACAO
		if (Validador.isDataValida(statusGIAITCDVo.getDataRatificacao()))
		{
			ps.setDate(++contador, new java.sql.Date(statusGIAITCDVo.getDataRatificacao().getTime()));
//		   sb.append(new java.sql.Date(statusGIAITCDVo.getDataRatificacao().getTime()).toString()).append("', ");
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
//		   sb.append("null, ");
		}
		// NUMERO DAR RATIFICACAO
		if (Validador.isNumericoValido(statusGIAITCDVo.getNumeroDARRatificacao()))
		{
			ps.setLong(++contador, statusGIAITCDVo.getNumeroDARRatificacao());
//		   sb.append(statusGIAITCDVo.getNumeroDARRatificacao()).append(", '");
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
//		   sb.append("null, '");
		}
		// DATA SEGUNDA RETIFICACAO
		if (Validador.isDataValida(statusGIAITCDVo.getDataEmissaoSegundaRetificacao()))
		{
			ps.setDate(++contador, new java.sql.Date(statusGIAITCDVo.getDataEmissaoSegundaRetificacao().getTime()));
//		   sb.append(new java.sql.Date(statusGIAITCDVo.getDataEmissaoSegundaRetificacao().getTime()).toString()).append("', ");
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
//		   sb.append("null, ");
		}
		// NUMERO DAR SEGUNDA RETIFICACAO
		if (Validador.isNumericoValido(statusGIAITCDVo.getNumeroDARSegundaRetificacao()))
		{
			ps.setLong(++contador, statusGIAITCDVo.getNumeroDARSegundaRetificacao());
//		   sb.append(statusGIAITCDVo.getNumeroDARSegundaRetificacao()).append(", '");
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
//		   sb.append("null, '");
		}
		// DATA CIENCIA RETIFICACAO
		if (Validador.isDataValida(statusGIAITCDVo.getDataCienciaRetificacao()))
		{
			ps.setDate(++contador, new java.sql.Date(statusGIAITCDVo.getDataCienciaRetificacao().getTime()));
//		   sb.append(new java.sql.Date(statusGIAITCDVo.getDataCienciaRetificacao().getTime())).append("', '");
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
//		   sb.append("null, '");
		}
		// DATA ENVIO DIVIDA ATIVA
		if (Validador.isDataValida(statusGIAITCDVo.getDataEnvioDividaAtiva()))
		{
			ps.setDate(++contador, new java.sql.Date(statusGIAITCDVo.getDataEnvioDividaAtiva().getTime()));
//		   sb.append(new java.sql.Date(statusGIAITCDVo.getDataEnvioDividaAtiva().getTime()).toString()).append("', '");
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
//		   sb.append("null, '");
		}
		// DATA CIENCIA SEGUNDA RETIFICACAO
		if (Validador.isDataValida(statusGIAITCDVo.getDataCienciaSegundaRetificacao()))
		{
			ps.setDate(++contador, new java.sql.Date(statusGIAITCDVo.getDataCienciaSegundaRetificacao().getTime()));
//		   sb.append(new java.sql.Date(statusGIAITCDVo.getDataCienciaSegundaRetificacao().getTime()).toString()).append("', ");
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
//		   sb.append("null, ");
		}
		// PROTOCOLO
		if (Validador.isNumericoValido(statusGIAITCDVo.getProtocolo()))
		{
			ps.setLong(++contador, statusGIAITCDVo.getProtocolo());
//		   sb.append(statusGIAITCDVo.getProtocolo()).append(", '");
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
//		   sb.append("null, '");
		}
		// DATA PROTOCOLO
		if (Validador.isDataValida(statusGIAITCDVo.getDataProtocolo()))
		{
			ps.setDate(++contador, new java.sql.Date(statusGIAITCDVo.getDataProtocolo().getTime()));
//		   sb.append(new java.sql.Date(statusGIAITCDVo.getDataProtocolo().getTime()).toString()).append("', ");
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
//		   sb.append("null, ");
		}
		// CODIGO AGENFA PROTOCOLO
		if (Validador.isNumericoValido(statusGIAITCDVo.getCodigoAgenfa()))
		{
			ps.setInt(++contador, statusGIAITCDVo.getCodigoAgenfa());
//		   sb.append(statusGIAITCDVo.getCodigoAgenfa()).append(", ");
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
//		   sb.append("null, ");
		}
		// PROTOCOLO DESISTENCIA
		if (Validador.isNumericoValido(statusGIAITCDVo.getProtocoloDesistencia()))
		{
			ps.setLong(++contador, statusGIAITCDVo.getProtocoloDesistencia());
//		   sb.append(statusGIAITCDVo.getProtocoloDesistencia()).append(", '");
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
//		   sb.append("null, '");
		}
		// DATA DESISTENCIA
		if (Validador.isDataValida(statusGIAITCDVo.getDataDesistencia()))
		{
			ps.setDate(++contador, new java.sql.Date(statusGIAITCDVo.getDataDesistencia().getTime()));
//		   sb.append(new java.sql.Date(statusGIAITCDVo.getDataDesistencia().getTime())).append("', '");
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
//		   sb.append("null, '");
		}
		// DATA NOTIFICACAO
		if (Validador.isDataValida(statusGIAITCDVo.getDataNotificacao()))
		{
			ps.setDate(++contador, new java.sql.Date(statusGIAITCDVo.getDataNotificacao().getTime()));
//		   sb.append(new java.sql.Date(statusGIAITCDVo.getDataNotificacao().getTime()).toString()).append("', '");
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
//		   sb.append("null, '");
		}
		// DATA EMISSAO RETIFICACAO
		if (Validador.isDataValida(statusGIAITCDVo.getDataEmissaoRetificacao()))
		{
			ps.setDate(++contador, new java.sql.Date(statusGIAITCDVo.getDataEmissaoRetificacao().getTime()));
//		   sb.append(new java.sql.Date(statusGIAITCDVo.getDataEmissaoRetificacao().getTime()).toString()).append("', '");
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
//		   sb.append("null, '");
		}
		// JUSTIFICATIVA REATIVACAO
		if (Validador.isStringValida(statusGIAITCDVo.getJustificativaReativacao()))
		{
			ps.setString(++contador, statusGIAITCDVo.getJustificativaReativacao());
//		   sb.append(statusGIAITCDVo.getJustificativaReativacao()).append("', ");
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
//		   sb.append("null, ");
		}
		//
	   if (Validador.isDominioNumericoValido(statusGIAITCDVo.getTipoAvaliacao()))
	   {
	      ps.setInt(++contador, statusGIAITCDVo.getTipoAvaliacao().getValorCorrente());
//	      sb.append(statusGIAITCDVo.getTipoAvaliacao().getValorCorrente()).append(");");
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
//	      sb.append("null);");
	   }
		//codigo unidade de avaliacao
		if(Validador.isNumericoValido(statusGIAITCDVo.getCodigoUnidadeAvaliacao()))
		{
			ps.setInt(++contador, statusGIAITCDVo.getCodigoUnidadeAvaliacao());
		}
		else
		{
		   ps.setNull(++contador, Types.NUMERIC);
		}
		//DATA CADASTRO DA AVALICAO
		if(Validador.isDataValida(statusGIAITCDVo.getDataCadastroAvaliacao()))
		{
			ps.setDate(++contador, new java.sql.Date(statusGIAITCDVo.getDataCadastroAvaliacao().getTime()));
		}
		else
		{
		   ps.setNull(++contador, Types.DATE);
		}
		
//	   System.out.println(sb.toString());
	}
}
