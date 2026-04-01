/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDTemporarioPk.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 10/12/2007
 */
package br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario;

/**
 * Classe de Chave Primária do GIA ITCD.
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class GIAITCDTemporarioPk
{
	private long codigo;

	/**
	 * Construtor que recebe a Chave Primária.
	 * @param codigo Chave primária.
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDTemporarioPk(long codigo)
	{
		setCodigo(codigo);
	}

	/**
	 * Atribui a Chave Primária.
	 * @param codigo Chave primária.
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
