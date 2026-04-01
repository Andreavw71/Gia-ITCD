package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnEstadoConservacao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRuralConstrucao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe para manipular consultas de Ficha Imóvel Rural Construção no banco de dados
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class FichaImovelRuralConstrucaoQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposFichaImovelRuralConstrucao
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metódo que constroi uma FichaImovelRuralConstrucaoVo com os dados do ResultSet
	 * 
	 * @param rs
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @return FichaImovelRuralConstrucaoVo
	 * @implemented by Daniel Balieiro
	 */
	private FichaImovelRuralConstrucaoVo getFichaImovelRuralConstrucao(final ResultSet rs, final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		fichaImovelRuralConstrucaoVo.setCodigo(rs.getLong(CAMPO_CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO));
		fichaImovelRuralConstrucaoVo.getConstrucaoVo().setCodigo(rs.getLong(CAMPO_ITCTB13_CODIGO_TIPO_CONSTRUCAO));
		fichaImovelRuralConstrucaoVo.getFichaImovelRuralVo().setCodigo(rs.getLong(CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL));
		fichaImovelRuralConstrucaoVo.setDescricaoConstrucao(rs.getString(CAMPO_DESCRICAO_CONSTRUCAO));
		fichaImovelRuralConstrucaoVo.setSituacaoEstadoConservacao(new DomnEstadoConservacao(rs.getInt(CAMPO_SITUACAO_ESTADO_CONSERVACAO)));
		fichaImovelRuralConstrucaoVo.setValorMercado(rs.getDouble(CAMPO_VALOR_MERCADO));
		return fichaImovelRuralConstrucaoVo;
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
	private void prepareStatementFindFichaImovelRuralConstrucao(final PreparedStatement ps, final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		int contador = 0;
		if (fichaImovelRuralConstrucaoVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getCodigo());
			}
			// CONSTRUCAO		
			if (Validador.isNumericoValido(((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getConstrucaoVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getConstrucaoVo().getCodigo());
			}
			// IMOVEL RURAL
			if (Validador.isNumericoValido(((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo());
			}
			// DESCRICAO CONSTRUCAO
			if (Validador.isStringValida(((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getDescricaoConstrucao()))
			{
				ps.setString(++contador, ((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getDescricaoConstrucao());
			}
			// SITUACAO ESTADO CONSERVACAO
			if (Validador.isDominioNumericoValido(((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getSituacaoEstadoConservacao()))
			{
				ps.setInt(++contador, ((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getSituacaoEstadoConservacao().getValorCorrente());
			}
			// VALOR MERCADO
			if (Validador.isNumericoValido(((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getValorMercado()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getValorMercado());
			}
		}
	}

	/**
	 * Método de consulta de uma Ficha Imóvel Rural Construção. <br>
	 * Caso mais de uma Ficha seja encontrada, será retornada a de menor código.<br><br>
	 * Os dados possíveis para consulta são:<br>
	 * <b>- fichaImovelRuralConstrucaoVo.codigo</b><br>
	 * <b>- fichaImovelRuralConstrucaoVo.construcaoVo.codigo</b><br>
	 * <b>- fichaImovelRuralConstrucaoVo.fichaImovelRuralVo.codigo</b><br>
	 * 
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return FichaImovelRuralConstrucaoVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoVo findFichaImovelRuralConstrucao(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindFichaImovelRuralConstrucao(fichaImovelRuralConstrucaoVo));
			prepareStatementFindFichaImovelRuralConstrucao(ps, fichaImovelRuralConstrucaoVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getFichaImovelRuralConstrucao(rs, fichaImovelRuralConstrucaoVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_FICHA_IMOVEL_RURAL_CONSTRUCAO);
		}
		finally
		{
			try
			{
				close(ps, rs);
				ps = null;
				rs = null;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return fichaImovelRuralConstrucaoVo;
	}

	/**
	 * Método para montar a SQL de consulta de uma Ficha Imóvel Rural Construção
	 * 
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindFichaImovelRuralConstrucao(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT FIR.").append(CAMPO_CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO);
		sql.append(" , FIR.").append(CAMPO_ITCTB13_CODIGO_TIPO_CONSTRUCAO);
		sql.append(" , FIR.").append(CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL);
		sql.append(" , FIR.").append(CAMPO_DESCRICAO_CONSTRUCAO);
		sql.append(" , FIR.").append(CAMPO_SITUACAO_ESTADO_CONSERVACAO);
		sql.append(" , FIR.").append(CAMPO_VALOR_MERCADO);
		sql.append(" FROM ").append(TABELA_FICHA_IMOVEL_RURAL_CONSTRUCAO).append(" FIR ");
		sql.append(" WHERE 1 = 1 ");
		if (fichaImovelRuralConstrucaoVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND FIR.").append(CAMPO_CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO).append(" = ? ");
			}
			// CONSTRUCAO
			if (Validador.isNumericoValido(((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getConstrucaoVo().getCodigo()))
			{
				sql.append("   AND FIR.").append(CAMPO_ITCTB13_CODIGO_TIPO_CONSTRUCAO).append(" = ? ");
			}
			// IMOVEL RURAL
			if (Validador.isNumericoValido(((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getFichaImovelRuralVo().getCodigo()))
			{
				sql.append("   AND FIR.").append(CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL).append(" = ? ");
			}
			// DESCRICAO CONSTRUCAO
			if (Validador.isStringValida(((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getDescricaoConstrucao()))
			{
				sql.append("   AND FIR.").append(CAMPO_DESCRICAO_CONSTRUCAO).append(" = ? ");
			}
			// SITUACAO ESTADO CONSERVACAO
			if (Validador.isDominioNumericoValido(((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getSituacaoEstadoConservacao()))
			{
				sql.append("   AND FIR.").append(CAMPO_SITUACAO_ESTADO_CONSERVACAO).append(" = ? ");
			}
			// VALOR MERCADO
			if (Validador.isNumericoValido(((FichaImovelRuralConstrucaoVo) fichaImovelRuralConstrucaoVo.getParametroConsulta()).getValorMercado()))
			{
				sql.append("   AND FIR.").append(CAMPO_VALOR_MERCADO).append(" = ? ");
			}
		}
		sql.append(" ORDER BY FIR.").append(CAMPO_CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO);
		return sql.toString();
	}

	/**
	 * Método de consulta de Ficha Imóvel Rural Construção. <br><br>
	 * Os dados possíveis para consulta são:<br>
	 * <b>- fichaImovelRuralConstrucaoVo.codigo</b><br>
	 * <b>- fichaImovelRuralConstrucaoVo.construcaoVo.codigo</b><br>
	 * <b>- fichaImovelRuralConstrucaoVo.fichaImovelRuralVo.codigo</b><br>
	 * 
	 * @param fichaImovelRuralConstrucaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return FichaImovelRuralConstrucaoVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoVo listFichaImovelRuralConstrucao(final FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelRuralConstrucaoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindFichaImovelRuralConstrucao(fichaImovelRuralConstrucaoVo));
			prepareStatementFindFichaImovelRuralConstrucao(ps, fichaImovelRuralConstrucaoVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoAtualVo = new FichaImovelRuralConstrucaoVo();
				getFichaImovelRuralConstrucao(rs, fichaImovelRuralConstrucaoAtualVo);
				fichaImovelRuralConstrucaoVo.getCollVO().add(fichaImovelRuralConstrucaoAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_FICHA_IMOVEL_RURAL_CONSTRUCAO);
		}
		finally
		{
			try
			{
				close(ps, rs);
				ps = null;
				rs = null;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return fichaImovelRuralConstrucaoVo;
	}
}
