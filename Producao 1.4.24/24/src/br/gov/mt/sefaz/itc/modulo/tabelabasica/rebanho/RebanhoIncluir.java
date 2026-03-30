/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: RebanhoIncluir.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 03/11/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.rebanho;

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

import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.http.AbstractServletITCD;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet controladora do fluxo de Inclusão de Rebanho.
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.1.1.1 $
 */
public class RebanhoIncluir extends AbstractServletITCD implements Flow, Form
{
	private static final int REQUISICAO_INCLUIR_REBANHO = 2;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
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
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarIncluirRebanho(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_REBANHO:
				{
					incluirRebanho(request, response);
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
		if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_INCLUIR_REBANHO;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por redirecionar para a JSP de incluir rebanho
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarIncluirRebanho(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(REBANHO_VO, new RebanhoVo(), request);
		processFlow(VIEW_INCLUIR_REBANHO, request, response, FORWARD);
	}

	/**
	 * Método responsável por incluir rebanho.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void incluirRebanho(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  LogException, PersistenciaException, AnotacaoException
	{
		RebanhoVo rebanhoVo = controladorInterativoJSP(request);
		Validador.validaObjeto(rebanhoVo);
		RebanhoBe rebanhoBe = null;
		try
		{
			rebanhoBe = new RebanhoBe();
			obterInformacoesLogSefaz(request, rebanhoVo);
			rebanhoBe.incluirRebanho(rebanhoVo);
			request.setAttribute(ENTIDADE_VO, rebanhoVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(REBANHO_VO, rebanhoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_REBANHO, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(REBANHO_VO, rebanhoVo, request);
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_REBANHO);
			processFlow(VIEW_INCLUIR_REBANHO, request, response, FORWARD);
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
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @throws NumberFormatException Esta exceção deve ser lançada para indicar que a aplicação tentou converter uma string para um dos tipos numéricos, mas a string não contém o formato apropriado.
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private RebanhoVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		return iniciaRebanhoVo(request);
	}

	/**
	 * Este método verifica se o buffer possui o VO para adicionar as informações
	 * da funcionalidade Incluir. Se possuir, o VO é retornado.
	 * Caso contrário, é criada uma nova instância do VO para atender a necessidade do fluxo.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private RebanhoVo iniciaRebanhoVo(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		RebanhoVo rebanhoVo = (RebanhoVo) getObjetoRequest(request, REBANHO_VO, true);
		Validador.validaObjeto(rebanhoVo);
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_REBANHO)))
		{
			rebanhoVo.setDescricaoRebanho(request.getParameter(CAMPO_DESCRICAO_REBANHO));
		}
		rebanhoVo.setStatusRebanho(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		return rebanhoVo;
	}
}
