package br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnCriterioBaseCalculo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAtividade;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDistancia;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBaseCalculoImovelRural;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


public class BaseCalculoImovelRuralQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposBaseCalculoImovelRural
{

   /* ---------------------------------------------------------------------------------------------
   *                                Construtores
   * --------------------------------------------------------------------------------------------- */
   public BaseCalculoImovelRuralQDao(Connection conexao)
   {
      super(conexao);
   }

   public BaseCalculoImovelRuralVo findBaseCalculoImovelRuralVo(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws ObjetoObrigatorioException, 
         ConsultaException
   {

      Validador.isObjetoValido(baseCalculoImovelRuralVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindBaseCalculoImovelRuralVo(baseCalculoImovelRuralVo));
         prepareStatementFindBaseCalculoImovelRuralVo(ps, baseCalculoImovelRuralVo);
         rs = ps.executeQuery();
         if (rs.next())
         {
            getBaseCalculoImovelRuralVo(rs, baseCalculoImovelRuralVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_BASE_CALCULO_IMOVEL_RURAL);
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
      return baseCalculoImovelRuralVo;
   }

   private void getBaseCalculoImovelRuralVo(ResultSet rs, BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws SQLException
   {
      Validador.isObjetoValido(rs);
      Validador.isObjetoValido(baseCalculoImovelRuralVo);
      baseCalculoImovelRuralVo.setCodigo(rs.getLong(CAMPO_CODIGO));
      baseCalculoImovelRuralVo.setTipoDistancia(new DomnTipoDistancia(rs.getInt(CAMPO_TIPO_DISTANCIA)));
      baseCalculoImovelRuralVo.setTipoAtividade(new DomnTipoAtividade(rs.getInt(CAMPO_TIPO_ATIVIDADE)));
      baseCalculoImovelRuralVo.setQuantidadeDistanciaInicial(rs.getInt(CAMPO_QUANTIDADE_DISTANCIA_INICIAL));
      baseCalculoImovelRuralVo.setQuantidadeDistanciaFinal(rs.getInt(CAMPO_QUANTIDADE_DISTANCIA_FINAL));
      baseCalculoImovelRuralVo.setPercentualAtividadeInicial(rs.getInt(CAMPO_PERCENTUAL_ATIVIDADE_INICIAL));
      baseCalculoImovelRuralVo.setPercentualAtividadeFinal(rs.getInt(CAMPO_PERCENTUAL_ATIVIDADE_FINAL));
      baseCalculoImovelRuralVo.setPercentualAreaExploradaInical(rs.getInt(CAMPO_PERCENTUAL_AREA_EXPLORADA_INICIAL));
      baseCalculoImovelRuralVo.setPercentualAreaExploradaFinal(rs.getInt(CAMPO_PERCENTUAL_AREA_EXPLORADA_FINAL));
      baseCalculoImovelRuralVo.setNumeroItem(rs.getInt(CAMPO_NUMERO_ITEM));
      baseCalculoImovelRuralVo.setPercentualBaseCalculoMinimo(rs.getDouble(CAMPO_PERCENTUAL_BASE_CALCULO_MINIMO));
      baseCalculoImovelRuralVo.setCriterioBaseCalculo(new DomnCriterioBaseCalculo(rs.getInt(CAMPO_CRITERIO_BASE_CALCULO)));
      baseCalculoImovelRuralVo.setStatusResgistroBaseCalculoImovelRural(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_BASE_CALCULO)));
      baseCalculoImovelRuralVo.setDataAtualizacaoBD(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
   }

   private String getSQLFindBaseCalculoImovelRuralVo(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
   {
      Validador.isObjetoValido(baseCalculoImovelRuralVo);

      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT ");
      sql.append(" BASE_CALC_IMOVEL_RURAL." + CAMPO_CODIGO);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_TIPO_DISTANCIA);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_TIPO_ATIVIDADE);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_QUANTIDADE_DISTANCIA_INICIAL);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_QUANTIDADE_DISTANCIA_FINAL);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_ATIVIDADE_INICIAL);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_ATIVIDADE_FINAL);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_AREA_EXPLORADA_INICIAL);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_AREA_EXPLORADA_FINAL);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_NUMERO_ITEM);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_BASE_CALCULO_MINIMO);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_CRITERIO_BASE_CALCULO);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_STATUS_BASE_CALCULO);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_DATA_ATUALIZACAO_BD);
      sql.append(" FROM " + TABELA_BASE_CALCULO_IMOVEL_RURAL + " BASE_CALC_IMOVEL_RURAL ");
      sql.append(" WHERE 1 = 1 ");

      if (baseCalculoImovelRuralVo.isConsultaParametrizada())
      {
         if (Validador.isNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getCodigo()))
         {
            sql.append(" AND BASE_CALC_IMOVEL_RURAL." + CAMPO_CODIGO + " = ?");
         }
         if (Validador.isDominioNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getTipoDistancia()))
         {
            sql.append(" AND BASE_CALC_IMOVEL_RURAL." + CAMPO_TIPO_DISTANCIA + " = ?");
         }
         if (Validador.isDominioNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getTipoAtividade()))
         {
            sql.append(" AND BASE_CALC_IMOVEL_RURAL." + CAMPO_TIPO_ATIVIDADE + " = ?");
         }
         if (Validador.isNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getQuantidadeDistanciaInicial()))
         {
            //sql.append(" AND BASE_CALC_IMOVEL_RURAL." + CAMPO_QUANTIDADE_DISTANCIA_INICIAL + " = ?");
         }
         if (Validador.isNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getQuantidadeDistanciaFinal()))
         {
            //sql.append(" AND BASE_CALC_IMOVEL_RURAL." + CAMPO_QUANTIDADE_DISTANCIA_FINAL + " = ?");
         }
         if (Validador.isNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getPercentualAtividadeInicial()))
         {
            //sql.append(" AND BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_ATIVIDADE_INICIAL + " = ?");
         }
         if (Validador.isNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getPercentualAtividadeFinal()))
         {
            //sql.append(" AND BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_ATIVIDADE_FINAL + " = ?");
         }
         //CAMPO_PERCENTUAL_AREA_EXPLORADA_INICIAL
         if (Validador.isNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getPercentualAreaExploradaInical()))
         {
            //sql.append(" AND BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_AREA_EXPLORADA_INICIAL + " = ?");
         }
         //CAMPO_PERCENTUAL_AREA_EXPLORADA_FINAL
         if (Validador.isNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getPercentualAreaExploradaFinal()))
         {
            //sql.append(" AND BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_AREA_EXPLORADA_FINAL + " = ?");
         }
         //CAMPO_NUMERO_ITEM
         if (Validador.isNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getNumeroItem()))
         {
            sql.append(" AND BASE_CALC_IMOVEL_RURAL." + CAMPO_NUMERO_ITEM + " = ?");
         }
         //CAMPO_PERCENTUAL_BASE_CALCULO_MINIMO
         if (Validador.isNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getPercentualBaseCalculoMinimo()))
         {
            //sql.append(" AND BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_BASE_CALCULO_MINIMO + " = ?");
         }
         if (Validador.isDominioNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getCriterioBaseCalculo()))
         {
            sql.append(" AND BASE_CALC_IMOVEL_RURAL." + CAMPO_CRITERIO_BASE_CALCULO + " = ?");
         }
         if (Validador.isDominioNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getStatusResgistroBaseCalculoImovelRural()))
         {
            sql.append(" AND BASE_CALC_IMOVEL_RURAL." + CAMPO_STATUS_BASE_CALCULO + " = ?");
         }
      }

      sql.append(" ORDER BY BASE_CALC_IMOVEL_RURAL." + CAMPO_CODIGO + " ");
      //System.out.println("SQL : "+sql.toString());
      return sql.toString();
   }

   private void prepareStatementFindBaseCalculoImovelRuralVo(PreparedStatement ps, BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(baseCalculoImovelRuralVo);
      int contador = 0;
      if (baseCalculoImovelRuralVo.isConsultaParametrizada())
      {
         // CODIGO
         if (Validador.isNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getCodigo()))
         {
            ps.setLong(++contador, baseCalculoImovelRuralVo.getParametroConsulta().getCodigo());
         }
         //CAMPO_TIPO_DISTANCIA
         if (Validador.isDominioNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getTipoDistancia()))
         {
            ps.setLong(++contador, baseCalculoImovelRuralVo.getParametroConsulta().getTipoDistancia().getValorCorrente());
         }
         //CAMPO_TIPO_ATIVIDADE
         if (Validador.isDominioNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getTipoAtividade()))
         {
            ps.setLong(++contador, baseCalculoImovelRuralVo.getParametroConsulta().getTipoAtividade().getValorCorrente());
         }
         //CAMPO_NUMERO_ITEM
         if (Validador.isNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getNumeroItem()))
         {
            ps.setLong(++contador, baseCalculoImovelRuralVo.getParametroConsulta().getNumeroItem());
         }
         //CAMPO_CRITERIO_BASE_CALCULO
         if (Validador.isDominioNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getCriterioBaseCalculo()))
         {
            ps.setLong(++contador, baseCalculoImovelRuralVo.getParametroConsulta().getCriterioBaseCalculo().getValorCorrente());
         }
         //CAMPO_STATUS_BASE_CALCULO
         if (Validador.isDominioNumericoValido(baseCalculoImovelRuralVo.getParametroConsulta().getStatusResgistroBaseCalculoImovelRural()))
         {
            ps.setLong(++contador, baseCalculoImovelRuralVo.getParametroConsulta().getStatusResgistroBaseCalculoImovelRural().getValorCorrente());
         }
      }
   }

   public BaseCalculoImovelRuralVo listarBaseCalculoImovelRuralVo(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws ObjetoObrigatorioException, 
         ConsultaException
   {
      Validador.isObjetoValido(baseCalculoImovelRuralVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindBaseCalculoImovelRuralVo(baseCalculoImovelRuralVo));
         prepareStatementFindBaseCalculoImovelRuralVo(ps, baseCalculoImovelRuralVo);
         Collection municipios = new ArrayList();
         for (rs = ps.executeQuery(); rs.next(); )
         {
            BaseCalculoImovelRuralVo b = new BaseCalculoImovelRuralVo();
            getBaseCalculoImovelRuralVo(rs, b);
            municipios.add(b);
         }
         if (Validador.isCollectionValida(municipios))
         {
            baseCalculoImovelRuralVo.setCollVO(municipios);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.LISTAR_BASE_CALCULO_IMOVEL_RURAL);
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
      return baseCalculoImovelRuralVo;
   }

   // INUTIL remover pq pode ser utilizado o metodo getSQLFindBaseCalculoImovelRuralVo
   private String getSQLListarBaseCalculoImovelRuralVo(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
   {
      Validador.isObjetoValido(baseCalculoImovelRuralVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT ");
      sql.append(" BASE_CALC_IMOVEL_RURAL." + CAMPO_CODIGO);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_TIPO_DISTANCIA);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_TIPO_ATIVIDADE);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_QUANTIDADE_DISTANCIA_INICIAL);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_QUANTIDADE_DISTANCIA_FINAL);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_ATIVIDADE_INICIAL);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_ATIVIDADE_FINAL);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_AREA_EXPLORADA_INICIAL);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_AREA_EXPLORADA_FINAL);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_NUMERO_ITEM);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_PERCENTUAL_BASE_CALCULO_MINIMO);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_CRITERIO_BASE_CALCULO);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_STATUS_BASE_CALCULO);
      sql.append(", BASE_CALC_IMOVEL_RURAL." + CAMPO_DATA_ATUALIZACAO_BD);
      sql.append(" FROM " + TABELA_BASE_CALCULO_IMOVEL_RURAL + " BASE_CALC_IMOVEL_RURAL ");
      sql.append(" WHERE 1 = 1 ");
      if (baseCalculoImovelRuralVo.isConsultaParametrizada())
      {

      }
      sql.append("  AND BASE_CALC_IMOVEL_RURAL." + CAMPO_STATUS_BASE_CALCULO + " = " + DomnStatusRegistro.ATIVO);
      sql.append("  ORDER BY BASE_CALC_IMOVEL_RURAL." + CAMPO_CODIGO);
      System.out.println(sql.toString());
      return sql.toString();
   }
   
   public BaseCalculoImovelRuralVo findCriterioCalculoITCD(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
      throws ConsultaException
   {
      Validador.isObjetoValido(baseCalculoImovelRuralVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = montaPreparedStatementConsultaIntervalos(baseCalculoImovelRuralVo.getParametroConsulta());
         rs = ps.executeQuery();
         if (rs.next())
         {
            getBaseCalculoImovelRuralVo(rs, baseCalculoImovelRuralVo);
         }
         else
         {
            throw new ConsultaException(MensagemErro.BASE_DE_CALCULO_NAO_ENCONTRADA);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_BASE_CALCULO_IMOVEL_RURAL);
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
      return baseCalculoImovelRuralVo;
   }
   
   private PreparedStatement montaPreparedStatementConsultaIntervalos(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
      throws SQLException
   {
      String sql = "SELECT * " + 
      "  FROM ITC.itctb52_base_calc_imov_rural t52 " + 
      " WHERE (? BETWEEN t52.qtde_distancia_inicial AND t52.qtde_distancia_final OR " + 
      "       (? >= t52.qtde_distancia_inicial AND " + 
      "       t52.qtde_distancia_final IS NULL) OR " + 
      "       (t52.qtde_distancia_inicial IS NULL AND " + 
      "       ? <= t52.qtde_distancia_final))    " + 
      "   AND (? BETWEEN t52.perc_atividade_inicial AND t52.perc_atividade_final OR " + 
      "       (? >= t52.perc_atividade_inicial AND " + 
      "       t52.perc_atividade_final IS NULL) OR " + 
      "       (? <= t52.perc_atividade_final AND " + 
      "       t52.perc_atividade_inicial IS NULL)) " + 
      "   AND (? BETWEEN t52.perc_area_expl_inicial AND t52.perc_area_expl_final OR " + 
      "       (? >= t52.perc_area_expl_inicial AND " + 
      "       t52.perc_area_expl_final IS NULL) OR " + 
      "       (? <= t52.perc_area_expl_final AND " + 
      "       t52.perc_area_expl_inicial IS NULL)) " + 
      "   AND t52.stat_base_calc = 1 " + 
      "   AND t52.tipo_distancia = ? " + 
      "   AND t52.tipo_atividade = ? " + 
      " ORDER BY t52.tipo_distancia, t52.qtde_distancia_inicial";
      
      PreparedStatement ps = conn.prepareStatement(sql);
      int contador = 1;
      
      //Seta os statements
      ps.setInt(contador++, baseCalculoImovelRuralVo.getQuantidadeDistanciaInicial());
      ps.setInt(contador++, baseCalculoImovelRuralVo.getQuantidadeDistanciaInicial());
      ps.setInt(contador++, baseCalculoImovelRuralVo.getQuantidadeDistanciaInicial());
      ps.setInt(contador++, baseCalculoImovelRuralVo.getPercentualAtividadeInicial());
      ps.setInt(contador++, baseCalculoImovelRuralVo.getPercentualAtividadeInicial());
      ps.setInt(contador++, baseCalculoImovelRuralVo.getPercentualAtividadeInicial());
      ps.setInt(contador++, baseCalculoImovelRuralVo.getPercentualAreaExploradaInical());
      ps.setInt(contador++, baseCalculoImovelRuralVo.getPercentualAreaExploradaInical());
      ps.setInt(contador++, baseCalculoImovelRuralVo.getPercentualAreaExploradaInical());
      ps.setInt(contador++, baseCalculoImovelRuralVo.getTipoDistancia().getValorCorrente());
      ps.setInt(contador++, baseCalculoImovelRuralVo.getTipoAtividade().getValorCorrente());
      
      return ps;
   }
}
