package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;

import br.gov.mt.sefaz.cas.integracao.ObjetoVo;
import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.GIAITCDInventarioArrolamentoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota.GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.PedidoRelatorioBe;
import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.PedidoRelatorioVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSituacaoProcessamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoRelatorio;
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

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Classe de negócio para a Baixa Automática do D.A.R.
 * @author Daniel Balieiro
 * @implemented by Dherkyan Ribeiro
 */
public class ProcessarRelatorioValorBeneficiarioAposAvaliacaoBe extends AbstractBe
{

   private GIAITCDBe giaItcdBe = null;
   private HSSFWorkbook arquivoExcel = null;
   private int contadorLinhaPlanilha = 0;

    private static final String CAMINHO_DIRETORIO_TEMPORARIO = "/usr/appl/servicos/itc/ArquivosTemporarios/";
   //private static final String CAMINHO_DIRETORIO_TEMPORARIO =  "C:\\usr\\appl\\servicos\\itc\\ArquivosTemporarios\\";
   private static final String ZIP = ".zip";
   private static final String XLS = ".xls";
   private static final String NOME_RELATORIO = "Relatorio_";
   
   private static final String NOME_ABA_PLANILHA = "VALOR APOS AVALIACÃO";
   /*
   * Quantidade de colunas do relatório
   */
   private static final int QUANTIDADE_COLUNAS = 15;
   /*
    * Indice das colunas do relatório
    */
   private static final int NUMERO_GIA = 0;
   private static final int DATA_CRIACAO = 1;
   private static final int TIPO_GIA = 2;
   private static final int STATUS_ITCD = 3;
   private static final int DATA_OBITO = 4;
   private static final int CPF_BENEFICIARIO = 5;
   private static final int NOME_BENEFICIARIO = 6;
   private static final int VALOR_BENEFICIARIO_RECEBIDO_DECLARACAO = 7;
   private static final int VALOR_BENEFICIARIO_RECEBIDO_APOS_AVALIACAO = 8;
   private static final int CPF_CONJUGE_1 = 9;
   private static final int NOME_CONJUGE_1 = 10;
   private static final int CPF_CONJUGE_2 = 11;
   private static final int NOME_CONJUGE_2 = 12;
   private static final int BASE_CALCULO_CONCJUGE_SEPARACAO = 13;
   private static final int ALIQUOTA = 14;

   /**
    * Construtor Padrão
    * @throws SQLException
    * @implemented by Dherkyan Ribeiro
    */
   public ProcessarRelatorioValorBeneficiarioAposAvaliacaoBe() throws SQLException
   {
      super();
   }

   /**
    * Construtor que recebe a Conexão com o Banco de Dados
    * @param conn
    * @implemented by Dherkyan Ribeiro
    */
   public ProcessarRelatorioValorBeneficiarioAposAvaliacaoBe(Connection conn)
   {
      super(conn);
      giaItcdBe = new GIAITCDBe(conn);
   }

   /**
    * 
    * @implemented by Dherkyan Ribeiro
    */
   private void init()
   {
      arquivoExcel = new HSSFWorkbook();
      arquivoExcel.createSheet(NOME_ABA_PLANILHA);
      contadorLinhaPlanilha = 0;
   }

