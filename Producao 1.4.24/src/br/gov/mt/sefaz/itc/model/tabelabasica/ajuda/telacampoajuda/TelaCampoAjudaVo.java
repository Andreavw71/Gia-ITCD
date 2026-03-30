/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: TelaCampoAjudaVo.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: TelaCampoAjudaVo.java,v 1.1.1.1 2008/05/28 17:55:06 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.campoajuda.CampoAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telaajuda.TelaAjudaVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposTelaCampoAjuda;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Date;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores do objeto TelaCampoAjuda (Value Object).
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_TELA_CAMPO_AJUDA
     ,nomeAmigavel = "Tela Campo Ajuda"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "campoAjudaVo.codigo"
			    ,nomeColuna = CamposTelaCampoAjuda.CAMPO_CODIGO_CAMPO_AJUDA
			)
			,@AnotacaoIdentificador
			(
			    nomeAtributo = "telaAjudaVo.codigo"
			    ,nomeColuna = CamposTelaCampoAjuda.CAMPO_CODIGO_TELA_AJUDA
			)
     }
 )
public class TelaCampoAjudaVo extends EntidadeVo
{
	private CampoAjudaVo campoAjudaVo;
	private DomnStatusRegistro statusTelaCampo;
	private static final long serialVersionUID = Long.MAX_VALUE;
	private String descricaoAjudaCampo;
	private TelaAjudaVo telaAjudaVo;
	private Date dataAtualizacaoBD;
	private int codigoOrdenacao;

	/**
	 * Construtor da classe.
	 * @param codigo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaCampoAjudaVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor vazio.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaCampoAjudaVo()
	{
		super();
	}

	/**
	 * Construtor que recebe um Parametro Consulta.
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaCampoAjudaVo(TelaCampoAjudaVo telaCampoAjudaVo)
	{
		super();
		setParametroConsulta(telaCampoAjudaVo);
	}

	/**
	 * Construtor que recebe uma Collection.
	 * @param collVo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaCampoAjudaVo(Collection collVo)
	{
		super(collVo);
	}

	/**
	 * Atribui um campo ajuda.
	 * @param campoAjudaVo Campo Ajuda (Value Object).
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setCampoAjudaVo(CampoAjudaVo campoAjudaVo)
	{
		this.campoAjudaVo = campoAjudaVo;
	}

	/**
	 * Retorna um Campo Ajuda.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public CampoAjudaVo getCampoAjudaVo()
	{
		if (campoAjudaVo == null)
		{
			setCampoAjudaVo(new CampoAjudaVo());
			return campoAjudaVo;
		}
		else
		{
			return campoAjudaVo;
		}
	}

	/**
	 * Atribui um tela ajuda.
	 * @param telaAjudaVo Tela Ajuda (Value Object).
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setTelaAjudaVo(TelaAjudaVo telaAjudaVo)
	{
		this.telaAjudaVo = telaAjudaVo;
	}

	/**
	 * Retorna uma Tela Ajuda.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaAjudaVo getTelaAjudaVo()
	{
		if (telaAjudaVo == null)
		{
			setTelaAjudaVo(new TelaAjudaVo());
			return telaAjudaVo;
		}
		else
		{
			return telaAjudaVo;
		}
	}

	/**
	 * Atribui uma Descrição Ajuda Campo.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setDescricaoAjudaCampo(String descricaoAjudaCampo)
	{
		if (Validador.isStringValida(descricaoAjudaCampo))
		{
			this.descricaoAjudaCampo = descricaoAjudaCampo.trim().toUpperCase();
		}
		else
		{
			this.descricaoAjudaCampo = descricaoAjudaCampo;
		}
	}

	/**
	 * Retorna uma Descrição Ajuda Campo.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposTelaCampoAjuda.CAMPO_DESCRICAO_AJUDA_CAMPO
	     ,obrigatorio = true
	 )
	public String getDescricaoAjudaCampo()
	{
		if (Validador.isStringValida(descricaoAjudaCampo))
		{
			return descricaoAjudaCampo;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * Atribui um status da tela campo ajuda.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setStatusTelaCampo(DomnStatusRegistro statusTelaCampo)
	{
		this.statusTelaCampo = statusTelaCampo;
	}

	/**
	 * Retorna um status da tela campo ajuda.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposTelaCampoAjuda.CAMPO_STATUS_TELA_CAMPO_AJUDA
	     ,obrigatorio = true
	 )
	public DomnStatusRegistro getStatusTelaCampo()
	{
		if (!Validador.isDominioNumericoValido(statusTelaCampo))
		{
			setStatusTelaCampo(new DomnStatusRegistro(-1));
		}
		return statusTelaCampo;
	}

	/**
	 * Atribui a Data de Atualização BD
	 * @param dataAtualizacaoBD
	 * @implemented by Daniel Balieiro
	 */
	public void setDataAtualizacaoBD(Date dataAtualizacaoBD)
	{
		this.dataAtualizacaoBD = dataAtualizacaoBD;
	}

	/**
	 * Retorna a Data de Atualização BD
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposTelaCampoAjuda.CAMPO_DATA_ATUALIZACAO_BD
	     ,obrigatorio = true
		  ,timestamp = true
	 )
	public Date getDataAtualizacaoBD()
	{
		return dataAtualizacaoBD;
	}
	
	/**
	 * Retorna a Data de Atualização Formatada
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public String getDataAtualizacaoBDFormatada() 
	{
		if(Validador.isDataValida(dataAtualizacaoBD))
		{
			return new SefazDataHora(dataAtualizacaoBD).formata(MASCARA_DATA_FORMATADA);
		}
		else 
		{
			return "";
		}
	}

	public void setCodigoOrdenacao(int codigoOrdenacao)
	{
		this.codigoOrdenacao = codigoOrdenacao;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposTelaCampoAjuda.CAMPO_CODIGO_ORDENACAO
	    ,obrigatorio = false
	)
	public int getCodigoOrdenacao()
	{
		return codigoOrdenacao;
	}
	
	public boolean isExiste()
	{
		return Validador.isNumericoValido(getCampoAjudaVo().getCodigo()) && Validador.isNumericoValido(getTelaAjudaVo().getCodigo());
	}
}
