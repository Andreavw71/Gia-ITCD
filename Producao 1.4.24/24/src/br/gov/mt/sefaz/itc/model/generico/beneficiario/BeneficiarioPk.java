package br.gov.mt.sefaz.itc.model.generico.beneficiario;

/**
 * Classe de Chave Primária do Beneficiario
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class BeneficiarioPk
{
	private long codigo;

	/**
	 * Construtor que recebe a Chave Primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public BeneficiarioPk(long codigo)
	{
		setCodigo(codigo);
	}

	/**
	 * Atribui uma Chave Primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Retorna a Chave Primária
	 * @return long
	 * @implemented by Daniel Balieiro
	 */
	public long getCodigo()
	{
		return codigo;
	}
}
