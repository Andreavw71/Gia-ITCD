/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AjudaPesquisar.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 12/11/2007
 * Adaptações: Janeiro de 2008 / Wendell Farias 
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.ajuda;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.funcionalidade.FuncionalidadeBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.funcionalidade.FuncionalidadeVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.moduloajuda.ModuloAjudaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.moduloajuda.ModuloAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telaajuda.TelaAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda.TelaCampoAjudaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda.TelaCampoAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telafuncionalidade.TelaFuncionalidadeBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telafuncionalidade.TelaFuncionalidadeVo;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por pesquisar Ajuda no banco de dados.
 * @author Elizabeth Barbosa Moreira
 * @version $Revision: 1.1.1.1 $
 */
public class AjudaPesquisar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_LISTAR_FUNCIONALIDADE_AJUDA = 3;
	private static final int REQUISICAO_LISTAR_SUBMODULO_AJUDA = 2;
	private static final int REQUISICAO_LISTAR_TELA_CAMPO_AJUDA = 5;
	private static final int REQUISICAO_LISTAR_TELA_FUNCIONALIDADE_AJUDA = 4;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, SQLException, ObjetoObrigatorioException, ConsultaException, 
			  ConexaoException
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
		}
	}

	/**
	 *  Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Elizabeth Barbosa Moreira
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
		if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR)))
		{
			return REQUISICAO_LISTAR_TELA_CAMPO_AJUDA;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Pesquisar e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private TelaFuncionalidadeVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo = 
				 (TelaFuncionalidadeVo) getBuffer(request).getAttribute(TELA_FUNCIONALIDADE_VO);
		if (telaFuncionalidadeVo == null)
		{
			telaFuncionalidadeVo = new TelaFuncionalidadeVo();
		}
		if (request.getParameter(CAMPO_SELECT_MODULO_AJUDA) != null)
		{
			if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_MODULO_AJUDA)))
			{
				long codigoModulo = StringUtil.toLong(request.getParameter(CAMPO_SELECT_MODULO_AJUDA));
				telaFuncionalidadeVo.getFuncionalidadeVo().getModuloAjudaVo().setCodigo(codigoModulo);
				telaFuncionalidadeVo.getTelaAjudaVo().getTelaCampoAjudaVo().getCollVO().clear();
				telaFuncionalidadeVo.getTelaAjudaVo().getCollVO().clear();
			}
			else
			{
				telaFuncionalidadeVo.getCollVO().clear();
			}
		}
		if (request.getParameter(CAMPO_SELECT_SUBMODULO_AJUDA) != null)
		{
			if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_SUBMODULO_AJUDA)))
			{
				long codigoSubModulo = StringUtil.toLong(request.getParameter(CAMPO_SELECT_SUBMODULO_AJUDA));
				telaFuncionalidadeVo.getFuncionalidadeVo().getModuloAjudaVo().getSubModuloAjuda().setCodigo(codigoSubModulo);
			}
			else
			{
				if (request.getParameter(CAMPO_SELECT_SUBMODULO_AJUDA).equals(""))
				{
					telaFuncionalidadeVo.getFuncionalidadeVo().getModuloAjudaVo().setSubModuloAjuda(new ModuloAjudaVo());
					telaFuncionalidadeVo.getFuncionalidadeVo().getCollVO().clear();
					telaFuncionalidadeVo.getCollVO().clear();
				}
			}
		}
		if (request.getParameter(CAMPO_SELECT_FUNCIONALIDADE_AJUDA) != null)
		{
			if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_FUNCIONALIDADE_AJUDA)))
			{
				long codigoFuncionalidade = StringUtil.toLong(request.getParameter(CAMPO_SELECT_FUNCIONALIDADE_AJUDA));
				telaFuncionalidadeVo.getFuncionalidadeVo().setCodigo(codigoFuncionalidade);
				telaFuncionalidadeVo.setCodigo(codigoFuncionalidade);
			}
			else
			{
				if (request.getParameter(CAMPO_SELECT_FUNCIONALIDADE_AJUDA).equals(""))
				{
					telaFuncionalidadeVo.getFuncionalidadeVo().getCollVO().clear();
					telaFuncionalidadeVo.getFuncionalidadeVo().setDescricaoFuncionalidade("");
					telaFuncionalidadeVo.getFuncionalidadeVo().setCodigo(0);
					telaFuncionalidadeVo.getTelaAjudaVo().getCollVO().clear();
					telaFuncionalidadeVo.getTelaAjudaVo().setCodigo(0);
					telaFuncionalidadeVo.getCollVO().clear();
				}
			}
		}
		if (request.getParameter(CAMPO_SELECT_TELA_AJUDA) != null)
		{
			if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TELA_AJUDA)))
			{
				long codigoTelaAjuda = StringUtil.toLong(request.getParameter(CAMPO_SELECT_TELA_AJUDA));
				telaFuncionalidadeVo.getTelaAjudaVo().setCodigo(codigoTelaAjuda);
			}
			else
			{
				telaFuncionalidadeVo.getTelaAjudaVo().getCollVO().clear();
				telaFuncionalidadeVo.getTelaAjudaVo().setCodigo(0);
				telaFuncionalidadeVo.getCollVO().clear();
				telaFuncionalidadeVo.setDescricaoTelaFuncionalidade("");
				telaFuncionalidadeVo.setInformacaoTituloTelaFuncionalidade("");
			}
		}
		return telaFuncionalidadeVo;
	}

	/**
	 * Método responsável por solicitar a consulta de módulos da ajuda.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private void solicitarListarModuloAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		Validador.validaObjeto(telaFuncionalidadeVo);
		ModuloAjudaVo moduloAjudaVo = new ModuloAjudaVo(new ModuloAjudaVo());
		ModuloAjudaBe moduloAjudaBe = null;
		try
		{
			moduloAjudaBe = new ModuloAjudaBe();
			moduloAjudaBe.listarModuloAjuda(moduloAjudaVo);
			telaFuncionalidadeVo.getFuncionalidadeVo().setModuloAjudaVo(moduloAjudaVo);
			Validador.validaObjeto(telaFuncionalidadeVo);
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
		getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		processFlow(VIEW_PESQUISAR_AJUDA, request, response, FORWARD);
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
			  SQLException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException
	{
		ModuloAjudaBe moduloAjudaBe = null;
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		ModuloAjudaVo moduloAjudaVo = telaFuncionalidadeVo.getFuncionalidadeVo().getModuloAjudaVo();
		ModuloAjudaVo consultaSubModuloVo = new ModuloAjudaVo(moduloAjudaVo);
		consultaSubModuloVo.setCodigo(moduloAjudaVo.getCodigo());
		consultaSubModuloVo.setSubModuloAjuda(consultaSubModuloVo);
		consultaSubModuloVo = new ModuloAjudaVo(consultaSubModuloVo);
		try
		{
			moduloAjudaBe = new ModuloAjudaBe();
			consultaSubModuloVo = moduloAjudaBe.listarSubModuloAjuda(consultaSubModuloVo);
			telaFuncionalidadeVo.getFuncionalidadeVo().getModuloAjudaVo().setSubModuloAjuda(consultaSubModuloVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_AJUDA);
		}
		finally
		{
			if (moduloAjudaBe != null)
			{
				moduloAjudaBe.close();
				moduloAjudaBe = null;
			}
		}
		getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		processFlow(VIEW_PESQUISAR_AJUDA, request, response, FORWARD);
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
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void listarFuncionalidadeAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  SQLException, ConsultaException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		FuncionalidadeVo funcionalidadeVo = telaFuncionalidadeVo.getFuncionalidadeVo();
		funcionalidadeVo.setParametroConsulta(funcionalidadeVo);
		telaFuncionalidadeVo.setTelaAjudaVo(new TelaAjudaVo());
		FuncionalidadeBe funcionalidadeBe = null;
		try
		{
			funcionalidadeBe = new FuncionalidadeBe();
			funcionalidadeBe.listarFuncionalidade(funcionalidadeVo);
			telaFuncionalidadeVo.setFuncionalidadeVo(funcionalidadeVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_AJUDA);
		}
		finally
		{
			if (funcionalidadeBe != null)
			{
				funcionalidadeBe.close();
				funcionalidadeBe = null;
			}
		}
		getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		processFlow(VIEW_PESQUISAR_AJUDA, request, response, FORWARD);
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
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void listarTelaFuncionalidadeAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  SQLException, ConsultaException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		telaFuncionalidadeVo.setConsultaCompleta(true);
		telaFuncionalidadeVo.setParametroConsulta(telaFuncionalidadeVo);
		TelaFuncionalidadeBe telaFuncionalidadeBe = null;
		try
		{
			telaFuncionalidadeBe = new TelaFuncionalidadeBe();
			telaFuncionalidadeVo.setCollVO(null);
			telaFuncionalidadeBe.listarTelaFuncionalidade(telaFuncionalidadeVo);
			TelaFuncionalidadeBe.atualizarTelaFuncionalidadeVo(telaFuncionalidadeVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_AJUDA);
		}
		finally
		{
			if (telaFuncionalidadeBe != null)
			{
				telaFuncionalidadeBe.close();
				telaFuncionalidadeBe = null;
			}
		}
		getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		processFlow(VIEW_PESQUISAR_AJUDA, request, response, FORWARD);
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
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void listarTelaCampoAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		TelaCampoAjudaBe telaCampoAjudaBe = null;
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		//Atualiza o VO da telaFuncionalide para exibir as informações título e descrição da Tela/Funcionalidade.
		TelaFuncionalidadeBe.atualizarTelaFuncionalidadeVo(telaFuncionalidadeVo);
		TelaCampoAjudaVo telaCampoAjudaVo = new TelaCampoAjudaVo();
		telaCampoAjudaVo.setTelaAjudaVo(telaFuncionalidadeVo.getTelaAjudaVo());
		telaCampoAjudaVo.setParametroConsulta(telaCampoAjudaVo);
		telaCampoAjudaVo.setConsultaCompleta(true);
		try
		{
			telaCampoAjudaBe = new TelaCampoAjudaBe();
			telaCampoAjudaBe.listarTelaCampoAjuda(telaCampoAjudaVo);
			Validador.validaObjeto(telaCampoAjudaVo);
			telaFuncionalidadeVo.getTelaAjudaVo().setTelaCampoAjudaVo(telaCampoAjudaVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_AJUDA);
		}
		finally
		{
			if (telaCampoAjudaBe != null)
			{
				telaCampoAjudaBe.close();
				telaCampoAjudaBe = null;
			}
		}
		getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		processFlow(VIEW_PESQUISAR_AJUDA, request, response, FORWARD);
	}
}
