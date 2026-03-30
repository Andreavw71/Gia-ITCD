package br.gov.mt.sefaz.itc.util.validador;

import br.com.abaco.util.exceptions.ParametroObrigatorioException;


public class ValidadorBaseCalculoImovelRural  extends ValidadorITCD
{
   public ValidadorBaseCalculoImovelRural()
   {
   }
   
   
   public static boolean isDistanciaInicialMenorQueFinal( Integer distanciaInicial, Integer distanciaFinal, String MSGException) throws ParametroObrigatorioException
   {
      if(!isNumeroXMaiorQueY(distanciaFinal , distanciaInicial ))
      {
         throw new ParametroObrigatorioException(MSGException);
      }  
      return true;
   }
   
   public static boolean isDistanciaInicialIgualFinal( Integer distanciaInicial, Integer distanciaFinal, String MSGException) throws ParametroObrigatorioException
   {
      if(isNumeroDiferente(distanciaInicial , distanciaFinal   ) )
      {
         throw new ParametroObrigatorioException(MSGException);
      }  
      return true;
   }
   
   public static boolean isDistanciaInicialMenosFinalIgualUm( Integer distanciaInicial, Integer distanciaFinal, String MSGException) throws ParametroObrigatorioException
   {
      if( (distanciaInicial - distanciaFinal) != 1  )
      {
         throw new ParametroObrigatorioException(MSGException);
      }  
      return true;
   }
   
}
