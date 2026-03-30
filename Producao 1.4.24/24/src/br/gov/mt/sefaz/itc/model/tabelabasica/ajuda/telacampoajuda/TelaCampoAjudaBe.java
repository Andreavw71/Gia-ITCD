 /**
  * Ábaco Tecnologia de Informação - LTDA
  * Arquivo: TelaCampoAjudaBe.java
  * Revisão: Leandro Dorileo
  * Data revisão: 20/03/2008
  * $Id: TelaCampoAjudaBe.java,v 1.2 2008/06/04 18:48:07 lucas.nascimento Exp $
  */
 package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.log.FormAcessoLogITC;
import br.gov.mt.sefaz.itc.util.log.FuncionalidadeLog;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;

import sefaz.mt.util.AlteraException;
import sefaz.mt.util.IncluiException;


/**
 * Classe de negócios da entidade TelaCampoAjuda (Business Entity).
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.2 $
 */
public class TelaCampoAjudaBe extends AbstractBe
{
	/**
	 * Construtor da classe.
	 * Este construtor não abre uma conexão com o banco de dados, mas utiliza a conexão recebida como parâmetro para realizar suas validações.
	 * @param conexao
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaCampoAjudaBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Construtor padrão da classe.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaCampoAjudaBe() throws SQLException
	{
		super();
	}

	/**
	 * Método utilizado para Listar Tela Campo Ajuda.
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaCampoAjudaVo listarTelaCampoAjuda(final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(telaCampoAjudaVo);
		(new TelaCampoAjudaQDao(conn)).listTelaCampoAjuda(telaCampoAjudaVo);
		return telaCampoAjudaVo;
	}

	/**
	 * Método utilizado para Consultar Tela Campo Ajuda.
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaCampoAjudaVo consultarTelaCampoAjuda(final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(telaCampoAjudaVo);
		(new TelaCampoAjudaQDao(conn)).findTelaCampoAjuda(telaCampoAjudaVo);
		return telaCampoAjudaVo;
	}

	/**
	 * Método utilizado para Incluir Tela Campo Ajuda.
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public synchronized void incluirTelaCampoAjuda(final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  IncluiException, SQLException, ConexaoException
	{
		Validador.validaObjeto(telaCampoAjudaVo);
		try
		{
			try
			{
				synchronized (TelaCampoAjudaVo.class)
				{
					//codigo do LOG
					new FormAcessoLogITC(FuncionalidadeLog.INCLUIR_TELA_FUNCIONALIDADE_PROPRIA_TRANSACAO, telaCampoAjudaVo);
					//fim do código do LOG
					new TelaCampoAjudaDao(conn).insertTelaCampoAjuda(telaCampoAjudaVo);
					commit();
				}
			}
			catch (ObjetoObrigatorioException e)
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
	 * Método utilizado para Alterar Tela Campo Ajuda.
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public synchronized void alterarTelaCampoAjuda(final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(telaCampoAjudaVo);
		try
		{
			try
			{
				synchronized (TelaCampoAjudaVo.class)
				{
					telaCampoAjudaVo.setDataAtualizacaoBD(new Date());
					alterar(telaCampoAjudaVo);
					commit();
				}
			}
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			//Este bloco foi alterado para commit para permitir a "EXCLUSÃO DO ITEN".
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
	 * @param telaCampoAjudaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(telaCampoAjudaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(telaCampoAjudaVo);
	}

	/**
	 * Método utilizado para Inativar Tela Campo Ajuda.
	 * @param telaCampoAjudaVo Tela Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public synchronized void inativarTelaCampoAjuda(final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  AlteraException, SQLException, ConexaoException
	{
		Validador.validaObjeto(telaCampoAjudaVo);
		telaCampoAjudaVo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.INATIVO));
		try
		{
			try
			{
				synchronized (TelaCampoAjudaVo.class)
				{
					new TelaCampoAjudaDao(conn).inactiveTelaCampoAjuda(telaCampoAjudaVo);
					commit();
				}
			}
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch (AlteraException e)
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
