package br.gov.mt.sefaz.itc.modulo.generico.avaliacao;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ImpossivelCriptografarException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PDFBadElementException;
import br.com.abaco.util.exceptions.PDFDocumentException;
import br.com.abaco.util.exceptions.PDFFileNotFoundException;
import br.com.abaco.util.exceptions.PDFIOException;
import br.com.abaco.util.exceptions.PDFMalformedURLException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;
import br.com.abaco.util.pdf.InterfacePDF;

import br.gov.mt.sefaz.itc.model.generico.documentoarrecadacao.DocumentoArrecadacaoITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.Form;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.relatorio.DeclaracaoInsencaoPorValoresGerarPDF;
import br.gov.mt.sefaz.itc.model.relatorio.DeclaracaoNaoOcorrenciaFatoGerador;
import br.gov.mt.sefaz.itc.model.relatorio.GIAITCDEmitirNotificacaoGerarPDF;
import br.gov.mt.sefaz.itc.model.relatorio.RetificacaoGIAITCDDoacaoGerarPDF;
import br.gov.mt.sefaz.itc.model.relatorio.RetificacaoGIAITCDInventarioArrolamentoGerarPDF;
import br.gov.mt.sefaz.itc.model.relatorio.RetificacaoGIAITCDSeparacaoDivorcioGerarPDF;
import br.gov.mt.sefaz.itc.util.DarException;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnFuncionalidadeOrigem;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por imprimir Documento de Avaliação
 * @author João Batista Padilha e Silva
 * @version $Revision: 1.5 $
 */
