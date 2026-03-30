package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural;

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
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria.FichaImovelRuralBenfeitoriaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria.FichaImovelRuralBenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao.FichaImovelRuralConstrucaoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao.FichaImovelRuralConstrucaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura.FichaImovelRuralCulturaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura.FichaImovelRuralCulturaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho.FichaImovelRuralRebanhoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho.FichaImovelRuralRebanhoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural.BaseCalculoImovelRuralBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural.BaseCalculoImovelRuralVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.construcao.ConstrucaoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio.CriterioMunicipioBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio.CriterioMunicipioVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoVo;
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
 * Classe responsável por implementar a regra de negócio da entidade "Ficha Imóvel Rural"
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.6 $
 */
public class FichaImovelRuralBe extends AbstractBe
{

	/**
	 * Construtor Padrão
	 * 
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método para validar dados ao incluir ou alterar uma Ficha Imóvel Rural
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Daniel Balieiro
	 */
	private void validaIncluirAlterarFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, IntegracaoException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		if (!Validador.isStringValida(fichaImovelRuralVo.getDescricaoDenominacao()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_DENOMINACAO_VALIDA);
		}
		if (!Validador.isStringValida(fichaImovelRuralVo.getEnderecoVo().getLogradouro()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_LOGRADOURO_VALIDO);
		}
		if (!Validador.isStringValida(fichaImovelRuralVo.getEnderecoVo().getPontoReferencia()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_PONTO_REFERENCIA_VALIDO);
		}
		if (Validador.isObjetoValido(fichaImovelRuralVo.getEnderecoVo().getCep()) && 
				 Validador.isNumericoValido(fichaImovelRuralVo.getEnderecoVo().getCep().getCodgCep()))
		{
			CadastroBe cadastroBe = new CadastroBe(conn);
			CepIntegracaoVo cepIntegracaoVo = cadastroBe.buscarCep(fichaImovelRuralVo.getEnderecoVo().getCep());
			if (fichaImovelRuralVo.getEnderecoVo().getCep().getCodgCep().toString().length() != 8)
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CEP_VALIDO);
			}
			if (!cepIntegracaoVo.isExiste())
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CEP_NAO_CADASTRADO);
			}
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CEP);
		}
		if (!Validador.isNumericoValido(fichaImovelRuralVo.getQuantidadeDistancia()))
		{
			//throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_QUANTIDADE_DISTANCIA); 000006307/2017-05
		}
		if (!Validador.isNumericoValido(fichaImovelRuralVo.getAreaTotal()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_AREA_TOTAL);
		}
		if (fichaImovelRuralVo.getSituacaoPastagem().getValorCorrente() == DomnSimNao.SIM)
		{
			if (!Validador.isNumericoValido(fichaImovelRuralVo.getAreaPastagem()))
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_AREA_PASTAGENS);
			}
			if (!Validador.isNumericoValido(fichaImovelRuralVo.getValorPastagem()))
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_VALOR_PASTAGENS);
			}
		}
		if (fichaImovelRuralVo.getSituacaoAcessaoNatural().getValorCorrente() == DomnSimNao.SIM && 
				 !Validador.isNumericoValido(fichaImovelRuralVo.getValorAcessaoNatural()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_VALOR_ACESSAO_NATURAL);
		}
		if (!Validador.isNumericoValido(fichaImovelRuralVo.getValorMercadoImovel()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_VALOR_IMOVEL);
		}
	}

	/**
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(fichaImovelRuralVo);
	}

	/**
	 * Método para remover uma Ficha Imóvel Rural
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void removerFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		try
		{
			try
			{
				synchronized (FichaImovelRuralVo.class)
				{
					FichaImovelRuralConstrucaoBe fichaImovelRuralConstrucaoBe = new FichaImovelRuralConstrucaoBe(conn);
					for (Iterator iteConstrucao = fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getCollVO().iterator(); iteConstrucao.hasNext(); )
					{
						FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo = (FichaImovelRuralConstrucaoVo) iteConstrucao.next();
						fichaImovelRuralConstrucaoVo.getFichaImovelRuralVo().setCodigo(fichaImovelRuralVo.getCodigo());
					   fichaImovelRuralConstrucaoVo.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
						fichaImovelRuralConstrucaoBe.removerFichaImovelRuralConstrucao(fichaImovelRuralConstrucaoVo);
					}
					FichaImovelRuralBenfeitoriaBe fichaImovelRuralBenfeitoriaBe = new FichaImovelRuralBenfeitoriaBe(conn);
					for (Iterator iteBenfeitoria = fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo().getCollVO().iterator(); iteBenfeitoria.hasNext(); )
					{
						FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo = (FichaImovelRuralBenfeitoriaVo) iteBenfeitoria.next();
						fichaImovelRuralBenfeitoriaVo.getFichaImovelRuralVo().setCodigo(fichaImovelRuralVo.getCodigo());
					   fichaImovelRuralBenfeitoriaVo.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
						fichaImovelRuralBenfeitoriaBe.removerFichaImovelRuralBenfeitoria(fichaImovelRuralBenfeitoriaVo);
					}
					FichaImovelRuralCulturaBe fichaImovelRuralCulturaBe = new FichaImovelRuralCulturaBe(conn);
					for (Iterator iteCultura = fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCollVO().iterator(); iteCultura.hasNext(); )
					{
						FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo = (FichaImovelRuralCulturaVo) iteCultura.next();
						fichaImovelRuralCulturaVo.getFichaImovelRuralVo().setCodigo(fichaImovelRuralVo.getCodigo());
					   fichaImovelRuralCulturaVo.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
						fichaImovelRuralCulturaBe.removerFichaImovelRuralCultura(fichaImovelRuralCulturaVo);
					}
					FichaImovelRuralRebanhoBe fichaImovelRuralRebanhoBe = new FichaImovelRuralRebanhoBe(conn);
					for (Iterator iteRebanho = fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getCollVO().iterator(); iteRebanho.hasNext(); )
					{
						FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo = (FichaImovelRuralRebanhoVo) iteRebanho.next();
						fichaImovelRuralRebanhoVo.getFichaImovelRuralVo().setCodigo(fichaImovelRuralVo.getCodigo());
					   fichaImovelRuralRebanhoVo.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
						fichaImovelRuralRebanhoBe.removerFichaImovelRuralRebanho(fichaImovelRuralRebanhoVo);
					}
					excluir(fichaImovelRuralVo);
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
	 * Método para alterar uma Ficha Imóvel Rural
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws RegistroExistenteException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException, ParametroObrigatorioException, 
			  IntegracaoException, RegistroExistenteException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		validaIncluirAlterarFichaImovelRural(fichaImovelRuralVo);
		removerFichaImovelRural(fichaImovelRuralVo);
		incluirFichaImovelRural(fichaImovelRuralVo);
	}

	/**
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(fichaImovelRuralVo);
	}

	/**
	 * Método responsável por alterar uma ficha de imóvel rural.
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws ConexaoException
	 * @throws RegistroExistenteException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarFichaImovelRuralAlterarBemTributavel(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, IntegracaoException, DadoNecessarioInexistenteException, ConexaoException, 
			  RegistroExistenteException, LogException, PersistenciaException, AnotacaoException
	{
	   Validador.validaObjeto(fichaImovelRuralVo);
		try
		{
			try
			{
			   synchronized(FichaImovelRuralVo.class)
			   {
			      validaIncluirAlterarFichaImovelRural(fichaImovelRuralVo);
			      if(Validador.isNumericoValido(fichaImovelRuralVo.getCodigo()))
					{
						alterar(fichaImovelRuralVo);
						if(Validador.isCollectionValida(fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getCollVO()))
						{
							FichaImovelRuralConstrucaoBe fichaImovelRuralConstrucaoBe = new FichaImovelRuralConstrucaoBe(conn);
							for(Iterator iteConstrucao = fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getCollVO().iterator(); iteConstrucao.hasNext(); )
							{
								FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo = (FichaImovelRuralConstrucaoVo) iteConstrucao.next();
							   fichaImovelRuralConstrucaoVo.setFichaImovelRuralVo(fichaImovelRuralVo);
							   fichaImovelRuralConstrucaoVo.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
							  
							   if(Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getCodigo()))
							   {
							      fichaImovelRuralConstrucaoBe.alterarFichaImovelRuralConstrucaoAlterarFichaImovelRural(fichaImovelRuralConstrucaoVo);
							   }else
							   {
							      fichaImovelRuralConstrucaoBe.incluirFichaImovelRuralConstrucao(fichaImovelRuralConstrucaoVo);
							   }
							}
						}
						if(Validador.isCollectionValida(fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo().getCollVO()))
						{
							FichaImovelRuralBenfeitoriaBe fichaImovelRuralBenfeitoriaBe = new FichaImovelRuralBenfeitoriaBe(conn);
							for(Iterator iteBenfeitoria = fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo().getCollVO().iterator(); iteBenfeitoria.hasNext(); )
							{
							   FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo = (FichaImovelRuralBenfeitoriaVo) iteBenfeitoria.next();
							   fichaImovelRuralBenfeitoriaVo.setFichaImovelRuralVo(fichaImovelRuralVo);
							   fichaImovelRuralBenfeitoriaVo.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
							   //fichaImovelRuralBenfeitoriaBe.alterarFichaImovelRuralBenfeitoriaAlterarFichaImovelRural(fichaImovelRuralBenfeitoriaVo);
							   if(Validador.isNumericoValido(fichaImovelRuralBenfeitoriaVo.getCodigo()))
							   {
							      fichaImovelRuralBenfeitoriaBe.alterarFichaImovelRuralBenfeitoriaAlterarFichaImovelRural(fichaImovelRuralBenfeitoriaVo);
							   }else
							   {
							      fichaImovelRuralBenfeitoriaBe.incluirFichaImovelRuralBenfeitoria(fichaImovelRuralBenfeitoriaVo);
							   }
							}
						}
						if(Validador.isCollectionValida(fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCollVO()))
						{
							FichaImovelRuralCulturaBe fichaImovelRuralCulturaBe = new FichaImovelRuralCulturaBe(conn);						
						   for (Iterator iteCultura = fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCollVO().iterator(); iteCultura.hasNext(); )
						   {
						      FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo = (FichaImovelRuralCulturaVo) iteCultura.next();
						      fichaImovelRuralCulturaVo.setFichaImovelRuralVo(fichaImovelRuralVo);
						      fichaImovelRuralCulturaVo.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
						      //fichaImovelRuralCulturaBe.alterarFichaImovelRuralCulturaAlterarFichaImovelRural(fichaImovelRuralCulturaVo);
						      if(Validador.isNumericoValido(fichaImovelRuralCulturaVo.getCodigo()))
						      {
						         fichaImovelRuralCulturaBe.alterarFichaImovelRuralCulturaAlterarFichaImovelRural(fichaImovelRuralCulturaVo);
						      }else
						      {
						         fichaImovelRuralCulturaBe.incluirFichaImovelRuralCultura(fichaImovelRuralCulturaVo);
						      }
						   }							
						}
						if(Validador.isCollectionValida(fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getCollVO()))
						{
							FichaImovelRuralRebanhoBe fichaImovelRuralRebanhoBe = new FichaImovelRuralRebanhoBe(conn);							
						   for (Iterator iteRebanho = fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getCollVO().iterator(); iteRebanho.hasNext(); )
						   {
						      FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo = (FichaImovelRuralRebanhoVo) iteRebanho.next();
						      fichaImovelRuralRebanhoVo.setFichaImovelRuralVo(fichaImovelRuralVo);
						      fichaImovelRuralRebanhoVo.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
                        if(Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getCodigo()))
                        {
                            fichaImovelRuralRebanhoBe.alterarFichaImovelRuralRebanhoAlterarFichaImovelRural(fichaImovelRuralRebanhoVo);
                        }else
                        {
                           fichaImovelRuralRebanhoBe.incluirFichaImovelRuralRebanho(fichaImovelRuralRebanhoVo);
                        }
						     
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
			catch(ConexaoException e)
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
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}	
	}

	/**
	 * Método responsável por exlcuir uma ficha de imóvel rural.
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void excluirFichaImovelRuralAlterarBemTributavel(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException, LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		try
		{
			try
			{
				synchronized(FichaImovelRuralVo.class)
				{
					validarExcluirFichaImovelRural(fichaImovelRuralVo);
					if(Validador.isCollectionValida(fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo().getCollVO()))
					{
						FichaImovelRuralBenfeitoriaBe fichaImovelRuralBenfeitoriaBe = new FichaImovelRuralBenfeitoriaBe(conn);
						for(Iterator it = fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo().getCollVO().iterator(); it.hasNext(); )
						{
							FichaImovelRuralBenfeitoriaVo fichaBenfeitoria = (FichaImovelRuralBenfeitoriaVo) it.next();
						   fichaBenfeitoria.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
						   fichaImovelRuralBenfeitoriaBe.exluirFichaImovelRuralBenfeitoriaExcluirFichaImovelRural(fichaBenfeitoria);
						}
					}
				   if(Validador.isCollectionValida(fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getCollVO()))
				   {
				      FichaImovelRuralConstrucaoBe fichaImovelRuralConstrucaoBe = new FichaImovelRuralConstrucaoBe(conn);
				      for(Iterator it = fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getCollVO().iterator(); it.hasNext(); )
				      {
				         FichaImovelRuralConstrucaoVo fichaConstrucao = (FichaImovelRuralConstrucaoVo) it.next();
				         fichaConstrucao.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
				         fichaImovelRuralConstrucaoBe.exluirFichaImovelRuralConstrucaoExcluirFichaImovelRural(fichaConstrucao);
				      }
				   }
				   if(Validador.isCollectionValida(fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCollVO()))
				   {
				      FichaImovelRuralCulturaBe fichaImovelRuralCulturaBe = new FichaImovelRuralCulturaBe(conn);
				      for(Iterator it = fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCollVO().iterator(); it.hasNext(); )
				      {
				         FichaImovelRuralCulturaVo fichaCultura = (FichaImovelRuralCulturaVo) it.next();
				         fichaCultura.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
				         fichaImovelRuralCulturaBe.exluirFichaImovelRuralCulturaExcluirFichaImovelRural(fichaCultura);
				      }
				   }
				   if(Validador.isCollectionValida(fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getCollVO()))
				   {
				      FichaImovelRuralRebanhoBe fichaImovelRuralRebanhoBe = new FichaImovelRuralRebanhoBe(conn);
				      for(Iterator it = fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getCollVO().iterator(); it.hasNext(); )
				      {
				         FichaImovelRuralRebanhoVo fichaRebanho = (FichaImovelRuralRebanhoVo) it.next();
				         fichaRebanho.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
				         fichaImovelRuralRebanhoBe.exluirFichaImovelRuralRebanhoExcluirFichaImovelRural(fichaRebanho);
				      }
				   }
					excluir(fichaImovelRuralVo);
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
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método responsável por validar os dados necessários à exclusão de uma ficha de imóvel rural.
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validarExcluirFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		if(!Validador.isNumericoValido(fichaImovelRuralVo.getCodigo()))
		{
			throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_FICHA_IMOVEL_RURAL_NECESSARIO_PARA_EXCLUIR);
		}
	}

	public synchronized void incluirFichaImovelRuralIncluirBemTributavel(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException, ParametroObrigatorioException, 
			  IntegracaoException, RegistroExistenteException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		incluirFichaImovelRural(fichaImovelRuralVo);
	}
	
	
	public synchronized void incluirFichaImovelRuralAlterarBemTributavel(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException, ParametroObrigatorioException, 
			  IntegracaoException, RegistroExistenteException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		incluirFichaImovelRural(fichaImovelRuralVo);
	}

	/**
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(fichaImovelRuralVo);
	}
	/**
	 * Método para incluir uma Ficha Imóvel Rural
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws RegistroExistenteException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException, ParametroObrigatorioException, 
			  IntegracaoException, RegistroExistenteException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		try
		{
			try
			{
				synchronized (FichaImovelRuralVo.class)
				{
					validaIncluirAlterarFichaImovelRural(fichaImovelRuralVo);					
					fichaImovelRuralVo.setEnderecoVo((new CadastroBe(conn)).incluirEndereco(fichaImovelRuralVo.getEnderecoVo()));
					incluir(fichaImovelRuralVo);
					FichaImovelRuralConstrucaoBe fichaImovelRuralConstrucaoBe = new FichaImovelRuralConstrucaoBe(conn);
					for (Iterator iteConstrucao = fichaImovelRuralVo.getFichaImovelRuralConstrucaoVo().getCollVO().iterator(); iteConstrucao.hasNext(); )
					{
						FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo = (FichaImovelRuralConstrucaoVo) iteConstrucao.next();
						fichaImovelRuralConstrucaoVo.setFichaImovelRuralVo(fichaImovelRuralVo);
						fichaImovelRuralConstrucaoVo.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
						fichaImovelRuralConstrucaoBe.incluirFichaImovelRuralConstrucao(fichaImovelRuralConstrucaoVo);
					}
					FichaImovelRuralBenfeitoriaBe fichaImovelRuralBenfeitoriaBe = new FichaImovelRuralBenfeitoriaBe(conn);
					for (Iterator iteBenfeitoria = fichaImovelRuralVo.getFichaImovelRuralBenfeitoriaVo().getCollVO().iterator(); iteBenfeitoria.hasNext(); )
					{
						FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo = (FichaImovelRuralBenfeitoriaVo) iteBenfeitoria.next();
						fichaImovelRuralBenfeitoriaVo.setFichaImovelRuralVo(fichaImovelRuralVo);
						fichaImovelRuralBenfeitoriaVo.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
						fichaImovelRuralBenfeitoriaBe.incluirFichaImovelRuralBenfeitoria(fichaImovelRuralBenfeitoriaVo);
					}
					FichaImovelRuralCulturaBe fichaImovelRuralCulturaBe = new FichaImovelRuralCulturaBe(conn);
					for (Iterator iteCultura = fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCollVO().iterator(); iteCultura.hasNext(); )
					{
						FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo = (FichaImovelRuralCulturaVo) iteCultura.next();
						fichaImovelRuralCulturaVo.setFichaImovelRuralVo(fichaImovelRuralVo);
						fichaImovelRuralCulturaVo.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
						fichaImovelRuralCulturaBe.incluirFichaImovelRuralCultura(fichaImovelRuralCulturaVo);
					}
					FichaImovelRuralRebanhoBe fichaImovelRuralRebanhoBe = new FichaImovelRuralRebanhoBe(conn);
					for (Iterator iteRebanho = fichaImovelRuralVo.getFichaImovelRuralRebanhoVo().getCollVO().iterator(); iteRebanho.hasNext(); )
					{
						FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo = (FichaImovelRuralRebanhoVo) iteRebanho.next();
						fichaImovelRuralRebanhoVo.setFichaImovelRuralVo(fichaImovelRuralVo);
						fichaImovelRuralRebanhoVo.setLogSefazVo(fichaImovelRuralVo.getLogSefazVo());
						fichaImovelRuralRebanhoBe.incluirFichaImovelRuralRebanho(fichaImovelRuralRebanhoVo);
					}
				}
			}			
			catch (ConexaoException e)
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
			catch (PersistenciaException e)
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
	 * Método pra consultar uma Ficha Imóvel Rural
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @return FichaImovelRuralVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralVo consultarFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, ParametroObrigatorioException, SQLException, 
             IOException
   {
		Validador.validaObjeto(fichaImovelRuralVo);
		(new FichaImovelRuralQDao(conn)).findFichaImovelRural(fichaImovelRuralVo);
		if (fichaImovelRuralVo.isExiste() && fichaImovelRuralVo.isConsultaCompleta())
		{
		   consultarBaseDeCalculo(fichaImovelRuralVo);
		   consultarCriterioMunicipio(fichaImovelRuralVo);
			listarConstrucaoFichaImovelRural(fichaImovelRuralVo);
			listarBenfeitoriaFichaImovelRural(fichaImovelRuralVo);
			listarCulturaFichaImovelRural(fichaImovelRuralVo);
			listarRebanhoFichaImovelRural(fichaImovelRuralVo);
			BemTributavelVo bemTributavelVo = new BemTributavelVo();
			bemTributavelVo.setCodigo(fichaImovelRuralVo.getBemTributavelVo().getCodigo());
			bemTributavelVo = new BemTributavelVo(bemTributavelVo);
			(new BemTributavelBe(conn)).consultarBemTributavel(bemTributavelVo);
			fichaImovelRuralVo.setBemTributavelVo(bemTributavelVo);
			EnderecoIntegracaoVo endereco = fichaImovelRuralVo.getEnderecoVo();
			if (endereco.isExiste())
			{
				endereco.setParametroConsulta(endereco);
				fichaImovelRuralVo.setEnderecoVo((new CadastroBe(conn)).buscarEndereco(endereco));
			}
		}
		return fichaImovelRuralVo;
	}

   private void consultarBaseDeCalculo(final FichaImovelRuralVo fichaImovelRuralVo)
      throws ConsultaException, ObjetoObrigatorioException, 
             ParametroObrigatorioException
   {
      if(Validador.isNumericoValido(fichaImovelRuralVo.getCriterioMunicipioVo().getCodigo()))
      {
         ExibirLOG.exibirLogSimplificado("Consultando codigo de base de calculo...");
         BaseCalculoImovelRuralVo vo = new BaseCalculoImovelRuralVo();
         vo.setCodigo(fichaImovelRuralVo.getBaseCalculoImovelRuralVo().getCodigo());
         fichaImovelRuralVo.setBaseCalculoImovelRuralVo(new BaseCalculoImovelRuralBe(conn).consultarBaseCalculoImovelRuralVo(new BaseCalculoImovelRuralVo(vo)));
      }else
      {
         ExibirLOG.exibirLogSimplificado("Imovel - "+fichaImovelRuralVo.getCodigo() +" | sem codigo de base de calculo...");
      }
   }

   /**
    * Consulta do criterio municipio para a ficha informada
    * @param fichaImovelRuralVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    */
   private void consultarCriterioMunicipio(final FichaImovelRuralVo fichaImovelRuralVo)
      throws ConsultaException, ObjetoObrigatorioException, 
             ParametroObrigatorioException
   {
      if(Validador.isNumericoValido(fichaImovelRuralVo.getCriterioMunicipioVo().getCodigo()))
      {
         ExibirLOG.exibirLogSimplificado("Consultando codigo de criterio municipio...");
         CriterioMunicipioVo vo = new CriterioMunicipioVo();
         vo.setCodigo(fichaImovelRuralVo.getCriterioMunicipioVo().getCodigo());
         fichaImovelRuralVo.setCriterioMunicipioVo(new CriterioMunicipioBe(conn).consultarCriterioMunicipio(new CriterioMunicipioVo(vo)));
      }else
      {
         ExibirLOG.exibirLogSimplificado("Imovel - "+fichaImovelRuralVo.getCodigo() +" | sem codigo de criterio municipio...");
      }
   }

	/**
	 * Método para listar Ficha Imóvel Rural
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @return FichaImovelRuralVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralVo listarFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, ParametroObrigatorioException, SQLException, 
             IOException
   {
		Validador.validaObjeto(fichaImovelRuralVo);
		(new FichaImovelRuralQDao(conn)).listFichaImovelRural(fichaImovelRuralVo);
		if (fichaImovelRuralVo.isConsultaCompleta())
		{
			for (Iterator iteRural = fichaImovelRuralVo.getCollVO().iterator(); iteRural.hasNext(); )
			{
				FichaImovelRuralVo fichaImovelRuralAtualVo = (FichaImovelRuralVo) iteRural.next();
				if (fichaImovelRuralAtualVo.isConsultaCompleta())
				{
					listarConstrucaoFichaImovelRural(fichaImovelRuralAtualVo);
					listarBenfeitoriaFichaImovelRural(fichaImovelRuralAtualVo);
					listarCulturaFichaImovelRural(fichaImovelRuralAtualVo);
					listarRebanhoFichaImovelRural(fichaImovelRuralAtualVo);
					BemTributavelVo bemTributavelVo = new BemTributavelVo();
					bemTributavelVo.setCodigo(fichaImovelRuralAtualVo.getBemTributavelVo().getCodigo());
					bemTributavelVo = new BemTributavelVo(bemTributavelVo);
					(new BemTributavelBe(conn)).listarItemBemTributavel(bemTributavelVo);
					EnderecoIntegracaoVo endereco = fichaImovelRuralAtualVo.getEnderecoVo();
					endereco.setParametroConsulta(endereco);
					fichaImovelRuralVo.setEnderecoVo((new CadastroBe(conn)).buscarEndereco(endereco));
				}
			}
		}
		return fichaImovelRuralVo;
	}

	/**
	 * Método para listar Ficha Imóvel Rural Cultura
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @return FichaImovelRuralVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralVo listarCulturaFichaImovelRural(FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo = new FichaImovelRuralCulturaVo();
		fichaImovelRuralCulturaVo.getFichaImovelRuralVo().setCodigo(fichaImovelRuralVo.getCodigo());
		fichaImovelRuralCulturaVo = new FichaImovelRuralCulturaVo(fichaImovelRuralCulturaVo);
		fichaImovelRuralCulturaVo.setConsultaCompleta(fichaImovelRuralVo.isConsultaCompleta());
		(new FichaImovelRuralCulturaBe(conn)).listarFichaImovelRuralCultura(fichaImovelRuralCulturaVo);
		if(fichaImovelRuralVo.isConsultaCompleta())
		{
			if(Validador.isCollectionValida(fichaImovelRuralCulturaVo.getCollVO()))
			{
				for(Iterator it = fichaImovelRuralCulturaVo.getCollVO().iterator(); it.hasNext(); )
				{
				   FichaImovelRuralCulturaVo culturaAtual = (FichaImovelRuralCulturaVo) it.next();
					CulturaVo culturaConsulta = new CulturaVo();
					CulturaVo culturaParametro = new CulturaVo(culturaAtual.getCulturaVo().getCodigo());
					culturaConsulta.setConsultaCompleta(true);
					culturaConsulta.setParametroConsulta(culturaParametro);
					(new CulturaBe(conn)).consultarCultura(culturaConsulta);
					culturaAtual.setCulturaVo(culturaConsulta);
				}
			}
		}
		fichaImovelRuralVo.setFichaImovelRuralCulturaVo(fichaImovelRuralCulturaVo);
		return fichaImovelRuralVo;
	}

	/**
	 * Método para listar Ficha Imóvel Rural Rebanho
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @return FichaImovelRuralVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralVo listarRebanhoFichaImovelRural(FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo = new FichaImovelRuralRebanhoVo();
		fichaImovelRuralRebanhoVo.getFichaImovelRuralVo().setCodigo(fichaImovelRuralVo.getCodigo());
		fichaImovelRuralRebanhoVo = new FichaImovelRuralRebanhoVo(fichaImovelRuralRebanhoVo);
		fichaImovelRuralRebanhoVo.setConsultaCompleta(fichaImovelRuralVo.isConsultaCompleta());
		(new FichaImovelRuralRebanhoBe(conn)).listarFichaImovelRuralRebanho(fichaImovelRuralRebanhoVo);
		if(fichaImovelRuralVo.isConsultaCompleta())
		{
			if(Validador.isCollectionValida(fichaImovelRuralRebanhoVo.getCollVO()))
			{
				for(Iterator it = fichaImovelRuralRebanhoVo.getCollVO().iterator(); it.hasNext(); )
				{
				   FichaImovelRuralRebanhoVo rebanhoAtual = (FichaImovelRuralRebanhoVo) it.next();
					RebanhoVo rebanhoConsulta = new RebanhoVo();
					RebanhoVo rebanhoParametro = new RebanhoVo(rebanhoAtual.getRebanhoVo().getCodigo());
					rebanhoConsulta.setConsultaCompleta(true);
					rebanhoConsulta.setParametroConsulta(rebanhoParametro);
					(new RebanhoBe(conn)).consultarRebanho(rebanhoConsulta);
					rebanhoAtual.setRebanhoVo(rebanhoConsulta);
				}
			}
		}
		fichaImovelRuralVo.setFichaImovelRuralRebanhoVo(fichaImovelRuralRebanhoVo);
		return fichaImovelRuralVo;
	}

	/**
	 * Método para listar Ficha Imóvel Rural Construção
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @return FichaImovelRuralVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralVo listarConstrucaoFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, ParametroObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo = new FichaImovelRuralConstrucaoVo();
		fichaImovelRuralConstrucaoVo.getFichaImovelRuralVo().setCodigo(fichaImovelRuralVo.getCodigo());
		fichaImovelRuralConstrucaoVo = new FichaImovelRuralConstrucaoVo(fichaImovelRuralConstrucaoVo);
		fichaImovelRuralConstrucaoVo.setConsultaCompleta(fichaImovelRuralVo.isConsultaCompleta());
		(new FichaImovelRuralConstrucaoBe(conn)).listarFichaImovelRuralConstrucao(fichaImovelRuralConstrucaoVo);
	   if(fichaImovelRuralVo.isConsultaCompleta())
	   {
	      if(Validador.isCollectionValida(fichaImovelRuralConstrucaoVo.getCollVO()))
	      {
	         for(Iterator it = fichaImovelRuralConstrucaoVo.getCollVO().iterator(); it.hasNext(); )
	         {
	            FichaImovelRuralConstrucaoVo construcaoAtual = (FichaImovelRuralConstrucaoVo) it.next();
	            ConstrucaoVo construcaoConsulta = new ConstrucaoVo();
	            ConstrucaoVo construcaoParametro = new ConstrucaoVo(construcaoAtual.getConstrucaoVo().getCodigo());
	            construcaoConsulta.setConsultaCompleta(true);
	            construcaoConsulta.setParametroConsulta(construcaoParametro);
	            (new ConstrucaoBe(conn)).consultarConstrucao(construcaoConsulta);
	            construcaoAtual.setConstrucaoVo(construcaoConsulta);
	         }
	      }
	   }		
		fichaImovelRuralVo.setFichaImovelRuralConstrucaoVo(fichaImovelRuralConstrucaoVo);
		return fichaImovelRuralVo;
	}

	/**
	 * Método para Listar Ficha Imóvel Rural Benfeitoria
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @return FichaImovelRuralVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralVo listarBenfeitoriaFichaImovelRural(FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo = new FichaImovelRuralBenfeitoriaVo();
		fichaImovelRuralBenfeitoriaVo.getFichaImovelRuralVo().setCodigo(fichaImovelRuralVo.getCodigo());
		fichaImovelRuralBenfeitoriaVo = new FichaImovelRuralBenfeitoriaVo(fichaImovelRuralBenfeitoriaVo);
		fichaImovelRuralBenfeitoriaVo.setConsultaCompleta(fichaImovelRuralVo.isConsultaCompleta());
		(new FichaImovelRuralBenfeitoriaBe(conn)).listarFichaImovelRuralBenfeitoria(fichaImovelRuralBenfeitoriaVo);		
	   if(fichaImovelRuralVo.isConsultaCompleta())
	   {
	      if(Validador.isCollectionValida(fichaImovelRuralBenfeitoriaVo.getCollVO()))
	      {
	         for(Iterator it = fichaImovelRuralBenfeitoriaVo.getCollVO().iterator(); it.hasNext(); )
	         {
	            FichaImovelRuralBenfeitoriaVo benfeitoriaAtual = (FichaImovelRuralBenfeitoriaVo) it.next();
	            BenfeitoriaVo benfeitoriaConsulta = new BenfeitoriaVo();
	            BenfeitoriaVo benfeitoriaParametro = new BenfeitoriaVo(benfeitoriaAtual.getBenfeitoriaVo().getCodigo());
	            benfeitoriaConsulta.setConsultaCompleta(true);
	            benfeitoriaConsulta.setParametroConsulta(benfeitoriaParametro);
	            (new BenfeitoriaBe(conn)).consultarBenfeitoria(benfeitoriaConsulta);
	            benfeitoriaAtual.setBenfeitoriaVo(benfeitoriaConsulta);
	         }
	      }
	   }     		
		fichaImovelRuralVo.setFichaImovelRuralBenfeitoriaVo(fichaImovelRuralBenfeitoriaVo);
		return fichaImovelRuralVo;
	}

	/**
	 * Método responsável por consultar a localidade do CEP informado para ficha de imóvel rural.
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void pesquisaCepImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException, IntegracaoException
	{
	   Validador.validaObjeto(fichaImovelRuralVo);
	   if(fichaImovelRuralVo.getEnderecoVo().getCep().getCodgCep().toString().length() == 8)
	   {
	      fichaImovelRuralVo.getEnderecoVo().setCep(new CadastroBe(conn).buscarCep(fichaImovelRuralVo.getEnderecoVo().getCep()));
	      validaCepFichaImovelRural(fichaImovelRuralVo);
	   }
	   else
	   {
	      throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CEP_VALIDO);            
	   }	
	}
	
	/**
	 * Método responsável por validar a regra de negócio que envolve o CEP para uma ficha de imóvel rural.
	 * @param fichaImovelRuralVo
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validaCepFichaImovelRural(FichaImovelRuralVo fichaImovelRuralVo) throws RegistroNaoPodeSerUtilizadoException, 
			  ObjetoObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		if (Validador.isNumericoValido(fichaImovelRuralVo.getEnderecoVo().getCep().getCodgCep()))
		{
			if(!fichaImovelRuralVo.getEnderecoVo().getCep().getLocalidade().getUf().getSiglUf().equals(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO))
			{
				fichaImovelRuralVo.getEnderecoVo().setCep(new CepIntegracaoVo());
				throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CEP_NAO_PERMITIDO);
			}
		}
		else
		{
			throw   new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CEP_NAO_CADASTRADO);
		}  
	}	
}
