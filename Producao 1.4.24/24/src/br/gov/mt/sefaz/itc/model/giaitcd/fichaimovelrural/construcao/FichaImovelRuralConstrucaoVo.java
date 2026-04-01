package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao;

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
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnEstadoConservacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralConstrucao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Iterator;


/**
 * Classe responsável por encapsular os valores do objeto Ficha Imóvel Rural - Bem (Value Object).
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_FICHA_IMOVEL_RURAL_CONSTRUCAO
     ,nomeAmigavel = "Ficha de Imóvel Rural - Construção"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposFichaImovelRuralConstrucao.CAMPO_CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_FICHA_IMOVEL_RURAL_CONSTRUCAO 
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class FichaImovelRuralConstrucaoVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private ConstrucaoVo construcaoVo;
	private String descricaoConstrucao;
	private DomnEstadoConservacao situacaoEstadoConservacao;
	private FichaImovelRuralVo fichaImovelRuralVo;
	private double valorMercado;

	/**
	 * Construtor Padrão
	 * 
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a chave primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe o Parametro de Consulta
	 * 
	 * @param fichaImovelRuralConstrucaoVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoVo(FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo)
	{
		super();
		setParametroConsulta(fichaImovelRuralConstrucaoVo);
	}

	/**
	 * Construtor que recebe uma Collection de FichaImovelRuralConstrucaoVo
	 * 
	 * @param collVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoVo(Collection collVo)
	{
		super(collVo);
	}

	/**
	 * Retorna a chave primária
	 * 
	 * @return FichaImovelRuralBemPk
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoPk getPk()
	{
		return new FichaImovelRuralConstrucaoPk(getCodigo());
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Retorna o Total de TODOS os Bens
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	public double getValorTotalBens()
	{
		double total = 0;
		for (Iterator iteBem = getCollVO().iterator(); iteBem.hasNext(); )
		{
			total += ((FichaImovelRuralConstrucaoVo) iteBem.next()).getValorMercado();
		}
		return total;
	}

	/**
	 * Retorna o valor formato para o Total dos Bens.
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public String getValorTotalBensFormatado()
	{
		double valorTotal = getValorTotalBens();
		if (!Validador.isNumericoValido(valorTotal))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(valorTotal);
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
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRuralConstrucao.CAMPO_VALOR_MERCADO
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
	 * Atribui a Situação do Estado de Conservação
	 * 
	 * @param situacaoEstadoConservacao
	 * @implemented by Daniel Balieiro
	 */
	public void setSituacaoEstadoConservacao(DomnEstadoConservacao situacaoEstadoConservacao)
	{
		this.situacaoEstadoConservacao = situacaoEstadoConservacao;
	}

	/**
	 * Retorna a Situação do Estado de Conservação
	 * 
	 * @return int
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRuralConstrucao.CAMPO_SITUACAO_ESTADO_CONSERVACAO
	     ,obrigatorio = true
	 )
	public DomnEstadoConservacao getSituacaoEstadoConservacao()
	{
		if (!Validador.isDominioNumericoValido(situacaoEstadoConservacao))
		{
			setSituacaoEstadoConservacao(new DomnEstadoConservacao(-1));
		}
		return situacaoEstadoConservacao;
	}

	/**
	 * Atribui a Descrição do Bem
	 * 
	 * @param descricaoBem
	 * @implemented by Daniel Balieiro
	 */
	public void setDescricaoConstrucao(String descricaoBem)
	{
		if (Validador.isStringValida(descricaoBem))
		{
			this.descricaoConstrucao = descricaoBem.trim().toUpperCase();
		}
		else
		{
			this.descricaoConstrucao = descricaoBem;
		}
	}

	/**
	 * Retorna a Descrição do Bem
	 * 
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRuralConstrucao.CAMPO_DESCRICAO_CONSTRUCAO
	     ,obrigatorio = false
	 )
	public String getDescricaoConstrucao()
	{
		if (!Validador.isStringValida(descricaoConstrucao))
		{
			return STRING_VAZIA;
		}
		return descricaoConstrucao;
	}

	/**
	 * Atribui a Ficha Imóvel Rural
	 * 
	 * @param fichaImovelRuralVo
	 * @implemented by Daniel Balieiro
	 */
	public void setFichaImovelRuralVo(FichaImovelRuralVo fichaImovelRuralVo)
	{
		this.fichaImovelRuralVo = fichaImovelRuralVo;
	}

	/**
	 * Retorna a Ficha Imóvel Rural
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
	             nomeColuna = CamposFichaImovelRuralConstrucao.CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL
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
	 * Efetua um Clone do objeto atual
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public Object clone()
	{
		FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoAuxVo = new FichaImovelRuralConstrucaoVo();
		fichaImovelRuralConstrucaoAuxVo.getConstrucaoVo().setCodigo(this.getConstrucaoVo().getCodigo());
		fichaImovelRuralConstrucaoAuxVo.getConstrucaoVo().setDescricaoConstrucao(this.getConstrucaoVo().getDescricaoConstrucao());
		fichaImovelRuralConstrucaoAuxVo.setDescricaoConstrucao(this.getDescricaoConstrucao());
		fichaImovelRuralConstrucaoAuxVo.setSituacaoEstadoConservacao(new DomnEstadoConservacao(this.getSituacaoEstadoConservacao().getValorCorrente()));
		fichaImovelRuralConstrucaoAuxVo.setValorMercado(this.getValorMercado());
		return fichaImovelRuralConstrucaoAuxVo;
	}

	/**
	 * Atribui o Construção 
	 * @param construcaoVo
	 * @implemented by Daniel Balieiro
	 */
	public void setConstrucaoVo(ConstrucaoVo construcaoVo)
	{
		this.construcaoVo = construcaoVo;
	}

	/**
	 * Retorna o Construção
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = true
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposFichaImovelRuralConstrucao.CAMPO_ITCTB13_CODIGO_TIPO_CONSTRUCAO
	             , nomeAtributo = "codigo"
	         )
	     }
	 )
	public ConstrucaoVo getConstrucaoVo()
	{
		if (!Validador.isObjetoValido(construcaoVo))
		{
			setConstrucaoVo(new ConstrucaoVo());
		}
		return construcaoVo;
	}
   
   /**
    * Cria um novo objeto do tipo <b>FichaImovelRuralConstrucaoVo</b> e copia os valores dos seguintes atributos.
    * </br>
    * </br>
    * <ol>
    * <li>this.getCodigo()</li>
    * <li>this.getConstrucaoVo().getCodigo()</li>
    * <li>this.getConstrucaoVo().getDescricaoConstrucao()</li>
    * <li>this.getDescricaoConstrucao()</li>
    * <li>this.getSituacaoEstadoConservacao().getValorCorrente()</li>
    * <li>this.getValorMercado()</li>
    * </ol>
    * @return Object : do tipo br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao.<b>FichaImovelRuralConstrucaoVo</b>
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public Object clonePadrao()
   {
      FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoAuxVo = new FichaImovelRuralConstrucaoVo();
      fichaImovelRuralConstrucaoAuxVo.setCodigo(this.getCodigo());
      fichaImovelRuralConstrucaoAuxVo.getConstrucaoVo().setCodigo(this.getConstrucaoVo().getCodigo());
      fichaImovelRuralConstrucaoAuxVo.getConstrucaoVo().setDescricaoConstrucao(this.getConstrucaoVo().getDescricaoConstrucao());
      fichaImovelRuralConstrucaoAuxVo.setDescricaoConstrucao(this.getDescricaoConstrucao());
      fichaImovelRuralConstrucaoAuxVo.setSituacaoEstadoConservacao(new DomnEstadoConservacao(this.getSituacaoEstadoConservacao().getValorCorrente()));
      fichaImovelRuralConstrucaoAuxVo.setValorMercado(this.getValorMercado());
      return fichaImovelRuralConstrucaoAuxVo;
   }
   
}
