package br.gov.mt.sefaz.itc.modulo.generico.giaitcd;

import br.com.abaco.util.Paginador;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.EmailMessagingException;
import br.com.abaco.util.exceptions.EmailParametroObrigatorioException;
import br.com.abaco.util.exceptions.EmailUnsupportedEncodingException;
import br.com.abaco.util.exceptions.ImpossivelCriptografarException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;
import br.com.abaco.util.exceptions.SenhaInvalidaException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;
import br.com.abaco.util.http.JspUtil;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.Form;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaBe;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnFuncionalidadeOrigem;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsulta;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.http.HttpUtil;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.io.IOException;

import java.sql.SQLException;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por pesquisar GIA-ITCD. Essa servlet recebe requisição de quase todas as funcionalidades do sistema 
 * que precisam pesquisar uma GIA-ITCD.
 * @author Rogério Sanches de Oliveira
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.10 $
 * 
 */
public class GIAITCDPesquisar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_SOLICITAR_MOSTRAR_CONTRIBUINTE = 2;
	private static final int REQUISICAO_CONSULTAR_GIAITCD = 3;
	private static final int REQUISICAO_CONSULTAR_GIAITCD_PARA_ALTERAR = 4;	
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_CONSTRIBUINTE = 5;
	private static final int REQUISICAO_SELECIONAR_GIA_ITCD_PARA_ALTERAR = 6;
	private static final int REQUISICAO_DETALHAR_GIA_ITCD = 7;
	private static final int REQUISICAO_ESQUECI_SENHA = 8;	
	private static final int REQUISICAO_LISTAR_GIAITCD = 9;
	private static final int REQUISICAO_PESQUISAR_GIASITCD = 10;
	private static final int REQUISICAO_SOLICITAR_PESQUISAR_GIAITCD = 11;
	private static final int REQUISICAO_SOLICITAR_PESQUISAR_GIAITCD_PARA_RETORNAR_SERVLET_PAI = 12;
	private static final int REQUISICAO_CONSULTAR_GIAITCD_PARA_INATIVAR = 13;
	private static final int REQUISICAO_CONSULTAR_GIAITCD_PARA_REATIVAR = 14;
	private static final int REQUISICAO_SELECIONAR_GIA_ITCD_PARA_INATIVAR = 15;
	private static final int REQUISICAO_SELECIONAR_GIA_ITCD_PARA_REATIVAR = 16;
	private static final int REQUISICAO_CONSULTAR_GIAITCD_PARA_ALTERAR_STATUS_MANUAL = 17;
	private static final int REQUISICAO_SELECIONAR_GIA_ITCD_PARA_ALTERAR_STATUS_MANUAL = 18;
	private static final int REQUISICAO_CONSULTAR_GIAITCD_PARA_AVALIAR = 19;
	private static final int REQUISICAO_SELECIONAR_GIA_ITCD_PARA_AVALIAR = 20;
	private static final int REQUISICAO_SELECIONAR_GIA_ITCD_PARA_IMPRIMIR_DOCUMENTOS_AVALIACAO = 21;
	private static final int REQUISICAO_CONSULTAR_GIAITCD_PARA_IMPRIMIR_DOCUMENTOS_AVALIACAO = 22;
	private static final int REQUISICAO_TROCAR_PAGINA = 23;
   private static final int REQUISICAO_DETALHAR_LOG = 24;

	/**
	 * Este método trabalha colaborativamente com o método
	 * redirecionar. O método redirecionar determina a ação a ser tomada e
	 * processoRequest a executa.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, IntegracaoException, 
			  ConexaoException, EmailParametroObrigatorioException, EmailUnsupportedEncodingException, EmailMessagingException, 
			  CloneNotSupportedException, ImpossivelCriptografarException, ParametroObrigatorioException, SQLException, 
			  SenhaInvalidaException, IOException
   {
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarConsultarGIAITCD(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_GIAITCD:
				{
					consultarGIAITCD(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_CONSTRIBUINTE:
				{
					solicitarPesquisarContribuinte(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_MOSTRAR_CONTRIBUINTE:
				{
					mostraDadosContribuinte(request, response);
					break;
				}
			case REQUISICAO_LISTAR_GIAITCD:
				{
					listarGIASITCD(request, response);
					break;
				}
			case REQUISICAO_SELECIONAR_GIA_ITCD_PARA_ALTERAR:
				{
					selecionarGIAITCDParaAlterar(request, response);
					break;
				}			
			case REQUISICAO_SELECIONAR_GIA_ITCD_PARA_INATIVAR:
				{
					selecionarGIAITCDParaInativar(request, response);
					break;
				}
			case REQUISICAO_SELECIONAR_GIA_ITCD_PARA_REATIVAR:
				{
					selecionarGIAITCDParaReativar(request, response);
					break;
				}
			case REQUISICAO_SELECIONAR_GIA_ITCD_PARA_ALTERAR_STATUS_MANUAL:
				{
					selecionarGIAITCDParaAlterarStatusManual(request, response);
					break;
				}
			case REQUISICAO_SELECIONAR_GIA_ITCD_PARA_AVALIAR:
				{
					selecionarGIAITCDParaAvaliar(request, response);
					break;
				}
			case REQUISICAO_SELECIONAR_GIA_ITCD_PARA_IMPRIMIR_DOCUMENTOS_AVALIACAO:
				{
					selecionarGIAITCDParaImprimirDocumentosAvaliacao(request, response);
					break;
				}
			case REQUISICAO_PESQUISAR_GIASITCD:
				{
					consultarGIASITCD(request, response);
					break;
				}
			case REQUISICAO_DETALHAR_GIA_ITCD:
				{
					detalharGIAITCD(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_PESQUISAR_GIAITCD:
				{
					solicitarPesquisarGIAITCD(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_PESQUISAR_GIAITCD_PARA_RETORNAR_SERVLET_PAI:
				{
				   solicitarConsultarGIAITCDParaRetornarServletPai(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_GIAITCD_PARA_ALTERAR:
				{
					consultarGIAITCDParaAlterar(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_GIAITCD_PARA_INATIVAR:
				{
					consultarGIAITCDParaInativar(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_GIAITCD_PARA_REATIVAR:
				{
					consultarGIAITCDParaReativar(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_GIAITCD_PARA_ALTERAR_STATUS_MANUAL:
				{
					consultarGIAITCDParaAlterarStatusManual(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_GIAITCD_PARA_AVALIAR:
				{
					consultarGIAITCDParaAvaliar(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_GIAITCD_PARA_IMPRIMIR_DOCUMENTOS_AVALIACAO:
				{
					consultarGIAITCDParaImprimirDocumentosAvaliacao(request, response);
					break;
				}
			case REQUISICAO_TROCAR_PAGINA:
				{
					solicitarTrocarPagina(request, response);
					break;
				}
		}
	}

	/**
	 * Este método é responsável pela análise dos parâmetros e a
	 * tomada de decisão quanto ao controle do fluxo da aplicação. Para uma funcionalidade qualquer do sistema precisar pesquisar uma GIA-ITCD
	 * basta apenas colocar o atributo FormITC.GIAITCD_VO no request.
	 * @param request
	 * @return int
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO) != null)
		{
			return REQUISICAO_SOLICITAR_MOSTRAR_CONTRIBUINTE;
		}
	   else if(Validador.isObjetoValido(request.getAttribute(PARAMETRO_CONSULTAR_ALTERAR)))
	   {
	      return REQUISICAO_SOLICITAR_PESQUISAR_GIAITCD_PARA_RETORNAR_SERVLET_PAI;       
	   }		
		else if(Validador.isObjetoValido(request.getAttribute(PARAMETRO_CONSULTAR_INATIVAR)))
		{
			return REQUISICAO_SOLICITAR_PESQUISAR_GIAITCD_PARA_RETORNAR_SERVLET_PAI;
		}
		else if(Validador.isObjetoValido(request.getAttribute(PARAMETRO_CONSULTAR_REATIVAR)))
		{
			return REQUISICAO_SOLICITAR_PESQUISAR_GIAITCD_PARA_RETORNAR_SERVLET_PAI;
		}
		else if(Validador.isObjetoValido(request.getAttribute(PARAMETRO_CONSULTAR_ALTERAR_STATUS_MANUAL)))
		{
			return REQUISICAO_SOLICITAR_PESQUISAR_GIAITCD_PARA_RETORNAR_SERVLET_PAI;
		}
		else if(Validador.isObjetoValido(request.getAttribute(PARAMETRO_CONSULTAR_AVALIAR)))
		{
			return REQUISICAO_SOLICITAR_PESQUISAR_GIAITCD_PARA_RETORNAR_SERVLET_PAI;
		}
		else if(Validador.isObjetoValido(request.getAttribute(PARAMETRO_CONSULTAR_IMPRIMIR_DOCUMENTOS_AVALIACAO)))
		{
			return REQUISICAO_SOLICITAR_PESQUISAR_GIAITCD_PARA_RETORNAR_SERVLET_PAI;
		}
	   else if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR)))
	   {
	      if(Validador.isObjetoValido(getBuffer(request).getAttribute(ORIGEM)))
	      {
	         if(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.ALTERAR_GIA_ITCD)
	         {
	            return REQUISICAO_CONSULTAR_GIAITCD_PARA_ALTERAR;
	         }
				else if(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.CONSULTAR_GIA_ITCD)
				{
					return REQUISICAO_CONSULTAR_GIAITCD;
				}
				else if(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.INATIVAR_GIA_ITCD)
				{
					return REQUISICAO_CONSULTAR_GIAITCD_PARA_INATIVAR;
				}
				else if(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.REATIVAR_GIA_ITCD)
				{
					return REQUISICAO_CONSULTAR_GIAITCD_PARA_REATIVAR;
				}
				else if(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.ALTERAR_STATUS_MANUAL)
							
				{
					return REQUISICAO_CONSULTAR_GIAITCD_PARA_ALTERAR_STATUS_MANUAL;
				}
				else if((StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.AVALIAR_GIA_ITCD)
				   ||(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.ALTERAR_AVALIACAO_GIA_ITCD))
				{
					return REQUISICAO_CONSULTAR_GIAITCD_PARA_AVALIAR;
				}
				else if(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.IMPRIMIR_DOCUMENTOS_AVALIACAO)
				{
					return REQUISICAO_CONSULTAR_GIAITCD_PARA_IMPRIMIR_DOCUMENTOS_AVALIACAO;
				}
	      }
	      return REQUISICAO_CONSULTAR_GIAITCD;
	   }
	   else if (Validador.isStringValida(request.getParameter(BOTAO_LISTAR_GIAITCD)))
	   {
	      return REQUISICAO_LISTAR_GIAITCD;
	   }		
	   else if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_GIASITCD)))
	   {
	      if(Validador.isObjetoValido(getBuffer(request).getAttribute(ORIGEM)))
	      {
	         if(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.ALTERAR_GIA_ITCD)
	         {
					return REQUISICAO_SELECIONAR_GIA_ITCD_PARA_ALTERAR;
				}
				else if(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.CONSULTAR_GIA_ITCD)
				{
					return REQUISICAO_DETALHAR_GIA_ITCD;
				}
			   else if(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.INATIVAR_GIA_ITCD)
			   {
			      return REQUISICAO_SELECIONAR_GIA_ITCD_PARA_INATIVAR;
			   }
			   else if(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.REATIVAR_GIA_ITCD)
			   {
			      return REQUISICAO_SELECIONAR_GIA_ITCD_PARA_REATIVAR;
			   }	
				else if(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.ALTERAR_STATUS_MANUAL)
				{
					return REQUISICAO_SELECIONAR_GIA_ITCD_PARA_ALTERAR_STATUS_MANUAL;
				}
			   else if((StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.AVALIAR_GIA_ITCD)
			      ||(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.ALTERAR_AVALIACAO_GIA_ITCD))
			   {
			      return REQUISICAO_SELECIONAR_GIA_ITCD_PARA_AVALIAR;
			   }				
				else if(StringUtil.toInt(getBuffer(request).getAttribute(ORIGEM).toString()) == DomnFuncionalidadeOrigem.IMPRIMIR_DOCUMENTOS_AVALIACAO)
				{
					return REQUISICAO_SELECIONAR_GIA_ITCD_PARA_IMPRIMIR_DOCUMENTOS_AVALIACAO;
				}
			}
	      return REQUISICAO_PESQUISAR_GIASITCD;
	   }		
		else if (request.getAttribute(GIAITCD_VO) != null)
		{
			return REQUISICAO_SOLICITAR_PESQUISAR_GIAITCD;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_CONTRIBUINTE)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_CONSTRIBUINTE;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_NOVA_SENHA)))
		{
			return REQUISICAO_ESQUECI_SENHA;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_TROCAR_PAGINA)))
		{
			return REQUISICAO_TROCAR_PAGINA;
		}
	   else if (Validador.isStringValida(request.getParameter(PARAMETRO_DETALHAR_LOG)))
	   {
	      return REQUISICAO_DETALHAR_LOG;
	   }
		return REQUISICAO_VAZIA;
	}

	/**
	 * Este método é acionado para verificar se o usuário é servidor sefaz, lotado na  AGENFA, CGED, GIOR
	 * @param request
	 * @param response
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitarConsultarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, IntegracaoException, ConexaoException
	{
	   removeBuffer(request);
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
	   GIAITCDBe giaITCDBe = null;
	   try
	   {  
      
	      giaITCDBe = new GIAITCDBe();
	      try
	      {
				obterInformacoesLogSefaz(request, giaITCDVo);
				giaITCDVo.setOrigem(DomnFuncionalidadeOrigem.CONSULTAR_GIA_ITCD);
				giaITCDBe.verificaUsuarioAptoAcessoFuncionalidade(giaITCDVo);
	      }
	      catch (RegistroNaoPodeSerUtilizadoException e)
	      {
	         giaITCDVo.setMensagem(e.getMessage());
	         request.setAttribute(ENTIDADE_VO, giaITCDVo);
	         processFlow(VIEW_ERRO, request, response, FORWARD);
				return;
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
	    getBuffer(request).setAttribute(ORIGEM, ""+giaITCDVo.getOrigem(),request);
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	   processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
	}

	/**
	 * Método responsável por solicitar uma consulta da GIA-ITCD
	 * @param request
	 * @param response
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void consultarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, IntegracaoException, 
			  ConexaoException, ImpossivelCriptografarException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);    
	   GIAITCDBe giaITCDBe = null;
	   try
	   {
	      giaITCDBe = new GIAITCDBe();
	      try
	      {
	         HttpUtil.validarCaracteresInformadosModAberto(getBuffer(request) , request.getParameter(Form.CAMPO_IMAGEM), giaITCDVo.getLogSefazVo());
				giaITCDVo = giaITCDBe.solicitarConsultarGIAITCDAtiva(giaITCDVo);
	      }
	      catch(ParametroObrigatorioException e)
	      {
	         request.setAttribute(EXCEPTION, e);       
	         request.setAttribute(GIAITCD_VO, giaITCDVo);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
				return;
	      }
         catch (IOException e)
         {
             request.setAttribute(EXCEPTION, e);       
             request.setAttribute(GIAITCD_VO, giaITCDVo);
             processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
             return;
         }
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
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		if(giaITCDVo.getOrigem() == DomnFuncionalidadeOrigem.CONSULTAR_GIA_ITCD)
		{
		   if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == DomnTipoProcesso.INVENTARIO_ARROLAMENTO)
		   {
		      consultarContribuinteTipoGiaArrolamento(request, response);
		   }
		   else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == DomnTipoProcesso.DOACAO)
		   {
		      consultarContribuinteTipoGiaDoacao(request, response);
		   }
		   else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA)
		   {
		      consultarContribuinteTipoGiaSeparacaoDivorcio(request, response);
		   }			
		}
	}

	/**
	 *  Método que recupera do request os dados informados pelo usuário
	 * @param request
	 * @return GIAITCDVo
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private GIAITCDVo controladorInterativoJSP(HttpServletRequest request)
	{
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		if (giaITCDVo == null)
		{
			giaITCDVo = new GIAITCDVo();
			giaITCDVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SENHA)))
		{
			giaITCDVo.setSenha(request.getParameter(CAMPO_SENHA));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_CODIGO_GIA)))
		{
			giaITCDVo.setCodigo(StringUtil.toLong(request.getParameter(CAMPO_CODIGO_GIA)));
		}
		if(Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_GIASITCD)))
		{
			giaITCDVo.setCodigo(StringUtil.toLong(request.getParameter(BOTAO_PESQUISAR_GIASITCD)));
		}
		if(Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_CONSULTA)))
		{
			giaITCDVo.setTipoConsultaGia(new DomnTipoConsulta(StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_CONSULTA))));
		}
		return giaITCDVo;
	}

	/**
	 * Este método é responsável por chamar o componente de pesquisa do contribuinte.
	 * Através desta chamada, temos acesso ao componente onde podemos pesquisar o contribuinte
	 * por CPF ou CNPJ.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitarPesquisarContribuinte(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		String codOriginal = JspUtil.getCodigoPermissaoOriginal(request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(codOriginal, FormAcesso.NOME_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	}

	/**
	 * Método que direciona para a servlet de pesquisa específica da GIA-ITCD Inventario Arrolamento
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void consultarContribuinteTipoGiaArrolamento(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_PESQUISAR_GIAITCD_INVENTARIO_ARROLAMENTO, request), request, response, FORWARD);
	}

	/**
	 * Método que direciona para a servlet de pesquisa específica da GIA-ITCD Doacao
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void consultarContribuinteTipoGiaDoacao(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_PESQUISAR_GIAITCD_DOACAO, request), request, response, FORWARD);
	}

	/**
	 * Método que direciona para a servlet de pesquisa específica da GIA-ITCD SeparacaoDivorcio
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void consultarContribuinteTipoGiaSeparacaoDivorcio(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_PESQUISAR_GIAITCD_SEPARACAO_DIVORCIO, request), request, response, FORWARD);
	}

	/**
	 * Método responsável por solicitar uma consulta da GIA-ITCD
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConexaoException
	 * @implemented by Rogério Sanches de Oliveira
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void consultaPesquisarTipoGIA(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, IntegracaoException, 
			  ConexaoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		GIAITCDVo giaITCDVoRetorno = new GIAITCDVo();
		Validador.isObjetoValido(giaITCDVo);
		GIAITCDBe giaITCDBe = null;
		try
		{
		   giaITCDBe = new GIAITCDBe();
			try
			{
			   obterInformacoesLogSefaz(request, giaITCDVo);
			   giaITCDVoRetorno = new GIAITCDVo(giaITCDVo);
			   giaITCDVoRetorno.setConsultaCompleta(true);
			   giaITCDVoRetorno.setLogSefazVo(giaITCDVo.getLogSefazVo());
			   giaITCDVoRetorno = giaITCDBe.consultarGIAITCDAtiva(giaITCDVoRetorno);				
			}
		   catch (ParametroObrigatorioException e)
		   {
		      request.setAttribute(EXCEPTION, e);
		      processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
				return;
		   }
         catch (IOException e)
         {
             request.setAttribute(EXCEPTION, e);
             processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
             return;
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
		if (JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD))
		{
			if (giaITCDVoRetorno.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == 
						(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
				consultarContribuinteTipoGiaArrolamento(request, response);
			}
			else if (giaITCDVoRetorno.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == 
						(DomnTipoProcesso.DOACAO))
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
				consultarContribuinteTipoGiaDoacao(request, response);
			}
			else if (giaITCDVoRetorno.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == 
						(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
				consultarContribuinteTipoGiaSeparacaoDivorcio(request, response);
			}
		}
	}

	/**
	 * Método responsável por mostrar os Dados do Contribuinte, recebe do request e redireciona para a JSP
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void mostraDadosContribuinte(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		ContribuinteIntegracaoVo responsavelVo = 
				 (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
		giaITCDVo.setResponsavelVo(responsavelVo);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
	}

	/**Método que será acionado quando o usuario for da SEFAZ, listar gias de acordo com o CPF do contribuinte
	 * 
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void listarGIASITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, ConexaoException, 
			  IntegracaoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		Validador.isObjetoValido(giaITCDVo);
		GIAITCDVo giaITCDVoRetorno = new GIAITCDVo();
		GIAITCDBe giaITCDBe = null;
	   GIAITCDDoacaoSucessivaBe giaDoacaoBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
		   giaDoacaoBe = new GIAITCDDoacaoSucessivaBe();
			giaITCDVo = new GIAITCDVo(giaITCDVo);
			giaITCDVo.setConsultaCompleta(true);
			obterInformacoesLogSefaz(request, giaITCDVo);
			try
			{
				giaITCDVoRetorno = giaITCDBe.listarGIAITCDPorCPFResponsavel(giaITCDVo);                      
			   
				if(Validador.isCollectionValida(giaITCDVoRetorno.getCollVO())){
               for (Iterator<GIAITCDVo> it = giaITCDVoRetorno.getCollVO().iterator(); it.hasNext(); ) {
                  GIAITCDVo item =  it.next();                  
                  if(giaDoacaoBe.verificaSeGiaPresenteEmDoacaoSucessiva(item.getCodigo())){
                     item.setGiaUtilizadaParaDoacaoSucessiva(true);
                  }
               }            
					giaITCDVoRetorno.setPaginador(new Paginador<GIAITCDVo>(giaITCDVoRetorno.getCollVO(), 15));
				}
			}
			catch (ParametroObrigatorioException e)
			{
				request.setAttribute(EXCEPTION, e);
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo.getParametroConsulta(), request);
				processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
				return;
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
		   if (giaDoacaoBe != null)
		   {
		      giaDoacaoBe.close();
		      giaDoacaoBe = null;
		   }
		}
		giaITCDVo.getParametroConsulta().setCollVO(giaITCDVoRetorno.getCollVO());
		giaITCDVo.getParametroConsulta().setPaginador(giaITCDVoRetorno.getPaginador());
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo.getParametroConsulta(), request);
		processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);			
	}

	/**
	 * Método responsável por solicitar uma consulta de GIA-ITCD para o Be.
	 * Dependendo da funcionalidade a pesquisa é feita de através de outro método no Be.
	 * @param request
	 * @param response
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void consultarGIASITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, IntegracaoException, 
			  ConexaoException, 
             IOException
   {
		GIAITCDVo giaITCDConsultaVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		GIAITCDVo giaITCDVoRetorno = null;
		//Código adquirido na JSP que lista a GIA, apenas para a funcionalidade de pequisar.
		long codigoGIA = StringUtil.toLong(request.getParameter(BOTAO_PESQUISAR_GIASITCD));
		giaITCDConsultaVo.setCodigo(codigoGIA);
		GIAITCDBe giaITCDBe = null;
		GIAITCDVo giaITCDVo = null;
		if (JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD))
		{
			try
			{
				for (Iterator it = giaITCDConsultaVo.getCollVO().iterator(); it.hasNext(); )
				{
					GIAITCDVo gia = (GIAITCDVo) it.next();
					if (gia.getCodigo() == codigoGIA)
					{
						giaITCDVoRetorno = gia;
						break;
					}
				}
				giaITCDVo = new GIAITCDVo(giaITCDVoRetorno);
				giaITCDVo.setConsultaCompleta(true);
				giaITCDVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
				giaITCDBe = new GIAITCDBe();
				giaITCDVoRetorno = giaITCDBe.consultarGIAITCDAtiva(giaITCDVo);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
			}
			catch (ParametroObrigatorioException e)
			{
				request.setAttribute(EXCEPTION, e);
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDConsultaVo, request);
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
		if (!JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD) && 
				 !JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_ALTERAR_GIAITCD))
		{
			giaITCDConsultaVo = controladorInterativoJSP(request);
			giaITCDVo = new GIAITCDVo(giaITCDConsultaVo);
			try
			{
				giaITCDVo.setConsultaCompleta(true);
				obterInformacoesLogSefaz(request, giaITCDVo);
				giaITCDBe = new GIAITCDBe();
				//TODO Verificar quem é a funcionalidade pai e chamar o consultar específico de cada um
				if (JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_REATIVAR_GIAITCD_PESQUISAR_GIAITCD))
				{
					giaITCDVoRetorno = giaITCDBe.consultarGIAITCDInativa(giaITCDVo);
				}
				else if (JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_IMPRIMIR_AVALIACAO_GIAITCD_PESQUISAR_GIAITCD))
				{
					giaITCDVoRetorno = giaITCDBe.consultarGIAITCDAvaliar(giaITCDVo);
				}
				else
				{
					giaITCDVoRetorno = giaITCDBe.consultarGIAITCDAtiva(giaITCDVo);
				}
				if (!JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD))
				{
					getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
					processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
			}
			catch (ParametroObrigatorioException e)
			{
				request.setAttribute(EXCEPTION, e);
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDConsultaVo, request);
				if (!JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD))
				{
					processFlow(VIEW_CONSULTAR_GIAITCD, request, response, FORWARD);
					return;
				}
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
		if (JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD))
		{
			if (giaITCDVoRetorno.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == 
						(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
				consultarContribuinteTipoGiaArrolamento(request, response);
			}
			else if (giaITCDVoRetorno.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == 
						(DomnTipoProcesso.DOACAO))
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
				consultarContribuinteTipoGiaDoacao(request, response);
			}
			else if (giaITCDVoRetorno.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == 
						(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
				consultarContribuinteTipoGiaSeparacaoDivorcio(request, response);
			}
		}
	}

	/**
	 * Método responsável por solicitar detalhamento da GIA pesquisada na funcionalidade Consultar GIA-ITCD.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void detalharGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, ConsultaException, 
			  IntegracaoException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
	   GIAITCDVo giaITCDVoRetorno = new GIAITCDVo();
	   GIAITCDBe giaITCDBe = null;
	   try
	   {
	      giaITCDBe = new GIAITCDBe();
	      try
	      {
	         giaITCDVoRetorno = giaITCDBe.selecionarGIAITCD(giaITCDVo);
	         giaITCDVoRetorno = giaITCDBe.solicitarConsultarGIAITCDAtiva(giaITCDVoRetorno);
	         giaITCDVoRetorno.setCollVO(giaITCDVo.getCollVO());
				giaITCDVoRetorno.setPaginador(giaITCDVo.getPaginador());
				
	      }
	      catch(ParametroObrigatorioException e)
	      {
	         giaITCDVo.setCodigo(0);       
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
				return;
	      }
         catch (IOException e)
         {
             giaITCDVo.setCodigo(0);       
             request.setAttribute(EXCEPTION, e);       
             getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
             processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
             return;
         }
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
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
	   if(giaITCDVoRetorno.getOrigem() == DomnFuncionalidadeOrigem.CONSULTAR_GIA_ITCD)
	   {
	      if (giaITCDVoRetorno.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == DomnTipoProcesso.INVENTARIO_ARROLAMENTO)
	      {
	         consultarContribuinteTipoGiaArrolamento(request, response);
	      }
	      else if (giaITCDVoRetorno.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == DomnTipoProcesso.DOACAO)
	      {
	         consultarContribuinteTipoGiaDoacao(request, response);
	      }
	      else if (giaITCDVoRetorno.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA)
	      {
	         consultarContribuinteTipoGiaSeparacaoDivorcio(request, response);
	      }        
	   }		
	}

	/**
	 * Método responsável por solicitar consulta completa da GIA-ITCD selecionada e exibir seus dados para funcionalidade Alterar GIA-ITCD.
	 * @param request
	 * @param response
	 * @throws ConexaoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void selecionarGIAITCDParaAlterar(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
			  ObjetoObrigatorioException, ConsultaException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ImpossivelCriptografarException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
	   GIAITCDVo giaITCDVoRetorno = new GIAITCDVo();
	   GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			try
			{
				giaITCDVoRetorno = giaITCDBe.selecionarGIAITCDAlterar(giaITCDVo);
			   giaITCDVoRetorno.setCollVO(giaITCDVo.getCollVO());
			}
			catch(RegistroNaoPodeSerUtilizadoException e)
			{
				giaITCDVo.setCodigo(0);			
				request.setAttribute(EXCEPTION, e);
			   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
				processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);  
				return;
			}
		   catch(ParametroObrigatorioException e)
		   {
		      giaITCDVo.setCodigo(0);       
		      request.setAttribute(EXCEPTION, e);       
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		      processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
		      return;
		   }	
			catch(SenhaInvalidaException e)
			{
			   giaITCDVo.setCodigo(0);       
			   request.setAttribute(EXCEPTION, e);       
			   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			   processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
			   return;				
			}
         catch (IOException e)
         {
             giaITCDVo.setCodigo(0);       
             request.setAttribute(EXCEPTION, e);       
             getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
             processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
             return;
         }
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
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
	   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);
	   return;
	}

	/**
	 * Método que redireciona para a JSP de consulta GIA, quando a requisição vem de outra servlet
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarPesquisarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
	}

	/**
	 * Método responsável por verificar como o usuário irá realizar a consulta e também se o mesmo pode fazê-la.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarConsultarGIAITCDParaRetornarServletPai(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
	   GIAITCDBe giaITCDBe = null;
	   try
	   {  
	      giaITCDBe = new GIAITCDBe();
	      try
	      {
	         giaITCDBe.verificaUsuarioAptoAcessoFuncionalidade(giaITCDVo);
	      }
	      catch (RegistroNaoPodeSerUtilizadoException e)
	      {
	         giaITCDVo.setMensagem(e.getMessage());
	         request.setAttribute(ENTIDADE_VO, giaITCDVo);
	         processFlow(VIEW_ERRO, request, response, FORWARD);
	         return;
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
	   getBuffer(request).setAttribute(ORIGEM, ""+giaITCDVo.getOrigem(),request);
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	   processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
	   return;
	}

	/**
	 * Método reponsável por realizar a consulta da GIA-ITCD para funcionalidade Alterar GIA-ITCD.
	 * @param request
	 * @param response
	 * @throws ConexaoException
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void consultarGIAITCDParaAlterar(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
			  ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, 
			  ConsultaException, IntegracaoException, ImpossivelCriptografarException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		GIAITCDVo retorno = new GIAITCDVo();
		GIAITCDBe giaITCDBe = null;
		try
		{		   
			giaITCDBe = new GIAITCDBe();
			try
			{
			   HttpUtil.validarCaracteresInformadosModAberto(getBuffer(request) , request.getParameter(Form.CAMPO_IMAGEM), giaITCDVo.getLogSefazVo());
				retorno = giaITCDBe.consultarGIAITCDAlterar(giaITCDVo);
			}
			catch(ParametroObrigatorioException e)
			{
				request.setAttribute(EXCEPTION, e);			
			   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			   processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD); 
			   return;
			}
		   catch (RegistroNaoPodeSerUtilizadoException e)
		   {
		      request.setAttribute(EXCEPTION, e);
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		      processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
		      return;
		   }
			catch(SenhaInvalidaException e)
			{
			   request.setAttribute(EXCEPTION, e);
			   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			   processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
			   return;				
			}
         catch (IOException e)
         {
            request.setAttribute(EXCEPTION, e);
            getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
            processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
            return;  
         }
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
	   getBuffer(request).setAttribute(GIAITCD_VO, retorno, request);
	   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);		
	   return;
	}

	/**
	 * Método responsável por consultar uma GIA-ITCD para Inativar.
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
	private void consultarGIAITCDParaInativar(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, ConexaoException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);  
	   GIAITCDVo retorno = new GIAITCDVo();
	   GIAITCDBe giaITCDBe = null;
	   try
	   {
	      giaITCDBe = new GIAITCDBe();
	      try
	      {
	         retorno = giaITCDBe.consultarGIAITCDInativar(giaITCDVo);
	      }
	      catch(ParametroObrigatorioException e)
	      {
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);  
	         return;
	      }
			catch(RegistroNaoPodeSerUtilizadoException e)
			{
			   request.setAttribute(EXCEPTION, e);       
			   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			   processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);  
			   return;				
			}
         catch (IOException e)
         {
             request.setAttribute(EXCEPTION, e);       
             getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
             processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);  
             return;  
         }
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
	   getBuffer(request).setAttribute(GIAITCD_VO, retorno, request);
	   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);      		
	   return;
	}

	/**
	 * Método responsável por consultar uma GIA-ITCD e validar se a mesma pode ser reativada.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void consultarGIAITCDParaReativar(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);   
		GIAITCDVo retorno = new GIAITCDVo();
	   GIAITCDBe giaITCDBe = null;
	   try
	   {
	      giaITCDBe = new GIAITCDBe();
	      try
	      {
	         retorno = giaITCDBe.consultarGIAITCDReativar(giaITCDVo);
	      }
	      catch(ParametroObrigatorioException e)
	      {
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
	         return;
	      }
	      catch (RegistroNaoPodeSerUtilizadoException e)
	      {
	         request.setAttribute(EXCEPTION, e);
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
	         return;
	      }
         catch (IOException e)
         {
             request.setAttribute(EXCEPTION, e);
             getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
             processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
             return;
         }
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
	   getBuffer(request).setAttribute(GIAITCD_VO, retorno, request);
	   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);      	
	   return;
	}

	/**
	 * Método responsável por solicitar consulta completa da GIA-ITCD selecionada e exibir seus dados para funcionalidade Inativar GIA-ITCD.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void selecionarGIAITCDParaInativar(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
	   GIAITCDVo giaITCDVoRetorno = new GIAITCDVo();
	   GIAITCDBe giaITCDBe = null;
	   try
	   {
	      giaITCDBe = new GIAITCDBe();
	      try
	      {
	         giaITCDVoRetorno = giaITCDBe.selecionarGIAITCDInativar(giaITCDVo);
	         giaITCDVoRetorno.setCollVO(giaITCDVo.getCollVO());
	      }
	      catch(ParametroObrigatorioException e)
	      {
	         giaITCDVo.setCodigo(0);       
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
	         return;
	      }    
			catch(RegistroNaoPodeSerUtilizadoException e)
			{
			   giaITCDVo.setCodigo(0);       
			   request.setAttribute(EXCEPTION, e);       
			   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			   processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
			   return;				
			}
         catch (IOException e)
         {
            giaITCDVo.setCodigo(0);       
            request.setAttribute(EXCEPTION, e);       
            getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
            processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
            return;
         }
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
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
	   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);      
	   return;
	}

	/**
	 * Método responsável por solicitar consulta completa da GIA-ITCD selecionada e exibir seus dados para funcionalidade Reativar GIA-ITCD.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void selecionarGIAITCDParaReativar(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
	   GIAITCDVo giaITCDVoRetorno = new GIAITCDVo();
	   GIAITCDBe giaITCDBe = null;
	   try
	   {
	      giaITCDBe = new GIAITCDBe();
	      try
	      {
	         giaITCDVoRetorno = giaITCDBe.selecionarGIAITCDReativar(giaITCDVo);
	         giaITCDVoRetorno.setCollVO(giaITCDVo.getCollVO());
	      }
	      catch(RegistroNaoPodeSerUtilizadoException e)
	      {
	         giaITCDVo.setCodigo(0);       
	         request.setAttribute(EXCEPTION, e);
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
	         return;
	      }
	      catch(ParametroObrigatorioException e)
	      {
	         giaITCDVo.setCodigo(0);       
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
	         return;
	      }
         catch (IOException e)
         {
            giaITCDVo.setCodigo(0);       
            request.setAttribute(EXCEPTION, e);       
            getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
            processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
            return;
         }
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
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
	   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);      	
	   return;
	}

	/**
	 * Método responsável por consultar uma gia para funcionalidade GIA-TICD Alterar Status Manual.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void consultarGIAITCDParaAlterarStatusManual(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
			  IntegracaoException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		GIAITCDVo retorno = new GIAITCDVo();
	   GIAITCDBe giaITCDBe = null;
	   try
	   {
	      giaITCDBe = new GIAITCDBe();
	      try
	      {
	         retorno = giaITCDBe.consultarGIAITCDAlterarStatusManual(giaITCDVo);           
	      }
	      catch(ParametroObrigatorioException e)
	      {
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
	         return;
	      }
	      catch (RegistroNaoPodeSerUtilizadoException e)
	      {
	         request.setAttribute(EXCEPTION, e);
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
	         return;
	      }
         catch (IOException e)
         {
             request.setAttribute(EXCEPTION, e);
             getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
             processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
             return;
         }
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
	   getBuffer(request).setAttribute(GIAITCD_VO, retorno, request);
	   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);      
	   return;
	}

	/**
	 * Método responsável por solicitar consulta completa da GIA-ITCD selecionada e exibir seus dados para funcionalidade GIA-ITCD Alterar Status Manual.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void selecionarGIAITCDParaAlterarStatusManual(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, ConexaoException, 
			  IntegracaoException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
	   GIAITCDVo giaITCDVoRetorno = new GIAITCDVo();
	   GIAITCDBe giaITCDBe = null;
	   try
	   {
	      giaITCDBe = new GIAITCDBe();
	      try
	      {
	         giaITCDVoRetorno = giaITCDBe.selecionarGIAITCDAlterarStatusManual(giaITCDVo);
	         giaITCDVoRetorno.setCollVO(giaITCDVo.getCollVO());
	      }
	      catch(RegistroNaoPodeSerUtilizadoException e)
	      {
	         giaITCDVo.setCodigo(0);       
	         request.setAttribute(EXCEPTION, e);
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD); 
				return;
	      }
	      catch(ParametroObrigatorioException e)
	      {
	         giaITCDVo.setCodigo(0);       
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
	         return;
	      }
         catch (IOException e)
         {
            giaITCDVo.setCodigo(0);       
            request.setAttribute(EXCEPTION, e);       
            getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
            processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
            return;
         }
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
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
	   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);      
	   return;
	}

	/**
	 * Método responsável por consultar uma GIA-ITCD e validar se a mesma pode ser avaliada.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void consultarGIAITCDParaAvaliar(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException                                                                                                          
   {
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);    
		GIAITCDVo retorno = new GIAITCDVo();
	   GIAITCDBe giaITCDBe = null;
	   try
	   {
			giaITCDBe = new GIAITCDBe();
			try
			{
				if(giaITCDVo.getOrigem() == DomnFuncionalidadeOrigem.AVALIAR_GIA_ITCD)
				{
				   retorno = giaITCDBe.solicitarConsultarGIAITCDAvaliar(giaITCDVo);
				}
				else
				{
				   retorno = giaITCDBe.solicitarConsultarGIAITCDAvaliarAlterar(giaITCDVo);           
				}
			}
			catch(RegistroNaoPodeSerUtilizadoException e)
	      {
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
	         return;
	      }
	      catch(ParametroObrigatorioException e)
	      {
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
	         return;
	      }
	      catch(IOException e)
	      {
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
	         return;
	      }
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
      
      if(retorno.isReabrirAvaliacao())
      {   
          retorno.getStatusVo().setJustificativaAlteracao("");        
          //getBuffer(request).setAttribute(Form.PARAMETRO_EXCLUIR_REABRIR_AVALIACAO, Form.PARAMETRO_EXCLUIR_REABRIR_AVALIACAO, request);
          request.setAttribute(Form.PARAMETRO_REABRIR_AVALIACAO_BEM, Form.PARAMETRO_REABRIR_AVALIACAO_BEM);
      }  
	   if(retorno.isExcluirAvaliacao())
	   {   
	       retorno.getStatusVo().setJustificativaAlteracao("");        
	       //getBuffer(request).setAttribute(Form.PARAMETRO_EXCLUIR_AVALIACAO_BEM, Form.PARAMETRO_EXCLUIR_AVALIACAO_BEM, request);
          request.setAttribute(Form.PARAMETRO_EXCLUIR_AVALIACAO_BEM, Form.PARAMETRO_EXCLUIR_AVALIACAO_BEM);
	   }
         getBuffer(request).setAttribute(GIAITCD_VO, retorno, request);
         processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);        
	   return;

	}

	/**
	 * Método responsável por solicitar consulta completa da GIA-ITCD selecionada e exibir seus dados para funcionalidade Avaliar GIA-ITCD.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void selecionarGIAITCDParaAvaliar(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, ConexaoException, 
			  IntegracaoException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
	   GIAITCDVo giaITCDVoRetorno = new GIAITCDVo();
	   GIAITCDBe giaITCDBe = null;
	   try
	   {
	      giaITCDBe = new GIAITCDBe();
	      try
	      {
	         giaITCDVoRetorno = giaITCDBe.selecionarGIAITCDAvaliar(giaITCDVo);
	         giaITCDVoRetorno.setCollVO(giaITCDVo.getCollVO());
	      }
	      catch(RegistroNaoPodeSerUtilizadoException e)
	      {
	         giaITCDVo.setCodigo(0);       
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
	         return;
	      }        
	      catch(ParametroObrigatorioException e)
	      {
	         giaITCDVo.setCodigo(0);       
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
	         return;
	      }
         catch (IOException e)
         {
            giaITCDVo.setCodigo(0);       
            request.setAttribute(EXCEPTION, e);       
            getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
            processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
            return;
         }
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
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
	   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);      
	   return;
	}

	/**
	 * Método responsável por consultar uma GIA-ITCD e validar se a mesma está apta para ser impresso os documentos de sua avaliação.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void consultarGIAITCDParaImprimirDocumentosAvaliacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);   
		GIAITCDVo retorno = new GIAITCDVo();
	   GIAITCDBe giaITCDBe = null;
	   try
	   {
	      giaITCDBe = new GIAITCDBe();
	      try
	      {
	         retorno = giaITCDBe.solicitarConsultarGIAITCDParaImprimirDocumentosAvaliacao(giaITCDVo);
	      }
	      catch(ParametroObrigatorioException e)
	      {
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
	         return;
	      }
	      catch(RegistroNaoPodeSerUtilizadoException e)
	      {
	         request.setAttribute(EXCEPTION, e);       
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	         processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
	         return;
	      }
         catch (IOException e)
         {
            request.setAttribute(EXCEPTION, e);       
            getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
            processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
            return;
         }
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
	   getBuffer(request).setAttribute(GIAITCD_VO, retorno, request);
	   processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);            	
	   return;
	}

	/**
	 * Método responsável por solicitar consulta completa da GIA-ITCD selecionada e exibir seus dados para funcionalidade Imprimir Documentos da Avaliação.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void selecionarGIAITCDParaImprimirDocumentosAvaliacao(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, ConexaoException, 
			  IntegracaoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		GIAITCDVo giaITCDVoRetorno = new GIAITCDVo();
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			try
			{
				giaITCDVoRetorno = giaITCDBe.selecionarGIAITCDImprimirDocumentosAvaliacao(giaITCDVo);
			   giaITCDVoRetorno.setCollVO(giaITCDVo.getCollVO());
			}
			catch(RegistroNaoPodeSerUtilizadoException e)
			{
				giaITCDVo.setCodigo(0);       
				request.setAttribute(EXCEPTION, e);       
			   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
				processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
			   return;
			}        
			catch(ParametroObrigatorioException e)
			{
				giaITCDVo.setCodigo(0);       
				request.setAttribute(EXCEPTION, e);       
			   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
				processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
			   return;
			}
         catch (IOException e)
         {
            giaITCDVo.setCodigo(0);       
            request.setAttribute(EXCEPTION, e);       
            getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
            processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);                           
            return;
         }
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
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVoRetorno, request);
		processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);      
	   return;
	}

	/**
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarTrocarPagina(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo gia = controladorInterativoJSP(request);
		Validador.validaObjeto(gia);
		gia.getPaginador().setIrPagina(StringUtil.toInt(request.getParameter(PARAMETRO_TROCAR_PAGINA)));
		gia.getPaginador().trocarPagina();
		getBuffer(request).setAttribute(GIAITCD_VO, gia, request);
	   processFlow(VIEW_SOLICITA_CONSULTAR_TIPO_GIAITCD, request, response, FORWARD);
	}
}
