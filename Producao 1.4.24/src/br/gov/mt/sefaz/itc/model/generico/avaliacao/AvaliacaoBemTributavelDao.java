/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AvaliacaoBemTributavelDao.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: AvaliacaoBemTributavelDao.java,v 1.1.1.1 2008/05/28 17:55:04 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.avaliacao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposAvaliacaoBemtributavel;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Date;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.SefazDataHora;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


/**
 * Classe de manutenção de dados no Banco de Dados. Responsável por inserir e alterar registros referentes a Avaliação de Bem Tributável
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class AvaliacaoBemTributavelDao extends AbstractDao implements TabelasITC, CamposAvaliacaoBemtributavel, SequencesITC
{
	/**
	 * Retorna os campos desta tabela
	 *
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{
		return new String[] 
		{ 
			CAMPO_AVALIACAO_BEMTRIBUTAVEL_CODIGO
			, CAMPO_ITCTB18_CODIGO_BEMTRIBUTAVEL
			, CAMPO_AVALIACAO_JUDICIAL
			, CAMPO_VALOR_AVALIACAO
			, CAMPO_DATA_AVALIACAO
			, CAMPO_DATA_CADASTRO
			, CAMPO_OBSERVACAO
			, CAMPO_INFO_ISENTO
			, CAMPO_DATA_ATUALIZACAO_BD
         , CAMPO_STATUS_AVALIACAO
         , CAMPO_AVALIACAO_IMPRESSA
		};
	}

	/**
	 * Construtor que recebe a conexão com o banco de dados
	 *
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_AVALIACAO_BEMTRIBUTAVEL);
	}

	/**
	 * Método para montar o Prepared Statemante com os valores úteis da Avaliação.
	 *
	 * @param ps
	 * @param avaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertAvaliacaoBemTributavel(PreparedStatement ps, AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		int contador = 0;
		//CODIGO
		if (Validador.isNumericoValido(avaliacaoBemTributavelVo.getCodigo()))
		{
			ps.setLong(++contador, avaliacaoBemTributavelVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// BEM TRIBUTAVEL
		if (Validador.isNumericoValido(avaliacaoBemTributavelVo.getBemTributavel().getCodigo()))
		{
			ps.setLong(++contador, avaliacaoBemTributavelVo.getBemTributavel().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// AVALIACAO JUDICIAL
		if (Validador.isDominioNumericoValido(avaliacaoBemTributavelVo.getAvaliacaoJudicial()))
		{
			ps.setInt(++contador, avaliacaoBemTributavelVo.getAvaliacaoJudicial().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR DA AVALIACAO
		if (Validador.isNumericoValido(avaliacaoBemTributavelVo.getValorAvaliacao()))
		{
			ps.setDouble(++contador, avaliacaoBemTributavelVo.getValorAvaliacao());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA AVALIACAO
		if (Validador.isDataValida(avaliacaoBemTributavelVo.getDataAvaliacao()))
		{
			ps.setDate(++contador, new java.sql.Date(avaliacaoBemTributavelVo.getDataAvaliacao().getTime()));
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
		}
		// DATA CADASTRO
		if (Validador.isDataValida(avaliacaoBemTributavelVo.getDataCadastro()))
		{
			ps.setDate(++contador, new java.sql.Date(avaliacaoBemTributavelVo.getDataCadastro().getTime()));
		}
		else
		{
			ps.setDate(++contador, new java.sql.Date(new Date().getTime()));
		}
		// OBSERVACAO
		if (Validador.isStringValida(avaliacaoBemTributavelVo.getObservacao()))
		{
			ps.setString(++contador, avaliacaoBemTributavelVo.getObservacao());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// ISENTO
		if (Validador.isDominioNumericoValido(avaliacaoBemTributavelVo.getIsento()))
		{
			ps.setInt(++contador, avaliacaoBemTributavelVo.getIsento().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
	   
      // STATUS_AVALICAO
	   if (Validador.isDominioNumericoValido(avaliacaoBemTributavelVo.getStatusAvaliacao()))
	   {
	      ps.setInt(++contador, avaliacaoBemTributavelVo.getStatusAvaliacao().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   // STATUS_IMPRESSAO
	   if (Validador.isDominioNumericoValido(avaliacaoBemTributavelVo.getAvaliacaoImpressa()))
	   {
	      ps.setInt(++contador, avaliacaoBemTributavelVo.getAvaliacaoImpressa().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	}

	/**
	 * Método para montar o Prepared Statemante com os valores úteis da Avaliação.
	 *
	 * @param ps
	 * @param avaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateAvaliacaoBemTributavel(PreparedStatement ps, AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		int contador = 0;
		// BEM TRIBUTAVEL
		if (Validador.isNumericoValido(avaliacaoBemTributavelVo.getBemTributavel().getCodigo()))
		{
			ps.setLong(++contador, avaliacaoBemTributavelVo.getBemTributavel().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// AVALIACAO JUDICIAL
		if (Validador.isDominioNumericoValido(avaliacaoBemTributavelVo.getAvaliacaoJudicial()))
		{
			ps.setInt(++contador, avaliacaoBemTributavelVo.getAvaliacaoJudicial().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR DA AVALIACAO
		if (Validador.isNumericoValido(avaliacaoBemTributavelVo.getValorAvaliacao()))
		{
			ps.setDouble(++contador, avaliacaoBemTributavelVo.getValorAvaliacao());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA AVALIACAO
		if (Validador.isDataValida(avaliacaoBemTributavelVo.getDataAvaliacao()))
		{
			ps.setDate(++contador, new java.sql.Date(avaliacaoBemTributavelVo.getDataAvaliacao().getTime()));
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
		}
		// DATA CADASTRO
		if (Validador.isDataValida(avaliacaoBemTributavelVo.getDataCadastro()))
		{
			ps.setDate(++contador, new java.sql.Date(avaliacaoBemTributavelVo.getDataCadastro().getTime()));
		}
		else
		{
			ps.setDate(++contador, (java.sql.Date) new java.util.Date());
		}
		// OBSERVACAO
		if (Validador.isStringValida(avaliacaoBemTributavelVo.getObservacao()))
		{
			ps.setString(++contador, avaliacaoBemTributavelVo.getObservacao());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// ISENTO
		if (Validador.isDominioNumericoValido(avaliacaoBemTributavelVo.getIsento()))
		{
			ps.setInt(++contador, avaliacaoBemTributavelVo.getIsento().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
		//CODIGO
		if (Validador.isNumericoValido(avaliacaoBemTributavelVo.getCodigo()))
		{
			ps.setLong(++contador, avaliacaoBemTributavelVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   // STATUS_AVALICAO
	   if (Validador.isDominioNumericoValido(avaliacaoBemTributavelVo.getStatusAvaliacao()))
	   {
	      ps.setInt(++contador, avaliacaoBemTributavelVo.getStatusAvaliacao().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   // STATUS_IMPRESSAO
	   if (Validador.isDominioNumericoValido(avaliacaoBemTributavelVo.getAvaliacaoImpressa()))
	   {
	      ps.setInt(++contador, avaliacaoBemTributavelVo.getAvaliacaoImpressa().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	}

	/**
	 * Método que adiciona o registro no banco de dados
	 *
	 * @param avaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertAvaliacaoBemTributavel(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			GeradorLogSefazMT.gerar(avaliacaoBemTributavelVo, DomnOperacao.OPERACAO_INSERT, avaliacaoBemTributavelVo.getNumeroParticao(), avaliacaoBemTributavelVo.getCodigoTransacao(), avaliacaoBemTributavelVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			SefazSequencia sequence = new SefazSequencia(conn);
			avaliacaoBemTributavelVo.setCodigo(sequence.next(SEQUENCE_AVALIACAO_BEMTRIBUTAVEL));
			ps = conn.prepareStatement(sql);
			preparedStatementInsertAvaliacaoBemTributavel(ps, avaliacaoBemTributavelVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_AVALIACAO_BEM_TRIBUTAVEL);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_AVALIACAO_BEM_TRIBUTAVEL);
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
	 * Método que altera o registro no banco de dados
	 *
	 * @param avaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateAvaliacaoBemTributavel(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_AVALIACAO_BEMTRIBUTAVEL_CODIGO });
		try
		{
			GeradorLogSefazMT.gerar(avaliacaoBemTributavelVo, DomnOperacao.OPERACAO_UPDATE, avaliacaoBemTributavelVo.getNumeroParticao(), avaliacaoBemTributavelVo.getCodigoTransacao(), avaliacaoBemTributavelVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateAvaliacaoBemTributavel(ps, avaliacaoBemTributavelVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_AVALIACAO_BEM_TRIBUTAVEL);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_AVALIACAO_BEM_TRIBUTAVEL);
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
	 * Método que altera a Data de Atualização BD
	 *
	 * @param avaliacaoBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Lucas Nascimento
	 */
	public void updateDataAtualizacao(final AvaliacaoBemTributavelVo avaliacaoBemTributavelVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(avaliacaoBemTributavelVo);
		PreparedStatement ps = null;
		String sql = 
				 utilStmt.geraUpdt(new String[] { CAMPO_DATA_ATUALIZACAO_BD }, new String[] { CAMPO_AVALIACAO_BEMTRIBUTAVEL_CODIGO });
		try
		{
			GeradorLogSefazMT.gerar(avaliacaoBemTributavelVo, DomnOperacao.OPERACAO_UPDATE, avaliacaoBemTributavelVo.getNumeroParticao(), avaliacaoBemTributavelVo.getCodigoTransacao(), avaliacaoBemTributavelVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			ps.setTimestamp(1, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
			ps.setLong(2, (avaliacaoBemTributavelVo.getCodigo()));
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_AVALIACAO_BEM_TRIBUTAVEL);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_AVALIACAO_BEM_TRIBUTAVEL);
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
}
