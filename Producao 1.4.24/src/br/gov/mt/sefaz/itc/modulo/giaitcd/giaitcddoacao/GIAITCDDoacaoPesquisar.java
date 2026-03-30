package br.gov.mt.sefaz.itc.modulo.giaitcd.giaitcddoacao;

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
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaVo;
import br.gov.mt.sefaz.itc.model.relatorio.DeclaracaoInsencaoPorValoresGerarPDF;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.AliquotaVo;
import br.gov.mt.sefaz.itc.util.DarException;
import br.gov.mt.sefaz.itc.util.dominio.DomnAba;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.excecoes.DoacaoSucessivaNaoPermitidaException;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Classe responsavel por receber  a requisição via request e enviar para a jsp responsável, do módulo GIAITCD tipo de processo Doação
 * @author Rogério Sanches de Oliveira
 */
public class GIAITCDDoacaoPesquisar extends AbstractAbacoServlet implements Flow, Form
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
   private static final int REQUISICAO_SOLICITAR_ABA_DOACAO_SUCESSIVA = 12;

	/**
	 *  Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, PDFFileNotFoundException, PDFIOException, 
			  PDFDocumentException, PDFBadElementException, PDFMalformedURLException, ConexaoException, ConsultaException, SQLException
	{
	   GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
	   if (giaITCDVo instanceof GIAITCDDoacaoVo) {
	      verificarExibirAbaDoacaoSucessiva(request, (GIAITCDDoacaoVo) giaITCDVo);
	   }
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitaConsultarGIAITCDDoacao(request, response);
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
		   case REQUISICAO_SOLICITAR_ABA_DOACAO_SUCESSIVA:
		      {
		         solicitaAbaDoacaoSucessiva(request, response);
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
    * Verifica se existe pelo menos um beneficiário com GiaitcdDoacaoSucessivaVo
    * e define o atributo "exibirAbaDoacaoSucessiva" no request
    * 
    * @param request O HttpServletRequest onde o atributo será definido
    * @param giaITCDDoacaoVo O objeto GIAITCDDoacaoVo a ser verificado
    */
   private void verificarExibirAbaDoacaoSucessiva(HttpServletRequest request, GIAITCDDoacaoVo giaITCDDoacaoVo) {
      boolean temDoacaoSucessiva = false;
              
      if (giaITCDDoacaoVo.getBeneficiarioVo() != null && 
          giaITCDDoacaoVo.getBeneficiarioVo().getCollVO() != null && 
          !giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().isEmpty()) {
          
          for (Object obj : giaITCDDoacaoVo.getBeneficiarioVo().getCollVO()) {
             GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) obj;
               if (beneficiario.getGiaitcdDoacaoSucessivaVo().getCollVO() != null &&
                  !beneficiario.getGiaitcdDoacaoSucessivaVo().getCollVO().isEmpty() ) {
                    temDoacaoSucessiva = true;
                    break;
               }
          }          
      }
       request.setAttribute("exibirAbaDoacaoSucessiva", temDoacaoSucessiva);
   }

	/**
	 *  Este método é responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request
	 * @return
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
	   else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_DOACAO_SUCESSIVA)))
	   {
	      return REQUISICAO_SOLICITAR_ABA_DOACAO_SUCESSIVA;
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
		return REQUISICAO_VAZIA;
	}

	/**
	 * Este Método  é acionado quando o usuário solicita a consulta da GIA
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaConsultarGIAITCDDoacao(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}

	/**
	 * Este método é acionado quando o usuário clica na aba dados gerais. 
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaDadosGerais(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}

	/**
	 *  Este método é acionado quando o usuário clica na aba bens tributáveis. 
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaBensTributaveis(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}
   
   private void solicitaAbaDoacaoSucessiva(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
           TipoRedirecionamentoInvalidoException, ConexaoException, ConsultaException, ObjetoObrigatorioException, SQLException
   {
   
      GIAITCDDoacaoSucessivaBe doacaoSucessivaBe = null;       
      String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);     
      GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
      boolean exibicao = giaITCDDoacaoVo.getDataCriacao().before(buscaParametroGerencialDataLimite());  
      request.setAttribute("quantidadeDoacaoSucessivaMaiorQueParametro", false);
      
      if(exibicao){
         doacaoSucessivaBe = new GIAITCDDoacaoSucessivaBe();                 
            if (giaITCDDoacaoVo.getBeneficiarioVo() != null && giaITCDDoacaoVo.getBeneficiarioVo().getCollVO() != null && !giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().isEmpty()){
               for (Object obj: giaITCDDoacaoVo.getBeneficiarioVo().getCollVO()){
                  GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) obj;                           
                  GIAITCDDoacaoSucessivaVo giaitcdDoacaoSucessivaVo = doacaoSucessivaBe.consultaDoacoesSucessivasDoBenefNaoUtilizadasParaCalculo(beneficiario);
                  if (giaitcdDoacaoSucessivaVo.getCollVO() != null && !giaitcdDoacaoSucessivaVo.getCollVO().isEmpty()){
                        if(giaitcdDoacaoSucessivaVo.getCollVO().size() >= 2){
                            request.setAttribute("quantidadeDoacaoSucessivaMaiorQueParametro", true);
                       }
                   }
               }
            }
      }
         
      if(request.getAttribute("exibirAbaDoacaoSucessiva") == Boolean.FALSE)
      {
         if(abaAtual.equals("abaBeneficiarios"))
         {
            solicitaAbaDemonstrativoDeCalculo(request, response);
         }else
         {
            solicitaAbaBeneficiarios(request, response);
         }
      }  
      
      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
      getBuffer(request).setAttribute(ABA_ATUAL, ABA_DOACAO_SUCESSIVA, request);
      parametroConsulta(request);
      processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
   }
   
   private static Date buscaParametroGerencialDataLimite(){
      ConfiguracaoGerencialParametrosVo configuracaoDataLimite = new ConfiguracaoGerencialParametrosVo();
      configuracaoDataLimite.setCodigo(66);      
      configuracaoDataLimite = new ConfiguracaoGerencialParametrosVo(configuracaoDataLimite);
      ConfiguracaoGerencialParametrosBe configuracaoGerencialParametrosBe = null;
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      Date dataLimite = null;
      
      try
      {
         configuracaoGerencialParametrosBe = 
               new ConfiguracaoGerencialParametrosBe();
         configuracaoGerencialParametrosBe.consultarConfiguracaoGerencialParametros(configuracaoDataLimite);

         if (configuracaoDataLimite != null)
         {
            dataLimite = df.parse(configuracaoDataLimite.getValorItem());
         }
      }
      catch (Exception e)
      {
         System.out.println("Erro ao buscar Parametros Gerenciais" + 
                            e.getStackTrace());
      }
      finally
      {
         if (configuracaoGerencialParametrosBe != null)
         {
            configuracaoGerencialParametrosBe.close();
            configuracaoGerencialParametrosBe = null;
         }
      }
      return dataLimite;
   }

	/**
	 *  Este método é acionado quando o usuário clica na aba beneficiarios
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);      
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}

	/**
	 * Este método é acionado quando o usuário clica na aba Demonstrativo De Calculo
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaDemonstrativoDeCalculo(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConexaoException, ConsultaException, ObjetoObrigatorioException
	{
	   GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
	   GIAITCDDoacaoSucessivaBe doacaoSucessivaBe = null; 
	   boolean limitacaoPreenchimentoDoacaoSucessiva = false;
	   boolean apresentacaoDispensaRecolhimento = false; 
	   boolean exibicao = giaITCDDoacaoVo.getDataCriacao().before(buscaParametroGerencialDataLimite());  
      
	   try
	   {      
	      if(exibicao){
            doacaoSucessivaBe = new GIAITCDDoacaoSucessivaBe(); 
            request.setAttribute("exibirValorBaseCalculoDoacaoSucessiva", exibicao);
            if (giaITCDDoacaoVo.getBeneficiarioVo() != null && giaITCDDoacaoVo.getBeneficiarioVo().getCollVO() != null && !giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().isEmpty()){
               for (Object obj: giaITCDDoacaoVo.getBeneficiarioVo().getCollVO()){
                  GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) obj;	                        
                  GIAITCDDoacaoSucessivaVo giaitcdDoacaoSucessivaVo = doacaoSucessivaBe.consultaDoacoesSucessivasDoBenefNaoUtilizadasParaCalculo(beneficiario);
                  if (giaitcdDoacaoSucessivaVo.getCollVO() != null && !giaitcdDoacaoSucessivaVo.getCollVO().isEmpty()){
                     if(giaitcdDoacaoSucessivaVo.getCollVO().size() >= 2){
                        limitacaoPreenchimentoDoacaoSucessiva = true;
                     }
                  }
               }
            }
            
            if(limitacaoPreenchimentoDoacaoSucessiva){
               throw new DoacaoSucessivaNaoPermitidaException("Preenchimento não permitido! Existem duas ou mais GIA-ITCD-e de doação incluídas em data anterior a 06/08/2025. O Contribuinte deverá realizar a declaração utilizando o Sistema e-Process com o tipo de processo “ITCD - Denúncia Espontânea”.");
            } else{	   
               request.setAttribute("qtdeColunasAliquota", retornaMaiorQtdAliquotaBeneficiario(giaITCDDoacaoVo)); 
               getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
               getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
               parametroConsulta(request);
               processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
               
               
            }
         }else{
            if (giaITCDDoacaoVo.getBeneficiarioVo() != null && giaITCDDoacaoVo.getBeneficiarioVo().getCollVO() != null && !giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().isEmpty()){
               for (Object obj: giaITCDDoacaoVo.getBeneficiarioVo().getCollVO()){
                  GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) obj;                        
                  if(beneficiario.getInfoDispensaRecolhimento() != null && beneficiario.getInfoDispensaRecolhimento() == 2){
                     apresentacaoDispensaRecolhimento = true;
                  }
               }
            }
            request.setAttribute("exibirValorBaseCalculoDoacaoSucessiva", false);
            request.setAttribute("exibirDispensaRecolhimento", apresentacaoDispensaRecolhimento);                      
            request.setAttribute("qtdeColunasAliquota", retornaMaiorQtdAliquotaBeneficiario(giaITCDDoacaoVo));  
            
            getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
            getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
            parametroConsulta(request);
            processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);          
                      
         }
         
	   } catch(DoacaoSucessivaNaoPermitidaException e){   
         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
         request.setAttribute("exceptionDoacaoSucessiva", e);
         processFlow(VIEW_ERRO_GERAL, request, response, FORWARD);
         
      } catch(SQLException e){
	      e.printStackTrace();
	      throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
	   }      
		
	}
   
   private int retornaMaiorQtdAliquotaBeneficiario(GIAITCDDoacaoVo giaITCDDoacaoVo){
   
      if(giaITCDDoacaoVo.getBeneficiarioVo() != null){
         GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoVo.getBeneficiarioVo();
         if(beneficiario.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().isEmpty()){
            AliquotaVo aliquotasInterVivos = new AliquotaVo();
            for(Iterator ite = giaITCDDoacaoVo.getParametroLegislacaoVo().getAliquotaVo().getCollVO().iterator(); ite.hasNext();)
            {
               AliquotaVo atual = (AliquotaVo) ite.next();
               if (atual.getTipoFatoGerador().is(DomnTipoGIA.INTER_VIVOS))
               {
                  aliquotasInterVivos.getCollVO().add(atual);
               }
            }
            beneficiario.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().addAll(aliquotasInterVivos.getCollVO());
         }
      }
      
      int qtdAliquotas = 0;
      if (giaITCDDoacaoVo.getBeneficiarioVo() != null && giaITCDDoacaoVo.getBeneficiarioVo().getCollVO() != null && !giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().isEmpty()){
         for (Object obj: giaITCDDoacaoVo.getBeneficiarioVo().getCollVO()){
            GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) obj; 
               int size = beneficiario.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().size();
               if(size > qtdAliquotas){
                  qtdAliquotas = size;
               }               
         }
      } 
      return qtdAliquotas;
   }

	/**
	 *  Este método é acionado quando o usuário clica na aba Acompanhamento
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaAcompanhamento(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		getBuffer(request).setAttribute(Form.ABA_ATUAL, ABA_ACOMPANHAMENTO, request);
		parametroConsulta(request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaImovelRural(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		// Pega o Indice do Bem que deseja Detalhar
		int indice = 
				 StringUtil.toInt(request.getParameter(PARAMETRO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_RURAL));
		FichaImovelRuralVo fichaImovelRuralVo = new FichaImovelRuralVo();
		BemTributavelVo bemTributavelVo = 
				 (BemTributavelVo) ((ArrayList) giaITCDDoacaoVo.getBemTributavelVo().getCollVO()).get(indice);
             
		fichaImovelRuralVo.setBemTributavelVo(bemTributavelVo);
	   fichaImovelRuralVo.setNumeroVersaoGIAITCD(giaITCDDoacaoVo.getNumeroVersaoGIAITCD());
      
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DETALHE_RURAL, request);
		processFlow(VIEW_PESQUISAR_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaImovelUrbano(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		// Pega o Indice do Bem que deseja Detalhar
		int indice = 
				 StringUtil.toInt(request.getParameter(PARAMETRO_SOLICITAR_GIAITCD_ABA_BENS_TRIBUTAVEIS_DETALHE_IMOVEL_URBANO));
		FichaImovelUrbanoVo fichaImovelUrbanoVo = new FichaImovelUrbanoVo();
		BemTributavelVo bemTributavelVo = 
				 (BemTributavelVo) ((ArrayList) giaITCDDoacaoVo.getBemTributavelVo().getCollVO()).get(indice);
             
		fichaImovelUrbanoVo.setBemTributavelVo(bemTributavelVo);
      fichaImovelUrbanoVo.setNumeroVersaoGIAITCD(giaITCDDoacaoVo.getNumeroVersaoGIAITCD());
      
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		getBuffer(request).setAttribute(FICHA_IMOVEL_URBANO_VO, fichaImovelUrbanoVo, request);
		processFlow(VIEW_PESQUISAR_GIAITCD_DETALHAR_FICHA_IMOVEL_URBANO, request, response, FORWARD);
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Consultar.
	 * @param request
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private GIAITCDDoacaoVo controladorInterativoJSP(HttpServletRequest request)
	{
	   GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
	   GIAITCDDoacaoVo giaITCDDoacaoVo = null;
	   if(giaITCDVo instanceof GIAITCDDoacaoVo)
	   {
	      giaITCDDoacaoVo = (GIAITCDDoacaoVo) giaITCDVo;
	   }
		if (giaITCDDoacaoVo == null)
		{
			giaITCDDoacaoVo = new GIAITCDDoacaoVo();
		}
		giaITCDDoacaoVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
		giaITCDDoacaoVo.setImprimirDar(false);		
		return giaITCDDoacaoVo;
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
	private void solicitaGerarDAR(HttpServletRequest request, HttpServletResponse response) throws  
			  ConexaoException, ObjetoObrigatorioException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO,giaITCDDoacaoVo, request);
		GIAITCDBe giaITCDBe = null;
      
	   String ipClient = request.getHeader("CLIENTIP");
	   String portClient = request.getHeader("CLIENTPORT");
      
		try
		{
			giaITCDBe = new GIAITCDBe();
			if(!giaITCDDoacaoVo.getGiaITCDDarVo().isDarQuitado())
			{
				giaITCDBe.gerarDAR(giaITCDDoacaoVo, ipClient, portClient);
				giaITCDDoacaoVo.setImprimirDar(true);				
			}
		   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		   getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
		   processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);			
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
		catch (DarException e)
		{
			giaITCDDoacaoVo.setMensagem(e.getMessage());
			request.setAttribute(ENTIDADE_VO,giaITCDDoacaoVo);			
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
			processFlow(VIEW_ERRO, request, response, FORWARD);
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
    * @implemented by Dherkyan Ribeiro da Silva
    */
   private void solicitaGerarDARModAberto(HttpServletRequest request, HttpServletResponse response) throws  
           ConexaoException, ObjetoObrigatorioException, PaginaNaoEncontradaException, 
           TipoRedirecionamentoInvalidoException
   {
      GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
      getBuffer(request).setAttribute(GIAITCD_VO,giaITCDDoacaoVo, request);
      GIAITCDBe giaITCDBe = null;
      
      String ipClient = request.getHeader("CLIENTIP");
      String portClient = request.getHeader("CLIENTPORT");
      
      try
      {
         giaITCDBe = new GIAITCDBe();
         if(!giaITCDDoacaoVo.getGiaITCDDarVo().isDarQuitado())
         {
            giaITCDBe.gerarDAR(giaITCDDoacaoVo, ipClient, portClient);
            giaITCDDoacaoVo.setImprimirDar(true);           
         }
         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
         processFlow(VIEW_IMPRIMIAR_DAR_DECLARACAO, request, response, FORWARD);        
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
      catch (DarException e)
      {
         giaITCDDoacaoVo.setMensagem(e.getMessage());
         request.setAttribute(ENTIDADE_VO,giaITCDDoacaoVo);       
         getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
         processFlow(VIEW_ERRO, request, response, FORWARD);
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
	   GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
	   Validador.validaObjeto(giaITCDDoacaoVo);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			giaITCDBe.solicitarVerificarStatusAnteriorIgualRetificado(giaITCDDoacaoVo);
			getBuffer(request).setAttribute(GIAITCD_VO,giaITCDDoacaoVo, request);
			response.setContentType(TIPO_PDF);
			DeclaracaoInsencaoPorValoresGerarPDF relatorio = 
						new DeclaracaoInsencaoPorValoresGerarPDF(request, giaITCDDoacaoVo, InterfacePDF.PAGINA_A4_RETRATO);
			ByteArrayOutputStream baos = relatorio.gerarRelatorio();
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		catch(ParametroObrigatorioException e)
		{
			request.setAttribute(ENTIDADE_VO, giaITCDDoacaoVo);
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
}
