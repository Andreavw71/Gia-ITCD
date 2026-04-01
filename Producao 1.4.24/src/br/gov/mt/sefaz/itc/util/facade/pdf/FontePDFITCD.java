package br.gov.mt.sefaz.itc.util.facade.pdf;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;

import java.awt.Color;


/**
 * Interface utilizada para definir a formatação do seguintes relatorios em PDF:
 *  1- DeclaracaoInsencaoPorValoresGerarPDF
 *  2- GIAITCDEmitirNotificacaoGerarPDF
 *  
 * @author Wendell Pereira de Farias
 * @version $Revision: 1.1.1.1 $
 */
public interface FontePDFITCD
{
	public static final int TAMANHO_GRANDE = 12; //UNDERLINE
	public static final int TAMANHO_MEDIO = 10;
	public static final int TAMANHO_PEQUENO = 9;
	public static final Font fontGrandeITCD = 
		  FontFactory.getFont(FontFactory.TIMES_ROMAN, TAMANHO_GRANDE, Font.NORMAL, Color.BLACK);
	public static final Font fontGrandeBoldITCD = 
		  FontFactory.getFont(FontFactory.TIMES_ROMAN, TAMANHO_GRANDE, Font.BOLD, Color.BLACK);
	public static final Font fontGrandeBoldItalicITCD = 
		  FontFactory.getFont(FontFactory.TIMES_ROMAN, TAMANHO_GRANDE, Font.BOLD | Font.BOLDITALIC, Color.BLACK);
	public static final Font fontGrandeBoldSubilinhadoITCD = 
		  FontFactory.getFont(FontFactory.TIMES_ROMAN, TAMANHO_GRANDE, Font.BOLD | Font.UNDERLINE, Color.BLACK);
	public static final Font fontGrandeBoldBrancaITCD = 
		  FontFactory.getFont(FontFactory.TIMES_ROMAN, TAMANHO_GRANDE, Font.BOLD, Color.WHITE);
	public static final Font fontMediaITCD = 
		  FontFactory.getFont(FontFactory.TIMES_ROMAN, TAMANHO_MEDIO, Font.NORMAL, Color.BLACK);
	public static final Font fontMediaBoldITCD = 
		  FontFactory.getFont(FontFactory.TIMES_ROMAN, TAMANHO_MEDIO, Font.BOLD, Color.BLACK);
	public static final Font fontMediaBoldBrancaITCD = 
		  FontFactory.getFont(FontFactory.TIMES_ROMAN, TAMANHO_MEDIO, Font.BOLD, Color.WHITE);
	public static final Font fontPequenaITCD = 
		  FontFactory.getFont(FontFactory.TIMES_ROMAN, TAMANHO_PEQUENO, Font.NORMAL, Color.BLACK);
	public static final Font fontPequenaBoldITCD = 
		  FontFactory.getFont(FontFactory.TIMES_ROMAN, TAMANHO_PEQUENO, Font.NORMAL | Font.BOLD, Color.BLACK);
	public static final Font fontPequenaBrancaITCD = 
		  FontFactory.getFont(FontFactory.TIMES_ROMAN, TAMANHO_PEQUENO, Font.NORMAL | Font.BOLD, Color.WHITE);
}
