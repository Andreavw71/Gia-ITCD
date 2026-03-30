package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;

import br.gov.mt.sefaz.eprocess.integracao.DomnStatusProcesso;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.AvaliacaoBemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.AvaliacaoBemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario.GIAITCDTemporarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.aliquota.GIAITCDSeparacaDivorcioAliquotaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.TipoProcessoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.TipoProcessoVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.ITCDServico;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSituacaoProcessamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAvaliacao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoEprocess;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.integracao.eprocess.EprocessBe;
import br.gov.mt.sefaz.itc.util.integracao.eprocess.ProcessoIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;
import br.gov.mt.sefaz.itc.util.log.ExibirLOG;
import br.gov.mt.sefaz.scl.integracao.IntegracaoScl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.net.InetAddress;


import java.util.Calendar;

import sefaz.mt.util.DBUtilException;
import sefaz.mt.util.Propriedades;
import sefaz.mt.util.SefazDisplay;


/**
 * Serviço automático responsável por realizar
 * automaticamente o protocolo da GIA, a avaliação
 * e também a notificação
 */
public class ProtocolarGerarDarAvaliacaoAutomatico extends ITCDServico
{
   private Connection conexao;
   private EntidadeVo entidadeVo;
   private static final long NUMR_MATRICULA_SERVIDOR_SERVICO = 999999997L;
   private static final String NOME_ATIVIDADE = "/servicos"; //Este é o diretório padrão quando é um serviço, mas pode variar a pedido da SEFAZ.
   private static final String NOME_SISTEMA = "/itc"; //Este é o nome do seu sistema.
   private static final String NOME_SAIDA = "/out"; //Este é o diretório padrão de saída de um serviço, mas pode variar a pedido da SEFAZ.
   private static final String NOME_ARQUIVO_LOG = "/ProtocolarGerarDarAvaliacaoAutomatico.log"; //Este é o nome do seu arquivo que será gravado o LOG.
   private static final String NOME_ARQUIVO_PROPERTIES = "ITCDS.properties"; //Este arquivo será criado e empacotado pela SEFAZ.
   private static final String CONFIG_GERENCIAL = "CÓDIGO DE AGENFA DE PROTOCOLO E AVALIAÇÃO AUTOMÁTICA";
   private static final String INFO_OBSERVACAO = "Avaliação automática";
   private static final String NOME_SERVICO = "protocolargerardaravaliacaoautomatico";
   private SefazDisplay log = null;
   
   /**
    * Construtor que inicia a conexão utilizando o método da classe AbacoServico.
    */
   public ProtocolarGerarDarAvaliacaoAutomatico()
      throws ConexaoException, ObjetoObrigatorioException, SQLException
   {
      Propriedades.setArquivoPropriedades(NOME_ARQUIVO_PROPERTIES);
      this.conexao = abreConexao();
      this.conexao.setAutoCommit(false);
      this.log = new SefazDisplay("/usr/appl/servicos/itc/out/ProtocolarGerarDarAvaliacaoAutomatico.log");
   }
   /**
    * Método main do serviço executando o método principal.
    */
   public static void main(String[] args)
      throws ConsultaException, DBUtilException, ConexaoException, 
             ObjetoObrigatorioException, IntegracaoException, 
             ParametroObrigatorioException, Exception
   {
      ExibirLOG.exibirLog("Inicio - Execucao do servico ProtocolarGerarDarAvaliacaoAutomatico");
      new ProtocolarGerarDarAvaliacaoAutomatico().executaServico();
      //new ProtocolarGerarDarAvaliacaoAutomatico().testeServico();
      ExibirLOG.exibirLog("Fim - Execucao do servico ProtocolarGerarDarAvaliacaoAutomatico");
   }
   
