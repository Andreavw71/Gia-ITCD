package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


/**
 * Dominio para tipo de contribuinte, usado para localizar os contribuintes
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.2 $
 */
public class DomnTipoContribuinte extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int DECLARANTE = 1;
	public static final int PROCURADOR = 2;
	public static final int DE_CUJUS = 3;
	public static final int MEEIRO = 4;
	public static final int BENEFICIARIOS = 5;
	public static final int CONJUGE1 = 6;
	public static final int CONJUGE2 = 7;
	private static final String DESCRICAO_DECLARANTE = "DECLARANTE";
	private static final String DESCRICAO_PROCURADOR = "PROCURADOR";
	private static final String DESCRICAO_DE_CUJUS = "DE CUJUS";
	private static final String DESCRICAO_MEEIRO = "MEEIRO";
	private static final String DESCRICAO_BENEFICIARIOS = "BENEFICIARIOS";
	private static final String DESCRICAO_CONJUGE1 = "CONJUGE1";
	private static final String DESCRICAO_CONJUGE2 = "CONJUGE2";
	private static int domnIndice[] = { DECLARANTE, PROCURADOR, DE_CUJUS, MEEIRO, BENEFICIARIOS, CONJUGE1, CONJUGE2 };
	private static String domnDescricao[] = 
	  { DESCRICAO_DECLARANTE, DESCRICAO_PROCURADOR, DESCRICAO_DE_CUJUS, DESCRICAO_MEEIRO,DESCRICAO_BENEFICIARIOS, DESCRICAO_CONJUGE1, DESCRICAO_CONJUGE2 };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnTipoContribuinte()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnTipoContribuinte(int valorConstante)
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
