package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBemTributavel;

import java.util.Collection;


public class ComparadorBemTributavelVo extends Comparador<BemTributavelVo> implements CamposBemTributavel
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   public ComparadorBemTributavelVo()
   {
      super(TABELA_GIA_ITCD_BEM_TRIBUTAVEL);
   }
   
   public ComparadorBemTributavelVo( DomnTipoOperacao tipoOperacao)
   {
      super(TABELA_GIA_ITCD_BEM_TRIBUTAVEL , tipoOperacao);
   }


   public HistoricoLogVo rotinaComparacao(BemTributavelVo objetoModificada, BemTributavelVo objetoOriginal)
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

   /**
    * 
    * 
    * 
    * 
    * 
    * 
    */
   public void comparar(BemTributavelVo bemTributavelVoModificado, BemTributavelVo bemTributavelVoOriginal)
   {
      // CAMPO_ISENCAO_PREVISTA
      valorDiferente = bemTributavelVoModificado.getIsencaoPrevista().getDomnValr() != bemTributavelVoOriginal.getIsencaoPrevista().getDomnValr();
      registroLogVo(CAMPO_ISENCAO_PREVISTA, bemTributavelVoModificado.getIsencaoPrevista().getTextoCorrente(), bemTributavelVoOriginal.getIsencaoPrevista().getTextoCorrente());


      // CAMPO_VALOR_MERCADO
      valorDiferente = bemTributavelVoModificado.getValorMercado() != bemTributavelVoOriginal.getValorMercado();
      registroLogVo(CAMPO_VALOR_MERCADO, bemTributavelVoModificado.getValorMercadoFormatado(), bemTributavelVoOriginal.getValorMercadoFormatado());

      //DESCRICAO BEM TRIBUTAVEL
      valorDiferente = !bemTributavelVoModificado.getDescricaoBemTributavel().equals(bemTributavelVoOriginal.getDescricaoBemTributavel());
      registroLogVo(CAMPO_DESCRICAO_BEM_TRIBUTAVEL, bemTributavelVoModificado.getDescricaoBemTributavel(), bemTributavelVoOriginal.getDescricaoBemTributavel());


      //BEM PARTICULAR
      valorDiferente = bemTributavelVoModificado.getBemParticular().getDomnValr() != bemTributavelVoOriginal.getBemParticular().getDomnValr();
      registroLogVo(CAMPO_FLAG_BEM_PARTICULAR, bemTributavelVoModificado.getBemParticular().getTextoCorrente(), bemTributavelVoOriginal.getBemParticular().getTextoCorrente());


      //TIPO DE USUARIO
      valorDiferente = bemTributavelVoOriginal.getTipoUsuario().getDomnValr() != bemTributavelVoOriginal.getTipoUsuario().getDomnValr();
      registroLogVo(CAMPO_TIPO_USUARIO_INCLUSAO, bemTributavelVoModificado.getTipoUsuario().getTextoCorrente(), bemTributavelVoOriginal.getTipoUsuario().getTextoCorrente());
      
      if(dadoObrigatorio)
      {
         valorDiferente = true;
         historicoLogVo.setInfoChavePrimaria("" +bemTributavelVoOriginal.getCodigo());
      }
      
   }

   /**
    * Método responsável por verificar se os objetos comparados possuem
    * o mesmo código ou sej representam o mesmo registro no BD.
    * 
    * Lógica
    *  1 - comparar o código de cada elemento da colecaoA com todos os
    * códigos dos elementos da colecaoB
    *  2 - caso exista um código correspondente na colecaoB invoca o 
    *  próximo método.
    * 
    * @param colecaoA Lista com o maior números de elementos.
    * @param colecaoB Lista com o menor números de elementos.
    * 
    */
   private void rotinaComparacao(Collection<BemTributavelVo> colecaoA, Collection<BemTributavelVo> colecaoB)
   {

      for (BemTributavelVo bemA: colecaoA)
      {
         boolean criarLog = true;
         for (BemTributavelVo bemB: colecaoB)
         {
            if (bemA.getBemVo().getDescricaoTipoBem().trim().equalsIgnoreCase(bemB.getBemVo().getDescricaoTipoBem().trim()))
            {
               compararValor(bemA, bemB);
               criarLog = false;
            }

         }
         if (criarLog)
         {
            criarRegistroLogIncluido(bemA);
         }

      }
      verificarBemExcluido(colecaoA, colecaoB);

   }

   /**
    * 
    * 
    * 
    * 
    * 
    */
   private void compararValor(BemTributavelVo bemTributavelVo, BemTributavelVo bemTributavelVoOriginal)
   {

      // CODIGO BEM TRIBUTAVEL
      //      if (bemTributavelVo.getBemVo().getCodigo() != bemTributavelVoOriginal.getBemVo().getCodigo())
      //      {System.out.println("CODIGO BEM TRIBUTAVEL : " + bemTributavelVo.getBemVo().getCodigo());}


      // ISENCAO PERVISTA
      if (bemTributavelVo.getIsencaoPrevista().getDomnValr() != bemTributavelVoOriginal.getIsencaoPrevista().getDomnValr())
      {
         criarObjetoHistoricoLogvo(bemTributavelVoOriginal.getIsencaoPrevista().getTextoCorrente(), CAMPO_ISENCAO_PREVISTA);
      }

      // VALOR DO MERCADO
      if (bemTributavelVo.getValorMercado() != bemTributavelVoOriginal.getValorMercado())
      {
         criarObjetoHistoricoLogvo(bemTributavelVoOriginal.getValorMercadoFormatado(), CAMPO_VALOR_MERCADO);
      }

      //DESCRICAO BEM TRIBUTAVEL
      if (!bemTributavelVo.getDescricaoBemTributavel().equals(bemTributavelVoOriginal.getDescricaoBemTributavel()))
      {
         criarObjetoHistoricoLogvo(bemTributavelVoOriginal.getDescricaoBemTributavel(), CAMPO_DESCRICAO_BEM_TRIBUTAVEL);
      }

      //BEM PARTICULAR
      if (bemTributavelVo.getBemParticular().getDomnValr() != bemTributavelVoOriginal.getBemParticular().getDomnValr())
      {
         criarObjetoHistoricoLogvo(bemTributavelVoOriginal.getBemParticular().getTextoCorrente(), CAMPO_FLAG_BEM_PARTICULAR);
      }

      //TIPO DE USUARIO
      if (bemTributavelVoOriginal.getTipoUsuario().getDomnValr() != bemTributavelVoOriginal.getTipoUsuario().getDomnValr())
      {
         criarObjetoHistoricoLogvo(bemTributavelVo.getTipoUsuario().getTextoCorrente(), CAMPO_TIPO_USUARIO_INCLUSAO);
      }

   }


   /**
    * Método resposavel por gerar um registro de log de BensTributaveis que
    * foram excluidos.
    * 
    * Metodo de Comparação : Os itens são comparados pela descrição que deve ser única
    * para cada item.
    * 
    * Logica : Compara cada item da lista (colecaoB = dados retornados do BD)
    * com todos os itens da lista (colecaoA = aos itens que está sendo alterada.)
    * caso um item da lista (colecaB) não seja encontrado na lista (colecaoA)
    * este registro sera considerado excluido.
    * 
    * 
    */
   private void verificarBemExcluido(Collection<BemTributavelVo> colecaoA, Collection<BemTributavelVo> colecaoB)
   {

      for (BemTributavelVo bemB: colecaoB)
      {
         boolean criarLog = true;
         for (BemTributavelVo bemA: colecaoA)
         {
            if (bemB.getBemVo().getDescricaoTipoBem().trim().equalsIgnoreCase(bemA.getBemVo().getDescricaoTipoBem().trim()))
            {
               criarLog = false;
            }

         }
         if (criarLog)
         {
            criarRegistroLog(bemB);
         }
      }
   }


   private void criarRegistroLog(BemTributavelVo bemTributavelVo)
   {
      criarObjetoHistoricoLogvo(bemTributavelVo.getDescricaoBemTributavel(), CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL, new DomnTipoOperacao(DomnTipoOperacao.EXCLUSAO));
   }

   private void criarRegistroLogIncluido(BemTributavelVo bemTributavelVo)
   {
      criarObjetoHistoricoLogvo(bemTributavelVo.getDescricaoBemTributavel(), CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL, new DomnTipoOperacao(DomnTipoOperacao.INCLUSAO));
   }

}
