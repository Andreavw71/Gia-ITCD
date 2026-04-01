package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBeneficiario;


public class ComparadorBeneficiarioVo extends Comparador<BeneficiarioVo> implements CamposBeneficiario
{
   public ComparadorBeneficiarioVo()
   {
      super(TABELA_BENEFICIARIO);
   }

   public ComparadorBeneficiarioVo(DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_BENEFICIARIO, tipoOperacao);
   }

   public HistoricoLogVo rotinaComparacao(BeneficiarioVo objetoModificada, BeneficiarioVo objetoOriginal)
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

   protected void comparar(BeneficiarioVo objetoModificado, BeneficiarioVo objetoOriginal)
   {
      // CAMPO_VALOR_RECEBIDO
      valorDiferente = objetoModificado.getValorRecebido() != objetoOriginal.getValorRecebido();
      registroLogVo(CAMPO_VALOR_RECEBIDO, objetoModificado.getValorRecebidoFormatado(), objetoOriginal.getValorRecebidoFormatado());


      // CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA
      valorDiferente = !objetoModificado.getPessoaBeneficiaria().getNumrContribuinte().equals(objetoOriginal.getPessoaBeneficiaria().getNumrContribuinte());
      registroLogVo(CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA, "" + objetoModificado.getPessoaBeneficiaria().getNumrContribuinte(), "" + objetoOriginal.getPessoaBeneficiaria().getNumrContribuinte());


      // CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA
      valorDiferente = !objetoModificado.getPessoaBeneficiaria().getNomeContribuinte().equalsIgnoreCase(objetoOriginal.getPessoaBeneficiaria().getNomeContribuinte());
      registroLogVo(CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA, "" + objetoModificado.getPessoaBeneficiaria().getNomeContribuinte(), "" + objetoOriginal.getPessoaBeneficiaria().getNomeContribuinte());


      if (dadoObrigatorio)
      {
         valorDiferente = true;

         if (Validador.isObjetoValido(historicoLogVo.getDomnTipoOperacao()) && historicoLogVo.getDomnTipoOperacao().is(DomnTipoOperacao.EXCLUSAO))
         {
            historicoLogVo.setInfoChavePrimaria("" + objetoModificado.getCodigo());
         } else
         {
            historicoLogVo.setInfoChavePrimaria("" + objetoOriginal.getCodigo());
         }
      }
   }
}
