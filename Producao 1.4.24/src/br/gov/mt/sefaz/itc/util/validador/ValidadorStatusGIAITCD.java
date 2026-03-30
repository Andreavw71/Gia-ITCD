package br.gov.mt.sefaz.itc.util.validador;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;


public class ValidadorStatusGIAITCD
{
   public ValidadorStatusGIAITCD()
   {
   }


   public static void validar(GIAITCDVo vo) throws RegistroNaoPodeSerUtilizadoException, 
                                                   ObjetoObrigatorioException
   {
      ValidadorGIAITCD.validar(vo);

      if (!Validador.isObjetoValido(vo))
      {
         throw new RegistroNaoPodeSerUtilizadoException("Status da GIA com valor inconsistente. null");
      }
   }
   
   public static void validarFlagLocalAvaliacao(GIAITCDVo vo) throws RegistroNaoPodeSerUtilizadoException, 
                                                                     ObjetoObrigatorioException
   {
      validar(vo);
      if (!Validador.isDominioNumericoValido(vo.getStatusVo().getTipoAvaliacao()))
      {
         throw new RegistroNaoPodeSerUtilizadoException("Local da avalição com valor inconsistente. null");
      }
   }


}
