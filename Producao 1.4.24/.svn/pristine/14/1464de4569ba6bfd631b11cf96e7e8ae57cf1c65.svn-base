package br.gov.mt.sefaz.itc.util.integracao.acessoweb;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import java.util.ArrayList;
import java.util.Collection;

import sefaz.mt.acessoweb.integracao.UsuarioVO;


public class UsuarioIntegracaoVo implements EntidadeFacade, VoIntegrador
{

	private static final String NOME_SISTEMA = "ITCD";
	private boolean consultaCompleta;
	private Collection collVO;
	private EntidadeFacade parametroConsulta;	
	private long usuarioLogado;
	private String mensagem;
	private String nomeSistema;
	private String titulo;
	
	//Atributos de UsuarioVo
	 private Integer codigo;
	 private String identificacao;
	 private String nome;
	 private String email;
	 private int codgTipoUsuario;
	 private int codgTipoOutroUsuario;
	 private String descTipoOutroUsuario; 
	
	public UsuarioIntegracaoVo()
	{
	}

	public UsuarioIntegracaoVo(Integer codg)
	{
		setCodigo(codg);
	}
	
	public UsuarioIntegracaoVo(UsuarioVO usuario) throws ParametroObrigatorioException
	{
		super();
		if (usuario != null)
		{
			if (Validador.isStringValida(usuario.getIdentificacao()))
			{
				setIdentificacao(usuario.getIdentificacao());
			}
			else
			{
				throw new ParametroObrigatorioException(A_INTEGRACAO_NAO_RETORNOU + "o identificação do usuário" + 
						QUE_E_OBRIGATORIO);
			}
			if (Validador.isStringValida(usuario.getNome()))
			{
				setNome(usuario.getNome());
			}
			else
			{
				setNome(STRING_VAZIA);
			}
			if (Validador.isNumericoValido(usuario.getCodgTipoUsuario()))
			{
				setCodgTipoUsuario(usuario.getCodgTipoUsuario());
			}
			if (Validador.isStringValida(usuario.getEmail()))
			{
				setEmail(usuario.getEmail());
			}
		   if (Validador.isStringValida(usuario.getDescTipoOutroUsuario()))
		   {
		      setDescTipoOutroUsuario(usuario.getDescTipoOutroUsuario());
		   }		
			if(Validador.isNumericoValido(usuario.getCodigo()))
			{
				setCodigo(usuario.getCodigo());
			}
		   if(Validador.isNumericoValido(usuario.getCodgTipoOutroUsuario()))
		   {
		      setCodgTipoOutroUsuario(usuario.getCodgTipoOutroUsuario());
		   }			
		}
	}
	
	public String toString()
	{
		return String.valueOf(getCodigo());
	}
	
	
	public void setNomeSistema(String nomeSistema)
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
	
	public void setCollVO(Collection collVO)
	{
		this.collVO = collVO;
	}

	public Collection getCollVO()
	{
		if (collVO!=null)
		{
			return collVO;
		}
		else
		{
			setCollVO(new ArrayList());
			return getCollVO();
		}
	}

	public boolean isExiste()
	{
		return !this.equals(new UsuarioIntegracaoVo());
	}

	public boolean isExisteCollVO()
	{
		return Validador.isCollectionValida(getCollVO());
	}

	public boolean isConsultaCompleta()
	{
		return consultaCompleta;
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
	
	public UsuarioIntegracaoVo getParametroConsulta()
	{
		return (UsuarioIntegracaoVo) parametroConsulta;
	}

	public void setParametroConsulta(EntidadeFacade parametroConsulta)
	{
		this.parametroConsulta = parametroConsulta;
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

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public int compare(Object segmentoVo1, Object segmentoVo2)
	{
		return ((UsuarioVO) segmentoVo1).getCodigo().compareTo(((UsuarioVO) segmentoVo2).getCodigo());
	}

	public int compareTo(Object usuarioVo)
	{
		return this.getCodigo().compareTo(((UsuarioVO) usuarioVo).getCodigo());
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
		hashAtual += getCodigo().intValue();
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

	public void setCodigo(Integer codigo)
	{
		this.codigo = codigo;
	}

	public Integer getCodigo()
	{
		return codigo;
	}

	public void setIdentificacao(String identificacao)
	{
		this.identificacao = identificacao;
	}

	public String getIdentificacao()
	{
		return identificacao;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getEmail()
	{
		return email;
	}

	public void setCodgTipoUsuario(int codgTipoUsuario)
	{
		this.codgTipoUsuario = codgTipoUsuario;
	}

	public int getCodgTipoUsuario()
	{
		return codgTipoUsuario;
	}

	public void setCodgTipoOutroUsuario(int codgTipoOutroUsuario)
	{
		this.codgTipoOutroUsuario = codgTipoOutroUsuario;
	}

	public int getCodgTipoOutroUsuario()
	{
		return codgTipoOutroUsuario;
	}

	public void setDescTipoOutroUsuario(String descTipoOutroUsuario)
	{
		this.descTipoOutroUsuario = descTipoOutroUsuario;
	}

	public String getDescTipoOutroUsuario()
	{
		return descTipoOutroUsuario;
	}
}
