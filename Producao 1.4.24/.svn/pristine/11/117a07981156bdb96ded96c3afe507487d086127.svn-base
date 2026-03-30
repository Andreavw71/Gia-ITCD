package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;

/**
 * 
 * Dominio que representa o ambiente em que aplicação esta
 * publicada
 * 
 * @author Dherkyan Ribeiro
 * @version $Revision: 1.1.1.1 $
 */
public class DomAmbienteAplicacao extends AbstractDominioNumericoAbaco
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   public static final int LOCAL = 1;
   public static final int DESENVOLVIMENTO = 2;
   public static final int HOMOLOGACAO = 3;
   public static final int PRODUCAO = 4;
   private static final String DESCRICAO_LOCAL= "LOCAL";
   private static final String DESCRICAO_DESENVOLVIMENTO = "DESENVOLVIMENTO";
   private static final String DESCRICAO_HOMOLOGACAO = "HOMOLOGACAO";
   private static final String DESCRICAO_PRODUCAO = "PRODUCAO";
   private static int domnIndice[] = { LOCAL, DESENVOLVIMENTO, HOMOLOGACAO,PRODUCAO};
   private static String domnDescricao[] = { DESCRICAO_LOCAL, DESCRICAO_DESENVOLVIMENTO , DESCRICAO_HOMOLOGACAO, DESCRICAO_PRODUCAO  };

   /**
    * Instancia o domínio sem valor corrente
    */
   public DomAmbienteAplicacao()
   {
      super(-1);
   }

   /**
    * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
    * @param  valorConstante O valor do domínio.
    */
   public DomAmbienteAplicacao(int valorConstante)
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