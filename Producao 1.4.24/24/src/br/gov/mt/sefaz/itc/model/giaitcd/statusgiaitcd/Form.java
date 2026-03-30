package br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd;

import br.gov.mt.sefaz.itc.util.facade.FormITC;

/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: Form.java
 * Revisão: Elizabeth Barbosa Moreira
 * Data revisão:
 */

/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: Form.java
 * Revisão: Elizabeth Barbosa Moreira
 * Data revisão: 
 */
public interface Form extends FormITC
{
	public static final String CAMPO_NUMERO_PROTOCOLO = "campoNumeroProtocolo";
	public static final String CAMPO_DATA_PROTOCOLO = "campoNumeroDataProtocolo";
	public static final String CAMPO_AGENFA_PROTOCOLO = "campoAgenfaProtocolo";
	public static final String CAMPO_AGENFA_PROTOCOLO_HIDDEN = "campoAgenfaProtocoloHidden";
	public static final String CAMPO_DATA_PERMISSAO = "campoDataPermissao";
	public static final String CAMPO_OBSERVACAO = "campoObservacao";
	public static final String CAMPO_NUMERO_PROTOCOLO_PARCELAMENTO = "campoNumeroProtocoloParcelamento";
	public static final String CAMPO_QUANTIDADE_PARCELAS = "campoQuantidadeParcelas";
	public static final String CAMPO_DATA_PARCELAMENTO = "campoDataParcelamento";
	public static final String CAMPO_DATA_IMPUGNACAO = "campoDataImpugnacao";
	public static final String CAMPO_NUMERO_PROTOCOLO_IMPUGNACAO = "campoNumeroProtocoloImpugnacao";
	public static final String CAMPO_DATA_CIENCIA_NOTIFICACAO = "campoDataCienciaNotificacao";
	public static final String CAMPO_DATA_ENVIO_DIVIDA_ATIVA = "campoDataEnvioDividaAtiva";
	public static final String CAMPO_DATA_CIENCIA_SEGUNDA_NOTIFICACAO = "campoDataCienciaSegundaNotificacao";
	public static final String CAMPO_DATA_CIENCIA_NOTIFICACAO_RATIFICACAO = "campoDataCienciaNotificacaoRafiticacao";
	public static final String CAMPO_DATA_EMISSAO_DAR_RATIFICACAO = "campoDataEmissaoDarRafiticacao";
	public static final String CAMPO_NUMERO_DAR_RATIFICACAO = "campoNumeroDarRafiticacao";
	public static final String CAMPO_DATA_EMISSAO_DAR_SEGUNDA_RETIFICACAO = "campoDataEmissaoDarRafiticacao";
	public static final String CAMPO_NUMERO_DAR_SEGUNDA_RETIFICACAO = "campoNumeroDarSegundaRatificacao";
	public static final String CAMPO_DATA_CIENCIA_RETIFICACAO = "campoDataCienciaRetificacao";
	public static final String CAMPO_DATA_CIENCIA_SEGUNDA_RETIFICACAO = "campoDataCienciaSegundaRetificacao";
	public static final String CAMPO_SELECT_AGENFA_PROTOCOLO = "campoSelectAgenfaProtocolo";
	public static final String CAMPO_DATA_QUITACAO = "campoDataQuitacao";
	public static final String CAMPO_NUMERO_DAR_QUITACAO = "campoNumeroDarQuitacao";
	public static final String CAMPO_DATA_ENVIO_INSCRICAO_DIVIDA_ATIVA = "campoDataEnvioInscricaoDividaAtiva";
	public static final String CAMPO_JUSTIFICATIVA_ENVIO_INSCRICAO_DIVIDA_ATIVA = "campoJustificativaEnvioInscricaoDividaAtiva";
	public static final String CAMPO_JUSTIFICATIVA_ENVIO_DIVIDA_ATIVA = "campoJustificativaEnvioDividaAtiva";
	public static final String CAMPO_MOTIVO_IMPUGNACAO = "campoMotivoImpugnacao";
	public static final String CAMPO_DATA_SEGUNDA_RETIFICACAO = "campoDataSegundaRetificacao";
	public static final String CAMPO_VALOR_IMPOSTO_SEGUNDA_RETIFICACAO = "campoValorImpostoSegundaRetificacao";
	public static final String CAMPO_VALOR_IMPOSTO_RATIFICACAO = "campoValorImpostoRatificacao";
	public static final String CAMPO_DATA_RATIFICACAO = "campoDataRatificacao";
	public static final String CAMPO_DATA_CIENCIA_RATIFICACAO = "campoDataCienciaRatificacao";
	public static final String CAMPO_JUSTIFICATIVA_RETORNO_PARCELAMENTO = "campoJustificativaRetornoParcelamento";
   public static final String CAMPO_DATA_ENVIO_CCF = "campoDataEnvioCCF";
   public static final String CAMPO_NUMERO_PROTOCOLO_CCF = "campoNumeroProtocoloCcf";
   public static final String CAMPO_OBSERVACAO_ISENTO = "campoObservacaoIsento";
   public static final String CAMPO_OBSERVACAO_ENVIADO_CCF = "campoObservacaoEnviadoCCF";
   public static final String CAMPO_OBSERVACAO_QUITADO_CCF = "campoObservcaoQuitadoCCF";
   
	public static final String TD_CAMPOS = "tdTodosCampos";
	public static final String DIV = "div";
	public static final String TD_CAMPO = "tdCampo";
	
	public static final String BOTAO_ADICIONAR_DAR = "botaoAdicionarDar";
   public static final String BOTAO_EXCLUIR_DAR = "botaoExcluirDar";
}
