package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.aliquota;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaVo;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDDoacaoBeneficiarioAliquota;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Iterator;


public class GIAITCDDoacaoBeneficiarioAliquotaQDao extends AbstractDao implements CamposGIAITCDDoacaoBeneficiarioAliquota, TabelasITC
{
   /**
    * Contrutor padrão recebendo uma Conexão
    * @implemented by Lucas Nascimento
    */
   public GIAITCDDoacaoBeneficiarioAliquotaQDao(Connection conexao)
   {
      super(conexao);
   }

   /**
    * Configura um objeto do tipo <code>GIAITCDDoacaoBeneficiarioAliquotaVo</code> com os valores
    * recebidos no <code>ResultSet</code>
    *
    * @param rs            ResultSet
    * @param giaItcdDoacaoBeneficiarioAliquotaVo  objeto a ser configurado
    * @throws ObjetoObrigatorioException
    * @throws SQLException
    */
   private void getGiaItcdDoacaoBeneficiarioAliquota(final ResultSet rs, final GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
           SQLException
   {
      Validador.validaObjeto(rs);
      Validador.validaObjeto(giaItcdDoacaoBeneficiarioAliquotaVo);
      giaItcdDoacaoBeneficiarioAliquotaVo.getGiaItcdDoacaoBeneficiarioVo().setCodigo(rs.getLong(CAMPO_CODIGO_BENEFICIARIO));
      giaItcdDoacaoBeneficiarioAliquotaVo.setCodigoAliquota(rs.getLong(CAMPO_CODIGO_ALIQUOTA));
      giaItcdDoacaoBeneficiarioAliquotaVo.setPercentualAliquota(rs.getDouble(CAMPO_PERCENTUAL_ALIQUOTA));
      giaItcdDoacaoBeneficiarioAliquotaVo.setValorRecolher(rs.getDouble(CAMPO_VALOR_RECOLHER));
      giaItcdDoacaoBeneficiarioAliquotaVo.setValorBaseCalculo(rs.getDouble(CAMPO_BASE_CALCULO));
      giaItcdDoacaoBeneficiarioAliquotaVo.setNumrUpfDisponivel(rs.getDouble(CAMPO_NUMR_UPF_DISP));
      giaItcdDoacaoBeneficiarioAliquotaVo.setNumrUpfUtilizada(rs.getDouble(CAMPO_NUMR_UPF_UTLZ));
      giaItcdDoacaoBeneficiarioAliquotaVo.setInfoUpfRefGiaAntr(rs.getInt(CAMPO_INFO_UPF_REFR_GIA_ANTR));
   }
   
   /**
    * Monta um BeneficiarioVo de acordo como ResultSet
    * @param rs
    * @param beneficiarioVo
    * @throws ObjetoObrigatorioException
    * @throws SQLException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void getBeneficiario(final ResultSet rs, final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
           SQLException
   {
      //TODO: Trocar e utilizar o que já existe.
      Validador.validaObjeto(rs);
      Validador.validaObjeto(beneficiarioVo);      
      beneficiarioVo.setCodigo(rs.getLong("CODG_BENEFICIARIO"));
      beneficiarioVo.setGiaITCDVo(new GIAITCDVo(rs.getLong("ITCTB14_CODG_ITCD")));
      beneficiarioVo.setPessoaBeneficiaria(new ContribuinteIntegracaoVo(new Long(rs.getLong("ACCTB01_NUMR_PESS_BENEF"))));
      beneficiarioVo.setValorRecebido(rs.getDouble("VALR_RECEBIDO"));
      beneficiarioVo.setValorRecebidoAvaliacao(rs.getDouble("VALR_RECEBIDO_AVALIACAO"));
      beneficiarioVo.setFlagDoacaoSucessiva(rs.getInt("INFO_DOACAO_SUCESSIVA"));
      beneficiarioVo.setValorRecebidoDoacaoSucessiva(rs.getDouble("VALR_RECB_DOACAO_SUCESSIVA"));
      beneficiarioVo.setFlagDoacaoAnteriorManualEprocess(rs.getInt("INFO_DOACAO_ANTR_PROT_MANUAL"));
      beneficiarioVo.setValorItcdBeneficiario(rs.getDouble("VALR_ITCD_BENF"));      
      ((GIAITCDDoacaoBeneficiarioVo)beneficiarioVo).setInfoDispensaRecolhimento(rs.getInt("INFO_DSPE_RCLH"));
      ((GIAITCDDoacaoBeneficiarioVo)beneficiarioVo).setInfoIsencao(rs.getInt("INFO_ISENCAO"));
   }

   /**
    * Encontra "um" determinado Beneficiario do tipo Doação
    *
    * @param giaItcdDoacaoBeneficiarioAliquotaVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    */
   public GIAITCDDoacaoBeneficiarioAliquotaVo findGiaItcdDoacaoBeneficiarioAliquota(final GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(giaItcdDoacaoBeneficiarioAliquotaVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {         
         ps = conn.prepareStatement(getSQLFindGiaItcdDoacaoBeneficiarioAliquota(giaItcdDoacaoBeneficiarioAliquotaVo));
         prepareStatementFindGiaItcdDoacaoBeneficiarioAliquota(ps, giaItcdDoacaoBeneficiarioAliquotaVo);
         rs = ps.executeQuery();
         if (rs.next())
         {
            getGiaItcdDoacaoBeneficiarioAliquota(rs, giaItcdDoacaoBeneficiarioAliquotaVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTA_GIA_ITCD_DOACAO_BENEFICIARIO_ALIQUOTA);
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
      return giaItcdDoacaoBeneficiarioAliquotaVo;
   }
   
   public void findGiaItcdDoacaoBeneficiarioAliquotaBeneficiarioPermanente(final GIAITCDDoacaoBeneficiarioVo giaDoacaoBeneficiario)  throws ObjetoObrigatorioException, ConsultaException {           
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {        
         ps = conn.prepareStatement(getSQLFindGiaItcdDoacaoBeneficiarioAliquotaBeneficiarioPermanente(giaDoacaoBeneficiario));         
         rs = ps.executeQuery();
         while (rs.next()){
            GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo = new GIAITCDDoacaoBeneficiarioAliquotaVo();
            getGiaItcdDoacaoBeneficiarioAliquota(rs, giaItcdDoacaoBeneficiarioAliquotaVo);
            giaDoacaoBeneficiario.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().add(giaItcdDoacaoBeneficiarioAliquotaVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTA_GIA_ITCD_DOACAO_BENEFICIARIO_ALIQUOTA);
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
   }
   
   private String getSQLFindGiaItcdDoacaoBeneficiarioAliquotaBeneficiarioPermanente(final GIAITCDDoacaoBeneficiarioVo giaDoacaoBeneficiario){
      StringBuilder sql = new StringBuilder();

      sql.append("SELECT ")
         .append("    tb62.ITCTB11_CODG_LGIL_ALIQUOTA, ")
         .append("    tb62.ITCTB41_ITCTB05_CODG_BENF, ")
         .append("    tb62.PERC_ALIQUOTA, ")
         .append("    tb62.VALR_RECOLHER, ")
         .append("    tb62.BASE_CALCULO, ")
         .append("    tb62.NUMR_UPF_UTLZ, ")
         .append("    tb62.NUMR_UPF_DISP, ")
         .append("    tb62.INFO_UPF_REFR_GIA_ANTR ")
         .append("FROM ")
         .append("    ITC.ITCTB05_BENEFICIARIO tb05 ")
         .append("INNER JOIN ITC.ITCTB62_BENF_DOACAO_ALIQUOTA tb62 ON ")
         .append("    tb62.ITCTB41_ITCTB05_CODG_BENF = tb05.CODG_BENEFICIARIO ")
         .append("WHERE ")
         .append("    tb05.ITCTB14_CODG_ITCD = ").append(giaDoacaoBeneficiario.getGiaITCDVo().getCodigo()).append(" ")
         .append("    AND tb05.CODG_BENEFICIARIO = ").append(giaDoacaoBeneficiario.getCodigo());
         
      return sql.toString();
   }
   
   public void findGiaItcdDoacaoBeneficiarioAliquotaDoacaoSucessiva(final GIAITCDDoacaoSucessivaVo giaItcdDoacaoSucessiva, final Long numrPessoaBenef) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         for(Iterator it = giaItcdDoacaoSucessiva.getCollVO().iterator(); it.hasNext();){
            GIAITCDDoacaoSucessivaVo doacaoSucessiva = (GIAITCDDoacaoSucessivaVo) it.next(); 
            
            ps = conn.prepareStatement(getSQLFindBeneficiarioDoacaoSucessiva(doacaoSucessiva.getGiaITCDVo().getCodigo(), numrPessoaBenef));        
            rs = ps.executeQuery();
            
            GIAITCDDoacaoBeneficiarioVo beneficiario = new GIAITCDDoacaoBeneficiarioVo(); 
            
            while (rs.next()){                           
               getBeneficiario(rs, beneficiario);
            }
            
            ps = conn.prepareStatement(getSQLFindGiaItcdDoacaoBeneficiarioAliquotaDoacaoSucessiva(beneficiario.getCodigo()));        
            rs = ps.executeQuery();
               
            while (rs.next()){            
               GIAITCDDoacaoBeneficiarioAliquotaVo aliquotaAtual = new GIAITCDDoacaoBeneficiarioAliquotaVo();
               getGiaItcdDoacaoBeneficiarioAliquota(rs, aliquotaAtual);  
               beneficiario.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().add(aliquotaAtual);                  
            }  
            
            doacaoSucessiva.setBeneficiarioVo(beneficiario);
            
         }   
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTA_GIA_ITCD_DOACAO_BENEFICIARIO_ALIQUOTA);
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
      
      giaItcdDoacaoSucessiva.getBeneficiarioVo();
   }

   /**
    * Encontra "um" determinado Beneficiario do tipo Doacão
    *
    * @param giaItcdDoacaoBeneficiarioAliquotaVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    */
   public GIAITCDDoacaoBeneficiarioAliquotaVo listGiaItcdDoacaoBeneficiarioAliquota(final GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(giaItcdDoacaoBeneficiarioAliquotaVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindGiaItcdDoacaoBeneficiarioAliquota(giaItcdDoacaoBeneficiarioAliquotaVo));
         prepareStatementFindGiaItcdDoacaoBeneficiarioAliquota(ps, giaItcdDoacaoBeneficiarioAliquotaVo);
         rs = ps.executeQuery();
         while (rs.next())
         {
            GIAITCDDoacaoBeneficiarioAliquotaVo aliquotaAtual = new GIAITCDDoacaoBeneficiarioAliquotaVo();
            getGiaItcdDoacaoBeneficiarioAliquota(rs, aliquotaAtual);
            giaItcdDoacaoBeneficiarioAliquotaVo.getCollVO().add(aliquotaAtual);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTA_GIA_ITCD_DOACAO_BENEFICIARIO_ALIQUOTA);
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
      return giaItcdDoacaoBeneficiarioAliquotaVo;
   }

   /**
    * Método responsável por retornar a String de Consulta (SELECT) SQL 
    * 
    * @param giaItcdDoacaoBeneficiarioAliquotaVo
    * @return
    * @throws ObjetoObrigatorioException
    * @implemented by Lucas Nascimento
    */
   private String getSQLFindGiaItcdDoacaoBeneficiarioAliquota(final GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(giaItcdDoacaoBeneficiarioAliquotaVo);
      
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT G.").append(CAMPO_CODIGO_BENEFICIARIO).append(" ");
      sql.append(" , G.").append(CAMPO_CODIGO_ALIQUOTA).append(" ");
      sql.append(" , G.").append(CAMPO_PERCENTUAL_ALIQUOTA).append(" ");
      sql.append(" , G.").append(CAMPO_VALOR_RECOLHER).append(" ");
      sql.append(" , G.").append(CAMPO_BASE_CALCULO).append(" ");
      sql.append(" , G.").append(CAMPO_NUMR_UPF_DISP).append(" ");
      sql.append(" , G.").append(CAMPO_NUMR_UPF_UTLZ).append(" ");
      sql.append(" , G.").append(CAMPO_INFO_UPF_REFR_GIA_ANTR).append(" ");
      sql.append(" FROM ").append(TABELA_GIA_ITCD_DOACAO_ALIQUOTA).append(" G ");
      sql.append(" WHERE 1 = 1 ");
      if (giaItcdDoacaoBeneficiarioAliquotaVo.isConsultaParametrizada())
      {
         GIAITCDDoacaoBeneficiarioAliquotaVo parametro = (GIAITCDDoacaoBeneficiarioAliquotaVo) giaItcdDoacaoBeneficiarioAliquotaVo.getParametroConsulta();
         
         // CAMPO_CODIGO_BENEFICIARIO
         if (Validador.isNumericoValido(parametro.getGiaItcdDoacaoBeneficiarioVo().getCodigo()))
         {
            sql.append("   AND G.").append(CAMPO_CODIGO_BENEFICIARIO).append(" = ? ");
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
      sql.append(" ORDER BY G.").append(CAMPO_CODIGO_BENEFICIARIO).append(" ");
      return sql.toString();
   }
   
   /**
    * Método responsável por retornar a todas as aliquotas de um beneficiario de uma gia. 
    * 
    * @param giaItcdDoacaoBeneficiarioAliquotaVo
    * @return
    * @throws ObjetoObrigatorioException
    * @implemented by Lucas Nascimento
    */
   private String getSQLFindGiaItcdDoacaoBeneficiarioAliquotaDoacaoSucessiva(final Long codgBeneficiario) throws ObjetoObrigatorioException{
      StringBuffer sql = new StringBuffer();

      sql.append("SELECT ")
         .append("    tb62.ITCTB11_CODG_LGIL_ALIQUOTA, ")
         .append("    tb62.ITCTB41_ITCTB05_CODG_BENF, ")
         .append("    tb62.PERC_ALIQUOTA, ")
         .append("    tb62.VALR_RECOLHER, ")
         .append("    tb62.BASE_CALCULO, ")
         .append("    tb62.NUMR_UPF_UTLZ, ")
         .append("    tb62.NUMR_UPF_DISP, ")
         .append("    tb62.INFO_UPF_REFR_GIA_ANTR ")
         .append("FROM ")
         .append("    ITCTB62_BENF_DOACAO_ALIQUOTA tb62 ")
         .append("WHERE ")
         .append("    tb62.ITCTB41_ITCTB05_CODG_BENF = ")
         .append(codgBeneficiario);
         
         return sql.toString();
   }
   
   /**
    * Método responsável por retornar todos os beneficiarios de uma Doacão sucessiva 
    * 
    * @param giaItcdDoacaoBeneficiarioAliquotaVo
    * @return
    * @throws ObjetoObrigatorioException
    * @implemented by Lucas Nascimento
    */
   private String getSQLFindBeneficiarioDoacaoSucessiva(final Long codgItcd, final Long numrPessoaBenef) throws ObjetoObrigatorioException{
      StringBuffer sql = new StringBuffer();


      sql.append("SELECT ")
         .append("    tb05.CODG_BENEFICIARIO, ")
         .append("    tb05.ITCTB14_CODG_ITCD, ")
         .append("    tb05.ACCTB01_NUMR_PESS_BENEF, ")
         .append("    tb05.VALR_RECEBIDO, ")
         .append("    tb05.VALR_RECEBIDO_AVALIACAO, ")
         .append("    tb05.INFO_DOACAO_SUCESSIVA, ")
         .append("    tb05.VALR_RECB_DOACAO_SUCESSIVA, ")
         .append("    tb05.INFO_DOACAO_ANTR_PROT_MANUAL, ")
         .append("    tb05.VALR_ITCD_BENF, ")
         .append("    tb41.INFO_ISENCAO, ")
         .append("    tb41.INFO_DSPE_RCLH ")
         .append("FROM ")
         .append("    ITCTB05_BENEFICIARIO tb05 ")
         .append("INNER JOIN ")
         .append("    ITCTB41_BENEF_DOACAO tb41 ")
         .append("    ON tb41.ITCTB05_CODG_BENEFICIARIO = tb05.CODG_BENEFICIARIO ")
         .append("WHERE ")
         .append("    tb05.ITCTB14_CODG_ITCD = ").append(codgItcd).append(" ")
         .append("    AND tb05.ACCTB01_NUMR_PESS_BENEF = ").append(numrPessoaBenef);
         
      return sql.toString();
   }
   

   /**
    * Método responável por adicionar os parâmetros válidos na instrução SQL (java.sql.PreparedStatement)
    * @param ps (java.sql.PreparedStatement)
    * @param giaItcdDoacaoBeneficiarioAliquotaVo (Value Object)
    * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
    * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
    * @implemented by Lucas Nascimento
    */
   private void prepareStatementFindGiaItcdDoacaoBeneficiarioAliquota(final PreparedStatement ps, final GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo) throws ObjetoObrigatorioException, 
           SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(giaItcdDoacaoBeneficiarioAliquotaVo);
      int contador = 0;
      if (giaItcdDoacaoBeneficiarioAliquotaVo.isConsultaParametrizada())
      {
         GIAITCDDoacaoBeneficiarioAliquotaVo parametro = (GIAITCDDoacaoBeneficiarioAliquotaVo) giaItcdDoacaoBeneficiarioAliquotaVo.getParametroConsulta();
         
         // CAMPO_CODIGO_BENEFICIARIO
         if (Validador.isNumericoValido(parametro.getGiaItcdDoacaoBeneficiarioVo().getCodigo()))
         {
            ps.setLong(++contador, parametro.getGiaItcdDoacaoBeneficiarioVo().getCodigo());
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
