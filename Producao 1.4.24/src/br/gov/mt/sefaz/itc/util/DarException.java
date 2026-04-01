package br.gov.mt.sefaz.itc.util;

import br.com.abaco.util.exceptions.AbstractAbacoException;


/**
 * @author Ricardo Vitor de Oliveira Moraes
 * @version
 */
public class DarException extends AbstractAbacoException
{
	private String message = null;
	private Throwable cause = null;

	public DarException(String message)
	{
		super();
		this.message = message;
	}
	
	public DarException(String message, Exception causa)
	{
		this.cause = causa;
		this.message = message + " - " + causa.getMessage();
	}

	public String getMessage()
	{
		return this.message;
	}

	public Throwable getCause()
	{
		return this.cause;
	}
}
