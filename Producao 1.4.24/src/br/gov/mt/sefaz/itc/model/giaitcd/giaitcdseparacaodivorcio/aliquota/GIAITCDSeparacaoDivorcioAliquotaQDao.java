package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.aliquota;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDSeparacaoDivorcioAliquota;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GIAITCDSeparacaoDivorcioAliquotaQDao extends AbstractDao implements CamposGIAITCDSeparacaoDivorcioAliquota, TabelasITC
{
   /**
    * Contrutor padrão recebendo uma Conexão
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public GIAITCDSeparacaoDivorcioAliquotaQDao(Connection conexao)
   {
      super(conexao);
   }
   
   /**
    * Configura um objeto do tipo <code>GIAITCDSeparacaoDivorcioAliquotaVo</code> com os valores
    * recebidos no <code>ResultSet</code>
    *
    * @param rs            ResultSet
    * @param giaItcdSeparacaoDivorcioAliquotaVo  objeto a ser configurado
    * @throws ObjetoObrigatorioException
    * @throws SQLException
    */
   private void getGiaItcdSeparacaoAliquota(final ResultSet rs, final GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException, 
           SQLException
   {
      Validador.validaObjeto(rs);
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);
      giaItcdSeparacaoDivorcioAliquotaVo.getGiaItcdSeparacaoDivorcioVo().setCodigo(rs.getLong(CAMPO_CODIGO_GIA_ITCD));
      giaItcdSeparacaoDivorcioAliquotaVo.setCodigoAliquota(rs.getLong(CAMPO_CODIGO_ALIQUOTA));
      giaItcdSeparacaoDivorcioAliquotaVo.setPercentualAliquota(rs.getDouble(CAMPO_PERCENTUAL_ALIQUOTA));
      giaItcdSeparacaoDivorcioAliquotaVo.setValorRecolher(rs.getDouble(CAMPO_VALOR_RECOLHER));
      giaItcdSeparacaoDivorcioAliquotaVo.setValorBaseCalculo(rs.getDouble(CAMPO_BASE_CALCULO));
   }
   
   /**
    * Encontra "um" determinado Beneficiario do tipo Doação
    *
    * @param giaItcdSeparacaoDivorcioAliquotaVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    */
   public GIAITCDSeparacaoDivorcioAliquotaVo findGiaItcdSeparacaoAliquota(final GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = 
     conn.prepareStatement(getSQLFindGiaItcdSeparacaoAliquota(giaItcdSeparacaoDivorcioAliquotaVo));
         prepareStatementFindGiaItcdSeparacaoAliquota(ps, giaItcdSeparacaoDivorcioAliquotaVo);
         rs = ps.executeQuery();
         if (rs.next())
         {
            getGiaItcdSeparacaoAliquota(rs, giaItcdSeparacaoDivorcioAliquotaVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTA_GIA_ITCD_SEPARACAO__ALIQUOTA);
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
      return giaItcdSeparacaoDivorcioAliquotaVo;
   }
   
   
   /**
    * Encontra "um" determinado Beneficiario do tipo Doacão
    *
    * @param giaItcdSeparacaoDivorcioAliquotaVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    */
   public GIAITCDSeparacaoDivorcioAliquotaVo listGiaItcdSeparacaoAliquota(final GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = 
     conn.prepareStatement(getSQLFindGiaItcdSeparacaoAliquota(giaItcdSeparacaoDivorcioAliquotaVo));
         prepareStatementFindGiaItcdSeparacaoAliquota(ps, giaItcdSeparacaoDivorcioAliquotaVo);
         rs = ps.executeQuery();
         while (rs.next())
         {
            GIAITCDSeparacaoDivorcioAliquotaVo aliquotaAtual = new GIAITCDSeparacaoDivorcioAliquotaVo();
            getGiaItcdSeparacaoAliquota(rs, aliquotaAtual);
            giaItcdSeparacaoDivorcioAliquotaVo.getCollVO().add(aliquotaAtual);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTA_GIA_ITCD_SEPARACAO__ALIQUOTA);
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
      return giaItcdSeparacaoDivorcioAliquotaVo;
   }
   
   /**
    * Método responsável por retornar a String de Consulta (SELECT) SQL 
    * 
    * @param giaItcdSeparacaoDivorcioAliquotaVo
    * @return
    * @throws ObjetoObrigatorioException
    * @implemented by Lucas Nascimento
    */
   private String getSQLFindGiaItcdSeparacaoAliquota(final GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);
      
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT G.").append(CAMPO_CODIGO_GIA_ITCD).append(" ");
      sql.append(" , G.").append(CAMPO_CODIGO_ALIQUOTA).append(" ");
      sql.append(" , G.").append(CAMPO_PERCENTUAL_ALIQUOTA).append(" ");
      sql.append(" , G.").append(CAMPO_VALOR_RECOLHER).append(" ");
      sql.append(" , G.").append(CAMPO_BASE_CALCULO).append(" ");
      sql.append(" FROM ").append(TABELA_GIA_ITCD_SEPARACAO_ALIQUOTA).append(" G ");
      sql.append(" WHERE 1 = 1 ");
      if (giaItcdSeparacaoDivorcioAliquotaVo.isConsultaParametrizada())
      {
         GIAITCDSeparacaoDivorcioAliquotaVo parametro = (GIAITCDSeparacaoDivorcioAliquotaVo) giaItcdSeparacaoDivorcioAliquotaVo.getParametroConsulta();
         
         // CAMPO_CODIGO_GIA_ITCD
         if (Validador.isNumericoValido(parametro.getGiaItcdSeparacaoDivorcioVo().getCodigo()))
         {
            sql.append("   AND G.").append(CAMPO_CODIGO_GIA_ITCD).append(" = ? ");
         }
         //CAMPO_CODIGO_ALIQUOTA
         if (Validador.isNumericoValido(parametro.getCodigoAliquota()))
         {
            sql.append("   AND G.").append(CAMPO_CODIGO_ALIQUOTA).append(" = ? ");
         }
         //CAMPO_PERCENTUAL_ALIQUOTA
         if (Validador.isNumericoValido(parametro.getPercentualAliquota()))
         {
            sql.append("   AND G.").append(CAMPO_PERCENTUAL_ALIQUOTA).append(" = ? ");
         }
         //CAMPO_VALOR_RECOLHER
         if (Validador.isNumericoValido(parametro.getValorRecolher()))
         {
            sql.append("   AND G.").append(CAMPO_VALOR_RECOLHER).append(" = ? ");
         }
         //CAMPO_BASE_CALCULO
         if (Validador.isNumericoValido(parametro.getValorBaseCalculo()))
         {
            sql.append("   AND G.").append(CAMPO_BASE_CALCULO).append(" = ? ");
         }
      }
      sql.append(" ORDER BY G.").append(CAMPO_CODIGO_GIA_ITCD).append(" ");
      return sql.toString();
   }

   /**
    * Método responável por adicionar os parâmetros válidos na instrução SQL (java.sql.PreparedStatement)
    * @param ps (java.sql.PreparedStatement)
    * @param giaItcdSeparacaoDivorcioAliquotaVo (Value Object)
    * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
    * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
    * @implemented by Lucas Nascimento
    */
   private void prepareStatementFindGiaItcdSeparacaoAliquota(final PreparedStatement ps, final GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo) throws ObjetoObrigatorioException, 
           SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(giaItcdSeparacaoDivorcioAliquotaVo);
      int contador = 0;
      if (giaItcdSeparacaoDivorcioAliquotaVo.isConsultaParametrizada())
      {
         GIAITCDSeparacaoDivorcioAliquotaVo parametro = (GIAITCDSeparacaoDivorcioAliquotaVo) giaItcdSeparacaoDivorcioAliquotaVo.getParametroConsulta();
         
         // CAMPO_CODIGO_GIA_ITCD
         if (Validador.isNumericoValido(parametro.getGiaItcdSeparacaoDivorcioVo().getCodigo()))
         {
            ps.setLong(++contador, parametro.getGiaItcdSeparacaoDivorcioVo().getCodigo());
         }
         //CAMPO_CODIGO_ALIQUOTA
         if (Validador.isNumericoValido(parametro.getCodigoAliquota()))
         {
            ps.setDouble(++contador, parametro.getCodigoAliquota());
         }
         //CAMPO_PERCENTUAL_ALIQUOTA
         if (Validador.isNumericoValido(parametro.getPercentualAliquota()))
         {
            ps.setDouble(++contador, parametro.getPercentualAliquota());
         }
         //CAMPO_VALOR_RECOLHER
         if (Validador.isNumericoValido(parametro.getValorRecolher()))
         {
            ps.setDouble(++contador, parametro.getValorRecolher());
         }
         //CAMPO_BASE_CALCULO
         if (Validador.isNumericoValido(parametro.getValorBaseCalculo()))
         {
            ps.setDouble(++contador, parametro.getValorBaseCalculo());
         }
      }
   }
   
}
