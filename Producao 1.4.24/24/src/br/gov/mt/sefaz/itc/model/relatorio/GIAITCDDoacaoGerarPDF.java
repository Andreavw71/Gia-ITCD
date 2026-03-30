package br.gov.mt.sefaz.itc.model.relatorio;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.aliquota.GIAITCDDoacaoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaVo;

import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.pdf.AbstractGeradorPDFITCD;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;


/**
 * Relatório Especificado pelo Caso de Uso Reimpressão GIAITCD - Declaração de Isenção de Valores, Separação/Divórcio/Padilha
 * e Declaração de Não Ocorrência de Fato Gerador.
 *
 * @author Wendell Pereira de Farias
 * @version $Revision: 1.6 $
 */
public class GIAITCDDoacaoGerarPDF extends AbstractGeradorPDFITCD
{
	//Associação com o objeto GiaDoação.
	GIAITCDDoacaoVo gIAITCDDoacaoVo = (GIAITCDDoacaoVo) getEntidadeVo();
	String valorITCDFormatado = GIAITCDDoacaoVo.STRING_VAZIA;
	String valorMultaFormatado = GIAITCDDoacaoVo.STRING_VAZIA;
	String valorRecolhimentoFormatado = GIAITCDDoacaoVo.STRING_VAZIA;
	String tipoDoacao = GIAITCDDoacaoVo.STRING_VAZIA;
	String valorBaseCalculoReduzida = GIAITCDDoacaoVo.STRING_VAZIA;

	public GIAITCDDoacaoGerarPDF(HttpServletRequest request, GIAITCDVo giaItcdVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(request, giaItcdVo, formatoPagina);
	}

	public GIAITCDDoacaoGerarPDF(GIAITCDVo giaItcdVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(giaItcdVo, formatoPagina);
	}


