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


/**
 * Classe responsavel por gerar o PDF de Declaração de Insenção por Valores
 * @author João Batista Padilha e Silva / Wendell Pereira de Farias
 * @version $Revision: 1.3 $
 */
public class DeclaracaoNaoOcorrenciaFatoGerador extends AbstractGeradorPDFITCD
{
	/**
	 * Associação com o StatusGIAITCDVo
	 * Associação utlizada para resgatar todas as informações necessárias, principalmente a classificação da GIA em Avaliação.
	 */
	private static GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo;
	//Bloco statico de inicialização
	{
		if (getEntidadeVo() instanceof GIAITCDSeparacaoDivorcioVo)
		{
			giaITCDSeparacaoDivorcioVo = (GIAITCDSeparacaoDivorcioVo) getEntidadeVo();
		}
	}

	/**
	 * Método Construtor
	 * @author João Batista Padilha e Silva
	 * @version $Revision: 1.3 $
	 * @implemented by Wendell Pereira de Farias
	 */
	public DeclaracaoNaoOcorrenciaFatoGerador(HttpServletRequest request, GIAITCDVo giaItcdVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(request, giaItcdVo, formatoPagina);
	}


	/**
	 * Metodo responsável por criar a tabela com messagem para o contribuinte.
	 * @throws BadElementException
	 * @return
	 * @implemented by Wendell Pereira de Farias
	 */
	public Table tabelaMensagem() throws BadElementException
	{
		float[] colunas = { 30, 15, 20, 20, 15 };
		Table tabela = instanciarTabela(5, colunas);
		tabela.setDefaultColspan(5);
		Cell celula = new Cell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_DECLARACAO_NAO_OCORRENCIA_FATO_GERADOR) + "\n\n", fontPequenaBold));
		celula.setColspan(5);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		//Retorna a Mensagem da Configuração de Parametros Gerencias
		tabela.addCell(celula);
		return tabela;
	}

	/**
	 * Metodo responsável por gerar o corpo do relatório.
	 * Chama os seguintes metodos:
	 * tabelaConjuge(), tabelaProcurador(), tabelaMensagem(), tabelaAutenticidade().
	 * @param document
	 * @throws BadElementException
	 * @throws DocumentException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Wendell Pereira de Farias
	 */
	public void gerarCorpoPDF(Document document) throws BadElementException, DocumentException, 
			  ObjetoObrigatorioException
	{
		float[] colunas = { 30, 15, 20, 20, 15 }; //Vetor que define o numero de colunas.
		String numeroDeclaracao = GIAITCDSeparacaoDivorcioVo.STRING_VAZIA;
		String numeroProtocolo = GIAITCDSeparacaoDivorcioVo.STRING_VAZIA;
		String dataProtocolo = GIAITCDSeparacaoDivorcioVo.STRING_VAZIA;
		String numeroGIA = GIAITCDSeparacaoDivorcioVo.STRING_VAZIA;
		String agenciaFazendaria = GIAITCDSeparacaoDivorcioVo.STRING_VAZIA;
		//Validação valores utilizado no relatório
		if (giaITCDSeparacaoDivorcioVo != null)
		{
			//Retorna Número Declaracao Não Fator Gerador
			if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getNumrDeclaracaoFatoGerador()))
			{
				numeroDeclaracao = String.valueOf(giaITCDSeparacaoDivorcioVo.getNumrDeclaracaoFatoGerador());
			}
			//Retorna Número Processo
			if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getNumeroProcesso()))
			{
				numeroProtocolo = String.valueOf(giaITCDSeparacaoDivorcioVo.getNumeroProcesso());
			}
			//Retorna Data Protocolo
			if (Validador.isStringValida(giaITCDSeparacaoDivorcioVo.getDataProtocoloITCDFormatada()))
			{
				dataProtocolo = giaITCDSeparacaoDivorcioVo.getDataProtocoloITCDFormatada();
			}
			//Retorna Código GIAITCD
			if (Validador.isStringValida(giaITCDSeparacaoDivorcioVo.getCodigoFormatado()))
			{
				numeroGIA = giaITCDSeparacaoDivorcioVo.getCodigoFormatado();
			}
			if (giaITCDSeparacaoDivorcioVo.getAgenciaProtocolo() != null)
			{
				//Retorna Agencia Fazendaria
				if (Validador.isStringValida(giaITCDSeparacaoDivorcioVo.getAgenciaProtocolo().getNomeUnidade()))
				{
					agenciaFazendaria = giaITCDSeparacaoDivorcioVo.getAgenciaProtocolo().getNomeUnidade();
				}
			}
		}
		Table tabela = instanciarTabela(5, colunas); //Criando a tabela.
		tabela.setDefaultColspan(5);
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		//Linha 01
		Cell celula = 
				 new Cell(new Phrase("DECLARAÇÃO DE RECONHECIMENTO DE NÃO OCORRÊNCIA DE FATO GERADOR AO IMPOSTO SOBRE TRANSMISSÃO CAUSA MORTIS E DOAÇÃO DE QUAISQUER BENS OU DIREITOS - ITCD", fontMediaBold));
		celula.setColspan(5);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		//Linha 02
		tabela.setDefaultColspan(2);
		Phrase frase = 
				 new Phrase("DECLARAÇÃO n°: \n" + giaITCDSeparacaoDivorcioVo.getNumrDeclaracaoFatoGerador(), fontPequenaBold);
		tabela.addCell(frase);
		tabela.setDefaultColspan(2);
		frase = new Phrase("N° Protocolo: \n" + numeroProtocolo, fontPequenaBold);
		tabela.addCell(frase);
		tabela.setDefaultColspan(1);
		frase = new Phrase("Data do Protocolo: \n" + dataProtocolo, fontPequenaBold);
		tabela.addCell(frase);
		//Linha 03
		tabela.setDefaultColspan(2);
		tabela.addCell(new Phrase("N° da GIA-TCD: \n" + numeroGIA, fontPequenaBold));
		tabela.setDefaultColspan(3);
		tabela.addCell(new Phrase("Agência Fazendária: \n" + agenciaFazendaria, fontPequenaBold));
		document.add(tabela);
		//Retorna a tabela do declarante
		GIAITCDSeparacaoDivorcioGerarPDF separacaoPDF = new GIAITCDSeparacaoDivorcioGerarPDF(giaITCDSeparacaoDivorcioVo,InterfacePDF.PAGINA_A4_RETRATO);
		PdfPTable tabelaDeclarante = separacaoPDF.tabelaDeclarante();
		if (tabelaDeclarante != null)
		{
			document.add(tabelaDeclarante);
		}
		//Retorna a tabela Procurador
		if(Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getProcuradorVo().getNumrContribuinte()))
		{
		   PdfPTable tabelaProcurador = separacaoPDF.tabelaProcurador();
		   if (tabelaProcurador != null)
		   {
		      document.add(tabelaProcurador);
		   }			
		}
		//Retorna a tabela Mensagem para contribuinte
		Table tabelaMensagem = tabelaMensagem();
		if (tabelaMensagem != null)
		{
			document.add(tabelaMensagem);
		}
		//Retorna a tabela Autenticidad
		PdfPTable tabelaAutenticidade = tabelaAutenticidadeRetificacaoNotificacao(giaITCDSeparacaoDivorcioVo);
		if (tabelaAutenticidade != null)
		{
			document.add(tabelaAutenticidade);
		}
		if(giaITCDSeparacaoDivorcioVo.isGiaRetificada())
		{
			RetificacaoGIAITCDSeparacaoDivorcioGerarPDF retificacao = new RetificacaoGIAITCDSeparacaoDivorcioGerarPDF(giaITCDSeparacaoDivorcioVo, this.getFormatoPagina() );
			document.newPage();
			retificacao.gerarCorpoPDF(document);
		}		
	}

	/**
	 * Sobrecarga do método gerarCorpoPDF que poderá ser chamada por outra classe geradora de  PDF, que por sua vez, passa por parâmetro 
	 * o objeto do tipo Document e objeto do tipo FichaImovelRuralVo .
	 * @param vo , Document
	 * @param document
	 * @throws BadElementException
	 * @throws DocumentException
	 * @throws ObjetoObrigatorioException
	 * @implemented by João Batista Padilha e Silva / Wendell Pereira de Farias 
	 */
	public void gerarCorpoPDF(GIAITCDSeparacaoDivorcioVo vo, Document document) throws BadElementException, 
			  DocumentException, ObjetoObrigatorioException
	{
		DeclaracaoNaoOcorrenciaFatoGerador.giaITCDSeparacaoDivorcioVo = vo;
		this.gerarCorpoPDF(document);
	}
}
