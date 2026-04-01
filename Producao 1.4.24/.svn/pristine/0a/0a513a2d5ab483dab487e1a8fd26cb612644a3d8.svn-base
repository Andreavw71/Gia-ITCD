package br.gov.mt.sefaz.itc.model.tabelabasica.iptu;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoIPTU;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposITPTU;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


public class IPTUQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposITPTU
{
   /* ---------------------------------------------------------------------------------------------
     *                                Construtores
     * --------------------------------------------------------------------------------------------- */
   public IPTUQDao(Connection conexao)
   {
      super(conexao);
   }
   
   public IPTUVo finIPTUVo(IPTUVo iptuVo) throws ObjetoObrigatorioException, 
         ConsultaException
   {
      Validador.isObjetoValido(iptuVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindIPTUVo(iptuVo));
         prepareStatementFindIPTUVo(ps, iptuVo);
         rs = ps.executeQuery();
         if (rs.next())
         {
            getIPTUVo(rs, iptuVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.LISTAR_IPTU);
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
      return iptuVo;
   }
   
   
   public IPTUVo listarIPTUVo (IPTUVo iptuVo) throws ObjetoObrigatorioException, ConsultaException
   {
            Validador.isObjetoValido(iptuVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindIPTUVo(iptuVo));
         prepareStatementFindIPTUVo(ps, iptuVo);
         Collection iptuLista = new ArrayList();
         for (rs = ps.executeQuery(); rs.next(); )
         {
            IPTUVo c = new IPTUVo();
            getIPTUVo(rs, c);
            iptuLista.add(c);
         }
         if (Validador.isCollectionValida(iptuLista))
         {
            iptuVo.setCollVO(iptuLista);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.LISTAR_IPTU);
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
      return iptuVo;
   }
   
   private void getIPTUVo(ResultSet rs, IPTUVo iptuVo) throws SQLException
   {
      Validador.isObjetoValido(rs);
      Validador.isObjetoValido(iptuVo);
      
      iptuVo.setCodigo(rs.getLong(CAMPO_CODIGO_IPTU));
      MunicipioIntegracaoVo municipio = new MunicipioIntegracaoVo();
      municipio.setCodgMunicipio(new Integer(rs.getInt(CAMPO_CODIGO_MUNICIPIO)));
      iptuVo.setMunicipio(municipio);
      iptuVo.setTipoITPU( new DomnTipoIPTU(rs.getInt(CAMPO_TIPO_IPTU)));
      iptuVo.setValorPercentualEstimado(rs.getDouble(CAMPO_VALOR_PERCENTUAL_ESTIMADO));
      iptuVo.setStatusResgistroIPTU(new DomnAtivoInativo(rs.getInt(CAMPO_STATUS_REGISTRO_IPTU)) );
      iptuVo.setValorMetroTerritorial(rs.getDouble(CAMPO_VALOR_METRO_TERRITORIAL));
      iptuVo.setValorMetroPredial(rs.getDouble(CAMPO_VALOR_METRO_PREDIAL));
      iptuVo.setDataAtualizacaoBD(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
   }
   
   private void prepareStatementFindIPTUVo(PreparedStatement ps, IPTUVo iptuVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(iptuVo);
      int contador = 0;
      if (iptuVo.isConsultaParametrizada())
      {
         // CODIGO
         if (Validador.isNumericoValido(iptuVo.getParametroConsulta().getCodigo()))
         {
            ps.setLong(++contador, iptuVo.getParametroConsulta().getCodigo());
         }
         // CODIGO MUNICIPIO
         if (Validador.isNumericoValido(iptuVo.getParametroConsulta().getMunicipio().getCodgMunicipio()))
         {
            ps.setLong(++contador, iptuVo.getParametroConsulta().getMunicipio().getCodgMunicipio());
         }
         //CAMPO_TIPO_IPTU
         if (Validador.isDominioNumericoValido( iptuVo.getParametroConsulta().getTipoITPU()))
         {
            ps.setLong(++contador, iptuVo.getParametroConsulta().getTipoITPU().getValorCorrente());
         }
         //CAMPO_VALOR_PERCENTUAL_ESTIMADO
         if (Validador.isNumericoValido(iptuVo.getParametroConsulta().getValorPercentualEstimado()))
         {
            ps.setDouble(++contador, iptuVo.getParametroConsulta().getValorPercentualEstimado());
         }
         // STATUS REGISTRO
         if (Validador.isDominioNumericoValido( iptuVo.getParametroConsulta().getStatusResgistroIPTU()))
         {
            ps.setLong(++contador, iptuVo.getParametroConsulta().getStatusResgistroIPTU().getValorCorrente());
         }
         //CAMPO_VALOR_METRO_TERRITORIAL
         if (Validador.isNumericoValido(iptuVo.getParametroConsulta().getValorMetroTerritorial()))
         {
            ps.setDouble(++contador, iptuVo.getParametroConsulta().getValorMetroTerritorial());
         }
         //CAMPO_VALOR_METRO_PREDIAL
         if (Validador.isNumericoValido(iptuVo.getParametroConsulta().getValorMetroPredial()))
         {
            ps.setDouble(++contador, iptuVo.getParametroConsulta().getValorMetroPredial());
         }
      }
   }

   private String getSQLFindIPTUVo(IPTUVo iptuVo)
   {
      Validador.isObjetoValido(iptuVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT ");
      sql.append(" IPTU." + CAMPO_CODIGO_IPTU);
      sql.append(", IPTU." + CAMPO_CODIGO_MUNICIPIO);
      sql.append(", IPTU." + CAMPO_TIPO_IPTU);
      sql.append(", IPTU." + CAMPO_VALOR_PERCENTUAL_ESTIMADO);
      sql.append(", IPTU." + CAMPO_STATUS_REGISTRO_IPTU);
      sql.append(", IPTU." + CAMPO_VALOR_METRO_TERRITORIAL);
      sql.append(", IPTU." + CAMPO_VALOR_METRO_PREDIAL);
      sql.append(", IPTU." + CAMPO_DATA_ATUALIZACAO_BD);
      sql.append(" FROM " + TABELA_IPTU + " IPTU ");
      sql.append(" WHERE 1 = 1 ");
      if (iptuVo.isConsultaParametrizada())
      {
         if (Validador.isNumericoValido(iptuVo.getParametroConsulta().getCodigo()))
         {
            sql.append(" AND IPTU." + CAMPO_CODIGO_IPTU + " = ?");
         }
         if (Validador.isNumericoValido(iptuVo.getParametroConsulta().getMunicipio().getCodgMunicipio()))
         {
            sql.append(" AND IPTU." + CAMPO_CODIGO_MUNICIPIO+ " = ?");
         }
         if (Validador.isDominioNumericoValido( iptuVo.getParametroConsulta().getTipoITPU()))
         {
            sql.append(" AND IPTU." + CAMPO_TIPO_IPTU + " = ?");
         }
         if (Validador.isDominioNumericoValido( iptuVo.getParametroConsulta().getStatusResgistroIPTU()))
         {
            sql.append(" AND IPTU." + CAMPO_STATUS_REGISTRO_IPTU + " = ?");
         }
      }
      sql.append(" ORDER BY IPTU." + CAMPO_CODIGO_IPTU + " ");
      System.out.println(sql.toString());
      return sql.toString();
   }
   
}
