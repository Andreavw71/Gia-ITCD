package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.GIAITCDInventarioArrolamentoBeneficiarioVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Classe responsável por encapsular os valores do objeto Iventário Arrolamento Beneficiario Aliquota (Value Object)
 * 
 * @author Lucas Nascimento
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_GIA_ITCD_IVENTARIO_BENEFICIARIO_ALIQUOTA
     ,nomeAmigavel = "GIA-ITCD Beneficiário - Alíquota"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigoAliquota"
			    ,nomeColuna = CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.CAMPO_CODIGO_ALIQUOTA
			)
			,@AnotacaoIdentificador
			(
			    nomeAtributo = "giaITCDIventarioArrolamentoBeneficiarioVo.codigo"
			    ,nomeColuna =  CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.CAMPO_CODIGO_BENEFICIARIO
			)
     }
 )
public class GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDIventarioArrolamentoBeneficiarioVo;
	private long codigoAliquota;
	private double valorBaseCalculo;
	private double percentualAliquota;
	private double valorRecolher;

	/**
	 * Contrutor padrão vazio
	 * 
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo()
	{
		super();
	}

	/**
	 * Contrutor publico recebendo um código
	 * 
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Contrutor recebendo paramêtros para consula
	 * 
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo(GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo)
	{
		super();
		setParametroConsulta(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
	}

	/**
	 * Contrutor recebendo collVO
	 * 
	 * @param collVO
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo(Collection collVO)
	{
		super(collVO);
	}

	/**
	 * Atribui o Beneficiario do Iventario
	 * 
	 * @param giaITCDIventarioArrolamentoBeneficiarioVo
	 * @implemented by Lucas Nascimento
	 */
	public void setGiaITCDIventarioArrolamentoBeneficiarioVo(GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDIventarioArrolamentoBeneficiarioVo)
	{
		this.giaITCDIventarioArrolamentoBeneficiarioVo = giaITCDIventarioArrolamentoBeneficiarioVo;
	}

	/**
	 * Retorna o Beneficiario do Iventario
	 * 
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioVo getGiaITCDIventarioArrolamentoBeneficiarioVo()
	{
		if (!Validador.isObjetoValido(giaITCDIventarioArrolamentoBeneficiarioVo))
		{
			setGiaITCDIventarioArrolamentoBeneficiarioVo(new GIAITCDInventarioArrolamentoBeneficiarioVo());
		}
		return this.giaITCDIventarioArrolamentoBeneficiarioVo;
	}

	/**
	 * Atribuindo o Código da Aliquota
	 * 
	 * @param codigoAliquota
	 * @implemented by Lucas Nascimento
	 */
	public void setCodigoAliquota(long codigoAliquota)
	{
		this.codigoAliquota = codigoAliquota;
	}

	/**
	 * Retorna Código da Aliquota
	 * 
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public long getCodigoAliquota()
	{
		return codigoAliquota;
	}

	/**
	 * Atribui o Percentual da Aliquota
	 * 
	 * @param percentualAliquota
	 * @implemented by Lucas Nascimento
	 */
	public void setPercentualAliquota(double percentualAliquota)
	{
		this.percentualAliquota = percentualAliquota;
	}

	/**
	 * Retorna o Percentual da Aliquota
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.CAMPO_PERCENTUAL_ALIQUOTA
	     ,obrigatorio = false
	 )
	public double getPercentualAliquota()
	{
		return percentualAliquota;
	}

	/**
	 * Retorna o Percentual da Aliquota Formatado
	 * @return
	 * @implemented by João Batista Padilha e Silva
	 */
	public String getPercentualAliquotaFormatado()
	{
		return StringUtil.doubleToMonetario(getPercentualAliquota());
	}

	/**
	 * Atribui o Valor a Recolher
	 * @param valorRecolher
	 * @implemented by Lucas Nascimento
	 */
	public void setValorRecolher(double valorRecolher)
	{
		this.valorRecolher = valorRecolher;
	}

	/**
	 * Retorna o Valor a Recolher
	 * 
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.CAMPO_VALOR_RECOLHER
	     ,obrigatorio = false
	 )
	public double getValorRecolher()
	{
		return valorRecolher;
	}

	public String getValorRecolherFormatado()
	{
		return StringUtil.doubleToMonetario(getValorRecolher());
	}

	/**
	 * Atribui o Valor da Base de Cálculo
	 * @param valorBaseCalculo
	 * @implemented by Lucas Nascimento
	 */
	public void setValorBaseCalculo(double valorBaseCalculo)
	{
		this.valorBaseCalculo = valorBaseCalculo;
	}

	/**
	 * Retorna a base de calculo
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.CAMPO_BASE_CALCULO
	     ,obrigatorio = false
	 )
	public double getValorBaseCalculo()
	{
		return valorBaseCalculo;
	}

	/**
	 * Retorna o valor formatado para a Base de calculo
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public String getValorBaseCalculoFormatado()
	{
		return StringUtil.doubleToMonetario(getValorBaseCalculo());
	}
	
	public int hashCode()
	{
		int hash = 0;
		hash += new Long(codigoAliquota).intValue() * MULTIPLICADOR_HASH_CODE;
		hash += getGiaITCDIventarioArrolamentoBeneficiarioVo().hashCode();
		return hash;		
	}
	
	public int compareTo(Object entidade)
	{
		GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo objeto = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) entidade;
		int codigo = new Long(this.getCodigoAliquota()).compareTo(new Long(objeto.getCodigoAliquota()));
		if(codigo != 0)
		{
			return codigo;
		}
		else
		{
			return this.getGiaITCDIventarioArrolamentoBeneficiarioVo().compareTo(objeto.getGiaITCDIventarioArrolamentoBeneficiarioVo());
		}
	}
   
   public List<GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo> getListVo()
   {
     return new ArrayList(super.getCollVO());
   }
   
}
