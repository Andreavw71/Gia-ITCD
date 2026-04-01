package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;

public class DomnSituacaoProcessamento extends AbstractDominioNumericoAbaco
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   public static final int NAO_PROCESSADO = 1;
   public static final int PROCESSADO = 2;
   public static final int PROCESSADO_COM_ERRRO = 3;
   private static final String DESCRICAO_NAO_PROCESSADO = "NÃO PROCESSADO";
   private static final String DESCRICAO_PROCESSADO = "PROCESSADO";
   private static final String DESCRICAO_PROCESSADO_COM_ERRRO = "PROCESSADO COM ERRO";
   private static int domnIndice[] =
   { NAO_PROCESSADO, PROCESSADO, PROCESSADO_COM_ERRRO };
   private static String domnDescricao[] =
   { DESCRICAO_NAO_PROCESSADO, DESCRICAO_PROCESSADO, DESCRICAO_PROCESSADO_COM_ERRRO };

   /**
    * Instancia o domínio sem valor corrente
    */
   public DomnSituacaoProcessamento()
   {
      super(-1);
   }

   /**
    * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
    * @param  valorConstante O valor do domínio.
    */
   public DomnSituacaoProcessamento(int valorConstante)
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
