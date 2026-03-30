package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva;

import br.com.abaco.log5.util.excecoes.IncluiException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposDoacaoSucessiva;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;
import br.gov.mt.sefaz.itc.util.sql.PreparedStatementUtils;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import sefaz.mt.util.SefazDataHora;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


/**
 * Classe para manutenção de dados da Doação - Sucessiva
 * @author Cleiton Saraiva
 * @implemented by Cleiton Saraiva
 */
public class GIAITCDDoacaoSucessivaDao extends AbstractDao implements TabelasITC, CamposDoacaoSucessiva, SequencesITC
{

   /**
    * Construtor que recebe a conexão com o banco de dados
    * @param connection
    * @implemented by Cleiton Saraiva
    */
   public GIAITCDDoacaoSucessivaDao(Connection connection)
   {
      super(connection);
      utilStmt = new UtilStmt(TABELA_GIA_ITCD_DOACAO_SUCESSIVA);
   }
   
   /**
    * Retorna os campos da tabela
    * @return
    * @implemented by Cleiton Saraiva
    */   
   private String[] getCampos(Integer tipo)
      {
      if(tipo == 2 ){
         return new String[] 
         { 
            CAMPO_CODG_BENF_GIA_SEQC, 
            CAMPO_ITCTB05_CODG_BENEFICIARIO, 
            CAMPO_ITCTB14_CODG_ITCD_ANTR,
            CAMPO_ACCTB01_NUMR_PESS_BENF,             
            CAMPO_DATA_GIA_ITCD_ANTR, 
            CAMPO_VALR_BASE_CALC_BENF_GIA_ANTR, 
            CAMPO_VALR_ITCD_BENF_GIA_ANTR, 
            CAMPO_NUMR_PROT_GIA_ANTR, 
            CAMPO_DATA_PROT_GIA_ANTR,
            CAMPO_DATA_ATUALIZACAO,
            CAMPO_VALR_RECEBIDO_BENF_GIA_ANTR,
            CAMPO_VALR_ITCD_RCLH_BENF,
            CAMPO_INFO_ISENCAO
         };
      }else
      {
         return new String[] 
         { 
            CAMPO_CODG_BENF_GIA_SEQC, 
            CAMPO_ITCTB05_CODG_BENEFICIARIO, 
            CAMPO_ITCTB14_CODG_ITCD_ANTR, 
            CAMPO_ACCTB01_NUMR_PESS_BENF,            
            CAMPO_DATA_GIA_ITCD_ANTR, 
            CAMPO_VALR_BASE_CALC_BENF_GIA_ANTR, 
            CAMPO_VALR_ITCD_BENF_GIA_ANTR, 
            CAMPO_NUMR_PROT_GIA_ANTR, 
            CAMPO_DATA_PROT_GIA_ANTR,
            CAMPO_DATA_INCLUSAO,
            CAMPO_DATA_ATUALIZACAO,
            CAMPO_VALR_RECEBIDO_BENF_GIA_ANTR,
            CAMPO_VALR_ITCD_RCLH_BENF,
            CAMPO_INFO_ISENCAO
         };
      }
         
   }
   
   
   public void insertGIAITCDoacaoSucessiva(final GIAITCDDoacaoSucessivaVo doacaoSucessivaVo) throws ObjetoObrigatorioException, IncluiException, 
             ObjetoObrigatorioException
   {
      Validador.validaObjeto(doacaoSucessivaVo);
      PreparedStatement ps = null;
      String sql = utilStmt.geraInsr(getCampos(1));  
      try
      {
         ps = conn.prepareStatement(sql);
         SefazSequencia sequence = new SefazSequencia(conn);
         doacaoSucessivaVo.setCodigo(sequence.next(SEQUENCE_DOACAO_SUCESSIVA));
         preparedStatementGIAITCDDoacaoSucessiva(ps, (GIAITCDDoacaoSucessivaVo) doacaoSucessivaVo, 1);
            try {
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 1) {
                    throw new SQLException("Esperava inserir 1 linha, mas inseriu " + rowsAffected);
                }
            } catch (SQLException e) {
                // Aqui você captura a exceção real e pode ver se é "tabela ou view não existe"
                throw new SQLException("Erro ao executar insert: " + e.getMessage());
            }
      }
      catch (SQLException e)
      {
         ExibirLOG.exibirLogSimplificado("Erro insertGIAITCDoacaoSucessiva - SQL: " + e.getMessage());
         throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_DOACAO_SUCESSIVA);
      }
      
      catch (Exception e)
      {
         ExibirLOG.exibirLogSimplificado("Erro insertGIAITCDoacaoSucessiva - SQL: " + e.getMessage());
         throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_DOACAO_SUCESSIVA);
      }
      finally
      {
         if (ps != null)
         {
            try
            {
               close(ps);
               ps = null;
            }
            catch (SQLException e)
            {
               ExibirLOG.exibirLogSimplificado("Erro ao fechar conexão insertGIAITCDoacaoSucessiva - SQL: " + e.getMessage());
            }
         }
      }
   }
   
   private void preparedStatementGIAITCDDoacaoSucessiva(final PreparedStatement ps, final GIAITCDDoacaoSucessivaVo doacaoSucessivaVo, Integer tipo) throws ObjetoObrigatorioException, SQLException {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(doacaoSucessivaVo);

      int contador = 0;
      if (tipo == 1) { // Insert
         PreparedStatementUtils.setLongOrNull(ps, ++contador, doacaoSucessivaVo.getCodigo());
         PreparedStatementUtils.setLongOrNull(ps, ++contador, doacaoSucessivaVo.getBeneficiarioVo().getCodigo());
         PreparedStatementUtils.setLongOrNull(ps, ++contador, doacaoSucessivaVo.getGiaITCDVo().getCodigo());          
      }
      PreparedStatementUtils.setLongOrNull(ps, ++contador, doacaoSucessivaVo.getPessoaBeneficiaria().getNumrContribuinte().longValue());      
      PreparedStatementUtils.setDateOrNull(ps, ++contador, doacaoSucessivaVo.getDataGIAITCDAnterior());
      PreparedStatementUtils.setDoubleOrNull(ps, ++contador, doacaoSucessivaVo.getValorBaseDeCalc());
      PreparedStatementUtils.setDoubleOrNull(ps, ++contador, doacaoSucessivaVo.getValorITCD());
      PreparedStatementUtils.setIntOrNull(ps, ++contador, Integer.parseInt(doacaoSucessivaVo.getNumeroEprocess().split("/")[0]));
      PreparedStatementUtils.setDateOrNull(ps, ++contador, doacaoSucessivaVo.getDataEProcess());
      if(tipo == 1){// Insert
        ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
      }      
      ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp()); 
      
      PreparedStatementUtils.setDoubleOrNull(ps,++contador, doacaoSucessivaVo.getValorRecebidoBenefAnterior());
      PreparedStatementUtils.setDoubleOrNull(ps, ++contador, doacaoSucessivaVo.getValorRecolherBenef());
      ps.setInt(++contador, doacaoSucessivaVo.getInfoIsencao());      
      
      if (tipo == 2) { // Update         
         PreparedStatementUtils.setLongOrNull(ps, ++contador, doacaoSucessivaVo.getCodigo());
         PreparedStatementUtils.setLongOrNull(ps, ++contador, doacaoSucessivaVo.getBeneficiarioVo().getCodigo());
         PreparedStatementUtils.setLongOrNull(ps, ++contador, doacaoSucessivaVo.getGiaITCDVo().getCodigo());
      }
   
   }
}
