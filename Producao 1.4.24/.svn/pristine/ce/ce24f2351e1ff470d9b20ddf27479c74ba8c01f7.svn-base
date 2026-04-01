package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDoacao;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDDoacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * Classe responsável por encapsular os valores do objeto GIA ITCD Doação (Value Object).
 * 
 * @author Daniel Balieiro
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.3 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_GIA_ITCD_DOACAO
     ,nomeAmigavel = "GIA-ITCD Doação"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposGIAITCDDoacao.CAMPO_ITCBTB14_CODIGO_ITCD
			)
     }
 )
public class GIAITCDDoacaoVo extends GIAITCDVo
{
	private double fracaoIdeal;
	private double baseCalculoReduzida;
	private DomnTipoDoacao tipoDoacao;
	private static final long serialVersionUID = Long.MAX_VALUE;
	private transient ServidorSefazIntegracaoVo servidorSefazIntegracaoVo;
	private transient boolean protocoAutomatico;  
   private Integer flagInfoDoacaoSucessiva;
   
	/**
	 * Construtor vazio
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public GIAITCDDoacaoVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a Chave Primária
	 * 
	 * @param codigo
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recece o Parametro de Consulta
	 * 
	 * @param giaITCDDoacaoVo
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoVo(GIAITCDDoacaoVo giaITCDDoacaoVo)
	{
		super(giaITCDDoacaoVo);
	}

	/**
	 * Construtor que recebe uma Collection de GIAITCDDoacaoVo
	 * @param collVo
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoVo(Collection collVo)
	{
		super(collVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui uma Fração Ideal
	 * 
	 * @param fracaoIdeal
	 * @implemented by Daniel Balieiro
	 */
	public void setFracaoIdeal(double fracaoIdeal)
	{
		this.fracaoIdeal = fracaoIdeal;
	}

	/**
	 * Retorna a Fração Ideal
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDDoacao.CAMPO_FRAC_IDEAL
	     ,obrigatorio = true
	 )
	public double getFracaoIdeal()
	{
		return fracaoIdeal;
	}

	/**
	 * Retorna a Fração Ideal Formatada
	 * 
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	public String getFracaoIdealFormatada()
	{
		final double fracao = getFracaoIdeal();
		if (!Validador.isNumericoValido(fracao))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(fracao, getQuantidadeCasasDecimais());
	}

	/**
	 * Atribui uma Base de Calculo Reduzido
	 * 
	 * @param baseCalculoReduzido
	 * @implemented by Daniel Balieiro
	 */
	public void setBaseCalculoReduzida(double baseCalculoReduzido)
	{
		this.baseCalculoReduzida = baseCalculoReduzido;
	}

	/**
	 * Retorna a Base de Calculo Reduzido
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDDoacao.CAMPO_BASE_CALCULO_REDUZIDA
	     ,obrigatorio = false
	 )
	public double getBaseCalculoReduzida(){
      return this.baseCalculoReduzida;
	}

	/**
	 * Retorna a Base de Calculo Formatado
	 * 
	 * @return String
	 * @implemented by João Batista Padilha e Silva
	 */
	public String getBaseCalculoReduzidaFormatado()
	{   	   
	   return StringUtil.doubleToMonetario(baseCalculoReduzida);
	}

	/**
	 * Atribui o Tipo da Doação
	 * 
	 * @param tipoDoacao
	 * @implemented by Daniel Balieiro
	 */
	public void setTipoDoacao(DomnTipoDoacao tipoDoacao)
	{
		this.tipoDoacao = tipoDoacao;
	}

