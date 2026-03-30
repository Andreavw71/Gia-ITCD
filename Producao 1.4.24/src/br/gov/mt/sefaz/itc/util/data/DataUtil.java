package br.gov.mt.sefaz.itc.util.data;

import br.com.abaco.util.Validador;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import sefaz.mt.util.SefazDataHora;


public class DataUtil
{
	public static int converteMilissegundosDataEmDias(long milissegundos)
	{
		return (int) (milissegundos / getMilissegundosDia());
	}
	
	public static long getMilissegundosDia()
	{
		return 24 * 60 * 60 * 1000;
	}
	
	public static Date adicionaDia(Date data, int quantidadeDias)
	{
		return adicionaValoresData(data, Calendar.DATE, quantidadeDias);	
	}
   
   public static Date adicionaMes(Date data, int quantidadeMeses)
   {
      return adicionaValoresData(data, Calendar.MONTH, quantidadeMeses);  
   }
   
	
	public static Date subtraiDia(Date data, int quantidadeDias)
	{
	   return adicionaValoresData(data, Calendar.DATE, -quantidadeDias);
	}
	
	public static Date adicionaValoresData(Date data, int campo, int valor)
	{
		if(data != null)
		{
		   Calendar c = Calendar.getInstance();
		   c.setTime(data);
			c.add(campo, valor);
			return c.getTime();
		}
		return null;		
	}
	
	public static Date subtraiValoresData(Date data, int campo, int valor)
	{
		if(data != null)
		{
			Calendar c = Calendar.getInstance();
			c.setTime(data);
			c.add(campo, -valor);
			return c.getTime();
		}
		return null;
	}
	
	public static String formataData(Date data)
	{
		return formataData(data, "dd/MM/yyyy");
	}
	
	public static String formataData(Date data, String pattern)
	{
		if(Validador.isDataValida(data) && Validador.isStringValida(pattern))
		{
			return new SimpleDateFormat(pattern).format(data);
		}
		return "";
	}
   
   public static Date dataUltimoDiaMes(Date data)
   {
      if(Validador.isDataValida(data))
      {
         SefazDataHora sfData = new SefazDataHora(data);
         int ultimoDiaMes = sfData.getUltimoDiaDoMes();
         return new SefazDataHora(sfData.getAno(), sfData.getMes(), ultimoDiaMes).toUtilData();
      }
      return null;
   }
}
