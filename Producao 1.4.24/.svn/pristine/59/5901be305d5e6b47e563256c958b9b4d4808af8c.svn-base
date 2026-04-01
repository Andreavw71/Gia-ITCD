package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria.FichaImovelRuralBenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralBenfeitoria;


public class ComparadorFichaImovelRuralBenfeitoriaVo extends Comparador<FichaImovelRuralBenfeitoriaVo> implements CamposFichaImovelRuralBenfeitoria
{
   public ComparadorFichaImovelRuralBenfeitoriaVo()
   {
      super(TABELA_FICHA_IMOVEL_RURAL_BENFEITORIA);
   }

   public ComparadorFichaImovelRuralBenfeitoriaVo(DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_FICHA_IMOVEL_RURAL_BENFEITORIA, tipoOperacao);
   }

   public HistoricoLogVo rotinaComparacao(FichaImovelRuralBenfeitoriaVo objetoModificada, FichaImovelRuralBenfeitoriaVo objetoOriginal)
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

   protected void comparar(FichaImovelRuralBenfeitoriaVo objetoModificado, FichaImovelRuralBenfeitoriaVo objetoOriginal)
   {
      //CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA
      valorDiferente = !objetoModificado.getDescricaoComplementarBenfeitoria().equalsIgnoreCase(objetoOriginal.getDescricaoComplementarBenfeitoria());
      registroLogVo(CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA, objetoModificado.getDescricaoComplementarBenfeitoria(), objetoOriginal.getDescricaoComplementarBenfeitoria());


      // CAMPO_ITCTB07_CODIGO_BENFEITORIA
      valorDiferente = objetoModificado.getBenfeitoriaVo().getCodigo() != objetoOriginal.getBenfeitoriaVo().getCodigo();
      registroLogVo(CAMPO_ITCTB07_CODIGO_BENFEITORIA, "" + objetoModificado.getBenfeitoriaVo().getCodigo(), "" + objetoOriginal.getBenfeitoriaVo().getCodigo());

      if (dadoObrigatorio)
      {
         historicoLogVo.setInfoChavePrimaria("" + objetoOriginal.getCodigo());
      }
   }
}
