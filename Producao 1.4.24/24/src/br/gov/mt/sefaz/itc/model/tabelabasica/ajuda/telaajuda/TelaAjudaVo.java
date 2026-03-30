/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: TelaAjudaVo.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: TelaAjudaVo.java,v 1.1.1.1 2008/05/28 17:55:06 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telaajuda;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda.TelaCampoAjudaVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposTelaAjuda;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;


/**
 * Classe responsável por encapsular os valores do objeto TelaAjuda (Value Object).
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_TELA_AJUDA
     ,nomeAmigavel = "Tela Ajuda"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposTelaAjuda.CAMPO_CODIGO_TELA_AJUDA
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_TELA_AJUDA
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class TelaAjudaVo extends EntidadeVo
{
	private static final  long serialVersionUID = Long.MAX_VALUE;
	private String descricaoTelaAjuda;
	private transient TelaCampoAjudaVo telaCampoAjudaVo;

	/**
	 * Construtor da classe.
	 * @param codigo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaAjudaVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor Vazio.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaAjudaVo()
	{
		super();
	}

	/**
	 * Construtor que recebe um Parametro de Consulta.
	 * @param telaAjudaVo Tela Ajuda (Value Object).
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaAjudaVo(TelaAjudaVo telaAjudaVo)
	{
		super();
		setParametroConsulta(telaAjudaVo);
	}

	/**
	 * Construtor que recebe uma Collection.
	 * @param collVo Coleção de Value Objects.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaAjudaVo(Collection collVo)
	{
		super(collVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui uma descrição ao tela ajuda.
	 * @param descricaoTelaAjuda
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setDescricaoTelaAjuda(String descricaoTelaAjuda)
	{
		if (Validador.isStringValida(descricaoTelaAjuda))
		{
			this.descricaoTelaAjuda = descricaoTelaAjuda.trim().toUpperCase();
		}
		else
		{
			this.descricaoTelaAjuda = descricaoTelaAjuda;
		}
	}

	/**
	 * Retorna a descrição do tela ajuda.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposTelaAjuda.CAMPO_DESCRICAO_TELA_AJUDA
	     ,obrigatorio = true
	 )
	public String getDescricaoTelaAjuda()
	{
		if (Validador.isStringValida(descricaoTelaAjuda))
		{
			return descricaoTelaAjuda;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * Atribui um Tela Campo Ajuda.
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setTelaCampoAjudaVo(TelaCampoAjudaVo telaCampoAjudaVo)
	{
		this.telaCampoAjudaVo = telaCampoAjudaVo;
	}

	/**
	 * Retorna um Tela Campo Ajuda.
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaCampoAjudaVo getTelaCampoAjudaVo()
	{
		if (telaCampoAjudaVo == null)
		{
			setTelaCampoAjudaVo(new TelaCampoAjudaVo());
			return telaCampoAjudaVo;
		}
		else
		{
			return telaCampoAjudaVo;
		}
	}
}
