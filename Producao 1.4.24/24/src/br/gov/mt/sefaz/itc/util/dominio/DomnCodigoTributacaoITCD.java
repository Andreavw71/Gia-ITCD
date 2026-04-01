package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;


public enum DomnCodigoTributacaoITCD
{
	ITCD_NORMAL (ConfiguracaoITCD.CODIGO_TRIBUTO_ARRECADACAO)
	, ITCD_PARCELAMENTO (ConfiguracaoITCD.CODIGO_TRIBUTO_ARRECADACAO_PARCELAMENTO)
   , ITCD_LANCAMENTO_OFICIO_PARCELAMENTO (ConfiguracaoITCD.CODIGO_TRIBUTO_LANCAMENTO_OFICIO_PARCELAMENTO);
	
	private int codigo;
	
	DomnCodigoTributacaoITCD(int codigo)
	{
		this.codigo = codigo;
	}
	
	public int getCodigo()
	{
		return this.codigo;
	}
	
	public static boolean isCodigoTributoValido(Integer codigo)
	{
		if(Validador.isNumericoValido(codigo))
		{
		   for(DomnCodigoTributacaoITCD tributo : DomnCodigoTributacaoITCD.values())
		   {
		      if(tributo.codigo == codigo)
		      {
		         return true;
		      }
		   }
		}
		return false;
	}
}
