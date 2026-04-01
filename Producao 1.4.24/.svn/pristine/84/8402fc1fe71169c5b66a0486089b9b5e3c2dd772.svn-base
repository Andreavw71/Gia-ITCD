package br.gov.mt.sefaz.itc.util.integracao.ccfiscal;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.ccfiscal.integracao.CodigoTributoVO;

import java.util.ArrayList;
import java.util.Collection;


public class CodigoTributoIntegracaoVo extends CodigoTributoVO  implements EntidadeFacade, VoIntegrador
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
	/* ================== CONSTRUTORES ================== */

	public CodigoTributoIntegracaoVo()
	{
		super();
	}

	public CodigoTributoIntegracaoVo(CodigoTributoIntegracaoVo filtroConsultaDebitoIntegracaoVo)
	{
		this();
		setParametroConsulta(filtroConsultaDebitoIntegracaoVo);
		;
	}

	public CodigoTributoIntegracaoVo(CodigoTributoVO codigoTributoVO) throws ParametroObrigatorioException
	{
		super();
		if (codigoTributoVO != null)
		{
			if (Validador.isNumericoValido(codigoTributoVO.getCodigoTributo()))
			{
				setCodigoTributo(codigoTributoVO.getCodigoTributo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o código do código do tributo" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(codigoTributoVO.getNomeTributo()))
			{
				setNomeTributo(codigoTributoVO.getNomeTributo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o nome do tributo" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(codigoTributoVO.getNomeTipoTributo()))
			{
				setNomeTipoTributo(codigoTributoVO.getNomeTipoTributo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o nome do tipo do tributo" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isObjetoValido(codigoTributoVO.getClassificacaoTipoTributo()))
			{
				setClassificacaoTipoTributo(codigoTributoVO.getClassificacaoTipoTributo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o Classificação do tipo do tributo" + 
								  QUE_E_OBRIGATORIO);
			}
		}
	}

	public CodigoTributoIntegracaoVo(Collection<CodigoTributoVO> listaFiltroConsultaDebito) throws ParametroObrigatorioException
	{
		super();
		if (Validador.isCollectionValida(listaFiltroConsultaDebito))
		{
			for (CodigoTributoVO filtroConsultaDebitoVo: listaFiltroConsultaDebito)
			{
				if (Validador.isCollectionValida(getCollVO()))
				{
					getCollVO().add(new CodigoTributoIntegracaoVo(filtroConsultaDebitoVo));
				}
				else
				{
					Collection<CodigoTributoVO> lista = new ArrayList<CodigoTributoVO>();
					lista.add(new CodigoTributoIntegracaoVo(filtroConsultaDebitoVo));
					setCollVO(lista);
				}
			}
		}
	}
	{
		setNomeSistema(NOME_SISTEMA);
	}
	/* ===================== MÉTODOS PADRÃO ===================== */

	public boolean isExiste()
	{
		return !this.equals(new CodigoTributoIntegracaoVo());
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

	public CodigoTributoIntegracaoVo getParametroConsulta()
	{
		return (CodigoTributoIntegracaoVo) parametroConsulta;
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
		hashAtual += getCodigoTributo();
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

	public int compare(Object categoriaVo1, Object categoriaVo2)
	{
		return Long.valueOf(((CodigoTributoIntegracaoVo) categoriaVo1).getCodigoTributo()).compareTo(Long.valueOf(((CodigoTributoIntegracaoVo) categoriaVo2).getCodigoTributo()));
	}

	public int compareTo(Object categoriaVo)
	{
		return Long.valueOf(super.getCodigoTributo()).compareTo(Long.valueOf(((CodigoTributoIntegracaoVo) categoriaVo).getCodigoTributo()));
	}

	public void setSemEsteVo(boolean semEsteVo)
	{
		this.semEsteVo = semEsteVo;
	}

	public boolean isSemEsteVo()
	{
		return semEsteVo;
	}
}
