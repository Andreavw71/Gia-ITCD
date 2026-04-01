package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnTipoOperacao extends AbstractDominioNumericoAbaco
{


   private static final long serialVersionUID = Long.MAX_VALUE;
   public static final int INCLUSAO = 1;
   public static final int ALTERACAO = 2;
   public static final int EXCLUSAO = 3;


   public static final String DESC_INCLUSAO = "Inclusão";
   public static final String DESC_ALTERACAO = "Alteração";
   public static final String DESC_EXCLUSAO = "Exclusão";

   public static int domnIndice[] =
   { INCLUSAO, ALTERACAO, 
     EXCLUSAO };

   public static String domnDescricao[] =
   { DESC_INCLUSAO, 
     DESC_ALTERACAO, 
     DESC_EXCLUSAO };


   /**
    * Instancia o domínio sem valor corrente
    */
   public DomnTipoOperacao()
   {
      super(-1);
   }

   /**
    * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
    * @param  valorConstante O valor do domínio.
    */
   public DomnTipoOperacao(int valorConstante)
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
