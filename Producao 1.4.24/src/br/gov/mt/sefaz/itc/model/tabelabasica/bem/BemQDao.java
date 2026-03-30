/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BemQDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 08/10/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.bem;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoVerificacao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBem;
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
 * @version $Revision: 1.2 $
 */
public class BemQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposBem
{
	/**
	 * Construtor da classe.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public BemQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Metodo responsavel por criar um BemVo apartir de um ResultSet.
	 * @param (java.sql.ResultSet).
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void getBem(final ResultSet rs, final BemVo bemVo) throws ObjetoObrigatorioException, SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(bemVo);
		bemVo.setCodigo(rs.getLong(CAMPO_CODIGO_BEM));
		bemVo.setDataAtualizacao(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
		bemVo.setDescricaoTipoBem(rs.getString(CAMPO_DESCRICAO_BEM));
		bemVo.setStatusBem(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_BEM)));
		bemVo.setClassificacaoTipoBem(new DomnTipoBem(rs.getInt(CAMPO_TIPO_BEM)));
		bemVo.setPossuiConstrucao(new DomnSimNao(rs.getInt(CAMPO_POSSUI_CONSTRUCAO)));
	   bemVo.setTipoVerificacao(new DomnTipoVerificacao(rs.getInt(CAMPO_TIPO_VERIFICACAO)));
	   bemVo.setTipoProtocoloGIA(new DomnTipoProtocolo(rs.getInt(CAMPO_TIPO_PROTOCOLO_GIA)));
	}

	/**
	 * Método utilizado para buscar dados de um determinado Bem.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * o Bem de menor código cadastrado no banco de dados.
	 * A pesquisa parametrizada pode ser: ou pelo código, e/ou pela descrição completa, e/ou pelo status, e/ou pela classificação do Bem.
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public BemVo findBem(final BemVo bemVo) throws ObjetoObrigatorioException, ConsultaException
	{
		Validador.validaObjeto(bemVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindBem(bemVo));
			prepareStatementFindBem(ps, bemVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getBem(rs, bemVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_BEM);
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
		return bemVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento da consulta de um Bem.
	 * @param ps (java.sql.PreparedStatement).
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindBem(final PreparedStatement ps, final BemVo bemVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(bemVo);
		int contador = 0;
		if (bemVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((BemVo) bemVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((BemVo) bemVo.getParametroConsulta()).getCodigo());
			}
			// DESCRICAO
			if (Validador.isStringValida(((BemVo) bemVo.getParametroConsulta()).getDescricaoTipoBem()))
			{
				ps.setString(++contador, ((BemVo) bemVo.getParametroConsulta()).getDescricaoTipoBem());
			}
			// STATUS BEM
			if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getStatusBem()))
			{
				ps.setInt(++contador, ((BemVo) bemVo.getParametroConsulta()).getStatusBem().getValorCorrente());
			}
			// TIPO BEM
			if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getClassificacaoTipoBem()))
			{
				ps.setInt(++contador, ((BemVo) bemVo.getParametroConsulta()).getClassificacaoTipoBem().getValorCorrente());
			}
			// POSSUI CONSTRUCAO
			if(Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getPossuiConstrucao()))
			{
				ps.setInt(++contador,((BemVo) bemVo.getParametroConsulta()).getPossuiConstrucao().getValorCorrente());
			}
         // CAMPO_TIPO_VERIFICACAO
		   if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getTipoVerificacao()))
		   {
		      ps.setInt(++contador, ((BemVo) bemVo.getParametroConsulta()).getTipoVerificacao().getValorCorrente());
		   }
         // CAMPO_TIPO_PROTOCOLO_GIA
		   if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getTipoProtocoloGIA()))
		   {
		      ps.setInt(++contador, ((BemVo) bemVo.getParametroConsulta()).getTipoProtocoloGIA().getValorCorrente());
		   }
		}
	}

	/**
	 * Cria a SQL de Consulta do Bem.
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindBem(final BemVo bemVo) throws ObjetoObrigatorioException, ClassCastException
	{
		Validador.validaObjeto(bemVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" ");
		sql.append(" SELECT ");
		sql.append(" DISTINCT BEM." + CAMPO_CODIGO_BEM + " ");
		sql.append(" , BEM." + CAMPO_DATA_ATUALIZACAO_BD + " ");
		sql.append(" , BEM." + CAMPO_DESCRICAO_BEM + " ");
		sql.append(" , BEM." + CAMPO_STATUS_BEM + " ");
		sql.append(" , BEM." + CAMPO_TIPO_BEM + " ");
		sql.append(" , BEM." + CAMPO_POSSUI_CONSTRUCAO + " ");
	   sql.append(" , BEM." + CAMPO_TIPO_VERIFICACAO + " ");
	   sql.append(" , BEM." + CAMPO_TIPO_PROTOCOLO_GIA + " ");
		sql.append(" FROM " + TABELA_BEM + " BEM ");
		sql.append(" WHERE 1 = 1 ");
		if (bemVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((BemVo) bemVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND BEM." + CAMPO_CODIGO_BEM + " = ? ");
			}
			if (Validador.isStringValida(((BemVo) bemVo.getParametroConsulta()).getDescricaoTipoBem()))
			{
				sql.append(" AND UPPER(BEM." + CAMPO_DESCRICAO_BEM + ") = (UPPER(?)) ");
			}
			if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getStatusBem()))
			{
				sql.append("   AND BEM." + CAMPO_STATUS_BEM + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getClassificacaoTipoBem()))
			{
				sql.append("   AND BEM." + CAMPO_TIPO_BEM + " = ? ");
			}
			if(Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getPossuiConstrucao()))
			{
				sql.append(" AND BEM."+CAMPO_POSSUI_CONSTRUCAO + " = ? ");
			}
		   if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getTipoVerificacao()))
		   {
		      sql.append("   AND BEM." + CAMPO_TIPO_VERIFICACAO + " = ? ");
		   }
		   if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getTipoProtocoloGIA()))
		   {
		      sql.append("   AND BEM." + CAMPO_TIPO_PROTOCOLO_GIA+ " = ? ");
		   }
		}
		sql.append(" ");
		sql.append(" ORDER BY BEM." + CAMPO_CODIGO_BEM + " ");
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de uma coleção de Bens.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todos os Bens cadastrados no sistema.
	 * A pesquisa parametrizada pode ser: ou pelo código, e/ou pela descrição completa, e/ou pelo status, e/ou pela classificação do Bem.
	 * @param bemVo Bem (Value Object).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public BemVo listBem(final BemVo bemVo) throws ObjetoObrigatorioException, ConsultaException
	{
		Validador.validaObjeto(bemVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListBem(bemVo));
			prepareStatementListBem(ps, bemVo);
			Collection listaBem = new ArrayList();
			for (rs = ps.executeQuery(); rs.next(); )
			{
				BemVo bemVoAtual = new BemVo();
				getBem(rs, bemVoAtual);
				listaBem.add(bemVoAtual);
			}
			if (Validador.isCollectionValida(listaBem))
			{
				bemVo.setCollVO(listaBem);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_BEM);
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
		return bemVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement somente valores válidos no momento de listar Bem	.
	 * @param ps (java.sql.PreparedStatement).
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementListBem(final PreparedStatement ps, final BemVo bemVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(bemVo);
		int contador = 0;
		if (bemVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((BemVo) bemVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((BemVo) bemVo.getParametroConsulta()).getCodigo());
			}
			// STATUS BEM
			if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getStatusBem()))
			{
				ps.setInt(++contador, ((BemVo) bemVo.getParametroConsulta()).getStatusBem().getValorCorrente());
			}
			// TIPO BEM
			if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getClassificacaoTipoBem()))
			{
				ps.setInt(++contador, ((BemVo) bemVo.getParametroConsulta()).getClassificacaoTipoBem().getValorCorrente());
			}
		   // POSSUI CONSTRUCAO
		   if(Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getPossuiConstrucao()))
		   {
		      ps.setInt(++contador,((BemVo) bemVo.getParametroConsulta()).getPossuiConstrucao().getValorCorrente());
		   }
		   // CAMPO_TIPO_VERIFICACAO
		   if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getTipoVerificacao()))
		   {
		      ps.setInt(++contador, ((BemVo) bemVo.getParametroConsulta()).getTipoVerificacao().getValorCorrente());
		   }
		   // CAMPO_TIPO_PROTOCOLO_GIA
		   if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getTipoProtocoloGIA()))
		   {
		      ps.setInt(++contador, ((BemVo) bemVo.getParametroConsulta()).getTipoProtocoloGIA().getValorCorrente());
		   }
		}
	}

	/**
	 * Cria a SQL de Consulta do Bem.
	 * @param bemVo Bem (Value Object).
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListBem(final BemVo bemVo)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  BEM." + CAMPO_CODIGO_BEM + " ");
		sql.append(" , BEM." + CAMPO_DATA_ATUALIZACAO_BD + " ");
		sql.append(" , BEM." + CAMPO_DESCRICAO_BEM + " ");
		sql.append(" , BEM." + CAMPO_STATUS_BEM + " ");
		sql.append(" , BEM." + CAMPO_TIPO_BEM + " ");
		sql.append(" , BEM." + CAMPO_POSSUI_CONSTRUCAO + " ");
	   sql.append(" , BEM." + CAMPO_TIPO_VERIFICACAO + " ");
	   sql.append(" , BEM." + CAMPO_TIPO_PROTOCOLO_GIA + " ");
		sql.append(" FROM " + TABELA_BEM + " BEM ");
		sql.append(" WHERE 1 = 1 ");
		if (bemVo != null && bemVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((BemVo) bemVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND BEM." + CAMPO_CODIGO_BEM + " = ? ");
			}
			if (Validador.isStringValida(((BemVo) bemVo.getParametroConsulta()).getDescricaoTipoBem()))
			{
				sql.append(" AND UPPER(BEM." + CAMPO_DESCRICAO_BEM + ") LIKE (UPPER('%" + 
								  ((BemVo) bemVo.getParametroConsulta()).getDescricaoTipoBem() + "%')) ");
			}
			if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getStatusBem()))
			{
				sql.append("   AND BEM." + CAMPO_STATUS_BEM + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getClassificacaoTipoBem()))
			{
				sql.append("   AND BEM." + CAMPO_TIPO_BEM + " = ? ");
			}
		   if(Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getPossuiConstrucao()))
		   {
		      sql.append(" AND BEM."+CAMPO_POSSUI_CONSTRUCAO + " = ? ");
		   }
         // CAMPO_TIPO_VERIFICACAO
		   if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getTipoVerificacao()))
		   {
		      sql.append("   AND BEM." + CAMPO_TIPO_VERIFICACAO + " = ? ");
		   }
         // CAMPO_TIPO_PROTOCOLO_GIA
		   if (Validador.isDominioNumericoValido(((BemVo) bemVo.getParametroConsulta()).getTipoProtocoloGIA()))
		   {
		      sql.append("   AND BEM." + CAMPO_TIPO_PROTOCOLO_GIA + " = ? ");
		   }
		}
		sql.append(" ORDER BY BEM." + CAMPO_DESCRICAO_BEM + " ");
		return sql.toString();
	}
}
