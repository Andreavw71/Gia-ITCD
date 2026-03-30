/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CulturaBe.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 01/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.cultura;

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
 * Classe de negócio da entidade Cultura
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class CulturaBe extends AbstractBe
{
	/**
	 * Construtor padrão da classe.
	 * Este construtor abre uma conexão com o banco de dados do ITC para realizar suas validações.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public CulturaBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor da classe.
	 * Este construtor não abre uma conexão com o banco de dados, mas utiliza a conexão recebida como parâmetro para realizar suas validações.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public CulturaBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método utilizado para validar se o objeto do tipo CulturaVo possui todos os parâmetros obrigatórios.
	 * @param culturaVo Cultura (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void validaParametrosIncluirCultura(final CulturaVo culturaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(culturaVo);
		if (!Validador.isStringValida(culturaVo.getDescricaoCultura()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CULTURA_PARAMETRO_DESCRICAO);
		}
	}

	/**
	 * Método utilizado para validar se o objeto do tipo CulturaVo possui todos os parâmetros obrigatórios.
	 * @param culturaVo Cultura (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void validaParametrosAlterarCultura(final CulturaVo culturaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(culturaVo);
		validaParametrosIncluirCultura(culturaVo);
		if (!Validador.isDominioNumericoValido(culturaVo.getStatusCultura()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CULTURA_PARAMETRO_STATUS);
		}
	}

	/**
	 * Inclui informações sobre uma Cultura no banco de dados.
	 * Este método é o responsável por tratar das regras de negócio da inclusão de uma Cultura.
	 * @param culturaVo Cultura (Value Object).
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirCultura(final CulturaVo culturaVo) throws ObjetoObrigatorioException,  
			  RegistroExistenteException, ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(culturaVo);
		try
		{
			try
			{
				synchronized (CulturaVo.class)
				{
					validaParametrosIncluirCultura(culturaVo);
					CulturaVo culturaConsultaVo = new CulturaVo();
					culturaConsultaVo.setDescricaoCultura(culturaVo.getDescricaoCultura());
					culturaConsultaVo = new CulturaVo(culturaConsultaVo);
					culturaConsultaVo = consultarCultura(culturaConsultaVo);
					//Verifica se existe alguma descrição já gravada em banco para não duplicar informações
					if (culturaConsultaVo.isExiste())
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_CULTURA_INCLUIR_DESCRICAO);
					}
					else
					{
						culturaVo.setStatusCultura(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
						culturaVo.setDataAtualizacao(new Date());
						incluir(culturaVo);
						commit();
						culturaVo.setMensagem(MensagemSucesso.INCLUIR);
					}
				}
			}
			catch (RegistroExistenteException e)
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
	 * @param culturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final CulturaVo culturaVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(culturaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(culturaVo);
	}

	/**
	 * Consulta informações sobre uma Cultura no banco de dados.
	 * A consulta pode ser parametrizada através de um dos seguintes atributos da classe CulturaVo:
	 * - CulturaVo.getCodigo();
	 * - CulturaVo.getDescricao();
	 * @param culturaVo Cultura (Value Object).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	public CulturaVo consultarCultura(final CulturaVo culturaVo) throws ConsultaException, ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(culturaVo);
		(new CulturaQDao(conn)).findCultura(culturaVo);
		return culturaVo;
	}

	/**
	 * Método que certifica se é permitido alterar determinado CulturaVo.
	 * @param culturaVo Cultura (Value Object).
	 * @param culturaConsultaVo Cultura (Value Object).
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private boolean permiteAlterar(final CulturaVo culturaVo, final CulturaVo culturaConsultaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(culturaVo);
		Validador.validaObjeto(culturaConsultaVo);
		if (culturaConsultaVo.isExiste() && Validador.isNumericoValido(culturaConsultaVo.getCodigo()))
		{
			return culturaVo.getCodigo() == culturaConsultaVo.getCodigo();
		}
		return true;
	}

	/**
	 * Altera informações sobre uma Cultura no banco de dados.
	 * Este método é o responsável por tratar das regras de negócio da alteração de uma Cultura.
	 * @param culturaVo Cultura (Value Object).
	 * @throws RegistroExistenteException
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void alterarCultura(final CulturaVo culturaVo) throws RegistroExistenteException, ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, AnotacaoException, 
			  PersistenciaException
	{
		Validador.validaObjeto(culturaVo);
		validaParametrosAlterarCultura(culturaVo);
		try
		{
			try
			{
				synchronized (CulturaVo.class)
				{
					//Verifica se existe alguma descrição já gravada em banco para não duplicar informações
					CulturaVo culturaConsultaVo = new CulturaVo();
					culturaConsultaVo.setDescricaoCultura(culturaVo.getDescricaoCultura());
					culturaConsultaVo = new CulturaVo(culturaConsultaVo);
					consultarCultura(culturaConsultaVo);
					if (permiteAlterar(culturaVo, culturaConsultaVo))
					{
						culturaVo.setDataAtualizacao(new Date());
						alterar(culturaVo);
						commit();
						culturaVo.setMensagem(MensagemSucesso.ALTERAR);
					}
					else
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_CULTURA_INCLUIR_DESCRICAO);
					}
				}
			}
			catch (ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch (ConsultaException e)
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
	 * @param culturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final CulturaVo culturaVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(culturaVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(culturaVo);
	}

	/**
	 * Lista informações sobre um ou mais Culturas no banco de dados.
	 * @param culturaVo Cultura (Value Object).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	public CulturaVo listarCultura(final CulturaVo culturaVo) throws ConsultaException, ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(culturaVo);
		(new CulturaQDao(conn)).listCultura(culturaVo);
		Validador.validaObjeto(culturaVo);
		Validador.isCollectionValida(culturaVo.getCollVO());
		return culturaVo;
	}

	/**
	 * Reativa uma Cultura
	 * @param culturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void reativarCultura(final CulturaVo culturaVo) throws ObjetoObrigatorioException, RegistroExistenteException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, AnotacaoException, 
			  PersistenciaException
	{
		Validador.validaObjeto(culturaVo);
		culturaVo.setStatusCultura(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		alterarCultura(culturaVo);
	}
}
