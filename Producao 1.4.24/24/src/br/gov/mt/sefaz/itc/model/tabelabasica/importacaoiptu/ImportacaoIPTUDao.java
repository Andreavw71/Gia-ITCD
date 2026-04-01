package br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.dominio.DomnGeracaoServico;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposImportacaoIPTU;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


public class ImportacaoIPTUDao extends AbstractDao implements TabelasITC, SequencesITC, CamposImportacaoIPTU
{
	
	public ImportacaoIPTUDao(Connection conexao)
	{
		super(conexao);
	}
   
   private String getSQLFindImportacao(final ImportacaoIPTUVo importacaoIPTUVo) throws ObjetoObrigatorioException, ClassCastException
      {
         Validador.validaObjeto(importacaoIPTUVo);
         StringBuffer sql = new StringBuffer();
         sql.append(" SELECT ");
         sql.append(" IMPORTACAO." + CAMPO_CODG_IMPORTACAO_SEQC + "");
         sql.append(" , IMPORTACAO." + CAMPO_ACCTB09_CODG_MUNICIPIO + "");
         sql.append(" , IMPORTACAO." + CAMPO_ACSTB11_CODG_USUARIO_SEQ + "");
         sql.append(" , IMPORTACAO." + CAMPO_DESC_NOME_ARQUIVO_ANTIGO + "");
         sql.append(" , IMPORTACAO." + CAMPO_STAT_IMPORTACAO + "");
         sql.append(" , IMPORTACAO." + CAMPO_DATA_HORA_UPLOAD + "");
         sql.append(" , IMPORTACAO." + CAMPO_DATA_HORA_PROCESSAMENTO + ""); 
         sql.append(" , IMPORTACAO." + CAMPO_ARQV_IPTU + "");
         sql.append(" , IMPORTACAO." + CAMPO_DESC_NOME_ARQUIVO_NOVO + "");
        
         sql.append(" FROM " + TABELA_IMPORTACAO_IPTU + " IMPORTACAO ");
         sql.append(" WHERE 1 = 1 ");
         if (importacaoIPTUVo.isConsultaParametrizada())
         {
            if (Validador.isNumericoValido(importacaoIPTUVo.getParametroConsulta().getCodigo()))
            {
               sql.append(" AND IMPORTACAO." + CAMPO_CODG_IMPORTACAO_SEQC + " = ? ");
            }
            if (Validador.isNumericoValido(importacaoIPTUVo.getParametroConsulta().getMunicipioVo().getCodgMunicipio()))
            {
               sql.append(" AND IMPORTACAO." + CAMPO_ACCTB09_CODG_MUNICIPIO + " = ? ");
            }
            if (Validador.isNumericoValido(importacaoIPTUVo.getParametroConsulta().getCodgUsuarioSeq()))
            {
               sql.append(" AND IMPORTACAO." + CAMPO_ACSTB11_CODG_USUARIO_SEQ + " = ? ");
            }
            if (Validador.isStringValida(importacaoIPTUVo.getParametroConsulta().getDescNomeArquivoAntigo()))
            {
               sql.append(" AND IMPORTACAO." + CAMPO_DESC_NOME_ARQUIVO_ANTIGO + " = ? ");
            }
            if (Validador.isDominioNumericoValido(importacaoIPTUVo.getParametroConsulta().getStatusImportacao()))
            {
               sql.append(" AND IMPORTACAO." + CAMPO_STAT_IMPORTACAO + " = ? ");
            }
            if (Validador.isDataValida(importacaoIPTUVo.getParametroConsulta().getDataHoraUpload()))
            {
               sql.append(" AND IMPORTACAO." + CAMPO_DATA_HORA_UPLOAD + " = ? ");
            }
            if (Validador.isDataValida(importacaoIPTUVo.getParametroConsulta().getDataHoraUpload()))
            {
               sql.append(" AND IMPORTACAO." + CAMPO_DATA_HORA_UPLOAD + " = ? ");
            }
            if (Validador.isDataValida(importacaoIPTUVo.getParametroConsulta().getDataHoraProcessamento()))
            {
               sql.append(" AND IMPORTACAO." + CAMPO_DATA_HORA_UPLOAD + " = ? ");
            }            
           
         }
         sql.append(" ORDER BY IMPORTACAO." + CAMPO_CODG_IMPORTACAO_SEQC + " ");
         return sql.toString();
      }
      
