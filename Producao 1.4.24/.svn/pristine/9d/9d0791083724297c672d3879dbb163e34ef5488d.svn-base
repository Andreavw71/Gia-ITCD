package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralBenfeitoria;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;


/**
 * Classe responsável por encapsular os valores do objeto Ficha Imóvel Rural - Benfeitoria (Value Object).
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_FICHA_IMOVEL_RURAL_BENFEITORIA
     ,nomeAmigavel = "Ficha Imóvel Rural - Benfeitoria"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposFichaImovelRuralBenfeitoria.CAMPO_CODIGO_IMOVEL_RURAL_BENFEITORIA
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_FICHA_IMOVEL_RURAL_BENFEITORIA
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class FichaImovelRuralBenfeitoriaVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private BenfeitoriaVo benfeitoriaVo;
	private String descricaoComplementarBenfeitoria;
	private FichaImovelRuralVo fichaImovelRuralVo;

	/**
	 * Construtor Padrão
	 * 
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBenfeitoriaVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a chave primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBenfeitoriaVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe o parametro de consulta
	 * 
	 * @param fichaImovelRuralBenfeitoriaVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBenfeitoriaVo(FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo)
	{
		super();
		setParametroConsulta(fichaImovelRuralBenfeitoriaVo);
	}

	/**
	 * Construtor que recebe uma coleção de FichaImovelRuralBenfeitoriaVo
	 * 
	 * @param collVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBenfeitoriaVo(Collection collVo)
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
	 * @return FichaImovelRuralBenfeitoriaPk
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBenfeitoriaPk getPk()
	{
		return new FichaImovelRuralBenfeitoriaPk(getCodigo());
	}

	/**
	 * Atribui a Ficha de Imovel Rual
	 * 
	 * @param fichaImovelRural
	 * @implemented by Daniel Balieiro
	 */
	public void setFichaImovelRuralVo(FichaImovelRuralVo fichaImovelRural)
	{
		this.fichaImovelRuralVo = fichaImovelRural;
	}

	/**
	 * Retorna a Ficha de Imovel Rural
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
	             nomeColuna = CamposFichaImovelRuralBenfeitoria.CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL
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
	 * Atribui a Benefeitoria
	 * 
	 * @param benfeitoria
	 * @implemented by Daniel Balieiro
	 */
	public void setBenfeitoriaVo(BenfeitoriaVo benfeitoria)
	{
		this.benfeitoriaVo = benfeitoria;
	}

	/**
	 * Retorna a Benfeitoria
	 * 
	 * @return BenfeitoriaVo
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = true
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposFichaImovelRuralBenfeitoria.CAMPO_ITCTB07_CODIGO_BENFEITORIA
	             , nomeAtributo = "codigo"
	         )
	     }
	 )
	public BenfeitoriaVo getBenfeitoriaVo()
	{
		if (!Validador.isObjetoValido(benfeitoriaVo))
		{
			setBenfeitoriaVo(new BenfeitoriaVo());
		}
		return benfeitoriaVo;
	}

	/**
	 * Cria um Clone do objeto atual
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public Object clone()
	{
		FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaAuxVo = new FichaImovelRuralBenfeitoriaVo();
		fichaImovelRuralBenfeitoriaAuxVo.getBenfeitoriaVo().setCodigo(this.getBenfeitoriaVo().getCodigo());
		fichaImovelRuralBenfeitoriaAuxVo.getBenfeitoriaVo().setDescricaoBenfeitoria(this.getBenfeitoriaVo().getDescricaoBenfeitoria());
		fichaImovelRuralBenfeitoriaAuxVo.setDescricaoComplementarBenfeitoria(this.getDescricaoComplementarBenfeitoria());
		return fichaImovelRuralBenfeitoriaAuxVo;
	}

	public void setDescricaoComplementarBenfeitoria(String descricaoComplementarBenfeitoria)
	{
		this.descricaoComplementarBenfeitoria = descricaoComplementarBenfeitoria;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposFichaImovelRuralBenfeitoria.CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA
	    ,obrigatorio = false
	)
	public String getDescricaoComplementarBenfeitoria()
	{
		if(!Validador.isStringValida(descricaoComplementarBenfeitoria))
		{
			return STRING_VAZIA;
		}
		return descricaoComplementarBenfeitoria;
	}
   
   
   /**
    * Cria um novo objeto do tipo <b>FichaImovelRuralBenfeitoriaVo</b> e copia os valores dos seguintes atributos.
    * </br>
    * </br>
    * <ol>
    * <li>this.getCodigo()</li>
    * <li>this.getBenfeitoriaVo().getCodigo()</li>
    * <li>this.getBenfeitoriaVo().getDescricaoBenfeitoria()</li>
    * <li>this.getDescricaoComplementarBenfeitoria()</li>
    * </ol>
    * @return Object : do tipo br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria.<b>FichaImovelRuralBenfeitoriaVo</b>
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public Object clonePadrao()
   {
      FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaAuxVo = new FichaImovelRuralBenfeitoriaVo();
      fichaImovelRuralBenfeitoriaAuxVo.setCodigo(this.getCodigo());
      fichaImovelRuralBenfeitoriaAuxVo.getBenfeitoriaVo().setCodigo(this.getBenfeitoriaVo().getCodigo());
      fichaImovelRuralBenfeitoriaAuxVo.getBenfeitoriaVo().setDescricaoBenfeitoria(this.getBenfeitoriaVo().getDescricaoBenfeitoria());
      fichaImovelRuralBenfeitoriaAuxVo.setDescricaoComplementarBenfeitoria(this.getDescricaoComplementarBenfeitoria());
      return fichaImovelRuralBenfeitoriaAuxVo;
   }
}
