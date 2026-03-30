/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: DomnTipoAtividade.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data criação : 18/09/2015
 * Data ultima revisão : 18/09/2015
 */
package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnTipoAtividade extends AbstractDominioNumericoAbaco
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   public static final int AGRICULTURA = 1;
   public static final int PECUARIA = 2;
   private static final String DESCRICAO_AGRICULTURA = "AGRICULTURA";
   private static final String DESCRICAO_PECUARIA = "PECUARIA";
   private static int domnIndice[] = { AGRICULTURA, PECUARIA };
   private static String domnDescricao[] = { DESCRICAO_AGRICULTURA, DESCRICAO_PECUARIA };

   /**
    * Instancia o domínio sem valor corrente
    */
   public DomnTipoAtividade()
   {
      super(-1);
   }

   /**
    * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
    * @param  valorConstante O valor do domínio.
    */
   public DomnTipoAtividade(int valorConstante)
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
