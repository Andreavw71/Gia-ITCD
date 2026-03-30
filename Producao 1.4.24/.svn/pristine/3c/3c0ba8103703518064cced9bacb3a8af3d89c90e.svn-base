package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.aliquota;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDSeparacaoDivorcioAliquota;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Classe responsável por encapsular os valores do objeto Separação Aliquota (Value Object)
 * 
 * @author Dherkyan Ribeiro
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_GIA_ITCD_SEPARACAO_ALIQUOTA
     ,nomeAmigavel = "GIA-ITCD Separação - Alíquota"
     ,identificadores =
     {
         @AnotacaoIdentificador
         (
             nomeAtributo = "codigoAliquota"
             ,nomeColuna = CamposGIAITCDSeparacaoDivorcioAliquota.CAMPO_CODIGO_ALIQUOTA
         )
         ,@AnotacaoIdentificador
         (
             nomeAtributo = "giaItcdSeparacaoDivorcioVo.codigo"
             ,nomeColuna =  CamposGIAITCDSeparacaoDivorcioAliquota.CAMPO_CODIGO_GIA_ITCD
         )
     }
 )
public class GIAITCDSeparacaoDivorcioAliquotaVo extends EntidadeVo<GIAITCDSeparacaoDivorcioAliquotaVo>
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   private GIAITCDSeparacaoDivorcioVo giaItcdSeparacaoDivorcioVo;
   private long codigoAliquota;
   private double valorBaseCalculo;
   private double percentualAliquota;
   private double valorRecolher;
   
   /**
    * Contrutor padrão vazio
    * 
    * @implemented by Dherkyan Ribeiro
    */
   public GIAITCDSeparacaoDivorcioAliquotaVo()
   {
      super();
   }

   /**
    * Contrutor publico recebendo um código
    * 
    * @implemented by Dherkyan Ribeiro
    */
   public GIAITCDSeparacaoDivorcioAliquotaVo(long codigo)
   {
      super(codigo);
   }

   /**
    * Contrutor recebendo paramêtros para consula
    * 
    * @implemented by Dherkyan Ribeiro
    */
   public GIAITCDSeparacaoDivorcioAliquotaVo(GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo)
   {
      super();
      setParametroConsulta(giaItcdSeparacaoDivorcioAliquotaVo);
   }

   /**
    * Contrutor recebendo collVO
    * 
    * @param collVO
    * @implemented by Dherkyan Ribeiro
    */
   public GIAITCDSeparacaoDivorcioAliquotaVo(Collection collVO)
   {
      super(collVO);
   }

   /**
    * 
    * @param giaItcdSeparacaoDivorcioVo
    */
   public void setGiaItcdSeparacaoDivorcioVo(GIAITCDSeparacaoDivorcioVo giaItcdSeparacaoDivorcioVo)
   {
      this.giaItcdSeparacaoDivorcioVo = giaItcdSeparacaoDivorcioVo;
   }

   public GIAITCDSeparacaoDivorcioVo getGiaItcdSeparacaoDivorcioVo()
   {
      if(!Validador.isObjetoValido(this.giaItcdSeparacaoDivorcioVo))
      {
         setGiaItcdSeparacaoDivorcioVo(new GIAITCDSeparacaoDivorcioVo());
      }
      return this.giaItcdSeparacaoDivorcioVo;
   }

   public void setCodigoAliquota(long codigoAliquota)
   {
      this.codigoAliquota = codigoAliquota;
   }

   public long getCodigoAliquota()
   {
      return codigoAliquota;
   }

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
        nomeColuna = CamposGIAITCDSeparacaoDivorcioAliquota.CAMPO_BASE_CALCULO
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
        nomeColuna = CamposGIAITCDSeparacaoDivorcioAliquota.CAMPO_PERCENTUAL_ALIQUOTA
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
        nomeColuna = CamposGIAITCDSeparacaoDivorcioAliquota.CAMPO_VALOR_RECOLHER
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
      GIAITCDSeparacaoDivorcioAliquotaVo objeto = (GIAITCDSeparacaoDivorcioAliquotaVo) entidade;
      int codigo = new Long(this.getCodigoAliquota()).compareTo(new Long(objeto.getCodigoAliquota()));
      if(codigo != 0)
      {
         return codigo;
      }
      else
      {
         return this.getGiaItcdSeparacaoDivorcioVo().compareTo(objeto.getGiaItcdSeparacaoDivorcioVo());
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
      hash += getGiaItcdSeparacaoDivorcioVo().hashCode();
      return hash;      
   }
   
   /**
    * 
    * @return
    */
   public List<GIAITCDSeparacaoDivorcioAliquotaVo> getListVo()
   {
     return new ArrayList(super.getCollVO());
   }
}
