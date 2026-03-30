package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralRebanho;
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
 * Classe para manipular dados da Ficha Imóvel Rural Rebanho
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class FichaImovelRuralRebanhoDao extends AbstractDao implements CamposFichaImovelRuralRebanho, TabelasITC, SequencesITC
{
	/**
	 * Contrutor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralRebanhoDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_FICHA_IMOVEL_RURAL_REBANHO);
	}

	/**
	 * Retorna os campos da tabela de Ficha Imóvel Rural - Rebanho
	 * 
	 * @return String[]
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{
		return new String[] { CAMPO_CODIGO_IMOVEL_RURAL_REBANHO, CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL, CAMPO_ITCTB10_CODG_REBANHO, CAMPO_QUANTIDADE_REBANHO, CAMPO_DESCRICAO_REBANHO, CAMPO_VALOR_MERCADO };
	}

	/**
	 * Método para inserir uma Ficha Imóvel Rural Rebanho no banco de dados
	 * 
	 * @param fichaImovelRuralRebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(fichaImovelRuralRebanhoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			GeradorLogSefazMT.gerar(fichaImovelRuralRebanhoVo, DomnOperacao.OPERACAO_INSERT, fichaImovelRuralRebanhoVo.getNumeroParticao(), fichaImovelRuralRebanhoVo.getCodigoTransacao(), fichaImovelRuralRebanhoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			SefazSequencia sequence = new SefazSequencia(conn);
			fichaImovelRuralRebanhoVo.setCodigo(sequence.next(SEQUENCE_FICHA_IMOVEL_RURAL_REBANHO));
			ps = conn.prepareStatement(sql);
			preparedStatementInsertFichaImovelRuralRebanho(ps, fichaImovelRuralRebanhoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL_REBANHO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL_REBANHO);
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
	 * Método que monta o PreparedStatement de acordo com os dados válidos do FichaImovelRuralRebanhoVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralRebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertFichaImovelRuralRebanho(final PreparedStatement ps, FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralRebanhoVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralRebanhoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// IMOVEL RURAL
		if (Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getFichaImovelRuralVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralRebanhoVo.getFichaImovelRuralVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// REBANHO
		if (Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getRebanhoVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralRebanhoVo.getRebanhoVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// QUANTIDADE REBANHO
		if (Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getQuantidadeRebanho()))
		{
			ps.setLong(++contador, fichaImovelRuralRebanhoVo.getQuantidadeRebanho());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DESCRICAO REBANHO
		if (Validador.isStringValida(fichaImovelRuralRebanhoVo.getDescricaoRebanho()))
		{
			ps.setString(++contador, fichaImovelRuralRebanhoVo.getDescricaoRebanho());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// VALOR MERCADO
		if (Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getValorMercado()))
		{
			ps.setDouble(++contador, fichaImovelRuralRebanhoVo.getValorMercado());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método que atualiza uma Ficha Imóvel Rural Rebanho
	 * 
	 * @param fichaImovelRuralRebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(fichaImovelRuralRebanhoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_CODIGO_IMOVEL_RURAL_REBANHO });
		try
		{
			GeradorLogSefazMT.gerar(fichaImovelRuralRebanhoVo, DomnOperacao.OPERACAO_UPDATE, fichaImovelRuralRebanhoVo.getNumeroParticao(), fichaImovelRuralRebanhoVo.getCodigoTransacao(), fichaImovelRuralRebanhoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateFichaImovelRuralRebanho(ps, fichaImovelRuralRebanhoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_RURAL_REBANHO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_RURAL_REBANHO);
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
	 * Método que monta o PreparedStatement de acordo com os dados válidos do FichaImovelRuralRebanhoVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralRebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateFichaImovelRuralRebanho(final PreparedStatement ps, FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralRebanhoVo);
		int contador = 0;
		// IMOVEL RURAL
		if (Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getFichaImovelRuralVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralRebanhoVo.getFichaImovelRuralVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// REBANHO
		if (Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getRebanhoVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralRebanhoVo.getRebanhoVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// QUANTIDADE REBANHO
		if (Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getQuantidadeRebanho()))
		{
			ps.setLong(++contador, fichaImovelRuralRebanhoVo.getQuantidadeRebanho());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DESCRICAO REBANHO
		if (Validador.isStringValida(fichaImovelRuralRebanhoVo.getDescricaoRebanho()))
		{
			ps.setString(++contador, fichaImovelRuralRebanhoVo.getDescricaoRebanho());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// VALOR MERCADO
		if (Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getValorMercado()))
		{
			ps.setDouble(++contador, fichaImovelRuralRebanhoVo.getValorMercado());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CODIGO
		if (Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralRebanhoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método que deleta uma Ficha Imóvel Rural Rebanho
	 * 
	 * @param fichaImovelRuralRebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ExcluiException
	 * @implemented by Daniel Balieiro
	 */
	public void deleteFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
			  ExcluiException
	{
		Validador.validaObjeto(fichaImovelRuralRebanhoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_CODIGO_IMOVEL_RURAL_REBANHO });
		try
		{
			if (!Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getCodigo()))
			{
				throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL_REBANHO);
			}
			GeradorLogSefazMT.gerar(fichaImovelRuralRebanhoVo, DomnOperacao.OPERACAO_DELETE, fichaImovelRuralRebanhoVo.getNumeroParticao(), fichaImovelRuralRebanhoVo.getCodigoTransacao(), fichaImovelRuralRebanhoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementDeleteFichaImovelRuralRebanho(ps, fichaImovelRuralRebanhoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL_REBANHO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL_REBANHO);
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
	 * Método que monta o PreparedStatement de acordo com os dados válidos do FichaImovelRuralRebanhoVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralRebanhoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementDeleteFichaImovelRuralRebanho(final PreparedStatement ps, final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralRebanhoVo);
		int contador = 0;
		if (Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralRebanhoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}
}
