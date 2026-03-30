package br.gov.mt.sefaz.itc.util.integracao.cadastro;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.itc.util.dominio.DomnTipoLogradouro;

import java.util.ArrayList;
import java.util.Collection;

import sefaz.mt.cadastro.integracao.EnderecoVO;


/**
 * Classe utilizada como vo de integração.
 * Representa o adapter do vo endereço para o sistema ITC.
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.1.1.1 $
 */
public class EnderecoIntegracaoVo implements EntidadeFacade, VoIntegrador
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
	private String complemento;
	//Atributos do EnderecoVo
	private Collection listaTipoLogradouro;
	private String tipoLogr;
	private String logradouro;
	private String bairro;
	private long codgEndereco;
	private String numrLogradouro;
	private Integer numrDddTelefone;
	private Integer numrTelefone;
	private String endrEmail;
	private String nomeLocalidade;
	private CepIntegracaoVo cep;
	private UFIntegracaoVO uf;
	private String pontoReferencia;
	private static final long serialVersionUID = Long.MAX_VALUE;
   private DomnTipoLogradouro domnTipoLogradouro;

	public void setComplemento(String arg0)
	{
		this.complemento = arg0;
	}

	public String getComplemento()
	{
		if (!Validador.isStringValida(this.complemento))
		{
			setComplemento(STRING_VAZIA);
		}
		return this.complemento;
	}

	public EnderecoIntegracaoVo()
	{
		super();
	}

	public EnderecoIntegracaoVo(EnderecoIntegracaoVo enderecoIntegracaoVo)
	{
		super();
		setParametroConsulta(enderecoIntegracaoVo);
	}

	public EnderecoIntegracaoVo(EnderecoVO enderecoVo) throws ParametroObrigatorioException
	{
		super();
		if (enderecoVo != null)
		{
			if (Validador.isObjetoValido(enderecoVo.getUf()))
			{
				setUf(new UFIntegracaoVO(enderecoVo.getUf()));
			}
			if (Validador.isNumericoValido(enderecoVo.getCodgEndereco()))
			{
				setCodgEndereco(enderecoVo.getCodgEndereco());
			}
			if (Validador.isStringValida(enderecoVo.getLogradouro()))
			{
				setLogradouro(enderecoVo.getLogradouro());
			}
			if (Validador.isStringValida(enderecoVo.getBairro()))
			{
				setBairro(enderecoVo.getBairro());
			}
			if (Validador.isStringValida(enderecoVo.getNumrLogradouro()))
			{
				setNumrLogradouro(enderecoVo.getNumrLogradouro());
			}
			if (Validador.isNumericoValido(enderecoVo.getNumrDddTelefone()))
			{
				setNumrDddTelefone(enderecoVo.getNumrDddTelefone());
			}
			if (Validador.isNumericoValido(enderecoVo.getNumrTelefone()))
			{
				setNumrTelefone(enderecoVo.getNumrTelefone());
			}
			if (Validador.isStringValida(enderecoVo.getEndrEmail()))
			{
				setEndrEmail(enderecoVo.getEndrEmail());
			}
			if (Validador.isStringValida(enderecoVo.getEndrComplemento()))
			{
				setComplemento(enderecoVo.getEndrComplemento());
			}
			if (enderecoVo.getCep() != null)
			{
				setCep(new CepIntegracaoVo(enderecoVo.getCep()));
			}
			if (Validador.isStringValida(enderecoVo.getTipoLogr()))
			{
				setTipoLogr(enderecoVo.getTipoLogr());
			}
			if (Validador.isStringValida(enderecoVo.getEndrPontoReferencia()))
			{
				setPontoReferencia(enderecoVo.getEndrPontoReferencia());
			}
		}
	}

	public EnderecoVO toEnderecoVo() throws ParametroObrigatorioException
	{
		EnderecoVO retorno = new EnderecoVO();
		if (Validador.isObjetoValido(getUf()))
		{
			retorno.setUf(getUf().toUfVO());
		}
		if (Validador.isNumericoValido(getCodgEndereco()))
		{
			retorno.setCodgEndereco(this.getCodgEndereco());
		}
		if (Validador.isStringValida(getLogradouro()))
		{
			retorno.setLogradouro(getLogradouro());
		}
		if (Validador.isStringValida(getBairro()))
		{
			retorno.setBairro(getBairro());
		}
		if (Validador.isStringValida(getNumrLogradouro()))
		{
			retorno.setNumrLogradouro(getNumrLogradouro());
		}
		if (Validador.isNumericoValido(getNumrDddTelefone()))
		{
			retorno.setNumrDddTelefone(getNumrDddTelefone());
		}
		if (Validador.isNumericoValido(getNumrTelefone()))
		{
			retorno.setNumrTelefone(getNumrTelefone());
		}
		if (Validador.isStringValida(getEndrEmail()))
		{
			retorno.setEndrEmail(getEndrEmail());
		}
		if (Validador.isStringValida(getComplemento()))
		{
			retorno.setEndrComplemento(getComplemento());
		}
		if (getCep() != null)
		{
			retorno.setCep(getCep().toCepVO());
		}
		if (Validador.isStringValida(getTipoLogr()))
		{
			retorno.setTipoLogr(getTipoLogr());
		}
		if (Validador.isStringValida(getPontoReferencia()))
		{
			retorno.setEndrPontoReferencia(getPontoReferencia());
		}
		return retorno;
	}

	public EnderecoIntegracaoVo(long codigoEndereco)
	{
		setCodgEndereco(codigoEndereco);
	}
	{
		setNomeSistema(NOME_SISTEMA);
	}

	public void setLogradouro(String logradouro)
	{
		if (!Validador.isStringValida(logradouro))
		{
			this.logradouro = (logradouro.trim().toUpperCase());
		}
		else
		{
			this.logradouro = logradouro;
		}
	}

	public void setBairro(String bairro)
	{
		if (!Validador.isStringValida(bairro))
		{
			this.bairro = (bairro.trim().toUpperCase());
		}
		else
		{
			this.bairro = bairro;
		}
	}

	public String toString()
	{
		return String.valueOf(getCodgEndereco());
	}

	public boolean isExiste()
	{
		return !this.equals(new EnderecoIntegracaoVo());
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

	public EnderecoIntegracaoVo getParametroConsulta()
	{
		return (EnderecoIntegracaoVo) parametroConsulta;
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

	public String getTelefoneFormatado()
	{
		StringBuffer numeroFormatado = new StringBuffer();
		if (Validador.isNumericoValido(getNumrDddTelefone()))
		{
			numeroFormatado.append("(");
			numeroFormatado.append(getNumrDddTelefone());
			numeroFormatado.append(")");
		}
		else
		{
			if (Validador.isNumericoValido(getNumrTelefone()))
			{
				numeroFormatado.append("(xx)");
			}
		}
		if (Validador.isNumericoValido(getNumrTelefone()))
		{
			if (getNumrTelefone().intValue() > 9999)
			{
				String numeroNaoFormatado = getNumrTelefone().toString();
				String sufixo = 
							  numeroNaoFormatado.substring((numeroNaoFormatado.length() - 4), (numeroNaoFormatado.length()));
				String prefixo = numeroNaoFormatado.substring(0, (numeroNaoFormatado.length() - 4));
				if (Validador.isStringValida(prefixo))
				{
					numeroFormatado.append(prefixo + "-");
				}
				if (Validador.isStringValida(sufixo))
				{
					numeroFormatado.append(sufixo);
				}
			}
			else
			{
				numeroFormatado.append(getNumrTelefone());
			}
		}
		return numeroFormatado.toString();
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
		int hashCodgEndereco = new Long(getCodgEndereco()).hashCode();
		int hashCodgCep = (getCep() != null ? getCep().hashCode() : 0);
		int hashLogradouro = (Validador.isStringValida(getLogradouro()) ? getLogradouro().hashCode() : 0);
		int hashBairro = (Validador.isStringValida(getBairro()) ? getLogradouro().hashCode() : 0);
		int hashNumeroLogradouro = (Validador.isStringValida(getNumrLogradouro()) ? getNumrLogradouro().hashCode() : 0);
		int hashDDDTelefone = (Validador.isNumericoValido(getNumrDddTelefone()) ? getNumrDddTelefone().hashCode() : 0);
		int hashNumeroTelefone = (Validador.isNumericoValido(getNumrTelefone()) ? getNumrTelefone().hashCode() : 0);
		int hashEmail = (Validador.isStringValida(getEndrEmail()) ? getEndrEmail().hashCode() : 0);
		int hashMensagem = getMensagem().hashCode();
		int hashTitulo = getTitulo().hashCode();
		int hashCollVO = getCollVO().size() * MULTIPLICADOR_HASH_CODE;
		hashAtual += hashMensagem + hashTitulo + hashCollVO + hashCodgEndereco + hashCodgCep;
		hashAtual += 
					 hashLogradouro + hashBairro + hashNumeroLogradouro + hashDDDTelefone + hashNumeroTelefone + hashEmail;
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
	 * Método utilizado para comparar um objeto do tipo EnderecoVO com a instï¿½ncia desta classe.
	 * Esta comparaï¿½ï¿½o serï¿½ feita pelo código do entereï¿½o.
	 * @param enderecoVo
	 * @throws ClassCastException Se o parâmetro não for do tipo sefaz.mt.cadastro.integracao.EnderecoVO.
	 * @throws NullPointerException Se o parâmetro informado for null.
	 * @return int - Retorna <b>-1</b> se o código desta instï¿½ncia for menor que o código do objeto recebido como parâmetro.
	 * 	Retorna <b>0</b> se o código desta instï¿½ncia for igual ao código do objeto recebido como parâmetro.
	 *    Retorna <b>1</b> se o código desta instï¿½ncia for maior que o código do objeto recebido como parâmetro.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int compareTo(Object enderecoVo) throws ClassCastException, NullPointerException
	{
		return new Long(this.getCodgEndereco()).compareTo(new Long(((EnderecoVO) enderecoVo).getCodgEndereco()));
	}

	/**
	 * Método utilizado para comparar dois objetos do tipo sefaz.mt.cadastro.integracao.EnderecoVO.
	 * Esta comparaï¿½ï¿½o serï¿½ feita pelo código do entereï¿½o.
	 * @param enderecoVo1
	 * @param enderecoVo2
	 * @throws ClassCastException Se pelo menos um dos parâmetros não for do tipo sefaz.mt.cadastro.integracao.EnderecoVO.
	 * @throws NullPointerException Se pelo menos um dos parâmetros informados for null.
	 * @return int - Retorna <b>-1</b> se o código do objeto enderecoVo1 for menor que o código do objeto enderecoVo2.
	 * 	Retorna <b>0</b> se o código do objeto enderecoVo1 for igual ao código do objeto enderecoVo2.
	 *    Retorna <b>1</b> se o código do objeto enderecoVo1 for maior que o código do objeto enderecoVo2.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int compare(Object enderecoVo1, Object enderecoVo2) throws ClassCastException, NullPointerException
	{
		return new Long(((EnderecoVO) enderecoVo1).getCodgEndereco()).compareTo(new Long(((EnderecoVO) enderecoVo2).getCodgEndereco()));
	}
	//Métodos do EnderecoVO

	public void setTipoLogr(String tipoLogr)
	{
		this.tipoLogr = tipoLogr;
	}

	public String getTipoLogr()
	{
		if (Validador.isStringValida(tipoLogr))
		{
			return tipoLogr;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	public String getLogradouro()
	{
		if (Validador.isStringValida(logradouro))
		{
			return logradouro;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	public String getBairro()
	{
		if (Validador.isStringValida(bairro))
		{
			return bairro;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	public void setCodgEndereco(long codgEndereco)
	{
		this.codgEndereco = codgEndereco;
	}

	public long getCodgEndereco()
	{
		return codgEndereco;
	}

	public void setNumrLogradouro(String numrLogradouro)
	{
		this.numrLogradouro = numrLogradouro;
	}

	public String getNumrLogradouro()
	{
		if (Validador.isStringValida(numrLogradouro))
		{
			return numrLogradouro;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	public void setNumrDddTelefone(Integer numrDddTelefone)
	{
		this.numrDddTelefone = numrDddTelefone;
	}

	public Integer getNumrDddTelefone()
	{
		return numrDddTelefone;
	}

	public void setNumrTelefone(Integer numrTelefone)
	{
		this.numrTelefone = numrTelefone;
	}

	public Integer getNumrTelefone()
	{
		return numrTelefone;
	}

	public void setEndrEmail(String endrEmail)
	{
		this.endrEmail = endrEmail;
	}

	public String getEndrEmail()
	{
		if (Validador.isStringValida(endrEmail))
		{
			return endrEmail;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	public void setNomeLocalidade(String nomeLocalidade)
	{
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeLocalidade()
	{
		if (Validador.isStringValida(nomeLocalidade))
		{
			return nomeLocalidade;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	public void setCep(CepIntegracaoVo cep)
	{
		this.cep = cep;
	}

	public CepIntegracaoVo getCep()
	{
		if (!Validador.isObjetoValido(cep))
		{
			setCep(new CepIntegracaoVo());
		}
		return cep;
	}

	public void setUf(UFIntegracaoVO uf)
	{
		this.uf = uf;
	}

	public UFIntegracaoVO getUf()
	{
		if (!Validador.isObjetoValido(uf))
		{
			setUf(new UFIntegracaoVO());
		}
		return uf;
	}

	public void setPontoReferencia(String pontoReferencia)
	{
		this.pontoReferencia = pontoReferencia;
	}

	public String getPontoReferencia()
	{
		if (Validador.isStringValida(pontoReferencia))
		{
			return pontoReferencia;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	public void setListaTipoLogradouro(Collection listaTipoLogradouro)
	{
		this.listaTipoLogradouro = listaTipoLogradouro;
	}

	public Collection getListaTipoLogradouro()
	{
		if (listaTipoLogradouro == null)
		{
			setListaTipoLogradouro(new ArrayList());
		}
		return listaTipoLogradouro;
	}

   public void setDomnTipoLogradouro(DomnTipoLogradouro domnTipoLogradouro)
   {
      this.domnTipoLogradouro = domnTipoLogradouro;
   }

   public DomnTipoLogradouro getDomnTipoLogradouro()
   {  
      try
      {
         setDomnTipoLogradouro( new DomnTipoLogradouro( Integer.parseInt( this.tipoLogr)));
      }catch( NumberFormatException e)
      {
         setDomnTipoLogradouro( new DomnTipoLogradouro());
         e.printStackTrace();
      }
      return domnTipoLogradouro;
   }
}
