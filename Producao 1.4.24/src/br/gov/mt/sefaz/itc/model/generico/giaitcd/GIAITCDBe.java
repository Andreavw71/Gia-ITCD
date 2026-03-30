
/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDBe.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 10/12/2007
 */
package br.gov.mt.sefaz.itc.model.generico.giaitcd;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.sefaz.integracao.acessoweb.ValidaPermissaoBe;
import br.com.abaco.sefaz.integracao.arrecadacao.DarEmitidoIntegracaoVo;
import br.com.abaco.util.AbacoUtil;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.data.AbacoData;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.EmailMessagingException;
import br.com.abaco.util.exceptions.EmailParametroObrigatorioException;
import br.com.abaco.util.exceptions.EmailUnsupportedEncodingException;
import br.com.abaco.util.exceptions.ImpossivelCriptografarException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;
import br.com.abaco.util.exceptions.SenhaInvalidaException;
import br.com.abaco.util.numero.AbacoNumero;
import br.com.abaco.util.seguranca.Seguranca;
import br.com.abaco.util.smtp.AbacoEmail;
import br.com.abaco.util.smtp.EmailBean;

import br.gov.mt.sefaz.ccfiscal.integracao.DomnFlagLogico;
import br.gov.mt.sefaz.ccfiscal.integracao.DomnSituacaoDebito;
import br.gov.mt.sefaz.eprocess.integracao.DomnStatusProcesso;
import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.AvaliacaoBemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.servidor.AvaliacaoBemTributavelServidorVo;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.documentoarrecadacao.DocumentoArrecadacaoGiaNormalBe;
import br.gov.mt.sefaz.itc.model.generico.documentoarrecadacao.DocumentoArrecadacaoGiaVencidoBe;
import br.gov.mt.sefaz.itc.model.generico.documentoarrecadacao.DocumentoArrecadacaoITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioQDao;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.GIAITCDInventarioArrolamentoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogBe;
import br.gov.mt.sefaz.itc.model.log.itemhistorico.ItemHistoricoBe;
import br.gov.mt.sefaz.itc.model.log.log.LogITCDBe;
import br.gov.mt.sefaz.itc.model.log.log.LogITCDVo;
import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.PedidoRelatorioVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.bem.BemVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.AliquotaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.ParametroLegislacaoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.ParametroLegislacaoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.TipoProcessoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.TipoProcessoVo;
import br.gov.mt.sefaz.itc.util.DarException;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.calculo.CalculoITCD;
import br.gov.mt.sefaz.itc.util.data.DataUtil;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnAvaliacaoTipo;
import br.gov.mt.sefaz.itc.util.dominio.DomnEstadoCivil;
import br.gov.mt.sefaz.itc.util.dominio.DomnFuncionalidadeOrigem;
import br.gov.mt.sefaz.itc.util.dominio.DomnGrupoPermissao;
import br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSituacaoProcessamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAvaliacao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoEprocess;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoEmail;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.integracao.acessoweb.AcessoWebBe;
import br.gov.mt.sefaz.itc.util.integracao.acessoweb.UsuarioIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.UFIntegracaoVO;
import br.gov.mt.sefaz.itc.util.integracao.ccfiscal.CcFiscalBe;
import br.gov.mt.sefaz.itc.util.integracao.ccfiscal.DebitoIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.ccfiscal.FiltroConsultaDebitoIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.contacorrente.ContaCorrenteBe;
import br.gov.mt.sefaz.itc.util.integracao.contacorrente.ContaCorrenteIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.eprocess.EprocessBe;
import br.gov.mt.sefaz.itc.util.integracao.eprocess.ProcessoIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.GestaoPessoasBe;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;
import br.gov.mt.sefaz.itc.util.log.ExibirLOG;
import br.gov.mt.sefaz.itc.util.validador.ValidadorAvaliacao;
import br.gov.mt.sefaz.itc.util.validador.ValidadorStatusGIAITCD;

import java.io.IOException;

import java.net.InetAddress;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.net.InetAddress;

import java.text.SimpleDateFormat;

import sefaz.mt.cadastro.dominio.DomnTipoDocumento;
import sefaz.mt.util.SefazData;
import sefaz.mt.util.SefazDataHora;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.excecao.ValidacaoException;

/**
 * Classe de negócio para a entidade GIA-ITCD
 * @author Daniel Balieiro
 * @implemented by Daniel Balieiro
 */
public class GIAITCDBe extends AbstractBe
{

   /**
    * Para as regras de negócio da GIA é necessário que seja conhecido o código 
    * do municipio da Capital de Mato Grosso, Cuiabá.
    * @implemented by Lucas Nascimento
    */
   public static final int CODIGO_CAPITAL = 90000;

   /**
    * Construtor genérico sem parametro.
    * 
    * @throws SQLException
    * @implemented by Leandro Dorileo
    */
   public GIAITCDBe() throws SQLException
   {
      super();
   }

   /**
    * Construtor recebendo uma conexao.
    * @param connection conexão com o banco de dados
    * @implemented by Leandro Dorileo
    */
   public GIAITCDBe(Connection connection)
   {
      super(connection);
   }

