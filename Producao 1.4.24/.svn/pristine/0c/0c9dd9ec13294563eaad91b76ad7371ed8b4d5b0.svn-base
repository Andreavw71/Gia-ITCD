 /**
  * Ábaco Tecnologia de Informação - LTDA
  * Arquivo: AvaliacaoBemTributavelVo.java
  * Revisão: Leandro Dorileo
  * Data revisão: 20/03/2008
  * $Id: AvaliacaoBemTributavelVo.java,v 1.1.1.1 2008/05/28 17:55:04 lucas.nascimento Exp $
  */
 package br.gov.mt.sefaz.itc.model.generico.avaliacao;

 import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
 import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
 import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
 import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
 import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
 import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
 import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
 import br.com.abaco.util.StringUtil;
 import br.com.abaco.util.Validador;

 import br.gov.mt.sefaz.itc.model.generico.avaliacao.servidor.AvaliacaoBemTributavelServidorVo;
 import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
 import br.gov.mt.sefaz.itc.util.EntidadeVo;
 import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
 import br.gov.mt.sefaz.itc.util.dominio.DomnAvaliacaoTipo;
 import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
 import br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsultaAvaliacao;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposAvaliacaoBemtributavel;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
 import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.UnidadeSefazIntegracaoVo;

 import java.util.Collection;
 import java.util.Date;

 import sefaz.mt.util.SefazDataHora;


 /**
  * Classe de representação da tabela de Avaliação de Bem Tributavel
  *
  * @author Daniel Balieiro
  * @version $Revision: 1.1.1.1 $
  */
  @AnotacaoTabelaClasse
  (
      nomeTabela = TabelasITC.TABELA_AVALIACAO_BEMTRIBUTAVEL
      ,nomeAmigavel = "Avaliação Bem Tributável"
      ,identificadores =
      {
          @AnotacaoIdentificador
          (
              nomeAtributo = "codigo"
              ,nomeColuna = CamposAvaliacaoBemtributavel.CAMPO_AVALIACAO_BEMTRIBUTAVEL_CODIGO
              ,sequencia = @AnotacaoSequencia
              (
                  nomeSequencia = SequencesITC.SEQUENCE_AVALIACAO_BEMTRIBUTAVEL
                  ,tipoSequencia = DomnTipoSequencia.SEQUENCE
              )
          )
      }
  )
 public class AvaliacaoBemTributavelVo extends EntidadeVo
 {
    private static final long serialVersionUID = Long.MAX_VALUE;
    private BemTributavelVo bemTributavel;
    private DomnSimNao avaliacaoJudicial;
    private double valorAvaliacao;
    private java.util.Date dataAvaliacao;
    private java.util.Date dataCadastro;
    private String observacao;
    private transient AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo; // 1 AvaliacaoBemTributavel -> N Servidores
    private DomnSimNao isento; // Se essa avaliação vai ser considerada isenta de tributação
    private Date dataAtualizacao;
    private transient UnidadeSefazIntegracaoVo listaAgenfa;
    private transient UnidadeSefazIntegracaoVo listaGerencia;
    private transient UnidadeSefazIntegracaoVo listaUnidadesFazendarias;
    private boolean avaliacaoIsenta;
    private transient Date dataInicial;
    private transient Date dataFinal;
    private transient DomnTipoConsultaAvaliacao tipoConsultaAvaliacao;
    private DomnAtivoInativo statusAvaliacao;
    private DomnSimNao avaliacaoImpressa;
    private double valorReabertura;
    private DomnAvaliacaoTipo tipoAvaliacao;
    
    /**
     * Construtor padrão
     *
     * @implemented by Daniel Balieiro
     */
    public AvaliacaoBemTributavelVo()
    {
    }

    /**
     * Construtor que recebe a chave primária
     *
     * @param codigo
     * @implemented by Daniel Balieiro
     */
    public AvaliacaoBemTributavelVo(long codigo)
    {
       super(codigo);
    }

    /**
     * Construtor que recebe o Parametro de Consulta
     *
     * @param avaliacaoBemtributavelVo
     * @implemented by Daniel Balieiro
     */
    public AvaliacaoBemTributavelVo(AvaliacaoBemTributavelVo avaliacaoBemtributavelVo)
    {
       super();
       avaliacaoBemtributavelVo.setStatusAvaliacao(new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
       setParametroConsulta(avaliacaoBemtributavelVo);
    }

    /**
     * Construtor que recebe uma Coleção de Avaliações
     *
     * @param collVo
     * @implemented by Daniel Balieiro
     */
    public AvaliacaoBemTributavelVo(Collection collVo)
    {
       super(collVo);
    }
    
    public long getCodigo()
    {
       return super.getCodigo();
    }

    /**
     * Método para retornar a chave primária
     *
     * @return
     * @implemented by Daniel Balieiro
     */
    public AvaliacaoBemTributavelPk getPk()
    {
       return new AvaliacaoBemTributavelPk(getCodigo());
    }

    /**
     * Atribui um Bem Tributavel
     *
     * @param bemTributavel
     * @implemented by Daniel Balieiro
     */
    public void setBemTributavel(BemTributavelVo bemTributavel)
    {
       this.bemTributavel = bemTributavel;
    }

    /**
     * Retorna o Bem Tributavel
     *
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
                 nomeColuna = CamposAvaliacaoBemtributavel.CAMPO_ITCTB18_CODIGO_BEMTRIBUTAVEL
                 , nomeAtributo = "codigo"
             )
         }
     )
    public BemTributavelVo getBemTributavel()
    {
       if (!Validador.isObjetoValido(bemTributavel))
       {
          setBemTributavel(new BemTributavelVo());
       }
       return bemTributavel;
    }

    /**
     * Atribui existencia ou não de avaliação judicial
     *
     * @param avalJudicial
     * @implemented by Daniel Balieiro
     */
    public void setAvaliacaoJudicial(DomnSimNao avalJudicial)
    {
       this.avaliacaoJudicial = avalJudicial;
    }

    /**
     * Retorna a existencia ou não de avaliação judicial
     *
     * @return
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposAvaliacaoBemtributavel.CAMPO_AVALIACAO_JUDICIAL
         ,obrigatorio = true
     )
    public DomnSimNao getAvaliacaoJudicial()
    {
       if (!Validador.isDominioNumericoValido(avaliacaoJudicial))
       {
          setAvaliacaoJudicial(new DomnSimNao());
       }
       return avaliacaoJudicial;
    }

    /**
     * Atribui o Valor da Avaliação
     *
     * @param valorAvaliacao
     * @implemented by Daniel Balieiro
     */
    public void setValorAvaliacao(double valorAvaliacao)
    {
       this.valorAvaliacao = valorAvaliacao;
    }

    /**
     * Retorna o Valor da Avaliação
     *
     * @return
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposAvaliacaoBemtributavel.CAMPO_VALOR_AVALIACAO
         ,obrigatorio = true
     )
    public double getValorAvaliacao()
    {
       return valorAvaliacao;
    }

    public String getValorAvaliacaoFormatado()
    {
       if (!Validador.isNumericoValido(getValorAvaliacao()))
       {
          return STRING_VAZIA;
       }
       return StringUtil.doubleToMonetario(getValorAvaliacao());
    }

    /**
     * Retorna a Data da Avaliação
     *
     * @param dataAvaliacao
     * @implemented by Daniel Balieiro
     */
    public void setDataAvaliacao(Date dataAvaliacao)
    {
       this.dataAvaliacao = dataAvaliacao;
    }

    /**
     * Atribui a Data de Avaliação
     *
     * @return
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposAvaliacaoBemtributavel.CAMPO_DATA_AVALIACAO
         ,obrigatorio = true
     )
    public Date getDataAvaliacao()
    {
       return dataAvaliacao;
    }

    public String getDataAvaliacaoFormatado()
    {
       if (Validador.isDataValida(getDataAvaliacao()))
       {
          return new SefazDataHora(this.getDataAvaliacao()).formata("dd/MM/yyyy");
       }
       else
       {
          return STRING_VAZIA;
       }
    }

    /**
     * Atribui a Data de Cadastro
     *
     * @param dataCadastro
     * @implemented by Daniel Balieiro
     */
    public void setDataCadastro(Date dataCadastro)
    {
       this.dataCadastro = dataCadastro;
    }

    /**
     * Retorna a Data de Cadastro
     *
     * @return
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposAvaliacaoBemtributavel.CAMPO_DATA_CADASTRO
         ,obrigatorio = true
     )
    public Date getDataCadastro()
    {
       return dataCadastro;
    }

    public String getDataCadastroFormatado()
    {
       if (Validador.isDataValida(getDataCadastro()))
       {
          return new SefazDataHora(this.getDataCadastro()).formata("dd/MM/yyyy");
       }
       else
       {
          return STRING_VAZIA;
       }
    }

    /**
     * Atribui a Observação
     *
     * @param observacao
     * @implemented by Daniel Balieiro
     */
    public void setObservacao(String observacao)
    {
       this.observacao = observacao;
    }

    /**
     * Retorna a Observacao
     *
     * @return
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposAvaliacaoBemtributavel.CAMPO_OBSERVACAO
         ,obrigatorio = false
     )
    public String getObservacao()
    {
       if (!Validador.isStringValida(observacao))
       {
          return STRING_VAZIA;
       }
       return observacao;
    }

    /**
     * Atribui Servidores a esta Avaliação
     *
     * @param avaliacaoBemTributavelServidorVo
     * @implemented by Daniel Balieiro
     */
    public void setAvaliacaoBemTributavelServidorVo(AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo)
    {
       this.avaliacaoBemTributavelServidorVo = avaliacaoBemTributavelServidorVo;
    }

    /**
     * Retorna os Servidores desta Avaliação
     *
     * @return
     * @implemented by Daniel Balieiro
     */
    public AvaliacaoBemTributavelServidorVo getAvaliacaoBemTributavelServidorVo()
    {
       if (!Validador.isObjetoValido(avaliacaoBemTributavelServidorVo))
       {
          setAvaliacaoBemTributavelServidorVo(new AvaliacaoBemTributavelServidorVo());
       }
       return avaliacaoBemTributavelServidorVo;
    }

    /**
     * Atribui se é ou não Isento
     * @param isento
     * @implemented by Daniel Balieiro
     */
    public void setIsento(DomnSimNao isento)
    {
       this.isento = isento;
    }

    /**
     * Retorna se é ou não Isento
     * @return
     * @implemented by Daniel Balieiro
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposAvaliacaoBemtributavel.CAMPO_INFO_ISENTO
         ,obrigatorio = true
     )
    public DomnSimNao getIsento()
    {
       if (!Validador.isDominioNumericoValido(isento))
       {
          setIsento(new DomnSimNao(-1));
       }
       return isento;
    }

    /**
     * Atribui a Data de Atualizacao
     * @param dataAtualizacao
     * @implemented by Lucas Nascimento
     */
    public void setDataAtualizacao(Date dataAtualizacao)
    {
       this.dataAtualizacao = dataAtualizacao;
    }

    /**
     * Retorna a Data de Atualização
     * @return
     * @implemented by Lucas Nascimento
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposAvaliacaoBemtributavel.CAMPO_DATA_ATUALIZACAO_BD
         ,obrigatorio = true
         ,timestamp = true
     )
    public Date getDataAtualizacao()
    {
       return dataAtualizacao;
    }
    
    
    
    
    /**
     * Retorna a DAta de Atualização Formatada
     * @return
     * @implemented by Daniel Balieiro
     */
    public String getDataAtualizacaoFormatada() 
    {
       if(Validador.isDataValida(dataAtualizacao)) 
       {
          return new SefazDataHora(dataAtualizacao).formata(MASCARA_DATA_FORMATADA);
       }
       else
       {
          return "";
       }
    }

    public void setListaAgenfa(UnidadeSefazIntegracaoVo unidadeSefaz)
    {
       this.listaAgenfa = unidadeSefaz;
    }

    public UnidadeSefazIntegracaoVo getListaAgenfa()
    {
       if (!Validador.isObjetoValido(this.listaAgenfa))
       {
          setListaAgenfa(new UnidadeSefazIntegracaoVo());
       }
       return this.listaAgenfa;
    }

    public void setAvaliacaoIsenta(boolean avaliacaoIsenta)
    {
       this.avaliacaoIsenta = avaliacaoIsenta;
    }

    public boolean isAvaliacaoIsenta()
    {
       return avaliacaoIsenta;
    }

    public void setDataInicial(Date dataInicial)
    {
       this.dataInicial = dataInicial;
    }

    public Date getDataInicial()
    {
       return dataInicial;
    }
    
    public String getDataInicialFormatada()
    {
       if(!Validador.isDataValida(getDataInicial()))
       {
          return STRING_VAZIA;
       }
       return new SefazDataHora(getDataInicial()).formata(MASCARA_DATA_FORMATADA);
    }

    public void setDataFinal(Date dataFinal)
    {
       this.dataFinal = dataFinal;
    }

    public Date getDataFinal()
    {
       return dataFinal;
    }

    public String getDataFinalFormatada()
    {
       if(!Validador.isDataValida(getDataFinal()))
       {
          return STRING_VAZIA;
       }
       return new SefazDataHora(getDataFinal()).formata(MASCARA_DATA_FORMATADA);
    }

    public void setTipoConsultaAvaliacao(DomnTipoConsultaAvaliacao tipoConsultaAvaliacao)
    {
       this.tipoConsultaAvaliacao = tipoConsultaAvaliacao;
    }

    public DomnTipoConsultaAvaliacao getTipoConsultaAvaliacao()
    {
       if(!Validador.isDominioNumericoValido(tipoConsultaAvaliacao))
       {
          setTipoConsultaAvaliacao(new DomnTipoConsultaAvaliacao());
       }
       return tipoConsultaAvaliacao;
    }

    public void setListaGerencia(UnidadeSefazIntegracaoVo listaGerencia)
    {
       this.listaGerencia = listaGerencia;
    }

    public UnidadeSefazIntegracaoVo getListaGerencia()
    {
       if(!Validador.isObjetoValido(listaGerencia))
       {
          setListaGerencia(new UnidadeSefazIntegracaoVo());
       }
       return listaGerencia;
    }

    public boolean isPeriodoValido()
    {
       boolean retorno = true;
       if (getDataInicial().after(getDataFinal()))
       {
          retorno = false;
       }
       return retorno;
    }

    public void setListaUnidadesFazendarias(UnidadeSefazIntegracaoVo listaUnidadesFazendarias)
    {
       this.listaUnidadesFazendarias = listaUnidadesFazendarias;
    }

    public UnidadeSefazIntegracaoVo getListaUnidadesFazendarias()
    {
       if(!Validador.isObjetoValido(listaUnidadesFazendarias))
       {
          setListaUnidadesFazendarias(new UnidadeSefazIntegracaoVo());
       }
       return listaUnidadesFazendarias;
    }

    public void setStatusAvaliacao(DomnAtivoInativo statusAvaliacao)
    {
       this.statusAvaliacao = statusAvaliacao;
    }

    @AnotacaoAtributo
    (
        nomeColuna = CamposAvaliacaoBemtributavel.CAMPO_STATUS_AVALIACAO
        ,obrigatorio = true
    )   
    public DomnAtivoInativo getStatusAvaliacao()
    {
       if (!Validador.isObjetoValido(statusAvaliacao))
       {
          setStatusAvaliacao(new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
       }
       return statusAvaliacao;
    }


    public void setAvaliacaoImpressa(DomnSimNao avaliacaoImpressa)
    {
       this.avaliacaoImpressa = avaliacaoImpressa;
    }

    @AnotacaoAtributo
    (
        nomeColuna = CamposAvaliacaoBemtributavel.CAMPO_AVALIACAO_IMPRESSA
        ,obrigatorio = true
    )  
    public DomnSimNao getAvaliacaoImpressa()
    {
       if (!Validador.isObjetoValido(avaliacaoImpressa))
       {
          setAvaliacaoImpressa(new DomnSimNao(DomnSimNao.NAO));
       }
       return avaliacaoImpressa;
    }


    public void setValorReabertura(double valorReabertura)
    {
       this.valorReabertura = valorReabertura;
    }

    /**
     * Retorna o Valor de reabertura da avaliavação
     *
     * @return
     * @implemented by Dherkyan Ribeiro da Silva
     */
     @AnotacaoAtributo
     (
         nomeColuna = CamposAvaliacaoBemtributavel.CAMPO_VALOR_REABERTURA
         ,obrigatorio = false
     )
    public double getValorReabertura()
    {
       return valorReabertura;
    }
    
    public String getValorReaberturaFormatado()
    {
       if (!Validador.isNumericoValido(getValorReabertura()))
       {
          return STRING_VAZIA;
       }
       return StringUtil.doubleToMonetario(getValorReabertura());
    }


    public void setTipoAvaliacao(DomnAvaliacaoTipo tipoAvaliacao)
    {
       this.tipoAvaliacao = tipoAvaliacao;
    }

    @AnotacaoAtributo
    (
        nomeColuna = CamposAvaliacaoBemtributavel.CAMPO_TIPO_AVALIACAO
        ,obrigatorio = false
    )
    public DomnAvaliacaoTipo getTipoAvaliacao()
    {
       if (!Validador.isObjetoValido(tipoAvaliacao))
       {
          setTipoAvaliacao(new DomnAvaliacaoTipo(DomnAvaliacaoTipo.NORMAL));
       }
       return tipoAvaliacao;
    }
 }
