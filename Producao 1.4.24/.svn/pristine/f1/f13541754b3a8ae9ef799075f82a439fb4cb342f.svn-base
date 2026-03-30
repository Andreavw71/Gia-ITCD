package br.gov.mt.sefaz.itc.util;

import java.math.BigDecimal;


/**
 * @author Ricardo Vitor de Oliveira Moraes
 * @version
 */
public class NumeroUtil
{

	private static final int PADRAO_ARREDONDAMENTO = BigDecimal.ROUND_HALF_EVEN;
	private static final int QUANTIDADE_CASAS_DECIMAIS_PADRAO = 2;

	/**
	 * @param valor
	 * @param qtdeCasaDecimal
	 * @return
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static double arredondaNumero(double valor, int qtdeCasaDecimal)
	{
		BigDecimal val = new BigDecimal(valor);
		val = val.setScale(qtdeCasaDecimal, PADRAO_ARREDONDAMENTO);
		return val.doubleValue();
	}

	/**
	 * @param valor
	 * @return
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static double arredondaNumero(double valor)
	{
		BigDecimal val = new BigDecimal(valor);
		val = val.setScale(QUANTIDADE_CASAS_DECIMAIS_PADRAO, PADRAO_ARREDONDAMENTO);
		return val.doubleValue();
	}	
}
