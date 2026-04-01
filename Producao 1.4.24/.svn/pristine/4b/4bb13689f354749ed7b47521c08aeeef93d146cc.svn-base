/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDInventarioArrolamentoPesquisar.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 19/11/2007
 */
package br.gov.mt.sefaz.itc.modulo.giaitcd.giaitcdinventarioarrolamento;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
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

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.Form;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.relatorio.DeclaracaoInsencaoPorValoresGerarPDF;
import br.gov.mt.sefaz.itc.util.DarException;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Classe responsavel por receber  a requisição via request e enviar para a jsp responsável, do módulo GIAITCD tipo de processo InventarioArrolamento
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.3 $
 */
public class GIAITCDInventarioArrolamentoPesquisar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_SOLICITAR_ABA_DADOS_GERAIS = 2;
	private static final int REQUISICAO_SOLICITAR_ABA_BENS_TRIBUTAVEIS = 3;
	private static final int REQUISICAO_SOLICITAR_ABA_BENEFICIARIOS = 4;
	private static final int REQUISICAO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO = 5;
	private static final int REQUISICAO_SOLICITAR_ABA_ACOMPANHAMENTO = 6;
	private static final int REQUISICAO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_URBANO = 7;
	private static final int REQUISICAO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_RURAL = 8;
	private static final int REQUISICAO_GERAR_DAR = 9;
	private static final int REQUISICAO_IMPRIMIR_DECLARACAO_ISENCAO = 10;
	private static final int REQUISICAO_GERAR_DAR_MOD_ABERTO = 11;
	private static final int REQUISICAO_SOLICITAR_ABA_FORMA_PAGAMENTO = 12;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, ConexaoException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitaConsultarGIAITCDInventarioArrolamento(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ABA_DADOS_GERAIS:
				{
					solicitaAbaDadosGerais(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ABA_BENS_TRIBUTAVEIS:
				{
					solicitaAbaBensTributaveis(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ABA_BENEFICIARIOS:
				{
					solicitaAbaBeneficiarios(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO:
				{
					solicitaAbaDemonstrativoDeCalculo(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ABA_ACOMPANHAMENTO:
				{
					solicitaAbaAcompanhamento(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_URBANO:
				{
					solicitaAbaImovelUrbano(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_RURAL:
				{
					solicitaAbaImovelRural(request, response);
					break;
				}
			case REQUISICAO_GERAR_DAR:
				{
					solicitaGerarDAR(request, response);
					break;
				}
		   case REQUISICAO_GERAR_DAR_MOD_ABERTO:
		      {
		         solicitaGerarDARModAberto(request, response);
		         break;
		      }
			case REQUISICAO_IMPRIMIR_DECLARACAO_ISENCAO:
				{
					solicitaImprimirDeclaracaoIsencao(request, response);
					break;
				}		   
		}
	}

	/**
	 * Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_DADOS_GERAIS)))
		{
			return REQUISICAO_SOLICITAR_ABA_DADOS_GERAIS;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_BENS_TRIBUTAVEIS)))
		{
			return REQUISICAO_SOLICITAR_ABA_BENS_TRIBUTAVEIS;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_BENEFICIARIOS)))
		{
			return REQUISICAO_SOLICITAR_ABA_BENEFICIARIOS;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO)))
		{
			return REQUISICAO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_ACOMPANHAMENTO)))
		{
			return REQUISICAO_SOLICITAR_ABA_ACOMPANHAMENTO;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_URBANO)))
		{
			return REQUISICAO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_URBANO;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_RURAL)))
		{
			return REQUISICAO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_RURAL;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_IMPRIMIR_DAR)))
		{
			return REQUISICAO_GERAR_DAR;
		}
	    if (Validador.isStringValida(request.getParameter(BOTAO_IMPRIMIR_DAR_MOD_ABERTO)))
        {
			return REQUISICAO_GERAR_DAR_MOD_ABERTO;
        }
		if (Validador.isStringValida(request.getParameter(BOTAO_IMPRIMIR_DECLARACAO_ISENCAO)))
		{
			return REQUISICAO_IMPRIMIR_DECLARACAO_ISENCAO;
		}
	    if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_FORMA_PAGAMENTO)))
	    {
	        return REQUISICAO_SOLICITAR_ABA_FORMA_PAGAMENTO;
	    }
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por solicitar a consulta da GIAITCD Inventário/Arrolamento.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaConsultarGIAITCDInventarioArrolamento(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException
	{
		GIAITCDInventarioArrolamentoVo gIAITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		GIAITCDInventarioArrolamentoBe.restaurarQuantidadeBeneficiario(gIAITCDInventarioArrolamentoVo);
		getBuffer(request).setAttribute(GIAITCD_VO, gIAITCDInventarioArrolamentoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, Form.ABA_DADOS_GERAIS, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/**  
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaDadosGerais(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException
	{
		GIAITCDInventarioArrolamentoVo gIAITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, gIAITCDInventarioArrolamentoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/**  
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaBensTributaveis(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException
	{
		GIAITCDInventarioArrolamentoVo gIAITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, gIAITCDInventarioArrolamentoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/**  
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException
	{
		GIAITCDInventarioArrolamentoVo gIAITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, gIAITCDInventarioArrolamentoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/** 
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaDemonstrativoDeCalculo(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException
	{
		GIAITCDInventarioArrolamentoVo gIAITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, gIAITCDInventarioArrolamentoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/**  
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaAcompanhamento(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException
	{
		GIAITCDInventarioArrolamentoVo gIAITCDInventarioArrolamentoVo = controladorInterativoJSP(request);		
		getBuffer(request).setAttribute(GIAITCD_VO, gIAITCDInventarioArrolamentoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_ACOMPANHAMENTO, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/**
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaImovelRural(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, ConexaoException
	{
		GIAITCDInventarioArrolamentoVo gIAITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		// Pega o Indice do Bem que deseja Detalhar
		int indice = 
				 StringUtil.toInt(request.getParameter(PARAMETRO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_RURAL));
		FichaImovelRuralVo fichaImovelRuralVo = new FichaImovelRuralVo();
		BemTributavelVo bemTributavelVo = 
				 (BemTributavelVo) ((ArrayList) gIAITCDInventarioArrolamentoVo.getBemTributavelVo().getCollVO()).get(indice);
             
		fichaImovelRuralVo.setBemTributavelVo(bemTributavelVo);
	   fichaImovelRuralVo.setNumeroVersaoGIAITCD(gIAITCDInventarioArrolamentoVo.getNumeroVersaoGIAITCD());
      
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		getBuffer(request).setAttribute(GIAITCD_VO, gIAITCDInventarioArrolamentoVo, request);
		processFlow(VIEW_PESQUISAR_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaImovelUrbano(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, ConexaoException
	{
		GIAITCDInventarioArrolamentoVo gIAITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		// Pega o Indice do Bem que deseja Detalhar
      FichaImovelUrbanoVo fichaImovelUrbanoVo = new FichaImovelUrbanoVo();
		int indice = 
				 StringUtil.toInt(request.getParameter(PARAMETRO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_URBANO));
		BemTributavelVo bemTributavelVo = 
				 (BemTributavelVo) ((ArrayList) gIAITCDInventarioArrolamentoVo.getBemTributavelVo().getCollVO()).get(indice);
             
	   fichaImovelUrbanoVo.setBemTributavelVo(bemTributavelVo);
	   fichaImovelUrbanoVo.setNumeroVersaoGIAITCD(gIAITCDInventarioArrolamentoVo.getNumeroVersaoGIAITCD());
      
		getBuffer(request).setAttribute(FICHA_IMOVEL_URBANO_VO, fichaImovelUrbanoVo, request);
		getBuffer(request).setAttribute(GIAITCD_VO, gIAITCDInventarioArrolamentoVo, request);
		processFlow(VIEW_PESQUISAR_GIAITCD_DETALHAR_FICHA_IMOVEL_URBANO, request, response, FORWARD);
	}

	/**
	 *  Cria uma instância uníca para o objeto para evitar tomar um null pointer exception
	 * @param request
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private GIAITCDInventarioArrolamentoVo controladorInterativoJSP(HttpServletRequest request)
	{
	   GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
	   GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = null;
	   if(giaITCDVo instanceof GIAITCDInventarioArrolamentoVo)
	   {
	      giaITCDInventarioArrolamentoVo = (GIAITCDInventarioArrolamentoVo) giaITCDVo;
	   }  
		if (giaITCDInventarioArrolamentoVo == null)
		{
			giaITCDInventarioArrolamentoVo = new GIAITCDInventarioArrolamentoVo();
		}
		giaITCDInventarioArrolamentoVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
		giaITCDInventarioArrolamentoVo.setImprimirDar(false);		
		return giaITCDInventarioArrolamentoVo;
	}

	/**
	 * Método que envia para o request um parametro para controlar o java script generico
	 * @param request
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void parametroConsulta(HttpServletRequest request)
	{
		getBuffer(request).setAttribute(CONTROLE_CONSULTA_JAVA_SCRIPT, CONTROLE_CONSULTA_JAVA_SCRIPT, request);
	}

	/**
	 * Método desenvolvido para solicitar a geração do DAR
	 * @param request
	 * @param response
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitaGerarDAR(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO,giaITCDInventarioArrolamentoVo, request);
		GIAITCDBe giaITCDBe = null;
      
	   String ipClient = request.getHeader("CLIENTIP");
	   String portClient = request.getHeader("CLIENTPORT");
      
		try
		{
			obterInformacoesLogSefaz(request, giaITCDInventarioArrolamentoVo);
			giaITCDBe = new GIAITCDBe();
			if(!giaITCDInventarioArrolamentoVo.getGiaITCDDarVo().isDarQuitado())
			{
			   giaITCDBe.gerarDAR(giaITCDInventarioArrolamentoVo, ipClient, portClient );
				giaITCDInventarioArrolamentoVo.setImprimirDar(true);				
			}
		   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		   getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
		   processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);

		}
		catch (DarException e)
		{
			giaITCDInventarioArrolamentoVo.setMensagem(e.getMessage());
			request.setAttribute(ENTIDADE_VO,giaITCDInventarioArrolamentoVo);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
			processFlow(VIEW_ERRO, request, response, FORWARD);
			
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
			if(giaITCDBe!=null)
			{
				giaITCDBe.close();
				giaITCDBe=null;
			}
		}
	}




   /**
    * Método desenvolvido para solicitar a geração do DAR no Mod Aberto
    * @param request
    * @param response
    * @implemented by Dherkyan Ribeiro
    */
   private void solicitaGerarDARModAberto(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
   {
      GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
      getBuffer(request).setAttribute(GIAITCD_VO,giaITCDInventarioArrolamentoVo, request);
      GIAITCDBe giaITCDBe = null;
      
      String ipClient = request.getHeader("CLIENTIP");
      String portClient = request.getHeader("CLIENTPORT");
      
      try
      {
         obterInformacoesLogSefaz(request, giaITCDInventarioArrolamentoVo);
         giaITCDBe = new GIAITCDBe();
         if(!giaITCDInventarioArrolamentoVo.getGiaITCDDarVo().isDarQuitado())
         {
            giaITCDBe.gerarDAR(giaITCDInventarioArrolamentoVo, ipClient, portClient);
           
            giaITCDInventarioArrolamentoVo.setImprimirDar(true);           
         }
         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
         processFlow(VIEW_IMPRIMIAR_DAR_DECLARACAO, request, response, FORWARD);

      }
      catch (DarException e)
      {
         giaITCDInventarioArrolamentoVo.setMensagem(e.getMessage());
         request.setAttribute(ENTIDADE_VO,giaITCDInventarioArrolamentoVo);
         getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
         processFlow(VIEW_ERRO, request, response, FORWARD);
         
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
         if(giaITCDBe!=null)
         {
            giaITCDBe.close();
            giaITCDBe=null;
         }
      }
   }


	/**
	 * Método desenvolvido para solicirar a impressão da declaração de isenção por valores
	 * @param request
	 * @param response
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitaImprimirDeclaracaoIsencao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, 
			  PDFMalformedURLException, 
			  ConsultaException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException
	{
	   GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
	   Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
	   GIAITCDBe giaITCDBe = null;
	   try
	   {
	      giaITCDBe = new GIAITCDBe();
	      giaITCDBe.solicitarVerificarStatusAnteriorIgualRetificado(giaITCDInventarioArrolamentoVo);
			getBuffer(request).setAttribute(GIAITCD_VO,giaITCDInventarioArrolamentoVo, request);
			response.setContentType(TIPO_PDF);
			DeclaracaoInsencaoPorValoresGerarPDF relatorio = 
						new DeclaracaoInsencaoPorValoresGerarPDF(request, giaITCDInventarioArrolamentoVo, InterfacePDF.PAGINA_A4_RETRATO);
			ByteArrayOutputStream baos = relatorio.gerarRelatorio();
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	   catch(ParametroObrigatorioException e)
	   {
	      request.setAttribute(ENTIDADE_VO, giaITCDInventarioArrolamentoVo);
	      processFlow(VIEW_ERRO, request, response, FORWARD);
	   }
	   catch (IOException io)
	   {
	      io.printStackTrace();
	      throw new PDFIOException();
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
	private void solicitaAbaFormaPagamento(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
	    GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_FORMA_PAGAMENTO, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}
}
