package br.gov.mt.sefaz.itc.model.log.historicolog;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.log.log.LogITCDVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposHistoricoLog;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class HistoricoLogQDao extends AbstractDao implements CamposHistoricoLog, 
                                                             TabelasITC, 
                                                             SequencesITC
{
   public HistoricoLogQDao(Connection conexao)
   {
      super(conexao);
   }


   // ----------------------- EStrutura SQL -------------------------------

   public HistoricoLogVo findHistoricoLogVo(final HistoricoLogVo historicoLogVo) throws ObjetoObrigatorioException, 
                                                                                        ConsultaException
   {
      Validador.validaObjeto(historicoLogVo);
      PreparedStatement ps = null;
      ResultSet rs = null;

      try
      {
         ps = conn.prepareStatement(getSQLFindHistoricoLog(historicoLogVo));
         prepareStatementFindHistoricoLogVo(ps, historicoLogVo);
         rs = ps.executeQuery();
         while (rs.next())
         {
            HistoricoLogVo temp = new HistoricoLogVo();
            getHistoricoLogVo(rs,temp );
            historicoLogVo.getCollVO().add(temp);
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_LOG_ITCD);
      } 
      finally
      {
         try
         {
            close(ps, rs);
            ps = null;
            rs = null;
         } catch (SQLException e)
         {
            e.printStackTrace();
         }
      }

      return historicoLogVo;
   }

   private String getSQLBase()
   {
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT HISTORICO_LOG.").append(CAMPO_NUM_HIST_SEQ);
      sql.append(" , HISTORICO_LOG.").append(CAMPO_ITCTB48_NUM_LOG_SEQ);
      sql.append(" , HISTORICO_LOG.").append(CAMPO_TIPO_OPERACAO);
      sql.append(" , HISTORICO_LOG.").append(CAMPO_NOME_TABELA);
      sql.append(" FROM ").append(TABELA_GIA_ITCD_HISTORICO_LOG).append(" HISTORICO_LOG ");
      return sql.toString();
   }

   private HistoricoLogVo getHistoricoLogVo(final ResultSet rs, HistoricoLogVo historicoLogVo) throws SQLException, 
                                                                                                      ObjetoObrigatorioException
   {
      Validador.validaObjeto(rs);
      Validador.validaObjeto(historicoLogVo);
      // recuperando valor do request.
      historicoLogVo.setCodigo(rs.getLong(CAMPO_NUM_HIST_SEQ));
      historicoLogVo.setLogITCDVo(new LogITCDVo(rs.getLong(CAMPO_ITCTB48_NUM_LOG_SEQ)));
      historicoLogVo.setDomnTipoOperacao(new DomnTipoOperacao(rs.getInt(CAMPO_TIPO_OPERACAO)));
      historicoLogVo.setNomeTabela(rs.getString(CAMPO_NOME_TABELA));

      return historicoLogVo;
   }

   private String getSQLFindHistoricoLog(final HistoricoLogVo historicoLogVo) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(historicoLogVo);
      StringBuffer sql = new StringBuffer();
      sql.append(getSQLBase());
      sql.append(" WHERE 1 = 1 ");

      if (historicoLogVo.isConsultaParametrizada())
      {
         // CODIGO
         if (Validador.isNumericoValido((historicoLogVo.getParametroConsulta()).getCodigo()))
         {
            sql.append(" AND HISTORICO_LOG.").append(CAMPO_NUM_HIST_SEQ).append(" = ? ");
         }
         // CODIGO
         if (Validador.isNumericoValido((historicoLogVo.getParametroConsulta()).getLogITCDVo().getCodigo()))
         {
            sql.append(" AND HISTORICO_LOG.").append(CAMPO_ITCTB48_NUM_LOG_SEQ).append(" = ? ");
         }
      }

      sql.append(" ORDER BY HISTORICO_LOG.").append(CAMPO_NUM_HIST_SEQ).append(" ASC ");
      return sql.toString();
   }

   private void prepareStatementFindHistoricoLogVo(final PreparedStatement ps, final HistoricoLogVo historicoLogVo) throws ObjetoObrigatorioException, 
                                                                                                                           SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(historicoLogVo);
      int contador = 0;
      if (historicoLogVo.isConsultaParametrizada())
      {
         // CODIGO Historico do LOG
         if (Validador.isNumericoValido((historicoLogVo.getParametroConsulta()).getCodigo()))
         {
            ps.setLong(++contador, (historicoLogVo.getParametroConsulta()).getCodigo());
         }
         // CODIGO  do LOG
         if (Validador.isNumericoValido((historicoLogVo.getParametroConsulta()).getLogITCDVo().getCodigo()))
         {
            ps.setLong(++contador, (historicoLogVo.getParametroConsulta()).getLogITCDVo().getCodigo());
         }
      }

   }


}
