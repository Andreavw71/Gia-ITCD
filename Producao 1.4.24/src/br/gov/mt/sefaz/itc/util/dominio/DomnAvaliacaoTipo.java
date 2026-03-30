package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;

public class DomnAvaliacaoTipo extends AbstractDominioNumericoAbaco
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   public static final int NORMAL = 1;
   public static final int REAVALICAO = 2;

   public static final String DESCRICAO_NORMAL = "Normal";
   public static final String DESCRICAO_REAVALICAO = "Reavaliação";
   
   private static int domnIndice[] = { NORMAL, REAVALICAO };
   private static String domnDescricao[] = { DESCRICAO_NORMAL, DESCRICAO_REAVALICAO };

   /**
    * Instancia o domínio sem valor corrente
    */
   public DomnAvaliacaoTipo()
   {
      super(-1);
   }

   public DomnAvaliacaoTipo(int valor)
   {
      super(valor);
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
