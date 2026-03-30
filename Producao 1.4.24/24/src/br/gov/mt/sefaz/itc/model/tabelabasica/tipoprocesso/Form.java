package br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso;

import br.gov.mt.sefaz.itc.util.facade.FormITC;


public interface Form extends FormITC
{   
   //CAMPOS
   public static final String CAMPO_TIPOS_DE_PROCESSOS = "campoSelectTipoProcesso";
   public static final String CAMPO_CODIGO_GIA = "CAMPO_CODIGO_GIA";
   public static final String TIPO_PROCESSO = "selectTipoProcesso";
   public static final String TIPO_PROCESSO_EPROCESS = "selectTipoProcessoEprocess";
   public static final String CAMPO_DESCRICAO_PROCESSO = "campoDescricaoProcesso";
   public static final String URL_VALIDAR = "urlValidar";
   //BOTAO
   public static final String BOTAO_PESQUISAR_GIA_ITCD = "botaoPesquisarGiaITCD";
   public static final String BOTAO_INCLUIR_PROCESSO_EPROCESS = "botaoIncluirProcesso";
   public static final String BOTAO_VALIDAR_PROCESSO_EPROCESS = "botaoValidarProcessoEprocess";
   public static final String PARAMETRO_CRIPTOGRAFADO_VALIDAR_PROTOCOLO = "paramEprocessCrypt";
}
