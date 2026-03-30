package br.gov.mt.sefaz.itc.model.log.log;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposLog;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LogITCDQDao extends AbstractDao implements CamposLog, 
                                                        TabelasITC, 
                                                        SequencesITC
{

   /**
    * Construtor que recebe a conexão com o banco de dados
    * @param conexao
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public LogITCDQDao(Connection conexao)
   {
      super(conexao);
   }

   /**
    * Método que retorna um obejeto do tipo LogITCDVo
    * iniciado com o próximo valor da sequence ITCTB48_LOG;
    * 
    * 
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public LogITCDVo getLogITCDVo()
   {
      LogITCDVo logITCDVo = new LogITCDVo();
      logITCDVo.setCodigo(getSequenceITCTB48Log());
      return logITCDVo;
   }

   private String getSQLCodigoLogITCDVo()
   {
      String sql = "SELECT MAX(NUMR_LOG_SEQC)FROM ITCTB48_LOG;";
      return sql;
   }

   /**
    * Método contendo o SQL para realizar um SELECT na sequence ITCTB48_LOG
    * para retornar o próximo valor da sequence ITCTB48_LOG.
    * 
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   private String getSQLSequenceLogITCDVo()
   {
      String sql = "SELECT " + SEQUENCE_LOG + ".NEXTVAL FROM DUAL";
      return sql;
   }


   /**
    * Método que retorna o próximo valor da sequence ITCTB48_LOG;
    * 
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   private long getSequenceITCTB48Log()
   {
      PreparedStatement ps = null;
      ResultSet rs = null;
      long codigo = 0;
      try
      {
         ps = conn.prepareStatement(getSQLSequenceLogITCDVo());
         rs = ps.executeQuery();
         if (rs.next())
         {
            codigo = rs.getLong(CamposLog.CAMPO_NUM_LOG_SEQC);
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
      }
      return codigo;
   }

   private Long getMaxPrimaryKey()
   {
      PreparedStatement ps = null;
      ResultSet rs = null;
      long primaryKey = 0;
      try
      {
         ps = conn.prepareStatement(getSQLCodigoLogITCDVo());
         rs = ps.executeQuery();
         if (rs.next())
         {
            primaryKey = rs.getLong(CamposLog.CAMPO_NUM_LOG_SEQC);
            primaryKey++;
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
      }
      return primaryKey;
   }


//------------------------ Estrutura SQL --------------------------------

   /**
    * Cria o código SQL para consulta equivalente a <b> SELECT * FROM ITCTB48_LOG </b>
    * <br>
    * Esta consulta não utiliza nenhum tipo de parametro para filtrar os dados
    * sendo assim ira retornar todos os dados existentes na tabela.
    * 
    * @return String contedo o código SQL para consulta completa.
    */
   private String getSQLBase()
   {
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT LOGITCD.").append(CAMPO_NUM_LOG_SEQC);
      sql.append(" , LOGITCD.").append(CAMPO_DATA_TRANSACAO);
      sql.append(" , LOGITCD.").append(CAMPO_ENDERECO_IP);
      sql.append(" FROM ").append(TABELA_GIA_ITCD_LOG).append(" LOGITCD ");    
      return sql.toString();
   }
   
   /**
    * Retira as informações do ResultSet a atribui os valores ao
    * Objeto <b>LogITCDVo</b> passado como parametro.
    * 
    * 
    * @param rs Objeto retornado pela consulta no BD.
    * @param logITCDVo Objeto ao qual será atribuito os valores do ResultSet.
    * @return LogITCDVo com os valores obtidos do ResultSet.
    * @throws ObjetoObrigatorioException caso o objeto tenha valor <b>null</b>
    * @throws SQLException caso não consiga executar corretamente a consulta no BD.
    */
   private LogITCDVo getLogITCD(final ResultSet rs, final LogITCDVo logITCDVo) throws  SQLException, 
                                                                                      ObjetoObrigatorioException
   {
      Validador.validaObjeto(rs);
      Validador.validaObjeto(logITCDVo);
      // recuperando valor do request.
      logITCDVo.setCodigo(rs.getLong(CAMPO_NUM_LOG_SEQC));
      logITCDVo.setDataTransacao(rs.getDate(CAMPO_DATA_TRANSACAO));
      logITCDVo.setEnderecoIP(rs.getString(CAMPO_ENDERECO_IP));
      
      return logITCDVo;
   }
   
   /**
    * Adiciona parametros na consulta SQL para serem utilizadas como filtros
    * nas consultas.
    * retornado apenas dados que atendam os filtros.
    * 
    * 
    * @param logITCDVo objeto com os dados para serem
    * @return
    * @throws ObjetoObrigatorioException caso o objeto tenha valor <b>null</b>
    */
   private String getSQLFindLogITCDVo(final LogITCDVo logITCDVo) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(logITCDVo);
      StringBuffer sql = new StringBuffer();
      sql.append(getSQLBase());
      sql.append(" WHERE 1 = 1 ");
      if (logITCDVo.isConsultaParametrizada())
      {
         // CODIGO
         if (Validador.isNumericoValido(( logITCDVo.getParametroConsulta()).getCodigo()))
         {
            sql.append(" AND LOGITCD.").append(CAMPO_NUM_LOG_SEQC).append(" = ? ");
         }
      }
      sql.append(" ORDER BY LOGITCD.").append(CAMPO_NUM_LOG_SEQC).append(" ASC ");
      return sql.toString();
   }
   
   /**
    * Pesquisa no BD o registro referente ao código infomado
    * cado seja encontrado será preenchido os demais atributos
    * do objeto.
    * 
    * 
    * @param logITCDVo com os dados que serão aplicados parametros
    * para realizasão da consulta.
    * @return
    * @throws ObjetoObrigatorioException caso o objeto tenha valor <b>null</b>
    * @throws SQLException caso não consiga executar corretamente a consulta no BD.
    */
  public LogITCDVo findLogITCDVo(final LogITCDVo logITCDVo) throws ObjetoObrigatorioException, 
                                                                     ConsultaException
   {

      Validador.validaObjeto(logITCDVo);
      PreparedStatement ps = null;
      ResultSet rs = null;

      try
      {
         ps = conn.prepareStatement(getSQLFindLogITCDVo(logITCDVo));
         prepareStatementFindStatusGIAITCD(ps, logITCDVo);
         rs = ps.executeQuery();
         if(rs.next())
         {
            getLogITCD(rs,logITCDVo);
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_LOG_ITCD);
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
      
      return logITCDVo;
   }


   /**
    * Define os valores que devem ser utilizados nos parametros
    * 
    * 
    * @param ps 
    * @param logITCDVo com os dados que serão aplicados aos parametros.
    * @throws ObjetoObrigatorioException caso o objeto tenha valor <b>null</b>
    * @throws SQLException caso não consiga executar corretamente a consulta no BD.
    */
   private void prepareStatementFindStatusGIAITCD(final PreparedStatement ps,final LogITCDVo  logITCDVo) throws ObjetoObrigatorioException, 
                                                                                                                SQLException
   {
      Validador.validaObjeto(ps);
		Validador.validaObjeto(logITCDVo);
		int contador = 0;
		if (logITCDVo.isConsultaParametrizada())
		{
         // CODIGO
         if (Validador.isNumericoValido((logITCDVo.getParametroConsulta()).getCodigo()))
         {
            ps.setLong(++contador, (logITCDVo.getParametroConsulta()).getCodigo());
         }
      }
   }
}
