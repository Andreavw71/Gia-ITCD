package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnTipoConfiguracaoGerencialParametros extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int TEXTO = 1;
	public static final int NUMERO = 2;
	public static final int DATA = 3;
	public static final int VALOR = 4;
	public static final int DECIMAL = 5;
	private static final String DESCRICAO_TEXTO = "TEXTO";
	private static final String DESCRICAO_NUMERO = "NÚMERO";
	private static final String DESCRICAO_DATA = "DATA";
	private static final String DESCRICAO_VALOR = "VALOR";
	private static final String DESCRICAO_DECIMAL = "DECIMAL";
	private static int domnIndice[] = { TEXTO, NUMERO, DATA, VALOR, DECIMAL };
	private static String domnDescricao[] = { DESCRICAO_TEXTO, DESCRICAO_NUMERO, DESCRICAO_DATA, DESCRICAO_VALOR, DESCRICAO_DECIMAL };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnTipoConfiguracaoGerencialParametros()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnTipoConfiguracaoGerencialParametros(int valorConstante)
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
