 /**
  * Ábaco Tecnologia de Informação - LTDA
  * Arquivo: GIAITCDVo.java
  * Revisão: Marlo Einchenberg Motta
  * Data revisão: 10/12/2007
  */
 package br.gov.mt.sefaz.itc.model.generico.giaitcd;

 import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
 import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
 import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
 import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
 import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
 import br.com.abaco.util.StringUtil;
 import br.com.abaco.util.Validador;
 import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

 import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
 import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
 import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarVo;
 import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioVo;
 import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
 import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
 import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
 import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.GIAITCDInventarioArrolamentoBeneficiarioVo;
 import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
 import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoVo;
 import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.ParametroLegislacaoVo;
 import br.gov.mt.sefaz.itc.util.EntidadeVo;
 import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
 import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
 import br.gov.mt.sefaz.itc.util.dominio.DomnSituacaoProcessamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsulta;
 import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCD;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDTemporario;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
 import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;
 import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;
 import br.gov.mt.sefaz.itc.util.integracao.cadastro.UFIntegracaoVO;
 import br.gov.mt.sefaz.itc.util.integracao.eprocess.EprocessBe;
 import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;
 import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.UnidadeSefazIntegracaoVo;

 import java.io.Serializable;

 import java.sql.SQLException;

 import java.util.Collection;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.Date;
