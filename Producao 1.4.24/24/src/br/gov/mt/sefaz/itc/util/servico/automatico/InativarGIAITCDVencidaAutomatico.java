package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.ITCDServico;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;
import br.gov.mt.sefaz.scl.integracao.IntegracaoScl;

import java.io.IOException;

import java.sql.Connection;

import sefaz.mt.util.Propriedades;


/**
 * Classe para inativar automaticamente uma GIA por vencimento do Prazo de Protocolação
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
public class InativarGIAITCDVencidaAutomatico extends ITCDServico
{

   private static final String NOME_SERVICO = "inativargiaitcdvencidaautomatico";

	/**
	 * Método Primário
	 * @param args
	 * @implemented by Daniel Balieiro
	 */
	public static void main(String[] args) throws IOException
	{
	   ExibirLOG.exibirLog("Inicio - Execucao do servico InativarGIAITCDVencidaAutomatico");
		String nomeAtividade = "/servicos"; //Este é o diretório padrão quando é um serviço, mas pode variar a pedido da SEFAZ.
		String nomeSistema = "/itc"; //Este é o nome do seu sistema.
		String nomeSaida = "/out"; //Este é o diretório padrão de saída de um serviço, mas pode variar a pedido da SEFAZ.
		String nomeArquivoLOG = "/InativarGIAITCDVencidaAutomatico.log"; //Este é o nome do seu arquivo que será gravado o LOG.
		String nomeArquivoProperties = "ITCDS.properties"; //Este arquivo será criado e empacotado pela SEFAZ.
		Connection conexao = null;
		//Aqui você declara a sua sub classe de AbstractBe. Segue exemplo:
		GIAITCDBe giaItcdBe = null;
		registrarLogExecucao(null, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
		try
		{
			Propriedades.setArquivoPropriedades(nomeArquivoProperties);
			//Aqui seria instanciado o seu Vo.
			EntidadeVo entidadeVo = new EntidadeVo();
		   entidadeVo.setLogSefazVo(preencheLogSefazServicoAutomatico());
			//Setando usuário automático para ser responsável pela alteração dos documentos que serão alterados.
			entidadeVo.setUsuarioLogado(getCodigoUsuarioAutomatico());
			//Esta conexão criada nunca expira, ou seja, ela não tem tempo para fechar sozinha.
			conexao = abreConexao();
         
		   registrarInicioServicoMonitorSCL(conexao, NOME_SERVICO);
         
			//Aqui você instancia a sua sub classe de AbstractBe passando a conexão aberta anteriormente. Segue exemplo:
			giaItcdBe = new GIAITCDBe(conexao);
			giaItcdBe.inativarAutomaticoGIAITCD(entidadeVo);
         
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
				if (giaItcdBe != null)
				{
					giaItcdBe.close();
					giaItcdBe = null;
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
	   ExibirLOG.exibirLog("Fim - Execucao do servico InativarGIAITCDVencidaAutomatico");
	}
   
}
