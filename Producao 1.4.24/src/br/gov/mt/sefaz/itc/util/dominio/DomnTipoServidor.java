package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnTipoServidor extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int EFETIVO = 1;
	public static final int ESTAVEL = 2;
	public static final int COMISSIONADO = 3;
	public static final int TERCEIRIZADO = 4;
	public static final int ESTAGIARIO = 5;
	public static final String DESC_EFETIVO = "EFETIVO";
	public static final String DESC_ESTAVEL = "ESTÁVEL";
	public static final String DESC_COMISSIONADO = "COMISSIONADO";
	public static final String DESC_TERCEIRIZADO = "TERCEIRIZADO";
	public static final String DESC_ESTAGIARIO = "ESTAGIÁRIO";
	private static int domnIndice[] = { EFETIVO, ESTAVEL, COMISSIONADO, TERCEIRIZADO, ESTAGIARIO };
	private static String domnDescricao[] = 
	  { DESC_EFETIVO, DESC_ESTAVEL, DESC_COMISSIONADO, DESC_TERCEIRIZADO, DESC_ESTAGIARIO };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnTipoServidor()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnTipoServidor(int valorConstante)
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
