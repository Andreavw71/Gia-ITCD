package br.gov.mt.sefaz.itc.modulo.giaitcd.giaitcddoacao;

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

import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaVo;
import br.gov.mt.sefaz.itc.model.relatorio.GIAITCDDoacaoGerarPDF;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.AliquotaVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnAba;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoContribuinte;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.excecoes.DoacaoSucessivaNaoPermitidaException;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.7 $
 */
public class GIAITCDDoacaoAlterar extends AbstractAbacoServlet implements Form, Flow
{
	private static final int REQUISICAO_MOSTRAR_DADOS_BENEFICIARIOS = 2;
	private static final int REQUISICAO_MOSTRAR_DADOS_DECLARANTE = 3;
	private static final int REQUISICAO_MOSTRAR_DADOS_PROCURADOR = 4;
	private static final int REQUISICAO_REMOVER_BENEFICIARIOS = 5;
	private static final int REQUISICAO_SOLICITAR_ABA_BENEFICIARIOS = 6;
	private static final int REQUISICAO_SOLICITAR_ABA_BENS_TRIBUTAVEIS = 7;
	private static final int REQUISICAO_SOLICITAR_ABA_DADOS_GERAIS = 8;
	private static final int REQUISICAO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO = 9;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_DECLARANTE = 10;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_PROCURADOR = 11;
	private static final int REQUISICAO_INCLUIR_BENEFICIARIOS = 12;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_BENEFICIARIOS = 13;
	private static final int REQUISICAO_CONFIRMAR_GIAITCD = 14;
	private static final int REQUISICAO_IMPRIMIR_GIAITCD = 15;
	private static final int REQUISICAO_SALVAR_BENEFICIARIOS = 16;
	private static final int REQUISICAO_SALVAR_DADOS_GERAIS = 17;
	private static final int REQUISICAO_SOLICITAR_EXCLUIR_PROCURADOR = 18;
	private static final int REQUISICAO_SOLICITAR_ABA_FORMA_PAGAMENTO = 19;
   private static final int REQUISICAO_SOLICITAR_ABA_DOACAO_SUCESSIVA = 20;
   private static final String EXIBIR_ABA_DOACAO_SUCESSIVA = "exibirAbaDoacaoSucessiva";

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
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
	 * @throws ParseException
	 * @throws PDFFileNotFoundException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @implemented by Rogério Sanches de Oliveira
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, IntegracaoException, ConsultaException, 
			  ConexaoException, DadoNecessarioInexistenteException, PersistenciaException, LogException, AnotacaoException, 
			  ImpossivelCriptografarException, ParseException, PDFFileNotFoundException, 
			  PDFIOException, PDFDocumentException, PDFBadElementException, PDFMalformedURLException, SQLException
	{
	   GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
      if (giaITCDVo instanceof GIAITCDDoacaoVo){
         verificarExibirAbaDoacaoSucessiva(request,(GIAITCDDoacaoVo) giaITCDVo);
      }
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitaAlterarGIAITCDDoacao(request, response);
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
			case REQUISICAO_SOLICITAR_ABA_BENEFICIARIOS:
				{
					solicitaAbaBeneficiarios(request, response);
					break;
				}
		   case REQUISICAO_SOLICITAR_ABA_DOACAO_SUCESSIVA:
            {
               solicitaAbaDoacaoSucessiva(request, response);
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
			case REQUISICAO_SALVAR_DADOS_GERAIS:
				{
					salvarDoacaoDadosGerais(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_BENEFICIARIOS:
				{
					solicitaConsultarBeneficiarios(request, response);
					break;
				}
			case REQUISICAO_SALVAR_BENEFICIARIOS:
				{
					salvarDoacaoBeneficiarios(request, response);
					break;
				}
			case REQUISICAO_MOSTRAR_DADOS_PROCURADOR:
				{
					mostrarDadosProcurador(request, response);
					break;
				}
			case REQUISICAO_MOSTRAR_DADOS_BENEFICIARIOS:
				{
					mostrarDadosBeneficiarios(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_BENEFICIARIOS:
				{
					incluirBeneficiarios(request, response);
					break;
				}
			case REQUISICAO_REMOVER_BENEFICIARIOS:
				{
					removerBeneficiarios(request, response);
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
       * Verifica se existe pelo menos um beneficiário com GiaitcdDoacaoSucessivaVo
       * e define o atributo "exibirAbaDoacaoSucessiva" no request
       * 
       * @param request O HttpServletRequest onde o atributo será definido
       * @param giaITCDDoacaoVo O objeto GIAITCDDoacaoVo a ser verificado
       */
      private void verificarExibirAbaDoacaoSucessiva(HttpServletRequest request, GIAITCDDoacaoVo giaITCDDoacaoVo)
         throws SQLException, ObjetoObrigatorioException, ConsultaException
      {
         boolean temDoacaoSucessiva = false;
         GIAITCDDoacaoSucessivaBe doacaoSucessivaBe = new GIAITCDDoacaoSucessivaBe(); 
         
         try{         
            if (giaITCDDoacaoVo.getBeneficiarioVo() != null && 
                giaITCDDoacaoVo.getBeneficiarioVo().getCollVO() != null && 
                !giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().isEmpty()) {
                
                for (Object obj : giaITCDDoacaoVo.getBeneficiarioVo().getCollVO()) {
                   GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) obj;
                   beneficiario.getGiaitcdDoacaoSucessivaVo().getCollVO().clear();
                   //Consultar Doação Sucessiva
                   GIAITCDDoacaoSucessivaVo giaitcdDoacaoSucessivaVo = doacaoSucessivaBe.consultaDoacoesSucessivasDoBenef(beneficiario);
                   if(giaitcdDoacaoSucessivaVo.getCollVO() != null 
                       && !giaitcdDoacaoSucessivaVo.getCollVO().isEmpty()){
                       beneficiario.setFlagDoacaoSucessiva(1);
                       beneficiario.setGiaitcdDoacaoSucessivaVo(giaitcdDoacaoSucessivaVo);
                       beneficiario.setValorRecebidoDoacaoSucessiva(giaitcdDoacaoSucessivaVo.getSomaValorBaseDeCalcAnterior());
                       temDoacaoSucessiva = true;
                   }
                }          
            }
         }finally{         
            if(doacaoSucessivaBe != null){
               doacaoSucessivaBe.close();
               doacaoSucessivaBe = null;
            }            
         }
          request.setAttribute(EXIBIR_ABA_DOACAO_SUCESSIVA, temDoacaoSucessiva);
      }

	/**
	 * Este método é responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_BENS_TRIBUTAVEIS)))
		{
			return REQUISICAO_SOLICITAR_ABA_BENS_TRIBUTAVEIS;
		}
		else if(Validador.isStringValida(request.getParameter(PARAMETRO_EXCLUIR_PROCURADOR)))
		{
			return REQUISICAO_SOLICITAR_EXCLUIR_PROCURADOR;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_BENEFICIARIOS)))
		{
			return REQUISICAO_SOLICITAR_ABA_BENEFICIARIOS;
		}
	   else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_DOACAO_SUCESSIVA)))
      {
         return REQUISICAO_SOLICITAR_ABA_DOACAO_SUCESSIVA;
      }
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO)))
		{
			return REQUISICAO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_DADOS_GERAIS)))
		{
			return REQUISICAO_SOLICITAR_ABA_DADOS_GERAIS;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_DECLARANTE)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_DECLARANTE;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_PROCURADOR)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_PROCURADOR;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_BENEFICIARIOS)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_BENEFICIARIOS;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_BENEFICIARIO)))
		{
			return REQUISICAO_INCLUIR_BENEFICIARIOS;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_EXCLUIR_BENEFICIARIO)))
		{
			return REQUISICAO_REMOVER_BENEFICIARIOS;
		}
		if (request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO) != null)
		{
			if (((GIAITCDVo)getBuffer(request).getAttribute(GIAITCD_VO)).getOrigem() == DomnTipoContribuinte.DECLARANTE)
			{
				return REQUISICAO_MOSTRAR_DADOS_DECLARANTE;
			}
			else if (((GIAITCDVo)getBuffer(request).getAttribute(GIAITCD_VO)).getOrigem() == DomnTipoContribuinte.PROCURADOR)
			{
				return REQUISICAO_MOSTRAR_DADOS_PROCURADOR;
			}
			else if (((GIAITCDVo)getBuffer(request).getAttribute(GIAITCD_VO)).getOrigem() == DomnTipoContribuinte.BENEFICIARIOS)
			{
				return REQUISICAO_MOSTRAR_DADOS_BENEFICIARIOS;
			}
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SALVAR_DADOS_GERAIS)))
		{
			return REQUISICAO_SALVAR_DADOS_GERAIS;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SALVAR_DADOS_BENEFICIARIOS)))
		{
			return REQUISICAO_SALVAR_BENEFICIARIOS;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_CONFIRMAR_GIAITCD;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_IMPRIMIR)))
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
	      else if(StringUtil.toInt(request.getParameter(PARAMETRO_ORIGEM)) == DomnTipoContribuinte.BENEFICIARIOS)
	      {
	         return REQUISICAO_SOLICITAR_CONSULTAR_BENEFICIARIOS;
	      }
	   }
	   else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_FORMA_PAGAMENTO)))
	   {
			return REQUISICAO_SOLICITAR_ABA_FORMA_PAGAMENTO;
	   }
		return REQUISICAO_VAZIA;
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir.
	 * @param request
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private GIAITCDDoacaoVo controladorInterativoJSP(HttpServletRequest request)
	{
	   GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
	   String paramTipoProtocoloSelecionadoContribuinte = request.getParameter( Form.CAMPO_TIPO_PROTOCOLO_SELECIONADO_CONTRIBUINTE );
	   GIAITCDDoacaoVo giaITCDDoacaoVo = null;
	   if(giaITCDVo instanceof GIAITCDDoacaoVo)
	   {
	      giaITCDDoacaoVo = (GIAITCDDoacaoVo) giaITCDVo;   
	      if (Validador.isStringValida(request.getParameter(CAMPO_CHECK_RECOLH_SOBRE_BASE_CALC))){
            giaITCDDoacaoVo.setBaseCalculoReduzida(Double.parseDouble(request.getParameter(CAMPO_CHECK_RECOLH_SOBRE_BASE_CALC)));
         }
	   }
		if (giaITCDDoacaoVo == null)
		{
			giaITCDDoacaoVo = new GIAITCDDoacaoVo();
		}
      if(Validador.isStringValida(paramTipoProtocoloSelecionadoContribuinte ))
      {
         giaITCDDoacaoVo.setTipoProtocoloGIASelecionadoPeloContribuinte( new DomnTipoProtocolo(Integer.parseInt(paramTipoProtocoloSelecionadoContribuinte)  ) );
      }
		if (giaITCDDoacaoVo.isExisteSenha())
		{
			parametroConsulta(request);
		}
		atualizaAbaDadosGerais(giaITCDDoacaoVo, request);
		atualizaAbaBeneficiarios(giaITCDDoacaoVo, request);
	    String AbaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
		return giaITCDDoacaoVo;
	}

	/**
	 * Recupera os campos digitados na aba dados gerais. e seta para o giaITCDDoacaoVo
	 * @param giaITCDDoacaoVo
	 * @param request
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void atualizaAbaDadosGerais(final GIAITCDDoacaoVo giaITCDDoacaoVo, HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_MUNICIPIO)))
		{
			giaITCDDoacaoVo.getMunicipioProtocolar().setCodgMunicipio(StringUtil.toInteger(request.getParameter(CAMPO_SELECT_MUNICIPIO)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_NATUREZA_OPERACAO)))
		{
			long codigo = StringUtil.toLong(request.getParameter(CAMPO_SELECT_NATUREZA_OPERACAO));
			Collection collVo = giaITCDDoacaoVo.getNaturezaOperacaoVo().getCollVO();
			for (Iterator iteNat = giaITCDDoacaoVo.getNaturezaOperacaoVo().getCollVO().iterator(); iteNat.hasNext(); )
			{
				NaturezaOperacaoVo natureza = (NaturezaOperacaoVo) iteNat.next();
				if (natureza.getCodigo() == codigo)
				{
					giaITCDDoacaoVo.setNaturezaOperacaoVo(natureza);
					giaITCDDoacaoVo.getNaturezaOperacaoVo().setCollVO(collVo);
					break;
				}
			}
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_FRACAO_IDEAL)))
		{
			double fracaoIdeal = StringUtil.monetarioToDouble(request.getParameter(CAMPO_FRACAO_IDEAL));
			giaITCDDoacaoVo.setFracaoIdeal(fracaoIdeal);
		}
		if(Validador.isStringValida(request.getParameter(CAMPO_JUSTIFICATIVA_ALTERACAO)))
		{
			giaITCDDoacaoVo.setJustificativaAlteracao(request.getParameter(CAMPO_JUSTIFICATIVA_ALTERACAO));
		}
	   if (Validador.isStringValida(request.getParameter(CAMPO_CHECK_RECOLH_SOBRE_BASE_CALC))){
         giaITCDDoacaoVo.setBaseCalculoReduzida(Double.parseDouble(request.getParameter(CAMPO_CHECK_RECOLH_SOBRE_BASE_CALC)));
      }
	}

	/**
	 * Recupera os campos digitados na aba beneficiarios. e seta para o giaITCDDoacaoVo
	 * @param giaITCDDoacaoVo
	 * @param request
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void atualizaAbaBeneficiarios(final GIAITCDDoacaoVo giaITCDDoacaoVo, HttpServletRequest request)
	{
   	
	   boolean exibicao = new Date().after(buscaParametroGerencialDataLimite());
	   request.setAttribute("exibicaoFlagDoacaoAnteriorManualEprocess", exibicao);
  
		giaITCDDoacaoVo.getBeneficiarioVo().setGiaITCDVo(giaITCDDoacaoVo);
		if (Validador.isStringValida(request.getParameter(CAMPO_PERCENTUAL_ATRIBUIDO)))
		{
			((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoVo.getBeneficiarioVo()).setPercentualBemRecebido(StringUtil.monetarioToDouble((String) request.getParameter(CAMPO_PERCENTUAL_ATRIBUIDO)));
		}
      
         if(exibicao){
         for (Iterator iteBeneficiario = giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); iteBeneficiario.hasNext();){
            BeneficiarioVo benef = (BeneficiarioVo) iteBeneficiario.next();
            benef.setFlagDoacaoAnteriorManualEprocess(null);
         }
      }else{
         if (Validador.isStringValida(request.getParameter("flagDoacaoAnteriorManualEprocess"))){
               (giaITCDDoacaoVo.getBeneficiarioVo()).setFlagDoacaoAnteriorManualEprocess(Integer.parseInt(request.getParameter("flagDoacaoAnteriorManualEprocess")));
            }
      }   	   
	}
   
   private static Date buscaParametroGerencialDataLimite(){
      ConfiguracaoGerencialParametrosVo configuracaoDataLimite = new ConfiguracaoGerencialParametrosVo();
      configuracaoDataLimite.setCodigo(66);      
      configuracaoDataLimite = new ConfiguracaoGerencialParametrosVo(configuracaoDataLimite);
      ConfiguracaoGerencialParametrosBe configuracaoGerencialParametrosBe = null;
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      Date dataLimite = null;
      
      try
      {
         configuracaoGerencialParametrosBe = 
               new ConfiguracaoGerencialParametrosBe();
         configuracaoGerencialParametrosBe.consultarConfiguracaoGerencialParametros(configuracaoDataLimite);

         if (configuracaoDataLimite != null)
         {
            dataLimite = df.parse(configuracaoDataLimite.getValorItem());
         }
      }
      catch (Exception e)
      {
         System.out.println("Erro ao buscar Parametros Gerenciais" + 
                            e.getStackTrace());
      }
      finally
      {
         if (configuracaoGerencialParametrosBe != null)
         {
            configuracaoGerencialParametrosBe.close();
            configuracaoGerencialParametrosBe = null;
         }
      }
      return dataLimite;
   }

	/**
	 * Método que prepara a jsp para ser apresentada para o usuario para ser alterada quando solicitado na servlet
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAlterarGIAITCDDoacao(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		if(!giaITCDDoacaoVo.isUsuarioServidor())
		{
			//giaITCDDoacaoVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO));	// O.S 000033396/2015-45 		
		}
		NaturezaOperacaoVo naturezaOperacaoVo = new NaturezaOperacaoVo();
		naturezaOperacaoVo.setStatusNaturezaOperacao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		naturezaOperacaoVo.setTipoProcesso(new DomnTipoProcesso(DomnTipoProcesso.DOACAO));
		naturezaOperacaoVo.setParametroConsulta(naturezaOperacaoVo);
		getBuffer(request).setAttribute(LISTA_NATUREZA_DA_OPERACAO, naturezaOperacaoVo, request);		      
      processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_DOACAO_PESQUISAR_NATUREZA_DA_OPERACAO, request), request, response, INCLUDE);  
		Validador.validaObjeto(getBuffer(request).getAttribute(LISTA_NATUREZA_DA_OPERACAO));
		giaITCDDoacaoVo.getNaturezaOperacaoVo().setCollVO(((NaturezaOperacaoVo) getBuffer(request).getAttribute(LISTA_NATUREZA_DA_OPERACAO)).getCollVO());
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}

	/**
	 * Método que prepara a jsp para ser apresentada para o usuario para ser alterada quando solicitado na servlet
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @implemented by Rogério Sanches de Oliveira
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaBensTributaveis(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, ConsultaException, ConexaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, DadoNecessarioInexistenteException, PersistenciaException, LogException, 
			  AnotacaoException, ImpossivelCriptografarException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		try
		{
			String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
			getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
			validaAba(giaITCDDoacaoVo, abaAtual, DomnAba.BENS_TRIBUTAVEIS);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		   giaITCDDoacaoVo.getBemTributavelVo().setExibirTipoOutrosBens(GIAITCDBe.exibeTipoOutrosBens(giaITCDDoacaoVo));
		   giaITCDDoacaoVo.getBemTributavelVo().setExibirIsencaoPrevistaEmLei(GIAITCDBe.exibeIsencaoPrevistaEmLei(giaITCDDoacaoVo));
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
		}
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, giaITCDDoacaoVo.getBemTributavelVo(), request);
      
      if(Validador.isStringValida(getNumeroMatriculaUsuarioLogado(request)))
      {
         salvarAbas(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_DOACAO_MANTER_BEM_TRIBUTAVEL_SERVIDOR, request), request, response, giaITCDDoacaoVo);
      }else
      {
         salvarAbas(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_DOACAO_MANTER_BEM_TRIBUTAVEL, request), request, response, giaITCDDoacaoVo);
      }
      
		
	}

	/**
	 * Método que prepara a jsp para ser apresentada para o usuario para ser alterada quando solicitado na servlet
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
	 * @implemented by Rogério Sanches de Oliveira
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, ConsultaException, ConexaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, DadoNecessarioInexistenteException, PersistenciaException, LogException, 
			  AnotacaoException, ImpossivelCriptografarException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDBe = null;
      
      boolean exibicao = new Date().after(buscaParametroGerencialDataLimite());
	   request.setAttribute("exibicaoFlagDoacaoAnteriorManualEprocess", exibicao);
      
		try
		{
			String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
		   Validador.validaObjeto(abaAtual);
		   GIAITCDDoacaoBe.atualizaAbaBeneficiarios(giaITCDDoacaoVo);
			getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
			validaAba(giaITCDDoacaoVo, abaAtual, DomnAba.BENEFICIARIOS);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
			giaITCDBe = new GIAITCDBe();
			giaITCDBe.gerarDemonstrativoCalculo(giaITCDDoacaoVo);
		}
		catch(SQLException e)
		{
		   ExibirLOG.exibirLogSimplificado("Erro ao solicita aba beneficiario: " + e.getMessage());
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
		}
		finally
		{
			if(giaITCDBe != null)
			{
			   giaITCDBe.close();
			   giaITCDBe = null;
			}
		}
	   if (!giaITCDDoacaoVo.isExisteSenha())
	   {
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
	      processFlow(VIEW_CADASTRAR_SENHA_GIAITCD, request, response, FORWARD);
	   }
	   else
	   {
	      salvarAbas(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, giaITCDDoacaoVo);
	   }
	}
   
   /**
       * Método que prepara a jsp para ser apresentada para o usuario para ser alterada quando solicitado na servlet
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
       * @implemented by Rogério Sanches de Oliveira
       * @implemented by Ricardo Vitor de Oliveira Moraes
       */
      private void solicitaAbaDoacaoSucessiva(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
              IntegracaoException, ConsultaException, ConexaoException, PaginaNaoEncontradaException, 
              TipoRedirecionamentoInvalidoException, DadoNecessarioInexistenteException, PersistenciaException, LogException, 
              AnotacaoException, ImpossivelCriptografarException
      {                  
         request.setAttribute("quantidadeDoacaoSucessivaMaiorQueParametro", false);
         GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
         GIAITCDBe giaITCDBe = null;
         
         try{
            boolean exibicao = giaITCDDoacaoVo.getDataCriacao().before(buscaParametroGerencialDataLimite()); 
            if(exibicao){
               GIAITCDDoacaoSucessivaBe doacaoSucessivaBe = null; 
               doacaoSucessivaBe = new GIAITCDDoacaoSucessivaBe();    
               
               if (giaITCDDoacaoVo.getBeneficiarioVo() != null && giaITCDDoacaoVo.getBeneficiarioVo().getCollVO() != null && !giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().isEmpty()){
                  for (Object obj: giaITCDDoacaoVo.getBeneficiarioVo().getCollVO()){
                     GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) obj;                           
                     GIAITCDDoacaoSucessivaVo giaitcdDoacaoSucessivaVo = doacaoSucessivaBe.consultaDoacoesSucessivasDoBenefNaoUtilizadasParaCalculo(beneficiario);
                     if (giaitcdDoacaoSucessivaVo.getCollVO() != null && !giaitcdDoacaoSucessivaVo.getCollVO().isEmpty()){
                           if(giaitcdDoacaoSucessivaVo.getCollVO().size() >= 2){
                              request.setAttribute("quantidadeDoacaoSucessivaMaiorQueParametro", true);
                           }
                        }
                     }
                  }
            }
            String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);         
            if(request.getAttribute(EXIBIR_ABA_DOACAO_SUCESSIVA) == Boolean.FALSE)
            {
               if(abaAtual.equals("abaBeneficiarios"))
               {
                  solicitaAbaDemonstrativoDeCalculo(request, response);
               }else
               {
                  solicitaAbaBeneficiarios(request, response);
               }
            }
            
            Validador.validaObjeto(abaAtual);
            GIAITCDDoacaoBe.atualizaAbaBeneficiarios(giaITCDDoacaoVo);
            getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
            validaAba(giaITCDDoacaoVo, abaAtual, DomnAba.DOACAO_SUCESSIVA);
            getBuffer(request).setAttribute(ABA_ATUAL, ABA_DOACAO_SUCESSIVA, request);
            giaITCDBe = new GIAITCDBe();
            giaITCDBe.gerarDemonstrativoCalculo(giaITCDDoacaoVo);
         }
         catch(SQLException e)
         {
            e.printStackTrace();
            throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
         }
         catch (ParametroObrigatorioException e)
         {
            getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
            request.setAttribute(EXCEPTION, e);
            processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
         }
         catch (RegistroExistenteException e)
         {
            getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
            request.setAttribute(EXCEPTION, e);
            processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
         }
         finally
         {
            if(giaITCDBe != null)
            {
               giaITCDBe.close();
               giaITCDBe = null;
            }
         }
         if (!giaITCDDoacaoVo.isExisteSenha())
         {
            getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
            processFlow(VIEW_CADASTRAR_SENHA_GIAITCD, request, response, FORWARD);
         }
         else
         {
            salvarAbas(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, giaITCDDoacaoVo);
         }
      }   

	/**
	 * Método que prepara a jsp para ser apresentada para o usuario para ser alterada quando solicitado na servlet
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
	 * @implemented by Rogério Sanches de Oliveira
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaDemonstrativoDeCalculo(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, ConsultaException, ConexaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, DadoNecessarioInexistenteException, PersistenciaException, LogException, 
			  AnotacaoException, ImpossivelCriptografarException
	{
	   GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
	   GIAITCDBe giaITCDBe = null;                  
	   GIAITCDDoacaoSucessivaBe doacaoSucessivaBe = null; 
	   boolean limitacaoPreenchimentoDoacaoSucessiva = false;
	   boolean apresentacaoDispensaRecolhimento = false;
	   boolean exibicao = giaITCDDoacaoVo.getDataCriacao().before(buscaParametroGerencialDataLimite());   
		try
		{ 
		   if(exibicao){
		   doacaoSucessivaBe = new GIAITCDDoacaoSucessivaBe();      
            if (giaITCDDoacaoVo.getBeneficiarioVo() != null && giaITCDDoacaoVo.getBeneficiarioVo().getCollVO() != null && !giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().isEmpty()){
               for (Object obj: giaITCDDoacaoVo.getBeneficiarioVo().getCollVO()){
                  GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) obj;                           
                  GIAITCDDoacaoSucessivaVo giaitcdDoacaoSucessivaVo = doacaoSucessivaBe.consultaDoacoesSucessivasDoBenefNaoUtilizadasParaCalculo(beneficiario);
                  if (giaitcdDoacaoSucessivaVo.getCollVO() != null && !giaitcdDoacaoSucessivaVo.getCollVO().isEmpty()){
                     if(giaitcdDoacaoSucessivaVo.getCollVO().size() >= 2){
                        limitacaoPreenchimentoDoacaoSucessiva = true;
                     }
                  }
               }
            }
            
            if(limitacaoPreenchimentoDoacaoSucessiva){
               throw new DoacaoSucessivaNaoPermitidaException("Preenchimento não permitido! Existem duas ou mais GIA-ITCD-e de doação incluídas em data anterior a 06/08/2025. O Contribuinte deverá realizar a declaração utilizando o Sistema e-Process com o tipo de processo “ITCD - Denúncia Espontânea”.");
            } else{               
               request.setAttribute("qtdeColunasAliquota", retornaMaiorQtdAliquotaBeneficiario(giaITCDDoacaoVo));
               String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
               Validador.validaObjeto(abaAtual);
               getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
               validaAba(giaITCDDoacaoVo, abaAtual, DomnAba.DEMONSTRATIVO_DE_CALCULO);
               getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);        
               giaITCDBe = new GIAITCDBe();
               giaITCDBe.gerarDemonstrativoCalculo(giaITCDDoacaoVo);               
               
            } 
         }else{ 
            if (giaITCDDoacaoVo.getBeneficiarioVo() != null && giaITCDDoacaoVo.getBeneficiarioVo().getCollVO() != null && !giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().isEmpty()){
               for (Object obj: giaITCDDoacaoVo.getBeneficiarioVo().getCollVO()){
                  GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) obj;                        
                  if(beneficiario.getInfoDispensaRecolhimento() != null && beneficiario.getInfoDispensaRecolhimento() == 2){
                     apresentacaoDispensaRecolhimento = true;
                  }
               }
            }
            request.setAttribute("exibirDispensaRecolhimento", apresentacaoDispensaRecolhimento);            
            request.setAttribute("qtdeColunasAliquota", retornaMaiorQtdAliquotaBeneficiario(giaITCDDoacaoVo));
            String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
            Validador.validaObjeto(abaAtual);
            getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
            validaAba(giaITCDDoacaoVo, abaAtual, DomnAba.DEMONSTRATIVO_DE_CALCULO);
            getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);        
            giaITCDBe = new GIAITCDBe();
            giaITCDBe.gerarDemonstrativoCalculo(giaITCDDoacaoVo);     
         }
         
         
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
		}
      catch(DoacaoSucessivaNaoPermitidaException e){   
         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
         request.setAttribute("exceptionDoacaoSucessiva", e);
         processFlow(VIEW_ERRO_GERAL, request, response, FORWARD);
      }
	   finally
	   {
	      if(giaITCDBe != null)
	      {
	         giaITCDBe.close();
	         giaITCDBe = null;
	      }
         
	      if(doacaoSucessivaBe != null){
	         doacaoSucessivaBe.close();
	         doacaoSucessivaBe = null;
	      } 
	   }		
	   parametroConsulta(request);	 
	   if (!giaITCDDoacaoVo.isExisteSenha())
	   {
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
	      processFlow(VIEW_CADASTRAR_SENHA_GIAITCD, request, response, FORWARD);
	   }
	   else
	   {
	      salvarAbas(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, giaITCDDoacaoVo);
	   }		
	}
   
   private int retornaMaiorQtdAliquotaBeneficiario(GIAITCDDoacaoVo giaITCDDoacaoVo){
      if(giaITCDDoacaoVo.getBeneficiarioVo() != null){
         GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoVo.getBeneficiarioVo();
         if(beneficiario.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().isEmpty()){
            AliquotaVo aliquotasInterVivos = new AliquotaVo();
            for(Iterator ite = giaITCDDoacaoVo.getParametroLegislacaoVo().getAliquotaVo().getCollVO().iterator(); ite.hasNext();)
            {
               AliquotaVo atual = (AliquotaVo) ite.next();
               if (atual.getTipoFatoGerador().is(DomnTipoGIA.INTER_VIVOS))
               {
                  aliquotasInterVivos.getCollVO().add(atual);
               }
            }
            beneficiario.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().addAll(aliquotasInterVivos.getCollVO());
         }
      }
      
      int qtdAliquotas = 0;
      if (giaITCDDoacaoVo.getBeneficiarioVo() != null && 
          giaITCDDoacaoVo.getBeneficiarioVo().getCollVO() != null && 
          !giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().isEmpty())
      {
         for (Object obj: giaITCDDoacaoVo.getBeneficiarioVo().getCollVO())
         {
            GIAITCDDoacaoBeneficiarioVo beneficiario = 
               (GIAITCDDoacaoBeneficiarioVo) obj;
            int size = 
               beneficiario.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().size();
            if (size > qtdAliquotas)
            {
               qtdAliquotas = size;
            }
         }
      }
      return qtdAliquotas;
   }

	/**
	 * Método que prepara a jsp para ser apresentada para o usuario para ser alterada quando solicitado na servlet
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaAbaDadosGerais(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, IntegracaoException, ConsultaException, 
			  ConexaoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		try
		{
			String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
			getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
			validaAba(giaITCDDoacaoVo, abaAtual, DomnAba.DADOS_GERAIS);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
		}
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}

	/** Método para consultar  e altear contribuinte declarante
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaConsultarDeclarante(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		giaITCDDoacaoVo.setOrigem(DomnTipoContribuinte.DECLARANTE);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
      
	   if(Validador.isStringValida(getNumeroMatriculaUsuarioLogado(request)))
	   {
	      processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_DOACAO_PESQUISAR_CONTRIBUINTE_SERVIDOR, request), request, response, FORWARD);
	   }else
	   {
	      processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_DOACAO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	   }
	}

	/** Método para consultar  e alterar contribuinte procurador
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Wendell Pereira de Farias
	 */
	private void solicitaConsultarProcurador(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		giaITCDDoacaoVo.setOrigem(DomnTipoContribuinte.PROCURADOR);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
      
	   if(Validador.isStringValida(getNumeroMatriculaUsuarioLogado(request)))
	   {
	      processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_DOACAO_PESQUISAR_CONTRIBUINTE_SERVIDOR, request), request, response, FORWARD);
	   }else
	   {
	      processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_DOACAO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	   }
	}

	/** Método para consultar alterar contribuinte beneficiario
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitaConsultarBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		giaITCDDoacaoVo.setOrigem(DomnTipoContribuinte.BENEFICIARIOS);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
      
	   if(Validador.isStringValida(getNumeroMatriculaUsuarioLogado(request)))
	   {
	      processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_DOACAO_PESQUISAR_CONTRIBUINTE_SERVIDOR, request), request, response, FORWARD);
	   }else
	   {
	      processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_DOACAO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	   }
      
	}

	/** 
	 * Método executado quando busca o contribuinte declarante e vai mostrar as informações
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void mostrarDadosDeclarante(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, IntegracaoException, ConexaoException
	{
		ContribuinteIntegracaoVo declaranteVo = (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
		Validador.validaObjeto(declaranteVo);
	   GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
	   GIAITCDDoacaoBe gIAITCDDoacaoBe = null;
		if(Validador.isNumericoValido(declaranteVo.getNumrContribuinte()))
		{
		   try
		   {
		      gIAITCDDoacaoBe = new GIAITCDDoacaoBe();
		      try
		      {
		         gIAITCDDoacaoBe.atribuirDeclarante(giaITCDDoacaoVo, declaranteVo);
		         gIAITCDDoacaoBe.atribuirMunicipioProtocolarResponsavel(giaITCDDoacaoVo);
		      }
		      catch(RegistroNaoPodeSerUtilizadoException e)
		      {
		         request.setAttribute(EXCEPTION, e);
		      }
		      catch(DadoNecessarioInexistenteException e)
		      {
		         giaITCDDoacaoVo.setMensagem(e.getMessage());
		         request.setAttribute(ENTIDADE_VO, giaITCDDoacaoVo);
		         processFlow(VIEW_ERRO, request, response, FORWARD);
		         return;
		      }
		   }
		   catch(SQLException e)
		   {
		      throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		   }
		   finally
		   {
		      if(gIAITCDDoacaoBe != null)
		      {
		         gIAITCDDoacaoBe.close();
		         gIAITCDDoacaoBe = null;
		      }
		   }			
		}
		//giaITCDDoacaoVo = (GIAITCDDoacaoVo) getBuffer(request).getAttribute(GIAITCD_VO);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}

	/**
	 * Método executado quando busca o contribuinte Procurador e vai mostrar as informações
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void mostrarDadosProcurador(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, IntegracaoException, ConexaoException
	{
		ContribuinteIntegracaoVo procuradorVo = (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
		Validador.validaObjeto(procuradorVo);
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
	   GIAITCDDoacaoBe gIAITCDDoacaoBe = null;
		if(Validador.isNumericoValido(procuradorVo.getNumrContribuinte()))
		{
		   try
		   {
		      gIAITCDDoacaoBe = new GIAITCDDoacaoBe();
		      try
		      {
		         gIAITCDDoacaoBe.atribuirProcurador(giaITCDDoacaoVo, procuradorVo);
		         gIAITCDDoacaoBe.atribuirMunicipioProtocolarProcurador(giaITCDDoacaoVo);
		      }
		      catch(RegistroNaoPodeSerUtilizadoException e)
		      {
		         request.setAttribute(EXCEPTION, e);
		      }
		      catch(DadoNecessarioInexistenteException e)
		      {
		         giaITCDDoacaoVo.setMensagem(e.getMessage());
		         request.setAttribute(ENTIDADE_VO, giaITCDDoacaoVo);         
		         processFlow(VIEW_ERRO, request, response, FORWARD);   
		         return;
		      }        
		   }
		   catch(SQLException e)
		   {
		      throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		   }
		   finally
		   {
		      if(gIAITCDDoacaoBe != null)
		      {
		         gIAITCDDoacaoBe.close();
		         gIAITCDDoacaoBe = null;
		      }
		   }			
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}

	/**
	 * Método executado quando busca o contribuinte Beneficiarios e vai mostrar as informações
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void mostrarDadosBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConexaoException
	{
		ContribuinteIntegracaoVo beneficiariosVo = 
				 (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
		Validador.validaObjeto(beneficiariosVo);
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
	   GIAITCDDoacaoBe giaITCDDoacaoBe = null;
		if(Validador.isNumericoValido(beneficiariosVo.getNumrContribuinte()))
		{
		   try
		   {
		      giaITCDDoacaoBe = new GIAITCDDoacaoBe();
		      try
		      {
		         giaITCDDoacaoBe.atribuirBeneficiario(giaITCDDoacaoVo, beneficiariosVo);
		      }
		      catch(RegistroNaoPodeSerUtilizadoException e)
		      {
		         request.setAttribute(EXCEPTION, e);
		      }
		      catch (RegistroExistenteException e)
		      {
		         request.setAttribute(EXCEPTION, e);
		         giaITCDDoacaoVo.getBeneficiarioVo().setPessoaBeneficiaria(new ContribuinteIntegracaoVo());
		      }        
		      catch(DadoNecessarioInexistenteException e)
		      {
		         giaITCDDoacaoVo.setMensagem(e.getMessage());
		         request.setAttribute(ENTIDADE_VO, giaITCDDoacaoVo);
		         processFlow(VIEW_ERRO, request, response, FORWARD);
		         return;
		      }
		   }
		   catch(SQLException e)
		   {
		      throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		   }
		   finally
		   {
		      if (giaITCDDoacaoBe != null)
		      {
		         giaITCDDoacaoBe.close();
		         giaITCDDoacaoBe = null;
		      }
		   }     			
		}
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}

	/**
	 * Este método é acionado quando vai salvar cada aba individualmente.
	 * @param destino
	 * @param request
	 * @param response
	 * @param giaITCDDoacaoVo
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
	 * @implemented by Rogério Sanches de Oliveira
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void salvarAbas(String destino, HttpServletRequest request, HttpServletResponse response, GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, DadoNecessarioInexistenteException, 
			  PersistenciaException, LogException, AnotacaoException, ImpossivelCriptografarException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDBe giaITCDBe = null;
	   GIAITCDDoacaoBe.atualizaAbasGiaDoacao(giaITCDDoacaoVo);
		if (!giaITCDDoacaoVo.isUsuarioServidor())
		{
			try
			{
				giaITCDBe = new GIAITCDBe();
				obterInformacoesLogSefaz(request, giaITCDDoacaoVo);
				giaITCDBe.solicitarManterGIAITCD(giaITCDDoacaoVo);
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
				processFlow(destino, request, response, FORWARD);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
			}
			catch (ParametroObrigatorioException e)
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
				request.setAttribute(EXCEPTION, e);
				processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
			}
			catch (RegistroExistenteException e)
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
				request.setAttribute(EXCEPTION, e);
				processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
			}
			catch(RegistroNaoPodeSerUtilizadoException e)
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
				request.setAttribute(EXCEPTION, e);
				processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);			
			}
         catch (IOException e)
         {
             getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
             request.setAttribute(EXCEPTION, e);
             processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);  
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
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);		
				processFlow(destino, request, response, FORWARD);			
		}
	}

	/**
	 * Método que inclui um registro de contribuinte beneficiario  no banco no giaITCDDoacaoVo,  e limpa o contribunte pesquisado
	 * @param request
	 * @param response
	 * @throws ParseException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void incluirBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, SQLException, ConsultaException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		GIAITCDDoacaoBeneficiarioVo beneficiarioVo = new GIAITCDDoacaoBeneficiarioVo();
	   GIAITCDDoacaoSucessivaBe doacaoSucessivaBe = new GIAITCDDoacaoSucessivaBe();
		beneficiarioVo.getPessoaBeneficiaria().setNumrDocumento(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getNumrDocumento());
		beneficiarioVo.getPessoaBeneficiaria().setNumrContribuinte(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getNumrContribuinte());
	   beneficiarioVo.getPessoaBeneficiaria().setTipoDocumento(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getTipoDocumento());      
		beneficiarioVo.getPessoaBeneficiaria().setNomeContribuinte(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getNomeContribuinte());
		beneficiarioVo.getPessoaBeneficiaria().setEndereco(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getEndereco());
		beneficiarioVo.getPessoaBeneficiaria().setEnderecoNumero(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getEnderecoNumero());
		beneficiarioVo.getPessoaBeneficiaria().setEnderecoComplemento(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getEnderecoComplemento());
		beneficiarioVo.getPessoaBeneficiaria().setPontoReferencia(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getPontoReferencia());
		beneficiarioVo.getPessoaBeneficiaria().setEnderecoBairro(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getEnderecoBairro());
		beneficiarioVo.getPessoaBeneficiaria().setMunicipio(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getMunicipio());
		beneficiarioVo.getPessoaBeneficiaria().setSiglaUF(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getSiglaUF());
		beneficiarioVo.getPessoaBeneficiaria().setEnderecoCEP(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getEnderecoCEP());
		beneficiarioVo.getPessoaBeneficiaria().setEmail(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getEmail());
		beneficiarioVo.getPessoaBeneficiaria().setNumrDdd(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getNumrDdd());
		beneficiarioVo.getPessoaBeneficiaria().setNumrTelefone(giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().getNumrTelefone());
		beneficiarioVo.setPercentualBemRecebido(((GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoVo.getBeneficiarioVo()).getPercentualBemRecebido());
	   beneficiarioVo.setFlagDoacaoAnteriorManualEprocess(giaITCDDoacaoVo.getBeneficiarioVo().getFlagDoacaoAnteriorManualEprocess());
      beneficiarioVo.setGiaITCDVo(giaITCDDoacaoVo);
      
	   //Consultar Doação Sucessiva
      GIAITCDDoacaoSucessivaVo giaitcdDoacaoSucessivaVo = doacaoSucessivaBe.consultaDoacoesSucessivasDoBenef(beneficiarioVo);
         if(giaitcdDoacaoSucessivaVo.getCollVO() != null && !giaitcdDoacaoSucessivaVo.getCollVO().isEmpty()){
            request.setAttribute(EXIBIR_ABA_DOACAO_SUCESSIVA, Boolean.TRUE);
         }
      beneficiarioVo.setGiaitcdDoacaoSucessivaVo(giaitcdDoacaoSucessivaVo);
      beneficiarioVo.setValorRecebidoDoacaoSucessiva(giaitcdDoacaoSucessivaVo.getSomaValorBaseDeCalcAnterior());
      
		giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().setNumrDocumento("");
		giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().setNumrContribuinte(new Long(0));
		giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().setNomeContribuinte("");
		giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().setEndereco("");
		giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().setEnderecoNumero("");
		giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().setEnderecoComplemento("");
		giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().setPontoReferencia("");
		giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().setEnderecoBairro("");
		giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().setMunicipio("");
		giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().setSiglaUF("");
		giaITCDDoacaoVo.getBeneficiarioVo().getPessoaBeneficiaria().setEmail("");
	   giaITCDDoacaoVo.getBeneficiarioVo().setFlagDoacaoAnteriorManualEprocess(null);
		// Fim Reseta
		giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().add(beneficiarioVo);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}

	/**
	 * Método que remove um registro no giaITCDDoacaoVo
	 * @param request
	 * @param response
	 * @throws ParseException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void removerBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws ParseException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, SQLException, ObjetoObrigatorioException, ConsultaException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		int indice = StringUtil.toInt(request.getParameter(BOTAO_EXCLUIR_BENEFICIARIO));
		((ArrayList) giaITCDDoacaoVo.getBeneficiarioVo().getCollVO()).remove(indice);
	   verificarExibirAbaDoacaoSucessiva(request, giaITCDDoacaoVo);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}

	/**
	 * Este método tem como objetivo salvar o registro no banco, salvar a aba beneficiarios
	 * 
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
	 * @implemented by Rogério Sanches de Oliveira
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void salvarDoacaoBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, ConsultaException, ConexaoException, LogException, AnotacaoException, PersistenciaException, 
			  ImpossivelCriptografarException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			GIAITCDDoacaoBe.validaBeneficiariosGIAITCDDoacao(giaITCDDoacaoVo);
			obterInformacoesLogSefaz(request, giaITCDDoacaoVo);
			giaITCDBe.manterGIAITCD(giaITCDDoacaoVo);
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
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
		}
	   catch (RegistroNaoPodeSerUtilizadoException e)
	   {
	      getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	   }		
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
		}
		finally
		{
			if (giaITCDBe != null)
			{
				giaITCDBe.close();
				giaITCDBe = null;
			}
		}
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}

	/**
	 * Este método é acionado quando o usuario clica no botão confirmar alteração da GIA
	 * @param request
	 * @param response
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
	 * @implemented by Rogério Sanches de Oliveira
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void confirmarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, ConsultaException, ConexaoException, DadoNecessarioInexistenteException, 
			  PersistenciaException, LogException, AnotacaoException, ImpossivelCriptografarException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDBe = null;
		parametroConsulta(request);
		try
		{
			giaITCDBe = new GIAITCDBe();
			obterInformacoesLogSefaz(request, giaITCDDoacaoVo);
		   giaITCDDoacaoVo.setNumeroVersaoGIAITCD(new DomnVersaoGIAITCD(DomnVersaoGIAITCD.VERSAO_1_4));
			giaITCDBe.confirmarGIAITCD(giaITCDDoacaoVo);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			giaITCDDoacaoVo.setMensagem(MensagemSucesso.ALTERAR);
			request.setAttribute(ENTIDADE_VO, giaITCDDoacaoVo);
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
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
		}
		catch (RegistroNaoPodeSerUtilizadoException e)
		{
		   getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
		   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		   request.setAttribute(EXCEPTION, e);
		   processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);			
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
		}
      catch (IOException e)
      {
          getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
          getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
          request.setAttribute(EXCEPTION, e);
          processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
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
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void salvarDoacaoDadosGerais(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, LogException, AnotacaoException, PersistenciaException, 
			  ImpossivelCriptografarException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			obterInformacoesLogSefaz(request, giaITCDDoacaoVo);
			giaITCDBe.manterGIAITCD(giaITCDDoacaoVo);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
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
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
			request.setAttribute(EXCEPTION, e);
		}
	   catch (RegistroExistenteException e)
	   {
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
	      request.setAttribute(EXCEPTION, e);
	   }
	   catch (RegistroNaoPodeSerUtilizadoException e)
	   {
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
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
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ParseException
	 * @throws ObjetoObrigatorioException
	 * @throws PDFFileNotFoundException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitarImprimirGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ParseException, 
			  ObjetoObrigatorioException, PDFFileNotFoundException, PDFIOException, PDFDocumentException, 
			  PDFBadElementException, PDFMalformedURLException
	{
		try
		{
			GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
			Validador.validaObjeto(giaITCDDoacaoVo);
			response.setContentType(TIPO_PDF);
			GIAITCDDoacaoGerarPDF relatorio = 
						new GIAITCDDoacaoGerarPDF(request, giaITCDDoacaoVo, InterfacePDF.PAGINA_A4_RETRATO);
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
	 * Método que envia para o request um parametro para controlar a aba que esta seleciona e emitir a mensagem de salvar os dados
	 * é usado somente nas funcionalidades incluir e alterar.
	 * @param request
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void parametroConsulta(HttpServletRequest request)
	{
		request.setAttribute(CONTROLE_VALIDA_FORMULARIO, CONTROLE_VALIDA_FORMULARIO);
	}

	/**
	 * Método para  auxiliar na validações das Abas. Identifica a aba atual e valida se necessário
	 * 
	 * @param giaITCDDoacaoVo
	 * @param abaAtual
	 * @param abaDestino
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException ---- Essa exception será tratada no método que chamar esse método
	 * @throws RegistroExistenteException ---- Essa exception será tratada no método que chamar esse método
	 * @throws IntegracaoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void validaAba(GIAITCDDoacaoVo giaITCDDoacaoVo, String abaAtual, int abaDestino) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, RegistroExistenteException, IntegracaoException, ConsultaException, 
			  ConexaoException
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
		else if (abaAtual.equals(ABA_BENEFICIARIOS))
		{
			abaOrigem = DomnAba.BENEFICIARIOS;
		}
		if (abaOrigem < abaDestino)
		{
			switch (abaOrigem)
			{
				case DomnAba.DADOS_GERAIS:
					{
						GIAITCDDoacaoBe.validarDadosGeraisGIAITCDDoacao(giaITCDDoacaoVo);
						if (abaDestino == DomnAba.BENEFICIARIOS)
						{
							GIAITCDDoacaoBe.validaBensTributaveisGIAITCDDoacao(giaITCDDoacaoVo);
						}
						if (abaDestino == DomnAba.DEMONSTRATIVO_DE_CALCULO || abaDestino == DomnAba.FORMA_PAGAMENTO )
						{
							GIAITCDDoacaoBe.validaBensTributaveisGIAITCDDoacao(giaITCDDoacaoVo);
							GIAITCDDoacaoBe.validaBeneficiariosGIAITCDDoacao(giaITCDDoacaoVo);
						}
						break;
					}
				case DomnAba.BENEFICIARIOS:
					{
						GIAITCDDoacaoBe.validaBeneficiariosGIAITCDDoacao(giaITCDDoacaoVo);
						break;
					}
			}
		}
	}

	/**
	 * Método responsável por solicitar a remoção do procurador para GIA-ITCD em questão.
	 * @param request
	 * @param response
	 * @throws ParseException
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarExcluirProcurador(HttpServletRequest request, HttpServletResponse response) throws ParseException, 
			  ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDDoacaoVo giaITCDDoacaoVo = controladorInterativoJSP(request);
		try
		{
			GIAITCDBe.excluirProcurador(giaITCDDoacaoVo);
		}
		catch(ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDDoacaoVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
	}	
	

}
