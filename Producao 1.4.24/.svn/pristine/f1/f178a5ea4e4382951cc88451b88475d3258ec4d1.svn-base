package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralConstrucao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
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
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


/**
 * Classe para acesso a dados do Ficha Imóvel Rural - Construção (Data Access Object)
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class FichaImovelRuralConstrucaoDao extends AbstractDao implements CamposFichaImovelRuralConstrucao, TabelasITC, SequencesITC
{
	/**
	 * Construtor que recebe a conexão com o banco dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_FICHA_IMOVEL_RURAL_CONSTRUCAO);
	}

	/**
	 * Retorna os Campos da Tabela de Ficha Imóvel Rural - Construcão
	 * 
	 * @return String[]
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{
		return new String[] { CAMPO_CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO, CAMPO_ITCTB13_CODIGO_TIPO_CONSTRUCAO, CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL, CAMPO_DESCRICAO_CONSTRUCAO, CAMPO_SITUACAO_ESTADO_CONSERVACAO, CAMPO_VALOR_MERCADO };
	}

	/**
	 * Método para inserir uma Ficha Imóvel Rural - Construção no banco de dados
	 * 
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertFichaImovelRuralConstrucao(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			fichaImovelRuralConstrucaoVo.setCodigo(sequence.next(SEQUENCE_FICHA_IMOVEL_RURAL_CONSTRUCAO));
			GeradorLogSefazMT.gerar(fichaImovelRuralConstrucaoVo, DomnOperacao.OPERACAO_INSERT, fichaImovelRuralConstrucaoVo.getNumeroParticao(), fichaImovelRuralConstrucaoVo.getCodigoTransacao(), fichaImovelRuralConstrucaoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertFichaImovelRuralConstrucao(ps, fichaImovelRuralConstrucaoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL_CONSTRUCAO);
		}
		/*	   catch (LogSefazException e)
	   {
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL_CONSTRUCAO);
		}*/
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL_CONSTRUCAO);
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
	 * Método que monta o PreparedStatement de acordo com os dados válidos do FichaImovelRuralConstrucaoVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertFichaImovelRuralConstrucao(final PreparedStatement ps, final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralConstrucaoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CONSTRUCAO
		if (Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getConstrucaoVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralConstrucaoVo.getConstrucaoVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// IMOVEL RURAL
		if (Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getFichaImovelRuralVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralConstrucaoVo.getFichaImovelRuralVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DESCRICAO CONSTRUCAO
		if (Validador.isStringValida(fichaImovelRuralConstrucaoVo.getDescricaoConstrucao()))
		{
			ps.setString(++contador, fichaImovelRuralConstrucaoVo.getDescricaoConstrucao());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// SITUACAO ESTADO CONSERVACAO
		if (Validador.isDominioNumericoValido(fichaImovelRuralConstrucaoVo.getSituacaoEstadoConservacao()))
		{
			ps.setInt(++contador, fichaImovelRuralConstrucaoVo.getSituacaoEstadoConservacao().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR MERCADO
		if (Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getValorMercado()))
		{
			ps.setDouble(++contador, fichaImovelRuralConstrucaoVo.getValorMercado());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método para atualizar uma Ficha Imóvel Rual Construção
	 * 
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateFichaImovelRuralConstrucao(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO });
		try
		{
			GeradorLogSefazMT.gerar(fichaImovelRuralConstrucaoVo, DomnOperacao.OPERACAO_UPDATE, fichaImovelRuralConstrucaoVo.getNumeroParticao(), fichaImovelRuralConstrucaoVo.getCodigoTransacao(), fichaImovelRuralConstrucaoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateFichaImovelRuralConstrucao(ps, fichaImovelRuralConstrucaoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_RURAL_CONSTRUCAO);
		}
		/*catch (LogSefazException e)
	   {
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_RURAL_CONSTRUCAO);
		}*/
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_RURAL_CONSTRUCAO);
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
	 * Método que monta o PreparedStatement de acordo com os dados válidos do FichaImovelRuralConstrucaoVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateFichaImovelRuralConstrucao(final PreparedStatement ps, final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		int contador = 0;
		// CONSTRUCAO
		if (Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getConstrucaoVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralConstrucaoVo.getConstrucaoVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// IMOVEL RURAL
		if (Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getFichaImovelRuralVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralConstrucaoVo.getFichaImovelRuralVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DESCRICAO CONSTRUCAO
		if (Validador.isStringValida(fichaImovelRuralConstrucaoVo.getDescricaoConstrucao()))
		{
			ps.setString(++contador, fichaImovelRuralConstrucaoVo.getDescricaoConstrucao());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// SITUACAO ESTADO CONSERVACAO
		if (Validador.isDominioNumericoValido(fichaImovelRuralConstrucaoVo.getSituacaoEstadoConservacao()))
		{
			ps.setInt(++contador, fichaImovelRuralConstrucaoVo.getSituacaoEstadoConservacao().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR MERCADO
		if (Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getValorMercado()))
		{
			ps.setDouble(++contador, fichaImovelRuralConstrucaoVo.getValorMercado());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CODIGO
		if (Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralConstrucaoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método para deletar uma Ficha Imóvel Rural Construção
	 * 
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ExcluiException
	 * @implemented by Daniel Balieiro
	 */
	public void deleteFichaImovelRuralConstrucao(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  ExcluiException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO });
		try
		{
			if (!Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getCodigo()))
			{
				throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL_CONSTRUCAO);
			}
			GeradorLogSefazMT.gerar(fichaImovelRuralConstrucaoVo, DomnOperacao.OPERACAO_DELETE, fichaImovelRuralConstrucaoVo.getNumeroParticao(), fichaImovelRuralConstrucaoVo.getCodigoTransacao(), fichaImovelRuralConstrucaoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementDeleteFichaImovelRuralConstrucao(ps, fichaImovelRuralConstrucaoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL_CONSTRUCAO);
		}
		/*catch (LogSefazException e)
	   {
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL_CONSTRUCAO);
		}*/
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL_CONSTRUCAO);
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
	 * Método que monta o PreparedStatement de acordo com os dados válidos do FichaImovelRuralConstrucaoVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementDeleteFichaImovelRuralConstrucao(final PreparedStatement ps, final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		int contador = 0;
		// TIPO CONSTRUCAO
		if (Validador.isNumericoValido(fichaImovelRuralConstrucaoVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralConstrucaoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}
}