	/**
	 * Retorna o Tipo da Doação
	 * 
	 * @return DomnTipoDoacao
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDDoacao.CAMPO_TIPO_DOACAO
	     ,obrigatorio = true
	 )
	public DomnTipoDoacao getTipoDoacao()
	{
		if (!Validador.isDominioNumericoValido(tipoDoacao))
		{
			setTipoDoacao(new DomnTipoDoacao(-1));
		}
		return tipoDoacao;
	}

	/**
	 * Retorna o valor da fração ideal
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public double getValorFracaoIdeal()
	{
		return getValorTotalBensDeclarados() * getFracaoIdeal() / 100;
	}

	/**
	 * Retorna o valor da fração ideal formatado
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public String getValorFracaoIdealFormatado()
	{
		if (!Validador.isNumericoValido(getValorFracaoIdeal()))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(getValorFracaoIdeal(), 2);
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
		for(Iterator iteBeneficiarios = getBeneficiarioVo().getCollVO().iterator();iteBeneficiarios.hasNext();)
		{
		   GIAITCDDoacaoBeneficiarioVo beneficiarioAtual = (GIAITCDDoacaoBeneficiarioVo) iteBeneficiarios.next();
			if (Validador.isNumericoValido(beneficiarioAtual.getValorRecolher()))
			{
				return beneficiarioAtual.getPessoaBeneficiaria();
			}
		}
		//Caso todos sejam isentos retorna o primeiro
	   GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) ((List) getBeneficiarioVo().getCollVO()).get(0);
	   return beneficiario.getPessoaBeneficiaria();
	}  
	
	//== Servidor Sefaz Integração ==\\
	 public ServidorSefazIntegracaoVo getServidorSefazIntegracaoVo()
	 {
		 if (servidorSefazIntegracaoVo == null)
		 {
			 setServidorSefazIntegracaoVo(new ServidorSefazIntegracaoVo());
		 }
		 return servidorSefazIntegracaoVo;
	 }

		public void setServidorSefazIntegracaoVo(ServidorSefazIntegracaoVo servidorSefazIntegracaoVo)
		{
			this.servidorSefazIntegracaoVo = servidorSefazIntegracaoVo;
		}	

	public int getQuantidadeCasasDecimais()
	{
		return 4;
	}

	public void setProtocoAutomatico(boolean protocoAutomatico)
	{
		this.protocoAutomatico = protocoAutomatico;
	}

	public boolean isProtocoAutomatico()
	{
		return protocoAutomatico;
	}
   
   public double getSomaValorRecebidoDoacaoSucessiva() {
         double soma = 0.0;
         if (getBeneficiarioVo() != null && getBeneficiarioVo().getCollVO() != null) {
             for (Object obj : getBeneficiarioVo().getCollVO()) {
                BeneficiarioVo beneficiario = (BeneficiarioVo) obj;
                if(beneficiario.getValorRecebidoDoacaoSucessiva() > 0){
                   soma += (beneficiario.getValorRecebidoDoacaoSucessiva() + beneficiario.getValorRecebidoSemDoacaoSucessiva());
                }   
             }
         }
         return soma;
     }
     
   public boolean getIsTodosBeneficiariosDoacaoSucessiva(){
      boolean result = false;
      if (getBeneficiarioVo() != null && getBeneficiarioVo().getCollVO() != null){
         for (Object obj: getBeneficiarioVo().getCollVO()){
            BeneficiarioVo beneficiario = (BeneficiarioVo) obj;
            if (beneficiario.getFlagDoacaoSucessiva() == 1){
               result = true;
            }
         }
      }
      return result;
   }

     /**
      * Retorna a soma de todos os valores recebidos de doação sucessiva dos beneficiários formatada
      * @return String - Soma dos valores recebidos de doação sucessiva formatada
      * @implemented by Rodrigo
      */
     public String getSomaValorRecebidoDoacaoSucessivaFormatado() {
         return StringUtil.doubleToMonetario(getSomaValorRecebidoDoacaoSucessiva(), 2);
     }


   public void setFlagInfoDoacaoSucessiva(Integer flagInfoDoacaoSucessiva){
      this.flagInfoDoacaoSucessiva = flagInfoDoacaoSucessiva;
   }

   @AnotacaoAtributo(
       nomeColuna = CamposGIAITCDDoacao.CAMPO_INFO_DOACAO_SUCESSIVA
       ,obrigatorio = false
   )
   public Integer getFlagInfoDoacaoSucessiva(){     
      if(getIsTodosBeneficiariosDoacaoSucessiva()){
         setFlagInfoDoacaoSucessiva(1);
      }else{
         setFlagInfoDoacaoSucessiva(2);
      }
      return flagInfoDoacaoSucessiva;
   }
   
   public boolean getQuantidadeDoacaoSucessivaMaiorQueParametro() {         
         int cont = 0;
         if (getBeneficiarioVo() != null && getBeneficiarioVo().getCollVO() != null) {
             for (Object obj : getBeneficiarioVo().getCollVO()) {
                GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) obj;
                cont += beneficiario.getGiaitcdDoacaoSucessivaVo().getCollVO().size();                  
             }
         }         
         if(cont >= 2){
            return true;
         }         
         return false;
     }
     
   public boolean getIsPresenteDispensaRecolhimento(){
      boolean retorno = false;      
      if (getBeneficiarioVo() != null && getBeneficiarioVo().getCollVO() != null){
         for (Object obj: getBeneficiarioVo().getCollVO()){
            GIAITCDDoacaoBeneficiarioVo beneficiario = (GIAITCDDoacaoBeneficiarioVo) obj;
            if (beneficiario.getInfoDispensaRecolhimento() == 2){
               retorno = true;
            }
         }
      }
      
      return retorno;
   }
     
}
