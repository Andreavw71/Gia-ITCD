/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CulturaVo.java
 * Revisão: Dherkyan Ribeiro da Silva.
 * Data implementação: 13/10/2015
 * Data revisão: 13/10/2015
 */
package br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.util;

import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;

import java.util.Comparator;


public class StatusComparator implements Comparator
{
   private StatusGIAITCDVo s1;
   private StatusGIAITCDVo s2;

   public StatusComparator()
   {
   }

   public int compare(Object statusGIAITCDVo1, Object statusGIAITCDVo2)
   {
      s1 = (StatusGIAITCDVo) statusGIAITCDVo1;
      s2 = (StatusGIAITCDVo) statusGIAITCDVo2;
      return Long.valueOf(s1.getCodigo()).compareTo(s2.getCodigo());
   }
}
