package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnEstadoConservacao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAcesso;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelUrbano;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Classe de acesso a dados (Data Access Object)
 * @author Lucas Nascimento
 * @version $Revision: 1.2 $
 */
public class FichaImovelUrbanoQDao extends AbstractDao implements CamposFichaImovelUrbano, TabelasITC
{

	/**
	 * Construtor padrão recebendo a conexão com o banco de dados.
	 * @param conexao
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Médoto que preenche os dados de uma Ficha Imóvel Urbano a partir do Result Set
	 * @param rs Result Set
	 * @param fichaImovelUrbanoVo Ficha Imovel Urbano - Value Object VO
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Lucas Nascimento
	 */
	private void getFichaImovelUrbano(final ResultSet rs, final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(fichaImovelUrbanoVo);
		fichaImovelUrbanoVo.setCodigo(rs.getLong(CAMPO_CODIGO_IMOVEL_URBANO));
		fichaImovelUrbanoVo.getEnderecoVo().setCodgEndereco(rs.getLong(CAMPO_ACCTB06_CODIGO_ENDERECO));
		fichaImovelUrbanoVo.getConstrucaoVo().setCodigo(rs.getLong(CAMPO_ITCTB13_CODIGO_CONSTRUCAO));
		fichaImovelUrbanoVo.getBemTributavelVo().setCodigo(rs.getLong(CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRBT));
		fichaImovelUrbanoVo.setEstadoConservacao(new DomnEstadoConservacao(rs.getInt(CAMPO_TIPO_CONSERVACAO)));
		fichaImovelUrbanoVo.setQuantidadeAreaTotal(rs.getDouble(CAMPO_QUANTIDADE_AREA_TOTAL));
		fichaImovelUrbanoVo.setQuantidadeAreaConstruida(rs.getDouble(CAMPO_QUANTIDADE_AREA_CONSTRUIDA));
		fichaImovelUrbanoVo.setTipoAcesso(new DomnTipoAcesso(rs.getInt(CAMPO_TIPO_ACESSO)));
		fichaImovelUrbanoVo.setValorMercadoTotal(rs.getDouble(CAMPO_VALOR_MERCADO_TOTAL));
		fichaImovelUrbanoVo.setValorVenalIptu(rs.getDouble(CAMPO_VALOR_VENAL_IPTU));
	   fichaImovelUrbanoVo.getIptuVo().setCodigo(rs.getLong(CAMPO_ITCTB54_IPTU));
	   fichaImovelUrbanoVo.getIptuPrefeituraVo().setCodigo(rs.getLong(CAMPO_ITCTB57_IPTU_PREFEITURA));
      
      fichaImovelUrbanoVo.setValorPercentualTransmitido(rs.getDouble(CAMPO_VALR_PERC_TRANSMITIDO));
      fichaImovelUrbanoVo.setValorInfomado(rs.getDouble(CAMPO_VALR_INFORMADO));
	}

