package br.gov.mt.sefaz.itc.model.relatorio.parametrorelatorio;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.PedidoRelatorioVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposParametroRelatorio;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

/**
 * 
 * Classe Responsável por encapsular os valores do objeto ParametroRelatorio
 * @author Dherkyan Ribeiro da Silva
 */
@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_PARAMETRO_RELATORIO, nomeAmigavel = 
      TabelasITC.NOME_AMIGAVEL_PEDIDO_RELATORIO, identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = 
               CamposParametroRelatorio.CAMPO_CODIGO_PARAMETRO, sequencia = 
               @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_PARAMETRO_RELATORIO, tipoSequencia = 
                     DomnTipoSequencia.SEQUENCE)
         )
         } )
public class ParametroRelatorioVo extends EntidadeVo<ParametroRelatorioVo>
{

   private static final long serialVersionUID = Long.MAX_VALUE;
   
   /*
    * Representa o relatorio que ao
    * este parametros pertecem
    * 
    */
   private PedidoRelatorioVo pedidoRelatorio;
   private String nomeParametro;
   private String valorParametro;

   public ParametroRelatorioVo()
   {
      super();
   }

   public ParametroRelatorioVo(long codigo)
   {
      super(codigo);
   }

   public ParametroRelatorioVo(ParametroRelatorioVo parametroRelatorioVo)
   {
      super();
      setParametroConsulta(parametroRelatorioVo);
   }
   
   public ParametroRelatorioPk getPk()
   {
      return new ParametroRelatorioPk(getCodigo());
   }

   public void setPedidoRelatorio(PedidoRelatorioVo pedidoRelatorio)
   {
      this.pedidoRelatorio = pedidoRelatorio;
   }
   
   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposParametroRelatorio.CAMPO_TB60_CODIGO_RELATORIO, nomeAtributo = "codigo")
            } )
   public PedidoRelatorioVo getPedidoRelatorio()
   {
      if(!Validador.isObjetoValido(this.pedidoRelatorio))
      {
         this.pedidoRelatorio = new PedidoRelatorioVo();
      }
      return pedidoRelatorio;
   }

   public void setNomeParametro(String nomeParametro)
   {
      this.nomeParametro = nomeParametro;
   }

   @AnotacaoAtributo(nomeColuna = CamposParametroRelatorio.CAMPO_NOME_PARAMETRO, obrigatorio = false)
   public String getNomeParametro()
   {
      return nomeParametro;
   }

   public void setValorParametro(String valorParametro)
   {
      this.valorParametro = valorParametro;
   }

   @AnotacaoAtributo(nomeColuna = CamposParametroRelatorio.CAMPO_VALOR_PARAMETRO, obrigatorio = false)
   public String getValorParametro()
   {
      return valorParametro;
   }
}
