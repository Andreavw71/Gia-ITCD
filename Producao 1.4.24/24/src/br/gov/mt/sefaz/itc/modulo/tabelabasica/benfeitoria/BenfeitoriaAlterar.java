/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BenfeitoriaAlterar.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 16/10/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.benfeitoria;

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
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.Form;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por alterar Benfeitoria no banco de dados.
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.1.1.1 $
 */
public class BenfeitoriaAlterar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_SOLICITAR_ALTERAR_BENFEITORIA = 2;
	private static final int REQUISICAO_ALTERAR_BENFEITORIA = 3;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, ConexaoException, 
			  LogException, PersistenciaException, AnotacaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarConsultarBenfeitoria(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ALTERAR_BENFEITORIA:
				{
					solicitarAlterarBenfeitoria(request, response);
					break;
				}
			case REQUISICAO_ALTERAR_BENFEITORIA:
				{
					alterarBenfeitoria(request, response);
					break;
				}
		}
	}

	/**
	 * Este método é responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (request.getAttribute(BENFEITORIA_VO) != null)
		{
			return REQUISICAO_SOLICITAR_ALTERAR_BENFEITORIA;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_ALTERAR_BENFEITORIA;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por solicitar a consulta de benfeitoria.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void solicitarConsultarBenfeitoria(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(BENFEITORIA_VO, new BenfeitoriaVo(), request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_BENFEITORIA_PESQUISAR_BENFEITORIA, request), request, response, FORWARD);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ConsultaException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void alterarBenfeitoria(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConexaoException, 
			  LogException, PersistenciaException, AnotacaoException
	{
		BenfeitoriaVo benfeitoriaVo = controladorInterativoJSP(request);
		Validador.validaObjeto(benfeitoriaVo);
		BenfeitoriaBe benfeitoriaBe = null;
		try
		{
			benfeitoriaBe = new BenfeitoriaBe();
			obterInformacoesLogSefaz(request, benfeitoriaVo);
			benfeitoriaBe.alterarBenfeitoria(benfeitoriaVo);
			request.setAttribute(ENTIDADE_VO, benfeitoriaVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(BENFEITORIA_VO, benfeitoriaVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ALTERAR_BENFEITORIA, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(BENFEITORIA_VO, benfeitoriaVo, request);
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_BENFEITORIA);
			processFlow(VIEW_ALTERAR_BENFEITORIA, request, response, FORWARD);
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
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade AlterarBenfeitoria e devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @throws NumberFormatException Esta exceção deve ser lançada para indicar que a aplicação tentou converter uma string para um dos tipos numéricos, mas a string não contém o formato apropriado.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	private BenfeitoriaVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		BenfeitoriaVo benfeitoriaVo = iniciaBenfeitoriaVo(request);
		return benfeitoriaVo;
	}

	/**
	 * Método responsável por instanciar um objeto do tivo <i>BenfeitoriaVo</i> e atribuir os valores capturados pelo objeto <i>request</i>.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @throws NumberFormatException Esta exceção deve ser lançada para indicar que a aplicação tentou converter uma string para um dos tipos numéricos, mas a string não contém o formato apropriado.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	private BenfeitoriaVo iniciaBenfeitoriaVo(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		BenfeitoriaVo benfeitoriaVo = (BenfeitoriaVo) getBuffer(request).getAttribute(BENFEITORIA_VO);
		Validador.validaObjeto(benfeitoriaVo);
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_BENFEITORIA)))
		{
			benfeitoriaVo.setDescricaoBenfeitoria(request.getParameter(CAMPO_DESCRICAO_BENFEITORIA));
		}
		int statusBenfeitoria = StringUtil.toInt(request.getParameter(CAMPO_SELECT_STATUS));
		if (Validador.isNumericoValido(statusBenfeitoria))
		{
			benfeitoriaVo.setStatusBenfeitoria(new DomnStatusRegistro(statusBenfeitoria));
		}
		return benfeitoriaVo;
	}

	/**
	 * Método responsável por redirecionar para a JSP de alterar benfeitoria.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void solicitarAlterarBenfeitoria(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		BenfeitoriaVo benfeitoriaVo = (BenfeitoriaVo) request.getAttribute(BENFEITORIA_VO);
		Validador.validaObjeto(benfeitoriaVo);
		getBuffer(request).setAttribute(BENFEITORIA_VO, benfeitoriaVo, request);
		processFlow(VIEW_ALTERAR_BENFEITORIA, request, response, FORWARD);
	}
}
