package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnEstadoCivil extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int CASADO = 1;
	public static final int DIVORCIADO = 2;
	public static final int CONVIVENTE = 3;
	public static final int SEPARADO = 4;
	public static final int SOLTEIRO = 5;
	public static final int VIUVO = 6;
	public static final String DESCRICAO_CASADO = "CASADO";
	public static final String DESCRICAO_DIVORCIADO = "DIVORCIADO";
	public static final String DESCRICAO_CONVIVENTE = "CONVIVENTE";
	public static final String DESCRICAO_SEPARADO = "SEPARADO";
	public static final String DESCRICAO_SOLTEIRO = "SOLTEIRO";
	public static final String DESCRICAO_VIUVO = "VIUVO";
	private static int domnIndice[] = { CASADO, DIVORCIADO, CONVIVENTE, SEPARADO, SOLTEIRO, VIUVO };
	private static String domnDescricao[] = 
	  { DESCRICAO_CASADO, DESCRICAO_DIVORCIADO, DESCRICAO_CONVIVENTE, DESCRICAO_SEPARADO, DESCRICAO_SOLTEIRO, DESCRICAO_VIUVO };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnEstadoCivil()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnEstadoCivil(int valorConstante)
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
