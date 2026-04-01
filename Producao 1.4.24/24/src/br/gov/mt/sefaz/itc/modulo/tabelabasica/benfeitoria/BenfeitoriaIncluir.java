/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BenfeitoriaIncluir.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 16/10/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.benfeitoria;

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

import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.Form;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por incluir Benfeitoria no banco de dados.
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.1.1.1 $
 */
public class BenfeitoriaIncluir extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_INCLUIR_BENFEITORIA = 2;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, IntegracaoException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarIncluirBenfeitoria(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_BENFEITORIA:
				{
					incluirBenfeitoria(request, response);
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
		if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_INCLUIR_BENFEITORIA;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por redirecionar para a JSP de incluir benfeitoria.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarIncluirBenfeitoria(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(BENFEITORIA_VO, new BenfeitoriaVo(), request);
		processFlow(VIEW_INCLUIR_BENFEITORIA, request, response, FORWARD);
	}

	/**
	 * Método responsável por incluir benfeitoria.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void incluirBenfeitoria(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, PaginaNaoEncontradaException,TipoRedirecionamentoInvalidoException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		BenfeitoriaVo benfeitoriaVo = controladorInterativoJSP(request);
		Validador.validaObjeto(benfeitoriaVo);
		BenfeitoriaBe benfeitoriaBe = null;
		try
		{
			benfeitoriaBe = new BenfeitoriaBe();
			obterInformacoesLogSefaz(request, benfeitoriaVo);
			benfeitoriaBe.incluirBenfeitoria(benfeitoriaVo);
			request.setAttribute(ENTIDADE_VO, benfeitoriaVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(BENFEITORIA_VO, benfeitoriaVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_BENFEITORIA, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(BENFEITORIA_VO, benfeitoriaVo, request);
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_BENFEITORIA);
			processFlow(VIEW_INCLUIR_BENFEITORIA, request, response, FORWARD);
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
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir Benfeitoria e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @throws NumberFormatException Esta exceção deve ser lançada para indicar que a aplicação tentou converter uma string para um dos tipos numéricos, mas a string não contém o formato apropriado.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
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
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws NumberFormatException Esta exceção deve ser lançada para indicar que a aplicação tentou converter uma string para um dos tipos numéricos, mas a string não contém o formato apropriado.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private BenfeitoriaVo iniciaBenfeitoriaVo(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		BenfeitoriaVo benfeitoriaVo = (BenfeitoriaVo) getBuffer(request).getAttribute(BENFEITORIA_VO);
		Validador.validaObjeto(benfeitoriaVo);
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_BENFEITORIA)))
		{
			benfeitoriaVo.setDescricaoBenfeitoria(request.getParameter(CAMPO_DESCRICAO_BENFEITORIA));
		}
		benfeitoriaVo.setStatusBenfeitoria(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		return benfeitoriaVo;
	}
}
