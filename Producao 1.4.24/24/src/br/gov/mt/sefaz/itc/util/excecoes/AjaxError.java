package br.gov.mt.sefaz.itc.util.excecoes;

import br.com.abaco.util.exceptions.AbstractAbacoException;


public class AjaxError extends AbstractAbacoException
{
	public AjaxError()
	{
		super();
	}
	
	public AjaxError(String mensagem)
	{
		super(mensagem);		
	}
	
	public AjaxError(Exception e)
	{
		this(e.getMessage());
	}
}