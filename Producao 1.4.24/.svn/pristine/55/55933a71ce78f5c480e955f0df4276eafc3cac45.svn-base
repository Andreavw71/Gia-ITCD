package br.gov.mt.sefaz.itc.util.http;

import br.com.abaco.util.MsgException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.CodigoPermissaoNaoInformadoException;
import br.com.abaco.util.exceptions.FileUploadAbacoException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ProibidoRequisicaoDoGETException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.exceptions.TituloNaoInformadoException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.http.AbacoBuffer;
import br.com.abaco.util.http.JspUtil;
import br.com.abaco.util.logsefaz.LogSefazVo;

import br.gov.mt.sefaz.acessoweb.http.InfoClienteBean;
import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.excecoes.AjaxError;
import br.gov.mt.sefaz.itc.util.excecoes.AjaxException;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.FormITC;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.io.IOException;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sefaz.mt.acessoweb.Usuario;


public abstract class AbstractServletITCD  extends HttpServlet implements MensagemErro, Flow
{
	private static final String TITULO_SISTEMA_SEM_TITULO = "SISTEMA SEM TÍTULO.";
	
	protected static final String ATRIBUTO_TITULO_FUNCIONALIDADE = "atributoTituloFuncionalidade";
	protected static final String ATRIBUTO_TITULO_SISTEMA = "tituloSistema";
	public static final String ATRIBUTO_CODIGO_PERMISSAO = "atributoCodigoPermissaoOriginal";
	private static final String ATRIBUTO_INCLUDE = "atributoInclude";
	
	protected static final int FORWARD = 1;
	protected static final int INCLUDE = 2;
	
	protected final String TIPO_PDF = "application/pdf";
	protected final String TIPO_HTML = "text/html;charset=ISO-8859-1";
	
	protected static final int REQUISICAO_VAZIA = 1;
	private static final int REQUISICAO_REDIRECIONA_SERVLET_FILHA = 997;
	private static final int REQUISICAO_REDIRECIONA_VIEW_ERRO = 998;
	private static final int REQUISICAO_VOLTAR_MENU = 999;

	
	protected AbstractServletITCD()
	{
	}
	
