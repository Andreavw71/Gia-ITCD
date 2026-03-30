package br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao;

import br.gov.mt.sefaz.itc.util.facade.FormITC;


/**
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.1.1.1 $
 */
public interface Form extends FormITC
{
	public static final String CAMPO_SELECT_TIPO_GIA = "campoSelectTipoGIA";
	public static final String CAMPO_SELECT_TIPO_PROCESSO_NATUREZA_OPERACAO = "campoSelectTipoProcessoNaturezaOperacao";
	public static final String CAMPO_SELECT_TIPO_PROCESSO_CAUSA_MORTIS = "campoSelectTipoProcessoCausaMortis";
	public static final String CAMPO_SELECT_TIPO_PROCESSO_INTER_VIVOS = "campoSelectTipoProcessoInterVivos";
	public static final String CAMPO_DESCRICAO_NATUREZA_OPERACAO = "campoDescricaoNaturezaOperacao";
	public static final String CAMPO_PERCENTUAL_NATUREZA_OPERACAO = "campoPercentualNaturezaOperacao";
	public static final String CAMPO_SELECT_TIPO_BASE_CALCULO_NATUREZA_OPERACAO = "campoSelecTipoBaseCalculoNaturezaOperacao";
	public static final String CAMPO_SELECT_ISENCAO_PREVISTA_LEI = "campoSelectIsencaoPrevistaLei";
}
