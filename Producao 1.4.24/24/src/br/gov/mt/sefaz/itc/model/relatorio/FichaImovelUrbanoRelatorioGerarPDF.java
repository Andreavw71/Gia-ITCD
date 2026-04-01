package br.gov.mt.sefaz.itc.model.relatorio;

import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.fichaimovel.FichaImovelVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.benfeitoria.FichaImovelUrbanoBenfeitoriaVo;
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

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;


public class FichaImovelUrbanoRelatorioGerarPDF extends AbstractGeradorPDFITCD
{
	public FichaImovelUrbanoVo fichaImovelUrbanoVo;

	/**
	 * Construtor padrão da classe FichaImovelUrbanoRelatorioGerarPDF
	 * @implemented by Maxwell Rocha
	 * **/
	public FichaImovelUrbanoRelatorioGerarPDF(HttpServletRequest request, FichaImovelVo fichaImovelVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(request, fichaImovelVo, formatoPagina);
	}

	public FichaImovelUrbanoRelatorioGerarPDF(FichaImovelVo fichaImovelVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(fichaImovelVo, formatoPagina);
	}

	
	/**
	 * Método da classe abstrata implementada para gerar o PDF.
	 * @implemented by Maxwell Rocha
	 * **/
	public void gerarCorpoPDF(Document document) throws BadElementException, DocumentException
	{
		Table tabelaDescricaoImovelUrbano = tabelaDescricaoImovelUrbano();
		document.add(tabelaDescricaoImovelUrbano);
		Table tabelaCaracteristicaImovelUrbano = tabelaCaracteristicaImovelUrbano();
		document.add(tabelaCaracteristicaImovelUrbano);
		Table tabelaConstrucaoImovelUrbano = tabelaConstrucaoImovelUrbano();
		document.add(tabelaConstrucaoImovelUrbano);
		Table tabelaAcessoImovelUrbano = tabelaAcessoImovelUrbano();
		document.add(tabelaAcessoImovelUrbano);
		Table tabelaBenfeitoriaFichaImovelUrbano = tabelaBenfeitoriaFichaImovelUrbano();
		document.add(tabelaBenfeitoriaFichaImovelUrbano);
		Table tabelaValoresFichaImovelUrbano = tabelaValoresFichaImovelUrbano();
		document.add(tabelaValoresFichaImovelUrbano);
		PdfPTable tabelaAssinaturaImoveisUrbano = tabelaAssinaturaDeclarante();
		document.add(tabelaAssinaturaImoveisUrbano);
	}

	/**
	 * Sobrecarga do método gerarCorpoPDF que poderá ser chamada por outra classe geradora de  PDF, que por sua vez, passa por parâmetro 
	 * o objeto do tipo Document e objeto do tipo FichaImovelUrbanoVo .
	 * @param fichaImovelUrbanoVo , Document
	 * @implemented by Maxwell Rocha
	 * **/
	public void gerarCorpoPDF(FichaImovelUrbanoVo fichaImovelUrbanoVo, Document document) throws BadElementException, 
			  DocumentException
	{
		this.fichaImovelUrbanoVo = fichaImovelUrbanoVo;
		gerarCorpoPDF(document);
	}


