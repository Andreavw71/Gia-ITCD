package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota;
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
 * Classe de acesso a dados (Data Access Object)
 * @author Lucas Nascimento
 */
public class GIAITCDInventarioArrolamentoBeneficiarioAliquotaDao extends AbstractDao implements TabelasITC, CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota
{

	/**
	 * Contrutor da classe
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioAliquotaDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_GIA_ITCD_IVENTARIO_BENEFICIARIO_ALIQUOTA);
	}

	/**
	 * Retorna todos os campos da tabela de Iventario Arrolamento Beneficiario Aliquota (ITCTB44_BENEF_INVENT_ALIQUOTA)
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public String[] getCamposInventarioArrolamentoBeneficiarioAliquota()
	{
		return new String[] { CAMPO_CODIGO_BENEFICIARIO, CAMPO_CODIGO_ALIQUOTA, CAMPO_PERCENTUAL_ALIQUOTA, CAMPO_VALOR_RECOLHER, CAMPO_BASE_CALCULO };
	}

	/**
	 * Inclui informações sobre uma Aliquota de um Beneficiario do Iventário/Arrolamento  no banco de dados
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Lucas Nascimento
	 */
	public void insertGIAITCDIventarioArrolamentoBeneficiarioAliquota(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCamposInventarioArrolamentoBeneficiarioAliquota());
		try
		{
			GeradorLogSefazMT.gerar(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo, DomnOperacao.OPERACAO_INSERT, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getNumeroParticao(), giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getCodigoTransacao(), giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertGIAITCDIventarioArrolamentoBeneficiarioAliquota(ps, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA);
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
	 * Método responável por adicionar os parâmetros válidos na instrução SQL (java.sql.PreparedStatement)
	 * @param ps (java.sql.PreparedStatement)
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo (Value Object)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementInsertGIAITCDIventarioArrolamentoBeneficiarioAliquota(final PreparedStatement ps, final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		int contador = 0;
		//	   CAMPO_CODIGO_BENEFICIARIO,
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getGiaITCDIventarioArrolamentoBeneficiarioVo().getCodigo()))
		{
			ps.setLong(++contador, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getGiaITCDIventarioArrolamentoBeneficiarioVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	   CAMPO_CODIGO_ALIQUOTA,
			ps.setLong(++contador, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getCodigoAliquota());
		//	   CAMPO_PERCENTUAL_ALIQUOTA,
			ps.setDouble(++contador, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getPercentualAliquota());
		//	   CAMPO_VALOR_RECOLHER
			ps.setDouble(++contador, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getValorRecolher());
		//    CAMPO_BASE_CALCULO
			ps.setDouble(++contador, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getValorBaseCalculo());
	}

	/**
	 * Inclui informações sobre uma Aliquota de um Beneficiario do Iventário/Arrolamento  no banco de dados
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Lucas Nascimento
	 */
	public void updateGIAITCDIventarioArrolamentoBeneficiarioAliquota(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		PreparedStatement ps = null;
		String sql = 
				 utilStmt.geraUpdt(getCamposInventarioArrolamentoBeneficiarioAliquota(), new String[] { CAMPO_CODIGO_BENEFICIARIO, CAMPO_CODIGO_ALIQUOTA });
		try
		{
			GeradorLogSefazMT.gerar(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo, DomnOperacao.OPERACAO_UPDATE, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getNumeroParticao(), giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getCodigoTransacao(), giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateGIAITCDIventarioArrolamentoBeneficiarioAliquota(ps, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA);
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
	 * Método responável por adicionar os parâmetros válidos na instrução SQL (java.sql.PreparedStatement)
	 * @param ps (java.sql.PreparedStatement)
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo (Value Object)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementUpdateGIAITCDIventarioArrolamentoBeneficiarioAliquota(final PreparedStatement ps, final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		int contador = 0;
		//    CAMPO_PERCENTUAL_ALIQUOTA,
			ps.setDouble(++contador, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getPercentualAliquota());
		//    CAMPO_VALOR_RECOLHER
			ps.setDouble(++contador, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getValorRecolher());
		//    CAMPO_BASE_CALCULO
			ps.setDouble(++contador, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getValorBaseCalculo());
		//    CAMPO_CODIGO_BENEFICIARIO,
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getGiaITCDIventarioArrolamentoBeneficiarioVo().getCodigo()))
		{
			ps.setLong(++contador, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getGiaITCDIventarioArrolamentoBeneficiarioVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//    CAMPO_CODIGO_ALIQUOTA,
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getCodigoAliquota()))
		{
			ps.setLong(++contador, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getCodigoAliquota());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Exlui informações sobre uma Aliquota de um Beneficiario do Iventário/Arrolamento  no banco de dados
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Lucas Nascimento
	 */
	public void removeGIAITCDIventarioArrolamentoBeneficiarioAliquota(final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  ExcluiException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_CODIGO_BENEFICIARIO, CAMPO_CODIGO_ALIQUOTA });
		try
		{
			GeradorLogSefazMT.gerar(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo, DomnOperacao.OPERACAO_DELETE, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getNumeroParticao(), giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getCodigoTransacao(), giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementRemoveGIAITCDIventarioArrolamentoBeneficiarioAliquota(ps, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.EXCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.EXCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA);
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
	 * Método responável por adicionar os parâmetros válidos na instrução SQL (java.sql.PreparedStatement)
	 * @param ps (java.sql.PreparedStatement)
	 * @param giaITCDInventarioArrolamentoBeneficiarioAliquotaVo (Value Object)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementRemoveGIAITCDIventarioArrolamentoBeneficiarioAliquota(final PreparedStatement ps, final GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
		int contador = 0;
		//    CAMPO_CODIGO_BENEFICIARIO,
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getGiaITCDIventarioArrolamentoBeneficiarioVo().getCodigo()))
		{
			ps.setLong(++contador, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getGiaITCDIventarioArrolamentoBeneficiarioVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//    CAMPO_CODIGO_ALIQUOTA,
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getCodigoAliquota()))
		{
			ps.setLong(++contador, giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getCodigoAliquota());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}
}
