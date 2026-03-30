package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio;

/**
 * Classe de representação da Chave Primária da tabela de GIA-ITCD Separação
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class GIAITCDSeparacaoDivorcioPk
{
	private long codigo;

	/**
	 * Construtor que recebe a Chave Primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDSeparacaoDivorcioPk(long codigo)
	{
		setCodigo(codigo);
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

	/**
	 * Atribui a Chave Primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}
}
