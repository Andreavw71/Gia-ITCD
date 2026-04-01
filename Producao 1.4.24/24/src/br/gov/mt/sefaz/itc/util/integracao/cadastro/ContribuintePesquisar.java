package br.gov.mt.sefaz.itc.util.integracao.cadastro;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;
import br.com.abaco.util.http.JspUtil;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.Form;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Classe responsável por consultar o contribuinte no sistema de Cadastro
 * pelo parametro informado (CPF ou CNPJ)
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.2 $
 */
public class ContribuintePesquisar extends AbstractAbacoServlet implements Form, Flow
{
	private static final int REQUISICAO_CONSULTAR_CONTRIBUINTE_CPF = 2;
	private static final int REQUISICAO_CONSULTAR_CONTRIBUINTE_CNPJ = 3;
	private static final int REQUISICAO_BUSCAR_CONTRIBUINTE = 4;
	private static final int REQUISICAO_DOCUMENTO_INVALIDO = 5;
	private static final int REQUISICAO_SOLICITAR_PESQUISAR_CONTRIBUINTE_TIPO_DOCUMENTO = 6;
	private static final int REQUISICAO_PESQUISAR_CONTRIBUINTE_TIPO_DOCUMENTO = 7;
	private static final int REQUISICAO_SOLICITAR_PESQUISAR_TIPO_DOCUMENTO = 8;
	private static final int REQUISICAO_PESQUISAR_CONTRIBUINTE_OUTROS_DOCUMENTOS = 9;

