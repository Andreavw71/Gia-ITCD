package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralBenfeitoria;
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
 * Classe para acesso a dados do Ficha Imóvel Rural - Benfeitoria (Data Access Object)
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
public class FichaImovelRuralBenfeitoriaDao extends AbstractDao implements CamposFichaImovelRuralBenfeitoria, TabelasITC, SequencesITC
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBenfeitoriaDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_FICHA_IMOVEL_RURAL_BENFEITORIA);
	}

	/**
	 * Retorna os Campos da Tabela de Ficha Imóvel Rural - Benfeitoria
	 * 
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{
		return new String[] { CAMPO_CODIGO_IMOVEL_RURAL_BENFEITORIA, CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL, CAMPO_ITCTB07_CODIGO_BENFEITORIA, CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA, };
	}

	/**
	 * Método para inserir uma Ficha Imóvel Rural Benfeitoria no banco de dados
	 * 
	 * @param fichaImovelRuralBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertFichaImovelRuralBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			fichaImovelRuralBenfeitoriaVo.setCodigo(sequence.next(SEQUENCE_FICHA_IMOVEL_RURAL_BENFEITORIA));
			GeradorLogSefazMT.gerar(fichaImovelRuralBenfeitoriaVo, DomnOperacao.OPERACAO_INSERT, fichaImovelRuralBenfeitoriaVo.getNumeroParticao(), fichaImovelRuralBenfeitoriaVo.getCodigoTransacao(), fichaImovelRuralBenfeitoriaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertFichaImovelRuralBenfeitoria(ps, fichaImovelRuralBenfeitoriaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL_BENFEITORIA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL_BENFEITORIA);
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
	 * Método que monta o PreparedStatement com os dados válidos do FichaImovelRuralBenfeitoriaVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertFichaImovelRuralBenfeitoria(final PreparedStatement ps, FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(fichaImovelRuralBenfeitoriaVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralBenfeitoriaVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// IMOVEL RURAL
		if (Validador.isNumericoValido(fichaImovelRuralBenfeitoriaVo.getFichaImovelRuralVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralBenfeitoriaVo.getFichaImovelRuralVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// BENFEITORIA
		if (Validador.isNumericoValido(fichaImovelRuralBenfeitoriaVo.getBenfeitoriaVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralBenfeitoriaVo.getBenfeitoriaVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//DESCRICAO COMPLEMENTAR BENFEITORIA
		 if (Validador.isStringValida(fichaImovelRuralBenfeitoriaVo.getDescricaoComplementarBenfeitoria()))
		 {
		    ps.setString(++contador, fichaImovelRuralBenfeitoriaVo.getDescricaoComplementarBenfeitoria());
		 }
		 else
		 {
		    ps.setNull(++contador, Types.VARCHAR);
		 }
		
	}

	/**
	 * Método que atualiza a Ficha Imóvel Rural Benfeitoria no banco de dados.
	 * 
	 * @param fichaImovelRuralBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateFichaImovelRuralBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_CODIGO_IMOVEL_RURAL_BENFEITORIA });
		try
		{
			GeradorLogSefazMT.gerar(fichaImovelRuralBenfeitoriaVo, DomnOperacao.OPERACAO_UPDATE, fichaImovelRuralBenfeitoriaVo.getNumeroParticao(), fichaImovelRuralBenfeitoriaVo.getCodigoTransacao(), fichaImovelRuralBenfeitoriaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateFichaImovelRuralBenfeitoria(ps, fichaImovelRuralBenfeitoriaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_RURAL_BENFEITORIA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_RURAL_BENFEITORIA);
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
	 * Método que monta o PreparedStatement com os dados válidos do FichaImovelRuralBenfeitoriaVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateFichaImovelRuralBenfeitoria(final PreparedStatement ps, FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
		int contador = 0;
		// IMOVEL RURAL
		if (Validador.isNumericoValido(fichaImovelRuralBenfeitoriaVo.getFichaImovelRuralVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralBenfeitoriaVo.getFichaImovelRuralVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// BENFEITORIA
		if (Validador.isNumericoValido(fichaImovelRuralBenfeitoriaVo.getBenfeitoriaVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralBenfeitoriaVo.getBenfeitoriaVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   //DESCRICAO COMPLEMENTAR BENFEITORIA
	    if (Validador.isStringValida(fichaImovelRuralBenfeitoriaVo.getDescricaoComplementarBenfeitoria()))
	    {
	       ps.setString(++contador, fichaImovelRuralBenfeitoriaVo.getDescricaoComplementarBenfeitoria());
	    }
	    else
	    {
	       ps.setNull(++contador, Types.VARCHAR);
	    }		
		// CODIGO
		if (Validador.isNumericoValido(fichaImovelRuralBenfeitoriaVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralBenfeitoriaVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método para deletar uma Ficha Imóvel Rural Benfeitoria
	 * 
	 * @param fichaImovelRuralBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ExcluiException
	 * @implemented by Daniel Balieiro
	 */
	public void deleteFichaImovelRuralBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  ExcluiException
	{
		Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_CODIGO_IMOVEL_RURAL_BENFEITORIA });
		try
		{
			if (!Validador.isNumericoValido(fichaImovelRuralBenfeitoriaVo.getCodigo()))
			{
				throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL_BENFEITORIA);
			}
			GeradorLogSefazMT.gerar(fichaImovelRuralBenfeitoriaVo, DomnOperacao.OPERACAO_DELETE, fichaImovelRuralBenfeitoriaVo.getNumeroParticao(), fichaImovelRuralBenfeitoriaVo.getCodigoTransacao(), fichaImovelRuralBenfeitoriaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementDeleteFichaImovelRuralBenfeitoria(ps, fichaImovelRuralBenfeitoriaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL_BENFEITORIA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL_BENFEITORIA);
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
	 * Método que monta o PreparedStatement de acordo com os dados válidos do FichaImovelRuralBenfeitoriaVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementDeleteFichaImovelRuralBenfeitoria(final PreparedStatement ps, final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
		int contador = 0;
		if (Validador.isNumericoValido(fichaImovelRuralBenfeitoriaVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralBenfeitoriaVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}
}
