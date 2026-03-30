package br.gov.mt.sefaz.itc.model.relatorio;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.pdf.InterfacePDF;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
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


public class RetificacaoGIAITCDDoacaoGerarPDF extends AbstractGeradorPDFITCD
{
	GIAITCDDoacaoVo gIAITCDDoacaoVo = (GIAITCDDoacaoVo) getEntidadeVo();

	public RetificacaoGIAITCDDoacaoGerarPDF(HttpServletRequest request, GIAITCDVo gIAITCDVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(request, gIAITCDVo, formatoPagina);
	}

	public RetificacaoGIAITCDDoacaoGerarPDF(GIAITCDVo gIAITCDVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(gIAITCDVo, formatoPagina);
	}
	

	/**
	 * Método da classe abstrata implementada para gerar o pdf.
	 * @param document
	 * @throws ObjetoObrigatorioException
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by Marcelo Moraes Machado
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void gerarCorpoPDF(Document document) throws ObjetoObrigatorioException, BadElementException, 
			  DocumentException
	{
		float[] larguraColunas = { 16, 16, 17, 17, 17, 17 };
		Table tabela = instanciarTabela(6, larguraColunas);
		tabela.setDefaultColspan(6);
		tabela.setPadding(3F);
		Cell celula = new Cell(new Phrase("RETIFICAÇÃO DA GIA-ITCD DOAÇÃO", fontGrandeBold));
		celula.setColspan(6);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultColspan(1);
		tabela.addCell(new Phrase("Data emissão da Retificação", fontPequenaBold));
		tabela.addCell(new Phrase(obterDataNotificacaoRetificacaoFormatada(gIAITCDDoacaoVo, gIAITCDDoacaoVo.getStatusVo().getDataEmissaoRetificacao()), fontPequenaBold));
		tabela.addCell(new Phrase("Nº GIA - ITCD", fontPequenaBold));
		tabela.addCell(new Phrase(String.valueOf(gIAITCDDoacaoVo.getCodigo()), fontPequenaBold));
		tabela.addCell(new Phrase("Tipo de processo", fontPequenaBold));
		tabela.addCell(new Phrase(gIAITCDDoacaoVo.getNaturezaOperacaoVo().getTipoProcesso().getTextoCorrente(), fontPequenaBold));
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.setDefaultColspan(6);
		tabela.addCell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_GIA_DOACAO), fontPequenaBold));
		document.add(tabela);
		GIAITCDDoacaoGerarPDF doacaoPDF = new GIAITCDDoacaoGerarPDF(gIAITCDDoacaoVo, InterfacePDF.PAGINA_A4_RETRATO);
	   PdfPTable tabelaDeclarante = doacaoPDF.tabelaDeclarante();
	   document.add(tabelaDeclarante);
	   if(Validador.isNumericoValido(gIAITCDDoacaoVo.getProcuradorVo().getEnderecoCEP()))
	   {
	      //Retorna a tabela do procurador
	      PdfPTable tabelaProcurador = doacaoPDF.tabelaProcurador();
	      document.add(tabelaProcurador);
	   }
	   //Retorna a tabela da natureza da operação
	   PdfPTable tabelaNaturezaOperacao = doacaoPDF.tabelaNaturezaOperacao();
	   document.add(tabelaNaturezaOperacao);
	   //Retorna a tabela de bens
	   PdfPTable tabelaBens = doacaoPDF.tabelaBens("Valor da Avaliação");
	   if (tabelaBens != null)
	   {
	      document.add(tabelaBens);
	   }
	   //Retorna a tabela de beneficiarios
	   PdfPTable tabelaBeneficiario = doacaoPDF.tabelaBeneficiario();
	   if (tabelaBeneficiario != null)
	   {
	      document.add(tabelaBeneficiario);
	   }
	   //Retorna a tabela de demonstrativos de calculo
	   PdfPTable tabelaDemonstrativoCalculo = doacaoPDF.tabelaDemonstrativoCalculo(false);
	   document.add(tabelaDemonstrativoCalculo);
	   //Retorna a tabela de demonstrativo por beneficiário
	   PdfPTable tabelaBeneficiarioDetalharDemonstrativo = doacaoPDF.tabelaBeneficiarioDetalharDemonstrativo();
	   document.add(tabelaBeneficiarioDetalharDemonstrativo);
	   PdfPTable tabelaResumoExplicativo = doacaoPDF.tabelaResumoExplicativo();
	   document.add(tabelaResumoExplicativo);
		if(Validador.isStringValida(gIAITCDDoacaoVo.getJustificativaAlteracao()))
		{
			PdfPTable tabelaJustificativaAlteracao = tabelaJustificativaAlteracao(gIAITCDDoacaoVo);
			document.add(tabelaJustificativaAlteracao);
		}
//		PdfPTable tabelaObservacaoDoacao = tabelaObservacao(gIAITCDDoacaoVo,false,null);
//		document.add(tabelaObservacaoDoacao);
		PdfPTable tabelaNotificacao = tabelaNotificacao(gIAITCDDoacaoVo, ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_GIA_RETIFICADORA);
		document.add(tabelaNotificacao);
		PdfPTable tabelaAutenticidade = tabelaAutenticidadeRetificacaoNotificacao(gIAITCDDoacaoVo);
		document.add(tabelaAutenticidade);
		PdfPTable tabelaAssinaturaDeclarante = doacaoPDF.tabelaAssinaturaDeclarante();
		document.add(tabelaAssinaturaDeclarante);
//		for (Iterator iteBemTributavel = 
//					 gIAITCDDoacaoVo.getBemTributavelVo().getCollVO().iterator(); iteBemTributavel.hasNext(); )
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
	   PdfPTable tabelaDadosContribuinte = tabelaDadosContribuinte(gIAITCDDoacaoVo.getContribuinteNotificacaoDar());
		document.add(tabelaDadosContribuinte);
	}
}
