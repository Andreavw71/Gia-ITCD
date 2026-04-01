package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho;

/**
 * Classe de chave primária
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class FichaImovelRuralRebanhoPk
{
	private long codigo;

	/**
	 * Construtor que recebe a Chave Primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralRebanhoPk(long codigo)
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
	 * Atribui uma Chave Primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}
}
