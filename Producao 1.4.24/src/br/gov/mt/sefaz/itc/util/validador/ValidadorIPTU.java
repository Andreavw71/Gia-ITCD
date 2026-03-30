package br.gov.mt.sefaz.itc.util.validador;

import br.com.abaco.util.exceptions.ParametroObrigatorioException;


public class ValidadorIPTU extends ValidadorITCD
{
   public ValidadorIPTU()
   {
   }
   
   
   /**
    * 
    */
   public static boolean isCampoPreenchido(Object obj , String MSGException) throws ParametroObrigatorioException
   {
      boolean preenchido = isCampoPreenchido(obj);
      if(!preenchido)
      {
         throw new ParametroObrigatorioException(MSGException);
      }
      return preenchido;
   }
   
   public static boolean isCampoPreenchido(Object obj)
   {
      return isValorPreenchido(obj);
   }
   
}
