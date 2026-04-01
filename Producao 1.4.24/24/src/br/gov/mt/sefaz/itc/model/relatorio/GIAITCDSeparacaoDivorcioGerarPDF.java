package br.gov.mt.sefaz.itc.model.relatorio;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.conjuge.ConjugeBemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.aliquota.GIAITCDDoacaoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.aliquota.GIAITCDSeparacaoDivorcioAliquotaVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento;
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

import java.awt.Color;

import java.util.Iterator;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;


/**
 * Relatório Especificado pelo Caso de Uso GIAITCD - Separação/Divórcio/Padilha.
 * 
 * @author Maxwell Mendes Rocha / Wendell Pereira de Farias - Fev/2008
 * @version $Revision: 1.3 $
 */
public class GIAITCDSeparacaoDivorcioGerarPDF extends AbstractGeradorPDFITCD
{
	//Associação com a GIAITCD
	GIAITCDSeparacaoDivorcioVo gIAITCDSeparacaoDivorcioVo = (GIAITCDSeparacaoDivorcioVo) getEntidadeVo();

	public GIAITCDSeparacaoDivorcioGerarPDF(HttpServletRequest request, GIAITCDVo giaItcdVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(request, giaItcdVo, formatoPagina);
	}

	public GIAITCDSeparacaoDivorcioGerarPDF(GIAITCDVo giaItcdVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
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
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void gerarCorpoPDF(Document document) throws BadElementException, DocumentException, 
			  ObjetoObrigatorioException
	{
		float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, };
		//Variaveis auxiliares na validação.                
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
		PdfPCell cell = new PdfPCell(new Phrase("Nº GIA - ITCD", fontGrandeBold));
	   cell.setColspan(colunas.length);
	   cell.setPadding(DEFAULT_PADDING);
	   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		cell.setBackgroundColor(Color.LIGHT_GRAY);
	   tabela.addCell(cell);
		//Consulta a tabela de Configuração gerencias de parametros.
		cell = new PdfPCell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_GIA_SEPARACAO) + "\n\n", fontMedia));
	   cell.setColspan(colunas.length);
	   cell.setPadding(DEFAULT_PADDING);
	   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   tabela.addCell(cell);
		document.add(tabela);
		//Retorna a tabela do declarante
		PdfPTable tabelaDeclarante = tabelaDeclarante();
		document.add(tabelaDeclarante);
		PdfPTable tabelaProcesso = tabelaProcesso();
		document.add(tabelaProcesso);
		
		//Retorna a tabela do procurador
		if(Validador.isNumericoValido(gIAITCDSeparacaoDivorcioVo.getProcuradorVo().getNumrContribuinte()))
		{
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
		PdfPTable tabelaConjuge1 = tabelaConjuge1();
		if (tabelaConjuge1 != null)
		{
			document.add(tabelaConjuge1);
		}
		PdfPTable tabelaBensConjuge1 = tabelaBensConjuge1();
		if (tabelaBensConjuge1 != null)
		{
			document.add(tabelaBensConjuge1);
		}
		PdfPTable tabelaConjuge2 = tabelaConjuge2();
		if (tabelaConjuge2 != null)
		{
			document.add(tabelaConjuge2);
		}
		PdfPTable tabelaBensConjuge2 = tabelaBensConjuge2();
		if (tabelaBensConjuge2 != null)
		{
			document.add(tabelaBensConjuge2);
		}
		//Retorna a tabela de demonstrativos de calculo
		PdfPTable tabelaDemonstrativoCalculo = tabelaDemonstrativoCalculo();
		document.add(tabelaDemonstrativoCalculo);
		PdfPTable tabelaTotalizacaoConjuge = tabelaTotalizacaoConjuge();
		document.add(tabelaTotalizacaoConjuge);
		PdfPTable tabelaResumoExplicativo = tabelaResumoExplicativo();
		document.add(tabelaResumoExplicativo);
		//Retorna a tabela de prazo da declaração
		PdfPTable tabelaPrazoDeclaracao = tabelaObservacao(gIAITCDSeparacaoDivorcioVo,true,"Separação/Divórcio/Partilha");
		document.add(tabelaPrazoDeclaracao);
		//Retorna a tabela de assinatura do declarante
		PdfPTable tabelaAssinaturaDeclarante = tabelaAssinaturaDeclarante();
		document.add(tabelaAssinaturaDeclarante);
		//Retorna a tabela de autencidade
		PdfPTable tabelaAutenticidade = tabelaAutenticidade(gIAITCDSeparacaoDivorcioVo);
		document.add(tabelaAutenticidade);
		for (Iterator iteBemTributavel = 
					 gIAITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().iterator(); iteBemTributavel.hasNext(); )
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
	 * Método responsável por escrever a tabela de Declarante/Doação
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Maxwell Mendes Rocha
	 * @implemented by Wendell Pereira de Farias
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaDeclarante() throws BadElementException, DocumentException
	{
		return tabelaPessoa("Declarante", "do Declarante", gIAITCDSeparacaoDivorcioVo.getResponsavelVo() );
	}
	/**
	 * Método responsável por montar a tabela com os dados do processo de separação.
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaProcesso() throws BadElementException, DocumentException
	{
		float[] colunas = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
	   PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
	   tabela.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	   tabela.setTotalWidth(LARGURA_RELATORIO);
	   PdfPCell celula = new PdfPCell(new Phrase("Processo".toUpperCase(), fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);
	   tabela.setHeaderRows(1);
	   celula = new PdfPCell(new Phrase("Data de Casamento: \n" + gIAITCDSeparacaoDivorcioVo.getDataCasamentoFormatada(), fontPequenaBold));
	   celula.setColspan(5);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Regime de Casamento: \n" + gIAITCDSeparacaoDivorcioVo.getRegimeCasamento().getTextoCorrente(), fontPequenaBold));
		celula.setColspan(5);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Número do Processo de Separação: \n" + gIAITCDSeparacaoDivorcioVo.getNumeroProcessoFormatado(), fontPequenaBold));
	   celula.setColspan(5);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Data da Separação: \n" + gIAITCDSeparacaoDivorcioVo.getDataSeparacaoFormatada(), fontPequenaBold));
	   celula.setColspan(5);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
		return tabela;
	}

	/**
	 * Método responsável por escrever a tabela de Procurador.
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Maxwell Mendes Rocha
	 * @implemented by Wendell Pereira de Farias
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaProcurador() throws BadElementException, DocumentException
	{
		return tabelaPessoa("Procurador","do Procurador",gIAITCDSeparacaoDivorcioVo.getProcuradorVo());
	}

	/**
	 * Método responsável por escrever a tabela de Natureza da Operação/Doação
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by Maxwell Mendes Rocha
	 * @implemented by Wendell Pereira de Farias
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaNaturezaOperacao() throws BadElementException, DocumentException
	{
		float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
		//Validação valores utilizado no relatório
		String descricaoNaturezaOperacao = GIAITCDSeparacaoDivorcioVo.STRING_VAZIA;
		//Valida GIA
		if (gIAITCDSeparacaoDivorcioVo != null)
		{
			//Retorna valor Natureza Operação.
			if (gIAITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo() != null)
			{
				if (Validador.isStringValida(gIAITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().getDescricaoNaturezaOperacao()))
				{
					descricaoNaturezaOperacao = 
										 gIAITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().getDescricaoNaturezaOperacao();
				}
			}
		}
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
		tabela.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		tabela.setTotalWidth(LARGURA_RELATORIO);
		PdfPCell celula = new PdfPCell(new Phrase("Natureza da Operação".toUpperCase(), fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);
	   tabela.setHeaderRows(1);
		celula = new PdfPCell(new Phrase(descricaoNaturezaOperacao, fontPequenaBold));
		celula.setColspan(20);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   tabela.addCell(celula);
		return tabela;
	}

	/**
	 * Método responsável por escrever a tabela de Bens/GIA
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by Maxwell Mendes Rocha
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaBens() throws BadElementException, DocumentException
	{
		return tabelaBens("");
	}

	/**
	 * Método responsável por escrever a tabela de Bens/GIA
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by Maxwell Mendes Rocha
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaBens(String labelValorBem) throws BadElementException, DocumentException
	{
   
	   if((gIAITCDSeparacaoDivorcioVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_3)) || (gIAITCDSeparacaoDivorcioVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_4))){
      
		float[] colunas = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
		tabela.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		tabela.setTotalWidth(LARGURA_RELATORIO);
		PdfPCell celula = new PdfPCell(new Phrase("Bens".toUpperCase(), fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);
		boolean exibeBemParticular = false;
		if(gIAITCDSeparacaoDivorcioVo.getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS))
		{
		   celula = new PdfPCell(new Phrase("Classificação", fontPequenaBold));
		   celula.setColspan(4);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);			
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Tipo", fontPequenaBold));
		   celula.setColspan(3);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);						
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Descrição", fontPequenaBold));
		   celula.setColspan(4);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);						
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Valor Declarado Pelo Contribuinte", fontPequenaBold));
		   celula.setColspan(2);
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
         
		   celula = new PdfPCell(new Phrase("Bem Particular", fontPequenaBold));
		   celula.setColspan(3);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);						
		   tabela.addCell(celula);	
		   exibeBemParticular = true;        
         
		}
		else
		{
		   celula = new PdfPCell(new Phrase("Classificação", fontPequenaBold));
		   celula.setColspan(4);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);						
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Tipo", fontPequenaBold));
		   celula.setColspan(3);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);						
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Descrição", fontPequenaBold));
		   celula.setColspan(4);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);						
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Valor Declarado Pelo Contribuinte", fontPequenaBold));
		   celula.setColspan(2);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setBackgroundColor(Color.LIGHT_GRAY);                
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Valor Arbitrado", fontPequenaBold));
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
		}
	   tabela.setHeaderRows(2);		
		int linha = 0;
		//Verifica se a coleção de bens existe
		if (gIAITCDSeparacaoDivorcioVo.getBemTributavelVo().isExisteCollVO())
		{
			linha++;
			Iterator it = gIAITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().iterator();
			while (it.hasNext())
			{
				BemTributavelVo bem = (BemTributavelVo) it.next();
				if(exibeBemParticular)
				{
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
				   celula.setColspan(4);
				   tabela.addCell(celula); 
				   celula = new PdfPCell(new Phrase(bem.getValorInformadoFormatado(), fontPequenaBold));
				   celula.setColspan(2);
				   celula.setPadding(DEFAULT_PADDING);
				   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
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
               
				   celula = new PdfPCell(new Phrase(bem.getBemParticular().getTextoCorrente(), fontPequenaBold));
				   celula.setPadding(DEFAULT_PADDING);
				   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
				   celula.setColspan(3);
				   tabela.addCell(celula);					
				}
				else
				{
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
				   celula.setColspan(4);
				   tabela.addCell(celula);                
				   celula = new PdfPCell(new Phrase(bem.getValorInformadoFormatado(), fontPequenaBold));
				   celula.setPadding(DEFAULT_PADDING);
				   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
				   celula.setColspan(2);
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
				}	
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
		   celula = new PdfPCell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_BENS_DIREITOS), fontPequenaBold));
		   celula.setColspan(20);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		   tabela.addCell(celula);                   			
		}
		//Verifica se existe tabela
		if (linha > 0)
		{
			return tabela;
		}
      
      }else if(gIAITCDSeparacaoDivorcioVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_2)){
      
         float[] colunas = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
         PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
         tabela.setHorizontalAlignment(Element.ALIGN_MIDDLE);
         tabela.setTotalWidth(LARGURA_RELATORIO);
         PdfPCell celula = new PdfPCell(new Phrase("Bens".toUpperCase(), fontMediaBold));
         celula.setColspan(colunas.length);
         celula.setBackgroundColor(Color.LIGHT_GRAY);
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setPadding(DEFAULT_PADDING);
         tabela.addCell(celula);
         boolean exibeBemParticular = false;
         if(gIAITCDSeparacaoDivorcioVo.getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS))
         {
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
            celula = new PdfPCell(new Phrase("Bem Particular", fontPequenaBold));
            celula.setColspan(3);
            celula.setPadding(DEFAULT_PADDING);
            celula.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setBackgroundColor(Color.LIGHT_GRAY);                
            tabela.addCell(celula); 
            exibeBemParticular = true;   
         }
         else
         {
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
            celula = new PdfPCell(new Phrase("Valor Declarado Pelo Contribuinte", fontPequenaBold));
            celula.setColspan(3);
            celula.setPadding(DEFAULT_PADDING);
            celula.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setBackgroundColor(Color.LIGHT_GRAY);                
            tabela.addCell(celula);
            celula = new PdfPCell(new Phrase("Valor Arbitrado", fontPequenaBold));
            celula.setColspan(3);
            celula.setPadding(DEFAULT_PADDING);
            celula.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setBackgroundColor(Color.LIGHT_GRAY);                
            tabela.addCell(celula);
         }
         tabela.setHeaderRows(2);      
         int linha = 0;
         //Verifica se a coleção de bens existe
         if (gIAITCDSeparacaoDivorcioVo.getBemTributavelVo().isExisteCollVO())
         {
            linha++;
            Iterator it = gIAITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().iterator();
            while (it.hasNext())
            {
               BemTributavelVo bem = (BemTributavelVo) it.next();
               if(exibeBemParticular)
               {
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
                  celula = new PdfPCell(new Phrase(bem.getValorInformadoFormatado(), fontPequenaBold));
                  celula.setColspan(3);
                  celula.setPadding(DEFAULT_PADDING);
                  celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
                  tabela.addCell(celula); 
                  celula = new PdfPCell(new Phrase(bem.getValorMercadoFormatado(), fontPequenaBold));
                  celula.setPadding(DEFAULT_PADDING);
                  celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
                  celula.setColspan(3);
                  tabela.addCell(celula);          
                  celula = new PdfPCell(new Phrase(bem.getBemParticular().getTextoCorrente(), fontPequenaBold));
                  celula.setPadding(DEFAULT_PADDING);
                  celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                  celula.setColspan(3);
                  tabela.addCell(celula);             
               }
               else
               {
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
               }  
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
            celula = new PdfPCell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_BENS_DIREITOS), fontPequenaBold));
            celula.setColspan(20);
            celula.setPadding(DEFAULT_PADDING);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            tabela.addCell(celula);                            
         }
         //Verifica se existe tabela
         if (linha > 0)
         {
            return tabela;
         }
      
      }
      
		return null;
	}

	/**
	 * Método responsável por escrever a tabela com os Dados do Conjuge1
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by Lucas Nascimento
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaConjuge1() throws BadElementException, DocumentException
	{
		if (!Validador.isNumericoValido(gIAITCDSeparacaoDivorcioVo.getPessoaConjuge1().getNumrContribuinte()))
		{
			return null;
		}
		float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
		tabela.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		tabela.setTotalWidth(LARGURA_RELATORIO);
		PdfPCell celula = new PdfPCell(new Phrase("Dados do Declarante(Cônjuge 1)".toUpperCase(), fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);
	   tabela.setHeaderRows(1);
		celula = new PdfPCell(new Phrase("Declarante(nome): \n" + 
						 toString(gIAITCDSeparacaoDivorcioVo.getPessoaConjuge1().getNomeContribuinte()), fontPequenaBold));
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		celula.setColspan(10);
	   tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("CPF: \n" + 
						 StringUtil.formataCPF(toString(gIAITCDSeparacaoDivorcioVo.getPessoaConjuge1().getNumrDocumento())), fontPequenaBold));
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setColspan(10);
	   tabela.addCell(celula);
		return tabela;
	}

	/**
	 * Método responsável por escrever a tabela com os Dados dos Bens Atribuídos ao Conjuge1
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by Lucas Nascimento
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaBensConjuge(String tituloTabela, ConjugeBemTributavelVo conjugeBemTributavelVo) throws BadElementException, DocumentException
	{
		if ( ! (Validador.isObjetoValido(conjugeBemTributavelVo) && conjugeBemTributavelVo.isExisteCollVO() && Validador.isStringValida(tituloTabela)))
		{
			return null;
		}
		float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
		tabela.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		tabela.setTotalWidth(LARGURA_RELATORIO);
		PdfPCell celula = new PdfPCell(new Phrase(tituloTabela, fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Tipo de Bem", fontPequenaBold));
	   celula.setColspan(6);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);             
	   tabela.addCell(celula);		
		celula = new PdfPCell(new Phrase("Descrição do Bem", fontPequenaBold));
		celula.setColspan(6);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setBackgroundColor(Color.LIGHT_GRAY);					
		tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Valor do Bem", fontPequenaBold));
	   celula.setColspan(4);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);             
	   tabela.addCell(celula);		
	   celula = new PdfPCell(new Phrase("Valor Atribuído", fontPequenaBold));
	   celula.setColspan(4);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setBackgroundColor(Color.LIGHT_GRAY);							
	   tabela.addCell(celula);
	   tabela.setHeaderRows(2);		
		for (Iterator iteConjuge1 = conjugeBemTributavelVo.getCollVO().iterator(); iteConjuge1.hasNext(); )
		{
			ConjugeBemTributavelVo conjugeBem = (ConjugeBemTributavelVo) iteConjuge1.next();
		   celula = new PdfPCell(new Phrase(conjugeBem.getBemTributavelVo().getBemVo().getDescricaoTipoBem(), fontPequenaBold));
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		   celula.setColspan(6);
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase(conjugeBem.getBemTributavelVo().getDescricaoBemTributavel(), fontPequenaBold));
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		   celula.setColspan(6);
		   tabela.addCell(celula);         
         
         if(conjugeBem.getBemTributavelVo().getConcordaComValorArbitrado().getTextoCorrente().equals("SIM")){
         
            celula = new PdfPCell(new Phrase(conjugeBem.getBemTributavelVo().getValorMercadoFormatado(), fontPequenaBold));
            celula.setPadding(DEFAULT_PADDING);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            celula.setColspan(4);
            tabela.addCell(celula);
            
         } else{
         
            celula = new PdfPCell(new Phrase(conjugeBem.getBemTributavelVo().getValorInformadoFormatado(), fontPequenaBold));
            celula.setPadding(DEFAULT_PADDING);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            celula.setColspan(4);
            tabela.addCell(celula);              
         }			
         
			celula = new PdfPCell(new Phrase(conjugeBem.getValorConjugeFormatado(), fontPequenaBold));
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
		   celula.setColspan(4);
		   tabela.addCell(celula);			
		}
		return tabela;
	}

	/**
	 * Método responsável por escrever a tabela com os Dados do Conjuge1
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by João Batista Padilha e Silva
	 * @implemented by Lucas Nascimento
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaConjuge2() throws BadElementException, DocumentException
	{
		if (!Validador.isNumericoValido(gIAITCDSeparacaoDivorcioVo.getPessoaConjuge2().getNumrContribuinte()))
		{
			return null;
		}
		return tabelaPessoa("Dados do Cônjuge 2", "do Cônjuge", gIAITCDSeparacaoDivorcioVo.getPessoaConjuge2());
	}

	/**
	 * Método responsável por escrever a tabela com os Dados dos Bens Atribuídos ao Conjuge2
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaBensConjuge1() throws BadElementException, DocumentException
	{
		if (!gIAITCDSeparacaoDivorcioVo.getConjuge2().isExisteCollVO())
		{
			return null;
		}
		return tabelaBensConjuge("Bens Cônjuge 1".toUpperCase(), gIAITCDSeparacaoDivorcioVo.getConjuge1());
	}	

	/**
	 * Método responsável por escrever a tabela com os Dados dos Bens Atribuídos ao Conjuge2
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by Lucas Nascimento
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaBensConjuge2() throws BadElementException, DocumentException
	{
		if (!gIAITCDSeparacaoDivorcioVo.getConjuge2().isExisteCollVO())
		{
			return null;
		}
		return tabelaBensConjuge("Bens Cônjuge 2".toUpperCase(), gIAITCDSeparacaoDivorcioVo.getConjuge2());
	}

   public PdfPTable tabelaDemonstrativoCalculo() throws BadElementException, DocumentException
   {
      if(gIAITCDSeparacaoDivorcioVo.getParametroLegislacaoVo().isLegislacaoCascata())
      {
        return tabelaBeneficiarioDetalharDemonstrativoCalculoEmCascata();
      }
      else
      {
        return tabelaDemonstrativoCalculoNormal(true);
      }
   }
   

	/**
	 * Método responsável por escrever a tabela Demonstrativo de Calculo/GIA
	 * @implemented by Maxwell Mendes Rocha
	 * @implemented by Wendell Pereira de Farias
	 * **/
	public PdfPTable tabelaDemonstrativoCalculoNormal(boolean exibeMensagemParametroConfiguracao) throws BadElementException, DocumentException
	{
	   float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
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
		celula = new PdfPCell(new Phrase("Lei \n" + gIAITCDSeparacaoDivorcioVo.getParametroLegislacaoVo().getNumeroLegislacaoFormatado(), fontPequenaBold));
		celula.setPadding(DEFAULT_PADDING);
		celula.setColspan(3);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Valor da UPF \n" + gIAITCDSeparacaoDivorcioVo.getValorUPFFormatado(), fontPequenaBold));
		celula.setPadding(DEFAULT_PADDING);
		celula.setColspan(3);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);    
		tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Valor Total Declaro pelo Contribuinte \n" + gIAITCDSeparacaoDivorcioVo.getBemTributavelVo().getValorTotalInformadoContribuinteFormatado(), fontPequenaBold));
	   celula.setColspan(4);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Total dos Bens Arbitrados \n" + gIAITCDSeparacaoDivorcioVo.getValorTotalBensDeclaradosFormatado(), fontPequenaBold));
		celula.setPadding(DEFAULT_PADDING);
		celula.setColspan(7);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
		tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Valor Base Cálculo Tributável \n" + gIAITCDSeparacaoDivorcioVo.getValorBaseCalculoTributavelFormatado(), fontPequenaBold));
		celula.setPadding(DEFAULT_PADDING);
		celula.setColspan(7);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
		tabela.addCell(celula);       
      
		
//		if (gIAITCDSeparacaoDivorcioVo.getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS))
//		{			
//			celula = new PdfPCell(new Phrase("Lei \n" + gIAITCDSeparacaoDivorcioVo.getParametroLegislacaoVo().getNumeroLegislacaoFormatado(), fontPequenaBold));
//			celula.setPadding(DEFAULT_PADDING);
//			celula.setColspan(2);
//			celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//			celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
//			tabela.addCell(celula);
//			celula = new PdfPCell(new Phrase("Valor da UPF \n" + gIAITCDSeparacaoDivorcioVo.getValorUPFFormatado(), fontPequenaBold));
//			celula.setPadding(DEFAULT_PADDING);
//			celula.setColspan(3);
//			celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//			celula.setVerticalAlignment(Element.ALIGN_MIDDLE);    
//			tabela.addCell(celula);
//			celula = new PdfPCell(new Phrase("Total dos bens declarados \n" + gIAITCDSeparacaoDivorcioVo.getValorTotalBensDeclaradosFormatado(), fontPequenaBold));
//			celula.setPadding(DEFAULT_PADDING);
//			celula.setColspan(5);
//			celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//			celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
//			tabela.addCell(celula);
//			celula = new PdfPCell(new Phrase("Valor Base Cálculo Tributável \n" + gIAITCDSeparacaoDivorcioVo.getValorCalculoDemonstrativoFormatado(), fontPequenaBold));
//			celula.setPadding(DEFAULT_PADDING);
//			celula.setColspan(5);
//			celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//			celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
//			tabela.addCell(celula);
//			celula = new PdfPCell(new Phrase("Valor Total Bens Particulares \n" + gIAITCDSeparacaoDivorcioVo.getValorTotalBensDeclaradosAnteriorCasamentoFormatado(), fontPequenaBold));
//			celula.setPadding(DEFAULT_PADDING);
//			celula.setColspan(5);
//			celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//			celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
//			tabela.addCell(celula);
//		}
//		else
//		{
//		   celula = new PdfPCell(new Phrase("Lei \n" + gIAITCDSeparacaoDivorcioVo.getParametroLegislacaoVo().getNumeroLegislacaoFormatado(), fontPequenaBold));
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setColspan(3);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
//		   tabela.addCell(celula);
//		   celula = new PdfPCell(new Phrase("Valor da UPF \n" + gIAITCDSeparacaoDivorcioVo.getValorUPFFormatado(), fontPequenaBold));
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setColspan(3);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);    
//		   tabela.addCell(celula);
//		   celula = new PdfPCell(new Phrase("Total dos Bens Declarados \n" + gIAITCDSeparacaoDivorcioVo.getValorTotalBensDeclaradosFormatado(), fontPequenaBold));
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setColspan(7);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
//		   tabela.addCell(celula);
//		   celula = new PdfPCell(new Phrase("Valor Base Cálculo Tributável \n" + gIAITCDSeparacaoDivorcioVo.getValorCalculoDemonstrativoFormatado(), fontPequenaBold));
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setColspan(7);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
//		   tabela.addCell(celula);		
//		}		
	   return tabela;
	}

	/**
	 * Método responsável por montar a tabela que exibe a totalização dos bens atribuídos para cada cônjuge.
	 * @return PdfPTable
	 * @throws BadElementException
	 * @throws DocumentException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public PdfPTable tabelaTotalizacaoConjuge() throws BadElementException, DocumentException
	{
	   float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
	   PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);     
	   PdfPCell celula = new PdfPCell(new Phrase("Resumo Distribuição Bens Cônjuges".toUpperCase(), fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);    
	   tabela.addCell(celula); 
		celula = new PdfPCell(new Phrase("Cônjuge", fontPequenaBold));
		celula.setColspan(10);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);		
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Valor Total Atribuído", fontPequenaBold));
	   celula.setColspan(10);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);		
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);		
		tabela.setHeaderRows(2);
		celula = new PdfPCell(new Phrase(gIAITCDSeparacaoDivorcioVo.getConjuge1().getPessoaConjuge().getNomeContribuinte(), fontPequenaBold));
	   celula.setColspan(10);
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);    
		celula = new PdfPCell(new Phrase(gIAITCDSeparacaoDivorcioVo.getValorTotalConjuge1Formatado(), fontPequenaBold));
	   celula.setColspan(10);
	   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);    		
	   celula = new PdfPCell(new Phrase(gIAITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNomeContribuinte(), fontPequenaBold));
	   celula.setColspan(10);
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);    
	   celula = new PdfPCell(new Phrase(gIAITCDSeparacaoDivorcioVo.getValorTotalConjuge2Formatado(), fontPequenaBold));
	   celula.setColspan(10);
	   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);          
	   return tabela;		
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
		float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };  
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
		PdfPCell celula = new PdfPCell(new Phrase("Resumo Explicativo".toUpperCase(), fontMediaBold));
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setColspan(colunas.length);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);
		tabela.setHeaderRows(1);
		celula = new PdfPCell(new Phrase("Contribuinte (Cônjuge) \n" + gIAITCDSeparacaoDivorcioVo.getConjugeExcesso().getPessoaConjuge().getNomeContribuinte(), fontPequenaBold));
		celula.setColspan(6);
		celula.setPadding(DEFAULT_PADDING);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
		tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Base de Cálculo Tributável \n" + gIAITCDSeparacaoDivorcioVo.getValorBaseCalculoTributavelFormatado(), fontPequenaBold));
		celula.setColspan(5);		
		celula.setPadding(DEFAULT_PADDING);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
		tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Alíquota \n" + gIAITCDSeparacaoDivorcioVo.getValorAliquotaFormatado(), fontPequenaBold));
		celula.setColspan(2);		
		celula.setPadding(DEFAULT_PADDING);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
		tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Valor do ITCD \n" + gIAITCDSeparacaoDivorcioVo.getValorITCDFormatado(), fontPequenaBold));
		celula.setColspan(3);		
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
	   tabela.addCell(celula);		
	   celula = new PdfPCell(new Phrase("Valor à Recolher Devido \n" + gIAITCDSeparacaoDivorcioVo.getValorRecolhimentoFormatado(), fontPequenaBold));
	   celula.setColspan(4);      
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
	   tabela.addCell(celula);		
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
      float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
      PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);     
      PdfPCell celula = new PdfPCell(new Phrase("DEMONSTRATIVO DE CÁLCULO ".toUpperCase(), fontMediaBold));
      celula.setColspan(colunas.length);
      celula.setBackgroundColor(Color.LIGHT_GRAY);
      celula.setHorizontalAlignment(Element.ALIGN_CENTER);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);    
      tabela.addCell(celula); 
     
         TreeMap celulasAliquota = new TreeMap();
         if(Validador.isCollectionValida( gIAITCDSeparacaoDivorcioVo.getGiaItcdSeparacaoDivorcioAliquotaVo().getCollVO()))
         {
            for(Iterator itAliquotas = gIAITCDSeparacaoDivorcioVo.getGiaItcdSeparacaoDivorcioAliquotaVo().getCollVO().iterator(); itAliquotas.hasNext(); )
            {
               GIAITCDSeparacaoDivorcioAliquotaVo aliquota = (GIAITCDSeparacaoDivorcioAliquotaVo) itAliquotas.next();
               if(!celulasAliquota.containsKey(""+aliquota.getPercentualAliquota()))
               {
                  celulasAliquota.put(""+aliquota.getPercentualAliquota(), montaCelulaAliquota(aliquota));                                         
               }
            }
         }
         celula = new PdfPCell(new Phrase("Nome", fontPequenaBold));
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setBackgroundColor(Color.LIGHT_GRAY);       
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(defineColspanNomeBeneficiarioPorQuantidadeAliquotas(celulasAliquota.size()));
         tabela.addCell(celula);
         celula = new PdfPCell(new Phrase("Base de Cálculo Tributável", fontPequenaBold));
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
         
         celula = new PdfPCell(new Phrase("Observação ", fontPequenaBold));
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setBackgroundColor(Color.LIGHT_GRAY);                
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(5);            
         tabela.addCell(celula);         
         
         montaCelulasBeneficiarioCascata( tabela, celulasAliquota.size());                            
         
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
   private void montaCelulasBeneficiarioCascata(PdfPTable tabela, final int quantidadeCelulasAliquota)
   {
      PdfPCell celula = new PdfPCell(new Phrase( gIAITCDSeparacaoDivorcioVo.getConjugeExcesso().getPessoaConjuge().getNomeContribuinte(), fontPequenaBold));
      celula.setHorizontalAlignment(Element.ALIGN_LEFT);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);
      celula.setColspan(defineColspanNomeBeneficiarioPorQuantidadeAliquotas(quantidadeCelulasAliquota));                     
      tabela.addCell(celula);
      
      celula = new PdfPCell(new Phrase(gIAITCDSeparacaoDivorcioVo.getValorBaseCalculoTributavelFormatado(), fontPequenaBold));
      celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);
      celula.setColspan(2);                     
      tabela.addCell(celula);
      
      for (Iterator iteAliqBeneficiario = gIAITCDSeparacaoDivorcioVo.getGiaItcdSeparacaoDivorcioAliquotaVo().getCollVO().iterator(); iteAliqBeneficiario.hasNext(); )
      {
         GIAITCDSeparacaoDivorcioAliquotaVo aliquotaBeneficiario = (GIAITCDSeparacaoDivorcioAliquotaVo) iteAliqBeneficiario.next();
         celula = new PdfPCell(new Phrase(aliquotaBeneficiario.getValorBaseCalculoFormatado(), fontPequenaBold));
         celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(2);                                 
         tabela.addCell(celula);
      }
      
      for(int i = gIAITCDSeparacaoDivorcioVo.getGiaItcdSeparacaoDivorcioAliquotaVo().getCollVO().size(); i < quantidadeCelulasAliquota; i++)
      {
         celula = new PdfPCell(new Phrase("0,00", fontPequenaBold));
         celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setPadding(DEFAULT_PADDING);
         celula.setColspan(2);                        
         tabela.addCell(celula);                
      }
      
      celula = new PdfPCell(new Phrase(gIAITCDSeparacaoDivorcioVo.getValorITCDFormatado(), fontPequenaBold));
      celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);
      celula.setColspan(2);                     
      tabela.addCell(celula);
      
      celula = new PdfPCell(new Phrase(gIAITCDSeparacaoDivorcioVo.getValorRecolhimentoFormatado(), fontPequenaBold));
      celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
      celula.setPadding(DEFAULT_PADDING);
      celula.setColspan(2);                           
      tabela.addCell(celula); 
      
      Integer infoDispensaRecolhimento = gIAITCDSeparacaoDivorcioVo.getConjugeExcesso().getInfoDispensaRecolhimento();
      Integer infoIsencao = gIAITCDSeparacaoDivorcioVo.getConjugeExcesso().getInfoIsencao();
      
      if(infoDispensaRecolhimento != null || infoIsencao != null){
      
         if(infoDispensaRecolhimento == 2){        
            celula = new PdfPCell(new Phrase("Dispensa de Imposto", fontPequenaBold));
            celula.setPadding(DEFAULT_PADDING);
            celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);       
            celula.setColspan(5);
            tabela.addCell(celula);             
      
         }else if(infoIsencao == 2){         
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
   
   /**
    * Método responsável por montar as células referentes à alíquota para tabela de detalhamento de cálculo do beneficiário.
    * @param aliquotaVo
    * @return PdfPCell
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private PdfPCell montaCelulaAliquota (final GIAITCDSeparacaoDivorcioAliquotaVo aliquotaVo)
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
         celulaAliquota = new PdfPCell(new Phrase(aliquotaVo.getPercentualAliquotaFormatado() + "%", fontPequenaBold));
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
      int colspan = 1;
      
      if(quantidadeAlquotas == 2)
         colspan = 10;
      if(quantidadeAlquotas == 3)
         colspan = 8;
      if(quantidadeAlquotas == 4)
         colspan = 6;
      if(quantidadeAlquotas == 5)
         colspan = 4;

      return colspan;
   }
}
