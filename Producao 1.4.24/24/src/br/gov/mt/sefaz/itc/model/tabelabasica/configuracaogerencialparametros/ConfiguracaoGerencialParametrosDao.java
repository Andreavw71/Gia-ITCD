/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConfiguracaoGerencialParametrosDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 06/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposConfiguracaoGerencialParametros;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.SefazDataHora;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


/**
 * Classe utilizada para realizar manutenção no banco de dados.
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class ConfiguracaoGerencialParametrosDao extends AbstractDao implements TabelasITC, SequencesITC, CamposConfiguracaoGerencialParametros
{
	/**
	 * Construtor que recebe a conexão com o Banco de Dados.
	 * @param conexao
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_CONFIGURACAO_GERENCIAL_PARAMETROS);
	}

	/**
	 * Retorna os Campos da Tabela de Configuração Gerencial Parametros (ITCTB04_CONFIG_GERENCIAL).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{
		return new String[] { CAMPO_CODIGO_CONFIGURACAO_GERENCIAL_PARAMETROS, CAMPO_VALOR_ITEM, CAMPO_DESCRICAO_ITEM, CAMPO_TIPO_ITEM, CAMPO_DATA_ATUALIZACAO_BD, CAMPO_STATUS_CADASTRADO };
	}

	/**
	 * Método utilizado para inserir uma Configuração Gerencial Parametros no Banco de Dados.
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public void insertConfiguracaoGerencialParametros(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			configuracaoGerencialParametrosVo.setCodigo(sequence.next(SEQUENCE_CONFIGURACAO_GERENCIAL_PARAMETROS));
			GeradorLogSefazMT.gerar(configuracaoGerencialParametrosVo, DomnOperacao.OPERACAO_INSERT, configuracaoGerencialParametrosVo.getNumeroParticao(), configuracaoGerencialParametrosVo.getCodigoTransacao(), configuracaoGerencialParametrosVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertConfiguracaoGerencialParametros(ps, configuracaoGerencialParametrosVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_CONFIGURACAO_GERENCIAL_PARAMETROS);
		}
		//	   catch (LogSefazException e)
		//	   {
		//	      e.printStackTrace();
		//	      throw new IncluiException(MensagemErro.INCLUIR_CONFIGURACAO_GERENCIAL_PARAMETROS);
		//	   }
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_CONFIGURACAO_GERENCIAL_PARAMETROS);
		}
		finally
		{
			if (ps != null)
			{
				try
				{
					close(ps);
					ps = null;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Método responsável por adicionar os parâmetros válidos na instrução SQL (java.sql.PrepareStatement).
	 * @param ps (java.sql.PreparedStatement).
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertConfiguracaoGerencialParametros(final PreparedStatement ps, final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		int contador = 0;
		try
		{
			// CODIGO
			if (Validador.isNumericoValido(configuracaoGerencialParametrosVo.getCodigo()))
			{
				ps.setLong(++contador, configuracaoGerencialParametrosVo.getCodigo());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// VALOR ITEM
			if (Validador.isStringValida(configuracaoGerencialParametrosVo.getValorItem()))
			{
				ps.setString(++contador, configuracaoGerencialParametrosVo.getValorItem());
			}
			else
			{
				ps.setNull(++contador, Types.VARCHAR);
			}
			// DESCRICAO
			if (Validador.isStringValida(configuracaoGerencialParametrosVo.getDescricaoItem()))
			{
				ps.setString(++contador, configuracaoGerencialParametrosVo.getDescricaoItem());
			}
			else
			{
				ps.setNull(++contador, Types.VARCHAR);
			}
			// TIPO ITEM
			if (Validador.isDominioNumericoValido(configuracaoGerencialParametrosVo.getTipoItem()))
			{
				ps.setInt(++contador, configuracaoGerencialParametrosVo.getTipoItem().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// DATA ATUALIZACAO
			ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
			// STATUS CADASTRADO
			if (Validador.isDominioNumericoValido(configuracaoGerencialParametrosVo.getStatusCadastrado()))
			{
				ps.setInt(++contador, configuracaoGerencialParametrosVo.getStatusCadastrado().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método utilizado para alterar valores de uma determinada Configuração Gerencial Parametros no Banco de dados.
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public void updateConfiguracaoGerencialParametros(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_CODIGO_CONFIGURACAO_GERENCIAL_PARAMETROS });
		try
		{
			GeradorLogSefazMT.gerar(configuracaoGerencialParametrosVo, DomnOperacao.OPERACAO_UPDATE, configuracaoGerencialParametrosVo.getNumeroParticao(), configuracaoGerencialParametrosVo.getCodigoTransacao(), configuracaoGerencialParametrosVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateConfiguracaoGerencialParametros(ps, configuracaoGerencialParametrosVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_CONFIGURACAO_GERENCIAL_PARAMETROS);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_CONFIGURACAO_GERENCIAL_PARAMETROS);
		}
		finally
		{
			if (ps != null)
			{
				try
				{
					close(ps);
					ps = null;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Método responsável por adicionar os parâmetros válidos na instrução SQL (java.sql.PrepareStatement).
	 * @param ps (java.sql.PreparedStatement).
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateConfiguracaoGerencialParametros(final PreparedStatement ps, final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		int contador = 0;
		try
		{
			// VALOR ITEM
			if (Validador.isStringValida(configuracaoGerencialParametrosVo.getValorItem()))
			{
				ps.setString(++contador, configuracaoGerencialParametrosVo.getValorItem());
			}
			else
			{
				ps.setNull(++contador, Types.VARCHAR);
			}
			// DESCRICAO
			if (Validador.isStringValida(configuracaoGerencialParametrosVo.getDescricaoItem()))
			{
				ps.setString(++contador, configuracaoGerencialParametrosVo.getDescricaoItem());
			}
			else
			{
				ps.setNull(++contador, Types.VARCHAR);
			}
			// TIPO ITEM
			if (Validador.isDominioNumericoValido(configuracaoGerencialParametrosVo.getTipoItem()))
			{
				ps.setInt(++contador, configuracaoGerencialParametrosVo.getTipoItem().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// DATA ATUALIZACAO
			ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
			// STATUS CADASTRADO
			if (Validador.isDominioNumericoValido(configuracaoGerencialParametrosVo.getStatusCadastrado()))
			{
				ps.setInt(++contador, configuracaoGerencialParametrosVo.getStatusCadastrado().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// CODIGO
			if (Validador.isNumericoValido(configuracaoGerencialParametrosVo.getCodigo()))
			{
				ps.setLong(++contador, configuracaoGerencialParametrosVo.getCodigo());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}
}