   /**
    * Método principal que executa o serviço da GIA desde o Protocolo até
    * a notificação.
    */
   private void executaServico()
      throws SQLException, IOException, ConexaoException, ObjetoObrigatorioException
   {
      //Objetos necessário para realizar o serviço (Classes de negócio e Vo's)
      GIAITCDTemporarioVo giaITCDTemporarioVo = null;
      GIAITCDTemporarioBe giaITCDTemporarioBe = null;
      EprocessBe eprocessBe = null;
      TipoProcessoBe tipoProcessoBe = null;
      GIAITCDBe giaITCDBe = null;
      StatusGIAITCDVo statusGIAITCDVo = null;
      List<Long> contadorgGIAProtocolada = new ArrayList<Long>();
      List<Long> contadorgGIAComErro = new ArrayList<Long>();
      
      try 
      {
         /*Grava na tabela do monitor o inicio do serviço,
          *além de gravar o inicio da execução no LOG */
         registrarInicioServicoMonitorSCL(conexao, NOME_SERVICO);
         
         //registrarLogExecucao(null, NOME_ATIVIDADE, NOME_SISTEMA, NOME_SAIDA, NOME_ARQUIVO_LOG);
         
         log.println(SefazDisplay.OPERATIONAL,">> Inicio do Processamento das GIA's!");
         
         /* Cria os objetos entidades para preencher o log dos objetos
          * para poder acessar as tabelas sem a autenticação da GIA */
         entidadeVo = new EntidadeVo();
         
         entidadeVo.setLogSefazVo(preencheLogSefazServicoAutomatico());
         entidadeVo.setUsuarioLogado(entidadeVo.getLogSefazVo().getUsuario().getCodigo());
         //Busca a lista de todas as gias que estão com status protocolo Automatico e versão 1.2
         giaITCDTemporarioBe = new GIAITCDTemporarioBe(conexao);
         giaITCDTemporarioVo = retornaListaGIAConsultaParametrizada();
         
         //giaITCDTemporarioBe.listarGIAITCDTemporario(giaITCDTemporarioVo);
         Collection listaGiaTemporaria = new ArrayList();
         giaITCDTemporarioBe.listarDadosBasicosGIAITCDTemporario(giaITCDTemporarioVo);
         
         if(giaITCDTemporarioVo.getCollVO().size() > 0)
         {
            listaGiaTemporaria.addAll(giaITCDTemporarioVo.getCollVO());
         }
         giaITCDTemporarioVo = retornaListaGIAConsultaParametrizada();
         giaITCDTemporarioVo.getParametroConsulta().setNumeroVersaoGIAITCD(new DomnVersaoGIAITCD(DomnVersaoGIAITCD.VERSAO_1_4));
         giaITCDTemporarioBe.listarDadosBasicosGIAITCDTemporario(giaITCDTemporarioVo);
         if(giaITCDTemporarioVo.getCollVO().size() > 0)
         {
            listaGiaTemporaria.addAll(giaITCDTemporarioVo.getCollVO());
         }
         
         giaITCDTemporarioVo.setCollVO(listaGiaTemporaria);
         
         ExibirLOG.exibirLog("Quantidade inicial de GIA's para processamento - "+giaITCDTemporarioVo.getCollVO().size());
         //Caso não há nenhum resultado sai do serviço
         if(giaITCDTemporarioVo.getCollVO().isEmpty()) 
         {
            log.println(SefazDisplay.OPERATIONAL,">> Não há GIAS disponiveís para o processamento!");
            //fimServicoMonitorSCL();
            System.exit(0);
         }
         
         //Cria objeto de integração com o eprocess
         eprocessBe = new EprocessBe(conexao);

         //Itera casa gia que foi encontrada com o protocolo automatico e versao 1.2
         for(GIAITCDTemporarioVo giaBasica : giaITCDTemporarioVo.getCollVO())
         {  

            GIAITCDTemporarioVo giaITCDTemporarioIterator = new GIAITCDTemporarioVo(giaBasica.getCodigo());           
            
            //Verifica se o último status é pendente de protocolo, caso seja continua o processamento
            statusGIAITCDVo = retornaUltimoStatus(giaITCDTemporarioIterator);
            
            ExibirLOG.exibirLog("Inicio GIA: " +  giaBasica.getCodigo() + " - Versão : " + giaBasica.getNumeroVersaoGIAITCD().getTextoCorrente() + " - Status : " +  statusGIAITCDVo.getStatusGIAITCD().getTextoCorrente());

            if(statusGIAITCDVo.getStatusGIAITCD().is(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO) && !(giaBasica.getNumeroVersaoGIAITCD().getTextoCorrente().equals("GIA Versão 1.2")))
            {
               //Retorna a coleção de processos que possui o código da GIA-ITCD a ser Iterada
               Collection<ProcessoIntegracaoVo> colecaoProcessos = eprocessBe.listarProcessoPorCodigoGiaITCD(giaITCDTemporarioIterator.getCodigo());
               
               //Caso não haja processos pega a próxima GIA
               if(colecaoProcessos.isEmpty()){
                  continue;
               }
               
               //Instancia as classes de negócio
               tipoProcessoBe = new TipoProcessoBe(conexao);
               giaITCDBe = new GIAITCDBe(conexao);
               try
               {
		
                  for (ProcessoIntegracaoVo processo: colecaoProcessos)
                  {
                     //Busca o tipo de processo automático com o eprocess
                     TipoProcessoVo tipoProcessoVo = new TipoProcessoVo(DomnTipoProcessoEprocess.PROCESSO_AUTOMATICO);
                     tipoProcessoVo = tipoProcessoBe.buscarTipoProcesso(tipoProcessoVo);

                     /* Verifica se o numero do processo e o ano são válidos além do status do processo ser
                   * diferente Apensado e finalizado */
                     if ((processo.getNumeroProcesso() != 0 && processo.getAnoProcesso() != 0) && 
                        (!processo.getStatusProcesso().is(DomnStatusProcesso.APENSADO) && 
                        !processo.getStatusProcesso().is(DomnStatusProcesso.FINALIZADO)) && 
                        (processo.getTipoProcessoIntegracaoVo().getCodgTipoProcesso().equals(tipoProcessoVo.getTipoProcessoIntegracaoVo().getCodgTipoProcesso())))
                     {
                        ExibirLOG.exibirLog("ENCONTROU PROCESSO N°: " +  processo.getNumeroProcesso() +"/"+  processo.getAnoProcesso());                                                
                        
                        giaITCDTemporarioIterator = new GIAITCDTemporarioVo(giaITCDTemporarioIterator);       
                        // Consulta a gia Completa com os campos BLOB e XML.
                        giaITCDTemporarioBe.consultaGIAITCDTemporario( giaITCDTemporarioIterator );
                        
                        //Transforma o XML em um VO da TB 14 (GIAITCDVo)
                        GIAITCDVo giaITCDInternoVo = giaITCDTemporarioIterator.getGiaitcdVo();
                        
                        //Cria o registro na TB14 da GIA           
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.YEAR, 2025);
                        cal.set(Calendar.MONTH, Calendar.DECEMBER); 
                        cal.set(Calendar.DAY_OF_MONTH, 31);
                        cal.set(Calendar.HOUR_OF_DAY, 23);
                        cal.set(Calendar.MINUTE, 59);
                        cal.set(Calendar.SECOND, 59);
                        cal.set(Calendar.MILLISECOND, 0);
                        Date dataLimite = cal.getTime();
                        
                        // Nao permitir as de doação que forem antes de 01/01/2026 e de resto permitir tudo
                        if(!(giaITCDInternoVo instanceof GIAITCDDoacaoVo && giaITCDInternoVo.getDataCriacao().before(dataLimite))){
                         
                        ExibirLOG.exibirLog("Inicio - Procesasmento GIA: " +   giaITCDInternoVo.getCodigo(), giaITCDInternoVo.getCodigo());
                        ExibirLOG.exibirLog("Lista de status inicias da GIA - " + giaITCDInternoVo.getCodigo());
                        StatusGIAITCDBe.imprimirListaDeStatusNoLog(conexao, giaITCDInternoVo.getCodigo());

                        ExibirLOG.exibirLog("Migrando a GIA para a ITCTB14_ITCD - " + giaITCDInternoVo.getCodigo());
                        giaITCDBe.incluirGIAITCD(giaITCDInternoVo);

                        //Insere o novo status da GIA (PROTOCOLADO)
                        ExibirLOG.exibirLog("Inicio - Rotina - Insercao do status PROTOCOLADO", giaITCDInternoVo.getCodigo());
                        StatusGIAITCDBe.imprimirListaDeStatusNoLog(conexao, giaITCDInternoVo.getCodigo());
                        GIAITCDVo giaITCDInternoConsultaVo = new GIAITCDVo(giaITCDInternoVo.getCodigo());
                        giaITCDInternoConsultaVo.setNumeroVersaoGIAITCD(giaITCDInternoVo.getNumeroVersaoGIAITCD());
                        giaITCDInternoConsultaVo.setTipoProtocoloGIA(new DomnTipoProtocolo(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO));
                        setUsuarioLogSefaz(giaITCDInternoVo);
                        giaITCDInternoVo.setParametroConsulta(giaITCDInternoConsultaVo);
                        ExibirLOG.exibirLog("Inicio - Rotina - (Alterar Status Manual)", giaITCDInternoVo.getCodigo());
                        giaITCDBe.alterarStatusGIAITCDManual(giaITCDInternoVo);
                        ExibirLOG.exibirLog("Fim - Rotina - (Alterar Status Manual)", giaITCDInternoVo.getCodigo());
                        StatusGIAITCDBe.imprimirListaDeStatusNoLog(conexao, giaITCDInternoVo.getCodigo());

                        //Realiza ajustes nas informações do novo status para adequação do serviço
                        atualizaStatusProtocoloAutomatico(processo, giaITCDInternoVo);
                        ExibirLOG.exibirLog("Fim - Rotina - Inserção do status PROTOCOLADO", giaITCDInternoVo.getCodigo());

                        //Realiza a avaliação automática da GIA com os bens inserindo também um novo status
                        avaliacaoAutomatica(giaITCDInternoVo);

                        //Muda o status da GIA para avaliado e avalia a GIA
                        ExibirLOG.exibirLog("Início - Rotina - Alterando o STATUS para avaliado", giaITCDInternoVo.getCodigo());
                        StatusGIAITCDBe.imprimirListaDeStatusNoLog(conexao, giaITCDInternoVo.getCodigo());
                        alterarStatusParaAvaliado(giaITCDInternoVo);
                        ExibirLOG.exibirLog("Fim - Rotina - Alterando o STATUS para avaliado", giaITCDInternoVo.getCodigo());
                        StatusGIAITCDBe.imprimirListaDeStatusNoLog(conexao, giaITCDInternoVo.getCodigo());
                        giaITCDInternoVo.setConsultaCompleta(true);
                        giaITCDInternoVo.setParametroConsulta(new GIAITCDVo(giaITCDInternoVo.getCodigo()));
                        giaITCDInternoVo = giaITCDBe.consultarGIAITCD(giaITCDInternoVo);
                        giaITCDInternoVo.setImprimirDar(true);
                        setUsuarioLogSefaz(giaITCDInternoVo);

                        giaITCDInternoVo.setImprimirDar(true);

                        //Valida os documentos para imprimir o dar
                        giaITCDBe.validaImprimirDocumentosAvaliacao(giaITCDInternoVo);

                        giaITCDInternoVo = giaITCDBe.consultarGIAITCD(giaITCDInternoVo);

                        //Cria uma nova instância da classe BE da gia para poder gerar o DAR
                        setUsuarioLogSefaz(giaITCDInternoVo);

                        setUsuarioLogSefaz(giaITCDInternoVo);

                        atualizaUltimoStatusMatriculaServidor(giaITCDInternoVo);
                        
                        
                        StatusGIAITCDVo ultimoStatus = obterUltimoStatusDaGiaNoBD(giaITCDInternoVo);
                        
                        alterarStatusGiaITCDTemporaria(giaITCDTemporarioIterator, ultimoStatus, giaITCDTemporarioBe);

                        if (!(ultimoStatus.getStatusGIAITCD().is(DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR) || ultimoStatus.getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO) || ultimoStatus.getStatusGIAITCD().is(DomnStatusGIAITCD.ISENTO_IMPOSTO_ATE_1_UPF) || ultimoStatus.getStatusGIAITCD().is(DomnStatusGIAITCD.DISPENSADO_RECOLHIMENTO)|| ultimoStatus.getStatusGIAITCD().is(DomnStatusGIAITCD.DISPENSA_E_ISENCAO)))
                        {                        
                            if((giaITCDInternoVo.getNumeroVersaoGIAITCD().getValorCorrente() <= DomnVersaoGIAITCD.VERSAO_1_3) || (giaITCDInternoVo.getNumeroVersaoGIAITCD().getValorCorrente() <= DomnVersaoGIAITCD.VERSAO_1_4)){
                               ExibirLOG.exibirLog("Gerando DAR GIA", giaITCDInternoVo.getCodigo());
                               
                               String ipDaMaquina = InetAddress.getLocalHost().getHostAddress();                               
                               
                               giaITCDBe.gerarDAR(giaITCDInternoVo, ipDaMaquina, "0");
                            }
                            /*
                            else
                            {
                               ExibirLOG.exibirLog("Incluido Débito CCF GIA", giaITCDInternoVo.getCodigo());
                               giaITCDBe.incluirDebitoCCF(giaITCDInternoVo); 
                            }
                            */
                        }       
                        //Commit
                        conexao.commit();                                               
                        contadorgGIAProtocolada.add(giaITCDInternoVo.getCodigo());
                        ExibirLOG.exibirLog("Fim - Procesasmento GIA: " +  giaITCDInternoVo.getCodigo(), giaITCDInternoVo.getCodigo());

                        //log.println(SefazDisplay.OPERATIONAL,">> Término do processamento da GIA Nº " + giaITCDTemporarioIterator.getCodigo());
                        }else{
                           ExibirLOG.exibirLog("Não processar GIAs anteriores a 2026 - GIA ITCD " +  giaITCDInternoVo.getCodigo(), giaITCDInternoVo.getCodigo());
                        }
                     }
                  } 
               }
               catch (Exception e)
               {
                  conexao.rollback();
                  
                  giaITCDTemporarioIterator.setSituacaoProcessamento(new DomnSituacaoProcessamento(DomnSituacaoProcessamento.PROCESSADO_COM_ERRRO));
                  giaITCDTemporarioIterator.setDescricaoMensagemSituacaoErrro(e.getMessage());
                  giaITCDTemporarioBe.alterarGIAITCDTemporarioSituacao(giaITCDTemporarioIterator);
                  
                  //Commit 
                  conexao.commit();
                  
                  //registrarLogExecucao(e, NOME_ATIVIDADE, NOME_SISTEMA, NOME_SAIDA, NOME_ARQUIVO_LOG);
                  contadorgGIAComErro.add(giaITCDTemporarioIterator.getCodigo());
                  ExibirLOG.exibirLog("Falha:  no processamento da GIA." , giaITCDTemporarioIterator.getCodigo() );
                  e.printStackTrace();
                  log.println(SefazDisplay.ERROR, " Erro no processamento da GIA Nº " + giaITCDTemporarioIterator.getCodigo());
                  log.println(SefazDisplay.ERROR, "Stack Trace: ");
                  log.println(SefazDisplay.ERROR, retornaMensagemExcecao(e));
               }               
            }   
         
         registrarFimServicoMonitorSCL(conexao, NOME_SERVICO);
         
         ExibirLOG.exibirLog("GIA's processadas");
           
         for (Long codigo: contadorgGIAProtocolada)
         {
            ExibirLOG.exibirLogSimplificado("GIA: " + codigo);
         }
         
         ExibirLOG.exibirLog("Total de GIA's protocoladas: " + contadorgGIAProtocolada.size());
         
         ExibirLOG.exibirLog("GIA's com falha");
           
         for (Long codigo: contadorgGIAComErro)
         {
            ExibirLOG.exibirLogSimplificado("GIA: " + codigo);
         }
         
         ExibirLOG.exibirLog("Total de GIA's com falha: " + contadorgGIAComErro.size());
         }
      }
      catch(Throwable e) 
      {
         conexao.rollback();
         //registrarLogExecucao(e, NOME_ATIVIDADE, NOME_SISTEMA, NOME_SAIDA, NOME_ARQUIVO_LOG);
          ExibirLOG.exibirLog("Falha: O servico não iniciou o processamento das GIA.");
         log.println(SefazDisplay.ERROR, " Erro no processamento do serviço ");
         log.println(SefazDisplay.ERROR, "Stack Trace: ");
         log.println(SefazDisplay.ERROR, retornaMensagemExcecao(e));
      }
      finally 
      {
         fechaConexao(conexao);
      }
   }

   /**
    * Método que monta o Vo da GIA temporária para buscar os registros
    * de GIA que estão como protocolo automático e versão 1.2
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws ConexaoException
    * @return GIAITCDTemporarioVo
    */
   private GIAITCDTemporarioVo retornaListaGIAConsultaParametrizada()
      throws ObjetoObrigatorioException, ConsultaException, 
             ParametroObrigatorioException, IntegracaoException, 
             ConexaoException
   {
      GIAITCDTemporarioVo giaITCDParametroVo = new GIAITCDTemporarioVo();
      giaITCDParametroVo.setTipoProtocoloGIA(new DomnTipoProtocolo(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO));
      giaITCDParametroVo.setNumeroVersaoGIAITCD(new DomnVersaoGIAITCD(DomnVersaoGIAITCD.VERSAO_1_3));
      giaITCDParametroVo.getStatusITCD().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO));      
      
      GIAITCDTemporarioVo giaITCDTemporarioVo = new GIAITCDTemporarioVo(giaITCDParametroVo);
      giaITCDTemporarioVo.setConsultaCompleta(true);
      
      return giaITCDTemporarioVo;
   }

   /**
    * @param statusGiaItcdVo
    * @param processo
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @return StatusGIAITCDVo
    */
   private StatusGIAITCDVo retornaStatusGiaPreenchido(StatusGIAITCDVo statusGiaItcdVo, ProcessoIntegracaoVo processo)
      throws ObjetoObrigatorioException, ConexaoException, ConsultaException
   {
      ExibirLOG.exibirLog("Início - Rotina - preencher dados do status para atualização"+statusGiaItcdVo.getStatusGIAITCD().getTextoCorrente());
      ServidorSefazIntegracaoVo servidorSefaz = statusGiaItcdVo.getServidor();
      //SGPTB06_NUMR_MATR_SERV 
      servidorSefaz.setNumrMatricula(NUMR_MATRICULA_SERVIDOR_SERVICO);
      //NUMR_PROTOCOLO 
      statusGiaItcdVo.setProtocolo(processo.getNumeroProcesso());
      //DATA_PROTOCOLO 
      statusGiaItcdVo.setDataProtocolo(processo.getDataProtocolo());
      //FLAG_LOCAL_AVALIACAO 
      statusGiaItcdVo.setTipoAvaliacao(new DomnTipoAvaliacao(DomnTipoAvaliacao.AVALIACAO_AGENFA));
      //CODG_AGENFA_PROTOCOLO
      ConfiguracaoGerencialParametrosVo confgConsultaVo = new ConfiguracaoGerencialParametrosVo();
      confgConsultaVo.setDescricaoItem(CONFIG_GERENCIAL);
      statusGiaItcdVo.setCodigoAgenfa(Integer.parseInt(new ConfiguracaoGerencialParametrosBe(conexao).consultarConfiguracaoGerencialParametros(new ConfiguracaoGerencialParametrosVo(confgConsultaVo)).getValorItem()));
      
      //Alterar STATUS ITCD para 2
      statusGiaItcdVo.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.PROTOCOLADO));
      
      ExibirLOG.exibirLogSimplificado("Tipo Avaliação: "+statusGiaItcdVo.getTipoAvaliacao().getTextoCorrente());
      ExibirLOG.exibirLogSimplificado("Status: "+statusGiaItcdVo.getStatusGIAITCD().getTextoCorrente());
      ExibirLOG.exibirLogSimplificado("Código Agenda: "+statusGiaItcdVo.getCodigoAgenfa());
      ExibirLOG.exibirLog("Fim - Rotina - preencher dados do status para atualização"+statusGiaItcdVo.getStatusGIAITCD().getTextoCorrente());
      
      return statusGiaItcdVo;
   }

   /**
    * @param processo
    * @param giaITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws DadoNecessarioInexistenteException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    */
   private void atualizaStatusProtocoloAutomatico(ProcessoIntegracaoVo processo, GIAITCDVo giaITCDVo)
      throws ConsultaException, ObjetoObrigatorioException, ConexaoException, 
             DadoNecessarioInexistenteException, LogException, 
             AnotacaoException, PersistenciaException
   {
      ExibirLOG.exibirLog("Início - Rotina - atualização do status para protocolado" , giaITCDVo.getCodigo());
      StatusGIAITCDBe.imprimirListaDeStatusNoLog(conexao , giaITCDVo.getCodigo() );
      StatusGIAITCDBe statusBe = new StatusGIAITCDBe(conexao);
      StatusGIAITCDVo parametroBusca = new StatusGIAITCDVo();
      parametroBusca.setGiaITCDVo(new GIAITCDVo(giaITCDVo.getCodigo()));
      parametroBusca.setConsultaCompleta(true);
      parametroBusca = new StatusGIAITCDVo(parametroBusca);
      
      StatusGIAITCDVo statusGIA = statusBe.consultarStatusGIAITCD(parametroBusca);
      statusGIA = retornaStatusGiaPreenchido(statusGIA, processo);
      statusGIA.setLogSefazVo(entidadeVo.getLogSefazVo());
      statusGIA.setUsuarioLogado(entidadeVo.getUsuarioLogado());
      statusBe.alterarStatus(statusGIA);
      ExibirLOG.exibirLog("Fim - Rotina - atualização do status para protocolado" , giaITCDVo.getCodigo());
      StatusGIAITCDBe.imprimirListaDeStatusNoLog(conexao , giaITCDVo.getCodigo() );
   }

   /**
    * @param giaITCDTemporarioVo
    * @return
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws IntegracaoException
    */
   private StatusGIAITCDVo retornaUltimoStatus(GIAITCDTemporarioVo giaITCDTemporarioVo)
      throws ConsultaException, ObjetoObrigatorioException, IntegracaoException
   {
      StatusGIAITCDVo statusConsultaParametro = new StatusGIAITCDVo();
      StatusGIAITCDBe statusGIAITCDBe = new StatusGIAITCDBe(conexao);
      
      statusConsultaParametro.setGiaTemporaria(giaITCDTemporarioVo);
      StatusGIAITCDVo statusGIAITCDVo = new StatusGIAITCDVo(statusConsultaParametro);
      statusGIAITCDVo = statusGIAITCDBe.listarStatusGIAITCD(statusGIAITCDVo);
      statusGIAITCDVo = statusGIAITCDBe.procurarUltimoStatusCollectionVo(statusGIAITCDVo);
      
      return statusGIAITCDVo;
   }
   
   private void atualizaUltimoStatusMatriculaServidor(GIAITCDVo giaITCDVo)
      throws ConsultaException, ObjetoObrigatorioException, IntegracaoException, ConexaoException, 
             DadoNecessarioInexistenteException, LogException, 
             AnotacaoException, PersistenciaException
   {
      StatusGIAITCDBe statusGIAITCDBe = new StatusGIAITCDBe(conexao);
      /*
      StatusGIAITCDVo statusConsultaParametro = new StatusGIAITCDVo();
      StatusGIAITCDBe statusGIAITCDBe = new StatusGIAITCDBe(conexao);
      
      statusConsultaParametro.setGiaITCDVo(giaITCDVo);
      StatusGIAITCDVo statusGIAITCDVo = new StatusGIAITCDVo(statusConsultaParametro);
      statusGIAITCDVo = statusGIAITCDBe.listarStatusGIAITCD(statusGIAITCDVo);
      statusGIAITCDVo = statusGIAITCDBe.procurarUltimoStatusCollectionVo(statusGIAITCDVo);
      */
      StatusGIAITCDVo statusGIAITCDVo = obterUltimoStatusDaGiaNoBD(giaITCDVo);
      
      //SGPTB06_NUMR_MATR_SERV
      ServidorSefazIntegracaoVo servidorSefaz = statusGIAITCDVo.getServidor();
      servidorSefaz.setNumrMatricula(NUMR_MATRICULA_SERVIDOR_SERVICO);
      
      statusGIAITCDVo.setLogSefazVo(entidadeVo.getLogSefazVo());
      statusGIAITCDVo.setUsuarioLogado(entidadeVo.getUsuarioLogado());
      
      statusGIAITCDBe.alterarStatus(statusGIAITCDVo);
      
      //------------------------------------------------------------------------
      StatusGIAITCDVo sts =  statusGIAITCDVo;
      try
      {
         if (statusGIAITCDVo.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))
         {
            ExibirLOG.exibirLogSimplificado("Insesindo status : NOTIFICADO CIENTE");
            sts.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.NOTIFICADO_CIENTE));
            sts.setDataCienciaNotificacao(new Date());
            statusGIAITCDBe.incluirStatusGIAITCD(sts);
         }
         else if (statusGIAITCDVo.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
         {
            ExibirLOG.exibirLogSimplificado("Insesindo status : RETIFICADO CIENTE");
            sts.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO_CIENTE));
            sts.setDataCienciaRetificacao(new Date());
            statusGIAITCDBe.incluirStatusGIAITCD(sts);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   /**
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ParametroObrigatorioException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    */
   private void avaliacaoAutomatica(GIAITCDVo giaITCDVo)
      throws ObjetoObrigatorioException, ConsultaException, ConexaoException, 
             IntegracaoException, ParametroObrigatorioException, LogException, 
             PersistenciaException, AnotacaoException, SQLException, 
             IOException
   {
      BemTributavelBe bemTrivutavelBe = new BemTributavelBe(conexao);
      BemTributavelVo bemTributavelVo = new BemTributavelVo();
      GIAITCDVo consultaGiaItcdVo = new GIAITCDVo(giaITCDVo.getCodigo());
      bemTributavelVo.setGiaITCDVo(consultaGiaItcdVo);
      
      bemTributavelVo = bemTrivutavelBe.listarItemBemTributavel(new BemTributavelVo(bemTributavelVo));
      double porcentagem = bemTributavelVo.getCalculoPercentualAcordadoArbitrado();
      
      for(Object bemIterator : bemTributavelVo.getCollVO()) 
      {
         insereAvaliacao((BemTributavelVo) bemIterator, porcentagem, giaITCDVo.getPorcentagemAconsiderar());
      }
      
   }

   /**
    * @param bemTributavelVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws ConexaoException
    */
   private void insereAvaliacao(BemTributavelVo bemTributavelVo, double porcentagem, double porcentagemAconsiderar)
      throws ObjetoObrigatorioException, LogException, PersistenciaException, 
             AnotacaoException, ConexaoException
   {
      AvaliacaoBemTributavelVo bemAvaliacao = new AvaliacaoBemTributavelVo();
      //2.	ITCTB18_CODG_ITCD_BEM_TRBT = ITCTB18_ITCD_BEM_TRBT.CODG_ITCD_BEM_TRBT
      bemAvaliacao.setBemTributavel(new BemTributavelVo(bemTributavelVo.getCodigo()));
      //3. AVAL_JUDICIAL = 2
      bemAvaliacao.setAvaliacaoJudicial(new DomnSimNao(DomnSimNao.NAO));
      //4.	VALR_AVALIACAO = ITCTB18_ITCD_BEM_TRBT.VALR_MERCADO
      if((bemTributavelVo.getGiaITCDVo().getNumeroVersaoGIAITCD().getValorCorrente() <= DomnVersaoGIAITCD.VERSAO_1_3) || (bemTributavelVo.getGiaITCDVo().getNumeroVersaoGIAITCD().getValorCorrente() <= DomnVersaoGIAITCD.VERSAO_1_4)){      
         if( porcentagem >= porcentagemAconsiderar){
               if(bemTributavelVo.getConcordaComValorArbitrado().is(DomnSimNao.SIM)){
                     bemAvaliacao.setValorAvaliacao(bemTributavelVo.getValorMercado());
               }else{
                     bemAvaliacao.setValorAvaliacao(bemTributavelVo.getValorInformadoContribuinte());
               }
         }else{
                     bemAvaliacao.setValorAvaliacao(bemTributavelVo.getValorMercado());
         }         
      }    
      //5. DATA_AVALIACAO = Data Atual
      bemAvaliacao.setDataAvaliacao(new Date());
      //6. DATA_CADASTRO =Data Atual
      bemAvaliacao.setDataCadastro(new Date());
      //7. INFO_OBSERVACAO  = ‘Avaliação automática’
      bemAvaliacao.setObservacao(INFO_OBSERVACAO);
      //8. INFO_ISENTO = ITCTB18_ITCD_BEM_TRBT.ISEN_PREVISTA
      bemAvaliacao.setIsento(bemTributavelVo.getIsencaoPrevista());
      //9. DATA_ATUALIZACAO_BD = Data Atual
      bemAvaliacao.setDataAtualizacao(new Date());
      //10.   STAT_AVALIACAO = 1(fixo)
      bemAvaliacao.setStatusAvaliacao(new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
      //11.   STAT_IMPRESSAO = 2(fixo)
      bemAvaliacao.setAvaliacaoImpressa(new DomnSimNao(DomnSimNao.NAO));
      //12.	VALR_REABERTURA = null(fixo)
      bemAvaliacao.setValorReabertura(0);
      
      //Inclui no banco a avaliacao para cada registro da TB18
      AvaliacaoBemTributavelBe avaliacaoBemTributavelBe = new AvaliacaoBemTributavelBe(conexao);
      avaliacaoBemTributavelBe.incluirAvaliacaoBemTributavel(bemAvaliacao);
   }

   /**
    * @param giaITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws RegistroExistenteException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    * @throws ConexaoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    */
   private void alterarStatusParaAvaliado(GIAITCDVo giaITCDVo)
      throws ConsultaException, ObjetoObrigatorioException, 
             RegistroNaoPodeSerUtilizadoException, RegistroExistenteException, 
             ParametroObrigatorioException, IntegracaoException, 
             ConexaoException, LogException, PersistenciaException, 
             AnotacaoException, IOException
   {
      ExibirLOG.exibirLog("INICIO - chamada método alterarStatusParaAvaliado" ,giaITCDVo.getCodigo() );
      StatusGIAITCDBe.imprimirListaDeStatusNoLog( conexao , giaITCDVo.getCodigo() );
      StatusGIAITCDVo statusAvaliado = new StatusGIAITCDVo();
      StatusGIAITCDBe statusBe = new StatusGIAITCDBe(conexao);
      StatusGIAITCDVo statusNovo = null;
      //Busca o registro anterior do Status (PROTOCOLADO)
      statusAvaliado.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.PROTOCOLADO));
      statusAvaliado.setGiaITCDVo(new GIAITCDVo(giaITCDVo.getCodigo()));
      ExibirLOG.exibirLog("chamada método alterarStatusParaAvaliado - para buscar o status avaliado: "+statusAvaliado.getStatusGIAITCD().getTextoCorrente() ,statusAvaliado.getGiaITCDVo().getCodigo() );//TODO dherkyan pilha de ERRO.
      StatusGIAITCDBe.imprimirListaDeStatusNoLog( conexao , giaITCDVo.getCodigo() );
      statusNovo = statusBe.consultarStatusGIAITCD(new StatusGIAITCDVo(statusAvaliado));
      //StatusGIAITCDBe.imprimirListaDeStatusNoLog( conexao , giaITCDVo.getCodigo() );
      ExibirLOG.exibirLog("chamada método alterarStatusParaAvaliado - Status retornado: "+statusNovo.getStatusGIAITCD().getTextoCorrente() ,statusNovo.getGiaITCDVo().getCodigo() );//TODO dherkyan pilha de ERRO.
      //Pega este status PROTOCOLADO e cria um novo registro porém desta vez com o STATUS (AVALIADO)
      statusNovo.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO));
      statusNovo.setDataAtualizacao(new Date());
      statusNovo.setDataCadastroAvaliacao(new Date());
      
      ConfiguracaoGerencialParametrosVo configsVo = new ConfiguracaoGerencialParametrosVo();
      configsVo.setDescricaoItem(ConfiguracaoITCD.PARAMETRO_CODIGO_DE_AGENFA_DE_PROTOCOLO_E_AVALIACAO_AUTOMATICA);
      configsVo = new ConfiguracaoGerencialParametrosVo(configsVo);
      (new ConfiguracaoGerencialParametrosBe(conexao)).consultarConfiguracaoGerencialParametros(configsVo);
      
      try
      {
         statusNovo.setCodigoUnidadeAvaliacao(Integer.valueOf( configsVo.getValorItem()));
      }catch(NumberFormatException nfe)
      {
         ExibirLOG.exibirLog("CÓDIGO DE AGENFA DE PROTOCOLO E AVALIAÇÃO AUTOMÁTICA INVÁLIDO : "+configsVo.getValorItem() );
      }
      
      //SGPTB06_NUMR_MATR_SERV
      ServidorSefazIntegracaoVo servidorSefaz = new ServidorSefazIntegracaoVo();
      servidorSefaz.setNumrMatricula(NUMR_MATRICULA_SERVIDOR_SERVICO);
      
      //Seta o log do usuário serviço
      statusNovo.setLogSefazVo(entidadeVo.getLogSefazVo());
      statusNovo.setUsuarioLogado(entidadeVo.getUsuarioLogado());
      ExibirLOG.exibirLog("chamada método alterarStatusParaAvaliado - antes de incluido o status:" ,statusNovo.getGiaITCDVo().getCodigo() );
      statusBe.incluirStatusGIAITCD(statusNovo);
      ExibirLOG.exibirLog("FIM - chamada método alterarStatusParaAvaliado" ,giaITCDVo.getCodigo() );
      StatusGIAITCDBe.imprimirListaDeStatusNoLog( conexao , giaITCDVo.getCodigo() );
   }
   
   
   /**
    * 
    * Método em desenvolvimento
    * 
    * @param giaITCDVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws DadoNecessarioInexistenteException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @throws RegistroNaoPodeSerUtilizadoException
    * @throws RegistroExistenteException
    * @throws ParametroObrigatorioException
    * @throws IntegracaoException
    */
   private void incluirStatusNotificadoCienteOuRetificadoCiente(StatusGIAITCDVo giaITCDVo)
      throws ConsultaException, ObjetoObrigatorioException, ConexaoException, 
             DadoNecessarioInexistenteException, LogException, 
             AnotacaoException, PersistenciaException, 
             RegistroNaoPodeSerUtilizadoException, RegistroExistenteException, 
             ParametroObrigatorioException, IntegracaoException, IOException
   {
      StatusGIAITCDVo statusAvaliado = new StatusGIAITCDVo();
      StatusGIAITCDBe statusBe = new StatusGIAITCDBe(conexao);
      StatusGIAITCDVo statusNovo = null;
      //Busca o registro anterior do Status (PROTOCOLADO)
      statusAvaliado.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO));
      statusAvaliado.setGiaITCDVo(new GIAITCDVo(giaITCDVo.getGiaITCDVo().getCodigo()));
      statusNovo = statusBe.consultarStatusGIAITCD(new StatusGIAITCDVo(statusAvaliado));
      //Pega este status PROTOCOLADO e cria um novo registro porém desta vez com o STATUS (AVALIADO)
      statusNovo.setDataAtualizacao(new Date());
      statusNovo.setDataCadastroAvaliacao(new Date());
      statusNovo.setDataNotificacao(new Date());
      
      //Seta o log do usuário serviço
      statusNovo.setLogSefazVo(entidadeVo.getLogSefazVo());
      statusNovo.setUsuarioLogado(entidadeVo.getUsuarioLogado());
      
      //SETA STATUS NOTIFICADO
      statusNovo.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.NOTIFICADO));
      
      //SETA TB39
      statusNovo.setGiaTemporaria(new GIAITCDTemporarioVo(giaITCDVo.getGiaITCDVo().getCodigo()));
      
      //ALTERAR o status
      statusBe.incluirStatusGIAITCD(statusNovo);
   }

   /**
    * Registra o inicio da execução do serviço no monitor de serviços do SCL.
    * @throws SQLException
    */
   private void inicioServicoMonitorSCL()
      throws SQLException
   {
      IntegracaoScl integracaoScl = new IntegracaoScl(conexao);
      integracaoScl.registrarInicioExecucaoServico(NOME_SERVICO);
      conexao.commit();
   }
   
   /**
    * Registra o fim da execução do serviço no monitor de serviços do SCL.
    * @throws SQLException
    */
   private void fimServicoMonitorSCL()
      throws SQLException
   {
      IntegracaoScl integracaoScl = new IntegracaoScl(conexao);
      integracaoScl.registrarFimExecucaoServico(NOME_SERVICO);
      //conexao.commit();
   }
   
   private void setUsuarioLogSefaz(GIAITCDVo giaITCDVo) 
   {
      giaITCDVo.setLogSefazVo(entidadeVo.getLogSefazVo());
      giaITCDVo.setUsuarioLogado(entidadeVo.getUsuarioLogado());
   }

   private void alterarStatusGiaITCDTemporaria(GIAITCDTemporarioVo giaITCDTemporarioVo, StatusGIAITCDVo ultimoStatus, GIAITCDTemporarioBe giaITCDTemporarioBe)
      throws ObjetoObrigatorioException, LogException, AnotacaoException, 
             PersistenciaException, ConsultaException, ConexaoException
   {
      ExibirLOG.exibirLog("Início - Rotina - Atualizar status da GIA Temporaria - de: "+giaITCDTemporarioVo.getStatusITCD().getStatusGIAITCD().getTextoCorrente()+" - para: "+ultimoStatus.getStatusGIAITCD().getTextoCorrente() ,giaITCDTemporarioVo.getCodigo());
      StatusGIAITCDVo statusAuxiliar = new StatusGIAITCDVo();
      GIAITCDTemporarioVo giaTemporariaAuxiliar = new GIAITCDTemporarioVo(new GIAITCDTemporarioVo(giaITCDTemporarioVo.getCodigo()));
      giaTemporariaAuxiliar = giaITCDTemporarioBe.consultaGIAITCDTemporario(giaTemporariaAuxiliar);
      statusAuxiliar.setStatusGIAITCD(new DomnStatusGIAITCD(ultimoStatus.getStatusGIAITCD().getDomnValr()));    
      giaTemporariaAuxiliar.setSituacaoProcessamento(new DomnSituacaoProcessamento(DomnSituacaoProcessamento.PROCESSADO) );
      giaTemporariaAuxiliar.setStatusITCD(statusAuxiliar);
      giaITCDTemporarioBe.alterarGIAITCDTemporario(giaTemporariaAuxiliar);
      ExibirLOG.exibirLog("Fim - Rotina - Atualizar status da GIA Temporaria - de: "+giaITCDTemporarioVo.getStatusITCD().getStatusGIAITCD().getTextoCorrente()+" - para: "+ultimoStatus.getStatusGIAITCD().getTextoCorrente() ,giaITCDTemporarioVo.getCodigo());
   }
   
   private String retornaMensagemExcecao(Exception e) 
   {
      StringWriter stringWriterErro = new StringWriter();
      e.printStackTrace(new PrintWriter(stringWriterErro));
      return stringWriterErro.toString();
   }
   private String retornaMensagemExcecao(Throwable e) 
   {
      StringWriter stringWriterErro = new StringWriter();
      e.printStackTrace(new PrintWriter(stringWriterErro));
      return stringWriterErro.toString();
   }
   
   public void imprimirListaDeStatusTestes(Connection con , long codigo)
   {
      StatusGIAITCDVo status = new StatusGIAITCDVo();
      status.setGiaTemporaria(new GIAITCDTemporarioVo(codigo));
      status = new StatusGIAITCDVo(status);
      try
      {
         new StatusGIAITCDBe(con).listarStatusGIAITCD(status);
         for(StatusGIAITCDVo s : status.getCollVO() )
         {
            System.out.println("GIA: "+codigo +" - Status ID: "+s.getCodigo() +" : " +s.getStatusGIAITCD().getTextoCorrente());
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
   
   
   /**
    * Tem por função obter o ultimo status da GIA
    * no Banco de dados;
    * 
    * 
    */
   private StatusGIAITCDVo obterUltimoStatusDaGiaNoBD(GIAITCDVo giaITCDVo)
   {     
      StatusGIAITCDVo statusConsultaParametro = new StatusGIAITCDVo();
      StatusGIAITCDBe statusGIAITCDBe = new StatusGIAITCDBe(conexao);  
      statusConsultaParametro.setGiaITCDVo(giaITCDVo);
      StatusGIAITCDVo statusGIAITCDVo = new StatusGIAITCDVo(statusConsultaParametro);
      try
      {
         statusGIAITCDVo = statusGIAITCDBe.listarStatusGIAITCD(statusGIAITCDVo);
         statusGIAITCDVo = statusGIAITCDBe.procurarUltimoStatusCollectionVo(statusGIAITCDVo);     
      }
      catch (Exception e)
      {
        e.printStackTrace();;
      }
      return statusGIAITCDVo;
   }
   
   private void testeServico()
      throws SQLException, ObjetoObrigatorioException, LogException, 
             PersistenciaException, AnotacaoException, ConexaoException, 
             ConsultaException
   {


      /*
      GIAITCDSeparacaoDivorcioAliquotaVo aliquota = new GIAITCDSeparacaoDivorcioAliquotaVo(54);

      aliquota.setLogSefazVo(preencheLogSefazServicoAutomatico());
      aliquota.setValorBaseCalculo(200.0d);
      aliquota.setValorRecolher(100.0d);
      aliquota.setPercentualAliquota(10.d);
      aliquota.setCodigoAliquota(54);



      aliquota.setGiaItcdSeparacaoDivorcioVo(giaItcdSeparacaoDivorcioVo);

      new GIAITCDSeparacaDivorcioAliquotaBe(conexao).incluirGiaItcdSeparacaoAliquota(aliquota);
*/

       GIAITCDSeparacaoDivorcioVo giaItcdSeparacaoDivorcioVo =  new GIAITCDSeparacaoDivorcioVo();
       giaItcdSeparacaoDivorcioVo.setCodigo(2942);

         new GIAITCDSeparacaDivorcioAliquotaBe(conexao).rotinaAlterarAliquota(giaItcdSeparacaoDivorcioVo);

      conexao.commit();
   }

   

}

