/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDInventarioArrolamentoBeneficiarioVo.java
 * Revisão:
 * Data revisão:
 * $Id: GIAITCDInventarioArrolamentoBeneficiarioVo.java,v 1.4 2009/03/13 14:10:15 ricardo.moraes Exp $
 */
package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota.GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDInventarioArrolamentoBeneficiario;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Iterator;


/**
 * (Value Object)Representação dos dados especícifos de beneficiario de GIAITCD do tipo
 * Inventário e Arrolamento
 * @author	Leandro Dorileo
 * @version $Revision: 1.4 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_GIA_ITCD_INVENTARIO_BENEFICIARIO 
     ,nomeAmigavel = "GIA-ITCD Inventário & Arrolamento - Beneficiário"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_ITCTB05_CODIGO_BENEFICIARIO
			)
     }
 )
public class GIAITCDInventarioArrolamentoBeneficiarioVo extends BeneficiarioVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private int infoQuantidadeUpf;
	private double valorRecolher;
	private double percentualBemRecebido;
	private double valorMultaRecolher;
	private double valorITCDBeneficiario;
	private GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo;
	private DomnSimNao flagBeneficiarioMeeiro;
   private Integer infoIsencao;
   private Integer infoDispensaRecolhimento;
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Construtor público sem parâmetro
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioVo()
	{
		super();
	}

	/**
	 * Construtor público recebendo codigo
	 * 
	 * @param codigo		codigo do Beneficiario
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor público recebendo parâmetros para consulta completa
	 * 
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo		parametros para consulta completa
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioVo(GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo)
	{
		super();
		setParametroConsulta(giaITCDInventarioArrolamentoBeneficiarioVo);
	}

	/**
	 * Construtor público recebendo a collVO
	 * 
	 * @param collVO
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioVo(Collection collVO)
	{
		super(collVO);
	}

	/**
	 * Atribui a Quantidade de UPF
	 * @param infoQuantidadeUpf
	 * @implemented by Lucas Nascimento
	 */
	public void setInfoQuantidadeUpf(int infoQuantidadeUpf)
	{
		this.infoQuantidadeUpf = infoQuantidadeUpf;
	}

	/**
	 * Retorna a Quantidade UPF
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_INFO_QTD_UPF
	     ,obrigatorio = false
	 )
	public int getInfoQuantidadeUpf()
	{
		return infoQuantidadeUpf;
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
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_VALR_RECOLHER
	     ,obrigatorio = false
	 )
	public double getValorRecolher()
	{
		if(!Validador.isNumericoValido(this.valorRecolher))
		{
			double valorRecolherAuxiliar = 0;			
		   for (Iterator iteAliquotas = getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().iterator(); iteAliquotas.hasNext(); )
		   {
		      GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) iteAliquotas.next();
		      valorRecolherAuxiliar += giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getValorRecolher();
		   }
			this.valorRecolher = valorRecolherAuxiliar;
		   if(Validador.isNumericoValido(this.getValorMultaRecolher()))
		   {
		      this.valorRecolher += this.getValorMultaRecolher();
		   }     			
		}
		return this.valorRecolher;
	}

	public String getValorRecolherFormatado()
	{
		return StringUtil.doubleToMonetario(getValorRecolher());
	}

	/**
	 * Atribui a o Percentual do Bem Recebido
	 * @param percentualBemRecebido
	 * @implemented by Lucas Nascimento
	 */
	public void setPercentualBemRecebido(double percentualBemRecebido)
	{
		this.percentualBemRecebido = percentualBemRecebido;
	}

	/**
	 * Retorna o Percentual do Bem Recebido
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public double getPercentualBemRecebido()
	{
		return this.percentualBemRecebido;
	}

	/**
	 * Retorna o Percentual do Bem Recebido
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public double getPercentualBemRecebidoCalculado()
	{
		if (Validador.isObjetoValido(this.getGiaITCDVo().getBeneficiarioVo()) && 
				 Validador.isCollectionValida(this.getGiaITCDVo().getBeneficiarioVo().getCollVO()))
		{
			double numeroBeneficiarios = this.getGiaITCDVo().getBeneficiarioVo().getCollVO().size();
			return 100D / numeroBeneficiarios;
		}
		return 0;
	}

	/**
	 * Atribui o Percentual do Bem Recebido Formatado
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public String getPercentualBemRecebidoFormatado()
	{
		return StringUtil.doubleToMonetario(getPercentualBemRecebido());
	}

	/**
	 * Atribuindo as Aliquotas de Calcula de um determinado Beneficiario
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @implemented by Lucas Nascimento
	 */
	public void setGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo(GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo)
	{
		this.giaITCDInventarioArrolamentoBeneficiarioAliquotaVo = giaITCDInventarioArrolamentoBeneficiarioAliquotaVo;
	}

	/**
	 * Retrona as aliquotas de calculo de um determinado beneficiario da gia de Inventário / Arrolamento
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo()
	{
		if (!Validador.isObjetoValido(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo))
		{
			setGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo(new GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo());
		}
		return giaITCDInventarioArrolamentoBeneficiarioAliquotaVo;
	}

	/**
	 * Metodo sobrecrito para calculo 
	 * 
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public double getValorRecebido()
	{
		return super.getValorRecebido();
	}

	/**
	 * Retorna o Valor Recebido Calculado
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public double getValorRecebidoCalculado()
	{
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

	public void setValorMultaRecolher(double valorMultaRecolher)
	{
		this.valorMultaRecolher = valorMultaRecolher;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_VALOR_MULTA_RECOLHER
	    ,obrigatorio = false
	)
	public double getValorMultaRecolher()
	{
		return valorMultaRecolher;
	}
	
	public String getValorMultaRecolherFormatado()
	{
		if(!Validador.isNumericoValido(getValorMultaRecolher()))
		{
			return "0,00";
		}
		return StringUtil.doubleToMonetario(getValorMultaRecolher());
	}

	public void setValorITCDBeneficiario(double valorITCDBeneficiario)
	{
		this.valorITCDBeneficiario = valorITCDBeneficiario;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_VALOR_ITCD_BENEFICIARIO
	    ,obrigatorio = false
	)
	public double getValorITCDBeneficiario()
	{
		return valorITCDBeneficiario;
	}
	
	public String getValorITCDBeneficiarioFormatado()
	{
		if(!Validador.isNumericoValido(getValorITCDBeneficiario()))
		{
			return "0,00";
		}
		return StringUtil.doubleToMonetario(getValorITCDBeneficiario());
	}

	public void setFlagBeneficiarioMeeiro(DomnSimNao flagBeneficiarioMeeiro)
	{
		this.flagBeneficiarioMeeiro = flagBeneficiarioMeeiro;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_FLAG_BENEF_MEEIRO
	    ,obrigatorio = true
	)
	public DomnSimNao getFlagBeneficiarioMeeiro()
	{
		if(!Validador.isDominioNumericoValido(flagBeneficiarioMeeiro))
		{
			setFlagBeneficiarioMeeiro(new DomnSimNao(-1));
		}
		return flagBeneficiarioMeeiro;
	}


   public void setInfoIsencao(Integer infoIsencao){
      this.infoIsencao = infoIsencao;
   }

   @AnotacaoAtributo
   (
       nomeColuna = CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_INFO_ISENCAO
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
       nomeColuna = CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_INFO_DSPE_RCLH
       ,obrigatorio = false
   )
   public Integer getInfoDispensaRecolhimento(){
      return infoDispensaRecolhimento;
   }
}
