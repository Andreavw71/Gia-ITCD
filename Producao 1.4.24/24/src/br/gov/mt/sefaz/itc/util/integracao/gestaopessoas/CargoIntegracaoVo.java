package br.gov.mt.sefaz.itc.util.integracao.gestaopessoas;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.itc.util.integracao.contacorrente.ContaCorrenteIntegracaoVo;

import java.util.Collection;

import sefaz.mt.gestaopessoas.integracao.CargoVO;
import sefaz.mt.gestaopessoas.integracao.ServidorSefazVO;


public class CargoIntegracaoVo implements EntidadeFacade, VoIntegrador
{
	private boolean consultaCompleta;
	private Collection collVO;
	private EntidadeFacade parametroConsulta;
	private long usuarioLogado;
	private String mensagem;
	private String nomeSistema;
	private static final long serialVersionUID = Long.MAX_VALUE;
	private Long numrCargoSeqc;
	private String siglCargo;
	private String descCargo;

	public CargoIntegracaoVo()
	{
	}

	public CargoIntegracaoVo(CargoVO cargoVO) throws ParametroObrigatorioException
	{
		super();
		if (cargoVO != null)
		{
			if (Validador.isNumericoValido(cargoVO.getNumrCargoSeqc()))
			{
				setNumrCargoSeqc(cargoVO.getNumrCargoSeqc());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o número do cargo do servidor" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(cargoVO.getSiglCargo()))
			{
				setSiglCargo(cargoVO.getSiglCargo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a sigla do cargo do servidor" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(cargoVO.getDescCargo()))
			{
				setDescCargo(cargoVO.getDescCargo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a descrição do cargo do servidor" + 
								  QUE_E_OBRIGATORIO);
			}
		}
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
	 * Retorna a collVO do VO em questão
	 * @return  collVO do VO em questão
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
	 * Verifica se a collVO existe e se é válida
	 * @return      true caso a <code>collVO</code> seja válida e false caso contrário
	 */
	public boolean isExisteCollVO()
	{
		return Validador.isCollectionValida(this.collVO);
	}

	/**
	 * Verifica se a consulta é parametrizada ou não. A consulta pode ser parametrizada
	 * ou completa, portanto, pode ser necessário verificar o tipo da consulta.
	 *
	 * @return      true caso <code>parametroConsulta</code> seja um objeto válido e false caso contrário
	 */
	public boolean isConsultaParametrizada()
	{
		return (this.getParametroConsulta() != null);
	}

	/**
	 * Verifica se a consulta é completa ou não.
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

	public void setNumrCargoSeqc(Long numrCargoSeqc)
	{
		this.numrCargoSeqc = numrCargoSeqc;
	}

	public Long getNumrCargoSeqc()
	{
		return numrCargoSeqc;
	}

	public void setSiglCargo(String siglCargo)
	{
		this.siglCargo = siglCargo;
	}

	public String getSiglCargo()
	{
		if (Validador.isStringValida(siglCargo))
		{
			return siglCargo;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	public void setDescCargo(String descCargo)
	{
		this.descCargo = descCargo;
	}

	public String getDescCargo()
	{
		return descCargo;
	}
}
