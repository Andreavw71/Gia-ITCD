 /**
  * Ábaco Tecnologia de Informação - LTDA
  * Arquivo: VeiculoVo.java
  * Revisão: Douglas Magalhaes
  * Revisão: Dherkyan Ribeiro
  * Data revisão: 14/11/2015
  */
 package br.gov.mt.sefaz.itc.model.giaitcd.fichabem.fichaveiculo;

 import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
 import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
 import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
 import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
 import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
 import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
 import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
 import br.com.abaco.util.StringUtil;
 import br.com.abaco.util.Validador;

 import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
 import br.gov.mt.sefaz.itc.model.generico.ficha.AbstractFichaVo;
 import br.gov.mt.sefaz.itc.util.calculo.CalculoITCD;
 import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaVeiculo;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
 import br.gov.mt.sefaz.itc.util.integracao.ipva.CategoriaIntegracaoVo;
 import br.gov.mt.sefaz.itc.util.integracao.ipva.MarcaIntegracaoVo;
 import br.gov.mt.sefaz.itc.util.integracao.ipva.MarcaModeloIntegracaoVo;

 import java.util.Collection;
 import java.util.Date;
 import java.util.Vector;

 /**
  * Classe responsável por encapsular os valores do objeto VeiculoVo (Value Object).
  * @author Douglas Magalhaes
  * @author Dherkyan Ribeiro
  * @version $Revision: 1.2 $
  */
 @AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_VEICULO, nomeAmigavel = "Veículo", identificadores =
       { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = CamposFichaVeiculo.CAMPO_CODIGO_VEICULO, sequencia = 
                @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_VEICULO, tipoSequencia = DomnTipoSequencia.SEQUENCE)
          )
          } )
 public class FichaVeiculoVo extends AbstractFichaVo<FichaVeiculoVo>
 {
    private static final long serialVersionUID = Long.MAX_VALUE;
    private String numrChassi;
    private String numrPlaca;
    private long numrRenavam;
    /* A lista de marcas deve ser trabalhada neste objeto para NÃO serem inseridas no XML*/
    //private transient MarcaModeloIntegracaoVo marcaModeloCollVo;
    private MarcaModeloIntegracaoVo marcaModelo;
    private CategoriaIntegracaoVo categoria;
    /* A lista de marcas deve ser trabalhada neste objeto para NÃO serem inseridas no XML*/
    private transient MarcaIntegracaoVo marcaCollVo;
    private MarcaIntegracaoVo marcaVo;
    private int anoLicenciamento;
    private int anoFabricacao;
    private int anoReferencia;
    private String  descricaoMarcaModelo;
    private double valorVenal = 0d;
    private String nomeProprietario;
    private double valorInformado = 0d;
    private String siglaUfVeiculo;
    private DomnSimNao isencaoPrevista;
    private double valorTributavel;
    private Vector listaModeloVeiculo;
    private Date dataAtualizacaoBD;
    private transient boolean existeValorVeiculo = true;
    private transient DomnSimNao flagConcordaValorArbitrado;
    

    /**
     * Construtor vazio.
     * @implemented by Douglas Magalhaes
     * @implemented by Dherkyan Ribeiro
     */
    public FichaVeiculoVo()
    {
       super();
    }

    /**
     * Construtor padrão.
     * @param codigo Chave primária
     * @implemented by Douglas Magalhaes
     * @implemented by Dherkyan Ribeiro
     */
    public FichaVeiculoVo(long codigo)
    {
       super(codigo);
    }

    /**
     * Construtor que recebe um parametro de consulta.
     * @param veiculoVo
     * @implemented by Douglas Magalhaes
     * @implemented by Dherkyan Ribeiro
     */
    public FichaVeiculoVo(FichaVeiculoVo veiculoVo)
    {
       super();
       setParametroConsulta(veiculoVo);
    }

    /**
     * Construtor que recebe um Collection.
     * @param collVo Coleção de Value Objects.
     * @implemented by Douglas Magalhaes
     * @implemented by Dherkyan Ribeiro
     */
    public FichaVeiculoVo(Collection collVo)
    {
       super(collVo);
    }

    public long getCodigo()
    {
       return super.getCodigo();
    }

    public void setNumrChassi(String numrChassi)
    {
       this.numrChassi = numrChassi;
    }

    public String getNumrChassi()
    {
       return numrChassi;
    }

    public void setNumrPlaca(String numrPlaca)
    {
       this.numrPlaca = numrPlaca;
    }

    /**
     * @return
     * @implemented by Douglas Magalhaes
     * @implemented by Dherkyan Ribeiro
     */
    @AnotacaoAtributo(nomeColuna = CamposFichaVeiculo.CAMPO_NUMERO_PLACA, obrigatorio = false)
    public String getNumrPlaca()
    {
       return numrPlaca;
    }

    public void setNumrRenavam(long numrRenavam)
    {
       this.numrRenavam = numrRenavam;
    }

    /**
     * @return
     * @implemented by Douglas Magalhaes
     * @implemented by Dherkyan Ribeiro
     */
    @AnotacaoAtributo(nomeColuna = CamposFichaVeiculo.CAMPO_NUMERO_RENAVAM, obrigatorio = false)
    public long getNumrRenavam()
    {
       return numrRenavam;
    }

    public String getValorRenavanFormatado()
    {
       if (!Validador.isNumericoValido(this.numrRenavam))
       {
          return STRING_VAZIA;
       }
       return String.valueOf(this.numrRenavam);
    }

    public void setAnoLicenciamento(int anoLicenciamento)
    {
       this.anoLicenciamento = anoLicenciamento;
    }

    public int getAnoLicenciamento()
    {
       return anoLicenciamento;
    }

    public void setAnoFabricacao(int anoFabricacao)
    {
       this.anoFabricacao = anoFabricacao;
    }

    /**
     * @return
     * @implemented by Douglas Magalhaes
     * @implemented by Dherkyan Ribeiro
     */
    @AnotacaoAtributo(nomeColuna = CamposFichaVeiculo.CAMPO_ANO_FABRICACAO, obrigatorio = false)
    public int getAnoFabricacao()
    {
       return anoFabricacao;
    }

    public void setValorVenal(double valorVenal)
    {
       this.valorVenal = valorVenal;
    }

    /**
     * @return
     * @implemented by Douglas Magalhaes
     * @implemented by Dherkyan Ribeiro
     */
    @AnotacaoAtributo(nomeColuna = CamposFichaVeiculo.CAMPO_VALOR_VENAL, obrigatorio = false)
    public double getValorVenal()
    {
       return valorVenal;
    }

    public void setNomeProprietario(String nomeProprietario)
    {
       this.nomeProprietario = nomeProprietario;
    }

    /**
     * @return
     * @implemented by Douglas Magalhaes
     * @implemented by Dherkyan Ribeiro
     */
    @AnotacaoAtributo(nomeColuna = CamposFichaVeiculo.CAMPO_NOME_PROPRIETARIO, obrigatorio = false)
    public String getNomeProprietario()
    {
       return nomeProprietario;
    }

    public void setValorInformado(double valorInformado)
    {
       this.valorInformado = valorInformado;
    }

    /**
     * @return
     * @implemented by Douglas Magalhaes
     * @implemented by Dherkyan Ribeiro
     */
    @AnotacaoAtributo(nomeColuna = CamposFichaVeiculo.CAMPO_VALOR_INFORMADO, obrigatorio = false)
    public double getValorInformado()
    {
       return valorInformado;
    }

    public String getValorInformadoFormatado()
    {
       if (!Validador.isNumericoValido(this.valorInformado))
       {
          return STRING_VAZIA;
       }
       return StringUtil.doubleToMonetario(this.valorInformado);
    }

    public void setSiglaUfVeiculo(String siglaUfVeiculo)
    {
       this.siglaUfVeiculo = siglaUfVeiculo;
    }

    /**
     * @return
     * @implemented by Douglas Magalhaes
     * @implemented by Dherkyan Ribeiro
     */
    @AnotacaoAtributo(nomeColuna = CamposFichaVeiculo.CAMPO_SIGLA_UF_VEICULO, obrigatorio = false)
    public String getSiglaUfVeiculo()
    {
       return siglaUfVeiculo;
    }

    public void setBemTributavelVo(BemTributavelVo bemTributavelVo)
    {
       super.setBemTributavelVo( bemTributavelVo);
    }

    /**
     * @implemented by Douglas Magalhaes
     * @implemented by Dherkyan Ribeiro
     */
    @AnotacaoAtributoExterno(obrigatorio = true, colunaReferencia =
          { @AnotacaoColunaExterna(nomeColuna = CamposFichaVeiculo.CAMPO_ITCTB18_CODIGO_BEM_TRIBUTAVEL, nomeAtributo = "codigo")
             } )
    public BemTributavelVo getBemTributavelVo()
    {
       return super.getBemTributavelVo();
    }

    public void setAnoReferencia(int anoReferencia)
    {
       this.anoReferencia = anoReferencia;
    }

    /**
     * @return
     * @implemented by Douglas Magalhaes
     * @implemented by Dherkyan Ribeiro
     */
    @AnotacaoAtributo(nomeColuna = CamposFichaVeiculo.CAMPO_ANO_REFERENCIA, obrigatorio = false)
    public int getAnoReferencia()
    {
       return anoReferencia;
    }

    public String getValorVenalFormatado()
    {
       String retorno = STRING_VAZIA;
       if (Validador.isNumericoValido(getValorVenal()))
       {
          return StringUtil.doubleToMonetario(getValorVenal());
       }
       return retorno;
    }

    public String getValorvInformadoFormatado()
    {
       String retorno = STRING_VAZIA;
       if (Validador.isNumericoValido(getValorInformado()))
       {
          return StringUtil.doubleToMonetario(getValorInformado());
       }
       return retorno;
    }

    public void setValorTributavel(double valorTributavel)
    {
       this.valorTributavel = valorTributavel;
    }

    public double getValorTributavel()
    {
       return CalculoITCD.calcularValorTributavelVeiculo(this);
    }

    public void setIsencaoPrevista(DomnSimNao isencaoPrevista)
    {
       this.isencaoPrevista = isencaoPrevista;
    }

    public DomnSimNao getIsencaoPrevista()
    {
       return isencaoPrevista;
    }

    public void setMarcaModelo(MarcaModeloIntegracaoVo marcaModelo)
    {
       this.marcaModelo = marcaModelo;
    }

    @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
          { @AnotacaoColunaExterna(nomeColuna = CamposFichaVeiculo.CAMPO_DESCRICAO_MARCA_MODELO, nomeAtributo = "descMarcaModelo")
             } )
    public MarcaModeloIntegracaoVo getMarcaModelo()
    {
       if (!Validador.isObjetoValido(marcaModelo))
       {
          setMarcaModelo(new MarcaModeloIntegracaoVo());
       }
       return marcaModelo;
    }

    public void setCategoria(CategoriaIntegracaoVo categoria)
    {
       this.categoria = categoria;
    }

    public CategoriaIntegracaoVo getCategoria()
    {
       if (!Validador.isObjetoValido(categoria))
       {
          setCategoria(new CategoriaIntegracaoVo());
       }
       return categoria;
    }

    public void setListaModeloVeiculo(Vector listaModeloVeiculo)
    {
       this.listaModeloVeiculo = listaModeloVeiculo;
    }

    public Vector getListaModeloVeiculo()
    {
       return listaModeloVeiculo;
    }

    //@AnotacaoAtributo(nomeColuna = CamposVeiculo.CAMPO_DATA_ATUALIZACAO_BD, obrigatorio = false)
    public Date getDataAtualizacaoBD()
    {
       return dataAtualizacaoBD;
    }

    public void setDataAtualizacaoBD(Date dataAtualizacaoBD)
    {
       this.dataAtualizacaoBD = dataAtualizacaoBD;
    }

   
 /*
    public Double get_valorTributavel()
    {
       return valorTributavel;
    }
 */
    public void setMarcaVo(MarcaIntegracaoVo marcaVo)
    {
       this.marcaVo = marcaVo;
    }

    public MarcaIntegracaoVo getMarcaVo()
    {
       if (!Validador.isObjetoValido(marcaVo))
       {
          setMarcaVo(new MarcaIntegracaoVo());
       }
       return marcaVo;
    }

    public void setMarcaCollVo(MarcaIntegracaoVo marcaCollVo)
    {
       this.marcaCollVo = marcaCollVo;
    }

    public MarcaIntegracaoVo getMarcaCollVo()
    {  
       if (!Validador.isObjetoValido(marcaCollVo))
       {
          setMarcaCollVo(new MarcaIntegracaoVo());
       }
       return marcaCollVo;
    }
 /*
    public void setMarcaModeloCollVo(MarcaModeloIntegracaoVo marcaModeloCollVo)
    {
       this.marcaModeloCollVo = marcaModeloCollVo;
    }

    public MarcaModeloIntegracaoVo getMarcaModeloCollVo()
    {
       if (!Validador.isObjetoValido(marcaModeloCollVo))
       {
          setMarcaModeloCollVo(new MarcaModeloIntegracaoVo());
       }
       return marcaModeloCollVo;
    }
    */

    public double get_valorTributavel()
    {
       return valorTributavel;
    }

    public void setExisteValorVeiculo(boolean existeValorVeiculo)
    {
       this.existeValorVeiculo = existeValorVeiculo;
    }

    public boolean isExisteValorVeiculo()
    {
       return existeValorVeiculo;
    }

    public void setFlagConcordaValorArbitrado(DomnSimNao flagConcordaValorArbitrado)
    {
       this.flagConcordaValorArbitrado = flagConcordaValorArbitrado;
    }

    public DomnSimNao getFlagConcordaValorArbitrado()
    {
       return flagConcordaValorArbitrado;
    }
 }
