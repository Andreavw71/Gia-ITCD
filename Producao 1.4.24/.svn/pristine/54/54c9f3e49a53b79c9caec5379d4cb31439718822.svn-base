package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.conjuge.ConjugeBemTributavelVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposConjuge;


public class ComparadorConjugeBemTributavelVo extends Comparador<ConjugeBemTributavelVo> implements CamposConjuge
{
   public ComparadorConjugeBemTributavelVo()
   {
      super(TABELA_CONJUGE_BEM_TRIBUTAVEL);
   }

   public ComparadorConjugeBemTributavelVo(DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_CONJUGE_BEM_TRIBUTAVEL , tipoOperacao);
   }

   public HistoricoLogVo rotinaComparacao(ConjugeBemTributavelVo objetoModificada, ConjugeBemTributavelVo objetoOriginal)
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

   protected void comparar(ConjugeBemTributavelVo objetoModificado, ConjugeBemTributavelVo objetoOriginal)
   {
      // CAMPO_TIPO_CONJUGE
      valorDiferente = objetoModificado.getTipoConjuge().getValorCorrente() != objetoOriginal.getTipoConjuge().getValorCorrente();
      registroLogVo(CAMPO_TIPO_CONJUGE, objetoModificado.getTipoConjuge().getTextoCorrente(), objetoOriginal.getTipoConjuge().getTextoCorrente());


      // CAMPO_VALOR_CONJUGE
      valorDiferente = objetoModificado.getValorConjuge() != objetoOriginal.getValorConjuge();
      registroLogVo(CAMPO_VALOR_CONJUGE, objetoModificado.getValorConjugeFormatado(), objetoOriginal.getValorConjugeFormatado());


      if (dadoObrigatorio)
      {
         historicoLogVo.setInfoChavePrimaria(objetoOriginal.getCodigoFormatado());
      }
   }
}
