/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConstrucaoVo.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 12/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.construcao;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposConstrucao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Date;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores do objeto Construcao (Value Object).
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_CONSTRUCAO
     ,nomeAmigavel = "Contrução"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposConstrucao.CAMPO_CODIGO_CONSTRUCAO
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_CONSTRUCAO
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class ConstrucaoVo extends EntidadeVo
{
	private Date dataAtualizacao;
	private DomnStatusRegistro statusConstrucao;
	private static final long serialVersionUID = Long.MAX_VALUE;
	private String descricaoConstrucao;
	private DomnSimNao permiteFichaRural;
	private DomnSimNao permiteFichaUrbano;

	/**
	 * Construtor da classe.
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public ConstrucaoVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor Vazio.
	 * @implemented by Daniel Balieiro
	 */
	public ConstrucaoVo()
	{
		super();
	}

	/**
	 * Construtor que recebe um Parametro de Consulta.
	 * @param construcaoVo Construcao (Value Object).
	 * @implemented by Daniel Balieiro
	 */
	public ConstrucaoVo(ConstrucaoVo construcaoVo)
	{
		super();
		setParametroConsulta(construcaoVo);
	}

	/**
	 * Construtor que recebe uma Collection.
	 * @param collVo Coleção de Value Objects.
	 * @implemented by Daniel Balieiro
	 */
	public ConstrucaoVo(Collection collVo)
	{
		super(collVo);
	}

	/**
	 * Retorna a chave primária (PK).
	 * @return br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoPK
	 * @implemented by Daniel Balieiro
	 */
	public ConstrucaoPK getPk()
	{
		return new ConstrucaoPK(getCodigo());
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Retorna a descrição da Construção.
	 * @return String
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposConstrucao.CAMPO_DESCRICAO_CONSTRUCAO
	     ,obrigatorio = true
	 )
	public String getDescricaoConstrucao()
	{
		if (Validador.isStringValida(descricaoConstrucao))
		{
			return descricaoConstrucao;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * Atribui uma descrição à Construção.	
	 * @param descricaoConstrucao
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void setDescricaoConstrucao(String descricaoConstrucao)
	{
		if (Validador.isStringValida(descricaoConstrucao))
		{
			this.descricaoConstrucao = descricaoConstrucao.trim().toUpperCase();
		}
		else
		{
			this.descricaoConstrucao = descricaoConstrucao;
		}
	}

	/**
	 * Retorna o Status da Construção.
	 * @return DomnStatusRegistro
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Mottta
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposConstrucao.CAMPO_STATUS_CONSTRUCAO
	     ,obrigatorio = true
	 )
	public DomnStatusRegistro getStatusConstrucao()
	{
		if (!Validador.isDominioNumericoValido(statusConstrucao))
		{
			setStatusConstrucao(new DomnStatusRegistro(-1));
		}
		return statusConstrucao;
	}

	/**
	 * Atribui um Status à Construção.
	 * @param statusConstrucao
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void setStatusConstrucao(DomnStatusRegistro statusConstrucao)
	{
		this.statusConstrucao = statusConstrucao;
	}

	/**
	 * Atribui valor da Data Atualicação.
	 * @param dataAtualizacao
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposConstrucao.CAMPO_DATA_ATUALIZACAO_BD
	     ,obrigatorio = true
		  ,timestamp = true
	 )
	public void setDataAtualizacao(Date dataAtualizacao)
	{
		this.dataAtualizacao = dataAtualizacao;
	}

	/**
	 * Retorna Data Atualização.
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public Date getDataAtualizacao()
	{
		return dataAtualizacao;
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

	/**
	 * Atribui se permite ficha rural
	 * @param permiteFichaRural
	 * @implemented by Daniel Balieiro
	 */
	public void setPermiteFichaRural(DomnSimNao permiteFichaRural)
	{
		this.permiteFichaRural = permiteFichaRural;
	}

	/**
	 * Retorna se permite ficha rural
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposConstrucao.CAMPO_PERMITE_FICHA_RURAL
	     ,obrigatorio = false
	 )
	public DomnSimNao getPermiteFichaRural()
	{
		if (!Validador.isDominioNumericoValido(permiteFichaRural))
		{
			setPermiteFichaRural(new DomnSimNao(-1));
		}
		return permiteFichaRural;
	}

	/**
	 * Atribui se permite ficha urbano
	 * @param permiteFichaUrbano
	 * @implemented by Daniel Balieiro
	 */
	public void setPermiteFichaUrbano(DomnSimNao permiteFichaUrbano)
	{
		this.permiteFichaUrbano = permiteFichaUrbano;
	}

	/**
	 * Retorna se permite ficha urbano
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposConstrucao.CAMPO_PERMITE_FICHA_URBANO
	     ,obrigatorio = false
	 )
	public DomnSimNao getPermiteFichaUrbano()
	{
		if (!Validador.isDominioNumericoValido(permiteFichaUrbano))
		{
			setPermiteFichaUrbano(new DomnSimNao(-1));
		}
		return permiteFichaUrbano;
	}
}
