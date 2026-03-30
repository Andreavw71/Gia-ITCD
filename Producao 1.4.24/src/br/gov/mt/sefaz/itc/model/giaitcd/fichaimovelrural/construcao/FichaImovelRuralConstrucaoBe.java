package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao;

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
import br.com.abaco.util.exceptions.RegistroExistenteException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnEstadoConservacao;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Iterator;


/**
 * Classe responsável por implementar a regra de negócio da entidade "Ficha Imóvel Rural Construcao"
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.5 $
 */
public class FichaImovelRuralConstrucaoBe extends AbstractBe
{

	/**
	 * Construtor Padrão
	 * 
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		new GenericoLogAnotacaoDao(conn, true).delete(fichaImovelRuralConstrucaoVo);
	}

	/**
	 * Método para remover uma Ficha Imóvel Rural Construção
	 * 
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void removerFichaImovelRuralConstrucao(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		try
		{
			try
			{
				synchronized (FichaImovelRuralConstrucaoVo.class)
				{
					excluir(fichaImovelRuralConstrucaoVo);
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
	 * Método para validar a inclusão da Ficha Imovel Rural - Construção
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	private void validarIncluirFichaImovelRuralConstrucao(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  RegistroExistenteException, ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		for (Iterator iteCons = fichaImovelRuralConstrucaoVo.getCollVO().iterator(); iteCons.hasNext(); )
		{
			FichaImovelRuralConstrucaoVo construcao = (FichaImovelRuralConstrucaoVo) iteCons.next();
			for (Iterator iteConsConsulta = 
							fichaImovelRuralConstrucaoVo.getCollVO().iterator(); iteConsConsulta.hasNext(); )
			{
				FichaImovelRuralConstrucaoVo construcaoConsulta = (FichaImovelRuralConstrucaoVo) iteConsConsulta.next();
				validaDescricaoComplementarConstrucao(construcao, construcaoConsulta);
			}
		}
		if (!Validador.isDominioNumericoValido(fichaImovelRuralConstrucaoVo.getSituacaoEstadoConservacao()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_CONSERVACAO);
		}
		if (!Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getValorMercado()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_VALOR_MERCADO);
		}
	}

	/**
	 * Método responsável por validar os dados necessários à alteração de uma construção para ficha de imóvel rural.
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validaAlterarFichaImovelRuralConstrucao(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException, RegistroExistenteException, ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		if(!Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getCodigo()))
		{
			throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO_NECESSARIO_PARA_ALTERAR);
		}
	   validarIncluirFichaImovelRuralConstrucao(fichaImovelRuralConstrucaoVo);
	}

	/**
	 * Método responsável por validar a descrição complementar de uma Construção da ficha de Imóvel Rural.
	 * @param fichaImovelRuralConstrucaoVo
	 * @param fichaImovelRuralConstrucaoConsultaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private static void validaDescricaoComplementarConstrucao(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo, final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoConsultaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		Validador.validaObjeto(fichaImovelRuralConstrucaoConsultaVo);
	   if (fichaImovelRuralConstrucaoVo.getDescricaoConstrucao().equals(fichaImovelRuralConstrucaoConsultaVo.getDescricaoConstrucao())
	         && fichaImovelRuralConstrucaoVo.getConstrucaoVo().getCodigo() == fichaImovelRuralConstrucaoConsultaVo.getConstrucaoVo().getCodigo())
	   {
	      if(Validador.isStringValida(fichaImovelRuralConstrucaoVo.getDescricaoConstrucao()))
	      {
	         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_DESCRICAO_EXISTENTE);
	      }
	      else
	      {
	         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_DESCRICAO_VAZIA_EXISTENTE);
	      }        
	   }		
	}

	/**
	 * Método responsável por adicionar na ficha de Imóvel Rural uma Construção.
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static void adicionarConstrucoesImovelRural(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
	   Iterator itFicha = fichaImovelRuralConstrucaoVo.getCollVO().iterator();
	   while (itFicha.hasNext())
	   {
	      FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoAtualVo = (FichaImovelRuralConstrucaoVo) itFicha.next();
			validaDescricaoComplementarConstrucao(fichaImovelRuralConstrucaoVo, fichaImovelRuralConstrucaoAtualVo);
	   }
		fichaImovelRuralConstrucaoVo.getCollVO().add(fichaImovelRuralConstrucaoVo.clonePadrao());
		reinicializaConstrucaoImovelRural(fichaImovelRuralConstrucaoVo);        
	}
	
	/**
	 * 
	 * @param fichaImovelRuralConstrucaoVo
	 * @return
	 * @implemented by João Batista Padilha e Silva
	 */
	public static  void reinicializaConstrucaoImovelRural(FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo)
	{
		fichaImovelRuralConstrucaoVo.setValorMercado(0.00);
		fichaImovelRuralConstrucaoVo.setSituacaoEstadoConservacao(new DomnEstadoConservacao());
		fichaImovelRuralConstrucaoVo.getConstrucaoVo().setCodigo(0);
		fichaImovelRuralConstrucaoVo.getConstrucaoVo().setDescricaoConstrucao(EntidadeVo.STRING_VAZIA);
		fichaImovelRuralConstrucaoVo.setDescricaoConstrucao(EntidadeVo.STRING_VAZIA);
	}

