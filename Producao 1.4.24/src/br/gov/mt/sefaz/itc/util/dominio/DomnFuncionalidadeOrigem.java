package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


/**
 * Domínio responsável por manter as funcionalidades do sistema de forma padrão, para que se possa utilizar do atributo origem.
 * @author Ricardo Vitor de Oliveira Moraes
 * @version $Revision: 1.3 $
 */
public class DomnFuncionalidadeOrigem extends AbstractDominioNumericoAbaco
{

	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int ALTERAR_GIA_ITCD = 1;
	public static final int CONSULTAR_GIA_ITCD = 2;
	public static final int INATIVAR_GIA_ITCD = 3;
	public static final int REATIVAR_GIA_ITCD = 4;
	public static final int AVALIAR_GIA_ITCD = 5;
	public static final int ALTERAR_STATUS_MANUAL = 6;
	public static final int IMPRIMIR_DOCUMENTOS_AVALIACAO = 7;
	public static final int ALTERAR_AVALIACAO_GIA_ITCD = 8 ;

	public static final String DESCRICAO_ALTERAR_GIA_ITCD = "Alterar GIA-ITCD";
	public static final String DESCRICAO_CONSULTAR_GIA_ITCD = "Consultar GIA-ITCD";
	public static final String DESCRICAO_INATIVAR_GIA_ITCD = "Inativar GIA-ITCD";
	public static final String DESCRICAO_REATIVAR_GIA_ITCD = "Reativar GIA-ITCD";
	public static final String DESCRICAO_AVALIAR_GIA_ITCD = "Avaliar GIA-ITCD";
	public static final String DESCRICAO_ALTERAR_STATUS_MANUAL = "Alterar Status Manual GIA-ITCD";
	public static final String DESCRICAO_IMPRIMIR_DOCUMENTOS_AVALIACAO = "Imprimir Documentos da Avaliação";
	public static final String DESCRICAO_ALTERAR_AVALIACAO_GIA_ITCD = "Alterar Avaliação GIA-ITCD";

	public DomnFuncionalidadeOrigem() 
	{
		super(-1);
	}
	
	public DomnFuncionalidadeOrigem(int valorConstante)
	{
		super(valorConstante);
	}
	
	public int[] getDomnIndice()
	{
		return new int[]
		{
		   ALTERAR_GIA_ITCD
			,CONSULTAR_GIA_ITCD
			,INATIVAR_GIA_ITCD
			,REATIVAR_GIA_ITCD
			,AVALIAR_GIA_ITCD
			,ALTERAR_STATUS_MANUAL
			,IMPRIMIR_DOCUMENTOS_AVALIACAO
			,ALTERAR_AVALIACAO_GIA_ITCD
		};
	}

	public String[] getDomnDescricao()
	{
		return new String[]
		{
		   DESCRICAO_ALTERAR_GIA_ITCD
			,DESCRICAO_CONSULTAR_GIA_ITCD
			,DESCRICAO_INATIVAR_GIA_ITCD
			,DESCRICAO_REATIVAR_GIA_ITCD
			,DESCRICAO_AVALIAR_GIA_ITCD
			,DESCRICAO_ALTERAR_STATUS_MANUAL
			,DESCRICAO_IMPRIMIR_DOCUMENTOS_AVALIACAO
			,DESCRICAO_ALTERAR_AVALIACAO_GIA_ITCD
		};
	}

	public int getKey()
	{
		return super.getValorCorrente();
	}	
}
