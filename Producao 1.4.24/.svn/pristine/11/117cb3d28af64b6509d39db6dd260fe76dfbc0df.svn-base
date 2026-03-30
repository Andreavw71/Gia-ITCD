package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDDoacao;
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
import sefaz.mt.util.UtilStmt;


/**
 * Classe para manutenção de dados do GIA ITCD Doação
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class GIAITCDDoacaoDao extends AbstractDao implements TabelasITC, SequencesITC, CamposGIAITCDDoacao
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_GIA_ITCD_DOACAO);
	}

	/**
	 * Método que retorna os campos da tabela de GIA ITCD Doação
	 * 
	 * @return String[]
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{
		return new String[] { CAMPO_ITCBTB14_CODIGO_ITCD, CAMPO_FRAC_IDEAL, CAMPO_BASE_CALCULO_REDUZIDA, CAMPO_TIPO_DOACAO, CAMPO_INFO_DOACAO_SUCESSIVA};
	}

	/**
	 * Método para montar o PreparedStatement com os dados válidos do GIAITCDDoacaoVo
	 * 
	 * @param ps
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertGIAITCDDoacao(final PreparedStatement ps, final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDDoacaoVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(giaITCDDoacaoVo.getCodigo()))
		{
			ps.setLong(++contador, giaITCDDoacaoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// FRACAO IDEAL
		if (Validador.isNumericoValido(giaITCDDoacaoVo.getFracaoIdeal()))
		{
			ps.setDouble(++contador, giaITCDDoacaoVo.getFracaoIdeal());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// BASE CALCULO REDUZIDA
		if (Validador.isNumericoValido(giaITCDDoacaoVo.getBaseCalculoReduzida()))
		{
			ps.setDouble(++contador, giaITCDDoacaoVo.getBaseCalculoReduzida());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// TIDO DOACAO
		if (Validador.isDominioNumericoValido(giaITCDDoacaoVo.getTipoDoacao()))
		{
			ps.setInt(++contador, giaITCDDoacaoVo.getTipoDoacao().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
      //INFO_DOACAO_SUCESSIVA
       if (Validador.isNumericoValido(giaITCDDoacaoVo.getFlagInfoDoacaoSucessiva()))
       {
          ps.setInt(++contador, giaITCDDoacaoVo.getFlagInfoDoacaoSucessiva());
       }
       else
       {
          ps.setNull(++contador, Types.NUMERIC);
       }
      
	}

	/**
	 * Método para inserir uma GIA ITCD Doação
	 * 
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertGIAITCDDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			GeradorLogSefazMT.gerar(giaITCDDoacaoVo, DomnOperacao.OPERACAO_INSERT, giaITCDDoacaoVo.getNumeroParticao(), giaITCDDoacaoVo.getCodigoTransacao(), giaITCDDoacaoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertGIAITCDDoacao(ps, giaITCDDoacaoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_DOACAO);
		}
		/*catch (LogSefazException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_DOACAO);
		}*/
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_DOACAO);
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
	 * Método para montar o PreparedStatement com os dados válidos do GIAITCDDoacaoVo
	 * 
	 * @param ps
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateGIAITCDDoacao(final PreparedStatement ps, final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDDoacaoVo);
		int contador = 0;
		// FRACAO IDEAL
		if (Validador.isNumericoValido(giaITCDDoacaoVo.getFracaoIdeal()))
		{
			ps.setDouble(++contador, giaITCDDoacaoVo.getFracaoIdeal());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// BASE CALCULO REDUZIDA
		if (Validador.isNumericoValido(giaITCDDoacaoVo.getBaseCalculoReduzida()))
		{
			ps.setDouble(++contador, giaITCDDoacaoVo.getBaseCalculoReduzida());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// TIDO DOACAO
		if (Validador.isDominioNumericoValido(giaITCDDoacaoVo.getTipoDoacao()))
		{
			ps.setInt(++contador, giaITCDDoacaoVo.getTipoDoacao().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// ITCD
		if (Validador.isNumericoValido(giaITCDDoacaoVo.getCodigo()))
		{
			ps.setLong(++contador, giaITCDDoacaoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método para alterar dados do GIA ITCD Doação no banco de dados
	 * 
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateGIAITCDDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_ITCBTB14_CODIGO_ITCD });
		try
		{
			GeradorLogSefazMT.gerar(giaITCDDoacaoVo, DomnOperacao.OPERACAO_UPDATE, giaITCDDoacaoVo.getNumeroParticao(), giaITCDDoacaoVo.getCodigoTransacao(), giaITCDDoacaoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateGIAITCDDoacao(ps, giaITCDDoacaoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_DOACAO);
		}
		/*catch (LogSefazException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_DOACAO);
		}*/
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_DOACAO);
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
