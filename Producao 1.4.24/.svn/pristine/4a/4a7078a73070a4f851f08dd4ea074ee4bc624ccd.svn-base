package br.gov.mt.sefaz.itc.model.log.itemhistorico;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposItemHistoricoLog;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.SQLException;

import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


public class ItemHistoricoDao extends AbstractDao implements CamposItemHistoricoLog, 
                                                             TabelasITC, 
                                                             SequencesITC
{
   /**
    * Construtor que recebe a Conexão com o Banco de Dados.
    * @param conexao objeto de conexão com o banco de dados.
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public ItemHistoricoDao(Connection conexao)
   {
      super(conexao);
      utilStmt = new UtilStmt(TABELA_GIA_ITCD_ITEM_HISTORICO_LOG);
   }


   public long getProximoValorSequence() throws SQLException
   {
      Long pk = null;
      try
      {
         pk = new SefazSequencia(conn).next(SEQUENCE_ITEM_HISTORICO_LOG);
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new SQLException(MensagemErro.FALHAR_AO_GERAR_SEQUENCE + "50");
      }
      return pk;
   }

}
