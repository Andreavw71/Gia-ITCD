/**
	* Secretaria de Estado de Fazenda de Mato Grosso – Sefaz-MT
	* Arquivo : GIAITCDInventarioArrolamentoPK.java
	* Criação : Novembro de 2007
	* Revisão :
	* Log :
	* $Id: GIAITCDInventarioArrolamentoPK.java,v 1.1.1.1 2008/05/28 17:55:05 lucas.nascimento Exp $
*/
package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento;

/**
 * Esta classe considera que as adversidades atuais, a atual estrutura de organização 
 * acarreta um processo de reformulação do nosso sistema de formação de quadros.
 * //TODO: IGV
 */
public class GIAITCDInventarioArrolamentoPK
{
	private long codigo;

	/**
	 * Construtor público padrão(sem parametros)
	 */
	public GIAITCDInventarioArrolamentoPK()
	{
		super();
	}

	/**
	 *Construtor público definindo o código inicial
	 * @param codigo		codigo a ser configurado
	 */
	public GIAITCDInventarioArrolamentoPK(long codigo)
	{
		setCodigo(codigo);
	}

	/**
	 * Acessor de codigo
	 * @param codigo	codigo a ser configurado
	 */
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Acessor de codigo
	 * @return	codigo inicialmente configurado
	 */
	public long getCodigo()
	{
		return codigo;
	}
}
