 /**
  * Ábaco Tecnologia de Informação - LTDA
  * Arquivo: TelaCampoAjudaDao.java
  * Revisão: Leandro Dorileo
  * Data revisão: 20/03/2008
  * $Id: TelaCampoAjudaDao.java,v 1.1.1.1 2008/05/28 17:55:06 lucas.nascimento Exp $
  */
 package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposTelaCampoAjuda;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import java.util.Date;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.UtilStmt;


/**
 * Classe de acesso a dados(Data Access Object).
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.1.1.1 $
 */
public class TelaCampoAjudaDao extends AbstractDao implements TabelasITC, CamposTelaCampoAjuda
{
	/**
	 * Construtor da classe.
	 * @param conexao
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaCampoAjudaDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_TELA_CAMPO_AJUDA);
	}

	/**
	 * Retorna os Campos da Tabela de Tela Campo Ajuda (ITCTB35_TELA_CAMPO_AJUDA)
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String[] getTelaCampoAjuda()
	{
		return new String[] { CAMPO_CODIGO_CAMPO_AJUDA, CAMPO_CODIGO_TELA_AJUDA, CAMPO_DESCRICAO_AJUDA_CAMPO, CAMPO_STATUS_TELA_CAMPO_AJUDA, CAMPO_DATA_ATUALIZACAO_BD };
	}

	/**
	 * Inclui informaçãos sobre uma Tela Campo Ajuda no banco de dados.
	 * @param telaCampoAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void insertTelaCampoAjuda(final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(telaCampoAjudaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getTelaCampoAjuda());
		try
		{
			GeradorLogSefazMT.gerar(telaCampoAjudaVo, DomnOperacao.OPERACAO_INSERT, telaCampoAjudaVo.getNumeroParticao(), telaCampoAjudaVo.getCodigoTransacao(), telaCampoAjudaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			telaCampoAjudaVo.setDataAtualizacaoBD(new Date());
			preparedStatementInsertTelaCampoAjuda(ps, telaCampoAjudaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_TELA_CAMPO_AJUDA);
		}
		//    catch (LogSefazException e)
		//    {
		//       e.printStackTrace();
		//       throw new IncluiException(MensagemErro.INCLUIR_TELA_CAMPO_AJUDA);
		//    }
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_TELA_CAMPO_AJUDA);
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
	 * @param telaCampoAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException  Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException  Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void preparedStatementInsertTelaCampoAjuda(final PreparedStatement ps, final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(telaCampoAjudaVo);
		int contador = 0;
		if (Validador.isNumericoValido(telaCampoAjudaVo.getCampoAjudaVo().getCodigo()))
		{
			ps.setLong(++contador, telaCampoAjudaVo.getCampoAjudaVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(telaCampoAjudaVo.getTelaAjudaVo().getCodigo()))
		{
			ps.setLong(++contador, telaCampoAjudaVo.getTelaAjudaVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isStringValida(telaCampoAjudaVo.getDescricaoAjudaCampo()))
		{
			ps.setString(++contador, telaCampoAjudaVo.getDescricaoAjudaCampo());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		if (Validador.isDominioNumericoValido(telaCampoAjudaVo.getStatusTelaCampo()))
		{
			ps.setInt(++contador, telaCampoAjudaVo.getStatusTelaCampo().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO BD
		ps.setTimestamp(++contador, new Timestamp(telaCampoAjudaVo.getDataAtualizacaoBD().getTime()));
	}

	/**
	 * Atualiza informações sobre uma Tela Campo Ajuda no banco de dados.
	 * @param telaCampoAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException  Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void updateTelaCampoAjuda(final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(telaCampoAjudaVo);
		PreparedStatement ps = null;
		String sql = 
				 utilStmt.geraUpdt(new String[] { CAMPO_DESCRICAO_AJUDA_CAMPO, CAMPO_STATUS_TELA_CAMPO_AJUDA, CAMPO_DATA_ATUALIZACAO_BD }, new String[] { CAMPO_CODIGO_TELA_AJUDA, CAMPO_CODIGO_CAMPO_AJUDA });
		try
		{
			GeradorLogSefazMT.gerar(telaCampoAjudaVo, DomnOperacao.OPERACAO_UPDATE, telaCampoAjudaVo.getNumeroParticao(), telaCampoAjudaVo.getCodigoTransacao(), telaCampoAjudaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			telaCampoAjudaVo.setDataAtualizacaoBD(new Date());
			preparedStatementUpdateTelaCampoAjuda(ps, telaCampoAjudaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_TELA_CAMPO_AJUDA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_TELA_CAMPO_AJUDA);
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
	 * Método responsável por alterar os parâmetros válidos na instrução SQL (java.sql.PrepareStatement).
	 * @param ps (PreparedStatement).
	 * @param telaCampoAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void preparedStatementUpdateTelaCampoAjuda(final PreparedStatement ps, final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(telaCampoAjudaVo);
		int contador = 0;
		if (Validador.isStringValida(telaCampoAjudaVo.getDescricaoAjudaCampo()))
		{
			ps.setString(++contador, telaCampoAjudaVo.getDescricaoAjudaCampo());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		if (Validador.isDominioNumericoValido(telaCampoAjudaVo.getStatusTelaCampo()))
		{
			ps.setInt(++contador, telaCampoAjudaVo.getStatusTelaCampo().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO BD
		ps.setTimestamp(++contador, new Timestamp(telaCampoAjudaVo.getDataAtualizacaoBD().getTime()));
		if (Validador.isNumericoValido(telaCampoAjudaVo.getTelaAjudaVo().getCodigo()))
		{
			ps.setLong(++contador, telaCampoAjudaVo.getTelaAjudaVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(telaCampoAjudaVo.getCodigo()))
		{
			ps.setLong(++contador, telaCampoAjudaVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Desativa as informações sobre uma Tela Campo Ajuda no banco de dados.
	 * @param telaCampoAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException  Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR  um registro no banco de dados e não consegue.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void inactiveTelaCampoAjuda(final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(telaCampoAjudaVo);
		PreparedStatement ps = null;
		String sql = 
				 utilStmt.geraUpdt(new String[] { CAMPO_STATUS_TELA_CAMPO_AJUDA, CAMPO_DATA_ATUALIZACAO_BD }, new String[] { CAMPO_CODIGO_CAMPO_AJUDA, CAMPO_CODIGO_TELA_AJUDA });
		try
		{
			//    TODO  GeradorLogSefazMT.gerar(telaCampoAjudaVo, DomnOperacao.OPERACAO_UPDATE, DomnNumeroParticao.PROPRIA_TRANSACAO, DomnCodigoTransacao.TELA_CAMPO_AJUDA_ALTERAR, telaCampoAjudaVo.getUsuarioLogado(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInactiveTelaCampoAjuda(ps, telaCampoAjudaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_TELA_CAMPO_AJUDA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_TELA_CAMPO_AJUDA);
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
	 * Método responsável por alterar os parâmetros válidos na instrução SQL (java.sql.PrepareStatement).
	 * @param ps (PreparedStatement).
	 * @param telaCampoAjudaVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void preparedStatementInactiveTelaCampoAjuda(final PreparedStatement ps, final TelaCampoAjudaVo telaCampoAjudaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(telaCampoAjudaVo);
		int contador = 0;
		if (Validador.isDominioNumericoValido(telaCampoAjudaVo.getStatusTelaCampo()))
		{
			ps.setInt(++contador, (telaCampoAjudaVo.getStatusTelaCampo().getValorCorrente()));
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO BD
		ps.setTimestamp(++contador, new Timestamp(telaCampoAjudaVo.getDataAtualizacaoBD().getTime()));
		if (Validador.isNumericoValido(telaCampoAjudaVo.getTelaAjudaVo().getCodigo()))
		{
			ps.setLong(++contador, telaCampoAjudaVo.getTelaAjudaVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(telaCampoAjudaVo.getCodigo()))
		{
			ps.setLong(++contador, telaCampoAjudaVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}
}
