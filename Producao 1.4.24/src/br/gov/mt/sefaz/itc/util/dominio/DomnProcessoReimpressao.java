package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnProcessoReimpressao extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int CODIGO_DOACAO = 1;
	public static final int CODIGO_INVENTARIO_ARROLAMENTO = 2;
	public static final int CODIGO_SEPARACAO_DIVORCIO_PARTILHA = 3;
	public static final int CODIGO_ISENCAO_VALORES = 4;
	public static final int CODIGO_OCORRENCIA_FATO_GERADOR = 5;
	public static final String DESCRICAO_DOACAO = "GIA-ITCD - Doação / Outros";
	public static final String DESCRICAO_INVENTARIO_ARROLAMENTO = "GIA-ITCD - Inventário/Arrolamento";
	public static final String DESCRICAO_SEPARACAO_DIVORCIO_PARTILHA = "GIA-ITCD - Separação/Divórcio/Partilha";
	public static final String DESCRICAO_ISENCAO_VALORES = "Declaração de Isenção";
	public static final String DESCRICAO_OCORRENCIA_FATO_GERADOR = "Declaração de não ocorrência de fato gerador";
	private static int domnIndice[] = 
	  { CODIGO_DOACAO, CODIGO_INVENTARIO_ARROLAMENTO, CODIGO_SEPARACAO_DIVORCIO_PARTILHA, CODIGO_ISENCAO_VALORES, CODIGO_OCORRENCIA_FATO_GERADOR };
	private static String domnDescricao[] = 
	  { DESCRICAO_DOACAO, DESCRICAO_INVENTARIO_ARROLAMENTO, DESCRICAO_SEPARACAO_DIVORCIO_PARTILHA, DESCRICAO_ISENCAO_VALORES, DESCRICAO_OCORRENCIA_FATO_GERADOR };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnProcessoReimpressao()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnProcessoReimpressao(int valorConstante)
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
