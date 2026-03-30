package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.sefaz.integracao.arrecadacao.DarEmitidoIntegracaoVo;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
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
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatDar;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.integracao.arrecadacao.DocumentoArrecadacaoBe;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Iterator;


/**
 * Classe de negócio para a Baixa Automática do D.A.R.
 * @author Daniel Balieiro
 * @implemented by Daniel Balieiro
 */
public class BaixarDARAutomaticoBe extends AbstractBe
{
	/**
	 * Construtor Padrão
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public BaixarDARAutomaticoBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a Conexão com o Banco de Dados
	 * @param conn
	 * @implemented by Daniel Balieiro
	 */
	public BaixarDARAutomaticoBe(Connection conn)
	{
		super(conn);
	}

	/**
	 * Método que efetua a Baixa Automática do DAR
	 * @param entidadeVo
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws SQLException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws RegistroExistenteException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public String baixarDarAutomatico(EntidadeVo entidadeVo) throws ConsultaException, ObjetoObrigatorioException, 
			  ConexaoException, ParametroObrigatorioException, IntegracaoException, SQLException, 
             IOException
   {
		StringBuffer sb = new StringBuffer();
		GIAITCDVo gias = getGIAPorStatus();
      Integer quantidadesDeGIASQuitada = 0;
      ExibirLOG.exibirLog("Quantidade de gia encontradas: "+gias.getCollVO().size());
		for (Iterator iteGias = gias.getCollVO().iterator(); iteGias.hasNext(); )
		{
			GIAITCDVo gia = (GIAITCDVo) iteGias.next();
			gia.setUsuarioLogado(entidadeVo.getUsuarioLogado());
			gia.setLogSefazVo(entidadeVo.getLogSefazVo());
			GIAITCDDarVo giaDar = getListaDARVinculado(gia);
			giaDar.ordenaPorCodigoITCDDarDecrescente();
			for(Iterator it = giaDar.getCollVO().iterator(); it.hasNext(); )
			{
				GIAITCDDarVo atual = (GIAITCDDarVo) it.next();
				DarEmitidoIntegracaoVo darConsulta = new DarEmitidoIntegracaoVo();
				darConsulta.setParametroConsulta(atual.getDarEmitido());
				if (Validador.isNumericoValido(atual.getDarEmitido().getNumrDarSeqc()))
				{
					darConsulta = (new DocumentoArrecadacaoBe(conn)).obterDarEmitidoPorNumrDarOuCodgUofSeq(darConsulta);
				}
				if (darConsulta.getStatDar().is(DomnStatDar.QUITADO))
				{
					try
					{
						boolean quitou = false;
						if (!gia.getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA))
						{              
							quitarGia(gia);                 
							quitou = true;
						   quantidadesDeGIASQuitada++;
						}
						else
						{
							StatusGIAITCDVo statusAnterior = new StatusGIAITCDVo();
							statusAnterior.getGiaITCDVo().setCodigo(gia.getCodigo());
							statusAnterior.setStatusGIAITCD(gia.getStatusVo().getStatusGIAITCD());
							statusAnterior = (new StatusGIAITCDBe(conn)).obterStatusGIAITCDAnterior(statusAnterior);
							if (!statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO))
							{
								quitarGia(gia);
								quitou = true;
							   quantidadesDeGIASQuitada++;
							}
						}
						if(quitou)
						{
						   ExibirLOG.exibirLog("Gia Quitada : "+gia.getCodigo() +" Tipo : "+gia.getNaturezaOperacaoVo().getTipoProcesso().getTextoCorrente() );
							conn.commit();
							break;
						}                 							
					}			
					catch(Exception e)
					{
						e.printStackTrace();
						sb.append("\nNão foi possível quitar gia nº " + gia.getCodigo() + " motivo: " + e.getMessage());
						break;
					}
				}           
			}
		}
	   ExibirLOG.exibirLog("Quantidade de Gias Quitadas: "+quantidadesDeGIASQuitada );
		return sb.toString();
	}

	/**
	 * Método que altera o Status de uma GIA-ITCD para Quitado
	 * @param gia
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws SQLException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws RegistroExistenteException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void quitarGia(GIAITCDVo gia) throws ObjetoObrigatorioException, ParametroObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, SQLException, RegistroNaoPodeSerUtilizadoException, 
			  LogException, PersistenciaException, AnotacaoException, RegistroExistenteException, IOException
   {
		gia.getStatusVo().getServidor().setNumrMatricula(StringUtil.toLongWrapper(gia.getLogSefazVo().getUsuario().getIdentificacao()));
		gia.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(DomnStatusGIAITCD.QUITADO));
		(new GIAITCDBe(conn)).alterarStatusGIAITCD(gia);
	   ExibirLOG.exibirLog("Gia: "+gia.getCodigo() +" - "+DomnStatusGIAITCD.DESC_QUITADO,gia.getCodigo());
	}

	/**
	 * Médoto que retorna o Dar Vinculado
	 * @param giaVo
	 * @return
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	private GIAITCDDarVo getListaDARVinculado(GIAITCDVo giaVo) throws ConsultaException, ObjetoObrigatorioException, 
			  ConexaoException, ParametroObrigatorioException, IntegracaoException
	{
		GIAITCDDarVo giaDar = new GIAITCDDarVo();
		giaDar.setGia(new GIAITCDVo(giaVo.getCodigo()));
		giaDar = new GIAITCDDarVo(giaDar);
		return (new GIAITCDDarBe(conn)).listarGIAITCDDar(giaDar);
	}

	/**
	 * Método que retorna uma lista de GIAITCDVo que contenham os seguintes status: "NOTIFICADO", "RETIFICADO", "2ª RETIFICAÇÃO", "RATIFICADO", "PARCELADO", "PARA INSCRIÇÃO EM DIVIDA ATIVA" ou "IMPUGNADO" 
	 * @return
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Daniel Balieiro
	 */
	private GIAITCDVo getGIAPorStatus() throws ConsultaException, ObjetoObrigatorioException, ConexaoException, 
			  ParametroObrigatorioException, IntegracaoException, SQLException, 
             IOException
   {
		GIAITCDVo giaRetorno = new GIAITCDVo();
		DomnStatusGIAITCD[] arrayStatus = new DomnStatusGIAITCD[] { 
								new DomnStatusGIAITCD(DomnStatusGIAITCD.NOTIFICADO), 
								new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO), 
								new DomnStatusGIAITCD(DomnStatusGIAITCD.NOTIFICADO_CIENTE), 
								new DomnStatusGIAITCD(DomnStatusGIAITCD.RETIFICADO_CIENTE), 
								new DomnStatusGIAITCD(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA) };
								
		StatusGIAITCDVo statuses = (new StatusGIAITCDBe(conn)).listarStatusGIAITCDPorGrupoAgenfa(arrayStatus, 0);
	   ExibirLOG.exibirLog("Quantidade de status encontrados : "+statuses.getCollVO().size());
		GIAITCDBe giaBe = new GIAITCDBe(conn);
		for (Iterator iteStatus = statuses.getCollVO().iterator(); iteStatus.hasNext(); )
		{
			StatusGIAITCDVo status = (StatusGIAITCDVo) iteStatus.next();
			GIAITCDVo giaConsulta = new GIAITCDVo();
			giaConsulta.setCodigo(status.getGiaITCDVo().getCodigo());
			giaConsulta = new GIAITCDVo(giaConsulta);
			giaRetorno.getCollVO().add(giaBe.consultarGIAITCDUsuarioNaoLotado(giaConsulta));
		}
		return giaRetorno;
	}
}
