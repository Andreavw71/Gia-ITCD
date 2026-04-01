package br.gov.mt.sefaz.itc.util.facade.pdf;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.pdf.AbstractGeradorPDF;

import br.com.abaco.util.exceptions.PDFBadElementException;
import br.com.abaco.util.exceptions.PDFDocumentException;
import br.com.abaco.util.exceptions.PDFFileNotFoundException;
import br.com.abaco.util.exceptions.PDFIOException;
import br.com.abaco.util.exceptions.PDFMalformedURLException;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;

import java.io.ByteArrayOutputStream;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.net.MalformedURLException;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import sefaz.mt.cadastro.dominio.DomnTipoDocumento;


/**
 * @author Ricardo Vitor de Oliveira Moraes
 */
public abstract class AbstractGeradorPDFITCD extends AbstractGeradorPDF implements ConfiguracaoPDF, FontePDFITCD
{

   private EntidadeVo entidadeVo;
   private PdfWriter pdfWriter = null;

	protected AbstractGeradorPDFITCD(HttpServletRequest request, EntidadeVo entidadeVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(request, entidadeVo, formatoPagina);
	   this.entidadeVo = entidadeVo;
	}
   
   protected PdfWriter getWriter(){
     return this.pdfWriter;
   }

	protected AbstractGeradorPDFITCD(EntidadeVo entidadeVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(entidadeVo, formatoPagina);
	}
   
   public final void adicionarCabecalhoCustomizado(Document document) throws ObjetoObrigatorioException, BadElementException, 
              MalformedURLException, FileNotFoundException, IOException, DocumentException
      {
         Table table = gerarCabecalho();
         
         float[] colunas = { 20,20,20,40 }; 
         
         Table tabela = new Table(colunas.length);
         tabela.setWidth(100);
         tabela.setWidths(colunas);
         tabela.setPadding(3F);
         
         Cell celula1 = new Cell(new Phrase("Nº GIA - ITCD".toUpperCase(), fontPequenaBold));
         celula1.setHorizontalAlignment(Element.ALIGN_LEFT);
         celula1.setVerticalAlignment(Element.ALIGN_TOP);
         celula1.setColspan(1);
         celula1.setBorder(2);
         tabela.addCell(celula1);
              
         Cell celula2 = new Cell(new Phrase(Long.toString(((GIAITCDVo) this.entidadeVo).getCodigo()), fontPequenaBold));
         celula2.setHorizontalAlignment(Cell.ALIGN_LEFT);
         celula2.setVerticalAlignment(Cell.ALIGN_TOP);
         celula2.setColspan(1);
         tabela.addCell(celula2);   
              
         Cell celula3 = new Cell(new Phrase("Tipo de Processo", fontPequenaBold));
         celula3.setHorizontalAlignment(Cell.ALIGN_LEFT);
         celula3.setVerticalAlignment(Cell.ALIGN_LEFT);
         celula3.setColspan(1);
         tabela.addCell(celula3);      
         Cell celula4 = new Cell(new Phrase(((GIAITCDVo) this.entidadeVo).getNaturezaOperacaoVo().getTipoProcesso().getTextoCorrente(), fontPequenaBold));
         celula4.setHorizontalAlignment(Cell.ALIGN_LEFT);
         celula4.setVerticalAlignment(Cell.ALIGN_TOP);
         celula4.setColspan(1);
         tabela.addCell(celula4); 
              
         Cell cellVazia = new Cell(""); // Cria uma célula vazia
         cellVazia.setBorder(Cell.NO_BORDER); // Remove a borda da célula
         
         table.addCell(cellVazia);
         // Define o cabeçalho no documento
         Phrase phrase = new Phrase();
         phrase.setLeading(0);
         phrase.add(table);
         phrase.add(tabela);
               
         HeaderFooter header = new HeaderFooter(phrase, false);
         header.setBorder(HeaderFooter.NO_BORDER);
         document.setHeader(header);
      }
      
      public void adicionarRodapeCustomizado(Document document) throws ObjetoObrigatorioException, BadElementException, 
                 DocumentException, MalformedURLException, IOException
         {
            Validador.validaObjeto(document);
            HeaderFooter rodape = new HeaderFooter(new Phrase("", fontPequena), false);
            rodape.setAlignment(HeaderFooter.ALIGN_RIGHT);
            rodape.setBorder(HeaderFooter.TOP);
            document.setFooter(rodape);
         }
      
