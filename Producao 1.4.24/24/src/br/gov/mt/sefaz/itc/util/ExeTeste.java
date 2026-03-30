package br.gov.mt.sefaz.itc.util;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;

import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;


public class ExeTeste
{
   public ExeTeste()
   {
   }

   public static void main(String[] args)
   {
      DomnTipoOperacao operacao = new DomnTipoOperacao(DomnTipoOperacao.EXCLUSAO);
      Object obj = operacao;
      
      
      DomnTipoOperacao op2 = new DomnTipoOperacao(DomnTipoOperacao.INCLUSAO);
      Object obj2 = op2;
      
      
      
      System.out.println("DOM : "+obj.toString());
      
      AbstractDominioNumericoAbaco ab = (AbstractDominioNumericoAbaco) obj;
      AbstractDominioNumericoAbaco ab2 = (AbstractDominioNumericoAbaco) obj2;
      
      if(ab.getTextoCorrente().equalsIgnoreCase(ab2.getTextoCorrente()))
      {
         System.out.println("SIM");
      }else
      {
         System.out.println("NÃO");
      }
      
   }


}
