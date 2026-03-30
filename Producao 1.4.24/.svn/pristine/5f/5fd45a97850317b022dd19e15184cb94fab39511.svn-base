/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AvaliacaoConsultar.java
 * Revisão:
 * Data revisão:
 * Implementação: Elizabeth Barbosa Moreira
 * Adaptações: Janeiro de 2008 / Elizabeth Barbosa Moreira
 * Data novas implementação:
 */
package br.gov.mt.sefaz.itc.modulo.generico.avaliacao;

import br.com.abaco.util.Paginador;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.generico.avaliacao.AvaliacaoBemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.AvaliacaoBemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.Form;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnFuncionalidadeOrigem;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsultaAvaliacao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoContribuinte;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.io.IOException;

import java.sql.SQLException;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**Servlet de Controle de Fluxo da funcionalidade Icluir Avaliação.
 * @author Elizabeth Barbosa Moreira
 * @version $Revision:
 */
public class AvaliacaoConsultar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_GIA_ITCD_POR_NUMERO = 2;
	private static final int REQUISICAO_SOLICITAR_LISTAR_GIA_ITCD = 3;	
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_CONTRIBUINTE_RESPONSAVEL = 4;	
	private static final int REQUISICAO_MOSTRAR_DADOS_CONTRIBUINTE = 5;
	private static final int REQUISICAO_DETALHAR_AVALIACAO_GIA_ITCD = 6;
	private static final int REQUISICAO_DETALHAR_AVALIACAO_BEM_GIA_ITCD = 7;
	private static final int REQUISICAO_TROCAR_PAGINA = 8;
	

	/**Metodo utilizado para processar a requisição.
	 *
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException,
			  TipoRedirecionamentoInvalidoException, ParseException, SQLException, ConsultaException,
			  ObjetoObrigatorioException, ConexaoException, ParametroObrigatorioException, IntegracaoException, 
			  DadoNecessarioInexistenteException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
				   solicitarConsultarAvaliacaoGIAITCD(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_GIA_ITCD_POR_NUMERO:
				{
					solicitarConsultarAvaliacaoGIAITCDPorNumeroGIAITCD(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_LISTAR_GIA_ITCD:
				{
					solicitarListarGIAITCD(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_CONTRIBUINTE_RESPONSAVEL:
				{
				   solicitaConsultarContribuinteResponsavel(request, response);
					break;
				}
			case REQUISICAO_MOSTRAR_DADOS_CONTRIBUINTE:
				{
				   mostrarDadosContribuinte(request, response);
					break;
				}
			case REQUISICAO_DETALHAR_AVALIACAO_GIA_ITCD:
				{
				   detalharGIAITCD(request, response);
					break;
				}
			case REQUISICAO_DETALHAR_AVALIACAO_BEM_GIA_ITCD:
				{
				   detalharAvaliacaoBem(request, response);
					break;
				}
			case REQUISICAO_TROCAR_PAGINA:
				{
					solicitarTrocarPagina(request, response);
					break;
				}
		}
	}

	/**Metodo utilizado por redirenciona as funcionalidades.
	 *
	 * @param request
	 * @return int
	 * @implemented by Elizabeth Barbosa
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if(Validador.isStringValida(request.getParameter(PARAMETRO_PESQUISAR_GIAITCD_POR_NUMERO)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_GIA_ITCD_POR_NUMERO;
		}
		else if(Validador.isStringValida(request.getParameter(PARAMETRO_DETALHAR_AVALIACAO_BEM)))
		{
			return REQUISICAO_DETALHAR_AVALIACAO_BEM_GIA_ITCD;
		}
		else if(Validador.isStringValida(request.getParameter(BOTAO_LISTAR_GIAITCD)))
		{
			return REQUISICAO_SOLICITAR_LISTAR_GIA_ITCD;
		}
	   else if (request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO) != null)
	   {
	      return REQUISICAO_MOSTRAR_DADOS_CONTRIBUINTE;
	   }		
	   else if(Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_CONTRIBUINTE)))
	   {
			return REQUISICAO_SOLICITAR_CONSULTAR_CONTRIBUINTE_RESPONSAVEL;
	   }
		else if(Validador.isStringValida(request.getParameter(BOTAO_DETALHAR_GIASITCD)))
		{
			return REQUISICAO_DETALHAR_AVALIACAO_GIA_ITCD;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_TROCAR_PAGINA)))
		{
			return REQUISICAO_TROCAR_PAGINA;
		}
		return REQUISICAO_VAZIA;
	}

	/**Controlador interativo, responsavel por preencher o objeto atual
	 * 
	 * @param request
	 * @return br.gov.mt.sefaz.cogar.model.garantia.GarantiaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @implemented by Elizabeth Barbosa
	 */
	private AvaliacaoBemTributavelVo controladorInterativoJSP(HttpServletRequest request) throws ConsultaException,
			  ObjetoObrigatorioException, ConexaoException, IntegracaoException
	{
		AvaliacaoBemTributavelVo avaliacao = (AvaliacaoBemTributavelVo) getBuffer(request).getAttribute(AVALIACAO_BEM_VO);
		if (avaliacao == null)
		{
			avaliacao = new AvaliacaoBemTributavelVo();
		}
		if(Validador.isStringValida(request.getParameter(CAMPO_CODIGO_GIA)))
		{
			avaliacao.getBemTributavel().getGiaITCDVo().setCodigo(StringUtil.toLong(request.getParameter(CAMPO_CODIGO_GIA)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DATA_INICIAL)))
		{
			avaliacao.setDataInicial(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_INICIAL)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DATA_FINAL)))
		{
			avaliacao.setDataFinal(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_FINAL)));
		}
	   if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_CONSULTA)))
	   {			
			avaliacao.setTipoConsultaAvaliacao(new DomnTipoConsultaAvaliacao(StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_CONSULTA))));
	   }		
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_AGENFA)))
		{
			avaliacao.getListaAgenfa().setCodgUnidade(StringUtil.toInteger(request.getParameter(CAMPO_SELECT_AGENFA)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_GERENCIA_EXECUCAO)))
		{
			avaliacao.getListaGerencia().setCodgUnidade(StringUtil.toInteger(request.getParameter(CAMPO_SELECT_GERENCIA_EXECUCAO)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_UNIDADE_AVALIACAO)))
		{
			avaliacao.getListaUnidadesFazendarias().setCodgUnidade(StringUtil.toInteger(request.getParameter(CAMPO_SELECT_UNIDADE_AVALIACAO)));
		}
		return avaliacao;
	}

	/**
	 * Este método é acionado quando, o usuario solicita a inclusão da GIA, aciona o caso de uso de integração de pesquisa de contribuinte
	 * @param request
	 * @param response
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaConsultarContribuinteResponsavel(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, ConexaoException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		AvaliacaoBemTributavelVo avaliacaoBemTributavelVo = controladorInterativoJSP(request);
		GIAITCDVo giaITCDVo = avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo();
		giaITCDVo.setOrigem(DomnTipoContribuinte.DECLARANTE);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		request.setAttribute(CONTRIBUINTE_APENAS_CPF, CONTRIBUINTE_APENAS_CPF);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_PESQUISAR_AVALIACAO_GIAITCD_PESQUISAR_GIAITCD_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	}

	/**
	 * Fluxo responsável por mostrar os dados do contribuinte consultado.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ParseException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void mostrarDadosContribuinte(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ParseException, ConsultaException, 
			  ConexaoException, IntegracaoException
	{
		ContribuinteIntegracaoVo contribuinteIntegracaoVo = (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
		Validador.validaObjeto(contribuinteIntegracaoVo);
		AvaliacaoBemTributavelVo avaliacaoBemTributavelVo = controladorInterativoJSP(request);
		avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo().setResponsavelVo(contribuinteIntegracaoVo);
		getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacaoBemTributavelVo, request);
		processFlow(VIEW_PESQUISAR_AVALIACAO, request, response, FORWARD);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ParseException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarConsultarAvaliacaoGIAITCDPorNumeroGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ParseException, ConsultaException, 
			  ConexaoException, IntegracaoException, DadoNecessarioInexistenteException
	{
	   AvaliacaoBemTributavelVo avaliacao = controladorInterativoJSP(request);
	   Validador.validaObjeto(avaliacao);
		AvaliacaoBemTributavelBe avaliacaoBemTributabelBe = null;
		try
		{
		   avaliacaoBemTributabelBe = new AvaliacaoBemTributavelBe();
			obterInformacoesLogSefaz(request, avaliacao);
		   avaliacao = avaliacaoBemTributabelBe.consultarGIAITCDAvaliadaPorNumero(avaliacao);
			processaBensTributaveis(avaliacao.getBemTributavel().getGiaITCDVo());
			getBuffer(request).setAttribute(CONSULTAR_AVALIACAO, CONSULTAR_AVALIACAO, request);
			getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacao, request);
		   getBuffer(request).setAttribute(GIAITCD_VO, avaliacao.getBemTributavel().getGiaITCDVo(), request);
		   processFlow(VIEW_AVALIACAO_DETALHAR_BEM_GIAITCD, request, response, FORWARD);			
		}
		catch(SQLException e)
		{
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch(ParametroObrigatorioException e)
		{
		   request.setAttribute(EXCEPTION, e);
			getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacao, request);			
		   processFlow(VIEW_PESQUISAR_AVALIACAO, request, response, FORWARD);			
		}
      catch (IOException e)
      {
         request.setAttribute(EXCEPTION, e);
         getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacao, request);        
         processFlow(VIEW_PESQUISAR_AVALIACAO, request, response, FORWARD);
      }
      finally
		{
			if(avaliacaoBemTributabelBe != null)
			{
			   avaliacaoBemTributabelBe.close();
			   avaliacaoBemTributabelBe = null;
			}
		}
	}

	protected void processaBensTributaveis(GIAITCDVo gia)
	{
		BemTributavelVo atual = null;
		for (Iterator it = gia.getBemTributavelVo().getCollVO().iterator(); it.hasNext(); )
		{
			atual = (BemTributavelVo) it.next();
			atual.getAvaliacaoBemTributavelVo().setIsento(atual.getIsencaoPrevista());
			atual.setBemPassivelAvaliacao(GIAITCDBe.isBemPassivelAvaliacao(gia, atual));
		}
	}
	

	/**
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ParseException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarListarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ParseException, ConsultaException, ConexaoException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, DadoNecessarioInexistenteException
	{
		AvaliacaoBemTributavelVo avaliacao = controladorInterativoJSP(request);	
		Validador.validaObjeto(avaliacao);
		AvaliacaoBemTributavelBe avaliacaoBemTributavelBe = null;
		try
		{
		   avaliacaoBemTributavelBe = new AvaliacaoBemTributavelBe();
			obterInformacoesLogSefaz(request, avaliacao);
			avaliacao = avaliacaoBemTributavelBe.listarGIAITCDAvaliada(avaliacao);
			if(Validador.isCollectionValida(avaliacao.getBemTributavel().getGiaITCDVo().getCollVO()))
			{
				avaliacao.getBemTributavel().getGiaITCDVo().setPaginador(new Paginador<GIAITCDVo>(avaliacao.getBemTributavel().getGiaITCDVo().getCollVO(), 15));
			}
			getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacao, request);			
		   processFlow(VIEW_PESQUISAR_AVALIACAO, request, response, FORWARD);         
		}
		catch(SQLException e)
		{
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch(ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
			getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacao, request);
			processFlow(VIEW_PESQUISAR_AVALIACAO, request, response, FORWARD);         
		}
		finally
		{
			if(avaliacaoBemTributavelBe != null)
			{
			   avaliacaoBemTributavelBe.close();
			   avaliacaoBemTributavelBe = null;
			}
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ParseException
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void detalharGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException,
			  TipoRedirecionamentoInvalidoException, ParseException, ConsultaException, ObjetoObrigatorioException,
			  ConexaoException, IntegracaoException, ParametroObrigatorioException
	{
		
		long codigoGIA = StringUtil.toLong(request.getParameter(BOTAO_DETALHAR_GIASITCD));		
	   AvaliacaoBemTributavelVo avaliacao = controladorInterativoJSP(request);
		if(Validador.isNumericoValido(codigoGIA))
		   avaliacao.getBemTributavel().getGiaITCDVo().setCodigo(codigoGIA);
	   Validador.validaObjeto(avaliacao);
	   AvaliacaoBemTributavelBe avaliacaoBemTributabelBe = null;
	   try
	   {
	      avaliacaoBemTributabelBe = new AvaliacaoBemTributavelBe();
	      obterInformacoesLogSefaz(request, avaliacao);
			Collection collVO = avaliacao.getBemTributavel().getGiaITCDVo().getCollVO();
	      avaliacao = avaliacaoBemTributabelBe.consultarGIAITCDAvaliadaPorNumero(avaliacao);
			avaliacao.getBemTributavel().getGiaITCDVo().setCollVO(collVO);
	      processaBensTributaveis(avaliacao.getBemTributavel().getGiaITCDVo());
	      getBuffer(request).setAttribute(CONSULTAR_AVALIACAO, CONSULTAR_AVALIACAO, request);
	      getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacao, request);
	      getBuffer(request).setAttribute(GIAITCD_VO, avaliacao.getBemTributavel().getGiaITCDVo(), request);
	      processFlow(VIEW_AVALIACAO_DETALHAR_BEM_GIAITCD, request, response, FORWARD);       
	   }
	   catch(SQLException e)
	   {
	      throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
	   }
	   catch(ParametroObrigatorioException e)
	   {
	      request.setAttribute(EXCEPTION, e);
	      getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacao, request);        
	      processFlow(VIEW_PESQUISAR_AVALIACAO, request, response, FORWARD);         
	   }
      catch (IOException e)
      {
          request.setAttribute(EXCEPTION, e);
          getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacao, request);        
          processFlow(VIEW_PESQUISAR_AVALIACAO, request, response, FORWARD);      
      }
      finally
	   {
	      if(avaliacaoBemTributabelBe != null)
	      {
	         avaliacaoBemTributabelBe.close();
	         avaliacaoBemTributabelBe = null;
	      }
	   }
	}
	
	/**
	 * Este método obtem da gia um bem tributável específico para detalhe do mesmo em tela.
	 *
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ParseException
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private void detalharAvaliacaoBem(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException,
			  TipoRedirecionamentoInvalidoException, ParseException, ConsultaException, ObjetoObrigatorioException,
			  ConexaoException, IntegracaoException
	{
		AvaliacaoBemTributavelVo avaliacaoBemTributavelVo = controladorInterativoJSP(request);
		int indice = StringUtil.toInt(request.getParameter(PARAMETRO_DETALHAR_AVALIACAO_BEM));
		BemTributavelVo bemTributavelVo =
				 (BemTributavelVo) ((ArrayList) avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo().getBemTributavelVo().getCollVO()).get(indice);
		getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacaoBemTributavelVo, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		processFlow(VIEW_DETALHAR_AVALIACAO_BEM, request, response, FORWARD);
	}

	/**
	 * Este método executa a consulta de Avalição por Agenfa
	 *
	 * @param request
	 * @param response
	 * @throws ParseException
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 */
	private void consultarAvalicaoPorUnidadeFazendaria(HttpServletRequest request, HttpServletResponse response) throws ParseException,
			  ConsultaException, ObjetoObrigatorioException, ConexaoException, IntegracaoException,
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		AvaliacaoBemTributavelVo avaliacaoBemTributavelVo = controladorInterativoJSP(request);
		
		if(avaliacaoBemTributavelVo.getDataInicial().after(new Date()))
		{
			request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_DATA_INICIAL_MAIOR_ATUAL));
			processFlow(VIEW_PESQUISAR_AVALIACAO_POR_AGENFA, request, response, FORWARD);
		}
		
		if(avaliacaoBemTributavelVo.getDataFinal().after(new Date()))
		{
			request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_DATA_FINAL_MAIOR_ATUAL));
			processFlow(VIEW_PESQUISAR_AVALIACAO_POR_AGENFA, request, response, FORWARD);
		}
		
		if (avaliacaoBemTributavelVo.isPeriodoValido())
		{
			AvaliacaoBemTributavelVo parametro = new AvaliacaoBemTributavelVo(avaliacaoBemTributavelVo);
			AvaliacaoBemTributavelBe be = null;
			try
			{
				be = new AvaliacaoBemTributavelBe();
				parametro = be.listarAvaliacaoBemTributavelPorStatusPeriodo(parametro);
				getBuffer(request).setAttribute(AVALIACAO_BEM_VO, parametro, request);
				processFlow(VIEW_PESQUISAR_AVALIACAO_LISTA_GIA, request, response, FORWARD);
			}
			catch (SQLException e)
			{
				request.setAttribute(EXCEPTION, e);
				processFlow(VIEW_PESQUISAR_AVALIACAO_POR_AGENFA, request, response, FORWARD);
			}
			finally
			{
				if(be != null)
				{
					be.close();
					be = null;
				}
			}
		}
		else
		{
			request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_DATA_FINAL_MAIOR_INICIAL));
			processFlow(VIEW_PESQUISAR_AVALIACAO_POR_AGENFA, request, response, FORWARD);
		}
	}
	
	private void solicitarConsultarAvaliacaoGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, IntegracaoException, ConexaoException, 
			  ParseException, ConsultaException, DadoNecessarioInexistenteException
	{
		removeBuffer(request);
		AvaliacaoBemTributavelVo avaliacaoBemTributavelVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDBe = null;
		try
		{  
			giaITCDBe = new GIAITCDBe();
			try
			{
				obterInformacoesLogSefaz(request, avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo());
				avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo().setOrigem(DomnFuncionalidadeOrigem.CONSULTAR_GIA_ITCD);
				giaITCDBe.verificaUsuarioAptoAcessoFuncionalidade(avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo());
				obterUnidadesFazendarias(avaliacaoBemTributavelVo);
			   getBuffer(request).setAttribute(ORIGEM, ""+avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo().getOrigem(),request);
			   getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacaoBemTributavelVo, request);
			   processFlow(VIEW_PESQUISAR_AVALIACAO, request, response, FORWARD);				
			}
			catch (RegistroNaoPodeSerUtilizadoException e)
			{
				avaliacaoBemTributavelVo.setMensagem(e.getMessage());
				request.setAttribute(ENTIDADE_VO, avaliacaoBemTributavelVo);
				processFlow(VIEW_ERRO, request, response, FORWARD);
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
	
	private void obterUnidadesFazendarias(AvaliacaoBemTributavelVo avaliacao) throws ConexaoException, 
			  ObjetoObrigatorioException, IntegracaoException
	{	
		if(!Validador.isObjetoValido(avaliacao))
		{
			avaliacao = new AvaliacaoBemTributavelVo();			
		}
		AvaliacaoBemTributavelBe avaliacaoBemTributavelBe = null;
		try
		{
			avaliacaoBemTributavelBe = new AvaliacaoBemTributavelBe();
		   avaliacaoBemTributavelBe.solicitarObterListaUnidadesFazendariasConsulta(avaliacao);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if(avaliacaoBemTributavelBe != null)
			{
			   avaliacaoBemTributavelBe.close();
			   avaliacaoBemTributavelBe = null;
			}
		}
	}
	
	private void listaGIAITCDPorResponsavel(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, ConexaoException, 
			  IntegracaoException, ParseException
	{
	   AvaliacaoBemTributavelVo avaliacaoBemTributavelVo = controladorInterativoJSP(request);
		Validador.isObjetoValido(avaliacaoBemTributavelVo);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
		   GIAITCDVo giaITCDVoRetorno = new GIAITCDVo();
			GIAITCDVo giaITCDVo = new GIAITCDVo(avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo());
			giaITCDVo.setConsultaCompleta(true);
			try
			{
				giaITCDVoRetorno = giaITCDBe.listarGIAITCDPorCPFResponsavelAvaliacao(giaITCDVo);
				avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo().setCollVO(giaITCDVoRetorno.getCollVO());   				
			   getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacaoBemTributavelVo, request);
			   processFlow(VIEW_PESQUISAR_AVALIACAO, request, response, FORWARD);         				
			}
			catch (ParametroObrigatorioException e)
			{
				request.setAttribute(EXCEPTION, e);
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
				processFlow(VIEW_PESQUISAR_AVALIACAO, request, response, FORWARD);
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

	private void solicitarTrocarPagina(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		AvaliacaoBemTributavelVo avaliacao = (AvaliacaoBemTributavelVo) getBuffer(request).getAttribute(AVALIACAO_BEM_VO);
		Validador.validaObjeto(avaliacao);
		avaliacao.getBemTributavel().getGiaITCDVo().getPaginador().setIrPagina(StringUtil.toInt(request.getParameter(PARAMETRO_TROCAR_PAGINA)));
		avaliacao.getBemTributavel().getGiaITCDVo().getPaginador().trocarPagina();
		getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacao, request);
		processFlow(VIEW_PESQUISAR_AVALIACAO, request, response, FORWARD);
	}	
}
