package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralCultura;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Iterator;


/**
 * Classe responsável por encapsular os valores do objeto Ficha Imóvel Rural - Cultura (Value Object).
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_FICHA_IMOVEL_RURAL_CULTURA
     ,nomeAmigavel = "Ficha Imóvel Rural - Cultura"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposFichaImovelRuralCultura.CAMPO_CODIGO_IMOVEL_RURAL_CULTURA
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_FICHA_IMOVEL_RURAL_CULTURA 
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class FichaImovelRuralCulturaVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private CulturaVo culturaVo;
	private double valorMercado;
	private String descricaoComplementarCultura;
	private double valorHectare;
	private FichaImovelRuralVo fichaImovelRuralVo;
	private double areaCultivada;

	/**
	 * Construtor Padrão
	 * 
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a Chave Primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe o parametro de consulta
	 * 
	 * @param fichaImovelRuralCulturaVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaVo(FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo)
	{
		super();
		setParametroConsulta(fichaImovelRuralCulturaVo);
	}

	/**
	 * Construtor que recebe uma Collection de FichaImovelRuralCulturaVo
	 * 
	 * @param collVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaVo(Collection collVo)
	{
		super(collVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Retorna a Chave Primária
	 * 
	 * @return FichaImovelRuralCulturaPk
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaPk getPk()
	{
		return new FichaImovelRuralCulturaPk(getCodigo());
	}

	/**
	 * Retorna o total de TODAS as culturas
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	public double getValorTotalCulturas()
	{
		double total = 0;
		for (Iterator iteCultura = getCollVO().iterator(); iteCultura.hasNext(); )
		{
			total += ((FichaImovelRuralCulturaVo) iteCultura.next()).getValorMercado();
		}
		return total;
	}

	/**
	 * Retorna o valor formatado para o Total de Culturas
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public String getValorTotalCulturasFormatado()
	{
		double valorTotal = getValorTotalCulturas();
		if (!Validador.isNumericoValido(valorTotal))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(valorTotal);
	}

	/**
	 * Atribui uma Cultura
	 * 
	 * @param culturaVo
	 * @implemented by Daniel Balieiro
	 */
	public void setCulturaVo(CulturaVo culturaVo)
	{
		this.culturaVo = culturaVo;
	}

	/**
	 * Retorna a Cultura
	 * 
	 * @return CulturaVo
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = true
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposFichaImovelRuralCultura.CAMPO_ITCTB08_CODIGO_CULTURA
	             , nomeAtributo = "codigo"
	         )
	     }
	 )
	public CulturaVo getCulturaVo()
	{
		if (!Validador.isObjetoValido(culturaVo))
		{
			setCulturaVo(new CulturaVo());
		}
		return culturaVo;
	}

	/**
	 * Atribui uma Ficha Imóvel Rural
	 * 
	 * @param fichaImovelRuralVo
	 * @implemented by Daniel Balieiro
	 */
	public void setFichaImovelRuralVo(FichaImovelRuralVo fichaImovelRuralVo)
	{
		this.fichaImovelRuralVo = fichaImovelRuralVo;
	}

	/**
	 * Retorna uma Ficha Imóvel Rural
	 *  
	 * @return FichaImovelRuralVo
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = true
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposFichaImovelRuralCultura.CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL
	             , nomeAtributo = "codigo"
	         )
	     }
	 )
	public FichaImovelRuralVo getFichaImovelRuralVo()
	{
		if (!Validador.isObjetoValido(fichaImovelRuralVo))
		{
			setFichaImovelRuralVo(new FichaImovelRuralVo());
		}
		return fichaImovelRuralVo;
	}

	/**
	 * Atribui um Valor de Mercado
	 * 
	 * @param valorMercado
	 * @implemented by Daniel Balieiro
	 */
	public void setValorMercado(double valorMercado)
	{
		this.valorMercado = valorMercado;
	}

	/**
	 * Retorna o Valor de Mercado
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRuralCultura.CAMPO_VALOR_MERCADO
	     ,obrigatorio = true
	 )
	public double getValorMercado()
	{
		return valorMercado;
	}

	/**
	 * Retorna o Valor de Mercado Formatado
	 * 
	 * @return String 
	 * @implemented by João Batista Padilha e Silva
	 */
	public String getValorMercadoFormatado()
	{
		if (!Validador.isNumericoValido(getValorMercado()))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(getValorMercado());
	}

	/**
	 * Atribui uma Área Cultivada
	 * 
	 * @param areaCultivada
	 * @implemented by Daniel Balieiro
	 */