      public final ByteArrayOutputStream gerarRelatorioCustomizado() throws PDFFileNotFoundException, PDFIOException, PDFDocumentException, PDFBadElementException, 
                 PDFMalformedURLException, ObjetoObrigatorioException, MalformedURLException, 
                FileNotFoundException, IOException
      {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document(this.getFormatoPagina());
            document.setMargins(getMargemDireita(), getMargemEsquerda(), getMargemSuperior(), getMargemInferior() );
            System.out.println("Vers?o do iText: " + Document.getVersion());
            try
            {
               
               this.pdfWriter = PdfWriter.getInstance(document, baos);
//               if(((GIAITCDVo) this.entidadeVo).getStatusVo().getStatusGIAITCD().equals(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO)){
//                  this.configurarPdfWriter(this.pdfWriter);
//               }
               this.adicionarCabecalhoCustomizado(document);
               this.adicionarRodapeCustomizado(document);               
               document.open();
               this.gerarCorpoPDF(document);
               document.close();
               
               PdfReader reader = new PdfReader(baos.toByteArray());
               ByteArrayOutputStream baosFinal = new ByteArrayOutputStream();
               PdfStamper stamper = new PdfStamper(reader, baosFinal);
               int totalPages = reader.getNumberOfPages();
               Font fonteMenor = FontFactory.getFont(FontFactory.HELVETICA,8);                           
               for (int i = 1; i <= totalPages; i++) {
                  PdfContentByte over = stamper.getOverContent(i);
                  ColumnText.showTextAligned(over, Element.ALIGN_RIGHT, 
                  new Phrase("Página " + i + " de " + totalPages,fonteMenor), 585, 10, 0); // Posição no rodapé inferior direito
               }
               stamper.close();
               return baosFinal;  
               
            }
            catch (MalformedURLException erro)
            {
               erro.printStackTrace();
               throw new PDFMalformedURLException();
            }
            catch (BadElementException erro)
            {
               erro.printStackTrace();
               throw new PDFBadElementException();
            }
            catch (DocumentException erro)
            {
               erro.printStackTrace();
               throw new PDFDocumentException();
            }
            catch(FileNotFoundException erro)
            {
               erro.printStackTrace();
               throw new PDFFileNotFoundException();
            }
            catch (IOException erro)
            {
               erro.printStackTrace();
               throw new PDFIOException();
            }           
         }
         
//   protected void configurarPdfWriter(PdfWriter writer) throws DocumentException {           
//   }

	/**
	 * Metodo responsável por retornar o titulo do relatório.
	 * @return String[]
	 * @implemented by Lucas Nascimento
	 */
	public String[] getTitulosRelatorio()
	{
		return new String[] 
		{ 
			/*SECRETARIA_ADJUNTA_DA_RECEITA_PUBLICA, 
			SUPERINTENDENCITA_DE_INFORMACOES_SOBRE_OUTRAS_RECEITAS, 
			GERENCIA_DE_INFORMACOES_DE_OUTRAS_RECEITAS, 
			ConfiguracaoPDF.GIA_ITCD
         */
         /* Nome alterado O.S.:  	000038686/2015-30 */
		   SECRETARIA_ADJUNTA_DA_RECEITA_PUBLICA,
         ConfiguracaoITCD.NOME_SUPERINTENDENCIA,
         ConfiguracaoITCD.NOME_GERENCIA,
		   ConfiguracaoPDF.GIA_ITCD
		};
	}	

	/**
	 * Método criado para instanciar um objeto Table(Itext) e setar as configurações utilizadas para cada tabela deste pdf.
	 * @param numeroColuna
	 * @param larguraColuna
	 * @return Table
	 * @throws BadElementException
	 * @implemented by Maxwell Mendes Rocha
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @deprecated Devido criação método instanciarTabelaPadrao retornando PdfPTable
	 * *
	 */
	public Table instanciarTabela(int numeroColuna, float[] larguraColuna) throws BadElementException
	{
		Table tabela = new Table(numeroColuna);
		tabela.setOffset(9);
		tabela.setWidths(larguraColuna);
		tabela.setSpaceInsideCell(ESPACO_INTERNO_CELULA);
		tabela.setSpaceBetweenCells(ESPACO_ENTRE_CELULAS);
		tabela.setWidth(LARGURA_RELATORIO);
		tabela.setPadding(3);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
		return tabela;
	}  

