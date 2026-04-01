package br.gov.mt.sefaz.itc.model.relatorio.parametrorelatorio;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.PedidoRelatorioVo;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposParametroRelatorio;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * Classe Responsável por encapsular a lógia de acesso ao BD
 * @author Dherkyan Ribeiro da Silva
 */
public class ParametroRelatorioQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposParametroRelatorio
{
   public ParametroRelatorioQDao(Connection conexao)
   {
      super(conexao);
   }

   
   /**
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public ParametroRelatorioVo findParametroRelatorio(ParametroRelatorioVo parametroRelatorioVo) throws ObjetoObrigatorioException, 
         ConsultaException, ParametroObrigatorioException
   {
      Validador.isObjetoValido(parametroRelatorioVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindParametroRelatorio(parametroRelatorioVo));
         prepareStatementFindParametroRelatorio(ps, parametroRelatorioVo);
         rs = ps.executeQuery();
         if (rs.next())
         {
            getParametroRelatorio(rs, parametroRelatorioVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_PARAMETRO_RELATORIO);
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
      return parametroRelatorioVo;

   }

   /**
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public ParametroRelatorioVo listarParametrosRelatorio(ParametroRelatorioVo parametroRelatorioVo) throws ObjetoObrigatorioException, 
         ConsultaException, ParametroObrigatorioException
   {
      Validador.isObjetoValido(parametroRelatorioVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLListarParametroRelatorio(parametroRelatorioVo));
         prepareStatementListarParametroRelatorio(ps, parametroRelatorioVo);
         Collection parametros = new ArrayList();
         for (rs = ps.executeQuery(); rs.next(); )
         {
            ParametroRelatorioVo p = new ParametroRelatorioVo();
            getParametroRelatorio(rs, p);
            parametros.add(p);
         }
         if (Validador.isCollectionValida(parametros))
         {
            parametroRelatorioVo.setCollVO(parametros);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.LISTAR_PARAMETRO_RELATORIO);
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
      return parametroRelatorioVo;

   }

   /**
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   private void getParametroRelatorio(final ResultSet rs, final ParametroRelatorioVo parametroRelatorioVo) throws ObjetoObrigatorioException, 
         SQLException, ParametroObrigatorioException
   {
      Validador.isObjetoValido(rs);
      Validador.isObjetoValido(parametroRelatorioVo);
      parametroRelatorioVo.setCodigo(rs.getLong(CAMPO_CODIGO_PARAMETRO));
      parametroRelatorioVo.setPedidoRelatorio(new PedidoRelatorioVo(rs.getLong(CAMPO_TB60_CODIGO_RELATORIO)));
      parametroRelatorioVo.setNomeParametro(rs.getString(CAMPO_NOME_PARAMETRO));
      parametroRelatorioVo.setValorParametro(rs.getString(CAMPO_VALOR_PARAMETRO) );
   }

   private void prepareStatementFindParametroRelatorio(PreparedStatement ps, final ParametroRelatorioVo parametroRelatorioVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(parametroRelatorioVo);
      int contador = 0;
      if (parametroRelatorioVo.isConsultaParametrizada())
      {
         ParametroRelatorioVo parametro = parametroRelatorioVo.getParametroConsulta();

         // CAMPO_CODIGO_PARAMETRO
         if (Validador.isNumericoValido(parametro.getCodigo()))
         {
            ps.setLong(++contador, parametro.getCodigo());
         }
         // CAMPO_TB60_CODIGO_RELATORIO
         if (Validador.isNumericoValido(parametro.getPedidoRelatorio().getCodigo()))
         {
            ps.setLong(++contador, parametro.getPedidoRelatorio().getCodigo());
         }
         // CAMPO_NOME_PARAMETRO
         if (Validador.isStringValida(parametro.getNomeParametro()))
         {
            ps.setString(++contador, parametro.getNomeParametro());
         }
         // CAMPO_VALOR_PARAMETRO
         if (Validador.isStringValida(parametro.getValorParametro()))
         {
            ps.setString(++contador, parametro.getValorParametro());
         }
      }
   }


   /**
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   private String getSQLFindParametroRelatorio(final ParametroRelatorioVo parametroRelatorioVo)
   {
      Validador.isObjetoValido(parametroRelatorioVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT ");
      sql.append(" PARAMETRO." + CAMPO_CODIGO_PARAMETRO);
      sql.append(" , PARAMETRO." + CAMPO_TB60_CODIGO_RELATORIO);
      sql.append(" , PARAMETRO." + CAMPO_NOME_PARAMETRO);
      sql.append(" , PARAMETRO." + CAMPO_VALOR_PARAMETRO);
      sql.append(" FROM " + TABELA_PARAMETRO_RELATORIO + " PARAMETRO ");
      sql.append(" WHERE 1 = 1 ");

      if (parametroRelatorioVo.isConsultaParametrizada())
      {

         ParametroRelatorioVo parametro = parametroRelatorioVo.getParametroConsulta();

         // CAMPO_CODIGO_PARAMETRO
         if (Validador.isNumericoValido(parametro.getCodigo()))
         {
            sql.append(" AND PARAMETRO." + CAMPO_CODIGO_PARAMETRO + " = ?");
         }
         // CAMPO_TB60_CODIGO_RELATORIO
         if (Validador.isNumericoValido(parametro.getPedidoRelatorio().getCodigo()))
         {
            sql.append(" AND PARAMETRO." + CAMPO_TB60_CODIGO_RELATORIO + " = ?");
         }
         // CAMPO_NOME_PARAMETRO
         if (Validador.isStringValida(parametro.getNomeParametro()))
         {
            sql.append(" AND PARAMETRO." + CAMPO_NOME_PARAMETRO + " = ?");
         }
         // CAMPO_VALOR_PARAMETRO
         if (Validador.isStringValida(parametro.getValorParametro()))
         {
            sql.append(" AND PARAMETRO." + CAMPO_VALOR_PARAMETRO + " = ?");
         }
      }

      sql.append(" ORDER BY PARAMETRO." + CAMPO_CODIGO_PARAMETRO + " ");
      return sql.toString();
   }


   /**
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   private void prepareStatementListarParametroRelatorio(PreparedStatement ps, final ParametroRelatorioVo parametroRelatorioVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(parametroRelatorioVo);
      int contador = 0;
      if (parametroRelatorioVo.isConsultaParametrizada())
      {
         ParametroRelatorioVo parametro = parametroRelatorioVo.getParametroConsulta();

         // CAMPO_CODIGO_PARAMETRO
         if (Validador.isNumericoValido(parametro.getCodigo()))
         {
            ps.setLong(++contador, parametro.getCodigo());
         }
         // CAMPO_TB60_CODIGO_RELATORIO
         if (Validador.isNumericoValido(parametro.getPedidoRelatorio().getCodigo()))
         {
            ps.setLong(++contador, parametro.getPedidoRelatorio().getCodigo());
         }
         // CAMPO_NOME_PARAMETRO
         if (Validador.isStringValida(parametro.getNomeParametro()))
         {
            ps.setString(++contador, parametro.getNomeParametro());
         }
         // CAMPO_VALOR_PARAMETRO
         if (Validador.isStringValida(parametro.getValorParametro()))
         {
            ps.setString(++contador, parametro.getValorParametro());
         }
      }
   }


   /**
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   private String getSQLListarParametroRelatorio(final ParametroRelatorioVo parametroRelatorioVo)
   {
      Validador.isObjetoValido(parametroRelatorioVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT ");
      sql.append(" PARAMETRO." + CAMPO_CODIGO_PARAMETRO);
      sql.append(" , PARAMETRO." + CAMPO_TB60_CODIGO_RELATORIO);
      sql.append(" , PARAMETRO." + CAMPO_NOME_PARAMETRO);
      sql.append(" , PARAMETRO." + CAMPO_VALOR_PARAMETRO);
      sql.append(" FROM " + TABELA_PARAMETRO_RELATORIO + " PARAMETRO ");
      sql.append(" WHERE 1 = 1 ");

      if (parametroRelatorioVo.isConsultaParametrizada())
      {

         ParametroRelatorioVo parametro = parametroRelatorioVo.getParametroConsulta();

         // CAMPO_CODIGO_PARAMETRO
         if (Validador.isNumericoValido(parametro.getCodigo()))
         {
            sql.append(" AND PARAMETRO." + CAMPO_CODIGO_PARAMETRO + " = ?");
         }
         // CAMPO_TB60_CODIGO_RELATORIO
         if (Validador.isNumericoValido(parametro.getPedidoRelatorio().getCodigo()))
         {
            sql.append(" AND PARAMETRO." + CAMPO_TB60_CODIGO_RELATORIO + " = ?");
         }
         // CAMPO_NOME_PARAMETRO
         if (Validador.isStringValida(parametro.getNomeParametro()))
         {
            sql.append(" AND PARAMETRO." + CAMPO_NOME_PARAMETRO + " = ?");
         }
         // CAMPO_VALOR_PARAMETRO
         if (Validador.isStringValida(parametro.getValorParametro()))
         {
            sql.append(" AND PARAMETRO." + CAMPO_VALOR_PARAMETRO + " = ?");
         }
      }

      sql.append(" ORDER BY PARAMETRO." + CAMPO_CODIGO_PARAMETRO + " ");
      return sql.toString();
   }

}
