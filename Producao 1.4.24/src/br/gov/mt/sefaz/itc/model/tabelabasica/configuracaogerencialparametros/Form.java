/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: Form.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 06/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros;

import br.gov.mt.sefaz.itc.util.facade.FormITC;


/**
 * Interface que representa a nomenclatura dos campos do formulário HTML.
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
public interface Form extends FormITC
{
	public static final String CAMPO_SELECT_ITENS_PREENCHIMENTO = "campoSelectItensPreenchimento";
	public static final String CAMPO_VALOR_CAMPO = "campoValorCampo";
	public static final String CONFIGURACAO_PARAMETRO = "configuracao";
	public static final String CONFIGURACAO_ROTULO = "rotulo";
}
