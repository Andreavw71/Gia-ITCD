package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


/**
 * Dominios das Abas
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.1.1.1 $
 */
public class DomnAba extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	// As constantes declaradas nesse dominio deve seguir, obrigatoriamente, a sequencia exata das abas, nas funcionalidades da GIAITCD 
	public static final int DADOS_GERAIS = 10;
	public static final int BENS_TRIBUTAVEIS = 20;
	public static final int BENEFICIARIOS = 30;
	public static final int CONJUGE = 40;
	public static final int DEMONSTRATIVO_DE_CALCULO = 50;
	public static final int ACOMPANHAMENTO = 60;
	public static final int SEM_ABA = 70;
	public static final int FORMA_PAGAMENTO = 80;
   public static final int DOACAO_SUCESSIVA = 90;
	private static final String DESCRICAO_DADOS_GERAIS = "DADOS_GERAIS";
	private static final String DESCRICAO_BENS_TRIBUTAVEIS = "BENS_TRIBUTAVEIS";
	private static final String DESCRICAO_BENEFICIARIOS = "BENEFICIARIOS";
	private static final String DESCRICAO_CONJUGE = "CONJUGE";
	private static final String DESCRICAO_DEMONSTRATIVO_DE_CALCULO = "DEMONSTRATIVO_DE_CALCULO";
	private static final String DESCRICAO_ACOMPANHAMENTO = "ACOMPANHAMENTO";
	private static final String DESCRICAO_SEM_ABA = "SEM_ABA";
	private static final String DESCRICAO_FORMA_PAGAMENTO = "FORMA_PAGAMENTO";
   private static final String DESCRICAO_DOACAO_SUCESSIVA = "DOACAO_SUCESSIVA";

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnAba()
	{
		super(-1);
	}
	
	public DomnAba(int valor)
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
		return new int[]
		{
			DADOS_GERAIS
			, BENS_TRIBUTAVEIS
			, BENEFICIARIOS
			, CONJUGE
			, DEMONSTRATIVO_DE_CALCULO
			, ACOMPANHAMENTO
			, SEM_ABA
         , DOACAO_SUCESSIVA
		};
	}

	/**
	 * Obtém o array com todas as descrições das constantes deste domínio.
	 * <b>Este método é utilizado pelo tagsgenerico.jar, logo é obrigatório para todos os domínios.</b>
	 * @return String[] - array de String com as constantes deste domínio.
	 */
	public String[] getDomnDescricao()
	{
		return new String[]
		{
			DESCRICAO_DADOS_GERAIS
			, DESCRICAO_BENS_TRIBUTAVEIS
			, DESCRICAO_BENEFICIARIOS
			, DESCRICAO_CONJUGE
			, DESCRICAO_DEMONSTRATIVO_DE_CALCULO
			, DESCRICAO_ACOMPANHAMENTO
			, DESCRICAO_SEM_ABA
         , DESCRICAO_DOACAO_SUCESSIVA
		};
	}
	
	public int getKey()
	{
		return super.getValorCorrente();
	}	
}
