package br.gov.mt.sefaz.itc.util.validador;

import br.com.abaco.util.Validador;


public class ValidadorITCD
{
   public ValidadorITCD()
   {
   }
   
   static boolean numeroMaiorIgualZero(Number numero)
   {
      if(Validador.isObjetoValido(numero))
      {
         return new Double(numero.toString()) >= 0;
      }
      return false;
   }
   
   static boolean isValorPreenchido(Object obj)
   {
      if(Validador.isObjetoValido(obj))
      {
         return obj.toString().trim().length() > 0;
      }
      return false;
   }
   
   static boolean isNumeroXMaiorQueY(Number x , Number y)
   {
      if(Validador.isObjetoValido(x) & Validador.isObjetoValido(y))
      {
         return ( new Double(x.toString()) > new Double(y.toString()) );
      }
      return false;
   }
   
   static boolean isNumeroIgual(Number x , Number y)
   {
      if(Validador.isObjetoValido(x) & Validador.isObjetoValido(y))
      {
         return ( new Double(x.toString()).equals( new Double(y.toString())));
      }
      return false;
   }
   
   static boolean isNumeroDiferente(Number x , Number y)
   {
      if(Validador.isObjetoValido(x) & Validador.isObjetoValido(y))
      {
         return !( new Double(x.toString()).equals( new Double(y.toString())));
      }
      return false;
   }
   
   
}
