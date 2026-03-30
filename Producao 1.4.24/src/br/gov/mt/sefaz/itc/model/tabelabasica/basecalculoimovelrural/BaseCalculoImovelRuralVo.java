/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BaseCalculoImovelRuralVo.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data criação : 18/09/2015
 * Data ultima revisão : 18/09/2015
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnCriterioBaseCalculo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAtividade;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDistancia;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBaseCalculoImovelRural;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 
 * Classe Responsável por encapsular os valores do objeto Base de Calculo Imovel Rural
 * @author Dherkyan Ribeiro da Silva
 */
@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_BASE_CALCULO_IMOVEL_RURAL, nomeAmigavel = 
      TabelasITC.NOME_AMIGAVEL_BASE_CALCULO_IMOVEL_RURAL, identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = CamposBaseCalculoImovelRural.CAMPO_CODIGO, sequencia = 
               @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_BASE_CALCULO_IMOVEL_RURAL, tipoSequencia = 
                     DomnTipoSequencia.SEQUENCE)
         )
         } )
public class BaseCalculoImovelRuralVo extends EntidadeVo<BaseCalculoImovelRuralVo>
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   private DomnTipoDistancia tipoDistancia;
   private DomnTipoAtividade tipoAtividade;
   private int quantidadeDistanciaInicial;
   private int quantidadeDistanciaFinal;
   private int percentualAtividadeInicial;
   private int percentualAtividadeFinal;
   private int percentualAreaExploradaInical;
   private int percentualAreaExploradaFinal;
   private int numeroItem;
   private double percentualBaseCalculoMinimo;
   private DomnCriterioBaseCalculo criterioBaseCalculo;
   private DomnStatusRegistro statusResgistroBaseCalculoImovelRural;
   private Date dataAtualizacaoBD;
   private boolean maiorDistanciaFinalPerimetroUrbano;

   public BaseCalculoImovelRuralVo()
   {
      super();
   }

   public BaseCalculoImovelRuralVo(long codigo)
   {
      super(codigo);
   }

   public BaseCalculoImovelRuralVo(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
   {
      super();
      setParametroConsulta(baseCalculoImovelRuralVo);
   }

   public long getCodigo()
   {
      return super.getCodigo();
   }

   public void setTipoDistancia(DomnTipoDistancia tipoDistancia)
   {
      this.tipoDistancia = tipoDistancia;
   }

   @AnotacaoAtributo(nomeColuna = CamposBaseCalculoImovelRural.CAMPO_TIPO_DISTANCIA, obrigatorio = false)
   public DomnTipoDistancia getTipoDistancia()
   {
      if (Validador.isDominioNumericoValido(this.tipoDistancia))
      {
         return this.tipoDistancia;
      }
      else
      {
         setTipoDistancia(new DomnTipoDistancia(-1));
         return this.tipoDistancia;
      }
   }

   public void setTipoAtividade(DomnTipoAtividade tipoAtividade)
   {
      this.tipoAtividade = tipoAtividade;
   }

   @AnotacaoAtributo(nomeColuna = CamposBaseCalculoImovelRural.CAMPO_TIPO_ATIVIDADE, obrigatorio = false)
   public DomnTipoAtividade getTipoAtividade()
   {
      if (Validador.isDominioNumericoValido(this.tipoAtividade))
      {
         return this.tipoAtividade;
      }
      else
      {
         setTipoAtividade(new DomnTipoAtividade(-1));
      }
      return this.tipoAtividade;
   }

   public void setQuantidadeDistanciaInicial(int quantidadeDistanciaInicial)
   {
      this.quantidadeDistanciaInicial = quantidadeDistanciaInicial;
   }

   @AnotacaoAtributo(nomeColuna = CamposBaseCalculoImovelRural.CAMPO_QUANTIDADE_DISTANCIA_INICIAL, obrigatorio = false)
   public int getQuantidadeDistanciaInicial()
   {
      return quantidadeDistanciaInicial;
   }

   public void setQuantidadeDistanciaFinal(int quantidadeDistanciaFinal)
   {
      this.quantidadeDistanciaFinal = quantidadeDistanciaFinal;
   }

   @AnotacaoAtributo(nomeColuna = CamposBaseCalculoImovelRural.CAMPO_QUANTIDADE_DISTANCIA_FINAL, obrigatorio = false)
   public int getQuantidadeDistanciaFinal()
   {
      return quantidadeDistanciaFinal;
   }

   public void setPercentualAtividadeInicial(int percentualAtividadeInicial)
   {
      this.percentualAtividadeInicial = percentualAtividadeInicial;
   }

   @AnotacaoAtributo(nomeColuna = CamposBaseCalculoImovelRural.CAMPO_PERCENTUAL_ATIVIDADE_INICIAL, obrigatorio = false)
   public int getPercentualAtividadeInicial()
   {
      return percentualAtividadeInicial;
   }

   public void setPercentualAtividadeFinal(int percentualAtividadeFinal)
   {
      this.percentualAtividadeFinal = percentualAtividadeFinal;
   }

   @AnotacaoAtributo(nomeColuna = CamposBaseCalculoImovelRural.CAMPO_PERCENTUAL_ATIVIDADE_FINAL, obrigatorio = false)
   public int getPercentualAtividadeFinal()
   {
      return percentualAtividadeFinal;
   }

   public void setPercentualAreaExploradaInical(int percentualAreaExploradaInical)
   {
      this.percentualAreaExploradaInical = percentualAreaExploradaInical;
   }

   @AnotacaoAtributo(nomeColuna = CamposBaseCalculoImovelRural.CAMPO_PERCENTUAL_AREA_EXPLORADA_INICIAL, obrigatorio = false)
   public int getPercentualAreaExploradaInical()
   {
      return percentualAreaExploradaInical;
   }

   public void setPercentualAreaExploradaFinal(int percentualAreaExploradaFinal)
   {
      this.percentualAreaExploradaFinal = percentualAreaExploradaFinal;
   }

   @AnotacaoAtributo(nomeColuna = CamposBaseCalculoImovelRural.CAMPO_PERCENTUAL_AREA_EXPLORADA_FINAL, obrigatorio = false)
   public int getPercentualAreaExploradaFinal()
   {
      return percentualAreaExploradaFinal;
   }

   public void setNumeroItem(int numeroItem)
   {
      this.numeroItem = numeroItem;
   }

   @AnotacaoAtributo(nomeColuna = CamposBaseCalculoImovelRural.CAMPO_NUMERO_ITEM, obrigatorio = false)
   public int getNumeroItem()
   {
      return numeroItem;
   }

   public void setPercentualBaseCalculoMinimo(double percentualBaseCalculoMinimo)
   {
      this.percentualBaseCalculoMinimo = percentualBaseCalculoMinimo;
   }

   @AnotacaoAtributo(nomeColuna = CamposBaseCalculoImovelRural.CAMPO_PERCENTUAL_BASE_CALCULO_MINIMO, obrigatorio = false)
   public double getPercentualBaseCalculoMinimo()
   {
      return percentualBaseCalculoMinimo;
   }

   public void setCriterioBaseCalculo(DomnCriterioBaseCalculo criterioBaseCalculo)
   {
      this.criterioBaseCalculo = criterioBaseCalculo;
   }

   @AnotacaoAtributo(nomeColuna = CamposBaseCalculoImovelRural.CAMPO_CRITERIO_BASE_CALCULO, obrigatorio = false)
   public DomnCriterioBaseCalculo getCriterioBaseCalculo()
   {
      if (Validador.isDominioNumericoValido(this.criterioBaseCalculo))
      {
         return this.criterioBaseCalculo;
      }
      else
      {
         setCriterioBaseCalculo(new DomnCriterioBaseCalculo(-1));
      }
      return this.criterioBaseCalculo;
   }

   public void setStatusResgistroBaseCalculoImovelRural(DomnStatusRegistro statusResgistroBaseCalculoImovelRural)
   {
      this.statusResgistroBaseCalculoImovelRural = statusResgistroBaseCalculoImovelRural;
   }

   @AnotacaoAtributo(nomeColuna = CamposBaseCalculoImovelRural.CAMPO_STATUS_BASE_CALCULO, obrigatorio = false)
   public DomnStatusRegistro getStatusResgistroBaseCalculoImovelRural()
   {
      if (Validador.isDominioNumericoValido(this.statusResgistroBaseCalculoImovelRural))
      {
         return this.statusResgistroBaseCalculoImovelRural;
      }
      else
      {
         setStatusResgistroBaseCalculoImovelRural(new DomnStatusRegistro(-1));
      }
      return statusResgistroBaseCalculoImovelRural;
   }

   public void setDataAtualizacaoBD(Date dataAtualizacaoBD)
   {
      this.dataAtualizacaoBD = dataAtualizacaoBD;
   }

   @AnotacaoAtributo(nomeColuna = CamposBaseCalculoImovelRural.CAMPO_DATA_ATUALIZACAO_BD, obrigatorio = false)
   public Date getDataAtualizacaoBD()
   {
      return dataAtualizacaoBD;
   }

   // Metodos formatados
   public String getQuantidadeDistanciaInicialFormatada()
   {
      return "" + this.quantidadeDistanciaInicial;
   }

   public String getQuantidadeDistanciaFinalFormatada()
   {
      return "" + this.quantidadeDistanciaFinal;
   }

   public int getPercentualAtividadeInicialFormatada()
   {
      return percentualAtividadeInicial;
   }

   public String getPercentualAtividadeFinalFormatada()
   {
      return "" + this.percentualAtividadeFinal;
   }
   
   public String getPercentualAtividadeFinalFormatadaNaoMostrarZero()
   {
      if(Validador.isNumericoValido(this.percentualAtividadeFinal))
      {
         return STRING_VAZIA + this.percentualAtividadeFinal ;
      }
      return STRING_VAZIA ;
   }

   public String getPercentualAreaExploradaInicalFormatada()
   {
      return "" + this.percentualAreaExploradaInical;
   }

   public String getPercentualAreaExploradaFinalFormatada()
   {
      return "" + this.percentualAreaExploradaFinal;
   }
   
   public String getPercentualAreaExploradaFinalFormatadaNaoMostrarZero()
   {
      if(Validador.isNumericoValido(this.percentualAreaExploradaFinal))
      {
         return STRING_VAZIA + this.percentualAreaExploradaFinal ;
      }
      return STRING_VAZIA ;
   }

   public String getNumeroItemFormatada()
   {
      return "" + this.numeroItem;
   }

   public String getPercentualBaseCalculoMinimoFormatada()
   {
      return "" + this.percentualBaseCalculoMinimo;
   }

   public void setIsMaiorDistanciaFinalPerimetroUrbano(boolean maiorDistanciaFinalPerimetroUrbano)
   {
      this.maiorDistanciaFinalPerimetroUrbano = maiorDistanciaFinalPerimetroUrbano;
   }

   public boolean isIsMaiorDistanciaFinalPerimetroUrbano()
   {
      return maiorDistanciaFinalPerimetroUrbano;
   }

   public List<BaseCalculoImovelRuralVo> getListVo()
   {
      List<BaseCalculoImovelRuralVo> bases = new ArrayList();
      if (Validador.isCollectionValida(this.getCollVO()))
      {
         bases = (List<BaseCalculoImovelRuralVo>) this.getCollVO();
      }
      return bases;
   }

}
