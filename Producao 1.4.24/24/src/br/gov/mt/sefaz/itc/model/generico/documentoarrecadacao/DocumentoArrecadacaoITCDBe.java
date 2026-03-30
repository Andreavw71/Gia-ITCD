/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: DocumentoArrecadacaoBe.java
 * Revisão: Leandro Dorileo
 * Data revisão: 25/02/2008
 * $Id: DocumentoArrecadacaoITCDBe.java,v 1.8 2008/08/19 14:09:47 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.documentoarrecadacao;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.sefaz.integracao.arrecadacao.DarEmitidoIntegracaoVo;
import br.com.abaco.sefaz.integracao.arrecadacao.DocumentoArrecadacaoBe;
import br.com.abaco.sefaz.integracao.arrecadacao.DocumentoArrecadacaoVo;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.data.AbacoData;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.IntegracaoErro;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.util.DarException;
import br.gov.mt.sefaz.itc.util.NumeroUtil;
import br.gov.mt.sefaz.itc.util.data.DataUtil;
import br.gov.mt.sefaz.itc.util.dominio.DomnCodigoTributacaoITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.integracao.acessoweb.AcessoWebBe;
import br.gov.mt.sefaz.itc.util.integracao.acessoweb.UsuarioIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.contacorrente.ContaCorrenteBe;
import br.gov.mt.sefaz.itc.util.integracao.contacorrente.ContaCorrenteIntegracaoVo;
import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import sefaz.mt.acessoweb.Usuario;
import sefaz.mt.arrecadacao.dominio.DomnCodgMotvCancDar;
import sefaz.mt.arrecadacao.dominio.DomnCodgSistemaOrigem;
import sefaz.mt.arrecadacao.dominio.DomnStatDar;
import sefaz.mt.arrecadacao.dominio.DomnStatImpressao;
import sefaz.mt.arrecadacao.dominio.DomnStatSaidaDar;
import sefaz.mt.arrecadacao.dominio.DomnStatTransferencia;
import sefaz.mt.arrecadacao.integracao.IntegracaoArrecadacao;
import sefaz.mt.arrecadacao.modulo.dar.InfoDarBean;
import sefaz.mt.util.SefazDataHora;


/**
 * @author Leandro Dorileo
 * @version $Revision: 1.8 $
 */
public abstract class DocumentoArrecadacaoITCDBe extends AbstractBe
{
	/**
	 * Construtor Padrão
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public DocumentoArrecadacaoITCDBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * @param conn
	 * @implemented by Daniel Balieiro
	 */
	public DocumentoArrecadacaoITCDBe(Connection conn)
	{
		super(conn);
	}
	

	/**
	 * Este método é responsável pela geração de um novo DAR(Documento de Arregacação) para uma
	 * determinada GIA.
	 * 
	 * @param giaitcdVo GIA para a qual serï¿½ gerada o DAR(Documento de Arrecadação)
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws DarException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void gerarDAR(GIAITCDVo giaitcdVo, String ipClient, String portClient) throws DarException, ObjetoObrigatorioException, IOException
   {
		GIAITCDVo giaConsultada = null;
		DocumentoArrecadacaoVo documentoArrecadacaoVO = null;
		GIAITCDDarVo giaDar = null;
		try
		{
			try
			{
				giaConsultada = consultarDAR(giaitcdVo);
				
				if(Validador.isNumericoValido(giaConsultada.getGiaITCDDarVo().getDarEmitido().getNumrDarSeqc()))
				{  
					giaDar = giaConsultada.getGiaITCDDarVo();
					giaDar.setLogSefazVo(giaitcdVo.getLogSefazVo());
					giaDar.setSubstituido(new DomnSimNao(DomnSimNao.SIM));
					giaDar.setDataAtualizacao(new Date());
					new GIAITCDDarBe(conn).alterarGIAITCDDar(giaDar);                    								
				}
				// Configura os parametros para geracao de dar
				giaConsultada.getGiaITCDDarVo().setGia(giaitcdVo);
				documentoArrecadacaoVO = configuraInfoDar(giaitcdVo, giaConsultada.getGiaITCDDarVo(), false);
				geraDar(documentoArrecadacaoVO, giaitcdVo, ipClient, portClient); 
				if(Validador.isObjetoValido(giaDar) && Validador.isNumericoValido(giaDar.getDarEmitido().getNumrDarSeqc()))
				{
					cancelarDar(giaDar);	
				}			
			}
			catch (ConsultaException e)
			{
			   e.printStackTrace();
				conn.rollback();
            throw new DarException(MensagemErro.GERAR_DAR, e);
			}
			catch (ParametroObrigatorioException e)
			{
			   e.printStackTrace();
				conn.rollback();
            throw new DarException(MensagemErro.GERAR_DAR, e);
			}
			catch (LogException e)
			{
			   e.printStackTrace();
				conn.rollback();
            throw new DarException(MensagemErro.GERAR_DAR, e);
			}
			catch (ConexaoException e)
			{
			   e.printStackTrace();
				conn.rollback();
				 throw new DarException(MensagemErro.GERAR_DAR, e);
			}
			catch (RegistroExistenteException e)
			{
			   e.printStackTrace();
				conn.rollback();
            throw new DarException(MensagemErro.GERAR_DAR, e);
			}
			catch (IntegracaoException e)
			{
            e.printStackTrace();
				conn.rollback();
            throw new DarException(MensagemErro.GERAR_DAR, e);
			}
			catch (ObjetoObrigatorioException e)
			{
			   e.printStackTrace();
				conn.rollback();
				 throw new DarException(MensagemErro.GERAR_DAR, e);
			}
			catch (PersistenciaException e)
			{
			   e.printStackTrace();
				conn.rollback();
				throw new DarException(MensagemErro.GERAR_DAR, e);
			}
			catch (DadoNecessarioInexistenteException e)
			{
			   e.printStackTrace();
				conn.rollback();
				throw new DarException(MensagemErro.GERAR_DAR, e);
			}
			catch (AnotacaoException e)
			{
			   e.printStackTrace();
				conn.rollback();
				throw new DarException(MensagemErro.GERAR_DAR, e);
			}		
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DarException(MensagemErro.GERAR_DAR, new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO));
		}
	}

	/**
	 * Este metodo gera um novo DAR no sistema de arrecadacao
	 * 
	 * @param documentoArrecadacaoVO dados do dar a ser gerado
	 * @param giaitcdVo dados da gia a ser inseridos na tabela de ligacao entre gia e dar
	 * @throws IntegracaoException
	 * @throws ParametroObrigatorioException
	 * @throws SQLException
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void geraDar(DocumentoArrecadacaoVo documentoArrecadacaoVO, GIAITCDVo giaitcdVo, String ipClient, String portClient) throws IntegracaoException, 
			  ObjetoObrigatorioException, ConexaoException, ConsultaException, ParametroObrigatorioException, LogException, 
			  PersistenciaException, AnotacaoException, SQLException, 
             IOException
   {
	   ExibirLOG.exibirLog("Início - Rotina - Gerar DAR" , giaitcdVo.getCodigo());
      
      //DocumentoArrecadacaoBe integracaoBe = new DocumentoArrecadacaoBe(conn);
      /*Fazendo a alteração da chamada da integração não passando mais a conexão devido ao fato
      da geração do DAR sempre commitar a transação, sendo assim agora ele cria sua própria conexão */
      //Gerando o DAR no sistema de Arrecadação
		//DocumentoArrecadacaoBe integracaoBe = new DocumentoArrecadacaoBe();
		//DarEmitidoIntegracaoVo darEmitidoIntegracaoVO = integracaoBe.gravarDAR(documentoArrecadacaoVO);
	   /**
       * Método gravarDarUtil migrado para o ITCD para se adaptar ao novo
       * método do arrecadação (gravarDarUtil) que não comita a conexão.
       * 
       * @implemented Dherkyan Ribeiro da Silva
       */
      DarEmitidoIntegracaoVo darEmitidoIntegracaoVO = gravarDarUtil(documentoArrecadacaoVO, ipClient, portClient);
      
