/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: MultaDeMoraDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 12/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.multademora;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposMultaMora;
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
public class MultaDeMoraDao extends AbstractDao implements TabelasITC, SequencesITC, CamposMultaMora
{
	/**
	 * Construtor que recebe a conexão com o Banco de Dados.
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public MultaDeMoraDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_MULTA_MORA);
	}

	/**
	 * Retorna os Campos da Tabela ITCTB02_MULTA_MORA.
	 * @return String[]
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{
		return new String[] { CAMPO_CODIGO_MULTA_MORA, CAMPO_PERCENTUAL_MULTA_MORA, CAMPO_PERCENTUAL_MAXIMO_MULTA_MORA, CAMPO_STATUS_MULTA_MORA, CAMPO_DATA_ATUALIZACAO_BD };
	}

	/**
	 * Método utilizado para inserir uma Multa de Mora.
	 * @param multaDeMoraVo Multa de Mora (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public void insertMultaDeMora(final MultaDeMoraVo multaDeMoraVo) throws ObjetoObrigatorioException, IncluiException
	{
		Validador.validaObjeto(multaDeMoraVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			multaDeMoraVo.setCodigo(sequence.next(SEQUENCE_MULTA_MORA));
			GeradorLogSefazMT.gerar(multaDeMoraVo, DomnOperacao.OPERACAO_INSERT, multaDeMoraVo.getNumeroParticao(), multaDeMoraVo.getCodigoTransacao(), multaDeMoraVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertMultaDeMora(ps, multaDeMoraVo);
			if (ps.executeUpdate() != 1)
			{
				throw new IncluiException(MensagemErro.INCLUIR_MULTA_MORA);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_MULTA_MORA);
		}
		//	   catch (LogSefazException e)
		//	   {
		//	     e.printStackTrace();
		//	     throw new IncluiException(MensagemErro.INCLUIR_MULTA_MORA);
		//	   }
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_MULTA_MORA);
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
	 * @param multaDeMoraVo Multa de Mora (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertMultaDeMora(final PreparedStatement ps, final MultaDeMoraVo multaDeMoraVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(multaDeMoraVo);
		int contador = 0;
		try
		{
			// CODIGO
			if (Validador.isNumericoValido(multaDeMoraVo.getCodigo()))
			{
				ps.setLong(++contador, multaDeMoraVo.getCodigo());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// TIPO MULTA MORA
			if (Validador.isNumericoValido(multaDeMoraVo.getPercentualMultaMora()))
			{
				ps.setDouble(++contador, multaDeMoraVo.getPercentualMultaMora());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// PERCENTUAL 
			if (Validador.isNumericoValido(multaDeMoraVo.getPercentualMaximoMultaMora()))
			{
				ps.setDouble(++contador, multaDeMoraVo.getPercentualMaximoMultaMora());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// STATUS  
			if (Validador.isDominioNumericoValido(multaDeMoraVo.getStatusMultaMora()))
			{
				ps.setInt(++contador, multaDeMoraVo.getStatusMultaMora().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// DATA ATUALIZACAO
			ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método utilizado para alterar uma Multa de Mora.
	 * @param multaDeMoraVo Multa de Mora (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public void updateMultaDeMora(final MultaDeMoraVo multaDeMoraVo) throws ObjetoObrigatorioException, AlteraException
	{
		Validador.validaObjeto(multaDeMoraVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_CODIGO_MULTA_MORA });
		try
		{
			GeradorLogSefazMT.gerar(multaDeMoraVo, DomnOperacao.OPERACAO_UPDATE, multaDeMoraVo.getNumeroParticao(), multaDeMoraVo.getCodigoTransacao(), multaDeMoraVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementIUpdateMultaDeMora(ps, multaDeMoraVo);
			if (ps.executeUpdate() != 1)
			{
				throw new AlteraException(MensagemErro.ALTERAR_MULTA_MORA);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_MULTA_MORA);
		}
		//	   catch (LogSefazException e)
		//	   {
		//	      e.printStackTrace();
		//	      throw new AlteraException(MensagemErro.ALTERAR_MULTA_MORA);
		//	   }
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_MULTA_MORA);
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
	 * @param multaDeMoraVo Multa de Mora (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementIUpdateMultaDeMora(final PreparedStatement ps, final MultaDeMoraVo multaDeMoraVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(multaDeMoraVo);
		int contador = 0;
		try
		{
			// TIPO MULTA MORA
			if (Validador.isNumericoValido(multaDeMoraVo.getPercentualMultaMora()))
			{
				ps.setDouble(++contador, multaDeMoraVo.getPercentualMultaMora());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// PERCENTUAL 
			if (Validador.isNumericoValido(multaDeMoraVo.getPercentualMaximoMultaMora()))
			{
				ps.setDouble(++contador, multaDeMoraVo.getPercentualMaximoMultaMora());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// STATUS  
			if (Validador.isDominioNumericoValido(multaDeMoraVo.getStatusMultaMora()))
			{
				ps.setInt(++contador, multaDeMoraVo.getStatusMultaMora().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// DATA ATUALIZACAO
			ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
			// CODIGO
			if (Validador.isNumericoValido(multaDeMoraVo.getCodigo()))
			{
				ps.setLong(++contador, multaDeMoraVo.getCodigo());
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
