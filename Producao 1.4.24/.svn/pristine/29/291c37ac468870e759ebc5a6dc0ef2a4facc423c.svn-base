/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BenfeitoriaVo.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 09/10/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBenfeitoria;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Date;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores do objeto Benfeitoria (Value Object).
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_BENFEITORIA
     ,nomeAmigavel = "Benfeitoria"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposBenfeitoria.CAMPO_CODIGO_BENFEITORIA
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_BENFEITORIA 
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class BenfeitoriaVo extends EntidadeVo
{
	private Date dataAtualizacao;
	private DomnStatusRegistro statusBenfeitoria;
	private static final long serialVersionUID = Long.MAX_VALUE;
	private String descricaoBenfeitoria;

	/**
	 * Construtor da classe.
	 * @param codigo Chave primária.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public BenfeitoriaVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor Vazio.
	 * @implemented by Daniel Balieiro
	 */
	public BenfeitoriaVo()
	{
		super();
	}

	/**.
	 * Construtor que recebe um Parametro de Consulta.
	 * @param benfeitoriaVo
	 * @implemented by Daniel Balieiro
	 */
	public BenfeitoriaVo(BenfeitoriaVo benfeitoriaVo)
	{
		super();
		setParametroConsulta(benfeitoriaVo);
	}

	/**
	 * Construtor que recebe uma Collection
	 * @param collVo Coleção de Value Objects.
	 * @implemented by Daniel Balieiro
	 */
	public BenfeitoriaVo(Collection collVo)
	{
		super(collVo);
	}

	/**
	 * Retorna a chave primária (PK).
	 * @return br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoriaVo.BenfeitoriaPK
	 * @implemented by Daniel Balieiro
	 */
	public BenfeitoriaPK getPk()
	{
		return new BenfeitoriaPK(getCodigo());
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Retorna a descrição da benfeitoriaVo.
	 * @return
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposBenfeitoria.CAMPO_DESCRICAO_BENFEITORIA
	     ,obrigatorio = true
	 )
	public String getDescricaoBenfeitoria()
	{
		if (Validador.isStringValida(descricaoBenfeitoria))
		{
			return descricaoBenfeitoria;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * Atribui uma descrição à benfeitoriaVo.	
	 * @param descricaoBenfeitoria Descrição da Benfeitoria.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void setDescricaoBenfeitoria(String descricaoBenfeitoria)
	{
		if (Validador.isStringValida(descricaoBenfeitoria))
		{
			this.descricaoBenfeitoria = descricaoBenfeitoria.trim().toUpperCase();
		}
		else
		{
			this.descricaoBenfeitoria = descricaoBenfeitoria;
		}
	}

	/**
	 * Retorna o Status da Benfeitoria.
	 * @return
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Mottta
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposBenfeitoria.CAMPO_STATUS_BENFEITORIA
	     ,obrigatorio = true
	 )
	public DomnStatusRegistro getStatusBenfeitoria()
	{
		if (!Validador.isDominioNumericoValido(statusBenfeitoria))
		{
			setStatusBenfeitoria(new DomnStatusRegistro(-1));
		}
		return statusBenfeitoria;
	}

	/**
	 * Atribui um Status à Benfeitoria.
	 * @param statusBenfeitoria Status da Benfeitoria (DomnStatusRegistro).
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void setStatusBenfeitoria(DomnStatusRegistro statusBenfeitoria)
	{
		this.statusBenfeitoria = statusBenfeitoria;
	}

	/**
	 * Atribui valor da Data Atualicação.
	 * @param dataAtualizacao Data de atualização do registro no banco de dados.
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
	     nomeColuna = CamposBenfeitoria.CAMPO_DATA_ATUALIZACAO_BD
	     ,obrigatorio = true
		  ,timestamp = true
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
			return "";
		}
	}
}
