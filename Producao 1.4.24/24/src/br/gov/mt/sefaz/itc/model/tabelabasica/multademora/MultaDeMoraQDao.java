/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: MultaDeMoraQDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 12/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.multademora;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposMultaMora;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe de acesso a dados (Data Access Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class MultaDeMoraQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposMultaMora
{
	/**
	 * Construtor que recebe a Conexão com o Banco de dados.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	public MultaDeMoraQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Alimenta MultaDeMoraVo com os dados do ResultSet.
	 * @param (java.sql.ResultSet).
	 * @param multaDeMoraVo Multa de Mora (Value Object).
	 * @implemented by Daniel Balieiro
	 */
	private void getMultaDeMora(final ResultSet rs, final MultaDeMoraVo multaDeMoraVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(multaDeMoraVo);
		try
		{
			multaDeMoraVo.setCodigo(rs.getLong(CAMPO_CODIGO_MULTA_MORA));
			multaDeMoraVo.setPercentualMultaMora(rs.getDouble(CAMPO_PERCENTUAL_MULTA_MORA));
			multaDeMoraVo.setPercentualMaximoMultaMora(rs.getDouble(CAMPO_PERCENTUAL_MAXIMO_MULTA_MORA));
			multaDeMoraVo.setStatusMultaMora(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_MULTA_MORA)));
			multaDeMoraVo.setDataAtualizacao(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método utilizado para buscar dados de uma determinada Multa de Mora.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * a Multa de Mora de menor código cadastrado no banco de dados.
	 * <br>Esta pesquisa pode ser realizada pelos seguintes filtros:
	 * <br>- multaDeMoraVo.codigo
	 * @param multaDeMoraVo Multa de Mora (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return MultaDeMoraVo
	 * @implemented by Daniel Balieiro
	 */
	public MultaDeMoraVo findMultaDeMora(final MultaDeMoraVo multaDeMoraVo) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException
	{
		Validador.validaObjeto(multaDeMoraVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindMultaDeMora(multaDeMoraVo));
			prepareStatemantFindMultaDeMora(ps, multaDeMoraVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getMultaDeMora(rs, multaDeMoraVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_MULTA_MORA);
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
		return multaDeMoraVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement os parâmetros de pesquisa possíveis ao Consultar MultaDeMoraVo.
	 * @param ps (java.sql.PreparedStatement).
	 * @param multaDeMoraVo Multa de Mora (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatemantFindMultaDeMora(final PreparedStatement ps, final MultaDeMoraVo multaDeMoraVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(multaDeMoraVo);
		try
		{
			int contador = 0;
			if (multaDeMoraVo.isConsultaParametrizada())
			{
				if (Validador.isNumericoValido(((MultaDeMoraVo) multaDeMoraVo.getParametroConsulta()).getCodigo()))
				{
					ps.setLong(++contador, ((MultaDeMoraVo) multaDeMoraVo.getParametroConsulta()).getCodigo());
				}
				if (Validador.isNumericoValido(((MultaDeMoraVo) multaDeMoraVo.getParametroConsulta()).getPercentualMultaMora()))
				{
					ps.setDouble(++contador, ((MultaDeMoraVo) multaDeMoraVo.getParametroConsulta()).getPercentualMultaMora());
				}
				if (Validador.isNumericoValido(((MultaDeMoraVo) multaDeMoraVo.getParametroConsulta()).getPercentualMaximoMultaMora()))
				{
					ps.setDouble(++contador, ((MultaDeMoraVo) multaDeMoraVo.getParametroConsulta()).getPercentualMaximoMultaMora());
				}
				if (Validador.isDominioNumericoValido(((MultaDeMoraVo) multaDeMoraVo.getParametroConsulta()).getStatusMultaMora()))
				{
					ps.setInt(++contador, ((MultaDeMoraVo) multaDeMoraVo.getParametroConsulta()).getStatusMultaMora().getValorCorrente());
				}
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Retorna o SQL para consulta de multa de mora.
	 * @param multaDeMoraVo Multa de Mora (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	private String getSQLFindMultaDeMora(final MultaDeMoraVo multaDeMoraVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(multaDeMoraVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT MULTA." + CAMPO_CODIGO_MULTA_MORA + ", ");
		sql.append(" MULTA." + CAMPO_PERCENTUAL_MULTA_MORA + ", ");
		sql.append(" MULTA." + CAMPO_PERCENTUAL_MAXIMO_MULTA_MORA + ", ");
		sql.append(" MULTA." + CAMPO_STATUS_MULTA_MORA + ", ");
		sql.append(" MULTA." + CAMPO_DATA_ATUALIZACAO_BD + " ");
		sql.append(" FROM " + TABELA_MULTA_MORA + " MULTA ");
		sql.append(" WHERE 1=1 ");
		if (multaDeMoraVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((MultaDeMoraVo) multaDeMoraVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND MULTA." + CAMPO_CODIGO_MULTA_MORA + " = ? ");
			}
			if (Validador.isNumericoValido(((MultaDeMoraVo) multaDeMoraVo.getParametroConsulta()).getPercentualMultaMora()))
			{
				sql.append(" AND MULTA." + CAMPO_PERCENTUAL_MULTA_MORA + " = ? ");
			}
			if (Validador.isNumericoValido(((MultaDeMoraVo) multaDeMoraVo.getParametroConsulta()).getPercentualMaximoMultaMora()))
			{
				sql.append(" AND MULTA." + CAMPO_PERCENTUAL_MAXIMO_MULTA_MORA + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((MultaDeMoraVo) multaDeMoraVo.getParametroConsulta()).getStatusMultaMora()))
			{
				sql.append(" AND MULTA." + CAMPO_STATUS_MULTA_MORA + " = ? ");
			}
		}
		sql.append(" ORDER BY MULTA.").append(CAMPO_DATA_ATUALIZACAO_BD).append(" DESC");
		return sql.toString();
	}
}
