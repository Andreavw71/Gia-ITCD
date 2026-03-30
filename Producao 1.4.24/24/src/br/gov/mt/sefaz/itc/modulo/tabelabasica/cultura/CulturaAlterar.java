/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CulturaAlterar.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 02/11/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.cultura;

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

import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.Form;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author Elizabeth Barbosa Moreira
 * @version $Revision: 1.1.1.1 $
 */
public class CulturaAlterar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_ALTERAR_CULTURA = 3;
	private static final int REQUISICAO_SOLICITAR_ALTERAR_CULTURA = 2;

	/**
	 * Método responsável por receber as requisições de páginas JSP e outros servlets.
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @implemented by Marlo Eichenberg Motta
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, LogException, AnotacaoException, PersistenciaException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarConsultarCultura(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ALTERAR_CULTURA:
				{
					solicitarAlterarCultura(request, response);
					break;
				}
			case REQUISICAO_ALTERAR_CULTURA:
				{
					alterarCultura(request, response);
					break;
				}
		}
	}

	/**
	 * Método responsável por redirecionar o fluxo da Servlet.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (request.getAttribute(CULTURA_VO) != null)
		{
			return REQUISICAO_SOLICITAR_ALTERAR_CULTURA;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_ALTERAR_CULTURA;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por solicitar a consulta de cultura.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void solicitarConsultarCultura(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(CULTURA_VO, new CulturaVo(), request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_CULTURA_PESQUISAR_CULTURA, request), request, response, FORWARD);
	}

	/**
	 * Método responsável por alterar a cultura.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void alterarCultura(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, LogException, AnotacaoException, PersistenciaException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		CulturaVo culturaVo = controladorInterativoJSP(request);
		Validador.validaObjeto(culturaVo);
		CulturaBe culturaBe = null;
		try
		{
			culturaBe = new CulturaBe();
			obterInformacoesLogSefaz(request, culturaVo);
			culturaBe.alterarCultura(culturaVo);
			request.setAttribute(ENTIDADE_VO, culturaVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(CULTURA_VO, culturaVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ALTERAR_CULTURA, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(CULTURA_VO, culturaVo, request);
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_CULTURA);
			processFlow(VIEW_ALTERAR_CULTURA, request, response, FORWARD);
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
	}

	/**
	 * Método responsável por capturar uma um objeot do tipo <i>CulturaVoi</> da requisição.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @throws NumberFormatException Esta exceção deve ser lançada para indicar que a aplicação tentou converter uma string para um dos tipos numéricos, mas a string não contém o formato apropriado.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	private CulturaVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		CulturaVo culturaVo = iniciaCulturaVo(request);
		return culturaVo;
	}

	/**
	 * Método responsável por instanciar um objeto do tivo <i>CulturaVo</i> e atribuir os valores capturados pelo objeto <i>request</i>.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	private CulturaVo iniciaCulturaVo(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		CulturaVo culturaVo = (CulturaVo) getBuffer(request).getAttribute(CULTURA_VO);
		Validador.validaObjeto(culturaVo);
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_CULTURA)))
		{
			culturaVo.setDescricaoCultura(request.getParameter(CAMPO_DESCRICAO_CULTURA));
		}
		int statusCultura = StringUtil.toInt(request.getParameter(CAMPO_SELECT_STATUS));
		if (Validador.isNumericoValido(statusCultura))
		{
			culturaVo.setStatusCultura(new DomnStatusRegistro(statusCultura));
		}
		return culturaVo;
	}

	/**
	 * Método responsável por solicitar a alteração de uma cultura.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void solicitarAlterarCultura(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		CulturaVo culturaVo = (CulturaVo) request.getAttribute(CULTURA_VO);
		Validador.validaObjeto(culturaVo);
		getBuffer(request).setAttribute(CULTURA_VO, culturaVo, request);
		processFlow(VIEW_ALTERAR_CULTURA, request, response, FORWARD);
	}
}
