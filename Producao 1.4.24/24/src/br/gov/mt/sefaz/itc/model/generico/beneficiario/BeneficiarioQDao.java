package br.gov.mt.sefaz.itc.model.generico.beneficiario;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBeneficiario;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *  Classe de acesso a dados (Data Access Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class BeneficiarioQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposBeneficiario
{
	/**
	 * Construtor que recebe a Conexão com o Banco de Dados
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public BeneficiarioQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Monta um BeneficiarioVo de acordo como ResultSet
	 * @param rs
	 * @param beneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void getBeneficiario(final ResultSet rs, final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(beneficiarioVo);
		beneficiarioVo.setCodigo(rs.getLong(CAMPO_CODIGO_BENEFICIARIO));
		beneficiarioVo.setGiaITCDVo(new GIAITCDVo(rs.getLong(CAMPO_ITCTB14_CODIGO_GIA_ITCD)));
		beneficiarioVo.setPessoaBeneficiaria(new ContribuinteIntegracaoVo(new Long(rs.getLong(CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA))));
		beneficiarioVo.setValorRecebido(rs.getDouble(CAMPO_VALOR_RECEBIDO));
	   beneficiarioVo.setValorRecebidoAvaliacao(rs.getDouble(CAMPO_VALOR_RECEBIDO_AVALIACAO));
	   beneficiarioVo.setFlagDoacaoSucessiva(rs.getInt(CAMPO_INFO_DOACAO_SUCESSIVA));
      beneficiarioVo.setValorRecebidoDoacaoSucessiva(rs.getDouble(CAMPO_VALR_RECB_DOACAO_SUCESSIVA));
      beneficiarioVo.setFlagDoacaoAnteriorManualEprocess(rs.getInt(CAMPO_INFO_DOACAO_ANTR_PROT_MANUAL));
	   beneficiarioVo.setValorItcdBeneficiario(rs.getDouble(CAMPO_VALR_ITCD_BENF));
	}

	/**
	 * Método utilizado para buscar dados de um determinado Beneficiário.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * o Beneficiário de menor código cadastrado no banco de dados.
	 * <br>A busca pode ser feita por:<br>
	 * beneficiarioVo.codigo<br>
	 * beneficiarioVo.giaITCDVo.codigo<br>
	 * beneficiarioVo.pessoaBeneficiaria.numrContribuinte<br>
	 * beneficiarioVo.percetualRecebido
	 * @param beneficiarioVo
	 * @return BeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public BeneficiarioVo findBeneficiario(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(beneficiarioVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindBeneficiario(beneficiarioVo));
			prepareStatementFindBeneficiario(ps, beneficiarioVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getBeneficiario(rs, beneficiarioVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_BENEFICIARIO);
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
		return beneficiarioVo;
	}

	/**
	 * Monta o PreparedStatement baseado no BeneficiarioVo
	 * @param ps
	 * @param beneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindBeneficiario(final PreparedStatement ps, final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(beneficiarioVo);
		int contador = 0;
		if (beneficiarioVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getCodigo());
			}
			// GIA ITCD
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
			{
				ps.setLong(++contador, ((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getGiaITCDVo().getCodigo());
			}
			// PESSOA
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getPessoaBeneficiaria().getNumrContribuinte().longValue()))
			{
				ps.setLong(++contador, ((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getPessoaBeneficiaria().getNumrContribuinte().longValue());
			}
			// VALOR
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getValorRecebido()))
			{
				ps.setDouble(++contador, ((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getValorRecebido());
			}
		   // VALOR RECEBIDO AVALIACAO
		   if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getValorRecebidoAvaliacao()))
		   {
		      ps.setDouble(++contador, ((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getValorRecebidoAvaliacao());
		   }
         
		}
	}

	/**
	 * Cria a SQL de Consulta do BeneficiarioVo
	 * @param beneficiarioVo
	 * @return String
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private String getSQLFindBeneficiario(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(beneficiarioVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT BENEF." + CAMPO_CODIGO_BENEFICIARIO + " ");
		sql.append(" , BENEF." + CAMPO_ITCTB14_CODIGO_GIA_ITCD + " ");
		sql.append(" , BENEF." + CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA + " ");
		sql.append(" , BENEF." + CAMPO_VALOR_RECEBIDO + " ");
	   sql.append(" , BENEF." + CAMPO_VALOR_RECEBIDO_AVALIACAO + " ");
	   sql.append(" , BENEF." + CAMPO_INFO_DOACAO_SUCESSIVA + " ");
      sql.append(" , BENEF." + CAMPO_VALR_RECB_DOACAO_SUCESSIVA + " ");
      sql.append(" , BENEF." + CAMPO_INFO_DOACAO_ANTR_PROT_MANUAL + " ");
	   sql.append(" , BENEF." + CAMPO_VALR_ITCD_BENF + " ");
		sql.append(" FROM " + TABELA_BENEFICIARIO + " BENEF ");
		sql.append(" WHERE 1 = 1 ");
		if (beneficiarioVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND BENEF." + CAMPO_CODIGO_BENEFICIARIO + " = ? ");
			}
			//  GIA ITCD
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
			{
				sql.append("   AND BENEF." + CAMPO_ITCTB14_CODIGO_GIA_ITCD + " = ? ");
			}
			//  PESSOA
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getPessoaBeneficiaria().getNumrContribuinte()))
			{
				sql.append("   AND BENEF." + CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA + " = ? ");
			}
			// VALOr
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getValorRecebido()))
			{
				sql.append("   AND BENEF." + CAMPO_VALOR_RECEBIDO + " = ? ");
			}
		   // VALOR RECEBIDO AVALIACAO
		   if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getValorRecebidoAvaliacao()))
		   {
		      sql.append("   AND BENEF." + CAMPO_VALOR_RECEBIDO_AVALIACAO + " = ? ");
		   }
		}
		sql.append(" ORDER BY BENEF." + CAMPO_CODIGO_BENEFICIARIO + " ");
		return sql.toString();
	}

	/**
	 * Método utilizado para buscar dados de um determinado Beneficiário.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * todos os Beneficiário cadastrados no banco de dados.
	 * <br>A busca pode ser feita por:<br>
	 * beneficiarioVo.codigo<br>
	 * beneficiarioVo.giaITCDVo.codigo<br>
	 * beneficiarioVo.pessoaBeneficiaria.numrContribuinte<br>
	 * beneficiarioVo.percetualRecebido
	 * @param beneficiarioVo
	 * @return BeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public BeneficiarioVo listBeneficiario(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(beneficiarioVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListBenfeitoria(beneficiarioVo));
			prepareStatementListBeneficiario(ps, beneficiarioVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				BeneficiarioVo beneficiarioAtualVo = new BeneficiarioVo();
				getBeneficiario(rs, beneficiarioAtualVo);
				beneficiarioVo.getCollVO().add(beneficiarioAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_BENEFICIARIO);
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
		return beneficiarioVo;
	}

	/**
	 * Monta o PreparedStatement baseado no BeneficiarioVo
	 * @param ps
	 * @param beneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementListBeneficiario(final PreparedStatement ps, final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(beneficiarioVo);
		int contador = 0;
		if (beneficiarioVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getCodigo());
			}
			// GIA ITCD
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
			{
				ps.setLong(++contador, ((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getGiaITCDVo().getCodigo());
			}
			// PESSOA
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getPessoaBeneficiaria().getNumrContribuinte().longValue()))
			{
				ps.setLong(++contador, ((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getPessoaBeneficiaria().getNumrContribuinte().longValue());
			}
			// VALOR
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getValorRecebido()))
			{
				ps.setDouble(++contador, ((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getValorRecebido());
			}
		   // VALOR RECEBIDO AVALIACAO
		   if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getValorRecebidoAvaliacao()))
		   {
		      ps.setDouble(++contador, ((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getValorRecebidoAvaliacao());
		   }
		}
	}

	/**
	 * Cria a SQL de Consulta do Beneficiário
	 * @param beneficiarioVo
	 * @return String
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private String getSQLListBenfeitoria(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(beneficiarioVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT BENEF." + CAMPO_CODIGO_BENEFICIARIO + " ");
		sql.append(" , BENEF." + CAMPO_ITCTB14_CODIGO_GIA_ITCD + " ");
		sql.append(" , BENEF." + CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA + " ");
		sql.append(" , BENEF." + CAMPO_VALOR_RECEBIDO + " ");
	   sql.append(" , BENEF." + CAMPO_VALOR_RECEBIDO_AVALIACAO + " ");
	   sql.append(" , BENEF." + CAMPO_INFO_DOACAO_SUCESSIVA + " ");
      sql.append(" , BENEF." + CAMPO_VALR_RECB_DOACAO_SUCESSIVA + " ");
      sql.append(" , BENEF." + CAMPO_INFO_DOACAO_ANTR_PROT_MANUAL + " ");
	   sql.append(" , BENEF." + CAMPO_VALR_ITCD_BENF + " ");
		sql.append(" FROM " + TABELA_BENEFICIARIO + " BENEF ");
		sql.append(" WHERE 1 = 1 ");
		if (beneficiarioVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND BENEF." + CAMPO_CODIGO_BENEFICIARIO + " = ? ");
			}
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
			{
				sql.append("   AND BENEF." + CAMPO_ITCTB14_CODIGO_GIA_ITCD + " = ? ");
			}
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getPessoaBeneficiaria().getNumrContribuinte()))
			{
				sql.append("   AND BENEF." + CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA + " = ? ");
			}
			if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getValorRecebido()))
			{
				sql.append("   AND BENEF." + CAMPO_VALOR_RECEBIDO + " = ? ");
			}
		   if (Validador.isNumericoValido(((BeneficiarioVo) beneficiarioVo.getParametroConsulta()).getValorRecebidoAvaliacao()))
		   {
		      sql.append("   AND BENEF." + CAMPO_VALOR_RECEBIDO_AVALIACAO + " = ? ");
		   }
		}
		sql.append(" ORDER BY BENEF." + CAMPO_CODIGO_BENEFICIARIO + " ");
		return sql.toString();
	}
}
