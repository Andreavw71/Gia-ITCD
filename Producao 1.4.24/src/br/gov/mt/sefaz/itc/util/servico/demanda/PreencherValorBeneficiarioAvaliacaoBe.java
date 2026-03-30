package br.gov.mt.sefaz.itc.util.servico.demanda;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.sefaz.integracao.arrecadacao.DarEmitidoIntegracaoVo;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioBe;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatDar;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.integracao.arrecadacao.DocumentoArrecadacaoBe;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sefaz.mt.util.SefazDisplay;

/**
 * Classe de negócio para a Baixa Automática do D.A.R.
 * @author Daniel Balieiro
 * @implemented by Dherkyan Ribeiro
 */
public class PreencherValorBeneficiarioAvaliacaoBe extends AbstractBe
{

   private SefazDisplay log = null;
   /**
    * Construtor Padrão
    * @throws SQLException
    * @implemented by Dherkyan Ribeiro
    */
   public PreencherValorBeneficiarioAvaliacaoBe() throws SQLException
   {
      super();
      this.log = new SefazDisplay("/usr/appl/servicos/itc/out/ValorBeneficiarioAvaliacao.log");
   }

   /**
    * Construtor que recebe a Conexão com o Banco de Dados
    * @param conn
    * @implemented by Dherkyan Ribeiro
    */
   public PreencherValorBeneficiarioAvaliacaoBe(Connection conn)
   {
      super(conn);
      this.log = new SefazDisplay("/usr/appl/servicos/itc/out/ValorBeneficiarioAvaliacao.log");
   }

