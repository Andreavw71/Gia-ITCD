package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposContribuinteIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;


public class ComparadorPessoa extends Comparador<ContribuinteIntegracaoVo> implements CamposContribuinteIntegracaoVo
{
   public ComparadorPessoa(String nomeTabela)
   {
      super(nomeTabela);
   }
   
   public ComparadorPessoa(String nomeTabela  , DomnTipoOperacao tipoOperacao)
   {
      super(nomeTabela, tipoOperacao);
   }

   public HistoricoLogVo rotinaComparacao(ContribuinteIntegracaoVo objetoModificada, ContribuinteIntegracaoVo objetoOriginal)
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

   protected void comparar(ContribuinteIntegracaoVo objetoModificado, ContribuinteIntegracaoVo objetoOriginal)
   {
      if (historicoLogVo.getDomnTipoOperacao().is(DomnTipoOperacao.EXCLUSAO) & objetoModificado.getNumrContribuinte().equals(0))
      {

      } else if (historicoLogVo.getDomnTipoOperacao().is(DomnTipoOperacao.INCLUSAO) & objetoOriginal.getNumrContribuinte().equals(0))
      {

      } else
      {
         // CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA
         valorDiferente = !objetoModificado.getNumrContribuinte().equals(objetoOriginal.getNumrContribuinte());
         registroLogVo(CAMPO_NUMR_PESSOA, "" + objetoModificado.getNumrContribuinte(), "" + objetoOriginal.getNumrContribuinte());


         // CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA
         valorDiferente = !objetoModificado.getNomeContribuinte().equalsIgnoreCase(objetoOriginal.getNomeContribuinte());
         registroLogVo(CAMPO_NOME_PESSOA, "" + objetoModificado.getNomeContribuinte(), "" + objetoOriginal.getNomeContribuinte());

         if (dadoObrigatorio)
         {
         }
      }

   }
}
