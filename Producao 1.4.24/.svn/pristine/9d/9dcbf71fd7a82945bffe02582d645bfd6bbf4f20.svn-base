package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu.ImportacaoIPTUBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu.ImportacaoIPTUVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptuprefeitura.IPTUPrefeituraBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptuprefeitura.IPTUPrefeituraVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnAbaExcelPrefeituraIPTU;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnGeracaoServico;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author Douglas Souza
 */
public class ImportarIPTUPrefeituraBe extends AbstractBe
{

	private static final String caminhoArquivoLeitura = "/usr/appl/servicos/itc/arquivo/entrada/";
   private static final String XLSX = ".xlsx";
   private static final String XLS = ".xls";
   private String diretorioTemp = "java.io.tmpdir";  
   private ImportacaoIPTUVo importacaoIPTUVo = null;
   private ImportacaoIPTUBe importacaoIPTUBe = null;
   private IPTUPrefeituraVo iptuPrefeituraVo = null;
   private IPTUPrefeituraBe iptuPrefeituraBe = null;
   private IPTUPrefeituraVo iptuPrefeituraConcultaVo = null;
   
	public ImportarIPTUPrefeituraBe(Connection conn)
	{
		super(conn);
	}
   /**
    * 
    * @param entidadeVo
    * @throws Exception
    */
   public void processarIPTUPrefeitura(EntidadeVo entidadeVo)
      throws Exception
   {
      importacaoIPTUVo = new ImportacaoIPTUVo();
      importacaoIPTUVo.setStatusImportacao(new DomnGeracaoServico(DomnGeracaoServico.NAO_PROCESSADO));
      importacaoIPTUVo.setParametroConsulta(importacaoIPTUVo);

      importacaoIPTUBe = new ImportacaoIPTUBe(conn);
      importacaoIPTUBe.listImportacao(importacaoIPTUVo);

      Iterator ite = importacaoIPTUVo.getCollVO().iterator();
      while (ite.hasNext())
      {
         ImportacaoIPTUVo importacaoIPTUVoAtual = (ImportacaoIPTUVo) ite.next();
         try
         {
            //Mundando o Status para "Em_processamento";
            importacaoIPTUVoAtual.setStatusImportacao(new DomnGeracaoServico(DomnGeracaoServico.EM_PROCESSAMENTO));
            importacaoIPTUVoAtual.setLogSefazVo(entidadeVo.getLogSefazVo());
           
            importacaoIPTUBe.alterarArquivoIPTU(importacaoIPTUVoAtual);
            conn.commit();
            ExibirLOG.exibirLog("Mudando o status para em processamento");

            criarArquivoeExcelDiretorio(importacaoIPTUVoAtual);
            ExibirLOG.exibirLog("o arquivo foi guardado no diretorio");
            iptuPrefeituraVo = new IPTUPrefeituraVo();
            iptuPrefeituraVo.setImportacaoIPTUVo(importacaoIPTUVo);
 
            if (verificarExtensao(importacaoIPTUVoAtual.getArquivoIPTU()).equals(XLSX))
            {
               iptuPrefeituraVo = LerArquivoExcel(importacaoIPTUVoAtual, XLSX);
            }
            
            if (verificarExtensao(importacaoIPTUVoAtual.getArquivoIPTU()).equals(XLS))
            {
               ExibirLOG.exibirLog("Inicio da leitura do arquivo excel");
               iptuPrefeituraVo = LerArquivoExcel(importacaoIPTUVoAtual, XLS);
               ExibirLOG.exibirLog("Fim da leitura do arquivo excel");
            }
            iptuPrefeituraVo.setImportacaoIPTUVo(importacaoIPTUVoAtual);
            iptuPrefeituraVo.setLogSefazVo(entidadeVo.getLogSefazVo());
            
            persistirIptuPrefeitura(iptuPrefeituraVo);
            
         }
         

         catch (Exception erro)
         {
            importacaoIPTUVoAtual.setStatusImportacao(new DomnGeracaoServico(DomnGeracaoServico.PROCESSAMENTO_COM_ERRO));
            importacaoIPTUBe.alterarArquivoIPTU(importacaoIPTUVoAtual);
            conn.commit();
            throw erro;
         }
      }
      ExibirLOG.exibirLog("Fim do arquivo processado");

   }
  /**
    * 
    * @param importacaoIPTUVo
    * @return
    * @throws FileNotFoundException
    * @throws IOException
    * @throws Exception
    * @Função Metodo para ler Arquivo XLSX e XLS
    */
   public IPTUPrefeituraVo LerArquivoExcel(ImportacaoIPTUVo importacaoIPTUVo, String formatoArquivo)
      throws FileNotFoundException, IOException, Exception
   {
      Validador.isObjetoValido(importacaoIPTUVo);
      IPTUPrefeituraVo iptuPrefeituraVoRetorno = new IPTUPrefeituraVo();
      ArrayList listaContribuinteIptu = new ArrayList();
      Iterator<Row> linhas = null;
      POIFSFileSystem fs = null;
      
      try
      {        
         //FileInputStream fileInputStream = new FileInputStream(new File(importacaoIPTUVo.getArquivoIPTU().getPath()));
          ExibirLOG.exibirLog("Diretorio do arquivo"+importacaoIPTUVo.getArquivoIPTU().getPath());
          fs = new POIFSFileSystem(new FileInputStream(importacaoIPTUVo.getArquivoIPTU().getPath()));

         if (formatoArquivo.equals(XLSX))
         {
            // Carregando workbook
            //XSSFWorkbook planilha = new XSSFWorkbook(fs);
            // Seleciona a primeira ABA
            //XSSFSheet aba = planilha.getSheetAt(0);
            // Enquanto tiver linhas para ler executar o codigo
            //linhas = aba.iterator();
         }
         if (formatoArquivo.equals(XLS))
         {
            HSSFWorkbook planilha = new HSSFWorkbook(fs);
            // Seleciona a primeira ABA
            HSSFSheet aba = planilha.getSheetAt(0);
            // Enquanto tiver linhas para ler executar o codigo
            linhas = aba.iterator();
         }

         while (linhas.hasNext())
         {
            iptuPrefeituraVo = new IPTUPrefeituraVo();
            // Carregar a linha na variavel
            Row linha = linhas.next();
            // Se for a linha 1 ou mais adicionar valor
            if (linha.getRowNum() >= 1)
            {
               validadorCelulaXLS(linha, DomnAbaExcelPrefeituraIPTU.INSCRICAO_IMOBILIARIA);
               if (Validador.isStringValida(linha.getCell(DomnAbaExcelPrefeituraIPTU.INSCRICAO_IMOBILIARIA).getStringCellValue()))
               {
                  iptuPrefeituraVo.setNumrInscricaoImovel(linha.getCell(DomnAbaExcelPrefeituraIPTU.INSCRICAO_IMOBILIARIA).getStringCellValue());
               }
               validadorCelulaXLS(linha, DomnAbaExcelPrefeituraIPTU.STATUS);             
               
               if (Validador.isStringValida(linha.getCell(DomnAbaExcelPrefeituraIPTU.STATUS).getStringCellValue()))
               {
                  iptuPrefeituraVo.setStatImovel(linha.getCell(DomnAbaExcelPrefeituraIPTU.STATUS).getStringCellValue());
               }
               validadorCelulaXLS(linha, DomnAbaExcelPrefeituraIPTU.VALOR_VENAL);
               if (Validador.isNumericoValido(linha.getCell(DomnAbaExcelPrefeituraIPTU.VALOR_VENAL).getNumericCellValue()))
               {
                  iptuPrefeituraVo.setValrVenal(linha.getCell(DomnAbaExcelPrefeituraIPTU.VALOR_VENAL).getNumericCellValue());
               }
               validadorCelulaXLS(linha, DomnAbaExcelPrefeituraIPTU.AREA_TOTAL);
               if (Validador.isNumericoValido(linha.getCell(DomnAbaExcelPrefeituraIPTU.AREA_TOTAL).getNumericCellValue()))
               {
                  iptuPrefeituraVo.setQtdeAreaTotal(linha.getCell(DomnAbaExcelPrefeituraIPTU.AREA_TOTAL).getNumericCellValue());
               }
               validadorCelulaXLS(linha, DomnAbaExcelPrefeituraIPTU.AREA_CONSTRUIDA);
               if (Validador.isNumericoValido(linha.getCell(DomnAbaExcelPrefeituraIPTU.AREA_CONSTRUIDA).getNumericCellValue()))
               {
                  iptuPrefeituraVo.setQtdeAreaConstruida(linha.getCell(DomnAbaExcelPrefeituraIPTU.AREA_CONSTRUIDA).getNumericCellValue());
               }
               validadorCelulaXLS(linha, DomnAbaExcelPrefeituraIPTU.CONTRIBUINTE);
               if (Validador.isStringValida(linha.getCell(DomnAbaExcelPrefeituraIPTU.CONTRIBUINTE).getStringCellValue()))
               {
                  iptuPrefeituraVo.setNomeContribuinte(linha.getCell(DomnAbaExcelPrefeituraIPTU.CONTRIBUINTE).getStringCellValue());
               }
               validadorCelulaXLS(linha, DomnAbaExcelPrefeituraIPTU.CNPJ_CPF);
               if (Validador.isStringValida(linha.getCell(DomnAbaExcelPrefeituraIPTU.CNPJ_CPF).getStringCellValue()))
               {
                  iptuPrefeituraVo.setNumrDocumento(Long.parseLong(linha.getCell(DomnAbaExcelPrefeituraIPTU.CNPJ_CPF).getStringCellValue()));
               }
               System.gc();
              
               listaContribuinteIptu.add(iptuPrefeituraVo);
            }
         }
         iptuPrefeituraVoRetorno.setCollVO(listaContribuinteIptu);
      }
      catch (Exception erro)
      {                 
         throw erro;
      }
      return iptuPrefeituraVoRetorno;
   }
 
