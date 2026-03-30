 /**
  * Ábaco Tecnologia de Informação - LTDA
  * Arquivo: FichaImovelUrbanoVo.java
  * Revisão: Marlo Einchenberg Motta
  * Data revisão: 27/11/2007
  */
 package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano;

 import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
 import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
 import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
 import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
 import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
 import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
 import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
 import br.com.abaco.util.StringUtil;
 import br.com.abaco.util.Validador;

 import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;import br.gov.mt.sefaz.itc.model.generico.fichaimovel.FichaImovelVo;
 import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.benfeitoria.FichaImovelUrbanoBenfeitoriaVo;
 import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoVo;
 import br.gov.mt.sefaz.itc.model.tabelabasica.iptu.IPTUVo;
 import br.gov.mt.sefaz.itc.model.tabelabasica.iptuprefeitura.IPTUPrefeituraVo;
 import br.gov.mt.sefaz.itc.util.calculo.CalculoITCD;
 import br.gov.mt.sefaz.itc.util.dominio.DomnEstadoConservacao;
 import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
 import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelUrbano;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
 import br.gov.mt.sefaz.itc.util.integracao.cadastro.EnderecoIntegracaoVo;

 import java.util.Collection;


 /**
  * Classe responsável por encapsular os valores do objeto FichaImovelUrbano (Value Object).
  * @author Marlo Eichenberg Motta
  * @author Lucas Nascimento
  * @version $Revision: 1.3 $
  */
  @AnotacaoTabelaClasse
  (
      nomeTabela = TabelasITC.TABELA_IMOVEL_URBANO
      ,nomeAmigavel = "Ficha de Imóvel Urbano"
      ,identificadores =
      {
          @AnotacaoIdentificador
          (
              nomeAtributo = "codigo"
              ,nomeColuna = CamposFichaImovelUrbano.CAMPO_CODIGO_IMOVEL_URBANO
              ,sequencia = @AnotacaoSequencia
              (
                  nomeSequencia = SequencesITC.SEQUENCE_IMOVEL_URBANO
                  ,tipoSequencia = DomnTipoSequencia.SEQUENCE
              )
          )
      }
  )
 public class FichaImovelUrbanoVo extends FichaImovelVo
 {
    private static final long serialVersionUID = Long.MAX_VALUE;   
    private ConstrucaoVo construcaoVo;  
    private DomnEstadoConservacao estadoConservacao;
    private DomnTipoAcesso tipoAcesso;
    private FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo;
    private double quantidadeAreaConstruida;
    private double quantidadeAreaTotal;
    private double valorMercadoTotal;
    private double valorVenalIptu;
    private boolean existeConstrucao;
    private IPTUVo iptuVo;
    private IPTUPrefeituraVo iptuPrefeituraVo;
    //campos novos - 05/2019
    private double valorPercentualTransmitido;
    private double valorInfomado;
    private transient DomnSimNao flagConcordaValorArbitrado;
    
    //campo de informação para validação no jsp 
    private DomnVersaoGIAITCD numeroVersaoGIAITCD; 

    /**
     * Construtor vazio.
     * @implemented by Marlo Eichenberg Motta
     * @implemented by Lucas Nascimento
     */
    public FichaImovelUrbanoVo()
    {
       super();
    }

    /**
     * Construtor padrão.
     * @param codigo Chave primária
     * @implemented by Marlo Eichenberg Motta
     * @implemented by Lucas Nascimento
     */
    public FichaImovelUrbanoVo(long codigo)
    {
       super(codigo);
    }

    /**
     * Construtor que recebe um parametro de consulta.
     * @param fichaImovelUrbanoVo
     * @implemented by Marlo Eichenberg Motta
     * @implemented by Lucas Nascimento
     */
    public FichaImovelUrbanoVo(FichaImovelUrbanoVo fichaImovelUrbanoVo)
    {
       super();
       setParametroConsulta(fichaImovelUrbanoVo);
    }

    /**
     * Construtor que recebe um Collection.
     * @param collVo Coleção de Value Objects.
     * @implemented by Marlo Eichenberg Motta
     * @implemented by Lucas Nascimento
     */
    public FichaImovelUrbanoVo(Collection collVo)
    {
       super(collVo);
    }
    
    public long getCodigo()
    {
       return super.getCodigo();
    }

    /**
     * Atribui a Construção
     * @param construcaoVo
     * @implemented by Lucas Nascimento
     */
    public void setConstrucaoVo(ConstrucaoVo construcaoVo)
    {
       this.construcaoVo = construcaoVo;
    }

    /**
     * Retorna a Contrução
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
                 nomeColuna = CamposFichaImovelUrbano.CAMPO_ITCTB13_CODIGO_CONSTRUCAO
                 , nomeAtributo = "codigo"
             )
         }
     )
    public ConstrucaoVo getConstrucaoVo()
    {
       if (!Validador.isObjetoValido(construcaoVo))
       {
          setConstrucaoVo(new ConstrucaoVo());
       }
       return construcaoVo;
    }

    /**
     * Atribui o Estado de Conservação
     * @param estadoConservacao
     * @implemented by Lucas Nascimento
     */
    public void setEstadoConservacao(DomnEstadoConservacao estadoConservacao)
    {
       this.estadoConservacao = estadoConservacao;
    }

    /**
     * Retorna o Estado de Conservação
     * @return
     * @implemented by Lucas Nascimento
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposFichaImovelUrbano.CAMPO_TIPO_CONSERVACAO
         ,obrigatorio = false
     )
    public DomnEstadoConservacao getEstadoConservacao()
    {
       if (!Validador.isDominioNumericoValido(estadoConservacao))
       {
          setEstadoConservacao(new DomnEstadoConservacao(-1));
       }
       return estadoConservacao;
    }

    /**
     * Atribui o Tipo de Acesso
     * @param tipoAcesso
     * @implemented by Lucas Nascimento
     */
    public void setTipoAcesso(DomnTipoAcesso tipoAcesso)
    {
       this.tipoAcesso = tipoAcesso;
    }

    /**
     * Retorna o Tipo de Acesso
     * @return
     * @implemented by Lucas Nascimento
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposFichaImovelUrbano.CAMPO_TIPO_ACESSO
         ,obrigatorio = true
     )
    public DomnTipoAcesso getTipoAcesso()
    {
       if (!Validador.isObjetoValido(tipoAcesso))
       {
          setTipoAcesso(new DomnTipoAcesso(-1));
       }
       return tipoAcesso;
    }

    /**
     * Atribui a Benfeitoria
     * @param fichaImovelUrbanoBenfeitoriaVo
     * @implemented by Lucas Nascimento
     */
    public void setFichaImovelUrbanoBenfeitoriaVo(FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo)
    {
       this.fichaImovelUrbanoBenfeitoriaVo = fichaImovelUrbanoBenfeitoriaVo;
    }

    /**
     * Retorna a Ficha Imovel Ubano Benfeitoria
     * @return
     * @implemented by Lucas Nascimento
     */
    public FichaImovelUrbanoBenfeitoriaVo getFichaImovelUrbanoBenfeitoriaVo()
    {
       if (!Validador.isObjetoValido(fichaImovelUrbanoBenfeitoriaVo))
       {
          setFichaImovelUrbanoBenfeitoriaVo(new FichaImovelUrbanoBenfeitoriaVo());
       }
       return fichaImovelUrbanoBenfeitoriaVo;
    }

    /**
     * Atribui a Area Construida
     * @param quantidadeAreaConstruida
     * @implemented by Lucas Nascimento
     */
    public void setQuantidadeAreaConstruida(double quantidadeAreaConstruida)
    {
       this.quantidadeAreaConstruida = quantidadeAreaConstruida;
    }

    /**
     * Retorna a Quantidade de Area Contruida
     * @return
     * @implemented by Lucas Nascimento
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposFichaImovelUrbano.CAMPO_QUANTIDADE_AREA_CONSTRUIDA
         ,obrigatorio = false
     )
    public double getQuantidadeAreaConstruida()
    {
       return quantidadeAreaConstruida;
    }

    /**
     * Retorna a Area Contruida Formatada
     * @return
     * @implemented by Lucas Nascimento
     */
    public String getQuantidadeAreaConstruidaFormatado()
    {
       if (!Validador.isNumericoValido(getQuantidadeAreaConstruida()))
       {
          return STRING_VAZIA;
       }
       return StringUtil.doubleToMonetario(getQuantidadeAreaConstruida(),4);
    }

    /**
     * Atribui a Area Total Construida
     * @param quantidadeAreaTotal
     * @implemented by Lucas Nascimento
     */
    public void setQuantidadeAreaTotal(double quantidadeAreaTotal)
    {
       this.quantidadeAreaTotal = quantidadeAreaTotal;
    }

    /**
     * Retorna a quantidade total da Area
     * @return
     * @implemented by Lucas Nascimento
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposFichaImovelUrbano.CAMPO_QUANTIDADE_AREA_TOTAL
         ,obrigatorio = true
     )
    public double getQuantidadeAreaTotal()
    {
       return quantidadeAreaTotal;
    }

    /**
     * Retorna a quantidade de area toral construida formatada
     * @return
     * @implemented by Lucas Nascimento
     */
    public String getQuantidadeAreaTotalFormatado()
    {
       if (!Validador.isNumericoValido(getQuantidadeAreaTotal()))
       {
          return STRING_VAZIA;
       }
       return StringUtil.doubleToMonetario(getQuantidadeAreaTotal(),4);
    }

    /**
     * Atribui o valor total de mercado
     * @param valorMercadoTotal
     * @implemented by Lucas Nascimento
     */
    public void setValorMercadoTotal(double valorMercadoTotal)
    {
       this.valorMercadoTotal = valorMercadoTotal;
    }

    /**
     * Retorna o valor total de mercado
     * @return
     * @implemented by Lucas Nascimento
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposFichaImovelUrbano.CAMPO_VALOR_MERCADO_TOTAL
         ,obrigatorio = true
     )
    public double getValorMercadoTotal()
    {
       return valorMercadoTotal;
    }

    /**
     * Método que retorna o valor de mercado formatado
     * @return
     * @implemented by Anderson Boehler Iglesias Araujo
     */
    public String getValorMercadoTotalFormatado()
    {
       if (!Validador.isNumericoValido(getValorMercadoTotal()))
       {
          return STRING_VAZIA;
       }
       return StringUtil.doubleToMonetario(getValorMercadoTotal());
    }

    /**
     * Atribui o valor venal do iptu
     * @param valorVenalIptu
     * @implemented by Lucas Nascimento
     */
    public void setValorVenalIptu(double valorVenalIptu)
    {
       this.valorVenalIptu = valorVenalIptu;
    }

    /**
     * Retorna o valor venal do iptu
     * @return
     * @implemented by Lucas Nascimento
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposFichaImovelUrbano.CAMPO_VALOR_VENAL_IPTU
         ,obrigatorio = false
     )
    public double getValorVenalIptu()
    {
       return valorVenalIptu;
    }

    /**
     * Retorna o valor venal do iptu formatado
     * @return
     * @implemented by Lucas Nascimento
     */
    public String getValorVenalIptuFormatado()
    {
       if (!Validador.isNumericoValido(getValorVenalIptu()))
       {
          return STRING_VAZIA;
       }
       return StringUtil.doubleToMonetario(getValorVenalIptu());
    }

    /**
     * Retorna o maior valor entre o Valor Total de Mercado e o Valor Venal de Iptu
     * @return
     * @implemented by Lucas Nascimento
     */
    public double getValorTributavel()      
    {
       //return (getValorMercadoTotal() > getValorVenalIptu()) ? getValorMercadoTotal() : getValorVenalIptu();
       return CalculoITCD.calcularValorTributavelImovelUrbano(this);
    }

    /**
     * Retorna o Valor Tributavel Formatado
     * @return
     * @implemented by Daniel Balieiro
     */
    public String getValorTributavelFormatado()
    {
       if (!Validador.isNumericoValido(getValorTributavel()))
       {
          return STRING_VAZIA;
       }
       return StringUtil.doubleToMonetario(getValorTributavel());
    }

    public void setExisteConstrucao(boolean existeConstrucao)
    {
       this.existeConstrucao = existeConstrucao;
    }

    public boolean isExisteConstrucao()
    {
       return existeConstrucao;
    }

    @AnotacaoAtributoExterno
    (
        obrigatorio = true
        ,colunaReferencia =
        {
            @AnotacaoColunaExterna
            (
                nomeColuna = CamposFichaImovelUrbano.CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRBT
                , nomeAtributo = "codigo"
            )
        }
    )
    public BemTributavelVo getBemTributavelVo()
    {
       return super.getBemTributavelVo();
    }

    @AnotacaoAtributoExterno
    (
        obrigatorio = true
        ,colunaReferencia =
        {
            @AnotacaoColunaExterna
            (
                nomeColuna = CamposFichaImovelUrbano.CAMPO_ACCTB06_CODIGO_ENDERECO
                , nomeAtributo = "codgEndereco"
            )
        }
    )
    public EnderecoIntegracaoVo getEnderecoIntegracaoVo()
    {
       return super.getEnderecoVo();
    }

    public void setIptuVo(IPTUVo iptuVo)
    {
       this.iptuVo = iptuVo;
    }

    @AnotacaoAtributoExterno
    (
        obrigatorio = false
        ,colunaReferencia =
        {
            @AnotacaoColunaExterna
            (
                nomeColuna = CamposFichaImovelUrbano.CAMPO_ITCTB54_IPTU
                , nomeAtributo = "codigo"
            )
        }
    )
    public IPTUVo getIptuVo()
    {
       if (!Validador.isObjetoValido(iptuVo))
       {
          setIptuVo(new IPTUVo());
       }
       return iptuVo;
    }

    public void setIptuPrefeituraVo(IPTUPrefeituraVo iptuPrefeituraVo)
    {
       this.iptuPrefeituraVo = iptuPrefeituraVo;
    }
    
    @AnotacaoAtributoExterno
    (
        obrigatorio = false
        ,colunaReferencia =
        {
            @AnotacaoColunaExterna
            (
                nomeColuna = CamposFichaImovelUrbano.CAMPO_ITCTB57_IPTU_PREFEITURA
                , nomeAtributo = "codigo"
            )
        }
    )
    public IPTUPrefeituraVo getIptuPrefeituraVo()
    {
       if (!Validador.isObjetoValido(iptuPrefeituraVo))
       {
          setIptuPrefeituraVo(new IPTUPrefeituraVo());
       }
       return iptuPrefeituraVo;
    }

    /**
     * @param valorPercentualTransmitido
     */
    public void setValorPercentualTransmitido(double valorPercentualTransmitido)
    {
       this.valorPercentualTransmitido = valorPercentualTransmitido;
    }

    /**
     * @return
     */

    public double getValorPercentualTransmitido()
    {
         return valorPercentualTransmitido;
    }
    
    public String getValorPercentualTransmitidoFormatado()
    {
       if (!Validador.isNumericoValido(getValorPercentualTransmitido()))
       {
          return STRING_VAZIA;
       }
       return StringUtil.doubleToMonetario(getValorPercentualTransmitido(), 0);
    }

    /**
     * @param valorTotal
     */
    public void setValorInfomado(double valorTotal)
    {
       this.valorInfomado = valorTotal;
    }

    /**
     * @return
     */

    public double getValorInfomado()
    {
       return valorInfomado;
    }
    
  
    public String getValorTotalFormatado()
    {
       if (!Validador.isNumericoValido(getValorInfomado()))
       {
          return STRING_VAZIA;
       }
       return StringUtil.doubleToMonetario(getValorInfomado());
    }

    public void setFlagConcordaValorArbitrado(DomnSimNao flagConcordaValorArbitrado)
    {
       this.flagConcordaValorArbitrado = flagConcordaValorArbitrado;
    }

    public DomnSimNao getFlagConcordaValorArbitrado()
    {
       return flagConcordaValorArbitrado;
    }
    
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
    
 }
