package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnSituacaoRegistro extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int ATIVA = 1;
	public static final int INATIVA = 2;
	public static final int INVALIDO = 3;
	public static final int FERIAS = 4;
	public static final int LICENCA_PREMIO = 5;
	public static final int LICENCA_SAUDE = 6;
	public static final int OUTROS_AFASTAMENTO = 7;
	public static final int APOSENTADO = 8;
	public static final int LIC_MOT_AFAST_CONJUGE = 9;
	public static final int LIC_ATIVIDADE_POLITICA = 10;
	public static final int LIC_DESEMP_MAND_CLASSISTA = 11;
	public static final int AFAST_P_DECISAO_JUDICIAL = 12;
	public static final int AFAST_P_OUTRO_ORGAO_A_DISPOSICAO = 13;
	public static final int AFAST_P_EXERC_MAND_ELETIVO = 14;
	public static final int AFAST_PREVENTIVO = 15;
	public static final int LIC_P_QUALIFICACAO_PROFISSIONAL = 16;
	public static final int AFAST_P_ESTUDI_MISSAO_EXTERIOR = 17;
	private static final String DESC_ATIVA = "ATIVO";
	private static final String DESC_INATIVA = "INATIVO";
	private static final String DESC_INVALIDO = "REGISTRO INVALIDO";
	private static final String DESC_FERIAS = "EM FÉRIAS";
	private static final String DESC_LICENCA_PREMIO = "EM LICENÇA PRÊMIO";
	private static final String DESC_LICENCA_SAUDE = "EM LICENÇA SAUDE";
	private static final String DESC_OUTROS_AFASTAMENTO = "EM LIC. INTER. PARTICULAR";
	private static final String DESC_APOSENTADO = "APOSENTADO";
	private static final String DESC_LIC_MOT_AFAST_CONJUGE = "EM LIC. MOT. AFAST. CONJUGE";
	private static final String DESC_LIC_ATIVIDADE_POLITICA = "EM LIC. ATIVIDADE POLITICA";
	private static final String DESC_LIC_DESEMP_MAND_CLASSISTA = "EM LIC. DESEMP. MAND. CLASSISTA";
	private static final String DESC_AFAST_P_DECISAO_JUDICIAL = "EM AFAST. P/ DECISÃO JUDICIAL";
	private static final String DESC_AFAST_P_OUTRO_ORGAO_A_DISPOSICAO = "EM AFAST. P/ OUTRO ORGÃO(A DISPOSIÇÃO)";
	private static final String DESC_AFAST_P_EXERC_MAND_ELETIVO = "EM AFAST. P/ EXERC. MAND. ELETIVO";
	private static final String DESC_AFAST_PREVENTIVO = "EM AFAST. PREVENTIVO";
	private static final String DESC_LIC_P_QUALIFICACAO_PROFISSIONAL = "EM LIC. P/ QUALIFICAÇAO PROFISSIONAL";
	private static final String DESC_AFAST_P_ESTUDI_MISSAO_EXTERIOR = "EM AFAST. P/ ESTUDO/MISSÃO NO EXTERIOR";
	private static int domnIndice[] = 
	  { ATIVA, INATIVA, INVALIDO, FERIAS, LICENCA_PREMIO, LICENCA_SAUDE, OUTROS_AFASTAMENTO, APOSENTADO, LIC_MOT_AFAST_CONJUGE, LIC_ATIVIDADE_POLITICA, LIC_DESEMP_MAND_CLASSISTA, AFAST_P_DECISAO_JUDICIAL, AFAST_P_OUTRO_ORGAO_A_DISPOSICAO, AFAST_P_EXERC_MAND_ELETIVO, AFAST_PREVENTIVO, LIC_P_QUALIFICACAO_PROFISSIONAL, AFAST_P_ESTUDI_MISSAO_EXTERIOR };
	private static String domnDescricao[] = 
	  { DESC_ATIVA, DESC_INATIVA, DESC_INVALIDO, DESC_FERIAS, DESC_LICENCA_PREMIO, DESC_LICENCA_SAUDE, DESC_OUTROS_AFASTAMENTO, DESC_APOSENTADO, DESC_LIC_MOT_AFAST_CONJUGE, DESC_LIC_ATIVIDADE_POLITICA, DESC_LIC_DESEMP_MAND_CLASSISTA, DESC_AFAST_P_DECISAO_JUDICIAL, DESC_AFAST_P_OUTRO_ORGAO_A_DISPOSICAO, DESC_AFAST_P_EXERC_MAND_ELETIVO, DESC_AFAST_PREVENTIVO, DESC_LIC_P_QUALIFICACAO_PROFISSIONAL, DESC_AFAST_P_ESTUDI_MISSAO_EXTERIOR };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnSituacaoRegistro()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnSituacaoRegistro(int valorConstante)
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
