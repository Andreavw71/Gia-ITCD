/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDInventarioArrolamentoBeneficiarioDao.java
 * Revisão:
 * Data revisão:
 * $Id: GIAITCDInventarioArrolamentoBeneficiarioDao.java,v 1.3 2009/03/13 14:12:48 ricardo.moraes Exp $
 */
package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDInventarioArrolamentoBeneficiario;
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
 * @author Leandro Dorileo
 * @version $Revision: 1.3 $
 */
public class GIAITCDInventarioArrolamentoBeneficiarioDao extends AbstractDao implements CamposGIAITCDInventarioArrolamentoBeneficiario
{

	/**
	 * Construtor público recebendo uma conexao
	 * @param connection
	 */
	public GIAITCDInventarioArrolamentoBeneficiarioDao(Connection connection)
	{
		super(connection);
		utilStmt = new UtilStmt(TabelasITC.TABELA_GIA_ITCD_INVENTARIO_BENEFICIARIO);
	}

	/**
	 * Retorna uma matriz com os campos da tabela
	 * @return	matriz com os campos da tabela
	 */
	private String[] getCampos()
	{
		return new String[] 
		{
		   CAMPO_ITCTB05_CODIGO_BENEFICIARIO
			,CAMPO_INFO_QTD_UPF
			,CAMPO_VALR_RECOLHER
			,CAMPO_FLAG_BENEF_MEEIRO
			,CAMPO_VALOR_ITCD_BENEFICIARIO
			,CAMPO_VALOR_MULTA_RECOLHER
		};
	}

