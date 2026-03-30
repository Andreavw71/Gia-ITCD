package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnTipoGIA extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int CAUSA_MORTIS = 1;
	public static final int INTER_VIVOS = 2;
	private static final String DESCRICAO_CAUSA_MORTIS = "CAUSA MORTIS";
	private static final String DESCRICAO_INTER_VIVOS = "INTER VIVOS";
	private static int domnIndice[] = { CAUSA_MORTIS, INTER_VIVOS };
	private static String domnDescricao[] = { DESCRICAO_CAUSA_MORTIS, DESCRICAO_INTER_VIVOS };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnTipoGIA()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnTipoGIA(int valorConstante)
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