	/**
	 * Metodo responsável por gerar o corpo do relatório.
	 * @param document
	 * @throws BadElementException
	 * @throws DocumentException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Wendell Pereira de Farias
	 */
	public void gerarCorpoPDF(Document document) throws BadElementException, DocumentException, 
			  ObjetoObrigatorioException
	{
		float[] colunas = { 25, 25, 25, 25 };
		PdfPTable tabela = instanciarTabelaPadrao(4, colunas);
		PdfPCell celula = new PdfPCell(new Phrase("GIA-ITCD", fontGrandeBold));
		celula.setColspan(4);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);    
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		tabela.addCell(celula);
		//Consulta a tabela de Configuração gerencias de parametros.
		celula = new PdfPCell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_GIA_DOACAO) + "\n\n", fontMedia));
		celula.setColspan(4);
		celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		tabela.addCell(celula);
		document.add(tabela);
		//Retorna a tabela do declarante
		PdfPTable tabelaDeclarante = tabelaDeclarante();
		document.add(tabelaDeclarante);
		if(Validador.isNumericoValido(gIAITCDDoacaoVo.getProcuradorVo().getEnderecoCEP()))
		{
			//Retorna a tabela do procurador
			PdfPTable tabelaProcurador = tabelaProcurador();
			document.add(tabelaProcurador);
		}
		//Retorna a tabela da natureza da operação
		PdfPTable tabelaNaturezaOperacao = tabelaNaturezaOperacao();
		document.add(tabelaNaturezaOperacao);
		//Retorna a tabela de bens
		PdfPTable tabelaBens = tabelaBens();
		if (tabelaBens != null)
		{
			document.add(tabelaBens);
		}
		//Retorna a tabela de beneficiarios
		PdfPTable tabelaBeneficiario = tabelaBeneficiario();
		if (tabelaBeneficiario != null)
		{
			document.add(tabelaBeneficiario);
		}      
	   //Retorna a tabela de doações sucessivas
      PdfPTable tabelaDoacoesSucessivas = tabelaDoacoesSucessivas();
      if (tabelaDoacoesSucessivas != null) {
         //document.newPage(); // opcional: quebra de página antes, se desejar
         document.add(tabelaDoacoesSucessivas);
      }	   
		//Retorna a tabela de demonstrativos de calculo
      if(gIAITCDDoacaoVo.getIsTodosBeneficiariosDoacaoSucessiva()){
         PdfPTable tabelaDemonstrativoCalculo = tabelaDemonstrativoCalculo(true);
         document.add(tabelaDemonstrativoCalculo);
         PdfPTable tabelaBeneficiarioDetalharDemonstrativo = tabelaBeneficiarioDetalharDemonstrativo();
         document.add(tabelaBeneficiarioDetalharDemonstrativo);
      }else{
         PdfPTable tabelaDemonstrativoCalculoSemDoacaoSucessiva = tabelaDemonstrativoCalculoSemDoacaoSucessiva(true);
         document.add(tabelaDemonstrativoCalculoSemDoacaoSucessiva);
         PdfPTable tabelaBeneficiarioDetalharDemonstrativoSemDoacaoSucessiva = tabelaBeneficiarioDetalharDemonstrativoSemDoacaoSucessiva();
         document.add(tabelaBeneficiarioDetalharDemonstrativoSemDoacaoSucessiva);
      }
		PdfPTable tabelaResumoExplicativo = tabelaResumoExplicativo();
		document.add(tabelaResumoExplicativo);
		//Retorna a tabela de prazo da declaração
		PdfPTable tabelaPrazoDeclaracao = tabelaObservacao(gIAITCDDoacaoVo, true, "Doação / Outros");
		document.add(tabelaPrazoDeclaracao);
		//Retorna a tabela de assinatura do declarante
		PdfPTable tabelaAssinaturaDeclarante = tabelaAssinaturaDeclarante();
		document.add(tabelaAssinaturaDeclarante);
		//Retorna a tabela de autencidade
		PdfPTable tabelaAutenticidade = tabelaAutenticidade(gIAITCDDoacaoVo);
		document.add(tabelaAutenticidade);
		for (Iterator iteBemTributavel = 
					 gIAITCDDoacaoVo.getBemTributavelVo().getCollVO().iterator(); iteBemTributavel.hasNext(); )
		{
			BemTributavelVo bemTributavel = (BemTributavelVo) iteBemTributavel.next();
			bemTributavel.getFichaImovelVo().setBemTributavelVo(bemTributavel);
			if (bemTributavel.getFichaImovelVo() instanceof FichaImovelRuralVo)
			{
				document.newPage();
				FichaImovelRuralRelatorioGerarPDF geraPDF = 
							  new FichaImovelRuralRelatorioGerarPDF(getRequest(), bemTributavel.getFichaImovelVo(), getFormatoPagina());
				geraPDF.gerarCorpoPDF((FichaImovelRuralVo) bemTributavel.getFichaImovelVo(), document);
			}
			else if (bemTributavel.getFichaImovelVo() instanceof FichaImovelUrbanoVo)
			{
				document.newPage();
				FichaImovelUrbanoRelatorioGerarPDF geraPDF = 
							  new FichaImovelUrbanoRelatorioGerarPDF(getRequest(), bemTributavel.getFichaImovelVo(), getFormatoPagina());
				geraPDF.gerarCorpoPDF((FichaImovelUrbanoVo) bemTributavel.getFichaImovelVo(), document);
			}
		}
	}
   

	/**
	 * Método responsável por escrever a tabela de Natureza da Operação/Doação
	 * @implemented by Maxwell Mendes Rocha
	 * @implemented by Wendell Pereira de Farias
	 * **/
	public PdfPTable tabelaNaturezaOperacao() throws BadElementException, DocumentException
	{
	   float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
	   PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
	   PdfPCell celula = new PdfPCell(new Phrase("Natureza da Operação".toUpperCase(), fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);    
	   tabela.addCell(celula);
	   tabela.setHeaderRows(1);
	   //LINHA 1
	   celula = new PdfPCell(new Phrase("Natureza da Operação \n" + gIAITCDDoacaoVo.getNaturezaOperacaoVo().getDescricaoNaturezaOperacao(), fontPequenaBold));
	   celula.setColspan(12);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Percentual da Doação / Transmissão \n " + gIAITCDDoacaoVo.getFracaoIdealFormatada() + "%", fontPequenaBold));
	   celula.setColspan(8);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
		return tabela;
	}
	
	/**
	 * Método responsável por escrever a tabela de Bens/GIA
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public PdfPTable tabelaBens(String labelValorBem) throws BadElementException, DocumentException
	{	   
	   if((gIAITCDDoacaoVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_3)) || (gIAITCDDoacaoVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_4))){                
      
	   float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 8 };
	   PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
	   PdfPCell celula = new PdfPCell(new Phrase("Bens".toUpperCase(), fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);    
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Classificação", fontPequenaBold));
	   celula.setColspan(4);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);       
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Tipo", fontPequenaBold));
	   celula.setColspan(2);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);                
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Descrição", fontPequenaBold));
	   celula.setColspan(6);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);                
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Isenção Prevista em Lei", fontPequenaBold));
	   celula.setColspan(2);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);                
	   tabela.addCell(celula);		
	   celula = new PdfPCell(new Phrase("Valor Declarado Pelo Contribuinte", fontPequenaBold));
	   celula.setColspan(3);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);                
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase(Validador.isStringValida(labelValorBem)?labelValorBem:"Valor Arbitrado", fontPequenaBold));
	   celula.setColspan(3);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);                
	   tabela.addCell(celula);
      
	   celula = new PdfPCell(new Phrase("Anuência valor arbitrado", fontPequenaBold));
	   celula.setColspan(2);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);                
	   tabela.addCell(celula);
      
		tabela.setHeaderRows(2);
	   int qtdeLinha = 0;
	   if (gIAITCDDoacaoVo.getBemTributavelVo().isExisteCollVO())
	   {
	      qtdeLinha++;
	      Iterator it = gIAITCDDoacaoVo.getBemTributavelVo().getCollVO().iterator();
	      while (it.hasNext())
	      {
	         BemTributavelVo bem = (BemTributavelVo) it.next();
	         // classificação do bem
				celula = new PdfPCell(new Phrase(bem.getBemVo().getClassificacaoTipoBem().getTextoCorrente(), fontPequenaBold));
				celula.setPadding(DEFAULT_PADDING);
				celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
				celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				celula.setColspan(4);
				tabela.addCell(celula);
				celula = new PdfPCell(new Phrase(bem.getBemVo().getDescricaoTipoBem(), fontPequenaBold));
				celula.setPadding(DEFAULT_PADDING);
				celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
				celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				celula.setColspan(2);
				tabela.addCell(celula);
				celula = new PdfPCell(new Phrase(bem.getDescricaoBemTributavel(), fontPequenaBold));
				celula.setPadding(DEFAULT_PADDING);
				celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
				celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				celula.setColspan(6);
				tabela.addCell(celula);                
				celula = new PdfPCell(new Phrase(bem.getIsencaoPrevistaFormatada().getTextoCorrente(), fontPequenaBold));
				celula.setPadding(DEFAULT_PADDING);
				celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
				celula.setHorizontalAlignment(Element.ALIGN_CENTER);
				celula.setColspan(2);
				tabela.addCell(celula);  
	         celula = new PdfPCell(new Phrase(bem.getValorInformadoFormatado(), fontPequenaBold));
	         celula.setPadding(DEFAULT_PADDING);
	         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	         celula.setColspan(3);
	         tabela.addCell(celula);
				celula = new PdfPCell(new Phrase(bem.getValorMercadoFormatado(), fontPequenaBold));
				celula.setPadding(DEFAULT_PADDING);
				celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
				celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
				celula.setColspan(3);
				tabela.addCell(celula);
            
	         celula = new PdfPCell(new Phrase(bem.getConcordaComValorArbitrado().getTextoCorrente(), fontPequenaBold));            
	         celula.setPadding(DEFAULT_PADDING);
	         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	         celula.setColspan(2);
	         tabela.addCell(celula);
            
	         if(Validador.isStringValida(bem.getAvaliacaoBemTributavelVo().getObservacao()))
	         {
	            celula = new PdfPCell(new Phrase("Observação da Avaliação: "+bem.getAvaliacaoBemTributavelVo().getObservacao(), fontPequenaBold));
	            celula.setColspan(20);
	            celula.setPadding(DEFAULT_PADDING);
	            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	            tabela.addCell(celula);
	         }				 
	      }
	   }      
		if (qtdeLinha > 0)
		{
			return tabela;
		}
      }else if(gIAITCDDoacaoVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_2)){
      
         float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,8 };
               PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
               PdfPCell celula = new PdfPCell(new Phrase("Bens".toUpperCase(), fontMediaBold));
               celula.setColspan(colunas.length);
               celula.setBackgroundColor(Color.LIGHT_GRAY);
               celula.setHorizontalAlignment(Element.ALIGN_CENTER);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setPadding(DEFAULT_PADDING);    
               tabela.addCell(celula);
               celula = new PdfPCell(new Phrase("Classificação do bem", fontPequenaBold));
               celula.setColspan(4);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_CENTER);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setBackgroundColor(Color.LIGHT_GRAY);       
               tabela.addCell(celula);
               celula = new PdfPCell(new Phrase("Tipo de Bem", fontPequenaBold));
               celula.setColspan(3);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_CENTER);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setBackgroundColor(Color.LIGHT_GRAY);                
               tabela.addCell(celula);
               celula = new PdfPCell(new Phrase("Descrição", fontPequenaBold));
               celula.setColspan(6);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_CENTER);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setBackgroundColor(Color.LIGHT_GRAY);                
               tabela.addCell(celula);
               celula = new PdfPCell(new Phrase("Isenção Prevista em Lei", fontPequenaBold));
               celula.setColspan(3);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_CENTER);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setBackgroundColor(Color.LIGHT_GRAY);                
               tabela.addCell(celula);    
               celula = new PdfPCell(new Phrase("Valor Declarado Pelo Contribuinte", fontPequenaBold));
               celula.setColspan(4);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_CENTER);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setBackgroundColor(Color.LIGHT_GRAY);                
               tabela.addCell(celula);
               celula = new PdfPCell(new Phrase(Validador.isStringValida(labelValorBem)?labelValorBem:"Valor Arbitrado", fontPequenaBold));
               celula.setColspan(5);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_CENTER);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setBackgroundColor(Color.LIGHT_GRAY);                
               tabela.addCell(celula);
               tabela.setHeaderRows(2);
               int qtdeLinha = 0;
               if (gIAITCDDoacaoVo.getBemTributavelVo().isExisteCollVO())
               {
                  qtdeLinha++;
                  Iterator it = gIAITCDDoacaoVo.getBemTributavelVo().getCollVO().iterator();
                  while (it.hasNext())
                  {
                     BemTributavelVo bem = (BemTributavelVo) it.next();
                     // classificação do bem
                     celula = new PdfPCell(new Phrase(bem.getBemVo().getClassificacaoTipoBem().getTextoCorrente(), fontPequenaBold));
                     celula.setPadding(DEFAULT_PADDING);
                     celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                     celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                     celula.setColspan(4);
                     tabela.addCell(celula);
                     celula = new PdfPCell(new Phrase(bem.getBemVo().getDescricaoTipoBem(), fontPequenaBold));
                     celula.setPadding(DEFAULT_PADDING);
                     celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                     celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                     celula.setColspan(3);
                     tabela.addCell(celula);
                     celula = new PdfPCell(new Phrase(bem.getDescricaoBemTributavel(), fontPequenaBold));
                     celula.setPadding(DEFAULT_PADDING);
                     celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                     celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                     celula.setColspan(6);
                     tabela.addCell(celula);                
                     celula = new PdfPCell(new Phrase(bem.getIsencaoPrevistaFormatada().getTextoCorrente(), fontPequenaBold));
                     celula.setPadding(DEFAULT_PADDING);
                     celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                     celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                     celula.setColspan(3);
                     tabela.addCell(celula);  
                     celula = new PdfPCell(new Phrase(bem.getValorInformadoFormatado(), fontPequenaBold));
                     celula.setPadding(DEFAULT_PADDING);
                     celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                     celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
                     celula.setColspan(4);
                     tabela.addCell(celula);
                     celula = new PdfPCell(new Phrase(bem.getValorMercadoFormatado(), fontPequenaBold));
                     celula.setPadding(DEFAULT_PADDING);
                     celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                     celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
                     celula.setColspan(4);
                     tabela.addCell(celula);   
                     if(Validador.isStringValida(bem.getAvaliacaoBemTributavelVo().getObservacao()))
                     {
                        celula = new PdfPCell(new Phrase("Observação da Avaliação: "+bem.getAvaliacaoBemTributavelVo().getObservacao(), fontPequenaBold));
                        celula.setColspan(20);
                        celula.setPadding(DEFAULT_PADDING);
                        celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                        tabela.addCell(celula);
                     }            
                  }
               }  
               if (qtdeLinha > 0)
               {
                  return tabela;
               }
      
      }
		return null;
	}

	/**
	 * Método responsável por escrever a tabela de Bens/GIA
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public PdfPTable tabelaBens() throws BadElementException, DocumentException
	{
		return tabelaBens("");
	}

	

	/**
	 * Método responsável por escrever a tabela de Beneficiário/Doação
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public PdfPTable tabelaBeneficiario() throws BadElementException, DocumentException
	{
	   float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
	   PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
	   PdfPCell celula = new PdfPCell(new Phrase("Beneficiários".toUpperCase(), fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);    
	   tabela.addCell(celula);
	   tabela.setHeaderRows(1);
	   int linha = 0;
		if (gIAITCDDoacaoVo.getBeneficiarioVo().isExisteCollVO())
		{
			linha++;
			Iterator it = gIAITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator();
			while (it.hasNext())
			{
				BeneficiarioVo beneficiarioVo = (BeneficiarioVo) it.next();
				for(Iterator itCell = obterDadosContribuinte(beneficiarioVo.getPessoaBeneficiaria(), "do Beneficiário").iterator(); itCell.hasNext(); )
				{
					PdfPCell celulaAtual = (PdfPCell) itCell.next();
					tabela.addCell(celulaAtual);
				}
				celula = new PdfPCell(new Phrase("Percentual Atribuido \n" + 
									  ((GIAITCDDoacaoBeneficiarioVo) beneficiarioVo).getPercentualBemRecebido() + 
									  "%", fontPequenaBold));
				celula.setColspan(colunas.length / 2);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);
				celula = new PdfPCell(new Phrase("Valor recebido \n " + 
									  beneficiarioVo.getValorRecebidoSemDoacaoSucessivaFormatado(), fontPequenaBold));
			   celula.setColspan(colunas.length / 2);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);
			}         
		                       
         if(gIAITCDDoacaoVo.getNaturezaOperacaoVo().getDescricaoNaturezaOperacao().equals("RENUNCIA DE USUFRUTO") || gIAITCDDoacaoVo.getNaturezaOperacaoVo().getDescricaoNaturezaOperacao().equals("EXTINÇÃO DE USUFRUTO")){ 
            celula = new PdfPCell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_BENS_DIREITOS) + "\n" + "- Caso se trate de renúncia de usufruto, e haja grandes benfeitorias realizadas pelo usufrutuário não indenizadas, a transmissão gratuita dessas benfeitorias ao nu-proprietário configura doação plena, nos termos do art. 1.219 do Código Civil, sendo, portanto, fato gerador do ITCD.  Deverá ser apresentada  \"Declaração Manual do ITCD\", em substituição à GIA ITCD, para fins de apuração do imposto devido.", fontPequenaBold));
            celula.setColspan(20);
            celula.setPadding(DEFAULT_PADDING);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            tabela.addCell(celula);             
         }else{
            celula = new PdfPCell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_BENS_DIREITOS), fontPequenaBold));
            celula.setColspan(20);
            celula.setPadding(DEFAULT_PADDING);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            tabela.addCell(celula);
         }        
		}     

		if (linha > 0)
		{
			return tabela;
		}
		return null;
	}
   
   /**
      * Método responsável por escrever a tabela de Doações Sucessivas
      */
      public PdfPTable tabelaDoacoesSucessivas() throws BadElementException, DocumentException {
          float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
          PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
          PdfPCell celula = new PdfPCell(new Phrase("DOAÇÕES SUCESSIVAS", fontMediaBold));
          celula.setColspan(colunas.length);
          celula.setBackgroundColor(Color.LIGHT_GRAY);
          celula.setHorizontalAlignment(Element.ALIGN_CENTER);
          celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
          celula.setPadding(DEFAULT_PADDING);    
          tabela.addCell(celula);
          tabela.setHeaderRows(1);
          int linha = 0;

          if (gIAITCDDoacaoVo.getBeneficiarioVo().isExisteCollVO()) {            
              for (Iterator iteBeneficiario = gIAITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); iteBeneficiario.hasNext();) {
                  GIAITCDDoacaoBeneficiarioVo beneficiarioVo = (GIAITCDDoacaoBeneficiarioVo) iteBeneficiario.next();
                  
                 if (beneficiarioVo.getGiaitcdDoacaoSucessivaVo() != null && beneficiarioVo.getGiaitcdDoacaoSucessivaVo().getCollVO() != null && !beneficiarioVo.getGiaitcdDoacaoSucessivaVo().getCollVO().isEmpty()) {
                     linha++;                   
                    // Nome do beneficiário
                    celula = new PdfPCell(new Phrase(beneficiarioVo.getPessoaBeneficiaria().getNomeContribuinte(), fontMediaBold));
                    celula.setColspan(colunas.length);
                    celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celula.setPadding(DEFAULT_PADDING);
                    tabela.addCell(celula);

                    // Cabeçalho das colunas
                    celula = new PdfPCell(new Phrase("GIA ITCD", fontPequenaBold));
                    celula.setColspan(4);
                    celula.setBackgroundColor(Color.LIGHT_GRAY);
                    celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celula.setPadding(DEFAULT_PADDING);
                    tabela.addCell(celula);

                    celula = new PdfPCell(new Phrase("e-Process", fontPequenaBold));
                    celula.setColspan(4);
                    celula.setBackgroundColor(Color.LIGHT_GRAY);
                    celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celula.setPadding(DEFAULT_PADDING);
                    tabela.addCell(celula);

                    celula = new PdfPCell(new Phrase("Mês", fontPequenaBold));
                    celula.setColspan(4);
                    celula.setBackgroundColor(Color.LIGHT_GRAY);
                    celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celula.setPadding(DEFAULT_PADDING);
                    tabela.addCell(celula);

                    celula = new PdfPCell(new Phrase("Valor Base de Cálculo", fontPequenaBold));
                    celula.setColspan(4);
                    celula.setBackgroundColor(Color.LIGHT_GRAY);
                    celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celula.setPadding(DEFAULT_PADDING);
                    tabela.addCell(celula);

                    celula = new PdfPCell(new Phrase("Valor ITCD", fontPequenaBold));
                    celula.setColspan(4);
                    celula.setBackgroundColor(Color.LIGHT_GRAY);
                    celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celula.setPadding(DEFAULT_PADDING);
                    tabela.addCell(celula);
                    
                    // Dados das doações sucessivas
                     for (Iterator iteDoacao = beneficiarioVo.getGiaitcdDoacaoSucessivaVo().getCollVO().iterator(); iteDoacao.hasNext();) {
                         GIAITCDDoacaoSucessivaVo doacao = (GIAITCDDoacaoSucessivaVo) iteDoacao.next();
                    
                         celula = new PdfPCell(new Phrase(String.valueOf(doacao.getGiaITCDVo().getCodigo()), fontPequenaBold));
                         celula.setColspan(4);
                         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                         celula.setPadding(DEFAULT_PADDING);
                         tabela.addCell(celula);
                    
                         SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                         celula = new PdfPCell(new Phrase((doacao.getNumeroEprocess()), fontPequenaBold));
                         celula.setColspan(4);
                         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                         celula.setPadding(DEFAULT_PADDING);
                         tabela.addCell(celula);
                    
                         celula = new PdfPCell(new Phrase((doacao.getMesGIAITCDAnterior().trim() + "/" + yearFormat.format(doacao.getDataGIAITCDAnterior())), fontPequenaBold));
                         celula.setColspan(4);
                         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                         celula.setPadding(DEFAULT_PADDING);
                         tabela.addCell(celula);
                    
                         celula = new PdfPCell(new Phrase(doacao.getValorBaseDeCalcFormatado(), fontPequenaBold));
                         celula.setColspan(4);
                         celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         celula.setPadding(DEFAULT_PADDING);
                         tabela.addCell(celula);
                    
                         celula = new PdfPCell(new Phrase(doacao.getValorITCDFormatado(), fontPequenaBold));
                         celula.setColspan(4);
                         celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         celula.setPadding(DEFAULT_PADDING);
                         tabela.addCell(celula);
                     }
                 }                
              }
          }
         if (linha > 0)
         {
            return tabela;
         }
         return null;
      }

	/**
	 * Método responsável por escrever a tabela Demonstrativo de Calculo/GIA
	 * @implemented by Maxwell Mendes Rocha
	 * @implemented by Wendell Pereira de Farias
	 * **/
	public PdfPTable tabelaDemonstrativoCalculo(boolean exibeMensagemParametroConfiguracao) throws BadElementException, DocumentException
	{
	   float[] colunas = { 25, 7, 23, 15, 15, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 13, 12, 20, 9, 9, 9, 20, 9, 9, 9};
		//Validação valores utilizado no relatório
		String fracaoIdealFormatada = GIAITCDDoacaoVo.STRING_VAZIA;
		String numeroLei = GIAITCDDoacaoVo.STRING_VAZIA;
		String anoLei = GIAITCDDoacaoVo.STRING_VAZIA;
		String lei = GIAITCDDoacaoVo.STRING_VAZIA;
		String percentualMulta = GIAITCDDoacaoVo.STRING_VAZIA;
		String valorUPFFormatado = GIAITCDDoacaoVo.STRING_VAZIA;
		String valorTotalBensDeclaradosFormatado = GIAITCDDoacaoVo.STRING_VAZIA;
		String valorCalculoDemonstrativoFormatado = GIAITCDDoacaoVo.STRING_VAZIA;
	   String valorTotalInformadoContribuinteFormatado = GIAITCDDoacaoVo.STRING_VAZIA;
	   String valorTotalBaseCalculoDoacaoSucessivaFormatado = GIAITCDDoacaoVo.STRING_VAZIA;


		//Validação valores utilizado no relatório.
		if (gIAITCDDoacaoVo != null)
		{
			//Retorna Fracao Ideal Formatada
			if (Validador.isStringValida(gIAITCDDoacaoVo.getFracaoIdealFormatada()))
			{
				fracaoIdealFormatada = gIAITCDDoacaoVo.getFracaoIdealFormatada();
			}
			//Retorna o Número da Lei
			if (Validador.isNumericoValido(gIAITCDDoacaoVo.getParametroLegislacaoVo().getNumeroLegislacao()))
			{
				numeroLei = String.valueOf(gIAITCDDoacaoVo.getParametroLegislacaoVo().getNumeroLegislacao());
				lei = numeroLei;
			}
			if (Validador.isNumericoValido(gIAITCDDoacaoVo.getParametroLegislacaoVo().getAnoLegislacao()))
			{
				anoLei = String.valueOf(gIAITCDDoacaoVo.getParametroLegislacaoVo().getAnoLegislacao());
				lei = lei + "/" + anoLei;
			}
			//Retorna o Objeto Beneficiario
			if (gIAITCDDoacaoVo.getBeneficiarioVo() != null)
			{
				//Retorna o Percentual da Alíquota Formatada
				if (Validador.isStringValida(gIAITCDDoacaoVo.getParametroLegislacaoVo().getAliquotaVo().getPercentualLegislacaoAliquotaFormatado()))
				{
					percentualMulta = 
										 gIAITCDDoacaoVo.getParametroLegislacaoVo().getAliquotaVo().getPercentualLegislacaoAliquotaFormatado();
				}
				//Retorna UPFF Formatado
				if (Validador.isStringValida(gIAITCDDoacaoVo.getValorUPFFormatado()))
				{
					valorUPFFormatado = gIAITCDDoacaoVo.getValorUPFFormatado();
				}
				//Retorna o Valor Total dos Bens Declarados
				if (Validador.isStringValida(gIAITCDDoacaoVo.getValorTotalBensDeclaradosFormatado()))
				{
					valorTotalBensDeclaradosFormatado = gIAITCDDoacaoVo.getValorTotalBensDeclaradosFormatado();
				}
				//Retorna o Valor do Calculo Demonstrativo Formatado
				if (Validador.isStringValida(gIAITCDDoacaoVo.getValorCalculoDemonstrativoFormatado()))
				{
					valorCalculoDemonstrativoFormatado = gIAITCDDoacaoVo.getValorCalculoDemonstrativoFormatado();
				}
				//Retorna o Valor do ITCD Formatado
				if (Validador.isStringValida(gIAITCDDoacaoVo.getValorITCDFormatado()))
				{
					valorITCDFormatado = gIAITCDDoacaoVo.getValorITCDFormatado();
				}
				//Retorna o Valor Multa Formatado
				if (Validador.isStringValida(gIAITCDDoacaoVo.getValorMultaFormatado()))
				{
					valorMultaFormatado = gIAITCDDoacaoVo.getValorMultaFormatado();
				}
				//Retorna o Valor Recolhimento Formatado
				if (Validador.isStringValida(gIAITCDDoacaoVo.getValorRecolhimentoFormatado()))
				{
					valorRecolhimentoFormatado = gIAITCDDoacaoVo.getValorRecolhimentoFormatado();
				}
			   //Retorna o Doação/outros (Total ou parcial);
			   if (Validador.isDominioNumericoValido(gIAITCDDoacaoVo.getTipoDoacao()))
			   {
			      tipoDoacao = gIAITCDDoacaoVo.getTipoDoacao().getTextoCorrente();
			   }
			   // Retorna o Percentual da redução de base de cálculos;
			   if (Validador.isNumericoValido(gIAITCDDoacaoVo.getBaseCalculoReduzida()))
			   {
			      valorBaseCalculoReduzida = gIAITCDDoacaoVo.getBaseCalculoReduzidaFormatado();
			   }	
			   if (Validador.isNumericoValido(gIAITCDDoacaoVo.getValorTotalInformadoBensDeclarados()))
			   {
			      valorTotalInformadoContribuinteFormatado = gIAITCDDoacaoVo.getBemTributavelVo().getValorTotalInformadoContribuinteFormatado();
			   }
			   if (Validador.isStringValida(gIAITCDDoacaoVo.getSomaValorRecebidoDoacaoSucessivaFormatado()))
            {
               valorTotalBaseCalculoDoacaoSucessivaFormatado = gIAITCDDoacaoVo.getSomaValorRecebidoDoacaoSucessivaFormatado();
            }   
			}
		}
	   PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
	   PdfPCell celula = new PdfPCell(new Phrase("Demonstrativo de Cálculo".toUpperCase(), fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);    
	   celula.setPadding(DEFAULT_PADDING);    
	   tabela.addCell(celula);
	   tabela.setHeaderRows(1);
		if(exibeMensagemParametroConfiguracao)
		{
		   celula = new PdfPCell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_DEMONSTRATIVO_CALCULO), fontPequenaBold));
		   celula.setColspan(colunas.length);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   tabela.addCell(celula);			
		}
		celula = new PdfPCell(new Phrase("Percentual Transmitido\n" + fracaoIdealFormatada + "%", fontPequenaBold));
	   celula.setColspan(2);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Doação/Outros  \n" + tipoDoacao, fontPequenaBold));
	   celula.setColspan(2);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Percentual de Redução de Base de Cálculos \n" + valorBaseCalculoReduzida+"%", fontPequenaBold));
	   celula.setColspan(4);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Lei \n" + lei, fontPequenaBold));
	   celula.setColspan(2);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Valor da UPF \n" + valorUPFFormatado, fontPequenaBold));
	   celula.setColspan(2);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Total dos Bens Declarados pelo Contribuinte \n" + valorTotalInformadoContribuinteFormatado, fontPequenaBold));
	   celula.setColspan(4);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   tabela.addCell(celula); 
		celula = new PdfPCell(new Phrase("Total dos bens Arbitrados \n" + valorTotalBensDeclaradosFormatado, fontPequenaBold));
	   celula.setColspan(4);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   tabela.addCell(celula);	
      celula = new PdfPCell(new Phrase("Valor da Base de Cálculo Tributável \n" + valorCalculoDemonstrativoFormatado, fontPequenaBold));
      celula.setColspan(4);
      celula.setPadding(DEFAULT_PADDING);
      celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      tabela.addCell(celula); 
	   celula = new PdfPCell(new Phrase("Valor da Base de Cálculo Doação Sucessiva \n" + valorTotalBaseCalculoDoacaoSucessivaFormatado, fontPequenaBold));
      celula.setColspan(4);
      celula.setPadding(DEFAULT_PADDING);
      celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      tabela.addCell(celula); 

		return tabela;
	}
   
   public PdfPTable tabelaDemonstrativoCalculoSemDoacaoSucessiva(boolean exibeMensagemParametroConfiguracao) throws BadElementException, DocumentException
      {
         float[] colunas = { 25,7, 23, 15, 15, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 13, 12, 32};
               //Validação valores utilizado no relatório
               String fracaoIdealFormatada = GIAITCDDoacaoVo.STRING_VAZIA;
               String numeroLei = GIAITCDDoacaoVo.STRING_VAZIA;
               String anoLei = GIAITCDDoacaoVo.STRING_VAZIA;
               String lei = GIAITCDDoacaoVo.STRING_VAZIA;
               String percentualMulta = GIAITCDDoacaoVo.STRING_VAZIA;
               String valorUPFFormatado = GIAITCDDoacaoVo.STRING_VAZIA;
               String valorTotalBensDeclaradosFormatado = GIAITCDDoacaoVo.STRING_VAZIA;
               String valorCalculoDemonstrativoFormatado = GIAITCDDoacaoVo.STRING_VAZIA;
               String valorTotalInformadoContribuinteFormatado = GIAITCDDoacaoVo.STRING_VAZIA;


               //Validação valores utilizado no relatório.
               if (gIAITCDDoacaoVo != null)
               {
                  //Retorna Fracao Ideal Formatada
                  if (Validador.isStringValida(gIAITCDDoacaoVo.getFracaoIdealFormatada()))
                  {
                     fracaoIdealFormatada = gIAITCDDoacaoVo.getFracaoIdealFormatada();
                  }
                  //Retorna o Número da Lei
                  if (Validador.isNumericoValido(gIAITCDDoacaoVo.getParametroLegislacaoVo().getNumeroLegislacao()))
                  {
                     numeroLei = String.valueOf(gIAITCDDoacaoVo.getParametroLegislacaoVo().getNumeroLegislacao());
                     lei = numeroLei;
                  }
                  if (Validador.isNumericoValido(gIAITCDDoacaoVo.getParametroLegislacaoVo().getAnoLegislacao()))
                  {
                     anoLei = String.valueOf(gIAITCDDoacaoVo.getParametroLegislacaoVo().getAnoLegislacao());
                     lei = lei + "/" + anoLei;
                  }
                  //Retorna o Objeto Beneficiario
                  if (gIAITCDDoacaoVo.getBeneficiarioVo() != null)
                  {
                     //Retorna o Percentual da Alíquota Formatada
                     if (Validador.isStringValida(gIAITCDDoacaoVo.getParametroLegislacaoVo().getAliquotaVo().getPercentualLegislacaoAliquotaFormatado()))
                     {
                        percentualMulta = 
                                        gIAITCDDoacaoVo.getParametroLegislacaoVo().getAliquotaVo().getPercentualLegislacaoAliquotaFormatado();
                     }
                     //Retorna UPFF Formatado
                     if (Validador.isStringValida(gIAITCDDoacaoVo.getValorUPFFormatado()))
                     {
                        valorUPFFormatado = gIAITCDDoacaoVo.getValorUPFFormatado();
                     }
                     //Retorna o Valor Total dos Bens Declarados
                     if (Validador.isStringValida(gIAITCDDoacaoVo.getValorTotalBensDeclaradosFormatado()))
                     {
                        valorTotalBensDeclaradosFormatado = gIAITCDDoacaoVo.getValorTotalBensDeclaradosFormatado();
                     }
                     //Retorna o Valor do Calculo Demonstrativo Formatado
                     if (Validador.isStringValida(gIAITCDDoacaoVo.getValorCalculoDemonstrativoFormatado()))
                     {
                        valorCalculoDemonstrativoFormatado = gIAITCDDoacaoVo.getValorCalculoDemonstrativoFormatado();
                     }
                     //Retorna o Valor do ITCD Formatado
                     if (Validador.isStringValida(gIAITCDDoacaoVo.getValorITCDFormatado()))
                     {
                        valorITCDFormatado = gIAITCDDoacaoVo.getValorITCDFormatado();
                     }
                     //Retorna o Valor Multa Formatado
                     if (Validador.isStringValida(gIAITCDDoacaoVo.getValorMultaFormatado()))
                     {
                        valorMultaFormatado = gIAITCDDoacaoVo.getValorMultaFormatado();
                     }
                     //Retorna o Valor Recolhimento Formatado
                     if (Validador.isStringValida(gIAITCDDoacaoVo.getValorRecolhimentoFormatado()))
                     {
                        valorRecolhimentoFormatado = gIAITCDDoacaoVo.getValorRecolhimentoFormatado();
                     }
                     //Retorna o Doação/outros (Total ou parcial);
                     if (Validador.isDominioNumericoValido(gIAITCDDoacaoVo.getTipoDoacao()))
                     {
                        tipoDoacao = gIAITCDDoacaoVo.getTipoDoacao().getTextoCorrente();
                     }
                     // Retorna o Percentual da redução de base de cálculos;
                     if (Validador.isNumericoValido(gIAITCDDoacaoVo.getBaseCalculoReduzida()))
                     {
                        valorBaseCalculoReduzida = gIAITCDDoacaoVo.getBaseCalculoReduzidaFormatado();
                     }  
                     if (Validador.isNumericoValido(gIAITCDDoacaoVo.getValorTotalInformadoBensDeclarados()))
                     {
                        valorTotalInformadoContribuinteFormatado = gIAITCDDoacaoVo.getBemTributavelVo().getValorTotalInformadoContribuinteFormatado();
                     }  
                  }
               }
               PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
               PdfPCell celula = new PdfPCell(new Phrase("Demonstrativo de Cálculo".toUpperCase(), fontMediaBold));
               celula.setColspan(colunas.length);
               celula.setBackgroundColor(Color.LIGHT_GRAY);
               celula.setHorizontalAlignment(Element.ALIGN_CENTER);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);    
               celula.setPadding(DEFAULT_PADDING);    
               tabela.addCell(celula);
               tabela.setHeaderRows(1);
               if(exibeMensagemParametroConfiguracao)
               {
                  celula = new PdfPCell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_DEMONSTRATIVO_CALCULO), fontPequenaBold));
                  celula.setColspan(colunas.length);
                  celula.setPadding(DEFAULT_PADDING);
                  celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                  celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  tabela.addCell(celula);       
               }
               celula = new PdfPCell(new Phrase("Percentual Transmitido\n" + fracaoIdealFormatada + "%", fontPequenaBold));
               celula.setColspan(2);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               tabela.addCell(celula);
               celula = new PdfPCell(new Phrase("Doação/Outros  \n" + tipoDoacao, fontPequenaBold));
               celula.setColspan(2);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               tabela.addCell(celula);
               celula = new PdfPCell(new Phrase("Percentual de Redução de Base de Cálculos \n" + valorBaseCalculoReduzida+"%", fontPequenaBold));
               celula.setColspan(4);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               tabela.addCell(celula);
               celula = new PdfPCell(new Phrase("Lei \n" + lei, fontPequenaBold));
               celula.setColspan(2);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               tabela.addCell(celula);
               celula = new PdfPCell(new Phrase("Valor da UPF \n" + valorUPFFormatado, fontPequenaBold));
               celula.setColspan(2);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               tabela.addCell(celula);
               celula = new PdfPCell(new Phrase("Total dos Bens Declarados pelo Contribuinte \n" + valorTotalInformadoContribuinteFormatado, fontPequenaBold));
               celula.setColspan(4);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               tabela.addCell(celula); 
               celula = new PdfPCell(new Phrase("Total dos bens Arbitrados \n" + valorTotalBensDeclaradosFormatado, fontPequenaBold));
               celula.setColspan(4);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               tabela.addCell(celula);    
               celula = new PdfPCell(new Phrase("Valor da Base de Cálculo Tributável \n" + valorCalculoDemonstrativoFormatado, fontPequenaBold));
               celula.setColspan(4);
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               tabela.addCell(celula);    

               return tabela;
      }
      
      

	/**
	 * Método responsável por escrever o Detalhamento do Cálculo por de Beneficiário/GIA
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public PdfPTable tabelaBeneficiarioDetalharDemonstrativo() throws BadElementException, DocumentException
	{  
      if(gIAITCDDoacaoVo.getParametroLegislacaoVo().isLegislacaoCascata())
      {
         return tabelaBeneficiarioDetalharDemonstrativoCalculoEmCascata();
      }
      else
      {
	     return tabelaBeneficiarioDetalharDemonstrativoCalculoNormal();
	   }
	}
   
   public PdfPTable tabelaBeneficiarioDetalharDemonstrativoSemDoacaoSucessiva() throws BadElementException, DocumentException
   {  
      if(gIAITCDDoacaoVo.getParametroLegislacaoVo().isLegislacaoCascata())
      {
         return tabelaBeneficiarioDetalharDemonstrativoCalculoEmCascataSemDoacaoSucessiva();
      }
      else
      {
        return tabelaBeneficiarioDetalharDemonstrativoCalculoNormal();
      }
   }
	

	/**
	 * Método responsável por descrever a tabela Resumo Explicativo.
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaResumoExplicativo() throws BadElementException, DocumentException
	{
		float[] colunas = {50, 50};  
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
		PdfPCell celula = new PdfPCell(new Phrase("Resumo Explicativo".toUpperCase(), fontMediaBold));
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setColspan(colunas.length);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);
		tabela.setHeaderRows(1);
		celula = new PdfPCell(new Phrase("Valor Total do ITCD \n" + gIAITCDDoacaoVo.getValorITCDFormatado(), fontPequenaBold));
		celula.setPadding(DEFAULT_PADDING);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
		tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Valor total a recolher \n" + gIAITCDDoacaoVo.getValorRecolhimentoFormatado(), fontPequenaBold));
		celula.setPadding(DEFAULT_PADDING);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
		tabela.addCell(celula);
		return tabela;    
	}

	public PdfPTable tabelaDeclarante() throws BadElementException, DocumentException
	{
		return tabelaPessoa("Doador", "do Doador", gIAITCDDoacaoVo.getResponsavelVo());
	}

	public PdfPTable tabelaProcurador() throws BadElementException, DocumentException
	{
		return tabelaPessoa("Procurador", "do Procurador", gIAITCDDoacaoVo.getProcuradorVo());
	}
	
   public PdfPTable tabelaBeneficiarioDetalharDemonstrativoCalculoNormal() throws BadElementException, DocumentException
   {
      PdfPTable tabela = null;
      PdfPCell celula = null;
      if(gIAITCDDoacaoVo.getDataCriacao().before(retornaDataLimite())){
         float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
         tabela = instanciarTabelaPadrao(colunas.length, colunas);
         celula = new PdfPCell(new Phrase("Cálculo do Demonstrativo por Beneficiário".toUpperCase(), fontMediaBold));
         celula.setColspan(colunas.length);
      }else{
         float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, };
         tabela = instanciarTabelaPadrao(colunas.length, colunas);
         celula = new PdfPCell(new Phrase("Cálculo do Demonstrativo por Beneficiário".toUpperCase(), fontMediaBold));
         celula.setColspan(colunas.length);
      } 
      
      celula.setBackgroundColor(Color.LIGHT_GRAY);
      celula.setHorizontalAlignment(Element.ALIGN_CENTER);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);    
      tabela.addCell(celula); 
      celula = new PdfPCell(new Phrase("Nome do Beneficiário", fontPequenaBold));
      celula.setColspan(8);
      celula.setBackgroundColor(Color.LIGHT_GRAY);
      celula.setHorizontalAlignment(Element.ALIGN_CENTER);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);    
      tabela.addCell(celula); 
      celula = new PdfPCell(new Phrase("Valor Recebido", fontPequenaBold));
      celula.setColspan(5);
      celula.setBackgroundColor(Color.LIGHT_GRAY);
      celula.setHorizontalAlignment(Element.ALIGN_CENTER);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);    
      tabela.addCell(celula); 
      celula = new PdfPCell(new Phrase("Alíquota", fontPequenaBold));
      celula.setColspan(2);
      celula.setBackgroundColor(Color.LIGHT_GRAY);
      celula.setHorizontalAlignment(Element.ALIGN_CENTER);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);    
      tabela.addCell(celula); 
      celula = new PdfPCell(new Phrase("Valor a Recolher Devido", fontPequenaBold));
      celula.setColspan(5);
      celula.setBackgroundColor(Color.LIGHT_GRAY);
      celula.setHorizontalAlignment(Element.ALIGN_CENTER);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);    
      tabela.addCell(celula);                
      
      tabela.setHeaderRows(2);
      for (Iterator iteBeneficiario = gIAITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); iteBeneficiario.hasNext(); )
      {
         GIAITCDDoacaoBeneficiarioVo beneficiarioVo = (GIAITCDDoacaoBeneficiarioVo) iteBeneficiario.next();
         celula = new PdfPCell(new Phrase(beneficiarioVo.getPessoaBeneficiaria().getNomeContribuinte(), fontPequenaBold));
         celula.setPadding(DEFAULT_PADDING);
         celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setColspan(8);         
         tabela.addCell(celula);             
         celula = new PdfPCell(new Phrase(beneficiarioVo.getValorRecebidoCalculadoFormatado(), fontPequenaBold));
         celula.setPadding(DEFAULT_PADDING);
         celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);       
         celula.setColspan(5);
         tabela.addCell(celula);
         celula = new PdfPCell(new Phrase("Fração BC Sujeita à Alíquota de "+ beneficiarioVo.getPercentualAliquotaFormatado() + "%", fontPequenaBold));
         celula.setPadding(DEFAULT_PADDING);
         celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setColspan(2);
         tabela.addCell(celula);
         celula = new PdfPCell(new Phrase(beneficiarioVo.getValorRecolherFormatado(), fontPequenaBold));
         celula.setPadding(DEFAULT_PADDING);
         celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);       
         celula.setColspan(5);
         tabela.addCell(celula);
         
         GIAITCDDoacaoBeneficiarioVo beneficiarioDoacao = (GIAITCDDoacaoBeneficiarioVo) beneficiarioVo;
         Integer infoDispensaRecolhimento = beneficiarioDoacao.getInfoDispensaRecolhimento();
         Integer infoIsencao = beneficiarioDoacao.getInfoIsencao();
         
         if(infoDispensaRecolhimento != null || infoIsencao != null){
         
            celula = new PdfPCell(new Phrase("Observação ", fontPequenaBold));
            celula.setColspan(5);
            celula.setBackgroundColor(Color.LIGHT_GRAY);
            celula.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setPadding(DEFAULT_PADDING);    
            tabela.addCell(celula);
         
            if(beneficiarioDoacao.getInfoDispensaRecolhimento() == 2){
            
               celula = new PdfPCell(new Phrase("Dispensa de Imposto", fontPequenaBold));
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);       
               celula.setColspan(5);
               tabela.addCell(celula);              

            }else if(beneficiarioDoacao.getInfoIsencao() == 2){
            
               celula = new PdfPCell(new Phrase("Isento de Imposto", fontPequenaBold));
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);       
               celula.setColspan(5);
               tabela.addCell(celula);
               
            }else{
               celula = new PdfPCell(new Phrase("Imposto a Recolher", fontPequenaBold));
               celula.setPadding(DEFAULT_PADDING);
               celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);       
               celula.setColspan(5);
               tabela.addCell(celula);
            }
            
         }
      }
      return tabela;
   }
   
   /**
    * Método responsável por escrever o Detalhamento do Cálculo por de Beneficiário/GIA
    * @throws BadElementException
    * @throws DocumentException
    * @return PdfPTable
    * @implemented by Ricardo Vitor de Oliveira Moraes
    * @implemented by Maxwell Mendes Rocha
    * *
    */
   public PdfPTable tabelaBeneficiarioDetalharDemonstrativoCalculoEmCascata() throws BadElementException, DocumentException
   {
      int linhasCabecalho = 1;
      PdfPTable tabela = null;
      PdfPCell celula = null;
      if(gIAITCDDoacaoVo.getDataCriacao().before(retornaDataLimite())){
         float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
         tabela = instanciarTabelaPadrao(colunas.length, colunas);
         celula = new PdfPCell(new Phrase("Cálculo do Demonstrativo por Beneficiário".toUpperCase(), fontMediaBold));
         celula.setColspan(colunas.length);
      }else{
         float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, };
         tabela = instanciarTabelaPadrao(colunas.length, colunas);
         celula = new PdfPCell(new Phrase("Cálculo do Demonstrativo por Beneficiário".toUpperCase(), fontMediaBold));
         celula.setColspan(colunas.length);
      }      
      
      celula.setBackgroundColor(Color.LIGHT_GRAY);
      celula.setHorizontalAlignment(Element.ALIGN_CENTER);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);    
      tabela.addCell(celula); 
     
         TreeMap celulasAliquota = new TreeMap();
         if(Validador.isCollectionValida( gIAITCDDoacaoVo.getBeneficiarioVo().getCollVO()))
         {
            for(Iterator it = gIAITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext(); )
            {
               GIAITCDDoacaoBeneficiarioVo beneficiarioAtual = (GIAITCDDoacaoBeneficiarioVo) it.next();
               if(Validador.isCollectionValida(beneficiarioAtual.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO()))
               {
                  for(Iterator itAliquotas = beneficiarioAtual.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().iterator(); itAliquotas.hasNext(); )
                  {
                     GIAITCDDoacaoBeneficiarioAliquotaVo aliquota = (GIAITCDDoacaoBeneficiarioAliquotaVo) itAliquotas.next();
                     if(!celulasAliquota.containsKey(""+aliquota.getPercentualAliquota()))
                     {
                        celulasAliquota.put(""+aliquota.getPercentualAliquota(), montaCelulaAliquota(aliquota));                                         
                     }
                  }
               }
            }
         }
         celula = new PdfPCell(new Phrase("Nome do Beneficiário", fontPequenaBold));
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setBackgroundColor(Color.LIGHT_GRAY);       
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(defineColspanNomeBeneficiarioPorQuantidadeAliquotas(celulasAliquota.size()));
         tabela.addCell(celula);
         celula = new PdfPCell(new Phrase("Valor Recebido", fontPequenaBold));
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setBackgroundColor(Color.LIGHT_GRAY);                
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(2);
         tabela.addCell(celula);       
         if(celulasAliquota != null && celulasAliquota.size() > 0)
         {
            Iterator it = celulasAliquota.values().iterator();
            int i = 7;
            while(it.hasNext() && i>0)
            {
               PdfPCell celulaAtual = (PdfPCell) it.next();
               celula.setHorizontalAlignment(Element.ALIGN_CENTER);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setBackgroundColor(Color.LIGHT_GRAY);       
               celula.setPadding(DEFAULT_PADDING);          
               celulaAtual.setColspan(2);
               tabela.addCell(celulaAtual);
               i--;
            }
         }
         
         celula = new PdfPCell(new Phrase("Valor do ITCD", fontPequenaBold));
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setBackgroundColor(Color.LIGHT_GRAY);                
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(2);            
         tabela.addCell(celula);
        
         
         if(gIAITCDDoacaoVo.getDataCriacao().before(retornaDataLimite())){
            celula = new PdfPCell(new Phrase("Valor de ITCD Anterior", fontPequenaBold));
            celula.setColspan(2);
            celula.setBackgroundColor(Color.LIGHT_GRAY);
            celula.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setPadding(DEFAULT_PADDING);    
            tabela.addCell(celula);         
         }
         
         celula = new PdfPCell(new Phrase("Valor a Recolher Devido", fontPequenaBold));
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setBackgroundColor(Color.LIGHT_GRAY);                
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(2);            
         tabela.addCell(celula);  
         
         celula = new PdfPCell(new Phrase("Observação", fontPequenaBold));                  
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setBackgroundColor(Color.LIGHT_GRAY);                
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(2);            
         tabela.addCell(celula);
         

         if(Validador.isCollectionValida(gIAITCDDoacaoVo.getBeneficiarioVo().getCollVO()))
         {
            for (Iterator iteBeneficiario = gIAITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); iteBeneficiario.hasNext(); )
            {
               GIAITCDDoacaoBeneficiarioVo beneficiarioVo = (GIAITCDDoacaoBeneficiarioVo) iteBeneficiario.next();
               montaCelulasBeneficiarioCascata(beneficiarioVo, tabela, celulasAliquota.size(), gIAITCDDoacaoVo.getDataCriacao());                            
            }           
         }
         
      return tabela;
   }
   
   private static Date retornaDataLimite(){      
       Calendar cal = Calendar.getInstance();
       cal.set(Calendar.YEAR, 2025);
       cal.set(Calendar.MONTH, Calendar.JANUARY);
       cal.set(Calendar.DAY_OF_MONTH, 1);

       cal.set(Calendar.HOUR_OF_DAY, 0);
       cal.set(Calendar.MINUTE, 0);
       cal.set(Calendar.SECOND, 0);
       cal.set(Calendar.MILLISECOND, 0);
       Date data = cal.getTime();
       
      return data;
   }
   
   public PdfPTable tabelaBeneficiarioDetalharDemonstrativoCalculoEmCascataSemDoacaoSucessiva() throws BadElementException, DocumentException
      {
         int linhasCabecalho = 1;
            PdfPTable tabela = null;
            PdfPCell celula = null;
            if(gIAITCDDoacaoVo.getDataCriacao().before(retornaDataLimite())){
               float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
               tabela = instanciarTabelaPadrao(colunas.length, colunas);
               celula = new PdfPCell(new Phrase("Cálculo do Demonstrativo por Beneficiário".toUpperCase(), fontMediaBold));
               celula.setColspan(colunas.length);
            }else{
               float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, };
               tabela = instanciarTabelaPadrao(colunas.length, colunas);
               celula = new PdfPCell(new Phrase("Cálculo do Demonstrativo por Beneficiário".toUpperCase(), fontMediaBold));
               celula.setColspan(colunas.length);
            }               
              celula.setBackgroundColor(Color.LIGHT_GRAY);
              celula.setHorizontalAlignment(Element.ALIGN_CENTER);
              celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
              celula.setPadding(DEFAULT_PADDING);    
              tabela.addCell(celula); 
             
                 TreeMap celulasAliquota = new TreeMap();
                 if(Validador.isCollectionValida( gIAITCDDoacaoVo.getBeneficiarioVo().getCollVO()))
                 {
                    for(Iterator it = gIAITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext(); )
                    {
                       GIAITCDDoacaoBeneficiarioVo beneficiarioAtual = (GIAITCDDoacaoBeneficiarioVo) it.next();
                       if(Validador.isCollectionValida(beneficiarioAtual.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO()))
                       {
                          for(Iterator itAliquotas = beneficiarioAtual.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().iterator(); itAliquotas.hasNext(); )
                          {
                             GIAITCDDoacaoBeneficiarioAliquotaVo aliquota = (GIAITCDDoacaoBeneficiarioAliquotaVo) itAliquotas.next();
                             if(!celulasAliquota.containsKey(""+aliquota.getPercentualAliquota()))
                             {
                                celulasAliquota.put(""+aliquota.getPercentualAliquota(), montaCelulaAliquota(aliquota));                                         
                             }
                          }
                       }
                    }
                 }
                 celula = new PdfPCell(new Phrase("Nome do Beneficiário", fontPequenaBold));
                 celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                 celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 celula.setBackgroundColor(Color.LIGHT_GRAY);       
                 celula.setPadding(DEFAULT_PADDING);
                 celula.setColspan(defineColspanNomeBeneficiarioPorQuantidadeAliquotas(celulasAliquota.size()));
                 tabela.addCell(celula);
                 celula = new PdfPCell(new Phrase("Valor Recebido", fontPequenaBold));
                 celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                 celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 celula.setBackgroundColor(Color.LIGHT_GRAY);                
                 celula.setPadding(DEFAULT_PADDING);
                 celula.setColspan(2);
                 tabela.addCell(celula);       
                 if(celulasAliquota != null && celulasAliquota.size() > 0)
                 {
                    Iterator it = celulasAliquota.values().iterator();
                    int i = 7;
                    while(it.hasNext() && i>0)
                    {
                       PdfPCell celulaAtual = (PdfPCell) it.next();
                       celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                       celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                       celula.setBackgroundColor(Color.LIGHT_GRAY);       
                       celula.setPadding(DEFAULT_PADDING);          
                       celulaAtual.setColspan(2);
                       tabela.addCell(celulaAtual);
                       i--;
                    }
                 }
                 
                 celula = new PdfPCell(new Phrase("Valor do ITCD", fontPequenaBold));
                 celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                 celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 celula.setBackgroundColor(Color.LIGHT_GRAY);                
                 celula.setPadding(DEFAULT_PADDING);
                 celula.setColspan(2);            
                 tabela.addCell(celula);
                 
                 celula = new PdfPCell(new Phrase("Valor a Recolher Devido", fontPequenaBold));
                 celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                 celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 celula.setBackgroundColor(Color.LIGHT_GRAY);                
                 celula.setPadding(DEFAULT_PADDING);
                 celula.setColspan(2);            
                 tabela.addCell(celula);     
                 
                 celula = new PdfPCell(new Phrase("Observação", fontPequenaBold));                  
                 celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                 celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 celula.setBackgroundColor(Color.LIGHT_GRAY);                
                 celula.setPadding(DEFAULT_PADDING);
                 celula.setColspan(2);            
                 tabela.addCell(celula);

                 if(Validador.isCollectionValida(gIAITCDDoacaoVo.getBeneficiarioVo().getCollVO()))
                 {
                    for (Iterator iteBeneficiario = gIAITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); iteBeneficiario.hasNext(); )
                    {
                       GIAITCDDoacaoBeneficiarioVo beneficiarioVo = (GIAITCDDoacaoBeneficiarioVo) iteBeneficiario.next();
                       montaCelulasBeneficiarioCascataSemDoacaoSucessiva(beneficiarioVo, tabela, celulasAliquota.size());                            
                    }           
                 }
                 
              return tabela;
      }
   
   /**
    * Método responsável por montar as células com os dados do beneficiário recebido como parâmetro, 
    * para cálculo da gia no formato cascata.
    * @param beneficiarioVo
    * @param tabela
    * @param quantidadeCelulasAliquota
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void montaCelulasBeneficiarioCascata(final GIAITCDDoacaoBeneficiarioVo beneficiarioVo, PdfPTable tabela, final int quantidadeCelulasAliquota, final Date dataCriacaoGia)
   {
      PdfPCell celula = new PdfPCell(new Phrase(beneficiarioVo.getPessoaBeneficiaria().getNomeContribuinte(), fontPequenaBold));
      celula.setHorizontalAlignment(Element.ALIGN_LEFT);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);
      celula.setColspan(defineColspanNomeBeneficiarioPorQuantidadeAliquotas(quantidadeCelulasAliquota));                     
      tabela.addCell(celula);
      celula = new PdfPCell(new Phrase(beneficiarioVo.getValorRecebidoFormatado(), fontPequenaBold));
      celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);
      celula.setColspan(2);                     
      tabela.addCell(celula);
      for (Iterator iteAliqBeneficiario = beneficiarioVo.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().iterator(); iteAliqBeneficiario.hasNext(); )
      {
         GIAITCDDoacaoBeneficiarioAliquotaVo aliquotaBeneficiario = (GIAITCDDoacaoBeneficiarioAliquotaVo) iteAliqBeneficiario.next();
         celula = new PdfPCell(new Phrase(aliquotaBeneficiario.getValorBaseCalculoFormatado(), fontPequenaBold));
         celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(2);                                 
         tabela.addCell(celula);
      }
      for(int i = beneficiarioVo.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().size(); i < quantidadeCelulasAliquota; i++)
      {
         celula = new PdfPCell(new Phrase("0,00", fontPequenaBold));
         celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(2);                        
         tabela.addCell(celula);                
      }
      
      celula = new PdfPCell(new Phrase(beneficiarioVo.getValorItcdBeneficiarioFormatado(), fontPequenaBold));
      celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);
      celula.setColspan(2);                     
      tabela.addCell(celula);
      
      if(dataCriacaoGia.before(retornaDataLimite())){      
         celula = new PdfPCell(new Phrase(beneficiarioVo.getValorItcdDoacaoSucessivaFormatado(), fontPequenaBold));
         celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(2);                           
         tabela.addCell(celula); 
      }
      
      celula = new PdfPCell(new Phrase(beneficiarioVo.getValorRecolherFormatado(), fontPequenaBold));
      celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);
      celula.setColspan(2);                           
      tabela.addCell(celula);  
      
      if(beneficiarioVo.getInfoDispensaRecolhimento() != null && beneficiarioVo.getInfoDispensaRecolhimento() == 2){         
         celula = new PdfPCell(new Phrase("Dispensa de Imposto", fontPequenaBold));
         celula.setPadding(DEFAULT_PADDING);
         celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);       
         celula.setColspan(5);
         tabela.addCell(celula);              

      }else if(beneficiarioVo.getInfoIsencao() != null && beneficiarioVo.getInfoIsencao() == 2){         
         celula = new PdfPCell(new Phrase("Isento de Imposto", fontPequenaBold));
         celula.setPadding(DEFAULT_PADDING);
         celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);       
         celula.setColspan(5);
         tabela.addCell(celula);            
      }else{
         celula = new PdfPCell(new Phrase("Imposto a Recolher", fontPequenaBold));
         celula.setPadding(DEFAULT_PADDING);
         celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);       
         celula.setColspan(5);
         tabela.addCell(celula);
      }
         
      
      
   }
   
   private void montaCelulasBeneficiarioCascataSemDoacaoSucessiva(final GIAITCDDoacaoBeneficiarioVo beneficiarioVo, PdfPTable tabela, final int quantidadeCelulasAliquota){
      PdfPCell celula = new PdfPCell(new Phrase(beneficiarioVo.getPessoaBeneficiaria().getNomeContribuinte(), fontPequenaBold));
      celula.setHorizontalAlignment(Element.ALIGN_LEFT);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);
      celula.setColspan(defineColspanNomeBeneficiarioPorQuantidadeAliquotas(quantidadeCelulasAliquota));                     
      tabela.addCell(celula);
      celula = new PdfPCell(new Phrase(beneficiarioVo.getValorRecebidoFormatado(), fontPequenaBold));
      celula.setHorizontalAlignment(Element.ALIGN_MIDDLE);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);
      celula.setColspan(2);                     
      tabela.addCell(celula);
      for (Iterator iteAliqBeneficiario = beneficiarioVo.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().iterator(); iteAliqBeneficiario.hasNext(); )
      {
         GIAITCDDoacaoBeneficiarioAliquotaVo aliquotaBeneficiario = (GIAITCDDoacaoBeneficiarioAliquotaVo) iteAliqBeneficiario.next();
         celula = new PdfPCell(new Phrase(aliquotaBeneficiario.getValorBaseCalculoFormatado(), fontPequenaBold));
         celula.setHorizontalAlignment(Element.ALIGN_MIDDLE);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(2);                                 
         tabela.addCell(celula);
      }
      for(int i = beneficiarioVo.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().size(); i < quantidadeCelulasAliquota; i++)
      {
         celula = new PdfPCell(new Phrase("0,00", fontPequenaBold));
         celula.setHorizontalAlignment(Element.ALIGN_MIDDLE);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(2);                        
         tabela.addCell(celula);                
      }
      
      celula = new PdfPCell(new Phrase(beneficiarioVo.getValorItcdBeneficiarioFormatado(), fontPequenaBold));
      celula.setHorizontalAlignment(Element.ALIGN_MIDDLE);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);
      celula.setColspan(2);                     
      tabela.addCell(celula);   
      
      celula = new PdfPCell(new Phrase(beneficiarioVo.getValorRecolherFormatado(), fontPequenaBold));
      celula.setHorizontalAlignment(Element.ALIGN_MIDDLE);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);
      celula.setColspan(2);                           
      tabela.addCell(celula); 
      
         if(beneficiarioVo.getInfoDispensaRecolhimento() != null && beneficiarioVo.getInfoDispensaRecolhimento() == 2){         
            celula = new PdfPCell(new Phrase("Dispensa de Imposto", fontPequenaBold));
            celula.setPadding(DEFAULT_PADDING);
            celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);       
            celula.setColspan(5);
            tabela.addCell(celula);              

         }else if(beneficiarioVo.getInfoIsencao() != null && beneficiarioVo.getInfoIsencao() == 2){         
            celula = new PdfPCell(new Phrase("Isento de Imposto", fontPequenaBold));
            celula.setPadding(DEFAULT_PADDING);
            celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);       
            celula.setColspan(5);
            tabela.addCell(celula);            
         }else{
            celula = new PdfPCell(new Phrase("Imposto a Recolher", fontPequenaBold));
            celula.setPadding(DEFAULT_PADDING);
            celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);       
            celula.setColspan(5);
            tabela.addCell(celula);
         }
      
   }
   
   /**
    * Método responsável por montar as células referentes à alíquota para tabela de detalhamento de cálculo do beneficiário.
    * @param aliquotaVo
    * @return PdfPCell
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private PdfPCell montaCelulaAliquota (final GIAITCDDoacaoBeneficiarioAliquotaVo aliquotaVo)
   {
      PdfPCell celulaAliquota = new PdfPCell();
      if(aliquotaVo.getPercentualAliquota() == 0)
      {
         celulaAliquota = new PdfPCell(new Phrase("Valor da Isenção", fontPequenaBold));
         celulaAliquota.setBackgroundColor(Color.LIGHT_GRAY);
         celulaAliquota.setColspan(2);
         celulaAliquota.setHorizontalAlignment(Element.ALIGN_CENTER);
         celulaAliquota.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celulaAliquota.setPadding(DEFAULT_PADDING);
      }
      else
      {
         celulaAliquota = new PdfPCell(new Phrase("Fração BC Sujeita à Alíquota de "+ aliquotaVo.getPercentualAliquotaFormatado() + "%", fontPequenaBold));
         celulaAliquota.setBackgroundColor(Color.LIGHT_GRAY);        
         celulaAliquota.setColspan(2);
         celulaAliquota.setHorizontalAlignment(Element.ALIGN_CENTER);
         celulaAliquota.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celulaAliquota.setPadding(DEFAULT_PADDING);
      }
      return celulaAliquota;     
   }
   
   /**
    * Faz o colspan das celulas no PDF de acordo com a quantidade de aliquotas.
    * 
    * 
    * @param quantidadeAlquotas
    * @return
    */
   private static int defineColspanNomeBeneficiarioPorQuantidadeAliquotas(int quantidadeAlquotas )
   {
      int colspan = 10;
      
      if(quantidadeAlquotas == 2)
         colspan = 8;
      if(quantidadeAlquotas == 3)
         colspan = 6;
      if(quantidadeAlquotas == 4)
         colspan = 4;
      if(quantidadeAlquotas == 5)
         colspan = 2;

      return colspan;
   }
   
}
