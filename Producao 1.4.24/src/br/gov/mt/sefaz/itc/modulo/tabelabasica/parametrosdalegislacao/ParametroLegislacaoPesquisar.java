package br.gov.mt.sefaz.itc.modulo.tabelabasica.parametrosdalegislacao;

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

import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.ParametroLegislacaoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.ParametroLegislacaoVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet de pequisa de Paramaetro da Legislação
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.1.1.1 $
 */
public class ParametroLegislacaoPesquisar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_CONSULTAR_PESQUISAR_PARAMETRO_LEGISLACAO = 2;
	private static final int REQUISICAO_CONSULTAR_ALTERAR_PARAMETRO_LEGISLACAO = 3;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request
	 * @param response
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, ConexaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, 
			  ParametroObrigatorioException, IntegracaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					listarParametroLegislacao(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_PESQUISAR_PARAMETRO_LEGISLACAO:
				{
					consultarPesquisarParametroLegislacao(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_ALTERAR_PARAMETRO_LEGISLACAO:
				{
					consultarAlterarParametroLegislacao(request, response);
					break;
				}
		}
	}

	/**
	 *  Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_PARAMETRO_LEGISLACAO_PESQUISAR)))
		{
			return REQUISICAO_CONSULTAR_PESQUISAR_PARAMETRO_LEGISLACAO;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_PARAMETRO_LEGISLACAO_ALTERAR)))
		{
			return REQUISICAO_CONSULTAR_ALTERAR_PARAMETRO_LEGISLACAO;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private ParametroLegislacaoVo controladorInterativoJSP(HttpServletRequest request)
	{
		return new ParametroLegislacaoVo();
	}

	/**
	 * Método responsável por consultar parametro de legislação
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void consultarPesquisarParametroLegislacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  IntegracaoException
	{
		ParametroLegislacaoVo parametroLegislacaoVo = 
				 new ParametroLegislacaoVo(new ParametroLegislacaoVo(StringUtil.toLong(request.getParameter(PARAMETRO_CODIGO_PARAMETRO_LEGISLACAO_PESQUISAR))));
		parametroLegislacaoVo.setConsultaCompleta(true);
		ParametroLegislacaoBe parametroLegislacaoBe = null;
		try
		{
			parametroLegislacaoBe = new ParametroLegislacaoBe();
			parametroLegislacaoVo = parametroLegislacaoBe.consultarParametroLegislacao(parametroLegislacaoVo);
			Validador.validaObjeto(parametroLegislacaoVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			e.printStackTrace();
			getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
		}
		finally
		{
			if (parametroLegislacaoBe != null)
			{
				parametroLegislacaoBe.close();
				parametroLegislacaoBe = null;
			}
		}
		request.setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo);
		processFlow(VIEW_PESQUISAR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
	}

	/**
	 * Método responsável por consultar parametro de legislação pela funcionalidade de alterar
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void consultarAlterarParametroLegislacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  IntegracaoException
	{
		ParametroLegislacaoVo parametroLegislacaoVo = 
				 new ParametroLegislacaoVo(StringUtil.toLong(request.getParameter(PARAMETRO_CODIGO_PARAMETRO_LEGISLACAO_ALTERAR)));
		parametroLegislacaoVo = new ParametroLegislacaoVo(parametroLegislacaoVo);
		parametroLegislacaoVo.setConsultaCompleta(true);
		ParametroLegislacaoBe parametroLegislacaoBe = null;
		try
		{
			parametroLegislacaoBe = new ParametroLegislacaoBe();
			parametroLegislacaoBe.consultarParametroLegislacao(parametroLegislacaoVo);
			Validador.validaObjeto(parametroLegislacaoVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			e.printStackTrace();
			getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
		}
		finally
		{
			if (parametroLegislacaoBe != null)
			{
				parametroLegislacaoBe.close();
				parametroLegislacaoBe = null;
			}
		}
		getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
		processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);
	}

	/**
	 * Método responsável por listar o Parametro da Legislação
	 * @param request
	 * @param response
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void listarParametroLegislacao(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, ConexaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, 
			  ParametroObrigatorioException, IntegracaoException
	{
		removeBuffer(request);
		ParametroLegislacaoVo parametroLegislacaoVo = new ParametroLegislacaoVo();
		ParametroLegislacaoBe parametroLegislacaoBe = null;
		try
		{
			parametroLegislacaoBe = new ParametroLegislacaoBe();
			if (request.getAttribute(PARAMETRO_LEGISLACAO_VO) != null)
			{
				parametroLegislacaoVo = 
								  new ParametroLegislacaoVo((ParametroLegislacaoVo) request.getAttribute(PARAMETRO_LEGISLACAO_VO));
			}
			parametroLegislacaoBe.listarParametroLegislacao(parametroLegislacaoVo);
			Validador.validaObjeto(parametroLegislacaoVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (parametroLegislacaoBe != null)
			{
				parametroLegislacaoBe.close();
				parametroLegislacaoBe = null;
			}
		}
		getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
		processFlow(VIEW_LISTAR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
	}
}
