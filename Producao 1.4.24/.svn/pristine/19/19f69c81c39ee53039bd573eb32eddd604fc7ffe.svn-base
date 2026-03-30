package br.gov.mt.sefaz.itc.modulo.generico.giaitcd;

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
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.exceptions.TituloNaoInformadoException;
import br.com.abaco.util.http.AbstractAbacoServlet;
import br.com.abaco.util.pdf.InterfacePDF;

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
import br.gov.mt.sefaz.itc.util.FormAcesso;
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


public class GIAITCDPesquisarReimprimirNotificacaoRetificacao extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_CONSULTAR_GIA_ITCD = 2;
	private static final int REQUISICAO_SOLICITAR_MOSTRAR_CONTRIBUINTE = 3;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_CONTRIBUINTE = 4;
	private static final int REQUISICAO_SOLICITAR_REIMPRIMIR_NOTIFICACAO_RETIFICACAO = 5;
	private int TIPO_GIA_PDF = 6;
	private static final int REQUISICAO_GERAR_PDF = 7;

	/**
	 * Este método é responsável pela análise dos parâmetros e a
	 * tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO) != null)
		{
			return REQUISICAO_SOLICITAR_MOSTRAR_CONTRIBUINTE;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR)))
		{
			return REQUISICAO_CONSULTAR_GIA_ITCD;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_CONTRIBUINTE)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_CONTRIBUINTE;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_REIMPRIMIR_NOTIFICACAO_RETIFICACAO)))
		{
			return REQUISICAO_SOLICITAR_REIMPRIMIR_NOTIFICACAO_RETIFICACAO;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_REIMPRIMIR)))
		{
			return REQUISICAO_GERAR_PDF;
		}
	   else if(Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_CONTRIBUINTE)))
	   {
	      return REQUISICAO_SOLICITAR_CONSULTAR_CONTRIBUINTE;
	   }		
		return REQUISICAO_VAZIA;
	}

	/**
	 * Este método trabalha colaborativamente com o método
	 * redirecionar. O método redirecionar determina a ação a ser tomada e
	 * processoRequest a executa.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 * **/
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, ConexaoException, 
			  ParametroObrigatorioException, IntegracaoException, ParseException, PDFIOException, PDFFileNotFoundException, 
			  PDFDocumentException, PDFBadElementException, PDFMalformedURLException, TituloNaoInformadoException, 
			  ImpossivelCriptografarException, CloneNotSupportedException, IOException, 
                                                                                                  LogException, 
                                                                                                  AnotacaoException, 
                                                                                                  PersistenciaException
   {
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					removeBuffer(request);
					processFlow(VIEW_PESQUISAR_REIMPRIMIR_NOTIFICACAO_REFITICACAO_GIA_ITCD, request, response, FORWARD);
					break;
				}
			case REQUISICAO_CONSULTAR_GIA_ITCD:
				{
					solicitarPesquisarGIAITCD(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_CONTRIBUINTE:
				{
					solictarConsultarContribuinte(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_MOSTRAR_CONTRIBUINTE:
				{
					mostraDadosContribuinte(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_REIMPRIMIR_NOTIFICACAO_RETIFICACAO:
				{
					processFlow(VIEW_PESQUISAR_REIMPRIMIR_NOTIFICACAO_REFITICACAO_GIA_ITCD, request, response, FORWARD);
					break;
				}
			case REQUISICAO_GERAR_PDF:
				{
					gerarPDF(request, response);
					break;
				}
		}
	}

	/**
	 * Método responsável por recuperar do buffer os valores (CPF, NOME) informados pelo usuário.
	 * @implemented by Rogério Sanches de Oliveira
	 *
	 **/
	private void mostraDadosContribuinte(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException
	{
		removeBuffer(request);
		GIAITCDVo giaItcdVo = controladorInterativoJSP(request);
		ContribuinteIntegracaoVo responsavelVo = 
				 (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
		giaItcdVo.setResponsavelVo(responsavelVo);
		getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
		processFlow(VIEW_PESQUISAR_REIMPRIMIR_NOTIFICACAO_REFITICACAO_GIA_ITCD, request, response, FORWARD);
	}

	/**
	 * Consulta de Contribuinte através do CPF ou CNPJ.
	 * @implemented by Rogério Sanches de Oliveira
	 * 
	 **/
	private void solictarConsultarContribuinte(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ParseException, ObjetoObrigatorioException
	{
		GIAITCDVo giaItcdVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_REIMPRIMIR_NOTIFICACAO_RETIFICACAO_GIAITCD_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private GIAITCDVo controladorInterativoJSP(HttpServletRequest request) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException
	{
		GIAITCDVo giaItcdVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		if (giaItcdVo == null)
		{
			giaItcdVo = new GIAITCDVo();
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_STATUS)))
		{
			int dominioStatusGIAITCD = (StringUtil.toInt(request.getParameter(Form.CAMPO_SELECT_STATUS)));
			giaItcdVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(dominioStatusGIAITCD));
		}
		if(Validador.isStringValida(request.getParameter(CAMPO_CODIGO_GIA)))
		{
		   giaItcdVo.setCodigo(Long.parseLong(request.getParameter(CAMPO_CODIGO_GIA)));
		}
		return giaItcdVo;
	}

	/**
	 * Método responsável por realizar uma consulta na GIAITCD identificando seu tipo de GIA e preparando os dados 
	 * para ser representado no relatório.
	 * @param request, response
	 * @implemented by Rogério Sanches de Oliveira
	 * **/
	private void solicitarPesquisarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, ConexaoException, 
			  IntegracaoException, ImpossivelCriptografarException, 
                                                                                                           LogException, 
                                                                                                           AnotacaoException, 
                                                                                                           PersistenciaException
   {
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		GIAITCDVo giaITCDVoRetorno = new GIAITCDVo();
		Validador.isObjetoValido(giaITCDVo);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDVoRetorno = new GIAITCDVo(giaITCDVo);
			obterInformacoesLogSefaz(request, giaITCDVoRetorno);			
			giaITCDVoRetorno.setConsultaCompleta(true);
			giaITCDBe = new GIAITCDBe();
			if (giaITCDVo.getStatusVo().isStatusIn(new int[]{DomnStatusGIAITCD.NOTIFICADO, DomnStatusGIAITCD.NOTIFICADO_CIENTE}) )
			{
			   giaITCDVoRetorno = giaITCDBe.consultarGIAITCDNotificada(giaITCDVoRetorno);
			} 
			else if (giaITCDVo.getStatusVo().isStatusIn(new int[]{DomnStatusGIAITCD.RETIFICADO, DomnStatusGIAITCD.RETIFICADO_CIENTE}) )
		   {
            giaITCDVoRetorno = giaITCDBe.consultarGIAITCDRetificada(giaITCDVoRetorno);
         }       
		   giaITCDVoRetorno.getStatusAnterior().setStatusGIAITCD( giaITCDVo.getStatusVo().getStatusGIAITCD() );
		   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
		   processFlow(VIEW_RESULTADO_CONSULTA_REIMPRIMIR_NOTIFICACAO_REFITICACAO_GIA_ITCD, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
		   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			processFlow(VIEW_PESQUISAR_REIMPRIMIR_NOTIFICACAO_REFITICACAO_GIA_ITCD, request, response, FORWARD);
		}
	   catch (IOException e)
	   {
	         request.setAttribute(EXCEPTION, e);
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_PESQUISAR_REIMPRIMIR_NOTIFICACAO_REFITICACAO_GIA_ITCD, request, response, FORWARD);
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
	 * Método utilizado para gerar o PDF de impressão da GIA tipo Inventário Arrolamento
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PDFIOException
	 * @throws PDFFileNotFoundException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @throws TituloNaoInformadoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void gerarPDFNotificacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException, 
			  IOException
	{
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		Validador.validaObjeto(giaITCDVo);
		response.setContentType(TIPO_PDF);
		GIAITCDEmitirNotificacaoGerarPDF relatorio = 
				 new GIAITCDEmitirNotificacaoGerarPDF(request, giaITCDVo, InterfacePDF.PAGINA_A4_RETRATO);
		ByteArrayOutputStream baos = relatorio.gerarRelatorio();
		response.getOutputStream().write(baos.toByteArray());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	private void gerarPDFIsencaoValores(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, 
			  PDFMalformedURLException, 
			  IOException
	{
	   GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
	   Validador.validaObjeto(giaITCDVo);
	   response.setContentType(TIPO_PDF);
	   DeclaracaoInsencaoPorValoresGerarPDF relatorio = 
	          new DeclaracaoInsencaoPorValoresGerarPDF(request, giaITCDVo, InterfacePDF.PAGINA_A4_RETRATO);
	   ByteArrayOutputStream baos = relatorio.gerarRelatorio();
	   response.getOutputStream().write(baos.toByteArray());
	   response.getOutputStream().flush();
	   response.getOutputStream().close();
	}

	private void gerarPDFNaoOcorrenciaFatoGerador(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, 
			  PDFMalformedURLException, 
			  IOException
	{
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		Validador.validaObjeto(giaITCDVo);
		response.setContentType(TIPO_PDF);
		DeclaracaoNaoOcorrenciaFatoGerador relatorio = 
				 new DeclaracaoNaoOcorrenciaFatoGerador(request, giaITCDVo, InterfacePDF.PAGINA_A4_RETRATO);
		ByteArrayOutputStream baos = relatorio.gerarRelatorio();
		response.getOutputStream().write(baos.toByteArray());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}	

	/**
	 * Método utilizado para gerar o PDF de impressão da GIA tipo Inventário Arrolamento
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PDFIOException
	 * @throws PDFFileNotFoundException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @throws TituloNaoInformadoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void gerarPDFRetificacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PDFIOException, PDFFileNotFoundException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException, 
			  TituloNaoInformadoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaItcdVo = controladorInterativoJSP(request);
		try
		{
			DomnTipoProcesso dominio = new DomnTipoProcesso();
			if (giaItcdVo.getNaturezaOperacaoVo().getTipoProcesso().getDomnValr() == DomnTipoProcesso.DOACAO)
			{
				GIAITCDDoacaoVo gIAITCDDoacaoVo = (GIAITCDDoacaoVo) getBuffer(request).getAttribute(GIAITCD_VO);
				Validador.validaObjeto(gIAITCDDoacaoVo);
				response.setContentType(TIPO_PDF);
				RetificacaoGIAITCDDoacaoGerarPDF relatorio = 
							  new RetificacaoGIAITCDDoacaoGerarPDF(request, gIAITCDDoacaoVo, InterfacePDF.PAGINA_A4_RETRATO);
				ByteArrayOutputStream baos = relatorio.gerarRelatorio();
				response.getOutputStream().write(baos.toByteArray());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
			if (giaItcdVo.getNaturezaOperacaoVo().getTipoProcesso().getDomnValr() == DomnTipoProcesso.INVENTARIO_ARROLAMENTO)
			{
				GIAITCDInventarioArrolamentoVo giaItcdInventarioArrolamentoVo = 
							  (GIAITCDInventarioArrolamentoVo) getBuffer(request).getAttribute(GIAITCD_VO);
				Validador.validaObjeto(giaItcdInventarioArrolamentoVo);
				response.setContentType(TIPO_PDF);
				RetificacaoGIAITCDInventarioArrolamentoGerarPDF relatorio = 
							  new RetificacaoGIAITCDInventarioArrolamentoGerarPDF(request, giaItcdInventarioArrolamentoVo, InterfacePDF.PAGINA_A4_RETRATO);
				ByteArrayOutputStream baos = relatorio.gerarRelatorio();
				response.getOutputStream().write(baos.toByteArray());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
			if (giaItcdVo.getNaturezaOperacaoVo().getTipoProcesso().getDomnValr() == DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA)
			{
				GIAITCDSeparacaoDivorcioVo gIAITCDSeparacaoDivorcioVo = 
							  (GIAITCDSeparacaoDivorcioVo) getBuffer(request).getAttribute(GIAITCD_VO);
				Validador.validaObjeto(gIAITCDSeparacaoDivorcioVo);
				response.setContentType(TIPO_PDF);
				//TODO Aguardando implementação do Wendell, pois o mesmo está terminando a implementação. 
				RetificacaoGIAITCDSeparacaoDivorcioGerarPDF relatorio = 
							  new RetificacaoGIAITCDSeparacaoDivorcioGerarPDF(request, gIAITCDSeparacaoDivorcioVo, InterfacePDF.PAGINA_A4_RETRATO);
				ByteArrayOutputStream baos = relatorio.gerarRelatorio();
				response.getOutputStream().write(baos.toByteArray());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		}
		catch (IOException io)
		{
			io.printStackTrace();
			throw new PDFIOException();
		}
	}

	/**
	 * Método utilizado para identificar o tipo de relatorio RETIFICADO OU NOTIFICADO para gerar o PDF de impressão da GIA 
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PDFIOException
	 * @throws PDFFileNotFoundException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @throws TituloNaoInformadoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void gerarPDF(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PDFIOException, PDFFileNotFoundException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException, 
			  TituloNaoInformadoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  IOException, ConexaoException, ConsultaException, IntegracaoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);	
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
		   giaITCDBe.solicitarVerificarStatusAnteriorIgualRetificado(giaITCDVo);
		   giaITCDBe.gerarDemonstrativoCalculo(giaITCDVo);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
         if (giaITCDVo.getStatusAnterior().getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))
		   {
		      gerarPDFNotificacao(request, response);
		   }	
         else if (giaITCDVo.getStatusAnterior().getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
		   {	 
		      if (giaITCDVo.getStatusVo().isStatusInCollVo(new int[]{DomnStatusGIAITCD.ISENTO}) && giaITCDVo.isGiaRetificada())
		      {        
               gerarPDFIsencaoValores(request, response);
            }
            else if(giaITCDVo.getStatusVo().isStatusInCollVo(new int[]{DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR}) && giaITCDVo.isGiaRetificada())
            {
               gerarPDFNaoOcorrenciaFatoGerador(request, response);
            }		      
            else
		      {           
		         gerarPDFRetificacao(request, response);
		      }
		   }
		}
	   catch(ParametroObrigatorioException e)
	   {
	      throw new ObjetoObrigatorioException();
	   }
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);			
		}
		finally
		{
			if(giaITCDBe != null)
			{
				giaITCDBe.close();
				giaITCDBe = null;
			}
		}
	}
}
