package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnVersaoGIAITCD extends AbstractDominioNumericoAbaco
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   public static final int VERSAO_1 = 1;
   public static final int VERSAO_1_2 = 2;
   public static final int VERSAO_1_3 = 3;
   public static final int VERSAO_1_4 = 4;  
   private static final String DESCRICAO_VERSAO_1 = "GIA Versão 1.0";
   private static final String DESCRICAO_VERSAO_1_2 = "GIA Versão 1.2";
   private static final String DESCRICAO_VERSAO_1_3 = "GIA Versão 1.3";
   private static final String DESCRICAO_VERSAO_1_4 = "GIA Versão 1.4";
   private static int domnIndice[] = { VERSAO_1, VERSAO_1_2, VERSAO_1_3, VERSAO_1_4 };
   private static String domnDescricao[] = { DESCRICAO_VERSAO_1, DESCRICAO_VERSAO_1_2, DESCRICAO_VERSAO_1_3, DESCRICAO_VERSAO_1_4};
   
   /**
    * Instancia o domínio sem valor corrente
    */
   public DomnVersaoGIAITCD()
   {
      super(-1);
   }
   
   /**
    * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
    * @param  valorConstante O valor do domínio.
    */
   public DomnVersaoGIAITCD( int valorConstante)
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
