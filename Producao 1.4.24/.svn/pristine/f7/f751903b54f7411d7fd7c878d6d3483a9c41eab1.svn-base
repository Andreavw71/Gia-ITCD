package br.gov.mt.sefaz.itc.util;

import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.logsefaz.LogSefazVo;
import br.com.abaco.util.servico.AbacoServico;

import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.log.ExibirLOG;
import br.gov.mt.sefaz.scl.integracao.IntegracaoScl;

import java.sql.Connection;
import java.sql.SQLException;


public class ITCDServico extends AbacoServico
{
	
   public ITCDServico()
	{
	}

	public static LogSefazVo preencheLogSefazServicoAutomatico() throws ConexaoException, ObjetoObrigatorioException, 
			  ConsultaException
	{
		LogSefazVo logSefazVo = new LogSefazVo();
		logSefazVo.setCodgModulo(ConfiguracaoITCD.CODIGO_MODULO);
		logSefazVo.getUsuario().setCodigo(new Long(getCodigoUsuarioAutomatico()).intValue());
		logSefazVo.getUsuario().setIdentificacao(ConfiguracaoITCD.IDENTIFICACAO);
		logSefazVo.getUsuario().setNome(ConfiguracaoITCD.NOME_USUARIO);
		logSefazVo.getInfoClienteBean().setBrowser(ConfiguracaoITCD.BROWSER);
		logSefazVo.getInfoClienteBean().setHostName(ConfiguracaoITCD.HOST_NAME);
		logSefazVo.getInfoClienteBean().setIpAddress(ConfiguracaoITCD.ENDERECO_IP);
		logSefazVo.getInfoClienteBean().setMacAddress(ConfiguracaoITCD.MAC);
		logSefazVo.getInfoClienteBean().setOsName(ConfiguracaoITCD.NOME_OS);
		return logSefazVo;
	}
   
   /**
    * Registra o inicio da execução do serviço no monitor de serviços do SCL.
    * @throws SQLException
    * @implemented by dherkyan.silva@sefaz.mt.gov.br
    */
   protected static void registrarInicioServicoMonitorSCL(Connection conexaoServico, String nomeServico)
      throws SQLException
   {
      ExibirLOG.exibirLog("Registrando inicio do servico no monitor SCL...");
      //ExibirLOG.exibirLogSimplificado("Conexao : "+conexaoServico.toString());
      IntegracaoScl integracaoScl = new IntegracaoScl(conexaoServico);
      integracaoScl.registrarInicioExecucaoServico(nomeServico);
      conexaoServico.commit();
      ExibirLOG.exibirLog("Inicio do servico registrado no monitor SCL...");
   }
   
   /**
    * Registra o fim da execução do serviço no monitor de serviços do SCL.
    * @throws SQLException
    * @implemented by dherkyan.silva@sefaz.mt.gov.br
    */
   protected static void registrarFimServicoMonitorSCL(Connection conexaoServico, String nomeServico)
      throws SQLException
   {
      ExibirLOG.exibirLog("Registrando fim do servico no monitor SCL...");
      //ExibirLOG.exibirLogSimplificado("Conexao : "+conexaoServico.toString());
      IntegracaoScl integracaoScl = new IntegracaoScl(conexaoServico);
      integracaoScl.registrarFimExecucaoServico(nomeServico);
      conexaoServico.commit();
      ExibirLOG.exibirLog("Fim do servico registrado no monitor SCL...");
   }
   
}
