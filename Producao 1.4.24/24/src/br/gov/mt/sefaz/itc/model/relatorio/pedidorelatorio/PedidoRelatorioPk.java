package br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio;

/**
 * 
 * @author Dherkyan Ribeiro da Silva.
 */
public class PedidoRelatorioPk
{
   private long codigo;

   public PedidoRelatorioPk()
   {
   }

   public PedidoRelatorioPk(long codigo)
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

   public PedidoRelatorioPk getPk()
   {
      return new PedidoRelatorioPk(getCodigo());
   }
}
