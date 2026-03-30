/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDTemporarioQDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 10/12/2007
 */
package br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSituacaoProcessamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDTemporario;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe de acesso a dados (Data Access Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class GIAITCDTemporarioQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposGIAITCDTemporario
{
	/**
	 * Construtor que recebe a Conexão com o Banco de Dados.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDTemporarioQDao(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Retorna o GIA ITCD Temporário de acordo com o ResultSet.
	 * @param rs
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	private void getGIAITCDTemporario(final ResultSet rs, final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(rs);
		Validador.validaObjeto(giaITCDTemporarioVo);
		giaITCDTemporarioVo.setCodigo(rs.getLong(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO));
		giaITCDTemporarioVo.setDataGIAITCDTemporario(rs.getDate(CAMPO_DATA_CRIACAO));
      try
      { 
         rs.getBlob(CAMPO_INFO_GIA_TEMPORARIO);
         if(rs.wasNull())
         {
            ExibirLOG.exibirLog("GIA não possui BLOB no BD",giaITCDTemporarioVo.getCodigo() );
         }else
         { 
            giaITCDTemporarioVo.setInputStream(rs.getBlob(CAMPO_INFO_GIA_TEMPORARIO).getBinaryStream());    
         } 
      }catch( Exception e)
      {
         e.printStackTrace();
      }
      
	   StatusGIAITCDVo status = new StatusGIAITCDVo();
	   int numrStatus = rs.getInt("STAT_TB27");
	   if (rs.wasNull())
	   {
	      status.setStatusGIAITCD(new DomnStatusGIAITCD(rs.getInt(CAMPO_STATUS_ITCD)));        
	   }
	   else
	   {
	      status.setStatusGIAITCD(new DomnStatusGIAITCD(numrStatus));
	   }  
		giaITCDTemporarioVo.setStatusITCD(status);
		giaITCDTemporarioVo.setSenhaGIAITCD(rs.getString(CAMPO_INFO_SENHA));
		giaITCDTemporarioVo.setCodigoAutenticidade(rs.getString(CAMPO_CODIGO_AUTENTICIDADE));
		giaITCDTemporarioVo.setPrazoProtocolar(rs.getDate(CAMPO_DATA_PRAZO_PROTOCOLAR));
		giaITCDTemporarioVo.setCodigoResponsavel(rs.getLong(CAMPO_CODIGO_RESPONSAVEL));
		giaITCDTemporarioVo.setGiaConfirmada(new DomnSimNao(rs.getInt(CAMPO_GIA_CONFIRMADA)));
		giaITCDTemporarioVo.setDataAtualizacao(rs.getTimestamp(CAMPO_DATA_ATUALIZACAO_BD));
	   giaITCDTemporarioVo.setGiaTempXML(  rs.getString(CAMPO_INFO_GIA_TEMPORARIO_XML));
	   giaITCDTemporarioVo.setNumeroVersaoGIAITCD(new DomnVersaoGIAITCD(rs.getInt(CAMPO_NUMERO_VERSAO_GIAITCD)));
	   giaITCDTemporarioVo.setTipoProtocoloGIA(new DomnTipoProtocolo(rs.getInt(CAMPO_TIPO_PROTOCOLO_GIA)));
	   giaITCDTemporarioVo.setSituacaoProcessamento(new DomnSituacaoProcessamento(rs.getInt(CAMPO_SITUACAO_PROCESSAMENTO)));
	   giaITCDTemporarioVo.setDescricaoMensagemSituacaoErrro(  rs.getString(CAMPO_DESCRICAO_MENSAGEM_SITUACAO_ERRO));
	}

	/**
	 * Método utilizado para buscar dados de uma determinada GIA ITCD Temporário.
	 * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
	 * a GIA ITCD Temporária de menor código cadastrado no banco de dados.
	 * <br>A busca pode ser feita por:<br>
	 * giaITCDTemporarioVo.codigo<br>
	 * giaITCDTemporarioVo.dataGIAITCDTemporario<br>
	 * giaITCDTemporarioVo.statusITCD<br>
	 * giaITCDTemporarioVo.senhaGIAITCD
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDTemporarioVo findGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindGIAITCDTemporario(giaITCDTemporarioVo));
			prepareStatementFindGIAITCDTemporario(ps, giaITCDTemporarioVo);
			rs = ps.executeQuery();
			if (rs.next())
			{
				getGIAITCDTemporario(rs, giaITCDTemporarioVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_TEMPORARIO);
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
		return giaITCDTemporarioVo;
	}

	/**
	 * Monta o PreparedStatement baseado no GIAITCDTemporarioVo.
	 * @param ps (java.sql.PreparedStatement).
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindGIAITCDTemporario(final PreparedStatement ps, final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDTemporarioVo);
		int contador = 0;
		if (giaITCDTemporarioVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigo());
			}
			// DATA CRIACAO
			if (Validador.isDataValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getDataGIAITCDTemporario()))
			{
				ps.setDate(++contador, new java.sql.Date(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getDataGIAITCDTemporario().getTime()));
			}
			// STATUS ITCD
			if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getStatusITCD().getStatusGIAITCD()))
			{
				ps.setLong(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getStatusITCD().getStatusGIAITCD().getValorCorrente());
			}
			// SENHA
			if (Validador.isStringValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getSenhaGIAITCD()))
			{
				ps.setString(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getSenhaGIAITCD());
			}
			// CODIGO AUTENTICIDADE
			if (Validador.isStringValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigoAutenticidade()))
			{
				ps.setString(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigoAutenticidade());
			}
			// DATA PRAZO PROTOCOLAR
			if (Validador.isDataValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getPrazoProtocolar()))
			{
				ps.setDate(++contador, new java.sql.Date(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getPrazoProtocolar().getTime()));
			}
			// GIA CONFIRMADA
			if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getGiaConfirmada()))
			{
				ps.setInt(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getGiaConfirmada().getValorCorrente());
			}
			// CODIGO RESPONSAVEL
			if (Validador.isNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigoResponsavel()))
			{
				ps.setLong(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigoResponsavel());
			}
		   if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getNumeroVersaoGIAITCD()))
		   {
		      ps.setInt(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getNumeroVersaoGIAITCD().getValorCorrente());
		   }
		   if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getTipoProtocoloGIA()))
		   {
		      ps.setInt(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getTipoProtocoloGIA().getValorCorrente());
		   }
         //SITC_PROCESSAMENTO
		   if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getSituacaoProcessamento()))
		   {
		      ps.setInt(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getSituacaoProcessamento().getValorCorrente());
		   }
		   //DESC_MENSAGEM_SITUACAO_ERRO
		   if (Validador.isStringValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getDescricaoMensagemSituacaoErrro()))
		   {
		      ps.setString(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getDescricaoMensagemSituacaoErrro());
		   }
         
		}
	}

	/**
	 * Cria a SQL de Consulta do GIA ITCD Temporário.
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT GIA.").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO);
		sql.append(" , GIA.").append(CAMPO_DATA_CRIACAO);
		sql.append(" , GIA.").append(CAMPO_INFO_GIA_TEMPORARIO);
		sql.append(" , GIA.").append(CAMPO_STATUS_ITCD);
	   sql.append(" , STATUS.").append(CamposStatusGIAITCD.CAMPO_STAT_ITCD).append(" AS STAT_TB27");
		sql.append(" , GIA.").append(CAMPO_INFO_SENHA);
		sql.append(" , GIA.").append(CAMPO_CODIGO_AUTENTICIDADE);
		sql.append(" , GIA.").append(CAMPO_DATA_PRAZO_PROTOCOLAR);
		sql.append(" , GIA.").append(CAMPO_CODIGO_RESPONSAVEL);
		sql.append(" , GIA.").append(CAMPO_GIA_CONFIRMADA);
		sql.append(" , GIA.").append(CAMPO_DATA_ATUALIZACAO_BD);
      sql.append(" , GIA.").append(CAMPO_INFO_GIA_TEMPORARIO_XML);
	   sql.append(" , GIA.").append(CAMPO_NUMERO_VERSAO_GIAITCD);
	   sql.append(" , GIA.").append(CAMPO_TIPO_PROTOCOLO_GIA);
	   sql.append(" , GIA.").append(CAMPO_SITUACAO_PROCESSAMENTO);
	   sql.append(" , GIA.").append(CAMPO_DESCRICAO_MENSAGEM_SITUACAO_ERRO);
		sql.append(" FROM ").append(TABELA_GIA_ITCD_TEMPORARIO).append(" GIA ");
	   sql.append(" INNER JOIN " + TABELA_STATUS_GIA_ITCD + " STATUS ");
	   sql.append(" ON STATUS." + CamposStatusGIAITCD.CAMPO_CODIGO_GIA_TEMPORARIA + " = GIA." + CAMPO_CODIGO_GIA_ITCD_TEMPORARIO);
		sql.append(" WHERE 1 = 1 ");
      
		if (giaITCDTemporarioVo.isConsultaParametrizada())
		{      
		   sql.append(" AND STATUS." + CamposStatusGIAITCD.CAMPO_CODIGO_STATUS_ITCD + " = (");
		   sql.append(" SELECT MAX(STAT." + CamposStatusGIAITCD.CAMPO_CODIGO_STATUS_ITCD + ") FROM ");
		   sql.append(TABELA_STATUS_GIA_ITCD + " STAT ");
		   sql.append(" WHERE STAT." + CamposStatusGIAITCD.CAMPO_CODIGO_GIA_TEMPORARIA + " = GIA." + CAMPO_CODIGO_GIA_ITCD_TEMPORARIO + 
		         ") ");
               
			if (Validador.isNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigo()))
			{
				sql.append("   AND GIA.").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO).append(" = ? ");
			}
			if (Validador.isDataValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getDataGIAITCDTemporario()))
			{
				sql.append("   AND GIA.").append(CAMPO_DATA_CRIACAO).append(" = ? ");
			}
			if (Validador.isNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getStatusITCD().getCodigo()))
			{
				sql.append("   AND GIA.").append(CAMPO_STATUS_ITCD).append(" = ? ");
			}
			if (Validador.isStringValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getSenhaGIAITCD()))
			{
				sql.append("   AND GIA.").append(CAMPO_INFO_SENHA).append(" = ? ");
			}
			if (Validador.isStringValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigoAutenticidade()))
			{
				sql.append("   AND GIA.").append(CAMPO_CODIGO_AUTENTICIDADE).append(" = ? ");
			}
			if (Validador.isDataValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getPrazoProtocolar()))
			{
				sql.append("   AND GIA.").append(CAMPO_DATA_PRAZO_PROTOCOLAR).append(" = ? ");
			}
			if (Validador.isNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigoResponsavel()))
			{
				sql.append("   AND GIA.").append(CAMPO_CODIGO_RESPONSAVEL).append(" = ? ");
			}
			if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getGiaConfirmada()))
			{
				sql.append(" AND GIA.").append(CAMPO_GIA_CONFIRMADA).append(" = ? ");
			}
		   if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getNumeroVersaoGIAITCD()))
		   {
		      sql.append(" AND GIA.").append(CAMPO_NUMERO_VERSAO_GIAITCD).append(" = ? ");
		   }
		   if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getTipoProtocoloGIA()))
		   {
		      sql.append(" AND GIA.").append(CAMPO_TIPO_PROTOCOLO_GIA).append(" = ? ");
		   }
		   if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getSituacaoProcessamento()))
		   {
		      sql.append(" AND GIA.").append(CAMPO_SITUACAO_PROCESSAMENTO).append(" = ? ");
		   }
		}
		sql.append(" ORDER BY GIA.").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO);
		return sql.toString();
	}
   
   /**
    * Método utilizado para buscar dados de várias GIA ITCD Temporário.
    * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método retornará
    * todas as GIA ITCD Temporária cadastradas no banco de dados.
    * <br>A busca pode ser feita por:<br>
    * giaITCDTemporarioVo.codigo<br>
    * giaITCDTemporarioVo.dataGIAITCDTemporario<br>
    * giaITCDTemporarioVo.statusITCD<br>
    * giaITCDTemporarioVo.senhaGIAITCD
    * @param giaITCDTemporarioVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @return GIAITCDTemporarioVo
    * @implemented by Daniel Balieiro
    */
   public GIAITCDTemporarioVo listGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(giaITCDTemporarioVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindGIAITCDTemporario(giaITCDTemporarioVo));
         prepareStatementFindGIAITCDTemporario(ps, giaITCDTemporarioVo);
         rs = ps.executeQuery();
         while (rs.next())
         {
            GIAITCDTemporarioVo giaITCDTemporarioAtualVo = new GIAITCDTemporarioVo();
            getGIAITCDTemporario(rs, giaITCDTemporarioAtualVo);
            giaITCDTemporarioVo.getCollVO().add(giaITCDTemporarioAtualVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_TEMPORARIO);
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
      return giaITCDTemporarioVo;
   }
   
   
	/**
	 * Método para listar as GIA-ITCD Temporárias
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDTemporarioVo listGIAITCDTemporarioAtivas(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = conn.prepareStatement(getSQLFindGIAITCDTemporarioAtivas(giaITCDTemporarioVo));
			//prepareStatementFindGIAITCDTemporarioAtivas(ps, giaITCDTemporarioVo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				GIAITCDTemporarioVo giaITCDTemporarioAtualVo = new GIAITCDTemporarioVo(rs.getLong(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO));
				//getGIAITCDTemporario(rs, giaITCDTemporarioAtualVo);
				giaITCDTemporarioVo.getCollVO().add(giaITCDTemporarioAtualVo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_TEMPORARIO);
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
		return giaITCDTemporarioVo;
	}   
   

	/**
	 * Método para montar o Prepare Statement de acordo com os dados válidos da GIA-ITCD Temporária
	 * @param ps
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementFindGIAITCDTemporarioAtivas(final PreparedStatement ps, final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDTemporarioVo);
		int contador = 0;
		if (giaITCDTemporarioVo.isConsultaParametrizada())
		{
			// CODIGO
			if (Validador.isNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigo()))
			{
				ps.setLong(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigo());
			}
			// DATA CRIACAO
			if (Validador.isDataValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getDataGIAITCDTemporario()))
			{
				ps.setDate(++contador, new java.sql.Date(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getDataGIAITCDTemporario().getTime()));
			}
			// SENHA
			if (Validador.isStringValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getSenhaGIAITCD()))
			{
				ps.setString(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getSenhaGIAITCD());
			}
			// CODIGO AUTENTICIDADE
			if (Validador.isStringValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigoAutenticidade()))
			{
				ps.setString(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigoAutenticidade());
			}
			// DATA PRAZO PROTOCOLAR
			if (Validador.isDataValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getPrazoProtocolar()))
			{
				ps.setDate(++contador, new java.sql.Date(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getPrazoProtocolar().getTime()));
			}
			// CODIGO RESPONSAVEL
			if (Validador.isNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigoResponsavel()))
			{
				ps.setLong(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigoResponsavel());
			}
			// GIA CONFIRMADA
			if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getGiaConfirmada()))
			{
				ps.setInt(++contador, ((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getGiaConfirmada().getValorCorrente());
			}
		}
	}
	
	private String obterSQLCamposRetornoBase(String alias)
	{
	   StringBuffer sql = new StringBuffer();
	   sql.append(" ").append(alias).append(".").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO);
//	   sql.append(" ,").append(alias).append(".").append(CAMPO_DATA_CRIACAO);
//	   sql.append(" ,").append(alias).append(".").append(CAMPO_INFO_GIA_TEMPORARIO);
//	   sql.append(" ,").append(alias).append(".").append(CAMPO_STATUS_ITCD);
//	   sql.append(" ,").append(alias).append(".").append(CAMPO_INFO_SENHA);
//	   sql.append(" ,").append(alias).append(".").append(CAMPO_CODIGO_AUTENTICIDADE);
//	   sql.append(" ,").append(alias).append(".").append(CAMPO_DATA_PRAZO_PROTOCOLAR);
//	   sql.append(" ,").append(alias).append(".").append(CAMPO_CODIGO_RESPONSAVEL);
//	   sql.append(" ,").append(alias).append(".").append(CAMPO_GIA_CONFIRMADA);
//	   sql.append(" ,").append(alias).append(".").append(CAMPO_DATA_ATUALIZACAO_BD);
		return sql.toString();
	}

	/**
	 * Método que monta a SQL de consulta de GIA-ITCD Temporárias Ativas
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ClassCastException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String getSQLFindGIAITCDTemporarioAtivas(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  ClassCastException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		StringBuffer sql = new StringBuffer();		
		sql.append(" SELECT ");
	   sql.append(obterSQLCamposRetornoBase("GIA"));
		sql.append(" FROM ").append(TABELA_GIA_ITCD_TEMPORARIO).append(" GIA ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND GIA.").append(CAMPO_STATUS_ITCD).append(" IN ");
			sql.append("(");
				sql.append(DomnStatusGIAITCD.EM_ELABORACAO).append(",").append(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO);
			sql.append(") ");
		sql.append(" AND GIA.").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO).append(" NOT IN ");
			sql.append("(");
				sql.append(" SELECT DISTINCT STATUS.").append(CamposStatusGIAITCD.CAMPO_CODIGO_GIA_TEMPORARIA);
				sql.append(" FROM ").append(TabelasITC.TABELA_STATUS_GIA_ITCD).append(" STATUS ");
				sql.append(" WHERE STATUS.").append(CamposStatusGIAITCD.CAMPO_CODIGO_GIA_TEMPORARIA).append(" IS NOT NULL ");
			sql.append(")");
		sql.append(" UNION ");
	   sql.append(" SELECT ");
	   sql.append(obterSQLCamposRetornoBase("GIA"));
	   sql.append(" FROM ").append(TABELA_STATUS_GIA_ITCD).append(" STATUS ");
		sql.append(" INNER JOIN ").append(TABELA_GIA_ITCD_TEMPORARIO).append(" GIA ON GIA.").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO).append(" = STATUS.").append(CamposStatusGIAITCD.CAMPO_CODIGO_GIA_TEMPORARIA);
		sql.append(" WHERE 1=1 ");
		sql.append(" AND STATUS.").append(CamposStatusGIAITCD.CAMPO_CODIGO_STATUS_ITCD).append(" IN ");
			sql.append("(");
				sql.append(" SELECT MAX(STAT.").append(CamposStatusGIAITCD.CAMPO_CODIGO_STATUS_ITCD).append(")");
				sql.append(" FROM ").append(TABELA_STATUS_GIA_ITCD).append(" STAT ");
				sql.append(" WHERE STAT.").append(CamposStatusGIAITCD.CAMPO_CODIGO_GIA_TEMPORARIA).append(" = GIA.").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO);
				sql.append(" OR STAT.").append(CamposStatusGIAITCD.CAMPO_ITCTB14_CODIGO_ITCD).append(" = GIA.").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO);
			sql.append(")");
	   sql.append(" AND STATUS.").append(CamposStatusGIAITCD.CAMPO_STAT_ITCD).append(" IN ");
	      sql.append("(");
	         sql.append(DomnStatusGIAITCD.EM_ELABORACAO).append(",").append(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO);
	      sql.append(") ");
	   sql.append(" AND GIA.").append(CAMPO_STATUS_ITCD).append(" IN ");
	      sql.append("(");
				sql.append(DomnStatusGIAITCD.EM_ELABORACAO).append(",").append(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO);
	      sql.append(") ");
		sql.append(" ORDER BY ").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO);
      sql.append(" DESC");
      System.out.println("SQL : GIA ATIVA : "+sql.toString());
		return sql.toString();
	}
   
   
   /**
    * <b>Objetivo:</b> Tem por objetivo listar gias temporarias, porém sem os campos
    * BLOB e XML para evitar java.lang.OutOfMemoryError
    * 
    * 
    * @param giaITCDTemporarioVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    */
   public GIAITCDTemporarioVo listDadosBasicosGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(giaITCDTemporarioVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindDadosBasicosGIAITCDTemporario(giaITCDTemporarioVo));
         
         prepareStatementFindGIAITCDTemporario(ps, giaITCDTemporarioVo);
         rs = ps.executeQuery();
         while (rs.next())
         {
            GIAITCDTemporarioVo giaITCDTemporarioAtualVo = new GIAITCDTemporarioVo();
            getDadosBasicosGIAITCDTemporario(rs, giaITCDTemporarioAtualVo);
            giaITCDTemporarioVo.getCollVO().add(giaITCDTemporarioAtualVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_TEMPORARIO);
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
      return giaITCDTemporarioVo;
   }
   
   
   private String getSQLFindDadosBasicosGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
           ClassCastException
   {
      Validador.validaObjeto(giaITCDTemporarioVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT GIA.").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO);
      sql.append(" , GIA.").append(CAMPO_DATA_CRIACAO);
      //sql.append(" , GIA.").append(CAMPO_INFO_GIA_TEMPORARIO);
      sql.append(" , GIA.").append(CAMPO_STATUS_ITCD);
      sql.append(" , GIA.").append(CAMPO_INFO_SENHA);
      sql.append(" , GIA.").append(CAMPO_CODIGO_AUTENTICIDADE);
      sql.append(" , GIA.").append(CAMPO_DATA_PRAZO_PROTOCOLAR);
      sql.append(" , GIA.").append(CAMPO_CODIGO_RESPONSAVEL);
      sql.append(" , GIA.").append(CAMPO_GIA_CONFIRMADA);
      sql.append(" , GIA.").append(CAMPO_DATA_ATUALIZACAO_BD);
      //sql.append(" , GIA.").append(CAMPO_INFO_GIA_TEMPORARIO_XML);
      sql.append(" , GIA.").append(CAMPO_NUMERO_VERSAO_GIAITCD);
      sql.append(" , GIA.").append(CAMPO_TIPO_PROTOCOLO_GIA);
      sql.append(" , GIA.").append(CAMPO_SITUACAO_PROCESSAMENTO);
      sql.append(" , GIA.").append(CAMPO_DESCRICAO_MENSAGEM_SITUACAO_ERRO);
      sql.append(" FROM ").append(TABELA_GIA_ITCD_TEMPORARIO).append(" GIA ");
      sql.append(" WHERE 1 = 1 "); 
      
      if (giaITCDTemporarioVo.isConsultaParametrizada())
      {
         if (Validador.isNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigo()))
         {
            sql.append("   AND GIA.").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO).append(" = ? ");
         }
         if (Validador.isDataValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getDataGIAITCDTemporario()))
         {
            sql.append("   AND GIA.").append(CAMPO_DATA_CRIACAO).append(" = ? ");
         }
         if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getStatusITCD().getStatusGIAITCD()))
         {
            sql.append("   AND GIA.").append(CAMPO_STATUS_ITCD).append(" = ? ");
         }
         if (Validador.isStringValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getSenhaGIAITCD()))
         {
            sql.append("   AND GIA.").append(CAMPO_INFO_SENHA).append(" = ? ");
         }
         if (Validador.isStringValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigoAutenticidade()))
         {
            sql.append("   AND GIA.").append(CAMPO_CODIGO_AUTENTICIDADE).append(" = ? ");
         }
         if (Validador.isDataValida(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getPrazoProtocolar()))
         {
            sql.append("   AND GIA.").append(CAMPO_DATA_PRAZO_PROTOCOLAR).append(" = ? ");
         }
         if (Validador.isNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getCodigoResponsavel()))
         {
            sql.append("   AND GIA.").append(CAMPO_CODIGO_RESPONSAVEL).append(" = ? ");
         }
         if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getGiaConfirmada()))
         {
            sql.append(" AND GIA.").append(CAMPO_GIA_CONFIRMADA).append(" = ? ");
         }
         if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getNumeroVersaoGIAITCD()))
         {
            sql.append(" AND GIA.").append(CAMPO_NUMERO_VERSAO_GIAITCD).append(" >= ? ");
         }
         if (Validador.isDominioNumericoValido(((GIAITCDTemporarioVo) giaITCDTemporarioVo.getParametroConsulta()).getTipoProtocoloGIA()))
         {
            sql.append(" AND GIA.").append(CAMPO_TIPO_PROTOCOLO_GIA).append(" = ? ");
         }
      }
      sql.append(" ORDER BY GIA.").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO);
      return sql.toString();
   }
   
   private void getDadosBasicosGIAITCDTemporario(final ResultSet rs, final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
           SQLException
   {
      Validador.validaObjeto(rs);
      Validador.validaObjeto(giaITCDTemporarioVo);
      giaITCDTemporarioVo.setCodigo(rs.getLong(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO));
      giaITCDTemporarioVo.setDataGIAITCDTemporario(rs.getDate(CAMPO_DATA_CRIACAO));
      /*
      try
      { 
         rs.getBlob(CAMPO_INFO_GIA_TEMPORARIO);
         if(rs.wasNull())
         {
            ExibirLOG.exibirLog("GIA não possui BLOB no BD",giaITCDTemporarioVo.getCodigo() );
         }else
         { 
            giaITCDTemporarioVo.setInputStream(rs.getBlob(CAMPO_INFO_GIA_TEMPORARIO).getBinaryStream());    
         } 
      }catch( Exception e)
      {
         e.printStackTrace();
      }
      */
      StatusGIAITCDVo status = new StatusGIAITCDVo();
      status.setStatusGIAITCD(new DomnStatusGIAITCD(rs.getInt(CAMPO_STATUS_ITCD)));
      giaITCDTemporarioVo.setStatusITCD(status);
      giaITCDTemporarioVo.setSenhaGIAITCD(rs.getString(CAMPO_INFO_SENHA));
      giaITCDTemporarioVo.setCodigoAutenticidade(rs.getString(CAMPO_CODIGO_AUTENTICIDADE));
      giaITCDTemporarioVo.setPrazoProtocolar(rs.getDate(CAMPO_DATA_PRAZO_PROTOCOLAR));
      giaITCDTemporarioVo.setCodigoResponsavel(rs.getLong(CAMPO_CODIGO_RESPONSAVEL));
      giaITCDTemporarioVo.setGiaConfirmada(new DomnSimNao(rs.getInt(CAMPO_GIA_CONFIRMADA)));
      giaITCDTemporarioVo.setDataAtualizacao(rs.getTimestamp(CAMPO_DATA_ATUALIZACAO_BD));
      //giaITCDTemporarioVo.setGiaTempXML(  rs.getString(CAMPO_INFO_GIA_TEMPORARIO_XML));
      giaITCDTemporarioVo.setNumeroVersaoGIAITCD(new DomnVersaoGIAITCD(rs.getInt(CAMPO_NUMERO_VERSAO_GIAITCD)));
      giaITCDTemporarioVo.setTipoProtocoloGIA(new DomnTipoProtocolo(rs.getInt(CAMPO_TIPO_PROTOCOLO_GIA)));
   }
   
   
   /**
    * 
    * @param giaITCDTemporarioVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ClassCastException
    */
   private String getSQLFindDadosBasicosGIAITCDTemporarioPorIntervaloDeTempoDataCriacao(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
           ClassCastException
   {
      Validador.validaObjeto(giaITCDTemporarioVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT GIA.").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO);
      sql.append(" , GIA.").append(CAMPO_DATA_CRIACAO);
      //sql.append(" , GIA.").append(CAMPO_INFO_GIA_TEMPORARIO);
      sql.append(" , GIA.").append(CAMPO_STATUS_ITCD);
      sql.append(" , GIA.").append(CAMPO_INFO_SENHA);
      sql.append(" , GIA.").append(CAMPO_CODIGO_AUTENTICIDADE);
      sql.append(" , GIA.").append(CAMPO_DATA_PRAZO_PROTOCOLAR);
      sql.append(" , GIA.").append(CAMPO_CODIGO_RESPONSAVEL);
      sql.append(" , GIA.").append(CAMPO_GIA_CONFIRMADA);
      sql.append(" , GIA.").append(CAMPO_DATA_ATUALIZACAO_BD);
      //sql.append(" , GIA.").append(CAMPO_INFO_GIA_TEMPORARIO_XML);
      sql.append(" , GIA.").append(CAMPO_NUMERO_VERSAO_GIAITCD);
      sql.append(" , GIA.").append(CAMPO_TIPO_PROTOCOLO_GIA);
      sql.append(" , GIA.").append(CAMPO_SITUACAO_PROCESSAMENTO);
      sql.append(" , GIA.").append(CAMPO_DESCRICAO_MENSAGEM_SITUACAO_ERRO);
      sql.append(" FROM ").append(TABELA_GIA_ITCD_TEMPORARIO).append(" GIA ");
      sql.append(" WHERE TRUNC(DATA_CRIACAO) BETWEEN TO_DATE('01/01/2015', 'dd/mm/yyyy') AND TO_DATE('31/12/2015', 'dd/mm/yyyy')  ");
      sql.append(" ORDER BY GIA.").append(CAMPO_CODIGO_GIA_ITCD_TEMPORARIO);
      System.out.println(sql.toString());
      return sql.toString();
   
   }
   
   
   /**
    * <b>Objetivo:</b> Tem por objetivo listar gias temporarias, porém sem os campos
    * BLOB e XML para evitar java.lang.OutOfMemoryError
    * 
    * 
    * @param giaITCDTemporarioVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    */
   public GIAITCDTemporarioVo listarDadosBasicosGIAITCDTemporarioPorIntervaloDeTempoDataCriacao(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(giaITCDTemporarioVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindDadosBasicosGIAITCDTemporarioPorIntervaloDeTempoDataCriacao(giaITCDTemporarioVo));
         //prepareStatementFindGIAITCDTemporario(ps, giaITCDTemporarioVo);
         rs = ps.executeQuery();
         while (rs.next())
         {
            GIAITCDTemporarioVo giaITCDTemporarioAtualVo = new GIAITCDTemporarioVo();
            getDadosBasicosGIAITCDTemporario(rs, giaITCDTemporarioAtualVo);
            giaITCDTemporarioVo.getCollVO().add(giaITCDTemporarioAtualVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_TEMPORARIO);
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
      return giaITCDTemporarioVo;
   }
   
   
}
