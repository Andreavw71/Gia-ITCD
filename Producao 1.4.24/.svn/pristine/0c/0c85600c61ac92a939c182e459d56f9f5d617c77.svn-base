package br.gov.mt.sefaz.itc.util.integracao.gestaopessoas;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.TipoUnidadeSefazIntegracaoVo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import sefaz.mt.gestaopessoas.integracao.TipoUnidadeSefazVO;
import sefaz.mt.gestaopessoas.integracao.UnidadeSefazVO;


/**
 * Classe utilizada como vo de integração.
 * Representa o adapter do vo unidade sefaz para o sistema ITC.
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.1.1.1 $
 */
public class UnidadeSefazIntegracaoVo implements EntidadeFacade, VoIntegrador
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private static final String NOME_SISTEMA = "ITC";
	private boolean consultaCompleta;
	private Collection collVO;
	private EntidadeFacade parametroConsulta;
	private int origem;
	private long usuarioLogado;
	private String mensagem;
	private String nomeSistema;
	private String titulo;
	//Atributos UnidadeSefazVO
	private TipoUnidadeSefazIntegracaoVo tipoUnidade;
	private Integer codgUnidade;
	private String siglaUnidade;
	private String nomeUnidade;
	private String codgSituacaoUnidade;
	private Long numrMatrServResponsavel;
	private Integer codgMunicipio;
	private MunicipioIntegracaoVo municipio;

	public UnidadeSefazIntegracaoVo()
	{
		super();
	}

	public UnidadeSefazIntegracaoVo(UnidadeSefazVO unidadeSefazVo) throws ParametroObrigatorioException
	{
		super();
		if (unidadeSefazVo != null)
		{
			if (Validador.isNumericoValido(unidadeSefazVo.getCodgUnidade()))
			{
				setCodgUnidade(unidadeSefazVo.getCodgUnidade());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o código da unidade sefaz" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(unidadeSefazVo.getSiglaUnidade()))
			{
				setSiglaUnidade(unidadeSefazVo.getSiglaUnidade());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "a sigla da unidade sefaz" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isNumericoValido(unidadeSefazVo.getCodgMunicipio()))
			{
				setCodgMunicipio(unidadeSefazVo.getCodgMunicipio());
			}
			if (Validador.isStringValida(unidadeSefazVo.getNomeUnidade()))
			{
				setNomeUnidade(unidadeSefazVo.getNomeUnidade());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o nome da unidade sefaz" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(unidadeSefazVo.getCodgSituacaoUnidade()))
			{
				setCodgSituacaoUnidade(unidadeSefazVo.getCodgSituacaoUnidade());
			}
			if (unidadeSefazVo.getTipoUnidade() != null)
			{
				setTipoUnidade(new TipoUnidadeSefazIntegracaoVo(unidadeSefazVo.getTipoUnidade()));
			}
		}
	}

	public UnidadeSefazVO toUnidadeSefazVO()
	{
		UnidadeSefazVO retorno = new UnidadeSefazVO();
		if(Validador.isNumericoValido(this.codgUnidade))
		{
			retorno.setCodgUnidade(this.codgUnidade);
		}
		if(Validador.isStringValida(this.getSiglaUnidade()))
		{
			retorno.setSiglaUnidade(this.siglaUnidade);
		}
		if(Validador.isNumericoValido(this.getCodgMunicipio()))
		{
			retorno.setCodgMunicipio(this.codgMunicipio);
		}
		if(Validador.isStringValida(this.nomeUnidade))
		{
			retorno.setNomeUnidade(this.nomeUnidade);
		}
		if(Validador.isStringValida(this.codgSituacaoUnidade))
		{
			retorno.setCodgSituacaoUnidade(this.codgSituacaoUnidade);
		}
		if(Validador.isObjetoValido(this.tipoUnidade))
		{
			TipoUnidadeSefazVO tipo = new TipoUnidadeSefazVO();
			tipo.setDescTipoUnidade(this.getTipoUnidade().getDescTipoUnidade());
			retorno.setTipoUnidade(tipo);
		}
		return retorno;
	}

	public UnidadeSefazIntegracaoVo(Integer codigoUnidade)
	{
		super();
		setCodgUnidade(codigoUnidade);
	}

	public UnidadeSefazIntegracaoVo(Collection listaUnidades) throws ParametroObrigatorioException
	{
		super();
		if (Validador.isCollectionValida(listaUnidades))
		{
			Iterator it = listaUnidades.iterator();
			while (it.hasNext())
			{
				Object objeto = it.next();
				if (objeto instanceof UnidadeSefazVO)
				{
					if (Validador.isCollectionValida(getCollVO()))
					{
						getCollVO().add(new UnidadeSefazIntegracaoVo((UnidadeSefazVO) objeto));
					}
					else
					{
						Collection lista = getCollVO();
						lista.add(new UnidadeSefazIntegracaoVo((UnidadeSefazVO) objeto));
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
		return String.valueOf(getCodgUnidade());
	}

	public boolean isExiste()
	{
		return !this.equals(new UnidadeSefazIntegracaoVo());
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

	public UnidadeSefazIntegracaoVo getParametroConsulta()
	{
		return (UnidadeSefazIntegracaoVo) parametroConsulta;
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
	
	public String getDadosUnidadeFormatado()
	{
		if(Validador.isStringValida(getSiglaUnidade()) && Validador.isStringValida(getNomeUnidade()))
		{
			return getSiglaUnidade() + " - " + getNomeUnidade();
		}
		return "";
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

	public Integer getCodgUnidade()
	{
		if (Validador.isNumericoValido(codgUnidade))
		{
			return codgUnidade;
		}
		else
		{
			setCodgUnidade(Integer.valueOf("0"));
			return Integer.valueOf("0");
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
		int hashCodgUnidade = getCodgUnidade().hashCode();
		int hashMensagem = getMensagem().hashCode();
		int hashTitulo = getTitulo().hashCode();
		int hashCollVO = getCollVO().size() * MULTIPLICADOR_HASH_CODE;
		hashAtual += hashMensagem + hashTitulo + hashCollVO + hashCodgUnidade;
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
	 * Método utilizado para comparar um objeto do tipo UnidadeSefazVO com a instância desta classe.
	 * Esta comparação será feita pelo código da unidade.
	 * @param unidadeSefazVo
	 * @throws ClassCastException Se o parâmetro não for do tipo sefaz.mt.gestaopessoas.integracao.UnidadeSefazVO.
	 * @throws NullPointerException Se o parâmetro informado for null.
	 * @return int - Retorna <b>-1</b> se o código desta instância for menor que o código do objeto recebido como parâmetro.
	 * 	Retorna <b>0</b> se o código desta instância for igual ao código do objeto recebido como parâmetro.
	 *    Retorna <b>1</b> se o código desta instância for maior que o código do objeto recebido como parâmetro.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int compareTo(Object unidadeSefazVo)
	{
		return this.getCodgUnidade().compareTo(((UnidadeSefazIntegracaoVo) unidadeSefazVo).getCodgUnidade());
	}

	/**
	 * Método utilizado para comparar dois objetos do tipo sefaz.mt.gestaopessoas.integracao.UnidadeSefazVO.
	 * Esta comparação será feita pelo código da unidade.
	 * @param unidadeSefazVo1
	 * @param unidadeSefazVo2
	 * @throws ClassCastException Se pelo menos um dos parâmetros não for do tipo sefaz.mt.gestaopessoas.integracao.UnidadeSefazVO.
	 * @throws NullPointerException Se pelo menos um dos parâmetros informados for null.
	 * @return int - Retorna <b>-1</b> se o código do objeto unidadeSefazVo1 for menor que o código do objeto unidadeSefazVo2.
	 * 	Retorna <b>0</b> se o código do objeto unidadeSefazVo1 for igual ao código do objeto unidadeSefazVo2.
	 *    Retorna <b>1</b> se o código do objeto unidadeSefazVo1 for maior que o código do objeto unidadeSefazVo2.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int compare(Object unidadeSefazVo1, Object unidadeSefazVo2) throws ClassCastException, NullPointerException
	{
		return ((UnidadeSefazVO) unidadeSefazVo1).getCodgUnidade().compareTo(((UnidadeSefazVO) unidadeSefazVo2).getCodgUnidade());
	}
	
	//Métodos UnidadeSefazVO

	public void setTipoUnidade(TipoUnidadeSefazIntegracaoVo tipoUnidade)
	{
		this.tipoUnidade = tipoUnidade;
	}

	public TipoUnidadeSefazIntegracaoVo getTipoUnidade()
	{
		if(!Validador.isObjetoValido(tipoUnidade))
		{
			setTipoUnidade(new TipoUnidadeSefazIntegracaoVo());
		}
		return tipoUnidade;
	}

	public void setCodgUnidade(Integer codgUnidade)
	{
		this.codgUnidade = codgUnidade;
	}

	public void setSiglaUnidade(String siglaUnidade)
	{
		this.siglaUnidade = siglaUnidade;
	}

	public String getSiglaUnidade()
	{
		return siglaUnidade;
	}

	public void setNomeUnidade(String nomeUnidade)
	{
		this.nomeUnidade = nomeUnidade;
	}

	public String getNomeUnidade()
	{
		return nomeUnidade;
	}

	public void setCodgSituacaoUnidade(String codgSituacaoUnidade)
	{
		this.codgSituacaoUnidade = codgSituacaoUnidade;
	}

	public String getCodgSituacaoUnidade()
	{
		return codgSituacaoUnidade;
	}

	public void setNumrMatrServResponsavel(Long numrMatrServResponsavel)
	{
		this.numrMatrServResponsavel = numrMatrServResponsavel;
	}

	public Long getNumrMatrServResponsavel()
	{
		return numrMatrServResponsavel;
	}

	public void setCodgMunicipio(Integer codgMunicipio)
	{
		this.codgMunicipio = codgMunicipio;
	}

	public Integer getCodgMunicipio()
	{
		return codgMunicipio;
	}

	public MunicipioIntegracaoVo getMunicipio()
	{
		return this.municipio;
	}

	public void setMunicipio(MunicipioIntegracaoVo municipio)
	{
		this.municipio = municipio;
	}

	public void ordenarUnidadeSefazPorNome() throws ObjetoObrigatorioException
	{
		if(Validador.isCollectionValida(this.getCollVO()))
		{
			Collections.sort((List)collVO, new Comparator()
													{
														public int compare(Object object1, Object object2)
														{
															return ((UnidadeSefazIntegracaoVo) object1).getNomeUnidade().compareTo(((UnidadeSefazIntegracaoVo)object2).getNomeUnidade());
														}
													});
		}

	}	
}
