package br.gov.mt.sefaz.itc.model.relatorio;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.pdf.AbstractGeradorPDFITCD;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.Color;

import javax.servlet.http.HttpServletRequest;


/**Classe de Impressão de PDF da funcionalidade Garantia
 * @author Elizabeth Barbosa Moreira
 * @version $Revision: 
 */
public class GIAITCDAutenticidadeGerarPDF extends AbstractGeradorPDFITCD
{

	GIAITCDVo giaITCDVo = (GIAITCDVo) getEntidadeVo();
	
	/**
	 * Construtor da Classe GIAITCDAutenticidadeGerarPDF
	 * @implemented by Elizabeth Barbosa Moreira
	 * *
	 */
	public GIAITCDAutenticidadeGerarPDF(HttpServletRequest request, GIAITCDVo giaITCDVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(request, giaITCDVo, formatoPagina);
	}
	
	

	public void gerarCorpoPDF(Document document) throws BadElementException, DocumentException, 
			  ObjetoObrigatorioException
	{
		Validador.validaObjeto(document);
	   float[] colunas = {25, 25, 25, 25};
	   PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
	   tabela.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	   tabela.setTotalWidth(LARGURA_RELATORIO);
	   PdfPCell celula = new PdfPCell(new Phrase("DADOS DA CONSULTA DA AUTENTICIDADE", fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);
	   tabela.setHeaderRows(1);
		celula = new PdfPCell(new Phrase("Número da GIA-ITCD:\n" + giaITCDVo.getCodigo(), fontPequenaBold));
		celula.setColspan(2);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Tipo da GIA-ITCD:\n" + giaITCDVo.getTipoGIA().getTextoCorrente(), fontPequenaBold));
		celula.setColspan(2);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Nome do Declarante:\n" + giaITCDVo.getResponsavelVo().getNomeContribuinte(), fontPequenaBold));
	   celula.setColspan(2);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("CPF / CNPJ:\n" + giaITCDVo.getResponsavelVo().getNumrDocumento(), fontPequenaBold));
	   celula.setColspan(2);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
		boolean isExibeValores = false;
		if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO))
		{
		   isExibeValores = true;
		   celula = new PdfPCell(new Phrase("GIA-ITCD Quitada", fontMediaBold));
		   celula.setColspan(colunas.length);
		   celula.setPadding(DEFAULT_PADDING);
			celula.setBackgroundColor(Color.LIGHT_GRAY);			
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   tabela.addCell(celula);
		}
		else if(giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_MANUALMENTE))
		{
		   isExibeValores = false;
		   celula = new PdfPCell(new Phrase("GIA-ITCD Quitada", fontMediaBold));
		   celula.setColspan(colunas.length);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setBackgroundColor(Color.LIGHT_GRAY);       
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   tabela.addCell(celula);			
		}
	   else if(giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_PELA_GIOR))
	   {
	      isExibeValores = false;
	      celula = new PdfPCell(new Phrase("GIA-ITCD Quitada", fontMediaBold));
	      celula.setColspan(colunas.length);
	      celula.setPadding(DEFAULT_PADDING);
	      celula.setBackgroundColor(Color.LIGHT_GRAY);       
	      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	      tabela.addCell(celula);       
	   }
	   else if(giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.QUITADO_CCF))
	   {
	      isExibeValores = false;
	      celula = new PdfPCell(new Phrase("GIA-ITCD Quitada", fontMediaBold));
	      celula.setColspan(colunas.length);
	      celula.setPadding(DEFAULT_PADDING);
	      celula.setBackgroundColor(Color.LIGHT_GRAY);       
	      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	      tabela.addCell(celula);       
	   }
		else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO))
		{
		   celula = new PdfPCell(new Phrase("Contribuinte Isento do Imposto ITCD".toUpperCase(), fontMediaBold));
		   celula.setColspan(colunas.length);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setBackgroundColor(Color.LIGHT_GRAY);       
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   tabela.addCell(celula);
		}
	   else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO_IMPOSTO_ATE_1_UPF))
	   {
	      celula = new PdfPCell(new Phrase("Contribuinte Isento do Imposto até 1 UPF".toUpperCase(), fontMediaBold));
	      celula.setColspan(colunas.length);
	      celula.setPadding(DEFAULT_PADDING);
	      celula.setBackgroundColor(Color.LIGHT_GRAY);       
	      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	      tabela.addCell(celula);
	   }
		else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR))
		{
		   celula = new PdfPCell(new Phrase("Não há ocorrência de fato gerador".toUpperCase(), fontMediaBold));
		   celula.setColspan(colunas.length);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setBackgroundColor(Color.LIGHT_GRAY);       
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   tabela.addCell(celula);
		}
		else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO)
					|| giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR))
		{
		   celula = new PdfPCell(new Phrase("GIA-ITCD Pendente de Protocolo".toUpperCase(), fontMediaBold));
		   celula.setColspan(colunas.length);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setBackgroundColor(Color.LIGHT_GRAY);       
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   tabela.addCell(celula);			
		}
	   else if (giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.INATIVADO_AUTOMATICAMENTE)
	            || giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.INATIVO))
		{
		   celula = new PdfPCell(new Phrase("GIA-ITCD se encontra Inativa ou Inativada automaticamente".toUpperCase(), fontMediaBold));
		   celula.setColspan(colunas.length);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setBackgroundColor(Color.LIGHT_GRAY);       
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   tabela.addCell(celula);       			
		}
		else 
		{
			isExibeValores = true;
		   celula = new PdfPCell(new Phrase("GIA-ITCD Pendente de Recolhimento".toUpperCase(), fontMediaBold));
		   celula.setColspan(colunas.length);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setBackgroundColor(Color.LIGHT_GRAY);       
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   tabela.addCell(celula);          
		}
		if(isExibeValores)
		{
		   celula = new PdfPCell(new Phrase("Valor do Imposto: R$ ".toUpperCase() + giaITCDVo.getValorITCDFormatado(), fontPequenaBold));
		   celula.setColspan(colunas.length);
		   celula.setPadding(DEFAULT_PADDING);
		   //celula.setBackgroundColor(Color.LIGHT_GRAY);       
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   tabela.addCell(celula);    
			if(giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
			{
			   celula = new PdfPCell(new Phrase("Valor da Multa: R$ ".toUpperCase() + giaITCDVo.getValorMultaFormatado(), fontPequenaBold));
			   celula.setColspan(colunas.length);
			   celula.setPadding(DEFAULT_PADDING);
			   //celula.setBackgroundColor(Color.LIGHT_GRAY);       
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			   tabela.addCell(celula);                      
			   celula = new PdfPCell(new Phrase("Valor a Recolher: R$ ".toUpperCase() + giaITCDVo.getValorRecolhimentoFormatado(), fontPequenaBold));
			   celula.setColspan(colunas.length);
			   celula.setPadding(DEFAULT_PADDING);
			   //celula.setBackgroundColor(Color.LIGHT_GRAY);       
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			   tabela.addCell(celula);                                           				
			}
		}
		document.add(tabela);
	}

}
