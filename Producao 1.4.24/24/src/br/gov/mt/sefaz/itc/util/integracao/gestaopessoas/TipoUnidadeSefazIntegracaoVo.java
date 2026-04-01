package br.gov.mt.sefaz.itc.util.integracao.gestaopessoas;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.itc.util.dominio.DomnSituacaoRegistro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import sefaz.mt.gestaopessoas.integracao.TipoUnidadeSefazVO;


/**
 * Classe utilizada como vo de integração.
 * Representa o adapter do tipo de unidade sefaz para o sistema CBA.
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.1.1.1 $
 */
public class TipoUnidadeSefazIntegracaoVo implements EntidadeFacade, VoIntegrador
{
	private static final String NOME_SISTEMA = "ITC";
	private boolean consultaCompleta;
	private Collection collVO;
	private EntidadeFacade parametroConsulta;
	private int origem;
	private long usuarioLogado;
	private String mensagem;
	private String nomeSistema;
	private String titulo;
	private static final long serialVersionUID = Long.MAX_VALUE;
	//Atributos TipoUnidadeSefazVO
	private Integer codgTipoUnidade;
	private String siglaTipoUnidade;
	private String descTipoUnidade;
	private DomnSituacaoRegistro codgSituacaoTipo;

	public TipoUnidadeSefazIntegracaoVo()
	{
		super();
	}

