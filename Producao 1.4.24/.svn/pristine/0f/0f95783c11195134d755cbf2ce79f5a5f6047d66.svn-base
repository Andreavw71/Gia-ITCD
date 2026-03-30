package br.gov.mt.sefaz.itc.model.relatorio;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.fichaimovel.FichaImovelVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria.FichaImovelRuralBenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao.FichaImovelRuralConstrucaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura.FichaImovelRuralCulturaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho.FichaImovelRuralRebanhoVo;
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

import java.sql.SQLException;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;


public class FichaImovelRuralRelatorioGerarPDF extends AbstractGeradorPDFITCD
{
	FichaImovelRuralVo fichaImovelRuralVo;

	/**
	 * Construtor padrão da classe FichaImovelRuralRelatorioGerarPDF
	 * @implemented by Maxwell Rocha
	 * **/
	public FichaImovelRuralRelatorioGerarPDF(HttpServletRequest request, FichaImovelVo fichaImovelVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(request, fichaImovelVo, formatoPagina);
	}

	public FichaImovelRuralRelatorioGerarPDF(FichaImovelVo fichaImovelVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(fichaImovelVo, formatoPagina);
	}
	

	/**
	 * Sobrecarga do método gerarCorpoPDF que poderá ser chamada por outra classe geradora de  PDF, que por sua vez, passa por parâmetro 
	 * o objeto do tipo Document e objeto do tipo FichaImovelRuralVo .
	 * @param fichaImovelRuralVo , document
	 * @implemented by Maxwell Rocha
	 * **/
	public void gerarCorpoPDF(FichaImovelRuralVo fichaImovelRuralVo, Document document) throws BadElementException, 
			  DocumentException
	{
		this.fichaImovelRuralVo = fichaImovelRuralVo;
		gerarCorpoPDF(document);
	}

	/**
	 * Método da classe abstrata implementada para gerar o PDF.
	 * @implemented by Maxwell Rocha
	 * **/
	public void gerarCorpoPDF(Document document) throws BadElementException, DocumentException
	{
		
		Table tabelaDescricaoImovelRural = tabelaDescricaoImovelRural();
		document.add(tabelaDescricaoImovelRural);
		if(Validador.isCollectionValida(fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCollVO()))
		{
			Table tabelaCulturaImoveisRurais = tabelaCulturaImoveisRurais();
			document.add(tabelaCulturaImoveisRurais);
		}
		Table tabelaAcessoesImoveisRurais = tabelaAcessoesImoveisRurais();
		if (tabelaAcessoesImoveisRurais != null)
		{
			document.add(tabelaAcessoesImoveisRurais);
		}
		if(Validador.isCollectionValida(fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getCollVO()))
		{
			Table tabelaRebanhoImoveisRurais = tabelaRebanhoImoveisRurais();
			document.add(tabelaRebanhoImoveisRurais);
		}
		if(Validador.isCollectionValida(fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getCollVO()))		
		{
			Table tabelaConstrucoesExistentesImoveisRurais = tabelaConstrucoesExistentesImoveisRurais();
			document.add(tabelaConstrucoesExistentesImoveisRurais);
		}
		if(Validador.isCollectionValida(fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo().getCollVO()))
		{
		   Table tabelaBenfeitoriaImoveisRurais = tabelaBenfeitoriaImoveisRurais();
		   document.add(tabelaBenfeitoriaImoveisRurais);
		}
		Table tabelaValoresImoveisRurais;
      try
      {
         tabelaValoresImoveisRurais = tabelaValoresImoveisRurais();
      }
      catch (Exception e)
      {
         throw new DocumentException(e.getMessage());
      }
      document.add(tabelaValoresImoveisRurais);
		PdfPTable tabelaAssinaturaDeclaranteImoveisRurais = tabelaAssinaturaDeclarante();
		document.add(tabelaAssinaturaDeclaranteImoveisRurais);
	}

