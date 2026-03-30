/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AvaliacaoAlterar.java
 * Revisão:
 * Data revisão:
 * Implementação: Elizabeth Barbosa Moreira
 * Adaptações: Janeiro de 2008 / Elizabeth Barbosa Moreira
 * Data novas implementação:
 */
package br.gov.mt.sefaz.itc.modulo.generico.avaliacao;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;
import br.com.abaco.util.exceptions.SolicitaConfirmacaoException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;

import br.gov.mt.sefaz.itc.model.generico.avaliacao.servidor.AvaliacaoBemTributavelServidorVo;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;

import java.io.IOException;

import java.sql.SQLException;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**Servlet de Controle de Fluxo da funcionalidade Icluir Avaliação.
 * @author Elizabeth Barbosa Moreira
 * @version $Revision:
 */
public class AvaliacaoAlterar extends ManterAvaliacao
{

	/**
	 * Este método configura as permissões para os fluxos de pesquisar GIAITCD e de
	 * pesquisar Servidor no sistema de Gestão de Pessoas.
	 */
	protected void configuraPermissao()
	{
		this.codigoPesquisarGIAITCD = FormAcesso.CODIGO_ALTERAR_AVALIACAO_GIAITCD_PESQUISAR_GIAITCD;
		this.codigoPesquisarGestaoPessoas = FormAcesso.CODIGO_ALTERAR_AVALIACAO_GIAITCD_PESQUISAR_GESTAO_PESSOAS;
	}

	/**
	 * Direciona para a página de alteração da Avaliação em si. Chama a validação dos dados básicos - <b>validaAlterarAvaliacao</b> -
	 * para verificar se pode ou não alterar a avaliação.
	 * 
	 * @param request requisição http
	 * @param response resposta http
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void detalharBemGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
//		listaUnidadeSefaz(request, giaITCDVo);
//		for (Iterator it = giaITCDVo.getBemTributavelVo().getCollVO().iterator(); it.hasNext(); )
//		{
//			BemTributavelVo atual = (BemTributavelVo) it.next();
//			if(atual.getBemParticular().is(DomnSimNao.NAO))
//			{
//				if(Validador.isNumericoValido(atual.getAvaliacaoBemTributavelVo().getUnidadeSefazIntegracaoVo().getCodgUnidade()))	
//				{
//					getBuffer(request).setAttribute(PARAMETRO_CODG_UNIDADE_SEFAZ, atual.getAvaliacaoBemTributavelVo().getUnidadeSefazIntegracaoVo().getCodgUnidade(), request);
//					break;
//				}
//			}
//		}
		processaBensTributaveis(giaITCDVo);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		processFlow(VIEW_AVALIACAO_DETALHAR_BEM_GIAITCD, request, response, FORWARD);
	}

	/**Método utilizado para salvar Dados Gerais.
	 * @param request
	 * @param response
	 * @implemented by Elizabeth Barbosa
	 */
	protected void salvarDadosGerais(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException, IntegracaoException, LogException, PersistenciaException, AnotacaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		salvarDadosGeraisConfirmacao(request, response);
//		GIAITCDVo giaItcdVo = controladorInterativoJSP(request);
//		Integer codgUnidade = (Integer) getBuffer(request).getAttribute(PARAMETRO_CODG_UNIDADE_SEFAZ);
//		Validador.validaObjeto(codgUnidade);
//		ajustaUnidadeAvaliacao(giaItcdVo, codgUnidade);
//		giaItcdVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO));
//		try
//		{
//		   exibeMensagemConfirmacao(giaItcdVo);
//		}
//	   catch (SolicitaConfirmacaoException e)
//	   {
//	      request.setAttribute(CONFIRMACAO, e);
//	      getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
//	      processFlow(VIEW_AVALIACAO_DETALHAR_BEM_GIAITCD, request, response, FORWARD);
//	   }
	}

