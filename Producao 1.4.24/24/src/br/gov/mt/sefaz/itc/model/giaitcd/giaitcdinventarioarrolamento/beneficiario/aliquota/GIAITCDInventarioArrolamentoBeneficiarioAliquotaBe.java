package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Iterator;


/**
 * Classe de negócio da entidade GIAITCDInventarioArrolamentoBeneficiarioAliquota
 * @author Lucas Nascimento
 */
public class GIAITCDInventarioArrolamentoBeneficiarioAliquotaBe extends AbstractBe
{
	/**
	 * Contrutor padrão vazio
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioAliquotaBe() throws SQLException
	{
		super();
	}

	/**
	 * Contrutor recebendo uma conexão
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioAliquotaBe(Connection conn) throws SQLException
	{
		super(conn);
	}

	/**
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
	}

	/**
	 * Inclui no banco de dados uma Aliquota de um determinado beneficiario de uma gia do tipo Inventario Arrolamento
	 * 
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Lucas Nascimento
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirGIAITCDInventarioArrolamentoBeneficiarioAliquota(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		try
		{
			try
			{
				synchronized (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo.class)
				{
					incluir(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
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
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
	}

	/**
	 * Altera os dados persistidos de uma determinada aliquota de um determinado beneficiário de uma determinada
	 * GIA do tipo Inventário e Arrolamento
	 * 
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo     dados a serem modificados
	 * @throws ObjetoObrigatorioException
	 */
	public synchronized void alterarGIAITCDInventarioArrolamentoBeneficiarioAliquota(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		try
		{
			try
			{
				synchronized (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo.class)
				{
					alterar(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
					giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setMensagem(MensagemSucesso.ALTERAR);
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
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Exclui uma determinada aliquota de um determinado beneficiário de uma determinada
	 * GIA do tipo Inventário e Arrolamento
	 * 
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void excluirGIAITCDInventarioArrolamentoBeneficiarioAliquota(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		try
		{
			try
			{
				synchronized (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo.class)
				{
					excluir(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
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
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);	
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
	}

	/**
	 * Consulta uma aliquota para o beneficiario
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo consultarGIAITCDInventarioArrolamentoBeneficiarioAliquota(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		(new GIAITCDInventarioArrolamentoBeneficiarioAliquotaQDao(conn)).findGIAITCDInventarioArrolamentoBeneficiarioAliquota(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		return giaITCDInventarioArrolamentoBeneficiarioAliquotaVo;
	}

	/**
	 * Retorna uma lista de aliquotas de uma determinado beneficiario de inventario arrolamento
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo listarGIAITCDInventarioArrolamentoBeneficiarioAliquota(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		(new GIAITCDInventarioArrolamentoBeneficiarioAliquotaQDao(conn)).listGIAITCDInventarioArrolamentoBeneficiarioAliquota(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		return giaITCDInventarioArrolamentoBeneficiarioAliquotaVo;
	}

	/**
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void alterarGIAITCDInventarioArrolamentoBeneficiarioAliquota_AlterarGIAITCDInventarioArrolamentoBeneficiario(GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  ConsultaException, LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
	   Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
	   try
	   {
	      try
	      {
	         synchronized (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo.class)
	         {
	            GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo consulta = new GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo();
	            GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo parametro = new GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo();
	            parametro.getGiaITCDIventarioArrolamentoBeneficiarioVo().setCodigo(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getGiaITCDIventarioArrolamentoBeneficiarioVo().getCodigo());
	            consulta.setParametroConsulta(parametro);
	            consulta.setConsultaCompleta(true);
	            consulta = new GIAITCDInventarioArrolamentoBeneficiarioAliquotaBe(conn).listarGIAITCDInventarioArrolamentoBeneficiarioAliquota(consulta);
	            for (Iterator ite = consulta.getCollVO().iterator(); ite.hasNext(); )
	            {
	               GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquotaAtual = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) ite.next();
	               aliquotaAtual.setGiaITCDIventarioArrolamentoBeneficiarioVo(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getGiaITCDIventarioArrolamentoBeneficiarioVo());
	               aliquotaAtual.setLogSefazVo(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getGiaITCDIventarioArrolamentoBeneficiarioVo().getLogSefazVo());
	               excluir(aliquotaAtual);
	            }                             
					for (Iterator ite = giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getCollVO().iterator(); ite.hasNext(); )
					{
						GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquotaAtual = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) ite.next();
						if(aliquotaAtual.getCodigoAliquota() != 0)
						{
						   aliquotaAtual.setGiaITCDIventarioArrolamentoBeneficiarioVo(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getGiaITCDIventarioArrolamentoBeneficiarioVo());
						   aliquotaAtual.setLogSefazVo(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getGiaITCDIventarioArrolamentoBeneficiarioVo().getLogSefazVo());
						   incluirGIAITCDInventarioArrolamentoBeneficiarioAliquota(aliquotaAtual);							
						}
					}                 
	         }
	      }
	      catch (ConsultaException e)
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
