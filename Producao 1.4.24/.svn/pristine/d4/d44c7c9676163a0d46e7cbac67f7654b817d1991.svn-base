package br.gov.mt.sefaz.itc.util.serializacao;

import br.com.abaco.util.StringUtil;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Arrays;
import java.util.Comparator;


/**
 * @author Ricardo Vitor de Oliveira Moraes
 * @version
 */
public class SerializaUtil
{
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		final String caminhoPadrao = "/usr/appl/servicos/itc/arquivo/";
		final String caminhoCorrecao = caminhoPadrao + "corrigir/";
		final String caminhoCorrigido = caminhoPadrao + "entrada/";
	   File arquivo = new File(caminhoCorrecao);	   
	   String[] arquivos = arquivo.list();
		
	   if(arquivos != null && arquivos.length > 0)
	   {
			Arrays.sort
			(
				arquivos
				, new Comparator<String>()
				{
						public int compare(String o1, String o2)
						{
							String comparado = o1.substring(0, o1.length()-4);
							String comparador = o2.substring(0, o2.length()-4);
							return StringUtil.toInteger(comparado).compareTo(StringUtil.toInteger(comparador));
						}
				}
			);
	      for (int i = 0; i < arquivos.length; i++)
	      {				
				arquivo = new File(caminhoCorrecao+arquivos[i]);
				FileInputStream fis = new FileInputStream(arquivo);				
				ObjectInputStream readObj = new ObjectInputStream(fis);		
				GIAITCDVo gia = (GIAITCDVo) readObj.readObject();
				readObj.close();
				fis.close();
				arquivo.delete();
				
				arquivo = new File(caminhoCorrigido+arquivos[i]);
				if(!arquivo.exists())
				{
					arquivo.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(arquivo);
				ObjectOutputStream writeObj = new ObjectOutputStream(fos);
				writeObj.writeObject(gia);
				writeObj.flush();
				writeObj.close();
				fos.close();
				
	      }
	   }
		
	}
	
}
