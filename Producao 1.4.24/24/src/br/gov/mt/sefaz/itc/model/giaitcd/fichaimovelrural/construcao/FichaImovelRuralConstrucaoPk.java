package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao;

/**
 * Classe de Chave Primária
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class FichaImovelRuralConstrucaoPk
{
	private long codigo;

	/**
	 * Construtor que recebe a chave primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoPk(long codigo)
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
	 * Retorna a chave primária
	 * 
	 * @return long
	 * @implemented by Daniel Balieiro
	 */
	public long getCodigo()
	{
		return codigo;
	}
}
