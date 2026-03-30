package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelUrbano;


public class ComparadorFichaImovelUrbanoVo extends Comparador<FichaImovelUrbanoVo> implements CamposFichaImovelUrbano
{
   public ComparadorFichaImovelUrbanoVo()
   {
      super(TABELA_IMOVEL_URBANO);
   }

   public ComparadorFichaImovelUrbanoVo(DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_IMOVEL_URBANO, tipoOperacao);
   }

   public HistoricoLogVo rotinaComparacao(FichaImovelUrbanoVo objetoModificada, FichaImovelUrbanoVo objetoOriginal)
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

   protected void comparar(FichaImovelUrbanoVo objetoModificado, FichaImovelUrbanoVo objetoOriginal)
   {
      // CAMPO_ITCTB13_CODIGO_CONSTRUCAO
      valorDiferente = objetoModificado.getConstrucaoVo().getCodigo() != objetoOriginal.getConstrucaoVo().getCodigo();
      registroLogVo(CAMPO_ITCTB13_CODIGO_CONSTRUCAO, "" + objetoModificado.getConstrucaoVo().getCodigo(), "" + objetoOriginal.getConstrucaoVo());


      // CAMPO_TIPO_CONSERVACAO
      valorDiferente = objetoModificado.getEstadoConservacao().getValorCorrente() != objetoOriginal.getEstadoConservacao().getValorCorrente();
      registroLogVo( CAMPO_TIPO_CONSERVACAO , objetoModificado.getEstadoConservacao().getTextoCorrente() , objetoOriginal.getEstadoConservacao().getTextoCorrente() );
      
      
      // CAMPO_TIPO_ACESSO
      valorDiferente = objetoModificado.getTipoAcesso().getValorCorrente() != objetoOriginal.getTipoAcesso().getValorCorrente();
      registroLogVo(CAMPO_TIPO_ACESSO , objetoModificado.getTipoAcesso().getTextoCorrente() , objetoOriginal.getTipoAcesso().getTextoCorrente()  );


      // CAMPO_QUANTIDADE_AREA_CONSTRUIDA
      valorDiferente = objetoModificado.getQuantidadeAreaConstruida() != objetoOriginal.getQuantidadeAreaConstruida();
      registroLogVo( CAMPO_QUANTIDADE_AREA_CONSTRUIDA , objetoModificado.getQuantidadeAreaConstruidaFormatado() , objetoOriginal.getQuantidadeAreaConstruidaFormatado()  );


      // CAMPO_QUANTIDADE_AREA_TOTAL
      valorDiferente = objetoModificado.getQuantidadeAreaTotal() != objetoOriginal.getQuantidadeAreaTotal();
      registroLogVo( CAMPO_QUANTIDADE_AREA_TOTAL , objetoModificado.getQuantidadeAreaTotalFormatado() , objetoOriginal.getQuantidadeAreaTotalFormatado() );


      // CAMPO_VALOR_MERCADO_TOTAL
      valorDiferente = objetoModificado.getValorMercadoTotal() != objetoOriginal.getValorMercadoTotal();
      registroLogVo( CAMPO_VALOR_MERCADO_TOTAL , objetoModificado.getValorMercadoTotalFormatado() , objetoOriginal.getValorMercadoTotalFormatado() );


      // CAMPO_VALOR_VENAL_IPTU
      valorDiferente = objetoModificado.getValorVenalIptu() !=  objetoOriginal.getValorVenalIptu();
      registroLogVo(  CAMPO_VALOR_VENAL_IPTU , objetoModificado.getValorVenalIptuFormatado() , objetoOriginal.getValorVenalIptuFormatado() );


      // CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRBT
      valorDiferente = objetoModificado.getBemTributavelVo().getCodigo() != objetoOriginal.getBemTributavelVo().getCodigo();
      registroLogVo( CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRBT , objetoModificado.getBemTributavelVo().getCodigoFormatado()  , objetoOriginal.getBemTributavelVo().getCodigoFormatado()  );
      
      
      // CAMPO_ACCTB06_CODIGO_ENDERECO
      valorDiferente = objetoModificado.getEnderecoIntegracaoVo().getCodgEndereco() != objetoOriginal.getEnderecoIntegracaoVo().getCodgEndereco();
      registroLogVo( CAMPO_ACCTB06_CODIGO_ENDERECO , ""+objetoModificado.getEnderecoIntegracaoVo().getCodgEndereco(), "" +objetoOriginal.getEnderecoIntegracaoVo().getCodgEndereco() );
      
      
      
      if (dadoObrigatorio)
      {
         historicoLogVo.setInfoChavePrimaria("" + objetoOriginal.getCodigo());
      }
   }


}
