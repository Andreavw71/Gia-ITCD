/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CampoAjudaVo.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: CampoAjudaVo.java,v 1.1.1.1 2008/05/28 17:55:05 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.campoajuda;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposCampoAjuda;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;


/**
 * Representa a entidade do campo da ajuda.
 * @author Thiago de Castilho Pacheco
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_CAMPO_AJUDA
     ,nomeAmigavel = "Campo Ajuda"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposCampoAjuda.CAMPO_CODIGO_CAMPO_AJUDA
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_CAMPO_AJUDA
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class CampoAjudaVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private String descricaoRotulo;

	/**
	 * Construtor da classe.
	 * @param codigo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public CampoAjudaVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor Vazio.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public CampoAjudaVo()
	{
		super();
	}

	/**
	 * Construtor que recebe um Parametro de Consulta.
	 * @param campoAjudaVo CampoAjuda (Value Object).
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public CampoAjudaVo(CampoAjudaVo campoAjudaVo)
	{
		super();
		setParametroConsulta(campoAjudaVo);
	}

	/**
	 * Construtor que recebe uma Collection.
	 * @param collVo Coleção de Value Objects.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public CampoAjudaVo(Collection collVo)
	{
		super(collVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui uma descrição rotulo.
	 * @param descricaoRotulo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setDescricaoRotulo(String descricaoRotulo)
	{
		if (Validador.isStringValida(descricaoRotulo))
		{
			this.descricaoRotulo = descricaoRotulo.trim().toUpperCase();
		}
		else
		{
			this.descricaoRotulo = descricaoRotulo;
		}
	}

	/**
	 * Retorna a descrição do rotulo.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposCampoAjuda.CAMPO_DESCRICAO_ROTULO
	     ,obrigatorio = true
	 )
	public String getDescricaoRotulo()
	{
		if (Validador.isStringValida(descricaoRotulo))
		{
			return descricaoRotulo;
		}
		else
		{
			return STRING_VAZIA;
		}
	}
}