      ExibirLOG.exibirLog("Informações do DAR Emitido");
	   ExibirLOG.exibirLogSimplificado("Número DAR: "+darEmitidoIntegracaoVO.getNumeroDarFormatado());
	   ExibirLOG.exibirLogSimplificado("Código UOF Seqc: "+darEmitidoIntegracaoVO.getCodgUofSeqc());
	   ExibirLOG.exibirLogSimplificado("Valor Tributo: "+darEmitidoIntegracaoVO.getValorTributoFormatado());
	   ExibirLOG.exibirLogSimplificado("Valor TSE: "+darEmitidoIntegracaoVO.getValorTseFormatado());
	   ExibirLOG.exibirLogSimplificado("Valor Multa: "+darEmitidoIntegracaoVO.getValorMulta());
	   ExibirLOG.exibirLogSimplificado("Data Emissão: " + new SefazDataHora(  darEmitidoIntegracaoVO.getDataEmissao()).formata("dd/MM/yyyy") );
	   ExibirLOG.exibirLogSimplificado("Data vencimento: " + new SefazDataHora(  darEmitidoIntegracaoVO.getDataVencimento()).formata("dd/MM/yyyy") );
	   ExibirLOG.exibirLog("Fim - Rotina - Gerar DAR" , giaitcdVo.getCodigo());
      
		//Vinculando o DAR Gerado a GIA-ITCD
		GIAITCDDarVo giaDar = null;
		giaDar = new GIAITCDDarVo();
		giaDar.setGia(giaitcdVo);
		giaDar.setDarEmitido(darEmitidoIntegracaoVO);
		giaDar.setSubstituido(new DomnSimNao(DomnSimNao.NAO));
		giaDar.setNumeroParcela(1);
		giaDar.setLogSefazVo(giaitcdVo.getLogSefazVo());
		
      // Ta comentado por que o commit?
      
