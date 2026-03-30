package br.gov.mt.sefaz.itc.util.integracao.ccfiscal;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.ccfiscal.integracao.DebitoEstornoVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class DebitoEstornoIntegracaoVo extends DebitoEstornoVO implements EntidadeFacade, VoIntegrador
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

	public DebitoEstornoIntegracaoVo()
	{
		super();
	}

	public DebitoEstornoIntegracaoVo(DebitoEstornoIntegracaoVo creditoIntegracaoVo)
	{
		this();
		setParametroConsulta(creditoIntegracaoVo);
	}

	public DebitoEstornoIntegracaoVo(DebitoEstornoVO debitoEstornoVO) throws ParametroObrigatorioException
	{
		super();
		if (debitoEstornoVO != null)
		{
			if (Validador.isNumericoValido(debitoEstornoVO.getCodigoDebito()))
			{
				setCodigoDebito(debitoEstornoVO.getCodigoDebito());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do débito " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getNumeroIdentificacaoPessoa()))
			{
				setNumeroIdentificacaoPessoa(debitoEstornoVO.getNumeroIdentificacaoPessoa());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "O número pessoa  " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(debitoEstornoVO.getNomeInstrumentoConstitutivo()))
			{
				setNomeInstrumentoConstitutivo(debitoEstornoVO.getNomeInstrumentoConstitutivo());
			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a nome do instrumento consitutivo" + 
//								  QUE_E_OBRIGATORIO);
//			}
			if (Validador.isStringValida(debitoEstornoVO.getSiglaInstrumentoConstitutivo()))
			{
				setSiglaInstrumentoConstitutivo(debitoEstornoVO.getSiglaInstrumentoConstitutivo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a sigla  do instrumento consitutivo" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(debitoEstornoVO.getNumeroDocumentoCreditoTributario()))
			{
				setNumeroDocumentoCreditoTributario(debitoEstornoVO.getNumeroDocumentoCreditoTributario());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + 
								  "o número do documento de crédito tributário " + QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getCodigoTributo()))
			{
				setCodigoTributo(debitoEstornoVO.getCodigoTributo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do tributo" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getCodigoTipoTributo()))
			{
				setCodigoTipoTributo(debitoEstornoVO.getCodigoTipoTributo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do tipo tributo" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(debitoEstornoVO.getNomeTipoTributo()))
			{
				setNomeTipoTributo(debitoEstornoVO.getNomeTipoTributo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o nome do tipo do tributo" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isDataValida(debitoEstornoVO.getPeriodoReferencia()))
			{
				setPeriodoReferencia(debitoEstornoVO.getPeriodoReferencia());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o período de referência" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getCodigoGrupoInfracao()))
			{
				setCodigoGrupoInfracao(debitoEstornoVO.getCodigoGrupoInfracao());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do Grupo Infração" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getCodigoSubgrupoInfracao()))
			{
				setCodigoSubgrupoInfracao(debitoEstornoVO.getCodigoSubgrupoInfracao());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do Subgrupo Infração" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getCodigoInfracao()))
			{
				setCodigoInfracao(debitoEstornoVO.getCodigoInfracao());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código da Infração" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isDataValida(debitoEstornoVO.getDataVencimento()))
			{
				setDataVencimento(debitoEstornoVO.getDataVencimento());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a data  de vencimento" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getCodigoUnidade()))
			{
				setCodigoUnidade(debitoEstornoVO.getCodigoUnidade());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código da Unidade Sefaz" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getCodgSistemOrigem()))
			{
				setCodgSistemOrigem(debitoEstornoVO.getCodgSistemOrigem());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do Sistema " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getCodigoUsuario()))
			{
				setCodigoUsuario(debitoEstornoVO.getCodigoUsuario());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código da usuário" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isObjetoValido(debitoEstornoVO.getSituacaoDebito()))
			{
				setSituacaoDebito(debitoEstornoVO.getSituacaoDebito());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " a situação do débito " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(debitoEstornoVO.getSiglaUnidade()))
			{
				setSiglaUnidade(debitoEstornoVO.getSiglaUnidade());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " a sigla da Unidade " + 
								  QUE_E_OBRIGATORIO);
			}
//			if (Validador.isNumericoValido(debitoEstornoVO.getValorLancamento()))
//			{
				setValorLancamento(debitoEstornoVO.getValorLancamento());
//			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o valor do lançamento " + 
//								  QUE_E_OBRIGATORIO);
//			}
			if (Validador.isObjetoValido(debitoEstornoVO.getTipoProcesso()))
			{
				setTipoProcesso(debitoEstornoVO.getTipoProcesso());
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getValorPenalidade()))
			{
				setValorPenalidade(debitoEstornoVO.getValorPenalidade());
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getValorTributo()))
			{
				setValorTributo(debitoEstornoVO.getValorTributo());
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getSaldoTributo()))
			{
				setSaldoTributo(debitoEstornoVO.getSaldoTributo());
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getSaldoPenalidade()))
			{
				setSaldoPenalidade(debitoEstornoVO.getSaldoPenalidade());
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getSaldoTotalDebito()))
			{
				setSaldoTotalDebito(debitoEstornoVO.getSaldoTotalDebito());
			}
			if (Validador.isObjetoValido(debitoEstornoVO.getSituacaoVencimento()))
			{
				setSituacaoVencimento(debitoEstornoVO.getSituacaoVencimento());
			}
			if (Validador.isObjetoValido(debitoEstornoVO.getTipoRevisao()))
			{
				setTipoRevisao(debitoEstornoVO.getTipoRevisao());
			}
			if (Validador.isNumericoValido(debitoEstornoVO.getCodigoOrgao()))
			{
				setCodigoOrgao(debitoEstornoVO.getCodigoOrgao());
			}
			if (Validador.isDataValida(debitoEstornoVO.getDataSolicitacaoEstorno()))
			{
				setDataSolicitacaoEstorno(debitoEstornoVO.getDataSolicitacaoEstorno());
			}
			if (Validador.isStringValida(debitoEstornoVO.getDescricaoSumariaMotivo()))
			{
				setDescricaoSumariaMotivo(debitoEstornoVO.getDescricaoSumariaMotivo());
			}
		}
	}

	public DebitoEstornoIntegracaoVo(Collection<br.gov.mt.sefaz.ccfiscal.integracao.DebitoEstornoVO> listaDeCredito) throws ParametroObrigatorioException
	{
		super();
		if (Validador.isCollectionValida(listaDeCredito))
		{
			Iterator it = listaDeCredito.iterator();
			while (it.hasNext())
			{
				Object objeto = it.next();
				if (objeto instanceof DebitoEstornoVO)
				{
					if (Validador.isCollectionValida(getCollVO()))
					{
						getCollVO().add(new DebitoEstornoIntegracaoVo((DebitoEstornoVO) objeto));
					}
					else
					{
						Collection lista = new ArrayList();
						lista.add(new DebitoEstornoIntegracaoVo((DebitoEstornoVO) objeto));
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

	public DebitoEstornoIntegracaoVo getParametroConsulta()
	{
		return (DebitoEstornoIntegracaoVo) parametroConsulta;
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
	 * @implemented by Aésio Tominaga
	 */
	public long getUsuarioLogado()
	{
		return usuarioLogado;
	}

	/**
	 * @param mensagem
	 * @implemented by Aésio Tominaga
	 */
	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	/**
	 * @return
	 * @implemented by Aésio Tominaga
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
	 * @implemented by Aésio Tominaga
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
	 * @implemented by Aésio Tominaga
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
	 * @implemented by Aésio Tominaga
	 */
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	/**
	 * @return
	 * @implemented by Aésio Tominaga
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
	 * @implemented by Aésio Tominaga
	 */
	public void setSemEsteVo(boolean semEsteVo)
	{
		this.semEsteVo = semEsteVo;
	}

	/**
	 * @return
	 * @implemented by Aésio Tominaga
	 */
	public boolean isSemEsteVo()
	{
		return semEsteVo;
	}

	/**
	 * @return boolean
	 * @implemented by Aésio Tominaga
	 */
	public boolean isExiste()
	{
		return !this.equals(new DebitoEstornoIntegracaoVo());
	}

	/**
	 * @return boolean
	 * @implemented by Aésio Tominaga
	 */
	public boolean isExisteCollVO()
	{
		return Validador.isCollectionValida(getCollVO());
	}

	/**
	 * @return boolean
	 * @implemented by Aésio Tominaga
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
	 * @implemented by Aésio Tominaga
	 */
	public int compare(Object enderecoIntegracaoVo1, Object enderecoIntegracaoVo2)
	{
		return new Long(((DebitoEstornoIntegracaoVo) enderecoIntegracaoVo1).getCodigoDebito()).compareTo(new Long(((DebitoEstornoIntegracaoVo) enderecoIntegracaoVo2).getCodigoDebito()));
	}

	/**
	 * @param enderecoIntegracaoVo
	 * @return int
	 * @implemented by Aésio Tominaga
	 */
	public int compareTo(Object enderecoIntegracaoVo)
	{
		return new Long(this.getCodigoDebito()).compareTo(new Long(((DebitoEstornoIntegracaoVo) enderecoIntegracaoVo).getCodigoDebito()));
	}

	/**
	 * @param outroEntidadeVO
	 * @return boolean
	 * @implemented by Aésio Tominaga
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
	 * @implemented by Aésio Tominaga
	 */
	public int hashCode()
	{
		int hashAtual = 0;
		int hashEndereco = new Long(getCodigoDebito()).hashCode();
		int hashCollVO = getCollVO().size() * MULTIPLICADOR_HASH_CODE;
		hashAtual += hashCollVO + hashEndereco;
		return hashAtual;
	}

	/**
	 * @throws CloneNotSupportedException
	 * @return Object
	 * @implemented by Aésio Tominaga
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
