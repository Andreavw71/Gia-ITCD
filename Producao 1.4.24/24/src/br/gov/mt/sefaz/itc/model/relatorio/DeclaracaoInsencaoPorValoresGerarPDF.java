package br.gov.mt.sefaz.itc.model.relatorio;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.pdf.AbstractGeradorPDFITCD;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

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

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;


/**
 * Classe responsavel por gerar o PDF de Declaração de Insenção por Valores
 * @author Wendell Pereira de Farias
 * @version $Revision: 1.4 $
 */
public class DeclaracaoInsencaoPorValoresGerarPDF extends AbstractGeradorPDFITCD
{

	/**
	 * Associação com o GIAITCDVo
	 * Associação utlizada para resgatar todas as informações necessárias, principalmente a classificação da GIA em Avaliação.
	 * OBS: getEntidadeVo() : Metodo implementado classse pai.
	 */
	GIAITCDVo giaITCDVo = (GIAITCDVo) getEntidadeVo();
   ContribuinteIntegracaoVo pessoaConj1; 
   ContribuinteIntegracaoVo pessoaConj2;

	public DeclaracaoInsencaoPorValoresGerarPDF(HttpServletRequest request,GIAITCDVo giaITCDVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(request, giaITCDVo, formatoPagina);
	}

	public DeclaracaoInsencaoPorValoresGerarPDF(GIAITCDVo giaITCDVo, Rectangle formatoPagina) throws ObjetoObrigatorioException
	{
		super(giaITCDVo, formatoPagina);
	}
	
	
	/**
	 * Sobrecarga do método gerarCorpoPDF que poderá ser chamada por outra classe geradora de  PDF, que por sua vez, passa por parâmetro 
	 * o objeto do tipo Document e objeto do tipo FichaImovelRuralVo .
	 * @param giaITCDVo , Document
	 * @implemented by Wendell Pereira de Farias
	 */
	public void gerarCorpoPDF(GIAITCDVo giaITCDVo, Document document) throws ObjetoObrigatorioException, 
			  BadElementException, DocumentException, IOException
	{
		this.giaITCDVo = giaITCDVo;
		this.gerarCorpoPDF(document);
	}

	/**
	 * Metodo responsável por gerar parte do corpo do relatório, validando a GIAITCD.
	 * @throws BadElementException
	 * @return
	 * @implemented by Wendell Pereira de Farias
	 */
	public Table tabelaDeclaracaoInsencaoPorValor() throws BadElementException
	{
		/**
		 *  Processo para criação da Tabela
		 *  O processo de definição da tabela inicializa com a criação de um vetor do tipo float, que representa o tamanho de cada celula e o número de colunas.
		 */
	   float[] colunas = { 30, 15, 20, 20, 15 };
		String numeroProtocoloGIAITCD = GIAITCDVo.STRING_VAZIA;
		String dataProtocoloGIAITCD = GIAITCDVo.STRING_VAZIA;
		String numeroGIAITCD = GIAITCDVo.STRING_VAZIA;
		String agenciaFazendaria = GIAITCDVo.STRING_VAZIA;
		//Reorna Objeto GIAITCD 
		if (giaITCDVo != null)
		{
			//Retorna o status
			if (giaITCDVo.getStatusVo() != null)
			{
				//Retorna o Protocolo
				if (Validador.isNumericoValido(giaITCDVo.getStatusVo().getProtocolo()))
				{
					numeroProtocoloGIAITCD = "" + giaITCDVo.getStatusVo().getProtocolo();
				}
				//Retorna a Data Protocolo
				if (Validador.isDataValida(giaITCDVo.getStatusVo().getDataProtocolo()))
				{
					dataProtocoloGIAITCD = giaITCDVo.getStatusVo().getDataProtocoloFormatada();
				}
			}
			//Retorna o Codigo da Gia
			if (Validador.isNumericoValido(giaITCDVo.getCodigo()))
			{
				numeroGIAITCD = "" + giaITCDVo.getCodigo();
			}
			//Retorna a Agencia Protocolo
			if (Validador.isNumericoValido(giaITCDVo.getAgenciaProtocolo().getCodgUnidade()))
			{
				agenciaFazendaria = "" + giaITCDVo.getAgenciaProtocolo().getCodgUnidade() + " " +giaITCDVo.getAgenciaProtocolo().getNomeUnidade();
			}
		}
		Phrase frase;
		Table tabela = instanciarTabela(5, colunas); //Criando a tabela.
		tabela.setDefaultHorizontalAlignment(Element.ALIGN_LEFT); //É necessário definir o alinhamento da tabela.
		tabela.setDefaultColspan(5);
		//Foi inserido o sub título.
      String textoCabecalhoDeclaracao = "DECLARAÇÃO DE ISENÇÃO DE VALORES DO IMPOSTO SOBRE TRANSMISSÃO CAUSA MORTIS E  DOAÇÃO DE QUAISQUER BENS OU DIREITOS- ITCD.";
      if(giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO_IMPOSTO_ATE_1_UPF))
      {
         textoCabecalhoDeclaracao = "DECLARAÇÃO DE ISENÇÃO DO IMPOSTO APURADO, QUANDO INFERIOR A UMA UPF/MT, CONFORME PREVISTO NO PARÁGRAFO 6º DO ARTIGO 19 DA LEI Nº 7.850/2002";
      }
      
