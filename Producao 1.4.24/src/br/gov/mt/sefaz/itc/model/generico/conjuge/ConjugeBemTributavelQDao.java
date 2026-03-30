/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConjugeBemTributavelQDao.java
 * Revisão:
 * Data revisão:
 * $Id: ConjugeBemTributavelQDao.java,v 1.2 2009/05/05 20:02:29 ricardo.moraes Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.conjuge;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoConjuge;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposConjuge;
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
 * @author Leandro Dorileo
 * @version $Revision: 1.2 $
 */
public class ConjugeBemTributavelQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposConjuge
{

	/**
	 * Construtor da classe.
	 * 
	 * @param conexao objeto de conexao com o banco de dados.
	 * @implemented by Leandro Dorileo
	 */
	public ConjugeBemTributavelQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metodo responsavel por criar um BemTributavelVo apartir de um ResultSet.
	 * @param (java.sql.ResultSet).
	 * @param conjugeBemTributavelVo VO Bem Tributável (Value Object).
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Leandro Dorileo
	 */
	private void getConjugeBemTributavel(final ResultSet rs, final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(conjugeBemTributavelVo);
		conjugeBemTributavelVo.setTipoConjuge(new DomnTipoConjuge(rs.getInt(CAMPO_TIPO_CONJUGE)));
		conjugeBemTributavelVo.setBemTributavelVo(new BemTributavelVo(rs.getLong(CAMPO_ITCTB18_CODG_ITCD_BEM_TRBT)));
		conjugeBemTributavelVo.getPessoaConjuge().setNumrContribuinte(new Long(rs.getLong(CAMPO_ACCTB01_NUMR_PESS_CONJUGE)));
		conjugeBemTributavelVo.getGiaITCDVo().setCodigo(rs.getLong(CAMPO_ITCTB17_ITCTB14_CODG_ITCD));
		conjugeBemTributavelVo.setValorConjuge(rs.getDouble(CAMPO_VALOR_CONJUGE));
	   conjugeBemTributavelVo.setInfoDispensaRecolhimento(rs.getInt(CAMPO_INFO_DSPE_RCLH));
	   conjugeBemTributavelVo.setInfoIsencao(rs.getInt(CAMPO_INFO_ISENCAO));
	}

	/**
	 * Encontra um conjuge relacionado com um bem tributável de acordo com os parâmetros informados
	 * no vo do parâmetro <code>conjugeBemTributavelVo</code>.
	 * 
	 * @param conjugeBemTributavelVo	vo do conjuge relacionado com o bem tributável(Value Object)
	 * @return ConjugeBemTributavelVo
	 * @implemented by Leandro Dorileo
	 */
	public ConjugeBemTributavelVo findConjugeBemTributavel(final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListFindConjugeBemTributavel(conjugeBemTributavelVo));
			prepareStatementListFindConjugeBemTributavel(ps, conjugeBemTributavelVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getConjugeBemTributavel(rs, conjugeBemTributavelVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_CONJUGE_BEM_TRIBUTAVEL);
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
		return conjugeBemTributavelVo;
	}

