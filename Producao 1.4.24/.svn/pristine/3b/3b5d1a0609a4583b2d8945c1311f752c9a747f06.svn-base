package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposDoacaoSucessiva;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import br.gov.mt.sefaz.itc.util.sql.PreparedStatementUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.Date;


public class GIAITCDDoacaoSucessivaQDao extends AbstractDao implements TabelasITC, CamposDoacaoSucessiva, SequencesITC
{ 
   public GIAITCDDoacaoSucessivaQDao(Connection conexao)
   {
      super(conexao);
   }
   
   private String[] getCampos()
      {
      return new String[] 
      { 
         CAMPO_ITCTB05_CODG_BENEFICIARIO,
         CAMPO_ACCTB01_NUMR_PESS_BENF,
         CAMPO_ITCTB14_CODG_ITCD_ANTR,
         CAMPO_DATA_GIA_ITCD_ANTR,
         CAMPO_VALR_ITCD_BENF_GIA_ANTR,
         CAMPO_VALR_BASE_CALC_BENF_GIA_ANTR,
         CAMPO_NUMR_PROT_GIA_ANTR,
         CAMPO_DATA_PROT_GIA_ANTR 
      };
      
   }
   
   public GIAITCDDoacaoSucessivaVo findAllGIAITCDDoacaoSucessivaVo(final BeneficiarioVo beneficiarioVo, GIAITCDDoacaoSucessivaVo giaitcdDoacaoSucessivaVo)
      throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.validaObjeto(beneficiarioVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      try
      {
         
         Calendar cal = Calendar.getInstance();
         if(beneficiarioVo.getGiaITCDVo().getDataCriacao() == null){
            cal.setTime(new Date());
         }else{
            cal.setTime(beneficiarioVo.getGiaITCDVo().getDataCriacao());
         }
         cal.set(Calendar.MONTH, Calendar.JANUARY);
         cal.set(Calendar.DAY_OF_MONTH, 1);
         cal.set(Calendar.HOUR_OF_DAY, 0);
         cal.set(Calendar.MINUTE, 0);
         cal.set(Calendar.SECOND, 0);
         cal.set(Calendar.MILLISECOND, 0);   
         Date inicioAno = cal.getTime();         
         
         ps = conn.prepareStatement(getSQLBaseConsultaExterna(beneficiarioVo));
         ps.setTimestamp(1, new Timestamp(inicioAno.getTime()));
         ps.setTimestamp(2, new Timestamp(inicioAno.getTime()));
         
         rs = ps.executeQuery();
         while (rs.next())
         {
            GIAITCDDoacaoSucessivaVo doacaoSucessivaVoTemporarioAtualVo = new GIAITCDDoacaoSucessivaVo();
            doacaoSucessivaVoTemporarioAtualVo.setBeneficiarioVo(beneficiarioVo);
            getGIAITCDDoacaoSucessivaVo(rs, doacaoSucessivaVoTemporarioAtualVo, false);
            giaitcdDoacaoSucessivaVo.getListVo().add(doacaoSucessivaVoTemporarioAtualVo);
         }
      }
      catch (SQLException e)
      {

         ExibirLOG.exibirLogSimplificado("Erro - SQL ao consultar Doações Sucessivas externas: " + e.getMessage());
         throw new ConsultaException("Erro ao consultar Doações Sucessivas externas" + 
                                     e.getMessage());
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
            ExibirLOG.exibirLogSimplificado("Erro - SQL: " + e.getMessage());
         }
      }
      return giaitcdDoacaoSucessivaVo;
   }
   
   public GIAITCDDoacaoSucessivaVo findAllGIAITCDDoacaoSucessivaVoNaoUtilizadaParaCalculo(final BeneficiarioVo beneficiarioVo, GIAITCDDoacaoSucessivaVo giaitcdDoacaoSucessivaVo)
      throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.validaObjeto(beneficiarioVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      try
      {
         Calendar cal = Calendar.getInstance();
         if(beneficiarioVo.getGiaITCDVo().getDataCriacao() == null){
            cal.setTime(new Date());
         }else{
            cal.setTime(beneficiarioVo.getGiaITCDVo().getDataCriacao());
         }         
         cal.set(Calendar.MONTH, Calendar.JANUARY);
         cal.set(Calendar.DAY_OF_MONTH, 1);
         cal.set(Calendar.HOUR_OF_DAY, 0);
         cal.set(Calendar.MINUTE, 0);
         cal.set(Calendar.SECOND, 0);
         cal.set(Calendar.MILLISECOND, 0);   
         Date inicioAno = cal.getTime();   
      
         ps = conn.prepareStatement(getSQLBaseConsultaExternaNaoUtilizadaParaCalculo(beneficiarioVo));
         ps.setTimestamp(1, new Timestamp(inicioAno.getTime()));
         ps.setTimestamp(2, new Timestamp(inicioAno.getTime()));
         
         rs = ps.executeQuery();
         while (rs.next())
         {
            GIAITCDDoacaoSucessivaVo doacaoSucessivaVoTemporarioAtualVo = new GIAITCDDoacaoSucessivaVo();
            doacaoSucessivaVoTemporarioAtualVo.setBeneficiarioVo(beneficiarioVo);
            getGIAITCDDoacaoSucessivaVo(rs, doacaoSucessivaVoTemporarioAtualVo, false);
            giaitcdDoacaoSucessivaVo.getListVo().add(doacaoSucessivaVoTemporarioAtualVo);
         }
      }
      catch (SQLException e)
      {

         ExibirLOG.exibirLogSimplificado("Erro - SQL ao consultar Doações Sucessivas externas: " + e.getMessage());
         throw new ConsultaException("Erro ao consultar Doações Sucessivas externas" + 
                                     e.getMessage());
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
            ExibirLOG.exibirLogSimplificado("Erro - SQL: " + e.getMessage());
         }
      }
      return giaitcdDoacaoSucessivaVo;
   }
   
   
   
   private String getSQLBaseConsultaExterna(final BeneficiarioVo beneficiarioVo)
   { 
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT ")
         .append("    benef.ACCTB01_NUMR_PESS_BENEF AS ACCTB01_NUMR_PESS_BENF, ")
         .append("    benefdoacao.ITCTB05_CODG_BENEFICIARIO AS ITCTB05_CODG_BENEFICIARIO, ")
         .append("    benef.ITCTB14_CODG_ITCD AS ITCTB14_CODG_ITCD_GIA_ANTR, ")
         .append("    itcd.DATA_CRIACAO AS DATA_GIA_ITCD_ANTR, ")
         .append("    benefdoacao.VALR_RECOLHER AS VALR_TTCD_BENF_GIA_ANTR, ")
         .append("    benef.VALR_RECEBIDO AS VALR_BASE_CALC_BENF_GIA_ANTR, ")
         .append("    (")
         .append("        SELECT ")
         .append("            status.NUMR_PROTOCOLO || '/' || TO_CHAR(status.DATA_PROTOCOLO, 'YYYY') ")
         .append("        FROM ")
         .append("            ITC.ITCTB27_STATUS_ITCD status ")
         .append("        WHERE ")
         .append("            status.ITCTB14_CODG_ITCD = benef.ITCTB14_CODG_ITCD ")
         .append("            AND status.STAT_ITCD = 2 ")
         .append("    ) AS NUMR_PROT_GIA_ANTR, ")         
         .append("    (")
         .append("        SELECT status.DATA_ATUALIZACAO_BD ")
         .append("        FROM ")
         .append("            ITC.ITCTB27_STATUS_ITCD status ")
         .append("        WHERE ")
         .append("            status.ITCTB14_CODG_ITCD = benef.ITCTB14_CODG_ITCD ")
         .append("            AND status.STAT_ITCD = 2 ")
         .append("    ) AS DATA_PROT_GIA_ANTR, ")         
         .append("     TO_CHAR(itcd.DATA_CRIACAO, 'MONTH') AS MES_GIAITCD, ")
         .append("     benef.INFO_DOACAO_SUCESSIVA AS DOACAO_SUCESSIVA_ANTERIOR ")
         .append("FROM ")
         .append("    ITC.ITCTB05_BENEFICIARIO benef ")
         .append("    INNER JOIN ITC.ITCTB41_BENEF_DOACAO benefdoacao ")
         .append("        ON benefdoacao.ITCTB05_CODG_BENEFICIARIO = benef.CODG_BENEFICIARIO ")
         .append("    INNER JOIN ITC.ITCTB14_ITCD itcd ")
         .append("        ON itcd.CODG_ITCD = benef.ITCTB14_CODG_ITCD ")
         .append("    INNER JOIN ( ")
         .append("      SELECT * FROM ( ")
         .append("        SELECT ")
         .append("            ITCTB14_CODG_ITCD, ")
         .append("            STAT_ITCD, ")
         .append("            DATA_ATUALIZACAO_BD, ")
         .append("            ROW_NUMBER() OVER ( ")
         .append("                PARTITION BY ITCTB14_CODG_ITCD ")
         .append("                ORDER BY DATA_ATUALIZACAO_BD DESC ")
         .append("            ) AS rn ")
         .append("        FROM ITC.ITCTB27_STATUS_ITCD ")
         .append("    ) status ")
         .append("    WHERE status.rn = 1 ")
         .append("      AND status.STAT_ITCD NOT IN (14, 19) ")
         .append(") status ON status.ITCTB14_CODG_ITCD = itcd.CODG_ITCD ")
         .append("WHERE ")
         .append("    benef.ACCTB01_NUMR_PESS_BENEF = ").append(beneficiarioVo.getPessoaBeneficiaria().getNumrContribuinte())
         .append("    AND itcd.CODG_ITCD != ").append(beneficiarioVo.getGiaITCDVo().getCodigo())
         .append("    AND itcd.ACCTB01_NUMR_PESS_RSPV = ").append(beneficiarioVo.getGiaITCDVo().getResponsavelVo().getNumrContribuinte())
         .append("    AND itcd.DATA_CRIACAO >= ? ")
         .append("    AND itcd.DATA_CRIACAO < ADD_MONTHS(?, 12) ")      
         .append("    ORDER BY itcd.CODG_ITCD ASC");         
      return sql.toString();
   }
   
   private String getSQLBaseConsultaExternaNaoUtilizadaParaCalculo(final BeneficiarioVo beneficiarioVo)
   { 
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT ")
         .append("    benef.ACCTB01_NUMR_PESS_BENEF AS ACCTB01_NUMR_PESS_BENF, ")
         .append("    benef.CODG_BENEFICIARIO AS ITCTB05_CODG_BENEFICIARIO, ")
         .append("    benef.ITCTB14_CODG_ITCD AS ITCTB14_CODG_ITCD_GIA_ANTR, ")
         .append("    itcd.DATA_CRIACAO AS DATA_GIA_ITCD_ANTR, ")
         .append("    benefdoacao.VALR_RECOLHER AS VALR_TTCD_BENF_GIA_ANTR, ")
         .append("    benef.VALR_RECEBIDO AS VALR_BASE_CALC_BENF_GIA_ANTR, ")
         .append("    (")
         .append("        SELECT ")
         .append("            status.NUMR_PROTOCOLO || '/' || TO_CHAR(status.DATA_PROTOCOLO, 'YYYY') ")
         .append("        FROM ")
         .append("            ITC.ITCTB27_STATUS_ITCD status ")
         .append("        WHERE ")
         .append("            status.ITCTB14_CODG_ITCD = benef.ITCTB14_CODG_ITCD ")
         .append("            AND status.STAT_ITCD = 2 ")
         .append("    ) AS NUMR_PROT_GIA_ANTR, ")         
         .append("    (")
         .append("        SELECT status.DATA_ATUALIZACAO_BD ")
         .append("        FROM ")
         .append("            ITC.ITCTB27_STATUS_ITCD status ")
         .append("        WHERE ")
         .append("            status.ITCTB14_CODG_ITCD = benef.ITCTB14_CODG_ITCD ")
         .append("            AND status.STAT_ITCD = 2 ")
         .append("    ) AS DATA_PROT_GIA_ANTR, ")         
         .append("     TO_CHAR(itcd.DATA_CRIACAO, 'MONTH') AS MES_GIAITCD, ")
         .append("     benef.INFO_DOACAO_SUCESSIVA AS DOACAO_SUCESSIVA_ANTERIOR ")
         .append("FROM ")
         .append("    ITC.ITCTB05_BENEFICIARIO benef ")
         .append("    INNER JOIN ITC.ITCTB41_BENEF_DOACAO benefdoacao ")
         .append("        ON benefdoacao.ITCTB05_CODG_BENEFICIARIO = benef.CODG_BENEFICIARIO ")
         .append("    INNER JOIN ITC.ITCTB14_ITCD itcd ")
         .append("        ON itcd.CODG_ITCD = benef.ITCTB14_CODG_ITCD ")
         .append("    INNER JOIN ( ")
         .append("      SELECT * FROM ( ")
         .append("        SELECT ")
         .append("            ITCTB14_CODG_ITCD, ")
         .append("            STAT_ITCD, ")
         .append("            DATA_ATUALIZACAO_BD, ")
         .append("            ROW_NUMBER() OVER ( ")
         .append("                PARTITION BY ITCTB14_CODG_ITCD ")
         .append("                ORDER BY DATA_ATUALIZACAO_BD DESC ")
         .append("            ) AS rn ")
         .append("        FROM ITC.ITCTB27_STATUS_ITCD ")
         .append("    ) status ")
         .append("    WHERE status.rn = 1 ")
         .append("      AND status.STAT_ITCD NOT IN (14, 19) ")
         .append(") status ON status.ITCTB14_CODG_ITCD = itcd.CODG_ITCD ")
         .append("WHERE ")
         .append("    benef.ACCTB01_NUMR_PESS_BENEF = ").append(beneficiarioVo.getPessoaBeneficiaria().getNumrContribuinte()).append(" ")
         .append("    AND itcd.CODG_ITCD != ").append(beneficiarioVo.getGiaITCDVo().getCodigo())
         .append("    AND itcd.ACCTB01_NUMR_PESS_RSPV = ").append(beneficiarioVo.getGiaITCDVo().getResponsavelVo().getNumrContribuinte()).append(" ")
         .append("    AND itcd.DATA_CRIACAO >= ? ")
         .append("    AND itcd.DATA_CRIACAO < ADD_MONTHS(?, 12) ") 
         .append("    AND (SELECT COUNT(*) ")
         .append("         FROM itc.itctb68_benf_gia_doacao_suce s ")
         .append("         WHERE s.itctb14_codg_itcd_gia_antr = benef.ITCTB14_CODG_ITCD ")
         .append("           AND s.acctb01_numr_pess_benf = ").append(beneficiarioVo.getPessoaBeneficiaria().getNumrContribuinte()).append(") = 0 ")
         .append("ORDER BY itcd.CODG_ITCD ASC");        
      return sql.toString();
   }
   
   private GIAITCDDoacaoSucessivaVo getGIAITCDDoacaoSucessivaVo(final ResultSet rs, final GIAITCDDoacaoSucessivaVo giaitcdDoacaoSucessivaVo, Boolean giaItcdPermanente)
      throws ObjetoObrigatorioException, SQLException, ConsultaException
   {
      Validador.validaObjeto(rs);
      Validador.validaObjeto(giaitcdDoacaoSucessivaVo);
      
      giaitcdDoacaoSucessivaVo.getPessoaBeneficiaria().setNumrContribuinte(rs.getLong(CAMPO_ACCTB01_NUMR_PESS_BENF));
      giaitcdDoacaoSucessivaVo.getGiaITCDVo().setCodigo(rs.getLong(CAMPO_ITCTB14_CODG_ITCD_ANTR));
      giaitcdDoacaoSucessivaVo.setDataGIAITCDAnterior(rs.getDate(CAMPO_DATA_GIA_ITCD_ANTR));  
      giaitcdDoacaoSucessivaVo.setValorITCD(getValorBeneficiarioPorCodigoGiaITCD(giaitcdDoacaoSucessivaVo.getGiaITCDVo().getCodigo(), giaitcdDoacaoSucessivaVo.getPessoaBeneficiaria().getNumrContribuinte()));
      giaitcdDoacaoSucessivaVo.setValorBaseDeCalc(rs.getDouble(CAMPO_VALR_BASE_CALC_BENF_GIA_ANTR));
      giaitcdDoacaoSucessivaVo.setNumeroEprocess(rs.getString(CAMPO_NUMR_PROT_GIA_ANTR));
      giaitcdDoacaoSucessivaVo.setDataEProcess(new Date(rs.getTimestamp(CAMPO_DATA_PROT_GIA_ANTR).getTime()));
      giaitcdDoacaoSucessivaVo.setMesGIAITCDAnterior(rs.getString(CAMPO_SEM_TB_MES_GIA_ITCD_ANTR));
      giaitcdDoacaoSucessivaVo.setFlagDoacaoSucessivaAnterior(PreparedStatementUtils.getDomnSimNaoOrNull(rs,CAMPO_SEM_TB_DOACAO_SUCESSIVA_ANTERIOR));
      giaitcdDoacaoSucessivaVo.setValorITCDBeneficiario(giaitcdDoacaoSucessivaVo.getValorITCD());  
      giaitcdDoacaoSucessivaVo.setIsIsentoUpf(verificaIsencaoUpfGiaDoacaoSucessiva(rs.getLong(CAMPO_ITCTB14_CODG_ITCD_ANTR)));      
      giaitcdDoacaoSucessivaVo.setValorBaseDeCalc(rs.getDouble(CAMPO_VALR_BASE_CALC_BENF_GIA_ANTR));
      giaitcdDoacaoSucessivaVo.getBeneficiarioVo().setCodigo(rs.getLong(CAMPO_ITCTB05_CODG_BENEFICIARIO));
      return giaitcdDoacaoSucessivaVo;
   }
   
   public GIAITCDDoacaoSucessivaVo listaDoacoesSucessivas(final GIAITCDDoacaoSucessivaVo doacaoSucessivaVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(doacaoSucessivaVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      try
      {
         ps = conn.prepareStatement(getSQLFindDoacaoSucessiva(doacaoSucessivaVo));
         rs = ps.executeQuery();
         while (rs.next())
         {
            GIAITCDDoacaoSucessivaVo doacaoSucessivaAtualVo = new GIAITCDDoacaoSucessivaVo();
            getGIAITCDDoacaoSucessivaVo(rs, doacaoSucessivaAtualVo, true);
            doacaoSucessivaVo.getCollVO().add(doacaoSucessivaAtualVo);
         }      
         
      }
      catch (SQLException e)
      {
         ExibirLOG.exibirLogSimplificado("Erro - SQL: " + e.getMessage());
         throw new ConsultaException(MensagemErro.CONSULTAR_BENEFICIARIO);
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
            ExibirLOG.exibirLogSimplificado("Erro - SQL ao Listar Doação Sucessivas internas da tabela ITCTB68: " + e.getMessage());
         }
      }
      return doacaoSucessivaVo;
   }
   
   private String getSQLFindDoacaoSucessiva(final GIAITCDDoacaoSucessivaVo doacaoSucessivaVo) throws ObjetoObrigatorioException, 
           ClassCastException
   {
      Validador.validaObjeto(doacaoSucessivaVo);
      
      StringBuilder campos = new StringBuilder();
       String[] camposArray = getCampos();
 
      for (int i = 0; i < camposArray.length; i++) {
          if (camposArray[i].equals(CAMPO_NUMR_PROT_GIA_ANTR)) {
              campos.append(camposArray[i] + " || '/' || TO_CHAR(" + 
                           CAMPO_DATA_PROT_GIA_ANTR + ",'YYYY') AS " + 
                           CAMPO_NUMR_PROT_GIA_ANTR);
          } else {
              campos.append(camposArray[i]);
          }
          
          if (i < camposArray.length - 1) {
              campos.append(",");
          }
      }     
      
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT " + campos.toString());
      sql.append(" , TO_CHAR(" + CAMPO_DATA_GIA_ITCD_ANTR + ", 'MONTH') AS MES_GIAITCD,");
      sql.append(" 2 AS " + CAMPO_SEM_TB_DOACAO_SUCESSIVA_ANTERIOR); 
      
      sql.append(" FROM ITC." + TABELA_GIA_ITCD_DOACAO_SUCESSIVA);
      sql.append(" WHERE ITCTB05_CODG_BENEFICIARIO = " + doacaoSucessivaVo.getBeneficiarioVo().getCodigo());
      sql.append(" AND ITC.ITCTB68_BENF_GIA_DOACAO_SUCE.ITCTB14_CODG_ITCD_GIA_ANTR != ").append(doacaoSucessivaVo.getBeneficiarioVo().getGiaITCDVo().getCodigo());
      sql.append(" ORDER BY " + CAMPO_DATA_GIA_ITCD_ANTR + " ASC ");
      return sql.toString();
   }
   
   private boolean verificaIsencaoUpfGiaDoacaoSucessiva(long codigoItcd) throws ObjetoObrigatorioException, ConsultaException{
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      StringBuilder sql = new StringBuilder();      
      sql.append("SELECT ")
         .append("    CASE ")
         .append("      WHEN MAX(status.STAT_ITCD) = 32 THEN 'true' ")
         .append("      ELSE 'false' ")
         .append("    END AS RESULTADO ")
         .append("FROM ")
         .append("    ITC.ITCTB27_STATUS_ITCD status ")
         .append("WHERE ")
         .append("    status.itctb14_codg_itcd = ").append(codigoItcd);

      try
      {
         ps = conn.prepareStatement(sql.toString());
         rs = ps.executeQuery();
         while (rs.next())
         {
            if (rs.getString("RESULTADO").equals("true"))
               return true;
         }
      }
      catch (SQLException e)
      {
         ExibirLOG.exibirLogSimplificado("Erro - SQL ao consultar Doações Sucessivas externas: " + 
                                         e.getMessage());
         throw new ConsultaException("Erro ao consultar Doações Sucessivas externas" + 
                                     e.getMessage());
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
            ExibirLOG.exibirLogSimplificado("Erro - SQL: " + e.getMessage());
         }
      }
      
      return false;
   }
   
   private double getValorBeneficiarioPorCodigoGiaITCD(long codigoItcd, long numrContribuinte) throws ObjetoObrigatorioException, ConsultaException{
      PreparedStatement ps = null;
      ResultSet rs = null;
      double resultado = 0.0;
      
      StringBuilder sql = new StringBuilder();      
      sql.append("SELECT ")
         .append("   benef.VALR_ITCD_BENF AS VALR_ITCD_BENF ")
         .append("FROM ")
         .append("    ITC.ITCTB05_BENEFICIARIO benef ")
         .append("WHERE ")
         .append("    benef.ITCTB14_CODG_ITCD = ").append(codigoItcd)
         .append("AND ")
         .append("    benef.ACCTB01_NUMR_PESS_BENEF = ").append(numrContribuinte);

      try
      {
         ps = conn.prepareStatement(sql.toString());
         rs = ps.executeQuery();
         while (rs.next()){
            resultado = rs.getDouble("VALR_ITCD_BENF");
         }
      }
      catch (SQLException e)
      {
         ExibirLOG.exibirLogSimplificado("Erro - SQL ao consultar Doações Sucessivas externas: " + 
                                         e.getMessage());
         throw new ConsultaException("Erro ao consultar Doações Sucessivas externas" + 
                                     e.getMessage());
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
            ExibirLOG.exibirLogSimplificado("Erro - SQL: " + e.getMessage());
         }
      }
      
      return resultado;
   }
   
   public boolean verificaSeGiaPresenteEmDoacaoSucessiva(long codgGia) throws ObjetoObrigatorioException, ConsultaException{
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      StringBuilder sql = new StringBuilder()
          .append("SELECT ")
          .append("   CASE ")
          .append("       WHEN COUNT(*) > 0 THEN 'true' ")
          .append("       ELSE 'false' ")
          .append("   END AS RESULTADO ")
          .append("FROM itctb68_benf_gia_doacao_suce doacao ")
          .append("WHERE doacao.itctb14_codg_itcd_gia_antr = ").append(codgGia);

      try
      {
         ps = conn.prepareStatement(sql.toString());
         rs = ps.executeQuery();
         while (rs.next()){
            if (rs.getString("RESULTADO").equals("true"))
               return true;
         }
      }
      catch (SQLException e)
      {
         ExibirLOG.exibirLogSimplificado("Erro - SQL ao consultar Doações Sucessivas externas: " + 
                                         e.getMessage());
         throw new ConsultaException("Erro ao consultar Doações Sucessivas externas" + 
                                     e.getMessage());
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
            ExibirLOG.exibirLogSimplificado("Erro - SQL: " + e.getMessage());
         }
      }
      
      return false;
   }
}
