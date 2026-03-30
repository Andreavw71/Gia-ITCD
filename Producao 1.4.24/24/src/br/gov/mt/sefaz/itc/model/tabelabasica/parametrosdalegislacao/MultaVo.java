package br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposParametroLegislacaoMulta;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;


/**
 * Classe responsável por encapsular os valores do objeto Multa (Value Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_PARAMETRO_LEGISLACAO_MULTA
     ,nomeAmigavel = "Multa Parâmetro Legislação"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposParametroLegislacaoMulta.CAMPO_CODIGO_MULTA
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_PARAMETRO_LEGISLACAO_MULTA
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class MultaVo extends EntidadeVo implements Comparable
{
	private double percentualMulta;
	private int quantidadeDiasConsulta;
	private int quantidadeDiasFinal;
	private int quantidadeDiasInicial;
	private long codigoParametroLegislacao;
	private static final long serialVersionUID = Long.MAX_VALUE;

	/**
	 * Construtor Padrão
	 * @implemented by Daniel Balieiro
	 */
	public MultaVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a Chave Primária
	 * @param codigo Chave Primária
	 * @implemented by Daniel Balieiro
	 */
	public MultaVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor 	que recebe a Collection de MultaVo
	 * @param collVo Coleção de MultaVo
	 * @implemented by Daniel Balieiro
	 */
	public MultaVo(Collection collVo)
	{
		super(collVo);
	}

	/**
	 * Construtor que recebe Parametro de Consulta
	 * @param multaVo Parametro de Consulta
	 * @implemented by Daniel Balieiro
	 */
	public MultaVo(MultaVo multaVo)
	{
		super();
		setParametroConsulta(multaVo);
	}

	/**
	 * Retorna a Chave Primária
	 * @return MultaPk - Chave Primária
	 * @implemented by Daniel Balieiro
	 */
	public MultaPk getPk()
	{
		return new MultaPk(getCodigo());
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui uma Quantidade de Dias Inicial
	 * @param quantidadeDiasInicial Quantidade de dias inicial
	 * @implemented by Daniel Balieiro
	 */
	public void setQuantidadeDiasInicial(int quantidadeDiasInicial)
	{
		this.quantidadeDiasInicial = quantidadeDiasInicial;
	}

	/**
	 * Retorna a Quantidade de dias Inicial
	 * @return int - Quantidade de Dias Inicial
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacaoMulta.CAMPO_QUANTIDADE_DIAS_INICIAL
	     ,obrigatorio = true
	 )
	public int getQuantidadeDiasInicial()
	{
		return quantidadeDiasInicial;
	}

	/**
	 * Método que retorna a Quantidade de Dias Inicial Formatada
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getQuantidadeDiasInicialFormatada()
	{
		if (!Validador.isNumericoValido(getQuantidadeDiasInicial()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getQuantidadeDiasInicial());
	}

	/**
	 * Atribui uma Quantidade de Dias Final
	 * @param quantidadeDiasFinal Quantidade de dias Final
	 * @implemented by Daniel Balieiro
	 */
	public void setQuantidadeDiasFinal(int quantidadeDiasFinal)
	{
		this.quantidadeDiasFinal = quantidadeDiasFinal;
	}

	/**
	 * Retorna a Quantidade de Dias Final
	 * @return int - Quantidade de Dias Final
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacaoMulta.CAMPO_QUANTIDADE_DIAS_FINAL
	     ,obrigatorio = false
	 )
	public int getQuantidadeDiasFinal()
	{
		return quantidadeDiasFinal;
	}

	/**
	 * Método que retorna a Quantidade de Dias Final Formatada
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getQuantidadeDiasFinalFormatada()
	{
		if (!Validador.isNumericoValido(getQuantidadeDiasFinal()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getQuantidadeDiasFinal());
	}

	/**
	 * Atribui um Percentual de Multa
	 * @param percentualMulta Percentual de Multa
	 * @implemented by Daniel Balieiro
	 */
	public void setPercentualMulta(double percentualMulta)
	{
		this.percentualMulta = percentualMulta;
	}

	/**
	 * Retorna um Percentual de Multa
	 * @return double - Percentual de Multa
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacaoMulta.CAMPO_PERCENTUAL_MULTA
	     ,obrigatorio = false
	 )
	public double getPercentualMulta()
	{
		return percentualMulta;
	}

	/**
	 * Retorna um Percentual de Multa formatado
	 * @return String - Percentual de Multa formatado
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getPercentualMultaFormatado()
	{
		return StringUtil.doubleToMonetario(percentualMulta);
	}

	/**
	 * Atribui um Código de Legislação
	 * @param codigoParametroLegislacao
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigoParametroLegislacao(long codigoParametroLegislacao)
	{
		this.codigoParametroLegislacao = codigoParametroLegislacao;
	}

	/**
	 * Retorna o Código de Legislação
	 * @return long - Código de Legislação
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacaoMulta.CAMPO_ITCTB09_CODIGO_PARAMETRO_LEGISLACAO
	     ,obrigatorio = true
	 )
	public long getCodigoParametroLegislacao()
	{
		return codigoParametroLegislacao;
	}

	/**
	 * Atribui uma Quantidade de Dias para Consulta por Período
	 * @param quantidadeDiasConsulta Quantidade de Dias
	 * @implemented by Daniel Balieiro
	 */
	public void setQuantidadeDiasConsulta(int quantidadeDiasConsulta)
	{
		this.quantidadeDiasConsulta = quantidadeDiasConsulta;
	}

	/**
	 * Retorna a Quantidade de Dias
	 * @return int - Quantidade de Dias
	 * @implemented by Daniel Balieiro
	 */
	public int getQuantidadeDiasConsulta()
	{
		return quantidadeDiasConsulta;
	}

	/**
	 * Método usado para comparar MultaVO
	 * @param o (MultaVo) que vai ser comparado
	 * @return int
	 * @implemented by Daniel Balieiro
	 */
	public int compareTo(Object o)
	{
		try
		{
			MultaVo multaVo = (MultaVo) o;
			if (this.getQuantidadeDiasInicial() < multaVo.getQuantidadeDiasInicial())
			{
				return -1;
			}
			else if (this.getQuantidadeDiasInicial() > multaVo.getQuantidadeDiasInicial())
			{
				return +1;
			}
			else
			{
				return 0;
			}
		}
		catch (ClassCastException e)
		{
			return 0;
		}
	}

	public int hashCode()
	{
		int hashAtual = 0;
		int hashCollVO = getCollVO().size() * MULTIPLICADOR_HASH_CODE;
		hashAtual += hashCollVO;
		hashAtual += getQuantidadeDiasInicial();
	   hashAtual += getQuantidadeDiasFinal();
		hashAtual += getPercentualMulta();
		hashAtual *= MULTIPLICADOR_HASH_CODE;
		return hashAtual;    
	}	
	
}
