package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.data.AbacoData;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.conjuge.ConjugeBemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.aliquota.GIAITCDSeparacaoDivorcioAliquotaVo;
import br.gov.mt.sefaz.itc.util.NumeroUtil;
import br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDSeparacaoDivorcio;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import sefaz.mt.util.SefazDataHora;


/**
 * GIA-ITCD Separação / Divórcio / Partilha (Value Object).
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.3 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_GIA_ITCD_SEPARACAO_DIVORCIO
     ,nomeAmigavel = "GIA-ITCD Separação / Divórcio"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposGIAITCDSeparacaoDivorcio.CAMPO_ITCBTB14_CODIGO_ITCD
			)
     }
 )
public class GIAITCDSeparacaoDivorcioVo extends GIAITCDVo
{
	private ConjugeBemTributavelVo conjuge1; //Conjuge1
	private ConjugeBemTributavelVo conjuge2; //Conjuge2
	private DomnRegimeCasamento regimeCasamento;
	private long numeroProcesso; //numero do processo de separaçao
	private Date dataSeparacao; //data do processo de separaçao	
	private double valorAliquota; //Valor da Alíquota de Cálculo
	private double valorIncidencia; //Valor de Incidência
	private Date dataCasamento;
	private static final long serialVersionUID = Long.MAX_VALUE;
	private ConjugeBemTributavelVo conjugeExcesso;
	private double valorTotalConjuge1;
	private double valorTotalConjuge2;
   private GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo;
   private double  valorTotalConjugeExcesso;
   
   // Atributos para apagar os bens tributaveis distribuidos ao mudar de protocolo.
   private String tipoProtocoloAnterior;
   private int aux = 0;
   
   public String getTipoProtocoloAnterior(){
      return tipoProtocoloAnterior;
   }
   
   public void setTipoProtocoloAnterior(String protocoloAnterior){
      this.tipoProtocoloAnterior = protocoloAnterior;
   }
   
   public int getAux(){
      return aux;
   }
   
   public void setAux(int aux1){
      this.aux = aux1;
   }
   

	/**
	 * Construtor vazio
	 * 
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public GIAITCDSeparacaoDivorcioVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a Chave Primária
	 * 
	 * @param codigo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public GIAITCDSeparacaoDivorcioVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recece o Parametro de Consulta
	 * 
	 * @param giaITCDSeparacaoDivorcioVo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public GIAITCDSeparacaoDivorcioVo(GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo)
	{
		super(giaITCDSeparacaoDivorcioVo);
	}

	/**
	 * Construtor que recebe uma Collection de GIAITCDSeparacaoDivorcioVo
	 * 
	 * @param collVo
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public GIAITCDSeparacaoDivorcioVo(Collection collVo)
	{
		super(collVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui um Regime Casamento
	 * 
	 * @param regimeCasamento
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setRegimeCasamento(DomnRegimeCasamento regimeCasamento)
	{
		this.regimeCasamento = regimeCasamento;
	}

	/**
	 * Retorna um Regime Casamento
	 * @return DomnRegimeCasamento
	 * @implemented by Thiago de Castilho Pacheco
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDSeparacaoDivorcio.CAMPO_REGIME_CASAMENTO
	     ,obrigatorio = true
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
	 * Atribui uma Data Separação
	 * 
	 * @param dataSeparacao
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setDataSeparacao(Date dataSeparacao)
	{
		this.dataSeparacao = dataSeparacao;
	}

	/**
	 * Retorna uma Data Separação
	 * 
	 * @return Date
	 * @implemented by Thiago de Castilho Pacheco
	 */
	@AnotacaoAtributo
	(
	    nomeColuna = CamposGIAITCDSeparacaoDivorcio.CAMPO_DATA_SEPARACAO
	    ,obrigatorio = true
	)
	public Date getDataSeparacao()
	{
		return dataSeparacao;
	}

	/**
	 * Retorna uma Data Separação Formatada
	 * 
	 * @return String
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public String getDataSeparacaoFormatada()
	{
		if (Validador.isDataValida(new SefazDataHora(getDataSeparacao())))
		{
			return new SefazDataHora(this.getDataSeparacao()).formata("dd/MM/yyyy");
		}
		else
		{
			//return new SefazDataHora(new Date()).formata("dd/MM/yyyy");
			return STRING_VAZIA;
		}
	}

	/**
	 * Atribui um Número Processo
	 * 
	 * @param numeroProcesso
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setNumeroProcesso(long numeroProcesso)
	{
		this.numeroProcesso = numeroProcesso;
	}

	/**
	 * Retorna Número Processo
	 * 
	 * @return long
	 * @implemented by Thiago de Castilho Pacheco
	 */
	@AnotacaoAtributo
	(
	    nomeColuna = CamposGIAITCDSeparacaoDivorcio.CAMPO_NUMERO_PROCESSO
	    ,obrigatorio = false
	)
	public long getNumeroProcesso()
	{
		return numeroProcesso;
	}

	/**
	 * Formata o numero Processo
	 * @return
	 * @implemented by João Batista Padilha e Silva
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
	 * Atribui um ConjugeBemTributavel cônjuge1.
	 * 
	 * @param conjuge1
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setConjuge1(ConjugeBemTributavelVo conjuge1)
	{
		this.conjuge1 = conjuge1;
	}

	/**
	 * Retorna um ConjugeBemTributavel cônjuge1.
	 * 
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ConjugeBemTributavelVo getConjuge1()
	{
		if (!Validador.isObjetoValido(this.conjuge1))
		{
			setConjuge1(new ConjugeBemTributavelVo());
		}
		return conjuge1;
	}

	/**
	 * Atribui um ConjugeBemTributavel cônjuge2.
	 * 
	 * @param conjuge2
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void setConjuge2(ConjugeBemTributavelVo conjuge2)
	{
		this.conjuge2 = conjuge2;
	}

	/**
	 * Retorna um ConjugeBemTributavel cônjuge2.
	 * 
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ConjugeBemTributavelVo getConjuge2()
	{
		if (!Validador.isObjetoValido(this.conjuge2))
		{
			setConjuge2(new ConjugeBemTributavelVo());
		}
		return conjuge2;
	}

	/**
	 * Retorna a Pessoa Conjuge do Conjuge1
	 * 
	 * @return ContribuinteIntegracaoVo
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = true
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposGIAITCDSeparacaoDivorcio.CAMPO_PESSOA_CONJUGE1
	             , nomeAtributo = "numrContribuinte"
	         )
	     }
	 )
	public ContribuinteIntegracaoVo getPessoaConjuge1()
	{
		return getConjuge1().getPessoaConjuge();
	}

	/**
	 * Atribui a Pessoa Conjuge 1
	 * 
	 * @param contribuinteIntegracaoVo
	 */
	public void setPessoaConjuge1(ContribuinteIntegracaoVo contribuinteIntegracaoVo)
	{
		getConjuge1().setPessoaConjuge(contribuinteIntegracaoVo);
	}

	/**
	 * Retorna a Pessoa Conjuge do Conjuge2
	 * 
	 * @return ContribuinteIntegracaoVo
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = true
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposGIAITCDSeparacaoDivorcio.CAMPO_PESSOA_CONJUGE2
	             , nomeAtributo = "numrContribuinte"
	         )
	     }
	 )
	public ContribuinteIntegracaoVo getPessoaConjuge2()
	{
		return getConjuge2().getPessoaConjuge();
	}

	/**
	 * Atribui a Pessoa Conjuge 2
	 * 
	 * @param contribuinteIntegracaoVo
	 */
	public void setPessoaConjuge2(ContribuinteIntegracaoVo contribuinteIntegracaoVo)
	{
		getConjuge2().setPessoaConjuge(contribuinteIntegracaoVo);
	}

	/**
	 * Retorna o valor total de bens da Pessoa
	 * 
	 * @return
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDSeparacaoDivorcio.CAMPO_VALOR_TOTAL_CONJUGE1
	     ,obrigatorio = false
	 )
	public double getValorTotalConjuge1()
	{
	   double valor = somaValorBens(getConjuge1().getCollVO(), false);
	   if(Validador.isNumericoValido(valor))
	   {
	      this.valorTotalConjuge1 = valor;
	   }
	   return this.valorTotalConjuge1;	
	}

	public double getValorTotalConjuge1Valido()
	{
		return somaValorBens(getConjuge1().getCollVO(), true);
	}

	/**
	 * Retorna o valor total de bens da Pessoa
	 * 
	 * @return
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDSeparacaoDivorcio.CAMPO_VALOR_TOTAL_CONJUGE2
	     ,obrigatorio = false
	 )	 
	public double getValorTotalConjuge2()
	{
		double valor = somaValorBens(getConjuge2().getCollVO(), false);
		if(Validador.isNumericoValido(valor))
      {
			this.valorTotalConjuge2 = valor;
		}
		return this.valorTotalConjuge2;
	}

	public double getValorTotalConjuge2Valido()
	{
		return somaValorBens(getConjuge2().getCollVO(), true);
	}
	
	private double getValorConjugeAvaliacao(ConjugeBemTributavelVo conjugeBem){	
            return conjugeBem.getValorConjuge();
	}
      	
	/**
	 * Retorna o valor total de bens da Pessoa
	 * 
	 * @return
	 */
	private double somaValorBens(Collection coll, boolean isSomenteValido)
	{
		double soma = 0d;
		Iterator ite = coll.iterator();
		while (ite.hasNext())
		{
		   double valorAvaliacao;
			ConjugeBemTributavelVo conjugeBem = (ConjugeBemTributavelVo) ite.next();
         if(conjugeBem.getBemTributavelVo().getAvaliacaoBemTributavelVo().getValorAvaliacao() != 0.0 && (!conjugeBem.getBemTributavelVo().getAvaliacaoBemTributavelVo().getObservacao().equals("Avaliação automática"))){
            // Pegar o valor separado, fazer a porcentagem do valor total e mutiliplar pelo valor da avaliação.
            double valorPercentual = getValorConjugeAvaliacao(conjugeBem) / conjugeBem.getBemTributavelVo().getValorBemDeAcordoComPorCentagem();
            valorAvaliacao = conjugeBem.getBemTributavelVo().getAvaliacaoBemTributavelVo().getValorAvaliacao() * valorPercentual;
            
         }else{
            valorAvaliacao = getValorConjugeAvaliacao(conjugeBem);
         }         
			if (isSomenteValido)
			{				
				if (conjugeBem.getBemTributavelVo().getIsencaoPrevista().is(DomnSimNao.NAO))
				{
					soma += ( valorAvaliacao > 0 ? valorAvaliacao : conjugeBem.getValorConjuge());
				}
			}
			else
			{
				soma += ( valorAvaliacao > 0 ? valorAvaliacao : conjugeBem.getValorConjuge());
			}
		}
		return soma;
	}

	/**
	 * Retorna um Valor Total Cônjuge 1 Formatado
	 * 
	 * @return String
	 * @implemented by Lucas Nascimento
	 */
	public String getValorTotalConjuge1Formatado()
	{
		//return StringUtil.doubleToMonetario(getValorTotalConjuge1());
		 /* A chamada superior foi substituida porque a validação (Validador.isNumericoValido(valor)) não permite retornar 0 (zero)
		  impedindo que valor seja atualizado na ABA Demonstrativo Cálculo quando o valor
		  é passado totalmente para outro na ABA Cônjuge.*/
		 return StringUtil.doubleToMonetario( somaValorBens(getConjuge1().getCollVO(), false) );
		 
	}

	/**
	 * Retorna um Valor Total Cônjuge 2 Formatado
	 * 
	 * @return String
	 * @implemented by Lucas Nascimento
	 */
	public String getValorTotalConjuge2Formatado()
	{
		//return StringUtil.doubleToMonetario(getValorTotalConjuge2());
      /* A chamada superior foi substituida porque a validação (Validador.isNumericoValido(valor)) não permite retornar 0 (zero)
       impedindo que valor seja atualizado na ABA Demonstrativo Cálculo quando o valor
       é passado totalmente para outro na ABA Cônjuge.*/
	   return StringUtil.doubleToMonetario( somaValorBens(getConjuge2().getCollVO(), false) );
	}

	/**
	 * Atribui a Aliquota
	 * @param valorAliquota
	 * @implemented by Lucas Nascimento
	 */
	public void setValorAliquota(double valorAliquota)
	{
		this.valorAliquota = valorAliquota;
	}

	/**
	 * Retorna a Aliquota
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDSeparacaoDivorcio.CAMPO_VALOR_ALIQUOTA
	     ,obrigatorio = false
	 )
	public double getValorAliquota()
	{
		return valorAliquota;
	}

	/**
	 * Retorna a Aliquota Formatada
	 * @return
	 * @implemented by João Batista Padilha e Silva
	 */
	public String getValorAliquotaFormatado()
	{
		return StringUtil.doubleToMonetario(getValorAliquota());
	}

	/**
	 * Atribui o Valor de Incidencia
	 * @param valorIncidencia
	 * @implemented by Lucas Nascimento
	 */
	public void setValorIncidencia(double valorIncidencia)
	{
		this.valorIncidencia = valorIncidencia;
	}

	/**
	 * Retorna o Valor de Incidência
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDSeparacaoDivorcio.CAMPO_VALOR_INCIDENCIA
	     ,obrigatorio = false
	 )
	public double getValorIncidencia()
	{
		return valorIncidencia;
	}
	
	public String getValorIncidenciaFormatado()
	{
		return StringUtil.doubleToMonetario(getValorIncidencia());
	}
	
	public double getValorTotalBensDeclarados()
	{
		return calculaValorTotalBens(false);
	}
	
	private double calculaValorTotalBens(boolean ignoraAvaliacao)
	{
	   double valorTotalBensDeclarados = 0;
	   for (Iterator iteBem = getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
	   {
	      BemTributavelVo bem = (BemTributavelVo) iteBem.next();
	      if (Validador.isObjetoValido(bem))
	      {
				if(!ignoraAvaliacao)
				{
				   if (Validador.isNumericoValido(bem.getAvaliacaoBemTributavelVo().getCodigo()))
				   {
				      valorTotalBensDeclarados += bem.getAvaliacaoBemTributavelVo().getValorAvaliacao();
				   }
				   else
				   {
				      valorTotalBensDeclarados += bem.getValorMercado();
				   }					
				}
				else
				{
				   valorTotalBensDeclarados += bem.getValorMercado();
				}
	      }
	   }
	   return valorTotalBensDeclarados;		
	}

	public double getValorTotalInformadoBensDeclarados()
	{
		return calculaValorTotalBens(true);
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
		if ( getValorTotalConjuge1Valido() >= getValorTotalConjuge2Valido() )
		{
			return getPessoaConjuge1();
		}
		else
		{
			return getPessoaConjuge2();
		}
	}

	public void setDataCasamento(Date dataCasamento)
	{
		this.dataCasamento = dataCasamento;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposGIAITCDSeparacaoDivorcio.CAMPO_DATA_CASAMENTO
	    ,obrigatorio = true
	)
	public Date getDataCasamento()
	{
		return dataCasamento;
	}

	public String getDataCasamentoFormatada()
	{
		if (Validador.isDataValida(new SefazDataHora(getDataCasamento())))
		{
			return new SefazDataHora(this.getDataCasamento()).formata("dd/MM/yyyy");
		}
		else
		{
			return STRING_VAZIA;
		}
	}	
	
	public Date getDataParaCompararInicioDia(Date dataParam)
	{
		Date dataAjustada = AbacoData.converteDataComPrimeiroMinutoDia(dataParam);
		if(!Validador.isDataValida(dataAjustada))
		{
			return dataParam;
		}
		 return dataAjustada;
	}

	public Date getDataParaCompararFimDia(Date dataParam)
	{
		Date dataAjustada = AbacoData.converteDataComUltimoMinutoDia(dataParam);
	   if(!Validador.isDataValida(dataAjustada))
	   {
	      return dataParam;
	   }
	    return dataAjustada;
	}

	public void setConjugeExcesso(ConjugeBemTributavelVo conjugeTemp)
	{
		this.conjugeExcesso = conjugeTemp;
	}

	public ConjugeBemTributavelVo getConjugeExcesso()
	{
		if(!Validador.isObjetoValido(conjugeExcesso))
		{
			setConjugeExcesso(new ConjugeBemTributavelVo());
		}
		return conjugeExcesso;
	}

	public void setValorTotalConjuge1(double valorTotalConjuge1)
	{
		this.valorTotalConjuge1 = valorTotalConjuge1;
	}

	public void setValorTotalConjuge2(double valorTotalConjuge2)
	{
		this.valorTotalConjuge2 = valorTotalConjuge2;
	}


   public void setGiaItcdSeparacaoDivorcioAliquotaVo(GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo)
   {
      this.giaItcdSeparacaoDivorcioAliquotaVo = giaItcdSeparacaoDivorcioAliquotaVo;
   }

   public GIAITCDSeparacaoDivorcioAliquotaVo getGiaItcdSeparacaoDivorcioAliquotaVo()
   {
      if(!Validador.isObjetoValido(this.giaItcdSeparacaoDivorcioAliquotaVo))
      {
         setGiaItcdSeparacaoDivorcioAliquotaVo(new GIAITCDSeparacaoDivorcioAliquotaVo());
      }
      return giaItcdSeparacaoDivorcioAliquotaVo;
   }


   public void setValorTotalConjugeExcesso(double valorTotalConjugeExcesso)
   {
      this.valorTotalConjugeExcesso = valorTotalConjugeExcesso;
   }

   public double getValorTotalConjugeExcesso()
   {
      return valorTotalConjugeExcesso;
   }
   
   public String getValorTotalConjugeExcessoFormatado()
   {
      return StringUtil.doubleToMonetario(getValorTotalConjugeExcesso());
   }

}