	private int redirecionaControleInterno(HttpServletRequest request)
	{
		if(Validador.isStringValida(request.getParameter(FormITC.PARAMETRO_REDIRECIONA_VIEW_ERRO)))
		{
			return REQUISICAO_REDIRECIONA_VIEW_ERRO;
		}
		if(Validador.isStringValida(request.getParameter(FormITC.BOTAO_RETORNO_MENU)))
		{
			return REQUISICAO_VOLTAR_MENU;
		}
		return REQUISICAO_REDIRECIONA_SERVLET_FILHA;
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			imprimirNomeSistemaFuncionalidade(request);
			setTitulo(request);
			setCodigoPermissao(request);
			bloqueiaRequisicaoDoGET(request,  response);
			obterControlesMenu(request);
			switch(redirecionaControleInterno(request))
			{
			   case REQUISICAO_REDIRECIONA_VIEW_ERRO:
				{
					redirecionarViewErro(request, response);
					break;
				}	
				case REQUISICAO_VOLTAR_MENU:
				{
					redirecionarMenu(request, response);
					break;
				}
				default:
				{
				   processRequest(request, response);
					break;
				}
			}			
		}
		catch (Throwable throwable)
		{
			try
			{
				if(throwable instanceof AjaxException)
				{
					throwable.printStackTrace();
				   request.setAttribute("exception", (AjaxException) throwable);
					response.setStatus(500);
				   processFlow(VIEW_VERIFICA_ERRO_AJAX, request, response, FORWARD);
				}
				else if(throwable instanceof AjaxError)
				{
				   throwable.printStackTrace();
					getBuffer(request).setAttribute("exception", (AjaxError) throwable, request);
				   processFlow(VIEW_ERRO_AJAX, request, response, FORWARD);					
				}
				else
				{
				   EntidadeVo entidadeVo = new EntidadeVo(getTitulo(request), new MsgException(throwable).getMessage());
				   request.setAttribute("vo", entidadeVo);
				   processFlow(VIEW_ERRO, request, response, FORWARD);					
				}
			}
			catch (TituloNaoInformadoException e)
			{
				try
				{
					e.printStackTrace();
					EntidadeVo entidadeVo = new EntidadeVo(getTituloSistema(request), new MsgException(e).getMessage());
					request.setAttribute("vo", entidadeVo);
					processFlow(VIEW_ERRO, request, response, FORWARD);
				}
				catch (TituloNaoInformadoException titulo)
				{
					try
					{
						e.printStackTrace();
						EntidadeVo entidadeVo = new EntidadeVo(TITULO_SISTEMA_SEM_TITULO, SISTEMA_SEM_TITULO);
						request.setAttribute("vo", entidadeVo);
						processFlow(VIEW_ERRO, request, response, FORWARD);
					}
					catch (PaginaNaoEncontradaException pagina)
					{
						pagina.printStackTrace();
						throw new Error(pagina);
					}
					catch (TipoRedirecionamentoInvalidoException tipo)
					{
						tipo.printStackTrace();
						throw new Error(tipo);
					}
				}
				catch (PaginaNaoEncontradaException pagina)
				{
					pagina.printStackTrace();
					throw new Error(pagina);
				}
				catch (TipoRedirecionamentoInvalidoException tipo)
				{
					tipo.printStackTrace();
					throw new Error(tipo);
				}
			}
			catch (TipoRedirecionamentoInvalidoException e)
			{
				try
				{
					EntidadeVo entidadeVo = new EntidadeVo(getTitulo(request), new MsgException(throwable).getMessage());
					request.setAttribute("vo", entidadeVo);
					processFlow(VIEW_ERRO, request, response, FORWARD);
				}
				catch (Throwable t)
				{
					t.printStackTrace();
					throw new Error(t);
				}
			}
			catch (Throwable e)
			{
				e.printStackTrace();
				throw new Error(e);
			}
		}
	}
	
	private void obterControlesMenu(HttpServletRequest request)
	{
		String url = (String) getBuffer(request).getAttribute("urlMenu");
		if(!Validador.isStringValida(url))
		{
		   /* Pegar dados do Menu */
		   String codgMenu = request.getParameter("codgMenu");
		   String barraMenu = request.getParameter("barraMenu");
		   String nomeMenu = request.getParameter("nomeMenu");
		   String scheme = request.getScheme();
		   String remoteHost = request.getRemoteHost();
		   int serverPort = request.getServerPort();
		   String serverName = request.getServerName().toLowerCase();
		   if (request.getHeader("X-FORWARDED-HOST") != null)
		   {
		      remoteHost = request.getHeader("X-FORWARDED-HOST");
		   }        
		   else
		   {
		      remoteHost = request.getHeader("HOST");
		   }
		   String endereco = scheme + "://" + remoteHost;  
		   if(Validador.isStringValida(codgMenu) && Validador.isStringValida(barraMenu) && Validador.isStringValida(nomeMenu))
		   {
		      url = endereco + "/acessoweb/menu/jsp/ApresentaMenu.jsp?codgMenu=" + codgMenu + "&barraMenu=" + barraMenu + "&nomeMenu=" + nomeMenu;
//		      System.out.println(url);
		   }
		   else if(!isServidorLocal(serverName))
		   {
		      url = endereco + "/acessoweb/menu/jsp/ApresentaMenu.jsp?codgMenu=2";
//		      System.out.println(url);
		   }
		   else
		   {
		      //endereco = scheme + "://" + serverName + ":" + serverPort;
		      url = endereco + "/pgf/";
//		      System.out.println(url);
		   }			
		   getBuffer(request).setAttribute("urlMenu", url, request);      			
		}
	}
	
	private boolean isServidorLocal(String nomeServidor)
	{
		Set<String> listaServers = new TreeSet<String>();
		listaServers.add("172.16.20.65");
		listaServers.add("127.0.0.1");
		boolean encontrou = false;
		if(!listaServers.isEmpty())
		{
			for(String atual : listaServers)
			{
				if(atual.equals(nomeServidor))
				{
					encontrou = true;
					break;
				}
			}
		}
		return encontrou;
	}
	
	private void redirecionarMenu(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, IOException, FileUploadAbacoException
	{
		String url = (String) getBuffer(request).getAttribute("urlMenu");;
		removeBuffer(request, true);
		response.sendRedirect(url);	
	}
	
	private void redirecionarViewErro(HttpServletRequest request, HttpServletResponse response) throws TituloNaoInformadoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
	   String mensagem = ((AjaxError) getBuffer(request).getAttribute("exception")).getMessage();
	   EntidadeVo entidadeVo = new EntidadeVo(getTitulo(request), mensagem);
	   request.setAttribute("vo", entidadeVo);            
	   processFlow(VIEW_ERRO, request, response, FORWARD);		
	}
	
	private void imprimirNomeSistemaFuncionalidade(HttpServletRequest request)
	{
		System.out.println("");
		System.out.println("Nome sistema: " + request.getContextPath()+ "   Funcionalidade: " + request.getServletPath()+"   Método de requisição: "+request.getMethod());
		//System.out.println("Versão compilação JDK: " + System.getProperty("java.version"));     
		imprimeNavegador(request);
		System.out.println("");
	}
	
	protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable;
	
	protected abstract int redirecionar(HttpServletRequest request);

	/**
	 * Método utilizado para redirecionar o fluxo da aplicação para uma JSP ou para uma Servlet.
	 * Este método não redirecionará para nenhuma url caso o response esteja comitado.
	 * @param urlJspServlet
	 * @param request
	 * @param response
	 * @param tipoRedirecionamento
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Karen Barbato da Silva
	 * @implemented by Wanderlúcio Alves de Oliveira
	 */
	protected final void processFlow(String urlJspServlet, HttpServletRequest request, HttpServletResponse response, int tipoRedirecionamento) throws PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		RequestDispatcher rd = null;
		if(Validador.isStringValida(urlJspServlet))
		{
			try
			{
				if(VIEW_SUCESSO.equals(urlJspServlet) || VIEW_ERRO.equals(urlJspServlet))
				{
					removeBuffer(request);
				}
				rd = request.getRequestDispatcher(urlJspServlet);
				if (!response.isCommitted())
				{
					switch (tipoRedirecionamento)
					{
						case FORWARD:
						{
							rd.forward(request, response);
							break;
						}
						case INCLUDE:
						{
							request.setAttribute(ATRIBUTO_INCLUDE, ATRIBUTO_INCLUDE);
							rd.include(request, response);
							request.removeAttribute(ATRIBUTO_INCLUDE);
							break;
						}
						default:
						{
							throw new TipoRedirecionamentoInvalidoException();
						}
					}
				}
			}
			catch(NullPointerException n)
			{
				n.printStackTrace();
				throw new PaginaNaoEncontradaException(urlJspServlet);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				throw new PaginaNaoEncontradaException(urlJspServlet);
			}
			catch (ServletException e)
			{
				e.printStackTrace();
				throw new PaginaNaoEncontradaException(urlJspServlet);
			}
		}
		else
		{
			throw new PaginaNaoEncontradaException(PAGINA_NAO_INFORMADA);
		}
	}

	protected final void processFlowAjax(String urlJspServlet, HttpServletRequest request, HttpServletResponse response) throws AjaxError
	{
		RequestDispatcher rd = null;
		if(Validador.isStringValida(urlJspServlet))
		{
			try
			{
				rd = request.getRequestDispatcher(urlJspServlet);
				if (!response.isCommitted())
				{
					rd.forward(request, response);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				removeBuffer(request);
				throw new AjaxError(e);
			}
		}
		else
		{
			throw new AjaxError(PAGINA_NAO_INFORMADA);
		}
	}	

	/**
	 * Método utilizado para remover os atributos do buffer de acordo com o id da sessão do usuário.
	 * @param request
	 * @implemented by Karen Barbato da Silva
	 * @implemented by Wanderlúcio Alves de Oliveira
	 */
	protected synchronized void removeBuffer(HttpServletRequest request)
	{
		removeBuffer(request, false);
	}

	private synchronized void removeBuffer(HttpServletRequest request, boolean limpaMenu)
	{
		AbacoBuffer buffer = getBuffer(request);
		String url = (String) buffer.getAttribute("urlMenu");
	   buffer.destroy(request.getSession().getId());
	   if(!limpaMenu)
	   {
	      getBuffer(request).setAttribute("urlMenu",url,request);          
	   }		
	}	

	/**
	 * Método utilizado para retornar o buffer de acordo com o id da sessão do usuário.
	 * @param request
	 * @return AbacoBuffer
	 * @implemented by Karen Barbato da Silva
	 * @implemented by Wanderlúcio Alves de Oliveira
	 */
	protected synchronized AbacoBuffer getBuffer(HttpServletRequest request)
	{
		return AbacoBuffer.getSessionInstance(request.getSession().getId());
	}

	/**
	 * Método utilizado para retornar o código do usuário logado.
	 * @param request
	 * @return long - Código do usuário logado
	 * @implemented by Karen Barbato da Silva
	 * @implemented by Wanderlúcio Alves de Oliveira
	 */
	protected synchronized long getCodigoUsuarioLogado(HttpServletRequest request)
	{
		Usuario usuario = (Usuario) request.getAttribute("usuario");
		if (usuario != null && Validador.isNumericoValido(usuario.getCodigo()))
		{
			return usuario.getCodigo();
		}
		else
		{
			return 0;
		}
	}
	
	/**
	 * Método utilizado para retornar o número da matrícula do usuário logado.
	 * Se o usuário logado nao for um servidor SEFAZ, retorna uma String VAZIA, ou seja, "".
	 * @param request
	 * @return String - Número da matrícula do usuario logado
	 * @implemented by Roniselton B. R. Silva
	 * @implemented by Wanderlúcio Alves de Oliveira
	 */
	protected synchronized String getNumeroMatriculaUsuarioLogado(HttpServletRequest request)
	{
		Usuario usuario = (Usuario) request.getAttribute("usuario");
		if (usuario != null && Validador.isStringValida(usuario.getIdentificacao()) && usuario.getTipo() == Usuario.FUNCIONARIOSEFAZ)
		{
			return usuario.getIdentificacao();
		}
		else
		{
			return "";
		}
	}

	/**
	 * Método utilizado para retornar o número do CRC do usuário logado.
	 * Se o usuário logado não for um contador, retorna uma String VAZIA, ou seja, "".
	 * @param request
	 * @return String - Número do CRC do usuário logado.
	 * @implemented by Wanderlúcio Alves de Oliveira
	 */
	protected synchronized String getNumeroCRCUsuarioLogado(HttpServletRequest request)
	{
		Usuario usuario = (Usuario) request.getAttribute("usuario");
		if (usuario != null && Validador.isStringValida(usuario.getIdentificacao()) && usuario.getTipo() == Usuario.CONTABILISTA)
		{
			return usuario.getIdentificacao();
		}
		else
		{
			return "";
		}
	}

	/**
	 * Método utilizado para retornar o nome do usuário logado.
	 * @param request
	 * @return String - Nome do usuário que está logado.
	 * @implemented by Wanderlúcio Alves de Oliveira
	 */
	protected synchronized String getNomeUsuarioLogado(HttpServletRequest request)
	{
		Usuario usuario = (Usuario) request.getAttribute("usuario");
		if (usuario != null && Validador.isStringValida(usuario.getNome()))
		{
			return usuario.getNome();
		}
		else
		{
			return "";
		}
	}

	/**
	 * Método utilizado para retornar o título da funcionalidade.
	 * @param request
	 * @return String - título da funcionalidade
	 * @throws TituloNaoInformadoException
	 * @implemented by Karen Barbato da Silva
	 * @implemented by Wanderlúcio Alves de Oliveira
	 */
	private final String getTitulo(HttpServletRequest request) throws TituloNaoInformadoException
	{
		if(request.getAttribute(ATRIBUTO_TITULO_FUNCIONALIDADE)!=null)
		{
			String titulo = (String) request.getAttribute(ATRIBUTO_TITULO_FUNCIONALIDADE);
			if(Validador.isStringValida(titulo))
			{
				return titulo;
			}
			else
			{
				throw new TituloNaoInformadoException();
			}
		}
		else
		{
			throw new TituloNaoInformadoException();
		}
	}

	/**
	 * Método utilizado buscar o título da funcionalidade definido no web.xml do sistema e adicionar ao
	 * request.
	 * @param request
	 * @throws TituloNaoInformadoException
	 * @implemented by Karen Barbato da Silva
	 * @implemented by Wanderlúcio Alves de Oliveira
	 */
	private final void setTitulo(HttpServletRequest request) throws TituloNaoInformadoException
	{
		String titulo = getInitParameter(ATRIBUTO_TITULO_FUNCIONALIDADE);
		if(Validador.isStringValida(titulo))
		{
			if (!isInclude(request))
			{
				request.setAttribute(ATRIBUTO_TITULO_FUNCIONALIDADE, titulo);
			}
		}
		else
		{
			 throw new TituloNaoInformadoException();
		}
	}
	
	/**
	 * Método utilizado buscar o código de permissao da funcionalidade definido no web.xml do sistema
	 * e adicionar ao request. Se a requisição a servlet veio via INCLUDE, não será modificado o código
	 * de permissão.
	 * @param request
	 * @throws CodigoPermissaoNaoInformadoException
	 * @implemented by Karen Barbato da Silva
	 * @implemented by Wanderlúcio Alves de Oliveira
	 */
	private final void setCodigoPermissao(HttpServletRequest request) throws CodigoPermissaoNaoInformadoException
	{
		String codigoPermissao = getInitParameter(ATRIBUTO_CODIGO_PERMISSAO);
		if(Validador.isStringValida(codigoPermissao))
		{
			if (!isInclude(request))
			{
				request.setAttribute(ATRIBUTO_CODIGO_PERMISSAO, codigoPermissao);
			}
		}
		else
		{
			 throw new CodigoPermissaoNaoInformadoException();
		}
	}
	
	private boolean isInclude(HttpServletRequest request)
	{
		if (Validador.isStringValida((String) request.getAttribute(ATRIBUTO_INCLUDE)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Método utilizado para retornar o título do sistema definido no web.xml do sistema
	 * @param request
	 * @return String - título do sistema
	 * @throws TituloNaoInformadoException
	 * @implemented by Karen Barbato da Silva
	 */
	private final String getTituloSistema(HttpServletRequest request) throws TituloNaoInformadoException
	{
		String tituloSistema = null;
		tituloSistema = request.getSession().getServletContext().getInitParameter(ATRIBUTO_TITULO_SISTEMA);
		if(Validador.isStringValida(tituloSistema))
		{
			return tituloSistema;
		}
		else
		{
			throw new TituloNaoInformadoException();
		}
	}

	private void bloqueiaRequisicaoDoGET(HttpServletRequest request, HttpServletResponse response) throws ProibidoRequisicaoDoGETException
	{
		if (request.getMethod().equals("GET"))
		{
			String referer = request.getHeader("referer");
			String uri = request.getRequestURI();
			if (Validador.isStringValida(referer) || Validador.isStringValida(uri))
			{
				if( referer != null)
					referer = referer.toUpperCase();
				if(uri != null)
					uri = uri.toUpperCase();
				if ((referer != null && referer.indexOf("MENU") != -1) || (referer != null && referer.indexOf("ACESSOWEB") != -1) || (uri != null && uri.indexOf("POPUP") != -1 ) || 
							  (uri != null && uri.indexOf("IMAGEM") != -1) || (uri != null && uri.indexOf("APPLET") != -1) || (uri != null && uri.indexOf("PORTALPAGINA") != -1))
				{
					System.out.println("REQUISIÇÃO GET PERMITIDA.");
				}
				else
				{
					System.out.println("REQUISIÇÃO GET BLOQUEADA.");
					throw new ProibidoRequisicaoDoGETException();
				}
			}
			else
			{
				System.out.println("REQUISIÇÃO GET BLOQUEADA.");
				throw new ProibidoRequisicaoDoGETException();
			}
		}
	}

	private void imprimeNavegador(HttpServletRequest request)
	{
		String navegador = request.getHeader("user-agent");
		if (navegador != null)
		{
			navegador = navegador.toUpperCase();
			if (navegador.indexOf("FIREFOX") != -1)
			{
				System.out.println("Navegador: Mozilla Firefox");
			}
			else if (navegador.indexOf("MSIE") != -1)
			{
				System.out.println("Navegador: Internet Explorer");
			}
			else if( navegador.indexOf("NETSCAPE")!=-1)
			{
				System.out.println("Navegador: Netscape");
			}
			else
			{
				System.out.println("Não foi possível identificar o navegador.");
			}
		}
		else
		{
			System.out.println("Não foi possível identificar o navegador.");
		}
	}
	
	protected boolean isUsuarioLogado(HttpServletRequest request)
	{
		Usuario usuario = (Usuario) request.getAttribute("usuario");
		if (usuario != null && Validador.isNumericoValido(usuario.getCodigo()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private synchronized Usuario obterInformacoesUsuario(HttpServletRequest request)
	{
		Usuario usuario = new Usuario();
		usuario.setIdentificacao(getNumeroMatriculaUsuarioLogado(request));
		usuario.setNome(getNomeUsuarioLogado(request));
		usuario.setCodigo((int) getCodigoUsuarioLogado(request));
		return usuario;
	}

	/** Novo método utilizado para registrar log.
	 * @param request
	 * @param entidadeVo
	 * @implemented by Roniselton B R Silva
	 */
	protected synchronized void obterInformacoesLogSefaz(HttpServletRequest request, final EntidadeVo entidadeVo)
	{
		LogSefazVo logSefazVo = new LogSefazVo();
		InfoClienteBean icb = (InfoClienteBean) request.getAttribute("infoClienteBean");
		logSefazVo.setInfoClienteBean(icb);
		Usuario usuario = (Usuario) request.getAttribute("usuario");
		logSefazVo.setUsuario(usuario);
		String codgModulo = (String)request.getAttribute("codgModulo");
		logSefazVo.setCodgModulo( codgModulo );
		//atribui ao VO e a todos os elementos da collection dele, caso exista.
		entidadeVo.setLogSefazVo(logSefazVo);
		if (Validador.isCollectionValida(entidadeVo.getCollVO()))
		{
			Iterator it = entidadeVo.getCollVO().iterator();
			while(it.hasNext())
			{
				EntidadeVo entidadeVoAtual = (EntidadeVo) it.next();
				entidadeVoAtual.setLogSefazVo(logSefazVo);
			}
		}
		
	}

	/**
	 * Método responsável por finalizar a conexão de um Be passado como parâmetro.
	 * @param abstractBe - Qualquer objeto que extenda de AbstractBe
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void finalizaConexao(AbstractBe abstractBe)
	{
		if(abstractBe != null)
		{
			abstractBe.close();       
			abstractBe = null;
		}     
	}


	public void processFlowServletPai(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);
		
	}  
	
	public void processFlowProximaServletForward(HttpServletRequest request, HttpServletResponse response, String codigoServlet) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(codigoServlet, request), request, response, FORWARD);
	}

	public void processFlowProximaServletInclude(HttpServletRequest request, HttpServletResponse response, String codigoServlet) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(codigoServlet, request), request, response, INCLUDE);
	}  
	
	protected EntidadeFacade getObjetoRequest(HttpServletRequest request, String nomeObjetoRequest)
	{
		return getObjetoRequest(request, nomeObjetoRequest, true);
	}
	
	protected EntidadeFacade getObjetoRequest(HttpServletRequest request, String nomeObjetoRequest, boolean buscaBuffer)
	{
		EntidadeFacade vo = null;
		if(buscaBuffer)
		{
			vo = (EntidadeFacade) getBuffer(request).getAttribute(nomeObjetoRequest);   
		}
		if(!Validador.isObjetoValido(vo))
		{
			vo = (EntidadeFacade) request.getAttribute(nomeObjetoRequest);
		}
		return vo;     
	}
}
