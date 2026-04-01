/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: MultaDeMoraVo.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 12/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.multademora;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposMultaMora;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Date;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores do objeto Multa de Mora (Value Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_MULTA_MORA
     ,nomeAmigavel = "Multa de Mora"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposMultaMora.CAMPO_CODIGO_MULTA_MORA
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_MULTA_MORA
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class MultaDeMoraVo extends EntidadeVo
{
	private Date dataAtualizacao;
	private DomnStatusRegistro statusMultaMora;
	private double percentualMaximoMultaMora;
	private double percentualMultaMora;
	private static final long serialVersionUID = Long.MAX_VALUE;

	/**
	 * Construtor Padrão.
	 * @implemented by Daniel Balieiro
	 */
	public MultaDeMoraVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a chave primária
	 * @param codigo Chave primária.
	 * @implemented by Daniel Balieiro
	 */
	public MultaDeMoraVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe um parametro de consulta.
	 * @param multaDeMoraVo Multa de Mora (Value Object).
	 * @implemented by Daniel Balieiro
	 */
	public MultaDeMoraVo(MultaDeMoraVo multaDeMoraVo)
	{
		super();
		setParametroConsulta(multaDeMoraVo);
	}

	/**
	 * Construtor que recebe uma coleção.
	 * @param collVo Coleção de Value Objects.
	 * @implemented by Daniel Balieiro
	 */
	public MultaDeMoraVo(Collection collVo)
	{
		super(collVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui o Tipo da Multa Mora.
	 * @param tipoMultaMora
	 * @implemented by Daniel Balieiro
	 */
	public void setPercentualMultaMora(double tipoMultaMora)
	{
		this.percentualMultaMora = tipoMultaMora;
	}

	/**
	 * Retorna o Tipo da Multa Mora.
	 * @return int
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposMultaMora.CAMPO_PERCENTUAL_MULTA_MORA
	     ,obrigatorio = true
	 )
	public double getPercentualMultaMora()
	{
		return percentualMultaMora;
	}

	/**
	 * Atribui o Percentual da Multa Mora.
	 * @param percentualMultaMora
	 * @implemented by Daniel Balieiro
	 */
	public void setPercentualMaximoMultaMora(double percentualMultaMora)
	{
		this.percentualMaximoMultaMora = percentualMultaMora;
	}

	/**
	 * Retorna o Percentual da Multa Mora.
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposMultaMora.CAMPO_PERCENTUAL_MAXIMO_MULTA_MORA
	     ,obrigatorio = true
	 )
	public double getPercentualMaximoMultaMora()
	{
		return percentualMaximoMultaMora;
	}

	/**
	 * Atribui um Status da Multa Mora.
	 * @param statusMultaMora
	 * @implemented by Daniel Balieiro
	 */
	public void setStatusMultaMora(DomnStatusRegistro statusMultaMora)
	{
		this.statusMultaMora = statusMultaMora;
	}

	/**
	 * Retorna o Status da Multa Mora.
	 * @return DomnStatusRegistro
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposMultaMora.CAMPO_STATUS_MULTA_MORA
	     ,obrigatorio = true
	 )
	public DomnStatusRegistro getStatusMultaMora()
	{
		if (!Validador.isDominioNumericoValido(statusMultaMora))
		{
			setStatusMultaMora(new DomnStatusRegistro(-1));
		}
		return statusMultaMora;
	}

	/**
	 * Atribui a Data de Atualização.
	 * @param dataAtualizacao
	 * @implemented by Daniel Balieiro
	 */
	public void setDataAtualizacao(Date dataAtualizacao)
	{
		this.dataAtualizacao = dataAtualizacao;
	}

	/**
	 * Retorna a Data de Atualizacao.
	 * @return Date
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposMultaMora.CAMPO_DATA_ATUALIZACAO_BD
	     ,obrigatorio = true
		  ,timestamp = true
	 )
	public Date getDataAtualizacao()
	{
		return dataAtualizacao;
	}
	
	/**
	 * Retorna Chave Primária.
	 * @return MultaDeMoraPk
	 * @implemented by Daniel Balieiro
	 */
	public MultaDeMoraPk getPk()
	{
		return new MultaDeMoraPk(getCodigo());
	}
	
	/**
	 * Retorna Data Atualização.
	 * @return
	 * @implemented by Daniel Balieiro
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

	public int getQuantidadeCasasDecimais()
	{
		return 3;
	}
}
