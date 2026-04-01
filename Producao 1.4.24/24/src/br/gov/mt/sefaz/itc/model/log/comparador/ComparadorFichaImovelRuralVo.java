package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRural;


public class ComparadorFichaImovelRuralVo extends Comparador<FichaImovelRuralVo> implements CamposFichaImovelRural
{
   public ComparadorFichaImovelRuralVo()
   {
      super(TABELA_FICHA_IMOVEL_RURAL);
   }

   public ComparadorFichaImovelRuralVo(DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_FICHA_IMOVEL_RURAL, tipoOperacao);
   }

   public HistoricoLogVo rotinaComparacao(FichaImovelRuralVo objetoModificada, FichaImovelRuralVo objetoOriginal)
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

   protected void comparar(FichaImovelRuralVo objetoModificado, FichaImovelRuralVo objetoOriginal)
   {
      // CAMPO_VALOR_ACESSAO_NATURAL
      valorDiferente = objetoModificado.getValorAcessaoNatural() != objetoOriginal.getValorAcessaoNatural();
      registroLogVo(CAMPO_VALOR_ACESSAO_NATURAL, objetoModificado.getValorAcessaoNaturalFormatado(), objetoOriginal.getValorAcessaoNaturalFormatado());


      // CAMPO_VALOR_ITR
      valorDiferente = objetoModificado.getValorITR() != objetoOriginal.getValorITR();
      registroLogVo(CAMPO_VALOR_ITR, objetoModificado.getValorITRFormatado(), objetoOriginal.getValorITRFormatado());

      // CAMPO_VALOR_MAQUINA_EQUIPAMENTO
      valorDiferente = objetoModificado.getValorMaquinaEquipamento() != objetoOriginal.getValorMaquinaEquipamento();
      registroLogVo(CAMPO_VALOR_MAQUINA_EQUIPAMENTO, objetoModificado.getValorMaquinaEquipamentoFormatado(), objetoOriginal.getValorMaquinaEquipamentoFormatado());


      // CAMPO_VALOR_MERCADO_IMOVEL
      valorDiferente = objetoModificado.getValorMercadoImovel() != objetoOriginal.getValorMercadoImovel();
      registroLogVo(CAMPO_VALOR_MERCADO_IMOVEL, objetoModificado.getValorMercadoImovelFormatado(), objetoOriginal.getValorMercadoImovelFormatado());


      // CAMPO_VALOR_OUTRO
      valorDiferente = objetoModificado.getValorOutro() != objetoOriginal.getValorOutro();
      registroLogVo(CAMPO_VALOR_OUTRO, objetoModificado.getValorOutroFormatado(), objetoOriginal.getValorOutroFormatado());


      // CAMPO_VALOR_PASTAGEM
      valorDiferente = objetoModificado.getValorPastagem() != objetoOriginal.getValorPastagem();
      registroLogVo(CAMPO_VALOR_PASTAGEM, objetoModificado.getValorPastagemFormatado(), objetoOriginal.getValorPastagemFormatado());


      // CAMPO_AREA_PASTAGEM
      valorDiferente = objetoModificado.getAreaPastagem() != objetoOriginal.getAreaPastagem();
      registroLogVo(CAMPO_AREA_PASTAGEM, objetoModificado.getAreaPastagemFormatado(), objetoOriginal.getAreaPastagemFormatado());


      // CAMPO_AREA_TOTAL
      valorDiferente = objetoModificado.getAreaTotal() != objetoOriginal.getAreaTotal();
      registroLogVo(CAMPO_AREA_TOTAL, objetoModificado.getAreaTotalFormatado(), objetoOriginal.getAreaTotalFormatado());


      // CAMPO_CODIGO_RECEITA_FEDERAL
      valorDiferente = objetoModificado.getCodigoReceitaFederal() != objetoOriginal.getCodigoReceitaFederal();
      registroLogVo(CAMPO_CODIGO_RECEITA_FEDERAL, objetoModificado.getCodigoReceitaFederalFormatado(), objetoOriginal.getCodigoReceitaFederalFormatado());


      // CAMPO_NUMERICO_INDEA
      valorDiferente = objetoModificado.getNumericoIndea() != objetoOriginal.getNumericoIndea();
      registroLogVo(CAMPO_NUMERICO_INDEA, objetoModificado.getNumericoIndeaFormatado(), objetoOriginal.getNumericoIndeaFormatado());


      // CAMPO_DESCRICAO_DENOMINACAO
      valorDiferente = !objetoModificado.getDescricaoDenominacao().equalsIgnoreCase(objetoOriginal.getDescricaoDenominacao());
      registroLogVo(CAMPO_DESCRICAO_DENOMINACAO, objetoModificado.getDescricaoDenominacao(), objetoOriginal.getDescricaoDenominacao());


      // CAMPO_QUANTIDADE_DISTANCIA
      valorDiferente = objetoModificado.getQuantidadeDistancia() != objetoOriginal.getQuantidadeDistancia();
      registroLogVo(CAMPO_QUANTIDADE_DISTANCIA, objetoModificado.getQuantidadeDistanciaFormatado(), objetoOriginal.getQuantidadeDistanciaFormatado());


      // CAMPO_SITUACAO_ACESSAO_NATURAL
      valorDiferente = objetoModificado.getSituacaoAcessaoNatural().getValorCorrente() != objetoOriginal.getSituacaoAcessaoNatural().getValorCorrente();
      registroLogVo(CAMPO_SITUACAO_ACESSAO_NATURAL, objetoModificado.getSituacaoAcessaoNatural().getTextoCorrente(), objetoOriginal.getSituacaoAcessaoNatural().getTextoCorrente());
      
      
      // CAMPO_QTDE_DISTANCIA_ASFALTO
      valorDiferente = objetoModificado.getDistanciaAsfalto() != objetoOriginal.getDistanciaAsfalto();
      registroLogVo(CAMPO_QTDE_DISTANCIA_ASFALTO, objetoModificado.getDistanciaAsfaltoFormatado(), objetoOriginal.getDistanciaAsfaltoFormatado());

      // CAMPO_SITUACAO_PASTAGEM
      valorDiferente = objetoModificado.getSituacaoPastagem().getValorCorrente() != objetoOriginal.getSituacaoPastagem().getValorCorrente();
      registroLogVo(CAMPO_SITUACAO_PASTAGEM, objetoModificado.getSituacaoPastagem().getTextoCorrente(), objetoOriginal.getSituacaoPastagem().getTextoCorrente());


      // CAMPO_ACCTB06_CODIGO_ENDERECO
      valorDiferente = !objetoModificado.getEnderecoVo().getCep().getCodgCep().equals(objetoOriginal.getEnderecoVo().getCep().getCodgCep());
      registroLogVo(CAMPO_ACCTB06_CODIGO_ENDERECO, objetoModificado.getEnderecoVo().getCep().getCodgCep().toString(), objetoOriginal.getEnderecoVo().getCep().getCodgCep().toString());

      if (dadoObrigatorio)
      {
         historicoLogVo.setInfoChavePrimaria("" + objetoOriginal.getCodigo());
      }

   }


}
