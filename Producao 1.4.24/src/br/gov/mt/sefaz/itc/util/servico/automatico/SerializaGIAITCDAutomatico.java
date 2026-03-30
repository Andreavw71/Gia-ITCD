package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.gov.mt.sefaz.itc.util.ITCDServico;

import java.io.IOException;

import java.sql.Connection;

import sefaz.mt.util.Propriedades;


/**
 * Classe criada para corrigir os objetos serializados no banco de dados,
 * devido a erro ocasionado por objetos persistíveis que não possuiam um 
 * serialVersionUID padrão.
 * @author Ricardo Vitor de Oliveira Moraes
 * @version
 */
public class SerializaGIAITCDAutomatico extends ITCDServico
{
	public static void main(String[] args) throws IOException
	{
		String nomeAtividade = "/servicos";
		String nomeSistema = "/itc";
		String nomeSaida = "/out";
		String nomeArquivoLOG = "/SerializaGIAITCDAutomatico.log";
		String nomeArquivoProperties = "ITCDS.properties";
		Connection conexao = null;
		SerializaGIAITCDAutomaticoBe serializaGIAITCDAutomaticoBe = null;
		registrarLogExecucao(null, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
		try
		{
			Propriedades.setArquivoPropriedades(nomeArquivoProperties);
			conexao = abreConexao();
		   serializaGIAITCDAutomaticoBe = new SerializaGIAITCDAutomaticoBe(conexao);
		   serializaGIAITCDAutomaticoBe.serializaGIAITCDAutomatico();
			throw new Exception(SEM_ERROS);
		}
	   catch (Exception erro)
	   {
	      erro.printStackTrace();
	      registrarLogExecucao(erro, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
	   }
	   catch (Error erro)
	   {
	      erro.printStackTrace();
	      registrarLogExecucao(erro, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
	   }
	   catch (Throwable erro)
	   {
	      erro.printStackTrace();
	      registrarLogExecucao(erro, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
	   }
	   finally
	   {
	      try
	      {
	         //Aqui você fecha a sua sub classe de AbstractBe(). Segue exemplo:
	         if (serializaGIAITCDAutomaticoBe != null)
	         {
	            serializaGIAITCDAutomaticoBe.close();
	            serializaGIAITCDAutomaticoBe = null;
	         }
	         fechaConexao(conexao);
	         conexao = null;
	      }
	      catch (Exception erro)
	      {
	         erro.printStackTrace();
	         registrarLogExecucao(erro, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
	      }
	      catch (Error erro)
	      {
	         erro.printStackTrace();
	         registrarLogExecucao(erro, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
	      }
	      catch (Throwable erro)
	      {
	         erro.printStackTrace();
	         registrarLogExecucao(erro, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
	      }
	   }		
	}
	
}
