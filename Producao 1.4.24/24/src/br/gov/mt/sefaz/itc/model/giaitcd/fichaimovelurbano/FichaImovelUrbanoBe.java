package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.benfeitoria.FichaImovelUrbanoBenfeitoriaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.benfeitoria.FichaImovelUrbanoBenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptu.IPTUBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptu.IPTUVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptuprefeitura.IPTUPrefeituraBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptuprefeitura.IPTUPrefeituraVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CepIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.EnderecoIntegracaoVo;
import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Iterator;

/**
 * Classe de negócio.
 * 
 * @author Lucas Nascimento
 * @version $Revision: 1.5 $
 */
public class FichaImovelUrbanoBe extends AbstractBe
{

	/**
	 * Construtor padrï¿½o recebendo uma conexão com o banco de dados.
	 * @param conexao
	 */
	public FichaImovelUrbanoBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Contrutor padrï¿½o vazio
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoBe() throws SQLException
	{
		super();
	}

	/**
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(fichaImovelUrbanoVo);
	}

	/**
	 * Inclui os dados de uma Ficha de Imï¿½vel Urbano no banco de dados.
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	public synchronized void incluirFichaImovelUrbano(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  ConexaoException, ParametroObrigatorioException, IntegracaoException, LogException, PersistenciaException, 
			  AnotacaoException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		try
		{
			try
			{
				validaIncluirAlterarFichaImovelUrbano(fichaImovelUrbanoVo);
				synchronized (FichaImovelUrbanoVo.class)
				{					
					fichaImovelUrbanoVo.setEnderecoVo((new CadastroBe(conn)).incluirEndereco(fichaImovelUrbanoVo.getEnderecoVo()));
					incluir(fichaImovelUrbanoVo);
					FichaImovelUrbanoBenfeitoriaBe fichaImovelUrbanoBenfeitoriaBe =  new FichaImovelUrbanoBenfeitoriaBe(conn);
					for (Iterator itemBenfeitoria = fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().getCollVO().iterator(); itemBenfeitoria.hasNext(); )
					{
						FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo = (FichaImovelUrbanoBenfeitoriaVo) itemBenfeitoria.next();
						fichaImovelUrbanoBenfeitoriaVo.setFichaImovelUrbanoVo(fichaImovelUrbanoVo);
					   fichaImovelUrbanoBenfeitoriaVo.setLogSefazVo(fichaImovelUrbanoVo.getLogSefazVo());
                  
					    ExibirLOG.exibirLog("Descrição: " + fichaImovelUrbanoBenfeitoriaVo.getDescricaoComplementarBenfeitoria());
                   ExibirLOG.exibirLog("Tamanho Descrição: " + fichaImovelUrbanoBenfeitoriaVo.getDescricaoComplementarBenfeitoria().length());
						
                  fichaImovelUrbanoBenfeitoriaBe.incluirFichaImovelUrbanoBenfeitoria(fichaImovelUrbanoBenfeitoriaVo);
					}
				}
			}
			catch (ConexaoException e)
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
			catch (IntegracaoException e)
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
	 * Método que encapsula a lï¿½gica de validaï¿½ï¿½o e regras de negócio da inclusï¿½o.
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Lucas Nascimento
	 */
	private void validaIncluirAlterarFichaImovelUrbano(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, IntegracaoException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		//Tipo Logradouro
		if (!Validador.isStringValida(fichaImovelUrbanoVo.getEnderecoVo().getTipoLogr()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_TIPO_LOGRADOURO);
		}
		//Informar Logradouro
		if (!Validador.isStringValida(fichaImovelUrbanoVo.getEnderecoVo().getLogradouro()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_LOGRADOURO);
		}
		//Informar Nï¿½mero do Logradouro
		if (!Validador.isStringValida(fichaImovelUrbanoVo.getEnderecoVo().getNumrLogradouro()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_NUMERO_LOGRADOURO);
		}
		//Informar Bairro
		if (!Validador.isStringValida(fichaImovelUrbanoVo.getEnderecoVo().getBairro()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_BAIRRO);
		}
		//Informar CEP
		if (!Validador.isObjetoValido(fichaImovelUrbanoVo.getEnderecoVo().getCep()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_CEP_NAO_INFORMADO);
		}
		else if (!Validador.isNumericoValido(fichaImovelUrbanoVo.getEnderecoVo().getCep().getCodgCep()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_CEP_NAO_INFORMADO);
		}
		else if ((fichaImovelUrbanoVo.getEnderecoVo().getCep().getCodgCep().toString().length() != 8))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_CEP_INVALIDO);
		}
		else
		{
			CadastroBe cadastroBe = new CadastroBe(conn);
			CepIntegracaoVo cepVo = cadastroBe.buscarCep(fichaImovelUrbanoVo.getEnderecoVo().getCep());
			if (!cepVo.isExiste())
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_CEP_NAO_CADASTRADO);
			}
		}
		// Selecionar o tipo do Imovel
		if (!Validador.isNumericoValido(fichaImovelUrbanoVo.getBemTributavelVo().getCodigo()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_TIPO_IMOVEL);
		}
		if(fichaImovelUrbanoVo.getBemTributavelVo().getBemVo().getPossuiConstrucao().is(DomnSimNao.SIM))
		{
		   // Selecionar o estado de conservaï¿½ï¿½o
		   if (!Validador.isDominioNumericoValido(fichaImovelUrbanoVo.getEstadoConservacao()))
		   {
		      throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_INFORMAR_ESTADO_CONSERVACAO);
		   }
		   // Informar a ï¿½rea contruida
		   if (!Validador.isNumericoValido(fichaImovelUrbanoVo.getQuantidadeAreaConstruida()))
		   {
		      throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_AREA_CONSTRUIDA);
		   }
		   // Informar o tipo de construï¿½ï¿½o
		   if (!fichaImovelUrbanoVo.getConstrucaoVo().isExiste())
		   {
		      throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_TIPO_CONSTRUCAO);
		   }			
		}
	   // Informar a ï¿½rea total
	   if (!Validador.isNumericoValido(fichaImovelUrbanoVo.getQuantidadeAreaTotal()))
	   {
	      throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_AREA_TOTAL);
	   }		
		// Informar o tipo de acesso
		if (!Validador.isDominioNumericoValido(fichaImovelUrbanoVo.getTipoAcesso()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_INFORMAR_TIPO_ACESSO);
		}
		// Informar o valor total de mercado
		if (!Validador.isNumericoValido(fichaImovelUrbanoVo.getValorMercadoTotal()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_VALOR_MERCADO);
		}
		// Informar o valor venal IPTU
