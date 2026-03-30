package br.gov.mt.sefaz.itc.model.log.log;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposLog;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.SQLException;

import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


public class LogITCDDao extends AbstractDao implements CamposLog, 
                                                       TabelasITC, 
                                                       SequencesITC
{
   /**
    * Construtor que recebe a Conexão com o Banco de Dados.
    * @param conexao objeto de conexão com o banco de dados.
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public LogITCDDao(Connection conexao)
   {
      super(conexao);
      utilStmt = new UtilStmt(TABELA_GIA_ITCD_LOG);
   }


   public long getProximoValorSequence() throws SQLException
   {
      Long pk = null;
      try
      {
         pk = new SefazSequencia(conn).next(SEQUENCE_LOG);
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new SQLException(MensagemErro.FALHAR_AO_GERAR_SEQUENCE + "48");
      }
      return pk;
   }

}
