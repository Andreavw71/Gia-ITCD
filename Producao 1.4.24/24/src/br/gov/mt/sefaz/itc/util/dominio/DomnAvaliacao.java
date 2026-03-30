package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnAvaliacao extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int CODIGO_GIAITCD = 1;
	public static final int CODIGO_ISENCAO_VALORES = 2;
	public static final int CODIGO_OCORRENCIA_FATO_GERADOR = 3;
	public static final int AVALIACAO_POR_AGENFA = 4;
	public static final int TODAS_AS_AVALIACOES = 5;
	public static final int NOTIFICADA = 6;
	public static final int RETIFICADA = 7;
	public static final String DESCRICAO_GIAITCD = "Número-GIA-ITCD";
	public static final String DESCRICAO_ISENCAO_VALORES = "Declaração de Isenção para Valores";
	public static final String DESCRICAO_OCORRENCIA_FATO_GERADOR = "Declaração de não ocorrência de fato gerador";
	public static final String DESCRICAO_AVALIACAO_POR_AGENFA = "Avalicação por AGENFA";
	public static final String DESCRICAO_TODAS_AS_AVALIACOES = "Descrição todas as avaliações";
	public static final String DESCRICAO_NOTIFICADAS = "Descrição notificadas";
	public static final String DESCRICAO_RETIFICADAS = "Descrição retificadas";
	private static int domnIndice[] = 
	  { CODIGO_GIAITCD, CODIGO_ISENCAO_VALORES, CODIGO_OCORRENCIA_FATO_GERADOR, AVALIACAO_POR_AGENFA };
	private static String domnDescricao[] = 
	  { DESCRICAO_GIAITCD, DESCRICAO_ISENCAO_VALORES, DESCRICAO_OCORRENCIA_FATO_GERADOR, DESCRICAO_AVALIACAO_POR_AGENFA };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnAvaliacao()
	{
		super(-1);
	}
	
	public DomnAvaliacao(int valor)
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
