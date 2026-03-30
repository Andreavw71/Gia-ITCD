/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BemIncluir.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 08/10/2007
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.bem;

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

import br.gov.mt.sefaz.itc.model.tabelabasica.bem.BemBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.bem.BemVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.bem.Form;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoVerificacao;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por incluir Bem.
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.2 $
 */
public class BemIncluir extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_INCLUIR_BEM = 2;

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
	 * @implemented by Anderson Boehler Iglesias Araujo
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
					solicitarIncluirBem(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_BEM:
				{
					incluirBem(request, response);
					break;
				}
		}
	}

	/**
	 *  Este método é responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_INCLUIR_BEM;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por redirecionar para a JSP de incluir Bem.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarIncluirBem(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(BEM_VO, new BemVo(), request);
		processFlow(VIEW_INCLUIR_BEM, request, response, FORWARD);
	}

	/**
	 * Método responsável por incluir um Bem.
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
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void incluirBem(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, ConsultaException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		BemVo bemVo = controladorInterativoJSP(request);
		Validador.validaObjeto(bemVo);
		BemBe bemBe = null;
		try
		{
			bemBe = new BemBe();
			obterInformacoesLogSefaz(request, bemVo);
			bemBe.incluirBem(bemVo);
			request.setAttribute(ENTIDADE_VO, bemVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(BEM_VO, bemVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_BEM, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(BEM_VO, bemVo, request);
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_BEM);
			processFlow(VIEW_INCLUIR_BEM, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (bemBe != null)
			{
				bemBe.close();
				bemBe = null;
			}
		}
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir Bem e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private BemVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		BemVo bemVo = iniciaBemVo(request);
		return bemVo;
	}

	/**
	 * Método responsável por instanciar um objeto do tivo <i>BemVo</i> e atribuir os valores capturados pelo objeto <i>request</i>.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private BemVo iniciaBemVo(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		BemVo bemVo = (BemVo) getBuffer(request).getAttribute(BEM_VO);
		Validador.validaObjeto(bemVo);
		int tipoBem = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_BEM));
		if (Validador.isNumericoValido(tipoBem))
		{
			bemVo.setClassificacaoTipoBem(new DomnTipoBem(tipoBem));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_BEM)))
		{
			bemVo.setDescricaoTipoBem(request.getParameter(CAMPO_DESCRICAO_BEM));
		}
		bemVo.setStatusBem(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		if(Validador.isStringValida(request.getParameter(CAMPO_POSSUI_CONSTRUCAO)))
		{
			bemVo.setPossuiConstrucao(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_POSSUI_CONSTRUCAO))));
		}
	   int tipoVerificacao = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_VERIFICACAO));
	   if (Validador.isNumericoValido(tipoVerificacao))
	   {
	      bemVo.setTipoVerificacao(new DomnTipoVerificacao(tipoVerificacao));
	   }
	   int tipoProtocoloGIA = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_PROTOCOLO));
	   if (Validador.isNumericoValido(tipoProtocoloGIA))
	   {
	      bemVo.setTipoProtocoloGIA(new DomnTipoProtocolo(tipoProtocoloGIA));
	   }
		return bemVo;
	}	
}
