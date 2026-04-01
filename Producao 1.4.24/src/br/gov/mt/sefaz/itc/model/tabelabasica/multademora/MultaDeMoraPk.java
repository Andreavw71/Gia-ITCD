/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: MultaDeMoraPk.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 12/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.multademora;

/**
 * Classe de chave primária (Value Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class MultaDeMoraPk
{
	private long codigo;

	/**
	 * Construtor Padrão
	 * @implemented by Daniel Balieiro
	 */
	public MultaDeMoraPk()
	{
	}

	/**
	 * Construtor que recebe a chave primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public MultaDeMoraPk(long codigo)
	{
		setCodigo(codigo);
	}

	/**
	 * Atribui uma chave primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Retorna a chave primária
	 * @return long
	 * @implemented by Daniel Balieiro
	 */
	public long getCodigo()
	{
		return codigo;
	}
}
