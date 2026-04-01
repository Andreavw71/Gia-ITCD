package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposEndereco;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.EnderecoIntegracaoVo;


public class ComparadorEnderecoIntegracaoVo extends Comparador<EnderecoIntegracaoVo> implements CamposEndereco
{

   public ComparadorEnderecoIntegracaoVo()
   {
      super("ACCTB06_ENDERECO");
   }

   public ComparadorEnderecoIntegracaoVo(DomnTipoOperacao tipoOperacao)
   {
      super("ACCTB06_ENDERECO", tipoOperacao);
   }

   public HistoricoLogVo rotinaComparacao(EnderecoIntegracaoVo objetoModificada, EnderecoIntegracaoVo objetoOriginal)
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

   protected void comparar(EnderecoIntegracaoVo objetoModificado, EnderecoIntegracaoVo objetoOriginal)
   {
      
      // CAMPO_DESC_TIPO_LOGRADOURO
      valorDiferente = !objetoModificado.getTipoLogr().equalsIgnoreCase(objetoOriginal.getTipoLogr());
      registroLogVo(CAMPO_DESC_TIPO_LOGRADOURO, objetoModificado.getTipoLogr()  , objetoOriginal.getTipoLogr() );
      
      // CAMPO_NOME_LOGRADOURO
      valorDiferente = !objetoModificado.getLogradouro().equalsIgnoreCase(objetoOriginal.getLogradouro());
      registroLogVo(CAMPO_NOME_LOGRADOURO, objetoModificado.getLogradouro()  , objetoOriginal.getLogradouro());
      
      // CAMPO_NOME_LOGRADOURO
      valorDiferente = !objetoModificado.getBairro().equalsIgnoreCase(objetoOriginal.getBairro());
      registroLogVo(CAMPO_NOME_LOGRADOURO, objetoModificado.getBairro()  , objetoOriginal.getBairro());
      
      
      // CAMPO_NOME_LOCALIDADE
      valorDiferente = !objetoModificado.getNomeLocalidade().equalsIgnoreCase(objetoOriginal.getNomeLocalidade());
      registroLogVo(CAMPO_NOME_LOCALIDADE, objetoModificado.getNomeLocalidade()  , objetoOriginal.getNomeLocalidade());
      
      // CAMPO_NOME_LOCALIDADE
      valorDiferente = !objetoModificado.getPontoReferencia().equalsIgnoreCase(objetoOriginal.getPontoReferencia());
      registroLogVo(CAMPO_ENDR_PONTO_REFERENCIA, objetoModificado.getPontoReferencia()  , objetoOriginal.getPontoReferencia());
      
      // CAMPO_NUMR_LOGRADOURO
      valorDiferente = !objetoModificado.getNumrLogradouro().equalsIgnoreCase(objetoOriginal.getNumrLogradouro());
      registroLogVo(CAMPO_NUMR_LOGRADOURO, objetoModificado.getNumrLogradouro()  , objetoOriginal.getNumrLogradouro());
      
      // CAMPO_NUMR_LOGRADOURO
      valorDiferente = !objetoModificado.getCep().getCodgCep().equals(objetoOriginal.getCep().getCodgCep());
      registroLogVo(CAMPO_NUMR_CEP, objetoModificado.getCep().getCodgCep().toString()  , objetoOriginal.getCep().getCodgCep().toString());
      
      if(dadoObrigatorio)
      {
         valorDiferente = true;
         historicoLogVo.setInfoChavePrimaria(""+objetoOriginal.getCodgEndereco());
      }
      
   }
}
