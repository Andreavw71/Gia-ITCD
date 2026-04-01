package br.gov.mt.sefaz.itc.model.relatorio;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.pdf.InterfacePDF;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
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
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.Color;

import javax.servlet.http.HttpServletRequest;


public class RetificacaoGIAITCDInventarioArrolamentoGerarPDF extends AbstractGeradorPDFITCD
{
	//Recuperando valor encapsulado no entidadeVo.
	GIAITCDInventarioArrolamentoVo gIAITCDInventarioArrolamentoVo = (GIAITCDInventarioArrolamentoVo) getEntidadeVo();

	/**
	 * Construtor da classe PDF
	 * @implemented by Marcelo Moraes Machado
	 * **/
	public RetificacaoGIAITCDInventarioArrolamentoGerarPDF(HttpServletRequest request, GIAITCDVo gIAITCDVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(request, gIAITCDVo, formatoPagina);
	}

	public RetificacaoGIAITCDInventarioArrolamentoGerarPDF(GIAITCDVo gIAITCDVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
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
		float[] larguraColunas = { 10, 10, 20, 20, 20, 20  };
		Table tabela = instanciarTabela(6, larguraColunas);
		tabela.setDefaultColspan(6);
	   Cell celula = new Cell(new Phrase("RETIFICAÇÃO DA GIA-ITCD INVENTÁRIO/ARROLAMENTO", fontGrandeBold));
	   celula.setColspan(6);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
	   tabela.addCell(celula);
		tabela.setDefaultColspan(1);
		tabela.addCell(new Phrase("Data de Emissão", fontPequenaBold));
      if(gIAITCDInventarioArrolamentoVo.getStatusVo().getStatusGIAITCD().getDomnValr() == DomnStatusGIAITCD.RETIFICADO){
         tabela.addCell(new Phrase(obterDataNotificacaoRetificacaoFormatada(gIAITCDInventarioArrolamentoVo, gIAITCDInventarioArrolamentoVo.getStatusVo().getDataEmissaoRetificacao()), fontPequenaBold));
      }else{
         tabela.addCell(new Phrase("—", fontPequenaBold));
      }
		tabela.addCell(new Phrase("Nº GIA - ITCD", fontPequenaBold));
		tabela.addCell(new Phrase(String.valueOf(gIAITCDInventarioArrolamentoVo.getCodigo()), fontPequenaBold));
		tabela.addCell(new Phrase("Tipo de processo", fontPequenaBold));
		tabela.addCell(new Phrase(gIAITCDInventarioArrolamentoVo.getNaturezaOperacaoVo().getTipoProcesso().getTextoCorrente(), fontPequenaBold));
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.setDefaultColspan(6);
		tabela.addCell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_GIA_INVENTARIO), fontPequenaBold));
		document.add(tabela);
		InventarioArrolamentoGerarPDF inventarioPDF = new InventarioArrolamentoGerarPDF(gIAITCDInventarioArrolamentoVo, InterfacePDF.PAGINA_A4_RETRATO);
	   PdfPTable tabelaInventariante = inventarioPDF.tabelaInventariante();
	   document.add(tabelaInventariante);
	   PdfPTable tabelaDeCujus = inventarioPDF.tabelaDeCujus();
	   document.add(tabelaDeCujus);        
	   PdfPTable tabelaProcesso = inventarioPDF.tabelaProcesso();
	   document.add(tabelaProcesso);
	   if(inventarioPDF.isEstadoCivilCasadoConvivente(gIAITCDInventarioArrolamentoVo))
	   {        
	      PdfPTable tabelaMeeiro = inventarioPDF.tabelaMeeiro();
	      document.add(tabelaMeeiro);
	   }
	   if(gIAITCDInventarioArrolamentoVo.getProcuradorVo().getNumrContribuinte().doubleValue() != 0)
	   {
	      PdfPTable tabelaProcurador = inventarioPDF.tabelaProcurador();
	      document.add(tabelaProcurador);        
	   }
	   PdfPTable tabelaNaturezaOperacao = inventarioPDF.tabelaNaturezaOperacao();
	   document.add(tabelaNaturezaOperacao);
	   PdfPTable tabelaBens = inventarioPDF.tabelaBens("Valor da Avaliação");
	   if (tabelaBens != null)
	   {
	      document.add(tabelaBens);
	   }
	   PdfPTable tabelaBeneficiario = inventarioPDF.tabelaBeneficiario();
	   if (tabelaBeneficiario != null)
	   {
	      document.add(tabelaBeneficiario);
	   }
	   PdfPTable tabelaDemonstrativoCalculo = inventarioPDF.tabelaDemonstrativoCalculo(false);
	   document.add(tabelaDemonstrativoCalculo);
	   PdfPTable tabelaBeneficiarioDetalharDemonstrativo = inventarioPDF.tabelaBeneficiarioDetalharDemonstrativo();
	   document.add(tabelaBeneficiarioDetalharDemonstrativo);
	   PdfPTable tabelaResumoExplicativo = inventarioPDF.tabelaResumoExplicativo();
	   document.add(tabelaResumoExplicativo);
	   if(Validador.isStringValida(gIAITCDInventarioArrolamentoVo.getJustificativaAlteracao()))
	   {
	      PdfPTable tabelaJustificativaAlteracao = tabelaJustificativaAlteracao(gIAITCDInventarioArrolamentoVo);
	      document.add(tabelaJustificativaAlteracao);
	   }		
//		PdfPTable tabelaObservacaoInventarioArrolamento = tabelaObservacao(gIAITCDInventarioArrolamentoVo, false, null);
//		document.add(tabelaObservacaoInventarioArrolamento);
		PdfPTable tabelaNotificacao = tabelaNotificacao(gIAITCDInventarioArrolamentoVo,ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_GIA_RETIFICADORA);
		document.add(tabelaNotificacao);
		PdfPTable tabelaAutenticidade = tabelaAutenticidadeRetificacaoNotificacao(gIAITCDInventarioArrolamentoVo);
		document.add(tabelaAutenticidade);
		PdfPTable tabelaAssinaturaDeclarante = inventarioPDF.tabelaAssinaturaDeclarante();
		document.add(tabelaAssinaturaDeclarante);
//		for (Iterator iteBemTributavel = 
//					 gIAITCDInventarioArrolamentoVo.getBemTributavelVo().getCollVO().iterator(); iteBemTributavel.hasNext(); )
//		{
//			BemTributavelVo bemTributavel = (BemTributavelVo) iteBemTributavel.next();
//			bemTributavel.setGiaITCDVo(gIAITCDInventarioArrolamentoVo);
//			bemTributavel.getFichaImovelVo().setBemTributavelVo(bemTributavel);
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
	   PdfPTable dadosContribuinte = tabelaDadosContribuinte(gIAITCDInventarioArrolamentoVo.getContribuinteNotificacaoDar());		
		document.add(dadosContribuinte);
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
		return tabela;
	}	
}
