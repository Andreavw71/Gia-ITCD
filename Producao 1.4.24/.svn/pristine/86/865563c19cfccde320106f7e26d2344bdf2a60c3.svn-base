/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AvaliacaoBemTributavelServidorVo.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: AvaliacaoBemTributavelServidorVo.java,v 1.1.1.1 2008/05/28 17:55:04 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.avaliacao.servidor;

import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.Validador;
import br.com.abaco.util.facade.EntidadeFacade;

import br.gov.mt.sefaz.itc.model.generico.avaliacao.AvaliacaoBemTributavelVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposAvaliacaoBemtributavelServidor;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;

import java.util.Collection;


/**
 * Classe que representa a Tabela de Avaliação de Bem Tributável - Servidor
 *
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_AVALIACAO_BEMTRIBUTAVEL_SERVIDOR
     ,nomeAmigavel = "Avaliação Bem Tributável Servidor"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "avaliacaoBemTributavelVo.codigo"
			    ,nomeColuna = CamposAvaliacaoBemtributavelServidor.CAMPO_ITCTB28_CODIGO_AVALIACAO_BEM_TRIBUTAVEL
			)
			,@AnotacaoIdentificador
			(
			    nomeAtributo = "servidorSefazVo.numrMatricula"
			    ,nomeColuna = CamposAvaliacaoBemtributavelServidor.CAMPO_SGPTB06_NUMERO_MATRICULA_SERVIDOR
			)
     }
 )
public class AvaliacaoBemTributavelServidorVo extends EntidadeVo
{
	private AvaliacaoBemTributavelVo avaliacaoBemTributavelVo; // 1 Servidor -> 1 Avaliação
	private ServidorSefazIntegracaoVo servidorSefazVo;

	/**
	 * Construtor Vazio
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelServidorVo()
	{
	}

	/**
	 * Construtor que recebe a chave primária
	 *
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelServidorVo(long codigo)
	{
		super(codigo);
	}

	/**
	 *Construtor recebendo um servidor.
	 * 
	 * @param servidor
	 */
	public AvaliacaoBemTributavelServidorVo(ServidorSefazIntegracaoVo servidor)
	{
		this.servidorSefazVo = servidor;
	}

	/**
	 * Contrutor que recebe uma coleção de Avaliacão de Bem Tributável
	 *
	 * @param collVo
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelServidorVo(Collection collVo)
	{
		super(collVo);
	}

	/**
	 * Construtor que recebe o Parametro de Consulta
	 *
	 * @param parametroConsulta
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelServidorVo(AvaliacaoBemTributavelServidorVo parametroConsulta)
	{
		super();
		setParametroConsulta(parametroConsulta);
	}

	/**
	 * Método que retorna a Chave Primária
	 *
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelServidorPk getPk()
	{
		return new AvaliacaoBemTributavelServidorPk(getCodigo());
	}

	/**
	 * Atribui uma Avaliação de Bem Tributavel
	 *
	 * @param avaliacaoBemTributavelVo
	 * @implemented by Daniel Balieiro
	 */
	public void setAvaliacaoBemTributavelVo(AvaliacaoBemTributavelVo avaliacaoBemTributavelVo)
	{
		this.avaliacaoBemTributavelVo = avaliacaoBemTributavelVo;
	}

	/**
	 * Retorna a Avaliação de Bem Tributável
	 *
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelVo getAvaliacaoBemTributavelVo()
	{
		if (!Validador.isObjetoValido(avaliacaoBemTributavelVo))
		{
			setAvaliacaoBemTributavelVo(new AvaliacaoBemTributavelVo());
		}
		return avaliacaoBemTributavelVo;
	}

	/**
	 * Atribui um Servidor Sefaz
	 *
	 * @param servidor
	 * @implemented by Daniel Balieiro
	 */
	public void setServidorSefazVo(ServidorSefazIntegracaoVo servidor)
	{
		this.servidorSefazVo = servidor;
	}

	/**
	 * Retorna o Servidor Sefaz
	 *
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public ServidorSefazIntegracaoVo getServidorSefazVo()
	{
		if (!Validador.isObjetoValido(servidorSefazVo))
		{
			setServidorSefazVo(new ServidorSefazIntegracaoVo());
		}
		return servidorSefazVo;
	}
	
	public int hashCode()
	{
		int codgAvaliacaoBemTributavel = new Long(this.getAvaliacaoBemTributavelVo().getCodigo()).intValue();
		int matriculaServidor = this.getServidorSefazVo().getNumrMatricula().intValue();
		int hash = 0;
		hash += codgAvaliacaoBemTributavel;
		hash += matriculaServidor;
		hash *= MULTIPLICADOR_HASH_CODE;
		return hash;
	}
	
	public int compareTo(Object entidadeVo) throws ClassCastException, NullPointerException
	{
		int codigo = new Long(this.getAvaliacaoBemTributavelVo().getCodigo()).compareTo(new Long(((AvaliacaoBemTributavelServidorVo) entidadeVo).getAvaliacaoBemTributavelVo().getCodigo()));
		if (codigo != 0)
		{
			return codigo;
		}
		else
		{
			return this.getServidorSefazVo().getNumrMatricula().compareTo(((AvaliacaoBemTributavelServidorVo) entidadeVo).getServidorSefazVo().getNumrMatricula());
		}
	}     

	public boolean equals(Object object)
	{
		if (object instanceof EntidadeFacade)
		{
			if (this.hashCode() == object.hashCode())
			{
				return true;
			}
			return false;
		}
		else
		{
			return false;
		}
	}	
}
