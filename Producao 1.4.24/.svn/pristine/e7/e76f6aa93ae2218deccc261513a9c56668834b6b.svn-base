/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConfiguracaoGerencialParametrosIncluir.java
 * Revisão: Marlo Einchenberg Motta / Wendell Pereira de Farias - Mar2008
 * Data revisão: 06/11/2007 
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.configuracaogerencialparametros;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.Form;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import java.text.ParseException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sefaz.mt.util.AlteraException;


/**
 * Servlet responsável por incluir Configuração Gerencial de Parâmetros.
 * @author Anderson Boehler Iglesias Araujo/Wendell Pereira de Farias
 * @version $Revision: 1.1.1.1 $
 */
public class ConfiguracaoGerencialParametrosIncluir extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_ADICIONAR_CONFIGURACAO_GERENCIAL = 2;
	private static final int REQUISICAO_INCLUIR_CONFIGURACAO_GERENCIAL = 3;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @throws ParseException Esta exceção deve ser lançada caso ocorra algum problema durante a conversão de uma String em data ou em valores primitivos.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo/Wendell Pereira de Farias
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  AlteraException, ParseException, ConexaoException, LogException, PersistenciaException, AnotacaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarIncluirConfiguracaoGerencialParametros(request, response);
					break;
				}
			case REQUISICAO_ADICIONAR_CONFIGURACAO_GERENCIAL:
				{
					adicionarConfiguracaoGerencialParametros(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_CONFIGURACAO_GERENCIAL:
				{
					incluirConfiguracaoGerencialParametros(request, response);
					break;
				}
		}
	}

	/**
	 *  Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo/Wendell Pereira de Farias
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_INCLUIR_CONFIGURACAO_GERENCIAL;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR)))
		{
			return REQUISICAO_ADICIONAR_CONFIGURACAO_GERENCIAL;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável incluir uma configuração gerencial de parâmetros.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo/Wendell Pereira de Farias
	 */
	private void solicitarIncluirConfiguracaoGerencialParametros(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo = controladorInterativoJSP(request);
		configuracaoGerencialParametrosVo.setStatusCadastrado(new DomnSimNao(DomnSimNao.NAO));
		configuracaoGerencialParametrosVo = new ConfiguracaoGerencialParametrosVo(configuracaoGerencialParametrosVo);
		configuracaoGerencialParametrosVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
		ConfiguracaoGerencialParametrosBe configuracaoGerencialParametrosBe = null;
		try
		{
			configuracaoGerencialParametrosBe = new ConfiguracaoGerencialParametrosBe();
			configuracaoGerencialParametrosBe.listarConfiguracaoGerencialParametrosNaoCadastrados(configuracaoGerencialParametrosVo);
			Validador.validaObjeto(configuracaoGerencialParametrosVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (configuracaoGerencialParametrosBe != null)
			{
				configuracaoGerencialParametrosBe.close();
				configuracaoGerencialParametrosBe = null;
			}
		}
		//variavel usada como flag na JSP para informar se existe ou nao uma configuração gerencial de parametros adicionada
		request.setAttribute(EXISTE, FALSO);
		getBuffer(request).setAttribute(CONFIGURACAO_GERENCIAL_PARAMETROS_VO, configuracaoGerencialParametrosVo, request);
		processFlow(VIEW_INCLUIR_CONFIGURACAO_GERENCIAL_PARAMETROS, request, response, FORWARD);
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @throws NumberFormatException Esta exceção deve ser lançada para indicar que a aplicação tentou converter uma string para um dos tipos numéricos, mas a string não contém o formato apropriado.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo/Wendell Pereira de Farias
	 */
	private ConfiguracaoGerencialParametrosVo controladorInterativoJSP(HttpServletRequest request) throws ClassCastException, 
			  NumberFormatException
	{
		ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo = 
				 (ConfiguracaoGerencialParametrosVo) getBuffer(request).getAttribute(CONFIGURACAO_GERENCIAL_PARAMETROS_VO);
		if (configuracaoGerencialParametrosVo == null)
		{
			configuracaoGerencialParametrosVo = new ConfiguracaoGerencialParametrosVo();
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR)))
		{
			int codigo = StringUtil.toInt(request.getParameter(BOTAO_ADICIONAR));
			String auxCampoValor = CAMPO_VALOR_CAMPO + codigo;
			if (Validador.isNumericoValido(codigo))
			{
				ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosAtualVo = 
							  new ConfiguracaoGerencialParametrosVo(codigo);
				int indice = 
							  ((List) configuracaoGerencialParametrosVo.getCollVO()).indexOf(configuracaoGerencialParametrosAtualVo);
				configuracaoGerencialParametrosAtualVo = 
								  (ConfiguracaoGerencialParametrosVo) ((List) configuracaoGerencialParametrosVo.getCollVO()).get(indice);
				configuracaoGerencialParametrosAtualVo.setValorItem(request.getParameter(auxCampoValor));
				configuracaoGerencialParametrosAtualVo.setStatusCadastrado(new DomnSimNao(DomnSimNao.SIM));
				configuracaoGerencialParametrosAtualVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
			}
		}
		return configuracaoGerencialParametrosVo;
	}

	/**
	 * Método que adiciona configurações gerenciais de parâmetros na tela.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo/Wendell Pereira de Farias
	 */
	private void adicionarConfiguracaoGerencialParametros(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo = controladorInterativoJSP(request);
		request.setAttribute(CONFIGURACAO_GERENCIAL_PARAMETROS_VO, configuracaoGerencialParametrosVo);
		//variavel usada como flag EXISTE na JSP para informar se existe ou nao uma configuração gerencial de parametros adicionada
		request.setAttribute(EXISTE, VERDADEIRO);
		processFlow(VIEW_INCLUIR_CONFIGURACAO_GERENCIAL_PARAMETROS, request, response, FORWARD);
	}

	/**
	 * Método responsável por incluir uma configuração gerencial.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @throws ParseException Esta exceção deve ser lançada caso ocorra algum problema durante a conversão de uma String em data ou em valores primitivos.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo/Wendell Pereira de Farias
	 */
	private void incluirConfiguracaoGerencialParametros(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  AlteraException, ParseException, ConexaoException, LogException, PersistenciaException, AnotacaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo = controladorInterativoJSP(request);
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		ConfiguracaoGerencialParametrosBe configuracaoGerencialParametrosBe = null;
		ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosPreoaradoVo = null;
		try
		{
			configuracaoGerencialParametrosBe = new ConfiguracaoGerencialParametrosBe();
			//codigo do LOG
			obterInformacoesLogSefaz(request, configuracaoGerencialParametrosVo);
			//fim do código do LOG
			configuracaoGerencialParametrosPreoaradoVo = 
							ConfiguracaoGerencialParametrosBe.preparaConfiguracaoGerencialParametros(configuracaoGerencialParametrosVo);
			configuracaoGerencialParametrosBe.incluirConfiguracaoGerencialParametros(configuracaoGerencialParametrosPreoaradoVo);
			request.setAttribute(ENTIDADE_VO, configuracaoGerencialParametrosPreoaradoVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(CONFIGURACAO_GERENCIAL_PARAMETROS_VO, configuracaoGerencialParametrosVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_CONFIGURACAO_GERENCIAL_PARAMETROS, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (configuracaoGerencialParametrosBe != null)
			{
				configuracaoGerencialParametrosBe.close();
				configuracaoGerencialParametrosBe = null;
			}
		}
	}
}
