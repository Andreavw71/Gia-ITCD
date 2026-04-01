/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConfiguracaoGerencialParametrosAlterar.java
 * Revisão: Marlo Einchenberg Motta
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por alterar Configuração Gerencial de Parâmetros.
 * @author Anderson Boehler Iglesias Araujo/Wendell Pereira de Farias
 * @version $Revision: 1.1.1.1 $
 */
public class ConfiguracaoGerencialParametrosAlterar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_ADICIONAR_CONFIGURACAO_GERENCIAL = 2;
	private static final int REQUISICAO_ALTERAR_CONFIGURACAO_GERENCIAL = 3;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Anderson Boehler Iglesias Araujo/Wendell Pereira de Farias
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, 
			  ConsultaException, LogException, PersistenciaException, AnotacaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarAlterarConfiguracaoGerencialParametros(request, response);
					break;
				}
			case REQUISICAO_ADICIONAR_CONFIGURACAO_GERENCIAL:
				{
					adicionarConfiguracaoGerencialParametros(request, response);
					break;
				}
			case REQUISICAO_ALTERAR_CONFIGURACAO_GERENCIAL:
				{
					alterarConfiguracaoGerencialParametros(request, response);
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
			return REQUISICAO_ALTERAR_CONFIGURACAO_GERENCIAL;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_ALTERAR)))
		{
			return REQUISICAO_ADICIONAR_CONFIGURACAO_GERENCIAL;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por realizar alteração.
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
	private void solicitarAlterarConfiguracaoGerencialParametros(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo = new ConfiguracaoGerencialParametrosVo();
		configuracaoGerencialParametrosVo.setStatusCadastrado(new DomnSimNao(DomnSimNao.SIM));
		configuracaoGerencialParametrosVo = new ConfiguracaoGerencialParametrosVo(configuracaoGerencialParametrosVo);
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
		//variavel usada como flag EXISTE na JSP para informar se existe ou nao uma configuração gerencial de parametros adicionada
		request.setAttribute(EXISTE, VERDADEIRO);
		getBuffer(request).setAttribute(CONFIGURACAO_GERENCIAL_PARAMETROS_VO, configuracaoGerencialParametrosVo, request);
		processFlow(VIEW_ALTERAR_CONFIGURACAO_GERENCIAL_PARAMETROS, request, response, FORWARD);
	}

	/**
	 * /**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request
	 * @throws ClassCastException
	 * @throws ObjetoObrigatorioException
	 * @return ConfiguracaoGerencialParametrosVo
	 * @implemented by Wendell Pereira de Farias
	 */
	private ConfiguracaoGerencialParametrosVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo = (ConfiguracaoGerencialParametrosVo) getBuffer(request).getAttribute(CONFIGURACAO_GERENCIAL_PARAMETROS_VO);
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		return configuracaoGerencialParametrosVo;
	}

	/**
	 * Adiciona na tela as Configurações Gerenciais de Parâmetros.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo / Wendell Pereira de Farias
	 */
	private void adicionarConfiguracaoGerencialParametros(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException
	{
		//variavel usada como flag na JSP para informar se existe ou nao uma configuração gerencial de parametros adicionada	
		ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo = controladorInterativoJSP(request);
		try
		{
		   if (Validador.isStringValida(request.getParameter(BOTAO_ALTERAR)))
		   {
		      int codigo = StringUtil.toInt(request.getParameter(BOTAO_ALTERAR));
		      String auxCampoValor = CAMPO_VALOR_CAMPO + codigo;
		      if (Validador.isNumericoValido(codigo))
		      {
		         if (Validador.isObjetoValido(request.getParameter(auxCampoValor)))
		         {
		            ConfiguracaoGerencialParametrosBe.adicionarItemAlterar(configuracaoGerencialParametrosVo, codigo, request.getParameter(auxCampoValor));
		         }
		      }
		   }     
		   request.setAttribute(EXISTE, FALSO);
		}
		catch(ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
		}
	   getBuffer(request).setAttribute(CONFIGURACAO_GERENCIAL_PARAMETROS_VO, configuracaoGerencialParametrosVo, request);
	   processFlow(VIEW_ALTERAR_CONFIGURACAO_GERENCIAL_PARAMETROS, request, response, FORWARD);        		
	}

	/**
	 * Altera as Configuração Gerenciais de Parâmetros.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo/Wendell Pereira de Farias
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void alterarConfiguracaoGerencialParametros(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, LogException, PersistenciaException, AnotacaoException
	{
		ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo = (ConfiguracaoGerencialParametrosVo) getBuffer(request).getAttribute(CONFIGURACAO_GERENCIAL_PARAMETROS_VO);
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		configuracaoGerencialParametrosVo.setStatusCadastrado(new DomnSimNao(DomnSimNao.SIM));
		ConfiguracaoGerencialParametrosBe configuracaoGerencialParametrosBe = null;
		ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosPreparadoVo = null;
		try
		{
			configuracaoGerencialParametrosBe = new ConfiguracaoGerencialParametrosBe();
			obterInformacoesLogSefaz(request, configuracaoGerencialParametrosVo);
			configuracaoGerencialParametrosPreparadoVo = ConfiguracaoGerencialParametrosBe.preparaConfiguracaoGerencialParametros(configuracaoGerencialParametrosVo);
			configuracaoGerencialParametrosBe.alterarConfiguracaoGerencialParametros(configuracaoGerencialParametrosPreparadoVo);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(CONFIGURACAO_GERENCIAL_PARAMETROS_VO, configuracaoGerencialParametrosVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ALTERAR_CONFIGURACAO_GERENCIAL_PARAMETROS, request, response, FORWARD);
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
		request.setAttribute(ENTIDADE_VO, configuracaoGerencialParametrosVo);
		processFlow(VIEW_SUCESSO, request, response, FORWARD);
	}
}
