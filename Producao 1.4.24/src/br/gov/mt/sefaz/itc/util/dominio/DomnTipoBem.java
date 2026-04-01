package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnTipoBem extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int IMOVEL_URBANO = 1;
	public static final int IMOVEL_RURAL = 2;
	public static final int OUTROS_BENS = 3;
	private static final String DESCRICAO_IMOVEL_URBANO = "IMOVEL URBANO";
	private static final String DESCRICAO_IMOVEL_RURAL = "IMOVEL RURAL";
	private static final String DESCRICAO_OUTROS_BENS = "OUTROS BENS";
	private static int domnIndice[] = { IMOVEL_URBANO, IMOVEL_RURAL, OUTROS_BENS };
	private static String domnDescricao[] = { DESCRICAO_IMOVEL_URBANO, DESCRICAO_IMOVEL_RURAL, DESCRICAO_OUTROS_BENS };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnTipoBem()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnTipoBem(int valorConstante)
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
