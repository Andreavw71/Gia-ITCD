package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDDoacaoBeneficiario;
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
 * Classe para manutenção de dados da Doação - Beneficiário
 * @author Daniel Balieiro
 * @implemented by Daniel Balieiro
 */
public class GIAITCDDoacaoBeneficiarioDao extends AbstractDao implements CamposGIAITCDDoacaoBeneficiario, TabelasITC
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * @param connection
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBeneficiarioDao(Connection connection)
	{
		super(connection);
		utilStmt = new UtilStmt(TABELA_GIA_ITCD_DOACAO_BENEFICIARIO);
	}

	/**
	 * Retorna os campos da tabela
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{
		return new String[] { CAMPO_ITCTB05_CODIGO_BENEFICIARIO, CAMPO_PERCENTUAL_ALIQUOTA, CAMPO_PERCENTUAL_BEM_RECEBIDO, CAMPO_VALOR_RECOLHER, CAMPO_INFO_ISENCAO, CAMPO_INFO_DSPE_RCLH };
	}

	/**
	 * Monta o Prepared Statement de acordo com os dados válidos do Vo
	 * @param ps
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertGIAITCDDoacaoBeneficiario(final PreparedStatement ps, final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		int contador = 0;
		// CODIGO BENEFICIARIO
		if (Validador.isNumericoValido(giaITCDDoacaoBeneficiarioVo.getCodigo()))
		{
			ps.setLong(++contador, giaITCDDoacaoBeneficiarioVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// PERCENTUAL ALIQUOTA
		if (Validador.isNumericoValido(giaITCDDoacaoBeneficiarioVo.getPercentualAliquota()))
		{
			ps.setDouble(++contador, giaITCDDoacaoBeneficiarioVo.getPercentualAliquota());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// PERCENTUAL BEM RECEBIDO
		if (Validador.isNumericoValido(giaITCDDoacaoBeneficiarioVo.getPercentualBemRecebido()))
		{
			ps.setDouble(++contador, giaITCDDoacaoBeneficiarioVo.getPercentualBemRecebido());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR RECOLHER
		if (Validador.isNumericoValido(giaITCDDoacaoBeneficiarioVo.getValorRecolher()))
		{
			ps.setDouble(++contador, giaITCDDoacaoBeneficiarioVo.getValorRecolher());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   // INFO_ISENCAO
	   if (Validador.isNumericoValido(giaITCDDoacaoBeneficiarioVo.getInfoIsencao()))
	   {
	      ps.setInt(++contador, giaITCDDoacaoBeneficiarioVo.getInfoIsencao());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }      
	   // INFO_DSPE_RCLH
	   if (Validador.isNumericoValido(giaITCDDoacaoBeneficiarioVo.getInfoDispensaRecolhimento()))
	   {
	      ps.setInt(++contador, giaITCDDoacaoBeneficiarioVo.getInfoDispensaRecolhimento());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
      
	}

	/**
	 * Inclui uma GIA-ITCD de Doação Beneficiário
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertGIAITCDDoacaoBeneficiario(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			GeradorLogSefazMT.gerar(giaITCDDoacaoBeneficiarioVo, DomnOperacao.OPERACAO_INSERT, giaITCDDoacaoBeneficiarioVo.getNumeroParticao(), giaITCDDoacaoBeneficiarioVo.getCodigoTransacao(), giaITCDDoacaoBeneficiarioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertGIAITCDDoacaoBeneficiario(ps, giaITCDDoacaoBeneficiarioVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_DOACAO_BENEFICIARIO);
		}
		/*		catch (LogSefazException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_DOACAO_BENEFICIARIO);
		}*/
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_DOACAO_BENEFICIARIO);
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
	 * Monta o Prepared Statement para atualizar uma GIA-ITCD com os dados válidos do Vo
	 * @param ps
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateGIAITCDDoacaoBeneficiario(final PreparedStatement ps, final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		int contador = 0;
		// PERCENTUAL ALIQUOTA
		if (Validador.isNumericoValido(giaITCDDoacaoBeneficiarioVo.getPercentualAliquota()))
		{
			ps.setDouble(++contador, giaITCDDoacaoBeneficiarioVo.getPercentualAliquota());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// PERCENTUAL BEM RECEBIDO
		if (Validador.isNumericoValido(giaITCDDoacaoBeneficiarioVo.getPercentualBemRecebido()))
		{
			ps.setDouble(++contador, giaITCDDoacaoBeneficiarioVo.getPercentualBemRecebido());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR RECOLHER
		if (Validador.isNumericoValido(giaITCDDoacaoBeneficiarioVo.getValorRecolher()))
		{
			ps.setDouble(++contador, giaITCDDoacaoBeneficiarioVo.getValorRecolher());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CODIGO BENEFICIARIO
		if (Validador.isNumericoValido(giaITCDDoacaoBeneficiarioVo.getCodigo()))
		{
			ps.setLong(++contador, giaITCDDoacaoBeneficiarioVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   // INFO_ISENCAO
	   if (Validador.isNumericoValido(giaITCDDoacaoBeneficiarioVo.getInfoIsencao()))
	   {
	      ps.setInt(++contador, giaITCDDoacaoBeneficiarioVo.getInfoIsencao());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }      
	   // INFO_DSPE_RCLH
	   if (Validador.isNumericoValido(giaITCDDoacaoBeneficiarioVo.getInfoDispensaRecolhimento()))
	   {
	      ps.setInt(++contador, giaITCDDoacaoBeneficiarioVo.getInfoDispensaRecolhimento());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	}

	/**
	 * Método para atualizar o GIA-ITCD Doação Beneficiário
	 * @param giaITCDDoacaoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateGIAITCDDoacaoBeneficiario(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_ITCTB05_CODIGO_BENEFICIARIO });
		try
		{
			GeradorLogSefazMT.gerar(giaITCDDoacaoBeneficiarioVo, DomnOperacao.OPERACAO_UPDATE, giaITCDDoacaoBeneficiarioVo.getNumeroParticao(), giaITCDDoacaoBeneficiarioVo.getCodigoTransacao(), giaITCDDoacaoBeneficiarioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateGIAITCDDoacaoBeneficiario(ps, giaITCDDoacaoBeneficiarioVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_DOACAO_BENEFICIARIO);
		}
		//		catch (LogSefazException e)
		//		{
		//			e.printStackTrace();
		//			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_DOACAO_BENEFICIARIO);
		//		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_DOACAO_BENEFICIARIO);
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

	public void deleteGIAITCDDoacaoBeneficiario(final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  ExcluiException
	{
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_ITCTB05_CODIGO_BENEFICIARIO });
		try
		{
			GeradorLogSefazMT.gerar(giaITCDDoacaoBeneficiarioVo, DomnOperacao.OPERACAO_DELETE, giaITCDDoacaoBeneficiarioVo.getNumeroParticao(), giaITCDDoacaoBeneficiarioVo.getCodigoTransacao(), giaITCDDoacaoBeneficiarioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementDeleteGIAITCDDoacaoBeneficiario(ps, giaITCDDoacaoBeneficiarioVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.ALTERAR_GIA_ITCD_DOACAO_BENEFICIARIO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.ALTERAR_GIA_ITCD_DOACAO_BENEFICIARIO);
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

	private void preparedStatementDeleteGIAITCDDoacaoBeneficiario(final PreparedStatement ps, final GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDDoacaoBeneficiarioVo);
		int contador = 0;
		// CODIGO BENEFICIARIO
		if (Validador.isNumericoValido(giaITCDDoacaoBeneficiarioVo.getCodigo()))
		{
			ps.setLong(++contador, giaITCDDoacaoBeneficiarioVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}	
}
