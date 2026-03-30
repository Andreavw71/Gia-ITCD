package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


/**
 * Classe utilizada para informar o código da info partição para o sistema de LOG.
 * O código da info partição, é o código da transação pai (maior) que manda outras transações menores (filhas) serem executadas.
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class DomnNumeroParticao extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int PROPRIA_TRANSACAO = 1;
	public static final String DESC_PROPRIA_TRANSACAO = "PROPRIA TRANSAÇÃO";
	//SOMENTE PARA EXEMPLO SE ESTA TRANSAÇÃO MANDASSE REALIZAR OUTRAS TRANSAÇÕES MENORES
	public static final int INCLUIR_BEM = 2;
	public static final int PARAMETRO_LEGISLACAO_INCLUIR = DomnCodigoTransacao.PARAMETRO_LEGISLACAO_INCLUIR;
	public static final int PARAMETRO_LEGISLACAO_ALTERAR = DomnCodigoTransacao.PARAMETRO_LEGISLACAO_ALTERAR;
	public static final int TELA_FUNCIONALIDADE_INCLUIR = DomnCodigoTransacao.TELA_CAMPO_AJUDA_INCLUIR;
	public static final int TELA_FUNCIONALIDADE_ALTERAR = DomnCodigoTransacao.TELA_CAMPO_AJUDA_ALTERAR;
	public static final int GIA_ITCD_INCLUIR = DomnCodigoTransacao.GIA_ITCD_INCLUIR;
	public static final int GIA_ITCD_ALTERAR = DomnCodigoTransacao.GIA_ITCD_ALTERAR;
	public static final int GIA_ITCD_BEM_TRIBUTAVEL_INCLUIR = DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_INCLUIR;
	public static final int GIA_ITCD_FICHA_IMOVEL_RURAL_INCLUIR = DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_INCLUIR;
	public static final int GIA_ITCD_BEM_TRIBUTAVEL_ALTERAR = DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_ALTERAR;
	public static final int GIA_ITCD_FICHA_IMOVEL_RURAL_ALTERAR = DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_ALTERAR;
	public static final int GIA_ITCD_FICHA_IMOVEL_URBANO_INCLUIR = DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_URBANO_INCLUIR;
	public static final int GIA_ITCD_FICHA_IMOVEL_URBANO_ALTERAR = DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_URBANO_ALTERAR;
	public static final int GIA_ITCD_INVENTARIO_ARROLAMENTO_INCLUIR = DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_INCLUIR;
	public static final int GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_INCLUIR = DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_INCLUIR;
	public static final int GIA_ITCD_INVENTARIO_ARROLAMENTO_ALTERAR = DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_ALTERAR;
	public static final int GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALTERAR = DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALTERAR;
	public static final int GIA_ITCD_DOACAO_INCLUIR = DomnCodigoTransacao.GIA_ITCD_DOACAO_INCLUIR;
	public static final int GIA_ITCD_DOACAO_ALTERAR = DomnCodigoTransacao.GIA_ITCD_DOACAO_ALTERAR;	
	public static final int GIA_ITCD_SEPARACAO_INCLUIR = DomnCodigoTransacao.GIA_ITCD_SEPARACAO_INCLUIR;
	public static final int GIA_ITCD_SEPARACAO_ALTERAR = DomnCodigoTransacao.GIA_ITCD_SEPARACAO_ALTERAR;
	public static final int AVALIACAO_BEM_TRIBUTAVEL_INCLUIR = DomnCodigoTransacao.AVALIACAO_BEM_TRIBUTAVEL_INCLUIR;
	public static final int AVALIACAO_BEM_TRIBUTAVEL_ALTERAR = DomnCodigoTransacao.AVALIACAO_BEM_TRIBUTAVEL_ALTERAR;
	
	public static final String DESC_PARAMETRO_LEGISLACAO_INCLUIR = "PARAMETRO LEGISLAÇÃO INCLUIR";
	public static final String DESC_PARAMETRO_LEGISLACAO_ALTERAR = "PARAMETRO LEGILAÇÃO ALTERAR";
	public static final String DESC_TELA_FUNCIONALIDADE_INCLUIR = "TELA FUNCIONALIDADE INCLUIR";	
	public static final String DESC_TELA_FUNCIONALIDADE_ALTERAR = "TELA FUNCIONALIDADE ALTERAR";
	public static final String DESC_GIA_ITCD_INCLUIR = "GIA_ITCD_INCLUIR";
	
	public static final String DESC_GIA_ITCD_ALTERAR = "GIA_ITCD_ALTERAR";
	public static final String DESC_GIA_ITCD_BEM_TRIBUTAVEL_INCLUIR = "GIA_ITCD_BEM_TRIBUTAVEL_INCLUIR";
	public static final String DESC_GIA_ITCD_FICHA_IMOVEL_RURAL_INCLUIR = "GIA_ITCD_FICHA_IMOVEL_RURAL_INCLUIR";
	public static final String DESC_GIA_ITCD_BEM_TRIBUTAVEL_ALTERAR = "GIA_ITCD_BEM_TRIBUTAVEL_ALTERAR";
	public static final String DESC_GIA_ITCD_FICHA_IMOVEL_RURAL_ALTERAR = "GIA_ITCD_FICHA_IMOVEL_RURAL_ALTERAR";
	public static final String DESC_GIA_ITCD_FICHA_IMOVEL_URBANO_INCLUIR = "GIA_ITCD_FICHA_IMOVEL_URBANO_INCLUIR";
	public static final String DESC_GIA_ITCD_FICHA_IMOVEL_URBANO_ALTERAR = "GIA_ITCD_FICHA_IMOVEL_URBANO_ALTERAR";
	public static final String DESC_GIA_ITCD_INVENTARIO_ARROLAMENTO_INCLUIR = "GIA_ITCD_INVENTARIO_ARROLAMENTO_INCLUIR";
	public static final String DESC_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_INCLUIR = "GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_INCLUIR";
	public static final String DESC_GIA_ITCD_INVENTARIO_ARROLAMENTO_ALTERAR = "GIA_ITCD_INVENTARIO_ARROLAMENTO_ALTERAR";
	public static final String DESC_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALTERAR = "GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALTERAR";
	public static final String DESC_GIA_ITCD_DOACAO_INCLUIR = "DESC_GIA_ITCD_DOACAO_INCLUIR";
	public static final String DESC_GIA_ITCD_DOACAO_ALTERAR = "GIA_ITCD_DOACAO_ALTERAR";
	public static final String DESC_GIA_ITCD_SEPARACAO_INCLUIR = "GIA_ITCD_SEPARACAO_INCLUIR";
	public static final String DESC_GIA_ITCD_SEPARACAO_ALTERAR = "GIA_ITCD_SEPARACAO_ALTERAR";
	public static final String DESC_AVALIACAO_BEM_TRIBUTAVEL_INCLUIR = "AVALIACAO_BEM_TRIBUTAVEL_INCLUIR";
	public static final String DESC_AVALIACAO_BEM_TRIBUTAVEL_ALTERAR = "AVALIACAO_BEM_TRIBUTAVEL_ALTERAR";
	
	private static int domnIndice[] = { PROPRIA_TRANSACAO, 
		PARAMETRO_LEGISLACAO_INCLUIR, 
		PARAMETRO_LEGISLACAO_ALTERAR, 
		TELA_FUNCIONALIDADE_INCLUIR,
		TELA_FUNCIONALIDADE_ALTERAR,
		GIA_ITCD_INCLUIR,
		GIA_ITCD_ALTERAR,
		GIA_ITCD_BEM_TRIBUTAVEL_INCLUIR ,
		GIA_ITCD_FICHA_IMOVEL_RURAL_INCLUIR ,
		GIA_ITCD_BEM_TRIBUTAVEL_ALTERAR ,
		GIA_ITCD_FICHA_IMOVEL_RURAL_ALTERAR, 
		GIA_ITCD_FICHA_IMOVEL_URBANO_INCLUIR ,
		GIA_ITCD_FICHA_IMOVEL_URBANO_ALTERAR ,
		GIA_ITCD_INVENTARIO_ARROLAMENTO_INCLUIR ,
		GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_INCLUIR ,
		GIA_ITCD_INVENTARIO_ARROLAMENTO_ALTERAR ,
		GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALTERAR ,
		GIA_ITCD_DOACAO_INCLUIR ,
		GIA_ITCD_DOACAO_ALTERAR,
		GIA_ITCD_SEPARACAO_INCLUIR,
		GIA_ITCD_SEPARACAO_ALTERAR,
		AVALIACAO_BEM_TRIBUTAVEL_INCLUIR,
		AVALIACAO_BEM_TRIBUTAVEL_ALTERAR};
	private static String domnDescricao[] = { DESC_PROPRIA_TRANSACAO, 
		DESC_PARAMETRO_LEGISLACAO_INCLUIR, 
		DESC_PARAMETRO_LEGISLACAO_ALTERAR, 
		DESC_TELA_FUNCIONALIDADE_INCLUIR, 
		DESC_TELA_FUNCIONALIDADE_ALTERAR,
		DESC_GIA_ITCD_INCLUIR,
		DESC_GIA_ITCD_ALTERAR,
		DESC_GIA_ITCD_BEM_TRIBUTAVEL_INCLUIR ,
		DESC_GIA_ITCD_FICHA_IMOVEL_RURAL_INCLUIR ,
		DESC_GIA_ITCD_BEM_TRIBUTAVEL_ALTERAR ,
		DESC_GIA_ITCD_FICHA_IMOVEL_RURAL_ALTERAR ,
		DESC_GIA_ITCD_FICHA_IMOVEL_URBANO_INCLUIR ,
		DESC_GIA_ITCD_FICHA_IMOVEL_URBANO_ALTERAR ,
		DESC_GIA_ITCD_INVENTARIO_ARROLAMENTO_INCLUIR ,
		DESC_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_INCLUIR ,
		DESC_GIA_ITCD_INVENTARIO_ARROLAMENTO_ALTERAR ,
		DESC_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALTERAR ,
		DESC_GIA_ITCD_DOACAO_INCLUIR ,
		DESC_GIA_ITCD_DOACAO_ALTERAR,
		DESC_GIA_ITCD_SEPARACAO_INCLUIR,
		DESC_GIA_ITCD_SEPARACAO_ALTERAR,
		DESC_AVALIACAO_BEM_TRIBUTAVEL_INCLUIR,
		DESC_AVALIACAO_BEM_TRIBUTAVEL_ALTERAR};

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnNumeroParticao()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnNumeroParticao(int valorConstante)
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

