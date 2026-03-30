package br.gov.mt.sefaz.itc.model.tabelabasica.iptuprefeitura;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptu.IPTUVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.externas.CamposIPTUPrefeitura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

import sefaz.mt.util.UtilStmt;


public class IPTUPrefeituraDao extends AbstractDao implements TabelasITC, SequencesITC, CamposIPTUPrefeitura
{
	
	public IPTUPrefeituraDao(Connection conexao)
	{
	   super(conexao);
	   utilStmt = new UtilStmt(TABELA_IPTU_PREFEITURA);
	}
   
   private String getSQLFindIptuPrefeitura(final IPTUPrefeituraVo iptuPrefeituraVo) throws ObjetoObrigatorioException, ClassCastException
      {
         Validador.validaObjeto(iptuPrefeituraVo);
         StringBuffer sql = new StringBuffer();
         sql.append(" SELECT ");
         sql.append("IPTU_PREFEITURA." +CAMPO_CODG_IPTU_PREFEITURA_SEQC+"");
         sql.append(" , IPTU_PREFEITURA." +CAMPO_ACCTB09_CODG_MUNICIPIO+""); 
         sql.append(" , IPTU_PREFEITURA." +CAMPO_NUMR_INSCRICAO_IMOVEL +"");
         sql.append(" , IPTU_PREFEITURA." +CAMPO_DESC_STAT_IMOVEL+"");
         sql.append(" , IPTU_PREFEITURA." +CAMPO_VALR_VENAL+"");
         sql.append(" , IPTU_PREFEITURA." +CAMPO_QTDE_AREA_TOTAL +"");
         sql.append(" , IPTU_PREFEITURA." +CAMPO_QTDE_AREA_CONSTRUIDA +"");
         sql.append(" , IPTU_PREFEITURA." +CAMPO_NOME_CONTRIBUINTE+"");
         sql.append(" , IPTU_PREFEITURA." +CAMPO_NUMR_DOCUMENTO+"");
         sql.append(" , IPTU_PREFEITURA." +CAMPO_DATA_ATUALIZACAO_BD+"");
         sql.append(" , IPTU_PREFEITURA." +CAMPO_ITCTB56_CODG_IMPORTACAO_SEQC+"");       
         sql.append(" , IPTU_PREFEITURA." +CAMPO_STAT_IPTU_PREFEITURA+"");

        
         sql.append(" FROM " + TABELA_IPTU_PREFEITURA + "  IPTU_PREFEITURA ");
         sql.append(" WHERE 1 = 1 ");
         if (iptuPrefeituraVo.isConsultaParametrizada())
         {
            if (Validador.isNumericoValido(iptuPrefeituraVo.getParametroConsulta().getCodigo()))
            {
               sql.append(" AND IPTU_PREFEITURA." + CAMPO_CODG_IPTU_PREFEITURA_SEQC + " = ? ");
            }
            if (Validador.isNumericoValido(iptuPrefeituraVo.getParametroConsulta().getMunicipioVo().getCodgMunicipio()))
            {
               sql.append(" AND  IPTU_PREFEITURA." + CAMPO_ACCTB09_CODG_MUNICIPIO + " = ? ");
            }
            if (Validador.isStringValida(iptuPrefeituraVo.getParametroConsulta().getNumrInscricaoImovel()))
            {
               sql.append(" AND  IPTU_PREFEITURA." + CAMPO_NUMR_INSCRICAO_IMOVEL + " = ? ");
            }
            if (Validador.isStringValida(iptuPrefeituraVo.getParametroConsulta().getStatImovel()))
            {
               sql.append(" AND  IPTU_PREFEITURA." +CAMPO_DESC_STAT_IMOVEL + " = ? ");
            }
            if (Validador.isNumericoValido(iptuPrefeituraVo.getParametroConsulta().getValrVenal()))
            {
               sql.append(" AND  IPTU_PREFEITURA." + CAMPO_VALR_VENAL + " = ? ");
            }
            if (Validador.isNumericoValido(iptuPrefeituraVo.getParametroConsulta().getQtdeAreaTotal()))
            {
               sql.append(" AND  IPTU_PREFEITURA." + CAMPO_QTDE_AREA_TOTAL + " = ? ");
            }
            if (Validador.isNumericoValido(iptuPrefeituraVo.getParametroConsulta().getQtdeAreaConstruida()))
            {
               sql.append(" AND  IPTU_PREFEITURA." + CAMPO_QTDE_AREA_CONSTRUIDA + " = ? ");
            }
            if (Validador.isStringValida(iptuPrefeituraVo.getParametroConsulta().getNomeContribuinte()))
            {
               sql.append(" AND  IPTU_PREFEITURA." + CAMPO_NOME_CONTRIBUINTE + " = ? ");
            }  
            if (Validador.isNumericoValido(iptuPrefeituraVo.getParametroConsulta().getNumrDocumento()))
            {
               sql.append(" AND  IPTU_PREFEITURA." + CAMPO_NUMR_DOCUMENTO + " = ? ");
            } 
            if (Validador.isDataValida(iptuPrefeituraVo.getParametroConsulta().getDataAtualizacaoBd()))
            {
               sql.append(" AND  IPTU_PREFEITURA." + CAMPO_DATA_ATUALIZACAO_BD + " = ? ");
            } 
            if (Validador.isNumericoValido(iptuPrefeituraVo.getParametroConsulta().getImportacaoIPTUVo().getCodigo()))
            {
               sql.append(" AND  IPTU_PREFEITURA." + CAMPO_ITCTB56_CODG_IMPORTACAO_SEQC + " = ? ");
            }
            if (Validador.isDominioNumericoValido(iptuPrefeituraVo.getParametroConsulta().getStatIPTUprefeitura()))
            {
               sql.append(" AND  IPTU_PREFEITURA." + CAMPO_STAT_IPTU_PREFEITURA + " = ? ");
            }
           
         }
         sql.append(" ORDER BY IPTU_PREFEITURA." + CAMPO_CODG_IPTU_PREFEITURA_SEQC + " ");
         return sql.toString();
      }
      
