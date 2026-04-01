/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: TelaFuncionalidadeBe.java
 * Revisão: Marlo Einchenberg Motta / Fev2008 Wendell Pereira de Farias / Leandro Dorileo
 * Data revisão: 20/11/2007 / 20/03/2008
 * $Id: TelaFuncionalidadeBe.java,v 1.3 2008/06/27 14:43:08 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telafuncionalidade;

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

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;
import java.util.Iterator;


/**
 * Componente de negócio de Tela funcionalidade
 * @author Thiago de Castilho Pacheco
 */
public class TelaFuncionalidadeBe extends AbstractBe
{
	/**
	 * Construtor da classe.
	 * Este construtor não abre uma conexão com o banco de dados, mas utiliza a conexão recebida como parâmetro para realizar suas validações.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaFuncionalidadeBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Construtor padrão da classe.
	 * @throws SQLException
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaFuncionalidadeBe() throws SQLException
	{
		super();
	}

	/**
	 * Método utilizado por listar as Tela/Campo por funcionalidade.
	 * @param telaFuncionalidadeVo Tela/Funcionalidade (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaFuncionalidadeVo listarTelaFuncionalidade(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		(new TelaFuncionalidadeQDao(conn)).listTelaFuncionalidade(telaFuncionalidadeVo);
		return telaFuncionalidadeVo;
	}

	/**
	 * Método utilizado por listar as Tela/Campo por funcionalidade apresentada para o usuario.
	 * @param telaFuncionalidadeVo Tela/Funcionalidade (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 * @implemented by Wendell Pereira de Farias
	 */
	public TelaFuncionalidadeVo listarTelaFuncionalidadeUsuario(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		(new TelaFuncionalidadeQDao(conn)).listTelaFuncionalidadeUsuario(telaFuncionalidadeVo);
		return telaFuncionalidadeVo;
	}

	/**
	 * Método utilizado para Consultar Tela Funcionalidade.
	 * @param telaFuncionalidadeVo Tela/Funcionalidade (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaFuncionalidadeVo consultarTelaFuncionalidade(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		(new TelaFuncionalidadeQDao(conn)).findTelaFuncionalidade(telaFuncionalidadeVo);
		return telaFuncionalidadeVo;
	}

	/**
	 * Método utilizado para Incluir Tela Funcionalidade
	 * @param telaFuncionalidadeVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Thiago de Castilho Pacheco
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirTelaFuncionalidade(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		telaFuncionalidadeVo.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		try
		{
			try
			{
				synchronized (TelaFuncionalidadeVo.class)
				{
					incluir(telaFuncionalidadeVo);
					commit();
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
	 * Método utilizado para Alterar Tela Funcionalidade
	 * @param telaFuncionalidadeVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws ConexaoException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Thiago de Castilho Pacheco
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarTelaFuncionalidade(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  LogException, ConexaoException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		try
		{
			try
			{
				synchronized (TelaFuncionalidadeVo.class)
				{
					telaFuncionalidadeVo.setDataAtualizacaoBD(new Date());
					alterar(telaFuncionalidadeVo);
					commit();
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
	 * @param telaFuncionalidadeVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(telaFuncionalidadeVo);
	}

	/**
	 * @param telaFuncionalidadeVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(telaFuncionalidadeVo);
	}	

	/**
	 * Método responsável por atualizar o objeto telaFuncionalidadeVo com os atributos selecionados pelo usuário.
	 * @param telaFuncionalidadeVo Tela/Funcionalidade (Value Object).
	 * @implemented by Marlo Eichenberg Motta
	 */
	public static TelaFuncionalidadeVo atualizarTelaFuncionalidadeVo(final TelaFuncionalidadeVo telaFuncionalidadeVo)
	{
		//código responsável por identificar a TelaFuncionalidade selecionada pelo usuário no CollVO.
		Iterator iteratorTelafuncionalidade = telaFuncionalidadeVo.getCollVO().iterator();
		while (iteratorTelafuncionalidade.hasNext())
		{
			TelaFuncionalidadeVo telaFuncionalidadeTempVo = (TelaFuncionalidadeVo) iteratorTelafuncionalidade.next();
			if ((telaFuncionalidadeVo.getCodigo() == telaFuncionalidadeTempVo.getFuncionalidadeVo().getCodigo()) && 
						(telaFuncionalidadeVo.getTelaAjudaVo().getCodigo() == 
						telaFuncionalidadeTempVo.getTelaAjudaVo().getCodigo()))
			{
				telaFuncionalidadeVo.setCodigo(telaFuncionalidadeTempVo.getFuncionalidadeVo().getCodigo()); //Foi inserido para atualizar o codigo do objeto principal
				telaFuncionalidadeVo.setInformacaoTituloTelaFuncionalidade(telaFuncionalidadeTempVo.getInformacaoTituloTelaFuncionalidade());
				telaFuncionalidadeVo.setDescricaoTelaFuncionalidade(telaFuncionalidadeTempVo.getDescricaoTelaFuncionalidade());
				telaFuncionalidadeVo.setStatusTelaFuncionalidade(telaFuncionalidadeTempVo.getStatusTelaFuncionalidade()); //Foi inserido para atualizar o status
			}
		}
		return telaFuncionalidadeVo;
	}
}
