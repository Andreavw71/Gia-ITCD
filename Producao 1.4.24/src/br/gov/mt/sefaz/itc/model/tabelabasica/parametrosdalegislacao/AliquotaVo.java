package br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposParametroLegislacaoAliquota;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;


/**
 * Classe responsável por encapsular os valores do objeto Aliquota (Value Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_PARAMETRO_LEGISLACAO_ALIQUOTA
     ,nomeAmigavel = "Alíquota Parâmetro Legislação"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposParametroLegislacaoAliquota.CAMPO_CODIGO_ALIQUOTA
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_PARAMETRO_LEGISLACAO_ALIQUOTA
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class AliquotaVo extends EntidadeVo implements Comparable
{
	private DomnSimNao tipoIsencao;
	private DomnTipoGIA tipoFatoGerador;
	private double percentualLegislacaoAliquota;
	private int quantidadeUPFConsulta; //usado para fazer consulta(pra verificar se uma aliquota esta em uma faixa)
	private int quantidadeUPFFinal;
	private int quantidadeUPFInicial;
	private long codigoParametroLegislacao;
	private static final long serialVersionUID = Long.MAX_VALUE;

	/**
	 * Construtor Padrão
	 * @implemented by Daniel Balieiro
	 */
	public AliquotaVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a Chave Primária como Parametro
	 * @param codigo Chave Primária
	 * @implemented by Daniel Balieiro
	 */
	public AliquotaVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe uma Collection de AliquotaVo
	 * @param collVo Coleção de AliquotaVo
	 * @implemented by Daniel Balieiro
	 */
	public AliquotaVo(Collection collVo)
	{
		super(collVo);
	}

	/**
	 * Construtor que recebe uma Parametro de Consulta
	 * @param aliquotaVo Parametro de Consulta
	 * @implemented by Daniel Balieiro
	 */
	public AliquotaVo(AliquotaVo aliquotaVo)
	{
		super();
		setParametroConsulta(aliquotaVo);
	}

	/**
	 * Retorna a Chave Primária da Aliquota
	 * @return AliquotaPk - Chave Primária
	 * @implemented by Daniel Balieiro
	 */
	public AliquotaPk getPk()
	{
		return new AliquotaPk(getCodigo());
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Método para alterar o Tipo Fato Gerador
	 * @param tipoFatoGerador Tipo Fato Gerador
	 * @implemented by Daniel Balieiro
	 */
	public void setTipoFatoGerador(DomnTipoGIA tipoFatoGerador)
	{
		this.tipoFatoGerador = tipoFatoGerador;
	}

	/**
	 * Método retorna o Tipo Fato Gerador
	 * @return DomnTipoFatoGerador - Tipo Fato Gerador
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacaoAliquota.CAMPO_TIPO_FATOR_GERADOR
	     ,obrigatorio = true
	 )
	public DomnTipoGIA getTipoFatoGerador()
	{
		if (!Validador.isDominioNumericoValido(tipoFatoGerador))
		{
			setTipoFatoGerador(new DomnTipoGIA(-1));
		}
		return tipoFatoGerador;
	}

	/**
	 * Método para alterar o Tipo Isenção
	 * @param tipoIsencao Tipo Isenção
	 * @implemented by Daniel Balieiro
	 */
	public void setTipoIsencao(DomnSimNao tipoIsencao)
	{
		this.tipoIsencao = tipoIsencao;
	}

	/**
	 * Método que retorna o Tipo Isenção
	 * @return DomnSimNao - Tipo Isenção
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacaoAliquota.CAMPO_TIPO_ISENCAO
	     ,obrigatorio = true
	 )
	public DomnSimNao getTipoIsencao()
	{
		if (!Validador.isDominioNumericoValido(tipoIsencao))
		{
			setTipoIsencao(new DomnSimNao(-1));
		}
		return tipoIsencao;
	}

	/**
	 * Método para alterar o Percentual Legislação Aliquota
	 * @param percentualLegislacaoAliquota - Percentual Legislação Aliquota
	 * @implemented by Daniel Balieiro
	 */
	public void setPercentualLegislacaoAliquota(double percentualLegislacaoAliquota)
	{
		this.percentualLegislacaoAliquota = percentualLegislacaoAliquota;
	}

	/**
	 * Método que retorna o Percentual Legislação Aliquota
	 * @return double - Percentual Legislação Aliquota
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacaoAliquota.CAMPO_PERCENTUAL_LEGISLACAO_ALIQUOTA
	     ,obrigatorio = false
	 )
	public double getPercentualLegislacaoAliquota()
	{
		return percentualLegislacaoAliquota;
	}

	/**
	 * Retorna um Percentual Legislação Aliquotaa formatado
	 * @return String - Percentual Legislação Aliquota formatado
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getPercentualLegislacaoAliquotaFormatado()
	{
		return StringUtil.doubleToMonetario(percentualLegislacaoAliquota);
	}

	/**
	 * Método para alterar a Quantidade de UPF Inicial
	 * @param quantidadeUPFInicial Quantidade de UPF Inicial
	 * @implemented by Daniel Balieiro
	 */
	public void setQuantidadeUPFInicial(int quantidadeUPFInicial)
	{
		this.quantidadeUPFInicial = quantidadeUPFInicial;
	}

	/**
	 * Método que retorna a quantidade de UPF Inicial
	 * @return int - Quantidade de UPF Inicial
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacaoAliquota.CAMPO_QUANTIDADE_UPF_INICIAL
	     ,obrigatorio = true
	 )
	public int getQuantidadeUPFInicial()
	{
		return quantidadeUPFInicial;
	}

	/**
	 * Método que retorna a Quantidade de UPF Inicial Formatada
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getQuantidadeUPFInicialFormatada()
	{
		if (!Validador.isNumericoValido(getQuantidadeUPFInicial()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getQuantidadeUPFInicial());
	}

	/**
	 * Método para alterara a Quantidade de UPF Final
	 * @param quantidadeUPFFinal Quantidade de UPF Final
	 * @implemented by Daniel Balieiro
	 */
	public void setQuantidadeUPFFinal(int quantidadeUPFFinal)
	{
		this.quantidadeUPFFinal = quantidadeUPFFinal;
	}

	/**
	 * Método que retorna a Quantidade de UPF Final
	 * @return int - Quantidade de UPF Final
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacaoAliquota.CAMPO_QUANTIDADE_UPF_FINAL
	     ,obrigatorio = false
	 )
	public int getQuantidadeUPFFinal()
	{
		return quantidadeUPFFinal;
	}

	/**
	 * Método que retorna a Quantidade de UPF Final Formatada
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getQuantidadeUPFFinalFormatada()
	{
		if (!Validador.isNumericoValido(getQuantidadeUPFFinal()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getQuantidadeUPFFinal());
	}

	/**
	 * Atribuiu um código do Parametro de Legislação
	 * @param codigoParametroLegislacao
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigoParametroLegislacao(long codigoParametroLegislacao)
	{
		this.codigoParametroLegislacao = codigoParametroLegislacao;
	}

	/**
	 * Retorna um Código de Parametro de Legislação
	 * @return long - Código de Legislação
	 * @implemented by Daniel Balieiro
	 */
	@AnotacaoAtributo
	(
	    nomeColuna = CamposParametroLegislacaoAliquota.CAMPO_ITCTB09_CODIGO_PARAMETRO_LEGISLACAO
	    ,obrigatorio = true
	)
	public long getCodigoParametroLegislacao()
	{
		return codigoParametroLegislacao;
	}

	/**
	 * Atribui uma Quantidade de UPF para Consulta por perídodo
	 * @param quantidadeUPFConsulta Quantidade de UPF
	 * @implemented by Daniel Balieiro
	 */
	public void setQuantidadeUPFConsulta(int quantidadeUPFConsulta)
	{
		this.quantidadeUPFConsulta = quantidadeUPFConsulta;
	}

	/**
	 * Retorna a Quantidade de UPF de Consulta 
	 * @return int Quantidade de UPF 
	 * @implemented by Daniel Balieiro
	 */
	public int getQuantidadeUPFConsulta()
	{
		return quantidadeUPFConsulta;
	}
	
	public int hashCode()
	{
		int hashAtual = 0;
		int hashCollVO = getCollVO().size() * MULTIPLICADOR_HASH_CODE;
		hashAtual += hashCollVO;
		hashAtual += (int) getCodigo();
		if(Validador.isDominioNumericoValido(getTipoFatoGerador()))
			hashAtual += getTipoFatoGerador().getValorCorrente();
		hashAtual += getPercentualLegislacaoAliquota();
		hashAtual += getQuantidadeUPFInicial();
		hashAtual += getQuantidadeUPFFinal();
		hashAtual *= MULTIPLICADOR_HASH_CODE;
		return hashAtual;
	}
	
	/**
	 * Método usado para comparar AliquotaVo
	 * @param o (AliquotaVo) que vai ser comparado
	 * @return int
	 * @implemented by Daniel Balieiro
	 */
	public int compareTo(Object o)
	{
		try
		{
			AliquotaVo aliquotaVo = (AliquotaVo) o;
			if (this.getQuantidadeUPFInicial() < aliquotaVo.getQuantidadeUPFInicial())
			{
				return -1;
			}
			else if (this.getQuantidadeUPFInicial() > aliquotaVo.getQuantidadeUPFInicial())
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
}
