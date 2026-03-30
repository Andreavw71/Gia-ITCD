package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura;

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
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaVo;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Iterator;


/**
 * Classe para encapsular as regras de negócio da entidade "Ficha Imóvel Rural Cultura".
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.5 $
 */
public class FichaImovelRuralCulturaBe extends AbstractBe
{

	/**
	 * Construtor Padrão
	 * 
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		new GenericoLogAnotacaoDao(conn, true).delete(fichaImovelRuralCulturaVo);
	}

	/**
	 * Método para remover a Ficha Imóvel Rural Cultura
	 * 
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws LogException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void removerFichaImovelRuralCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  ConexaoException, AnotacaoException, PersistenciaException, LogException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		try
		{
			try
			{
				synchronized (FichaImovelRuralCulturaVo.class)
				{
					excluir(fichaImovelRuralCulturaVo);
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
	 * Método para validar a inclusão da Ficha Imovel Rural - Cultura
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	private void validarIncluirFichaImovelRuralCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  RegistroExistenteException, ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		for (Iterator iteCult = fichaImovelRuralCulturaVo.getCollVO().iterator(); iteCult.hasNext(); )
		{
			FichaImovelRuralCulturaVo ficha = (FichaImovelRuralCulturaVo) iteCult.next();
			for (Iterator iteCultConsulta = fichaImovelRuralCulturaVo.getCollVO().iterator(); iteCultConsulta.hasNext(); )
			{
				FichaImovelRuralCulturaVo fichaConsulta = (FichaImovelRuralCulturaVo) iteCultConsulta.next();
				validaDescricaoComplementarCultura(ficha, fichaConsulta);
			}
		}
		if (!Validador.isNumericoValido(fichaImovelRuralCulturaVo.getAreaCultivada()))
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_AREA_CULTIVADA);
		if(!Validador.isNumericoValido(fichaImovelRuralCulturaVo.getValorHectare()))
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_VALOR_HECTARE);
		if (!Validador.isNumericoValido(fichaImovelRuralCulturaVo.getValorMercado()))
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_VALOR_MERCADO);
	}

	/**
	 * Método responsável por validar a descrição complementar de uma Cultura da ficha de Imóvel Rural.
	 * @param fichaImovelRuralCulturaVo
	 * @param fichaImovelRuralCulturaConsultaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static void validaDescricaoComplementarCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo, final FichaImovelRuralCulturaVo fichaImovelRuralCulturaConsultaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		Validador.validaObjeto(fichaImovelRuralCulturaConsultaVo);
	   if (fichaImovelRuralCulturaConsultaVo.getCulturaVo().getCodigo() == fichaImovelRuralCulturaVo.getCulturaVo().getCodigo()
	         && fichaImovelRuralCulturaConsultaVo.getDescricaoComplementarCultura().equals(fichaImovelRuralCulturaVo.getDescricaoComplementarCultura()))
	   {
	      if(Validador.isStringValida(fichaImovelRuralCulturaVo.getDescricaoComplementarCultura()))
	      {
	         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_DESCRICAO_EXISTENTE);
	      }
	      else
	      {
	         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_DESCRICAO_VAZIA_EXISTENTE);
	      }           
	   }		
	}

	/**
	 * Método responsável por adicionar na ficha de Imóvel Rural uma Benfeitoria.
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static void adicionarCulturaImovelRural(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
	   Iterator itFicha = fichaImovelRuralCulturaVo.getCollVO().iterator();
	   while (itFicha.hasNext())
	   {
	      FichaImovelRuralCulturaVo fichaImovelRuralCulturaAtualVo = (FichaImovelRuralCulturaVo) itFicha.next();
			validaDescricaoComplementarCultura(fichaImovelRuralCulturaVo, fichaImovelRuralCulturaAtualVo);
	   }
		calculaValorMercadoCultura(fichaImovelRuralCulturaVo);          
		fichaImovelRuralCulturaVo.getCollVO().add(fichaImovelRuralCulturaVo.clonePadrao());
		reinicializaCulturaImovelRural(fichaImovelRuralCulturaVo);        
	}

	/**
	 * 
	 * @param fichaImovelRuralCulturaVo
	 * @return
	 * @implemented by João Batista Padilha e Silva
	 */
	public static void reinicializaCulturaImovelRural(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo)
	{
		fichaImovelRuralCulturaVo.setAreaCultivada(0);
		fichaImovelRuralCulturaVo.setValorMercado(0.00);
		fichaImovelRuralCulturaVo.getCulturaVo().setCodigo(0);
		fichaImovelRuralCulturaVo.getCulturaVo().setDescricaoCultura("");
		fichaImovelRuralCulturaVo.setDescricaoComplementarCultura("");
		fichaImovelRuralCulturaVo.setValorHectare(0.00);
	}