      if(giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.DISPENSADO_RECOLHIMENTO))
      {
         textoCabecalhoDeclaracao = "DECLARAÇÃO DE DISPENSA DE RECOLHIMENTO DO IMPOSTO APURADO, QUANDO INFERIOR A UMA UPF/MT, CONFORME PREVISTO NO PARÁGRAFO 6º DO ARTIGO 19 DA LEI N. 7.850/2002";
      }
      
		Cell celula = new Cell(new Phrase(textoCabecalhoDeclaracao, fontMediaBold));
		celula.setColspan(5);
		celula.setBackgroundColor(Color.LIGHT_GRAY);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		tabela.addCell(celula);
		tabela.setDefaultColspan(2); //Metodo utilizado para mesclar as  celulas de uma determinada linha, o parametro representa o número de celulas que serão mescladas
		frase = new Phrase("Declaração N° \n" + String.valueOf(giaITCDVo.getNumrDeclaracaoIsencao()), fontPequenaBold);
		celula = new Cell(frase);
		tabela.addCell(celula); //Inserindo uma celula na nova linha da tabela
		tabela.setDefaultColspan(2);
		frase = new Phrase("N° do protocolo da GIA-ITCD: \n" + numeroProtocoloGIAITCD, fontPequenaBold);
		tabela.addCell(frase);
		tabela.setDefaultColspan(1);
		frase = new Phrase("Data do Protocolo da GIA-ITCD: \n" + dataProtocoloGIAITCD, fontPequenaBold);
		tabela.addCell(frase);
		tabela.setDefaultColspan(2);
		frase = new Phrase("Número da GIA-ITCD: \n" + numeroGIAITCD, fontPequenaBold);
		celula = new Cell(frase);
		tabela.addCell(celula);
		tabela.setDefaultColspan(3);
		frase = new Phrase("Agência Fazendária: \n" + agenciaFazendaria, fontPequenaBold);
		tabela.addCell(frase);
		return tabela;		
	}
	
	/**
	 * Método responsável por montar as celulas com os dados pessoais de um Contribuinte.
	 * @param contribuinteIntegracaoVo
	 * @param pessoa
	 * @return Collection
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public Collection obterDadosContribuinte(ContribuinteIntegracaoVo contribuinteIntegracaoVo, String pessoa)
	{
		Collection dadosContribuinte = new ArrayList();
		//LINHA 1
		PdfPCell celula = new PdfPCell(new Phrase("Nome / Razão Social / Outros: \n" +  contribuinteIntegracaoVo.getNomeContribuinte(), fontPequenaBold));
		celula.setColspan(8);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("CPF / CNPJ \n" +  StringUtil.formataCPF(contribuinteIntegracaoVo.getNumrDocumento()), fontPequenaBold));
		celula.setColspan(4);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("Logradouro: \n" + contribuinteIntegracaoVo.getEndereco(), fontPequenaBold));
		celula.setColspan(8);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		//LINHA 2
		celula = new PdfPCell(new Phrase("Número: \n" + contribuinteIntegracaoVo.getEnderecoNumero(), fontPequenaBold));
		celula.setColspan(2);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("Complemento: \n" + contribuinteIntegracaoVo.getEnderecoComplemento(), fontPequenaBold));
		celula.setColspan(6);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("Ponto de Referência: \n" + contribuinteIntegracaoVo.getPontoReferencia(), fontPequenaBold));
		celula.setColspan(6);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("Bairro: \n" + contribuinteIntegracaoVo.getEnderecoBairro(), fontPequenaBold));
		celula.setColspan(4);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("CEP: \n" + StringUtil.formataTexto(String.valueOf(contribuinteIntegracaoVo.getEnderecoCEP()), "99.999-999"), fontPequenaBold));
		celula.setColspan(2);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		//LINHA 3
		celula = new PdfPCell(new Phrase("Município: \n" + contribuinteIntegracaoVo.getMunicipio(), fontPequenaBold));
		celula.setColspan(8);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("UF: \n" + contribuinteIntegracaoVo.getSiglaUF(), fontPequenaBold));
		celula.setColspan(1);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("DDD: \n" + contribuinteIntegracaoVo.getNumrDdd(), fontPequenaBold));
		celula.setColspan(1);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("Telefone: \n" + contribuinteIntegracaoVo.getNumrTelefoneFormatado(), fontPequenaBold));
		celula.setColspan(2);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		celula = new PdfPCell(new Phrase("E-mail: \n" + contribuinteIntegracaoVo.getEmail(), fontPequenaBold));
		celula.setColspan(8);
		celula.setPadding(DEFAULT_PADDING);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setHorizontalAlignment(Element.ALIGN_LEFT);
		dadosContribuinte.add(celula);
		
		return dadosContribuinte;
		
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
		Cell celula = 
				 new Cell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_DECLARACAO_ISENCAO_VALORES) + "\n\n", fontPequenaBold));
		celula.setColspan(5);
		celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
		//Retorna a Mensagem da Configuração de Parametros Gerencias
		tabela.addCell(celula);
		return tabela;
	}	
   

   public Table tabelaMensagemDispensaRecolhimento() throws BadElementException
   {
      float[] colunas = { 30, 15, 20, 20, 15 };
      Table tabela = instanciarTabela(5, colunas);
      tabela.setDefaultColspan(5);
      Cell celula = 
             new Cell(new Phrase(retornaValorItem(ConfiguracaoITCD.PARAMETRO_TEXTO_DESCRITIVO_DECLARACAO_DISPENSA_RECOLHIMENTO) + "\n\n", fontPequenaBold));
      celula.setColspan(5);
      celula.setHorizontalAlignment(Cell.ALIGN_CENTER);
      //Retorna a Mensagem da Configuração de Parametros Gerencias
      tabela.addCell(celula);
      return tabela;
   }  

	/**
	 * Método responsável por finalizar a construção do corpo do relatório.  
	 * @param document
	 * @throws BadElementException
	 * @throws DocumentException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Wendell Pereira de Farias
	 */
	public void gerarCorpoPDF(Document document) throws BadElementException, DocumentException, 
			  ObjetoObrigatorioException
	{
		Table tabelaDeclaracaoInsencaoPorValor = tabelaDeclaracaoInsencaoPorValor();
		document.add(tabelaDeclaracaoInsencaoPorValor);
		PdfPTable tabelaIdentificacao = tabelaPessoa("IDENTIFICAÇÃO","", giaITCDVo.getContribuinteNotificacaoDar());
		document.add(tabelaIdentificacao);
      
	   if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO) && ((GIAITCDInventarioArrolamentoVo)giaITCDVo).getMeeiroBeneficiario().getCodigo() > 0) {
         giaITCDVo.getBeneficiarioVo().getListVo().add(((GIAITCDInventarioArrolamentoVo)giaITCDVo).getMeeiroBeneficiario());
      }
      
      for (BeneficiarioVo beneficiario : giaITCDVo.getBeneficiarioVo().getListVo()) {
            ContribuinteIntegracaoVo contribuinte = beneficiario.getPessoaBeneficiaria();
         if (contribuinte.getNumrContribuinte() !=  giaITCDVo.getContribuinteNotificacaoDar().getNumrContribuinte()){
            PdfPTable tabelaIdentificacaoAdicional = tabelaPessoa("","", contribuinte);
            document.add(tabelaIdentificacaoAdicional);          
         }	             
      }
      
	   if (giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO) && ((GIAITCDInventarioArrolamentoVo)giaITCDVo).getMeeiroBeneficiario().getCodigo() > 0) {
         giaITCDVo.getBeneficiarioVo().getListVo().remove(((GIAITCDInventarioArrolamentoVo)giaITCDVo).getMeeiroBeneficiario());
      }
      
	   if(giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA)){
         this.pessoaConj1 =((GIAITCDSeparacaoDivorcioVo) giaITCDVo).getConjuge1().getPessoaConjuge();
         if (pessoaConj1.getNumrContribuinte() !=  giaITCDVo.getContribuinteNotificacaoDar().getNumrContribuinte()){
            PdfPTable tabelaIdentificacaoAdicionalConjugue1 = tabelaPessoa("","", this.pessoaConj1);
            document.add(tabelaIdentificacaoAdicionalConjugue1);
         }
         this.pessoaConj2 =((GIAITCDSeparacaoDivorcioVo) giaITCDVo).getConjuge2().getPessoaConjuge();
         if (pessoaConj2.getNumrContribuinte() !=  giaITCDVo.getContribuinteNotificacaoDar().getNumrContribuinte()){
            PdfPTable tabelaIdentificacaoAdicionalConjugue2 = tabelaPessoa("","", this.pessoaConj2);
            document.add(tabelaIdentificacaoAdicionalConjugue2);
         }  
      }
      
	   if(giaITCDVo.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.DISPENSADO_RECOLHIMENTO)){
	      Table tabelaMensagemDispensaRecolhimento = tabelaMensagemDispensaRecolhimento();
	      document.add(tabelaMensagemDispensaRecolhimento);
	   }else{
         Table tabelaMensagem = tabelaMensagem();
         document.add(tabelaMensagem);
      }     
      
		PdfPTable tabelaCodigoAutenticidade = tabelaAutenticidadeRetificacaoNotificacao(giaITCDVo);
		document.add(tabelaCodigoAutenticidade);
	   if(giaITCDVo.isGiaRetificada())
	   {
	      document.newPage();
	      if(giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.DOACAO))
	      {
	         RetificacaoGIAITCDDoacaoGerarPDF retificacao = new RetificacaoGIAITCDDoacaoGerarPDF(giaITCDVo, this.getFormatoPagina());
	         retificacao.gerarCorpoPDF(document);
	      }
	      else if(giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA))
	      {
	         RetificacaoGIAITCDSeparacaoDivorcioGerarPDF retificacao = new RetificacaoGIAITCDSeparacaoDivorcioGerarPDF(giaITCDVo, this.getFormatoPagina());
	         retificacao.gerarCorpoPDF(document);
	      }
	      else if(giaITCDVo.getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
	      {
	         RetificacaoGIAITCDInventarioArrolamentoGerarPDF retificacao = new RetificacaoGIAITCDInventarioArrolamentoGerarPDF(giaITCDVo, this.getFormatoPagina());
	         retificacao.gerarCorpoPDF(document);
	      }
	   }
		
	}
}
