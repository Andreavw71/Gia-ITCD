/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: RebanhoLPMVo.java
 * Revisão: Dherkyan Ribeiro
 * Data revisão: 14/11/2015
 */
package br.gov.mt.sefaz.itc.model.giaitcd.fichabem.ficharebanholpm;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.ficha.AbstractFichaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichabem.fichaveiculo.FichaVeiculoVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.calculo.CalculoITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaRebanhoLPM;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaVeiculo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import br.gov.mt.sefaz.itc.util.integracao.lpm.ProdutoNcmIntegracaoVo;

import java.util.Collection;
import java.util.Date;

/**
 * Classe responsável por encapsular os valores do objeto Rebanho LPM (Value Object).
 * @author Dherkyan Ribeiro
 * @version $Revision: 1.2 $
 */
@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_REBANHO_LPM, nomeAmigavel = "Rebanho LPM", identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = CamposFichaRebanhoLPM.CAMPO_CODIGO_REBANHO, sequencia = 
               @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_REBANHO_LPM, tipoSequencia = 
                     DomnTipoSequencia.SEQUENCE)
         )
         } )
public class FichaRebanhoLPMVo extends AbstractFichaVo<FichaRebanhoLPMVo>
{
   private ProdutoNcmIntegracaoVo produtoNcmIntegracaoVo;
   private String descricao;
   private double valorUnitario;
   private int quantidade;
   private double valorCalculado;
   private double valorInformado;
   private Date dataAtualizacaoBD;
   private transient DomnSimNao flagConcordaValorArbitrado;

   /**
    * Construtor vazio.
    * @implemented by Dherkyan Ribeiro
    */
   public FichaRebanhoLPMVo()
   {
      super();
   }

   /**
    * Construtor padrão.
    * @param codigo Chave primária
    * @implemented by Dherkyan Ribeiro
    */
   public FichaRebanhoLPMVo(long codigo)
   {
      super(codigo);
   }

   /**
    * Construtor que recebe um parametro de consulta.
    * @param rebanhoVo
    * @implemented by Dherkyan Ribeiro
    */
   public FichaRebanhoLPMVo(FichaRebanhoLPMVo rebanhoVo)
   {
      super();
      setParametroConsulta(rebanhoVo);
   }

   /**
    * Construtor que recebe um Collection.
    * @param collVo Coleção de Value Objects.
    * @implemented by Dherkyan Ribeiro
    */
   public FichaRebanhoLPMVo(Collection collVo)
   {
      super(collVo);
   }

   public long getCodigo()
   {
      return super.getCodigo();
   }

   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }

   /**
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   @AnotacaoAtributo(nomeColuna = CamposFichaRebanhoLPM.CAMPO_DESCRICAO, obrigatorio = false)
   public String getDescricao()
   {
      return descricao;
   }

   public void setValorUnitario(double valorUnitario)
   {
      this.valorUnitario = valorUnitario;
   }

   /**
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   @AnotacaoAtributo(nomeColuna = CamposFichaRebanhoLPM.CAMPO_VALOR_UNITARIO, obrigatorio = false)
   public double getValorUnitario()
   {
      return valorUnitario;
   }

   public void setQuantidade(int quantidade)
   {
      this.quantidade = quantidade;
   }
   public String getValorQuantidadeFormatado()
   {
      if (!Validador.isNumericoValido(this.quantidade))
      {
         return STRING_VAZIA;
      }
      return String.valueOf(this.quantidade);
   }

   /**
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   @AnotacaoAtributo(nomeColuna = CamposFichaRebanhoLPM.CAMPO_QUANTIDADE, obrigatorio = false)
   public int getQuantidade()
   {
      return quantidade;
   }

   public void setValorCalculado(double valorCalculado)
   {
      this.valorCalculado = valorCalculado;
   }

   /**
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   @AnotacaoAtributo(nomeColuna = CamposFichaRebanhoLPM.CAMPO_VALOR_CALCULADO, obrigatorio = false)
   public double getValorCalculado()
   {
      return CalculoITCD.calcularValorTributavelRebanho(this);
    
   }
   
   public String getValorCalculadoFormatado()
   {
      String retorno = STRING_VAZIA;
      if (Validador.isNumericoValido(getValorCalculado()))
      {
         return StringUtil.doubleToMonetario(getValorCalculado());
      }
      return retorno;
   }
   public void setValorInformado(double valorInformado)
   {
      this.valorInformado = valorInformado;
   }

   /**
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   @AnotacaoAtributo(nomeColuna = CamposFichaRebanhoLPM.CAMPO_VALOR_INFORMADO, obrigatorio = false)
   public double getValorInformado()
   {
      return valorInformado;
   }
   public String getValorInformadoFormatado()
   {
      if (!Validador.isNumericoValido(this.valorInformado))
      {
         return STRING_VAZIA;
      }
      return StringUtil.doubleToMonetario(this.valorInformado);
   }

   public void setBemTributavelVo(BemTributavelVo bemTributavelVo)
   {
      super.setBemTributavelVo( bemTributavelVo);
   }

   /**
    * @implemented by Dherkyan Ribeiro
    */
   @AnotacaoAtributoExterno(obrigatorio = true, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposFichaRebanhoLPM.CAMPO_ITCTB18_CODIGO_BEM_TRIBUTAVEL, nomeAtributo = "codigo")
            } )
   public BemTributavelVo getBemTributavelVo()
   {
      return super.getBemTributavelVo();
   }

   public void setProdutoNcmIntegracaoVo(ProdutoNcmIntegracaoVo produtoIntegracaoVo)
   {
      this.produtoNcmIntegracaoVo = produtoIntegracaoVo;
   }

   /**
    * @implemented by Dherkyan Ribeiro
    */
   @AnotacaoAtributoExterno(obrigatorio = true, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposFichaRebanhoLPM.CAMPO_ACGTB95_CODIGO_PRODUTO, nomeAtributo = "codigo")
            } )
   public ProdutoNcmIntegracaoVo getProdutoNcmIntegracaoVo()
   {
      if (!Validador.isObjetoValido(produtoNcmIntegracaoVo))
      {
         setProdutoNcmIntegracaoVo(new ProdutoNcmIntegracaoVo());
      }
      return produtoNcmIntegracaoVo;
   }
   
   //@AnotacaoAtributo(nomeColuna = CamposVeiculo.CAMPO_DATA_ATUALIZACAO_BD, obrigatorio = false)
   public Date getDataAtualizacaoBD()
   {
      return dataAtualizacaoBD;
   }

   public void setDataAtualizacaoBD(Date dataAtualizacaoBD)
   {
      this.dataAtualizacaoBD = dataAtualizacaoBD;
   }
	public void setFlagConcordaValorArbitrado(DomnSimNao flagConcordaValorArbitrado)
	{
		this.flagConcordaValorArbitrado = flagConcordaValorArbitrado;
	}

	public DomnSimNao getFlagConcordaValorArbitrado()
	{
		return flagConcordaValorArbitrado;
	}
}
