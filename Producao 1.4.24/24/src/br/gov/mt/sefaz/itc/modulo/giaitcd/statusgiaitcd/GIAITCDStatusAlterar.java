/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDStatusAlterar.java
 * Revisão: Leandro Dorileo
 * Data revisão: 17/03/2008
 * $Id: GIAITCDStatusAlterar.java,v 1.5 2009/02/20 18:25:21 ricardo.moraes Exp $
 */
package br.gov.mt.sefaz.itc.modulo.giaitcd.statusgiaitcd;

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

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.Form;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnFuncionalidadeOrigem;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.io.IOException;

import java.sql.SQLException;

import java.text.ParseException;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GIAITCDStatusAlterar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_SOLICITAR_ALTERAR_STATUS_GIAITCD = 2;
	private static final int REQUISICAO_ALTERAR_STATUS_GIAITCD = 3;
	private static final int REQUISICAO_ADICIONAR_DAR = 4;
   private static final int REQUISICAO_EXCLUIR_DAR = 5;

	/**
	 * Processa a requisição e determina a ação a ser tomada
	 * 
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConsultaException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Leandro Dorileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, IntegracaoException, 
			  ConsultaException, LogException, PersistenciaException, AnotacaoException, ParseException
   {
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarConsultarGIAITCD(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ALTERAR_STATUS_GIAITCD:
				{
					solicitarAlterarStatusGIAITCD(request, response);
					break;
				}
			case REQUISICAO_ALTERAR_STATUS_GIAITCD:
				{
					alterarStatusGIAITCD(request, response);
					break;
				}
			case REQUISICAO_ADICIONAR_DAR:
				{
					solicitarAdicionarDar(request, response);
					break;
				}
		   case REQUISICAO_EXCLUIR_DAR:
		      {
		         solicitarExcluirDar(request, response);
		         break;
		      }
		}
	}

	/**
	 * Participa do controle do fluxo da funcionalidade
	 *
	 * @param request
	 * @return
	 * 
	 * @implemented by Elizabeth Barbosa Moreira
	 * @implemented by Leandro Dorileo
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if(request.getAttribute(GIAITCD_VO) != null)
		{
			return REQUISICAO_SOLICITAR_ALTERAR_STATUS_GIAITCD;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_ALTERAR_STATUS_GIAITCD;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_DAR)))
		{
			return REQUISICAO_ADICIONAR_DAR;
		}
	   else if (Validador.isStringValida(request.getParameter(BOTAO_EXCLUIR_DAR)))
	   {
	      return REQUISICAO_EXCLUIR_DAR;
	   }
		return REQUISICAO_VAZIA;
	}
	
	private GIAITCDVo controladorInterativoJSPBuffer(HttpServletRequest request)
	{
	   GIAITCDVo giaItcdVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		if(!Validador.isObjetoValido(giaItcdVo))
		{
		   giaItcdVo = (GIAITCDVo) request.getAttribute(GIAITCD_VO);
		}
		return giaItcdVo;
	}

	/**
	 * @param request
	 * @return GIAITCDVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private GIAITCDVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		GIAITCDVo giaItcdVo = controladorInterativoJSPBuffer(request);
		Validador.validaObjeto(giaItcdVo);
		//giaItcdVo.setCodigo(StringUtil.toLong(request.getParameter(CAMPO_CODIGO_GIA)));
		giaItcdVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(StringUtil.toInt(request.getParameter(CAMPO_SELECT_STATUS))));
		if(giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLADO))
		{
		   giaItcdVo.getStatusVo().setProtocolo(StringUtil.toLong(request.getParameter(CAMPO_NUMERO_PROTOCOLO)));
		   giaItcdVo.getStatusVo().setDataProtocolo(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_PROTOCOLO)));
		}
		else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO_CIENTE))
		{
			giaItcdVo.getStatusVo().setDataCienciaNotificacao(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_CIENCIA_NOTIFICACAO)));
		}
		else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO))
		{
		   giaItcdVo.getStatusVo().setProtocoloParcelamento(StringUtil.toLong(request.getParameter(CAMPO_NUMERO_PROTOCOLO_PARCELAMENTO)));
		   giaItcdVo.getStatusVo().setDataParcelamento(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_PARCELAMENTO))); 
			giaItcdVo.getStatusVo().setJustificativaReparcelamento(request.getParameter(CAMPO_JUSTIFICATIVA_RETORNO_PARCELAMENTO));
		}
		else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_MANUALMENTE))
		{
			giaItcdVo.getStatusVo().setDataQuitacao(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_QUITACAO)));
			giaItcdVo.getStatusVo().setObservacao(request.getParameter(CAMPO_OBSERVACAO));
		   giaItcdVo.getStatusVo().setNumeroParcelas(StringUtil.toInt(request.getParameter(CAMPO_QUANTIDADE_PARCELAS)));
		}
		else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA))
		{
		   giaItcdVo.getStatusVo().setDataEnvioInscricaoDividaAtiva(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_ENVIO_INSCRICAO_DIVIDA_ATIVA)));
			giaItcdVo.getStatusVo().setJustificativaEnvioInscricaoDividaAtiva(request.getParameter(CAMPO_JUSTIFICATIVA_ENVIO_INSCRICAO_DIVIDA_ATIVA));
		}
	   else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA))
	   {
	      giaItcdVo.getStatusVo().setDataEnvioDividaAtiva(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_ENVIO_DIVIDA_ATIVA)));
	      giaItcdVo.getStatusVo().setJustificativaEnvioDividaAtiva(request.getParameter(CAMPO_JUSTIFICATIVA_ENVIO_DIVIDA_ATIVA));
	   }		
	   else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO_CIENTE))
		{
		   giaItcdVo.getStatusVo().setDataCienciaRetificacao(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_CIENCIA_RETIFICACAO)));
		}
		else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.IMPUGNADO))
		{
		   giaItcdVo.getStatusVo().setProtocoloImpugnacao(StringUtil.toLong(request.getParameter(CAMPO_NUMERO_PROTOCOLO_IMPUGNACAO)));
		   giaItcdVo.getStatusVo().setDataImpugnacao(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_IMPUGNACAO))); 
			giaItcdVo.getStatusVo().setMotivoImpugnacao(request.getParameter(CAMPO_MOTIVO_IMPUGNACAO));
		}
		else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO))
		{
		   giaItcdVo.getStatusVo().setDataEmissaoSegundaRetificacao(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_SEGUNDA_RETIFICACAO)));
			giaItcdVo.getStatusVo().setValorImposto(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_IMPOSTO_SEGUNDA_RETIFICACAO)));
		}
	   else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO))
	   {
	      giaItcdVo.getStatusVo().setDataRatificacao(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_RATIFICACAO)));
	      giaItcdVo.getStatusVo().setValorImposto(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_IMPOSTO_RATIFICACAO)));
	   }
	   else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE))
	   {
	      giaItcdVo.getStatusVo().setDataCienciaSegundaRetificacao(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_CIENCIA_SEGUNDA_RETIFICACAO)));
	   }
	   else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO_CIENTE))
	   {
	      giaItcdVo.getStatusVo().setDataCienciaRatificacao(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_CIENCIA_RATIFICACAO)));
	   }
	   else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO))
	   {
	      giaItcdVo.getStatusVo().setObservacao(request.getParameter(CAMPO_OBSERVACAO_ISENTO));
	   }      
	   else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ENVIADO_CCF))
	   {
	      giaItcdVo.getStatusVo().setProtocoloCCF(StringUtil.toLong(request.getParameter(CAMPO_NUMERO_PROTOCOLO_CCF)));
	      giaItcdVo.getStatusVo().setDataEnvioCCF(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_ENVIO_CCF)));
	      giaItcdVo.getStatusVo().setObservacao(request.getParameter(CAMPO_OBSERVACAO_ENVIADO_CCF));
	   }   
	   else if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_CCF))
	   {
	      giaItcdVo.getStatusVo().setObservacao(request.getParameter(CAMPO_OBSERVACAO_QUITADO_CCF));
	   }
      else if(giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_PELA_GIOR))
      {
         giaItcdVo.getStatusVo().setJustificativaAlteracao(request.getParameter(CAMPO_JUSTIFICATIVA_ALTERACAO));
      }
		return giaItcdVo;
	}

	private GIAITCDVo controladorInterativoQuitacaoManualJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		GIAITCDVo giaItcdVo = controladorInterativoJSPBuffer(request);
		Validador.validaObjeto(giaItcdVo);
		giaItcdVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(StringUtil.toInt(request.getParameter(CAMPO_SELECT_STATUS))));
		if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_MANUALMENTE))
		{
			giaItcdVo.getStatusVo().setDataQuitacao(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_QUITACAO)));
			giaItcdVo.getStatusVo().setObservacao(request.getParameter(CAMPO_OBSERVACAO));
			giaItcdVo.getStatusVo().setNumeroParcelas(StringUtil.toInt(request.getParameter(CAMPO_QUANTIDADE_PARCELAS)));
			giaItcdVo.getStatusVo().getGiaITCDDarVo().getDarEmitido().setCodgUofSeqc(StringUtil.toLongWrapper(request.getParameter(CAMPO_NUMERO_DAR_QUITACAO)));
		}
		return giaItcdVo;
	}	

	/**
	 * Este método prepara os dados a serem enviados para a tela de alteração de status
	 * 
	 * @param request
	 * @param response
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Elizabeth Barbosa Moreira
	 * @implemented by Leandro Dorileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarAlterarStatusGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, 
			  IntegracaoException
	{
		GIAITCDVo giaItcdVo = controladorInterativoJSPBuffer(request);
		int status = giaItcdVo.getStatusVo().getStatusGIAITCD().getValorCorrente();
		giaItcdVo.setParametroConsulta(giaItcdVo);
		StatusGIAITCDBe statusBe = null;
		Collection listaStatusPossiveis = null;
		try
		{
			statusBe = new StatusGIAITCDBe();
			//statusBe.validaConsultaAlteracaoStatus(giaItcdVo.getStatusVo());
			giaItcdVo.getStatusAnterior().setStatusGIAITCD(new DomnStatusGIAITCD(giaItcdVo.getStatusVo().getStatusGIAITCD().getValorCorrente()));
			//giaItcdVo.setStatusVo(new StatusGIAITCDVo());
			listaStatusPossiveis = statusBe.obterStatusPossivel(giaItcdVo, getCodigoUsuarioLogado(request));
			if (!Validador.isDominioNumericoValido(giaItcdVo.getGiaConfirmada()))
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_GIA_ITCD_NAO_CONFIRMADA));
				processFlow(VIEW_PESQUISAR_STATUS_GIAITCD, request, response, FORWARD);
				return;
			}
         // Limpar dados do STATUS------------------------       
         limparInformacoesDoStatusVo(giaItcdVo.getStatusVo());
         //-----------------------------------------------
         
			getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
			getBuffer(request).setAttribute(LISTA_STATUS, listaStatusPossiveis, request);
			processFlow(VIEW_ALTERAR_STATUS_GIAITCD, request, response, FORWARD);
		}		
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ConsultaException e)
		{
			DomnStatusGIAITCD dominioStatusGIAITCD = new DomnStatusGIAITCD();
			dominioStatusGIAITCD.setDomnValr(status);
			giaItcdVo.getStatusVo().setStatusGIAITCD(dominioStatusGIAITCD);
			request.setAttribute(GIAITCD_VO, giaItcdVo.getParametroConsulta());
			request.setAttribute(EXCEPTION, e);
		   consultarGIAITCD(request, response);
		}
		catch (ParametroObrigatorioException e)
		{
			DomnStatusGIAITCD dominioStatusGIAITCD = new DomnStatusGIAITCD();
			dominioStatusGIAITCD.setDomnValr(status);
			giaItcdVo.getStatusVo().setStatusGIAITCD(dominioStatusGIAITCD);
			request.setAttribute(GIAITCD_VO, giaItcdVo.getParametroConsulta());
			request.setAttribute(EXCEPTION, e);
		   consultarGIAITCD(request, response);
		}
		finally
		{
			if (statusBe != null)
			{
				statusBe.close();
				statusBe = null;
			}
		}
	}

	/**
	 * Método responsável por alterar Status GIAITCD.
	 * 
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Elizabeth Barbosa Moreira
	 * @implemented by Leandro Dorileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void alterarStatusGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, LogException, PersistenciaException, 
			  AnotacaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ParseException
	{
		GIAITCDVo giaItcdVo = controladorInterativoJSP(request);
		Collection listaStatusPossiveis = (Collection) getBuffer(request).getAttribute(LISTA_STATUS);
		Validador.validaObjeto(giaItcdVo);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			obterInformacoesLogSefaz(request, giaItcdVo);
			giaItcdVo.getStatusVo().getServidor().setNumrMatricula(Long.valueOf(getNumeroMatriculaUsuarioLogado(request)));
         giaITCDBe.verificarGiaQuitacaoCCF(giaItcdVo);
			giaITCDBe.alterarStatusGIAITCD(giaItcdVo);
			giaItcdVo.setMensagem(giaItcdVo.getStatusVo().getMensagem());
			getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
			request.setAttribute(ENTIDADE_VO, giaItcdVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
			getBuffer(request).setAttribute(LISTA_STATUS, listaStatusPossiveis, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ALTERAR_STATUS_GIAITCD, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
			getBuffer(request).setAttribute(LISTA_STATUS, listaStatusPossiveis, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ALTERAR_STATUS_GIAITCD, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (RegistroNaoPodeSerUtilizadoException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
			getBuffer(request).setAttribute(LISTA_STATUS, listaStatusPossiveis, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ALTERAR_STATUS_GIAITCD, request, response, FORWARD);
		}
      catch (IOException e)
      {
          getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
          getBuffer(request).setAttribute(LISTA_STATUS, listaStatusPossiveis, request);
          request.setAttribute(EXCEPTION, e);
          processFlow(VIEW_ALTERAR_STATUS_GIAITCD, request, response, FORWARD);
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

	/**
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void consultarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
	   request.setAttribute(PARAMETRO_CONSULTAR_ALTERAR_STATUS_MANUAL, PARAMETRO_CONSULTAR_ALTERAR_STATUS_MANUAL);   
	   processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_STATUS_PESQUISAR_GIAITCD, request), request, response, FORWARD); 		
	}

	/**
	 * Método responsável por solicitar a consulta da GIA-ITCD.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarConsultarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
	   removeBuffer(request);
	   GIAITCDVo giaITCDVo = new GIAITCDVo();
	   obterInformacoesLogSefaz(request, giaITCDVo);
	   giaITCDVo.setOrigem(DomnFuncionalidadeOrigem.ALTERAR_STATUS_MANUAL);
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	   request.setAttribute(PARAMETRO_CONSULTAR_ALTERAR_STATUS_MANUAL, PARAMETRO_CONSULTAR_ALTERAR_STATUS_MANUAL);   
	   processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_STATUS_PESQUISAR_GIAITCD, request), request, response, FORWARD);	
	}

	/**
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws ConsultaException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarAdicionarDar(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConexaoException, IntegracaoException, ConsultaException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
	   GIAITCDVo giaItcdVo = controladorInterativoQuitacaoManualJSP(request);
	   Collection listaStatusPossiveis = (Collection) getBuffer(request).getAttribute(LISTA_STATUS);
		Validador.validaObjeto(giaItcdVo);
		GIAITCDBe giaITCDBe = null;
		try
		{
		   giaITCDBe = new GIAITCDBe();
			try
			{
			   giaITCDBe.adicionarDarStatusQuitadoManual(giaItcdVo);
			}
			catch(RegistroNaoPodeSerUtilizadoException e)
			{
				request.setAttribute(EXCEPTION, e);
			}
         catch (IOException e)
         {
             request.setAttribute(EXCEPTION, e);
         }
         getBuffer(request).setAttribute(LISTA_STATUS, listaStatusPossiveis, request);
			getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
			processFlow(VIEW_ALTERAR_STATUS_GIAITCD, request, response, FORWARD);
		}
		catch(SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
		finally
		{
			if(giaITCDBe != null)
			{
			   giaITCDBe.close();
			   giaITCDBe = null;
			}
		}
	}
   
   
   /**
    * Excluir um dar da lista de DARs informada.
    * @param request
    * @param response
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ConsultaException
    * @throws PaginaNaoEncontradaException
    * @throws TipoRedirecionamentoInvalidoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void solicitarExcluirDar(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
           ConexaoException, IntegracaoException, ConsultaException, PaginaNaoEncontradaException, 
           TipoRedirecionamentoInvalidoException
   {
      GIAITCDVo giaItcdVo = controladorInterativoQuitacaoManualJSP(request);
      Collection listaStatusPossiveis = (Collection) getBuffer(request).getAttribute(LISTA_STATUS);
      Validador.validaObjeto(giaItcdVo);

          // Cria uma colecao com as mesmas referencias da lista de DAR.
         List<GIAITCDDarVo> dars = (List<GIAITCDDarVo>) giaItcdVo.getStatusVo().getGiaITCDDarVo().getCollVO();
         // Recupera o valor do indice do link excluir referente a um DAR
         int indice = StringUtil.toInt(request.getParameter(BOTAO_EXCLUIR_DAR));
          
         // Remove da colecao o DAR Informado.    
         dars.remove(indice);
   
      getBuffer(request).setAttribute(LISTA_STATUS, listaStatusPossiveis, request);
      getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
      processFlow(VIEW_ALTERAR_STATUS_GIAITCD, request, response, FORWARD);
     
   }
   
   /**
    * 
    * 
    * 
    * 
    * @param statusVO
    */
   private void limparInformacoesDoStatusVo(StatusGIAITCDVo statusVO)
   {  
      statusVO.setObservacao(null);
      
      //statusVO.setJustificativaReativacao(null);
      statusVO.setJustificativaEnvioDividaAtiva(null);
      //statusVO.setJustificativaReparcelamento(null);
      statusVO.setJustificativaEnvioInscricaoDividaAtiva(null);
      //statusVO.setJustificativaAlteracao(null);
      statusVO.setMotivoImpugnacao(null);
      
      statusVO.setNumeroParcelas(0);
      //statusVO.setNumeroDARQuitacao(0);
      //statusVO.setNumeroParticao(0);
      //statusVO.setNumeroDARQuitacao(0);
      //statusVO.setNumeroDARRatificacao(0);
      statusVO.setNumeroDARSegundaRetificacao(0);
      
      statusVO.setProtocoloParcelamento(0);  
      statusVO.setProtocoloImpugnacao(0);
      //statusVO.setProtocoloDesistencia(0);
      //statusVO.setProtocolo(0);
      statusVO.setProtocoloCCF(0);
      
      statusVO.setValorImposto(0);
      statusVO.setStatusGIAITCD(new DomnStatusGIAITCD());
      

      statusVO.setDataCienciaRetificacao(null);
      //statusVO.setDataCienciaSegundaRetificacao(null);
      statusVO.setDataCienciaRatificacao(null);
      //statusVO.setDataCienciaNotificacao(null);
      //statusVO.setDataCadastroAvaliacao(null);
      //statusVO.setDataDesistencia(null);
      //statusVO.setDataEmissaoRetificacao(null);
      statusVO.setDataEmissaoSegundaRetificacao(null);
      statusVO.setDataEnvioCCF(null);
      statusVO.setDataEnvioDividaAtiva(null);
      //statusVO.setDataEnvioInscricaoDividaAtiva(null);
      statusVO.setDataImpugnacao(null);
      //statusVO.setDataNotificacao(null);
      statusVO.setDataParcelamento(null);
      //statusVO.setDataPermissao(null);
      //statusVO.setDataProtocolo(null);
      //statusVO.setDataQuitacao(null);
      statusVO.setDataRatificacao(null);   

   }
   
}