	/**
	 * Este método valida os dados básicos e obrigatórios para a alteração das avaliações
	 * dos bens tributáveis.
	 *
	 * @param giaItcdVo        dados consultados da GIA
	 * @param request          requisição http
	 * @param response         resposta http
	 * @return
	 * @implemented by Leandro Dorileo
	 */
	protected boolean validaAvaliacao(GIAITCDVo giaItcdVo, HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
			  ObjetoObrigatorioException, ConsultaException, IntegracaoException
	{
		StatusGIAITCDBe statusBe = null;
		try
		{
			statusBe = new StatusGIAITCDBe();
			boolean isProtocolada = statusBe.giaProtocolada(giaItcdVo);
			/** a gia foi confirmada? */
			if (!Validador.isDominioNumericoValido(giaItcdVo.getGiaConfirmada()))
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_GIA_ITCD_NAO_CONFIRMADA));
				return false;
			}
			/** possui data de protocolo? */
			if (!isProtocolada)
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_GIA_ITCD_SEM_DATA_PROTOCOLO));
				return false;
			}
			/** o status atual é protocolado? */
			else if (statusBe.verificaStatusGIA(giaItcdVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.PROTOCOLADO)))
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_GIA_ITCD_SEM_AVALIACAO));
				return false;
			}
			/** o status atual é avaliado? */
			else if (!statusBe.verificaStatusGIA(giaItcdVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO)))
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_NAO_ALTERAR));
				return false;
			}
			boolean isServidor = false;
			boolean isApenasBemParticular = true;
			for (Iterator iteBemTributavel = 
					  giaItcdVo.getBemTributavelVo().getCollVO().iterator(); iteBemTributavel.hasNext(); )
			{
				BemTributavelVo bemTributavelVo = (BemTributavelVo) iteBemTributavel.next();
				if(bemTributavelVo.getBemParticular().is(DomnSimNao.NAO))
				{
					isApenasBemParticular = false;
					for (Iterator iteServidor = bemTributavelVo.getAvaliacaoBemTributavelVo().getAvaliacaoBemTributavelServidorVo().getCollVO().iterator(); iteServidor.hasNext(); )
					{
						AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo = (AvaliacaoBemTributavelServidorVo) iteServidor.next();
						if (avaliacaoBemTributavelServidorVo.getServidorSefazVo().getNumrMatricula().equals(giaItcdVo.getServidorSefazIntegracaoVo().getNumrMatricula()))
						{
							isServidor = true;
							break;
						}
					}
				}
			}
			if(!isApenasBemParticular)
			{
				return isServidor;
			}
			return isApenasBemParticular;
		}
		catch (SQLException e)
		{
			throw new ConexaoException("Problemas ao consultar Status.");
		}
		finally
		{
			if(statusBe != null)
			{
			   statusBe.close();
			   statusBe = null;				
			}
		}
	}

	protected void salvarDadosGeraisConfirmacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException, IntegracaoException, LogException, PersistenciaException, 
			  AnotacaoException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaItcdVo = controladorInterativoJSP(request);
		ajustaUnidadeAvaliacao(giaItcdVo, new Integer(giaItcdVo.getStatusVo().getCodigoUnidadeAvaliacao()));
		giaItcdVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO));
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			//codigo do LOG
				obterInformacoesLogSefaz(request, giaItcdVo);
			//fim do código do LOG
			giaITCDBe.alterarAvaliacaoBemTributavel(giaItcdVo);
			getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
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
			getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_AVALIACAO_DETALHAR_BEM_GIAITCD, request, response, FORWARD);
		}
		catch (RegistroNaoPodeSerUtilizadoException e)
		{
			e.printStackTrace();
			getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_AVALIACAO_DETALHAR_BEM_GIAITCD, request, response, FORWARD);
		}
		catch(DadoNecessarioInexistenteException e)
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
	
	public static void exibeMensagemConfirmacao(final GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, 
			  SolicitaConfirmacaoException
	{
		Validador.validaObjeto(giaItcdVo);
		throw new SolicitaConfirmacaoException(MensagemSucesso.APOS_AVALIACAO_NAO_SERA_PERMITIDO_ALTERAR);
	}

   
   protected void excluirAvaliacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
                                                                                                    PaginaNaoEncontradaException, 
                                                                                                    TipoRedirecionamentoInvalidoException, 
                                                                                                    ConexaoException, 
                                                                                                    ConsultaException, 
                                                                                                    IntegracaoException, 
                                                                                                    LogException, 
                                                                                                    PersistenciaException, 
                                                                                                    AnotacaoException, 
                                                                                                    RegistroExistenteException
   {
      GIAITCDVo giaItcdVo = controladorInterativoJSP(request);
      ajustaUnidadeAvaliacao(giaItcdVo, new Integer(giaItcdVo.getStatusVo().getCodigoUnidadeAvaliacao()));
      giaItcdVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO));
      GIAITCDBe giaITCDBe = null;
      try
      {
         giaITCDBe = new GIAITCDBe();
         //codigo do LOG
            obterInformacoesLogSefaz(request, giaItcdVo);
         //fim do código do LOG
         
         giaITCDBe.inativarAvaliacaoBemTributavel(giaItcdVo);
         getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
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
         getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
         request.setAttribute(EXCEPTION, e);
         processFlow(VIEW_AVALIACAO_DETALHAR_BEM_GIAITCD, request, response, FORWARD);
      }
      catch (RegistroNaoPodeSerUtilizadoException e)
      {
         e.printStackTrace();
         getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
         request.setAttribute(EXCEPTION, e);
         processFlow(VIEW_AVALIACAO_DETALHAR_BEM_GIAITCD, request, response, FORWARD);
      }
      catch(DadoNecessarioInexistenteException e)
      {
         giaItcdVo.setMensagem(e.getMessage());
         request.setAttribute(ENTIDADE_VO, giaItcdVo);
         processFlow(VIEW_ERRO, request, response, FORWARD);
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
