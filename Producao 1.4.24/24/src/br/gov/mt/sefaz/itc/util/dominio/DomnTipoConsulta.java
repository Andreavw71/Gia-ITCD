package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


/**
 * Domínio responsável por manter as opções de consulta para quando usuário for servidor SEFAZ.
 * @author Ricardo Vitor de Oliveira Moraes
 * @version $Revision: 1.2 $
 */
public class DomnTipoConsulta extends AbstractDominioNumericoAbaco
{

	public static final int CONTRIBUINTE_RESPONSAVEL  = 1;
	public static final int NUMERO_GIA = 2;
	
	public static final String DESCRICAO_CONTRIBUINTE_RESPONSAVEL = "Contribuinte Responsável";
	public static final String DESCRICAO_NUMERO_GIA = "Número da GIA-ITCD";

	private static int domnIndice[] = 
	  { CONTRIBUINTE_RESPONSAVEL, NUMERO_GIA };
	private static String domnDescricao[] = 
	  { DESCRICAO_CONTRIBUINTE_RESPONSAVEL, DESCRICAO_NUMERO_GIA};


	public DomnTipoConsulta()
	{
		super(-1);
	}
	
	public DomnTipoConsulta(int valorConstante)
	{
		super(valorConstante);
	}

	public int[] getDomnIndice()
	{
		return domnIndice;
	}

	public String[] getDomnDescricao()
	{
		return domnDescricao;
	}

	public int getKey()
	{
		return super.getValorCorrente();
	}	
}
