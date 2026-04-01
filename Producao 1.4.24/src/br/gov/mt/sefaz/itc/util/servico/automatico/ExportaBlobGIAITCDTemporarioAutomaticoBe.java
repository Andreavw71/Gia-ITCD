package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioVo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Connection;

import java.util.Iterator;


/**
 * @author Ricardo Vitor de Oliveira Moraes
 * @version
 */
public class ExportaBlobGIAITCDTemporarioAutomaticoBe extends AbstractBe
{

	private static final String caminhoPadrao = "/usr/appl/servicos/itc/arquivo/corrigir/";

	public ExportaBlobGIAITCDTemporarioAutomaticoBe(Connection conn)
	{
		super(conn);
	}

	/**
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IOException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void exportaGIAITCD() throws ObjetoObrigatorioException, ConsultaException,IOException, 
			  CloneNotSupportedException
	{
		GIAITCDTemporarioVo gia = new GIAITCDTemporarioBe(conn).listarGIAITCDTemporario(new GIAITCDTemporarioVo());
		if(Validador.isCollectionValida(gia.getCollVO()))
		{
			for(Iterator it = gia.getCollVO().iterator(); it.hasNext(); )
			{
			   GIAITCDTemporarioVo atual = (GIAITCDTemporarioVo) it.next();
			   atual.getInputStream().mark(atual.getInputStream().available());
				if(!atual.leuGIAITCDSerializada())
				{
				   File arquivo = new File(caminhoPadrao + atual.getCodigo() + ".gia");
				   arquivo.createNewFile();
				   FileOutputStream fos = new FileOutputStream(arquivo);
					
					atual.getInputStream().reset();
				   int b = 0;
				   while ((b = atual.getInputStream().read() ) != -1)
				   {
				      fos.write(b);
				   }
				   fos.flush();
				   fos.close();	
					atual.getInputStream().close();
				}
			}
		}
	}
	
}
