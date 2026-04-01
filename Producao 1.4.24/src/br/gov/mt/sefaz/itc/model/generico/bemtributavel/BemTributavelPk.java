package br.gov.mt.sefaz.itc.model.generico.bemtributavel;

/**
 * Classe de representação da Chave Primária da entidade Bem Tributável
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class BemTributavelPk
{
	private long codigo;

	/**
	 * Construtor Padrão
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public BemTributavelPk(long codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Retorna o Código
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public long getCodigo()
	{
		return codigo;
	}

	/**
	 * Atribui o Código
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}
}
