package br.gov.mt.sefaz.itc.model.relatorio;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.GIAITCDInventarioArrolamentoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota.GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnEstadoCivil;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoRenuncia;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
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

import java.util.Iterator;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;


public class InventarioArrolamentoGerarPDF extends AbstractGeradorPDFITCD
{
	GIAITCDInventarioArrolamentoVo gIAITCDInventarioArrolamentoVo = (GIAITCDInventarioArrolamentoVo) getEntidadeVo();

	/**
	 * Método da classe abstrata implementada para gerar o pdf.
	 * @implemented by Maxwell Rocha
	 * **/
	public void gerarCorpoPDF(Document document) throws ObjetoObrigatorioException, BadElementException, 
			  DocumentException
	{
		float[] colunas = { 25, 25, 25, 25 };
		PdfPTable tabela = instanciarTabelaPadrao(4, colunas);
		PdfPCell cell = new PdfPCell(new Phrase("Nº GIA - ITCD", fontGrandeBold));		
	   cell.setColspan(4);
	   cell.setPadding(DEFAULT_PADDING);
	   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   cell.setHorizontalAlignment(Element.ALIGN_CENTER);    
	   cell.setBackgroundColor(Color.LIGHT_GRAY);
	   tabela.addCell(cell);
		cell = new PdfPCell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_GIA_INVENTARIO), fontPequenaBold));
		cell.setColspan(4);
		cell.setPadding(DEFAULT_PADDING);
	   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);		
		tabela.addCell(cell);
		document.add(tabela);
		PdfPTable tabelaInventariante = tabelaInventariante();
		document.add(tabelaInventariante);
		PdfPTable tabelaDeCujus = tabelaDeCujus();
		document.add(tabelaDeCujus);			
	   PdfPTable tabelaProcesso = tabelaProcesso();
	   document.add(tabelaProcesso);
	   if(isEstadoCivilCasadoConvivente(gIAITCDInventarioArrolamentoVo))
	   {			
			PdfPTable tabelaMeeiro = tabelaMeeiro();
			document.add(tabelaMeeiro);
		}
		if(gIAITCDInventarioArrolamentoVo.getProcuradorVo().getNumrContribuinte().doubleValue() != 0)
		{
		   PdfPTable tabelaProcurador = tabelaProcurador();
		   document.add(tabelaProcurador);			
		}
		PdfPTable tabelaNaturezaOperacao = tabelaNaturezaOperacao();
		document.add(tabelaNaturezaOperacao);
		PdfPTable tabelaBens = tabelaBens();
		if (tabelaBens != null)
		{
			document.add(tabelaBens);
		}
		PdfPTable tabelaBeneficiario = tabelaBeneficiario();
		if (tabelaBeneficiario != null)
		{
			document.add(tabelaBeneficiario);
		}
		PdfPTable tabelaDemonstrativoCalculo = tabelaDemonstrativoCalculo(true);
		document.add(tabelaDemonstrativoCalculo);
		PdfPTable tabelaBeneficiarioDetalharDemonstrativo = tabelaBeneficiarioDetalharDemonstrativo();
		document.add(tabelaBeneficiarioDetalharDemonstrativo);
		PdfPTable tabelaResumoExplicativo = tabelaResumoExplicativo();
		document.add(tabelaResumoExplicativo);
		PdfPTable tabelaObservacaoInventarioArrolamento = tabelaObservacao();
		document.add(tabelaObservacaoInventarioArrolamento);
		PdfPTable tabelaAssinaturaDeclarante = tabelaAssinaturaDeclarante();
		document.add(tabelaAssinaturaDeclarante);
		PdfPTable tabelaAutenticidade = tabelaAutenticidade(gIAITCDInventarioArrolamentoVo);
		document.add(tabelaAutenticidade);
		for (Iterator iteBemTributavel = gIAITCDInventarioArrolamentoVo.getBemTributavelVo().getCollVO().iterator(); iteBemTributavel.hasNext(); )
		{
			BemTributavelVo bemTributavel = (BemTributavelVo) iteBemTributavel.next();
			bemTributavel.setGiaITCDVo(gIAITCDInventarioArrolamentoVo);
			bemTributavel.getFichaImovelVo().setBemTributavelVo(bemTributavel);
			if (bemTributavel.getFichaImovelVo() instanceof FichaImovelRuralVo)
			{
				document.newPage();
				FichaImovelRuralRelatorioGerarPDF geraPDF = new FichaImovelRuralRelatorioGerarPDF(getRequest(), bemTributavel.getFichaImovelVo(), getFormatoPagina());
				geraPDF.gerarCorpoPDF((FichaImovelRuralVo) bemTributavel.getFichaImovelVo(), document);
			}
			else if (bemTributavel.getFichaImovelVo() instanceof FichaImovelUrbanoVo)
			{
				document.newPage();
				FichaImovelUrbanoRelatorioGerarPDF geraPDF = new FichaImovelUrbanoRelatorioGerarPDF(getRequest(), bemTributavel.getFichaImovelVo(), getFormatoPagina());
				geraPDF.gerarCorpoPDF((FichaImovelUrbanoVo) bemTributavel.getFichaImovelVo(), document);
			}
		}
	}
   
   /*
   protected void configurarPdfWriter(PdfWriter writer) throws DocumentException {
           writer.setPageEvent(new MarcaDagua("SEM VALOR FISCAL"));
   }
   */

	/**
	 * Método responsável por escrever a tabela de Inventariante/GIA
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public PdfPTable tabelaInventariante() throws BadElementException, DocumentException
	{
		return tabelaPessoa("Inventariante", "do Inventariante", gIAITCDInventarioArrolamentoVo.getResponsavelVo());
	}

	/**
	 * Método responsável por escrever a tabela de De Cujus/GIA
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public PdfPTable tabelaDeCujus() throws BadElementException, DocumentException
	{
	   float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
		PdfPCell celula = new PdfPCell(new Phrase("De Cujus".toUpperCase(), fontMediaBold));		
		celula.setColspan(colunas.length);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);		
		tabela.addCell(celula);
	   tabela.setHeaderRows(1);
		//LINHA 1
		celula = new PdfPCell(new Phrase("Nome do De Cujus \n" +  gIAITCDInventarioArrolamentoVo.getPessoaDeCujus().getNomeContribuinte(), fontPequenaBold));
		celula.setColspan(13);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);		
		tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("CPF do De Cujus \n" + StringUtil.formataCPF(gIAITCDInventarioArrolamentoVo.getPessoaDeCujus().getNumrDocumento()), fontPequenaBold));
	   celula.setColspan(4);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);  
		tabela.addCell(celula);		
	   celula = new PdfPCell(new Phrase("Data de Falecimento\n" + gIAITCDInventarioArrolamentoVo.getDataFalecimentoFormatada(), fontPequenaBold));
	   celula.setColspan(3);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);  
		tabela.addCell(celula);		
		//LINHA 2
		 if(isEstadoCivilCasadoConvivente(gIAITCDInventarioArrolamentoVo))
		{
		   celula = new PdfPCell(new Phrase("Estado Civil do De Cujus \n" + gIAITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().getTextoCorrente(), fontPequenaBold));
		   celula.setColspan(10);
		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setPadding(DEFAULT_PADDING);  
		   tabela.addCell(celula);    
		   celula = new PdfPCell(new Phrase("Regime de Casamento do De Cujus \n" + gIAITCDInventarioArrolamentoVo.getRegimeCasamento().getTextoCorrente(), fontPequenaBold));
		   celula.setColspan(10);
		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setPadding(DEFAULT_PADDING);  
		   tabela.addCell(celula);    
		}
		else
		{
		   celula = new PdfPCell(new Phrase("Estado Civil do De Cujus \n" + gIAITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().getTextoCorrente(), fontPequenaBold));
		   celula.setColspan(20);
		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setPadding(DEFAULT_PADDING);  
		   tabela.addCell(celula);    
		}
		return tabela;
	}

	/**
	 * Método responsável por escrever a tabela de Processo/GIA
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public PdfPTable tabelaProcesso() throws BadElementException, DocumentException
	{
		float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
		PdfPCell celula = new PdfPCell(new Phrase("Processo".toUpperCase(), fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);		
	   tabela.addCell(celula);
	   tabela.setHeaderRows(1);
		//LINHA 1
		celula = new PdfPCell(new Phrase("Data de Inventário e Arrolamento \n" + gIAITCDInventarioArrolamentoVo.getDataInventarioFormatada(), fontPequenaBold));
		celula.setColspan(6);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Estado do Inventário\n" + gIAITCDInventarioArrolamentoVo.getUfAbertura().getDescUf(), fontPequenaBold));
	   celula.setColspan(3);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Renuncia (Sim / Não)\n" + gIAITCDInventarioArrolamentoVo.getRenuncia().getTextoCorrente(), fontPequenaBold));
	   celula.setColspan(4);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
		Phrase p = new Phrase();
		if(gIAITCDInventarioArrolamentoVo.getHerdeirosDescendentes().is(DomnSimNao.SIM))
		{
			p = new Phrase("Herdeiros (Descendentes / Ascendentes / Outros)\nDESCENDENTES",fontPequenaBold);
		}
		else if(gIAITCDInventarioArrolamentoVo.getHerdeirosAscendentes().is(DomnSimNao.SIM))
		{
			p = new Phrase("Herdeiros (Descendentes / Ascendentes / Outros)\nASCENDENTES",fontPequenaBold);
		}
		else if(gIAITCDInventarioArrolamentoVo.getHerdeirosAscendentes().is(DomnSimNao.NAO) && gIAITCDInventarioArrolamentoVo.getNumeroHerdeiros() > 0)
		{
			p = new Phrase("Herdeiros (Descendentes / Ascendentes / Outros)\nOUTROS HERDEIROS",fontPequenaBold);
		}
	   celula = new PdfPCell(p);
	   celula.setColspan(7);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);		
		if(Validador.isNumericoValido(gIAITCDInventarioArrolamentoVo.getNumeroHerdeiros()))
		{
		   celula = new PdfPCell(new Phrase("Qtde herdeiros  \n" + gIAITCDInventarioArrolamentoVo.getNumeroHerdeiros(), fontPequenaBold));
		   celula.setColspan(2);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Número do Processo Inv./Arrolamento/Cartório \n" + gIAITCDInventarioArrolamentoVo.getNumeroProcesso(), fontPequenaBold));
		   celula.setColspan(2);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Juízo/Comarca  \n" + (Validador.isStringValida(gIAITCDInventarioArrolamentoVo.getDescricaoJuizoComarca())?gIAITCDInventarioArrolamentoVo.getDescricaoJuizoComarca():"\n") , fontPequenaBold));
		   celula.setColspan(16);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		   tabela.addCell(celula);
		}
		else
		{
		   celula = new PdfPCell(new Phrase("Nº do Processo \n" + gIAITCDInventarioArrolamentoVo.getNumeroProcesso(), fontPequenaBold));
		   celula.setColspan(7);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Juízo/Comarca  \n" + (Validador.isStringValida(gIAITCDInventarioArrolamentoVo.getDescricaoJuizoComarca())?gIAITCDInventarioArrolamentoVo.getDescricaoJuizoComarca():"\n") , fontPequenaBold));
		   celula.setColspan(20);
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		   tabela.addCell(celula);
		}
		return tabela;
	}	

	/**
	 * Método responsável por escrever a tabela de Meeiro/GIA
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public PdfPTable tabelaMeeiro() throws BadElementException, DocumentException
	{
	   float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
	   PdfPCell celula = new PdfPCell(new Phrase("Cônjuge Sobrevivente (Meeiro / Outro)".toUpperCase(), fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);		
	   tabela.addCell(celula);
	   tabela.setHeaderRows(1);
		//LINHA 1
		 celula = new PdfPCell(new Phrase("Nome do cônjuge sobrevivente \n" + toString(gIAITCDInventarioArrolamentoVo.getPessoaMeeiro().getNomeContribuinte()), fontPequenaBold));
		 celula.setColspan(7);
		 celula.setPadding(DEFAULT_PADDING);
		 celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		 celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		 tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("CPF do cônjuge sobrevivente\n" + StringUtil.formataCPF(toString(gIAITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrDocumento())), fontPequenaBold));
	   celula.setColspan(6);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Logradouro \n" + toString(gIAITCDInventarioArrolamentoVo.getPessoaMeeiro().getEndereco()), fontPequenaBold));
	   celula.setColspan(7);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
		//LINHA 2
		 celula = new PdfPCell(new Phrase("Número do Logradouro \n" + toString(gIAITCDInventarioArrolamentoVo.getPessoaMeeiro().getEnderecoNumero()), fontPequenaBold));
		 celula.setColspan(3);
		 celula.setPadding(DEFAULT_PADDING);
		 celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		 celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		 tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Bairro  \n" + toString(gIAITCDInventarioArrolamentoVo.getPessoaMeeiro().getEnderecoBairro()), fontPequenaBold));
	   celula.setColspan(8);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Municipio \n" + toString(gIAITCDInventarioArrolamentoVo.getPessoaMeeiro().getMunicipio()), fontPequenaBold));
	   celula.setColspan(8);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("UF \n" + toString(gIAITCDInventarioArrolamentoVo.getPessoaMeeiro().getSiglaUF()), fontPequenaBold));
	   celula.setColspan(1);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
		return tabela;
	}

	/**
	 * Método responsável por escrever a tabela de Procurador/GIA
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public PdfPTable tabelaProcurador() throws BadElementException, DocumentException
	{
		return tabelaPessoa("Procurador", "do Procurador",gIAITCDInventarioArrolamentoVo.getProcuradorVo());
	}

	/**
	 * Método responsável por escrever a tabela de Natureza da Operação/GIA
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
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
	   celula = new PdfPCell(new Phrase("Natureza da Operação \n" + gIAITCDInventarioArrolamentoVo.getNaturezaOperacaoVo().getDescricaoNaturezaOperacao(), fontPequenaBold));
	   celula.setColspan(12);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Fração Ideal\n " + gIAITCDInventarioArrolamentoVo.getFracaoIdealFormatada() + "%", fontPequenaBold));
	   celula.setColspan(8);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   tabela.addCell(celula);       

//		if(gIAITCDInventarioArrolamentoVo.isExibeFracaoIdealBensParticulares())
//		{
//		   celula = new PdfPCell(new Phrase("Fração Ideal\n " + gIAITCDInventarioArrolamentoVo.getFracaoIdealFormatada() + "%", fontPequenaBold));
//		   celula.setColspan(4);
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   tabela.addCell(celula);
//		   celula = new PdfPCell(new Phrase("Fração Ideal Bens Particulares \n 100,00%", fontPequenaBold));
//		   celula.setColspan(4);
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   tabela.addCell(celula);			
//		}
//		else
//		{
//		   celula = new PdfPCell(new Phrase("Fração Ideal Bens em Comum \n " + gIAITCDInventarioArrolamentoVo.getFracaoIdealFormatada() + "%", fontPequenaBold));
//		   celula.setColspan(8);
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   tabela.addCell(celula);			
//		}
		return tabela;
	}

	/**
	 * Método responsável por escrever a tabela de Bens/GIA
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public PdfPTable tabelaBens(String labelValorBem) throws BadElementException, DocumentException
	{
	   if((gIAITCDInventarioArrolamentoVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_3)) || (gIAITCDInventarioArrolamentoVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_4))){
	   
      float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
	   float[] colunasSemIsencao = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
	   PdfPTable tabela = null;
      
      if(gIAITCDInventarioArrolamentoVo.getExibirIsencaoBemTributavel()){
         tabela = instanciarTabelaPadrao(colunas.length, colunas);
      }else{
         tabela = instanciarTabelaPadrao(colunasSemIsencao.length, colunasSemIsencao);
      }    
      
	   PdfPCell celula = new PdfPCell(new Phrase("Bens".toUpperCase(), fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);		
	   tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Classificação", fontPequenaBold));
		celula.setColspan(3);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);		
	   tabela.addCell(celula);       
	   celula = new PdfPCell(new Phrase("Tipo", fontPequenaBold));
	   celula.setColspan(3);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);		
	   tabela.addCell(celula);       
	   celula = new PdfPCell(new Phrase("Descrição", fontPequenaBold));
	   celula.setColspan(5);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
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
	   celula.setColspan(2);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);		
	   tabela.addCell(celula);
      
	   celula = new PdfPCell(new Phrase("Anuência valor arbitrado", fontPequenaBold));
	   celula.setColspan(2);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);                
	   tabela.addCell(celula);
      
      
      if(gIAITCDInventarioArrolamentoVo.getExibirIsencaoBemTributavel()){
      
         if(gIAITCDInventarioArrolamentoVo.isExibeFracaoIdealBensParticulares())		
         {
            celula = new PdfPCell(new Phrase("Isenção prevista em lei", fontPequenaBold));
            celula.setColspan(2);
            celula.setPadding(DEFAULT_PADDING);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula.setBackgroundColor(Color.LIGHT_GRAY);			
            tabela.addCell(celula);                		
            celula = new PdfPCell(new Phrase("Bem Particular", fontPequenaBold));
            celula.setColspan(2);
            celula.setPadding(DEFAULT_PADDING);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula.setBackgroundColor(Color.LIGHT_GRAY);	
            tabela.addCell(celula);       			
         }
         else
         {
            celula = new PdfPCell(new Phrase("Isenção prevista em lei", fontPequenaBold));
            celula.setColspan(4);
            celula.setPadding(DEFAULT_PADDING);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula.setBackgroundColor(Color.LIGHT_GRAY);			
            tabela.addCell(celula);       			
         }    
         
      }
      
	   tabela.setHeaderRows(2);
		int linha = 0;
		if (gIAITCDInventarioArrolamentoVo.getBemTributavelVo().isExisteCollVO())
		{
			linha++;
			Iterator it = gIAITCDInventarioArrolamentoVo.getBemTributavelVo().getCollVO().iterator();
			while (it.hasNext())
			{
				BemTributavelVo bem = (BemTributavelVo) it.next();			
			   celula = new PdfPCell(new Phrase(bem.getBemVo().getClassificacaoTipoBem().getTextoCorrente(), fontPequenaBold));
			   celula.setColspan(3);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);       
			   celula = new PdfPCell(new Phrase(bem.getBemVo().getDescricaoTipoBem(), fontPequenaBold));
			   celula.setColspan(3);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);       
			   celula = new PdfPCell(new Phrase(bem.getDescricaoBemTributavel(), fontPequenaBold));
			   celula.setColspan(5);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula); 
			   celula = new PdfPCell(new Phrase(bem.getValorInformadoFormatado(), fontPequenaBold));
			   celula.setColspan(2);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
			   tabela.addCell(celula);  
			   celula = new PdfPCell(new Phrase(bem.getValorMercadoFormatado(), fontPequenaBold));
			   celula.setColspan(2);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
			   tabela.addCell(celula);
            
			   celula = new PdfPCell(new Phrase(bem.getConcordaComValorArbitrado().getTextoCorrente(), fontPequenaBold));            
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
			   celula.setColspan(2);
			   tabela.addCell(celula);
            
			   if(gIAITCDInventarioArrolamentoVo.getExibirIsencaoBemTributavel()){            
               if(gIAITCDInventarioArrolamentoVo.isExibeFracaoIdealBensParticulares())
               {
                  celula = new PdfPCell(new Phrase(bem.getIsencaoPrevistaFormatada().getTextoCorrente(), fontPequenaBold));
                  celula.setColspan(2);
                  celula.setPadding(DEFAULT_PADDING);
                  celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                  tabela.addCell(celula);                   				
                  celula = new PdfPCell(new Phrase(bem.getBemParticular().getTextoCorrente(), fontPequenaBold));
                  celula.setColspan(2);
                  celula.setPadding(DEFAULT_PADDING);
                  celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                  tabela.addCell(celula);       
               }
               else
               {
                  celula = new PdfPCell(new Phrase(bem.getIsencaoPrevistaFormatada().getTextoCorrente(), fontPequenaBold));
                  celula.setColspan(4);
                  celula.setPadding(DEFAULT_PADDING);
                  celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                  tabela.addCell(celula);                               					
               }
            }
			}
		}
		if (linha > 0)
		{
			return tabela;
		}
      } else {
         float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
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
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
         celula.setBackgroundColor(Color.LIGHT_GRAY);    
         tabela.addCell(celula);       
         celula = new PdfPCell(new Phrase("Tipo", fontPequenaBold));
         celula.setColspan(4);
         celula.setPadding(DEFAULT_PADDING);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
         celula.setBackgroundColor(Color.LIGHT_GRAY);    
         tabela.addCell(celula);       
         celula = new PdfPCell(new Phrase("Descrição", fontPequenaBold));
         celula.setColspan(5);
         celula.setPadding(DEFAULT_PADDING);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
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
         celula.setColspan(4);
         celula.setPadding(DEFAULT_PADDING);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
         celula.setBackgroundColor(Color.LIGHT_GRAY);    
         tabela.addCell(celula);     
         
         if(gIAITCDInventarioArrolamentoVo.isExibeFracaoIdealBensParticulares())    
         {
            celula = new PdfPCell(new Phrase("Isenção prevista em lei", fontPequenaBold));
            celula.setColspan(3);
            celula.setPadding(DEFAULT_PADDING);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula.setBackgroundColor(Color.LIGHT_GRAY);       
            tabela.addCell(celula);                      
            celula = new PdfPCell(new Phrase("Bem Particular", fontPequenaBold));
            celula.setColspan(3);
            celula.setPadding(DEFAULT_PADDING);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula.setBackgroundColor(Color.LIGHT_GRAY); 
            tabela.addCell(celula);                
         }
         else
         {
            celula = new PdfPCell(new Phrase("Isenção prevista em lei", fontPequenaBold));
            celula.setColspan(6);
            celula.setPadding(DEFAULT_PADDING);
            celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celula.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula.setBackgroundColor(Color.LIGHT_GRAY);       
            tabela.addCell(celula);                
         }
         tabela.setHeaderRows(2);
         int linha = 0;
         if (gIAITCDInventarioArrolamentoVo.getBemTributavelVo().isExisteCollVO())
         {
            linha++;
            Iterator it = gIAITCDInventarioArrolamentoVo.getBemTributavelVo().getCollVO().iterator();
            while (it.hasNext())
            {
               BemTributavelVo bem = (BemTributavelVo) it.next();       
               celula = new PdfPCell(new Phrase(bem.getBemVo().getClassificacaoTipoBem().getTextoCorrente(), fontPequenaBold));
               celula.setColspan(4);
               celula.setPadding(DEFAULT_PADDING);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setHorizontalAlignment(Element.ALIGN_LEFT);
               tabela.addCell(celula);       
               celula = new PdfPCell(new Phrase(bem.getBemVo().getDescricaoTipoBem(), fontPequenaBold));
               celula.setColspan(4);
               celula.setPadding(DEFAULT_PADDING);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setHorizontalAlignment(Element.ALIGN_LEFT);
               tabela.addCell(celula);       
               celula = new PdfPCell(new Phrase(bem.getDescricaoBemTributavel(), fontPequenaBold));
               celula.setColspan(5);
               celula.setPadding(DEFAULT_PADDING);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setHorizontalAlignment(Element.ALIGN_LEFT);
               tabela.addCell(celula); 
               celula = new PdfPCell(new Phrase(bem.getValorInformadoFormatado(), fontPequenaBold));
               celula.setColspan(3);
               celula.setPadding(DEFAULT_PADDING);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
               tabela.addCell(celula);  
               celula = new PdfPCell(new Phrase(bem.getValorMercadoFormatado(), fontPequenaBold));
               celula.setColspan(4);
               celula.setPadding(DEFAULT_PADDING);
               celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
               celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
               tabela.addCell(celula);
               
               if(gIAITCDInventarioArrolamentoVo.isExibeFracaoIdealBensParticulares())
               {
                  celula = new PdfPCell(new Phrase(bem.getIsencaoPrevistaFormatada().getTextoCorrente(), fontPequenaBold));
                  celula.setColspan(3);
                  celula.setPadding(DEFAULT_PADDING);
                  celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                  tabela.addCell(celula);                               
                  celula = new PdfPCell(new Phrase(bem.getBemParticular().getTextoCorrente(), fontPequenaBold));
                  celula.setColspan(3);
                  celula.setPadding(DEFAULT_PADDING);
                  celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                  tabela.addCell(celula);       
               }
               else
               {
                  celula = new PdfPCell(new Phrase(bem.getIsencaoPrevistaFormatada().getTextoCorrente(), fontPequenaBold));
                  celula.setColspan(6);
                  celula.setPadding(DEFAULT_PADDING);
                  celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  celula.setHorizontalAlignment(Element.ALIGN_CENTER);
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
         }
         if (linha > 0)
         {
            return tabela;
         }     
      }
		return null;
	}
	
	/**
	 * Método responsável por escrever a tabela de Bens/GIA
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public PdfPTable tabelaBens() throws BadElementException, DocumentException
	{
		return tabelaBens("");
	}

	/**
	 * Método responsável por escrever a tabela de Beneficiário/GIA
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
		if (gIAITCDInventarioArrolamentoVo.getBeneficiarioVo().isExisteCollVO())
		{
			linha++;
			Iterator it = gIAITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().iterator();
			while (it.hasNext())
			{
				BeneficiarioVo beneficiarioVo = (BeneficiarioVo) it.next();
			   //LINHA 1
			   celula = new PdfPCell(new Phrase("Nome do Beneficiário \n" + beneficiarioVo.getPessoaBeneficiaria().getNomeContribuinte(), fontPequenaBold));
			   celula.setColspan(8);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);
			   celula = new PdfPCell(new Phrase("CPF do Beneficiário\n " + StringUtil.formataCPF(beneficiarioVo.getPessoaBeneficiaria().getNumrDocumento()), fontPequenaBold));
			   celula.setColspan(4);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);
			   celula = new PdfPCell(new Phrase("Logradouro \n" + beneficiarioVo.getPessoaBeneficiaria().getEndereco(), fontPequenaBold));
			   celula.setColspan(8);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);
			   //LINHA 2
			    celula = new PdfPCell(new Phrase("Número \n" + beneficiarioVo.getPessoaBeneficiaria().getEnderecoNumero(), fontPequenaBold));
			    celula.setColspan(2);
			    celula.setPadding(DEFAULT_PADDING);
			    celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			    celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			    tabela.addCell(celula);
			   celula = new PdfPCell(new Phrase("Complemento \n" + beneficiarioVo.getPessoaBeneficiaria().getEnderecoComplemento(), fontPequenaBold));
			   celula.setColspan(6);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);
			   celula = new PdfPCell(new Phrase("Ponto de Referência \n" + beneficiarioVo.getPessoaBeneficiaria().getPontoReferencia(), fontPequenaBold));
			   celula.setColspan(6);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);
			   celula = new PdfPCell(new Phrase("Bairro \n" + beneficiarioVo.getPessoaBeneficiaria().getEnderecoBairro(), fontPequenaBold));
			   celula.setColspan(4);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);
			   celula = new PdfPCell(new Phrase("CEP \n" + StringUtil.formataTexto(String.valueOf(beneficiarioVo.getPessoaBeneficiaria().getEnderecoCEP()), "99.999-999"), fontPequenaBold));
			   celula.setColspan(2);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);
			   //LINHA 3
			    celula = new PdfPCell(new Phrase("Município \n" + beneficiarioVo.getPessoaBeneficiaria().getMunicipio(), fontPequenaBold));
			    celula.setColspan(7);
			    celula.setPadding(DEFAULT_PADDING);
			    celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			    celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			    tabela.addCell(celula);
			   celula = new PdfPCell(new Phrase("UF \n" + beneficiarioVo.getPessoaBeneficiaria().getSiglaUF(), fontPequenaBold));
			   celula.setColspan(1);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);
			   celula = new PdfPCell(new Phrase("DDD \n" + beneficiarioVo.getPessoaBeneficiaria().getNumrDdd(), fontPequenaBold));
			   celula.setColspan(1);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);
			   celula = new PdfPCell(new Phrase("Telefone \n" + StringUtil.formataTexto(String.valueOf(beneficiarioVo.getPessoaBeneficiaria().getNumrTelefone()), "9999-9999"), fontPequenaBold));
			   celula.setColspan(2);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);
			   celula = new PdfPCell(new Phrase("Email \n" + beneficiarioVo.getPessoaBeneficiaria().getEmail(), fontPequenaBold));
			   celula.setColspan(5);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);
			   celula = new PdfPCell(new Phrase("Valor dos Bens Recebidos \nR$" + beneficiarioVo.getValorRecebidoFormatado(), fontPequenaBold));
			   celula.setColspan(4);
			   celula.setPadding(DEFAULT_PADDING);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabela.addCell(celula);		
				if(it.hasNext())
				{
					celula = new PdfPCell(new Phrase("\n", fontPequenaBold));
					celula.setColspan(colunas.length);
				   celula.setPadding(DEFAULT_PADDING);
				   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
					celula.setBackgroundColor(Color.LIGHT_GRAY);					
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
		if (linha > 0)
		{
			return tabela;
		}
		return null;
	}

	/**
	 * Método responsável por escrever a tabela Demonstrativo de Calculo/GIA
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public PdfPTable tabelaDemonstrativoCalculo(boolean exibeMensagemParametroConfiguracao) throws BadElementException, DocumentException
	{
	   float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
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
	   celula = new PdfPCell(new Phrase("Lei \n" + gIAITCDInventarioArrolamentoVo.getParametroLegislacaoVo().getNumeroLegislacaoFormatado(), fontPequenaBold));
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(2);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Percentual da Multa \n" + gIAITCDInventarioArrolamentoVo.getPercentualMultaFormatado() + "%", fontPequenaBold));
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(3);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   tabela.addCell(celula);    
	   celula = new PdfPCell(new Phrase("Fração Ideal \n" + gIAITCDInventarioArrolamentoVo.getFracaoIdealFormatada(), fontPequenaBold));
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(4);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
	   tabela.addCell(celula);          
	   celula = new PdfPCell(new Phrase("Valor da UPF \n" + gIAITCDInventarioArrolamentoVo.getValorUPFFormatado(), fontPequenaBold));
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(3);
	   celula = new PdfPCell(new Phrase("Total dos Bens Declarados pelo Contribuinte \n" + gIAITCDInventarioArrolamentoVo.getBemTributavelVo().getValorTotalInformadoContribuinteFormatado(), fontPequenaBold));
	   celula.setColspan(4);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);    
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Valor de Incidência ITCD \n" + gIAITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosFormatado(), fontPequenaBold));
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(4);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase("Valor p/ base de Cálculo tributável \n" + gIAITCDInventarioArrolamentoVo.getValorBaseCalculoTributavelFormatado(), fontPequenaBold));
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(4);
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
	   tabela.addCell(celula);       
		
//		if(gIAITCDInventarioArrolamentoVo.isExibeFracaoIdealBensParticulares())
//		{
//		   celula = new PdfPCell(new Phrase("Lei \n" + gIAITCDInventarioArrolamentoVo.getParametroLegislacaoVo().getNumeroLegislacaoFormatado(), fontPequenaBold));
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setColspan(3);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
//		   tabela.addCell(celula);
//		   celula = new PdfPCell(new Phrase("Percentual da Multa \n" + gIAITCDInventarioArrolamentoVo.getPercentualMultaFormatado() + "%", fontPequenaBold));
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setColspan(4);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
//		   tabela.addCell(celula);		
//		   celula = new PdfPCell(new Phrase("Fração Ideal Bens em Comum \n" + gIAITCDInventarioArrolamentoVo.getFracaoIdealFormatada(), fontPequenaBold));
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setColspan(5);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
//		   tabela.addCell(celula);    
//			celula = new PdfPCell(new Phrase("Fração Ideal Bens Particulares \n 100%", fontPequenaBold));
//			celula.setPadding(DEFAULT_PADDING);
//			celula.setColspan(5);
//			celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//			celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
//			tabela.addCell(celula);    		
//			celula = new PdfPCell(new Phrase("Valor da UPF \n" + gIAITCDInventarioArrolamentoVo.getValorUPFFormatado(), fontPequenaBold));
//			celula.setPadding(DEFAULT_PADDING);
//			celula.setColspan(3);
//			celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//			celula.setVerticalAlignment(Element.ALIGN_MIDDLE);		
//			tabela.addCell(celula);
//			celula = new PdfPCell(new Phrase("Total dos bens declarados \n" + gIAITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosFormatado(), fontPequenaBold));
//			celula.setPadding(DEFAULT_PADDING);
//			celula.setColspan(10);
//			celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//			celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
//			tabela.addCell(celula);
//			celula = new PdfPCell(new Phrase("Valor p/ base de Cálculo tributável \n" + gIAITCDInventarioArrolamentoVo.getValorBaseCalculoTributavelFormatado(), fontPequenaBold));
//			celula.setPadding(DEFAULT_PADDING);
//			celula.setColspan(10);
//			celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//			celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
//			tabela.addCell(celula);
//		}
//		else
//		{
//		   celula = new PdfPCell(new Phrase("Lei \n" + gIAITCDInventarioArrolamentoVo.getParametroLegislacaoVo().getNumeroLegislacaoFormatado(), fontPequenaBold));
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setColspan(2);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
//		   tabela.addCell(celula);
//		   celula = new PdfPCell(new Phrase("Percentual da Multa \n" + gIAITCDInventarioArrolamentoVo.getPercentualMultaFormatado() + "%", fontPequenaBold));
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setColspan(3);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
//		   tabela.addCell(celula);    
//		   celula = new PdfPCell(new Phrase("Fração Ideal Bens em Comum \n" + gIAITCDInventarioArrolamentoVo.getFracaoIdealFormatada(), fontPequenaBold));
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setColspan(4);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
//		   tabela.addCell(celula);    		
//		   celula = new PdfPCell(new Phrase("Valor da UPF \n" + gIAITCDInventarioArrolamentoVo.getValorUPFFormatado(), fontPequenaBold));
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setColspan(3);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);    
//		   tabela.addCell(celula);
//		   celula = new PdfPCell(new Phrase("Total dos bens declarados \n" + gIAITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosFormatado(), fontPequenaBold));
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setColspan(4);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
//		   tabela.addCell(celula);
//		   celula = new PdfPCell(new Phrase("Valor p/ base de Cálculo tributável \n" + gIAITCDInventarioArrolamentoVo.getValorBaseCalculoTributavelFormatado(), fontPequenaBold));
//		   celula.setPadding(DEFAULT_PADDING);
//		   celula.setColspan(4);
//		   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
//		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
//		   tabela.addCell(celula);			
//		}
		return tabela;
	}

	/**
	 * Método responsável por montar as células referentes à alíquota para tabela de detalhamento de cálculo do beneficiário.
	 * @param aliquotaVo
	 * @return PdfPCell
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private PdfPCell montaCelulaAliquota (final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquotaVo)
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
	 * Método responsável por montar as células com os dados do beneficiário recebido como parâmetro, 
	 * para cálculo da gia no formato cascata.
	 * @param beneficiarioVo
	 * @param tabela
	 * @param quantidadeCelulasAliquota
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void montaCelulasBeneficiarioCascata(final GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioVo, PdfPTable tabela, final int quantidadeCelulasAliquota)
	{
	   PdfPCell celula = new PdfPCell(new Phrase(beneficiarioVo.getPessoaBeneficiaria().getNomeContribuinte(), fontPequenaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan( defineColspanNomeBeneficiarioPorQuantidadeAliquotas( quantidadeCelulasAliquota) );               		
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase(beneficiarioVo.getValorRecebidoFormatado(), fontPequenaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(2);            		
	   tabela.addCell(celula);
	   for (Iterator iteAliqBeneficiario = beneficiarioVo.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().iterator(); iteAliqBeneficiario.hasNext(); )
	   {
	      GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquotaBeneficiario = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) iteAliqBeneficiario.next();
	      celula = new PdfPCell(new Phrase(aliquotaBeneficiario.getValorBaseCalculoFormatado(), fontPequenaBold));
	      celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      celula.setPadding(DEFAULT_PADDING);
		   celula.setColspan(2);            						
	      tabela.addCell(celula);
	   }
	   for(int i = beneficiarioVo.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().size(); i < quantidadeCelulasAliquota; i++)
	   {
	      celula = new PdfPCell(new Phrase("0,00", fontPequenaBold));
	      celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      celula.setPadding(DEFAULT_PADDING);
		   celula.setColspan(2);             			
	      tabela.addCell(celula);                
	   }
	   celula = new PdfPCell(new Phrase(beneficiarioVo.getValorITCDBeneficiarioFormatado(), fontPequenaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(2);          		
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase(beneficiarioVo.getValorMultaRecolherFormatado(), fontPequenaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(2);         				
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase(beneficiarioVo.getValorRecolherFormatado(), fontPequenaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(3);            				
	   tabela.addCell(celula);       
      
	   Integer infoDispensaRecolhimento = beneficiarioVo.getInfoDispensaRecolhimento();
	   Integer infoIsencao = beneficiarioVo.getInfoIsencao();
	   
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
	   }else{
         celula = new PdfPCell(new Phrase("", fontPequenaBold));
         celula.setPadding(DEFAULT_PADDING);
         celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);       
         celula.setColspan(5);
         tabela.addCell(celula);
      }
	}

	/**
	 * Método responsável por montar as células com os dados do beneficiário recebido como parâmetro, 
	 * para cálculo da gia no formato normal.
	 * @param beneficiarioVo
	 * @param tabela
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void montaCelulasBeneficiarioNormal(final GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioVo, PdfPTable tabela)
	{
	   PdfPCell celula = new PdfPCell(new Phrase(beneficiarioVo.getPessoaBeneficiaria().getNomeContribuinte(), fontPequenaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_LEFT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(6);               
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase(beneficiarioVo.getValorRecebidoFormatado(), fontPequenaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(3);               
	   tabela.addCell(celula);
	   for (Iterator iteAliqBeneficiario = beneficiarioVo.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().iterator(); iteAliqBeneficiario.hasNext(); )
	   {
	      GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquotaBeneficiario = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) iteAliqBeneficiario.next();
	      celula = new PdfPCell(new Phrase(aliquotaBeneficiario.getPercentualAliquotaFormatado()+"%", fontPequenaBold));
	      celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	      celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      celula.setPadding(DEFAULT_PADDING);
	      celula.setColspan(2);                                 
	      tabela.addCell(celula);
	   }
	   celula = new PdfPCell(new Phrase(beneficiarioVo.getValorITCDBeneficiarioFormatado(), fontPequenaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(3);                              
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase(beneficiarioVo.getValorMultaRecolherFormatado(), fontPequenaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(3);                              
	   tabela.addCell(celula);
	   celula = new PdfPCell(new Phrase(beneficiarioVo.getValorRecolherFormatado(), fontPequenaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);
	   celula.setColspan(3);                              
	   tabela.addCell(celula);             		
	}

	/**
	 * Método responsável por retornar a célula subtítulo que separa os dados dos beneficiários comuns dos dados do meeiro beneficiário.
	 * @return PdfPCell
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private PdfPCell obtemSubtituloMeeiroBenenficiario ()
	{
	   PdfPCell celula = new PdfPCell(new Phrase("Dados do Cônjuge sobrevivente (Meeiro) beneficiário", fontPequenaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);             
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);		
		return celula;
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
	public PdfPTable tabelaBeneficiarioDetalharDemonstrativo() throws BadElementException, DocumentException
	{
		int linhasCabecalho = 1;
	   float[] colunas = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
	   PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);     
	   PdfPCell celula = new PdfPCell(new Phrase("Cálculo do Demonstrativo por Beneficiário".toUpperCase(), fontMediaBold));
	   celula.setColspan(colunas.length);
	   celula.setBackgroundColor(Color.LIGHT_GRAY);
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
	   celula.setPadding(DEFAULT_PADDING);    
	   tabela.addCell(celula);	
		if(!Validador.isCollectionValida( gIAITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO()))
		{
			celula = obtemSubtituloMeeiroBenenficiario();
			celula.setColspan(colunas.length);			
			tabela.addCell(celula);			
			linhasCabecalho++;
		}
		if(gIAITCDInventarioArrolamentoVo.getParametroLegislacaoVo().isLegislacaoCascata())
		{
		   TreeMap celulasAliquota = new TreeMap();
		   if(Validador.isCollectionValida( gIAITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO()))
		   {
		      for(Iterator it = gIAITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext(); )
		      {
		         GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioAtual = (GIAITCDInventarioArrolamentoBeneficiarioVo) it.next();
		         if(Validador.isCollectionValida(beneficiarioAtual.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO()))
		         {
		            for(Iterator itAliquotas = beneficiarioAtual.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().iterator(); itAliquotas.hasNext(); )
		            {
		               GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquota = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) itAliquotas.next();
		               if(!celulasAliquota.containsKey(""+aliquota.getPercentualAliquota()))
		               {
						      celulasAliquota.put(""+aliquota.getPercentualAliquota(), montaCelulaAliquota(aliquota));                    							
		               }
		            }
		         }
		      }
		   }
		   else if(Validador.isNumericoValido(gIAITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getPessoaBeneficiaria().getNumrContribuinte()))
		   {
		      if(Validador.isCollectionValida(gIAITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO()))
		      {
		         for(Iterator itAliquotas = gIAITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().iterator(); itAliquotas.hasNext(); )
		         {
		            GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquota = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) itAliquotas.next();
		            if(!celulasAliquota.containsKey(""+aliquota.getPercentualAliquota()))
		            {
		               celulasAliquota.put(""+aliquota.getPercentualAliquota(), montaCelulaAliquota(aliquota));                                         
		            }
		         }
		      }        
		   }		
		   celula = new PdfPCell(new Phrase("Nome do Beneficiário", fontPequenaBold));
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);			
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setColspan(  defineColspanNomeBeneficiarioPorQuantidadeAliquotas(celulasAliquota.size())  );
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Valor do Quinhão / Valor Recebido", fontPequenaBold));
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
		   celula = new PdfPCell(new Phrase("Valor Total da Multa", fontPequenaBold));
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
		   celula.setColspan(3);        
		   tabela.addCell(celula);
         celula = new PdfPCell(new Phrase("Observação ", fontPequenaBold));
         celula.setColspan(5);
         celula.setBackgroundColor(Color.LIGHT_GRAY);
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setPadding(DEFAULT_PADDING);    
         tabela.addCell(celula);
		   
			linhasCabecalho++;
			tabela.setHeaderRows(linhasCabecalho);
         
			if(Validador.isCollectionValida(gIAITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO()))
			{
			   for (Iterator iteBeneficiario = gIAITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().iterator(); iteBeneficiario.hasNext(); )
			   {
			      GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioVo = (GIAITCDInventarioArrolamentoBeneficiarioVo) iteBeneficiario.next();
					montaCelulasBeneficiarioCascata(beneficiarioVo, tabela, celulasAliquota.size());										
			   }				
			}
			if(Validador.isNumericoValido(gIAITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getPessoaBeneficiaria().getNumrContribuinte()))
			{
				if(Validador.isCollectionValida(gIAITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO()))
				{
					celula = obtemSubtituloMeeiroBenenficiario();
				   celula.setColspan(colunas.length);
				   tabela.addCell(celula);       					
				}
				GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioVo = gIAITCDInventarioArrolamentoVo.getMeeiroBeneficiario();
				montaCelulasBeneficiarioCascata(beneficiarioVo, tabela, celulasAliquota.size());														
			}			
		}
		else
		{
		   float[] colunasSemLegislacaoCascata = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
		   tabela = instanciarTabelaPadrao(colunasSemLegislacaoCascata.length, colunasSemLegislacaoCascata); 
		   celula = new PdfPCell(new Phrase("Cálculo do Demonstrativo por Beneficiário".toUpperCase(), fontMediaBold));
         celula.setColspan(colunas.length);
         celula.setBackgroundColor(Color.LIGHT_GRAY);
         celula.setHorizontalAlignment(Element.ALIGN_CENTER);
         celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
         celula.setPadding(DEFAULT_PADDING);    
         tabela.addCell(celula);
               
		   celula = new PdfPCell(new Phrase("Nome do Beneficiário", fontPequenaBold));
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);						
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setColspan(6);
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Valor do Quinhão / Valor Recebido", fontPequenaBold));
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);						
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setColspan(3);
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Alíquota", fontPequenaBold));
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);						
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setColspan(2);
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Valor do ITCD", fontPequenaBold));
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);						
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setColspan(3);            
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Valor Total da Multa", fontPequenaBold));
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);						
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setColspan(3);            
		   tabela.addCell(celula);
		   celula = new PdfPCell(new Phrase("Valor a Recolher Devido", fontPequenaBold));
		   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			celula.setBackgroundColor(Color.LIGHT_GRAY);						
		   celula.setPadding(DEFAULT_PADDING);
		   celula.setColspan(3);            
		   tabela.addCell(celula);		
			linhasCabecalho++;
			tabela.setHeaderRows(linhasCabecalho);
         
		   if(Validador.isCollectionValida(gIAITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO()))
		   {
		      for (Iterator iteBeneficiario = gIAITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().iterator(); iteBeneficiario.hasNext(); )
		      {
		         GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioVo = (GIAITCDInventarioArrolamentoBeneficiarioVo) iteBeneficiario.next();
					montaCelulasBeneficiarioNormal(beneficiarioVo, tabela);
		      }           
		   }
		   if(Validador.isNumericoValido(gIAITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getPessoaBeneficiaria().getNumrContribuinte()))
		   {
		      if(Validador.isCollectionValida(gIAITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO()))
		      {
		         celula = obtemSubtituloMeeiroBenenficiario();
		         celula.setColspan(colunas.length);
		         tabela.addCell(celula);                      
		      }
		      GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioVo = gIAITCDInventarioArrolamentoVo.getMeeiroBeneficiario();
				montaCelulasBeneficiarioNormal(beneficiarioVo, tabela);
		   }			
		}
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
		float[] colunas = {33, 33, 34};	
		PdfPTable tabela = instanciarTabelaPadrao(colunas.length, colunas);
		PdfPCell celula = new PdfPCell(new Phrase("Resumo Explicativo".toUpperCase(), fontMediaBold));
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setColspan(colunas.length);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setPadding(DEFAULT_PADDING);
		tabela.addCell(celula);
		tabela.setHeaderRows(1);
		celula = new PdfPCell(new Phrase("Valor Total do ITCD \n" + gIAITCDInventarioArrolamentoVo.getValorITCDFormatado(), fontPequenaBold));
		celula.setPadding(DEFAULT_PADDING);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
		tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Valor Total da Multa \n" + gIAITCDInventarioArrolamentoVo.getValorMultaFormatado(), fontPequenaBold));
		celula.setPadding(DEFAULT_PADDING);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
		tabela.addCell(celula);
		celula = new PdfPCell(new Phrase("Valor total a recolher \n" + gIAITCDInventarioArrolamentoVo.getValorRecolhimentoFormatado(), fontPequenaBold));
		celula.setPadding(DEFAULT_PADDING);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);          
		tabela.addCell(celula);
		return tabela;		
	}

	/**
	 * Método responsável por escrever a tabela Observação de Inventario Arrolamento
	 * @throws BadElementException
	 * @throws DocumentException
	 * @return PdfPTable
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public PdfPTable tabelaObservacao() throws BadElementException, DocumentException
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
		String dataProtocolar = "";
		if (Validador.isDataValida(gIAITCDInventarioArrolamentoVo.getTemporarioVo().getPrazoProtocolar()))
		{
			dataProtocolar = "\n" + gIAITCDInventarioArrolamentoVo.getTemporarioVo().getPrazoProtocolarFormatado();
		}
	   celula = new PdfPCell(new Phrase("Prazo para protocolar a GIA-ITCD - Inventário / Arrolamento " + dataProtocolar, fontMediaBold));
	   celula.setHorizontalAlignment(Element.ALIGN_CENTER);
	   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);
	   tabela.addCell(celula);
		if(gIAITCDInventarioArrolamentoVo.getRenuncia().is(DomnSimNao.SIM))
		{
			if(gIAITCDInventarioArrolamentoVo.getTipoRenuncia().is(DomnTipoRenuncia.TRANSLATIVA))
			{
				celula = new PdfPCell(new Phrase(MensagemErro.MENSAGEM_RENUNCIA_TRANSLATIVA_IMPRESSAO_GIA, fontGrandeBold));
			   celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			   celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   celula.setPadding(DEFAULT_PADDING);
			   tabela.addCell(celula);
			}
		}
	   switch((int)gIAITCDInventarioArrolamentoVo.getStatusVo().getStatusGIAITCD().getValorCorrente()){
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
		celula = new PdfPCell(new Phrase(FRASE_LEI, fontMediaBold));
		celula.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setPadding(DEFAULT_PADDING);		
		tabela.addCell(celula);
		return tabela;
	}

	/**
	 * Construtor da Classe InventarioArrolamentoGerarPDF
	 * @implemented by Maxwell Mendes Rocha
	 * *
	 */
	public InventarioArrolamentoGerarPDF(HttpServletRequest request, GIAITCDVo giaItcdVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(request, giaItcdVo, formatoPagina);
	}
	
	public InventarioArrolamentoGerarPDF( GIAITCDVo giaItcdVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super( giaItcdVo, formatoPagina);
	}

	/**
	 * Método responsável por verificar se estado civil do De Cujus é Casado ou Convivente.
	 * @param gIAITCDInventarioArrolamentoVo
	 * @return boolean
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public boolean isEstadoCivilCasadoConvivente(GIAITCDInventarioArrolamentoVo gIAITCDInventarioArrolamentoVo)
	{
		if(gIAITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CASADO) || gIAITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CONVIVENTE))
		{
			return true;
		}
		return false;
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
      // 1+(celulasAliquota.size()*2>7?1:7-(celulasAliquota.size()*2))
      
      int colspan = 1;
      
      if(quantidadeAlquotas == 1)
         colspan = 9;
      if(quantidadeAlquotas == 2)
         colspan = 8;
      if(quantidadeAlquotas == 3)
         colspan = 5;   
      if(quantidadeAlquotas == 4)
         colspan = 4;
      if(quantidadeAlquotas == 5)
         colspan = 2;

      return colspan;
   }
   
}
