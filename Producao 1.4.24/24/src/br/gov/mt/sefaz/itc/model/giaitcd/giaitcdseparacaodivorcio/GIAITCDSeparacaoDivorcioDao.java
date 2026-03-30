package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDSeparacaoDivorcio;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.UtilStmt;


/**
 * Classe para manutenção de dados do GIA ITCD Separação/Divórcio
 * 
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.2 $
 */
public class GIAITCDSeparacaoDivorcioDao extends AbstractDao implements TabelasITC, CamposGIAITCDSeparacaoDivorcio, SequencesITC
{

	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public GIAITCDSeparacaoDivorcioDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_GIA_ITCD_SEPARACAO_DIVORCIO);
	}

	/**
	 * Método que retorna os campos da tabela de GIA ITCD Separação/Divórcio
	 * 
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private String[] getCampos()
	{
		return new String[]
		{
			CAMPO_ITCBTB14_CODIGO_ITCD
			,CAMPO_REGIME_CASAMENTO
			,CAMPO_NUMERO_PROCESSO
			,CAMPO_DATA_SEPARACAO
			,CAMPO_PESSOA_CONJUGE1
			,CAMPO_PESSOA_CONJUGE2
			,CAMPO_VALOR_ALIQUOTA
			,CAMPO_VALOR_TOTAL_CONJUGE1
			,CAMPO_VALOR_TOTAL_CONJUGE2
			,CAMPO_VALOR_INCIDENCIA
			,CAMPO_DATA_CASAMENTO
		};
	}

	/**
	 * Método para montar o PreparedStatement com os dados válidos do GIAITCDSeparacaoDivorcioVo
	 * 
	 * @param ps
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void preparedStatementInsertGIAITCDSeparacaoDivorcio(final PreparedStatement ps, final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		int contador = 0;
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getCodigo()))
		{
			ps.setLong(++contador, giaITCDSeparacaoDivorcioVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isDominioNumericoValido(giaITCDSeparacaoDivorcioVo.getRegimeCasamento()))
		{
			ps.setInt(++contador, giaITCDSeparacaoDivorcioVo.getRegimeCasamento().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getNumeroProcesso()))
		{
			ps.setLong(++contador, giaITCDSeparacaoDivorcioVo.getNumeroProcesso());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isDataValida(giaITCDSeparacaoDivorcioVo.getDataSeparacao()))
		{
			ps.setDate(++contador, new Date(giaITCDSeparacaoDivorcioVo.getDataSeparacao().getTime()));
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getPessoaConjuge1().getNumrContribuinte()))
		{
			ps.setLong(++contador, giaITCDSeparacaoDivorcioVo.getPessoaConjuge1().getNumrContribuinte().longValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getPessoaConjuge2().getNumrContribuinte()))
		{
			ps.setLong(++contador, giaITCDSeparacaoDivorcioVo.getPessoaConjuge2().getNumrContribuinte().longValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getValorAliquota()))
		{
			ps.setDouble(++contador, giaITCDSeparacaoDivorcioVo.getValorAliquota());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getValorTotalConjuge1()))
		{
			ps.setDouble(++contador, giaITCDSeparacaoDivorcioVo.getValorTotalConjuge1());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getValorTotalConjuge2()))
		{
			ps.setDouble(++contador, giaITCDSeparacaoDivorcioVo.getValorTotalConjuge2());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getValorIncidencia()))
		{
			ps.setDouble(++contador, giaITCDSeparacaoDivorcioVo.getValorIncidencia());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if(Validador.isDataValida(giaITCDSeparacaoDivorcioVo.getDataCasamento()))
		{
			ps.setDate(++contador, new Date(giaITCDSeparacaoDivorcioVo.getDataCasamento().getTime()));
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
		}
	}

	/**
	 * Método para inserir uma GIA ITCD Separação Divórcio
	 * 
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void insertGIAITCDSeparacaoDivorcio(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			GeradorLogSefazMT.gerar(giaITCDSeparacaoDivorcioVo, DomnOperacao.OPERACAO_INSERT, giaITCDSeparacaoDivorcioVo.getNumeroParticao(), giaITCDSeparacaoDivorcioVo.getCodigoTransacao(), giaITCDSeparacaoDivorcioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertGIAITCDSeparacaoDivorcio(ps, giaITCDSeparacaoDivorcioVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_SEPARACAO_DIVORCIO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_SEPARACAO_DIVORCIO);
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
	 * Método para montar o PreparedStatement com os dados válidos do GIAITCDSeparacaoDivorcioVo
	 * 
	 * @param ps
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Thiago de Castilho Pacheco
	 */
	private void preparedStatementUpdateGIAITCDSeparacaoDivorcio(final PreparedStatement ps, final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		int contador = 0;
		if (Validador.isDominioNumericoValido(giaITCDSeparacaoDivorcioVo.getRegimeCasamento()))
		{
			ps.setInt(++contador, giaITCDSeparacaoDivorcioVo.getRegimeCasamento().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getNumeroProcesso()))
		{
			ps.setLong(++contador, giaITCDSeparacaoDivorcioVo.getNumeroProcesso());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isDataValida(giaITCDSeparacaoDivorcioVo.getDataSeparacao()))
		{
			ps.setDate(++contador, new Date(giaITCDSeparacaoDivorcioVo.getDataSeparacao().getTime()));
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getPessoaConjuge1().getNumrContribuinte()))
		{
			ps.setLong(++contador, giaITCDSeparacaoDivorcioVo.getPessoaConjuge1().getNumrContribuinte().longValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getPessoaConjuge2().getNumrContribuinte()))
		{
			ps.setLong(++contador, giaITCDSeparacaoDivorcioVo.getPessoaConjuge2().getNumrContribuinte().longValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getValorAliquota()))
		{
			ps.setDouble(++contador, giaITCDSeparacaoDivorcioVo.getValorAliquota());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getValorTotalConjuge1()))
		{
			ps.setDouble(++contador, giaITCDSeparacaoDivorcioVo.getValorTotalConjuge1());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getValorTotalConjuge2()))
		{
			ps.setDouble(++contador, giaITCDSeparacaoDivorcioVo.getValorTotalConjuge2());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getValorIncidencia()))
		{
			ps.setDouble(++contador, giaITCDSeparacaoDivorcioVo.getValorIncidencia());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   if(Validador.isDataValida(giaITCDSeparacaoDivorcioVo.getDataCasamento()))
	   {
	      ps.setDate(++contador, new Date(giaITCDSeparacaoDivorcioVo.getDataCasamento().getTime()));
	   }
	   else
	   {
	      ps.setNull(++contador, Types.DATE);
	   }		
		if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getCodigo()))
		{
			ps.setLong(++contador, giaITCDSeparacaoDivorcioVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método para alterar uma GIA ITCD Separação Divórcio
	 * 
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public void updateGIAITCDSeparacaoDivorcio(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_ITCBTB14_CODIGO_ITCD });
		try
		{
			GeradorLogSefazMT.gerar(giaITCDSeparacaoDivorcioVo, DomnOperacao.OPERACAO_UPDATE, giaITCDSeparacaoDivorcioVo.getNumeroParticao(), giaITCDSeparacaoDivorcioVo.getCodigoTransacao(), giaITCDSeparacaoDivorcioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateGIAITCDSeparacaoDivorcio(ps, giaITCDSeparacaoDivorcioVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_SEPARACAO_DIVORCIO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_SEPARACAO_DIVORCIO);
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
