package br.gov.mt.sefaz.itc.model.log.historicolog;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.log.itemhistorico.ItemHistoricoVo;
import br.gov.mt.sefaz.itc.model.log.log.LogITCDVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposHistoricoLog;
import br.gov.mt.sefaz.itc.util.facade.tabelas.NomeAmigavel;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;


@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_GIA_ITCD_HISTORICO_LOG, nomeAmigavel = "Historico LOG-ITCD", identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = CamposHistoricoLog.CAMPO_NUM_HIST_SEQ)
         } )
public class HistoricoLogVo extends EntidadeVo<HistoricoLogVo>
{

   // ----------------------------------------------------------------------- Atributos ------------------------------------------------------------------------
   private static final long serialVersionUID = Long.MAX_VALUE;

   /** Tipo de operação realizada no BD Inclusão, Alteração e Exclusão. */
   private DomnTipoOperacao domnTipoOperacao;

   /** Registro do LOG gerado. */
   private LogITCDVo logITCDVo;

   /** Nome real da tabela no BD. */
   private String nomeTabela;

   /** Nome Amiguavel da tabela no sistema. */
   private String nomeAmigavel;

   /** Registro de itens alterados, incluidos e excluidos.*/
   private ItemHistoricoVo itemLog;

   /** Valor da chave primaria da tabela no BD*/
   private String infoChavePrimaria;

   // ------------------------------------------------------------------- Construtores -------------------------------------------------------------------------
   public HistoricoLogVo()
   {
      super();
   }

   public HistoricoLogVo(long codigo)
   {
      super(codigo);
   }

   public HistoricoLogVo(HistoricoLogVo historicoLogVo)
   {
      super();
      setParametroConsulta(historicoLogVo);
   }

   // ------------------------------------------------------------------- Getters & Setters Utilitarios --------------------------------------------------------

   public void setNomeAmigavel(String nomeAmigavel)
   {
      this.nomeAmigavel = nomeAmigavel;
   }

   /**
    * Retorna um nome amigavel referente
    * ao nome da tabela no BD.
    * 
    * Este método retorna o nome amigavel
    * atraves de busca utilizando como parametro
    * o atributo <b>nomeTabela</b> que representa o nome
    * real da tabela no BD.
    * 
    * 
    * @return String retorna o nome amigavel 
    * da tabela caso não esteja definido um 
    * nome amigavel para esta tabela será retornado
    * a frase "Nome Amigável não Definido."
    */
   public String getNomeAmigavel()
   {
      setNomeAmigavel(new NomeAmigavel().tabela(getNomeTabela()));
      if (nomeAmigavel == null || this.nomeTabela.equalsIgnoreCase(this.nomeAmigavel))
      {
         nomeAmigavel = "Nome Amigável não Definido.";
      }

      return nomeAmigavel;
   }


   // ----------------------------------------------------------------------- Getters & Setters ----------------------------------------------------------------

   public long getCodigo()
   {
      return super.getCodigo();
   }


   public void setLogITCDVo(LogITCDVo logITCDVo)
   {
      this.logITCDVo = logITCDVo;
   }

   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposHistoricoLog.CAMPO_ITCTB48_NUM_LOG_SEQ, nomeAtributo = "codigo")
            } )
   public LogITCDVo getLogITCDVo()
   {
      return logITCDVo;
   }

   public void setNomeTabela(String nomeTabela)
   {
      this.nomeTabela = nomeTabela;
   }

   @AnotacaoAtributo(nomeColuna = CamposHistoricoLog.CAMPO_NOME_TABELA, obrigatorio = false)
   public String getNomeTabela()
   {
      return nomeTabela;
   }

   public void setDomnTipoOperacao(DomnTipoOperacao domnTipoOperacao)
   {
      this.domnTipoOperacao = domnTipoOperacao;
   }


   @AnotacaoAtributo(nomeColuna = CamposHistoricoLog.CAMPO_TIPO_OPERACAO, obrigatorio = false)
   public DomnTipoOperacao getDomnTipoOperacao()
   {
      if (!Validador.isObjetoValido(domnTipoOperacao))
      {
         setDomnTipoOperacao(new DomnTipoOperacao(-1));
      }
      return domnTipoOperacao;
   }


   public void setItemLog(ItemHistoricoVo itemLog)
   {
      this.itemLog = itemLog;
   }

   public ItemHistoricoVo getItemLog()
   {
      return itemLog;
   }


   public void setInfoChavePrimaria(String infoChavePrimaria)
   {
      this.infoChavePrimaria = infoChavePrimaria;
   }

   @AnotacaoAtributo(nomeColuna = CamposHistoricoLog.CAMPO_INFO_CHAVE_PRIMARIA, obrigatorio = false)
   public String getInfoChavePrimaria()
   {
      return infoChavePrimaria;
   }
}// fim da classe HistoricoLogVo.
