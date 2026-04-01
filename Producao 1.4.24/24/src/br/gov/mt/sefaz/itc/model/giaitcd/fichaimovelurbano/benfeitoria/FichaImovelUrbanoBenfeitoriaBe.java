package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.benfeitoria;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Iterator;


/**
 * Classe de negócio.
 * 
 * @author Lucas Nascimento
 * @version $Revision: 1.4 $
 */
public class FichaImovelUrbanoBenfeitoriaBe extends AbstractBe
{

	/**
	 * Construtor padrão recebendo uma conexão com o banco de dados.
	 * @param conexao
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoBenfeitoriaBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Construtor vazio.
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoBenfeitoriaBe() throws SQLException
	{
		super();
	}

	/**
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(fichaImovelUrbanoBenfeitoriaVo);
	}
	
	private void validaIncluirFichaImovelUrbanoBenfeitoria(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		if(!Validador.isNumericoValido(fichaImovelUrbanoBenfeitoriaVo.getFichaImovelUrbanoVo().getCodigo()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_BENFEITORIA_PARAMETRO_CODIGO_IMOVEL);
		}
		if(!Validador.isNumericoValido(fichaImovelUrbanoBenfeitoriaVo.getBenfeitoriaVo().getCodigo()))
		{
		   throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_BENFEITORIA_PARAMETRO_CODIGO_BENFEITORIA);
		}
	}

	/**
	 * Inclui os dados de uma Ficha de Imóvel Urbano Benfeitoria no banco de dados.
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	public synchronized void incluirFichaImovelUrbanoBenfeitoria(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  ConexaoException, LogException, AnotacaoException, PersistenciaException, ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		try
		{
			try
			{
				synchronized (FichaImovelUrbanoBenfeitoriaVo.class)
				{
				   validaIncluirFichaImovelUrbanoBenfeitoria(fichaImovelUrbanoBenfeitoriaVo);
					incluir(fichaImovelUrbanoBenfeitoriaVo);
				}
			}		
			catch(ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
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
	}

	/**
	 * Método para remover uma Ficha Imóvel Urbano - Benfeitoria
	 * 
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void removerFichaImovelUrbanoBenfeitoria(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  ConexaoException, LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		try
		{
			try
			{
				synchronized (FichaImovelUrbanoBenfeitoriaVo.class)
				{
					excluir(fichaImovelUrbanoBenfeitoriaVo);
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
	}

	/**
	 * Método para consultar uma Ficha Imóvel Urbano - Benfeitoria
	 * 
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return FichaImovelUrbanoBenfeitoriaVo
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoBenfeitoriaVo consultaFichaImovelUrbanoBenfeitoria(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		(new FichaImovelUrbanoBenfeitoriaQDao(conn)).findFichaImovelUrbanoBenfeitoria(fichaImovelUrbanoBenfeitoriaVo);
		return fichaImovelUrbanoBenfeitoriaVo;
	}

	/**
	 * Método para listar Ficha Imóvel Urbano - Benfeitoria
	 * 
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return FichaImovelUrbanoBenfeitoriaVo
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoBenfeitoriaVo listarFichaImovelUrbanoBenfeitoria(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		(new FichaImovelUrbanoBenfeitoriaQDao(conn)).listFichaImovelUrbanoBenfeitoria(fichaImovelUrbanoBenfeitoriaVo);
		return fichaImovelUrbanoBenfeitoriaVo;
	}

	/**
	 * Método responsável por verificar se uma benfeitoria é válida para adição, e em caso afirmativo adicionar a mesma para uma ficha de imóvel urbano.
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static void adicionarBenfeitoriaImovelUrbano(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
	   Iterator itFicha = fichaImovelUrbanoBenfeitoriaVo.getCollVO().iterator();
	   while (itFicha.hasNext())
	   {
	      FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaAtualVo = (FichaImovelUrbanoBenfeitoriaVo) itFicha.next();
	      validaDescricaoComplementarBenfeitoria(fichaImovelUrbanoBenfeitoriaVo, fichaImovelUrbanoBenfeitoriaAtualVo);
	   }
	   fichaImovelUrbanoBenfeitoriaVo.getCollVO().add(fichaImovelUrbanoBenfeitoriaVo.clone());
	   reinicializaBenfeitoriaImovelUrbano(fichaImovelUrbanoBenfeitoriaVo);   		
	}

	/**
	 * Método responsável por validar se uma benfeitoria já foi inserida com a mesma descrição para uma ficha de imóvel urbano.
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @param fichaImovelUrbanoBenfeitoriaConsultaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private static void validaDescricaoComplementarBenfeitoria(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo, final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaConsultaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
	   Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
	   Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaConsultaVo);
	   if (fichaImovelUrbanoBenfeitoriaVo.getBenfeitoriaVo().getCodigo() == fichaImovelUrbanoBenfeitoriaConsultaVo.getBenfeitoriaVo().getCodigo() && 
	         fichaImovelUrbanoBenfeitoriaVo.getDescricaoComplementarBenfeitoria().equals(fichaImovelUrbanoBenfeitoriaConsultaVo.getDescricaoComplementarBenfeitoria()))
	   {
	      if(Validador.isStringValida(fichaImovelUrbanoBenfeitoriaVo.getDescricaoComplementarBenfeitoria()))
	      {
	         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_URBANO_BENFEITORIA_DESCRICAO_EXISTENTE);
	      }
	      else
	      {
	        throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_URBANO_BENFEITORIA_DESCRICAO_VAZIA_EXISTENTE);
	      }
	   }     
	
	}

	/**
	 * Método responsável por limpar os valores de uma benfeitoria.
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private static void reinicializaBenfeitoriaImovelUrbano(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		fichaImovelUrbanoBenfeitoriaVo.getBenfeitoriaVo().setCodigo(0);
		fichaImovelUrbanoBenfeitoriaVo.getBenfeitoriaVo().setDescricaoBenfeitoria("");
		fichaImovelUrbanoBenfeitoriaVo.setDescricaoComplementarBenfeitoria("");
	}

	/**
	 * Método responsável por validar os dados necessários à alteração de uma benfeitoria par uma ficha dei móvel urbano.
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validarAlterarFichaImovelUrbanoBenfeitoria(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		if(!Validador.isNumericoValido(fichaImovelUrbanoBenfeitoriaVo.getCodigo()))
		{
			throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO_NECESSARIO_PARA_ALTERAR);
		}		
	}

	/**
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(fichaImovelUrbanoBenfeitoriaVo);
	}

	/**
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(fichaImovelUrbanoBenfeitoriaVo);
	}	

	/**
	 * Método responsável por realizar a alteração de uma benfeitoria para uma ficha de imóvel urbano.
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void alterarFichaImovelUrbanoBenfeitoriaAlterarFichaImovelRural(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException, ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		try
		{
			try
			{
				synchronized(FichaImovelUrbanoBenfeitoriaVo.class)
				{
					validarAlterarFichaImovelUrbanoBenfeitoria(fichaImovelUrbanoBenfeitoriaVo);
					alterar(fichaImovelUrbanoBenfeitoriaVo);
				}				
			}
			catch(DadoNecessarioInexistenteException e)
			{
				conn.rollback();
				throw e;
			}
			catch(ObjetoObrigatorioException e)
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
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método responsável por validar os dados necessários à exclusão de uma benfeitoria para uma ficha de imóvel urbano.
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validarExcluirFichaImovelUrbanoBenfeitoria(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		if(!Validador.isNumericoValido(fichaImovelUrbanoBenfeitoriaVo.getCodigo()))
		{
			throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_FICHA_IMOVEL_URBANO_BENFEITORIA_NECESSARIO_PARA_EXCLUIR);
		}
	}

	/**
	 * Método responsável por realizar a exclusão de uma benfeitoria para uma ficha de imóvel urbano.
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws DadoNecessarioInexistenteException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void excluirFichaImovelUrbanoBenfeitoriaExcluirFichaImovelUrbano(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException, DadoNecessarioInexistenteException, ConexaoException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		try
		{
			try
			{
				synchronized(FichaImovelUrbanoBenfeitoriaVo.class)
				{
					validarExcluirFichaImovelUrbanoBenfeitoria(fichaImovelUrbanoBenfeitoriaVo);
					excluir(fichaImovelUrbanoBenfeitoriaVo);
				}
			}
			catch(DadoNecessarioInexistenteException e)
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
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}
}
