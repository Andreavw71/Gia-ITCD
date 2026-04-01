/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AvaliacaoBemTributavelVo.java
 * Revisão: Dherkyan Ribeiro
 * Data implementação : 08/10/2015
 * Data revisão: 08/10/2015
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural.util;

import br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural.BaseCalculoImovelRuralVo;

import java.util.Comparator;


/**
 * Tem por objetivo comparar os valores
 * dos atributos da classe para determinar a ordem
 * A ordem de comparação é definada pela analista do projeto.
 * 
 * 
 */
public class BaseCalculoImovelRuralComparator implements Comparator
{
   public BaseCalculoImovelRuralComparator()
   {
   }

   public int compare(Object baseCalculoImovelRuralVo1, Object baseCalculoImovelRuralVo2)
   {
      int retorno = 0;
      BaseCalculoImovelRuralVo b1 = (BaseCalculoImovelRuralVo) baseCalculoImovelRuralVo1;
      BaseCalculoImovelRuralVo b2 = (BaseCalculoImovelRuralVo) baseCalculoImovelRuralVo2;
      retorno = Long.valueOf(b1.getCodigo()).compareTo(b2.getCodigo());

      retorno = Integer.valueOf(b1.getQuantidadeDistanciaInicial()).compareTo(b2.getQuantidadeDistanciaInicial());
      if (retorno == 0)
      {
         retorno = Integer.valueOf(b1.getQuantidadeDistanciaFinal()).compareTo(b2.getQuantidadeDistanciaFinal());
      }
      if (retorno == 0)
      {
         retorno = Integer.valueOf(b1.getPercentualAtividadeInicial()).compareTo(b2.getPercentualAtividadeInicial());
      }
      if (retorno == 0)
      {
         retorno = Integer.valueOf(b1.getPercentualAtividadeFinal()).compareTo(b2.getPercentualAtividadeFinal());
      }
      if (retorno == 0)
      {
         retorno = Integer.valueOf(b1.getPercentualAreaExploradaInical()).compareTo(b2.getPercentualAreaExploradaInical());
      }
      if (retorno == 0)
      {
         retorno = Integer.valueOf(b1.getPercentualAreaExploradaFinal()).compareTo(b2.getPercentualAreaExploradaFinal());
      }
      return retorno;
   }

}
