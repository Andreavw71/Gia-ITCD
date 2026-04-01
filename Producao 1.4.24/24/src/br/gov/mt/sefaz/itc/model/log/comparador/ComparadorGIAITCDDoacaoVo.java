package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDDoacao;


public class ComparadorGIAITCDDoacaoVo extends Comparador<GIAITCDDoacaoVo> implements CamposGIAITCDDoacao
{
   public ComparadorGIAITCDDoacaoVo()
   {
      super(TABELA_GIA_ITCD_DOACAO);
   }


   public HistoricoLogVo rotinaComparacao(GIAITCDDoacaoVo objetoModificada, GIAITCDDoacaoVo objetoOriginal)
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

   protected void comparar(GIAITCDDoacaoVo objetoModificado, GIAITCDDoacaoVo objetoOriginal)
   {
      // FRACAO IDEAL
      valorDiferente = objetoModificado.getFracaoIdeal() != objetoOriginal.getFracaoIdeal();
      registroLogVo(CAMPO_FRAC_IDEAL, objetoModificado.getFracaoIdealFormatada(), objetoOriginal.getFracaoIdealFormatada());


      // BASE CALCULO REDUZIDA
      valorDiferente = objetoModificado.getBaseCalculoReduzida() != objetoOriginal.getBaseCalculoReduzida();
      registroLogVo(CAMPO_BASE_CALCULO_REDUZIDA, objetoModificado.getBaseCalculoReduzidaFormatado(), objetoOriginal.getBaseCalculoReduzidaFormatado());


      // CAMPO_TIPO_DOACAO
      valorDiferente = !objetoModificado.getTipoDoacao().is(objetoOriginal.getTipoDoacao().getValorCorrente());
      registroLogVo(CAMPO_TIPO_DOACAO, objetoModificado.getTipoDoacao().getTextoCorrente(), objetoOriginal.getTipoDoacao().getTextoCorrente());

      if (dadoObrigatorio)
      {

      }

   }
}
