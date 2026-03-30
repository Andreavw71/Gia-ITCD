/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: MultaDeMoraIncluir.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 12/11/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.multademora;

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

import br.gov.mt.sefaz.itc.model.tabelabasica.multademora.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.multademora.MultaDeMoraBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.multademora.MultaDeMoraVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por incluir Multa Mora no banco de dados.
 * @author Elizabeth Barbosa Moreira
 * @version $Revision: 1.1.1.1 $
 */
public class MultaDeMoraIncluir extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_INCLUIR_MULTA_MORA = 2;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @throws ConsultaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Elizabeth Barbosa Moreira
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
					solicitarIncluirMultaMora(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_MULTA_MORA:
				{
					incluirMultaMora(request, response);
					break;
				}
		}
	}

	/**
	 *  Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_INCLUIR_MULTA_MORA;
		}
		else
		{
			return REQUISICAO_VAZIA;
		}
	}

	/**
	 * Método responsável por redirecionar para a JSP de incluir MultaDeMora.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private void solicitarIncluirMultaMora(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(MULTADEMORA_VO, new MultaDeMoraVo(), request);
		processFlow(VIEW_INCLUIR_MULTA_MORA, request, response, FORWARD);
	}

	/**
	 * Método responsável por incluir MultaDeMora.
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
	 * @implemented by Elizabeth Barbosa
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void incluirMultaMora(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, ConsultaException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		MultaDeMoraVo multademoraVo = controladorInterativoJSP(request);
		Validador.validaObjeto(multademoraVo);
		MultaDeMoraBe multademoraBe = null;
		try
		{
			multademoraBe = new MultaDeMoraBe();
			obterInformacoesLogSefaz(request, multademoraVo);
			multademoraBe.incluirMultaDeMora(multademoraVo);
			request.setAttribute(ENTIDADE_VO, multademoraVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(MULTADEMORA_VO, multademoraVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_MULTA_MORA, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (multademoraBe != null)
			{
				multademoraBe.close();
				multademoraBe = null;
			}
		}
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	private MultaDeMoraVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		MultaDeMoraVo multademoraVo = (MultaDeMoraVo) getBuffer(request).getAttribute(MULTADEMORA_VO);
		Validador.validaObjeto(multademoraVo);
		if (Validador.isStringValida(request.getParameter(CAMPO_TIPO_MULTA_MORA)))
		{
			multademoraVo.setPercentualMultaMora(StringUtil.monetarioToDouble(request.getParameter(CAMPO_TIPO_MULTA_MORA)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_PERCENTUAL_MULTA_MORA)))
		{
			multademoraVo.setPercentualMaximoMultaMora(StringUtil.monetarioToDouble(request.getParameter(CAMPO_PERCENTUAL_MULTA_MORA)));
		}
		multademoraVo.setStatusMultaMora(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		return multademoraVo;
	}
}
