/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CriterioMunicipioQDao.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data criação : 21/09/2015
 * Data ultima revisão : 21/09/2015
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposCriterioMunicipio;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


public class CriterioMunicipioQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposCriterioMunicipio
{
   /* ---------------------------------------------------------------------------------------------
     *                                Construtores
     * --------------------------------------------------------------------------------------------- */
   public CriterioMunicipioQDao(Connection conexao)
   {
      super(conexao);
   }

   /* ---------------------------------------------------------------------------------------------
     *                                Métodos  DAO
     * --------------------------------------------------------------------------------------------- */

   public CriterioMunicipioVo findCriterioMunicipio(CriterioMunicipioVo criterioMunicipioVo) throws ObjetoObrigatorioException, 
         ConsultaException
   {
      Validador.isObjetoValido(criterioMunicipioVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindCriterioMunicipio(criterioMunicipioVo));
         prepareStatementFindCriterioMunicipio(ps, criterioMunicipioVo);
         rs = ps.executeQuery();
         if (rs.next())
         {
            getCriterioMunicipio(rs, criterioMunicipioVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_CRITERIO_MUNICIPIO);
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
      return criterioMunicipioVo;
   }
   
   public CriterioMunicipioVo listarCriterioMunicipioAtivo(CriterioMunicipioVo criterioMunicipioVo) throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.isObjetoValido(criterioMunicipioVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLListarDistancia(criterioMunicipioVo));
         prepareStatementListarDistancia(ps, criterioMunicipioVo);
         Collection municipios = new ArrayList();
         for (rs = ps.executeQuery(); rs.next(); )
         {
            CriterioMunicipioVo c = new CriterioMunicipioVo();
            getCriterioMunicipio(rs, c);
            municipios.add(c);
         }
         if (Validador.isCollectionValida(municipios))
         {
            criterioMunicipioVo.setCollVO(municipios);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.LISTAR_CRITERIO_MUNICIPIO);
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
      return criterioMunicipioVo;

   }

   /* ---------------------------------------------------------------------------------------------
    *                               Métodos Utilitarios
    * --------------------------------------------------------------------------------------------- */