public class AvaliacaoImprimir extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_CONTRIBUINTE = 2;
	private static final int REQUISICAO_SOLICITAR_IMPRIMIR_CONTRIBUINTE = 3;
	private static final int REQUISICAO_SOLICITAR_DETALHAR_GIAITCD = 4;
	private static final int REQUISICAO_IMPRIMIR_DOCUMENTO_AVALIACAO = 5;
	private static final int REQUISICAO_IMPRIMIR_DOCUMENTO_ARRECADACAO = 6;

	/**
	 * Método Inicial que redireciona para o método invocado por cada requisição
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws DarException
	 * @throws IOException
	 * @throws PDFFileNotFoundException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, ConexaoException, 
			  ParametroObrigatorioException, IntegracaoException, LogException, PersistenciaException, AnotacaoException, 
			  DarException, IOException, PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, 
			  PDFMalformedURLException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarImprimirContribuinte(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_DETALHAR_GIAITCD:
				{
					detalharGIAITCD(request, response);
					break;
				}
			case REQUISICAO_IMPRIMIR_DOCUMENTO_AVALIACAO:
				{
					imprimirDocumentoAvaliacao(request, response);
					break;
				}
			case REQUISICAO_IMPRIMIR_DOCUMENTO_ARRECADACAO:
				{
				   solicitaGerarDar(response,request);
					break;
				}
		}
	}

	/**
	 * Método que redireciona para a requisição invocada
	 * @param request
	 * @return
	 * @implemented by João Batista Padilha e Silva
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (request.getAttribute(GIAITCD_VO) != null)
		{
			return REQUISICAO_SOLICITAR_DETALHAR_GIAITCD;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_IMPRIMIR_DOCUMENTO_AVALIACAO)))
		{
			return REQUISICAO_IMPRIMIR_DOCUMENTO_AVALIACAO;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_IMPRIMIR_DOCUMENTO_ARRECADACAO)))
		{
			return REQUISICAO_IMPRIMIR_DOCUMENTO_ARRECADACAO;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método Controlador da Servlet. Faz o papel de setar os campos necessários da GIA
	 * @param request
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private GIAITCDVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		Validador.validaObjeto(giaITCDVo);
		// Teste de Tipo de GIa
		if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.DOACAO))
		{
			giaITCDVo = (GIAITCDDoacaoVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
		{
			giaITCDVo = (GIAITCDSeparacaoDivorcioVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
		{
			giaITCDVo = (GIAITCDInventarioArrolamentoVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		if (request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO) != null)
		{
			ContribuinteIntegracaoVo contribuinteIntegracaoVo = 
						(ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
			giaITCDVo.setResponsavelVo(contribuinteIntegracaoVo);
		}
		if (Validador.isStringValida(request.getParameter(Form.CAMPO_CODIGO_GIA)))
		{
			giaITCDVo.setCodigo(StringUtil.toLong(request.getParameter(CAMPO_CODIGO_GIA)));
		}
		giaITCDVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
		return giaITCDVo;
	}

	/**
	 * Metodo que inicia o processo de Consulta do Contribuinte
	 * Método Obsoleto - Verificar para retirar
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ParseException
	 * @throws ObjetoObrigatorioException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarConsultarContribuinte(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ParseException, ObjetoObrigatorioException
	{
		GIAITCDVo giaItcdVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
		//TODO processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_IMPRIMIR_AVALIACAO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	}

	/**
	 * Método que inicia o processo de pesquisa do contribuinte
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitarImprimirContribuinte(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
	   GIAITCDVo giaITCDVo = new GIAITCDVo();
	   obterInformacoesLogSefaz(request, giaITCDVo);
	   giaITCDVo.setOrigem(DomnFuncionalidadeOrigem.IMPRIMIR_DOCUMENTOS_AVALIACAO);
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	   request.setAttribute(PARAMETRO_CONSULTAR_IMPRIMIR_DOCUMENTOS_AVALIACAO, PARAMETRO_CONSULTAR_IMPRIMIR_DOCUMENTOS_AVALIACAO);   
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_IMPRIMIR_AVALIACAO_GIAITCD_PESQUISAR_GIAITCD, request), request, response, FORWARD);
	}

	/**
	 * Método que consulta o Contribuinte
	 * Método Obsoleto Verificar para retirar
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void consultarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException
	{
		GIAITCDVo giaItcdVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_STATUS_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	}

	/**
	 * Método para detalhar o contribuinte pesquisado pela Avaliação
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void detalharGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, ConexaoException, ParametroObrigatorioException, IntegracaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		request.setAttribute(GIAITCD_VO, giaITCDVo);
		processFlow(VIEW_IMPRIMIR_AVALIACAO, request, response, FORWARD);
	}

	/**
	 * Método que Decide qual documento Imprimir, fazendo testes em Inter Vivos e Causa Mortis
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws DarException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws PDFFileNotFoundException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @throws IOException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void imprimirDocumentoAvaliacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConsultaException, ConexaoException, 
			  IntegracaoException, 
			  DarException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, PDFFileNotFoundException, 
			  PDFIOException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException, IOException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDbe = null;
      
	   String ipClient = request.getHeader("CLIENTIP");
      String portClient = request.getHeader("CLIENTPORT");
      
		try
		{
			giaITCDbe = new GIAITCDBe();
			obterInformacoesLogSefaz(request, giaITCDVo);
			giaITCDbe.validaImprimirDocumentosAvaliacao(giaITCDVo);
			giaITCDVo.setStatusAnterior(giaITCDbe.obterStatusAnteriorGIAITCD(giaITCDVo));
		   getBuffer(request).setAttribute(GIAITCD_VO,giaITCDVo,request);
			if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO)
					|| giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
			{
               giaITCDbe.processarGeracaoDAR(giaITCDVo, ipClient, portClient);            
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (RegistroNaoPodeSerUtilizadoException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
			processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_IMPRIMIR_AVALIACAO_GIAITCD_PESQUISAR_GIAITCD, request), request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			request.setAttribute(EXCEPTION, e);
			processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_IMPRIMIR_AVALIACAO_GIAITCD_PESQUISAR_GIAITCD, request), request, response, FORWARD);
		}
		finally
		{
			if(giaITCDbe != null)
			{
			   giaITCDbe.close();
			   giaITCDbe = null;
			}
		}
		if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
		{
			if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR))
			{
				solicitaImprimirDocumentoFatoGerador(request, response, giaITCDVo);
			}
			else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO))
			{
				solicitaDeclaracaoIsensaoPorValores(request, response, giaITCDVo);
			}
			else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))
			{
				solicitaDocumentoNotificacao(request, response, giaITCDVo);
			}
			else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
			{
				solicitaDocumentoRetificacaoSeparacao(request, response, giaITCDVo);
			}
		}
		else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.DOACAO))
		{
			if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO))
			{
				solicitaDeclaracaoIsensaoPorValores(request, response, giaITCDVo);
			}
			else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))
			{
				solicitaDocumentoNotificacao(request, response, giaITCDVo);
			}
			else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
			{
				solicitaDocumentoRetificacaoDoacao(request, response, giaITCDVo);
			}
		}
		else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
		{
			if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO))
			{
				solicitaDeclaracaoIsensaoPorValores(request, response, giaITCDVo);
			}
		   if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO_IMPOSTO_ATE_1_UPF))
		   {
		      solicitaDeclaracaoIsensaoImpostoAteUmaUPF(request, response, giaITCDVo);
		   }
			else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))
			{
				solicitaDocumentoNotificacao(request, response, giaITCDVo);
			}
			else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
			{
				solicitaDocumentoRetificacaoInventarioArrolamento(request, response, giaITCDVo);
			}
		}
		
	}

	/**
	 * Método que solicita Imprimir o Documento de Não Ocorrência de Fato Gerador
	 * @param request
	 * @param response
	 * @param giaITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws PDFFileNotFoundException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @throws IOException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitaImprimirDocumentoFatoGerador(HttpServletRequest request, HttpServletResponse response, GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException, 
			  IOException
	{
		DeclaracaoNaoOcorrenciaFatoGerador relatorio = 
				 new DeclaracaoNaoOcorrenciaFatoGerador(request, giaITCDVo, InterfacePDF.PAGINA_A4_RETRATO);
		ByteArrayOutputStream baos = relatorio.gerarRelatorio();
	   response.setContentType(TIPO_PDF);
		response.getOutputStream().write(baos.toByteArray());
		response.getOutputStream().flush();
		response.getOutputStream().close();
		return;
	}

	/**
	 * Método que solicita Impressão de Declaração por Isenção de Valores
	 * @param request
	 * @param response
	 * @param giaITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws PDFFileNotFoundException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @throws IOException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitaDeclaracaoIsensaoPorValores(HttpServletRequest request, HttpServletResponse response, GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException, 
			  IOException
	{
		// Emite Relatório
		DeclaracaoInsencaoPorValoresGerarPDF relatorio = 
				 new DeclaracaoInsencaoPorValoresGerarPDF(request, giaITCDVo, InterfacePDF.PAGINA_A4_RETRATO);
		ByteArrayOutputStream baos = relatorio.gerarRelatorio();
	   response.setContentType(TIPO_PDF);
		response.getOutputStream().write(baos.toByteArray());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
   
   /**
    * 
    * @param request
    * @param response
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws PDFFileNotFoundException
    * @throws PDFIOException
    * @throws PDFDocumentException
    * @throws PDFBadElementException
    * @throws PDFMalformedURLException
    * @throws IOException
    */
   private void solicitaDeclaracaoIsensaoImpostoAteUmaUPF(HttpServletRequest request, HttpServletResponse response, GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
           PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException, 
           IOException
   {
      // Emite Relatório
      DeclaracaoInsencaoPorValoresGerarPDF relatorio = 
             new DeclaracaoInsencaoPorValoresGerarPDF(request, giaITCDVo, InterfacePDF.PAGINA_A4_RETRATO);
      ByteArrayOutputStream baos = relatorio.gerarRelatorio();
      response.setContentType(TIPO_PDF);
      response.getOutputStream().write(baos.toByteArray());
      response.getOutputStream().flush();
      response.getOutputStream().close();
   }

	/**
	 * Método que Solicita Imprimir Documento de Notificação
	 * @param request
	 * @param response
	 * @param giaITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws PDFFileNotFoundException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @throws IOException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitaDocumentoNotificacao(HttpServletRequest request, HttpServletResponse response, GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException, 
			  IOException
	{
		// Emite Relatório
		GIAITCDEmitirNotificacaoGerarPDF relatorio = 
				 new GIAITCDEmitirNotificacaoGerarPDF(request, giaITCDVo, InterfacePDF.PAGINA_A4_RETRATO);
		ByteArrayOutputStream baos = relatorio.gerarRelatorio();
	   response.setContentType(TIPO_PDF);
		response.getOutputStream().write(baos.toByteArray());
		response.getOutputStream().flush();
		response.getOutputStream().close();
		return;
	}

	/**
	 * Método que muda o Status da Gia
	 * @param giaITCDVo
	 * @param dominio
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ImpossivelCriptografarException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void mudaStatusGIAITCD(GIAITCDVo giaITCDVo, int dominio) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException, IntegracaoException, LogException, 
			  AnotacaoException, PersistenciaException, ImpossivelCriptografarException, RegistroNaoPodeSerUtilizadoException, 
			  RegistroExistenteException
	{
		// Muda a situação da GIA para Isento
		giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(dominio));
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			giaITCDBe.manterGIAITCD(giaITCDVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
	   catch (IOException e)
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

	/**
	 * Método que solicita Emitir Documento de Retificação Doação
	 * @param request
	 * @param response
	 * @param giaITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws PDFFileNotFoundException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @throws IOException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitaDocumentoRetificacaoDoacao(HttpServletRequest request, HttpServletResponse response, GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException, 
			  IOException
	{
		// Emite Relatório
		RetificacaoGIAITCDDoacaoGerarPDF relatorio = 
				 new RetificacaoGIAITCDDoacaoGerarPDF(request, ((GIAITCDDoacaoVo) giaITCDVo), InterfacePDF.PAGINA_A4_RETRATO);
		ByteArrayOutputStream baos = relatorio.gerarRelatorio();
	   response.setContentType(TIPO_PDF);
		response.getOutputStream().write(baos.toByteArray());
		response.getOutputStream().flush();
		response.getOutputStream().close();
		return;
	}

	/**
	 * Método que solicita Emitir Documento de Retificação Separação Divorcio
	 * @param request
	 * @param response
	 * @param giaITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws PDFFileNotFoundException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @throws IOException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitaDocumentoRetificacaoSeparacao(HttpServletRequest request, HttpServletResponse response, GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException, 
			  IOException
	{
		// Emite Relatório
		RetificacaoGIAITCDSeparacaoDivorcioGerarPDF relatorio = 
				 new RetificacaoGIAITCDSeparacaoDivorcioGerarPDF(request, ((GIAITCDSeparacaoDivorcioVo) giaITCDVo), InterfacePDF.PAGINA_A4_RETRATO);
		ByteArrayOutputStream baos = relatorio.gerarRelatorio();
	   response.setContentType(TIPO_PDF);
		response.getOutputStream().write(baos.toByteArray());
		response.getOutputStream().flush();
		response.getOutputStream().close();
		return;
	}

	/**
	 * @param request
	 * @param response
	 * @param giaITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws PDFFileNotFoundException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @throws IOException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaDocumentoRetificacaoInventarioArrolamento(HttpServletRequest request, HttpServletResponse response, GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException, 
			  IOException
	{
		// Emite Relatório
		RetificacaoGIAITCDInventarioArrolamentoGerarPDF relatorio = 
				 new RetificacaoGIAITCDInventarioArrolamentoGerarPDF(request, ((GIAITCDInventarioArrolamentoVo) giaITCDVo), InterfacePDF.PAGINA_A4_RETRATO);
		ByteArrayOutputStream baos = relatorio.gerarRelatorio();
	   response.setContentType(TIPO_PDF);
		response.getOutputStream().write(baos.toByteArray());
		response.getOutputStream().flush();
		response.getOutputStream().close();
		return;
	}

	/**
	 * @param response
	 * @param request
	 * @throws DarException
	 * @throws ConexaoException
	 * @throws ObjetoObrigatorioException
	 * @throws IOException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaGerarDar(HttpServletResponse response, HttpServletRequest request) throws DarException, 
			  ConexaoException, ObjetoObrigatorioException, IOException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDBe = null;
		DocumentoArrecadacaoITCDBe documentoArrecadacaoITCDBe = null;
      
	   String ipClient = request.getHeader("CLIENTIP");
	   String portClient = request.getHeader("CLIENTPORT");
      
		try
		{
		   giaITCDBe = new GIAITCDBe();
		   if ((giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))||
				 (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO)))
			{
				giaITCDBe.gerarDAR(giaITCDVo,ipClient,portClient);
				String dar = giaITCDBe.imprimirDar(giaITCDVo).toString();
			   response.getWriter().write(dar);
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
