/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDDarPk.java
 * Revisão: Leandro Dorileo
 * Data revisão: 17/03/2008
 * $Id: GIAITCDDarPk.java,v 1.1.1.1 2008/05/28 17:55:05 lucas.nascimento Exp $
 */
 
package br.gov.mt.sefaz.itc.model.generico.giaitcddar;

/**
 * Classe de Chave Primária de um GIA ITCD.
 * @author Leandro Dorileo
 * @version $Revision: 1.1.1.1 $
 */
public class GIAITCDDarPk
{
	private long codigo;

	/**
	 * Construtor Padrão.
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarPk()
	{
	}

	/**
	 * Construtor que recebe a Chave Primária.
	 * @param codigo
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarPk(long codigo)
	{
		setCodigo(codigo);
	}

	/**
	 * Atribui a Chave Primária.
	 * @param codigo
	 * @implemented by Leandro Dorileo
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Retorna a Chave Primária.
	 * @return long
	 * @implemented by Leandro Dorileo
	 */
	public long getCodigo()
	{
		return codigo;
	}
}
