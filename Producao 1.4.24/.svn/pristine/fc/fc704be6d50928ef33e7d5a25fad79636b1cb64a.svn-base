package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.data.AbacoData;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.data.DataUtil;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe de negócio para execução automática de Alteração de Status da GIA-ITCD
 * @author Daniel Balieiro
 * @implemented by Daniel Balieiro
 */
public class AlterarStatusGIAITCDAutomaticoBe extends AbstractBe
{
	/**
	 * Construtor Padrão
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public AlterarStatusGIAITCDAutomaticoBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a Conexão com o Banco de Dados
	 * @param conn
	 * @implemented by Daniel Balieiro
	 */
	public AlterarStatusGIAITCDAutomaticoBe(Connection conn)
	{
		super(conn);
	}
	
	private static boolean isGIAVencida(Date dataLimite)
	{
		return dataLimite.before(new Date());
	}

	/**
	 * Método para alterar o Status da GIA-ITCD
	 * @param entidadeVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws RegistroExistenteException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void alterarStatusGIAITCDAutomatico(EntidadeVo entidadeVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, ParametroObrigatorioException, IntegracaoException, 
			  RegistroNaoPodeSerUtilizadoException, LogException, PersistenciaException, AnotacaoException, 
			  RegistroExistenteException, SQLException, IOException
   {
		// Tratamento das GIAs
		for (Iterator iteGias = getListGIAITCDStatusAutomatico().iterator(); iteGias.hasNext(); )
		{
			GIAITCDVo giaAtual = (GIAITCDVo) iteGias.next();
			giaAtual.setLogSefazVo(entidadeVo.getLogSefazVo());
			giaAtual.setUsuarioLogado(entidadeVo.getUsuarioLogado());
			//giaAtual.setStatusVo(getStatusGIAITCDStatusAutomatico(giaAtual));
			Date dataLimite = getDataLimiteGIAITCDStatusAutomatico(giaAtual);
			if (isGIAVencida(dataLimite))
			{
			   ExibirLOG.exibirLog("Enviando para divida ativa GIA-ITCD : "+giaAtual.getCodigo() ,giaAtual.getCodigo());
			   SefazDataHora sfzDataLimite = new SefazDataHora(dataLimite.getTime());
			   ExibirLOG.exibirLogSimplificado("Data limite : "+sfzDataLimite.toString());
				enviarDividaAtiva(giaAtual);
				try
				{
					conn.commit();
				}
				catch(SQLException e)
				{
					throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
				}
			}
		}
		// Tratamento para GIA parcelada
//		for (Iterator iteGias = getListGIAITCDParceladaStatusAutomatico().iterator(); iteGias.hasNext(); )
//		{
//			GIAITCDVo giaAtual = (GIAITCDVo) iteGias.next();
//			GIAITCDDarVo giaDarAtual = new GIAITCDDarVo();
//			giaDarAtual.setGia(giaAtual);
//			giaDarAtual = new GIAITCDDarBe(conn).consultarUltimoGIAITCDDar(new GIAITCDDarVo(giaDarAtual));
//			giaAtual.setGiaITCDDarVo(giaDarAtual);
//
//			if (giaAtual.getGiaITCDDarVo().getSubstituido().is(DomnSimNao.NAO) && 
//						giaAtual.getGiaITCDDarVo().getDarEmitido().getStatDar().is(DomnStatDar.PENDENTE))
//			{
//				Date dataEmissao = giaAtual.getGiaITCDDarVo().getDarEmitido().getDataEmissao();
//				Date dataLimite = new SefazDataHora(dataEmissao).adicionaDia(ConfiguracaoITCD.PRAZO_MAXIMO_GIA_ITCD_PARCELADA).toUtilData();
//				dataLimite = (new AbacoData()).getProximoDiaUtil(dataLimite, giaAtual.getMunicipioProtocolar().getCodgMunicipio().intValue());
//				if (isGIAVencida(dataLimite))
//				{
//					enviarDividaAtiva(giaAtual);
//				   try
//				   {
//				      conn.commit();
//				   }
//				   catch(SQLException e)
//				   {
//				      throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
//				   }				
//				}
//			}
//		}
	}

	/**
	 * Método para enviar para a Divida Ativa
	 * @param gia
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws RegistroExistenteException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void enviarDividaAtiva(GIAITCDVo gia) throws ObjetoObrigatorioException, RegistroNaoPodeSerUtilizadoException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException, IntegracaoException, ConsultaException, 
			  ParametroObrigatorioException, RegistroExistenteException, 
             IOException
   {
		StatusGIAITCDVo status = gia.getStatusVo();
		status.setGiaITCDVo(gia);
		status.setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA));
		status.getServidor().setNumrMatricula(StringUtil.toLongWrapper(gia.getLogSefazVo().getUsuario().getIdentificacao()));
		status.setDataEnvioInscricaoDividaAtiva(new Date());
		status.setJustificativaEnvioInscricaoDividaAtiva(ConfiguracaoITCD.MENSAGEM_JUSTIFICATIVA_INSCRICAO_DIVIDA_ATIVA_AUTOMATICAMENTE);
		status.setLogSefazVo(gia.getLogSefazVo());
		gia.setStatusVo(status);
		(new GIAITCDBe(conn)).alterarStatusGIAITCDAutomatico(gia);
	}

	/**
	 * Método que retorna a Lista de GIA-ITCD Parcelada
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 */
	private Collection getListGIAITCDParceladaStatusAutomatico() throws ObjetoObrigatorioException, ConsultaException, 
			  ConexaoException, ParametroObrigatorioException, IntegracaoException, SQLException, 
             IOException
   {
	   GIAITCDVo giaRetorno = new GIAITCDVo();
	   DomnStatusGIAITCD[] arrayStatus = new DomnStatusGIAITCD[] 
	                     { 
	                        new DomnStatusGIAITCD(DomnStatusGIAITCD.PARCELADO) 
	                     };
	                     
	   StatusGIAITCDVo statuses = (new StatusGIAITCDBe(conn)).listarStatusGIAITCDPorGrupoAgenfa(arrayStatus, 0);
	   GIAITCDBe giaBe = new GIAITCDBe(conn);
	   for (Iterator iteStatus = statuses.getCollVO().iterator(); iteStatus.hasNext(); )
	   {
	      StatusGIAITCDVo status = (StatusGIAITCDVo) iteStatus.next();
	      GIAITCDVo giaConsulta = new GIAITCDVo();
	      giaConsulta.setCodigo(status.getGiaITCDVo().getCodigo());
	      giaConsulta = new GIAITCDVo(giaConsulta);
	      giaRetorno.getCollVO().add(giaBe.consultarGIAITCDUsuarioNaoLotado(giaConsulta));
	   }
	   return giaRetorno.getCollVO();	
	}

