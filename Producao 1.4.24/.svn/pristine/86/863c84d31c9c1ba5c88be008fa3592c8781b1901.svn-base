/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CulturaQDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 10/10/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.cultura;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposCultura;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Classe de acesso a dados (Data Access Object).
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.1.1.1 $
 */
public class CulturaQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposCultura
{
	/**
	 * Construtor da classe.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public CulturaQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metodo responsavel por criar um CulturaVo apartir de um ResultSet
	 * @param (java.sql.ResultSet).
	 * @param culturaVo Cultura (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void getCultura(final ResultSet rs, final CulturaVo culturaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(culturaVo);
		culturaVo.setCodigo(rs.getLong(CAMPO_CODIGO_CULTURA));
		culturaVo.setDataAtualizacao(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
		culturaVo.setDescricaoCultura(rs.getString(CAMPO_DESCRICAO_CULTURA));
		culturaVo.setStatusCultura(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_CULTURA)));
	}

	/**
	 * Método utilizado para buscar dados de uma determinada Cultura.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * a Cultura de menor código cadastrado no banco de dados.
	 * A pesquisa parametrizada pode ser: ou pelo código, e/ou pela descrição completa, e/ou pelo status da Cultura.
	 * @param culturaVo Cultura (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	public CulturaVo findCultura(final CulturaVo culturaVo) throws ObjetoObrigatorioException, ConsultaException
	{
		Validador.validaObjeto(culturaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindCultura(culturaVo));
			prepareStatementFindCultura(ps, culturaVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getCultura(rs, culturaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_CULTURA);
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
		return culturaVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento da consulta de uma Cultura.	
	 * @param ps (java.sql.PreparedStatement).
	 * @param culturaVo Cultura (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void prepareStatementFindCultura(final PreparedStatement ps, final CulturaVo culturaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(culturaVo);
		int contador = 0;
		if (culturaVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((CulturaVo) culturaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((CulturaVo) culturaVo.getParametroConsulta()).getCodigo());
			}
			// DESCRICAO
			if (Validador.isStringValida(((CulturaVo) culturaVo.getParametroConsulta()).getDescricaoCultura()))
			{
				ps.setString(++contador, ((CulturaVo) culturaVo.getParametroConsulta()).getDescricaoCultura());
			}
			// STATUS CULTURA
			if (Validador.isDominioNumericoValido(((CulturaVo) culturaVo.getParametroConsulta()).getStatusCultura()))
			{
				ps.setInt(++contador, ((CulturaVo) culturaVo.getParametroConsulta()).getStatusCultura().getValorCorrente());
			}
		}
	}

	/**
	 * Cria a string SQL de Consulta da Cultura.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	private String getSQLFindCultura(final CulturaVo culturaVo) throws ObjetoObrigatorioException, ClassCastException
	{
		Validador.validaObjeto(culturaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" DISTINCT CULTURA." + CAMPO_CODIGO_CULTURA + " ");
		sql.append(" , CULTURA." + CAMPO_DESCRICAO_CULTURA + " ");
		sql.append(" , CULTURA." + CAMPO_STATUS_CULTURA + " ");
		sql.append(" , CULTURA." + CAMPO_DATA_ATUALIZACAO_BD + " ");
		sql.append(" FROM " + TABELA_CULTURA + " CULTURA ");
		sql.append(" WHERE 1 = 1 ");
		if (culturaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((CulturaVo) culturaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND CULTURA." + CAMPO_CODIGO_CULTURA + " = ? ");
			}
			if (Validador.isStringValida(((CulturaVo) culturaVo.getParametroConsulta()).getDescricaoCultura()))
			{
				sql.append(" AND UPPER(CULTURA." + CAMPO_DESCRICAO_CULTURA + ") = (UPPER(?)) ");
			}
			if (Validador.isDominioNumericoValido(((CulturaVo) culturaVo.getParametroConsulta()).getStatusCultura()))
			{
				sql.append("   AND CULTURA." + CAMPO_STATUS_CULTURA + " = ? ");
			}
		}
		sql.append(" ORDER BY CULTURA." + CAMPO_CODIGO_CULTURA + " ");
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de uma coleção de Culturas.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todos as Culturas cadastrados no sistema.
	 * A pesquisa parametrizada pode ser: ou pelo código, e/ou pela descrição completa, e/ou pelo status da Cultura.
	 * @param culturaVo Cultura (Value Object).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	public CulturaVo listCultura(final CulturaVo culturaVo) throws ConsultaException, ObjetoObrigatorioException
	{
		Validador.validaObjeto(culturaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListarCultura(culturaVo));
			prepareStatementListarCultura(ps, culturaVo);
			Collection listaCultura = new ArrayList();
			for (rs = ps.executeQuery(); rs.next(); )
			{
				CulturaVo culturaVoAtual = new CulturaVo();
				getCultura(rs, culturaVoAtual);
				listaCultura.add(culturaVoAtual);
			}
			if (Validador.isCollectionValida(listaCultura))
			{
				culturaVo.setCollVO(listaCultura);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_CULTURA);
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
		return culturaVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento de listar Cultura.
	 * @param ps (java.sql.PreparedStatement).
	 * @param culturaVo Cultura (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void prepareStatementListarCultura(final PreparedStatement ps, final CulturaVo culturaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(culturaVo);
		int contador = 0;
		if (culturaVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((CulturaVo) culturaVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((CulturaVo) culturaVo.getParametroConsulta()).getCodigo());
			}
			// STATUS CULTURA
			if (Validador.isDominioNumericoValido(((CulturaVo) culturaVo.getParametroConsulta()).getStatusCultura()))
			{
				ps.setInt(++contador, ((CulturaVo) culturaVo.getParametroConsulta()).getStatusCultura().getValorCorrente());
			}
		}
	}

	/**
	 * Cria a SQL de Consulta da Cultura.
	 * @param culturaVo Cultura (Value Object).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	private String getSQLListarCultura(final CulturaVo culturaVo)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  CULTURA." + CAMPO_CODIGO_CULTURA + " ");
		sql.append(" , CULTURA." + CAMPO_DATA_ATUALIZACAO_BD + " ");
		sql.append(" , CULTURA." + CAMPO_DESCRICAO_CULTURA + " ");
		sql.append(" , CULTURA." + CAMPO_STATUS_CULTURA + " ");
		sql.append(" FROM " + TABELA_CULTURA + " CULTURA ");
		sql.append(" WHERE 1 = 1 ");
		if (culturaVo != null && culturaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((CulturaVo) culturaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND CULTURA." + CAMPO_CODIGO_CULTURA + " = ? ");
			}
			if (Validador.isStringValida(((CulturaVo) culturaVo.getParametroConsulta()).getDescricaoCultura()))
			{
				sql.append(" AND UPPER(CULTURA." + CAMPO_DESCRICAO_CULTURA + ") LIKE (UPPER('%" + 
								  ((CulturaVo) culturaVo.getParametroConsulta()).getDescricaoCultura() + "%')) ");
			}
			if (Validador.isDominioNumericoValido(((CulturaVo) culturaVo.getParametroConsulta()).getStatusCultura()))
			{
				sql.append("   AND CULTURA." + CAMPO_STATUS_CULTURA + " = ? ");
			}
		}
		sql.append(" ORDER BY CULTURA." + CAMPO_DESCRICAO_CULTURA + " ");
		return sql.toString();
	}
}
