/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BemAlterar.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 08/10/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.bem;

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

import br.gov.mt.sefaz.itc.model.tabelabasica.bem.BemBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.bem.BemVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.bem.Form;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoVerificacao;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sefaz.mt.util.AlteraException;


public class BemAlterar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_ALTERAR_BEM = 3;
	private static final int REQUISICAO_SOLICITAR_ALTERAR_BEM = 2;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, ConsultaException, 
			  AlteraException, LogException, PersistenciaException, AnotacaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarConsultarBem(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ALTERAR_BEM:
				{
					solicitarAlterarBem(request, response);
					break;
				}
			case REQUISICAO_ALTERAR_BEM:
				{
					alterarBem(request, response);
					break;
				}
		}
	}

	/**
	 *  Este método é responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (request.getAttribute(BEM_VO) != null)
		{
			return REQUISICAO_SOLICITAR_ALTERAR_BEM;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_ALTERAR_BEM;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por redirecionar para a JSP de alterar bem
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void solicitarConsultarBem(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(BEM_VO, new BemVo(), request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_BEM_PESQUISAR_BEM, request), request, response, FORWARD);
	}

	/**
	 * Método responsável por alterar um Bem.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void alterarBem(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, ConsultaException, 
			  AlteraException, LogException, PersistenciaException, AnotacaoException
	{
		BemVo bemVo = controladorInterativoJSP(request);
		Validador.validaObjeto(bemVo);
		BemBe bemBe = null;
		try
		{
			bemBe = new BemBe();
			obterInformacoesLogSefaz(request, bemVo);
			bemBe.alterarBem(bemVo);
			getBuffer(request).setAttribute(BEM_VO, bemVo, request);
			request.setAttribute(ENTIDADE_VO, bemVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(BEM_VO, bemVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ALTERAR_BEM, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(BEM_VO, bemVo, request);
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_BEM);
			processFlow(VIEW_ALTERAR_BEM, request, response, FORWARD);
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
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Alterar Bem e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private BemVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		BemVo bemVo = iniciaBemVo(request);
		return bemVo;
	}

	/**
	 * Método responsável por instanciar um objeto do tivo <i>CulturaVo</i> e atribuir os valores capturados pelo objeto <i>request</i>.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private BemVo iniciaBemVo(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		BemVo bemVo = (BemVo) getBuffer(request).getAttribute(BEM_VO);
		Validador.validaObjeto(bemVo);
		int tipoBem = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_BEM));
		if (Validador.isNumericoValido(tipoBem))
		{
			bemVo.setClassificacaoTipoBem(new DomnTipoBem(tipoBem));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_BEM)))
		{
			bemVo.setDescricaoTipoBem(request.getParameter(CAMPO_DESCRICAO_BEM));
		}
		int statusBem = StringUtil.toInt(request.getParameter(CAMPO_SELECT_STATUS));
		if (Validador.isNumericoValido(statusBem))
		{
			bemVo.setStatusBem(new DomnStatusRegistro(statusBem));
		}
		if(Validador.isStringValida(request.getParameter(CAMPO_POSSUI_CONSTRUCAO)))
		{
			bemVo.setPossuiConstrucao(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_POSSUI_CONSTRUCAO))));
		}
	   int tipoVerificacao = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_VERIFICACAO));
	   if (Validador.isNumericoValido(tipoVerificacao))
	   {
	      bemVo.setTipoVerificacao(new DomnTipoVerificacao(tipoVerificacao));
	   }
	   int tipoProtocoloGIA = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_PROTOCOLO));
	   if (Validador.isNumericoValido(tipoProtocoloGIA))
	   {
	      bemVo.setTipoProtocoloGIA(new DomnTipoProtocolo(tipoProtocoloGIA));
	   }
		return bemVo;
	}

	/**
	 * Método responsável por redirecionar para a JSP de alterar Bem.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void solicitarAlterarBem(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		BemVo bemVo = (BemVo) request.getAttribute(BEM_VO);
		Validador.validaObjeto(bemVo);
		getBuffer(request).setAttribute(BEM_VO, bemVo, request);
		processFlow(VIEW_ALTERAR_BEM, request, response, FORWARD);
	}
}
