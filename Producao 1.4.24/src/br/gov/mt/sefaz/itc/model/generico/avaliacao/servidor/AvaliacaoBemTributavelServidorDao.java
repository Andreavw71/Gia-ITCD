/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AvaliacaoBemTributavelServidorDao.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: AvaliacaoBemTributavelServidorDao.java,v 1.1.1.1 2008/05/28 17:55:04 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.avaliacao.servidor;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposAvaliacaoBemtributavelServidor;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.ExcluiException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.UtilStmt;


/**
 * Classe para acesso ao banco de dados, responsável por inserir e alterar registros referentes à Avaliação de Bem Tributável - Servidor
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class AvaliacaoBemTributavelServidorDao extends AbstractDao implements TabelasITC, CamposAvaliacaoBemtributavelServidor
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conn
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelServidorDao(Connection conn)
	{
		super(conn);
		utilStmt = new UtilStmt(TABELA_AVALIACAO_BEMTRIBUTAVEL_SERVIDOR);
	}

	/**
	 * Retorna os campos desta tabela
	 * 
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{
		return new String[] { CAMPO_ITCTB28_CODIGO_AVALIACAO_BEM_TRIBUTAVEL, CAMPO_SGPTB06_NUMERO_MATRICULA_SERVIDOR };
	}

	/**
	 * Monta o Prepared Statement de acordo com os dados válidos da Avaliação - Servidor
	 * 
	 * @param ps
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertAvaliacaoBemTributavelServidor(PreparedStatement ps, AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		int contador = 0;
		// AVALICAO JUDICIAL
		if (Validador.isNumericoValido(avaliacaoBemTributavelServidorVo.getAvaliacaoBemTributavelVo().getCodigo()))
		{
			ps.setLong(++contador, avaliacaoBemTributavelServidorVo.getAvaliacaoBemTributavelVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// SERVIDOR SEFAZ
		if (Validador.isNumericoValido(avaliacaoBemTributavelServidorVo.getServidorSefazVo().getNumrMatricula().longValue()))
		{
			ps.setLong(++contador, avaliacaoBemTributavelServidorVo.getServidorSefazVo().getNumrMatricula().longValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Adiciona uma Avaliação - Servidor no banco de dados
	 * 
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertAvaliacaoBemTributavelServidor(final AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			GeradorLogSefazMT.gerar(avaliacaoBemTributavelServidorVo, DomnOperacao.OPERACAO_INSERT, avaliacaoBemTributavelServidorVo.getNumeroParticao(), avaliacaoBemTributavelServidorVo.getCodigoTransacao(), avaliacaoBemTributavelServidorVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertAvaliacaoBemTributavelServidor(ps, avaliacaoBemTributavelServidorVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR);
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
	 * Alterar uma Avaliação - Servidor no banco de dados
	 * 
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateAvaliacaoBemTributavelServidor(final AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos());
		try
		{
			GeradorLogSefazMT.gerar(avaliacaoBemTributavelServidorVo, DomnOperacao.OPERACAO_UPDATE, avaliacaoBemTributavelServidorVo.getNumeroParticao(), avaliacaoBemTributavelServidorVo.getCodigoTransacao(), avaliacaoBemTributavelServidorVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertAvaliacaoBemTributavelServidor(ps, avaliacaoBemTributavelServidorVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR);
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
	 * Apagar uma Avaliação - Servidor no banco de dados
	 * 
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void deleteAvaliacaoBemTributavelServidorByAvaliacao(final AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  ExcluiException
	{
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_ITCTB28_CODIGO_AVALIACAO_BEM_TRIBUTAVEL, CAMPO_SGPTB06_NUMERO_MATRICULA_SERVIDOR });
		try
		{
			GeradorLogSefazMT.gerar(avaliacaoBemTributavelServidorVo, DomnOperacao.OPERACAO_DELETE, avaliacaoBemTributavelServidorVo.getNumeroParticao(), avaliacaoBemTributavelServidorVo.getCodigoTransacao(), avaliacaoBemTributavelServidorVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementDeleteAvaliacaoBemTributavelServidorByAvaliacao(ps, avaliacaoBemTributavelServidorVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.ALTERAR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.ALTERAR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR);
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
	 * Monta o Prepared Statement de acordo com os dados válidos da Avaliação - Servidor
	 * 
	 * @param ps
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementDeleteAvaliacaoBemTributavelServidorByAvaliacao(PreparedStatement ps, AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		int contador = 0;
		// AVALICAO JUDICIAL
		if (Validador.isNumericoValido(avaliacaoBemTributavelServidorVo.getAvaliacaoBemTributavelVo().getCodigo()))
		{
			ps.setLong(++contador, avaliacaoBemTributavelServidorVo.getAvaliacaoBemTributavelVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if(Validador.isNumericoValido(avaliacaoBemTributavelServidorVo.getServidorSefazVo().getNumrMatricula()))
		{
		   ps.setLong(++contador, avaliacaoBemTributavelServidorVo.getServidorSefazVo().getNumrMatricula().longValue());
		}
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }		
	}
}
