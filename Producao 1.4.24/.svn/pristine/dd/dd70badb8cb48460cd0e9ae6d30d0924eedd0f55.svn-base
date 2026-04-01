package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.gov.mt.sefaz.itc.util.ITCDServico;

import java.io.IOException;

import java.sql.Connection;

import sefaz.mt.util.Propriedades;


/**
 * @author Ricardo Vitor de Oliveira Moraes
 * @version
 */
public class ExportaBlobGIAITCDTemporariaAutomatico extends ITCDServico
{
	public static void main(String[] args) throws IOException
	{
	   String nomeAtividade = "/servicos";
	   String nomeSistema = "/itc";
	   String nomeSaida = "/out";
	   String nomeArquivoLOG = "/ExportaBlobGIAITCDTemporariaAutomatico.log";
	   String nomeArquivoProperties = "ITCDS.properties";
	   Connection conexao = null;
	   ExportaBlobGIAITCDTemporarioAutomaticoBe exportaBlobGIAITCDTemporarioAutomaticoBe = null;
	   registrarLogExecucao(null, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
	   try
	   {
	      Propriedades.setArquivoPropriedades(nomeArquivoProperties);
	      conexao = abreConexao();
	      exportaBlobGIAITCDTemporarioAutomaticoBe = new ExportaBlobGIAITCDTemporarioAutomaticoBe(conexao);
	      exportaBlobGIAITCDTemporarioAutomaticoBe.exportaGIAITCD();
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
	         if (exportaBlobGIAITCDTemporarioAutomaticoBe != null)
	         {
	            exportaBlobGIAITCDTemporarioAutomaticoBe.close();
	            exportaBlobGIAITCDTemporarioAutomaticoBe = null;
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