import java.util.Iterator;
import java.util.List;

 import sefaz.mt.util.SefazDataHora;


 /**
  * Classe responsável por encapsular os valores do objeto GIA ITCD (Value Object).
  * @author Daniel Balieiro
  * @version $Revision: 1.11 $
  */
  @AnotacaoTabelaClasse
  (
      nomeTabela = TabelasITC.TABELA_GIA_ITCD
      ,nomeAmigavel = "GIA-ITCD"
      ,identificadores =
      {
          @AnotacaoIdentificador
          (
              nomeAtributo = "codigo"
              ,nomeColuna = CamposGIAITCD.CAMPO_CODIGO_GIA_ITCD
          )
      }
  )
 public class GIAITCDVo extends EntidadeVo<GIAITCDVo> implements Serializable
 {
    // ---------------------------------------------------------------ATRIBUTOS----------------------------------------------------------------------------------
    private static final long serialVersionUID = Long.MAX_VALUE;
    private ContribuinteIntegracaoVo responsavelVo;
    private ContribuinteIntegracaoVo procuradorVo;
    private NaturezaOperacaoVo naturezaOperacaoVo;
    private ParametroLegislacaoVo parametroLegislacaoVo;
    private Date dataCriacao;
    private DomnTipoGIA tipoGIA;
    private String senha;
    private String codigoAutenticidade;
    private double valorTotalBensDeclarados;
    private double valorTotalArbitrado;
    private double valorTotalInformadoBensDeclarados;
    private double valorTotalBensDeclaradosPosteriorCasamento;
    private double valorTotalBensDeclaradosAnteriorCasamento;
    private double valorUPF;
    private double valorCalculoDemonstrativo;
    private double valorITCD;
    private double valorITCDOriginal;
    private double valorITCDRetificado;
    private double valorRecolhimento;
    private double valorTSE = 0;
    private double valorBaseCalculoTributavel;
    private MunicipioIntegracaoVo municipioProtocolar; // Município para protocolar
    private DomnSimNao possuiCPF;
    private long numrDeclaracaoFatoGerador; // Numero da declaração da nao ocorrencia de fato gerador
    private long numrDeclaracaoIsencao; //Número da declaração de isenção
    private Date dataAtualizacaoBD; // Data de atualização do registro para o DW
    private BemTributavelVo bemTributavelVo;
    private BeneficiarioVo beneficiarioVo;
    private transient GIAITCDDarVo giaITCDDarVo;
    private double valorCorrecaoMonetaria;
    private double valorJuros;
    private double valorMulta;
    private transient GIAITCDTemporarioVo temporarioVo;
    private StatusGIAITCDVo statusVo;
    private UnidadeSefazIntegracaoVo agenciaProtocolo;
    private transient UnidadeSefazIntegracaoVo agenciaAvaliacao;
    private DomnSimNao giaConfirmada;
    private transient String mensagemConfirmarGia;
    private ServidorSefazIntegracaoVo servidorSefazResponsavelAlteracaoVo;
    private transient boolean usuarioServidor;
    private transient DomnTipoConsulta tipoConsultaGia;
    private transient ServidorSefazIntegracaoVo servidorSefazIntegracaoVo;
    private String justificativaAlteracao; 
    private transient boolean imprimirDar;
    private transient boolean giaRetificada;
    private transient boolean giaAvaliada;
    private transient boolean giaParcelada;
    private transient StatusGIAITCDVo statusAnterior;
    private transient boolean reabrirAvaliacao;
    private transient boolean excluirAvaliacao;
    private transient DomnAtivoInativo statusUltimaAvaliacao;
    private transient UFIntegracaoVO ufIntegracaoVO;
    private DomnVersaoGIAITCD numeroVersaoGIAITCD;
    private DomnTipoProtocolo tipoProtocoloGIA;
    private transient DomnSimNao alterarParaPendenteProtocolo;   
    private Boolean exibirIsencaoBemTributavel;
    private Date dataLimiteExibirIsencaoBemTributavel;
    private transient boolean existeDoacaoSucessivaNaoRegParaBenef;
    private transient boolean giaUtilizadaParaDoacaoSucessiva;
    private Date dataIsencaoPrevistaParametroGerencial;
    
    /**
     * Em alguns casos o contribuinte tem opção de selecionar
     * se deseja que o protocolo da GIA seja MANUAL ou AUTOMATICO.
     * Atualmente o único local onde o contribuinte pode
     * selecionar o tipo de protocolo é na aba "demonstrativo de calculo"
     */
    private transient DomnTipoProtocolo tipoProtocoloGIASelecionadoPeloContribuinte;
    private transient boolean tipoProtocoloGIAOpcional;
    private transient DomnSituacaoProcessamento situacaoProcessamento;
    private transient String descricaoMensagemSituacaoErrro;
    
    // Porcentagem a ser considerada nos calculos.    
    private double porcentagemAconsiderar = 0.0;  
    
   private boolean giaIsentaUpf;
        
    //-----------------------------------------------------------------------CONSTRUTORES------------------------------------------------------------------------
    /**
     * Construtor padrao.
     * @implemented by Daniel Balieiro
     */
    public GIAITCDVo()
    {
       super();
    }

    /**
     * Construtor que recebe a Chave Primï¿½ria do Value Object.
     * @param codigo Chave Primaria
     * @implemented by Daniel Balieiro
     */
    public GIAITCDVo(long codigo)
    {
       super(codigo);
    }

    /**
     * Construtor que recebe um Value Object como Parametro de Consulta.
     * @param giaITCDVo Parametro de Consulta
     * @implemented by Daniel Balieiro
     */
    public GIAITCDVo(GIAITCDVo giaITCDVo)
    {
       super();
       setParametroConsulta(giaITCDVo);
    }

    //-----------------------------------------------------------------------GETTERS & SETTERS-------------------------------------------------------------------
            
    public long getCodigo()
    {
       //trigger(super.getCodigo());
       return super.getCodigo();
    }
    /**
     * Construtor que recebe uma Collection de GIA ITCD Value Object.
     * @param collVO Colecao de GIAITCDVo
     * @implemented by Daniel Balieiro
     */
    public GIAITCDVo(Collection collVO)
    {
       super(collVO);
    }

    /**
     * Retorna a Chave Primï¿½ria.
     * @return GIAITCDPk
     * @implemented by Daniel Balieiro
     */
    public GIAITCDPk getPk()
    {
       return new GIAITCDPk(getCodigo());
    }

    /**
     * Atribui a Data de Criacao.
     * @param dataCriacao
     * @implemented by Daniel Balieiro
     */
    public void setDataCriacao(Date dataCriacao)
    {
       this.dataCriacao = dataCriacao;
    }

    /**
     * Retorna a Data de Criacao.
     * @return Date
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_DATA_CRIACAO
         ,obrigatorio = true
     )
    public Date getDataCriacao()
    {
       return dataCriacao;
    }

    /**
     * Retorna a Data de Criacao Formatada.
     * @return String
     * @implemented by Daniel Balieiro
     */
    public String getDataCriacaoFormatada(){
       if (Validador.isDataValida(getDataCriacao())){
          return new SefazDataHora(getDataCriacao()).formata("dd/MM/yyyy");
       }
       else{
          return STRING_VAZIA;
       }
    }
    
    public String getAnoCriacaoFormatado() {
         if (Validador.isDataValida(getDataCriacao())) {
            return new SefazDataHora(getDataCriacao()).formata("yyyy");
         } else {
            return STRING_VAZIA;
      }
   }

    /**
     * Atribui o Tipo Gia.
     * @param tipoGIA
     * @implemented by Daniel Balieiro
     */
    public void setTipoGIA(DomnTipoGIA tipoGIA)
    {
       this.tipoGIA = tipoGIA;
    }

    /**
     * Retorna o Tipo GIA.
     * @return DomnTipoGIA
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_TIPO_ITCD
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
     * Atribui a Senha.
     * @param senha
     * @implemented by Daniel Balieiro
     */
    public void setSenha(String senha)
    {
       this.senha = senha;
    }

    /**
     * Retorna a Info Senha.
     * @return String
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_INFO_SENHA
         ,obrigatorio = true
     )
    public String getSenha()
    {
       if (!Validador.isStringValida(senha))
       {
          return STRING_VAZIA;
       }
       return senha;
    }

    /**
     * Método responsável por verificar se existe uma senha vï¿½lida.
     * @return
     * @implemented by Marlo Eichenberg Motta
     */
    public boolean isExisteSenha()
    {
       return (Validador.isStringValida(senha));
    }

    /**
     * Retorna a Data do Protocolo do ITCD formatada.
     * @return Date
     * @implemented by Leandro Dorileo
     */
    public String getDataProtocoloITCDFormatada()
    {
       if (Validador.isDataValida(getStatusVo().getDataProtocolo()))
       {
          return new SefazDataHora(getStatusVo().getDataProtocolo()).formata("dd/MM/yyyy");
       }
       return STRING_VAZIA;
    }

    /**
     * Atribui o Código de Autenticidade.
     * @param codigoAutenticidade
     * @implemented by Daniel Balieiro
     */
    public void setCodigoAutenticidade(String codigoAutenticidade)
    {
       this.codigoAutenticidade = codigoAutenticidade;
    }

    /**
     * Retorna o Codigo de Autenticidade.
     * @return String
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_CODIGO_AUTENTICIDADE
         ,obrigatorio = true
     )
    public String getCodigoAutenticidade()
    {
       if (!Validador.isStringValida(codigoAutenticidade))
       {
          return STRING_VAZIA;
       }
       return codigoAutenticidade;
    }

    /**
     * Retorna a Data de Atualização Formatada.
     * @return String
     * @implemented by Daniel Balieiro
     */
    public String getDataAtualizacaoFormatada()
    {
       if (Validador.isDataValida(getDataAtualizacaoBD()))
       {
          return new SefazDataHora(getDataAtualizacaoBD()).formata("dd/MM/yyyy");
       }
       else
       {
          return "";
       }
    }

    /**
     * Atribui uma Natureza de Operaïção a esta GIA ITCD.
     * @param naturezaOperacaoVo
     * @implemented by Daniel Balieiro
     */
    public void setNaturezaOperacaoVo(NaturezaOperacaoVo naturezaOperacaoVo)
    {
       this.naturezaOperacaoVo = naturezaOperacaoVo;
    }

    /**
     * Retorna a Natureza de Operacao desta GIA ITCD.
     * @return NaturezaOperacaoVo
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributoExterno
     (
         obrigatorio = true
         ,colunaReferencia =
         {
             @AnotacaoColunaExterna
             (
                 nomeColuna = CamposGIAITCD.CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO
                 , nomeAtributo = "codigo"
             )
         }
     )
    public NaturezaOperacaoVo getNaturezaOperacaoVo()
    {
       if (!Validador.isObjetoValido(naturezaOperacaoVo))
       {
          setNaturezaOperacaoVo(new NaturezaOperacaoVo());
       }
       return naturezaOperacaoVo;
    }

    /**
     * Atribui um Procurador a este GIA ITCD.
     * @param procuradorVo
     * @implemented by Daniel Balieiro
     */
    public void setProcuradorVo(ContribuinteIntegracaoVo procuradorVo)
    {
       this.procuradorVo = procuradorVo;
    }

    /**
     * Retorna o Procurador desta GIA ITCD.
     * @return ContribuinteIntegracaoVo
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributoExterno
     (
         obrigatorio = false
         ,colunaReferencia =
         {
             @AnotacaoColunaExterna
             (
                 nomeColuna = CamposGIAITCD.CAMPO_ACCTB01_NUMERO_PESSOA_PROCURADOR
                 , nomeAtributo = "numrContribuinte"
             )
         }
     )
    public ContribuinteIntegracaoVo getProcuradorVo()
    {
       if (!Validador.isObjetoValido(procuradorVo))
       {
          setProcuradorVo(new ContribuinteIntegracaoVo());
       }
       return procuradorVo;
    }

    /**
     * Atribui um Responsavel desta GIA ITCD.
     * @param responsavelVo
     * @implemented by Daniel Balieiro
     */
    public void setResponsavelVo(ContribuinteIntegracaoVo responsavelVo)
    {
       this.responsavelVo = responsavelVo;
    }

    /**
     * Atribui uma GIA-ITCD do tipo Inventï¿½rio Arrolamento.
     * @param inventarioArrolamentoVo
     * @implemented by Marlo Eichenberg Motta
     */
    public void setInventarioArrolamentoVo(GIAITCDInventarioArrolamentoVo inventarioArrolamentoVo)
    {
       try
       {
          Validador.validaObjeto(inventarioArrolamentoVo);
       }
       catch (ObjetoObrigatorioException e)
       {
          setInventarioArrolamentoVo(new GIAITCDInventarioArrolamentoVo());
       }
    }

    /**
     * Atribui um Bem Tributï¿½vel.
     * @param bemTributavelVo Bem Tributï¿½vel (Value Object).
     * @implemented by Marlo Eichenberg Motta
     */
    public void setBemTributavelVo(BemTributavelVo bemTributavelVo)
    {
       this.bemTributavelVo = bemTributavelVo;
    }

    /**
     * Retorna o bem tributavel.
     * @return
     * @implemented by Marlo Eichenberg Motta
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
     * Atribui um beneficiario.
     * @param beneficiarioVo
     * @implemented by Marlo Eichenberg Motta
     */
    public void setBeneficiarioVo(BeneficiarioVo beneficiarioVo)
    {
       this.beneficiarioVo = beneficiarioVo;
       if (Validador.isObjetoValido(beneficiarioVo))
       {
          this.beneficiarioVo.alinhaReferencia(this, this.getUsuarioLogado());
       }
    }

    /**
     * Retorna o beneficiario.
     * @return
     * @implemented by Marlo Eichenberg Motta
     */
    public BeneficiarioVo getBeneficiarioVo()
    {
       if (!Validador.isObjetoValido(beneficiarioVo))
       {
          if (this instanceof GIAITCDDoacaoVo)
          {
             setBeneficiarioVo(new GIAITCDDoacaoBeneficiarioVo());
          }
          else if (this instanceof GIAITCDInventarioArrolamentoVo)
          {
             setBeneficiarioVo(new GIAITCDInventarioArrolamentoBeneficiarioVo());
          }
          else
          {
             setBeneficiarioVo(new BeneficiarioVo());
          }
       }
       return beneficiarioVo;
    }

    /**
     * Retorna o responsável.
     * @return
     * @implemented by Marlo Eichenberg Motta
     */
     @AnotacaoAtributoExterno
     (
         obrigatorio = true
         ,colunaReferencia =
         {
             @AnotacaoColunaExterna
             (
                 nomeColuna = CamposGIAITCD.CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL
                 , nomeAtributo = "numrContribuinte"
             )
         }
     )
    public ContribuinteIntegracaoVo getResponsavelVo()
    {
       if (!Validador.isObjetoValido(responsavelVo))
          setResponsavelVo(new ContribuinteIntegracaoVo());
       return responsavelVo;
    }

    /**
     * Atribui um parâmetro da legislacao.
     * @param parametroLegislacaoVo
     * @implemented by Marlo Eichenberg Motta
     */
    public void setParametroLegislacaoVo(ParametroLegislacaoVo parametroLegislacaoVo)
    {
       this.parametroLegislacaoVo = parametroLegislacaoVo;
    }

    /**
     * Retorna o parâmetro da legislacao.
     * @return
     * @implemented by Marlo Eichenberg Motta
     */
     @AnotacaoAtributoExterno
     (
         obrigatorio = true
         ,colunaReferencia =
         {
             @AnotacaoColunaExterna
             (
                 nomeColuna = CamposGIAITCD.CAMPO_ITCTB09_CODIGO_LEGISLACAO
                 , nomeAtributo = "codigo"
             )
         }
     )
    public ParametroLegislacaoVo getParametroLegislacaoVo()
    {
       if (!Validador.isObjetoValido(parametroLegislacaoVo))
       {
          setParametroLegislacaoVo(new ParametroLegislacaoVo());
       }
       return parametroLegislacaoVo;
    }

    /**
     * Atribui uma GIAITCD Temporario.
     * @param temporarioVo
     * @implemented by Marlo Eichenberg Motta
     */
    public void setTemporarioVo(GIAITCDTemporarioVo temporarioVo)
    {
       this.temporarioVo = temporarioVo;
    }

    /**
     * Retorna a GIAITCD Temporaria.
     * @return
     * @implemented by Marlo Eichenberg Motta
     */
    public GIAITCDTemporarioVo getTemporarioVo()
    {
       if (!Validador.isObjetoValido(temporarioVo))
       {
          setTemporarioVo(new GIAITCDTemporarioVo());
          temporarioVo.setCodigo(this.getCodigo());
       }
       return temporarioVo;
    }

    /**
     * Atribui um Status.
     * @param statusVo
     * @implemented by Marlo Eichenberg Motta
     */
    public void setStatusVo(StatusGIAITCDVo statusVo)
    {
       this.statusVo = statusVo;
    }

    /**
     * Retorna o status.
     * @return StatusGIAITCDVo
     * @implemented by Daniel Balieiro
     * @implemented by Marlo Eichenberg Motta
     */
    public StatusGIAITCDVo getStatusVo()
    {
       if (!Validador.isObjetoValido(statusVo))
          setStatusVo(new StatusGIAITCDVo());
       //atualiza a referencia do gia com o status
       if (Validador.isNumericoValido(this.getCodigo()))
       {
          statusVo.getGiaITCDVo().setCodigo(this.getCodigo());
       }
       return statusVo;
    }

    /**
     * Atribui o valor da GIA ITCD.
     * @param valorITCD
     * @implemented by Marlo Eichenberg Motta
     */
    public void setValorITCD(double valorITCD)
    {
       this.valorITCD = valorITCD;
    }

    /**
     * Retorna o valor da GIA ITCD.
     * @return
     * @implemented by Marlo Eichenberg Motta
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_VALOR_ITCD
         ,obrigatorio = false
     )
    public double getValorITCD()
    {
       return valorITCD;
    }

    /**
     * Método que retorna valor ITCD formatado
     * @return String
     * @implemented by Anderson Boehler Iglesias Araujo
     */
    public String getValorITCDFormatado()
    {
       return StringUtil.doubleToMonetario(getValorITCD());
    }

    /**
     * Atribui o  valor da correï¿½ï¿½o monetï¿½ria.
     * @param valorCorrecaoMonetaria
     * @implemented by Marlo Eichenberg Motta
     */
    public void setValorCorrecaoMonetaria(double valorCorrecaoMonetaria)
    {
       this.valorCorrecaoMonetaria = valorCorrecaoMonetaria;
    }

    /**
     * Retorna o valor da correï¿½ï¿½o monetï¿½ria.
     * @return
     * @implemented by Marlo Eichenberg Motta
     */
    public double getValorCorrecaoMonetaria()
    {
       return valorCorrecaoMonetaria;
    }

    /**
     * Método que retorna valorCorrecaoMonetaria formatado
     * @return String
     * @implemented by Anderson Boehler Iglesias Araujo
     */
    public String getValorCorrecaoMonetariaFormatado()
    {
       return StringUtil.doubleToMonetario(getValorCorrecaoMonetaria());
    }

    /**
     * Atribui o valor de multa.
     * @param valorMulta
     * @implemented by Marlo Eichenberg Motta
     */
    public void setValorMulta(double valorMulta)
    {
       this.valorMulta = valorMulta;
    }

    /**
     * Retorna o valor de multa.
     * @return
     * @implemented by Marlo Eichenberg Motta
     */
    public double getValorMulta()
    {
       return valorMulta;
    }

    /**
     * Método que retorna o valor Multa Formatado
     * @return
     * @implemented by Anderson Boehler Iglesias Araujo
     */
    public String getValorMultaFormatado()
    {
       return StringUtil.doubleToMonetario(getValorMulta());
    }

    /**
     * Atribui o valor de juros.
     * @param valorJuros
     * @implemented by Marlo Eichenberg Motta
     */
    public void setValorJuros(double valorJuros)
    {
       this.valorJuros = valorJuros;
    }

    /**
     * Retorna o valor de juros.
     * @return String
     * @implemented by Marlo Eichenberg Motta
     */
    public double getValorJuros()
    {
       return valorJuros;
    }

    /**
     * Método que retorna o valor Juros Formatado
     * @return String
     * @implemented by Anderson Boehler Iglesias Araujo
     */
    public String getValorJurosFormatado()
    {
       return StringUtil.doubleToMonetario(getValorJuros());
    }

    /**
     * Atribui o valor TSE.
     * @param valorTSE
     * @implemented by Marlo Eichenberg Motta
     */
    public void setValorTSE(double valorTSE)
    {
       this.valorTSE = valorTSE;
    }

    /**
     * Retorna o valor TSE.
     * @return
     * @implemented by Marlo Eichenberg Motta
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_VALOR_TSE
         ,obrigatorio = false
     )
    public double getValorTSE()
    {
       return valorTSE;
    }

    /**
     * Método que retorna o valor TSE Formatado
     * @return String
     * @implemented by Anderson Boehler Iglesias Araujo
     */
    public String getValorTSEFormatado()
    {
       return StringUtil.doubleToMonetario(getValorTSE());
    }

    /**
     * Atribui o valor do recolhimento.
     * @param valorRecolhimento
     * @implemented by Marlo Eichenberg Motta
     */
    public void setValorRecolhimento(double valorRecolhimento)
    {
       this.valorRecolhimento = valorRecolhimento;
    }

    /**
     * Retorna o valor do recolhimento.
     * @return
     * @implemented by Marlo Eichenberg Motta
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_VALOR_TOTAL_RECOLHER
         ,obrigatorio = false
     )
    public double getValorRecolhimento()
    {
       if (Validador.isNumericoValido(getValorITCD()) && Validador.isNumericoValido(getValorTSE()))
       {
          setValorRecolhimento(getValorITCD() + getValorTSE());
       }
       return valorRecolhimento;
    }

    /**
     * Método que retorna o valor recolhimento Formatado
     * @return String
     * @implemented by Anderson Boehler Iglesias Araujo
     */
    public String getValorRecolhimentoFormatado()
    {
       return StringUtil.doubleToMonetario(getValorRecolhimento());
    }

    /**
     * Atribui a agência de protocolo.
     * @param agenciaProtocolo
     * @implemented by Marlo Eichenberg Motta
     */
    public void setAgenciaProtocolo(UnidadeSefazIntegracaoVo agenciaProtocolo)
    {
       this.agenciaProtocolo = agenciaProtocolo;
    }

    /**
     * Retorna a agência de protocolo.
     * @return
     * @implemented by Marlo Eichenberg Motta
     */
    public UnidadeSefazIntegracaoVo getAgenciaProtocolo()
    {
       if (!Validador.isObjetoValido(this.agenciaProtocolo))
       {
          setAgenciaProtocolo(new UnidadeSefazIntegracaoVo());
       }
       return this.agenciaProtocolo;
    }

    /**
     * Método que retorna o Valor Total dos Bens Declarados.<br>
     * Quando não existir valor a ser somado, o retorno será igual a Zero.
     * 
     * @return double
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_VALOR_TOTAL_BENS
         ,obrigatorio = false
     )
    public double getValorTotalBensDeclarados()
    {
       return valorTotalBensDeclarados;
    }
    
    /**
     * Método que retorna o Valor Total dos Bens Declarados Formatado.
     * @return String
     * @implemented by Anderson Boehler Iglesias Araujo
     */
    public String getValorTotalBensDeclaradosFormatado()
    {
       return StringUtil.doubleToMonetario(getValorTotalBensDeclarados());
    }
    
    public void setValorTotalBensDeclaradosPosteriorCasamento(double valorTotalBensDeclaradosPosteriorCasamento)
    {
       this.valorTotalBensDeclaradosPosteriorCasamento = valorTotalBensDeclaradosPosteriorCasamento;
    }

    public double getValorTotalBensDeclaradosPosteriorCasamento()
    {
       return valorTotalBensDeclaradosPosteriorCasamento;
    }

    public String getValorTotalBensDeclaradosPosteriorCasamentoFormatado()
    {
       return StringUtil.doubleToMonetario(getValorTotalBensDeclaradosPosteriorCasamento());
    }
    

    public void setValorTotalBensDeclaradosAnteriorCasamento(double valorTotalBensDeclaradosAnteriorCasamento)
    {
       this.valorTotalBensDeclaradosAnteriorCasamento = valorTotalBensDeclaradosAnteriorCasamento;
    }

    public double getValorTotalBensDeclaradosAnteriorCasamento()
    {
       return valorTotalBensDeclaradosAnteriorCasamento;
    }  

    public String getValorTotalBensDeclaradosAnteriorCasamentoFormatado()
    {
       return StringUtil.doubleToMonetario(getValorTotalBensDeclaradosAnteriorCasamento());
    }     

    /**
     * Atribui o Valor da UPF
     * @param valorUPF
     * @implemented by Daniel Balieiro
     */
    public void setValorUPF(double valorUPF)
    {
       this.valorUPF = valorUPF;
    }

    /**
     * Retorna o Valor da UPF
     * @return
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_VALOR_UPF
         ,obrigatorio = true
     )
    public double getValorUPF()
    {
       return valorUPF;
    }

    /**
     * Método que retorna o valor UPF Formatado
     * @return String
     * @implemented by Anderson Boehler Iglesias Araujo
     */
    public String getValorUPFFormatado()
    {
       return StringUtil.doubleToMonetario(getValorUPF());
    }

    /**
     * Atribui o Valor para Calculo Demonstrativo
     * @param valorCalculoDemonstrativo
     * @implemented by Daniel Balieiro
     */
    public void setValorCalculoDemonstrativo(double valorCalculoDemonstrativo)
    {
       this.valorCalculoDemonstrativo = valorCalculoDemonstrativo;
    }

    /**
     * Retorna o Valor para Calculo Demonstrativo
     * @return
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_VALOR_CALCULO_DEMONSTRATIVO
         ,obrigatorio = false
     )
    public double getValorCalculoDemonstrativo()
    {
       return valorCalculoDemonstrativo;
    }

    /**
     * Método que retorna o valor calculo demonstrativo Formatado
     * @return String
     * @implemented by Anderson Boehler Iglesias Araujo
     */
    public String getValorCalculoDemonstrativoFormatado()
    {
       return StringUtil.doubleToMonetario(getValorCalculoDemonstrativo());
    }

    /**
     * Atribui o Valor Total de Bens Declarados
     * 
     * @param valorTotalBensDeclarados
     * @implemented by Lucas Nascimento
     */
    public void setValorTotalBensDeclarados(double valorTotalBensDeclarados)
    {
       this.valorTotalBensDeclarados = valorTotalBensDeclarados;
    }

    /**
     * Atribui um DAR a GIA
     * @param giaITCDDarVo
     * @implemented by Lucas Nascimento
     */
    public void setGiaITCDDarVo(GIAITCDDarVo giaITCDDarVo)
    {
       this.giaITCDDarVo = giaITCDDarVo;
    }

    /**
     * Retorna o Dar de uma GIA
     * @return
     * @implemented by Lucas Nascimento
     */
    public GIAITCDDarVo getGiaITCDDarVo()
    {
       if (!Validador.isObjetoValido(giaITCDDarVo))
       {
          setGiaITCDDarVo(new GIAITCDDarVo());
       }
       return giaITCDDarVo;
    }

    /**
     * Atribui o Municipio a Protocolar de uma GIA
     * @param municipioProtocolar
     * @implemented by Lucas Nascimento
     */
    public void setMunicipioProtocolar(MunicipioIntegracaoVo municipioProtocolar)
    {
       this.municipioProtocolar = municipioProtocolar;
    }

    /**
     * Retorna o municipio a protocolar
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
                 nomeColuna = CamposGIAITCD.CAMPO_MUNICIPIO_PROTOCOLAR
                 , nomeAtributo = "codgMunicipio"
             )
         }
     )
    public MunicipioIntegracaoVo getMunicipioProtocolar()
    {
       if (!Validador.isObjetoValido(municipioProtocolar))
          setMunicipioProtocolar(new MunicipioIntegracaoVo());
       return municipioProtocolar;
    }

    /**
     * Atribui o Status de confirmação de GIA
     * @param giaConfirmada
     * @implemented by Lucas Nascimento
     */
    public void setGiaConfirmada(DomnSimNao giaConfirmada)
    {
       //System.out.println("CONFIRMADA : "+giaConfirmada.getTextoCorrente());
       this.giaConfirmada = giaConfirmada;
    }

    /**
     * Retorna o Status de confirmação de uma GIA
     * @return
     * @implemented by Lucas Nascimento
     */
    public DomnSimNao getGiaConfirmada()
    {
       if (!Validador.isDominioNumericoValido(giaConfirmada))
       {
          setGiaConfirmada(new DomnSimNao(DomnSimNao.NAO));
       }
       return giaConfirmada;
    }

    /**
     * Atribui o Status de Possui CPF
     * @param possuiCPF
     * @implemented by Lucas Nascimento
     */
    public void setPossuiCPF(DomnSimNao possuiCPF)
    {
       this.possuiCPF = possuiCPF;
    }

    /**
     * Retorna o Status de Possui CPF
     * @return
     * @implemented by Lucas Nascimento
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_POSSUI_CPF
         ,obrigatorio = false
     )
    public DomnSimNao getPossuiCPF()
    {
       if (!Validador.isDominioNumericoValido(possuiCPF))
       {
          setPossuiCPF(new DomnSimNao(-1));
       }
       return possuiCPF;
    }

    /**
     * Atribui o Número de Declaração de Não Ocorrência de Fato Gerador
     * @param numrDeclaracaoFatoGerador
     * @implemented by Lucas Nascimento
     */
    public void setNumrDeclaracaoFatoGerador(long numrDeclaracaoFatoGerador)
    {
       this.numrDeclaracaoFatoGerador = numrDeclaracaoFatoGerador;
    }

    /**
     * Retorna o Número de Declaração de Não Ocorrência de Fato Gerador
     * @return
     * @implemented by Lucas Nascimento
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_NUMR_DECL_FATO_GERADOR
         ,obrigatorio = false
     )
    public long getNumrDeclaracaoFatoGerador()
    {
       return numrDeclaracaoFatoGerador;
    }

    /**
     * Retorna o Número de Declaração de Não Ocorrência de Fato Gerador Formatado
     * @return String
     * @implemented by Anderson Boehler Iglesias Araujo
     */
    public String getNumrDeclaracaoFatoGeradorFormatado()
    {
       if (!Validador.isNumericoValido(getNumrDeclaracaoFatoGerador()))
       {
          return STRING_VAZIA;
       }
       return String.valueOf(getNumrDeclaracaoFatoGerador());
    }

    /**
     * Atribui a Mensagem para uma Gia Confirmada
     * @param mensagemConfirmarGia
     * @implemented by Lucas Nascimento
     */
    public void setMensagemConfirmarGia(String mensagemConfirmarGia)
    {
       this.mensagemConfirmarGia = mensagemConfirmarGia;
    }

    /**
     * Retorna a Mensagem para uma Gia Confirmada
     * @return
     * @implemented by Lucas Nascimento
     */
    public String getMensagemConfirmarGia()
    {
       if (!Validador.isStringValida(mensagemConfirmarGia))
       {
          return STRING_VAZIA;
       }
       return mensagemConfirmarGia;
    }

    /**
     * Atribui o Número da Declaração de Isenção
     * @param numrDeclaracaoIsencao
     * @implemented by Lucas Nascimento
     */
    public void setNumrDeclaracaoIsencao(long numrDeclaracaoIsencao)
    {
       this.numrDeclaracaoIsencao = numrDeclaracaoIsencao;
    }

    /**
     * Retorna o Número da Declaração de Isenção
     * @return
     * @implemented by Lucas Nascimento
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_NUMR_DECL_ISENCAO
         ,obrigatorio = false
     )
    public long getNumrDeclaracaoIsencao()
    {
       return numrDeclaracaoIsencao;
    }

    /**
     *  Retorna o Número da Declaração de Isenção Formatado
     * @return String
     * @implemented by Anderson Boehler Iglesias Araujo
     */
    public String getNumrDeclaracaoIsencaoFormatado()
    {
       if (!Validador.isNumericoValido(getNumrDeclaracaoIsencao()))
       {
          return STRING_VAZIA;
       }
       return String.valueOf(getNumrDeclaracaoIsencao());
    }

    /**
     * Atribui a Data de Atualização BD
     * @param dataAtualizacaoBD
     * @implemented by Daniel Balieiro
     */
    public void setDataAtualizacaoBD(Date dataAtualizacaoBD)
    {
       this.dataAtualizacaoBD = dataAtualizacaoBD;
    }

    /**
     * Retorna a Data de Atualização BD
     * @return
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposGIAITCD.CAMPO_DATA_ATUALIZACAO_BD
         ,obrigatorio = true
         ,timestamp = true
     )
    public Date getDataAtualizacaoBD()
    {
       return dataAtualizacaoBD;
    }

    /**
     * Retorna a Data de Atualização Formatada
     * @return
     * @implemented by Daniel Balieiro
     */
    public String getDataAtualizacaoBDFormatada()
    {
       if (Validador.isDataValida(getDataAtualizacaoBD()))
       {
          return new SefazDataHora(getDataAtualizacaoBD()).formata("dd/MM/yyyy");
       }
       else
       {
          return "";
       }     
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
       //Este método tem que ser sobrescrito nas subclasses, 
       //infelismente DEVIDO AO PADRÃO SEFAZ não declararam essa classe como ABSTRACT
       return new ContribuinteIntegracaoVo();
    }

    public void setValorBaseCalculoTributavel(double valorBaseCalculoTributavel)
    {
       this.valorBaseCalculoTributavel = valorBaseCalculoTributavel;
    }

    public double getValorBaseCalculoTributavel()
    {
       return valorBaseCalculoTributavel;
    }
    
    public String getValorBaseCalculoTributavelFormatado()
    {
       if(!Validador.isNumericoValido(valorBaseCalculoTributavel))
       {
          return "0,00";       
       }
       return StringUtil.doubleToMonetario(valorBaseCalculoTributavel);
    }

    //== Servidor Sefaz Integração ==\\
    @AnotacaoAtributoExterno
    (
        obrigatorio = false
        ,colunaReferencia =
        {
            @AnotacaoColunaExterna
            (
                nomeColuna = CamposGIAITCD.CAMPO_SERVIDOR_RESPONSAVEL_ALTERACAO
                , nomeAtributo = "numrMatricula"
            )
        }
    )
     public ServidorSefazIntegracaoVo getServidorSefazResponsavelAlteracaoVo()
     {
        if (servidorSefazResponsavelAlteracaoVo == null)
        {
           setServidorSefazResponsavelAlteracaoVo(new ServidorSefazIntegracaoVo());
        }
        return servidorSefazResponsavelAlteracaoVo;
     }

    public void setServidorSefazResponsavelAlteracaoVo(ServidorSefazIntegracaoVo servidorSefazIntegracaoVo)
    {
       this.servidorSefazResponsavelAlteracaoVo = servidorSefazIntegracaoVo;
    }

    public void setUsuarioServidor(boolean usuarioServidor)
    {
       this.usuarioServidor = usuarioServidor;
    }

    public boolean isUsuarioServidor()
    {
       return usuarioServidor;
    }

    public void setTipoConsultaGia(DomnTipoConsulta tipoConsultaGia)
    {
       this.tipoConsultaGia = tipoConsultaGia;
    }

    public DomnTipoConsulta getTipoConsultaGia()
    {
       if(!Validador.isDominioNumericoValido(tipoConsultaGia))
       {
          setTipoConsultaGia(new DomnTipoConsulta());
       }
       return tipoConsultaGia;
    }

    public void setServidorSefazIntegracaoVo(ServidorSefazIntegracaoVo servidorSefazIntegracaoVo)
    {
       this.servidorSefazIntegracaoVo = servidorSefazIntegracaoVo;
    }

    public ServidorSefazIntegracaoVo getServidorSefazIntegracaoVo()
    {
       if(!Validador.isObjetoValido(servidorSefazIntegracaoVo))
       {
          setServidorSefazIntegracaoVo(new ServidorSefazIntegracaoVo());
       }
       return servidorSefazIntegracaoVo;
    }

    public void setJustificativaAlteracao(String justificativaAlteracao)
    {
       this.justificativaAlteracao = justificativaAlteracao;
    }

    @AnotacaoAtributo
    (
        nomeColuna = CamposGIAITCD.CAMPO_JUSTIFICATIVA_ALTERACAO
        ,obrigatorio = false
    )
    public String getJustificativaAlteracao()
    {
       if(!Validador.isStringValida(justificativaAlteracao))
       {
          return STRING_VAZIA;
       }
       return justificativaAlteracao;
    }

    public void setImprimirDar(boolean imprimirDar)
    {
       this.imprimirDar = imprimirDar;
    }

    public boolean isImprimirDar()
    {
       return imprimirDar;
    }

    public void setAgenciaAvaliacao(UnidadeSefazIntegracaoVo agenciaAvaliacao)
    {
       this.agenciaAvaliacao = agenciaAvaliacao;
    }

    public UnidadeSefazIntegracaoVo getAgenciaAvaliacao()
    {
       if(!Validador.isObjetoValido(agenciaAvaliacao))
       {
          setAgenciaAvaliacao(new UnidadeSefazIntegracaoVo());
       }
       return agenciaAvaliacao;
    }

    public void setGiaRetificada(boolean giaRetificada)
    {
       this.giaRetificada = giaRetificada;
    }

    public boolean isGiaRetificada()
    {
       return giaRetificada;
    }
    
    public int getQuantidadeCasasDecimais()
    {
       return 2;
    }

    public void setStatusAnterior(StatusGIAITCDVo statusAnterior)
    {
       this.statusAnterior = statusAnterior;
    }

    public StatusGIAITCDVo getStatusAnterior()
    {
       if(!Validador.isObjetoValido(statusAnterior))
       {
          setStatusAnterior(new StatusGIAITCDVo());
       }
       return statusAnterior;
    }

    public void setValorTotalInformadoBensDeclarados(double valorTotalInformadoBensDeclarados)
    {
       this.valorTotalInformadoBensDeclarados = valorTotalInformadoBensDeclarados;
    }

    public double getValorTotalInformadoBensDeclarados()
    {
       return valorTotalInformadoBensDeclarados;
    }
    
    public String getValorTotalInformadoBensDeclaradosFormatado()
    {
       return StringUtil.doubleToMonetario(getValorTotalInformadoBensDeclarados());
    }
    
    public void ordenaGiasPorCodigoDecrescente()
    {
       if(Validador.isCollectionValida(getCollVO()))
       {
          Collections.<GIAITCDVo>sort
          (
             (List<GIAITCDVo>) getCollVO()
             , new Comparator<GIAITCDVo>()
             {
                   public int compare(GIAITCDVo o1, GIAITCDVo o2)
                   {
                      return new Long(o2.getCodigo()).compareTo(o1.getCodigo());
                   }
             }
          );
       }
    }

    public void setGiaAvaliada(boolean giaAvaliada)
    {
       this.giaAvaliada = giaAvaliada;
    }

    public boolean isGiaAvaliada()
    {
       return giaAvaliada;
    }

    public void setGiaParcelada(boolean giaParcelada)
    {
       this.giaParcelada = giaParcelada;
    }

    public boolean isGiaParcelada()
    {
       return giaParcelada;
    }

    public void setValorITCDRetificado(double valorITCDRetificado)
    {
       this.valorITCDRetificado = valorITCDRetificado;
    }

    @AnotacaoAtributo
    (
        nomeColuna = CamposGIAITCD.CAMPO_VALOR_ITCD_RETIFICADO
        ,obrigatorio = false
    )
    public double getValorITCDRetificado()
    {
       return valorITCDRetificado;
    }

    public void setValorITCDOriginal(double valorITCDOriginal)
    {
       this.valorITCDOriginal = valorITCDOriginal;
    }

    public double getValorITCDOriginal()
    {
       return valorITCDOriginal;
    }
    
    public void setValorTotalArbitrado(double valorTotalArbitrado){
      this.valorTotalArbitrado = valorTotalArbitrado;
    }
    
    public String getValorTotalArbitradoFormatado(){
      return StringUtil.doubleToMonetario(valorTotalArbitrado);
    }

    public void setReabrirAvaliacao(boolean reabrirExcluirAvaliacao)
    {
       this.reabrirAvaliacao = reabrirExcluirAvaliacao;
    }

    public boolean isReabrirAvaliacao()
    {
       return reabrirAvaliacao;
    }


    public void setExcluirAvaliacao(boolean excluirAvaliacao)
    {
       this.excluirAvaliacao = excluirAvaliacao;
    }

    public boolean isExcluirAvaliacao()
    {
       return excluirAvaliacao;
    }


    public void setStatusUltimaAvaliacao(DomnAtivoInativo statusUltimaAvaliacao)
    {
       this.statusUltimaAvaliacao = statusUltimaAvaliacao;
    }

    public DomnAtivoInativo getStatusUltimaAvaliacao()
    {
       return statusUltimaAvaliacao;
    }
    
    public void setUfIntegracaoVO(UFIntegracaoVO ufIntegracaoVO)
    {
       this.ufIntegracaoVO = ufIntegracaoVO;
    }

    public UFIntegracaoVO getUfIntegracaoVO()
    {
       if(!Validador.isObjetoValido( this.ufIntegracaoVO ))
       {
          ufIntegracaoVO = new UFIntegracaoVO();
       }
       return ufIntegracaoVO;
    }
    
    @AnotacaoAtributo
    (
        nomeColuna = CamposGIAITCD.CAMPO_NUMERO_VERSAO_GIAITCD
        ,obrigatorio = false
    )
    public DomnVersaoGIAITCD getNumeroVersaoGIAITCD()
    {
       if (!Validador.isDominioNumericoValido(numeroVersaoGIAITCD))
       {
          setNumeroVersaoGIAITCD( new DomnVersaoGIAITCD(-1));       
       }
       return numeroVersaoGIAITCD;
    }

    public void setNumeroVersaoGIAITCD(DomnVersaoGIAITCD versaoGIAITCD)
    {
       this.numeroVersaoGIAITCD = versaoGIAITCD;
    }
    
    @AnotacaoAtributo
    (
        nomeColuna = CamposGIAITCD.CAMPO_TIPO_PROTOCOLO_GIA
        ,obrigatorio = false
    )
    public DomnTipoProtocolo getTipoProtocoloGIA()
    {
       if(!Validador.isDominioNumericoValido(tipoProtocoloGIA))
       {
          setTipoProtocoloGIA(new DomnTipoProtocolo(-1));
       }
       return tipoProtocoloGIA;
    }  

    public void setTipoProtocoloGIA(DomnTipoProtocolo tipoProtocoloGIA)
    {
       this.tipoProtocoloGIA = tipoProtocoloGIA;
    }


    public void setAlterarParaPendenteProtocolo(DomnSimNao alterarParaPendenteProtocolo)
    {
       this.alterarParaPendenteProtocolo = alterarParaPendenteProtocolo;
    }

    public DomnSimNao getAlterarParaPendenteProtocolo()
    {
       if (!Validador.isDominioNumericoValido(alterarParaPendenteProtocolo))
       {
          setAlterarParaPendenteProtocolo(new DomnSimNao(-1));
       }
       return alterarParaPendenteProtocolo;
    }

    public void setTipoProtocoloGIASelecionadoPeloContribuinte(DomnTipoProtocolo tipoProtocoloGIASelecionadoPeloContribuinte)
    {
       this.tipoProtocoloGIASelecionadoPeloContribuinte = tipoProtocoloGIASelecionadoPeloContribuinte;
    }

    public DomnTipoProtocolo getTipoProtocoloGIASelecionadoPeloContribuinte()
    {
       if(!Validador.isDominioNumericoValido(tipoProtocoloGIASelecionadoPeloContribuinte))
       {
          setTipoProtocoloGIASelecionadoPeloContribuinte(new DomnTipoProtocolo(-1));
       }
       return tipoProtocoloGIASelecionadoPeloContribuinte;
    }

    public void setTipoProtocoloGIAOpcional(boolean tipoProtocoloGIAOpcional)
    {
       this.tipoProtocoloGIAOpcional = tipoProtocoloGIAOpcional;
    }

    public boolean isTipoProtocoloGIAOpcional()
    {
       return tipoProtocoloGIAOpcional;
    }
    
    public String getExibirStatusProtocolo()
    {
       EprocessBe processoBe = null;
       try
       {
          processoBe = new EprocessBe();
          String msg = processoBe.exibirStatusProcessoGiaEprocess(getCodigo());
          if (getTipoProtocoloGIA().is(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO))
          {
             if (msg.equalsIgnoreCase("Pendente"))
             {
                return msg;
             }
             else if (msg.equalsIgnoreCase("Protocolo não validado"))
             {
                return msg;
             }
             else if(msg.equalsIgnoreCase("Protocolado"))
             {// matches("\\d+/\\d{4}"))
                return msg;
             }
          }
       }
       catch (Exception e)
       {
          e.printStackTrace();
       }
       finally
       {
          if(processoBe != null)
          {
             processoBe.close();
             processoBe = null;
             System.out.println("CLOSE CONEXÃO E-PROCESS - getExibirStatusProtocolo");
          }
       }
       return STRING_VAZIA;
    }
    
    
    public String getNumeroProcessoEprocess()
    {
       String numero = STRING_VAZIA;
       EprocessBe processoBe = null;
       try
       {
          processoBe = new EprocessBe();
          numero = processoBe.getNumeroProcessoEprocess(getCodigo());
       }
       catch (Exception e)
       {
          e.printStackTrace();
       }
       finally
       {
          if(processoBe != null)
          {
             processoBe.close();
             processoBe = null;
             System.out.println("CLOSE CONEXÃO E-PROCESS - getNumeroProcessoEprocess");
          }
       }
       return numero;
    }
    
    
    
    public void setSituacaoProcessamento(DomnSituacaoProcessamento situacaoProcessamento)
    {
       this.situacaoProcessamento = situacaoProcessamento;
    }

    /**
    * Status da gia gerado pelo serviço ProtocolarGerarDarAvaliacaoAutomatico
    * @return String
    * @implemented by Dherkyan Ribeio Silva
    */
    public DomnSituacaoProcessamento getSituacaoProcessamento()
    {
       return situacaoProcessamento;
    }

    public void setDescricaoMensagemSituacaoErrro(String descricaoMensagemSituacaoErrro)
    {
       this.descricaoMensagemSituacaoErrro = descricaoMensagemSituacaoErrro;
    }

    
    /**
    * Representa a mensagem de erro
    * caso ocorra algum erro durante o processamento
    * da gia pelo serviço ProtocolarGerarDarAvaliacaoAutomatico
    * @return String
    * @implemented by Dherkyan Ribeio Silva
    */
    public String getDescricaoMensagemSituacaoErrro()
    {
       return descricaoMensagemSituacaoErrro;
    }
    
    public double getTotalSomaValoresArbitradoAvaliacao(){
       double valorSomaArbitrado = 0.0;
       Iterator iterator = getBemTributavelVo().getCollVO().iterator();             
       
       while(iterator.hasNext()){
          BemTributavelVo BemVo = (BemTributavelVo) iterator.next();           
          
          if (isGiaAvaliada()){         
             valorSomaArbitrado += BemVo.getAvaliacaoBemTributavelVo().getValorAvaliacao();            
          }else{
             valorSomaArbitrado += BemVo.getValorMercado();
          }
       }
       return valorSomaArbitrado;
    }   
    
    public double getTotalSomaValoresConcordado(){
       double valorSomaConcordado = 0.0;
       
       Iterator iterator = getBemTributavelVo().getCollVO().iterator(); 
       
       while(iterator.hasNext()){
          BemTributavelVo BemVo = (BemTributavelVo) iterator.next(); 
          
          if(BemVo.getConcordaComValorArbitrado().getTextoCorrente() == "SIM"){
          
            if(isGiaAvaliada()){
               valorSomaConcordado += BemVo.getValorArbitradoAux();
            }else{
               valorSomaConcordado += BemVo.getValorMercado();
            }            
             
          }else{
             valorSomaConcordado += BemVo.getValorInformadoContribuinte();
          }                  
       }
       return valorSomaConcordado;
    }
    
    public double getCalculoPercentualValoresArbitradoConcordado(){
       double valorSomaConcordado = getTotalSomaValoresConcordado();
       double valorSomaArbitrado = getTotalSomaValoresArbitradoAvaliacao(); 
       double porcentagem = 0.0;     
       
       if(valorSomaConcordado == 0 && valorSomaArbitrado == 0){
       
         return 0.0;
         
       } else{
          porcentagem = ((valorSomaConcordado/valorSomaArbitrado) *100);    
          
          return porcentagem;
       }      
       
    }
    
    public String getTotalSomaValoresArbitradoAvaliacaoFormatado(){
          double valorSomaArbitrado = getTotalSomaValoresArbitradoAvaliacao();         
          
          return StringUtil.doubleToMonetario(valorSomaArbitrado);
    }
    
    public String getTotalSomaValoresConcordadoFormatado(){
          double valorSomaConcordado = getTotalSomaValoresConcordado();
                   
          return StringUtil.doubleToMonetario(valorSomaConcordado);
    }    
    
    public String getCalculoPercentualValoresArbitradoConcordadoFormatado(){
       double valorSomaConcordado = getTotalSomaValoresConcordado();
       double valorSomaArbitrado = getTotalSomaValoresArbitradoAvaliacao(); 
       double porcentagem = 0.0;     
       
       if(valorSomaConcordado == 0 && valorSomaArbitrado == 0){
         return "0";
       }
       else{
          porcentagem = ((valorSomaConcordado/valorSomaArbitrado) *100);  
                
          String valor = String.format("%05.2f", porcentagem);
          
          return valor;
       }      
    }  
    
    public Double getPorcentagemAconsiderar(){     
      return porcentagemAconsiderar;
    }      
    
    public void setPorcentagemAconsiderar(Double porcentagem){
      this.porcentagemAconsiderar = porcentagem;    
    }
    
    public void setExibirIsencaoBemTributavel(Boolean exibir){
       this.exibirIsencaoBemTributavel = exibir;
    }
    
    public boolean getExibirIsencaoBemTributavel(){
      this.setExibirIsencaoBemTributavel(false);
        if(Validador.isDataValida(this.dataLimiteExibirIsencaoBemTributavel)){
            for(StatusGIAITCDVo status : getStatusVo().getCollVO()){
            
            if (((status.getStatusGIAITCD().getValorCorrente() == DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO) && (status.getDataAtualizacao().before(this.dataLimiteExibirIsencaoBemTributavel))) || 
                  ((status.getStatusGIAITCD().getValorCorrente() == DomnStatusGIAITCD.PROTOCOLADO) && (status.getDataAtualizacao().before(this.dataLimiteExibirIsencaoBemTributavel)))){
                  this.setExibirIsencaoBemTributavel(true);
               }            
            } 
          }
       return this.exibirIsencaoBemTributavel;
    }
    
    public void setDataLimiteExibirIsencaoBemTributavel(Date dataLimite){
       this.dataLimiteExibirIsencaoBemTributavel = dataLimite;
    }
    
    public void setExisteDoacaoSucessivaNaoRegParaBenef(boolean existeDoacaoSucessivaNaoRegParaBenef)
    {
       this.existeDoacaoSucessivaNaoRegParaBenef = existeDoacaoSucessivaNaoRegParaBenef;
    }

    public boolean isExisteDoacaoSucessivaNaoRegParaBenef()
    {
       return existeDoacaoSucessivaNaoRegParaBenef;
    }


   public void setGiaUtilizadaParaDoacaoSucessiva(boolean giaUtilizadaParaDoacaoSucessiva)
   {
      this.giaUtilizadaParaDoacaoSucessiva = giaUtilizadaParaDoacaoSucessiva;
   }

   public boolean getGiaUtilizadaParaDoacaoSucessiva()
   {
      return giaUtilizadaParaDoacaoSucessiva;
   }


   public void setDataIsencaoPrevistaParametroGerencial(Date dataIsencaoPrevistaParametroGerencial)
   {
      this.dataIsencaoPrevistaParametroGerencial = dataIsencaoPrevistaParametroGerencial;
   }

   public Date getDataIsencaoPrevistaParametroGerencial()
   {
      return dataIsencaoPrevistaParametroGerencial;
   }
   
   public boolean getIsGiaDoacao() {
       return this instanceof GIAITCDDoacaoVo;
   }


   public void setGiaIsentaUpf(boolean giaIsentaUpf)
   {
      this.giaIsentaUpf = giaIsentaUpf;
   }

   public boolean isGiaIsentaUpf()
   {
      return giaIsentaUpf;
   }
}
