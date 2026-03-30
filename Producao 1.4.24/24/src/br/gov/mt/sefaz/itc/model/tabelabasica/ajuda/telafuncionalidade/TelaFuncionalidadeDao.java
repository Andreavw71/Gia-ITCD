/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: TelaFuncionalidadeDao.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: TelaFuncionalidadeDao.java,v 1.1.1.1 2008/05/28 17:55:06 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telafuncionalidade;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposTelaFuncionalidade;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import java.util.Date;

import sefaz.mt.util.AlteraException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.UtilStmt;


/**
 * Classe de acesso a dados(Data Access Object).
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.1.1.1 $
 */
public class TelaFuncionalidadeDao extends AbstractDao implements TabelasITC, CamposTelaFuncionalidade
{
	/**
	 * Construtor da classe.
	 * @param conexao
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaFuncionalidadeDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_TELA_FUNCIONALIDADE);
	}

	/**
	 * Retorna os Campos da Tabela de Tela Funcionalidade (ITCTB38_TELA_FUNCIONALIDADE)
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String[] getTelaFuncionalidade()
	{
		return new String[] { CAMPO_CODIGO_FUNCIONALIDADE, CAMPO_CODIGO_TELA_AJUDA, CAMPO_DESCRICAO_TELA_FUNCIONALIDADE, CAMPO_INFORMACAO_TITULO_TELA_FUNCIONALIDADE, CAMPO_STATUS_TELA_FUNCIONALIDADE, CAMPO_DATA_ATUALIZACAO_BD };
	}

	/**
	 * Inclui informaçãos sobre uma Tela Funcionalidade no banco de dados.
	 * @param telaFuncionalidadeVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void insertTelaFuncionalidade(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getTelaFuncionalidade());
		try
		{
			//		TODO	GeradorLogSefazMT.gerar(telaFuncionalidadeVo, DomnOperacao.OPERACAO_INSERT, DomnNumeroParticao.PROPRIA_TRANSACAO, DomnCodigoTransacao.TELA_FUNCIONALIDADE_INCLUIR, telaFuncionalidadeVo.getUsuarioLogado(), conn);
			ps = conn.prepareStatement(sql);
			telaFuncionalidadeVo.setDataAtualizacaoBD(new Date());
			preparedStatementInsertTelaFuncionalidade(ps, telaFuncionalidadeVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_TELA_FUNCIONALIDADE);
		}
		//		catch (LogSefazException e)
		//		{
		//			e.printStackTrace();
		//			throw new IncluiException(MensagemErro.INCLUIR_TELA_FUNCIONALIDADE);
		//		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_TELA_FUNCIONALIDADE);
		}
		finally
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

	/**
	 * Método responsável por adicionar os parâmetros válidos na instrução SQL (java.sql.PrepareStatement).
	 * @param ps (PreparedStatement).
	 * @param telaFuncionalidadeVo (Value Object).
	 * @throws ObjetoObrigatorioException  Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException  Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void preparedStatementInsertTelaFuncionalidade(final PreparedStatement ps, final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(telaFuncionalidadeVo);
		int contador = 0;
		if (Validador.isNumericoValido(telaFuncionalidadeVo.getFuncionalidadeVo().getCodigo()))
		{
			ps.setLong(++contador, telaFuncionalidadeVo.getFuncionalidadeVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(telaFuncionalidadeVo.getTelaAjudaVo().getCodigo()))
		{
			ps.setLong(++contador, telaFuncionalidadeVo.getTelaAjudaVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isStringValida(telaFuncionalidadeVo.getDescricaoTelaFuncionalidade()))
		{
			ps.setString(++contador, telaFuncionalidadeVo.getDescricaoTelaFuncionalidade());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		if (Validador.isStringValida(telaFuncionalidadeVo.getInformacaoTituloTelaFuncionalidade()))
		{
			ps.setString(++contador, telaFuncionalidadeVo.getInformacaoTituloTelaFuncionalidade());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		if (Validador.isDominioNumericoValido(telaFuncionalidadeVo.getStatusTelaFuncionalidade()))
		{
			ps.setInt(++contador, telaFuncionalidadeVo.getStatusTelaFuncionalidade().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO BD
		ps.setTimestamp(++contador, new Timestamp(telaFuncionalidadeVo.getDataAtualizacaoBD().getTime()));
	}

	/**
	 * Atualiza informações sobre uma Tela Funcionalidade no banco de dados.
	 * @param telaFuncionalidadeVo (Value Object).
	 * @throws ObjetoObrigatorioException  Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void updateTelaFuncionalidade(final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(telaFuncionalidadeVo);
		PreparedStatement ps = null;
		String sql = 
				 utilStmt.geraUpdt(new String[] { CAMPO_DESCRICAO_TELA_FUNCIONALIDADE, CAMPO_INFORMACAO_TITULO_TELA_FUNCIONALIDADE, CAMPO_STATUS_TELA_FUNCIONALIDADE, CAMPO_DATA_ATUALIZACAO_BD }, new String[] { CAMPO_CODIGO_FUNCIONALIDADE, CAMPO_CODIGO_TELA_AJUDA });
		try
		{
			//		TODO	GeradorLogSefazMT.gerar(telaFuncionalidadeVo, DomnOperacao.OPERACAO_UPDATE, DomnNumeroParticao.PROPRIA_TRANSACAO, DomnCodigoTransacao.TELA_FUNCIONALIDADE_ALTERAR, telaFuncionalidadeVo.getUsuarioLogado(), conn);
			ps = conn.prepareStatement(sql);
			telaFuncionalidadeVo.setDataAtualizacaoBD(new Date());
			preparedStatementUpdateTelaFuncionalidade(ps, telaFuncionalidadeVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_TELA_FUNCIONALIDADE);
		}
		//		catch (LogSefazException e)
		//		{
		//			e.printStackTrace();
		//			throw new AlteraException(MensagemErro.ALTERAR_TELA_FUNCIONALIDADE);
		//		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_TELA_FUNCIONALIDADE);
		}
		finally
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

	/**
	 * Método responsável por adicionar os parâmetros válidos na instrução SQL (java.sql.PrepareStatement).
	 * @param ps (PreparedStatement).
	 * @param telaFuncionalidadeVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void preparedStatementUpdateTelaFuncionalidade(final PreparedStatement ps, final TelaFuncionalidadeVo telaFuncionalidadeVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(telaFuncionalidadeVo);
		int contador = 0;
		if (Validador.isStringValida(telaFuncionalidadeVo.getDescricaoTelaFuncionalidade()))
		{
			ps.setString(++contador, telaFuncionalidadeVo.getDescricaoTelaFuncionalidade());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		if (Validador.isStringValida(telaFuncionalidadeVo.getInformacaoTituloTelaFuncionalidade()))
		{
			ps.setString(++contador, telaFuncionalidadeVo.getInformacaoTituloTelaFuncionalidade());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		if (Validador.isDominioNumericoValido(telaFuncionalidadeVo.getStatusTelaFuncionalidade()))
		{
			ps.setInt(++contador, telaFuncionalidadeVo.getStatusTelaFuncionalidade().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO BD
		ps.setTimestamp(++contador, new Timestamp(telaFuncionalidadeVo.getDataAtualizacaoBD().getTime()));
		if (Validador.isNumericoValido(telaFuncionalidadeVo.getFuncionalidadeVo().getCodigo()))
		{
			ps.setLong(++contador, telaFuncionalidadeVo.getFuncionalidadeVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(telaFuncionalidadeVo.getTelaAjudaVo().getCodigo()))
		{
			ps.setLong(++contador, telaFuncionalidadeVo.getTelaAjudaVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}
}
