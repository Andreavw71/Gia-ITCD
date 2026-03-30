package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


/**
 * @author Fernanda S Azevedo
 * @version $Revision: 1.1 $
 */
public class DomnTipoAvaliacao extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int AVALIACAO_AGENFA = 1;
	public static final int AVALIACAO_CGED = 2;
	public static final String DESC_AVALIACAO_AGENFA = "AVALIAÇÃO AGENFA";
	public static final String DESC_AVALIACAO_CGED = "AVALIAÇÃO CGED";
	private static int domnIndice[] = { AVALIACAO_AGENFA, AVALIACAO_CGED };
	private static String domnDescricao[] = 
	  { DESC_AVALIACAO_AGENFA, DESC_AVALIACAO_CGED};

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnTipoAvaliacao()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnTipoAvaliacao(int valorConstante)
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
