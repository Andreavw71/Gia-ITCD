/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDSeparacaoDivorcioAlterar.java
 * Revisão: 29/01/2008 - João Batista Padilha e Silva
 * Data revisão: 
 */
package br.gov.mt.sefaz.itc.modulo.giaitcd.giaitcdseparacaodivorcio;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.ImpossivelCriptografarException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PDFBadElementException;
import br.com.abaco.util.exceptions.PDFDocumentException;
import br.com.abaco.util.exceptions.PDFFileNotFoundException;
import br.com.abaco.util.exceptions.PDFIOException;
import br.com.abaco.util.exceptions.PDFMalformedURLException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;
import br.com.abaco.util.pdf.InterfacePDF;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.conjuge.ConjugeBemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.Form;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.relatorio.GIAITCDSeparacaoDivorcioGerarPDF;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.NumeroUtil;
import br.gov.mt.sefaz.itc.util.dominio.DomnAba;
import br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoContribuinte;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet responsável por incluir uma GIA-ITCD do tipo Separação / Divórcio.
 * @author João Batista Padilha e Silva
 * @version $Revision: 1.5 $
 */
public class GIAITCDSeparacaoDivorcioAlterar extends AbstractAbacoServlet implements Form, Flow
{
	private static final int REQUISICAO_SOLICITAR_ABA_DADOS_GERAIS = 2;
	private static final int REQUISICAO_SOLICITAR_ABA_BENS_TRIBUTAVEIS = 3;
	private static final int REQUISICAO_SOLICITAR_ABA_CONJUGE = 4;
	private static final int REQUISICAO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO = 5;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_DECLARANTE = 6;
	private static final int REQUISICAO_MOSTRAR_DADOS_DECLARANTE = 7;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_PROCURADOR = 8;
	private static final int REQUISICAO_MOSTRAR_DADOS_PROCURADOR = 9;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_CONJUGE1 = 10;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_CONJUGE2 = 11;
	private static final int REQUISICAO_MOSTRAR_DADOS_CONJUGE1 = 12;
	private static final int REQUISICAO_MOSTRAR_DADOS_CONJUGE2 = 13;
	private static final int REQUISICAO_SALVAR_DADOS_GERAIS = 14;
	private static final int REQUISICAO_CADASTRAR_SENHA = 15;
	private static final int REQUISICAO_SALVAR_CONJUGE = 16;
	private static final int REQUISICAO_SOLICITA_INCLUIR_BEM_CONJUGE1 = 17;
	private static final int REQUISICAO_SOLICITA_INCLUIR_BEM_CONJUGE2 = 18;
	private static final int REQUISICAO_INCLUIR_BEM_CONJUGE = 19;
	private static final int REQUISICAO_EXCLUIR_BEM_CONJUGE1 = 20;
	private static final int REQUISICAO_EXCLUIR_BEM_CONJUGE2 = 21;
	private static final int REQUISICAO_CONFIRMAR_GIAITCD = 22;
	private static final int REQUISICAO_IMPRIMIR_GIAITCD = 23;
	private static final int REQUISICAO_SOLICITAR_EXCLUIR_CONJUGE2 = 24;
	private static final int REQUISICAO_SOLICITAR_EXCLUIR_PROCURADOR = 25;
	private static final int REQUISICAO_SOLICITAR_ABA_FORMA_PAGAMENTO = 26;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws IntegracaoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @throws PDFFileNotFoundException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, IntegracaoException, ConsultaException, 
			  ConexaoException, DadoNecessarioInexistenteException, PersistenciaException, LogException, AnotacaoException, 
			  ImpossivelCriptografarException, PDFFileNotFoundException, PDFIOException, PDFDocumentException, 
			  PDFBadElementException, PDFMalformedURLException, 
			   RegistroNaoPodeSerUtilizadoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitaAlterarGIAITCDSeparacaoDivorcio(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ABA_DADOS_GERAIS:
				{
					solicitaAbaDadosGerais(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ABA_BENS_TRIBUTAVEIS:
				{
					solicitaAbaBensTributaveis(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ABA_CONJUGE:
				{
					solicitaAbaConjuge(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO:
				{
					solicitaAbaDemonstrativoDeCalculo(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_DECLARANTE:
				{
					solicitaConsultarDeclarante(request, response);
					break;
				}
			case REQUISICAO_MOSTRAR_DADOS_DECLARANTE:
				{
					mostrarDadosDeclarante(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_PROCURADOR:
				{
					solicitaConsultarProcurador(request, response);
					break;
				}
			case REQUISICAO_MOSTRAR_DADOS_PROCURADOR:
				{
					mostrarDadosProcurador(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_CONJUGE1:
				{
					solicitaConsultarConjuge1(request, response);
					break;
				}
			case REQUISICAO_MOSTRAR_DADOS_CONJUGE1:
				{
					mostrarDadosConjuge1(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_CONJUGE2:
				{
					solicitaConsultarConjuge2(request, response);
					break;
				}
			case REQUISICAO_MOSTRAR_DADOS_CONJUGE2:
				{
					mostrarDadosConjuge2(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_EXCLUIR_CONJUGE2:
				{
					solicitaExcluirConjuge2(request, response);
					break;
				}
			case REQUISICAO_CADASTRAR_SENHA:
				{
					cadastrarSenha(request, response);
					break;
				}
			case REQUISICAO_SALVAR_DADOS_GERAIS:
				{
					salvarSeparacaoDivorcioDadosGerais(request, response);
					break;
				}
			case REQUISICAO_SALVAR_CONJUGE:
				{
					salvaSeparacaoDivorcioConjuge(request, response);
					break;
				}
			case REQUISICAO_SOLICITA_INCLUIR_BEM_CONJUGE1:
				{
					solicitaIncluirBemConjuge1(request, response);
					break;
				}
			case REQUISICAO_SOLICITA_INCLUIR_BEM_CONJUGE2:
				{
					solicitaIncluirBemConjuge2(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_BEM_CONJUGE:
				{
					incluirBemConjuge(request, response);
					break;
				}
			case REQUISICAO_EXCLUIR_BEM_CONJUGE1:
				{
					solicitaExcluirBemConjuge1(request, response);
					break;
				}
			case REQUISICAO_EXCLUIR_BEM_CONJUGE2:
				{
					solicitaExcluirBemConjuge2(request, response);
					break;
				}
			case REQUISICAO_CONFIRMAR_GIAITCD:
				{
					confirmarGIAITCD(request, response);
					break;
				}
			case REQUISICAO_IMPRIMIR_GIAITCD:
				{
					solicitarImprimirGIAITCD(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_EXCLUIR_PROCURADOR:
				{
					solicitarExcluirProcurador(request, response);
					break;
				}		    
		}
	}

	/**
	 * Este método é responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request
	 * @return
	 * @implemented by João Batista Padilha e Silva
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_DADOS_GERAIS)))
		{
			return REQUISICAO_SOLICITAR_ABA_DADOS_GERAIS;
		}
		if(Validador.isStringValida(request.getParameter(PARAMETRO_EXCLUIR_PROCURADOR)))
		{
			return REQUISICAO_SOLICITAR_EXCLUIR_PROCURADOR;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_BENS_TRIBUTAVEIS)))
		{
			return REQUISICAO_SOLICITAR_ABA_BENS_TRIBUTAVEIS;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_CONJUGE)))
		{
			return REQUISICAO_SOLICITAR_ABA_CONJUGE;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO)))
		{
			return REQUISICAO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO;
		}
	    if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_FORMA_PAGAMENTO)))
	    {
	        return REQUISICAO_SOLICITAR_ABA_FORMA_PAGAMENTO;
	    }
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_DECLARANTE)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_DECLARANTE;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_PROCURADOR)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_PROCURADOR;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_CONJUGE)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_CONJUGE1;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_CONJUGE2)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_CONJUGE2;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_EXCLUIR_CONJUGE2)))
		{
			return REQUISICAO_SOLICITAR_EXCLUIR_CONJUGE2;
		}
		if (request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO) != null)
		{
			if (((GIAITCDVo)getBuffer(request).getAttribute(GIAITCD_VO)).getOrigem() == DomnTipoContribuinte.DECLARANTE)
			{
				return REQUISICAO_MOSTRAR_DADOS_DECLARANTE;
			}
			if (((GIAITCDVo)getBuffer(request).getAttribute(GIAITCD_VO)).getOrigem() == DomnTipoContribuinte.PROCURADOR)
			{
				return REQUISICAO_MOSTRAR_DADOS_PROCURADOR;
			}
			if (((GIAITCDVo)getBuffer(request).getAttribute(GIAITCD_VO)).getOrigem() == DomnTipoContribuinte.CONJUGE1)
			{
				return REQUISICAO_MOSTRAR_DADOS_CONJUGE1;
			}
			if (((GIAITCDVo)getBuffer(request).getAttribute(GIAITCD_VO)).getOrigem() == DomnTipoContribuinte.CONJUGE2)
			{
				return REQUISICAO_MOSTRAR_DADOS_CONJUGE2;
			}
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SALVAR_DADOS_GERAIS)))
		{
			return REQUISICAO_SALVAR_DADOS_GERAIS;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SALVAR_DADOS_CONJUGE)))
		{
			return REQUISICAO_SALVAR_CONJUGE;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CADASTRAR_SENHA)))
		{
			return REQUISICAO_CADASTRAR_SENHA;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_SOLICITA_INCLUIR_BEM_CONJUGE1)))
		{
			return REQUISICAO_SOLICITA_INCLUIR_BEM_CONJUGE1;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_SOLICITA_INCLUIR_BEM_CONJUGE2)))
		{
			return REQUISICAO_SOLICITA_INCLUIR_BEM_CONJUGE2;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_INCLUIR_BEM_CONJUGE)))
		{
			return REQUISICAO_INCLUIR_BEM_CONJUGE;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_SOLICITA_EXCLUIR_BEM_CONJUGE1)))
		{
			return REQUISICAO_EXCLUIR_BEM_CONJUGE1;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_SOLICITA_EXCLUIR_BEM_CONJUGE2)))
		{
			return REQUISICAO_EXCLUIR_BEM_CONJUGE2;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_CONFIRMAR_GIAITCD;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_IMPRIMIR)))
		{
			return REQUISICAO_IMPRIMIR_GIAITCD;
		}
	   else if(Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_CONTRIBUINTE)))
	   {
	      if(StringUtil.toInt(request.getParameter(PARAMETRO_ORIGEM)) == DomnTipoContribuinte.DECLARANTE)
	      {
	         return REQUISICAO_SOLICITAR_CONSULTAR_DECLARANTE;
	      }
	      else if(StringUtil.toInt(request.getParameter(PARAMETRO_ORIGEM)) == DomnTipoContribuinte.PROCURADOR)
	      {
	         return REQUISICAO_SOLICITAR_CONSULTAR_PROCURADOR;  
	      }
	      else if(StringUtil.toInt(request.getParameter(PARAMETRO_ORIGEM)) == DomnTipoContribuinte.CONJUGE1)
	      {
	         return REQUISICAO_SOLICITAR_CONSULTAR_CONJUGE1;
	      }        
	      else if(StringUtil.toInt(request.getParameter(PARAMETRO_ORIGEM)) == DomnTipoContribuinte.CONJUGE2)
	      {
	         return REQUISICAO_SOLICITAR_CONSULTAR_CONJUGE2;
	      }
	   }     
		
		return REQUISICAO_VAZIA;
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private GIAITCDSeparacaoDivorcioVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
	   GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
	   String paramTipoProtocoloSelecionadoContribuinte = request.getParameter( Form.CAMPO_TIPO_PROTOCOLO_SELECIONADO_CONTRIBUINTE );
	   GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = null;
		if (Validador.isObjetoValido(giaITCDVo))
		{
			if(giaITCDVo instanceof GIAITCDSeparacaoDivorcioVo)
			{
				giaITCDSeparacaoDivorcioVo = (GIAITCDSeparacaoDivorcioVo) giaITCDVo;
			}  				 
		}
	   Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
	   if(Validador.isStringValida(paramTipoProtocoloSelecionadoContribuinte ))
	   {
	      giaITCDSeparacaoDivorcioVo.setTipoProtocoloGIASelecionadoPeloContribuinte( new DomnTipoProtocolo(Integer.parseInt(paramTipoProtocoloSelecionadoContribuinte)  ) );
	   }
		giaITCDSeparacaoDivorcioVo.setTipoGIA(new DomnTipoGIA(DomnTipoGIA.INTER_VIVOS));
		giaITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().setTipoProcesso(new DomnTipoProcesso(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA));
		if (giaITCDSeparacaoDivorcioVo.isExisteSenha())
		{
			parametroConsulta(request);
		}
		String AbaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
	    Validador.validaObjeto(AbaAtual);
		if (!Validador.isStringValida(AbaAtual))
		{
			AbaAtual = "";
		}
		if (AbaAtual.equals(ABA_DADOS_GERAIS))
		{
			atualizaAbaDadosGerais(giaITCDSeparacaoDivorcioVo, request);
		}
	  
	   
		return giaITCDSeparacaoDivorcioVo;
	}

	/**
	 * Este método é responsável por setar todos os parâmetros vindo do request para o VO, atualizando a aba.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by João Batista Padilha e Silva
	 */
	private void atualizaAbaDadosGerais(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo, HttpServletRequest request)
	{
	   if(Validador.isStringValida(request.getParameter(CAMPO_DATA_CASAMENTO)))
	   {
	      giaITCDSeparacaoDivorcioVo.setDataCasamento(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_CASAMENTO)));
	   }	
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_REGIME_CASAMENTO)))
		{
			int regimeCasamento = StringUtil.toInt(request.getParameter(CAMPO_SELECT_REGIME_CASAMENTO));
			giaITCDSeparacaoDivorcioVo.setRegimeCasamento(new DomnRegimeCasamento(regimeCasamento));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_NUMERO_PROCESSO_SEPARACAO_DIVORCIO)))
		{
			long numeroProcesso = StringUtil.toLong(request.getParameter(CAMPO_NUMERO_PROCESSO_SEPARACAO_DIVORCIO));
			if (Validador.isNumericoValido(numeroProcesso))
			{
				giaITCDSeparacaoDivorcioVo.setNumeroProcesso(numeroProcesso);
			}
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DATA_SEPARACAO_DIVORCIO)))
		{
			giaITCDSeparacaoDivorcioVo.setDataSeparacao(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_SEPARACAO_DIVORCIO)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_NATUREZA_OPERACAO)))
		{
			long codigo = StringUtil.toLong(request.getParameter(CAMPO_SELECT_NATUREZA_OPERACAO));
			Collection collVo = giaITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().getCollVO();
			for (Iterator iteNat = 
							giaITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().getCollVO().iterator(); iteNat.hasNext(); )
			{
				NaturezaOperacaoVo natureza = (NaturezaOperacaoVo) iteNat.next();
				if (natureza.getCodigo() == codigo)
				{
					giaITCDSeparacaoDivorcioVo.setNaturezaOperacaoVo(natureza);
					giaITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().setCollVO(collVo);
					break;
				}
			}
		}
		if(Validador.isStringValida(request.getParameter(CAMPO_JUSTIFICATIVA_ALTERACAO)))
		{
			giaITCDSeparacaoDivorcioVo.setJustificativaAlteracao(request.getParameter(CAMPO_JUSTIFICATIVA_ALTERACAO));
		}
	}

	/**
	 * Método que inicia o processo de Mudança de aba, para a aba dados gerais.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaDadosGerais(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, IntegracaoException, ConsultaException, 
			  ConexaoException, DadoNecessarioInexistenteException, PersistenciaException, LogException, AnotacaoException, 
			  ImpossivelCriptografarException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
	   Validador.validaObjeto(abaAtual);
		try
		{
			validaAba(giaITCDSeparacaoDivorcioVo, abaAtual, DomnAba.DADOS_GERAIS);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
			GIAITCDSeparacaoDivorcioBe.atualizarAbasGiaITCD(giaITCDSeparacaoDivorcioVo);												
		}
		catch(RegistroNaoPodeSerUtilizadoException e)
		{
		   getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
		   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		   request.setAttribute(EXCEPTION, e);
		   processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);			
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		}
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
	   salvarAbas(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, giaITCDSeparacaoDivorcioVo);		
	}

	/**
	 * Método que inicia o processo de Mudança de aba, para a aba Bens tributaveis.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaBensTributaveis(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException, 
			  ConexaoException, DadoNecessarioInexistenteException, PersistenciaException, LogException, AnotacaoException, 
			  ImpossivelCriptografarException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
	   String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
	   Validador.validaObjeto(abaAtual);      		
		try
		{
			validaAba(giaITCDSeparacaoDivorcioVo, abaAtual, DomnAba.BENS_TRIBUTAVEIS);
 		   giaITCDSeparacaoDivorcioVo.getBemTributavelVo().setExibirBemParticular(GIAITCDBe.exibeBemParticular(giaITCDSeparacaoDivorcioVo));
		   giaITCDSeparacaoDivorcioVo.getBemTributavelVo().setExibirIsencaoPrevistaEmLei(GIAITCDBe.exibeIsencaoPrevistaEmLei(giaITCDSeparacaoDivorcioVo));
			giaITCDSeparacaoDivorcioVo.getBemTributavelVo().setExibirTipoOutrosBens(GIAITCDBe.exibeTipoOutrosBens(giaITCDSeparacaoDivorcioVo));			
		   GIAITCDSeparacaoDivorcioBe.atualizarAbasGiaITCD(giaITCDSeparacaoDivorcioVo);        			
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		}
		catch(RegistroNaoPodeSerUtilizadoException e)
		{
		   getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
		   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		   request.setAttribute(EXCEPTION, e);
		   processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);			
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		}
	   getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, giaITCDSeparacaoDivorcioVo.getBemTributavelVo(), request);
      
	   if(Validador.isStringValida(getNumeroMatriculaUsuarioLogado(request)))
	   {
         salvarAbas(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_SEPARACAO_DIVORCIO_MANTER_BEM_TRIBUTAVEL_SERVIDOR, request), request, response, giaITCDSeparacaoDivorcioVo); 
      }else
      {
         salvarAbas(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_SEPARACAO_DIVORCIO_MANTER_BEM_TRIBUTAVEL, request), request, response, giaITCDSeparacaoDivorcioVo);   
      }
       	   
	}

	/**
	 * Método que inicia o processo de Mudança de aba, para a aba Conjuge.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaConjuge(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, ConsultaException, ConexaoException, DadoNecessarioInexistenteException, 
			  PersistenciaException, LogException, AnotacaoException, ImpossivelCriptografarException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
	   Validador.validaObjeto(abaAtual);
		GIAITCDBe giaITCDBe = null;
		try
		{
			validaAba(giaITCDSeparacaoDivorcioVo, abaAtual, DomnAba.CONJUGE);
			GIAITCDSeparacaoDivorcioBe.atualizarAbasGiaITCD(giaITCDSeparacaoDivorcioVo);									
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
		   giaITCDBe = new GIAITCDBe();
		   giaITCDBe.gerarDemonstrativoCalculo(giaITCDSeparacaoDivorcioVo);
			salvarAbas(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, giaITCDSeparacaoDivorcioVo);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch(RegistroNaoPodeSerUtilizadoException e)
		{
		   getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
		   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		   request.setAttribute(EXCEPTION, e);
		   processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);			
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
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
	 * Método que inicia o processo de Mudança de aba, para a aba demonstrativo de cálculo.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaDemonstrativoDeCalculo(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, ConsultaException, ConexaoException, DadoNecessarioInexistenteException, 
			  PersistenciaException, LogException, AnotacaoException, ImpossivelCriptografarException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
	   Validador.validaObjeto(abaAtual);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
		   try
		   {
		      GIAITCDSeparacaoDivorcioBe.atualizarAbasGiaITCD(giaITCDSeparacaoDivorcioVo);                    
		      validaAba(giaITCDSeparacaoDivorcioVo, abaAtual, DomnAba.DEMONSTRATIVO_DE_CALCULO);
		      giaITCDBe.gerarDemonstrativoCalculo(giaITCDSeparacaoDivorcioVo);
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		      getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
		      parametroConsulta(request);
		      salvarAbas(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, giaITCDSeparacaoDivorcioVo);
		   }
		   catch (ParametroObrigatorioException e)
		   {
		      getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		      request.setAttribute(EXCEPTION, e);
		      processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		   }
		   catch (RegistroNaoPodeSerUtilizadoException e)
		   {
		      getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		      request.setAttribute(EXCEPTION, e);
		      processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		   }     			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
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
	 * Método que inicia o processo de Consultar Declarante.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaConsultarDeclarante(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		giaITCDSeparacaoDivorcioVo.setOrigem(DomnTipoContribuinte.DECLARANTE);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
	   if(Validador.isStringValida(getNumeroMatriculaUsuarioLogado(request)))
	   {
	      processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_SEPARACAO_DIVORCIO_PESQUISAR_CONTRIBUINTE_SERVIDOR, request), request, response, FORWARD);
	   }else{
	      processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_SEPARACAO_DIVORCIO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	   }
		
	}

	/**
	 * Método que inicia o processo de Consulta Procurador
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaConsultarProcurador(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		giaITCDSeparacaoDivorcioVo.setOrigem(DomnTipoContribuinte.PROCURADOR);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_SEPARACAO_DIVORCIO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	}

	/**
	 * Método que inicia o processo de consulta do Conjuge 1
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaConsultarConjuge1(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		giaITCDSeparacaoDivorcioVo.setOrigem(DomnTipoContribuinte.CONJUGE1);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_SEPARACAO_DIVORCIO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	}

	/**
	 * Método que inicia o processo de consulta Conjuge 2
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaConsultarConjuge2(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		giaITCDSeparacaoDivorcioVo.setOrigem(DomnTipoContribuinte.CONJUGE2);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
	   if(Validador.isStringValida(getNumeroMatriculaUsuarioLogado(request)))
	   {
         processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_SEPARACAO_DIVORCIO_PESQUISAR_CONTRIBUINTE_SERVIDOR, request), request, response, FORWARD);
      }else
      {
         processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_SEPARACAO_DIVORCIO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
      }
		
	}

	/**
	 * Método que exibe os dados do Declarante consultado
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void mostrarDadosDeclarante(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		ContribuinteIntegracaoVo declaranteVo = 
				 (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
		Validador.validaObjeto(declaranteVo);
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		
		if (Validador.isNumericoValido(declaranteVo.getNumrContribuinte()))
		{
			if ((declaranteVo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getProcuradorVo().getNumrDocumento())))
			{
				RegistroNaoPodeSerUtilizadoException e = 
							new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_DECLARANTE);
				request.setAttribute(EXCEPTION, e);
			}
			else if (declaranteVo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNumrDocumento()))
			{
				RegistroNaoPodeSerUtilizadoException e = 
							new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_DECLARANTE_CONJUGE2);
				request.setAttribute(EXCEPTION, e);
			}
			else
			{
				giaITCDSeparacaoDivorcioVo.setResponsavelVo(declaranteVo);
				giaITCDSeparacaoDivorcioVo.getConjuge1().setPessoaConjuge(declaranteVo);
			}
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * Método que exibe os dados do Conjuge 1
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void mostrarDadosConjuge1(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		ContribuinteIntegracaoVo conjuge1Vo = 
				 (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
		Validador.validaObjeto(conjuge1Vo);
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		if (Validador.isNumericoValido(conjuge1Vo.getNumrContribuinte()))
		{
			if (conjuge1Vo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNumrDocumento()))
			{
				RegistroNaoPodeSerUtilizadoException e = 
							new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_CONJUGE2_CONJUGE1);
				request.setAttribute(EXCEPTION, e);
			}
			else if (conjuge1Vo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getProcuradorVo().getNumrDocumento()))
			{
				RegistroNaoPodeSerUtilizadoException e = 
							new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_CONJUGE1_PROCURADOR);
				request.setAttribute(EXCEPTION, e);
			}
			else
			{
				giaITCDSeparacaoDivorcioVo.getConjuge1().setPessoaConjuge(conjuge1Vo);
				giaITCDSeparacaoDivorcioVo.getConjuge1().getCollVO().clear();
			}
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * Método que mostra os dados do Conjuge 2
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void mostrarDadosConjuge2(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		ContribuinteIntegracaoVo conjuge2Vo = 
				 (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
		Validador.validaObjeto(conjuge2Vo);
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		if (Validador.isNumericoValido(conjuge2Vo.getNumrContribuinte()))
		{
			if (conjuge2Vo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getResponsavelVo().getNumrDocumento()))
			{
				RegistroNaoPodeSerUtilizadoException e = 
							new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_DECLARANTE_CONJUGE2);
				request.setAttribute(EXCEPTION, e);
			}
			else if (conjuge2Vo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getConjuge1().getPessoaConjuge().getNumrDocumento()))
			{
				RegistroNaoPodeSerUtilizadoException e = 
							new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_CONJUGE2_CONJUGE1);
				request.setAttribute(EXCEPTION, e);
			}
			else if (conjuge2Vo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getProcuradorVo().getNumrDocumento()))
			{
				RegistroNaoPodeSerUtilizadoException e = 
							new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_CONJUGE2_PROCURADOR);
				request.setAttribute(EXCEPTION, e);
			}
			else
			{
				giaITCDSeparacaoDivorcioVo.getConjuge2().setPessoaConjuge(conjuge2Vo);
				giaITCDSeparacaoDivorcioVo.getConjuge2().getCollVO().clear();
			}
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * Método que exclui os dados do Conjuge 2
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaExcluirConjuge2(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		giaITCDSeparacaoDivorcioVo.setConjuge2(new ConjugeBemTributavelVo());
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
		getBuffer(request).removeAttribute(CONJUGE2, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * Método que exibe os dados do Procurador
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void mostrarDadosProcurador(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		ContribuinteIntegracaoVo procuradorVo =  (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
		Validador.validaObjeto(procuradorVo);
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		if (Validador.isNumericoValido(procuradorVo.getNumrContribuinte()))
		{
			if (procuradorVo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getResponsavelVo().getNumrDocumento()))
			{
				RegistroNaoPodeSerUtilizadoException e = 
							new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_PROCURADOR);
				request.setAttribute(EXCEPTION, e);
			}
			else if (procuradorVo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNumrDocumento()))
			{
				RegistroNaoPodeSerUtilizadoException e = 
							new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_PROCURADOR_CONJUGE2);
				request.setAttribute(EXCEPTION, e);
			}
			else
			{
				giaITCDSeparacaoDivorcioVo.setProcuradorVo(procuradorVo);
			}
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * Método que Inicia o processo de cadastramento de Senha para a GIA
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ImpossivelCriptografarException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void cadastrarSenha(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, LogException, AnotacaoException, PersistenciaException, 
			  ImpossivelCriptografarException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDBe = null;
		if (giaITCDSeparacaoDivorcioVo.isExisteSenha())
		{
			try
			{
				giaITCDBe = new GIAITCDBe();
				giaITCDSeparacaoDivorcioVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
				giaITCDBe.manterGIAITCD(giaITCDSeparacaoDivorcioVo);
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
				processFlow(VIEW_EXIBIR_CODIGO_GIAITCD, request, response, FORWARD);
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
		   catch(RegistroExistenteException e)
		   {
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		      request.setAttribute(EXCEPTION, e);
		      processFlow(VIEW_CADASTRAR_SENHA_GIAITCD, request, response, FORWARD);
		   }
		   catch(RegistroNaoPodeSerUtilizadoException e)
		   {        
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		      request.setAttribute(EXCEPTION, e);
		      processFlow(VIEW_CADASTRAR_SENHA_GIAITCD, request, response, FORWARD);
		   }			
			catch (ParametroObrigatorioException e)
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
				request.setAttribute(EXCEPTION, e);
				processFlow(VIEW_CADASTRAR_SENHA_GIAITCD, request, response, FORWARD);
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
		else
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			processFlow(VIEW_CADASTRAR_SENHA_GIAITCD, request, response, FORWARD);
		}
	}

	/**
	 * Método que inicia o processo de salvamento da aba Dados Gerais
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ImpossivelCriptografarException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void salvarSeparacaoDivorcioDadosGerais(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, ConsultaException, ConexaoException, LogException, AnotacaoException, PersistenciaException, 
			  ImpossivelCriptografarException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDBe = null;
		if (giaITCDSeparacaoDivorcioVo.isExisteSenha())
		{
			try
			{
				giaITCDBe = new GIAITCDBe();
				GIAITCDSeparacaoDivorcioBe.validarAbaDadosGerais(giaITCDSeparacaoDivorcioVo);
				obterInformacoesLogSefaz(request, giaITCDSeparacaoDivorcioVo);
				giaITCDBe.manterGIAITCD(giaITCDSeparacaoDivorcioVo);
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
				getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
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
		   catch(RegistroExistenteException e)
		   {
		      getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		      request.setAttribute(EXCEPTION, e);
		      processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);           
		   }
			catch(RegistroNaoPodeSerUtilizadoException e)
			{
			   getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
			   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			   request.setAttribute(EXCEPTION, e);
			   processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);				
			}
			catch (ParametroObrigatorioException e)
			{
				getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
				request.setAttribute(EXCEPTION, e);
				processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
			}
			finally
			{
				if (giaITCDBe != null)
				{
					giaITCDBe.close();
					giaITCDBe = null;
				}
			}
			processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		}
		else
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			processFlow(VIEW_CADASTRAR_SENHA_GIAITCD, request, response, FORWARD);
		}
	}

	/**
	 * Método que inicia o processo de salvamento da aba Conjuge
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ImpossivelCriptografarException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void salvaSeparacaoDivorcioConjuge(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, LogException, AnotacaoException, PersistenciaException, 
			  ImpossivelCriptografarException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDBe = null;
		if (giaITCDSeparacaoDivorcioVo.isExisteSenha())
		{
			try
			{
				giaITCDBe = new GIAITCDBe();
				obterInformacoesLogSefaz(request, giaITCDSeparacaoDivorcioVo);
				giaITCDBe.manterGIAITCD(giaITCDSeparacaoDivorcioVo);
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
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
				request.setAttribute(EXCEPTION, e);
			}
		   catch(RegistroExistenteException e)
		   {
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		      request.setAttribute(EXCEPTION, e);
		   }
		   catch(RegistroNaoPodeSerUtilizadoException e)
		   {
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		      request.setAttribute(EXCEPTION, e);
		   }			
			finally
			{
				if (giaITCDBe != null)
				{
					giaITCDBe.close();
					giaITCDBe = null;
				}
			}
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
			processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		}
		else
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			processFlow(VIEW_CADASTRAR_SENHA_GIAITCD, request, response, FORWARD);
		}
	}

	/**
	 * Método que inicia o processo de salvamento das abas
	 * @param destino
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void salvarAbas(String destino, HttpServletRequest request, HttpServletResponse response, GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, DadoNecessarioInexistenteException, 
			  PersistenciaException, LogException, AnotacaoException, ImpossivelCriptografarException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
//	   GIAITCDSeparacaoDivorcioBe.atualizarAbasGiaITCD(giaITCDSeparacaoDivorcioVo);
		if(!giaITCDSeparacaoDivorcioVo.isUsuarioServidor())
		{
		   GIAITCDBe giaITCDBe = null;
		   try
		   {
		      giaITCDBe = new GIAITCDBe();
				try
				{
			      obterInformacoesLogSefaz(request, giaITCDSeparacaoDivorcioVo);
				   giaITCDBe.solicitarManterGIAITCD(giaITCDSeparacaoDivorcioVo);
				   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
				   processFlow(destino, request, response, FORWARD);					
				}
		      catch (ParametroObrigatorioException e)
		      {
		         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		         request.setAttribute(EXCEPTION, e);
		         processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		      }
		      catch (RegistroExistenteException e)
		      {
		         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		         request.setAttribute(EXCEPTION, e);
		         processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		      }
		      catch(RegistroNaoPodeSerUtilizadoException e)
		      {
		         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		         request.setAttribute(EXCEPTION, e);
		         processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);        
		      }
            catch (IOException e)
            {
               getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
               request.setAttribute(EXCEPTION, e);
               processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);   
            }
         }
		   catch (SQLException e)
		   {
		      e.printStackTrace();
		      throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
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
		else
		{
		   getBuffer(request).setAttribute(CONTROLE_VALIDA_FORMULARIO, CONTROLE_VALIDA_FORMULARIO, request);
		   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		   processFlow(destino, request, response, FORWARD);			
		}
	}

	/**
	 * Método que envia para o request um parametro para controlar o java script genérico para evitar de passar pelo request, validaFormulario, que se encontra na pagina
	 * de controle de abas.
	 * @param request
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void parametroConsulta(HttpServletRequest request)
	{
		request.setAttribute(CONTROLE_VALIDA_FORMULARIO, CONTROLE_VALIDA_FORMULARIO);
	}

	/**
	 * Este método é responsável iniciar a alteração do Separação Divorcio/Partilha.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitaAlterarGIAITCDSeparacaoDivorcio(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		NaturezaOperacaoVo naturezaOperacaoVo = new NaturezaOperacaoVo();
		naturezaOperacaoVo.setStatusNaturezaOperacao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		giaITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().setTipoProcesso(new DomnTipoProcesso(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA));		
	   if(!Validador.isDominioNumericoValido(giaITCDSeparacaoDivorcioVo.getStatusVo().getStatusGIAITCD()))
	   {
	      giaITCDSeparacaoDivorcioVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO));
	      System.out.println("GIA : "+giaITCDSeparacaoDivorcioVo.getCodigo()+" | status alterado para - PENDENTE_DE_PROTOCOLO");
	   }		
		naturezaOperacaoVo.setTipoProcesso(new DomnTipoProcesso(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA));
		naturezaOperacaoVo.setParametroConsulta(naturezaOperacaoVo);
		getBuffer(request).setAttribute(LISTA_NATUREZA_DA_OPERACAO, naturezaOperacaoVo, request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_SEPARACAO_DIVORCIO_PESQUISAR_NATUREZA_DA_OPERACAO, request), request, response, INCLUDE);
		Validador.validaObjeto(getBuffer(request).getAttribute(LISTA_NATUREZA_DA_OPERACAO));
		giaITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().setCollVO(((NaturezaOperacaoVo) getBuffer(request).getAttribute(LISTA_NATUREZA_DA_OPERACAO)).getCollVO());
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * Método que inicia o processo de inclusão do Bem Tributável para o Conjuge 1
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaIncluirBemConjuge1(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, DadoNecessarioInexistenteException, 
			  PersistenciaException, LogException, AnotacaoException, ImpossivelCriptografarException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
		giaITCDSeparacaoDivorcioVo.setOrigem(DomnTipoContribuinte.CONJUGE1);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		salvarAbas(VIEW_INCLUIR_GIAITCD_SEPARACAO_DIVORCIO_INCLUIR_BEM_CONJUGE, request, response, giaITCDSeparacaoDivorcioVo);
	}

	/**
	 * Método que inicia o processo de inclusão do Bem Tributável para o Conjuge 2
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaIncluirBemConjuge2(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, DadoNecessarioInexistenteException, 
			  PersistenciaException, LogException, AnotacaoException, ImpossivelCriptografarException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
		giaITCDSeparacaoDivorcioVo.setOrigem(DomnTipoContribuinte.CONJUGE2);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		salvarAbas(VIEW_INCLUIR_GIAITCD_SEPARACAO_DIVORCIO_INCLUIR_BEM_CONJUGE, request, response, giaITCDSeparacaoDivorcioVo);
	}

	/**
	 * Método que inclui o Bem Tributavel para cada Conjuge, fazendo o teste se é conjuge1 ou conjuge 2, caso não seja nenhum dos dois para a execução deste método.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ImpossivelCriptografarException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void incluirBemConjuge(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException, ConexaoException, 
			  IntegracaoException, LogException, AnotacaoException, PersistenciaException, ImpossivelCriptografarException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		int i = 0;
		double valorTotalAtribuido = 0;
		Iterator it = giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().iterator();
		while (it.hasNext())
		{
			double valorDigitado = StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_BEM_CONJUGE + i));
			if (Validador.isStringValida(request.getParameter(CAMPO_CHECK_BEM_CONJUGE + i)))
			{
				if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_BEM_CONJUGE + i)) || valorDigitado != 0)
				{
					BemTributavelVo bemTributavelVo = (BemTributavelVo) it.next();
					double valorTotalConjuge1 = 0;
					double valorTotalConjuge2 = 0;
					valorTotalConjuge1 = 
										 NumeroUtil.arredondaNumero(giaITCDSeparacaoDivorcioVo.getConjuge1().getValorAtribuidoBem(bemTributavelVo));
					valorTotalConjuge2 = 
										 NumeroUtil.arredondaNumero(giaITCDSeparacaoDivorcioVo.getConjuge2().getValorAtribuidoBem(bemTributavelVo));
					// Somando os valores
					valorTotalAtribuido = 
										 NumeroUtil.arredondaNumero(valorTotalConjuge1 + valorTotalConjuge2 + valorDigitado);
					//Teste de comparação
					if (valorTotalAtribuido > NumeroUtil.arredondaNumero(bemTributavelVo.getValorMercado()))
					{
						getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
						getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
						giaITCDSeparacaoDivorcioVo.setOrigem(0);
						request.setAttribute(EXCEPTION, new ParametroObrigatorioException(MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_VALOR_BEM_CONJUGE_MAIOR_VALOR_MERCADO));
						processFlow(VIEW_INCLUIR_GIAITCD_SEPARACAO_DIVORCIO_INCLUIR_BEM_CONJUGE, request, response, FORWARD);
						return;
					}
					// Gravar os Valores
					ConjugeBemTributavelVo conjugeBemTributavelVo = new ConjugeBemTributavelVo();
					conjugeBemTributavelVo.setBemTributavelVo(bemTributavelVo);
					conjugeBemTributavelVo.setValorConjuge(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_BEM_CONJUGE + i)));
					if (giaITCDSeparacaoDivorcioVo.getOrigem() == DomnTipoContribuinte.CONJUGE1)
					{
						giaITCDSeparacaoDivorcioVo.getConjuge1().getCollVO().add(conjugeBemTributavelVo);
					}
					else if (giaITCDSeparacaoDivorcioVo.getOrigem() == DomnTipoContribuinte.CONJUGE2)
					{
						giaITCDSeparacaoDivorcioVo.getConjuge2().getCollVO().add(conjugeBemTributavelVo);
					}
					else
					{
						break;
					}
				}
				else
				{
					it.next();
				}
			}
			else
			{
				it.next();
			}
			i++;
		}
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			obterInformacoesLogSefaz(request, giaITCDSeparacaoDivorcioVo);
			giaITCDBe.manterGIAITCD(giaITCDSeparacaoDivorcioVo);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
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
	   catch(RegistroExistenteException e)
	   {
	      getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	   }
	   catch(RegistroNaoPodeSerUtilizadoException e)
	   {
	      getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	   }		
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		}
		finally
		{
			if (giaITCDBe != null)
			{
				giaITCDBe.close();
				giaITCDBe = null;
			}
		}
		giaITCDSeparacaoDivorcioVo.setOrigem(0);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * Método de exclusão do Bem Tributável para o Conjuge 1
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaExcluirBemConjuge1(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		int indice = StringUtil.toInt(request.getParameter(BOTAO_SOLICITA_EXCLUIR_BEM_CONJUGE1));
		((ArrayList) giaITCDSeparacaoDivorcioVo.getConjuge1().getCollVO()).remove(indice);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * Método de exclusão do Bem Tributável para o Conjuge 2
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaExcluirBemConjuge2(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		int indice = StringUtil.toInt(request.getParameter(BOTAO_SOLICITA_EXCLUIR_BEM_CONJUGE2));
		((ArrayList) giaITCDSeparacaoDivorcioVo.getConjuge2().getCollVO()).remove(indice);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void confirmarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, ConsultaException, ConexaoException, DadoNecessarioInexistenteException, 
			  PersistenciaException, LogException, AnotacaoException, ImpossivelCriptografarException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			obterInformacoesLogSefaz(request, giaITCDSeparacaoDivorcioVo);
		   giaITCDSeparacaoDivorcioVo.setNumeroVersaoGIAITCD(new DomnVersaoGIAITCD(DomnVersaoGIAITCD.VERSAO_1_4));
			//fim do código do LOG
//			if (giaITCDSeparacaoDivorcioVo.getGiaConfirmada().getValorCorrente() == DomnSimNao.NAO)
//			{
				giaITCDBe.confirmarGIAITCD(giaITCDSeparacaoDivorcioVo);
/*			}
			else
			{
				giaITCDBe.manterGIAITCD(giaITCDSeparacaoDivorcioVo);
			}
		*/
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			giaITCDSeparacaoDivorcioVo.setMensagem(MensagemSucesso.ALTERAR);
			request.setAttribute(ENTIDADE_VO, giaITCDSeparacaoDivorcioVo);
			processFlow(VIEW_MENSAGEM_ORIENTACAO_GIA, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		}
	   catch (RegistroNaoPodeSerUtilizadoException e)
	   {
	      getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	   }
      catch (IOException e)
      {
         getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
         request.setAttribute(EXCEPTION, e);
         processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
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
	 * Método para  auxiliar na validações das Abas. Identifica a aba atual e valida se necessário
	 * 
	 * @param giaITCDSeparacaoDivorcioVo
	 * @param abaAtual
	 * @param abaDestino
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException Essa exception será tratada no método que chmar esse método
	 * @throws RegistroExistenteException Essa exception será tratada no método que chmar esse método
	 * @throws IntegracaoException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validaAba(GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo, String abaAtual, int abaDestino) throws ParametroObrigatorioException, 
			  IntegracaoException, ObjetoObrigatorioException, RegistroNaoPodeSerUtilizadoException
	{
		int abaOrigem = DomnAba.SEM_ABA;
		if (abaAtual.equals(ABA_DADOS_GERAIS))
		{
			abaOrigem = DomnAba.DADOS_GERAIS;
		}
		else if (abaAtual.equals(ABA_BENS_TRIBUTAVEIS))
		{
			abaOrigem = DomnAba.BENS_TRIBUTAVEIS;
		}
		else if (abaAtual.equals(ABA_CONJUGE))
		{
			abaOrigem = DomnAba.CONJUGE;
		}
		if (abaOrigem < abaDestino)
		{
			switch (abaOrigem)
			{
			   case DomnAba.DADOS_GERAIS:
			      {
			         GIAITCDSeparacaoDivorcioBe.validarAbaDadosGerais(giaITCDSeparacaoDivorcioVo);
			         if(abaDestino == DomnAba.DEMONSTRATIVO_DE_CALCULO)
			         {
			            GIAITCDSeparacaoDivorcioBe.validarAbaBemTributavel(giaITCDSeparacaoDivorcioVo);                      
			            GIAITCDSeparacaoDivorcioBe.validarAbaConjuge(giaITCDSeparacaoDivorcioVo);                    
			         }                
			         else if(abaDestino == DomnAba.CONJUGE)
			         {
			            GIAITCDSeparacaoDivorcioBe.validarAbaBemTributavel(giaITCDSeparacaoDivorcioVo);                    
			         }						
			         break;
			      }
			   case DomnAba.BENS_TRIBUTAVEIS:
			      {
			         GIAITCDSeparacaoDivorcioBe.validarAbaBemTributavel(giaITCDSeparacaoDivorcioVo);
			         if(abaDestino == DomnAba.DEMONSTRATIVO_DE_CALCULO)
			         {
			            GIAITCDSeparacaoDivorcioBe.validarAbaConjuge(giaITCDSeparacaoDivorcioVo);                    
			         }
			         break;
			      }
			   case DomnAba.CONJUGE:
			      {
			         GIAITCDSeparacaoDivorcioBe.validarAbaConjuge(giaITCDSeparacaoDivorcioVo);
			         break;
			      }
			}
		}
	}

	/**
	 * Este método é acionado quando o usuário solicita a impressão do cocumento referente a GIA Doação cadastrada
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PDFFileNotFoundException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @implemented by Leandro Dorileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarImprimirGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, 
			  PDFMalformedURLException
	{
		try
		{
			GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
			Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
			response.setContentType(TIPO_PDF);
			GIAITCDSeparacaoDivorcioGerarPDF relatorio = 
						new GIAITCDSeparacaoDivorcioGerarPDF(request, giaITCDSeparacaoDivorcioVo, InterfacePDF.PAGINA_A4_RETRATO);
			ByteArrayOutputStream baos = relatorio.gerarRelatorio();
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		catch (IOException io)
		{
			io.printStackTrace();
			throw new PDFIOException();
		}
	}

	/**
	 * Método responsável por solicitar a remoção do procurador para GIA-ITCD em questão.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarExcluirProcurador(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo = controladorInterativoJSP(request);
		try
		{
			GIAITCDBe.excluirProcurador(giaITCDSeparacaoDivorcioVo);
		}
		catch(ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDSeparacaoDivorcioVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
	} 		
}