   /**
    * 
    * 
    * 
    * 
    * @param entidadeVo
    * @implemented by Dherkyan Ribeiro
    */
   public String processarRelatorio(EntidadeVo entidadeVo) throws SQLException, ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException, ConexaoException, IntegracaoException
   {

      PedidoRelatorioVo pedidosParaProcessamento = listarRelatorioNaoProcessado();

      ExibirLOG.exibirLog("Quantidade de relatorios para processar: " + pedidosParaProcessamento.getCollVO().size());

      for (PedidoRelatorioVo pedido: pedidosParaProcessamento.getListVo())
      {
         try
         {
            GIAITCDVo giaITCDVoCodigo = new GIAITCDVo();

            giaItcdBe.listarCodigoGiaRelatorioValorBeneficiarioAposAValiacao(giaITCDVoCodigo, pedido);

            criarRelatorio();

            for (GIAITCDVo giaParametro: giaITCDVoCodigo.getCollVO())
            {
               GIAITCDVo giaItcdVoConsulta = new GIAITCDVo(giaParametro);
               giaItcdVoConsulta.setCodigo(giaParametro.getCodigo());
               giaItcdVoConsulta.setConsultaCompleta(true);
               GIAITCDVo giaItcdVo = giaItcdBe.consultaGIAITCDBasico(giaItcdVoConsulta); //consultaGIAITCDBasico //consultarGIAITCD //solicitarConsultarGIAITCDAtiva

               if(StatusGIAITCDBe.isGiaPossuiStatus( giaItcdVo.getStatusVo() , DomnStatusGIAITCD.AVALIADO ))
               {
                  exportarDadosExcelGia(giaItcdVo);
                  ExibirLOG.exibirLog("...Exportar Gia para Excel...");
               }
                             
            }
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
      }

      return "";
   }

   /**
    * 
    * @implemented by Dherkyan Ribeiro
    */
   private void gravarCabecalhoNaPlanilha()
   {
      Object[] cabecalho =
      { "Número da GIA", "Data Criação", "Tipo da GIA-ITCD", "Status da GIA-ITCD", "Data Óbito", "CPF Beneficiário", 
         "Nome Beneficiário", "Valor Beneficiário Recebido Declaração", "Valor Beneficiário(Após Avaliação)", "CPF Cônjuge1", 
         "Nome Cônjuge1", "CPF Cônjuge2", "Nome Cônjuge2", "Base de Cálculo Cônjuge Separação", "Alíquota" };

      gravarDadosPlanilha(cabecalho);
   }

   /**
    * 
    * @implemented by Dherkyan Ribeiro
    */
   private void criarRelatorio()
   {
      init();
      gravarCabecalhoNaPlanilha();
   }

   /**
    * 
    * @param dado
    * @implemented by Dherkyan Ribeiro
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
    * @implemented by Dherkyan Ribeiro
    */
   private void exportarDadosExcelGia(final GIAITCDVo giaITCDVo)
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
    * @implemented by Dherkyan Ribeiro
    */
   private void exportarDadosExcelGiaDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo)
   {
      for (BeneficiarioVo beneficiario: giaITCDDoacaoVo.getBeneficiarioVo().getListVo())
      {
         Object[] dadosGia = new Object[QUANTIDADE_COLUNAS];
         GIAITCDDoacaoBeneficiarioVo beneficiarioDoacao = (GIAITCDDoacaoBeneficiarioVo) beneficiario;
         dadosGia[NUMERO_GIA] = giaITCDDoacaoVo.getCodigo();
         dadosGia[DATA_CRIACAO] = giaITCDDoacaoVo.getDataCriacaoFormatada();
         dadosGia[TIPO_GIA] = giaITCDDoacaoVo.getNaturezaOperacaoVo().getTipoProcesso().getTextoCorrente();
         dadosGia[STATUS_ITCD] = giaITCDDoacaoVo.getStatusVo().getStatusGIAITCD().getTextoCorrente();        
         dadosGia[CPF_BENEFICIARIO] = beneficiarioDoacao.getPessoaBeneficiaria().getNumrDocumento();
         dadosGia[NOME_BENEFICIARIO] = beneficiarioDoacao.getPessoaBeneficiaria().getNomeContribuinte();
         dadosGia[VALOR_BENEFICIARIO_RECEBIDO_DECLARACAO] = beneficiarioDoacao.getValorRecebidoAuxiliarFormatado();
         dadosGia[VALOR_BENEFICIARIO_RECEBIDO_APOS_AVALIACAO] = beneficiarioDoacao.getValorRecebidoFormatado();
         dadosGia[ALIQUOTA] = beneficiarioDoacao.getPercentualAliquotaFormatado();

         gravarDadosPlanilha(dadosGia);
      }
   }

   private void exportarDadosExcelGiaInventario(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo)
   {
      for (BeneficiarioVo beneficiario: giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getListVo())
      {
         Object[] dadosGia = new Object[QUANTIDADE_COLUNAS];
         GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioTemp = 
            (GIAITCDInventarioArrolamentoBeneficiarioVo) beneficiario;
         dadosGia[NUMERO_GIA] = giaITCDInventarioArrolamentoVo.getCodigo();
         dadosGia[STATUS_ITCD] = giaITCDInventarioArrolamentoVo.getStatusVo().getStatusGIAITCD().getTextoCorrente();
         dadosGia[DATA_CRIACAO] = giaITCDInventarioArrolamentoVo.getDataCriacaoFormatada();
         dadosGia[TIPO_GIA] = giaITCDInventarioArrolamentoVo.getNaturezaOperacaoVo().getTipoProcesso().getTextoCorrente();
         dadosGia[CPF_BENEFICIARIO] = beneficiarioTemp.getPessoaBeneficiaria().getNumrDocumento();
         dadosGia[NOME_BENEFICIARIO] = beneficiarioTemp.getPessoaBeneficiaria().getNomeContribuinte();
         dadosGia[DATA_OBITO] = giaITCDInventarioArrolamentoVo.getDataFalecimentoFormatada();
         dadosGia[VALOR_BENEFICIARIO_RECEBIDO_DECLARACAO] = beneficiarioTemp.getValorRecebidoAuxiliarFormatado();
         dadosGia[VALOR_BENEFICIARIO_RECEBIDO_APOS_AVALIACAO] = beneficiarioTemp.getValorRecebidoFormatado();
         GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquotas = beneficiarioTemp.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo();
         for(GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquota :aliquotas.getListVo() )
         {
           dadosGia[ALIQUOTA] = aliquota.getPercentualAliquotaFormatado();
         }
         gravarDadosPlanilha(dadosGia);
      }
   }

