package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnTipoConsultaAvaliacao extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int NUMERO_GIA_ITCD = 1;
	public static final int AVALIACAO_POR_AGENFA = 2;
	public static final int AVALIACAO_POR_GERENCIA_DE_EXECUCAO = 3;
	public static final int AVALIACAO_POR_UNIDADE_FAZENDARIA = 4;
	public static final int AVALIACAO_POR_CONTRIBUINTE = 5;
	
	private static final String DESCRICAO_NUMERO_GIA_ITCD = "Número GIA-ITCD";
	private static final String DESCRICAO_AVALIACAO_POR_AGENFA = "Avaliação por Agenfa";
	private static final String DESCRICAO_AVALIACAO_POR_GERENCIA_DE_EXECUCAO = "Avaliação por Gerência";
	private static final String DESCRICAO_AVALIACAO_POR_UNIDADE_FAZENDARIA = "Todas as Unidades Fazendárias";
	private static final String DESCRICAO_AVALIACAO_POR_CONTRIBUINTE = "Contribuinte";	
	
	public static int domnIndice[] = { NUMERO_GIA_ITCD, AVALIACAO_POR_AGENFA, AVALIACAO_POR_GERENCIA_DE_EXECUCAO, AVALIACAO_POR_UNIDADE_FAZENDARIA, AVALIACAO_POR_CONTRIBUINTE };
	public static String domnDescricao[] = { DESCRICAO_NUMERO_GIA_ITCD, DESCRICAO_AVALIACAO_POR_AGENFA, DESCRICAO_AVALIACAO_POR_GERENCIA_DE_EXECUCAO, DESCRICAO_AVALIACAO_POR_UNIDADE_FAZENDARIA, DESCRICAO_AVALIACAO_POR_CONTRIBUINTE };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnTipoConsultaAvaliacao()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnTipoConsultaAvaliacao(int valorConstante)
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
