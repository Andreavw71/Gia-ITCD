package br.gov.mt.sefaz.itc.util.validador;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.itc.model.tabelabasica.distancia.DistanciaVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDistancia;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;


public class ValidadorDistancia extends ValidadorITCD
{
   public ValidadorDistancia()
   {
   }
   
   /**
    * 
    */
   public static void distanciaInicialPerimetroUrbanoMaiorQueZero(Integer distanciaInicialPerimetroUrbano , String MSGException) throws ParametroObrigatorioException
   {
      if(!numeroMaiorIgualZero(distanciaInicialPerimetroUrbano))
      {
         throw new ParametroObrigatorioException(MSGException);
      }
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
   
   public static void isdistanciaInicialMenorQueFinal( Integer distanciaInicial, Integer distanciaFinal, String MSGException) throws ParametroObrigatorioException
   {
      if(!isNumeroXMaiorQueY(distanciaFinal , distanciaInicial ))
      {
         throw new ParametroObrigatorioException(MSGException);
      }
   }
   
   public static void isTipoDistanciaValida(DomnTipoDistancia tipoDistancia) throws ParametroObrigatorioException
   {
      if(!Validador.isDominioNumericoValido(tipoDistancia))
      {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_SELECIONAR_TIPO_DISTANCIA);
      } 
   }
   
   /**
    * <b>Objetivo:</b> Este método tem por objetivo informar se o 
    * valor DistanciaFinalRodoviaPavimentada está em aberto, é considerado
    * em aberto caso o valor seje igual 0(zero) ou NULL (nulo).
    * 
    * @param distanciaVo
    * @return
    * @throws ParametroObrigatorioException
    */
   public static boolean isDistanciaEmAberto(int distanciaVo) throws ParametroObrigatorioException
   {
      return !Validador.isNumericoValido( distanciaVo);
   }
   
   public static void main(String[] args) throws ParametroObrigatorioException
   {
      
    System.out.println( ValidadorDistancia.isNumeroXMaiorQueY( 1 ,1));
   }
   
}
