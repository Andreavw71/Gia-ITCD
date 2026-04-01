package br.gov.mt.sefaz.itc.modulo.giaitcd.giaitcdautenticidade;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ImpossivelCriptografarException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.Form;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.io.IOException;

import java.sql.SQLException;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sefaz.mt.util.AlteraException;
import sefaz.mt.util.IncluiException;


/**
 *
 * @author Elizabeth Barbosa Moreira
 * @version $Revision: 1.1.1.1 $
 */
public class GIAITCDAutenticidadePesquisar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_CONSULTAR_AUTENTICIDADE = 2;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws IntegracaoException Esta exceção deve ser lançada quando o sistema tenta realizar integração com um sistema externo e a integração causa uma Exception.
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
			  ParametroObrigatorioException, IntegracaoException, RegistroExistenteException, AlteraException, IncluiException, 
			  ImpossivelCriptografarException, ParseException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarImprimirAutenticidade(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_AUTENTICIDADE:
				{
					consultarAutenticidade(request, response);
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
		if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR)))
		{
			return REQUISICAO_CONSULTAR_AUTENTICIDADE;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 *  Método responsável por controlar as requisições da classe.
	 * @param request
	 * @return
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private GIAITCDVo controladorInterativoJSP(HttpServletRequest request)
	{
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		if (giaITCDVo == null)
		{
			giaITCDVo = new GIAITCDVo();
		}
		return giaITCDVo;
	}

	/**
	 *  Método responsável solicitar a impressão da autenticidade de uma GIAITCD.
	 * @param request
	 * @param response
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private void solicitarImprimirAutenticidade(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		processFlow(VIEW_PESQUISAR_GIAITCD_AUTENTICIDADE, request, response, FORWARD);
	}

	/**
	 *  Método responsável por consultar a GIAITCD e autenticidade, redirecionando para servelet pai imprimir GIAITCD.
	 * @param request
	 * @param response
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	private void consultarAutenticidade(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
			  ObjetoObrigatorioException, ConexaoException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		giaITCDVo = new GIAITCDVo(giaITCDVo);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			giaITCDVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
			giaITCDVo.setConsultaCompleta(true);
			(giaITCDVo.getParametroConsulta()).setCodigoAutenticidade(null);
			giaITCDVo = giaITCDBe.consultarGIAITCDAutenticidade(giaITCDVo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			e.printStackTrace();
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_GIA_ITCD_NAO_EXISTE));
			processFlow(VIEW_PESQUISAR_GIAITCD_AUTENTICIDADE, request, response, FORWARD);
		}
      catch (IOException e)
      {
         e.printStackTrace();
         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
         request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_GIA_ITCD_NAO_EXISTE));
         processFlow(VIEW_PESQUISAR_GIAITCD_AUTENTICIDADE, request, response, FORWARD);
      }
      finally
		{
			if (giaITCDBe != null)
			{
				giaITCDBe.close();
				giaITCDBe = null;
			}
		}
		//armazena a consulta da GIA e da Autenticidade no Buffer
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		request.setAttribute(PARAMETRO_IMPRIMIR_AUTENTICIDADE_GIA_ITCD, VERDADEIRO);
		//redireciona para servelet PAI que é Autenticidade Imprimir
	}
}
