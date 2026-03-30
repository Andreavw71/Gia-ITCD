package br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposNaturezaOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *  Classe de acesso a dados (Data Access Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class NaturezaOperacaoQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposNaturezaOperacao
{
	/**
	 * Construtora da Classe
	 * @param conexao Conexão que será utilizada para a manutenção dos dados, junto ao banco de dados
	 * @implemented by Daniel Balieiro
	 */
	public NaturezaOperacaoQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Alimenta o NaturezaOperacaoVo com os dados o ResultSet
	 * @param rs Contém os dados para alimentar o NaturezaOperacaoVo
	 * @param naturezaOperacaoVo VO que será alimentado
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public void getNaturezaOperacaoVo(final ResultSet rs, final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		try
		{
			Validador.validaObjeto(rs);
			Validador.validaObjeto(naturezaOperacaoVo);
			naturezaOperacaoVo.setCodigo(rs.getLong(CAMPO_CODIGO_NATUREZA_OPERACAO));
			naturezaOperacaoVo.setDescricaoNaturezaOperacao(rs.getString(CAMPO_DESCRICAO_NATUREZA_OPERACAO));
			naturezaOperacaoVo.setPercentualBaseCalculo(rs.getDouble(CAMPO_PERCENTUAL_NATUREZA_OPERACAO));
			naturezaOperacaoVo.setTipoGIA(new DomnTipoGIA(rs.getInt(CAMPO_TIPO_GIA)));
			naturezaOperacaoVo.setTipoBaseCalculo(new DomnSimNao(rs.getInt(CAMPO_TIPO_BASE_CALCULO)));
			naturezaOperacaoVo.setTipoProcesso(new DomnTipoProcesso(rs.getInt(CAMPO_TIPO_PROCESSO)));
			naturezaOperacaoVo.setStatusNaturezaOperacao(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_NATUREZA_OPERACAO)));
			naturezaOperacaoVo.setDataAtualizacao(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
			naturezaOperacaoVo.setFlagIsencaoPrevistaLei(new DomnSimNao(rs.getInt(CAMPO_FLAG_ISENCAO_PREVISTA_LEI)));
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método utilizado para buscar dados de uma determinada NaturezaOperacao.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * a NaturezaOperacao de menor código cadastrado no banco de dados.
	 * <br>Esta pesquisa pode ser realizada pelos seguintes filtros:
	 * <br>- naturezaOperacaoVo.codigo
	 * @param naturezaOperacaoVo Com os dados a serem considerados na Consulta
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return NaturezaOperacaoVo - Encontrada
	 * @implemented by Daniel Balieiro
	 */
	public NaturezaOperacaoVo findNaturezaOperacaoVo(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindNaturezaOperacaoVo(naturezaOperacaoVo));
			prepareStatemantFindNaturezaOperacaoVo(ps, naturezaOperacaoVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getNaturezaOperacaoVo(rs, naturezaOperacaoVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_NATUREZA_OPERACAO);
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
		return naturezaOperacaoVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement os parâmetros de pesquisa possíveis ao Consultar NaturezaOperacaoVo
	 * @param ps PreparedStatement que receberá as informações
	 * @param naturezaOperacaoVo Que contém os dados
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatemantFindNaturezaOperacaoVo(final PreparedStatement ps, final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(naturezaOperacaoVo);
		try
		{
			int contador = 0;
			if (naturezaOperacaoVo.isConsultaParametrizada())
			{
				if (Validador.isNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getCodigo()))
				{
					ps.setLong(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getCodigo());
				}
				if (Validador.isStringValida(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getDescricaoNaturezaOperacao()))
				{
					ps.setString(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getDescricaoNaturezaOperacao());
				}
				if (Validador.isNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getPercentualBaseCalculo()))
				{
					ps.setDouble(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getPercentualBaseCalculo());
				}
				if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoGIA()))
				{
					ps.setInt(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoGIA().getValorCorrente());
				}
				if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoBaseCalculo()))
				{
					ps.setInt(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoBaseCalculo().getValorCorrente());
				}
				if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoProcesso()))
				{
					ps.setInt(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoProcesso().getValorCorrente());
				}
				if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getStatusNaturezaOperacao()))
				{
					ps.setInt(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getStatusNaturezaOperacao().getValorCorrente());
				}
			   if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getFlagIsencaoPrevistaLei()))
			   {
			      ps.setInt(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getFlagIsencaoPrevistaLei().getValorCorrente());
			   }				
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método para gerar o SQL de consulta apartir de um NaturezaOperacaoVo
	 * @param naturezaOperacaoVo NaturezaOperacaoVo que será consultado para gerar o SQL
	 * @throws ObjetoObrigatorioException
	 * @return String - SQL Gerada
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindNaturezaOperacaoVo(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT NATUREZA_OPERACAO." + CAMPO_CODIGO_NATUREZA_OPERACAO + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_DESCRICAO_NATUREZA_OPERACAO + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_PERCENTUAL_NATUREZA_OPERACAO + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_TIPO_GIA + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_TIPO_BASE_CALCULO + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_TIPO_PROCESSO + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_STATUS_NATUREZA_OPERACAO + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_DATA_ATUALIZACAO_BD + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_FLAG_ISENCAO_PREVISTA_LEI + " ");
		sql.append(" FROM " + TABELA_NATUREZA_OPERACAO + " NATUREZA_OPERACAO ");
		sql.append(" WHERE 1=1 ");
		if (naturezaOperacaoVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND NATUREZA_OPERACAO." + CAMPO_CODIGO_NATUREZA_OPERACAO + " = ? ");
			}
			if (Validador.isStringValida(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getDescricaoNaturezaOperacao()))
			{
				sql.append(" AND UPPER(NATUREZA_OPERACAO." + CAMPO_DESCRICAO_NATUREZA_OPERACAO + " ) = UPPER(?) ");
			}
			if (Validador.isNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getPercentualBaseCalculo()))
			{
				sql.append(" AND NATUREZA_OPERACAO." + CAMPO_PERCENTUAL_NATUREZA_OPERACAO + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoGIA()))
			{
				sql.append(" AND NATUREZA_OPERACAO." + CAMPO_TIPO_GIA + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoBaseCalculo()))
			{
				sql.append(" AND NATUREZA_OPERACAO." + CAMPO_TIPO_BASE_CALCULO + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoProcesso()))
			{
				sql.append(" AND NATUREZA_OPERACAO." + CAMPO_TIPO_PROCESSO + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getStatusNaturezaOperacao()))
			{
				sql.append(" AND NATUREZA_OPERACAO." + CAMPO_STATUS_NATUREZA_OPERACAO + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getFlagIsencaoPrevistaLei()))
			{
				sql.append(" AND NATUREZA_OPERACAO." + CAMPO_FLAG_ISENCAO_PREVISTA_LEI + " = ? ");
			}
			sql.append(" ORDER BY NATUREZA_OPERACAO." + CAMPO_CODIGO_NATUREZA_OPERACAO + " ");
		}
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de várias NaturezaOperacaos.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todas as NaturezaOperacaos do banco de dados.
	 * <br>Esta pesquisa pode ser realizada pelos seguintes filtros:
	 * <br>- naturezaOperacaoVo.codigo
	 * @param naturezaOperacaoVo Com os dados a serem considerados na Consulta
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return NaturezaOperacaoVo - Encontrada
	 * @implemented by Daniel Balieiro
	 */
	public NaturezaOperacaoVo listNaturezaOperacaoVo(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListNaturezaOperacaoVo(naturezaOperacaoVo));
			prepareStatemantListNaturezaOperacaoVo(ps, naturezaOperacaoVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				NaturezaOperacaoVo naturezaOperacaoAtualVo = new NaturezaOperacaoVo();
				getNaturezaOperacaoVo(rs, naturezaOperacaoAtualVo);
				naturezaOperacaoVo.getCollVO().add(naturezaOperacaoAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_NATUREZA_OPERACAO);
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
		return naturezaOperacaoVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement os parâmetros de pesquisa possíveis ao Consultar NaturezaOperacaoVo
	 * @param ps PreparedStatement que receberá as informações
	 * @param naturezaOperacaoVo Que contém os dados
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatemantListNaturezaOperacaoVo(final PreparedStatement ps, final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(naturezaOperacaoVo);
		try
		{
			int contador = 0;
			if (naturezaOperacaoVo.isConsultaParametrizada())
			{
				if (Validador.isNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getCodigo()))
				{
					ps.setLong(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getCodigo());
				}
				if (Validador.isNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getPercentualBaseCalculo()))
				{
					ps.setDouble(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getPercentualBaseCalculo());
				}
				if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoGIA()))
				{
					ps.setInt(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoGIA().getValorCorrente());
				}
				if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoBaseCalculo()))
				{
					ps.setInt(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoBaseCalculo().getValorCorrente());
				}
				if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoProcesso()))
				{
					ps.setInt(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoProcesso().getValorCorrente());
				}
				if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getStatusNaturezaOperacao()))
				{
					ps.setInt(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getStatusNaturezaOperacao().getValorCorrente());
				}
			   if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getFlagIsencaoPrevistaLei()))
			   {
			      ps.setInt(++contador, ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getFlagIsencaoPrevistaLei().getValorCorrente());
			   }           				
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método para gerar o SQL de consulta apartir de um NaturezaOperacaoVo
	 * @param naturezaOperacaoVo NaturezaOperacaoVo que será consultado para gerar o SQL
	 * @throws ObjetoObrigatorioException
	 * @return String - SQL Gerada
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListNaturezaOperacaoVo(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT NATUREZA_OPERACAO." + CAMPO_CODIGO_NATUREZA_OPERACAO + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_DESCRICAO_NATUREZA_OPERACAO + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_PERCENTUAL_NATUREZA_OPERACAO + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_TIPO_GIA + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_TIPO_BASE_CALCULO + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_TIPO_PROCESSO + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_STATUS_NATUREZA_OPERACAO + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_DATA_ATUALIZACAO_BD + ", ");
		sql.append(" NATUREZA_OPERACAO." + CAMPO_FLAG_ISENCAO_PREVISTA_LEI + " ");		
		sql.append(" FROM " + TABELA_NATUREZA_OPERACAO + " NATUREZA_OPERACAO ");
		sql.append(" WHERE 1=1 ");
		if (naturezaOperacaoVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND NATUREZA_OPERACAO." + CAMPO_CODIGO_NATUREZA_OPERACAO + " = ? ");
			}
			if (Validador.isStringValida(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getDescricaoNaturezaOperacao()))
			{
				sql.append(" AND UPPER(NATUREZA_OPERACAO." + CAMPO_DESCRICAO_NATUREZA_OPERACAO + ") LIKE (UPPER('%" + 
								  ((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getDescricaoNaturezaOperacao() + 
								  "%')) ");
			}
			if (Validador.isNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getPercentualBaseCalculo()))
			{
				sql.append(" AND NATUREZA_OPERACAO." + CAMPO_PERCENTUAL_NATUREZA_OPERACAO + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoGIA()))
			{
				sql.append(" AND NATUREZA_OPERACAO." + CAMPO_TIPO_GIA + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoBaseCalculo()))
			{
				sql.append(" AND NATUREZA_OPERACAO." + CAMPO_TIPO_BASE_CALCULO + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getTipoProcesso()))
			{
				sql.append(" AND NATUREZA_OPERACAO." + CAMPO_TIPO_PROCESSO + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getStatusNaturezaOperacao()))
			{
				sql.append(" AND NATUREZA_OPERACAO." + CAMPO_STATUS_NATUREZA_OPERACAO + " = ? ");
			}
		   if (Validador.isDominioNumericoValido(((NaturezaOperacaoVo) naturezaOperacaoVo.getParametroConsulta()).getFlagIsencaoPrevistaLei()))
		   {
		      sql.append(" AND NATUREZA_OPERACAO." + CAMPO_FLAG_ISENCAO_PREVISTA_LEI + " = ? ");
		   }			
			sql.append(" ORDER BY NATUREZA_OPERACAO." + CAMPO_CODIGO_NATUREZA_OPERACAO + " ");
		}
		return sql.toString();
	}
}
