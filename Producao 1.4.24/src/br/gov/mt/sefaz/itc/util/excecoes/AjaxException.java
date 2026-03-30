package br.gov.mt.sefaz.itc.util.excecoes;

import br.com.abaco.util.exceptions.AbstractAbacoException;


public class AjaxException extends AbstractAbacoException
{
	public AjaxException()
	{
		super();
	}
	
	public AjaxException(String mensagem)
	{
		super(mensagem);		
	}
	
	public AjaxException(Exception e)
	{
		this(e.getMessage());
	}
}