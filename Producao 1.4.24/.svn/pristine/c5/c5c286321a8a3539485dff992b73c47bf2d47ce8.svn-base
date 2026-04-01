/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: EntidadeVo.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 05/10/2007
 */
package br.gov.mt.sefaz.itc.util;

import br.com.abaco.util.Paginador;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.log.informacao.LogVo;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
public class EntidadeVo<T extends br.com.abaco.util.EntidadeVo> extends br.com.abaco.util.EntidadeVo 
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final String NOME_SISTEMA = "ITC";	
	public static final String MASCARA_DATA_FORMATADA = "dd/MM/yyyy";
	private Paginador<T> paginador;
	

	public EntidadeVo(String titulo, String mensagem)
	{
		super(titulo, mensagem);
	}

	public EntidadeVo(Collection collVo)
	{
		super(collVo);
	}

	public EntidadeVo(long codigo)
	{
		super(codigo);
	}

	public EntidadeVo()
	{
		super();
	}
	{
		setNomeSistema(NOME_SISTEMA);
	}

	public final void setNomeSistema(String nomeSistema) throws ProibidoMudarNomeSistemaException
	{
		if (Validador.isStringValida(nomeSistema) && nomeSistema.equals(NOME_SISTEMA))
		{
			super.setNomeSistema(nomeSistema);
		}
		else
		{
			throw new ProibidoMudarNomeSistemaException();
		}
	}

	public String getCodigoFormatado()
	{
		if (!Validador.isNumericoValido(this.getCodigo()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getCodigo());
	}

	/**
	 * @return LogVo
	 * @deprecated Favor utilizar getLogSefazVo()
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public LogVo getLogVo()
	{
		return super.getLogVo();
	}

	/**
	 * @param log
	 * @deprecated Favor utilizar setLogSefazVo()
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void setLogVo(LogVo log)
	{
		super.setLogVo(log);
	}

	public T getParametroConsulta()
	{
		return (T) super.getParametroConsulta();
	}

	public void setParametroConsulta(T parametroConsulta)
	{
		super.setParametroConsulta(parametroConsulta);
	}

	public void setPaginador(Paginador<T> paginador)
	{
		this.paginador = paginador;
	}

	public Paginador<T> getPaginador()
	{
		if(!Validador.isObjetoValido(paginador))
		{
			setPaginador(new Paginador<T>(getCollVO()));
		}
		return paginador;
	}
	
	public Collection<T> getCollVO()
	{
		return super.getCollVO();
	}
   
   /**
    * <b>Objetivo:</b> método utilitario para
    * converter java.util.Collection em objeto java.util.List
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public List<T> getListVo()
   {
     return new ArrayList(super.getCollVO());
   }
   
   public Set<T> getCollVOSet() {
       return new HashSet<T>(super.getCollVO());
   }
}
