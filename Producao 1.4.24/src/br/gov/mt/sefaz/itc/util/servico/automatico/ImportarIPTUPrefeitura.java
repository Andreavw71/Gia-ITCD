package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.ITCDServico;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import br.gov.mt.sefaz.scl.integracao.IntegracaoScl;

import java.io.IOException;

import java.sql.Connection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import sefaz.mt.util.Propriedades;


public class ImportarIPTUPrefeitura
   extends ITCDServico
{

   private static final String NOME_SERVICO = "importariptuprefeitura";
   
   public static void main(String[] args)
      throws IOException
   {
       ExibirLOG.exibirLog("Inicio - Execucao do servico ImportarIPTUPrefeitura");
      String nomeAtividade = "/servicos";
        String nomeSistema = "/itc";
        String nomeSaida = "/out";
        String nomeArquivoLOG = "/ImportarIPTUPrefeitura.log";
        String nomeArquivoProperties = "ITCDS.properties";
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formato = new SimpleDateFormat("HH:mm:ss");
        Connection conexao = null;
        Date dataInicioProcessamento = null;
        registrarLogExecucao(null, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
        ImportarIPTUPrefeituraBe importarIPTUPrefeituraBe = null;
        System.out.println(" >> Iniciando o serviço Importar Dados IPTU Prefeitura... ");
        System.out.println(" DATA: " + formatador.format(dataInicioProcessamento = new Date()) +" - Hora inicio processamento: " + formato.format(dataInicioProcessamento = new Date()));

        try {
                  Propriedades.setArquivoPropriedades(nomeArquivoProperties);
                  EntidadeVo entidadeVo = new EntidadeVo();
                  entidadeVo.setLogSefazVo(preencheLogSefazServicoAutomatico());
                  //Setando usuário automático para ser responsável pela alteração dos documentos que serão alterados.
                  entidadeVo.setUsuarioLogado(entidadeVo.getLogSefazVo().getUsuario().getCodigo());
                  conexao = abreConexao();
         
                  registrarInicioServicoMonitorSCL(conexao, NOME_SERVICO);
         
                  importarIPTUPrefeituraBe = new ImportarIPTUPrefeituraBe(conexao);
                  ExibirLOG.exibirLog("Início do Processamento");
                  importarIPTUPrefeituraBe.processarIPTUPrefeitura(entidadeVo);
                  ExibirLOG.exibirLog("Fim do Processamento");
         
                  registrarFimServicoMonitorSCL(conexao, NOME_SERVICO);
                  conexao.commit();
                 
                  throw new Exception(SEM_ERROS);
        } catch (Exception erro) {
            erro.printStackTrace();
            registrarLogExecucao(erro, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
        } catch (Error erro) {
            erro.printStackTrace();
            registrarLogExecucao(erro, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
        } catch (Throwable erro) {
            erro.printStackTrace();
            registrarLogExecucao(erro, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
        } finally {
            try {
                if (importarIPTUPrefeituraBe != null) {
                    importarIPTUPrefeituraBe.close();
                    importarIPTUPrefeituraBe = null;
                }
                fechaConexao(conexao);
                conexao = null;
            } catch (Exception erro) {
                erro.printStackTrace();
                registrarLogExecucao(erro, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
            } catch (Error erro) {
                erro.printStackTrace();
                registrarLogExecucao(erro, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
            } catch (Throwable erro) {
                erro.printStackTrace();
                registrarLogExecucao(erro, nomeAtividade, nomeSistema, nomeSaida, nomeArquivoLOG);
            }
        }
       ExibirLOG.exibirLog("Fim - Execucao do servico ImportarIPTUPrefeitura");
    }

}
