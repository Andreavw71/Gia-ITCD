package br.gov.mt.sefaz.itc.util.validador;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;

import java.util.Iterator;


public class ValidadorAvaliacao extends ValidadorGIAITCD
{
   public ValidadorAvaliacao()
   {
   }

   /**
    * 
    * 
    * 
    * @param giaITCDVo
    * @return
    */
   public static boolean isPossivelExcluirOuReabrirAvaliacaoDaGia(final GIAITCDVo giaITCDVo)
   {
      boolean isPossivelExcluirOuReabrirAvaliacaoDaGia = false;

      for (Iterator iteAvaliacao = giaITCDVo.getBemTributavelVo().getCollVO().iterator(); iteAvaliacao.hasNext(); )
      {
         BemTributavelVo bemVo = (BemTributavelVo) iteAvaliacao.next();
         if (isPossivelExcluirOuReabrirAvaliacaoDoBem(giaITCDVo, bemVo))
         {
            isPossivelExcluirOuReabrirAvaliacaoDaGia = true;
         }
      }

      return isPossivelExcluirOuReabrirAvaliacaoDaGia;
   }

   /**
    * 
    * @param giaITCDVo
    * @param bemTributavelVo
    * @return
    */
   public static boolean isPossivelExcluirOuReabrirAvaliacaoDoBem(final GIAITCDVo giaITCDVo, final BemTributavelVo bemTributavelVo)
   {
      boolean isPossivelExcluirOuReabrirAvaliacaoDoBem = true;
      if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
      {

      }
      else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
      {
         if (bemTributavelVo.getBemParticular().is(DomnSimNao.SIM))
         {
            if (((GIAITCDSeparacaoDivorcioVo) giaITCDVo).getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS))
            {
               isPossivelExcluirOuReabrirAvaliacaoDoBem = false;
            }
         }
      }
      else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.DOACAO))
      {

      }
      return isPossivelExcluirOuReabrirAvaliacaoDoBem;
   }

}
