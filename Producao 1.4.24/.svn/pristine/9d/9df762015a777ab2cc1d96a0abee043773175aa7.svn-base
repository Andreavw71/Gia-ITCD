/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BemDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 08/10/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.bem;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBem;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.log.util.excecao.LogSefazException;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.SefazDataHora;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


/**
 * Classe de acesso a dados (Data Access Object).
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.2 $
 */
public class BemDao extends AbstractDao implements TabelasITC, SequencesITC, CamposBem
{
	/**
	 * Construtor da Classe.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public BemDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_BEM);
	}

	/**
	 * Retorna os Campos da Tabela de Bems (ITCTB06_BEM)
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCamposBem()
	{
		return new String[] { CAMPO_CODIGO_BEM, CAMPO_DESCRICAO_BEM, CAMPO_STATUS_BEM, CAMPO_TIPO_BEM, CAMPO_DATA_ATUALIZACAO_BD, CAMPO_POSSUI_CONSTRUCAO };
	}

	/**
	 * Inclui informaçãos sobre um Bem no banco de dados.
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void insertBem(final BemVo bemVo) throws ObjetoObrigatorioException, IncluiException
	{
		Validador.validaObjeto(bemVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCamposBem());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			bemVo.setCodigo(sequence.next(SEQUENCE_BEM));
			GeradorLogSefazMT.gerar(bemVo, DomnOperacao.OPERACAO_INSERT, bemVo.getNumeroParticao(), bemVo.getCodigoTransacao(), bemVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertBem(ps, bemVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_BEM);
		}
		catch (LogSefazException e)
		{
		e.printStackTrace();
		throw new IncluiException(MensagemErro.INCLUIR_BEM);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_BEM);
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
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertBem(final PreparedStatement ps, final BemVo bemVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(bemVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(bemVo.getCodigo()))
		{
			ps.setLong(++contador, bemVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DESCRICAO
		if (Validador.isStringValida(bemVo.getDescricaoTipoBem()))
		{
			ps.setString(++contador, bemVo.getDescricaoTipoBem());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// STATUS BEM
		if (Validador.isDominioNumericoValido(bemVo.getStatusBem()))
		{
			ps.setInt(++contador, bemVo.getStatusBem().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// TIPO BEM
		if (Validador.isDominioNumericoValido(bemVo.getClassificacaoTipoBem()))
		{
			ps.setInt(++contador, bemVo.getClassificacaoTipoBem().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
		// POSSUI CONSTRUCAO
		if(Validador.isDominioNumericoValido(bemVo.getPossuiConstrucao()))
		{
			ps.setInt(++contador, bemVo.getPossuiConstrucao().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Atualiza informações sobre um Bem no banco de dados.
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void updateBem(final BemVo bemVo) throws ObjetoObrigatorioException, AlteraException
	{
		Validador.validaObjeto(bemVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCamposBem(), new String[] { CAMPO_CODIGO_BEM });
		try
		{
			GeradorLogSefazMT.gerar(bemVo, DomnOperacao.OPERACAO_UPDATE, bemVo.getNumeroParticao(), 
			bemVo.getCodigoTransacao(), bemVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateBem(ps, bemVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_BEM);
		}
		catch (LogSefazException e)
		{
			e.printStackTrace();	
			throw new AlteraException(MensagemErro.ALTERAR_BEM);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_BEM);
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
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateBem(final PreparedStatement ps, final BemVo bemVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(bemVo);
		int contador = 0;
		// DESCRICAO
		if (Validador.isStringValida(bemVo.getDescricaoTipoBem()))
		{
			ps.setString(++contador, bemVo.getDescricaoTipoBem());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// STATUS BEM
		if (Validador.isDominioNumericoValido(bemVo.getStatusBem()))
		{
			ps.setInt(++contador, bemVo.getStatusBem().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// TIPO BEM
		if (Validador.isDominioNumericoValido(bemVo.getClassificacaoTipoBem()))
		{
			ps.setInt(++contador, bemVo.getClassificacaoTipoBem().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
	   // POSSUI CONSTRUCAO
	   if(Validador.isDominioNumericoValido(bemVo.getPossuiConstrucao()))
	   {
	      ps.setInt(++contador, bemVo.getPossuiConstrucao().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }     		
		// CODIGO
		if (Validador.isNumericoValido(bemVo.getCodigo()))
		{
			ps.setLong(++contador, bemVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}
}
