/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ManterAvaliacao.java
 * Revisão: Leandro Dorileo
 * Data revisão: 
 * $Id: ManterAvaliacao.java,v 1.4 2009/05/05 18:55:44 fernanda.silva Exp $
 */
package br.gov.mt.sefaz.itc.modulo.generico.avaliacao;

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

import br.gov.mt.sefaz.itc.model.generico.avaliacao.AvaliacaoBemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.AvaliacaoBemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.Form;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.servidor.AvaliacaoBemTributavelServidorVo;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnFuncionalidadeOrigem;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.UnidadeSefazIntegracaoVo;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Esta classe define as atividades/fluxos básicos para as funcionalidades Incluir e
 * Alterar Avaliação.
 *
 * @author Leandro Dorileo
 * @version $Revision: 1.4 $
 */
public abstract class ManterAvaliacao extends AbstractAbacoServlet implements Flow, Form
{
	private static final int REQUISICAO_SOLICITAR_DETALHAR_BEM_GIAITCD = 4;
	private static final int REQUISICAO_SOLICITAR_INCLUIR_AVALIACAO_BEM = 5;
	private static final int REQUISICAO_INCLUIR_AVALIACAO_BEM = 6;
	private static final int REQUISICAO_SOLICITAR_INCLUIR_SERVIDOR = 7;
	private static final int REQUISICAO_INCLUIR_SERVIDOR = 8;
	private static final int REQUISICAO_EXCLUIR_SERVIDOR = 9;
	private static final int REQUISICAO_SALVAR_DADOS_GERAIS = 10;
	private static final int REQUISICAO_SOLICITAR_ALTERAR_AVALIACAO_BEM = 11;
	private static final int REQUISICAO_CONSULTAR_VALOR_AVALIACAO = 12;
	private static final int REQUISICAO_SALVAR_DADOS_GERAIS_CONFIRMACAO = 13;	
	private static final int REQUISICAO_ISENTAR_BEM_AVALIADO = 14;
   private static final int REQUISICAO_AVALIACAO_EXCLUIDA = 15;
   private static final int REQUISICAO_REABRIR_AVALIACAO = 16;
	protected String codigoPesquisarGIAITCD = null;
	protected String codigoPesquisarGestaoPessoas = null;

	/**Metodo utilizado para processar a requisição.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Elizabeth Barbosa Moreira
	 */
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, IntegracaoException, ConexaoException, 
			  ConsultaException, LogException, PersistenciaException, AnotacaoException, RegistroExistenteException
   {
		configuraPermissao();
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarPesquisarGIAITCD(request, response); // inicia a pesquisa de gia
					break;
				}
			case REQUISICAO_SOLICITAR_DETALHAR_BEM_GIAITCD:
				{
					detalharBemGIAITCD(request, response); // fluxo inicial depois da pesquisa da GIA
					break;
				}
			case REQUISICAO_SOLICITAR_INCLUIR_AVALIACAO_BEM:
				{ // direciona para a inclusão de avaliação de um bem
					solicitarIncluirAvalicaoBem(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_INCLUIR_SERVIDOR:
				{
					solicitarIncluirServidor(request, response); // tela de pesquisa de servidores
					break;
				}
			case REQUISICAO_INCLUIR_SERVIDOR:
				{
					incluirServidor(request, response); // atribui o servidor selecionado
					break;
				}
			case REQUISICAO_INCLUIR_AVALIACAO_BEM:
				{
					incluirAvaliacaoBem(request, response); // processa o final da inclusão da avaliação
					break;
				}
			case REQUISICAO_SOLICITAR_ALTERAR_AVALIACAO_BEM:
				{
					solicitarAlterarAvaliacaoBem(request, response); // redireciona para a tela de alteração da avaliação de um bem
					break;
				}
			case REQUISICAO_EXCLUIR_SERVIDOR:
				{
					excluirServidor(request, response); // o usuário quer remover um servidor
					break;
				}
			case REQUISICAO_SALVAR_DADOS_GERAIS:
				{
					salvarDadosGerais(request, response);
					break;
				}
		   case REQUISICAO_SALVAR_DADOS_GERAIS_CONFIRMACAO:
		      {
		         salvarDadosGeraisConfirmacao(request, response);
		         break;
		      }
			case REQUISICAO_ISENTAR_BEM_AVALIADO:
				{
					isentarAvaliacaoBem(request, response);
				   break;
				}
         case REQUISICAO_REABRIR_AVALIACAO:
            {
               reabrirAvalicao(request, response);
               break;
            }
		   case REQUISICAO_AVALIACAO_EXCLUIDA:
		      {
               excluirAvaliacao(request, response);
		         break;
		      }
		}
	}

