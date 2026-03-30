/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BenfeitoriaBe.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 15/10/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria;

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
 * Classe responsável por implementar a regra de negócio da entidade "Benfeitoria".
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
public class BenfeitoriaBe extends AbstractBe
{

	/**
	 * Construtor da classe.
	 * Este construtor não abre uma conexão com o banco de dados, mas utiliza a conexão recebida como parâmetro para realizar suas validações.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public BenfeitoriaBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Construtor padrão da classe.
	 * Este construtor abre uma conexão com o banco de dados do ITCD para realizar suas validações.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public BenfeitoriaBe() throws SQLException
	{
		super();
	}

	/**
	 * Consulta informações sobre um Benfeitoria no banco de dados.
	 * @param benfeitoriaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public BenfeitoriaVo consultarBenfeitoria(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(benfeitoriaVo);
		(new BenfeitoriaQDao(conn)).findBenfeitoria(benfeitoriaVo);
		return benfeitoriaVo;
	}

	/**
	 * Inclui informações sobre uma Benfeitoria no banco de dados.
	 * Este método é o responsável por tratar das regras de negócio da inclusão de uma Benfeitoria.
	 * @param benfeitoriaVo (Value Object).
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirBenfeitoria(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  RegistroExistenteException, ConsultaException, ParametroObrigatorioException, ConexaoException, LogException, 
			  PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(benfeitoriaVo);
		try
		{
			try
			{
				synchronized (BenfeitoriaVo.class)
				{
					validaParametrosIncluirBenfeitoria(benfeitoriaVo);
					BenfeitoriaVo benfeitoriaConsultaVo = new BenfeitoriaVo();
					benfeitoriaConsultaVo.setDescricaoBenfeitoria(benfeitoriaVo.getDescricaoBenfeitoria());
					benfeitoriaConsultaVo = new BenfeitoriaVo(benfeitoriaConsultaVo);
					consultarBenfeitoria(benfeitoriaConsultaVo);
					if (benfeitoriaConsultaVo.isExiste())
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_BENFEITORIA_INCLUIR_ALTERAR_DESCRICAO);
					}
					else
					{
						benfeitoriaVo.setStatusBenfeitoria(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
						benfeitoriaVo.setDataAtualizacao(new Date());
						incluir(benfeitoriaVo);
						commit();
						benfeitoriaVo.setMensagem(MensagemSucesso.INCLUIR);
					}
				}
			}
			catch (ConsultaException e)
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
			catch(RegistroExistenteException e)
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
	 * @param benfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(benfeitoriaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(benfeitoriaVo);
	}

	/**
	 * Método usado para validar se todos os atributos necessários para a inclusão de uma Benfeitoria são válidos.
	 * @param benfeitoriaVo (Value Object).
	 * <b>É obrigatório informar a Descrição da Benfeitoria.</b>
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosIncluirBenfeitoria(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(benfeitoriaVo);
		if (!Validador.isStringValida(benfeitoriaVo.getDescricaoBenfeitoria()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BENFEITORIA_PARAMETRO_DESCRICAO);
		}
	}

	/**
	 * Método usado para validar se todos os atributos necessários para a alteração de uma Benfeitoria são válidos.
	 * @param benfeitoriaVo (Value Object).
	 * <b>É obrigatório informar a Descrição da Benfeitoria.</b>
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosAlterarBenfeitoria(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(benfeitoriaVo);
		validaParametrosIncluirBenfeitoria(benfeitoriaVo);
	}

	/**
	 * Método que certifica se é permitido alterar uma Benfeitoria.
	 * @param benfeitoriaVo (Value Object).
	 * @param benfeitoriaConsultaVo (Value Object).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private boolean permiteAlterar(final BenfeitoriaVo benfeitoriaVo, final BenfeitoriaVo benfeitoriaConsultaVo)
	{
		if (benfeitoriaConsultaVo.isExiste())
		{
			return benfeitoriaVo.getCodigo() == benfeitoriaConsultaVo.getCodigo();
		}
		return true;
	}

	/**
	 * Altera informações sobre uma Benfeitoria no banco de dados.
	 * @param benfeitoriaVo (Value Object).
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws RegistroExistenteException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarBenfeitoria(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, RegistroExistenteException, 
			  ConexaoException, LogException, 
			  PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(benfeitoriaVo);
		try
		{
			try
			{
				synchronized (BenfeitoriaVo.class)
				{
					validaParametrosAlterarBenfeitoria(benfeitoriaVo);
					BenfeitoriaVo benfeitoriaConsultaVo = new BenfeitoriaVo();
					benfeitoriaConsultaVo.setDescricaoBenfeitoria(benfeitoriaVo.getDescricaoBenfeitoria());
					benfeitoriaConsultaVo = new BenfeitoriaVo(benfeitoriaConsultaVo);
					consultarBenfeitoria(benfeitoriaConsultaVo);
					if (permiteAlterar(benfeitoriaVo, benfeitoriaConsultaVo))
					{
						benfeitoriaVo.setDataAtualizacao(new Date());
						alterar(benfeitoriaVo);
						commit();
						benfeitoriaVo.setMensagem(MensagemSucesso.ALTERAR);
					}
					else
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_BENFEITORIA_INCLUIR_ALTERAR_DESCRICAO);
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
	 * @param benfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(benfeitoriaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(benfeitoriaVo);
	}

	/**
	 * Lista informações sobre um ou mais Benfeitorias no banco de dados.
	 * @param benfeitoriaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return 
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public BenfeitoriaVo listarBenfeitoria(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(benfeitoriaVo);
		(new BenfeitoriaQDao(conn)).listBenfeitoria(benfeitoriaVo);
		Validador.validaObjeto(benfeitoriaVo);
		return benfeitoriaVo;
	}

	/**
	 * Reativa uma Benfeitoria
	 * @param benfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws RegistroExistenteException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void reativarBenfeitoria(final BenfeitoriaVo benfeitoriaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, RegistroExistenteException, 
			  ConexaoException, LogException, 
			  PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(benfeitoriaVo);
		benfeitoriaVo.setStatusBenfeitoria(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		alterarBenfeitoria(benfeitoriaVo);
	}
}
