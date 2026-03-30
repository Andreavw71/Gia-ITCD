package br.gov.mt.sefaz.itc.model.relatorio;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.pdf.InterfacePDF;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDocumento;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.pdf.AbstractGeradorPDFITCD;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPTable;

import javax.servlet.http.HttpServletRequest;


/** Esta funcionalidade necessita que GIAITCD tenha o status de NOTIFICADO.
 * @author Rogério Sanches de Oliveira
 * @author Anderson Boehler Iglesias Araujo
 * @author Wendell Pereira de Farias
 * @version $Revision: 1.4 $
 */
public class GIAITCDEmitirNotificacaoGerarPDF extends AbstractGeradorPDFITCD
{
	GIAITCDVo giaITCDVo = (GIAITCDVo) super.getEntidadeVo();

	/** Construtor responsavel por enviar o Request, GIAITCD, formatoPagina.
	 * @param request
	 * @param giaITCDVo
	 * @param formatoPagina
	 * @throws ObjetoObrigatorioException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Wendell Pereira de Farias
	 */
	public GIAITCDEmitirNotificacaoGerarPDF(HttpServletRequest request, GIAITCDVo giaITCDVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(request, giaITCDVo, formatoPagina);
	}

	/** Metodo responsavel por retornar uma tabela com a lista de valores a recolher dos bens tributaveis.
	 * @throws BadElementException
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Wendell Pereira de Farias
	 */
	public Table tabelaValoresRecolher() throws BadElementException
	{
		float[] colunas = { 34, 33, 33 };
		Table tabela = new Table(3);
		tabela.setOffset(3);
		tabela.setBorder(Table.NO_BORDER);
		tabela.setWidths(colunas);
		tabela.setSpaceInsideCell(ESPACO_INTERNO_CELULA);
		tabela.setSpaceBetweenCells(ESPACO_ENTRE_CELULAS);
		tabela.setWidth(90);
		tabela.setPadding(1);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
		tabela.setDefaultCellBorder(Cell.NO_BORDER);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.setDefaultVerticalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell celula = new Cell(new Phrase("VALOR DECLARADO ", fontGrandeBoldITCD));
		celula.setColspan(2);
		tabela.addCell(celula);
		celula = new Cell(new Phrase("VALOR A RECOLHER \n", fontGrandeBoldITCD));
		celula.setColspan(1);
		tabela.addCell(celula);
		celula = 
new Cell(new Phrase("_____________________________________________________________________________________", fontPequenaBold));
		celula.setColspan(3);
		tabela.addCell(celula);
		celula = new Cell(new Phrase(giaITCDVo.getValorTotalBensDeclaradosFormatado(), fontGrandeBoldITCD));
		celula.setColspan(2);
		tabela.addCell(celula);
		tabela.addCell(new Phrase(giaITCDVo.getValorRecolhimentoFormatado(), fontGrandeBoldITCD));
		tabela.setDefaultCellBorder(Cell.NO_BORDER);
		/*
                                                				VALOR DECLARADO    (.getValorMercadoFormatado())		VALOR A RECOLHER getValorRecolhimentoFormatado()
		 									R$ 500.000,00 										       R$450,00
	*/
		tabela.setDefaultColspan(1);
		tabela.setDefaultHorizontalAlignment(Table.ALIGN_RIGHT);
		return tabela;
	}