	/**Metodo utilizado por redirenciona as funcionalidades.
	 * 
	 * @param request
	 * @return int
	 * @implemented by Elizabeth Barbosa
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR)))
		{
			return REQUISICAO_SOLICITAR_DETALHAR_BEM_GIAITCD;
		}
		else if(Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_GIASITCD)))
		{
			return REQUISICAO_SOLICITAR_DETALHAR_BEM_GIAITCD;			
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_INCLUIR_AVALIACAO_BEM)))
		{
			return REQUISICAO_SOLICITAR_INCLUIR_AVALIACAO_BEM;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_INCLUIR_SERVIDOR)))
		{
			return REQUISICAO_SOLICITAR_INCLUIR_SERVIDOR;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_INCLUIR_SERVIDOR)))
		{
			return REQUISICAO_INCLUIR_SERVIDOR;
		}
		else if(Validador.isStringValida(request.getParameter(PARAMETRO_GIA_ITCD_ISENTA)))
		{
			return REQUISICAO_ISENTAR_BEM_AVALIADO;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_INCLUIR)))
		{
			return REQUISICAO_INCLUIR_AVALIACAO_BEM;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_SALVAR_DADOS_GERAIS;
		}
	   if(Validador.isStringValida(request.getParameter(PARAMETRO_CONFIRMACAO_EXCEPTION)))
	   {
	      return REQUISICAO_SALVAR_DADOS_GERAIS_CONFIRMACAO;
	   }		
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ALTERAR_AVALIACAO_BEM)))
		{
			return REQUISICAO_SOLICITAR_ALTERAR_AVALIACAO_BEM;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_EXCLUIR_SERVIDOR)))
		{
			return REQUISICAO_EXCLUIR_SERVIDOR;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_PESQUISAR_VALOR_AVALIACAO)))
		{
			return REQUISICAO_CONSULTAR_VALOR_AVALIACAO;
		}
	   else if (Validador.isStringValida(request.getParameter(BOTAO_EXCLUIR_AVALIACAO)))
	   {
	      return REQUISICAO_AVALIACAO_EXCLUIDA;
	   }	   
      else if (Validador.isStringValida(request.getParameter(BOTAO_REABRIR_AVALIACAO)))
	   {
	      return REQUISICAO_REABRIR_AVALIACAO;
	   }
      
      
		return REQUISICAO_VAZIA;
	}

	/**Controlador interativo, responsavel por preencher o objeto atual
	 * @param request
	 * @throws ObjetoObrigatorioException
	 * @return br.gov.mt.sefaz.cogar.model.garantia.GarantiaVo
	 * @implemented by Elizabeth Barbosa
	 * @implemented Leandro Dorileo
	 */
	protected GIAITCDVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		GIAITCDVo giaItcdVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		AvaliacaoBemTributavelVo avaliacaoBem = (AvaliacaoBemTributavelVo) getBuffer(request).getAttribute(AVALIACAO_BEM_VO);
		BemTributavelVo bemTributavel = (BemTributavelVo) getBuffer(request).getAttribute(BEM_TRIBUTAVEL_VO);
	   Validador.validaObjeto(giaItcdVo);
		// EXISTE UMA GIA?
		if (giaItcdVo != null && avaliacaoBem != null && 
				 !Validador.isStringValida(request.getParameter(PARAMETRO_INCLUIR_SERVIDOR)))
		{
			// foi selecionada a avaliação judicial?
			if (Validador.isStringValida(request.getParameter(CHECKEBOX_AVALIACAO_JUDICIAL)))
			{
				Integer valorSelecionado = new Integer(request.getParameter(CHECKEBOX_AVALIACAO_JUDICIAL));
				avaliacaoBem.setAvaliacaoJudicial(new DomnSimNao(valorSelecionado.intValue()));
			}
			// ISENÇÃO?
			if (Validador.isStringValida(request.getParameter(CHECKEBOX_ISENCAO_PREVISTA_EM_LEI)))
			{
				Integer valorSelecionado = new Integer(request.getParameter(CHECKEBOX_ISENCAO_PREVISTA_EM_LEI));			
				avaliacaoBem.setIsento(new DomnSimNao(valorSelecionado.intValue()));
			}
			// CAMPO VALOR AVALIAÇÃO
			if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_AVALIACAO)))
			{
				double valorAvaliacao = StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_AVALIACAO));
				avaliacaoBem.setValorAvaliacao(valorAvaliacao);
			}
			// CAMPO DATA DA AVALIAÇÃO
			if (Validador.isStringValida(request.getParameter(CAMPO_DATA_AVALIACAO)))
			{
				avaliacaoBem.setDataAvaliacao(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_AVALIACAO)));
			}
			// CAMPO MATRICULA DO SERVIDOR
			if (Validador.isStringValida(request.getParameter(Form.CAMPO_MATRICULA_SERVIDOR)))
			{
				giaItcdVo.getBemTributavelVo().getAvaliacaoBemTributavelVo().getAvaliacaoBemTributavelServidorVo().getServidorSefazVo().setNumrMatricula(new Long(request.getParameter(CAMPO_MATRICULA_SERVIDOR)));
			}
			// CAMPO OBSERVAÇÃO
			if (Validador.isStringValida(request.getParameter(Form.CAMPO_OBSERVACAO)))
			{
				avaliacaoBem.setObservacao(request.getParameter(CAMPO_OBSERVACAO));
			}
		}
	   // CAMPO JUSTIFICATIVA DE ALTERAÇÃO QUANDO FOR EXCLUIR OU REABRIR UMA AVALIAÇÃO
	   if (Validador.isStringValida(request.getParameter(Form.CAMPO_JUSTIFICATIVA_ALTERACAO)))
	   {
	      giaItcdVo.getStatusVo().setJustificativaAlteracao(request.getParameter(Form.CAMPO_JUSTIFICATIVA_ALTERACAO));
	   }
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_UNIDADE_SEFAZ)))
		{
			giaItcdVo.getStatusVo().setCodigoUnidadeAvaliacao(StringUtil.toInt(request.getParameter(CAMPO_SELECT_UNIDADE_SEFAZ)));
		}
		getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavel, request);
		getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacaoBem, request);
		return giaItcdVo;
	}

	/**Metodo para redirecionar para servelet de pesquisar GIAITCD  responsavel por preencher o objeto atual
	 * @param request
	 * @throws ObjetoObrigatorioException
	 * @return br.gov.mt.sefaz.cogar.model.garantia.GarantiaVo
	 * @implemented by Elizabeth Barbosa
	 * @implemented by Leandro Dorileo
	 */
	private void solicitarPesquisarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
	   GIAITCDVo giaITCDVo = new GIAITCDVo();
	   obterInformacoesLogSefaz(request, giaITCDVo);
		if(codigoPesquisarGIAITCD == FormAcesso.CODIGO_INCLUIR_AVALIACAO_GIAITCD_PESQUISAR_GIAITCD)
		{
			giaITCDVo.setOrigem(DomnFuncionalidadeOrigem.AVALIAR_GIA_ITCD);	
		}
		else
		{
		   giaITCDVo.setOrigem(DomnFuncionalidadeOrigem.ALTERAR_AVALIACAO_GIA_ITCD);
		}	   
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
	   request.setAttribute(PARAMETRO_CONSULTAR_AVALIAR, PARAMETRO_CONSULTAR_AVALIAR);   
		getBuffer(request).setAttribute(AVALIACAO_BEM_VO, null, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, null, request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(codigoPesquisarGIAITCD, request), request, response, FORWARD);
	}

	/**
	 * Este método configura incialmente a avaliação de cada bem tributável com a isenção cadastrada
	 * no bem tributável durante o cadastro da gia
	 *
	 * @param gia
	 * @implemented by Leandro Dorileo
	 */
	protected void processaBensTributaveis(GIAITCDVo gia)
	{
		BemTributavelVo atual = null;
		for (Iterator it = gia.getBemTributavelVo().getCollVO().iterator(); it.hasNext(); )
		{
			atual = (BemTributavelVo) it.next();
			atual.getAvaliacaoBemTributavelVo().setIsento(atual.getIsencaoPrevista());
			atual.setBemPassivelAvaliacao(GIAITCDBe.isBemPassivelAvaliacao(gia, atual));
		}
	}

	/**
	 * Este método encontra em uma gia um bem tributável que esteja configurado com um determinado
	 * código.
	 *
	 * @param giaItcdVo              gia a ser percorrida/analisada
	 * @param codigoBemSelecionado   código a ser comparado
	 * @return                       bem tributável requerido
	 * @implemented by Leandro Dorileo
	 */
	protected BemTributavelVo findBemTributavel(GIAITCDVo giaItcdVo, long codigoBemSelecionado)
	{
		BemTributavelVo retorno = null;
		for (Iterator it = giaItcdVo.getBemTributavelVo().getCollVO().iterator(); it.hasNext(); )
		{
			BemTributavelVo atual = (BemTributavelVo) it.next();
			if (atual.getCodigo() == codigoBemSelecionado)
			{
				retorno = atual;
				break;
			}
		}
		return retorno;
	}

	/**Método utilizado para solicitar incluir Avaliacao Bem.
	 * 
	 * @param request
	 * @param response
	 * @implemented by Elizabeth Barbosa
	 * @implemented by Leandro Dorileo
	 */
	private void solicitarIncluirAvalicaoBem(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException
	{
		GIAITCDVo giaItcdVo = controladorInterativoJSP(request);
		int codigoBemSelecionado = StringUtil.toInt(request.getParameter(PARAMETRO_SOLICITAR_INCLUIR_AVALIACAO_BEM));
		BemTributavelVo bemTributavelVo = null;
		AvaliacaoBemTributavelVo avaliacao = new AvaliacaoBemTributavelVo();
		bemTributavelVo = findBemTributavel(giaItcdVo, codigoBemSelecionado);
		if (bemTributavelVo.getIsencaoPrevista().getDomnValr() == DomnSimNao.SIM)
		{
			avaliacao.setValorAvaliacao(bemTributavelVo.getValorMercado());
			avaliacao.setIsento(bemTributavelVo.getIsencaoPrevista());
			avaliacao.setAvaliacaoIsenta(true);
		}
		else
		{
			avaliacao.setValorAvaliacao(0);
			avaliacao.setIsento(bemTributavelVo.getIsencaoPrevista());
		}			
		avaliacao.setAvaliacaoJudicial(new DomnSimNao(DomnSimNao.NAO));
		avaliacao.setDataCadastro(new Date());
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacao, request);
		processFlow(VIEW_INCLUIR_AVALIACAO_BEM, request, response, FORWARD);
	}

	/**Método utilizado para solicitar incluir Servidor.
	 * 
	 * @param request
	 * @param response
	 * @implemented by Elizabeth Barbosa
	 * @implemented by Leandro Dorileo
	 */
	private void solicitarIncluirServidor(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException
	{
		controladorInterativoJSP(request);
		processFlow(VIEW_AVALIACAO_PESQUISAR_SERVIDOR, request, response, FORWARD);
	}

	/**Método utilizado para incluir Servidor.
	 * 
	 * @param request
	 * @param response
	 * @implemented by Elizabeth Barbosa
	 * @implemented by Leandro Dorileo
	 */
	private void incluirServidor(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
	   GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
	   AvaliacaoBemTributavelVo avaliacao = (AvaliacaoBemTributavelVo) getBuffer(request).getAttribute(AVALIACAO_BEM_VO);
		Validador.validaObjeto(giaITCDVo);
		Validador.validaObjeto(avaliacao);
		//controladorInterativoJSP(request);
		String parmMatServidor = request.getParameter(Form.CAMPO_MATRICULA_SERVIDOR);
		Long matriculaServidor = null;
		ServidorSefazIntegracaoVo servidorVo = null;
		AvaliacaoBemTributavelServidorVo avaliacaoServidor = null;
		if (Validador.isStringValida(parmMatServidor))
		{
			servidorVo = new ServidorSefazIntegracaoVo();
			matriculaServidor = new Long(parmMatServidor);
			servidorVo.setNumrMatricula(matriculaServidor);
			/** integra com o sistema sgp pra obter o restante dos dados */
			request.setAttribute(SERVIDOR_VO, servidorVo);
			processFlow(FormAcesso.getUrlServletOriginalSemContexto(codigoPesquisarGestaoPessoas, request), request, response, INCLUDE);
			servidorVo = 	(ServidorSefazIntegracaoVo) request.getAttribute(SERVIDOR_VO); // reatribui com o resultado da consulta
			/** fim da integracao */
			if (request.getAttribute(EXCEPTION) != null)
			{
				processFlow(VIEW_AVALIACAO_PESQUISAR_SERVIDOR, request, response, FORWARD);
				return;
			}
			avaliacaoServidor = new AvaliacaoBemTributavelServidorVo(servidorVo);
		}
		//verifica se a GIA – ITCD esta estipulada para ser avaliada pela “AGENFA DE PROTOCOLO”
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			giaITCDBe.validarIncluirServidorAvaliacao(giaITCDVo, servidorVo);			
		}
	   catch (ConexaoException e)
	   {
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_AVALIACAO_PESQUISAR_SERVIDOR, request, response, FORWARD);
			return;
	   }
	   catch (SQLException e)
	   {
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_AVALIACAO_PESQUISAR_SERVIDOR, request, response, FORWARD);
	      return;
	   }
	   catch (IntegracaoException e)
	   {
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_AVALIACAO_PESQUISAR_SERVIDOR, request, response, FORWARD);
	      return;
	   }
	   catch (RegistroNaoPodeSerUtilizadoException e)
	   {
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_AVALIACAO_PESQUISAR_SERVIDOR, request, response, FORWARD);
	      return;
	   }
	   finally
	   {
	      if (giaITCDBe != null)
	      {
	         giaITCDBe.close();
	         giaITCDBe = null;
	      }
	   }
		
		// Adicionar no CollVO do AvaliacaoBemTributavelServidorVo
		boolean servidorJaCadastrado = false;
		for (Iterator it = avaliacao.getAvaliacaoBemTributavelServidorVo().getCollVO().iterator(); it.hasNext(); )
		{
			AvaliacaoBemTributavelServidorVo atual = (AvaliacaoBemTributavelServidorVo) it.next();
			if (atual.getServidorSefazVo().getNumrMatricula().equals(servidorVo.getNumrMatricula()))
			{
				servidorJaCadastrado = true;
			}
		}
		if (servidorJaCadastrado)
		{
			request.setAttribute(EXCEPTION, new ParametroObrigatorioException("Servidor Cadastrado nessa avaliação."));
			processFlow(VIEW_AVALIACAO_PESQUISAR_SERVIDOR, request, response, FORWARD);
		}
		else
		{
			avaliacao.getAvaliacaoBemTributavelServidorVo().getCollVO().add(avaliacaoServidor);
			processFlow(VIEW_INCLUIR_AVALIACAO_BEM, request, response, FORWARD);
		}
	}

	/**Método utilizado para excluir Servidor.
	 * 
	 * @param request
	 * @param response
	 * @implemented by Elizabeth Barbosa
	 * @implemented by Leandro Dorileo
	 */
	private void excluirServidor(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		controladorInterativoJSP(request);
		AvaliacaoBemTributavelVo avaliacao = (AvaliacaoBemTributavelVo) getBuffer(request).getAttribute(AVALIACAO_BEM_VO);
		int indice = StringUtil.toInt(request.getParameter(PARAMETRO_SOLICITAR_EXCLUIR_SERVIDOR));
		((ArrayList) avaliacao.getAvaliacaoBemTributavelServidorVo().getCollVO()).remove(indice);
		processFlow(VIEW_INCLUIR_AVALIACAO_BEM, request, response, FORWARD);
	}

	/**Método utilizado para incluir Avaliacao Bem.
	 * 
	 * @param request
	 * @param response
	 * @implemented by Leandro Dorileo
	 */
	private void incluirAvaliacaoBem(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException
	{
	   GIAITCDBe giaBe = null;
		try
		{
			GIAITCDVo giaItcdVo = controladorInterativoJSP(request);
			AvaliacaoBemTributavelVo avaliacaoBem = (AvaliacaoBemTributavelVo) getBuffer(request).getAttribute(AVALIACAO_BEM_VO);
			BemTributavelVo bemTributavelVo = (BemTributavelVo) getBuffer(request).getAttribute(BEM_TRIBUTAVEL_VO);
			if (Validador.isStringValida(request.getParameter(BOTAO_INCLUIR)))
			{
				try
				{
					giaBe = new GIAITCDBe();
					bemTributavelVo.setAvaliacaoBemTributavelVo(avaliacaoBem);
					Date dataPrazo = giaBe.obtemPrazoAvaliacaoJudicial(giaItcdVo.getStatusVo().getDataProtocolo(), giaItcdVo.getMunicipioProtocolar().getCodgMunicipio().intValue());
					//codigo do LOG
						obterInformacoesLogSefaz(request, bemTributavelVo);
					//fim do código do LOG
					giaBe.validaAvaliacaoBemTributavel(bemTributavelVo, dataPrazo);
					if (avaliacaoBem.getAvaliacaoBemTributavelServidorVo().getCollVO() != null)
					{
						boolean existeServidor = false;
						for (Iterator it = avaliacaoBem.getAvaliacaoBemTributavelServidorVo().getCollVO().iterator(); it.hasNext(); )
						{
							AvaliacaoBemTributavelServidorVo atual = (AvaliacaoBemTributavelServidorVo) it.next();
							giaBe.validaAvaliacaoBemTributavelServidorVo(atual);
							existeServidor = true;
						}
						if (!existeServidor)
						{
							throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_SERVIDOR_OBRIGATORIO);
						}
					}
					Collection listaBem = new ArrayList();
					listaBem.add(bemTributavelVo);
					if (giaItcdVo.getBemTributavelVo().getCollVO() != null)
					{
						for (Iterator it = giaItcdVo.getBemTributavelVo().getCollVO().iterator(); it.hasNext(); )
						{
							BemTributavelVo atual = (BemTributavelVo) it.next();
							if (atual.getCodigo() != bemTributavelVo.getCodigo())
							{
								listaBem.add(atual);
							}
						}
					}
					giaItcdVo.getBemTributavelVo().setCollVO(listaBem);
					listaUnidadeSefaz(request, giaItcdVo);
				}
				catch (ConexaoException e)
				{
					request.setAttribute(EXCEPTION, e);
					processFlow(VIEW_INCLUIR_AVALIACAO_BEM, request, response, FORWARD);
				}
				catch (ConsultaException e)
				{
					request.setAttribute(EXCEPTION, e);
					processFlow(VIEW_INCLUIR_AVALIACAO_BEM, request, response, FORWARD);
				}
				catch (ParametroObrigatorioException e)
				{
					bemTributavelVo.setAvaliacaoBemTributavelVo(new AvaliacaoBemTributavelVo());
					throw e;
				}
				catch (SQLException e)
				{
					request.setAttribute(EXCEPTION, e);
					processFlow(VIEW_INCLUIR_AVALIACAO_BEM, request, response, FORWARD);
				}
				catch (IntegracaoException e)
				{
					request.setAttribute(EXCEPTION, e);
					processFlow(VIEW_INCLUIR_AVALIACAO_BEM, request, response, FORWARD);
				}
				catch (RegistroNaoPodeSerUtilizadoException e)
				{
					request.setAttribute(EXCEPTION, e);
					processFlow(VIEW_INCLUIR_AVALIACAO_BEM, request, response, FORWARD);
				}
			}
			processFlow(VIEW_AVALIACAO_DETALHAR_BEM_GIAITCD, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_AVALIACAO_BEM, request, response, FORWARD);
		}
	   finally
	   {
	      if (giaBe != null)
	      {
	         giaBe.close();
	         giaBe = null;
	      }
	   }
	}

	/**Método utilizado para solicitar alterar Avaliação de um Bem.
	 * 
	 * @param request
	 * @param response
	 * @implemented by Elizabeth Barbosa
	 * @implemented by Leandro Dorileo
	 */
	private void solicitarAlterarAvaliacaoBem(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		controladorInterativoJSP(request);
		GIAITCDVo giaItcdVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		int codigoBemSelecionado = StringUtil.toInt(request.getParameter(PARAMETRO_SOLICITAR_ALTERAR_AVALIACAO_BEM));
		BemTributavelVo bemTributavelVo = findBemTributavel(giaItcdVo, codigoBemSelecionado);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);	
      getBuffer(request).setAttribute(AVALIACAO_BEM_VO, bemTributavelVo.getAvaliacaoBemTributavelVo(), request);
      
      // Caso seja pressionado o botão BOTAO_EXCLUIR_AVALIACAO ou BOTAO_REABRIR_AVALIACAO retorna a página de detalhamento dos bens.
      // se não retorna a página para inclusão da avaliação (Manipulada para permitir a alteração da avaliação).
      if( giaItcdVo.isReabrirAvaliacao() || giaItcdVo.isExcluirAvaliacao() )
      {
         processFlow(VIEW_DETALHAR_AVALIACAO_BEM, request, response, FORWARD);
         //processFlow( VIEW_DETALHAMENTO_LOG , request, response, FORWARD); 
      }else
      {
         processFlow(VIEW_INCLUIR_AVALIACAO_BEM, request, response, FORWARD);
      }
		
	  
	}

	/**
	 * Configura no buffer uma lista de unidades sefaz.
	 *
	 * @param request
	 * @throws IntegracaoException
	 */
	protected void listaUnidadeSefaz(HttpServletRequest request, GIAITCDVo giaITCDVo) throws IntegracaoException
	{
		AvaliacaoBemTributavelBe avaliacaoBemTributavelBe = null;
		try
		{
		   avaliacaoBemTributavelBe = new AvaliacaoBemTributavelBe();
		   avaliacaoBemTributavelBe.listarUnidadeSefazAtivaParaAvaliacao(giaITCDVo);
			getBuffer(request).setAttribute(LISTA_UNIDADE_SEFAZ, giaITCDVo.getAgenciaProtocolo().getCollVO(), request);
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
	   finally
	   {
	      if (avaliacaoBemTributavelBe != null)
	      {
	         avaliacaoBemTributavelBe.close();
	         avaliacaoBemTributavelBe = null;
	      }
	   }
	}

	/**
	 * Configura todas as avaliações com o código da unidade escolhida pelo usuario.
	 *
	 * @param gia
	 * @param codgUnidade
	 */
	protected void ajustaUnidadeAvaliacao(GIAITCDVo gia, Integer codgUnidade) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(gia);
		Validador.validaObjeto(codgUnidade);
		Validador.validaObjeto(gia.getBemTributavelVo().getCollVO());
		for (Iterator it = gia.getBemTributavelVo().getCollVO().iterator(); it.hasNext(); )
		{
			BemTributavelVo atual = (BemTributavelVo) it.next();
			atual.getAvaliacaoBemTributavelVo().setListaAgenfa(new UnidadeSefazIntegracaoVo(codgUnidade));
		}
	}

	/**
	 * Método utilizado para salvar Dados Gerais.
	 * 
	 * @param request
	 * @param response
	 * @implemented by Elizabeth Barbosa
	 * @implemented by Leandro Dorileo
	 */
	protected abstract void salvarDadosGerais(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException, IntegracaoException, LogException, PersistenciaException, AnotacaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException,RegistroExistenteException;
			  
	protected abstract void salvarDadosGeraisConfirmacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException, IntegracaoException, LogException, PersistenciaException, AnotacaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException,RegistroExistenteException;

	/**
	 * Este método valida os dados básicos e obrigatórios para a inclusão e alteração das avaliações
	 * dos bens tributáveis.
	 *
	 * @param giaItcdVo        dados consultados da GIA
	 * @param request          requisição http
	 * @param response         resposta http
	 * @return
	 * @implemented by Leandro Dorileo
	 */
	protected abstract boolean validaAvaliacao(GIAITCDVo giaItcdVo, HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
			  ObjetoObrigatorioException, ConsultaException, IntegracaoException;

	/**
	 * Este método configura as permissões para os fluxos de pesquisar GIAITCD e de
	 * pesquisar Servidor no sistema de Gestão de Pessoas.
	 */
	protected abstract void configuraPermissao();

	/**Método utilizado para detalhar Bem GIAITCD.
	 * 
	 * @param request
	 * @param response
	 * @implemented by Leandro Dorileo
	 */
	protected abstract void detalharBemGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException;

	private void isentarAvaliacaoBem(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		controladorInterativoJSP(request);
		BemTributavelVo bemTributavelVo = (BemTributavelVo)getBuffer(request).getAttribute(BEM_TRIBUTAVEL_VO);
		AvaliacaoBemTributavelVo avaliacao = (AvaliacaoBemTributavelVo) getBuffer(request).getAttribute(AVALIACAO_BEM_VO);		
		if(avaliacao.getIsento().is(DomnSimNao.SIM) && avaliacao.getIsento().is(bemTributavelVo.getIsencaoPrevista().getValorCorrente()))
		{
			avaliacao.setAvaliacaoIsenta(true);
			avaliacao.setValorAvaliacao(bemTributavelVo.getValorMercado());
		}
		else
		{
		   avaliacao.setAvaliacaoIsenta(false);
		   avaliacao.setValorAvaliacao(0);			
		}		
	   getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
	   getBuffer(request).setAttribute(AVALIACAO_BEM_VO, avaliacao, request);
	   processFlow(VIEW_INCLUIR_AVALIACAO_BEM, request, response, FORWARD);		
	}
   
   /**
    * 
    * 
    * 
    * 
    * 
    * 
    * 
    * @param request
    * @param response
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ConsultaException
    * @throws RegistroExistenteException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws PaginaNaoEncontradaException
    * @throws TipoRedirecionamentoInvalidoException
    */
  protected abstract void excluirAvaliacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
                                                                                                 ConexaoException, 
                                                                                                 IntegracaoException, 
                                                                                                 ConsultaException, 
                                                                                                 RegistroExistenteException, 
                                                                                                 LogException, 
                                                                                                 PersistenciaException, 
                                                                                                 AnotacaoException, 
                                                                                                 PaginaNaoEncontradaException, 
                                                                                                 TipoRedirecionamentoInvalidoException;
   
   
   
   /**
    * 
    * 
    * 
    * 
    */
   protected void reabrirAvalicao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
           ConexaoException, IntegracaoException, ConsultaException, RegistroExistenteException, LogException, 
           PersistenciaException, AnotacaoException, PaginaNaoEncontradaException, 
           TipoRedirecionamentoInvalidoException
   {
      GIAITCDBe giaITCDBe = null;
      GIAITCDVo giaItcdVo = null;
      try
      {
         giaItcdVo = controladorInterativoJSP(request);
         ajustaUnidadeAvaliacao(giaItcdVo, new Integer(giaItcdVo.getStatusVo().getCodigoUnidadeAvaliacao()));
         giaITCDBe = new GIAITCDBe();
         //codigo do LOG
            obterInformacoesLogSefaz(request, giaItcdVo);
         //fim do código do LOG
         //ajustaAvaliadores(giaItcdVo);       
         giaITCDBe.reabrirAvaliacao(giaItcdVo);
         //giaITCDBe.alterarStatusParaAvaliado(giaItcdVo);      
         request.setAttribute(ENTIDADE_VO, giaItcdVo);
         processFlow(VIEW_SUCESSO, request, response, FORWARD);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      catch (ParametroObrigatorioException e)
      {
         e.printStackTrace();
         request.setAttribute(EXCEPTION, e);
         request.setAttribute(GIAITCD_VO, giaItcdVo);
         listaUnidadeSefaz(request,giaItcdVo);
         processFlow(VIEW_AVALIACAO_DETALHAR_BEM_GIAITCD, request, response, FORWARD);
      }
      catch (RegistroNaoPodeSerUtilizadoException e)
      {
         e.printStackTrace();
         request.setAttribute(EXCEPTION, e);
         request.setAttribute(GIAITCD_VO, giaItcdVo);
         listaUnidadeSefaz(request,giaItcdVo);
         processFlow(VIEW_CONSULTAR_GIAITCD, request, response, FORWARD);
      }
      catch (IOException e)
      {
         giaItcdVo.setMensagem(e.getMessage());
         request.setAttribute(ENTIDADE_VO, giaItcdVo);
         processFlow(VIEW_ERRO, request, response, FORWARD);
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
