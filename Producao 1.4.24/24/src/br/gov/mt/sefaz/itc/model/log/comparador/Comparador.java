package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.model.log.itemhistorico.ItemHistoricoVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;


public abstract class Comparador<T> implements TabelasITC
{
   private String nomeTabela;
   protected HistoricoLogVo historicoLogVo;
   protected ItemHistoricoVo items;
   protected static final String MASCARA_DATA_TIMESTEMP = "dd/MM/yyyy HH:mm:ss";
   protected Boolean dadoObrigatorio = false;
   protected Boolean valorDiferente = false;
   private DomnTipoOperacao tipoOperacao;

   public Comparador(String nomeTabela)
   {
      this.nomeTabela = nomeTabela;
      if (historicoLogVo == null)
      {
         historicoLogVo = new HistoricoLogVo();
         historicoLogVo.setNomeTabela(this.nomeTabela);
         historicoLogVo.setDomnTipoOperacao(new DomnTipoOperacao(DomnTipoOperacao.ALTERACAO));
      }

      if (items == null)
      {
         items = new ItemHistoricoVo();
      }
   }

   public Comparador(String nomeTabela, DomnTipoOperacao tipoOperacao)
   {
      this.nomeTabela = nomeTabela;
      if (historicoLogVo == null)
      {
         historicoLogVo = new HistoricoLogVo();
         historicoLogVo.setNomeTabela(this.nomeTabela);
         historicoLogVo.setDomnTipoOperacao(tipoOperacao);
      }

      if (items == null)
      {
         items = new ItemHistoricoVo();
      }
   }


   /**
    * Método especializado em criar objeto do tipo <b>HistoricoLogVo</b>.
    * 
    * 
    * 
    * @return Retorna um <b>HistoricoLogVo</b> com todas as informações
    * preechidas.
    * 
    */
   public HistoricoLogVo criarObjetoHistoricoLogvo(String valorAnterior, String nomeCampo, DomnTipoOperacao tipoOperacao)
   {
      HistoricoLogVo temp = new HistoricoLogVo();
      //temp.setNomeCampo(nomeCampo);
      temp.setNomeTabela(nomeTabela);
      temp.setDomnTipoOperacao(tipoOperacao);

      addItemCollection(temp);

      return historicoLogVo;
   }


   /**
    * Método especializado em criar objeto do tipo <b>HistoricoLogVo</b>.
    *  OBS : Caso não seja definido o tipo de operação (INCLUSÃO, ALTERAÇÃO ou EXCLUSÃO)
    * por padrão a operação será considerada com do tipo (ALTERAçÃO)
    * 
    * @return Retorna um <b>HistoricoLogVo</b> com todas as informações
    * preechidas.
    * @deprecated
    */
   public HistoricoLogVo criarObjetoHistoricoLogvo(String valorAnterior, String nomeCampo)
   {
      HistoricoLogVo temp = new HistoricoLogVo();
      //temp.setNomeCampo(nomeCampo);
      temp.setNomeTabela(nomeTabela);
      temp.setDomnTipoOperacao(new DomnTipoOperacao(DomnTipoOperacao.ALTERACAO));

      addItemCollection(temp);

      return historicoLogVo;
   }

   protected void registroLogVo(String nomeCampo, String valorAnterior)
   {
      items.getCollVO().add(new ItemHistoricoVo(nomeCampo, valorAnterior));
   }


   /**
    *  <b>Objetivo:</b> Este método tem por objetivo verificar o
    *  tipo de operação(INCLUSAO, ALTERACAO , EXCLUSAO ) que esta sendo executada
    *  e adicionar no LOG dados da operação.
    * 
    * 
    * 
    * 
    * @param nomeCampo
    * @param valorAnterior 
    * @param valorAtual
    */
   protected void registroLogVo(String nomeCampo, String valorAtual, String valorAnterior)
   {
      // DomnTipoOperacao.ALTERACAO = true & valorDiferente = true : Criar LOG de Alteracao.
      if (historicoLogVo.getDomnTipoOperacao().is(DomnTipoOperacao.ALTERACAO) & valorDiferente)
      {
         addItemHistoricoVo( new ItemHistoricoVo(nomeCampo, valorAtual, valorAnterior));
         dadoObrigatorio = true;
      }

      // DomnTipoOperacao.INCLUSAO  : Criar LOG de Inclusao.
      if (historicoLogVo.getDomnTipoOperacao().is(DomnTipoOperacao.INCLUSAO))
      {
         items.getCollVO().add(new ItemHistoricoVo(nomeCampo, valorAtual, null));
         dadoObrigatorio = true;
      }

      // DomnTipoOperacao.EXCLUSAO : Criar LOG de Exclusao.
      if (historicoLogVo.getDomnTipoOperacao().is(DomnTipoOperacao.EXCLUSAO))
      {
         items.getCollVO().add(new ItemHistoricoVo(nomeCampo, null, valorAnterior));
         dadoObrigatorio = true;
      }
   }

   private void addItemCollection(HistoricoLogVo temp)
   {
      if (Validador.isObjetoValido(temp))
      {
         historicoLogVo.getCollVO().add(temp);
      }

   }

   /**
    *<b>Objetivo : </b> Este método tem por objetivo adicionar um  
    * ItemHistoricoVo na coleção do objeto.
    * 
    * @param itemHistoricoVo
    */
   private void addItemHistoricoVo(ItemHistoricoVo itemHistoricoVo)
   {
      if (itemHistoricoVo != null)
      {
         items.getCollVO().add(itemHistoricoVo);
      }
   }


   /**
    * <b>Objetivo : </b> Este método tem por objetivo centralizar a chamada
    * da rotina de comparação das classes que herdam.
    * 
    * 
    * 
    * @param objetoModificada
    * @param objetoOriginal
    * @return
    */
   public abstract HistoricoLogVo rotinaComparacao(T objetoModificada, T objetoOriginal);

   /**
    * <b>Objetivo : </b> Este método tem por objetivo centralizar as comparações
    * entre atributos de classes que herdarem desta.
    * 
    * 
    * @param objetoModificado
    * @param objetoOriginal
    */
   protected abstract void comparar(T objetoModificado, T objetoOriginal);

   public void setTipoOperacao(DomnTipoOperacao tipoOperacao)
   {
      this.tipoOperacao = tipoOperacao;
   }

   public DomnTipoOperacao getTipoOperacao()
   {
      return tipoOperacao;
   }

   /**
    * <b>Objetivo : </b> Este método tem por objetivo verificar se o tipo de
    * operação que sendo processada é do tipo alteração (DomnTipoOperacao.ALTERACAO)
    * 
    * 
    * 
    * @return true se e somente se a operação for do tipo alteração (DomnTipoOperacao.ALTERACAO).
    */
   protected boolean isOperacaoDeAlteracao()
   {
      return historicoLogVo.getDomnTipoOperacao().is(DomnTipoOperacao.ALTERACAO);
   }

}
