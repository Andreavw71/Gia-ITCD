package br.gov.mt.sefaz.itc.modulo.giaitcd.giaitcdautenticidade;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PDFBadElementException;
import br.com.abaco.util.exceptions.PDFDocumentException;
import br.com.abaco.util.exceptions.PDFFileNotFoundException;
import br.com.abaco.util.exceptions.PDFIOException;
import br.com.abaco.util.exceptions.PDFMalformedURLException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;
import br.com.abaco.util.pdf.InterfacePDF;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.Form;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.relatorio.GIAITCDAutenticidadeGerarPDF;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnAvaliacao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.http.HttpUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**Servlet de Controle de Fluxo da funcionalidade Imprimir Autenticidade.
 * @author Elizabeth Barbosa Moreira
 * @version $Revision: 
 */
public class GIAITCDAutenticidadeImprimir extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_IMPRIMIR_AUTENTICIDADE = 2;
	private static final int REQUISICAO_GERAR_PDF_AUTENTICIDADE = 3;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_AUTENTICIDADE = 4;

	/**
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @throws PDFFileNotFoundException
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, PDFFileNotFoundException, PDFIOException, 
			  PDFDocumentException, PDFBadElementException, PDFMalformedURLException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarImprimirAutenticidade(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_AUTENTICIDADE:
				{
					solicitarConsultarAutenticidade(request, response);
					break;
				}
			case REQUISICAO_IMPRIMIR_AUTENTICIDADE:
				{
					imprimirAutenticidade(request, response);
					break;
				}
			case REQUISICAO_GERAR_PDF_AUTENTICIDADE:
				{
					gerarPDF(request, response);
					break;
				}
		}
	}

	/**Metodo utilizado por redirenciona as funcionalidades.
	 * @param request
	 * @return
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida((String) request.getAttribute(PARAMETRO_IMPRIMIR_AUTENTICIDADE_GIA_ITCD)))
		{
			return REQUISICAO_IMPRIMIR_AUTENTICIDADE;
		}
		else if (Validador.isStringValida(request.getParameter(Form.BOTAO_PESQUISAR)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_AUTENTICIDADE;
		}
		else if (Validador.isStringValida(request.getParameter(Form.BOTAO_IMPRIMIR)))
		{
			return REQUISICAO_GERAR_PDF_AUTENTICIDADE;
		}
		else
		{
			return REQUISICAO_VAZIA;
		}
	}

	/**Controlador interativo, responsavel por preencher o objeto atual
	 * @param request
	 * @return
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private GIAITCDVo controladorInterativoJSP(HttpServletRequest request)
	{
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		if (giaITCDVo == null)
		{
			giaITCDVo = new GIAITCDVo();
		}
		int codigoGIAITCD = StringUtil.toInt(request.getParameter(PARAMETRO_CODIGO_GIA));
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_GIA)))
		{
			giaITCDVo.setCodigo(codigoGIAITCD);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_CODIGO_AUTENTICIDADE)))
		{
			giaITCDVo.setCodigoAutenticidade(request.getParameter(CAMPO_CODIGO_AUTENTICIDADE).toUpperCase());
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_DECLARACAO_DE_ISENCAO_DE_VALORES)))
		{
			giaITCDVo.setNumrDeclaracaoIsencao(StringUtil.toLong(request.getParameter(CAMPO_SELECT_DECLARACAO_DE_ISENCAO_DE_VALORES)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_DECLARACAO_DE_NAO_OCORRENCIA_DE_FATO_GERADOR)))
		{
			giaITCDVo.setNumrDeclaracaoFatoGerador(StringUtil.toLong(request.getParameter(CAMPO_SELECT_DECLARACAO_DE_NAO_OCORRENCIA_DE_FATO_GERADOR)));
		}
		return giaITCDVo;
	}

	/**Metodo responsavel por redirecionar para View pesquisar Autenticidade
	 * @param request
	 * @return
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private void solicitarImprimirAutenticidade(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
	   GIAITCDVo giaITCDVo = new GIAITCDVo();
		obterInformacoesLogSefaz( request, giaITCDVo );		
		getBuffer(request).setAttribute( GIAITCD_VO, giaITCDVo, request );
		processFlow(VIEW_PESQUISAR_GIAITCD_AUTENTICIDADE, request, response, FORWARD);
	}

	/**Metodo responsavel por imprimir Autenticidade
	 * @param request
	 * @return
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private void imprimirAutenticidade(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException
	{
		//Adicionando o tipo da pesquisa e o parametro utilizado para efetuar a pesquisa.
		//Validar o  VO
		GIAITCDVo giaITCDVo = (GIAITCDVo) request.getAttribute(GIAITCD_VO);
		Validador.validaObjeto(giaITCDVo);
		//GIAITCDBe.validaAutenticidadeImprimir(giaITCDVo);
		giaITCDVo.getCodigoAutenticidade();
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		processFlow(VIEW_IMPRIMIR_GIAITCD_AUTENTICIDADE, request, response, FORWARD);
	}

	/**Metodo responsavel por imprimir Autenticidade
	 * @param request
	 * @return
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private void gerarPDF(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, 
			  PDFMalformedURLException
	{
		try
		{
			GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
			Validador.validaObjeto(giaITCDVo);
			response.setContentType(TIPO_PDF);
			//redireciona para o PDF responsável por gerar o relatório
			GIAITCDAutenticidadeGerarPDF relatorio = 
						new GIAITCDAutenticidadeGerarPDF(request, giaITCDVo, InterfacePDF.PAGINA_A4_RETRATO);
			ByteArrayOutputStream baos;
			baos = relatorio.gerarRelatorio();
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		catch (IOException e)
		{
			throw new PDFIOException();
		}
	}

	/**Metodo responsavel por consultar  Autenticidade
	 * @param request
	 * @return
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private void solicitarConsultarAutenticidade(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		GIAITCDVo giaITCDVoConsulta = new GIAITCDVo();
	   //verifica se o código da autenticidade  é valido
	   if (!Validador.isStringValida((giaITCDVo.getCodigoAutenticidade())))
	   {
	      request.setAttribute(EXCEPTION, new Exception("Código de autenticidade deve ser informado."));
	      removeBuffer(request);
	      processFlow(VIEW_PESQUISAR_GIAITCD_AUTENTICIDADE, request, response, FORWARD);
	   }		
	   if(Validador.isNumericoValido(giaITCDVo.getCodigo()))
		{
			giaITCDVoConsulta.setCodigo(giaITCDVo.getCodigo());
		}
		if(Validador.isNumericoValido(giaITCDVo.getNumrDeclaracaoIsencao()))
		{
			giaITCDVoConsulta.setNumrDeclaracaoIsencao(giaITCDVo.getNumrDeclaracaoIsencao());
		}
		if(Validador.isNumericoValido(giaITCDVo.getNumrDeclaracaoFatoGerador()))
		{
			giaITCDVoConsulta.setNumrDeclaracaoFatoGerador(giaITCDVo.getNumrDeclaracaoFatoGerador());
		}				
		try
		{			  
		   HttpUtil.validarCaracteresInformadosModAberto(getBuffer(request) , request.getParameter(Form.CAMPO_IMAGEM), giaITCDVo.getLogSefazVo());
		   //redireciona para servelet Autenticidade Pesquisar
		   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoConsulta, request);
		   processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_IMPRIMIR_AUTENTICIDADE_PESQUISAR_GIAITCD, request), request, response, INCLUDE);
		   //transfere os dados da consulta da GIA-ITCD e da Autenticidade do Buffer para o  Vo
		   giaITCDVoConsulta = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		   Validador.validaObjeto( giaITCDVoConsulta );
		   obterInformacoesLogSefaz( request, giaITCDVoConsulta );			
			int status = StringUtil.toInt(request.getParameter(CAMPO_SELECT_STATUS));
			giaITCDVo.getNaturezaOperacaoVo().setTipoProcesso( new DomnTipoProcesso( status ) );
			//verifica se o numero da declaração de isenção de valores do banco confera com o numero que o usuário digitou
			if (status == DomnAvaliacao.CODIGO_ISENCAO_VALORES)
			{
				if (!Validador.isNumericoValido(giaITCDVoConsulta.getCodigo()))
				{
					request.setAttribute(EXCEPTION, new Exception("Numero da GIA-ITCD/Declaração de Isenção Informado não existe."));
					getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);     
					processFlow(VIEW_PESQUISAR_GIAITCD_AUTENTICIDADE, request, response, FORWARD);
				}
			}
			//verifica se o numero da declaração de não ocorrencia do banco confera com o numero que o usuário digitou
			else if (status == DomnAvaliacao.CODIGO_OCORRENCIA_FATO_GERADOR)
			{
				if (!Validador.isNumericoValido(giaITCDVoConsulta.getCodigo()))		
				{
					request.setAttribute(EXCEPTION, new Exception("Numero da GIA-ITCD/Declaração de não ocorrência de fator gerador  Informado não existe."));
					getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);     
					processFlow(VIEW_PESQUISAR_GIAITCD_AUTENTICIDADE, request, response, FORWARD);
				}
			}
			//verifica se o código da autenticidade  do banco confera com o codigo que o usuário digitou
			if (!giaITCDVo.getCodigoAutenticidade().equals(giaITCDVoConsulta.getCodigoAutenticidade()))
			{
				request.setAttribute(EXCEPTION, new Exception("Código de autenticidade inválido."));
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);     
				processFlow(VIEW_PESQUISAR_GIAITCD_AUTENTICIDADE, request, response, FORWARD);
			}
			//verifica se a GIA-ITCD  é valida
			if (giaITCDVoConsulta.getGiaConfirmada().is(DomnSimNao.NAO))
			{
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_GIA_CONFIRMADA));
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);     
				processFlow(VIEW_PESQUISAR_GIAITCD_AUTENTICIDADE, request, response, FORWARD);
			}
	      //redireciona para a View
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoConsulta, request);         
	      processFlow(VIEW_IMPRIMIR_GIAITCD_AUTENTICIDADE, request, response, FORWARD);
	   }
	   catch (ParametroObrigatorioException e)
	   {                 
	      request.setAttribute( EXCEPTION, e );
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	      processFlow(VIEW_PESQUISAR_GIAITCD_AUTENTICIDADE, request, response, FORWARD);
	   }
	}
}
