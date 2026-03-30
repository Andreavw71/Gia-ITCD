package br.gov.mt.sefaz.itc.model.tabelabasica.distancia;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDistancia;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposDistancia;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Dherkyan Ribeiro
 */
public class DistanciaQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposDistancia
{
   public DistanciaQDao(Connection conexao)
   {
      super(conexao);
   }

   public DistanciaVo findDistancia(DistanciaVo distanciaVo) throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.isObjetoValido(distanciaVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindDistancia(distanciaVo));
         prepareStatementFindDistancia(ps, distanciaVo);
         rs = ps.executeQuery();
         if (rs.next())
         {
            getDistancia(rs, distanciaVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_DISTANCIA);
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
      return distanciaVo;

   }

   public DistanciaVo listarDistanciaAtiva(DistanciaVo distanciaVo) throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.isObjetoValido(distanciaVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLListarDistancia(distanciaVo));
         prepareStatementListarDistancia(ps, distanciaVo);
         Collection distancias = new ArrayList();
         for (rs = ps.executeQuery(); rs.next(); )
         {
            DistanciaVo d = new DistanciaVo();
            getDistancia(rs, d);
            distancias.add(d);
         }
         if (Validador.isCollectionValida(distancias))
         {
            distanciaVo.setCollVO(distancias);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.LISTAR_DISTANCIA);
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
      return distanciaVo;

   }

   private void getDistancia(final ResultSet rs, final DistanciaVo distanciaVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.isObjetoValido(rs);
      Validador.isObjetoValido(distanciaVo);
      distanciaVo.setCodigo(rs.getLong(CAMPO_CODIGO_DISTANCIA));
      distanciaVo.setDistanciaInicialPerimetroUrbano(rs.getInt(CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO));
      distanciaVo.setDistanciaFinalPerimetroUrbano(rs.getInt(CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO));
      distanciaVo.setDistanciaInicialRodoviaPavimentada(rs.getInt(CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA));
      distanciaVo.setDistanciaFinalRodoviaPavimentada(rs.getInt(CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA));
      distanciaVo.setTipoDistancia(new DomnTipoDistancia(rs.getInt(CAMPO_TIPO_DISTANCIA)));
      distanciaVo.setStatusResgistroDistancia(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_REGISTRO_DISTANCIA)));
      distanciaVo.setDataAtualizacaoBD(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
   }

   private String getSQLFindDistancia(final DistanciaVo distanciaVo)
   {
      Validador.isObjetoValido(distanciaVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT ");
      sql.append(" DISTANCIA." + CAMPO_CODIGO_DISTANCIA);
      sql.append(" , DISTANCIA." + CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO);
      sql.append(" , DISTANCIA." + CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO);
      sql.append(" , DISTANCIA." + CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA);
      sql.append(" , DISTANCIA." + CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA);
      sql.append(" , DISTANCIA." + CAMPO_TIPO_DISTANCIA);
      sql.append(" , DISTANCIA." + CAMPO_STATUS_REGISTRO_DISTANCIA);
      sql.append(" , DISTANCIA." + CAMPO_DATA_ATUALIZACAO_BD);
      sql.append(" FROM " + TABELA_DISTANCIA + " DISTANCIA ");
      sql.append(" WHERE 1 = 1 ");

      if (distanciaVo.isConsultaParametrizada())
      {
         // CAMPO_CODIGO_DISTANCIA
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getCodigo()))
         {
            sql.append(" AND DISTANCIA." + CAMPO_CODIGO_DISTANCIA + " = ?");
         }
         // CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaInicialPerimetroUrbano()))
         {
            sql.append(" AND DISTANCIA." + CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO + " = ?");
         }
         // CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaFinalPerimetroUrbano()))
         {
            sql.append(" AND DISTANCIA." + CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO + " = ?");
         }
         // CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaInicialRodoviaPavimentada()))
         {
            sql.append(" AND DISTANCIA." + CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA + " = ?");
         }
         // CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaFinalRodoviaPavimentada()))
         {
            sql.append(" AND DISTANCIA." + CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA + " = ?");
         }
         // CAMPO_TIPO_DISTANCIA
         if (Validador.isDominioNumericoValido(distanciaVo.getParametroConsulta().getTipoDistancia()))
         {
            sql.append(" AND DISTANCIA." + CAMPO_TIPO_DISTANCIA + " = ?");
         }
         // CAMPO_STATUS_REGISTRO_DISTANCIA
         if (Validador.isDominioNumericoValido(distanciaVo.getParametroConsulta().getStatusResgistroDistancia()))
         {
            sql.append(" AND DISTANCIA." + CAMPO_STATUS_REGISTRO_DISTANCIA + " = ?");
         }
         //System.out.println(sql.toString());
      }

      sql.append(" ORDER BY DISTANCIA." + CAMPO_CODIGO_DISTANCIA + " ");
      return sql.toString();
   }

   private void prepareStatementFindDistancia(PreparedStatement ps, DistanciaVo distanciaVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(distanciaVo);
      int contador = 0;
      if (distanciaVo.isConsultaParametrizada())
      {
         // CAMPO_CODIGO_DISTANCIA
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getCodigo()))
         {
            ps.setLong(++contador, distanciaVo.getParametroConsulta().getCodigo());
         }
         // CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaInicialPerimetroUrbano()))
         {
            ps.setInt(++contador, ((DistanciaVo) distanciaVo.getParametroConsulta()).getDistanciaInicialPerimetroUrbano());
         }
         // CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaFinalPerimetroUrbano()))
         {
            ps.setInt(++contador, distanciaVo.getParametroConsulta().getDistanciaFinalPerimetroUrbano());
         }
         // CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaInicialRodoviaPavimentada()))
         {
            ps.setInt(++contador, distanciaVo.getParametroConsulta().getDistanciaInicialRodoviaPavimentada());
         }
         // CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaInicialRodoviaPavimentada()))
         {
            ps.setInt(++contador, distanciaVo.getParametroConsulta().getDistanciaFinalRodoviaPavimentada());
         }
         // CAMPO_TIPO_DISTANCIA
         if (Validador.isDominioNumericoValido(distanciaVo.getParametroConsulta().getTipoDistancia()))
         {
            ps.setInt(++contador, distanciaVo.getParametroConsulta().getTipoDistancia().getValorCorrente());
         }
         // CAMPO_STATUS_REGISTRO_DISTANCIA
         if (Validador.isDominioNumericoValido(distanciaVo.getParametroConsulta().getStatusResgistroDistancia()))
         {
            ps.setInt(++contador, distanciaVo.getParametroConsulta().getStatusResgistroDistancia().getValorCorrente());
         }
      }
   }

   private String getSQLListarDistancia(final DistanciaVo distanciaVo)
   {
      Validador.isObjetoValido(distanciaVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT ");
      sql.append(" DISTANCIA." + CAMPO_CODIGO_DISTANCIA);
      sql.append(" , DISTANCIA." + CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO);
      sql.append(" , DISTANCIA." + CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO);
      sql.append(" , DISTANCIA." + CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA);
      sql.append(" , DISTANCIA." + CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA);
      sql.append(" , DISTANCIA." + CAMPO_TIPO_DISTANCIA);
      sql.append(" , DISTANCIA." + CAMPO_STATUS_REGISTRO_DISTANCIA);
      sql.append(" , DISTANCIA." + CAMPO_DATA_ATUALIZACAO_BD);
      sql.append(" FROM " + TABELA_DISTANCIA + " DISTANCIA ");
      sql.append(" WHERE 1 = 1 ");
      if (distanciaVo.isConsultaParametrizada())
      {
         //CAMPO_STATUS_REGISTRO_DISTANCIA
         if (Validador.isDominioNumericoValido(distanciaVo.getParametroConsulta().getStatusResgistroDistancia()))
         {
            //sql.append("  AND DISTANCIA." + CAMPO_STATUS_REGISTRO_DISTANCIA + " = ?");
         }
         //CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaInicialPerimetroUrbano()))
         {
            sql.append(" AND DISTANCIA." + CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO + " = ?");
         }
         //CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaFinalPerimetroUrbano()))
         {
            sql.append(" AND DISTANCIA." + CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO + " = ?");
         }
         // CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaInicialRodoviaPavimentada()))
         {
            sql.append(" AND DISTANCIA." + CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA + " = ?");
         }
         //CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaFinalRodoviaPavimentada()))
         {
            sql.append(" AND DISTANCIA." + CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA + " = ?");
         }
         //CAMPO_TIPO_DISTANCIA
         if (Validador.isDominioNumericoValido(distanciaVo.getParametroConsulta().getTipoDistancia()))
         {
            sql.append(" AND DISTANCIA." + CAMPO_TIPO_DISTANCIA + " = ?");
         }
      }
      sql.append("  AND DISTANCIA." + CAMPO_STATUS_REGISTRO_DISTANCIA + " = " + DomnStatusRegistro.ATIVO);
      sql.append("  ORDER BY DISTANCIA." + CAMPO_TIPO_DISTANCIA + " , DISTANCIA." + CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO + 
            " , DISTANCIA." + CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO + " , DISTANCIA." + 
            CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA + " , DISTANCIA." + CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA);
      //System.out.println("SQL" + sql.toString());
      return sql.toString();
   }

   private void prepareStatementListarDistancia(PreparedStatement ps, DistanciaVo distanciaVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(distanciaVo);
      int contador = 0;
      if (distanciaVo.isConsultaParametrizada())
      {
         //CAMPO_CODIGO_DISTANCIA
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getCodigo()))
         {
            ps.setLong(++contador, distanciaVo.getParametroConsulta().getCodigo());
         }
         // CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaInicialPerimetroUrbano()))
         {
            ps.setInt(++contador, ((DistanciaVo) distanciaVo.getParametroConsulta()).getDistanciaInicialPerimetroUrbano());
         }
         // CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaFinalPerimetroUrbano()))
         {
            ps.setInt(++contador, distanciaVo.getParametroConsulta().getDistanciaFinalPerimetroUrbano());
         }
         // CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaInicialRodoviaPavimentada()))
         {
            ps.setInt(++contador, distanciaVo.getParametroConsulta().getDistanciaInicialRodoviaPavimentada());
         }
         // CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA
         if (Validador.isNumericoValido(distanciaVo.getParametroConsulta().getDistanciaInicialRodoviaPavimentada()))
         {
            ps.setInt(++contador, distanciaVo.getParametroConsulta().getDistanciaFinalRodoviaPavimentada());
         }
         // CAMPO_TIPO_DISTANCIA
         if (Validador.isDominioNumericoValido(distanciaVo.getParametroConsulta().getTipoDistancia()))
         {
            ps.setInt(++contador, distanciaVo.getParametroConsulta().getTipoDistancia().getValorCorrente());
         }
         // CAMPO_STATUS_REGISTRO_DISTANCIA
         if (Validador.isDominioNumericoValido(distanciaVo.getParametroConsulta().getStatusResgistroDistancia()))
         {
            //ps.setInt(++contador, distanciaVo.getParametroConsulta().getStatusResgistroDistancia().getValorCorrente());
         }
      }
   }
   
   public DistanciaVo findDistanciaIntervaloPrmtUrbanoRodovia(DistanciaVo distanciaVo)
      throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.isObjetoValido(distanciaVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      
      try
      {
         ps = montaPreparedStatementParaConsultaDeIntervalos(distanciaVo.getParametroConsulta());
         rs = ps.executeQuery();
         if(rs.next())
         {
            getDistancia(rs, distanciaVo);
         }
         else 
         {
            throw new ConsultaException(MensagemErro.SELECIONAR_INTERVALO_DISTANCIA_INEXISTENTE);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.SELECIONAR_INTERVALO_DISTANCIA);
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
      return distanciaVo;
   }
   
   private PreparedStatement montaPreparedStatementParaConsultaDeIntervalos(DistanciaVo distanciaVo)
      throws SQLException
   {
      //O stat_distancia sempre será 1 pois pegará da base as distâncias que estão ativas
      String sql = "SELECT * " + 
      "  FROM ITC.itctb51_distancia t51" + 
      " WHERE (? BETWEEN t51.qtde_distancia_inic_prmt_urbn AND t51.qtde_distancia_final_prmt_urbn OR " + 
      "       (? >= t51.qtde_distancia_inic_prmt_urbn AND " + 
      "       t51.qtde_distancia_final_prmt_urbn IS NULL) OR " + 
      "       (t51.qtde_distancia_inic_prmt_urbn IS NULL AND " + 
      "       ? <= t51.qtde_distancia_final_prmt_urbn)) " + 
      "   AND (? BETWEEN t51.qtde_distancia_inic_rodv_pavm AND t51.qtde_distancia_final_rodv_pavm OR " + 
      "       (? >= t51.qtde_distancia_inic_rodv_pavm AND " + 
      "       t51.qtde_distancia_final_rodv_pavm IS NULL) OR " + 
      "       (? <= t51.qtde_distancia_final_rodv_pavm AND " + 
      "       t51.qtde_distancia_inic_rodv_pavm IS NULL)) " + 
      "   AND t51.stat_distancia = 1";
      
      PreparedStatement ps = conn.prepareStatement(sql);
      int contadorParametro = 1;
      
      //Seta distancias inicial e final para capturar o intervalor
      ps.setInt(contadorParametro++, distanciaVo.getDistanciaInicialPerimetroUrbano());
      ps.setInt(contadorParametro++, distanciaVo.getDistanciaInicialPerimetroUrbano());
      ps.setInt(contadorParametro++, distanciaVo.getDistanciaInicialPerimetroUrbano());
      ps.setInt(contadorParametro++, distanciaVo.getDistanciaInicialRodoviaPavimentada());
      ps.setInt(contadorParametro++, distanciaVo.getDistanciaInicialRodoviaPavimentada());
      ps.setInt(contadorParametro++, distanciaVo.getDistanciaInicialRodoviaPavimentada());
      
      return ps;
   }
   
}