	/** Metodo responsavel por gerar o corpo do relatorio.
	 * @param document
	 * @throws DocumentException
	 * @implemented by Wendell Pereira de Farias
	 */
	public void gerarCorpoPDF(Document document) throws DocumentException, ObjetoObrigatorioException
	{
		/**
         * Variaveis auxiliares para preenchimento do relatório.
         */
		String localidade;
		String contribuinte;
		String CPF_CNPJ;
		String data_Protocolo_GIAITCD;
		String referente_GIAITCD_Numero;
		localidade = "Sem Localidade";
		contribuinte = "Contribuinte Sem Nome";
		CPF_CNPJ = "000.000.000-00";
		data_Protocolo_GIAITCD = "00/00/0000";
		referente_GIAITCD_Numero = "00000";		
	   PdfPTable tabelaDadosContribuinte = tabelaDadosContribuinte(giaITCDVo.getContribuinteNotificacaoDar());
		if (giaITCDVo != null)
		{
			if (giaITCDVo.getStatusVo() != null)
			{
//				if ((giaITCDVo != null) && (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO)))
//				{
					if (giaITCDVo.getContribuinteNotificacaoDar() != null)
					{
						if (giaITCDVo.getContribuinteNotificacaoDar().getMunicipio() != null)
						{
							localidade = giaITCDVo.getContribuinteNotificacaoDar().getMunicipio();
						}
						if (giaITCDVo.getContribuinteNotificacaoDar().getNomeContribuinte() != null)
						{
							contribuinte = giaITCDVo.getContribuinteNotificacaoDar().getNomeContribuinte();
						}
						if (giaITCDVo.getContribuinteNotificacaoDar().getNumrDocumento() != null)
						{
							if(giaITCDVo.getContribuinteNotificacaoDar().getTipoDocumento().is(DomnTipoDocumento.CPF))
							{
							   CPF_CNPJ = StringUtil.formataCPF(giaITCDVo.getContribuinteNotificacaoDar().getNumrDocumento());
							}								
							else
							{
							   CPF_CNPJ = StringUtil.formataCNPJ(giaITCDVo.getContribuinteNotificacaoDar().getNumrDocumento());
							}								
						}
						if (Validador.isDataValida(giaITCDVo.getStatusVo().getDataProtocolo()))
						{
							data_Protocolo_GIAITCD = giaITCDVo.getStatusVo().getDataProtocoloFormatada();
						}
						if (Validador.isNumericoValido(giaITCDVo.getCodigo()))
						{
							referente_GIAITCD_Numero = "" + giaITCDVo.getCodigo();
						}
					}
//				}
			}
		}
		Paragraph quebralinha = new Paragraph("            ");
		quebralinha.setLeading(20); //40
		Paragraph p = new Paragraph("NOTIFICAÇÃO", fontGrandeBoldItalicITCD);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		p.setLeading(30); //50
		document.add(p);
		document.add(quebralinha);
		float[] larguraColunas = { 60, 40 };
		Table tabela = new Table(2);
		tabela.setOffset(3);
		tabela.setWidth(90);
		tabela.setBorder(Table.NO_BORDER);
		tabela.setWidths(larguraColunas);
		tabela.setSpaceInsideCell(ESPACO_INTERNO_CELULA);
		tabela.setSpaceBetweenCells(ESPACO_ENTRE_CELULAS);
		tabela.setPadding(1);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
		tabela.setDefaultCellBorder(Cell.NO_BORDER);
		tabela.setDefaultColspan(1);
		tabela.setDefaultHorizontalAlignment(Table.ALIGN_LEFT);
		Phrase frase = new Phrase("Localidade:  ", fontGrandeBoldITCD);
		frase.add(new Phrase(localidade, fontGrandeITCD));
		tabela.addCell(frase);
		frase = new Phrase("Data da Notificação:  ", fontGrandeBoldITCD);
		frase.add(obterDataNotificacaoRetificacaoFormatada(giaITCDVo, giaITCDVo.getStatusVo().getDataNotificacao()));
		tabela.addCell(frase);
		tabela.setDefaultColspan(2);
		frase = new Phrase("Contribuinte:  ", fontGrandeBoldITCD);
		frase.add(new Phrase(contribuinte, fontGrandeITCD));
		tabela.addCell(frase);
		frase = new Phrase("CPF/CNPJ:  ", fontGrandeBoldITCD);
		frase.add(new Phrase(CPF_CNPJ, fontGrandeITCD));
		tabela.addCell(frase);
		frase = new Phrase("Data de Protocolo da GIA-ITCD:  ", fontGrandeBoldITCD);
		frase.add(new Phrase(data_Protocolo_GIAITCD + "\n", fontGrandeITCD));
		tabela.addCell(frase);
		frase = new Phrase("Referente à GIA-ITCD de número ", fontGrandeITCD);
		frase.add(new Phrase("n° " + referente_GIAITCD_Numero + "\n\n", fontGrandeBoldITCD));
		tabela.addCell(frase);
		document.add(tabela);
		Paragraph paragraph = new Paragraph();
		paragraph.add(new Phrase("             " + 
						 retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_NOTIFICACAO)+"\n", fontGrandeITCD));
		paragraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
		paragraph.setIndentationLeft(20);
		paragraph.setIndentationRight(20);
//		paragraph.setSpacingBefore(15);
//		paragraph.setSpacingAfter(15);
		document.add(paragraph);
		//Criando a tabela lista bens tributaveis.
//		Table tabelaValoresRecolher = tabelaValoresRecolher();
//		document.add(tabelaValoresRecolher); //Inserindo a tabela no documento
		try
		{
			if (giaITCDVo instanceof GIAITCDInventarioArrolamentoVo)
			{
				InventarioArrolamentoGerarPDF pdfInventario = new InventarioArrolamentoGerarPDF(giaITCDVo, InterfacePDF.PAGINA_A4_RETRATO);
				PdfPTable demonstrativoCalculo = pdfInventario.tabelaDemonstrativoCalculo(false);
				document.add(demonstrativoCalculo);
				PdfPTable demonstrativoCalculoBeneficiarios = pdfInventario.tabelaBeneficiarioDetalharDemonstrativo();
				document.add(demonstrativoCalculoBeneficiarios);
				PdfPTable demonstrativoCalculoResumoExplicativo = pdfInventario.tabelaResumoExplicativo();
				document.add(demonstrativoCalculoResumoExplicativo);
			}
			else if (giaITCDVo instanceof GIAITCDDoacaoVo)
			{
				GIAITCDDoacaoGerarPDF pdfDoacao = new GIAITCDDoacaoGerarPDF(giaITCDVo, InterfacePDF.PAGINA_A4_RETRATO);
				PdfPTable demonstrativoCalculo = pdfDoacao.tabelaDemonstrativoCalculo(false);
				document.add(demonstrativoCalculo); 
				PdfPTable demonstrativoCalculoBeneficiarios = pdfDoacao.tabelaBeneficiarioDetalharDemonstrativo();
				document.add(demonstrativoCalculoBeneficiarios); 
			   PdfPTable demonstrativoCalculoResumoExplicativo = pdfDoacao.tabelaResumoExplicativo();
			   document.add(demonstrativoCalculoResumoExplicativo);
				
			}
		   else if (giaITCDVo instanceof GIAITCDSeparacaoDivorcioVo)
		   {
		      GIAITCDSeparacaoDivorcioGerarPDF pdfSeparacao = new GIAITCDSeparacaoDivorcioGerarPDF(giaITCDVo, InterfacePDF.PAGINA_A4_RETRATO);
		      PdfPTable demonstrativoCalculo = pdfSeparacao.tabelaDemonstrativoCalculoNormal(false);
		      document.add(demonstrativoCalculo); 
				PdfPTable demonstrativoCalculoConjuge = pdfSeparacao.tabelaTotalizacaoConjuge();
				document.add(demonstrativoCalculoConjuge);
		      PdfPTable demonstrativoCalculoResumoExplicativo = pdfSeparacao.tabelaResumoExplicativo();
		      document.add(demonstrativoCalculoResumoExplicativo);
				
		   }			
		}
		catch (ObjetoObrigatorioException e)
		{
			e.printStackTrace();
		}
		PdfPTable tabelaNotificacao = tabelaNotificacao(giaITCDVo,ConfiguracaoITCD.PARAMETRO_TEXTO_INFORMATIVO_NOTIFICACAO);
		document.add(tabelaNotificacao);
		PdfPTable tabelaAssinatura = tabelaAssinaturaDeclarante();
		document.add(tabelaAssinatura);
		document.newPage();
		document.add(tabelaDadosContribuinte);
		
	}
	
}
