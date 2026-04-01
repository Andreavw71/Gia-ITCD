/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConstrucaoPesquisar.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 12/11/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.construcao;

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

import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.Form;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por pesquisar Construção do banco de dados
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.2 $
 */
public class ConstrucaoPesquisar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_CONSULTAR_CONSTRUCAO = 2;
	private static final int REQUISICAO_LISTAR_CONSTRUCAO = 3;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws IntegracaoException Esta exceção deve ser lançada quando o sistema tenta realizar integração com um sistema externo e a integração causa uma Exception.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  ConexaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					listarConstrucao(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_CONSTRUCAO:
				{
					consultarConstrucao(request, response);
					break;
				}
			case REQUISICAO_LISTAR_CONSTRUCAO:
				{
					solicitarListarConstrucao(request, response);
					break;
				}
		}
	}

	/**
	 * Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_CONSTRUCAO)))
		{
			return REQUISICAO_CONSULTAR_CONSTRUCAO;
		}
		if (request.getAttribute(LISTA_CONSTRUCAO) != null)
		{
			return REQUISICAO_LISTAR_CONSTRUCAO;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Este método cria um BemVo e atribui os atributos <i>descricaoTipoBem</i> e <i>classificacaoTipoBem</i> de acordo com os campos 
	 * <i>CAMPO_DESCRICAO_CONSTRUCAO</i> originário da requisição.
	 * Caso contrário, é criada uma nova instância do ConstrucaoVo para atender a necessidade do fluxo.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private ConstrucaoVo controladorInterativoJSP(HttpServletRequest request)
	{
		ConstrucaoVo construcaoVo = (ConstrucaoVo) getBuffer(request).getAttribute(CONSTRUCAO_VO);
		if(!Validador.isObjetoValido(construcaoVo))
		{
			construcaoVo = new ConstrucaoVo();
		}
		return construcaoVo;
	}

	/**
	 * Método responsável por listar as construções.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws IntegracaoException Esta exceção deve ser lançada quando o sistema tenta realizar integração com um sistema externo e a integração causa uma Exception.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void listarConstrucao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  ConexaoException
	{
		ConstrucaoVo construcaoVo = controladorInterativoJSP(request);
		ConstrucaoBe construcaoBe = null;
		try
		{
			construcaoBe = new ConstrucaoBe();
			construcaoVo = construcaoBe.listarConstrucao(construcaoVo);
			Validador.validaObjeto(construcaoVo);
			if (!Validador.isCollectionValida(construcaoVo.getCollVO()))
			{
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_CONSTRUCAO_LISTAR_DESCRICAO));
				request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_CONSTRUCAO);
			}
		   request.setAttribute(CONSTRUCAO_VO, construcaoVo);
		   processFlow(VIEW_LISTAR_CONSTRUCAO, request, response, FORWARD);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (construcaoBe != null)
			{
				construcaoBe.close();
				construcaoBe = null;
			}
		}
	}

	/**
	 * Método responsável por consultar uma construção.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void consultarConstrucao(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  ConexaoException
	{
		ConstrucaoVo construcaoVo = new ConstrucaoVo(new ConstrucaoVo(StringUtil.toLong(request.getParameter(PARAMETRO_CODIGO_CONSTRUCAO))));
		construcaoVo.setConsultaCompleta(true);
		ConstrucaoBe construcaoBe = null;
		try
		{
			construcaoBe = new ConstrucaoBe();
			construcaoVo = construcaoBe.consultarConstrucao(construcaoVo);
			Validador.validaObjeto(construcaoVo);
		   request.setAttribute(CONSTRUCAO_VO, construcaoVo);
		   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(CONSTRUCAO_VO, construcaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_LISTAR_CONSTRUCAO, request, response, FORWARD);
		}
		finally
		{
			if (construcaoBe != null)
			{
				construcaoBe.close();
				construcaoBe = null;
			}
		}
	}

	/**
	 *  Método responsável por listar as Construções cadastradas, será usado através de outra servlet que precise buscar
	 *  as construções existentes no banco de dados
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarListarConstrucao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		ConstrucaoVo construcaoVo = (ConstrucaoVo) getBuffer(request).getAttribute(LISTA_CONSTRUCAO);
		if(!Validador.isObjetoValido(construcaoVo))
		{
			construcaoVo = new ConstrucaoVo();
		}
		construcaoVo.setConsultaCompleta(false);
		ConstrucaoBe construcaoBe = null;
		try
		{
			construcaoBe = new ConstrucaoBe();
			construcaoBe.listarConstrucao(construcaoVo);
			Validador.validaObjeto(construcaoVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (construcaoBe != null)
			{
				construcaoBe.close();
				construcaoBe = null;
			}
		}
		getBuffer(request).setAttribute(LISTA_CONSTRUCAO, construcaoVo, request);
	}
}
