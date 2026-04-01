package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


/**
 * 
 * Dominio que representa o estado e motivo do cancelamento do DAR.
 * Adapter serializado.
 * 
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.1.1.1 $
 */
public class DomnStatDar extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int PENDENTE = 1;
	public static final int QUITADO = 2;
	public static final int CANCELADO = 3;
	public static final String DESC_PENDENTE = "PENDENTE";
	public static final String DESC_QUITADO = "QUITADO";
	public static final String DESC_CANCELADO = "CANCELADO";

	/** índices numéricos do domínio */
	private static int domnIndice[] = { PENDENTE, QUITADO, CANCELADO };

	/** descriç?es do domínio */
	private static String domnDescricao[] = { DESC_PENDENTE, DESC_QUITADO, DESC_CANCELADO };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnStatDar()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnStatDar(int valorConstante)
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
