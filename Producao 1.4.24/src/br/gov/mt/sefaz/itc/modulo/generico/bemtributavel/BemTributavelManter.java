package br.gov.mt.sefaz.itc.modulo.generico.bemtributavel;

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
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;
import br.com.abaco.util.http.JspUtil;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.Form;
//import br.gov.mt.sefaz.itc.model.giaitcd.fichabem.ficharebanholpm.Form;
import br.gov.mt.sefaz.itc.model.generico.fichaimovel.FichaImovelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichabem.ficharebanholpm.FichaRebanhoLPMBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichabem.ficharebanholpm.FichaRebanhoLPMVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichabem.fichaveiculo.FichaVeiculoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria.FichaImovelRuralBenfeitoriaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao.FichaImovelRuralConstrucaoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao.FichaImovelRuralConstrucaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura.FichaImovelRuralCulturaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura.FichaImovelRuralCulturaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho.FichaImovelRuralRebanhoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho.FichaImovelRuralRebanhoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.benfeitoria.FichaImovelUrbanoBenfeitoriaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.bem.BemVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoVo;
import br.gov.mt.sefaz.itc.util.FormAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnAba;
import br.gov.mt.sefaz.itc.util.dominio.DomnEstadoConservacao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoIPTU;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoUsuario;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoVerificacao;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.FormITC;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.ipva.ValorVenalIntegracaoBe;
import br.gov.mt.sefaz.lpm.model.produtoncm.ProdutoNcmVo;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet responsável por incluir e alterar o Bem Tributável de uma GIAITCD
 * Um bemTributavel pode ser um Imóvel Urbano, Imóvel Rural, ou ainda outro tipo de Bem.
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.17 $
 */
