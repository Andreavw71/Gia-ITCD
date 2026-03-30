package br.gov.mt.sefaz.itc.model.tabelabasica.iptu;

public class IPTUPk
{
   private long codigo;

   public IPTUPk()
   {
   }

   public IPTUPk(long codigo)
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

   public IPTUPk getPk()
   {
      return new IPTUPk(getCodigo());
   }
}
