/*
* Ábaco Tecnologia de Informação - LTDA
* Arquivo: ItemHistoricoVo.java
* Revisão: Dherkyan Ribeiro da Silva
* Data revisão: 08/08/2014
*/
package br.gov.mt.sefaz.itc.model.log.itemhistorico;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;

import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposItemHistoricoLog;
import br.gov.mt.sefaz.itc.util.facade.tabelas.NomeAmigavel;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;


/**
 * Esta classe faz parte do sistema de LOG do sistema ITCD.
 * </br>
 * O <code>ItemHistoricoVo</code> representa os dados
 * do LOG serão persistidos no BD quando ocorrer alguma
 * alteração como Incluir, Excluir ou Alterar alguma informação. 
 * 
 * @author Dherkya Ribeiro da Silva
 * @implemented by Dherkyan Ribeiro da silva dherkyan@hotmail.com
 * @version 1.0, 08/08/2014
 * @see br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo
 * @see br.gov.mt.sefaz.itc.model.log.log.LogITCDVo
 * @see br.gov.mt.sefaz.itc.util.facade.tabelas.NomeAmigavel
 * @since JDK1.0
 */
@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_GIA_ITCD_ITEM_HISTORICO_LOG, nomeAmigavel = "ITEM HISTORICO", identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = CamposItemHistoricoLog.CAMPO_NUMR_ITEM_SEQC, sequencia = @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_ITEM_HISTORICO_LOG, tipoSequencia = DomnTipoSequencia.SEQUENCE)
         )
         } )
public class ItemHistoricoVo extends EntidadeVo<ItemHistoricoVo>
{

   // ----------------------------------------------------------------------- Atributos ------------------------------------------------------------------------

   /** Constante de versão para interoperabilidade entre as versões. */
   private static final long serialVersionUID = Long.MAX_VALUE;

   /** Representa o nome de coluna no BD. */
   private String nomeCampo;

   /** Valor existente no BD antes da alteração ocorrer. */
   private String valorAnterior;

   /** Valor atual no BD. */
   private String valorAtual;

   /** Representa a tabela em que esta ocorendo as alterações. */
   private HistoricoLogVo historicoLogVo;

   /** Representa o nome amigavel de uma coluna do BD para exibr na View. */
   private String nomeAmigavel;


   // ------------------------------------------------------------------- Construtores -------------------------------------------------------------------------

   public ItemHistoricoVo()
   {
      super();
   }

   public ItemHistoricoVo(String nomeCampo, String valorAnterior)
   {
      super();
      this.nomeCampo = nomeCampo;
      this.valorAnterior = valorAnterior;

   }

   public ItemHistoricoVo(String nomeCampo, String valorAtual , String valorAnterior)
   {
      super();
      this.nomeCampo = nomeCampo;
      this.valorAnterior = valorAnterior;
      this.valorAtual = valorAtual;
   }

   public ItemHistoricoVo(long codigo)
   {
      super(codigo);
   }

   public ItemHistoricoVo(ItemHistoricoVo itemHistoricoVo)
   {
      super();
      setParametroConsulta(itemHistoricoVo);
   }


   // ------------------------------------------------------------------- Getters & Setters Utilitarios --------------------------------------------------------

   /**
    * <b>Objetivo:</b>
    * Este método tem por objetivo retornar o nome
    * amigavel
    * 
    * 
    * Este método retorna o nome amigavel
    * atraves de busca utilizando como parametro
    * o tributo <b>nomeCampo</b> que representa o nome
    * real da coluna no BD.
    * 
    * 
    * @return String retorna o nome amigavel 
    * da coluna caso não esteja definido um 
    * nome amigavel para esta coluna será retornado
    * a frase "Nome Amigável não Definido."
    */
   public String getNomeCampoAmigavel()
   {
      setNomeAmigavel(new NomeAmigavel().coluna(getNomeCampo()));
      if (nomeAmigavel == null || this.nomeCampo.equalsIgnoreCase(this.nomeAmigavel))
      {
         return "Nome Amigável não Definido.";
      }
      return nomeAmigavel;
   }


   // ----------------------------------------------------------------------- Getters & Setters ----------------------------------------------------------------

   public long getCodigo()
   {
      return super.getCodigo();
   }

   public void setNomeCampo(String nomeCampo)
   {
      this.nomeCampo = nomeCampo;
   }

   @AnotacaoAtributo(nomeColuna = CamposItemHistoricoLog.CAMPO_NOME_CAMPO, obrigatorio = false)
   public String getNomeCampo()
   {
      return nomeCampo;
   }

   @AnotacaoAtributo(nomeColuna = CamposItemHistoricoLog.CAMPO_VALOR_ANTERIOR, obrigatorio = false)
   public void setValorAnterior(String valorAnterior)
   {
      this.valorAnterior = valorAnterior;
   }

   public String getValorAnterior()
   {
      return valorAnterior;
   }

   public void setHistoricoLogVo(HistoricoLogVo historicoLogVo)
   {
      this.historicoLogVo = historicoLogVo;
   }

   @AnotacaoAtributoExterno(obrigatorio = true, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposItemHistoricoLog.CAMPO_NUMR_HITORICO_SEQC, nomeAtributo = "codigo")
            } )
   public HistoricoLogVo getHistoricoLogVo()
   {
      return historicoLogVo;
   }


   public void setValorAtual(String valorAtual)
   {
      this.valorAtual = valorAtual;
   }


   @AnotacaoAtributo(nomeColuna = CamposItemHistoricoLog.CAMPO_VALOR_ATUAL, obrigatorio = false)
   public String getValorAtual()
   {
      return valorAtual;
   }


   public void setNomeAmigavel(String nomeAmigavel)
   {
      this.nomeAmigavel = nomeAmigavel;
   }

   public String getNomeAmigavel()
   {
      setNomeAmigavel(new NomeAmigavel().coluna(getNomeCampo()));
      if (nomeAmigavel == null || this.nomeCampo.equalsIgnoreCase(this.nomeAmigavel))
      {
         nomeAmigavel = "Nome Amigável não Definido.";
      }

      return nomeAmigavel;
   }
   
   // ------------------------------------------------------------------------- equals & hashCode --------------------------------------------------------------
   public int hashCode()
   {
      int hash = 1;
      //hash = hash * MULTIPLICADOR_HASH_CODE + (int) (getCodigo() ^ (getCodigo() >>> 32));
      hash = (hash + (int) getCodigo()) * MULTIPLICADOR_HASH_CODE;
      return hash;
   }
   
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (!(obj instanceof ItemHistoricoVo))
         return false;
      ItemHistoricoVo objetcVO = (ItemHistoricoVo) obj;
      if (getCodigo() != objetcVO.getCodigo())
         return false;
      return true;
   }


} // fim classe ItemHistoricoVo.