   /**
    * Método que efetua a Baixa Automática do DAR
    * @param entidadeVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws SQLException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws RegistroExistenteException
    * @implemented by Dherkyan Ribeiro
    */
   public String valorBeneficiarioAvaliacao(EntidadeVo entidadeVo) throws ConsultaException, ObjetoObrigatorioException, 
         ConexaoException, ParametroObrigatorioException, IntegracaoException, SQLException, 
             IOException
   {
      StringBuffer sb = new StringBuffer();
      StatusGIAITCDVo codigoGiaComStatusAvaliado = getListaDeStatusAvaliado();

      GIAITCDTemporarioBe giaItcdTemporarioBe = new GIAITCDTemporarioBe(conn);
      GIAITCDTemporarioVo giaITCDTemporarioVoIntervaloTempo = new GIAITCDTemporarioVo();
      giaItcdTemporarioBe.listarDadosBasicosGIAITCDTemporarioPorIntervaloDeTempoDataCriacao(giaITCDTemporarioVoIntervaloTempo);

      ExibirLOG.exibirLog("Quantidade de GIA's 01/01/2015 a 31/12/2015 - " + 
            giaITCDTemporarioVoIntervaloTempo.getCollVO().size());
      ExibirLOG.exibirLog("Quantidade de GIA's que possuem status avaliado - " + 
            codigoGiaComStatusAvaliado.getCollVO().size());

      List<Long> listaCodigoGiaIntervaloDeTempo = getListaCodigoGiaComPorIntervaloDeTempo(giaITCDTemporarioVoIntervaloTempo);
      List<Long> listaCodigoGiaComStatusAvaliado = getListaCodigoGiaComStatusAvaliado(codigoGiaComStatusAvaliado);
      List<Long> listaCodigoGiaParaProcessar = 
         getListaCodigoGiaParaProcessar(listaCodigoGiaIntervaloDeTempo, listaCodigoGiaComStatusAvaliado);

      // Conjunto de Listas para guardar codigos de gias processadas CORRETAMENTE.
      List<Long> listaGiaDoacaoProcessada = new ArrayList<Long>();
      List<Long> listaGiaSeparacaoProcessada = new ArrayList<Long>();
      List<Long> listaGiaInventarioProcessada = new ArrayList<Long>();
      
      // Conjunto de Listas para guardar codigos de gias com ERRO.
      List<Long> listaGiaDoacaoErro = new ArrayList<Long>();
      List<Long> listaGiaSeparacaoErro = new ArrayList<Long>();
      List<Long> listaGiaInventarioErro = new ArrayList<Long>();

      GIAITCDBe giaItcdBe = new GIAITCDBe(conn);
      GIAITCDVo giaParametro;
      GIAITCDVo giaConsulta;
      for (Iterator it = listaCodigoGiaParaProcessar.iterator(); it.hasNext(); )
      {
         Long codigo = (Long) it.next();

         giaParametro = new GIAITCDVo(codigo);
         giaParametro.setConsultaCompleta(true);

         giaConsulta = new GIAITCDVo(giaParametro);
         giaConsulta.setConsultaCompleta(true);

         GIAITCDVo giaItcdVo = giaItcdBe.consultaGiaPermanente(giaConsulta);
         giaItcdVo.setLogSefazVo(entidadeVo.getLogSefazVo());
         try
         {
            new GIAITCDBe(conn).atualizarValorRecebidoAposAvaliacao(giaItcdVo);

            
            switch (giaItcdVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente())
            {
               case DomnTipoProcesso.DOACAO:
                  listaGiaDoacaoProcessada.add(giaItcdVo.getCodigo());
                  break;
               case DomnTipoProcesso.INVENTARIO_ARROLAMENTO:
                  listaGiaInventarioProcessada.add(giaItcdVo.getCodigo());
                  break;
               case DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA:
                  listaGiaSeparacaoProcessada.add(giaItcdVo.getCodigo());
                  break;
            }

         }
         catch (Exception e)
         {
            log.println(SefazDisplay.ERROR, "Código Gia: "+giaItcdVo.getCodigo()+" "+ retornaMensagemExcecao(e));
            switch (giaItcdVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente())
            {
               case DomnTipoProcesso.DOACAO:
                  listaGiaDoacaoErro.add(giaItcdVo.getCodigo());
                  break;
               case DomnTipoProcesso.INVENTARIO_ARROLAMENTO:
                  listaGiaInventarioErro.add(giaItcdVo.getCodigo());
                  break;
               case DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA:
                  listaGiaSeparacaoErro.add(giaItcdVo.getCodigo());
                  break;
            }            
         }
      }
      
      ExibirLOG.exibirLog("DADOS DE EXECUCAO DO SERVICO");
      
      ExibirLOG.exibirLog("GIAS PROCESSADAS CORRETAMENTE");
      ExibirLOG.exibirLogSimplificado("Total: " + (listaGiaDoacaoProcessada.size() + listaGiaInventarioProcessada.size() + listaGiaSeparacaoProcessada.size() ));
      ExibirLOG.exibirLog("GIA - DOACAO / OUTROS");
      for(long codigo : listaGiaDoacaoProcessada)
      {
         System.out.println(codigo);
      }
      ExibirLOG.exibirLogSimplificado("Total: "+listaGiaDoacaoProcessada.size());
      
      ExibirLOG.exibirLog("GIA - INVENTARIO / ARROLAMENTO");
      for(long codigo : listaGiaInventarioProcessada)
      {
         System.out.println(codigo);
      }
      ExibirLOG.exibirLogSimplificado("Total: "+listaGiaInventarioProcessada.size());
      
      ExibirLOG.exibirLog("GIA - SEPARACAO / DIVORCIO / PARTILHA");
      for(long codigo : listaGiaSeparacaoProcessada)
      {
         System.out.println(codigo);
      }
      ExibirLOG.exibirLogSimplificado("Total: "+listaGiaSeparacaoProcessada.size());
      
      ExibirLOG.exibirLog("GIAS COM FALHA DE PROCESSAMENTO");
      ExibirLOG.exibirLogSimplificado("TOTAL: " + (listaGiaDoacaoErro.size() + listaGiaInventarioErro.size() + listaGiaSeparacaoErro.size() ));
      ExibirLOG.exibirLog("GIA - DOACAO / OUTROS");
      for(long codigo : listaGiaDoacaoErro)
      {
         System.out.println(codigo);
      }
      ExibirLOG.exibirLogSimplificado("Total: "+listaGiaDoacaoErro.size());
      
      ExibirLOG.exibirLog("GIA - INVENTARIO / ARROLAMENTO");
      for(long codigo : listaGiaInventarioErro)
      {
         System.out.println(codigo);
      }
      ExibirLOG.exibirLogSimplificado("Total: "+listaGiaInventarioErro.size());
      
      ExibirLOG.exibirLog("GIA - SEPARACAO / DIVORCIO / PARTILHA");
      for(long codigo : listaGiaSeparacaoErro)
      {
         System.out.println(codigo);
      }
      ExibirLOG.exibirLogSimplificado("Total: "+listaGiaSeparacaoErro.size());
      
      return "";
   }

