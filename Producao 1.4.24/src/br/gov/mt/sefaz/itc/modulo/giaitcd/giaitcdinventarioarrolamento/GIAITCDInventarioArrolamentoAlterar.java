package br.gov.mt.sefaz.itc.modulo.giaitcd.giaitcdinventarioarrolamento;

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
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.Form;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.relatorio.InventarioArrolamentoGerarPDF;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnAba;
import br.gov.mt.sefaz.itc.util.dominio.DomnEstadoCivil;
import br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoContribuinte;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoInventario;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoRenuncia;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.7 $
 */
public class GIAITCDInventarioArrolamentoAlterar extends AbstractAbacoServlet implements Form, Flow
{
	private static final int REQUISICAO_SOLICITAR_ABA_DADOS_GERAIS = 2;
	private static final int REQUISICAO_SOLICITAR_ABA_BENS_TRIBUTAVEIS = 3;
	private static final int REQUISICAO_SOLICITAR_ABA_BENEFICIARIOS = 4;
	private static final int REQUISICAO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO = 5;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_INVENTARIANTE = 6;
	private static final int REQUISICAO_MOSTRAR_DADOS_INVENTARIANTE = 7;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_DE_CUJUS = 8;
	private static final int REQUISICAO_MOSTRAR_DADOS_DE_CUJUS = 9;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_DE_CUJUS_SEM_CPF = 10;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_MEEIRO = 11;
	private static final int REQUISICAO_MOSTRAR_DADOS_MEEIRO = 12;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_PROCURADOR = 13;
	private static final int REQUISICAO_MOSTRAR_DADOS_PROCURADOR = 14;
	private static final int REQUISICAO_SALVAR_DADOS_GERAIS = 15;
	private static final int REQUISICAO_SOLICITAR_CONSULTAR_BENEFICIARIOS = 16;
	private static final int REQUISICAO_MOSTRAR_DADOS_BENEFICIARIOS = 17;
	private static final int REQUISICAO_SOLICITAR_EXCLUIR_PROCURADOR = 18;
	private static final int REQUISICAO_REMOVER_BENEFICIARIOS = 19;
	private static final int REQUISICAO_SALVAR_BENEFICIARIOS = 20;
	private static final int REQUISICAO_CONFIRMAR_GIAITCD = 21;
	private static final int REQUISICAO_IMPRIMIR_GIAITCD = 22;
	private static final int REQUISICAO_SOLICITAR_INSERIR_MEEIRO_COMO_BENEFICIARIO = 23;	
	private static final int REQUISICAO_SOLICITAR_ABA_FORMA_PAGAMENTO = 24;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException, ConexaoException, 
			  IntegracaoException, DadoNecessarioInexistenteException, PersistenciaException, LogException, AnotacaoException, 
			  ImpossivelCriptografarException, PDFFileNotFoundException, PDFIOException, PDFDocumentException, 
			  PDFBadElementException, PDFMalformedURLException, Exception
   {
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitaAlterarGIAITCDInventarioArrolamento(request, response);
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
			case REQUISICAO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO:
				{
					solicitaAbaDemonstrativoDeCalculo(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_INVENTARIANTE:
				{
					solicitaConsultarInventariante(request, response);
					break;
				}
			case REQUISICAO_MOSTRAR_DADOS_INVENTARIANTE:
				{
					mostrarDadosInventariante(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_DE_CUJUS:
				{
					solicitaConsultarDeCujus(request, response);
					break;
				}
			case REQUISICAO_MOSTRAR_DADOS_DE_CUJUS:
				{
					mostrarDadosDeCujus(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_MEEIRO:
				{
					solicitaConsultarMeeiro(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CONSULTAR_DE_CUJUS_SEM_CPF:
				{
					solicitaConsultarDeCujusSemCPF(request, response);
					break;
				}
			case REQUISICAO_MOSTRAR_DADOS_MEEIRO:
				{
					mostrarDadosMeeiro(request, response);
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
			case REQUISICAO_SOLICITAR_CONSULTAR_BENEFICIARIOS:
				{
					solicitaConsultarBeneficiarios(request, response);
					break;
				}
			case REQUISICAO_MOSTRAR_DADOS_BENEFICIARIOS:
				{
					mostrarDadosBeneficiarios(request, response);
					break;
				}
			case REQUISICAO_SALVAR_DADOS_GERAIS:
				{
					salvarInventarioArrolamentoDadosGerais(request, response);
					break;
				}
			case REQUISICAO_SALVAR_BENEFICIARIOS:
				{
					salvarBeneficiarios(request, response);
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
		   case REQUISICAO_SOLICITAR_INSERIR_MEEIRO_COMO_BENEFICIARIO:
		      {
		         solicitarInserirMeeiroComoBeneficiario(request, response);
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
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by João Batista Padilha e Silva
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
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO)))
		{
			return REQUISICAO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_DADOS_GERAIS)))
		{
			return REQUISICAO_SOLICITAR_ABA_DADOS_GERAIS;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_INVENTARIANTE)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_INVENTARIANTE;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_DE_CUJUS)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_DE_CUJUS;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_DE_CUJUS_SEM_CPF)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_DE_CUJUS_SEM_CPF;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_MEEIRO)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_MEEIRO;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_PROCURADOR)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_PROCURADOR;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_PESQUISAR_BENEFICIARIOS)))
		{
			return REQUISICAO_SOLICITAR_CONSULTAR_BENEFICIARIOS;
		}
		else if (request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO) != null)
		{
			if (((GIAITCDVo)getBuffer(request).getAttribute(GIAITCD_VO)).getOrigem() == DomnTipoContribuinte.DECLARANTE)
			{
				return REQUISICAO_MOSTRAR_DADOS_INVENTARIANTE;
			}
			else if (((GIAITCDVo)getBuffer(request).getAttribute(GIAITCD_VO)).getOrigem() == DomnTipoContribuinte.DE_CUJUS)
			{
				return REQUISICAO_MOSTRAR_DADOS_DE_CUJUS;
			}
			else if (((GIAITCDVo)getBuffer(request).getAttribute(GIAITCD_VO)).getOrigem() == DomnTipoContribuinte.MEEIRO)
			{
				return REQUISICAO_MOSTRAR_DADOS_MEEIRO;
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
		else if (Validador.isStringValida(request.getParameter(BOTAO_EXCLUIR_BENEFICIARIO)))
		{
			return REQUISICAO_REMOVER_BENEFICIARIOS;
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
	         return REQUISICAO_SOLICITAR_CONSULTAR_INVENTARIANTE;
	      }
	      else if(StringUtil.toInt(request.getParameter(PARAMETRO_ORIGEM)) == DomnTipoContribuinte.PROCURADOR)
	      {
	         return REQUISICAO_SOLICITAR_CONSULTAR_PROCURADOR;  
	      }
	      else if(StringUtil.toInt(request.getParameter(PARAMETRO_ORIGEM)) == DomnTipoContribuinte.MEEIRO)
	      {
	         return REQUISICAO_SOLICITAR_CONSULTAR_MEEIRO;
	      }        
	      else if(StringUtil.toInt(request.getParameter(PARAMETRO_ORIGEM)) == DomnTipoContribuinte.DE_CUJUS)
	      {
	         return REQUISICAO_SOLICITAR_CONSULTAR_DE_CUJUS;
	      }
	      else if(StringUtil.toInt(request.getParameter(PARAMETRO_ORIGEM)) == DomnTipoContribuinte.BENEFICIARIOS)
	      {
	         return REQUISICAO_SOLICITAR_CONSULTAR_BENEFICIARIOS;
	      }
	   }
	   else if(Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_CONTRIBUINTE_OUTROS_DOCUMENTOS)))
	   {
	      if(StringUtil.toInt(request.getParameter(PARAMETRO_ORIGEM)) == DomnTipoContribuinte.DE_CUJUS)
	      {
	         return REQUISICAO_SOLICITAR_CONSULTAR_DE_CUJUS_SEM_CPF;
	      }
	   }
	   else if(Validador.isStringValida(request.getParameter(SELECT_MEEIRO_BENEFICIARIO)))
	   {
	      return REQUISICAO_SOLICITAR_INSERIR_MEEIRO_COMO_BENEFICIARIO;
	   }	
	    else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_FORMA_PAGAMENTO)))
	    {
	         return REQUISICAO_SOLICITAR_ABA_FORMA_PAGAMENTO;
	    }
		return REQUISICAO_VAZIA;
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private GIAITCDInventarioArrolamentoVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
	   GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = null;
	   GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
	   String paramTipoProtocoloSelecionadoContribuinte = request.getParameter( CAMPO_TIPO_PROTOCOLO_SELECIONADO_CONTRIBUINTE );
		if(Validador.isObjetoValido(giaITCDVo))
		{
		   if(giaITCDVo instanceof GIAITCDInventarioArrolamentoVo)
		   {
		      giaITCDInventarioArrolamentoVo = (GIAITCDInventarioArrolamentoVo) giaITCDVo;
		   }  			
		}
	   Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
	   if(Validador.isStringValida(paramTipoProtocoloSelecionadoContribuinte ))
	   {
	      giaITCDInventarioArrolamentoVo.setTipoProtocoloGIASelecionadoPeloContribuinte( new DomnTipoProtocolo(Integer.parseInt(paramTipoProtocoloSelecionadoContribuinte)  ) );
	   }
		if (giaITCDInventarioArrolamentoVo.isExisteSenha())
		{
			parametroConsulta(request);
		}
		giaITCDInventarioArrolamentoVo.setTipoGIA(new DomnTipoGIA(DomnTipoGIA.CAUSA_MORTIS));
		giaITCDInventarioArrolamentoVo.getNaturezaOperacaoVo().setTipoProcesso(new DomnTipoProcesso(DomnTipoProcesso.INVENTARIO_ARROLAMENTO));
		String AbaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
		Validador.validaObjeto(AbaAtual);
		if (AbaAtual.equals(ABA_DADOS_GERAIS))
		{
			atualizaAbaDadosGerais(giaITCDInventarioArrolamentoVo, request);
		}
		else if (AbaAtual.equals(ABA_BENEFICIARIOS))
		{
			atualizaAbaBeneficiarios(giaITCDInventarioArrolamentoVo, request);
		}
		return giaITCDInventarioArrolamentoVo;
	}

	/**
	 * @param giaITCDInventarioArrolamentoVo
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void atualizaAbaDadosGerais(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo, HttpServletRequest request)
	{
	   if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_POSSUI_CPF)))
	   {
	      int possuiCPF = StringUtil.toInt(request.getParameter(CAMPO_SELECT_POSSUI_CPF));
	      if (Validador.isNumericoValido(possuiCPF))
	      {
	         giaITCDInventarioArrolamentoVo.setPossuiCPF(new DomnSimNao(possuiCPF));
	      }
	   }  
	   int estadoCivil = StringUtil.toInt(request.getParameter(CAMPO_SELECT_ESTADO_CIVIL));   
	   if (Validador.isNumericoValido(estadoCivil))
	   {
	      if (estadoCivil == DomnEstadoCivil.CASADO)
	      {
	         int regimeCasamento = StringUtil.toInt(request.getParameter(CAMPO_SELECT_REGIME_CASAMENTO));
	         if (Validador.isNumericoValido(regimeCasamento))
	         {
	            giaITCDInventarioArrolamentoVo.setRegimeCasamento(new DomnRegimeCasamento(regimeCasamento));
	         }
	      }
	      else if (estadoCivil != DomnEstadoCivil.CONVIVENTE)
	      {
	         giaITCDInventarioArrolamentoVo.setPessoaMeeiro(new ContribuinteIntegracaoVo());
	      }
	      giaITCDInventarioArrolamentoVo.setEstadoCivilDeCujus(new DomnEstadoCivil(estadoCivil));
	   }
	   if (Validador.isStringValida(request.getParameter(CAMPO_DATA_FALECIMENTO)))
	   {
	      giaITCDInventarioArrolamentoVo.setDataFalecimento(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_FALECIMENTO)));
	   }
	   if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_PROCESSO_INVENTARIO)))
	   {
	      giaITCDInventarioArrolamentoVo.setTipoProcessoInventario(new DomnTipoProcessoInventario(StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_PROCESSO_INVENTARIO))));
	   }
	   if (Validador.isStringValida(request.getParameter(CAMPO_DATA_INVENTARIO_ARROLAMENTO)))
	   {
	      giaITCDInventarioArrolamentoVo.setDataInventario(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_INVENTARIO_ARROLAMENTO)));
	   }
	   if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_UF_ABERTURA_INVENTARIO_ARROLAMENTO)))
	   {
	      giaITCDInventarioArrolamentoVo.getUfAbertura().setSiglUf(request.getParameter(CAMPO_SELECT_UF_ABERTURA_INVENTARIO_ARROLAMENTO));
	   }
	   int renuncia = StringUtil.toInt(request.getParameter(CAMPO_SELECT_RENUNCIA));
	   if(Validador.isNumericoValido(renuncia))
	   {
	      if(renuncia == DomnSimNao.SIM)
	      {
	         if(Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_RENUNCIA)))
	         {
	            giaITCDInventarioArrolamentoVo.setTipoRenuncia(new DomnTipoRenuncia(StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_RENUNCIA))));
	         }
	      }
	      giaITCDInventarioArrolamentoVo.setRenuncia(new DomnSimNao(renuncia));
	   }
	   int selectHerdeirosDescendentes = StringUtil.toInt(request.getParameter(CAMPO_SELECT_HERDEIROS_DESCENDENTES));
	   if(Validador.isNumericoValido(selectHerdeirosDescendentes))
	   {
	      if(selectHerdeirosDescendentes == DomnSimNao.SIM)
	      {
	         if(Validador.isStringValida(request.getParameter(CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES)))
	         {
	            giaITCDInventarioArrolamentoVo.setNumeroHerdeirosDescendentes(StringUtil.toInt(request.getParameter(CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES)));
	            giaITCDInventarioArrolamentoVo.setNumeroHerdeirosAscendentes(0);
	            giaITCDInventarioArrolamentoVo.setNumeroOutrosHerdeiros(0);
	         }
	         giaITCDInventarioArrolamentoVo.setHerdeirosAscendentes(new DomnSimNao());
	      }
	      else
	      {
	         int selectHerdeirosAscendentes = StringUtil.toInt(request.getParameter(CAMPO_SELECT_HERDEIROS_ASCENDENTES));
	         if(Validador.isNumericoValido(selectHerdeirosAscendentes))
	         {
	            if(selectHerdeirosAscendentes == DomnSimNao.SIM)
	            {
	               if(Validador.isStringValida(request.getParameter(CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES)))
	               {
	                  giaITCDInventarioArrolamentoVo.setNumeroHerdeirosAscendentes(StringUtil.toInt(request.getParameter(CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES)));
	                  giaITCDInventarioArrolamentoVo.setNumeroHerdeirosDescendentes(0);
	                  giaITCDInventarioArrolamentoVo.setNumeroOutrosHerdeiros(0);
	               }
	            }
	            else
	            {
	               if(!(giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CASADO) || (giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CONVIVENTE))))
	               {
	                  giaITCDInventarioArrolamentoVo.setNumeroOutrosHerdeiros(StringUtil.toInt(request.getParameter(CAMPO_QUANTIDADE_OUTROS_HERDEIROS)));
	                  giaITCDInventarioArrolamentoVo.setNumeroHerdeirosAscendentes(0);
	                  giaITCDInventarioArrolamentoVo.setNumeroHerdeirosDescendentes(0);
	               }
	               else
	               {
	                  giaITCDInventarioArrolamentoVo.setNumeroOutrosHerdeiros(0);
	                  giaITCDInventarioArrolamentoVo.setNumeroHerdeirosAscendentes(0);
	                  giaITCDInventarioArrolamentoVo.setNumeroHerdeirosDescendentes(0);                   
	               }
	            }              
	         }
	         giaITCDInventarioArrolamentoVo.setHerdeirosAscendentes(new DomnSimNao(selectHerdeirosAscendentes));
	      }
	      giaITCDInventarioArrolamentoVo.setHerdeirosDescendentes(new DomnSimNao(selectHerdeirosDescendentes));
	   }
	   if (Validador.isStringValida(request.getParameter(CAMPO_NUMERO_PROCESSO_INVENTARIO_ARROLAMENTO)))
	   {
	      long numeroProcesso = StringUtil.toLong(request.getParameter(CAMPO_NUMERO_PROCESSO_INVENTARIO_ARROLAMENTO));
	      if (Validador.isNumericoValido(numeroProcesso))
	      {
	         giaITCDInventarioArrolamentoVo.setNumeroProcesso(numeroProcesso);
	      }
	   }     
	   if (Validador.isStringValida(request.getParameter(CAMPO_JUIZO_COMARCA)))
	   {
	      giaITCDInventarioArrolamentoVo.setDescricaoJuizoComarca(request.getParameter(CAMPO_JUIZO_COMARCA));
	   }
	   if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_NATUREZA_OPERACAO)))
	   {
	      giaITCDInventarioArrolamentoVo.getNaturezaOperacaoVo().setCodigo(StringUtil.toLong(request.getParameter(CAMPO_SELECT_NATUREZA_OPERACAO)));
	   }
		if(Validador.isStringValida(request.getParameter(CAMPO_JUSTIFICATIVA_ALTERACAO)))
		{
			giaITCDInventarioArrolamentoVo.setJustificativaAlteracao(request.getParameter(CAMPO_JUSTIFICATIVA_ALTERACAO));
		}
	}

	/**
	 * @param giaITCDInventarioArrolamentoVo
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	 private void atualizaAbaBeneficiarios(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo, HttpServletRequest request)
	{
	   giaITCDInventarioArrolamentoVo.getBeneficiarioVo().setGiaITCDVo(giaITCDInventarioArrolamentoVo);
	   if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_MEEIRO_BENEFICIARIO)))
	   {
	      giaITCDInventarioArrolamentoVo.setFlagMeeiroBeneficiario(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_SELECT_MEEIRO_BENEFICIARIO))));
	   }
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAlterarGIAITCDInventarioArrolamento(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, Exception
   {
		//removeBuffer(request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = (GIAITCDInventarioArrolamentoVo)getBuffer(request).getAttribute(GIAITCD_VO);
		Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
	   giaITCDInventarioArrolamentoVo.getUfIntegracaoVO().setCollVO(new GIAITCDBe().obterListaUF()  );
		GIAITCDInventarioArrolamentoBe.restaurarQuantidadeBeneficiario(giaITCDInventarioArrolamentoVo);		
	   giaITCDInventarioArrolamentoVo.getNaturezaOperacaoVo().setStatusNaturezaOperacao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   giaITCDInventarioArrolamentoVo.getNaturezaOperacaoVo().setTipoProcesso(new DomnTipoProcesso(DomnTipoProcesso.INVENTARIO_ARROLAMENTO));
		if(!Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getStatusVo().getStatusGIAITCD()))
		{
		   giaITCDInventarioArrolamentoVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO));
         System.out.println("GIA : "+giaITCDInventarioArrolamentoVo.getCodigo()+" | status alterado para - PENDENTE_DE_PROTOCOLO");
		}
	   giaITCDInventarioArrolamentoVo.getNaturezaOperacaoVo().setParametroConsulta(giaITCDInventarioArrolamentoVo.getNaturezaOperacaoVo());
	   getBuffer(request).setAttribute(LISTA_NATUREZA_DA_OPERACAO, giaITCDInventarioArrolamentoVo.getNaturezaOperacaoVo(), request);
	   processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_PESQUISAR_NATUREZA_DA_OPERACAO, request), request, response, INCLUDE);
	   request.setAttribute(SOLICITAR_LISTAR_TIPO_DOCUMENTOS, SOLICITAR_LISTAR_TIPO_DOCUMENTOS);
	   processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_PESQUISAR_CONTRIBUINTE, request), request, response, INCLUDE);
	   ContribuinteIntegracaoVo contribuinteIntegracaoVo = (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
	   giaITCDInventarioArrolamentoVo.setContribuinteIntegracaoVo(contribuinteIntegracaoVo);
	   Validador.validaObjeto(getBuffer(request).getAttribute(LISTA_NATUREZA_DA_OPERACAO));
	   giaITCDInventarioArrolamentoVo.setNaturezaOperacaoVo((NaturezaOperacaoVo) getBuffer(request).getAttribute(LISTA_NATUREZA_DA_OPERACAO));
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	   giaITCDInventarioArrolamentoVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
	   processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaBensTributaveis(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException, ConexaoException, 
			  IntegracaoException, DadoNecessarioInexistenteException, PersistenciaException, LogException, AnotacaoException, 
			  ImpossivelCriptografarException
	{
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		try
		{
			String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
			Validador.validaObjeto(abaAtual);
			getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
			validaAba(giaITCDInventarioArrolamentoVo, abaAtual, DomnAba.DADOS_GERAIS);
		   GIAITCDInventarioArrolamentoBe.validaDadosBasicos(giaITCDInventarioArrolamentoVo);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		   giaITCDInventarioArrolamentoVo.getBemTributavelVo().setExibirBemParticular(GIAITCDBe.exibeBemParticular(giaITCDInventarioArrolamentoVo));
		   giaITCDInventarioArrolamentoVo.getBemTributavelVo().setExibirIsencaoPrevistaEmLei(GIAITCDBe.exibeIsencaoPrevistaEmLei(giaITCDInventarioArrolamentoVo));
		   giaITCDInventarioArrolamentoVo.getBemTributavelVo().setExibirTipoOutrosBens(GIAITCDBe.exibeTipoOutrosBens(giaITCDInventarioArrolamentoVo));
		   GIAITCDInventarioArrolamentoBe.atualizarAbasGiaITCD(giaITCDInventarioArrolamentoVo);         						
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		}
	   catch (RegistroNaoPodeSerUtilizadoException e)
	   {
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	   }				
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		}
      ajustarBemCadastradoAlteracao(giaITCDInventarioArrolamentoVo.getBemTributavelVo());
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, giaITCDInventarioArrolamentoVo.getBemTributavelVo(), request);
	   if(Validador.isStringValida(getNumeroMatriculaUsuarioLogado(request)))
	   {
         salvarAbas(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_MANTER_BEM_TRIBUTAVEL_SERVIDOR, request), request, response, giaITCDInventarioArrolamentoVo);
      }else
      {
         salvarAbas(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_MANTER_BEM_TRIBUTAVEL, request), request, response, giaITCDInventarioArrolamentoVo);
      }
		
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, DadoNecessarioInexistenteException, PersistenciaException, LogException, 
			  AnotacaoException, ImpossivelCriptografarException
	{
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDBe = null;
		try
		{
			String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
			Validador.validaObjeto(abaAtual);
			getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
			validaAba(giaITCDInventarioArrolamentoVo, abaAtual, DomnAba.DADOS_GERAIS);
		    GIAITCDInventarioArrolamentoBe.validaDadosBasicos(giaITCDInventarioArrolamentoVo);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
			GIAITCDInventarioArrolamentoBe.atualizarAbasGiaITCD(giaITCDInventarioArrolamentoVo);			
			giaITCDBe = new GIAITCDBe();
			giaITCDBe.gerarDemonstrativoCalculo(giaITCDInventarioArrolamentoVo);			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		}
	   catch (RegistroNaoPodeSerUtilizadoException e)
	   {
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	   }		
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		}
		finally
		{
			if(giaITCDBe != null)
			{
				giaITCDBe.close();
				giaITCDBe = null;
			}
		}
		giaITCDInventarioArrolamentoVo.getBeneficiarioVo().setGiaITCDVo(giaITCDInventarioArrolamentoVo);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		salvarAbas(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, giaITCDInventarioArrolamentoVo);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
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
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaDemonstrativoDeCalculo(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, DadoNecessarioInexistenteException, 
			  PersistenciaException, LogException, AnotacaoException, ImpossivelCriptografarException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
		   try
		   {
		      giaITCDInventarioArrolamentoVo.getBemTributavelVo().setExibirBemParticular(GIAITCDBe.exibeBemParticular(giaITCDInventarioArrolamentoVo));
		      giaITCDInventarioArrolamentoVo.getBemTributavelVo().setExibirIsencaoPrevistaEmLei(GIAITCDBe.exibeIsencaoPrevistaEmLei(giaITCDInventarioArrolamentoVo));			
		      GIAITCDInventarioArrolamentoBe.atualizarAbasGiaITCD(giaITCDInventarioArrolamentoVo);    
		      giaITCDBe.gerarDemonstrativoCalculo(giaITCDInventarioArrolamentoVo);
		      String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
		      Validador.validaObjeto(abaAtual);
		      getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
		      validaAba(giaITCDInventarioArrolamentoVo, abaAtual, DomnAba.DEMONSTRATIVO_DE_CALCULO);
		      getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		      parametroConsulta(request);
		      salvarAbas(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, giaITCDInventarioArrolamentoVo);         
				
		   }
		   catch (ParametroObrigatorioException e)
		   {
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		      request.setAttribute(EXCEPTION, e);
		      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		   }
		   catch (RegistroNaoPodeSerUtilizadoException e)
		   {
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		      request.setAttribute(EXCEPTION, e);
		      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		   }        
		   catch (RegistroExistenteException e)
		   {
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		      request.setAttribute(EXCEPTION, e);
		      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		   }
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO );
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
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaDadosGerais(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException, ConexaoException, 
			  IntegracaoException, DadoNecessarioInexistenteException, PersistenciaException, LogException, AnotacaoException, 
			  ImpossivelCriptografarException
	{
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		try
		{
			String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
			Validador.validaObjeto(abaAtual);
			getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
			validaAba(giaITCDInventarioArrolamentoVo, abaAtual, DomnAba.DADOS_GERAIS);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
			GIAITCDInventarioArrolamentoBe.atualizarAbasGiaITCD(giaITCDInventarioArrolamentoVo);						
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		}
	   catch (RegistroNaoPodeSerUtilizadoException e)
	   {
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	   }		
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		}
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	   giaITCDInventarioArrolamentoVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
		salvarAbas(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, giaITCDInventarioArrolamentoVo);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaConsultarInventariante(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		giaITCDInventarioArrolamentoVo.setOrigem(DomnTipoContribuinte.DECLARANTE);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		request.setAttribute(CONTRIBUINTE_APENAS_CPF, CONTRIBUINTE_APENAS_CPF);
      
	   if(Validador.isStringValida(getNumeroMatriculaUsuarioLogado(request)))
	   {
	      processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_PESQUISAR_CONTRIBUINTE_SERVIDOR, request), request, response, FORWARD);
	   }else{
	      processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	   }
		
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaConsultarDeCujus(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		giaITCDInventarioArrolamentoVo.setOrigem(DomnTipoContribuinte.DE_CUJUS);
		giaITCDInventarioArrolamentoVo.setPessoaDeCujus(new ContribuinteIntegracaoVo());		
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	   getBuffer(request).setAttribute(IS_DE_CUJUS_SEM_CPF, "false", request);
      
	   if(Validador.isStringValida(getNumeroMatriculaUsuarioLogado(request)))
      {
         processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_PESQUISAR_CONTRIBUINTE_SERVIDOR, request), request, response, FORWARD);
      }else
      {
          processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
      }
      
	
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaConsultarMeeiro(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		giaITCDInventarioArrolamentoVo.setOrigem(DomnTipoContribuinte.MEEIRO);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		request.setAttribute(CONTRIBUINTE_APENAS_CPF, CONTRIBUINTE_APENAS_CPF);
	   if(Validador.isStringValida(getNumeroMatriculaUsuarioLogado(request)))
      {
          processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_PESQUISAR_CONTRIBUINTE_SERVIDOR, request), request, response, FORWARD);
      }else
      {
          processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
      }
		
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaConsultarProcurador(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		giaITCDInventarioArrolamentoVo.setOrigem(DomnTipoContribuinte.PROCURADOR);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		request.setAttribute(CONTRIBUINTE_APENAS_CPF, CONTRIBUINTE_APENAS_CPF);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaConsultarBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		giaITCDInventarioArrolamentoVo.setOrigem(DomnTipoContribuinte.BENEFICIARIOS);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		request.setAttribute(CONTRIBUINTE_APENAS_CPF, CONTRIBUINTE_APENAS_CPF);
	   if(Validador.isStringValida(getNumeroMatriculaUsuarioLogado(request)))
      {
         processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_PESQUISAR_CONTRIBUINTE_SERVIDOR, request), request, response, FORWARD);
      }else
      {
         processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_ALTERAR_GIAITCD_ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
      }
		
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void mostrarDadosInventariante(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException
	{
		ContribuinteIntegracaoVo inventarianteVo = (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
		Validador.validaObjeto(inventarianteVo);
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		GIAITCDInventarioArrolamentoBe giaITCDInventarioArrolamentoBe = null;
		if (Validador.isNumericoValido(inventarianteVo.getNumrContribuinte()))
		{
			try
			{
				giaITCDInventarioArrolamentoBe = new GIAITCDInventarioArrolamentoBe();
				try
				{
					GIAITCDInventarioArrolamentoBe.atribuirInventariante(inventarianteVo, giaITCDInventarioArrolamentoVo);
					giaITCDInventarioArrolamentoBe.atribuirMunicipioProtocolar(giaITCDInventarioArrolamentoVo);                       
				}
				catch(RegistroNaoPodeSerUtilizadoException e)
				{
					request.setAttribute(EXCEPTION, e);
				}
				catch(DadoNecessarioInexistenteException e)
				{
					giaITCDInventarioArrolamentoVo.setMensagem(e.getMessage());
					request.setAttribute(ENTIDADE_VO, giaITCDInventarioArrolamentoVo);         
					processFlow(VIEW_ERRO, request, response, FORWARD);
				}                 
			}
			catch(SQLException e)
			{
				throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
			}
			finally
			{
				if(giaITCDInventarioArrolamentoBe != null)
				{
					giaITCDInventarioArrolamentoBe.close();
					giaITCDInventarioArrolamentoBe = null;
				}
			}
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void mostrarDadosDeCujus(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
	   ContribuinteIntegracaoVo deCujusVo = (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
	   Validador.validaObjeto(deCujusVo);
	   GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		if (Validador.isNumericoValido(deCujusVo.getNumrContribuinte()))
		{
			try
			{
				GIAITCDInventarioArrolamentoBe.atribuirDeCujus(deCujusVo, giaITCDInventarioArrolamentoVo);         
			}
			catch(RegistroNaoPodeSerUtilizadoException e)
			{
				request.setAttribute(EXCEPTION, e);
			}
		}
	   getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	   processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void mostrarDadosMeeiro(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
	   ContribuinteIntegracaoVo meeiroVo = (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
	   Validador.validaObjeto(meeiroVo);
	   GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		if (Validador.isNumericoValido(meeiroVo.getNumrContribuinte()))
		{
			try
			{
				GIAITCDInventarioArrolamentoBe.atribuirMeeiro(meeiroVo, giaITCDInventarioArrolamentoVo);
			}
			catch(RegistroNaoPodeSerUtilizadoException e)
			{
				request.setAttribute(EXCEPTION, e);
			}
			if(giaITCDInventarioArrolamentoVo.isUsuarioServidor())
			{
				giaITCDInventarioArrolamentoVo.setMeeiroIncluidoPeloServidor(true);
			}
		}
	   getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	   processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void mostrarDadosProcurador(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException
	{
	   ContribuinteIntegracaoVo procuradorVo = (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
	   Validador.validaObjeto(procuradorVo);
	   GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
	   GIAITCDInventarioArrolamentoBe giaITCDInventarioArrolamentoBe = null;
		if (Validador.isNumericoValido(procuradorVo.getNumrContribuinte()))
		{
			try
			{
				giaITCDInventarioArrolamentoBe = new GIAITCDInventarioArrolamentoBe();
				try
				{
					GIAITCDInventarioArrolamentoBe.atribuirProcurador(procuradorVo, giaITCDInventarioArrolamentoVo);               
					giaITCDInventarioArrolamentoBe.atribuirMunicipioProtocolar(giaITCDInventarioArrolamentoVo);                                            
				}
				catch(RegistroNaoPodeSerUtilizadoException e)
				{
					request.setAttribute(EXCEPTION, e);
				}
				catch(DadoNecessarioInexistenteException e)
				{
					giaITCDInventarioArrolamentoVo.setMensagem(e.getMessage());
					request.setAttribute(ENTIDADE_VO, giaITCDInventarioArrolamentoVo);
					processFlow(VIEW_ERRO, request, response, FORWARD);
				}        
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
			}
			finally
			{
				if(giaITCDInventarioArrolamentoBe != null)
				{
					giaITCDInventarioArrolamentoBe.close();
					giaITCDInventarioArrolamentoBe = null;
				}
			}
		}
	   getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	   processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void mostrarDadosBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
	   ContribuinteIntegracaoVo pessoaBeneficiaria = (ContribuinteIntegracaoVo) request.getAttribute(CONTRIBUINTE_INTEGRACAO_VO);
	   Validador.validaObjeto(pessoaBeneficiaria);
	   GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		if (Validador.isNumericoValido(pessoaBeneficiaria.getNumrContribuinte()))
		{
			try
			{
				GIAITCDInventarioArrolamentoBe.atribuirBeneficiario(pessoaBeneficiaria, giaITCDInventarioArrolamentoVo);
				GIAITCDInventarioArrolamentoBe.atualizaAbaBeneficiarios(giaITCDInventarioArrolamentoVo);        
			}
			catch(RegistroNaoPodeSerUtilizadoException e)
			{
				request.setAttribute(EXCEPTION, e);
			}
			catch (RegistroExistenteException e)
			{
				request.setAttribute(EXCEPTION, e);       
			}
			catch(DadoNecessarioInexistenteException e)
			{
				giaITCDInventarioArrolamentoVo.setMensagem(e.getMessage());
				request.setAttribute(ENTIDADE_VO, giaITCDInventarioArrolamentoVo);
				processFlow(VIEW_ERRO, request, response, FORWARD);
			}
		}
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	   getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
	   processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
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
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void salvarInventarioArrolamentoDadosGerais(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, DadoNecessarioInexistenteException, 
			  PersistenciaException, LogException, AnotacaoException, ImpossivelCriptografarException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
	   GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	   getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
	   GIAITCDInventarioArrolamentoBe giaITCDInventarioArrolamentoBe = null;
	   try
	   {
	      GIAITCDInventarioArrolamentoBe.validaDadosBasicos(giaITCDInventarioArrolamentoVo);
	      GIAITCDInventarioArrolamentoBe.atualizarAbasGiaITCD(giaITCDInventarioArrolamentoVo);         
	      giaITCDInventarioArrolamentoBe = new GIAITCDInventarioArrolamentoBe();
	      if (giaITCDInventarioArrolamentoVo.isExisteSenha())
	      {
	         salvarAbas(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, giaITCDInventarioArrolamentoVo);
	      }
	      else
	      {
	         getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	         processFlow(VIEW_CADASTRAR_SENHA_GIAITCD, request, response, FORWARD);
	      }
	   }
	   catch (ParametroObrigatorioException e)
	   {
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	   }
	   catch (RegistroNaoPodeSerUtilizadoException e)
	   {
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	   }     
	   catch(SQLException e)
	   {
	      e.printStackTrace();
	      throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);         
	   }
	   finally
	   {
	      if(giaITCDInventarioArrolamentoBe != null)
	      {
	         giaITCDInventarioArrolamentoBe.close();
	         giaITCDInventarioArrolamentoBe = null;
	      }
	   }
	}

	/**
	 * @param destino
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @param giaITCDInventarioArrolamentoVo
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
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void salvarAbas(String destino, HttpServletRequest request, HttpServletResponse response, GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, DadoNecessarioInexistenteException, 
			  PersistenciaException, LogException, AnotacaoException, ImpossivelCriptografarException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
	   GIAITCDInventarioArrolamentoBe.atualizarAbasGiaITCD(giaITCDInventarioArrolamentoVo);
		if(!giaITCDInventarioArrolamentoVo.isUsuarioServidor())
		{
		   GIAITCDBe giaITCDBe = null;
		   try
		   {
		      giaITCDBe = new GIAITCDBe();
              obterInformacoesLogSefaz(request, giaITCDInventarioArrolamentoVo);
		      giaITCDBe.solicitarManterGIAITCD(giaITCDInventarioArrolamentoVo);
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		      processFlow(destino, request, response, FORWARD);
		   }
		   catch (SQLException e)
		   {
		      e.printStackTrace();
		      throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		   }
		   catch (ParametroObrigatorioException e)
		   {
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		      request.setAttribute(EXCEPTION, e);
		      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		   }
		   catch(RegistroNaoPodeSerUtilizadoException e)
		   {
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		      request.setAttribute(EXCEPTION, e);
		      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);       
		   }
		   catch (RegistroExistenteException e)
		   {
		      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		      request.setAttribute(EXCEPTION, e);
		      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		   }
         catch (IOException e)
         {
            getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
            request.setAttribute(EXCEPTION, e);
            processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
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
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);		
	      processFlow(destino, request, response, FORWARD);			
		}
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void removerBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		int indice = StringUtil.toInt(request.getParameter(BOTAO_EXCLUIR_BENEFICIARIO));
		((ArrayList) giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO()).remove(indice);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
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
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void salvarBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, LogException, AnotacaoException, PersistenciaException, 
			  ImpossivelCriptografarException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
	   GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
	   GIAITCDBe giaITCDBe = null;
		try
		{
			GIAITCDInventarioArrolamentoBe.atualizarAbasGiaITCD(giaITCDInventarioArrolamentoVo);         
			giaITCDBe = new GIAITCDBe();
			obterInformacoesLogSefaz(request, giaITCDInventarioArrolamentoVo);
			giaITCDBe.manterGIAITCD(giaITCDInventarioArrolamentoVo);
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
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		}
	   catch (RegistroExistenteException e)
	   {
	      getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	   }
		catch (RegistroNaoPodeSerUtilizadoException e)
		{
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		}
		finally
		{
			if (giaITCDBe != null)
			{
				giaITCDBe.close();
				giaITCDBe = null;
			}
		}
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
		processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void confirmarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, DadoNecessarioInexistenteException, ConsultaException, ConexaoException, 
			  PersistenciaException, LogException, AnotacaoException, ImpossivelCriptografarException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
	   GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
	   GIAITCDBe giaITCDBe = null;
	   GIAITCDInventarioArrolamentoBe giaITCDInventarioArrolamentoBe = null;
	   parametroConsulta(request);
	   try
	   {
	      giaITCDBe = new GIAITCDBe();
	      giaITCDInventarioArrolamentoBe = new GIAITCDInventarioArrolamentoBe();
	      giaITCDInventarioArrolamentoBe.atribuirMunicipioProtocolar(giaITCDInventarioArrolamentoVo);
         obterInformacoesLogSefaz(request, giaITCDInventarioArrolamentoVo);
	      giaITCDInventarioArrolamentoVo.setNumeroVersaoGIAITCD(new DomnVersaoGIAITCD(DomnVersaoGIAITCD.VERSAO_1_4));
	      giaITCDBe.confirmarGIAITCD(giaITCDInventarioArrolamentoVo);
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	      request.setAttribute(ENTIDADE_VO, giaITCDInventarioArrolamentoVo);
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
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	   }
	   catch (ParametroObrigatorioException e)
	   {
	      getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	   }
	   catch (RegistroNaoPodeSerUtilizadoException e)
	   {
	      getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
	      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	      request.setAttribute(EXCEPTION, e);
	      processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	   }
      catch (IOException e)
      {
          getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
          getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
          request.setAttribute(EXCEPTION, e);
          processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
      }
      finally
	   {
	      if (giaITCDBe != null)
	      {
	         giaITCDBe.close();
	         giaITCDBe = null;
	      }
	      if(giaITCDInventarioArrolamentoBe != null)
	      {
	         giaITCDInventarioArrolamentoBe.close();
	         giaITCDInventarioArrolamentoBe = null;
	      }
	   }
	}

	/**
	 * Método que envia para o request um parametro para controlar o java script generico
	 * @param request
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void parametroConsulta(HttpServletRequest request)
	{
		request.setAttribute(CONTROLE_VALIDA_FORMULARIO, CONTROLE_VALIDA_FORMULARIO);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PDFFileNotFoundException
	 * @throws PDFIOException
	 * @throws PDFDocumentException
	 * @throws PDFBadElementException
	 * @throws PDFMalformedURLException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarImprimirGIAITCD(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, 
			  PDFMalformedURLException
	{
		try
		{
			GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
			Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
			response.setContentType(TIPO_PDF);
			InventarioArrolamentoGerarPDF relatorio = new InventarioArrolamentoGerarPDF(request, giaITCDInventarioArrolamentoVo, InterfacePDF.PAGINA_A4_RETRATO);
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
	 * Metodo responsavel por direcionar a servlet de pesqusiar contribuinte(de Cujus) quando ele nao possuir CPF
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaConsultarDeCujusSemCPF(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		giaITCDInventarioArrolamentoVo.setOrigem(DomnTipoContribuinte.DE_CUJUS);
		giaITCDInventarioArrolamentoVo.setPessoaDeCujus(new ContribuinteIntegracaoVo());    
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		getBuffer(request).setAttribute(IS_DE_CUJUS_SEM_CPF, "true", request);     
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(FormAcesso.CODIGO_INCLUIR_GIAITCD_INVENTARIO_ARROLAMENTO_PESQUISAR_CONTRIBUINTE, request), request, response, FORWARD);
	}

	/**
	 * Método para  auxiliar na validações das Abas. Identifica a aba atual e valida se necessário
	 * 
	 * @param giaITCDInventarioArrolamentoVo
	 * @param abaAtual
	 * @param abaDestino
	 * @throws ParametroObrigatorioException
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @throws RegistroExistenteException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validaAba(GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo, String abaAtual, int abaDestino) throws ParametroObrigatorioException, 
			  ObjetoObrigatorioException, RegistroNaoPodeSerUtilizadoException, RegistroExistenteException
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
	               GIAITCDInventarioArrolamentoBe.validaDadosBasicos(giaITCDInventarioArrolamentoVo);
	               if (abaDestino == DomnAba.BENEFICIARIOS)
	               {
	                  GIAITCDBe.validaBemTributavel(giaITCDInventarioArrolamentoVo);
	               }
	               if (abaDestino == DomnAba.DEMONSTRATIVO_DE_CALCULO || abaDestino == DomnAba.FORMA_PAGAMENTO )
	               {
	                  GIAITCDBe.validaBemTributavel(giaITCDInventarioArrolamentoVo);
	                  GIAITCDBe.validaBeneficiario(giaITCDInventarioArrolamentoVo);
	               }
	               break;
	            }
	         case DomnAba.BENEFICIARIOS:
	            {
	               GIAITCDBe.validaBeneficiario(giaITCDInventarioArrolamentoVo);
	               break;
	            }
	      }
	   }
	}

	/**
	 * Método responsável por configurar Meeiro como beneficiário ou não.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarInserirMeeiroComoBeneficiario(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException, ConexaoException, 
			  IntegracaoException, DadoNecessarioInexistenteException, PersistenciaException, LogException, AnotacaoException, 
			  ImpossivelCriptografarException
	{
		GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
		try
		{
			String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
			Validador.validaObjeto(abaAtual);
			getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
			validaAba(giaITCDInventarioArrolamentoVo, abaAtual, DomnAba.BENEFICIARIOS);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
			GIAITCDInventarioArrolamentoBe.configurarMeeiroComoBeneficiario(giaITCDInventarioArrolamentoVo);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		}
		catch (RegistroNaoPodeSerUtilizadoException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		}     
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		}
		getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
		if (giaITCDInventarioArrolamentoVo.isExisteSenha())
		{
			salvarAbas(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, giaITCDInventarioArrolamentoVo);
		}
		else
		{
			processFlow(VIEW_CADASTRAR_SENHA_GIAITCD, request, response, FORWARD);
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
	   GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = controladorInterativoJSP(request);
	   try
	   {
	      GIAITCDBe.excluirProcurador(giaITCDInventarioArrolamentoVo);
	   }
	   catch(ParametroObrigatorioException e)
	   {
	      request.setAttribute(EXCEPTION, e);
	   }
	   getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
	   getBuffer(request).setAttribute(GIAITCD_VO, giaITCDInventarioArrolamentoVo, request);
	   processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
	}

   private void ajustarBemCadastradoAlteracao(BemTributavelVo bemTributavelVo)
   {
      if(Validador.isObjetoValido(bemTributavelVo))
      {
         if(Validador.isCollectionValida(bemTributavelVo.getCollVO()))
         {
            for (BemTributavelVo bemTributavel:(ArrayList<BemTributavelVo>)bemTributavelVo.getCollVO()) 
            {
               if(!Validador.isNumericoValido(bemTributavel.getBemVo().getCodigo()))
               {
                  if(!Validador.isDominioNumericoValido(bemTributavel.getTipoUsuario()))
                  {
                     bemTributavel.setTipoUsuario(bemTributavelVo.getTipoUsuario());
                  }                  
               }
            }
            
         }
      }
   
   }	
}
