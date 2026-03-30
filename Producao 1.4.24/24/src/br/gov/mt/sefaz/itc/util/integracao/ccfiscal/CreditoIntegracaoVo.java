package br.gov.mt.sefaz.itc.util.integracao.ccfiscal;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.ccfiscal.integracao.CreditoVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class CreditoIntegracaoVo extends CreditoVO implements EntidadeFacade, VoIntegrador
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private static final String NOME_SISTEMA = "SNE";
	private boolean consultaCompleta;
	private Collection collVO;
	private EntidadeFacade parametroConsulta;
	private int origem;
	private long usuarioLogado;
	private String mensagem;
	private String nomeSistema;
	private String titulo;
	private boolean semEsteVo;

	public CreditoIntegracaoVo()
	{
		super();
	}

	public CreditoIntegracaoVo(CreditoIntegracaoVo creditoIntegracaoVo)
	{
		this();
		setParametroConsulta(creditoIntegracaoVo);
	}

	public CreditoIntegracaoVo(CreditoVO creditoVO) throws ParametroObrigatorioException
	{
		super();
		if (creditoVO != null)
		{
			if (Validador.isNumericoValido(creditoVO.getCodigoUsuario()))
			{
				setCodigoUsuario(creditoVO.getCodigoUsuario());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do usuário " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(creditoVO.getNumeroPessoaContribuinte()))
			{
				setNumeroPessoaContribuinte(creditoVO.getNumeroPessoaContribuinte());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o número do contribuinte " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isDataValida(creditoVO.getDataPagamento()))
			{
				setDataPagamento(creditoVO.getDataPagamento());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a data de pagamento " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(creditoVO.getCodigoIdentificacaoNumeroDocumentoPagamento()))
			{
				setCodigoIdentificacaoNumeroDocumentoPagamento(creditoVO.getCodigoIdentificacaoNumeroDocumentoPagamento());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o número do documento de pagamento " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(creditoVO.getValorLancamento()))
			{
				setValorLancamento(creditoVO.getValorLancamento());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o valor do lançamento " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(creditoVO.getCodigoUnidadeSefaz()))
			{
				setCodigoUnidadeSefaz(creditoVO.getCodigoUnidadeSefaz());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código da Unidade Sefaz " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isDataValida(creditoVO.getPeriodoReferencia()))
			{
				setPeriodoReferencia(creditoVO.getPeriodoReferencia());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o período de refrência " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(creditoVO.getCodigoSistema()))
			{
				setCodigoSistema(creditoVO.getCodigoSistema());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do sistema " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(creditoVO.getCodigoTributo()))
			{
				setCodigoTributo(creditoVO.getCodigoTributo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do tributo " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(creditoVO.getValorTSE()))
			{
				setValorTSE(creditoVO.getValorTSE());
			}
		}
	}

	public CreditoIntegracaoVo(Collection listaDeCredito) throws ParametroObrigatorioException
	{
		super();
		if (Validador.isCollectionValida(listaDeCredito))
		{
			Iterator it = listaDeCredito.iterator();
			while (it.hasNext())
			{
				Object objeto = it.next();
				if (objeto instanceof CreditoVO)
				{
					if (Validador.isCollectionValida(getCollVO()))
					{
						getCollVO().add(new CreditoIntegracaoVo((CreditoVO) objeto));
					}
					else
					{
						Collection lista = new ArrayList();
						lista.add(new CreditoIntegracaoVo((CreditoVO) objeto));
						setCollVO(lista);
					}
				}
			}
		}
	}
	{
		setNomeSistema(NOME_SISTEMA);
	}

	public void setConsultaCompleta(boolean consultaCompleta)
	{
		this.consultaCompleta = consultaCompleta;
	}

	public boolean isConsultaCompleta()
	{
		return consultaCompleta;
	}

	public void setCollVO(Collection collVO)
	{
		this.collVO = collVO;
	}

	public Collection getCollVO()
	{
		if (collVO != null)
		{
			return collVO;
		}
		else
		{
			setCollVO(new ArrayList());
			return getCollVO();
		}
	}

	public void setParametroConsulta(EntidadeFacade parametroConsulta)
	{
		this.parametroConsulta = parametroConsulta;
	}

	public CreditoIntegracaoVo getParametroConsulta()
	{
		return (CreditoIntegracaoVo) parametroConsulta;
	}

	public void setOrigem(int origem)
	{
		this.origem = origem;
	}

	public int getOrigem()
	{
		return origem;
	}

	public void setUsuarioLogado(long usuarioLogado)
	{
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * @return
	 * @implemented by Renata Freitas
	 */
	public long getUsuarioLogado()
	{
		return usuarioLogado;
	}

	/**
	 * @param mensagem
	 * @implemented by Renata Freitas
	 */
	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	/**
	 * @return
	 * @implemented by Renata Freitas
	 */
	public String getMensagem()
	{
		if (Validador.isStringValida(mensagem))
		{
			return mensagem;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * @param nomeSistema
	 * @throws ProibidoMudarNomeSistemaException
	 * @implemented by Renata Freitas
	 */
	public void setNomeSistema(String nomeSistema) throws ProibidoMudarNomeSistemaException
	{
		if (Validador.isStringValida(nomeSistema) && nomeSistema.equals(NOME_SISTEMA))
		{
			this.nomeSistema = nomeSistema;
		}
		else
		{
			throw new ProibidoMudarNomeSistemaException();
		}
	}

	/**
	 * @return
	 * @implemented by Renata Freitas
	 */
	public String getNomeSistema()
	{
		if (Validador.isStringValida(nomeSistema))
		{
			return nomeSistema;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * @param titulo
	 * @implemented by Renata Freitas
	 */
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	/**
	 * @return
	 * @implemented by Renata Freitas
	 */
	public String getTitulo()
	{
		if (Validador.isStringValida(titulo))
		{
			return titulo;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * @param semEsteVo
	 * @implemented by Renata Freitas
	 */
	public void setSemEsteVo(boolean semEsteVo)
	{
		this.semEsteVo = semEsteVo;
	}

	/**
	 * @return
	 * @implemented byRenata Freitas
	 */
	public boolean isSemEsteVo()
	{
		return semEsteVo;
	}

	/**
	 * @return boolean
	 * @implemented byRenata Freitas
	 */
	public boolean isExiste()
	{
		return !this.equals(new CreditoIntegracaoVo());
	}

	/**
	 * @return boolean
	 * @implemented byRenata Freitas
	 */
	public boolean isExisteCollVO()
	{
		return Validador.isCollectionValida(getCollVO());
	}

	/**
	 * @return boolean
	 * @implemented byRenata Freitas
	 */
	public boolean isConsultaParametrizada()
	{
		if (getParametroConsulta() != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * @param enderecoIntegracaoVo1
	 * @param enderecoIntegracaoVo2
	 * @return int
	 * @implemented byRenata Freitas
	 */
	public int compare(Object enderecoIntegracaoVo1, Object enderecoIntegracaoVo2)
	{
		return new Long(((CreditoIntegracaoVo) enderecoIntegracaoVo1).getCodigoIdentificacaoNumeroDocumentoPagamento()).compareTo(new Long(((CreditoIntegracaoVo) enderecoIntegracaoVo2).getCodigoIdentificacaoNumeroDocumentoPagamento()));
	}

	/**
	 * @param enderecoIntegracaoVo
	 * @return int
	 * @implemented byRenata Freitas
	 */
	public int compareTo(Object enderecoIntegracaoVo)
	{
		return new Long(this.getCodigoIdentificacaoNumeroDocumentoPagamento()).compareTo(new Long(((CreditoIntegracaoVo) enderecoIntegracaoVo).getCodigoIdentificacaoNumeroDocumentoPagamento()));
	}

	/**
	 * @param outroEntidadeVO
	 * @return boolean
	 * @implemented byRenata Freitas
	 */
	public boolean equals(Object outroEntidadeVO)
	{
		if (outroEntidadeVO instanceof EntidadeFacade)
		{
			if (this.hashCode() == outroEntidadeVO.hashCode())
			{
				return true;
			}
			return false;
		}
		else
		{
			return false;
		}
	}

	/** 
	 * @return int
	 * @implemented byRenata Freitas
	 */
	public int hashCode()
	{
		int hashAtual = 0;
		int hashEndereco = new Long(getCodigoIdentificacaoNumeroDocumentoPagamento()).hashCode();
		int hashCollVO = getCollVO().size() * MULTIPLICADOR_HASH_CODE;
		hashAtual += hashCollVO + hashEndereco;
		return hashAtual;
	}

	/**
	 * @throws CloneNotSupportedException
	 * @return Object
	 * @implemented byRenata Freitas
	 */
	public Object clone() throws CloneNotSupportedException
	{
		Object clonado = null;
		clonado = super.clone();
		if (Validador.isCollectionValida(getCollVO()))
		{
			((EntidadeFacade) clonado).setCollVO(getCollVO());
		}
		return clonado;
	}
}
