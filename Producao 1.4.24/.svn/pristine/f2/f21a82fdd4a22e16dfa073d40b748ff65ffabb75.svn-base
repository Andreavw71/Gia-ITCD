package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.GIAITCDInventarioArrolamentoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDInventarioArrolamentoBeneficiario;


public class ComparadorGIAITCDInventarioArrolamentoBeneficiarioVo extends Comparador<GIAITCDInventarioArrolamentoBeneficiarioVo> implements CamposGIAITCDInventarioArrolamentoBeneficiario
{
   public ComparadorGIAITCDInventarioArrolamentoBeneficiarioVo()
   {
      super(TABELA_GIA_ITCD_INVENTARIO_BENEFICIARIO);
   }

   public ComparadorGIAITCDInventarioArrolamentoBeneficiarioVo(DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_GIA_ITCD_INVENTARIO_BENEFICIARIO, tipoOperacao);
   }

   public HistoricoLogVo rotinaComparacao(GIAITCDInventarioArrolamentoBeneficiarioVo objetoModificada, GIAITCDInventarioArrolamentoBeneficiarioVo objetoOriginal)
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

   protected void comparar(GIAITCDInventarioArrolamentoBeneficiarioVo objetoModificado, GIAITCDInventarioArrolamentoBeneficiarioVo objetoOriginal)
   {
      // CAMPO_INFO_QTD_UPF
      valorDiferente = objetoModificado.getInfoQuantidadeUpf() != objetoOriginal.getInfoQuantidadeUpf();
      registroLogVo(CAMPO_INFO_QTD_UPF, "" + objetoModificado.getInfoQuantidadeUpf(), "" + objetoOriginal.getInfoQuantidadeUpf());
      
      
      // CAMPO_VALR_RECOLHER
      valorDiferente = objetoModificado.getValorRecolher() != objetoOriginal.getValorRecolher();
      registroLogVo( CAMPO_VALR_RECOLHER , objetoModificado.getValorRecolherFormatado() , objetoOriginal.getValorRecolherFormatado()  );


      // CAMPO_VALOR_MULTA_RECOLHER
      valorDiferente = objetoModificado.getValorMultaRecolher() != objetoOriginal.getValorMultaRecolher();
      registroLogVo( CAMPO_VALOR_MULTA_RECOLHER , objetoModificado.getValorMultaRecolherFormatado() , objetoOriginal.getValorMultaRecolherFormatado() );
  
   
      // CAMPO_VALOR_ITCD_BENEFICIARIO
      valorDiferente = objetoModificado.getValorITCDBeneficiario() != objetoOriginal.getValorITCDBeneficiario();
      registroLogVo( CAMPO_VALOR_ITCD_BENEFICIARIO , objetoModificado.getValorITCDBeneficiarioFormatado() , objetoOriginal.getValorITCDBeneficiarioFormatado() );
  
  
      // CAMPO_FLAG_BENEF_MEEIRO
      valorDiferente = objetoModificado.getFlagBeneficiarioMeeiro().getValorCorrente() != objetoOriginal.getFlagBeneficiarioMeeiro().getValorCorrente();
      registroLogVo(  CAMPO_FLAG_BENEF_MEEIRO , objetoModificado.getFlagBeneficiarioMeeiro().getTextoCorrente() , objetoOriginal.getFlagBeneficiarioMeeiro().getTextoCorrente() );
      
      if(dadoObrigatorio)
      {
         valorDiferente = true;
         // CAMPO_ITCTB05_CODIGO_BENEFICIARIO
         historicoLogVo.setInfoChavePrimaria( ""+objetoOriginal.getCodigo());
      }
      
   }
}
