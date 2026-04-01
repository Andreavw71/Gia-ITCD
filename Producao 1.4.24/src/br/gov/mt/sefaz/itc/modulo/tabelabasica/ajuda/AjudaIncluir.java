/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AjudaIncluir.java
 * Revisão: 
 * Data revisão: 
 * Implementação: Wendell Pereira de Farias
 * Adaptações: Janeiro de 2008 / Wendell Farias 
 * Data novas implementação: 04/01/2008
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.ajuda;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.campoajuda.CampoAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.funcionalidade.FuncionalidadeBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.funcionalidade.FuncionalidadeVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.moduloajuda.ModuloAjudaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.moduloajuda.ModuloAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda.TelaCampoAjudaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda.TelaCampoAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telafuncionalidade.TelaFuncionalidadeBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telafuncionalidade.TelaFuncionalidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por incluir informações do Ajuda no banco de dados.
 * @author Elizabeth Barbosa Moreira/ Wendell Pereira de Farias
 * @version $Revision: 1.1.1.1 $
 */
public class AjudaIncluir extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_LISTAR_SUBMODULO_AJUDA = 2;
	private static final int REQUISICAO_LISTAR_FUNCIONALIDADE_AJUDA = 3;
	private static final int REQUISICAO_LISTAR_TELA_FUNCIONALIDADE_AJUDA = 4;
	private static final int REQUISICAO_LISTAR_TELA_CAMPO_AJUDA = 5;
	private static final int REQUISICAO_EXCLUIR_DESCRICAO_CAMPO_AJUDA = 7;
	private static final int REQUISICAO_ADICIONAR_DESCRICAO_CAMPO_AJUDA = 8;
	private static final int REQUISICAO_INCLUIR_DESCRICAO_CAMPO_AJUDA = 9;
	private static final int REQUISICAO_SOLICITAR_PESQUISAR_DESCRICAO_AJUDA_CAMPOEXCLUIR_DESCRICAO_CAMPO_AJUDA = 10;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.     
	 * @param request
	 * @param response
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, 
			  LogException, PersistenciaException, AnotacaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarListarModuloAjuda(request, response);
					break;
				}
			case REQUISICAO_LISTAR_SUBMODULO_AJUDA:
				{
					listarSubModuloAjuda(request, response);
					break;
				}
			case REQUISICAO_LISTAR_FUNCIONALIDADE_AJUDA:
				{
					listarFuncionalidadeAjuda(request, response);
					break;
				}
			case REQUISICAO_LISTAR_TELA_FUNCIONALIDADE_AJUDA:
				{
					listarTelaFuncionalidadeAjuda(request, response);
					break;
				}
			case REQUISICAO_LISTAR_TELA_CAMPO_AJUDA:
				{
					listarTelaCampoAjuda(request, response);
					break;
				}
			case REQUISICAO_EXCLUIR_DESCRICAO_CAMPO_AJUDA:
				{
					excluirTelaCampoAjuda(request, response);
					break;
				}
			case REQUISICAO_ADICIONAR_DESCRICAO_CAMPO_AJUDA:
				{
					adicionarTelaCampoAjuda(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_DESCRICAO_CAMPO_AJUDA:
				{
					incluirTelaCampoAjuda(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_PESQUISAR_DESCRICAO_AJUDA_CAMPOEXCLUIR_DESCRICAO_CAMPO_AJUDA:
				{
					listarDescricaoCampoAjuda(request, response);
					break;
				}
		}
	}

	/**
	 *  Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_MODULO)))
		{
			return REQUISICAO_LISTAR_SUBMODULO_AJUDA;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_SUB_MODULO)))
		{
			return REQUISICAO_LISTAR_FUNCIONALIDADE_AJUDA;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_FUNCIONALIDADE)))
		{
			return REQUISICAO_LISTAR_TELA_FUNCIONALIDADE_AJUDA;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_TELA)))
		{
			return REQUISICAO_LISTAR_TELA_CAMPO_AJUDA;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_ADICIONAR_DESCRICAO_CAMPO_AJUDA;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR_CLONE)))
		{
			return REQUISICAO_INCLUIR_DESCRICAO_CAMPO_AJUDA;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_AJUDA_EXCLUIR)))
		{
			return REQUISICAO_EXCLUIR_DESCRICAO_CAMPO_AJUDA;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_DESCRICAO_AJUDA_CAMPO)))
		{
			return REQUISICAO_SOLICITAR_PESQUISAR_DESCRICAO_AJUDA_CAMPOEXCLUIR_DESCRICAO_CAMPO_AJUDA;
		}
		return REQUISICAO_VAZIA;
	}
	
	private TelaFuncionalidadeVo controladorInterativoBuffer(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		TelaFuncionalidadeVo telaFuncionalidade = (TelaFuncionalidadeVo) getBuffer(request).getAttribute(TELA_FUNCIONALIDADE_VO);
		Validador.validaObjeto(telaFuncionalidade);
		return telaFuncionalidade;
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Pesquisar e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @implemented by Anderson Boehler Iglesias Araujo/Wendell Pereira de Farias
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private TelaFuncionalidadeVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		//captura o objetoAtual da requisição
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoBuffer(request);
		//verifica se foi selecionado um modulo do ajuda
		if (request.getParameter(CAMPO_SELECT_MODULO_AJUDA) != null)
		{
			if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_MODULO_AJUDA))) //verifica se o modulo selecionado é valido
			{
				long codigoModulo = StringUtil.toLong(request.getParameter(CAMPO_SELECT_MODULO_AJUDA));
				telaFuncionalidadeVo.getFuncionalidadeVo().getModuloAjudaVo().setCodigo(codigoModulo);
			}
			else //se não for limpa a coleção de objetos atuais
			{
				telaFuncionalidadeVo.getCollVO().clear();
			}
		}
		//verifica se foi selecionado o submodulo do ajuda
		if (request.getParameter(CAMPO_SELECT_SUBMODULO_AJUDA) != null)
		{
			if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_SUBMODULO_AJUDA))) //verifica se o submodulo selecionado é valido
			{
				long codigoSubModulo = StringUtil.toLong(request.getParameter(CAMPO_SELECT_SUBMODULO_AJUDA));
				telaFuncionalidadeVo.getFuncionalidadeVo().getModuloAjudaVo().getSubModuloAjuda().setCodigo(codigoSubModulo);
			}
			else //se não for limpa a coleção de objetos atuais
			{
				telaFuncionalidadeVo.getFuncionalidadeVo().getModuloAjudaVo().setSubModuloAjuda(new ModuloAjudaVo());
				telaFuncionalidadeVo.getFuncionalidadeVo().getCollVO().clear();
			}
		}
		//verifica se foi selecionado a funcionalidade do ajuda
		//A seguinte instrução TelaFuncionalidadeVo.STRING_VAZIA.length() foi utilizada para atribuir o valor zero quando necessário.
		if (request.getParameter(CAMPO_SELECT_FUNCIONALIDADE_AJUDA) != null)
		{
			if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_FUNCIONALIDADE_AJUDA))) //verifica se a funcionalidade selecionado é valido
			{
				long codigoFuncionalidade = StringUtil.toLong(request.getParameter(CAMPO_SELECT_FUNCIONALIDADE_AJUDA));
				telaFuncionalidadeVo.setCodigo(codigoFuncionalidade);
				telaFuncionalidadeVo.getFuncionalidadeVo().setCodigo(codigoFuncionalidade);
			}
			else if (request.getParameter(CAMPO_SELECT_FUNCIONALIDADE_AJUDA).equals(TelaFuncionalidadeVo.STRING_VAZIA))
			{
				telaFuncionalidadeVo.getFuncionalidadeVo().getCollVO().clear();
				telaFuncionalidadeVo.getFuncionalidadeVo().setDescricaoFuncionalidade(TelaFuncionalidadeVo.STRING_VAZIA);
				telaFuncionalidadeVo.getFuncionalidadeVo().setCodigo(TelaFuncionalidadeVo.STRING_VAZIA.length());
				telaFuncionalidadeVo.getTelaAjudaVo().getCollVO().clear();
				telaFuncionalidadeVo.getTelaAjudaVo().setCodigo(TelaFuncionalidadeVo.STRING_VAZIA.length());
				telaFuncionalidadeVo.getCollVO().clear();
				telaFuncionalidadeVo.setDescricaoTelaFuncionalidade(TelaFuncionalidadeVo.STRING_VAZIA);
				telaFuncionalidadeVo.setInformacaoTituloTelaFuncionalidade(TelaFuncionalidadeVo.STRING_VAZIA);
			}
			if (request.getParameter(CAMPO_CHECK_HABILITAR_AJUDA) != null)
			{
				if (request.getParameter(CAMPO_CHECK_HABILITAR_AJUDA).equals("on"))
				{
					telaFuncionalidadeVo.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
				}
			}
			else
			{
				telaFuncionalidadeVo.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.INATIVO));
			}
		}
		if (request.getParameter(CAMPO_SELECT_TELA_AJUDA) != null)
		{
			if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TELA_AJUDA)))
			{
				long codigoTelaAjuda = StringUtil.toLong(request.getParameter(CAMPO_SELECT_TELA_AJUDA));
				if (codigoTelaAjuda != TelaFuncionalidadeVo.STRING_VAZIA.length())
				{
					telaFuncionalidadeVo.getTelaAjudaVo().setCodigo(codigoTelaAjuda);
				}
				if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_AJUDA))) //verifica se a funcionalidade selecionado é valido
				{
					telaFuncionalidadeVo.setDescricaoTelaFuncionalidade(request.getParameter(CAMPO_DESCRICAO_AJUDA));
				}
				if (Validador.isStringValida(request.getParameter(CAMPO_TITULO_TELA_AJUDA))) //verifica se a funcionalidade selecionado é valido
				{
					telaFuncionalidadeVo.setInformacaoTituloTelaFuncionalidade(request.getParameter(CAMPO_TITULO_TELA_AJUDA));
				}
			}
			else
			{
				telaFuncionalidadeVo.getTelaAjudaVo().setCodigo(TelaFuncionalidadeVo.STRING_VAZIA.length());
				telaFuncionalidadeVo.getTelaAjudaVo().getCollVO().clear();
				telaFuncionalidadeVo.getTelaAjudaVo().setDescricaoTelaAjuda(TelaFuncionalidadeVo.STRING_VAZIA);
			}
		}
		return telaFuncionalidadeVo;
	}

	/**
	 * Método responsável por solicitar a consulta de módulos da ajuda.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @implemented by Anderson Boehler Iglesias Araujo/Wendell Pereira de Farias
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarListarModuloAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException
	{
		removeBuffer(request);
		TelaFuncionalidadeVo telaFuncionalidadeVo = new TelaFuncionalidadeVo();
		ModuloAjudaVo moduloAjudaVo = new ModuloAjudaVo(new ModuloAjudaVo());
		ModuloAjudaBe moduloAjudaBe = null;
		try
		{
			moduloAjudaBe = new ModuloAjudaBe(); 
			moduloAjudaBe.listarModuloAjuda(moduloAjudaVo);
			telaFuncionalidadeVo.getFuncionalidadeVo().setModuloAjudaVo(moduloAjudaVo);
		   getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		   processFlow(VIEW_INCLUIR_AJUDA, request, response, FORWARD);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (moduloAjudaBe != null)
			{
				moduloAjudaBe.close();
				moduloAjudaBe = null;
			}
		}
	}

	/**
	 * Método responsável por solicitar a listagem de submódulos da ajuda.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void listarSubModuloAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException, ConexaoException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		//limpar os objetos.
		telaFuncionalidadeVo.getFuncionalidadeVo().getCollVO().clear();
		telaFuncionalidadeVo.getCollVO().clear();
		telaFuncionalidadeVo.setDescricaoTelaFuncionalidade(TelaFuncionalidadeVo.STRING_VAZIA);
		telaFuncionalidadeVo.setInformacaoTituloTelaFuncionalidade(TelaFuncionalidadeVo.STRING_VAZIA);
		telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO().clear();
		ModuloAjudaVo moduloAjudaVo = telaFuncionalidadeVo.getFuncionalidadeVo().getModuloAjudaVo();
		ModuloAjudaVo consultaSubModuloVo = new ModuloAjudaVo(moduloAjudaVo);
		ModuloAjudaBe moduloAjudaBe = null;
		try
		{
			moduloAjudaBe = new ModuloAjudaBe();
			consultaSubModuloVo = moduloAjudaBe.listarSubModuloAjuda(consultaSubModuloVo);
			telaFuncionalidadeVo.getFuncionalidadeVo().getModuloAjudaVo().setSubModuloAjuda(consultaSubModuloVo);
		   getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		   processFlow(VIEW_INCLUIR_AJUDA, request, response, FORWARD);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (moduloAjudaBe != null)
			{
				moduloAjudaBe.close();
				moduloAjudaBe = null;
			}
		}
	}

	/**
	 * Método responsável por solicitar as funcionalidades da ajuda.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta/Wendell Pereira de Farias
	 */
	private void listarFuncionalidadeAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, ConsultaException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		FuncionalidadeVo funcionalidadeVo = telaFuncionalidadeVo.getFuncionalidadeVo();
		//limpar os objetos.
		funcionalidadeVo.setCodigo(0);
		funcionalidadeVo.getCollVO().clear();
		telaFuncionalidadeVo.getCollVO().clear();
		telaFuncionalidadeVo.getTelaAjudaVo().setCodigo(0);
		telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().setCodigo(0);
		telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO().clear();
		telaFuncionalidadeVo.setInformacaoTituloTelaFuncionalidade(TelaFuncionalidadeVo.STRING_VAZIA);
		telaFuncionalidadeVo.setDescricaoTelaFuncionalidade(TelaFuncionalidadeVo.STRING_VAZIA);
		funcionalidadeVo.setParametroConsulta(funcionalidadeVo);
		funcionalidadeVo.setConsultaCompleta(true);
		FuncionalidadeBe funcionalidadeBe = null;
		try
		{
			funcionalidadeBe = new FuncionalidadeBe();
			funcionalidadeBe.listarFuncionalidade(funcionalidadeVo);
			telaFuncionalidadeVo.setFuncionalidadeVo(funcionalidadeVo);
		   getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		   processFlow(VIEW_INCLUIR_AJUDA, request, response, FORWARD);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (funcionalidadeBe != null)
			{
				funcionalidadeBe.close();
				funcionalidadeBe = null;
			}
		}
	}

	/**
	 * Método responsável por solicitar a listagem de telas da ajuda.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta/Wendell Pereira de Farias
	 */
	private void listarTelaFuncionalidadeAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		telaFuncionalidadeVo.setParametroConsulta(telaFuncionalidadeVo);
		telaFuncionalidadeVo.getTelaAjudaVo().setCodigo(0);
		telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().setCodigo(0);
		telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO().clear();
		telaFuncionalidadeVo.setDescricaoTelaFuncionalidade(TelaFuncionalidadeVo.STRING_VAZIA);
		telaFuncionalidadeVo.setInformacaoTituloTelaFuncionalidade(TelaFuncionalidadeVo.STRING_VAZIA);
		TelaFuncionalidadeBe telaFuncionalidadeBe = null;
		try
		{
			telaFuncionalidadeBe = new TelaFuncionalidadeBe();
			telaFuncionalidadeVo.setCollVO(null);
			telaFuncionalidadeBe.listarTelaFuncionalidade(telaFuncionalidadeVo);
			TelaFuncionalidadeBe.atualizarTelaFuncionalidadeVo(telaFuncionalidadeVo);
		   getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		   processFlow(VIEW_INCLUIR_AJUDA, request, response, FORWARD);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (telaFuncionalidadeBe != null)
			{
				telaFuncionalidadeBe.close();
				telaFuncionalidadeBe = null;
			}
		}
	}

	/**
	 * Método responsável por consultar os campos de uma determinada tela de ajuda.
	 * O método atualiza o VO da telaFuncionalide para exibir as informações título, descrição e campos da Tela/Funcionalidade na JSP.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta/Wendell Pereira de Farias
	 */
	private void listarTelaCampoAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		TelaFuncionalidadeBe.atualizarTelaFuncionalidadeVo(telaFuncionalidadeVo);
		TelaCampoAjudaVo telaCampoAjudaVo = new TelaCampoAjudaVo();
		telaCampoAjudaVo.setTelaAjudaVo(telaFuncionalidadeVo.getTelaAjudaVo());
		telaCampoAjudaVo.setCampoAjudaVo(new CampoAjudaVo());
		telaCampoAjudaVo.setParametroConsulta(telaCampoAjudaVo);
		telaCampoAjudaVo.setConsultaCompleta(true);
		TelaCampoAjudaBe telaCampoAjudaBe = null;
		try
		{
			telaCampoAjudaBe = new TelaCampoAjudaBe();
			telaCampoAjudaBe.listarTelaCampoAjuda(telaCampoAjudaVo);
			Validador.validaObjeto(telaCampoAjudaVo);
			telaFuncionalidadeVo.getTelaAjudaVo().setTelaCampoAjudaVo(telaCampoAjudaVo);
		   getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		   processFlow(VIEW_INCLUIR_AJUDA, request, response, FORWARD);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (telaCampoAjudaBe != null)
			{
				telaCampoAjudaBe.close();
				telaCampoAjudaBe = null;
			}
		}
	}

	/**
	 * Método responsável por retorna uma lista com a descrição dos campos - Modulo Ajuda.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Wendell Pereira de Farias
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void listarDescricaoCampoAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		int codigo = StringUtil.toInt(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_DESCRICAO_AJUDA_CAMPO));
		if (Validador.isNumericoValido(codigo))
		{
			TelaCampoAjudaVo telaCampoAjudaVoAtual = new TelaCampoAjudaVo(codigo);
			int indice = ((List) telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO()).indexOf(telaCampoAjudaVoAtual);
			telaCampoAjudaVoAtual = (TelaCampoAjudaVo) ((List) telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO()).get(indice);
			((List) telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO()).remove(indice);
			((List) telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO()).add(indice, telaCampoAjudaVoAtual);
			telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().setCodigo(telaCampoAjudaVoAtual.getCodigo());
			telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().setDescricaoAjudaCampo(telaCampoAjudaVoAtual.getDescricaoAjudaCampo());
			telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().setCampoAjudaVo(telaCampoAjudaVoAtual.getCampoAjudaVo());
			telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().setStatusTelaCampo(telaCampoAjudaVoAtual.getStatusTelaCampo());
		}		
		getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		processFlow(VIEW_INCLUIR_AJUDA, request, response, FORWARD);
	}

	/**
	 * Método responsável por selecionar um item novo que será confirmado a sua persistência coletiva ou unitária.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Wendell Pereira de Farias
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void adicionarTelaCampoAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		int codigo = StringUtil.toInt(request.getParameter(BOTAO_CONFIRMAR));
		if (Validador.isNumericoValido(codigo))
		{
			TelaCampoAjudaVo telaCampoAjudaVoAtual = new TelaCampoAjudaVo(codigo);
			TelaCampoAjudaVo telaCampoAjudaVoAux = new TelaCampoAjudaVo();
			telaCampoAjudaVoAux.setCodigo(telaCampoAjudaVoAtual.getCodigo());
			telaCampoAjudaVoAux.setDescricaoAjudaCampo(TelaFuncionalidadeVo.STRING_VAZIA);
			telaCampoAjudaVoAux.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.INATIVO));
			int status_confirmado = -DomnStatusRegistro.ATIVO; //controla a funcionalidade confirmar
			int indice = ((List) telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO()).indexOf(telaCampoAjudaVoAtual);
			telaCampoAjudaVoAtual = (TelaCampoAjudaVo) ((List) telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO()).get(indice);
			telaCampoAjudaVoAtual.setDescricaoAjudaCampo(request.getParameter(CAMPO_DESCRICAO_AJUDA_ROTULO_AJUDA));
			telaCampoAjudaVoAtual.setStatusTelaCampo(new DomnStatusRegistro(status_confirmado));
			((List) telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO()).remove(indice);
			((List) telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO()).add(indice, telaCampoAjudaVoAtual);
			//objeto de controla ou principal deve ser atualizado
			telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().setCodigo(telaCampoAjudaVoAux.getCodigo());
			telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().setDescricaoAjudaCampo(telaCampoAjudaVoAux.getDescricaoAjudaCampo());
			telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().setCampoAjudaVo(telaCampoAjudaVoAux.getCampoAjudaVo());
			telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().setStatusTelaCampo(telaCampoAjudaVoAux.getStatusTelaCampo());
		}
		getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		processFlow(VIEW_INCLUIR_AJUDA, request, response, FORWARD);
	}

	/**
	 * Método utilizado para excluir os itens
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Wendell Pereira de Farias
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void excluirTelaCampoAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		TelaCampoAjudaBe telaCampoAjudaBe = null;
		try
		{
		   int codigo = StringUtil.toInt(request.getParameter(PARAMETRO_CODIGO_AJUDA_EXCLUIR));
		   if (Validador.isNumericoValido(codigo))
		   {
		      TelaCampoAjudaVo telaCampoAjudaVoAtual = new TelaCampoAjudaVo(codigo);
		      int indice = ((List) telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO()).indexOf(telaCampoAjudaVoAtual);
		      telaCampoAjudaVoAtual = (TelaCampoAjudaVo) ((List) telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO()).get(indice);
		      telaCampoAjudaVoAtual.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.INATIVO));
				telaCampoAjudaBe = new TelaCampoAjudaBe();
				obterInformacoesLogSefaz(request, telaCampoAjudaVoAtual);
				telaCampoAjudaBe.alterarTelaCampoAjuda(telaCampoAjudaVoAtual);
				//remove o item da lista        
				((List) telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO()).remove(indice);
				((List) telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO()).add(indice, telaCampoAjudaVoAtual);
				telaFuncionalidadeVo.setDescricaoTelaFuncionalidade(request.getParameter(CAMPO_DESCRICAO_AJUDA));
				telaFuncionalidadeVo.setInformacaoTituloTelaFuncionalidade(request.getParameter(CAMPO_TITULO_TELA_AJUDA));
		      getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		      processFlow(VIEW_INCLUIR_AJUDA, request, response, FORWARD);				
		   }			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if(telaCampoAjudaBe != null)
			{
				telaCampoAjudaBe.close();
			   telaCampoAjudaBe = null;
			}
		}
	}

	/**
	 * Método utilizado para incluir um novo item.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Wendell Pereira de Farias
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void incluirTelaCampoAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, LogException, 
			  PersistenciaException, AnotacaoException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		TelaFuncionalidadeBe telaFuncionalidadeBe = null;
		TelaCampoAjudaBe telaCampoAjudaBe = null;
		try
		{
			telaFuncionalidadeBe = new TelaFuncionalidadeBe();
			obterInformacoesLogSefaz(request, telaFuncionalidadeVo);
			telaFuncionalidadeBe.alterarTelaFuncionalidade(telaFuncionalidadeVo);
			//Avalia toda a coleção CollVo em busca dos itens que devem ser alterados
			telaCampoAjudaBe = new TelaCampoAjudaBe();
			for (Iterator iteTelaCampoAjudaVo = telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO().iterator(); iteTelaCampoAjudaVo.hasNext(); )
			{
				TelaCampoAjudaVo telaCampoAjudaVoAtual = (TelaCampoAjudaVo) iteTelaCampoAjudaVo.next();
				if (telaCampoAjudaVoAtual != null)
				{
					if (Validador.isDominioNumericoValido(telaCampoAjudaVoAtual.getStatusTelaCampo()))
					{
						obterInformacoesLogSefaz(request, telaCampoAjudaVoAtual);
						telaCampoAjudaVoAtual.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
						telaCampoAjudaVoAtual.setConsultaCompleta(true);
						telaCampoAjudaBe.alterarTelaCampoAjuda(telaCampoAjudaVoAtual);
					}
				}
			}
		   getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		   processFlow(VIEW_SUCESSO, request, response, FORWARD);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (telaFuncionalidadeBe != null)
			{
				telaFuncionalidadeBe.close();
				telaFuncionalidadeBe = null;
			}
			if (telaCampoAjudaBe != null)
			{
				telaCampoAjudaBe.close();
				telaCampoAjudaBe = null;
			}
		}
	}
}
