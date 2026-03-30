package br.gov.mt.sefaz.itc.modulo.tabelabasica.naturezadaoperacao;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;
import br.com.abaco.util.http.JspUtil;

import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por pesquisar Construção do banco de dados
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.2 $
 */
public class NaturezaOperacaoPesquisar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_CONSULTAR_NATUREZA_OPERACAO = 2;
	private static final int REQUISICAO_LISTAR_NATUREZA_DA_OPERACAO = 3;

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  ConexaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					listarNaturezaOperacao(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_NATUREZA_OPERACAO:
				{
					consultarNaturezaOperacao(request, response);
					break;
				}
			case REQUISICAO_LISTAR_NATUREZA_DA_OPERACAO:
				{
					solicitarListarNaturezaOperacao(request, response);
					break;
				}
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_NATUREZA_OPERACAO)))
		{
			return REQUISICAO_CONSULTAR_NATUREZA_OPERACAO;
		}
		if (request.getAttribute(LISTA_NATUREZA_DA_OPERACAO) != null)
		{
			return REQUISICAO_LISTAR_NATUREZA_DA_OPERACAO;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void listarNaturezaOperacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  ConexaoException
	{
		NaturezaOperacaoVo naturezaOperacaoVo = new NaturezaOperacaoVo();
		NaturezaOperacaoBe naturezaOperacaoBe = null;
		try
		{
			naturezaOperacaoBe = new NaturezaOperacaoBe();
			naturezaOperacaoVo = naturezaOperacaoBe.listarNaturezaOperacao(naturezaOperacaoVo);
			Validador.validaObjeto(naturezaOperacaoVo);
			if (!Validador.isCollectionValida(naturezaOperacaoVo.getCollVO()))
			{
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.LISTAR_NATUREZA_OPERACAO));
				request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_NATUREZA_OPERACAO);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (naturezaOperacaoBe != null)
			{
				naturezaOperacaoBe.close();
				naturezaOperacaoBe = null;
			}
		}
		request.setAttribute(NATUREZA_OPERACAO_VO, naturezaOperacaoVo);
		processFlow(VIEW_LISTAR_NATUREZA_OPERACAO, request, response, FORWARD);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void consultarNaturezaOperacao(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  ConexaoException
	{
		NaturezaOperacaoVo naturezaOperacaoVo = 
				 new NaturezaOperacaoVo(new NaturezaOperacaoVo(StringUtil.toLong(request.getParameter(PARAMETRO_CODIGO_NATUREZA_OPERACAO))));
		naturezaOperacaoVo.setConsultaCompleta(true);
		NaturezaOperacaoBe naturezaOperacaoBe = null;
		try
		{
			naturezaOperacaoBe = new NaturezaOperacaoBe();
			naturezaOperacaoVo = naturezaOperacaoBe.consultarNaturezaOperacao(naturezaOperacaoVo);
			Validador.validaObjeto(naturezaOperacaoVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (naturezaOperacaoBe != null)
			{
				naturezaOperacaoBe.close();
				naturezaOperacaoBe = null;
			}
		}
		request.setAttribute(NATUREZA_OPERACAO_VO, naturezaOperacaoVo);
		processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarListarNaturezaOperacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		NaturezaOperacaoVo naturezaOperacaoVo = 
				 (NaturezaOperacaoVo) getBuffer(request).getAttribute(LISTA_NATUREZA_DA_OPERACAO);
		if(!Validador.isObjetoValido(naturezaOperacaoVo))
		{
		   naturezaOperacaoVo = new NaturezaOperacaoVo();
		}
		naturezaOperacaoVo.setConsultaCompleta(false);
		NaturezaOperacaoBe naturezaOperacaoBe = null;
		try
		{
			naturezaOperacaoBe = new NaturezaOperacaoBe();
			naturezaOperacaoBe.listarNaturezaOperacao(naturezaOperacaoVo);
			Validador.validaObjeto(naturezaOperacaoVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (naturezaOperacaoBe != null)
			{
				naturezaOperacaoBe.close();
				naturezaOperacaoBe = null;
			}
		}
		getBuffer(request).setAttribute(LISTA_NATUREZA_DA_OPERACAO, naturezaOperacaoVo, request);
	}
}
