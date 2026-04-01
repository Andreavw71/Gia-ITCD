/*
* Secretaria de Estado de Fazenda de Mato Grosso ï¿½ Sefaz-MT
* Arquivo : ServidorSefazIntegracaoVo.java
* CriaÃ§ao : Outubro de 2007
* Revisao :
* Log :
* $Id: ServidorSefazIntegracaoVo.java,v 1.2 2009/03/02 13:53:12 ricardo.moraes Exp $
*/
package br.gov.mt.sefaz.itc.util.integracao.gestaopessoas;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.itc.util.integracao.contacorrente.ContaCorrenteIntegracaoVo;

import java.util.Collection;
import java.util.Iterator;

import sefaz.mt.cadastro.integracao.ContribuinteVO;
import sefaz.mt.gestaopessoas.integracao.ServidorSefazVO;


/**
 * @author Leandro Dorileo
 * @version $Revision: 1.2 $
 */
public class ServidorSefazIntegracaoVo implements EntidadeFacade, VoIntegrador
{
	private Collection collVO;
	private boolean consultaCompleta;
	private EntidadeFacade parametroConsulta;
	private long usuarioLogado;
	private String mensagem;
	private String nomeSistema;
	private static final String NOME_SISTEMA = "ITC";
	private static final long serialVersionUID = Long.MAX_VALUE;
	//Atributos ServidorSefazVO
	private CargoIntegracaoVo cargo;
//	private DomnSituacaoRegistro situacaoRegistro;
//	private DomnTipoServidor tipoServidor;
//	private EnderecoIntegracaoVo endereco;
	private Long numrCPF;
	private Long numrMatricula;
	private String nomeServidor;
	private UnidadeSefazIntegracaoVo unidadeSefaz;

	public ServidorSefazIntegracaoVo()
	{
		super();
	}

	public ServidorSefazIntegracaoVo(ServidorSefazIntegracaoVo servidorSefazIntegracaoVo)
	{
		super();
		setParametroConsulta(servidorSefazIntegracaoVo);
	}

	public ServidorSefazIntegracaoVo(Collection servidorSefazIntegracaoVo) throws ParametroObrigatorioException
	{
		super();
		if (Validador.isCollectionValida(servidorSefazIntegracaoVo))
		{
			Iterator it = servidorSefazIntegracaoVo.iterator();
			while (it.hasNext())
			{
				Object objeto = it.next();
				if (objeto instanceof ContribuinteVO)
				{
					if (Validador.isCollectionValida(getCollVO()))
					{
						getCollVO().add(new ServidorSefazIntegracaoVo((ServidorSefazVO) objeto));
					}
					else
					{
						Collection lista = getCollVO();
						lista.add(new ServidorSefazIntegracaoVo((ServidorSefazVO) objeto));
						setCollVO(lista);
					}
				}
			}
		}
	}
	{
		setNomeSistema(NOME_SISTEMA);
	}

