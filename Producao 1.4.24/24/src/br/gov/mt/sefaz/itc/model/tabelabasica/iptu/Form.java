package br.gov.mt.sefaz.itc.model.tabelabasica.iptu;

import br.gov.mt.sefaz.itc.util.facade.FormITC;


public interface Form extends FormITC
{
      
   public static String CAMPO_VALOR_PERCENTUAL_ESTIMADO = "campoValorPercentualEstimado";
   public static String CAMPO_VALOR_METRO_TERRITORIAL = "campoValorMetroTerritorial";
   public static String CAMPO_VALOR_METRO_PREDIAL = "campoValorMetroPredial";
   public static final String CAMPO_SELECT_TIPO_IPTU = "campoSelectTipoITPU";
   
   
   public static final String BOTAO_ALTERAR_IPTU = "botaoAlterarIPTU";
   public static final String BOTAO_ADICIONAR_IPTU = "botaoAdicionarIPTU";
}
