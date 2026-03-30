package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.ITCDServico;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;
import br.gov.mt.sefaz.scl.integracao.IntegracaoScl;

import java.io.IOException;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sefaz.mt.util.Propriedades;


/**
 * Classe de serviço para Alteração Automática de Status da GIA-ITCD
 * @author Daniel Balieiro
 * @implemented by Daniel Balieiro
 */
public class AlterarStatusGIAITCDAutomatico extends ITCDServico
{

   private static final String NOME_SERVICO = "alterarstatusgiaitcdautomatico";
	/**
	 * Método Primário
	 * @param args
	 * @implemented by Daniel Balieiro
	 */
	public static void main(String[] args) throws IOException
	{
	   ExibirLOG.exibirLog("Inicio - Execucao do servico AlterarStatusGIAITCDAutomatico");
		String nomeAtividade = "/servicos"; //Este é o diretório padrão quando é um serviço, mas pode variar a pedido da SEFAZ.
		String nomeSistema = "/itc"; //Este é o nome do seu sistema.
		String nomeSaida = "/out"; //Este é o diretório padrão de saída de um serviço, mas pode variar a pedido da SEFAZ.
		String nomeArquivoLOG = "/AlterarStatusGIAITCDAutomatico.log"; //Este é o nome do seu arquivo que será gravado o LOG.
		String nomeArquivoProperties = "ITCDS.properties"; //Este arquivo será criado e empacotado pela SEFAZ.
		Connection conexao = null;
		//Aqui você declara a sua sub classe de AbstractBe. Segue exemplo:
		AlterarStatusGIAITCDAutomaticoBe alterarStatusGIAITCDAutomaticoBe = null;
		registrarLogExecucao(null, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
      
		try
		{
       
		   //Esta conexão criada nunca expira, ou seja, ela não tem tempo para fechar sozinha.
         Propriedades.setArquivoPropriedades(nomeArquivoProperties);
		   conexao = abreConexao();
           
		   registrarInicioServicoMonitorSCL(conexao, NOME_SERVICO);
			//Aqui seria instanciado o seu Vo.
			EntidadeVo entidadeVo = new EntidadeVo();
			entidadeVo.setLogSefazVo(preencheLogSefazServicoAutomatico());
			//Setando usuário automático para ser responsável pela alteração dos documentos que serão alterados.
			entidadeVo.setUsuarioLogado(entidadeVo.getLogSefazVo().getUsuario().getCodigo());
			//Aqui você instancia a sua sub classe de AbstractBe passando a conexão aberta anteriormente. Segue exemplo:
			alterarStatusGIAITCDAutomaticoBe = new AlterarStatusGIAITCDAutomaticoBe(conexao);
			alterarStatusGIAITCDAutomaticoBe.alterarStatusGIAITCDAutomatico(entidadeVo);
		   
		   registrarFimServicoMonitorSCL(conexao, NOME_SERVICO);
		   conexao.commit();
         
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
				if (alterarStatusGIAITCDAutomaticoBe != null)
				{
					alterarStatusGIAITCDAutomaticoBe.close();
					alterarStatusGIAITCDAutomaticoBe = null;
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
	   ExibirLOG.exibirLog("Fim - Execucao do servico AlterarStatusGIAITCDAutomatico");
	}
   
   
   private static void testeAcessoTabela(Connection conexao)
   {
      testeTb27(conexao, ITCTB_27);
   }

   private static void testeTb27(Connection conexao, String sql)
   {
      ExibirLOG.exibirLog("INICIO TESTE SQL - ITCTB27_STATUS_ITCD");
      
      ExibirLOG.exibirLogSimplificado("SQL : "+sql);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conexao.prepareStatement(sql);
         rs = ps.executeQuery();
         
         while(rs.next())
         {
            ExibirLOG.exibirLogSimplificado(rs.getString("ITCTB14_CODG_ITCD") +" - " +rs.getString("CODG_STATUS_ITCD"));
         }
        
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         ExibirLOG.exibirLog("FIM TESTE SQL - ITCTB27_STATUS_ITCD");
         
         try
         {
            if (ps != null)
            {
               ps.close();
               ps = null;
            }
            if (rs != null)
            {
               rs.close();
               rs = null;
            }
         }catch(Exception e)
         {
            e.printStackTrace();
         }
        
      }
   }


   public static final String ITCTB_27 = "SELECT STATUS.* " + 
   " FROM ITCTB27_STATUS_ITCD STATUS " + 
   " WHERE STATUS.CODG_STATUS_ITCD = " + 
   "  (SELECT MAX(STAT.CODG_STATUS_ITCD) " + 
   "  FROM ITCTB27_STATUS_ITCD STAT " + 
   "  WHERE STAT.ITCTB14_CODG_ITCD = STATUS.ITCTB14_CODG_ITCD " + 
   "  ) " + 
   " AND STATUS.STAT_ITCD IN (8,7,21,22) " + 
   " ORDER BY STATUS.ITCTB14_CODG_ITCD ";
   
}
