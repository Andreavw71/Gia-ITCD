/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: Form.java
 * Revisão:
 * Data revisão:
 * $Id: Form.java,v 1.4 2009/01/20 13:03:21 ricardo.moraes Exp $
 */
package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento;

import br.gov.mt.sefaz.itc.util.facade.FormITC;


/**
 * Form da funcionalidade de GIAITCD de Inventário e Arrolamento
 * @author Leandro Dorileo
 * @version $Revision: 1.4 $
 */
public interface Form extends FormITC
{
	public static final String CAMPO_CHECK_ISENCAO_PREVISTA_LEI = "campoCheckIsencaoPrevistaLei";
	public static final String CAMPO_DATA_FALECIMENTO = "campoDataFalecimento";
	public static final String CAMPO_DATA_INVENTARIO_ARROLAMENTO = "campoDataInventarioArrolamento";
	public static final String CAMPO_DESCRICAO = "campoDescricao";
	public static final String CAMPO_JUIZO_COMARCA = "campoJuizoComarca";
	public static final String CAMPO_NUMERO_DOCUMENTO = "campoNumeroDocumento";
	public static final String CAMPO_NUMERO_HERDEIROS = "campoNumeroHerdeiros";
	public static final String CAMPO_NUMERO_PROCESSO_INVENTARIO_ARROLAMENTO = 
		  "campoNumeroProcessoInventarioArrolamento";
	public static final String CAMPO_SELECT_CLASSIFICACAO_BEM = "campoSelectClassificacaoBem";
	public static final String CAMPO_SELECT_ESTADO_CIVIL = "campoSelectEstadoCivil";
	public static final String CAMPO_SELECT_NATUREZA_OPERACAO = "campoSelectNaturezaOperacao";
	public static final String CAMPO_SELECT_POSSUI_CPF = "campoSelectPossuiCPF";
	public static final String CAMPO_SELECT_REGIME_CASAMENTO = "campoSelectRegimeCasamento";
	public static final String CAMPO_SELECT_UF_ABERTURA_INVENTARIO_ARROLAMENTO = 
		  "campoSelectUFAberturaInventarioArrolamento";
	public static final String CAMPO_TIPO_DOCUMENTO = "campoTipoDocumento";
	public static final String CAMPO_VALOR_MERCADO = "campoValorMercado";
	public static final String PARAMETRO_SOLICITAR_PESQUISAR_DE_CUJUS_SEM_CPF = 
		  "parametroSolicitarPesquisarDeCujusSemCPF";
	public static final String IS_DE_CUJUS_SEM_CPF = "isDeCujusSemCPF";
	public static final String CAMPO_SELECT_RENUNCIA = "campoInformeRenuncia";
	public static final String CAMPO_SELECT_HERDEIROS_DESCENDENTES = "campoSelectHerdeirosDescendentes";
	public static final String CAMPO_SELECT_HERDEIROS_ASCENDENTES = "campoSelectHerdeirosAscendentes";
	public static final String CAMPO_SELECT_OUTROS_HERDEIROS = "campoSelectOutrosHerdeiros";
	public static final String CAMPO_SELECT_TIPO_RENUNCIA = "campoSelectTipoRenuncia";
	public static final String CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES = "campoQuantidadeHerdeirosDescendentes";
	public static final String CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES = "campoQuantidadeHerdeirosAscendentes";
	public static final String CAMPO_QUANTIDADE_OUTROS_HERDEIROS = "camopQuantidadeOutrosHerdeiros";
   public static final String CAMPO_SELECT_TIPO_PROCESSO_INVENTARIO = "campoSelectTipoProcessoInvetario";

}
