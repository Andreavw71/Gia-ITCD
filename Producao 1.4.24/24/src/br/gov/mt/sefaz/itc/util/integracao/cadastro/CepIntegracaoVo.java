package br.gov.mt.sefaz.itc.util.integracao.cadastro;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import java.util.ArrayList;
import java.util.Collection;

import sefaz.mt.cadastro.integracao.CepVO;


/**
 * Classe utilizada como vo de integração.
 * Representa o adapter do vo de cep para o sistema ITC.
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.2 $
 */
public class CepIntegracaoVo implements EntidadeFacade, VoIntegrador
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
	//Atributos do CepVO
	private Integer codgCep;
	private String nomeBairro;
	private String descTipoLogradouro;
	private String descTituloPatente;
	private String nomeLogradouro;
	private String numrInicial;
	private String numrFinal;
	private LocalidadeIntegracaoVo localidade;
	private static final long serialVersionUID = Long.MAX_VALUE;

	public CepIntegracaoVo()
	{
		super();
	}

	public CepIntegracaoVo(CepIntegracaoVo cepIntegracaoVo)
	{
		super();
		setParametroConsulta(cepIntegracaoVo);
	}

	public CepIntegracaoVo(CepVO cepVo) throws ParametroObrigatorioException
	{
		super();
		if (cepVo != null)
		{
			if (Validador.isNumericoValido(cepVo.getCodgCep()))
			{
				setCodgCep(cepVo.getCodgCep());
			}
			if (cepVo.getLocalidade() != null)
			{
				LocalidadeIntegracaoVo loc = new LocalidadeIntegracaoVo();
				loc.setCodgLocalidade(cepVo.getLocalidade().getCodgLocalidade());
				loc.setNomeLocalidade(cepVo.getLocalidade().getNomeLocalidade());
				loc.setUf(new UFIntegracaoVO(cepVo.getLocalidade().getUf()));
				setLocalidade(loc);
			}
		}
	}

	public CepVO toCepVO()
	{
		CepVO retorno = new CepVO();
		if (Validador.isNumericoValido(getCodgCep()))
		{
			retorno.setCodgCep(getCodgCep());
		}
		if (Validador.isStringValida(getNomeBairro()))
		{
			retorno.setNomeBairro(getNomeBairro());
		}
		if (Validador.isStringValida(getDescTipoLogradouro()))
		{
			retorno.setDescTipoLogradouro(getDescTipoLogradouro());
		}
		if (Validador.isStringValida(getDescTituloPatente()))
		{
			retorno.setDescTituloPatente(getDescTituloPatente());
		}
		if (Validador.isStringValida(getNomeLogradouro()))
		{
			retorno.setNomeLogradouro(getNomeLogradouro());
		}
		if (Validador.isStringValida(getNumrInicial()))
		{
			retorno.setNumrInicial(getNumrInicial());
		}
		if (Validador.isStringValida(getNumrFinal()))
		{
			retorno.setNumrFinal(getNumrFinal());
		}
		if (Validador.isObjetoValido(getLocalidade()))
		{
			retorno.setLocalidade(getLocalidade().toLocalidadeVO());
		}
		return retorno;
	}

	public CepIntegracaoVo(Integer codigoCep)
	{
		super();
		setCodgCep(codigoCep);
	}
	{
		setNomeSistema(NOME_SISTEMA);
	}

	public String toString()
	{
		return String.valueOf(getCodgCep());
	}

	public boolean isExiste()
	{
		return !this.equals(new CepIntegracaoVo());
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

	public CepIntegracaoVo getParametroConsulta()
	{
		return (CepIntegracaoVo) parametroConsulta;
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

	public Integer getCodgCep()
	{
		if (Validador.isNumericoValido(codgCep))
		{
			return codgCep;
		}
		else
		{
			setCodgCep(Integer.valueOf("0"));
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
		int hashCodgCep = getCodgCep().hashCode();
		int hashMensagem = getMensagem().hashCode();
		int hashTitulo = getTitulo().hashCode();
		int hashCollVO = getCollVO().size() * MULTIPLICADOR_HASH_CODE;
		hashAtual += hashMensagem + hashTitulo + hashCollVO + hashCodgCep;
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
	 * Método utilizado para comparar um objeto do tipo CepVO com a instância desta classe.
	 * Esta comparação será feita pelo código do cep.
	 * @param cepVo
	 * @throws ClassCastException Se o parâmetro não for do tipo sefaz.mt.cadastro.integracao.CepVO.
	 * @throws NullPointerException Se o parâmetro informado for null.
	 * @return int - Retorna <b>-1</b> se o código desta instância for menor que o código do objeto recebido como parâmetro.
	 * 	Retorna <b>0</b> se o código desta instância for igual ao código do objeto recebido como parâmetro.
	 *    Retorna <b>1</b> se o código desta instância for maior que o código do objeto recebido como parâmetro.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int compareTo(Object cepVo) throws ClassCastException, NullPointerException
	{
		return this.getCodgCep().compareTo(((CepVO) cepVo).getCodgCep());
	}

	/**
	 * Método utilizado para comparar dois objetos do tipo sefaz.mt.cadastro.integracao.CepVO.
	 * Esta comparação será feita pelo código do cep.
	 * @param cepVo1
	 * @param cepVo2
	 * @throws ClassCastException Se pelo menos um dos parâmetros não for do tipo sefaz.mt.cadastro.integracao.CepVO.
	 * @throws NullPointerException Se pelo menos um dos parâmetros informados for null.
	 * @return int - Retorna <b>-1</b> se o código do objeto cepVo1 for menor que o código do objeto cepVo2.
	 * 	Retorna <b>0</b> se o código do objeto cepVo1 for igual ao código do objeto cepVo2.
	 *    Retorna <b>1</b> se o código do objeto cepVo1 for maior que o código do objeto cepVo2.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int compare(Object cepVo1, Object cepVo2) throws ClassCastException, NullPointerException
	{
		return ((CepVO) cepVo1).getCodgCep().compareTo(((CepVO) cepVo2).getCodgCep());
	}
	//Métodos do CepVO

	public void setCodgCep(Integer codgCep)
	{
		this.codgCep = codgCep;
	}

	public void setNomeBairro(String nomeBairro)
	{
		this.nomeBairro = nomeBairro;
	}

	public String getNomeBairro()
	{
		return nomeBairro;
	}

	public void setDescTipoLogradouro(String descTipoLogradouro)
	{
		this.descTipoLogradouro = descTipoLogradouro;
	}

	public String getDescTipoLogradouro()
	{
		return descTipoLogradouro;
	}

	public void setDescTituloPatente(String descTituloPatente)
	{
		this.descTituloPatente = descTituloPatente;
	}

	public String getDescTituloPatente()
	{
		return descTituloPatente;
	}

	public void setNomeLogradouro(String nomeLogradouro)
	{
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getNomeLogradouro()
	{
		return nomeLogradouro;
	}

	public void setNumrInicial(String numrInicial)
	{
		this.numrInicial = numrInicial;
	}

	public String getNumrInicial()
	{
		return numrInicial;
	}

	public void setNumrFinal(String numrFinal)
	{
		this.numrFinal = numrFinal;
	}

	public String getNumrFinal()
	{
		return numrFinal;
	}

	public void setLocalidade(LocalidadeIntegracaoVo localidade)
	{
		this.localidade = localidade;
	}

	public LocalidadeIntegracaoVo getLocalidade()
	{
		return localidade;
	}
}
