package br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposParametroLegislacaoAliquota;
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
public class AliquotaQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposParametroLegislacaoAliquota
{
	/**
	 * Construtora da Classe
	 * @param conexao Conexão que será utilizada para a manutenção dos dados, junto ao banco de dados
	 * @implemented by Daniel Balieiro
	 */
	public AliquotaQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Alimenta o AliquotaVo com os dados o ResultSet
	 * @param rs Contém os dados para alimentar o AliquotaVo
	 * @param aliquotaVo VO que será alimentado
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public void getAliquotaVo(final ResultSet rs, final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		try
		{
			Validador.validaObjeto(rs);
			Validador.validaObjeto(aliquotaVo);
			aliquotaVo.setCodigo(rs.getLong(CAMPO_CODIGO_ALIQUOTA));
			aliquotaVo.setCodigoParametroLegislacao(rs.getLong(CAMPO_ITCTB09_CODIGO_PARAMETRO_LEGISLACAO));
			aliquotaVo.setTipoFatoGerador(new DomnTipoGIA(rs.getInt(CAMPO_TIPO_FATOR_GERADOR)));
			aliquotaVo.setTipoIsencao(new DomnSimNao(rs.getInt(CAMPO_TIPO_ISENCAO)));
			aliquotaVo.setPercentualLegislacaoAliquota(rs.getDouble(CAMPO_PERCENTUAL_LEGISLACAO_ALIQUOTA));
			aliquotaVo.setQuantidadeUPFInicial(rs.getInt(CAMPO_QUANTIDADE_UPF_INICIAL));
			aliquotaVo.setQuantidadeUPFFinal(rs.getInt(CAMPO_QUANTIDADE_UPF_FINAL));
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método utilizado para buscar dados de uma determinada Aliquota.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * a Aliquota de menor código cadastrado no banco de dados.
	 * <br>Esta pesquisa pode ser realizada pelos seguintes filtros:
	 * <br>- aliquotaVo.codigo
	 * <br>- aliquotaVo.codigoParametroLegislacao
	 * @param aliquotaVo Com os dados a serem considerados na Consulta
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return AliquotaVo - Encontrada
	 * @implemented by Daniel Balieiro
	 */
	public AliquotaVo findAliquotaVo(final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, ConsultaException, 
			  ConexaoException
	{
		Validador.validaObjeto(aliquotaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListFindAliquotaVo(aliquotaVo));
			prepareStatemantListFindAliquotaVo(ps, aliquotaVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getAliquotaVo(rs, aliquotaVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_PARAMETRO_LEGISLACAO_ALIQUOTA);
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
		return aliquotaVo;
	}

	/**
	 * Método utilizado para buscar dados de várias Aliquotas.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará todas as Aliquotas do banco de dados.
	 * <br>Esta pesquisa pode ser realizada pelos seguintes filtros:
	 * <br>- aliquotaVo.codigo
	 * <br>- aliquotaVo.codigoParametroLegislacao
	 * @param aliquotaVo Com os dados a serem considerados na Consulta
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return AliquotaVo - Encontrada
	 * @implemented by Daniel Balieiro
	 */
	public AliquotaVo listAliquotaVo(final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, ConsultaException, 
			  ConexaoException
	{
		Validador.validaObjeto(aliquotaVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListFindAliquotaVo(aliquotaVo));
			prepareStatemantListFindAliquotaVo(ps, aliquotaVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				AliquotaVo aliquotaAtualVo = new AliquotaVo();
				getAliquotaVo(rs, aliquotaAtualVo);
				aliquotaVo.getCollVO().add(aliquotaAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_PARAMETRO_LEGISLACAO_ALIQUOTA);
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
		return aliquotaVo;
	}

	/**
	 * Método utilizado para adicionar ao PreparedStatement os parâmetros de pesquisa possíveis ao Consultar AliquotaVo
	 * @param ps PreparedStatement que receberá as informações
	 * @param aliquotaVo Que contém os dados
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatemantListFindAliquotaVo(final PreparedStatement ps, final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(aliquotaVo);
		try
		{
			int contador = 0;
			if (aliquotaVo.isConsultaParametrizada())
			{
				if (Validador.isNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getCodigo()))
				{
					ps.setLong(++contador, ((AliquotaVo) aliquotaVo.getParametroConsulta()).getCodigo());
				}
				if (Validador.isNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getCodigoParametroLegislacao()))
				{
					ps.setLong(++contador, ((AliquotaVo) aliquotaVo.getParametroConsulta()).getCodigoParametroLegislacao());
				}
				if (Validador.isDominioNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getTipoFatoGerador()))
				{
					ps.setInt(++contador, ((AliquotaVo) aliquotaVo.getParametroConsulta()).getTipoFatoGerador().getValorCorrente());
				}
				if (Validador.isDominioNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getTipoIsencao()))
				{
					ps.setInt(++contador, ((AliquotaVo) aliquotaVo.getParametroConsulta()).getTipoIsencao().getValorCorrente());
				}
				if (Validador.isNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getPercentualLegislacaoAliquota()))
				{
					ps.setDouble(++contador, ((AliquotaVo) aliquotaVo.getParametroConsulta()).getPercentualLegislacaoAliquota());
				}
				if (Validador.isNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getQuantidadeUPFInicial()))
				{
					ps.setInt(++contador, ((AliquotaVo) aliquotaVo.getParametroConsulta()).getQuantidadeUPFInicial());
				}
				if (Validador.isNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getQuantidadeUPFFinal()))
				{
					ps.setInt(++contador, ((AliquotaVo) aliquotaVo.getParametroConsulta()).getQuantidadeUPFFinal());
				}
				if (Validador.isNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getQuantidadeUPFConsulta()))
				{
					ps.setInt(++contador, ((AliquotaVo) aliquotaVo.getParametroConsulta()).getQuantidadeUPFConsulta());
				}
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método para gerar o SQL de consulta apartir de um AliquotaVo
	 * @param aliquotaVo AliquotaVo que será consultado para gerar o SQL
	 * @throws ObjetoObrigatorioException
	 * @return String - SQL Gerada
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListFindAliquotaVo(final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(aliquotaVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ALIQUOTA." + CAMPO_CODIGO_ALIQUOTA + ", ");
		sql.append(" ALIQUOTA." + CAMPO_ITCTB09_CODIGO_PARAMETRO_LEGISLACAO + ", ");
		sql.append(" ALIQUOTA." + CAMPO_TIPO_FATOR_GERADOR + ", ");
		sql.append(" ALIQUOTA." + CAMPO_TIPO_ISENCAO + ", ");
		sql.append(" ALIQUOTA." + CAMPO_PERCENTUAL_LEGISLACAO_ALIQUOTA + ", ");
		sql.append(" ALIQUOTA." + CAMPO_QUANTIDADE_UPF_INICIAL + ", ");
		sql.append(" ALIQUOTA." + CAMPO_QUANTIDADE_UPF_FINAL + " ");
		sql.append(" FROM " + TABELA_PARAMETRO_LEGISLACAO_ALIQUOTA + " ALIQUOTA ");
		sql.append(" WHERE 1=1 ");
		if (aliquotaVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND ALIQUOTA." + CAMPO_CODIGO_ALIQUOTA + " = ? ");
			}
			if (Validador.isNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getCodigoParametroLegislacao()))
			{
				sql.append(" AND ALIQUOTA." + CAMPO_ITCTB09_CODIGO_PARAMETRO_LEGISLACAO + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getTipoFatoGerador()))
			{
				sql.append(" AND ALIQUOTA." + CAMPO_TIPO_FATOR_GERADOR + " = ? ");
			}
			if (Validador.isDominioNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getTipoIsencao()))
			{
				sql.append(" AND ALIQUOTA." + CAMPO_TIPO_ISENCAO + " = ? ");
			}
			if (Validador.isNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getPercentualLegislacaoAliquota()))
			{
				sql.append(" AND ALIQUOTA." + CAMPO_PERCENTUAL_LEGISLACAO_ALIQUOTA + " = ? ");
			}
			if (Validador.isNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getQuantidadeUPFInicial()))
			{
				sql.append(" AND ALIQUOTA." + CAMPO_QUANTIDADE_UPF_INICIAL + " = ? ");
			}
			if (Validador.isNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getQuantidadeUPFFinal()))
			{
				sql.append(" AND ALIQUOTA." + CAMPO_QUANTIDADE_UPF_FINAL + " = ? ");
			}
			if (Validador.isNumericoValido(((AliquotaVo) aliquotaVo.getParametroConsulta()).getQuantidadeUPFConsulta()))
			{
				sql.append(" AND ? BETWEEN ALIQUOTA." + CAMPO_QUANTIDADE_UPF_INICIAL + " AND ALIQUOTA." + 
								  CAMPO_QUANTIDADE_UPF_FINAL + " ");
			}
			sql.append(" ORDER BY ALIQUOTA." + CAMPO_CODIGO_ALIQUOTA + " ");
		}
		return sql.toString();
	}
}