	public ServidorSefazIntegracaoVo(ServidorSefazVO servidorSefazVO) throws ParametroObrigatorioException
	{
		super();
		if (servidorSefazVO != null)
		{
			if (Validador.isObjetoValido(servidorSefazVO.getCargo()))
			{
				setCargo(new CargoIntegracaoVo(servidorSefazVO.getCargo()));
			}
			if (Validador.isNumericoValido(servidorSefazVO.getNumrCPF()))
			{
				setNumrCPF(servidorSefazVO.getNumrCPF());
			}
			if (Validador.isNumericoValido(servidorSefazVO.getNumrMatricula()))
			{
				setNumrMatricula(servidorSefazVO.getNumrMatricula());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o número da matrï¿½cula do servidor" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(servidorSefazVO.getNomeServidor()))
			{
				setNomeServidor(servidorSefazVO.getNomeServidor());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o nome do servidor" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isObjetoValido(servidorSefazVO.getUnidadeSefaz()))
			{
				setUnidadeSefaz(new UnidadeSefazIntegracaoVo(servidorSefazVO.getUnidadeSefaz()));
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a unidade da SEFAZ do servidor" + 
								  QUE_E_OBRIGATORIO);
			}
		}
	}
	
	public String toString() 
	{
		return String.valueOf(numrMatricula);
	}

	public int compare(Object t, Object t1)
	{
		return ((ServidorSefazVO) t).getNumrCPF().compareTo(((ServidorSefazVO) t1).getNumrCPF());
	}

	public int compareTo(Object t)
	{
		return compare(this, t);
	}

	/**
	 * Configura o valor do atributo <code>nomeSistema</code> com o valor do parametro
	 * <code>nomeSistema</code>
	 *
	 * @param nomeSistema       Nome a ser configurado como do sistema
	 * @implememented by Leandro Dorileo
	 */
	public void setNomeSistema(String nomeSistema) throws ProibidoMudarNomeSistemaException
	{
		if (Validador.isStringValida(nomeSistema) && 
				 nomeSistema.equals(br.gov.mt.sefaz.itc.util.EntidadeVo.NOME_SISTEMA))
		{
			this.nomeSistema = nomeSistema;
		}
		else
			throw new ProibidoMudarNomeSistemaException();
	}

	/**
	 * Retorna apropriadamente o nome do sistema
	 * @return      nome do sistema
	 * @implemented by Leandro Dorileo
	 */
	public String getNomeSistema()
	{
		return (Validador.isStringValida(this.nomeSistema)) ? this.nomeSistema : STRING_VAZIA;
	}

	/**
	 * Configura apropriadamente o atributo <code>collVO</code> usando o parametro
	 * <code>collVO</code>.
	 * @param collVO        collecao de VO`s a ser configurada como collVO do VO
	 * @implemented by Leandro Dorileo
	 */
	public void setCollVO(Collection collVO)
	{
		this.collVO = collVO;
	}

	/**
	 * Caso não tenha sido criado um collVO e se precise de um antes de manipula-lo
	 * criamos-o aqui.
	 * @return      just created and set collVO to collVO attribute
	 * @implemented by Leandro Dorileo
	 */
	private Collection createCollVO()
	{
		this.setCollVO(collVO);
		return this.collVO;
	}

	/**
	 * Retorna a collVO do VO em questï¿½o
	 * @return  collVO do VO em questï¿½o
	 */
	public Collection getCollVO()
	{
		return (Validador.isCollectionValida(this.collVO)) ? this.collVO : createCollVO();
	}

	public boolean isExiste()
	{
		return !this.equals(new ContaCorrenteIntegracaoVo());
	}

	/**
	 * Verifica se a collVO existe e se é vï¿½lida
	 * @return      true caso a <code>collVO</code> seja vï¿½lida e false caso contrï¿½rio
	 */
	public boolean isExisteCollVO()
	{
		return Validador.isCollectionValida(this.collVO);
	}

	/**
	 * Verifica se a consulta ï¿½ parametrizada ou não. A consulta pode ser parametrizada
	 * ou completa, portanto, pode ser necessï¿½rio verificar o tipo da consulta.
	 *
	 * @return      true caso <code>parametroConsulta</code> seja um objeto vï¿½lido e false caso contrï¿½rio
	 */
	public boolean isConsultaParametrizada()
	{
		return (this.getParametroConsulta() != null);
	}

	/**
	 * Verifica se a consulta ï¿½ completa ou não.
	 *
	 * @return     estado de <code>consultaCompleta</code>
	 */
	public boolean isConsultaCompleta()
	{
		return consultaCompleta;
	}

	/**
	 * Configura o atributo <code>consultaCompleta</code> com o valor do parametro
	 * <code>consultaCompleta</code>.
	 *
	 * @param consultaCompleta    valor a ser configurado no atributo <code>consultaCompleta</code>
	 */
	public void setConsultaCompleta(boolean consultaCompleta)
	{
		this.consultaCompleta = consultaCompleta;
	}

	/**
	 * Configura o atributo <code>usuarioLogado</code> com o valor do parametro
	 * <code>usuarioLogado</code>.
	 *
	 * @param usuarioLogado    valor a ser configurado no atributo <code>usuarioLogado</code>
	 */
	public void setUsuarioLogado(long usuarioLogado)
	{
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * Retorna o valor configurado no atributo <code>usuarioLogado</code>
	 *
	 * @return     valor configurado no atributo <code>usuarioLogado</code>
	 */
	public long getUsuarioLogado()
	{
		return this.usuarioLogado;
	}

	/**
	 * Configura o valor do atributo <code>mensagem</code> com o valor do parâmetro
	 * <code>mensagem</code>
	 *
	 * @param mensagem   String contendo a mensagem a ser configurada
	 */
	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	/**
	 * Retorna o valor configurado no atributo <code>mensagem</code>
	 *
	 * @return  valor configurado no atributo <code>mensagem</code>
	 */
	public String getMensagem()
	{
		return this.mensagem;
	}

	/**
	 * Configura o valor do atributo <code>parametro</code>  com o valor do parâmetro
	 * <code>parametroConsulta</code>
	 *
	 * @param parametroConsulta   instancia de <code>EntidadeFacade</code> a ser configurada
	 */
	public void setParametroConsulta(EntidadeFacade parametroConsulta)
	{
		this.parametroConsulta = parametroConsulta;
	}

	/**
	 * Retorna o valor configurado no atributo <code>parametroConsulta</code>
	 *
	 * @return     valor configurado no atributo <code>parametroConsulta</code>
	 */
	public EntidadeFacade getParametroConsulta()
	{
		return parametroConsulta;
	}
	//Métodos ServidorSefazVO

	public void setCargo(CargoIntegracaoVo cargo)
	{
		this.cargo = cargo;
	}

	public CargoIntegracaoVo getCargo()
	{
		return cargo;
	}

/*	public void setSituacaoRegistro(DomnSituacaoRegistro situacaoRegistro)
	{
		this.situacaoRegistro = situacaoRegistro;
	}

	public DomnSituacaoRegistro getSituacaoRegistro()
	{
		return situacaoRegistro;
	}*/

/*	public void setTipoServidor(DomnTipoServidor tipoServidor)
	{
		this.tipoServidor = tipoServidor;
	}

	public DomnTipoServidor getTipoServidor()
	{
		return tipoServidor;
	}*/

/*	public void setEndereco(EnderecoIntegracaoVo endereco)
	{
		this.endereco = endereco;
	}

	public EnderecoIntegracaoVo getEndereco()
	{
		return endereco;
	}*/

	public void setNumrCPF(Long numrCPF)
	{
		this.numrCPF = numrCPF;
	}

	public Long getNumrCPF()
	{
		return numrCPF;
	}

	public void setNumrMatricula(Long numrMatricula)
	{
		this.numrMatricula = numrMatricula;
	}

	public Long getNumrMatricula()
	{
		if (!Validador.isObjetoValido(numrMatricula))
		{
			setNumrMatricula(new Long(0));
		}
		return numrMatricula;
	}

	public void setNomeServidor(String nomeServidor)
	{
		this.nomeServidor = nomeServidor;
	}

	public String getNomeServidor()
	{
		return nomeServidor;
	}

	public void setUnidadeSefaz(UnidadeSefazIntegracaoVo unidadeSefaz)
	{
		this.unidadeSefaz = unidadeSefaz;
	}

	public UnidadeSefazIntegracaoVo getUnidadeSefaz()
	{
		if(!Validador.isObjetoValido(unidadeSefaz))
		{
			return new UnidadeSefazIntegracaoVo();
		}
		return unidadeSefaz;
	}
	
	public String getDadosServidorFormatado()
	{
		if(Validador.isStringValida(getNumrMatriculaFormatado()) && Validador.isStringValida(getNomeServidorFormatado()))
		{
			return getNumrMatriculaFormatado() + " - " + getNomeServidorFormatado();	
		}
		return "";
	}
	
	public String getNumrMatriculaFormatado()
	{
		if(Validador.isNumericoValido(getNumrMatricula()))
		{
			return String.valueOf(getNumrMatricula());
		}
		return "";
	}
	
	public String getNomeServidorFormatado()
	{
	   if(Validador.isStringValida(getNomeServidor()))
	   {
	      return getNomeServidor();
	   }
	   return "";		
	}
}
