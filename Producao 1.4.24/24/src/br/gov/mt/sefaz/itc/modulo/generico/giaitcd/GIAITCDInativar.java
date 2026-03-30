package br.gov.mt.sefaz.itc.modulo.generico.giaitcd;

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
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnFuncionalidadeOrigem;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet desenvolvida para inativar uma GIA
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.2 $
 */
public class GIAITCDInativar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_SOLICITAR_INATIVAR_GIAITCD = 2;
	private static final int REQUISICAO_INATIVAR_GIAITCD = 3;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, ConexaoException, 
			  IntegracaoException, LogException, PersistenciaException, AnotacaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarPesquisarGIAITCD(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_INATIVAR_GIAITCD:
				{
					solicitarInativarGIAITCD(request, response);
					break;
				}
			case REQUISICAO_INATIVAR_GIAITCD:
				{
					inativarGIAITCD(request, response);
					break;
				}
		}
	}

	/**
	 * Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 *     
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (request.getAttribute(GIAITCD_VO) != null)
		{
			return REQUISICAO_SOLICITAR_INATIVAR_GIAITCD;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_INATIVAR_GIAITCD;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private GIAITCDVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		Validador.validaObjeto(giaITCDVo);
		//int tipoProcesso = retornaTipoProcesso(request);
//		if (tipoProcesso == DomnTipoProcesso.INVENTARIO_ARROLAMENTO)
//		{
//			giaITCDVo = (GIAITCDInventarioArrolamentoVo) getBuffer(request).getAttribute(GIAITCD_VO);
//		}
//		else if (tipoProcesso == DomnTipoProcesso.DOACAO)
//		{
//			giaITCDVo = (GIAITCDDoacaoVo) getBuffer(request).getAttribute(GIAITCD_VO);
//		}
//		else if (tipoProcesso == DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA)
//		{
//			giaITCDVo = (GIAITCDSeparacaoDivorcioVo) getBuffer(request).getAttribute(GIAITCD_VO);
//		}
		if (Validador.isStringValida(request.getParameter(CAMPO_OBSERVACAO)))
		{
			giaITCDVo.getStatusVo().setObservacao(request.getParameter(CAMPO_OBSERVACAO));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DATA_DESISTENCIA)))
		{
			giaITCDVo.getStatusVo().setDataDesistencia(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_DESISTENCIA)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_NUMERO_PROTOCOLO_DESISTENCIA)))
		{
			long numeroProtocolo = StringUtil.toLong(request.getParameter(CAMPO_NUMERO_PROTOCOLO_DESISTENCIA));
			if (Validador.isNumericoValido(numeroProtocolo))
			{
				giaITCDVo.getStatusVo().setProtocoloDesistencia(numeroProtocolo);
			}
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_NUMERO_GIA_SUBSTITUTA)))
		{
			giaITCDVo.getStatusVo().setNumeroGiaSubstituta(StringUtil.toLong(request.getParameter(CAMPO_NUMERO_GIA_SUBSTITUTA)));
		}
		return giaITCDVo;
	}

	/**
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarPesquisarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
	   removeBuffer(request);
	   GIAITCDVo giaITCDVo = new GIAITCDVo();
	   obterInformacoesLogSefaz(request, giaITCDVo);
	   giaITCDVo.setOrigem(DomnFuncionalidadeOrigem.INATIVAR_GIA_ITCD);
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	   request.setAttribute(PARAMETRO_CONSULTAR_INATIVAR, PARAMETRO_CONSULTAR_INATIVAR);	
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_INATIVAR_GIAITCD_PESQUISAR_GIAITCD, request), request, response, FORWARD);
	}

	/**
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarInativarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		processFlow(VIEW_INATIVAR_GIAITCD, request, response, FORWARD);
	}

	/**
	 * Método desenvolvido para retornar o tipo de Processo da natureza da Operacao e identificar o tipo da GIA.
	 * 
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return int
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private int retornaTipoProcesso(HttpServletRequest request)
	{
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		int tipoProcesso = 0;
		if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == 
				 DomnTipoProcesso.INVENTARIO_ARROLAMENTO)
		{
			tipoProcesso = DomnTipoProcesso.INVENTARIO_ARROLAMENTO;
		}
		else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == DomnTipoProcesso.DOACAO)
		{
			tipoProcesso = DomnTipoProcesso.DOACAO;
		}
		else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == 
				 DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA)
		{
			tipoProcesso = DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA;
		}
		return tipoProcesso;
	}

	/**
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void inativarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, LogException, PersistenciaException, 
			  AnotacaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			obterInformacoesLogSefaz(request, giaITCDVo);
			giaITCDBe.inativarGIAITCD(giaITCDVo);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(ENTIDADE_VO, giaITCDVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INATIVAR_GIAITCD, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INATIVAR_GIAITCD, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (RegistroNaoPodeSerUtilizadoException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INATIVAR_GIAITCD, request, response, FORWARD);
		}
      catch (IOException e)
      {
          getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
          request.setAttribute(EXCEPTION, e);
          processFlow(VIEW_INATIVAR_GIAITCD, request, response, FORWARD);
      }
      finally
		{
			if (giaITCDBe != null)
			{
				giaITCDBe.close();
				giaITCDBe = null;
			}
		}
	}
}
