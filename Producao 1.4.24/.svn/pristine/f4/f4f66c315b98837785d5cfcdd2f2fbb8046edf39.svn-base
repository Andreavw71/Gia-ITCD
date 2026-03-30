package br.gov.mt.sefaz.itc.util.validador;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;


public class ValidadorGIAITCD
{
   
   
   public ValidadorGIAITCD()
   {
   }
   
  public static void validar(GIAITCDVo vo) throws RegistroNaoPodeSerUtilizadoException, 
                                                   ObjetoObrigatorioException
   {
     if(!Validador.isObjetoValido(vo))
     {
        throw new RegistroNaoPodeSerUtilizadoException("GIA Inválida");
     }
  }
   
   
   
}
