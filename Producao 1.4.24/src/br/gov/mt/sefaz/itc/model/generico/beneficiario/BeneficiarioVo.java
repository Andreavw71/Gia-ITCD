package br.gov.mt.sefaz.itc.model.generico.beneficiario;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.facade.EntidadeFacade;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.PedidoRelatorioVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBeneficiario;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Classe responsável por encapsular os valores temporários do objeto Beneficiario (Value Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.4 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_BENEFICIARIO
     ,nomeAmigavel = "Beneficiário"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposBeneficiario.CAMPO_CODIGO_BENEFICIARIO
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_BENEFICIARIO 
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class BeneficiarioVo extends EntidadeVo
{
	private double valorRecebido;
	private GIAITCDVo giaITCDVo;
	private ContribuinteIntegracaoVo pessoaBeneficiaria;
	private static final long serialVersionUID = Long.MAX_VALUE;
   private double valorRecebidoAvaliacao;
   private Integer flagDoacaoAnteriorManualEprocess;
   private double valorRecebidoDoacaoSucessiva;
   private double valorItcdDoacaoSucessiva;
   private Integer flagDoacaoSucessiva;
   private Double valorItcdBeneficiario;   
   
   /**
    * Utilizado apenas para axuliar os calculos
    * dos valores recebidos pelos beneficiarios.
    * 
    */
   private transient double valorRecebidoAuxiliar;

	/**
	 * Construtor Padrão
	 * @implemented by Daniel Balieiro
	 */
	public BeneficiarioVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a Chave Primária
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public BeneficiarioVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe o Parametro de Consulta
	 * @param beneficiarioVo
	 * @implemented by Daniel Balieiro
	 */
	public BeneficiarioVo(BeneficiarioVo beneficiarioVo)
	{
		super();
		setParametroConsulta(beneficiarioVo);
	}

	/**
	 * Construtor que recebe uma Collection de BeneficiarioVo
	 * @param collVo
	 * @implemented by Daniel Balieiro
	 */
	public BeneficiarioVo(Collection collVo)
	{
		super(collVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Retorna a Chave Primária
	 * @return BeneficiarioPk
	 * @implemented by Daniel Balieiro
	 */
	public BeneficiarioPk getPk()
	{
		return new BeneficiarioPk(getCodigo());
	}

	/**
	 * Atribui um Contribuinte
	 * @param pessoaBeneficiaria
	 * @implemented by Daniel Balieiro
	 */
	public void setPessoaBeneficiaria(ContribuinteIntegracaoVo pessoaBeneficiaria)
	{
		this.pessoaBeneficiaria = pessoaBeneficiaria;
	}

	/**
	 * Retorna o Contribuinte
	 * @return ContribuinteIntegracaoVo
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = true
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposBeneficiario.CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA
	             , nomeAtributo = "numrContribuinte"
	         )
	     }
	 )
	public ContribuinteIntegracaoVo getPessoaBeneficiaria()
	{
		if (pessoaBeneficiaria == null)
		{
			setPessoaBeneficiaria(new ContribuinteIntegracaoVo());
		}
		return pessoaBeneficiaria;
	}

	/**
	 * Método para vincular a referencia entre a GIA-ITCD e os Beneficiários
	 * @param giaItcdVo
	 * @param usuarioLogado
	 * @implemented by Daniel Balieiro
	 */
	public void alinhaReferencia(GIAITCDVo giaItcdVo, long usuarioLogado)
	{
		configuraGiaUsuarioLogado(giaItcdVo, usuarioLogado);
		if (Validador.isCollectionValida(this.getCollVO()))
		{
			Iterator it = this.getCollVO().iterator();
			while (it.hasNext())
			{
				BeneficiarioVo atual = (BeneficiarioVo) it.next();
				configuraGiaUsuarioLogado(giaItcdVo, usuarioLogado);
			}
		}
	}

	/**
	 * Configura o Usuário Logado na GIA-ITCD
	 * @param giaItcdVo
	 * @param usuarioLogado
	 * @implemented by Daniel Balieiro
	 */
	private void configuraGiaUsuarioLogado(GIAITCDVo giaItcdVo, long usuarioLogado)
	{
		this.giaITCDVo = giaItcdVo;
		this.setUsuarioLogado(usuarioLogado);
	}

	/**
	 * Atribui o Valor Recebido
	 * @param valorRecebido
	 * @implemented by Daniel Balieiro
	 */
	public void setValorRecebido(double valorRecebido)
	{
		this.valorRecebido = valorRecebido;
	}

	/**
	 * Retorna o Valor Recebido
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposBeneficiario.CAMPO_VALOR_RECEBIDO
	     ,obrigatorio = false
	 )
    
   public double getValorRecebidoSemDoacaoSucessiva(){
      return valorRecebido;
   }
       
   public String getValorRecebidoSemDoacaoSucessivaFormatado(){
      return StringUtil.doubleToMonetario(valorRecebido);
   }    
   
	public double getValorRecebido(){
	   return valorRecebido;
	}
    
	/**
	 * Método que retorna o valor recebido Formatado
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getValorRecebidoFormatado()
	{
		return StringUtil.doubleToMonetario(getValorRecebido());
	}

	/**
	 * Atribui a GIA
	 * @param giaITCDVo
	 * @implemented by Lucas Nascimento
	 */
	public void setGiaITCDVo(GIAITCDVo giaITCDVo)
	{
		this.giaITCDVo = giaITCDVo;
	}

	/**
	 * Retorna a GIA
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
	             nomeColuna = CamposBeneficiario.CAMPO_ITCTB14_CODIGO_GIA_ITCD
	             , nomeAtributo = "codigo"
	         )
	     }
	 )
	public GIAITCDVo getGiaITCDVo()
	{
		if (this.giaITCDVo == null)
		{
			setGiaITCDVo(new GIAITCDVo());
		}
		return this.giaITCDVo;
	}

	public int getNumeroBeneficiariosInseridos()
	{
		if(!Validador.isCollectionValida(getCollVO()))
		{
			return 0;
		}
		return getCollVO().size();
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
			int hashCodigo = (int) getCodigo();
			int hashPessoaBeneficiaria = getPessoaBeneficiaria().getNumrContribuinte().intValue() * MULTIPLICADOR_HASH_CODE;
			int hashCollVO = getCollVO().size() * MULTIPLICADOR_HASH_CODE;			
			hashAtual += hashCollVO + hashPessoaBeneficiaria;
			hashAtual += hashCollVO + hashCodigo;
			return hashAtual;
		}

		public int compareTo(Object entidadeVo) throws ClassCastException, NullPointerException
		{
			int codigo = new Long(this.getCodigo()).compareTo(new Long(((BeneficiarioVo) entidadeVo).getCodigo()));
			if (codigo != 0)
			{
				return codigo;
			}
			else
			{
				return this.getPessoaBeneficiaria().getNumrContribuinte().compareTo(((BeneficiarioVo) entidadeVo).getPessoaBeneficiaria().getNumrContribuinte());
			}
		}

	public int getQuantidadeCasasDecimaisPercentualRecebido()
	{
		return 3;
	}
   
   
   /**
    * Atribui o Valor Recebido Avaliação
    * @param valorRecebidoAvaliacao
    * @implemented by Dherkyan Ribeiro
    */
   public void setValorRecebidoAvaliacao(double valorRecebidoAvaliacao)
   {
      this.valorRecebidoAvaliacao = valorRecebidoAvaliacao;
   }

   /**
    * Retorna o Valor Recebido Avaliação
    * @return
    * @implemented by Dherkyan Ribeiro
    */
    @AnotacaoAtributo
    (
        nomeColuna = CamposBeneficiario.CAMPO_VALOR_RECEBIDO_AVALIACAO
        ,obrigatorio = false
    )
   public double getValorRecebidoAvaliacao()
   {
      return valorRecebidoAvaliacao;
   }

   /**
    * Método que retorna o valor recebido Avaliação Formatado
    * @return String
    * @implemented by Dherkyan Ribeiro
    */
   public String getValorRecebidoAvaliacaoFormatado()
   {
      return StringUtil.doubleToMonetario(getValorRecebidoAvaliacao());
   }

   public void setValorRecebidoAuxiliar(double valorRecebidoAuxiliar)
   {
      this.valorRecebidoAuxiliar = valorRecebidoAuxiliar;
   }

   public double getValorRecebidoAuxiliar()
   {
      return valorRecebidoAuxiliar;
   }
   
   /**
    * Método que retorna o valor recebido Avaliação Formatado
    * @return String
    * @implemented by Dherkyan Ribeiro
    */
   public String getValorRecebidoAuxiliarFormatado()
   {
      return StringUtil.doubleToMonetario(getValorRecebidoAuxiliar());
   }
   
   public List<BeneficiarioVo> getListVo()
   {
      return (List<BeneficiarioVo>) this.getCollVO();
   }
   
   @AnotacaoAtributo
      (
          nomeColuna = CamposBeneficiario.CAMPO_INFO_DOACAO_ANTR_PROT_MANUAL
          ,obrigatorio = false
      )
   public Integer getFlagDoacaoAnteriorManualEprocess() {
          return flagDoacaoAnteriorManualEprocess;
   }

   public void setFlagDoacaoAnteriorManualEprocess(Integer flagDoacaoAnteriorManualEprocess) {
         this.flagDoacaoAnteriorManualEprocess = flagDoacaoAnteriorManualEprocess;
   }

   public void setValorRecebidoDoacaoSucessiva(double valorRecebidoDoacaoSucessiva){
         this.valorRecebidoDoacaoSucessiva = valorRecebidoDoacaoSucessiva;
   }

      @AnotacaoAtributo
      (
          nomeColuna = CamposBeneficiario.CAMPO_VALR_RECB_DOACAO_SUCESSIVA
          ,obrigatorio = false
      )
   public double getValorRecebidoDoacaoSucessiva(){
      return valorRecebidoDoacaoSucessiva;
   }

   public void setValorItcdDoacaoSucessiva(double valorItcdDoacaoSucessiva){
      this.valorItcdDoacaoSucessiva = valorItcdDoacaoSucessiva;
   }
     
   public double getValorItcdDoacaoSucessiva(){
      return valorItcdDoacaoSucessiva;
   }
      
   public String getValorItcdDoacaoSucessivaFormatado(){
        return StringUtil.doubleToMonetario(getValorItcdDoacaoSucessiva(), 2);
   }

   public void setFlagDoacaoSucessiva(Integer flagDoacaoSucessiva){
      this.flagDoacaoSucessiva = flagDoacaoSucessiva;
   }

      @AnotacaoAtributo
      (
          nomeColuna = CamposBeneficiario.CAMPO_INFO_DOACAO_SUCESSIVA
          ,obrigatorio = false
      )
   public Integer getFlagDoacaoSucessiva(){
      if(flagDoacaoSucessiva == null){
         setFlagDoacaoSucessiva(2);
      }
      return flagDoacaoSucessiva;
   }
   
   @AnotacaoAtributo
   (
       nomeColuna = CamposBeneficiario.CAMPO_VALR_ITCD_BENF
       ,obrigatorio = false
   )
   public Double getValorItcdBeneficiario(){     
      return valorItcdBeneficiario;
   }
   
   public String getValorItcdBeneficiarioFormatado(){
      return StringUtil.doubleToMonetario(getValorItcdBeneficiario(), 2);
   }
   
   public void setValorItcdBeneficiario(Double valorItcdBeneficiario)
   {
      this.valorItcdBeneficiario = valorItcdBeneficiario;
   }
   
}
