/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: AvaliacaoBemTributavelServidorQDao.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: AvaliacaoBemTributavelServidorQDao.java,v 1.1.1.1 2008/05/28 17:55:04 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.avaliacao.servidor;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.AvaliacaoBemTributavelVo;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposAvaliacaoBemtributavelServidor;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe para acesso ao banco de dados, responsável por efetuar consultas referentes a Avaliação de Bem Tributavel - Servidor
 *
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class AvaliacaoBemTributavelServidorQDao extends AbstractDao implements TabelasITC, CamposAvaliacaoBemtributavelServidor
{
	/**
	 * Construtor que recebe a conexão com o banco de dados
	 *
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelServidorQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método que monta o VO da Avaliação - Servidor com os dados encontrados no banco de dados
	 *
	 * @param rs
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private AvaliacaoBemTributavelServidorVo getAvaliacaoServidor(ResultSet rs, AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		avaliacaoBemTributavelServidorVo.setAvaliacaoBemTributavelVo(new AvaliacaoBemTributavelVo(rs.getLong(CAMPO_ITCTB28_CODIGO_AVALIACAO_BEM_TRIBUTAVEL)));
		avaliacaoBemTributavelServidorVo.getServidorSefazVo().setNumrMatricula(new Long(rs.getLong(CAMPO_SGPTB06_NUMERO_MATRICULA_SERVIDOR)));
		return avaliacaoBemTributavelServidorVo;
	}

	/**
	 * Método que monta a SQL de consulta de Avaliação - Servidor
	 *
	 * @param avaliacaoBemTributavelServidorVo
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListAvaliacaoBemTributavelServidor(AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT SERV.").append(CAMPO_ITCTB28_CODIGO_AVALIACAO_BEM_TRIBUTAVEL);
		sql.append(" , SERV.").append(CAMPO_SGPTB06_NUMERO_MATRICULA_SERVIDOR);
		sql.append(" FROM ").append(TABELA_AVALIACAO_BEMTRIBUTAVEL_SERVIDOR).append(" SERV ");
		sql.append(" WHERE 1 = 1 ");
		if (avaliacaoBemTributavelServidorVo != null && avaliacaoBemTributavelServidorVo.isConsultaParametrizada())
		{
			// AVALIACAO JUDICIAL
			if (Validador.isNumericoValido(((AvaliacaoBemTributavelServidorVo) avaliacaoBemTributavelServidorVo.getParametroConsulta()).getAvaliacaoBemTributavelVo().getCodigo()))
			{
				sql.append(" AND SERV.").append(CAMPO_ITCTB28_CODIGO_AVALIACAO_BEM_TRIBUTAVEL).append(" = ? ");
			}
			// SERVIDOR SEFAZ
			if (Validador.isNumericoValido(((AvaliacaoBemTributavelServidorVo) avaliacaoBemTributavelServidorVo.getParametroConsulta()).getServidorSefazVo().getNumrMatricula().longValue()))
			{
				sql.append(" AND SERV.").append(CAMPO_SGPTB06_NUMERO_MATRICULA_SERVIDOR).append(" = ? ");
			}
		}
		sql.append(" ORDER BY SERV.").append(CAMPO_ITCTB28_CODIGO_AVALIACAO_BEM_TRIBUTAVEL);
		return sql.toString();
	}

	/**
	 * Método que monta o Prepared Statement de acordo com os dandos válidos da Avaliação - Servidor
	 *
	 * @param ps
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementListAvaliacaoBemTributavelServidor(PreparedStatement ps, AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws SQLException
	{
		int contador = 0;
		// AVALICAO JUDICIAL
		if (Validador.isNumericoValido(((AvaliacaoBemTributavelServidorVo) avaliacaoBemTributavelServidorVo.getParametroConsulta()).getAvaliacaoBemTributavelVo().getCodigo()))
		{
			ps.setLong(++contador, ((AvaliacaoBemTributavelServidorVo) avaliacaoBemTributavelServidorVo.getParametroConsulta()).getAvaliacaoBemTributavelVo().getCodigo());
		}
		// SERVIDOR SEFAZ	
		if (Validador.isNumericoValido(((AvaliacaoBemTributavelServidorVo) avaliacaoBemTributavelServidorVo.getParametroConsulta()).getServidorSefazVo().getNumrMatricula().longValue()))
		{
			ps.setLong(++contador, ((AvaliacaoBemTributavelServidorVo) avaliacaoBemTributavelServidorVo.getParametroConsulta()).getServidorSefazVo().getNumrMatricula().longValue());
		}
	}

	/**
	 * Método para listar as Avaliações - Servidor
	 *
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelServidorVo listAvaliacaoBemTributavelServidor(final AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListAvaliacaoBemTributavelServidor(avaliacaoBemTributavelServidorVo));
			prepareStatementListAvaliacaoBemTributavelServidor(ps, avaliacaoBemTributavelServidorVo);
			for (rs = ps.executeQuery(); rs.next(); )
			{
				AvaliacaoBemTributavelServidorVo avaliacaoServidor = new AvaliacaoBemTributavelServidorVo();
				getAvaliacaoServidor(rs, avaliacaoServidor);
				avaliacaoBemTributavelServidorVo.getCollVO().add(avaliacaoServidor);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR);
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
		return avaliacaoBemTributavelServidorVo;
	}

	/**
	 * Método para consultar uma Avaliação - Servidor
	 *
	 * @param avaliacaoBemTributavelServidorVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public AvaliacaoBemTributavelServidorVo findAvaliacaoBemTributavelServidor(final AvaliacaoBemTributavelServidorVo avaliacaoBemTributavelServidorVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(avaliacaoBemTributavelServidorVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListAvaliacaoBemTributavelServidor(avaliacaoBemTributavelServidorVo));
			prepareStatementListAvaliacaoBemTributavelServidor(ps, avaliacaoBemTributavelServidorVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getAvaliacaoServidor(rs, avaliacaoBemTributavelServidorVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR);
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
		return avaliacaoBemTributavelServidorVo;
	}
}
