/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: VeiculoQDao.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data revisão: 14/11/2015
 */
package br.gov.mt.sefaz.itc.model.giaitcd.fichabem.fichaveiculo;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaVeiculo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe de acesso a dados (Data Access Object).
 * @author Dherkyan Ribeiro da Silva
 * @version 1.2 $
 */
public class FichaVeiculoQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposFichaVeiculo
{
   /**
    * Construtor que recebe a Conexão com o Banco de dados.
    * @param conexao objeto de conexão com o banco de dados.
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public FichaVeiculoQDao(Connection conexao)
   {
      super(conexao);
   }

   private void getVeiculo(final ResultSet rs, final FichaVeiculoVo veiculoVo) throws ObjetoObrigatorioException, SQLException
   {
      Validador.validaObjeto(rs);
      Validador.validaObjeto(veiculoVo);
      veiculoVo.setCodigo(rs.getLong(CAMPO_CODIGO_VEICULO));
      veiculoVo.setSiglaUfVeiculo(rs.getString(CAMPO_SIGLA_UF_VEICULO));
      veiculoVo.setNumrPlaca(rs.getString(CAMPO_NUMERO_PLACA));
      veiculoVo.setNumrRenavam(rs.getLong(CAMPO_NUMERO_RENAVAM));
      // CAMPO_DESCRICAO_MARCA_MODELO
      veiculoVo.setAnoFabricacao(rs.getInt(CAMPO_ANO_FABRICACAO));
      veiculoVo.setNomeProprietario(rs.getString(CAMPO_NOME_PROPRIETARIO));
      veiculoVo.setValorVenal(rs.getDouble(CAMPO_VALOR_VENAL));
      veiculoVo.setAnoReferencia(rs.getInt(CAMPO_ANO_REFERENCIA));
      veiculoVo.setBemTributavelVo(new BemTributavelVo(rs.getLong(CAMPO_ITCTB18_CODIGO_BEM_TRIBUTAVEL)));
      veiculoVo.setValorInformado(rs.getDouble(CAMPO_VALOR_INFORMADO));
   }

   public FichaVeiculoVo findVeiculo(final FichaVeiculoVo veiculoVo) throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.validaObjeto(veiculoVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindVeiculo(veiculoVo));
         prepareStatementFindVeiculo(ps, veiculoVo);
         rs = ps.executeQuery();
         if (rs.next())
         {
            getVeiculo(rs, veiculoVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_VEICULO);
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
      return veiculoVo;
   }

   public FichaVeiculoVo listarVeiculo(final FichaVeiculoVo veiculoVo) throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.validaObjeto(veiculoVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindVeiculo(veiculoVo));
         prepareStatementFindVeiculo(ps, veiculoVo);
         rs = ps.executeQuery();
         while (rs.next())
         {
            FichaVeiculoVo veiculoVoAtual = new FichaVeiculoVo();
            getVeiculo(rs, veiculoVoAtual);
            veiculoVo.getCollVO().add(veiculoVoAtual);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.LISTAR_VEICULO);
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
      return veiculoVo;
   }

   private String getSQLFindVeiculo(FichaVeiculoVo veiculoVo) throws ObjetoObrigatorioException, ClassCastException
   {
      Validador.validaObjeto(veiculoVo);
      FichaVeiculoVo parametroConsulta = (FichaVeiculoVo) veiculoVo.getParametroConsulta();
      Validador.validaObjeto(parametroConsulta);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT DISTINCT VEICULO." + CAMPO_CODIGO_VEICULO + " ");
      sql.append(" , VEICULO." + CAMPO_SIGLA_UF_VEICULO + " ");
      sql.append(" , VEICULO." + CAMPO_NUMERO_PLACA + " ");
      sql.append(" , VEICULO." + CAMPO_NUMERO_RENAVAM + " ");
      sql.append(" , VEICULO." + CAMPO_DESCRICAO_MARCA_MODELO + " ");
      sql.append(" , VEICULO." + CAMPO_ANO_FABRICACAO + " ");
      sql.append(" , VEICULO." + CAMPO_NOME_PROPRIETARIO + " ");
      sql.append(" , VEICULO." + CAMPO_VALOR_VENAL + " ");
      sql.append(" , VEICULO." + CAMPO_ANO_REFERENCIA + " ");
      sql.append(" , VEICULO." + CAMPO_ITCTB18_CODIGO_BEM_TRIBUTAVEL + " ");
      sql.append(" , VEICULO." + CAMPO_VALOR_VENAL + " ");
      sql.append(" , VEICULO." + CAMPO_VALOR_INFORMADO + " ");
      sql.append("  FROM " + TABELA_VEICULO + " VEICULO ");
      sql.append("  WHERE 1 = 1 ");
      if (veiculoVo.isConsultaParametrizada())
      {
         //CAMPO_CODIGO_VEICULO
         if (Validador.isNumericoValido(parametroConsulta.getCodigo()))
         {
            sql.append("  AND VEICULO." + CAMPO_CODIGO_VEICULO + " = ? ");
         }
         //CAMPO_SIGLA_UF_VEICULO
         if (Validador.isStringValida(parametroConsulta.getSiglaUfVeiculo()))
         {
            sql.append("  AND VEICULO." + CAMPO_SIGLA_UF_VEICULO + " = ? ");
         }
         //CAMPO_NUMERO_PLACA
         if (Validador.isStringValida(parametroConsulta.getNumrPlaca()))
         {
            sql.append("  AND VEICULO." + CAMPO_NUMERO_PLACA + " = ? ");
         }
         //CAMPO_NUMERO_RENAVAM
         if (Validador.isNumericoValido(parametroConsulta.getNumrRenavam()))
         {
            sql.append("  AND VEICULO." + CAMPO_NUMERO_RENAVAM + " = ? ");
         }
         //CAMPO_DESCRICAO_MARCA_MODELO
         if (Validador.isStringValida("falta capturar marca e modelo"))
         {
            // sql.append("  AND VEICULO." + CAMPO_DESCRICAO_MARCA_MODELO + " = ? ");
         }
         //CAMPO_ANO_FABRICACAO
         if (Validador.isNumericoValido(parametroConsulta.getAnoFabricacao()))
         {
            sql.append("  AND VEICULO." + CAMPO_ANO_FABRICACAO + " = ? ");
         }
         //CAMPO_NOME_PROPRIETARIO
         if (Validador.isStringValida(parametroConsulta.getNomeProprietario()))
         {
            sql.append("  AND VEICULO." + CAMPO_NOME_PROPRIETARIO + " = ? ");
         }
         //CAMPO_VALOR_VENAL
         if (Validador.isNumericoValido(parametroConsulta.getValorVenal()))
         {
            sql.append("  AND VEICULO." + CAMPO_VALOR_VENAL + " = ? ");
         }
         //CAMPO_ANO_REFERENCIA
         if (Validador.isNumericoValido(parametroConsulta.getAnoReferencia()))
         {
            sql.append("  AND VEICULO." + CAMPO_ANO_REFERENCIA + " = ? ");
         }
         //CAMPO_ITCTB18_CODIGO_BEM_TRIBUTAVEL
         if (Validador.isNumericoValido(parametroConsulta.getBemTributavelVo().getCodigo()))
         {
            sql.append("  AND VEICULO." + CAMPO_ITCTB18_CODIGO_BEM_TRIBUTAVEL + " = ? ");
         }
         //CAMPO_VALOR_INFORMADO
         if (Validador.isNumericoValido(parametroConsulta.getValorInformado()))
         {
            sql.append("  AND VEICULO." + CAMPO_VALOR_INFORMADO + " = ? ");
         }
      }
      sql.append(" ORDER BY VEICULO." + CAMPO_CODIGO_VEICULO + " ");
      //System.out.println("SQL : "+sql.toString());
      return sql.toString();
   }

   private void prepareStatementFindVeiculo(PreparedStatement ps, FichaVeiculoVo veiculoVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      FichaVeiculoVo parametroConsulta = (FichaVeiculoVo) veiculoVo.getParametroConsulta();
      Validador.validaObjeto(veiculoVo);
      int contador = 0;
      if (veiculoVo.isConsultaParametrizada())
      {
         //CAMPO_CODIGO_VEICULO
         if (Validador.isNumericoValido(parametroConsulta.getCodigo()))
         {
            ps.setLong(++contador, parametroConsulta.getCodigo());
         }
         //CAMPO_SIGLA_UF_VEICULO
         if (Validador.isStringValida(parametroConsulta.getSiglaUfVeiculo()))
         {
            ps.setString(++contador, parametroConsulta.getSiglaUfVeiculo());
         }
         //CAMPO_NUMERO_PLACA
         if (Validador.isStringValida(parametroConsulta.getNumrPlaca()))
         {
            ps.setString(++contador, parametroConsulta.getNumrPlaca());
         }
         //CAMPO_NUMERO_RENAVAM
         if (Validador.isNumericoValido(parametroConsulta.getNumrRenavam()))
         {
            ps.setLong(++contador, parametroConsulta.getNumrRenavam());
         }
         //CAMPO_DESCRICAO_MARCA_MODELO
         if (Validador.isStringValida("falta capturar marca e modelo"))
         {
            // sql.append("  AND VEICULO." + CAMPO_DESCRICAO_MARCA_MODELO + " = ? ");
         }
         //CAMPO_ANO_FABRICACAO
         if (Validador.isNumericoValido(parametroConsulta.getAnoFabricacao()))
         {
            ps.setInt(++contador, parametroConsulta.getAnoFabricacao());
         }
         //CAMPO_NOME_PROPRIETARIO
         if (Validador.isStringValida(parametroConsulta.getNomeProprietario()))
         {
            ps.setString(++contador, parametroConsulta.getNomeProprietario());
         }
         //CAMPO_VALOR_VENAL
         if (Validador.isNumericoValido(parametroConsulta.getValorVenal()))
         {
            ps.setDouble(++contador, parametroConsulta.getValorVenal());
         }
         //CAMPO_ANO_REFERENCIA
         if (Validador.isNumericoValido(parametroConsulta.getAnoReferencia()))
         {
            ps.setInt(++contador, parametroConsulta.getAnoReferencia());
         }
         //CAMPO_ITCTB18_CODIGO_BEM_TRIBUTAVEL
         if (Validador.isNumericoValido(parametroConsulta.getBemTributavelVo().getCodigo()))
         {
            ps.setLong(++contador, parametroConsulta.getBemTributavelVo().getCodigo());
         }
         //CAMPO_VALOR_INFORMADO
         if (Validador.isNumericoValido(parametroConsulta.getValorInformado()))
         {
            ps.setDouble(++contador, parametroConsulta.getValorInformado());
         }
      }
   }

   private String getSQLListarVeiculo(FichaVeiculoVo veiculoVo)
   {
      StringBuffer sql = new StringBuffer();
      return sql.toString();
   }

   private void prepareStatementListVeiculo(PreparedStatement ps, FichaVeiculoVo veiculoVo)
   {
   }
}
