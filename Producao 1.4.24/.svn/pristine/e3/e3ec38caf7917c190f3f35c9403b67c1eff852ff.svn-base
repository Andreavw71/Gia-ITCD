package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota.GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota;


public class ComparadorGIAITCDInventarioArrolamentoBeneficiarioAliquotaVo extends Comparador<GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo> implements CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota
{
   public ComparadorGIAITCDInventarioArrolamentoBeneficiarioAliquotaVo()
   {
      super(TABELA_GIA_ITCD_IVENTARIO_BENEFICIARIO_ALIQUOTA);
   }

   public ComparadorGIAITCDInventarioArrolamentoBeneficiarioAliquotaVo(DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_GIA_ITCD_IVENTARIO_BENEFICIARIO_ALIQUOTA, tipoOperacao);
   }

   public HistoricoLogVo rotinaComparacao(GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo objetoModificada, GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo objetoOriginal)
   {
      comparar(objetoModificada, objetoOriginal);
      if (Validador.isCollectionValida(items.getCollVO()))
      {
         historicoLogVo.setItemLog(items);
         return historicoLogVo;
      } else
      {
         return null;
      }
   }

   protected void comparar(GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo objetoModificado, GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo objetoOriginal)
   {
      // CAMPO_PERCENTUAL_ALIQUOTA
      valorDiferente = objetoModificado.getPercentualAliquota() != objetoOriginal.getPercentualAliquota();
      registroLogVo(CAMPO_PERCENTUAL_ALIQUOTA, objetoModificado.getPercentualAliquotaFormatado(), objetoOriginal.getPercentualAliquotaFormatado());


      //CAMPO_VALOR_RECOLHER
      valorDiferente = objetoModificado.getValorRecolher() != objetoOriginal.getValorRecolher();
      registroLogVo(CAMPO_VALOR_RECOLHER, objetoModificado.getValorRecolherFormatado(), objetoOriginal.getValorRecolherFormatado());


      //CAMPO_BASE_CALCULO
      valorDiferente = objetoModificado.getValorBaseCalculo() != objetoOriginal.getValorBaseCalculo();
      registroLogVo(CAMPO_BASE_CALCULO, objetoModificado.getValorBaseCalculoFormatado(), objetoOriginal.getValorBaseCalculoFormatado());


      if (dadoObrigatorio)
      {
         historicoLogVo.setInfoChavePrimaria("" + objetoOriginal.getCodigo());
      }
   }
}
