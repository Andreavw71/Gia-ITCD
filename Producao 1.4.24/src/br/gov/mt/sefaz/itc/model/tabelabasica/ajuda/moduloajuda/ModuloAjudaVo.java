/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ModuloAjudaVo.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.moduloajuda;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposModuloAjuda;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;


/**
 * Classe responsável por encapsular os valores do objeto ModuloAjuda (Value Object).
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_MODULO_AJUDA
     ,nomeAmigavel = "Módulo Ajuda"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposModuloAjuda.CAMPO_CODIGO_MODULO_AJUDA
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_MODULO_AJUDA
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class ModuloAjudaVo extends EntidadeVo
{
	private DomnStatusRegistro statusModuloAjuda;
	private long codigoOrdenacao;
	private static final long serialVersionUID = Long.MAX_VALUE;
	private ModuloAjudaVo subModuloAjuda;
	private String descricaoModuloAjuda;

	/**
	 * Construtor da classe.
	 * @param codigo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ModuloAjudaVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor Vazio.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ModuloAjudaVo()
	{
		super();
	}

	/**
	 * Construtor que recebe um Parametro de Consulta.
	 * @param telaAjudaVo Tela Ajuda (Value Object).
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ModuloAjudaVo(ModuloAjudaVo moduloAjudaVo)
	{
		super();
		setParametroConsulta(moduloAjudaVo);
	}

	/**
	 * Construtor que recebe uma collection.
	 * @param collVo Coleção de Value Objects.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ModuloAjudaVo(Collection collVo)
	{
		super(collVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui um código ao submódulo.
	 * @param subModuloAjuda
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setSubModuloAjuda(ModuloAjudaVo subModuloAjuda)
	{
		this.subModuloAjuda = subModuloAjuda;
	}

	/**
	 * Retorna o código do submódulo.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = false
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposModuloAjuda.CAMPO_CODIGO_SUBMODULO_AJUDA
	             , nomeAtributo = "codigo"
	         )
	     }
	 )
	public ModuloAjudaVo getSubModuloAjuda()
	{
		if (subModuloAjuda == null)
		{
			setSubModuloAjuda(new ModuloAjudaVo());
			return subModuloAjuda;
		}
		else
		{
			return subModuloAjuda;
		}
	}

	/**
	 * Atribui a descrição ao módulo ajuda.
	 * @param descricaoModuloAjuda
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setDescricaoModuloAjuda(String descricaoModuloAjuda)
	{
		if (Validador.isStringValida(descricaoModuloAjuda))
		{
			this.descricaoModuloAjuda = descricaoModuloAjuda.trim().toUpperCase();
		}
		else
		{
			this.descricaoModuloAjuda = descricaoModuloAjuda;
		}
	}

	/**
	 * Retorna a descrição do módulo ajuda.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposModuloAjuda.CAMPO_DESCRICAO_MODULO_AJUDA
	     ,obrigatorio = true
	 )
	public String getDescricaoModuloAjuda()
	{
		if (Validador.isStringValida(descricaoModuloAjuda))
		{
			return descricaoModuloAjuda;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * Atribui um status ao módulo ajuda.
	 * @param statusModuloAjuda
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setStatusModuloAjuda(DomnStatusRegistro statusModuloAjuda)
	{
		this.statusModuloAjuda = statusModuloAjuda;
	}

	/**
	 * Retorna um status do módulo ajuda.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposModuloAjuda.CAMPO_STATUS_MODULO_AJUDA
	     ,obrigatorio = true
	 )
	public DomnStatusRegistro getStatusModuloAjuda()
	{
		if (!Validador.isDominioNumericoValido(statusModuloAjuda))
		{
			setStatusModuloAjuda(new DomnStatusRegistro(-1));
		}
		return statusModuloAjuda;
	}

	/**
	 * Atribui um código para ordenação.
	 * @param codigoOrdenacao
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setCodigoOrdenacao(long codigoOrdenacao)
	{
		this.codigoOrdenacao = codigoOrdenacao;
	}

	/**
	 * Retorna o código da ordenação.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposModuloAjuda.CAMPO_CODIGO_ORDENACAO
	     ,obrigatorio = true
	 )
	public long getCodigoOrdenacao()
	{
		return codigoOrdenacao;
	}
}
