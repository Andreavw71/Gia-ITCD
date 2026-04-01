package br.gov.mt.sefaz.itc.util.integracao.ccfiscal;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.ccfiscal.integracao.DebitoRevisaoVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class DebitoRevisaoIntegracaoVo extends DebitoRevisaoVO implements EntidadeFacade, VoIntegrador
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

	public DebitoRevisaoIntegracaoVo()
	{
		super();
	}

	public DebitoRevisaoIntegracaoVo(DebitoRevisaoIntegracaoVo creditoIntegracaoVo)
	{
		this();
		setParametroConsulta(creditoIntegracaoVo);
	}

	public DebitoRevisaoIntegracaoVo(DebitoRevisaoVO debitoRevisaoVO) throws ParametroObrigatorioException
	{
		super();
		if (debitoRevisaoVO != null)
		{
			if (Validador.isNumericoValido(debitoRevisaoVO.getCodigoDebito()))
			{
				setCodigoDebito(debitoRevisaoVO.getCodigoDebito());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do débito " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(debitoRevisaoVO.getNumeroDocumento()))
			{
				setNumeroDocumento(debitoRevisaoVO.getNumeroDocumento());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getNumeroPessoa()))
			{
				setNumeroPessoa(debitoRevisaoVO.getNumeroPessoa());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "O número pessoa  " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getCodigoInstrumentoConstitutivo()))
			{
				setCodigoInstrumentoConstitutivo(debitoRevisaoVO.getCodigoInstrumentoConstitutivo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do Instrumento Constitutivo " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(debitoRevisaoVO.getNomeInstrumentoConstitutivo()))
			{
				setNomeInstrumentoConstitutivo(debitoRevisaoVO.getNomeInstrumentoConstitutivo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a nome do instrumento consitutivo" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(debitoRevisaoVO.getSiglaInstrumentoConstitutivo()))
			{
				setSiglaInstrumentoConstitutivo(debitoRevisaoVO.getSiglaInstrumentoConstitutivo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a sigla  do instrumento consitutivo" + 
								  QUE_E_OBRIGATORIO);
			}
			
			if (Validador.isDataValida(debitoRevisaoVO.getDataInstrumentoConstitutivo()))
			{
				setDataInstrumentoConstitutivo(debitoRevisaoVO.getDataInstrumentoConstitutivo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a data  do instrumento consitutivo" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(debitoRevisaoVO.getNumeroDocumentoCreditoTributario()))
			{
				setNumeroDocumentoCreditoTributario(debitoRevisaoVO.getNumeroDocumentoCreditoTributario());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + 
								  "o número do documento de crédito tributário " + QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getCodigoTributo()))
			{
				setCodigoTributo(debitoRevisaoVO.getCodigoTributo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do tributo" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(debitoRevisaoVO.getNomeTipoTributo()))
			{
				setNomeTipoTributo(debitoRevisaoVO.getNomeTipoTributo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o nome do tipo do tributo" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isDataValida(debitoRevisaoVO.getPeriodoReferencia()))
			{
				setPeriodoReferencia(debitoRevisaoVO.getPeriodoReferencia());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o período de referência" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isObjetoValido(debitoRevisaoVO.getDecadencia()))
			{
				setDecadencia(debitoRevisaoVO.getDecadencia());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o dominio decadência" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getCodigoGrupoInfracaoLancamento()))
			{
				setCodigoGrupoInfracaoLancamento(debitoRevisaoVO.getCodigoGrupoInfracaoLancamento());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do Grupo Infração" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getCodigoSubgrupoInfracaoLancamento()))
			{
				setCodigoSubgrupoInfracaoLancamento(debitoRevisaoVO.getCodigoSubgrupoInfracaoLancamento());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do Subgrupo Infração" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getCodigoInfracao()))
			{
				setCodigoInfracao(debitoRevisaoVO.getCodigoInfracao());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código da Infração" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isDataValida(debitoRevisaoVO.getDataVencimento()))
			{
				setDataVencimento(debitoRevisaoVO.getDataVencimento());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a data  de vencimento" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isDataValida(debitoRevisaoVO.getDataValidadeCalculo()))
			{
				setDataValidadeCalculo(debitoRevisaoVO.getDataValidadeCalculo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a data  de validade do cálculo" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getCodigoUnidadeSefaz()))
			{
				setCodigoUnidadeSefaz(debitoRevisaoVO.getCodigoUnidadeSefaz());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código da Unidade Sefaz" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getCodigoSistema()))
			{
				setCodigoSistema(debitoRevisaoVO.getCodigoSistema());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do Sistema " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isObjetoValido(debitoRevisaoVO.getModalidadeLancamento()))
			{
				setModalidadeLancamento(debitoRevisaoVO.getModalidadeLancamento());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código da modalidade lançamento " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getCodigoUsuario()))
			{
				setCodigoUsuario(debitoRevisaoVO.getCodigoUsuario());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código da usuário" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isObjetoValido(debitoRevisaoVO.getSituacaoDebito()))
			{
				setSituacaoDebito(debitoRevisaoVO.getSituacaoDebito());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " a situação do débito " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(debitoRevisaoVO.getSiglaUnidade()))
			{
				setSiglaUnidade(debitoRevisaoVO.getSiglaUnidade());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " a sigla da Unidade " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isObjetoValido(debitoRevisaoVO.getFlagDevedorSolidario()))
			{
				setFlagDevedorSolidario(debitoRevisaoVO.getFlagDevedorSolidario());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o flag devedor solidário " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isObjetoValido(debitoRevisaoVO.getPrescricao()))
			{
				setPrescricao(debitoRevisaoVO.getPrescricao());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " a prescricão" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isObjetoValido(debitoRevisaoVO.getTipoLancamento()))
			{
				setTipoLancamento(debitoRevisaoVO.getTipoLancamento());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o tipo lançamento" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isObjetoValido(debitoRevisaoVO.getGrauVinculacaoDebitoContribuinte()))
			{
				setGrauVinculacaoDebitoContribuinte(debitoRevisaoVO.getGrauVinculacaoDebitoContribuinte());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o grau vinculado debito contribuine " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getCoeficienteCorrecaoMonetaria()))
			{
				setCoeficienteCorrecaoMonetaria(debitoRevisaoVO.getCoeficienteCorrecaoMonetaria());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getCoeficienteCorrecaoMonetariaPenalidade()))
			{
				setCoeficienteCorrecaoMonetariaPenalidade(debitoRevisaoVO.getCoeficienteCorrecaoMonetariaPenalidade());
			}
			if (Validador.isDataValida(debitoRevisaoVO.getDataCienciaInstrumentoConstitutivo()))
			{
				setDataCienciaInstrumentoConstitutivo(debitoRevisaoVO.getDataCienciaInstrumentoConstitutivo());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getNumeroPessoaSolidario()))
			{
				setNumeroPessoaSolidario(debitoRevisaoVO.getNumeroPessoaSolidario());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getPercentualJuros()))
			{
				setPercentualJuros(debitoRevisaoVO.getPercentualJuros());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getPercentualMultaMora()))
			{
				setPercentualMultaMora(debitoRevisaoVO.getPercentualMultaMora());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getPrazoMaximoDataCienciaDesconto()))
			{
				setPrazoMaximoDataCienciaDesconto(debitoRevisaoVO.getPrazoMaximoDataCienciaDesconto());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getTotalAtualizadoDebito()))
			{
				setTotalAtualizadoDebito(debitoRevisaoVO.getTotalAtualizadoDebito());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getTotalAtualizadoDocumentoCreditoTributario()))
			{
				setTotalAtualizadoDocumentoCreditoTributario(debitoRevisaoVO.getTotalAtualizadoDocumentoCreditoTributario());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getValorCorrecaoMonetaria()))
			{
				setValorCorrecaoMonetaria(debitoRevisaoVO.getValorCorrecaoMonetaria());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getValorCorrecaoMonetariaPenalidade()))
			{
				setValorCorrecaoMonetariaPenalidade(debitoRevisaoVO.getValorCorrecaoMonetariaPenalidade());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getValorJuros()))
			{
				setValorJuros(debitoRevisaoVO.getValorJuros());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getValorMultaMora()))
			{
				setValorMultaMora(debitoRevisaoVO.getValorMultaMora());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getValorPenalidade()))
			{
				setValorPenalidade(debitoRevisaoVO.getValorPenalidade());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getValorTributo()))
			{
				setValorTributo(debitoRevisaoVO.getValorTributo());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getSaldoTributo()))
			{
				setSaldoTributo(debitoRevisaoVO.getSaldoTributo());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getSaldoPenalidade()))
			{
				setSaldoPenalidade(debitoRevisaoVO.getSaldoPenalidade());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getSaldoTotalDebito()))
			{
				setSaldoTotalDebito(debitoRevisaoVO.getSaldoTotalDebito());
			}
			if (Validador.isObjetoValido(debitoRevisaoVO.getSituacaoVencimento()))
			{
				setSituacaoVencimento(debitoRevisaoVO.getSituacaoVencimento());
			}
			if (Validador.isStringValida(debitoRevisaoVO.getNumeroDocumentoSolidario()))
			{
				setNumeroDocumentoSolidario(debitoRevisaoVO.getNumeroDocumentoSolidario());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getNumeroPessoaSolidario()))
			{
				setNumeroPessoaSolidario(debitoRevisaoVO.getNumeroPessoaSolidario());
			}
			if (Validador.isObjetoValido(debitoRevisaoVO.getCodigoTipoRevisao()))
			{
				setCodigoTipoRevisao(debitoRevisaoVO.getCodigoTipoRevisao());
			}
			if (Validador.isObjetoValido(debitoRevisaoVO.getCodigoTipoSistemaProcessoAlterar()))
			{
				setCodigoTipoSistemaProcessoAlterar(debitoRevisaoVO.getCodigoTipoSistemaProcessoAlterar());
			}
			if (Validador.isDataValida(debitoRevisaoVO.getDataSolicitacaoAlteracao()))
			{
				setDataSolicitacaoAlteracao(debitoRevisaoVO.getDataSolicitacaoAlteracao());
			}
			if (Validador.isNumericoValido(debitoRevisaoVO.getCodigoOrgaoAlteracao()))
			{
				setCodigoOrgaoAlteracao(debitoRevisaoVO.getCodigoOrgaoAlteracao());
			}
			if (Validador.isStringValida(debitoRevisaoVO.getDescricaoMotivoAlteracao()))
			{
				setDescricaoMotivoAlteracao(debitoRevisaoVO.getDescricaoMotivoAlteracao());
			}
			if (Validador.isStringValida(debitoRevisaoVO.getNumeroProcessoAlteracao()))
			{
				setNumeroProcessoAlteracao(debitoRevisaoVO.getNumeroProcessoAlteracao());
			}
			if (Validador.isDataValida(debitoRevisaoVO.getDataNotificacaoJulgamentoPrimeiraInstancia()))
			{
				setDataNotificacaoJulgamentoPrimeiraInstancia(debitoRevisaoVO.getDataNotificacaoJulgamentoPrimeiraInstancia());
			}
			if (Validador.isDataValida(debitoRevisaoVO.getDataNotificacaoJulgamentoSegundaInstancia()))
			{
				setDataNotificacaoJulgamentoSegundaInstancia(debitoRevisaoVO.getDataNotificacaoJulgamentoSegundaInstancia());
			}
		}
	}

	public DebitoRevisaoIntegracaoVo(Collection listaDeCredito) throws ParametroObrigatorioException
	{
		super();
		if (Validador.isCollectionValida(listaDeCredito))
		{
			Iterator it = listaDeCredito.iterator();
			while (it.hasNext())
			{
				Object objeto = it.next();
				if (objeto instanceof DebitoRevisaoVO)
				{
					if (Validador.isCollectionValida(getCollVO()))
					{
						getCollVO().add(new DebitoRevisaoIntegracaoVo((DebitoRevisaoVO) objeto));
					}
					else
					{
						Collection lista = new ArrayList();
						lista.add(new DebitoRevisaoIntegracaoVo((DebitoRevisaoVO) objeto));
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

	public DebitoRevisaoIntegracaoVo getParametroConsulta()
	{
		return (DebitoRevisaoIntegracaoVo) parametroConsulta;
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
		return !this.equals(new DebitoRevisaoIntegracaoVo());
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
		return new Long(((DebitoRevisaoIntegracaoVo) enderecoIntegracaoVo1).getCodigoDebito()).compareTo(new Long(((DebitoRevisaoIntegracaoVo) enderecoIntegracaoVo2).getCodigoDebito()));
	}

	/**
	 * @param enderecoIntegracaoVo
	 * @return int
	 * @implemented by Aésio Tominaga
	 */
	public int compareTo(Object enderecoIntegracaoVo)
	{
		return new Long(this.getCodigoDebito()).compareTo(new Long(((DebitoRevisaoIntegracaoVo) enderecoIntegracaoVo).getCodigoDebito()));
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
