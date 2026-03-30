package br.gov.mt.sefaz.itc.model.tabelabasica.distancia.util;

import br.gov.mt.sefaz.itc.model.tabelabasica.distancia.DistanciaVo;

import java.util.Comparator;


public class DistanciaComparator implements Comparator
{
   private int atributoDeOrdenacao;
   public DistanciaComparator()
   {
      
   }
   
   public DistanciaComparator(int atributoDeOrdenacao)
   {
      //atributoDeOrdenacao = DistanciaComparatorAtributo.
   }

   public int compare(Object distanciaVo1, Object distanciaVo2)
   {
      int retorno = 0;
      DistanciaVo d1 = (DistanciaVo) distanciaVo1;
      DistanciaVo d2 = (DistanciaVo) distanciaVo2;
      retorno = Long.valueOf(d1.getCodigo()).compareTo(d2.getCodigo());
      retorno = Integer.valueOf(d1.getDistanciaInicialPerimetroUrbano()).compareTo(d2.getDistanciaInicialPerimetroUrbano());
      if (retorno == 0)
      {
         retorno = Integer.valueOf(d1.getDistanciaFinalPerimetroUrbano()).compareTo(d2.getDistanciaFinalPerimetroUrbano());
      }
      if (retorno == 0)
      {
         retorno = Integer.valueOf(d1.getDistanciaInicialRodoviaPavimentada()).compareTo(d2.getDistanciaInicialRodoviaPavimentada());
      }
      if (retorno == 0)
      {
         retorno = Integer.valueOf(d1.getDistanciaFinalRodoviaPavimentada()).compareTo(d2.getDistanciaFinalRodoviaPavimentada());
      }
      return retorno;
   }
   
   class DistanciaComparatorAtributo
   {  
      /**
       * Utiliza somente o códido na comparacao
       */
      public static final int CODIGO = 1;
      /**
       * Utiliza todos os atributos com exceção do código.
       */
      public static final int ATRIBUTO = 2;
   }

}
