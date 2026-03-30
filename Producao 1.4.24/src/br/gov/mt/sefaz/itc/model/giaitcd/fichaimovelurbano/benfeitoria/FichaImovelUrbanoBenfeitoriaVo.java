package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.benfeitoria;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelUrbanoBenfeitoria;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Iterator;


/**
 * Classe responsável por encapsular os valores do objeto FichaImovelUrbanoBenfeitoria (Value Object)
 * @author Lucas Nascimento
 * @version $Revision: 1.2 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_IMOVEL_URBANO_BENFEITORIA
     ,nomeAmigavel = "Benfeitoria Ficha de Imóvel Urbano"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposFichaImovelUrbanoBenfeitoria.CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_IMOVEL_URBANO_BENFEITORIA
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class FichaImovelUrbanoBenfeitoriaVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private BenfeitoriaVo benfeitoriaVo;
	private String descricaoComplementarBenfeitoria;
	private FichaImovelUrbanoVo fichaImovelUrbanoVo;

	/**
	 * Construtor vazio.
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoBenfeitoriaVo()
	{
		super();
	}

	/**
	 * Construtor padrão.
	 * @param codigo Chave primário
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoBenfeitoriaVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe um parâmetro de consulta
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoBenfeitoriaVo(FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo)
	{
		super();
		setParametroConsulta(fichaImovelUrbanoBenfeitoriaVo);
	}

	/**
	 * Construtor que recebe uma Collection
	 * @param collVo
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoBenfeitoriaVo(Collection collVo)
	{
		super(collVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui uma benfeitoria 
	 * @param benfeitoriaVo
	 * @implemented by Lucas Nascimento
	 */
	public void setBenfeitoriaVo(BenfeitoriaVo benfeitoriaVo)
	{
		if (Validador.isObjetoValido(benfeitoriaVo))
		{
			benfeitoriaVo.setUsuarioLogado(this.getUsuarioLogado());
			for (Iterator iteBenfeitoria = benfeitoriaVo.getCollVO().iterator(); iteBenfeitoria.hasNext(); )
			{
				BenfeitoriaVo benfeitoriaAtualVo = (BenfeitoriaVo) iteBenfeitoria.next();
				benfeitoriaAtualVo.setUsuarioLogado(this.getUsuarioLogado());
			}
		}
		this.benfeitoriaVo = benfeitoriaVo;
	}

	/**
	 * Retorna uma benfeitoria
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = true
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposFichaImovelUrbanoBenfeitoria.CAMPO_ITCTB07_CODIGO_BENFEITORIA
	             , nomeAtributo = "codigo"
	         )
	     }
	 )
	public BenfeitoriaVo getBenfeitoriaVo()
	{
		try
		{
			Validador.validaObjeto(benfeitoriaVo);
		}
		catch (ObjetoObrigatorioException e)
		{
			setBenfeitoriaVo(new BenfeitoriaVo());
		}
		return benfeitoriaVo;
	}

	/**
	 * Atribui uma ficha de imovel urbano
	 * @param fichaImovelUrbano
	 * @implemented by Lucas Nascimento
	 */
	public void setFichaImovelUrbanoVo(FichaImovelUrbanoVo fichaImovelUrbano)
	{
		if (Validador.isObjetoValido(fichaImovelUrbano))
		{
			fichaImovelUrbano.setUsuarioLogado(this.getUsuarioLogado());
			for (Iterator iteFicha = fichaImovelUrbano.getCollVO().iterator(); iteFicha.hasNext(); )
			{
				FichaImovelUrbanoVo fichaImovelUrbanoAtualVo = (FichaImovelUrbanoVo) iteFicha.next();
				fichaImovelUrbanoAtualVo.setUsuarioLogado(this.getUsuarioLogado());
			}
		}
		this.fichaImovelUrbanoVo = fichaImovelUrbano;
	}

	/**
	 * Retorna uma ficha de imovel urbano
	 * @return FichaImovelUrbanoVo
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = true
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposFichaImovelUrbanoBenfeitoria.CAMPO_ITCTB19_CODIGO_IMOVEL_URBANO
	             , nomeAtributo = "codigo"
	         )
	     }
	 )
	public FichaImovelUrbanoVo getFichaImovelUrbanoVo()
	{
		if (!Validador.isObjetoValido(fichaImovelUrbanoVo))
		{
			setFichaImovelUrbanoVo(new FichaImovelUrbanoVo());
		}
		return fichaImovelUrbanoVo;
	}

	/**
	 * Retorna a Pk da tabela.
	 * @return FichaImovelUrbanoBenfeitoriaPk
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoBenfeitoriaPk getFichaImovelUrbanoBenfeitoriaPk()
	{
		return new FichaImovelUrbanoBenfeitoriaPk(getCodigo());
	}

	/**
	 * Cria um clone do objeto atual
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public Object clone()
	{
		FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaAuxVo = new FichaImovelUrbanoBenfeitoriaVo();
		fichaImovelUrbanoBenfeitoriaAuxVo.getBenfeitoriaVo().setCodigo(this.getBenfeitoriaVo().getCodigo());
		fichaImovelUrbanoBenfeitoriaAuxVo.getBenfeitoriaVo().setDescricaoBenfeitoria(this.getBenfeitoriaVo().getDescricaoBenfeitoria());
		fichaImovelUrbanoBenfeitoriaAuxVo.setDescricaoComplementarBenfeitoria(this.getDescricaoComplementarBenfeitoria());
		return fichaImovelUrbanoBenfeitoriaAuxVo;
	}

	public void setDescricaoComplementarBenfeitoria(String descricaoComplementarBenfeitoria)
	{
		this.descricaoComplementarBenfeitoria = descricaoComplementarBenfeitoria;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposFichaImovelUrbanoBenfeitoria.CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA
	    ,obrigatorio = false
	)
	public String getDescricaoComplementarBenfeitoria()
	{
		if(!Validador.isStringValida(descricaoComplementarBenfeitoria))
		{
			setDescricaoComplementarBenfeitoria(STRING_VAZIA);
		}
		return descricaoComplementarBenfeitoria;
	}
   
}
