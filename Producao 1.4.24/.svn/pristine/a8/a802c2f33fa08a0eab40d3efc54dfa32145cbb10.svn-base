package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


/**
 * Adapter do dominio da classe pai.
 * 
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.1.1.1 $
 */
public class DomnTipoPess extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;

	/** Mnemônico para Pessoa Física */
	public static final int FISICA = 1;

	/** Mnemônico para Pessoa Jurídica */
	public static final int JURIDICA = 2;

	/** Mnemônico para Inscrito Comércio Indústria */
	public static final int INSCCCI = 3;

	/** Mnemônico para Inscrito Agropecuária Pessoa Física */
	public static final int INSCCAPFISICA = 4;

	/** Mnemônico para Inscrito Agropecuária Pessoa Juridica */
	public static final int INSCCAPJURIDICA = 5;

	/** Mnemônico para Tipo Sócio Estrangeiro - Pessoa Física */
	public static final int ESTRANGEIROPF = 6;

	/** Mnemônico para Tipo Sócio Estrangeiro - Pessoa Jurídica */
	public static final int ESTRANGEIROPJ = 7;

	/** Mnemônico para Contribuinte Eventual - NAI */
	public static final int EVENTUALNAI = 8;

	/** Mnemônico para Contribuinte Eventual - Arrecadação  */
	public static final int EVENTUALARRECADACAO = 9;
	public static final String DESC_FISICA = "PESSOA FÍSICA";
	public static final String DESC_JURIDICA = "PESSOA JURÍDICA";
	public static final String DESC_INSCCCI = "COMÉRCIO E INDUSTRIA";
	public static final String DESC_INSCCAPFISICA = "PROPRIEDADE RURAL - PESSOA FÍSICA";
	public static final String DESC_INSCCAPJURIDICA = "PROPRIEDADE RURAL - PESSOA JURÍDICA";
	public static final String DESC_ESTRANGEIROPF = "SÓCIO ESTRANGEIRO - PESSOA FISÍCA";
	public static final String DESC_ESTRANGEIROPJ = "SÓCIO ESTRABGEIRO - PESSOA JURÍDICA";
	public static final String DESC_EVENTUALNAI = "CONTRIBUINTE EVENTUAL - NAI";
	public static final String DESC_EVENTUALARRECADACAO = "CONTRRIBUITE EVENTUAL - ARRECADAÇÃO";

	/** Array das constantes dos mnemônicos do domínio. */
	private static int domnIndice[] = 
	  { FISICA, JURIDICA, INSCCCI, INSCCAPFISICA, INSCCAPJURIDICA, ESTRANGEIROPF, ESTRANGEIROPJ, EVENTUALNAI, EVENTUALARRECADACAO };

	/** Array das descrições dos mnemônicos do domínio. */
	private static String domnDescricao[] = 
	  { DESC_FISICA, DESC_JURIDICA, DESC_INSCCCI, DESC_INSCCAPFISICA, DESC_INSCCAPJURIDICA, DESC_ESTRANGEIROPF, DESC_ESTRANGEIROPJ, DESC_EVENTUALNAI, DESC_EVENTUALARRECADACAO };

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnTipoPess()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnTipoPess(int valorConstante)
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
