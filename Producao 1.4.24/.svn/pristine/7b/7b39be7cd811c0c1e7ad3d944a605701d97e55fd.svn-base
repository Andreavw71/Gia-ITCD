/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: RebanhoAlterar.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 03/11/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.rebanho;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;

import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.http.AbstractServletITCD;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet controladora do fluxo de alteração de Rebanho.
 * @author Anderson Boehler Iglesias Araujo
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
public class RebanhoAlterar extends AbstractServletITCD implements Flow, Form
{
	private static final int REQUISICAO_SOLICITAR_ALTERAR_REBANHO = 2;
	private static final int REQUISICAO_ALTERAR_REBANHO = 3;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, ConexaoException, 
			  LogException, PersistenciaException, AnotacaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarConsultarRebanho(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ALTERAR_REBANHO:
				{
					solicitarAlterarRebanho(request, response);
					break;
				}
			case REQUISICAO_ALTERAR_REBANHO:
				{
					alterarRebanho(request, response);
					break;
				}
		}
	}

	/**
	 *  Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (request.getAttribute(REBANHO_VO) != null)
		{
			return REQUISICAO_SOLICITAR_ALTERAR_REBANHO;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_ALTERAR_REBANHO;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método que realiza a solicitação da requisição para a consulta de rebanho.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void solicitarConsultarRebanho(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_REBANHO_PESQUISAR_REBANHO, request), request, response, FORWARD);
	}

	/**
	 * Método responsável por realizar o fluxo da alteração de rebanho.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void alterarRebanho(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, LogException, PersistenciaException, AnotacaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		RebanhoVo rebanhoVo = controladorInterativoJSP(request);
		Validador.validaObjeto(rebanhoVo);
		RebanhoBe rebanhoBe = null;
		try
		{
			rebanhoBe = new RebanhoBe();
			obterInformacoesLogSefaz(request, rebanhoVo);
			rebanhoBe.alterarRebanho(rebanhoVo);
			request.setAttribute(ENTIDADE_VO, rebanhoVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(REBANHO_VO, rebanhoVo);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ALTERAR_REBANHO, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			request.setAttribute(REBANHO_VO, rebanhoVo);
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_REBANHO);
			processFlow(VIEW_ALTERAR_REBANHO, request, response, FORWARD);
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
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return RebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private RebanhoVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		return iniciaRebanhoVo(request);
	}
	
	/**
	 * Este método verifica se o buffer possui o VO para adicionar as informações
	 * da funcionalidade Alterar. Se possuir, o VO é retornado.
	 * Caso contrário, é criada uma nova instância do VO para atender a necessidade do fluxo.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return RebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private RebanhoVo iniciaRebanhoVo(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		RebanhoVo rebanhoVo = (RebanhoVo) getObjetoRequest(request, REBANHO_VO);
		Validador.validaObjeto(rebanhoVo);
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_REBANHO)))
		{
			long codigo = StringUtil.toLong(request.getParameter(PARAMETRO_CODIGO_REBANHO));
			if (Validador.isNumericoValido(codigo))
			{
				rebanhoVo.setCodigo(codigo);
			}
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_REBANHO)))
		{
			rebanhoVo.setDescricaoRebanho(request.getParameter(CAMPO_DESCRICAO_REBANHO));
		}
		int statusRebanho = StringUtil.toInt(request.getParameter(CAMPO_SELECT_STATUS));
		if (Validador.isNumericoValido(statusRebanho))
		{
			rebanhoVo.setStatusRebanho(new DomnStatusRegistro(statusRebanho));
		}
		return rebanhoVo;
	}

	/**
	 * Método responsável por controlar o fluxo da alteração de Rebanho.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void solicitarAlterarRebanho(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		RebanhoVo rebanhoVo = (RebanhoVo) request.getAttribute(REBANHO_VO);
		Validador.validaObjeto(rebanhoVo);
		request.setAttribute(REBANHO_VO, rebanhoVo);
		processFlow(VIEW_ALTERAR_REBANHO, request, response, FORWARD);
	}
}