   public IPTUPrefeituraVo listIptuPrefeitura(final IPTUPrefeituraVo iptuPrefeituraVo) throws ConsultaException, ObjetoObrigatorioException, 
             Exception
   {
      Validador.validaObjeto(iptuPrefeituraVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindIptuPrefeitura(iptuPrefeituraVo));
         prepareStatementListarImportacao(ps, iptuPrefeituraVo);
         Collection listaIptuPrefeitura = new ArrayList();
         rs = ps.executeQuery();
         while(rs.next())
         {
            IPTUPrefeituraVo iptuPrefeituraVoAtual = new IPTUPrefeituraVo();
            getIPTUPrefeitura(rs, iptuPrefeituraVoAtual);
            listaIptuPrefeitura.add(iptuPrefeituraVoAtual);
         }
         if (Validador.isCollectionValida(listaIptuPrefeitura))
         {
            iptuPrefeituraVo.setCollVO(listaIptuPrefeitura);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.LISTAR_PREFEITURA);
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
      return iptuPrefeituraVo;
   }
   
   public IPTUPrefeituraVo finIptuPrefeitura(IPTUPrefeituraVo iptuPrefeituraVo) throws ObjetoObrigatorioException, 
         ConsultaException
   {
      Validador.isObjetoValido(iptuPrefeituraVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindIptuPrefeitura(iptuPrefeituraVo));
         prepareStatementListarImportacao(ps, iptuPrefeituraVo);
         rs = ps.executeQuery();
         if (rs.next())
         {
            getIPTUPrefeitura(rs, iptuPrefeituraVo);
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
      return iptuPrefeituraVo;
   }
   
   private void prepareStatementListarImportacao(final PreparedStatement ps, final IPTUPrefeituraVo iptuPrefeituraVo) throws ObjetoObrigatorioException, 
           SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(iptuPrefeituraVo);
      int contador = 0;
      if (iptuPrefeituraVo.isConsultaParametrizada())
      {
         // CODIGO
         if (Validador.isNumericoValido(iptuPrefeituraVo.getParametroConsulta().getCodigo()))
         {
            ps.setLong(++contador, (iptuPrefeituraVo.getParametroConsulta()).getCodigo());
         }        
       
         if (Validador.isStringValida(iptuPrefeituraVo.getParametroConsulta().getNumrInscricaoImovel()))
         {
            ps.setString(++contador, (iptuPrefeituraVo.getParametroConsulta().getNumrInscricaoImovel()));
         }
         if (Validador.isNumericoValido(iptuPrefeituraVo.getParametroConsulta().getImportacaoIPTUVo().getCodigo()))
         {
            ps.setLong(++contador, (iptuPrefeituraVo.getParametroConsulta().getImportacaoIPTUVo().getCodigo()));
         }
         if (Validador.isDominioNumericoValido(iptuPrefeituraVo.getParametroConsulta().getStatIPTUprefeitura()))
         {
            ps.setInt(++contador, (iptuPrefeituraVo.getParametroConsulta().getStatIPTUprefeitura().getValorCorrente()));
         }
      }
   }
   
   private void getIPTUPrefeitura(final ResultSet rs, final IPTUPrefeituraVo iptuPrefeituraVo) throws ObjetoObrigatorioException, 
           SQLException
   {

      Validador.validaObjeto(rs);
      Validador.validaObjeto(iptuPrefeituraVo);
      iptuPrefeituraVo.setCodigo(rs.getLong(CAMPO_CODG_IPTU_PREFEITURA_SEQC));
      iptuPrefeituraVo.getMunicipioVo().setCodgMunicipio(rs.getInt(CAMPO_ACCTB09_CODG_MUNICIPIO));
      iptuPrefeituraVo.setNumrInscricaoImovel(rs.getString(CAMPO_NUMR_INSCRICAO_IMOVEL));
      iptuPrefeituraVo.setStatImovel(rs.getString(CAMPO_DESC_STAT_IMOVEL));
      iptuPrefeituraVo.setValrVenal(rs.getDouble(CAMPO_VALR_VENAL));
      iptuPrefeituraVo.setQtdeAreaTotal(rs.getDouble(CAMPO_QTDE_AREA_TOTAL));
      iptuPrefeituraVo.setQtdeAreaConstruida(rs.getDouble(CAMPO_QTDE_AREA_CONSTRUIDA));
      iptuPrefeituraVo.setNomeContribuinte(rs.getString(CAMPO_NOME_CONTRIBUINTE));
      iptuPrefeituraVo.setNumrDocumento(rs.getLong(CAMPO_NUMR_DOCUMENTO));
      iptuPrefeituraVo.setDataAtualizacaoBd(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
      iptuPrefeituraVo.getImportacaoIPTUVo().setCodigo(rs.getInt(CAMPO_ITCTB56_CODG_IMPORTACAO_SEQC));      
      iptuPrefeituraVo.setStatIPTUprefeitura(new DomnAtivoInativo(rs.getInt(CAMPO_STAT_IPTU_PREFEITURA)));
      

   }
}
