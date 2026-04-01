package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;

/**
 * 
 * 
 * 
 * @author Dherkyan Ribeiro da Silva
 * @version $Revision: 1.2
 */
public class DomnTipoProcessoInventario extends AbstractDominioNumericoAbaco
{

   private static final long serialVersionUID = Long.MAX_VALUE;
   public static final int PROCESO_ADMINISTRATIVO = 1;
   public static final int PROCESO_JUDICIAL = 2;
   private static final String DESCRICAO_PROCESO_ADMINISTRATIVO = "Inventário/Arrolamento Administrativo";
   private static final String DESCRICAO_PROCESO_JUDICIAL = "Inventário/Arrolamento Judicial";
   private static int domnIndice[] = { PROCESO_ADMINISTRATIVO, PROCESO_JUDICIAL };
   private static String domnDescricao[] = { DESCRICAO_PROCESO_ADMINISTRATIVO, DESCRICAO_PROCESO_JUDICIAL };

   /**
    * Instancia o domínio sem valor corrente
    */
   public DomnTipoProcessoInventario()
   {
      super(-1);
   }

   /**
    * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
    * @param  valorConstante O valor do domínio.
    */
   public DomnTipoProcessoInventario(int valorConstante)
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
