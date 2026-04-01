package br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposNaturezaOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores do objeto Natureza Operação (Value Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_NATUREZA_OPERACAO
     ,nomeAmigavel = "Natureza da Operação"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposNaturezaOperacao.CAMPO_CODIGO_NATUREZA_OPERACAO
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_NATUREZA_OPERACAO
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class NaturezaOperacaoVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private Date dataAtualizacao;
	private DomnSimNao tipoBaseCalculo;
	private DomnSimNao flagIsencaoPrevistaLei;
	private DomnStatusRegistro statusNaturezaOperacao;
	private DomnTipoGIA tipoGIA;
	private DomnTipoProcesso tipoProcesso;
	private double percentualBaseCalculo;
	private String descricaoNaturezaOperacao;
	
	/**
	 * Construtor Padrão
	 * @implemented by Daniel Balieiro
	 */
	public NaturezaOperacaoVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a chave primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public NaturezaOperacaoVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe um parametro de consulta
	 * @param naturezaOperacaoVo
	 * @implemented by Daniel Balieiro
	 */
	public NaturezaOperacaoVo(NaturezaOperacaoVo naturezaOperacaoVo)
	{
		super();
		setParametroConsulta(naturezaOperacaoVo);
	}

	/**
	 * Construtor que recebe uma coleção
	 * @param collVo
	 * @implemented by Daniel Balieiro
	 */
	public NaturezaOperacaoVo(Collection collVo)
	{
		super(collVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui uma Descrição
	 * @param descricaoNaturezaOperacao
	 * @implemented by Daniel Balieiro
	 */
	public void setDescricaoNaturezaOperacao(String descricaoNaturezaOperacao)
	{
		if (Validador.isStringValida(descricaoNaturezaOperacao))
		{
			this.descricaoNaturezaOperacao = descricaoNaturezaOperacao.trim().toUpperCase();
		}
		else
		{
			this.descricaoNaturezaOperacao = descricaoNaturezaOperacao;
		}
	}

	/**
	 * Retorna a Descrição da Natureza da Operação
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposNaturezaOperacao.CAMPO_DESCRICAO_NATUREZA_OPERACAO
	     ,obrigatorio = true
	 )
	public String getDescricaoNaturezaOperacao()
	{
		if (!Validador.isStringValida(descricaoNaturezaOperacao))
		{
			return STRING_VAZIA;
		}
		return descricaoNaturezaOperacao;
	}

	/**
	 * Atribui um Percentual da base de cálculo da natureza da operação.
	 * @param percentualBaseCalculo
	 * @implemented by Daniel Balieiro
	 */
	public void setPercentualBaseCalculo(double percentualBaseCalculo)
	{
		this.percentualBaseCalculo = percentualBaseCalculo;
	}

	/**
	 * Retorna Percentual da base de cálculo da natureza da operação.
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposNaturezaOperacao.CAMPO_PERCENTUAL_NATUREZA_OPERACAO
	     ,obrigatorio = false
	 )
	public double getPercentualBaseCalculo()
	{
		return percentualBaseCalculo;
	}

	/**
	 * Retorna Percentual da base de cálculo da natureza da operação, formatado.
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	public String getPercentualBaseCalculoFormatada()
	{
		if(Validador.isNumericoValido(getPercentualBaseCalculo()))
		{
			return StringUtil.doubleToMonetario(percentualBaseCalculo, getQuantidadeCasasDecimais());	
		}
		return "-";
	}

	/**
	 * Atribui o Tipo de GIA
	 * @param tipoGia
	 * @implemented by Daniel Balieiro
	 */
	public void setTipoGIA(DomnTipoGIA tipoGia)
	{
		this.tipoGIA = tipoGia;
	}

	/**
	 * Retorna Tipo GIA
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposNaturezaOperacao.CAMPO_TIPO_GIA
	     ,obrigatorio = true
	 )
	public DomnTipoGIA getTipoGIA()
	{
		if (!Validador.isDominioNumericoValido(tipoGIA))
		{
			setTipoGIA(new DomnTipoGIA(-1));
		}
		return tipoGIA;
	}

	/**
	 * Atribui um Tipo de Base Calculo
	 * @param tipoBaseCalculo
	 * @implemented by Daniel Balieiro
	 */
	public void setTipoBaseCalculo(DomnSimNao tipoBaseCalculo)
	{
		this.tipoBaseCalculo = tipoBaseCalculo;
	}

	/**
	 * Retorna Tipo Base de Calculo
	 * @return DomnSimNao
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposNaturezaOperacao.CAMPO_TIPO_BASE_CALCULO
	     ,obrigatorio = true
	 )
	public DomnSimNao getTipoBaseCalculo()
	{
		if (!Validador.isDominioNumericoValido(tipoBaseCalculo))
		{
			setTipoBaseCalculo(new DomnSimNao(-1));
		}
		return tipoBaseCalculo;
	}

	/**
	 * Atribui um Tipo de Processo
	 * @param tipoProcesso
	 * @implemented by Daniel Balieiro
	 */
	public void setTipoProcesso(DomnTipoProcesso tipoProcesso)
	{
		this.tipoProcesso = tipoProcesso;
	}

	/**
	 * Retorna o Tipo de Processo
	 * @return DomnTipoProcesso
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposNaturezaOperacao.CAMPO_TIPO_PROCESSO
	     ,obrigatorio = true
	 )
	public DomnTipoProcesso getTipoProcesso()
	{
		if (!Validador.isDominioNumericoValido(tipoProcesso))
		{
			setTipoProcesso(new DomnTipoProcesso(-1));
		}
		return tipoProcesso;
	}

	/**
	 * Atribui o Status da Natureza da Operação
	 * @param statusNaturezaOperacao
	 * @implemented by Daniel Balieiro
	 */
	public void setStatusNaturezaOperacao(DomnStatusRegistro statusNaturezaOperacao)
	{
		this.statusNaturezaOperacao = statusNaturezaOperacao;
	}

	/**
	 * Retorna o Status da Natureza da Operação
	 * @return DomnStatusRegistro
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposNaturezaOperacao.CAMPO_STATUS_NATUREZA_OPERACAO
	     ,obrigatorio = true
	 )
	public DomnStatusRegistro getStatusNaturezaOperacao()
	{
		if (!Validador.isDominioNumericoValido(statusNaturezaOperacao))
		{
			setStatusNaturezaOperacao(new DomnStatusRegistro(-1));
		}
		return statusNaturezaOperacao;
	}

	/**
	 * Atribui a Data da ultima Atualização
	 * @param dataAtualizacao
	 * @implemented by Daniel Balieiro
	 */
	public void setDataAtualizacao(Date dataAtualizacao)
	{
		this.dataAtualizacao = dataAtualizacao;
	}

	/**
	 * Retorna a Data da ultima Atualização
	 * @return Date
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposNaturezaOperacao.CAMPO_DATA_ATUALIZACAO_BD
	     ,obrigatorio = true
		  ,timestamp = true
	 )
	public Date getDataAtualizacao()
	{
		return dataAtualizacao;
	}

	/**
	 * Retorna a Data da ultima Atualização
	 * @return Date
	 * @implemented by Leandro Dorileo
	 */
	public String getDataAtualizacaoFormatada()
	{
		if (Validador.isDataValida(getDataAtualizacao()))
		{
			return new SefazDataHora(getDataAtualizacao()).formata("dd/MM/yyyy");
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	public void setFlagIsencaoPrevistaLei(DomnSimNao flagIsencaoPrevistaLei)
	{
		this.flagIsencaoPrevistaLei = flagIsencaoPrevistaLei;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposNaturezaOperacao.CAMPO_FLAG_ISENCAO_PREVISTA_LEI
	    ,obrigatorio = false
	)
	public DomnSimNao getFlagIsencaoPrevistaLei()
	{
		if(!Validador.isDominioNumericoValido(flagIsencaoPrevistaLei))
		{
			setFlagIsencaoPrevistaLei(new DomnSimNao());
		}
		return flagIsencaoPrevistaLei;
	}
	
	public String getFlagIsencaoPrevistaLeiFormatada()
	{
		if(Validador.isDominioNumericoValido(getFlagIsencaoPrevistaLei()))
		{
			return getFlagIsencaoPrevistaLei().getTextoCorrente();
		}
		return "-";
	}

	public void ordenaPorTipoGiaTipoProcessoCodigo()
	{
		if(Validador.isCollectionValida(this.getCollVO()))
		{
			Collections.sort
			(
				(List) this.getCollVO()
				, new Comparator()
				{
						public int compare(Object o1, Object o2)
						{
							NaturezaOperacaoVo obj1 = (NaturezaOperacaoVo) o1;
							NaturezaOperacaoVo obj2 = (NaturezaOperacaoVo) o2;
							int valor = new Integer(obj1.getTipoGIA().getValorCorrente()).compareTo(new Integer(obj2.getTipoGIA().getValorCorrente()));
							if(valor == 0)
							{
								valor = new Integer(obj1.getTipoProcesso().getValorCorrente()).compareTo(new Integer(obj2.getTipoProcesso().getValorCorrente()));
								if(valor == 0)
								{
									valor = new Long(obj1.getCodigo()).compareTo(new Long(obj2.getCodigo()));	
								}								
							}
							return valor;
						}
				}
			);
		}
	}

	public int getQuantidadeCasasDecimais()
	{
		return 3;
	}	
}
