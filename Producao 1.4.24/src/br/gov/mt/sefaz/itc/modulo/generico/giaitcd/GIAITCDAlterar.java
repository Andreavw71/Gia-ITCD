package br.gov.mt.sefaz.itc.modulo.generico.giaitcd;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.Form;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnFuncionalidadeOrigem;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.3 $
 */
public class GIAITCDAlterar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_CONSULTAR_GIAITCD = 2;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarPesquisarGIAITCD(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_GIAITCD:
				{
					verificaTipoGIAITCD(request, response);
					break;
				}
		}
	}

	protected int redirecionar(HttpServletRequest request)
	{
		if (request.getAttribute(GIAITCD_VO) != null)
		{
			return REQUISICAO_CONSULTAR_GIAITCD;
		}
		return REQUISICAO_VAZIA;
	}

	private void solicitarPesquisarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException
	{
		removeBuffer(request);
		GIAITCDVo giaITCDVo = new GIAITCDVo();
		obterInformacoesLogSefaz(request, giaITCDVo);
		giaITCDVo.setOrigem(DomnFuncionalidadeOrigem.ALTERAR_GIA_ITCD);
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		request.setAttribute(PARAMETRO_CONSULTAR_ALTERAR, PARAMETRO_CONSULTAR_ALTERAR);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_PESQUISAR_GIAITCD, request), request, response, FORWARD);
	}

	private void alterarContribuinteTipoGiaArrolamento(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
	   if(Validador.isStringValida( getNumeroMatriculaUsuarioLogado(request)))
	   {
         processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_SERVIDOR, request), request, response, FORWARD);
      }else
      {
         processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO, request), request, response, FORWARD);
      }
         
	
	}

	private void alterarContribuinteTipoGiaDoacao(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
      if(Validador.isStringValida( getNumeroMatriculaUsuarioLogado(request)))
      {
         processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_DOACAO_SERVIDOR, request), request, response, FORWARD);
      }else
      {
         processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_DOACAO, request), request, response, FORWARD);
      }
		
	}

	private void alterarContribuinteTipoGiaSeparacaoDivorcio(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
	   if(Validador.isStringValida( getNumeroMatriculaUsuarioLogado(request)))
	   {
         processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_SEPARACAO_DIVORCIO_SERVIDOR, request), request, response, FORWARD);     
      }else
      {
         processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_SEPARACAO_DIVORCIO, request), request, response, FORWARD);      
      }
        
	}

	private void verificaTipoGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == 
				 (DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
		{
			alterarContribuinteTipoGiaArrolamento(request, response);
		}
		if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == (DomnTipoProcesso.DOACAO))
		{
			alterarContribuinteTipoGiaDoacao(request, response);
		}
		if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == 
				 (DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
		{
			alterarContribuinteTipoGiaSeparacaoDivorcio(request, response);
		}
	}

	/**
	 *  Método que recupera do request os dados informados pelo usuário
	 * @param request
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
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
}
