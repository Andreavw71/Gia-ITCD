package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDInventarioArrolamento;

import sefaz.mt.util.SefazDataHora;


public class ComparadorGIAITCDInventarioArrolamentoVo extends Comparador<GIAITCDInventarioArrolamentoVo> implements CamposGIAITCDInventarioArrolamento
{
   public ComparadorGIAITCDInventarioArrolamentoVo()
   {
      super(TABELA_GIA_ITCD_INVENTARIO_ARROLAMENTO);
   }

   public HistoricoLogVo rotinaComparacao(GIAITCDInventarioArrolamentoVo objetoModificada, GIAITCDInventarioArrolamentoVo objetoOriginal)
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


   protected void comparar(GIAITCDInventarioArrolamentoVo giaModificada, GIAITCDInventarioArrolamentoVo giaOriginal)
   {
      // DATA INVENTARIO
      valorDiferente = !giaModificada.getDataInventario().equals(giaOriginal.getDataInventario());
      registroLogVo(CAMPO_DATA_INVENTARIO, new SefazDataHora(giaModificada.getDataInventario()).formata(MASCARA_DATA_TIMESTEMP), new SefazDataHora(giaOriginal.getDataInventario()).formata(MASCARA_DATA_TIMESTEMP));


      // DATA_FALECIMENTO
      valorDiferente = !giaModificada.getDataFalecimento().equals(giaOriginal.getDataFalecimento());
      registroLogVo(CAMPO_DATA_FALECIMENTO, new SefazDataHora(giaModificada.getDataFalecimento()).formata(MASCARA_DATA_TIMESTEMP), new SefazDataHora(giaOriginal.getDataFalecimento()).formata(MASCARA_DATA_TIMESTEMP));


      // DESCRICAO JUIZO COMARCA
      valorDiferente = !giaModificada.getDescricaoJuizoComarca().equalsIgnoreCase(giaOriginal.getDescricaoJuizoComarca());
      registroLogVo(CAMPO_DESCRICAO_JUIZO_COMARCA, giaModificada.getDescricaoJuizoComarca(), giaOriginal.getDescricaoJuizoComarca());


      // NUMERO HERDEIROS
      valorDiferente = giaModificada.getNumeroHerdeiros() != giaOriginal.getNumeroHerdeiros();
      registroLogVo(CAMPO_NUMERO_HERDEIROS, "" + giaModificada.getNumeroHerdeiros(), "" + giaOriginal.getNumeroHerdeiros());


      // NUMERO PROCESSO
      valorDiferente = giaModificada.getNumeroProcesso() != giaOriginal.getNumeroProcesso();
      registroLogVo(CAMPO_NUMERO_PROCESSO, giaModificada.getNumeroProcessoFormatado(), giaOriginal.getNumeroProcessoFormatado());


      // DATA PROCESSO
      valorDiferente = !giaModificada.getDataProcesso().equals(giaOriginal.getDataProcesso());
      registroLogVo(CAMPO_DATA_PROCESSO, new SefazDataHora(giaModificada.getDataProcesso()).formata(MASCARA_DATA_TIMESTEMP), new SefazDataHora(giaOriginal.getDataProcesso()).formata(MASCARA_DATA_TIMESTEMP));


      // VALOR MULTA
      valorDiferente = giaModificada.getValorMulta() != giaOriginal.getValorMulta();
      registroLogVo(CAMPO_VALR_MULTA, giaModificada.getValorMultaFormatada(), giaOriginal.getValorMultaFormatada());


      // REGIMENTO CASAMENTO
      valorDiferente = giaModificada.getRegimeCasamento().getDomnValr() != giaOriginal.getRegimeCasamento().getDomnValr();
      registroLogVo(CAMPO_REGIMENTO_CASAMENTO, giaModificada.getRegimeCasamento().getTextoCorrente(), giaOriginal.getRegimeCasamento().getTextoCorrente());


      // SITUACAO ESTADO CIVIL
      valorDiferente = giaModificada.getEstadoCivilDeCujus().getDomnValr() != giaOriginal.getEstadoCivilDeCujus().getDomnValr();
      registroLogVo(CAMPO_SITUACAO_ESTADO_CIVIL, giaModificada.getEstadoCivilDeCujus().getTextoCorrente(), giaOriginal.getEstadoCivilDeCujus().getTextoCorrente());


      // FLAG RENUNCIA
      valorDiferente = giaModificada.getRenuncia().getDomnValr() != giaOriginal.getRenuncia().getDomnValr();
      registroLogVo(CAMPO_FLAG_RENUNCIA, giaModificada.getRenuncia().getTextoCorrente(), giaOriginal.getRenuncia().getTextoCorrente());

   }


} // fim classe(ComparadorGIAITCDInventarioArrolamentoVo).
