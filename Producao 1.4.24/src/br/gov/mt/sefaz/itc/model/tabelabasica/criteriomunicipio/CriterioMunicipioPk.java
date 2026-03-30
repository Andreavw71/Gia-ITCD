/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CriterioMunicipioPk.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data criação : 18/09/2015
 * Data ultima revisão : 18/09/2015
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio;

public class CriterioMunicipioPk
{
   private long codigo;

   public CriterioMunicipioPk()
   {
   }

   public CriterioMunicipioPk(long codigo)
   {
      super();
      setCodigo(codigo);
   }

   public void setCodigo(long codigo)
   {
      this.codigo = codigo;
   }

   public long getCodigo()
   {
      return codigo;
   }

   public CriterioMunicipioPk getPk()
   {
      return new CriterioMunicipioPk(getCodigo());
   }
}
