package br.gov.mt.sefaz.itc.util.pdf;

/**
 * Classe utilizada para validar os objetos mais utilizados.
 * @author Fábio Vanzella
 * @version $Revision: 1.1.1.1 $
 */
public final class PDFValidador
{
	private PDFValidador()
	{
	}

	/**
	 * Método utilizado para validar se uma string é valida.
	 * @param valorString
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	public static String isStringValida(String valorString)
	{
		if (valorString != null && !valorString.trim().equals(""))
		{
			return valorString;
		}
		else
		{
			return "";
		}
	}

	/**
	 * Método utilizado para validar um número.
	 * @param numero
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	public static String isNumericoValido(short numero)
	{
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número.
	 * @param numero
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	public static String isNumericoValido(int numero)
	{
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número.
	 * @param numero
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	public static String isNumericoValido(long numero)
	{
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número.
	 * @param numero
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	public static String isNumericoValido(float numero)
	{
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. 
	 * @param numero
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	public static String isNumericoValido(double numero)
	{
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número.
	 * @param numero
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	public static String isNumericoValido(Byte numero)
	{
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número.
	 * @param numero
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	public static String isNumericoValido(Short numero)
	{
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número.
	 * @param numero
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	public static String isNumericoValido(Integer numero)
	{
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número.
	 * @param numero
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	public static String isNumericoValido(Long numero)
	{
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número.
	 * @param numero
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	public static String isNumericoValido(Float numero)
	{
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número.
	 * @param numero
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	public static String isNumericoValido(Double numero)
	{
		return validarNumero(numero);
	}

	/**
	 * Método utilizado para validar um número. 
	 * @param numero
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	private static String validarNumero(final double numero)
	{
		if (numero != 0.0d)
		{
			long numeroLong = (long) numero;
			return String.valueOf(numeroLong);
		}
		else
		{
			return "";
		}
	}

	/**
	 * Método utilizado para validar um número.
	 * @param numero
	 * @return String
	 * @implemented by Fábio Vanzella
	 */
	private static String validarNumero(final Number numero)
	{
		if (numero != null && numero.doubleValue() != 0.0d)
		{
			return String.valueOf(numero);
		}
		else
		{
			return "";
		}
	}
}
