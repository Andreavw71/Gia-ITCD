/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDInventarioArrolamentoVo.java
 * Revisão:
 * Data revisão:
 * $Id: GIAITCDInventarioArrolamentoVo.java,v 1.9 2009/03/13 14:06:58 ricardo.moraes Exp $
 */
package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.GIAITCDInventarioArrolamentoBeneficiarioVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnEstadoCivil;
import br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoInventario;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoRenuncia;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDInventarioArrolamento;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.UFIntegracaoVO;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores do objeto Iventário Arrolamento (Value Object).
 * @author Lucas Nascimento
 * @version $Revision: 1.9 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_GIA_ITCD_INVENTARIO_ARROLAMENTO
     ,nomeAmigavel = "GIA-ITCD Inventário & Arrolamento"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_ITCTB14_CODIGO_GIA_ITCD
			)
     }
 )
public class GIAITCDInventarioArrolamentoVo extends GIAITCDVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	
	private ContribuinteIntegracaoVo pessoaMeeiro;
	private ContribuinteIntegracaoVo pessoaDeCujus;
	private UFIntegracaoVO ufAbertura;
	private DomnEstadoCivil estadoCivilDeCujus;
	private DomnRegimeCasamento regimeCasamento;
	private long numeroProcesso;
	private Date dataInventario;
	private Date dataFalecimento;
	private String descricaoJuizoComarca;
	private double fracaoIdeal;
	private double percentualMulta;
	private double valorMulta;
	private Date dataProcesso;
	private transient ContribuinteIntegracaoVo contribuinteIntegracaoVo;
	private DomnSimNao renuncia;
	private DomnSimNao herdeirosDescendentes;
	private int numeroHerdeirosDescendentes;	
	private DomnTipoRenuncia tipoRenuncia;
	private DomnSimNao herdeirosAscendentes;
	private int numeroHerdeirosAscendentes;
	private int numeroOutrosHerdeiros;
	private int numeroHerdeiros;
	private GIAITCDInventarioArrolamentoBeneficiarioVo meeiroBeneficiario;
	private DomnSimNao flagMeeiroBeneficiario;
	private boolean exibeMeeiroBeneficiario;
	private transient boolean meeiroIncluidoPeloServidor;
   private DomnTipoProcessoInventario tipoProcessoInventario;
    

	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Retorna a data do processo formatada
	 * @return
	 */
	public String getDataProcessoFormatada()
	{
		Date data = getDataProcesso();
		SefazDataHora sefazData = new SefazDataHora(data);
		return sefazData.formata();
	}

	/**
	 * Construtor vazio
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoVo()
	{
		super();
	}

	/**
	 * Construtor passando a chave primária
	 * @param codigo
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor passando o parametro de consulta
	 * @param giaITCDIventarioArrolamentoVo
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoVo(GIAITCDInventarioArrolamentoVo giaITCDIventarioArrolamentoVo)
	{
		super();
		setParametroConsulta(giaITCDIventarioArrolamentoVo);
	}

	/**
	 * Construtor passando uma Collection
	 * @param collVO
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoVo(Collection collVO)
	{
		super(collVO);
	}

	/**
	 * Atribui a data de falecimento do iventário/arrolamento
	 * @param dataFalecimento
	 * @implemented by Lucas Nascimento
	 */
	public void setDataFalecimento(Date dataFalecimento)
	{
		this.dataFalecimento = dataFalecimento;
	}

	/**
	 * Retorna a data de falecimento do iventário/arrolamento
	 * @return data de falecimento
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_DATA_FALECIMENTO
	     ,obrigatorio = true
	 )
	public Date getDataFalecimento()
	{
		return this.dataFalecimento;
	}

	/**
	 * Retorna a Data de Falecimento formatada
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public String getDataFalecimentoFormatada()
	{
		if (Validador.isDataValida(dataFalecimento))
		{
			return new SefazDataHora(dataFalecimento).formata(MASCARA_DATA_FORMATADA);
		}
		else
		{
			return "";
		}
	}

	/**
	 * Atribui a data do iventário/arrolamento
	 * @param dataInventario
	 * @implemented by Lucas Nascimento
	 */
	public void setDataInventario(Date dataInventario)
	{
		this.dataInventario = dataInventario;
	}

	/**
	 * Retorna a data do iventário/arrolamento
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_DATA_INVENTARIO
	     ,obrigatorio = true
	 )
	public Date getDataInventario()
	{
		return this.dataInventario;
	}

	/**
	 * Retorna a data de inventário formatada
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public String getDataInventarioFormatada()
	{
		if (Validador.isDataValida(dataInventario))
		{
			return new SefazDataHora(dataInventario).formata(MASCARA_DATA_FORMATADA);
		}
		else
		{
			return "";
		}
	}

	/**
	 * Atribui o regime de casamento
	 * @param regimeCasamento
	 * @implemented by Lucas Nascimento
	 */
	public void setRegimeCasamento(DomnRegimeCasamento regimeCasamento)
	{
		this.regimeCasamento = regimeCasamento;
	}

	/**
	 * Retorna o regime de casamento 
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_REGIMENTO_CASAMENTO
	     ,obrigatorio = false
	 )
	public DomnRegimeCasamento getRegimeCasamento()
	{
		if (!Validador.isDominioNumericoValido(regimeCasamento))
		{
			setRegimeCasamento(new DomnRegimeCasamento(-1));
		}
		return regimeCasamento;
	}

	/**
	 * Atribui o numero de herdeiros 
	 * @param numeroHerdeiros
	 * @implemented by Lucas Nascimento
	 */
	public void setNumeroHerdeirosDescendentes(int numeroHerdeiros)
	{
		this.numeroHerdeirosDescendentes = numeroHerdeiros;
	}

	/**
	 * Retorna o numero de herdeiros
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public int getNumeroHerdeirosDescendentes()
	{
		return numeroHerdeirosDescendentes;
	}

	/**
	 * Método responsável por devolver o numero de herdeiros formatado.
	 * Se o numero de herdeiros for 0 ele devolve uma STRING_VAZIA, senao retorna
	 * o valor existente. Geralmente usado na taglib na JSP
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getNumeroHerdeirosDescendentesFormatado()
	{
		if (!Validador.isNumericoValido(getNumeroHerdeirosDescendentes()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getNumeroHerdeirosDescendentes());
	}

	/**
	 * Atribui a pessoa de cujus
	 * @param pessoaDeCujus
	 * @implemented by Lucas Nascimento
	 */
	public void setPessoaDeCujus(ContribuinteIntegracaoVo pessoaDeCujus)
	{
		this.pessoaDeCujus = pessoaDeCujus;
	}

	/**
	 * Retorna a pessoa de cujus
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	@AnotacaoAtributoExterno
	(
	    obrigatorio = true
	    ,colunaReferencia =
	    {
	        @AnotacaoColunaExterna
	        (
	            nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_ACCTB01_NUMERO_PESSOA_DE_CUJUS
	            , nomeAtributo = "numrContribuinte"
	        )
	    }
	)	 
	public ContribuinteIntegracaoVo getPessoaDeCujus()
	{
		if(!Validador.isObjetoValido(pessoaDeCujus))
		{
		   setPessoaDeCujus(new ContribuinteIntegracaoVo());
		}
		return pessoaDeCujus;
	}

	/**
	 * Atribui a pessoa meeiro
	 * @param pessoaMeeiro
	 * @implemented by Lucas Nascimento
	 */
	public void setPessoaMeeiro(ContribuinteIntegracaoVo pessoaMeeiro)
	{
		this.pessoaMeeiro = pessoaMeeiro;
	}

	/**
	 * Retorna a pessoa meeiro
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	@AnotacaoAtributoExterno
	(
	    obrigatorio = false
	    ,colunaReferencia =
	    {
	        @AnotacaoColunaExterna
	        (
	            nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_ACCTB01_NUMERO_PESSOA_MEEIRO
	            , nomeAtributo = "numrContribuinte"
	        )
	    }
	)	 
	public ContribuinteIntegracaoVo getPessoaMeeiro()
	{
		if(!Validador.isObjetoValido(pessoaMeeiro))	
		{
			setPessoaMeeiro(new ContribuinteIntegracaoVo());
		}
		return pessoaMeeiro;
	}

	/**
	 * Atribui a descrição do juiso da comarca
	 * @param descricaoJuizoComarca
	 * @implemented by Lucas Nascimento
	 */
	public void setDescricaoJuizoComarca(String descricaoJuizoComarca)
	{
		if (Validador.isStringValida(descricaoJuizoComarca))
		{
			this.descricaoJuizoComarca = descricaoJuizoComarca.trim().toUpperCase();
		}
		else
		{
			this.descricaoJuizoComarca = descricaoJuizoComarca;
		}
	}

	/**
	 * Retorna a descricao do juizo da comarca
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_DESCRICAO_JUIZO_COMARCA
	     ,obrigatorio = false
	 )
	public String getDescricaoJuizoComarca()
	{
		if (!Validador.isStringValida(descricaoJuizoComarca))
		{
			return STRING_VAZIA;
		}
		return descricaoJuizoComarca;
	}

	/**
	 * Atribui a UF de Abertura
	 * @param ufIntegracaoVo
	 * @implemented by Daniel Balieiro
	 */
	public void setUfAbertura(UFIntegracaoVO ufIntegracaoVo)
	{
		this.ufAbertura = ufIntegracaoVo;
	}

	/**
	 * Retorna a UF de Abertura
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = true
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_SIGLA_UF_ABERTURA
	             , nomeAtributo = "siglUf"
	         )
	     }
	 )
	public UFIntegracaoVO getUfAbertura()
	{
		try
		{
			Validador.validaObjeto(ufAbertura);
		}
		catch (ObjetoObrigatorioException e)
		{
			setUfAbertura(new UFIntegracaoVO());
		}
		return ufAbertura;
	}

	/**
	 * Atribui o Estado Civil de Cujus
	 * @param estadoCivilDeCujus
	 * @implemented by Daniel Balieiro
	 */
	public void setEstadoCivilDeCujus(DomnEstadoCivil estadoCivilDeCujus)
	{
		this.estadoCivilDeCujus = estadoCivilDeCujus;
	}

	/**
	 * Retorna o Estado Civil de Cujus
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_SITUACAO_ESTADO_CIVIL
	     ,obrigatorio = true
	 )
	public DomnEstadoCivil getEstadoCivilDeCujus()
	{
		if (!Validador.isDominioNumericoValido(estadoCivilDeCujus))
		{
			setEstadoCivilDeCujus(new DomnEstadoCivil(-1));
		}
		return estadoCivilDeCujus;
	}

	/**
	 * Atribui o Número de Processo
	 * @param numeroProcesso
	 * @implemented by Daniel Balieiro
	 */
	public void setNumeroProcesso(long numeroProcesso)
	{
		this.numeroProcesso = numeroProcesso;
	}

	/**
	 * Retorna o Número do Processo
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_NUMERO_PROCESSO
	     ,obrigatorio = false
	 )
	public long getNumeroProcesso()
	{
		return numeroProcesso;
	}

	/**
	 * Método responsável por devolver o numero processo formatado.
	 * Se o numero processo for 0 ele devolve uma STRING_VAZIA, senao retorna
	 * o valor existente. Geralmente usado na taglib na JSP
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getNumeroProcessoFormatado()
	{
		if (!Validador.isNumericoValido(getNumeroProcesso()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getNumeroProcesso());
	}

	/**
	 * Atribui a Data de Processo
	 * @param dataProcesso
	 * @implemented by Daniel Balieiro
	 */
	public void setDataProcesso(Date dataProcesso)
	{
		this.dataProcesso = dataProcesso;
	}

	/**
	 * Retorna a data do processo
	 * @return
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_DATA_PROCESSO
	     ,obrigatorio = false
	 )
	public Date getDataProcesso()
	{
		if (!Validador.isDataValida(this.dataProcesso))
		{
			setDataProcesso(new Date());
		}
		return this.dataProcesso;
	}

	/**
	 * Atribui a Fraçao Ideal
	 * @param fracaoIdeal
	 * @implemented by Daniel Balieiro
	 */
	public void setFracaoIdeal(double fracaoIdeal)
	{
		this.fracaoIdeal = fracaoIdeal;
	}

	/**
	 * Retorna a Fraçao Ideal
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_FRAC_IDEAL
	     ,obrigatorio = true
	 )
	public double getFracaoIdeal()
	{
		return fracaoIdeal;
	}

	/**
	 * Método que retorna a fração ideal formatada
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getFracaoIdealFormatada()
	{
		return StringUtil.doubleToMonetario(getFracaoIdeal());
	}

	/**
	 * Atribui o Percentual de Multa
	 * @param percentualMulta
	 * @implemented by Daniel Balieiro
	 */
	public void setPercentualMulta(double percentualMulta)
	{
		this.percentualMulta = percentualMulta;
	}

	/**
	 * Retorna o Percentual de Multa
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_PERC_MULTA
	     ,obrigatorio = false
	 )
	public double getPercentualMulta()
	{
		return percentualMulta;
	}

	/**
	 * Método que retorna o percentual da multa formatado
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getPercentualMultaFormatado()
	{
		return StringUtil.doubleToMonetario(getPercentualMulta());
	}

	/**
	 * Atribui o Valor Multa
	 * @param valorMulta
	 * @implemented by Daniel Balieiro
	 */
	public void setValorMulta(double valorMulta)
	{
		this.valorMulta = valorMulta;
	}

	/**
	 * Retorna o Valor da Multa
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_VALR_MULTA
	     ,obrigatorio = false
	 )
	public double getValorMulta()
	{
		return this.valorMulta;
	}

	/**
	 * Método que retorna o valor da multa formatada
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getValorMultaFormatada()
	{
		return StringUtil.doubleToMonetario(getValorMulta());
	}

	/**
	 * Retorna o Valor Cálculado da Fração Ideal
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public double getValorFracaoIdeal()
	{
		return getValorTotalBensDeclarados() * getFracaoIdeal() / 100;
	}

	/**
	 * Retorna o Valor Fracao Ideal Formatado
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public String getValorFracaoIdealFormatado()
	{
		if (!Validador.isNumericoValido(getValorFracaoIdeal()))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(getValorFracaoIdeal());
	}
	
	/**
	 * Este método retornará o Contribuinte que será usado para a emissão da notificação 
	 * e para a emissão do dar e seguirá a seguinte regra:
	 * <ul>
	 *    <li>GIAITCDInventarioArrolamentoVo: Será o primeiro herdeiro da lista de herdeiros.</li>
	 *    <li>GIAITCDDoacaoVo: Será o primeiro herdeiro não isento da lista de beneficiarios.</li>
	 *    <li>GIAITCDSeparacaoDivorcioVo: Será o conjuge que receber a maior parte da divisão de bens.</li>
	 * </ul>
	 * @return
	 */
	public ContribuinteIntegracaoVo getContribuinteNotificacaoDar()
	{
		GIAITCDInventarioArrolamentoBeneficiarioVo beneficiario = new GIAITCDInventarioArrolamentoBeneficiarioVo();
		if(Validador.isCollectionValida(getBeneficiarioVo().getCollVO()))
		{
		   beneficiario = (GIAITCDInventarioArrolamentoBeneficiarioVo) ((List) getBeneficiarioVo().getCollVO()).get(0);
		}
		else if(getMeeiroBeneficiario().getFlagBeneficiarioMeeiro().is(DomnSimNao.SIM))
		{
		   beneficiario = getMeeiroBeneficiario();			
		}
		return beneficiario.getPessoaBeneficiaria();
	}

	public void setContribuinteIntegracaoVo(ContribuinteIntegracaoVo contribuinteIntegracaoVo)
	{
		this.contribuinteIntegracaoVo = contribuinteIntegracaoVo;
	}

	public ContribuinteIntegracaoVo getContribuinteIntegracaoVo()
	{
		if(!Validador.isObjetoValido(contribuinteIntegracaoVo))
		{
			return new ContribuinteIntegracaoVo();
		}
		return contribuinteIntegracaoVo;
	}

	public void setRenuncia(DomnSimNao renuncia)
	{
		this.renuncia = renuncia;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_FLAG_RENUNCIA
	    ,obrigatorio = true
	)
	public DomnSimNao getRenuncia()
	{
		if(!Validador.isDominioNumericoValido(renuncia))
		{
			return new DomnSimNao();
		}
		return renuncia;
	}

	public void setHerdeirosDescendentes(DomnSimNao herdeirosDescendentes)
	{
		this.herdeirosDescendentes = herdeirosDescendentes;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_FLAG_HERDEIROS_DESCENDENTES
	    ,obrigatorio = true
	)
	public DomnSimNao getHerdeirosDescendentes()
	{
		if(!Validador.isDominioNumericoValido(herdeirosDescendentes))
		{
			return new DomnSimNao();
		}
		return herdeirosDescendentes;
	}

	public void setTipoRenuncia(DomnTipoRenuncia tipoRenuncia)
	{
		this.tipoRenuncia = tipoRenuncia;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_TIPO_RENUNCIA
	    ,obrigatorio = false
	)
	public DomnTipoRenuncia getTipoRenuncia()
	{
		if(!Validador.isDominioNumericoValido(tipoRenuncia))
		{
			return new DomnTipoRenuncia();
		}
		return tipoRenuncia;
	}

	public void setHerdeirosAscendentes(DomnSimNao herdeirosAscendentes)
	{
		this.herdeirosAscendentes = herdeirosAscendentes;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_FLAG_HERDEIROS_ASCENDENTES
	    ,obrigatorio = false
	)
	public DomnSimNao getHerdeirosAscendentes()
	{
		if(!Validador.isDominioNumericoValido(herdeirosAscendentes))
		{
			return new DomnSimNao();
		}
		return herdeirosAscendentes;
	}

	public void setNumeroHerdeirosAscendentes(int numeroHerdeirosAscendentes)
	{
		this.numeroHerdeirosAscendentes = numeroHerdeirosAscendentes;
	}

	public int getNumeroHerdeirosAscendentes()
	{
		return numeroHerdeirosAscendentes;
	}

	public void setNumeroOutrosHerdeiros(int numeroOutrosHerdeiros)
	{
		this.numeroOutrosHerdeiros = numeroOutrosHerdeiros;
	}

	public int getNumeroOutrosHerdeiros()
	{	
		return numeroOutrosHerdeiros;
	}

	/**
	 * Método responsável por devolver o numero de herdeiros formatado.
	 * Se o numero de herdeiros for 0 ele devolve uma STRING_VAZIA, senao retorna
	 * o valor existente. Geralmente usado na taglib na JSP
	 * @return String
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public String getNumeroHerdeirosAscendentesFormatado()
	{
		if (!Validador.isNumericoValido(getNumeroHerdeirosAscendentes()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getNumeroHerdeirosAscendentes());
	}
	
	/**
	 * Método responsável por devolver o numero de herdeiros formatado.
	 * Se o numero de herdeiros for 0 ele devolve uma STRING_VAZIA, senao retorna
	 * o valor existente. Geralmente usado na taglib na JSP
	 * @return String
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public String getNumeroOutrosHerdeirosFormatado()
	{
		if (!Validador.isNumericoValido(getNumeroOutrosHerdeiros()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getNumeroOutrosHerdeiros());
	}

	public void setNumeroHerdeiros(int numeroHerdeiros)
	{
		this.numeroHerdeiros = numeroHerdeiros;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_NUMERO_HERDEIROS
	    ,obrigatorio = false
	)
	public int getNumeroHerdeiros()
	{
		return numeroHerdeiros;
	}

	public void setMeeiroBeneficiario(GIAITCDInventarioArrolamentoBeneficiarioVo meeiroBeneficiario)
	{
		this.meeiroBeneficiario = meeiroBeneficiario;
	}

	public GIAITCDInventarioArrolamentoBeneficiarioVo getMeeiroBeneficiario()
	{
		if(!Validador.isObjetoValido(meeiroBeneficiario))
		{
			setMeeiroBeneficiario(new GIAITCDInventarioArrolamentoBeneficiarioVo());
		}
		return meeiroBeneficiario;
	}
	
	public boolean isExibeFracaoIdealBensParticulares()
	{
		if(this.getEstadoCivilDeCujus().is(DomnEstadoCivil.CASADO) || this.getEstadoCivilDeCujus().is(DomnEstadoCivil.CONVIVENTE))
		{
			if(this.getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isEstadoCivilCasado()
	{
		return this.getEstadoCivilDeCujus().is(DomnEstadoCivil.CASADO);
	}
	
	public boolean isEstadoCivilConvivente()
	{
		return this.getEstadoCivilDeCujus().is(DomnEstadoCivil.CONVIVENTE);
	}
	
	public boolean isRegimeCasamentoComunhaoParcialBens()
	{
		return this.getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS);
	}
	
	public boolean isExisteHerdeirosAscendentes()
	{
		return this.getHerdeirosAscendentes().is(DomnSimNao.SIM);
	}
	
	public boolean isExisteHerdeiros()
	{
		if(this.getHerdeirosDescendentes().is(DomnSimNao.NAO) && this.getHerdeirosAscendentes().is(DomnSimNao.NAO))
		{
			if(isEstadoCivilCasado() || isEstadoCivilConvivente())
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		return true;
	}

	public double getValorMeacao()
	{
		if(this.isEstadoCivilCasado() || this.isEstadoCivilConvivente())
		{
			if(this.getRegimeCasamento().is(DomnRegimeCasamento.SEPARACAO_TOTAL_DE_BENS))
			{
				return 0;
			}
			else
			{
				return getValorTotalBensDeclaradosPosteriorCasamento()/2;
			}
		}
		return 0;
	}
	
	public String getValorMeacaoFormatado()
	{
		if(!Validador.isNumericoValido(getValorMeacao()))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(getValorMeacao());
	}

	public void setExibeMeeiroBeneficiario(boolean exibeMeeiroBeneficiario)
	{
		this.exibeMeeiroBeneficiario = exibeMeeiroBeneficiario;
	}

	public boolean isExibeMeeiroBeneficiario()
	{
		return exibeMeeiroBeneficiario;
	}

	public void setFlagMeeiroBeneficiario(DomnSimNao meeiroBeneficiario)
	{
		this.flagMeeiroBeneficiario = meeiroBeneficiario;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_FLAG_MEEIRO_BENEFICIARIO
	    ,obrigatorio = true
	)
	public DomnSimNao getFlagMeeiroBeneficiario()
	{
		if(!Validador.isDominioNumericoValido(flagMeeiroBeneficiario))
		{
			return new DomnSimNao();
		}
		return flagMeeiroBeneficiario;
	}

	public void setMeeiroIncluidoPeloServidor(boolean meeiroIncluidoPeloServidor)
	{
		this.meeiroIncluidoPeloServidor = meeiroIncluidoPeloServidor;
	}

	public boolean isMeeiroIncluidoPeloServidor()
	{
		return meeiroIncluidoPeloServidor;
	}


   public void setTipoProcessoInventario(DomnTipoProcessoInventario tipoProcessoInventario)
   {
      this.tipoProcessoInventario = tipoProcessoInventario;
   }  
   
   @AnotacaoAtributo
   (
       nomeColuna = CamposGIAITCDInventarioArrolamento.CAMPO_TIPO_PROCESSO_INVENTARIO
       ,obrigatorio = false
   )
   public DomnTipoProcessoInventario getTipoProcessoInventario()
   {
      if(!Validador.isDominioNumericoValido(this.tipoProcessoInventario))
      {
         return new DomnTipoProcessoInventario();
      }
      return tipoProcessoInventario;
   }
}
