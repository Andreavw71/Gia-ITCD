package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario;

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
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.log.LogUtil;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioBe;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioQDao;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.aliquota.GIAITCDDoacaoBeneficiarioAliquotaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.aliquota.GIAITCDDoacaoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaVo;
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
 * Classe de negócio da Entidade GIA-ITCD Doação Beneficiário
 * @author Daniel Balieiro
 * @implemented by Daniel Balieiro
 */
public class GIAITCDDoacaoBeneficiarioBe extends AbstractBe
{

	/**
	 * Construtor Padrão
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBeneficiarioBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a Conexão com o Banco de Dados
	 * @param conn
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBeneficiarioBe(Connection conn)
	{
		super(conn);
	}

	/**
	 * Método para consultar uma GIA-ITCD Doação Beneficiário
	 * @param giaITCDDoacaoBeneficiario
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBeneficiarioVo consultaGIAITCDDoacaoBeneficiario(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiario) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiario);	
	   try
	   {
	      (new BeneficiarioBe(conn)).consultaBeneficiario(giaITCDDoacaoBeneficiario);
	   }
	   catch (SQLException e)
	   {
	      e.printStackTrace();
	      throw new ConsultaException(MensagemErro.CONSULTAR_BENEFICIARIO);
	   }  

		(new GIAITCDDoacaoBeneficiarioQDao(conn)).findGIAITCDDoacaoGIAITCDDoacaoBeneficiario(giaITCDDoacaoBeneficiario);
		return giaITCDDoacaoBeneficiario;
	}

	/**
	 * Método para listar GIA-ITCDs
	 * @param giaITCDDoacaoBeneficiario
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBeneficiarioVo listarGIAITCDDoacaoBeneficiario(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiario) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiario);
		(new BeneficiarioQDao(conn)).listBeneficiario(giaITCDDoacaoBeneficiario);
	   GIAITCDDoacaoBeneficiarioVo giaITCDBeneficiarioConsultaVo = new GIAITCDDoacaoBeneficiarioVo();
		GIAITCDDoacaoBeneficiarioVo giaITCDBeneficiarioParametroVo = new GIAITCDDoacaoBeneficiarioVo();
		Collection listaBeneficiarios = new ArrayList();
		for (Iterator iteBenef = giaITCDDoacaoBeneficiario.getCollVO().iterator(); iteBenef.hasNext(); )
		{
			BeneficiarioVo beneficiarioAtual = (BeneficiarioVo) iteBenef.next();
		   giaITCDBeneficiarioConsultaVo = new GIAITCDDoacaoBeneficiarioVo();
		   giaITCDBeneficiarioParametroVo = new GIAITCDDoacaoBeneficiarioVo();
			if (Validador.isNumericoValido(beneficiarioAtual.getCodigo()))
			{			
			   giaITCDBeneficiarioParametroVo.setCodigo(beneficiarioAtual.getCodigo());
			   giaITCDBeneficiarioConsultaVo.setParametroConsulta(giaITCDBeneficiarioParametroVo);
				(new GIAITCDDoacaoBeneficiarioQDao(conn)).findGIAITCDDoacaoGIAITCDDoacaoBeneficiario(giaITCDBeneficiarioConsultaVo);
			   giaITCDBeneficiarioConsultaVo.setGiaITCDVo(beneficiarioAtual.getGiaITCDVo());
			   giaITCDBeneficiarioConsultaVo.setValorRecebido(beneficiarioAtual.getValorRecebido());
			   giaITCDBeneficiarioConsultaVo.setPessoaBeneficiaria(new CadastroBe(conn).obterContribuinte(new ContribuinteIntegracaoVo(beneficiarioAtual.getPessoaBeneficiaria())));
				listaBeneficiarios.add(giaITCDBeneficiarioConsultaVo);
			}
		}
		if(Validador.isCollectionValida(listaBeneficiarios))
		{
		   giaITCDDoacaoBeneficiario.setCollVO(listaBeneficiarios);
		}
		return giaITCDDoacaoBeneficiario;
	}

	/**
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(giaITCDDoacaoBeneficiarioVo);
	}

	/**
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(giaITCDDoacaoBeneficiarioVo);
	}

	/**
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(giaITCDDoacaoBeneficiarioVo);
	}	

	/**
	 * Método para incluir uma GIA-ITCD Doação Beneficiário
	 * @param giaITCDDoacaoBeneficiario
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirGIAITCDDoacaoBeneficiario(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiario) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, LogException, PersistenciaException, AnotacaoException, ConexaoException, IntegracaoException, RegistroExistenteException, ConsultaException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiario);
		try
		{
			try
			{
				synchronized (GIAITCDDoacaoBeneficiarioVo.class)
				{
					(new BeneficiarioBe(conn)).incluirBeneficiario(giaITCDDoacaoBeneficiario);
					incluir(giaITCDDoacaoBeneficiario);
				   for (Iterator ite = giaITCDDoacaoBeneficiario.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().iterator(); ite.hasNext(); )
				   {
				      GIAITCDDoacaoBeneficiarioAliquotaVo  giaItcdDoacaoBeneficiarioAliquotaVo = (GIAITCDDoacaoBeneficiarioAliquotaVo) ite.next();
				      if(giaItcdDoacaoBeneficiarioAliquotaVo.getCodigoAliquota() != 0)
				      {
				         giaItcdDoacaoBeneficiarioAliquotaVo.setGiaItcdDoacaoBeneficiarioVo(giaITCDDoacaoBeneficiario);
				         giaItcdDoacaoBeneficiarioAliquotaVo.setLogSefazVo(giaITCDDoacaoBeneficiario.getLogSefazVo());
				         (new GIAITCDDoacaoBeneficiarioAliquotaBe(conn)).incluirGiaItcdDoacaoBeneficiarioAliquota(giaItcdDoacaoBeneficiarioAliquotaVo);
				      }
				   }
				   if(giaITCDDoacaoBeneficiario.getGiaitcdDoacaoSucessivaVo().getCollVO() != null && !giaITCDDoacaoBeneficiario.getGiaitcdDoacaoSucessivaVo().getCollVO().isEmpty()){
                  for (Object obj : giaITCDDoacaoBeneficiario.getGiaitcdDoacaoSucessivaVo().getCollVO()) {
                     GIAITCDDoacaoSucessivaVo doacaoSucessivaVo = (GIAITCDDoacaoSucessivaVo) obj;
                     doacaoSucessivaVo.getBeneficiarioVo().setCodigo(giaITCDDoacaoBeneficiario.getCodigo());
                     (new GIAITCDDoacaoSucessivaBe(conn)).incluirDoacaoSucessivaVo(doacaoSucessivaVo);
                  }
               }
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
	 * Método para alterar uma GIA-ITCD Doação Beneficiário
	 * @param giaITCDDoacaoBeneficiario
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarGIAITCDDoacaoBeneficiario(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiario) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiario);
		try
		{
			try
			{
				synchronized (GIAITCDDoacaoBeneficiarioVo.class)
				{
					(new BeneficiarioBe(conn)).alterarBeneficiario(giaITCDDoacaoBeneficiario);
					alterar(giaITCDDoacaoBeneficiario);
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
			catch (ConexaoException e)
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
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método responsável por realizar a exclusão de um beneficiário para uma gia ITCD Doação.
	 * @param giaITCDDoacaoBeneficiario
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void excluirGIAITCDDoacaoBeneficiario(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiario) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConexaoException, LogException, PersistenciaException, 
			  AnotacaoException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiario);		
	   try
	   {
	      try
	      {
	         synchronized (GIAITCDDoacaoBeneficiarioVo.class)
	         {
					excluir(giaITCDDoacaoBeneficiario);
	            (new BeneficiarioBe(conn)).excluirBeneficiario(giaITCDDoacaoBeneficiario);
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
			catch(ConexaoException e)
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
	   }
	   catch (SQLException e)
	   {
	      e.printStackTrace();
	      throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
	   }
		
	}

	/**
	 * Método responsável por realizar a alteração de um beneficiário de uma gia ITCD Doação.
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws ParametroObrigatorioException
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarGIAITCDDoacaoBeneficiarioAlterarGiaDoacao(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ConsultaException, 
			  IntegracaoException, ParametroObrigatorioException, ObjetoObrigatorioException, LogException, 
			  PersistenciaException, AnotacaoException, ConexaoException, RegistroExistenteException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		try
		{
			try
			{
				synchronized (GIAITCDDoacaoBeneficiarioVo.class)
				{
				   GIAITCDDoacaoBeneficiarioVo beneficiarioConsultaVo = new GIAITCDDoacaoBeneficiarioVo();
				   GIAITCDDoacaoBeneficiarioVo beneficiarioParametroVo = new GIAITCDDoacaoBeneficiarioVo();
				   beneficiarioParametroVo.setGiaITCDVo(giaITCDDoacaoBeneficiarioVo.getGiaITCDVo());
				   beneficiarioConsultaVo.setConsultaCompleta(true);
				   beneficiarioConsultaVo.setParametroConsulta(beneficiarioParametroVo);
				   beneficiarioConsultaVo = listarGIAITCDDoacaoBeneficiario(beneficiarioConsultaVo);
				   Collection beneficiarioOriginalVo = beneficiarioConsultaVo.getCollVO();
				   Collection beneficiarioInserirVo = null;
				   Collection beneficiarioExcluirVo = null;
				   Collection beneficiarioAlterarVo = null;
				   //BENEFICIÁRIOS PARA INCLUIR
				   beneficiarioInserirVo = LogUtil.obterListaObjetosParaInclusao(beneficiarioOriginalVo, giaITCDDoacaoBeneficiarioVo.getCollVO());               
				   for (Iterator iteBenef = beneficiarioInserirVo.iterator(); iteBenef.hasNext(); )
				   {
				      GIAITCDDoacaoBeneficiarioVo giaBenef = (GIAITCDDoacaoBeneficiarioVo) iteBenef.next();
				      giaBenef.setLogSefazVo(giaITCDDoacaoBeneficiarioVo.getGiaITCDVo().getLogSefazVo());
				      giaBenef.setGiaITCDVo(giaITCDDoacaoBeneficiarioVo.getGiaITCDVo());
				      incluirGIAITCDDoacaoBeneficiario(giaBenef);
				   }
				   //BENEFICIÁRIOS ALTERAR
				   beneficiarioAlterarVo = LogUtil.obterListaObjetosParaAlteracao(beneficiarioOriginalVo, giaITCDDoacaoBeneficiarioVo.getCollVO());
				   for (Iterator iteBenef = beneficiarioAlterarVo.iterator(); iteBenef.hasNext(); )
				   {
				      GIAITCDDoacaoBeneficiarioVo giaBenef = (GIAITCDDoacaoBeneficiarioVo) iteBenef.next();
				      giaBenef.setLogSefazVo(giaITCDDoacaoBeneficiarioVo.getGiaITCDVo().getLogSefazVo());
				      giaBenef.setGiaITCDVo(giaITCDDoacaoBeneficiarioVo.getGiaITCDVo());
				      alterarGIAITCDDoacaoBeneficiario(giaBenef);
				   }
				   //BENEFICIÁRIOS EXCLUIR
				    beneficiarioExcluirVo = LogUtil.obterListaObjetosParaExclusao(beneficiarioOriginalVo, giaITCDDoacaoBeneficiarioVo.getCollVO());
				    for (Iterator iteBenef = beneficiarioExcluirVo.iterator(); iteBenef.hasNext(); )
				    {
				       GIAITCDDoacaoBeneficiarioVo giaBenef = (GIAITCDDoacaoBeneficiarioVo) iteBenef.next();
				       giaBenef.setLogSefazVo(giaITCDDoacaoBeneficiarioVo.getGiaITCDVo().getLogSefazVo());
				       giaBenef.setGiaITCDVo(giaITCDDoacaoBeneficiarioVo.getGiaITCDVo());
				       excluirGIAITCDDoacaoBeneficiario(giaBenef);
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
		}
	   catch (SQLException e)
	   {
	      e.printStackTrace();
	      throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
	   }
	}
}
