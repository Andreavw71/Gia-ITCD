package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.benfeitoria.FichaImovelUrbanoBenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelUrbanoBenfeitoria;


public class ComparadorFichaImovelUrbanoBenfeitoriaVo extends Comparador<FichaImovelUrbanoBenfeitoriaVo> implements CamposFichaImovelUrbanoBenfeitoria
{
   public ComparadorFichaImovelUrbanoBenfeitoriaVo()
   {
      super(TABELA_IMOVEL_URBANO_BENFEITORIA);
   }

   public ComparadorFichaImovelUrbanoBenfeitoriaVo(DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_IMOVEL_URBANO_BENFEITORIA, tipoOperacao);
   }

   public HistoricoLogVo rotinaComparacao(FichaImovelUrbanoBenfeitoriaVo objetoModificada, FichaImovelUrbanoBenfeitoriaVo objetoOriginal)
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

   protected void comparar(FichaImovelUrbanoBenfeitoriaVo objetoModificado, FichaImovelUrbanoBenfeitoriaVo objetoOriginal)
   {  
      // CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA
      valorDiferente = !objetoModificado.getDescricaoComplementarBenfeitoria().equalsIgnoreCase(objetoOriginal.getDescricaoComplementarBenfeitoria());
      registroLogVo( CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA , objetoModificado.getDescricaoComplementarBenfeitoria() , objetoOriginal.getDescricaoComplementarBenfeitoria() );
      
      if (dadoObrigatorio)
      {
         historicoLogVo.setInfoChavePrimaria( ""+ objetoOriginal.getCodigo());
      }
   }
}
