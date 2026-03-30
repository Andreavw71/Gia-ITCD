package br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoEprocess;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;


public class TipoProcessoQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposTipoProcesso
{
   public TipoProcessoQDao(Connection conexao)
   {
      super(conexao);
   }
   
   private void getTipoProcesso(final ResultSet rs, final TipoProcessoVo tipoProcessoVo) throws ObjetoObrigatorioException, 
           SQLException
   {
      Validador.validaObjeto(rs);
      Validador.validaObjeto(tipoProcessoVo);
      tipoProcessoVo.setCodigo(rs.getLong(CAMPO_CODG_TIPO_PROCESSO));
      tipoProcessoVo.getTipoProcessoIntegracaoVo().setCodgTipoProcesso(rs.getLong(CAMPO_CODG_TIPO_PRCO_SEQC));
      tipoProcessoVo.setDescTipoProcesso(rs.getString(CAMPO_DESC_TIPO_PROCESSO));
      tipoProcessoVo.setDomnTipoEprocess(new DomnTipoProcessoEprocess(rs.getInt(CAMPO_DOMN_TIPO_PROCESO)));
   }
   
   private void prepareStatementFindTipoProcesso(PreparedStatement ps, TipoProcessoVo tipoProcessoVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(tipoProcessoVo);
      int contador = 0;
      
      if (tipoProcessoVo.isConsultaParametrizada())
      {
         // CODG_TIPO_PROCESSO
         if (Validador.isNumericoValido((tipoProcessoVo.getParametroConsulta()).getCodigo()))
         {
            ps.setLong(++contador, (tipoProcessoVo.getParametroConsulta()).getCodigo());
         }
         // PDGTB01_CODG_TIPO_PRCO_SEQC
         if (Validador.isNumericoValido((tipoProcessoVo.getParametroConsulta()).getTipoProcessoIntegracaoVo().getCodgTipoProcesso()))
         {
            ps.setLong(++contador, (tipoProcessoVo.getParametroConsulta()).getTipoProcessoIntegracaoVo().getCodgTipoProcesso());
         }
         // DESC_TIPO_PROCESSO
         if (Validador.isStringValida((tipoProcessoVo.getParametroConsulta()).getDescTipoProcesso()))
         {
            ps.setString(++contador, (tipoProcessoVo.getParametroConsulta()).getDescTipoProcesso());
         }
         // DOMN_TIPO_PROCESO
         if (Validador.isDominioNumericoValido((tipoProcessoVo.getParametroConsulta()).getDomnTipoEprocess()))
         {
            ps.setInt(++contador, (tipoProcessoVo.getParametroConsulta()).getDomnTipoEprocess().getValorCorrente());
         }
         
      }
   }
   
   public TipoProcessoVo findTipoProcesso(TipoProcessoVo tipoProcessoVo) throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.isObjetoValido(tipoProcessoVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindTipoProcesso(tipoProcessoVo));
         prepareStatementFindTipoProcesso(ps, tipoProcessoVo);
         rs = ps.executeQuery();
         if (rs.next())
         {
            getTipoProcesso(rs, tipoProcessoVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.TIPO_PROCESSO_EPROCESS_ERRO);
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
      return tipoProcessoVo;
   }
   
   private String getSQLFindTipoProcesso(final TipoProcessoVo tipoProcessoVo)
   {
      Validador.isObjetoValido(tipoProcessoVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT ");
      sql.append(" TIPO_PROCESSO." + CAMPO_CODG_TIPO_PROCESSO);
      sql.append(" , TIPO_PROCESSO." + CAMPO_CODG_TIPO_PRCO_SEQC);
      sql.append(" , TIPO_PROCESSO." + CAMPO_DESC_TIPO_PROCESSO);
      sql.append(" , TIPO_PROCESSO." + CAMPO_DOMN_TIPO_PROCESO);
      sql.append(" FROM " + TABELA_TIPO_PROCESSO + " TIPO_PROCESSO ");
      sql.append(" WHERE 1 = 1 ");

      if (tipoProcessoVo.isConsultaParametrizada())
      {
         if (Validador.isNumericoValido((tipoProcessoVo.getParametroConsulta()).getCodigo()))
         {
            sql.append(" AND TIPO_PROCESSO." + CAMPO_CODG_TIPO_PROCESSO + " = ?");
         }
         if (Validador.isNumericoValido((tipoProcessoVo.getParametroConsulta()).getTipoProcessoIntegracaoVo().getCodgTipoProcesso()))
         {
            sql.append(" AND TIPO_PROCESSO." + CAMPO_CODG_TIPO_PRCO_SEQC + " = ?");
         }
         if (Validador.isStringValida((tipoProcessoVo.getParametroConsulta()).getDescTipoProcesso()))
         {
            sql.append(" AND TIPO_PROCESSO." + CAMPO_DESC_TIPO_PROCESSO + " = ?");
         }
         if (Validador.isDominioNumericoValido((tipoProcessoVo.getParametroConsulta()).getDomnTipoEprocess()))
         {
            sql.append(" AND TIPO_PROCESSO." + CAMPO_DOMN_TIPO_PROCESO + " = ?");
         }
      }
      sql.append(" ORDER BY TIPO_PROCESSO." + CAMPO_CODG_TIPO_PROCESSO + " ");
      //ExibirLOG.exibirLOGDeErroNoConsole( sql.toString() );
      return sql.toString();
   }

   
   public TipoProcessoVo listGIAITCDTipoProcesso(final TipoProcessoVo tipoProcessoVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(tipoProcessoVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindTipoProcesso(tipoProcessoVo));
         prepareStatementFindTipoProcesso(ps, tipoProcessoVo);
         rs = ps.executeQuery();
         tipoProcessoVo.setCollVO(new ArrayList());
         while (rs.next())
         {
            TipoProcessoVo tipoProcessoVoAtual = new TipoProcessoVo();
            getTipoProcesso(rs, tipoProcessoVoAtual);
            tipoProcessoVo.getCollVO().add(tipoProcessoVoAtual);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.LISTAR_GIA_ITCD_SEPARACAO_DIVORCIO);
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
      return tipoProcessoVo;
   }
   
   //TODO: TERMINAR UPDATE/DELETE DA CLASSE TIPO PROCESSO
    /*public void updateBem(final TipoProcessoVo tipoProcessoVo) throws ObjetoObrigatorioException, AlteraException
    {
       Validador.validaObjeto(tipoProcessoVo);
       PreparedStatement ps = null;
       String sql = utilStmt.geraUpdt(getCamposBem(), new String[] { CAMPO_CODIGO_BEM });
       try
       {
          GeradorLogSefazMT.gerar(bemVo, DomnOperacao.OPERACAO_UPDATE, bemVo.getNumeroParticao(), 
          bemVo.getCodigoTransacao(), bemVo.getLogSefazVo().getUsuario().getCodigo(), conn);
          ps = conn.prepareStatement(sql);
          preparedStatementUpdateBem(ps, bemVo);
          if (ps.executeUpdate() != 1)
          {
             throw new SQLException();
          }
       }
       catch (SQLException e)
       {
          e.printStackTrace();
          throw new AlteraException(MensagemErro.ALTERAR_BEM);
       }
       catch (LogSefazException e)
       {
          e.printStackTrace(); 
          throw new AlteraException(MensagemErro.ALTERAR_BEM);
       }
       catch (Exception e)
       {
          e.printStackTrace();
          throw new AlteraException(MensagemErro.ALTERAR_BEM);
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
    }*/
}