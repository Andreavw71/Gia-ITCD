package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRural;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe para consultas da entidade Ficha Imovel Rural 
 * @author Daniel Balieiro
 * @version $Revision: 1.3 $
 */
public class FichaImovelRuralQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposFichaImovelRural
{
	/**
	 * Construtor da classe.
	 * @param conexao
	 * @implemented by Marlo Eichenberg Motta
	 */
	public FichaImovelRuralQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método que monta o FichaImovelRuralVo de acordo com os dados do ResultSet
	 * 
	 * @param rs
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @return FichaImovelRuralVo
	 * @implemented by Daniel Balieiro
	 */
	private FichaImovelRuralVo getFichaImovelRural(final ResultSet rs, final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(fichaImovelRuralVo);
		fichaImovelRuralVo.setCodigo(rs.getLong(CAMPO_CODIGO_IMOVEL_RURAL));
		fichaImovelRuralVo.getEnderecoVo().setCodgEndereco(rs.getLong(CAMPO_ACCTB06_CODIGO_ENDERECO));
		fichaImovelRuralVo.getBemTributavelVo().setCodigo(rs.getLong(CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRIBUTARIO));
		fichaImovelRuralVo.setDescricaoDenominacao(rs.getString(CAMPO_DESCRICAO_DENOMINACAO));
		fichaImovelRuralVo.setQuantidadeDistancia(rs.getDouble(CAMPO_QUANTIDADE_DISTANCIA));
		fichaImovelRuralVo.setAreaTotal(rs.getDouble(CAMPO_AREA_TOTAL));
		fichaImovelRuralVo.setNumericoIndea(rs.getLong(CAMPO_NUMERICO_INDEA));
		fichaImovelRuralVo.setCodigoReceitaFederal(rs.getLong(CAMPO_CODIGO_RECEITA_FEDERAL));
		fichaImovelRuralVo.setSituacaoPastagem(new DomnSimNao(rs.getInt(CAMPO_SITUACAO_PASTAGEM)));
		fichaImovelRuralVo.setAreaPastagem(rs.getDouble(CAMPO_AREA_PASTAGEM));
		fichaImovelRuralVo.setValorPastagem(rs.getDouble(CAMPO_VALOR_PASTAGEM));
		fichaImovelRuralVo.setSituacaoAcessaoNatural(new DomnSimNao(rs.getInt(CAMPO_SITUACAO_ACESSAO_NATURAL)));
		fichaImovelRuralVo.setValorAcessaoNatural(rs.getDouble(CAMPO_VALOR_ACESSAO_NATURAL));
		fichaImovelRuralVo.setValorMercadoImovel(rs.getDouble(CAMPO_VALOR_MERCADO_IMOVEL));
		fichaImovelRuralVo.setValorMaquinaEquipamento(rs.getDouble(CAMPO_VALOR_MAQUINA_EQUIPAMENTO));
		fichaImovelRuralVo.setValorOutro(rs.getDouble(CAMPO_VALOR_OUTRO));
		fichaImovelRuralVo.setValorITR(rs.getDouble(CAMPO_VALOR_ITR));
	   fichaImovelRuralVo.setDistanciaAsfalto(rs.getDouble(CAMPO_QTDE_DISTANCIA_ASFALTO));
	   fichaImovelRuralVo.getCriterioMunicipioVo().setCodigo(rs.getLong(CAMPO_NUMR_SEQC_CRIT_MUNC));
	   fichaImovelRuralVo.getBaseCalculoImovelRuralVo().setCodigo(rs.getLong(CAMPO_NUMR_SEQC_BASE_CALC));
		return fichaImovelRuralVo;
	}

