/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDInventarioArrolamentoDao.java
 * Revisão:
 * Data revisão:
 * $Id: GIAITCDInventarioArrolamentoDao.java,v 1.2 2009/03/13 14:02:24 ricardo.moraes Exp $
 */
package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDInventarioArrolamento;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.UtilStmt;


/**
 * Classe de acesso a dados( Data Access Object)
 * @author Lucas Nascimento
 * @version $Revision: 1.2 $
 */
public class GIAITCDInventarioArrolamentoDao extends AbstractDao implements TabelasITC, SequencesITC, CamposGIAITCDInventarioArrolamento
{

	/**
	 * Construtor da classe
	 * @param conexao objeto de conexão com o banco de dados
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDInventarioArrolamentoDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_GIA_ITCD_INVENTARIO_ARROLAMENTO);
	}

	/**
	 * Retorna todos os campos da tabela de Inventario/Arrolamento (ITCTB15_ITCD_INVENTARIO)
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public String[] getCamposIventarioArrolamento()
	{
		return new String[]
		{ 
				CAMPO_ITCTB14_CODIGO_GIA_ITCD
				,CAMPO_ACCTB01_NUMERO_PESSOA_MEEIRO
				,CAMPO_ACCTB01_NUMERO_PESSOA_DE_CUJUS
				,CAMPO_SITUACAO_ESTADO_CIVIL
				,CAMPO_REGIMENTO_CASAMENTO
				,CAMPO_NUMERO_PROCESSO
				,CAMPO_DATA_INVENTARIO
				,CAMPO_SIGLA_UF_ABERTURA
				,CAMPO_DATA_FALECIMENTO
				,CAMPO_NUMERO_HERDEIROS
				,CAMPO_DESCRICAO_JUIZO_COMARCA
				,CAMPO_FRAC_IDEAL
				,CAMPO_PERC_MULTA
				,CAMPO_VALR_MULTA
				,CAMPO_DATA_PROCESSO
				,CAMPO_TIPO_RENUNCIA
				,CAMPO_FLAG_RENUNCIA
				,CAMPO_FLAG_HERDEIROS_DESCENDENTES
				,CAMPO_FLAG_HERDEIROS_ASCENDENTES
				,CAMPO_FLAG_MEEIRO_BENEFICIARIO
            ,CAMPO_TIPO_PROCESSO_INVENTARIO
		};
	}

	/**
	 * Inclui informações sobre um Iventário/Arrolamento no banco de dados
	 * @param giaITCDIventarioArrolamentoVo
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Lucas Nascimento
	 */
	public void insertGIAITCDIventarioArrolamento(final GIAITCDInventarioArrolamentoVo giaITCDIventarioArrolamentoVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(giaITCDIventarioArrolamentoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCamposIventarioArrolamento());
		try
		{
			GeradorLogSefazMT.gerar(giaITCDIventarioArrolamentoVo, DomnOperacao.OPERACAO_INSERT, giaITCDIventarioArrolamentoVo.getNumeroParticao(), giaITCDIventarioArrolamentoVo.getCodigoTransacao(), giaITCDIventarioArrolamentoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertGIAITCDIventarioArrolamento(ps, giaITCDIventarioArrolamentoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO);
		}
		/*	   catch (LogSefazException e)
	   {
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_IVENTARIO_ARROLAMENTO);
		}*/
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO);
		}
		finally
		{
			if (ps != null)
			{
				try
				{
					close(ps);
					ps = null;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Método responável por adicionar os parâmetros válidos na instrução SQL (java.sql.PreparedStatement)
	 * @param ps (java.sql.PreparedStatement)
	 * @param giaITCDInventarioArrolamentoVo (Value Object)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementInsertGIAITCDIventarioArrolamento(final PreparedStatement ps, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
		int contador = 0;
		//CAMPO_ITCTB14_CODIGO_GIA_ITCD,
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getCodigo()))
		{
			ps.setLong(++contador, giaITCDInventarioArrolamentoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//CAMPO_ACCTB01_NUMERO_PESSOA_MEEIRO,
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrContribuinte()))
		{
			ps.setLong(++contador, giaITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrContribuinte().longValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//CAMPO_ACCTB01_NUMERO_PESSOA_DE_CUJUS,
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getPessoaDeCujus().getNumrContribuinte()))
		{
			ps.setLong(++contador, giaITCDInventarioArrolamentoVo.getPessoaDeCujus().getNumrContribuinte().longValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//CAMPO_SITUACAO_ESTADO_CIVIL,
		if (Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus()))
		{
			ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//CAMPO_REGIMENTO_CASAMENTO,
		if (Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getRegimeCasamento()))
		{
			ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getRegimeCasamento().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//CAMPO_NUMERO_PROCESSO,
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getNumeroProcesso()))
		{
			ps.setLong(++contador, giaITCDInventarioArrolamentoVo.getNumeroProcesso());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//CAMPO_DATA_INVENTARIO,
		if (Validador.isDataValida(giaITCDInventarioArrolamentoVo.getDataInventario()))
		{
			ps.setDate(++contador, new java.sql.Date(giaITCDInventarioArrolamentoVo.getDataInventario().getTime()));
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
		}
		//CAMPO_SIGLA_UF_ABERTURA,
		if (Validador.isStringValida(giaITCDInventarioArrolamentoVo.getUfAbertura().getSiglUf()))
		{
			ps.setString(++contador, giaITCDInventarioArrolamentoVo.getUfAbertura().getSiglUf());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		//CAMPO_DATA_FALECIMENTO,
		if (Validador.isDataValida(giaITCDInventarioArrolamentoVo.getDataFalecimento()))
		{
			ps.setDate(++contador, new java.sql.Date(giaITCDInventarioArrolamentoVo.getDataFalecimento().getTime()));
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
		}
		//CAMPO_NUMERO_HERDEIROS,
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getNumeroHerdeiros()))
		{
			ps.setLong(++contador, giaITCDInventarioArrolamentoVo.getNumeroHerdeiros());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//CAMPO_DESCRICAO_JUIZO_COMARCA
		if (Validador.isStringValida(giaITCDInventarioArrolamentoVo.getDescricaoJuizoComarca()))
		{
			ps.setString(++contador, giaITCDInventarioArrolamentoVo.getDescricaoJuizoComarca());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		//	   CAMPO_FRAC_IDEAL,
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getFracaoIdeal()))
		{
			ps.setDouble(++contador, giaITCDInventarioArrolamentoVo.getFracaoIdeal());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	   CAMPO_PERC_MULTA,
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getPercentualMulta()))
		{
			ps.setDouble(++contador, giaITCDInventarioArrolamentoVo.getPercentualMulta());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	   CAMPO_VALR_MULTA		
		if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorMulta()))
		{
			ps.setDouble(++contador, giaITCDInventarioArrolamentoVo.getValorMulta());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA_PROCESSO
		if(Validador.isDataValida(giaITCDInventarioArrolamentoVo.getDataProcesso()))
		{
			ps.setDate(++contador, new Date(giaITCDInventarioArrolamentoVo.getDataProcesso().getTime()));
		}
		else 
		{
			ps.setNull(++contador, Types.DATE);
		}
		//TIPO_RENUNCIA
		if(Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getTipoRenuncia()))
		{
			ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getTipoRenuncia().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//FLAG_RENUNCIA
		if(Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getRenuncia()))
		{
			ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getRenuncia().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//FLAG_POSS_HERD_DESCENDENTE
		if(Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getHerdeirosDescendentes()))
		{
			ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getHerdeirosDescendentes().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//FLAG_POSS_HERD_ASCENDENTE
		if(Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes()))
		{
			ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//FLAG_MEEIRO_BENEFICIARIO
		if(Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario()))
		{
			ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método para atualizar uma GIA-ITCD Inventário Arrolamento
	 * @param giaitcdinventarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateGIAITCDInventarioArrolamento(final GIAITCDInventarioArrolamentoVo giaitcdinventarioVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(giaitcdinventarioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCamposIventarioArrolamento(), new String[] { CAMPO_ITCTB14_CODIGO_GIA_ITCD });
		try
		{
			GeradorLogSefazMT.gerar(giaitcdinventarioVo, DomnOperacao.OPERACAO_UPDATE, giaitcdinventarioVo.getNumeroParticao(), giaitcdinventarioVo.getCodigoTransacao(), giaitcdinventarioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateGIAITCDIventarioArrolamento(ps, giaitcdinventarioVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.INCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.INCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO);
		}
		finally
		{
			if (ps != null)
			{
				try
				{
					close(ps);
					ps = null;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Método responável por adicionar os parâmetros válidos na instrução SQL (java.sql.PreparedStatement)
	 * @param ps (java.sql.PreparedStatement)
	 * @param giaITCDInventarioArrolamentoVo (Value Object)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementUpdateGIAITCDIventarioArrolamento(final PreparedStatement ps, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
		int contador = 0;
	   //CAMPO_ACCTB01_NUMERO_PESSOA_MEEIRO,
	   if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrContribuinte()))
	   {
	      ps.setLong(++contador, giaITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrContribuinte().longValue());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   //CAMPO_ACCTB01_NUMERO_PESSOA_DE_CUJUS,
	   if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getPessoaDeCujus().getNumrContribuinte()))
	   {
	      ps.setLong(++contador, giaITCDInventarioArrolamentoVo.getPessoaDeCujus().getNumrContribuinte().longValue());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   //CAMPO_SITUACAO_ESTADO_CIVIL,
	   if (Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus()))
	   {
	      ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   //CAMPO_REGIMENTO_CASAMENTO,
	   if (Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getRegimeCasamento()))
	   {
	      ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getRegimeCasamento().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   //CAMPO_NUMERO_PROCESSO,
	   if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getNumeroProcesso()))
	   {
	      ps.setLong(++contador, giaITCDInventarioArrolamentoVo.getNumeroProcesso());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   //CAMPO_DATA_INVENTARIO,
	   if (Validador.isDataValida(giaITCDInventarioArrolamentoVo.getDataInventario()))
	   {
	      ps.setDate(++contador, new java.sql.Date(giaITCDInventarioArrolamentoVo.getDataInventario().getTime()));
	   }
	   else
	   {
	      ps.setNull(++contador, Types.DATE);
	   }
	   //CAMPO_SIGLA_UF_ABERTURA,
	   if (Validador.isStringValida(giaITCDInventarioArrolamentoVo.getUfAbertura().getSiglUf()))
	   {
	      ps.setString(++contador, giaITCDInventarioArrolamentoVo.getUfAbertura().getSiglUf());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.VARCHAR);
	   }
	   //CAMPO_DATA_FALECIMENTO,
	   if (Validador.isDataValida(giaITCDInventarioArrolamentoVo.getDataFalecimento()))
	   {
	      ps.setDate(++contador, new java.sql.Date(giaITCDInventarioArrolamentoVo.getDataFalecimento().getTime()));
	   }
	   else
	   {
	      ps.setNull(++contador, Types.DATE);
	   }
	   //CAMPO_NUMERO_HERDEIROS,
	   if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getNumeroHerdeiros()))
	   {
	      ps.setLong(++contador, giaITCDInventarioArrolamentoVo.getNumeroHerdeiros());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   //CAMPO_DESCRICAO_JUIZO_COMARCA
	   if (Validador.isStringValida(giaITCDInventarioArrolamentoVo.getDescricaoJuizoComarca()))
	   {
	      ps.setString(++contador, giaITCDInventarioArrolamentoVo.getDescricaoJuizoComarca());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.VARCHAR);
	   }
	   //    CAMPO_FRAC_IDEAL,
	   if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getFracaoIdeal()))
	   {
	      ps.setDouble(++contador, giaITCDInventarioArrolamentoVo.getFracaoIdeal());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   //    CAMPO_PERC_MULTA,
	   if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getPercentualMulta()))
	   {
	      ps.setDouble(++contador, giaITCDInventarioArrolamentoVo.getPercentualMulta());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   //    CAMPO_VALR_MULTA     
	   if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorMulta()))
	   {
	      ps.setDouble(++contador, giaITCDInventarioArrolamentoVo.getValorMulta());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   // DATA_PROCESSO
	   if(Validador.isDataValida(giaITCDInventarioArrolamentoVo.getDataProcesso()))
	   {
	      ps.setDate(++contador, new Date(giaITCDInventarioArrolamentoVo.getDataProcesso().getTime()));
	   }
	   else 
	   {
	      ps.setNull(++contador, Types.DATE);
	   }
	   //TIPO_RENUNCIA
	   if(Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getTipoRenuncia()))
	   {
	      ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getTipoRenuncia().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   //FLAG_RENUNCIA
	   if(Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getRenuncia()))
	   {
	      ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getRenuncia().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   //FLAG_POSS_HERD_DESCENDENTE
	   if(Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getHerdeirosDescendentes()))
	   {
	      ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getHerdeirosDescendentes().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   //FLAG_POSS_HERD_ASCENDENTE
	   if(Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes()))
	   {
	      ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   //FLAG_MEEIRO_BENEFICIARIO
	   if(Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario()))
	   {
	      ps.setInt(++contador, giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   //CAMPO_ITCTB14_CODIGO_GIA_ITCD,
	   if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getCodigo()))
	   {
	      ps.setLong(++contador, giaITCDInventarioArrolamentoVo.getCodigo());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }  
	}
}
