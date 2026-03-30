/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AvaliacaoIncluir.java
 * Revisão:
 * Data revisão:
 * Implementação: Elizabeth Barbosa Moreira
 * Adaptações: Janeiro de 2008 / Elizabeth Barbosa Moreira
 * Data novas implementação:
 * 	Reimplementação da funcionalidade - Abril/2008 - Leandro Dorileo
 */
package br.gov.mt.sefaz.itc.modulo.generico.avaliacao;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
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
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAvaliacao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;

import java.io.IOException;

import java.sql.SQLException;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**Servlet de Controle de Fluxo da funcionalidade Icluir Avaliação.
 * @author Elizabeth Barbosa Moreira
 * @author Leandro Dorileo
 * @version $Revision: 1.7 $
 */
public class AvaliacaoIncluir extends ManterAvaliacao
{

	/**
	 * Este método configura as permissões para os fluxos de pesquisar GIAITCD e de
	 * pesquisar Servidor no sistema de Gestão de Pessoas.
	 */
	protected void configuraPermissao()
	{
		this.codigoPesquisarGIAITCD = FormAcesso.CODIGO_INCLUIR_AVALIACAO_GIAITCD_PESQUISAR_GIAITCD;
		this.codigoPesquisarGestaoPessoas = FormAcesso.CODIGO_INCLUIR_AVALIACAO_GIAITCD_PESQUISAR_GESTAO_PESSOAS;
	}

