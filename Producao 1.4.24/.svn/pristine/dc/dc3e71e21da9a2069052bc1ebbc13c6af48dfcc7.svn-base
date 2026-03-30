package br.gov.mt.sefaz.itc.modulo.generico.giaitcd;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
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
import br.com.abaco.util.exceptions.TituloNaoInformadoException;
import br.com.abaco.util.http.AbstractAbacoServlet;
import br.com.abaco.util.pdf.InterfacePDF;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.Form;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.model.relatorio.DeclaracaoInsencaoPorValoresGerarPDF;
import br.gov.mt.sefaz.itc.model.relatorio.DeclaracaoNaoOcorrenciaFatoGerador;
import br.gov.mt.sefaz.itc.model.relatorio.GIAITCDDoacaoGerarPDF;
import br.gov.mt.sefaz.itc.model.relatorio.GIAITCDSeparacaoDivorcioGerarPDF;
import br.gov.mt.sefaz.itc.model.relatorio.InventarioArrolamentoGerarPDF;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnProcessoReimpressao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.excecoes.DoacaoSucessivaNaoPermitidaException;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.http.HttpUtil;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import br.gov.mt.sefaz.itc.util.integracao.cadastro.UFIntegracaoVO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GIAITCDPesquisarReimprimir extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_CONSULTAR_GIA_ITCD = 2;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_CONTRIBUINTE = 8;
	private static final int REQUISICAO_SOLICITAR_INCLUIR_CONTRIBUINTE = 9;
	private static final int REQUISICAO_GERAR_PDF = 10;

	/**
	 * Este método trabalha colaborativamente com o método
	 * redirecionar. O método redirecionar determina a ação a ser tomada e
	 * processoRequest a executa.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Maxwell Rocha
	 * **/
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, ConexaoException, 
			  IntegracaoException, ImpossivelCriptografarException, ParseException, PDFIOException, PDFFileNotFoundException, 
			  PDFDocumentException, PDFBadElementException, PDFMalformedURLException, TituloNaoInformadoException, 
         DadoNecessarioInexistenteException, PersistenciaException, LogException, AnotacaoException, 
             IOException
   {
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarReimprimirGIAITCD(request, response);					
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
			case REQUISICAO_SOLICITAR_INCLUIR_CONTRIBUINTE:
				{
					solictarPesquisar(request, response);
					break;
				}
			case REQUISICAO_GERAR_PDF:
				{
					confirmarGIAITCDGerarPDF(request, response);
					break;
				}
		}
	}

	/**
	 * Este método é responsável pela análise dos parâmetros e a
	 * tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request
	 * @return
	 * @implemented by Maxwell Rocha
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO) != null)
		{
			return REQUISICAO_SOLICITAR_INCLUIR_CONTRIBUINTE;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR)))
		{
			return REQUISICAO_CONSULTAR_GIA_ITCD;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_CONTRIBUINTE)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_CONTRIBUINTE;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_REIMPRIMIR)))
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
	 * Método responsável por recuperar do buffer os valores (CPF, NOME) informados pelo usuário.
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	private void solictarPesquisar(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException
	{
		GIAITCDVo giaItcdVo = controladorInterativoJSP(request);
		obterInformacoesLogSefaz( request, giaItcdVo );
		getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
		processFlow(VIEW_PESQUISAR_REIMPRIMIRGIA_GIA_ITCD, request, response, FORWARD);
	}

	/**
	 * Consulta de Contribuinte através do CPF ou CNPJ.
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	private void solictarConsultarContribuinte(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ParseException, ObjetoObrigatorioException
	{
		GIAITCDVo giaItcdVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_REIMPRIMIR_GIAITCD_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Maxwell Mendes Rocha
	 */
	private GIAITCDVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		if (giaITCDVo == null)
		{
			giaITCDVo = (GIAITCDVo) request.getAttribute(GIAITCD_VO);
		}
		Validador.validaObjeto(giaITCDVo);
		String codigoSelectProcessoImpressao = (String) getBuffer(request).getAttribute(CODIGOSELECAOPROCESSOIMPRESSAO);
		if (Validador.isStringValida(request.getParameter(Form.CAMPO_SELECT_PROCESSO_IMPRESSAO)))
		{
			codigoSelectProcessoImpressao = request.getParameter(Form.CAMPO_SELECT_PROCESSO_IMPRESSAO);
		}
		getBuffer(request).setAttribute(CODIGOSELECAOPROCESSOIMPRESSAO, codigoSelectProcessoImpressao, request);
		if (Validador.isStringValida(request.getParameter(Form.CAMPO_SELECT_PROCESSO_IMPRESSAO)) && 
				 Validador.isStringValida(request.getParameter(Form.CAMPO_CODIGO_GIA)))
		{			
			GIAITCDVo gia = new GIAITCDVo();
			gia.setResponsavelVo(giaITCDVo.getResponsavelVo());
			int tipoPesquisaProcessoReimpressao = 
						StringUtil.toInt(request.getParameter(Form.CAMPO_SELECT_PROCESSO_IMPRESSAO));
			long codigo = StringUtil.toLong(request.getParameter(Form.CAMPO_CODIGO_GIA));
			switch (tipoPesquisaProcessoReimpressao)
			{
				case DomnProcessoReimpressao.CODIGO_OCORRENCIA_FATO_GERADOR:
					{
						gia.setNumrDeclaracaoFatoGerador(codigo);
						gia.setNumrDeclaracaoIsencao(0);
						gia.setCodigo(0);
						break;
					}
				case DomnProcessoReimpressao.CODIGO_ISENCAO_VALORES:
					{
						gia.setNumrDeclaracaoIsencao(codigo);
						gia.setNumrDeclaracaoFatoGerador(0);
						gia.setCodigo(0);
						break;
					}
				default:
					{
						gia.setCodigo(codigo);
						gia.setNumrDeclaracaoIsencao(0);
						gia.setNumrDeclaracaoFatoGerador(0);
					}
			}
			giaITCDVo = gia;
		}
		if (request.getAttribute("contribuinteIntegracaoVo") != null)
		{
			ContribuinteIntegracaoVo contribuinteIntegracaoVo = 
						(ContribuinteIntegracaoVo) request.getAttribute("contribuinteIntegracaoVo");
			giaITCDVo.setResponsavelVo(contribuinteIntegracaoVo);
		}
		return giaITCDVo;
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
	 * Método responsável por realizar uma consulta na GIAITCD identificando seu tipo de GIA e preparando os dados 
	 * para ser representado no relatório.
	 * @param request, response
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	protected void solicitarPesquisarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, ConexaoException, 
			  IntegracaoException, ImpossivelCriptografarException, IOException
   {   
		String codigoSelectProcessoImpressao = request.getParameter(Form.CAMPO_SELECT_PROCESSO_IMPRESSAO);
		int codigo = StringUtil.toInt(codigoSelectProcessoImpressao);
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		GIAITCDVo giaConsulta = new GIAITCDVo(giaITCDVo);
		giaConsulta.setConsultaCompleta(true);
		GIAITCDBe giaItcdBe = null;
      
	   GIAITCDDoacaoSucessivaBe doacaoSucessivaBe = null; 
	   request.setAttribute("quantidadeDoacaoSucessivaMaiorQueParametro", false);     
      
		try{    
			giaItcdBe = new GIAITCDBe();
		   obterInformacoesLogSefaz(request, giaConsulta);
		   HttpUtil.validarCaracteresInformadosModAberto(getBuffer(request) , request.getParameter(Form.CAMPO_IMAGEM), giaConsulta.getLogSefazVo());			
			switch (codigo)
			{
				case DomnProcessoReimpressao.CODIGO_OCORRENCIA_FATO_GERADOR:
					{
						giaConsulta = giaItcdBe.consultaGiaPermanente(giaConsulta);
						break;
					}
				case DomnProcessoReimpressao.CODIGO_ISENCAO_VALORES:
					{
						giaConsulta = giaItcdBe.consultaGiaPermanente(giaConsulta);
						break;
					}
				default:
					{
						giaConsulta = giaItcdBe.consultaGIAITCDBasico(giaConsulta);
					}
			}
         
		   if(giaConsulta instanceof GIAITCDDoacaoVo){
            boolean exibicao = new Date().after(buscaParametroGerencialDataLimite());
            if(exibicao){
               doacaoSucessivaBe = new GIAITCDDoacaoSucessivaBe();                 
               if (giaConsulta.getBeneficiarioVo() != null && giaConsulta.getBeneficiarioVo().getCollVO() != null && !giaConsulta.getBeneficiarioVo().getCollVO().isEmpty()){
                  for (Object obj: giaConsulta.getBeneficiarioVo().getCollVO()){
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
               
            }
			if(Validador.isNumericoValido(giaConsulta.getCodigo()))
			{
				if(giaConsulta.getGiaConfirmada().is(DomnSimNao.NAO))
				{
               throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_CONFIRMADA);
				}
			}
			if (!Validador.isNumericoValido(giaConsulta.getCodigo()))
			{
				if(!Validador.isNumericoValido(giaConsulta.getTemporarioVo().getCodigo()))
				{
					throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_CAMPO_SELECT_DECLARACAO_DE_NAO_OCORRENCIA_DE_FATO_GERADOR);
				}
				else
				{
					if(!giaITCDVo.isUsuarioServidor())
					{
					   if(!giaConsulta.getTemporarioVo().getStatusITCD().getStatusGIAITCD().is(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO))
					   {
					      throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_NAO_PODE_SER_IMPRESSA_STATUS_DIFERENTE_PENDENTE_PROTOCOLO);
					   }						
					}
				}
			}
			else
			{
				if(!giaITCDVo.isUsuarioServidor())
				{
				   if(!giaConsulta.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO))
				   {
                  // Alteração o único status que pode ser impresso é EM_ELABORACAO.
				      //throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_NAO_PODE_SER_IMPRESSA_STATUS_DIFERENTE_PENDENTE_PROTOCOLO);
				   }					
				}
			}
			if (codigo != giaConsulta.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() && 
						codigo != DomnProcessoReimpressao.CODIGO_OCORRENCIA_FATO_GERADOR && 
						codigo != DomnProcessoReimpressao.CODIGO_ISENCAO_VALORES)
			{
				throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_REIMPRIMIR_TIPO_PROCESSO);
			}
			if (giaConsulta.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
			{
				throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_REIMPRIMIR_GIA_E_RETIFICADA);
			}
			if(codigo == DomnProcessoReimpressao.CODIGO_OCORRENCIA_FATO_GERADOR && !giaConsulta.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR))
			{
				throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_REIMPRIMIR_GIA_NAO_OCORRENCIA);
			}
			if(codigo == DomnProcessoReimpressao.CODIGO_ISENCAO_VALORES && !giaConsulta.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO))
			{
				throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_REIMPRIMIR_GIA_ISENTO);
			}
		   getBuffer(request).setAttribute(GIAITCD_VO, giaConsulta, request);
		   request.removeAttribute(EXCEPTION);
		   processFlow(VIEW_RESULTADO_CONSULTA_REIMPRIMIR_GIA_ITCD, request, response, FORWARD);			
		}
		catch (RegistroNaoPodeSerUtilizadoException e)
		{
			request.setAttribute(EXCEPTION, e);
			solictarPesquisar(request, response);
			return;
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
			solictarPesquisar(request, response);
			return;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (giaItcdBe != null)
			{
				giaItcdBe.close();
				giaItcdBe = null;
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
	 * @implemented by Maxwell Mendes Rocha
	 */
	private void confirmarGIAITCDGerarPDF(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PDFIOException, PDFFileNotFoundException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException, 
			  TituloNaoInformadoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, 
         IntegracaoException, ConsultaException, DadoNecessarioInexistenteException, PersistenciaException, LogException, 
         AnotacaoException, ImpossivelCriptografarException
   {
		Validador.isObjetoValido((String) getBuffer(request).getAttribute(CODIGOSELECAOPROCESSOIMPRESSAO));
		int TIPO_GIA_PDF = Integer.parseInt((String) getBuffer(request).getAttribute(CODIGOSELECAOPROCESSOIMPRESSAO));
      StatusGIAITCDVo status = null;
      GIAITCDBe giaITCDBe = null;
      StatusGIAITCDBe statusGiaItcdBe = null;
      CadastroBe cadastroBe = null;    
      
		try
		{		   
		   ByteArrayOutputStream baos = null;
			GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		   statusGiaItcdBe = new StatusGIAITCDBe();
		   status = statusGiaItcdBe.procurarUltimoStatusCollectionVo(giaITCDVo.getStatusVo());           
		   
		   if (Validador.isDominioNumericoValido(status.getStatusGIAITCD()) && status.getStatusGIAITCD().is(DomnStatusGIAITCD.EM_ELABORACAO))
		   {
		      giaITCDBe = new GIAITCDBe();
		      giaITCDBe.confirmarGIAITCDParaImpressao(giaITCDVo);
		   }       
         
			if (TIPO_GIA_PDF == DomnProcessoReimpressao.CODIGO_DOACAO)
			{
				GIAITCDDoacaoVo gIAITCDDoacaoVo = (GIAITCDDoacaoVo) giaITCDVo;
				Validador.validaObjeto(gIAITCDDoacaoVo);
				GIAITCDDoacaoGerarPDF relatorio = 
							  new GIAITCDDoacaoGerarPDF(request, gIAITCDDoacaoVo, InterfacePDF.PAGINA_A4_RETRATO);
				baos = relatorio.gerarRelatorioCustomizado();
			}
			if (TIPO_GIA_PDF == DomnProcessoReimpressao.CODIGO_INVENTARIO_ARROLAMENTO)
			{
				GIAITCDInventarioArrolamentoVo giaItcdInventarioArrolamentoVo = (GIAITCDInventarioArrolamentoVo) giaITCDVo;
			   UFIntegracaoVO ufConsulta = new UFIntegracaoVO();
			   cadastroBe = new CadastroBe();
            ufConsulta.setCollVO(cadastroBe.listarUf());
            for (Iterator iteUF = ufConsulta.getCollVO().iterator(); iteUF.hasNext(); )
            {
                  UFIntegracaoVO ufAtual = (UFIntegracaoVO) iteUF.next();
                  if (ufAtual.getSiglUf().equals(giaItcdInventarioArrolamentoVo.getUfAbertura().getSiglUf()))
                  {
                     giaItcdInventarioArrolamentoVo.setUfAbertura(ufAtual);
                     giaItcdInventarioArrolamentoVo.getUfAbertura().setCollVO(ufConsulta.getCollVO());
                     break;
               }
            }         
         
         	Validador.validaObjeto(giaItcdInventarioArrolamentoVo);
				InventarioArrolamentoGerarPDF relatorio = 
							  new InventarioArrolamentoGerarPDF(request, giaItcdInventarioArrolamentoVo, InterfacePDF.PAGINA_A4_RETRATO);
				baos = relatorio.gerarRelatorioCustomizado();
			}
			if (TIPO_GIA_PDF == DomnProcessoReimpressao.CODIGO_SEPARACAO_DIVORCIO_PARTILHA)
			{
				GIAITCDSeparacaoDivorcioVo gIAITCDSeparacaoDivorcioVo = (GIAITCDSeparacaoDivorcioVo) giaITCDVo;
				Validador.validaObjeto(gIAITCDSeparacaoDivorcioVo);
				GIAITCDSeparacaoDivorcioGerarPDF relatorio = 
							  new GIAITCDSeparacaoDivorcioGerarPDF(request, gIAITCDSeparacaoDivorcioVo, InterfacePDF.PAGINA_A4_RETRATO);
				baos = relatorio.gerarRelatorioCustomizado();
			}
			if (TIPO_GIA_PDF == DomnProcessoReimpressao.CODIGO_ISENCAO_VALORES)
			{
				Validador.validaObjeto(giaITCDVo);
				DeclaracaoInsencaoPorValoresGerarPDF relatorio = 
							  new DeclaracaoInsencaoPorValoresGerarPDF(request, giaITCDVo, InterfacePDF.PAGINA_A4_RETRATO);
				baos = relatorio.gerarRelatorio();
			}
			if (TIPO_GIA_PDF == DomnProcessoReimpressao.CODIGO_OCORRENCIA_FATO_GERADOR)
			{
				Validador.validaObjeto(giaITCDVo);
				DeclaracaoNaoOcorrenciaFatoGerador relatorio = 
							  new DeclaracaoNaoOcorrenciaFatoGerador(request, giaITCDVo, InterfacePDF.PAGINA_A4_RETRATO);
				baos = relatorio.gerarRelatorio();
			}
			if(baos != null)
			{
			   response.setContentType(TIPO_PDF);        
			   response.getOutputStream().write(baos.toByteArray());
			   response.getOutputStream().flush();
			   response.getOutputStream().close();       				
			}
			else
			{
			   giaITCDVo.setMensagem(MensagemErro.ERRO_GERAR_PDF_REIMPRESSAO);
				request.setAttribute(ENTIDADE_VO, giaITCDVo);
				processFlow(VIEW_ERRO, request, response, FORWARD);
			}         
               
		}
		catch (IOException io)
		{
			io.printStackTrace();
			throw new PDFIOException();
		}
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      catch (RegistroExistenteException e)
      {
         GIAITCDVo giaErro =  new GIAITCDVo();
         giaErro.setMensagem(MensagemErro.VALIDAR_GIA_ITCD_VALIDA_BEM_TIPO_DO_BEM);
         request.setAttribute(ENTIDADE_VO, giaErro);
         processFlow(VIEW_ERRO, request, response, FORWARD);
      }
      catch (RegistroNaoPodeSerUtilizadoException e)
      {
         GIAITCDVo giaErro =  new GIAITCDVo();
         giaErro.setMensagem(MensagemErro.VALIDAR_GIA_ITCD_VALIDA_BEM_TIPO_DO_BEM);
         request.setAttribute(ENTIDADE_VO, giaErro);
         processFlow(VIEW_ERRO, request, response, FORWARD);  
      }
      catch (ParametroObrigatorioException e)
      {
         GIAITCDVo giaErro =  new GIAITCDVo();
         giaErro.setMensagem(MensagemErro.VALIDAR_GIA_ITCD_VALIDA_BEM_TIPO_DO_BEM);
         request.setAttribute(ENTIDADE_VO, giaErro);
         processFlow(VIEW_ERRO, request, response, FORWARD);
      }
      finally
      {
         if (giaITCDBe != null)
         {
            giaITCDBe.close();
            giaITCDBe = null;
         }
         
         if (cadastroBe != null)
         {
            cadastroBe.close();
            cadastroBe = null;
         }
         if (statusGiaItcdBe != null)
         {
            statusGiaItcdBe.close();
            statusGiaItcdBe = null;
         }
      }
   }

	private void solicitarReimprimirGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, IntegracaoException, ConexaoException
	{
		removeBuffer(request);
		GIAITCDVo giaITCDVo = new GIAITCDVo();
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			obterInformacoesLogSefaz(request, giaITCDVo);
			giaITCDBe.verificaUsuarioServidor(giaITCDVo);
		}
		catch(SQLException e)
		{
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
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	   processFlow(VIEW_PESQUISAR_REIMPRIMIRGIA_GIA_ITCD, request, response, FORWARD);
	}
   
   /**
    * Este método tem por objetivo confirmar a GIA-ITCD.
    * 
    * <br><br>
    * <b>Passo</b>
    * <br>
    * <ol>
    *    <li> Validar.    </li>
    *    <li> Alterar o status para PENDENTE_DE_PROTOCOLO.    </li>
    * </ol>
    * 
    * 
    * 
    * 
    */
   private void confirmarGIAITCD(GIAITCDVo giaITCDVo)
      throws ConexaoException, ObjetoObrigatorioException, 
             ParametroObrigatorioException, IntegracaoException, 
             ConsultaException, RegistroExistenteException, 
             RegistroNaoPodeSerUtilizadoException, 
             DadoNecessarioInexistenteException, PersistenciaException, 
             LogException, AnotacaoException, ImpossivelCriptografarException, 
             IOException
   {
      StatusGIAITCDVo status = null;
      GIAITCDBe giaITCDBe = null;
      StatusGIAITCDBe statusGIAITCDBe = null;
      try
      {
         System.out.println("LOG : "+this.getClass().getSimpleName() +" - Confimar Reimprimir Gia");
         statusGIAITCDBe = new StatusGIAITCDBe();
         status = statusGIAITCDBe.procurarUltimoStatusCollectionVo(giaITCDVo.getStatusVo());
         
         if (Validador.isDominioNumericoValido(status.getStatusGIAITCD()) && status.getStatusGIAITCD().is(DomnStatusGIAITCD.EM_ELABORACAO))
         {
            giaITCDBe = new GIAITCDBe();
            giaITCDVo.setGiaConfirmada(new DomnSimNao(DomnSimNao.SIM));
            giaITCDVo.getTemporarioVo().setGiaConfirmada(new DomnSimNao(DomnSimNao.SIM));
          
            giaITCDBe.confirmarGIAITCD(giaITCDVo);
           
           
         }
      }
      catch (SQLException e)
      {
          throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if(giaITCDBe != null)
         {
            giaITCDBe.close();
            giaITCDBe = null;
         }
         if(statusGIAITCDBe != null)
         {
            statusGIAITCDBe.close();
            statusGIAITCDBe = null;
         }
      }
   }
   
}