	/**
	 * Configura o prepared statement de insercao de uma GIAITCD do tipo Inventário
	 * e Arrolamento
	 * 
	 * @param ps		prepared statement a ser configurado
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 */
	private void preparedStatementInsertGIAITCDInventarioArrolamentoBeneficiario(final PreparedStatement ps, final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		int contador = 0;
	   //CAMPO_ITCTB05_CODIGO_BENEFICIARIO
	   if (Validador.isNumericoValido(giaITCDInventarioArrolamentoBeneficiarioVo.getCodigo()))
	   {
	      ps.setLong(++contador, giaITCDInventarioArrolamentoBeneficiarioVo.getCodigo());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
		//CAMPO_INFO_QTD_UPF
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoBeneficiarioVo.getInfoQuantidadeUpf()))
		{
			ps.setLong(++contador, giaITCDInventarioArrolamentoBeneficiarioVo.getInfoQuantidadeUpf());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//CAMPO_VALR_RECOLHER
		ps.setDouble(++contador, giaITCDInventarioArrolamentoBeneficiarioVo.getValorRecolher());
		//FLAG_BENEFICIARIO_MEEIRO
		if(Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoBeneficiarioVo.getFlagBeneficiarioMeeiro()))
		{
			ps.setInt(++contador, giaITCDInventarioArrolamentoBeneficiarioVo.getFlagBeneficiarioMeeiro().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//VALR_ITCD_BENEFICIARIO
		ps.setDouble(++contador, giaITCDInventarioArrolamentoBeneficiarioVo.getValorITCDBeneficiario());
		//VALR_MULTA_RECOLHER
		ps.setDouble(++contador, giaITCDInventarioArrolamentoBeneficiarioVo.getValorMultaRecolher());
	}

	/**
	 * Método responsável por persistir os dados de um beneficiário de uma GIA
	 * do tipo Inventário e Arrolamento.
	 *
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo	dados a serem persistidos
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 */
	public void insertGIAITCDInventarioArrolamentoBeneficiario(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			GeradorLogSefazMT.gerar(giaITCDInventarioArrolamentoBeneficiarioVo, DomnOperacao.OPERACAO_INSERT, giaITCDInventarioArrolamentoBeneficiarioVo.getNumeroParticao(), giaITCDInventarioArrolamentoBeneficiarioVo.getCodigoTransacao(), giaITCDInventarioArrolamentoBeneficiarioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertGIAITCDInventarioArrolamentoBeneficiario(ps, giaITCDInventarioArrolamentoBeneficiarioVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO);
		}
		/*catch (LogSefazException e)
	  {
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_DOACAO_BENEFICIARIO);
		}
		*/
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO);
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
	 * Configura o prepared statement de update de um beneficiário de uma gia
	 * do tipo Inventário e Arrolamento
	 * 
	 * @param ps		prepared statement a ser configurado
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo	dados a serem persistidos
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 */
	private void preparedStatementUpdateGIAITCDInventarioArrolamentoBeneficiario(final PreparedStatement ps, final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		int contador = 0;
	    //CAMPO_INFO_QTD_UPF
	       ps.setLong(++contador, giaITCDInventarioArrolamentoBeneficiarioVo.getInfoQuantidadeUpf());
	    //CAMPO_VALR_RECOLHER
	    ps.setDouble(++contador, giaITCDInventarioArrolamentoBeneficiarioVo.getValorRecolher());
	    //FLAG_BENEFICIARIO_MEEIRO
	    if(Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoBeneficiarioVo.getFlagBeneficiarioMeeiro()))
	    {
	       ps.setInt(++contador, giaITCDInventarioArrolamentoBeneficiarioVo.getFlagBeneficiarioMeeiro().getValorCorrente());
	    }
	    else
	    {
	       ps.setNull(++contador, Types.NUMERIC);
	    }
	    //VALR_ITCD_BENEFICIARIO
	    ps.setDouble(++contador, giaITCDInventarioArrolamentoBeneficiarioVo.getValorITCDBeneficiario());
	    //VALR_MULTA_RECOLHER
	    ps.setDouble(++contador, giaITCDInventarioArrolamentoBeneficiarioVo.getValorMultaRecolher());
	   //CAMPO_ITCTB05_CODIGO_BENEFICIARIO
	   if (Validador.isNumericoValido(giaITCDInventarioArrolamentoBeneficiarioVo.getCodigo()))
	   {
	      ps.setLong(++contador, giaITCDInventarioArrolamentoBeneficiarioVo.getCodigo());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
		
	}

	/**
	 * Modifica os dados persistidos de um beneficiário de uma GIA do tipo Inventário
	 * e Arrolamento
	 *
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo		dados a serem modificados
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 */
	public void updateGIAITCDInventarioArrolamentoBeneficiario(final GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_ITCTB05_CODIGO_BENEFICIARIO });
		try
		{
			GeradorLogSefazMT.gerar(giaITCDInventarioArrolamentoBeneficiarioVo, DomnOperacao.OPERACAO_UPDATE, giaITCDInventarioArrolamentoBeneficiarioVo.getNumeroParticao(), giaITCDInventarioArrolamentoBeneficiarioVo.getCodigoTransacao(), giaITCDInventarioArrolamentoBeneficiarioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateGIAITCDInventarioArrolamentoBeneficiario(ps, giaITCDInventarioArrolamentoBeneficiarioVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO);
		}
		/*catch (LogSefazException e)
	  {
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_DOACAO_BENEFICIARIO);
		}
		*/
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO);
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
	 * Método responsável por realizar o delete de um beneficiário da gia de inventario e arrolamento.
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @throws ExcluiException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void deleteGIAITCDInventarioArrolamentoBeneficiario(GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ExcluiException, 
			  ObjetoObrigatorioException
	{
	   Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
	   PreparedStatement ps = null;
	   String sql = utilStmt.geraDelt(new String[] {CAMPO_ITCTB05_CODIGO_BENEFICIARIO});
	   try
	   {
	      GeradorLogSefazMT.gerar(giaITCDInventarioArrolamentoBeneficiarioVo, DomnOperacao.OPERACAO_DELETE, giaITCDInventarioArrolamentoBeneficiarioVo.getNumeroParticao(), giaITCDInventarioArrolamentoBeneficiarioVo.getCodigoTransacao(), giaITCDInventarioArrolamentoBeneficiarioVo.getLogSefazVo().getUsuario().getCodigo(), conn);			
			ps = conn.prepareStatement(sql);
			preparedStatementDeleteGIAITCDInventarioArrolamentoBeneficiario(ps, giaITCDInventarioArrolamentoBeneficiarioVo);
			if(ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
	   }
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.ALTERAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO);
		}
		catch(Exception e)
		{
		   e.printStackTrace();
		   throw new ExcluiException(MensagemErro.ALTERAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO);			
		}
		finally
		{
			if(ps != null)
			{
				try
				{
					close(ps);
					ps = null;
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Configura o prepared statement de update de um beneficiário de uma gia
	 * do tipo Inventário e Arrolamento
	 * @param ps
	 * @param giaITCDInventarioArrolamentoBeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void preparedStatementDeleteGIAITCDInventarioArrolamentoBeneficiario(PreparedStatement ps, GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
	   Validador.validaObjeto(ps);
	   Validador.validaObjeto(giaITCDInventarioArrolamentoBeneficiarioVo);
	   int contador = 0;
	   // CODIGO BENEFICIARIO
	   if (Validador.isNumericoValido(giaITCDInventarioArrolamentoBeneficiarioVo.getCodigo()))
	   {
	      ps.setLong(++contador, giaITCDInventarioArrolamentoBeneficiarioVo.getCodigo());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }	
	}
}