	public TipoUnidadeSefazIntegracaoVo(TipoUnidadeSefazVO tipoUnidadeSefazVo) throws ParametroObrigatorioException
	{
		super();
		if (tipoUnidadeSefazVo != null)
		{
			if (Validador.isNumericoValido(tipoUnidadeSefazVo.getCodgTipoUnidade()))
			{
				setCodgTipoUnidade(tipoUnidadeSefazVo.getCodgTipoUnidade());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código do tipo da unidade sefaz" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(tipoUnidadeSefazVo.getDescTipoUnidade()))
			{
				setDescTipoUnidade(tipoUnidadeSefazVo.getDescTipoUnidade());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a descrição do tipo da unidade sefaz" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(tipoUnidadeSefazVo.getSiglaTipoUnidade()))
			{
				setSiglaTipoUnidade(tipoUnidadeSefazVo.getSiglaTipoUnidade());
			}
			if (Validador.isDominioNumericoValido(tipoUnidadeSefazVo.getCodgSituacaoTipo()))
			{
				sefaz.mt.gestaopessoas.dominio.DomnSituacaoRegistro domn = tipoUnidadeSefazVo.getCodgSituacaoTipo();
				DomnSituacaoRegistro domnITCD = new DomnSituacaoRegistro(domn.getValorCorrente());
				setCodgSituacaoTipo(domnITCD);
			}
		}
	}

	public TipoUnidadeSefazIntegracaoVo(Integer codigoTipoUnidadeSefaz)
	{
		super();
		setCodgTipoUnidade(codigoTipoUnidadeSefaz);
	}

	public TipoUnidadeSefazIntegracaoVo(Collection listaDeTipoUnidadeSefaz) throws ParametroObrigatorioException
	{
		super();
		if (Validador.isCollectionValida(listaDeTipoUnidadeSefaz))
		{
			Iterator it = listaDeTipoUnidadeSefaz.iterator();
			while (it.hasNext())
			{
				Object objeto = it.next();
				if (objeto instanceof TipoUnidadeSefazVO)
				{
					if (Validador.isCollectionValida(getCollVO()))
					{
						getCollVO().add(new TipoUnidadeSefazIntegracaoVo((TipoUnidadeSefazVO) objeto));
					}
					else
					{
						Collection lista = getCollVO();
						lista.add(new TipoUnidadeSefazIntegracaoVo((TipoUnidadeSefazVO) objeto));
						setCollVO(lista);
					}
				}
			}
		}
	}
	{
		setNomeSistema(NOME_SISTEMA);
	}

	public String toString()
	{
		return String.valueOf(getCodgTipoUnidade());
	}

	public boolean isExiste()
	{
		return !this.equals(new TipoUnidadeSefazIntegracaoVo());
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

	public TipoUnidadeSefazIntegracaoVo getParametroConsulta()
	{
		return (TipoUnidadeSefazIntegracaoVo) parametroConsulta;
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

	public Integer getCodgTipoUnidade()
	{
		if (!Validador.isNumericoValido(codgTipoUnidade))
		{
		   setCodgTipoUnidade(new Integer(0));			
		}
	   return codgTipoUnidade;
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
		int hashCodgTipoUnidade = getCodgTipoUnidade().hashCode();
		int hashMensagem = getMensagem().hashCode();
		int hashTitulo = getTitulo().hashCode();
		int hashCollVO = getCollVO().size() * MULTIPLICADOR_HASH_CODE;
		hashAtual += hashMensagem + hashTitulo + hashCollVO + hashCodgTipoUnidade;
		hashAtual += (int) getUsuarioLogado() + getOrigem();
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
	 * Método utilizado para comparar um objeto do tipo TipoUnidadeSefazVO com a instância desta classe.
	 * Esta comparação será feita pelo código.
	 * @param tipoUnidadeSefazVo
	 * @throws ClassCastException Se o parâmetro não for do tipo sefaz.mt.gestaopessoas.integracao.TipoUnidadeSefazVO.
	 * @throws NullPointerException Se o parâmetro informado for null.
	 * @return int - Retorna <b>-1</b> se o código desta instância for menor que o código do objeto recebido como parâmetro.
	 * 	Retorna <b>0</b> se o código desta instância for igual ao código do objeto recebido como parâmetro.
	 *    Retorna <b>1</b> se o código desta instância for maior que o código do objeto recebido como parâmetro.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int compareTo(Object tipoUnidadeSefazVo) throws ClassCastException, NullPointerException
	{
		return this.getCodgTipoUnidade().compareTo(((TipoUnidadeSefazVO) tipoUnidadeSefazVo).getCodgTipoUnidade());
	}

	/**
	 * Método utilizado para comparar dois objetos do tipo sefaz.mt.gestaopessoas.integracao.TipoUnidadeSefazVO.
	 * Esta comparação será feita pelo código.
	 * @param tipoUnidadeSefazVo1
	 * @param tipoUnidadeSefazVo2
	 * @throws ClassCastException Se pelo menos um dos parâmetros não for do tipo sefaz.mt.gestaopessoas.integracao.TipoUnidadeSefazVO.
	 * @throws NullPointerException Se pelo menos um dos parâmetros informados for null.
	 * @return int - Retorna <b>-1</b> se o código do objeto tipoUnidadeSefazVo1 for menor que o código do objeto tipoUnidadeSefazVo2.
	 * 	Retorna <b>0</b> se o código do objeto tipoUnidadeSefazVo1 for igual ao código do objeto tipoUnidadeSefazVo2.
	 *    Retorna <b>1</b> se o código do objeto tipoUnidadeSefazVo1 for maior que o código do objeto tipoUnidadeSefazVo2.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int compare(Object tipoUnidadeSefazVo1, Object tipoUnidadeSefazVo2) throws ClassCastException, 
			  NullPointerException
	{
		return ((TipoUnidadeSefazVO) tipoUnidadeSefazVo1).getCodgTipoUnidade().compareTo(((TipoUnidadeSefazVO) tipoUnidadeSefazVo2).getCodgTipoUnidade());
	}
	//Métodos TipoUnidadeSefazVO

	public void setCodgTipoUnidade(Integer codgTipoUnidade)
	{
		this.codgTipoUnidade = codgTipoUnidade;
	}

	public void setSiglaTipoUnidade(String siglaTipoUnidade)
	{
		this.siglaTipoUnidade = siglaTipoUnidade;
	}

	public String getSiglaTipoUnidade()
	{
		return siglaTipoUnidade;
	}

	public void setDescTipoUnidade(String descTipoUnidade)
	{
		this.descTipoUnidade = descTipoUnidade;
	}

	public String getDescTipoUnidade()
	{
		return descTipoUnidade;
	}

	public void setCodgSituacaoTipo(DomnSituacaoRegistro codgSituacaoTipo)
	{
		this.codgSituacaoTipo = codgSituacaoTipo;
	}

	public DomnSituacaoRegistro getCodgSituacaoTipo()
	{
		return codgSituacaoTipo;
	}
}
