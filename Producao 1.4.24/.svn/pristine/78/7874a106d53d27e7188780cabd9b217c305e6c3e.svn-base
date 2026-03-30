package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.aliquota.GIAITCDDoacaoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaVo;
import br.gov.mt.sefaz.itc.util.NumeroUtil;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDDoacaoBeneficiario;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


/**
 * Entidade de GIA-ITCD Doação Beneficiário
 * @author Daniel Balieiro
 * @implemented by Daniel Balieiro
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_GIA_ITCD_DOACAO_BENEFICIARIO
     ,nomeAmigavel = "GIA-ITCD Doação - Beneficiário"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposGIAITCDDoacaoBeneficiario.CAMPO_ITCTB05_CODIGO_BENEFICIARIO
			)
     }
 )
public class GIAITCDDoacaoBeneficiarioVo extends BeneficiarioVo
{
	private double percentualAliquota;
	private double percentualBemRecebido;
	private double valorRecolher;
   private GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo;   
   private Integer infoIsencao;
   private Integer infoDispensaRecolhimento;
   private GIAITCDDoacaoSucessivaVo giaitcdDoacaoSucessivaVo;
   
	private static final long serialVersionUID = Long.MAX_VALUE;

	/**
	 * Construtor Padrão
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBeneficiarioVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a chave primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBeneficiarioVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe o parametro de consulta
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBeneficiarioVo(GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo)
	{
		super();
		setParametroConsulta(giaITCDDoacaoBeneficiarioVo);
	}

	/**
	 * Método que recebe uma collvo
	 * @param collVO
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBeneficiarioVo(Collection collVO)
	{
		super(collVO);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui o Percentual de Aliquota
	 * @param percentualAliquota
	 * @implemented by Daniel Balieiro
	 */
	public void setPercentualAliquota(double percentualAliquota)
	{
		this.percentualAliquota = percentualAliquota;
	}

	/**
	 * Retorna o Percentual Aliquota
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDDoacaoBeneficiario.CAMPO_PERCENTUAL_ALIQUOTA
	     ,obrigatorio = false
	 )
	public double getPercentualAliquota()
	{
		return NumeroUtil.arredondaNumero(percentualAliquota, 2);
	}

	/**
	 * Retorna o Percentual Aliquota Formatado
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public String getPercentualAliquotaFormatado()
	{
		return StringUtil.doubleToMonetario(getPercentualAliquota());
	}

	/**
	 * Atribui o Percentual do Bem Recebido
	 * @param percentualBemRecebido
	 * @implemented by Daniel Balieiro
	 */
	public void setPercentualBemRecebido(double percentualBemRecebido)
	{
		this.percentualBemRecebido = percentualBemRecebido;
	}
	
	/**
	 * Retorna o Percentual de Bem Recebido
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna =CamposGIAITCDDoacaoBeneficiario.CAMPO_PERCENTUAL_BEM_RECEBIDO
	     ,obrigatorio = true
	 )
	public double getPercentualBemRecebido()
	{
		return NumeroUtil.arredondaNumero(percentualBemRecebido, getQuantidadeCasasDecimaisPercentualRecebido());
	}

	/**
	 * Retorna o Percentual Bem Recebido Formatado
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public String getPercentualBemRecebidoFormatado()
	{
		return StringUtil.doubleToMonetario(getPercentualBemRecebido(),getQuantidadeCasasDecimaisPercentualRecebido());
	}

	/**
	 * Atribui o Valor a Recolher
	 * @param valorRecolher
	 * @implemented by Daniel Balieiro
	 */
	public void setValorRecolher(double valorRecolher)
	{
		this.valorRecolher = valorRecolher;
	}

	/**
	 * Retorna o Valor a Recolher
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDDoacaoBeneficiario.CAMPO_VALOR_RECOLHER
	     ,obrigatorio = false
	 )
	public double getValorRecolher()
	{
		return NumeroUtil.arredondaNumero(valorRecolher, 2);
	}

	/**
	 * Retorna o Valor a Recolher Formatado
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public String getValorRecolherFormatado()
	{
		return StringUtil.doubleToMonetario(getValorRecolher(), 2);
	}

	/**
	 * Retorna o Valor Recebido Calculado
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public double getValorRecebidoCalculado()
	{
		if (Validador.isObjetoValido(getGiaITCDVo()) && (getGiaITCDVo() instanceof GIAITCDDoacaoVo))
		{
			setValorRecebido((this.getGiaITCDVo().getValorTotalBensDeclarados() * getPercentualBemRecebido() / 100) * 
							((GIAITCDDoacaoVo) this.getGiaITCDVo()).getFracaoIdeal() / 100);
		}
		return getValorRecebido();
	}

	/**
	 * Retorna o Valor Recebido Calculado Formatado
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public String getValorRecebidoCalculadoFormatado()
	{
		if (!Validador.isNumericoValido(getValorRecebidoCalculado()))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(getValorRecebidoCalculado());
	}
	
	public int getQuantidadeCasasDecimaisPercentualRecebido()
	{
		return 4;
	}


   /**
    * 
    * @param giaItcdDDoacaoBeneficiarioAliquotaVo
    */
   public void setGiaItcdDoacaoBeneficiarioAliquotaVo(GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDDoacaoBeneficiarioAliquotaVo)
   {
      this.giaItcdDoacaoBeneficiarioAliquotaVo = giaItcdDDoacaoBeneficiarioAliquotaVo;
   }

   /**
    * 
    * @return GIAITCDDoacaoBeneficiarioAliquotaVo;
    */
   public GIAITCDDoacaoBeneficiarioAliquotaVo getGiaItcdDoacaoBeneficiarioAliquotaVo()
   {
      if(!Validador.isObjetoValido(giaItcdDoacaoBeneficiarioAliquotaVo))
      {
         setGiaItcdDoacaoBeneficiarioAliquotaVo(new GIAITCDDoacaoBeneficiarioAliquotaVo());
      }
      return giaItcdDoacaoBeneficiarioAliquotaVo;
   }
   
   public void setGiaitcdDoacaoSucessivaVo(GIAITCDDoacaoSucessivaVo giaitcdDoacaoSucessivaVo)
   {
      this.giaitcdDoacaoSucessivaVo = giaitcdDoacaoSucessivaVo;
   }
   
   public GIAITCDDoacaoSucessivaVo getGiaitcdDoacaoSucessivaVo(){
      if(!Validador.isObjetoValido(giaitcdDoacaoSucessivaVo))
      {
         setGiaitcdDoacaoSucessivaVo(new GIAITCDDoacaoSucessivaVo());
      }
      return giaitcdDoacaoSucessivaVo;
   }


   public void setInfoIsencao(int infoIsencao)
   {
      this.infoIsencao = infoIsencao;
   }

   @AnotacaoAtributo
   (
       nomeColuna = CamposGIAITCDDoacaoBeneficiario.CAMPO_INFO_ISENCAO
       ,obrigatorio = false
   )
   public Integer getInfoIsencao(){
      return infoIsencao;
   }

   public void setInfoDispensaRecolhimento(Integer infoDispensaRecolhimento){
      this.infoDispensaRecolhimento = infoDispensaRecolhimento;
   }

   @AnotacaoAtributo
   (
       nomeColuna = CamposGIAITCDDoacaoBeneficiario.CAMPO_INFO_DSPE_RCLH
       ,obrigatorio = false
   )
   public Integer getInfoDispensaRecolhimento(){   
      return infoDispensaRecolhimento;
   }
}
