/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConfiguracaoGerencialParametrosVo.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 06/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoConfiguracaoGerencialParametros;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposConfiguracaoGerencialParametros;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Date;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores do objeto Configuração Gerencial Parametros (Value Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_CONFIGURACAO_GERENCIAL_PARAMETROS
     ,nomeAmigavel = "Configuração Gerencial de Parâmetros"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposConfiguracaoGerencialParametros.CAMPO_CODIGO_CONFIGURACAO_GERENCIAL_PARAMETROS
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_CONFIGURACAO_GERENCIAL_PARAMETROS
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class ConfiguracaoGerencialParametrosVo extends EntidadeVo
{
	private Date dataAtualizacao;
	private DomnSimNao statusCadastrado;
	private DomnTipoConfiguracaoGerencialParametros tipoItem;
	private static final long serialVersionUID = Long.MAX_VALUE;
	private String descricaoItem;
	private String valorItem;

	/**
	 * Construtor vazio.
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a chave primária.
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe um parametro de consulta.
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosVo(ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo)
	{
		super();
		setParametroConsulta(configuracaoGerencialParametrosVo);
	}

	/**
	 * Construtor que recebe uma coleção.
	 * @param collVo Coleção de Value Objects.
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosVo(Collection collVo)
	{
		super(collVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui um Valor Item.
	 * @param valorItem
	 * @implemented by Daniel Balieiro
	 */
	public void setValorItem(String valorItem)
	{
		this.valorItem = valorItem;
	}

	/**
	 * Retorna o Valor Item.
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposConfiguracaoGerencialParametros.CAMPO_VALOR_ITEM
	     ,obrigatorio = false
	 )
	public String getValorItem()
	{
		if (!Validador.isStringValida(valorItem))
		{
			return STRING_VAZIA;
		}
		return valorItem;
	}
	
	public String getValorItemFormatado()
	{
		return getValorItem();
	}

	/**
	 * Atribui uma Descrição Item.
	 * @param descricaoItem
	 * @implemented by Daniel Balieiro
	 */
	public void setDescricaoItem(String descricaoItem)
	{
		if (Validador.isStringValida(descricaoItem))
		{
			this.descricaoItem = descricaoItem.trim().toUpperCase();
		}
		else
		{
			this.descricaoItem = descricaoItem;
		}
	}

	/**
	 * Retorna a Descrição Item.
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposConfiguracaoGerencialParametros.CAMPO_DESCRICAO_ITEM
	     ,obrigatorio = true
	 )
	public String getDescricaoItem()
	{
		if (!Validador.isStringValida(descricaoItem))
		{
			return STRING_VAZIA;
		}
		return descricaoItem;
	}

	/**
	 * Atribui um Tipo Item.
	 * @param tipoItem
	 * @implemented by Daniel Balieiro
	 */
	public void setTipoItem(DomnTipoConfiguracaoGerencialParametros tipoItem)
	{
		this.tipoItem = tipoItem;
	}

	/**
	 * Retorna o Tipo Item.
	 * @return DomnTipoConfiguracaoGerencialParametros
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposConfiguracaoGerencialParametros.CAMPO_TIPO_ITEM
	     ,obrigatorio = true
	 )
	public DomnTipoConfiguracaoGerencialParametros getTipoItem()
	{
		if (!Validador.isDominioNumericoValido(tipoItem))
		{
			setTipoItem(new DomnTipoConfiguracaoGerencialParametros(-1));
		}
		return tipoItem;
	}

	/**
	 * Atribui uma Data de Atualização.
	 * @param dataAtualizacao
	 * @implemented by Daniel Balieiro
	 */
	public void setDataAtualizacao(Date dataAtualizacao)
	{
		this.dataAtualizacao = dataAtualizacao;
	}

	/**
	 * Retorna a Data de Atualização.
	 * @return Date
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposConfiguracaoGerencialParametros.CAMPO_DATA_ATUALIZACAO_BD
	     ,obrigatorio = true
		  ,timestamp = true
	 )
	public Date getDataAtualizacao()
	{
		return dataAtualizacao;
	}
	
	/**
	 * Atribui o Status Cadastrado.
	 * @param statusCadastrado
	 * @implemented by Daniel Balieiro
	 */
	public void setStatusCadastrado(DomnSimNao statusCadastrado)
	{
		this.statusCadastrado = statusCadastrado;
	}

	/**
	 * Retorna o Status Cadastrado.
	 * @return DomnSimNao
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposConfiguracaoGerencialParametros.CAMPO_STATUS_CADASTRADO
	     ,obrigatorio = true
	 )
	public DomnSimNao getStatusCadastrado()
	{
		if (!Validador.isDominioNumericoValido(statusCadastrado))
		{
			setStatusCadastrado(new DomnSimNao(-1));
		}
		return statusCadastrado;
	}

	/**
	 * Retorna a chave primária.
	 * @return ConfiguracaoGerencialParametrosPk
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosPk getPk()
	{
		return new ConfiguracaoGerencialParametrosPk(getCodigo());
	}
	
		/**
	 * Método utilizado para retornar a data de atualização formatada no formato dia/mês/ano.
	 * Retorna uma string contendo a data formatada em formato dia / mês / ano. Ex.: 27/02/2007 ou uma string vazia caso não seja uma data válida.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	public String getDataAtualizacaoFormatada()
	{
		if (Validador.isDataValida(getDataAtualizacao()))
		{
			return new SefazDataHora(getDataAtualizacao()).formata("dd/MM/yyyy");
		}
		else
		{
			return STRING_VAZIA;
		}
	}
}
