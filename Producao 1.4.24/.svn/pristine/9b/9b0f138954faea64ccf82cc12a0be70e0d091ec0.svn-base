package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBeneficiario;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposDoacaoSucessiva;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_GIA_ITCD_DOACAO_SUCESSIVA, 
                      nomeAmigavel = "DoacaoSucessiva", identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", 
                               nomeColuna = CamposDoacaoSucessiva.CAMPO_CODG_BENF_GIA_SEQC, 
                               sequencia = 
                               @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_DOACAO_SUCESSIVA, 
                                                  tipoSequencia = 
                                                  DomnTipoSequencia.SEQUENCE)
         )
         } )
public class GIAITCDDoacaoSucessivaVo
   extends EntidadeVo<GIAITCDDoacaoSucessivaVo>
{
   private static final long serialVersionUID = Long.MAX_VALUE;

   private BeneficiarioVo beneficiarioVo;
   private ContribuinteIntegracaoVo pessoaBeneficiaria;
   private GIAITCDVo giaITCDVo;
   private Long numeroGiaITCD;
   private String numeroEprocess;
   private String mesGIAITCDAnterior;
   private Date dataGIAITCDAnterior;
   private double valorBaseDeCalc;
   private String valorBaseDeCalcFormatado;
   private double valorITCD;
   private String valorITCDFormatado;
   private Date dataInclusao;
   private Date dataAtualizacao;
   private Date dataEProcess;
   private DomnSimNao flagDoacaoSucessivaAnterior;
   private double valorITCDBeneficiario;
   private boolean isIsentoUpf;
   private double valorRecolherBenef;
   private Integer infoIsencao;
   private double valorRecebidoBenefAnterior;
   

   public GIAITCDDoacaoSucessivaVo()
   {
      super();
   }

   public GIAITCDDoacaoSucessivaVo(long codigo)
   {
      super(codigo);
   }

   public GIAITCDDoacaoSucessivaVo(GIAITCDDoacaoSucessivaVo gitcddoacaoSucessivaVo)
   {
      super();
      setParametroConsulta(gitcddoacaoSucessivaVo);
   }

   public GIAITCDDoacaoSucessivaVo(Collection<GIAITCDDoacaoSucessivaVo> collVo)
   {
      super(collVo);
   }

   public GIAITCDDoacaoSucessivaPk getPk()
   {
      return new GIAITCDDoacaoSucessivaPk(getCodigo());
   }
   
   public long getCodigo()
   {
      return super.getCodigo();
   }
   
   public List<GIAITCDDoacaoSucessivaVo> getListVo()
   {
      return (List<GIAITCDDoacaoSucessivaVo>) this.getCollVO();
   }
   
   public void setGiaITCDVo(GIAITCDVo giaITCDVo)
   {
      this.giaITCDVo = giaITCDVo;
      this.setNumeroGiaITCD(giaITCDVo.getCodigo());
   }

   
   @AnotacaoAtributoExterno(obrigatorio = true, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposDoacaoSucessiva.CAMPO_ITCTB14_CODG_ITCD_ANTR, nomeAtributo = "codigo")
            } )
   public GIAITCDVo getGiaITCDVo()
   {
      if (!Validador.isObjetoValido(giaITCDVo))
      {
         setGiaITCDVo(new GIAITCDVo());
      }
      return giaITCDVo;
   }

   public void setBeneficiarioVo(BeneficiarioVo beneficiarioVo)
   {
      this.beneficiarioVo = beneficiarioVo;
   }
   
   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = 
                                  CamposDoacaoSucessiva.CAMPO_ITCTB05_CODG_BENEFICIARIO, 
                                  nomeAtributo = "beneficiarioVo")
            } )
   public BeneficiarioVo getBeneficiarioVo()
   {
      if (!Validador.isObjetoValido(beneficiarioVo))
      {
         setBeneficiarioVo(new BeneficiarioVo());
      }
      return this.beneficiarioVo;
   }

   public void setPessoaBeneficiaria(ContribuinteIntegracaoVo pessoaBeneficiaria)
   {
      this.pessoaBeneficiaria = pessoaBeneficiaria;
   }

   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = 
                                  CamposDoacaoSucessiva.CAMPO_ACCTB01_NUMR_PESS_BENF, 
                                  nomeAtributo = "pessoaBeneficiaria")
            } )
   public ContribuinteIntegracaoVo getPessoaBeneficiaria()
   {
      if (!Validador.isObjetoValido(pessoaBeneficiaria))
      {
         setPessoaBeneficiaria(new ContribuinteIntegracaoVo());
      }
      return pessoaBeneficiaria;
   }

   public void setNumeroGiaITCD(long numeroGiaITCD)
   {
      this.numeroGiaITCD = numeroGiaITCD;
   }
   
  
   public Long getNumeroGiaITCD()
   {
      return numeroGiaITCD;
   }

  
   public String getValorBaseDeCalcFormatado()
   {
      return valorBaseDeCalcFormatado;
   }

   public void setValorBaseDeCalc(double valorBaseDeCalc)
   {
      this.valorBaseDeCalc = valorBaseDeCalc;
      this.setValorBaseDeCalcFormatado(StringUtil.doubleToMonetario(valorBaseDeCalc));
   }

   @AnotacaoAtributo(nomeColuna = CamposDoacaoSucessiva.CAMPO_VALR_BASE_CALC_BENF_GIA_ANTR, obrigatorio = false)
   public double getValorBaseDeCalc()
   {
      return valorBaseDeCalc;
   }

   public void setNumeroEprocess(String numeroEprocess)
   {
      this.numeroEprocess = numeroEprocess;
   }
   
   @AnotacaoAtributo(nomeColuna = CamposDoacaoSucessiva.CAMPO_NUMR_PROT_GIA_ANTR, obrigatorio = false)
   public String getNumeroEprocess()
   {
      return numeroEprocess;
   }

   public void setMesGIAITCDAnterior(String mesGIAITCDAnterior)
   {
      this.mesGIAITCDAnterior = mesGIAITCDAnterior;
   }

   public String getMesGIAITCDAnterior()
   {
      return mesGIAITCDAnterior;
   }

   public void setDataGIAITCDAnterior(Date dataGIAITCDAnterior)
   {
      this.dataGIAITCDAnterior = dataGIAITCDAnterior;
   }

   @AnotacaoAtributo(nomeColuna = CamposDoacaoSucessiva.CAMPO_DATA_GIA_ITCD_ANTR, obrigatorio = false)
   public Date getDataGIAITCDAnterior()
   {
      return dataGIAITCDAnterior;
   }

   public void setValorBaseDeCalcFormatado(String valorBaseDeCalcFormatado)
   {
      this.valorBaseDeCalcFormatado = valorBaseDeCalcFormatado;
   }

   public void setValorITCD(double valorITCD)
   {
      this.valorITCD = valorITCD;
      this.setValorITCDFormatado(StringUtil.doubleToMonetario(valorITCD));
   }

   @AnotacaoAtributo(nomeColuna = CamposDoacaoSucessiva.CAMPO_VALR_ITCD_BENF_GIA_ANTR, obrigatorio = false)
   public double getValorITCD()
   {
      return valorITCD;
   }

   public void setValorITCDFormatado(String valorITCDFormatado)
   {
      this.valorITCDFormatado = valorITCDFormatado;
   }

   public String getValorITCDFormatado()
   {
      return valorITCDFormatado;
   }

   public void setDataInclusao(Date dataInclusao)
   {
      this.dataInclusao = dataInclusao;
   }

   @AnotacaoAtributo(nomeColuna = CamposDoacaoSucessiva.CAMPO_DATA_INCLUSAO, obrigatorio = false)
   public Date getDataInclusao()
   {
      return dataInclusao;
   }

   public void setDataAtualizacao(Date dataAtualizacao)
   {
      this.dataAtualizacao = dataAtualizacao;
   }

   @AnotacaoAtributo(nomeColuna = CamposDoacaoSucessiva.CAMPO_DATA_ATUALIZACAO, obrigatorio = false)
   public Date getDataAtualizacao()
   {
      return dataAtualizacao;
   }
      
   public double getSomaValorBaseDeCalcAnterior() {
       double soma = 0.0;
       if (this.getCollVO() != null && !this.getCollVO().isEmpty()) {
           for (GIAITCDDoacaoSucessivaVo item : this.getCollVO()) {
                   soma += item.getValorBaseDeCalc();
           }
       }
       return soma;
   }
   
   public double getSomaValorITCDAnterior(){
      double soma = 0.0;
      Date dataMaisRecente = null;
      if(this.getCollVO() != null && !this.getCollVO().isEmpty()){
         for(GIAITCDDoacaoSucessivaVo item: this.getCollVO()){
            if(!item.getIsIsentoUpf()){
               if(dataMaisRecente == null || (item.getDataEProcess() != null && item.getDataEProcess().after(dataMaisRecente))){
                     soma = item.getValorITCD();
                     dataMaisRecente = item.getDataEProcess();
                  }
               }
            }
         }      
      return soma;
   }

   public void setDataEProcess(Date dataEProcess)
   {
      this.dataEProcess = dataEProcess;
   }

   @AnotacaoAtributo(nomeColuna = CamposDoacaoSucessiva.CAMPO_DATA_PROT_GIA_ANTR, obrigatorio = false)
   public Date getDataEProcess()
   {
      return dataEProcess;
   }

   public void setFlagDoacaoSucessivaAnterior(DomnSimNao flagDoacaoSucessivaAnterior)
   {
      this.flagDoacaoSucessivaAnterior = flagDoacaoSucessivaAnterior;
   }

   public DomnSimNao getFlagDoacaoSucessivaAnterior()
   {
      return flagDoacaoSucessivaAnterior;
   }

   public void setValorITCDBeneficiario(double valorITCDBeneficiario)
   {
      this.valorITCDBeneficiario = valorITCDBeneficiario;
   }

   public double getValorITCDBeneficiario()
   {
      return valorITCDBeneficiario;
   }
   
   public String getValorITCDBeneficiarioFormatado(){
      return StringUtil.doubleToMonetario(getValorITCDBeneficiario(), 2);
   }

   public void setIsIsentoUpf(boolean isIsentoUpf){
      this.isIsentoUpf = isIsentoUpf;
   }

   public boolean getIsIsentoUpf(){
      return isIsentoUpf;
   }

   public void setValorRecolherBenef(double valorRecolherBenef)
   {
      this.valorRecolherBenef = valorRecolherBenef;
   }

   @AnotacaoAtributo(nomeColuna = CamposDoacaoSucessiva.CAMPO_VALR_ITCD_RCLH_BENF, obrigatorio = false)
   public double getValorRecolherBenef(){
      setValorRecolherBenef(getValorITCDBeneficiario());
      return valorRecolherBenef;
   }

   public void setInfoIsencao(Integer infoIsencao){
      this.infoIsencao = infoIsencao;
   }

   @AnotacaoAtributo(
          nomeColuna = CamposDoacaoSucessiva.CAMPO_INFO_ISENCAO
          ,obrigatorio = false
   )
   public Integer getInfoIsencao(){
      if(this.isIsentoUpf){
         setInfoIsencao(2);
      }else{
         setInfoIsencao(1);
      }
      return infoIsencao;
   }


   public void setValorRecebidoBenefAnterior(double valorRecebidoBenefAnterior){
      this.valorRecebidoBenefAnterior = valorRecebidoBenefAnterior;
   }

   @AnotacaoAtributo(
          nomeColuna = CamposDoacaoSucessiva.CAMPO_VALR_RECEBIDO_BENF_GIA_ANTR
          ,obrigatorio = false
   )
   public double getValorRecebidoBenefAnterior(){
      setValorRecebidoBenefAnterior(getValorBaseDeCalc());
      return valorRecebidoBenefAnterior;
   }
}
