package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura.FichaImovelRuralCulturaVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralCultura;


public class ComparadorFichaImovelRuralCulturaVo extends Comparador<FichaImovelRuralCulturaVo> implements CamposFichaImovelRuralCultura
{
   public ComparadorFichaImovelRuralCulturaVo()
   {
      super(TABELA_FICHA_IMOVEL_RURAL_CULTURA);
   }

   public ComparadorFichaImovelRuralCulturaVo(DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_FICHA_IMOVEL_RURAL_CULTURA, tipoOperacao);
   }

   public HistoricoLogVo rotinaComparacao(FichaImovelRuralCulturaVo objetoModificada, FichaImovelRuralCulturaVo objetoOriginal)
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

   protected void comparar(FichaImovelRuralCulturaVo objetoModificado, FichaImovelRuralCulturaVo objetoOriginal)
   {
      // CAMPO_VALOR_MERCADO
      valorDiferente = objetoModificado.getValorMercado() != objetoOriginal.getValorMercado();
      registroLogVo( CAMPO_VALOR_MERCADO , objetoModificado.getValorMercadoFormatado() ,  objetoOriginal.getValorMercadoFormatado() );
      
      
      // CAMPO_AREA_CULTIVADA
      valorDiferente = objetoModificado.getAreaCultivada() != objetoOriginal.getAreaCultivada();
      registroLogVo( CAMPO_AREA_CULTIVADA , objetoModificado.getAreaCultivadaFormatado() , objetoOriginal.getAreaCultivadaFormatado() );
      
      
      // CAMPO_DESCRICAO_COMPLEMENTAR_CULTURA
      valorDiferente = !objetoModificado.getDescricaoComplementarCultura().equalsIgnoreCase(objetoOriginal.getDescricaoComplementarCultura());
      registroLogVo( CAMPO_DESCRICAO_COMPLEMENTAR_CULTURA, objetoModificado.getDescricaoComplementarCultura() , objetoOriginal.getDescricaoComplementarCultura());
      
      
      // CAMPO_VALOR_HECTARE
      valorDiferente = objetoModificado.getValorHectare() != objetoOriginal.getValorHectare();
      registroLogVo(  CAMPO_VALOR_HECTARE  , objetoModificado.getValorHectareFormatado(),  objetoOriginal.getValorHectareFormatado() );
      
      if (dadoObrigatorio)
      {
         historicoLogVo.setInfoChavePrimaria("" + objetoModificado.getCodigo());
      }
   }
}
