/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CulturaPesquisar.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 03/11/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.cultura;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;
import br.com.abaco.util.http.JspUtil;

import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.Form;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sefaz.mt.util.IncluiException;


/**
 *
 * @author Elizabeth Barbosa Moreira
 * @version $Revision: 1.2 $
 */
public class CulturaPesquisar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_LISTAR_CULTURA = 5;
	private static final int REQUISICAO_LISTAR_CULTURA_DESCRICAO = 4;
	private static final int REQUISICAO_LISTAR_CULTURA_CODIGO = 3;
	private static final int REQUISICAO_CONSULTAR_CULTURA = 2;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws IntegracaoException Esta exceção deve ser lançada quando o sistema tenta realizar integração com um sistema externo e a integração causa uma Exception.
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, IncluiException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ParametroObrigatorioException, ConexaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarListarCultura(request, response);
					break;
				}
			case REQUISICAO_LISTAR_CULTURA_CODIGO:
				{
					listarCulturaCodigo(request, response);
					break;
				}
			case REQUISICAO_LISTAR_CULTURA_DESCRICAO:
				{
					listarCulturaDescricao(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_CULTURA:
				{
					consultarCultura(request, response);
					break;
				}
			case REQUISICAO_LISTAR_CULTURA:
				{
					solicitarGIAListarCultura(request, response);
					break;
				}
		}
	}

	/**
	 *  Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR)))
		{
			if (Validador.isStringValida(request.getParameter(CAMPO_CODIGO_CULTURA)))
			{
				return REQUISICAO_LISTAR_CULTURA_CODIGO;
			}
			if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_CULTURA)))
			{
				return REQUISICAO_LISTAR_CULTURA_DESCRICAO;
			}
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_CODIGO_CULTURA)))
		{
			return REQUISICAO_CONSULTAR_CULTURA;
		}
		if (request.getAttribute(LISTA_CULTURA) != null)
		{
			return REQUISICAO_LISTAR_CULTURA;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por redirecionar para a JSP de consultar cultura
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private void solicitarListarCultura(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(CULTURA_VO, new CulturaVo(), request);
		processFlow(VIEW_PESQUISAR_CULTURA, request, response, FORWARD);
	}

	/**
	 * Método responsável por listar Culturas.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws IntegracaoException Esta exceção deve ser lançada quando o sistema tenta realizar integração com um sistema externo e a integração causa uma Exception.
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private void listarCulturaDescricao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  ParametroObrigatorioException, ConexaoException
	{
		CulturaVo culturaVo = controladorInterativoJSP(request);
		culturaVo = new CulturaVo(culturaVo);
		CulturaBe culturaBe = null;
		try
		{
			culturaBe = new CulturaBe();
			culturaBe.listarCultura(culturaVo);
			Validador.validaObjeto(culturaVo);
			if (!Validador.isCollectionValida(culturaVo.getCollVO()))
			{
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_CULTURA_LISTAR_DESCRICAO));
				request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_CULTURA);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (culturaBe != null)
			{
				culturaBe.close();
				culturaBe = null;
			}
		}
		request.setAttribute(CULTURA_VO, culturaVo);
		processFlow(VIEW_PESQUISAR_CULTURA, request, response, FORWARD);
	}

	/**
	 * Método responsável por listar as culturas por código.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void listarCulturaCodigo(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, ParametroObrigatorioException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConexaoException
	{
		CulturaVo culturaVo = controladorInterativoJSP(request);
		culturaVo = new CulturaVo(culturaVo);
		CulturaBe culturaBe = null;
		try
		{
			culturaBe = new CulturaBe();
			culturaBe.listarCultura(culturaVo);
			Validador.validaObjeto(culturaVo);
			if (!Validador.isCollectionValida(culturaVo.getCollVO()))
			{
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_CULTURA_LISTAR_CODIGO));
				request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_CODIGO_CULTURA);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (culturaBe != null)
			{
				culturaBe.close();
				culturaBe = null;
			}
		}
		request.setAttribute(CULTURA_VO, culturaVo);
		processFlow(VIEW_PESQUISAR_CULTURA, request, response, FORWARD);
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	private CulturaVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		CulturaVo culturaVo = (CulturaVo) getBuffer(request).getAttribute(CULTURA_VO);
		Validador.validaObjeto(culturaVo);
		if (Validador.isStringValida(request.getParameter(CAMPO_CODIGO_CULTURA)))
		{
			culturaVo.setCodigo(StringUtil.toLong(request.getParameter(CAMPO_CODIGO_CULTURA)));
		}
		else if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_CULTURA)))
		{
			culturaVo.setDescricaoCultura(request.getParameter(CAMPO_DESCRICAO_CULTURA));
		}
		return culturaVo;
	}

	/**
	 * Este método cria um CulturaVo e atribui os atributos <i>descricaoTipoCultura</i> e <i>classificacaoTipoCultura</i> de acordo com os campos 
	 * <i>CAMPO_DESCRICAO_Cultura</i> e <i>CAMPO_SELECT_TIPO_Cultura</i> originários da requisição.
	 * Caso contrário, é criada uma nova instância do CulturaVo para atender a necessidade do fluxo.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private void consultarCultura(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, ParametroObrigatorioException, ConexaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		CulturaVo culturaVo = controladorInterativoJSP(request);
		culturaVo.setParametroConsulta(culturaVo);
		CulturaBe culturaBe = null;
		try
		{
			culturaBe = new CulturaBe();
			culturaVo = culturaBe.consultarCultura(culturaVo);
			Validador.validaObjeto(culturaVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (culturaBe != null)
			{
				culturaBe.close();
				culturaBe = null;
			}
		}
		request.setAttribute(CULTURA_VO, culturaVo);
		processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);
	}

	/**
	 *  Método responsável por listar as Cultura cadastradas, será usado através de outra servlet que precise buscar
	 *  as culturas existentes no banco de dados
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarGIAListarCultura(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, ParametroObrigatorioException, ConexaoException
	{
		CulturaVo culturaVo = (CulturaVo) getBuffer(request).getAttribute(LISTA_CULTURA);
		if (!Validador.isObjetoValido(culturaVo))
		{
			culturaVo = new CulturaVo();
		}
		culturaVo.setConsultaCompleta(false);
		CulturaBe culturaBe = null;
		try
		{
			culturaBe = new CulturaBe();
			culturaBe.listarCultura(culturaVo);
			Validador.validaObjeto(culturaVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (culturaBe != null)
			{
				culturaBe.close();
				culturaBe = null;
			}
		}
		getBuffer(request).setAttribute(LISTA_CULTURA, culturaVo, request);
	}
}