   /**
    * Este método consulta uma GIAITCD e verifica se a mesma possui status e o status é protocolado
    * 
    * @param gia
    * @return GIAITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultarGIAITCDAvaliar(final GIAITCDVo gia) throws ConsultaException, 
                                                                        ObjetoObrigatorioException, 
                                                                        ConexaoException, 
                                                                        ParametroObrigatorioException, 
                                                                        IntegracaoException, SQLException, 
             IOException
   {
      GIAITCDVo giaConsultada = consultarGIAITCDAtiva(gia);
      /** FE: 4:1 */
      if (!Validador.isNumericoValido(giaConsultada.getCodigo()))
      {
         throw new ConsultaException(MensagemErro.VALIDAR_GIA_ITCD_NAO_EXISTE);
      }
      return giaConsultada;
   }

   /**
    * @param giaITCDVo
    * @throws ParametroObrigatorioException
    * @throws ObjetoObrigatorioException
    * @throws IntegracaoException
    * @throws ConsultaException
    * @throws RegistroExistenteException
    * @throws ConexaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws DadoNecessarioInexistenteException
    * @throws PersistenciaException
    * @throws LogException
    * @throws AnotacaoException
    * @throws ImpossivelCriptografarException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void confirmarGIAITCD(final GIAITCDVo giaITCDVo) throws ParametroObrigatorioException, 
                                                                               ObjetoObrigatorioException, 
                                                                               IntegracaoException, 
                                                                               ConsultaException, 
                                                                               RegistroExistenteException, 
                                                                               ConexaoException, 
                                                                               RegistroNaoPodeSerUtilizadoException, 
                                                                               DadoNecessarioInexistenteException, 
                                                                               PersistenciaException, 
                                                                               LogException, 
                                                                               AnotacaoException, 
                                                                               ImpossivelCriptografarException, 
             IOException
   {
      Validador.validaObjeto(giaITCDVo);
      valiarDadoGIAITCD(giaITCDVo);  
      adequarCollectionGIAITCDTemporaria(giaITCDVo);
      giaITCDVo.setMensagemConfirmarGia(getMensagemConfirmado(giaITCDVo));
      giaITCDVo.setGiaConfirmada(new DomnSimNao(DomnSimNao.SIM));
      giaITCDVo.getTemporarioVo().setGiaConfirmada(new DomnSimNao(DomnSimNao.SIM));
      solicitarManterGIAITCD(giaITCDVo);
   }
   
   /**
    * @param giaITCDVo
    * @throws ParametroObrigatorioException
    * @throws ObjetoObrigatorioException
    * @throws IntegracaoException
    * @throws ConsultaException
    * @throws RegistroExistenteException
    * @throws ConexaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws DadoNecessarioInexistenteException
    * @throws PersistenciaException
    * @throws LogException
    * @throws AnotacaoException
    * @throws ImpossivelCriptografarException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void confirmarGIAITCDParaImpressao(final GIAITCDVo giaITCDVo) throws ParametroObrigatorioException, 
                                                                               ObjetoObrigatorioException, 
                                                                               IntegracaoException, 
                                                                               ConsultaException, 
                                                                               RegistroExistenteException, 
                                                                               ConexaoException, 
                                                                               RegistroNaoPodeSerUtilizadoException, 
                                                                               DadoNecessarioInexistenteException, 
                                                                               PersistenciaException, 
                                                                               LogException, 
                                                                               AnotacaoException, 
                                                                               ImpossivelCriptografarException, 
             IOException
   {
      Validador.validaObjeto(giaITCDVo);
      valiarDadoGIAITCD(giaITCDVo);  
      adequarCollectionGIAITCDTemporaria(giaITCDVo);
      giaITCDVo.setMensagemConfirmarGia(getMensagemConfirmado(giaITCDVo));
      //giaITCDVo.setGiaConfirmada(new DomnSimNao(DomnSimNao.SIM));
      //giaITCDVo.getTemporarioVo().setGiaConfirmada(new DomnSimNao(DomnSimNao.SIM));
      giaITCDVo.setAlterarParaPendenteProtocolo(new DomnSimNao(DomnSimNao.SIM));
      giaITCDVo.getTemporarioVo().setAlterarParaPendenteProtocolo(new DomnSimNao(DomnSimNao.SIM));
      solicitarManterGIAITCD(giaITCDVo);
   }
   
   private void valiarDadoGIAITCD(final GIAITCDVo giaITCDVo) throws ParametroObrigatorioException, ObjetoObrigatorioException, 
         IntegracaoException, ConsultaException, RegistroExistenteException, ConexaoException, 
         RegistroNaoPodeSerUtilizadoException
   {
      if (!Validador.isNumericoValido(giaITCDVo.getMunicipioProtocolar().getCodgMunicipio().intValue()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_MUNICIPIO);
      }
      if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.DOACAO))
      {
         (new GIAITCDDoacaoBe(conn)).validarIncluirGIAITCDDoacao((GIAITCDDoacaoVo) giaITCDVo);
      } else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
      {
         (new GIAITCDInventarioArrolamentoBe(conn)).validaParametrosIncluirGIAITCDInventarioArrolamento((GIAITCDInventarioArrolamentoVo) giaITCDVo);
      } else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
      {
         (new GIAITCDSeparacaoDivorcioBe(conn)).validarIncluirAlterarGIAITCDSeparacaoDivorcio((GIAITCDSeparacaoDivorcioVo) giaITCDVo);
      }
   }
   

   /**
    * <b>Objetivo:</b>
    * Este método tem por objetivo a montagem da mensagem de confimação.
    * 
    * 
    * <br>
    * <b>Rotina:</b>
    * <ol>
    * <li>.</li>
    * </ol>
    * 
    * <b>Validações:</b>
    * <ol>
    * <li>.</li>
    * </ol>  
    *    
    * 
    * @param gia
    * @return String
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public String getMensagemConfirmado(GIAITCDVo gia) throws ObjetoObrigatorioException, 
                                                             ConexaoException, 
                                                             ConsultaException, 
                                                             IntegracaoException, 
                                                             ParametroObrigatorioException
   {
      StringBuffer str = new StringBuffer();
      str.append("O prazo de vencimento para protocolar a GIA-ITCD: ");
      str.append(gia.getTemporarioVo().getPrazoProtocolarFormatado());
      str.append(". ");
      str.append("<BR>");
      ConfiguracaoGerencialParametrosVo parametroConfiguracao = null;
      if(gia.getTipoProtocoloGIA().is( DomnTipoProtocolo.PROTOCOLO_AUTOMATICO ))
      {
         parametroConfiguracao = new ConfiguracaoGerencialParametrosBe(conn).obterValorItem(ConfiguracaoITCD.PARAMETRO_INFORMACOES_PROTOCOLO_AUTOMATICO_GIA_ITCD);
      }else
      {
         parametroConfiguracao = new ConfiguracaoGerencialParametrosBe(conn).obterValorItem(ConfiguracaoITCD.PARAMETRO_INFORMACOES_PROTOCOLO_GIA_ITCD);
      }
      gia.setMensagemConfirmarGia(parametroConfiguracao.getValorItemFormatado());
      //gia.setAgenciaProtocolo(obterAgenfaProtocolo(gia));
      str.append(gia.getMensagemConfirmarGia());
      str.append("<BR>");
      return str.append(getListaDocumentos(gia)).toString();
   }

   /**
    * Método que retorna a lista de documentos, em formato String, para a mensagem de confirmação da GIA-ITCD
    * 
    * @param gia
    * @return String
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private String getListaDocumentos(GIAITCDVo gia) throws ObjetoObrigatorioException, 
                                                           ConexaoException, 
                                                           ConsultaException
   {
      String DESCRICAO_ITEM = null;
      if (gia instanceof GIAITCDInventarioArrolamentoVo)
      {
         DESCRICAO_ITEM = ConfiguracaoITCD.PARAMETRO_LISTA_DOCUMENTOS_GIA_INVENTARIO_ARROLAMENTO;
      } else if (gia instanceof GIAITCDDoacaoVo)
      {
         DESCRICAO_ITEM = ConfiguracaoITCD.PARAMETRO_LISTA_DOCUMENTOS_GIA_DOACAO;
      } else if (gia instanceof GIAITCDSeparacaoDivorcioVo)
      {
         DESCRICAO_ITEM = ConfiguracaoITCD.PARAMETRO_LISTA_DOCUMENTOS_GIA_SEPARACAO;
      }
      if (DESCRICAO_ITEM != null)
      {
         ConfiguracaoGerencialParametrosVo configs = new ConfiguracaoGerencialParametrosBe(conn).obterValorItem(DESCRICAO_ITEM);
         return configs.getValorItem();
      }
      return "";
   }

   /**
    * <b>Objetivo:</b>
    * Este método tem por objetivo direcionar a GIA para ser manipulada como temporária ou permanente.
    * 
    * 
    * <br>
    * <b>Rotina:</b>
    * <ol>
    * <li>Verifica o status atual da GIA.</li>
    * <li>Decide se o status atual é de uma GIA temporária ou permenente.</li>
    * </ol>
    * 
    * <b>Validações:</b>
    * <ol>
    * <li>giaITCDVo != null.</li>
    * </ol>  
    *
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws DadoNecessarioInexistenteException
    * @throws RegistroExistenteException
    * @throws PersistenciaException
    * @throws LogException
    * @throws AnotacaoException
    * @throws ImpossivelCriptografarException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void solicitarManterGIAITCD(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                        ParametroObrigatorioException, 
                                                                        ConsultaException, 
                                                                        ConexaoException, 
                                                                        IntegracaoException, 
                                                                        DadoNecessarioInexistenteException, 
                                                                        RegistroExistenteException, 
                                                                        PersistenciaException, 
                                                                        LogException, 
                                                                        AnotacaoException, 
                                                                        ImpossivelCriptografarException, 
                                                                        RegistroNaoPodeSerUtilizadoException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDBe.alterarAutomaticamenteTipoProtocoloGia( giaITCDVo  );
      if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLADO) || giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR) || giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR) || giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELA_GIOR) || verificaUsuarioPossuiPermissao(giaITCDVo, new DomnGrupoPermissao[]
            { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GIOR) }))
      {
         manterGIAITCDPermanente(giaITCDVo);
      } else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO) || giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.EM_ELABORACAO))
      {
         manterGIAITCD(giaITCDVo);
      }
   }

   /**
    * Método responsável por manter uma gia permanente.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws DadoNecessarioInexistenteException
    * @throws RegistroExistenteException
    * @throws PersistenciaException
    * @throws LogException
    * @throws AnotacaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void manterGIAITCDPermanente(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                      ParametroObrigatorioException, 
                                                                                      ConsultaException, 
                                                                                      ConexaoException, 
                                                                                      IntegracaoException, 
                                                                                      DadoNecessarioInexistenteException, 
                                                                                      RegistroExistenteException, 
                                                                                      PersistenciaException, 
                                                                                      LogException, 
                                                                                      AnotacaoException, 
                                                                                      RegistroNaoPodeSerUtilizadoException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      gerarDemonstrativoCalculo(giaITCDVo);
      ServidorSefazIntegracaoVo servidorSefazIntegracaoVo = new ServidorSefazIntegracaoVo();
      servidorSefazIntegracaoVo.setNumrMatricula(StringUtil.toLongWrapper(giaITCDVo.getLogSefazVo().getUsuario().getIdentificacao()));
      servidorSefazIntegracaoVo = new GestaoPessoasBe(conn).buscarServidorSefazPorNumrMatricula(servidorSefazIntegracaoVo);
      giaITCDVo.setServidorSefazResponsavelAlteracaoVo(servidorSefazIntegracaoVo);
      alterarGIAITCD(giaITCDVo);
   }

   /**
    * Método para coordenar a persistência de dados da GIA-ITCD na tabela temporária.
    * 
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @throws ImpossivelCriptografarException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void manterGIAITCDAlterar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                   ParametroObrigatorioException, 
                                                                                   ConsultaException, 
                                                                                   ConexaoException, 
                                                                                   IntegracaoException, 
                                                                                   LogException, 
                                                                                   AnotacaoException, 
                                                                                   PersistenciaException, 
                                                                                   ImpossivelCriptografarException, 
                                                                                   RegistroNaoPodeSerUtilizadoException, 
                                                                                   RegistroExistenteException, 
             IOException
   {
      Validador.validaObjeto(giaITCDVo);
      if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLADO))
      {
         //validar campos obrigatorios.

      } else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO))
      {
         manterGIAITCD(giaITCDVo);
      }
   }

   /**
    * Método para coordenar a persistência de dados da GIA-ITCD na tabela temporária.
    * 
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @throws ImpossivelCriptografarException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void manterGIAITCD(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                            ParametroObrigatorioException, 
                                                                            ConsultaException, 
                                                                            ConexaoException, 
                                                                            IntegracaoException, 
                                                                            LogException, 
                                                                            AnotacaoException, 
                                                                            PersistenciaException, 
                                                                            ImpossivelCriptografarException, 
                                                                            RegistroNaoPodeSerUtilizadoException, 
                                                                            RegistroExistenteException, 
             IOException
   {
      Validador.validaObjeto(giaITCDVo);
      if (!Validador.isNumericoValido(giaITCDVo.getCodigo()))
      {
         giaITCDVo.setDataCriacao(new Date());
      }
      gerarDemonstrativoCalculo(giaITCDVo);
	  
      if (!Validador.isDominioNumericoValido(giaITCDVo.getTemporarioVo().getGiaConfirmada()))
      {
         giaITCDVo.getTemporarioVo().setGiaConfirmada(new DomnSimNao(DomnSimNao.NAO));
         giaITCDVo.setGiaConfirmada(giaITCDVo.getTemporarioVo().getGiaConfirmada());
      }
      if (!Validador.isDataValida(giaITCDVo.getTemporarioVo().getPrazoProtocolar()))
      {
         giaITCDVo.getTemporarioVo().setPrazoProtocolar(obterPrazoProtocolarGIAITCD(giaITCDVo));
      }
      if (!Validador.isNumericoValido(giaITCDVo.getCodigo()))
      {
         incluirGIAITCDTemporario(giaITCDVo);
         giaITCDVo.setMensagemConfirmarGia(getMensagemConfirmado(giaITCDVo));
         giaITCDVo.setCodigo(giaITCDVo.getTemporarioVo().getCodigo());
      } else
      {
         giaITCDVo.setMensagemConfirmarGia(getMensagemConfirmado(giaITCDVo));
         alterarGIAITCDTemporario(giaITCDVo);
      }

   }

   /**
    * Método para consultar o último Status da GIA
    * 
    * @param giaITCDVo
    * @return StatusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public StatusGIAITCDVo obterStatusAnteriorGIAITCD(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                       ParametroObrigatorioException, 
                                                                                       ConsultaException
   {
      Validador.validaObjeto(giaITCDVo);
      // objeto criado para garantir que somente os dados necessários serão enviados a consulta
      StatusGIAITCDVo statusConsulta = new StatusGIAITCDVo();
      statusConsulta.setStatusGIAITCD(giaITCDVo.getStatusVo().getStatusGIAITCD());
      statusConsulta.getGiaITCDVo().setCodigo(giaITCDVo.getCodigo());
      return new StatusGIAITCDBe(conn).obterStatusGIAITCDAnterior(statusConsulta);
   }

   private Date obterDataCotacaoUPF(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                      ConsultaException
   {
      if (isGiaPossuiStatus(giaITCDVo, DomnStatusGIAITCD.AVALIADO))
      {
         StatusGIAITCDVo parametro = new StatusGIAITCDVo();
         StatusGIAITCDVo consulta = new StatusGIAITCDVo(parametro);
         parametro.getGiaITCDVo().setCodigo(giaITCDVo.getCodigo());
         parametro.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO));
         new StatusGIAITCDBe(conn).consultarStatusGIAITCD(consulta);
         if (Validador.isDataValida(consulta.getDataCadastroAvaliacao()) && !Validador.isNumericoValido(consulta.getServidorSefazResponsavelAlteracao().getNumrMatricula()))
         {
            return consulta.getDataCadastroAvaliacao();
         }
         return consulta.getDataAtualizacao();
      }
      return new Date();
   }

   /**
    * Método de início do Demonstrativo de Cálculo.
    * 
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
    //@Deprecated
   public void gerarDemonstrativoCalculo(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                           ParametroObrigatorioException, 
                                                                           ConsultaException, 
                                                                           ConexaoException, 
                                                                           IntegracaoException
   {
      Validador.validaObjeto(giaITCDVo);
      
      SefazDataHora dataCotacaoUPF = new SefazDataHora(obterDataCotacaoUPF(giaITCDVo));
      ContaCorrenteIntegracaoVo contaCorrenteIntegracaoVo = new ContaCorrenteIntegracaoVo();
      contaCorrenteIntegracaoVo.setDataCotacaoUPF(dataCotacaoUPF);
      ContaCorrenteBe contaCorrenteBe = null;
      try
      {
         // Não utiliza conexão ITCD.
         contaCorrenteBe = new ContaCorrenteBe(conn);
         contaCorrenteIntegracaoVo = (contaCorrenteBe).getValorUPF(contaCorrenteIntegracaoVo);
      } catch (SQLException e)
      {
         throw new ConexaoException(e.getMessage());
      } finally
      {
         if (contaCorrenteBe != null)
         {
            contaCorrenteBe.close();
            contaCorrenteBe = null;
         }
      }
      
      if(giaITCDVo.getStatusVo().getStatusGIAITCD().getTextoCorrente().equals(DomnStatusGIAITCD.DESC_EM_ELABORACAO)){
         giaITCDVo.setValorUPF(contaCorrenteIntegracaoVo.getValorUPF().doubleValue());            
      }
      
      
      ParametroLegislacaoVo parametroLegislacaoVo = new ParametroLegislacaoVo();
      parametroLegislacaoVo.setConsultaCompleta(true);
      //(new ParametroLegislacaoBe(conn)).consultarParametroLegislacaoAtual(parametroLegislacaoVo);
      parametroLegislacaoVo = (new ParametroLegislacaoBe(conn)).consultarParametroLegislacaoAtualPorData(giaITCDVo.getDataCriacao(), true);
      giaITCDVo.setParametroLegislacaoVo(parametroLegislacaoVo);
      switch (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente())
      {
         case DomnTipoProcesso.DOACAO:
            (new GIAITCDDoacaoBe(conn)).gerarDemonstrativoCalculo((GIAITCDDoacaoVo) giaITCDVo);
            break;
         case DomnTipoProcesso.INVENTARIO_ARROLAMENTO:
            (new GIAITCDInventarioArrolamentoBe(conn)).gerarDemonstrativoCalculo((GIAITCDInventarioArrolamentoVo) giaITCDVo);
            break;
         case DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA:
            GIAITCDSeparacaoDivorcioVo giaItcdSeparacaoDivorcioVo = (GIAITCDSeparacaoDivorcioVo) giaITCDVo;
            parametroLegislacaoVo = (new ParametroLegislacaoBe(conn)).consultarParametroLegislacaoAtualPorData(giaItcdSeparacaoDivorcioVo.getDataSeparacao(), true);
            giaItcdSeparacaoDivorcioVo.setParametroLegislacaoVo(parametroLegislacaoVo);
            (new GIAITCDSeparacaoDivorcioBe(conn)).gerarDemonstrativoCalculo(giaItcdSeparacaoDivorcioVo);
            break;
      }
   }

   /**
    * Inclui no banco de dados o registro de uma GIAITCD na forma de temporária.
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @throws ConexaoException
    * @implemented by Daniel Balieiro
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void alterarGIAITCDTemporario(final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                                        ConsultaException, 
                                                                                        LogException, 
                                                                                        AnotacaoException, 
                                                                                        PersistenciaException, 
                                                                                        ConexaoException, 
                                                                                        RegistroNaoPodeSerUtilizadoException, 
                                                                                        RegistroExistenteException, 
                                                                                        ParametroObrigatorioException, 
                                                                                        IntegracaoException, IOException
   {
      Validador.validaObjeto(giaitcdVo);
      try
      {
         Date prazoProtocolar = null;
         if (Validador.isObjetoValido(giaitcdVo.getTemporarioVo()))
         {
            prazoProtocolar = giaitcdVo.getTemporarioVo().getPrazoProtocolar();
         }
         DomnSimNao confirmada = giaitcdVo.getTemporarioVo().getGiaConfirmada();
         GIAITCDTemporarioVo giaTemporaria = new GIAITCDTemporarioVo();
         giaTemporaria.setGiaConfirmada(confirmada);
         giaTemporaria.setCodigo(giaitcdVo.getCodigo());
         giaTemporaria.setSenhaGIAITCD(giaitcdVo.getSenha());
         giaTemporaria.setStatusITCD(giaitcdVo.getStatusVo());
         giaTemporaria.setCodigoAutenticidade(giaitcdVo.getCodigoAutenticidade());
         
         giaTemporaria.setGiaTempXML( giaitcdVo.getTemporarioVo().getGiaTempXML() );
         giaTemporaria.setCodigoResponsavel(giaitcdVo.getResponsavelVo().getNumrContribuinte().longValue());
         //TODO Dherkyan - em alguns momentos este objeto estava nulo impedindo que gia temp fosse atualizada no BD. 
         //giaTemporaria.setNumeroVersaoGIAITCD( giaitcdVo.getTemporarioVo().getNumeroVersaoGIAITCD());
         //giaTemporaria.setTipoProtocoloGIA( giaitcdVo.getTemporarioVo().getTipoProtocoloGIA());
         giaTemporaria.setNumeroVersaoGIAITCD( giaitcdVo.getNumeroVersaoGIAITCD());
         giaTemporaria.setTipoProtocoloGIA( giaitcdVo.getTipoProtocoloGIA());
         giaTemporaria.setSituacaoProcessamento(giaitcdVo.getSituacaoProcessamento());
         giaTemporaria.setDescricaoMensagemSituacaoErrro(giaitcdVo.getDescricaoMensagemSituacaoErrro());
          if (giaitcdVo.getAlterarParaPendenteProtocolo().is(DomnSimNao.SIM))
         {
            giaTemporaria.getStatusITCD().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO));
            giaitcdVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO));
         }
         if (prazoProtocolar != null)
         {
            giaTemporaria.setPrazoProtocolar(prazoProtocolar);
         }
         GIAITCDTemporarioVo parametro = new GIAITCDTemporarioVo(giaitcdVo.getCodigo());
         GIAITCDTemporarioVo consulta = new GIAITCDTemporarioVo(parametro);
         consulta = (new GIAITCDTemporarioBe(conn)).consultaGIAITCDTemporario(consulta);
         if (consulta.isExiste())
         {
            giaTemporaria.setDataGIAITCDTemporario(consulta.getDataGIAITCDTemporario());
            giaTemporaria.setCodigoAutenticidade(consulta.getCodigoAutenticidade());
         }
         //codigo do LOG
         giaTemporaria.setLogSefazVo(giaitcdVo.getLogSefazVo());
         //fim do código do LOG
         giaitcdVo.setTemporarioVo(null);
         giaitcdVo.getStatusVo().setGiaTemporaria(null);
         giaTemporaria.setGiaitcdVo(giaitcdVo);

         (new GIAITCDTemporarioBe(conn)).alterarGIAITCDTemporario(giaTemporaria);

         if (giaitcdVo.getAlterarParaPendenteProtocolo().is(DomnSimNao.SIM))
         {
            StatusGIAITCDBe statusBe = new StatusGIAITCDBe(conn);
            StatusGIAITCDVo consultaStatus = new StatusGIAITCDVo();
            consultaStatus.getGiaTemporaria().setCodigo(giaitcdVo.getCodigo());
            if (!statusBe.verificaExisteStatusGIAITCD(consultaStatus, new DomnStatusGIAITCD(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO)))
            {
               consultaStatus = new StatusGIAITCDVo();
               consultaStatus.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO));
               consultaStatus.setLogSefazVo(giaitcdVo.getLogSefazVo());
               consultaStatus.getGiaTemporaria().setCodigo(giaTemporaria.getCodigo());
               statusBe.incluirStatusGIAITCD(consultaStatus);
               giaTemporaria.setStatusITCD(consultaStatus);
            }
         }
         giaitcdVo.setTemporarioVo(giaTemporaria);
         giaitcdVo.setStatusVo(giaTemporaria.getStatusITCD());
         commit();
      } catch (SQLException e)
      {
         try
         {
            conn.rollback();
         } catch (SQLException e1)
         {
            e1.printStackTrace();
         }
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }


   /**
    * Inclui no banco de dados o registro de uma GIAITCD na forma de temporária.
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @throws ImpossivelCriptografarException
    * @throws ConexaoException
    * @implemented by Daniel Balieiro
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void incluirGIAITCDTemporario(final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                                        LogException, 
                                                                                        AnotacaoException, 
                                                                                        PersistenciaException, 
                                                                                        ImpossivelCriptografarException, 
                                                                                        ConexaoException, 
                                                                                        RegistroNaoPodeSerUtilizadoException, 
                                                                                        IntegracaoException, 
                                                                                        ParametroObrigatorioException, 
                                                                                        ConsultaException, 
                                                                                        RegistroExistenteException, IOException
   {
      Validador.validaObjeto(giaitcdVo);
      try
      {
         Date prazoProtocolar = null;
         if (Validador.isObjetoValido(giaitcdVo.getTemporarioVo()))
         {
            prazoProtocolar = giaitcdVo.getTemporarioVo().getPrazoProtocolar();
         }
         giaitcdVo.setTemporarioVo(new GIAITCDTemporarioVo());
         giaitcdVo.getTemporarioVo().setGiaitcdVo(giaitcdVo);
         giaitcdVo.getTemporarioVo().setCodigo(giaitcdVo.getCodigo());
         giaitcdVo.getTemporarioVo().setStatusITCD(giaitcdVo.getStatusVo());
         giaitcdVo.getTemporarioVo().getStatusITCD().setDataAtualizacao(new Date());
         giaitcdVo.getTemporarioVo().setSenhaGIAITCD(giaitcdVo.getSenha());
         giaitcdVo.getTemporarioVo().setGiaConfirmada(new DomnSimNao(DomnSimNao.NAO));
         giaitcdVo.getTemporarioVo().setDataGIAITCDTemporario(giaitcdVo.getDataCriacao());
         giaitcdVo.getTemporarioVo().setLogSefazVo(giaitcdVo.getLogSefazVo());
         giaitcdVo.getTemporarioVo().setNumeroVersaoGIAITCD( giaitcdVo.getNumeroVersaoGIAITCD());
         giaitcdVo.getTemporarioVo().setTipoProtocoloGIA( giaitcdVo.getTipoProtocoloGIA());
         if (prazoProtocolar != null)
         {
            giaitcdVo.getTemporarioVo().setPrazoProtocolar(prazoProtocolar);
         }
         (new GIAITCDTemporarioBe(conn)).incluirGIAITCDTemporario(giaitcdVo.getTemporarioVo());
         giaitcdVo.setSenha(giaitcdVo.getTemporarioVo().getSenhaGIAITCD());
         commit();
      } catch (SQLException e)
      {
         try
         {
            conn.rollback();
         } catch (SQLException e1)
         {
            e1.printStackTrace();
         }
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }

   /**
    * Valida os dados necessários para a inclusão de uma GIAITCD.
    * @param giaItcdVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws RegistroExistenteException
    * @implemented by Daniel Balieiro
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validaParametrosIncluirGIAITCD(final GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, 
                                                                                 ParametroObrigatorioException, 
                                                                                 RegistroExistenteException
   {
      Validador.validaObjeto(giaItcdVo);
      validaParametrosIncluirAlterarGIAITCD(giaItcdVo);
   }

   /**
    * Valida os dados necessários para a inclusão de uma GIAITCD.
    * @param giaItcdVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws RegistroExistenteException
    * @throws DadoNecessarioInexistenteException
    * @implemented by Daniel Balieiro
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validaParametrosAlterarGIAITCD(final GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, 
                                                                                 ParametroObrigatorioException, 
                                                                                 RegistroExistenteException, 
                                                                                 DadoNecessarioInexistenteException
   {
      Validador.validaObjeto(giaItcdVo);
      if (!Validador.isNumericoValido(giaItcdVo.getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_CODIGO_ITC);
      }
      if (!Validador.isNumericoValido(giaItcdVo.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula()))
      {
         throw new DadoNecessarioInexistenteException(MensagemErro.VALIDAR_DADOS_SERVIDOR_PARA_ALTERAR_GIA_ITCD);
      }
      validaParametrosIncluirAlterarGIAITCD(giaItcdVo);
   }

   /**
    * Valida os dados necessários para a inclusão e alteração de uma GIAITCD.
    * @param giaItcdVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws RegistroExistenteException
    * @implemented by Daniel Balieiro
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validaParametrosIncluirAlterarGIAITCD(final GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, 
                                                                                        ParametroObrigatorioException, 
                                                                                        RegistroExistenteException
   {
      Validador.validaObjeto(giaItcdVo.getResponsavelVo());
      Validador.validaObjeto(giaItcdVo.getNaturezaOperacaoVo());
      validaResponsavel(giaItcdVo);
      validaNaturezaOperacao(giaItcdVo);
      validaTipoGIA(giaItcdVo);
      validaSenha(giaItcdVo);
      validaBemTributavel(giaItcdVo);
      if (giaItcdVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente() != DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA)
      {
         validaBeneficiario(giaItcdVo);
      }
   }

   /**
    * <b>Objetivo</b>
    * 
    * Este método tem por objetivo gravar um registro do tipo GIAITCDVo
    * no banco de dados.
    * 
    * @param giaITCDVo
    * 
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * 
    * @implemented by Ricardo Vitor de Oliveira Moraes, Dherkyan Ribeiro da Silva
    */
   private synchronized void incluir(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                       LogException, 
                                                                       AnotacaoException, 
                                                                       PersistenciaException
   {
      Validador.validaObjeto(giaITCDVo);
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(giaITCDVo, GIAITCDVo.class);
   }


   /**
    * Encapsula o processo de inclusão de uma GIA. A GIA pode ser do tipo Separação e Divórcio,
    * Inventário e Arrolamento ou Doação.
    * @param giaItcdVo
    * @throws ObjetoObrigatorioException
    * @throws IntegracaoException
    * @throws RegistroExistenteException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ConexaoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Daniel Balieiro
    * @implemented by Marlo Eichenberg Motta
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void incluirGIAITCD(final GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, 
                                                                             IntegracaoException, 
                                                                             RegistroExistenteException, 
                                                                             ConsultaException, 
                                                                             ParametroObrigatorioException, 
                                                                             RegistroNaoPodeSerUtilizadoException, 
                                                                             ConexaoException, 
                                                                             LogException, 
                                                                             PersistenciaException, 
                                                                             AnotacaoException
   {
      Validador.validaObjeto(giaItcdVo);
      try
      {
         try
         {
            synchronized (GIAITCDVo.class)
            {
               validaParametrosIncluirGIAITCD(giaItcdVo);
               giaItcdVo.setDataAtualizacaoBD(new Date());               
               ExibirLOG.exibirLogSimplificado("DATA CRIACAO:" + giaItcdVo.getDataCriacaoFormatada());
               ExibirLOG.exibirLogSimplificado("DATA ATUALIZACAO:" + giaItcdVo.getDataAtualizacaoFormatada());
               incluir(giaItcdVo);
			   // Incluir  Bens tributáveis
               BemTributavelBe bemTributavelBe = new BemTributavelBe(conn);
               giaItcdVo.getBemTributavelVo().setGiaITCDVo(giaItcdVo);
               giaItcdVo.getBemTributavelVo().setLogSefazVo(giaItcdVo.getLogSefazVo());
               bemTributavelBe.incluirBemTributavelIncluirGIAITCD(giaItcdVo.getBemTributavelVo());
               // passamos então a execução para a implementação específica de cada GIA
               if (giaItcdVo instanceof GIAITCDInventarioArrolamentoVo)
               {
                  GIAITCDInventarioArrolamentoBe be = new GIAITCDInventarioArrolamentoBe(conn);
                  be.incluirGIAITCDInventarioArrolamento((GIAITCDInventarioArrolamentoVo) giaItcdVo);
               } else if (giaItcdVo instanceof GIAITCDDoacaoVo)
               {
                  GIAITCDDoacaoBe be = new GIAITCDDoacaoBe(conn);
                  be.incluirGIAITCDDoacao((GIAITCDDoacaoVo) giaItcdVo);
               } else if (giaItcdVo instanceof GIAITCDSeparacaoDivorcioVo)
               {
                  GIAITCDSeparacaoDivorcioBe be = new GIAITCDSeparacaoDivorcioBe(conn);
                  be.incluirGIAITCDSeparacaoDivorcio((GIAITCDSeparacaoDivorcioVo) giaItcdVo);
               }
               //commit();
               giaItcdVo.setMensagem(MensagemSucesso.INCLUIR);
            }
         } catch (IntegracaoException e)
         {
            conn.rollback();
            throw e;
         } catch (RegistroExistenteException e)
         {
            conn.rollback();
            throw e;
         } catch (ConsultaException e)
         {
            conn.rollback();
            throw e;
         } catch (ParametroObrigatorioException e)
         {
            conn.rollback();
            throw e;
         } catch (RegistroNaoPodeSerUtilizadoException e)
         {
            conn.rollback();
            throw e;
         } catch (ObjetoObrigatorioException e)
         {
            conn.rollback();
            throw e;
         } catch (LogException e)
         {
            conn.rollback();
            throw e;
         } catch (PersistenciaException e)
         {
            conn.rollback();
            throw e;
         } catch (AnotacaoException e)
         {
            conn.rollback();
            throw e;
         } catch (RuntimeException e)
         {
            conn.rollback();
            throw e;
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }

   /**
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void alterar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                       LogException, 
                                                                       AnotacaoException, 
                                                                       PersistenciaException
   {
      Validador.validaObjeto(giaITCDVo);

      //TESTE
      try
      {
         GIAITCDVo giaITCDParamVo = new GIAITCDVo();
         giaITCDParamVo.setCodigo(giaITCDVo.getCodigo());
         GIAITCDVo giaITCDConsultaVo = new GIAITCDVo(giaITCDParamVo);
         if(giaITCDVo.getTipoProtocoloGIA() != null && giaITCDVo.getTipoProtocoloGIA().is(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO)) 
         {
            giaITCDConsultaVo.setLogSefazVo(giaITCDVo.getLogSefazVo());
         }
         giaITCDConsultaVo = consultarGIAITCD(giaITCDConsultaVo);
         if (!giaITCDConsultaVo.getSenha().equals(giaITCDVo.getSenha()))
         {
            ExibirLOG.exibirLogSimplificado( "As senhas são diferentes" );
            ExibirLOG.exibirLog( "Senha no Banco de Dados: " + giaITCDConsultaVo.getSenha() + "Senha Atual: " + giaITCDVo.getSenha() );
           
         }
      } catch (Exception e)
      {
         e.printStackTrace();
      }

      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(giaITCDVo, GIAITCDVo.class);
   }


   /**
    * Método responsável por alterar GIA ITCD quando funcionalidade alterar for solicitada por um Servidor SEFAZ.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws DadoNecessarioInexistenteException
    * @throws IntegracaoException
    * @throws ConexaoException
    * @throws RegistroExistenteException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws PersistenciaException
    * @throws LogException
    * @throws AnotacaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void alterarGIAITCD(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                              DadoNecessarioInexistenteException, 
                                                                              IntegracaoException, 
                                                                              ConexaoException, 
                                                                              RegistroExistenteException, 
                                                                              ConsultaException, 
                                                                              ParametroObrigatorioException, 
                                                                              PersistenciaException, 
                                                                              LogException, 
                                                                              AnotacaoException, 
                                                                              RegistroNaoPodeSerUtilizadoException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      BemTributavelBe bemTributavelBe = new BemTributavelBe(conn);
      try
      {
         try
         {
            synchronized (GIAITCDVo.class)
            {
               validaParametrosAlterarGIAITCD(giaITCDVo);
               giaITCDVo.setDataAtualizacaoBD(new Date());
               alterar(giaITCDVo);

               //Incluir / Alterar Bem Tributável
               giaITCDVo.getBemTributavelVo().setGiaITCDVo(giaITCDVo);
               giaITCDVo.getBemTributavelVo().setLogSefazVo(giaITCDVo.getLogSefazVo());
               bemTributavelBe.alterarBemTributavelAlterarGIAITCD(giaITCDVo.getBemTributavelVo());

               // passamos então a execução para a implementação específica de cada GIA
               if (giaITCDVo instanceof GIAITCDInventarioArrolamentoVo)
               {
                  GIAITCDInventarioArrolamentoBe be = new GIAITCDInventarioArrolamentoBe(conn);
                  be.alterarGIAITCDInventarioArrolamento((GIAITCDInventarioArrolamentoVo) giaITCDVo);
               } else if (giaITCDVo instanceof GIAITCDDoacaoVo)
               {
                  GIAITCDDoacaoBe be = new GIAITCDDoacaoBe(conn);
                  be.alterarGIAITCDDoacao((GIAITCDDoacaoVo) giaITCDVo);
               } else if (giaITCDVo instanceof GIAITCDSeparacaoDivorcioVo)
               {
                  GIAITCDSeparacaoDivorcioBe be = new GIAITCDSeparacaoDivorcioBe(conn);
                  be.alterarGIAITCDSeparacaoDivorcio((GIAITCDSeparacaoDivorcioVo) giaITCDVo);
               }
               //Excluir Bem Tributável
               giaITCDVo.getBemTributavelVo().setGiaITCDVo(giaITCDVo);
               giaITCDVo.getBemTributavelVo().setLogSefazVo(giaITCDVo.getLogSefazVo());
               bemTributavelBe.alterarBemTributavelAlterarGIAITCDExcluir(giaITCDVo.getBemTributavelVo());

               /* Verifica se alteração está sendo realizada por servidor do grupo AVALIADOR GIOR
				     * se TRUE altera o status para "Alterado pela GIOR"
				     * se FALSE altera o status para "Alterado por Servidor"
				     */
               if (verificaUsuarioPossuiPermissao(giaITCDVo, new DomnGrupoPermissao[]
                     { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GIOR) }))
               {
                  /*
                    * Codigo abaixo substituido para permitir que STATUS repita a cada alteracao feita
                    * por um servidor constando os dados referente a alteracao
                    */
                  // if(!giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELA_GIOR))                
                  giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.ALTERADO_PELA_GIOR));
                  giaITCDVo.getStatusVo().setLogSefazVo(giaITCDVo.getLogSefazVo());
                  giaITCDVo.getStatusVo().setServidorSefazResponsavelAlteracao(giaITCDVo.getServidorSefazResponsavelAlteracaoVo());
                  alterarStatusGIAITCDManual(giaITCDVo);
                  /*
                    * Codigo abaixo substituido para permitir que STATUS repita a cada alteracao feita
                    * por um servidor constando os dados referente a alteracao
                    */
                  //}else if(!giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR))
               } else
               {
                  giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR));
                  giaITCDVo.getStatusVo().setLogSefazVo(giaITCDVo.getLogSefazVo());
                  giaITCDVo.getStatusVo().setServidorSefazResponsavelAlteracao(giaITCDVo.getServidorSefazResponsavelAlteracaoVo());
                  alterarStatusGIAITCDManual(giaITCDVo);
               }


               // -------------------------------------------------------- INICIO ROTINA DE LOG ----------------------------------------------------------------  

               // Verifica se o status que es tá sendo incluido é DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR ou DomnStatusGIAITCD.ALTERADO_PELA_GIOR
               // caso seja cria um registro de LOG senão não cria o Registro de LOG.
               if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR) | giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELA_GIOR))
               {

                  new LogITCDBe(conn).rotinaProcessamentoLOG(giaITCDVo);

               }

               // ----------------------------------------------------------- FIM ROTINA DE LOG ----------------------------------------------------------------

               commit();
               giaITCDVo.setMensagem(MensagemSucesso.ALTERAR);
            }
         } catch (RegistroExistenteException e)
         {
            conn.rollback();
            throw e;
         } catch (DadoNecessarioInexistenteException e)
         {
            conn.rollback();
            throw e;
         } catch (ConsultaException e)
         {
            conn.rollback();
            throw e;
         } catch (ParametroObrigatorioException e)
         {
            conn.rollback();
            throw e;
         } catch (ObjetoObrigatorioException e)
         {
            conn.rollback();
            throw e;
         } catch (PersistenciaException e)
         {
            conn.rollback();
            throw e;
         } catch (LogException e)
         {
            conn.rollback();
            throw e;
         } catch (AnotacaoException e)
         {
            conn.rollback();
            throw e;
         } catch (IntegracaoException e)
         {
            conn.rollback();
            throw e;
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }


   /**
    * Método para consultar somente GIA-ITCD de Inventário Arrolamento. Caso a GIA-ITCD encontrada 
    * não for desse tipo, o mesmo objeto que foi passado como parametro será retornado.
    * 
    * @param giaITCDInventarioArrolamentoVo
    * @return GIAITCDInventarioArrolamentoVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDInventarioArrolamentoVo consultarGIAITCDInventarioArrolamento(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
                                                                                                                                                           ConsultaException, 
                                                                                                                                                           ConexaoException, 
                                                                                                                                                           ParametroObrigatorioException, 
                                                                                                                                                           IntegracaoException, SQLException, 
             IOException
   {
      Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
      GIAITCDVo giaConsulta = consultarGIAITCDAtiva(giaITCDInventarioArrolamentoVo);
      if (giaConsulta instanceof GIAITCDInventarioArrolamentoVo)
      {
         return (GIAITCDInventarioArrolamentoVo) giaConsulta;
      } else
      {
         return giaITCDInventarioArrolamentoVo;
      }
   }

   /**
    * Método para consultar somente GIA-ITCD de Separação Divórcio. Caso a GIA-ITCD encontrada 
    * não for desse tipo, o mesmo objeto que foi passado como parametro será retornado.
    * 
    * @param giaITCDSeparacaoDivorcioVo
    * @return GIAITCDSeparacaoDivorcioVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws ImpossivelCriptografarException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDSeparacaoDivorcioVo consultarGIAITCDSeparacaoDivorcio(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
                                                                                                                                           ConsultaException, 
                                                                                                                                           ConexaoException, 
                                                                                                                                           ParametroObrigatorioException, 
                                                                                                                                           IntegracaoException, 
                                                                                                                                           ImpossivelCriptografarException, SQLException, IOException
   {
      Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
      GIAITCDVo giaConsulta = consultarGIAITCDAtiva(giaITCDSeparacaoDivorcioVo);
      if (giaConsulta instanceof GIAITCDSeparacaoDivorcioVo)
      {
         return (GIAITCDSeparacaoDivorcioVo) giaConsulta;
      } else
      {
         return giaITCDSeparacaoDivorcioVo;
      }
   }

   /**
    * Método para consultar somente GIA-ITCD de Doação. Caso a GIA-ITCD encontrada 
    * não for desse tipo, o mesmo objeto que foi passado como parametro será retornado.
    * 
    * @param giaITCDDoacaoVo
    * @return GIAITCDDoacaoVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws ImpossivelCriptografarException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDDoacaoVo consultarGIAITCDDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
                                                                                               ConsultaException, 
                                                                                               ConexaoException, 
                                                                                               ParametroObrigatorioException, 
                                                                                               IntegracaoException, 
                                                                                               ImpossivelCriptografarException, SQLException, IOException
   {
      Validador.validaObjeto(giaITCDDoacaoVo);
      GIAITCDVo giaConsulta = consultarGIAITCDAtiva(giaITCDDoacaoVo);
      if (giaConsulta instanceof GIAITCDDoacaoVo)
      {
         return (GIAITCDDoacaoVo) giaConsulta;
      } else
      {
         return giaITCDDoacaoVo;
      }
   }

   /**
    * Método para listar GIA-ITCDs de acordo com um array de domínios de Status e qual a Agenfa de Protoloco.
    * 
    * @param arrayStatus
    * @param codigoAgenfa
    * @return GIAITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo listarGIAITCDPorConjuntoStatus(DomnStatusGIAITCD[] arrayStatus, int codigoAgenfa) throws ConsultaException, 
                                                                                                             ObjetoObrigatorioException, 
                                                                                                             ConexaoException, 
                                                                                                             ParametroObrigatorioException, 
                                                                                                             IntegracaoException, SQLException, 
             IOException
   {
      StatusGIAITCDVo statuses = (new StatusGIAITCDBe(conn)).listarStatusGIAITCDPorGrupoAgenfa(arrayStatus, codigoAgenfa);
      GIAITCDVo giaRetorno = new GIAITCDVo();
      for (Iterator iteStatus = statuses.getCollVO().iterator(); iteStatus.hasNext(); )
      {
         StatusGIAITCDVo statusGia = (StatusGIAITCDVo) iteStatus.next();
         GIAITCDVo giaConsulta = new GIAITCDVo();
         giaConsulta.setCodigo(statusGia.getGiaITCDVo().getCodigo());
         giaConsulta = new GIAITCDVo(giaConsulta);
         giaConsulta.setConsultaCompleta(true);
         giaRetorno.getCollVO().add(consultaGIAITCDBasico(giaConsulta));
      }
      return giaRetorno;
   }

   /**
    * Método para listar as GIAs de um determinado CPF
    * 
    * @param giaItcdVo
    * @return GIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws ConexaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo listarGIAITCDPorCPFResponsavel(GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, 
                                                                               ConsultaException, 
                                                                               ParametroObrigatorioException, 
                                                                               IntegracaoException, 
                                                                               ConexaoException
   {
      Validador.validaObjeto(giaItcdVo);
      GIAITCDVo giaRetorno = new GIAITCDVo();
      if (giaItcdVo.isConsultaParametrizada())
      {
         final long numeroContribuinte = (giaItcdVo.getParametroConsulta()).getResponsavelVo().getNumrContribuinte().longValue();
         Map<Long, GIAITCDVo> listaGias = new TreeMap<Long, GIAITCDVo>();
         ContribuinteIntegracaoVo responsavel = new ContribuinteIntegracaoVo();
         responsavel.setNumrContribuinte(new Long(numeroContribuinte));
         responsavel = new ContribuinteIntegracaoVo(responsavel);
         responsavel = (new CadastroBe(conn)).obterContribuinte(responsavel);
         if (!Validador.isNumericoValido(responsavel.getNumrContribuinte().longValue()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_NAO_EXISTE_CONTRIBUINTE);
         }
         GIAITCDQDao giaQDao = new GIAITCDQDao(conn);
         GIAITCDVo giaConsulta = new GIAITCDVo();
         giaConsulta.setResponsavelVo(responsavel);
         giaConsulta = new GIAITCDVo(giaConsulta);
         giaQDao.listGIAITCDPorCPFResponsavel(giaConsulta);
         for (Iterator it = giaConsulta.getCollVO().iterator(); it.hasNext(); )
         {
            GIAITCDVo atual = (GIAITCDVo) it.next();
            Long codigo = new Long(atual.getCodigo());
            if (!listaGias.containsKey(codigo))
            {
               listaGias.put(codigo, atual);
            }
         }
         GIAITCDTemporarioVo temp = new GIAITCDTemporarioVo();
         temp.setCodigoResponsavel(numeroContribuinte);
         temp = new GIAITCDTemporarioVo(temp);
         temp = (new GIAITCDTemporarioBe(conn)).listarGIAITCDTemporario(temp);
         // se foi encontrada GIA com o código informado
         for (Iterator iteGia = temp.getCollVO().iterator(); iteGia.hasNext(); )
         {
            GIAITCDTemporarioVo giaTemporario = (GIAITCDTemporarioVo) iteGia.next(); 
            GIAITCDVo giaItcdXml = giaTemporario.getGiaitcdVo();
            
            if(giaTemporario.getStatusITCD() != null){
               giaItcdXml.setStatusVo(giaTemporario.getStatusITCD());
            }
            
            Long codigo = new Long(giaTemporario.getCodigo());

            if (!listaGias.containsKey(codigo)){
               listaGias.put(codigo,adequarCollectionGIAITCDTemporaria(giaItcdXml));
            }
         }
         // se foi encontrada GIA com o código informado
         if (listaGias.isEmpty())
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_NAO_EXISTE_CONTRIBUINTE);
         } else
         {
            giaRetorno.getCollVO().addAll(listaGias.values());
            giaRetorno.ordenaGiasPorCodigoDecrescente();
         }
      } else
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_CONSULTA);
      }
      return giaRetorno;
   }

   /**
    * Método responsável por consultar uma GIAITCD.
    * Será retornada uma nova instancia de GIAITCDVo, com os dados pesquisados.
    * 
    * @param giaITCDVo
    * @return GIAITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Marlo Eichenberg Motta
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultarGIAITCDAtiva(GIAITCDVo giaITCDVo) throws ConsultaException, 
                                                                      ObjetoObrigatorioException, 
                                                                      ConexaoException, 
                                                                      ParametroObrigatorioException, 
                                                                      IntegracaoException, SQLException, 
             IOException
   {
      GIAITCDVo giaConsulta = consultarGIAITCD(giaITCDVo);
      if (giaConsulta.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.INATIVO))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CONSULTAR_GIA_ITCD_STATUS_INATIVO);
      }
      return giaConsulta;
   }

   /**
    * Método responsável por consultar uma GIAITCD.
    * Será retornada uma nova instancia de GIAITCDVo, com os dados pesquisados.
    * 
    * @param giaItcdVo
    * @return GIAITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Marlo Eichenberg Motta
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultarGIAITCD(GIAITCDVo giaItcdVo) throws ConsultaException, 
                                                                 ObjetoObrigatorioException, 
                                                                 ConexaoException, 
                                                                 ParametroObrigatorioException, 
                                                                 IntegracaoException, SQLException, 
             IOException
   {
      GIAITCDVo giaConsulta = new GIAITCDVo();
      giaConsulta.setLogSefazVo(giaItcdVo.getLogSefazVo());
      if(giaItcdVo.getTipoProtocoloGIA() != null && giaItcdVo.getTipoProtocoloGIA().is(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO)) 
      {
         giaConsulta.setLogSefazVo(giaItcdVo.getLogSefazVo());
      }
      if (!(Validador.isNumericoValido(giaItcdVo.getLogSefazVo().getUsuario().getCodigo()) || Validador.isNumericoValido(giaItcdVo.getUsuarioLogado())))
      {
         giaConsulta = consultarGIAITCDGeral(giaItcdVo);
      } else
      {
         giaConsulta = consultaGIAITCDBasico(giaItcdVo);
      }
      
      return giaConsulta;
   }

   /**
    * @param giaItcdVo
    * @return GIAITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Marlo Eichenberg Motta
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultarGIAITCDNotificada(GIAITCDVo giaItcdVo) throws ConsultaException, 
                                                                           ObjetoObrigatorioException, 
                                                                           ConexaoException, 
                                                                           ParametroObrigatorioException, 
                                                                           IntegracaoException, 
                                                                           LogException, 
                                                                           AnotacaoException, 
                                                                           PersistenciaException, SQLException, 
             IOException
   {
      GIAITCDVo giaConsulta = new GIAITCDVo();
      giaConsulta = consultarGIAITCD(giaItcdVo);
      if (!Validador.isNumericoValido(giaConsulta.getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_NAO_ENCONTRADA);
      } else if (!giaConsulta.getStatusVo().isStatusInCollVo(new int[]
            { DomnStatusGIAITCD.NOTIFICADO }))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CONSULTAR_GIA_ITCD_STATUS_NOTIFICADO);
      }
         
       //--------------------------------------------------------Validar a Reimpressão--------------------------------------------------------------------------
       if(new AvaliacaoBemTributavelBe(conn).validarStatusImpressao(giaConsulta.getBemTributavelVo() , new DomnSimNao(DomnSimNao.NAO) ))
       {
          throw new ParametroObrigatorioException(  "Avaliação dessa GIA-ITCD não finalizada."  );
       }
       if(new AvaliacaoBemTributavelBe(conn).validarAvalicaoStatusAvaliacao(giaConsulta.getBemTributavelVo() , new DomnAtivoInativo(DomnAtivoInativo.INATIVO) ))
       {
          throw new ParametroObrigatorioException(  "Não existe Avaliação dessa GIA-ITCD."  );
       }
       
      return giaConsulta;
   }

   /**
    * Método responsável por verificar se uma GIA-ITCD possui um determinado status.
    * @param giaITCDVo
    * @param statusGIA
    * @return boolean
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public boolean isGiaPossuiStatus(GIAITCDVo giaITCDVo, int statusGIA) throws ObjetoObrigatorioException, 
                                                                               ConsultaException
   {
      Validador.validaObjeto(giaITCDVo);
      StatusGIAITCDVo statusConsulta = new StatusGIAITCDVo();
      StatusGIAITCDVo statusParametro = new StatusGIAITCDVo();
      statusParametro.getGiaITCDVo().setCodigo(giaITCDVo.getCodigo());
      statusParametro.setStatusGIAITCD(new DomnStatusGIAITCD(statusGIA));
      statusConsulta.setParametroConsulta(statusParametro);
      statusConsulta = new StatusGIAITCDBe(conn).consultarStatusGIAITCD(statusConsulta);
      if (Validador.isNumericoValido(statusConsulta.getCodigo()))
      {
         return true;
      }
      return false;
   }

   /**
    * Método responsável por consultar uma GIA-ITCD validando se a mesma possui o status de Retificada.
    * @param giaItcdVo
    * @return GIAITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Marlo Eichenberg Motta
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultarGIAITCDRetificada(GIAITCDVo giaItcdVo) throws ConsultaException, 
                                                                           ObjetoObrigatorioException, 
                                                                           ConexaoException, 
                                                                           ParametroObrigatorioException, 
                                                                           IntegracaoException, 
                                                                           LogException, 
                                                                           AnotacaoException, 
                                                                           PersistenciaException, SQLException, 
             IOException
   {
      GIAITCDVo giaConsulta = new GIAITCDVo();
      giaConsulta = consultarGIAITCD(giaItcdVo);
      if (!Validador.isNumericoValido(giaConsulta.getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_NAO_ENCONTRADA);
      } else if (!giaConsulta.getStatusVo().isStatusInCollVo(new int[]
            { DomnStatusGIAITCD.RETIFICADO }))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CONSULTAR_GIA_ITCD_STATUS_RETIFICADO);
      }
      
      //--------------------------------------------------------------Validar a Reimpressão---------------------------------------------------------------------
      if(new AvaliacaoBemTributavelBe(conn).validarStatusImpressao(giaConsulta.getBemTributavelVo() , new DomnSimNao(DomnSimNao.NAO) ))
      {
         throw new ParametroObrigatorioException(  "Avaliação dessa GIA-ITCD não finalizada."  );
      }
      if(new AvaliacaoBemTributavelBe(conn).validarAvalicaoStatusAvaliacao(giaConsulta.getBemTributavelVo() , new DomnAtivoInativo(DomnAtivoInativo.INATIVO) ))
      {
         throw new ParametroObrigatorioException(  "Não existe Avaliação dessa GIA-ITCD."  );
      }
      
      return giaConsulta;
   }

   /**
    * Método responsável por consultar uma GIAITCD Inativa.
    * Será retornada uma nova instancia de GIAITCDVo, com os dados pesquisados.
    * 
    * @param giaItcdVo
    * @return GIAITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Marlo Eichenberg Motta
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultarGIAITCDInativa(GIAITCDVo giaItcdVo) throws ConsultaException, 
                                                                        ObjetoObrigatorioException, 
                                                                        ConexaoException, 
                                                                        ParametroObrigatorioException, 
                                                                        IntegracaoException, SQLException, 
             IOException
   {
      GIAITCDVo giaConsulta = new GIAITCDVo();
      giaConsulta = consultaGIAITCDBasico(giaItcdVo);
      if (!giaConsulta.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.INATIVO) && !giaConsulta.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.INATIVADO_AUTOMATICAMENTE))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CONSULTAR_GIA_ITCD_STATUS_NAO_INATIVO);
      }
      return giaConsulta;
   }

   /**
    * Método responsável por consultar uma GIAITCD.
    * Será retornada uma nova instancia de GIAITCDVo, com os dados pesquisados.
    * 
    * @param giaItcdVo
    * @return GIAITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Marlo Eichenberg Motta
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultarGIAITCDAutenticidade(GIAITCDVo giaItcdVo) throws ConsultaException, 
                                                                              ObjetoObrigatorioException, 
                                                                              ConexaoException, 
                                                                              ParametroObrigatorioException, 
                                                                              IntegracaoException, SQLException, 
             IOException
   {
      GIAITCDVo giaConsulta = new GIAITCDVo();
      giaConsulta = consultaGIAITCDBasico(giaItcdVo);

      return giaConsulta;
   }

   /**
    * Efetua a consulta de uma GIA-ITCD, não validando se o usuário é ou não Lotado
    * 
    * @param giaItcdVo
    * @return GIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultarGIAITCDUsuarioNaoLotado(GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, 
                                                                                 ConsultaException, 
                                                                                 ConexaoException, 
                                                                                 ParametroObrigatorioException, 
                                                                                 IntegracaoException, SQLException, 
             IOException
   {
      return consultaGIAITCDBasico(giaItcdVo);
   }

   /**
    * Método responsável por consultar uma GIAITCD.
    * Será retornada uma nova instancia de GIAITCDVo, com os dados pesquisados.
    * 
    * @param giaItcdVo
    * @return GIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultaGIAITCDBasico(GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, 
                                                                      ConsultaException, 
                                                                      ConexaoException, 
                                                                      ParametroObrigatorioException, 
                                                                      IntegracaoException, SQLException, 
             IOException
   {
      GIAITCDVo giaConsulta = null; //VO que vai ser consultado e retornado.
      Validador.validaObjeto(giaItcdVo);
      if (!giaItcdVo.isConsultaParametrizada())
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_CONSULTA);
      }
      if (!Validador.isNumericoValido((giaItcdVo.getParametroConsulta()).getCodigo()) && !Validador.isNumericoValido((giaItcdVo.getParametroConsulta()).getNumrDeclaracaoFatoGerador()) && !Validador.isNumericoValido((giaItcdVo.getParametroConsulta()).getNumrDeclaracaoIsencao()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CONSULTAR_GIA_ITCD_CODIGO);
      }
      giaConsulta = consultaGiaPermanente(giaItcdVo);
      if (!Validador.isNumericoValido(giaConsulta.getCodigo()))
      {
         giaConsulta = consultaGIATemporaria(giaItcdVo);
      }
      if (!Validador.isNumericoValido(giaConsulta.getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_NAO_EXISTE);
      }
      
      obterListaStatus(giaConsulta);
      giaConsulta.setGiaAvaliada(StatusGIAITCDBe.isGiaPossuiStatus(giaConsulta.getStatusVo(), DomnStatusGIAITCD.AVALIADO));
      giaConsulta.setGiaParcelada(StatusGIAITCDBe.isStatusAnterior(giaConsulta.getStatusVo(), DomnStatusGIAITCD.PARCELADO));
      
      try{
         ConfiguracaoGerencialParametrosVo configuracaoConsultaDataInicial = new ConfiguracaoGerencialParametrosVo();
         configuracaoConsultaDataInicial.setCodigo(65);
         configuracaoConsultaDataInicial = new ConfiguracaoGerencialParametrosVo(configuracaoConsultaDataInicial);
           
         ConfiguracaoGerencialParametrosBe configuracaoGerencialParametrosBe = new ConfiguracaoGerencialParametrosBe(conn);
         configuracaoGerencialParametrosBe.consultarConfiguracaoGerencialParametros(configuracaoConsultaDataInicial);
         String dataConfig = configuracaoConsultaDataInicial.getValorItem();
                  
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
         Date dataFixa = sdf.parse(dataConfig); 
         giaItcdVo.setDataLimiteExibirIsencaoBemTributavel(dataFixa);
         giaConsulta.setDataLimiteExibirIsencaoBemTributavel(dataFixa);
      }catch(ParseException e){
         System.out.println("Registro 65 da Configuração de parâmetro de Data Limite para exibir Isenção do Bem Tributável foi deletado da tabela do Banco de dados erro:" +e.getMessage());
      }
      
      return giaConsulta;
   }

    /**
     * <b>Objetivo</b>
     * Este método tem por objetivo recuperar os dados do LOG ITCD e
     * adicionar a cada log no devido status.
     * 
     * 
     * <b>Validações</b>
     * <ol>
     * <li>statusGIAITCDVo != null </li>
     * <li>statusGIAITCDVo.getCollVO() contém pelo menos 1 elemento</li>
     * </ol>
     * 
     * 
     * @param giaITCDVo
     * 
     */
   private void obterListaLogITCD(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                    ConsultaException
   {
      LogITCDVo logITCDVo = new LogITCDVo();
      new LogITCDBe(conn).obterLogPorStatus(giaITCDVo.getStatusVo());
      new HistoricoLogBe(conn).obterHistoricoLogVoPorStatusITCD(giaITCDVo.getStatusVo());
      new ItemHistoricoBe(conn).obterItemHistoricoLogVoPorStatusITCD(giaITCDVo.getStatusVo());
   }

   private void obterListaStatus(final GIAITCDVo giaITCDVo) throws ConsultaException, 
                                                                   ObjetoObrigatorioException, 
                                                                   IntegracaoException
   {
      StatusGIAITCDVo parametro = new StatusGIAITCDVo();
      StatusGIAITCDVo consulta = new StatusGIAITCDVo();
      parametro.getGiaITCDVo().setCodigo(giaITCDVo.getCodigo());
      parametro.getGiaTemporaria().setCodigo(giaITCDVo.getCodigo());
      consulta.setParametroConsulta(parametro);
      consulta.setConsultaCompleta(true);
      StatusGIAITCDBe statusBe = new StatusGIAITCDBe(conn);
      consulta = statusBe.listarStatusGIAITCD(consulta);
      if (Validador.isCollectionValida(consulta.getCollVO()))
      {
         giaITCDVo.getStatusVo().setCollVO(consulta.getCollVO());
      }
      //
      giaITCDVo.getStatusVo().setIsPossuiStatusQuitadoeAvaliacaoExcluida( statusBe.isPossuiStatusQuitadoeAvaliacaoExcluida(consulta) );
      
      // Buscar LOG de Operacoes.
      obterListaLogITCD(giaITCDVo);
      
      
   }

   /**
    * Efetua consulta de GIA-ITCD nas tabelas permanentes.
    * @param giaItcdVo
    * @return GIAITCDVo
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ObjetoObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultaGiaPermanente(final GIAITCDVo giaItcdVo) throws ConsultaException, 
                                                                            ParametroObrigatorioException, 
                                                                            ConexaoException, 
                                                                            IntegracaoException, 
                                                                            ObjetoObrigatorioException, 
             SQLException, IOException

   {
      Validador.isObjetoValido(giaItcdVo);
      GIAITCDVo giaRetorno = null;
      GIAITCDQDao giaQDao = new GIAITCDQDao(conn);
      CadastroBe cadastroBe = new CadastroBe(conn);
      giaRetorno = new GIAITCDVo((giaItcdVo.getParametroConsulta()).getCodigo());
      giaRetorno.setNumrDeclaracaoFatoGerador((giaItcdVo.getParametroConsulta()).getNumrDeclaracaoFatoGerador());
      giaRetorno.setNumrDeclaracaoIsencao((giaItcdVo.getParametroConsulta()).getNumrDeclaracaoIsencao());
      giaRetorno = new GIAITCDVo(giaRetorno);
      if (!Validador.isDominioNumericoValido((giaItcdVo.getParametroConsulta()).getNaturezaOperacaoVo().getTipoProcesso()))
      {
         if (!Validador.isNumericoValido((giaItcdVo.getParametroConsulta()).getNaturezaOperacaoVo().getCodigo()))
         {
            //Faz a primeira consulta para retornar o Código da Natureza de Operação
            giaQDao.findGIAITCD(giaRetorno);
            (giaItcdVo.getParametroConsulta()).setNaturezaOperacaoVo(giaRetorno.getNaturezaOperacaoVo());
         } else
         {
            giaRetorno.setNaturezaOperacaoVo((giaItcdVo.getParametroConsulta()).getNaturezaOperacaoVo());
         }
         NaturezaOperacaoVo naturezaConsulta = new NaturezaOperacaoVo(giaRetorno.getNaturezaOperacaoVo().getCodigo());
         naturezaConsulta = new NaturezaOperacaoVo(naturezaConsulta);
         (new NaturezaOperacaoBe(conn)).consultarNaturezaOperacao(naturezaConsulta);
         giaRetorno.setNaturezaOperacaoVo(naturezaConsulta);
      } else
      {
         giaRetorno.getNaturezaOperacaoVo().setTipoProcesso((giaItcdVo.getParametroConsulta()).getNaturezaOperacaoVo().getTipoProcesso());
      }
      if (Validador.isNumericoValido((giaItcdVo.getParametroConsulta()).getNaturezaOperacaoVo().getCodigo()))
      {
         //Conforme a consulta inicial qual o tipo da gia que será instânciado para a consulta completa
         if (giaRetorno.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
         {
            giaRetorno = new GIAITCDInventarioArrolamentoVo((giaItcdVo.getParametroConsulta()).getCodigo());
            giaRetorno = new GIAITCDInventarioArrolamentoVo((GIAITCDInventarioArrolamentoVo) giaRetorno);
         } else if (giaRetorno.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.DOACAO))
         {
            giaRetorno = new GIAITCDDoacaoVo((giaItcdVo.getParametroConsulta()).getCodigo());
            giaRetorno = new GIAITCDDoacaoVo((GIAITCDDoacaoVo) giaRetorno);
         } else if (giaRetorno.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
         {
            giaRetorno = new GIAITCDSeparacaoDivorcioVo((giaItcdVo.getParametroConsulta()).getCodigo());
            giaRetorno = new GIAITCDSeparacaoDivorcioVo((GIAITCDSeparacaoDivorcioVo) giaRetorno);
         }
         /* Setando os demais campos de consulta */
         (giaRetorno.getParametroConsulta()).setCodigoAutenticidade((giaItcdVo.getParametroConsulta()).getCodigoAutenticidade());
         (giaRetorno.getParametroConsulta()).setNumrDeclaracaoFatoGerador((giaItcdVo.getParametroConsulta()).getNumrDeclaracaoFatoGerador());
         (giaRetorno.getParametroConsulta()).setNumrDeclaracaoIsencao((giaItcdVo.getParametroConsulta()).getNumrDeclaracaoIsencao());
      }
      giaRetorno.setConsultaCompleta(true);
      giaQDao.findGIAITCD(giaRetorno);
      if (Validador.isNumericoValido(giaRetorno.getCodigo()))
      {
         giaRetorno.setGiaConfirmada(new DomnSimNao(DomnSimNao.SIM));
         if (giaItcdVo.isConsultaCompleta())
         {
            // RESPONSAVEL
            if (Validador.isNumericoValido(giaRetorno.getResponsavelVo().getNumrContribuinte()))
            {
               ContribuinteIntegracaoVo responsavelConsulta = new ContribuinteIntegracaoVo(giaRetorno.getResponsavelVo());
               giaRetorno.setResponsavelVo(cadastroBe.obterContribuinte(responsavelConsulta));
            }
            // PROCURADOR
            if (Validador.isNumericoValido(giaRetorno.getProcuradorVo().getNumrContribuinte()))
            {
               ContribuinteIntegracaoVo procuradorConsulta = new ContribuinteIntegracaoVo(giaRetorno.getProcuradorVo());
               giaRetorno.setProcuradorVo(cadastroBe.obterContribuinte(procuradorConsulta));
            }
            // NATUREZA OPERACAO
            if (Validador.isNumericoValido(giaRetorno.getNaturezaOperacaoVo().getCodigo()))
            {
               NaturezaOperacaoVo naturezaConsulta = new NaturezaOperacaoVo(giaRetorno.getNaturezaOperacaoVo());
               giaRetorno.setNaturezaOperacaoVo((new NaturezaOperacaoBe(conn)).consultarNaturezaOperacao(naturezaConsulta));
               giaRetorno.getNaturezaOperacaoVo().setCollVO((new NaturezaOperacaoBe(conn)).listarNaturezaOperacao(naturezaConsulta).getCollVO());
            }
            // PARAMETRO LEGISLACAO
            if (Validador.isNumericoValido(giaRetorno.getParametroLegislacaoVo().getCodigo()))
            {
               ParametroLegislacaoVo parametroConsulta = new ParametroLegislacaoVo(giaRetorno.getParametroLegislacaoVo());
               parametroConsulta.setConsultaCompleta(true);
               giaRetorno.setParametroLegislacaoVo((new ParametroLegislacaoBe(conn)).consultarParametroLegislacao(parametroConsulta));
            }
            if (Validador.isNumericoValido(giaRetorno.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula()))
            {
               giaRetorno.setServidorSefazResponsavelAlteracaoVo(new GestaoPessoasBe(conn).buscarServidorSefazPorNumrMatricula(giaRetorno.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula().longValue()));
               giaRetorno.getServidorSefazResponsavelAlteracaoVo().setUnidadeSefaz(new GestaoPessoasBe(conn).buscarUnidadeSefazPorCodigo(giaRetorno.getServidorSefazResponsavelAlteracaoVo().getUnidadeSefaz().getCodgUnidade().intValue()));

            }
            // BEM TRIBUTAVEL
            BemTributavelVo bemTributavelConsulta = new BemTributavelVo();
            bemTributavelConsulta.setGiaITCDVo(giaRetorno);
            bemTributavelConsulta = new BemTributavelVo(bemTributavelConsulta);
            bemTributavelConsulta.setConsultaCompleta(true);
            bemTributavelConsulta = (new BemTributavelBe(conn)).listarItemBemTributavel(bemTributavelConsulta);
            if (!bemTributavelConsulta.getCollVO().isEmpty())
            {
               giaRetorno.setBemTributavelVo(bemTributavelConsulta);
            }
            // MUNICIPIO PROTOCOLAR
            if (Validador.isNumericoValido(giaRetorno.getMunicipioProtocolar().getCodgMunicipio()))
            {
               giaRetorno.setMunicipioProtocolar(cadastroBe.obterMunicipioPorCodigo(giaRetorno.getMunicipioProtocolar()));
               giaRetorno.getTemporarioVo().setPrazoProtocolar(obterPrazoProtocolarGIAITCD(giaRetorno));
            } // GIA DAR				 

            GIAITCDDarVo giaDarConsulta = new GIAITCDDarVo();
            giaDarConsulta.setGia(giaRetorno);
            giaDarConsulta = new GIAITCDDarVo(giaDarConsulta);
            giaRetorno.setGiaITCDDarVo((new GIAITCDDarBe(conn)).consultarUltimoGIAITCDDar(giaDarConsulta));            
         
         }


         // STATUS GIA
         StatusGIAITCDVo statusGia = new StatusGIAITCDVo();
         statusGia.getGiaITCDVo().setCodigo(giaRetorno.getCodigo());
         statusGia = new StatusGIAITCDVo(statusGia);
         statusGia = (new StatusGIAITCDBe(conn)).consultarStatusGIAITCD(statusGia);

         if (Validador.isNumericoValido(statusGia.getCodigo()))
         {
            giaRetorno.setStatusVo(statusGia);
            GestaoPessoasBe gestaoPessoasBe = new GestaoPessoasBe(conn);
            if (Validador.isNumericoValido(statusGia.getCodigoAgenfa()))
            {
               giaRetorno.setAgenciaProtocolo(gestaoPessoasBe.buscarUnidadeSefazPorCodigo(statusGia.getCodigoAgenfa()));
            }
            if (Validador.isNumericoValido(statusGia.getCodigoUnidadeAvaliacao()))
            {
               giaRetorno.setAgenciaAvaliacao(gestaoPessoasBe.buscarUnidadeSefazPorCodigo(statusGia.getCodigoUnidadeAvaliacao()));
            }
            if (Validador.isNumericoValido(statusGia.getServidor().getNumrMatricula()))
            {
               statusGia.setServidor(gestaoPessoasBe.buscarServidorSefazPorNumrMatricula(statusGia.getServidor().getNumrMatricula().longValue()));
               statusGia.getServidor().setUnidadeSefaz(gestaoPessoasBe.buscarUnidadeSefazPorCodigo(statusGia.getServidor().getUnidadeSefaz().getCodgUnidade().intValue()));
            }
            if (Validador.isNumericoValido(statusGia.getServidorSefazResponsavelAlteracao().getNumrMatricula()))
            {
               statusGia.setServidorSefazResponsavelAlteracao(gestaoPessoasBe.buscarServidorSefazPorNumrMatricula(statusGia.getServidorSefazResponsavelAlteracao().getNumrMatricula().longValue()));
               statusGia.getServidorSefazResponsavelAlteracao().setUnidadeSefaz(gestaoPessoasBe.buscarUnidadeSefazPorCodigo(statusGia.getServidorSefazResponsavelAlteracao().getUnidadeSefaz().getCodgUnidade().intValue()));
            }
            gestaoPessoasBe = null;
         }

         // passamos então a execução para a implementação específica de cada GIA
         if (giaRetorno.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
         {
            giaRetorno.setParametroConsulta(new GIAITCDInventarioArrolamentoVo(giaRetorno.getCodigo()));
            giaRetorno.setConsultaCompleta(true);
            (new GIAITCDInventarioArrolamentoBe(conn)).consultarGIAITCDInventarioArrolamento((GIAITCDInventarioArrolamentoVo) giaRetorno);
         } else if (giaRetorno.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.DOACAO))
         {
            giaRetorno.setParametroConsulta(new GIAITCDDoacaoVo(giaRetorno.getCodigo()));
            giaRetorno.setConsultaCompleta(true);
            (new GIAITCDDoacaoBe(conn)).consultarGIAITCDDoacao((GIAITCDDoacaoVo) giaRetorno);
         } else if (giaRetorno.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
         {
            giaRetorno.setParametroConsulta(new GIAITCDSeparacaoDivorcioVo(giaRetorno.getCodigo()));
            giaRetorno.setConsultaCompleta(true);
            (new GIAITCDSeparacaoDivorcioBe(conn)).consultarGIAITCDSeparacaoDivorcio((GIAITCDSeparacaoDivorcioVo) giaRetorno);
         }
      }
      if (Validador.isNumericoValido(giaRetorno.getCodigo()))
      {
         gerarDemonstrativoCalculo(giaRetorno);
      }
      return giaRetorno;
   }


   /**
    * Efetua a consulta de uma GIA-ITCD Temporária
    * @param giaItcdVo
    * @return GIAITCDVo
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private GIAITCDVo consultaGIATemporaria(final GIAITCDVo giaItcdVo) throws ConsultaException, 
                                                                             ParametroObrigatorioException, 
                                                                             ObjetoObrigatorioException, 
                                                                             ConexaoException, 
                                                                             IntegracaoException
   {
      Validador.isObjetoValido(giaItcdVo);
      GIAITCDVo giaRetorno = null;
      
      GIAITCDVo controlePorcentagemAconsiderar = new GIAITCDVo();
      
      GIAITCDTemporarioVo temp = new GIAITCDTemporarioVo();
      temp.setCodigo((giaItcdVo.getParametroConsulta()).getCodigo());
      if (Validador.isStringValida((giaItcdVo.getParametroConsulta()).getCodigoAutenticidade()))
      {
         temp.setCodigoAutenticidade((giaItcdVo.getParametroConsulta()).getCodigoAutenticidade());
      }
      temp = new GIAITCDTemporarioVo(temp);
      giaRetorno = (new GIAITCDTemporarioBe(conn)).consultaGIAITCDTemporario(temp).getGiaitcdVo();
      
      GIAITCDTemporarioVo retorno = (new GIAITCDTemporarioBe(conn)).consultaGIAITCDTemporario(temp);      
      giaRetorno = retorno.getGiaitcdVo();
      giaRetorno.setSituacaoProcessamento(retorno.getSituacaoProcessamento());
      giaRetorno.setDescricaoMensagemSituacaoErrro(retorno.getDescricaoMensagemSituacaoErrro());
      
      if(controlePorcentagemAconsiderar.getPorcentagemAconsiderar() != giaRetorno.getPorcentagemAconsiderar()){
         giaRetorno.setPorcentagemAconsiderar(controlePorcentagemAconsiderar.getPorcentagemAconsiderar());
      }
      
      giaRetorno.getStatusVo().setStatusGIAITCD(giaRetorno.getTemporarioVo().getStatusITCD().getStatusGIAITCD());
      validarSeExisteDoacaoSucessivaNaoInformadaAnt(giaRetorno); 
      adequarCollectionGIAITCDTemporaria(giaRetorno);
      return giaRetorno;
   }
   
   public void validarSeExisteDoacaoSucessivaNaoInformadaAnt(GIAITCDVo giaRetorno)
         throws ObjetoObrigatorioException, ConsultaException
      {
         giaRetorno.setExisteDoacaoSucessivaNaoRegParaBenef(false);
         if(!giaRetorno.getBeneficiarioVo().getCollVO().isEmpty()){
            Iterator iteBeneficiario = giaRetorno.getBeneficiarioVo().getCollVO().iterator();
            while (iteBeneficiario.hasNext()) {
                Object obj = iteBeneficiario.next();
                
                if (obj instanceof GIAITCDDoacaoBeneficiarioVo) {
                    GIAITCDDoacaoBeneficiarioVo beneficiarioAnterior = (GIAITCDDoacaoBeneficiarioVo) obj;               
                    
                    GIAITCDDoacaoSucessivaVo doacaoSucessivaVoAtual = new GIAITCDDoacaoSucessivaVo();
                    doacaoSucessivaVoAtual.setBeneficiarioVo(new BeneficiarioVo(beneficiarioAnterior.getCodigo()));
                    
                    doacaoSucessivaVoAtual = new GIAITCDDoacaoSucessivaBe(conn).consultaDoacoesSucessivasDoBenef(beneficiarioAnterior);
                    
                    if (doacaoSucessivaVoAtual.getCollVO().size() > beneficiarioAnterior.getGiaitcdDoacaoSucessivaVo().getCollVO().size()) {
                        giaRetorno.setExisteDoacaoSucessivaNaoRegParaBenef(true);
                        break;
                    }  
                }
            }
         }
      }

   /**
    * Método para tirar da Collection (CollVo) o objeto e coloca-lo no seu devido lugar
    * @param giaRetorno
    * @return GIAITCDVo
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ObjetoObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private GIAITCDVo adequarCollectionGIAITCDTemporaria(GIAITCDVo giaRetorno) throws ConsultaException, 
                                                                                     ParametroObrigatorioException, 
                                                                                     ConexaoException, 
                                                                                     IntegracaoException, 
                                                                                     ObjetoObrigatorioException
   {
      /* Os VOs que tem relacionamento 1-N tem apenas o código no Vo Principal, por tanto será necessário fazer uma consulta "completa"
		 * Os VO identificados até o momento que será necessário fazer isso são:
		 *    NaturezaOperacaoVo
		 *    ConstrucaoVo - Para Imovel Urbano
		 */
      if (Validador.isNumericoValido(giaRetorno.getNaturezaOperacaoVo().getCodigo()))
      {
         for (Iterator iteNat = giaRetorno.getNaturezaOperacaoVo().getCollVO().iterator(); iteNat.hasNext(); )
         {
            NaturezaOperacaoVo natureza = (NaturezaOperacaoVo) iteNat.next();
            if (natureza.getCodigo() == giaRetorno.getNaturezaOperacaoVo().getCodigo())
            {
               natureza.setCollVO(giaRetorno.getNaturezaOperacaoVo().getCollVO());
               giaRetorno.setNaturezaOperacaoVo(natureza);
               break;
            }
         }
      }
      for (Iterator iteBemTributavel = giaRetorno.getBemTributavelVo().getCollVO().iterator(); iteBemTributavel.hasNext(); )
      {
         BemTributavelVo bemAtual = (BemTributavelVo) iteBemTributavel.next();
         if (bemAtual.getFichaImovelVo() instanceof FichaImovelUrbanoVo)
         {
            FichaImovelUrbanoVo fichaUrbano = (FichaImovelUrbanoVo) bemAtual.getFichaImovelVo();
            fichaUrbano.setBemTributavelVo(bemAtual);
            if (Validador.isNumericoValido(fichaUrbano.getConstrucaoVo().getCodigo()))
            {
               for (Iterator iteConst = fichaUrbano.getConstrucaoVo().getCollVO().iterator(); iteConst.hasNext(); )
               {
                  ConstrucaoVo construcao = (ConstrucaoVo) iteConst.next();
                  if (construcao.getCodigo() == fichaUrbano.getConstrucaoVo().getCodigo())
                  {
                     construcao.setCollVO(fichaUrbano.getConstrucaoVo().getCollVO());
                     fichaUrbano.setConstrucaoVo(construcao);
                     break;
                  }
               }
            }
         }
      }
      if (Validador.isNumericoValido(giaRetorno.getMunicipioProtocolar().getCodgMunicipio()))
      {
         MunicipioIntegracaoVo municipioProtocolar = new MunicipioIntegracaoVo();
         municipioProtocolar.setCodgMunicipio(giaRetorno.getMunicipioProtocolar().getCodgMunicipio());
         giaRetorno.setMunicipioProtocolar(new CadastroBe(conn).obterMunicipioPorCodigo(municipioProtocolar));
      }
      return giaRetorno;
   }

   /**
    * Método responsável por consultar uma GIAITCD.
    * Será retornada uma nova instancia de GIAITCDVo, com os dados pesquisados.
    * 
    * @param giaItcdVo
    * @return GIAITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Marlo Eichenberg Motta
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultarGIAITCDGeral(GIAITCDVo giaItcdVo) throws ConsultaException, 
                                                                      ObjetoObrigatorioException, 
                                                                      ConexaoException, 
                                                                      ParametroObrigatorioException, 
                                                                      IntegracaoException, SQLException, 
             IOException
   {
      verificaUsuarioServidor(giaItcdVo);
      boolean isUsuarioServidor = giaItcdVo.isUsuarioServidor();
      
      if (!isUsuarioServidor && !Validador.isStringValida((giaItcdVo.getParametroConsulta()).getSenha()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_SENHA_GIA);  
      }

      GIAITCDVo giaConsulta = consultaGIAITCDBasico(giaItcdVo);
      if (!isUsuarioServidor)
      {
         try
         {
            String senha = Seguranca.criptografar((giaItcdVo.getParametroConsulta()).getSenha(), "senha");
            String desSenha = Seguranca.descriptografar(giaConsulta.getSenha(),  "senha");
            Seguranca.compararSenhas(senha, giaConsulta.getSenha(), MensagemErro.VALIDAR_SENHA_INFORMADA_INVALIDA);
         } catch (Exception e)
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_SENHA_INFORMADA_INVALIDA);
         }
      }
      return giaConsulta;
   }

   /**
    * Metodo para inativar GIA-ITCD que tenha passado do prazo máximo de protocolação, de acordo com a
    * Configuração Gerencial de Parametros.
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws RegistroExistenteException
    * @throws SQLException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void inativarAutomaticoGIAITCD(EntidadeVo entidadeVo) throws ConsultaException
   {
      try
      {
         GIAITCDVo giaitcdConsultaVo = new GIAITCDVo();
         (new GIAITCDTemporarioBe(conn)).listarCodigoGIAITCDTemporarioAtivas(giaitcdConsultaVo.getTemporarioVo());
         ExibirLOG.exibirLog("Quantidade inicial de GIA's para processamento - "+giaitcdConsultaVo.getTemporarioVo().getCollVO().size());
         List<Long> contadorgGIAComErro = new ArrayList<Long>();
         for (Iterator iteGia = giaitcdConsultaVo.getTemporarioVo().getCollVO().iterator(); iteGia.hasNext(); )
         {
               long codigoGia = 0;
               try
               {
                  GIAITCDTemporarioVo atual = (GIAITCDTemporarioVo) iteGia.next();
                  codigoGia = atual.getCodigo();
                  GIAITCDTemporarioVo parametro = new GIAITCDTemporarioVo(atual.getCodigo());
                  atual.setParametroConsulta(parametro);
                  new GIAITCDTemporarioQDao(conn).findGIAITCDTemporario(atual);
               
                  GIAITCDVo giaAtual = atual.getGiaitcdVo();
                  giaAtual.setDataCriacao(atual.getDataGIAITCDTemporario());
                  
                  if (validaPrazoProtocolarGIAITCD(giaAtual))
                  {  
                     ExibirLOG.exibirLog("Inicio da inativacao da GIA-ITCD : "+giaAtual.getCodigo() ,giaAtual.getCodigo());
                     
                     SefazDataHora prazoProtocolar = new SefazDataHora(giaAtual.getTemporarioVo().getPrazoProtocolar());
                     if(Validador.isObjetoValido(prazoProtocolar))
                     {
                        ExibirLOG.exibirLogSimplificado("Prazo para protocolar: "+prazoProtocolar.toString());
                     }
                     giaAtual.setLogSefazVo(entidadeVo.getLogSefazVo());
                     inativarGIAITCDAutomatico(giaAtual, new DomnStatusGIAITCD(DomnStatusGIAITCD.INATIVADO_AUTOMATICAMENTE));
                  }
               conn.commit();
               
            } catch (Exception e)
            {
               ExibirLOG.exibirLog("Falha na gia: " +codigoGia +" Erro: " +e.getMessage(), codigoGia);
               contadorgGIAComErro.add(codigoGia);
               e.printStackTrace();
            }
            
         }
         ExibirLOG.exibirLog("Total de GIA's com falha: " +contadorgGIAComErro.size());
         
         ExibirLOG.exibirLog("GIA's com falha");
           
         for (Long codigo: contadorgGIAComErro)
         {
            ExibirLOG.exibirLogSimplificado("GIA: " + codigo);
         }
         
      }catch (Exception e)
      {
         e.printStackTrace();
         throw new ConsultaException(ObjetoObrigatorioException.NAO_FOI_POSSIVEL_CONSULTAR_REGISTRO);
      }
   }

   /**
    * Método responsável por obter e retornar a lista de UF's.
    * @return Collection
    * @throws Exception
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public Collection obterListaUF() throws Exception
   {
      return (new CadastroBe(conn)).listarUf();
   }

   /**
    * Metodo responsavel por obter e retornar a lista de municipios ativos.
    * @return Collection
    * @throws SQLException
    * @throws IntegracaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public Collection obterListaMunicipios() throws SQLException, 
                                                   IntegracaoException
   {
      return (new CadastroBe(conn)).listarMunicipiosAtivos();
   }

   /**
    * Retorna o Prazo para protocolar a GIA
    * 
    * @param giaitcdVo
    * @return Date
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public Date obterPrazoProtocolarGIAITCD(final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                             ConexaoException, 
                                                                             ConsultaException, 
                                                                             ParametroObrigatorioException, 
                                                                             IntegracaoException
   {
      Validador.validaObjeto(giaitcdVo);
      if (!Validador.isNumericoValido(giaitcdVo.getMunicipioProtocolar().getCodgMunicipio().intValue()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_MUNICIPIO);
      }
      final String stringConsulta = ConfiguracaoITCD.PARAMETRO_PRAZO_VENCIMENTO_PROTOCOLAR_GIA;
      ConfiguracaoGerencialParametrosVo configsVo = new ConfiguracaoGerencialParametrosVo();
      configsVo.setDescricaoItem(stringConsulta);
      configsVo = new ConfiguracaoGerencialParametrosVo(configsVo);
      (new ConfiguracaoGerencialParametrosBe(conn)).consultarConfiguracaoGerencialParametros(configsVo);
      if (!Validador.isStringValida(configsVo.getValorItem()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_CONSULTA_PRAZO_PROTOCOLAR);
      }
      Calendar calendar = new GregorianCalendar(); // calendario para calculo de dia limite
      calendar.setTime(giaitcdVo.getDataCriacao());
      calendar.add(Calendar.DATE, Integer.parseInt(configsVo.getValorItem()));
      AbacoData abacoData = new AbacoData();
      if (abacoData.isDiaUtil(calendar.getTime(), giaitcdVo.getMunicipioProtocolar().getCodgMunicipio().intValue()))
      {
         return calendar.getTime();
      }
      return abacoData.getProximoDiaUtil(calendar.getTime(), giaitcdVo.getMunicipioProtocolar().getCodgMunicipio().intValue());
   }

   /**
    * Método que contém as validações referentes ao Prazo de Protocolação da GIA-ITCD
    * 
    * @param giaitcdVo
    * @return true caso o prazo ainda esteja vï¿½lido
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private boolean validaPrazoProtocolarGIAITCD(final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                                  ConexaoException, 
                                                                                  ConsultaException, 
                                                                                  ParametroObrigatorioException, 
                                                                                  IntegracaoException
   {
      Validador.validaObjeto(giaitcdVo);
      if (Validador.isDataValida(giaitcdVo.getTemporarioVo().getPrazoProtocolar()))
      {
         return ((new Date()).after(AbacoData.converteDataComUltimoMinutoDia(giaitcdVo.getTemporarioVo().getPrazoProtocolar())));
      }
      return false;

   }

   /**
    * Método de validação para Inativar uma GIA-ITCD 
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarInativarGIAITCD(final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                         ParametroObrigatorioException, 
                                                                         ConsultaException, 
                                                                         ConexaoException, 
                                                                         IntegracaoException, 
             SQLException, IOException
   {
      Validador.validaObjeto(giaitcdVo);
      if (!Validador.isNumericoValido(giaitcdVo.getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_CODIGO);
      }
      GIAITCDVo giaitcdConsultaVo = new GIAITCDVo();
      giaitcdConsultaVo.setCodigo(giaitcdVo.getCodigo());
      giaitcdConsultaVo.setResponsavelVo(giaitcdVo.getResponsavelVo());
      giaitcdConsultaVo = new GIAITCDVo(giaitcdConsultaVo);
      giaitcdConsultaVo.setUsuarioLogado(giaitcdVo.getUsuarioLogado());
      giaitcdConsultaVo = consultaGIAITCDBasico(giaitcdConsultaVo);
      if (!Validador.isNumericoValido(giaitcdConsultaVo.getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_NAO_EXISTE);
      }
   }

   public void inativarGIAITCDAutomatico(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                           ParametroObrigatorioException, 
                                                                           ConsultaException, 
                                                                           ConexaoException, 
                                                                           IntegracaoException, 
                                                                           RegistroNaoPodeSerUtilizadoException, 
                                                                           LogException, 
                                                                           PersistenciaException, 
                                                                           AnotacaoException, 
                                                                           RegistroExistenteException, IOException
   {
      inativarGIAITCDAutomatico(giaITCDVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.INATIVO));
   }

   /**
    * Método para inativar uma GIA
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws RegistroExistenteException
    * @throws SQLException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void inativarGIAITCDAutomatico(final GIAITCDVo giaitcdVo, DomnStatusGIAITCD status) throws ObjetoObrigatorioException, 
                                                                                                                  ParametroObrigatorioException, 
                                                                                                                  ConsultaException, 
                                                                                                                  ConexaoException, 
                                                                                                                  IntegracaoException, 
                                                                                                                  RegistroNaoPodeSerUtilizadoException, 
                                                                                                                  LogException, 
                                                                                                                  PersistenciaException, 
                                                                                                                  AnotacaoException, 
                                                                                                                  RegistroExistenteException, IOException
   {
      Validador.validaObjeto(giaitcdVo);
      Validador.validaObjeto(status);
      try
      {
         synchronized (GIAITCDVo.class)
         {
            try
            {
               validarInativarGIAITCD(giaitcdVo);
               // inativando a GIA-ITCD
               giaitcdVo.getStatusVo().setStatusGIAITCD(status);
               giaitcdVo.getStatusVo().setLogSefazVo(giaitcdVo.getLogSefazVo());
               giaitcdVo.getStatusVo().getServidor().setNumrMatricula(StringUtil.toLongWrapper(giaitcdVo.getLogSefazVo().getUsuario().getIdentificacao()));
               GIAITCDVo giaConsulta = new GIAITCDVo(giaitcdVo.getCodigo());
               giaConsulta = new GIAITCDVo(giaConsulta);
               giaConsulta.setConsultaCompleta(true);
               giaConsulta = consultaGiaPermanente(giaConsulta);
               if (!Validador.isNumericoValido(giaConsulta.getCodigo()))
               {
                  giaConsulta = new GIAITCDVo(giaitcdVo.getCodigo());
                  giaConsulta = new GIAITCDVo(giaConsulta);
                  giaConsulta = consultaGIATemporaria(giaConsulta);
                  if (Validador.isNumericoValido(giaConsulta.getCodigo()))
                  {
                     GIAITCDTemporarioVo giaTemp = giaitcdVo.getTemporarioVo();
                     giaTemp.setStatusITCD(giaitcdVo.getStatusVo());
                     giaTemp.getStatusITCD().getGiaITCDVo().setCodigo(0);
                     giaTemp.setLogSefazVo(giaitcdVo.getLogSefazVo());
                     (new GIAITCDTemporarioBe(conn)).alterarGIAITCDTemporario(giaTemp);
                     giaTemp.getStatusITCD().setGiaTemporaria(giaTemp);
                     alterarStatusGIAITCDAutomatico(giaTemp);
                     ExibirLOG.exibirLog("Inativada:  "+giaTemp.getCodigo() , giaTemp.getCodigo());
                  }
               } else
               {
                  alterarStatusGIAITCDAutomatico(giaitcdVo);
                  ExibirLOG.exibirLog("Inativada:  "+giaitcdVo.getCodigo() , giaitcdVo.getCodigo());
               }

               commit();
               giaitcdVo.setMensagem(MensagemSucesso.GIA_ITCD_INATIVACAO);
            } catch (ObjetoObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (ParametroObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (ConsultaException e)
            {
               conn.rollback();
               throw e;
            } catch (ConexaoException e)
            {
               conn.rollback();
               throw e;
            } catch (IntegracaoException e)
            {
               conn.rollback();
               throw e;
            } catch (RegistroNaoPodeSerUtilizadoException e)
            {
               conn.rollback();
               throw e;
            } catch (LogException e)
            {
               conn.rollback();
               throw e;
            } catch (PersistenciaException e)
            {
               conn.rollback();
               throw e;
            } catch (AnotacaoException e)
            {
               conn.rollback();
               throw e;
            } catch (RegistroExistenteException e)
            {
               conn.rollback();
               throw e;
            }
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }

   }

   /**
    * Método para inativar uma GIA
    * 
    * @param giaitcdVo
    * @throws ParametroObrigatorioException
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws RegistroExistenteException
    * @throws SQLException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void inativarGIAITCD(final GIAITCDVo giaitcdVo) throws ParametroObrigatorioException, 
                                                                              ObjetoObrigatorioException, 
                                                                              ConsultaException, 
                                                                              ConexaoException, 
                                                                              IntegracaoException, 
                                                                              RegistroNaoPodeSerUtilizadoException, 
                                                                              LogException, 
                                                                              PersistenciaException, 
                                                                              AnotacaoException, 
                                                                              RegistroExistenteException, IOException
   {
      if (!Validador.isNumericoValido(giaitcdVo.getStatusVo().getProtocoloDesistencia()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_PROTOCOLO_DESISTENCIA);
      }
      if (!Validador.isDataValida(giaitcdVo.getStatusVo().getDataDesistencia()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_DESISTENCIA);
      }
      if (new Date().before(giaitcdVo.getStatusVo().getDataDesistencia()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_DESISTENCIA_FUTURA);
      }
      if (!Validador.isStringValida(giaitcdVo.getStatusVo().getObservacao()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_OBSERVACAO);
      }
      if (giaitcdVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
      {
         if (!Validador.isNumericoValido(giaitcdVo.getStatusVo().getNumeroGiaSubstituta()))
         {
            throw new ParametroObrigatorioException(MensagemErro.NUMERO_GIA_SUBSTITUTA_NAO_INFORMADO);
         }
      }
      inativarGIAITCDAutomatico(giaitcdVo);
   }

   /**
    * Método para validar se a Reativação de uma GIA-ITCD Temporária pode ser feita
    * @param giaitcdVo
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarReativarGIAITCDTemporaria(GIAITCDVo consulta, GIAITCDVo giaitcdVo) throws ParametroObrigatorioException
   {
      if (!Validador.isNumericoValido(consulta.getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_CODIGO);
      }
      if (!Validador.isStringValida(giaitcdVo.getStatusVo().getJustificativaReativacao()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_STATUS_JUSTIFICATIVA_REATIVACAO);
      }
      if (!consulta.getStatusVo().isStatusIn(new int[]
            { DomnStatusGIAITCD.INATIVO, 
              DomnStatusGIAITCD.INATIVADO_AUTOMATICAMENTE }))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CONSULTAR_GIA_ITCD_STATUS_NAO_INATIVO);
      }
   }

   /**
    * Método de validação para a Reativação de uma GIA-ITCD
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws RegistroExistenteException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws DadoNecessarioInexistenteException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarReativarGIAITCDPermanente(GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                             ParametroObrigatorioException, 
                                                                             ConsultaException, 
                                                                             ConexaoException, 
                                                                             IntegracaoException, 
                                                                             RegistroExistenteException, 
                                                                             LogException, 
                                                                             PersistenciaException, 
                                                                             AnotacaoException, 
                                                                             RegistroNaoPodeSerUtilizadoException, 
                                                                             DadoNecessarioInexistenteException, SQLException, IOException
   {
      Validador.validaObjeto(giaitcdVo);
      if (!Validador.isNumericoValido(giaitcdVo.getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_CODIGO);
      }
      GIAITCDVo giaConsulta = new GIAITCDVo();
      giaConsulta.setCodigo(giaitcdVo.getCodigo());
      giaConsulta.setResponsavelVo(giaitcdVo.getResponsavelVo());
      giaConsulta = new GIAITCDVo(giaConsulta);
      giaConsulta.setUsuarioLogado(giaitcdVo.getUsuarioLogado());
      giaConsulta.setConsultaCompleta(true);
      giaConsulta = consultarGIAITCDInativa(giaConsulta);
      if (!Validador.isNumericoValido(giaConsulta.getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_NAO_EXISTE);
      }
      if (!Validador.isStringValida(giaitcdVo.getStatusVo().getJustificativaReativacao()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_STATUS_JUSTIFICATIVA_REATIVACAO);
      }
      for (Iterator iteBem = giaConsulta.getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
      {
         BemTributavelVo bem = (BemTributavelVo) iteBem.next();
         bem.setLogSefazVo(giaitcdVo.getLogSefazVo());
         (new BemTributavelBe(conn)).reativarBemTributavelBem(bem);
         ConstrucaoVo construcao = null;
         RebanhoVo rebanho = null;
         BenfeitoriaVo benfeitoria = null;
         CulturaVo cultura = null;
         if (bem.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_RURAL))
         {
            construcao = ((FichaImovelRuralVo) bem.getFichaImovelVo()).getFichaImovelRuralConstrucaoVo().getConstrucaoVo();
            rebanho = ((FichaImovelRuralVo) bem.getFichaImovelVo()).getFichaImovelRuralRebanhoVo().getRebanhoVo();
            cultura = ((FichaImovelRuralVo) bem.getFichaImovelVo()).getFichaImovelRuralCulturaVo().getCulturaVo();
         } else if (bem.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_URBANO))
         {
            construcao = ((FichaImovelUrbanoVo) bem.getFichaImovelVo()).getConstrucaoVo();
            benfeitoria = ((FichaImovelUrbanoVo) bem.getFichaImovelVo()).getFichaImovelUrbanoBenfeitoriaVo().getBenfeitoriaVo();
         }
         if (construcao != null && Validador.isNumericoValido(construcao.getCodigo()))
         {
            construcao.setLogSefazVo(giaitcdVo.getLogSefazVo());
            (new ConstrucaoBe(conn)).reativarConstrucaoVo(construcao);
         }
         if (rebanho != null && Validador.isNumericoValido(rebanho.getCodigo()))
         {
            rebanho.setLogSefazVo(giaitcdVo.getLogSefazVo());
            (new RebanhoBe(conn)).reativarRebanho(rebanho);
         }
         if (benfeitoria != null && Validador.isNumericoValido(benfeitoria.getCodigo()))
         {
            benfeitoria.setLogSefazVo(giaitcdVo.getLogSefazVo());
            (new BenfeitoriaBe(conn)).reativarBenfeitoria(benfeitoria);
         }
         if (cultura != null && Validador.isNumericoValido(cultura.getCodigo()))
         {
            cultura.setLogSefazVo(giaitcdVo.getLogSefazVo());
            (new CulturaBe(conn)).reativarCultura(cultura);
         }
      }
      if (Validador.isNumericoValido(giaConsulta.getNaturezaOperacaoVo().getCodigo()))
      {
         giaConsulta.getNaturezaOperacaoVo().setLogSefazVo(giaitcdVo.getLogSefazVo());
         (new NaturezaOperacaoBe(conn)).reativarNaturezaOperacao(giaConsulta.getNaturezaOperacaoVo());
      }
      if (Validador.isNumericoValido(giaConsulta.getParametroLegislacaoVo().getCodigo()))
      {
         giaConsulta.getParametroLegislacaoVo().setLogSefazVo(giaitcdVo.getLogSefazVo());
         (new ParametroLegislacaoBe(conn)).reativarParametroLegislacao(giaConsulta.getParametroLegislacaoVo());
      }
   }

   /**
    * Metodo para Reativar uma GIA-ITCD
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws RegistroExistenteException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws DadoNecessarioInexistenteException
    * @throws SQLException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void reativarGIAITCD(GIAITCDVo giaitcdVo, String ipClient, String portClient) throws ConsultaException, 
                                                                        LogException, 
                                                                        AnotacaoException, 
                                                                        PersistenciaException, 
                                                                        ObjetoObrigatorioException, 
                                                                        IntegracaoException, 
                                                                        ConexaoException, 
                                                                        ParametroObrigatorioException, 
                                                                        RegistroExistenteException, 
                                                                        DadoNecessarioInexistenteException, 
                                                                        RegistroNaoPodeSerUtilizadoException, IOException
   {
      Validador.validaObjeto(giaitcdVo);
      boolean ocorreuErro = false;
      try
      {
         synchronized (GIAITCDVo.class)
         {
            StatusGIAITCDVo statusBackup = giaitcdVo.getStatusVo();
            try
            {
               boolean isTemporaria = false;
               GIAITCDVo giaConsulta = new GIAITCDVo(giaitcdVo.getCodigo());
               giaConsulta = new GIAITCDVo(giaConsulta);
               giaConsulta = consultaGiaPermanente(giaConsulta);
               if (!Validador.isNumericoValido(giaConsulta.getCodigo()))
               {
                  giaConsulta = new GIAITCDVo(giaitcdVo.getCodigo());
                  giaConsulta = new GIAITCDVo(giaConsulta);
                  giaConsulta = consultaGIATemporaria(giaConsulta);
                  if (!Validador.isNumericoValido(giaConsulta.getCodigo()))
                  {
                     throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_NAO_EXISTE);
                  } else
                  {
                     validarReativarGIAITCDTemporaria(giaConsulta, giaitcdVo);
                     isTemporaria = true;
                  }
               } else
               {
                  validarReativarGIAITCDPermanente(giaitcdVo);
               }
               statusBackup = giaitcdVo.getStatusVo();
               StatusGIAITCDVo statusAnterior = obterStatusAnteriorGIAITCD(giaitcdVo);
               StatusGIAITCDVo statusAnteriorCopia = obterStatusAnteriorGIAITCD(giaitcdVo);
               statusAnterior.setJustificativaReativacao(giaitcdVo.getStatusVo().getJustificativaReativacao());
               statusAnterior.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.REATIVADO));
               statusAnterior.getServidor().setNumrMatricula(StringUtil.toLongWrapper(giaitcdVo.getLogSefazVo().getUsuario().getIdentificacao()));
               giaitcdVo.setStatusVo(statusAnterior);
               giaitcdVo.setStatusAnterior(statusBackup);
               new GIAITCDBe(conn).alterarStatusGIAITCD(giaitcdVo);
               if (isTemporaria)
               {
                  giaConsulta.getTemporarioVo().setStatusITCD(statusAnterior);
                  giaConsulta.getTemporarioVo().setLogSefazVo(giaitcdVo.getLogSefazVo());
                  giaConsulta.getTemporarioVo().setDataAtualizacao(new Date());
                  giaConsulta.getTemporarioVo().setGiaitcdVo(giaitcdVo);
                  new GIAITCDTemporarioBe(conn).alterarGIAITCDTemporario(giaConsulta.getTemporarioVo());
                  giaitcdVo.setTemporarioVo(giaConsulta.getTemporarioVo());
               }
               if (statusAnteriorCopia.isStatusIn(new int[]
                     { DomnStatusGIAITCD.NOTIFICADO_CIENTE, 
                       DomnStatusGIAITCD.RETIFICADO_CIENTE, 
                       DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE, 
                       DomnStatusGIAITCD.RATIFICADO_CIENTE }))
               {
                  StatusGIAITCDVo parametro = new StatusGIAITCDVo();
                  parametro.getGiaITCDVo().setCodigo(giaitcdVo.getCodigo());
                  parametro.setStatusGIAITCD(new DomnStatusGIAITCD(statusAnteriorCopia.getStatusGIAITCD().getValorCorrente()));
                  statusAnteriorCopia = new StatusGIAITCDBe(conn).obterStatusGIAITCDAnterior(parametro);
               } else if (!Validador.isNumericoValido(statusAnteriorCopia.getCodigo()))
               {
                  statusAnteriorCopia = statusAnterior;
                  statusAnteriorCopia.setCodigo(0);
                  if (giaConsulta.getTemporarioVo().getGiaConfirmada().is(DomnSimNao.SIM))
                  {
                     statusAnteriorCopia.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO));
                  } else
                  {
                     statusAnteriorCopia.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.EM_ELABORACAO));
                  }
                  statusAnteriorCopia.setDataAtualizacao(new Date());
               } else
               {
                  statusAnteriorCopia.setJustificativaReativacao(statusAnterior.getJustificativaReativacao());
                  statusAnteriorCopia.setDataAtualizacao(new Date());
                  statusAnteriorCopia.setCodigo(0);
               }
               giaitcdVo.setStatusVo(statusAnteriorCopia);
               new GIAITCDBe(conn).alterarStatusGIAITCD(giaitcdVo, true);
               if (isTemporaria)
               {
                  giaitcdVo.setTemporarioVo(null);
                  giaConsulta.getTemporarioVo().setStatusITCD(statusAnteriorCopia);
                  giaConsulta.getTemporarioVo().setLogSefazVo(giaitcdVo.getLogSefazVo());
                  giaConsulta.getTemporarioVo().setDataAtualizacao(new Date());
                  giaConsulta.getTemporarioVo().setGiaitcdVo(giaitcdVo);
                  new GIAITCDTemporarioBe(conn).alterarGIAITCDTemporario(giaConsulta.getTemporarioVo());
                  giaitcdVo.setTemporarioVo(giaConsulta.getTemporarioVo());
               }
               String mensagemExtra = "";
               if (isTemporaria)
               {
                  GIAITCDTemporarioVo giaTemp = giaitcdVo.getTemporarioVo();
                  if (giaitcdVo.getStatusVo().isStatusIn(new int[]
                        { DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO, 
                          DomnStatusGIAITCD.EM_ELABORACAO }))
                  {
                     int quantidadeDiasProtocoloPosReativacao = StringUtil.toInt(new ConfiguracaoGerencialParametrosBe(conn).obterValorItem(ConfiguracaoITCD.PARAMETRO_PRAZO_VENCIMENTO_PROTOCOLO_GIA_REATIVADA).getValorItem());
                     AbacoData ad = new AbacoData();
                     Date dataMaximaProtocolo = DataUtil.adicionaDia(new Date(), quantidadeDiasProtocoloPosReativacao);
                     if (!ad.isDiaUtil(dataMaximaProtocolo, giaitcdVo.getMunicipioProtocolar().getCodgMunicipio().intValue()))
                        ;
                     {
                        dataMaximaProtocolo = ad.getProximoDiaUtil(dataMaximaProtocolo, giaitcdVo.getMunicipioProtocolar().getCodgMunicipio().intValue());
                     }
                     giaTemp.setPrazoProtocolar(dataMaximaProtocolo);
                     mensagemExtra = "\nO prazo de vencimento para protocolar GIA-ITCD é " + DataUtil.formataData(dataMaximaProtocolo) + ".";
                  }
                  giaTemp.setGiaitcdVo(giaitcdVo);
                  //			         giaTemp.setStatusITCD(statusAnterior);
                  (new GIAITCDTemporarioBe(conn)).alterarGIAITCDTemporario(giaTemp);
               } else if (statusAnterior.isStatusIn(new int[]
                     { DomnStatusGIAITCD.NOTIFICADO, 
                       DomnStatusGIAITCD.NOTIFICADO_CIENTE, 
                       DomnStatusGIAITCD.RETIFICADO, 
                       DomnStatusGIAITCD.RETIFICADO_CIENTE, 
                       DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA }))
               {
                  try
                  {
                     gerarDAR(giaitcdVo,ipClient,portClient);
                  } catch (DarException e)
                  {
                     ; //mensagemExtra = "\n" + e.getMessage() + ".";
                  }
               }
               commit();
               giaitcdVo.setMensagem(MensagemSucesso.GIA_ITCD_REATIVAR + mensagemExtra);
            } catch (ConsultaException e)
            {
               ocorreuErro = true;
               conn.rollback();

               throw e;
            } catch (LogException e)
            {
               ocorreuErro = true;
               conn.rollback();
               throw e;
            } catch (AnotacaoException e)
            {
               ocorreuErro = true;
               conn.rollback();
               throw e;
            } catch (PersistenciaException e)
            {
               ocorreuErro = true;
               conn.rollback();
               throw e;
            } catch (RuntimeException e)
            {
               ocorreuErro = true;
               conn.rollback();
               throw e;
            } catch (ObjetoObrigatorioException e)
            {
               ocorreuErro = true;
               conn.rollback();
               throw e;
            } catch (IntegracaoException e)
            {
               ocorreuErro = true;
               conn.rollback();
               throw e;
            } catch (ConexaoException e)
            {
               ocorreuErro = true;
               conn.rollback();
               throw e;
            } catch (ParametroObrigatorioException e)
            {
               ocorreuErro = true;
               conn.rollback();
               throw e;
            } catch (RegistroExistenteException e)
            {
               ocorreuErro = true;
               conn.rollback();
               throw e;
            } catch (DadoNecessarioInexistenteException e)
            {
               ocorreuErro = true;
               conn.rollback();
               throw e;
            } catch (RegistroNaoPodeSerUtilizadoException e)
            {
               ocorreuErro = true;
               conn.rollback();
               throw e;
            } finally
            {
               if (ocorreuErro)
               {
                  giaitcdVo.setStatusVo(statusBackup);
               }
            }
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }

   /**
    * Método com as validações necessárias para alterar o Status de uma GIA-ITCD
    * 
    * @param giaitcdVo
    * @param statusAnterior
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarAlterarStatusGIAITCD(final GIAITCDVo giaitcdVo, StatusGIAITCDVo statusAnterior) throws ObjetoObrigatorioException, 
                                                                                                              ConsultaException, 
                                                                                                              ConexaoException, 
                                                                                                              ParametroObrigatorioException, 
                                                                                                              IntegracaoException, 
                                                                                                              RegistroNaoPodeSerUtilizadoException, SQLException, IOException
   {
      Validador.validaObjeto(giaitcdVo);
      GIAITCDVo giaConsulta = new GIAITCDVo();
      giaConsulta.setCodigo(giaitcdVo.getCodigo());
      giaConsulta.setResponsavelVo(giaitcdVo.getResponsavelVo());
      giaConsulta = new GIAITCDVo(giaConsulta);
      if (Validador.isNumericoValido(giaitcdVo.getLogSefazVo().getUsuario().getCodigo()))
      {
         giaConsulta.setUsuarioLogado((long) giaitcdVo.getLogSefazVo().getUsuario().getCodigo());
      } else
      {
         giaConsulta.setUsuarioLogado(giaitcdVo.getUsuarioLogado());
      }

      giaConsulta.setConsultaCompleta(true);
      giaConsulta.setLogSefazVo(giaitcdVo.getLogSefazVo());
      if (statusAnterior.isStatusIn(new int[]
            { DomnStatusGIAITCD.INATIVO, 
              DomnStatusGIAITCD.INATIVADO_AUTOMATICAMENTE }))
      {
         giaConsulta = consultarGIAITCDInativa(giaConsulta);
      } else
      {
         giaConsulta = consultarGIAITCDAtiva(giaConsulta);
      }
      if (!giaitcdVo.getResponsavelVo().getNumrContribuinte().equals(giaConsulta.getResponsavelVo().getNumrContribuinte()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_REMETENTE);
      }
   }

   /**
    * Método para alterar o Status de uma GIA-ITCD, manualmente.
    * 
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws SQLException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws RegistroExistenteException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void alterarStatusGIAITCD(final GIAITCDVo giaitcdVo, boolean statusAnteriorReativacao) throws ObjetoObrigatorioException, 
                                                                                                                     ParametroObrigatorioException, 
                                                                                                                     ConsultaException, 
                                                                                                                     ConexaoException, 
                                                                                                                     IntegracaoException, 
                                                                                                                     RegistroNaoPodeSerUtilizadoException, 
                                                                                                                     LogException, 
                                                                                                                     PersistenciaException, 
                                                                                                                     AnotacaoException, 
                                                                                                                     RegistroExistenteException, IOException
   {
      Validador.validaObjeto(giaitcdVo);
      try
      {
         synchronized (GIAITCDVo.class)
         {
            try
            {
               StatusGIAITCDVo statusAnterior = new StatusGIAITCDVo();
               statusAnterior.getGiaITCDVo().setCodigo(giaitcdVo.getCodigo());
               statusAnterior = new StatusGIAITCDBe(conn).obterStatusGIAITCDAnterior(statusAnterior);
               if (!Validador.isNumericoValido(statusAnterior.getCodigo())) // SE GIA ANTERIOR A 07/12/2010
               {
                  statusAnterior = giaitcdVo.getStatusAnterior();
                  statusAnterior.setGiaITCDVo(null);
                  statusAnterior.setGiaTemporaria(giaitcdVo.getTemporarioVo());
               }
               validarAlterarStatusGIAITCD(giaitcdVo, statusAnterior);
               StatusGIAITCDVo status = giaitcdVo.getStatusVo();
               GIAITCDVo gia = giaitcdVo;
               /** como nos vamos migrar os dados da tabela temporaria para as definitivas devemos ter todos os dados em maos */
               if ((status.getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLADO) || status.getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR)) && !statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.REATIVADO))
               {
                  GIAITCDVo giaConsulta = new GIAITCDVo(giaitcdVo);
                  giaConsulta.setConsultaCompleta(true);
                  giaConsulta.setUsuarioLogado((long) giaitcdVo.getLogSefazVo().getUsuario().getCodigo());
                  giaConsulta.setLogSefazVo(giaitcdVo.getLogSefazVo());
                  giaConsulta = consultarGIAITCDGeral(giaConsulta);
                  giaConsulta.setStatusVo(giaitcdVo.getStatusVo());
                  giaConsulta.getStatusVo().setGiaITCDVo(giaConsulta);
                  giaConsulta.setLogSefazVo(giaitcdVo.getLogSefazVo());
                  giaConsulta.setUsuarioServidor(false); // NAO REMOVER ESTA LINHA POIS, ESTE DADO ERA PARA SER TRANSIENT NO ENTANTO ESTAVA SENDO SERIALIZADO PODENDO CAUSAR PROBLEMAS DE VALIDACAO EM ALGUMAS REGRAS.
                  status = giaConsulta.getStatusVo();
                  status.setLogSefazVo(giaConsulta.getLogSefazVo());
                  gia = giaConsulta;
                  gia.getAgenciaProtocolo().setCodgUnidade(giaitcdVo.getAgenciaProtocolo().getCodgUnidade());
                  statusAnterior.setGiaITCDVo(gia);
               }
               status.setLogSefazVo(gia.getLogSefazVo());
               if (Validador.isNumericoValido(statusAnterior.getGiaITCDVo().getCodigo()))
               {
                  status.setGiaITCDVo(gia);
                  status.setGiaTemporaria(null);
               } else if (Validador.isNumericoValido(statusAnterior.getGiaTemporaria().getCodigo()))
               {
                  status.setGiaITCDVo(null);
                  status.getGiaTemporaria().setCodigo(gia.getTemporarioVo().getCodigo());
               } else
               {
                  throw new PersistenciaException("Impossível inserir novo status: Código da GIA-ITCD não encontrado.");
               }
               if (statusAnteriorReativacao)
               {
                  (new StatusGIAITCDBe(conn)).incluirStatusAnteriorReativacao(status);
               } else
               {
                  (new StatusGIAITCDBe(conn)).incluirStatusGIAITCD(status);
                  if (giaitcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO))
                  {
                     gerarNumrDeclaracaoIsencaoAlterarGia(giaitcdVo);
                  }
                  else if(giaitcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO_IMPOSTO_ATE_1_UPF))
                  {
                     gerarNumrDeclaracaoIsencaoAlterarGia(giaitcdVo);
                  }
               }
               
               commit();
            } catch (ObjetoObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (ParametroObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (ConsultaException e)
            {
               conn.rollback();
               throw e;
            } catch (ConexaoException e)
            {
               conn.rollback();
               throw e;
            } catch (IntegracaoException e)
            {
               conn.rollback();
               throw e;
            } catch (RegistroNaoPodeSerUtilizadoException e)
            {
               conn.rollback();
               throw e;
            } catch (LogException e)
            {
               conn.rollback();
               throw e;
            } catch (PersistenciaException e)
            {
               conn.rollback();
               throw e;
            } catch (AnotacaoException e)
            {
               conn.rollback();
               throw e;
            } catch (RegistroExistenteException e)
            {
               conn.rollback();
               throw e;
            } catch (RuntimeException e)
            {
               conn.rollback();
               throw e;
            }
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }

   }

   public synchronized void alterarStatusGIAITCD(final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                                   ParametroObrigatorioException, 
                                                                                   ConsultaException, 
                                                                                   ConexaoException, 
                                                                                   IntegracaoException, 
                                                                                   SQLException, 
                                                                                   RegistroNaoPodeSerUtilizadoException, 
                                                                                   LogException, 
                                                                                   PersistenciaException, 
                                                                                   AnotacaoException, 
                                                                                   RegistroExistenteException, IOException
   {
      Validador.validaObjeto(giaitcdVo);
      alterarStatusGIAITCD(giaitcdVo, false);
   }

   public void adicionarDarStatusQuitadoManual(GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                           IntegracaoException, 
                                                                           ConsultaException, 
                                                                           ConexaoException, 
                                                                           RegistroNaoPodeSerUtilizadoException, SQLException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDDarVo giaDar = new GIAITCDDarVo();
      giaDar.setCodigoUofSequencial(giaITCDVo.getStatusVo().getGiaITCDDarVo().getDarEmitido().getCodgUofSeqc());
      giaDar.getGia().setCodigo(giaITCDVo.getCodigo());
      GIAITCDDarBe giaDarBe = new GIAITCDDarBe(conn);
      try
      {
         giaDarBe.validaDarVinculadoITCD(giaDar);
         giaITCDVo.getStatusVo().getGiaITCDDarVo().getCollVO().add(giaDar);
      } catch (ParametroObrigatorioException e)
      {
         throw new RegistroNaoPodeSerUtilizadoException(e.getMessage());
      }
      giaITCDVo.getStatusVo().getGiaITCDDarVo().setDarEmitido(new DarEmitidoIntegracaoVo());
   }

   /**
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws SQLException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws RegistroExistenteException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void alterarStatusGIAITCDSegundaRetificacao(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                                     ParametroObrigatorioException, 
                                                                                                     ConsultaException, 
                                                                                                     ConexaoException, 
                                                                                                     IntegracaoException, 
                                                                                                     SQLException, 
                                                                                                     RegistroNaoPodeSerUtilizadoException, 
                                                                                                     LogException, 
                                                                                                     PersistenciaException, 
                                                                                                     AnotacaoException, 
                                                                                                     RegistroExistenteException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      alterarStatusGIAITCD(giaITCDVo);
      new DocumentoArrecadacaoGiaNormalBe(conn).gerarDarManual(giaITCDVo);
   }

   /**
    * Método para alterar o Status de uma GIA-ITCD, manualmente.
    * 
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws RegistroExistenteException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void alterarStatusGIAITCDManual(final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                                         ParametroObrigatorioException, 
                                                                                         ConsultaException, 
                                                                                         ConexaoException, 
                                                                                         IntegracaoException, 
                                                                                         RegistroNaoPodeSerUtilizadoException, 
                                                                                         LogException, 
                                                                                         PersistenciaException, 
                                                                                         AnotacaoException, 
                                                                                         RegistroExistenteException, SQLException, IOException
   {
      Validador.validaObjeto(giaitcdVo);
      StatusGIAITCDVo statusAnterior = obterStatusAnteriorGIAITCD(giaitcdVo);
      validarAlterarStatusGIAITCD(giaitcdVo, statusAnterior);
      StatusGIAITCDVo status = giaitcdVo.getStatusVo();
      // Copia a JUSTIFICATIVA DE ALTERAÇÃO para manter em cada STATUS gravado.
      if(giaitcdVo.getTipoProtocoloGIA().is(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO)) 
      {
         status.setGiaTemporaria(new GIAITCDTemporarioVo(giaitcdVo.getCodigo()));
      }
      if (Validador.isStringValida(giaitcdVo.getJustificativaAlteracao()))
      {
         status.setJustificativaAlteracao(giaitcdVo.getJustificativaAlteracao());
      }
      try
      {
         if (conn.isClosed())
         {
            System.out.println("-----------------------------Conexao Fechada X0");
         }
      } catch (SQLException e)
      {
         System.out.println("-----------------------------Conexao Fechada X0 Erro.......");
         e.printStackTrace();
      }
      ExibirLOG.exibirLog(" 1 método - alterarStatusGIAITCDManual - "+status.getStatusGIAITCD().getTextoCorrente());//TODO
      (new StatusGIAITCDBe(conn)).incluirStatusGIAITCD(status);
   }

   /**
    * Altera o Status de uma GIA-ITCD sem validar
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws RegistroExistenteException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void alterarStatusGIAITCDAutomatico(final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                                             RegistroNaoPodeSerUtilizadoException, 
                                                                                             LogException, 
                                                                                             PersistenciaException, 
                                                                                             AnotacaoException, 
                                                                                             ConexaoException, 
                                                                                             IntegracaoException, 
                                                                                             ConsultaException, 
                                                                                             ParametroObrigatorioException, 
                                                                                             RegistroExistenteException, 
             IOException
   {
      Validador.validaObjeto(giaitcdVo);
      (new StatusGIAITCDBe(conn)).incluirStatusGIAITCD(giaitcdVo.getStatusVo());
   }

   /**
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws RegistroExistenteException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void alterarStatusGIAITCDAutomatico(final GIAITCDTemporarioVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                                                       RegistroNaoPodeSerUtilizadoException, 
                                                                                                       LogException, 
                                                                                                       PersistenciaException, 
                                                                                                       AnotacaoException, 
                                                                                                       ConexaoException, 
                                                                                                       IntegracaoException, 
                                                                                                       ConsultaException, 
                                                                                                       ParametroObrigatorioException, 
                                                                                                       RegistroExistenteException, 
             IOException
   {
      Validador.validaObjeto(giaitcdVo);
      (new StatusGIAITCDBe(conn)).incluirStatusGIAITCD(giaitcdVo.getStatusITCD());
   }

   /**
    * Este método valida uma avaliação
    * @param bem
    * @param dataPrazo
    * @throws ParametroObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ObjetoObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void validaAvaliacaoBemTributavel(BemTributavelVo bem, Date dataPrazo) throws ParametroObrigatorioException, 
                                                                                        RegistroNaoPodeSerUtilizadoException, 
                                                                                        ObjetoObrigatorioException
   {
      if (bem.getAvaliacaoBemTributavelVo().getAvaliacaoJudicial().is(DomnSimNao.NAO))
      {
         if (!Validador.isDataValida(bem.getAvaliacaoBemTributavelVo().getDataAvaliacao()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_ALTERAR_DATA);
         }
         if (bem.getAvaliacaoBemTributavelVo().getDataAvaliacao().after(new Date()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDA_GIA_ITCD_AVALIACAO_DATA_MAIOR_ATUAL);
         }
         if (bem.getAvaliacaoBemTributavelVo().getDataAvaliacao().before(bem.getGiaITCDVo().getStatusVo().getDataProtocolo()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDA_GIA_ITCD_AVALIACAO_DATA_AVALIACAO_DATA_PROTOCOLO);
         }
         if (bem.getAvaliacaoBemTributavelVo().getIsento().is(DomnSimNao.SIM) && bem.getIsencaoPrevista().is(DomnSimNao.NAO))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_BEM_NAO_ISENTO);
         }
         if (!Validador.isNumericoValido(bem.getAvaliacaoBemTributavelVo().getValorAvaliacao()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_IMOVEIS_NAO_AVALIADOS);
         }
         if (!Validador.isNumericoValido(bem.getAvaliacaoBemTributavelVo().getValorAvaliacao()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_VALOR);
         } 
         else if (bem.getAvaliacaoBemTributavelVo().getValorAvaliacao() < bem.getValorMercado())
         {
            //throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_VALOR_MENOR);
            ExibirLOG.exibirLog("Bem avaliado com valor inferior ao informado pelo conbrituinte:" , bem.getGiaITCDVo().getCodigo());
            ExibirLOG.exibirLogSimplificado("Valor de mercado: "+bem.getValorMercadoFormatado());
            ExibirLOG.exibirLogSimplificado("Valor do avaliado: "+bem.getAvaliacaoBemTributavelVo().getValorAvaliacaoFormatado());
         }
      } else
      {
         if (!Validador.isDataValida(bem.getAvaliacaoBemTributavelVo().getDataAvaliacao()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_DATA_JUDICIAL);
         }
         if (!Validador.isNumericoValido(bem.getAvaliacaoBemTributavelVo().getValorAvaliacao()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_VALOR_AVALIACAO);
         }
         if (bem.getAvaliacaoBemTributavelVo().getDataAvaliacao().after(new Date()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDA_GIA_ITCD_AVALIACAO_DATA_MAIOR_ATUAL_JUDICIAL);
         }
         if (dataPrazo != null && bem.getAvaliacaoBemTributavelVo().getDataAvaliacao().after(dataPrazo))
         {
            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_PRAZO);
         }
         if (!Validador.isNumericoValido(bem.getAvaliacaoBemTributavelVo().getValorAvaliacao()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_VALOR_JUDICIAL);
         } 
         else if (bem.getAvaliacaoBemTributavelVo().getValorAvaliacao() < bem.getValorMercado())
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_VALOR_MENOR_JUDICIAL);
         }
      }
   }

   /**
    * Valida um avaliador de uma avaliação
    * 
    * @param avaliacaoServidor
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void validaAvaliacaoBemTributavelServidorVo(AvaliacaoBemTributavelServidorVo avaliacaoServidor) throws ParametroObrigatorioException, 
                                                                                                                 IntegracaoException
   {
      if (!Validador.isNumericoValido(avaliacaoServidor.getServidorSefazVo().getNumrMatricula()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_SERVIDOR);
      } else
      {
         GestaoPessoasBe gestao = new GestaoPessoasBe(conn);
         ServidorSefazIntegracaoVo servidor = gestao.buscarServidorSefazPorNumrMatricula(avaliacaoServidor.getServidorSefazVo());
         if (!servidor.isExiste())
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_MATRICULA);
         }
      }
   }

   /**
    * Método de validação para inclusão de um Bem Tributável na GIA-ITCD
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarIncluirAvaliacaoBemTributavel(GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                                 ParametroObrigatorioException, 
                                                                                 ConexaoException, 
                                                                                 ConsultaException, 
                                                                                 IntegracaoException, 
                                                                                 RegistroNaoPodeSerUtilizadoException
   {
      Validador.validaObjeto(giaitcdVo);

      if (!giaitcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLADO) && !giaitcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR) && !giaitcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELA_GIOR))
      {
         throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_STATUS_PROTOCOLADO);
      }

      validarIncluirAlterarAvaliacaoBemTributavel(giaitcdVo);

   }

   /**
    * Método de validação para inclusão de um Bem Tributável na GIA-ITCD
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarIncluirAlterarAvaliacaoBemTributavel(GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                                        ParametroObrigatorioException, 
                                                                                        ConexaoException, 
                                                                                        ConsultaException, 
                                                                                        IntegracaoException, 
                                                                                        RegistroNaoPodeSerUtilizadoException
   {
      Validador.validaObjeto(giaitcdVo);

      for (Iterator iteBem = giaitcdVo.getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
      {
         BemTributavelVo bem = (BemTributavelVo) iteBem.next();
         if (isBemPassivelAvaliacao(giaitcdVo, bem))
         {
            if (!Validador.isNumericoValido(bem.getAvaliacaoBemTributavelVo().getValorAvaliacao()))
            {
               throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_IMOVEIS_NAO_AVALIADOS);
            }
            if (!bem.getAvaliacaoBemTributavelVo().getListaAgenfa().isExiste())
            {
               throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_AGENCIA);
            }
         }
      }
      if (!Validador.isNumericoValido(giaitcdVo.getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_NAO_EXISTE);
      }
      if (!Validador.isDataValida(giaitcdVo.getStatusVo().getDataProtocolo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_SEM_DATA_PROTOCOLO);
      }
      Date dataPrazo = obtemPrazoAvaliacaoJudicial(giaitcdVo.getStatusVo().getDataProtocolo(), giaitcdVo.getMunicipioProtocolar().getCodgMunicipio().intValue());
      for (Iterator iteBem = giaitcdVo.getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
      {
         BemTributavelVo bem = (BemTributavelVo) iteBem.next();
         if (isBemPassivelAvaliacao(giaitcdVo, bem))
         {
            validaAvaliacaoBemTributavel(bem, dataPrazo);
            for (Iterator iteServidor = bem.getAvaliacaoBemTributavelVo().getAvaliacaoBemTributavelServidorVo().getCollVO().iterator(); iteServidor.hasNext(); )
            {
               AvaliacaoBemTributavelServidorVo avaliacaoServidor = (AvaliacaoBemTributavelServidorVo) iteServidor.next();
               validaAvaliacaoBemTributavelServidorVo(avaliacaoServidor);
            }
         }
      }
   }

   /**
    * Método que retorna o Prazo de Avaliação Judicial
    * @param dataProtocolacao
    * @param municipio
    * @return java.util.Date
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public Date obtemPrazoAvaliacaoJudicial(Date dataProtocolacao, int municipio) throws ObjetoObrigatorioException, 
                                                                                        ConexaoException, 
                                                                                        ConsultaException, 
                                                                                        ParametroObrigatorioException, 
                                                                                        IntegracaoException
   {
      final String DESCRICAO = ConfiguracaoITCD.PARAMETRO_PRAZO_AVALIACAO_JUDICIAL;
      ConfiguracaoGerencialParametrosVo conf = new ConfiguracaoGerencialParametrosVo();
      conf.setTitulo(DESCRICAO);
      conf = new ConfiguracaoGerencialParametrosVo(conf);
      (new ConfiguracaoGerencialParametrosBe(conn)).consultarConfiguracaoGerencialParametros(conf);
      if (!Validador.isStringValida(conf.getValorItem()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_CONSULTA_PRAZO_AVALIACAO_JUDICIAL);
      }
      Calendar calendario = new GregorianCalendar();
      calendario.setTime(dataProtocolacao);
      calendario.add(Calendar.DATE, Integer.parseInt(conf.getValorItem()));
      return (new AbacoData()).getProximoDiaUtil(calendario.getTime(), municipio);
   }

   /**
    * Método responsável por validar a re-avaliação dos bens.
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarAlterarAvaliacaoBemTributavel(GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                                 ParametroObrigatorioException, 
                                                                                 ConexaoException, 
                                                                                 ConsultaException, 
                                                                                 IntegracaoException, 
                                                                                 RegistroNaoPodeSerUtilizadoException
   {
      Validador.validaObjeto(giaitcdVo);

      if (!giaitcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.AVALIADO))
      {
         throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_STATUS_AVALIADO);
      }
      for (Iterator iteBem = giaitcdVo.getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
      {
         BemTributavelVo bem = (BemTributavelVo) iteBem.next();
         if (isBemPassivelAvaliacao(giaitcdVo, bem))
         {
            if (!Validador.isDataValida(bem.getAvaliacaoBemTributavelVo().getDataAvaliacao()))
            {
               throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_ALTERAR_DATA);
            }
         }
      }
      validarIncluirAlterarAvaliacaoBemTributavel(giaitcdVo);
   }

   /**
    * Método responsável por verificar se o bem é passível de avaliação, sendo assim necessário validar e incluir.
    * @param giaITCDVo
    * @param bemTributavelVo
    * @return boolean
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static boolean isBemPassivelAvaliacao(final GIAITCDVo giaITCDVo, final BemTributavelVo bemTributavelVo)
   {
      boolean verifica = true;
      if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
      {
         if (!(((GIAITCDInventarioArrolamentoVo) giaITCDVo).getUfAbertura().getSiglUf().equals(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO)))
         {
            if (bemTributavelVo.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.OUTROS_BENS))
            {
               verifica = false;
               bemTributavelVo.setMotivoBemNaoPodeSerAvaliado("Bem móvel");
            }
         }
      } else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
      {
         if (bemTributavelVo.getBemParticular().is(DomnSimNao.SIM))
         {
            if (((GIAITCDSeparacaoDivorcioVo) giaITCDVo).getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS))
            {
               verifica = false;
               bemTributavelVo.setMotivoBemNaoPodeSerAvaliado("Bem Particular");
            }
         }
      }
      bemTributavelVo.setBemPassivelAvaliacao(verifica);
      return verifica;
   }

   /**
    * @param gia
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private GIAITCDVo obterGIAITCDSimples(final GIAITCDVo gia) throws ObjetoObrigatorioException, 
                                                                     ConsultaException
   {
      Validador.validaObjeto(gia);
      GIAITCDVo giaConsulta = new GIAITCDVo();
      GIAITCDVo parametro = new GIAITCDVo();
      parametro.setCodigo(gia.getCodigo());
      giaConsulta.setParametroConsulta(parametro);
      return consultarGIAITCDSimples(giaConsulta);
   }

   private GIAITCDVo obterGIAITCDCompleta(final GIAITCDVo gia) throws ConsultaException, 
                                                                      ObjetoObrigatorioException, 
                                                                      ConexaoException, 
                                                                      ParametroObrigatorioException, 
                                                                      IntegracaoException, SQLException, 
             IOException
   {
      GIAITCDVo giaConsulta = new GIAITCDVo(gia);
      giaConsulta.setConsultaCompleta(true);
      giaConsulta.setUsuarioLogado((long) gia.getLogSefazVo().getUsuario().getCodigo());
      giaConsulta.setLogSefazVo(gia.getLogSefazVo());
      return consultaGIAITCDBasico(giaConsulta);
   }

   /**
    * Método para incluir uma Avaliação do Bem Tributável
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws SQLException
    * @throws RegistroExistenteException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void incluirAvaliacaoBemTributavel(GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                         RegistroNaoPodeSerUtilizadoException, 
                                                                         ConexaoException, 
                                                                         IntegracaoException, 
                                                                         ConsultaException, 
                                                                         ParametroObrigatorioException, 
                                                                         RegistroExistenteException, 
                                                                         LogException, 
                                                                         PersistenciaException, 
                                                                         AnotacaoException, 
             IOException
   {
      Validador.validaObjeto(giaitcdVo);
      try
      {
         synchronized (GIAITCDVo.class)
         {
            try
            {
               validarIncluirAvaliacaoBemTributavel(giaitcdVo);
               for (Iterator iteAvaliacao = giaitcdVo.getBemTributavelVo().getCollVO().iterator(); iteAvaliacao.hasNext(); )
               {
                  BemTributavelVo bemAtual = (BemTributavelVo) iteAvaliacao.next();
                  if (isBemPassivelAvaliacao(giaitcdVo, bemAtual))
                  {
                     bemAtual.getAvaliacaoBemTributavelVo().setLogSefazVo(giaitcdVo.getLogSefazVo());
                     (new AvaliacaoBemTributavelBe(conn)).incluirAvaliacaoBemTributavel(bemAtual.getAvaliacaoBemTributavelVo());
                  }
               }
               giaitcdVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO));
               giaitcdVo.getStatusVo().setDataCadastroAvaliacao(new Date());
               giaitcdVo.getStatusVo().setGiaITCDVo(giaitcdVo);
               giaitcdVo.getStatusVo().setServidor(new ServidorSefazIntegracaoVo());
               giaitcdVo.getStatusVo().setServidorSefazResponsavelAlteracao(new ServidorSefazIntegracaoVo());
               giaitcdVo.getStatusVo().getServidor().setNumrMatricula(StringUtil.toLongWrapper(giaitcdVo.getLogSefazVo().getUsuario().getIdentificacao()));
               giaitcdVo.getStatusVo().setLogSefazVo(giaitcdVo.getLogSefazVo());
               (new StatusGIAITCDBe(conn)).incluirStatusGIAITCD(giaitcdVo.getStatusVo());
               GIAITCDVo completa = obterGIAITCDCompleta(giaitcdVo);
               GIAITCDVo original = obterGIAITCDSimples(giaitcdVo);

               if (Validador.isNumericoValido(original.getCodigo()))
               {
                  original.setValorITCDRetificado(completa.getValorITCD());
                  original.setDataAtualizacaoBD(new Date());
                  original.setLogSefazVo(giaitcdVo.getLogSefazVo());
                  alterar(original);
               }
               // TODO Teste invocar sistema de LOG para tirar LOG da avaliação.             
               // new LogITCDBe(conn).rotinaProcessamentoLOG(giaitcdVo);
               
               commit();
               giaitcdVo.setMensagem(MensagemSucesso.INCLUIR);
            } catch (ObjetoObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (RegistroNaoPodeSerUtilizadoException e)
            {
               conn.rollback();
               throw e;
            } catch (ConexaoException e)
            {
               conn.rollback();
               throw e;
            } catch (IntegracaoException e)
            {
               conn.rollback();
               throw e;
            } catch (ConsultaException e)
            {
               conn.rollback();
               throw e;
            } catch (ParametroObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (RegistroExistenteException e)
            {
               conn.rollback();
               throw e;
            } catch (LogException e)
            {
               conn.rollback();
               throw e;
            } catch (PersistenciaException e)
            {
               conn.rollback();
               throw e;
            } catch (AnotacaoException e)
            {
               conn.rollback();
               throw e;
            } catch (RuntimeException e)
            {
               conn.rollback();
               throw e;
            }
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }


   /**
    * <b></b>
    * Método utilizado para reabrir uma avaliacao, possibilitando que seja
    * alterada novamente.
    * LOGICA : 
    * 1 - Verificar as permissoes do usuario.
    * 1 - Verificar em que tipo de STATUS a GIA esta atualmente.
    * 2 - Caso seja um dos STATUS Permitidos prossegue com a rotina.
    * 4 - Inclue o STATUS { Avaliada } novamente.
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws SQLException
    * @throws RegistroExistenteException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public void reabrirAvaliacao(GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                            RegistroNaoPodeSerUtilizadoException, 
                                                            ConexaoException, 
                                                            IntegracaoException, 
                                                            ConsultaException, 
                                                            ParametroObrigatorioException, 
                                                            RegistroExistenteException, 
                                                            LogException, 
                                                            PersistenciaException, 
                                                            AnotacaoException, 
             IOException
   {
      Validador.validaObjeto(giaitcdVo);
      try
      {
         synchronized (GIAITCDVo.class)
         {
            try
            {  
               //----------------------------------Copia os valores da avaliação parao atributo valor reabertura------------------------------------------------
               new AvaliacaoBemTributavelBe(conn).copiarValorAvaliacaoParaValorReabertura( giaitcdVo.getBemTributavelVo());
               //----------------------------------Alterar o status das avaliações de todos os bens tributaveis para não impresso-------------------------------
               new AvaliacaoBemTributavelBe(conn).alterarStausAvaliacaoImpressa( giaitcdVo, new DomnSimNao(DomnSimNao.NAO) );
               
               //---------------------------------------Invlui um novo status na GIA o status avaliação reaberta------------------------------------------------
               giaitcdVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIACAO_REABERTA));
               giaitcdVo.getStatusVo().setDataCadastroAvaliacao(null);
               giaitcdVo.getStatusVo().setGiaITCDVo(giaitcdVo);
               giaitcdVo.getStatusVo().setServidor(new ServidorSefazIntegracaoVo());
               giaitcdVo.getStatusVo().setServidorSefazResponsavelAlteracao(new ServidorSefazIntegracaoVo());
               giaitcdVo.getStatusVo().getServidor().setNumrMatricula(StringUtil.toLongWrapper(giaitcdVo.getLogSefazVo().getUsuario().getIdentificacao()));
               giaitcdVo.getStatusVo().setLogSefazVo(giaitcdVo.getLogSefazVo());
               (new StatusGIAITCDBe(conn)).incluirStatusGIAITCD(giaitcdVo.getStatusVo());
               
               //------------------------------------------Inclui um novo status na GIA o status avaliada-------------------------------------------------------
               giaitcdVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO));
               giaitcdVo.getStatusVo().setDataCadastroAvaliacao( new Date() );
               giaitcdVo.getStatusVo().setGiaITCDVo(giaitcdVo);
               giaitcdVo.getStatusVo().setServidor(new ServidorSefazIntegracaoVo());
               giaitcdVo.getStatusVo().setServidorSefazResponsavelAlteracao(new ServidorSefazIntegracaoVo());
               giaitcdVo.getStatusVo().getServidor().setNumrMatricula(StringUtil.toLongWrapper(giaitcdVo.getLogSefazVo().getUsuario().getIdentificacao()));
               giaitcdVo.getStatusVo().setLogSefazVo(giaitcdVo.getLogSefazVo());
               (new StatusGIAITCDBe(conn)).incluirStatusGIAITCD(giaitcdVo.getStatusVo());
               
               commit();            
            } catch (ObjetoObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (RegistroNaoPodeSerUtilizadoException e)
            {
               conn.rollback();
               throw e;
            } catch (ConexaoException e)
            {
               conn.rollback();
               throw e;
            } catch (IntegracaoException e)
            {
               conn.rollback();
               throw e;
            } catch (ConsultaException e)
            {
               conn.rollback();
               throw e;
            } catch (ParametroObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (RegistroExistenteException e)
            {
               conn.rollback();
               throw e;
            } catch (LogException e)
            {
               conn.rollback();
               throw e;
            } catch (PersistenciaException e)
            {
               conn.rollback();
               throw e;
            } catch (AnotacaoException e)
            {
               conn.rollback();
               throw e;
            } catch (RuntimeException e)
            {
               conn.rollback();
               throw e;
            }
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }


   /**
    * Método utilizado para reabrir uma avaliacao, possibilitando que seja
    * alterada novamente.
    * LOGICA : 
    * 1 - Verificar as permissoes do usuario.
    * 1 - Verificar em que tipo de STATUS a GIA esta atualmente.
    * 2 - Caso seja um dos STATUS Permitidos prossegue com a rotina.
    * 4 - Inclue o STATUS { Avaliada } novamente.
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws SQLException
    * @throws RegistroExistenteException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public void alterarStatusParaAvaliado(GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                     RegistroNaoPodeSerUtilizadoException, 
                                                                     ConexaoException, 
                                                                     IntegracaoException, 
                                                                     ConsultaException, 
                                                                     ParametroObrigatorioException, 
                                                                     RegistroExistenteException, 
                                                                     LogException, 
                                                                     PersistenciaException, 
                                                                     AnotacaoException, 
             IOException
   {
      Validador.validaObjeto(giaitcdVo);
      try
      {
         synchronized (GIAITCDVo.class)
         {
            try
            {
               giaitcdVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO));
               giaitcdVo.getStatusVo().setDataCadastroAvaliacao(new Date());
               giaitcdVo.getStatusVo().setGiaITCDVo(giaitcdVo);
               giaitcdVo.getStatusVo().setServidor(new ServidorSefazIntegracaoVo());
               giaitcdVo.getStatusVo().setServidorSefazResponsavelAlteracao(new ServidorSefazIntegracaoVo());
               giaitcdVo.getStatusVo().getServidor().setNumrMatricula(StringUtil.toLongWrapper(giaitcdVo.getLogSefazVo().getUsuario().getIdentificacao()));
               giaitcdVo.getStatusVo().setLogSefazVo(giaitcdVo.getLogSefazVo());
               (new StatusGIAITCDBe(conn)).incluirStatusGIAITCD(giaitcdVo.getStatusVo());
              
               commit();
               giaitcdVo.setMensagem(MensagemSucesso.AVALIACAO_REABERTA);
            } catch (ObjetoObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (RegistroNaoPodeSerUtilizadoException e)
            {
               conn.rollback();
               throw e;
            } catch (ConexaoException e)
            {
               conn.rollback();
               throw e;
            } catch (IntegracaoException e)
            {
               conn.rollback();
               throw e;
            } catch (ConsultaException e)
            {
               conn.rollback();
               throw e;
            } catch (ParametroObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (RegistroExistenteException e)
            {
               conn.rollback();
               throw e;
            } catch (LogException e)
            {
               conn.rollback();
               throw e;
            } catch (PersistenciaException e)
            {
               conn.rollback();
               throw e;
            } catch (AnotacaoException e)
            {
               conn.rollback();
               throw e;
            } catch (RuntimeException e)
            {
               conn.rollback();
               throw e;
            }
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }


   /**
    * Método para consulta simples de uma GIAITCDVo
    * @param giaITCDVo
    * @return GIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private GIAITCDVo consultarGIAITCDSimples(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                               ConsultaException
   {
      Validador.validaObjeto(giaITCDVo);
      return new GIAITCDQDao(conn).findGIAITCD(giaITCDVo);
   }

   /**
    * Método para Alterar uma Avaliação do Bem Tributável
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws SQLException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void alterarAvaliacaoBemTributavel(GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                         ParametroObrigatorioException, 
                                                                         ConexaoException, 
                                                                         ConsultaException, 
                                                                         IntegracaoException, 
                                                                         RegistroNaoPodeSerUtilizadoException, 
                                                                         LogException, 
                                                                         PersistenciaException, 
                                                                         AnotacaoException, 
                                                                         DadoNecessarioInexistenteException, 
                                                                         SQLException
   {

      try
      {
         synchronized (GIAITCDVo.class)
         {
            try
            {
               validarAlterarAvaliacaoBemTributavel(giaitcdVo);               
               obterListaStatus(giaitcdVo);
               boolean reavaliacao = isReavaliacao(giaitcdVo);
               for (Iterator iteAvaliacao = giaitcdVo.getBemTributavelVo().getCollVO().iterator(); iteAvaliacao.hasNext(); )
               {
                  BemTributavelVo bem = (BemTributavelVo) iteAvaliacao.next();
                  if (isBemPassivelAvaliacao(giaitcdVo, bem))
                  {
                     bem.getAvaliacaoBemTributavelVo().setLogSefazVo(giaitcdVo.getLogSefazVo());
                     if(reavaliacao)
                     {
                        bem.getAvaliacaoBemTributavelVo().setTipoAvaliacao(new DomnAvaliacaoTipo(DomnAvaliacaoTipo.REAVALICAO));
                     }
                     
                     (new AvaliacaoBemTributavelBe(conn)).alterarAvaliacaoBemTributavel(bem.getAvaliacaoBemTributavelVo());
                  }
               }
               StatusGIAITCDVo status = giaitcdVo.getStatusVo();
               status.getServidorSefazResponsavelAlteracao().setNumrMatricula(StringUtil.toLongWrapper(giaitcdVo.getLogSefazVo().getUsuario().getIdentificacao()));
               status.setLogSefazVo(giaitcdVo.getLogSefazVo());
               new StatusGIAITCDBe(conn).alterarStatus(status);
               GIAITCDVo giaConsulta = new GIAITCDVo();
               GIAITCDVo parametro = new GIAITCDVo();
               parametro.setCodigo(giaitcdVo.getCodigo());
               giaConsulta.setParametroConsulta(parametro);
               giaConsulta = consultarGIAITCDSimples(giaConsulta);
               if (Validador.isNumericoValido(giaConsulta.getCodigo()))
               {
                  giaConsulta.setValorITCDRetificado(giaitcdVo.getValorITCD());
                  giaConsulta.setDataAtualizacaoBD(new Date());
                  giaConsulta.setLogSefazVo(giaitcdVo.getLogSefazVo());
                  alterar(giaConsulta);
               }
               commit();
            } catch (ObjetoObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (ParametroObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (ConexaoException e)
            {
               conn.rollback();
               throw e;
            } catch (ConsultaException e)
            {
               conn.rollback();
               throw e;
            } catch (IntegracaoException e)
            {
               conn.rollback();
               throw e;
            } catch (RegistroNaoPodeSerUtilizadoException e)
            {
               conn.rollback();
               throw e;
            } catch (LogException e)
            {
               conn.rollback();
               throw e;
            } catch (PersistenciaException e)
            {
               conn.rollback();
               throw e;
            } catch (AnotacaoException e)
            {
               conn.rollback();
               throw e;
            } catch (DadoNecessarioInexistenteException e)
            {
               conn.rollback();
               throw e;
            } catch (RuntimeException e)
            {
               conn.rollback();
               throw e;
            }
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw e;
      }
   }


   /**
    * Método para Alterar uma Avaliação do Bem Tributável
    * @param giaitcdVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws SQLException
    * @implemented by Daniel Balieiro
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public void inativarAvaliacaoBemTributavel(GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
                                                                          ParametroObrigatorioException, 
                                                                          ConexaoException, 
                                                                          ConsultaException, 
                                                                          IntegracaoException, 
                                                                          RegistroNaoPodeSerUtilizadoException, 
                                                                          LogException, 
                                                                          PersistenciaException, 
                                                                          AnotacaoException, 
                                                                          DadoNecessarioInexistenteException, 
                                                                          SQLException, 
                                                                          RegistroExistenteException, IOException
   {

      try
      {
         synchronized (GIAITCDVo.class)
         {
            try
            {
               validarAlterarAvaliacaoBemTributavel(giaitcdVo);
               for (Iterator iteAvaliacao = giaitcdVo.getBemTributavelVo().getCollVO().iterator(); iteAvaliacao.hasNext(); )
               {
                  BemTributavelVo bem = (BemTributavelVo) iteAvaliacao.next();
                  //if(bem.getBemParticular().is(DomnSimNao.NAO) & !( giaitcdVo instanceof GIAITCDSeparacaoDivorcioVo ) )
                  if( isBemPassivelAvaliacao( giaitcdVo , bem))
                  {
                     bem.getAvaliacaoBemTributavelVo().setLogSefazVo(giaitcdVo.getLogSefazVo());
                     bem.getAvaliacaoBemTributavelVo().setStatusAvaliacao(new DomnAtivoInativo(DomnAtivoInativo.INATIVO));
                    (new AvaliacaoBemTributavelBe(conn)).alterarAvaliacaoBemTributavel(bem.getAvaliacaoBemTributavelVo());     
                  }
                                 
               }
               StatusGIAITCDVo status = giaitcdVo.getStatusVo();
               status.getServidorSefazResponsavelAlteracao().setNumrMatricula(StringUtil.toLongWrapper(giaitcdVo.getLogSefazVo().getUsuario().getIdentificacao()));
               status.setLogSefazVo(giaitcdVo.getLogSefazVo());
               //new StatusGIAITCDBe(conn).alterarStatus(status);
               GIAITCDVo giaConsulta = new GIAITCDVo();
               GIAITCDVo parametro = new GIAITCDVo();
               parametro.setCodigo(giaitcdVo.getCodigo());
               giaConsulta.setParametroConsulta(parametro);
               giaConsulta = consultarGIAITCDSimples(giaConsulta);
               if (Validador.isNumericoValido(giaConsulta.getCodigo()))
               {
                  giaConsulta.setValorITCDRetificado(giaitcdVo.getValorITCD());
                  giaConsulta.setDataAtualizacaoBD(new Date());
                  giaConsulta.setLogSefazVo(giaitcdVo.getLogSefazVo());
                  alterar(giaConsulta);
               }

               giaitcdVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIACAO_EXCLUIDA));

               giaitcdVo.getStatusVo().setDataCadastroAvaliacao(new Date());
               giaitcdVo.getStatusVo().setGiaITCDVo(giaitcdVo);
               giaitcdVo.getStatusVo().setServidor(new ServidorSefazIntegracaoVo());
               giaitcdVo.getStatusVo().setServidorSefazResponsavelAlteracao(new ServidorSefazIntegracaoVo());
               giaitcdVo.getStatusVo().getServidor().setNumrMatricula(StringUtil.toLongWrapper(giaitcdVo.getLogSefazVo().getUsuario().getIdentificacao()));
               giaitcdVo.getStatusVo().setLogSefazVo(giaitcdVo.getLogSefazVo());

               // Incluir o Status DomnStatusGIAITCD.AVALIACAO_EXCLUIDA
               (new StatusGIAITCDBe(conn)).incluirStatusGIAITCD(giaitcdVo.getStatusVo());

               // Incluir o Status DomnStatusGIAITCD.PROTOCOLADO
               giaitcdVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.PROTOCOLADO));
               (new StatusGIAITCDBe(conn)).incluirStatusGIAITCD(giaitcdVo.getStatusVo());

               commit();
               giaitcdVo.setMensagem(MensagemSucesso.AVALIACAO_EXCLUIDA_COM_SUCESSO);
            } catch (ObjetoObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (ParametroObrigatorioException e)
            {
               conn.rollback();
               throw e;
            } catch (ConexaoException e)
            {
               conn.rollback();
               throw e;
            } catch (ConsultaException e)
            {
               conn.rollback();
               throw e;
            } catch (IntegracaoException e)
            {
               conn.rollback();
               throw e;
            } catch (RegistroNaoPodeSerUtilizadoException e)
            {
               conn.rollback();
               throw e;
            } catch (LogException e)
            {
               conn.rollback();
               throw e;
            } catch (PersistenciaException e)
            {
               conn.rollback();
               throw e;
            } catch (AnotacaoException e)
            {
               conn.rollback();
               throw e;

            } catch (RuntimeException e)
            {
               conn.rollback();
               throw e;
            }
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw e;
      }
   }


   /**
    * Verifica se o responsável - ou declarante - é válido.
    * @param giaITCDVo
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void validaResponsavel(final GIAITCDVo giaITCDVo) throws ParametroObrigatorioException
   {
      //CPF do procurador
      if (!Validador.isStringValida(giaITCDVo.getResponsavelVo().getNumrDocumento()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_RESPONSAVEL);
      }
      if (giaITCDVo.getResponsavelVo().getTipoDocumento().is(DomnTipoDocumento.CPF))
      {
         if (!Validador.isCPFValido(giaITCDVo.getResponsavelVo().getNumrDocumento()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CPF_INVALIDO);
         }
      } else
      {
         if (!Validador.isCNPJValido(giaITCDVo.getResponsavelVo().getNumrDocumento()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CNPJ_INVALIDO);
         }
      }
   }

   /**
    * Valida a Natureza da operação.
    * @param giaItcdVo
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void validaNaturezaOperacao(final GIAITCDVo giaItcdVo) throws ParametroObrigatorioException
   {
      //Natureza da operacao
      if (!giaItcdVo.getNaturezaOperacaoVo().isExiste())
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_NATUREZA_OPERACAO);
      }
   }

   /**
    * Valida a senha informada.
    * @param giaITCDVo
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void validaSenha(final GIAITCDVo giaITCDVo) throws ParametroObrigatorioException
   {
      //Senha
      if (!Validador.isStringValida(giaITCDVo.getSenha()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_SENHA);
      }
   }

   /**
    * Verifica se o tipo da GIA válido.
    * @param giaItcdVo
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void validaTipoGIA(final GIAITCDVo giaItcdVo) throws ParametroObrigatorioException
   {
      if (!Validador.isDominioNumericoValido(giaItcdVo.getTipoGIA()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_ITCD_TIPO_GIA_NAO_INFORMADO);
      }
   }

   /**
    * Verifica se o número do protocolo, bem como a data de protocolo foram informados são válidos.
    * @param giaItcdVo
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void validaProtocolo(final GIAITCDVo giaItcdVo) throws ParametroObrigatorioException
   {
      if (!Validador.isNumericoValido(giaItcdVo.getStatusVo().getProtocolo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_ITCD_NUMR_PROTOCOLO);
      }
      if (!Validador.isDataValida(giaItcdVo.getStatusVo().getDataProtocolo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_ITCD_DATA_PROTOCOLO);
      }
   }

   /**
    * Valida se foram informatos bens tributáveis bem como a consistência dos mesmos.
    * @param giaITCDVo
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void validaBemTributavel(final GIAITCDVo giaITCDVo) throws ParametroObrigatorioException
   {
      if (!Validador.isCollectionValida(giaITCDVo.getBemTributavelVo().getCollVO()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INFORMAR_PRIMEIRO_BEM_TRIBUTAVEL);
      }
      
      for (BemTributavelVo bemTributavelVo :(ArrayList<BemTributavelVo>) giaITCDVo.getBemTributavelVo().getCollVO()) 
      {  
            BemVo bemVo = bemTributavelVo.getBemVo();
            if(!Validador.isObjetoValido(bemVo))
            {
               throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_CLASSIFICACAO_BEM); 
            }           
            if(!Validador.isNumericoValido(bemVo.getCodigo()))
            {
               throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_TIPO_BEM); 
            }
            if(!Validador.isDominioNumericoValido(bemVo.getClassificacaoTipoBem()))
            {
               throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_CLASSIFICACAO_BEM); 
            }
      }
      
   }

   /**
    * Adiciona um bem tribuitável em uma determinada Collection e valida a descrição para
    * garantir que as descrições dos bens tributáveis são únicas.
    * @param listaBemTributavel
    * @param bemTributavelVo
    * @return Collection
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Marlo Eichenberg Motta
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static Collection addBemTributavel(Collection listaBemTributavel, BemTributavelVo bemTributavelVo) throws ParametroObrigatorioException
   {
      if (!Validador.isCollectionValida(listaBemTributavel))
      {
         listaBemTributavel = new ArrayList();
      }
      Iterator it = listaBemTributavel.iterator();
      while (it.hasNext())
      {
         BemTributavelVo atual = (BemTributavelVo) it.next();
         if (atual.getDescricaoBemTributavel().equals(bemTributavelVo.getDescricaoBemTributavel()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_INCLUIR_DESCRICAO_E_TIPO);
         }
      }
      listaBemTributavel.add(bemTributavelVo);
      return listaBemTributavel;
   }

   /**
    * Valida o beneficário. Verifica se o beneficiïário foi setado se foi informado o CPF e se o CPF é válido.
    * @param giaITCDVo
    * @throws ParametroObrigatorioException
    * @throws RegistroExistenteException
    * @throws ObjetoObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Marlo Eichenberg Motta
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void validaBeneficiario(final GIAITCDVo giaITCDVo) throws ParametroObrigatorioException, 
                                                                           ObjetoObrigatorioException, 
                                                                           RegistroExistenteException
   {
      if (giaITCDVo instanceof GIAITCDInventarioArrolamentoVo)
      {
         GIAITCDInventarioArrolamentoVo gia = (GIAITCDInventarioArrolamentoVo) giaITCDVo;
         if (!(gia.getEstadoCivilDeCujus().is(DomnEstadoCivil.CASADO) || gia.getEstadoCivilDeCujus().is(DomnEstadoCivil.CONVIVENTE)))
         {
            if (giaITCDVo.getBeneficiarioVo().getCollVO().size() <= 0)
            {
               throw new ParametroObrigatorioException(MensagemErro.BENEFICIARIO_NAO_INFORMADO);
            }
         }
      } else
      {
         if (giaITCDVo.getBeneficiarioVo().getCollVO().size() <= 0)
         {
            throw new ParametroObrigatorioException(MensagemErro.BENEFICIARIO_NAO_INFORMADO);
         }
      }
      if (giaITCDVo instanceof GIAITCDInventarioArrolamentoVo)
      {
         GIAITCDInventarioArrolamentoBe.validaHerdeirosBeneficiarios((GIAITCDInventarioArrolamentoVo) giaITCDVo);
      }
      for (Iterator ite = giaITCDVo.getBeneficiarioVo().getCollVO().iterator(); ite.hasNext(); )
      {
         BeneficiarioVo beneficiarioVo = (BeneficiarioVo) ite.next();
         if (!Validador.isStringValida(beneficiarioVo.getPessoaBeneficiaria().getNumrDocumento()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF);
         }
         if (beneficiarioVo.getPessoaBeneficiaria().getTipoDocumento().is(DomnTipoDocumento.CPF))
         {
            if (!Validador.isCPFValido(beneficiarioVo.getPessoaBeneficiaria().getNumrDocumento()))
            {
               throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CPF_INVALIDO);
            }
         } else
         {
            if (!Validador.isCNPJValido(beneficiarioVo.getPessoaBeneficiaria().getNumrDocumento()))
            {
               throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CNPJ_INVALIDO);
            }
         }
      }
      validaBeneficiarioDuplicado(giaITCDVo);
   }

   /**
    * Valida se existem beneficiarios duplicados na GIA
    * @param giaITCDVo
    * @throws RegistroExistenteException
    * @throws ObjetoObrigatorioException
    * @implemented by Lucas Nascimento
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void validaBeneficiarioDuplicado(final GIAITCDVo giaITCDVo) throws RegistroExistenteException, 
                                                                                    ObjetoObrigatorioException
   {
      Validador.validaObjeto(giaITCDVo.getBeneficiarioVo());
      Map mapBeneficiario = new HashMap();
      String cpfBeneficiario;
      for (Iterator iteBeneficiario = giaITCDVo.getBeneficiarioVo().getCollVO().iterator(); iteBeneficiario.hasNext(); )
      {
         BeneficiarioVo beneficiarioAtual = (BeneficiarioVo) iteBeneficiario.next();
         cpfBeneficiario = beneficiarioAtual.getPessoaBeneficiaria().getNumrDocumento();
         mapBeneficiario.put(cpfBeneficiario, null);
      }
      cpfBeneficiario = giaITCDVo.getBeneficiarioVo().getPessoaBeneficiaria().getNumrDocumento();
      if (mapBeneficiario.containsKey(cpfBeneficiario))
      {
         throw new RegistroExistenteException(MensagemErro.VALIDAR_BENEFICIARIO_CPF_DUPLICADO);
      }
   }

   /**
    * Metodo responsável por gerar DAR.
    * @param giaitcdVo
    * @return StringBuffer
    * @throws DarException
    * @throws ObjetoObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public StringBuffer imprimirDar(GIAITCDVo giaitcdVo) throws DarException, 
                                                               ObjetoObrigatorioException
   {
      Validador.validaObjeto(giaitcdVo);
      Validador.validaObjeto(giaitcdVo.getGiaITCDDarVo());
      if (!Validador.isNumericoValido(giaitcdVo.getGiaITCDDarVo().getDarEmitido().getNumrDarSeqc()))
      {
         throw new DarException(MensagemErro.IMPRIMIR_DAR);
      }
      DocumentoArrecadacaoITCDBe documentoArrecadacaoBe = new DocumentoArrecadacaoGiaNormalBe(conn);
      return documentoArrecadacaoBe.imprimirDar(giaitcdVo.getGiaITCDDarVo());
   }

   /**
    * Método responsável por verificar o status do DAR.
    * @param giaDarVo
    * @throws ObjetoObrigatorioException
    * @throws DarException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */ 
   private void verificaSituacaoDar(GIAITCDDarVo giaDarVo) throws ObjetoObrigatorioException, 
                                                                  DarException
   {
      Validador.validaObjeto(giaDarVo);
      if (giaDarVo.isDarQuitado())
      {
         throw new DarException(MensagemErro.EXISTE_DAR_QUITADO_GIA_ITCD);
      } else if (giaDarVo.isDarSubstituido())
      {
         throw new DarException(MensagemErro.ERRO_GERAR_DAR_DAR_SUBSTITUIDO);
      }
   }


   public boolean dataVencimentoInvalida(final GIAITCDVo giaITCDVo, boolean isRelatorio) throws ObjetoObrigatorioException, 
                                                                            IntegracaoException, 
                                                                            DadoNecessarioInexistenteException, 
                                                                            ConexaoException, 
                                                                            ConsultaException, 
                                                                            ParametroObrigatorioException
   {
      DocumentoArrecadacaoITCDBe documentoArrecadacaoBe = new DocumentoArrecadacaoGiaNormalBe(conn);
      Date dataVencimento = documentoArrecadacaoBe.obtemDataVencimentoDar(documentoArrecadacaoBe.determinaPeriodoRefDar(giaITCDVo, isRelatorio), giaITCDVo);
      if (dataVencimento != null)
      {
         return dataVencimento.before(new Date());
      }
      return false;
   }

   public void processarGeracaoDAR( GIAITCDVo giaitcdVo, String ipClient, String portClient ) throws ConsultaException, 
                                                               ObjetoObrigatorioException, 
                                                               ParametroObrigatorioException, 
                                                               IntegracaoException, 
                                                               DarException, 
             IOException
   {   
            // consulta o ultimo dar gerado
            GIAITCDDarVo giaDarVo = (new GIAITCDDarBe(conn)).consultarUltimoGIAITCDDar(new GIAITCDDarVo(new GIAITCDDarVo(giaitcdVo)));
            
            if( StatusGIAITCDBe.isGiaPossuiStatus( giaitcdVo.getStatusVo() , DomnStatusGIAITCD.QUITADO  ) & giaDarVo.isDarQuitado() )
            { 
              System.out.println("O DAR NÃO foi gerado pois a GIA possui o status DomnStatusGIAITCD.QUITADO & DAR quitado. ");            
            }else
            {
               gerarDAR(giaitcdVo, ipClient, portClient);
            }
      
   }

   /**
    * Este método dispara a geracao de dar
    * 
    * @param giaitcdVo
    * @throws DarException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void gerarDAR(GIAITCDVo giaitcdVo, String ipClient, String portClient) throws DarException, IOException
   {   
      DocumentoArrecadacaoITCDBe documentoArrecadacaoBe = null;          
      
      try
      {
         //ExibirLOG.exibirLog("Início - Rotina - Gerar DAR",giaitcdVo.getCodigo());
         // consulta o ultimo dar gerado
         GIAITCDDarVo giaDarVo = (new GIAITCDDarBe(conn)).consultarUltimoGIAITCDDar(new GIAITCDDarVo(new GIAITCDDarVo(giaitcdVo)));
         // consulta o status anterior ao atual
         StatusGIAITCDVo statusAnteriorGIA = obterStatusAnteriorGIAITCD(giaitcdVo);
         StatusGIAITCDVo statusAnteriorInativacaoGIA = null;
         if (statusAnteriorGIA.isStatusIn(new int[]
               { DomnStatusGIAITCD.REATIVADO }))
         {
            StatusGIAITCDVo parametro = new StatusGIAITCDVo();
            parametro.getGiaITCDVo().setCodigo(giaitcdVo.getCodigo());
            parametro.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.INATIVO));
            statusAnteriorInativacaoGIA = new StatusGIAITCDBe(conn).obterStatusGIAITCDAnterior(parametro);
         }
         if (giaDarVo.isExisteDarEmitido())
         {
            if( statusAnteriorGIA.getStatusGIAITCD().is(DomnStatusGIAITCD.AVALIADO) & (StatusGIAITCDBe.contarStatusNaCollectionDoVo( giaitcdVo.getStatusVo() , new DomnStatusGIAITCD( DomnStatusGIAITCD.AVALIADO)  ) >= 2 ) )
            {
               documentoArrecadacaoBe = new DocumentoArrecadacaoGiaNormalBe(conn);            
               
            } else if (giaitcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA))
            {
               if (statusAnteriorGIA.isStatusIn(new int[]
                     { DomnStatusGIAITCD.NOTIFICADO, 
                       DomnStatusGIAITCD.RETIFICADO, 
                       DomnStatusGIAITCD.NOTIFICADO_CIENTE, 
                       DomnStatusGIAITCD.RETIFICADO_CIENTE }))
               {
                  verificaSituacaoDar(giaDarVo);
                  documentoArrecadacaoBe = new DocumentoArrecadacaoGiaVencidoBe(conn);
               } else if (statusAnteriorGIA.isStatusIn(new int[]
                     { DomnStatusGIAITCD.REATIVADO }))
               {
                  if (Validador.isObjetoValido(statusAnteriorInativacaoGIA) && statusAnteriorInativacaoGIA.isStatusIn(new int[]
                        { DomnStatusGIAITCD.NOTIFICADO, 
                          DomnStatusGIAITCD.RETIFICADO, 
                          DomnStatusGIAITCD.RETIFICADO_CIENTE, 
                          DomnStatusGIAITCD.NOTIFICADO_CIENTE }))
                  {
                     verificaSituacaoDar(giaDarVo);
                     documentoArrecadacaoBe = new DocumentoArrecadacaoGiaVencidoBe(conn);
                  } else if (Validador.isObjetoValido(statusAnteriorInativacaoGIA) && statusAnteriorInativacaoGIA.isStatusIn(new int[]
                        { DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA }))
                  {
                     StatusGIAITCDVo parametro = new StatusGIAITCDVo();
                     parametro.getGiaITCDVo().setCodigo(giaitcdVo.getCodigo());
                     parametro.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.INATIVO));
                     parametro.setOrdemAparicaoStatus(2);
                     statusAnteriorInativacaoGIA = new StatusGIAITCDBe(conn).obterStatusGIAITCDAnterior(parametro);
                     if (statusAnteriorInativacaoGIA.isStatusIn(new int[]
                           { DomnStatusGIAITCD.NOTIFICADO, 
                             DomnStatusGIAITCD.RETIFICADO, 
                             DomnStatusGIAITCD.NOTIFICADO_CIENTE, 
                             DomnStatusGIAITCD.RETIFICADO_CIENTE }))
                     {
                        verificaSituacaoDar(giaDarVo);
                        documentoArrecadacaoBe = new DocumentoArrecadacaoGiaVencidoBe(conn);
                     } else
                     {
                        throw new DarException(MensagemErro.DAR_PROCURAR_AGENFA);
                     }
                  } else
                  {
                     throw new DarException(MensagemErro.DAR_PROCURAR_AGENFA);
                  }
               } else if (Validador.isObjetoValido(statusAnteriorInativacaoGIA) && statusAnteriorInativacaoGIA.isStatusIn(new int[]
                     { DomnStatusGIAITCD.PARCELADO }))
               {
                  throw new DarException(MensagemErro.DAR_GIA_ITCD_PARCELADO);
               } else
               {
                  throw new DarException(MensagemErro.DAR_PROCURAR_AGENFA);
               }
            }
            else if (giaitcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.INATIVO))
            {
               throw new DarException(MensagemErro.DAR_GIA_ITCD_INATIVA);
            } else if (giaitcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO))
            {
               throw new DarException(MensagemErro.DAR_GIA_ITCD_PARCELADO);
            } else if (giaitcdVo.getStatusVo().isStatusIn(new int[]
                  { DomnStatusGIAITCD.NOTIFICADO, 
                    DomnStatusGIAITCD.RETIFICADO, 
                    DomnStatusGIAITCD.RETIFICADO_CIENTE, 
                    DomnStatusGIAITCD.NOTIFICADO_CIENTE }))
            {
               verificaSituacaoDar(giaDarVo);
               if (statusAnteriorGIA.isStatusIn(new int[]
                     { DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA }))
               {
                  if (giaitcdVo.getStatusVo().isStatusIn(new int[]
                        { DomnStatusGIAITCD.RETIFICADO_CIENTE, 
                          DomnStatusGIAITCD.NOTIFICADO_CIENTE }))
                  {
                     if (dataVencimentoInvalida(giaitcdVo, false))
                     {
                        documentoArrecadacaoBe = new DocumentoArrecadacaoGiaVencidoBe(conn);
                     } else
                     {
                        documentoArrecadacaoBe = new DocumentoArrecadacaoGiaNormalBe(conn);
                     }
                  } else
                  {
                     throw new DarException(MensagemErro.DAR_PROCURAR_AGENFA);
                  }
               } else if (giaitcdVo.getStatusVo().isStatusIn(new int[]
                     { DomnStatusGIAITCD.RETIFICADO_CIENTE, 
                       DomnStatusGIAITCD.NOTIFICADO_CIENTE }) || !giaDarVo.isDarAtrasado())
               {
                  documentoArrecadacaoBe = new DocumentoArrecadacaoGiaNormalBe(conn);
               } else
               {    
                  throw new DarException(MensagemErro.DAR_PROCURAR_AGENFA);
               }
            } else
            {
               throw new DarException(MensagemErro.STATUS_GIA_ITCD_INVALIDO_GERACAO_DAR);
            }
         } else if (giaitcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA))
         {
            documentoArrecadacaoBe = new DocumentoArrecadacaoGiaVencidoBe(conn);
         } else // Gerando o primeiro DAR
         {
            documentoArrecadacaoBe = new DocumentoArrecadacaoGiaNormalBe(conn);
         }         
         
         documentoArrecadacaoBe.gerarDAR(giaitcdVo, ipClient, portClient);         
         
         commit();
         ExibirLOG.exibirLog("Fim - Rotina - Gerar DAR",giaitcdVo.getCodigo());
      } catch (DadoNecessarioInexistenteException e)
      {
         throw new DarException(MensagemErro.GERAR_DAR, e);
      } catch (SQLException e)
      {
         throw new DarException(MensagemErro.GERAR_DAR, e);
      } catch (ConsultaException e)
      {
         throw new DarException(MensagemErro.GERAR_DAR, e);
      } catch (ObjetoObrigatorioException e)
      {
         throw new DarException(MensagemErro.GERAR_DAR, e);
      } catch (ParametroObrigatorioException e)
      {
         throw new DarException(MensagemErro.GERAR_DAR, e);
      } catch (IntegracaoException e)
      {
         throw new DarException(MensagemErro.GERAR_DAR, e);
      } catch (ConexaoException e)
      {
         throw new DarException(MensagemErro.GERAR_DAR, e);
      }



   }

   /**
    * Este metodo consulta o DAR de uma determinada GIA
    * 
    * @param giaitcdVo
    * @return GIAITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws DarException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultarDAR(GIAITCDVo giaitcdVo) throws ConsultaException, 
                                                             ObjetoObrigatorioException, 
                                                             DarException
   {
      return (new DocumentoArrecadacaoGiaNormalBe(conn)).consultarDAR(giaitcdVo);
   }

   private GIAITCDVo obterGIAITCDParaAlterar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                               ConsultaException, 
                                                                               ConexaoException, 
                                                                               ParametroObrigatorioException, 
                                                                               IntegracaoException, SQLException, 
             IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo consulta = new GIAITCDVo();
      GIAITCDVo parametro = new GIAITCDVo();
      parametro.setCodigo(giaITCDVo.getCodigo());
      consulta.setParametroConsulta(parametro);
      consulta.setLogSefazVo(giaITCDVo.getLogSefazVo());
      consulta.setConsultaCompleta(false);
      return consultarGIAITCD(consulta);
   }

   /**
    * Método responsável por alterar Status da GIA-ITCD - Nao Ocorrência de Fato Gerador.
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws SQLException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws RegistroExistenteException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public boolean gerarNaoOcorrenciaFatoGerador(GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                            ParametroObrigatorioException, 
                                                                            ConsultaException, 
                                                                            ConexaoException, 
                                                                            IntegracaoException, 
                                                                            SQLException, 
                                                                            RegistroNaoPodeSerUtilizadoException, 
                                                                            LogException, 
                                                                            PersistenciaException, 
                                                                            AnotacaoException, 
                                                                            RegistroExistenteException, IOException
   {
      if (giaITCDVo instanceof GIAITCDSeparacaoDivorcioVo)
      {
         GIAITCDSeparacaoDivorcioVo giaDivorcio = (GIAITCDSeparacaoDivorcioVo) giaITCDVo;
         if (CalculoITCD.valorBensConjugesIguais(giaDivorcio.getValorTotalConjuge1() , giaDivorcio.getValorTotalConjuge2()))
         {
            giaDivorcio.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR));
            giaDivorcio.getStatusVo().setServidor(giaITCDVo.getServidorSefazIntegracaoVo());
            // recriando para não efetuar o commit
            (new GIAITCDBe(conn)).alterarStatusGIAITCD(giaDivorcio);
            if (!Validador.isNumericoValido(giaDivorcio.getNumrDeclaracaoFatoGerador()))
            {
               GIAITCDVo original = obterGIAITCDParaAlterar(giaDivorcio);
               SefazSequencia sequence = new SefazSequencia(conn);
               giaITCDVo.setNumrDeclaracaoFatoGerador(sequence.next(SequencesITC.SEQUENCE_GIA_ITCD_DECLARACAO_FATO_GERADOR));
               original.setNumrDeclaracaoFatoGerador(giaDivorcio.getNumrDeclaracaoFatoGerador());
               original.setLogSefazVo(giaITCDVo.getLogSefazVo());
               alterar(original);
            }
            return true;
         }
      }
      return false;
   }

   /**
    * Método responsável por alterar Status da GIA-ITCD - Isenção. 
    * @param giaITCDVo
    * @return boolean
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws SQLException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws RegistroExistenteException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public boolean gerarIsencao(GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                           ParametroObrigatorioException, 
                                                           ConsultaException, 
                                                           ConexaoException, 
                                                           IntegracaoException, 
                                                           SQLException, 
                                                           RegistroNaoPodeSerUtilizadoException, 
                                                           LogException, 
                                                           PersistenciaException, 
                                                           AnotacaoException, 
                                                           RegistroExistenteException, IOException
   {      
      if (giaITCDVo.getValorITCD() == 0){           
         giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.ISENTO));
         giaITCDVo.getStatusVo().setServidor(giaITCDVo.getServidorSefazIntegracaoVo());
         alterarStatusGIAITCD(giaITCDVo);
         gerarNumrDeclaracaoIsencaoAlterarGia(giaITCDVo);
         return true;
      }
      return false;
   }
   
   
   /**
    * Verifica se a gia se enquadra no caso de Isenção até 1 UPF.
    * se sim: gera o status e o número de declaração de isenção
    * @param giaITCDVo
    * @return boolean
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws SQLException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws RegistroExistenteException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public boolean verificarIsencaoImpostoMenorQueUmaUpf(GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                           ParametroObrigatorioException, 
                                                           ConsultaException, 
                                                           ConexaoException, 
                                                           IntegracaoException, 
                                                           SQLException, 
                                                           RegistroNaoPodeSerUtilizadoException, 
                                                           LogException, 
                                                           PersistenciaException, 
                                                           AnotacaoException, 
                                                           RegistroExistenteException, IOException

   {
      System.out.println("Verificando Isento - Imposto até 1 UPF/MT");
      switch (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente())
      {
         case DomnTipoProcesso.DOACAO:
            (new GIAITCDDoacaoBe(conn)).verificarIsencaoImpostoMenorQueUmaUpfPorBeneficiario((GIAITCDDoacaoVo) giaITCDVo);
            break;
         case DomnTipoProcesso.INVENTARIO_ARROLAMENTO:
            (new GIAITCDInventarioArrolamentoBe(conn)).verificarIsencaoImpostoMenorQueUmaUpfPorBeneficiario((GIAITCDInventarioArrolamentoVo) giaITCDVo);
            break;
         case DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA:
            (new GIAITCDSeparacaoDivorcioBe(conn)).verificarIsencaoImpostoMenorQueUmaUpfPorBeneficiario((GIAITCDSeparacaoDivorcioVo) giaITCDVo);
            break;
      }
      
      System.out.println("Valor UPF ITCD : " + giaITCDVo.getValorUPF());
      System.out.println("Valor ITCD : " + giaITCDVo.getValorITCD());
                  
       if(giaITCDVo instanceof GIAITCDDoacaoVo){
          GIAITCDDoacaoVo giaITCDDoacaoVo = (GIAITCDDoacaoVo) giaITCDVo;          
          boolean todosIsentos      = true;
          boolean todosDispensados = true;
          boolean existeIsento     = false;
          boolean existeDispensa   = false;
          
          for (Iterator it = giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext();) {
            GIAITCDDoacaoBeneficiarioVo giaDoacaoBeneficiario = (GIAITCDDoacaoBeneficiarioVo) it.next();
            Integer isento= giaDoacaoBeneficiario.getInfoIsencao(); 
            Integer dispensa = giaDoacaoBeneficiario.getInfoDispensaRecolhimento();  
            
            if (Integer.valueOf(2).equals(isento)) {
               existeIsento = true;
            }else{
               todosIsentos = false;
            }
            
            if (Integer.valueOf(2).equals(dispensa)) {
               existeDispensa = true;
            }else{
               todosDispensados = false;
            }            
            
          }
          if (todosIsentos) {
             System.out.println("Gia Isenta");
             giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.ISENTO));
             giaITCDVo.getStatusVo().setServidor(giaITCDVo.getServidorSefazIntegracaoVo());
             alterarStatusGIAITCD(giaITCDVo);
             gerarNumrDeclaracaoIsencaoAlterarGia(giaITCDVo);
             return true;
          }
          else if (todosDispensados) {
             System.out.println("Gia com dispensa de Recolhimento - Imposto até 1 UPF/MT");
             giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.DISPENSADO_RECOLHIMENTO));
             giaITCDVo.getStatusVo().setServidor(giaITCDVo.getServidorSefazIntegracaoVo());
             alterarStatusGIAITCD(giaITCDVo);
             gerarNumrDeclaracaoIsencaoAlterarGia(giaITCDVo);
             return true;
          }
          else if (existeIsento && existeDispensa) {
             System.out.println("Gia com dispensa de Recolhimento e Isenção - Imposto até 1 UPF/MT");
             giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.DISPENSA_E_ISENCAO));
             giaITCDVo.getStatusVo().setServidor(giaITCDVo.getServidorSefazIntegracaoVo());
             alterarStatusGIAITCD(giaITCDVo);
             gerarNumrDeclaracaoIsencaoAlterarGia(giaITCDVo);
             return true;
          }       
       }
       
      if(giaITCDVo instanceof GIAITCDSeparacaoDivorcioVo){
         GIAITCDSeparacaoDivorcioVo giaITCDDivorcioVo = (GIAITCDSeparacaoDivorcioVo) giaITCDVo;
         
         Integer infoDispensaRecolhimento = giaITCDDivorcioVo.getConjugeExcesso().getInfoDispensaRecolhimento();
         Integer infoIsencao = giaITCDDivorcioVo.getConjugeExcesso().getInfoIsencao();
         
         if(infoDispensaRecolhimento == 2){
            System.out.println("Gia com dispensa de Recolhimento - Imposto até 1 UPF/MT");
            giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.DISPENSADO_RECOLHIMENTO));
            giaITCDVo.getStatusVo().setServidor(giaITCDVo.getServidorSefazIntegracaoVo());
            alterarStatusGIAITCD(giaITCDVo);
            gerarNumrDeclaracaoIsencaoAlterarGia(giaITCDVo);
            return true;
         }
         if(infoIsencao == 2){
            System.out.println("Gia Isenta");
            giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.ISENTO));
            giaITCDVo.getStatusVo().setServidor(giaITCDVo.getServidorSefazIntegracaoVo());
            alterarStatusGIAITCD(giaITCDVo);
            gerarNumrDeclaracaoIsencaoAlterarGia(giaITCDVo);
            return true;
         }
         
      }
      
      if(giaITCDVo instanceof GIAITCDInventarioArrolamentoVo){
         GIAITCDInventarioArrolamentoVo giaITCDInventarioVo = (GIAITCDInventarioArrolamentoVo) giaITCDVo;
         
         boolean todosIsentos      = true;
         boolean todosDispensados = true;
         boolean existeIsento     = false;
         boolean existeDispensa   = false;
         
         for (Iterator it = giaITCDInventarioVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext();) {
           GIAITCDInventarioArrolamentoBeneficiarioVo giaInventarioBeneficiario = (GIAITCDInventarioArrolamentoBeneficiarioVo) it.next();
           Integer isento= giaInventarioBeneficiario.getInfoIsencao(); 
           Integer dispensa = giaInventarioBeneficiario.getInfoDispensaRecolhimento();  
           
           if (Integer.valueOf(2).equals(isento)) {
              existeIsento = true;
           }else{
              todosIsentos = false;
           }
           
           if (Integer.valueOf(2).equals(dispensa)) {
              existeDispensa = true;
           }else{
              todosDispensados = false;
           }            
           
         }
         
         if (todosIsentos) {
            System.out.println("Gia Isenta");
            giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.ISENTO));
            giaITCDVo.getStatusVo().setServidor(giaITCDVo.getServidorSefazIntegracaoVo());
            alterarStatusGIAITCD(giaITCDVo);
            gerarNumrDeclaracaoIsencaoAlterarGia(giaITCDVo);
            return true;
         }
         else if (todosDispensados) {
            System.out.println("Gia com dispensa de Recolhimento - Imposto até 1 UPF/MT");
            giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.DISPENSADO_RECOLHIMENTO));
            giaITCDVo.getStatusVo().setServidor(giaITCDVo.getServidorSefazIntegracaoVo());
            alterarStatusGIAITCD(giaITCDVo);
            gerarNumrDeclaracaoIsencaoAlterarGia(giaITCDVo);
            return true;
         }
         else if (existeIsento && existeDispensa) {
            System.out.println("Gia com dispensa de Recolhimento e Isenção - Imposto até 1 UPF/MT");
            giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.DISPENSA_E_ISENCAO));
            giaITCDVo.getStatusVo().setServidor(giaITCDVo.getServidorSefazIntegracaoVo());
            alterarStatusGIAITCD(giaITCDVo);
            gerarNumrDeclaracaoIsencaoAlterarGia(giaITCDVo);
            return true;
         } 
      }
             
      return false;
   }


   public void gerarNumrDeclaracaoIsencaoAlterarGia(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                      ConsultaException, 
                                                                                      ConexaoException, 
                                                                                      ParametroObrigatorioException, 
                                                                                      IntegracaoException, 
                                                                                      SQLException, 
                                                                                      LogException, 
                                                                                      AnotacaoException, 
                                                                                      PersistenciaException, 
             IOException
   {
      if (!Validador.isNumericoValido(giaITCDVo.getNumrDeclaracaoIsencao()))
      {
         GIAITCDVo original = obterGIAITCDParaAlterar(giaITCDVo);
         SefazSequencia sequence = new SefazSequencia(conn);
         giaITCDVo.setNumrDeclaracaoIsencao(sequence.next(SequencesITC.SEQUENCE_GIA_ITCD_DECLARACAO_ISENCAO));
         original.setNumrDeclaracaoIsencao(giaITCDVo.getNumrDeclaracaoIsencao());
         original.setLogSefazVo(giaITCDVo.getLogSefazVo());
         alterar(original);
      }
   }

   /**
    * Método responsável validar se a GIA-ITCD foi avaliada, e em caso afirmativo gerar o devido status
    * após sua avaliação.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws RegistroExistenteException
    * @throws IntegracaoException
    * @implemented by Lucas Nascimento
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void validaImprimirDocumentosAvaliacao(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                   LogException, 
                                                                                   PersistenciaException, 
                                                                                   AnotacaoException, 
                                                                                   RegistroNaoPodeSerUtilizadoException, 
                                                                                   ConsultaException, 
                                                                                   ConexaoException, 
                                                                                   ParametroObrigatorioException, 
                                                                                   RegistroExistenteException, 
                                                                                   IntegracaoException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      StatusGIAITCDBe statusBe = new StatusGIAITCDBe(conn);
      //Verificando se a Gia esta Avaliada, caso contrário sobe Exception
      try
      {
         try
         {
            if (statusBe.verificaExisteStatusGIAITCD(giaITCDVo.getStatusVo(), new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO)))
            {
               // Altera o STATUS_IMPRESSO da avaliacão para SIM.
               new AvaliacaoBemTributavelBe(conn).alterarStausAvaliacaoImpressa( giaITCDVo , new DomnSimNao(DomnSimNao.SIM) );
               
               //Gerando demonstrativo de Cálculo com os valores atuais               
               if (Validador.isNumericoValido(giaITCDVo.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula()))
               {
                  giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO));
                  giaITCDVo.getStatusVo().setDataEmissaoRetificacao(new Date());
                  giaITCDVo.getStatusVo().setServidor(giaITCDVo.getServidorSefazIntegracaoVo());
                  alterarStatusGIAITCD(giaITCDVo);
                  if (!gerarNaoOcorrenciaFatoGerador(giaITCDVo))
                  {
                     gerarIsencao(giaITCDVo);
                  }
                  commit();
                  giaITCDVo.setGiaRetificada(true);
                  return;
               }
               if (isBensRetificados(giaITCDVo) && (!giaITCDVo.isGiaIsentaUpf()))
               {
                  giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO));
                  giaITCDVo.getStatusVo().setDataEmissaoRetificacao(new Date());
                  giaITCDVo.getStatusVo().setServidor(giaITCDVo.getServidorSefazIntegracaoVo());
                  alterarStatusGIAITCD(giaITCDVo);
                  if (!gerarNaoOcorrenciaFatoGerador(giaITCDVo))
                  {
                     gerarIsencao(giaITCDVo);
                  }
                  commit();
                  giaITCDVo.setGiaRetificada(true);
                  return;
               }
               if (gerarNaoOcorrenciaFatoGerador(giaITCDVo))
               {
                  commit();
                  return;
               }           
               
               //Verificando se a Gia Calculada resultou em valor ITCD ZERO. Com isso o Status da GIA será ISENTA
               if (gerarIsencao(giaITCDVo))
               {
                  commit();
                  return;
               }          
               // Isencao de UPF deve ser a ultima verificação feita
               System.out.println("Valor ITCD: " + giaITCDVo.getValorITCD());
               if (verificarIsencaoImpostoMenorQueUmaUpf(giaITCDVo))
               {
                  commit();
                  return;
               }
               /*Caso durante a Interação dos Bens Tributáveis não tenha ocorrido nenhuma retificação de valores
						o Status da GIA será alterado para NOTIFICADO 
                */
               giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.NOTIFICADO));
               giaITCDVo.getStatusVo().setDataNotificacao(new Date());
               giaITCDVo.getStatusVo().setServidor(giaITCDVo.getServidorSefazIntegracaoVo());
               alterarStatusGIAITCD(giaITCDVo);              

               commit();
            } else
            {
               throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMPRIMIR_DOCUMENTOS_AVALIACAO_GIA_NAO_AVALIADA);
            }
         } catch (IntegracaoException e)
         {
            e.printStackTrace();
            conn.rollback();
            throw e;
         } catch (ConexaoException e)
         {
            e.printStackTrace();
            conn.rollback();
            throw e;
         } catch (ParametroObrigatorioException e)
         {
            e.printStackTrace();
            conn.rollback();
            throw e;
         } catch (RegistroExistenteException e)
         {
            e.printStackTrace();
            conn.rollback();
            throw e;
         } catch (PersistenciaException e)
         {
            e.printStackTrace();
            conn.rollback();
            throw e;
         } catch (LogException e)
         {
            e.printStackTrace();
            conn.rollback();
            throw e;
         } catch (AnotacaoException e)
         {
            e.printStackTrace();
            conn.rollback();
            throw e;
         } catch (RegistroNaoPodeSerUtilizadoException e)
         {
            e.printStackTrace();
            conn.rollback();
            throw e;
         } catch (ConsultaException e)
         {
            e.printStackTrace();
            conn.rollback();
            throw e;
         }
      } catch (SQLException e)
      {
         throw new ConexaoException(MensagemErro.ALTERAR_GIA_ITCD);
      }
   }

   /**
    * Método responsável por verificar se a opção 'Bem Particular' deve ou não ser exibido na aba bens tributáveis.
    * @param giaITCDVo
    * @return boolean
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static boolean exibeBemParticular(final GIAITCDVo giaITCDVo)
   {
      if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
      {
         GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo = (GIAITCDInventarioArrolamentoVo) giaITCDVo;
         if (giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CASADO))
         {
            if (giaITCDInventarioArrolamentoVo.getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS))
            {
               return true;
            } else
            {
               return false;
            }
         } else if (giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CONVIVENTE))
         {
            return true;
         }
         return false;
      } else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
      {
         GIAITCDSeparacaoDivorcioVo gIAITCDSeparacaoDivorcioVo = (GIAITCDSeparacaoDivorcioVo) giaITCDVo;
         if (gIAITCDSeparacaoDivorcioVo.getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS))
         {
            return true;
         }
         return false;
      }
      return false;
   }

   /**
    * Método responsável por configurar a visualização da opção Isenção Prevista em Lei para aba Bens Tributáveis.
    * @param giaITCDVo
    * @return boolean
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static boolean exibeIsencaoPrevistaEmLei(final GIAITCDVo giaITCDVo)
   {
      if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
      {
         return false;
      } else if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.DOACAO))
      {
         return giaITCDVo.getNaturezaOperacaoVo().getFlagIsencaoPrevistaLei().is(DomnSimNao.SIM);
      }
      return true;
   }

   /**
    * @param giaITCDVo
    * @return
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static boolean exibeTipoOutrosBens(final GIAITCDVo giaITCDVo)
   {
      if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.DOACAO))
      {
         if (Validador.isNumericoValido(giaITCDVo.getResponsavelVo().getNumrContribuinte()))
         {
            return giaITCDVo.getResponsavelVo().getSiglaUF().equals(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO);
         }
      }
      return true;
   }

   /**
    * Método responsável por pesquisar os dados do contribuinte, validar parametros e enviar e-mail ao contribuinte informando sua senha.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws EmailParametroObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ImpossivelCriptografarException
    * @throws EmailUnsupportedEncodingException
    * @throws EmailMessagingException
    * @implemented by João Batista Padilha e Silva
    * @implemented by Rogério Sanches de Oliveira
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void enviarEmailSenhaEsquecida(GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                     EmailParametroObrigatorioException, 
                                                                     ParametroObrigatorioException, 
                                                                     ConsultaException, 
                                                                     ConexaoException, 
                                                                     IntegracaoException, 
                                                                     ImpossivelCriptografarException, 
                                                                     EmailUnsupportedEncodingException, 
                                                                     EmailMessagingException, 
             SQLException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDConsultaVo = new GIAITCDVo();
      GIAITCDVo giaITCDParametroVo = new GIAITCDVo();
      giaITCDParametroVo.setCodigo(giaITCDVo.getCodigo());
      giaITCDParametroVo.setNumrDeclaracaoFatoGerador(giaITCDVo.getNumrDeclaracaoFatoGerador());
      giaITCDParametroVo.setNumrDeclaracaoIsencao(giaITCDVo.getNumrDeclaracaoIsencao());
      giaITCDConsultaVo.setParametroConsulta(giaITCDParametroVo);


      giaITCDConsultaVo = consultarGIAITCDUsuarioNaoLotado(giaITCDConsultaVo);
      if(giaITCDConsultaVo.getResponsavelVo().getNumrContribuinte().longValue() != giaITCDVo.getResponsavelVo().getNumrContribuinte().longValue())
      {
         throw new EmailParametroObrigatorioException(MensagemErro.VALIDAR_EMAIL_CONTRIBUINTE_INVALIDO);
      }
      
      String senhaRecuperada = giaITCDConsultaVo.getSenha();
      String novaSenha = Seguranca.descriptografar(senhaRecuperada, "senha");
      String nomeContribuite = giaITCDVo.getResponsavelVo().getNomeContribuinte();
      if (!Validador.isEmailValido(giaITCDVo.getResponsavelVo().getEmail()))
      {
         throw new EmailParametroObrigatorioException(MensagemErro.VALIDAR_EMAIL_CONTRIBUINTE_ENVIAR_SENHA);
      } else
      {
         String[] emailDestinatario =
         { giaITCDVo.getResponsavelVo().getEmail() };
         String[] nomeDestinatario =
         { giaITCDVo.getResponsavelVo().getNomeContribuinte() };
         String assunto = "ITCD, Solicitação de nova senha";
         String corpo = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n" + "<html>\n" + "  <head>\n" + "     <body>\n" + "         <title> Envio de senha por e-mail para contribuinte </title>\n" + "     </body>\n" + "      <table align=\"center\" border=\"0\" width=\"740\" cellspacing=\"0\" cellpadding=\"0\">\n" + "               <tr>\n" + "                <td align=\"center\"><B>NOTIFICAÇÃO AO CONTRIBUINTE</B></td>\n" + "              </tr>\n" + "               <tr>\n" + "                <td>&nbsp; \n" + "                  </td>\n" + "               <tr>\n" + "             <tr>\n" + "                  <td colspan=\"2\" align=\"center\">Prezado(a)Contribuinte</td>\n" + "               </tr>\n" + "               <tr>\n" + "                  <td>Nome: " + nomeContribuite + "</td>\n" + "               </tr>\n" + "               <br>\n" + "             <tr>\n" + "                  <td>De acordo com o solicitado,uma nova senha para acessar ao sistema ITCD foi gerada  automaticamente pelo sistema.</td>\n" + "               </tr>\n" + "               <br>\n" + "             <tr>\n" + "                <td> Anote sua nova senha: " + novaSenha + "</td>\n" + "               </tr>\n" + "               <br>\n" + "             <tr>\n" + "                  <td> Solicitamos que anote a senha gerada, para futuro acesso.</td>\n" + "             </tr>\n" + "               <br>\n" + "             <tr>\n" + "                  <td><h2>AVISO:</h2> Este e-mail foi gerado automaticamente.Por favor, não responsa este e-mail.</td>\n" + "               </tr>\n" + "<h3>AVISO LEGAL:</h3> Esta mensagem e arquivo(s) podem conter informações confidênciais e/ou legalmente protegidas.\n" + "               Caso tenha recebido por engano, favor devolvê-la ao remetente e eliminá-la do seu sistema, não divulgando \n" + "               ou utilizando parte ou a totalidade desta mensagem ou dos documentos a ela anexados.\n" + "<br>\n" + "               \n" + "<h3>LEGAL NOTICE:</h3> This message and attached document(s) may contain information of confidential nature and/or\n" + "               legally protected. If you have received this message by mistake, please reply to the sender, eliminate it\n" + "               from your system and do not disclose or use this message or the attached documents, in whole or in part." + "         </table>\n" + "         </head>\n" + "       </html>";
         EmailBean emailBean = new EmailBean(assunto, corpo, emailDestinatario, ConfiguracaoEmail.EMAIL_REMETENTE, nomeDestinatario, ConfiguracaoEmail.NOME_REMETENTE, ConfiguracaoEmail.SMTP, ConfiguracaoEmail.PORTA);
         emailBean.setMimeCorpoEmail("text/html");
         new AbacoEmail().enviaEmail(emailBean);
      }
      giaITCDConsultaVo.setResponsavelVo(giaITCDVo.getResponsavelVo());
      giaITCDVo = giaITCDConsultaVo;
   }

   /**
    * <b>Objetivo</b>
    * 
    * Este método tem por objetivo criar um array com os grupos
    * de permissão de uma determinda funcionalidade.
    *
    * 
    * @param funcionalidadeOrigem número da funcionalidade
    * que será utilizada para verificar quais grupos tem permissão
    * para acessar essa fucionalidade.
    * 
    * @return DomnGrupoPermissao[] retorna um array
    * dos grupos com permissão para acessar uma funcionalidade.
    * 
    * @implemented by Ricardo Vitor de Oliveira Moraes ,  Dherkyan Ribeiro da Silva.
    */
   private DomnGrupoPermissao[] getPermissaoGIAITCD(int funcionalidadeOrigem)
   {
      Collection permissoesUsuario = new ArrayList();
      if (funcionalidadeOrigem == DomnFuncionalidadeOrigem.ALTERAR_GIA_ITCD)
      {
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_AGENFA));
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GERENCIA));
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GIOR));
      } else if (funcionalidadeOrigem == DomnFuncionalidadeOrigem.INATIVAR_GIA_ITCD)
      {
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_GIOR));
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AGENFA));
      } else if (funcionalidadeOrigem == DomnFuncionalidadeOrigem.REATIVAR_GIA_ITCD)
      {
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_GIOR));
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AGENFA));
      } else if (funcionalidadeOrigem == DomnFuncionalidadeOrigem.ALTERAR_STATUS_MANUAL)
      {
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AGENFA));
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_GIOR));
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_CCF));
      } else if (funcionalidadeOrigem == DomnFuncionalidadeOrigem.AVALIAR_GIA_ITCD || funcionalidadeOrigem == DomnFuncionalidadeOrigem.ALTERAR_AVALIACAO_GIA_ITCD)
      {
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_AGENFA));
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GERENCIA));
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GIOR));
      } else if (funcionalidadeOrigem == DomnFuncionalidadeOrigem.IMPRIMIR_DOCUMENTOS_AVALIACAO)
      {
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_AGENFA));
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GERENCIA));
         permissoesUsuario.add(new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GIOR));
      } else if (funcionalidadeOrigem == DomnFuncionalidadeOrigem.CONSULTAR_GIA_ITCD)
      {
         permissoesUsuario = obterTodasPermissoesSistema();
      }
      return (DomnGrupoPermissao[]) permissoesUsuario.toArray(new DomnGrupoPermissao[permissoesUsuario.size()]);
   }

   /**
    * <b>Objetivo</b>
    * 
    * Este método tem por objetivo obter uma collection preenchidas com todos os grupos 
    * de permissões do sistema contidas em DomnGrupoPermissao.
    * 
    * @return Collection com todos os grupos do sistema
    * 
    * @implemented by Ricardo Vitor de Oliveira Moraes, Dherkyan Ribeiro da Silva.
    */
   private Collection obterTodasPermissoesSistema()
   {
      Collection permissoes = new ArrayList();
      for (int i = 0; i < new DomnGrupoPermissao().getDomnIndice().length; i++)
      {
         permissoes.add(new DomnGrupoPermissao(i + 1));
      }
      return permissoes;
   }

   /**
    * <b>Objetivo</b>
    * 
    * 
    * Retorna se o usuário lotado tem alguma das permissões passadas como parâmetro.
    * @param giaITCDVo
    * @param permissoes
    * @return boolean
    * @throws ConexaoException
    * @throws ObjetoObrigatorioException
    * @throws IntegracaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public boolean verificaUsuarioPossuiPermissao(GIAITCDVo giaITCDVo, DomnGrupoPermissao[] permissoes) throws ConexaoException, 
                                                                                                              ObjetoObrigatorioException, 
                                                                                                              IntegracaoException
   {
      if (Validador.isObjetoValido(giaITCDVo.getLogSefazVo().getUsuario()) && Validador.isArrayValido(permissoes))
      {
         ValidaPermissaoBe validaPermissaoBe = null;
         try
         {
            //TODO Não Utiliza a conexão do ITCD.
            validaPermissaoBe = new ValidaPermissaoBe();
            if (Validador.isNumericoValido(giaITCDVo.getLogSefazVo().getUsuario().getCodigo()))
            {
               return validaPermissaoBe.existePermissaoUsuario(giaITCDVo.getLogSefazVo().getUsuario().getCodigo(), permissoes);
            } else
            {
               return false;
            }


         } catch (SQLException e)
         {
            e.printStackTrace();
            throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
         } finally
         {
            if (validaPermissaoBe != null)
            {
               validaPermissaoBe.close();
               validaPermissaoBe = null;
            }
         }
      }
      return false;
   }

   /**
    * <b>Objetivo</b>
    * 
    * Este método tem por objetivo verificar se o usuário informado pertence
    * a algum dos grupos informados como parâmetro.
    * 

    * 
    * @return boolean true e somente true se o usuário informado
    * pertencer a algum dos grupos informados.
    * 
    * @param usuarioIntegracaoVo
    * @param permissoes
    * @throws ConexaoException
    * @throws ObjetoObrigatorioException
    * @throws IntegracaoException
    * 
    * 
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public boolean verificaUsuarioPossuiPermissao(UsuarioIntegracaoVo usuarioIntegracaoVo, DomnGrupoPermissao[] permissoes) throws ConexaoException, 
                                                                                                                                  ObjetoObrigatorioException, 
                                                                                                                                  IntegracaoException
   {
      if (Validador.isObjetoValido(usuarioIntegracaoVo) && Validador.isArrayValido(permissoes))
      {
         ValidaPermissaoBe validaPermissaoBe = null;
         try
         {
            // TODO Não utiliza a conexão do ITCD
            validaPermissaoBe = new ValidaPermissaoBe();
            return validaPermissaoBe.existePermissaoUsuario(usuarioIntegracaoVo.getCodigo().intValue(), permissoes);
         } catch (SQLException e)
         {
            e.printStackTrace();
            throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
         } finally
         {
            if (validaPermissaoBe != null)
            {
               validaPermissaoBe.close();
               validaPermissaoBe = null;
            }
         }
      }
      return false;
   }

   /**
    * Método responsável por verificar se o acesso está sendo realizado por um servidor SEFAZ, e caso seja validar se o mesmo possui permissão de acesso 
    * à funcionalidade.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ConexaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void verificaUsuarioAptoAcessoFuncionalidade(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                         IntegracaoException, 
                                                                                         RegistroNaoPodeSerUtilizadoException, 
                                                                                         ConexaoException
   {
      Validador.validaObjeto(giaITCDVo);
      verificaUsuarioServidor(giaITCDVo);
      switch (giaITCDVo.getOrigem())
      {
         case DomnFuncionalidadeOrigem.ALTERAR_GIA_ITCD:
            {
               if (giaITCDVo.isUsuarioServidor())
               {
                  if (verificaUsuarioPossuiPermissao(giaITCDVo, getPermissaoGIAITCD(giaITCDVo.getOrigem())))
                  {
                     giaITCDVo.setServidorSefazResponsavelAlteracaoVo(giaITCDVo.getServidorSefazIntegracaoVo());
                  } else
                  {
                     giaITCDVo.setServidorSefazResponsavelAlteracaoVo(null);
                     throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_ACESSO_USUARIO_FUNCIONALIDADE);
                  }
               } else
               {
                  giaITCDVo.setServidorSefazResponsavelAlteracaoVo(null);
               }
               break;
            }
         case DomnFuncionalidadeOrigem.INATIVAR_GIA_ITCD:
            {
               if (!(giaITCDVo.isUsuarioServidor() && verificaUsuarioPossuiPermissao(giaITCDVo, getPermissaoGIAITCD(giaITCDVo.getOrigem()))))
               {
                  throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_ACESSO_USUARIO_FUNCIONALIDADE);
               }
               break;
            }
         case DomnFuncionalidadeOrigem.REATIVAR_GIA_ITCD:
            {
               if (!(giaITCDVo.isUsuarioServidor() && verificaUsuarioPossuiPermissao(giaITCDVo, getPermissaoGIAITCD(giaITCDVo.getOrigem()))))
               {
                  throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_ACESSO_USUARIO_FUNCIONALIDADE);
               }
               break;
            }
         case DomnFuncionalidadeOrigem.ALTERAR_STATUS_MANUAL:
            {
               if (!(giaITCDVo.isUsuarioServidor() && verificaUsuarioPossuiPermissao(giaITCDVo, getPermissaoGIAITCD(giaITCDVo.getOrigem()))))
               {
                  throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_ACESSO_USUARIO_FUNCIONALIDADE);
               }
               break;
            }
         case DomnFuncionalidadeOrigem.AVALIAR_GIA_ITCD:
            {
               if (!(giaITCDVo.isUsuarioServidor() && verificaUsuarioPossuiPermissao(giaITCDVo, getPermissaoGIAITCD(giaITCDVo.getOrigem()))))
               {
                  throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_ACESSO_USUARIO_FUNCIONALIDADE);
               }
               break;
            }
         case DomnFuncionalidadeOrigem.ALTERAR_AVALIACAO_GIA_ITCD:
            {
               if (!(giaITCDVo.isUsuarioServidor() && verificaUsuarioPossuiPermissao(giaITCDVo, getPermissaoGIAITCD(giaITCDVo.getOrigem()))))
               {
                  throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_ACESSO_USUARIO_FUNCIONALIDADE);
               }
               break;
            }
         case DomnFuncionalidadeOrigem.IMPRIMIR_DOCUMENTOS_AVALIACAO:
            {
               if (!(giaITCDVo.isUsuarioServidor() && verificaUsuarioPossuiPermissao(giaITCDVo, getPermissaoGIAITCD(giaITCDVo.getOrigem()))))
               {
                  throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_ACESSO_USUARIO_FUNCIONALIDADE);
               }
               break;
            }
         default:
            {
               if (giaITCDVo.isUsuarioServidor() && !verificaUsuarioPossuiPermissao(giaITCDVo, getPermissaoGIAITCD(giaITCDVo.getOrigem())))
               {
                  throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_ACESSO_USUARIO_FUNCIONALIDADE);
               }
            }
      }
   }

   /**
    * Método responsável por verificar se o usuário que esta acessando a funcionalidade é um servidor SEFAZ.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws IntegracaoException
    * @throws ConexaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void verificaUsuarioServidor(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                         IntegracaoException, 
                                                                         ConexaoException
   {
      Validador.validaObjeto(giaITCDVo);
      ServidorSefazIntegracaoVo servidorSefazIntegracaoVo = new ServidorSefazIntegracaoVo();
      GestaoPessoasBe gestaoPessoasBe = null;
      try
      {
         // TODO Não utiliza a conexão do ITCD.
         gestaoPessoasBe = new GestaoPessoasBe(conn);
         try
         {
            servidorSefazIntegracaoVo.setNumrMatricula(StringUtil.toLongWrapper(giaITCDVo.getLogSefazVo().getUsuario().getIdentificacao()));
            servidorSefazIntegracaoVo = gestaoPessoasBe.buscarServidorSefazPorNumrMatricula(servidorSefazIntegracaoVo);
            giaITCDVo.setServidorSefazIntegracaoVo(servidorSefazIntegracaoVo);
            giaITCDVo.setUsuarioServidor(true);
         } catch (ParametroObrigatorioException e)
         {
            giaITCDVo.setUsuarioServidor(false);
            giaITCDVo.setServidorSefazIntegracaoVo(null);
         }
      } catch (Exception e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      } finally
      {
         if (gestaoPessoasBe != null)
         {
            gestaoPessoasBe.close();
            gestaoPessoasBe = null;
         }
      }
   }

   /**
    * Criado método responsável por preparar e solicitar a consulta de uma GIA-ITCD.
    * @param giaITCDVo
    * @return GIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo solicitarConsultarGIAITCD(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                ConsultaException, 
                                                                                ConexaoException, 
                                                                                ParametroObrigatorioException, 
                                                                                IntegracaoException, SQLException, 
             IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = new GIAITCDVo(giaITCDVo);
      giaITCDVoRetorno.setConsultaCompleta(true);
      giaITCDVoRetorno.setLogSefazVo(giaITCDVo.getLogSefazVo());
      giaITCDVoRetorno = consultarGIAITCD(giaITCDVoRetorno);
      giaITCDVoRetorno.setOrigem(giaITCDVo.getOrigem());
      giaITCDVoRetorno.setLogSefazVo(giaITCDVo.getLogSefazVo());
      giaITCDVoRetorno.setUsuarioServidor(giaITCDVo.isUsuarioServidor());
      giaITCDVoRetorno.setServidorSefazIntegracaoVo(giaITCDVo.getServidorSefazIntegracaoVo());
      return giaITCDVoRetorno;
   }

   /**
    * Criado método responsável por preparar e solicitar a consulta de uma GIA-ITCD.
    * @param giaITCDVo
    * @return GIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo solicitarConsultarGIAITCDAtiva(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                     ConsultaException, 
                                                                                     ConexaoException, 
                                                                                     ParametroObrigatorioException, 
                                                                                     IntegracaoException, SQLException, 
             IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = new GIAITCDVo();
      GIAITCDVo giaITCDVoConsulta = new GIAITCDVo();
      giaITCDVoConsulta.setCodigo(giaITCDVo.getCodigo());
      giaITCDVoConsulta.setSenha(giaITCDVo.getSenha());
      giaITCDVoRetorno.setParametroConsulta(giaITCDVoConsulta);
      giaITCDVoRetorno.setConsultaCompleta(true);
      giaITCDVoRetorno.setLogSefazVo(giaITCDVo.getLogSefazVo());
      giaITCDVoRetorno = consultarGIAITCD(giaITCDVoRetorno);
      giaITCDVoRetorno.setOrigem(giaITCDVo.getOrigem());
      giaITCDVoRetorno.setLogSefazVo(giaITCDVo.getLogSefazVo());
      giaITCDVoRetorno.setUsuarioServidor(giaITCDVo.isUsuarioServidor());
      giaITCDVoRetorno.setServidorSefazIntegracaoVo(giaITCDVo.getServidorSefazIntegracaoVo());
      giaITCDVoRetorno.setGiaRetificada(isBensRetificados(giaITCDVoRetorno));
      return giaITCDVoRetorno;
   }

   /**
    * Método responsável por retirar da lista de GIAS a GIA-ITCD selecionada na view.
    * @param giaITCDVo
    * @return GIAITCDVo
    * @throws ObjetoObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo selecionarGIAITCD(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = new GIAITCDVo();
      Iterator it = giaITCDVo.getCollVO().iterator();
      while (it.hasNext())
      {
         GIAITCDVo giaITCDAtualVo = (GIAITCDVo) it.next();
         if (giaITCDAtualVo.getCodigo() == giaITCDVo.getCodigo())
         {
            giaITCDVoRetorno = giaITCDAtualVo;
            break;
         }
      }
      giaITCDVoRetorno.setOrigem(giaITCDVo.getOrigem());
      giaITCDVoRetorno.setLogSefazVo(giaITCDVo.getLogSefazVo());
      giaITCDVoRetorno.setUsuarioServidor(giaITCDVo.isUsuarioServidor());
      giaITCDVoRetorno.setServidorSefazIntegracaoVo(giaITCDVo.getServidorSefazIntegracaoVo());
      return giaITCDVoRetorno;
   }

   /**
    * Método responsável por solicitar a consulta de uma GIA-ITCD e solicitar a validação se a mesma está apta para alteração.
    * @param giaITCDVo
    * @return GIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws SenhaInvalidaException
    * @throws ImpossivelCriptografarException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultarGIAITCDAlterar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                              ConsultaException, 
                                                                              ConexaoException, 
                                                                              ParametroObrigatorioException, 
                                                                              IntegracaoException, 
                                                                              RegistroNaoPodeSerUtilizadoException, 
                                                                              SenhaInvalidaException, 
                                                                              ImpossivelCriptografarException, SQLException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = solicitarConsultarGIAITCDAtiva(giaITCDVo);      
      String senhaDes = Seguranca.descriptografar(giaITCDVoRetorno.getSenha(), "senha");
      if (giaITCDVo.isUsuarioServidor())
      {
         if (!Validador.isNumericoValido(giaITCDVoRetorno.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula()))
         {
            giaITCDVoRetorno.setServidorSefazResponsavelAlteracaoVo(giaITCDVo.getServidorSefazResponsavelAlteracaoVo());
         }
      } else
      {
         String senha = Seguranca.criptografar(giaITCDVo.getSenha(), "senha");
         Seguranca.compararSenhas(senha, giaITCDVoRetorno.getSenha(), MensagemErro.VALIDAR_SENHA_INFORMADA_INVALIDA);
      }
      validaGIAITCDParaAlterar(giaITCDVoRetorno, giaITCDVo);
      return giaITCDVoRetorno;
   }

   public GIAITCDVo consultarGIAITCDInativar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                               ConsultaException, 
                                                                               ConexaoException, 
                                                                               ParametroObrigatorioException, 
                                                                               IntegracaoException, 
                                                                               RegistroNaoPodeSerUtilizadoException, SQLException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = solicitarConsultarGIAITCDAtiva(giaITCDVo);
      validaGIAITCDParaInativar(giaITCDVoRetorno);
      return giaITCDVoRetorno;
   }

   /**
    * Método responsável por solicitar a consulta de uma GIA-ITCD e solicitar a validação se a mesma está apta para alteração.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @return GIAITCDVo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultarGIAITCDAlterarStatusManual(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                          ConsultaException, 
                                                                                          ConexaoException, 
                                                                                          ParametroObrigatorioException, 
                                                                                          IntegracaoException, 
                                                                                          RegistroNaoPodeSerUtilizadoException, SQLException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = solicitarConsultarGIAITCD(giaITCDVo);
      validaGIAITCDParaAlterarStatusManual(giaITCDVoRetorno);
      return giaITCDVoRetorno;
   }

   /**
    * Método responsável por solicitar a consulta de uma GIA-ITCD e solicitar a validação se a mesma está apta para reativação.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @return GIAITCDVo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo consultarGIAITCDReativar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                               ConsultaException, 
                                                                               ConexaoException, 
                                                                               ParametroObrigatorioException, 
                                                                               IntegracaoException, 
                                                                               RegistroNaoPodeSerUtilizadoException, SQLException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = solicitarConsultarGIAITCD(giaITCDVo);
      validaGIAITCDParaReativar(giaITCDVoRetorno);
      return giaITCDVoRetorno;
   }

   /**
    * Método responsável por solicitar a consulta de uma GIA-ITCD e solicitar a validação se a mesma está apta para avaliação.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @return GIAITCDVo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo solicitarConsultarGIAITCDAvaliar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                       ConsultaException, 
                                                                                       ConexaoException, 
                                                                                       ParametroObrigatorioException, 
                                                                                       IntegracaoException, 
                                                                                       RegistroNaoPodeSerUtilizadoException, SQLException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = solicitarConsultarGIAITCDAtiva(giaITCDVo);
      validaGIAITCDParaAvaliar(giaITCDVoRetorno);
      return giaITCDVoRetorno;
   }

   /**
    * Método responsável por solicitar a consulta de uma GIA-ITCD e validar se a avaliação da mesma está apta a ser alterada.
    * @param giaITCDVo
    * @return GIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo solicitarConsultarGIAITCDAvaliarAlterar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                              ConsultaException, 
                                                                                              ConexaoException, 
                                                                                              ParametroObrigatorioException, 
                                                                                              IntegracaoException, 
                                                                                              RegistroNaoPodeSerUtilizadoException, SQLException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = solicitarConsultarGIAITCDAtiva(giaITCDVo);
      validaGIAITCDParaAvaliarAlterar(giaITCDVoRetorno);
      return giaITCDVoRetorno;
   }

   /**
    * Método responsável por solicitar a consulta de uma GIA-ITCD e solicitar a validação se a mesma está apta para imprimir os documentos da avaliação.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @return GIAITCDVo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo solicitarConsultarGIAITCDParaImprimirDocumentosAvaliacao(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                                               ConsultaException, 
                                                                                                               ConexaoException, 
                                                                                                               ParametroObrigatorioException, 
                                                                                                               IntegracaoException, 
                                                                                                               RegistroNaoPodeSerUtilizadoException, SQLException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = solicitarConsultarGIAITCDAtiva(giaITCDVo);
      validaGIAITCDParaImprimirDocumentosAvaliacao(giaITCDVoRetorno);
      return giaITCDVoRetorno;
   }

   /**
    * Método responsável por solicitar a seleção de uma GIA-ITCD e solicitar a consulta com validação da GIA selecionada verificando se a mesma está 
    * apta para alteração
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @return GIAITCDVo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo selecionarGIAITCDAlterar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                               RegistroNaoPodeSerUtilizadoException, 
                                                                               ConsultaException, 
                                                                               ConexaoException, 
                                                                               ParametroObrigatorioException, 
                                                                               IntegracaoException, 
                                                                               SenhaInvalidaException, 
                                                                               ImpossivelCriptografarException, 
             SQLException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = selecionarGIAITCD(giaITCDVo);
      giaITCDVoRetorno.setServidorSefazResponsavelAlteracaoVo(giaITCDVo.getServidorSefazResponsavelAlteracaoVo());
      return consultarGIAITCDAlterar(giaITCDVoRetorno);
   }

   /**
    * Método responsável por solicitar a seleção de uma GIA-ITCD e solicitar a consulta da GIA selecionada.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @return GIAITCDVo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo selecionarGIAITCDInativar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                ConsultaException, 
                                                                                ConexaoException, 
                                                                                ParametroObrigatorioException, 
                                                                                IntegracaoException, 
                                                                                RegistroNaoPodeSerUtilizadoException, SQLException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = selecionarGIAITCD(giaITCDVo);
      solicitarConsultarGIAITCDAtiva(giaITCDVoRetorno);
      validaGIAITCDParaInativar(giaITCDVoRetorno);
      return giaITCDVoRetorno;
   }

   /**
    * Método responsável por solicitar a seleção de uma GIA-ITCD e solicitar a consulta com validação da GIA selecionada verificando se a mesma está 
    * apta para reativação.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @return GIAITCDVo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo selecionarGIAITCDReativar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                RegistroNaoPodeSerUtilizadoException, 
                                                                                ConsultaException, 
                                                                                ConexaoException, 
                                                                                ParametroObrigatorioException, 
                                                                                IntegracaoException, SQLException, 
             IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = selecionarGIAITCD(giaITCDVo);
      return consultarGIAITCDReativar(giaITCDVoRetorno);
   }


   /**
    * Método responsável por validar se a GIA-ITCD passada como parâmetro está apta a ser alterada.
    * @param giaITCDRetornoVo
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validaGIAITCDParaAlterar(final GIAITCDVo giaITCDRetornoVo, final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                                             RegistroNaoPodeSerUtilizadoException, 
                                                                                                             ConexaoException, 
                                                                                                             IntegracaoException
   {
      Validador.validaObjeto(giaITCDVo);
      StatusGIAITCDVo status = giaITCDRetornoVo.getStatusVo();
      if (!giaITCDVo.isUsuarioServidor())
      {
         if(giaITCDRetornoVo.getTemporarioVo().getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1) || giaITCDRetornoVo.getTemporarioVo().getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_2))
         {
            throw new RegistroNaoPodeSerUtilizadoException("Senhor Contribuinte, essa GIA-ITCD foi iniciada em uma versão que não tem mais validade. Favor Inserir nova GIA-ITCD. Agradecemos a compreensão."); // 000033396/2015-45
         }        
      }
      if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.EM_ELABORACAO))
      {
         if (Validador.isNumericoValido(giaITCDVo.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula()))
         {
            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_GIAITCD_NAO_PROTOCOLADA);
         }
      }
      else if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO))
      {         
      if(giaITCDVo.isUsuarioServidor()){
         throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_GIAITCD_PENDENTE_PROTOCOLO); 
      }else{
            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_IMPRESSA_CONFIRMADA);  
            //throw new RegistroNaoPodeSerUtilizadoException("Senhor Contribuinte, essa GIA-ITCD foi iniciada em uma versão que não tem mais validade. Favor Inserir nova GIA-ITCD. Agradecemos a compreensão."); //	000033396/2015-45
         }
      }
      else if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLADO))
      {
         if (!Validador.isNumericoValido(giaITCDVo.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula()))
         {
            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_GIAITCD_PROTOCOLADA);
         }
         if (giaITCDRetornoVo.getStatusVo().getTipoAvaliacao().is(DomnTipoAvaliacao.AVALIACAO_CGED))
         {
            if (!verificaUsuarioPossuiPermissao(giaITCDVo, new DomnGrupoPermissao[]
                  { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GERENCIA), 
                    new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GIOR) }))
            {
               throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_SERVIDOR_NAO_TEM_PERMISSAO);
            }
         } else
         {
            if (verificaUsuarioPossuiPermissao(giaITCDVo, new DomnGrupoPermissao[]
                  { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_AGENFA) }))
            {
               /*
               if (giaITCDVo.getServidorSefazResponsavelAlteracaoVo().getUnidadeSefaz().getCodgUnidade().intValue() != giaITCDRetornoVo.getAgenciaAvaliacao().getCodgUnidade().intValue())
               {
                  throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_SERVIDOR_NAO_TEM_PERMISSAO);
               }
               */
            } else if (!verificaUsuarioPossuiPermissao(giaITCDVo, new DomnGrupoPermissao[]
                  { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GERENCIA), 
                    new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GIOR) }))
            {
               throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_SERVIDOR_NAO_TEM_PERMISSAO);
            }
         }
      } 
	  else if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR) || status.getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELA_GIOR))
      {
         if (giaITCDRetornoVo.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula().longValue() != giaITCDVo.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula().longValue() && !verificaUsuarioPossuiPermissao(giaITCDVo, new DomnGrupoPermissao[]
               { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GERENCIA), 
                 new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GIOR) }))
         {
            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_GIAITCD_GIA_ALTERADA_POR_OUTRO_SERVIDOR);
         }
      } 
	  else if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.INATIVO))
      {
         throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_CONSULTAR_GIA_ITCD_STATUS_INATIVO);
      } 
	  else
      {
         throw new RegistroNaoPodeSerUtilizadoException(status.getStatusGIAITCD().getTextoCorrente() + " " + MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_GIAITCD_GIA_STATUS_INVALIDO_ALTERACAO);
      }
   }

   /**
    * Método responsável por validar se a GIA-ITCD passada como parâmetro está apta a ser reativada.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validaGIAITCDParaReativar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                            RegistroNaoPodeSerUtilizadoException, 
                                                                            ParametroObrigatorioException, 
                                                                            ConsultaException
   {
      Validador.validaObjeto(giaITCDVo);
      if (!giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.INATIVO) && !giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.INATIVADO_AUTOMATICAMENTE))
      {
         throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_CONSULTAR_GIA_ITCD_STATUS_NAO_INATIVO);
      }
   }

   /**
    * @param listaPermissao
    * @return
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private Collection<Integer> obterListaStatusValidarAlterarStatus(Collection<Integer> listaPermissao)
   {
      Collection<Integer> retorno = new TreeSet<Integer>();
      for (Integer permissao: listaPermissao)
      {
         switch (permissao)
         {
            case DomnGrupoPermissao.CODG_GRUPO_PERM_CCF:
               {
                  retorno.add(DomnStatusGIAITCD.NOTIFICADO_CIENTE);
                  retorno.add(DomnStatusGIAITCD.PARCELADO);
                  retorno.add(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA);
                  retorno.add(DomnStatusGIAITCD.RETIFICADO_CIENTE);
                  retorno.add(DomnStatusGIAITCD.IMPUGNADO);
                  retorno.add(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE);
                  retorno.add(DomnStatusGIAITCD.RATIFICADO_CIENTE);
                  retorno.add(DomnStatusGIAITCD.ENVIADO_CCF);
                  break;
               }
            case DomnGrupoPermissao.CODG_GRUPO_PERM_AGENFA:
               {
                  break;
               }
            case DomnGrupoPermissao.CODG_GRUPO_PERM_GIOR:
               {
                  retorno.add(DomnStatusGIAITCD.IMPUGNADO);
                  retorno.add(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA);
                  retorno.add(DomnStatusGIAITCD.RETIFICADO);
                  retorno.add(DomnStatusGIAITCD.NOTIFICADO);
                  retorno.add(DomnStatusGIAITCD.PARCELADO);
                  retorno.add(DomnStatusGIAITCD.RATIFICADO);
                  retorno.add(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO);
                  retorno.add(DomnStatusGIAITCD.NOTIFICADO_CIENTE);
                  retorno.add(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE);
                  retorno.add(DomnStatusGIAITCD.RETIFICADO_CIENTE);
                  retorno.add(DomnStatusGIAITCD.RATIFICADO_CIENTE);
                  retorno.add(DomnStatusGIAITCD.ALTERADO_PELA_GIOR);
                  break;
               }
            default:
               {
                  retorno.add(DomnStatusGIAITCD.PROTOCOLADO);
                  retorno.add(DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR);
                  retorno.add(DomnStatusGIAITCD.ALTERADO_PELA_GIOR);
                  retorno.add(DomnStatusGIAITCD.AVALIADO);
                  retorno.add(DomnStatusGIAITCD.ISENTO);
                  retorno.add(DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR);
                  retorno.add(DomnStatusGIAITCD.QUITADO);
                  retorno.add(DomnStatusGIAITCD.QUITADO_MANUALMENTE);
                  retorno.add(DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA);
                  retorno.add(DomnStatusGIAITCD.INATIVO);
                  retorno.add(DomnStatusGIAITCD.INATIVADO_AUTOMATICAMENTE);
                  retorno.add(DomnStatusGIAITCD.EM_ELABORACAO);
                  retorno.add(DomnStatusGIAITCD.QUITADO_PELA_GIOR);
               }
         }
      }
      return retorno;
   }

   /**
    * @param status
    * @param permissao
    * @return
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private boolean validaAcessoNegadoAlterarStatusPelaPermissao(final DomnStatusGIAITCD status, Collection<Integer> permissao)
   {
      Collection<Integer> listaStatus = obterListaStatusValidarAlterarStatus(permissao);
      for (Integer statusAtual: listaStatus)
      {
         if (status.is(statusAtual))
         {
            return true;
         }
      }
      return false;
   }

   /**
    * @param status
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ObjetoObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validaStatusGiaParaAlterarStatus(final DomnStatusGIAITCD status) throws RegistroNaoPodeSerUtilizadoException, 
                                                                                        ObjetoObrigatorioException
   {
      Collection<Integer> listaPermissao = new ArrayList<Integer>();
      listaPermissao.add(0);
      if (validaAcessoNegadoAlterarStatusPelaPermissao(status, listaPermissao))
      {
         throw new RegistroNaoPodeSerUtilizadoException("GIA - ITCD " + status.getTextoCorrente() + ". " + MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_MANUAL);
      }
   }

   /**
    * Métodod responsável por validar de uma GIA-ITCD está apta a ser protocolada.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validaGIAITCDParaAlterarStatusManual(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                       RegistroNaoPodeSerUtilizadoException, 
                                                                                       ConexaoException, 
                                                                                       IntegracaoException
   {
      Validador.validaObjeto(giaITCDVo);
      if (giaITCDVo.getGiaConfirmada().is(DomnSimNao.NAO))
      {
         throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_NAO_CONFIRMADA);
      }
      //VERIFICA OS STATUS QUE NÃO PODEM SEM ALTERADOS MANUALMENTE
      validaStatusGiaParaAlterarStatus(giaITCDVo.getStatusVo().getStatusGIAITCD());
      //VERIFICA OS STATUS POR PERMISSÕES
      boolean isPermissaoAGENFA = verificaUsuarioPossuiPermissao(giaITCDVo, new DomnGrupoPermissao[]
            { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AGENFA) });
      boolean isPermissaoGIOR = verificaUsuarioPossuiPermissao(giaITCDVo, new DomnGrupoPermissao[]
            { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_GIOR) });
      boolean isPermissaoCCF = verificaUsuarioPossuiPermissao(giaITCDVo, new DomnGrupoPermissao[]
            { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_CCF) });

      if (giaITCDVo.isUsuarioServidor() && isPermissaoAGENFA)
      {
         if (!giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO))
         {
            if (!(giaITCDVo.getServidorSefazIntegracaoVo().getUnidadeSefaz().getCodgUnidade().intValue() == giaITCDVo.getAgenciaProtocolo().getCodgUnidade().intValue()))
            {
               throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.GIA_DEVERA_SER_ALTERADA_PELA_AGENFA_PROTOCOLO);
            }
         }
         else
         {
            if (!giaITCDVo.getServidorSefazIntegracaoVo().getUnidadeSefaz().getTipoUnidade().getCodgTipoUnidade().equals(ConfiguracaoITCD.CODIGO_TIPO_UNIDADE_SEFAZ_AGENFA))
            {
              // Retira a crítica se a unidade de lotação é do tipo ConfiguracaoITCD.CODIGO_TIPO_UNIDADE_SEFAZ_AGENFA
              // throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_ACESSO_USUARIO_NAO_LOTADO_AGENFA);
            }
         }
         giaITCDVo.getStatusVo().setCodigoAgenfa(giaITCDVo.getServidorSefazIntegracaoVo().getUnidadeSefaz().getCodgUnidade().intValue());
         giaITCDVo.setAgenciaProtocolo(giaITCDVo.getServidorSefazIntegracaoVo().getUnidadeSefaz());
      } else if (giaITCDVo.isUsuarioServidor())
      {
         Collection<Integer> listaPermissao = new ArrayList<Integer>();
         if (isPermissaoGIOR)
            listaPermissao.add(DomnGrupoPermissao.CODG_GRUPO_PERM_GIOR);
         if (isPermissaoCCF)
            listaPermissao.add(DomnGrupoPermissao.CODG_GRUPO_PERM_CCF);
         if (!validaAcessoNegadoAlterarStatusPelaPermissao(giaITCDVo.getStatusVo().getStatusGIAITCD(), listaPermissao))
         {
         // Retira a crítica se a unidade de lotação é do tipo ConfiguracaoITCD.CODIGO_TIPO_UNIDADE_SEFAZ_AGENFA
           // throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_ACESSO_USUARIO_FUNCIONAIDADE_ALTERAR_STATUS);
         }
         
      }
      
      if (giaITCDVo.isUsuarioServidor())
      {
         if( giaITCDVo.getStatusVo().getCodigoAgenfa() == 0 )
         {
            giaITCDVo.getStatusVo().setCodigoAgenfa(giaITCDVo.getServidorSefazIntegracaoVo().getUnidadeSefaz().getCodgUnidade().intValue());
         }
       
         if( giaITCDVo.getAgenciaProtocolo().getCodgUnidade() == 0)
         {
             giaITCDVo.setAgenciaProtocolo(giaITCDVo.getServidorSefazIntegracaoVo().getUnidadeSefaz());
         }
        
      }
      
   }

   /**
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validaGIAITCDParaInativar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                            ConexaoException, 
                                                                            IntegracaoException, 
                                                                            ParametroObrigatorioException, 
                                                                            ConsultaException, 
                                                                            RegistroNaoPodeSerUtilizadoException
   {
      Validador.validaObjeto(giaITCDVo);
      if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.INATIVO) || giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.INATIVADO_AUTOMATICAMENTE))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CONSULTAR_GIA_ITCD_STATUS_INATIVO);
      }
      if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA))
      {
         throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.GIA_ITCD_NAO_PODE_SER_INATIVADA);
      }
      if (verificaUsuarioPossuiPermissao(giaITCDVo, new DomnGrupoPermissao[]
            { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AGENFA) }))
      {
         if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO) || giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO) || giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_MANUALMENTE))
         {
            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_SEM_PERMISSAO_INATIVAR_GIA_ITCD);
         } else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA))
         {
            StatusGIAITCDVo statusAnterior = obterStatusAnteriorGIAITCD(giaITCDVo);
            if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO))
            {
               throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_SEM_PERMISSAO_INATIVAR_GIA_ITCD);
            }
         }
      }
      if(giaITCDVo instanceof GIAITCDDoacaoVo){
         GIAITCDDoacaoSucessivaBe giaDoacaoBe = new GIAITCDDoacaoSucessivaBe(conn);
         if(giaDoacaoBe.verificaSeGiaPresenteEmDoacaoSucessiva(giaITCDVo.getCodigo())){
            throw new RegistroNaoPodeSerUtilizadoException("A reativação ou inativação desta GIA-ITCD impactará diretamente no cálculo do imposto sobre doações sucessivas. Por favor, realize a consulta por CPF e certifique-se das informações antes de prosseguir com o procedimento.");
         }
      }      
   }

   /**
    * Este método valida os dados básicos e obrigatórios para a alteração das avaliações
    * dos bens tributáveis.
    * 
    * @param giaITCDVo
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validaGIAITCDParaAvaliar(final GIAITCDVo giaITCDVo) throws RegistroNaoPodeSerUtilizadoException, 
                                                                           ObjetoObrigatorioException, 
                                                                           ConexaoException, 
                                                                           IntegracaoException, 
                                                                           ConsultaException
   {
      StatusGIAITCDBe statusBe = null;
      try
      {
         statusBe = new StatusGIAITCDBe();
         boolean isProtocolada = statusBe.giaProtocolada(giaITCDVo);
         /** a gia foi confirmada? */
         if (!Validador.isDominioNumericoValido(giaITCDVo.getGiaConfirmada()))
         {
            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_NAO_CONFIRMADA);
         }
         if (!giaITCDVo.getStatusVo().isStatusIn(new int[]
               { DomnStatusGIAITCD.PROTOCOLADO, 
                 DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR, 
                 DomnStatusGIAITCD.ALTERADO_PELA_GIOR }))
         {
            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEM_AVALIACAO);
         }
         /** possui data de protocolo? */
         if (!isProtocolada)
         {
            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEM_DATA_PROTOCOLO);
         }
         validarIncluirServidorAvaliacao(giaITCDVo, giaITCDVo.getServidorSefazIntegracaoVo());
         if (statusBe.verificaStatusGIA(giaITCDVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO)))
         {
            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INCLUIR_AVALIACAO_GIA_AVALIADA);
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      } finally
      {
         if (statusBe != null)
         {
            statusBe.close();
            statusBe = null;
         }
      }
   }

   /**
    * <b>Objetivo</b>
    * 
    * Este método tem por objetivo automatizar a criação
    * do grupo de permissão GRUPO_PERM_AVALIADOR_GERENCIA ou GRUPO_PERM_AVALIADOR_AGENFA
    * para ser utilizado como parâmetro para outro método
    * por exemplo o método : verificaUsuarioPossuiPermissao(UsuarioIntegracaoVo usuarioIntegracaoVo, DomnGrupoPermissao[] permissoes)
    * 
    * 
    * @param tipoAvaliacao valor utilizado para definir qual tipo de permissão
    * será criado DomnTipoAvaliacao.AVALIACAO_CGED ou DomnTipoAvaliacao.AVALIACAO_AGENFA.
    * 
    * @return DomnGrupoPermissao[] um array representando o grupo de permissão GERENCIA ou AGENFA.
    * 
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private DomnGrupoPermissao[] getPermissaoGIAITCDTipoAvaliacao(int tipoAvaliacao)
   {
      DomnGrupoPermissao[] retorno = null;
      if (tipoAvaliacao == DomnTipoAvaliacao.AVALIACAO_CGED)
      {
         retorno = new DomnGrupoPermissao[]
               { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GERENCIA) };
      } else if (tipoAvaliacao == DomnTipoAvaliacao.AVALIACAO_AGENFA)
      {
         retorno = new DomnGrupoPermissao[]
               { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_AGENFA) };
      }
      return retorno;
   }

   private void validaAcessoUsuarioTipoAvaliacao(final GIAITCDVo giaITCDVo, final UsuarioIntegracaoVo usuario, boolean funcionalidadeAlterar) throws RegistroNaoPodeSerUtilizadoException, 
                                                                                                                                                     ConexaoException, 
                                                                                                                                                     ObjetoObrigatorioException, 
                                                                                                                                                     IntegracaoException
   {

      ValidadorStatusGIAITCD.validarFlagLocalAvaliacao(giaITCDVo);
      
      if (giaITCDVo.getStatusVo().getTipoAvaliacao().is(DomnTipoAvaliacao.AVALIACAO_CGED))
      {
         if (!funcionalidadeAlterar)
         {
            if (verificaUsuarioPossuiPermissao(usuario, getPermissaoGIAITCDTipoAvaliacao(DomnTipoAvaliacao.AVALIACAO_CGED)))
            {
               return;
            }
         }
         if (verificaUsuarioPossuiPermissao(usuario, new DomnGrupoPermissao[]
               { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GIOR) }))
         {
            return;
         }
      } else if (giaITCDVo.getStatusVo().getTipoAvaliacao().is(DomnTipoAvaliacao.AVALIACAO_AGENFA))
      {
         if (!funcionalidadeAlterar)
         {
            if (verificaUsuarioPossuiPermissao(usuario, getPermissaoGIAITCDTipoAvaliacao(DomnTipoAvaliacao.AVALIACAO_AGENFA)) && (giaITCDVo.getServidorSefazIntegracaoVo().getUnidadeSefaz().getCodgUnidade().intValue() == giaITCDVo.getAgenciaAvaliacao().getCodgUnidade().intValue()))
            {
               return;
            }
         }
         if (verificaUsuarioPossuiPermissao(usuario, getPermissaoGIAITCDTipoAvaliacao(DomnTipoAvaliacao.AVALIACAO_CGED)))
         {
            return;
         }
         if (verificaUsuarioPossuiPermissao(usuario, new DomnGrupoPermissao[]
               { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GIOR) }))
         {
            return;
         }
      }
      throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_NAO_TEM_ACESSO_AVALIACAO);
   }

   private void validaAcessoUsuarioTipoAvaliacao(final GIAITCDVo giaITCDVo, boolean funcionalidadeAlterar) throws ObjetoObrigatorioException, 
                                                                                                                  ConexaoException, 
                                                                                                                  IntegracaoException, 
                                                                                                                  RegistroNaoPodeSerUtilizadoException
   {
      validaAcessoUsuarioTipoAvaliacao(giaITCDVo, new UsuarioIntegracaoVo(giaITCDVo.getLogSefazVo().getUsuario().getCodigo()), funcionalidadeAlterar);
   }

   /**
    * @param giaITCDVo
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @implemented by Fernanda Silva Azevedo
    */
   private void validaGIAITCDParaAvaliarAlterar(final GIAITCDVo giaITCDVo) throws RegistroNaoPodeSerUtilizadoException, 
                                                                                  ObjetoObrigatorioException, 
                                                                                  ConexaoException, 
                                                                                  IntegracaoException, 
                                                                                  ConsultaException, 
                                                                                  ParametroObrigatorioException
   { 
   
      if(!ValidadorAvaliacao.isPossivelExcluirOuReabrirAvaliacaoDaGia(giaITCDVo))
      {
         throw new RegistroNaoPodeSerUtilizadoException("Esta GIA ainda não possui Avaliação");
      }
   
      /**
      * Método comentado por não considerar se pelo uns dos bens é passível de avaliação.
      *
     if(!new AvaliacaoBemTributavelBe(conn).validarBensPossuemAvaliacao(giaITCDVo.getBemTributavelVo()))
     {
        throw new RegistroNaoPodeSerUtilizadoException("Esta GIA ainda não possui Avaliação");
     }
     */

      if (!giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.AVALIADO))
      {
         boolean temp = true;
         StatusGIAITCDVo statusAnterior = new StatusGIAITCDBe(conn).obterStatusGIAITCDAnterior(giaITCDVo.getStatusVo());
         if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA))
         {
            if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
            {
               temp = false;
               giaITCDVo.setReabrirAvaliacao(true);
            } else if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO_CIENTE))
            {
               temp = false;
               giaITCDVo.setReabrirAvaliacao(true);
            } else if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))
            {
               temp = false;
               giaITCDVo.setReabrirAvaliacao(true);
            } else if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO_CIENTE))
            {
               temp = false;
               giaITCDVo.setReabrirAvaliacao(true);
            }else if( ( statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO) ||
               statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE) ||
               statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO_CIENTE)
            ) & verificaUsuarioPossuiPermissao(giaITCDVo, new DomnGrupoPermissao[]
                  { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GIOR) }))
            {
               // Permite somente os usuarios do grupo CODG_GRUPO_PERM_AVALIADOR_GIOR possam EXCLUIR a avalição.
               giaITCDVo.setExcluirAvaliacao(true);
               temp = false;
            }
         } else
         {
            if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
            {
               temp = false;
               giaITCDVo.setReabrirAvaliacao(true);
            } else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO_CIENTE))
            {
               temp = false;
               giaITCDVo.setReabrirAvaliacao(true);
            } else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))
            {
               temp = false;
               giaITCDVo.setReabrirAvaliacao(true);
            } else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO_CIENTE))
            {
               temp = false;
               giaITCDVo.setReabrirAvaliacao(true);
            } else if ( ( giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO) ||
               giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.IMPUGNADO) ||
               giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO) ||
               giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE) ||
               giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO) ||
               giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO_CIENTE) ||
               giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_CCF) ||
               giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ENVIADO_CCF) ||
               giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_PELA_GIOR) ||
               giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO) ||
               giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR) ||
               giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO) ||
               giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO_IMPOSTO_ATE_1_UPF) ||
               giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_MANUALMENTE)
            ) & verificaUsuarioPossuiPermissao(giaITCDVo, new DomnGrupoPermissao[]
                  { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GIOR) }))
            {
               // Permite somente os usuarios do grupo CODG_GRUPO_PERM_AVALIADOR_GIOR possam EXCLUIR a avalição.
               giaITCDVo.setExcluirAvaliacao(true);
               temp = false;
            }
         }

         if (temp)
         {
            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_NAO_TEM_ACESSO_AVALIACAO);
         }
      }

      if (!isServidorAvaliadorGIAITCD(giaITCDVo))
      {
         validaAcessoUsuarioTipoAvaliacao(giaITCDVo, true);
      }

      if (giaITCDVo.isReabrirAvaliacao())
      {
         giaITCDVo.setExcluirAvaliacao(giaITCDVo.isReabrirAvaliacao());
         if (isServidorAvaliadorGIAITCD(giaITCDVo))
         {
            // Usuario foi indicado como Avaliador por isso pode Reabrir ou Excluir a Avaliação.
         } else if (verificaUsuarioPossuiPermissao(giaITCDVo, new DomnGrupoPermissao[]
               { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AVALIADOR_GIOR) }))
         {
            // Usuario pertence ao grupo GIOR por isso tem permissão de alteração.
         } else
         {
            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_NAO_TEM_ACESSO_AVALIACAO);
         }
      }


   }

   /**
    * Métodod responsável por validar de uma GIA-ITCD está apta a imprimir os documentos de sua avaliação.
    * @param giaITCDVo
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validaGIAITCDParaImprimirDocumentosAvaliacao(final GIAITCDVo giaITCDVo) throws RegistroNaoPodeSerUtilizadoException, 
                                                                                               ObjetoObrigatorioException, 
                                                                                               ConexaoException, 
                                                                                               IntegracaoException, 
                                                                                               ConsultaException, 
                                                                                               ParametroObrigatorioException
   {
      Validador.validaObjeto(giaITCDVo);
      if (!giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.AVALIADO))
      {
         throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_NAO_AVALIADO);
      }
      if (!isServidorAvaliadorGIAITCD(giaITCDVo))
      {
         if (giaITCDVo.getBemTributavelVo().isExisteBemAvaliado())
         {
            validaAcessoUsuarioTipoAvaliacao(giaITCDVo, true);
         }
      }

   }

   /**
    * Método responsável por verificar se o servidor que está acessando a funcionaidade é um dos
    * servidores avaliador da GIA-ITCD.
    * @param giaITCDVo
    * @return boolean
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private boolean isServidorAvaliadorGIAITCD(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                ConsultaException, 
                                                                                IntegracaoException, 
                                                                                ParametroObrigatorioException
   {
      Validador.validaObjeto(giaITCDVo);	
      if (Validador.isCollectionValida(giaITCDVo.getBemTributavelVo().getCollVO()))
      {
         for (Iterator it = giaITCDVo.getBemTributavelVo().getCollVO().iterator(); it.hasNext(); )
         {
            BemTributavelVo bemAtual = (BemTributavelVo) it.next();
            if (isBemPassivelAvaliacao(giaITCDVo, bemAtual))
            {
               giaITCDVo.getBemTributavelVo().setExisteBemAvaliado(true);
               if (Validador.isCollectionValida(bemAtual.getAvaliacaoBemTributavelVo().getAvaliacaoBemTributavelServidorVo().getCollVO()))
               {
                  for (Iterator itAvaliador = bemAtual.getAvaliacaoBemTributavelVo().getAvaliacaoBemTributavelServidorVo().getCollVO().iterator(); itAvaliador.hasNext(); )
                  {
                     AvaliacaoBemTributavelServidorVo servidorAvaliacao = (AvaliacaoBemTributavelServidorVo) itAvaliador.next();
                     if (servidorAvaliacao.getServidorSefazVo().getNumrMatricula().equals(giaITCDVo.getServidorSefazIntegracaoVo().getNumrMatricula()))
                     {
                        return true;
                     }
                  }
               }
            }
         }
      }
      return false;
   }

   /**
    * 
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @throws ParametroObrigatorioException
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   private boolean isPermissaoParaExcluirReabrirAvalicao(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                           ConsultaException, 
                                                                                           IntegracaoException, 
                                                                                           ParametroObrigatorioException
   {
      Validador.validaObjeto(giaITCDVo);
      if (Validador.isCollectionValida(giaITCDVo.getBemTributavelVo().getCollVO()))
      {
         for (Iterator it = giaITCDVo.getBemTributavelVo().getCollVO().iterator(); it.hasNext(); )
         {
            BemTributavelVo bemAtual = (BemTributavelVo) it.next();
            if (isBemPassivelAvaliacao(giaITCDVo, bemAtual))
            {
               if (Validador.isCollectionValida(bemAtual.getAvaliacaoBemTributavelVo().getAvaliacaoBemTributavelServidorVo().getCollVO()))
               {
                  for (Iterator itAvaliador = bemAtual.getAvaliacaoBemTributavelVo().getAvaliacaoBemTributavelServidorVo().getCollVO().iterator(); itAvaliador.hasNext(); )
                  {
                     AvaliacaoBemTributavelServidorVo servidorAvaliacao = (AvaliacaoBemTributavelServidorVo) itAvaliador.next();
                     if (servidorAvaliacao.getServidorSefazVo().getNumrMatricula().equals(giaITCDVo.getServidorSefazIntegracaoVo().getNumrMatricula()))
                     {
                        return true;
                     }
                  }
               }
            }
         }
      }
      return false;
   }


   private boolean isAvaliacaoAtiva(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                      ConsultaException, 
                                                                      IntegracaoException, 
                                                                      ParametroObrigatorioException
   {
      Validador.validaObjeto(giaITCDVo);
      if (Validador.isCollectionValida(giaITCDVo.getBemTributavelVo().getCollVO()))
      {
         for (Iterator it = giaITCDVo.getBemTributavelVo().getCollVO().iterator(); it.hasNext(); )
         {
            BemTributavelVo bemAtual = (BemTributavelVo) it.next();
            if (isBemPassivelAvaliacao(giaITCDVo, bemAtual))
            {
               if (bemAtual.getAvaliacaoBemTributavelVo().getStatusAvaliacao().is(DomnAtivoInativo.INATIVO))
               {
                  return false;
               }
            }
         }
      }
      return true;
   }


   /**
    * Método responsável por solicitar a seleção de uma GIA-ITCD e solicitar a consulta com validação da GIA selecionada verificando se a mesma está 
    * apta para ser protocolada.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @return GIAITCDVo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo selecionarGIAITCDAlterarStatusManual(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                           RegistroNaoPodeSerUtilizadoException, 
                                                                                           ConsultaException, 
                                                                                           ConexaoException, 
                                                                                           ParametroObrigatorioException, 
                                                                                           IntegracaoException, SQLException, 
             IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = selecionarGIAITCD(giaITCDVo);
      return consultarGIAITCDAlterarStatusManual(giaITCDVoRetorno);
   }

   /**
    * Método responsável por solicitar a seleção de uma GIA-ITCD e solicitar a consulta da GIA selecionada.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @return GIAITCDVo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo selecionarGIAITCDAvaliar(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                               ConsultaException, 
                                                                               ConexaoException, 
                                                                               IntegracaoException, 
                                                                               RegistroNaoPodeSerUtilizadoException, 
                                                                               ParametroObrigatorioException, SQLException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = selecionarGIAITCD(giaITCDVo);
      if (giaITCDVoRetorno.getOrigem() == DomnFuncionalidadeOrigem.AVALIAR_GIA_ITCD)
      {
         return solicitarConsultarGIAITCDAvaliar(giaITCDVoRetorno);
      } else
      {
         return solicitarConsultarGIAITCDAvaliarAlterar(giaITCDVoRetorno);
      }
   }

   /**
    * Método responsável por solicitar a seleção de uma GIA-ITCD e solicitar a consulta da GIA selecionada.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @return GIAITCDVo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo selecionarGIAITCDImprimirDocumentosAvaliacao(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                                   ConsultaException, 
                                                                                                   ConexaoException, 
                                                                                                   IntegracaoException, 
                                                                                                   RegistroNaoPodeSerUtilizadoException, 
                                                                                                   ParametroObrigatorioException, SQLException, IOException
   {
      Validador.validaObjeto(giaITCDVo);
      GIAITCDVo giaITCDVoRetorno = selecionarGIAITCD(giaITCDVo);
      return solicitarConsultarGIAITCDParaImprimirDocumentosAvaliacao(giaITCDVoRetorno);
   }

   /**
    * Método responsável por listar GIA-ITCD pelo CPF do responsável pelo preenchimento.
    * @param giaItcdVo
    * @return GIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws ConexaoException
    * @implemented by Fernanda Silva Azevedo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo listarGIAITCDPorCPFResponsavelAvaliacao(GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, 
                                                                                        ConsultaException, 
                                                                                        ParametroObrigatorioException, 
                                                                                        IntegracaoException, 
                                                                                        ConexaoException
   {
      Validador.validaObjeto(giaItcdVo);
      GIAITCDVo giaRetorno = new GIAITCDVo();
      if (giaItcdVo.isConsultaParametrizada())
      {
         final long numeroContribuinte = (giaItcdVo.getParametroConsulta()).getResponsavelVo().getNumrContribuinte().longValue();
         ContribuinteIntegracaoVo responsavel = new ContribuinteIntegracaoVo();
         responsavel.setNumrContribuinte(new Long(numeroContribuinte));
         responsavel = new ContribuinteIntegracaoVo(responsavel);
         responsavel = (new CadastroBe(conn)).obterContribuinte(responsavel);
         if (!Validador.isNumericoValido(responsavel.getNumrContribuinte().longValue()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_NAO_EXISTE_CONTRIBUINTE);
         }
         GIAITCDQDao giaQDao = new GIAITCDQDao(conn);
         GIAITCDVo giaAntigo = new GIAITCDVo();
         GIAITCDVo giaConsulta = new GIAITCDVo();
         giaAntigo.setResponsavelVo(responsavel);
         giaAntigo = new GIAITCDVo(giaAntigo);
         giaConsulta = giaQDao.listGIAITCDPorCPFResponsavel(giaAntigo);
         giaRetorno.getCollVO().addAll(giaConsulta.getCollVO());
         GIAITCDTemporarioVo temp = new GIAITCDTemporarioVo();
         temp.setCodigoResponsavel(numeroContribuinte);
         temp = new GIAITCDTemporarioVo(temp);
         temp = (new GIAITCDTemporarioBe(conn)).listarGIAITCDTemporarioAtivas(temp);
         // se foi encontrada GIA com o código informado
         for (Iterator iteGia = temp.getCollVO().iterator(); iteGia.hasNext(); )
         {
            GIAITCDTemporarioVo giaTemporario = (GIAITCDTemporarioVo) iteGia.next();
            if ((!giaTemporario.getStatusITCD().getStatusGIAITCD().is(DomnStatusGIAITCD.INATIVO)) && (!giaTemporario.getStatusITCD().getStatusGIAITCD().is(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO)) && (!giaTemporario.getStatusITCD().getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLADO)) && (!giaTemporario.getStatusITCD().getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR)) && (!giaTemporario.getStatusITCD().getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR)) && (!giaTemporario.getStatusITCD().getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELA_GIOR)))
            {
               giaRetorno.getCollVO().add(adequarCollectionGIAITCDTemporaria(giaTemporario.getGiaitcdVo()));
            }
         }
         // se foi encontrada GIA com o código informado
         if (giaRetorno.getCollVO().isEmpty())
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_NAO_EXISTE_CONTRIBUINTE);
         }
         Collections.sort((List<GIAITCDVo>) giaRetorno.getCollVO(), new Comparator<GIAITCDVo>()
               {
                  public int compare(GIAITCDVo o1, GIAITCDVo o2)
                  {
                     return ((new Long(o1.getCodigo())).compareTo(new Long(o2.getCodigo())));
                  }
               });
      } else
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_CONSULTA);
      }
      return giaRetorno;
   }

   /**
    * Método responsável por validar se na alteração realizada pelo servidor, o campo justificativa alteração foi devidamente informado.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void validaJustificativaAlteracao(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                     ParametroObrigatorioException
   {
      Validador.validaObjeto(giaITCDVo);
      if (giaITCDVo.isUsuarioServidor())
      {
         if (!Validador.isStringValida(giaITCDVo.getJustificativaAlteracao()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_CAMPO_JUSTIFICATIVA_ALTERACAO);
         }
      }
   }

   /**
    * Método responsável por verificar se o servidor informado pode ser incluído como avaliador da GIA-ITCD.
    * @param giaITCDVo
    * @param servidorVo
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void validarIncluirServidorAvaliacao(final GIAITCDVo giaITCDVo, final ServidorSefazIntegracaoVo servidorVo) throws ObjetoObrigatorioException, 
                                                                                                                             ConexaoException, 
                                                                                                                             IntegracaoException, 
                                                                                                                             RegistroNaoPodeSerUtilizadoException
   {
      Validador.validaObjeto(giaITCDVo);
      UsuarioIntegracaoVo usuarioIntegracaoVo = new UsuarioIntegracaoVo();
      if (Validador.isNumericoValido(servidorVo.getNumrMatricula()))
      {
         UsuarioIntegracaoVo usuarioIntegracaoConsultaVo = new UsuarioIntegracaoVo();
         UsuarioIntegracaoVo usuarioIntegracaoParametroVo = new UsuarioIntegracaoVo();
         usuarioIntegracaoParametroVo.setIdentificacao(servidorVo.getNumrMatricula().toString());
         usuarioIntegracaoConsultaVo.setParametroConsulta(usuarioIntegracaoParametroVo);
         usuarioIntegracaoConsultaVo = new AcessoWebBe(conn).obterUsuarioPorIdentificacao(usuarioIntegracaoConsultaVo);
         if (!Validador.isNumericoValido(usuarioIntegracaoConsultaVo.getCodigo()))
         {
            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_SERVIDOR_USUARIO_ACESSO_WEB);
         }
         usuarioIntegracaoVo = usuarioIntegracaoConsultaVo;

      }
      validaAcessoUsuarioTipoAvaliacao(giaITCDVo, usuarioIntegracaoVo, false);

      switch (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente())
      {
         case (DomnTipoProcesso.INVENTARIO_ARROLAMENTO):
            {
               GIAITCDInventarioArrolamentoBe.validarIncluirServidorAvaliacao((GIAITCDInventarioArrolamentoVo) giaITCDVo, servidorVo);
               break;
            }
         case (DomnTipoProcesso.DOACAO):
            {
               GIAITCDDoacaoBe.validarIncluirServidorAvaliacao((GIAITCDDoacaoVo) giaITCDVo, servidorVo);
               break;
            }
         case (DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA):
            {
               GIAITCDSeparacaoDivorcioBe.validarIncluirServidorAvaliacao((GIAITCDSeparacaoDivorcioVo) giaITCDVo, servidorVo);
               break;
            }
      }
   }

   /**
    * Método responsável por listar GIA-ITCD.
    * @param giaItcdVo
    * @return GIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws ConexaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public GIAITCDVo listarGIAITCD(GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, 
                                                              ConsultaException, 
                                                              ParametroObrigatorioException, 
                                                              IntegracaoException, 
                                                              ConexaoException
   {
      Validador.validaObjeto(giaItcdVo);
      GIAITCDVo giaItcdTemporarioVo = null;
      if (giaItcdVo.isConsultaParametrizada())
      {
         GIAITCDQDao gIAITCDQDao = new GIAITCDQDao(conn);
         giaItcdTemporarioVo = gIAITCDQDao.listGIAITCD(giaItcdVo);
         if (giaItcdTemporarioVo.isExisteCollVO())
         {
            giaItcdVo.setCollVO(giaItcdTemporarioVo.getCollVO());
         }
      }
      return giaItcdVo;
   }

   /**
    * Este método calcula a aliquota em modo normal(sem cascata).
    * 
    * @param aliquotas listagem de aliquotas aplicáveis à legislacao em questão
    * @param valorRecebido valor recebido por cada beneficiário
    * @param valorUPF valor corrente da UPF
    * @param tipoFatoGerador
    * @return AliquotaVo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static AliquotaVo encontraAliquotaNormal(Collection aliquotas, double valorRecebido, double valorUPF, int tipoFatoGerador)
   {
      if (Validador.isNumericoValido(valorRecebido))
      {
         for (Iterator it = aliquotas.iterator(); it.hasNext(); )
         {
            AliquotaVo atual = (AliquotaVo) it.next();
            double upfInicio = (atual.getQuantidadeUPFInicial() - 1) * valorUPF;
            double upfFim = atual.getQuantidadeUPFFinal() * valorUPF;
            if (atual.getTipoFatoGerador().is(tipoFatoGerador))
            {
               if (valorRecebido <= upfFim && upfInicio <= 0)
               {
                  return atual;
               } else if ((valorRecebido > upfInicio) && (valorRecebido <= upfFim))
               {
                  return atual;
               } else if (valorRecebido > upfInicio && upfFim == 0)
               {
                  return atual;
               }
            }
         }
      }
      return new AliquotaVo();
   }

   /**
    * Método responsável por verificar se houve retificação no valor dos bens.
    * @param giaITCDVo
    * @return boolean
    * @throws ObjetoObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private boolean isBensRetificados(GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(giaITCDVo);
      //Iterando os Bens Tributáveis para verificar se houve retificação em algun bem.
      for (Iterator iteBemTributavel = giaITCDVo.getBemTributavelVo().getCollVO().iterator(); iteBemTributavel.hasNext(); )
      {
         BemTributavelVo bemAtual = (BemTributavelVo) iteBemTributavel.next();
         //Caso tenha alguma retificação o Status da GIA é alterado para RETIFICADO
         if (isBemPassivelAvaliacao(giaITCDVo, bemAtual))
         {
            if (Validador.isNumericoValido(bemAtual.getAvaliacaoBemTributavelVo().getValorAvaliacao()) && bemAtual.getValorMercado() != bemAtual.getAvaliacaoBemTributavelVo().getValorAvaliacao())
            {
               return true;
            }
            if (Validador.isDominioNumericoValido(bemAtual.getAvaliacaoBemTributavelVo().getIsento()) && !bemAtual.getIsencaoPrevista().is(bemAtual.getAvaliacaoBemTributavelVo().getIsento().getValorCorrente()))
            {
               return true;
            }
         }
      }
      return false;
   }

   /**
    * Método responsável por excluir o procurador para uma determinada GIA-ITCD.
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void excluirProcurador(GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                    ParametroObrigatorioException
   {
      Validador.validaObjeto(giaITCDVo);
      if (Validador.isObjetoValido(giaITCDVo.getProcuradorVo()))
      {
         if (!Validador.isNumericoValido(giaITCDVo.getProcuradorVo().getNumrContribuinte()))
         {
            giaITCDVo.setProcuradorVo(null);
            throw new ParametroObrigatorioException(MensagemErro.NAO_FOI_POSSIVEL_EXCLUIR_PROCURADOR);
         } else
         {
            giaITCDVo.setProcuradorVo(null);
         }
      }
   }

   /**
    * Método responsável por validar se o Status anterior da GIA-ITCD é igual a Retificado. 
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void solicitarVerificarStatusAnteriorIgualRetificado(GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
                                                                                           ParametroObrigatorioException, 
                                                                                           ConsultaException
   {
      Validador.validaObjeto(giaITCDVo);
      StatusGIAITCDVo statusGIAITCDVo = obterStatusAnteriorGIAITCD(giaITCDVo);
      if (statusGIAITCDVo.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
      {
         giaITCDVo.setGiaRetificada(true);
      }
   }

   public UFIntegracaoVO obterUfMT() throws ObjetoObrigatorioException, 
                                            IntegracaoException, 
                                            RegistroExistenteException
   {
      UFIntegracaoVO uFIntegracaoVO = new UFIntegracaoVO();
     // uFIntegracaoVO.setSiglUf("MT");
      //uFIntegracaoVO = (new CadastroBe(conn)).buscarUf(uFIntegracaoVO);
      uFIntegracaoVO.setCollVO(new CadastroBe(conn).listarUf());
    //  if (!Validador.isStringValida(uFIntegracaoVO.getDescUf()))
      //{
     //    throw new RegistroExistenteException(MensagemErro.NAO_EXISTE_UF_COM_SIGLA_MT);
    //  }
      return uFIntegracaoVO;
   }

   public void verificarGiaQuitacaoCCF(final GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, 
                                                                         IntegracaoException, 
                                                                         RegistroNaoPodeSerUtilizadoException, 
                                                                         RegistroExistenteException, 
                                                                         ParseException, 
                                                                         ParametroObrigatorioException
   {
      Validador.validaObjeto(giaItcdVo);
      if (giaItcdVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_CCF))
      {
         if (Validador.isCollectionValida(giaItcdVo.getBeneficiarioVo().getCollVO()))
         {
            FiltroConsultaDebitoIntegracaoVo filtroConsultaDebitoIntegracaoVo = new CcFiscalBe(conn).prepararFiltroConsultaDebito(giaItcdVo);
            DebitoIntegracaoVo debitoIntegracaoVo = new CcFiscalBe(conn).consultarDebito(filtroConsultaDebitoIntegracaoVo);
            if (!Validador.isCollectionValida(debitoIntegracaoVo.getCollVO()))
            {
               throw new RegistroExistenteException(MensagemErro.NAO_EXISTE_LANCAMENTO_DEBITO_DOS_BENEFICARIOS_NO_CCF);
            } else
            {
               StringBuilder nomeBeneficiarioComDebitoNaoQuitado = new StringBuilder();
               StringBuilder nomeBeneficiarioSemDebito = new StringBuilder();
               for (BeneficiarioVo beneficiarioAtualVo: (Collection<BeneficiarioVo>) giaItcdVo.getBeneficiarioVo().getCollVO())
               {
                  boolean possuiDebito = false;
                  for (DebitoIntegracaoVo debitoIntegracaoAtualVo: (Collection<DebitoIntegracaoVo>) debitoIntegracaoVo.getCollVO())
                  {
                     if (beneficiarioAtualVo.getPessoaBeneficiaria().getNumrContribuinte() == debitoIntegracaoAtualVo.getNumeroPessoa())
                     {
                        possuiDebito = true;
                        if (debitoIntegracaoAtualVo.getFlagSituacaoDebito().getValorCorrente() != DomnSituacaoDebito.QUITADO)
                        {
                           nomeBeneficiarioComDebitoNaoQuitado.append(new StringBuilder(beneficiarioAtualVo.getPessoaBeneficiaria().getNomeContribuinte() + ", "));
                           break;
                        }
                     }
                  }
                  if (!possuiDebito)
                  {
                     nomeBeneficiarioSemDebito.append(new StringBuilder(beneficiarioAtualVo.getPessoaBeneficiaria().getNomeContribuinte() + ", "));
                  }
               }
               if (Validador.isStringValida(nomeBeneficiarioComDebitoNaoQuitado.toString()) || Validador.isStringValida(nomeBeneficiarioSemDebito.toString()))
               {
                  StringBuilder msgErro = new StringBuilder();
                  ;
                  if (Validador.isStringValida(nomeBeneficiarioSemDebito.toString()))
                  {
                     if (nomeBeneficiarioSemDebito.toString().split(", ").length == 1)
                     {
                        msgErro.append(new StringBuilder("O(a) beneficiário(a) " + nomeBeneficiarioSemDebito + " não possui lançamento de débito no Sistema CCF. "));
                     } else
                     {
                        msgErro.append(new StringBuilder("Os(as) beneficários(as) " + nomeBeneficiarioSemDebito + " não possuem lançamentos de débito no Sistema CCF. "));
                     }
                  }
                  if (Validador.isStringValida(nomeBeneficiarioComDebitoNaoQuitado.toString()))
                  {
                     if (nomeBeneficiarioComDebitoNaoQuitado.toString().split(", ").length == 1)
                     {
                        msgErro.append(new StringBuilder("O(a) beneficiário(a) " + nomeBeneficiarioComDebitoNaoQuitado + " não se encontra com a situação Quitado no Sistema CCF."));
                     } else
                     {
                        msgErro.append(new StringBuilder("Os(as) beneficários(as) " + nomeBeneficiarioComDebitoNaoQuitado + " não se encontram com a situação Quitado no Sistema CCF."));
                     }
                  }
                  throw new RegistroNaoPodeSerUtilizadoException(msgErro.toString());
               }
            }
         }
      }
   }

   /**
    * <b>Objetivo:</b> este método tem por objetivo reunir todas as regras
    * que definem a gia como tendo PROTOCOLO_MANUAL ou PROTOCOLO_AUTOMATICO.
    * <br>
    * <b>Observação: </b> A ordem de precendencia das regras é definida
    * pela ordem das condições IF.<br> 1 - O 1º IF tem menor precendencia que 2º IF.
    * 
    * 
    * @param giaITCDVo que terá os bens testados e com base no testes pode ter seu TipoProtocolo alterado.
    * @implemented by Dherkyan Ribeiro
    */
   public static void alterarAutomaticamenteTipoProtocoloGia(final GIAITCDVo giaITCDVo)
   {
      final DomnTipoProtocolo PROTOCOLO_MANUAL = new DomnTipoProtocolo(DomnTipoProtocolo.PROTOCOLO_MANUAL);
      final DomnTipoProtocolo PROTOCOLO_AUTOMATICO = new DomnTipoProtocolo(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO);
      final DomnTipoProtocolo TIPO_PROTOCOLO_ATUAL = new DomnTipoProtocolo( giaITCDVo.getTipoProtocoloGIA().getValorCorrente());
      
      if( BemTributavelBe.isTodosBemComValorDeclaradoIgualArbitradoCollVo( giaITCDVo.getBemTributavelVo() ) )
      {       
         giaITCDVo.setTipoProtocoloGIA(PROTOCOLO_AUTOMATICO);
         giaITCDVo.getTemporarioVo().setTipoProtocoloGIA(giaITCDVo.getTipoProtocoloGIA());
         giaITCDVo.setTipoProtocoloGIAOpcional(false);
         ExibirLOG.exibirLog(" Todos os bens tributáveis da GIA tem valor informado IGUAL ao tributável" , giaITCDVo.getCodigo());
      }
      
      if (BemTributavelBe.isBemTributavelVoTipoProtocoloCollVO(giaITCDVo.getBemTributavelVo(), PROTOCOLO_MANUAL))
      {
         giaITCDVo.setTipoProtocoloGIA(PROTOCOLO_MANUAL);
         giaITCDVo.getTemporarioVo().setTipoProtocoloGIA(giaITCDVo.getTipoProtocoloGIA());
         giaITCDVo.setTipoProtocoloGIAOpcional(false);
         ExibirLOG.exibirLog("GIA ITCD contém bem tributável com PROTOCOLO MANUAL " , giaITCDVo.getCodigo());
      }
      else if (BemTributavelBe.isExisteBemTributavelVoIsentoCollVo(giaITCDVo.getBemTributavelVo()))
      {
         giaITCDVo.setTipoProtocoloGIA(PROTOCOLO_MANUAL);
         giaITCDVo.getTemporarioVo().setTipoProtocoloGIA(giaITCDVo.getTipoProtocoloGIA());
         giaITCDVo.setTipoProtocoloGIAOpcional(false);
         ExibirLOG.exibirLog("GIA ITCD contém bem tributável com ISENCAO PREVISTA EM LEI" , giaITCDVo.getCodigo());
      }
      else if (BemTributavelBe.isExisteBemComValorDeclaradoDiferenteDeArbitradoCollVo(giaITCDVo.getBemTributavelVo()))
      {
         ExibirLOG.exibirLog("GIA ITCD contém bem tributável com valor informado DIFERENTE do tributável " , giaITCDVo.getCodigo());
         ExibirLOG.exibirLog("Tipo de Protocolo da GIA ITCD: OPICIONAL " , giaITCDVo.getCodigo());
         if(Validador.isDominioNumericoValido( giaITCDVo.getTipoProtocoloGIASelecionadoPeloContribuinte() ))
         {
            giaITCDVo.setTipoProtocoloGIA( giaITCDVo.getTipoProtocoloGIASelecionadoPeloContribuinte() );
            giaITCDVo.getTemporarioVo().setTipoProtocoloGIA(giaITCDVo.getTipoProtocoloGIA());
            ExibirLOG.exibirLog("O contribuinte selecionou: "+giaITCDVo.getTipoProtocoloGIA().getTextoCorrente()  , giaITCDVo.getCodigo());
         }
            giaITCDVo.setTipoProtocoloGIAOpcional(true);
         
      }
      else
      {
         giaITCDVo.setTipoProtocoloGIA(PROTOCOLO_AUTOMATICO);
         giaITCDVo.getTemporarioVo().setTipoProtocoloGIA(giaITCDVo.getTipoProtocoloGIA());
         giaITCDVo.setTipoProtocoloGIAOpcional(false);
      }

      /*
       * Caso ocorra alteração do tipo de procotolo informar no LOG que ocorreu a alteração.
       */
      if( !giaITCDVo.getTipoProtocoloGIA().is( TIPO_PROTOCOLO_ATUAL.getValorCorrente() ) )
      {
         ExibirLOG.exibirLog("Tipo de protocolo da GIA alterado para: "+giaITCDVo.getTipoProtocoloGIA().getTextoCorrente() , giaITCDVo.getCodigo());
      }


      /**
       * Add status DomnSituacaoProcessamento.NAO_PROCESSADO
       * para gias to dipo DomnTipoProtocolo.PROTOCOLO_AUTOMATICO
       * 
       */
      if(giaITCDVo.getTipoProtocoloGIA().is(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO))
      {
         giaITCDVo.setSituacaoProcessamento(new DomnSituacaoProcessamento( DomnSituacaoProcessamento.NAO_PROCESSADO) );      
      }else
      {
         giaITCDVo.getTemporarioVo().setSituacaoProcessamento(null);
      }
      giaITCDVo.getTemporarioVo().setSituacaoProcessamento(giaITCDVo.getSituacaoProcessamento());
      giaITCDVo.getTemporarioVo().setDescricaoMensagemSituacaoErrro(giaITCDVo.getDescricaoMensagemSituacaoErrro());
   }
   
   /**
    * Método responsável por verificar se uma GIA deve ser protocolada no eprocess
    * ou validada, TRUE pode ser protocolada e FALSE se pode ser validada, caso
    * não ser nem este e nem aquele lança exceção
    * @param giaITCDVo
    * @return boolean
    * @throws ObjetoObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public boolean verificarGiaITCDParaProtocolo(final GIAITCDVo giaITCDVo)
      throws SQLException, ValidacaoException, ObjetoObrigatorioException, 
             ConsultaException, Exception
   {
      EprocessBe eprocessBe = null;
      Collection<ProcessoIntegracaoVo> colecaoProcessos = null;
      TipoProcessoVo tipoProcessoVo = null;
      StatusGIAITCDVo ultimoStatus = null;
      StatusGIAITCDBe statusGIAITCDBE = null;
      TipoProcessoBe tipoProcessoBe = null;
      boolean isProtocolar = false;
      try 
      {
         //Inicializa todas as variaveis
         eprocessBe = new EprocessBe(conn);
         tipoProcessoVo = new TipoProcessoVo();
         statusGIAITCDBE = new StatusGIAITCDBe(conn);
         tipoProcessoBe = new TipoProcessoBe(conn);
         ultimoStatus = statusGIAITCDBE.procurarUltimoStatusCollectionVo(giaITCDVo.getStatusVo());
         tipoProcessoVo.setDomnTipoEprocess(new DomnTipoProcessoEprocess(DomnTipoProcessoEprocess.PROCESSO_AUTOMATICO));
         tipoProcessoVo = tipoProcessoBe.buscarTipoProcesso(tipoProcessoVo);
         
         Validador.validaObjeto(ultimoStatus);
         
         boolean isVersaoValida = true;
         
         if((giaITCDVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_3)) || (giaITCDVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_4))){
            isVersaoValida = false;
         }
         
         if((!ultimoStatus.getStatusGIAITCD().is(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO)) || isVersaoValida || (!giaITCDVo.getTipoProtocoloGIA().is(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO))) {             
            if(ultimoStatus.getStatusGIAITCD().is(DomnStatusGIAITCD.EM_ELABORACAO)){            
              throw new ValidacaoException("GIA-ITCD não finalizada!");
            }
            else if(giaITCDVo.getTipoProtocoloGIA().is(DomnTipoProtocolo.PROTOCOLO_MANUAL)){               
                  throw new ValidacaoException("GIA-ITCD com protocolo manual e não pode ser protocolada ou validada automaticamente!");                
                              
            }else if (giaITCDVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_2) || giaITCDVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1)){
                  throw new ValidacaoException("Protocolo GIA-ITCD não autorizado. Em virtude de atualização do sistema atendendo a Portaria Portaria 177/2018 - Artigo 19, recomenda-se o preenchimento de nova GIA ITCD.");
            }
            
            else 
                  throw new ValidacaoException("Protocolo GIA-ITCD não autorizado.");            
            
         }
         else
         {
            colecaoProcessos = eprocessBe.listarProcessoPorCodigoGiaITCD(giaITCDVo.getCodigo());
            if(colecaoProcessos.isEmpty())
            {
               isProtocolar = true;
            }
            else if (!colecaoProcessos.isEmpty())
            {
               for (ProcessoIntegracaoVo processoAtual: colecaoProcessos)
               {
                  if (processoAtual.getTipoProcessoIntegracaoVo().getCodgTipoProcesso().equals(tipoProcessoVo.getTipoProcessoIntegracaoVo().getCodgTipoProcesso()))
                  {
                     if ((processoAtual.getNumeroProcesso() != 0 && processoAtual.getAnoProcesso() != 0) && 
                         (processoAtual.getStatusProcesso().getDomnValr() != DomnStatusProcesso.APENSADO) && 
                         (processoAtual.getStatusProcesso().getDomnValr() != DomnStatusProcesso.FINALIZADO))
                     {
                        throw new ValidacaoException("GIA-ITCD com Processo já iniciado e Validado, aguarde liberação para Emissão do DAR/Declarações!");
                     }
                  }
               }
            }
            else
            {
               for(ProcessoIntegracaoVo processoAtual : colecaoProcessos)
               {
                  if(processoAtual.getTipoProcessoIntegracaoVo().getCodgTipoProcesso() == tipoProcessoVo.getTipoProcessoIntegracaoVo().getCodgTipoProcesso())
                  {
                     if((processoAtual.getNumeroProcesso() == 0 && processoAtual.getAnoProcesso() == 0) &&
                        (processoAtual.getStatusProcesso().getDomnValr() != DomnStatusProcesso.APENSADO) && 
                        (processoAtual.getStatusProcesso().getDomnValr() != DomnStatusProcesso.FINALIZADO))
                           
                        isProtocolar = false;
                  }
               }
            }
         }
      }
      finally 
      {
         statusGIAITCDBE.close();
         tipoProcessoBe.close();
         eprocessBe.close();
         statusGIAITCDBE = null;
         tipoProcessoBe = null;
         eprocessBe = null;
      }
      return isProtocolar;
   }
 
 
 /**
  * <b>Objetivo:</b> Este método tem por objetivo atualizar os campos
  * dos valores recebidos pelos beneficiarios/conjuge após  a avalição
  * dos bens.
  */
 public void atualizarValorRecebidoAposAvaliacao(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException
   {
    switch (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente())
    {
       case DomnTipoProcesso.DOACAO:
          (new GIAITCDDoacaoBe(conn)).atualizarValorRecebidoAposAvaliacao((GIAITCDDoacaoVo) giaITCDVo);
          break;
       case DomnTipoProcesso.INVENTARIO_ARROLAMENTO:
          (new GIAITCDInventarioArrolamentoBe(conn)).atualizarValorRecebidoAposAvaliacao((GIAITCDInventarioArrolamentoVo) giaITCDVo);
          break;
       case DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA:
          (new GIAITCDSeparacaoDivorcioBe(conn)).atualizarValorRecebidoAposAvaliacao((GIAITCDSeparacaoDivorcioVo) giaITCDVo);
          break;
    }
 }
 
   /**
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ClassCastException
    * @implemented by Dherkyan Ribeiro
    */
   public GIAITCDVo listarCodigoGiaRelatorioValorBeneficiarioAposAValiacao(final GIAITCDVo giaITCDVo, final PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         ConsultaException
   {
      (new GIAITCDQDao(conn)).listarCodigoGiaRelatorioValorBeneficiarioAposAValiacao(giaITCDVo , pedidoRelatorioVo );
      return giaITCDVo;
   }
   
   public GIAITCDVo listarCodigoGiaRelatorioCreditoConstituido(final GIAITCDVo giaITCDVo, final PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         ConsultaException
   {
      (new GIAITCDQDao(conn)).listarCodigoGiaRelatorioCreditoConstituido(giaITCDVo , pedidoRelatorioVo );
      return giaITCDVo;
   }
   
   public GIAITCDVo listarCodigoGiaRelatorioEstoqueEmAberto(final GIAITCDVo giaITCDVo, final PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         ConsultaException
   {
      (new GIAITCDQDao(conn)).listarCodigoGiaRelatorioEstoqueEmAberto(giaITCDVo , pedidoRelatorioVo );
      return giaITCDVo;
   }
   
  /* public boolean isExisteGiaPorParametroLegislacao(final Long codigoParametroLegislacao) throws ObjetoObrigatorioException, 
			   ConsultaException
	{
 */
 

   private boolean existBemValorConcordadoNao(Collection<BemTributavelVo> collBemTributavelVo)
   {
      for (BemTributavelVo vo : collBemTributavelVo) 
      {
         if(vo.getConcordaComValorArbitrado() != null && vo.getConcordaComValorArbitrado().getValorCorrente() == DomnSimNao.NAO)
         {
            return true;
         }
      }
      return false;
      
   }

   /*
   public void incluirDebitoCCF(GIAITCDVo giaITCDInternoVo)
      throws ObjetoObrigatorioException, IntegracaoException, 
             RegistroNaoPodeSerUtilizadoException, SQLException
   {
      CcFiscalBe ccFiscalBe = new CcFiscalBe(conn);
      ccFiscalBe.incluirDebito(giaITCDInternoVo);     
   }
  */

   private boolean isReavaliacao(GIAITCDVo giaitcdVo)
      throws ConsultaException, ObjetoObrigatorioException
   {
      StatusGIAITCDVo statusParam = new StatusGIAITCDVo();
      statusParam.setGiaITCDVo(giaitcdVo);
      statusParam.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIACAO_EXCLUIDA));
      
      StatusGIAITCDVo status =  new StatusGIAITCDBe(conn).consultarStatusGIAITCD(new StatusGIAITCDVo(statusParam));
      if(status != null && status.getCodigo() > 0)
      {
         return true;
      }
      
      return false;      
   
   }
   
   public String existeGiaEmElaboracaoParaODeclarante(String numrContribuinte){
      List<String> giasAbertas = (new GIAITCDQDao(conn)).verificaExistenciaDeGiaEmElaboracaoOuPendenteDeProtocoloPorCodgResponsavel(numrContribuinte);
      String mensagem = null;

      if (giasAbertas != null && !giasAbertas.isEmpty()) {

          StringBuilder sb = new StringBuilder();

          sb.append("Encontrada GIA ITCD do Doador em elaboração ou pendente de protocolo. Por favor, finalize antes de iniciar uma nova GIA :\n");

          for (int i = 0; i < giasAbertas.size(); i++) {
              sb.append(" - ").append(giasAbertas.get(i)).append("\n");
          }

          mensagem = sb.toString();
      }
      
      return mensagem;
   }
}
