package br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioVo;
import br.gov.mt.sefaz.itc.model.log.log.LogITCDVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.data.DataUtil;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAvaliacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.UnidadeSefazIntegracaoVo;

import java.util.Collection;
import java.util.Date;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores do objeto STATUS GIA ITCD (Value Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_STATUS_GIA_ITCD, nomeAmigavel = "Status GIA-ITCD", identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = CamposStatusGIAITCD.CAMPO_CODIGO_STATUS_ITCD, sequencia = @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_STATUS_GIA_ITCD, tipoSequencia = DomnTipoSequencia.SEQUENCE)
         )
         } )
public class StatusGIAITCDVo extends EntidadeVo<StatusGIAITCDVo>
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   private ServidorSefazIntegracaoVo servidor;
   private GIAITCDVo giaITCDVo;
   private DomnStatusGIAITCD statusGIAITCD;
   private java.util.Date dataAtualizacao;
   private Date dataPermissao;
   private String observacao;
   private long protocoloParcelamento;
   private int numeroParcelas;
   private Date dataParcelamento;
   private long protocoloImpugnacao;
   private Date dataImpugnacao;
   private Date dataRatificacao;
   private long numeroDARRatificacao;
   private Date dataEmissaoSegundaRetificacao;
   private long numeroDARSegundaRetificacao;
   private Date dataCienciaRetificacao;
   private Date dataEnvioDividaAtiva;
   private Date dataCienciaSegundaRetificacao;
   private long protocolo;
   private Date dataProtocolo;
   private int codigoAgenfa;
   private int codigoUnidadeAvaliacao;
   private long protocoloDesistencia;
   private Date dataDesistencia;
   private Date dataNotificacao;
   private Date dataEmissaoRetificacao;
   private String justificativaReativacao;
   private DomnTipoAvaliacao tipoAvaliacao;
   private Date dataCadastroAvaliacao;
   private GIAITCDTemporarioVo giaTemporaria;
   private transient int[] listaStatus;
   private transient int ordemAparicaoStatus;
   private Date dataCienciaNotificacao;
   private Date dataQuitacao;
   private long numeroDARQuitacao;
   private String justificativaEnvioDividaAtiva;
   private Date dataEnvioInscricaoDividaAtiva;
   private String justificativaEnvioInscricaoDividaAtiva;
   private String motivoImpugnacao;
   private double valorImposto;
   private Date dataCienciaRatificacao;
   private String justificativaReparcelamento;
   private ServidorSefazIntegracaoVo servidorSefazResponsavelAlteracao;
   private long numeroGiaSubstituta;
   private UnidadeSefazIntegracaoVo unidadeProtocolo;
   private UnidadeSefazIntegracaoVo unidadeAvaliacao;
   private Date dataEnvioCCF;
   private long protocoloCCF;
   private String justificativaAlteracao;
   private LogITCDVo logITCDVo;
   private transient boolean isPossuiStatusQuitadoeAvaliacaoExcluida;

   private GIAITCDDarVo giaITCDDarVo;

   private transient boolean exibeCampoJustificativaRetornoParcelamento;


   /**
    * Construtor Padrão
    * @implemented by Daniel Balieiro
    */
   public StatusGIAITCDVo()
   {
      super();
   }

   /**
    * Construtor que recebe a chave primária
    * @param codigo
    * @implemented by Daniel Balieiro
    */
   public StatusGIAITCDVo(long codigo)
   {
      super(codigo);
   }

   /**
    * Construtor que recebe o Parametro de Consulta
    * @param statusGIAITCDVo
    * @implemented by Daniel Balieiro
    */
   public StatusGIAITCDVo(StatusGIAITCDVo statusGIAITCDVo)
   {
      super();
      setParametroConsulta(statusGIAITCDVo);
   }

   /**
    * Construtor que recebe uma Coleção de StatusGIAITCDVo
    * @param collVo
    * @implemented by Daniel Balieiro
    */
   public StatusGIAITCDVo(Collection collVo)
   {
      super(collVo);
   }

   /**
    * Retorna a Chave Primária
    * @return StatusGIAITCDPk
    * @implemented by Daniel Balieiro
    */
   public StatusGIAITCDPk getPk()
   {
      return new StatusGIAITCDPk(getCodigo());
   }

   /**
    * Este metodo verifica se o status atual esta contido no grupo de status informado
    * 
    * @param statusRequerido       grupo de status a ser testado
    * @return
    */
   public boolean isStatusIn(int[] statusRequerido)
   {
      boolean retorno = false;
      for (int a = 0; a < statusRequerido.length; a++)
      {
         if (this.getStatusGIAITCD().is(statusRequerido[a]))
         {
            retorno = true;
         }
      }
      return retorno;
   }

   public boolean isStatusInCollVo(int[] statusRequerido) throws ObjetoObrigatorioException, 
                                                                 ParametroObrigatorioException
   {
      Validador.validaObjeto(giaITCDVo);
      boolean retorno = false;
      for (StatusGIAITCDVo statusGIAITCDAtualVo: this.getCollVO())
      {
         if (statusGIAITCDAtualVo.isStatusIn(statusRequerido))
         {
            retorno = true;
            break;
         }
      }
      return retorno;
   }

   /**
    * Atribui um GIA ITCD
    * @param giaITCDVo
    * @implemented by Daniel Balieiro
    */
   public void setGiaITCDVo(GIAITCDVo giaITCDVo)
   {
      this.giaITCDVo = giaITCDVo;
   }

   /**
    * Retorna o GIA ITCD
    * @return GIAITCDVo
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposStatusGIAITCD.CAMPO_ITCTB14_CODIGO_ITCD, nomeAtributo = "codigo")
            } )
   public GIAITCDVo getGiaITCDVo()
   {
      if (!Validador.isObjetoValido(giaITCDVo))
      {
         setGiaITCDVo(new GIAITCDVo());
      }
      return giaITCDVo;
   }

   /**
    * Atribui um Servidor Sefaz
    * @param servidor
    * @implemented by Daniel Balieiro
    */
   public void setServidor(ServidorSefazIntegracaoVo servidor)
   {
      this.servidor = servidor;
   }

   /**
    * Retorna o Servidor Sefazz
    * @return ServidorSefazIntegracaoVo
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposStatusGIAITCD.CAMPO_SGPTB06_NUMERO_MATRICULA_SERVIDOR, nomeAtributo = "numrMatricula")
            } )
   public ServidorSefazIntegracaoVo getServidor()
   {
      if (!Validador.isObjetoValido(servidor))
      {
         setServidor(new ServidorSefazIntegracaoVo());
      }
      return servidor;
   }

   /**
    * Atribui um Status a GIA ITCD
    * @param statusGIAITCD
    * @implemented by Daniel Balieiro
    */
   public void setStatusGIAITCD(DomnStatusGIAITCD statusGIAITCD)
   {
      this.statusGIAITCD = statusGIAITCD;
   }

   /**
    * Retorna o Status da GIA ITCD
    * @return DomnStatusGIAITCD
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_STAT_ITCD, obrigatorio = true)
   public DomnStatusGIAITCD getStatusGIAITCD()
   {
      if (!Validador.isObjetoValido(statusGIAITCD))
      {
         setStatusGIAITCD(new DomnStatusGIAITCD());
      }
      return statusGIAITCD;
   }

   /**
    * Retorna a Data de Atualização Formatada.
    * @return String
    * @implemented by Rogério Sanches de Oliveira
    */
   public String getDataAtualizacaoFormatada()
   {
      return DataUtil.formataData(getDataAtualizacao());
   }

   public String getDataAtualizacaoFormatadaCompleta()
   {
      return DataUtil.formataData(getDataAtualizacao(), "dd/MM/yyyy HH:mm:ss");
   }

   /**
    * Atribui a Data de Atualização
    * @param dataAtualizacao
    * @implemented by Daniel Balieiro
    */
   public void setDataAtualizacao(Date dataAtualizacao)
   {
      this.dataAtualizacao = dataAtualizacao;
   }

   /**
    * Retorna a Data de Atualização
    * @return Date
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_ATUALIZACAO_BD, obrigatorio = true, timestamp = true)
   public Date getDataAtualizacao()
   {
      return dataAtualizacao;
   }

   /**
    * Atribui a Data de Permissao
    * @param dataPermissao
    * @implemented by Daniel Balieiro
    */
   public void setDataPermissao(Date dataPermissao)
   {
      this.dataPermissao = dataPermissao;
   }

   /**
    * Retorna a Data de Permissão
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_PERMISSAO, obrigatorio = false)
   public Date getDataPermissao()
   {
      return dataPermissao;
   }

   public String getDataPermissaoFormatada()
   {
      return DataUtil.formataData(getDataPermissao());
   }

   /**
    * Atribui a Observação
    * @param observacao
    * @implemented by Daniel Balieiro
    */
   public void setObservacao(String observacao)
   {
      if (Validador.isStringValida(observacao))
      {
         this.observacao = observacao.trim().toUpperCase();
      } else
      {
         this.observacao = observacao;
      }
   }

   /**
    * Retorna a Observação
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_OBSERVACAO, obrigatorio = false)
   public String getObservacao()
   {
      if (!Validador.isStringValida(observacao))
      {
         setObservacao(STRING_VAZIA);
      }
      return observacao;
   }

   /**
    * Atribui o Protocolo de Parcelamento
    * @param protocoloParcelamento
    * @implemented by Daniel Balieiro
    */
   public void setProtocoloParcelamento(long protocoloParcelamento)
   {
      this.protocoloParcelamento = protocoloParcelamento;
   }

   /**
    * Retorna o Protocolo de Parcelamento
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_PROTOCOLO_PARCELAMENTO, obrigatorio = false)
   public long getProtocoloParcelamento()
   {
      return protocoloParcelamento;
   }

   public String getProtocoloParcelamentoFormatado()
   {
      if (!Validador.isNumericoValido(this.protocoloParcelamento))
      {
         return STRING_VAZIA;
      }
      return String.valueOf(this.protocoloParcelamento);
   }

   /**
    * Atribui o Número de Parcelas
    * @param numeroParcelas
    * @implemented by Daniel Balieiro
    */
   public void setNumeroParcelas(int numeroParcelas)
   {
      this.numeroParcelas = numeroParcelas;
   }

   /**
    * Retona o Número de Parcelas
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_NUMERO_PARCELAS, obrigatorio = false)
   public int getNumeroParcelas()
   {
      return numeroParcelas;
   }

   public String getNumeroParcelasFormatado()
   {
      if (!Validador.isNumericoValido(this.numeroParcelas))
      {
         return STRING_VAZIA;
      }
      return String.valueOf(this.numeroParcelas);
   }

   /**
    * Atribui a Data de Parcelamento
    * @param dataParcelamento
    * @implemented by Daniel Balieiro
    */
   public void setDataParcelamento(Date dataParcelamento)
   {
      this.dataParcelamento = dataParcelamento;
   }

   /**
    * Retorna a Data de Parcelamento
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_PARCELAMENTO, obrigatorio = false)
   public Date getDataParcelamento()
   {
      return dataParcelamento;
   }

   public String getDataParcelamentoFormatado()
   {
      return DataUtil.formataData(getDataParcelamento());
   }

   /**
    * Atribui o Protocolo de Impugnação
    * @param protocoloImpugnacao
    * @implemented by Daniel Balieiro
    */
   public void setProtocoloImpugnacao(long protocoloImpugnacao)
   {
      this.protocoloImpugnacao = protocoloImpugnacao;
   }

   /**
    * Retorna o Protocolo de Impugnação
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_PROTOCOLO_IMPUGNACAO, obrigatorio = false)
   public long getProtocoloImpugnacao()
   {
      return protocoloImpugnacao;
   }

   public String getProtocoloImpugnacaoFormatado()
   {
      if (!Validador.isNumericoValido(this.protocoloImpugnacao))
      {
         return STRING_VAZIA;
      }
      return String.valueOf(this.protocoloImpugnacao);
   }

   /**
    * Atribui a Data de Impugnação
    * @param dataImpugnacao
    * @implemented by Daniel Balieiro
    */
   public void setDataImpugnacao(Date dataImpugnacao)
   {
      this.dataImpugnacao = dataImpugnacao;
   }

   /**
    * Retorna a Data de Impugnação
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_IMPUGNACAO, obrigatorio = false)
   public Date getDataImpugnacao()
   {
      return dataImpugnacao;
   }

   public String getDataImpugnacaoFormatado()
   {
      return DataUtil.formataData(getDataImpugnacao());
   }

   /**
    * Atribui a Data de Ratificação
    * @param dataRatificacao
    * @implemented by Daniel Balieiro
    */
   public void setDataRatificacao(Date dataRatificacao)
   {
      this.dataRatificacao = dataRatificacao;
   }

   /**
    * Retorna a Data de Ratificação
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_RATIFICACAO, obrigatorio = false)
   public Date getDataRatificacao()
   {
      return dataRatificacao;
   }

   public String getDataRatificacaoFormatado()
   {
      return DataUtil.formataData(getDataRatificacao());
   }

   /**
    * Atribui o Número do DAR
    * @param numeroDARRatificacao
    * @implemented by Daniel Balieiro
    */
   public void setNumeroDARRatificacao(long numeroDARRatificacao)
   {
      this.numeroDARRatificacao = numeroDARRatificacao;
   }

   /**
    * Retorna o Número do DAR
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_NUMERO_DAR_RATIFICACAO, obrigatorio = false)
   public long getNumeroDARRatificacao()
   {
      return numeroDARRatificacao;
   }

   /**
    * Retorna o Número do DAR formatado
    * @return
    * @implemented by Leandro Dorileo
    */
   public String getNumeroDARRatificacaoFormatado()
   {
      if (!Validador.isNumericoValido(this.numeroDARRatificacao))
      {
         return STRING_VAZIA;
      }
      return String.valueOf(numeroDARRatificacao);
   }

   /**
    * Atribui a Data de Emissão da Segunda Retificação
    * @param dataEmissaoSegundaRetificacao
    * @implemented by Daniel Balieiro
    */
   public void setDataEmissaoSegundaRetificacao(Date dataEmissaoSegundaRetificacao)
   {
      this.dataEmissaoSegundaRetificacao = dataEmissaoSegundaRetificacao;
   }

   /**
    * Retorna a Data de Emissão da Segunda Retificação
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_EMISSAO_SEGUNDA_RETIFICACAO, obrigatorio = false)
   public Date getDataEmissaoSegundaRetificacao()
   {
      return dataEmissaoSegundaRetificacao;
   }

   public String getDataEmissaoSegundaRetificacaoFormatado()
   {
      if (Validador.isDataValida(new SefazDataHora(getDataEmissaoSegundaRetificacao())))
      {
         return new SefazDataHora(this.getDataEmissaoSegundaRetificacao()).formata("dd/MM/yyyy");
      } else
      {
         return STRING_VAZIA;
      }
   }

   /**
    * Atribui o número do DAR da Segunda Retificação
    * @param numeroDARSegundaRetificacao
    * @implemented by Daniel Balieiro
    */
   public void setNumeroDARSegundaRetificacao(long numeroDARSegundaRetificacao)
   {
      this.numeroDARSegundaRetificacao = numeroDARSegundaRetificacao;
   }

   /**
    * Retorna o Número do DAR da Segunda Retificação
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_NUMERO_DAR_SEGUNDA_RETIFICACAO, obrigatorio = false)
   public long getNumeroDARSegundaRetificacao()
   {
      return numeroDARSegundaRetificacao;
   }

   /**
    * Retorna o Número do DAR da Segunda Retificação formatado
    * @return
    * @implemented by Leandro Dorileo
    */
   public String getNumeroDARSegundaRetificacaoFormatado()
   {
      if (!Validador.isNumericoValido(this.numeroDARSegundaRetificacao))
      {
         return STRING_VAZIA;
      }
      return String.valueOf(numeroDARSegundaRetificacao);
   }

   /**
    * Atribui a Data de Ciência de Retificação
    * @param dataCienciaRetificacao
    * @implemented by Daniel Balieiro
    */
   public void setDataCienciaRetificacao(Date dataCienciaRetificacao)
   {
      this.dataCienciaRetificacao = dataCienciaRetificacao;
   }

   /**
    * Retorna a Data de Ciência de Retificação
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_CIENCIA_RETIFICACAO, obrigatorio = false)
   public Date getDataCienciaRetificacao()
   {
      return dataCienciaRetificacao;
   }

   public String getDataCienciaRetificacaoFormatado()
   {
      return DataUtil.formataData(getDataCienciaRetificacao());
   }

   /**
    * Atribui a Data de Envio para a Dívida Ativa
    * @param dataEnvioDividaAtiva
    * @implemented by Daniel Balieiro
    */
   public void setDataEnvioDividaAtiva(Date dataEnvioDividaAtiva)
   {
      this.dataEnvioDividaAtiva = dataEnvioDividaAtiva;
   }

   /**
    * Retorna a Data de Envio para  a Dívida Ativa
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_ENVIO_DIVIDA_ATIVA, obrigatorio = false)
   public Date getDataEnvioDividaAtiva()
   {
      return dataEnvioDividaAtiva;
   }

   public String getDataEnvioDividaAtivaFormatado()
   {
      return DataUtil.formataData(getDataEnvioDividaAtiva());
   }

   /**
    * Atribui a Data de Ciência da Segunda Retificação
    * @param dataCienciaSegundaRetificacao
    * @implemented by Daniel Balieiro
    */
   public void setDataCienciaSegundaRetificacao(Date dataCienciaSegundaRetificacao)
   {
      this.dataCienciaSegundaRetificacao = dataCienciaSegundaRetificacao;
   }

   /**
    * Retorna a Data de Ciência da Segunda Retificação
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_CIENCIA_SEGUNDA_RETIFICACAO, obrigatorio = false)
   public Date getDataCienciaSegundaRetificacao()
   {
      return dataCienciaSegundaRetificacao;
   }

   public String getDataCienciaSegundaRetificacaoFormatado()
   {
      return DataUtil.formataData(getDataCienciaSegundaRetificacao());
   }

   /**
    * Atribui o Protocolo
    * @param protocolo
    * @implemented by Daniel Balieiro
    */
   public void setProtocolo(long protocolo)
   {
      this.protocolo = protocolo;
   }

   /**
    * Retorna o Protocolo
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_PROTOCOLO, obrigatorio = false)
   public long getProtocolo()
   {
      return protocolo;
   }

   /**
    * Método que retorna o Protocolo em formato String
    * @return
    * @implemented by Daniel Balieiro
    * @implemented by Leandro Dorileo
    */
   public String getProtocoloFormatado()
   {
      if (!Validador.isNumericoValido(this.protocolo))
      {
         return STRING_VAZIA;
      }
      return String.valueOf(getProtocolo());
   }

   /**
    * Atribui a Data do Protocolo
    * @param dataProtocolo
    * @implemented by Daniel Balieiro
    */
   public void setDataProtocolo(Date dataProtocolo)
   {
      this.dataProtocolo = dataProtocolo;
   }

   /**
    * Retorna a Data do Protocolo
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_PROTOCOLO, obrigatorio = false)
   public Date getDataProtocolo()
   {
      return dataProtocolo;
   }

   /**
    * Retorna a Data do Protocolo
    * @return
    * @implemented by Rogério Sanches de Oliveira
    */
   public String getDataProtocoloFormatada()
   {
      return DataUtil.formataData(getDataProtocolo());
   }

   /**
    * Atribui o Código da Agenfa
    * @param codigoAgenfa
    * @implemented by Daniel Balieiro
    */
   public void setCodigoAgenfa(int codigoAgenfa)
   {
      this.codigoAgenfa = codigoAgenfa;
   }

   /**
    * Retorna o Código da Agenfa
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_CODIGO_AGENFA_PROTOCOLO, obrigatorio = false)
   public int getCodigoAgenfa()
   {
      return codigoAgenfa;
   }

   /**
    * Atribui o Protocolo de Desistência
    * @param protocoloDesistencia
    * @implemented by Daniel Balieiro
    */
   public void setProtocoloDesistencia(long protocoloDesistencia)
   {
      this.protocoloDesistencia = protocoloDesistencia;
   }

   /**
    * Retorna o Procotolo de Desistência
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_PROTOCOLO_DESISTENCIA, obrigatorio = false)
   public long getProtocoloDesistencia()
   {
      return protocoloDesistencia;
   }

   /**
    * Retorna o Procotolo de Desistência Formatado
    * @return String
    * @implemented by Anderson Boehler Iglesias Araujo
    */
   public String getProtocoloDesistenciaFormatado()
   {
      if (!Validador.isNumericoValido(getProtocoloDesistencia()))
      {
         return STRING_VAZIA;
      }
      return String.valueOf(getProtocoloDesistencia());
   }

   /**
    * Atribui a Data de Desistência
    * @param dataDesistencia
    * @implemented by Daniel Balieiro
    */
   public void setDataDesistencia(Date dataDesistencia)
   {
      this.dataDesistencia = dataDesistencia;
   }

   /**
    * Retorna a Data de Desistência
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_DESISTENCIA, obrigatorio = false)
   public Date getDataDesistencia()
   {
      return dataDesistencia;
   }

   /**
    * Retorna a Data de Desistência Formatada
    * @return
    * @implemented by Anderson Boehler Iglesias Araujo
    */
   public String getDataDesistenciaFormatada()
   {
      return DataUtil.formataData(getDataDesistencia());
   }

   /**
    * Atribui a Data de Notificação
    * @param dataNotificacao
    * @implemented by Daniel Balieiro
    */
   public void setDataNotificacao(Date dataNotificacao)
   {
      this.dataNotificacao = dataNotificacao;
   }

   /**
    * Retorna a Data de Notificação
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_NOTIFICACAO, obrigatorio = false)
   public Date getDataNotificacao()
   {
      return dataNotificacao;
   }

   public String getDataNotificacaoFormatado()
   {
      return DataUtil.formataData(getDataNotificacao());
   }

   /**
    * Atribui a Data de Emissão da Retificação
    * @param dataEmissaoRetificacao
    * @implemented by Daniel Balieiro
    */
   public void setDataEmissaoRetificacao(Date dataEmissaoRetificacao)
   {
      this.dataEmissaoRetificacao = dataEmissaoRetificacao;
   }

   /**
    * Retorna a Data de Emissão da Retificação
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_EMISSAO_RETIFICACAO, obrigatorio = false)
   public Date getDataEmissaoRetificacao()
   {
      return dataEmissaoRetificacao;
   }

   public String getDataEmissaoRetificacaoFormatado()
   {
      return DataUtil.formataData(getDataEmissaoRetificacao());
   }

   /**
    * Atribui a Justificativa de Reativação
    * @param justificativaReativacao
    * @implemented by Daniel Balieiro
    */
   public void setJustificativaReativacao(String justificativaReativacao)
   {
      if (Validador.isStringValida(justificativaReativacao))
      {
         this.justificativaReativacao = justificativaReativacao.trim().toUpperCase();
      } else
      {
         this.justificativaReativacao = justificativaReativacao;
      }
   }

   /**
    * Retorna a Justificativa de Reativação
    * @return
    * @implemented by Daniel Balieiro
    */
   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_JUSTIFICATIVA_REATIVACAO, obrigatorio = false)
   public String getJustificativaReativacao()
   {
      if (!Validador.isStringValida(justificativaReativacao))
      {
         return STRING_VAZIA;
      }
      return justificativaReativacao;
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_TIPO_AVALIACAO, obrigatorio = false)
   public DomnTipoAvaliacao getTipoAvaliacao()
   {
      if (!Validador.isDominioNumericoValido(tipoAvaliacao))
      {
         setTipoAvaliacao(new DomnTipoAvaliacao());
      }
      return tipoAvaliacao;
   }

   public void setTipoAvaliacao(DomnTipoAvaliacao tipoAvaliacao)
   {
      this.tipoAvaliacao = tipoAvaliacao;
   }

   public void setCodigoUnidadeAvaliacao(int codigoUnidadeAvaliacao)
   {
      this.codigoUnidadeAvaliacao = codigoUnidadeAvaliacao;
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_CODIGO_UNIDADE_AVALIACAO, obrigatorio = false)
   public int getCodigoUnidadeAvaliacao()
   {
      return codigoUnidadeAvaliacao;
   }

   public void setDataCadastroAvaliacao(Date dataCadastroAvaliacao)
   {
      this.dataCadastroAvaliacao = dataCadastroAvaliacao;
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_CADASTRO_AVALIACAO, obrigatorio = false)
   public Date getDataCadastroAvaliacao()
   {
      return dataCadastroAvaliacao;
   }

   public String getDataCadastroAvaliacaoFormatada()
   {
      return DataUtil.formataData(getDataCadastroAvaliacao());
   }

   public void setListaStatus(int[] listaStatus)
   {
      this.listaStatus = listaStatus;
   }

   public int[] getListaStatus()
   {
      return listaStatus;
   }

   public void setGiaTemporaria(GIAITCDTemporarioVo giaTemporaria)
   {
      this.giaTemporaria = giaTemporaria;
   }

   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposStatusGIAITCD.CAMPO_CODIGO_GIA_TEMPORARIA, nomeAtributo = "codigo")
            } )
   public GIAITCDTemporarioVo getGiaTemporaria()
   {
      if (!Validador.isObjetoValido(giaTemporaria))
      {
         setGiaTemporaria(new GIAITCDTemporarioVo());
      }
      return giaTemporaria;
   }

   public void setDataCienciaNotificacao(Date dataCienciaNotificacao)
   {
      this.dataCienciaNotificacao = dataCienciaNotificacao;
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_CIENCIA_NOTIFICACAO, obrigatorio = false)
   public Date getDataCienciaNotificacao()
   {
      return dataCienciaNotificacao;
   }

   public String getDataCienciaNotificacaoFormatada()
   {
      return DataUtil.formataData(getDataCienciaNotificacao());
   }

   public void setDataQuitacao(Date dataQuitacao)
   {
      this.dataQuitacao = dataQuitacao;
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_QUITACAO, obrigatorio = false)
   public Date getDataQuitacao()
   {
      return dataQuitacao;
   }

   public String getDataQuitacaoFormatada()
   {
      return DataUtil.formataData(getDataQuitacao());
   }

   public void setNumeroDARQuitacao(long numeroDARQuitacao)
   {
      this.numeroDARQuitacao = numeroDARQuitacao;
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_NUMR_DAR_QUITACAO, obrigatorio = false)
   public long getNumeroDARQuitacao()
   {
      return numeroDARQuitacao;
   }

   public String getNumeroDARQuitacaoFormatado()
   {
      if (!Validador.isNumericoValido(getNumeroDARQuitacao()))
      {
         return STRING_VAZIA;
      }
      return String.valueOf(getNumeroDARQuitacao());
   }

   public void setJustificativaEnvioDividaAtiva(String justificativaEnvioDividaAtiva)
   {
      if (Validador.isStringValida(justificativaEnvioDividaAtiva))
      {
         this.justificativaEnvioDividaAtiva = justificativaEnvioDividaAtiva.trim().toUpperCase();
      } else
      {
         this.justificativaEnvioDividaAtiva = justificativaEnvioDividaAtiva;
      }
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_JUSTIFICATIVA_ENVIO_DIVIDA_ATIVA, obrigatorio = false)
   public String getJustificativaEnvioDividaAtiva()
   {
      if (!Validador.isStringValida(justificativaEnvioDividaAtiva))
      {
         setJustificativaEnvioDividaAtiva(STRING_VAZIA);
      }
      return justificativaEnvioDividaAtiva;
   }

   public void setDataEnvioInscricaoDividaAtiva(Date dataEnvioInscricaoDividaAtiva)
   {
      this.dataEnvioInscricaoDividaAtiva = dataEnvioInscricaoDividaAtiva;
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_ENVIO_INSCRICAO_DIVIDA_ATIVA, obrigatorio = false)
   public Date getDataEnvioInscricaoDividaAtiva()
   {
      return dataEnvioInscricaoDividaAtiva;
   }

   public String getDataEnvioInscricaoDividaAtivaFormatada()
   {
      return DataUtil.formataData(getDataEnvioInscricaoDividaAtiva());
   }

   public void setJustificativaEnvioInscricaoDividaAtiva(String justificativaEnvioInscricaoDividaAtiva)
   {
      if (Validador.isStringValida(justificativaEnvioInscricaoDividaAtiva))
      {
         this.justificativaEnvioInscricaoDividaAtiva = justificativaEnvioInscricaoDividaAtiva.trim().toUpperCase();
      } else
      {
         this.justificativaEnvioInscricaoDividaAtiva = justificativaEnvioInscricaoDividaAtiva;
      }
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_JUSTIFICATIVA_ENVIO_INSCRICAO_DIVIDA_ATIVA, obrigatorio = false)
   public String getJustificativaEnvioInscricaoDividaAtiva()
   {
      if (!Validador.isStringValida(justificativaEnvioInscricaoDividaAtiva))
      {
         setJustificativaEnvioInscricaoDividaAtiva(STRING_VAZIA);
      }
      return justificativaEnvioInscricaoDividaAtiva;
   }

   public void setMotivoImpugnacao(String motivoImpugnacao)
   {
      if (Validador.isStringValida(motivoImpugnacao))
      {
         this.motivoImpugnacao = motivoImpugnacao.trim().toUpperCase();
      } else
      {
         this.motivoImpugnacao = motivoImpugnacao;
      }
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_MOTIVO_IMPUGNACAO, obrigatorio = false)
   public String getMotivoImpugnacao()
   {
      if (!Validador.isStringValida(motivoImpugnacao))
      {
         setMotivoImpugnacao(STRING_VAZIA);
      }
      return motivoImpugnacao;
   }

   public void setValorImposto(double valorImposto)
   {
      this.valorImposto = valorImposto;
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_VALOR_IMPOSTO, obrigatorio = false)
   public double getValorImposto()
   {
      return valorImposto;
   }

   public String getValorImpostoFormatado()
   {
      if (!Validador.isNumericoValido(getValorImposto()))
      {
         return STRING_VAZIA;
      }
      return StringUtil.doubleToMonetario(getValorImposto(), 2);
   }

   public void setDataCienciaRatificacao(Date dataCienciaRatificacao)
   {
      this.dataCienciaRatificacao = dataCienciaRatificacao;
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_CIENCIA_RATIFICACAO, obrigatorio = false)
   public Date getDataCienciaRatificacao()
   {
      return dataCienciaRatificacao;
   }

   public String getDataCienciaRatificacaoFormatada()
   {
      return DataUtil.formataData(getDataCienciaRatificacao());
   }

   public void setGiaITCDDarVo(GIAITCDDarVo giaITCDDarVo)
   {
      this.giaITCDDarVo = giaITCDDarVo;
   }

   public GIAITCDDarVo getGiaITCDDarVo()
   {
      if (!Validador.isObjetoValido(giaITCDDarVo))
      {
         setGiaITCDDarVo(new GIAITCDDarVo());
      }
      return giaITCDDarVo;
   }

   public void setJustificativaReparcelamento(String justificativaReparcelamento)
   {
      if (Validador.isStringValida(justificativaReparcelamento))
      {
         this.justificativaReparcelamento = justificativaReparcelamento.trim().toUpperCase();
      } else
      {
         this.justificativaReparcelamento = justificativaReparcelamento;
      }
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_JUSTIFICATIVA_REPARCELAMENTO, obrigatorio = false)
   public String getJustificativaReparcelamento()
   {
      if (!Validador.isStringValida(justificativaReparcelamento))
      {
         setJustificativaReativacao(STRING_VAZIA);
      }
      return justificativaReparcelamento;
   }

   public void setExibeCampoJustificativaRetornoParcelamento(boolean exibeCampoJustificativaRetornoParcelamento)
   {
      this.exibeCampoJustificativaRetornoParcelamento = exibeCampoJustificativaRetornoParcelamento;
   }

   public boolean isExibeCampoJustificativaRetornoParcelamento()
   {
      return exibeCampoJustificativaRetornoParcelamento;
   }

   public long getCodigo()
   {
      return super.getCodigo();
   }

   public void setServidorSefazResponsavelAlteracao(ServidorSefazIntegracaoVo servidorSefazResponsavelAlteracao)
   {
      this.servidorSefazResponsavelAlteracao = servidorSefazResponsavelAlteracao;
   }

   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposStatusGIAITCD.CAMPO_SERVIDOR_SEFAZ_RESPONSAVEL_ALTERACAO, nomeAtributo = "numrMatricula")
            } )
   public ServidorSefazIntegracaoVo getServidorSefazResponsavelAlteracao()
   {
      if (!Validador.isObjetoValido(servidorSefazResponsavelAlteracao))
      {
         setServidorSefazResponsavelAlteracao(new ServidorSefazIntegracaoVo());
      }
      return servidorSefazResponsavelAlteracao;
   }

   public void setNumeroGiaSubstituta(long numeroGIAsubstituta)
   {
      this.numeroGiaSubstituta = numeroGIAsubstituta;
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_NUMERO_GIA_SUBSTITUTA, obrigatorio = false)
   public long getNumeroGiaSubstituta()
   {
      return numeroGiaSubstituta;
   }

   public void setOrdemAparicaoStatus(int ordemAparicaoStatus)
   {
      this.ordemAparicaoStatus = ordemAparicaoStatus;
   }

   public int getOrdemAparicaoStatus()
   {
      return ordemAparicaoStatus;
   }

   public void setUnidadeProtocolo(UnidadeSefazIntegracaoVo unidadeProtocolo)
   {
      this.unidadeProtocolo = unidadeProtocolo;
   }

   public UnidadeSefazIntegracaoVo getUnidadeProtocolo()
   {
      if (!Validador.isObjetoValido(unidadeProtocolo))
      {
         setUnidadeProtocolo(new UnidadeSefazIntegracaoVo());
      }
      return unidadeProtocolo;
   }

   public void setUnidadeAvaliacao(UnidadeSefazIntegracaoVo unidadeAvaliacao)
   {
      this.unidadeAvaliacao = unidadeAvaliacao;
   }

   public UnidadeSefazIntegracaoVo getUnidadeAvaliacao()
   {
      if (!Validador.isObjetoValido(unidadeAvaliacao))
      {
         setUnidadeAvaliacao(new UnidadeSefazIntegracaoVo());
      }
      return unidadeAvaliacao;
   }

   /**
    * @param dataEnvioCCF
    */
   public void setDataEnvioCCF(Date dataEnvioCCF)
   {
      this.dataEnvioCCF = dataEnvioCCF;
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_DATA_ENVIO_CCF, obrigatorio = false, timestamp = true)
   public Date getDataEnvioCCF()
   {
      return dataEnvioCCF;
   }

   public String getDataEnvioCCFFormatada()
   {
      return DataUtil.formataData(getDataEnvioCCF());
   }

   public void setProtocoloCCF(long protocoloCCF)
   {
      this.protocoloCCF = protocoloCCF;
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_PROTOCOLO_CCF, obrigatorio = false)
   public long getProtocoloCCF()
   {
      return protocoloCCF;
   }

   public String getProtocoloCcfFormatado()
   {
      if (!Validador.isNumericoValido(this.protocoloCCF))
      {
         return STRING_VAZIA;
      }
      return String.valueOf(getProtocoloCCF());
   }

   public void setJustificativaAlteracao(String justificativaAlteracao)
   {
      this.justificativaAlteracao = justificativaAlteracao;
   }

   @AnotacaoAtributo(nomeColuna = CamposStatusGIAITCD.CAMPO_JUSTIFICATIVA_ALTERACAO, obrigatorio = false)
   public String getJustificativaAlteracao()
   {
      if (!Validador.isStringValida(justificativaAlteracao))
      {
         return STRING_VAZIA;
      }
      return justificativaAlteracao;
   }


   public void setLogITCDVo(LogITCDVo logITCDVo)
   {
      this.logITCDVo = logITCDVo;
   }


   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposStatusGIAITCD.CAMPO_ITCTB48_NUMR_LOG_SEQ, nomeAtributo = "codigo")
            } )
   public LogITCDVo getLogITCDVo()
   {
      if (!Validador.isObjetoValido(logITCDVo))
      {
         setLogITCDVo(new LogITCDVo());
      }
      return logITCDVo;
   }


   public void setIsPossuiStatusQuitadoeAvaliacaoExcluida(boolean isPossuiStatusQuitadoeAvaliacaoExcluida)
   {
      this.isPossuiStatusQuitadoeAvaliacaoExcluida = isPossuiStatusQuitadoeAvaliacaoExcluida;
   }

   public boolean isIsPossuiStatusQuitadoeAvaliacaoExcluida()
   {
      return isPossuiStatusQuitadoeAvaliacaoExcluida;
   }
}
