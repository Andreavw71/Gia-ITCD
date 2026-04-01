package br.gov.mt.sefaz.itc.util.integracao.ccfiscal;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.ccfiscal.integracao.DebitoVO;

import java.util.ArrayList;
import java.util.Collection;


public class DebitoIntegracaoVo extends DebitoVO implements EntidadeFacade, VoIntegrador
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private static final String NOME_SISTEMA = "ITCD";
	private boolean consultaCompleta;
	private Collection collVO;
	private EntidadeFacade parametroConsulta;
	private int origem;
	private long usuarioLogado;
	private String mensagem;
	private String nomeSistema;
	private String titulo;
	private boolean semEsteVo;
	private long codigoDebito;
	
	/* ================== CONSTRUTORES ================== */

	public DebitoIntegracaoVo()
	{
		super();
	}

	public DebitoIntegracaoVo(DebitoIntegracaoVo debitoIntegracaoVo)
	{
		this();
		setParametroConsulta(debitoIntegracaoVo);
	}

	public DebitoIntegracaoVo(long codigoDebito)
	{
		this();
		setCodigoDebito(codigoDebito);
	}

	public DebitoIntegracaoVo(DebitoVO debitoVo) throws ParametroObrigatorioException
	{
		super();
		if (debitoVo != null)
		{
			if (Validador.isNumericoValido(debitoVo.getCodigoDebito()))
			{
				setCodigoDebito(debitoVo.getCodigoDebito());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o código do débito " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoVo.getNumeroPessoa()))
			{
				setNumeroPessoa(debitoVo.getNumeroPessoa());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o número pessoa  " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(debitoVo.getCodigoInstrumentoConstitutivo()))
			{
				setCodigoInstrumentoConstitutivo(debitoVo.getCodigoInstrumentoConstitutivo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o código do Instrumento Constitutivo " + 
								  QUE_E_OBRIGATORIO);
			}
//			if (Validador.isDataValida(debitoVo.getDataInstrumentoConstitutivo()))
//			{
//				setDataInstrumentoConstitutivo(debitoVo.getDataInstrumentoConstitutivo());
//			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " a data  do instrumento consitutivo" + 
//								  QUE_E_OBRIGATORIO);
//			}
			if (Validador.isStringValida(debitoVo.getNumeroDocumentoCreditoTributario()))
			{
				setNumeroDocumentoCreditoTributario(debitoVo.getNumeroDocumentoCreditoTributario());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + 
								  "o número do documento de crédito tributário " + QUE_E_OBRIGATORIO);
			}
//			if (Validador.isNumericoValido(debitoVo.getCodigoTributo()))
//			{
//				setCodigoTributo(debitoVo.getCodigoTributo());
//			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o código do tributo" + 
//								  QUE_E_OBRIGATORIO);
//			}
//			if (Validador.isDataValida(debitoVo.getPeriodoReferencia()))
//			{
//				setPeriodoReferencia(debitoVo.getPeriodoReferencia());
//			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o período de referência" + 
//								  QUE_E_OBRIGATORIO);
//			}
//			if (Validador.isNumericoValido(debitoVo.getCodigoGrupoInfracao()))
//			{
//				setCodigoGrupoInfracao(debitoVo.getCodigoGrupoInfracao());
//			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do Grupo Infração" + 
//								  QUE_E_OBRIGATORIO);
//			}
//			if (Validador.isNumericoValido(debitoVo.getCodigoSubgrupoInfracao()))
//			{
//				setCodigoSubgrupoInfracao(debitoVo.getCodigoSubgrupoInfracao());
//			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do Subgrupo Infração" + 
//								  QUE_E_OBRIGATORIO);
//			}
//			if (Validador.isNumericoValido(debitoVo.getCodigoInfracao()))
//			{
//				setCodigoInfracao(debitoVo.getCodigoInfracao());
//			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código da Infração" + 
//								  QUE_E_OBRIGATORIO);
//			}
//			if (Validador.isDataValida(debitoVo.getDataVencimento()))
//			{
//				setDataVencimento(debitoVo.getDataVencimento());
//			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a data  de vencimento" + 
//								  QUE_E_OBRIGATORIO);
//			}
//			if (Validador.isDataValida(debitoVo.getDataValidadeCalculo()))
//			{
//				setDataValidadeCalculo(debitoVo.getDataValidadeCalculo());
//			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a data  de validade do cálculo" + 
//								  QUE_E_OBRIGATORIO);
//			}
//			if (Validador.isNumericoValido(debitoVo.getCodigoUnidadeSefaz()))
//			{
//				setCodigoUnidadeSefaz(debitoVo.getCodigoUnidadeSefaz());
//			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código da Unidade Sefaz" + 
//								  QUE_E_OBRIGATORIO);
//			}
//			if (Validador.isNumericoValido(debitoVo.getCodigoSistema()))
//			{
//				setCodigoSistema(debitoVo.getCodigoSistema());
//			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do Sistema " + 
//								  QUE_E_OBRIGATORIO);
//			}
//			if (Validador.isObjetoValido(debitoVo.getCodigoModalidadeLancamento()))
//			{
//				setCodigoModalidadeLancamento(debitoVo.getCodigoModalidadeLancamento());
//			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código da modalidade lançamento " + 
//								  QUE_E_OBRIGATORIO);
//			}
//			if (Validador.isNumericoValido(debitoVo.getCodigoUsuario()))
//			{
//				setCodigoUsuario(debitoVo.getCodigoUsuario());
//			}
//			else
//			{
//				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código da usuário" + 
//								  QUE_E_OBRIGATORIO);
//			}
//			if (Validador.isNumericoValido(debitoVo.getCoeficienteCorrecaoMonetaria()))
//			{
//				setCoeficienteCorrecaoMonetaria(debitoVo.getCoeficienteCorrecaoMonetaria());
//			}
//			if (Validador.isNumericoValido(debitoVo.getCoeficienteCorrecaoMonetariaPenalidade()))
//			{
//				setCoeficienteCorrecaoMonetariaPenalidade(debitoVo.getCoeficienteCorrecaoMonetariaPenalidade());
//			}
//			if (Validador.isDataValida(debitoVo.getDataCienciaInstrumentoConstitutivo()))
//			{
//				setDataCienciaInstrumentoConstitutivo(debitoVo.getDataCienciaInstrumentoConstitutivo());
//			}
//			if (Validador.isNumericoValido(debitoVo.getNumeroPessoaSolidario()))
//			{
//				setNumeroPessoaSolidario(debitoVo.getNumeroPessoaSolidario());
//			}
//			if (Validador.isNumericoValido(debitoVo.getPercentualJuros()))
//			{
//				setPercentualJuros(debitoVo.getPercentualJuros());
//			}
//			if (Validador.isNumericoValido(debitoVo.getPercentualMultaMora()))
//			{
//				setPercentualMultaMora(debitoVo.getPercentualMultaMora());
//			}
//			if (Validador.isNumericoValido(debitoVo.getPrazoMaximoDataCienciaDesconto()))
//			{
//				setPrazoMaximoDataCienciaDesconto(debitoVo.getPrazoMaximoDataCienciaDesconto());
//			}
//			if (Validador.isNumericoValido(debitoVo.getTotalAtualizadoDebito()))
//			{
//				setTotalAtualizadoDebito(debitoVo.getTotalAtualizadoDebito());
//			}
//			if (Validador.isNumericoValido(debitoVo.getTotalAtualizadoDocumentoCreditoTributaria()))
//			{
//				setTotalAtualizadoDocumentoCreditoTributaria(debitoVo.getTotalAtualizadoDocumentoCreditoTributaria());
//			}
//			if (Validador.isNumericoValido(debitoVo.getValorCorrecaoMonetaria()))
//			{
//				setValorCorrecaoMonetaria(debitoVo.getValorCorrecaoMonetaria());
//			}
//			if (Validador.isNumericoValido(debitoVo.getValorCorrecaoMonetariaPenalidade()))
//			{
//				setValorCorrecaoMonetariaPenalidade(debitoVo.getValorCorrecaoMonetariaPenalidade());
//			}
//			if (Validador.isNumericoValido(debitoVo.getValorJuros()))
//			{
//				setValorJuros(debitoVo.getValorJuros());
//			}
//			if (Validador.isNumericoValido(debitoVo.getValorMultaMora()))
//			{
//				setValorMultaMora(debitoVo.getValorMultaMora());
//			}
//			if (Validador.isNumericoValido(debitoVo.getValorPenalidade()))
//			{
//				setValorPenalidade(debitoVo.getValorPenalidade());
//			}
//			if (Validador.isNumericoValido(debitoVo.getValorTributo()))
//			{
//				setValorTributo(debitoVo.getValorTributo());
//			}      
        if (Validador.isDominioNumericoValido(debitoVo.getFlagSituacaoDebito()))
        {
           setFlagSituacaoDebito(debitoVo.getFlagSituacaoDebito());
        } 
		}
	}

	public DebitoIntegracaoVo(Collection<DebitoVO> listaDeDebito) throws ParametroObrigatorioException
	{
		super();
		if (Validador.isCollectionValida(listaDeDebito))
		{
			for (DebitoVO debitoAtualVo: listaDeDebito)
			{
				if (Validador.isCollectionValida(getCollVO()))
				{
					getCollVO().add(new DebitoIntegracaoVo(debitoAtualVo));
				}
				else
				{
					Collection<DebitoVO> lista = new ArrayList<DebitoVO>();
					lista.add(new DebitoIntegracaoVo(debitoAtualVo));
					setCollVO(lista);
				}
			}
		}
	}
	{
		setNomeSistema(NOME_SISTEMA);
	}
   
	/* ===================== MÉTODOS PADRÃO ===================== */

	public String toString()
	{
		return String.valueOf(getCodigoInstrumentoConstitutivo());
	}

	public boolean isExiste()
	{
		return !this.equals(new DebitoIntegracaoVo());
	}

	public boolean isExisteCollVO()
	{
		return Validador.isCollectionValida(getCollVO());
	}

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

	public boolean isConsultaCompleta()
	{
		return consultaCompleta;
	}

	public void setConsultaCompleta(boolean consultaCompleta)
	{
		this.consultaCompleta = consultaCompleta;
	}

	public DebitoIntegracaoVo getParametroConsulta()
	{
		return (DebitoIntegracaoVo) parametroConsulta;
	}

	public void setParametroConsulta(EntidadeFacade parametroConsulta)
	{
		this.parametroConsulta = parametroConsulta;
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

	public long getUsuarioLogado()
	{
		return usuarioLogado;
	}

	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

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

	public final void setNomeSistema(String nomeSistema) throws ProibidoMudarNomeSistemaException
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

	public final String getNomeSistema()
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

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

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

	public int hashCode()
	{
		int hashAtual = 0;
		int hashCollVO = getCollVO().size() * MULTIPLICADOR_HASH_CODE;
		hashAtual += hashCollVO;
		hashAtual += getCodigoInstrumentoConstitutivo();
		hashAtual *= MULTIPLICADOR_HASH_CODE;
		return hashAtual;
	}

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

	public int compare(Object instrumentoConstitutivo, Object instrumentoConstitutivo2)
	{
		return Long.valueOf(((DebitoIntegracaoVo) instrumentoConstitutivo).getCodigoInstrumentoConstitutivo()).compareTo(Long.valueOf(((DebitoIntegracaoVo) instrumentoConstitutivo2).getCodigoInstrumentoConstitutivo()));
	}

	public int compareTo(Object instrumentoConstitutivoVo)
	{
		return Long.valueOf(super.getCodigoInstrumentoConstitutivo()).compareTo(Long.valueOf(((DebitoIntegracaoVo) instrumentoConstitutivoVo).getCodigoInstrumentoConstitutivo()));
	}

	public void setSemEsteVo(boolean semEsteVo)
	{
		this.semEsteVo = semEsteVo;
	}

	public boolean isSemEsteVo()
	{
		return semEsteVo;
	}

	public void setCodigoDebito(long codigoDebito)
	{
		this.codigoDebito = codigoDebito;
	}

	public long getCodigoDebito()
	{
		return codigoDebito;
	}

}
