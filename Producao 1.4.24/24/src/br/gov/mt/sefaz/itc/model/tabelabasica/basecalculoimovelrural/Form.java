package br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural;

import br.gov.mt.sefaz.itc.util.facade.FormITC;


public interface Form extends FormITC
{
   // CAMPOS
   public static final String CAMPO_SELECT_TIPO_DISTANCIA = "campoSelectTipoDistancia";
   public static final String CAMPO_SELECT_TIPO_ATIVIDADE = "campoSelectTipoAtividade";
   public static final String CAMPO_QUANTIDADE_DISTANCIA_INICIAL= "campoQuantidadeDistanciaInicial";
   public static final String CAMPO_QUANTIDADE_DISTANCIA_FINAL= "campoQuantidadeDistanciaFinal";
   public static final String CAMPO_PERCENTUAL_ATIVIDADE_INICAL = "campoPercentualAtividadeInicial";
   public static final String CAMPO_PERCENTUAL_ATIVIDADE_FINAL= "campoPercentualAtividadeFinal";
   public static final String CAMPO_PERCENTUAL_AREA_EXPLORA_INICAL = "campoPercentualAreaExploradaInical";
   public static final String CAMPO_PERCENTUAL_AREA_EXPLORA_FINAL = "campoPercentualAreaExploradaFinal";
   public static final String CAMPO_NUMERO_ITEM = "campoNumeroItem";
   public static final String CAMPO_PERCENTUAL_BASE_CALCULO_MINIMO = "campoPercentualBaseCalculoMinimo";
   public static final String CAMPO_SELECT_CRITERIO_BASE_CALCULO = "campoSelectCriterioBaseCalculo";
   
   // BOTOES
   public static final String BOTAO_ADICIONAR_BASE_CALCULO_IMOVEL_RURAL = "botaoAdicionarBaseCalculoImovelRural";
   public static final String BOTAO_SOLICITAR_ALTERAR_BASE_CALCULO_IMOVEL_RURAL = "botaoSolicitarAlterarBaseCalculoImovelRural";
   public static final String BOTAO_INATIVAR_BASE_CALCULO_IMOVEL_RURAL = "botaoInativarBaseCalculoImovelRural";
}
