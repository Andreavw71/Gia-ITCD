 /**
  * Ábaco Tecnologia de Informação - LTDA
  * Arquivo: BemTributavelQDao.java
  * Revisão:
  * Data revisão:
  * $Id: BemTributavelQDao.java,v 1.3 2009/03/02 13:55:15 ricardo.moraes Exp $
  */
 package br.gov.mt.sefaz.itc.model.generico.bemtributavel;

 import br.com.abaco.util.Validador;
 import br.com.abaco.util.exceptions.ConsultaException;
 import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

 import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
 import br.gov.mt.sefaz.itc.model.tabelabasica.bem.BemVo;
 import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
 import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
 import br.gov.mt.sefaz.itc.util.dominio.DomnTipoUsuario;
 import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBemTributavel;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
 import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;

 import java.util.ArrayList;
 import java.util.Collection;


 /**
  * Classe de acesso a dados (Data Access Object).
  * @author Leandro Dorileo
  * @version $Revision: 1.3 $
  */
 public class BemTributavelQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposBemTributavel
 {
    /**
     * Construtor da classe.
     * @param conexao objeto de conexão com o banco de dados.
     * @implemented by Marlo Eichenberg Motta
     */
    public BemTributavelQDao(Connection conexao)
    {
       super(conexao);
    }

    /**
     * Metodo responsavel por criar um BemTributavelVo apartir de um ResultSet.
     * @param (java.sql.ResultSet).
     * @param bemTributavelVo Bem Tributável VO (Value Object).
     * @throws ObjetoObrigatorioException
     * @throws SQLException
     * @implemented by Leandro Dorileo
     */
    private void getBemTributavel(final ResultSet rs, final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
            SQLException
    {
       Validador.validaObjeto(rs);
       Validador.validaObjeto(bemTributavelVo);
       bemTributavelVo.setCodigo(rs.getLong(CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL));
       bemTributavelVo.setDescricaoBemTributavel(rs.getString(CAMPO_DESCRICAO_BEM_TRIBUTAVEL));
       bemTributavelVo.setValorMercado(rs.getDouble(CAMPO_VALOR_MERCADO));
       //Campo para evitar ser perdido o valor arbitrado depois de realizar o serviço
       bemTributavelVo.setValorArbitradoAux(rs.getDouble(CAMPO_VALOR_MERCADO));
       bemTributavelVo.setIsencaoPrevista(new DomnSimNao(rs.getInt(CAMPO_ISENCAO_PREVISTA)));
       bemTributavelVo.setBemParticular(new DomnSimNao(rs.getInt(CAMPO_FLAG_BEM_PARTICULAR)));
       bemTributavelVo.setTipoUsuario(new DomnTipoUsuario(rs.getInt(CAMPO_TIPO_USUARIO_INCLUSAO)));
       bemTributavelVo.getGiaITCDVo().setCodigo(rs.getInt(CAMPO_ITCTB14_CODIGO_ITCD));
       bemTributavelVo.setStatusBemTributavel(new DomnAtivoInativo(rs.getInt(CAMPO_STAT_BEM_TRBT)) );
       bemTributavelVo.setBemVo(new BemVo(rs.getLong(CAMPO_ITCTB06_CODIGO_BEM)));
       bemTributavelVo.setValorInformadoContribuinte(rs.getDouble(CAMPO_VALOR_INFORMADO));
       bemTributavelVo.setConcordaComValorArbitrado(new DomnSimNao(rs.getInt(CAMPO_FLAG_CONCORDA_VALOR)));
    }

    /**
     * Método usado para buscar/listar um grupo de registros de Bens Tributáveis.
     * A pesquisa deve ser parametrizada. Caso a consulta não seja parametrizada, este método 
     * retornará todos os Bens Tributávels cadastrados no sistema.
     * A pesquisa parametrizada pode ser: pelo código ou por qualquer outro atributo de Bem Tributável.
     * 
     * @param bemTributavelVo VO de Bem Tributável(Value Object) - usado para passar os critérios da consulta
     * @return BemTributavelVo
     */
    public BemTributavelVo listBemTributavel(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
            ConsultaException
    {
       Validador.validaObjeto(bemTributavelVo);
       PreparedStatement ps = null;
       ResultSet rs = null;
       try
       {
          ps = conn.prepareStatement(getSQLListBemTributavel(bemTributavelVo));
          prepareStatementListBemTributavel(ps, bemTributavelVo);
          Collection listaBemTributavel = new ArrayList();
          for (rs = ps.executeQuery(); rs.next(); )
          {
             BemTributavelVo bemTributavelAtual = new BemTributavelVo();
             getBemTributavel(rs, bemTributavelAtual);
             listaBemTributavel.add(bemTributavelAtual);
          }
          if (Validador.isCollectionValida(listaBemTributavel))
          {
             bemTributavelVo.setCollVO(listaBemTributavel);
          }
       }
       catch (SQLException e)
       {
          e.printStackTrace();
          throw new ConsultaException(MensagemErro.LISTAR_BEM_TRIBUTAVEL);
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
       return bemTributavelVo;
    }

    /**
     * Cria a SQL de listagem de Bem Tributável.
     *
     * @param bemTributavelVo  VO do Bem Tributável (Value Object).
     * @return
     * @implemented by Leandro Dorileo
     */
    private String getSQLListBemTributavel(final BemTributavelVo bemTributavelVo)
    {
       StringBuffer sql = new StringBuffer();
       sql.append(" SELECT BEM." + CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL + " ");
       sql.append(" , BEM." + CAMPO_DESCRICAO_BEM_TRIBUTAVEL + " ");
       sql.append(" , BEM." + CAMPO_ISENCAO_PREVISTA + " ");
       sql.append(" , BEM." + CAMPO_ITCTB14_CODIGO_ITCD + " ");
       sql.append(" , BEM." + CAMPO_VALOR_MERCADO + " ");
       sql.append(" , BEM." + CAMPO_FLAG_BEM_PARTICULAR + " ");
       sql.append(" , BEM." + CAMPO_TIPO_USUARIO_INCLUSAO + " ");
       sql.append(" , BEM.").append(CAMPO_ITCTB06_CODIGO_BEM);
       sql.append(" , BEM.").append(CAMPO_STAT_BEM_TRBT);
       sql.append(" , BEM.").append(CAMPO_VALOR_INFORMADO);
       sql.append(" , BEM.").append(CAMPO_FLAG_CONCORDA_VALOR);
       sql.append(" FROM " + TABELA_GIA_ITCD_BEM_TRIBUTAVEL + " BEM ");
       sql.append(" WHERE 1 = 1 ");
       if (bemTributavelVo != null && bemTributavelVo.isConsultaParametrizada())
       {
          if (Validador.isNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getCodigo()))
          {
             sql.append(" AND BEM." + CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL + " = ? ");
          }
          if (Validador.isStringValida(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getDescricaoBemTributavel()))
          {
             sql.append(" AND UPPER(BEM." + CAMPO_DESCRICAO_BEM_TRIBUTAVEL + ") LIKE (UPPER('%?%'))");
          }
          if (Validador.isDominioNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getIsencaoPrevista()))
          {
             sql.append(" AND BEM." + CAMPO_ISENCAO_PREVISTA + " = ? ");
          }
          if (((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getGiaITCDVo() != null && 
                   Validador.isNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
          {
             sql.append(" AND BEM." + CAMPO_ITCTB14_CODIGO_ITCD + " = ? ");
          }
          if (Validador.isNumericoValido(bemTributavelVo.getValorMercado()))
          {
             sql.append(" AND BEM." + CAMPO_VALOR_MERCADO + " = ? ");
          }
          if (Validador.isNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getBemVo().getCodigo()))
          {
             sql.append(" AND  BEM." + CAMPO_ITCTB06_CODIGO_BEM + " = ? ");
          }
          if(Validador.isDominioNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getTipoUsuario()))
          {
             sql.append(" AND BEM." + CAMPO_TIPO_USUARIO_INCLUSAO + " = ? ");
          }
          // STATUS BEM TRIBUTAVEL
          if(Validador.isDominioNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getStatusBemTributavel()))
          {
             sql.append(" AND BEM." + CAMPO_STAT_BEM_TRBT + " = ? ");
          }
          if (Validador.isNumericoValido(bemTributavelVo.getValorInformadoContribuinte()))
          {
             sql.append(" AND BEM." + CAMPO_VALOR_INFORMADO + " = ? ");
          }
       }
       sql.append(" ORDER BY BEM." + CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL + "  ");
       return sql.toString();
    }

    /**
     * Método utilizado para adicionar ao PreparedStatement somente valores válidos no
     * momento de listar Bem Tributável.
     *
     * @param ps (java.sql.PreparedStatement).
     * @param bemTributavelVo VO de Bem Tributável (Value Object).
     * @throws ObjetoObrigatorioException
     * @throws SQLException
     * @implemented by Leandro Dorileo
     */
    private void prepareStatementListBemTributavel(final PreparedStatement ps, final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
            SQLException
    {
       Validador.validaObjeto(ps);
       Validador.validaObjeto(bemTributavelVo);
       int contador = 0;
       if (bemTributavelVo.isConsultaParametrizada())
       {
          //CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL
          if (Validador.isNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getCodigo()))
          {
             ps.setLong(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getCodigo());
          }
          //CAMPO_DESCRICAO_BEM_TRIBUTAVEL
          if (Validador.isStringValida(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getDescricaoBemTributavel()))
          {
             ps.setString(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getDescricaoBemTributavel());
          }
          //CAMPO_ISENCAO_PREVISTA
          if (Validador.isDominioNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getIsencaoPrevista()))
          {
             ps.setInt(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getIsencaoPrevista().getValorCorrente());
          }
          //CAMPO_ITCTB14_CODIGO_ITCD
          if (((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getGiaITCDVo() != null && 
                   Validador.isNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
          {
             ps.setLong(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getGiaITCDVo().getCodigo());
          }
          //CAMPO_VALOR_MERCADO
          if (Validador.isNumericoValido(bemTributavelVo.getValorMercado()))
          {
             ps.setDouble(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getValorMercado());
          }
          // BEM
          if (Validador.isNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getBemVo().getCodigo()))
          {
             ps.setLong(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getBemVo().getCodigo());
          }
          // TIPO USUARIO
          if(Validador.isDominioNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getTipoUsuario()))
          {
             ps.setInt(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getTipoUsuario().getValorCorrente());
          }
          // STATUS BEM TRIBUTAVEL
           if(Validador.isDominioNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getStatusBemTributavel()))
           {
              ps.setInt(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getStatusBemTributavel().getValorCorrente());
           }
          //CAMPO_VALOR_INFORMADO
          if (Validador.isNumericoValido(bemTributavelVo.getValorInformadoContribuinte()))
          {
             ps.setDouble(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getValorInformadoContribuinte());
          }
          
       }
    }

    /**
     * @param bemTributavelVo
     * @return BemTributavelVo
     * @implemented by Leandro Dorileo
     */
    public BemTributavelVo findBemTributavel(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
            ConsultaException
    {
       Validador.validaObjeto(bemTributavelVo);
       PreparedStatement ps = null;
       ResultSet rs = null;
       try
       {
          ps = conn.prepareStatement(getSQLFindBemTributavel(bemTributavelVo));
          prepareStatementFindBemTributavel(ps, bemTributavelVo);
          rs = ps.executeQuery();
          if (rs.next())
          {
             getBemTributavel(rs, bemTributavelVo);
          }
       }
       catch (SQLException e)
       {
          e.printStackTrace();
          throw new ConsultaException(MensagemErro.LISTAR_BEM_TRIBUTAVEL);
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
       return bemTributavelVo;
    }

    /**
     * Cria a SQL de Consulta do Bem Tributável.
     *
     * @param bemTributavelVo  VO do Bem Tributável (Value Object).
     * @return
     * @implemented by Leandro Dorileo
     */
    private String getSQLFindBemTributavel(final BemTributavelVo bemTributavelVo)
    {
       StringBuffer sql = new StringBuffer();
       sql.append(" SELECT BEM." + CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL + " ");
       sql.append(" , BEM." + CAMPO_DESCRICAO_BEM_TRIBUTAVEL + " ");
       sql.append(" , BEM." + CAMPO_ISENCAO_PREVISTA + " ");
       sql.append(" , BEM." + CAMPO_ITCTB14_CODIGO_ITCD + " ");
       sql.append(" , BEM." + CAMPO_VALOR_MERCADO + " ");
       sql.append(" , BEM." + CAMPO_ITCTB06_CODIGO_BEM + " ");
       sql.append(" , BEM." + CAMPO_FLAG_BEM_PARTICULAR + " ");
       sql.append(" , BEM." + CAMPO_TIPO_USUARIO_INCLUSAO + " ");
       sql.append(" , BEM.").append(CAMPO_STAT_BEM_TRBT);
       sql.append(" , BEM.").append(CAMPO_VALOR_INFORMADO);
        sql.append(" , BEM.").append(CAMPO_FLAG_CONCORDA_VALOR);
       sql.append(" FROM " + TABELA_GIA_ITCD_BEM_TRIBUTAVEL + " BEM ");
       sql.append(" WHERE 1 = 1 ");
       if (bemTributavelVo != null && bemTributavelVo.isConsultaParametrizada())
       {
          if (Validador.isNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getCodigo()))
          {
             sql.append(" AND BEM." + CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL + " = ? ");
          }
          if (((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getGiaITCDVo() != null && 
                   Validador.isNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
          {
             sql.append(" AND " + CAMPO_ITCTB14_CODIGO_ITCD + " = ? ");
          }
          if (Validador.isStringValida(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getDescricaoBemTributavel()))
          {
             sql.append(" AND " + CAMPO_DESCRICAO_BEM_TRIBUTAVEL + " = ? ");
          }
          if (Validador.isNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getBemVo().getCodigo()))
          {
             sql.append(" AND " + CAMPO_ITCTB06_CODIGO_BEM + " = ? ");
          }
          if (Validador.isDominioNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getTipoUsuario()))
          {
             sql.append(" AND " + CAMPO_TIPO_USUARIO_INCLUSAO + " = ?");
          }
          // STATUS BEM TRIBUTAVEL
          if (Validador.isDominioNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getStatusBemTributavel()))
          {
             sql.append(" AND " + CAMPO_STAT_BEM_TRBT + " = ?");
          }
       }
       return sql.toString();
    }

    /**
     * Método utilizado para adicionar ao PreparedStatement somente valores válidos no
     * momento de listar Bem Tributável.
     *
     * @param ps (java.sql.PreparedStatement).
     * @param bemTributavelVo VO de Bem Tributável (Value Object).
     * @throws ObjetoObrigatorioException
     * @throws SQLException
     * @implemented by Leandro Dorileo
     */
    private void prepareStatementFindBemTributavel(final PreparedStatement ps, final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
            SQLException
    {
       Validador.validaObjeto(ps);
       Validador.validaObjeto(bemTributavelVo);
       int contador = 0;
       if (bemTributavelVo.isConsultaParametrizada())
       {
          //CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL
          if (Validador.isNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getCodigo()))
          {
             ps.setLong(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getCodigo());
          }
          //CAMPO_ITCTB14_CODIGO_ITCD
          if (((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getGiaITCDVo() != null && 
                   Validador.isNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getGiaITCDVo().getCodigo()))
          {
             ps.setLong(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getGiaITCDVo().getCodigo());
          }
          //CAMPO_DESCRICAO_BEM_TRIBUTAVEL
          if (Validador.isStringValida(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getDescricaoBemTributavel()))
          {
             ps.setString(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getDescricaoBemTributavel());
          }
          // BEM
          if (Validador.isNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getBemVo().getCodigo()))
          {
             ps.setLong(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getBemVo().getCodigo());
          }
          //TIPO USUARIO
          if(Validador.isDominioNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getTipoUsuario()))
          {
             ps.setInt(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getTipoUsuario().getValorCorrente());
          }
          // STATUS BEM TRIBUTAVEL
          if(Validador.isDominioNumericoValido(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getStatusBemTributavel()))
          {
             ps.setInt(++contador, ((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getStatusBemTributavel().getValorCorrente());
          }
       }
    }
 }
