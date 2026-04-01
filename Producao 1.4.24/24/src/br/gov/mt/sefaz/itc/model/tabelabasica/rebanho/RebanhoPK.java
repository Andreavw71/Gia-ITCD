/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: RebanhoPK.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 03/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.rebanho;

/**
 * Classe de chave primária (Value Object).
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
public class RebanhoPK
{

	/**
	 * Código da Rebanho.
	 */
	private long codigo;

	/**
	 * Construtor da classe.
	 * @param codigo Chave Primaria do Rebanho
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public RebanhoPK(long codigo)
	{
		super();
		setCodigo(codigo);
	}

	/**
	 * Construtor padrão da classe.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public RebanhoPK()
	{
		super();
	}

	/**
	 * Retorna a Chave Primária.
	 * @return long
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
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
