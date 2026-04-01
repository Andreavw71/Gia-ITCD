package br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.relatorio.parametrorelatorio.ParametroRelatorioVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSituacaoProcessamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoRelatorio;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposPedidoRelatorio;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.acessoweb.UsuarioIntegracaoVo;

import br.gov.mt.sefaz.itc.util.integracao.castor.ObjetoIntegracaoVo;

import java.util.Date;
import java.util.List;

import sefaz.mt.util.SefazDataHora;

/**
 * 
 * Classe Responsável por encapsular os valores do objeto PedidoRelatorio
 * @author Dherkyan Ribeiro da Silva
 */
@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_PEDIDO_RELATORIO, nomeAmigavel = 
      TabelasITC.NOME_AMIGAVEL_PEDIDO_RELATORIO, identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = 
               CamposPedidoRelatorio.CAMPO_CODIGO_PEDIDO_RELATORIO, sequencia = 
               @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_PEDIDO_RELATORIO, tipoSequencia = 
                     DomnTipoSequencia.SEQUENCE)
         )
         } )
public class PedidoRelatorioVo extends EntidadeVo<PedidoRelatorioVo>
{

   private static final long serialVersionUID = Long.MAX_VALUE;

   /*
    * Data em que foi realizada a solicitação
    * para gerar o relatório
    */
   private Date dataSolicitacao;

   /*
    * Tipo e nome do relatório
    */
   private DomnTipoRelatorio tipoRelatorio;

   /*
    * Data em que houve processamento do
    * do relatório
    */
   private Date dataProcessamento;

   /*
    * status de processamento do relatório
    */
   private DomnSituacaoProcessamento situacaoProcessamento;

   /*
    * Servidor que fez a solicitação do relatório
    */
   private UsuarioIntegracaoVo usuarioSolicitante;

   /*
    * Parâmetros utilizados para execução do relatório
    *
    */
   private transient ParametroRelatorioVo parametroRelatorioVo;

   /*
    * Objeto de integraçãom com o Castor
    *
    */
   private ObjetoIntegracaoVo castorObjetoIntegracaoVo;

   public PedidoRelatorioVo()
   {
      super();
   }

   public PedidoRelatorioVo(long codigo)
   {
      super(codigo);
   }

   public PedidoRelatorioVo(PedidoRelatorioVo pedidoRelatorioVo)
   {
      super();
      setParametroConsulta(pedidoRelatorioVo);
   }

   public PedidoRelatorioPk getPk()
   {
      return new PedidoRelatorioPk(getCodigo());
   }

   public void setDataSolicitacao(Date dataSolicitacao)
   {
      this.dataSolicitacao = dataSolicitacao;
   }

   @AnotacaoAtributo(nomeColuna = CamposPedidoRelatorio.CAMPO_DATA_SOLICITACAO, obrigatorio = false)
   public Date getDataSolicitacao()
   {
      return dataSolicitacao;
   }
   
   
   public String getDataSolicitacaoFormatada()
   {
      if (Validador.isDataValida(getDataSolicitacao()))
      {
         return new SefazDataHora(getDataSolicitacao()).formata("dd/MM/yyyy");
      }
      else
      {
         return STRING_VAZIA;
      }
   }
   

   public void setTipoRelatorio(DomnTipoRelatorio tipoRelatorio)
   {
      this.tipoRelatorio = tipoRelatorio;
   }

   @AnotacaoAtributo(nomeColuna = CamposPedidoRelatorio.CAMPO_TIPO_RELATORIO, obrigatorio = false)
   public DomnTipoRelatorio getTipoRelatorio()
   {
      return tipoRelatorio;
   }

   public void setDataProcessamento(Date dataProcessamento)
   {
      this.dataProcessamento = dataProcessamento;
   }

   @AnotacaoAtributo(nomeColuna = CamposPedidoRelatorio.CAMPO_DATA_PROCESSAMENTO, obrigatorio = false)
   public Date getDataProcessamento()
   {
      return dataProcessamento;
   }

   public void setSituacaoProcessamento(DomnSituacaoProcessamento situacaoProcessamento)
   {
      this.situacaoProcessamento = situacaoProcessamento;
   }
   
   
   public String getDataProcessamentoFormatada()
   {
      if (Validador.isDataValida(getDataProcessamento()))
      {
         return new SefazDataHora(getDataProcessamento()).formata("dd/MM/yyyy");
      }
      else
      {
         return STRING_VAZIA;
      }
   }
   

   @AnotacaoAtributo(nomeColuna = CamposPedidoRelatorio.CAMPO_SITUACAO_PROCESSAMENTO, obrigatorio = false)
   public DomnSituacaoProcessamento getSituacaoProcessamento()
   {
      return situacaoProcessamento;
   }

   public void setUsuarioSolicitante(UsuarioIntegracaoVo usuarioSolicitante)
   {
      this.usuarioSolicitante = usuarioSolicitante;
   }

   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposPedidoRelatorio.CAMPO_ACSTB11_USUARIO, nomeAtributo = "codigo")
            } )
   public UsuarioIntegracaoVo getUsuarioSolicitante()
   {
      return usuarioSolicitante;
   }

   public void setParametroRelatorioVo(ParametroRelatorioVo parametroRelatorioVo)
   {
      this.parametroRelatorioVo = parametroRelatorioVo;
   }

   public ParametroRelatorioVo getParametroRelatorioVo()
   {
      if (!Validador.isObjetoValido(this.parametroRelatorioVo))
      {
         this.parametroRelatorioVo = new ParametroRelatorioVo();
      }
      return parametroRelatorioVo;
   }

   public List<PedidoRelatorioVo> getListVo()
   {
      return (List<PedidoRelatorioVo>) this.getCollVO();
   }

   public void setCastorObjetoIntegracaoVo(ObjetoIntegracaoVo objetoIntegracaoVo)
   {
      this.castorObjetoIntegracaoVo = objetoIntegracaoVo;
   }

   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposPedidoRelatorio.CAMPO_CASTB01_CODIGO_CASTOR, nomeAtributo = "codigo")
            } )
   public ObjetoIntegracaoVo getCastorObjetoIntegracaoVo()
   {
      if (!Validador.isObjetoValido(this.castorObjetoIntegracaoVo))
      {
         this.castorObjetoIntegracaoVo = new ObjetoIntegracaoVo();
      }
      return castorObjetoIntegracaoVo;
   }
   
   
}