   /**
    * Método que retorna uma lista de GIAITCDVo que contenham os seguintes status: "AVALIADO" 
    * @return
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @implemented by Dherkyan Ribeiro
    */
   private StatusGIAITCDVo getListaDeStatusAvaliado() throws ConsultaException, ObjetoObrigatorioException, ConexaoException, 
         ParametroObrigatorioException, IntegracaoException
   {
      DomnStatusGIAITCD domAvaliado = new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO);
      StatusGIAITCDVo statusGIAITCDVoConsulta = new StatusGIAITCDVo();
      statusGIAITCDVoConsulta.setStatusGIAITCD(domAvaliado);
      statusGIAITCDVoConsulta = new StatusGIAITCDVo(statusGIAITCDVoConsulta);
      StatusGIAITCDVo statusGIAITCDVo = (new StatusGIAITCDBe(conn)).listarStatus(statusGIAITCDVoConsulta);
      return statusGIAITCDVo;
   }

   /**
    * 
    * @param statusGiaItcdVo
    * @return
    */
   private List<Long> getListaCodigoGiaComStatusAvaliado(StatusGIAITCDVo statusGiaItcdVo)
   {
      List<Long> codigos = new ArrayList<Long>();
      for (Iterator it = statusGiaItcdVo.getCollVO().iterator(); it.hasNext(); )
      {
         StatusGIAITCDVo status = (StatusGIAITCDVo) it.next();
         codigos.add(status.getGiaITCDVo().getCodigo());
      }
      return codigos;
   }

   /**
    * 
    * @param giaITCDTemporarioVo
    * @return
    */
   private List<Long> getListaCodigoGiaComPorIntervaloDeTempo(GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
         ConsultaException
   {
      List<Long> codigos = new ArrayList<Long>();
      for (Iterator it = giaITCDTemporarioVo.getCollVO().iterator(); it.hasNext(); )
      {
         GIAITCDTemporarioVo giaTemp = (GIAITCDTemporarioVo) it.next();
         codigos.add(giaTemp.getCodigo());
      }
      return codigos;
   }

   /**
    * 
    * @param codigosGiaPorIntervaloDeTempo
    * @param codigosGiaComStatusAvaliado
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    */
   private List<Long> getListaCodigoGiaParaProcessar(List<Long> codigosGiaPorIntervaloDeTempo, List<Long> codigosGiaComStatusAvaliado) throws ObjetoObrigatorioException, 
         ConsultaException
   {
      List<Long> codigos = new ArrayList<Long>();
      for (Iterator it = codigosGiaPorIntervaloDeTempo.iterator(); it.hasNext(); )
      {
         Long codigo = (Long) it.next();
         if (codigosGiaComStatusAvaliado.contains(codigo))
         {
            codigos.add(codigo);
         }
      }
      return codigos;
   }

   private String retornaMensagemExcecao(Exception e) 
   {
      StringWriter stringWriterErro = new StringWriter();
      e.printStackTrace(new PrintWriter(stringWriterErro));
      return stringWriterErro.toString();
   }


}
