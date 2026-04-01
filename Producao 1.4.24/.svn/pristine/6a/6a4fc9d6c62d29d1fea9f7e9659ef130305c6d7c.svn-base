package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralCultura;
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
 * Classe para acesso a dados do Ficha Imóvel Rural - Cultura (Data Access Object)
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.3 $
 */
public class FichaImovelRuralCulturaDao extends AbstractDao implements CamposFichaImovelRuralCultura, TabelasITC, SequencesITC
{
	/**
	 * Contrutor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_FICHA_IMOVEL_RURAL_CULTURA);
	}

	/**
	 * Retorna os campos da tabela de Ficha Imóvel Rural - Cultura
	 * 
	 * @return String[]
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{
		return new String[] 
		{ 
			CAMPO_CODIGO_IMOVEL_RURAL_CULTURA
			,CAMPO_ITCTB08_CODIGO_CULTURA
			,CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL
			,CAMPO_DESCRICAO_COMPLEMENTAR_CULTURA
			,CAMPO_AREA_CULTIVADA
			,CAMPO_VALOR_HECTARE
			,CAMPO_VALOR_MERCADO };
	}

	/**
	 * Método para incluir uma Ficha Imóvel Rural Cultura
	 * 
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertFichaImovelRuralCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			fichaImovelRuralCulturaVo.setCodigo(sequence.next(SEQUENCE_FICHA_IMOVEL_RURAL_CULTURA));
			GeradorLogSefazMT.gerar(fichaImovelRuralCulturaVo, DomnOperacao.OPERACAO_INSERT, fichaImovelRuralCulturaVo.getNumeroParticao(), fichaImovelRuralCulturaVo.getCodigoTransacao(), fichaImovelRuralCulturaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertFichaImovelRuralCultura(ps, fichaImovelRuralCulturaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL_CULTURA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL_CULTURA);
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
	 * Método para montar o PreparedStatement de acordo com os dados válidos FichaImovelRuralCulturaVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertFichaImovelRuralCultura(final PreparedStatement ps, FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(fichaImovelRuralCulturaVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralCulturaVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CULTURA
		if (Validador.isNumericoValido(fichaImovelRuralCulturaVo.getCulturaVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralCulturaVo.getCulturaVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// FICHA IMOVEL RURAL
		if (Validador.isNumericoValido(fichaImovelRuralCulturaVo.getFichaImovelRuralVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralCulturaVo.getFichaImovelRuralVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//DESCRICAO_COMPLEMENTAR_CULTURA
		if(Validador.isStringValida(fichaImovelRuralCulturaVo.getDescricaoComplementarCultura()))
		{
			ps.setString(++contador, fichaImovelRuralCulturaVo.getDescricaoComplementarCultura());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// AREA CULTIVADA
		if (Validador.isNumericoValido(fichaImovelRuralCulturaVo.getAreaCultivada()))
		{
			ps.setDouble(++contador, fichaImovelRuralCulturaVo.getAreaCultivada());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//VALOR HECTARE
		 if(Validador.isNumericoValido(fichaImovelRuralCulturaVo.getValorHectare()))
		 {
		    ps.setDouble(++contador, fichaImovelRuralCulturaVo.getValorHectare());
		 }
		 else
		 {
		    ps.setNull(++contador, Types.NUMERIC);
		 }		
		// VALOR MERCADO
		if (Validador.isNumericoValido(fichaImovelRuralCulturaVo.getValorMercado()))
		{
			ps.setDouble(++contador, fichaImovelRuralCulturaVo.getValorMercado());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método para atualizar uma Ficha Imóvel Rural Cultura
	 * 
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateFichaImovelRuralCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_CODIGO_IMOVEL_RURAL_CULTURA });
		try
		{
			GeradorLogSefazMT.gerar(fichaImovelRuralCulturaVo, DomnOperacao.OPERACAO_UPDATE, fichaImovelRuralCulturaVo.getNumeroParticao(), fichaImovelRuralCulturaVo.getCodigoTransacao(), fichaImovelRuralCulturaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateFichaImovelRuralCultura(ps, fichaImovelRuralCulturaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_RURAL_CULTURA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_RURAL_CULTURA);
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
	 * Método para montar o PreparedStatement de acordo com os dados válidos do FichaImovelRuralCulturaVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateFichaImovelRuralCultura(final PreparedStatement ps, FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		int contador = 0;
		// CULTURA
		if (Validador.isNumericoValido(fichaImovelRuralCulturaVo.getCulturaVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralCulturaVo.getCulturaVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// FICHA IMOVEL RURAL
		if (Validador.isNumericoValido(fichaImovelRuralCulturaVo.getFichaImovelRuralVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralCulturaVo.getFichaImovelRuralVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   //DESCRICAO_COMPLEMENTAR_CULTURA
	   if(Validador.isStringValida(fichaImovelRuralCulturaVo.getDescricaoComplementarCultura()))
	   {
	      ps.setString(++contador, fichaImovelRuralCulturaVo.getDescricaoComplementarCultura());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.VARCHAR);
	   }		
		// AREA CULTIVADA
		if (Validador.isNumericoValido(fichaImovelRuralCulturaVo.getAreaCultivada()))
		{
			ps.setDouble(++contador, fichaImovelRuralCulturaVo.getAreaCultivada());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   //VALOR HECTARE
	    if(Validador.isNumericoValido(fichaImovelRuralCulturaVo.getValorHectare()))
	    {
	       ps.setDouble(++contador, fichaImovelRuralCulturaVo.getValorHectare());
	    }
	    else
	    {
	       ps.setNull(++contador, Types.NUMERIC);
	    }    		
		// VALOR MERCADO
		if (Validador.isNumericoValido(fichaImovelRuralCulturaVo.getValorMercado()))
		{
			ps.setDouble(++contador, fichaImovelRuralCulturaVo.getValorMercado());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CODIGO
		if (Validador.isNumericoValido(fichaImovelRuralCulturaVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralCulturaVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método para deletar uma Ficha Imóvel Rural Cultura do banco de dados
	 * 
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ExcluiException
	 * @implemented by Daniel Balieiro
	 */
	public void deleteFichaImovelRuralCultura(final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  ExcluiException
	{
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_CODIGO_IMOVEL_RURAL_CULTURA });
		try
		{
			if (!Validador.isNumericoValido(fichaImovelRuralCulturaVo.getCodigo()))
			{
				throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL_CULTURA);
			}
			GeradorLogSefazMT.gerar(fichaImovelRuralCulturaVo, DomnOperacao.OPERACAO_DELETE, fichaImovelRuralCulturaVo.getNumeroParticao(), fichaImovelRuralCulturaVo.getCodigoTransacao(), fichaImovelRuralCulturaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementDeleteFichaImovelRuralCultura(ps, fichaImovelRuralCulturaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL_CULTURA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL_CULTURA);
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
	 * Método para montar o PreparedStatement de acordo com os dados válidos do FichaImovelRuralCulturaVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralCulturaVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementDeleteFichaImovelRuralCultura(final PreparedStatement ps, final FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralCulturaVo);
		int contador = 0;
		if (Validador.isNumericoValido(fichaImovelRuralCulturaVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralCulturaVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}
}
