/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDInventarioArrolamentoBeneficiarioBe.java
 * Revisão:
 * Data revisão:
 * $Id: GIAITCDInventarioArrolamentoBeneficiarioBe.java,v 1.4 2009/03/23 15:44:10 ricardo.moraes Exp $
 */
package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario;

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
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioBe;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioQDao;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota.GIAITCDInventarioArrolamentoBeneficiarioAliquotaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota.GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * @author Leandro Dorileo
 * @version $Revision: 1.4 $
 */
public class GIAITCDInventarioArrolamentoBeneficiarioBe extends AbstractBe
{

	/**
	 * Construtor público sem parametro
	 * @throws SQLException
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioBe() throws SQLException
	{
		super();
	}

	/**
	 *Construtor público recebendo conexao como parametro
	 * @param conn	conexao a ser usada
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioBe(Connection conn)
	{
		super(conn);
	}

	/**
	 * Método responsável por consultar um beneficiário de Inventário e Arrolamento
	 * 
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo		parâmetros da consulta
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioVo consultaGIAITCDInventarioArrolamentoBeneficiario(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);		
	   try
	   {
	      (new BeneficiarioBe(conn)).consultaBeneficiario(giaITCDInventarioArrolamentoBeneficiarioVo);
	   }
	   catch (SQLException e)
	   {
	      e.printStackTrace();
	      throw new ConsultaException(MensagemErro.CONSULTAR_BENEFICIARIO);
	   }  
		(new GIAITCDInventarioArrolamentoBeneficiarioQDao(conn)).findGIAITCDInventarioArrolamentoGIAITCDBeneficiario(giaITCDInventarioArrolamentoBeneficiarioVo);
		if (giaITCDInventarioArrolamentoBeneficiarioVo.isConsultaCompleta())
		{
			GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquotasBeneficiario = 
						new GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo(giaITCDInventarioArrolamentoBeneficiarioVo.getCodigo());
			aliquotasBeneficiario = 
							new GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo(giaITCDInventarioArrolamentoBeneficiarioVo.getCodigo());
			aliquotasBeneficiario.setGiaITCDIventarioArrolamentoBeneficiarioVo(giaITCDInventarioArrolamentoBeneficiarioVo);
			aliquotasBeneficiario = new GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo(aliquotasBeneficiario);
			aliquotasBeneficiario.setConsultaCompleta(true);
			try
			{
				giaITCDInventarioArrolamentoBeneficiarioVo.setGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo(new GIAITCDInventarioArrolamentoBeneficiarioAliquotaBe(conn).listarGIAITCDInventarioArrolamentoBeneficiarioAliquota(aliquotasBeneficiario));
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				throw new ConsultaException(MensagemErro.CONSULTAR_BENEFICIARIO);
			}
		}
		return giaITCDInventarioArrolamentoBeneficiarioVo;
	}

	/**
	 * Método responsável por listar todas os beneficiários de uma determinada GIA do tipo
	 * Inventário e Arrolamento
	 * 
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioVo listarGIAITCDInventarioArrolamentoBeneficiario(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		(new BeneficiarioQDao(conn)).listBeneficiario(giaITCDInventarioArrolamentoBeneficiarioVo);
		GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioConsultaVo = new GIAITCDInventarioArrolamentoBeneficiarioVo();
	   GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioParametroVo = new GIAITCDInventarioArrolamentoBeneficiarioVo();
		Collection listaBeneficiarios = new ArrayList();
		for (Iterator iteBenef = giaITCDInventarioArrolamentoBeneficiarioVo.getCollVO().iterator(); iteBenef.hasNext(); )
		{
			BeneficiarioVo beneficiarioAtual = (BeneficiarioVo) iteBenef.next();
			beneficiarioConsultaVo = new GIAITCDInventarioArrolamentoBeneficiarioVo();
			beneficiarioParametroVo = new GIAITCDInventarioArrolamentoBeneficiarioVo();
			if (Validador.isNumericoValido(beneficiarioAtual.getCodigo()))
			{
				beneficiarioParametroVo.setCodigo(beneficiarioAtual.getCodigo());
				beneficiarioConsultaVo.setParametroConsulta(beneficiarioParametroVo);
				(new GIAITCDInventarioArrolamentoBeneficiarioQDao(conn)).findGIAITCDInventarioArrolamentoGIAITCDBeneficiario(beneficiarioConsultaVo);
				beneficiarioConsultaVo.setGiaITCDVo(beneficiarioAtual.getGiaITCDVo());
				beneficiarioConsultaVo.setValorRecebido(beneficiarioAtual.getValorRecebido());
				beneficiarioConsultaVo.setPessoaBeneficiaria(new CadastroBe(conn).obterContribuinte(new ContribuinteIntegracaoVo(beneficiarioAtual.getPessoaBeneficiaria())));
			   GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquotasBeneficiario = new GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo();
			   aliquotasBeneficiario.setGiaITCDIventarioArrolamentoBeneficiarioVo(beneficiarioConsultaVo);
			   aliquotasBeneficiario = new GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo(aliquotasBeneficiario);
			   aliquotasBeneficiario.setConsultaCompleta(true);
			   try
			   {
			      beneficiarioConsultaVo.setGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo(new GIAITCDInventarioArrolamentoBeneficiarioAliquotaBe(conn).listarGIAITCDInventarioArrolamentoBeneficiarioAliquota(aliquotasBeneficiario));
			   }
			   catch (SQLException e)
			   {
			      e.printStackTrace();
			      throw new ConsultaException(MensagemErro.CONSULTAR_BENEFICIARIO);
			   }
				listaBeneficiarios.add(beneficiarioConsultaVo);
			}
		}
	   giaITCDInventarioArrolamentoBeneficiarioVo.setCollVO(listaBeneficiarios);
		return giaITCDInventarioArrolamentoBeneficiarioVo;
	}

	/**
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(giaITCDInventarioArrolamentoBeneficiarioVo);
	}

	/**
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(giaITCDInventarioArrolamentoBeneficiarioVo);
	}

	/**
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirGIAITCDInventarioArrolamentoBeneficiarioIncluirGIAInventario(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException, ParametroObrigatorioException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		try
		{
			try
			{
				synchronized (GIAITCDInventarioArrolamentoBeneficiarioVo.class)
				{
					for (Iterator iteBeneficiario = giaITCDInventarioArrolamentoBeneficiarioVo.getCollVO().iterator(); iteBeneficiario.hasNext(); )
					{
						GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioAtual = (GIAITCDInventarioArrolamentoBeneficiarioVo) iteBeneficiario.next();
						beneficiarioAtual.setGiaITCDVo(giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDVo());
						beneficiarioAtual.setLogSefazVo(giaITCDInventarioArrolamentoBeneficiarioVo.getLogSefazVo());
						incluirGIAITCDInventarioArrolamentoBeneficiario(beneficiarioAtual);
					}						
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
			catch(ConexaoException e)
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
	}



	/**
	 * Inclui no banco de dados um registro de beneficiário de uma determinada gia
	 * do tipo Inventário e Arrolamento
	 * 
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluirGIAITCDInventarioArrolamentoBeneficiario(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, LogException, AnotacaoException, PersistenciaException, ConexaoException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		try
		{
			try
			{
				synchronized (GIAITCDInventarioArrolamentoBeneficiarioVo.class)
				{
					//codigo do LOG
					(new BeneficiarioBe(conn)).incluirBeneficiario(giaITCDInventarioArrolamentoBeneficiarioVo);
					incluir(giaITCDInventarioArrolamentoBeneficiarioVo);					
					for (Iterator ite = giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().iterator(); ite.hasNext(); )
					{
						GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) ite.next();
						if(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getCodigoAliquota() != 0)
						{
							giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setGiaITCDIventarioArrolamentoBeneficiarioVo(giaITCDInventarioArrolamentoBeneficiarioVo);
							giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setLogSefazVo(giaITCDInventarioArrolamentoBeneficiarioVo.getLogSefazVo());
							(new GIAITCDInventarioArrolamentoBeneficiarioAliquotaBe(conn)).incluirGIAITCDInventarioArrolamentoBeneficiarioAliquota(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
						}
					}
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
			catch(ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch(ConexaoException e)
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
	}

	/**
	 * Altera os dados persistidos de um determinado beneficiário de uma determinada
	 * GIA do tipo Inventário e Arrolamento
	 * 
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarGIAITCDInventarioArrolamentoBeneficiario(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, LogException, PersistenciaException, AnotacaoException, 
			  ConexaoException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		try
		{
			try
			{
				synchronized (GIAITCDInventarioArrolamentoBeneficiarioVo.class)
				{
					(new BeneficiarioBe(conn)).alterarBeneficiario(giaITCDInventarioArrolamentoBeneficiarioVo);
				   alterar(giaITCDInventarioArrolamentoBeneficiarioVo);
					giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().setGiaITCDIventarioArrolamentoBeneficiarioVo(giaITCDInventarioArrolamentoBeneficiarioVo);
				   giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().setLogSefazVo(giaITCDInventarioArrolamentoBeneficiarioVo.getLogSefazVo());
					new GIAITCDInventarioArrolamentoBeneficiarioAliquotaBe(conn).alterarGIAITCDInventarioArrolamentoBeneficiarioAliquota_AlterarGIAITCDInventarioArrolamentoBeneficiario(giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo());
				}
			}
			catch (ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch(ConsultaException e)
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
			catch(ConexaoException e)
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
	}

	/**
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(giaITCDInventarioArrolamentoBeneficiarioVo);
	}

	/**
	 * Método responsável por excluir do banco de dados um beneficiário da gia de inventário e arrolamento.
	 * 
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void excluirGIAITCDInventarioArrolamentoBeneficiario_AlterarGIAITCDInventarioArrolamento(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		try
		{
			try
			{
				synchronized (GIAITCDInventarioArrolamentoBeneficiarioVo.class)
				{
					 for (Iterator ite = giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().iterator(); ite.hasNext(); )
					 {
					    GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) ite.next();
					    giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setLogSefazVo(giaITCDInventarioArrolamentoBeneficiarioVo.getLogSefazVo());
						 new GIAITCDInventarioArrolamentoBeneficiarioAliquotaBe(conn).excluirGIAITCDInventarioArrolamentoBeneficiarioAliquota(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
					 }					
					 excluir(giaITCDInventarioArrolamentoBeneficiarioVo);
					(new BeneficiarioBe(conn)).excluirBeneficiario(giaITCDInventarioArrolamentoBeneficiarioVo);
				}
			}
			catch(ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch(ObjetoObrigatorioException e)
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
			catch(ConexaoException e)
			{
				conn.rollback();
				throw e;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws ParametroObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarGIAITCDInventarioArrolamentoBeneficiarioAlterarGIAInventarioArrolamento(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, ParametroObrigatorioException, LogException, PersistenciaException, 
			  AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		try
		{
			try
			{
				synchronized (GIAITCDInventarioArrolamentoBeneficiarioVo.class)
				{
					GIAITCDInventarioArrolamentoBeneficiarioBe giaBenefBE = new GIAITCDInventarioArrolamentoBeneficiarioBe(conn);
					GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioConsultaVo = new GIAITCDInventarioArrolamentoBeneficiarioVo();
					GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioParametroVo = new GIAITCDInventarioArrolamentoBeneficiarioVo();
					beneficiarioParametroVo.setGiaITCDVo(giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDVo());
					beneficiarioConsultaVo.setConsultaCompleta(true);
					beneficiarioConsultaVo.setParametroConsulta(beneficiarioParametroVo);
					beneficiarioConsultaVo = listarGIAITCDInventarioArrolamentoBeneficiario(beneficiarioConsultaVo);
					Collection beneficarioAtual = giaITCDInventarioArrolamentoBeneficiarioVo.getCollVO();
					Collection beneficiarioOriginalVo = beneficiarioConsultaVo.getCollVO();
					Collection beneficiarioInserirVo = null;
					Collection beneficiarioAlterarVo = null;
					Collection beneficiarioExcluirVo = null;
					//BENEFICIARIO PARA INCLUIR
					beneficiarioInserirVo = LogUtil.obterListaObjetosParaInclusao(beneficiarioOriginalVo, beneficarioAtual);
					for (Iterator iteBenef = beneficiarioInserirVo.iterator(); iteBenef.hasNext(); )
					{
						GIAITCDInventarioArrolamentoBeneficiarioVo giaBenef = (GIAITCDInventarioArrolamentoBeneficiarioVo) iteBenef.next();
						giaBenef.setGiaITCDVo(giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDVo());
						giaBenef.setLogSefazVo(giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDVo().getLogSefazVo());
						giaBenefBE.incluirGIAITCDInventarioArrolamentoBeneficiario(giaBenef);
					}
					//BENEFICIARIO PARA ALTERAR
					beneficiarioAlterarVo = LogUtil.obterListaObjetosParaAlteracao(beneficiarioOriginalVo, beneficarioAtual);
					for (Iterator iteBenef = beneficiarioAlterarVo.iterator(); iteBenef.hasNext(); )
					{
						GIAITCDInventarioArrolamentoBeneficiarioVo giaBenef = (GIAITCDInventarioArrolamentoBeneficiarioVo) iteBenef.next();
						giaBenef.setGiaITCDVo(giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDVo());
						giaBenef.setLogSefazVo(giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDVo().getLogSefazVo());
						giaBenefBE.alterarGIAITCDInventarioArrolamentoBeneficiario(giaBenef);
					}
					//BENEFICIARIO PARA EXCLUIR
					beneficiarioExcluirVo = LogUtil.obterListaObjetosParaExclusao(beneficiarioOriginalVo, beneficarioAtual);
					for (Iterator iteBenef = beneficiarioExcluirVo.iterator(); iteBenef.hasNext(); )
					{
						GIAITCDInventarioArrolamentoBeneficiarioVo giaBenef = (GIAITCDInventarioArrolamentoBeneficiarioVo) iteBenef.next();
						giaBenef.setGiaITCDVo(giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDVo());
						giaBenef.setLogSefazVo(giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDVo().getLogSefazVo());
						giaBenefBE.excluirGIAITCDInventarioArrolamentoBeneficiario_AlterarGIAITCDInventarioArrolamento(giaBenef);
					}
				}
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
			catch (ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
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
			catch(ConexaoException e)
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
	}
	
	
	
	
	
					
					
	
	
	
	
}
