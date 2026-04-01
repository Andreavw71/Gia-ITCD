/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: RebanhoBe.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 03/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.rebanho;

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
 * Classe responsável por implementar a regra de negócio da entidade "Rebanho".
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
public class RebanhoBe extends AbstractBe
{
	/**
	 * Construtor da classe.
	 * @param conexao Conexão que será utilizada durante a manutenção de dados.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public RebanhoBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Construtor padrão da classe.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public RebanhoBe() throws SQLException
	{
		super();
	}

	/**
	 * Inclui informações sobre um Rebanho no banco de dados.
	 * @param rebanhoVo RebanhoVo que deverá ser inserida no banco de dados.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws RegistroExistenteException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados mas o registro já existe.
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirRebanho(final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, 
			  RegistroExistenteException, ParametroObrigatorioException, ConsultaException, 
			  ConexaoException, LogException, 
			  PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(rebanhoVo);
		try
		{
			try
			{
				synchronized (rebanhoVo)
				{
					validaParametrosIncluirRebanho(rebanhoVo);
					RebanhoVo rebanhoConsultaVo = new RebanhoVo();
					rebanhoConsultaVo.setDescricaoRebanho(rebanhoVo.getDescricaoRebanho());
					rebanhoConsultaVo = new RebanhoVo(rebanhoConsultaVo);
					consultarRebanho(rebanhoConsultaVo);
					if (rebanhoConsultaVo.isExiste() && Validador.isNumericoValido(rebanhoConsultaVo.getCodigo()))
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_REBANHO_INCLUIR_DESCRICAO);
					}
					else
					{
						rebanhoVo.setStatusRebanho(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
						incluir(rebanhoVo);
						commit();
						rebanhoVo.setMensagem(MensagemSucesso.INCLUIR);
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
		catch (SQLException se)
		{
			se.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método responsável por realizar a inclusão de um registro de rebanho, e também gerar o log.
	 * @param rebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(rebanhoVo);
	   rebanhoVo.setDataAtualizacao( new Date() );
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(rebanhoVo);
	}

	/**
	 * Consulta informações sobre um Rebanho no banco de dados.
	 * @param rebanhoVo RebanhoVo que contém os parametros para a consulta.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @return RebanhoVo RebanhoVo com os valores encontrados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public RebanhoVo consultarRebanho(final RebanhoVo rebanhoVo) throws ConsultaException, ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(rebanhoVo);
		(new RebanhoQDao(conn)).findRebanho(rebanhoVo);
		return rebanhoVo;
	}

	/**
	 * Método usado para validar se todos os atributos necessários para a inclusão de um Rebanho, foram passados.
	 * @param rebanhoVo RebanhoVo que contém as informações que são checadas.
	 * <b>É obrigatório informar a Descrição do Rebanho.</b>
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosIncluirRebanho(final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(rebanhoVo);
		if (!Validador.isStringValida(rebanhoVo.getDescricaoRebanho()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_REBANHO_PARAMETRO_DESCRICAO);
		}
	}

	/**
	 * Método usado para validar se todos os atributos necessários para a alteração de um Rebanho, foram passados.
	 * @param rebanhoVo RebanhoVo que contém as informações que são checadas.
	 * <b>É obrigatório informar a Descrição do Rebanho.</b>
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosAlterarRebanho(final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(rebanhoVo);
		validaParametrosIncluirRebanho(rebanhoVo);
	}

	/**
	 * Método que certifica se é permitido alterar determinado RebanhVo.
	 * @param rebanhoVo Rebanho (Value Object).
	 * @param rebanhoConsultaVo RebanhoVo (Value Object) encontrado no Banco de Dados.
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private boolean permiteAlterar(final RebanhoVo rebanhoVo, final RebanhoVo rebanhoConsultaVo)
	{
		if (rebanhoConsultaVo.isExiste() && Validador.isNumericoValido(rebanhoConsultaVo.getCodigo()))
		{
			return rebanhoVo.getCodigo() == rebanhoConsultaVo.getCodigo();
		}
		return true;
	}

	/**
	 * Altera informações sobre uma Rebanho no banco de dados.
	 * @param rebanhoVo RebanhoVo que deverá ser alterada  no banco de dados.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws RegistroExistenteException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados mas o registro já existe.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarRebanho(final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, RegistroExistenteException, ConexaoException, LogException, 
			  PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(rebanhoVo);
		synchronized (rebanhoVo)
		{
			try
			{
			   validaParametrosAlterarRebanho(rebanhoVo);
			   RebanhoVo rebanhoConsultaVo = new RebanhoVo();
			   rebanhoConsultaVo.setDescricaoRebanho(rebanhoVo.getDescricaoRebanho());
			   rebanhoConsultaVo = new RebanhoVo(rebanhoConsultaVo);
			   consultarRebanho(rebanhoConsultaVo);
				if (!permiteAlterar(rebanhoVo, rebanhoConsultaVo))
				{
				   throw new RegistroExistenteException(MensagemErro.VALIDAR_REBANHO_INCLUIR_DESCRICAO);
				}
			   try
			   {
			      rebanhoVo.setDataAtualizacao(new Date());
			      alterar(rebanhoVo);
			      commit();
			      rebanhoVo.setMensagem(MensagemSucesso.ALTERAR);
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
	}

	/**
	 * Método responsável por realizar a alteração de um objeto rebanho.
	 * @param rebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(rebanhoVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(rebanhoVo);
	}

	/**
	 * Lista informações sobre um ou mais Rebanhos no banco de dados.
	 * @param rebanhoVo  RebanhoVo com os parametros para efetuar a busca.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return RebanhoVo  RebanhoVo com os valores encontrados.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public RebanhoVo listarRebanho(final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, ConsultaException
	{
		Validador.validaObjeto(rebanhoVo);
		RebanhoVo parametro = new RebanhoVo();
		RebanhoVo consulta = new RebanhoVo();
		if(Validador.isNumericoValido(rebanhoVo.getCodigo()))
		{
			parametro.setCodigo(rebanhoVo.getCodigo());	
		}
		else if(Validador.isStringValida(rebanhoVo.getDescricaoRebanho()))
		{
			parametro.setDescricaoRebanho(rebanhoVo.getDescricaoRebanho());	
		}		
		consulta.setParametroConsulta(parametro);
		new RebanhoQDao(conn).listRebanho(consulta);
		rebanhoVo.setCollVO(consulta.getCollVO());
		return rebanhoVo;
	}

	/**
	 * Método responsável por alterar o status de um rebanho para ativo.
	 * @param rebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws RegistroExistenteException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void reativarRebanho(final RebanhoVo rebanhoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, RegistroExistenteException, ConexaoException, LogException, 
			  PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(rebanhoVo);
		rebanhoVo.setStatusRebanho(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		alterarRebanho(rebanhoVo);
	}
}