   private void getCriterioMunicipio(ResultSet rs, CriterioMunicipioVo criterioMunicipioVo) throws SQLException
   {
      Validador.isObjetoValido(rs);
      Validador.isObjetoValido(criterioMunicipioVo);
      criterioMunicipioVo.setCodigo(rs.getLong(CAMPO_CODIGO_CRITERIO_MUNICIPIO));
      criterioMunicipioVo.setValorTotalMinimo(rs.getDouble(CAMPO_VALOR_TOTAL_MINIMO));
      criterioMunicipioVo.setValorTotalMedio(rs.getDouble(CAMPO_VALOR_TOTAL_MEDIO));
      criterioMunicipioVo.setValorTotalMaximo(rs.getDouble(CAMPO_VALOR_TOTAL_MAXIMO));
      criterioMunicipioVo.setStatusResgistroCriterioMunicipioVo(new DomnStatusRegistro(rs.getInt(CAMPO_STATUS_RESGISTRO)));
      criterioMunicipioVo.setDataAtualizacaoBD(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
      MunicipioIntegracaoVo municipio = new MunicipioIntegracaoVo();
      municipio.setCodgMunicipio(new Integer(rs.getInt(CAMPO_CODIGO_MUNICIPIO)));
      criterioMunicipioVo.setMunicipio(municipio);
   }

   private void prepareStatementFindCriterioMunicipio(PreparedStatement ps, CriterioMunicipioVo criterioMunicipioVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(criterioMunicipioVo);
      int contador = 0;
      if (criterioMunicipioVo.isConsultaParametrizada())
      {
         // CODIGO
         if (Validador.isNumericoValido(criterioMunicipioVo.getParametroConsulta().getCodigo()))
         {
            ps.setLong(++contador, criterioMunicipioVo.getParametroConsulta().getCodigo());
         }
         // CODIGO MUNICIPIO
         if (Validador.isNumericoValido(criterioMunicipioVo.getParametroConsulta().getMunicipio().getCodgMunicipio()))
         {
            ps.setLong(++contador, criterioMunicipioVo.getParametroConsulta().getMunicipio().getCodgMunicipio());
         }
         // STATUS REGISTRO
         if (Validador.isDominioNumericoValido( criterioMunicipioVo.getParametroConsulta().getStatusResgistroCriterioMunicipioVo()))
         {
            ps.setLong(++contador, criterioMunicipioVo.getParametroConsulta().getStatusResgistroCriterioMunicipioVo().getValorCorrente());
         }
      }
   }

   private String getSQLFindCriterioMunicipio(CriterioMunicipioVo criterioMunicipioVo)
   {
      Validador.isObjetoValido(criterioMunicipioVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT ");
      sql.append(" CRITERIO_MUNICIPIO." + CAMPO_CODIGO_CRITERIO_MUNICIPIO);
      sql.append(", CRITERIO_MUNICIPIO." + CAMPO_VALOR_TOTAL_MINIMO);
      sql.append(", CRITERIO_MUNICIPIO." + CAMPO_VALOR_TOTAL_MEDIO);
      sql.append(", CRITERIO_MUNICIPIO." + CAMPO_VALOR_TOTAL_MAXIMO);
      sql.append(", CRITERIO_MUNICIPIO." + CAMPO_STATUS_RESGISTRO);
      sql.append(", CRITERIO_MUNICIPIO." + CAMPO_DATA_ATUALIZACAO_BD);
      sql.append(", CRITERIO_MUNICIPIO." + CAMPO_CODIGO_MUNICIPIO);
      sql.append(" FROM " + TABELA_CRITERIO_MUNICIPIO + " CRITERIO_MUNICIPIO ");
      sql.append(" WHERE 1 = 1 ");
      if (criterioMunicipioVo.isConsultaParametrizada())
      {
         if (Validador.isNumericoValido(criterioMunicipioVo.getParametroConsulta().getCodigo()))
         {
            sql.append(" AND CRITERIO_MUNICIPIO." + CAMPO_CODIGO_CRITERIO_MUNICIPIO + " = ?");
         }
         if (Validador.isNumericoValido(criterioMunicipioVo.getParametroConsulta().getMunicipio().getCodgMunicipio()))
         {
            sql.append(" AND CRITERIO_MUNICIPIO." + CAMPO_CODIGO_MUNICIPIO+ " = ?");
         }
         if (Validador.isDominioNumericoValido( criterioMunicipioVo.getParametroConsulta().getStatusResgistroCriterioMunicipioVo()))
         {
            sql.append(" AND CRITERIO_MUNICIPIO." + CAMPO_STATUS_RESGISTRO + " = ?");
         }
      }
      sql.append(" ORDER BY CRITERIO_MUNICIPIO." + CAMPO_CODIGO_CRITERIO_MUNICIPIO + " ");
     // System.out.println(sql.toString());
      return sql.toString();
   }

   private String getSQLListarDistancia(CriterioMunicipioVo criterioMunicipioVo)
   {
      Validador.isObjetoValido(criterioMunicipioVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT ");
      sql.append(" CRITERIO_MUNICIPIO." + CAMPO_CODIGO_CRITERIO_MUNICIPIO);
      sql.append(", CRITERIO_MUNICIPIO." + CAMPO_VALOR_TOTAL_MINIMO);
      sql.append(", CRITERIO_MUNICIPIO." + CAMPO_VALOR_TOTAL_MEDIO);
      sql.append(", CRITERIO_MUNICIPIO." + CAMPO_VALOR_TOTAL_MAXIMO);
      sql.append(", CRITERIO_MUNICIPIO." + CAMPO_STATUS_RESGISTRO);
      sql.append(", CRITERIO_MUNICIPIO." + CAMPO_DATA_ATUALIZACAO_BD);
      sql.append(", CRITERIO_MUNICIPIO." + CAMPO_CODIGO_MUNICIPIO);
      sql.append(" FROM " + TABELA_CRITERIO_MUNICIPIO + " CRITERIO_MUNICIPIO ");
      sql.append(" WHERE 1 = 1 ");
      if (criterioMunicipioVo.isConsultaParametrizada())
      {
         
      
      }
      sql.append("  AND CRITERIO_MUNICIPIO." + CAMPO_STATUS_RESGISTRO+ " = " + DomnStatusRegistro.ATIVO);
      sql.append("  ORDER BY CRITERIO_MUNICIPIO." + CAMPO_CODIGO_CRITERIO_MUNICIPIO);
      //System.out.println(sql.toString());
      return sql.toString();
   }

   private void prepareStatementListarDistancia(PreparedStatement ps, CriterioMunicipioVo criterioMunicipioVo) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(criterioMunicipioVo);
      int contador = 0;
      if (criterioMunicipioVo.isConsultaParametrizada())
      {
      }
   }
}
