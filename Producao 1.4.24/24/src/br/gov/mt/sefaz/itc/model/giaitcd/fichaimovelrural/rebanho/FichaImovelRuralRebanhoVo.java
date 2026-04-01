package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho;

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
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralRebanho;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Iterator;


/**
 * Classe responsável por encapsular os valores do objeto Ficha Imóvel Rural Rebanho (Value Object).
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_FICHA_IMOVEL_RURAL_REBANHO
     ,nomeAmigavel = "Ficha Imóvel Rural - Rebanho"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposFichaImovelRuralRebanho.CAMPO_CODIGO_IMOVEL_RURAL_REBANHO
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_FICHA_IMOVEL_RURAL_REBANHO 
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class FichaImovelRuralRebanhoVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private FichaImovelRuralVo fichaImovelRuralVo;
	private RebanhoVo rebanhoVo;
	private double valorMercado;
	private long quantidadeRebanho;
	private String descricaoRebanho;
	private  double valorTotal;

	/**
	 * Construtor Padrão
	 * 
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralRebanhoVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a Chave Primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralRebanhoVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe o parametro de consulta
	 * 
	 * @param fichaImovelRuralRebanhoVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralRebanhoVo(FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo)
	{
		super();
		setParametroConsulta(fichaImovelRuralRebanhoVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Método que recebe uma Collection de FichaImovelRuralRebanhoVo
	 * 
	 * @param collVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralRebanhoVo(Collection collVo)
	{
		super(collVo);
	}

	/**
	 * Atribui uma FichaImovelRuralVo
	 * 
	 * @param fichaImovelRuralVo
	 * @implemented by Daniel Balieiro
	 */
	public void setFichaImovelRuralVo(FichaImovelRuralVo fichaImovelRuralVo)
	{
		this.fichaImovelRuralVo = fichaImovelRuralVo;
	}

	/**
	 * Retorna a FichaImovelRuralVo
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
	             nomeColuna = CamposFichaImovelRuralRebanho.CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL
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
	 * Atribui a RebanhoVo
	 * 
	 * @param rebanhoVo
	 * @implemented by Daniel Balieiro
	 */
	public void setRebanhoVo(RebanhoVo rebanhoVo)
	{
		this.rebanhoVo = rebanhoVo;
	}

	/**
	 * Retorna a RebanhoVo
	 * @return RebanhoVo
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = true
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposFichaImovelRuralRebanho.CAMPO_ITCTB10_CODG_REBANHO
	             , nomeAtributo = "codigo"
	         )
	     }
	 )
	public RebanhoVo getRebanhoVo()
	{
		if (!Validador.isObjetoValido(rebanhoVo))
		{
			setRebanhoVo(new RebanhoVo());
		}
		return rebanhoVo;
	}

	/**
	 * Atribui o Valor de Mercado
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
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRuralRebanho.CAMPO_VALOR_MERCADO
	     ,obrigatorio = true
	 )
	public double getValorMercado()
	{
		return valorMercado;
	}

	/**
	 * Retorna o Valor de Mercado Formatado
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
	 * Retorna o total deste rebanho
	 * 
	 * @return doble
	 * @implemented by Daniel Balieiro
	 */
	public double getValorTotal()
	{
		return valorTotal;
	}

	/**
	 * Retorna o Valor Total formatado
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public String getValorTotalFormatado()
	{
		if (!Validador.isNumericoValido(getValorTotal()))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(getValorTotal());
	}
	
	public void setValorTotal(double valorTotal)
	{
		this.valorTotal = valorTotal;
	}

	/**
	 * Retorna o total de TODOS os rebanhos 
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	public double getValorTotalRebanhos()
	{
		double total = 0;
		for (Iterator iteRebanho = getCollVO().iterator(); iteRebanho.hasNext(); )
		{
			total += ((FichaImovelRuralRebanhoVo) iteRebanho.next()).getValorTotal();
		}
		return total;
	}

	/**
	 * Retorna o Valor Total de Rebanhos Formatado
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public String getValorTotalRebanhosFormatado()
	{
		double valorTotal = getValorTotalRebanhos();
		if (!Validador.isNumericoValido(valorTotal))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(valorTotal);
	}

	/**
	 * Atribui a Quantidade de Rebanho
	 * 
	 * @param quantidadeRebanho
	 * @implemented by Daniel Balieiro
	 */
	public void setQuantidadeRebanho(long quantidadeRebanho)
	{
		this.quantidadeRebanho = quantidadeRebanho;
	}

	/**
	 * Retorna a Quantidade de Rebanho
	 * @return int
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRuralRebanho.CAMPO_QUANTIDADE_REBANHO
	     ,obrigatorio = true
	 )
	public long getQuantidadeRebanho()
	{
		return quantidadeRebanho;
	}

	/**
	 * Retorna a Quantidade de Rebanho Formatado
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public String getQuantidadeRebanhoFormatado()
	{
		if (!Validador.isNumericoValido(getQuantidadeRebanho()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getQuantidadeRebanho());
	}

	/**
	 * Atribui a Descrição do Rebanho
	 * 
	 * @param descricaoRebanho
	 * @implemented by Daniel Balieiro
	 */
	public void setDescricaoRebanho(String descricaoRebanho)
	{
		if (Validador.isStringValida(descricaoRebanho))
		{
			this.descricaoRebanho = descricaoRebanho.trim().toUpperCase();
		}
		else
		{
			this.descricaoRebanho = descricaoRebanho;
		}
	}

	/**
	 * Retorna a Descrição do Rebanho
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRuralRebanho.CAMPO_DESCRICAO_REBANHO
	     ,obrigatorio = false
	 )
	public String getDescricaoRebanho()
	{
		if (!Validador.isStringValida(descricaoRebanho))
		{
			return STRING_VAZIA;
		}
		return descricaoRebanho;
	}

	/**
	 * Cria o clone do objeto atual
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public Object clone()
	{
		FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoAuxVo = new FichaImovelRuralRebanhoVo();
		fichaImovelRuralRebanhoAuxVo.getRebanhoVo().setCodigo(this.getRebanhoVo().getCodigo());
		fichaImovelRuralRebanhoAuxVo.getRebanhoVo().setDescricaoRebanho(this.getRebanhoVo().getDescricaoRebanho());
		fichaImovelRuralRebanhoAuxVo.setDescricaoRebanho(this.getDescricaoRebanho());
		fichaImovelRuralRebanhoAuxVo.setQuantidadeRebanho(this.getQuantidadeRebanho());
		fichaImovelRuralRebanhoAuxVo.setValorMercado(this.getValorMercado());
		fichaImovelRuralRebanhoAuxVo.setValorTotal(this.getValorTotal());
		return fichaImovelRuralRebanhoAuxVo;
	}
   
   /**
    * Cria um novo objeto do tipo <b>FichaImovelRuralRebanhoVo</b> e copia os valores dos seguintes atributos.
    * </br>
    * </br>
    * <ol>
    * <li>this.getCodigo()</li>
    * <li>this.getRebanhoVo().getCodigo()</li>
    * <li>this.getRebanhoVo().getDescricaoRebanho()</li>
    * <li>this.getDescricaoRebanho()</li>
    * <li>this.getQuantidadeRebanho()</li>
    * <li>this.getValorMercado()</li>
    * <li>this.getValorTotal()</li>
    * </ol>
    * @return Object : do tipo br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho.<b>FichaImovelRuralRebanhoVo</b>
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public Object clonePadrao()
   {
      FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoAuxVo = new FichaImovelRuralRebanhoVo();
      fichaImovelRuralRebanhoAuxVo.setCodigo(this.getCodigo());
      fichaImovelRuralRebanhoAuxVo.getRebanhoVo().setCodigo(this.getRebanhoVo().getCodigo());
      fichaImovelRuralRebanhoAuxVo.getRebanhoVo().setDescricaoRebanho(this.getRebanhoVo().getDescricaoRebanho());
      fichaImovelRuralRebanhoAuxVo.setDescricaoRebanho(this.getDescricaoRebanho());
      fichaImovelRuralRebanhoAuxVo.setQuantidadeRebanho(this.getQuantidadeRebanho());
      fichaImovelRuralRebanhoAuxVo.setValorMercado(this.getValorMercado());
      fichaImovelRuralRebanhoAuxVo.setValorTotal(this.getValorTotal());
      return fichaImovelRuralRebanhoAuxVo;
   }
   
}
