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
public class DomnCancelamentoDar extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int CANCELAMENTOOK = 1;
	public static final int DARNAOCADASTRADO = 2;
	public static final int DARNAOPENDENTE = 3;
	public static final int SAIDADARNAOPENDENTE = 4;
	public static final int DARNAOEMITIDOPELOUSUARIOATUAL = 5;
	public static final int HORARIOULTRAPASSADO = 6;
	public static final int CANCELAMENTOOKCOMSELOFISCAL = 7;
	public static final String DESC_CANCELAMENTOOK = "CANCELAMENTO OK";
	public static final String DESC_DARNAOCADASTRADO = "DAR NÃO CADASTRADO";
	public static final String DESC_DARNAOPENDENTE = "DAR NÃO PENDENTE";
	public static final String DESC_SAIDADARNAOPENDENTE = "SAÍDA DO DAR N?O PENDENTE";
	public static final String DESC_DARNAOEMITIDOPELOUSUARIOATUAL = "DAR NÃO EMITIDO PELO USUÁRIO ATUAL";
	public static final String DESC_HORARIOULTRAPASSADO = "HORÁRIO ULTRAPASSADO";
	public static final String DESC_CANCELAMENTOOKCOMSELOFISCAL = "CANCELAMENTO OK COM SELO FISCAL";

	/** índices numéricos do domínio */
	private static int domnIndice[] = 
	  { CANCELAMENTOOK, DARNAOCADASTRADO, DARNAOPENDENTE, SAIDADARNAOPENDENTE, DARNAOEMITIDOPELOUSUARIOATUAL, HORARIOULTRAPASSADO, CANCELAMENTOOKCOMSELOFISCAL };

	/** descrições do domínio */
	private static String domnDescricao[] = 
	  { DESC_CANCELAMENTOOK, DESC_DARNAOCADASTRADO, DESC_DARNAOPENDENTE, DESC_SAIDADARNAOPENDENTE, DESC_DARNAOEMITIDOPELOUSUARIOATUAL, DESC_HORARIOULTRAPASSADO, DESC_CANCELAMENTOOKCOMSELOFISCAL };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnCancelamentoDar()
	{
		super(-1);
	}

	public DomnCancelamentoDar(int valor)
	{
		super(valor);
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