public class BemTributavelManter extends AbstractAbacoServlet implements Form, Flow
{
	private static final int REQUISICAO_LISTAR_BEM = 2;
	private static final int REQUISICAO_ADICIONAR_BEM_TRIBUTAVEL = 3;
	private static final int REQUISICAO_SOLICITAR_ALTERAR_BEM_TRIBUTAVEL = 4;
	private static final int REQUISICAO_EXCLUIR_BEM_TRIBUTAVEL = 5;
	private static final int REQUISICAO_ALTERAR_BEM_TRIBUTAVEL = 6;
	private static final int REQUISICAO_INCLUIR_IMOVEL_URBANO = 7;
	private static final int REQUISICAO_INCLUIR_IMOVEL_RURAL = 8;
	private static final int REQUISICAO_SALVAR_BENS_TRIBUTAVEIS = 9;
	private static final int REQUISICAO_ADICIONAR_BENFEITORIA_IMOVEL_URBANO = 10;
	private static final int REQUISICAO_PESQUISAR_CEP_IMOVEL_URBANO = 11;
	private static final int REQUISICAO_ADICIONAR_BENFEITORIA_IMOVEL_RURAL = 12;
	private static final int REQUISICAO_EXCLUIR_BENFEITORIA_IMOVEL_URBANO = 13;
	private static final int REQUISICAO_EXCLUIR_BENFEITORIA_IMOVEL_RURAL = 14;
	private static final int REQUISICAO_ADICIONAR_CULTURA_IMOVEL_RURAL = 15;
	private static final int REQUISICAO_ALTERAR_CULTURA_IMOVEL_RURAL = 16;
	private static final int REQUISICAO_SOLICITAR_ALTERAR_CULTURA_IMOVEL_RURAL = 17;
	private static final int REQUISICAO_EXCLUIR_CULTURA_IMOVEL_RURAL = 18;
	private static final int REQUISICAO_ADICIONAR_REBANHO_IMOVEL_RURAL = 19;
	private static final int REQUISICAO_SOLICITAR_ALTERAR_REBANHO_IMOVEL_RURAL = 20;
	private static final int REQUISICAO_ALTERAR_REBANHO_IMOVEL_RURAL = 21;
	private static final int REQUISICAO_EXCLUIR_REBANHO_IMOVEL_RURAL = 22;
	private static final int REQUISICAO_PESQUISAR_CEP_IMOVEL_RURAL = 23;
	private static final int REQUISICAO_ADICIONAR_CONSTRUCOES_IMOVEL_RURAL = 24;
	private static final int REQUISICAO_SOLICITAR_ALTERAR_CONSTRUCOES_IMOVEL_RURAL = 25;
	private static final int REQUISICAO_ALTERAR_CONSTRUCOES_IMOVEL_RURAL = 26;
	private static final int REQUISICAO_EXCLUIR_CONSTRUCOES_IMOVEL_RURAL = 27;
	private static final int REQUISICAO_SOLICITAR_ABA_DADOS_GERAIS = 28;
	private static final int REQUISICAO_SOLICITAR_ABA_BENS_TRIBUTAVEIS = 29;
	private static final int REQUISICAO_SOLICITAR_ABA_BENEFICIARIOS = 30;
	private static final int REQUISICAO_SOLICITAR_ABA_DEMONSTRATIVO_DE_CALCULO = 31;
	private static final int REQUISICAO_SOLICITAR_ABA_CONJUGE = 32;
	private static final int REQUISICAO_VOLTAR_IMOVEL_URBANO = 33;
	private static final int REQUISICAO_VOLTAR_IMOVEL_RURAL = 34;
	private static final int REQUISICAO_SOLICITAR_CALCULO_GERAL = 35;
	private static final int REQUISICAO_CALCULAR_VALOR_MERCADO_CULTURA = 36;
	private static final int REQUISICAO_CALCULAR_VALOR_REBANHO = 37;
	private static final int REQUISICAO_ADCIONAR_IMOVEL_URBANO_INTEGRADO = 38;
	private static final int REQUISICAO_PESQUISAR_VEICULO = 39;
	private static final int REQUISICAO_ADCIONAR_VEICULO = 40;
	private static final int REQUISICAO_VOLTAR_FICHA_VEICULO = 41;
	private static final int REQUISICAO_ALTERAR_VEICULO = 42;
	private static final int REQUISICAO_CATEGORIA_VEICULO = 43;
	private static final int REQUISICAO_PESQUISAR_VALOR_REBANHO = 44;
	private static final int REQUISICAO_ADICIONAR_REBANHO = 45;
	private static final int REQUISICAO_ALTERAR_REBANHO = 46;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws IntegracaoException
	 * @throws ConexaoException
	 * @throws ConsultaException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, 
			   IntegracaoException, ConexaoException, ConsultaException, 
			   DadoNecessarioInexistenteException, 
			   PersistenciaException, LogException, AnotacaoException, 
			   ImpossivelCriptografarException, 
			   RegistroNaoPodeSerUtilizadoException, 
			   ParametroObrigatorioException, SQLException, IOException, 
			   Exception
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarManterBemTributavel(request, response);
					break;
				}
			case REQUISICAO_LISTAR_BEM:
				{
					solicitarListarBem(request, response);
					break;
				}
			case REQUISICAO_ADICIONAR_BEM_TRIBUTAVEL:
				{
					adicionarBemTributavel(request, response);
					break;
				}
			case REQUISICAO_ALTERAR_BEM_TRIBUTAVEL:
				{
					alterarBemTributavel(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ALTERAR_BEM_TRIBUTAVEL:
				{
					solicitarAlterarBemTributavel(request, response);
					break;
				}
			case REQUISICAO_EXCLUIR_BEM_TRIBUTAVEL:
				{
					excluirBemTributavel(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_IMOVEL_URBANO:
				{
					incluirImovelUrbano(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_IMOVEL_RURAL:
				{
					incluirImovelRural(request, response);
					break;
				}
			case REQUISICAO_VOLTAR_IMOVEL_URBANO:
				{
					voltarImovelUrbano(request, response);
					break;
				}
			case REQUISICAO_VOLTAR_IMOVEL_RURAL:
				{
					voltarImovelRural(request, response);
					break;
				}
			case REQUISICAO_ADICIONAR_BENFEITORIA_IMOVEL_URBANO:
				{
					adicionarBenfeitoriaImovelUrbano(request, response);
					break;
				}
			case REQUISICAO_EXCLUIR_BENFEITORIA_IMOVEL_URBANO:
				{
					excluirBenfeitoriaImovelUrbano(request, response);
					break;
				}
			case REQUISICAO_PESQUISAR_CEP_IMOVEL_URBANO:
				{
					solicitarPesquisarCepImovelUrbano(request, response);
					break;
				}
			case REQUISICAO_PESQUISAR_CEP_IMOVEL_RURAL:
				{
					solicitarPesquisarCepImovelRural(request, response);
					break;
				}
			case REQUISICAO_ADICIONAR_BENFEITORIA_IMOVEL_RURAL:
				{
					adicionarBenfeitoriaImovelRural(request, response);
					break;
				}
			case REQUISICAO_EXCLUIR_BENFEITORIA_IMOVEL_RURAL:
				{
					excluirBenfeitoriaImovelRural(request, response);
					break;
				}
			case REQUISICAO_SALVAR_BENS_TRIBUTAVEIS:
				{
					salvarBensTributaveis(request, response);
					break;
				}
			case REQUISICAO_ADICIONAR_CULTURA_IMOVEL_RURAL:
				{
					adicionarCulturaImovelRural(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ALTERAR_CULTURA_IMOVEL_RURAL:
				{
					solicitarAlterarCulturaImovelRural(request, response);
					break;
				}
			case REQUISICAO_ALTERAR_CULTURA_IMOVEL_RURAL:
				{
					adicionarCulturaImovelRural(request, response);
					break;
				}
			case REQUISICAO_EXCLUIR_CULTURA_IMOVEL_RURAL:
				{
					excluirCulturaImovelRural(request, response);
					break;
				}
			case REQUISICAO_ADICIONAR_REBANHO_IMOVEL_RURAL:
				{
					adicionarRebanhoImovelRural(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ALTERAR_REBANHO_IMOVEL_RURAL:
				{
					solicitarAlterarRebanhoImovelRural(request, response);
					break;
				}
			case REQUISICAO_ALTERAR_REBANHO_IMOVEL_RURAL:
				{
					adicionarRebanhoImovelRural(request, response);
					break;
				}
			case REQUISICAO_EXCLUIR_REBANHO_IMOVEL_RURAL:
				{
					excluirRebanhoImovelRural(request, response);
					break;
				}
			case REQUISICAO_ADICIONAR_CONSTRUCOES_IMOVEL_RURAL:
				{
					adicionarConstrucoesImovelRural(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_ALTERAR_CONSTRUCOES_IMOVEL_RURAL:
				{
					solicitarAlterarConstrucoesImovelRural(request, response);
					break;
				}
			case REQUISICAO_ALTERAR_CONSTRUCOES_IMOVEL_RURAL:
				{
					adicionarConstrucoesImovelRural(request, response);
					break;
				}
			case REQUISICAO_EXCLUIR_CONSTRUCOES_IMOVEL_RURAL:
				{
					excluirConstrucoesImovelRural(request, response);
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
			case REQUISICAO_SOLICITAR_ABA_CONJUGE:
				{
					solicitaAbaConjuge(request, response);
					break;
				}
			case REQUISICAO_SOLICITAR_CALCULO_GERAL:
				{
					solicitaCalculoGeral(request, response);
					break;
				}
			case REQUISICAO_CALCULAR_VALOR_MERCADO_CULTURA:
				{
					solicitaCalculoValorMercadoCultura(request, response);
					break;
				}
			case REQUISICAO_CALCULAR_VALOR_REBANHO:
				{
					solicitaCalculoValorRebanho(request, response);
					break;
				}
			case REQUISICAO_ADCIONAR_IMOVEL_URBANO_INTEGRADO:
				{
					adicionarImovelUrbanoIntegrado(request, response);
					break;
				}
			case REQUISICAO_PESQUISAR_VEICULO:
				{
					pesquisarVeiculo(request, response);
					break;
				}
			case REQUISICAO_ADCIONAR_VEICULO:
				{
					incluirVeiculo(request, response);
					break;
				}
			case REQUISICAO_VOLTAR_FICHA_VEICULO:
				{
					voltarFichaVeiculo(request, response);
					break;
				}
			case REQUISICAO_ALTERAR_VEICULO:
				{
					alterarVeiculo(request, response);
					break;
				}
			case REQUISICAO_CATEGORIA_VEICULO:
				{
					pesquisarModeloVeiculo(request, response);
					break;
				}
			case REQUISICAO_PESQUISAR_VALOR_REBANHO:
				{
					pesquisarValorRebanho(request, response);
					break;
				}
			case REQUISICAO_ADICIONAR_REBANHO:
				{
					incluirRebanhoLpm(request, response);
					break;
				}
			case REQUISICAO_ALTERAR_REBANHO:
				{
					alterarRebanho(request, response);
					break;
				}
		}
	}

	/**
	 *  Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return int
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(BOTAO_LISTAR_BEM)))
		{
			return REQUISICAO_LISTAR_BEM;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_BEM_TRIBUTAVEL)))
		{
			return REQUISICAO_ADICIONAR_BEM_TRIBUTAVEL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_SOLICITAR_ALTERAR_BEM)))
		{
			return REQUISICAO_SOLICITAR_ALTERAR_BEM_TRIBUTAVEL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_EXCLUIR_BEM)))
		{
			return REQUISICAO_EXCLUIR_BEM_TRIBUTAVEL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_ALTERAR_BEM_TRIBUTAVEL)))
		{
			return REQUISICAO_ALTERAR_BEM_TRIBUTAVEL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_INCLUIR_IMOVEL_URBANO)))
		{
			return REQUISICAO_INCLUIR_IMOVEL_URBANO;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_VOLTAR_IMOVEL_URBANO)))
		{
			return REQUISICAO_VOLTAR_IMOVEL_URBANO;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_INCLUIR_IMOVEL_RURAL)))
		{
			return REQUISICAO_INCLUIR_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_VOLTAR_IMOVEL_RURAL)))
		{
			return REQUISICAO_VOLTAR_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_CEP_IMOVEL_URBANO)))
		{
			return REQUISICAO_PESQUISAR_CEP_IMOVEL_URBANO;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_CEP_IMOVEL_RURAL)))
		{
			return REQUISICAO_PESQUISAR_CEP_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_BENFEITORIA_IMOVEL_URBANO)))
		{
			return REQUISICAO_ADICIONAR_BENFEITORIA_IMOVEL_URBANO;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_BENFEITORIA_IMOVEL_RURAL)))
		{
			return REQUISICAO_ADICIONAR_BENFEITORIA_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_EXCLUIR_BENFEITORIA_IMOVEL_URBANO)))
		{
			return REQUISICAO_EXCLUIR_BENFEITORIA_IMOVEL_URBANO;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_EXCLUIR_BENFEITORIA_IMOVEL_RURAL)))
		{
			return REQUISICAO_EXCLUIR_BENFEITORIA_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_SALVAR_BENS_TRIBUTAVEIS)))
		{
			return REQUISICAO_SALVAR_BENS_TRIBUTAVEIS;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_CULTURA_IMOVEL_RURAL)))
		{
			return REQUISICAO_ADICIONAR_CULTURA_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_SOLICITAR_ALTERAR_CULTURA_IMOVEL_RURAL)))
		{
			return REQUISICAO_SOLICITAR_ALTERAR_CULTURA_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_ALTERAR_CULTURA)))
		{
			return REQUISICAO_ALTERAR_CULTURA_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_EXCLUIR_CULTURA)))
		{
			return REQUISICAO_EXCLUIR_CULTURA_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_REBANHO_IMOVEL_RURAL)))
		{
			return REQUISICAO_ADICIONAR_REBANHO_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_SOLICITAR_ALTERAR_REBANHO_IMOVEL_RURAL)))
		{
			return REQUISICAO_SOLICITAR_ALTERAR_REBANHO_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_ALTERAR_REBANHO_IMOVEL_RURAL)))
		{
			return REQUISICAO_ALTERAR_REBANHO_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_EXCLUIR_REBANHO_IMOVEL_RURAL)))
		{
			return REQUISICAO_EXCLUIR_REBANHO_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_CONSTRUCOES_IMOVEL_RURAL)))
		{
			return REQUISICAO_ADICIONAR_CONSTRUCOES_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_SOLICITAR_ALTERAR_CONSTRUCOES_IMOVEL_RURAL)))
		{
			return REQUISICAO_SOLICITAR_ALTERAR_CONSTRUCOES_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_ALTERAR_CONSTRUCOES_IMOVEL_RURAL)))
		{
			return REQUISICAO_ALTERAR_CONSTRUCOES_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_EXCLUIR_CONSTRUCOES_IMOVEL_RURAL)))
		{
			return REQUISICAO_EXCLUIR_CONSTRUCOES_IMOVEL_RURAL;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_BENS_TRIBUTAVEIS)))
		{
			return REQUISICAO_SOLICITAR_ABA_BENS_TRIBUTAVEIS;
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
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_SOLICITAR_ABA_CONJUGE)))
		{
			return REQUISICAO_SOLICITAR_ABA_CONJUGE;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_FICHA_IMOVEL_RURAL_CALCULO_GERAL)))
		{
			return REQUISICAO_SOLICITAR_CALCULO_GERAL;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_CALCULAR_VALOR_MERCADO_CULTURA)))
		{
			return REQUISICAO_CALCULAR_VALOR_MERCADO_CULTURA;
		}
		else if (Validador.isStringValida(request.getParameter(PARAMETRO_CALCULAR_VALOR_TOTAL_REBANHO)))
		{
			return REQUISICAO_CALCULAR_VALOR_REBANHO;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_IMOVEL_URBANO_INTEGRADO)))
		{
			return REQUISICAO_ADCIONAR_IMOVEL_URBANO_INTEGRADO;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_VEICULO)))
		{
			return REQUISICAO_PESQUISAR_VEICULO;
		}
		else if (Validador.isStringValida(request.getParameter(CAMPO_ALTERAR_VEICULO)))
		{
			return REQUISICAO_ALTERAR_VEICULO;
		}
		else if (Validador.isStringValida(request.getParameter(CAMPO_ADICIONAR_VEICULO)))
		{
			return REQUISICAO_ADCIONAR_VEICULO;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_VOLTAR_FICHA_VEICULO)))
		{
			return REQUISICAO_VOLTAR_FICHA_VEICULO;
		}
		else if (Validador.isStringValida(request.getParameter(CAMPO_CATEGORIA_VEICULO)))
		{
			return REQUISICAO_CATEGORIA_VEICULO;
		}
		else if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR_REBANHO)))
		{
			return REQUISICAO_PESQUISAR_VALOR_REBANHO;
		}
		else if (Validador.isStringValida(request.getParameter(CAMPO_ADICIONAR_REBANHO)))
		{
			return REQUISICAO_ADICIONAR_REBANHO;
		}
		else if (Validador.isStringValida(request.getParameter(CAMPO_ALTERAR_REBANHO)))
		{
			return REQUISICAO_ALTERAR_REBANHO;
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
	private BemTributavelVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		BemTributavelVo bemTributavelVo = (BemTributavelVo) getBuffer(request).getAttribute(BEM_TRIBUTAVEL_VO);
		Validador.validaObjeto(bemTributavelVo);
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		Validador.validaObjeto(giaITCDVo);
		bemTributavelVo.setGiaITCDVo(giaITCDVo);
		if (bemTributavelVo.getGiaITCDVo().isExisteSenha())
		{
			parametroConsulta(request);
		}
		String AbaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
		Validador.validaObjeto(AbaAtual);
		if (AbaAtual.equals(ABA_BENS_TRIBUTAVEIS))
		{
			atualizaAbaBensTributaveis(bemTributavelVo, request);
		}
		return bemTributavelVo;
	}

	/**
	 * @param bemTributavelVo
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void atualizaAbaBensTributaveis(final BemTributavelVo bemTributavelVo, HttpServletRequest request)
	{
		int classificacaoBem = StringUtil.toInt(request.getParameter(CAMPO_SELECT_CLASSIFICACAO_BEM));
		if (Validador.isNumericoValido(classificacaoBem))
		{
			bemTributavelVo.getBemVo().setClassificacaoTipoBem(new DomnTipoBem(classificacaoBem));
			if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_BEM)))
			{
				if (Validador.isStringValida(request.getParameter(CAMPO_HIDDEN_DESCRICAO_TIPO_BEM)))
				{
					bemTributavelVo.getBemVo().setCodigo(StringUtil.toLong(request.getParameter(CAMPO_SELECT_TIPO_BEM)));
					bemTributavelVo.getBemVo().setDescricaoTipoBem(request.getParameter(CAMPO_HIDDEN_DESCRICAO_TIPO_BEM));
					BemTributavelBe.selecionaBem(bemTributavelVo);
				}
			}
		}
		if (Validador.isDominioNumericoValido(bemTributavelVo.getBemVo().getClassificacaoTipoBem()) && !Validador.isNumericoValido(bemTributavelVo.getBemVo().getCodigo()))
		{
			if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_BEM)))
			{
				if (Validador.isStringValida(request.getParameter(CAMPO_HIDDEN_DESCRICAO_TIPO_BEM)))
				{
					bemTributavelVo.getBemVo().setCodigo(StringUtil.toLong(request.getParameter(CAMPO_SELECT_TIPO_BEM)));
					bemTributavelVo.getBemVo().setDescricaoTipoBem(request.getParameter(CAMPO_HIDDEN_DESCRICAO_TIPO_BEM));
					BemTributavelBe.selecionaBem(bemTributavelVo);
				}
			}
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO)))
		{
			bemTributavelVo.setDescricaoBemTributavel(request.getParameter(CAMPO_DESCRICAO));
		}
		double valorMercado = StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_MERCADO));
		if (Validador.isNumericoValido(valorMercado))
		{
			bemTributavelVo.setValorMercado(valorMercado);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_CHECK_ISENCAO_PREVISTA_LEI)))
		{
			bemTributavelVo.setIsencaoPrevista(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_CHECK_ISENCAO_PREVISTA_LEI))));
		}
		else
		{
			bemTributavelVo.setIsencaoPrevista(new DomnSimNao(DomnSimNao.NAO));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_CHECK_BEM_PARTICULAR)))
		{
			bemTributavelVo.setBemParticular(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_CHECK_BEM_PARTICULAR))));
		}
		else
		{
			bemTributavelVo.setBemParticular(new DomnSimNao(DomnSimNao.NAO));
		}
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarManterBemTributavel(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
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
	private void adicionarBemTributavel(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, SQLException, 
			   Exception
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request); //dherkyanx
		ServidorSefazIntegracaoVo servidorSefazIntegracaoVo = bemTributavelVo.getGiaITCDVo().getServidorSefazResponsavelAlteracaoVo();
		try
		{
			if (Validador.isNumericoValido(servidorSefazIntegracaoVo.getNumrMatricula()))
			{
				bemTributavelVo.setTipoUsuario(new DomnTipoUsuario(DomnTipoUsuario.SERVIDOR));
			}
			else
			{
				bemTributavelVo.setTipoUsuario(new DomnTipoUsuario(DomnTipoUsuario.CONTRIBUINTE));
			}
			BemTributavelBe.validaDescricaoDuplicada(bemTributavelVo);
			BemTributavelVo bemTributavelAuxVo = BemTributavelBe.extraiBemTributavel(bemTributavelVo);
			int classificacaoBem = bemTributavelVo.getBemVo().getClassificacaoTipoBem().getDomnValr();
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_INEXISTENTE, request);
			//========PESQUISAR BENFEITORIA
			BenfeitoriaVo benfeitoriaVo = new BenfeitoriaVo();
			benfeitoriaVo.setStatusBenfeitoria(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
			benfeitoriaVo.setParametroConsulta(benfeitoriaVo);
			getBuffer(request).setAttribute(LISTA_BENFEITORIA, benfeitoriaVo, request);
			String codOriginal = JspUtil.getCodigoPermissaoOriginal(request);
			processFlow(FormAcesso.getUrlServletOriginalSemContexto(codOriginal, FormAcesso.NOME_PESQUISAR_BENFEITORIA, request), request, response, INCLUDE);
			Validador.validaObjeto(getBuffer(request).getAttribute(LISTA_BENFEITORIA));
			benfeitoriaVo = (BenfeitoriaVo) getBuffer(request).getAttribute(LISTA_BENFEITORIA);
			//========PESQUISAR CONSTRUCAO
			ConstrucaoVo construcaoVo = new ConstrucaoVo();
			construcaoVo.setStatusConstrucao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
			construcaoVo.setParametroConsulta(construcaoVo);
			getBuffer(request).setAttribute(LISTA_CONSTRUCAO, construcaoVo, request);
			processFlow(FormAcesso.getUrlServletOriginalSemContexto(codOriginal, FormAcesso.NOME_PESQUISAR_CONSTRUCAO, request), request, response, INCLUDE);
			Validador.validaObjeto(getBuffer(request).getAttribute(LISTA_CONSTRUCAO));
			construcaoVo = (ConstrucaoVo) getBuffer(request).getAttribute(LISTA_CONSTRUCAO);
			if (classificacaoBem == DomnTipoBem.IMOVEL_URBANO)
			{
				bemTributavelAuxVo.setFichaImovelVo(new FichaImovelUrbanoVo());
				((FichaImovelUrbanoVo) bemTributavelAuxVo.getFichaImovelVo()).getFichaImovelUrbanoBenfeitoriaVo().setBenfeitoriaVo(benfeitoriaVo);
				((FichaImovelUrbanoVo) bemTributavelAuxVo.getFichaImovelVo()).setConstrucaoVo(construcaoVo);
				getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
				((FichaImovelUrbanoVo) bemTributavelAuxVo.getFichaImovelVo()).setExisteConstrucao(bemTributavelVo.getBemVo().getPossuiConstrucao().is(DomnSimNao.SIM));
				getBuffer(request).setAttribute(FICHA_IMOVEL_URBANO_VO, (FichaImovelUrbanoVo) bemTributavelAuxVo.getFichaImovelVo(), request);
				processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_URBANO, request, response, FORWARD);
			}
			else if (classificacaoBem == DomnTipoBem.IMOVEL_RURAL)
			{
				bemTributavelAuxVo.setFichaImovelVo(new FichaImovelRuralVo());
				((FichaImovelRuralVo) bemTributavelAuxVo.getFichaImovelVo()).getFichaImovelRuralBenfeitoriaVo().setBenfeitoriaVo(benfeitoriaVo);
				((FichaImovelRuralVo) bemTributavelAuxVo.getFichaImovelVo()).getFichaImovelRuralConstrucaoVo().setConstrucaoVo(construcaoVo);
				//========PESQUISAR CULTURA
				CulturaVo culturaVo = new CulturaVo();
				culturaVo.setStatusCultura(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
				culturaVo.setParametroConsulta(culturaVo);
				getBuffer(request).setAttribute(LISTA_CULTURA, culturaVo, request);
				processFlow(FormAcesso.getUrlServletOriginalSemContexto(codOriginal, FormAcesso.NOME_PESQUISAR_CULTURA, request), request, response, INCLUDE);
				Validador.validaObjeto(getBuffer(request).getAttribute(LISTA_CULTURA));
				culturaVo = (CulturaVo) getBuffer(request).getAttribute(LISTA_CULTURA);
				((FichaImovelRuralVo) bemTributavelAuxVo.getFichaImovelVo()).getFichaImovelRuralCulturaVo().setCulturaVo(culturaVo);
				//========PESQUISAR REBANHO
				RebanhoVo rebanhoVo = new RebanhoVo();
				rebanhoVo.setStatusRebanho(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
				rebanhoVo.setParametroConsulta(rebanhoVo);
				getBuffer(request).setAttribute(LISTA_REBANHO, rebanhoVo, request);
				processFlow(FormAcesso.getUrlServletOriginalSemContexto(codOriginal, FormAcesso.NOME_PESQUISAR_REBANHO, request), request, response, INCLUDE);
				Validador.validaObjeto(getBuffer(request).getAttribute(LISTA_REBANHO));
				rebanhoVo = (RebanhoVo) getBuffer(request).getAttribute(LISTA_REBANHO);
				((FichaImovelRuralVo) bemTributavelAuxVo.getFichaImovelVo()).getFichaImovelRuralRebanhoVo().setRebanhoVo(rebanhoVo);
				getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
				bemTributavelAuxVo.getFichaImovelVo().setBemTributavelVo(bemTributavelVo);
				getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, (FichaImovelRuralVo) bemTributavelAuxVo.getFichaImovelVo(), request);
				processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
			}
			else if (classificacaoBem == DomnTipoBem.OUTROS_BENS)
			{
				try
				{
					//Alteração 1.2 Douglas Souza
					//validação para Enviar a pagina de cadastro de Veiculo
					if (bemTributavelVo.getBemVo().getTipoVerificacao().is(DomnTipoVerificacao.IPVA))
					{
						FichaVeiculoVo fichaVeiculoVo = new FichaVeiculoVo();
						bemTributavelAuxVo.setFichaVo(fichaVeiculoVo);
						//Consultando UF
						bemTributavelAuxVo.getFichaVo().getUfsVO().setCollVO(new GIAITCDBe().obterListaUF());
						ValorVenalIntegracaoBe valorVenalIntegracaoBe = new ValorVenalIntegracaoBe();
						//Metodo responsavel por Buscar marca dos veiculo na integração com IPVA.
						fichaVeiculoVo.setMarcaVo(valorVenalIntegracaoBe.pesquisarMarcaVeiculo());
						fichaVeiculoVo.setBemTributavelVo(bemTributavelVo);
						//SERVLET JSON
						getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
						getBuffer(request).setAttribute(FICHA_VEICULO_VO, (FichaVeiculoVo) fichaVeiculoVo, request);
						processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_VEICULO, request, response, FORWARD);
					}
					else if (bemTributavelVo.getBemVo().getTipoVerificacao().is(DomnTipoVerificacao.REBANHO))
					{
						FichaRebanhoLPMVo fichaRebanhoLPMVo = new FichaRebanhoLPMVo();
						bemTributavelAuxVo.setFichaVo(fichaRebanhoLPMVo);
						//Consultando dados do Produto
						new FichaRebanhoLPMBe().listarProdutoAnimalVivoPorUnidadeMedida((FichaRebanhoLPMVo) bemTributavelAuxVo.getFichaVo());
						getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
						bemTributavelAuxVo.getFichaVo().setBemTributavelVo(bemTributavelVo);
						getBuffer(request).setAttribute(FICHA_REBANHO_VO, fichaRebanhoLPMVo, request);
						processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_REBANHO, request, response, FORWARD);
					}
					else if (bemTributavelVo.getBemVo().getTipoVerificacao().is(DomnTipoVerificacao.SEM_VERIFICACAO))
					{
						// Caso seje outros bens e DomnTipoVerificacao.SEM_VERIFICACAO o valores informados e mercado serão iguais.
						bemTributavelAuxVo.setValorInformadoContribuinte(bemTributavelAuxVo.getValorMercado());
					    bemTributavelAuxVo.setConcordaComValorArbitrado(new DomnSimNao(DomnSimNao.SIM));
						bemTributavelVo.setCollVO(BemTributavelBe.adicionaBemTributavelUtil(bemTributavelVo, bemTributavelAuxVo));
						BemTributavelBe.reinicializaBemTributavel(bemTributavelVo); // TODO adicionado dherkyan
						getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
					}
					else
					{
						throw new ParametroObrigatorioException("Não foi possível identificar o tipo de verificação para este bem.");
					}
					if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
					{
						getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
					}
					bemTributavelVo.setAlterar(false);
				}
				catch (ParametroObrigatorioException e)
				{
					getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
					request.setAttribute(EXCEPTION, e);
				}
				getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
				redirecionaJSP(request, response);
			}
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			request.setAttribute(EXCEPTION, e);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			redirecionaJSP(request, response);
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
	private void alterarBemTributavel(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, 
			   IntegracaoException, ParametroObrigatorioException, 
			   SQLException, Exception
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		BemTributavelVo bemTributavelAuxVo = BemTributavelBe.extraiBemTributavel(bemTributavelVo);
		if (bemTributavelVo.getGiaITCDVo() instanceof GIAITCDSeparacaoDivorcioVo)
		{
			GIAITCDSeparacaoDivorcioBe.removeConjugeBem(((GIAITCDSeparacaoDivorcioVo) bemTributavelVo.getGiaITCDVo()).getConjuge1().getCollVO(), bemTributavelAuxVo);
			GIAITCDSeparacaoDivorcioBe.removeConjugeBem(((GIAITCDSeparacaoDivorcioVo) bemTributavelVo.getGiaITCDVo()).getConjuge2().getCollVO(), bemTributavelAuxVo);
		}
		int classificacaoBem = bemTributavelVo.getBemVo().getClassificacaoTipoBem().getDomnValr();
		bemTributavelAuxVo.setFichaImovelVo(bemTributavelVo.getFichaImovelVo());
		bemTributavelAuxVo.getFichaImovelVo().getBemTributavelVo().setAlterar(true);
		bemTributavelAuxVo.setFichaVo(bemTributavelVo.getFichaVo()); //TODO novo
		if (Validador.isObjetoValido(bemTributavelAuxVo.getFichaVo())) //TODO novo
		{
			bemTributavelAuxVo.getFichaVo().getBemTributavelVo().setAlterar(true); //TODO novo
		}
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_INEXISTENTE, request);
		//========PESQUISAR BENFEITORIA
		BenfeitoriaVo benfeitoriaVo = new BenfeitoriaVo();
		benfeitoriaVo.setStatusBenfeitoria(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		benfeitoriaVo.setParametroConsulta(benfeitoriaVo);
		getBuffer(request).setAttribute(LISTA_BENFEITORIA, benfeitoriaVo, request);
		String codOriginal = JspUtil.getCodigoPermissaoOriginal(request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(codOriginal, FormAcesso.NOME_PESQUISAR_BENFEITORIA, request), request, response, INCLUDE);
		Validador.validaObjeto(getBuffer(request).getAttribute(LISTA_BENFEITORIA));
		benfeitoriaVo = (BenfeitoriaVo) getBuffer(request).getAttribute(LISTA_BENFEITORIA);
		//========PESQUISAR CONSTRUCAO
		ConstrucaoVo construcaoVo = new ConstrucaoVo();
		construcaoVo.setStatusConstrucao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		construcaoVo.setParametroConsulta(construcaoVo);
		getBuffer(request).setAttribute(LISTA_CONSTRUCAO, construcaoVo, request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(codOriginal, FormAcesso.NOME_PESQUISAR_CONSTRUCAO, request), request, response, INCLUDE);
		Validador.validaObjeto(getBuffer(request).getAttribute(LISTA_CONSTRUCAO));
		construcaoVo = (ConstrucaoVo) getBuffer(request).getAttribute(LISTA_CONSTRUCAO);
	    getBuffer(request).setAttribute("flagConcordaValorArbitrado", bemTributavelAuxVo.getConcordaComValorArbitrado(), request);
		if (classificacaoBem == DomnTipoBem.IMOVEL_URBANO)
		{
			((FichaImovelUrbanoVo) bemTributavelAuxVo.getFichaImovelVo()).getConstrucaoVo().setCollVO(construcaoVo.getCollVO());
			((FichaImovelUrbanoVo) bemTributavelAuxVo.getFichaImovelVo()).getFichaImovelUrbanoBenfeitoriaVo().getBenfeitoriaVo().setCollVO(benfeitoriaVo.getCollVO());
			((FichaImovelUrbanoVo) bemTributavelAuxVo.getFichaImovelVo()).setExisteConstrucao(bemTributavelVo.getBemVo().getPossuiConstrucao().is(DomnSimNao.SIM));
		    
			getBuffer(request).setAttribute(FICHA_IMOVEL_URBANO_VO, (FichaImovelUrbanoVo) bemTributavelAuxVo.getFichaImovelVo(), request);
			processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_URBANO, request, response, FORWARD);
		}
		else if (classificacaoBem == DomnTipoBem.IMOVEL_RURAL)
		{
			//========PESQUISAR CULTURA
			CulturaVo culturaVo = new CulturaVo();
			culturaVo.setStatusCultura(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
			culturaVo.setParametroConsulta(culturaVo);
			getBuffer(request).setAttribute(LISTA_CULTURA, culturaVo, request);
			processFlow(FormAcesso.getUrlServletOriginalSemContexto(codOriginal, FormAcesso.NOME_PESQUISAR_CULTURA, request), request, response, INCLUDE);
			Validador.validaObjeto(getBuffer(request).getAttribute(LISTA_CULTURA));
			culturaVo = (CulturaVo) getBuffer(request).getAttribute(LISTA_CULTURA);
			//========PESQUISAR REBANHO
			RebanhoVo rebanhoVo = new RebanhoVo();
			rebanhoVo.setStatusRebanho(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
			rebanhoVo.setParametroConsulta(rebanhoVo);
			getBuffer(request).setAttribute(LISTA_REBANHO, rebanhoVo, request);
			processFlow(FormAcesso.getUrlServletOriginalSemContexto(codOriginal, FormAcesso.NOME_PESQUISAR_REBANHO, request), request, response, INCLUDE);
			Validador.validaObjeto(getBuffer(request).getAttribute(LISTA_REBANHO));
			rebanhoVo = (RebanhoVo) getBuffer(request).getAttribute(LISTA_REBANHO);
			((FichaImovelRuralVo) bemTributavelAuxVo.getFichaImovelVo()).getFichaImovelRuralCulturaVo().getCulturaVo().setCollVO(culturaVo.getCollVO());
			((FichaImovelRuralVo) bemTributavelAuxVo.getFichaImovelVo()).getFichaImovelRuralRebanhoVo().getRebanhoVo().setCollVO(rebanhoVo.getCollVO());
			((FichaImovelRuralVo) bemTributavelAuxVo.getFichaImovelVo()).getFichaImovelRuralConstrucaoVo().getConstrucaoVo().setCollVO(construcaoVo.getCollVO());
			((FichaImovelRuralVo) bemTributavelAuxVo.getFichaImovelVo()).getFichaImovelRuralBenfeitoriaVo().getBenfeitoriaVo().setCollVO(benfeitoriaVo.getCollVO());
			getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, (FichaImovelRuralVo) bemTributavelAuxVo.getFichaImovelVo(), request);
			processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
		}
		//Metodo ITCD 1.2
		else if (classificacaoBem == DomnTipoBem.OUTROS_BENS)
		{
			if (bemTributavelVo.getBemVo().getTipoVerificacao().is(DomnTipoVerificacao.IPVA))
			{
				request.getSession().removeAttribute(CAMPO_ADICIONAR_VEICULO);
				getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
				bemTributavelAuxVo.getFichaVo().setBemTributavelVo(bemTributavelVo);
				FichaVeiculoVo fichaVeiculoVo = (FichaVeiculoVo) bemTributavelAuxVo.getFichaVo();
				bemTributavelAuxVo.getFichaVo().getUfsVO().setCollVO(new GIAITCDBe().obterListaUF());
				fichaVeiculoVo.getMarcaVo().setCollVO(new ValorVenalIntegracaoBe().pesquisarMarcaVeiculo().getCollVO());
				if (fichaVeiculoVo.getSiglaUfVeiculo().equalsIgnoreCase(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO))
				{
					new ValorVenalIntegracaoBe().pesquisarValorVenalVeiculo(fichaVeiculoVo);
					fichaVeiculoVo.setExisteValorVeiculo(true);
				}			   
				getBuffer(request).setAttribute(FICHA_VEICULO_VO, fichaVeiculoVo, request);
				processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_VEICULO, request, response, FORWARD);
			}
			else if (bemTributavelVo.getBemVo().getTipoVerificacao().is(DomnTipoVerificacao.REBANHO))
			{
				FichaRebanhoLPMVo fichaRebanhoLPMVo = null;
				FichaRebanhoLPMVo fichaRebanhoConsulta = new FichaRebanhoLPMVo();
				FichaRebanhoLPMBe fichaRebanhoLPMBe = new FichaRebanhoLPMBe();
				fichaRebanhoLPMVo = (FichaRebanhoLPMVo) bemTributavelAuxVo.getFichaVo();
				//Consultando o valor do Rebanho.
				fichaRebanhoLPMBe.listarProdutoAnimalVivoPorUnidadeMedida(fichaRebanhoConsulta);
				//Iterando para obter o valor do Rebanho e repassar para o Vo ProdutoNcmIntegracaoVo. 
				if (Validador.isCollectionValida(fichaRebanhoConsulta.getProdutoNcmIntegracaoVo().getCollVO()))
				{
					Iterator ite = fichaRebanhoConsulta.getProdutoNcmIntegracaoVo().getCollVO().iterator();
					while (ite.hasNext())
					{
						ProdutoNcmVo produtoNcmVoAtual = (ProdutoNcmVo) ite.next();
						if (produtoNcmVoAtual.getCodgProdutoSeqc() == fichaRebanhoLPMVo.getProdutoNcmIntegracaoVo().getCodigo())
						{
							fichaRebanhoLPMVo.setDescricao(produtoNcmVoAtual.getDescProduto());
							fichaRebanhoLPMVo.getProdutoNcmIntegracaoVo().setValorProdutoUnitario(produtoNcmVoAtual.getValorProduto());
							fichaRebanhoLPMVo.getProdutoNcmIntegracaoVo().setCollVO(fichaRebanhoConsulta.getProdutoNcmIntegracaoVo().getCollVO());
							break;
						}
					}
				}
				bemTributavelAuxVo.setFichaVo(fichaRebanhoLPMVo);			   
				getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
				bemTributavelAuxVo.getFichaVo().setBemTributavelVo(bemTributavelVo);
				getBuffer(request).setAttribute(FICHA_REBANHO_VO, (FichaRebanhoLPMVo) bemTributavelAuxVo.getFichaVo(), request);
				processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_REBANHO, request, response, FORWARD);
			}
			else if (bemTributavelVo.getBemVo().getTipoVerificacao().is(DomnTipoVerificacao.SEM_VERIFICACAO))
			{
				getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
			}
			else
			{
				throw new ParametroObrigatorioException("Não foi possível identificar o tipo de verificação para este bem.");
			}
			//bemTributavelVo.setCollVO(BemTributavelBe.adicionaBemTributavel(bemTributavelVo.getCollVO(), bemTributavelAuxVo));
			//BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
			//Remover o bemTributavel Antigo
			if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
			{
				getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			}
			bemTributavelVo.setAlterar(false);
		}
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarListarBem(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, 
			   ObjetoObrigatorioException
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		bemTributavelVo.setBemVo(retornaBemVo(bemTributavelVo, request, response));
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
	}

	/**
	 * @param bemTributavelVo
	 * @param request
	 * @param response
	 * @return
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private BemVo retornaBemVo(final BemTributavelVo bemTributavelVo, HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, 
			   ObjetoObrigatorioException
	{
		BemVo bemVo = new BemVo();
		int classificacaoBem = bemTributavelVo.getBemVo().getClassificacaoTipoBem().getDomnValr();
		bemVo.setClassificacaoTipoBem(new DomnTipoBem(classificacaoBem));
		bemVo.setStatusBem(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		bemVo.setParametroConsulta(bemVo);
		getBuffer(request).setAttribute(LISTA_BEM, bemVo, request);
		String codOriginal = JspUtil.getCodigoPermissaoOriginal(request);
		processFlow(FormAcesso.getUrlServletOriginalSemContexto(codOriginal, FormAcesso.NOME_PESQUISAR_BEM, request), request, response, INCLUDE);
		Validador.validaObjeto(getBuffer(request).getAttribute(LISTA_BEM));
		bemVo = (BemVo) getBuffer(request).getAttribute(LISTA_BEM);
		return bemVo;
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarAlterarBemTributavel(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, 
			   ObjetoObrigatorioException
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		int indice = StringUtil.toInt(request.getParameter(BOTAO_SOLICITAR_ALTERAR_BEM));
		BemTributavelVo bemTributavelAuxVo = (BemTributavelVo) ((ArrayList) bemTributavelVo.getCollVO()).remove(indice);
		if (bemTributavelAuxVo.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_URBANO))
		{
			if (((FichaImovelUrbanoVo) bemTributavelAuxVo.getFichaImovelVo()).getValorInfomado() > 0)
			{
				((FichaImovelUrbanoVo) bemTributavelAuxVo.getFichaImovelVo()).setValorMercadoTotal(((FichaImovelUrbanoVo) bemTributavelAuxVo.getFichaImovelVo()).getValorInfomado());
			}
		}
		//Coloca-se o bemTributavel da coleçao no buffer, para que caso o usuario clique no botao voltar nas fichas
		// o sistema consiga voltar ao bemtributavel antigo.
		BemTributavelVo bemTributavelAntigoVo = BemTributavelBe.extraiBemTributavel(bemTributavelAuxVo);
		bemTributavelAntigoVo.setFichaImovelVo(bemTributavelAuxVo.getFichaImovelVo());
		bemTributavelAntigoVo.setFichaVo(bemTributavelAuxVo.getFichaVo()); // TODO novo
		bemTributavelAntigoVo.getBemVo().setCollVO(bemTributavelAuxVo.getBemVo().getCollVO());
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, bemTributavelAntigoVo, request);
		//------------------
		bemTributavelVo.setBemVo(retornaBemVo(bemTributavelAuxVo, request, response));
		bemTributavelVo.getBemVo().setClassificacaoTipoBem(bemTributavelAuxVo.getBemVo().getClassificacaoTipoBem());
		bemTributavelVo.getBemVo().setDescricaoTipoBem(bemTributavelAuxVo.getBemVo().getDescricaoTipoBem());
		bemTributavelVo.getBemVo().setCodigo(bemTributavelAuxVo.getBemVo().getCodigo());
		bemTributavelVo.getBemVo().setPossuiConstrucao(bemTributavelAuxVo.getBemVo().getPossuiConstrucao());
		bemTributavelVo.setFichaImovelVo(bemTributavelAuxVo.getFichaImovelVo());
		//Ficha VeiculoVo adicionado ITCD 1.2
		bemTributavelVo.setFichaVo(bemTributavelAuxVo.getFichaVo()); // TODO novo
		//ITCD 1.2
		bemTributavelVo.getBemVo().setTipoVerificacao(bemTributavelAuxVo.getBemVo().getTipoVerificacao());
		bemTributavelVo.setDescricaoBemTributavel(bemTributavelAuxVo.getDescricaoBemTributavel());
		bemTributavelVo.setValorMercado(bemTributavelAuxVo.getValorMercado());
		bemTributavelVo.setIsencaoPrevista(bemTributavelAuxVo.getIsencaoPrevista());
		bemTributavelVo.setBemParticular(bemTributavelAuxVo.getBemParticular());
		bemTributavelVo.setTipoUsuario(bemTributavelAuxVo.getTipoUsuario());
		bemTributavelVo.setCodigo(bemTributavelAuxVo.getCodigo());
	    bemTributavelVo.setConcordaComValorArbitrado(bemTributavelAuxVo.getConcordaComValorArbitrado());
		verificarBemInativoAdicionarEmColecao(bemTributavelVo, request, response);
		bemTributavelVo.setAlterar(true);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest) 
	 * @param response (javax.servlet.http.HttpServletResponse) 
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void excluirBemTributavel(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, 
			   ObjetoObrigatorioException
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		int indice = StringUtil.toInt(request.getParameter(BOTAO_EXCLUIR_BEM));
		if (bemTributavelVo.getGiaITCDVo() instanceof GIAITCDSeparacaoDivorcioVo)
		{
			GIAITCDSeparacaoDivorcioBe.removeConjugeBem(((GIAITCDSeparacaoDivorcioVo) bemTributavelVo.getGiaITCDVo()).getConjuge1().getCollVO(), (BemTributavelVo) ((ArrayList) bemTributavelVo.getCollVO()).get(indice));
			GIAITCDSeparacaoDivorcioBe.removeConjugeBem(((GIAITCDSeparacaoDivorcioVo) bemTributavelVo.getGiaITCDVo()).getConjuge2().getCollVO(), (BemTributavelVo) ((ArrayList) bemTributavelVo.getCollVO()).get(indice));
		}
		((ArrayList) bemTributavelVo.getCollVO()).remove(indice);
		GIAITCDBe.alterarAutomaticamenteTipoProtocoloGia(bemTributavelVo.getGiaITCDVo());
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
	}

	private void incluirVeiculo(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		BemTributavelVo bemTributavelAuxVo = BemTributavelBe.extraiBemTributavel(bemTributavelVo);
		FichaVeiculoVo fichaveiculoVo = iniciaFichaVeiculo(request);
		bemTributavelAuxVo.setFichaVo(fichaveiculoVo);
		bemTributavelAuxVo.setValorMercado(fichaveiculoVo.getValorTributavel());
		bemTributavelAuxVo.setValorInformadoContribuinte(fichaveiculoVo.getValorInformado());
		bemTributavelAuxVo.setConcordaComValorArbitrado(fichaveiculoVo.getFlagConcordaValorArbitrado());
		try
		{
			bemTributavelVo.setCollVO(BemTributavelBe.adicionaBemTributavelUtil(bemTributavelVo, bemTributavelAuxVo));
			bemTributavelVo.setFichaVo(new FichaVeiculoVo());
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
			//Remover o bemTributavel Antigo
			if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
			{
				getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			}
			bemTributavelVo.setAlterar(false);
		}
		catch (Exception e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			request.setAttribute(EXCEPTION, e);
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
	}

	private void alterarVeiculo(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		BemTributavelVo bemTributavelAuxVo = BemTributavelBe.extraiBemTributavel(bemTributavelVo);
		FichaVeiculoVo fichaveiculoVo = iniciaFichaVeiculo(request); // TODO novo      
		bemTributavelAuxVo.setFichaVo(fichaveiculoVo); // TODO novo      
		bemTributavelAuxVo.setValorMercado(fichaveiculoVo.getValorTributavel()); // TODO novo 
		bemTributavelAuxVo.setValorInformadoContribuinte(fichaveiculoVo.getValorInformado()); // TODO novo
		bemTributavelAuxVo.setConcordaComValorArbitrado(fichaveiculoVo.getFlagConcordaValorArbitrado());//TODO versão 1.3
		try
		{
			bemTributavelVo.setCollVO(BemTributavelBe.adicionaBemTributavelUtil(bemTributavelVo, bemTributavelAuxVo));
			bemTributavelVo.setFichaVo(bemTributavelAuxVo.getFichaVo()); // TODO novo 
			bemTributavelVo.setFichaVo(new FichaVeiculoVo()); // TODO novo 
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
			//Remover o bemTributavel Antigo
			if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
			{
				getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			}
			bemTributavelVo.setAlterar(false);
		}
		catch (Exception e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			request.setAttribute(EXCEPTION, e);
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
	}

	private void alterarRebanho(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		BemTributavelVo bemTributavelAuxVo = BemTributavelBe.extraiBemTributavel(bemTributavelVo);
		FichaRebanhoLPMVo fichaRebanhoLPMVo = iniciaFichaRebanho(request); // TODO novo      
		bemTributavelAuxVo.setFichaVo(fichaRebanhoLPMVo); // TODO novo      
		bemTributavelAuxVo.setValorMercado(fichaRebanhoLPMVo.getValorCalculado()); // TODO novo 
		bemTributavelAuxVo.setValorInformadoContribuinte(fichaRebanhoLPMVo.getValorInformado()); // TODO novo 
		bemTributavelAuxVo.setConcordaComValorArbitrado(fichaRebanhoLPMVo.getFlagConcordaValorArbitrado()); // TODO novo 
		try
		{
			bemTributavelVo.setCollVO(BemTributavelBe.adicionaBemTributavelUtil(bemTributavelVo, bemTributavelAuxVo));
			bemTributavelVo.setFichaVo(bemTributavelAuxVo.getFichaVo()); // TODO novo 
			bemTributavelVo.setFichaVo(new FichaRebanhoLPMVo()); // TODO novo 
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
			//Remover o bemTributavel Antigo
			if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
			{
				getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			}
			bemTributavelVo.setAlterar(false);
		}
		catch (Exception e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			request.setAttribute(EXCEPTION, e);
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void incluirImovelUrbano(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request); //dherkyanx
		BemTributavelVo bemTributavelAuxVo = BemTributavelBe.extraiBemTributavel(bemTributavelVo);
		FichaImovelUrbanoVo fichaImovelUrbanoVo = iniciaFichaImovelUrbano(request);
		bemTributavelAuxVo.setFichaImovelVo(fichaImovelUrbanoVo);
		try
		{
			if (fichaImovelUrbanoVo.getIptuVo().getTipoITPU().is(DomnTipoIPTU.INTEGRADO))
			{
				BemTributavelBe.validarPercentualTransmitido(fichaImovelUrbanoVo);
			}
			else
			{
				fichaImovelUrbanoVo.setValorPercentualTransmitido(0);
			}
			bemTributavelAuxVo.setValorMercado(fichaImovelUrbanoVo.getValorTributavel());
			bemTributavelAuxVo.setValorInformadoContribuinte(fichaImovelUrbanoVo.getValorInfomado());
		    bemTributavelAuxVo.setConcordaComValorArbitrado(fichaImovelUrbanoVo.getFlagConcordaValorArbitrado());
			bemTributavelVo.setCollVO(BemTributavelBe.adicionaBemTributavelUtil(bemTributavelVo, bemTributavelAuxVo));
			bemTributavelVo.setFichaImovelVo(new FichaImovelVo());
			//TODO remover		   GIAITCDBe.alterarAutomaticamenteTipoProtocoloGia( bemTributavelVo.getGiaITCDVo() );
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
			//Remover o bemTributavel Antigo
			if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
			{
				getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			}
			bemTributavelVo.setAlterar(false);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			request.setAttribute(EXCEPTION, e);
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
	}

	private void pesquisarVeiculo(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, SQLException, 
			   IntegracaoException
	{
		ValorVenalIntegracaoBe valorVenalIntegracaoBe = null;
		//BemTributavelVo bemTributavelVo = controladorInterativoJSP(request); 
		BemTributavelVo bemTributavelVo = (BemTributavelVo) getBuffer(request).getAttribute(BEM_TRIBUTAVEL_VO);
		BemTributavelVo bemTributavelAuxVo = BemTributavelBe.extraiBemTributavel(bemTributavelVo);
		FichaVeiculoVo fichaveiculoVo = iniciaFichaVeiculo(request);
		bemTributavelAuxVo.setFichaVo(fichaveiculoVo);
		try
		{
			valorVenalIntegracaoBe = new ValorVenalIntegracaoBe();
			valorVenalIntegracaoBe.validarCampoFichaVeiculo((FichaVeiculoVo) bemTributavelAuxVo.getFichaVo());
			if (fichaveiculoVo.getSiglaUfVeiculo().endsWith(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO))
			{
				valorVenalIntegracaoBe.pesquisarValorVenalVeiculo((FichaVeiculoVo) bemTributavelAuxVo.getFichaVo());
			}
			else
			{
				valorVenalIntegracaoBe.buscarCategoriaValorVenal((FichaVeiculoVo) bemTributavelAuxVo.getFichaVo());
			}
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			bemTributavelAuxVo.getFichaVo().setBemTributavelVo(bemTributavelVo);
			getBuffer(request).setAttribute(FICHA_VEICULO_VO, (FichaVeiculoVo) bemTributavelAuxVo.getFichaVo(), request);
			processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_VEICULO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelAuxVo, request);
			request.setAttribute(EXCEPTION, e);
		}
	}
	//Douglas Souza
	//Buscando dados do Veiculo no Sistema Ipva itcd 1.2

	private void pesquisarModeloVeiculo(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, SQLException, 
			   IntegracaoException
	{
		ValorVenalIntegracaoBe valorVenalIntegracaoBe = null;
		//BemTributavelVo bemTributavelVo = controladorInterativoJSP(request); 
		BemTributavelVo bemTributavelVo = (BemTributavelVo) getBuffer(request).getAttribute(BEM_TRIBUTAVEL_VO);
		BemTributavelVo bemTributavelAuxVo = BemTributavelBe.extraiBemTributavel(bemTributavelVo);
		FichaVeiculoVo fichaveiculoVo = iniciaFichaVeiculo(request);
		bemTributavelAuxVo.setFichaVo(fichaveiculoVo);
		try
		{
			valorVenalIntegracaoBe = new ValorVenalIntegracaoBe();
			valorVenalIntegracaoBe.validarCampoFichaVeiculo((FichaVeiculoVo) bemTributavelAuxVo.getFichaVo());
			if (!fichaveiculoVo.getSiglaUfVeiculo().equalsIgnoreCase(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO))
			{
				valorVenalIntegracaoBe.pesquisarModeloVeiculo((FichaVeiculoVo) bemTributavelAuxVo.getFichaVo());
			}
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			bemTributavelAuxVo.getFichaVo().setBemTributavelVo(bemTributavelVo);
			getBuffer(request).setAttribute(FICHA_VEICULO_VO, (FichaVeiculoVo) bemTributavelAuxVo.getFichaVo(), request);
			processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_VEICULO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelAuxVo, request);
			request.setAttribute(EXCEPTION, e);
		}
	}

	private void pesquisarValorRebanho(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, SQLException, 
			   IntegracaoException, Exception
	{
		ArrayList listaPrecoRebanho = new ArrayList();
		//BemTributavelVo bemTributavelVo = controladorInterativoJSP(request); 
		FichaRebanhoLPMVo fichaRebanhoConsulta = new FichaRebanhoLPMVo();
		BemTributavelVo bemTributavelVo = (BemTributavelVo) getBuffer(request).getAttribute(BEM_TRIBUTAVEL_VO);
		BemTributavelVo bemTributavelAuxVo = BemTributavelBe.extraiBemTributavel(bemTributavelVo);
		FichaRebanhoLPMVo fichaRebanhoLPMVo = iniciaFichaRebanho(request);
		try
		{
			FichaRebanhoLPMBe fichaRebanhoLPMBe = new FichaRebanhoLPMBe();
			//Consultando dados do Produto
			fichaRebanhoLPMBe.listarProdutoAnimalVivoPorUnidadeMedida(fichaRebanhoConsulta);
			if (Validador.isCollectionValida(fichaRebanhoConsulta.getProdutoNcmIntegracaoVo().getCollVO()))
			{
				Iterator ite = fichaRebanhoConsulta.getProdutoNcmIntegracaoVo().getCollVO().iterator();
				while (ite.hasNext())
				{
					ProdutoNcmVo produtoNcmVoAtual = (ProdutoNcmVo) ite.next();
					if (produtoNcmVoAtual.getCodgProdutoSeqc() == fichaRebanhoLPMVo.getProdutoNcmIntegracaoVo().getCodigo())
					{
						fichaRebanhoLPMVo.setDescricao(produtoNcmVoAtual.getDescProduto());
						fichaRebanhoLPMVo.getProdutoNcmIntegracaoVo().setValorProdutoUnitario(produtoNcmVoAtual.getValorProduto());
					}
				}
			}
			bemTributavelAuxVo.setFichaVo(fichaRebanhoLPMVo);
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			bemTributavelAuxVo.getFichaVo().setBemTributavelVo(bemTributavelVo);
			getBuffer(request).setAttribute(FICHA_REBANHO_VO, (FichaRebanhoLPMVo) bemTributavelAuxVo.getFichaVo(), request);
			processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_REBANHO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelAuxVo, request);
			request.setAttribute(EXCEPTION, e);
		}
	}

	private void incluirRebanhoLpm(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, SQLException, 
			   IntegracaoException, Exception
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		BemTributavelVo bemTributavelAuxVo = BemTributavelBe.extraiBemTributavel(bemTributavelVo);
		FichaRebanhoLPMVo fichaRebanhoLPMVo = iniciaFichaRebanho(request);
		bemTributavelAuxVo.setFichaVo(fichaRebanhoLPMVo);
		bemTributavelAuxVo.setValorMercado(fichaRebanhoLPMVo.getValorCalculado());
		bemTributavelAuxVo.setValorInformadoContribuinte(fichaRebanhoLPMVo.getValorInformado());
	    bemTributavelAuxVo.setConcordaComValorArbitrado(fichaRebanhoLPMVo.getFlagConcordaValorArbitrado());
	    
		try
		{
			bemTributavelVo.setCollVO(BemTributavelBe.adicionaBemTributavelUtil(bemTributavelVo, bemTributavelAuxVo));
			bemTributavelVo.setFichaVo(new FichaRebanhoLPMVo());
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
			//Remover o bemTributavel Antigo
			if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
			{
				getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			}
			bemTributavelVo.setAlterar(false);
		}
		catch (Exception e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			request.setAttribute(EXCEPTION, e);
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void incluirImovelRural(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, SQLException, 
			   ConsultaException, ParametroObrigatorioException, 
			   ConexaoException
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		BemTributavelVo bemTributavelAuxVo = BemTributavelBe.extraiBemTributavel(bemTributavelVo);
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		bemTributavelAuxVo.setFichaImovelVo(fichaImovelRuralVo);
		bemTributavelAuxVo.setValorMercado(fichaImovelRuralVo.getValorTributavel());
		bemTributavelAuxVo.setValorInformadoContribuinte(fichaImovelRuralVo.getValorTotalMercado());
	    bemTributavelAuxVo.setConcordaComValorArbitrado(fichaImovelRuralVo.getFlagConcordaValorArbitrado());
		try
		{
			bemTributavelVo.setCollVO(BemTributavelBe.adicionaBemTributavelUtil(bemTributavelVo, bemTributavelAuxVo));
			bemTributavelVo.setFichaImovelVo(new FichaImovelVo());
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
			//Remover o bemTributavel Antigo
			if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
			{
				getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			}
			bemTributavelVo.setAlterar(false);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			request.setAttribute(EXCEPTION, e);
		}
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void voltarImovelUrbano(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		//Se o usuario clicou no botão voltar, o sistema verifica se tem o bemTributavel antigo no buffer e adiciona ele novamente a coleçao de bens tributaveis
		if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
		{
			bemTributavelVo.getCollVO().add(getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO));
			getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
		}
		bemTributavelVo.setAlterar(false);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
	}

	private void voltarFichaVeiculo(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		//Se o usuario clicou no botão voltar, o sistema verifica se tem o bemTributavel antigo no buffer e adiciona ele novamente a coleçao de bens tributaveis
		if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
		{
			bemTributavelVo.getCollVO().add(getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO));
			getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
		}
		bemTributavelVo.setAlterar(false);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void voltarImovelRural(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		//Se o usuario clicou no botão voltar, o sistema verifica se tem o bemTributavel antigo no buffer e adiciona ele novamente a coleçao de bens tributaveis
		if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
		{
			bemTributavelVo.getCollVO().add(getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO));
			getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
		}
		bemTributavelVo.setAlterar(false);
		getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
		redirecionaJSP(request, response);
	}

	/**
	 * @param request
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private FichaImovelUrbanoVo iniciaFichaImovelUrbano(HttpServletRequest request)
	{
		FichaImovelUrbanoVo fichaImovelUrbanoVo = (FichaImovelUrbanoVo) getBuffer(request).getAttribute(FICHA_IMOVEL_URBANO_VO);
		if (fichaImovelUrbanoVo == null)
		{
			fichaImovelUrbanoVo = new FichaImovelUrbanoVo();
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_LOGRADOURO)))
		{
			fichaImovelUrbanoVo.getEnderecoVo().setTipoLogr(request.getParameter(CAMPO_SELECT_TIPO_LOGRADOURO));
		}
		else
		{
			fichaImovelUrbanoVo.getEnderecoVo().setTipoLogr("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_LOGRADOURO)))
		{
			fichaImovelUrbanoVo.getEnderecoVo().setLogradouro(request.getParameter(CAMPO_LOGRADOURO));
		}
		else
		{
			fichaImovelUrbanoVo.getEnderecoVo().setLogradouro("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_NUMERO)))
		{
			fichaImovelUrbanoVo.getEnderecoVo().setNumrLogradouro(request.getParameter(CAMPO_NUMERO));
		}
		else
		{
			fichaImovelUrbanoVo.getEnderecoVo().setNumrLogradouro("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_BAIRRO)))
		{
			fichaImovelUrbanoVo.getEnderecoVo().setBairro(request.getParameter(CAMPO_BAIRRO));
		}
		else
		{
			fichaImovelUrbanoVo.getEnderecoVo().setBairro("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_PONTO_REFERENCIA)))
		{
			fichaImovelUrbanoVo.getEnderecoVo().setPontoReferencia(request.getParameter(CAMPO_PONTO_REFERENCIA));
		}
		else
		{
			fichaImovelUrbanoVo.getEnderecoVo().setPontoReferencia("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_COMPLEMENTO)))
		{
			fichaImovelUrbanoVo.getEnderecoVo().setComplemento(request.getParameter(CAMPO_COMPLEMENTO));
		}
		else
		{
			fichaImovelUrbanoVo.getEnderecoVo().setComplemento("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_ESTADO_CONSERVACAO)))
		{
			fichaImovelUrbanoVo.setEstadoConservacao(new DomnEstadoConservacao(Integer.parseInt(request.getParameter(CAMPO_SELECT_ESTADO_CONSERVACAO))));
		}
		else
		{
			fichaImovelUrbanoVo.setEstadoConservacao(null);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_AREA_TOTAL_IMOVEL)))
		{
			fichaImovelUrbanoVo.setQuantidadeAreaTotal(StringUtil.monetarioToDouble(request.getParameter(CAMPO_AREA_TOTAL_IMOVEL)));
		}
		else
		{
			fichaImovelUrbanoVo.setQuantidadeAreaTotal(0);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_AREA_CONSTRUIDA)))
		{
			fichaImovelUrbanoVo.setQuantidadeAreaConstruida(StringUtil.monetarioToDouble(request.getParameter(CAMPO_AREA_CONSTRUIDA)));
		}
		else
		{
			fichaImovelUrbanoVo.setQuantidadeAreaConstruida(0);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_CONSTRUCAO)))
		{
			fichaImovelUrbanoVo.getConstrucaoVo().setCodigo(StringUtil.toLong(request.getParameter(CAMPO_SELECT_TIPO_CONSTRUCAO)));
		}
		else
		{
			fichaImovelUrbanoVo.getConstrucaoVo().setCodigo(0);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_ACESSO)))
		{
			fichaImovelUrbanoVo.setTipoAcesso(new DomnTipoAcesso(StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_ACESSO))));
		}
		else
		{
			fichaImovelUrbanoVo.setTipoAcesso(null);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_BENFEITORIA)))
		{
			fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().getBenfeitoriaVo().setCodigo(StringUtil.toLong(request.getParameter(CAMPO_SELECT_BENFEITORIA)));
		}
		else
		{
			fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().getBenfeitoriaVo().setCodigo(0);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA_URBANO)))
		{
			fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().setDescricaoComplementarBenfeitoria(request.getParameter(CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA_URBANO));
		}
		else
		{
			fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().setDescricaoComplementarBenfeitoria("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_HIDDEN_DESCRICAO_BENFEITORIA)))
		{
			fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().getBenfeitoriaVo().setDescricaoBenfeitoria(request.getParameter(CAMPO_HIDDEN_DESCRICAO_BENFEITORIA));
		}
		else
		{
			fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().getBenfeitoriaVo().setDescricaoBenfeitoria("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_TOTAL)))
		{
			fichaImovelUrbanoVo.setValorMercadoTotal(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_TOTAL)));
		}
		else
		{
			fichaImovelUrbanoVo.setValorMercadoTotal(0);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_VENAL)))
		{
			fichaImovelUrbanoVo.setValorVenalIptu(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_VENAL)));
		}
		else
		{
			fichaImovelUrbanoVo.setValorVenalIptu(0);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_CEP)))
		{
			fichaImovelUrbanoVo.getEnderecoVo().getCep().setCodgCep(StringUtil.toInteger(request.getParameter(CAMPO_CEP)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_NUMERO_MATRICULA_INSCRICAO_IMOVEL)))
		{
			fichaImovelUrbanoVo.getIptuPrefeituraVo().setNumrInscricaoImovel((request.getParameter(CAMPO_NUMERO_MATRICULA_INSCRICAO_IMOVEL)));
		}
		else
		{
			fichaImovelUrbanoVo.getIptuPrefeituraVo().setNumrInscricaoImovel("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_PERCENTUAL_TRANSMITIDO_IMOVEL)))
		{
			fichaImovelUrbanoVo.setValorPercentualTransmitido(StringUtil.monetarioToDouble(request.getParameter(CAMPO_PERCENTUAL_TRANSMITIDO_IMOVEL)));
		}
		fichaImovelUrbanoVo.setValorInfomado(0);
	    if (Validador.isStringValida(request.getParameter(CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE)))
	    {
	        fichaImovelUrbanoVo.setFlagConcordaValorArbitrado(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE))));
	    }
	    else
	    {
	        fichaImovelUrbanoVo.setFlagConcordaValorArbitrado(new DomnSimNao(DomnSimNao.SIM));
	    }
		return fichaImovelUrbanoVo;
	}
	//TODO novo (alterações no método)

	private FichaVeiculoVo iniciaFichaVeiculo(HttpServletRequest request)
	{
		FichaVeiculoVo fichaVeiculoVo = (FichaVeiculoVo) getBuffer(request).getAttribute(FICHA_VEICULO_VO);
		if (fichaVeiculoVo == null)
		{
			fichaVeiculoVo = new FichaVeiculoVo();
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_PLACA_VEICULO)))
		{
			fichaVeiculoVo.setNumrPlaca((request.getParameter(CAMPO_PLACA_VEICULO)));
		}
		else
		{
			fichaVeiculoVo.setNumrPlaca("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_RENAVAN_VEICULO)))
		{
			fichaVeiculoVo.setNumrRenavam(Long.parseLong(request.getParameter(CAMPO_RENAVAN_VEICULO)));
		}
		else
		{
			fichaVeiculoVo.setNumrRenavam(0);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_UF_VEICULO)))
		{
			fichaVeiculoVo.setSiglaUfVeiculo((request.getParameter(CAMPO_UF_VEICULO)));
		}
		else
		{
			fichaVeiculoVo.setSiglaUfVeiculo("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_MARCA_MODELO_VEICULO)))
		{
			fichaVeiculoVo.getMarcaModelo().setDescMarcaModelo((request.getParameter(CAMPO_MARCA_MODELO_VEICULO)));
		}
		else
		{
			fichaVeiculoVo.getMarcaModelo().setDescMarcaModelo("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_ANO_FABRICACAO_VEICULO)))
		{
			fichaVeiculoVo.setAnoFabricacao(Integer.parseInt(request.getParameter(CAMPO_ANO_FABRICACAO_VEICULO)));
		}
		else
		{
			fichaVeiculoVo.setAnoFabricacao(0);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_NOME_PROPRIETARIO_VEICULO)))
		{
			fichaVeiculoVo.setNomeProprietario((request.getParameter(CAMPO_NOME_PROPRIETARIO_VEICULO)));
		}
		else
		{
			fichaVeiculoVo.setNomeProprietario("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_DECLARADO)))
		{
			fichaVeiculoVo.setValorInformado(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_DECLARADO)));
		}
		else
		{
			fichaVeiculoVo.setValorInformado(0d);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_INSENCAO_PREVISTA)))
		{
			fichaVeiculoVo.setIsencaoPrevista(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_INSENCAO_PREVISTA))));
		}
		else
		{
			fichaVeiculoVo.setIsencaoPrevista(null);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_UF_VEICULO_OUTROS_ESTADOS)))
		{
			fichaVeiculoVo.getCategoria().setCodgCategoria(Integer.parseInt(request.getParameter(CAMPO_UF_VEICULO_OUTROS_ESTADOS)));
		}
		else
		{
			fichaVeiculoVo.getCategoria().setCodgCategoria(0);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_ANO_FABRICACAO_VEICULO)))
		{
			fichaVeiculoVo.setAnoFabricacao((Integer.parseInt(request.getParameter(CAMPO_ANO_FABRICACAO_VEICULO))));
		}
		else
		{
			fichaVeiculoVo.setAnoFabricacao(0);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_MARCA_VEICULO)))
		{
			fichaVeiculoVo.getMarcaVo().setCodMarca(Integer.parseInt(request.getParameter(CAMPO_MARCA_VEICULO)));
		}
		else
		{
			fichaVeiculoVo.getMarcaVo().setCodMarca(0);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE)))
		{
			fichaVeiculoVo.setFlagConcordaValorArbitrado(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE))));
		}
		else
		{
			fichaVeiculoVo.setFlagConcordaValorArbitrado(new DomnSimNao(DomnSimNao.SIM));
		}
		return fichaVeiculoVo;
	}

	/**
	 * @param request   
	 * @implemented by Douglas souza
	 */
	private FichaRebanhoLPMVo iniciaFichaRebanho(HttpServletRequest request)
	{
		FichaRebanhoLPMVo fichaRebanhoLPMVo = (FichaRebanhoLPMVo) getBuffer(request).getAttribute(FICHA_REBANHO_VO);
		if (fichaRebanhoLPMVo == null)
		{
			fichaRebanhoLPMVo = new FichaRebanhoLPMVo();
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_LISTA_PRECO_MINIMO)))
		{
			fichaRebanhoLPMVo.getProdutoNcmIntegracaoVo().setCodigo((Integer.parseInt(request.getParameter(CAMPO_LISTA_PRECO_MINIMO))));
		}
		else
		{
			fichaRebanhoLPMVo.getProdutoNcmIntegracaoVo().setCodigo(0);
		}
		if (Validador.isStringValida(request.getParameter(QUANTIDADE_DE_CABECA)))
		{
			fichaRebanhoLPMVo.setQuantidade(Integer.parseInt(request.getParameter(QUANTIDADE_DE_CABECA)));
		}
		else
		{
			fichaRebanhoLPMVo.setQuantidade(0);
		}
		if (Validador.isStringValida(request.getParameter(VALOR_DECLARADO_REBANHO)))
		{
			fichaRebanhoLPMVo.setValorInformado(StringUtil.monetarioToDouble(request.getParameter(VALOR_DECLARADO_REBANHO)));
			
		}
		else
		{
			fichaRebanhoLPMVo.setValorInformado(0d);
		}
		if (Validador.isStringValida(request.getParameter(VALOR_IPM_REBANHO)))
		{
			fichaRebanhoLPMVo.setValorUnitario(Double.parseDouble(request.getParameter(VALOR_IPM_REBANHO)));
		}
		else
		{
			fichaRebanhoLPMVo.setValorUnitario(0d);
		}
	    if (Validador.isStringValida(request.getParameter(CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE)))
	    {
	        fichaRebanhoLPMVo.setFlagConcordaValorArbitrado(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE))));
	    }
	    else
	    {
	        fichaRebanhoLPMVo.setFlagConcordaValorArbitrado(new DomnSimNao(DomnSimNao.SIM));
	    }
		return fichaRebanhoLPMVo;
	}

	/**
	 * @param request
	 * @implemented by João Batista Padilha e Silva
	 */
	private FichaImovelRuralVo iniciaFichaImovelRural(HttpServletRequest request)
	{
		FichaImovelRuralVo fichaImovelRuralVo = (FichaImovelRuralVo) getBuffer(request).getAttribute(FICHA_IMOVEL_RURAL_VO);
		if (fichaImovelRuralVo == null)
		{
			fichaImovelRuralVo = new FichaImovelRuralVo();
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DENOMINACAO_IMOVEL)))
		{
			fichaImovelRuralVo.setDescricaoDenominacao(request.getParameter(CAMPO_DENOMINACAO_IMOVEL));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_LOGRADOURO)))
		{
			fichaImovelRuralVo.getEnderecoVo().setLogradouro(request.getParameter(CAMPO_LOGRADOURO));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_PONTO_REFERENCIA)))
		{
			fichaImovelRuralVo.getEnderecoVo().setPontoReferencia(request.getParameter(CAMPO_PONTO_REFERENCIA));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_KM_PERIMETRO_URBANO)))
		{
			fichaImovelRuralVo.setQuantidadeDistancia(StringUtil.monetarioToDouble(request.getParameter(CAMPO_KM_PERIMETRO_URBANO)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_KM_VIA_PAVIMENTADA)))
		{
			fichaImovelRuralVo.setDistanciaAsfalto(StringUtil.monetarioToDouble(request.getParameter(CAMPO_KM_VIA_PAVIMENTADA)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_AREA_TOTAL_IMOVEL)))
		{
			fichaImovelRuralVo.setAreaTotal(StringUtil.monetarioToDouble(request.getParameter(CAMPO_AREA_TOTAL_IMOVEL)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_NUMERO_INDEA)))
		{
			fichaImovelRuralVo.setNumericoIndea(StringUtil.toLong(request.getParameter(CAMPO_NUMERO_INDEA)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_CODIGO_RECEITA_FEDERAL)))
		{
			fichaImovelRuralVo.setCodigoReceitaFederal(StringUtil.toLong(request.getParameter(CAMPO_CODIGO_RECEITA_FEDERAL)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_CULTURA)))
		{
			fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCulturaVo().setCodigo(StringUtil.toInt(request.getParameter(CAMPO_SELECT_CULTURA)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_HIDDEN_DESCRICAO_CULTURA)))
		{
			fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCulturaVo().setDescricaoCultura(request.getParameter(CAMPO_HIDDEN_DESCRICAO_CULTURA));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_COMPLEMENTAR_CULTURA)))
		{
			fichaImovelRuralVo.getFichaImovelRuralCulturaVo().setDescricaoComplementarCultura(request.getParameter(CAMPO_DESCRICAO_COMPLEMENTAR_CULTURA));
		}
		else
		{
			fichaImovelRuralVo.getFichaImovelRuralCulturaVo().setDescricaoComplementarCultura("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_AREA_CULTIVADA)))
		{
			fichaImovelRuralVo.getFichaImovelRuralCulturaVo().setAreaCultivada(StringUtil.monetarioToDouble(request.getParameter(CAMPO_AREA_CULTIVADA)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_HECTARE)))
		{
			fichaImovelRuralVo.getFichaImovelRuralCulturaVo().setValorHectare(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_HECTARE)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_MERCADO_CULTURA)))
		{
			fichaImovelRuralVo.getFichaImovelRuralCulturaVo().setValorMercado(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_MERCADO_CULTURA)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_POSSUI_PASTAGENS_NATURAIS_ARTIFICIAIS)))
		{
			fichaImovelRuralVo.setSituacaoPastagem(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_SELECT_POSSUI_PASTAGENS_NATURAIS_ARTIFICIAIS))));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_TAMANHO_PASTAGENS)))
		{
			fichaImovelRuralVo.setAreaPastagem(StringUtil.monetarioToDouble(request.getParameter(CAMPO_TAMANHO_PASTAGENS)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_PASTAGENS)))
		{
			fichaImovelRuralVo.setValorPastagem(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_PASTAGENS)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_POSSUI_JAZIDAS)))
		{
			fichaImovelRuralVo.setSituacaoAcessaoNatural(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_SELECT_POSSUI_JAZIDAS))));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_ACESSOES_NATURAIS)))
		{
			fichaImovelRuralVo.setValorAcessaoNatural(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_ACESSOES_NATURAIS)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_REBANHO)))
		{
			fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getRebanhoVo().setCodigo(StringUtil.toLong(request.getParameter(CAMPO_SELECT_TIPO_REBANHO)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_HIDDEN_DESCRICAO_REBANHO)))
		{
			fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getRebanhoVo().setDescricaoRebanho(request.getParameter(CAMPO_HIDDEN_DESCRICAO_REBANHO));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_REBANHO)))
		{
			fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().setDescricaoRebanho(request.getParameter(CAMPO_DESCRICAO_REBANHO));
		}
		else
		{
			fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().setDescricaoRebanho("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_QUANTIDADE_REBANHO)))
		{
			fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().setQuantidadeRebanho(StringUtil.toLong(request.getParameter(CAMPO_QUANTIDADE_REBANHO)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_MERCADO_REBANHO)))
		{
			fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().setValorMercado(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_MERCADO_REBANHO)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_CONSTRUCAO)))
		{
			fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getConstrucaoVo().setCodigo(StringUtil.toLong(request.getParameter(CAMPO_SELECT_TIPO_CONSTRUCAO)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_HIDDEN_DESCRICAO_CONSTRUCAO)))
		{
			fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getConstrucaoVo().setDescricaoConstrucao(request.getParameter(CAMPO_HIDDEN_DESCRICAO_CONSTRUCAO));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_CONSTRUCAO)))
		{
			fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().setDescricaoConstrucao(request.getParameter(CAMPO_DESCRICAO_CONSTRUCAO));
		}
		else
		{
			fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().setDescricaoConstrucao("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_ESTADO_CONSERVACAO)))
		{
			fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().setSituacaoEstadoConservacao(new DomnEstadoConservacao(StringUtil.toInt(request.getParameter(CAMPO_SELECT_ESTADO_CONSERVACAO))));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_MERCADO_CONSTRUCAO)))
		{
			fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().setValorMercado(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_MERCADO_CONSTRUCAO)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_BENFEITORIA)))
		{
			fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo().getBenfeitoriaVo().setCodigo(StringUtil.toLong(request.getParameter(CAMPO_SELECT_BENFEITORIA)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_HIDDEN_DESCRICAO_BENFEITORIA)))
		{
			fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo().getBenfeitoriaVo().setDescricaoBenfeitoria(request.getParameter(CAMPO_HIDDEN_DESCRICAO_BENFEITORIA));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA)))
		{
			fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo().setDescricaoComplementarBenfeitoria(request.getParameter(CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA));
		}
		else
		{
			fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo().setDescricaoComplementarBenfeitoria("");
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_IMOVEL)))
		{		   
			fichaImovelRuralVo.setValorMercadoImovel(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_IMOVEL)));
		}else{
         fichaImovelRuralVo.setValorMercadoImovel(0.0);
      }
		if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_MAQUINAS_IMPLEMENTOS_AGRICOLAS)))
		{
			fichaImovelRuralVo.setValorMaquinaEquipamento(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_MAQUINAS_IMPLEMENTOS_AGRICOLAS)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_OUTROS_VALORES)))
		{         
			fichaImovelRuralVo.setValorOutro(StringUtil.monetarioToDouble(request.getParameter(CAMPO_OUTROS_VALORES)));
		}else{
         fichaImovelRuralVo.setValorOutro(0.0);
      } 
		if (Validador.isStringValida(request.getParameter(CAMPO_VALOR_VENAL)))
		{
			fichaImovelRuralVo.setValorITR(StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_VENAL)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_CEP)))
		{
			fichaImovelRuralVo.getEnderecoVo().getCep().setCodgCep(StringUtil.toInteger(request.getParameter(CAMPO_CEP)));
		}
	    if (Validador.isStringValida(request.getParameter(CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE)))
	    {
	        fichaImovelRuralVo.setFlagConcordaValorArbitrado(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE))));
	    }
	    else
	    {
	        fichaImovelRuralVo.setFlagConcordaValorArbitrado(new DomnSimNao(DomnSimNao.SIM));
	    }
		return fichaImovelRuralVo;
	}

	/**
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void salvarBensTributaveis(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   ConexaoException, PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, ConsultaException, 
			   IntegracaoException, DadoNecessarioInexistenteException, 
			   PersistenciaException, LogException, AnotacaoException, 
			   ImpossivelCriptografarException, 
			   RegistroNaoPodeSerUtilizadoException
	{
		GIAITCDVo giaITCDVo = new GIAITCDVo();
		DomnTipoProcesso tipoProcesso = retornaTipoProcessoUtil(request); // retornaTipoProcesso(request);
		if (tipoProcesso.is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
		{
			giaITCDVo = (GIAITCDInventarioArrolamentoVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		else if (tipoProcesso.is(DomnTipoProcesso.DOACAO))
		{
			giaITCDVo = (GIAITCDDoacaoVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		else if (tipoProcesso.is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
		{
			giaITCDVo = (GIAITCDSeparacaoDivorcioVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
      bemTributavelVo.setValorMercado(0.0);	   
	   BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
		//Se o usuario clicou no botão salvar, o sistema verifica se tem o bemTributavel antigo no buffer e adiciona ele novamente a coleçao de bens tributaveis
		if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
		{
			bemTributavelVo.getCollVO().add(getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO));
			getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
			bemTributavelVo.setAlterar(false);
		}
		GIAITCDBe giaITCDBe = null;
		if (!giaITCDVo.isUsuarioServidor())
		{
			if (giaITCDVo.isExisteSenha())
			{
				try
				{
					giaITCDBe = new GIAITCDBe();
					try
					{
						giaITCDVo.setBemTributavelVo(bemTributavelVo);
						//codigo do LOG
						obterInformacoesLogSefaz(request, giaITCDVo);
						//fim do código do LOG           
						if (Validador.isNumericoValido(giaITCDVo.getServidorSefazIntegracaoVo().getNumrMatricula()))
						{
							giaITCDVo.getBemTributavelVo().setTipoUsuario(new DomnTipoUsuario(DomnTipoUsuario.SERVIDOR));
						}
						else
						{
							giaITCDVo.getBemTributavelVo().setTipoUsuario(new DomnTipoUsuario(DomnTipoUsuario.CONTRIBUINTE));
						}
						giaITCDBe.solicitarManterGIAITCD(giaITCDVo);
						//             if (Validador.isStringValida(request.getParameter(BOTAO_SALVAR_BENS_TRIBUTAVEIS)))
						//                {
						//                   for (Iterator iteBemTrib = giaITCDVo.getBemTributavelVo().getCollVO().iterator(); iteBemTrib.hasNext(); )
						//                   {
						//                      BemTributavelVo bemTributavel = (BemTributavelVo) iteBemTrib.next();
						//                      bemTributavel.getGiaITCDVo().setCodigo(giaITCDVo.getCodigo());
						//                      (new BemTributavelBe()).incluirBemTributavel(bemTributavel);
						//                   }
						//                }
						getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
						getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, giaITCDVo.getBemTributavelVo(), request);
						getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
					}
					catch (RegistroExistenteException e)
					{
						getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
						getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, giaITCDVo.getBemTributavelVo(), request);
						request.setAttribute(EXCEPTION, e);
					}
					catch (ParametroObrigatorioException e)
					{
						getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
						getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, giaITCDVo.getBemTributavelVo(), request);
						request.setAttribute(EXCEPTION, e);
					}
					catch (IOException e)
					{
						getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
						getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, giaITCDVo.getBemTributavelVo(), request);
						request.setAttribute(EXCEPTION, e);
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
				redirecionaJSP(request, response);
			}
			else
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
				getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, giaITCDVo.getBemTributavelVo(), request);
				processFlow(VIEW_CADASTRAR_SENHA_GIAITCD, request, response, FORWARD);
			}
		}
		else
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, giaITCDVo.getBemTributavelVo(), request);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
			redirecionaJSP(request, response);
		}
	}

	/**
	 * Método responsável por adicionar uma benfeitoria em uma ficha de imóvel urbano.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void adicionarBenfeitoriaImovelUrbano(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		FichaImovelUrbanoVo fichaImovelUrbanoVo = iniciaFichaImovelUrbano(request);
		try
		{
			FichaImovelUrbanoBenfeitoriaBe.adicionarBenfeitoriaImovelUrbano(fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo());
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
		}
		getBuffer(request).setAttribute(FICHA_IMOVEL_URBANO_VO, fichaImovelUrbanoVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_URBANO, request, response, FORWARD);
	}

	/**
	 * Método responsável por adicionar uma benfeitoria em uma ficha de imóvel rural.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void adicionarBenfeitoriaImovelRural(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		try
		{
			FichaImovelRuralBenfeitoriaBe.adicionarBenfeitoriaImovelRural(fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo());
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
		}
		request.setAttribute(EXIBIR, BENFEITORIA_VO); // usado para controle na jsp para mostrar os dados da benfeitoria.
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * Método responsável por excluir uma benfeitoria de uma ficha de imóvel urbano.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void excluirBenfeitoriaImovelUrbano(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		FichaImovelUrbanoVo fichaImovelUrbanoVo = iniciaFichaImovelUrbano(request);
		int indice = StringUtil.toInt(request.getParameter(BOTAO_EXCLUIR_BENFEITORIA_IMOVEL_URBANO));
		((ArrayList) fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().getCollVO()).remove(indice);
		getBuffer(request).setAttribute(FICHA_IMOVEL_URBANO_VO, fichaImovelUrbanoVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_URBANO, request, response, FORWARD);
	}

	/**
	 * Método responsável por excluir uma benfeitoria de uma ficha de imóvel rural.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void excluirBenfeitoriaImovelRural(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		int indice = StringUtil.toInt(request.getParameter(BOTAO_EXCLUIR_BENFEITORIA_IMOVEL_RURAL));
		((ArrayList) fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo().getCollVO()).remove(indice);
		request.setAttribute(EXIBIR, BENFEITORIA_VO); // usado para controle na jsp para mostrar os dados da benfeitoria.
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * Método responsável por adicionar uma cultura a uma ficha de imóvel rural.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by João Batista Padilha e Silva
	 */
	private void adicionarCulturaImovelRural(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, 
			   ObjetoObrigatorioException
	{
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		try
		{
			FichaImovelRuralCulturaBe.adicionarCulturaImovelRural(fichaImovelRuralVo.getFichaImovelRuralCulturaVo());
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
		}
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		request.setAttribute(EXIBIR, CULTURA_VO); // usado para controle na jsp para mostrar os dados da cultura.
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * Método responsável por preparar a alteração de uma cultura.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitarAlterarCulturaImovelRural(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		int indice = StringUtil.toInt(request.getParameter(BOTAO_SOLICITAR_ALTERAR_CULTURA_IMOVEL_RURAL));
		FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo = (FichaImovelRuralCulturaVo) ((ArrayList) fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCollVO()).remove(indice);
		fichaImovelRuralVo.getFichaImovelRuralCulturaVo().setCodigo(fichaImovelRuralCulturaVo.getCodigo());
		fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCulturaVo().setCodigo(fichaImovelRuralCulturaVo.getCulturaVo().getCodigo());
		fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCulturaVo().setDescricaoCultura(fichaImovelRuralCulturaVo.getCulturaVo().getDescricaoCultura());
		fichaImovelRuralVo.getFichaImovelRuralCulturaVo().setAreaCultivada(fichaImovelRuralCulturaVo.getAreaCultivada());
		fichaImovelRuralVo.getFichaImovelRuralCulturaVo().setValorMercado(fichaImovelRuralCulturaVo.getValorMercado());
		fichaImovelRuralVo.getFichaImovelRuralCulturaVo().setValorHectare(fichaImovelRuralCulturaVo.getValorHectare());
		fichaImovelRuralVo.getFichaImovelRuralCulturaVo().setDescricaoComplementarCultura(fichaImovelRuralCulturaVo.getDescricaoComplementarCultura());
		request.setAttribute(ALTERAR_CULTURA, ALTERAR_CULTURA);
		request.setAttribute(EXIBIR, CULTURA_VO); // usado para controle na jsp para mostrar os dados da cultura.
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * Método responsável por excluir uma cultura de uma ficha de imóvel rural.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by João Batista Padilha e Silva
	 */
	private void excluirCulturaImovelRural(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		int indice = StringUtil.toInt(request.getParameter(BOTAO_EXCLUIR_CULTURA));
		FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo = (FichaImovelRuralCulturaVo) ((ArrayList) fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCollVO()).remove(indice);
		FichaImovelRuralCulturaBe.reinicializaCulturaImovelRural(fichaImovelRuralVo.getFichaImovelRuralCulturaVo());
		request.setAttribute(EXIBIR, CULTURA_VO); // usado para controle na jsp para mostrar os dados da cultura.
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * Método responsável por adicionar um rebanho para uma ficha de imóvel rural.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by João Batista Padilha e Silva
	 */
	private void adicionarRebanhoImovelRural(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, 
			   ObjetoObrigatorioException
	{
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		try
		{
			FichaImovelRuralRebanhoBe.adicionarRebanhoImovelRural(fichaImovelRuralVo.getFichaImovelRuralRebanhoVo());
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
		}
		request.setAttribute(EXIBIR, REBANHO_VO); // usado para controle na jsp para mostrar os dados da rebanho.
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * Método responsável por preparar a alteração de um rebanho para uma ficha de imóvel rural.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitarAlterarRebanhoImovelRural(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		int indice = StringUtil.toInt(request.getParameter(BOTAO_SOLICITAR_ALTERAR_REBANHO_IMOVEL_RURAL));
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo = (FichaImovelRuralRebanhoVo) ((ArrayList) fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getCollVO()).remove(indice);
		fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().setCodigo(fichaImovelRuralRebanhoVo.getCodigo()); // Repassando o código do rebanho.
		fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getRebanhoVo().setCodigo(fichaImovelRuralRebanhoVo.getRebanhoVo().getCodigo());
		fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getRebanhoVo().setDescricaoRebanho(fichaImovelRuralRebanhoVo.getRebanhoVo().getDescricaoRebanho());
		fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().setDescricaoRebanho(fichaImovelRuralRebanhoVo.getDescricaoRebanho());
		fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().setQuantidadeRebanho(fichaImovelRuralRebanhoVo.getQuantidadeRebanho());
		fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().setValorMercado(fichaImovelRuralRebanhoVo.getValorMercado());
		fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().setValorTotal(fichaImovelRuralRebanhoVo.getValorTotal());
		request.setAttribute(ALTERAR_REBANHO, ALTERAR_REBANHO);
		request.setAttribute(EXIBIR, REBANHO_VO); // usado para controle na jsp para mostrar os dados da rebanho.
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * Método responsável por excluir um rebanho de uma ficha de imóvel rural.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void excluirRebanhoImovelRural(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		int indice = StringUtil.toInt(request.getParameter(BOTAO_EXCLUIR_REBANHO_IMOVEL_RURAL));
		FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo = (FichaImovelRuralRebanhoVo) ((ArrayList) fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getCollVO()).remove(indice);
		FichaImovelRuralRebanhoBe.reinicializaRebanhoImovelRural(fichaImovelRuralVo.getFichaImovelRuralRebanhoVo());
		request.setAttribute(EXIBIR, REBANHO_VO); // usado para controle na jsp para mostrar os dados da rebanho.
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * Método responsável por adicionar uma construção em uma ficha de imóvel rural.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by João Batista Padilha e Silva
	 */
	private void adicionarConstrucoesImovelRural(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, 
			   ObjetoObrigatorioException
	{
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		try
		{
			FichaImovelRuralConstrucaoBe.adicionarConstrucoesImovelRural(fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo());
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
		}
		request.setAttribute(EXIBIR, CONSTRUCAO_VO); // usado para controle na jsp para mostrar os dados da construcao.
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * Método responsável por preparar a alteração de uma construção para uma ficha de imóvel rural.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitarAlterarConstrucoesImovelRural(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		int indice = StringUtil.toInt(request.getParameter(BOTAO_SOLICITAR_ALTERAR_CONSTRUCOES_IMOVEL_RURAL));
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo = (FichaImovelRuralConstrucaoVo) ((ArrayList) fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getCollVO()).remove(indice);
		fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().setCodigo(fichaImovelRuralConstrucaoVo.getCodigo());
		fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getConstrucaoVo().setCodigo(fichaImovelRuralConstrucaoVo.getConstrucaoVo().getCodigo());
		fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getConstrucaoVo().setDescricaoConstrucao(fichaImovelRuralConstrucaoVo.getConstrucaoVo().getDescricaoConstrucao());
		fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().setDescricaoConstrucao(fichaImovelRuralConstrucaoVo.getDescricaoConstrucao());
		fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().setSituacaoEstadoConservacao(new DomnEstadoConservacao(fichaImovelRuralConstrucaoVo.getSituacaoEstadoConservacao().getValorCorrente()));
		fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().setValorMercado(fichaImovelRuralConstrucaoVo.getValorMercado());
		request.setAttribute(ALTERAR_CONSTRUCAO, ALTERAR_CONSTRUCAO);
		request.setAttribute(EXIBIR, CONSTRUCAO_VO); // usado para controle na jsp para mostrar os dados da construcao.
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * Método rseponsável por excluir uma contrução de uma ficha de imóvel rural.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void excluirConstrucoesImovelRural(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		int indice = StringUtil.toInt(request.getParameter(BOTAO_EXCLUIR_CONSTRUCOES_IMOVEL_RURAL));
		FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo = (FichaImovelRuralConstrucaoVo) ((ArrayList) fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getCollVO()).remove(indice);
		FichaImovelRuralConstrucaoBe.reinicializaConstrucaoImovelRural(fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo());
		request.setAttribute(EXIBIR, CONSTRUCAO_VO); // usado para controle na jsp para mostrar os dados da construcao.
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * Método responsável por solicitar uma pesquisa de Cep para uma ficha de imóvel urbano.
	 * @param request
	 * @param response
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitarPesquisarCepImovelUrbano(HttpServletRequest request, HttpServletResponse response) throws IntegracaoException, 
			   ObjetoObrigatorioException, PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, ConexaoException, 
			   ConsultaException, ParametroObrigatorioException
	{
		FichaImovelUrbanoVo fichaImovelUrbanoVo = iniciaFichaImovelUrbano(request);
		FichaImovelUrbanoBe fichaImovelUrbanoBe = null;
		try
		{
			fichaImovelUrbanoBe = new FichaImovelUrbanoBe();
			try
			{
				fichaImovelUrbanoBe.pesquisaCepImovelUrbano(fichaImovelUrbanoVo);
			}
			catch (RegistroNaoPodeSerUtilizadoException e)
			{
				request.setAttribute(EXCEPTION, e);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (fichaImovelUrbanoBe != null)
			{
				fichaImovelUrbanoBe.close();
				fichaImovelUrbanoBe = null;
			}
		}
		getBuffer(request).setAttribute(FICHA_IMOVEL_URBANO_VO, fichaImovelUrbanoVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_URBANO, request, response, FORWARD);
	}

	/**
	 * Método responsável por solicitar uma pesquisa de Cep para uma ficha de imóvel rural.
	 * @param request
	 * @param response
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitarPesquisarCepImovelRural(HttpServletRequest request, HttpServletResponse response) throws IntegracaoException, 
			   ObjetoObrigatorioException, PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, 
			   ConexaoException
	{
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		FichaImovelRuralBe fichaImovelRuralBe = null;
		try
		{
			fichaImovelRuralBe = new FichaImovelRuralBe();
			try
			{
				fichaImovelRuralBe.pesquisaCepImovelRural(fichaImovelRuralVo);
			}
			catch (RegistroNaoPodeSerUtilizadoException e)
			{
				request.setAttribute(EXCEPTION, e);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (fichaImovelRuralBe != null)
			{
				fichaImovelRuralBe.close();
				fichaImovelRuralBe = null;
			}
		}
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * Método responsável por redirecionar para a JSP especifica de cada GIAITCD.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void redirecionaJSP(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == DomnTipoProcesso.INVENTARIO_ARROLAMENTO)
		{
			processFlow(VIEW_ESTRUTURA_GIAITCD_INVENTARIO_ARROLAMENTO_UTIL, request, response, FORWARD);
		}
		else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == DomnTipoProcesso.DOACAO)
		{
			processFlow(VIEW_ESTRUTURA_GIAITCD_DOACAO_UTIL, request, response, FORWARD);
		}
		else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA)
		{
			processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		}
	}

	/**
	 * Método desenvolvido para retornar o tipo de Processo da natureza da Operacao e identificar o tipo da GIA.
	 * 
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return int
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @deprecated - Dherkyan Ribeiro - método substituido por retornarTipoProcessoUtil(HttpServletRequest request);
	 * <br>
	 * <h2>remover método após a publicação da versão 1.2</h2>.
	 */
	private int retornaTipoProcesso(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		Validador.validaObjeto(giaITCDVo);
		int tipoProcesso = 0;
		if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == DomnTipoProcesso.INVENTARIO_ARROLAMENTO)
		{
			tipoProcesso = DomnTipoProcesso.INVENTARIO_ARROLAMENTO;
		}
		else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == DomnTipoProcesso.DOACAO)
		{
			tipoProcesso = DomnTipoProcesso.DOACAO;
		}
		else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() == DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA)
		{
			tipoProcesso = DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA;
		}
		return giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente();
	}

	/**
	 * <b>Objetivo:</b> este método tem por objetivo
	 * retonar o tipo de processo da  gia NaturezaOperacaoVo identificando
	 * assim o tipo de gia.
	 * 
	 * @param request
	 * @return um número que representa o TipoProcesso da NaturezaOperacaoVo.
	 * caso não seje possível identificar o TipoProcesso será retornado -1.
	 * @throws ObjetoObrigatorioException
	 */
	private DomnTipoProcesso retornaTipoProcessoUtil(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
		if (!Validador.isObjetoValido(giaITCDVo))
		{
			return new DomnTipoProcesso(-1);
		}
		return giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso();
	}

	/**
	 * Método responsável por solicitar a Aba Dados Gerais
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConexaoException
	 * @throws ConsultaException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ImpossivelCriptografarException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaDadosGerais(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   IntegracaoException, ConexaoException, ConsultaException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, LogException, 
			   AnotacaoException, PersistenciaException, 
			   ImpossivelCriptografarException
	{
		GIAITCDVo giaITCDVo = new GIAITCDVo();
		DomnTipoProcesso tipoProcesso = retornaTipoProcessoUtil(request); // retornaTipoProcesso(request);
		if (tipoProcesso.is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
		{
			giaITCDVo = (GIAITCDInventarioArrolamentoVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		else if (tipoProcesso.is(DomnTipoProcesso.DOACAO))
		{
			giaITCDVo = (GIAITCDDoacaoVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		else if (tipoProcesso.is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
		{
			giaITCDVo = (GIAITCDSeparacaoDivorcioVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		//Se o usuario clicou na aba, o sistema verifica se tem o bemTributavel antigo no buffer e adiciona ele novamente a coleçao de bens tributaveis
		if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
		{
			bemTributavelVo.getCollVO().add(getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO));
			getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
		}
		bemTributavelVo.setAlterar(false);
		giaITCDVo.setBemTributavelVo(bemTributavelVo);
		String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
		Validador.validaObjeto(abaAtual);
		GIAITCDBe giaITCDBe = null;
		try
		{
			validaAba(giaITCDVo, abaAtual, DomnAba.DADOS_GERAIS);
			giaITCDBe = new GIAITCDBe();
			obterInformacoesLogSefaz(request, giaITCDVo);
			giaITCDBe.manterGIAITCD(giaITCDVo);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_DADOS_GERAIS, request);
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
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(EXCEPTION, e);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(EXCEPTION, e);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
		}
		catch (IOException e)
		{
			giaITCDVo.setMensagem(e.getMessage());
			request.setAttribute(ENTIDADE_VO, giaITCDVo);
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
		processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);
	}

	/**
	 * Método
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws ImpossivelCriptografarException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaBensTributaveis(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   ConexaoException, PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, ConsultaException, 
			   IntegracaoException, DadoNecessarioInexistenteException, 
			   PersistenciaException, LogException, AnotacaoException, 
			   ImpossivelCriptografarException, 
			   RegistroNaoPodeSerUtilizadoException
	{
		salvarBensTributaveis(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConexaoException
	 * @throws ConsultaException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ImpossivelCriptografarException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaBeneficiarios(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   IntegracaoException, PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, ConexaoException, 
			   ConsultaException, LogException, AnotacaoException, 
			   PersistenciaException, 
			   ImpossivelCriptografarException
	{
		GIAITCDVo giaITCDVo = new GIAITCDVo();
		DomnTipoProcesso tipoProcesso = retornaTipoProcessoUtil(request); //retornaTipoProcesso(request);
		if (tipoProcesso.is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
		{
			giaITCDVo = (GIAITCDInventarioArrolamentoVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		else if (tipoProcesso.is(DomnTipoProcesso.DOACAO))
		{
			giaITCDVo = (GIAITCDDoacaoVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		else if (tipoProcesso.is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
		{
			giaITCDVo = (GIAITCDSeparacaoDivorcioVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		//Se o usuario clicou na aba, o sistema verifica se tem o bemTributavel antigo no buffer e adiciona ele novamente a coleçao de bens tributaveis
		if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
		{
			bemTributavelVo.getCollVO().add(getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO));
			getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
			bemTributavelVo.setAlterar(false);
		}
		try
		{
			String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
			Validador.validaObjeto(abaAtual);
			getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
			validaAba(bemTributavelVo.getGiaITCDVo(), abaAtual, DomnAba.BENEFICIARIOS);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENEFICIARIOS, request);
			processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			request.setAttribute(EXCEPTION, e);
			redirecionaJSP(request, response);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			request.setAttribute(EXCEPTION, e);
			redirecionaJSP(request, response);
		}
		giaITCDVo.setBemTributavelVo(bemTributavelVo);
		GIAITCDBe giaITCDBe = null;
		try
		{
			giaITCDBe = new GIAITCDBe();
			obterInformacoesLogSefaz(request, giaITCDVo);
			giaITCDBe.manterGIAITCD(giaITCDVo);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
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
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(EXCEPTION, e);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
			redirecionaJSP(request, response);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(EXCEPTION, e);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
			redirecionaJSP(request, response);
		}
		catch (RegistroNaoPodeSerUtilizadoException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(EXCEPTION, e);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
			redirecionaJSP(request, response);
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
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ImpossivelCriptografarException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaDemonstrativoDeCalculo(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   IntegracaoException, PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, ConsultaException, 
			   ConexaoException, LogException, AnotacaoException, 
			   PersistenciaException, 
			   ImpossivelCriptografarException
	{
		GIAITCDVo giaITCDVo = new GIAITCDVo();
		DomnTipoProcesso tipoProcesso = retornaTipoProcessoUtil(request); //retornaTipoProcesso(request);
		if (tipoProcesso.is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
		{
			giaITCDVo = (GIAITCDInventarioArrolamentoVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		else if (tipoProcesso.is(DomnTipoProcesso.DOACAO))
		{
			giaITCDVo = (GIAITCDDoacaoVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		else if (tipoProcesso.is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
		{
			giaITCDVo = (GIAITCDSeparacaoDivorcioVo) getBuffer(request).getAttribute(GIAITCD_VO);
		}
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		//Se o usuario clicou na aba, o sistema verifica se tem o bemTributavel antigo no buffer e adiciona ele novamente a coleçao de bens tributaveis
		if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
		{
			bemTributavelVo.getCollVO().add((BemTributavelVo)getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO));
			getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
			bemTributavelVo.setAlterar(false);
		}
		String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
		Validador.validaObjeto(abaAtual);
		try
		{
			validaAba(bemTributavelVo.getGiaITCDVo(), abaAtual, DomnAba.DEMONSTRATIVO_DE_CALCULO);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
			giaITCDVo.setBemTributavelVo(bemTributavelVo);
			GIAITCDBe giaITCDBe = null;
			try
			{
				giaITCDBe = new GIAITCDBe();
				obterInformacoesLogSefaz(request, giaITCDVo);
				giaITCDBe.manterGIAITCD(giaITCDVo);
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
				getBuffer(request).setAttribute(ABA_ATUAL, ABA_DEMONSTRATIVO_CALCULO, request);
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
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
				request.setAttribute(EXCEPTION, e);
				getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
			}
			catch (RegistroExistenteException e)
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
				request.setAttribute(EXCEPTION, e);
				getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
			}
			catch (RegistroNaoPodeSerUtilizadoException e)
			{
				getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
				request.setAttribute(EXCEPTION, e);
				getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
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
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			request.setAttribute(EXCEPTION, e);
			getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
			redirecionaJSP(request, response);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(BEM_TRIBUTAVEL_VO, bemTributavelVo, request);
			getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
			request.setAttribute(EXCEPTION, e);
			redirecionaJSP(request, response);
		}
		processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);
	}

	/**
	 * Método desenvolvido para retornar para a aba Conjuge do Separação/Divorcio/Partilha.
	 * 
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ImpossivelCriptografarException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaAbaConjuge(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   IntegracaoException, ConsultaException, ConexaoException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, LogException, 
			   AnotacaoException, PersistenciaException, 
			   ImpossivelCriptografarException
	{
		GIAITCDVo giaITCDVo = new GIAITCDVo();
		giaITCDVo = (GIAITCDSeparacaoDivorcioVo) getBuffer(request).getAttribute(GIAITCD_VO);
		BemTributavelVo bemTributavelVo = controladorInterativoJSP(request);
		//Se o usuario clicou na aba, o sistema verifica se tem o bemTributavel antigo no buffer e adiciona ele novamente a coleçao de bens tributaveis
		if (getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO) != null)
		{
			bemTributavelVo.getCollVO().add((BemTributavelVo)getBuffer(request).getAttribute(BEM_TRIBUTAVEL_ANTIGO_VO));
			getBuffer(request).removeAttribute(BEM_TRIBUTAVEL_ANTIGO_VO, request);
			BemTributavelBe.reinicializaBemTributavel(bemTributavelVo);
			bemTributavelVo.setAlterar(false);
		}
		giaITCDVo.setBemTributavelVo(bemTributavelVo);
		String abaAtual = (String) getBuffer(request).getAttribute(ABA_ATUAL);
		Validador.validaObjeto(abaAtual);
		getBuffer(request).setAttribute(ABA_ATUAL, abaAtual, request);
		GIAITCDBe giaITCDBe = null;
		try
		{
			validaAba(giaITCDVo, abaAtual, DomnAba.CONJUGE);
			giaITCDBe = new GIAITCDBe();
			obterInformacoesLogSefaz(request, giaITCDVo);
			giaITCDBe.manterGIAITCD(giaITCDVo);
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_CONJUGE, request);
			processFlow(FormAcesso.getUrlServletPai(JspUtil.getCodigoPermissaoOriginal(request), request), request, response, FORWARD);
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
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(EXCEPTION, e);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
			processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		}
		catch (RegistroNaoPodeSerUtilizadoException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(EXCEPTION, e);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
			processFlow(VIEW_ESTRUTURA_GIAITCD_SEPARACAO_DIVORCIO_UTIL, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
			request.setAttribute(EXCEPTION, e);
			getBuffer(request).setAttribute(ABA_ATUAL, ABA_BENS_TRIBUTAVEIS, request);
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
	 * @param giaITCDVo
	 * @param abaAtual
	 * @param abaDestino
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException Essa exception será tratada no método que chamar esse método
	 * @throws RegistroExistenteException Essa exception será tratada no método que chamar esse método
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void validaAba(GIAITCDVo giaITCDVo, String abaAtual, int abaDestino) throws ObjetoObrigatorioException, 
			   ParametroObrigatorioException, 
			   RegistroExistenteException, IntegracaoException
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
		else if (abaAtual.equals(ABA_BENEFICIARIOS))
		{
			abaOrigem = DomnAba.CONJUGE;
		}
		if (abaOrigem < abaDestino)
		{
			switch (abaOrigem)
			{
				case DomnAba.BENS_TRIBUTAVEIS:
					{
						GIAITCDBe.validaBemTributavel(giaITCDVo);
						if (abaDestino == DomnAba.DEMONSTRATIVO_DE_CALCULO)
						{
							if (giaITCDVo instanceof GIAITCDSeparacaoDivorcioVo)
							{
								GIAITCDSeparacaoDivorcioBe.validarAbaConjuge((GIAITCDSeparacaoDivorcioVo) giaITCDVo);
							}
							else if (giaITCDVo instanceof GIAITCDInventarioArrolamentoVo)
							{
								GIAITCDBe.validaBeneficiario((GIAITCDInventarioArrolamentoVo) giaITCDVo);
							}
							else if (giaITCDVo instanceof GIAITCDDoacaoVo)
							{
								GIAITCDDoacaoBe.validaBeneficiariosGIAITCDDoacao((GIAITCDDoacaoVo) giaITCDVo);
							}
						}
						break;
					}
			}
		}
	}

	/**
	 * Método que calcula os valores gerais da Ficha Imóvel Rural.
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by João Batista Padilha e Silva
	 */
	private void solicitaCalculoGeral(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaCalculoValorMercadoCultura(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, 
			   ObjetoObrigatorioException
	{
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo = fichaImovelRuralVo.getFichaImovelRuralCulturaVo();
		FichaImovelRuralCulturaBe.calculaValorMercadoCultura(fichaImovelRuralCulturaVo);
		request.setAttribute(EXIBIR, CULTURA_VO);
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * @param request
	 * @param response
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitaCalculoValorRebanho(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, 
			   ObjetoObrigatorioException
	{
		FichaImovelRuralVo fichaImovelRuralVo = iniciaFichaImovelRural(request);
		FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo = fichaImovelRuralVo.getFichaImovelRuralRebanhoVo();
		FichaImovelRuralRebanhoBe.calculaValorTotalRebanho(fichaImovelRuralRebanhoVo);
		request.setAttribute(EXIBIR, REBANHO_VO);
		getBuffer(request).setAttribute(FICHA_IMOVEL_RURAL_VO, fichaImovelRuralVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_RURAL, request, response, FORWARD);
	}

	/**
	 * Este método tem por obejtivo pesquisar e adicionar
	 * um imovel urbano integrado.
	 * 
	 * 
	 * @param request
	 * @param response
	 */
	private void adicionarImovelUrbanoIntegrado(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException, SQLException, 
			   ConsultaException, IOException, Exception
	{
		FichaImovelUrbanoVo fichaImovelUrbanoVo = iniciaFichaImovelUrbano(request);
		FichaImovelUrbanoBe fichaImovelUrbanoBe = null;
		try
		{
			fichaImovelUrbanoBe = new FichaImovelUrbanoBe();
			fichaImovelUrbanoBe.adicionarNumeroInscricaoImovelUrbanoIntegrado(fichaImovelUrbanoVo);
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
		}
      finally{
			if (fichaImovelUrbanoBe != null)
			{
				fichaImovelUrbanoBe.close();
				fichaImovelUrbanoBe = null;
			}
		}
		getBuffer(request).setAttribute(FICHA_IMOVEL_URBANO_VO, fichaImovelUrbanoVo, request);
		processFlow(VIEW_MANTER_GIAITCD_DETALHAR_FICHA_IMOVEL_URBANO, request, response, FORWARD);
	}

	private void verificarBemInativoAdicionarEmColecao(BemTributavelVo bemTributavelVo, HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			   PaginaNaoEncontradaException, 
			   TipoRedirecionamentoInvalidoException
	{
		//ADICIONADO PARA LIBERAR ALTERAÇÃO DO BEM TRIBUTAVEL CASO O BEM SEJA INATIVADO DURANTE A CRIAÇÃO DA GIA
		if (Validador.isNumericoValido(bemTributavelVo.getBemVo().getCodigo()))
		{
			if (!bemTributavelVo.getBemVo().getCollVO().contains(bemTributavelVo.getBemVo()))
			{
				bemTributavelVo.getBemVo().getCollVO().add(bemTributavelVo.getBemVo());
			}
		}
		else
		{
			bemTributavelVo.setHabilitarTipoBem(true);
			if (Validador.isNumericoValido(bemTributavelVo.getFichaImovelVo().getBemTributavelVo().getValorMercado()))
			{
				if (bemTributavelVo.getFichaImovelVo() instanceof FichaImovelUrbanoVo)
				{
					bemTributavelVo.getBemVo().setClassificacaoTipoBem(new DomnTipoBem(DomnTipoBem.IMOVEL_URBANO));
					bemTributavelVo.setBemVo(retornaBemVo(bemTributavelVo, request, response));
				}
				else if (bemTributavelVo.getFichaImovelVo() instanceof FichaImovelRuralVo)
				{
					bemTributavelVo.getBemVo().setClassificacaoTipoBem(new DomnTipoBem(DomnTipoBem.IMOVEL_RURAL));
				}
				else
				{
					throw new ObjetoObrigatorioException();
				}
				bemTributavelVo.setBemVo(retornaBemVo(bemTributavelVo, request, response));
			}
			else if (Validador.isObjetoValido(bemTributavelVo.getFichaVo()))
			{
				bemTributavelVo.getBemVo().setClassificacaoTipoBem(new DomnTipoBem(DomnTipoBem.OUTROS_BENS));
				bemTributavelVo.setBemVo(retornaBemVo(bemTributavelVo, request, response));
			}
			else
			{
				if (!Validador.isDominioNumericoValido(bemTributavelVo.getBemVo().getClassificacaoTipoBem()))
				{
					bemTributavelVo.setHabilitarClassificacaoBem(true);
				}
			}
		}
	}
}
