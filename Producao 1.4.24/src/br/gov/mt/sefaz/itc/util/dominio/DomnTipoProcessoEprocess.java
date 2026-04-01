package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnTipoProcessoEprocess extends AbstractDominioNumericoAbaco
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   public static final int PROCESSO_AUTOMATICO = 1;
   private static final String DESCRICAO_PROCESSO_AUTOMATICO = "PROCESSO AUTOMATICO";
   private static int domnIndice[] = { PROCESSO_AUTOMATICO };
   private static String domnDescricao[] = { DESCRICAO_PROCESSO_AUTOMATICO };

   public DomnTipoProcessoEprocess()
   {
      super(-1);
   }

   public DomnTipoProcessoEprocess(int valor) 
   {
      super(valor);
   }

   public int[] getDomnIndice()
   {
      return domnIndice;
   }

   public String[] getDomnDescricao()
   {
      return domnDescricao;
   }
   
   public int getKey()
   {
      return super.getValorCorrente();
   }  
}
