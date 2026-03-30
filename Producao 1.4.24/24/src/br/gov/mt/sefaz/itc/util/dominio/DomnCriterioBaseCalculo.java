/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: DomnCriterioBaseCalculo.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data criação : 18/09/2015
 * Data ultima revisão : 18/09/2015
 */
package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnCriterioBaseCalculo extends AbstractDominioNumericoAbaco
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   public static final int MINIMO = 1;
   public static final int MEDIO = 2;
   public static final int MAXIMO = 3;
   private static final String DESCRICAO_MINIMO = "Mínimo";
   private static final String DESCRICAO_MEDIO = "Médio";
   private static final String DESCRICAO_MAXIMO = "Máximo";
   private static int domnIndice[] = { MINIMO, MEDIO, MAXIMO };
   private static String domnDescricao[] = { DESCRICAO_MINIMO, DESCRICAO_MEDIO ,DESCRICAO_MAXIMO};

   /**
    * Instancia o domínio sem valor corrente
    */
   public DomnCriterioBaseCalculo()
   {
      super(-1);
   }

   /**
    * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
    * @param  valorConstante O valor do domínio.
    */
   public DomnCriterioBaseCalculo(int valorConstante)
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

