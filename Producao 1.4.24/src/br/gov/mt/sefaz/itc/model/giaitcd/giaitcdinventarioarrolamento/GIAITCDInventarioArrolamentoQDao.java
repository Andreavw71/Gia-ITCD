/**
	* Secretaria de Estado de Fazenda de Mato Grosso – Sefaz-MT
	* Arquivo : GIAITCDInventarioArrolamentoQDao.java
	* Criação : Novembro de 2007
	* Revisão :
	* Log :
	* $Id: GIAITCDInventarioArrolamentoQDao.java,v 1.3 2009/03/13 14:03:43 ricardo.moraes Exp $
*/
package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnEstadoCivil;
import br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoInventario;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoRenuncia;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDInventarioArrolamento;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sefaz.mt.util.UtilStmt;


/**
 * Classe de acesso a dados(Data Access Object)
 * @author Leandro Dorileo
 * @version $Revision: 1.3 $
 */
public class GIAITCDInventarioArrolamentoQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposGIAITCDInventarioArrolamento
{
	/**
	 * Construtor da classe
	 * @param conexao objeto de conexão com o banco de dados
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDInventarioArrolamentoQDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_GIA_ITCD_INVENTARIO_ARROLAMENTO);
	}

	/**
	 * Método para Consulta uma GIA-ITCD Inventário Arrolamento
	 * @param giaitcdVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDInventarioArrolamentoVo findGIAITCDInventarioArrolamento(final GIAITCDInventarioArrolamentoVo giaitcdVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaitcdVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindGIAITCDInventarioArrolamento(giaitcdVo));
			prepareStatementFindGIAITCDInventarioArrolamento(ps, giaitcdVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getGIAITCDInventarioArrolamento(rs, giaitcdVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_INVENTARIO_ARROMANENTO_BENEFICIARIO);
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
		return giaitcdVo;
	}

	/**
	 * Monta a SQL de Consulta GIA-ITCD Inventário
	 * @param giaITCDInventarioArrolamentoVo
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindGIAITCDInventarioArrolamento(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT GIA.").append(CAMPO_ITCTB14_CODIGO_GIA_ITCD).append(" ");
		sql.append(" , GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_DE_CUJUS).append(" ");
		sql.append(" , GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_MEEIRO).append(" ");
		sql.append(" , GIA.").append(CAMPO_DATA_FALECIMENTO).append(" ");
		sql.append(" , GIA.").append(CAMPO_DATA_INVENTARIO).append(" ");
		sql.append(" , GIA.").append(CAMPO_DESCRICAO_JUIZO_COMARCA).append(" ");
		sql.append(" , GIA.").append(CAMPO_NUMERO_HERDEIROS).append(" ");
		sql.append(" , GIA.").append(CAMPO_NUMERO_PROCESSO).append(" ");
		sql.append(" , GIA.").append(CAMPO_REGIMENTO_CASAMENTO).append(" ");
		sql.append(" , GIA.").append(CAMPO_SIGLA_UF_ABERTURA).append(" ");
		sql.append(" , GIA.").append(CAMPO_SITUACAO_ESTADO_CIVIL).append(" ");
		sql.append(" , GIA.").append(CAMPO_FRAC_IDEAL).append(" ");
		sql.append(" , GIA.").append(CAMPO_PERC_MULTA).append(" ");
		sql.append(" , GIA.").append(CAMPO_VALR_MULTA).append(" ");
		sql.append(" , GIA.").append(CAMPO_DATA_PROCESSO).append(" ");
		sql.append(" , GIA.").append(CAMPO_TIPO_RENUNCIA).append(" ");
		sql.append(" , GIA.").append(CAMPO_FLAG_RENUNCIA).append(" ");
		sql.append(" , GIA.").append(CAMPO_FLAG_HERDEIROS_DESCENDENTES).append(" ");
	   sql.append(" , GIA.").append(CAMPO_FLAG_HERDEIROS_ASCENDENTES).append(" ");
	   sql.append(" , GIA.").append(CAMPO_FLAG_MEEIRO_BENEFICIARIO).append(" ");
	   sql.append(" , GIA.").append(CAMPO_TIPO_PROCESSO_INVENTARIO).append(" ");
		sql.append(" FROM ").append(TABELA_GIA_ITCD_INVENTARIO_ARROLAMENTO).append(" GIA ");
		sql.append(" WHERE 1 = 1 ");
		if (giaITCDInventarioArrolamentoVo.isConsultaParametrizada())
		{
			//CODIGO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND GIA.").append(CAMPO_ITCTB14_CODIGO_GIA_ITCD).append(" = ? ");
			}
			//PESSOA DE CUJUS
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getPessoaDeCujus().getNumrContribuinte()))
			{
				sql.append("   AND GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_DE_CUJUS).append(" = ? ");
			}
			//PESSOA MEEIRO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getPessoaMeeiro().getNumrContribuinte()))
			{
				sql.append("   AND GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_MEEIRO).append(" = ? ");
			}
			//DATA DO FALECIMENTO
			if (Validador.isDataValida(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getDataFalecimento()))
			{
				sql.append("   AND GIA.").append(CAMPO_DATA_FALECIMENTO).append(" = ? ");
			}
			//DATA DO INVENTARIO
			if (Validador.isDataValida(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getDataInventario()))
			{
				sql.append("   AND GIA.").append(CAMPO_DATA_INVENTARIO).append(" = ? ");
			}
			//JUIZO COMARCA
			if (Validador.isStringValida(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getDescricaoJuizoComarca()))
			{
				sql.append("   AND GIA.").append(CAMPO_DESCRICAO_JUIZO_COMARCA).append(" = ? ");
			}
			//HERDEIROS
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getNumeroHerdeiros()))
			{
				sql.append("   AND GIA.").append(CAMPO_NUMERO_HERDEIROS).append(" = ? ");
			}
			//NUMERO DO PROCESSO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getNumeroProcesso()))
			{
				sql.append("   AND GIA.").append(CAMPO_NUMERO_PROCESSO).append(" = ? ");
			}
			//REGIME DO CASAMENTO
			if (Validador.isDominioNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getRegimeCasamento()))
			{
				sql.append("   AND GIA.").append(CAMPO_REGIMENTO_CASAMENTO).append(" = ? ");
			}
			//SIGLA DO UF DA ABERTURA DO PROCESSO
			if (Validador.isStringValida(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getUfAbertura().getSiglUf()))
			{
				sql.append("   AND GIA.").append(CAMPO_SIGLA_UF_ABERTURA).append(" = ? ");
			}
			//ESTADO CIVIL
			if (Validador.isDominioNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getEstadoCivilDeCujus()))
			{
				sql.append("   AND GIA.").append(CAMPO_SITUACAO_ESTADO_CIVIL).append(" = ? ");
			}
			//FRACAO IDEAL
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getFracaoIdeal()))
			{
				sql.append("   AND GIA.").append(CAMPO_FRAC_IDEAL).append(" = ? ");
			}
			//PERCENTUAL DE MULTA
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getPercentualMulta()))
			{
				sql.append("   AND GIA.").append(CAMPO_PERC_MULTA).append(" = ? ");
			}
			//VALOR DA MULTA
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getValorMulta()))
			{
				sql.append("   AND GIA.").append(CAMPO_VALR_MULTA).append(" = ? ");
			}
		   if (Validador.isDominioNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getTipoProcessoInventario()))
		   {
		      sql.append("   AND GIA.").append(CAMPO_TIPO_PROCESSO_INVENTARIO).append(" = ? ");
		   }
//			//DATA PROCESSO
//			if (Validador.isDataValida(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getDataProcesso()))
//			{
//				sql.append("   AND GIA.").append(CAMPO_DATA_PROCESSO).append(" = ? ");
//			}
		}
		sql.append(" ORDER BY GIA.").append(CAMPO_ITCTB14_CODIGO_GIA_ITCD).append(" ");
		return sql.toString();
	}

	/**
	 * Monta o Prepare Statement com os dados válidos do Vo
	 * @param ps
	 * @param giaITCDInventarioArrolamentoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindGIAITCDInventarioArrolamento(final PreparedStatement ps, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
		int contador = 0;
		if (giaITCDInventarioArrolamentoVo.isConsultaParametrizada())
		{
			//CODIGO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getCodigo());
			}
			//PESSOA DE CUJUS
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getPessoaDeCujus().getNumrContribuinte()))
			{
				ps.setLong(++contador, ((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getPessoaDeCujus().getNumrContribuinte().longValue());
			}
			//PESSOA MEEIRO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getPessoaMeeiro().getNumrContribuinte()))
			{
				ps.setLong(++contador, ((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getPessoaMeeiro().getNumrContribuinte().longValue());
			}
			//DATA DO FALECIMENTO
			if (Validador.isDataValida(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getDataFalecimento()))
			{
				ps.setDate(++contador, new java.sql.Date(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getDataFalecimento().getTime()));
			}
			//DATA DO INVENTARIO
			if (Validador.isDataValida(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getDataInventario()))
			{
				ps.setDate(++contador, new java.sql.Date(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getDataInventario().getTime()));
			}
			//JUIZO COMARCA
			if (Validador.isStringValida(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getDescricaoJuizoComarca()))
			{
				ps.setString(++contador, ((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getDescricaoJuizoComarca());
			}
			//HERDEIROS
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getNumeroHerdeiros()))
			{
				ps.setInt(++contador, ((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getNumeroHerdeiros());
			}
			//NUMERO DO PROCESSO
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getNumeroProcesso()))
			{
				ps.setLong(++contador, ((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getNumeroProcesso());
			}
			//REGIME DO CASAMENTO
			if (Validador.isDominioNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getRegimeCasamento()))
			{
				ps.setInt(++contador, ((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getRegimeCasamento().getValorCorrente());
			}
			//SIGLA DO UF DA ABERTURA DO PROCESSO
			if (Validador.isStringValida(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getUfAbertura().getSiglUf()))
			{
				ps.setString(++contador, ((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getUfAbertura().getSiglUf());
			}
			//ESTADO CIVIL
			if (Validador.isDominioNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getEstadoCivilDeCujus()))
			{
				ps.setDouble(++contador, ((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getEstadoCivilDeCujus().getValorCorrente());
			}
			//FRACAO IDEAL
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getFracaoIdeal()))
			{
				ps.setDouble(++contador, ((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getFracaoIdeal());
			}
			//PERCENTUAL DE MULTA
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getPercentualMulta()))
			{
				ps.setDouble(++contador, ((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getPercentualMulta());
			}
			//VALOR DA MULTA
			if (Validador.isNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getValorMulta()))
			{
				ps.setDouble(++contador, ((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getValorMulta());
			}
		   if (Validador.isDominioNumericoValido(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getTipoProcessoInventario()))
		   {
		      ps.setDouble(++contador, ((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getTipoProcessoInventario().getValorCorrente());
		   }
//			//DATA PROCESSO
//			if (Validador.isDataValida(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getDataProcesso()))
//			{
//				ps.setDate(++contador, new java.sql.Date(((GIAITCDInventarioArrolamentoVo) giaITCDInventarioArrolamentoVo.getParametroConsulta()).getDataProcesso().getTime()));
//			}
		}
	}

	/**
	 * Monta a GIA-ITCD com os dados do Result Set
	 * @param rs
	 * @param giaITCDInventarioArrolamentoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void getGIAITCDInventarioArrolamento(final ResultSet rs, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
		giaITCDInventarioArrolamentoVo.setCodigo(rs.getLong(CAMPO_ITCTB14_CODIGO_GIA_ITCD));
		giaITCDInventarioArrolamentoVo.getPessoaDeCujus().setNumrContribuinte(new Long(rs.getLong(CAMPO_ACCTB01_NUMERO_PESSOA_DE_CUJUS)));
		giaITCDInventarioArrolamentoVo.getPessoaMeeiro().setNumrContribuinte(new Long(rs.getLong(CAMPO_ACCTB01_NUMERO_PESSOA_MEEIRO)));
		giaITCDInventarioArrolamentoVo.setDataFalecimento(rs.getDate(CAMPO_DATA_FALECIMENTO));
		giaITCDInventarioArrolamentoVo.setDataInventario(rs.getDate(CAMPO_DATA_INVENTARIO));
		giaITCDInventarioArrolamentoVo.setDescricaoJuizoComarca(rs.getString(CAMPO_DESCRICAO_JUIZO_COMARCA));
		giaITCDInventarioArrolamentoVo.setNumeroHerdeiros(rs.getInt(CAMPO_NUMERO_HERDEIROS));
		giaITCDInventarioArrolamentoVo.setNumeroProcesso(rs.getLong(CAMPO_NUMERO_PROCESSO));
		giaITCDInventarioArrolamentoVo.setRegimeCasamento(new DomnRegimeCasamento(rs.getInt(CAMPO_REGIMENTO_CASAMENTO)));
		giaITCDInventarioArrolamentoVo.getUfAbertura().setSiglUf(rs.getString(CAMPO_SIGLA_UF_ABERTURA));
		giaITCDInventarioArrolamentoVo.setEstadoCivilDeCujus(new DomnEstadoCivil(rs.getInt(CAMPO_SITUACAO_ESTADO_CIVIL)));
		giaITCDInventarioArrolamentoVo.setFracaoIdeal(rs.getDouble(CAMPO_FRAC_IDEAL));
		giaITCDInventarioArrolamentoVo.setPercentualMulta(rs.getDouble(CAMPO_PERC_MULTA));
		giaITCDInventarioArrolamentoVo.setValorMulta(rs.getDouble(CAMPO_VALR_MULTA));
		giaITCDInventarioArrolamentoVo.setDataProcesso(rs.getDate(CAMPO_DATA_PROCESSO));
		if(Validador.isNumericoValido(rs.getInt(CAMPO_TIPO_RENUNCIA)))
		{
			giaITCDInventarioArrolamentoVo.setTipoRenuncia(new DomnTipoRenuncia(rs.getInt(CAMPO_TIPO_RENUNCIA)));			
		}
		giaITCDInventarioArrolamentoVo.setRenuncia(new DomnSimNao(rs.getInt(CAMPO_FLAG_RENUNCIA)));
		giaITCDInventarioArrolamentoVo.setHerdeirosDescendentes(new DomnSimNao(rs.getInt(CAMPO_FLAG_HERDEIROS_DESCENDENTES)));
		if(Validador.isNumericoValido(rs.getInt(CAMPO_FLAG_HERDEIROS_ASCENDENTES)))
		{
			giaITCDInventarioArrolamentoVo.setHerdeirosAscendentes(new DomnSimNao(rs.getInt(CAMPO_FLAG_HERDEIROS_ASCENDENTES)));
		}
		giaITCDInventarioArrolamentoVo.setFlagMeeiroBeneficiario(new DomnSimNao(rs.getInt(CAMPO_FLAG_MEEIRO_BENEFICIARIO)));
	   giaITCDInventarioArrolamentoVo.setTipoProcessoInventario(new DomnTipoProcessoInventario(rs.getInt(CAMPO_TIPO_PROCESSO_INVENTARIO)));
	}
}
