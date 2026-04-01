package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.ITCDServico;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;
import br.gov.mt.sefaz.scl.integracao.IntegracaoScl;

import java.io.IOException;

import java.sql.Connection;

import sefaz.mt.util.Propriedades;


/**
 * Classe de Serviço para a Baixa Automática de DAR
 * @author Daniel Balieiro
 * @implemented by Daniel Balieiro
 */
public class BaixarDARAutomatico extends ITCDServico
{
   
   private static final String NOME_SERVICO = "baixardarautomatico";
      
	/**
	 * Método Primário
	 * @param args
	 * @implemented by Daniel Balieiro
	 */
	public static void main(String[] args) throws IOException
	{
	   ExibirLOG.exibirLog("Inicio - Execucao do servico BaixarDARAutomatico");
		String nomeAtividade = "/servicos"; //Este é o diretório padrão quando é um serviço, mas pode variar a pedido da SEFAZ.
		String nomeSistema = "/itc"; //Este é o nome do seu sistema.
		String nomeSaida = "/out"; //Este é o diretório padrão de saída de um serviço, mas pode variar a pedido da SEFAZ.
		String nomeArquivoLOG = "/BaixarDARAutomatico.log"; //Este é o nome do seu arquivo que será gravado o LOG.
		String nomeArquivoProperties = "ITCDS.properties"; //Este arquivo será criado e empacotado pela SEFAZ.
		Connection conexao = null;
		//Aqui você declara a sua sub classe de AbstractBe. Segue exemplo:
		BaixarDARAutomaticoBe baixarDARAutomaticoBe = null;
		String mensagem = null;
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
			baixarDARAutomaticoBe = new BaixarDARAutomaticoBe(conexao);
			mensagem = baixarDARAutomaticoBe.baixarDarAutomatico(entidadeVo);
         
		   registrarFimServicoMonitorSCL(conexao, NOME_SERVICO);
		   conexao.commit();
			if(Validador.isStringValida(mensagem))
			{
				throw new Exception(mensagem);
			}
			else
			{
				throw new Exception(SEM_ERROS);	
			}
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
				if (baixarDARAutomaticoBe != null)
				{
					baixarDARAutomaticoBe.close();
					baixarDARAutomaticoBe = null;
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
	   ExibirLOG.exibirLog("Fim - Execucao do servico BaixarDARAutomatico");
	}
}
