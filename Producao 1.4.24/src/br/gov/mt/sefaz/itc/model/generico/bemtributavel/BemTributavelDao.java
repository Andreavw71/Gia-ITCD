 /**
  * Ábaco Tecnologia de Informação - LTDA
  * Arquivo: BemTributavelDao.java
  * Revisão: 
  * Data revisão:
  * $Id: BemTributavelDao.java,v 1.6 2009/03/23 14:32:35 ricardo.moraes Exp $
  */
 package br.gov.mt.sefaz.itc.model.generico.bemtributavel;

 import br.com.abaco.util.Validador;
 import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
 import br.com.abaco.util.exceptions.ParametroObrigatorioException;

 import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
 import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
 import br.gov.mt.sefaz.itc.util.dominio.DomnTipoUsuario;
 import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBemTributavel;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.SQLException;

 import sefaz.mt.log.GeradorLogSefazMT;
 import sefaz.mt.log.util.dominios.DomnOperacao;
 import sefaz.mt.util.AlteraException;
 import sefaz.mt.util.ExcluiException;
 import sefaz.mt.util.IncluiException;
 import sefaz.mt.util.SefazSequencia;
 import sefaz.mt.util.UtilStmt;


 /**
  * Classe de acesso a dados (Data Access Object) para a tabela de Bem Tributável.
  * @author Lucas Nascimento
  * @version $Revision: 1.6 $
  * 13/11/2007 - Leandro Dorileo
  *    Finalização do método insertBemTributavel. Implementação do tratamento das exceções etc
  *    Finalização do método preparedStatementInsertBemTributavel. Modificado o tratamento de campos NOTNULL.
  */
 public class BemTributavelDao extends AbstractDao implements CamposBemTributavel, TabelasITC, SequencesITC
 {

    /**
     * Construtor público da classe.
     * @param conexao objeto de conexão com o banco de dados.
     */
    public BemTributavelDao(Connection conexao)
    {
       super(conexao);
       utilStmt = new UtilStmt(TABELA_GIA_ITCD_BEM_TRIBUTAVEL);
    }

    /**
     * Retorna os Campos da Tabela de Bem Tributável
     * @return
     */
    public String[] getCamposBemTributavel()
    {
       return new String[] { CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL, CAMPO_ITCTB14_CODIGO_ITCD, CAMPO_VALOR_MERCADO, CAMPO_DESCRICAO_BEM_TRIBUTAVEL, CAMPO_ISENCAO_PREVISTA, CAMPO_FLAG_BEM_PARTICULAR, CAMPO_TIPO_USUARIO_INCLUSAO, CAMPO_ITCTB06_CODIGO_BEM, CAMPO_STAT_BEM_TRBT, CAMPO_VALOR_INFORMADO, CAMPO_FLAG_CONCORDA_VALOR };
    }

    /**
     * Incluir informações sobre um Bem Tributável no banco de dados.
     * @param bemTributavel       VO de Bem Tributável
     * @throws ObjetoObrigatorioException
     * @throws IncluiException
     */
    public void insertBemTributavel(final BemTributavelVo bemTributavel) throws ObjetoObrigatorioException, 
            IncluiException
    {
       Validador.validaObjeto(bemTributavel);
       PreparedStatement ps = null;
       String[] campos = getCamposBemTributavel();
       String sql = utilStmt.geraInsr(campos);
       try
       {
          GeradorLogSefazMT.gerar(bemTributavel, DomnOperacao.OPERACAO_INSERT, bemTributavel.getNumeroParticao(), bemTributavel.getCodigoTransacao(), bemTributavel.getLogSefazVo().getUsuario().getCodigo(), conn);
          SefazSequencia sequence = new SefazSequencia(conn);
          bemTributavel.setCodigo(sequence.next(SEQUENCE_GIA_ITCD_BEM_TRIBUTAVEL));
          ps = conn.prepareStatement(sql);
          preparedStatementInsertBemTributavel(ps, bemTributavel);
          if (ps.executeUpdate() != 1)
          {
             throw new SQLException();
          }
       }
       catch (SQLException e)
       {
          e.printStackTrace();
          throw new IncluiException(MensagemErro.INCLUIR_BEM_TRIBUTAVEL);
       }
       /**      catch(LogSefazException e)
       {
          e.printStackTrace();
          throw new IncluiException(MensagemErro.INCLUIR_BEM_TRIBUTAVEL);
       }*/
       catch (Exception e)
       {
          e.printStackTrace();
          throw new IncluiException(MensagemErro.INCLUIR_BEM_TRIBUTAVEL);
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
                e.printStackTrace();
             }
          }
       }
    }

    /**
     * Método responsável por adicionar os parâmetros válidos na instrução SQL (java.sql.PrepareStatement).
     * @param ps (java.sql.PreparedStatement).
     * @param bemTributavelVo Bem Tributável(Value Object).
     * @throws ObjetoObrigatorioException
     * @throws SQLException
     * @throws ParametroObrigatorioException
     * @implemented by Daniel Balieiro
     */
    private void preparedStatementInsertBemTributavel(final PreparedStatement ps, final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
            SQLException, ParametroObrigatorioException
    {
       Validador.validaObjeto(ps);
       Validador.validaObjeto(bemTributavelVo);
       int contador = 0;
       //CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL ,
       if (Validador.isNumericoValido(bemTributavelVo.getCodigo()))
       {
          ps.setLong(++contador, bemTributavelVo.getCodigo());
       }
       else
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_CODIGO);
       }
       //CAMPO_ITCTB14_CODIGO_ITCD,
       if (Validador.isNumericoValido(bemTributavelVo.getGiaITCDVo().getCodigo()))
       {
          ps.setLong(++contador, bemTributavelVo.getGiaITCDVo().getCodigo());
       }
       else
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_CODIGO_ITC);
       }
       //CAMPO_VALOR_MERCADO ,
       if (Validador.isNumericoValido(bemTributavelVo.getValorMercado()))
       {
          ps.setDouble(++contador, bemTributavelVo.getValorMercado());
       }
       else
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_VALOR_MERCADO);
       }
       //CAMPO_DESCRICAO_BEM_TRIBUTAVEL ,
       if (Validador.isStringValida(bemTributavelVo.getDescricaoBemTributavel()))
       {
          ps.setString(++contador, bemTributavelVo.getDescricaoBemTributavel());
       }
       else
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_DESCRICAO);
       }
       //CAMPO_ISENCAO_PREVISTA ,
       if (Validador.isDominioNumericoValido(bemTributavelVo.getIsencaoPrevista()))
       {
          ps.setInt(++contador, bemTributavelVo.getIsencaoPrevista().getValorCorrente());
       }
       else
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_ISENCAO_PREVISTA);
       }
       //FLAG_BEM_PARTICULAR
       if(Validador.isDominioNumericoValido(bemTributavelVo.getBemParticular()))
       {
          ps.setInt(++contador, bemTributavelVo.getBemParticular().getValorCorrente());
       }
       else
       {
          ps.setInt(++contador, DomnSimNao.NAO);
       }
       if(Validador.isDominioNumericoValido(bemTributavelVo.getTipoUsuario()))
       {
          ps.setInt(++contador, bemTributavelVo.getTipoUsuario().getValorCorrente());
       }
       else
       {
          ps.setInt(++contador, DomnTipoUsuario.CONTRIBUINTE);
       }
       // BEM
       if (Validador.isNumericoValido(bemTributavelVo.getBemVo().getCodigo()))
       {
          ps.setLong(++contador, bemTributavelVo.getBemVo().getCodigo());
       }
       else
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_TIPO);
       }
        if (Validador.isDominioNumericoValido(bemTributavelVo.getStatusBemTributavel()))
        {
            ps.setInt(++contador, bemTributavelVo.getStatusBemTributavel().getValorCorrente());
        }
        if (Validador.isNumericoValido(bemTributavelVo.getValorInformadoContribuinte()))
        {
            ps.setDouble(++contador, bemTributavelVo.getValorInformadoContribuinte());
        }
        
        if (Validador.isDominioNumericoValido(bemTributavelVo.getConcordaComValorArbitrado()))
        {
            ps.setInt(++contador, bemTributavelVo.getConcordaComValorArbitrado().getValorCorrente());
        }
    }

    /**
     * Atualiza informações sobre um Bem Tributável no banco de dados.
     * @param bemTributavelVo        VO de Bem Tributável
     * @throws ObjetoObrigatorioException
     * @throws AlteraException
     */
    public void updateBemTributavel(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
            AlteraException
    {
       Validador.validaObjeto(bemTributavelVo);
       PreparedStatement ps = null;
       String sql = utilStmt.geraUpdt(getCamposBemTributavel(), new String[] { CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL });
       try
       {
          GeradorLogSefazMT.gerar(bemTributavelVo, DomnOperacao.OPERACAO_UPDATE, bemTributavelVo.getNumeroParticao(), bemTributavelVo.getCodigoTransacao(), bemTributavelVo.getLogSefazVo().getUsuario().getCodigo(), conn);
          ps = conn.prepareStatement(sql);
          preparedStatementUpdateBemTributavel(ps, bemTributavelVo);
          if (ps.executeUpdate() != 1)
          {
             throw new SQLException();
          }
       }
       catch (SQLException e)
       {
          e.printStackTrace();
          throw new AlteraException(MensagemErro.ALTERAR_BEM_TRIBUTAVEL);
       }
       catch (ParametroObrigatorioException e)
       {
          e.printStackTrace();
          throw new AlteraException(MensagemErro.ALTERAR_BEM_TRIBUTAVEL);
       }
       /**      catch (LogSefazException e)
       {
          e.printStackTrace();
          throw new AlteraException(MensagemErro.ALTERAR_BEM_TRIBUTAVEL);
       }**/
       catch (Exception e)
       {
          e.printStackTrace();
          throw new AlteraException(MensagemErro.ALTERAR_BEM_TRIBUTAVEL);
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
                e.printStackTrace();
             }
          }
       }
    }

    /**
     * Método responsável por adicionar os parâmetros válidos na instrução SQL (java.sql.PrepareStatement).
     * @param ps (java.sql.PreparedStatement).
     * @param bemTributavelVo Bem Tributável(Value Object).
     * @throws ObjetoObrigatorioException
     * @throws SQLException
     * @throws ParametroObrigatorioException
     * @implemented by Daniel Balieiro
     */
    private void preparedStatementUpdateBemTributavel(final PreparedStatement ps, final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
            SQLException, ParametroObrigatorioException
    {
       Validador.validaObjeto(ps);
       Validador.validaObjeto(bemTributavelVo);
       int contador = 0;
       //CAMPO_ITCTB14_CODIGO_ITCD,
       if (Validador.isNumericoValido(bemTributavelVo.getGiaITCDVo().getCodigo()))
       {
          ps.setLong(++contador, bemTributavelVo.getGiaITCDVo().getCodigo());
       }
       else
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_CODIGO_ITC);
       }
       //CAMPO_VALOR_MERCADO ,
       if (Validador.isNumericoValido(bemTributavelVo.getValorMercado()))
       {
          ps.setDouble(++contador, bemTributavelVo.getValorMercado());
       }
       else
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_VALOR_MERCADO);
       }
       //CAMPO_DESCRICAO_BEM_TRIBUTAVEL ,
       if (Validador.isStringValida(bemTributavelVo.getDescricaoBemTributavel()))
       {
          ps.setString(++contador, bemTributavelVo.getDescricaoBemTributavel());
       }
       else
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_DESCRICAO);
       }
       //CAMPO_ISENCAO_PREVISTA ,
       if (Validador.isDominioNumericoValido(bemTributavelVo.getIsencaoPrevista()))
       {
          ps.setInt(++contador, bemTributavelVo.getIsencaoPrevista().getValorCorrente());
       }
       else
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_ISENCAO_PREVISTA);
       }
       //FLAG_BEM_PARTICULAR
       if(Validador.isDominioNumericoValido(bemTributavelVo.getBemParticular()))
       {
          ps.setInt(++contador, bemTributavelVo.getBemParticular().getValorCorrente());
       }
       else
       {
          ps.setInt(++contador, DomnSimNao.NAO);
       }  
       if(Validador.isDominioNumericoValido(bemTributavelVo.getTipoUsuario()))
       {
          ps.setInt(++contador, bemTributavelVo.getTipoUsuario().getValorCorrente());
       }
       else
       {
          ps.setInt(++contador, DomnTipoUsuario.CONTRIBUINTE);
       }
       // BEM 
       if (Validador.isNumericoValido(bemTributavelVo.getBemVo().getCodigo()))
       {
          ps.setLong(++contador, bemTributavelVo.getBemVo().getCodigo());
       }
       else
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_TIPO);
       }
       //CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL ,
       if (Validador.isNumericoValido(bemTributavelVo.getCodigo()))
       {
          ps.setLong(++contador, bemTributavelVo.getCodigo());
       }
       else
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_CODIGO);
       }
       
        if (Validador.isDominioNumericoValido(bemTributavelVo.getStatusBemTributavel()))
        {
            ps.setInt(++contador, bemTributavelVo.getStatusBemTributavel().getValorCorrente());
        }
        if (Validador.isNumericoValido(bemTributavelVo.getValorInformadoContribuinte()))
        {
            ps.setDouble(++contador, bemTributavelVo.getValorInformadoContribuinte());
        }
       
        if (Validador.isDominioNumericoValido(bemTributavelVo.getConcordaComValorArbitrado()))
        {
            ps.setInt(++contador, bemTributavelVo.getConcordaComValorArbitrado().getValorCorrente());
        }
    }

    /**
     * Método responsável por remover um registro da tabela de bem tributável
     * @param bemTributavelVo           VO usado por fornecer o parametro para a remoção
     * @throws ObjetoObrigatorioException
     * @throws ExcluiException
     */
    public void removeBemTributavel(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
            ExcluiException
    {
       Validador.validaObjeto(bemTributavelVo);
       PreparedStatement ps = null;
       String sql = utilStmt.geraDelt(new String[] { CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL });
       try
       {
          GeradorLogSefazMT.gerar(bemTributavelVo, DomnOperacao.OPERACAO_DELETE, bemTributavelVo.getNumeroParticao(), bemTributavelVo.getCodigoTransacao(), bemTributavelVo.getLogSefazVo().getUsuario().getCodigo(), conn);
          ps = conn.prepareStatement(sql);
          preparedStatementDeleteBemTributavel(ps, bemTributavelVo);
          if (ps.executeUpdate() != 1)
          {
             throw new SQLException();
          }
       }
       catch (SQLException e)
       {
          e.printStackTrace();
          throw new ExcluiException(MensagemErro.EXCLUIR_BEM_TRIBUTAVEL);
       }
       catch (ParametroObrigatorioException e)
       {
          e.printStackTrace();
          throw new ExcluiException(MensagemErro.EXCLUIR_BEM_TRIBUTAVEL);
       }
       catch (Exception e)
       {
          e.printStackTrace();
          throw new ExcluiException(MensagemErro.EXCLUIR_BEM_TRIBUTAVEL);
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
                e.printStackTrace();
             }
          }
       }
    }

    /**
     * Método responsável por adicionar os parâmetros válidos na instrução SQL (java.sql.PrepareStatement).
     * @param ps (java.sql.PreparedStatement).
     * @param bemTributavelVo  Bem Tributável(Value Object).
     * @throws ObjetoObrigatorioException
     * @throws SQLException
     */
    private void preparedStatementDeleteBemTributavel(final PreparedStatement ps, final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
            SQLException, ParametroObrigatorioException
    {
       Validador.validaObjeto(ps);
       Validador.validaObjeto(bemTributavelVo);
       int contador = 0;
       //CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL ,
       if (Validador.isNumericoValido(bemTributavelVo.getCodigo()))
       {
          ps.setLong(++contador, bemTributavelVo.getCodigo());
       }
       else
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_CODIGO);
       }
    }
 }
