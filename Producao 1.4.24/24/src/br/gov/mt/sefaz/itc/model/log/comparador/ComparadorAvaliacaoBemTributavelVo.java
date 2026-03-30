package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.avaliacao.AvaliacaoBemTributavelVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposAvaliacaoBemtributavel;


public class ComparadorAvaliacaoBemTributavelVo extends Comparador<AvaliacaoBemTributavelVo> implements CamposAvaliacaoBemtributavel
{
   public ComparadorAvaliacaoBemTributavelVo()
   {
      super(TABELA_AVALIACAO_BEMTRIBUTAVEL);
   }
   
   public ComparadorAvaliacaoBemTributavelVo(DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_AVALIACAO_BEMTRIBUTAVEL , tipoOperacao);
   }


   public HistoricoLogVo rotinaComparacao(AvaliacaoBemTributavelVo objetoModificada, AvaliacaoBemTributavelVo objetoOriginal)
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

   protected void comparar(AvaliacaoBemTributavelVo objetoModificado, AvaliacaoBemTributavelVo objetoOriginal)
   {
      // CAMPO_VALOR_AVALIACAO
      valorDiferente = objetoModificado.getValorAvaliacao() != objetoOriginal.getValorAvaliacao();
      registroLogVo( CAMPO_VALOR_AVALIACAO , objetoModificado.getValorAvaliacaoFormatado() , objetoOriginal.getValorAvaliacaoFormatado()  );
      
      // CAMPO_STATUS_AVALIACAO
      valorDiferente = objetoModificado.getStatusAvaliacao().getValorCorrente() != objetoOriginal.getStatusAvaliacao().getValorCorrente();
      registroLogVo( CAMPO_STATUS_AVALIACAO , objetoModificado.getStatusAvaliacao().getTextoCorrente() , objetoOriginal.getStatusAvaliacao().getTextoCorrente()  );
      
      
      // CAMPO_AVALIACAO_IMPRESSA
      valorDiferente = objetoModificado.getAvaliacaoImpressa().getValorCorrente() != objetoOriginal.getStatusAvaliacao().getValorCorrente();
      registroLogVo( CAMPO_AVALIACAO_IMPRESSA , objetoModificado.getStatusAvaliacao().getTextoCorrente() , objetoOriginal.getStatusAvaliacao().getTextoCorrente()  );
      
      
      // CAMPO_DATA_AVALIACAO
      if(Validador.isDataValida(objetoModificado.getDataAvaliacao()))
      {
         valorDiferente = objetoModificado.getDataAvaliacao().equals( objetoOriginal.getDataAvaliacao() );
         registroLogVo( CAMPO_DATA_AVALIACAO , objetoModificado.getDataAvaliacaoFormatado() , objetoOriginal.getDataAtualizacaoFormatada()  );
      }
      
      
      // CAMPO_OBSERVACAO
      valorDiferente = objetoModificado.getObservacao().equalsIgnoreCase( objetoOriginal.getObservacao() );
      registroLogVo( CAMPO_OBSERVACAO , objetoModificado.getObservacao() , objetoOriginal.getObservacao()  );
      
      
      if (dadoObrigatorio)
      {        
         if(historicoLogVo.getDomnTipoOperacao().is(DomnTipoOperacao.INCLUSAO))
         {
            historicoLogVo.setInfoChavePrimaria("" + objetoModificado.getCodigo());
         }else
         {
            historicoLogVo.setInfoChavePrimaria("" + objetoOriginal.getCodigo());
         }
      }
   }

}
