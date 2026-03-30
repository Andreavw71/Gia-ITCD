package br.gov.mt.sefaz.itc.model.generico.giaitcd;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.relatorio.parametrorelatorio.ParametroRelatorioVo;
import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.PedidoRelatorioVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposNaturezaOperacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe para consulta de GIA-ITCD
 * @author Daniel Balieiro
 * @implemented by Daniel Balieiro
 */
public class GIAITCDQDao extends AbstractDao implements CamposGIAITCD, TabelasITC, SequencesITC
{
   /**
    * Construtor que recebe a conexão com o banco de dados
    * @param conexao
    * @implemented by Daniel Balieiro
    */
   public GIAITCDQDao(Connection conexao)
   {
      super(conexao);
   }

   /**
    * Método que monta o GIA-ITCD Vo com os dados do Result Set
    * @param rs
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws SQLException
    * @implemented by Daniel Balieiro
    */
   private GIAITCDVo getGIAITCD(final ResultSet rs, final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, SQLException
   {
      Validador.validaObjeto(rs);
      Validador.validaObjeto(giaITCDVo);
      giaITCDVo.setCodigo(rs.getLong(CAMPO_CODIGO_GIA_ITCD));
      giaITCDVo.getResponsavelVo().setNumrContribuinte(new Long(rs.getLong(CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL)));
      giaITCDVo.getProcuradorVo().setNumrContribuinte(new Long(rs.getLong(CAMPO_ACCTB01_NUMERO_PESSOA_PROCURADOR)));
      giaITCDVo.getNaturezaOperacaoVo().setCodigo(rs.getLong(CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO));
      giaITCDVo.getParametroLegislacaoVo().setCodigo(rs.getLong(CAMPO_ITCTB09_CODIGO_LEGISLACAO));
      giaITCDVo.setDataCriacao(rs.getDate(CAMPO_DATA_CRIACAO));
      giaITCDVo.getTipoGIA().setValorCorrente(rs.getInt(CAMPO_TIPO_ITCD));
      giaITCDVo.setSenha(rs.getString(CAMPO_INFO_SENHA));
      giaITCDVo.setCodigoAutenticidade(rs.getString(CAMPO_CODIGO_AUTENTICIDADE));
      giaITCDVo.setValorTotalBensDeclarados(rs.getDouble(CAMPO_VALOR_TOTAL_BENS));
      giaITCDVo.setValorUPF(rs.getDouble(CAMPO_VALOR_UPF));
      giaITCDVo.setValorCalculoDemonstrativo(rs.getDouble(CAMPO_VALOR_CALCULO_DEMONSTRATIVO));
      giaITCDVo.setValorITCD(rs.getDouble(CAMPO_VALOR_ITCD));
      giaITCDVo.setValorITCDOriginal(giaITCDVo.getValorITCD());
      giaITCDVo.setValorRecolhimento(rs.getDouble(CAMPO_VALOR_TOTAL_RECOLHER));
      giaITCDVo.setValorTSE(rs.getDouble(CAMPO_VALOR_TSE));
      MunicipioIntegracaoVo municipio = new MunicipioIntegracaoVo();
      municipio.setCodgMunicipio(new Integer(rs.getInt(CAMPO_MUNICIPIO_PROTOCOLAR)));
      giaITCDVo.setMunicipioProtocolar(municipio);
      giaITCDVo.setPossuiCPF(new DomnSimNao(rs.getInt(CAMPO_POSSUI_CPF)));
      giaITCDVo.setNumrDeclaracaoFatoGerador(rs.getLong(CAMPO_NUMR_DECL_FATO_GERADOR));
      giaITCDVo.setNumrDeclaracaoIsencao(rs.getLong(CAMPO_NUMR_DECL_ISENCAO));
      giaITCDVo.getServidorSefazResponsavelAlteracaoVo().setNumrMatricula(new Long(rs.getLong(CAMPO_SERVIDOR_RESPONSAVEL_ALTERACAO)));
      //		giaITCDVo.setJustificativaAlteracao(rs.getString(CAMPO_JUSTIFICATIVA_ALTERACAO));
      giaITCDVo.setDataAtualizacaoBD(rs.getDate(CAMPO_DATA_ATUALIZACAO_BD));
      giaITCDVo.setValorITCDRetificado(rs.getDouble(CAMPO_VALOR_ITCD_RETIFICADO));
      giaITCDVo.setNumeroVersaoGIAITCD(new DomnVersaoGIAITCD(rs.getInt(CAMPO_NUMERO_VERSAO_GIAITCD)));
      giaITCDVo.setTipoProtocoloGIA(new DomnTipoProtocolo(rs.getInt(CAMPO_TIPO_PROTOCOLO_GIA)));
      return giaITCDVo;
   }

   /**
    * Método que monta o GIA-ITCD Vo com os dados do Result Set
    * @param rs
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws SQLException
    * @implemented by Dherkyan Ribeiro
    */
   private GIAITCDVo getGIAITCDCodigo(final ResultSet rs, final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(rs);
      Validador.validaObjeto(giaITCDVo);
      giaITCDVo.setCodigo(rs.getLong(CAMPO_CODIGO_GIA_ITCD));
      return giaITCDVo;
   }

   /**
    * Monta a GIA-ITCD com os valores do Result Set
    * @param rs
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws SQLException
    * @implemented by Daniel Balieiro
    */
   private GIAITCDVo getGIAITCDPorResponsavel(final ResultSet rs, final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      getGIAITCD(rs, giaITCDVo);
      giaITCDVo.getNaturezaOperacaoVo().setCodigo(rs.getLong(CamposNaturezaOperacao.CAMPO_CODIGO_NATUREZA_OPERACAO));
      giaITCDVo.getNaturezaOperacaoVo().setTipoProcesso(new DomnTipoProcesso(rs.getInt(CamposNaturezaOperacao.CAMPO_TIPO_PROCESSO)));
      giaITCDVo.getResponsavelVo().setNomeContribuinte(rs.getString("NOME_PESSOA"));
      giaITCDVo.getStatusVo().setCodigo(rs.getLong(CamposStatusGIAITCD.CAMPO_CODIGO_STATUS_ITCD));
      giaITCDVo.getStatusVo().setStatusGIAITCD(new DomnStatusGIAITCD(rs.getInt(CamposStatusGIAITCD.CAMPO_STAT_ITCD)));
      return giaITCDVo;
   }

   /**
    * <b>Objetivo:</b> Este método tem por objetivo  a montagem do SQL
    * utilizando na clausula WHERE os atributos preenchidos no VO.
    * 
    * 
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ClassCastException
    * @implemented by Daniel Balieiro
    */
   private String getSQLFindGIAITCD(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, ClassCastException
   {
      Validador.validaObjeto(giaITCDVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT GIA.").append(CAMPO_CODIGO_GIA_ITCD);
      sql.append(" , GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL);
      sql.append(" , GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_PROCURADOR);
      sql.append(" , GIA.").append(CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO);
      sql.append(" , GIA.").append(CAMPO_ITCTB09_CODIGO_LEGISLACAO);
      sql.append(" , GIA.").append(CAMPO_DATA_CRIACAO);
      sql.append(" , GIA.").append(CAMPO_TIPO_ITCD);
      sql.append(" , GIA.").append(CAMPO_INFO_SENHA);
      sql.append(" , GIA.").append(CAMPO_CODIGO_AUTENTICIDADE);
      sql.append(" , GIA.").append(CAMPO_VALOR_TOTAL_BENS);
      sql.append(" , GIA.").append(CAMPO_VALOR_UPF);
      sql.append(" , GIA.").append(CAMPO_VALOR_CALCULO_DEMONSTRATIVO);
      sql.append(" , GIA.").append(CAMPO_VALOR_ITCD);
      sql.append(" , GIA.").append(CAMPO_VALOR_TOTAL_RECOLHER);
      sql.append(" , GIA.").append(CAMPO_VALOR_TSE);
      sql.append(" , GIA.").append(CAMPO_MUNICIPIO_PROTOCOLAR);
      sql.append(" , GIA.").append(CAMPO_POSSUI_CPF);
      sql.append(" , GIA.").append(CAMPO_NUMR_DECL_FATO_GERADOR);
      sql.append(" , GIA.").append(CAMPO_NUMR_DECL_ISENCAO);
      sql.append(" , GIA.").append(CAMPO_SERVIDOR_RESPONSAVEL_ALTERACAO);
      //		sql.append(" , GIA.").append(CAMPO_JUSTIFICATIVA_ALTERACAO);
      sql.append(" , GIA.").append(CAMPO_DATA_ATUALIZACAO_BD);
      sql.append(" , GIA.").append(CAMPO_VALOR_ITCD_RETIFICADO);
      sql.append(" , GIA.").append(CAMPO_NUMERO_VERSAO_GIAITCD);
      sql.append(" , GIA.").append(CAMPO_TIPO_PROTOCOLO_GIA);
      sql.append(" FROM ").append(TABELA_GIA_ITCD).append(" GIA ");
      sql.append(" WHERE 1 = 1 ");
      if (giaITCDVo.isConsultaParametrizada())
      {
         // CODIGO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getCodigo()))
         {
            sql.append("   AND GIA.").append(CAMPO_CODIGO_GIA_ITCD).append(" = ? ");
         }
         // RESPONSAVEL
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getResponsavelVo().getNumrContribuinte()))
         {
            sql.append("   AND GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL).append(" = ? ");
         }
         // PROCURADOR
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getProcuradorVo().getNumrContribuinte()))
         {
            sql.append("   AND GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_PROCURADOR).append(" = ? ");
         }
         // NATUREZA OPERACAO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNaturezaOperacaoVo().getCodigo()))
         {
            sql.append("   AND GIA.").append(CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO).append(" = ? ");
         }
         // PARAMETRO LEGISLACAO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getParametroLegislacaoVo().getCodigo()))
         {
            sql.append("   AND GIA.").append(CAMPO_ITCTB09_CODIGO_LEGISLACAO).append(" = ? ");
         }
         // DATA CRIACAO
         if (Validador.isDataValida(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getDataCriacao()))
         {
            sql.append("   AND GIA.").append(CAMPO_DATA_CRIACAO).append(" = ? ");
         }
         // TIPO GIA
         if (Validador.isDominioNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getTipoGIA()))
         {
            sql.append("   AND GIA.").append(CAMPO_TIPO_ITCD).append(" = ? ");
         }
         // SENHA
         if (Validador.isStringValida(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getSenha()))
         {
            sql.append("   AND GIA.").append(CAMPO_INFO_SENHA).append(" = ? ");
         }
         // CODIGO AUTENTICIDADE
         if (Validador.isStringValida(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getCodigoAutenticidade()))
         {
            sql.append("   AND GIA.").append(CAMPO_CODIGO_AUTENTICIDADE).append(" = ? ");
         }
         // VALOR TOTAL DE BENS
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorTotalBensDeclarados()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_TOTAL_BENS).append(" = ? ");
         }
         // VALOR UPF
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorUPF()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_UPF).append(" = ? ");
         }
         // VALOR CALCULO DEMONSTRATIVO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorCalculoDemonstrativo()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_CALCULO_DEMONSTRATIVO).append(" = ? ");
         }
         // VALOR ITCD
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorITCD()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_ITCD).append(" = ? ");
         }
         // VALOR TOTAL RECOLHER
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorRecolhimento()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_TOTAL_RECOLHER).append(" = ? ");
         }
         // VALOR TSE
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorTSE()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_TSE).append(" = ? ");
         }
         // MUNICIPIO PROTOCOLAR
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getMunicipioProtocolar().getCodgMunicipio()))
         {
            sql.append("   AND GIA.").append(CAMPO_MUNICIPIO_PROTOCOLAR).append(" = ? ");
         }
         // POSSUI CPF
         if (Validador.isDominioNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getPossuiCPF()))
         {
            sql.append("   AND GIA.").append(CAMPO_POSSUI_CPF).append(" = ? ");
         }
         // CAMPO_NUMR_DECL_FATO_GERADOR
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNumrDeclaracaoFatoGerador()))
         {
            sql.append("   AND GIA.").append(CAMPO_NUMR_DECL_FATO_GERADOR).append(" = ? ");
         }
         // CAMPO_NUMR_DECL_ISENCAO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNumrDeclaracaoIsencao()))
         {
            sql.append("   AND GIA.").append(CAMPO_NUMR_DECL_ISENCAO).append(" = ? ");
         }
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getServidorSefazResponsavelAlteracaoVo().getNumrMatricula()))
         {
            sql.append("   AND GIA.").append(CAMPO_SERVIDOR_RESPONSAVEL_ALTERACAO).append(" = ? ");
         }
         //CAMPO_NUMERO_VERSAO_GIAITCD
         if (Validador.isDominioNumericoValido(giaITCDVo.getParametroConsulta().getNumeroVersaoGIAITCD()))
         {
            sql.append("   AND GIA.").append(CAMPO_NUMERO_VERSAO_GIAITCD).append(" = ? ");
         }
         //CAMPO_TIPO_PROTOCOLO_GIA
         if (Validador.isDominioNumericoValido(giaITCDVo.getParametroConsulta().getTipoProtocoloGIA()))
         {
            sql.append("   AND GIA.").append(CAMPO_TIPO_PROTOCOLO_GIA).append(" = ? ");
         }
      }
      sql.append(" ORDER BY GIA.").append(CAMPO_CODIGO_GIA_ITCD);
      return sql.toString();
   }

   /**
    * <b>Objetivo:</b>
    * 
    * Efetua a consulta de uma GIA-ITCD
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @implemented by Daniel Balieiro
    */
   public GIAITCDVo findGIAITCD(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.validaObjeto(giaITCDVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindGIAITCD(giaITCDVo));
         prepareStatementFindGIAITCD(ps, giaITCDVo);
         rs = ps.executeQuery();
         if (rs.next())
         {
            getGIAITCD(rs, giaITCDVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_TEMPORARIO);
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
      return giaITCDVo;
   }

   /**
    * Monta o Prepare Statement com os valores válidos da GIA-ITCD para consulta
    * @param ps
    * @param giaITCDVo
    * @throws ObjetoObrigatorioException
    * @throws SQLException
    * @implemented by Daniel Balieiro
    */
   private void prepareStatementFindGIAITCD(final PreparedStatement ps, final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(giaITCDVo);
      int contador = 0;
      if (giaITCDVo.isConsultaParametrizada())
      {
         // CODIGO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getCodigo()))
         {
            ps.setLong(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getCodigo());
         }
         // RESPONSAVEL
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getResponsavelVo().getNumrContribuinte()))
         {
            ps.setLong(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getResponsavelVo().getNumrContribuinte().longValue());
         }
         // PROCURADOR
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getProcuradorVo().getNumrContribuinte()))
         {
            ps.setLong(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getProcuradorVo().getNumrContribuinte().longValue());
         }
         // NATUREZA OPERACAO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNaturezaOperacaoVo().getCodigo()))
         {
            ps.setLong(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNaturezaOperacaoVo().getCodigo());
         }
         // PARAMETRO LEGISLACAO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getParametroLegislacaoVo().getCodigo()))
         {
            ps.setLong(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getParametroLegislacaoVo().getCodigo());
         }
         // DATA CRIACAO
         if (Validador.isDataValida(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getDataCriacao()))
         {
            ps.setDate(++contador, new java.sql.Date(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getDataCriacao().getTime()));
         }
         // TIPO GIA
         if (Validador.isDominioNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getTipoGIA()))
         {
            ps.setInt(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getTipoGIA().getValorCorrente());
         }
         // SENHA
         if (Validador.isStringValida(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getSenha()))
         {
            ps.setString(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getSenha());
         }
         // CODIGO AUTENTICIDADE
         if (Validador.isStringValida(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getCodigoAutenticidade()))
         {
            ps.setString(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getCodigoAutenticidade());
         }
         // VALOR TOTAL DE BENS
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorTotalBensDeclarados()))
         {
            ps.setDouble(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorTotalBensDeclarados());
         }
         // VALOR UPF
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorUPF()))
         {
            ps.setDouble(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorUPF());
         }
         // VALOR CALCULO DEMONSTRATIVO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorCalculoDemonstrativo()))
         {
            ps.setDouble(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorCalculoDemonstrativo());
         }
         // VALOR ITCD
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorITCD()))
         {
            ps.setDouble(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorITCD());
         }
         // VALOR TOTAL RECOLHER
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorRecolhimento()))
         {
            ps.setDouble(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorRecolhimento());
         }
         // VALOR TSE
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorTSE()))
         {
            ps.setDouble(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorTSE());
         }
         // MUNICIPIO PROTOCOLAR
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getMunicipioProtocolar().getCodgMunicipio()))
         {
            ps.setInt(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getMunicipioProtocolar().getCodgMunicipio().intValue());
         }
         // POSSUI CPF 
         if (Validador.isDominioNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getPossuiCPF()))
         {
            ps.setInt(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getPossuiCPF().getValorCorrente());
         }
         // NUMR_DECL_FATO_GERADOR
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNumrDeclaracaoFatoGerador()))
         {
            ps.setLong(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNumrDeclaracaoFatoGerador());
         }
         // NUMR_DECL_ISENCAO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNumrDeclaracaoIsencao()))
         {
            ps.setLong(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNumrDeclaracaoIsencao());
         }
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getServidorSefazResponsavelAlteracaoVo().getNumrMatricula()))
         {
            ps.setLong(++contador, ((GIAITCDVo) giaITCDVo.getParametroConsulta()).getServidorSefazResponsavelAlteracaoVo().getNumrMatricula().longValue());
         }
         //CAMPO_NUMERO_VERSAO_GIAITCD
         if (Validador.isDominioNumericoValido(giaITCDVo.getParametroConsulta().getNumeroVersaoGIAITCD()))
         {
            ps.setInt(++contador, (giaITCDVo.getParametroConsulta().getNumeroVersaoGIAITCD().getValorCorrente()));
         }
         //CAMPO_TIPO_PROTOCOLO_GIA
         if (Validador.isDominioNumericoValido(giaITCDVo.getParametroConsulta().getTipoProtocoloGIA()))
         {
            ps.setInt(++contador, (giaITCDVo.getParametroConsulta().getTipoProtocoloGIA().getValorCorrente()));
         }
      }
   }

   /**
    * Efetua uma listagem de GIA-ITCD
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @implemented by Daniel Balieiro
    */
   public GIAITCDVo listGIAITCD(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.validaObjeto(giaITCDVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindGIAITCD(giaITCDVo));
         prepareStatementFindGIAITCD(ps, giaITCDVo);
         rs = ps.executeQuery();
         while (rs.next())
         {
            GIAITCDVo giaITCDAtualVo = new GIAITCDVo();
            getGIAITCD(rs, giaITCDAtualVo);
            giaITCDVo.getCollVO().add(giaITCDAtualVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD_TEMPORARIO);
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
      return giaITCDVo;
   }

   /**
    * Efetua uma listagem de GIA-ITCD por Status
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @implemented by Daniel Balieiro
    */
   public GIAITCDVo listGIAITCDPorStatus(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.validaObjeto(giaITCDVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLListGIAITCDStatus(giaITCDVo));
         rs = ps.executeQuery();
         while (rs.next())
         {
            GIAITCDVo giaITCDAtualVo = new GIAITCDVo();
            getGIAITCD(rs, giaITCDAtualVo);
            giaITCDVo.getCollVO().add(giaITCDAtualVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD);
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
      return giaITCDVo;
   }

   /**
    * Monta o SQL de Listagem de GIA-ITCD por Status
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ClassCastException
    * @implemented by Daniel Balieiro
    */
   private String getSQLListGIAITCDStatus(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, ClassCastException
   {
      Validador.validaObjeto(giaITCDVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT GIA.").append(CAMPO_CODIGO_GIA_ITCD);
      sql.append(" , GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL);
      sql.append(" , GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_PROCURADOR);
      sql.append(" , GIA.").append(CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO);
      sql.append(" , GIA.").append(CAMPO_ITCTB09_CODIGO_LEGISLACAO);
      sql.append(" , GIA.").append(CAMPO_DATA_CRIACAO);
      sql.append(" , GIA.").append(CAMPO_TIPO_ITCD);
      sql.append(" , GIA.").append(CAMPO_INFO_SENHA);
      sql.append(" , GIA.").append(CAMPO_CODIGO_AUTENTICIDADE);
      sql.append(" , GIA.").append(CAMPO_VALOR_TOTAL_BENS);
      sql.append(" , GIA.").append(CAMPO_VALOR_UPF);
      sql.append(" , GIA.").append(CAMPO_VALOR_CALCULO_DEMONSTRATIVO);
      sql.append(" , GIA.").append(CAMPO_VALOR_ITCD);
      sql.append(" , GIA.").append(CAMPO_VALOR_ITCD_RETIFICADO);
      sql.append(" , GIA.").append(CAMPO_VALOR_TOTAL_RECOLHER);
      sql.append(" , GIA.").append(CAMPO_VALOR_TSE);
      sql.append(" , GIA.").append(CAMPO_MUNICIPIO_PROTOCOLAR);
      sql.append(" , GIA.").append(CAMPO_POSSUI_CPF);
      sql.append(" , GIA.").append(CAMPO_NUMR_DECL_FATO_GERADOR);
      sql.append(" , GIA.").append(CAMPO_NUMR_DECL_ISENCAO);
      sql.append(" , GIA.").append(CAMPO_SERVIDOR_RESPONSAVEL_ALTERACAO);
      //		sql.append(" , GIA.").append(CAMPO_JUSTIFICATIVA_ALTERACAO);
      sql.append(" , GIA.").append(CAMPO_DATA_ATUALIZACAO_BD);
      sql.append(" FROM ").append(TABELA_GIA_ITCD).append(" GIA ,  ");
      sql.append(TABELA_STATUS_GIA_ITCD).append(" STATUS ");
      sql.append(" WHERE GIA.").append(CAMPO_CODIGO_GIA_ITCD).append(" = ").append(" STATUS.").append(CamposStatusGIAITCD.CAMPO_ITCTB14_CODIGO_ITCD);
      if (giaITCDVo.isConsultaParametrizada())
      {
         // STATUS GIA
         sql.append(" AND STATUS.").append(CamposStatusGIAITCD.CAMPO_STAT_ITCD).append(" = ").append(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getStatusVo().getStatusGIAITCD().getValorCorrente());
      }
      sql.append(" ORDER BY GIA.").append(CAMPO_CODIGO_GIA_ITCD);
      return sql.toString();
   }

   /**
    * Efetua uma listagem de GIA-ITCD por Responsável
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @implemented by Daniel Balieiro
    */
   public GIAITCDVo listGIAITCDPorCPFResponsavel(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
         ConsultaException
   {
      Validador.validaObjeto(giaITCDVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindGIAITCDAtivasTeste(giaITCDVo));
         prepareStatementFindGIAITCD(ps, giaITCDVo);
         rs = ps.executeQuery();
         while (rs.next())
         {
            GIAITCDVo giaITCDAtualVo = new GIAITCDVo();
            getGIAITCDPorResponsavel(rs, giaITCDAtualVo);
            giaITCDVo.getCollVO().add(giaITCDAtualVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD);
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
      return giaITCDVo;
   }

   /**
    * Monta a SQL de consulta de GIA-ITCD Ativas
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ClassCastException
    * @implemented by Daniel Balieiro
    */
   private String getSQLFindGIAITCDAtivas(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, ClassCastException
   {
      Validador.validaObjeto(giaITCDVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT DISTINCT GIA.").append(CAMPO_CODIGO_GIA_ITCD);
      sql.append(" , GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL);
      sql.append(" , GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_PROCURADOR);
      sql.append(" , GIA.").append(CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO);
      sql.append(" , GIA.").append(CAMPO_ITCTB09_CODIGO_LEGISLACAO);
      sql.append(" , GIA.").append(CAMPO_DATA_CRIACAO);
      sql.append(" , GIA.").append(CAMPO_TIPO_ITCD);
      sql.append(" , GIA.").append(CAMPO_INFO_SENHA);
      sql.append(" , GIA.").append(CAMPO_CODIGO_AUTENTICIDADE);
      sql.append(" , GIA.").append(CAMPO_VALOR_TOTAL_BENS);
      sql.append(" , GIA.").append(CAMPO_VALOR_UPF);
      sql.append(" , GIA.").append(CAMPO_VALOR_CALCULO_DEMONSTRATIVO);
      sql.append(" , GIA.").append(CAMPO_VALOR_ITCD);
      sql.append(" , GIA.").append(CAMPO_VALOR_TOTAL_RECOLHER);
      sql.append(" , GIA.").append(CAMPO_VALOR_TSE);
      sql.append(" , GIA.").append(CAMPO_MUNICIPIO_PROTOCOLAR);
      sql.append(" , GIA.").append(CAMPO_POSSUI_CPF);
      sql.append(" , GIA.").append(CAMPO_NUMR_DECL_FATO_GERADOR);
      sql.append(" , GIA.").append(CAMPO_NUMR_DECL_ISENCAO);
      sql.append(" , GIA.").append(CAMPO_SERVIDOR_RESPONSAVEL_ALTERACAO);
      sql.append(" , GIA.").append(CAMPO_DATA_ATUALIZACAO_BD);
      sql.append(" , GIA.").append(CAMPO_VALOR_ITCD_RETIFICADO);
      sql.append(" , NAT.").append(CamposNaturezaOperacao.CAMPO_CODIGO_NATUREZA_OPERACAO);
      sql.append(" , NAT.").append(CamposNaturezaOperacao.CAMPO_TIPO_PROCESSO);
      sql.append(" , PES.NOME_PESSOA");
      sql.append(" FROM ").append(TABELA_GIA_ITCD).append(" GIA ,  ");
      sql.append(TABELA_STATUS_GIA_ITCD).append(" STATUS , ");
      sql.append(TABELA_NATUREZA_OPERACAO).append(" NAT, ");
      sql.append(" ACCTB01_PESSOA PES ");

      sql.append(" WHERE GIA.").append(CAMPO_CODIGO_GIA_ITCD).append(" = ").append(" STATUS.").append(CamposStatusGIAITCD.CAMPO_ITCTB14_CODIGO_ITCD);
      sql.append(" AND GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL).append(" = ").append(" PES.NUMR_PESSOA ");
      sql.append(" AND GIA.").append(CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO).append(" = ").append(" NAT.").append(CamposNaturezaOperacao.CAMPO_CODIGO_NATUREZA_OPERACAO);
      sql.append(" AND STATUS.").append(CamposStatusGIAITCD.CAMPO_STAT_ITCD).append(" <> ").append(DomnStatusGIAITCD.INATIVO);
      sql.append(" AND STATUS.").append(CamposStatusGIAITCD.CAMPO_CODIGO_STATUS_ITCD);
      sql.append(" = (SELECT MAX(STATUS.").append(CamposStatusGIAITCD.CAMPO_CODIGO_STATUS_ITCD).append(" ) FROM ");
      sql.append(TABELA_STATUS_GIA_ITCD).append(" STATUS WHERE STATUS.").append(CamposStatusGIAITCD.CAMPO_ITCTB14_CODIGO_ITCD).append(" = GIA.");
      sql.append(CAMPO_CODIGO_GIA_ITCD).append(" ) ");
      if (giaITCDVo.isConsultaParametrizada())
      {
         // CODIGO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getCodigo()))
         {
            sql.append("   AND GIA.").append(CAMPO_CODIGO_GIA_ITCD).append(" = ? ");
         }
         // RESPONSAVEL
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getResponsavelVo().getNumrContribuinte()))
         {
            sql.append("   AND GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL).append(" = ? ");
         }
         // PROCURADOR
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getProcuradorVo().getNumrContribuinte()))
         {
            sql.append("   AND GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_PROCURADOR).append(" = ? ");
         }
         // NATUREZA OPERACAO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNaturezaOperacaoVo().getCodigo()))
         {
            sql.append("   AND GIA.").append(CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO).append(" = ? ");
         }
         // PARAMETRO LEGISLACAO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getParametroLegislacaoVo().getCodigo()))
         {
            sql.append("   AND GIA.").append(CAMPO_ITCTB09_CODIGO_LEGISLACAO).append(" = ? ");
         }
         // DATA CRIACAO
         if (Validador.isDataValida(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getDataCriacao()))
         {
            sql.append("   AND GIA.").append(CAMPO_DATA_CRIACAO).append(" = ? ");
         }
         // TIPO GIA
         if (Validador.isDominioNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getTipoGIA()))
         {
            sql.append("   AND GIA.").append(CAMPO_TIPO_ITCD).append(" = ? ");
         }
         // SENHA
         if (Validador.isStringValida(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getSenha()))
         {
            sql.append("   AND GIA.").append(CAMPO_INFO_SENHA).append(" = ? ");
         }
         // CODIGO AUTENTICIDADE
         if (Validador.isStringValida(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getCodigoAutenticidade()))
         {
            sql.append("   AND GIA.").append(CAMPO_CODIGO_AUTENTICIDADE).append(" = ? ");
         }
         // VALOR TOTAL DE BENS
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorTotalBensDeclarados()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_TOTAL_BENS).append(" = ? ");
         }
         // VALOR UPF
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorUPF()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_UPF).append(" = ? ");
         }
         // VALOR CALCULO DEMONSTRATIVO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorCalculoDemonstrativo()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_CALCULO_DEMONSTRATIVO).append(" = ? ");
         }
         // VALOR ITCD
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorITCD()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_ITCD).append(" = ? ");
         }
         // VALOR TOTAL RECOLHER
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorRecolhimento()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_TOTAL_RECOLHER).append(" = ? ");
         }
         // VALOR TSE
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorTSE()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_TSE).append(" = ? ");
         }
         // MUNICIPIO PROTOCOLAR
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getMunicipioProtocolar().getCodgMunicipio()))
         {
            sql.append("   AND GIA.").append(CAMPO_MUNICIPIO_PROTOCOLAR).append(" = ? ");
         }
         // POSSUI CPF
         if (Validador.isDominioNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getPossuiCPF()))
         {
            sql.append("   AND GIA.").append(CAMPO_POSSUI_CPF).append(" = ? ");
         }
         // CAMPO_NUMR_DECL_FATO_GERADOR
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNumrDeclaracaoFatoGerador()))
         {
            sql.append("   AND GIA.").append(CAMPO_NUMR_DECL_FATO_GERADOR).append(" = ? ");
         }
         // CAMPO_NUMR_DECL_ISENCAO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNumrDeclaracaoIsencao()))
         {
            sql.append("   AND GIA.").append(CAMPO_NUMR_DECL_ISENCAO).append(" = ? ");
         }
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getServidorSefazResponsavelAlteracaoVo().getNumrMatricula()))
         {
            sql.append("   AND GIA.").append(CAMPO_SERVIDOR_RESPONSAVEL_ALTERACAO).append(" = ? ");
         }
      }
      sql.append(" ORDER BY GIA.").append(CAMPO_CODIGO_GIA_ITCD);
      return sql.toString();
   }

   private String getSQLFindGIAITCDAtivasTeste(final GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
         ClassCastException
   {
      Validador.validaObjeto(giaITCDVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT DISTINCT GIA.").append(CAMPO_CODIGO_GIA_ITCD);
      sql.append(" , GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL);
      sql.append(" , GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_PROCURADOR);
      sql.append(" , GIA.").append(CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO);
      sql.append(" , GIA.").append(CAMPO_ITCTB09_CODIGO_LEGISLACAO);
      sql.append(" , GIA.").append(CAMPO_DATA_CRIACAO);
      sql.append(" , GIA.").append(CAMPO_TIPO_ITCD);
      sql.append(" , GIA.").append(CAMPO_INFO_SENHA);
      sql.append(" , GIA.").append(CAMPO_CODIGO_AUTENTICIDADE);
      sql.append(" , GIA.").append(CAMPO_VALOR_TOTAL_BENS);
      sql.append(" , GIA.").append(CAMPO_VALOR_UPF);
      sql.append(" , GIA.").append(CAMPO_VALOR_CALCULO_DEMONSTRATIVO);
      sql.append(" , GIA.").append(CAMPO_VALOR_ITCD);
      sql.append(" , GIA.").append(CAMPO_VALOR_TOTAL_RECOLHER);
      sql.append(" , GIA.").append(CAMPO_VALOR_TSE);
      sql.append(" , GIA.").append(CAMPO_MUNICIPIO_PROTOCOLAR);
      sql.append(" , GIA.").append(CAMPO_POSSUI_CPF);
      sql.append(" , GIA.").append(CAMPO_NUMR_DECL_FATO_GERADOR);
      sql.append(" , GIA.").append(CAMPO_NUMR_DECL_ISENCAO);
      sql.append(" , GIA.").append(CAMPO_SERVIDOR_RESPONSAVEL_ALTERACAO);
      //		sql.append(" , GIA.").append(CAMPO_JUSTIFICATIVA_ALTERACAO);		
      sql.append(" , GIA.").append(CAMPO_DATA_ATUALIZACAO_BD);
      sql.append(" , GIA.").append(CAMPO_VALOR_ITCD_RETIFICADO);
      sql.append(" , GIA.").append(CAMPO_NUMERO_VERSAO_GIAITCD);
      sql.append(" , GIA.").append(CAMPO_TIPO_PROTOCOLO_GIA);
      sql.append(" , NAT.").append(CamposNaturezaOperacao.CAMPO_CODIGO_NATUREZA_OPERACAO);
      sql.append(" , NAT.").append(CamposNaturezaOperacao.CAMPO_TIPO_PROCESSO);
      sql.append(" , STATUS.").append(CamposStatusGIAITCD.CAMPO_CODIGO_STATUS_ITCD);
      sql.append(" , STATUS.").append(CamposStatusGIAITCD.CAMPO_STAT_ITCD);
      sql.append(" , PES.NOME_PESSOA");
      sql.append(" FROM ").append(TABELA_GIA_ITCD).append(" GIA ");
      sql.append(" INNER JOIN " + TABELA_STATUS_GIA_ITCD + " STATUS ");
      sql.append(" ON STATUS." + CamposStatusGIAITCD.CAMPO_ITCTB14_CODIGO_ITCD + " = GIA." + CAMPO_CODIGO_GIA_ITCD);
      sql.append(" INNER JOIN " + TABELA_NATUREZA_OPERACAO + " NAT ");
      sql.append(" ON NAT." + CamposNaturezaOperacao.CAMPO_CODIGO_NATUREZA_OPERACAO + " = GIA." + 
            CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO);
      sql.append(" INNER JOIN ACCTB01_PESSOA PES");
      sql.append(" ON PES.NUMR_PESSOA = GIA." + CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL);
      sql.append(" WHERE 1=1 ");
      if (giaITCDVo.isConsultaParametrizada())
      {
         sql.append(" AND STATUS." + CamposStatusGIAITCD.CAMPO_CODIGO_STATUS_ITCD + " = (");
         sql.append(" SELECT MAX(STAT." + CamposStatusGIAITCD.CAMPO_CODIGO_STATUS_ITCD + ") FROM ");
         sql.append(TABELA_STATUS_GIA_ITCD + " STAT ");
         sql.append(" WHERE STAT." + CamposStatusGIAITCD.CAMPO_ITCTB14_CODIGO_ITCD + " = GIA." + CAMPO_CODIGO_GIA_ITCD + 
               ") ");
         // CODIGO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getCodigo()))
         {
            sql.append("   AND GIA.").append(CAMPO_CODIGO_GIA_ITCD).append(" = ? ");
         }
         // RESPONSAVEL
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getResponsavelVo().getNumrContribuinte()))
         {
            sql.append("   AND GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL).append(" = ? ");
         }
         // PROCURADOR
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getProcuradorVo().getNumrContribuinte()))
         {
            sql.append("   AND GIA.").append(CAMPO_ACCTB01_NUMERO_PESSOA_PROCURADOR).append(" = ? ");
         }
         // NATUREZA OPERACAO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNaturezaOperacaoVo().getCodigo()))
         {
            sql.append("   AND GIA.").append(CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO).append(" = ? ");
         }
         // PARAMETRO LEGISLACAO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getParametroLegislacaoVo().getCodigo()))
         {
            sql.append("   AND GIA.").append(CAMPO_ITCTB09_CODIGO_LEGISLACAO).append(" = ? ");
         }
         // DATA CRIACAO
         if (Validador.isDataValida(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getDataCriacao()))
         {
            sql.append("   AND GIA.").append(CAMPO_DATA_CRIACAO).append(" = ? ");
         }
         // TIPO GIA
         if (Validador.isDominioNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getTipoGIA()))
         {
            sql.append("   AND GIA.").append(CAMPO_TIPO_ITCD).append(" = ? ");
         }
         // SENHA
         if (Validador.isStringValida(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getSenha()))
         {
            sql.append("   AND GIA.").append(CAMPO_INFO_SENHA).append(" = ? ");
         }
         // CODIGO AUTENTICIDADE
         if (Validador.isStringValida(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getCodigoAutenticidade()))
         {
            sql.append("   AND GIA.").append(CAMPO_CODIGO_AUTENTICIDADE).append(" = ? ");
         }
         // VALOR TOTAL DE BENS
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorTotalBensDeclarados()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_TOTAL_BENS).append(" = ? ");
         }
         // VALOR UPF
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorUPF()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_UPF).append(" = ? ");
         }
         // VALOR CALCULO DEMONSTRATIVO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorCalculoDemonstrativo()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_CALCULO_DEMONSTRATIVO).append(" = ? ");
         }
         // VALOR ITCD
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorITCD()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_ITCD).append(" = ? ");
         }
         // VALOR TOTAL RECOLHER
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorRecolhimento()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_TOTAL_RECOLHER).append(" = ? ");
         }
         // VALOR TSE
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getValorTSE()))
         {
            sql.append("   AND GIA.").append(CAMPO_VALOR_TSE).append(" = ? ");
         }
         // MUNICIPIO PROTOCOLAR
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getMunicipioProtocolar().getCodgMunicipio()))
         {
            sql.append("   AND GIA.").append(CAMPO_MUNICIPIO_PROTOCOLAR).append(" = ? ");
         }
         // POSSUI CPF
         if (Validador.isDominioNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getPossuiCPF()))
         {
            sql.append("   AND GIA.").append(CAMPO_POSSUI_CPF).append(" = ? ");
         }
         // CAMPO_NUMR_DECL_FATO_GERADOR
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNumrDeclaracaoFatoGerador()))
         {
            sql.append("   AND GIA.").append(CAMPO_NUMR_DECL_FATO_GERADOR).append(" = ? ");
         }
         // CAMPO_NUMR_DECL_ISENCAO
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getNumrDeclaracaoIsencao()))
         {
            sql.append("   AND GIA.").append(CAMPO_NUMR_DECL_ISENCAO).append(" = ? ");
         }
         if (Validador.isNumericoValido(((GIAITCDVo) giaITCDVo.getParametroConsulta()).getServidorSefazResponsavelAlteracaoVo().getNumrMatricula()))
         {
            sql.append("   AND GIA.").append(CAMPO_SERVIDOR_RESPONSAVEL_ALTERACAO).append(" = ? ");
         }

      }
      sql.append(" ORDER BY GIA.").append(CAMPO_CODIGO_GIA_ITCD);
      //System.out.println("SQL Class - GIAITCDQDao.listGIAITCDPorCPFResponsavel: "+sql.toString());
      return sql.toString();
   }

   // ----------------------------- SQL RELATORIO VALOR BENEFICIARIO APOS AVALIACAO-------------------------------------------

   /**
    * @param giaITCDVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ClassCastException
    * @implemented by Dherkyan Ribeiro
    */
   public GIAITCDVo listarCodigoGiaRelatorioValorBeneficiarioAposAValiacao(final GIAITCDVo giaITCDVo, final PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         ConsultaException
   {
      Validador.validaObjeto(giaITCDVo);
      Validador.validaObjeto(pedidoRelatorioVo);
      PreparedStatement ps = null;
      Statement stmt = null;
      ResultSet rs = null;
      try
      {
         System.out.println("Versão de publicação:  1.7");
         
         ps = conn.prepareStatement(getSQLListarCodigoGiaRelatorioValorBeneficiarioAposAvaliacao(pedidoRelatorioVo)); 
         
         prepareStatementListarCodigoGiaRelatorioValorBeneficiarioAposAValiacao(ps , pedidoRelatorioVo );
         
         rs = ps.executeQuery();
         
         while (rs.next())
         {
            GIAITCDVo giaITCDAtualVo = new GIAITCDVo();
            getGIAITCDCodigo(rs, giaITCDAtualVo);
            giaITCDVo.getCollVO().add(giaITCDAtualVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD);
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
      return giaITCDVo;
   }

   public GIAITCDVo listarCodigoGiaRelatorioCreditoConstituido(final GIAITCDVo giaITCDVo, final PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         ConsultaException
   {
      Validador.validaObjeto(giaITCDVo);
      Validador.validaObjeto(pedidoRelatorioVo);
      PreparedStatement ps = null;
      Statement stmt = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLListarCodigoGiaRelatorioCreditoConstituido(pedidoRelatorioVo)); 
         
         prepareStatementListarCodigoGiaRelatorioCreditoConstituido(ps , pedidoRelatorioVo );
         
         rs = ps.executeQuery();
         
         while (rs.next())
         {
            GIAITCDVo giaITCDAtualVo = new GIAITCDVo();
            getGIAITCDCodigo(rs, giaITCDAtualVo);
            giaITCDVo.getCollVO().add(giaITCDAtualVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD);
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
      return giaITCDVo;
   }
   
   public GIAITCDVo listarCodigoGiaRelatorioEstoqueEmAberto(final GIAITCDVo giaITCDVo, final PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         ConsultaException
   {
      Validador.validaObjeto(giaITCDVo);
      Validador.validaObjeto(pedidoRelatorioVo);
      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLListarCodigoGiaRelatorioEstoqueEmAberto(pedidoRelatorioVo)); 
         rs = ps.executeQuery();
         
         while (rs.next())
         {
            GIAITCDVo giaITCDAtualVo = new GIAITCDVo();
            getGIAITCDCodigo(rs, giaITCDAtualVo);
            giaITCDVo.getCollVO().add(giaITCDAtualVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD);
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
      return giaITCDVo;
   }



   private String getSQLListarCodigoGiaRelatorioEstoqueEmAberto(final PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         ClassCastException
   {
      Validador.validaObjeto(pedidoRelatorioVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT DISTINCT(ITCD.CODG_ITCD) ");
      sql.append(" FROM ITC.ITCTB14_ITCD ITCD ");
      sql.append("          INNER JOIN ITCTB27_STATUS_ITCD STATUS ON ITCD.CODG_ITCD = STATUS.ITCTB14_CODG_ITCD ");
      sql.append(" WHERE STATUS.CODG_STATUS_ITCD IN (SELECT MAX(I27SI2.CODG_STATUS_ITCD) ");
      sql.append("                                   FROM ITC.ITCTB27_STATUS_ITCD I27SI2 ");
      sql.append("                                   WHERE I27SI2.ITCTB14_CODG_ITCD = STATUS.ITCTB14_CODG_ITCD ) ");
      sql.append("  AND STATUS.STAT_ITCD IN (7, 8, 13, 12, 21, 22) ");
      sql.append(" ORDER BY ITCD.CODG_ITCD ");

      return sql.toString();

   }

   /**
    * 
    * 
    * 
    * @param pedidoRelatorioVo
    * @return
    * @throws ObjetoObrigatorioException
    * @throws ClassCastException
    */
   private String getSQLListarCodigoGiaRelatorioValorBeneficiarioAposAvaliacao(final PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         ClassCastException
   {
      Validador.validaObjeto(pedidoRelatorioVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT DISTINCT GIA.").append(CamposGIAITCD.CAMPO_CODIGO_GIA_ITCD);
      sql.append(" FROM ").append(TABELA_GIA_ITCD).append(" GIA ");
      sql.append(" WHERE 1=1 ");

      sql.append(" AND GIA." + CamposGIAITCD.CAMPO_DATA_CRIACAO + " ");
      sql.append(" BETWEEN DATA_CRIACAO_INICIAL AND DATA_CRIACAO_FINAL");

      sql.append(" ORDER BY GIA." + CamposGIAITCD.CAMPO_CODIGO_GIA_ITCD + " ");

      String sqlString = sql.toString();
      ParametroRelatorioVo parametro = pedidoRelatorioVo.getParametroRelatorioVo();
      for (ParametroRelatorioVo p: parametro.getCollVO())
      {
         if (!Validador.isStringValida(p.getValorParametro()))
         {
            throw new ObjetoObrigatorioException();
         }
         if (!Validador.isStringValida(p.getNomeParametro()))
         {
            throw new ObjetoObrigatorioException();
         }
         sqlString = sqlString.replace(p.getNomeParametro(), " ? ");
      }
      return sqlString;
   }
   
   private void prepareStatementListarCodigoGiaRelatorioValorBeneficiarioAposAValiacao(final PreparedStatement ps, final PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(pedidoRelatorioVo);
      int contador = 0;
      
      
      ParametroRelatorioVo parametro = pedidoRelatorioVo.getParametroRelatorioVo();
      for (ParametroRelatorioVo p: parametro.getCollVO())
      {
         if (!Validador.isStringValida(p.getValorParametro()))
         {
            throw new ObjetoObrigatorioException();
         }
         if (!Validador.isStringValida(p.getNomeParametro()))
         {
            throw new ObjetoObrigatorioException();
         }
         
         Date dataTemp  = StringUtil.toUtilData( p.getValorParametro());
         
         ps.setDate(++contador, new java.sql.Date( dataTemp.getTime()));
      }
   }
   
   

  public boolean isExisteGiaPorParametroLegislacao(Long codigoParametroLegislacao) throws ObjetoObrigatorioException, 
			   ConsultaException
	{
	  Validador.validaObjeto(codigoParametroLegislacao);
	  PreparedStatement ps = null;
	  ResultSet rs = null;
	  boolean isExiste = false;
	  try
	  {
		String sql = "SELECT COUNT(GIA.CODG_ITCD)as QTD_GIA FROM ITC.ITCTB14_ITCD GIA\n" + 
					  "WHERE GIA.ITCTB09_CODG_LEGISLACAO = ?";
	  
		 ps = conn.prepareStatement(sql);
	     ps.setLong(1, codigoParametroLegislacao);
		 rs = ps.executeQuery();
		 if (rs.next())
		 {
		   if(rs.getInt("QTD_GIA") > 0)
		   {
			   isExiste = true;
		   }
		 }
	  }
	  catch (SQLException e)
	  {
		 e.printStackTrace();
		 throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD);
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
	  return isExiste;
  }

   private String getSQLListarCodigoGiaRelatorioCreditoConstituido(PedidoRelatorioVo pedidoRelatorioVo)
      throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(pedidoRelatorioVo);
      String sql = "SELECT DISTINCT(ITCD.CODG_ITCD) CODG_ITCD\n" + 
                     "FROM ((SELECT I14I.CODG_ITCD\n" + 
                     "       FROM ITC.ITCTB14_ITCD I14I\n" + 
                     "                INNER JOIN ITC.ITCTB27_STATUS_ITCD I27SI on I14I.CODG_ITCD = I27SI.ITCTB14_CODG_ITCD\n" + 
                     "       WHERE I27SI.STAT_ITCD = 7\n" + 
                     "         AND I27SI.DATA_EMISSAO_RETIFICACAO BETWEEN ? AND ?)\n" + 
                     "      UNION\n" + 
                     "      (SELECT I14I.CODG_ITCD\n" + 
                     "       FROM ITC.ITCTB14_ITCD I14I\n" + 
                     "                INNER JOIN ITC.ITCTB27_STATUS_ITCD I27SI on I14I.CODG_ITCD = I27SI.ITCTB14_CODG_ITCD\n" + 
                     "       WHERE I27SI.STAT_ITCD = 8\n" + 
                     "         AND I27SI.DATA_NOTIFICACAO BETWEEN ? AND ?)\n" + 
                     "      UNION\n" + 
                     "      (SELECT I14I.CODG_ITCD\n" + 
                     "       FROM ITC.ITCTB14_ITCD I14I\n" + 
                     "                INNER JOIN ITC.ITCTB27_STATUS_ITCD I27SI on I14I.CODG_ITCD = I27SI.ITCTB14_CODG_ITCD\n" + 
                     "       WHERE I27SI.STAT_ITCD = 15\n" + 
                     "         AND I27SI.DATA_RATIFICACAO BETWEEN ? AND ?)\n" + 
                     "      UNION\n" + 
                     "      (SELECT I14I.CODG_ITCD\n" + 
                     "       FROM ITC.ITCTB14_ITCD I14I\n" + 
                     "                INNER JOIN ITC.ITCTB27_STATUS_ITCD I27SI on I14I.CODG_ITCD = I27SI.ITCTB14_CODG_ITCD\n" + 
                     "       WHERE I27SI.STAT_ITCD = 21\n" + 
                     "         AND I27SI.DATA_CIENCIA_NOTIFICACAO BETWEEN ? AND ?)\n" + 
                     "      UNION\n" + 
                     "      (SELECT I14I.CODG_ITCD\n" + 
                     "       FROM ITC.ITCTB14_ITCD I14I\n" + 
                     "                INNER JOIN ITC.ITCTB27_STATUS_ITCD I27SI on I14I.CODG_ITCD = I27SI.ITCTB14_CODG_ITCD\n" + 
                     "       WHERE I27SI.STAT_ITCD = 22\n" + 
                     "         AND I27SI.DATA_CIENCIA_RETIFICACAO BETWEEN ? AND ?)\n" + 
                     "      UNION\n" + 
                     "      (SELECT I14I.CODG_ITCD\n" + 
                     "       FROM ITC.ITCTB14_ITCD I14I\n" + 
                     "                INNER JOIN ITC.ITCTB27_STATUS_ITCD I27SI on I14I.CODG_ITCD = I27SI.ITCTB14_CODG_ITCD\n" + 
                     "       WHERE I27SI.STAT_ITCD = 24\n" + 
                     "         AND I27SI.DATA_CIENCIA_RATIFICACAO BETWEEN ? AND ?)\n" + 
                     "     ) ITCD\n" + 
                     "ORDER BY ITCD.CODG_ITCD \n "; 

      return sql.toString();
   
   }
   
   private void prepareStatementListarCodigoGiaRelatorioCreditoConstituido(final PreparedStatement ps, final PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(pedidoRelatorioVo);
      int contador = 0;
      int segundoContador = 0;
      
      ParametroRelatorioVo parametro = pedidoRelatorioVo.getParametroRelatorioVo();
      for (ParametroRelatorioVo p: parametro.getCollVO())
      {
         if (!Validador.isStringValida(p.getValorParametro()))
         {
            throw new ObjetoObrigatorioException();
         }
         if (!Validador.isStringValida(p.getNomeParametro()))
         {
            throw new ObjetoObrigatorioException();
         }
         
         Date dataTemp  = StringUtil.toUtilData( p.getValorParametro());
         
         ps.setDate(++contador, new java.sql.Date(dataTemp.getTime()));
         segundoContador = contador + 2;
         ps.setDate(segundoContador, new java.sql.Date(dataTemp.getTime()));
         for (int i = 0; i < 4; i++) 
         {
            segundoContador = segundoContador + 2;
            ps.setDate(segundoContador, new java.sql.Date(dataTemp.getTime()));         
         }      
      }
   }
   
   public List<String> verificaExistenciaDeGiaEmElaboracaoOuPendenteDeProtocoloPorCodgResponsavel(String numrResponsavel){
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<String> resultado = new ArrayList<String>();
      
      try
      {
         ps = conn.prepareStatement(getSQLExistenciaDeGiaEmElaboracaoOuPendenteDeProtocoloPorCodgResponsavel(numrResponsavel)); 
         rs = ps.executeQuery();
         
         while (rs.next()){
            resultado.add(String.valueOf(rs.getLong("CODG_ITCD_TEMP")));
         }
      }
      catch (SQLException e){
         e.printStackTrace();
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
      return resultado;
   }
   
   public String getSQLExistenciaDeGiaEmElaboracaoOuPendenteDeProtocoloPorCodgResponsavel(String numrResponsavel){
      StringBuilder sql = new StringBuilder();

      sql.append(" SELECT tb39.CODG_ITCD_TEMP ");
      sql.append(" FROM ITC.ITCTB39_ITCD_TEMP tb39 ");
      sql.append(" JOIN ITC.ITCTB27_STATUS_ITCD tb27 ");
      sql.append("   ON tb27.ITCTB39_CODG_ITCD_TEMP = tb39.CODG_ITCD_TEMP ");
      sql.append(" WHERE DBMS_LOB.INSTR(tb39.info_gia_temp_xml, ");
      sql.append("        '<br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo', 1, 1) > 0 ");
      sql.append("   AND tb39.CODG_RESPONSAVEL = ").append(numrResponsavel);
      sql.append("   AND tb27.DATA_ATUALIZACAO_BD = ( ");
      sql.append("       SELECT MAX(s2.DATA_ATUALIZACAO_BD) ");
      sql.append("       FROM ITC.ITCTB27_STATUS_ITCD s2 ");
      sql.append("       WHERE s2.ITCTB39_CODG_ITCD_TEMP = tb39.CODG_ITCD_TEMP ");
      sql.append("   ) ");
      sql.append("   AND tb27.STAT_ITCD IN (1, 20) ");
      sql.append("   AND tb39.DATA_CRIACAO >= TRUNC(SYSDATE, 'YYYY') ");
   
      return sql.toString();
   }
}
