package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.aliquota;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDDoacaoBeneficiarioAliquota;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Classe responsável por encapsular os valores do objeto Iventário Arrolamento Beneficiario Aliquota (Value Object)
 * 
 * @author Dherkyan Ribeiro
 */
  @AnotacaoTabelaClasse
  (
      nomeTabela = TabelasITC.TABELA_GIA_ITCD_DOACAO_ALIQUOTA
      ,nomeAmigavel = "GIA-ITCD Doação Beneficiário - Alíquota"
      ,identificadores =
      {
          @AnotacaoIdentificador
          (
              nomeAtributo = "codigoAliquota"
              ,nomeColuna = CamposGIAITCDDoacaoBeneficiarioAliquota.CAMPO_CODIGO_ALIQUOTA
          )
          ,@AnotacaoIdentificador
          (
              nomeAtributo = "giaItcdDoacaoBeneficiarioVo.codigo"
              ,nomeColuna =  CamposGIAITCDDoacaoBeneficiarioAliquota.CAMPO_CODIGO_BENEFICIARIO
          )
      }
  )
public class GIAITCDDoacaoBeneficiarioAliquotaVo extends EntidadeVo
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   private GIAITCDDoacaoBeneficiarioVo giaItcdDoacaoBeneficiarioVo;
   private long codigoAliquota;
   private double valorBaseCalculo;
   private double percentualAliquota;
   private double valorRecolher;
   private double valorBaseCalcDoacaoSucessivaAnterior;
   private double valorItcdDoacaoSucessivaAnterior;
   private double numrUpfUtilizada;
   private double numrUpfDisponivel;
   private int infoUpfRefGiaAntr;
   
   /**
    * Contrutor padrão vazio
    * 
    * @implemented by Dherkyan Ribeiro
    */
   public GIAITCDDoacaoBeneficiarioAliquotaVo()
   {
      super();
   }

   /**
    * Contrutor publico recebendo um código
    * 
    * @implemented by Dherkyan Ribeiro
    */
   public GIAITCDDoacaoBeneficiarioAliquotaVo(long codigo)
   {
      super(codigo);
   }

   /**
    * Contrutor recebendo paramêtros para consula
    * 
    * @implemented by Dherkyan Ribeiro
    */
   public GIAITCDDoacaoBeneficiarioAliquotaVo(GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo)
   {
      super();
      setParametroConsulta(giaItcdDoacaoBeneficiarioAliquotaVo);
   }

   /**
    * Contrutor recebendo collVO
    * 
    * @param collVO
    * @implemented by Dherkyan Ribeiro
    */
   public GIAITCDDoacaoBeneficiarioAliquotaVo(Collection collVO)
   {
      super(collVO);
   }

   /**
    * 
    * @param giaItcdDoacaoBeneficiarioVo
    */
   public void setGiaItcdDoacaoBeneficiarioVo(GIAITCDDoacaoBeneficiarioVo giaItcdDoacaoBeneficiarioVo)
   {
      this.giaItcdDoacaoBeneficiarioVo = giaItcdDoacaoBeneficiarioVo;
   }

   /**
    * 
    * @return
    */
   public GIAITCDDoacaoBeneficiarioVo getGiaItcdDoacaoBeneficiarioVo()
   {
      if (!Validador.isObjetoValido(this.giaItcdDoacaoBeneficiarioVo))
      {
         setGiaItcdDoacaoBeneficiarioVo(new GIAITCDDoacaoBeneficiarioVo());
      }
      return giaItcdDoacaoBeneficiarioVo;
   }

   /**
    * 
    * @param codigoAliquota
    */
   public void setCodigoAliquota(long codigoAliquota)
   {
      this.codigoAliquota = codigoAliquota;
   }

   /**
    * 
    * @return
    */
   public long getCodigoAliquota()
   {
      return codigoAliquota;
   }

   /**
    * 
    * @param valorBaseCalculo
    */
   public void setValorBaseCalculo(double valorBaseCalculo)
   {
      this.valorBaseCalculo = valorBaseCalculo;
   }

   /**
    * Retorna a base de calculo
    * @return
    * @implemented by Dherkyan Ribeiro
    */
    @AnotacaoAtributo
    (
        nomeColuna = CamposGIAITCDDoacaoBeneficiarioAliquota.CAMPO_BASE_CALCULO
        ,obrigatorio = false
    )
   public double getValorBaseCalculo()
   {
      return valorBaseCalculo;
   }

   /**
    * Retorna o valor formatado para a Base de calculo
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public String getValorBaseCalculoFormatado()
   {
      return StringUtil.doubleToMonetario(getValorBaseCalculo());
   }

   /**
    * 
    * @param percentualAliquota
    */
   public void setPercentualAliquota(double percentualAliquota)
   {
      this.percentualAliquota = percentualAliquota;
   }

   /**
    * Retorna o Percentual da Aliquota
    * @return
    * @implemented by Dherkyan Ribeiro
    */
    @AnotacaoAtributo
    (
        nomeColuna = CamposGIAITCDDoacaoBeneficiarioAliquota.CAMPO_PERCENTUAL_ALIQUOTA
        ,obrigatorio = false
    )
   public double getPercentualAliquota()
   {
      return percentualAliquota;
   }

   /**
    * Retorna o Percentual da Aliquota Formatado
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public String getPercentualAliquotaFormatado()
   {
      return StringUtil.doubleToMonetario(getPercentualAliquota());
   }

   /**
    * 
    * @param valorRecolher
    */
   public void setValorRecolher(double valorRecolher)
   {
      this.valorRecolher = valorRecolher;
   }

   /**
    * Retorna o Valor a Recolher
    * 
    * @return
    * @implemented by Dherkyan Ribeiro
    */
    @AnotacaoAtributo
    (
        nomeColuna = CamposGIAITCDDoacaoBeneficiarioAliquota.CAMPO_VALOR_RECOLHER
        ,obrigatorio = false
    )
   public double getValorRecolher()
   {
      return valorRecolher;
   }
   
   /**
    * 
    * @return
    */
   public String getValorRecolherFormatado()
   {
      return StringUtil.doubleToMonetario(getValorRecolher());
   }
   
   /**
    * 
    * @param entidade
    * @return
    */
   public int compareTo(Object entidade)
   {
      GIAITCDDoacaoBeneficiarioAliquotaVo objeto = (GIAITCDDoacaoBeneficiarioAliquotaVo) entidade;
      int codigo = new Long(this.getCodigoAliquota()).compareTo(new Long(objeto.getCodigoAliquota()));
      if(codigo != 0)
      {
         return codigo;
      }
      else
      {
         return this.getGiaItcdDoacaoBeneficiarioVo().compareTo(objeto.getGiaItcdDoacaoBeneficiarioVo());
      }
   }
   
   /**
    * 
    * @return
    */
   public int hashCode()
   {
      int hash = 0;
      hash += new Long(codigoAliquota).intValue() * MULTIPLICADOR_HASH_CODE;
      hash += getGiaItcdDoacaoBeneficiarioVo().hashCode();
      return hash;      
   }
   
   /**
    * 
    * @return
    */
   public List<GIAITCDDoacaoBeneficiarioAliquotaVo> getListVo()
   {
     return new ArrayList(super.getCollVO());
   }
   
   public void setValorBaseCalcDoacaoSucessivaAnterior(double valorBaseCalcDoacaoSucessivaAnterior)
   {
      this.valorBaseCalcDoacaoSucessivaAnterior = valorBaseCalcDoacaoSucessivaAnterior;
   }

   public double getValorBaseCalcDoacaoSucessivaAnterior()
   {
      return valorBaseCalcDoacaoSucessivaAnterior;
   }

   public void setValorItcdDoacaoSucessivaAnterior(double valorItcdDoacaoSucessivaAnterior)
   {
      this.valorItcdDoacaoSucessivaAnterior = valorItcdDoacaoSucessivaAnterior;
   }

   public double getValorItcdDoacaoSucessivaAnterior()
   {
      return valorItcdDoacaoSucessivaAnterior;
   }


   public void setNumrUpfUtilizada(double numrUpfUtilizada)
   {
      this.numrUpfUtilizada = numrUpfUtilizada;
   }

   @AnotacaoAtributo
   (
       nomeColuna = CamposGIAITCDDoacaoBeneficiarioAliquota.CAMPO_NUMR_UPF_UTLZ
       ,obrigatorio = false
   )
   public double getNumrUpfUtilizada()
   {
      return numrUpfUtilizada;
   }

   public void setNumrUpfDisponivel(double numrUpfDisponivel)
   {
      this.numrUpfDisponivel = numrUpfDisponivel;
   }

   @AnotacaoAtributo
   (
       nomeColuna = CamposGIAITCDDoacaoBeneficiarioAliquota.CAMPO_NUMR_UPF_DISP
       ,obrigatorio = false
   )
   public double getNumrUpfDisponivel()
   {
      return numrUpfDisponivel;
   }


   public void setInfoUpfRefGiaAntr(int infoUpfRefGiaAntr)
   {
      this.infoUpfRefGiaAntr = infoUpfRefGiaAntr;
   }

   @AnotacaoAtributo
   (
       nomeColuna = CamposGIAITCDDoacaoBeneficiarioAliquota.CAMPO_INFO_UPF_REFR_GIA_ANTR
       ,obrigatorio = false
   )
   public int getInfoUpfRefGiaAntr(){
      return infoUpfRefGiaAntr;
   }
}
