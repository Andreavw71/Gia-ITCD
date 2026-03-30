/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BemPk.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 09/10/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.bem;

/**
 * Classe de Chave Primaria de um BEM (Tabela ITCTB06_BEM).
 * @author Marlo Eichenberg Motta
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class BemPk
{
	private long codigo;

	/**
	 * Construtor da classe.
	 * @param codigo
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public BemPk(long codigo)
	{
		setCodigo(codigo);
	}

	/**
	 * Construtor padrão da classe.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public BemPk()
	{
	}

	/**
	 * Retorna a Chave Primária.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 * @return long
	 */
	public long getCodigo()
	{
		return codigo;
	}

	/**
	 * Atribui uma Chave Primária.
	 * @param codigo
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}
}
