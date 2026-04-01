package br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso;

public class TipoProcessoPk
{
   private long codigo;

   public TipoProcessoPk()
   {
   }

   public TipoProcessoPk(long codigo)
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

   public TipoProcessoPk getPk()
   {
      return new TipoProcessoPk(getCodigo());
   }
}
