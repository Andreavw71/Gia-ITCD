/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: RebanhoLPMQDao.java
 * Revisão: Dherkyan Ribeiro
 * Data revisão: 14/11/2015
 */
package br.gov.mt.sefaz.itc.model.giaitcd.fichabem.ficharebanholpm;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaRebanhoLPM;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import br.gov.mt.sefaz.itc.util.integracao.lpm.ProdutoNcmIntegracaoVo;

import br.gov.mt.sefaz.lpm.model.produtoncm.ProdutoNcmVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FichaRebanhoLPMQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposFichaRebanhoLPM
{
   /**
    * Construtor que recebe a Conexão com o Banco de dados.
    * @param conexao objeto de conexão com o banco de dados.
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public FichaRebanhoLPMQDao(Connection conexao)
   {
      super(conexao);
   }

   private void getRebanhoLPM(final ResultSet rs, final FichaRebanhoLPMVo rebanhoLpmVo) throws ObjetoObrigatorioException, SQLException, ParametroObrigatorioException
   {
      Validador.validaObjeto(rs);
      Validador.validaObjeto(rebanhoLpmVo);
      rebanhoLpmVo.setCodigo(rs.getLong(CAMPO_CODIGO_REBANHO));
      ProdutoNcmVo produto = new ProdutoNcmVo();
      produto.setCodgProdutoSeqc(rs.getInt(CAMPO_ACGTB95_CODIGO_PRODUTO ));
      rebanhoLpmVo.setProdutoNcmIntegracaoVo( new ProdutoNcmIntegracaoVo(produto));
      rebanhoLpmVo.setDescricao(rs.getString(CAMPO_DESCRICAO));
      rebanhoLpmVo.setValorUnitario(rs.getDouble(CAMPO_VALOR_UNITARIO));
      rebanhoLpmVo.setQuantidade(rs.getInt(CAMPO_QUANTIDADE));
      rebanhoLpmVo.setValorCalculado(rs.getDouble(CAMPO_VALOR_CALCULADO));
      rebanhoLpmVo.setValorInformado(rs.getDouble(CAMPO_VALOR_INFORMADO));
      rebanhoLpmVo.setBemTributavelVo(new BemTributavelVo(rs.getLong(CAMPO_ITCTB18_CODIGO_BEM_TRIBUTAVEL)));
   }

   public FichaRebanhoLPMVo findRebanho(final FichaRebanhoLPMVo rebanhoLpmVo) throws ObjetoObrigatorioException, ConsultaException, 
         ParametroObrigatorioException
   {
      Validador.validaObjeto(rebanhoLpmVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindRebanho(rebanhoLpmVo));
         prepareStatementFindRebanho(ps, rebanhoLpmVo);
         rs = ps.executeQuery();
         if (rs.next())
         {
            getRebanhoLPM(rs, rebanhoLpmVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_REBANHO_LPM);
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
      return rebanhoLpmVo;
   }

   public FichaRebanhoLPMVo listarRebanho(final FichaRebanhoLPMVo rebanho) throws ObjetoObrigatorioException, ConsultaException, 
         ParametroObrigatorioException
   {
      Validador.validaObjeto(rebanho);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindRebanho(rebanho));
         prepareStatementFindRebanho(ps, rebanho);
         rs = ps.executeQuery();
         while (rs.next())
         {
            FichaRebanhoLPMVo tempVo = new FichaRebanhoLPMVo();
            getRebanhoLPM(rs, tempVo);
            rebanho.getCollVO().add(tempVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.LISTAR_REBANHO_LPM);
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
      return rebanho;
   }

   private String getSQLFindRebanho(FichaRebanhoLPMVo rebanho) throws ObjetoObrigatorioException, ClassCastException
   {
      Validador.validaObjeto(rebanho);
      FichaRebanhoLPMVo parametroConsulta = (FichaRebanhoLPMVo) rebanho.getParametroConsulta();
      Validador.validaObjeto(parametroConsulta);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT DISTINCT REBANHO_LPM." + CAMPO_CODIGO_REBANHO + " ");
      sql.append(" , REBANHO_LPM ." + CAMPO_ACGTB95_CODIGO_PRODUTO+ " ");
      sql.append(" , REBANHO_LPM." + CAMPO_DESCRICAO + " ");
      sql.append(" , REBANHO_LPM." + CAMPO_VALOR_UNITARIO + " ");
      sql.append(" , REBANHO_LPM." + CAMPO_QUANTIDADE + " ");
      sql.append(" , REBANHO_LPM." + CAMPO_VALOR_CALCULADO + " ");
      sql.append(" , REBANHO_LPM." + CAMPO_VALOR_INFORMADO + " ");
      sql.append(" , REBANHO_LPM." + CAMPO_ITCTB18_CODIGO_BEM_TRIBUTAVEL + " ");
      sql.append("  FROM " + TABELA_REBANHO_LPM + " REBANHO_LPM ");
      sql.append("  WHERE 1 = 1 ");
      if (rebanho.isConsultaParametrizada())
      {
         //CAMPO_CODIGO_REBANHO
         if (Validador.isNumericoValido(parametroConsulta.getCodigo()))
         {
            sql.append("  AND REBANHO_LPM." + CAMPO_CODIGO_REBANHO + " = ? ");
         }
         //CAMPO_ACGTB95_CODIGO_PRODUTO
         if (Validador.isNumericoValido(parametroConsulta.getProdutoNcmIntegracaoVo().getCodigo() ))
         {
            sql.append("  AND REBANHO_LPM." + CAMPO_ACGTB95_CODIGO_PRODUTO + " = ? ");
         }
         //CAMPO_DESCRICAO
         if (Validador.isStringValida(parametroConsulta.getDescricao()))
         {
            sql.append("  AND REBANHO_LPM." + CAMPO_DESCRICAO + " = ? ");
         }
         //CAMPO_VALOR_UNITARIO
         if (Validador.isNumericoValido(parametroConsulta.getValorUnitario()))
         {
            sql.append("  AND REBANHO_LPM." + CAMPO_VALOR_UNITARIO + " = ? ");
         }
         //CAMPO_QUANTIDADE
         if (Validador.isNumericoValido(parametroConsulta.getQuantidade()))
         {
            sql.append("  AND REBANHO_LPM." + CAMPO_QUANTIDADE + " = ? ");
         }
         //CAMPO_VALOR_CALCULADO
         if (Validador.isNumericoValido(parametroConsulta.getValorCalculado()))
         {
            sql.append("  AND REBANHO_LPM." + CAMPO_VALOR_CALCULADO + " = ? ");
         }
         //CAMPO_VALOR_INFORMADO
         if (Validador.isNumericoValido(parametroConsulta.getValorInformado()))
         {
            sql.append("  AND REBANHO_LPM." + CAMPO_VALOR_INFORMADO + " = ? ");
         }
         //CAMPO_ITCTB18_CODIGO_BEM_TRIBUTAVEL
         if (Validador.isNumericoValido(parametroConsulta.getBemTributavelVo().getCodigo()))
         {
            sql.append("  AND REBANHO_LPM." + CAMPO_ITCTB18_CODIGO_BEM_TRIBUTAVEL + " = ? ");
         }
      }
      sql.append(" ORDER BY REBANHO_LPM." + CAMPO_CODIGO_REBANHO + " ");
      //System.out.println("SQL : "+sql.toString());
      return sql.toString();
   }

   private void prepareStatementFindRebanho(PreparedStatement ps, FichaRebanhoLPMVo rebanho) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      FichaRebanhoLPMVo parametroConsulta = (FichaRebanhoLPMVo) rebanho.getParametroConsulta();
      Validador.validaObjeto(rebanho);
      int contador = 0;
      if (rebanho.isConsultaParametrizada())
      {
         //CAMPO_CODIGO_REBANHO
         if (Validador.isNumericoValido(parametroConsulta.getCodigo()))
         {
            ps.setLong(++contador, parametroConsulta.getCodigo());
         }
         //CAMPO_ACGTB95_CODIGO_PRODUTO
         if (Validador.isNumericoValido(parametroConsulta.getProdutoNcmIntegracaoVo().getCodigo()))
         {
            ps.setLong(++contador, parametroConsulta.getProdutoNcmIntegracaoVo().getCodigo());
         }
         //CAMPO_DESCRICAO
         if (Validador.isStringValida(parametroConsulta.getDescricao()))
         {
            ps.setString(++contador, parametroConsulta.getDescricao());
         }
         //CAMPO_VALOR_UNITARIO
         if (Validador.isNumericoValido(parametroConsulta.getValorUnitario()))
         {
            ps.setDouble(++contador, parametroConsulta.getValorUnitario());
         }
         //CAMPO_QUANTIDADE
         if (Validador.isNumericoValido(parametroConsulta.getQuantidade()))
         {
            ps.setInt(++contador, parametroConsulta.getQuantidade());
         }
         //CAMPO_VALOR_CALCULADO
         if (Validador.isNumericoValido(parametroConsulta.getValorCalculado()))
         {
            ps.setDouble(++contador, parametroConsulta.getValorCalculado());
         }
         //CAMPO_VALOR_INFORMADO
         if (Validador.isNumericoValido(parametroConsulta.getValorInformado()))
         {
            ps.setDouble(++contador, parametroConsulta.getValorInformado());
         }
         //CAMPO_ITCTB18_CODIGO_BEM_TRIBUTAVEL
         if (Validador.isNumericoValido(parametroConsulta.getBemTributavelVo().getCodigo()))
         {
            ps.setLong(++contador, parametroConsulta.getBemTributavelVo().getCodigo());
         }
      }
   }

   private String getSQLListarRebanho(FichaRebanhoLPMVo rebanho)
   {
      StringBuffer sql = new StringBuffer();
      return sql.toString();
   }

   private void prepareStatementListRebanho(PreparedStatement ps,FichaRebanhoLPMVo rebanho)
   {
   }
}