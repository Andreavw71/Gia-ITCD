package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioVo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import sefaz.mt.util.AlteraException;


/**
 * @author Ricardo Vitor de Oliveira Moraes
 * @version
 */
public class SerializaGIAITCDAutomaticoBe extends AbstractBe
{

	private static final String caminhoArquivoLeitura = "/usr/appl/servicos/itc/arquivo/entrada/";
	
	public SerializaGIAITCDAutomaticoBe(Connection conn)
	{
		super(conn);
	}

	/**
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @throws AlteraException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void serializaGIAITCDAutomatico() throws ObjetoObrigatorioException, ConsultaException, 
			  ParametroObrigatorioException, ConexaoException, AlteraException
	{
		GIAITCDTemporarioVo giaITCDTemporarioVo = obterGIAITCDTemporarioParaCorrigir();
		String mensagemErro = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());		
		if(Validador.isCollectionValida(giaITCDTemporarioVo.getCollVO()))
		{
			GIAITCDTemporarioBe be = new GIAITCDTemporarioBe(conn);
			for(Iterator it = giaITCDTemporarioVo.getCollVO().iterator(); it.hasNext(); )
			{
			   GIAITCDTemporarioVo atual = (GIAITCDTemporarioVo) it.next();
				try
				{
					GIAITCDVo gia = obterGiaDeserializada(atual);	
				   atual.setGiaitcdVo(gia);
				   be.alterarGIAITCDTemporario(atual, true);
				   try
				   {
				      conn.commit();
				   }
				   catch(SQLException e)
				   {
				      throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
				   }           
				}
				catch(Exception e)
				{
					mensagemErro += "\n"+e.getMessage();
				}				
			}
		}
		else
		{
			mensagemErro = "Não foram encontradas nenhuma GIAITCD com erro de serialização.";
		}
		if(Validador.isStringValida(mensagemErro))
		{
			throw new ParametroObrigatorioException(mensagemErro);
		}
	}

	/**
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private GIAITCDTemporarioVo obterGIAITCDTemporarioParaCorrigir() throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		File arquivo = new File(caminhoArquivoLeitura);
	   Collection listaGiasParaCorrigir = new ArrayList();
		
		String[] arquivos = arquivo.list();

		if(arquivos != null && arquivos.length > 0)
		{
			for (int i = 0; i < arquivos.length; i++)
			{
				String numeroGia = arquivos[i];
				numeroGia = numeroGia.substring(0,numeroGia.indexOf(".gia"));
				GIAITCDTemporarioVo parametro = new GIAITCDTemporarioVo();
				parametro.setCodigo(StringUtil.toLong(numeroGia));
				GIAITCDTemporarioVo consulta = new GIAITCDTemporarioVo();
				consulta.setParametroConsulta(parametro);
			   consulta = new GIAITCDTemporarioBe(conn).consultaGIAITCDTemporario(consulta);
			   listaGiasParaCorrigir.add(consulta);
			}
		}
		if(Validador.isCollectionValida(listaGiasParaCorrigir))
		{
			return new GIAITCDTemporarioVo(listaGiasParaCorrigir);
		}
	   return new GIAITCDTemporarioVo();
	}

	/**
	 * @param giaITCDTemporarioVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws DadoNecessarioInexistenteException
	 * @throws ClassNotFoundException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private GIAITCDVo obterGiaDeserializada(GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  FileNotFoundException, IOException, DadoNecessarioInexistenteException, ClassNotFoundException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);		
		if(Validador.isNumericoValido(giaITCDTemporarioVo.getCodigo()))
		{
			File arquivo = new File(caminhoArquivoLeitura + giaITCDTemporarioVo.getCodigo() + ".gia");
			if(arquivo.exists())
			{
				FileInputStream fis = new FileInputStream(arquivo);	
				ObjectInputStream obj = new ObjectInputStream(fis);
				GIAITCDVo gia = (GIAITCDVo) obj.readObject();
				obj.close();
				fis.close();
				arquivo.delete();
				return gia;
			}
			else
			{
				throw new DadoNecessarioInexistenteException(getMensagemErro(giaITCDTemporarioVo.getCodigo()));
			}			
		}
		throw new DadoNecessarioInexistenteException("O código da GIAITCD que é necessário para encontrar o arquivo de correção, não foi encontrado.");
	}

	private String getMensagemErro(long codigo)
	{
		StringBuffer mensagem = new StringBuffer();
		mensagem.append("A GIAITCD número ").append(codigo).append(" não está sendo deserializada, favor corrija a GIAITCD ou insira o arquivo de correção no servidor.");
		return mensagem.toString();
	}
}
