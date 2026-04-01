/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CopiarJustificativaGIAParaJustificativaStatus.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data criação : 28/10/2014
 * Data ultima revisão : 28/10/2014
 */
package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.ITCDServico;

import java.io.IOException;

import java.sql.Connection;

import sefaz.mt.util.Propriedades;


public class CopiarJustificativaGIAParaJustificativaStatus extends ITCDServico
{
   public CopiarJustificativaGIAParaJustificativaStatus()
   {
   }


   /**
    * <b>Método : </b>
    * 
    * 
    * 
    * 
    * @param args
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public static void main(String[] args) throws IOException
   {

      String nomeAtividade = "/servicos";
      String nomeSistema = "/itc";
      String nomeSaida = "/out";
      String nomeArquivoLOG = "/CopiarJustificativaGIAParaJustificativaStatus.log";
      String nomeArquivoProperties = "ITCDS.properties";
      Connection conexao = null;
      CopiarJustificativaGIAParaJustificativaStatusBe be = null;
      registrarLogExecucao(null, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);

      try
      {
         Propriedades.setArquivoPropriedades(nomeArquivoProperties);
         conexao = abreConexao();
        
         
         //Aqui seria instanciado o seu Vo.
         EntidadeVo entidadeVo = new EntidadeVo();
         entidadeVo.setLogSefazVo(preencheLogSefazServicoAutomatico());
         //Setando usuário automático para ser responsável pela alteração dos documentos que serão alterados.
         entidadeVo.setUsuarioLogado(entidadeVo.getLogSefazVo().getUsuario().getCodigo());

         
         System.out.println(DIRETORIO_PADRAO_SEFAZ);
         
         be = new CopiarJustificativaGIAParaJustificativaStatusBe( conexao  );
         
         be.iniciarCopiaJustificativa( entidadeVo );
                
         throw new Exception(SEM_ERROS);
      } catch (ConexaoException e)
      {
         e.printStackTrace();
         registrarLogExecucao(e, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
      } catch (ObjetoObrigatorioException e)
      {
          e.printStackTrace();
          registrarLogExecucao(e, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
      } catch (Throwable e)
      {
         e.printStackTrace();
         registrarLogExecucao(e, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
      }		finally
		{
			try
			{
				if ( be != null )
				{
               be.close();
               be = null;
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