	/**
	 * Método que retorna um ArrayList de GIA-ITCD para alteração de Status
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 */
	private ArrayList getListGIAITCDStatusAutomatico() throws ObjetoObrigatorioException, ConsultaException, 
			  ConexaoException, ParametroObrigatorioException, IntegracaoException, SQLException, 
             IOException
   {
	   GIAITCDVo giaRetorno = new GIAITCDVo();
	   DomnStatusGIAITCD[] arrayStatus = new DomnStatusGIAITCD[] 
								{ 
									new DomnStatusGIAITCD(DomnStatusGIAITCD.NOTIFICADO) 
									,new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO) 
									,new DomnStatusGIAITCD(DomnStatusGIAITCD.NOTIFICADO_CIENTE) 
									,new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO_CIENTE)                     
								};
	                     
	   StatusGIAITCDVo statuses = (new StatusGIAITCDBe(conn)).listarStatusGIAITCDPorGrupoAgenfa(arrayStatus, 0);
	   GIAITCDBe giaBe = new GIAITCDBe(conn);
	   for (Iterator iteStatus = statuses.getCollVO().iterator(); iteStatus.hasNext(); )
	   {
	      StatusGIAITCDVo status = (StatusGIAITCDVo) iteStatus.next();
	      GIAITCDVo giaConsulta = new GIAITCDVo();
	      giaConsulta.setCodigo(status.getGiaITCDVo().getCodigo());
	      giaConsulta = new GIAITCDVo(giaConsulta);
	      giaRetorno.getCollVO().add(giaBe.consultarGIAITCDUsuarioNaoLotado(giaConsulta));
	   }
      ExibirLOG.exibirLogSimplificado("Quantidade de gias para alterar status : "+ giaRetorno.getCollVO().size());
	   return (ArrayList) giaRetorno.getCollVO();

	}

	/**
	 * Retorna o Status da GIA-ITCD
	 * @param gia
	 * @return
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	private StatusGIAITCDVo getStatusGIAITCDStatusAutomatico(GIAITCDVo gia) throws ConsultaException, 
			  ObjetoObrigatorioException
	{
		// Consultando o status de cada GIA, para resgatar os valores completos
		StatusGIAITCDVo statusGia = new StatusGIAITCDVo();
		statusGia.getGiaITCDVo().setCodigo(gia.getCodigo());
		statusGia.getStatusGIAITCD().setValorCorrente(gia.getStatusVo().getStatusGIAITCD().getValorCorrente());
		statusGia = new StatusGIAITCDVo(statusGia);
		return (new StatusGIAITCDBe(conn)).consultarStatusGIAITCD(statusGia);
	}

	/**
	 * Retorna a Data Limite para a modificação do Status
	 * @param gia
	 * @return
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Daniel Balieiro
	 */
	private Date getDataLimiteGIAITCDStatusAutomatico(GIAITCDVo gia) throws ParametroObrigatorioException, 
			  ConexaoException, ObjetoObrigatorioException, IntegracaoException, ConsultaException
	{
		Date data = new Date();
		switch (gia.getStatusVo().getStatusGIAITCD().getValorCorrente())
		{
			case DomnStatusGIAITCD.NOTIFICADO:
				data = gia.getStatusVo().getDataNotificacao();
				break;
			case DomnStatusGIAITCD.RETIFICADO:
				data = gia.getStatusVo().getDataEmissaoRetificacao();
				break;
			case DomnStatusGIAITCD.NOTIFICADO_CIENTE:
				data = gia.getStatusVo().getDataCienciaNotificacao();
				break;
			case DomnStatusGIAITCD.RETIFICADO_CIENTE:
				data = gia.getStatusVo().getDataCienciaRetificacao();
				break;
		}
		ConfiguracaoGerencialParametrosVo consulta = new ConfiguracaoGerencialParametrosVo();
	   ConfiguracaoGerencialParametrosVo parametro = new ConfiguracaoGerencialParametrosVo();
		parametro.setDescricaoItem(ConfiguracaoITCD.PARAMETRO_PRAZO_VENCIMENTO_DAR);
		consulta.setParametroConsulta(parametro);
		int prazoVencimentoDar = StringUtil.toInt(new ConfiguracaoGerencialParametrosBe(conn).consultarConfiguracaoGerencialParametros(consulta).getValorItem());
		Date dataLimite = DataUtil.adicionaDia(data, prazoVencimentoDar);
		AbacoData ad = new AbacoData();		
		if(!ad.isDiaUtil(dataLimite, gia.getMunicipioProtocolar().getCodgMunicipio().intValue()))
		{
			dataLimite = ad.getProximoDiaUtil(dataLimite, gia.getMunicipioProtocolar().getCodgMunicipio().intValue());  	
		}
		ad = null;
		return dataLimite;
		
	}

	/**
	 * Método para verificar se o Pagamento da GIA-ITCD foi efetuado
	 * @param gia
	 * @return
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	private boolean verificaPagamentoGIAITCDStatusAutomatico(GIAITCDVo gia) throws ConsultaException, 
			  ObjetoObrigatorioException
	{
		//Verificar se foi efetuado o pagamento
		// Array de Status possíveis
		ArrayList listDomnPago = new ArrayList();
		listDomnPago.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.IMPUGNADO));
		listDomnPago.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.PARCELADO));
		listDomnPago.add(new DomnStatusGIAITCD(DomnStatusGIAITCD.QUITADO));
		StatusGIAITCDBe statusBe = new StatusGIAITCDBe(conn);
		for (Iterator iteDomnPago = listDomnPago.iterator(); iteDomnPago.hasNext(); )
		{
			// Procura Status dessa GIA para o tipo do Array
			StatusGIAITCDVo statusGiaAtual = new StatusGIAITCDVo();
			statusGiaAtual.getGiaITCDVo().setCodigo(gia.getCodigo());
			statusGiaAtual.setStatusGIAITCD((DomnStatusGIAITCD) iteDomnPago.next());
			statusGiaAtual = new StatusGIAITCDVo(statusGiaAtual);
			statusGiaAtual = statusBe.consultarStatusGIAITCD(statusGiaAtual);
			if (Validador.isNumericoValido(statusGiaAtual.getCodigo()))
			{
				return true;
			}
		}
		// caso não tenha sido encontrado nenhum pagamento	
		return false;
	}
}
