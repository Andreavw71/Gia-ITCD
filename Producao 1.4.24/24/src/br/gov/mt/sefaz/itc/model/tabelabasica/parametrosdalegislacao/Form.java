package br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao;

import br.gov.mt.sefaz.itc.util.facade.FormITC;


public interface Form extends FormITC
{
	public static final String BOTAO_INCLUIR_ALIQUOTA = "botaoIncluirAliquota";
	public static final String BOTAO_INCLUIR_MULTA = "botaoIncluirMulta";
	public static final String CAMPO_CHECK_ISENCAO = "campoCheckIsencao";
	public static final String CAMPO_DATA_VIGENCIA_FINAL = "campoDataVigenciaFinal";
	public static final String CAMPO_DATA_VIGENCIA_INICIAL = "campoDataVigenciaInicial";
	public static final String CAMPO_NUMERO_LEGISLACAO = "campoNumeroLegislacao";
	public static final String CAMPO_NUMERO_LEGISLACAO_PRINCIPAL = "campoNumeroLegislacaoPrincipal";
	public static final String CAMPO_ANO_LEGISLACAO_PRINCIPAL = "campoAnoLegislacaoPrincipal";
	public static final String CAMPO_PERCENTUAL = "campoPercentual";
	public static final String CAMPO_PERCENTUAL_MULTA = "campoPercentualMulta";
	public static final String CAMPO_QUANTIDADE_DIAS_FINAL = "campoQuantidadeDiasFinal";
	public static final String CAMPO_QUANTIDADE_DIAS_INICIAL = "campoQuantidadeDiasInicial";
	public static final String CAMPO_QUANTIDADE_UPF_FINAL = "campoQuantidadeUPFFinal";
	public static final String CAMPO_QUANTIDADE_UPF_INICIAL = "campoQuantidadeUPFInicial";
	public static final String CAMPO_SELECT_TIPO_FATO_GERADOR = "campoSelectTipoFatoGerador";
	public static final String PARAMETRO_CODIGO_PARAMETRO_LEGISLACAO_ALTERAR = 
		  "parametroCodigoParametroLegislacaoAlterar";
	public static final String PARAMETRO_CODIGO_PARAMETRO_LEGISLACAO_PESQUISAR = 
		  "parametroCodigoParametroLegislacaoPesquisar";
	public static final String PARAMETRO_CONSULTAR_DATA_FINAL_ULTIMA_LEI = "parametroConsultarDataFinalUltimaLei";
	public static final String PARAMETRO_VALIDAR_NUMERO_LEGISLACAO = "parametroValidarNumeroLegislacao";
}
