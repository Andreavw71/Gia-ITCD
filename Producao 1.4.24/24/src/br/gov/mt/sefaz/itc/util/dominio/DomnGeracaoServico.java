package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


public class DomnGeracaoServico extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	
	
	public static final int NAO_PROCESSADO = 1;
   public static final int EM_PROCESSAMENTO = 2;
   public static final int PROCESSADO =  3;
   public static final int PROCESSAMENTO_COM_ERRO = 4;

	
   public static final String  DESCRICAO_NAO_PROCESSADO = "NAO_PROCESSADO";
   public static final String  DESCRICAO_EM_PROCESSAMENTO= "EM PROCESSAMENTO";   
	public static final String  DESCRICAO_PROCESSADO= "PROCESSADO";
	public static final String  DESCRICAO_PROCESSAMENTO_COM_ERRO = "PROCESSAMENTO COM ERRO";
	

	
	public DomnGeracaoServico()
	{
		super();
	}

	public DomnGeracaoServico(int valorConstante)
	{
		super(valorConstante);
	}

	public int[] getDomnIndice()
	{
		return new int[]
		{
			NAO_PROCESSADO  ,
			EM_PROCESSAMENTO,
			PROCESSADO,		
	      PROCESSAMENTO_COM_ERRO
		};
	}

	public String[] getDomnDescricao()
	{
		return new String[]
		{
	    DESCRICAO_NAO_PROCESSADO ,
	    DESCRICAO_EM_PROCESSAMENTO ,
	    DESCRICAO_PROCESSADO ,
	    DESCRICAO_PROCESSAMENTO_COM_ERRO
		};
	}
	
	public int getKey()
	{
		return super.getValorCorrente();
	}
}
  
