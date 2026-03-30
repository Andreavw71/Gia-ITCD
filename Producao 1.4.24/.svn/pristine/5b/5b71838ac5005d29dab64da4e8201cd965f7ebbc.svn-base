package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnTipoAcesso extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int ASFALTO = 1;
	public static final int TERRA = 2;
	public static final int CALCADA = 3;
	public static final int PARALELEPIPEDO = 4;
	public static final int OUTROS = 5;
	public static final String DESCRICAO_ASFALTO = "ASFALTO";
	public static final String DESCRICAO_TERRA = "TERRA";
	public static final String DESCRICAO_CALCADA = "CALÇADA";
	public static final String DESCRICAO_PARALELEPIPEDO = "PARALELEPÍPEDO";
	public static final String DESCRICAO_OUTROS = "OUTROS";
	private static int domnIndice[] = { ASFALTO, TERRA, CALCADA, PARALELEPIPEDO, OUTROS };
	private static String domnDescricao[] = 
	  { DESCRICAO_ASFALTO, DESCRICAO_TERRA, DESCRICAO_CALCADA, DESCRICAO_PARALELEPIPEDO, DESCRICAO_OUTROS };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnTipoAcesso()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnTipoAcesso(int valorConstante)
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