	/**Método utilizado para detalhar Bem GIAITCD.
	 *
	 * @param request
	 * @param response
	 * @implemented by Elizabeth Barbosa
	 * @implemented by Leandro Dorileo
	 */
	protected void detalharBemGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
		listaUnidadeSefaz(request,giaITCDVo);
		if(giaITCDVo.getStatusVo().getTipoAvaliacao().is(DomnTipoAvaliacao.AVALIACAO_AGENFA) && !Validador.isNumericoValido(giaITCDVo.getStatusVo().getCodigoUnidadeAvaliacao()))
		{
		   giaITCDVo.getStatusVo().setUnidadeAvaliacao(giaITCDVo.getServidorSefazIntegracaoVo().getUnidadeSefaz());
			giaITCDVo.getStatusVo().setCodigoUnidadeAvaliacao(giaITCDVo.getStatusVo().getCodigoAgenfa());
		}
		else if(giaITCDVo.getStatusVo().getTipoAvaliacao().is(DomnTipoAvaliacao.AVALIACAO_CGED))
		{		
			giaITCDVo.getStatusVo().setUnidadeAvaliacao(giaITCDVo.getServidorSefazIntegracaoVo().getUnidadeSefaz());
			giaITCDVo.getStatusVo().setCodigoUnidadeAvaliacao(giaITCDVo.getServidorSefazIntegracaoVo().getUnidadeSefaz().getCodgUnidade().intValue());
		}
		processaBensTributaveis(giaITCDVo);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
		processFlow(VIEW_AVALIACAO_DETALHAR_BEM_GIAITCD, request, response, FORWARD);
	}

	/**
	 * Método utilizado para salvar Dados Gerais.
	 *
	 * @param request
	 * @param response
	 * @implemented by Elizabeth Barbosa
	 * @implemented by Leandro Dorileo
	 */
	protected void salvarDadosGerais(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaItcdVo = null;
		try
		{
			giaItcdVo = controladorInterativoJSP(request);			
			ajustaUnidadeAvaliacao(giaItcdVo, new Integer(giaItcdVo.getStatusVo().getCodigoUnidadeAvaliacao()));
		   validarIncluir(giaItcdVo);
		}
	   catch (SolicitaConfirmacaoException e)
	   {
	      request.setAttribute(CONFIRMACAO, e);
	      getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
		   listaUnidadeSefaz(request,giaItcdVo);
	      processFlow(VIEW_AVALIACAO_DETALHAR_BEM_GIAITCD, request, response, FORWARD);
		}
	}
	
	protected void ajustaAvaliadores(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(giaITCDVo);
		boolean isServidorJaAvaliador = false;
		if(Validador.isCollectionValida(giaITCDVo.getBemTributavelVo().getCollVO()))
		{
			for(Iterator it = giaITCDVo.getBemTributavelVo().getCollVO().iterator(); it.hasNext(); )
			{
				BemTributavelVo bemAtual = (BemTributavelVo) it.next();
				if(GIAITCDBe.isBemPassivelAvaliacao(giaITCDVo, bemAtual))
				{
					if(Validador.isCollectionValida(bemAtual.getAvaliacaoBemTributavelVo().getAvaliacaoBemTributavelServidorVo().getCollVO()))
					{
					   isServidorJaAvaliador = false;
						for(Iterator itAvaliador = bemAtual.getAvaliacaoBemTributavelVo().getAvaliacaoBemTributavelServidorVo().getCollVO().iterator(); itAvaliador.hasNext(); )
						{
							AvaliacaoBemTributavelServidorVo avaliador = (AvaliacaoBemTributavelServidorVo) itAvaliador.next();
							if(avaliador.getServidorSefazVo().getNumrMatricula().equals(giaITCDVo.getServidorSefazIntegracaoVo().getNumrMatricula()))
							{
							   isServidorJaAvaliador = true;
								break;
							}
						}
						if(!isServidorJaAvaliador)
						{
							bemAtual.getAvaliacaoBemTributavelVo().getAvaliacaoBemTributavelServidorVo().getCollVO().add(new AvaliacaoBemTributavelServidorVo(giaITCDVo.getServidorSefazIntegracaoVo()));	
						}						
					}
				}
			}
		}		
	}
	
	protected void salvarDadosGeraisConfirmacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
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
			ajustaAvaliadores(giaItcdVo);			
			giaITCDBe.incluirAvaliacaoBemTributavel(giaItcdVo);
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
          e.printStackTrace();
          request.setAttribute(EXCEPTION, e);
          request.setAttribute(GIAITCD_VO, giaItcdVo);
          listaUnidadeSefaz(request,giaItcdVo);
          processFlow(VIEW_CONSULTAR_GIAITCD, request, response, FORWARD);
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



	public static void validarIncluir(final GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, 
			  SolicitaConfirmacaoException
	{
		Validador.validaObjeto(giaItcdVo);
		throw new SolicitaConfirmacaoException(MensagemSucesso.APOS_AVALIACAO_NAO_SERA_PERMITIDO_ALTERAR);
	}
	
	/**
	 * Este método valida os dados básicos e obrigatórios para a inclusão das avaliações
	 * dos bens tributáveis.
	 *
	 * @param giaItcdVo        dados consultados da GIA
	 * @param request          requisição http
	 * @param response         resposta http
	 * @return
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Leandro Dorileo
	 */
	protected boolean validaAvaliacao(GIAITCDVo giaItcdVo, HttpServletRequest request, HttpServletResponse response) throws ConexaoException,
			  ObjetoObrigatorioException, ConsultaException, IntegracaoException
	{
		StatusGIAITCDBe statusBe = null;
		try
		{
			statusBe = new StatusGIAITCDBe();
			boolean isGiaProtocolada = statusBe.giaProtocolada(giaItcdVo);
			getBuffer(request).setAttribute(GIAITCD_VO, giaItcdVo, request);
		   // a gia foi confirmada
		   if (!Validador.isDominioNumericoValido(giaItcdVo.getGiaConfirmada()) &&
		            giaItcdVo.getGiaConfirmada().is(DomnSimNao.NAO))
		   {
		      request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_GIA_ITCD_NAO_CONFIRMADA));
		      return false;
		   }
			// a gia foi protocolada?
			if (!isGiaProtocolada)
			{
				request.setAttribute(EXCEPTION, new Exception(MensagemErro.VALIDAR_GIA_ITCD_SEM_DATA_PROTOCOLO));
				return false;
			}
			// a gis já foi avaliada?
			if (!giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLADO))
			{
				request.setAttribute(EXCEPTION, new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_JA_FOI_AVALIADA));
				return false;
			}
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
		return true;
	}
   
   
   protected void excluirAvaliacao(HttpServletRequest request, HttpServletResponse response)
   {
   }
   
}
