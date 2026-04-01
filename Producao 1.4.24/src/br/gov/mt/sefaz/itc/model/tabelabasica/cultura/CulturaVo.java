/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CulturaVo.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 01/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.cultura;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposCultura;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Date;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores do objeto Cultura (Value Object).
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_CULTURA
     ,nomeAmigavel = "Cultura"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposCultura.CAMPO_CODIGO_CULTURA
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_CULTURA
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class CulturaVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private Date dataAtualizacao;
	private DomnStatusRegistro statusCultura;
	private String descricaoCultura;

	/**
	 * Construtor Padrão
	 * @implemented by Daniel Balieiro
	 */
	public CulturaVo()
	{
		super();
	}

	/**
	 * Construtor da classe.
	 * @param <b>codigo</b> Código identificador da Cultura (chave primária)
	 * @implemented by Marlo Eichenberg Motta
	 */
	public CulturaVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe um parâmetro de consulta.
	 * @param culturaVo parâmetro de consulta (Value Object)
	 * @implemented by Marlo Eichenberg Motta
	 */
	public CulturaVo(CulturaVo culturaVo)
	{
		super();
		setParametroConsulta(culturaVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui uma descrição a Cultura.
	 * @param descricaoCultura Descrição da Cultura
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void setDescricaoCultura(String descricaoCultura)
	{
		if (Validador.isStringValida(descricaoCultura))
		{
			this.descricaoCultura = descricaoCultura.trim().toUpperCase();
		}
		else
		{
			this.descricaoCultura = descricaoCultura;
		}
	}

	/**
	 * Retorna a descrição da cultura. Caso não seja uma String Válida, retorna uma string vazia.
	 * @return
	 * @see br.com.abaco.util.Validador#isStringValida
	 * @see br.com.abaco.util.facade.EntidadeFacade#STRING_VAZIA
	 * @implemented by Marlo Eichenberg Motta
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposCultura.CAMPO_DESCRICAO_CULTURA
	     ,obrigatorio = true
	 )
	public String getDescricaoCultura()
	{
		if (Validador.isStringValida(descricaoCultura))
		{
			return descricaoCultura;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * Atribui um status à cultura.
	 * @param statusCultura Status da Cultura
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void setStatusCultura(DomnStatusRegistro statusCultura)
	{
		this.statusCultura = statusCultura;
	}

	/**
	 * Retorna o status da cultura.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposCultura.CAMPO_STATUS_CULTURA
	     ,obrigatorio = true
	 )
	public DomnStatusRegistro getStatusCultura()
	{
		if (Validador.isDominioNumericoValido(statusCultura))
		{
			return statusCultura;
		}
		else
		{
			setStatusCultura(new DomnStatusRegistro(-1));
			return statusCultura;
		}
	}

	/**
	 * Atribui uma data de atualização à cultura.
	 * @param dataAtualizacao Data de atualização.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void setDataAtualizacao(Date dataAtualizacao)
	{
		this.dataAtualizacao = dataAtualizacao;
	}

	/**
	 * Retorna uma data de atualização.
	 * @return java.util.Date Data de altualização
	 * @implemented by Marlo Eichenberg Motta
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposCultura.CAMPO_DATA_ATUALIZACAO_BD
	     ,obrigatorio = true
		  ,timestamp = true
	 )
	public Date getDataAtualizacao()
	{
		return dataAtualizacao;
	}
	
	/**
	 * Retorna a Data de Atualização Formatada
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
			return "";
		}
	}
}
