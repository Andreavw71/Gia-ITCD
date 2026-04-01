package br.gov.mt.sefaz.itc.util.integracao.arrecadacao;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.arrecadacao.model.darquitado.DarQuitadoVo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * @author Roniselton Barreto Rodrigues Silva
 * @version $Revision: 1.1 $
 */
public class DarQuitadoIntegracaoVo extends DarQuitadoVo implements EntidadeFacade, VoIntegrador
{
	private boolean consultaCompleta;
	private Collection collVO;
	private EntidadeFacade parametroConsulta;
	private long usuarioLogado;
	private String mensagem;
	private String titulo;
	

	public DarQuitadoIntegracaoVo()
	{
		super();
	}

	public DarQuitadoIntegracaoVo(DarQuitadoVo darQuitadoVO) throws ParametroObrigatorioException
	{
		super();
		if (darQuitadoVO != null)
		{
			if (Validador.isNumericoValido(darQuitadoVO.getNumrDar()))
			{
				setNumrDar(darQuitadoVO.getNumrDar());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + " o número sequencial do DAR " + 
								  QUE_E_OBRIGATORIO);
			}		
			if (Validador.isNumericoValido(darQuitadoVO.getCodgTributo()))
			{
				setCodgTributo(darQuitadoVO.getCodgTributo());
			}
		}
	}

	public DarQuitadoIntegracaoVo(long numrDar)
	{
		super();
		setNumrDar(  numrDar );
	}



	public DarQuitadoIntegracaoVo(Collection listaDeContribuintes) throws ParametroObrigatorioException
	{
		super();
		if (Validador.isCollectionValida(listaDeContribuintes))
		{
			Iterator it = listaDeContribuintes.iterator();
			while (it.hasNext())
			{
				Object objeto = it.next();
				if (objeto instanceof DarQuitadoVo)
				{
					if (Validador.isCollectionValida(getCollVO()))
					{
						getCollVO().add(new DarQuitadoIntegracaoVo((DarQuitadoVo) objeto));
					}
					else
					{
						Collection lista = getCollVO();
						lista.add(new DarQuitadoIntegracaoVo((DarQuitadoVo) objeto));
						setCollVO(lista);
					}
				}
			}
		}
	}

	public String toString()
	{
		return String.valueOf(getNumrDar());
	}

	public boolean isExiste()
	{
		return !this.equals(new DarQuitadoIntegracaoVo());
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

	public DarQuitadoIntegracaoVo getParametroConsulta()
	{
		return (DarQuitadoIntegracaoVo) parametroConsulta;
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
		throw new ProibidoMudarNomeSistemaException();
	}

	public final String getNomeSistema()
	{
		return STRING_VAZIA;
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
		hashAtual += (int) getNumrDar();
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

	/**
	 * Método utilizado para comparar um objeto do tipo ContribuinteVO com a instância desta classe.
	 * Esta comparação será feita pelo número do contribuinte.
	 * @param darQuitadoVo
	 * @throws ClassCastException Se o parâmetro não for do tipo sefaz.mt.cadastro.integracao.ContribuinteVO.
	 * @throws NullPointerException Se o parâmetro informado for null.
	 * @return int - Retorna <b>-1</b> se o código desta instância for menor que o código do objeto recebido como parâmetro.
	 * Retorna <b>0</b> se o código desta instância for igual ao código do objeto recebido como parâmetro.
	 * Retorna <b>1</b> se o código desta instância for maior que o código do objeto recebido como parâmetro.
	 * @implemented by Karen Barbato
	 * @implemented by Wanderlúcio Alves de Oliveira
	 */
	public int compareTo(Object darQuitadoVo) throws ClassCastException, NullPointerException
	{
		return new Long(this.getNumrDar()) .compareTo(  new Long( ((DarQuitadoVo) darQuitadoVo).getNumrDar()));
	}

	/**
	 * Método utilizado para comparar dois objetos do tipo sefaz.mt.cadastro.integracao.ContribuinteVO.
	 * Esta comparação será feita pelo número do contribuinte.
	 * @param darQuitadoVo1
	 * @param darQuitadoVo2
	 * @throws ClassCastException Se pelo menos um dos parâmetros não for do tipo sefaz.mt.cadastro.integracao.ContribuinteVO.
	 * @throws NullPointerException Se pelo menos um dos parâmetros informados for null.
	 * @return int - Retorna <b>-1</b> se o código do objeto contribuinteVo1 for menor que o código do objeto contribuinteVo2.
	 * Retorna <b>0</b> se o código do objeto contribuinteVo1 for igual ao código do objeto contribuinteVo2.
	 * Retorna <b>1</b> se o código do objeto contribuinteVo1 for maior que o código do objeto contribuinteVo2.
	 * @implemented by Karen Barbato
	 * @implemented by Wanderlúcio Alves de Oliveira
	 */
	public int compare(Object darQuitadoVo1, Object darQuitadoVo2) throws ClassCastException, 
			  NullPointerException
	{
		return (    new Long(  ((DarQuitadoVo) darQuitadoVo1).getNumrDar()  ) .compareTo(  new Long( ((DarQuitadoVo) darQuitadoVo2).getNumrDar())));
	}


	public String getValorAutenticadoFormatado()
	{
		if (!Validador.isNumericoValido(super.getValorAutenticado()))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(super.getValorAutenticado());
	}

}
