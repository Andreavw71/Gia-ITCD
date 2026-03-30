/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: TelaFuncionalidadeVo.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: TelaFuncionalidadeVo.java,v 1.1.1.1 2008/05/28 17:55:06 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telafuncionalidade;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.funcionalidade.FuncionalidadeVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telaajuda.TelaAjudaVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposTelaFuncionalidade;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Date;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores do objeto TelaFuncionalidade (Value Object).
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_TELA_FUNCIONALIDADE
     ,nomeAmigavel = "Tela Funcionalidade"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "telaAjudaVo.codigo"
			    ,nomeColuna = CamposTelaFuncionalidade.CAMPO_CODIGO_TELA_AJUDA
			)
			,@AnotacaoIdentificador
			(
			    nomeAtributo = "funcionalidadeVo.codigo"
			    ,nomeColuna = CamposTelaFuncionalidade.CAMPO_CODIGO_FUNCIONALIDADE
			)
     }
 )
public class TelaFuncionalidadeVo extends EntidadeVo
{
	private DomnStatusRegistro statusTelaFuncionalidade;
	private FuncionalidadeVo funcionalidadeVo;
	private static final long serialVersionUID = Long.MAX_VALUE;
	private String descricaoTelaFuncionalidade;
	private String informacaoTituloTelaFuncionalidade;
	private TelaAjudaVo telaAjudaVo;
	private Date dataAtualizacaoBD;
	private int codigoOrdenacao;

	/**
	 * Atributos sem persistência
	 */
	private int posicaoAtualRegistro = 0;

	/**
	 * Construtor da classe.
	 * @param codigo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaFuncionalidadeVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor vazio.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaFuncionalidadeVo()
	{
		super();
	}

	/**
	 * Construtor que recebe um Parametro Consulta.
	 * @param telaFuncionalidadeVo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaFuncionalidadeVo(TelaFuncionalidadeVo telaFuncionalidadeVo)
	{
		super();
		setParametroConsulta(telaFuncionalidadeVo);
	}

	/**
	 * Construtor que recebe uma Collection.
	 * @param collVo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaFuncionalidadeVo(Collection collVo)
	{
		super(collVo);
	}
	
	public boolean isExiste()
	{
		return Validador.isNumericoValido(getFuncionalidadeVo().getCodigo()) && Validador.isNumericoValido(getTelaAjudaVo().getCodigo());
	}

	/**
	 * Atribui uma funcionalidade.
	 * @param funcionalidadeVo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setFuncionalidadeVo(FuncionalidadeVo funcionalidadeVo)
	{
		this.funcionalidadeVo = funcionalidadeVo;
	}

	/**
	 * Retorna uma funcionalidade.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public FuncionalidadeVo getFuncionalidadeVo()
	{
		if (funcionalidadeVo == null)
		{
			setFuncionalidadeVo(new FuncionalidadeVo());
			return funcionalidadeVo;
		}
		else
		{
			return funcionalidadeVo;
		}
	}

	/**
	 * Atribui um tela ajuda.
	 * @param telaAjudaVo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setTelaAjudaVo(TelaAjudaVo telaAjudaVo)
	{
		this.telaAjudaVo = telaAjudaVo;
	}

	/**
	 * Retorna um Tela Ajuda.
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
	 * Atribui uma Descrição Tela Funcionalidade.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setDescricaoTelaFuncionalidade(String descricaoTelaFuncionalidade)
	{
		if (Validador.isStringValida(descricaoTelaFuncionalidade))
		{
			this.descricaoTelaFuncionalidade = descricaoTelaFuncionalidade.trim().toUpperCase();
		}
		else
		{
			this.descricaoTelaFuncionalidade = descricaoTelaFuncionalidade;
		}
	}

	/**
	 * Retorna uma Descrição Tela Funcionalidade.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposTelaFuncionalidade.CAMPO_DESCRICAO_TELA_FUNCIONALIDADE
	     ,obrigatorio = false
	 )
	public String getDescricaoTelaFuncionalidade()
	{
		if (Validador.isStringValida(descricaoTelaFuncionalidade))
		{
			return descricaoTelaFuncionalidade;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * Atribui uma Informação Titulo Tela Funcionalidade.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setInformacaoTituloTelaFuncionalidade(String informacaoTituloTelaFuncionalidade)
	{
		if (Validador.isStringValida(informacaoTituloTelaFuncionalidade))
		{
			this.informacaoTituloTelaFuncionalidade = informacaoTituloTelaFuncionalidade.trim().toUpperCase();
		}
		else
		{
			this.informacaoTituloTelaFuncionalidade = informacaoTituloTelaFuncionalidade;
		}
	}

	/**
	 * Retorna uma Informação Titulo Tela Funcionalidade.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposTelaFuncionalidade.CAMPO_INFORMACAO_TITULO_TELA_FUNCIONALIDADE
	     ,obrigatorio = false
	 )
	public String getInformacaoTituloTelaFuncionalidade()
	{
		if (Validador.isStringValida(informacaoTituloTelaFuncionalidade))
		{
			return informacaoTituloTelaFuncionalidade;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * Atribui um status da tela funcionalidade.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setStatusTelaFuncionalidade(DomnStatusRegistro statusTelaFuncionalidade)
	{
		this.statusTelaFuncionalidade = statusTelaFuncionalidade;
	}

	/**
	 * Retorna um status da tela funcionalidade.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposTelaFuncionalidade.CAMPO_STATUS_TELA_FUNCIONALIDADE
	     ,obrigatorio = false
	 )
	public DomnStatusRegistro getStatusTelaFuncionalidade()
	{
		if (!Validador.isDominioNumericoValido(statusTelaFuncionalidade))
		{
			setStatusTelaFuncionalidade(new DomnStatusRegistro(-1));
		}
		return statusTelaFuncionalidade;
	}

	/**
	 * Controla a posição do registros do CollVO que serão exibidos. validando posicao inicial e final.
	 * @param valor
	 * @implemented by Wendell Pereira de Farias
	 */
	public void setPosicaoAtualRegistro(int valor)
	{
		if ((valor < this.getCollVO().size()) && (valor >= 0))
		{
			this.posicaoAtualRegistro = valor;
		}
	}

	/**
	 * Retorna o valor da posição atual dos registros.
	 * @return
	 * @implemented by Wendell Pereira de Farias
	 */
	public int getPosicaoAtualRegistro()
	{
		return this.posicaoAtualRegistro;
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
	     nomeColuna = CamposTelaFuncionalidade.CAMPO_DATA_ATUALIZACAO_BD
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
	    nomeColuna = CamposTelaFuncionalidade.CAMPO_CODIGO_ORDENACAO
	    ,obrigatorio = false
	)
	public int getCodigoOrdenacao()
	{
		return codigoOrdenacao;
	}
}
