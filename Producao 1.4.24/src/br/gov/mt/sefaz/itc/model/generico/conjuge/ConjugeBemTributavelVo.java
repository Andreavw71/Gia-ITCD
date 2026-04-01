/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConjugeBemTributavelVo.java
 * Revisão:
 * Data revisão:
 * $Id: ConjugeBemTributavelVo.java,v 1.3 2009/05/05 20:09:00 ricardo.moraes Exp $
 */

package br.gov.mt.sefaz.itc.model.generico.conjuge;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.facade.EntidadeFacade;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.NumeroUtil;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoConjuge;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposConjuge;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.util.Iterator;


/**
 * Classe responsável por encapsular os relacionamentos de um conjuge(Contribuinte)
 * com um bem tributável de uma GIA.
 *
 * @author Leandro Dorileo
 * @version $Revision: 1.3 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_CONJUGE_BEM_TRIBUTAVEL
     ,nomeAmigavel = "Bem Tributável Cônjuge"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "giaITCDVo.codigo"
			    ,nomeColuna = CamposConjuge.CAMPO_ITCTB17_ITCTB14_CODG_ITCD
			)
			,@AnotacaoIdentificador
			(
			    nomeAtributo = "bemTributavelVo.codigo"
			    ,nomeColuna = CamposConjuge.CAMPO_ITCTB18_CODG_ITCD_BEM_TRBT
			)
			,@AnotacaoIdentificador
			(
			    nomeAtributo = "pessoaConjuge.numrContribuinte"
			    ,nomeColuna = CamposConjuge.CAMPO_ACCTB01_NUMR_PESS_CONJUGE
			)
     }
 )
public class ConjugeBemTributavelVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private DomnTipoConjuge tipoConjuge;
	private ContribuinteIntegracaoVo pessoaConjuge;
	private BemTributavelVo bemTributavelVo;
	private GIAITCDVo giaITCDVo;
	private double valorConjuge;
   private double valorConjugeAvaliacao;
   private transient double valorConjugeAuxiliar;
   private Integer infoIsencao;
   private Integer infoDispensaRecolhimento;


	/**
	 * Construtor público padrão sem parâmetros
	 */
	public ConjugeBemTributavelVo()
	{
		super();
	}

	/**
	 * Construtor que recebe um parametro de consulta
	 * @param conjugeBemTributavelVo	vo de Bem Tributável(Value Object)
	 * @implemented by Leandro Dorileo
	 */
	public ConjugeBemTributavelVo(ConjugeBemTributavelVo conjugeBemTributavelVo)
	{
		super();
		setParametroConsulta(conjugeBemTributavelVo);
	}

	/**
	 * Construtor público recebendo objetos contendo as chaves primárias.
	 * 
	 * @param tipoConjuge		Domínio do tipo do conjuge
	 * @param pessoaConjuge		Encapsula os dados do contribuinte
	 * @param bemTributavelVo	Bem Tributável relacionado a esse conjuge
	 */
	public ConjugeBemTributavelVo(DomnTipoConjuge tipoConjuge, ContribuinteIntegracaoVo pessoaConjuge, BemTributavelVo bemTributavelVo)
	{
		super();
		this.tipoConjuge = tipoConjuge;
		this.pessoaConjuge = pessoaConjuge;
		this.bemTributavelVo = bemTributavelVo;
	}

	/**
	 * Atribui o Tipo do Conjuge
	 * @param tipoConjuge
	 * @implemented by Lucas Nascimento
	 */
	public void setTipoConjuge(DomnTipoConjuge tipoConjuge)
	{
		this.tipoConjuge = tipoConjuge;
	}

	/**
	 * Retorna o Tipo do Conjuge
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposConjuge.CAMPO_TIPO_CONJUGE
	     ,obrigatorio = true
	 )
	public DomnTipoConjuge getTipoConjuge()
	{
		if (!Validador.isDominioNumericoValido(this.tipoConjuge))
		{
			setTipoConjuge(new DomnTipoConjuge());
		}
		return tipoConjuge;
	}

	/**
	 * Atribui a Pessoa do Conjuge
	 * @param pessoaConjuge
	 * @implemented by Lucas Nascimento
	 */
	public void setPessoaConjuge(ContribuinteIntegracaoVo pessoaConjuge)
	{
		this.pessoaConjuge = pessoaConjuge;
	}

	/**
	 * Retorna a Pessoa do Conjuge
	 * 
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public ContribuinteIntegracaoVo getPessoaConjuge()
	{
		if (!Validador.isObjetoValido(this.pessoaConjuge))
		{
			setPessoaConjuge(new ContribuinteIntegracaoVo());
		}
		return pessoaConjuge;
	}

	/**
	 * Atribui o Bem Tributavel
	 * @param bemTributavelVo
	 * @implemented by Lucas Nascimento
	 */
	public void setBemTributavelVo(BemTributavelVo bemTributavelVo)
	{
		this.bemTributavelVo = bemTributavelVo;
	}

	/**
	 * Retorna o Bem Tributavel
	 * 
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public BemTributavelVo getBemTributavelVo()
	{
		if (!Validador.isObjetoValido(this.bemTributavelVo))
		{
			setBemTributavelVo(new BemTributavelVo());
		}
		return this.bemTributavelVo;
	}

	/**
	 * Atribui a GiaITCD
	 * 
	 * @param giaITCDVo
	 * @implemented by Lucas Nascimento
	 */
	public void setGiaITCDVo(GIAITCDVo giaITCDVo)
	{
		this.giaITCDVo = giaITCDVo;
	}

	/**
	 * Retorna o Gia ITCD
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDVo getGiaITCDVo()
	{
		if (!Validador.isObjetoValido(this.giaITCDVo))
		{
			setGiaITCDVo(new GIAITCDVo());
		}
		return this.giaITCDVo;
	}

	/**
	 * Retorna o Pk
	 * 
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public ConjugeBemTributavelPk getPk()
	{
		return new ConjugeBemTributavelPk(getCodigo());
	}

	/**
	 * Atribui o Valor do Conjuge
	 * @param valorTotal
	 * @implemented by Lucas Nascimento
	 */
	public void setValorConjuge(double valorTotal)
	{
		this.valorConjuge = valorTotal;
	}

	/**
	 * Retorna o Valor do Conjuge
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposConjuge.CAMPO_VALOR_CONJUGE
	     ,obrigatorio = true
	 )
	public double getValorConjuge()
	{
		return NumeroUtil.arredondaNumero(valorConjuge);
	}

	/**
	 * Método de retorno formatado do ValorConjuge 
	 * @return
	 * @implemented by João Batista Padilha e Silva
	 */
	public String getValorConjugeFormatado()
	{		
	   if(getBemTributavelVo().getAvaliacaoBemTributavelVo().getValorAvaliacao() != 0.0 && (!getBemTributavelVo().getAvaliacaoBemTributavelVo().getObservacao().equals("Avaliação automática"))){
	      // Pegar o valor separado, fazer a porcentagem do valor total e mutiliplar pelo valor da avaliação. Somente para avaliação manual
	      double valorPercentual = getValorConjuge() / getBemTributavelVo().getValorBemDeAcordoComPorCentagem();         
	      return StringUtil.doubleToMonetario(getBemTributavelVo().getAvaliacaoBemTributavelVo().getValorAvaliacao() * valorPercentual);	      
	   }
		return StringUtil.doubleToMonetario(getValorConjuge());
	}

	/**
	 * Retorna o valor atriuido a um determinado Bem passado como argumento
	 * @param bem
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public double getValorAtribuidoBem(BemTributavelVo bem)
	{
		for (Iterator iteConjuge = this.getCollVO().iterator(); iteConjuge.hasNext(); )
		{
			ConjugeBemTributavelVo conjugeBem = (ConjugeBemTributavelVo) iteConjuge.next();
			if (conjugeBem.getBemTributavelVo().getDescricaoBemTributavel().equals(bem.getDescricaoBemTributavel()) && 
						conjugeBem.getBemTributavelVo().getBemVo().getClassificacaoTipoBem().is(bem.getBemVo().getClassificacaoTipoBem().getValorCorrente()) && 
						(conjugeBem.getBemTributavelVo().getBemVo().getDescricaoTipoBem().equals(bem.getBemVo().getDescricaoTipoBem())))
			{
				return conjugeBem.getValorConjuge();
			}
		}
		return 0;
	}
	
	public boolean equals(Object object)
		{
			if (object instanceof EntidadeFacade)
			{
				if (this.hashCode() == object.hashCode())
				{
					return true;
				}
				return false;
			}
			else
			{
				return false;
			}
		}
	
	
	public int hashCode()
	{
	   int hashAtual = 0;
	   int hashCodigoGia = (int) getGiaITCDVo().getCodigo();
		int hashPessoa = getPessoaConjuge().getNumrContribuinte().intValue();
		int hashBem = (int) getBemTributavelVo().getCodigo();
	   int hashCollVo = getCollVO().size() * MULTIPLICADOR_HASH_CODE;
	   hashAtual += hashCodigoGia;
	   hashAtual += hashPessoa;
	   hashAtual += hashBem;
	   hashAtual += hashCollVo;
	   hashAtual *= MULTIPLICADOR_HASH_CODE;        
	   return hashAtual;		
	}

	public int compareTo(Object entidadeVo) throws ClassCastException, NullPointerException
	{
		int codigo = new Long(this.getGiaITCDVo().getCodigo()).compareTo(new Long(((ConjugeBemTributavelVo) entidadeVo).getGiaITCDVo().getCodigo()));
		if (codigo != 0)		
		{
			return codigo;
		}
		else
		{
		   codigo = new Long(this.getBemTributavelVo().getCodigo()).compareTo(new Long(((ConjugeBemTributavelVo) entidadeVo).getBemTributavelVo().getCodigo()));
		   if(codigo != 0)
		   {
		      return codigo;
		   }
			else
			{
			   codigo = new Long(this.getPessoaConjuge().getNumrContribuinte().longValue()).compareTo(new Long(((ConjugeBemTributavelVo) entidadeVo).getPessoaConjuge().getNumrContribuinte().longValue()));   
			   if(codigo != 0)
			   {
			      return codigo;
			   }
				else
				{
					codigo = this.getTipoConjuge().is(((ConjugeBemTributavelVo) entidadeVo).getTipoConjuge().getValorCorrente())?0:1;
					if(codigo != 0)
					{
						return codigo;
					}
					else
					{
						return this.getValorConjuge() == ((ConjugeBemTributavelVo) entidadeVo).getValorConjuge()?0:1;
					}
				}
				   
			}
		}
	}
   
   /**
    * Atribui o Valor do Conjuge
    * @param valorConjugeAvaliacao
    * @implemented by Dherkyan Ribeiro
    */
   public void setValorConjugeAvaliacao(double valorConjugeAvaliacao)
   {
      this.valorConjugeAvaliacao = valorConjugeAvaliacao;
   }

   /**
    * Retorna o Valor do Conjuge
    * @return
    * @implemented by Dherkyan Ribeiro
    */
    @AnotacaoAtributo
    (
        nomeColuna = CamposConjuge.CAMPO_VALOR_CONJUGE_AVALIACAO
        ,obrigatorio = false
    )
   public double getValorConjugeAvaliacao()
   {
      return NumeroUtil.arredondaNumero(valorConjugeAvaliacao);
   }

   /**
    * Método de retorno formatado do ValorConjuge 
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public String getValorConjugeAvaliacaoFormatado()
   {      
      return StringUtil.doubleToMonetario(getValorConjuge());
   }

   public void setValorConjugeAuxiliar(double valorConjugeAuxiliar)
   {
      this.valorConjugeAuxiliar = valorConjugeAuxiliar;
   }

   public double getValorConjugeAuxiliar()
   {
      return valorConjugeAuxiliar;
   }
   
   public void setInfoIsencao(Integer infoIsencao){
      this.infoIsencao = infoIsencao;
   }

   @AnotacaoAtributo
   (
       nomeColuna = CamposConjuge.CAMPO_INFO_ISENCAO
       ,obrigatorio = false
   )
   public Integer getInfoIsencao(){
      return infoIsencao;
   }

   public void setInfoDispensaRecolhimento(Integer infoDispensaRecolhimento){
      this.infoDispensaRecolhimento = infoDispensaRecolhimento;
   }

   @AnotacaoAtributo
   (
       nomeColuna = CamposConjuge.CAMPO_INFO_DSPE_RCLH
       ,obrigatorio = false
   )
   public Integer getInfoDispensaRecolhimento(){
      return infoDispensaRecolhimento;
   }
}
