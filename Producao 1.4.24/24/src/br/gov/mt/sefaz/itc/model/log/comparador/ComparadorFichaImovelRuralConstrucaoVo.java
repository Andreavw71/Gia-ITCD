package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao.FichaImovelRuralConstrucaoVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralConstrucao;


public class ComparadorFichaImovelRuralConstrucaoVo extends Comparador<FichaImovelRuralConstrucaoVo> implements CamposFichaImovelRuralConstrucao
{
   public ComparadorFichaImovelRuralConstrucaoVo()
   {
      super(TABELA_FICHA_IMOVEL_RURAL_CONSTRUCAO);
   }

   public ComparadorFichaImovelRuralConstrucaoVo(DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_FICHA_IMOVEL_RURAL_CONSTRUCAO, tipoOperacao);
   }

   public HistoricoLogVo rotinaComparacao(FichaImovelRuralConstrucaoVo objetoModificada, FichaImovelRuralConstrucaoVo objetoOriginal)
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

   protected void comparar(FichaImovelRuralConstrucaoVo objetoModificado, FichaImovelRuralConstrucaoVo objetoOriginal)
   {
      // CAMPO_VALOR_MERCADO
      valorDiferente = objetoModificado.getValorMercado() != objetoOriginal.getValorMercado();
      registroLogVo(CAMPO_VALOR_MERCADO, objetoModificado.getValorMercadoFormatado(), objetoOriginal.getValorMercadoFormatado());


      // CAMPO_SITUACAO_ESTADO_CONSERVACAO
      valorDiferente = objetoModificado.getSituacaoEstadoConservacao().getValorCorrente() != objetoOriginal.getSituacaoEstadoConservacao().getValorCorrente();
      registroLogVo(CAMPO_SITUACAO_ESTADO_CONSERVACAO, objetoModificado.getSituacaoEstadoConservacao().getTextoCorrente(), objetoOriginal.getSituacaoEstadoConservacao().getTextoCorrente());


      // CAMPO_DESCRICAO_CONSTRUCAO
      valorDiferente = !objetoModificado.getDescricaoConstrucao().equalsIgnoreCase(objetoOriginal.getDescricaoConstrucao());
      registroLogVo(CAMPO_DESCRICAO_CONSTRUCAO, objetoModificado.getDescricaoConstrucao(), objetoOriginal.getDescricaoConstrucao());

      if (dadoObrigatorio)
      {
         historicoLogVo.setInfoChavePrimaria("" + objetoOriginal.getCodigo());
      }
   }
}
