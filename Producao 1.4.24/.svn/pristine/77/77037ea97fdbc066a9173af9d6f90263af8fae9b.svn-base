package br.gov.mt.sefaz.itc.model.log.itemhistorico.util;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.log.itemhistorico.ItemHistoricoVo;

import java.util.Comparator;


public class ItemHistoricoVoComparador implements Comparator<ItemHistoricoVo>
{
   public ItemHistoricoVoComparador()
   {
   }

   public int compare(ItemHistoricoVo vo1, ItemHistoricoVo vo2)
   {
      return comparacaoPorCodigo(vo1 , vo2);
   }
   
   /**
    * Realiza a comparacao de um <b>ItemHistoricoVo</b> atraves
    * do codigo de cada objeto. Caso algum ou ambos os objetos sejam
    * nulos a comparacao não sera realizada e será retornado 0 indicando
    * que os objetos são iguais.
    * 
    * 
    * @param vo1
    * @param vo2
    * @return
    */
   private int comparacaoPorCodigo(ItemHistoricoVo vo1, ItemHistoricoVo vo2)
   {
      if (Validador.isObjetoValido(vo1) & Validador.isObjetoValido(vo2))
      {
         if (vo1.getCodigo() != vo2.getCodigo())
         {
            Long codigoVo1 = vo1.getCodigo();
            return codigoVo1.compareTo(vo2.getCodigo());
         }
      }
      return 0;
   }
}
