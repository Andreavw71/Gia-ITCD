package br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.sefaz.integracao.acessoweb.ValidaPermissaoBe;
import br.com.abaco.sefaz.integracao.arrecadacao.DarEmitidoIntegracaoVo;
import br.com.abaco.sefaz.integracao.arrecadacao.DocumentoArrecadacaoBe;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.data.AbacoData;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.log.log.LogITCDBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.util.data.DataUtil;
import br.gov.mt.sefaz.itc.util.dominio.DomnGrupoPermissao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatDar;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAvaliacao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CepIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.GestaoPessoasBe;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.UnidadeSefazIntegracaoVo;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.io.IOException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sefaz.mt.util.SefazData;
import sefaz.mt.util.SefazDisplay;

/**
 * Classe que encapsula as regras de negócio do Status da GIA-ITCD
 * @author Daniel Balieiro
 * @version $Revision: 1.12 $
 */
public class StatusGIAITCDBe extends AbstractBe
{
   /**
    * Construtor que recebe a Conexão com o Banco de Dados
    * @param conexao
    * @implemented by Daniel Balieiro
    */
   public StatusGIAITCDBe(Connection conexao)
   {
      super(conexao);
   }

   /**
    * Construtor vazio
    * @throws SQLException
    * @implemented by Daniel Balieiro
    */
   public StatusGIAITCDBe() throws SQLException
   {
      super();
   }

