package br.gov.mt.sefaz.itc.model.generico.fichaimovel;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.EnderecoIntegracaoVo;

import java.util.Collection;


/**
 * VO Pai de das Fichas Imóveis, Rural e Urbano
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class FichaImovelVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private BemTributavelVo bemTributavelVo;
	private EnderecoIntegracaoVo enderecoVo;

	/**
	 * Construtor Padrão
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelVo()
	{
		super();
	}

	/**
	 * Construtor que recebe o Parametro de Consulta
	 * 
	 * @param parametroConsulta
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelVo(FichaImovelVo parametroConsulta)
	{
		super();
		setParametroConsulta(parametroConsulta);
	}

	/**
	 * Construtor que recebe uma Collection de FichaImovelVo
	 * 
	 * @param collVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelVo(Collection collVo)
	{
		super(collVo);
	}

	/**
	 * Construtor que recebe a Chave Primária 
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Atribui um Bem Tributavel
	 * 
	 * @param bemTributavelVo
	 * @implemented by Daniel Balieiro
	 */
	public void setBemTributavelVo(BemTributavelVo bemTributavelVo)
	{
		this.bemTributavelVo = bemTributavelVo;
	}

	/**
	 * Retorna o Bem Tributavel
	 * 
	 * @return BemTributavelVo
	 * @implemented by Daniel Balieiro
	 */
	public BemTributavelVo getBemTributavelVo()
	{
		if (!Validador.isObjetoValido(bemTributavelVo))
		{
			setBemTributavelVo(new BemTributavelVo());
		}
		return bemTributavelVo;
	}

	/**
	 * Atribui um Endereço
	 * 
	 * @param enderecoVo
	 * @implemented by Daniel Balieiro
	 */
	public void setEnderecoVo(EnderecoIntegracaoVo enderecoVo)
	{
		this.enderecoVo = enderecoVo;
	}

	/**
	 * Retorna o Endereço
	 * 
	 * @return EnderecoIntegracaoVo
	 * @implemented by Daniel Balieiro
	 */
	public EnderecoIntegracaoVo getEnderecoVo()
	{
		if (!Validador.isObjetoValido(enderecoVo))
		{
			setEnderecoVo(new EnderecoIntegracaoVo());
		}
		return enderecoVo;
	}
}
