/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConfiguracaoGerencialParametrosQDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 06/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoConfiguracaoGerencialParametros;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposConfiguracaoGerencialParametros;
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
public class ConfiguracaoGerencialParametrosQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposConfiguracaoGerencialParametros
{
	/**
	 * Construtor que recebe a Conexão com o Banco de dados.
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Alimenta ConfiguracaoGerencialParametrosVo com os dados do ResultSet.
	 * @param (java.sql.ResultSet).
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void getConfiguracaoGerencialParametros(final ResultSet rs, final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		configuracaoGerencialParametrosVo.setCodigo(rs.getLong(CAMPO_CODIGO_CONFIGURACAO_GERENCIAL_PARAMETROS));
		configuracaoGerencialParametrosVo.setValorItem(rs.getString(CAMPO_VALOR_ITEM));
		configuracaoGerencialParametrosVo.setDescricaoItem(rs.getString(CAMPO_DESCRICAO_ITEM));
		configuracaoGerencialParametrosVo.setTipoItem(new DomnTipoConfiguracaoGerencialParametros(rs.getInt(CAMPO_TIPO_ITEM)));
		configuracaoGerencialParametrosVo.setDataAtualizacao(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
		configuracaoGerencialParametrosVo.setStatusCadastrado(new DomnSimNao(rs.getInt(CAMPO_STATUS_CADASTRADO)));
	}

	/**
	 * Método utilizado para buscar dados de uma determinada Configuração Gerencial Parametros.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * a Configuração Gerencial Parametros de menor código cadastrado no banco de dados.
	 * <br>Esta pesquisa pode ser realizada pelos seguintes filtros:
	 * <br>- configuracaoGerencialParametrosVo.codigo
	 * @param configuracaoGerencialParametrosVo
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosVo findConfiguracaoGerencialParametros(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindConfiguracaoGerencialParametros(configuracaoGerencialParametrosVo));
			prepareStatemantFindConfiguracaoGerencialParametros(ps, configuracaoGerencialParametrosVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getConfiguracaoGerencialParametros(rs, configuracaoGerencialParametrosVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_CONFIGURACAO_GERENCIAL_PARAMETROS);
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
		return configuracaoGerencialParametrosVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement os parâmetros de pesquisa possíveis ao Consultar ConfiguracaoGerencialParametrosVo.
	 * @param ps (java.sql.PreparedStatement).
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatemantFindConfiguracaoGerencialParametros(final PreparedStatement ps, final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		int contador = 0;
		if (configuracaoGerencialParametrosVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isStringValida(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getValorItem()))
			{
				ps.setString(++contador, ((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getValorItem());
			}
			if (Validador.isStringValida(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getDescricaoItem()))
			{
				ps.setString(++contador, ((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getDescricaoItem());
			}
			if (Validador.isDominioNumericoValido(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getTipoItem()))
			{
				ps.setInt(++contador, ((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getTipoItem().getValorCorrente());
			}
			if (Validador.isDominioNumericoValido(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getStatusCadastrado()))
			{
				ps.setInt(++contador, ((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getStatusCadastrado().getValorCorrente());
			}
		}
	}

	/**
	 * Método para gerar o SQL de consulta apartir de uma ConfiguracaoGerencialParametrosVo.
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindConfiguracaoGerencialParametros(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CGP." + CAMPO_CODIGO_CONFIGURACAO_GERENCIAL_PARAMETROS + ", ");
		sql.append(" CGP." + CAMPO_VALOR_ITEM + ", ");
		sql.append(" CGP." + CAMPO_DESCRICAO_ITEM + ", ");
		sql.append(" CGP." + CAMPO_TIPO_ITEM + ", ");
		sql.append(" CGP." + CAMPO_DATA_ATUALIZACAO_BD + ", ");
		sql.append(" CGP." + CAMPO_STATUS_CADASTRADO + " ");
		sql.append(" FROM " + TABELA_CONFIGURACAO_GERENCIAL_PARAMETROS + " CGP ");
		sql.append(" WHERE 1=1 ");
		if (configuracaoGerencialParametrosVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND CGP." + CAMPO_CODIGO_CONFIGURACAO_GERENCIAL_PARAMETROS + " = ? ");
			}
			if (Validador.isStringValida(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getValorItem()))
			{
				sql.append(" AND UPPER(CGP." + CAMPO_VALOR_ITEM + ")  = UPPER(?) ");
			}
			if (Validador.isStringValida(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getDescricaoItem()))
			{
				sql.append(" AND UPPER(CGP." + CAMPO_DESCRICAO_ITEM + ")  = UPPER(?) ");
			}
			if (Validador.isDominioNumericoValido(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getTipoItem()))
			{
				sql.append(" AND CGP." + CAMPO_TIPO_ITEM + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getStatusCadastrado()))
			{
				sql.append(" AND CGP." + CAMPO_STATUS_CADASTRADO + " = ? ");
			}
			sql.append(" ORDER BY CGP." + CAMPO_CODIGO_CONFIGURACAO_GERENCIAL_PARAMETROS + " ");
		}
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de uma determinada Configuração Gerencial Parametros.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * todas as Configuração Gerencial Parametros cadastradas no banco de dados.
	 * <br>Esta pesquisa pode ser realizada pelos seguintes filtros:
	 * <br>- configuracaoGerencialParametrosVo.codigo
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosVo listConfiguracaoGerencialParametros(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListConfiguracaoGerencialParametros(configuracaoGerencialParametrosVo));
			prepareStatemantListConfiguracaoGerencialParametros(ps, configuracaoGerencialParametrosVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosAtualVo = 
							  new ConfiguracaoGerencialParametrosVo();
				getConfiguracaoGerencialParametros(rs, configuracaoGerencialParametrosAtualVo);
				configuracaoGerencialParametrosVo.getCollVO().add(configuracaoGerencialParametrosAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_CONFIGURACAO_GERENCIAL_PARAMETROS);
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
		return configuracaoGerencialParametrosVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement os parâmetros de pesquisa possíveis para Listar ConfiguracaoGerencialParametrosVo.
	 * @param ps (java.sql.PreparedStatement).
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatemantListConfiguracaoGerencialParametros(final PreparedStatement ps, final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		int contador = 0;
		if (configuracaoGerencialParametrosVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getCodigo());
			}
			if (Validador.isDominioNumericoValido(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getTipoItem()))
			{
				ps.setInt(++contador, ((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getTipoItem().getValorCorrente());
			}
			if (Validador.isDominioNumericoValido(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getStatusCadastrado()))
			{
				ps.setInt(++contador, ((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getStatusCadastrado().getValorCorrente());
			}
		}
	}

	/**
	 * Método para gerar o SQL de consulta apartir de uma ConfiguracaoGerencialParametrosVo.
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListConfiguracaoGerencialParametros(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CGP." + CAMPO_CODIGO_CONFIGURACAO_GERENCIAL_PARAMETROS + ", ");
		sql.append(" CGP." + CAMPO_VALOR_ITEM + ", ");
		sql.append(" CGP." + CAMPO_DESCRICAO_ITEM + ", ");
		sql.append(" CGP." + CAMPO_TIPO_ITEM + ", ");
		sql.append(" CGP." + CAMPO_DATA_ATUALIZACAO_BD + ", ");
		sql.append(" CGP." + CAMPO_STATUS_CADASTRADO + " ");
		sql.append(" FROM " + TABELA_CONFIGURACAO_GERENCIAL_PARAMETROS + " CGP ");
		sql.append(" WHERE 1=1 ");
		if (configuracaoGerencialParametrosVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND CGP." + CAMPO_CODIGO_CONFIGURACAO_GERENCIAL_PARAMETROS + " = ? ");
			}
			if (Validador.isStringValida(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getValorItem()))
			{
				sql.append(" AND UPPER(CGP." + CAMPO_VALOR_ITEM + ") LIKE (UPPER('%" + 
								  ((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getValorItem() + 
								  "%')) ");
			}
			if (Validador.isStringValida(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getDescricaoItem()))
			{
				sql.append(" AND UPPER(CGP." + CAMPO_VALOR_ITEM + ") LIKE (UPPER('%" + 
								  ((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getDescricaoItem() + 
								  "%')) ");
			}
			if (Validador.isDominioNumericoValido(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getTipoItem()))
			{
				sql.append(" AND CGP." + CAMPO_TIPO_ITEM + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((ConfiguracaoGerencialParametrosVo) configuracaoGerencialParametrosVo.getParametroConsulta()).getStatusCadastrado()))
			{
				sql.append(" AND CGP." + CAMPO_STATUS_CADASTRADO + " = ? ");
			}
		}
		sql.append(" ORDER BY CGP." + CAMPO_DESCRICAO_ITEM + " ");
		return sql.toString();
	}
}
