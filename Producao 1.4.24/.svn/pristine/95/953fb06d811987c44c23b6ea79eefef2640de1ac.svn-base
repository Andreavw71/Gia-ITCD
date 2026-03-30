/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CulturaIncluir.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 01/11/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.cultura;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.Form;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por incluir Cultura no banco de dados
 * @author Marlo Eichenberg Motta e Elizabeth Barbosa Moreira
 * @version $Revision: 1.1.1.1 $
 */
public class CulturaIncluir extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_INCLUIR_CULTURA = 2;

	/**
	 * Método responsável por receber as requisições de páginas JSP e outros servlets.
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, IntegracaoException, 
			  ConexaoException, LogException, AnotacaoException, PersistenciaException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarIncluirCultura(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_CULTURA:
				{
					incluirCultura(request, response);
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
		if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_INCLUIR_CULTURA;
		}
		else
			return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por redirecionar para a JSP de incluir cultura.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void solicitarIncluirCultura(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(CULTURA_VO, new CulturaVo(), request);
		processFlow(VIEW_INCLUIR_CULTURA, request, response, FORWARD);
	}

	/**
	 * Método responsável por incluir cultura.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Elizabeth Barbosa
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void incluirCultura(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConexaoException, LogException, AnotacaoException, PersistenciaException
	{
		CulturaVo culturaVo = controladorInterativoJSP(request);
		Validador.validaObjeto(culturaVo);
		CulturaBe culturaBe = null;
		try
		{
			culturaBe = new CulturaBe();
			obterInformacoesLogSefaz(request, culturaVo);
			culturaBe.incluirCultura(culturaVo);
			request.setAttribute(ENTIDADE_VO, culturaVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(CULTURA_VO, culturaVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_CULTURA, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(CULTURA_VO, culturaVo, request);
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_CULTURA);
			processFlow(VIEW_INCLUIR_CULTURA, request, response, FORWARD);
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
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
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
	 * @return culturaVo Cultura (Value Object).
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
		culturaVo.setStatusCultura(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		return culturaVo;
	}
}
