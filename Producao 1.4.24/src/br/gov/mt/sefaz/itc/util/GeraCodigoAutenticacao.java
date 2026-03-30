package br.gov.mt.sefaz.itc.util;

import sefaz.mt.util.SefazDataHora;


public class GeraCodigoAutenticacao
{

	public String geraCodgAutenticacao(long parmNumrCertidao)
	{
		String numrCert = null;
		String parametro = null;
		String dataAtual = new SefazDataHora().toString();
		numrCert = zeroEsquerda(String.valueOf(parmNumrCertidao), 10);
		parametro = numrCert;
		parametro = 
					 dataAtual.substring(15, 16) + numrCert.substring(6, 7) + numrCert.substring(1, 2) + dataAtual.substring(17, 18) + 
					 numrCert.substring(7, 8) + numrCert.substring(5, 6) + dataAtual.substring(12, 13) + numrCert.substring(9, 10) + 
					 numrCert.substring(0, 1) + dataAtual.substring(18, 19) + numrCert.substring(2, 3) + numrCert.substring(8, 9) + 
					 dataAtual.substring(14, 15) + numrCert.substring(3, 4) + numrCert.substring(4, 5) + 
					 dataAtual.substring(11, 12);
		return geraCodgAut(parametro);
	}

	/**
	 * Método responsavel colocar zeros (0) a esquerda até o tamanho estipulado
	 */
	private String zeroEsquerda(String parmNumr, int tam)
	{
		String varSerPreenchida = parmNumr;
		while (varSerPreenchida.length() < tam)
		{
			varSerPreenchida = "0" + varSerPreenchida;
		}
		return varSerPreenchida;
	}

	/**
	 * Método responsavel por formar uma string de 16 posições
	 */
	private String geraCodgAut(String parmStrBase)
	{
		String result = "";
		String cadeia_aux = codifica(parmStrBase);
		for (int i = 15; i >= 0; i--)
		{
			result = result + cadeia_aux.charAt(i);
		}
		return result;
	}

	/**
	 * Método responsavel por códificar
	 */
	private String codifica(String parmStrCod)
	{
		int tam = 0;
		int a = 0;
		int b = 0;
		int c = 0;
		int pos = 0;
		String strTabela = "3H5IJ4FG8CDE2T9ABULMK7XY6VWZNOPS01QR";
		String result = "";
		tam = strTabela.length();
		parmStrCod = zeroEsquerda(parmStrCod, 16);
		for (int i = 0; i <= 15; i++)
		{
			c = (int) parmStrCod.charAt(i);
			if (c % tam == 0)
				pos = tam - 1;
			else
				pos = c % tam;
			result = result + strTabela.charAt(pos);
		}
		return result;
	}

	private int fdex(int parmX)
	{
		int[] chave = { 7, 3, 7, 9, 2, 9 };
		return ((chave[0] * expoente(parmX, 5)) + (chave[1] * expoente(parmX, 4)) + (chave[2] * expoente(parmX, 3)) + 
				 (chave[3] * expoente(parmX, 2)) + (chave[4] * parmX) + chave[5]);
	}

	private int expoente(int x, int y)
	{
		int ret;
		if (y == 0)
		{
			ret = 1;
		}
		else
		{
			ret = x;
		}
		for (int cont = 1; cont < y; cont++)
		{
			ret = ret * x;
		}
		return ret;
	}
}
