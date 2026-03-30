/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BemPesquisar.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 08/10/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.bem;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;
import br.com.abaco.util.http.JspUtil;

import br.gov.mt.sefaz.itc.model.tabelabasica.bem.BemBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.bem.BemVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.bem.Form;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sefaz.mt.util.IncluiException;


/**
 * Servlet responsável por pesquisar Bem do banco de dados.
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.2 $
 */
public class BemPesquisar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_CONSULTAR_BEM = 3;
	private static final int REQUISICAO_GIAITCD_LISTAR_BEM = 4;
	private static final int REQUISICAO_LISTAR_BEM = 2;

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
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, IncluiException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConexaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarListarBem(request, response);
					break;
				}
			case REQUISICAO_LISTAR_BEM:
				{
					listarBem(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_BEM:
				{
					consultarBem(request, response);
					break;
				}
			case REQUISICAO_GIAITCD_LISTAR_BEM:
				{
					solicitarGIAITCDListarBem(request, response);
					break;
				}
		}
	}

	/**
	 *  Este método é responsável pela análise dos parâmetros e a
	 *  tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR)))
		{
			return REQUISICAO_LISTAR_BEM;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_BEM)))
		{
			return REQUISICAO_CONSULTAR_BEM;
		}
		if (request.getAttribute(LISTA_BEM) != null)
		{
			return REQUISICAO_GIAITCD_LISTAR_BEM;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por capturar os argumentos da view de pesquisa de Bens.
	 * Se o codigo do bem vier como parâmetro da página JSP, um objeto bemVo é iniciado com o código informado
	 * no parametro PARAMETRO_CODIGO_BEM.
	 * Senão o objeto bemVo é criado com o conteúdo vindo da requisição ou dos parâmetros do formulário.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private BemVo controladorInterativoJSP(HttpServletRequest request)
	{
		BemVo bemVo = null;
		//Se o codigo do bem vier como parâmetro da página JSP, um objeto bemVo é iniciado com o código informado
		//no parametro PARAMETRO_CODIGO_BEM.
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_BEM)))
		{
			bemVo = new BemVo(StringUtil.toLong(request.getParameter(PARAMETRO_CODIGO_BEM)));
		}
		//Senão o objeto bemVo é criado com o conteúdo vindo da requisição ou dos parâmetros do formulário.
		else
		{
			bemVo = (BemVo) getBuffer(request).getAttribute(BEM_VO);
			if (bemVo == null)
			{
				bemVo = new BemVo();
			}
			int tipoBem = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_BEM));
			if (Validador.isNumericoValido(tipoBem))
			{
				bemVo.setClassificacaoTipoBem(new DomnTipoBem(tipoBem));
			}
			if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_BEM)))
			{
				bemVo.setDescricaoTipoBem(request.getParameter(CAMPO_DESCRICAO_BEM));
			}
		}
		return bemVo;
	}

	/**
	 * Método responsável por redirecionar para a JSP de consultar bem
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarListarBem(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		processFlow(VIEW_PESQUISAR_BEM, request, response, FORWARD);
	}

	/**
	 * Método responsável por listar Bens.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void listarBem(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  ConexaoException
	{
		BemVo bemVo = new BemVo(controladorInterativoJSP(request));
		Validador.validaObjeto(bemVo);
		BemBe bemBe = null;
		try
		{
			bemBe = new BemBe();
			bemVo = bemBe.listarBem(bemVo);
			Validador.validaObjeto(bemVo);
			if (!Validador.isCollectionValida(bemVo.getCollVO()))
			{
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_BEM_LISTAR_DESCRICAO));
				request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_BEM);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (bemBe != null)
			{
				bemBe.close();
				bemBe = null;
			}
		}
		request.setAttribute(BEM_VO, bemVo);
		processFlow(VIEW_PESQUISAR_BEM, request, response, FORWARD);
	}

	/**
	 * Método responsável por consultar um Bem.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws NumberFormatException Esta exceção deve ser lançada para indicar que a aplicação tentou converter uma string para um dos tipos numéricos, mas a string não contém o formato apropriado.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void consultarBem(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  NumberFormatException, ConexaoException
	{
		BemVo bemVo = new BemVo(controladorInterativoJSP(request));
		bemVo.setConsultaCompleta(true);
		BemBe bemBe = null;
		try
		{
			bemBe = new BemBe();
			bemVo = bemBe.consultarBem(bemVo);
			Validador.validaObjeto(bemVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (bemBe != null)
			{
				bemBe.close();
				bemBe = null;
			}
		}
		request.setAttribute(BEM_VO, bemVo);
		processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);
	}

	/**
	 *  Método responsável por listar os Bens cadastradas, será usado através de outra servlet que precise buscar
	 *  os bens existentes no banco de dados
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarGIAITCDListarBem(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		BemVo bemVo = (BemVo) getBuffer(request).getAttribute(LISTA_BEM);
		if (!Validador.isObjetoValido(bemVo))
		{
			bemVo = new BemVo();
		}
		bemVo.setConsultaCompleta(false);
		BemBe bemBe = null;
		try
		{
			bemBe = new BemBe();
			bemBe.listarBem(bemVo);
			Validador.validaObjeto(bemVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (bemBe != null)
			{
				bemBe.close();
				bemBe = null;
			}
		}
		getBuffer(request).setAttribute(LISTA_BEM, bemVo, request);
	}
}
