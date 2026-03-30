package br.gov.mt.sefaz.itc.modulo.tabelabasica.naturezadaoperacao;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author Rogério Sanches de Oliveira
 * @revisao Wendell Pereira de Farias /Mar2008
 * @version $Revision: 1.1.1.1 $
 */
public class NaturezaOperacaoAlterar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_ALTERAR_NATUREZA_OPERACAO = 3;
	private static final int REQUISICAO_SOLICITAR_ALTERAR_NATUREZA_OPERACAO = 2;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ConsultaException
	 * @implemented by Rogério Sanches de Oliveira
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, LogException, 
			  AnotacaoException, PersistenciaException, ConsultaException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarConsultarNaturezaOperacao(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ALTERAR_NATUREZA_OPERACAO:
				{
					solicitarAlterarNaturezaOperacao(request, response);
					break;
				}
			case REQUISICAO_ALTERAR_NATUREZA_OPERACAO:
				{
					alterarNaturezaOperacao(request, response);
					break;
				}
		}
	}

	/**
	 *  Este método é responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (request.getAttribute(NATUREZA_OPERACAO_VO) != null)
		{
			return REQUISICAO_SOLICITAR_ALTERAR_NATUREZA_OPERACAO;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_ALTERAR_NATUREZA_OPERACAO;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por redirecionar para a JSP de alterar natrueza operacao
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitarConsultarNaturezaOperacao(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(NATUREZA_OPERACAO_VO, new NaturezaOperacaoVo(), request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_NATUREZA_DA_OPERACAO_PESQUISAR_NATUREZA_DA_OPERACAO, request), request, response, FORWARD);
	}

	/**
	 * Método responsável por alterar um Bem.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ConsultaException
	 * @implemented by Rogério Sanches de Oliveira
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void alterarNaturezaOperacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, LogException, 
			  AnotacaoException, PersistenciaException, ConsultaException
	{
		NaturezaOperacaoVo naturezaOperacaoVo = controladorInterativoJSP(request);
		Validador.validaObjeto(naturezaOperacaoVo);
		NaturezaOperacaoBe naturezaOperacaoBe = null;
		try
		{
			naturezaOperacaoBe = new NaturezaOperacaoBe();
			obterInformacoesLogSefaz(request, naturezaOperacaoVo);
			naturezaOperacaoBe.alterarNaturezaOperacao(naturezaOperacaoVo);
			request.setAttribute(ENTIDADE_VO, naturezaOperacaoVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch(DadoNecessarioInexistenteException e)
		{
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(NATUREZA_OPERACAO_VO, naturezaOperacaoVo);
			processFlow(VIEW_ERRO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_PERCENTUAL_NATUREZA_OPERACAO);
			getBuffer(request).setAttribute(NATUREZA_OPERACAO_VO, naturezaOperacaoVo, request);
			processFlow(VIEW_ALTERAR_NATUREZA_OPERACAO, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_NATUREZA_OPERACAO);
			getBuffer(request).setAttribute(NATUREZA_OPERACAO_VO, naturezaOperacaoVo, request);
			processFlow(VIEW_ALTERAR_NATUREZA_OPERACAO, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (naturezaOperacaoBe != null)
			{
				naturezaOperacaoBe.close();
				naturezaOperacaoBe = null;
			}
		}
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Alterar Bem e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @throws NumberFormatException Esta exceção deve ser lançada para indicar que a aplicação tentou converter uma string para um dos tipos numéricos, mas a string não contém o formato apropriado.
	 * @return naturezaOperacaoVo Natureza (Value Object).
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private NaturezaOperacaoVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		NaturezaOperacaoVo naturezaOperacaoVo = iniciaNaturezaOperacaoVo(request);
		return naturezaOperacaoVo;
	}

	/**
	 * Este método verifica se o buffer possui o VO para adicionar as informações da funcionalidade NaturezaOperacao. Se possuir, o VO é retornado.
	 * Caso contrário, é criada uma nova instância do NaturezaOperacaoVo para atender a necessidade do fluxo.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @return NaturezaOperacaoVo (Value Object).
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private NaturezaOperacaoVo iniciaNaturezaOperacaoVo(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		NaturezaOperacaoVo naturezaOperacaoVo = (NaturezaOperacaoVo) getBuffer(request).getAttribute(NATUREZA_OPERACAO_VO);
		Validador.validaObjeto(naturezaOperacaoVo);
	   int flagIsencaoPrevistaLei = StringUtil.toInt(request.getParameter(CAMPO_SELECT_ISENCAO_PREVISTA_LEI));
	   if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_ISENCAO_PREVISTA_LEI)))
	   {
	      naturezaOperacaoVo.setFlagIsencaoPrevistaLei( new DomnSimNao( flagIsencaoPrevistaLei ) );
	   }
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_NATUREZA_OPERACAO)))
		{
			naturezaOperacaoVo.setDescricaoNaturezaOperacao(request.getParameter(CAMPO_DESCRICAO_NATUREZA_OPERACAO));
		}
		int statusNaturezaOperacao = StringUtil.toInt(request.getParameter(CAMPO_SELECT_STATUS));
		if (Validador.isNumericoValido(statusNaturezaOperacao))
		{
			naturezaOperacaoVo.setStatusNaturezaOperacao(new DomnStatusRegistro(statusNaturezaOperacao));
		}
		return naturezaOperacaoVo;
	}

	/**
	 * Método responsável por redirecionar para a JSP de alterar Bem.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitarAlterarNaturezaOperacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		NaturezaOperacaoVo naturezaOperacaoVo = (NaturezaOperacaoVo) request.getAttribute(NATUREZA_OPERACAO_VO);
		Validador.validaObjeto(naturezaOperacaoVo);
		getBuffer(request).setAttribute(NATUREZA_OPERACAO_VO, naturezaOperacaoVo, request);
		processFlow(VIEW_ALTERAR_NATUREZA_OPERACAO, request, response, FORWARD);
	}
}
