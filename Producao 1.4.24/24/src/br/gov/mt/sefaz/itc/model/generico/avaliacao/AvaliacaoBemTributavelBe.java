/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AvaliacaoBemTributavelBe.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: AvaliacaoBemTributavelBe.java,v 1.1.1.1 2008/05/28 17:55:04 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.avaliacao;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.log.LogUtil;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.servidor.AvaliacaoBemTributavelServidorBe;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.servidor.AvaliacaoBemTributavelServidorVo;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsultaAvaliacao;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.GestaoPessoasBe;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.UnidadeSefazIntegracaoVo;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


/**
 * Classe que encapsula as regras de negócio da entidade Avaliação Bem Tributável
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class AvaliacaoBemTributavelBe extends AbstractBe
{
	/**
	 * Construtor padrão
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * @param conn
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelBe(Connection conn)
	{
		super(conn);
	}

	/**
	 * Método para efetuar a consulta de uma Avaliação Bem Tributável
	 * @param avaliacaoBemTributavelVo
	 * @return AvaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public AvaliacaoBemTributavelVo consultarAvaliacaoBemTributavel(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, ParametroObrigatorioException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		(new AvaliacaoBemTributavelQDao(conn)).findAvaliacaoBemTributavell(avaliacaoBemTributavelVo);
      
		if (avaliacaoBemTributavelVo.isConsultaCompleta() && avaliacaoBemTributavelVo.isExiste())
		{
			AvaliacaoBemTributavelServidorVo avaliacaoServidorVo = new AvaliacaoBemTributavelServidorVo();
			avaliacaoServidorVo.setAvaliacaoBemTributavelVo(avaliacaoBemTributavelVo);
			avaliacaoServidorVo = new AvaliacaoBemTributavelServidorVo(avaliacaoServidorVo);
			avaliacaoServidorVo.setConsultaCompleta(true);
			avaliacaoBemTributavelVo.setAvaliacaoBemTributavelServidorVo((new AvaliacaoBemTributavelServidorBe(conn)).listarAvaliacaoBemTributavelServidor(avaliacaoServidorVo));
		}
           
		return avaliacaoBemTributavelVo;
	}

	/**
	 * Método para listar as Avaliações Bem Tributável
	 * @param avaliacaoBemTributavelVo
	 * @return AvaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public AvaliacaoBemTributavelVo listarAvaliacaoBemTributavel(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		(new AvaliacaoBemTributavelQDao(conn)).listAvaliacaoBemTributavell(avaliacaoBemTributavelVo);
		return avaliacaoBemTributavelVo;
	}

	/**
	 * Método para listar as Avaliações do Bem Tributável por status e período.
	 * @param avaliacaoBemTributavelVo
	 * @return AvaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public AvaliacaoBemTributavelVo listarAvaliacaoBemTributavelPorStatusPeriodo(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException,
			  ConsultaException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		(new AvaliacaoBemTributavelQDao(conn)).listAvaliacaoBemTributavelByStatusPeriodo(avaliacaoBemTributavelVo);
		return avaliacaoBemTributavelVo;
	}

	/**
	 * @param avaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		new GenericoLogAnotacaoDao(conn, true).insert(avaliacaoBemTributavelVo);
	}

	/**
	 * Método para incluir uma Avaliação Bem Tributável
	 * @param avaliacaoBemTributavelVo
	 * @return AvaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public AvaliacaoBemTributavelVo incluirAvaliacaoBemTributavel(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		try
		{
			try
			{
				synchronized (AvaliacaoBemTributavelVo.class)
				{
				   avaliacaoBemTributavelVo.setDataAtualizacao(new Date());
					incluir(avaliacaoBemTributavelVo);
					AvaliacaoBemTributavelServidorBe avaliacaoServidor = new AvaliacaoBemTributavelServidorBe(conn);
					for (Iterator iteServidor = avaliacaoBemTributavelVo.getAvaliacaoBemTributavelServidorVo().getCollVO().iterator(); iteServidor.hasNext(); )
					{
						AvaliacaoBemTributavelServidorVo servidor = (AvaliacaoBemTributavelServidorVo) iteServidor.next();
						servidor.setAvaliacaoBemTributavelVo(avaliacaoBemTributavelVo);
						servidor.setLogSefazVo(avaliacaoBemTributavelVo.getLogSefazVo());
						avaliacaoServidor.incluirAvaliacaoBemTributavelServidor(servidor);
					}
				}
			}
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
		   catch (LogException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch (PersistenciaException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch (AnotacaoException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
		return avaliacaoBemTributavelVo;
	}

	/**
	 * @param avaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		new GenericoLogAnotacaoDao(conn, true).update(avaliacaoBemTributavelVo);
	}

	/**
	 * Método para Alterar uma Avaliação Bem Tributável
	 * @param avaliacaoBemTributavelVo
	 * @return AvaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public AvaliacaoBemTributavelVo alterarAvaliacaoBemTributavel(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, ParametroObrigatorioException, LogException, PersistenciaException, 
			  AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		try
		{
			try
			{
				synchronized (AvaliacaoBemTributavelVo.class)
				{
					alterar(avaliacaoBemTributavelVo);
					AvaliacaoBemTributavelServidorBe avaliacaoServidor = new AvaliacaoBemTributavelServidorBe(conn);					
					AvaliacaoBemTributavelServidorVo avaliadorConsulta = new AvaliacaoBemTributavelServidorVo();
				   AvaliacaoBemTributavelServidorVo avaliadorParametro = new AvaliacaoBemTributavelServidorVo();
					avaliadorParametro.setAvaliacaoBemTributavelVo(avaliacaoBemTributavelVo);
					avaliadorConsulta.setParametroConsulta(avaliadorParametro);
					avaliadorConsulta.setConsultaCompleta(true);
					avaliadorConsulta = avaliacaoServidor.listarAvaliacaoBemTributavelServidor(avaliadorConsulta);
					Collection avaliadorIncluir = new ArrayList();
					Collection avaliadorExcluir = new ArrayList();
					avaliadorIncluir = LogUtil.obterListaObjetosParaInclusao(avaliadorConsulta.getCollVO(), avaliacaoBemTributavelVo.getAvaliacaoBemTributavelServidorVo().getCollVO());
					for(Iterator it = avaliadorIncluir.iterator(); it.hasNext(); )
					{
					   AvaliacaoBemTributavelServidorVo avaliador = (AvaliacaoBemTributavelServidorVo) it.next();
						avaliador.setAvaliacaoBemTributavelVo(avaliacaoBemTributavelVo);
						avaliador.setLogSefazVo(avaliacaoBemTributavelVo.getLogSefazVo());
					   avaliacaoServidor.incluirAvaliacaoBemTributavelServidor(avaliador);
					}
				   avaliadorExcluir = LogUtil.obterListaObjetosParaExclusao(avaliadorConsulta.getCollVO(), avaliacaoBemTributavelVo.getAvaliacaoBemTributavelServidorVo().getCollVO());
					for(Iterator it = avaliadorExcluir.iterator(); it.hasNext(); )
					{
					   AvaliacaoBemTributavelServidorVo avaliador = (AvaliacaoBemTributavelServidorVo) it.next();
					   avaliador.setAvaliacaoBemTributavelVo(avaliacaoBemTributavelVo);
					   avaliador.setLogSefazVo(avaliacaoBemTributavelVo.getLogSefazVo());
					   avaliacaoServidor.apagarAvaliacaoBemTributavelServidorPorAvaliacao(avaliador);
						
					}
					avaliacaoBemTributavelVo.setMensagem(MensagemSucesso.ALTERAR);
				}
			}
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch (ConsultaException e)
			{
				conn.rollback();
				throw e;
			}
			catch (IntegracaoException e)
			{
				conn.rollback();
				throw e;
			}
			catch(ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
		   catch (LogException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch (PersistenciaException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch (AnotacaoException e)
		   {
		      conn.rollback();
		      throw e;
		   }			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
		return avaliacaoBemTributavelVo;
	}

	/**
	 * @param avaliacaoBemTributavelVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private AvaliacaoBemTributavelVo solicitarObterObjetoOriginalAlterar(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, ParametroObrigatorioException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
	   AvaliacaoBemTributavelVo parametro = new AvaliacaoBemTributavelVo();
	   parametro.setCodigo(avaliacaoBemTributavelVo.getCodigo());              
	   AvaliacaoBemTributavelVo consulta = new AvaliacaoBemTributavelVo();
	   consulta.setParametroConsulta(parametro);
	   consulta.setConsultaCompleta(false);
	   return consultarAvaliacaoBemTributavel(consulta);
		
	}

	/**
	 * Método para Alterar uma Avaliação Bem Tributável
	 * @param avaliacaoBemTributavelVo
	 * @return AvaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws IntegracaoException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public AvaliacaoBemTributavelVo alterarDataAtualizacao(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException, IntegracaoException, 
			  ConsultaException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		try
		{
			try
			{
				synchronized (AvaliacaoBemTributavelVo.class)
				{
				   AvaliacaoBemTributavelVo original = solicitarObterObjetoOriginalAlterar(avaliacaoBemTributavelVo);
					original.setDataAtualizacao(new Date());
					original.setLogSefazVo(avaliacaoBemTributavelVo.getLogSefazVo());
					alterar(original);
					conn.commit();
					avaliacaoBemTributavelVo.setMensagem(MensagemSucesso.ALTERAR);
				}
			}
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
		   catch(LogException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch(PersistenciaException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch(AnotacaoException e)
		   {
		      conn.rollback();
		      throw e;
		   }	
			catch(IntegracaoException e)
			{
				conn.rollback();
				throw e;
			}
			catch(ConsultaException e)
			{
				conn.rollback();
				throw e;
			}
			catch(ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
		return avaliacaoBemTributavelVo;
	}

	/**
	 * Método responsável por listar as unidades SEFAZ que estarão disponíveis para seleção conforme tipo de avaliação da GIA.
	 * @param giaITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void listarUnidadeSefazAtivaParaAvaliacao(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
			  SQLException, ParametroObrigatorioException, IntegracaoException
	{
		Validador.validaObjeto(giaITCDVo);
	   UnidadeSefazIntegracaoVo unidadeSefazConsulta = new UnidadeSefazIntegracaoVo();
	   UnidadeSefazIntegracaoVo unidadeSefazParametro = new UnidadeSefazIntegracaoVo();
	   unidadeSefazConsulta.setParametroConsulta(unidadeSefazParametro);
		UnidadeSefazIntegracaoVo retorno = new UnidadeSefazIntegracaoVo();
		Collection<UnidadeSefazIntegracaoVo> listaUnidade = new ArrayList<UnidadeSefazIntegracaoVo>();
 
		unidadeSefazParametro.getTipoUnidade().setCodgTipoUnidade(new Integer(ConfiguracaoITCD.CODIGO_TIPO_UNIDADE_SEFAZ_AGENFA));        
		retorno = new GestaoPessoasBe(conn).listUnidSefazAtivaPorCodgTipoUnid(unidadeSefazConsulta);
		if(Validador.isCollectionValida(retorno.getCollVO()))
		{
			listaUnidade.addAll(retorno.getCollVO());
		}
		
		unidadeSefazParametro.getTipoUnidade().setCodgTipoUnidade(new Integer(ConfiguracaoITCD.CODIGO_TIPO_UNIDADE_SEFAZ_GERENCIA));
		retorno = new GestaoPessoasBe(conn).listUnidSefazAtivaPorCodgTipoUnid(unidadeSefazConsulta);			
	   if(Validador.isCollectionValida(retorno.getCollVO()))
	   {
	      listaUnidade.addAll(retorno.getCollVO());
	   }


	   giaITCDVo.getAgenciaProtocolo().getCollVO().addAll(listaUnidade);
	}

	/**
	 * Método responsável por consultar uma GIA-ITCD avaliada pelo número da GIA.
	 * @param avaliacaoBemTributavelVo
	 * @return AvaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public AvaliacaoBemTributavelVo consultarGIAITCDAvaliadaPorNumero(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException, IntegracaoException, 
             SQLException, IOException
   {
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		if(!Validador.isNumericoValido(avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo().getCodigo()))
		{
		   throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_CODIGO_GIA);
		}
	   GIAITCDVo giaITCDConsultaVo = new GIAITCDVo();
		giaITCDConsultaVo.setCodigo(avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo().getCodigo());
	   giaITCDConsultaVo.setLogSefazVo(avaliacaoBemTributavelVo.getLogSefazVo());
	   giaITCDConsultaVo = new GIAITCDBe(conn).solicitarConsultarGIAITCD(giaITCDConsultaVo);
		if(Validador.isNumericoValido(giaITCDConsultaVo.getCodigo()))
		{
			validaStatusConsultaAvaliacao(giaITCDConsultaVo);			
			avaliacaoBemTributavelVo.getBemTributavel().setGiaITCDVo(giaITCDConsultaVo);
			obterStatusAvaliado(giaITCDConsultaVo);
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_NAO_ENCONTRADA);
		}
		return avaliacaoBemTributavelVo;	
	}
	
	private void obterStatusAvaliado(GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(giaITCDVo);
		if(Validador.isCollectionValida(giaITCDVo.getStatusVo().getCollVO()))
		{
			for(Iterator it = giaITCDVo.getStatusVo().getCollVO().iterator(); it.hasNext(); )
			{
				StatusGIAITCDVo atual = (StatusGIAITCDVo) it.next();
				if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.AVALIADO))
				{
					atual.setCollVO(giaITCDVo.getStatusVo().getCollVO());
					giaITCDVo.setStatusVo(atual);
					break;
				}
			}
		}
	}

	/**
	 * Método responsável por listar GIA-ITCD avaliada.
	 * @param avaliacaoBemTributavelVo
	 * @return AvaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public AvaliacaoBemTributavelVo listarGIAITCDAvaliada(AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException
	{
	   Validador.validaObjeto(avaliacaoBemTributavelVo);
		if(!Validador.isDominioNumericoValido(avaliacaoBemTributavelVo.getTipoConsultaAvaliacao()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_TIPO_CONSULTA_AVALIACAO);
		}	
	   AvaliacaoBemTributavelVo avaliacaoConsultaVo = new AvaliacaoBemTributavelVo();
	   AvaliacaoBemTributavelVo avaliacaoParametroVo = new AvaliacaoBemTributavelVo();
		validaParametroConsultaAvaliacao(avaliacaoBemTributavelVo);
		if(avaliacaoBemTributavelVo.getTipoConsultaAvaliacao().is(DomnTipoConsultaAvaliacao.AVALIACAO_POR_CONTRIBUINTE))
		{
		   avaliacaoParametroVo.getBemTributavel().getGiaITCDVo().setResponsavelVo(avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo().getResponsavelVo());		   
		}
		else
		{
			avaliacaoParametroVo.setDataInicial(avaliacaoBemTributavelVo.getDataInicial());	
			Calendar c = Calendar.getInstance();
		   c.setTime(avaliacaoBemTributavelVo.getDataFinal());
			c.add(Calendar.DATE, 1);
			c.add(Calendar.SECOND, -1);
			avaliacaoParametroVo.setDataFinal(c.getTime());
			if(avaliacaoBemTributavelVo.getTipoConsultaAvaliacao().is(DomnTipoConsultaAvaliacao.AVALIACAO_POR_AGENFA))
			{
				avaliacaoParametroVo.getBemTributavel().getGiaITCDVo().getStatusVo().setCodigoUnidadeAvaliacao(avaliacaoBemTributavelVo.getListaAgenfa().getCodgUnidade().intValue());
			}
			else if (avaliacaoBemTributavelVo.getTipoConsultaAvaliacao().is(DomnTipoConsultaAvaliacao.AVALIACAO_POR_GERENCIA_DE_EXECUCAO))
			{
			   avaliacaoParametroVo.getBemTributavel().getGiaITCDVo().getStatusVo().setCodigoUnidadeAvaliacao(avaliacaoBemTributavelVo.getListaGerencia().getCodgUnidade().intValue());
			}
			else if (avaliacaoBemTributavelVo.getTipoConsultaAvaliacao().is(DomnTipoConsultaAvaliacao.AVALIACAO_POR_UNIDADE_FAZENDARIA))
			{
			   avaliacaoParametroVo.getBemTributavel().getGiaITCDVo().getStatusVo().setCodigoUnidadeAvaliacao(avaliacaoBemTributavelVo.getListaUnidadesFazendarias().getCodgUnidade().intValue());
			}
		}	   
	   //avaliacaoParametroVo.getBemTributavel().getGiaITCDVo().getStatusVo().setListaStatus(getListaStatusGIAITCDAvaliada());
	   avaliacaoConsultaVo.setParametroConsulta(avaliacaoParametroVo);
	   avaliacaoConsultaVo = listarAvaliacaoBemTributavelPorStatusPeriodo(avaliacaoConsultaVo);
		if(Validador.isCollectionValida(avaliacaoConsultaVo.getCollVO()))
		{
		   avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo().getCollVO().clear();
			for(Iterator it = avaliacaoConsultaVo.getCollVO().iterator(); it.hasNext(); )
			{			   
			   AvaliacaoBemTributavelVo avaliacaoBem = (AvaliacaoBemTributavelVo) it.next();
			   avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo().getCollVO().add(avaliacaoBem.getBemTributavel().getGiaITCDVo());
            
            StatusGIAITCDVo statusGia = new StatusGIAITCDVo();
            statusGia.getGiaITCDVo().setCodigo( avaliacaoBem.getBemTributavel().getGiaITCDVo().getCodigo() );
            statusGia = new StatusGIAITCDVo(statusGia);
            try
            {
               statusGia = (new StatusGIAITCDBe(conn)).listarStatusGIAITCD(statusGia);
               avaliacaoBem.getBemTributavel().getGiaITCDVo().setStatusUltimaAvaliacao( verificarAvaliacaoAtivaOuInativa( statusGia )  );
            } catch (IntegracaoException e)
            {
               e.printStackTrace();
            }         
			}
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LISTA_GIA_NAO_ENCONTRADA);
		}
		avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo().ordenaGiasPorCodigoDecrescente();
		return avaliacaoBemTributavelVo;
	}

	/**
	 * @return
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private int[] getListaStatusGIAITCDAvaliada()
	{
		return new int[]
		{
			DomnStatusGIAITCD.AVALIADO
			, DomnStatusGIAITCD.NOTIFICADO
			, DomnStatusGIAITCD.NOTIFICADO_CIENTE
			, DomnStatusGIAITCD.RETIFICADO
			, DomnStatusGIAITCD.RETIFICADO_CIENTE
			, DomnStatusGIAITCD.IMPUGNADO
			, DomnStatusGIAITCD.RATIFICADO
			, DomnStatusGIAITCD.RATIFICADO_CIENTE
			, DomnStatusGIAITCD.SEGUNDA_RETIFICACAO
			, DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE
			, DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR
			, DomnStatusGIAITCD.ISENTO
			, DomnStatusGIAITCD.PARCELADO
			, DomnStatusGIAITCD.QUITADO
			, DomnStatusGIAITCD.QUITADO_MANUALMENTE
			, DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA
			, DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA
		};
	}

	/**
	 * Método responsável por verificar na consulta de GIA-ITCD avaliada, se a GIA-ITCD consultada já foi avaliada.
	 * @param giaITCDVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validaStatusConsultaAvaliacao(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(giaITCDVo);
		
		if(!giaITCDVo.isGiaAvaliada())
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_NAO_AVALIADA);
		}
	}

	/**
	 * Método responsável por validar os parâmetros informados na consulta.
	 * @param avaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validaParametroConsultaAvaliacao(AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
	   if(avaliacaoBemTributavelVo.getTipoConsultaAvaliacao().is(DomnTipoConsultaAvaliacao.AVALIACAO_POR_CONTRIBUINTE))
	   {
	      if(!Validador.isNumericoValido(avaliacaoBemTributavelVo.getBemTributavel().getGiaITCDVo().getResponsavelVo().getNumrContribuinte()))
	      {
	         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CONTRIBUINTE_PESQUISA_GIA_ITCD);
	      }
	   }
	   else
	   {
	      if(avaliacaoBemTributavelVo.getDataInicial().after(new Date()))
	      {
	         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_DATA_INICIAL_MAIOR_ATUAL);
	      }        
	      if(avaliacaoBemTributavelVo.getDataFinal().after(new Date()))
	      {
	         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_DATA_FINAL_MAIOR_ATUAL);
	      }
	      if (!avaliacaoBemTributavelVo.isPeriodoValido())
	      {
	         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_DATA_FINAL_MAIOR_INICIAL);
	      }
			if(!(Validador.isNumericoValido(avaliacaoBemTributavelVo.getListaAgenfa().getCodgUnidade()) 
					|| Validador.isNumericoValido(avaliacaoBemTributavelVo.getListaGerencia().getCodgUnidade())
					|| Validador.isNumericoValido(avaliacaoBemTributavelVo.getListaUnidadesFazendarias().getCodgUnidade())))
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_SELECAO_UNIDADE_FAZENDARIA);
			}
	   }
		
	}

	/**
	 * @param avaliacao
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void solicitarObterListaUnidadesFazendariasConsulta(AvaliacaoBemTributavelVo avaliacao) throws ObjetoObrigatorioException, 
			  IntegracaoException
	{
		Validador.validaObjeto(avaliacao);
		solicitarObterListaUnidade(avaliacao.getListaAgenfa(), ConfiguracaoITCD.CODIGO_TIPO_UNIDADE_SEFAZ_AGENFA);
		solicitarObterListaUnidade(avaliacao.getListaGerencia(), ConfiguracaoITCD.CODIGO_TIPO_UNIDADE_SEFAZ_GERENCIA);
		solicitarObterListaUnidade(avaliacao.getListaUnidadesFazendarias(), 0);
		Collection listaReduzida = LogUtil.obterListaObjetosParaInclusao(avaliacao.getListaAgenfa().getCollVO(), avaliacao.getListaUnidadesFazendarias().getCollVO());
		listaReduzida = LogUtil.obterListaObjetosParaInclusao(avaliacao.getListaGerencia().getCollVO(), listaReduzida);
	   avaliacao.getListaUnidadesFazendarias().setCollVO(new ArrayList(listaReduzida));
		avaliacao.getListaUnidadesFazendarias().ordenarUnidadeSefazPorNome();
		avaliacao.getListaAgenfa().ordenarUnidadeSefazPorNome();
		avaliacao.getListaGerencia().ordenarUnidadeSefazPorNome();
	}

	/**
	 * @param listaUnidade
	 * @param tipoUnidadeSefaz
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarObterListaUnidade(UnidadeSefazIntegracaoVo listaUnidade, int tipoUnidadeSefaz) throws ObjetoObrigatorioException, 
			  IntegracaoException
	{
		GestaoPessoasBe gestaoPessoas = new GestaoPessoasBe(conn);
		UnidadeSefazIntegracaoVo consulta = new UnidadeSefazIntegracaoVo();
		UnidadeSefazIntegracaoVo parametro = new UnidadeSefazIntegracaoVo();
		parametro.getTipoUnidade().setCodgTipoUnidade(tipoUnidadeSefaz);
		consulta.setParametroConsulta(parametro);
		switch(tipoUnidadeSefaz)
		{
			case ConfiguracaoITCD.CODIGO_TIPO_UNIDADE_SEFAZ_AGENFA:
			{
				listaUnidade.setCollVO(gestaoPessoas.listUnidSefazAtivaPorCodgTipoUnid(consulta).getCollVO());
				break;
			}
			case ConfiguracaoITCD.CODIGO_TIPO_UNIDADE_SEFAZ_GERENCIA:
			{
			   listaUnidade.setCollVO(gestaoPessoas.listUnidSefazAtivaPorCodgTipoUnid(consulta).getCollVO());
				break;
			}
			default:
			{
			   listaUnidade.setCollVO(gestaoPessoas.listarUnidadesSefaz().getCollVO());
				break;
			}
			
		}
	}
   
   /**
    * <b>Objetivo:</b>
    * Este método tem por objetivo alterar o status da avaliação
    * de não impresso para impresso.
    * <br>
    * <b>Rotina:</b>
    * <ol>
    * <li>Capturar a avalição de cada bem tributavel contido na coleção do bem passado como parametro.</li>
    * <li>Alterar o dominio da avaliação de não impresso para impresso.</li>
    * <li>Invocar o método alterar para enviar as alterações para o BD.</li>
    * <li>Aguarda o commit da operação.</li>
    * </ol>
    * 
    * <b>Validações:</b>
    * <ol>
    * <li>Valida se o parametro informado é != null.</li>
    * <li>Valida se a coleção contida no BemTributavelVo contém pelo menos 1 (um) elemento.</li>
    * <li>Valida se AvaliacaoBemTributavelVo de cada bem é válida.</li>
    * <li>Outras validações executas pelo SCL.</li>
    * </ol>
    * @param giaITCDVo
    * 
    */
   public void alterarStausAvaliacaoImpressa(final GIAITCDVo giaITCDVo , DomnSimNao impresso ) throws ObjetoObrigatorioException, 
                                                                                     LogException, 
                                                                                     AnotacaoException, 
                                                                                     PersistenciaException
   {
     
      if (Validador.isObjetoValido(giaITCDVo))
      { 
         BemTributavelVo bemTributavelVO = giaITCDVo.getBemTributavelVo();
         if (Validador.isCollectionValida(bemTributavelVO.getCollVO()))
         {
            for (Iterator it = bemTributavelVO.getCollVO().iterator() ; it.hasNext();)
            {
               BemTributavelVo bemVO = (BemTributavelVo) it.next();
               // if(bemVO.getBemParticular().is(DomnSimNao.NAO))
               if(GIAITCDBe.isBemPassivelAvaliacao( giaITCDVo , bemVO ))//TODO Verifica se o bem pode ser avaliado ou não.
               { 
                  if (Validador.isObjetoValido(bemVO.getAvaliacaoBemTributavelVo()))
                  {
                     bemVO.getAvaliacaoBemTributavelVo().setAvaliacaoImpressa( impresso );
                     // Mantém um historico do valor da avalição.
                     bemVO.getAvaliacaoBemTributavelVo().setValorReabertura( bemVO.getAvaliacaoBemTributavelVo().getValorAvaliacao() );
                     alterar(bemVO.getAvaliacaoBemTributavelVo());                 
                  }
               }
            }
         } 
      }
   }
   
   
   
    /**
     * <b>Objetivo:</b>
     * Este método tem por objetivo verificar as avaliações de cada bem tributavel verificando
     * se alguma das avaliações contém o status passado como parâmetro.
     * 
     * 
     * <br>
     * <b>Rotina:</b>
     * <ol>
     * <li>Capturar a avalição de cada bem tributavel contido na coleção do bem passado como parametro.</li>
     * <li>Comparar se status de impressão da avaliação é igual ao informado no parâmetro.</li>
     * </ol>
     * 
     * <b>Validações:</b>
     * <ol>
     * <li>Valida se o parametro informado é != null.</li>
     * <li>Valida se a coleção contida no BemTributavelVo contém pelo menos 1 (um) elemento.</li>
     * <li>Valida se AvaliacaoBemTributavelVo de cada bem é válida.</li>
     * </ol>
     * @param bemTributavelVO
     * 
     * @return true se e somente for encontrado pelo menos um status igual ao informado no parâmetro.
     * 
     */
    public boolean validarStatusImpressao(BemTributavelVo bemTributavelVO , DomnSimNao status ) throws ObjetoObrigatorioException, 
                                                                                      LogException, 
                                                                                      AnotacaoException, 
                                                                                      PersistenciaException
    {
       if (Validador.isObjetoValido(bemTributavelVO))
       {
          if (Validador.isCollectionValida(bemTributavelVO.getCollVO()))
          {
             for (Iterator it = bemTributavelVO.getCollVO().iterator() ; it.hasNext();)
             {
                BemTributavelVo bemVO = (BemTributavelVo) it.next();
                if(bemVO.getBemParticular().is(DomnSimNao.NAO))
                {
                    if (Validador.isObjetoValido(bemVO.getAvaliacaoBemTributavelVo()))
                   {                   
                      if( bemVO.getAvaliacaoBemTributavelVo().getAvaliacaoImpressa().is(status.getDomnValr()))
                      {
                         return true;
                      }                          
                   }
                }
               
             }
          }       
       }
       return false;
    }
   
   
   
     /**
      * <b>Objetivo:</b>
      * Este método tem por objetivo verificar o status da avaliacao de cada bem tributavel verificando
      * se alguma das avaliações contém o status {ATIVO|INATIVO}  passado como parâmetro.
      * 
      * 
      * <br>
      * <b>Rotina:</b>
      * <ol>
      * <li>Capturar a avalição de cada bem tributavel contido na coleção do bem passado como parametro.</li>
      * <li>Comparar se status da avaliação é igual ao informado no parâmetro.</li>
      * </ol>
      * 
      * <b>Validações:</b>
      * <ol>
      * <li>Valida se o parametro informado é != null.</li>
      * <li>Valida se a coleção contida no BemTributavelVo contém pelo menos 1 (um) elemento.</li>
      * <li>Valida se AvaliacaoBemTributavelVo de cada bem é válida.</li>
      * </ol>
      * @param bemTributavelVO
      * 
      * @return true se e somente se for encontrado pelo menos um status igual ao informado no parâmetro.
      * 
      */
     public boolean validarAvalicaoStatusAvaliacao(BemTributavelVo bemTributavelVO , DomnAtivoInativo status ) throws ObjetoObrigatorioException, 
                                                                                       LogException, 
                                                                                       AnotacaoException, 
                                                                                       PersistenciaException
     {
        if (Validador.isObjetoValido(bemTributavelVO))
        {
           if (Validador.isCollectionValida(bemTributavelVO.getCollVO()))
           {
              for (Iterator it = bemTributavelVO.getCollVO().iterator() ; it.hasNext();)
              {
                 BemTributavelVo bemVO = (BemTributavelVo) it.next();
                 if(bemVO.getBemParticular().is(DomnSimNao.NAO))
                 {
                    if (Validador.isObjetoValido(bemVO.getAvaliacaoBemTributavelVo()))
                    {                   
                       if( bemVO.getAvaliacaoBemTributavelVo().getStatusAvaliacao().is(status.getValorCorrente())  )
                       {
                          return true;
                       }                          
                    }
                 }
              }
           }       
        }
        return false;
     }
   
   
      /**
       * <b>Objetivo:</b>
       * Este método tem por objetivo verificar se os bens possuem avaliação.
       * 
       * 
       * <br>
       * <b>Rotina:</b>
       * <ol>
       * <li>Testa a existência de avalição em cada bem tributavel.</li>
       * </ol>
       * 
       * <b>Validações:</b>
       * <ol>
       * <li>Valida se o parametro informado é != null.</li>
       * <li>Valida se a coleção contida no BemTributavelVo contém pelo menos 1 (um) elemento.</li>
       * <li>Valida se cada bem possuem avaliação.</li>
       * </ol>
       * @param bemTributavelVO
       * 
       * @return true se e somente cada bem possuir sua respectiva avaliação.
       * 
       */
      public boolean validarBensPossuemAvaliacao(BemTributavelVo bemTributavelVO)
      {
         if (Validador.isObjetoValido(bemTributavelVO))
         {
            if (Validador.isCollectionValida(bemTributavelVO.getCollVO()))
            {
               for (Iterator it = bemTributavelVO.getCollVO().iterator() ; it.hasNext();)
               {
                  BemTributavelVo bemVO = (BemTributavelVo) it.next();
                  if(bemVO.getBemParticular().is(DomnSimNao.NAO))
                  {
                     if (!Validador.isNumericoValido(bemVO.getAvaliacaoBemTributavelVo().getCodigo()))
                     {
                        return false;
                     }
                  }              
               }
            }else
            {
               return false;
            }
         }else
         {
            return false;
         }
         return true;
      }

   
   
   
       /**
        * <b>Objetivo:</b>
        * Este método tem por objetivo copiar o valor da avalicação para
        * o atributo valor rebertura, afim de manter o histórico de valores alterados
        * na avaliação.
        * <br>
        * <b>Rotina:</b>
        * <ol>
        * <li>Capturar a avalição de cada bem tributavel contido na coleção do bem passado como parametro.</li>
        * <li>Copia o valor da avalicação e repassa para valor rebertura.</li>
        * <li>Aguarda o commit da operação.</li>
        * </ol>
        * 
        * <b>Validações:</b>
        * <ol>
        * <li>Valida se o parametro informado é != null.</li>
        * <li>Valida se a coleção contida no BemTributavelVo contém pelo menos 1 (um) elemento.</li>
        * <li>Valida se AvaliacaoBemTributavelVo de cada bem é válida.</li>
        * <li>Valida se o bem é partiucular ou não (bem particular não tem avaliação).</li>
        * <li>Outras validações executas pelo SCL.</li>
        * </ol>
        * @param bemTributavelVO
        * 
        */
       public void copiarValorAvaliacaoParaValorReabertura(BemTributavelVo bemTributavelVO) throws ObjetoObrigatorioException, 
                                                                                         LogException, 
                                                                                         AnotacaoException, 
                                                                                         PersistenciaException
       {
          if (Validador.isObjetoValido(bemTributavelVO))
          {
             if (Validador.isCollectionValida(bemTributavelVO.getCollVO()))
             {
                for (Iterator it = bemTributavelVO.getCollVO().iterator() ; it.hasNext();)
                {
                   BemTributavelVo bemVO = (BemTributavelVo) it.next();
                   if(bemVO.getBemParticular().is(DomnSimNao.NAO))
                   {
                      if (Validador.isObjetoValido(bemVO.getAvaliacaoBemTributavelVo()))
                      {
                         // Mante um historico do valor da avalição.
                         bemVO.getAvaliacaoBemTributavelVo().setValorReabertura( bemVO.getAvaliacaoBemTributavelVo().getValorAvaliacao() );              
                      }
                   }
                 
                }
             } 
          }
       }
       
       
   /**
    * <b>Objetivo</b>
    * Este método tem por objetivo verificar se na collection dos status contém os staus AVALIADO e AVALIACAO_EXCLUIDA
    * sendo o código do status AVALIADO menor que o código do status AVALIACAO_EXCLUIDA.
    * 
    * <b>Validações</b>
    * <ol>
    * <li>statusGIAITCDVo != null </li>
    * <li>statusGIAITCDVo.getCollVO() contém pelo menos 1 elemento</li>
    * </ol>
    * 
    * 
    * @param statusGIAITCDVo
    * @return DomnAtivoInativo Retorva (ATIVO|INATIVO).
    */
   public DomnAtivoInativo verificarAvaliacaoAtivaOuInativa(StatusGIAITCDVo statusGIAITCDVo)
   {
   
      StatusGIAITCDBe statusBe  = new StatusGIAITCDBe(conn);
      
      StatusGIAITCDVo statusAvaliado = statusBe.procurarUltimoStatusNaCollectionDoVo( statusGIAITCDVo , new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIADO));
      StatusGIAITCDVo statusAvaliacaoExcluida = statusBe.procurarUltimoStatusNaCollectionDoVo( statusGIAITCDVo , new DomnStatusGIAITCD(DomnStatusGIAITCD.AVALIACAO_EXCLUIDA));
      
      if(Validador.isObjetoValido(statusAvaliado) & Validador.isObjetoValido(statusAvaliacaoExcluida)  )
      {
         if(statusAvaliado.getCodigo() < statusAvaliacaoExcluida.getCodigo())
         {
            return new DomnAtivoInativo(DomnAtivoInativo.INATIVO);
         }
      }
      return new DomnAtivoInativo(DomnAtivoInativo.ATIVO);
   }
       
}
