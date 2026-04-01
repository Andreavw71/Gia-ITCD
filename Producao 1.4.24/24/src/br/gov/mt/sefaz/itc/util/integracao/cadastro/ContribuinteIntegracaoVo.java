package br.gov.mt.sefaz.itc.util.integracao.cadastro;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDocumento;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoPess;

import java.io.Serializable;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import sefaz.mt.cadastro.dominio.DomnTipoInfoCnae;
import sefaz.mt.cadastro.integracao.ContribuinteVO;


//import sefaz.mt.cadastro.integracao.util.dominio.DomnTipoCpf;

/**
 * Classe utilizada como vo de integração.
 * Representa o adapter do vo de contribuinte para o sistema ITC.
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.4 $
 */
public class ContribuinteIntegracaoVo implements EntidadeFacade, VoIntegrador
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
	private DomnTipoDocumento tipoDocumento;
	//Atributos do ContribuinteVo
	private Long numrInscEstadual;
	private String numrDocumento;
	private Long numrContribuinte;
	private String nomeContribuinte;
	// Endereco	
	private long codgEndereco;
	private String endereco;
	private String enderecoNumero;
	private String enderecoComplemento;
	private String enderecoBairro;
	private String pontoReferencia;
	private Long enderecoCEP;
	private String municipio;
	private String siglaUF;
	private Integer numrDdd;
	private Integer numrTelefone;
	private String email;
	private Object[] cnae;
	private String domicilioFiscal;
	//private String descTipoDocumento; este atributo morreu para evitar confusao com tipoDocumento
	private String crc;
	private String statInscricaoEstadual;
	private int codgRegimePagamento;
	private String descRegimePagamento;
	private Collection quadroSocietario;
	private String nomeFantasia;
	private String numrInscricaoUF;
	//private DomnTipoCpf tipoCpf;
	private DomnTipoPess tipoPessoa;
	private Date dataAtualizacao;
	private Integer codgNaturezaJuridica;
	private Date dataAtlz;
	private Integer tipoContribuinte;
	private int codgAtividadeEconomica;
	private CnaeVO cnaePrimaria;
	private Integer codgMunicipio;
	private boolean cnaeServico;
	private Collection colecaoTipoDocumento;

	/**
	 * Este método foi criado para resolver a falta de encapsulamento do Endereco
	 * 
	 * @param enderecoVo
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void atribuiEndereco(EnderecoIntegracaoVo enderecoVo)
	{
		if (enderecoVo != null)
		{
			if (Validador.isNumericoValido(enderecoVo.getCodgEndereco()))
			{
				setCodgEndereco(enderecoVo.getCodgEndereco());
			}
			if (Validador.isStringValida(enderecoVo.getLogradouro()))
			{
				setEndereco(enderecoVo.getLogradouro());
			}
			if (Validador.isStringValida(enderecoVo.getBairro()))
			{
				setEnderecoBairro(enderecoVo.getBairro());
			}
			if (Validador.isStringValida(enderecoVo.getNumrLogradouro()))
			{
				setEnderecoNumero(enderecoVo.getNumrLogradouro());
			}
			if (Validador.isNumericoValido(enderecoVo.getNumrDddTelefone()))
			{
				setNumrDdd(enderecoVo.getNumrDddTelefone());
			}
			if (Validador.isNumericoValido(enderecoVo.getNumrTelefone()))
			{
				setNumrTelefone(enderecoVo.getNumrTelefone());
			}
			if (Validador.isStringValida(enderecoVo.getEndrEmail()))
			{
				setEmail(enderecoVo.getEndrEmail());
			}
			if (Validador.isStringValida(enderecoVo.getComplemento()))
			{
				setEnderecoComplemento(enderecoVo.getComplemento());
			}
			if (enderecoVo.getCep() != null)
			{
				//setCep(new CepIntegracaoVo(enderecoVo.getCep()));
				setEnderecoCEP(new Long(enderecoVo.getCep().getCodgCep().intValue()));
			}
			if (Validador.isStringValida(enderecoVo.getPontoReferencia()))
			{
				setPontoReferencia(enderecoVo.getPontoReferencia());
			}
			if (Validador.isObjetoValido(enderecoVo.getUf()))
			{
				setSiglaUF(enderecoVo.getUf().getSiglUf());
			}
			if (Validador.isStringValida(enderecoVo.getCep().getLocalidade().getNomeLocalidade()))
			{
				setMunicipio(enderecoVo.getCep().getLocalidade().getNomeLocalidade());
			}
		}
	}

	/**
	 * Construtor Padrão
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public ContribuinteIntegracaoVo()
	{
		super();
	}

	/**
	 * Construtor que recebe o ContribuinteVo para efetuar o translator
	 * @param contribuinteVO
	 * @throws ParametroObrigatorioException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public ContribuinteIntegracaoVo(ContribuinteVO contribuinteVO) throws ParametroObrigatorioException
	{
		super();
		if (contribuinteVO != null)
		{
			if (contribuinteVO.getCnaePrimaria() != null)
			{
				setCnaePrimaria(getCnaePrimaria());
			}
			if (Validador.isNumericoValido(contribuinteVO.getCodgEndereco()))
			{
				setCodgEndereco(contribuinteVO.getCodgEndereco());
			}
			if (Validador.isNumericoValido(contribuinteVO.getEnderecoCEP()))
			{
				setEnderecoCEP(contribuinteVO.getEnderecoCEP());
			}
			if (Validador.isNumericoValido(contribuinteVO.getCodgMunicipio()))
			{
				setCodgMunicipio(contribuinteVO.getCodgMunicipio());
			}
			else
			{
				setMunicipio(contribuinteVO.getMunicipio());
			}
			if (Validador.isStringValida(contribuinteVO.getNomeContribuinte()))
			{
				setNomeContribuinte(contribuinteVO.getNomeContribuinte());
			}
			if (Validador.isStringValida(contribuinteVO.getNomeFantasia()))
			{
				setNomeFantasia(contribuinteVO.getNomeFantasia());
			}
			if (Validador.isNumericoValido(contribuinteVO.getNumrContribuinte()))
			{
				setNumrContribuinte(contribuinteVO.getNumrContribuinte());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o número do contribuinte" + 
								  QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(contribuinteVO.getNumrDocumento()))
			{
				setNumrDocumento(StringUtil.retiraMascara(contribuinteVO.getNumrDocumento()));
			}
			if (Validador.isNumericoValido(contribuinteVO.getTipoContribuinte()))
			{
				setTipoContribuinte(contribuinteVO.getTipoContribuinte());
			}
			if (Validador.isStringValida(contribuinteVO.getDescTipoDocumento()))
			{
				if(contribuinteVO.getDescTipoDocumento().equals(DomnTipoDocumento.DESC_CPF))
				{
					setTipoDocumento(new DomnTipoDocumento(DomnTipoDocumento.CPF));
				}
				else if(contribuinteVO.getDescTipoDocumento().equals(DomnTipoDocumento.DESC_CNPJ))
				{
				   setTipoDocumento(new DomnTipoDocumento(DomnTipoDocumento.CNPJ));
				}
				else
				{
				   setTipoDocumento(new DomnTipoDocumento(DomnTipoDocumento.OUTROS));
				}
			}
		   if (Validador.isDominioNumericoValido(contribuinteVO.getTipoPessoa()))
		   {
		      setTipoPessoa(getTipoPessoa());
		   }
		   if (Validador.isNumericoValido(contribuinteVO.getNumrInscEstadual()))
		   {
		      setNumrInscEstadual(contribuinteVO.getNumrInscEstadual());
		   }
		   if (Validador.isStringValida(contribuinteVO.getStatInscricaoEstadual()))
		   {
		      setStatInscricaoEstadual(contribuinteVO.getStatInscricaoEstadual());
		   }			
		}
	}

	/**
	 * Construtor que recebe o Parametro de Consulta
	 * @param contribuinteIntegracaoVo
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public ContribuinteIntegracaoVo(ContribuinteIntegracaoVo contribuinteIntegracaoVo)
	{
		super();
		setParametroConsulta(contribuinteIntegracaoVo);
	}

	/**
	 * Construtor que recebe o Número do Contribuinte
	 * @param numeroContribuinte
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public ContribuinteIntegracaoVo(Long numeroContribuinte)
	{
		super();
		setNumrContribuinte(numeroContribuinte);
	}

	/**
	 * Construtor que recebe uma Lista de Contribuintes
	 * @param listaDeContribuintes
	 * @throws ParametroObrigatorioException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public ContribuinteIntegracaoVo(Collection listaDeContribuintes) throws ParametroObrigatorioException
	{
		super();
		if (Validador.isCollectionValida(listaDeContribuintes))
		{
			Iterator it = listaDeContribuintes.iterator();
			while (it.hasNext())
			{
				Object objeto = it.next();
				if (objeto instanceof ContribuinteVO)
				{
					if (Validador.isCollectionValida(getCollVO()))
					{
						getCollVO().add(new ContribuinteIntegracaoVo((ContribuinteVO) objeto));
					}
					else
					{
						Collection lista = getCollVO();
						lista.add(new ContribuinteIntegracaoVo((ContribuinteVO) objeto));
						setCollVO(lista);
					}
				}
			}
		}
	}
	{
		setNomeSistema(NOME_SISTEMA);
	}

	/**
	 * Atribui o Tipo de Documento
	 * @param arg0
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setTipoDocumento(DomnTipoDocumento arg0)
	{
		this.tipoDocumento = arg0;
	}

	/**
	 * Retorna o Tipo de Documento
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public DomnTipoDocumento getTipoDocumento()
	{
		if (!Validador.isDominioNumericoValido(this.tipoDocumento))
		{
			if (Validador.isStringValida(this.numrDocumento))
			{
			   if (this.numrDocumento.length() == 11 && Validador.isCPFValido(this.numrDocumento))
			   {
			      setTipoDocumento(new DomnTipoDocumento(DomnTipoDocumento.CPF));
			   }
				else if (this.numrDocumento.length() == 14 &&  Validador.isCNPJValido(this.numrDocumento))
				{
					setTipoDocumento(new DomnTipoDocumento(DomnTipoDocumento.CNPJ));
				}
				else
				{
					setTipoDocumento(new DomnTipoDocumento(DomnTipoDocumento.OUTROS));
				}
			}
			else
			{
				setTipoDocumento(new DomnTipoDocumento(-1));
			}
		}
		return this.tipoDocumento;
	}

	/**
	 * Sobrescreve o toString
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String toString()
	{
		return String.valueOf(getNumrContribuinte());
	}

	/**
	 * Verifica se o Vo é válido
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public boolean isExiste()
	{
		return !this.equals(new ContribuinteIntegracaoVo());
	}

	/**
	 * Verifica se o VO possui uma CollVo
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public boolean isExisteCollVO()
	{
		return Validador.isCollectionValida(getCollVO());
	}

	/**
	 * Verifica se a Consulta é Parametrizada
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
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
	 * Verifica se a Consulta é Completa
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public boolean isConsultaCompleta()
	{
		return consultaCompleta;
	}

	/**
	 * Atribui se a Consulta é Completa
	 * @param consultaCompleta
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setConsultaCompleta(boolean consultaCompleta)
	{
		this.consultaCompleta = consultaCompleta;
	}

	/**
	 * Retorna o Parametro de Consulta
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public ContribuinteIntegracaoVo getParametroConsulta()
	{
		return (ContribuinteIntegracaoVo) parametroConsulta;
	}

	/**
	 * Atribui o Parametro de Consulta
	 * @param parametroConsulta
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setParametroConsulta(EntidadeFacade parametroConsulta)
	{
		this.parametroConsulta = parametroConsulta;
	}

	/**
	 * Atribui a CollVo
	 * @param collVO
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setCollVO(Collection collVO)
	{
		this.collVO = collVO;
	}

	/**
	 * Retorna a CollVo
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
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

	/**
	 * Atribui a Origem
	 * @param origem
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setOrigem(int origem)
	{
		this.origem = origem;
	}

	/**
	 * Retorna a Origem
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int getOrigem()
	{
		return origem;
	}

	/**
	 * Atribui o Usuario Logado
	 * @param usuarioLogado
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setUsuarioLogado(long usuarioLogado)
	{
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * Retorna o Usuario Logado
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public long getUsuarioLogado()
	{
		return usuarioLogado;
	}

	/**
	 * Atribui a Mensagem
	 * @param mensagem
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	/**
	 * Retorna a Mensagem
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
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
	 * Atribui o Nome do Sistema
	 * @param nomeSistema
	 * @throws ProibidoMudarNomeSistemaException
	 * @implemented by Rogério Sanches de Oliveira
	 */
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

	/**
	 * Retorna o Nome do Sistema
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
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

	/**
	 * Atribui o Titulo
	 * @param titulo
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	/**
	 * Retorna o Titulo
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
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
	 * Retorna o Número do Contribuinte
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public Long getNumrContribuinte()
	{
		if (!Validador.isNumericoValido(numrContribuinte))
		{
			setNumrContribuinte(new Long(0));
		}
		return numrContribuinte;
	}

	/**
	 * Sobrescreve o Clone
	 * @return
	 * @throws CloneNotSupportedException
	 * @implemented by Rogério Sanches de Oliveira
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

	/**
	 * Sobrescreve o HashCode
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int hashCode()
	{
		int hashAtual = 0;
		int hashNumeroContribuinte = getNumrContribuinte().hashCode();
		int hashMensagem = getMensagem().hashCode();
		int hashTitulo = getTitulo().hashCode();
		int hashCollVO = getCollVO().size() * MULTIPLICADOR_HASH_CODE;
		hashAtual += hashMensagem + hashTitulo + hashCollVO + hashNumeroContribuinte;
		hashAtual += (int) getUsuarioLogado() + getOrigem();
		hashAtual *= MULTIPLICADOR_HASH_CODE;
		return hashAtual;
	}

	/**
	 * Sobrescreve o Equals
	 * @param outroEntidadeVO
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
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
	 * Método utilizado para comparar um objeto do tipo ContribuinteVO com a instï¿½ncia desta classe.
	 * Esta comparaï¿½ï¿½o serï¿½ feita pelo número do contribuinte.
	 * @param contribuinteVo
	 * @throws ClassCastException Se o parâmetro não for do tipo sefaz.mt.cadastro.integracao.ContribuinteVO.
	 * @throws NullPointerException Se o parâmetro informado for null.
	 * @return int - Retorna <b>-1</b> se o código desta instï¿½ncia for menor que o código do objeto recebido como parâmetro.
	 * 	Retorna <b>0</b> se o código desta instï¿½ncia for igual ao código do objeto recebido como parâmetro.
	 *    Retorna <b>1</b> se o código desta instï¿½ncia for maior que o código do objeto recebido como parâmetro.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int compareTo(Object contribuinteVo) throws ClassCastException, NullPointerException
	{
		return this.getNumrContribuinte().compareTo(((ContribuinteVO) contribuinteVo).getNumrContribuinte());
	}

	/**
	 * Método utilizado para comparar dois objetos do tipo sefaz.mt.cadastro.integracao.ContribuinteVO.
	 * Esta comparaï¿½ï¿½o serï¿½ feita pelo número do contribuinte.
	 * @param contribuinteVo1
	 * @param contribuinteVo2
	 * @throws ClassCastException Se pelo menos um dos parâmetros não for do tipo sefaz.mt.cadastro.integracao.ContribuinteVO.
	 * @throws NullPointerException Se pelo menos um dos parâmetros informados for null.
	 * @return int - Retorna <b>-1</b> se o código do objeto contribuinteVo1 for menor que o código do objeto contribuinteVo2.
	 * 	Retorna <b>0</b> se o código do objeto contribuinteVo1 for igual ao código do objeto contribuinteVo2.
	 *    Retorna <b>1</b> se o código do objeto contribuinteVo1 for maior que o código do objeto contribuinteVo2.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int compare(Object contribuinteVo1, Object contribuinteVo2) throws ClassCastException, NullPointerException
	{
		return ((ContribuinteVO) contribuinteVo1).getNumrContribuinte().compareTo(((ContribuinteVO) contribuinteVo2).getNumrContribuinte());
	}
	//Métodos do ContribuinteVo

	/**
	 * Atribui o Numero de Inscrição Estadual
	 * @param numrInscEstadual
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setNumrInscEstadual(Long numrInscEstadual)
	{
		this.numrInscEstadual = numrInscEstadual;
	}

	/**
	 * Retorna o Número de Inscrição Estadual
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public Long getNumrInscEstadual()
	{
		return numrInscEstadual;
	}

	/**
	 * Atribui o Numero de Documento
	 * @param numrDocumento
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setNumrDocumento(String numrDocumento)
	{
		this.numrDocumento = numrDocumento;
	}

	/**
	 * Retorna o Número de Documento
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getNumrDocumento()
	{
		if (!Validador.isStringValida(numrDocumento))
			return STRING_VAZIA;
		return numrDocumento;
	}

	/**
	 * Atribui o Número do Contribuinte
	 * @param numrContribuinte
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setNumrContribuinte(Long numrContribuinte)
	{
		this.numrContribuinte = numrContribuinte;
	}

	/**
	 * Atribui o Nome do Contribuinte
	 * @param nomeContribuinte
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setNomeContribuinte(String nomeContribuinte)
	{
		this.nomeContribuinte = nomeContribuinte;
	}

	/**
	 * Retorna o Nome do Contribuinte
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getNomeContribuinte()
	{	
		if (!Validador.isStringValida(nomeContribuinte))
		{
			setNomeContribuinte(STRING_VAZIA);
		}
		return nomeContribuinte;
	}

	/**
	 * Atribui o Endereco
	 * @param endereco
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setEndereco(String endereco)
	{
		this.endereco = endereco;
	}

	/**
	 * Retorna o Endereco
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getEndereco()
	{
	   if (!Validador.isStringValida(endereco))
	   {
	      setEndereco(STRING_VAZIA);
	   }
		return endereco;
	}

	/**
	 * Atribui o Numero no Endereco
	 * @param enderecoNumero
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setEnderecoNumero(String enderecoNumero)
	{
		this.enderecoNumero = enderecoNumero;
	}

	/**
	 * Retorna o Numero no Endereco
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getEnderecoNumero()
	{
		if(!Validador.isStringValida(enderecoNumero))
		{
			setEnderecoNumero(STRING_VAZIA);
		}
		return enderecoNumero;
	}

	/**
	 * Atribui o Endereco de Complemento
	 * @param enderecoComplemento
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setEnderecoComplemento(String enderecoComplemento)
	{
		this.enderecoComplemento = enderecoComplemento;
	}

	/**
	 * Retorna o Endereço Complemento
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getEnderecoComplemento()
	{
		if(!Validador.isStringValida(enderecoComplemento))
		{
			setEnderecoComplemento(STRING_VAZIA);
		}
		return enderecoComplemento;
	}

	/**
	 * Atribui o Endereco Bairro
	 * @param enderecoBairro
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setEnderecoBairro(String enderecoBairro)
	{
		this.enderecoBairro = enderecoBairro;
	}

	/**
	 * Retorna o Endereço Bairro
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getEnderecoBairro()
	{
		if(!Validador.isStringValida(enderecoBairro))
		{
			setEnderecoBairro(STRING_VAZIA);
		}
		return enderecoBairro;
	}

	/**
	 * Atribui o CEP
	 * @param enderecoCEP
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setEnderecoCEP(Long enderecoCEP)
	{
		this.enderecoCEP = enderecoCEP;
	}

	/**
	 * Retorna o CEP
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public Long getEnderecoCEP()
	{
		if(!Validador.isNumericoValido(enderecoCEP))
		{
			setEnderecoCEP(new Long(0));
		}
		return enderecoCEP;
	}

	/**
	 * Atribui o Municipio
	 * @param municipio
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setMunicipio(String municipio)
	{
		this.municipio = municipio;
	}

	/**
	 * Retorna o Municipio
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getMunicipio()
	{
		if (!Validador.isStringValida(this.municipio))
		{
			return STRING_VAZIA;
		}
		return municipio;
	}

	/**
	 * Atribui a Sigla UF
	 * @param siglaUF
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setSiglaUF(String siglaUF)
	{
		this.siglaUF = siglaUF;
	}

	/**
	 * Retorna a Sigla UF
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getSiglaUF()
	{
		if(!Validador.isStringValida(siglaUF))
		{
			setSiglaUF(STRING_VAZIA);
		}
		return siglaUF;
	}

	/**
	 * Atribui o Numero Telefone
	 * @param numrTelefone
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setNumrTelefone(Integer numrTelefone)
	{
		this.numrTelefone = numrTelefone;
	}

	/**
	 * Retorna o Numero de Telefone
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public Integer getNumrTelefone()
	{
		if(!Validador.isNumericoValido(numrTelefone))
		{
			setNumrTelefone(new Integer(0));
		}
		return numrTelefone;
	}

	/**
	 * Atribui o E-mail
	 * @param email
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Retorna o E-mail
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getEmail()
	{
		if (!Validador.isStringValida(email))
		{
			setEmail(STRING_VAZIA);
		}
		return email;
	}

	/**
	 * Atribui o CNAE
	 * @param cnae
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setCnae(Object[] cnae)
	{
		this.cnae = cnae;
	}

	/**
	 * Retorna o CNAE
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public Object[] getCnae()
	{
		return cnae;
	}

	/**
	 * Atribui o Domicilio Fiscal
	 * @param domicilioFiscal
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setDomicilioFiscal(String domicilioFiscal)
	{
		this.domicilioFiscal = domicilioFiscal;
	}

	/**
	 * Retorna o Dominicio Fiscal
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getDomicilioFiscal()
	{
		if (!Validador.isStringValida(domicilioFiscal))
		{
			setDomicilioFiscal(STRING_VAZIA);
		}
		return domicilioFiscal;
	}

	/**
	 * Atribui o CRC
	 * @param crc
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setCrc(String crc)
	{
		this.crc = crc;
	}

	/**
	 * Retorna o CRC
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getCrc()
	{
		if(!Validador.isStringValida(crc))
		{
			setCrc(STRING_VAZIA);
		}
		return crc;
	}

	/**
	 * Atribui o Status da Inscrição Estadual
	 * @param statInscricaoEstadual
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setStatInscricaoEstadual(String statInscricaoEstadual)
	{
		this.statInscricaoEstadual = statInscricaoEstadual;
	}

	/**
	 * Retorna o Status da Inscrição Estadual
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getStatInscricaoEstadual()
	{
		if (!Validador.isStringValida(statInscricaoEstadual))
		{
			setStatInscricaoEstadual(STRING_VAZIA);
		}
		return statInscricaoEstadual;
	}

	/**
	 * Atribui o Código de Endereço
	 * @param codgEndereco
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setCodgEndereco(long codgEndereco)
	{
		this.codgEndereco = codgEndereco;
	}

	/**
	 * Retorna o Código de Endereço
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public long getCodgEndereco()
	{
		return codgEndereco;
	}

	/**
	 * Atribui o Código de Regime de Pagamento
	 * @param codgRegimePagamento
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setCodgRegimePagamento(int codgRegimePagamento)
	{
		this.codgRegimePagamento = codgRegimePagamento;
	}

	/**
	 * Retorna o Código de Regime de Pagamento
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int getCodgRegimePagamento()
	{
		return codgRegimePagamento;
	}

	/**
	 * Atribui a Descrição do Regime de Casamento
	 * @param descRegimePagamento
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setDescRegimePagamento(String descRegimePagamento)
	{
		this.descRegimePagamento = descRegimePagamento;
	}

	/**
	 * Retorna a Descrição do Regime de Casamento
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getDescRegimePagamento()
	{
		return descRegimePagamento;
	}

	/**
	 * Atribui o Quadro Societario
	 * @param quadroSocietario
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setQuadroSocietario(Collection quadroSocietario)
	{
		this.quadroSocietario = quadroSocietario;
	}

	/**
	 * Retorna o Quadro Societario
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public Collection getQuadroSocietario()
	{
		return quadroSocietario;
	}

	/**
	 * Atribui o Nome Fantasia
	 * @param nomeFantasia
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setNomeFantasia(String nomeFantasia)
	{
		this.nomeFantasia = nomeFantasia;
	}

	/**
	 * Retorna o Nome Fantasia
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getNomeFantasia()
	{
		return nomeFantasia;
	}

	/**
	 * Atribui o Numero da Inscrição UF
	 * @param numrInscricaoUF
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setNumrInscricaoUF(String numrInscricaoUF)
	{
		this.numrInscricaoUF = numrInscricaoUF;
	}

	/**
	 * Retorna o Número da Inscrição UF
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getNumrInscricaoUF()
	{
		return numrInscricaoUF;
	}

	/**
	 * Atribui o Tipo de CPF
	 * @param tipoCpf
	 * @implemented by Rogério Sanches de Oliveira
	 */
