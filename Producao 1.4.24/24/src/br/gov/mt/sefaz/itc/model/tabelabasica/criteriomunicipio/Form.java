package br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio;

import br.gov.mt.sefaz.itc.util.facade.FormITC;


public interface Form extends FormITC
{

   public static String CAMPO_VALOR_MINIMO = "campoValorMinimo";
   public static String CAMPO_VALOR_MEDIO = "campoValorMedio";
   public static String CAMPO_VALOR_MAXIMO = "campoValorMaximo";
   
   public static final String BOTAO_ADICIONAR_CRITERIO_MUNICIPIO = "botaoAdicionarCriterioMunicipio";
   public static final String BOTAO_EXCLUIR_CRITERIO_MUNICIPIO = "botaoExcluirCriterioMunicipio";
   public static final String CAMPO_SELECT_CRITERIO_MUNICIPIO = "campoSelectCriterioMunicipio";
}
