package br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd;

/**
 * Classe para Chave Primária de Status GIA ITCD
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class StatusGIAITCDPk
{
	private long codigo;

	/**
	 * Construtor que recebe a Chave Primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public StatusGIAITCDPk(long codigo)
	{
		this.codigo = codigo;
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
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public long getCodigo()
	{
		return codigo;
	}
}
