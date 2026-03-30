package br.gov.mt.sefaz.itc.modulo.giaitcd.giaitcdseparacaodivorcio;

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
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.Form;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.relatorio.DeclaracaoInsencaoPorValoresGerarPDF;
import br.gov.mt.sefaz.itc.model.relatorio.DeclaracaoNaoOcorrenciaFatoGerador;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoVo;
import br.gov.mt.sefaz.itc.util.DarException;
import br.gov.mt.sefaz.itc.util.calculo.CalculoITCD;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.2 $
 */
public class GIAITCDSeparacaoDivorcioPesquisar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_SOLICITAR_ABA_DADOS_GERAIS = 2;
	private static final int REQUISICAO_SOLICITAR_ABA_BENS_TRIBUTAVEIS = 3;
	private static final int REQUISICAO_SOLICITAR_ABA_CONJUGE = 4;
	private static final int REQUISICAO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO = 5;
	private static final int REQUISICAO_SOLICITAR_ABA_ACOMPANHAMENTO = 6;
	private static final int REQUISICAO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_URBANO = 7;
	private static final int REQUISICAO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_RURAL = 8;
	private static final int REQUISICAO_GERAR_DAR = 9;
	private static final int REQUISICAO_IMPRIMIR_DECLARACAO_ISENCAO = 10;
	private static final int REQUISICAO_IMPRIMIR_DECLARACAO_FATO_GERADOR = 11;
    private static final int REQUISICAO_GERAR_DAR_MOD_ABERTO = 12;
	private static final int REQUISICAO_SOLICITAR_ABA_FORMA_PAGAMENTO = 13;

	/**
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, ConexaoException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitaConsultarGIAITCDSeparacaoDivorcio(request, response);
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
			case REQUISICAO_SOLICITAR_ABA_CONJUGE:
				{
					solicitaAbaConjuge(request, response);
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
			case REQUISICAO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_RURAL:
				{
					solicitaAbaImovelRural(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_URBANO:
				{
					solicitaAbaImovelUrbano(request, response);
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
			case REQUISICAO_IMPRIMIR_DECLARACAO_FATO_GERADOR:
				{
					solicitaImprimirDeclaracaoFatoGerador(request, response);
					break;
				}
		    case REQUISICAO_SOLICITAR_ABA_FORMA_PAGAMENTO:
		        {
		            solicitaAbaFormaPagamento(request, response);
		            break;
		        }
		}
	}

	/**
	 * @param request
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
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
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_CONJUGE)))
		{
			return REQUISICAO_SOLICITAR_ABA_CONJUGE;
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
		if (Validador.isStringValida(request.getParameter(BOTAO_IMPRIMIR_DECLARACAO_FATO_GERADOR)))
		{
			return REQUISICAO_IMPRIMIR_DECLARACAO_FATO_GERADOR;
		}
	    if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_FORMA_PAGAMENTO)))
	    {
	        return REQUISICAO_SOLICITAR_ABA_FORMA_PAGAMENTO;
	    }
		return REQUISICAO_VAZIA;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaConsultarGIAITCDSeparacaoDivorcio(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		Iterator itNatureza = giaITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().getCollVO().iterator();
		while (itNatureza.hasNext())
		{
			NaturezaOperacaoVo naturezaOperacaoVo = (NaturezaOperacaoVo) itNatureza.next();
			if (naturezaOperacaoVo.getCodigo() == giaITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().getCodigo())
			{
				giaITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().setDescricaoNaturezaOperacao(naturezaOperacaoVo.getDescricaoNaturezaOperacao());
			}
		}
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaDadosGerais(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaBensTributaveis(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaConjuge(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaDemonstrativoDeCalculo(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException
	{
		GIAITCDSeparacaoDivorcioVo gIAITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, gIAITCDSeparacaoDivorcioVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaAcompanhamento(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException
	{
		GIAITCDSeparacaoDivorcioVo gIAITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		request.setAttribute(GIAITCD_VO, gIAITCDSeparacaoDivorcioVo);
		request.setAttribute(ABA_ATUAL, ABA_ACOMPANHAMENTO);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 *  Cria uma instância uníca para o objeto para evitar tomar um null pointer exception
	 * @param request
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private GIAITCDSeparacaoDivorcioVo controladorInterativoJSP(HttpServletRequest request)
	{
	   GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
	   GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = null;
	   if(giaITCDVo instanceof GIAITCDSeparacaoDivorcioVo)
	   {
	      giaITCDSeparacaoDivorcioVo = (GIAITCDSeparacaoDivorcioVo) giaITCDVo;
	   }               
		if (giaITCDSeparacaoDivorcioVo == null)
		{
			giaITCDSeparacaoDivorcioVo = new GIAITCDSeparacaoDivorcioVo();
		}
		giaITCDSeparacaoDivorcioVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
		giaITCDSeparacaoDivorcioVo.setImprimirDar(false);		
		return giaITCDSeparacaoDivorcioVo;
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
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		// Pega o Indice do Bem que deseja Detalhar
		int indice = 
				 StringUtil.toInt(request.getParameter(PARAMETRO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_RURAL));
		FichaImovelRuralVo fichaImovelRuralVo = new FichaImovelRuralVo();
		BemTributavelVo bemTributavelVo = 
				 (BemTributavelVo) ((ArrayList) giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO()).get(indice);
             
		fichaImovelRuralVo.setBemTributavelVo(bemTributavelVo);
      fichaImovelRuralVo.setNumeroVersaoGIAITCD(giaITCDSeparacaoDivorcioVo.getNumeroVersaoGIAITCD());
      
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
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
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		// Pega o Indice do Bem que deseja Detalhar
		int indice = 
				 StringUtil.toInt(request.getParameter(PARAMETRO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_URBANO));
		FichaImovelUrbanoVo fichaImovelUrbanoVo = new FichaImovelUrbanoVo();
		BemTributavelVo bemTributavelVo = 
				 (BemTributavelVo) ((ArrayList) giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO()).get(indice);
             
		fichaImovelUrbanoVo.setBemTributavelVo(bemTributavelVo);
	   fichaImovelUrbanoVo.setNumeroVersaoGIAITCD(giaITCDSeparacaoDivorcioVo.getNumeroVersaoGIAITCD());
      
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		getBuffer(request).setAttribute(FICHA_IMOVEL_URBANO_VO, fichaImovelUrbanoVo, request);
		processFlow(VIEW_PESQUISAR_GIAITCD_DETALHAR_FICHA_IMOVEL_URBANO, request, response, FORWARD);
	}

	/**
	 * Método que envia para o request um parametro para controlar o java script genérico para evitar de passar pelo request, validaFormulario, que se encontra na pagina
	 * de controle de abas.
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
	private void solicitaGerarDAR(HttpServletRequest request, HttpServletResponse response) throws 
			  ConexaoException, ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO,giaITCDSeparacaoDivorcioVo, request);
		GIAITCDBe giaITCDBe = null;
      
	   String ipClient = request.getHeader("CLIENTIP");
	   String portClient = request.getHeader("CLIENTPORT");
      
		try
		{
			giaITCDBe = new GIAITCDBe();
			if(!giaITCDSeparacaoDivorcioVo.getGiaITCDDarVo().isDarQuitado())
			{
           
           giaITCDBe.gerarDAR(giaITCDSeparacaoDivorcioVo, ipClient, portClient);          
            
            giaITCDSeparacaoDivorcioVo.setImprimirDar(true);				
			}
		   request.setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo);
		   request.setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO);
		   processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
			
		}
		catch(DarException e)
		{
			giaITCDSeparacaoDivorcioVo.setMensagem(e.getMessage());
			request.setAttribute(ENTIDADE_VO,giaITCDSeparacaoDivorcioVo);
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
   private void solicitaGerarDARModAberto(HttpServletRequest request, HttpServletResponse response) throws 
           ConexaoException, ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
   {
      GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
      getBuffer(request).setAttribute(GIAITCD_VO,giaITCDSeparacaoDivorcioVo, request);
      GIAITCDBe giaITCDBe = null;
      
      String ipClient = request.getHeader("CLIENTIP");
      String portClient = request.getHeader("CLIENTPORT");
      
      try
      {
         giaITCDBe = new GIAITCDBe();
         if(!giaITCDSeparacaoDivorcioVo.getGiaITCDDarVo().isDarQuitado())
         {
            
            giaITCDBe.gerarDAR(giaITCDSeparacaoDivorcioVo, ipClient, portClient);
            
           
            giaITCDSeparacaoDivorcioVo.setImprimirDar(true);            
         }
         request.setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo);
         processFlow(VIEW_IMPRIMIAR_DAR_DECLARACAO, request, response, FORWARD);
         
      }
      catch(DarException e)
      {
         giaITCDSeparacaoDivorcioVo.setMensagem(e.getMessage());
         request.setAttribute(ENTIDADE_VO,giaITCDSeparacaoDivorcioVo);
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
			  ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException
	{
	   GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
	   Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			giaITCDBe.solicitarVerificarStatusAnteriorIgualRetificado(giaITCDSeparacaoDivorcioVo);
			getBuffer(request).setAttribute(GIAITCD_VO,giaITCDSeparacaoDivorcioVo, request);
			response.setContentType(TIPO_PDF);
			DeclaracaoInsencaoPorValoresGerarPDF relatorio = 
						new DeclaracaoInsencaoPorValoresGerarPDF(request, giaITCDSeparacaoDivorcioVo, InterfacePDF.PAGINA_A4_RETRATO);
			ByteArrayOutputStream baos = relatorio.gerarRelatorio();
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		catch(ParametroObrigatorioException e)
		{
			request.setAttribute(ENTIDADE_VO, giaITCDSeparacaoDivorcioVo);
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

	/**
	 * Método desenvolvido para solicirar a impressão da declaração de não ocorrência de fato gerador
	 * @param request
	 * @param response
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitaImprimirDeclaracaoFatoGerador(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, 
			  PDFMalformedURLException, 
			  ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException
	{
	   GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
	   Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
	   GIAITCDBe giaITCDBe = null;
	   try
	   {
	      giaITCDBe = new GIAITCDBe();
	      giaITCDBe.solicitarVerificarStatusAnteriorIgualRetificado(giaITCDSeparacaoDivorcioVo);
			getBuffer(request).setAttribute(GIAITCD_VO,giaITCDSeparacaoDivorcioVo, request);
			response.setContentType(TIPO_PDF);
			DeclaracaoNaoOcorrenciaFatoGerador relatorio = new DeclaracaoNaoOcorrenciaFatoGerador(request, giaITCDSeparacaoDivorcioVo, InterfacePDF.PAGINA_A4_RETRATO);
			ByteArrayOutputStream baos = relatorio.gerarRelatorio();
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	   catch(ParametroObrigatorioException e)
	   {
	      request.setAttribute(ENTIDADE_VO, giaITCDSeparacaoDivorcioVo);
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
	    GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		boolean isValorBensConjuguesIguais = GIAITCDSeparacaoDivorcioBe.isValorBensConjugesIguais(giaITCDSeparacaoDivorcioVo);
	    getBuffer(request).setAttribute(NAO_OCORRENCIA_DE_FATO_GERADOR, isValorBensConjuguesIguais, request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_FORMA_PAGAMENTO, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}
}