	/**
	 * Método responsável por escrever a tabela de Descrição do Imóvel Rural
	 * @return Table
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table tabelaDescricaoImovelRural() throws BadElementException
	{
		float[] colunas = { 35, 35, 15, 10, 5 };
		Table tabela = instanciarTabela(5, colunas);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultColspan(5);
		Cell celula = new Cell(new Phrase("DESCRIÇÃO DO IMÓVEL RURAL", fontMediaBold));
		celula.setColspan(5);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setColspan(5);
		tabela.addCell(new Phrase("Descrição do Bem: \n" + 
						 fichaImovelRuralVo.getBemTributavelVo().getDescricaoBemTributavel(), fontPequenaBold));
		tabela.setDefaultColspan(5);
		tabela.addCell(new Phrase("Denominação do Imovel: \n" + 
						 fichaImovelRuralVo.getDescricaoDenominacao(), fontPequenaBold));
		tabela.setDefaultColspan(5);
		tabela.addCell(new Phrase("Logradouro: \n" + 
						 fichaImovelRuralVo.getEnderecoVo().getLogradouro(), fontPequenaBold));
		tabela.setDefaultColspan(1);
		tabela.addCell(new Phrase("Ponto de Referência \n" + 
						 fichaImovelRuralVo.getEnderecoVo().getPontoReferencia(), fontPequenaBold));
		tabela.setDefaultColspan(2);	
	   tabela.addCell(new Phrase("Localidade \n" + 
	                fichaImovelRuralVo.getEnderecoVo().getCep().getLocalidade().getNomeLocalidade(), fontPequenaBold));						 
		tabela.setDefaultColspan(1);						 
		tabela.addCell(new Phrase("CEP \n" + fichaImovelRuralVo.getEnderecoVo().getCep().getCodgCep(), fontPequenaBold));
		tabela.addCell(new Phrase("UF \n" + 
						 fichaImovelRuralVo.getEnderecoVo().getCep().getLocalidade().getUf().getSiglUf(), fontPequenaBold));
		tabela.addCell(new Phrase("Quantos kms do perímetro urbano o imóvel se encontra \n " + 
						 fichaImovelRuralVo.getQuantidadeDistanciaFormatado(), fontPequenaBold));
		tabela.addCell(new Phrase("Área total do imóvel \n " + fichaImovelRuralVo.getAreaTotalFormatado(), fontPequenaBold));
		tabela.setDefaultColspan(3);
		tabela.addCell(new Phrase("Nº INDEA/ MT \n " + fichaImovelRuralVo.getNumericoIndea(), fontPequenaBold));
      //----------------------------------------------------------------------------		
	   tabela.setDefaultColspan(1);
	   tabela.addCell(new Phrase("Distância em kms até a rodovia pavimentada \n " + 
	                fichaImovelRuralVo.getDistanciaAsfaltoFormatado(), fontPequenaBold));
	   //-----------------------------------------------------------------------      
      
      tabela.setDefaultColspan(4);
		tabela.addCell(new Phrase("Código do Imóvel na Receita Federal \n " + 
						 fichaImovelRuralVo.getCodigoReceitaFederal(), fontPequenaBold));
		return tabela;
	}

	/**
	 * Método responsável por escrever a tabela de Cultura de Imóveis Rurais
	 * @return Table
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table tabelaCulturaImoveisRurais() throws BadElementException
	{
		float[] colunas = { 20, 20, 20, 20, 20 };
		Table tabela = instanciarTabela(5, colunas);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultColspan(5);
		Cell celula = new Cell(new Phrase("Cultura", fontMediaBold));
		celula.setColspan(5);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultColspan(1);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		for (Iterator iteCultura = 
					 fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCollVO().iterator(); iteCultura.hasNext(); )
		{
			FichaImovelRuralCulturaVo fichaCultura = (FichaImovelRuralCulturaVo) iteCultura.next();
			tabela.addCell(new Phrase("Cultura \n" + fichaCultura.getCulturaVo().getDescricaoCultura(), fontPequenaBold));
			tabela.addCell(new Phrase("Descrição da Cultura\n" + fichaCultura.getDescricaoComplementarCultura(), fontPequenaBold));
			tabela.addCell(new Phrase("Área Cultivada \n" + fichaCultura.getAreaCultivadaFormatado(), fontPequenaBold));
			tabela.addCell(new Phrase("Valor do Hectare\n" + fichaCultura.getValorHectareFormatado(), fontPequenaBold));
			tabela.addCell(new Phrase("Valor de Mercado \n" + fichaCultura.getValorMercadoFormatado(), fontPequenaBold));
		}
		return tabela;
	}

	/**
	 * Método responsável por escrever a tabela de Acessões de Imóveis Rurais
	 * @return Table
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table tabelaAcessoesImoveisRurais() throws BadElementException
	{
		float[] colunas = { 35, 10, 35, 20 };
		Table tabela = instanciarTabela(4, colunas);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultColspan(4);
		Cell celula = new Cell(new Phrase("Acessões", fontMediaBold));
		celula.setColspan(4);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultColspan(1);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		int linha = 0;
		tabela.addCell(new Phrase("Imóvel possui Pastagens Naturais e/ou Cultivadas ", fontPequenaBold));
		tabela.addCell(new Phrase(fichaImovelRuralVo.getSituacaoPastagem().getTextoCorrente(), fontPequenaBold));
		if (fichaImovelRuralVo.getSituacaoPastagem().getTextoCorrente() == "SIM")
		{			
			tabela.addCell(new Phrase("Tamanho das Pastagens", fontPequenaBold));
			tabela.addCell(new Phrase(String.valueOf(fichaImovelRuralVo.getAreaPastagem()), fontPequenaBold));
			tabela.setDefaultColspan(2);
			tabela.addCell(new Phrase("Valor das Pastagens", fontPequenaBold));
			tabela.addCell(new Phrase(fichaImovelRuralVo.getValorPastagemFormatado(), fontPequenaBold));
			linha++;
		}
		
		tabela.addCell(new Phrase("Imóvel possui Jazidas Minerais, Fontes de Água Radioativa, Térmicas e/ou Minerais ", fontPequenaBold));
		tabela.addCell(new Phrase(fichaImovelRuralVo.getSituacaoAcessaoNatural().getTextoCorrente(), fontPequenaBold));
		
		if (fichaImovelRuralVo.getSituacaoAcessaoNatural().getTextoCorrente() == "SIM"){
			tabela.addCell(new Phrase("Valor das acessões minerais", fontPequenaBold));
			tabela.addCell(new Phrase(fichaImovelRuralVo.getValorAcessaoNaturalFormatado(), fontPequenaBold));
			linha++;
		}
		if (linha > 0)
		{
			return tabela;
		}
		return null;
	}

	/**
	 * Método responsável por escrever a tabela de Rebanho de Imóveis Rurais
	 * @return Table
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table tabelaRebanhoImoveisRurais() throws BadElementException
	{
		float[] colunas = { 20, 20, 20, 20, 20 };
		Table tabela = instanciarTabela(5, colunas);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultColspan(5);
		Cell celula = new Cell(new Phrase("Rebanho", fontMediaBold));
		celula.setColspan(5);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultColspan(1);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		for (Iterator iteRebanho = 
					 fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getCollVO().iterator(); iteRebanho.hasNext(); )
		{
			FichaImovelRuralRebanhoVo fichaRebanho = (FichaImovelRuralRebanhoVo) iteRebanho.next();
			tabela.addCell(new Phrase("Tipo de Rebanho \n" + 
								fichaRebanho.getRebanhoVo().getDescricaoRebanho(), fontPequenaBold));
			tabela.addCell(new Phrase("Descrição do Rebanho  \n" + fichaRebanho.getDescricaoRebanho(), fontPequenaBold));
			tabela.addCell(new Phrase("Quantidade \n " + fichaRebanho.getQuantidadeRebanhoFormatado(), fontPequenaBold));
			tabela.addCell(new Phrase("Valor por Cabeça  \n" + fichaRebanho.getValorMercadoFormatado(), fontPequenaBold));
			tabela.addCell(new Phrase("Valor Total \n " + fichaRebanho.getValorTotalFormatado(), fontPequenaBold));
		}
		return tabela;
	}

	/**
	 * Método responsável por escrever a tabela de Construções Existentes de Imóveis Rurais
	 * @return Table
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table tabelaConstrucoesExistentesImoveisRurais() throws BadElementException
	{
		float[] colunas = { 50, 50 };
		Table tabela = instanciarTabela(2, colunas);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultColspan(2);
		Cell celula = new Cell(new Phrase("Construções Existentes", fontMediaBold));
		celula.setColspan(2);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.setDefaultColspan(1);
		for (Iterator iteConstrucao = 
					 fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getCollVO().iterator(); iteConstrucao.hasNext(); )
		{
			FichaImovelRuralConstrucaoVo fichaConstrucao = (FichaImovelRuralConstrucaoVo) iteConstrucao.next();
			tabela.addCell(new Phrase("Descrição da Construção  \n" + 
								fichaConstrucao.getDescricaoConstrucao(), fontPequenaBold));
		   tabela.addCell(new Phrase("Construção \n" + 
		                  fichaConstrucao.getConstrucaoVo().getDescricaoConstrucao(), fontPequenaBold));
			tabela.addCell(new Phrase("Estado de Conservação  \n " + 
								fichaConstrucao.getSituacaoEstadoConservacao().getTextoCorrente(), fontPequenaBold));
			tabela.addCell(new Phrase("Valor de Mercado  \n" + 
								fichaConstrucao.getValorMercadoFormatado(), fontPequenaBold));
		}
		return tabela;
	}

	/**
	 * Método responsável por escrever a tabela de Benfeitoria de Imóveis Rurais
	 * @return Table
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table tabelaBenfeitoriaImoveisRurais() throws BadElementException
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
		tabela.setDefaultColspan(1);
		for (Iterator iteBenfeitoria = 
					 fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo().getCollVO().iterator(); iteBenfeitoria.hasNext(); )
		{
			FichaImovelRuralBenfeitoriaVo fichaBenfeitoria = (FichaImovelRuralBenfeitoriaVo) iteBenfeitoria.next();
			tabela.addCell(new Phrase("Benfeitoria\n" + fichaBenfeitoria.getBenfeitoriaVo().getDescricaoBenfeitoria(), fontPequenaBold));
			tabela.addCell(new Phrase("Descrição da Benfeitoria\n" + fichaBenfeitoria.getDescricaoComplementarBenfeitoria(), fontPequenaBold));
		}
		return tabela;
	}

	/**
	 * Método responsável por escrever a tabela de Valores de Imóveis Rurais
	 * @return Table
	 * @implemented by Maxwell Mendes Rocha
	 * **/
	public Table tabelaValoresImoveisRurais() throws BadElementException, SQLException, ConsultaException, 
             ObjetoObrigatorioException, ParametroObrigatorioException, 
             ConexaoException
   {
		float[] colunas = { 25, 25, 25, 25 };	
		Table tabela = instanciarTabela(4, colunas);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		tabela.setDefaultColspan(4);
		Cell celula = new Cell(new Phrase("Valores", fontMediaBold));
		celula.setColspan(4);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.setDefaultColspan(1);
		tabela.addCell(new Phrase("Valor do Imóvel \n" + 
						 (fichaImovelRuralVo.getValorMercadoImovel() > 0 ? fichaImovelRuralVo.getValorMercadoImovelFormatado() : "0,00") , fontPequenaBold));
		tabela.addCell(new Phrase("Valor de mercado das máquinas agrícolas \n " + 
						 (fichaImovelRuralVo.getValorMaquinaEquipamento() > 0 ? fichaImovelRuralVo.getValorMaquinaEquipamentoFormatado() : "0,00"), fontPequenaBold));
		tabela.addCell(new Phrase("Outros valores \n" +(fichaImovelRuralVo.getValorOutro() > 0 ? fichaImovelRuralVo.getValorOutroFormatado() : "0,00"), fontPequenaBold));
		tabela.addCell(new Phrase("Valor Venal do ITR \n" + (fichaImovelRuralVo.getValorITR() > 0 ? fichaImovelRuralVo.getValorITRFormatado() : "0,00"), fontPequenaBold));
		tabela.addCell(new Phrase("Valor Total de Culturas \n" + 
						(fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getValorTotalCulturas() > 0 ? fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getValorTotalCulturasFormatado() : "0,00"), fontPequenaBold));
		tabela.addCell(new Phrase("Valor das Acessões \n" + 
						 (fichaImovelRuralVo.getValorAcessaoNatural() > 0 ? fichaImovelRuralVo.getValorAcessaoNaturalFormatado() : "0,00"), fontPequenaBold));
		tabela.addCell(new Phrase("Valor das Pastagens \n" + 
						 (fichaImovelRuralVo.getValorPastagem() > 0 ? fichaImovelRuralVo.getValorPastagemFormatado() : "0,00"), fontPequenaBold));
		tabela.addCell(new Phrase("Valor Total de Rebanho \n" + 
						 (fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getValorTotalRebanhos() > 0 ? fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getValorTotalRebanhosFormatado() : "0,00"), fontPequenaBold));
		tabela.addCell(new Phrase("Valor total das construções existentes no imóvel \n" + 
						 (fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getValorTotalBens() > 0 ? fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getValorTotalBensFormatado() : "0,00"), fontPequenaBold));
		tabela.addCell(new Phrase("Valor total de mercado  \n" + 
						 fichaImovelRuralVo.getValorTotalMercadoFormatado(), fontPequenaBold));
		if(fichaImovelRuralVo.getValorTotalIsentos() > 0)
		{
		   tabela.addCell(new Phrase("Valor Isento / Não Incidência\n" + fichaImovelRuralVo.getValorTotalIsentosFormatado(), fontPequenaBold));
		}
		tabela.addCell(new Phrase("Valor Tributável \n" + 
						 fichaImovelRuralVo.getValorTributavelFormatado(), fontPequenaBold));
		return tabela;
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

}
