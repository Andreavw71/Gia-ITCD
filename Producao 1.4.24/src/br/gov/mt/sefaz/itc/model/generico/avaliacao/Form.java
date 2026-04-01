/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: Form.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: Form.java,v 1.1.1.1 2008/05/28 17:55:04 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.avaliacao;

import br.gov.mt.sefaz.itc.util.facade.FormITC;


/**
 * @author Elizabeth Barbosa Moreira
 * @version $Revision:
 */
public interface Form extends FormITC
{
	public static final String PARAMETRO_SOLICITAR_INCLUIR_AVALIACAO_BEM = "parametroSolicitarIncluirAvaliacaoBem";
	public static final String PARAMETRO_SOLICITAR_ALTERAR_AVALIACAO_BEM = "parametroSolicitarAlterarAvaliacaoBem";
	public static final String PARAMETRO_DETALHAR_AVALIACAO_BEM = "parametroDetalharrAvaliacaoBem";
	public static final String PARAMETRO_SOLICITAR_PESQUISAR_AVALIACAO_POR_AGENFA = 
		  "parametroSolicitarPesquisarPorAgenfa";
	public static final String PARAMETRO_SOLICITAR_INCLUIR_SERVIDOR = "parametroSolicitarIncluirServidor";
	public static final String PARAMETRO_SOLICITAR_PESQUISAR_GIAITCD_AVALIADA = 
		  "parametroSolicitarPesquisarGIAITCDAvaliada";
	public static final String PARAMETRO_PESQUISAR_VALOR_AVALIACAO = "parametroPesquisarValorAvaliacao";
	public static final String PARAMETRO_PESQUISAR_GIAITCD_AVALIADA = "parametroPesquisarGIAITCDAvaliada";
	public static final String PARAMETRO_INCLUIR_SERVIDOR = "parametroIncluirServidor";
	public static final String PARAMETRO_SOLICITAR_EXCLUIR_SERVIDOR = "parametroExcluirServidor";
	public static final String PARAMETRO_PESQUISAR_GIAITCD = "parametroPesquisarGIAITCD";
	public static final String PARAMETRO_PESQUISAR_GIAITCD_POR_NUMERO = "parametroPesquisarGIAITCDPorNumero";
	public static final String PARAMETRO_PESQUISAR_AVALIACAO_GIAITCD = "parametroPesquisarAvaliacaoGIAITCD";
	public static final String PARAMETRO_PESQUISAR_AVALIACAO_AGENFA = "parametroPesquisarAvaliacaoAgenfa";
	public static final String PARAMETRO_PESQUISAR_AVALIACAO_POR_AGENFA = "parametroPesquisarAvaliacaoPorAgenfa";
	public static final String CHECKEBOX_AVALIACAO_JUDICIAL = "checkeboxAvalicaoJudicial";
	public static final String CHECKEBOX_ISENCAO_PREVISTA_EM_LEI = "checkeboxIsencaoPrevistaEmLei";
	public static final String CAMPO_VALOR_AVALIACAO = "campoValorAvaliacao";
	public static final String CAMPO_DATA_AVALIACAO = "campoDataAvaliacao";
	public static final String CAMPO_VALOR_AVALIACAO_JUDICIAL = "campoValorAvaliacaoJudicial";
	public static final String CAMPO_DATA_AVALIACAO_JUDICIAL = "campoDataAvaliacaoJudicial";
	public static final String CAMPO_OBSERVACAO = "campoObservacao";
	public static final String CAMPO_DATA_INICIAL = "campoDataInicial";
	public static final String CAMPO_DATA_FINAL = "campoDataFinal";
	public static final String CAMPO_MATRICULA_SERVIDOR = "campoMatriculaServidor";
	public static final String CAMPO_CHECK_ISENCAO_PREVISTA_LEI = "campoCheckIsencaoPrevistaLei";
	public static final String CAMPO_SELECT_UNIDADE_SEFAZ = "campoSelectUnidadeSefaz";
	public static final String CAMPO_SELECT_UNIDADE_SEFAZ_AGENFA = "campoSelectUnidadeSefazAgenfa";
	public static final String CAMPO_SELECT_UNIDADE_SEFAZ_GERENCIA = "campoSelectUnidadeSefazGerencia";
	public static final String PARAMETRO_CODG_UNIDADE_SEFAZ = "codgUnidadeSefaz";
	public static final String PARAMETRO_GIA_ITCD_ISENTA = "parametroGIAITCDIsenta";
	public static final String CONSULTAR_AVALIACAO = "consultarAvaliacao";
	public static final String CAMPO_SELECT_TIPO_PESQUISA_INICIAL = "campoSelectTipoPesquisaInicial";
	public static final String CAMPO_SELECT_AGENFA = "campoSelectAgenfa";
	public static final String CAMPO_SELECT_GERENCIA_EXECUCAO = "campoSelectGerenciaExecucao";
	public static final String CAMPO_SELECT_UNIDADE_AVALIACAO = "campoSelectUnidadeAvaliacao";
	public static final String PARAMETRO_PESQUISAR_AVALIACAO_NUMERO_GIA = "parametroPesquisarAvaliacaoNumeroGia";
	public static final String BOTAO_DETALHAR_GIASITCD = "botaoDetalharGIAITCD";
   
   public static final String TEXTO_BOTAO_REABRIR_AVALIACAO = "Reabrir Avaliacao";
   public static final String BOTAO_REABRIR_AVALIACAO = "botaoReabrirAvaliacao";
   public static final String PARAMETRO_REABRIR_AVALIACAO_BEM = "parametroReabrirAvaliacaoBem";
    
   
   
}
