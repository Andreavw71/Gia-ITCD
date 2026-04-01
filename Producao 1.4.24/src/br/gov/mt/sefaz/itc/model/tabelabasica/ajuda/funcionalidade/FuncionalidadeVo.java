/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: FuncionalidadeVo.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: FuncionalidadeVo.java,v 1.1.1.1 2008/05/28 17:55:06 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.funcionalidade;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.moduloajuda.ModuloAjudaVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFuncionalidade;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;


/**
 * Classe responsável por encapsular os valores do objeto Funcionalidade (Value Object).
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_FUNCIONALIDADE
     ,nomeAmigavel = "Funcionalidade"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposFuncionalidade.CAMPO_CODIGO_FUNCIONALIDADE
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_FUNCIONALIDADE
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class FuncionalidadeVo extends EntidadeVo
{
	private DomnStatusRegistro statusFuncionalidade;
	private static final long serialVersionUID = Long.MAX_VALUE;
	private ModuloAjudaVo moduloAjudaVo;
	private String descricaoFuncionalidade;

	/**
	 * Construtor da classe.
	 * @param codigo Chave primária.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public FuncionalidadeVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor vazio.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public FuncionalidadeVo()
	{
		super();
	}

	/**
	 * Construtor que recebe um Parametro de Consulta.
	 * @param funcionalidadeVo Funcionalidade (Value Object).
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public FuncionalidadeVo(FuncionalidadeVo funcionalidadeVo)
	{
		super();
		setParametroConsulta(funcionalidadeVo);
	}

	/**
	 * Construtor que recebe uma Collection.
	 * @param collVo Coleção de Value Objects.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public FuncionalidadeVo(Collection collVo)
	{
		super(collVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui um código ao módulo ajuda.
	 * @param moduloAjudaVo Módulo Ajuda (Value Object).
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setModuloAjudaVo(ModuloAjudaVo moduloAjudaVo)
	{
		this.moduloAjudaVo = moduloAjudaVo;
	}

	/**
	 * Retorna um módulo ajuda.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = true
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposFuncionalidade.CAMPO_CODIGO_MODULO_AJUDA
	             , nomeAtributo = "codigo"
	         )
	     }
	 )
	public ModuloAjudaVo getModuloAjudaVo()
	{
		if (moduloAjudaVo == null)
		{
			setModuloAjudaVo(new ModuloAjudaVo());
			return moduloAjudaVo;
		}
		else
		{
			return moduloAjudaVo;
		}
	}

	/**
	 * Atribui uma descrição da funcionalidade.
	 * @param descricaoFuncionalidade
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setDescricaoFuncionalidade(String descricaoFuncionalidade)
	{
		if (Validador.isStringValida(descricaoFuncionalidade))
		{
			this.descricaoFuncionalidade = descricaoFuncionalidade.trim().toUpperCase();
		}
		else
		{
			this.descricaoFuncionalidade = descricaoFuncionalidade;
		}
	}

	/**
	 * Retorna uma descrição da funcionalidade.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFuncionalidade.CAMPO_DESCRICAO_FUNCIONALIDADE
	     ,obrigatorio = true
	 )
	public String getDescricaoFuncionalidade()
	{
		if (Validador.isStringValida(descricaoFuncionalidade))
		{
			return descricaoFuncionalidade;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * Atribui um status da funcionalidade.
	 * @param statusFuncionalidade
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setStatusFuncionalidade(DomnStatusRegistro statusFuncionalidade)
	{
		this.statusFuncionalidade = statusFuncionalidade;
	}

	/**
	 * Retorna um status da funcionalidade.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFuncionalidade.CAMPO_STATUS_FUNCIONALIDADE
	     ,obrigatorio = true
	 )
	public DomnStatusRegistro getStatusFuncionalidade()
	{
		if (!Validador.isDominioNumericoValido(statusFuncionalidade))
		{
			setStatusFuncionalidade(new DomnStatusRegistro(-1));
		}
		return statusFuncionalidade;
	}
}