	/**
	 * Lista todos os conjuges relacionados a um determinado bem tributável de acordo com os parâmetros
	 * informados no vo do parâmetro <code>conjugeBemTributavelVo</code>.
	 * 
	 * @param conjugeBemTributavelVo vo do conjuge relacionado com o bem tributável(Value Object)
	 * @return ConjugeBemTributavelVo
	 * @implemented by Leandro Dorileo
	 */
	public ConjugeBemTributavelVo listConjugeBemTributavel(final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListFindConjugeBemTributavel(conjugeBemTributavelVo));
			prepareStatementListFindConjugeBemTributavel(ps, conjugeBemTributavelVo);
			Collection listaConjuge = new ArrayList();
			for (rs = ps.executeQuery(); rs.next(); )
			{
				ConjugeBemTributavelVo atual = new ConjugeBemTributavelVo();
				getConjugeBemTributavel(rs, atual);
				listaConjuge.add(atual);
			}
			if (Validador.isCollectionValida(listaConjuge))
			{
				conjugeBemTributavelVo.setCollVO(listaConjuge);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_BEM_TRIBUTAVEL);
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
		return conjugeBemTributavelVo;
	}

	/**
	 * Gera o sql para os métodos de consulta e listagem de bem conjuges relacionados a
	 * bens tributáveis.
	 * 
	 * @param conjugeBemTributavelVo vo do conjuge relacionado a bens tributáveis
	 * @return
	 */
	private String getSQLListFindConjugeBemTributavel(final ConjugeBemTributavelVo conjugeBemTributavelVo)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CONJ.").append(CAMPO_ACCTB01_NUMR_PESS_CONJUGE);
		sql.append(" , CONJ.").append(CAMPO_ITCTB18_CODG_ITCD_BEM_TRBT);
		sql.append(" , CONJ.").append(CAMPO_TIPO_CONJUGE);
		sql.append(" , CONJ.").append(CAMPO_ITCTB17_ITCTB14_CODG_ITCD);
		sql.append(" , CONJ.").append(CAMPO_VALOR_CONJUGE);
	   sql.append(" , CONJ.").append(CAMPO_INFO_ISENCAO);
	   sql.append(" , CONJ.").append(CAMPO_INFO_DSPE_RCLH);
		sql.append(" FROM ").append(TABELA_CONJUGE_BEM_TRIBUTAVEL).append(" CONJ ");
		sql.append(" WHERE 1 = 1 ");
		if (conjugeBemTributavelVo != null && conjugeBemTributavelVo.isConsultaParametrizada())
		{
			if (Validador.isDominioNumericoValido(((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getTipoConjuge()))
			{
				sql.append(" AND CONJ.").append(CAMPO_TIPO_CONJUGE).append(" = ? ");
			}
			if (Validador.isNumericoValido(((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getPessoaConjuge().getNumrContribuinte()))
			{
				sql.append(" AND CONJ.").append(CAMPO_ACCTB01_NUMR_PESS_CONJUGE).append(" = ? ");
			}
			if (Validador.isNumericoValido(((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getBemTributavelVo().getCodigo()))
			{
				sql.append(" AND CONJ.").append(CAMPO_ITCTB18_CODG_ITCD_BEM_TRBT).append(" = ? ");
			}
			if (Validador.isNumericoValido(((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
			{
				sql.append(" AND CONJ.").append(CAMPO_ITCTB17_ITCTB14_CODG_ITCD).append(" = ? ");
			}
			if (Validador.isNumericoValido(((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getValorConjuge()))
			{
				sql.append(" AND CONJ.").append(CAMPO_VALOR_CONJUGE).append(" = ? ");
			}
		}
		sql.append(" ORDER BY ").append(CAMPO_ACCTB01_NUMR_PESS_CONJUGE);
		return sql.toString();
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente os valores válidos
	 * no momento de listar ou selecionar um conjuge relacionado com um bem tributável.
	 *
	 * @param ps preparedStatement (java.sql.PreparedStatement)
	 * @param conjugeBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 */
	private void prepareStatementListFindConjugeBemTributavel(final PreparedStatement ps, final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		int contador = 0;
		if (conjugeBemTributavelVo != null && conjugeBemTributavelVo.isConsultaParametrizada())
		{
			if (Validador.isDominioNumericoValido(((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getTipoConjuge()))
			{
				ps.setLong(++contador, ((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getTipoConjuge().getValorCorrente());
			}
		   if (Validador.isNumericoValido(((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getPessoaConjuge().getNumrContribuinte()))
			{
				ps.setLong(++contador, ((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getPessoaConjuge().getNumrContribuinte().longValue());
			}
			if (Validador.isNumericoValido(((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getBemTributavelVo().getCodigo()))
			{
				ps.setLong(++contador, ((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getBemTributavelVo().getCodigo());
			}
			if (Validador.isNumericoValido(((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
			{
				ps.setLong(++contador, ((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getGiaITCDVo().getCodigo());
			}
			if (Validador.isNumericoValido(((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getValorConjuge()))
			{
				ps.setDouble(++contador, ((ConjugeBemTributavelVo) conjugeBemTributavelVo.getParametroConsulta()).getValorConjuge());
			}
		}
	}
}
