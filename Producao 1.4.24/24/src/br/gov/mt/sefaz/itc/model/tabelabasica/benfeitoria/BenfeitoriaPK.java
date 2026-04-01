/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BenfeitoriaPK.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 09/10/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria;

/**
 * Classe de Chave Primaria de uma BENFEITORIA (Tabela ITCTB07_BENFEITORIA).
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
public class BenfeitoriaPK
{

	/**
	 * Código da Benfeitoria.
	 */
	private long codigo;

	/**
	 * Construtor da classe.
	 * @param codigo Chave primária.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public BenfeitoriaPK(long codigo)
	{
		super();
		setCodigo(codigo);
	}

	/**
	 * Construtor padrão da classe.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public BenfeitoriaPK()
	{
		super();
	}

	/**
	 * Retorna a Chave Primária.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public long getCodigo()
	{
		return codigo;
	}

	/**
	 * Atribui uma Chave Primária.
	 * @param codigo Chave primária.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}
}