   public ImportacaoIPTUVo listImportacao(final ImportacaoIPTUVo importacaoIPTUVo) throws ConsultaException, ObjetoObrigatorioException, IOException, 
             Exception
   {
      Validador.validaObjeto(importacaoIPTUVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindImportacao(importacaoIPTUVo));
         prepareStatementListarImportacao(ps, importacaoIPTUVo);
         Collection listaImportacao = new ArrayList();
         rs = ps.executeQuery();
         while(rs.next())
         {
            ImportacaoIPTUVo importacaoIPTUVoAtual = new ImportacaoIPTUVo();
            getImportacaoIPTU(rs, importacaoIPTUVoAtual);
            listaImportacao.add(importacaoIPTUVoAtual);
         }
         if (Validador.isCollectionValida(listaImportacao))
         {
            importacaoIPTUVo.setCollVO(listaImportacao);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.LISTAR_CULTURA);
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
      return importacaoIPTUVo;
   }
   
   public ImportacaoIPTUVo consultarImportacao(final ImportacaoIPTUVo importacaoIPTUVo) throws ConsultaException, ObjetoObrigatorioException, IOException, 
             Exception
   {
      Validador.validaObjeto(importacaoIPTUVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindImportacao(importacaoIPTUVo));
         prepareStatementListarImportacao(ps, importacaoIPTUVo);        
         rs = ps.executeQuery();
         if (rs.next())
         {
            getImportacaoIPTU(rs, importacaoIPTUVo);
         }

      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.LISTAR_CULTURA);
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
      return importacaoIPTUVo;
   }
   
   private void prepareStatementListarImportacao(final PreparedStatement ps, final ImportacaoIPTUVo importacaoIPTUVo) throws ObjetoObrigatorioException, 
           SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(importacaoIPTUVo);
      int contador = 0;
      if (importacaoIPTUVo.isConsultaParametrizada())
      {
         // CODIGO
         if (Validador.isNumericoValido(importacaoIPTUVo.getParametroConsulta().getCodigo()))
         {
            ps.setLong(++contador, (importacaoIPTUVo.getParametroConsulta()).getCodigo());
         }
         // CODIGO DO MUNICIPIO
         if (Validador.isNumericoValido(importacaoIPTUVo.getMunicipioVo().getCodgMunicipio()))
         {
            ps.setLong(++contador, (importacaoIPTUVo.getMunicipioVo().getCodgMunicipio()));
         }
         // STATUS IMPORTAÇÃO
         if (Validador.isDominioNumericoValido(importacaoIPTUVo.getParametroConsulta().getStatusImportacao()))
         {
            ps.setInt(++contador, (importacaoIPTUVo.getParametroConsulta()).getStatusImportacao().getValorCorrente());
         }
      }
   }
   
   private void getImportacaoIPTU(final ResultSet rs, final ImportacaoIPTUVo importacaoIPTUVo) throws ObjetoObrigatorioException, 
           SQLException, IOException, Exception
   {
      Blob blob = null;
      Validador.validaObjeto(rs);
      Validador.validaObjeto(importacaoIPTUVo);
      
      importacaoIPTUVo.setCodigo(rs.getInt(CAMPO_CODG_IMPORTACAO_SEQC));
      importacaoIPTUVo.getMunicipioVo().setCodgMunicipio(rs.getInt(CAMPO_ACCTB09_CODG_MUNICIPIO));
      importacaoIPTUVo.setCodgUsuarioSeq(rs.getLong(CAMPO_ACSTB11_CODG_USUARIO_SEQ));
      importacaoIPTUVo.setDescNomeArquivoAntigo(rs.getString(CAMPO_DESC_NOME_ARQUIVO_ANTIGO));
      importacaoIPTUVo.setStatusImportacao(new DomnGeracaoServico(rs.getInt(CAMPO_STAT_IMPORTACAO))); 
      importacaoIPTUVo.setDataHoraUpload(rs.getDate(CAMPO_DATA_HORA_UPLOAD));
      importacaoIPTUVo.setDataHoraProcessamento(rs.getDate(CAMPO_DATA_HORA_PROCESSAMENTO));
      importacaoIPTUVo.setDescNomeArquivoNovo(rs.getString(CAMPO_DESC_NOME_ARQUIVO_NOVO));
      importacaoIPTUVo.setArqvIptu(converteCampoBlob(rs.getBlob(CAMPO_ARQV_IPTU)));      
      
      }   
   
   private byte[] converteCampoBlob(Blob blob)
      throws Exception
   {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      if (blob != null)
      {
         try
         {
            InputStream in = blob.getBinaryStream();
            int b = -1;
            while ((b = in.read()) != -1)
            {
               baos.write(b);
            }
            in.close();
            baos.close();
         }
         catch (Exception e)
         {
            throw e;
         }
      }
      return baos.toByteArray();
   }
}


