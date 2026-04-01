package br.gov.mt.sefaz.itc.model.log.historicolog;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposHistoricoLog;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.SQLException;

import sefaz.mt.util.SefazSequencia;


public class HistoricoLogDao extends AbstractDao implements CamposHistoricoLog, 
                                                            TabelasITC, 
                                                            SequencesITC
{

   /**
    * Construtor que recebe a conexão com o banco de dados
    * @param conexao
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public HistoricoLogDao(Connection conexao)
   {
      super(conexao);
   }

   public long getProximoValorSequence() throws SQLException
   {
      Long pk = null;
      try
      {
         pk = new SefazSequencia(conn).next(SEQUENCE_HISTORICO_LOG);
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new SQLException(MensagemErro.FALHAR_AO_GERAR_SEQUENCE + "49");
      }
      return pk;
   }


}
