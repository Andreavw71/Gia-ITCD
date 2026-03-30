/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDPk.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 10/12/2007
 */
package br.gov.mt.sefaz.itc.model.generico.giaitcd;

/**
 * Classe de Chave Primária de um GIA ITCD.
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class GIAITCDPk
{
	private long codigo;

	/**
	 * Construtor Padrão.
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDPk()
	{
	}

	/**
	 * Construtor que recebe a Chave Primária.
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDPk(long codigo)
	{
		setCodigo(codigo);
	}

	/**
	 * Atribui a Chave Primária.
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Retorna a Chave Primária.
	 * @return long
	 * @implemented by Daniel Balieiro
	 */
	public long getCodigo()
	{
		return codigo;
	}
}
