/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDEnviarSenha.java
 * Revisão: João Batista Padilha e Silva
 * Data revisão: 07/04/2008
 */
package br.gov.mt.sefaz.itc.modulo.generico.giaitcd;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.EmailMessagingException;
import br.com.abaco.util.exceptions.EmailParametroObrigatorioException;
import br.com.abaco.util.exceptions.EmailUnsupportedEncodingException;
import br.com.abaco.util.exceptions.ImpossivelCriptografarException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;
import br.com.abaco.util.http.JspUtil;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.Form;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GIAITCDEnviarSenha extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_SOLICITAR_MOSTRAR_CONTRIBUINTE = 2;
	private static final int REQUISICAO_CONSULTAR_GIAITCD = 3;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_CONSTRIBUINTE = 4;

	/**
	 * Este método trabalha colaborativamente com o método
	 * redirecionar. O método redirecionar determina a ação a ser tomada e
	 * processoRequest a executa.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConexaoException
	 * @throws EmailParametroObrigatorioException
	 * @throws EmailUnsupportedEncodingException
	 * @throws EmailMessagingException
	 * @throws CloneNotSupportedException
	 * @throws ImpossivelCriptografarException
	 * @implemented by João Batista Padilha e Silva
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, IntegracaoException, 
			  ConexaoException, EmailParametroObrigatorioException, EmailUnsupportedEncodingException, EmailMessagingException, 
			  CloneNotSupportedException, ImpossivelCriptografarException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarConsultarGIAITCD(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_GIAITCD:
				{
					enviarEmail(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_CONSTRIBUINTE:
				{
					solicitarPesquisarContribuinte(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_MOSTRAR_CONTRIBUINTE:
				{
					mostraDadosContribuinte(request, response);
					break;
				}
		}
	}

	/**
	 * Este método é responsável pela análise dos parâmetros e a
	 * tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request
	 * @return int
	 * @implemented by João Batista Padilha e Silva
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO) != null)
		{
			return REQUISICAO_SOLICITAR_MOSTRAR_CONTRIBUINTE;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_ENVIAR_SENHA)))
		{
			return REQUISICAO_CONSULTAR_GIAITCD;
		}
		if(Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_CONTRIBUINTE)))
		{
		   return REQUISICAO_SOLICITAR_CONSULTAR_CONSTRIBUINTE;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_CONTRIBUINTE)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_CONSTRIBUINTE;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Este método é acionado para verificar se o usuário é servidor sefaz, lotado na  AGENFA, CGED, GIOR
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitarConsultarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException
	{
		removeBuffer(request);
		processFlow(VIEW_PESQUISAR_GIAITCD_ENVIAR_SENHA, request, response, FORWARD);
	}

	/** Método que recupera todos os campos do formulario
	 * Método que recupera do request os dados informados pelo usuário
	 * @param request
	 * @return GIAITCDVo
	 * @implemented by João Batista Padilha e Silva
	 */
	private GIAITCDVo controladorInterativoJSP(HttpServletRequest request)
	{
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		if (giaITCDVo == null)
		{
			giaITCDVo = new GIAITCDVo();
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SENHA)))
		{
			giaITCDVo.setSenha(request.getParameter(CAMPO_SENHA));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_CODIGO_GIA)))
		{
			giaITCDVo.setCodigo(StringUtil.toLong(request.getParameter(CAMPO_CODIGO_GIA)));
		}
		return giaITCDVo;
	}

	/**
	 * Este método é responsável por chamar o componente de pesquisa do contribuinte.
	 * Através desta chamada, temos acesso ao componente onde podemos pesquisar o contribuinte
	 * por CPF ou CNPJ.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitarPesquisarContribuinte(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		String codOriginal = JspUtil.getCodigoPermissaoOriginal(request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(codOriginal, FormAcesso.NOME_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	}

	/**
	 * Método que mostra os dados do Contribuinte
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void mostraDadosContribuinte(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		ContribuinteIntegracaoVo responsavelVo = (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
		giaITCDVo.setResponsavelVo(responsavelVo);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		processFlow(VIEW_PESQUISAR_GIAITCD_ENVIAR_SENHA, request, response, FORWARD);
	}

	/**
	 * Método que envia para a jsp de envio de  senha
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws EmailParametroObrigatorioException
	 * @throws EmailUnsupportedEncodingException
	 * @throws EmailMessagingException
	 * @throws ImpossivelCriptografarException
	 * @throws ConsultaException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void enviarEmail(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, IntegracaoException, 
			  EmailParametroObrigatorioException, EmailUnsupportedEncodingException, EmailMessagingException, 
			  ImpossivelCriptografarException, ConsultaException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		Validador.isObjetoValido(giaITCDVo);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			try
			{
			   giaITCDVo.setConsultaCompleta(true);
			   giaITCDVo.setUsuarioLogado(getCodigoUsuarioLogado(request));			
				giaITCDBe.enviarEmailSenhaEsquecida(giaITCDVo);
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
				processFlow(VIEW_ENVIAR_SENHA_POR_EMAIL_GIAITCD, request, response, FORWARD);
			}
		   catch (ParametroObrigatorioException e)
		   {
		      request.setAttribute(EXCEPTION, e);
		      processFlow(VIEW_PESQUISAR_GIAITCD_ENVIAR_SENHA, request, response, FORWARD);
		   }
         catch (IOException e)
         {
            request.setAttribute(EXCEPTION, e);
            processFlow(VIEW_PESQUISAR_GIAITCD_ENVIAR_SENHA, request, response, FORWARD);
         }
      }
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (giaITCDBe != null)
			{
				giaITCDBe.close();
				giaITCDBe = null;
			}
		}
	}
}
