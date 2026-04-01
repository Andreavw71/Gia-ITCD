package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDSeparacaoDivorcio;

import sefaz.mt.util.SefazDataHora;


public class ComparadorGIAITCDSeparacaoDivorcioVo extends Comparador<GIAITCDSeparacaoDivorcioVo> implements CamposGIAITCDSeparacaoDivorcio
{
   public ComparadorGIAITCDSeparacaoDivorcioVo()
   {
      super(TABELA_GIA_ITCD_SEPARACAO_DIVORCIO);
   }

   public HistoricoLogVo rotinaComparacao(GIAITCDSeparacaoDivorcioVo objetoModificada, GIAITCDSeparacaoDivorcioVo objetoOriginal)
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

   protected void comparar(GIAITCDSeparacaoDivorcioVo objetoModificado, GIAITCDSeparacaoDivorcioVo objetoOriginal)
   {

      // REGIME CASAMENTO
      valorDiferente = !objetoModificado.getRegimeCasamento().is(objetoOriginal.getRegimeCasamento().getValorCorrente());
      registroLogVo(CAMPO_REGIME_CASAMENTO, objetoModificado.getRegimeCasamento().getTextoCorrente(), objetoOriginal.getRegimeCasamento().getTextoCorrente());


      // REGIME CASAMENTO
      valorDiferente = !objetoModificado.getDataSeparacao().equals(objetoOriginal.getDataSeparacao());
      registroLogVo(CAMPO_DATA_SEPARACAO, new SefazDataHora(objetoModificado.getDataSeparacao()).formata(MASCARA_DATA_TIMESTEMP), new SefazDataHora(objetoOriginal.getDataSeparacao()).formata(MASCARA_DATA_TIMESTEMP));


      // NUMERO PROCESSO
      valorDiferente = objetoModificado.getNumeroProcesso() != objetoOriginal.getNumeroProcesso();
      {
         registroLogVo(CAMPO_NUMERO_PROCESSO, objetoModificado.getNumeroProcessoFormatado(), objetoOriginal.getNumeroProcessoFormatado());
      }

      // PESSOA CONJUGE1
      valorDiferente = objetoModificado.getConjuge1().getCodigo() != objetoOriginal.getConjuge1().getCodigo();
      registroLogVo(CAMPO_PESSOA_CONJUGE1, "" + objetoModificado.getConjuge1().getCodigo(), "" + objetoOriginal.getConjuge1().getCodigo());


      // PESSOA CONJUGE2
      valorDiferente = objetoModificado.getConjuge2().getCodigo() != objetoOriginal.getConjuge2().getCodigo();
      registroLogVo(CAMPO_PESSOA_CONJUGE2, "" + objetoModificado.getConjuge2().getCodigo(), "" + objetoOriginal.getConjuge2().getCodigo());


      // VALOR TOTAL CONJUGE1
      valorDiferente = objetoModificado.getValorTotalConjuge1() != objetoOriginal.getValorTotalConjuge1();
      registroLogVo(CAMPO_VALOR_TOTAL_CONJUGE1, "" + objetoModificado.getValorTotalConjuge1(), "" + objetoOriginal.getValorTotalConjuge1());


      // VALOR TOTAL CONJUGE2
      valorDiferente = objetoModificado.getValorTotalConjuge2() != objetoOriginal.getValorTotalConjuge2();
      registroLogVo(CAMPO_VALOR_TOTAL_CONJUGE2, "" + objetoModificado.getValorTotalConjuge2(), "" + objetoOriginal.getValorTotalConjuge2());


      // VALOR ALIQUOTA
      valorDiferente = objetoModificado.getValorAliquota() != objetoOriginal.getValorAliquota();
      registroLogVo(CAMPO_VALOR_ALIQUOTA, objetoModificado.getValorAliquotaFormatado(), objetoOriginal.getValorAliquotaFormatado());


      // CAMPO VALOR INCIDENCIA
      valorDiferente = objetoModificado.getValorIncidencia() != objetoOriginal.getValorIncidencia();
      registroLogVo(CAMPO_VALOR_INCIDENCIA, objetoModificado.getValorIncidenciaFormatado(), objetoOriginal.getValorIncidenciaFormatado());


      // CAMPO DATA CASAMENTO
      valorDiferente = !objetoModificado.getDataCasamento().equals(objetoOriginal.getDataCasamento());
      registroLogVo(CAMPO_DATA_CASAMENTO, new SefazDataHora(objetoModificado.getDataCasamento()).formata(MASCARA_DATA_TIMESTEMP), new SefazDataHora(objetoOriginal.getDataCasamento()).formata(MASCARA_DATA_TIMESTEMP));


      if (dadoObrigatorio)
      {
      }

   }
}
