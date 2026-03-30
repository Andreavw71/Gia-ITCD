package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnTipoVerificacao extends AbstractDominioNumericoAbaco
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   public static final int SEM_VERIFICACAO = 1;
   public static final int IMOVEL_RURAL = 2;
   public static final int IMOVEL_URBANO = 3;
   public static final int IPVA = 4;
   public static final int REBANHO = 5;
   public static final String DESCRICAO_SEM_VERIFICACAO = "Sem Verificação";
   public static final String DESCRICAO_IMOVEL_RURAL = "Imóvel Rural";
   public static final String DESCRICAO_IMOVEL_URBANO = "Imóvel Urbano";
   public static final String DESCRICAO_IPVA = "IPVA";
   public static final String DESCRICAO_REBANHO = "Rebanho";
   private static int domnIndice[] = { SEM_VERIFICACAO, IMOVEL_RURAL, IMOVEL_URBANO, IPVA, REBANHO };
   private static String domnDescricao[] = 
     { DESCRICAO_SEM_VERIFICACAO, DESCRICAO_IMOVEL_RURAL, DESCRICAO_IMOVEL_URBANO, DESCRICAO_IPVA, DESCRICAO_REBANHO };

   /**
    * Instancia o domínio sem valor corrente
    */
   public DomnTipoVerificacao()
   {
      super(-1);
   }

   /**
    * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
    * @param  valorConstante O valor do domínio.
    */
   public DomnTipoVerificacao(int valorConstante)
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
