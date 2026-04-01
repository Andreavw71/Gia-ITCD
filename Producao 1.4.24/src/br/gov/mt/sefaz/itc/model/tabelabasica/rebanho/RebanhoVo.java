/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: RebanhoVo.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 03/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.rebanho;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposRebanho;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Date;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores do objeto Rebanho (Value Object).
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_REBANHO
     ,nomeAmigavel = "Rebanho"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposRebanho.CAMPO_CODIGO_REBANHO
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_REBANHO
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class RebanhoVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private Date dataAtualizacao;
	private DomnStatusRegistro statusRebanho;
	private String descricaoRebanho;

	/**
	 * Construtor da classe.
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public RebanhoVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor vazio.
	 * @implemented by Daniel Balieiro
	 */
	public RebanhoVo()
	{
		super();
	}

	/**
	 * Construtor que recebe um Parametro de Consulta.
	 * @param rebanhoVo Rebanho (Value Object)
	 * @implemented by Daniel Balieiro
	 */
	public RebanhoVo(RebanhoVo rebanhoVo)
	{
		super();
		setParametroConsulta(rebanhoVo);
	}

	/**
	 * Construtor que recebe uma Collection.
	 * @param collVo Coleção de Value Objects.
	 * @implemented by Daniel Balieiro
	 */
	public RebanhoVo(Collection collVo)
	{
		super(collVo);
	}

	/**
	 * Retorna a chave primária (PK).
	 * @return br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoPK
	 * @implemented by Daniel Balieiro
	 */
	public RebanhoPK getPk()
	{
		return new RebanhoPK(getCodigo());
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Retorna a descrição da rebanho.
	 * @return String
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposRebanho.CAMPO_DESCRICAO_REBANHO
	     ,obrigatorio = true
	 )
	public String getDescricaoRebanho()
	{
		if (!Validador.isStringValida(descricaoRebanho))
		{
			setDescricaoRebanho(STRING_VAZIA);			
		}
		return descricaoRebanho;
	}

	/**
	 * Atribui uma descrição à rebanho.	
	 * @param descricaoRebanho
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
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
	 * Retorna o Status da Rebanho.
	 * @return DomnStatusRegistro
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Mottta
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposRebanho.CAMPO_STATUS_REBANHO
	     ,obrigatorio = true
	 )
	public DomnStatusRegistro getStatusRebanho()
	{
		if (!Validador.isDominioNumericoValido(statusRebanho))
		{
			setStatusRebanho(new DomnStatusRegistro(-1));
		}
		return statusRebanho;
	}

	/**
	 * Atribui um Status à Rebanho.
	 * @param statusRebanho
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void setStatusRebanho(DomnStatusRegistro statusRebanho)
	{
		this.statusRebanho = statusRebanho;
	}

	/**
	 * Atribui valor da Data Atualicação.
	 * @param dataAtualizacao
	 * @implemented by Daniel Balieiro
	 */
	public void setDataAtualizacao(Date dataAtualizacao)
	{
		this.dataAtualizacao = dataAtualizacao;
	}

	/**
	 * Retorna Data Atualização.
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposRebanho.CAMPO_DATA_ATUALIZACAO_BD
	     ,obrigatorio = true
		  , timestamp = true
	 )
	public Date getDataAtualizacao()
	{
		return dataAtualizacao;
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
