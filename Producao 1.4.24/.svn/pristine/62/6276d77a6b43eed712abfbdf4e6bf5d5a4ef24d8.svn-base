/*
* Secretaria de Estado de Fazenda de Mato Grosso ï¿½ Sefaz-MT
* Arquivo : MunicipioIntegracaoVo.java
* Criaï¿½ï¿½o : Outubro de 2007
* Revisão :
* Log :
* $Id: MunicipioIntegracaoVo.java,v 1.3 2008/07/03 20:25:19 lucas.nascimento Exp $
*/
package br.gov.mt.sefaz.itc.util.integracao.cadastro;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.integracao.contacorrente.ContaCorrenteIntegracaoVo;

import java.util.Collection;

import sefaz.mt.cadastro.integracao.MunicipioVO;


/**
 * Classe de integração com o sistema de Cadastro de Contribuinte para os métodos de
 * endereco/municipio.
 *
 * Adicionados atributos e métodos da classe pai, para que possa ser serializado.
 *
 * @author Thiago de Castilho Pacheco
 * @author Leandro Dorileo
 * @version $Revision: 1.3 $
 */
public class MunicipioIntegracaoVo implements EntidadeFacade, VoIntegrador
{
	private Collection collVO;
	private boolean consultaCompleta;
	private EntidadeFacade parametroConsulta;
	private long usuarioLogado;
	private String mensagem;
	private String nomeSistema;
	private static final long serialVersionUID = Long.MAX_VALUE;
	//Atributos do MunicipioVO
	private Integer codgMunicipio;
	private String nomeMunicipio;
	private Integer codgMunicipioSede;
	private Integer codgUnidadeFazendaria;

	/**
	 * Construtor pï¿½blico padrï¿½o(sem parametros)
	 */
	public MunicipioIntegracaoVo()
	{
		setNomeSistema(EntidadeVo.NOME_SISTEMA);
	}

	/**
	 * Construtor pï¿½blico padrï¿½o(sem parametros)
	 */
	public MunicipioIntegracaoVo(MunicipioVO municipio)
	{
		this();
		this.codgMunicipio = municipio.getCodgMunicipio();
		this.nomeMunicipio = municipio.getNomeMunicipio();
		this.codgMunicipioSede = municipio.getCodgMunicipioSede();
 	}

	public int compare(Object t, Object t1)
	{
		return ((MunicipioIntegracaoVo) t).getCodgMunicipio().compareTo(((MunicipioIntegracaoVo) t1).getCodgMunicipio());
	}

	public int compareTo(Object municipioVo)
	{
		return this.getCodgMunicipio().compareTo(((MunicipioIntegracaoVo) municipioVo).getCodgMunicipio());
	}

	/**
	 * Configura o valor do atributo <code>nomeSistema</code> com o valor do parametro
	 * <code>nomeSistema</code>
	 *
	 * @param nomeSistema       Nome a ser configurado como do sistema
	 * @implememented by Leandro Dorileo
	 */
	public void setNomeSistema(String nomeSistema) throws ProibidoMudarNomeSistemaException
	{
		if (Validador.isStringValida(nomeSistema) && nomeSistema.equals(EntidadeVo.NOME_SISTEMA))
		{
			this.nomeSistema = nomeSistema;
		}
		else
			throw new ProibidoMudarNomeSistemaException();
	}

	/**
	 * Retorna apropriadamente o nome do sistema
	 * @return      nome do sistema
	 * @implemented by Leandro Dorileo
	 */
	public String getNomeSistema()
	{
		return (Validador.isStringValida(this.nomeSistema)) ? this.nomeSistema : STRING_VAZIA;
	}

	/**
	 * Configura apropriadamente o atributo <code>collVO</code> usando o parametro
	 * <code>collVO</code>.
	 * @param collVO        collecao de VO`s a ser configurada como collVO do VO
	 * @implemented by Leandro Dorileo
	 */
	public void setCollVO(Collection collVO)
	{
		this.collVO = collVO;
	}

	/**
	 * Caso não tenha sido criado um collVO e se precise de um antes de manipula-lo
	 * criamos-o aqui.
	 * @return      just created and set collVO to collVO attribute
	 * @implemented by Leandro Dorileo
	 */
	private Collection createCollVO()
	{
		this.setCollVO(collVO);
		return this.collVO;
	}

