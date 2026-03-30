package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.sefaz.integracao.arrecadacao.DocumentoArrecadacaoVo;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;

import br.gov.mt.sefaz.cas.integracao.ObjetoVo;
import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.conjuge.ConjugeBemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.documentoarrecadacao.DocumentoArrecadacaoGiaNormalBe;
import br.gov.mt.sefaz.itc.model.generico.documentoarrecadacao.DocumentoArrecadacaoGiaVencidoBe;
import br.gov.mt.sefaz.itc.model.generico.documentoarrecadacao.DocumentoArrecadacaoITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.GIAITCDInventarioArrolamentoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota.GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.PedidoRelatorioBe;
import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.PedidoRelatorioVo;
import br.gov.mt.sefaz.itc.util.DarException;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSituacaoProcessamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoRelatorio;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.castor.ServicoCastorBe;
import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ProcessarRelatorioValorBeneficiarioNotificadoRetificadoBe
   extends AbstractBe
{

   private GIAITCDBe giaItcdBe = null;
   private HSSFWorkbook arquivoExcel = null;
   private int contadorLinhaPlanilha = 0;

   private static final String CAMINHO_DIRETORIO_TEMPORARIO = 
      "/usr/appl/servicos/itc/ArquivosTemporarios/";
   private static final String ZIP = ".zip";
   private static final String XLS = ".xls";
   private static final String NOME_RELATORIO = "Relatorio_";

   private static final String NOME_ABA_PLANILHA = "CREDITO CONSTITUIDO";
   /*
   * Quantidade de colunas do relatório
   */
   private static final int QUANTIDADE_COLUNAS = 13;
   /*
    * Indice das colunas do relatório
    */
   private static final int TIPO_GIA = 0;
   private static final int NUMERO_GIA = 1;
   private static final int DATA_CRIACAO = 2;
   private static final int CPF_BENEFICIARIO = 3;
   private static final int NOME_BENEFICIARIO = 4;
   private static final int STATUS_ITCD = 5;
   private static final int DATA_CIENCIA_NOTIFICACAO = 6;
   private static final int VALOR_BENEFICIARIO_RECEBIDO_APOS_AVALIACAO = 7;   
   private static final int VALOR_TIBUTO = 8;   
   private static final int VALOR_PENALIDADE = 9;
   private static final int VALOR_MULTA = 10;
   private static final int VALOR_JUROS = 11;
   private static final int VALOR_CORRECAO_MONETARIA = 12;


   /**
    * Construtor Padrão
    * @throws SQLException
    
    */
   public ProcessarRelatorioValorBeneficiarioNotificadoRetificadoBe()
      throws SQLException
   {
      super();
   }

   /**
    * Construtor que recebe a Conexão com o Banco de Dados
    * @param conn
    
    */
   public ProcessarRelatorioValorBeneficiarioNotificadoRetificadoBe(Connection conn)
   {
      super(conn);
      giaItcdBe = new GIAITCDBe(conn);
   }

   /**
    * 
    
    */
   private void init()
   {
      arquivoExcel = new HSSFWorkbook();
      arquivoExcel.createSheet(NOME_ABA_PLANILHA);
      contadorLinhaPlanilha = 0;
   }


   public String processarRelatorio()
      throws SQLException, ConsultaException, ObjetoObrigatorioException, 
             ParametroObrigatorioException, ConexaoException, 
             IntegracaoException
   {

      PedidoRelatorioVo pedidosParaProcessamento = listarRelatorioNaoProcessado();
      StatusGIAITCDBe statusGIAITCDBe = null;

      ExibirLOG.exibirLog("Quantidade de relatorios para processar: " + pedidosParaProcessamento.getCollVO().size());

      for (PedidoRelatorioVo pedido: pedidosParaProcessamento.getListVo())
      {
         try
         {
            statusGIAITCDBe = new StatusGIAITCDBe(conn);
            GIAITCDVo giaITCDVoCodigo = new GIAITCDVo();

            giaItcdBe.listarCodigoGiaRelatorioCreditoConstituido(giaITCDVoCodigo, pedido);

            criarRelatorio();
            
            ExibirLOG.exibirLogSimplificado("Quantidade GIA a processar " + giaITCDVoCodigo.getCollVO().size());

            for (GIAITCDVo giaParametro: giaITCDVoCodigo.getCollVO())
            {
               GIAITCDBe giaItcdProcessamentoBe = null;
               
               try
               {
                  ExibirLOG.exibirLog("Nova conexão Processando GIA " + giaParametro);
                  giaItcdProcessamentoBe = new GIAITCDBe();               
                  GIAITCDVo giaItcdVoConsulta = new GIAITCDVo(giaParametro);
                  giaItcdVoConsulta.setCodigo(giaParametro.getCodigo());
                  giaItcdVoConsulta.setConsultaCompleta(true);
                  GIAITCDVo giaItcdVo = giaItcdProcessamentoBe.consultaGIAITCDBasico(giaItcdVoConsulta); 
                  StatusGIAITCDVo statusVo = statusGIAITCDBe.procurarUltimoStatusCollectionVo(giaItcdVo.getStatusVo());   
                  if(!statusVo.isStatusIn(new int[]{ DomnStatusGIAITCD.INATIVO, DomnStatusGIAITCD.INATIVADO_AUTOMATICAMENTE }))
                  {
                     statusVo.setCollVO(giaItcdVo.getStatusVo().getCollVO());
                     giaItcdVo.setStatusVo(statusVo);
                     ExibirLOG.exibirLogSimplificado("...Exportando Gia para Excel...");
                     exportarDadosExcelGia(giaItcdVo);
                     ExibirLOG.exibirLogSimplificado("...Termino exportação da Gia para Excel...");
                     ExibirLOG.exibirLog("Termino do Processando GIA " + giaParametro);  
                  }                   
               }catch(Exception e)
               {
                  throw e;
               }
               finally
               {
                  giaItcdProcessamentoBe.close();
                  giaItcdProcessamentoBe = null;
                  ExibirLOG.exibirLog("Fechou conexão Processando GIA " + giaParametro);
               }              
              
            }
            ExibirLOG.exibirLogSimplificado("Gerando arquivo Excel do pedido " + pedido.getCodigo() +" Tipo " + pedido.getTipoRelatorio().getTextoCorrente());
            gerarArquivoExcel(pedido);
         }
         catch (Exception e)
         {
            pedido.setSituacaoProcessamento(new DomnSituacaoProcessamento(DomnSituacaoProcessamento.PROCESSADO_COM_ERRRO));
            try
            {
               atualizarPedidoRelatorio(pedido);
            }
            catch (Exception ex)
            {
               ExibirLOG.exibirLog("Falha a atualizar status para PROCESSADO_COM_ERRRO : ");
               ex.printStackTrace();
            }
            e.printStackTrace();
         }
         finally
         {
            if(statusGIAITCDBe != null)
            {
               statusGIAITCDBe.close();
               statusGIAITCDBe = null;
            }
         }
      }

      return "";
   }

   /**
    * 
    
    */
   private void gravarCabecalhoNaPlanilha()
   {
      Object[] cabecalho =
      { "Tipo de GIA-ITCD", "Número da GIA-ITCD", "Data da Criação", 
        "Número Documento Contribuinte(Beneficiario ou Cônjuge)",
        "Nome do Contribuinte", "Situação Atual da GIA-ITCD", 
        "Data ciência da Notificação", "(R$)Valor Beneficiário(Após Avaliação)",
        "(R$) Tributo", "(R$) Penalidade", "(R$) Multa de mora",
        "(R$) Juros", "(R$) Correção Monetária"};

      gravarDadosPlanilha(cabecalho);
   }

   /**
    * 
    
    */
   private void criarRelatorio()
   {
      init();
      gravarCabecalhoNaPlanilha();
   }

   /**
    * 
    * @param dado
    
    */
   private void gravarDadosPlanilha(Object[] dado)
   {

      Map<Integer, Object[]> dados = new TreeMap<Integer, Object[]>();

      dados.put(contadorLinhaPlanilha, dado);

      Set<Integer> keyset = dados.keySet();
      for (Integer key: keyset)
      {
         HSSFSheet sheets = arquivoExcel.getSheet(NOME_ABA_PLANILHA);
         Row row = sheets.createRow(contadorLinhaPlanilha++);
         Object[] dadosLinha = dados.get(key);
         int cellnum = 0;
         for (Object obj: dadosLinha)
         {
            Cell cell = row.createCell(cellnum++);
            if (obj instanceof String)
            {
               cell.setCellValue((String) obj);
            }
            else if (obj instanceof Integer)
            {
               cell.setCellValue((Integer) obj);
            }
            else if (obj instanceof Long)
            {
               cell.setCellValue(obj.toString());
            }
            else if (obj instanceof Date)
            {
               cell.setCellValue((Date) obj);
            }
         }
      }
   }

   /**
    * 
    * @param giaITCDVo
    
    */
   private void exportarDadosExcelGia(final GIAITCDVo giaITCDVo)
      throws Exception
   {
      switch (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente())
      {
         case DomnTipoProcesso.DOACAO:
            exportarDadosExcelGiaDoacao((GIAITCDDoacaoVo) giaITCDVo);
            break;
         case DomnTipoProcesso.INVENTARIO_ARROLAMENTO:
            exportarDadosExcelGiaInventario((GIAITCDInventarioArrolamentoVo) giaITCDVo);
            break;
         case DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA:
            exportarDadosExcelGiaSeparacao((GIAITCDSeparacaoDivorcioVo) giaITCDVo);
            break;
      }
   }

  

   /**
    * 
    * @param giaITCDDoacaoVo
    
    */
   private void exportarDadosExcelGiaDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo)
      throws SQLException, ConsultaException, ObjetoObrigatorioException, 
             ParametroObrigatorioException, IntegracaoException, 
             DadoNecessarioInexistenteException, ConexaoException, DarException
   {
      StatusGIAITCDBe statusGIAITCDBe = null;
      try
      {
         statusGIAITCDBe = new StatusGIAITCDBe(conn);
         String valorJuros = "";
         String valorMultaMora = "";
         String valorCorrecaoMonetaria = "";
         String dataCiencia = "";
         DocumentoArrecadacaoVo dar = null;
            
         if (giaITCDDoacaoVo.getStatusVo().isStatusIn(new int[]
               { DomnStatusGIAITCD.QUITADO, 
                 DomnStatusGIAITCD.QUITADO_MANUALMENTE, 
                 DomnStatusGIAITCD.QUITADO_CCF, 
                 DomnStatusGIAITCD.QUITADO_PELA_GIOR }))
         
         {
            valorJuros =  giaITCDDoacaoVo.getGiaITCDDarVo().getDarEmitido().getValorJurosFormatado();
            valorMultaMora = giaITCDDoacaoVo.getGiaITCDDarVo().getDarEmitido().getValorMultaFormatado();
            valorCorrecaoMonetaria =  giaITCDDoacaoVo.getGiaITCDDarVo().getDarEmitido().getValorCorrecaoMonetariaFormatado();
         }
         else if(giaITCDDoacaoVo.getStatusVo().isStatusIn(new int[]
               { DomnStatusGIAITCD.RETIFICADO, 
                 DomnStatusGIAITCD.NOTIFICADO, 
                 DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA, 
                 DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA,
                 DomnStatusGIAITCD.RATIFICADO,
                 DomnStatusGIAITCD.SEGUNDA_RETIFICACAO,
                 DomnStatusGIAITCD.NOTIFICADO_CIENTE,
                 DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE,
                 DomnStatusGIAITCD.RATIFICADO_CIENTE}))
         {
            dar = getDocumentoArrecadacao(giaITCDDoacaoVo);
            if(Validador.isObjetoValido(dar))
            {
               valorJuros = StringUtil.doubleToMonetario(dar.getValrJuros());
               valorMultaMora = StringUtil.doubleToMonetario(dar.getValrMulta());
               valorCorrecaoMonetaria = StringUtil.doubleToMonetario(dar.getValrCorrMonetaria());
            }
            
         }
         
         dataCiencia =  getDataCiencia(statusGIAITCDBe, giaITCDDoacaoVo.getStatusVo());
         
         for (int i = 0; i < giaITCDDoacaoVo.getBeneficiarioVo().getListVo().size(); i++) 
         {
            BeneficiarioVo beneficiario = (BeneficiarioVo) giaITCDDoacaoVo.getBeneficiarioVo().getListVo().toArray()[i];
         
            Object[] dadosGia = new Object[QUANTIDADE_COLUNAS];
            GIAITCDDoacaoBeneficiarioVo beneficiarioDoacao =  (GIAITCDDoacaoBeneficiarioVo) beneficiario;
            dadosGia[TIPO_GIA] = giaITCDDoacaoVo.getNaturezaOperacaoVo().getTipoProcesso().getTextoCorrente();
            dadosGia[NUMERO_GIA] = giaITCDDoacaoVo.getCodigo();
            dadosGia[DATA_CRIACAO] = giaITCDDoacaoVo.getDataCriacaoFormatada();
            dadosGia[CPF_BENEFICIARIO] = beneficiarioDoacao.getPessoaBeneficiaria().getNumrDocumento();
            dadosGia[NOME_BENEFICIARIO] = beneficiarioDoacao.getPessoaBeneficiaria().getNomeContribuinte();
            dadosGia[STATUS_ITCD] = giaITCDDoacaoVo.getStatusVo().getStatusGIAITCD().getTextoCorrente();
            dadosGia[DATA_CIENCIA_NOTIFICACAO] =  dataCiencia; 
            dadosGia[VALOR_BENEFICIARIO_RECEBIDO_APOS_AVALIACAO] = beneficiarioDoacao.getValorRecebidoFormatado();
            dadosGia[VALOR_TIBUTO] =  beneficiarioDoacao.getValorRecolherFormatado();
            if(i < 1)
            {
               dadosGia[VALOR_PENALIDADE] = giaITCDDoacaoVo.getValorMultaFormatado();
               dadosGia[VALOR_MULTA] = valorMultaMora;
               dadosGia[VALOR_JUROS] = valorJuros;
               dadosGia[VALOR_CORRECAO_MONETARIA] = valorCorrecaoMonetaria;
            }

            gravarDadosPlanilha(dadosGia);
         }
      }finally
      {
         statusGIAITCDBe = null;
      }
      
   }

   private void exportarDadosExcelGiaInventario(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo)
      throws SQLException, ConsultaException, ObjetoObrigatorioException, 
             ParametroObrigatorioException, IntegracaoException, 
             ConexaoException, DadoNecessarioInexistenteException, DarException
   {
      StatusGIAITCDBe statusGIAITCDBe = null;
      try
      {
         statusGIAITCDBe = new StatusGIAITCDBe(conn);
         String valorJuros = "";
         String valorMultaMora = "";
         String valorCorrecaoMonetaria = "";
         String dataCiencia = "";
         DocumentoArrecadacaoVo dar = null;
         
         if (giaITCDInventarioArrolamentoVo.getStatusVo().isStatusIn(new int[]
               { DomnStatusGIAITCD.QUITADO, 
                 DomnStatusGIAITCD.QUITADO_MANUALMENTE, 
                 DomnStatusGIAITCD.QUITADO_CCF, 
                 DomnStatusGIAITCD.QUITADO_PELA_GIOR }))
         
         {
            valorJuros =  giaITCDInventarioArrolamentoVo.getGiaITCDDarVo().getDarEmitido().getValorJurosFormatado();
            if(Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getGiaITCDDarVo().getDarEmitido().getValorMulta()))
            {
               double valor = giaITCDInventarioArrolamentoVo.getGiaITCDDarVo().getDarEmitido().getValorMulta() - giaITCDInventarioArrolamentoVo.getValorMulta();
               valorMultaMora = StringUtil.doubleToMonetario(valor);
            }
            valorCorrecaoMonetaria =  giaITCDInventarioArrolamentoVo.getGiaITCDDarVo().getDarEmitido().getValorCorrecaoMonetariaFormatado();
         }
         else if(giaITCDInventarioArrolamentoVo.getStatusVo().isStatusIn(new int[]
               { DomnStatusGIAITCD.RETIFICADO, 
                 DomnStatusGIAITCD.NOTIFICADO, 
                 DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA, 
                 DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA,
                 DomnStatusGIAITCD.RATIFICADO,
                 DomnStatusGIAITCD.SEGUNDA_RETIFICACAO,
                 DomnStatusGIAITCD.NOTIFICADO_CIENTE,
                 DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE,
                 DomnStatusGIAITCD.RATIFICADO_CIENTE}))
         {
            dar = getDocumentoArrecadacao(giaITCDInventarioArrolamentoVo);
            if(Validador.isObjetoValido(dar))
            {
               valorJuros = StringUtil.doubleToMonetario(dar.getValrJuros());
               if(Validador.isNumericoValido(dar.getValrMulta()))
               {
                  double valor = dar.getValrMulta() - giaITCDInventarioArrolamentoVo.getValorMulta();
                  valorMultaMora = StringUtil.doubleToMonetario(valor);
               }
               
               valorCorrecaoMonetaria = StringUtil.doubleToMonetario(dar.getValrCorrMonetaria());
            }
            
         }
        
         dataCiencia =  getDataCiencia(statusGIAITCDBe, giaITCDInventarioArrolamentoVo.getStatusVo());
        
         for (int i = 0; i < giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getListVo().size(); i++) 
         {
            BeneficiarioVo beneficiario = (BeneficiarioVo) giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getListVo().toArray()[i];
           
            Object[] dadosGia = new Object[QUANTIDADE_COLUNAS];
            GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioTemp = (GIAITCDInventarioArrolamentoBeneficiarioVo) beneficiario;
            dadosGia[TIPO_GIA] = giaITCDInventarioArrolamentoVo.getNaturezaOperacaoVo().getTipoProcesso().getTextoCorrente();
            dadosGia[NUMERO_GIA] = giaITCDInventarioArrolamentoVo.getCodigo();
            dadosGia[DATA_CRIACAO] = giaITCDInventarioArrolamentoVo.getDataCriacaoFormatada();
            dadosGia[CPF_BENEFICIARIO] = beneficiarioTemp.getPessoaBeneficiaria().getNumrDocumento();
            dadosGia[NOME_BENEFICIARIO] = beneficiarioTemp.getPessoaBeneficiaria().getNomeContribuinte();
            dadosGia[STATUS_ITCD] = giaITCDInventarioArrolamentoVo.getStatusVo().getStatusGIAITCD().getTextoCorrente();
            dadosGia[DATA_CIENCIA_NOTIFICACAO] = dataCiencia;
            dadosGia[VALOR_BENEFICIARIO_RECEBIDO_APOS_AVALIACAO] = beneficiarioTemp.getValorRecebidoFormatado();
            dadosGia[VALOR_TIBUTO] =  beneficiarioTemp.getValorITCDBeneficiarioFormatado();
            if(i < 1)
            {  
               dadosGia[VALOR_PENALIDADE] = giaITCDInventarioArrolamentoVo.getValorMultaFormatado();               
               dadosGia[VALOR_MULTA] = valorMultaMora;
               dadosGia[VALOR_JUROS] = valorJuros;
               dadosGia[VALOR_CORRECAO_MONETARIA] = valorCorrecaoMonetaria;
            }
            
            gravarDadosPlanilha(dadosGia);
         }
         
         if(Validador.isObjetoValido(giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario()) &&  giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getCodigo() > 0)
         {
            Object[] dadosGia = new Object[QUANTIDADE_COLUNAS];
            GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioTemp = giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario();
            dadosGia[TIPO_GIA] = giaITCDInventarioArrolamentoVo.getNaturezaOperacaoVo().getTipoProcesso().getTextoCorrente();
            dadosGia[NUMERO_GIA] = giaITCDInventarioArrolamentoVo.getCodigo();
            dadosGia[DATA_CRIACAO] = giaITCDInventarioArrolamentoVo.getDataCriacaoFormatada();
            dadosGia[CPF_BENEFICIARIO] = beneficiarioTemp.getPessoaBeneficiaria().getNumrDocumento();
            dadosGia[NOME_BENEFICIARIO] = beneficiarioTemp.getPessoaBeneficiaria().getNomeContribuinte();
            dadosGia[STATUS_ITCD] = giaITCDInventarioArrolamentoVo.getStatusVo().getStatusGIAITCD().getTextoCorrente();
            dadosGia[DATA_CIENCIA_NOTIFICACAO] = dataCiencia;
            dadosGia[VALOR_BENEFICIARIO_RECEBIDO_APOS_AVALIACAO] = beneficiarioTemp.getValorRecebidoFormatado();
            dadosGia[VALOR_TIBUTO] =  beneficiarioTemp.getValorITCDBeneficiarioFormatado();
            
            gravarDadosPlanilha(dadosGia);
         }
         
      }finally
      {
         statusGIAITCDBe = null;
      }
   }

   private void exportarDadosExcelGiaSeparacao(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo)
      throws SQLException, ConsultaException, ObjetoObrigatorioException, 
             ParametroObrigatorioException, IntegracaoException, 
             ConexaoException, DadoNecessarioInexistenteException, DarException
   {
         StatusGIAITCDBe statusGIAITCDBe = null;        
         
         try
         {
            statusGIAITCDBe = new StatusGIAITCDBe(conn);
           
            String dataNotificacao = "";
            String valorJuros = "";
            String valorMultaMora = "";
            String valorCorrecaoMonetaria = "";
            DocumentoArrecadacaoVo dar = null;
            String tipoGia =  giaITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().getTipoProcesso().getTextoCorrente();
            String numeroGia =  String.valueOf(giaITCDSeparacaoDivorcioVo.getCodigo());
            String dataCriacao =  giaITCDSeparacaoDivorcioVo.getDataCriacaoFormatada();
            ConjugeBemTributavelVo conjuge1 = giaITCDSeparacaoDivorcioVo.getConjuge1();
            ConjugeBemTributavelVo conjuge2 = giaITCDSeparacaoDivorcioVo.getConjuge2();
            ConjugeBemTributavelVo conjugeExcesso = giaITCDSeparacaoDivorcioVo.getConjugeExcesso();
            String statusGia = giaITCDSeparacaoDivorcioVo.getStatusVo().getStatusGIAITCD().getTextoCorrente();           
            String valorRecebido = giaITCDSeparacaoDivorcioVo.getValorBaseCalculoTributavelFormatado();
            String valorTributo = giaITCDSeparacaoDivorcioVo.getValorITCDFormatado();
            String valorPenalidade = giaITCDSeparacaoDivorcioVo.getValorMultaFormatado();

            ExibirLOG.exibirLogSimplificado("CONJUGE 1: " + conjuge1.getPessoaConjuge().getNumrDocumento() + " - " + conjuge1.getPessoaConjuge().getNomeContribuinte());
            ExibirLOG.exibirLogSimplificado("CONJUGE 2: " + conjuge2.getPessoaConjuge().getNumrDocumento() + " - " + conjuge2.getPessoaConjuge().getNomeContribuinte());
         
            dataNotificacao =  getDataCiencia(statusGIAITCDBe, giaITCDSeparacaoDivorcioVo.getStatusVo());  
                       
            if (giaITCDSeparacaoDivorcioVo.getStatusVo().isStatusIn(new int[]
                  { DomnStatusGIAITCD.QUITADO, 
                    DomnStatusGIAITCD.QUITADO_MANUALMENTE, 
                    DomnStatusGIAITCD.QUITADO_CCF, 
                    DomnStatusGIAITCD.QUITADO_PELA_GIOR }))
            
            {
               valorJuros =  giaITCDSeparacaoDivorcioVo.getGiaITCDDarVo().getDarEmitido().getValorJurosFormatado();
               valorMultaMora = giaITCDSeparacaoDivorcioVo.getGiaITCDDarVo().getDarEmitido().getValorMultaFormatado();
               valorCorrecaoMonetaria =  giaITCDSeparacaoDivorcioVo.getGiaITCDDarVo().getDarEmitido().getValorCorrecaoMonetariaFormatado();
            }
            else if(giaITCDSeparacaoDivorcioVo.getStatusVo().isStatusIn(new int[]
                  { DomnStatusGIAITCD.RETIFICADO, 
                    DomnStatusGIAITCD.NOTIFICADO, 
                    DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA, 
                    DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA,
                    DomnStatusGIAITCD.RATIFICADO,
                    DomnStatusGIAITCD.SEGUNDA_RETIFICACAO,
                    DomnStatusGIAITCD.NOTIFICADO_CIENTE,
                    DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE,
                    DomnStatusGIAITCD.RATIFICADO_CIENTE}))
            {
               dar = getDocumentoArrecadacao(giaITCDSeparacaoDivorcioVo);
               if(Validador.isObjetoValido(dar))
               {
                  valorJuros = StringUtil.doubleToMonetario(dar.getValrJuros());
                  valorMultaMora = StringUtil.doubleToMonetario(dar.getValrMulta());
                  valorCorrecaoMonetaria = StringUtil.doubleToMonetario(dar.getValrCorrMonetaria());
               }               
            }
            
            Object[] dadosGiaConjuge1 = new Object[QUANTIDADE_COLUNAS];
            dadosGiaConjuge1[TIPO_GIA] = tipoGia;
            dadosGiaConjuge1[NUMERO_GIA] = numeroGia;
            dadosGiaConjuge1[DATA_CRIACAO] = dataCriacao;
            dadosGiaConjuge1[CPF_BENEFICIARIO] = conjuge1.getPessoaConjuge().getNumrDocumento();;
            dadosGiaConjuge1[NOME_BENEFICIARIO] = conjuge1.getPessoaConjuge().getNomeContribuinte();
            dadosGiaConjuge1[STATUS_ITCD] = statusGia;
            dadosGiaConjuge1[DATA_CIENCIA_NOTIFICACAO] = dataNotificacao;
           
            if(conjuge1.getPessoaConjuge().getNumrContribuinte().longValue() == conjugeExcesso.getPessoaConjuge().getNumrContribuinte().longValue())
            {        
               dadosGiaConjuge1[VALOR_BENEFICIARIO_RECEBIDO_APOS_AVALIACAO] = valorRecebido;
               dadosGiaConjuge1[VALOR_TIBUTO] = valorTributo;
               dadosGiaConjuge1[VALOR_PENALIDADE] = valorPenalidade;
               dadosGiaConjuge1[VALOR_MULTA] = valorMultaMora;
               dadosGiaConjuge1[VALOR_JUROS] = valorJuros;               
               dadosGiaConjuge1[VALOR_CORRECAO_MONETARIA] = valorCorrecaoMonetaria;
            }
            gravarDadosPlanilha(dadosGiaConjuge1);
            
            
            Object[] dadosGiaConjuge2 = new Object[QUANTIDADE_COLUNAS];
            dadosGiaConjuge2[TIPO_GIA] = tipoGia;
            dadosGiaConjuge2[NUMERO_GIA] = numeroGia;
            dadosGiaConjuge2[DATA_CRIACAO] = dataCriacao;
            dadosGiaConjuge2[CPF_BENEFICIARIO] = conjuge2.getPessoaConjuge().getNumrDocumento();;
            dadosGiaConjuge2[NOME_BENEFICIARIO] = conjuge2.getPessoaConjuge().getNomeContribuinte();
            dadosGiaConjuge2[STATUS_ITCD] = statusGia;
            dadosGiaConjuge2[DATA_CIENCIA_NOTIFICACAO] = dataNotificacao;            
            
            if(conjuge2.getPessoaConjuge().getNumrContribuinte().longValue() == conjugeExcesso.getPessoaConjuge().getNumrContribuinte().longValue())
            {
               dadosGiaConjuge2[VALOR_BENEFICIARIO_RECEBIDO_APOS_AVALIACAO] = valorRecebido;
               dadosGiaConjuge2[VALOR_TIBUTO] = valorTributo;
               dadosGiaConjuge2[VALOR_PENALIDADE] = valorPenalidade;
               dadosGiaConjuge2[VALOR_MULTA] = valorMultaMora;
               dadosGiaConjuge2[VALOR_JUROS] = valorJuros;
               dadosGiaConjuge2[VALOR_CORRECAO_MONETARIA] = valorCorrecaoMonetaria;
            }
            gravarDadosPlanilha(dadosGiaConjuge2);
      }finally
      {
         statusGIAITCDBe = null;
      }
   }

   /**
    * Lista os pedidos de relatório que são do tipo DomnTipoRelatorio.VALOR_POR_BENEFICIARIO_APOS_AVALIACAO
    * e ainda não foram processados
    * 
    
    */
   private PedidoRelatorioVo listarRelatorioNaoProcessado()
      throws SQLException, ConsultaException, ObjetoObrigatorioException, 
             ParametroObrigatorioException
   {

      PedidoRelatorioVo parametro = new PedidoRelatorioVo();
      parametro.setTipoRelatorio(new DomnTipoRelatorio(DomnTipoRelatorio.CREDITO_CONSTITUIDO));
      parametro.setSituacaoProcessamento(new DomnSituacaoProcessamento(DomnSituacaoProcessamento.NAO_PROCESSADO));

      PedidoRelatorioVo pedidoRelatorioVo = new PedidoRelatorioVo(parametro);

      PedidoRelatorioBe pedidoRelatorioBe = new PedidoRelatorioBe(conn);
      pedidoRelatorioBe.listarPedidoRelatorioCompleto(pedidoRelatorioVo);
      return pedidoRelatorioVo;
   }

   /**
    * 
    
    */
   private void gerarArquivoExcel(PedidoRelatorioVo pedidoRelatorio)
      throws FileNotFoundException, IOException, SQLException, 
             RegistroExistenteException, ObjetoObrigatorioException, 
             ParametroObrigatorioException, ConsultaException, 
             ConexaoException, LogException, AnotacaoException, 
             PersistenciaException
   {
      // Criando arquivo excel temporário no diretório de serviços
      String nomeRelatorio = 
         NOME_RELATORIO + pedidoRelatorio.getCodigo() + XLS;
      File arquivo = new File(CAMINHO_DIRETORIO_TEMPORARIO + nomeRelatorio);

      FileOutputStream out = new FileOutputStream(arquivo);
      arquivoExcel.write(out);
      out.close();

      // Zipando arquivo criado
      FileInputStream fis = 
         new FileInputStream(CAMINHO_DIRETORIO_TEMPORARIO + nomeRelatorio);
      FileOutputStream fos = 
         new FileOutputStream(CAMINHO_DIRETORIO_TEMPORARIO + NOME_RELATORIO + 
                              pedidoRelatorio.getCodigo() + ZIP);
      ZipOutputStream zipOut = new ZipOutputStream(fos);

      zipOut.putNextEntry(new ZipEntry(nomeRelatorio));

      int content;
      while ((content = fis.read()) != -1)
      {
         zipOut.write(content);
      }

      zipOut.closeEntry();
      zipOut.close();
      fos.close();


      // Pega o arquivo zip para enviar para o Castor.
      File arquivoZip = new File(CAMINHO_DIRETORIO_TEMPORARIO + NOME_RELATORIO + 
                  pedidoRelatorio.getCodigo() + ZIP);

      // CastorBe castor = new CastorBe(conn);
      ServicoCastorBe castor = new ServicoCastorBe(conn);
      ObjetoVo objetoVo = castor.gravar(arquivoZip, TabelasITC.TABELA_PEDIDO_RELATORIO);

      if (Validador.isObjetoValido(objetoVo) && Validador.isNumericoValido(objetoVo.getNumrObjetoSeqc()))
      {
         pedidoRelatorio.getCastorObjetoIntegracaoVo().setCodigo(objetoVo.getNumrObjetoSeqc());
         pedidoRelatorio.setSituacaoProcessamento(new DomnSituacaoProcessamento(DomnSituacaoProcessamento.PROCESSADO));
         atualizarPedidoRelatorio(pedidoRelatorio);
      }

      //Deleta os arquivos
      arquivo.delete();
      arquivoZip.delete();

      ExibirLOG.exibirLog("Arquivo deletado do diretório temporário ...");
   }

   /**
    * 
    * 
    * 
    * @param pedidoRelatorio
    * @throws SQLException
    */
   private void atualizarPedidoRelatorio(PedidoRelatorioVo pedidoRelatorio)
      throws SQLException, RegistroExistenteException, 
             ObjetoObrigatorioException, ParametroObrigatorioException, 
             ConsultaException, ConexaoException, LogException, 
             AnotacaoException, PersistenciaException
   {

      PedidoRelatorioBe pedidoRelatorioBe = new PedidoRelatorioBe(conn);

      pedidoRelatorio.setDataProcessamento(new Date());

      pedidoRelatorioBe.alterarPedidoRelatorio(pedidoRelatorio);

      conn.commit();
   }

   private DocumentoArrecadacaoVo getDocumentoArrecadacao(GIAITCDVo giaitcdVo)
      throws ConsultaException, ObjetoObrigatorioException, 
             ParametroObrigatorioException, IntegracaoException, 
             ConexaoException, DadoNecessarioInexistenteException, DarException
   {
      DocumentoArrecadacaoITCDBe documentoArrecadacaoBe = null;
      GIAITCDBe giaItcdBe = null;
      try
      {
         if (giaitcdVo.getGiaITCDDarVo().isExisteDarEmitido())
         {
            giaItcdBe = new GIAITCDBe(conn);
            StatusGIAITCDVo statusAnteriorGIA = 
               giaItcdBe.obterStatusAnteriorGIAITCD(giaitcdVo);

            if (statusAnteriorGIA.getStatusGIAITCD().is(DomnStatusGIAITCD.AVALIADO) & 
                (StatusGIAITCDBe.contarStatusNaCollectionDoVo(giaitcdVo.getStatusVo(), 
                                                              new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO)) >= 
                 2))
            {
               ExibirLOG.exibirLog("GIA " + giaitcdVo.getCodigo() + " DAR NORMAL ");
               documentoArrecadacaoBe = new DocumentoArrecadacaoGiaNormalBe(conn);
            }
            else
            {

               if (giaitcdVo.getStatusVo().isStatusIn(new int[]
                     { DomnStatusGIAITCD.RETIFICADO_CIENTE, 
                       DomnStatusGIAITCD.NOTIFICADO_CIENTE }) || 
                  !giaitcdVo.getGiaITCDDarVo().isDarAtrasado())
               {
                  ExibirLOG.exibirLog("GIA " + giaitcdVo.getCodigo() + " DAR NORMAL ");
                  documentoArrecadacaoBe =  new DocumentoArrecadacaoGiaNormalBe(conn);
               }
               else if (!giaItcdBe.dataVencimentoInvalida(giaitcdVo, true))
               {
                  ExibirLOG.exibirLog("GIA " + giaitcdVo.getCodigo() + " DAR NORMAL ");
                  documentoArrecadacaoBe = new DocumentoArrecadacaoGiaNormalBe(conn);
               }
               else
               {
                  documentoArrecadacaoBe = new DocumentoArrecadacaoGiaVencidoBe(conn);
               }

            }
         }
         else if (giaitcdVo.getStatusVo().isStatusIn(new int[]
                     {DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA,
                     DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA }))
         {
              documentoArrecadacaoBe = new DocumentoArrecadacaoGiaVencidoBe(conn);
         } else 
         {
              ExibirLOG.exibirLog("GIA " + giaitcdVo.getCodigo() + " DAR NORMAL ");
              documentoArrecadacaoBe = new DocumentoArrecadacaoGiaNormalBe(conn);
         }
     
         giaitcdVo.getGiaITCDDarVo().setGia(giaitcdVo);
         ExibirLOG.exibirLogSimplificado("GIA " + giaitcdVo.getCodigo() + " STATUS " + giaitcdVo.getStatusVo().getStatusGIAITCD().getTextoCorrente());
         return documentoArrecadacaoBe.getInfoDarRelatorio(giaitcdVo, giaitcdVo.getGiaITCDDarVo());
     
     
      }finally
      {
         giaItcdBe = null;
         documentoArrecadacaoBe = null;
      }
     
   }

   private String getDataCiencia(StatusGIAITCDBe statusGIAITCDBe, StatusGIAITCDVo statusGIAITCDVo)
      throws ObjetoObrigatorioException
   {
         
      ordenarStatusGiaPorCodigoDesc(statusGIAITCDVo);
   
      StatusGIAITCDVo statusVo = statusGIAITCDBe.procurarStatusNaCollectionDoVo(statusGIAITCDVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.RATIFICADO_CIENTE));
      if(statusVo != null && Validador.isDataValida(statusVo.getDataCienciaRatificacao()))
      {
         return statusVo.getDataCienciaRatificacaoFormatada();
      }
      statusVo = statusGIAITCDBe.procurarStatusNaCollectionDoVo(statusGIAITCDVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO_CIENTE));
      if(statusVo != null && Validador.isDataValida(statusVo.getDataCienciaRetificacao()))
      {
         return statusVo.getDataCienciaRetificacaoFormatado();
      }
      statusVo = statusGIAITCDBe.procurarStatusNaCollectionDoVo(statusGIAITCDVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.NOTIFICADO_CIENTE));
      if(statusVo != null && Validador.isDataValida(statusVo.getDataCienciaNotificacao()))
      {
         return statusVo.getDataCienciaNotificacaoFormatada();
      }
      statusVo = statusGIAITCDBe.procurarStatusNaCollectionDoVo(statusGIAITCDVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.RATIFICADO));
      if(statusVo != null && Validador.isDataValida(statusVo.getDataRatificacao()))
      {
         return statusVo.getDataRatificacaoFormatado();
      }
      statusVo = statusGIAITCDBe.procurarStatusNaCollectionDoVo(statusGIAITCDVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.NOTIFICADO));
      if(statusVo != null && Validador.isDataValida(statusVo.getDataNotificacao()))
      {
         return statusVo.getDataNotificacaoFormatado();
      }
      statusVo = statusGIAITCDBe.procurarStatusNaCollectionDoVo(statusGIAITCDVo, new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO));
      if(statusVo != null && Validador.isDataValida(statusVo.getDataEmissaoRetificacao()))
      {
         return statusVo.getDataEmissaoRetificacaoFormatado();
      }
      
      return "";
   }


   private void ordenarStatusGiaPorCodigoDesc(StatusGIAITCDVo statusGIAITCDVo)
   {
      Collection collVO = statusGIAITCDVo.getCollVO();
      if (Validador.isCollectionValida(collVO))
      {
         Collections.sort((List) collVO, new Comparator()
               {
                  public int compare(Object object1, Object object2)
                  {
                     return ((Long) ((StatusGIAITCDVo) object2).getCodigo()).compareTo(((Long) ((StatusGIAITCDVo) object1).getCodigo()));
                  }
               });
               
          statusGIAITCDVo.setCollVO(collVO);      
      }
   }
   
}