		//Inserindo na tabela de relacionamento entre gia e dar
		(new GIAITCDDarBe(conn)).incluirGIAITCDDar(giaDar);
		giaitcdVo.setGiaITCDDarVo(giaDar);
		//commit();
	}

	/**
	 * Imprime o último DAR de uma determinada GIA
	 * 
	 * @param giaDar dados da gia a serem usados na consulta
	 * @return StringBuffer
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws DarException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public StringBuffer imprimirDar(GIAITCDDarVo giaDar) throws DarException
	{
	   DocumentoArrecadacaoBe integracaoBe = new DocumentoArrecadacaoBe(conn);
		StringBuffer retorno = null;
		try
		{
			DarEmitidoIntegracaoVo darConsulta = new DarEmitidoIntegracaoVo();
			darConsulta.setParametroConsulta(giaDar.getDarEmitido());
			if (Validador.isNumericoValido(giaDar.getDarEmitido().getNumrDarSeqc()))
			{
				giaDar.setDarEmitido(integracaoBe.obterDarEmitidoPorNumrDarOuCodgUofSeq(darConsulta));	
			}
			giaDar.setSubstituido(new DomnSimNao(DomnSimNao.NAO));
			//montamos o dar para impressao
			retorno = integracaoBe.obterHtmlDar(darConsulta);
		}
		catch (IntegracaoException e)
		{
         e.printStackTrace();
         throw new DarException(MensagemErro.GERAR_DAR, e);
		}
		catch (ObjetoObrigatorioException e)
		{
		   e.printStackTrace();
         throw new DarException(MensagemErro.GERAR_DAR, e);
		}
		return retorno;
	}

	/**
	 * Monta um objeto de integracao com o sistema de arrecadação para a geração do DAR baseado
	 * nos dados da gia
	 * 
	 * @param giaitcdVo dados da gia
	 * @param giaDar
	 * @return DocumentoArrecadacaoVo
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConexaoException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private DocumentoArrecadacaoVo configuraInfoDar(GIAITCDVo giaitcdVo, GIAITCDDarVo giaDar, boolean isRelatorio) throws ConsultaException, 
			  ObjetoObrigatorioException, ParametroObrigatorioException, IntegracaoException, ConexaoException, 
			  DadoNecessarioInexistenteException
	{	
	   DocumentoArrecadacaoBe documentoArrecadacaoBe = null;
	   try
      {
         ExibirLOG.exibirLog("Início - Rotina - Configuração das informações do DAR" , giaitcdVo.getCodigo());
         ExibirLOG.exibirLog(" Configuração das informações do DAR - Relatório" + isRelatorio);
         SimpleDateFormat fomatador = new SimpleDateFormat("dd/MM/yyyy");
         Date dataPeriodoReferencia = determinaPeriodoRefDar(giaitcdVo, isRelatorio);
         Date dataVencimentoOriginalDar = obtemDataVencimentoDar(dataPeriodoReferencia, giaitcdVo);
         Date dataVencimentoDar = calculaVencimentoDar(dataVencimentoOriginalDar, giaitcdVo);
         documentoArrecadacaoBe = new DocumentoArrecadacaoBe(conn);
         DocumentoArrecadacaoVo documentoArrecadacaoVO = new DocumentoArrecadacaoVo();
         documentoArrecadacaoVO.setNumrPessoa(giaitcdVo.getContribuinteNotificacaoDar().getNumrContribuinte());
         documentoArrecadacaoVO.setTipoUsuarioSeqcEmissao(new Integer(Usuario.OUTROS));      
         documentoArrecadacaoVO.setValorTse(documentoArrecadacaoBe.obterValorTSE(documentoArrecadacaoVO.getTipoUsuarioSeqcEmissao()));
         
         if(giaitcdVo.getContribuinteNotificacaoDar().getSiglaUF().equals(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO))
         {
            documentoArrecadacaoVO.setCodgMunicipio(giaitcdVo.getContribuinteNotificacaoDar().getCodgMunicipio());   
         }
         else
         {
            documentoArrecadacaoVO.setCodgMunicipio(giaitcdVo.getAgenciaProtocolo().getCodgMunicipio());
         }     
         documentoArrecadacaoVO.setNumrNai(String.valueOf(giaitcdVo.getCodigo()));
         documentoArrecadacaoVO.setDataPeriodoReferencia(dataPeriodoReferencia);
         documentoArrecadacaoVO.setDataVencimento(dataVencimentoDar);
         documentoArrecadacaoVO.setCodgTributo(new Integer(DomnCodigoTributacaoITCD.ITCD_NORMAL.getCodigo()));
         documentoArrecadacaoVO.setStatSaidaDar(new Integer(DomnStatSaidaDar.PENDENTE));           
         documentoArrecadacaoVO.setValrTributo(new Double(giaitcdVo.getValorITCD()));
         documentoArrecadacaoVO.setValrCorrMonetaria(new Double(calculaCorrecaoMonetariaDAR(giaDar, dataVencimentoOriginalDar, dataVencimentoDar)));
         documentoArrecadacaoVO.setValrMulta(new Double(calculaMultaMoraDAR(giaDar, dataPeriodoReferencia, dataVencimentoOriginalDar, dataVencimentoDar)));
         documentoArrecadacaoVO.setCodgLocalEmissao(ConfiguracaoITCD.CODIGO_LOCAL_EMISSAO_DAR);      
         
         if(giaitcdVo instanceof GIAITCDDoacaoVo){
            documentoArrecadacaoVO.setValrTributo(new Double(giaitcdVo.getValorRecolhimento()));
         }
         
         //Se a GIA for de Inventário é necessário somar o valor da Multa de Mora no campo Multa.
         if (giaitcdVo instanceof GIAITCDInventarioArrolamentoVo)
         {
            //TODO Maio/2013 - foi constatado que o valor da multa de ofício (giaitcdVo.getValorMulta()) esta incorreto, 
            //TODO pois não estava somando a correção monetária ao valor do ITCD, segue a correção abaixo*/
         //       double valorMora =  ((GIAITCDInventarioArrolamentoVo) giaitcdVo).getValorMulta();
            double percMultaOficio = new GIAITCDInventarioArrolamentoBe(conn).encontraPercentualMulta((GIAITCDInventarioArrolamentoVo)giaitcdVo);
            double valorMultaOficio =  (documentoArrecadacaoVO.getValrTributo() + documentoArrecadacaoVO.getValrCorrMonetaria()) * percMultaOficio /100;
            documentoArrecadacaoVO.setValrMulta(new Double(documentoArrecadacaoVO.getValrMulta().doubleValue() + valorMultaOficio));
         }
         
         documentoArrecadacaoVO.setValrJuros(new Double(calculaJurosDAR(giaDar, dataVencimentoOriginalDar, dataVencimentoDar)));

         StringBuffer informacaoPrevista = new StringBuffer();
         informacaoPrevista.append("Número da GIA-ITCD: ").append(String.valueOf(giaitcdVo.getCodigo())).append("\n");
         informacaoPrevista.append("Valor Cálculo do Demonstrativo: R$").append(giaitcdVo.getValorCalculoDemonstrativoFormatado()).append("\n");
         informacaoPrevista.append("Demonstrativo das multas: ").append("\n");
         informacaoPrevista.append("\t"+montaMensagemMultaOficio(giaitcdVo)).append("\n");
         informacaoPrevista.append("\t"+montaMensagemMultaMora(dataPeriodoReferencia,dataVencimentoOriginalDar)).append("\n");
         informacaoPrevista.append("\bNão receber após a data de vencimento.");
         documentoArrecadacaoVO.setInfoPrevistaInstrucao(informacaoPrevista.toString());
         
         // deve ser informado o codigo do usuario que esta gerando o DAR
         documentoArrecadacaoVO.setCodigoUsuarioSeqcEmissao(new Integer((int) giaitcdVo.getUsuarioLogado()));
         
         UsuarioIntegracaoVo usuarioConsulta  = null;
         if (Validador.isNumericoValido(giaitcdVo.getUsuarioLogado()))
         {
            usuarioConsulta  = new UsuarioIntegracaoVo();
            usuarioConsulta.setCodigo(new Integer(new Long(giaitcdVo.getUsuarioLogado()).intValue()));
            usuarioConsulta.setParametroConsulta(usuarioConsulta);
            usuarioConsulta = new AcessoWebBe(conn).obterUsuarioPorCodigo(usuarioConsulta);
            documentoArrecadacaoVO.setTipoUsuarioSeqcEmissao(new Integer(usuarioConsulta.getCodgTipoUsuario()));
         }
         else
         {
            documentoArrecadacaoVO.setTipoUsuarioSeqcEmissao(new Integer(10));
         }

         documentoArrecadacaoVO.setCodgSistemaOrigem(new Integer(DomnCodgSistemaOrigem.ITCD));
         
         ExibirLOG.exibirLog("Informações enviadas para gerar o DAR");
         ExibirLOG.exibirLogSimplificado("Número Pessoa: "+documentoArrecadacaoVO.getNumrPessoa());
         ExibirLOG.exibirLogSimplificado("Código do Tributo: "+documentoArrecadacaoVO.getCodgTributo());
         ExibirLOG.exibirLogSimplificado("Código do Sistema de Origem: "+documentoArrecadacaoVO.getCodgSistemaOrigem());
         ExibirLOG.exibirLogSimplificado("Valor do Tributo: " +   documentoArrecadacaoVO.getValrTributo());
         ExibirLOG.exibirLogSimplificado("Valor do Multa: " +  documentoArrecadacaoVO.getValrMulta());
         ExibirLOG.exibirLogSimplificado("Valor do Juros: " +  documentoArrecadacaoVO.getValrJuros());
         ExibirLOG.exibirLogSimplificado("Valor do Correção Monetária: " +  documentoArrecadacaoVO.getValrCorrMonetaria());
         ExibirLOG.exibirLogSimplificado("Valor do TSE: " +  documentoArrecadacaoVO.getValorTse());
         ExibirLOG.exibirLogSimplificado("Números de Parcelas: " + documentoArrecadacaoVO.getNumrParcela());
         ExibirLOG.exibirLogSimplificado("Data vencimento: " + fomatador.format(documentoArrecadacaoVO.getDataVencimento()));
         ExibirLOG.exibirLogSimplificado("Data Periodo Referencia: " + fomatador.format(dataPeriodoReferencia));
         ExibirLOG.exibirLogSimplificado("Data Vencimento Original: " + fomatador.format(dataVencimentoOriginalDar));
         ExibirLOG.exibirLogSimplificado("Tipo Usuário Seq Emissão: " +documentoArrecadacaoVO.getTipoUsuarioSeqcEmissao() );
         ExibirLOG.exibirLogSimplificado("Número NAI: " + documentoArrecadacaoVO.getNumrNai() );
         
         
         ExibirLOG.exibirLog("Fim - Rotina - Configuração das informações do DAR" , giaitcdVo.getCodigo());
         return documentoArrecadacaoVO;
      }finally
      {
         documentoArrecadacaoBe = null;
      }
      
      
	}
   
   public DocumentoArrecadacaoVo getInfoDarRelatorio(GIAITCDVo giaitcdVo, GIAITCDDarVo giaDar) throws ConsultaException, 
           ObjetoObrigatorioException, ParametroObrigatorioException, IntegracaoException, ConexaoException, 
           DadoNecessarioInexistenteException
   {  
      return configuraInfoDar(giaitcdVo, giaDar, true);
   }

	/**
	 * Método responsável por obter o código do municipio do contribuinte ao qual o DAR irá ser gerado.
	 * @param giaITCDVo
	 * @return int
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public int obtemCodigoMunicipioContribuinteNotificacaoDar(GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
			  IntegracaoException, DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(giaITCDVo);
	   CadastroBe cadastroBe = null;
      try
      {
         ContribuinteIntegracaoVo consulta = giaITCDVo.getContribuinteNotificacaoDar();
         if (consulta.getSiglaUF() == null)
         {
            cadastroBe = new CadastroBe(conn);
            consulta = new ContribuinteIntegracaoVo(giaITCDVo.getContribuinteNotificacaoDar().getNumrContribuinte());
            consulta = new ContribuinteIntegracaoVo(consulta);
            consulta = cadastroBe.obterContribuinte(consulta);
         }

         if(consulta.getSiglaUF().equals(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO))
         {
            if(Validador.isNumericoValido(consulta.getCodgMunicipio()))
            {
               return consulta.getCodgMunicipio().intValue();
            }
            else
            {
               throw new DadoNecessarioInexistenteException(MensagemErro.NAO_FOI_POSSIVEL_CONSULTAR_MUNICIPIO_CONTRIBUINTE);
            }        
         }
         else if (Validador.isNumericoValido(giaITCDVo.getAgenciaProtocolo().getCodgMunicipio()))
         {
            return giaITCDVo.getAgenciaProtocolo().getCodgMunicipio().intValue();         
         }
         else
         {
            throw new DadoNecessarioInexistenteException(MensagemErro.NAO_FOI_POSSIVEL_CONSULTAR_MUNICIPIO_CONTRIBUINTE);
         }
         
      }finally
      {
         cadastroBe = null;
      }       		
		
	}

	/**
	 * Este metodo calcula qual o periodo de referência do DAR
	 * 
	 * @param giaItcd dados da gia a serem usados nos criterios
	 * @return java.util.Date
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public Date determinaPeriodoRefDar(final GIAITCDVo giaItcd, boolean isRelatorio) throws ConsultaException, ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{	
		Validador.validaObjeto(giaItcd);
		StatusGIAITCDVo atual = giaItcd.getStatusVo();
	   StatusGIAITCDVo statusAnterior = new GIAITCDBe(conn).obterStatusAnteriorGIAITCD(giaItcd);
	   
      ExibirLOG.exibirLog(" RELATÓRIO " + isRelatorio);
	   ExibirLOG.exibirLog(" STATUS ATUAL " + atual.getStatusGIAITCD().getTextoCorrente());
	   ExibirLOG.exibirLog(" STATUS ANTERIOR " + statusAnterior.getStatusGIAITCD().getTextoCorrente());
          
		if(statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.REATIVADO))
		{
			return AbacoData.converteDataComPrimeiroMinutoDia(statusAnterior.getDataAtualizacao());
		}
		else
		{
		   if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
		   {
		      return atual.getDataEmissaoRetificacao();
		   }
		   else if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))
		   {
		      return atual.getDataNotificacao();
		   }
		   else if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO_CIENTE))
		   {
		      return atual.getDataCienciaNotificacao();
		   }
		   else if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO_CIENTE))
		   {
		      return atual.getDataCienciaRetificacao();
		   }	
		   else if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA))
		   {		      
		      if(statusAnterior.isStatusIn(new int[] {DomnStatusGIAITCD.NOTIFICADO, DomnStatusGIAITCD.RETIFICADO, DomnStatusGIAITCD.NOTIFICADO_CIENTE, DomnStatusGIAITCD.RETIFICADO_CIENTE}))
		      {
		         if(statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
		         {
		            return statusAnterior.getDataEmissaoRetificacao();
		         }
		         else if(statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))
		         {
		            return statusAnterior.getDataNotificacao();
		         }
		         else if(statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO_CIENTE))
		         {
		            return statusAnterior.getDataCienciaNotificacao();
		         }
		         else if(statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO_CIENTE))
		         {
		            return statusAnterior.getDataCienciaRetificacao();
		         }		         
		      }
		   }	
         else if(isRelatorio)
         {
            ExibirLOG.exibirLog(" CHAMADA PELO RELATÓRIO ");
            if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO))
            {
               return atual.getDataCienciaRetificacao();
            }
            if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO))
            {
               return atual.getDataCienciaRatificacao();
            }
            if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO_CIENTE))
            {
               return atual.getDataCienciaRatificacao();
            }
            if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA))
            {
               if((StatusGIAITCDBe.contarStatusNaCollectionDoVo(giaItcd.getStatusVo(), new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO_CIENTE)) >= 1))
               {
                  return atual.getDataCienciaRetificacao();
               } 
               if((StatusGIAITCDBe.contarStatusNaCollectionDoVo(giaItcd.getStatusVo(), new DomnStatusGIAITCD(DomnStatusGIAITCD.NOTIFICADO_CIENTE)) >= 1))
               {
                  return atual.getDataCienciaNotificacao();
               }
               if((StatusGIAITCDBe.contarStatusNaCollectionDoVo(giaItcd.getStatusVo(), new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO)) >= 1))
               {
                  return atual.getDataEmissaoRetificacao();
               }
               if((StatusGIAITCDBe.contarStatusNaCollectionDoVo(giaItcd.getStatusVo(), new DomnStatusGIAITCD(DomnStatusGIAITCD.NOTIFICADO)) >= 1))
               {
                  return atual.getDataNotificacao();
               }
                            
            }
         }
		}
	   throw new ConsultaException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_NAO_ENCONTRADO_STATUS_DE_NOTIFICADO_OU_RETIFICADO);
	}


	/**
	 * Este método consulta o último dar(Documento de Arrecadação) de uma determinada GIA
	 * 
	 * @param giaitcdVo dados de parâmetro da gia(qual gia estï¿½ relacionada?)
	 * @return GIAITCDVo contendo um GIAITCDDarVo que contï¿½m um DAREmitidoVo
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @throws DarException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public GIAITCDVo consultarDAR(GIAITCDVo giaitcdVo) throws DarException
	{
		try
		{
			GIAITCDDarVo giaITCDDarVo = new GIAITCDDarVo();
			giaITCDDarVo.setGia(giaitcdVo);
			giaitcdVo.setGiaITCDDarVo(consultarDAR(giaITCDDarVo));
		}
		catch (DarException e)
		{
			 throw new DarException(MensagemErro.GERAR_DAR, e);
		}
		return giaitcdVo;
	}

	/**
	 * Este método executa efetivamente a consulta do último DAR de uma determinada gia.
	 * 
	 * @param giaITCDDarVo encapsula os dados da gia e do dar
	 * @return GIAITCDVo contendo um GIAITCDDarVo que contï¿½m um DAREmitidoVo
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @throws DarException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private GIAITCDDarVo consultarDAR(GIAITCDDarVo giaITCDDarVo) throws DarException
	{
		try
		{
			DocumentoArrecadacaoBe integracaoBe = new DocumentoArrecadacaoBe(conn);
			GIAITCDDarBe giaITCDDarBe = new GIAITCDDarBe(conn);
			giaITCDDarVo = giaITCDDarBe.consultarUltimoGIAITCDDar(new GIAITCDDarVo(giaITCDDarVo));
			DarEmitidoIntegracaoVo darConsulta = new DarEmitidoIntegracaoVo();
			darConsulta.setParametroConsulta(giaITCDDarVo.getDarEmitido());
		   if (Validador.isNumericoValido(giaITCDDarVo.getDarEmitido().getNumrDarSeqc()))
			{
				giaITCDDarVo.setDarEmitido(integracaoBe.obterDarEmitidoPorNumrDarOuCodgUofSeq(darConsulta));
			}
		}
		catch (IntegracaoException e)
		{
			throw new DarException(MensagemErro.GERAR_DAR, e);
		}
		catch (ConsultaException e)
		{
			throw new DarException(MensagemErro.GERAR_DAR, e);
		}
		catch (ObjetoObrigatorioException e)
		{
			throw new DarException(MensagemErro.GERAR_DAR, e);
		}
		catch (ParametroObrigatorioException e)
		{
			 throw new DarException(MensagemErro.GERAR_DAR, e);
		}
		return giaITCDDarVo;
	}

	/**
	 * Consulta no sistema de arrecadacao o valor da correcao monetaria para o periodo atual onde o periodo atual
	 * significa: dataInicial = primeiro dia do mes atual   e
	 * dataFinal = ultimo dia do mes atual
	 * 
	 * @param gia
	 * @param vencimentoOriginal
	 * @param vencimentoRecalculo
	 * @return double
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected double consultaCorrecaoMonetaria(GIAITCDVo gia, Date vencimentoOriginal, Date vencimentoRecalculo) throws ConsultaException, ParametroObrigatorioException, 
			  IntegracaoException
	{
		ContaCorrenteIntegracaoVo vo = null;
		ContaCorrenteBe contaCorrenteBe = null;
		
		double retorno = 0.0;
		vo = new ContaCorrenteIntegracaoVo();
		vo.setDataInicio(new SefazDataHora(vencimentoOriginal));
		vo.setDataFim(new SefazDataHora(vencimentoRecalculo));
		try
		{
			contaCorrenteBe = new ContaCorrenteBe();
			retorno = (contaCorrenteBe.getIndcCorrecaoSefaz(vo)).getIndcCorrecaoSefaz().doubleValue();
			gia.setValorCorrecaoMonetaria(retorno);
		}
		catch (SQLException e)
		{
			throw new ConsultaException(MensagemErro.CONSULTA_CORRECAO);
		}
		catch (IntegracaoException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CONTA_CORRENTE, e);
		}
		finally
		{
			if (contaCorrenteBe != null)
			{
				contaCorrenteBe.close();
				contaCorrenteBe = null;
			}
		}
		return retorno;
	}

	/**
	 * Consulta no sistema de arrecadacao o valor do percentual de juros para o periodo atual atual onde o periodo atual
	 * significa: dataInicial = primeiro dia do mes atual   e
	 * dataFinal = ultimo dia do mes atual
	 * 
	 * @param gia
	 * @param vencimentoOriginal
	 * @param vencimentoRecalculo
	 * @return double
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected double consultaPercentualJuros(GIAITCDVo gia, Date vencimentoOriginal, Date vencimentoRecalculo) throws ConsultaException, ParametroObrigatorioException, 
			  IntegracaoException
	{
		ContaCorrenteIntegracaoVo vo = null;
		ContaCorrenteBe contaCorrenteBe = null;

		double retorno = 0.0;
		vo = new ContaCorrenteIntegracaoVo();
		vo.setDataInicio(new SefazDataHora(vencimentoOriginal));
		vo.setDataFim(new SefazDataHora(vencimentoRecalculo));
		try
		{
			contaCorrenteBe = new ContaCorrenteBe();
			retorno = (contaCorrenteBe.getPercentualJurosFaz(vo)).getPercentualJurosFaz().doubleValue();
		}
		catch (SQLException e)
		{
			throw new ConsultaException(MensagemErro.CONSULTA_PERCENTUAL_JUROS);
		}
		catch (IntegracaoException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CONTA_CORRENTE, e);
		}
		finally
		{
			if (contaCorrenteBe != null)
			{
				contaCorrenteBe.close();
				contaCorrenteBe = null;
			}
		}
		return retorno;
	}

	/**
	 * Método responsável por obter a data de vencimento do DAR levando em consideração o prazo estipulado na funcionalidade de
	 * parâmetros do sistema e os feriados do município do contribuinte qual o DAR será gerado.
	 * @param periodoRefDar
	 * @param giaItcdVo
	 * @return java.uti.Date
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public Date obtemDataVencimentoDar(Date periodoRefDar, GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, IntegracaoException, 
			  DadoNecessarioInexistenteException, ConexaoException, ConsultaException, 
			  ParametroObrigatorioException
	{
	   ConfiguracaoGerencialParametrosVo parametro = new ConfiguracaoGerencialParametrosBe(conn).obterValorItem(ConfiguracaoITCD.PARAMETRO_PRAZO_VENCIMENTO_DAR);
	   if(!Validador.isStringValida(parametro.getValorItem()))
	   {
	      throw new DadoNecessarioInexistenteException("Não foi possível obter o parâmetro:  " + ConfiguracaoITCD.PARAMETRO_PRAZO_VENCIMENTO_DAR);
	   }
	   int valorParametro = StringUtil.toInt(parametro.getValorItem()); 
	   AbacoData abacoData = new AbacoData();
	   int codgMunicipioContribuinte = obtemCodigoMunicipioContribuinteNotificacaoDar(giaItcdVo);
		Date dataInicial = periodoRefDar;
		Date proximoDiaDataInicial = DataUtil.adicionaDia(dataInicial, 1);
		if(!abacoData.isDiaUtil(proximoDiaDataInicial, codgMunicipioContribuinte))
		{
			dataInicial = abacoData.getProximoDiaUtil(proximoDiaDataInicial, codgMunicipioContribuinte);
			valorParametro--;
		}
	   Date dataVencimento = DataUtil.adicionaDia(dataInicial, valorParametro);		
	   if(!abacoData.isDiaUtil(dataVencimento, codgMunicipioContribuinte))
	   {
	      return abacoData.getProximoDiaUtil(dataVencimento, codgMunicipioContribuinte);
	   }
	   return dataVencimento;		
	}

	/**
	 * Este metodo calcula os juros para a geracao de dar para uma determinada gia
	 * 
	 * @param giaDar
	 * @param vencimentoOriginal
	 * @param dataVencimentoDAR
	 * @return double
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected abstract double calculaJurosDAR(GIAITCDDarVo giaDar, Date vencimentoOriginal, Date dataVencimentoDAR) throws ConsultaException, 
			  ParametroObrigatorioException;

	/**
	 * Este metodo calcula a multa de mora para a geracao de dar para uma determinada gia
	 * 
	 * @param giaDar
	 * @param dataReferenciaDAR
	 * @param dataVencimentoOriginalDar
	 * @param dataVencimentoDAR
	 * @return double
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected abstract double calculaMultaMoraDAR(GIAITCDDarVo giaDar, Date dataReferenciaDAR, Date dataVencimentoOriginalDar, Date dataVencimentoDAR) throws ObjetoObrigatorioException, 
			  ConsultaException, ParametroObrigatorioException;

	/**
	 * monta a mensagem de multa mora
	 * 
	 * @param dataReferenciaDAR
	 * @param dataVencimentoOriginalDar
	 * @return String
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected abstract String montaMensagemMultaMora(Date dataReferenciaDAR, Date dataVencimentoOriginalDar)throws ObjetoObrigatorioException, ConsultaException, 
			  ParametroObrigatorioException;

	/**
	 * Este metodo determina a data de vencimento de um dar a ser gerado
	 * 
	 * @param periodoRefDar periodo de referencia do dar
	 * @param giaItcdVo dados da gia
	 * @return java.util.Date
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected abstract Date calculaVencimentoDar(Date periodoRefDar, GIAITCDVo giaItcdVo) throws ConsultaException, 
			  ObjetoObrigatorioException, ParametroObrigatorioException, ConexaoException, IntegracaoException, 
			  DadoNecessarioInexistenteException;

	/**
	 * Este metodo recalcula a correcao monetaria para o trubito
	 * 
	 * @param giaDar
	 * @param dataVencimentoOriginalDar
	 * @param dataVencimentoDAR
	 * @return double
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected abstract double calculaCorrecaoMonetariaDAR(GIAITCDDarVo giaDar, Date dataVencimentoOriginalDar, Date dataVencimentoDAR) throws ConsultaException, 
			  ParametroObrigatorioException;
	
	/**
	 * Monta a mensagem da Multa de Ofício
	 * 
	 * @param gia
	 * @return String
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected String montaMensagemMultaOficio(GIAITCDVo gia) throws ConsultaException, 
			  ParametroObrigatorioException, 
			  ObjetoObrigatorioException, ConexaoException
	{
		double percentualMulta = 0D;
		if (gia instanceof GIAITCDInventarioArrolamentoVo)
		{
			percentualMulta = new GIAITCDInventarioArrolamentoBe(conn).encontraPercentualMulta((GIAITCDInventarioArrolamentoVo)gia);
		}
		percentualMulta = NumeroUtil.arredondaNumero(percentualMulta, 3);
		return "Multa de Ofício: " + StringUtil.doubleToMonetario(percentualMulta, 3) + "%";
	}

	/**
	 * Método responsável por cancelar um dar emitido.
	 * @param giaITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void cancelarDar(GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, ConsultaException, 
			  ParametroObrigatorioException, IntegracaoException
	{
		Validador.validaObjeto(giaITCDVo);
		GIAITCDDarVo giaDarVo = (new GIAITCDDarBe(conn)).consultarUltimoGIAITCDDar(new GIAITCDDarVo(new GIAITCDDarVo(giaITCDVo)));		
		if(Validador.isNumericoValido(giaDarVo.getDarEmitido().getNumrDarSeqc()))
		{
			DarEmitidoIntegracaoVo darEmitido = new DarEmitidoIntegracaoVo();
			darEmitido.setNumrDarSeqc(giaDarVo.getDarEmitido().getNumrDarSeqc());
		   darEmitido.setCodgMotivoCanc(new DomnCodgMotvCancDar(DomnCodgMotvCancDar.SISTEMAITCD));
		   darEmitido.setCodgSistemaOrigem(new DomnCodgSistemaOrigem(DomnCodgSistemaOrigem.ITCD));
		   darEmitido.setStatDar(new DomnStatDar(DomnStatDar.CANCELADO));
			darEmitido.setSemUsuarioLogado(true);
			new DocumentoArrecadacaoBe(conn).cancelarDar(darEmitido);			
		}
	}

	/**
	 * Método responsável por cancelar um dar emitido.
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void cancelarDar(GIAITCDDarVo giaDar) throws ObjetoObrigatorioException, ConsultaException, 
			  ParametroObrigatorioException, IntegracaoException
	{
		Validador.validaObjeto(giaDar);
		if(Validador.isNumericoValido(giaDar.getDarEmitido().getNumrDarSeqc()))
		{
			DarEmitidoIntegracaoVo darEmitido = new DarEmitidoIntegracaoVo();
			darEmitido.setNumrDarSeqc(giaDar.getDarEmitido().getNumrDarSeqc());
			darEmitido.setCodgMotivoCanc(new DomnCodgMotvCancDar(DomnCodgMotvCancDar.SISTEMAITCD));
			darEmitido.setCodgSistemaOrigem(new DomnCodgSistemaOrigem(DomnCodgSistemaOrigem.ITCD));
			darEmitido.setStatDar(new DomnStatDar(DomnStatDar.CANCELADO));
			darEmitido.setSemUsuarioLogado(true);
			new DocumentoArrecadacaoBe(conn).cancelarDar(darEmitido);         
		}
	}

	/**
	 * Método responsável por gerar um DAR, substituindo o último DAR gerado.
	 * Pois conforme regra do sistema de Arrecadação não será reimpresso o DAR mais de uma vez,
	 * portanto a cada reimpressão um novo DAR deverá ser gerado e o último substituído.
	 * @param giaITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws IntegracaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void gerarDarManual(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, IntegracaoException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException, ConsultaException, 
			  ParametroObrigatorioException, RegistroExistenteException, IOException
   {
		Validador.validaObjeto(giaITCDVo);
		try
		{
		   try
		   {
		      GIAITCDDarBe giaITCDDarBe = new GIAITCDDarBe(conn);
		      GIAITCDDarVo giaDAR = (new GIAITCDDarBe(conn)).consultarUltimoGIAITCDDar(new GIAITCDDarVo(new GIAITCDDarVo(giaITCDVo)));      
		      if(Validador.isNumericoValido(giaDAR.getDarEmitido().getCodgUofSeqc()))
		      {
		         cancelarDar(giaITCDVo);
		      }
		      giaDAR.setSubstituido(new DomnSimNao(DomnSimNao.SIM));
		      giaITCDDarBe.alterarGIAITCDDar(giaDAR); 
		      giaDAR = new GIAITCDDarVo();
		      giaDAR.setGia(giaITCDVo);
		      giaDAR.getDarEmitido().setNumrParcela(new Integer(1));
		      giaDAR.getDarEmitido().setNumrDarSeqc(new Long(giaITCDVo.getStatusVo().getNumeroDARSegundaRetificacao()));        
		      giaITCDDarBe.incluirGIAITCDDar(giaDAR);
		   }
		   catch (ConsultaException e)
		   {
		      conn.rollback();
		       throw new ConsultaException(MensagemErro.GERAR_DAR);
		   }
		   catch (ParametroObrigatorioException e)
		   {
		      conn.rollback();
		       throw new ParametroObrigatorioException(MensagemErro.GERAR_DAR);
		   }
		   catch (RegistroExistenteException e)
		   {
		      conn.rollback();
		       throw new RegistroExistenteException(MensagemErro.GERAR_DAR);
		   }
		   catch (LogException e)
		   {
		      conn.rollback();
		      throw new LogException(MensagemErro.GERAR_DAR);
		   }
		   catch (PersistenciaException e)
		   {
		      conn.rollback();
		      throw new PersistenciaException(MensagemErro.GERAR_DAR);
		   }
		   catch (AnotacaoException e)
		   {
		      conn.rollback();
		      throw new AnotacaoException(MensagemErro.GERAR_DAR);
		   }			
		}
		catch(SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}

		
	}
   
   
   
   /**Método responsável por gerar um DAR.
    * Deve ser passado como parâmetro um Objeto do DocumentoArrecadacaoVo, sendo que os seguintes dados são obrigatórios:
    * <ul>
    * <li>PrazoVencimento
    * <li>ValrTse
    * <li>CodgSistemaOrigem
    * <li>CodgTributo
    * <li>CodgMunicipio
    * <li>NumrPessoa
    * <li>CodgUsuarioSeqcEmissao
    * <li>Tipo do Usuario
    * <li> CodgLocalEmissao
    * </ul>
    * O método irá chamar o método de integração para gerar o DAR e retornará o numéro do DAR gerado encapsulado em um
    * objeto DarEmitidoIntegracaoVo.
    * <br>Caso o DAR não seja gerado, será lançado uma exceção.
    * @param bean
    * @throws IntegracaoException
    * @throws ObjetoObrigatorioException
    * @return DarEmitidoIntegracaoVo
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public DarEmitidoIntegracaoVo gravarDarUtil(DocumentoArrecadacaoVo bean, String ipClient, String portClient) throws IntegracaoException, 
           ObjetoObrigatorioException
   {
      ExibirLOG.exibirLog("Início - Rotina - Validação e Envio das informações do para sistema de Arrecadação");
      final String PRAZO_VENCIMENTO_E_OBRIGATORIO = "O prazo de vencimento é obrigatório.";
      final String CODIGO_SISTEMA_E_OBRIGATORIO = "O código do sistema de origem é obrigatório.";
      final String CODIGO_TRIBUTO_E_OBRIGATORIO = "O código do tributo é obrigatório";
      final String CODIGO_MUNICIPIO_E_OBRIGATORIO = "O código do município é obrigatório";
      final String NUMERO_PESSOA_E_OBRIGATORIO = "O número pessoa é obrigatório";
      final String TIPO_USUARIO_E_OBRIGATORIO = " O Tipo do Usuário é obrigatório.";
      final String CODIGO_LOCAL_EMISSAO_E_OBRIGATORIO = "O Código do Local de Emissão é obrigatório.";
      
      Validador.validaObjeto(bean);
      try
      {
         if ( !Validador.isDataValida(bean.getDataVencimento()) && !Validador.isNumericoValido(bean.getPrazoVencimento()))
         {
            throw new ParametroObrigatorioException(PRAZO_VENCIMENTO_E_OBRIGATORIO);
         }
         if (!Validador.isNumericoValido(bean.getCodgSistemaOrigem()))
         {
            throw new ParametroObrigatorioException(CODIGO_SISTEMA_E_OBRIGATORIO);
         }
         if (!Validador.isNumericoValido(bean.getCodgTributo()))
         {
            throw new ParametroObrigatorioException(CODIGO_TRIBUTO_E_OBRIGATORIO);
         }
         if (!Validador.isNumericoValido(bean.getCodgMunicipio()))
         {
            throw new ParametroObrigatorioException(CODIGO_MUNICIPIO_E_OBRIGATORIO);
         }
         if (!Validador.isNumericoValido(bean.getNumrPessoa()))
         {
            throw new ParametroObrigatorioException(NUMERO_PESSOA_E_OBRIGATORIO);
         }
         if (!Validador.isNumericoValido(bean.getTipoUsuarioSeqcEmissao()))
         {
            throw new ParametroObrigatorioException(TIPO_USUARIO_E_OBRIGATORIO);
         }
         if(!Validador.isNumericoValido(bean.getCodgLocalEmissao()))
         {
            throw new ParametroObrigatorioException(CODIGO_LOCAL_EMISSAO_E_OBRIGATORIO);
         }
         
         //Criando InfoDARIntegracaoVo
         InfoDarBean info = new InfoDarBean();
         //obtendo valor do TSE
         /**
          * Deverá ser informado ou Prazo de Vencimento ou Data de Vencimento, sendo que a data de 
          * vencimento terá prioridade caso os dois sejam informados.
          */
         //Se a Data de Vencimento foi informada, ela é usada no vencimento do DAR        
         if (Validador.isDataValida(bean.getDataVencimento()))
         {
            info.setDataVencimento( new SefazDataHora( bean.getDataVencimento() ).toSqlData());
         }
         else //Caso contrário, será calculado com base no Prazo de Vencimento
         {
            java.util.Date dataVencimento = SefazDataHora.getDataHoraAtual().adicionaDia(bean.getPrazoVencimento().intValue() - 1).toUtilData();         
            dataVencimento = new AbacoData().getProximoDiaUtil( dataVencimento , bean.getCodgMunicipio().intValue() );
            info.setDataVencimento( new SefazDataHora( dataVencimento ).toSqlData());
         }        
         info.setStatDar(new Integer(DomnStatDar.PENDENTE));
         info.setStatTransferencia(new Integer(DomnStatTransferencia.NAOTRANSFERIDO));
         info.setValrTributo(bean.getValrTributo());
         info.setValrCorrMonetaria(bean.getValrCorrMonetaria());
         info.setValrMulta(bean.getValrMulta());
         info.setValrJuros(bean.getValrJuros());
         info.setValrTse(bean.getValorTse());         
         info.setStatImpresso(new Integer(DomnStatImpressao.NAOIMPRESSO));
         info.setDataHoraEmissao(SefazDataHora.getDataHoraAtual().toSqlData());
         info.setInfoPrevistaInstrucao(bean.getInfoPrevistaInstrucao());
         info.setCodgSistemaOrigem(bean.getCodgSistemaOrigem());
         info.setCodgTributo(bean.getCodgTributo());
         info.setNumrParcela(bean.getNumrParcela());
         info.setCodgMunicipio(bean.getCodgMunicipio());
         info.setNumrPessoa(bean.getNumrPessoa());
         info.setCodgUsuarioSeqcEmissao(bean.getCodigoUsuarioSeqcEmissao());
         info.setStatSaidaDar(bean.getStatSaidaDar());
         info.setNumrNai(bean.getNumrNai());
         info.setAchtb45CodgLocalEmissao(bean.getCodgLocalEmissao());     
         
         info.setNumrIpEmissao(ipClient);
         info.setNumrPortaIpEmissao(portClient);
         
         SefazDataHora dataEmissao = new SefazDataHora(bean.getDataPeriodoReferencia());
         SefazDataHora d = null;
         try
         {
            d = SefazDataHora.parseDataString("01/" + dataEmissao.getMes() + "/" + dataEmissao.getAno());
         }
         catch (ParseException e)
         {
            e.printStackTrace();
         }
         info.setDataPeriodoReferencia(d.toSqlData());
         //Chamando Gerar DAR
         ExibirLOG.exibirLogSimplificado("Gerando Dar ...");
         IntegracaoArrecadacao integracao = new IntegracaoArrecadacao(conn);
         Long numeroDar =   new Long(integracao.gravarDarUtil(info));
         DarEmitidoIntegracaoVo darEmitidoIntegracaoVo = new DarEmitidoIntegracaoVo(numeroDar);
         ExibirLOG.exibirLogSimplificado("Dar gerado com sucesso ...");
         ExibirLOG.exibirLog("Fim - Rotina - Validação e Envio das informações do para sistema de Arrecadação");
         return darEmitidoIntegracaoVo;
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_INCLUIR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_ARRECADACAO, e);
      }
      catch (Error e)
      {
         e.printStackTrace();
         throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_INCLUIR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_ARRECADACAO, e);
      }
   }
   
   
   
	
}
