package br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao;

/**
 * Classe de chave primária (Value Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class NaturezaOperacaoPk
{
	private long codigo;

	/**
	 * Construtor Padrão
	 * @implemented by Daniel Balieiro
	 */
	public NaturezaOperacaoPk()
	{
	}

	/**
	 * Construtor que recebe a chave primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public NaturezaOperacaoPk(long codigo)
	{
		setCodigo(codigo);
	}

	/**
	 * Atribui uma chave primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Retorna a chave primária
	 * @return long
	 * @implemented by Daniel Balieiro
	 */
	public long getCodigo()
	{
		return codigo;
	}
}
