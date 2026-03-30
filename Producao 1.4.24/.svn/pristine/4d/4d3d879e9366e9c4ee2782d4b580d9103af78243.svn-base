/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CriterioMunicipioVo.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data criação : 18/09/2015
 * Data ultima revisão : 21/09/2015
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposCriterioMunicipio;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;

import java.util.Date;


/**
 * 
 * Classe Responsável por encapsular os valores do objeto CriterioMunicipioVo
 * @author Dherkyan Ribeiro da Silva
 */
@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_CRITERIO_MUNICIPIO, nomeAmigavel = 
      TabelasITC.NOME_AMIGAVEL_CRITERIO_MUNICIPIO, identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = 
               CamposCriterioMunicipio.CAMPO_CODIGO_CRITERIO_MUNICIPIO, sequencia = 
               @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_CRITERIO_MUNICIPIO, tipoSequencia = 
                     DomnTipoSequencia.SEQUENCE)
         )
         } )
public class CriterioMunicipioVo extends EntidadeVo<CriterioMunicipioVo>
{
   private static final long serialVersionUID = Long.MAX_VALUE;

   private double valorTotalMinimo;
   private double valorTotalMedio;
   private double valorTotalMaximo;
   private DomnStatusRegistro statusResgistroCriterioMunicipioVo;
   private Date dataAtualizacaoBD;
   private MunicipioIntegracaoVo municipio;

   public CriterioMunicipioVo()
   {
      super();
   }

   public CriterioMunicipioVo(long codigo)
   {
      super(codigo);
   }

   public CriterioMunicipioVo(CriterioMunicipioVo criterioMunicipioVo)
   {
      super();
      setParametroConsulta(criterioMunicipioVo);
   }

   public CriterioMunicipioPk getPk()
   {
      return new CriterioMunicipioPk(getCodigo());
   }

   public long getCodigo()
   {
      return super.getCodigo();
   }

   public void setValorTotalMinimo(double valorTotalMinimo)
   {
      this.valorTotalMinimo = valorTotalMinimo;
   }

   @AnotacaoAtributo(nomeColuna = CamposCriterioMunicipio.CAMPO_VALOR_TOTAL_MINIMO, obrigatorio = false)
   public double getValorTotalMinimo()
   {
      return valorTotalMinimo;
   }

   public void setValorTotalMedio(double valorTotalMedio)
   {
      this.valorTotalMedio = valorTotalMedio;
   }

   @AnotacaoAtributo(nomeColuna = CamposCriterioMunicipio.CAMPO_VALOR_TOTAL_MEDIO, obrigatorio = false)
   public double getValorTotalMedio()
   {
      return valorTotalMedio;
   }

   public void setValorTotalMaximo(double valorTotalMaximo)
   {
      this.valorTotalMaximo = valorTotalMaximo;
   }

   @AnotacaoAtributo(nomeColuna = CamposCriterioMunicipio.CAMPO_VALOR_TOTAL_MAXIMO, obrigatorio = false)
   public double getValorTotalMaximo()
   {
      return valorTotalMaximo;
   }

   public void setStatusResgistroCriterioMunicipioVo(DomnStatusRegistro statusResgistroCriterioMunicipioVo)
   {
      this.statusResgistroCriterioMunicipioVo = statusResgistroCriterioMunicipioVo;
   }

   @AnotacaoAtributo(nomeColuna = CamposCriterioMunicipio.CAMPO_STATUS_RESGISTRO, obrigatorio = false)
   public DomnStatusRegistro getStatusResgistroCriterioMunicipioVo()
   {
      if(!Validador.isDominioNumericoValido(this.statusResgistroCriterioMunicipioVo))
      {
         setStatusResgistroCriterioMunicipioVo(new DomnStatusRegistro(-1));
      }
      return statusResgistroCriterioMunicipioVo;
   }

   public void setDataAtualizacaoBD(Date dataAtualizacaoBD)
   {
      this.dataAtualizacaoBD = dataAtualizacaoBD;
   }

   @AnotacaoAtributo(nomeColuna = CamposCriterioMunicipio.CAMPO_DATA_ATUALIZACAO_BD, obrigatorio = false)
   public Date getDataAtualizacaoBD()
   {
      return dataAtualizacaoBD;
   }

   public void setMunicipio(MunicipioIntegracaoVo municipio)
   {
      this.municipio = municipio;
   }

   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposCriterioMunicipio.CAMPO_CODIGO_MUNICIPIO, nomeAtributo = "codgMunicipio")
            } )
   public MunicipioIntegracaoVo getMunicipio()
   {
      if (!Validador.isObjetoValido(this.municipio))
      {
         this.municipio = new MunicipioIntegracaoVo();
      }
      return municipio;
   }

   /**
    * Reorna o Valor Minimo Formatado
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public String getValorTotalMinimoFormatado()
   {
      String retorno = STRING_VAZIA;
      if (Validador.isNumericoValido(getValorTotalMinimo()))
      {
         return StringUtil.doubleToMonetario(getValorTotalMinimo());
      }
      return retorno;
   }
   
   /**
    * Reorna o Valor Minimo Formatado
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public String getValorTotalMedioFormatado()
   {
      String retorno = STRING_VAZIA;
      if (Validador.isNumericoValido(getValorTotalMedio()))
      {
         return StringUtil.doubleToMonetario(getValorTotalMedio());
      }
      return retorno;
   }
   
   /**
    * Reorna o Valor Minimo Formatado
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public String getValorTotalMaximoFormatado()
   {
      String retorno = STRING_VAZIA;
      if (Validador.isNumericoValido(getValorTotalMaximo()))
      {
         return StringUtil.doubleToMonetario(getValorTotalMaximo());
      }
      return retorno;
   }

}
