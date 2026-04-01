package br.gov.mt.sefaz.itc.model.relatorio;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.pdf.InterfacePDF;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.pdf.AbstractGeradorPDFITCD;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.Color;

import javax.servlet.http.HttpServletRequest;


public class RetificacaoGIAITCDSeparacaoDivorcioGerarPDF extends AbstractGeradorPDFITCD
{
	GIAITCDSeparacaoDivorcioVo gIAITCDSeparacaoDivorcioVo = (GIAITCDSeparacaoDivorcioVo) getEntidadeVo();

	public RetificacaoGIAITCDSeparacaoDivorcioGerarPDF(HttpServletRequest request, GIAITCDVo gIAITCDVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(request, gIAITCDVo, formatoPagina);
	}

	public RetificacaoGIAITCDSeparacaoDivorcioGerarPDF(GIAITCDVo gIAITCDVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(gIAITCDVo, formatoPagina);
	}
	

	/**
	 * Método da classe abstrata implementada para gerar o pdf.
	 * @param document
	 * @throws ObjetoObrigatorioException
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by Rogério Sanches de Oliveira
	 *
	 */
	public void gerarCorpoPDF(Document document) throws ObjetoObrigatorioException, BadElementException, 
			  DocumentException
	{
		float[] larguraColunas = { 16, 16, 17, 17, 17, 17 };
		Table tabela = instanciarTabela(6, larguraColunas);
		tabela.setDefaultColspan(6);
		tabela.setPadding(3F);
		Cell celula = new Cell(new Phrase("RETIFICAÇÃO DA GIA-ITCD SEPARAÇÃO", fontGrandeBold));
		celula.setColspan(6);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultColspan(1);
		tabela.addCell(new Phrase("Data emissão da Retificação", fontPequenaBold));
		tabela.addCell(new Phrase(obterDataNotificacaoRetificacaoFormatada(gIAITCDSeparacaoDivorcioVo, gIAITCDSeparacaoDivorcioVo.getStatusVo().getDataEmissaoRetificacao()), fontPequenaBold));
		tabela.addCell(new Phrase("Nº GIA - ITCD", fontPequenaBold));
		tabela.addCell(new Phrase(String.valueOf(gIAITCDSeparacaoDivorcioVo.getCodigo()), fontPequenaBold));
		tabela.addCell(new Phrase("Tipo de processo", fontPequenaBold));
		tabela.addCell(new Phrase(gIAITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().getTipoProcesso().getTextoCorrente(), fontPequenaBold));
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.setDefaultColspan(6);
		tabela.addCell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_GIA_SEPARACAO), fontPequenaBold));
		document.add(tabela);
		GIAITCDSeparacaoDivorcioGerarPDF separacaoPDF = new GIAITCDSeparacaoDivorcioGerarPDF(gIAITCDSeparacaoDivorcioVo, InterfacePDF.PAGINA_A4_RETRATO);
	   PdfPTable tabelaDeclarante = separacaoPDF.tabelaDeclarante();
	   document.add(tabelaDeclarante);
	   PdfPTable tabelaProcesso = separacaoPDF.tabelaProcesso();
	   document.add(tabelaProcesso);
	   //Retorna a tabela do procurador
	   if(Validador.isNumericoValido(gIAITCDSeparacaoDivorcioVo.getProcuradorVo().getNumrContribuinte()))
	   {
	      PdfPTable tabelaProcurador = separacaoPDF.tabelaProcurador();
	      document.add(tabelaProcurador);        
	   }
	   //Retorna a tabela da natureza da operação
	   PdfPTable tabelaNaturezaOperacao = separacaoPDF.tabelaNaturezaOperacao();
	   document.add(tabelaNaturezaOperacao);
	   //Retorna a tabela de bens
	   PdfPTable tabelaBens = separacaoPDF.tabelaBens("Valor da Avaliação");
	   if (tabelaBens != null)
	   {
	      document.add(tabelaBens);
	   }
	   PdfPTable tabelaConjuge1 = separacaoPDF.tabelaConjuge1();
	   if (tabelaConjuge1 != null)
	   {
	      document.add(tabelaConjuge1);
	   }
	   PdfPTable tabelaBensConjuge1 = separacaoPDF.tabelaBensConjuge1();
	   if (tabelaBensConjuge1 != null)
	   {
	      document.add(tabelaBensConjuge1);
	   }
	   PdfPTable tabelaConjuge2 = separacaoPDF.tabelaConjuge2();
	   if (tabelaConjuge2 != null)
	   {
	      document.add(tabelaConjuge2);
	   }
	   PdfPTable tabelaBensConjuge2 = separacaoPDF.tabelaBensConjuge2();
	   if (tabelaBensConjuge2 != null)
	   {
	      document.add(tabelaBensConjuge2);
	   }
	   //Retorna a tabela de demonstrativos de calculo
	   PdfPTable tabelaDemonstrativoCalculo = separacaoPDF.tabelaDemonstrativoCalculoNormal(false);
	   document.add(tabelaDemonstrativoCalculo);
	   PdfPTable tabelaTotalizacaoConjuge = separacaoPDF.tabelaTotalizacaoConjuge();
	   document.add(tabelaTotalizacaoConjuge);
	   PdfPTable tabelaResumoExplicativo = separacaoPDF.tabelaResumoExplicativo();
	   document.add(tabelaResumoExplicativo);
	   if(Validador.isStringValida(gIAITCDSeparacaoDivorcioVo.getJustificativaAlteracao()))
	   {
	      PdfPTable tabelaJustificativaAlteracao = tabelaJustificativaAlteracao(gIAITCDSeparacaoDivorcioVo);
	      document.add(tabelaJustificativaAlteracao);
	   }     		
