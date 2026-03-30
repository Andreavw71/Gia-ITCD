package br.gov.mt.sefaz.itc.util.integracao.ccfiscal;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.ccfiscal.integracao.InstrumentoConstitutivoVO;

import java.util.ArrayList;
import java.util.Collection;


/**
 * @author Renata Freitas
 * @version $Revision: 1.2 $
 */
public class InstrumentoConstitutivoIntegracaoVo extends InstrumentoConstitutivoVO implements EntidadeFacade, VoIntegrador
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

	public InstrumentoConstitutivoIntegracaoVo()
	{
		super();
	}

	public InstrumentoConstitutivoIntegracaoVo(InstrumentoConstitutivoIntegracaoVo instrumentoConstitutivoIntegracaoVo)
	{
		this();
		setParametroConsulta(instrumentoConstitutivoIntegracaoVo);
	}

	public InstrumentoConstitutivoIntegracaoVo(long codigoInstrumento)
	{
		this();
		setCodigoInstrumentoConstitutivo(codigoInstrumento);
	}

	public InstrumentoConstitutivoIntegracaoVo(InstrumentoConstitutivoVO instrumentoConstitutivoVo) throws ParametroObrigatorioException
	{
		super();
		if (instrumentoConstitutivoVo != null)
		{
			if (Validador.isNumericoValido(instrumentoConstitutivoVo.getCodigoInstrumentoConstitutivo()))
			{
				setCodigoInstrumentoConstitutivo(instrumentoConstitutivoVo.getCodigoInstrumentoConstitutivo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do Instrumento Constitutivo " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(instrumentoConstitutivoVo.getNomeInstrumentoConstitutivo()))
			{
				setNomeInstrumentoConstitutivo(instrumentoConstitutivoVo.getNomeInstrumentoConstitutivo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o nome do Instrumento Constitutivo " + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(instrumentoConstitutivoVo.getSiglaInstrumentoConstitutivo()))
			{
				setSiglaInstrumentoConstitutivo(instrumentoConstitutivoVo.getSiglaInstrumentoConstitutivo());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a sigla nome do Instrumento Constitutivo " + 
								  QUE_E_OBRIGATORIO);
			}
		}
	}

	public InstrumentoConstitutivoIntegracaoVo(Collection<InstrumentoConstitutivoVO> listaDeCategoria) throws ParametroObrigatorioException
	{
		super();
		if (Validador.isCollectionValida(listaDeCategoria))
		{
			for (InstrumentoConstitutivoVO instrumentoConstitutivoAtualVo: listaDeCategoria)
			{
				if (Validador.isCollectionValida(getCollVO()))
				{
					getCollVO().add(new InstrumentoConstitutivoIntegracaoVo(instrumentoConstitutivoAtualVo));
				}
				else
				{
					Collection<InstrumentoConstitutivoVO> lista = new ArrayList<InstrumentoConstitutivoVO>();
					lista.add(new InstrumentoConstitutivoIntegracaoVo(instrumentoConstitutivoAtualVo));
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
		return !this.equals(new InstrumentoConstitutivoIntegracaoVo());
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

	public InstrumentoConstitutivoIntegracaoVo getParametroConsulta()
	{
		return (InstrumentoConstitutivoIntegracaoVo) parametroConsulta;
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
		return Long.valueOf(((InstrumentoConstitutivoIntegracaoVo) instrumentoConstitutivo).getCodigoInstrumentoConstitutivo()).compareTo(Long.valueOf(((InstrumentoConstitutivoIntegracaoVo) instrumentoConstitutivo2).getCodigoInstrumentoConstitutivo()));
	}

	public int compareTo(Object instrumentoConstitutivoVo)
	{
		return Long.valueOf(super.getCodigoInstrumentoConstitutivo()).compareTo(Long.valueOf(((InstrumentoConstitutivoIntegracaoVo) instrumentoConstitutivoVo).getCodigoInstrumentoConstitutivo()));
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
