/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AvaliacaoBemTributavelServidorPk.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: AvaliacaoBemTributavelServidorPk.java,v 1.1.1.1 2008/05/28 17:55:04 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.avaliacao.servidor;

/**
 * Classe que reprenseta a Chave Primária da Tabela de Avaliação Bem Tributavel - Servidor
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class AvaliacaoBemTributavelServidorPk
{
	private long codigo;

	/**
	 * Construtor que recebe a Chave Primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelServidorPk(long codigo)
	{
		setCodigo(codigo);
	}

	/**
	 * Atribui valor para a Chave Primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Retorna o valor da Chave Primária
	 * 
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public long getCodigo()
	{
		return codigo;
	}
}
