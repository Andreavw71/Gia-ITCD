/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConstrucaoIncluir.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 12/11/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.construcao;

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
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.Form;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por incluir Construcao no banco de dados.
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.1.1.1 $
 */
public class ConstrucaoIncluir extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_INCLUIR_CONSTRUCAO = 2;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @throws ConsultaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Rogério Sanches de Oliveira
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, ConsultaException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarIncluirConstrucao(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_CONSTRUCAO:
				{
					incluirConstrucao(request, response);
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
		if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_INCLUIR_CONSTRUCAO;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por redirecionar para a JSP de incluir construcao.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitarIncluirConstrucao(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(CONSTRUCAO_VO, new ConstrucaoVo(), request);
		processFlow(VIEW_INCLUIR_CONSTRUCAO, request, response, FORWARD);
	}

	/**
	 * Método responsável por incluir construcao.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @throws ConsultaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Rogério Sanches de Oliveira
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void incluirConstrucao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, ConsultaException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		ConstrucaoVo construcaoVo = controladorInterativoJSP(request);
		Validador.validaObjeto(construcaoVo);
		ConstrucaoBe construcaoBe = null;
		try
		{
			construcaoBe = new ConstrucaoBe();
			obterInformacoesLogSefaz(request, construcaoVo);
			construcaoBe.incluirConstrucao(construcaoVo);
			request.setAttribute(ENTIDADE_VO, construcaoVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(CONSTRUCAO_VO, construcaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_CONSTRUCAO, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(CONSTRUCAO_VO, construcaoVo, request);
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_CONSTRUCAO);
			processFlow(VIEW_INCLUIR_CONSTRUCAO, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (construcaoBe != null)
			{
				construcaoBe.close();
				construcaoBe = null;
			}
		}
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir Construcao e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @throws NumberFormatException Esta exceção deve ser lançada para indicar que a aplicação tentou converter uma string para um dos tipos numéricos, mas a string não contém o formato apropriado.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private ConstrucaoVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		ConstrucaoVo construcaoVo = (ConstrucaoVo) getBuffer(request).getAttribute(CONSTRUCAO_VO);
		Validador.validaObjeto(construcaoVo);
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_CONSTRUCAO)))
		{
			construcaoVo.setDescricaoConstrucao(request.getParameter(CAMPO_DESCRICAO_CONSTRUCAO));
		}
		int permiteUrbano = StringUtil.toInt(request.getParameter(CAMPO_CHECK_PERMITE_IMOVEL_URBANO));
		if (permiteUrbano == 0)
		{
			construcaoVo.setPermiteFichaUrbano(new DomnSimNao(DomnSimNao.NAO));
		}
		else if (Validador.isNumericoValido(permiteUrbano))
		{
			construcaoVo.setPermiteFichaUrbano(new DomnSimNao(permiteUrbano));
		}
		int permiteRural = StringUtil.toInt(request.getParameter(CAMPO_CHECK_PERMITE_IMOVEL_RURAL));
		if (permiteRural == 0)
		{
			construcaoVo.setPermiteFichaRural(new DomnSimNao(DomnSimNao.NAO));
		}
		else if (Validador.isNumericoValido(permiteRural))
		{
			construcaoVo.setPermiteFichaRural(new DomnSimNao(permiteRural));
		}
		construcaoVo.setStatusConstrucao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		return construcaoVo;
	}
}
