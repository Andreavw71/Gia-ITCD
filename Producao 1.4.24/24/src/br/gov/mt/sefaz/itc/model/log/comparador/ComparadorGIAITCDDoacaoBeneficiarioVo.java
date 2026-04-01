package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDDoacaoBeneficiario;


public class ComparadorGIAITCDDoacaoBeneficiarioVo extends Comparador<GIAITCDDoacaoBeneficiarioVo> implements CamposGIAITCDDoacaoBeneficiario
{
   public ComparadorGIAITCDDoacaoBeneficiarioVo()
   {
      super(TABELA_GIA_ITCD_DOACAO_BENEFICIARIO);
   }
   
   public ComparadorGIAITCDDoacaoBeneficiarioVo( DomnTipoOperacao tipoOperacao)
   {
   super( TABELA_GIA_ITCD_DOACAO_BENEFICIARIO , tipoOperacao);
   }
   

   public HistoricoLogVo rotinaComparacao(GIAITCDDoacaoBeneficiarioVo objetoModificada, GIAITCDDoacaoBeneficiarioVo objetoOriginal)
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

   protected void comparar(GIAITCDDoacaoBeneficiarioVo objetoModificado, GIAITCDDoacaoBeneficiarioVo objetoOriginal)
   {
      // CAMPO_PERCENTUAL_ALIQUOTA
      valorDiferente = objetoModificado.getPercentualAliquota() != objetoOriginal.getPercentualAliquota();
      registroLogVo(CAMPO_PERCENTUAL_ALIQUOTA, objetoModificado.getPercentualAliquotaFormatado(), objetoOriginal.getPercentualAliquotaFormatado());


      // CAMPO_PERCENTUAL_BEM_RECEBIDO
      valorDiferente = objetoModificado.getPercentualBemRecebido() != objetoOriginal.getPercentualBemRecebido();
      registroLogVo(CAMPO_PERCENTUAL_BEM_RECEBIDO, objetoModificado.getPercentualBemRecebidoFormatado(), objetoOriginal.getPercentualBemRecebidoFormatado());
      
      
      // CAMPO_VALOR_RECOLHER
      valorDiferente = objetoModificado.getValorRecolher() != objetoOriginal.getValorRecolher();
      registroLogVo( CAMPO_VALOR_RECOLHER ,objetoModificado.getValorRecolherFormatado() , objetoOriginal.getValorRecolherFormatado());
      
      
      if(dadoObrigatorio)
      {
         historicoLogVo.setInfoChavePrimaria( ""+objetoOriginal.getCodigo());
      }
   }
}