	/**
	 * Torna obrigatï¿½ria a implementaï¿½ï¿½o do método processRequest pela servlet da
	 * aplicaï¿½ï¿½o. Este método trabalha colaborativamente com o método
	 * redirecionar. O método redirecionar determina a aï¿½ï¿½o a ser tomada e
	 * processoRequest a executa.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, IntegracaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					processFlow(VIEW_PESQUISAR_CONTRIBUINTE, request, response, FORWARD);
					break;
				}
			case REQUISICAO_CONSULTAR_CONTRIBUINTE_CPF:
				{
					solicitarConsultaContribuinteCPF(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_CONTRIBUINTE_CNPJ:
				{
					solicitarConsultaContribuinteCNPJ(request, response);
					break;
				}
			case REQUISICAO_BUSCAR_CONTRIBUINTE:
				{
					buscarContribuintePeloCodigo(request, response);
					break;
				}
			case REQUISICAO_DOCUMENTO_INVALIDO:
				{
					retornarDocumentoInvalido(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_PESQUISAR_CONTRIBUINTE_TIPO_DOCUMENTO:
				{
					solicitarPesquisarContribuinteTipoDocumento(request, response);
					break;
				}
			case REQUISICAO_PESQUISAR_CONTRIBUINTE_TIPO_DOCUMENTO:
				{
					pesquisarContribuinteTipoDocumento(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_PESQUISAR_TIPO_DOCUMENTO:
				{
					solicitarListarTipoDocumentoInclude(request);
					break;
				}
			case REQUISICAO_PESQUISAR_CONTRIBUINTE_OUTROS_DOCUMENTOS:
				{
					pesquisarContribuinteOutrosDocumentos(request, response);
				}
		}
	}

	/**
	 * Este método ï¿½ responsável pela anï¿½lise dos parâmetros e a
	 * tomada de decisï¿½o quanto ao controle do fluxo da aplicaï¿½ï¿½o.
	 * @param request
	 * @return int
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(PARAMETRO_DOCUMENTO_INVALIDO)))
		{
			return REQUISICAO_DOCUMENTO_INVALIDO;
		}
		else if (Validador.isStringValida(request.getParameter(CAMPO_CNPJ)))
		{
			return REQUISICAO_CONSULTAR_CONTRIBUINTE_CNPJ;
		}
		else if (Validador.isStringValida(request.getParameter(CAMPO_CPF)))
		{
			return REQUISICAO_CONSULTAR_CONTRIBUINTE_CPF;
		}
		else if (Validador.isStringValida(request.getParameter(NUMERO_CONTRIBUINTE)))
		{
			return REQUISICAO_BUSCAR_CONTRIBUINTE;
		}
		else if(Validador.isObjetoValido(request.getAttribute(SOLICITAR_LISTAR_TIPO_DOCUMENTOS)))
		{
			return REQUISICAO_SOLICITAR_PESQUISAR_TIPO_DOCUMENTO;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_CONTRIBUINTE)))
		{
			if(Validador.isStringValida(request.getParameter(PARAMETRO_CPF)))
			{
			   return REQUISICAO_CONSULTAR_CONTRIBUINTE_CPF;
			}
			else if(Validador.isStringValida(request.getParameter(PARAMETRO_CNPJ)))
			{
				return REQUISICAO_CONSULTAR_CONTRIBUINTE_CNPJ;
			}
			else
			{
				return REQUISICAO_VAZIA;
			}
		}
		else if(Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_CONTRIBUINTE_OUTROS_DOCUMENTOS)))
		{
			return REQUISICAO_PESQUISAR_CONTRIBUINTE_OUTROS_DOCUMENTOS;
		}
		else if(Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_CONTRIBUINTE_TIPO_DOCUMENTO)))
		{
			return REQUISICAO_PESQUISAR_CONTRIBUINTE_TIPO_DOCUMENTO;			
		}
	   else if (Validador.isObjetoValido(request.getAttribute(PARAMETRO_SOLICITAR_PESQUISAR_CONTRIBUINTE_TIPO_DOCUMENTO)))
	   {
	      return REQUISICAO_SOLICITAR_PESQUISAR_CONTRIBUINTE_TIPO_DOCUMENTO;
	   }		
		else
		{
			return REQUISICAO_VAZIA;
		}
	}

	/**
	 * Método que consulta o Contribuinte no Sistema de Cadastro (Integração)
	 * atravï¿½s do CPF informado
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitarConsultaContribuinteCPF(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		ContribuinteIntegracaoVo contribuinteConsultaVo = controladorInterativoJSP(request);
		Validador.validaObjeto(contribuinteConsultaVo);
		ContribuinteIntegracaoVo contribuinteIntegracaoVo = new ContribuinteIntegracaoVo();
		contribuinteIntegracaoVo.setParametroConsulta(contribuinteConsultaVo);
		CadastroBe cadastroBe = null;
		try
		{
			cadastroBe = new CadastroBe();
			contribuinteIntegracaoVo = cadastroBe.obterContribuintePorCpf(contribuinteIntegracaoVo, true);
			Validador.validaObjeto(contribuinteIntegracaoVo);
			if (contribuinteIntegracaoVo.isExisteCollVO())
			{
				if(contribuinteIntegracaoVo.getCollVO().size() != 1)
				{
				   request.setAttribute(CONTRIBUINTE_INTEGRACAO_VO, contribuinteIntegracaoVo);
				   processFlow(VIEW_LISTAR_CONTRIBUINTE, request, response, FORWARD);					
				}
				else
				{
					contribuinteIntegracaoVo = cadastroBe.extraiContribuinte(contribuinteIntegracaoVo);
				   request.setAttribute(CONTRIBUINTE_INTEGRACAO_VO, contribuinteIntegracaoVo);
				   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);					
				}
			}
			else
			{
				request.setAttribute(EXCEPTION, new Exception("Contribuinte não cadastrado."));
				request.setAttribute(EXCEPTION_CADASTRO,EXCEPTION_CADASTRO);				
			   request.setAttribute(CONTRIBUINTE_INTEGRACAO_VO, new ContribuinteIntegracaoVo());
			   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);               
			}
		}
		catch (SQLException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		finally
		{
			if (cadastroBe != null)
			{
				cadastroBe.close();
				cadastroBe = null;
			}
		}
	}

	/**
	 * Método que consulta o Contribuinte no Sistema de Cadastro (Integração)
	 * atravï¿½s do CNPJ informado.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitarConsultaContribuinteCNPJ(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		ContribuinteIntegracaoVo contribuinteConsultaVo = controladorInterativoJSP(request);
		Validador.validaObjeto(contribuinteConsultaVo);
		ContribuinteIntegracaoVo contribuinteIntegracaoVo = new ContribuinteIntegracaoVo();
		contribuinteIntegracaoVo.setParametroConsulta(contribuinteConsultaVo);
		CadastroBe cadastroBe = null;
		try
		{
			cadastroBe = new CadastroBe();
			contribuinteIntegracaoVo = cadastroBe.obterContribuintePorCnpj(contribuinteIntegracaoVo);
			Validador.validaObjeto(contribuinteIntegracaoVo);
		   if (contribuinteIntegracaoVo.isExisteCollVO())
		   {
		      if(contribuinteIntegracaoVo.getCollVO().size() != 1)
		      {					
		         request.setAttribute(CONTRIBUINTE_INTEGRACAO_VO, contribuinteIntegracaoVo);
		         processFlow(VIEW_LISTAR_CONTRIBUINTE, request, response, FORWARD);               
		      }
		      else
		      {
		         contribuinteIntegracaoVo = cadastroBe.extraiContribuinte(contribuinteIntegracaoVo);
		         request.setAttribute(CONTRIBUINTE_INTEGRACAO_VO, contribuinteIntegracaoVo);
		         processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);               
		      }
		   }
		   else
		   {
		      request.setAttribute(EXCEPTION, new Exception("Contribuinte não cadastrado."));
		      request.setAttribute(EXCEPTION_CADASTRO,EXCEPTION_CADASTRO);
		      request.setAttribute(CONTRIBUINTE_INTEGRACAO_VO, new ContribuinteIntegracaoVo());
		      processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);               
		   }
		}
		catch (SQLException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		finally
		{
			if (cadastroBe != null)
			{
				cadastroBe.close();
				cadastroBe = null;
			}
		}
	}

	/**
	 * Método que retorna os dados referente ao Contribuinte informado. <br>O usuário
	 * informa um código e o método retorna o Nï¿½mero do Documento e o Nome do Contribuinte, 
	 * disponibilizando os dados no Buffer e no request.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void buscarContribuintePeloCodigo(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		ContribuinteIntegracaoVo contribuinteIntegracaoVo = new ContribuinteIntegracaoVo();
		ContribuinteIntegracaoVo contribuinteConsultaVo;
		contribuinteConsultaVo = 
					 new ContribuinteIntegracaoVo(StringUtil.toLongWrapper(request.getParameter(NUMERO_CONTRIBUINTE)));
		Validador.validaObjeto(contribuinteConsultaVo);
		contribuinteIntegracaoVo.setParametroConsulta(contribuinteConsultaVo);
		CadastroBe cadastroBe = null;
		try
		{
			cadastroBe = new CadastroBe();
			contribuinteIntegracaoVo = cadastroBe.obterContribuinte(contribuinteIntegracaoVo);
			Validador.validaObjeto(contribuinteIntegracaoVo);
		}
		catch (SQLException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		finally
		{
			if (cadastroBe != null)
			{
				cadastroBe.close();
				cadastroBe = null;
			}
		}
		request.setAttribute(CONTRIBUINTE_INTEGRACAO_VO, contribuinteIntegracaoVo);
		processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);
	}

	/**
	 * Método responsável por instanciar e popular o ContribuinteIntegracaoVo
	 * @param request - Requisiï¿½ï¿½o do Usuï¿½rio
	 * @throws ObjetoObrigatorioException
	 * @return ContribuinteIntegracaoVo - Vo instanciado e populado com as informações necessï¿½rias
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private ContribuinteIntegracaoVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		ContribuinteIntegracaoVo contribuinteIntegracaoVo = new ContribuinteIntegracaoVo();
		Validador.validaObjeto(contribuinteIntegracaoVo);
		if (Validador.isStringValida(request.getParameter(CAMPO_CPF)))
		{
			String cpfFormatado = request.getParameter(CAMPO_CPF);
			contribuinteIntegracaoVo.setNumrDocumento(StringUtil.retiraMascara(cpfFormatado));
		}
		else if (Validador.isStringValida(request.getParameter(CAMPO_CNPJ)))
		{
			contribuinteIntegracaoVo.setNumrDocumento(request.getParameter(CAMPO_CNPJ));
		}
		if(Validador.isStringValida(request.getParameter(PARAMETRO_CPF)))
		{
			contribuinteIntegracaoVo.setNumrDocumento(request.getParameter(PARAMETRO_CPF));
		}
		else if(Validador.isStringValida(request.getParameter(PARAMETRO_CNPJ)))
		{
			contribuinteIntegracaoVo.setNumrDocumento(request.getParameter(PARAMETRO_CNPJ));			
		}
		return contribuinteIntegracaoVo;
	}

	private void retornarDocumentoInvalido(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		request.setAttribute(EXCEPTION, new ParametroObrigatorioException("Digite um documento vï¿½lido e clique Localizar."));
		processFlow(VIEW_PESQUISAR_CONTRIBUINTE, request, response, FORWARD);
	}

	/**
	 * Método responsável por redirecionar para a pï¿½gina de pesquisa de contribuinte por Tipo de documento.
	 * @param request
	 * @param response
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarPesquisarContribuinteTipoDocumento(HttpServletRequest request, HttpServletResponse response) throws IntegracaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		CadastroBe cadastroBe = null;
		try
		{
			cadastroBe = new CadastroBe();
			Collection colecaoTipoDocumento = cadastroBe.listarTipoDocumento();
			request.setAttribute("colecaoTipoDocumento", colecaoTipoDocumento);
			processFlow(VIEW_PESQUISAR_CONTRIBUINTE_TIPO_DOCUMENTO, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		finally
		{
			if (cadastroBe != null)
			{
				cadastroBe.close();
				cadastroBe = null;
			}
		}
	}

	/**
	 * Método responsável por receber os dados da pï¿½gina de pesquisa de contribuinte, buscar no CadastroBe o contribuinte e
	 * redirecionar para a pï¿½gina de Listar Contribuinte.
	 * @param request
	 * @param response
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void pesquisarContribuinteTipoDocumento(HttpServletRequest request, HttpServletResponse response) throws IntegracaoException, 
			  ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		String numeroDocumento = new String();
		Integer tipoDocumento = new Integer(0);
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_DOCUMENTO)))
		{
			numeroDocumento = request.getParameter(CAMPO_DESCRICAO_DOCUMENTO);
		}
		if (Validador.isStringValida(request.getParameter(COMBO_PESQUISA_OPCAO_CONTRIBUINTE)))
		{
			tipoDocumento = StringUtil.toInteger(request.getParameter(COMBO_PESQUISA_OPCAO_CONTRIBUINTE));
		}
		CadastroBe cadastroBe = null;
		try
		{
			cadastroBe = new CadastroBe();
			ContribuinteIntegracaoVo contribuinteIntegracaoVo = cadastroBe.obterContribuintePorTipoDocumento(numeroDocumento, tipoDocumento);
			Validador.validaObjeto(contribuinteIntegracaoVo);
		   if (Validador.isCollectionValida(contribuinteIntegracaoVo.getCollVO()))
		   {
		      if(contribuinteIntegracaoVo.getCollVO().size() != 1)
		      {
		         request.setAttribute(CONTRIBUINTE_INTEGRACAO_VO, contribuinteIntegracaoVo);
		         processFlow(VIEW_LISTAR_CONTRIBUINTE, request, response, FORWARD);               
		      }
		      else
		      {
		         contribuinteIntegracaoVo = (ContribuinteIntegracaoVo) ((ArrayList) contribuinteIntegracaoVo.getCollVO()).get(0);;
		         request.setAttribute(CONTRIBUINTE_INTEGRACAO_VO, contribuinteIntegracaoVo);
		         processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);               
		      }
		   }
		   else
		   {
		      processFlow(VIEW_MENSAGEM_DE_CUJUS_NAO_CADASTRADO, request, response, FORWARD);               
		   }         
		}
		catch (SQLException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (ParametroObrigatorioException e)
		{
			processFlow(VIEW_MENSAGEM_DE_CUJUS_NAO_CADASTRADO, request, response, FORWARD);
		}
		finally
		{
			if (cadastroBe != null)
			{
				cadastroBe.close();
				cadastroBe = null;
			}
		}
	}

	/**
	 * Método responsável por receber os dados da pï¿½gina de pesquisa de contribuinte, buscar no CadastroBe o contribuinte e
	 * redirecionar para a pï¿½gina de Listar Contribuinte.
	 * @param request
	 * @param response
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void pesquisarContribuinteOutrosDocumentos(HttpServletRequest request, HttpServletResponse response) throws IntegracaoException, 
			  ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		String numeroDocumento = new String();
		Integer tipoDocumento = new Integer(0);
		if (Validador.isStringValida(request.getParameter(PARAMETRO_OUTROS_DOCUMENTOS)))
		{
			numeroDocumento = request.getParameter(PARAMETRO_OUTROS_DOCUMENTOS);
		}
		if (Validador.isStringValida(request.getParameter(COMBO_PESQUISA_OPCAO_CONTRIBUINTE_OUTROS_DOCUMENTOS)))
		{
			tipoDocumento = StringUtil.toInteger(request.getParameter(COMBO_PESQUISA_OPCAO_CONTRIBUINTE_OUTROS_DOCUMENTOS));
		}
		CadastroBe cadastroBe = null;
		try
		{
			cadastroBe = new CadastroBe();
			ContribuinteIntegracaoVo contribuinteIntegracaoVo = cadastroBe.obterContribuintePorTipoDocumento(numeroDocumento, tipoDocumento);
			Validador.validaObjeto(contribuinteIntegracaoVo);
		   if (Validador.isCollectionValida(contribuinteIntegracaoVo.getCollVO()))
		   {
		      if(contribuinteIntegracaoVo.getCollVO().size() != 1)
		      {
		         request.setAttribute(CONTRIBUINTE_INTEGRACAO_VO, contribuinteIntegracaoVo);
		         processFlow(VIEW_LISTAR_CONTRIBUINTE, request, response, FORWARD);               
		      }
		      else
		      {
		         contribuinteIntegracaoVo = (ContribuinteIntegracaoVo) ((ArrayList) contribuinteIntegracaoVo.getCollVO()).get(0);;
		         request.setAttribute(CONTRIBUINTE_INTEGRACAO_VO, contribuinteIntegracaoVo);
		         processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);               
		      }
		   }
		   else
		   {
		      processFlow(VIEW_MENSAGEM_DE_CUJUS_NAO_CADASTRADO, request, response, FORWARD);               
		   }
		}
		catch (SQLException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (ParametroObrigatorioException e)
		{
			processFlow(VIEW_MENSAGEM_DE_CUJUS_NAO_CADASTRADO, request, response, FORWARD);
		}
		finally
		{
			if (cadastroBe != null)
			{
				cadastroBe.close();
				cadastroBe = null;
			}
		}
	}

	/**
	 * Método responsável por listar via include os tipos de documento válidos para SEFAZ.
	 * @param request
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarListarTipoDocumentoInclude(HttpServletRequest request) throws IntegracaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
	   CadastroBe cadastroBe = null;
	   try
	   {
	      cadastroBe = new CadastroBe();
			ContribuinteIntegracaoVo contribuinteIntegracaoVo = new ContribuinteIntegracaoVo();
			contribuinteIntegracaoVo.setColecaoTipoDocumento(cadastroBe.listarTipoDocumento());
	      request.setAttribute(CONTRIBUINTE_INTEGRACAO_VO, contribuinteIntegracaoVo);
	   }
	   catch (SQLException e)
	   {
	      throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
	   }
	   finally
	   {
	      if (cadastroBe != null)
	      {
	         cadastroBe.close();
	         cadastroBe = null;
	      }
	   }		
	}
}