	/**
	 * Método criado para instanciar um objeto Table(Itext) e setar as configurações utilizadas para cada tabela deste pdf.
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table instanciarTabela(int colunaTabelas, float[] larguraColuna) throws BadElementException
	{
		Table tabela = new Table(colunaTabelas);
		tabela.setOffset(3);
		tabela.setWidths(larguraColuna);
		tabela.setSpaceInsideCell(ESPACO_INTERNO_CELULA);
		tabela.setSpaceBetweenCells(ESPACO_ENTRE_CELULAS);
		tabela.setWidth(LARGURA_RELATORIO);
		tabela.setPadding(1);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
		return tabela;
	}

	/**
	 * Método que escreve uma tabela de Imóvel Urbano.
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table tabelaDescricaoImovelUrbano() throws BadElementException
	{
		float[] colunas = { 25, 55, 20 };
		Table tabela = instanciarTabela(3, colunas);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultColspan(3);
		Cell celula = new Cell(new Phrase("Dados do Imóvel Urbano", fontMediaBold));
		celula.setColspan(3);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setColspan(3);
		tabela.addCell(new Phrase("Descrição do Bem Tributável \n" + 
						 fichaImovelUrbanoVo.getBemTributavelVo().getDescricaoBemTributavel(), fontPequenaBold));
		tabela.setDefaultColspan(1);
		tabela.addCell(new Phrase("Tipo de Logradouro \n" + 
						 fichaImovelUrbanoVo.getEnderecoVo().getDomnTipoLogradouro().getTextoCorrente() , fontPequenaBold));
		tabela.addCell(new Phrase("Logradouro \n" + 
						 fichaImovelUrbanoVo.getEnderecoVo().getLogradouro(), fontPequenaBold));
		tabela.addCell(new Phrase("Número \n " + 
						 fichaImovelUrbanoVo.getEnderecoVo().getNumrLogradouro(), fontPequenaBold));
		tabela.addCell(new Phrase("Bairro \n" + fichaImovelUrbanoVo.getEnderecoVo().getBairro(), fontPequenaBold));
		tabela.setDefaultColspan(2);
		tabela.addCell(new Phrase("Ponto de Referência \n" + 
						 fichaImovelUrbanoVo.getEnderecoVo().getPontoReferencia(), fontPequenaBold));
		tabela.setDefaultColspan(3);
		tabela.addCell(new Phrase("Complemento \n" + 
						 fichaImovelUrbanoVo.getEnderecoVo().getComplemento(), fontPequenaBold));
		tabela.setDefaultColspan(1);
		tabela.addCell(new Phrase("CEP \n" + 
						 fichaImovelUrbanoVo.getEnderecoVo().getCep().getCodgCep(), fontPequenaBold));
		tabela.addCell(new Phrase("Localidade \n" + 
						 fichaImovelUrbanoVo.getEnderecoVo().getCep().getLocalidade().getNomeLocalidade(), fontPequenaBold));
		tabela.addCell(new Phrase("UF \n" + 
						 fichaImovelUrbanoVo.getEnderecoVo().getCep().getLocalidade().getUf().getSiglUf(), fontPequenaBold));
		return tabela;
	}

	/**
	 * Método que escreve uma tabela de Característica do Imóvel.
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table tabelaCaracteristicaImovelUrbano() throws BadElementException
	{
		float[] colunas = { 25, 25, 25, 25 };
		Table tabela = instanciarTabela(4, colunas);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultColspan(4);
		Cell celula = new Cell(new Phrase("Características do Imóvel", fontMediaBold));
		celula.setColspan(4);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.setDefaultColspan(1);
		tabela.addCell(new Phrase("Tipo do Bem \n " + 
						 fichaImovelUrbanoVo.getBemTributavelVo().getBemVo().getDescricaoTipoBem(), fontPequenaBold));
		tabela.addCell(new Phrase("Estado de Conservação \n " + 
						 fichaImovelUrbanoVo.getEstadoConservacao().getTextoCorrente(), fontPequenaBold));
		tabela.addCell(new Phrase("Área Total \n" + fichaImovelUrbanoVo.getQuantidadeAreaTotalFormatado(), fontPequenaBold));
		tabela.addCell(new Phrase("Área Construida \n" + 
						 fichaImovelUrbanoVo.getQuantidadeAreaConstruidaFormatado(), fontPequenaBold));
		return tabela;
	}

	/**
	 * Método que escreve uma tabela de Construção.
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table tabelaConstrucaoImovelUrbano() throws BadElementException
	{
		float[] colunas = { 100 };
		Table tabela = instanciarTabela(1, colunas);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		Cell celula = new Cell(new Phrase("Construção", fontMediaBold));
		celula.setColspan(1);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.setDefaultColspan(1);
		tabela.addCell(new Phrase("Tipo de Construção \n" + 
						 fichaImovelUrbanoVo.getConstrucaoVo().getDescricaoConstrucao(), fontMediaBold));
		return tabela;
	}

	/**
	 * Método que escreve uma tabela de Acesso da Ficha de Imovel Urbano.
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table tabelaAcessoImovelUrbano() throws BadElementException
	{
		float[] colunas = { 100 };
		Table tabela = instanciarTabela(1, colunas);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		Cell celula = new Cell(new Phrase("Acesso", fontMediaBold));
		celula.setColspan(1);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.setDefaultColspan(1);
		tabela.addCell(new Phrase("Tipo de Acesso \n " + 
						 fichaImovelUrbanoVo.getTipoAcesso().getTextoCorrente(), fontPequenaBold));
		return tabela;
	}

	/**
	 * Método que escreve uma tabela de Benfeitoria da Ficha de Imovel Urbano.
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table tabelaBenfeitoriaFichaImovelUrbano() throws BadElementException
	{
		float[] colunas = { 50, 50 };
		Table tabela = instanciarTabela(2, colunas);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
	   tabela.setDefaultColspan(2);
		Cell celula = new Cell(new Phrase("Benfeitoria", fontMediaBold));
	   celula.setColspan(2);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		for (Iterator iteBenfeitoria = fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().getCollVO().iterator(); iteBenfeitoria.hasNext(); )
		{
			FichaImovelUrbanoBenfeitoriaVo imovelBenfeitoriaAtual = (FichaImovelUrbanoBenfeitoriaVo) iteBenfeitoria.next();
			tabela.addCell(new Phrase("Benfeitoria: \n" + imovelBenfeitoriaAtual.getBenfeitoriaVo().getDescricaoBenfeitoria(), fontPequenaBold));
         tabela.addCell(new Phrase("Descrição da Benfeitoria: \n" + imovelBenfeitoriaAtual.getDescricaoComplementarBenfeitoria(), fontPequenaBold));
		}
		return tabela;
	}

	/**
	 * Método que escreve uma tabela de Valores da Ficha de Imovel Urbano.
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table tabelaValoresFichaImovelUrbano() throws BadElementException
	{
		float[] colunas = {30, 25, 20, 20 };
		Table tabela = instanciarTabela(4, colunas);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultColspan(3);
		Cell celula = new Cell(new Phrase("Valores", fontMediaBold));
		celula.setColspan(4);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.setDefaultColspan(1);
      String valorTotalFormatado = "";
      String valorPercentualFormatado = "";
      if(fichaImovelUrbanoVo.getValorInfomado() > 0)
      {
         valorTotalFormatado = fichaImovelUrbanoVo.getValorTotalFormatado();
      }
      else
      {
         valorTotalFormatado = fichaImovelUrbanoVo.getValorMercadoTotalFormatado();
      }
      
      if(fichaImovelUrbanoVo.getValorPercentualTransmitido() > 0)
      {
         valorPercentualFormatado = fichaImovelUrbanoVo.getValorPercentualTransmitidoFormatado();
      }         
	   tabela.addCell(new Phrase("Valor do Percentual Transmitido do Imóvel: \n" + valorPercentualFormatado +" %", fontPequenaBold)); 
		tabela.addCell(new Phrase("Valor de mercado: \n" + valorTotalFormatado, fontPequenaBold));
		tabela.addCell(new Phrase("Valor Venal (IPTU): \n" + fichaImovelUrbanoVo.getValorVenalIptuFormatado(), fontPequenaBold));
		tabela.addCell(new Phrase("Valor Tributável: \n" + fichaImovelUrbanoVo.getValorTributavelFormatado(), fontPequenaBold));
		return tabela;
	}
}