	/**
	 * Método criado para instanciar um objeto Table(Itext) e setar as configurações utilizadas para cada tabela deste pdf.
	 * @param colunaTabelas
	 * @param larguraColuna
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public PdfPTable instanciarTabelaPadrao(int colunaTabelas, float[] larguraColuna) throws BadElementException, 
			  DocumentException
	{
		PdfPTable tabela = new PdfPTable(colunaTabelas);
		tabela.setWidths(larguraColuna);
		tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setWidthPercentage(100);
		tabela.setSpacingBefore(3);
		return tabela;
	}

	/**
	 * Método responsável por montar as celulas com os dados pessoais de um Contribuinte.
	 * @param contribuinteIntegracaoVo
	 * @param pessoa
	 * @return Collection
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public Collection obterDadosContribuinte(ContribuinteIntegracaoVo contribuinteIntegracaoVo, String pessoa)
	{
		Collection dadosContribuinte = new ArrayList();
	   //LINHA 1
	   PdfPCell celula = new PdfPCell(new Phrase((Validador.isStringValida(pessoa)?"Nome "+pessoa+": \n":"Nome: \n") +  contribuinteIntegracaoVo.getNomeContribuinte(), fontPequenaBold));
	   celula.setColspan(8);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   dadosContribuinte.add(celula);
		if(contribuinteIntegracaoVo.getTipoDocumento().is(DomnTipoDocumento.CPF))
		{
			celula = new PdfPCell(new Phrase((Validador.isStringValida(pessoa)?"CPF "+pessoa+": \n":"CPF: \n") +  StringUtil.formataCPF(contribuinteIntegracaoVo.getNumrDocumento()), fontPequenaBold));
		}
		else if (contribuinteIntegracaoVo.getTipoDocumento().is(DomnTipoDocumento.CNPJ))
		{
		   celula = new PdfPCell(new Phrase((Validador.isStringValida(pessoa)?"CNPJ "+pessoa+": \n":"CNPJ: \n") +  StringUtil.formataCNPJ(contribuinteIntegracaoVo.getNumrDocumento()), fontPequenaBold));
		}
		else
		{
		   celula = new PdfPCell(new Phrase((Validador.isStringValida(pessoa)?"CPF/CNPJ "+pessoa+": \n":"CPF/CNPJ: \n") +  contribuinteIntegracaoVo.getNumrDocumento(), fontPequenaBold));
		}
	   celula.setColspan(4);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   dadosContribuinte.add(celula);
	   celula = new PdfPCell(new Phrase("Logradouro: \n" + contribuinteIntegracaoVo.getEndereco(), fontPequenaBold));
	   celula.setColspan(8);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   dadosContribuinte.add(celula);
	   //LINHA 2
		celula = new PdfPCell(new Phrase("Número: \n" + contribuinteIntegracaoVo.getEnderecoNumero(), fontPequenaBold));
		celula.setColspan(2);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   dadosContribuinte.add(celula);
	   celula = new PdfPCell(new Phrase("Complemento: \n" + contribuinteIntegracaoVo.getEnderecoComplemento(), fontPequenaBold));
	   celula.setColspan(6);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   dadosContribuinte.add(celula);
	   celula = new PdfPCell(new Phrase("Ponto de Referência: \n" + contribuinteIntegracaoVo.getPontoReferencia(), fontPequenaBold));
	   celula.setColspan(6);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   dadosContribuinte.add(celula);
	   celula = new PdfPCell(new Phrase("Bairro: \n" + contribuinteIntegracaoVo.getEnderecoBairro(), fontPequenaBold));
	   celula.setColspan(4);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   dadosContribuinte.add(celula);
	   celula = new PdfPCell(new Phrase("CEP: \n" + contribuinteIntegracaoVo.getCEPFormatado(), fontPequenaBold));
	   celula.setColspan(2);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   dadosContribuinte.add(celula);
	   //LINHA 3
		celula = new PdfPCell(new Phrase("Município: \n" + contribuinteIntegracaoVo.getMunicipio(), fontPequenaBold));
		celula.setColspan(8);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   dadosContribuinte.add(celula);
	   celula = new PdfPCell(new Phrase("UF: \n" + contribuinteIntegracaoVo.getSiglaUF(), fontPequenaBold));
	   celula.setColspan(1);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   dadosContribuinte.add(celula);
	   celula = new PdfPCell(new Phrase("DDD: \n" + contribuinteIntegracaoVo.getNumrDddFormatado(), fontPequenaBold));
	   celula.setColspan(1);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   dadosContribuinte.add(celula);
	   celula = new PdfPCell(new Phrase("Telefone: \n" + contribuinteIntegracaoVo.getNumrTelefoneFormatado(), fontPequenaBold));
	   celula.setColspan(2);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   dadosContribuinte.add(celula);
	   celula = new PdfPCell(new Phrase("E-mail: \n" + contribuinteIntegracaoVo.getEmail(), fontPequenaBold));
	   celula.setColspan(8);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   dadosContribuinte.add(celula);
		
		return dadosContribuinte;
		
	}
	
	/**
	 * Método responsável por escrever a tabela de Declarante/Doação
	 * @implemented by Maxwell Mendes Rocha
	 * @implemented by Wendell Pereira de Farias
	 * **/
	public PdfPTable tabelaPessoa(String titulo, String pessoa, ContribuinteIntegracaoVo contribuinteIntegracaoVo) throws BadElementException, DocumentException
	{
		float[] colunas = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
		tabela.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		tabela.setTotalWidth(LARGURA_RELATORIO);
		PdfPCell celula = new PdfPCell(new Phrase(titulo.toUpperCase(), fontMediaBold));
		celula.setColspan(colunas.length);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);
		tabela.setHeaderRows(1);
		for(Iterator it = obterDadosContribuinte(contribuinteIntegracaoVo, pessoa).iterator(); it.hasNext(); )
		{
			PdfPCell celulaAtual = (PdfPCell) it.next();
			tabela.addCell(celulaAtual);
		}
	   return tabela;		
	}

	/**
	 * Método responsável por escrever a tabela de Assinatura do Inventario Arrolamento
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public PdfPTable tabelaAssinaturaDeclarante() throws BadElementException, DocumentException
	{
		float[] colunas = { 60, 40 };
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);     
		PdfPCell celula = new PdfPCell(new Phrase("Local e Data \n\n _________________________________________, ___/___/______", fontPequenaBold));
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Assinatura Declarante \n\n _________________________________________________\n\n Assinatura Servidor \n\n _________________________________________________\n  ", fontPequenaBold));
		celula.setColspan(colunas.length);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);
		return tabela;
	}

	/**
	 * Método responsável por escrever a tabela de Autencidade
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public PdfPTable tabelaAutenticidade(GIAITCDVo giaITCDVo) throws BadElementException, DocumentException
	{
		float[] colunas = { 100 };
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
		PdfPCell celula = new PdfPCell(new Phrase("\n A autenticidade desta GIA-ITCD deverá ser confirmada via Internet: www.sefaz.mt.gov.br \n ", fontMediaBold));
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setBackgroundColor(Color.LIGHT_GRAY);          
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Código de Autenticidade: " + giaITCDVo.getCodigoAutenticidade(), fontPequenaBold));
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);	
      if(giaITCDVo.getTipoProtocoloGIA().is(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO))
      {
         celula = new PdfPCell(new Phrase("Dados para protocolar: " + retornaValorItem(ConfiguracaoITCD.PARAMETRO_INFORMACOES_PROTOCOLO_AUTOMATICO_GIA_ITCD), fontPequenaBold));
      }else if(giaITCDVo.getTipoProtocoloGIA().is(DomnTipoProtocolo.PROTOCOLO_MANUAL))
      {
         celula = new PdfPCell(new Phrase("Dados para protocolar: " + retornaValorItem(ConfiguracaoITCD.PARAMETRO_INFORMACOES_PROTOCOLO_GIA_ITCD), fontPequenaBold));
      }else
      {
         celula = new PdfPCell(new Phrase("Dados para protocolar: ", fontPequenaBold));
      }
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);
		return tabela;
	}	

	/**
	 * Método responsável por escrever a tabela de Autencidade
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public PdfPTable tabelaAutenticidadeRetificacaoNotificacao(GIAITCDVo giaITCDVo) throws BadElementException, DocumentException
	{
		float[] colunas = { 100 };
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
		PdfPCell celula = new PdfPCell(new Phrase("\n A autenticidade desta GIA-ITCD deverá ser confirmada via Internet: www.sefaz.mt.gov.br \n ", fontMediaBold));
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setBackgroundColor(Color.LIGHT_GRAY);          
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Código de Autenticidade: " + giaITCDVo.getCodigoAutenticidade(), fontPequenaBold));
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);
		return tabela;
	}  	

	/**
	 * Método responsável por escrever a tabela de Prazo para Declaração de Doação
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public PdfPTable tabelaObservacao(GIAITCDVo giaITCDVo, boolean exibeDataProtocolar, String tipoGIA) throws BadElementException, DocumentException
	{
		float[] colunas = { 100 };
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);  
		PdfPCell celula = new PdfPCell(new Phrase("Observações".toUpperCase(), fontMediaBold));
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setColspan(colunas.length);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);
		tabela.setHeaderRows(1);
		if(exibeDataProtocolar)
		{
		   String dataProtocolar = "";
		   if (Validador.isDataValida(giaITCDVo.getTemporarioVo().getPrazoProtocolar()))
		   {
		      dataProtocolar = "\n" + giaITCDVo.getTemporarioVo().getPrazoProtocolarFormatado();
		   }
		   celula = new PdfPCell(new Phrase("Prazo para protocolar a GIA-ITCD - "+ tipoGIA + ": " + dataProtocolar + "\n\n", fontMediaBold));
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setPadding(DEFAULT_PADDING);
		   tabela.addCell(celula);			
		}
	   switch((int)giaITCDVo.getStatusVo().getStatusGIAITCD().getValorCorrente()){
         case 11:
         case 18:
         case 27:
         case 30:
            celula = new PdfPCell(new Phrase("Situação da GIA ITCD: QUITADO", fontMediaBold));
            celula.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setPadding(DEFAULT_PADDING);
            tabela.addCell(celula);
         break;
      } 
      
      if(giaITCDVo instanceof GIAITCDDoacaoVo){      
         GIAITCDDoacaoVo giaDoacao = (GIAITCDDoacaoVo) giaITCDVo;
            if(giaDoacao.getNaturezaOperacaoVo().getDescricaoNaturezaOperacao().equals("DOAÇÃO COM RESERVA DE USUFRUTO") && giaDoacao.getBaseCalculoReduzida() == 100.0){      
               celula = new PdfPCell(new Phrase("O Contribuinte optou pelo recolhimento do ITCD nos termos do inciso art. 28, § 3º, inciso III, do Decreto nº 2.125/2003, com encerramento da tributação, não sendo devido , por ocasião da renúncia ou da extinção, o imposto de que trata o inciso I, § 2º, do art. 11 do Decreto nº 2.125/2003.", fontMediaBold));
               celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setPadding(DEFAULT_PADDING);    
               tabela.addCell(celula);         
            }
            
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2025);
            cal.set(Calendar.MONTH, Calendar.DECEMBER); 
            cal.set(Calendar.DAY_OF_MONTH, 31);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 0);
            Date dataLimite = cal.getTime();
            
            if(giaDoacao.getFlagInfoDoacaoSucessiva() == 1 && giaDoacao.getIsPresenteDispensaRecolhimento() && giaDoacao.getDataCriacao().after(dataLimite)){
               celula = new PdfPCell(new Phrase("Obs.:  Caso tenham ocorrido doações anteriores, realizadas no presente exercício, pelo doador ao donatário, cumpre ressaltar que:\n" + 
               "1) os valores acima especificados referem-se exclusivamente à doação ora informada, sendo que os cálculos relativos à apuração dos valores de ITCD devidos nas doações anteriores constam expressos nas correspondentes GIAS;\n" + 
               "2) nos termos do artigo 19, inciso II, da Lei 7.850/2002, combinado com o inciso II do § 2º do artigo 7º do Decreto nº. 2.125/2003, o fracionamento da Base de Cálculo nas faixas de isenção e de alíquotas aplicáveis é cumulativo com os valores transmitidos anteriormente;\n" + 
               "3) quando, em razão da determinação do 19, § 6º, da Lei 7.850/2002, houver valor de ITCD devido e não recolhido em doação anterior, ele estará incluso no valor total do ITCD a recolher, acima apresentado, sempre que o somatório do valor do ITCD devido nas sucessivas doações superar o valor de 1 (uma) UPFMT.", fontMediaBold));
               celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setPadding(DEFAULT_PADDING);    
               tabela.addCell(celula);   
            }            
      }           
      
		celula = new PdfPCell(new Phrase(FRASE_LEI, fontMediaBold));
		celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);    
		tabela.addCell(celula);
		return tabela;
	}

	/**
	 * Método responsável por montar a tabela de notificação para Retificação / Notificação das GIAS.
	 * @param giaITCDVo
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws ObjetoObrigatorioException
	 * @throws DocumentException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaNotificacao(GIAITCDVo giaITCDVo, String textoDescritivo) throws BadElementException, ObjetoObrigatorioException, DocumentException
	{
		float[] colunas = { 100 };
		PdfPTable tabela = instanciarTabelaPadrao(1, colunas);
		PdfPCell celula = new PdfPCell(new Phrase("Notificação".toUpperCase(), fontMediaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);
	   tabela.setHeaderRows(1);
		celula = new PdfPCell(new Phrase("Fica o contribuinte notificado a recolher o imposto devido, no valor de R$ " + 
						 giaITCDVo.getValorRecolhimentoFormatado() + "(" + 
						 StringUtil.numeroPorExtenso(giaITCDVo.getValorRecolhimento()) + ")", fontPequenaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);    
	   tabela.addCell(celula);
		celula = new PdfPCell(new Phrase(retornaValorItem(textoDescritivo), fontPequenaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);    
	   tabela.addCell(celula);
		return tabela;
	}	

	/**
	 * Método responsável por montar a tabela de notificação para Retificação / Notificação das GIAS.
	 * @param giaITCDVo
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws ObjetoObrigatorioException
	 * @throws DocumentException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaJustificativaAlteracao(GIAITCDVo giaITCDVo) throws BadElementException, ObjetoObrigatorioException, DocumentException
	{
		float[] colunas = { 100 };
		PdfPTable tabela = instanciarTabelaPadrao(1, colunas);
		PdfPCell celula = new PdfPCell(new Phrase("Justificativa da Alteração".toUpperCase(), fontMediaBold));
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setColspan(colunas.length);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);
		tabela.setHeaderRows(1);
		celula = new PdfPCell(new Phrase(giaITCDVo.getJustificativaAlteracao(), fontPequenaBold));
		celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);    
		tabela.addCell(celula);
		return tabela;
	}  	

	/**Metodo responsavel por retornar o valor do item na tabela basica Configuração Gerenciais de Parametros.
	 * @param descricaoValorItem
	 * @return String
	 * @implemented by Lucas Nascimento
	 */
	public String retornaValorItem(String descricaoValorItem)
	{
		ConfiguracaoGerencialParametrosVo configuracaoConsulta = new ConfiguracaoGerencialParametrosVo();
		configuracaoConsulta.setDescricaoItem(descricaoValorItem);
		configuracaoConsulta = new ConfiguracaoGerencialParametrosVo(configuracaoConsulta);
		ConfiguracaoGerencialParametrosBe configuracaoGerencialParametrosBe = null;
		try
		{
			configuracaoGerencialParametrosBe = new ConfiguracaoGerencialParametrosBe();
			configuracaoGerencialParametrosBe.consultarConfiguracaoGerencialParametros(configuracaoConsulta);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ConexaoException e)
		{
			e.printStackTrace();
		}
		catch (ConsultaException e)
		{
			e.printStackTrace();
		}
		catch (ObjetoObrigatorioException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (configuracaoGerencialParametrosBe != null)
			{
				configuracaoGerencialParametrosBe.close();
				configuracaoGerencialParametrosBe = null;
			}
		}
		//Retorna Valor Item
		if (configuracaoConsulta.isExiste())
		{
			return configuracaoConsulta.getValorItem();
		}
		return EntidadeVo.STRING_VAZIA;
	}	

	/**
	 * Retorna STRING_VAZIA caso o objeto passado seja NULO
	 * @param obj
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public String toString(Object obj)
	{
		if (obj == null)
		{
			return EntidadeVo.STRING_VAZIA;
		}
		return String.valueOf(obj);
	}	
	
	public String obterDataNotificacaoRetificacaoFormatada(final GIAITCDVo giaITCDVo, final Date dataEmissaoNotificacaoRetificacao)
	{
		Date data = dataEmissaoNotificacaoRetificacao;
		if(giaITCDVo.getStatusAnterior().getStatusGIAITCD().is(DomnStatusGIAITCD.REATIVADO))
		{
			data = giaITCDVo.getStatusAnterior().getDataAtualizacao();
		}
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}
	
	public PdfPTable tabelaDadosContribuinte(final ContribuinteIntegracaoVo contribuinteIntegracaoVo) throws BadElementException, 
			  DocumentException
	{
	   float[] colunas = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
	   PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
	   tabela.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	   tabela.setTotalWidth(LARGURA_RELATORIO);
	   PdfPCell celula = new PdfPCell(new Phrase("DADOS DO CONTRIBUINTE", fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);
	   tabela.setHeaderRows(1);
	   for(Iterator it = obterDadosContribuinteCorrespondencia(contribuinteIntegracaoVo).iterator(); it.hasNext(); )
	   {
	      PdfPCell celulaAtual = (PdfPCell) it.next();
	      tabela.addCell(celulaAtual);
	   }
	   return tabela; 
	}

	public Collection obterDadosContribuinteCorrespondencia(ContribuinteIntegracaoVo contribuinteIntegracaoVo)
	{
		Collection dadosContribuinte = new ArrayList();
		//LINHA 1
		PdfPCell celula = new PdfPCell(new Phrase("Nome: \n" +  contribuinteIntegracaoVo.getNomeContribuinte(), fontPequenaBold));
		celula.setColspan(8);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("Logradouro: \n" + contribuinteIntegracaoVo.getEndereco(), fontPequenaBold));
		celula.setColspan(10);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("Número: \n" + contribuinteIntegracaoVo.getEnderecoNumero(), fontPequenaBold));
		celula.setColspan(2);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
	   //LINHA 2
		celula = new PdfPCell(new Phrase("Complemento: \n" + contribuinteIntegracaoVo.getEnderecoComplemento(), fontPequenaBold));
		celula.setColspan(6);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("Ponto de Referência: \n" + contribuinteIntegracaoVo.getPontoReferencia(), fontPequenaBold));
		celula.setColspan(6);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("Bairro: \n" + contribuinteIntegracaoVo.getEnderecoBairro(), fontPequenaBold));
		celula.setColspan(6);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("CEP: \n" + contribuinteIntegracaoVo.getCEPFormatado(), fontPequenaBold));
		celula.setColspan(2);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		//LINHA 3
		celula = new PdfPCell(new Phrase("Município: \n" + contribuinteIntegracaoVo.getMunicipio(), fontPequenaBold));
		celula.setColspan(19);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("UF: \n" + contribuinteIntegracaoVo.getSiglaUF(), fontPequenaBold));
		celula.setColspan(1);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		return dadosContribuinte;
		
	}	

   /**
    * Este metodo tem por objetivo formatar o número da GIA ITCD
    * <br><br>
    * <b>REGRAS</b>
    * <br>
    * <ol>
    * <li>
    * Acrescenta a letra "<b>A</b>" a frente no número da GIA caso esta seje DomnVersaoGIAITCD.VERSAO_1_2 e DomnTipoProtocolo.PROTOCOLO_AUTOMATICO.
    * </li>
    * </ol>
    *
    * 
    * @param giaItcdVo
    * @return numero GIA acrescido ou não da letra "<b>A</b>" conforme as regras.
    */
   protected String getNumeroGIAITCDFormatado( GIAITCDVo giaItcdVo)
   {
      String numeroFormatado = String.valueOf(giaItcdVo.getCodigo());
         if(giaItcdVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_2) && giaItcdVo.getTipoProtocoloGIA().is(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO))
         {
            numeroFormatado = "A "+numeroFormatado;
         }
      return numeroFormatado;
   }
}