//		if (!Validador.isNumericoValido(fichaImovelUrbanoVo.getValorVenalIptu()))
//		{
//			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_VALOR_VENAL);
//		}
	}

	/**
	 * Método para alterar uma Ficha Imï¿½vel Urbano
	 * 
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void alterarFichaImovelUrbano(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, IntegracaoException, ConexaoException, LogException, PersistenciaException, 
			  AnotacaoException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		validaIncluirAlterarFichaImovelUrbano(fichaImovelUrbanoVo);
		removerFichaImovelUrbano(fichaImovelUrbanoVo);
		incluirFichaImovelUrbano(fichaImovelUrbanoVo);
	}

	/**
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(fichaImovelUrbanoVo);
	}

	/**
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(fichaImovelUrbanoVo);
	}


	/**
	 * Método responsável por realizar a alteração de uma ficha de imóvel urbano.
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @throws LogException
	 * @throws DadoNecessarioInexistenteException
	 * @throws IntegracaoException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void alterarFichaImovelUrbanoAlterarBemTributavel(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  ConexaoException, ParametroObrigatorioException, LogException, DadoNecessarioInexistenteException, 
			  IntegracaoException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		try
		{
			try
			{
				synchronized(FichaImovelUrbanoVo.class)
				{
					validaIncluirAlterarFichaImovelUrbano(fichaImovelUrbanoVo);	
					if(Validador.isNumericoValido(fichaImovelUrbanoVo.getCodigo()))
					{
						alterar(fichaImovelUrbanoVo);
						if(Validador.isCollectionValida(fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().getCollVO()))
						{
						   FichaImovelUrbanoBenfeitoriaBe fichaImovelUrbanoBenfeitoriaBe = new FichaImovelUrbanoBenfeitoriaBe(conn);
						   for(Iterator iteBenfeitoria = fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().getCollVO().iterator(); iteBenfeitoria.hasNext(); )
						   {
						      FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo = (FichaImovelUrbanoBenfeitoriaVo) iteBenfeitoria.next();
						      fichaImovelUrbanoBenfeitoriaVo.setFichaImovelUrbanoVo(fichaImovelUrbanoVo);
						      fichaImovelUrbanoBenfeitoriaVo.setLogSefazVo(fichaImovelUrbanoVo.getLogSefazVo());
						      fichaImovelUrbanoBenfeitoriaBe.alterarFichaImovelUrbanoBenfeitoriaAlterarFichaImovelRural(fichaImovelUrbanoBenfeitoriaVo);                      
						   }							
						}						
					}
					else
					{
						throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_FICHA_IMOVEL_RURAL_NECESSARIO_PARA_ALTERAR);
					}
				}
			}
			catch(ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch(IntegracaoException e)
			{
				conn.rollback();
				throw e;
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
	 * Método para remover uma Ficha Imï¿½vel Urbano
	 * 
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void removerFichaImovelUrbano(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		try
		{
			try
			{
				synchronized (FichaImovelUrbanoVo.class)
				{
					FichaImovelUrbanoBenfeitoriaBe fichaImovelUrbanoBenfeitoriaBe = new FichaImovelUrbanoBenfeitoriaBe(conn);
					for (Iterator itemBenfeitoria = fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().getCollVO().iterator(); itemBenfeitoria.hasNext(); )
					{
						FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo = (FichaImovelUrbanoBenfeitoriaVo) itemBenfeitoria.next();
						fichaImovelUrbanoBenfeitoriaVo.getFichaImovelUrbanoVo().setCodigo(fichaImovelUrbanoVo.getCodigo());
					   fichaImovelUrbanoBenfeitoriaVo.setLogSefazVo(fichaImovelUrbanoVo.getLogSefazVo());
						fichaImovelUrbanoBenfeitoriaBe.removerFichaImovelUrbanoBenfeitoria(fichaImovelUrbanoBenfeitoriaVo);
					}
					excluir(fichaImovelUrbanoVo);
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
	 * Método para consultar uma Ficha Imï¿½vel Urbano
	 * 
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @return FichaImovelUrbanoVo
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoVo consultarFichaImovelUrbano(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, ParametroObrigatorioException, SQLException, 
             IOException
   {
		Validador.validaObjeto(fichaImovelUrbanoVo);
		(new FichaImovelUrbanoQDao(conn)).findFichaImovelUrbano(fichaImovelUrbanoVo);
		if (fichaImovelUrbanoVo.isExiste() && fichaImovelUrbanoVo.isConsultaCompleta())
		{
			listarBenfeitoriaFichaImovelUrbano(fichaImovelUrbanoVo);
			BemTributavelVo bemTributavelVo = new BemTributavelVo();
			bemTributavelVo.setCodigo(fichaImovelUrbanoVo.getBemTributavelVo().getCodigo());
			bemTributavelVo = new BemTributavelVo(bemTributavelVo);
			(new BemTributavelBe(conn)).consultarBemTributavel(bemTributavelVo);
			fichaImovelUrbanoVo.setBemTributavelVo(bemTributavelVo);
			EnderecoIntegracaoVo endereco = fichaImovelUrbanoVo.getEnderecoVo();
			if (endereco.isExiste())
			{
				endereco.setParametroConsulta(endereco);
				fichaImovelUrbanoVo.setEnderecoVo((new CadastroBe(conn)).buscarEndereco(endereco));
			}
			ConstrucaoVo construcao = fichaImovelUrbanoVo.getConstrucaoVo();
			if (construcao.isExiste())
			{
				construcao.setParametroConsulta(construcao);
				fichaImovelUrbanoVo.setConstrucaoVo((new ConstrucaoBe(conn)).consultarConstrucao(construcao));
			}
         IPTUVo iptu = fichaImovelUrbanoVo.getIptuVo();
         
		   if (iptu.isExiste())
		   {
		      iptu.setParametroConsulta(iptu);
		      fichaImovelUrbanoVo.setIptuVo((new IPTUBe(conn)).consultarIPTU(iptu));
		   }
		   IPTUPrefeituraVo iptuPrefeitura = fichaImovelUrbanoVo.getIptuPrefeituraVo();		   
		   if (iptuPrefeitura.isExiste())
		   {
		      iptuPrefeitura.setParametroConsulta(iptuPrefeitura);
		      fichaImovelUrbanoVo.setIptuPrefeituraVo((new IPTUPrefeituraBe(conn)).consultarIPTUPrefeitura(iptuPrefeitura));
		   }
         
		}
		return fichaImovelUrbanoVo;
	}

	/**
	 * Método para listar Ficha Imï¿½vel Urbano
	 * 
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @return FichaImovelUrbanoVo
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoVo listarFichaImovelUrbano(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, ParametroObrigatorioException, SQLException, 
             IOException
   {
		Validador.validaObjeto(fichaImovelUrbanoVo);
		(new FichaImovelUrbanoQDao(conn)).listFichaImovelUrbano(fichaImovelUrbanoVo);
		if (fichaImovelUrbanoVo.isConsultaCompleta())
		{
			for (Iterator iteUrbano = fichaImovelUrbanoVo.getCollVO().iterator(); iteUrbano.hasNext(); )
			{
				FichaImovelUrbanoVo fichaImovelUrbanoAtualVo = (FichaImovelUrbanoVo) iteUrbano.next();
				if (fichaImovelUrbanoAtualVo.isConsultaCompleta())
				{
					listarBenfeitoriaFichaImovelUrbano(fichaImovelUrbanoAtualVo);
					BemTributavelVo bemTributavelVo = new BemTributavelVo();
					bemTributavelVo.setCodigo(fichaImovelUrbanoAtualVo.getBemTributavelVo().getCodigo());
					bemTributavelVo = new BemTributavelVo(bemTributavelVo);
					(new BemTributavelBe(conn)).listarItemBemTributavel(bemTributavelVo);
					EnderecoIntegracaoVo endereco = fichaImovelUrbanoAtualVo.getEnderecoVo();
					endereco.setParametroConsulta(endereco);
					fichaImovelUrbanoVo.setEnderecoVo((new CadastroBe(conn)).buscarEndereco(endereco));
					ConstrucaoVo construcao = fichaImovelUrbanoVo.getConstrucaoVo();
					if (construcao.isExiste())
					{
						construcao.setParametroConsulta(construcao);
						fichaImovelUrbanoVo.setConstrucaoVo((new ConstrucaoBe(conn)).consultarConstrucao(construcao));
					}
				}
			}
		}
		return fichaImovelUrbanoVo;
	}

	/**
	 * Método para listar Ficha Imï¿½vel Urbano - Benfeitoria
	 * 
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @return FichaImovelUrbanoVo
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoVo listarBenfeitoriaFichaImovelUrbano(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo = new FichaImovelUrbanoBenfeitoriaVo();
		fichaImovelUrbanoBenfeitoriaVo.getFichaImovelUrbanoVo().setCodigo(fichaImovelUrbanoVo.getCodigo());
		fichaImovelUrbanoBenfeitoriaVo = new FichaImovelUrbanoBenfeitoriaVo(fichaImovelUrbanoBenfeitoriaVo);
		(new FichaImovelUrbanoBenfeitoriaBe(conn)).listarFichaImovelUrbanoBenfeitoria(fichaImovelUrbanoBenfeitoriaVo);
		if(fichaImovelUrbanoVo.isConsultaCompleta())
		{
			if(Validador.isCollectionValida(fichaImovelUrbanoBenfeitoriaVo.getCollVO()))
			{
				for(Iterator it = fichaImovelUrbanoBenfeitoriaVo.getCollVO().iterator(); it.hasNext(); )
				{
					FichaImovelUrbanoBenfeitoriaVo benfeitoriaAtual = (FichaImovelUrbanoBenfeitoriaVo) it.next()	;
					BenfeitoriaVo benfeitoriaParametroVo = new BenfeitoriaVo(benfeitoriaAtual.getBenfeitoriaVo().getCodigo());
					BenfeitoriaVo benfeitoriaConsultaVo = new BenfeitoriaVo(benfeitoriaParametroVo);
					benfeitoriaConsultaVo.setConsultaCompleta(true);
					(new BenfeitoriaBe(conn)).consultarBenfeitoria(benfeitoriaConsultaVo);
					benfeitoriaAtual.setBenfeitoriaVo(benfeitoriaConsultaVo);
				}
			}
		}
		fichaImovelUrbanoVo.setFichaImovelUrbanoBenfeitoriaVo(fichaImovelUrbanoBenfeitoriaVo);
		return fichaImovelUrbanoVo;
	}

	/**
	 * Método responsável por consultar a localidade do CEP informado para ficha de imóvel urbano.
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void pesquisaCepImovelUrbano(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  IntegracaoException, RegistroNaoPodeSerUtilizadoException, ConsultaException, ParametroObrigatorioException, 
         SQLException
   {
		Validador.validaObjeto(fichaImovelUrbanoVo);
		if(fichaImovelUrbanoVo.getEnderecoVo().getCep().getCodgCep().toString().length() == 8)
		{
			fichaImovelUrbanoVo.getEnderecoVo().setCep(new CadastroBe(conn).buscarCep(fichaImovelUrbanoVo.getEnderecoVo().getCep()));
        
         IPTUVo iptuVoConsulta = new IPTUVo();
         iptuVoConsulta.setMunicipio( new CadastroBe(conn).obterMunicipioPorCep( fichaImovelUrbanoVo.getEnderecoVo().getCep() ) );
            
         fichaImovelUrbanoVo.setIptuVo( new IPTUBe(conn).obterIptuPorCodigoOuMunicipio(iptuVoConsulta) );
         fichaImovelUrbanoVo.setValorPercentualTransmitido(100);

      }
		else
		{
		   throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CEP_VALIDO);            
		}
	}

   public void adicionarNumeroInscricaoImovelUrbanoIntegrado(FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException, IOException, Exception
   {
      IPTUPrefeituraVo iptuConsulta = new IPTUPrefeituraVo();
      iptuConsulta.setNumrInscricaoImovel( fichaImovelUrbanoVo.getIptuPrefeituraVo().getNumrInscricaoImovel() );
      iptuConsulta.setStatIPTUprefeitura(new DomnAtivoInativo(DomnAtivoInativo.ATIVO) );
      iptuConsulta = new IPTUPrefeituraVo(iptuConsulta);
      new IPTUPrefeituraBe().consultarIPTUPrefeitura(iptuConsulta);
      fichaImovelUrbanoVo.setIptuPrefeituraVo(iptuConsulta);
      fichaImovelUrbanoVo.setValorVenalIptu( iptuConsulta.getValrVenal() );
      fichaImovelUrbanoVo.setValorPercentualTransmitido(100);
      
      if(!Validador.isStringValida(iptuConsulta.getNumrInscricaoImovel()))
      {
         throw new ParametroObrigatorioException("Número de inscrição do imóvel não encontrado.");
      }
   }

	/**
	 * Método responsável por validar a regra de negócio que envolve o CEP para uma ficha de imóvel urbano.
	 * @param fichaImovelUrbanoVo
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validaCepFichaImovelUrbano(FichaImovelUrbanoVo fichaImovelUrbanoVo) throws RegistroNaoPodeSerUtilizadoException, 
			  ObjetoObrigatorioException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
	   if (Validador.isNumericoValido(fichaImovelUrbanoVo.getEnderecoVo().getCep().getCodgCep()))
	   {
	      if(!fichaImovelUrbanoVo.getEnderecoVo().getCep().getLocalidade().getUf().getSiglUf().equals(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO))
	      {
				fichaImovelUrbanoVo.getEnderecoVo().setCep(new CepIntegracaoVo());
	         throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_CEP_NAO_PERMITIDO);
	      }
	   }
	   else
	   {
	      throw   new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_CEP_NAO_CADASTRADO);
	   }	
	}

	/**
	 * Método responsável por validar os dados necessários à exclusão de uma ficha de imóvel urbano.
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validarExcluirFichaImovelUrbano(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		if(!Validador.isNumericoValido(fichaImovelUrbanoVo.getCodigo()))
		{
			throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_FICHA_IMOVEL_URBANO_NECESSARIO_PARA_EXCLUIR);
		}
	}

	/**
	 * Método responsável por realizar a exclusão de uma ficha de imóvel urbano.
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void excluirFichaImovelUrbanoAlterarBemTributavel(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException, LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		try
		{
			try
			{
				synchronized(FichaImovelUrbanoVo.class)
				{
					validarExcluirFichaImovelUrbano(fichaImovelUrbanoVo);
					
					if(Validador.isCollectionValida(fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().getCollVO()))
					{
						FichaImovelUrbanoBenfeitoriaBe fichaImovelUrbanoBenfeitoriaBe = new FichaImovelUrbanoBenfeitoriaBe(conn);
						for(Iterator it = fichaImovelUrbanoVo.getFichaImovelUrbanoBenfeitoriaVo().getCollVO().iterator(); it.hasNext(); )
						{
							FichaImovelUrbanoBenfeitoriaVo fichaBenfeitoria = (FichaImovelUrbanoBenfeitoriaVo) it.next();
							fichaBenfeitoria.setLogSefazVo(fichaImovelUrbanoVo.getLogSefazVo());
						   fichaImovelUrbanoBenfeitoriaBe.excluirFichaImovelUrbanoBenfeitoriaExcluirFichaImovelUrbano(fichaBenfeitoria);
						}
					}					
					excluir(fichaImovelUrbanoVo);
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
