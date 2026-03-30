package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura;

/**
 * Classe para Chave Primária
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class FichaImovelRuralCulturaPk
{
	private long codigo;

	/**
	 * Construtor que recebe a Chave Primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaPk(long codigo)
	{
		setCodigo(codigo);
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

	/**
	 * Atribui a Chave Primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}
}
