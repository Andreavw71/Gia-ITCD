package br.gov.mt.sefaz.itc.model.log.itemhistorico;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposItemHistoricoLog;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ItemHistoricoQDao extends AbstractDao implements CamposItemHistoricoLog, 
                                                              TabelasITC, 
                                                              SequencesITC
{
   public ItemHistoricoQDao(Connection conexao)
   {
      super(conexao);
   }

   
   public ItemHistoricoVo findItemHistoricoVo(ItemHistoricoVo item) throws ObjetoObrigatorioException, 
                                                                           ConsultaException
   {
      Validador.validaObjeto(item);
      PreparedStatement ps = null;
      ResultSet rs = null;

      try
      {
         ps = conn.prepareStatement(getSQLFindItemHistorico(item));
         prepareStatementFindItemHistoricoVo(ps,item);
         rs = ps.executeQuery();
         while (rs.next())
         {
            ItemHistoricoVo temp = new ItemHistoricoVo();
            getItemHistoricoVo(rs,temp );
            item.getCollVO().add(temp);
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

      return item;
   }

   
   private void prepareStatementFindItemHistoricoVo(PreparedStatement ps, final ItemHistoricoVo item ) throws ObjetoObrigatorioException, 
                                                                                                             SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(item);
      int contador = 0;
      if(item.isConsultaParametrizada())
      {
         // CODIGO
         if(Validador.isNumericoValido(item.getParametroConsulta().getCodigo()))
         {
            ps.setLong(++contador , (item.getParametroConsulta()).getCodigo() );
         }
         // CODIGO
         if(Validador.isNumericoValido( (item.getParametroConsulta()).getHistoricoLogVo().getCodigo()) )
         {
            ps.setLong(++contador ,  (item.getParametroConsulta()).getHistoricoLogVo().getCodigo());
         }
         // NOME CAMPO
         if(Validador.isStringValida( (item.getParametroConsulta()).getNomeCampo()) )
         {
            ps.setString(++contador , (item.getParametroConsulta()).getNomeCampo());
         }
         // VALOR ANTERIOR
         if(Validador.isStringValida( (item.getParametroConsulta()).getValorAnterior()) )
         {
            ps.setString(++contador , (item.getParametroConsulta()).getValorAnterior());
         }
         // VALOR ATUAL
         if(Validador.isStringValida( (item.getParametroConsulta()).getValorAtual() ) )
         {
            ps.setString(++contador ,  (item.getParametroConsulta()).getValorAtual()  );
         }
      }
   }

   private ItemHistoricoVo getItemHistoricoVo(final ResultSet rs, ItemHistoricoVo item) throws ObjetoObrigatorioException, 
                                                                                               SQLException
   {
      Validador.validaObjeto(rs);
      Validador.validaObjeto(item);
      //Recuperar Valor do Request.
      item.setCodigo(rs.getLong(CAMPO_NUMR_ITEM_SEQC));
      item.setHistoricoLogVo(new HistoricoLogVo(rs.getLong(CAMPO_NUMR_HITORICO_SEQC)));
      item.setNomeCampo(rs.getString(CAMPO_NOME_CAMPO));
      item.setValorAnterior(rs.getString(CAMPO_VALOR_ANTERIOR));
      item.setValorAtual(rs.getString(CAMPO_VALOR_ATUAL));
      return item;
   }

   private String getSQLFindItemHistorico(final ItemHistoricoVo item) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(item);
      StringBuffer sql = new StringBuffer();
      sql.append(getSQLBase());
      sql.append(" WHERE 1 = 1 ");

      if (item.isConsultaParametrizada())
      {
         // CODIGO
         if (Validador.isNumericoValido(item.getParametroConsulta().getCodigo()))
         {
            sql.append("AND ITEM_LOG.").append(CAMPO_NUMR_ITEM_SEQC).append(" = ? ");
         }
         // CODIGO HISTORICO
         if (Validador.isNumericoValido(item.getParametroConsulta().getHistoricoLogVo().getCodigo()))
         {
            sql.append("AND ITEM_LOG.").append(CAMPO_NUMR_HITORICO_SEQC).append(" = ? ");
         }
         //NOME CAMPO
         if (Validador.isStringValida(item.getParametroConsulta().getNomeCampo()))
         {
            sql.append("AND ITEM_LOG.").append(CAMPO_NOME_CAMPO).append(" = ? ");
         }
         //VALOR ANTERIOR
         if (Validador.isStringValida(item.getParametroConsulta().getValorAnterior()))
         {
            sql.append("AND ITEM_LOG.").append(CAMPO_VALOR_ANTERIOR).append(" = ? ");
         }
         //VALOR ATUAL
         if (Validador.isStringValida(item.getParametroConsulta().getValorAtual()))
         {
            sql.append("AND ITEM_LOG.").append(CAMPO_VALOR_ATUAL).append(" = ? ");
         }
      }
      sql.append(" ORDER BY ITEM_LOG.").append(CAMPO_NUMR_ITEM_SEQC).append(" ASC ");
      return sql.toString();
   }

   private String getSQLBase()
   {
      StringBuffer sql = new StringBuffer();
      sql.append("SELECT ITEM_LOG.").append(CAMPO_NUMR_ITEM_SEQC);
      sql.append(", ITEM_LOG.").append(CAMPO_NUMR_HITORICO_SEQC);
      sql.append(", ITEM_LOG.").append(CAMPO_NOME_CAMPO);
      sql.append(", ITEM_LOG.").append(CAMPO_VALOR_ANTERIOR);
      sql.append(", ITEM_LOG.").append(CAMPO_VALOR_ATUAL);
      sql.append(" FROM ").append(TABELA_GIA_ITCD_ITEM_HISTORICO_LOG).append(" ITEM_LOG");
      return sql.toString();
   }

}// fim classe(ItemHistoricoQDao).
