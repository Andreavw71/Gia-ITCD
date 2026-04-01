/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConjugeBemTributavelPk.java
 * Revisão:
 * Data revisão:
 * $Id: ConjugeBemTributavelPk.java,v 1.1.1.1 2008/05/28 17:55:04 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.conjuge;

/**
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
public class ConjugeBemTributavelPk
{
	private long codigo;

	/**
	 * Construtor que recebe a Chave Primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public ConjugeBemTributavelPk(long codigo)
	{
		setCodigo(codigo);
	}

	/**
	 * Atribui a Chave Primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Retorna a Chave Primária
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public long getCodigo()
	{
		return codigo;
	}
}