   /**
    * Este método verifica se uma gia foi protocolada ou não
    *
    * @param giaItcdVo
    * @return
    * @implemented by Leandro Dorileo
    */
   public boolean giaProtocolada(GIAITCDVo giaItcdVo) throws ConsultaException, ObjetoObrigatorioException, 
         IntegracaoException
   {
      boolean retorno = false;
      StatusGIAITCDVo status = new StatusGIAITCDVo();
      status.setGiaITCDVo(giaItcdVo);
      status = new StatusGIAITCDVo(status);
      status = listarStatusGIAITCD(status);
      for (Iterator iteStatus = status.getCollVO().iterator(); iteStatus.hasNext(); )
      {
         StatusGIAITCDVo statusAtual = (StatusGIAITCDVo) iteStatus.next();
         if (statusAtual.getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLADO))
         {
            return true;
         }
      }
      return retorno;
   }

   /**
    * Este método verifica se uma gia foi avaliada ou não
    *
    * @param giaItcdVo
    * @return
    * @implemented by Leandro Dorileo
    */
   public boolean giaAvaliada(GIAITCDVo giaItcdVo) throws ConsultaException, ObjetoObrigatorioException
   {
      boolean retorno = false;
      StatusGIAITCDVo status = new StatusGIAITCDVo();
      status.setGiaITCDVo(giaItcdVo);
      status = new StatusGIAITCDVo(status);
      status = consultarStatusGIAITCD(status);
      if (status.getStatusGIAITCD() != null && status.getStatusGIAITCD().is(DomnStatusGIAITCD.AVALIADO))
      {
         return true;
      }
      return retorno;
   }

   /**
    * Este método verifica se o status uma determinada gia é o informado.
    *
    * @param giaItcdVo				dados da gia a ser consultada
    * @param statusRequerido		status a ser comparado
    * @return
    * @implemented by Leandro Dorileo
    */
   public boolean verificaStatusGIA(GIAITCDVo giaItcdVo, DomnStatusGIAITCD statusRequerido) throws ConsultaException, 
         ObjetoObrigatorioException
   {
      StatusGIAITCDVo statusConsultado = null;
      boolean retorno = false;
      StatusGIAITCDVo status = new StatusGIAITCDVo();
      status.setGiaITCDVo(giaItcdVo);
      status = new StatusGIAITCDVo(status);
      statusConsultado = consultarStatusGIAITCD(status);
      retorno = statusConsultado.getStatusGIAITCD().is(statusRequerido.getValorCorrente());
      return retorno;
   }

   /**
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarIncluirStatusGIAITCDEmElaboracaoPendenteProtocolo(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      if (!Validador.isNumericoValido(statusGIAITCDVo.getGiaTemporaria().getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.CODIGO_GIA_TEMPORARIO_OBRIGATORIO);
      }
   }

   /**
    * Método para validar a inclusão do Status da GIA-ITCD sobre o Protocolo Autorizado pela GIOR
    * @param statusGIAITCDVo
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    */
   private void validarIncluirStatusGIAITCDProtocoloAutorizadoPelaGIOR(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException, IntegracaoException, RegistroExistenteException, ConsultaException, 
         RegistroNaoPodeSerUtilizadoException, ConexaoException, LogException, PersistenciaException, AnotacaoException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      if (!Validador.isDataValida(statusGIAITCDVo.getDataPermissao()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_PERMISSAO);
      }
      if (!Validador.isStringValida(statusGIAITCDVo.getObservacao()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_OBSERVACAO);
      }
      if (!Validador.isNumericoValido(statusGIAITCDVo.getCodigoAgenfa()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_AGENFA_PROTOCOLO);
      }
      else
      {
         statusGIAITCDVo.setMensagem(MensagemSucesso.ALTERAR);
      }
      autorizarProtocoloGIAITCD(statusGIAITCDVo.getGiaITCDVo());

   }

   /**
    * Método para Protocolar uma GIA-ITCD, retirando-a da tabela temporária e a enviando para tabela permanente.
    * @param gia
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
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void autorizarProtocoloGIAITCD(final GIAITCDVo gia) throws ObjetoObrigatorioException, IntegracaoException, 
         RegistroExistenteException, ConsultaException, ParametroObrigatorioException, RegistroNaoPodeSerUtilizadoException, 
         ConexaoException, LogException, PersistenciaException, AnotacaoException
   {
      (new GIAITCDBe(conn)).incluirGIAITCD(gia);
   }

   /**
    * Método para validar a inclusão do Status da GIA-ITCD sobre o Parcelamento
    * @param statusGIAITCDVo
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    */
   private void validarIncluirStatusGIAITCDParcelado(final StatusGIAITCDVo statusGIAITCDVo, final StatusGIAITCDVo statusAnterior) throws ParametroObrigatorioException, 
         ObjetoObrigatorioException, ConsultaException
   {
      if (!Validador.isNumericoValido(statusGIAITCDVo.getProtocoloParcelamento()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_PROTOCOLO_PARCELAMENTO);
      }
      validaDataStatus(statusGIAITCDVo, statusAnterior);
      if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA))
      {
         StatusGIAITCDVo statusAnteriorInscricao = new StatusGIAITCDVo();
         statusAnteriorInscricao.getGiaITCDVo().setCodigo(statusGIAITCDVo.getGiaITCDVo().getCodigo());
         statusAnteriorInscricao.getStatusGIAITCD().setValorCorrente(statusAnterior.getStatusGIAITCD().getValorCorrente());
         statusAnteriorInscricao = obterStatusGIAITCDAnterior(statusAnteriorInscricao);
         if (statusAnteriorInscricao.getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO))
         {
            if (!Validador.isStringValida(statusGIAITCDVo.getJustificativaReparcelamento()))
            {
               throw new ParametroObrigatorioException(MensagemErro.JUSTIFICATIVA_REPARCELAMENTO_DEVE_SER_INFORMADO);
            }
         }
      }
   }

   /**
    * Método que contém as regras de validação para mudança do Status da GIA-ITCD para Protocolo Impugnação
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarIncluirStatusGIAITCDProtocoloImpugnacao(final StatusGIAITCDVo statusGIAITCDVo, final StatusGIAITCDVo statusAnterior) throws ObjetoObrigatorioException, 
         ConexaoException, ConsultaException, ParametroObrigatorioException, IntegracaoException
   {
      if (!Validador.isNumericoValido(statusGIAITCDVo.getProtocoloImpugnacao()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_PROTOCOLO_IMPUGNADO);
      }
      validaDataStatus(statusGIAITCDVo, statusAnterior, false);
      statusGIAITCDVo.getGiaITCDVo().setLogSefazVo(statusGIAITCDVo.getLogSefazVo());
      boolean isGIOR = 
         new GIAITCDBe(conn).verificaUsuarioPossuiPermissao(statusGIAITCDVo.getGiaITCDVo(), new DomnGrupoPermissao[]
            { new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_GIOR) });
      if (!isGIOR)
      {
         final String descricao = ConfiguracaoITCD.PARAMETRO_PRAZO_IMPUGNACAO;
         ConfiguracaoGerencialParametrosVo config = new ConfiguracaoGerencialParametrosVo();
         config.setDescricaoItem(descricao);
         config = new ConfiguracaoGerencialParametrosVo(config);
         (new ConfiguracaoGerencialParametrosBe(conn)).consultarConfiguracaoGerencialParametros(config);
         StatusGIAITCDVo consulta = new StatusGIAITCDVo();
         StatusGIAITCDVo parametro = new StatusGIAITCDVo();
         parametro.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO_CIENTE));
         parametro.getGiaITCDVo().setCodigo(statusGIAITCDVo.getGiaITCDVo().getCodigo());
         consulta = new StatusGIAITCDVo(parametro);
         consultarStatusGIAITCD(consulta);
         Date dataParametro = consulta.getDataCienciaRetificacao();
         if (!consulta.isExiste())
         {
            parametro.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO));
            consulta.setParametroConsulta(parametro);
            consulta = consultarStatusGIAITCD(consulta);
            if (!consulta.isExiste())
            {
               throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_PRAZO_IMPUGNACAO);
            }
            dataParametro = consulta.getDataEmissaoRetificacao();
         }
         Date dataLimiteImpugnacao = 
            DataUtil.adicionaValoresData(dataParametro, Calendar.DATE, StringUtil.toInt(config.getValorItem().trim()));
         dataLimiteImpugnacao = AbacoData.getProximoDiaUtil(dataLimiteImpugnacao);

         if (statusGIAITCDVo.getDataImpugnacao().after(dataLimiteImpugnacao))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_PRAZO_IMPUGNACAO);
         }
      }
      if (!Validador.isStringValida(statusGIAITCDVo.getMotivoImpugnacao()))
      {
         throw new ParametroObrigatorioException(MensagemErro.MOTIVO_IMPUGNACAO_DEVE_SER_INFORMADO);
      }
   }

   /**
    * Método que valida a inclusão do Status da GIA-ITCD sobre Ratificação
    * @param statusGIAITCDVo
    * @param statusAnterior
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @implemented by Daniel Balieiro
    */
   private void validarIncluirStatusGIAITCDRatificacao(final StatusGIAITCDVo statusGIAITCDVo, StatusGIAITCDVo statusAnterior) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException, IntegracaoException, ConsultaException, ConexaoException, LogException, 
         PersistenciaException, AnotacaoException, RegistroExistenteException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      Validador.validaObjeto(statusAnterior);
      validaDataStatus(statusGIAITCDVo, statusAnterior);
      if (!Validador.isNumericoValido(statusGIAITCDVo.getValorImposto()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALOR_IMPOSTO_DEVE_SER_INFORMADO_E_MAIOR_ZERO);
      }
      if (statusGIAITCDVo.getValorImposto() < statusGIAITCDVo.getGiaITCDVo().getValorITCDOriginal())
      {
         throw new ParametroObrigatorioException(MensagemErro.VALOR_IMPOSTO_RATIFICACAO_DEVE_SER_IGUAL_MAIOR_VALOR_ITCD);
      }
   }

   /**
    * Método para validar a inclusão do Status da GIA-ITCD sobre a Segunda Retificação
    * @param statusGIAITCDVo
    * @param statusAnterior
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @implemented by Daniel Balieiro
    */
   private void validarIncluirStatusGIAITCDSegundaRetificacao(final StatusGIAITCDVo statusGIAITCDVo, StatusGIAITCDVo statusAnterior) throws ParametroObrigatorioException, 
         ObjetoObrigatorioException, IntegracaoException, ConsultaException, ConexaoException, LogException, 
         PersistenciaException, AnotacaoException, RegistroExistenteException
   {
      validaDataStatus(statusGIAITCDVo, statusAnterior);
      if (!Validador.isNumericoValido(statusGIAITCDVo.getValorImposto()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALOR_IMPOSTO_DEVE_SER_INFORMADO_E_MAIOR_ZERO);
      }
      if (statusGIAITCDVo.getValorImposto() < statusGIAITCDVo.getGiaITCDVo().getValorITCDOriginal())
      {
         throw new ParametroObrigatorioException(MensagemErro.VALOR_IMPOSTO_SEGUNDA_RETIFICACAO_DEVE_SER_IGUAL_MAIOR_VALOR_ITCD);
      }
   }

   /**
    * Método para validar a inclusão do Status da GIA-ITCD sobre Remetido para Dívida Ativa
    * @param statusGIAITCDVo
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    */
   private void validarIncluirStatusGIAITCDRemetidoDividaAtiva(final StatusGIAITCDVo statusGIAITCDVo, final StatusGIAITCDVo statusAnterior) throws ParametroObrigatorioException, 
         ObjetoObrigatorioException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      Validador.validaObjeto(statusAnterior);
      validaDataStatus(statusGIAITCDVo, statusAnterior);
      if (!Validador.isStringValida(statusGIAITCDVo.getJustificativaEnvioDividaAtiva()))
      {
         throw new ParametroObrigatorioException(MensagemErro.JUSTIFICATIVA_ENVIO_DIVIDA_ATIVA_DEVE_SER_INFORMADA);
      }
   }

   /**
    * Método de consulta Configuração Gerencial de Parametros
    * @param descricao
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @return
    * @implemented by Daniel Balieiro
    */
   private String consultaConfiguracaoGerencialParametros(String descricao) throws ObjetoObrigatorioException, 
         ConexaoException, ConsultaException
   {
      ConfiguracaoGerencialParametrosVo config = new ConfiguracaoGerencialParametrosVo();
      config.setDescricaoItem(descricao);
      config = new ConfiguracaoGerencialParametrosVo(config);
      (new ConfiguracaoGerencialParametrosBe(conn)).consultarConfiguracaoGerencialParametros(config);
      return config.getValorItem();
   }

   private void obterDadosAgenfaProtocolo(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         IntegracaoException
   {
      UnidadeSefazIntegracaoVo consulta = new UnidadeSefazIntegracaoVo();
      UnidadeSefazIntegracaoVo parametro = new UnidadeSefazIntegracaoVo();
      parametro.setCodgUnidade(statusGIAITCDVo.getGiaITCDVo().getAgenciaProtocolo().getCodgUnidade());
      consulta.setParametroConsulta(parametro);
      consulta = new GestaoPessoasBe(conn).buscarUnidadeSefazPorCodigo(consulta);
      statusGIAITCDVo.getGiaITCDVo().setAgenciaProtocolo(consulta);
   }

   /**
    * Método que valida a inclusão do Status da GIA-ITCD sobre Protocolado
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    */
   private void validarIncluirStatusGIAITCDProtocolado(final StatusGIAITCDVo statusGIAITCDVo, final StatusGIAITCDVo statusAnterior) throws ParametroObrigatorioException, 
         ObjetoObrigatorioException, ConexaoException, ConsultaException, IntegracaoException, RegistroExistenteException, 
         RegistroNaoPodeSerUtilizadoException, LogException, PersistenciaException, AnotacaoException
   {
      if (!Validador.isNumericoValido(statusGIAITCDVo.getProtocolo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_PROTOCOLO);
      }
      if (!Validador.isDataValida(statusGIAITCDVo.getDataProtocolo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_PROTOCOLO);
      }
      if (!Validador.isNumericoValido(statusGIAITCDVo.getGiaITCDVo().getAgenciaProtocolo().getCodgUnidade()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_AGENFA_PROTOCOLO);
      }
      if (statusGIAITCDVo.getDataProtocolo().after(new Date()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_PROTOCOLO_FUTURA);
      }
      SefazData dataProcotolo = new SefazData(statusGIAITCDVo.getDataProtocolo());
      SefazData dataCriacao = new SefazData(statusGIAITCDVo.getGiaITCDVo().getDataCriacao());
      obterDadosAgenfaProtocolo(statusGIAITCDVo);
      if (dataProcotolo.anterior(dataCriacao))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_PROTOCOLO_ANTIGA);
      }
      boolean isCged = false;
      UnidadeSefazIntegracaoVo agenfaAvaliacao = statusGIAITCDVo.getGiaITCDVo().getAgenciaProtocolo();

      outer:
      {
         Set<CepIntegracaoVo> listaCep = new HashSet<CepIntegracaoVo>();
         if (Validador.isCollectionValida(statusGIAITCDVo.getGiaITCDVo().getBemTributavelVo().getCollVO()))
         {
            for (Iterator it = statusGIAITCDVo.getGiaITCDVo().getBemTributavelVo().getCollVO().iterator(); it.hasNext(); )
            {
               BemTributavelVo bem = (BemTributavelVo) it.next();
               if (bem.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_RURAL) || 
                  bem.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_URBANO))
               {
                  listaCep.add(bem.getFichaImovelVo().getEnderecoVo().getCep());
               }
            }
         }
         Map<Integer, MunicipioIntegracaoVo> listaMunicipioIntegracao = new HashMap<Integer, MunicipioIntegracaoVo>();
         for (CepIntegracaoVo atual: listaCep)
         {
            MunicipioIntegracaoVo municipio = new MunicipioIntegracaoVo();
            municipio = new CadastroBe(conn).obterMunicipioPorCep(atual);
            if (!listaMunicipioIntegracao.containsKey(municipio.getCodgMunicipio()))
            {
               listaMunicipioIntegracao.put(municipio.getCodgMunicipio(), municipio);
            }
         }
         if (listaMunicipioIntegracao.size() > 1)
         {
            isCged = true;
            break outer;
         }
         else if (listaMunicipioIntegracao.size() == 1)
         {
            MunicipioIntegracaoVo municipio = listaMunicipioIntegracao.values().iterator().next();
            agenfaAvaliacao = new GestaoPessoasBe(conn).buscarAgenfaPorCodgMunicipio(municipio.getCodgMunicipio().intValue());
            if (!Validador.isNumericoValido(agenfaAvaliacao.getCodgUnidade()) || 
               !agenfaAvaliacao.getTipoUnidade().getCodgTipoUnidade().equals(ConfiguracaoITCD.CODIGO_TIPO_UNIDADE_SEFAZ_AGENFA))
            {
               isCged = true;
               break outer;
            }
         }
         for (Iterator iteBem = 
               statusGIAITCDVo.getGiaITCDVo().getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
         {
            BemTributavelVo bem = (BemTributavelVo) iteBem.next();
            if (bem.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_RURAL))
            {
               final String descricao = ConfiguracaoITCD.PARAMETRO_AREA_MINIMA_HECTARES_IMOVEL_RURAL;
               double areaMinimaRural = StringUtil.monetarioToDouble(consultaConfiguracaoGerencialParametros(descricao));
               if (Validador.isNumericoValido(areaMinimaRural))
               {
                  if (((FichaImovelRuralVo) bem.getFichaImovelVo()).getAreaTotal() >= areaMinimaRural)
                  {
                     isCged = true;
                     break outer;
                  }
               }
               String descricaoUrbano = ConfiguracaoITCD.PARAMETRO_DISTANCIA_KM_PERIMETRO_URBANO;
               double distancia = StringUtil.monetarioToDouble(consultaConfiguracaoGerencialParametros(descricaoUrbano));
               if (Validador.isNumericoValido(distancia))
               {
                  if (((FichaImovelRuralVo) bem.getFichaImovelVo()).getQuantidadeDistancia() >= distancia)
                  {
                     isCged = true;
                     break outer;
                  }
               }
            }
            else if (bem.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_URBANO))
            {
               final String descricao = ConfiguracaoITCD.PARAMETRO_LIMITE_MINIMO_AREA_CONSTRUIDA_IMOVEL_URBANO;
               double limiteMinimo = StringUtil.monetarioToDouble(consultaConfiguracaoGerencialParametros(descricao));
               if (Validador.isNumericoValido(limiteMinimo))
               {
                  if (((FichaImovelUrbanoVo) bem.getFichaImovelVo()).getQuantidadeAreaConstruida() >= limiteMinimo)
                  {
                     isCged = true;
                     break outer;
                  }
               }
            }
         }
         String descricao = ConfiguracaoITCD.PARAMETRO_VALOR_TOTAL_BENS;
         double baseCalculo = 0;
         try
         {
            String valor = consultaConfiguracaoGerencialParametros(descricao);
            baseCalculo = StringUtil.monetarioToDouble(valor);
         }
         catch (NumberFormatException e)
         {
            throw new ParametroObrigatorioException("Não foi possível obter o parametro BASE DE CÁLCULO DE IMPOSTO. Por favor contate o analista responsável.");
         }
         if (Validador.isNumericoValido(baseCalculo))
         {
            if (statusGIAITCDVo.getGiaITCDVo().getValorTotalBensDeclarados() > baseCalculo)
            {
               isCged = true;
            }
         }
      }

      if (!isCged)
      {
         statusGIAITCDVo.setMensagem(MensagemSucesso.GIA_ITCD_AVALIACAO_AGENFA + 
               (Validador.isNumericoValido(agenfaAvaliacao.getCodgUnidade())? ": " + agenfaAvaliacao.getNomeUnidade(): "."));
         statusGIAITCDVo.setCodigoUnidadeAvaliacao(agenfaAvaliacao.getCodgUnidade().intValue());
         statusGIAITCDVo.setTipoAvaliacao(new DomnTipoAvaliacao(DomnTipoAvaliacao.AVALIACAO_AGENFA));
      }
      else
      {
         statusGIAITCDVo.setMensagem(MensagemSucesso.GIA_ITCD_AVALIACAO_CGED);
         statusGIAITCDVo.setTipoAvaliacao(new DomnTipoAvaliacao(DomnTipoAvaliacao.AVALIACAO_CGED));
      }
      if (!verificaExisteStatusGIAITCD(statusGIAITCDVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.PROTOCOLADO)))
      {
         if (!statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR))
         {
            protocolarGIAITCD(statusGIAITCDVo.getGiaITCDVo());
            statusGIAITCDVo.getGiaTemporaria().setCodigo(statusGIAITCDVo.getGiaITCDVo().getCodigo());
         }
      }
   }

   /**
    * @param statusGIAITCDVo
    * @param statusAnterior
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarIncluirStatusGIAITCDNotificadoCiente(final StatusGIAITCDVo statusGIAITCDVo, final StatusGIAITCDVo statusAnterior) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      Validador.validaObjeto(statusAnterior);
      validaDataStatus(statusGIAITCDVo, statusAnterior, !statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA));
   }

   /**
    * @param statusGIAITCDVo
    * @param statusAnterior
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarIncluirStatusGIAITCDQuitadoManualmente(final StatusGIAITCDVo statusGIAITCDVo, final StatusGIAITCDVo statusAnterior) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException, ConsultaException, IntegracaoException, ConexaoException, LogException, 
         PersistenciaException, AnotacaoException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      Validador.validaObjeto(statusAnterior);
      validaDataStatus(statusGIAITCDVo, statusAnterior);

      if (!Validador.isCollectionValida(statusGIAITCDVo.getGiaITCDDarVo().getCollVO()))
      {
         throw new ParametroObrigatorioException(MensagemErro.DAR_NAO_INFORMADO);
      }
      if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO))
      {
         if (!Validador.isNumericoValido(statusGIAITCDVo.getNumeroParcelas()))
         {
            throw new ParametroObrigatorioException(MensagemErro.QUANTIDADE_PARCELAS_DEVE_SER_MAIOR_ZERO);
         }
         if (statusGIAITCDVo.getNumeroParcelas() != statusGIAITCDVo.getGiaITCDDarVo().getCollVO().size())
         {
            throw new ParametroObrigatorioException(MensagemErro.QUANTIDADE_PARCELAS_DIFERENTE_QUANTIDADE_DAR_INFORMADO);
         }
      }
      if (!Validador.isStringValida(statusGIAITCDVo.getObservacao()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_OBSERVACAO);
      }
      validarValorTotalTributoPago(statusGIAITCDVo);
   }

   /**
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void vincularDarStatusQuitadoManual(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         ConexaoException, IntegracaoException, ConsultaException, ParametroObrigatorioException, LogException, 
         PersistenciaException, AnotacaoException, IOException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      statusGIAITCDVo.getGiaITCDDarVo().ordenaPorDataEmissaoDar();
      int i = 0;
      GIAITCDDarBe giaDarBe = new GIAITCDDarBe(conn);
      for (Iterator it = statusGIAITCDVo.getGiaITCDDarVo().getCollVO().iterator(); it.hasNext(); )
      {
         GIAITCDDarVo atual = (GIAITCDDarVo) it.next();
         atual.getGia().setCodigo(statusGIAITCDVo.getGiaITCDVo().getCodigo());
         atual.setNumeroParcela(++i);
         atual.setLogSefazVo(statusGIAITCDVo.getLogSefazVo());
         atual.setSubstituido(new DomnSimNao(DomnSimNao.NAO));
         atual.setStatusGIAITCDVo(statusGIAITCDVo);
         giaDarBe.incluirGIAITCDDar(atual);
      }
   }

   /**
    * <b>Objetivo:</b>
    * Este método tem por objetivo verificar os status da GIA
    * afim de saber se o valor cobrado será o calculado pelo sistema
    * ou o valor informado pelo servidor no status SEGUNDA_RETIFICACAO .
    * <br>
    * O valor do imposto será o valor informado na SEGUNDA_RETIFICACAO
    * quando o status atual for igual a QUITADO_MANUALMENTE e se na lista
    * de status da GIA contiver o status SEGUNDA_RETIFICACAO cado contrário será utilizado
    * o valor calculado pelo sistema.
    * 
    * <br>
    * <br>
    * 
    * <b>Rotina:</b>
    * Verifica se o status atual é igual a QUITADO_MANUALMENTE e se na lista
    * de status da GIA contém o status SEGUNDA_RETIFICACAO.
    * 
    * 
    * 
    * @param statusGIAITCDVo
    * @return
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private double obterValorImposto(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException, ConsultaException
   {
      BigDecimal bd = new BigDecimal(0);
      if (statusGIAITCDVo.getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_MANUALMENTE))
      {
         StatusGIAITCDVo sts = 
            procurarStatusNaCollectionDoVo(statusGIAITCDVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO));
         if (Validador.isObjetoValido(sts) && Validador.isNumericoValido(sts.getValorImposto()))
         {
            bd = new BigDecimal(sts.getValorImposto());
         }
         else
         {
            bd = new BigDecimal(statusGIAITCDVo.getGiaITCDVo().getValorITCD());
         }
      }
      else
      {
         bd = new BigDecimal(statusGIAITCDVo.getGiaITCDVo().getValorITCD());
      }

      return bd.setScale(2, RoundingMode.FLOOR).doubleValue();
   }

   /**
    * <b>Objetivo:</b> Este método tem por objetivo verificar e soma dos valores DARs quitado
    * corresponde ao valor do imposto da GIA
    * 
    * <b>Rotina</b> Compara o valor ou soma dos valores dos DARs quitados com o valor do imposto.
    * *OBS Utiliza método auxiliar ( obterValorImposto() )   para verificar se valor será do imposto é o valor
    * calculado pelo sistema ou o valor informado no STATUS SEGUNDA_RETIFICACAO.
    * 
    * 
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarValorTotalTributoPago(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         ConsultaException, IntegracaoException, ParametroObrigatorioException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      double valorDarQuitado = statusGIAITCDVo.getGiaITCDDarVo().getSomaValorTributo();
      double valorImposto = obterValorImposto(statusGIAITCDVo);
      GIAITCDDarVo consulta = new GIAITCDDarVo();
      GIAITCDDarVo parametro = new GIAITCDDarVo();
      parametro.getGia().setCodigo(statusGIAITCDVo.getGiaITCDVo().getCodigo());
      consulta.setParametroConsulta(parametro);
      consulta = new GIAITCDDarBe(conn).listarGIAITCDDar(consulta);
      if (Validador.isCollectionValida(consulta.getCollVO()))
      {
         DocumentoArrecadacaoBe documentoArrecadacaoBe = new DocumentoArrecadacaoBe(conn);
         for (Iterator it = consulta.getCollVO().iterator(); it.hasNext(); )
         {
            GIAITCDDarVo atual = (GIAITCDDarVo) it.next();
            DarEmitidoIntegracaoVo darEmitidoConsulta = atual.getDarEmitido();
            darEmitidoConsulta.setParametroConsulta(darEmitidoConsulta);
            atual.setDarEmitido(documentoArrecadacaoBe.obterDarEmitidoPorNumrDarOuCodgUofSeq(darEmitidoConsulta));
            if (atual.getDarEmitido().getStatDar().is(DomnStatDar.QUITADO))
            {
               valorDarQuitado += atual.getDarEmitido().getValorTributo();
               valorDarQuitado += atual.getDarEmitido().getValorCorrMonetaria();
               valorDarQuitado += atual.getDarEmitido().getValorJuros();
               valorDarQuitado += atual.getDarEmitido().getValorMulta();
               valorDarQuitado += atual.getDarEmitido().getValorTse();
            }
         }
      }
      if (valorDarQuitado < valorImposto)
      {
         throw new ParametroObrigatorioException(MensagemErro.SOMATORIO_VALOR_TRIBUTO_DAR_INFORMADO_INFERIOR_VALOR_IMPOSTO);
      }
   }

   /**
    * @param statusGIAITCDVo
    * @param statusAnterior
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarIncluirStatusGIAITCDInscricaoDividaAtiva(final StatusGIAITCDVo statusGIAITCDVo, final StatusGIAITCDVo statusAnterior) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      Validador.validaObjeto(statusAnterior);
      validaDataStatus(statusGIAITCDVo, statusAnterior);
      if (!Validador.isStringValida(statusGIAITCDVo.getJustificativaEnvioInscricaoDividaAtiva()))
      {
         throw new ParametroObrigatorioException(MensagemErro.JUSTIFICATIVA_ENVIO_INSCRICAO_DIVIDA_ATIVA_DEVE_SER_INFORMADA);
      }
   }

   /**
    * @param statusGIAITCDVo
    * @param statusAnterior
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarIncluirStatusGIAITICDRetificadoCiente(final StatusGIAITCDVo statusGIAITCDVo, final StatusGIAITCDVo statusAnterior) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      Validador.validaObjeto(statusAnterior);
      validaDataStatus(statusGIAITCDVo, statusAnterior, !statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA));
   }

   /**
    * @param statusGIAITCDVo
    * @param statusAnterior
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarIncluirStatusGIAITCDSegundaRetificacaoCiente(final StatusGIAITCDVo statusGIAITCDVo, final StatusGIAITCDVo statusAnterior) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException, ConsultaException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      Validador.validaObjeto(statusAnterior);
      validaDataStatus(statusGIAITCDVo, statusAnterior);
   }

   /**
    * @param statusGIAITCDVo
    * @param statusAnterior
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarIncluirStatusGIAITCDRatificadoCiente(final StatusGIAITCDVo statusGIAITCDVo, final StatusGIAITCDVo statusAnterior) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException, ConsultaException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      Validador.validaObjeto(statusAnterior);
      validaDataStatus(statusGIAITCDVo, statusAnterior);
   }

   /**
    * Verifica o Histórico de Status da GIA em busca do Status passado como parametro
    * @param statusGIAITCDVo
    * @param statusProcurado
    * @throws ObjetoObrigatorioException
    * @return
    * @implemented by Daniel Balieiro
    */
   public boolean verificaExisteStatusGIAITCD(final StatusGIAITCDVo statusGIAITCDVo, DomnStatusGIAITCD statusProcurado) throws ObjetoObrigatorioException, 
         ConsultaException, IntegracaoException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      if (Validador.isObjetoValido(statusGIAITCDVo.getGiaITCDVo()))
      {
         StatusGIAITCDVo statusConsulta = new StatusGIAITCDVo();
         if (Validador.isNumericoValido(statusGIAITCDVo.getGiaITCDVo().getCodigo()))
         {
            statusConsulta.getGiaITCDVo().setCodigo(statusGIAITCDVo.getGiaITCDVo().getCodigo());
         }
         else
         {
            statusConsulta.getGiaTemporaria().setCodigo(statusGIAITCDVo.getGiaTemporaria().getCodigo());
         }

         statusConsulta = new StatusGIAITCDVo(statusConsulta);
         statusConsulta = listarStatusGIAITCD(statusConsulta);
         for (Iterator iteStatus = statusConsulta.getCollVO().iterator(); iteStatus.hasNext(); )
         {
            if (((StatusGIAITCDVo) iteStatus.next()).getStatusGIAITCD().is(statusProcurado.getValorCorrente()))
            {
               return true;
            }
         }
      }
      return false;
   }

   /**
    * Método que validar a inclusão do Status da GIA-ITCD 
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @throws RegistroExistenteException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws ConexaoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarIncluirStatusGIAITCD(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException, ConsultaException, IntegracaoException, RegistroExistenteException, 
         RegistroNaoPodeSerUtilizadoException, ConexaoException, LogException, PersistenciaException, AnotacaoException
   {
      StatusGIAITCDVo statusAnterior = new StatusGIAITCDVo();
      statusAnterior.getGiaITCDVo().setCodigo(statusGIAITCDVo.getGiaITCDVo().getCodigo());
      statusAnterior.getGiaTemporaria().setCodigo(statusGIAITCDVo.getGiaTemporaria().getCodigo());
      //statusAnterior.getStatusGIAITCD().setValorCorrente(statusGIAITCDVo.getStatusGIAITCD().getValorCorrente());
      statusAnterior = obterStatusGIAITCDAnterior(statusAnterior);
      switch (statusGIAITCDVo.getStatusGIAITCD().getValorCorrente())
      {
         case DomnStatusGIAITCD.EM_ELABORACAO:
            {
               validarIncluirStatusGIAITCDEmElaboracaoPendenteProtocolo(statusGIAITCDVo);
               break;
            }
         case DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO:
            {
               validarIncluirStatusGIAITCDEmElaboracaoPendenteProtocolo(statusGIAITCDVo);
               break;
            }
         case DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR:
            {
               validarIncluirStatusGIAITCDProtocoloAutorizadoPelaGIOR(statusGIAITCDVo);
               break;
            }
         case DomnStatusGIAITCD.PROTOCOLADO:
            {
               validarIncluirStatusGIAITCDProtocolado(statusGIAITCDVo, statusAnterior);
               break;
            }
         case DomnStatusGIAITCD.NOTIFICADO_CIENTE:
            {
               validarIncluirStatusGIAITCDNotificadoCiente(statusGIAITCDVo, statusAnterior);
               break;
            }
         case DomnStatusGIAITCD.PARCELADO:
            {
               validarIncluirStatusGIAITCDParcelado(statusGIAITCDVo, statusAnterior);
               break;
            }
         case DomnStatusGIAITCD.QUITADO_MANUALMENTE:
            {
               validarIncluirStatusGIAITCDQuitadoManualmente(statusGIAITCDVo, statusAnterior);
               break;
            }
         case DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA:
            {
               validarIncluirStatusGIAITCDInscricaoDividaAtiva(statusGIAITCDVo, statusAnterior);
               break;
            }
         case DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA:
            {
               validarIncluirStatusGIAITCDRemetidoDividaAtiva(statusGIAITCDVo, statusAnterior);
               break;
            }
         case DomnStatusGIAITCD.RETIFICADO_CIENTE:
            {
               validarIncluirStatusGIAITICDRetificadoCiente(statusGIAITCDVo, statusAnterior);
               break;
            }
         case DomnStatusGIAITCD.IMPUGNADO:
            {
               validarIncluirStatusGIAITCDProtocoloImpugnacao(statusGIAITCDVo, statusAnterior);
               break;
            }
         case DomnStatusGIAITCD.SEGUNDA_RETIFICACAO:
            {
               validarIncluirStatusGIAITCDSegundaRetificacao(statusGIAITCDVo, statusAnterior);
               break;
            }
         case DomnStatusGIAITCD.RATIFICADO:
            {
               validarIncluirStatusGIAITCDRatificacao(statusGIAITCDVo, statusAnterior);
               break;
            }
         case DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE:
            {
               validarIncluirStatusGIAITCDSegundaRetificacaoCiente(statusGIAITCDVo, statusAnterior);
               break;
            }
         case DomnStatusGIAITCD.RATIFICADO_CIENTE:
            {
               validarIncluirStatusGIAITCDRatificadoCiente(statusGIAITCDVo, statusAnterior);
               break;
            }
         case DomnStatusGIAITCD.ENVIADO_CCF:
            {
               validarIncluirStatusGIAITCDEnviadoCCF(statusGIAITCDVo);
               break;
            }
      }
   }

   /**
    * Método para Protocolar uma GIA-ITCD, retirando-a da tabela temporária e a enviando para tabela permanente.
    * @param gia
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
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void protocolarGIAITCD(final GIAITCDVo gia) throws ObjetoObrigatorioException, IntegracaoException, 
         RegistroExistenteException, ConsultaException, ParametroObrigatorioException, RegistroNaoPodeSerUtilizadoException, 
         ConexaoException, LogException, PersistenciaException, AnotacaoException
   {
      (new GIAITCDBe(conn)).incluirGIAITCD(gia);
      //(new GIAITCDTemporarioBe(conn)).removerGIAITCDTemporario(gia.getTemporarioVo());
   }

   /**
    * Método para validar se a consulta de GIA-ITCD retornou uma GIA válida para Alteração de Status Manual.
    * @param status
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @implemented by Daniel Balieiro
    */
   public void validaConsultaAlteracaoStatus(final StatusGIAITCDVo status) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException, ConsultaException
   {
      StatusGIAITCDVo statusAnterior = obterStatusGIAITCDAnterior(status);
      switch (statusAnterior.getStatusGIAITCD().getValorCorrente())
      {
         case DomnStatusGIAITCD.AVALIADO:
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_AVALIADO);
         case DomnStatusGIAITCD.ISENTO:
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_ISENTO);
         case DomnStatusGIAITCD.ISENTO_IMPOSTO_ATE_1_UPF:
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_ISENTO_IMPOSTO_ATE_1_UPF);
         case DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR:
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_NAO_OCORRENCIA_FATO_GERADOR);
         case DomnStatusGIAITCD.PARCELADO:
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_PARCELADO);
         case DomnStatusGIAITCD.QUITADO:
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_QUITADO);
         case DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA:
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_REMETIDO_DIVIDA_ATIVA);
         case DomnStatusGIAITCD.INATIVO:
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_INATIVO);
         case DomnStatusGIAITCD.SEGUNDA_RETIFICACAO:
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_SEGUNDA_RETIFICACAO);
         case DomnStatusGIAITCD.PROTOCOLADO:
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_PROTOCOLO);
      }
   }

   /**
    * <b>Objetivo:</b>
    * 
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void incluir(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, LogException, 
         AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      new GenericoLogAnotacaoDao(conn, false).insert(statusGIAITCDVo);
      ExibirLOG.exibirLog("STATUS Incluido: "+statusGIAITCDVo.getCodigo() +": "+statusGIAITCDVo.getStatusGIAITCD().getTextoCorrente());
   }

   /**
    * <b>Objetivo:</b>
    * 
    * 
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void alterar(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, LogException, 
         AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      new GenericoLogAnotacaoDao(conn, false).update(statusGIAITCDVo);
      ExibirLOG.exibirLog("FIM - chamada método alterar - STATUS Alterado para: "+statusGIAITCDVo.getStatusGIAITCD().getTextoCorrente() +"  ID Status: "+statusGIAITCDVo.getCodigo() +"ID GIA TEMP: "+statusGIAITCDVo.getGiaTemporaria().getCodigo() , statusGIAITCDVo.getGiaITCDVo().getCodigo() );//TODO dherkyan pilha de ERRO.
   }

   /**
    * Método que inclui um Status à GIA-ITCD
    * @param statusGIAITCDVo
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
   public synchronized void incluirStatusGIAITCD(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         RegistroNaoPodeSerUtilizadoException, RegistroExistenteException, ConsultaException, ParametroObrigatorioException, 
         IntegracaoException, ConexaoException, LogException, PersistenciaException, AnotacaoException, IOException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      try
      {
         try
         {
            ExibirLOG.exibirLog(" INICIO - chamada método incluirStatusGIAITCD" ,statusGIAITCDVo.getGiaITCDVo().getCodigo() );//TODO dherkyan pilha de ERRO.
            imprimirListaDeStatusNoLog(conn , statusGIAITCDVo.getGiaITCDVo().getCodigo());
            validarIncluirStatusGIAITCD(statusGIAITCDVo);
            synchronized (StatusGIAITCDVo.class)
            {
               statusGIAITCDVo.setDataAtualizacao(new Date());

               // Verifica se o status que es tá sendo incluido é DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR ou DomnStatusGIAITCD.ALTERADO_PELA_GIOR
               // caso seja cria um registro de LOG senão não cria o Registro de LOG.
               if (statusGIAITCDVo.getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR) | 
                  statusGIAITCDVo.getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELA_GIOR))
               {
                  statusGIAITCDVo.setLogITCDVo(new LogITCDBe(conn).criarResgistroLogITCD(statusGIAITCDVo.getLogSefazVo()));
               }

               incluir(statusGIAITCDVo);
               if (statusGIAITCDVo.getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_MANUALMENTE))
               {
                   vincularDarStatusQuitadoManual(statusGIAITCDVo);
               }
            }
            ExibirLOG.exibirLog(" FIM - chamada método incluirStatusGIAITCD" ,statusGIAITCDVo.getGiaITCDVo().getCodigo() );//TODO dherkyan pilha de ERRO.
            imprimirListaDeStatusNoLog(conn , statusGIAITCDVo.getGiaITCDVo().getCodigo());
         }
         catch (RegistroExistenteException e)
         {
            conn.rollback();
            throw e;
         }
         catch (ConsultaException e)
         {
            conn.rollback();
            throw e;
         }
         catch (ParametroObrigatorioException e)
         {
            conn.rollback();
            throw e;
         }
         catch (IntegracaoException e)
         {
            conn.rollback();
            throw e;
         }
         catch (ObjetoObrigatorioException e)
         {
            conn.rollback();
            throw e;
         }
         catch (ConexaoException e)
         {
            conn.rollback();
            throw e;
         }
         catch (LogException e)
         {
            conn.rollback();
            throw e;
         }
         catch (PersistenciaException e)
         {
            conn.rollback();
            throw e;
         }
         catch (AnotacaoException e)
         {
            conn.rollback();
            throw e;
         }
         catch (RegistroNaoPodeSerUtilizadoException e)
         {
            conn.rollback();
            throw e;
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }

   /**
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws ConexaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void incluirStatusAnteriorReativacao(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         LogException, PersistenciaException, AnotacaoException, ConexaoException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      try
      {
         try
         {
            synchronized (StatusGIAITCDVo.class)
            {
               statusGIAITCDVo.setDataAtualizacao(new Date());
               incluir(statusGIAITCDVo);
            }
         }
         catch (ObjetoObrigatorioException e)
         {
            conn.rollback();
            throw e;
         }
         catch (LogException e)
         {
            conn.rollback();
            throw e;
         }
         catch (PersistenciaException e)
         {
            conn.rollback();
            throw e;
         }
         catch (AnotacaoException e)
         {
            conn.rollback();
            throw e;
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }

   /**
    * Método de consulta do Status da GIA-ITCD
    * @param statusGIAITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @return
    * @implemented by Daniel Balieiro
    */
   public StatusGIAITCDVo consultarStatusGIAITCD(StatusGIAITCDVo statusGIAITCDVo) throws ConsultaException, 
         ObjetoObrigatorioException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      (new StatusGIAITCDQDao(conn)).findStatusGIAITCD(statusGIAITCDVo);
      return statusGIAITCDVo;
   }
   
   /**
    * Método de consulta do Status da GIA-ITCD
    * @param statusGIAITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public StatusGIAITCDVo listarStatus(StatusGIAITCDVo statusGIAITCDVo) throws ConsultaException, 
         ObjetoObrigatorioException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      (new StatusGIAITCDQDao(conn)).listStatusGIAITCD(statusGIAITCDVo);
      return statusGIAITCDVo;
   }

   /**
    * Método para listar os Status da GIA de acordo com um array de dominio de status e o código da Agenfa para Protocolo
    * @param arrayStatus
    * @param codigoAgenfa
    * @return
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @implemented by Daniel Balieiro
    */
   public StatusGIAITCDVo listarStatusGIAITCDPorGrupoAgenfa(DomnStatusGIAITCD[] arrayStatus, int codigoAgenfa) throws ConsultaException, 
         ObjetoObrigatorioException
   {
      return (new StatusGIAITCDQDao(conn)).listStatusGIAITCDByStatusGroupAgenfa(arrayStatus, codigoAgenfa);
   }

   /**
    * Método para listar os Status da GIA-ITCD
    * 
    * @param statusGIAITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @return StatusGIAITCDVo
    * @implemented by Daniel Balieiro
    */
   public StatusGIAITCDVo listarStatusGIAITCD(StatusGIAITCDVo statusGIAITCDVo) throws ConsultaException, 
         ObjetoObrigatorioException, IntegracaoException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      (new StatusGIAITCDQDao(conn)).listStatusGIAITCD(statusGIAITCDVo);
      if (Validador.isCollectionValida(statusGIAITCDVo.getCollVO()) && statusGIAITCDVo.isConsultaCompleta())
      {
         for (Iterator it = statusGIAITCDVo.getCollVO().iterator(); it.hasNext(); )
         {
            StatusGIAITCDVo atual = (StatusGIAITCDVo) it.next();
            atual.setConsultaCompleta(statusGIAITCDVo.isConsultaCompleta());
            if (Validador.isNumericoValido(atual.getServidor().getNumrMatricula()))
            {
               obterDadosServidor(atual);
            }
            if (Validador.isNumericoValido(atual.getServidorSefazResponsavelAlteracao().getNumrMatricula()))
            {
               obterDadosServidorAlteracao(atual);
            }
            if (atual.getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_MANUALMENTE))
            {
               obterDadosDarQuitadoManualmente(atual);
            }
            if (atual.getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO))
            {
               obterDadosDarQuitado(atual);
            }

         }
      }
      return statusGIAITCDVo;
   }

   /**
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void obterDadosDarQuitado(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         ConsultaException, IntegracaoException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      GIAITCDDarVo consulta = new GIAITCDDarVo();
      GIAITCDDarVo parametro = new GIAITCDDarVo();
      parametro.getGia().setCodigo(statusGIAITCDVo.getGiaITCDVo().getCodigo());
      consulta.setParametroConsulta(parametro);
      consulta.setConsultaCompleta(statusGIAITCDVo.isConsultaCompleta());
      consulta = new GIAITCDDarBe(conn).listarGIAITCDDar(consulta);
      if (Validador.isCollectionValida(consulta.getCollVO()))
      {
         consulta.ordenaPorCodigoITCDDarDecrescente();
         for (Iterator it = consulta.getCollVO().iterator(); it.hasNext(); )
         {
            GIAITCDDarVo darAtual = (GIAITCDDarVo) it.next();
            if (darAtual.getDarEmitido().getStatDar().is(DomnStatDar.QUITADO))
            {
               statusGIAITCDVo.getGiaITCDDarVo().getCollVO().clear();
               statusGIAITCDVo.getGiaITCDDarVo().getCollVO().add(darAtual);
            }
         }
      }
   }

   /**
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void obterDadosDarQuitadoManualmente(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         ConsultaException, IntegracaoException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      GIAITCDDarVo parametro = new GIAITCDDarVo();
      GIAITCDDarVo consulta = new GIAITCDDarVo();
      parametro.getStatusGIAITCDVo().setCodigo(statusGIAITCDVo.getCodigo());
      consulta.setConsultaCompleta(statusGIAITCDVo.isConsultaCompleta());
      consulta.setParametroConsulta(parametro);
      statusGIAITCDVo.setGiaITCDDarVo(new GIAITCDDarBe(conn).listarGIAITCDDar(consulta));
   }

   private void obterDadosServidor(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         IntegracaoException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      statusGIAITCDVo.setServidor(new GestaoPessoasBe(conn).buscarServidorSefazPorNumrMatricula(statusGIAITCDVo.getServidor().getNumrMatricula().longValue()));
   }

   private void obterDadosServidorAlteracao(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         IntegracaoException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      statusGIAITCDVo.setServidorSefazResponsavelAlteracao(new GestaoPessoasBe(conn).buscarServidorSefazPorNumrMatricula(statusGIAITCDVo.getServidorSefazResponsavelAlteracao().getNumrMatricula().longValue()));
   }

   /**
    * Método que retorna o Status anterior da GIA-ITCD
    * 
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @return StatusGIAITCDVo
    * @implemented by Daniel Balieiro
    */
   public StatusGIAITCDVo obterStatusGIAITCDAnterior(StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException, ConsultaException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      if (!Validador.isNumericoValido(statusGIAITCDVo.getGiaITCDVo().getCodigo()) && 
         !Validador.isNumericoValido(statusGIAITCDVo.getGiaTemporaria().getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_STATUS_GIA_ITCD_CODIGO_GIA);
      }
      StatusGIAITCDVo parametro = new StatusGIAITCDVo();
      StatusGIAITCDVo retorno = null;
      parametro.getGiaITCDVo().setCodigo((Validador.isNumericoValido(statusGIAITCDVo.getGiaITCDVo().getCodigo())? statusGIAITCDVo.getGiaITCDVo().getCodigo(): statusGIAITCDVo.getGiaTemporaria().getCodigo()));
      parametro.setStatusGIAITCD(statusGIAITCDVo.getStatusGIAITCD());
      statusGIAITCDVo.setParametroConsulta(parametro);
      retorno = (new StatusGIAITCDQDao(conn)).obterStatusGIAITCDAnterior(statusGIAITCDVo);
      if (!Validador.isNumericoValido(retorno.getCodigo()))
      {
         parametro = new StatusGIAITCDVo();
         parametro.getGiaTemporaria().setCodigo((Validador.isNumericoValido(statusGIAITCDVo.getGiaITCDVo().getCodigo())? statusGIAITCDVo.getGiaITCDVo().getCodigo(): statusGIAITCDVo.getGiaTemporaria().getCodigo()));
         parametro.setStatusGIAITCD(statusGIAITCDVo.getStatusGIAITCD());
         statusGIAITCDVo.setParametroConsulta(parametro);
         retorno = (new StatusGIAITCDQDao(conn)).obterStatusGIAITCDAnterior(statusGIAITCDVo);
      }
      return retorno;
   }

   /**
    * Este método determina quais os possíveis status para uma gia a partir de um determinado
    * status.
    * 
    * @param giaITCDVo
    * @param usuarioLogado
    * @return
    * @throws ConsultaException
    * @throws IntegracaoException
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    */
   public Collection obterStatusPossivel(GIAITCDVo giaITCDVo, long usuarioLogado) throws ConsultaException, 
         IntegracaoException, ObjetoObrigatorioException, ParametroObrigatorioException
   {
      Collection retorno = new ArrayList();
      ValidaPermissaoBe acessowebIntegracaoBe = new ValidaPermissaoBe(conn);
      StatusGIAITCDVo status = giaITCDVo.getStatusVo();
      boolean permissaoAgenfa = 
         acessowebIntegracaoBe.existePermissaoUsuario(usuarioLogado, new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_AGENFA));
      boolean permissaoGIOR = 
         acessowebIntegracaoBe.existePermissaoUsuario(usuarioLogado, new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_GIOR));
      boolean permissaoCCF = 
         acessowebIntegracaoBe.existePermissaoUsuario(usuarioLogado, new DomnGrupoPermissao(DomnGrupoPermissao.CODG_GRUPO_PERM_CCF));
      boolean isAgenfaProtocolo = 
         (giaITCDVo.getServidorSefazIntegracaoVo().getUnidadeSefaz().getCodgUnidade().intValue() == giaITCDVo.getAgenciaProtocolo().getCodgUnidade().intValue());
      acessowebIntegracaoBe = null;
      StatusGIAITCDVo statusAnterior = new StatusGIAITCDVo();
      statusAnterior.getGiaITCDVo().setCodigo(status.getGiaITCDVo().getCodigo());
      statusAnterior.getGiaTemporaria().setCodigo(status.getGiaTemporaria().getCodigo());
      statusAnterior.setStatusGIAITCD(new DomnStatusGIAITCD(status.getStatusGIAITCD().getValorCorrente()));
      statusAnterior = obterStatusGIAITCDAnterior(statusAnterior);
      // Obtem os STATUS que a GIA atual ja teve. 
      StatusGIAITCDVo statusDaGIA = listarStatusGIAITCD(status);

      if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO))
      {
         retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.PROTOCOLADO));
      }
      else
      {
         if (permissaoAgenfa || permissaoGIOR)
         {
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO) && permissaoAgenfa)
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.PROTOCOLADO));
            }
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR) && permissaoAgenfa)
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.PROTOCOLADO));
            }
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.NOTIFICADO_CIENTE));
            }
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO_CIENTE))
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.PARCELADO));
            }
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO))
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.QUITADO_MANUALMENTE));
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA));
            }
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA))
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.PARCELADO));
               status.setExibeCampoJustificativaRetornoParcelamento(statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO));
               if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))
               {
                  retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.NOTIFICADO_CIENTE));
               }
               if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
               {
                  retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO_CIENTE));
               }
               if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO_CIENTE) || 
                  statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE))
               {
                  retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.QUITADO_MANUALMENTE));
               }
               if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO_CIENTE))
               {
                  if (permissaoGIOR || isAgenfaProtocolo)
                  {
                     retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.IMPUGNADO));
                  }
               }
               if (permissaoGIOR)
               {
                  retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA));
               }
            }
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO_CIENTE));
            }
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO_CIENTE))
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.IMPUGNADO));
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.PARCELADO));
            }
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.IMPUGNADO))
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.PARCELADO));
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.QUITADO_MANUALMENTE));
               if (permissaoGIOR)
               {
                  retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.RATIFICADO));
                  retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO));
                  retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.ISENTO));
               }
            }
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO))
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.RATIFICADO_CIENTE));
            }
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO_CIENTE))
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA));
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.PARCELADO));
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.QUITADO_MANUALMENTE));
            }
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO))
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE));
            }
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE))
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA));
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.PARCELADO));
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.QUITADO_MANUALMENTE));
            }
         }
         if (permissaoCCF)
         {
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO_CIENTE) || 
               status.getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO) || 
               status.getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA) || 
               status.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO_CIENTE) || 
               status.getStatusGIAITCD().is(DomnStatusGIAITCD.IMPUGNADO) || 
               status.getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE) || 
               status.getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO_CIENTE))
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.ENVIADO_CCF));
            }
            if (status.getStatusGIAITCD().is(DomnStatusGIAITCD.ENVIADO_CCF))
            {
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.QUITADO_CCF));
               //retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.QUITADO_MANUALMENTE)); OS:000019839
               retorno.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.QUITADO_PELA_GIOR));
            }
         }

         // Adiciona alguns status possiveis para quando o usuario pertencer ao grupo GIOR.
         adicionarStatusPossiveisGrupoGIOR(retorno, permissaoGIOR, statusAnterior, status, statusDaGIA);

      }

      return retorno;
   }

   /**
    * <b>Objetivo:</b>
    * 
    * Metodo responsavel por adicionar na lista de STATUS o próximo STATUS (DomnStatusGIAITCD.QUITADO_PELA_GIOR)
    * para o qual a GIA pode ser alterada pelo o grupo (DomnGrupoPermissao.CODG_GRUPO_PERM_GIOR).
    * 
    * @param permissaoGIOR se <b>true</b> o status <b>DomnStatusGIAITCD.QUITADO_PELA_GIOR<b/> é adicionado a lista.
    */
   private void adicionarStatusPossiveisGrupoGIOR(Collection listaDeStatus, boolean permissaoGIOR, StatusGIAITCDVo statusAnterior, StatusGIAITCDVo statusAtual, StatusGIAITCDVo listaStatusGIA)
   {
      if (permissaoGIOR)
      {
         if (!statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_PELA_GIOR) & 
            !statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO))
         {
            // Se os Status atual da GIA for destes abaixo então adiciona o novo status QUITADO_PELA_GIOR.
            if (statusAtual.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO) || 
               statusAtual.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO_CIENTE) || 
               statusAtual.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO) || 
               statusAtual.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO_CIENTE) || 
               statusAtual.getStatusGIAITCD().is(DomnStatusGIAITCD.IMPUGNADO) || 
               statusAtual.getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO) || 
               statusAtual.getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE) || 
               statusAtual.getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO) || 
               statusAtual.getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO_CIENTE) || 
               statusAtual.getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA) || 
               statusAtual.getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO))
            {
               listaDeStatus.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.QUITADO_PELA_GIOR));
            }
            else if (statusAtual.getStatusGIAITCD().is(DomnStatusGIAITCD.ALTERADO_PELA_GIOR))
            {
               List<StatusGIAITCDVo> arrayStatus = (List<StatusGIAITCDVo>) listaStatusGIA.getCollVO();
               boolean flagAdd = false;
               for (StatusGIAITCDVo temp: arrayStatus)
               {
                  if (temp.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO) || 
                     temp.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO_CIENTE) || 
                     temp.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO) || 
                     temp.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO_CIENTE))
                  {
                     flagAdd = true;
                  }
               }
               if (flagAdd)
               {
                  listaDeStatus.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.QUITADO_PELA_GIOR));
               }
            }
         }

         // Adicionar status para o grupo GIOR.
         adicionarStatusGrupoGIOR(listaDeStatus, statusAnterior, listaStatusGIA, DomnStatusGIAITCD.SEGUNDA_RETIFICACAO);
         adicionarStatusGrupoGIOR(listaDeStatus, statusAnterior, listaStatusGIA, DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE);
         adicionarStatusGrupoGIOR(listaDeStatus, statusAnterior, listaStatusGIA, DomnStatusGIAITCD.RATIFICADO);
         adicionarStatusGrupoGIOR(listaDeStatus, statusAnterior, listaStatusGIA, DomnStatusGIAITCD.RATIFICADO_CIENTE);

         adicionarStatusGrupoGIOR(listaDeStatus, statusAnterior, listaStatusGIA, DomnStatusGIAITCD.RETIFICADO_CIENTE);
         adicionarStatusGrupoGIOR(listaDeStatus, statusAnterior, listaStatusGIA, DomnStatusGIAITCD.NOTIFICADO_CIENTE);

      }

   }

   /**
    * <b>Objetivo:</b> este método tem por objetivo adicionar possiveis status
    * para o grupo GIOR caso este status já tenha ocorrido anteriormente.
    * 
    * 
    * @param listaDeStatus Lista que será adicionada o novo status.
    * @param statusAnterior
    * @param listaStatusGIA
    * @param status Status a ser verificado se já ocorreu,caso já tenha ocorrido será adicionado.
    */
   private void adicionarStatusGrupoGIOR(Collection listaDeStatus, StatusGIAITCDVo statusAnterior, StatusGIAITCDVo listaStatusGIA, int status)
   {
   //    if (!statusAnterior.getStatusGIAITCD().is(status)){
         List<StatusGIAITCDVo> arrayStatus = (List<StatusGIAITCDVo>) listaStatusGIA.getCollVO();
         boolean addStatus = false;
         for (StatusGIAITCDVo statusVO: arrayStatus)
         {
            if (statusVO.getStatusGIAITCD().is(status))
            {
               addStatus = true;
            }
         }
         if (addStatus)
         {
            listaDeStatus.add(new DomnStatusGIAITCD(status));
            //Adiciona o status RETIFICADO caso o status adcionado seja SEGUNDA_RETIFICACAO_CIENTE.
            if (status == DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE)
            {
               //listaDeStatus.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO));
            }
            //Adiciona o status RATIFICADO caso o status adcionado seja RATIFICADO_CIENTE.
            if (status == DomnStatusGIAITCD.RATIFICADO_CIENTE)
            {
               listaDeStatus.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.RATIFICADO));
            }
         }
   //   }
   }

   /**
    * <b>Objetivo:</b>
    * 
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws DadoNecessarioInexistenteException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void alterarStatus(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         ConexaoException, DadoNecessarioInexistenteException, LogException, AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      try
      {
         synchronized (StatusGIAITCDVo.class)
         {
            try
            {
               validarDadosObrigatoriosAlterarStatusGIAITCD(statusGIAITCDVo);
               statusGIAITCDVo.setDataAtualizacao(new Date());
               alterar(statusGIAITCDVo);
            }
            catch (DadoNecessarioInexistenteException e)
            {
               conn.rollback();
               throw e;
            }
            catch (ObjetoObrigatorioException e)
            {
               conn.rollback();
               throw e;
            }
            catch (LogException e)
            {
               conn.rollback();
               throw e;
            }
            catch (AnotacaoException e)
            {
               conn.rollback();
               throw e;
            }
            catch (PersistenciaException e)
            {
               conn.rollback();
               throw e;
            }
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }

   /**
    * @param statusGIAITCDVo
    * @throws ObjetoObrigatorioException
    * @throws DadoNecessarioInexistenteException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarDadosObrigatoriosAlterarStatusGIAITCD(StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         DadoNecessarioInexistenteException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      if (!Validador.isNumericoValido(statusGIAITCDVo.getCodigo()))
      {
         throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_STATUS_NECESSARIO_ALTERAR_STATUS);
      }
   }

   /**
    * Método responsável por verificar se em um determinado existe em uma coleção de status.
    * @param statusGIAITCDVo
    * @param status
    * @return boolean
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static boolean isGiaPossuiStatus(StatusGIAITCDVo statusGIAITCDVo, int status)
   {
      if (Validador.isCollectionValida(statusGIAITCDVo.getCollVO()))
      {
         for (Iterator it = statusGIAITCDVo.getCollVO().iterator(); it.hasNext(); )
         {
            StatusGIAITCDVo atual = (StatusGIAITCDVo) it.next();
            if (atual.getStatusGIAITCD().is(status))
            {
               return true;
            }
         }
      }
      return false;
   }

   /**
    * Método responsável por verificar se um determinado status é o segundo mais recente.
    * @param statusGIAITCDVo
    * @param status
    * @return boolean
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static boolean isStatusAnterior(StatusGIAITCDVo statusGIAITCDVo, int status)
   {
      if (Validador.isCollectionValida(statusGIAITCDVo.getCollVO()) && statusGIAITCDVo.getCollVO().size() > 1)
      {
         List lista = ((List) statusGIAITCDVo.getCollVO()).subList(0, 2);
         return isGiaPossuiStatus(new StatusGIAITCDVo(lista), status);
      }
      return false;
   }

   private Date obterDataStatus(StatusGIAITCDVo status)
   {
      switch (status.getStatusGIAITCD().getValorCorrente())
      {
         case DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR:
            {
               return status.getDataAtualizacao();
            }
         case DomnStatusGIAITCD.ALTERADO_PELA_GIOR:
            {
               return status.getDataAtualizacao();
            }
         case DomnStatusGIAITCD.AVALIADO:
            {
               return status.getDataCadastroAvaliacao();
            }
         case DomnStatusGIAITCD.EM_ELABORACAO:
            {
               return status.getDataAtualizacao();
            }
         case DomnStatusGIAITCD.IMPUGNADO:
            {
               return status.getDataImpugnacao();
            }
         case DomnStatusGIAITCD.INATIVADO_AUTOMATICAMENTE:
            {
               return status.getDataAtualizacao();
            }
         case DomnStatusGIAITCD.INATIVO:
            {
               return status.getDataDesistencia();
            }
         case DomnStatusGIAITCD.ISENTO:
            {
               return status.getDataAtualizacao();
            }
         case DomnStatusGIAITCD.ISENTO_IMPOSTO_ATE_1_UPF:
            {
               return status.getDataAtualizacao();
            }
         case DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR:
            {
               return status.getDataAtualizacao();
            }
         case DomnStatusGIAITCD.NOTIFICADO:
            {
               return status.getDataNotificacao();
            }
         case DomnStatusGIAITCD.NOTIFICADO_CIENTE:
            {
               return status.getDataCienciaNotificacao();
            }
         case DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA:
            {
               return status.getDataEnvioInscricaoDividaAtiva();
            }
         case DomnStatusGIAITCD.PARCELADO:
            {
               return status.getDataParcelamento();
            }
         case DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO:
            {
               return status.getDataAtualizacao();
            }
         case DomnStatusGIAITCD.PROTOCOLADO:
            {
               return status.getDataProtocolo();
            }
         case DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR:
            {
               return status.getDataPermissao();
            }
         case DomnStatusGIAITCD.QUITADO:
            {
               return status.getDataQuitacao();
            }
         case DomnStatusGIAITCD.QUITADO_MANUALMENTE:
            {
               return status.getDataQuitacao();
            }
         case DomnStatusGIAITCD.RATIFICADO:
            {
               return status.getDataRatificacao();
            }
         case DomnStatusGIAITCD.RATIFICADO_CIENTE:
            {
               return status.getDataCienciaRatificacao();
            }
         case DomnStatusGIAITCD.REATIVADO:
            {
               return status.getDataAtualizacao();
            }
         case DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA:
            {
               return status.getDataEnvioDividaAtiva();
            }
         case DomnStatusGIAITCD.RETIFICADO:
            {
               return status.getDataEmissaoRetificacao();
            }
         case DomnStatusGIAITCD.RETIFICADO_CIENTE:
            {
               return status.getDataCienciaRetificacao();
            }
         case DomnStatusGIAITCD.SEGUNDA_RETIFICACAO:
            {
               return status.getDataEmissaoSegundaRetificacao();
            }
         case DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE:
            {
               return status.getDataCienciaSegundaRetificacao();
            }
         case DomnStatusGIAITCD.ENVIADO_CCF:
            {
               return status.getDataEnvioCCF();
            }
         case DomnStatusGIAITCD.QUITADO_CCF:
            {
               return status.getDataAtualizacao();
            }
         default:
            {
               return null;
            }
      }
   }

   private String obterDescricaoStatus(StatusGIAITCDVo status)
   {
      switch (status.getStatusGIAITCD().getValorCorrente())
      {
         case DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR:
            {
               return "de alteração pelo servidor";
            }
         case DomnStatusGIAITCD.ALTERADO_PELA_GIOR:
            {
               return "de alteração pelo servidor GIOR";
            }
         case DomnStatusGIAITCD.AVALIADO:
            {
               return "da avaliação";
            }
         case DomnStatusGIAITCD.EM_ELABORACAO:
            {
               return "da elaboração";
            }
         case DomnStatusGIAITCD.IMPUGNADO:
            {
               return "da impugnação";
            }
         case DomnStatusGIAITCD.INATIVADO_AUTOMATICAMENTE:
            {
               return "da inativação";
            }
         case DomnStatusGIAITCD.INATIVO:
            {
               return "da inativação";
            }
         case DomnStatusGIAITCD.ISENTO:
            {
               return "da isenção";
            }
         case DomnStatusGIAITCD.ISENTO_IMPOSTO_ATE_1_UPF:
            {
               return "da isenção - Imposto até 1 UPF/MT";
            }
         case DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR:
            {
               return "de não ocorrência de fato gerador";
            }
         case DomnStatusGIAITCD.NOTIFICADO:
            {
               return "da notificação";
            }
         case DomnStatusGIAITCD.NOTIFICADO_CIENTE:
            {
               return "de ciência da notificação";
            }
         case DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA:
            {
               return "de inscrição para dívida ativa";
            }
         case DomnStatusGIAITCD.PARCELADO:
            {
               return "do parcelamento";
            }
         case DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO:
            {
               return "de conclusão do cadastro da gia";
            }
         case DomnStatusGIAITCD.PROTOCOLADO:
            {
               return "de protocolo";
            }
         case DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR:
            {
               return "de autorização do protocolo";
            }
         case DomnStatusGIAITCD.QUITADO:
            {
               return "de quitação";
            }
         case DomnStatusGIAITCD.QUITADO_MANUALMENTE:
            {
               return "de quitação";
            }
         case DomnStatusGIAITCD.RATIFICADO:
            {
               return "da ratificação";
            }
         case DomnStatusGIAITCD.RATIFICADO_CIENTE:
            {
               return "de ciência da ratificação";
            }
         case DomnStatusGIAITCD.REATIVADO:
            {
               return "da reativação";
            }
         case DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA:
            {
               return "de envio para dívida ativa";
            }
         case DomnStatusGIAITCD.RETIFICADO:
            {
               return "de retificação";
            }
         case DomnStatusGIAITCD.RETIFICADO_CIENTE:
            {
               return "de ciência da retificação";
            }
         case DomnStatusGIAITCD.SEGUNDA_RETIFICACAO:
            {
               return "da segunda retificação";
            }
         case DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE:
            {
               return "de ciência da segunda retificação";
            }
         case DomnStatusGIAITCD.ENVIADO_CCF:
            {
               return "do envio ao CCF";
            }
         case DomnStatusGIAITCD.QUITADO_CCF:
            {
               return "da quitação no CCF";
            }
         default:
            {
               return "<status não encontrado>";
            }
      }
   }

   private void validaDataStatus(StatusGIAITCDVo statusAtual, StatusGIAITCDVo statusAnterior) throws ParametroObrigatorioException
   {
      validaDataStatus(statusAtual, statusAnterior, true);
   }

   private void validaDataStatus(StatusGIAITCDVo statusAtual, StatusGIAITCDVo statusAnterior, boolean validaDataAnterior) throws ParametroObrigatorioException
   {
      Date dataStatusAtual = obterDataStatus(statusAtual);
      Date dataStatusAnterior = obterDataStatus(statusAnterior);
      String descricaoStatusAtual = obterDescricaoStatus(statusAtual);
      String descricaoStatusAnterior = obterDescricaoStatus(statusAnterior);

      if (!Validador.isDataValida(dataStatusAtual))
      {
         throw new ParametroObrigatorioException("Data " + descricaoStatusAtual + " deve ser informada.");
      }
      if (dataStatusAtual.after(new Date()))
      {
         throw new ParametroObrigatorioException("Data " + descricaoStatusAtual + " não deve ser maior que a data atual.");
      }
      if (validaDataAnterior && Validador.isDataValida(dataStatusAnterior))
      {
         if (AbacoData.converteDataComUltimoMinutoDia(dataStatusAtual).before(AbacoData.converteDataComUltimoMinutoDia(dataStatusAnterior)))
         {
            throw new ParametroObrigatorioException("A data " + descricaoStatusAtual + " deve ser igual ou superior a data " + 
                  descricaoStatusAnterior);
         }
      }
   }

   private void validaDataStatus(StatusGIAITCDVo statusAtual) throws ParametroObrigatorioException
   {
      Date dataStatusAtual = obterDataStatus(statusAtual);
      String descricaoStatusAtual = obterDescricaoStatus(statusAtual);
      if (!Validador.isDataValida(dataStatusAtual))
      {
         throw new ParametroObrigatorioException("Data " + descricaoStatusAtual + " deve ser informada.");
      }
      if (dataStatusAtual.after(new Date()))
      {
         throw new ParametroObrigatorioException("Data " + descricaoStatusAtual + " não deve ser maior que a data atual.");
      }
   }

   private void validarIncluirStatusGIAITCDEnviadoCCF(final StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      Validador.validaObjeto(statusGIAITCDVo);
      validaDataStatus(statusGIAITCDVo);
      if (!Validador.isNumericoValido(statusGIAITCDVo.getProtocoloCCF()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_PROTOCOLO);
      }
   }

   /**
    * <b>Objetivo</b>
    * 
    * Este método tem por objetivo procurar na collection do
    * statusGIAITCDVo passado como parâmetro o domínio informado statusProcurado.
    * Se houver status repitido este método retornará o último encontrado
    * na collection.
    * 
    * <b>Validações</b>
    * <ol>
    * <li>statusGIAITCDVo != null </li>
    * <li>statusGIAITCDVo.getCollVO() contém pelo menos 1 elemento</li>
    * </ol>
    * 
    * 
    * @param statusGIAITCDVo
    * @param statusProcurado
    * @throws ObjetoObrigatorioException
    * @return StatusGIAITCDVo Retorna o último status do domínio encontrado.
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public StatusGIAITCDVo procurarStatusNaCollectionDoVo(final StatusGIAITCDVo statusGIAITCDVo, DomnStatusGIAITCD statusProcurado)
   {
      StatusGIAITCDVo status = null;
      if (Validador.isObjetoValido(statusGIAITCDVo))
      {
         if (Validador.isCollectionValida(statusGIAITCDVo.getCollVO()))
         {
            for (StatusGIAITCDVo sts: statusGIAITCDVo.getCollVO())
            {
               if (sts.getStatusGIAITCD().is(statusProcurado.getValorCorrente()))
               {
                  status = sts;
               }
            }
         }
      }
      return status;
   }

   /**
    * <b>Objetivo</b>
    * Este método tem por objetivo verificar se na collection dos status contém os staus QUITADO e AVALIACAO_EXCLUIDA
    * sendo o código do status QUITADO menor que o código do status AVALIACAO_EXCLUIDA.
    * 
    * <b>Validações</b>
    * <ol>
    * <li>statusGIAITCDVo != null </li>
    * <li>statusGIAITCDVo.getCollVO() contém pelo menos 1 elemento</li>
    * </ol>
    * 
    * 
    * @param statusGIAITCDVo
    * @return true e comente true se atendidas as regras acima.
    */
   public boolean isPossuiStatusQuitadoeAvaliacaoExcluida(StatusGIAITCDVo statusGIAITCDVo)
   {
      StatusGIAITCDVo quitado = 
         procurarStatusNaCollectionDoVo(statusGIAITCDVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.QUITADO));
      StatusGIAITCDVo avaliacaoExcluida = 
         procurarStatusNaCollectionDoVo(statusGIAITCDVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIACAO_EXCLUIDA));

      if (Validador.isObjetoValido(quitado) & Validador.isObjetoValido(avaliacaoExcluida))
      {
         if (quitado.getCodigo() < avaliacaoExcluida.getCodigo())
         {
            return true;
         }
      }
      return false;
   }

   /**
    * <b>Objetivo</b>
    * 
    * Este método tem por objetivo fazer uma collection do para
    * saber quantas vezes ocorreu um determinado Status.
    * 
    * <br>
    * <br>
    * <b>Rotina:</b>
    * Percorre a collection do Vo procurando o status informado como parâmetro
    * cada vez que o status for encontrado é aumentado uma unidade no contador.
    * 
    * <br>
    * <br>
    * 
    * <b>Validações</b>
    * <ol>
    * <li>statusGIAITCDVo != null </li>
    * <li>statusGIAITCDVo.getCollVO() contém pelo menos 1 elemento</li>
    * </ol>
    * 
    * 
    * @param statusGIAITCDVo
    * @param statusProcurado
    * @throws ObjetoObrigatorioException
    * @return int Com a quantidade de vezes que foi encontrado o status.
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public static int contarStatusNaCollectionDoVo(final StatusGIAITCDVo statusGIAITCDVo, DomnStatusGIAITCD statusProcurado)
   {
      int contador = 0;
      if (Validador.isObjetoValido(statusGIAITCDVo))
      {
         if (Validador.isCollectionValida(statusGIAITCDVo.getCollVO()))
         {
            for (StatusGIAITCDVo sts: statusGIAITCDVo.getCollVO())
            {
               if (sts.getStatusGIAITCD().is(statusProcurado.getValorCorrente()))
               {
                  contador++;
               }
            }
         }
      }
      return contador;
   }

   /**
    * <b>Objetivo</b>
    * 
    * Este método tem por objetivo procurar na collection do
    * statusGIAITCDVo passado como parâmetro o domínio informado statusProcurado.
    * Se houver status repitido este método retornará o status com maior código encontrado
    * na collection.
    * 
    * <b>Validações</b>
    * <ol>
    * <li>statusGIAITCDVo != null </li>
    * <li>statusGIAITCDVo.getCollVO() contém pelo menos 1 elemento</li>
    * </ol>
    * 
    * 
    * @param statusGIAITCDVo
    * @param statusProcurado
    * @throws ObjetoObrigatorioException
    * @return StatusGIAITCDVo Retorna o último status do domínio encontrado.
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public StatusGIAITCDVo procurarUltimoStatusNaCollectionDoVo(final StatusGIAITCDVo statusGIAITCDVo, DomnStatusGIAITCD statusProcurado)
   {
      StatusGIAITCDVo status = null;
      if (Validador.isObjetoValido(statusGIAITCDVo))
      {
         if (Validador.isCollectionValida(statusGIAITCDVo.getCollVO()))
         {
            for (StatusGIAITCDVo sts: statusGIAITCDVo.getCollVO())
            {
               if (sts.getStatusGIAITCD().is(statusProcurado.getValorCorrente()))
               {
                  if (status == null)
                  {
                     status = sts;
                  }
                  else if (status.getCodigo() < sts.getCodigo())
                  {
                     status = sts;
                  }
               }
            }
         }
      }
      return status;
   }
   
   public StatusGIAITCDVo procurarUltimoStatusCollectionVo(final StatusGIAITCDVo statusGIAITCDVo)
   {
      StatusGIAITCDVo status = null;
      status = new StatusGIAITCDVo(Long.MIN_VALUE);
      if (Validador.isObjetoValido(statusGIAITCDVo))
      {
         if (Validador.isCollectionValida(statusGIAITCDVo.getCollVO()))
         {
            for (StatusGIAITCDVo sts: statusGIAITCDVo.getCollVO())
            {
               if(sts != null && sts.getCodigo() > status.getCodigo()) 
               {
                  status = sts;
               }
            }
         }
      }
      return status;
   }
   
   public static void imprimirListaDeStatusNoLog(Connection con , long codigoGia)
   {
      try
      {
         if (Validador.isNumericoValido(codigoGia))
         {
            StatusGIAITCDVo sts = new StatusGIAITCDVo();
            sts.setGiaTemporaria(new GIAITCDTemporarioVo(codigoGia));
            sts = new StatusGIAITCDVo(sts);

            new StatusGIAITCDBe(con).listarStatusGIAITCD(sts);
            for (StatusGIAITCDVo s: sts.getCollVO())
            {
               ExibirLOG.exibirLogSimplificado("Codigo GIA: " + codigoGia + " - Codigo Status: " + s.getCodigo() + " - " + 
                     s.getStatusGIAITCD().getTextoCorrente());
            }
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
   
   public StatusGIAITCDVo listarStatusGIAITCDPorGrupo(DomnStatusGIAITCD[] arrayStatus) throws ConsultaException, 
            ObjetoObrigatorioException
   {
      return (new StatusGIAITCDQDao(conn)).listStatusGIAITCDByStatusGroup(arrayStatus);
   }   

}