   /**
    * 
    * @param iptuPrefeituraVo
    * @throws Exception
    */
   public void persistirIptuPrefeitura(IPTUPrefeituraVo iptuPrefeituraVo)
      throws Exception
   {
      try
      {
         Validador.isObjetoValido(iptuPrefeituraVo);
         iptuPrefeituraBe = new IPTUPrefeituraBe(conn);
         if (Validador.isCollectionValida(iptuPrefeituraVo.getCollVO()))
         {
            Iterator ite = iptuPrefeituraVo.getCollVO().iterator();
            while (ite.hasNext())
            {
               IPTUPrefeituraVo iptuPrefeituraVoAtual = (IPTUPrefeituraVo) ite.next();
               iptuPrefeituraConcultaVo = new IPTUPrefeituraVo();
               iptuPrefeituraConcultaVo.setImportacaoIPTUVo(iptuPrefeituraVo.getImportacaoIPTUVo());
               iptuPrefeituraConcultaVo.setNumrInscricaoImovel(iptuPrefeituraVoAtual.getNumrInscricaoImovel());               
               iptuPrefeituraConcultaVo.setStatIPTUprefeitura(new DomnAtivoInativo(DomnAtivoInativo.ATIVO));              
               iptuPrefeituraConcultaVo.setParametroConsulta(iptuPrefeituraConcultaVo);
               
               //Metodo para Buscar registro na Tabela 57 caso tenha irá Inativar o Registro
               iptuPrefeituraBe.consultarIPTUPrefeitura(iptuPrefeituraConcultaVo);
               if (Validador.isNumericoValido(iptuPrefeituraConcultaVo.getCodigo()))
               {
                  iptuPrefeituraConcultaVo.setStatIPTUprefeitura(new DomnAtivoInativo(DomnAtivoInativo.INATIVO));
                  iptuPrefeituraConcultaVo.setLogSefazVo(iptuPrefeituraVo.getImportacaoIPTUVo().getLogSefazVo());
                  iptuPrefeituraBe.alterarPrefeituraIPTU(iptuPrefeituraConcultaVo);
               }
               //Preparando Dados Para Inserir na Tabela ITCTB57_IPTU_PREFEITURA
               iptuPrefeituraVoAtual.setImportacaoIPTUVo(iptuPrefeituraVo.getImportacaoIPTUVo());
               iptuPrefeituraVoAtual.setMunicipioVo(iptuPrefeituraVo.getImportacaoIPTUVo().getMunicipioVo());
               iptuPrefeituraVoAtual.setLogSefazVo(iptuPrefeituraVo.getLogSefazVo());
               iptuPrefeituraVoAtual.setDataAtualizacaoBd(new Date());
               iptuPrefeituraVoAtual.setStatIPTUprefeitura(new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
               
               //Metodo Para Inserir na Tabela ITCTB57_IPTU_PREFEITURA
               iptuPrefeituraBe.incluirPrefeituraIPTU(iptuPrefeituraVoAtual);
            }            
         
            //Preparando Dados Para Inserir na Tabela ITCTB56_IMPORTACAO_IPTU
            iptuPrefeituraVo.getImportacaoIPTUVo().setStatusImportacao(new DomnGeracaoServico(DomnGeracaoServico.PROCESSADO));
            iptuPrefeituraVo.getImportacaoIPTUVo().setDataHoraProcessamento(new Date());
            
            //Alterando Registro de Status da Tabela ITCTB56_IMPORTACAO_IPTU para Processado
            importacaoIPTUBe.alterarArquivoIPTU(iptuPrefeituraVo.getImportacaoIPTUVo());           
            conn.commit();
            iptuPrefeituraVo.getImportacaoIPTUVo().getArquivoIPTU().delete();
         }
      }
      catch (Exception erro)
      {
         conn.rollback();
         throw erro;
      }
   }
   /**
    * 
    * @param importacaoIPTUVo
    * @return
    * @throws Exception
    */
   public ImportacaoIPTUVo criarArquivoeExcelDiretorio(ImportacaoIPTUVo importacaoIPTUVo)
      throws Exception
   {
     try
     {
         String nomeDiretorioTemp = "/usr/appl/servicos/itc/ArquivosTemporarios/";
         File sourceFile = new File(nomeDiretorioTemp + importacaoIPTUVo.getDescNomeArquivoNovo());
         FileOutputStream file = new FileOutputStream(sourceFile);
         BufferedOutputStream output = new BufferedOutputStream(file);
         output.flush();
         output.write(importacaoIPTUVo.getArqvIptu(), 0,importacaoIPTUVo.getArqvIptu().length);
         output.flush();
         output.close();
         importacaoIPTUVo.setArquivoIPTU(sourceFile);
         ExibirLOG.exibirLog("o nome do arquivo + diretorio"+sourceFile.getAbsoluteFile());
         ExibirLOG.exibirLog("o nome do arquivo + diretorio"+sourceFile.getAbsolutePath());
         ExibirLOG.exibirLog("o nome do arquivo + diretorio"+sourceFile.getName());
      }
     catch(Exception erro)
     {
        throw erro;
     }
     return importacaoIPTUVo;
   }
   public static XSSFCell validadorCelulaXLSX(Row celula, int linha )
   {
      XSSFCell celulaVerificada;      
      celulaVerificada = (XSSFCell) celula.getCell(linha, Row.CREATE_NULL_AS_BLANK);      
      return celulaVerificada;
   }   
  
   public static String verificarExtensao(File file) {  
       if (file instanceof File) {  
           if (file.getName().length() > 1 && file.getName().contains(".")) {  
               return file.getName().substring(file.getName().lastIndexOf("."));  
           }  
       }  
       return null;  
   }  
   public static HSSFCell validadorCelulaXLS(Row celula, int linha )
   {
      HSSFCell celulaVerificada;      
      celulaVerificada = (HSSFCell) celula.getCell(linha, Row.CREATE_NULL_AS_BLANK);      
      return celulaVerificada;
   }

}
