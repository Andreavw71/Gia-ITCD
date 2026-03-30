package br.gov.mt.sefaz.itc.modulo.generico.vincularDARGIAITCD;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.Form;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarVo;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class VincularDARGIAITCD extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_VINCULAR_DAR_GIA = 2;

	/**
	 * Este método trabalha colaborativamente com o método
	 * redirecionar. O método redirecionar determina a ação a ser tomada e
	 * processoRequest a executa.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws IntegracaoException
	 * @implemented by Wendell Pereira de Farias
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, ConexaoException, 
			  LogException, PersistenciaException, AnotacaoException, IntegracaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					iniciaVincularGIAITCDDAR(request, response);
					break;
				}
			case REQUISICAO_VINCULAR_DAR_GIA:
				{
					vincularDarGIAITCD(request, response);
					break;
				}
		}
	}

	/**
	 * Este método é responsável pela análise dos parâmetros e a
	 * tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request
	 * @return
	 * @implemented by Wendell Pereira de Farias
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_VINCULAR_DAR_GIA;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 *  Metodo responsável por iniciar o processo de vinculação entre uma DAR e a GIAITCD.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Wendell Pereira de Farias
	 */
	private void iniciaVincularGIAITCDDAR(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(GIAITCDDAR_VO, new GIAITCDDarVo(), request);
		processFlow(VIEW_VINCULAR_DAR_GIAITCD, request, response, FORWARD);
	}

	/**
	 *  Método que recupera do request os dados informados pelo usuário
	 * @param request
	 * @return
	 * @implemented by Wendell Pereira de Farias
	 */
	private GIAITCDDarVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		GIAITCDDarVo giaITCDDarVo = (GIAITCDDarVo) getBuffer(request).getAttribute(GIAITCDDAR_VO);
		Validador.validaObjeto(giaITCDDarVo);
		getBuffer(request).setAttribute(GIAITCDDAR_VO, giaITCDDarVo, request);
		//Verifica se PARAMETRO_CODIGO_GIA é valido.
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_GIA)))
		{
			giaITCDDarVo.getGia().setCodigo(StringUtil.toLong(request.getParameter(PARAMETRO_CODIGO_GIA)));
		}
		//Verifica se PARAMETRO_CODIGO_DAR é válido.
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_DAR)))
		{
			giaITCDDarVo.setCodigoUofSequencial(StringUtil.toLong(request.getParameter(PARAMETRO_CODIGO_DAR)));
		}
		//Verifica se PARAMETRO_NUMERO_PARCELA_DAR é válido.
		if (Validador.isStringValida(request.getParameter(PARAMETRO_NUMERO_PARCELA_DAR)))
		{
			if (StringUtil.toInt(request.getParameter(PARAMETRO_NUMERO_PARCELA_DAR)) > 0)
			{
				giaITCDDarVo.setNumeroParcela(StringUtil.toInt(request.getParameter(PARAMETRO_NUMERO_PARCELA_DAR)));
			}
		}
		return giaITCDDarVo;
	}

	/**
	 * Metodo responsável pela vinculação entre  a DAR e a GIAITCD. 
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws IntegracaoException
	 * @implemented by Lucas Nascimento
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void vincularDarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, LogException, PersistenciaException, AnotacaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, IntegracaoException
	{
		GIAITCDDarVo giaITCDDARVo = controladorInterativoJSP(request);
		GIAITCDDarBe giaITCDDARBe = null;
		try
		{
			giaITCDDARBe = new GIAITCDDarBe();
			giaITCDDARBe.vincularManualGiaDAR(giaITCDDARVo);
			getBuffer(request).setAttribute(GIAITCDDAR_VO, giaITCDDARVo, request);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
	   catch (IOException e)
	   {
	      e.printStackTrace();
	      throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
	   }
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCDDAR_VO, giaITCDDARVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_VINCULAR_DAR_GIAITCD, request, response, FORWARD);
		}
	   catch (RegistroNaoPodeSerUtilizadoException e)
	   {
	      getBuffer(request).setAttribute(GIAITCDDAR_VO, giaITCDDARVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_VINCULAR_DAR_GIAITCD, request, response, FORWARD);
	   }		
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(GIAITCDDAR_VO, giaITCDDARVo, request);
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, PARAMETRO_CODIGO_GIA);
			processFlow(VIEW_VINCULAR_DAR_GIAITCD, request, response, FORWARD);
		}
		finally
		{
			if (giaITCDDARBe != null)
			{
				giaITCDDARBe.close();
				giaITCDDARBe = null;
			}
		}
	}	
}