	/**
	 * Método de consulta de uma Ficha Imóvel Rural.<br>
	 * Se for encontrada mais de uma Ficha, será retornada a de menor código.<br><br>
	 * Os atributos para a consulta são:<br>
	 * <b>-fichaImovelRuralVo.codigo</b><br>
	 * <b>-fichaImovelRuralVo.endereco.codigo</b><br>
	 * <b>-fichaImovelRuralVo.bemTributavel.codigo</b><br>
	 * <b>-fichaImovelRuralVo.divisas</b><br>
	 * <b>-fichaImovelRuralVo.situacaoAcessaoNatural</b><br>
	 * <b>-fichaImovelRuralVo.situacaoPastagem</b><br>
	 * <b>-fichaImovelRuralVo.valorAcessaoNatural</b><br>
	 * <b>-fichaImovelRuralVo.valorITR</b><br>
	 * <b>-fichaImovelRuralVo.valorMaquinaEquipamento</b><br>
	 * <b>-fichaImovelRuralVo.valorMercadoImovel</b><br>
	 * <b>-fichaImovelRuralVo.valorOutro</b><br>
	 * <b>-fichaImovelRuralVo.valorPastagem</b><br>
	 * <b>-fichaImovelRuralVo.fichaImovelRuralConstrucaoVo.codigo</b><br>
	 * <b>-fichaImovelRuralVo.fichaImovelRuralBenfeitoriaVo.codigo</b><br>
	 * <b>-fichaImovelRuralVo.fichaImovelRuralCulturaVo.codigo</b><br>
	 * <b>-fichaImovelRuralVo.fichaImovelRuralRebanhoVo.codigo</b><br>
	 * <b>-fichaImovelRuralVo.quantidadeDistancia</b><br>
	 * <b>-fichaImovelRuralVo.areaPastagem</b><br>
	 * <b>-fichaImovelRuralVo.areaTotal</b><br>
	 * <b>-fichaImovelRuralVo.codigoReceitaFederal</b><br>
	 * <b>-fichaImovelRuralVo.numericoIndea</b><br>
	 * <b>-fichaImovelRuralVo.descricaoDenominacao</b><br>
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return FichaImovelRuralVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralVo findFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindFichaImovelRural(fichaImovelRuralVo));
			prepareStatementFindFichaImovelRural(ps, fichaImovelRuralVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getFichaImovelRural(rs, fichaImovelRuralVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_FICHA_IMOVEL_RURAL);
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
		return fichaImovelRuralVo;
	}

	/**
	 * Método que monta o PreparedStatement de acordo com os dados válidos do FichaImovelRuralVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindFichaImovelRural(final PreparedStatement ps, final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralVo);
		int contador = 0;
		if (fichaImovelRuralVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getCodigo());
			}
			// CODIGO ENDERECO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getEnderecoVo().getCodgEndereco()))
			{
				ps.setLong(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getEnderecoVo().getCodgEndereco());
			}
			// BEM TRIBUTAVEL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getBemTributavelVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getBemTributavelVo().getCodigo());
			}
			// DENOMINACAO
			if (Validador.isStringValida(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getDescricaoDenominacao()))
			{
				ps.setString(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getDescricaoDenominacao());
			}
			// DISTANCIA
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getQuantidadeDistancia()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getQuantidadeDistancia());
			}
			// AREA TOTAL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getAreaTotal()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getAreaTotal());
			}
			// INDEA
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getNumericoIndea()))
			{
				ps.setLong(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getNumericoIndea());
			}
			// CODIGO RECEITA FEDERAL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getCodigoReceitaFederal()))
			{
				ps.setLong(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getCodigoReceitaFederal());
			}
			// SITUACAO PASTAGEM
			if (Validador.isDominioNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getSituacaoPastagem()))
			{
				ps.setInt(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getSituacaoPastagem().getValorCorrente());
			}
			// AREA PASTAGEM
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getAreaPastagem()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getAreaPastagem());
			}
			// VALOR PASTAGEM
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorPastagem()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorPastagem());
			}
			// SITUACAO ACESSO NATURAL
			if (Validador.isDominioNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getSituacaoAcessaoNatural()))
			{
				ps.setInt(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getSituacaoAcessaoNatural().getValorCorrente());
			}
			// VALOR ACESSO NATURAL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorAcessaoNatural()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorAcessaoNatural());
			}
			// VALOR MERCADO IMOVEL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorMercadoImovel()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorMercadoImovel());
			}
			// VALOR MAQUINA EQUIPAMENTO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorMaquinaEquipamento()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorMaquinaEquipamento());
			}
			// VALOR OUTRO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorOutro()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorOutro());
			}
			// VALOR ITR
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorITR()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorITR());
			}
		   // DISTANCIA ASFALTO
		   if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getDistanciaAsfalto()))
		   {
		      ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getDistanciaAsfalto());
		   }
		}
	}

	/**
	 * Método que monta a SQL de consulta de uma Ficha Imóvel Rural
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT FIR." + CAMPO_CODIGO_IMOVEL_RURAL + " ");
		sql.append(" , FIR." + CAMPO_ACCTB06_CODIGO_ENDERECO + " ");
		sql.append(" , FIR." + CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRIBUTARIO + " ");
		sql.append(" , FIR." + CAMPO_DESCRICAO_DENOMINACAO + " ");
		sql.append(" , FIR." + CAMPO_QUANTIDADE_DISTANCIA + " ");
		sql.append(" , FIR." + CAMPO_AREA_TOTAL + " ");
		sql.append(" , FIR." + CAMPO_NUMERICO_INDEA + " ");
		sql.append(" , FIR." + CAMPO_CODIGO_RECEITA_FEDERAL + " ");
		sql.append(" , FIR." + CAMPO_SITUACAO_PASTAGEM + " ");
		sql.append(" , FIR." + CAMPO_AREA_PASTAGEM + " ");
		sql.append(" , FIR." + CAMPO_VALOR_PASTAGEM + " ");
		sql.append(" , FIR." + CAMPO_SITUACAO_ACESSAO_NATURAL + " ");
		sql.append(" , FIR." + CAMPO_VALOR_ACESSAO_NATURAL + " ");
		sql.append(" , FIR." + CAMPO_VALOR_MERCADO_IMOVEL + " ");
		sql.append(" , FIR." + CAMPO_VALOR_MAQUINA_EQUIPAMENTO + " ");
		sql.append(" , FIR." + CAMPO_VALOR_OUTRO + " ");
		sql.append(" , FIR." + CAMPO_VALOR_ITR + " ");
	   sql.append(" , FIR." + CAMPO_QTDE_DISTANCIA_ASFALTO + " ");
	   sql.append(" , FIR." + CAMPO_NUMR_SEQC_CRIT_MUNC + " ");
	   sql.append(" , FIR." + CAMPO_NUMR_SEQC_BASE_CALC + " ");
		sql.append(" FROM " + TABELA_FICHA_IMOVEL_RURAL + " FIR ");
		sql.append(" WHERE 1 = 1 ");
		if (fichaImovelRuralVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND FIR." + CAMPO_CODIGO_IMOVEL_RURAL + " = ? ");
			}
			// CODIGO ENDERECO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getEnderecoVo().getCodgEndereco()))
			{
				sql.append("   AND FIR." + CAMPO_ACCTB06_CODIGO_ENDERECO + " = ? ");
			}
			// BEM TRIBUTAVEL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getBemTributavelVo().getCodigo()))
			{
				sql.append("   AND FIR." + CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRIBUTARIO + " = ? ");
			}
			// DENOMINACAO
			if (Validador.isStringValida(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getDescricaoDenominacao()))
			{
				sql.append("   AND UPPER(FIR." + CAMPO_DESCRICAO_DENOMINACAO + ") = UPPER(?) ");
			}
			// DISTANCIA
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getQuantidadeDistancia()))
			{
				sql.append("   AND FIR." + CAMPO_QUANTIDADE_DISTANCIA + " = ? ");
			}
			// AREA TOTAL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getAreaTotal()))
			{
				sql.append("   AND FIR." + CAMPO_AREA_TOTAL + " = ? ");
			}
			// INDEA
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getNumericoIndea()))
			{
				sql.append("   AND FIR." + CAMPO_NUMERICO_INDEA + " = ? ");
			}
			// CODIGO RECEITA FEDERAL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getCodigoReceitaFederal()))
			{
				sql.append("   AND FIR." + CAMPO_CODIGO_RECEITA_FEDERAL + " = ? ");
			}
			// SITUACAO PASTAGEM
			if (Validador.isDominioNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getSituacaoPastagem()))
			{
				sql.append("   AND FIR." + CAMPO_SITUACAO_PASTAGEM + " = ? ");
			}
			// AREA PASTAGEM
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getAreaPastagem()))
			{
				sql.append("   AND FIR." + CAMPO_AREA_PASTAGEM + " = ? ");
			}
			// VALOR PASTAGEM
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorPastagem()))
			{
				sql.append("   AND FIR." + CAMPO_VALOR_PASTAGEM + " = ? ");
			}
			// SITUACAO ACESSO NATURAL
			if (Validador.isDominioNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getSituacaoAcessaoNatural()))
			{
				sql.append("   AND FIR." + CAMPO_SITUACAO_ACESSAO_NATURAL + " = ? ");
			}
			// VALOR ACESSO NATURAL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorAcessaoNatural()))
			{
				sql.append("   AND FIR." + CAMPO_VALOR_ACESSAO_NATURAL + " = ? ");
			}
			// VALOR MERCADO IMOVEL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorMercadoImovel()))
			{
				sql.append("   AND FIR." + CAMPO_VALOR_MERCADO_IMOVEL + " = ? ");
			}
			// VALOR MAQUINA EQUIPAMENTO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorMaquinaEquipamento()))
			{
				sql.append("   AND FIR." + CAMPO_VALOR_MAQUINA_EQUIPAMENTO + " = ? ");
			}
			// VALOR OUTRO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorOutro()))
			{
				sql.append("   AND FIR." + CAMPO_VALOR_OUTRO + " = ? ");
			}
			// VALOR ITR
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorITR()))
			{
				sql.append("   AND FIR." + CAMPO_VALOR_ITR + " = ? ");
			}
		   // DISTANCIA  ASFALTO
		   if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getDistanciaAsfalto()) )
		   {
		      sql.append("   AND FIR." + CAMPO_QTDE_DISTANCIA_ASFALTO + " = ? ");
		   }
		}
		sql.append(" ORDER BY FIR." + CAMPO_CODIGO_IMOVEL_RURAL + " ");
		return sql.toString();
	}

	/**
	 * Método que monta o PreparedStatement de acordo com o FichaImovelRuralVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementListFichaImovelRural(final PreparedStatement ps, final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralVo);
		int contador = 0;
		if (fichaImovelRuralVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getCodigo());
			}
			// CODIGO ENDERECO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getEnderecoVo().getCodgEndereco()))
			{
				ps.setLong(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getEnderecoVo().getCodgEndereco());
			}
			// BEM TRIBUTAVEL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getBemTributavelVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getBemTributavelVo().getCodigo());
			}
			// DISTANCIA
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getQuantidadeDistancia()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getQuantidadeDistancia());
			}
			// AREA TOTAL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getAreaTotal()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getAreaTotal());
			}
			// INDEA
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getNumericoIndea()))
			{
				ps.setLong(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getNumericoIndea());
			}
			// CODIGO RECEITA FEDERAL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getCodigoReceitaFederal()))
			{
				ps.setLong(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getCodigoReceitaFederal());
			}
			// SITUACAO PASTAGEM
			if (Validador.isDominioNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getSituacaoPastagem()))
			{
				ps.setInt(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getSituacaoPastagem().getValorCorrente());
			}
			// AREA PASTAGEM
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getAreaPastagem()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getAreaPastagem());
			}
			// VALOR PASTAGEM
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorPastagem()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorPastagem());
			}
			// SITUACAO ACESSO NATURAL
			if (Validador.isDominioNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getSituacaoAcessaoNatural()))
			{
				ps.setInt(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getSituacaoAcessaoNatural().getValorCorrente());
			}
			// VALOR ACESSO NATURAL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorAcessaoNatural()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorAcessaoNatural());
			}
			// VALOR MERCADO IMOVEL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorMercadoImovel()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorMercadoImovel());
			}
			// VALOR MAQUINA EQUIPAMENTO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorMaquinaEquipamento()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorMaquinaEquipamento());
			}
			// VALOR OUTRO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorOutro()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorOutro());
			}
			// VALOR ITR
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorITR()))
			{
				ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorITR());
			}
		   // DISTANCIA ASFALTO
		   if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getDistanciaAsfalto()))
		   {
		      ps.setDouble(++contador, ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getDistanciaAsfalto());
		   }
		}
	}

	/**
	 * Método de consulta de uma Ficha Imóvel Rural.<br><br>
	 * Os atributos para a consulta são:<br>
	 * <b>-fichaImovelRuralVo.codigo</b><br>
	 * <b>-fichaImovelRuralVo.endereco.codigo</b><br>
	 * <b>-fichaImovelRuralVo.bemTributavel.codigo</b><br>
	 * <b>-fichaImovelRuralVo.divisas</b><br>
	 * <b>-fichaImovelRuralVo.situacaoAcessaoNatural</b><br>
	 * <b>-fichaImovelRuralVo.situacaoPastagem</b><br>
	 * <b>-fichaImovelRuralVo.valorAcessaoNatural</b><br>
	 * <b>-fichaImovelRuralVo.valorITR</b><br>
	 * <b>-fichaImovelRuralVo.valorMaquinaEquipamento</b><br>
	 * <b>-fichaImovelRuralVo.valorMercadoImovel</b><br>
	 * <b>-fichaImovelRuralVo.valorOutro</b><br>
	 * <b>-fichaImovelRuralVo.valorPastagem</b><br>
	 * <b>-fichaImovelRuralVo.fichaImovelRuralConstrucaoVo.codigo</b><br>
	 * <b>-fichaImovelRuralVo.fichaImovelRuralBenfeitoriaVo.codigo</b><br>
	 * <b>-fichaImovelRuralVo.fichaImovelRuralCulturaVo.codigo</b><br>
	 * <b>-fichaImovelRuralVo.fichaImovelRuralRebanhoVo.codigo</b><br>
	 * <b>-fichaImovelRuralVo.quantidadeDistancia</b><br>
	 * <b>-fichaImovelRuralVo.areaPastagem</b><br>
	 * <b>-fichaImovelRuralVo.areaTotal</b><br>
	 * <b>-fichaImovelRuralVo.codigoReceitaFederal</b><br>
	 * <b>-fichaImovelRuralVo.numericoIndea</b><br>
	 * <b>-fichaImovelRuralVo.descricaoDenominacao</b><br>
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return FichaImovelRuralVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralVo listFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLListFichaImovelRural(fichaImovelRuralVo));
			prepareStatementListFichaImovelRural(ps, fichaImovelRuralVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				FichaImovelRuralVo fichaImovelRuralAtualVo = new FichaImovelRuralVo();
				getFichaImovelRural(rs, fichaImovelRuralAtualVo);
				fichaImovelRuralVo.getCollVO().add(fichaImovelRuralAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_FICHA_IMOVEL_RURAL);
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
		return fichaImovelRuralVo;
	}

	/**
	 * Método que monta a SQL de listagem do Ficha Imóvel Rural
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLListFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT FIR." + CAMPO_CODIGO_IMOVEL_RURAL + " ");
		sql.append(" , FIR." + CAMPO_ACCTB06_CODIGO_ENDERECO + " ");
		sql.append(" , FIR." + CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRIBUTARIO + " ");
		sql.append(" , FIR." + CAMPO_DESCRICAO_DENOMINACAO + " ");
		sql.append(" , FIR." + CAMPO_QUANTIDADE_DISTANCIA + " ");
		sql.append(" , FIR." + CAMPO_AREA_TOTAL + " ");
		sql.append(" , FIR." + CAMPO_NUMERICO_INDEA + " ");
		sql.append(" , FIR." + CAMPO_CODIGO_RECEITA_FEDERAL + " ");
		sql.append(" , FIR." + CAMPO_SITUACAO_PASTAGEM + " ");
		sql.append(" , FIR." + CAMPO_AREA_PASTAGEM + " ");
		sql.append(" , FIR." + CAMPO_VALOR_PASTAGEM + " ");
		sql.append(" , FIR." + CAMPO_SITUACAO_ACESSAO_NATURAL + " ");
		sql.append(" , FIR." + CAMPO_VALOR_ACESSAO_NATURAL + " ");
		sql.append(" , FIR." + CAMPO_VALOR_MERCADO_IMOVEL + " ");
		sql.append(" , FIR." + CAMPO_VALOR_MAQUINA_EQUIPAMENTO + " ");
		sql.append(" , FIR." + CAMPO_VALOR_OUTRO + " ");
		sql.append(" , FIR." + CAMPO_VALOR_ITR + " ");
	   sql.append(" , FIR." + CAMPO_QTDE_DISTANCIA_ASFALTO + " ");
	   sql.append(" , FIR." + CAMPO_NUMR_SEQC_CRIT_MUNC + " ");
	   sql.append(" , FIR." + CAMPO_NUMR_SEQC_BASE_CALC + " ");
		sql.append(" FROM " + TABELA_FICHA_IMOVEL_RURAL + " FIR ");
		sql.append(" WHERE 1 = 1 ");
		if (fichaImovelRuralVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND FIR." + CAMPO_CODIGO_IMOVEL_RURAL + " = ? ");
			}
			// CODIGO ENDERECO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getEnderecoVo().getCodgEndereco()))
			{
				sql.append("   AND FIR." + CAMPO_ACCTB06_CODIGO_ENDERECO + " = ? ");
			}
			// BEM TRIBUTAVEL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getBemTributavelVo().getCodigo()))
			{
				sql.append("   AND FIR." + CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRIBUTARIO + " = ? ");
			}
			// DENOMINACAO
			if (Validador.isStringValida(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getDescricaoDenominacao()))
			{
				sql.append("   AND UPPER(FIR." + CAMPO_DESCRICAO_DENOMINACAO + ") LIKE (UPPER('%" + 
								  ((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getDescricaoDenominacao() + 
								  "%')) ");
			}
			// DISTANCIA
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getQuantidadeDistancia()))
			{
				sql.append("   AND FIR." + CAMPO_QUANTIDADE_DISTANCIA + " = ? ");
			}
			// AREA TOTAL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getAreaTotal()))
			{
				sql.append("   AND FIR." + CAMPO_AREA_TOTAL + " = ? ");
			}
			// INDEA
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getNumericoIndea()))
			{
				sql.append("   AND FIR." + CAMPO_NUMERICO_INDEA + " = ? ");
			}
			// CODIGO RECEITA FEDERAL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getCodigoReceitaFederal()))
			{
				sql.append("   AND FIR." + CAMPO_CODIGO_RECEITA_FEDERAL + " = ? ");
			}
			// SITUACAO PASTAGEM
			if (Validador.isDominioNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getSituacaoPastagem()))
			{
				sql.append("   AND FIR." + CAMPO_SITUACAO_PASTAGEM + " = ? ");
			}
			// AREA PASTAGEM
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getAreaPastagem()))
			{
				sql.append("   AND FIR." + CAMPO_AREA_PASTAGEM + " = ? ");
			}
			// VALOR PASTAGEM
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorPastagem()))
			{
				sql.append("   AND FIR." + CAMPO_VALOR_PASTAGEM + " = ? ");
			}
			// SITUACAO ACESSO NATURAL
			if (Validador.isDominioNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getSituacaoAcessaoNatural()))
			{
				sql.append("   AND FIR." + CAMPO_SITUACAO_ACESSAO_NATURAL + " = ? ");
			}
			// VALOR ACESSO NATURAL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorAcessaoNatural()))
			{
				sql.append("   AND FIR." + CAMPO_VALOR_ACESSAO_NATURAL + " = ? ");
			}
			// VALOR MERCADO IMOVEL
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorMercadoImovel()))
			{
				sql.append("   AND FIR." + CAMPO_VALOR_MERCADO_IMOVEL + " = ? ");
			}
			// VALOR MAQUINA EQUIPAMENTO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorMaquinaEquipamento()))
			{
				sql.append("   AND FIR." + CAMPO_VALOR_MAQUINA_EQUIPAMENTO + " = ? ");
			}
			// VALOR OUTRO
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorOutro()))
			{
				sql.append("   AND FIR." + CAMPO_VALOR_OUTRO + " = ? ");
			}
			// VALOR ITR
			if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getValorITR()))
			{
				sql.append("   AND FIR." + CAMPO_VALOR_ITR + " = ? ");
			}
		   // DISTANCIA ASFALTO
		   if (Validador.isNumericoValido(((FichaImovelRuralVo) fichaImovelRuralVo.getParametroConsulta()).getDistanciaAsfalto()))
		   {
		      sql.append("   AND FIR." + CAMPO_QTDE_DISTANCIA_ASFALTO + " = ? ");
		   }
		}
		sql.append(" ORDER BY FIR." + CAMPO_CODIGO_IMOVEL_RURAL + " ");
		return sql.toString();
	}
}