//	 @AnotacaoAtributo
//	 (
//	     nomeColuna = CamposFichaImovelRuralCultura.CAMPO_AREA_CULTIVADA
//	     ,obrigatorio = true
//	 )
	public void setAreaCultivada(double areaCultivada)
	{
		this.areaCultivada = areaCultivada;
	}

	/**
	 * Retorna a Área Cultivada
	 * 
	 * @return int
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRuralCultura.CAMPO_AREA_CULTIVADA
	     ,obrigatorio = true
	 )
	public double getAreaCultivada()
	{
		return areaCultivada;
	}

	/**
	 * Retorna a Area Cultivada Formatado
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	public String getAreaCultivadaFormatado()
	{
		if (!Validador.isNumericoValido(areaCultivada))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(areaCultivada,4);
	}

	/**
	 * Cria um clone do objeto atual
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public Object clone()
	{
		FichaImovelRuralCulturaVo fichaImovelRuralCulturaAuxVo = new FichaImovelRuralCulturaVo();
		fichaImovelRuralCulturaAuxVo.getCulturaVo().setCodigo(this.getCulturaVo().getCodigo());
		fichaImovelRuralCulturaAuxVo.getCulturaVo().setDescricaoCultura(this.getCulturaVo().getDescricaoCultura());
		fichaImovelRuralCulturaAuxVo.setAreaCultivada(this.getAreaCultivada());
		fichaImovelRuralCulturaAuxVo.setValorMercado(this.getValorMercado());
		fichaImovelRuralCulturaAuxVo.setDescricaoComplementarCultura(this.getDescricaoComplementarCultura());
		fichaImovelRuralCulturaAuxVo.setValorHectare(this.getValorHectare());
		return fichaImovelRuralCulturaAuxVo;
	}

	public void setDescricaoComplementarCultura(String descricaoComplementarCultura)
	{
		this.descricaoComplementarCultura = descricaoComplementarCultura;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposFichaImovelRuralCultura.CAMPO_DESCRICAO_COMPLEMENTAR_CULTURA
	    ,obrigatorio = false
	)
	public String getDescricaoComplementarCultura()
	{
		if(descricaoComplementarCultura==null)
		{
			return STRING_VAZIA;
		}
		return descricaoComplementarCultura;
	}

	public void setValorHectare(double valorHectare)
	{
		this.valorHectare = valorHectare;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposFichaImovelRuralCultura.CAMPO_VALOR_HECTARE
	    ,obrigatorio = true
	)
	public double getValorHectare()
	{
		return valorHectare;
	}
	
	public String getValorHectareFormatado()
	{
		if(!Validador.isNumericoValido(valorHectare))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(getValorHectare());
	}
   
   /**
    * Cria um novo objeto do tipo <b>FichaImovelRuralCulturaVo</b> e copia os valores dos seguintes atributos.
    * </br>
    * </br>
    * <ol>
    * <li>this.getCodigo()</li>
    * <li>this.getCulturaVo().getCodigo()</li>
    * <li>this.getCulturaVo().getDescricaoCultura()</li>
    * <li>this.getAreaCultivada()</li>
    * <li>this.getValorMercado()</li>
    * <li>this.getDescricaoComplementarCultura()</li>
    * <li>this.getValorHectare()</li>
    * </ol>
    * @return Object : do tipo br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho.<b>FichaImovelRuralRebanhoVo</b>
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public Object clonePadrao()
   {
      FichaImovelRuralCulturaVo fichaImovelRuralCulturaAuxVo = new FichaImovelRuralCulturaVo();
      fichaImovelRuralCulturaAuxVo.setCodigo(this.getCodigo());
      fichaImovelRuralCulturaAuxVo.getCulturaVo().setCodigo(this.getCulturaVo().getCodigo());
      fichaImovelRuralCulturaAuxVo.getCulturaVo().setDescricaoCultura(this.getCulturaVo().getDescricaoCultura());
      fichaImovelRuralCulturaAuxVo.setAreaCultivada(this.getAreaCultivada());
      fichaImovelRuralCulturaAuxVo.setValorMercado(this.getValorMercado());
      fichaImovelRuralCulturaAuxVo.setDescricaoComplementarCultura(this.getDescricaoComplementarCultura());
      fichaImovelRuralCulturaAuxVo.setValorHectare(this.getValorHectare());
      return fichaImovelRuralCulturaAuxVo;
   }
   
}