	/**
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(fichaImovelRuralCulturaVo);
	}

	/**
	 * Método para incluir uma Ficha Imóvel Rural Cultura no banco de dados
	 * 
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirFichaImovelRuralCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  ConexaoException, RegistroExistenteException, ParametroObrigatorioException, LogException, AnotacaoException, 
			  PersistenciaException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		try
		{
			try
			{
				synchronized (FichaImovelRuralCulturaVo.class)
				{
					validarIncluirFichaImovelRuralCultura(fichaImovelRuralCulturaVo);
					incluir(fichaImovelRuralCulturaVo);
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
	 * Metodo para consultar uma Ficha Imóvel Rural Cultura
	 * 
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @return FichaImovelRuralCulturaVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaVo consultaFichaImovelRuralCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		(new FichaImovelRuralCulturaQDao(conn)).findFichaImovelRuralCultura(fichaImovelRuralCulturaVo);
		if (fichaImovelRuralCulturaVo.isConsultaCompleta())
		{
			CulturaVo cultura = new CulturaVo(fichaImovelRuralCulturaVo.getCulturaVo().getCodigo());
			cultura = new CulturaVo(cultura);
			(new CulturaBe(conn)).consultarCultura(cultura);
			fichaImovelRuralCulturaVo.setCulturaVo(cultura);
		}
		return fichaImovelRuralCulturaVo;
	}

	/**
	 * Método para listar Ficha Imóvel Rural Cultura
	 * 
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return FichaImovelRuralCulturaVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaVo listarFichaImovelRuralCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		(new FichaImovelRuralCulturaQDao(conn)).listFichaImovelRuralCultura(fichaImovelRuralCulturaVo);
		if (fichaImovelRuralCulturaVo.isConsultaCompleta())
		{
			for (Iterator iteCultura = fichaImovelRuralCulturaVo.getCollVO().iterator(); iteCultura.hasNext(); )
			{
				FichaImovelRuralCulturaVo ficha = (FichaImovelRuralCulturaVo) iteCultura.next();
				CulturaVo cultura = new CulturaVo(ficha.getCulturaVo().getCodigo());
				cultura = new CulturaVo(cultura);
				(new CulturaBe(conn)).consultarCultura(cultura);
				ficha.setCulturaVo(cultura);
			}
		}
		return fichaImovelRuralCulturaVo;
	}

	/**
	 * Método responsável por calcular o valor de mercado de uma cultura.
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static void calculaValorMercadoCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		if(fichaImovelRuralCulturaVo.getAreaCultivada() >0 && fichaImovelRuralCulturaVo.getValorHectare() > 0)
		{
			fichaImovelRuralCulturaVo.setValorMercado(fichaImovelRuralCulturaVo.getAreaCultivada() * fichaImovelRuralCulturaVo.getValorHectare());
		}
		else
		{
		   fichaImovelRuralCulturaVo.setValorMercado(0);
		}
	}

	/**
	 * Método responsável por validar os dados necessários à alteração de uma cultura para uma ficha de imóvel rural.
	 * @param fichaImovelRuralCulturaVo
	 * @throws DadoNecessarioInexistenteException
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validarAlterarFichaImovelRuralCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws DadoNecessarioInexistenteException, 
			  ObjetoObrigatorioException, RegistroExistenteException, ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		if(!Validador.isNumericoValido(fichaImovelRuralCulturaVo.getCodigo()))
		{
			throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO_NECESSARIO_PARA_ALTERAR);
		}
	   validarIncluirFichaImovelRuralCultura(fichaImovelRuralCulturaVo);
	}

	/**
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(fichaImovelRuralCulturaVo);
	}

	/**
	 * Método responsável por solicitar alteração de uma cultura para uma ficha de imóvel rural.
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void alterarFichaImovelRuralCulturaAlterarFichaImovelRural(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException, RegistroExistenteException, ParametroObrigatorioException, 
			  ConexaoException, 
			  LogException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		try
		{
			try
			{
				synchronized(FichaImovelRuralCulturaVo.class)
				{
					validarAlterarFichaImovelRuralCultura(fichaImovelRuralCulturaVo);
					alterar(fichaImovelRuralCulturaVo);
				}
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
	 * Método responsável por validar os dados necessários à exclusão de uma cultura para uma ficha de imóvel rural.
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validarExcluirFichaImovelRuralCultura (final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		if(!Validador.isNumericoValido(fichaImovelRuralCulturaVo.getCodigo()))
		{
			throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_FICHA_IMOVEL_RURAL_CULTURA_NECESSARIO_PARA_EXCLUIR);
		}
	}

	/**
	 * Método responsável por solicitar exclusão de uma cultura para uma ficha de imóvel rural.
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void exluirFichaImovelRuralCulturaExcluirFichaImovelRural(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  ConexaoException, DadoNecessarioInexistenteException, LogException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		try
		{
			try
			{
				synchronized(FichaImovelRuralCulturaVo.class)
				{
					validarExcluirFichaImovelRuralCultura(fichaImovelRuralCulturaVo);
					excluir(fichaImovelRuralCulturaVo);
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
			catch(ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException (ConexaoException.CONEXAO_FECHADA);
		}
	}
   
   public void excluirFichaImovelRuralCulturaVo(BemTributavelVo bemModificado, BemTributavelVo bemOriginal) throws ObjetoObrigatorioException, 
                                                                                                                   ConexaoException, 
                                                                                                                   DadoNecessarioInexistenteException, 
                                                                                                                   LogException, 
                                                                                                                   PersistenciaException, 
                                                                                                                   AnotacaoException
   {
      //------------------------------ Exclusão da FichaImovelRuralCulturaVo ----------------------------------------
      if(Validador.isObjetoValido(bemModificado) & Validador.isObjetoValido(bemOriginal) )
      {
         Iterator itFichaCulturaVoOriginal = ((FichaImovelRuralVo) bemOriginal.getFichaImovelVo()).getFichaImovelRuralCulturaVo().getCollVO().iterator();
         while (itFichaCulturaVoOriginal.hasNext())
         {
            boolean excluirCulturaVo = true;
            FichaImovelRuralCulturaVo bemCulturaVoOriginal = (FichaImovelRuralCulturaVo) itFichaCulturaVoOriginal.next();
            bemCulturaVoOriginal.setLogSefazVo(bemCulturaVoOriginal.getLogSefazVo());
            Iterator itFichaCulturaVoModificada = ((FichaImovelRuralVo) bemModificado.getFichaImovelVo()).getFichaImovelRuralCulturaVo().getCollVO().iterator();
            while (itFichaCulturaVoModificada.hasNext())
            {
               FichaImovelRuralCulturaVo bemCulturaVoModificada = (FichaImovelRuralCulturaVo) itFichaCulturaVoModificada.next();
               if (bemCulturaVoOriginal.getCodigo() == bemCulturaVoModificada.getCodigo())
               {
                  excluirCulturaVo = false;
               }
            }
            if (excluirCulturaVo)
            {
               exluirFichaImovelRuralCulturaExcluirFichaImovelRural(bemCulturaVoOriginal);
            }
         }
      }

   }
}
