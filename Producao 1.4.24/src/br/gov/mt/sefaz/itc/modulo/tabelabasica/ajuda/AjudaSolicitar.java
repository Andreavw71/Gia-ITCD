/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AjudaSolicitar.java
 * Revisão: 
 * Data revisão: 
 * Implementação: Wendell Pereira de Farias
 * Adaptações: Janeiro de 2008 / Wendell Farias 
 * Data novas implementação: 23/01/2008
 */
package br.gov.mt.sefaz.itc.modulo.tabelabasica.ajuda;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.campoajuda.CampoAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda.TelaCampoAjudaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda.TelaCampoAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telafuncionalidade.TelaFuncionalidadeBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telafuncionalidade.TelaFuncionalidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.SQLException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet responsável por controlar a exibição do Ajuda.
 * @author Wendell Pereira de Farias
 * @version $Revision: 1.2 $
 */
public class AjudaSolicitar extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_SOLICITAR_AJUDA = 2;
	private static final int REQUISICAO_SOLICITAR_ANTERIOR = 3;
	private static final int REQUISICAO_SOLICITAR_PROXIMO = 4;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.     * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws SQLException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @implemented by Wendell Pereira de Farias
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, SQLException, ObjetoObrigatorioException, ConsultaException, 
			  ConexaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
               listarTelaFuncionalidadeAjuda(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_AJUDA:
				{
					listarTelaFuncionalidadeAjuda(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ANTERIOR:
				{
					solicitaTelaFuncionalidadeAjudaAnterior(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_PROXIMO:
				{
					solicitaTelaFuncionalidadeAjudaProximo(request, response);
					break;
				}
		}
	}

	/**
	 * Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request
	 * @return
	 * @implemented by Wendell Pereira de Farias
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_AJUDA)))
		{
			return REQUISICAO_SOLICITAR_AJUDA;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_ANTERIOR_CLONE)))
		{
			return REQUISICAO_SOLICITAR_ANTERIOR;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_PROXIMO_CLONE)))
		{
			return REQUISICAO_SOLICITAR_PROXIMO;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Pesquisar e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request
	 * @throws ObjetoObrigatorioException
	 * @return
	 * @implemented by Wendell Pereira de Farias
	 */
	private TelaFuncionalidadeVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo =  (TelaFuncionalidadeVo) getBuffer(request).getAttribute(TELA_FUNCIONALIDADE_VO);
		if (telaFuncionalidadeVo == null)
		{
			telaFuncionalidadeVo = new TelaFuncionalidadeVo();
		}		
                int quantidadeObjetos = 0;
		if ((telaFuncionalidadeVo.getCollVO().size()) > 0)
		{
			quantidadeObjetos = telaFuncionalidadeVo.getCollVO().size();
		}                
                if (request.getParameter(PARAMETRO_SOLICITAR_AJUDA) != null)
                {
                        if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_AJUDA)))                            
                        {
                            if (request.getParameter(PARAMETRO_SOLICITAR_AJUDA).length() > 0)
                            {
                                telaFuncionalidadeVo = new TelaFuncionalidadeVo();
                                telaFuncionalidadeVo.setInformacaoTituloTelaFuncionalidade(request.getParameter(PARAMETRO_SOLICITAR_AJUDA));
                                quantidadeObjetos = TelaFuncionalidadeVo.STRING_VAZIA.length();                                
                            }                            
                        }                        
                }            		
		//Posição Atual da coleção objetos.
		if ((quantidadeObjetos < 1) || (telaFuncionalidadeVo.getPosicaoAtualRegistro()< 1))
		{
			telaFuncionalidadeVo.setPosicaoAtualRegistro(0);
		}                
		if (Validador.isStringValida(request.getParameter(BOTAO_ANTERIOR_CLONE)))
		{
			if (telaFuncionalidadeVo.getPosicaoAtualRegistro() > 0) //Se posicaoAtual maior que zero, decrementa a posicao atual. 
			{
				int posicaoAtualRegistro = telaFuncionalidadeVo.getPosicaoAtualRegistro();
				telaFuncionalidadeVo.setPosicaoAtualRegistro(--posicaoAtualRegistro);
			}
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_PROXIMO_CLONE)))
		{
			if (telaFuncionalidadeVo.getPosicaoAtualRegistro() < 
						(quantidadeObjetos-1)) //Se posicaoAtual maior que zero, decrementa a posicao atual. 
			{
				int posicaoAtualRegistro = telaFuncionalidadeVo.getPosicaoAtualRegistro();
				telaFuncionalidadeVo.setPosicaoAtualRegistro(++posicaoAtualRegistro);
			}
			else
			{
				telaFuncionalidadeVo.setPosicaoAtualRegistro(quantidadeObjetos);
			}
		}
		long codigoTelaFuncionalidadeAjuda;
		long codigoTelaAjuda;
		if (quantidadeObjetos > 0)
		{
			codigoTelaFuncionalidadeAjuda = 
							((TelaFuncionalidadeVo) (((List) telaFuncionalidadeVo.getCollVO()).get(telaFuncionalidadeVo.getPosicaoAtualRegistro()))).getCodigo();
			codigoTelaAjuda = 
							((TelaFuncionalidadeVo) (((List) telaFuncionalidadeVo.getCollVO()).get(telaFuncionalidadeVo.getPosicaoAtualRegistro()))).getTelaAjudaVo().getCodigo();
			telaFuncionalidadeVo.setCodigo(codigoTelaFuncionalidadeAjuda);
			telaFuncionalidadeVo.getTelaAjudaVo().setCodigo(codigoTelaAjuda);
		}
		telaFuncionalidadeVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
		return telaFuncionalidadeVo;
	}

	/**
	 * Método responsável por solicitar a listagem de telas da ajuda. 
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @throws ConsultaException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Wendell Pereira de Farias
	 */
	private void listarTelaFuncionalidadeAjuda(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  SQLException, ConsultaException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVoAux = controladorInterativoJSP(request);		
		TelaFuncionalidadeVo telaFuncionalidadeVo = new TelaFuncionalidadeVo(telaFuncionalidadeVoAux);
      telaFuncionalidadeVo.setConsultaCompleta(true);
		TelaFuncionalidadeBe telaFuncionalidadeBe = null;
		TelaCampoAjudaBe telaCampoAjudaBe = null;
		try
		{
			//Retorna a TelaFuncionalidade (Funcionalidade + TelaAjuda)
			telaFuncionalidadeBe = new TelaFuncionalidadeBe();			
			telaFuncionalidadeBe.listarTelaFuncionalidadeUsuario(telaFuncionalidadeVo);
			TelaFuncionalidadeBe.atualizarTelaFuncionalidadeVo(telaFuncionalidadeVo);
			//Retorna TelaCampoAjuda
			TelaCampoAjudaVo telaCampoAjudaVo = new TelaCampoAjudaVo();
			telaCampoAjudaVo.setTelaAjudaVo(telaFuncionalidadeVo.getTelaAjudaVo());
			telaCampoAjudaVo.setCampoAjudaVo(new CampoAjudaVo());
			telaCampoAjudaVo.setParametroConsulta(telaCampoAjudaVo);
			telaCampoAjudaVo.setConsultaCompleta(true);
			telaCampoAjudaBe = new TelaCampoAjudaBe();
			telaCampoAjudaBe.listarTelaCampoAjuda(telaCampoAjudaVo);
			Validador.validaObjeto(telaCampoAjudaVo);
			if (telaFuncionalidadeVo.getCodigo() > 0)
			{
				telaFuncionalidadeVo.getTelaAjudaVo().setTelaCampoAjudaVo(telaCampoAjudaVo);
			}
			if (telaFuncionalidadeVo.getStatusTelaFuncionalidade().is(DomnStatusRegistro.INATIVO))
			{
				throw new ConsultaException("Este módulo de ajuda está inativo.");
			}
		   request.setAttribute(EXISTE, FALSO);
		   getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		   processFlow(VIEW_APRESENTAR_AJUDA, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_AJUDA);
		}
		catch (ConsultaException e)
		{
			telaFuncionalidadeVo.setMensagem(e.getMessage());
		   request.setAttribute(ENTIDADE_VO, telaFuncionalidadeVo);
		   getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		   processFlow(VIEW_ERRO, request, response, FORWARD);
		}
		finally
		{
			if (telaFuncionalidadeBe != null)
			{
				telaFuncionalidadeBe.close();
				telaFuncionalidadeBe = null;
			}
			if (telaCampoAjudaBe != null)
			{
				telaCampoAjudaBe.close();
				telaCampoAjudaBe = null;
			}
		}
	}

	/**
	 *  Funcionalidade responsável por efetuar efeito de paginação ou apresentar objeto anterior.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Wendell Pereira de Farias
	 */
	private void solicitaTelaFuncionalidadeAjudaAnterior(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		telaFuncionalidadeVo.setConsultaCompleta(true);
		telaFuncionalidadeVo.setParametroConsulta(telaFuncionalidadeVo);
		TelaFuncionalidadeBe telaFuncionalidadeBe = null;
		TelaCampoAjudaBe telaCampoAjudaBe = null;
		try
		{
			//Retorna a TelaFuncionalidade (Funcionalidade + TelaAjuda)
			telaFuncionalidadeBe = new TelaFuncionalidadeBe();
			TelaFuncionalidadeBe.atualizarTelaFuncionalidadeVo(telaFuncionalidadeVo);
			//Retorna TelaCampoAjuda                        
			TelaCampoAjudaVo telaCampoAjudaVo = new TelaCampoAjudaVo();
			telaCampoAjudaVo.setTelaAjudaVo(telaFuncionalidadeVo.getTelaAjudaVo());
			telaCampoAjudaVo.setCampoAjudaVo(new CampoAjudaVo());
			telaCampoAjudaVo.setParametroConsulta(telaCampoAjudaVo);
			telaCampoAjudaVo.setConsultaCompleta(true);
			telaCampoAjudaBe = new TelaCampoAjudaBe();
			telaCampoAjudaBe.listarTelaCampoAjuda(telaCampoAjudaVo);
			Validador.validaObjeto(telaCampoAjudaVo);
			if (telaFuncionalidadeVo.getCodigo() > 0)
			{
				telaFuncionalidadeVo.getTelaAjudaVo().setTelaCampoAjudaVo(telaCampoAjudaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_AJUDA);
		}
		finally
		{
			if (telaFuncionalidadeBe != null)
			{
				telaFuncionalidadeBe.close();
				telaFuncionalidadeBe = null;
			}
			if (telaCampoAjudaBe != null)
			{
				telaCampoAjudaBe.close();
				telaCampoAjudaBe = null;
			}
		}
		getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		request.setAttribute(EXISTE, FALSO);
		processFlow(VIEW_APRESENTAR_AJUDA, request, response, FORWARD);
	}

	/**
	 * Funcionalidade responsável por efetuar efeito de paginação ou apresentar objeto proximo.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Wendell Pereira de Farias
	 */
	private void solicitaTelaFuncionalidadeAjudaProximo(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException
	{
		TelaFuncionalidadeVo telaFuncionalidadeVo = controladorInterativoJSP(request);
		telaFuncionalidadeVo.setConsultaCompleta(true);
		telaFuncionalidadeVo.setParametroConsulta(telaFuncionalidadeVo);
		TelaFuncionalidadeBe telaFuncionalidadeBe = null;
		TelaCampoAjudaBe telaCampoAjudaBe = null;
		try
		{
			//Retorna a TelaFuncionalidade (Funcionalidade + TelaAjuda)
			telaFuncionalidadeBe = new TelaFuncionalidadeBe();
			TelaFuncionalidadeBe.atualizarTelaFuncionalidadeVo(telaFuncionalidadeVo);
			//Retorna TelaCampoAjuda
			TelaCampoAjudaVo telaCampoAjudaVo = new TelaCampoAjudaVo();
			telaCampoAjudaVo.setTelaAjudaVo(telaFuncionalidadeVo.getTelaAjudaVo());
			telaCampoAjudaVo.setCampoAjudaVo(new CampoAjudaVo());
			telaCampoAjudaVo.setParametroConsulta(telaCampoAjudaVo);
			telaCampoAjudaVo.setConsultaCompleta(true);
			telaCampoAjudaBe = new TelaCampoAjudaBe();
			telaCampoAjudaBe.listarTelaCampoAjuda(telaCampoAjudaVo);
			Validador.validaObjeto(telaCampoAjudaVo);
			if (telaFuncionalidadeVo.getCodigo() > 0)
			{
				telaFuncionalidadeVo.getTelaAjudaVo().setTelaCampoAjudaVo(telaCampoAjudaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_AJUDA);
		}
		finally
		{
			if (telaFuncionalidadeBe != null)
			{
				telaFuncionalidadeBe.close();
				telaFuncionalidadeBe = null;
			}
			if (telaCampoAjudaBe != null)
			{
				telaCampoAjudaBe.close();
				telaCampoAjudaBe = null;
			}
		}
		getBuffer(request).setAttribute(TELA_FUNCIONALIDADE_VO, telaFuncionalidadeVo, request);
		request.setAttribute(EXISTE, FALSO);
		processFlow(VIEW_APRESENTAR_AJUDA, request, response, FORWARD);
	}
}
