package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnSimNao extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int SIM = 1;
	public static final int NAO = 2;
	private static final String DESCRICAO_SIM = "SIM";
	private static final String DESCRICAO_NAO = "NÃO";
	private static int domnIndice[] = { SIM, NAO };
	private static String domnDescricao[] = { DESCRICAO_SIM, DESCRICAO_NAO };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnSimNao()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnSimNao(int valorConstante)
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
