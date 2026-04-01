package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnTipoProcesso extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int DOACAO = 1;
	public static final int INVENTARIO_ARROLAMENTO = 2;
	public static final int SEPARACAO_DIVORCIO_PARTILHA = 3;
	private static final String DESCRICAO_DOACAO = "DOAÇÃO / OUTROS";
	private static final String DESCRICAO_INVENTARIO = "INVENTÁRIO / ARROLAMENTO";
	private static final String DESCRICAO_SEPARACAO_DIVORCIO_PARTILHA = "SEPARAÇÃO / DIVÓRCIO / PARTILHA";
	private static int domnIndice[] = { DOACAO, INVENTARIO_ARROLAMENTO, SEPARACAO_DIVORCIO_PARTILHA };
	private static String domnDescricao[] = 
	  { DESCRICAO_DOACAO, DESCRICAO_INVENTARIO, DESCRICAO_SEPARACAO_DIVORCIO_PARTILHA };

	/**
	 * Instancia o domínio sem valor corrente
    * 
	 */
	public DomnTipoProcesso()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnTipoProcesso(int valorConstante)
	{
		super(valorConstante);
	}

	/**
	 * Obtém o array com todas as constantes deste domínio.
	 * <b>Este método é utilizado pelo tagsgenerico.jar, logo é obrigatório para todos os domínios.</b>
	 * @return int[] - array de inteiro com as constantes deste domínio.
	 */
	public int[] getDomnIndice()
	{
		return domnIndice;
	}

	/**
	 * Obtém o array com todas as descrições das constantes deste domínio.
	 * <b>Este método é utilizado pelo tagsgenerico.jar, logo é obrigatório para todos os domínios.</b>
	 * @return String[] - array de String com as constantes deste domínio.
	 */
	public String[] getDomnDescricao()
	{
		return domnDescricao;
	}

	public int getKey()
	{
		return super.getValorCorrente();
	}	
}
