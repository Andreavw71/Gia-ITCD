/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BenfeitoriaPesquisar.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 17/10/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.benfeitoria;

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

import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.Form;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por pesquisar Benfeitorias do banco de dados.
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.2 $
 */
public class BenfeitoriaPesquisar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_CONSULTAR_BENFEITORIA = 2;
	private static final int REQUISICAO_LISTAR_BENFEITORIA = 3;

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
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  ParametroObrigatorioException, ConexaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					listarBenfeitoria(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_BENFEITORIA:
				{
					consultarBenfeitoria(request, response);
					break;
				}
			case REQUISICAO_LISTAR_BENFEITORIA:
				{
					solicitarListarBenfeitoria(request, response);
					break;
				}
		}
	}

	/**
	 * Este método é responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_BENFEITORIA)))
		{
			return REQUISICAO_CONSULTAR_BENFEITORIA;
		}
		if (request.getAttribute(LISTA_BENFEITORIA) != null)
		{
			return REQUISICAO_LISTAR_BENFEITORIA;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por capturar os argumentos da view de pesquisa de Bens.
	 * Como a consulta de Benfeitorias não é parametrizada, o método retorna obrigatoriamente uma nova instância de BenfeitoriaVo.
	 * Não existe valor originário da requisição para a entidade Benfeitoria.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private BenfeitoriaVo controladorInterativoJSP(HttpServletRequest request)
	{
		BenfeitoriaVo benfeitoriaVo = (BenfeitoriaVo) getBuffer(request).getAttribute(BENFEITORIA_VO);
		if(!Validador.isObjetoValido(benfeitoriaVo))
		{
			benfeitoriaVo = new BenfeitoriaVo();
		}
		return benfeitoriaVo;
	}

	/**
	 * Método responsável por listar Benfeitorias.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws IntegracaoException Esta exceção deve ser lançada quando o sistema tenta realizar integração com um sistema externo e a integração causa uma Exception.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void listarBenfeitoria(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  ConexaoException
	{
		BenfeitoriaVo benfeitoriaVo = controladorInterativoJSP(request);
		BenfeitoriaBe benfeitoriaBe = null;
		try
		{
			benfeitoriaBe = new BenfeitoriaBe();
			benfeitoriaVo = benfeitoriaBe.listarBenfeitoria(benfeitoriaVo);
			Validador.validaObjeto(benfeitoriaVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (benfeitoriaBe != null)
			{
				benfeitoriaBe.close();
				benfeitoriaBe = null;
			}
		}
		request.setAttribute(BENFEITORIA_VO, benfeitoriaVo);
		processFlow(VIEW_LISTAR_BENFEITORIA, request, response, FORWARD);
	}

	/**
	 * Método responsável por consultar de uma Benfeitoria.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void consultarBenfeitoria(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, ParametroObrigatorioException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConexaoException
	{
		BenfeitoriaVo benfeitoriaVo = 
				 new BenfeitoriaVo(new BenfeitoriaVo(StringUtil.toLong(request.getParameter(PARAMETRO_CODIGO_BENFEITORIA))));
		benfeitoriaVo.setConsultaCompleta(true);
		BenfeitoriaBe benfeitoriaBe = null;
		try
		{
			benfeitoriaBe = new BenfeitoriaBe();
			benfeitoriaVo = benfeitoriaBe.consultarBenfeitoria(benfeitoriaVo);
			Validador.validaObjeto(benfeitoriaVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (benfeitoriaBe != null)
			{
				benfeitoriaBe.close();
				benfeitoriaBe = null;
			}
		}
		request.setAttribute(BENFEITORIA_VO, benfeitoriaVo);
		processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);
	}

	/**
	 *  Método responsável por listar as Benfeitorias cadastradas, será usado através de outra servlet que precise buscar
	 *  as benfeitorias existentes no banco de dados
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarListarBenfeitoria(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		BenfeitoriaVo benfeitoriaVo = (BenfeitoriaVo) getBuffer(request).getAttribute(LISTA_BENFEITORIA);
		if(!Validador.isObjetoValido(benfeitoriaVo))
		{
			benfeitoriaVo = new BenfeitoriaVo();
		}
		benfeitoriaVo.setConsultaCompleta(false);
		BenfeitoriaBe benfeitoriaBe = null;
		try
		{
			benfeitoriaBe = new BenfeitoriaBe();
			benfeitoriaBe.listarBenfeitoria(benfeitoriaVo);
			Validador.validaObjeto(benfeitoriaVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (benfeitoriaBe != null)
			{
				benfeitoriaBe.close();
				benfeitoriaBe = null;
			}
		}
		getBuffer(request).setAttribute(LISTA_BENFEITORIA, benfeitoriaVo, request);
	}
}
