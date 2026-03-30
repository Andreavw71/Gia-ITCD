package br.gov.mt.sefaz.itc.model.log.comparador;

import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposContribuinteIntegracaoVo;


public class ComparadorPessoaDeclarante extends ComparadorPessoa implements CamposContribuinteIntegracaoVo
{
   public ComparadorPessoaDeclarante()
   {
      super(TABELA_EXTERNA_CADS_PESSOA_DECLARANTE);
   }

   public ComparadorPessoaDeclarante(DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_EXTERNA_CADS_PESSOA_DECLARANTE, tipoOperacao);
   }

}

