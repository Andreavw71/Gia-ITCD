package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho.FichaImovelRuralRebanhoVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralRebanho;


public class ComparadorFichaImovelRuralRebanhoVo extends Comparador<FichaImovelRuralRebanhoVo> implements CamposFichaImovelRuralRebanho
{
   public ComparadorFichaImovelRuralRebanhoVo()
   {
      super(TABELA_FICHA_IMOVEL_RURAL_REBANHO);
   }

   public ComparadorFichaImovelRuralRebanhoVo(DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_FICHA_IMOVEL_RURAL_REBANHO, tipoOperacao);
   }

   public HistoricoLogVo rotinaComparacao(FichaImovelRuralRebanhoVo objetoModificada, FichaImovelRuralRebanhoVo objetoOriginal)
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

   protected void comparar(FichaImovelRuralRebanhoVo objetoModificado, FichaImovelRuralRebanhoVo objetoOriginal)
   {
      // CAMPO_VALOR_MERCADO
      valorDiferente = objetoModificado.getValorMercado() != objetoOriginal.getValorMercado();
      registroLogVo(CAMPO_VALOR_MERCADO, objetoModificado.getValorMercadoFormatado(), objetoOriginal.getValorMercadoFormatado());


      // CAMPO_QUANTIDADE_REBANHO
      valorDiferente = objetoModificado.getQuantidadeRebanho() != objetoOriginal.getQuantidadeRebanho();
      registroLogVo(CAMPO_QUANTIDADE_REBANHO, objetoModificado.getQuantidadeRebanhoFormatado(), objetoOriginal.getQuantidadeRebanhoFormatado());


      // CAMPO_DESCRICAO_REBANHO
      valorDiferente = !objetoModificado.getDescricaoRebanho().equalsIgnoreCase(objetoOriginal.getDescricaoRebanho());
      registroLogVo(CAMPO_DESCRICAO_REBANHO, objetoModificado.getDescricaoRebanho(), objetoOriginal.getDescricaoRebanho());

      if (dadoObrigatorio)
      {
         historicoLogVo.setInfoChavePrimaria("" + objetoModificado.getCodigo());
      }
   }
}