	/**
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(fichaImovelRuralConstrucaoVo);
	}

	/**
	 * Método para incluir uma Ficha Imóvel Rural Construção
	 * 
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirFichaImovelRuralConstrucao(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, RegistroExistenteException, LogException, PersistenciaException, 
			  AnotacaoException, 
			  ConexaoException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		try
		{
			try
			{
				synchronized (FichaImovelRuralConstrucaoVo.class)
				{
					validarIncluirFichaImovelRuralConstrucao(fichaImovelRuralConstrucaoVo);
					incluir(fichaImovelRuralConstrucaoVo);
				}
			}			
			catch (ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch (RegistroExistenteException e)
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
	 * Método para consultar uma Ficha Imóvel Rural Construção
	 * 
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return FichaImovelRuralConstrucaoVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoVo consultaFichaImovelRuralConstrucao(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		(new FichaImovelRuralConstrucaoQDao(conn)).findFichaImovelRuralConstrucao(fichaImovelRuralConstrucaoVo);
		return fichaImovelRuralConstrucaoVo;
	}

	/**
	 * Método para listar Ficha Imóvel Rural Construção
	 * 
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return FichaImovelRuralConstrucaoVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoVo listarFichaImovelRuralConstrucao(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		(new FichaImovelRuralConstrucaoQDao(conn)).listFichaImovelRuralConstrucao(fichaImovelRuralConstrucaoVo);
		if(fichaImovelRuralConstrucaoVo.isConsultaCompleta())
		{
			for(Iterator iteCons = fichaImovelRuralConstrucaoVo.getCollVO().iterator(); iteCons.hasNext();)
			{
				FichaImovelRuralConstrucaoVo ficha = (FichaImovelRuralConstrucaoVo) iteCons.next();
				ConstrucaoVo construcao = new ConstrucaoVo(ficha.getConstrucaoVo().getCodigo());
				construcao = new ConstrucaoVo(construcao);
				(new ConstrucaoBe(conn)).consultarConstrucao(construcao);
				ficha.setConstrucaoVo(construcao);
			}
		}
		return fichaImovelRuralConstrucaoVo;
	}

	/**
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(fichaImovelRuralConstrucaoVo);
	}

	/**
	 * Método responsável por solicitar a alteração de uma construção para uma ficha de imóvel rural.
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @throws RegistroExistenteException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void alterarFichaImovelRuralConstrucaoAlterarFichaImovelRural(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, DadoNecessarioInexistenteException, RegistroExistenteException, LogException, 
			  PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		try
		{
			try
			{
				synchronized(FichaImovelRuralConstrucaoVo.class)
				{
				   validaAlterarFichaImovelRuralConstrucao(fichaImovelRuralConstrucaoVo);
					alterar(fichaImovelRuralConstrucaoVo);
				}
			}
			catch(ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch(DadoNecessarioInexistenteException e)
			{
				conn.rollback();
				throw e;
			}
			catch(RegistroExistenteException e)
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
	 * Métodos responsável por valdiar dados necessários à exclusão de uma cosntrução.
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validarExcluirFichaImovelRuralConstrucao (final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo ) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		if(!Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getCodigo()))
		{
			throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO_NECESSARIO_PARA_EXCLUIR);
		}		
	}

	/**
	 * Método responsável por solicitar a exclusão de uma construção para uma ficha de imóvel rural.
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void exluirFichaImovelRuralConstrucaoExcluirFichaImovelRural(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException, LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		try
		{
			try
			{
				synchronized(FichaImovelRuralConstrucaoVo.class)
				{
					validarExcluirFichaImovelRuralConstrucao(fichaImovelRuralConstrucaoVo);
					excluir(fichaImovelRuralConstrucaoVo);
				}
			}
			catch(DadoNecessarioInexistenteException e)
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
   
   public void excluirFichaImovelRuralRebanhoVo(BemTributavelVo bemModificado, BemTributavelVo bemOriginal) throws ObjetoObrigatorioException, 
                                                                                                                   DadoNecessarioInexistenteException, 
                                                                                                                   LogException, 
                                                                                                                   PersistenciaException, 
                                                                                                                   AnotacaoException, 
                                                                                                                   ConexaoException
   {
      //------------------------------ Exclusão da FichaImovelRuralConstrucaoVo ----------------------------------------
      if (Validador.isObjetoValido(bemModificado) & Validador.isObjetoValido(bemOriginal))
      {
         Iterator itFichaConstrucaoVoOriginal = ((FichaImovelRuralVo) bemOriginal.getFichaImovelVo()).getFichaImovelRuralConstrucaoVo().getCollVO().iterator();
         while (itFichaConstrucaoVoOriginal.hasNext())
         {
            boolean excluirConstrucaoRural = true;
            FichaImovelRuralConstrucaoVo bemConstrucaoVoOriginal = (FichaImovelRuralConstrucaoVo) itFichaConstrucaoVoOriginal.next();
            Iterator itFichaConstrucaoVoModificada = ((FichaImovelRuralVo) bemModificado.getFichaImovelVo()).getFichaImovelRuralConstrucaoVo().getCollVO().iterator();
            while (itFichaConstrucaoVoModificada.hasNext())
            {
               FichaImovelRuralConstrucaoVo bemConstrucaoVoModificada = (FichaImovelRuralConstrucaoVo) itFichaConstrucaoVoModificada.next();
               if (bemConstrucaoVoOriginal.getCodigo() == bemConstrucaoVoModificada.getCodigo())
               {
                  excluirConstrucaoRural = false;
               }
            }
            if (excluirConstrucaoRural)
            {
               exluirFichaImovelRuralConstrucaoExcluirFichaImovelRural(bemConstrucaoVoOriginal);
            }
         }
      }
      
   }
}