//		PdfPTable tabelaObservacaoDoacao = tabelaObservacao(gIAITCDSeparacaoDivorcioVo,false, null);
//		document.add(tabelaObservacaoDoacao);
		PdfPTable tabelaNotificacao = tabelaNotificacao(gIAITCDSeparacaoDivorcioVo,ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_GIA_RETIFICADORA);
		document.add(tabelaNotificacao);
		PdfPTable tabelaAutenticidade = tabelaAutenticidadeRetificacaoNotificacao(gIAITCDSeparacaoDivorcioVo);
		document.add(tabelaAutenticidade);
		PdfPTable tabelaAssinaturaDeclarante = separacaoPDF.tabelaAssinaturaDeclarante();
		document.add(tabelaAssinaturaDeclarante);
//		for (Iterator iteBemTributavel = 
//					 gIAITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().iterator(); iteBemTributavel.hasNext(); )
//		{
//			BemTributavelVo bemTributavel = (BemTributavelVo) iteBemTributavel.next();
//			if (bemTributavel.getFichaImovelVo() instanceof FichaImovelRuralVo)
//			{
//				document.newPage();
//				FichaImovelRuralRelatorioGerarPDF geraPDF = 
//							  new FichaImovelRuralRelatorioGerarPDF(bemTributavel.getFichaImovelVo(), getFormatoPagina());
//				geraPDF.gerarCorpoPDF((FichaImovelRuralVo) bemTributavel.getFichaImovelVo(), document);
//			}
//			else if (bemTributavel.getFichaImovelVo() instanceof FichaImovelUrbanoVo)
//			{
//				document.newPage();
//				FichaImovelUrbanoRelatorioGerarPDF geraPDF = 
//							  new FichaImovelUrbanoRelatorioGerarPDF(bemTributavel.getFichaImovelVo(), getFormatoPagina());
//				geraPDF.gerarCorpoPDF((FichaImovelUrbanoVo) bemTributavel.getFichaImovelVo(), document);
//			}
//		}
		document.newPage();
	   PdfPTable dadosDeclarante = tabelaDadosContribuinte(gIAITCDSeparacaoDivorcioVo.getResponsavelVo());		
		document.add(dadosDeclarante);
	}
}
