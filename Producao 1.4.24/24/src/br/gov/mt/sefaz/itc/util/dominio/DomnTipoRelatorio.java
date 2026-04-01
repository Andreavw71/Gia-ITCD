package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;

public class DomnTipoRelatorio extends AbstractDominioNumericoAbaco
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   public static final int VALOR_POR_BENEFICIARIO_APOS_AVALIACAO = 1;
   public static final int CREDITO_CONSTITUIDO= 2;
   public static final int ESTOQUE_EM_ABERTO= 3;
   private static final String DESCRICAO_VALOR_POR_BENEFICIARIO_APOS_AVALIACAO = "Relatório Valor por Beneficiário Declarado/Arbitrado e após a Avaliação";
   private static final String DESCRICAO_CREDITO_CONSTITUIDO = "Relatório de GIA-ITCD com Crédito Constituído";
   private static final String DESCRICAO_ESTOQUE_EM_ABERTO = "Relatório de GIA-ITCD -Estoque em Aberto";
   private static int domnIndice[] = { VALOR_POR_BENEFICIARIO_APOS_AVALIACAO, CREDITO_CONSTITUIDO, ESTOQUE_EM_ABERTO };
   private static String domnDescricao[] = { DESCRICAO_VALOR_POR_BENEFICIARIO_APOS_AVALIACAO, DESCRICAO_CREDITO_CONSTITUIDO, DESCRICAO_ESTOQUE_EM_ABERTO};

   /**
    * Instancia o domínio sem valor corrente
    */
   public DomnTipoRelatorio()
   {
      super(-1);
   }

   /**
    * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
    * @param  valorConstante O valor do domínio.
    */
   public DomnTipoRelatorio(int valorConstante)
   {
      super(valorConstante);
   }

   /**
    * Obtém o array com todas as constantes deste domínio.
    * <b>Este método é utilizado pelo tagsgenerico.jar, logo é obrigatório para todos os domínios.</b>
    * @return int[] - array de inteiro com as constantes deste domínio.
    */
   public int[] getDomnIndice()
   {
      return domnIndice;
   }

   /**
    * Obtém o array com todas as descrições das constantes deste domínio.
    * <b>Este método é utilizado pelo tagsgenerico.jar, logo é obrigatório para todos os domínios.</b>
    * @return String[] - array de String com as constantes deste domínio.
    */
   public String[] getDomnDescricao()
   {
      return domnDescricao;
   }

   public int getKey()
   {
      return super.getValorCorrente();
   }
}
