/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConstrucaoBe.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 12/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.construcao;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;


/**
 * Classe responsável por implementar a regra de negócio da entidade "Construção".
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
public class ConstrucaoBe extends AbstractBe
{

	/**
	 * Construtor da classe.
	 * @param conexao Conexão que será utilizada durante a manutenção de dados
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public ConstrucaoBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Construtor padrão da classe.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public ConstrucaoBe() throws SQLException
	{
		super();
	}

	/**
	 * Consulta informações sobre um Construção no banco de dados.
	 * @param construcaoVo ConstrucaoVo que contém os parametros para a consulta.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @return ConstrucaoVo ConstrucaoVo com os valores encontrados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public ConstrucaoVo consultarConstrucao(final ConstrucaoVo construcaoVo) throws ConsultaException, 
			  ObjetoObrigatorioException, ParametroObrigatorioException
	{
		Validador.validaObjeto(construcaoVo);
		(new ConstrucaoQDao(conn)).findConstrucao(construcaoVo);
		return construcaoVo;
	}

	/**
	 * Inclui informações sobre uma Construção no banco de dados.
	 * @param construcaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 * @implemented by Rogério Sanches
	 * 
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirConstrucao(final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  RegistroExistenteException, ParametroObrigatorioException, ConsultaException, 
			  ConexaoException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(construcaoVo);
		try
		{
			try
			{
				synchronized (ConstrucaoVo.class)
				{
					validaParametrosIncluirConstrucao(construcaoVo);
					ConstrucaoVo construcaoConsultaVo = new ConstrucaoVo();
					construcaoConsultaVo.setDescricaoConstrucao(construcaoVo.getDescricaoConstrucao());
					construcaoConsultaVo = new ConstrucaoVo(construcaoConsultaVo);
					consultarConstrucao(construcaoConsultaVo);
					if (construcaoConsultaVo.isExiste() && Validador.isNumericoValido(construcaoConsultaVo.getCodigo()))
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_CONSTRUCAO_PARAMETRO_DESCRICAO_EXISTENTE);
					}
					else
					{
						construcaoVo.setStatusConstrucao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
						construcaoVo.setDataAtualizacao(new Date());
						incluir(construcaoVo);
						commit();
						construcaoVo.setMensagem(MensagemSucesso.INCLUIR);
					}
				}
			}
			catch (RegistroExistenteException ree)
			{
				conn.rollback();
				throw ree;
			}
			catch (ConsultaException ce)
			{
				conn.rollback();
				throw ce;
			}
			catch (ParametroObrigatorioException poe)
			{
				conn.rollback();
				throw poe;
			}
			catch (ObjetoObrigatorioException ooe)
			{
				conn.rollback();
				throw ooe;
			}
			catch(LogException e)
			{
				conn.rollback();
				throw e;
			}
			catch(AnotacaoException e)
			{
				conn.rollback();
				throw e;				
			}
			catch(PersistenciaException e)
			{
				conn.rollback();
				throw e;
			}
		}
		catch (SQLException se)
		{
			se.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * @param construcaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(construcaoVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(construcaoVo);
	}

	/**
	 * Método usado para validar se todos os atributos necessários para a inclusão de uma Construção, foram passados.
	 * @param construcaoVo ConstrucaoVo que contém as informações que são checadas.
	 * <b>É obrigatório informar a Descrição da Construcao.</b>
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosIncluirConstrucao(final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(construcaoVo);
		if (!Validador.isStringValida(construcaoVo.getDescricaoConstrucao()))
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CONSTRUCAO_PARAMETRO_DESCRICAO);
		if (!Validador.isDominioNumericoValido(construcaoVo.getPermiteFichaRural()) && 
				 !Validador.isDominioNumericoValido(construcaoVo.getPermiteFichaUrbano()))
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CONSTRUCAO_UTILIZACAO);
	}

	/**
	 * Método usado para validar se todos os atributos necessários para a alteração de uma Construção, foram passados.
	 * @param construcaoVo ConstrucaoVo que contém as informações que são checadas.
	 * <b>É obrigatório informar a Descrição da Construção.</b>
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosAlterarConstrucao(final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(construcaoVo);
		if (!Validador.isStringValida(construcaoVo.getDescricaoConstrucao()))
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CONSTRUCAO_PARAMETRO_DESCRICAO);
		if (!Validador.isDominioNumericoValido(construcaoVo.getPermiteFichaRural()) && 
				 !Validador.isDominioNumericoValido(construcaoVo.getPermiteFichaUrbano()))
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CONSTRUCAO_UTILIZACAO);
	}

	/**
	 * Método que certifica se é permitido alterar determinado ConstrucaoVo.
	 * @param construcaoVo ConstrucaoVo com os dados digitados pelo Usuário
	 * @param construcaoConsultaVo ConstrucaoVo encontrado no Banco de Dados
	 * @return true
	 * @implemented by Daniel Balieiro
	 */
	private boolean permiteAlterar(final ConstrucaoVo construcaoVo, final ConstrucaoVo construcaoConsultaVo)
	{
		if (construcaoConsultaVo.isExiste() && Validador.isNumericoValido(construcaoConsultaVo.getCodigo()))
			return construcaoVo.getCodigo() == construcaoConsultaVo.getCodigo();
		return true;
	}

	/**
	 * Altera informações sobre uma Construção no banco de dados.
	 * @param construcaoVo ConstrucaoVo que deverá ser alterada  no banco de dados.
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarConstrucao(final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  RegistroExistenteException, ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(construcaoVo);
		validaParametrosAlterarConstrucao(construcaoVo);
		try
		{
			try
			{
				synchronized (ConstrucaoVo.class)
				{
					ConstrucaoVo construcaoConsultaVo = new ConstrucaoVo();
					construcaoConsultaVo.setDescricaoConstrucao(construcaoVo.getDescricaoConstrucao());
					construcaoConsultaVo = new ConstrucaoVo(construcaoConsultaVo);
					consultarConstrucao(construcaoConsultaVo);
					if (permiteAlterar(construcaoVo, construcaoConsultaVo))
					{
						construcaoVo.setDataAtualizacao(new Date());
						alterar(construcaoVo);
						commit();
						construcaoVo.setMensagem(MensagemSucesso.ALTERAR);
					}
					else
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_CONSTRUCAO_PARAMETRO_DESCRICAO_EXISTENTE);
					}
				}
			}
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch (RegistroExistenteException e)
			{
				conn.rollback();
				throw e;
			}
			catch(ParametroObrigatorioException e)
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
			catch(AnotacaoException e)
			{
				conn.rollback();
				throw e;
			}
			catch(PersistenciaException e)
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
	 * @param construcaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(construcaoVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(construcaoVo);
	}

	/**
	 * Lista informações sobre um ou mais Construções no banco de dados.
	 * @param construcaoVo ConstrucaoVo com os parametros para efetuar a busca.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return ConstrucaoVo  ConstrucaoVo com os valores encontrados.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public ConstrucaoVo listarConstrucao(final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(construcaoVo);
		(new ConstrucaoQDao(conn)).listConstrucao(construcaoVo);
		Validador.validaObjeto(construcaoVo);
		Validador.isCollectionValida(construcaoVo.getCollVO());
		return construcaoVo;
	}

	/**
	 * @param construcaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void reativarConstrucaoVo(final ConstrucaoVo construcaoVo) throws ObjetoObrigatorioException, 
			  RegistroExistenteException, ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(construcaoVo);
		construcaoVo.setStatusConstrucao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		alterarConstrucao(construcaoVo);
	}
}
