package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnEstadoConservacao extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int OTIMO = 1;
	public static final int BOM = 2;
	public static final int REGULAR = 3;
	public static final int RUIM = 4;
	public static final String DESCRICAO_OTIMO = "ÓTIMO";
	public static final String DESCRICAO_BOM = "BOM";
	public static final String DESCRICAO_REGULAR = "REGULAR";
	public static final String DESCRICAO_RUIM = "RUIM";
	private static int domnIndice[] = { OTIMO, BOM, REGULAR, RUIM };
	private static String domnDescricao[] = { DESCRICAO_OTIMO, DESCRICAO_BOM, DESCRICAO_REGULAR, DESCRICAO_RUIM };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnEstadoConservacao()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnEstadoConservacao(int valorConstante)
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
