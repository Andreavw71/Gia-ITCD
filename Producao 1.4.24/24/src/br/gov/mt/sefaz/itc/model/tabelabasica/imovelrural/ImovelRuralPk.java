package br.gov.mt.sefaz.itc.model.tabelabasica.imovelrural;

public class ImovelRuralPk
{
   private long codigo;

   public ImovelRuralPk() 
   {
      
   }

   public ImovelRuralPk(long codigo) 
   {
      this.codigo = codigo;
   }

   public long getCodigo()
   {
      return codigo;
   }

   public void setCodigo(long codigo)
   {
      this.codigo = codigo;
   }
   
   public ImovelRuralPk getPk() 
   {
      return new ImovelRuralPk(getCodigo());
   }
}
