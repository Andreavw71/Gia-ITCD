package br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao;

/**
 * Referencia à Chave Primária de um Parametro Legislação (Tabela ITCTB09_PARAM_LGIL)
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class ParametroLegislacaoPk
{
	/**
	 * Chave Primária
	 * @implemented by Daniel Balieiro
	 */
	private long codigo;

	/**
	 * Construtor Padrão
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoPk()
	{
	}

	/**
	 * Construtor que Recebe a Chave Primária como Parametro
	 * @param codigo Chave Primária
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoPk(long codigo)
	{
		setCodigo(codigo);
	}

	/**
	 * Método que altera a Chave Primária
	 * @param codigo Chave Primária
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Método que retorna a Chave Primária
	 * @return long Chave Primária
	 * @implemented by Daniel Balieiro
	 */
	public long getCodigo()
	{
		return codigo;
	}
}