/*	public void setTipoCpf(DomnTipoCpf tipoCpf)
	{
		this.tipoCpf = tipoCpf;
	}*/

	/**
	 * Retorna o Tipo de CPF
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
/*	public DomnTipoCpf getTipoCpf()
	{
		return tipoCpf;
	}*/

	/**
	 * Atribui o Tipo de Pessoa
	 * @param tipoPessoa
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setTipoPessoa(DomnTipoPess tipoPessoa)
	{
		this.tipoPessoa = tipoPessoa;
	}

	/**
	 * Retorna o Tipo de Pessoa
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public DomnTipoPess getTipoPessoa()
	{
		return tipoPessoa;
	}

	/**
	 * Atribui a Data de Atualização
	 * @param dataAtualizacao
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setDataAtualizacao(Date dataAtualizacao)
	{
		this.dataAtualizacao = dataAtualizacao;
	}

	/**
	 * Retorna a Data de Atualização
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public Date getDataAtualizacao()
	{
		return dataAtualizacao;
	}

	/**
	 * Atribui o Código da Natureza Juridica
	 * @param codgNaturezaJuridica
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setCodgNaturezaJuridica(Integer codgNaturezaJuridica)
	{
		this.codgNaturezaJuridica = codgNaturezaJuridica;
	}

	/**
	 * Retorna o Código da Natureza Juridica
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public Integer getCodgNaturezaJuridica()
	{
		return codgNaturezaJuridica;
	}

	/**
	 * Atribui a Data de ATLZ
	 * @param dataAtlz
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setDataAtlz(Date dataAtlz)
	{
		this.dataAtlz = dataAtlz;
	}

	/**
	 * Retorna a Data de ATLZ
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public Date getDataAtlz()
	{
		return dataAtlz;
	}

	/**
	 * Atribui o Tipo de Contribuinte
	 * @param tipoContribuinte
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setTipoContribuinte(Integer tipoContribuinte)
	{
		this.tipoContribuinte = tipoContribuinte;
	}

	/**
	 * Retorna o Tipo de Contribuinte
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public Integer getTipoContribuinte()
	{
		return tipoContribuinte;
	}

	/**
	 * Atribui o Código de Atividae Economica
	 * @param codgAtividadeEconomica
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setCodgAtividadeEconomica(int codgAtividadeEconomica)
	{
		this.codgAtividadeEconomica = codgAtividadeEconomica;
	}

	/**
	 * Retorna o Código de Atividade Economica
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public int getCodgAtividadeEconomica()
	{
		return codgAtividadeEconomica;
	}

	/**
	 * Atribui a CNAE Primária
	 * @param cnaePrimaria
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setCnaePrimaria(CnaeVO cnaePrimaria)
	{
		this.cnaePrimaria = cnaePrimaria;
	}

	/**
	 * Retorna a CNAE Primária
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public CnaeVO getCnaePrimaria()
	{
		return cnaePrimaria;
	}

	/**
	 * ATribui o Código de Municipio
	 * @param codgMunicipio
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setCodgMunicipio(Integer codgMunicipio)
	{
		this.codgMunicipio = codgMunicipio;
	}

	/**
	 * Retorna o Código de Municipio
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public Integer getCodgMunicipio()
	{
		return codgMunicipio;
	}

	/**
	 * Atribui a CNAE de Serviço
	 * @param cnaeServico
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setCnaeServico(boolean cnaeServico)
	{
		this.cnaeServico = cnaeServico;
	}

	/**
	 * Retorna se é CNAE de Serviço
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public boolean isCnaeServico()
	{
		return cnaeServico;
	}

	/**
	 * Atribui o Número do DDD
	 * @param numrDdd
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setNumrDdd(Integer numrDdd)
	{
		this.numrDdd = numrDdd;
	}

	/**
	 * Retorna o Número do DDD
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public Integer getNumrDdd()
	{
		if (!Validador.isNumericoValido(numrDdd))
		{
			setNumrDdd(new Integer(0));
		}
		return numrDdd;
		
	}
	
	public String getNumrDddFormatado()
	{
		if(!Validador.isNumericoValido(getNumrDdd()))
		{
			return STRING_VAZIA;
		}
		return getNumrDdd().toString();
	}

	/**
	 * Atribui o Ponto de Referencia
	 * @param pontoReferencia
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public void setPontoReferencia(String pontoReferencia)
	{
		this.pontoReferencia = pontoReferencia;
	}

	/**
	 * Retorna o Ponto de Rerefencia
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public String getPontoReferencia()
	{
		if(!Validador.isStringValida(pontoReferencia))
		{
			setPontoReferencia(STRING_VAZIA);
		}
		return pontoReferencia;
	}
	//Fim dos métodos do ContribuinteVo

	/**
	 * 
	 * Inner class criado para poder utilizar a classe serializada.
	 * 
	 * @author Thiago de Castilho Pacheco
	 * @version $Revision: 1.4 $
	 */
	public class CnaeVO implements Serializable
	{
		private Integer codgCnae;
		private String descCnae;
		private DomnTipoInfoCnae tipoInfoCnae;
		private static final long serialVersionUID = Long.MAX_VALUE;

		/**
		 * Construtor Padrão
		 * @implemented by Thiago de Castilho Pacheco
		 */
		public CnaeVO()
		{
		}

		/**
		 * Atribui o Código do CNAE
		 * @param codgCnae
		 * @implemented by Thiago de Castilho Pacheco
		 */
		public void setCodgCnae(Integer codgCnae)
		{
			this.codgCnae = codgCnae;
		}

		/**
		 * Retorna o Código do CNAE
		 * @return
		 * @implemented by Thiago de Castilho Pacheco
		 */
		public Integer getCodgCnae()
		{
			return codgCnae;
		}

		/**
		 * Atribui a Descrição do CNAE
		 * @param descCnae
		 * @implemented by Thiago de Castilho Pacheco
		 */
		public void setDescCnae(String descCnae)
		{
			this.descCnae = descCnae;
		}

		/**
		 * Retorna a Descrição do CNAE
		 * @return
		 * @implemented by Thiago de Castilho Pacheco
		 */
		public String getDescCnae()
		{
			return descCnae;
		}
	}
	
	public Collection getColecaoTipoDocumento()
	{
		if(!Validador.isCollectionValida(colecaoTipoDocumento))
		{
			return new ArrayList();
		}
		return colecaoTipoDocumento;
	}
	
	public void setColecaoTipoDocumento(Collection colecaoDocumentos)
	{
		this.colecaoTipoDocumento = colecaoDocumentos;
	}
	
	public String getCEPFormatado()
	{
		if(!Validador.isNumericoValido(getEnderecoCEP()))
		{
			return STRING_VAZIA;
		}
		return StringUtil.formataTexto(String.valueOf(getEnderecoCEP()),"99.999-999");
	}
	
	public String getNumrTelefoneFormatado()
	{
	   String telefoneFormatado = new String();
		if(Validador.isNumericoValido(getNumrTelefone()))
		{
		   telefoneFormatado = String.valueOf(getNumrTelefone());
		}	   
	   if(Validador.isStringValida(telefoneFormatado))
	   {
			
	      if(telefoneFormatado.length() == 8)
	      {
	         telefoneFormatado = StringUtil.formataTexto(telefoneFormatado, "9999-9999");
	      }
	      else
	      {
	         telefoneFormatado = StringUtil.formataTexto(telefoneFormatado, "999-9999");
	      }
	   }
	   return telefoneFormatado;
	}
	
	
}
