package br.gov.mt.sefaz.itc.model.tabelabasica.distancia;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDistancia;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposDistancia;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Date;


/**
 * 
 * Classe Responsável por encapsular os valores do objeto Distancia
 * @author Dherkyan Ribeiro da Silva
 */
@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_DISTANCIA, nomeAmigavel = 
      TabelasITC.NOME_AMIGAVEL_DISTANCIA, identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = CamposDistancia.CAMPO_CODIGO_DISTANCIA, sequencia = 
               @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_DISTANCIA, tipoSequencia = DomnTipoSequencia.SEQUENCE)
         )
         } )
public class DistanciaVo extends EntidadeVo<DistanciaVo>
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   private int distanciaInicialPerimetroUrbano;
   private int distanciaFinalPerimetroUrbano;
   private int distanciaInicialRodoviaPavimentada;
   private int distanciaFinalRodoviaPavimentada;
   private DomnTipoDistancia tipoDistancia;
   private DomnStatusRegistro statusResgistroDistancia;
   private Date dataAtualizacaoBD;
   private transient boolean isMaiorDistanciaFinalPerimetroUrbanoDaFaixa;
   private transient boolean isMaiorDistanciaFinalPerimetroUrbano;

   public DistanciaVo()
   {
      super();
   }

   public DistanciaVo(long codigo)
   {
      super(codigo);
   }

   public DistanciaVo(DistanciaVo distanciaVo)
   {
      super();
      setParametroConsulta(distanciaVo);
   }

   public DistanciaPk getPk()
   {
      return new DistanciaPk(getCodigo());
   }

   public long getCodigo()
   {
      return super.getCodigo();
   }

   public void setDistanciaInicialPerimetroUrbano(int distanciaInicialPerimetroUrbano)
   {
      this.distanciaInicialPerimetroUrbano = distanciaInicialPerimetroUrbano;
   }

   @AnotacaoAtributo(nomeColuna = CamposDistancia.CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO, obrigatorio = false)
   public int getDistanciaInicialPerimetroUrbano()
   {
      return distanciaInicialPerimetroUrbano;
   }

   public void setDistanciaFinalPerimetroUrbano(int distanciaFinalPerimetroUrbano)
   {
      this.distanciaFinalPerimetroUrbano = distanciaFinalPerimetroUrbano;
   }

   @AnotacaoAtributo(nomeColuna = CamposDistancia.CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO, obrigatorio = false)
   public int getDistanciaFinalPerimetroUrbano()
   {
      return distanciaFinalPerimetroUrbano;
   }
   
   public String getDistanciaFinalPerimetroUrbanoFormatada()
   {
      return Validador.isNumericoValido(this.distanciaFinalPerimetroUrbano) ? String.valueOf(this.distanciaFinalPerimetroUrbano) :STRING_VAZIA;
   }
   
   public void setDistanciaInicialRodoviaPavimentada(int distanciaInicialRodoviaPavimentada)
   {
      this.distanciaInicialRodoviaPavimentada = distanciaInicialRodoviaPavimentada;
   }

   @AnotacaoAtributo(nomeColuna = CamposDistancia.CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA, obrigatorio = false)
   public int getDistanciaInicialRodoviaPavimentada()
   {
      return distanciaInicialRodoviaPavimentada;
   }

   public void setDistanciaFinalRodoviaPavimentada(int distanciaFinalRodoviaPavimentada)
   {
      this.distanciaFinalRodoviaPavimentada = distanciaFinalRodoviaPavimentada;
   }

   @AnotacaoAtributo(nomeColuna = CamposDistancia.CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA, obrigatorio = false)
   public int getDistanciaFinalRodoviaPavimentada()
   {
      return distanciaFinalRodoviaPavimentada;
   }
   
   public String getDistanciaFinalRodoviaPavimentadaFormatada()
   {
      return Validador.isNumericoValido(this.distanciaFinalRodoviaPavimentada) ? String.valueOf(this.distanciaFinalRodoviaPavimentada) :STRING_VAZIA;
   }

   public void setDataAtualizacaoBD(Date dataAtualizacaoBD)
   {
      this.dataAtualizacaoBD = dataAtualizacaoBD;
   }

   @AnotacaoAtributo(nomeColuna = CamposDistancia.CAMPO_DATA_ATUALIZACAO_BD, obrigatorio = false)
   public Date getDataAtualizacaoBD()
   {
      return dataAtualizacaoBD;
   }

   public void setTipoDistancia(DomnTipoDistancia tipoDistancia)
   {
      this.tipoDistancia = tipoDistancia;
   }

   @AnotacaoAtributo(nomeColuna = CamposDistancia.CAMPO_TIPO_DISTANCIA, obrigatorio = false)
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

   public void setStatusResgistroDistancia(DomnStatusRegistro statusResgistroDistancia)
   {
      this.statusResgistroDistancia = statusResgistroDistancia;
   }

   @AnotacaoAtributo(nomeColuna = CamposDistancia.CAMPO_STATUS_REGISTRO_DISTANCIA, obrigatorio = false)
   public DomnStatusRegistro getStatusResgistroDistancia()
   {
      if (Validador.isObjetoValido(this.statusResgistroDistancia))
      {
         return this.statusResgistroDistancia;
      }
      else
      {
         setStatusResgistroDistancia(new DomnStatusRegistro(DomnAtivoInativo.ATIVO));
         return this.statusResgistroDistancia;
      }
   }

   public DistanciaVo clone()
   {
      try
      {
         return (DistanciaVo) super.clone();
      }
      catch (CloneNotSupportedException e)
      {
         System.out.println("Cloning not allowed.");
         return this;
      }
   }

   public void setIsMaiorDistanciaFinalPerimetroUrbanoDaFaixa(boolean isMaiorDistanciaFinalPerimetroUrbanoDaFaixa)
   {
      this.isMaiorDistanciaFinalPerimetroUrbanoDaFaixa = isMaiorDistanciaFinalPerimetroUrbanoDaFaixa;
   }

   public boolean isIsMaiorDistanciaFinalPerimetroUrbanoDaFaixa()
   {
      return isMaiorDistanciaFinalPerimetroUrbanoDaFaixa;
   }

   public void setIsMaiorDistanciaFinalPerimetroUrbano(boolean isMaiorDistanciaFinalPerimetroUrbano)
   {
      this.isMaiorDistanciaFinalPerimetroUrbano = isMaiorDistanciaFinalPerimetroUrbano;
   }

   public boolean isIsMaiorDistanciaFinalPerimetroUrbano()
   {
      return isMaiorDistanciaFinalPerimetroUrbano;
   }
}
