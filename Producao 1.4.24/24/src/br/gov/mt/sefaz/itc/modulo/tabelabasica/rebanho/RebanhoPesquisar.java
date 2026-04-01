/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: RebanhoPesquisar.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 03/11/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.rebanho;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.JspUtil;

import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.http.AbstractServletITCD;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RebanhoPesquisar extends AbstractServletITCD implements Flow, Form
{
	private static final int REQUISICAO_CONSULTAR_REBANHO = 4;
	private static final int REQUISICAO_LISTAR_REBANHO_CODIGO = 2;
	private static final int REQUISICAO_LISTAR_REBANHO_DESCRICAO = 3;
	private static final int REQUISICAO_LISTAR_REBANHO_RETORNAR_SERVLET_PAI =5;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws IntegracaoException Esta exceção deve ser lançada quando o sistema tenta realizar integração com um sistema externo e a integração causa uma Exception.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, IntegracaoException, 
			  ConexaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarListarRebanho(request, response);
					break;
				}
			case REQUISICAO_LISTAR_REBANHO_CODIGO:
				{
					listarRebanhoCodigo(request, response);
					break;
				}
			case REQUISICAO_LISTAR_REBANHO_DESCRICAO:
				{
					listarRebanhoDescricao(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_REBANHO:
				{
					consultarRebanho(request, response);
					break;
				}
			case REQUISICAO_LISTAR_REBANHO_RETORNAR_SERVLET_PAI:
				{
					listarRebanhoGIA(request, response);
					break;
				}
		}
	}

	/**
	 *  Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR)))
		{
			if (Validador.isStringValida(request.getParameter(CAMPO_CODIGO_REBANHO)))
			{
				return REQUISICAO_LISTAR_REBANHO_CODIGO;
			}
			if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_REBANHO)))
			{
				return REQUISICAO_LISTAR_REBANHO_DESCRICAO;
			}
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_REBANHO)))
		{
			return REQUISICAO_CONSULTAR_REBANHO;
		}
		if(Validador.isObjetoValido(request.getAttribute(LISTA_REBANHO)))
		{
			return REQUISICAO_LISTAR_REBANHO_RETORNAR_SERVLET_PAI;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por redirecionar para a JSP de consultar rebanho
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarListarRebanho(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(REBANHO_VO, new RebanhoVo(), request);
		processFlow(VIEW_PESQUISAR_REBANHO, request, response, FORWARD);
	}

	/**
	 * Método responsável por listar rebanho por descrição.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void listarRebanhoDescricao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		RebanhoVo rebanhoVo = controladorInterativoJSP(request);
		rebanhoVo = new RebanhoVo(rebanhoVo);
		RebanhoBe rebanhoBe = null;
		try
		{
			rebanhoBe = new RebanhoBe();
			rebanhoBe.listarRebanho(rebanhoVo);
			Validador.validaObjeto(rebanhoVo);
			if (!Validador.isCollectionValida(rebanhoVo.getCollVO()))
			{
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_REBANHO_LISTAR_DESCRICAO));
				request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_REBANHO);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (rebanhoBe != null)
			{
				rebanhoBe.close();
				rebanhoBe = null;
			}
		}
		request.setAttribute(REBANHO_VO, rebanhoVo);
		processFlow(VIEW_PESQUISAR_REBANHO, request, response, FORWARD);
	}

	/**
	 * Método responsável por listar rebanho por código.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void listarRebanhoCodigo(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		RebanhoVo rebanhoVo = controladorInterativoJSP(request);
		RebanhoBe rebanhoBe = null;
		try
		{
			rebanhoBe = new RebanhoBe();
			rebanhoBe.listarRebanho(rebanhoVo);
			if (!Validador.isCollectionValida(rebanhoVo.getCollVO()))
			{
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_REBANHO_LISTAR_CODIGO));
				request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_CODIGO_REBANHO);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (rebanhoBe != null)
			{
				rebanhoBe.close();
				rebanhoBe = null;
			}
		}
		request.setAttribute(REBANHO_VO, rebanhoVo);
		processFlow(VIEW_PESQUISAR_REBANHO, request, response, FORWARD);
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private RebanhoVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		RebanhoVo rebanhoVo = (RebanhoVo) getObjetoRequest(request, REBANHO_VO);
		Validador.validaObjeto(rebanhoVo);
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_REBANHO)))
		{
			rebanhoVo = new RebanhoVo(StringUtil.toLong(request.getParameter(PARAMETRO_CODIGO_REBANHO)));
		}
		else
		{
			if (Validador.isStringValida(request.getParameter(CAMPO_CODIGO_REBANHO)))
			{
				rebanhoVo.setCodigo(StringUtil.toLong(request.getParameter(CAMPO_CODIGO_REBANHO)));
			}
			else if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_REBANHO)))
			{
				rebanhoVo.setDescricaoRebanho(request.getParameter(CAMPO_DESCRICAO_REBANHO));
			}
		}
		return rebanhoVo;
	}

	/**
	 * Método responsável por tratar requisição de consultarRebanho.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void consultarRebanho(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  ConexaoException
	{
		RebanhoVo rebanhoVo = controladorInterativoJSP(request);
		rebanhoVo.setParametroConsulta(rebanhoVo);
		RebanhoBe rebanhoBe = null;
		try
		{
			rebanhoBe = new RebanhoBe();
			rebanhoVo = rebanhoBe.consultarRebanho(rebanhoVo);
		   request.setAttribute(REBANHO_VO, rebanhoVo);
		   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(REBANHO_VO, rebanhoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_PESQUISAR_REBANHO, request, response, FORWARD);
		}
		finally
		{
			if (rebanhoBe != null)
			{
				rebanhoBe.close();
				rebanhoBe = null;
			}
		}
	}

	private void listarRebanhoGIA(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException
	{
		RebanhoVo rebanhoVo = (RebanhoVo) getBuffer(request).getAttribute(LISTA_REBANHO);
		if(!Validador.isObjetoValido(rebanhoVo))
		{
			rebanhoVo = new RebanhoVo();
		}
		rebanhoVo.setConsultaCompleta(true);
		RebanhoBe rebanhoBe = null;
		try
		{
			rebanhoBe = new RebanhoBe();
			rebanhoVo = rebanhoBe.listarRebanho(rebanhoVo);
			getBuffer(request).setAttribute(LISTA_REBANHO, rebanhoVo, request);
		}
		catch(SQLException e)
		{
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if(rebanhoBe != null)
			{
				rebanhoBe.close();
				rebanhoBe = null;
			}
		}
		
	}
}