	/**
	 * Retorna a collVO do VO em questï¿½o
	 * @return  collVO do VO em questï¿½o
	 */
	public Collection getCollVO()
	{
		return (Validador.isCollectionValida(this.collVO)) ? this.collVO : createCollVO();
	}

	public boolean isExiste()
	{
		return !this.equals(new ContaCorrenteIntegracaoVo());
	}

	/**
	 * Verifica se a collVO existe e se é vï¿½lida
	 * @return      true caso a <code>collVO</code> seja vï¿½lida e false caso contrï¿½rio
	 */
	public boolean isExisteCollVO()
	{
		return Validador.isCollectionValida(this.collVO);
	}

	/**
	 * Verifica se a consulta ï¿½ parametrizada ou não. A consulta pode ser parametrizada
	 * ou completa, portanto, pode ser necessï¿½rio verificar o tipo da consulta.
	 *
	 * @return      true caso <code>parametroConsulta</code> seja um objeto vï¿½lido e false caso contrï¿½rio
	 */
	public boolean isConsultaParametrizada()
	{
		return (this.getParametroConsulta() != null);
	}

	/**
	 * Verifica se a consulta ï¿½ completa ou não.
	 *
	 * @return     estado de <code>consultaCompleta</code>
	 */
	public boolean isConsultaCompleta()
	{
		return consultaCompleta;
	}

	/**
	 * Configura o atributo <code>consultaCompleta</code> com o valor do parametro
	 * <code>consultaCompleta</code>.
	 *
	 * @param consultaCompleta    valor a ser configurado no atributo <code>consultaCompleta</code>
	 */
	public void setConsultaCompleta(boolean consultaCompleta)
	{
		this.consultaCompleta = consultaCompleta;
	}

	/**
	 * Configura o atributo <code>usuarioLogado</code> com o valor do parametro
	 * <code>usuarioLogado</code>.
	 *
	 * @param usuarioLogado    valor a ser configurado no atributo <code>usuarioLogado</code>
	 */
	public void setUsuarioLogado(long usuarioLogado)
	{
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * Retorna o valor configurado no atributo <code>usuarioLogado</code>
	 *
	 * @return     valor configurado no atributo <code>usuarioLogado</code>
	 */
	public long getUsuarioLogado()
	{
		return this.usuarioLogado;
	}

	/**
	 * Configura o valor do atributo <code>mensagem</code> com o valor do parâmetro
	 * <code>mensagem</code>
	 *
	 * @param mensagem   String contendo a mensagem a ser configurada
	 */
	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	/**
	 * Retorna o valor configurado no atributo <code>mensagem</code>
	 *
	 * @return  valor configurado no atributo <code>mensagem</code>
	 */
	public String getMensagem()
	{
		return this.mensagem;
	}

	/**
	 * Configura o valor do atributo <code>parametro</code>  com o valor do parâmetro
	 * <code>parametroConsulta</code>
	 *
	 * @param parametroConsulta   instancia de <code>EntidadeFacade</code> a ser configurada
	 */
	public void setParametroConsulta(EntidadeFacade parametroConsulta)
	{
		this.parametroConsulta = parametroConsulta;
	}

	/**
	 * Retorna o valor configurado no atributo <code>parametroConsulta</code>
	 *
	 * @return     valor configurado no atributo <code>parametroConsulta</code>
	 */
	public EntidadeFacade getParametroConsulta()
	{
		return parametroConsulta;
	}
	//Métodos do MunicipioVO

	public void setCodgMunicipio(Integer codgMunicipio)
	{
		this.codgMunicipio = codgMunicipio;
	}

	public Integer getCodgMunicipio()
	{
		return codgMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio)
	{
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getNomeMunicipio()
	{
		return nomeMunicipio;
	}

	public void setCodgMunicipioSede(Integer codgMunicipioSede)
	{
		this.codgMunicipioSede = codgMunicipioSede;
	}

	public Integer getCodgMunicipioSede()
	{
	   return codgMunicipioSede;
	}

	public void setCodgUnidadeFazendaria(Integer codgUnidadeFazendaria)
	{
		this.codgUnidadeFazendaria = codgUnidadeFazendaria;
	}

	public Integer getCodgUnidadeFazendaria()
	{
		return codgUnidadeFazendaria;
	}
}
