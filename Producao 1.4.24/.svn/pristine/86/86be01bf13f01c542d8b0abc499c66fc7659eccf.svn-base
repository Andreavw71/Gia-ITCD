/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: IPTUVo.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data criação : 07/10/2015
 * Data ultima revisão : 07/10/2015
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.iptu;

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
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoIPTU;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposITPTU;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;

import java.util.Date;


@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_IPTU, nomeAmigavel = TabelasITC.NOME_AMIGAVEL_IPTU, identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = CamposITPTU.CAMPO_CODIGO_IPTU, sequencia = 
               @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_IPTU, tipoSequencia = DomnTipoSequencia.SEQUENCE)
         )
         } )
public class IPTUVo extends EntidadeVo<IPTUVo>
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   private MunicipioIntegracaoVo municipio;
   private DomnTipoIPTU tipoITPU;
   private double valorPercentualEstimado;
   private DomnAtivoInativo statusResgistroIPTU;
   private double valorMetroTerritorial;
   private double valorMetroPredial;
   private Date dataAtualizacaoBD;

   public IPTUVo()
   {
      super();
   }

   public IPTUVo(long codigo)
   {
      super(codigo);
   }

   public IPTUVo(IPTUVo iptuvo)
   {
      super();
      setParametroConsulta(iptuvo);
   }

   public IPTUVo getPk()
   {
      return new IPTUVo(getCodigo());
   }

   public long getCodigo()
   {
      return super.getCodigo();
   }

   public void setMunicipio(MunicipioIntegracaoVo municipio)
   {
      this.municipio = municipio;
   }

   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposITPTU.CAMPO_CODIGO_MUNICIPIO, nomeAtributo = "codgMunicipio")
            } )
   public MunicipioIntegracaoVo getMunicipio()
   {
      if (!Validador.isObjetoValido(this.municipio))
      {
         this.municipio = new MunicipioIntegracaoVo();
      }
      return municipio;
   }

   public void setTipoITPU(DomnTipoIPTU tipoITPU)
   {
      this.tipoITPU = tipoITPU;
   }

   @AnotacaoAtributo(nomeColuna = CamposITPTU.CAMPO_TIPO_IPTU, obrigatorio = false)
   public DomnTipoIPTU getTipoITPU()
   {
      if (Validador.isDominioNumericoValido(this.tipoITPU))
      {
         return this.tipoITPU;
      }
      else
      {
         setTipoITPU(new DomnTipoIPTU(-1));
         return this.tipoITPU;
      }
   }

   public void setValorPercentualEstimado(double valorEstimado)
   {
      this.valorPercentualEstimado = valorEstimado;
   }

   @AnotacaoAtributo(nomeColuna = CamposITPTU.CAMPO_VALOR_PERCENTUAL_ESTIMADO, obrigatorio = false)
   public double getValorPercentualEstimado()
   {
      return valorPercentualEstimado;
   }

   public void setStatusResgistroIPTU(DomnAtivoInativo statusResgistroIPTU)
   {
      this.statusResgistroIPTU = statusResgistroIPTU;
   }

   @AnotacaoAtributo(nomeColuna = CamposITPTU.CAMPO_STATUS_REGISTRO_IPTU, obrigatorio = false)
   public DomnAtivoInativo getStatusResgistroIPTU()
   {
      return statusResgistroIPTU;
   }

   public void setValorMetroTerritorial(double valorMetroTerritorial)
   {
      this.valorMetroTerritorial = valorMetroTerritorial;
   }

   @AnotacaoAtributo(nomeColuna = CamposITPTU.CAMPO_VALOR_METRO_TERRITORIAL, obrigatorio = false)
   public double getValorMetroTerritorial()
   {
      return valorMetroTerritorial;
   }

   public void setValorMetroPredial(double valorMetroPredial)
   {
      this.valorMetroPredial = valorMetroPredial;
   }

   @AnotacaoAtributo(nomeColuna = CamposITPTU.CAMPO_VALOR_METRO_PREDIAL, obrigatorio = false)
   public double getValorMetroPredial()
   {
      return valorMetroPredial;
   }

   public void setDataAtualizacaoBD(Date dataAtualizacaoBD)
   {
      this.dataAtualizacaoBD = dataAtualizacaoBD;
   }

   @AnotacaoAtributo(nomeColuna = CamposITPTU.CAMPO_DATA_ATUALIZACAO_BD, obrigatorio = false)
   public Date getDataAtualizacaoBD()
   {
      return dataAtualizacaoBD;
   }

   /**
    * Reorna o Valor Percentual Estimado
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public String getValorPercentualEstimadoFormatado()
   {
      final double vpEstimado = getValorPercentualEstimado();
      if (!Validador.isNumericoValido(vpEstimado))
      {
         return STRING_VAZIA;
      }
      return StringUtil.doubleToMonetario(vpEstimado, 4);
   }

   /**
    * Reorna o Valor Percentual Estimado
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public String getValorMetroTerritorialFormatado()
   {
      String retorno = STRING_VAZIA;
      if (Validador.isNumericoValido(getValorMetroTerritorial()))
      {
         return StringUtil.doubleToMonetario(getValorMetroTerritorial());
      }
      return retorno;
   }

   /**
    * Reorna o Valor Percentual Estimado
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public String getValorMetroPredialFormatado()
   {
      String retorno = STRING_VAZIA;
      if (Validador.isNumericoValido(getValorMetroPredial()))
      {
         return StringUtil.doubleToMonetario(getValorMetroPredial());
      }
      return retorno;
   }

}
