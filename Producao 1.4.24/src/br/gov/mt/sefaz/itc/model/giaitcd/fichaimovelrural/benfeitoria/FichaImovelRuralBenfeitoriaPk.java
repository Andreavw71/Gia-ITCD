package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria;

/**
 * Classe de chave primária
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class FichaImovelRuralBenfeitoriaPk
{
	private long codigo;

	/**
	 * Construtor que recebe a chave primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBenfeitoriaPk(long codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Atribui a chave primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Retorna a Chave Primária
	 * 
	 * @return long
	 * @implemented by Daniel Balieiro
	 */
	public long getCodigo()
	{
		return codigo;
	}
}