   private void exportarDadosExcelGiaSeparacao(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo)
   {
      Object[] dadosGia = new Object[QUANTIDADE_COLUNAS];
      dadosGia[NUMERO_GIA] = giaITCDSeparacaoDivorcioVo.getCodigo();
      dadosGia[STATUS_ITCD] = giaITCDSeparacaoDivorcioVo.getStatusVo().getStatusGIAITCD().getTextoCorrente();
      dadosGia[DATA_CRIACAO] = giaITCDSeparacaoDivorcioVo.getDataCriacaoFormatada();
      dadosGia[TIPO_GIA] = giaITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().getTipoProcesso().getTextoCorrente();
      dadosGia[CPF_CONJUGE_1] = giaITCDSeparacaoDivorcioVo.getConjuge1().getPessoaConjuge().getNumrDocumento();
      dadosGia[NOME_CONJUGE_1] = giaITCDSeparacaoDivorcioVo.getConjuge1().getPessoaConjuge().getNomeContribuinte();
      dadosGia[CPF_CONJUGE_2] = giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNumrDocumento();
      dadosGia[NOME_CONJUGE_2] = giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNomeContribuinte();
      dadosGia[BASE_CALCULO_CONCJUGE_SEPARACAO] = giaITCDSeparacaoDivorcioVo.getValorBaseCalculoTributavelFormatado();
      dadosGia[ALIQUOTA] = giaITCDSeparacaoDivorcioVo.getValorAliquotaFormatado();

      gravarDadosPlanilha(dadosGia);
   }

   /**
    * Lista os pedidos de relatório que são do tipo DomnTipoRelatorio.VALOR_POR_BENEFICIARIO_APOS_AVALIACAO
    * e ainda não foram processados
    * 
    * @implemented by Dherkyan Ribeiro
    */
   private PedidoRelatorioVo listarRelatorioNaoProcessado() throws SQLException, ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException
   {

      PedidoRelatorioVo parametro = new PedidoRelatorioVo();
      parametro.setTipoRelatorio(new DomnTipoRelatorio(DomnTipoRelatorio.VALOR_POR_BENEFICIARIO_APOS_AVALIACAO));
      parametro.setSituacaoProcessamento(new DomnSituacaoProcessamento(DomnSituacaoProcessamento.NAO_PROCESSADO));

      PedidoRelatorioVo pedidoRelatorioVo = new PedidoRelatorioVo(parametro);

      PedidoRelatorioBe pedidoRelatorioBe = new PedidoRelatorioBe(conn);
      pedidoRelatorioBe.listarPedidoRelatorioCompleto(pedidoRelatorioVo);
      return pedidoRelatorioVo;
   }

   /**
    * 
    * @implemented by Dherkyan Ribeiro
    */
   private void gerarArquivoExcel(PedidoRelatorioVo pedidoRelatorio) throws FileNotFoundException, IOException, SQLException, 
         RegistroExistenteException, ObjetoObrigatorioException, ParametroObrigatorioException, ConsultaException, 
         ConexaoException, LogException, AnotacaoException, PersistenciaException
   {
         // Criando arquivo excel temporário no diretório de serviços
         String nomeRelatorio = NOME_RELATORIO + pedidoRelatorio.getCodigo() + XLS;
         File arquivo = new File(CAMINHO_DIRETORIO_TEMPORARIO + nomeRelatorio);

         FileOutputStream out =  new FileOutputStream(arquivo);
         arquivoExcel.write(out);
         out.close();

         // Zipando arquivo criado
         FileInputStream fis = new FileInputStream(CAMINHO_DIRETORIO_TEMPORARIO + nomeRelatorio);
         FileOutputStream fos = new FileOutputStream(CAMINHO_DIRETORIO_TEMPORARIO + NOME_RELATORIO + pedidoRelatorio.getCodigo()+ ZIP );
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
         File arquivoZip = new File(CAMINHO_DIRETORIO_TEMPORARIO + NOME_RELATORIO + pedidoRelatorio.getCodigo() + ZIP);

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
   private void atualizarPedidoRelatorio(PedidoRelatorioVo pedidoRelatorio) throws SQLException, RegistroExistenteException, 
         ObjetoObrigatorioException, ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, 
         AnotacaoException, PersistenciaException
   {

      PedidoRelatorioBe pedidoRelatorioBe = new PedidoRelatorioBe(conn);

      pedidoRelatorio.setDataProcessamento(new Date());

      pedidoRelatorioBe.alterarPedidoRelatorio(pedidoRelatorio);

      conn.commit();
   }

}
