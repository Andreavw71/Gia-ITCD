package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnRegimeCasamento extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int COMUNHAO_PARCIAL_DE_BENS = 1;
	public static final int SEPARACAO_TOTAL_DE_BENS = 2;
	public static final int COMUNHAO_UNIVERSAL_DE_BENS = 3;
	public static final String DESCRICAO_COMUNHAO_PARCIAL_DE_BENS_UNIAO_ESTAVEL = "COMUNHÃO PARCIAL DE BENS / UNIÃO ESTAVEL";
	public static final String DESCRICAO_SEPARACAO_TOTAL_DE_BENS = "SEPARAÇÃO TOTAL DE BENS";
	public static final String DESCRICAO_COMUNHAO_UNIVERSAL_DE_BENS = "COMUNHÃO UNIVERSAL DE BENS";
	private static int domnIndice[] = { COMUNHAO_PARCIAL_DE_BENS, SEPARACAO_TOTAL_DE_BENS, COMUNHAO_UNIVERSAL_DE_BENS };
	private static String domnDescricao[] = 
	  { DESCRICAO_COMUNHAO_PARCIAL_DE_BENS_UNIAO_ESTAVEL, DESCRICAO_SEPARACAO_TOTAL_DE_BENS, DESCRICAO_COMUNHAO_UNIVERSAL_DE_BENS };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnRegimeCasamento()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnRegimeCasamento(int valorConstante)
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
