package br.gov.mt.sefaz.itc.model.relatorio.parametrorelatorio;

/**
 * 
 * @author Dherkyan Ribeiro da Silva.
 */
public class ParametroRelatorioPk
{
   private long codigo;

   public ParametroRelatorioPk()
   {
   }

   public ParametroRelatorioPk(long codigo)
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

   public ParametroRelatorioPk getPk()
   {
      return new ParametroRelatorioPk(getCodigo());
   }
   }