	/**
	 * Médoto para pesquisa de uma Ficha de Imóvel Urbano
	 * @param fichaImovelUrbanoVo Ficha Imï¿½vel Urbano - Value Object
	 * @return FichaImovelUrbanoVo Value Object
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoVo findFichaImovelUrbano(FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindFichaImovelUrbano(fichaImovelUrbanoVo));
			preparedStatementFindFichaImovelUrbano(ps, fichaImovelUrbanoVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getFichaImovelUrbano(rs, fichaImovelUrbanoVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_IMOVEL_URBANO);
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
		return fichaImovelUrbanoVo;
	}

	/**
	 * Método usado para buscar/listar um grupo de registros de Imóveis Urbanos.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método 
	 * retornará todos os Imóveis Urbanos cadastrados no sistema.
	 * A pesquisa parametrizada pode ser: pelo código ou por qualquer outro atributo de um Imï¿½vel Urbano.
	 * 
	 * @param fichaImovelUrbanoVo VO de Ficha Imóvel Urbano(Value Object) - usado para passar os critérios da consulta
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return BemTributavelVo
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoVo listFichaImovelUrbano(FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindFichaImovelUrbano(fichaImovelUrbanoVo));
			preparedStatementFindFichaImovelUrbano(ps, fichaImovelUrbanoVo);
			Collection listaFichaImovelUrbano = new ArrayList();
			for (rs = ps.executeQuery(); rs.next(); )
			{
				FichaImovelUrbanoVo fichaImovelUrbanoVoAtual = new FichaImovelUrbanoVo();
				getFichaImovelUrbano(rs, fichaImovelUrbanoVoAtual);
				listaFichaImovelUrbano.add(fichaImovelUrbanoVoAtual);
			}
			if (Validador.isCollectionValida(listaFichaImovelUrbano))
			{
				fichaImovelUrbanoVo.setCollVO(listaFichaImovelUrbano);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.LISTAR_IMOVEL_URBANO);
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
		return fichaImovelUrbanoVo;
	}

	/**
	 * Método que retorna o SQL para a Query
	 * @param fichaImovelUrbanoVo
	 * @return String
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	private String getSQLFindFichaImovelUrbano(final FichaImovelUrbanoVo fichaImovelUrbanoVo)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT FICHA.").append(CAMPO_CODIGO_IMOVEL_URBANO).append(" , FICHA.").append(CAMPO_ACCTB06_CODIGO_ENDERECO).append(" , FICHA.").append(CAMPO_ITCTB13_CODIGO_CONSTRUCAO).append(" , FICHA.").append(CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRBT).append(" , FICHA.").append(CAMPO_TIPO_CONSERVACAO).append(" , FICHA.").append(CAMPO_QUANTIDADE_AREA_TOTAL).append(" , FICHA.").append(CAMPO_QUANTIDADE_AREA_CONSTRUIDA).append(" , FICHA.").append(CAMPO_TIPO_ACESSO).append(" , FICHA.").append(CAMPO_VALOR_MERCADO_TOTAL).append(" , FICHA.").append(CAMPO_VALOR_VENAL_IPTU).append(" , FICHA.").append(CAMPO_ITCTB54_IPTU).append(" , FICHA.").append(CAMPO_ITCTB57_IPTU_PREFEITURA).append(" , FICHA.").append(CAMPO_VALR_PERC_TRANSMITIDO).append(" , FICHA.").append(CAMPO_VALR_INFORMADO)
      .append(" FROM ").append(TABELA_IMOVEL_URBANO).append(" FICHA ").append(" WHERE 1 = 1 ");
		if (fichaImovelUrbanoVo != null && fichaImovelUrbanoVo.isConsultaParametrizada())
		{
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getCodigo()))
			{
				sql.append(" AND FICHA.").append(CAMPO_CODIGO_IMOVEL_URBANO).append(" = ? ");
			}
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getEnderecoVo().getCodgEndereco()))
			{
				sql.append(" AND FICHA.").append(CAMPO_ACCTB06_CODIGO_ENDERECO).append(" = ? ");
			}
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getConstrucaoVo().getCodigo()))
			{
				sql.append(" AND FICHA.").append(CAMPO_ITCTB13_CODIGO_CONSTRUCAO).append(" = ? ");
			}
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getBemTributavelVo().getCodigo()))
			{
				sql.append(" AND FICHA.").append(CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRBT).append(" = ? ");
			}
			if (Validador.isDominioNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getEstadoConservacao()))
			{
				sql.append(" AND FICHA.").append(CAMPO_TIPO_CONSERVACAO).append(" = ? ");
			}
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getQuantidadeAreaTotal()))
			{
				sql.append(" AND FICHA.").append(CAMPO_QUANTIDADE_AREA_TOTAL).append(" = ? ");
			}
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getQuantidadeAreaConstruida()))
			{
				sql.append(" AND FICHA.").append(CAMPO_QUANTIDADE_AREA_CONSTRUIDA).append(" = ? ");
			}
			if (Validador.isDominioNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getTipoAcesso()))
			{
				sql.append(" AND FICHA.").append(CAMPO_TIPO_ACESSO).append(" = ? ");
			}
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getValorMercadoTotal()))
			{
				sql.append(" AND FICHA.").append(CAMPO_VALOR_MERCADO_TOTAL).append(" = ? ");
			}
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getValorVenalIptu()))
			{
				sql.append(" AND FICHA.").append(CAMPO_VALOR_VENAL_IPTU).append(" = ? ");
			}
         //CAMPO_ITCTB54_IPTU
         if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getIptuVo().getCodigo()))
         {
             sql.append(" AND FICHA.").append(CAMPO_ITCTB54_IPTU).append(" = ? ");
         }
         //CAMPO_ITCTB57_IPTU_PREFEITURA
         if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getIptuPrefeituraVo().getCodigo()))
         {
             sql.append(" AND FICHA.").append(CAMPO_ITCTB57_IPTU_PREFEITURA).append(" = ? ");
         }
        
		   //CAMPO_VALR_PERC_TRANSMITIDO
		   if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getValorPercentualTransmitido()))
		   {
		       sql.append(" AND FICHA.").append(CAMPO_VALR_PERC_TRANSMITIDO).append(" = ? ");
		   }
        
         //CAMPO_VALR_TOTAL
		   if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getValorInfomado()))
		   {
		       sql.append(" AND FICHA.").append(CAMPO_VALR_INFORMADO).append(" = ? ");
		   }
  
         
		}
		sql.append(" ORDER BY FICHA.").append(CAMPO_CODIGO_IMOVEL_URBANO);
		return sql.toString();
	}

	/**
	 * Método para preparar o Statement para a Query
	 * @param ps
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementFindFichaImovelUrbano(final PreparedStatement ps, final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		Validador.validaObjeto(ps);
		int contador = 0;
		if (fichaImovelUrbanoVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getCodigo());
			}
			// ENDERECO
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getEnderecoVo().getCodgEndereco()))
			{
				ps.setLong(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getEnderecoVo().getCodgEndereco());
			}
			// CONSTRUCAO
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getConstrucaoVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getConstrucaoVo().getCodigo());
			}
			// BEM TRIBUTAVEL
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getBemTributavelVo().getCodigo()))
			{
				ps.setLong(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getBemTributavelVo().getCodigo());
			}
			// ESTADO DE CONSERVACAO
			if (Validador.isDominioNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getEstadoConservacao()))
			{
				ps.setInt(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getEstadoConservacao().getValorCorrente());
			}
			// QUANTIDADE AREA TOTAL
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getQuantidadeAreaTotal()))
			{
				ps.setDouble(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getQuantidadeAreaTotal());
			}
			// QUANTIDADE AREA CONSTRUIDA
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getQuantidadeAreaConstruida()))
			{
				ps.setDouble(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getQuantidadeAreaConstruida());
			}
			// TIPO ACESSO
			if (Validador.isDominioNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getTipoAcesso()))
			{
				ps.setInt(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getTipoAcesso().getValorCorrente());
			}
			// MERCADO TOTAL
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getValorMercadoTotal()))
			{
				ps.setDouble(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getValorMercadoTotal());
			}
			// VALOR VENAL IPTU
			if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getValorVenalIptu()))
			{
				ps.setDouble(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getValorVenalIptu());
			}
         //CAMPO_ITCTB54_IPTU
         if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getIptuVo().getCodigo()))
         {
             ps.setLong(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getIptuVo().getCodigo());
         }
         //CAMPO_ITCTB57_IPTU_PREFEITURA
          if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getIptuPrefeituraVo().getCodigo()))
          {
             ps.setLong(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getIptuPrefeituraVo().getCodigo());
          }
		   //CAMPO_VALR_PERC_TRANSMITIDO
		   if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getValorPercentualTransmitido()))
		   {
		       ps.setDouble(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getValorPercentualTransmitido());
		   }
		   //CAMPO_VALR_TOTAL
		   if (Validador.isNumericoValido(((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getValorInfomado()))
		   {
		       ps.setDouble(++contador, ((FichaImovelUrbanoVo) fichaImovelUrbanoVo.getParametroConsulta()).getValorInfomado());
		   }
		}
	}
}
