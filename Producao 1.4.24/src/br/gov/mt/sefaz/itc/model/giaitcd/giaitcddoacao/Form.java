package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao;

import br.gov.mt.sefaz.itc.util.facade.FormITC;


public interface Form extends FormITC
{
	public static final String CAMPO_CHECK_ISENCAO_PREVISTA_LEI = "campoCheckIsencaoPrevistaLei";
	public static final String CAMPO_DATA_FALECIMENTO = "campoDataFalecimento";
	public static final String CAMPO_DESCRICAO = "campoDescricao";
	public static final String CAMPO_FRACAO_IDEAL = "campoFracaoIdeal";
	public static final String CAMPO_HIDDEN_DESCRICAO_DEPOSITO_DESTINATARIO = 
		  "campoHiddenDescricaoDepositoDestinatario";
	public static final String CAMPO_HIDDEN_DESCRICAO_DEPOSITO_REMETENTE = "campoHiddenDescricaoDepositoRemetente";
	public static final String CAMPO_JUIZO_COMARCA = "campoJuizoComarca";
	public static final String CAMPO_JUSTIFICATIVA = "campoJustificativa";
	public static final String CAMPO_NUMERO_DOCUMENTO = "campoNumeroDocumento";
	public static final String CAMPO_NUMERO_HERDEIROS = "campoNumeroHerdeiros";
	public static final String CAMPO_NUMERO_PROCESSO_INVENTARIO_ARROLAMENTO = 
		  "campoNumeroProcessoInventarioArrolamento";
	public static final String CAMPO_SELECT_CLASSIFICACAO_BEM = "campoSelectClassificacaoBem";
	public static final String CAMPO_SELECT_DEPOSITO_DESTINATARIO = "campoSelectDepositoDestinatario";
	public static final String CAMPO_SELECT_ESTADO_CIVIL = "campoSelectEstadoCivil";
	public static final String CAMPO_SELECT_NATUREZA_OPERACAO = "campoSelectNaturezaOperacao";
	public static final String CAMPO_SELECT_REGIME_CASAMENTO = "campoSelectRegimeCasamento";
	public static final String CAMPO_SELECT_TIPO_BEM = "campoSelectTipoBem";
	public static final String CAMPO_TIPO_DOCUMENTO = "campoTipoDocumento";
	public static final String CAMPO_VALOR_MERCADO = "campoValorMercado";
   public static final String CAMPO_CHECK_RECOLH_SOBRE_BASE_CALC = "flagRecolhimentoBaseCalc";
}
