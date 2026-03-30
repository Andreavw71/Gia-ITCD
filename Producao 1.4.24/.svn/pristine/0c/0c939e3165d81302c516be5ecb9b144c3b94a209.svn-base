package br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.relatorio.parametrorelatorio.ParametroRelatorioVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSituacaoProcessamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoRelatorio;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposPedidoRelatorio;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import br.gov.mt.sefaz.itc.util.integracao.acessoweb.UsuarioIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.castor.ObjetoIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

import sefaz.mt.gestaopessoas.integracao.ServidorSefazVO;

/**
 * 
 * Classe Responsável por encapsular a lógia de acessi ai do objeto PedidoRelatorio
 * @author Dherkyan Ribeiro da Silva
 */
public class PedidoRelatorioQDao extends AbstractDao implements TabelasITC, SequencesITC, CamposPedidoRelatorio
{
   public PedidoRelatorioQDao(Connection conexao)
   {
      super(conexao);
   }

   private void getPedidoRelatorio(final ResultSet rs, final PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         SQLException, ParametroObrigatorioException
   {
      Validador.isObjetoValido(rs);
      Validador.isObjetoValido(pedidoRelatorioVo);
      pedidoRelatorioVo.setCodigo(rs.getLong(CAMPO_CODIGO_PEDIDO_RELATORIO));
      pedidoRelatorioVo.setDataSolicitacao(rs.getDate(CAMPO_DATA_SOLICITACAO));
      pedidoRelatorioVo.setTipoRelatorio(new DomnTipoRelatorio(rs.getInt(CAMPO_TIPO_RELATORIO)));
      pedidoRelatorioVo.setUsuarioSolicitante(new UsuarioIntegracaoVo(rs.getInt(CAMPO_ACSTB11_USUARIO)));
      pedidoRelatorioVo.setDataProcessamento(rs.getDate(CAMPO_DATA_PROCESSAMENTO));
      pedidoRelatorioVo.setSituacaoProcessamento(new DomnSituacaoProcessamento(rs.getInt(CAMPO_SITUACAO_PROCESSAMENTO)));
      pedidoRelatorioVo.setCastorObjetoIntegracaoVo( new ObjetoIntegracaoVo( rs.getLong(CAMPO_CASTB01_CODIGO_CASTOR) ) );
   }

   public PedidoRelatorioVo findPedidoRelatorio(PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         ConsultaException, ParametroObrigatorioException
   {
      Validador.isObjetoValido(pedidoRelatorioVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindPedidoRelatorio(pedidoRelatorioVo));
         prepareStatementFindPedidoRelatorio(ps, pedidoRelatorioVo);
         rs = ps.executeQuery();
         if (rs.next())
         {
            getPedidoRelatorio(rs, pedidoRelatorioVo);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_PEDIDO_RELATORIO);
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
      return pedidoRelatorioVo;

   }

   /**
    * 
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public PedidoRelatorioVo listarPedidoRelatorio(PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         ConsultaException, ParametroObrigatorioException
   {
      Validador.isObjetoValido(pedidoRelatorioVo);

      PreparedStatement ps = null;
      ResultSet rs = null;
      try
      {
         ps = conn.prepareStatement(getSQLFindPedidoRelatorio(pedidoRelatorioVo));
         prepareStatementFindPedidoRelatorio(ps, pedidoRelatorioVo);
         Collection pedidos = new ArrayList();
         for (rs = ps.executeQuery(); rs.next(); )
         {
            PedidoRelatorioVo p = new PedidoRelatorioVo();
            getPedidoRelatorio(rs, p);
            pedidos.add(p);
         }
         if (Validador.isCollectionValida(pedidos))
         {
            pedidoRelatorioVo.setCollVO(pedidos);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.LISTAR_PEDIDO_RELATORIO);
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
      return pedidoRelatorioVo;

   }

   private void prepareStatementFindPedidoRelatorio(PreparedStatement ps, final PedidoRelatorioVo pedidoRelatorioVo) throws ObjetoObrigatorioException, 
         SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(pedidoRelatorioVo);
      int contador = 0;
      if (pedidoRelatorioVo.isConsultaParametrizada())
      {
         PedidoRelatorioVo parametro = pedidoRelatorioVo.getParametroConsulta();

         // CAMPO_CODIGO_PEDIDO_RELATORIO
         if (Validador.isNumericoValido(parametro.getCodigo()))
         {
            ps.setLong(++contador, pedidoRelatorioVo.getParametroConsulta().getCodigo());
         }
         // CAMPO_DATA_SOLICITACAO
         if (Validador.isDataValida(pedidoRelatorioVo.getParametroConsulta().getDataSolicitacao()))
         {
            ps.setDate(++contador, (Date) parametro.getDataSolicitacao());
         }
         // CAMPO_TIPO_RELATORIO
         if (Validador.isDominioNumericoValido(parametro.getTipoRelatorio()))
         {
            ps.setInt(++contador, parametro.getTipoRelatorio().getValorCorrente());
         }
         // CAMPO_DATA_PROCESSAMENTO
         if (Validador.isDataValida(pedidoRelatorioVo.getParametroConsulta().getDataProcessamento()))
         {
            ps.setDate(++contador, (Date) parametro.getDataProcessamento());
         }
         // CAMPO_SITUACAO_PROCESSAMENTO
         if (Validador.isDominioNumericoValido(parametro.getSituacaoProcessamento()))
         {
            ps.setInt(++contador, parametro.getSituacaoProcessamento().getValorCorrente());
         }
      }
   }

   private String getSQLFindPedidoRelatorio(final PedidoRelatorioVo pedidoRelatorioVo)
   {
      Validador.isObjetoValido(pedidoRelatorioVo);
      StringBuffer sql = new StringBuffer();
      sql.append(" SELECT ");
      sql.append(" PEDIDO." + CAMPO_CODIGO_PEDIDO_RELATORIO);
      sql.append(" , PEDIDO." + CAMPO_DATA_SOLICITACAO);
      sql.append(" , PEDIDO." + CAMPO_TIPO_RELATORIO);
      sql.append(" , PEDIDO." + CAMPO_ACSTB11_USUARIO);
      sql.append(" , PEDIDO." + CAMPO_DATA_PROCESSAMENTO);
      sql.append(" , PEDIDO." + CAMPO_SITUACAO_PROCESSAMENTO);
      sql.append(" , PEDIDO." + CAMPO_CASTB01_CODIGO_CASTOR);
      sql.append(" FROM " + TABELA_PEDIDO_RELATORIO + " PEDIDO ");
      sql.append(" WHERE 1 = 1 ");

      if (pedidoRelatorioVo.isConsultaParametrizada())
      {

         PedidoRelatorioVo parametro = pedidoRelatorioVo.getParametroConsulta();

         // CAMPO_CODIGO_PEDIDO_RELATORIO
         if (Validador.isNumericoValido(parametro.getCodigo()))
         {
            sql.append(" AND PEDIDO." + CAMPO_CODIGO_PEDIDO_RELATORIO + " = ?");
         }
         // CAMPO_DATA_SOLICITACAO
         if (Validador.isDataValida(parametro.getDataSolicitacao()))
         {
            sql.append(" AND PEDIDO." + CAMPO_DATA_SOLICITACAO + " = ?");
         }
         // CAMPO_TIPO_RELATORIO
         if (Validador.isDominioNumericoValido(parametro.getTipoRelatorio()))
         {
            sql.append(" AND PEDIDO." + CAMPO_TIPO_RELATORIO + " = ?");
         }
         // CAMPO_DATA_PROCESSAMENTO
         if (Validador.isDataValida(pedidoRelatorioVo.getParametroConsulta().getDataProcessamento()))
         {
            sql.append(" AND PEDIDO." + CAMPO_DATA_PROCESSAMENTO + " = ?");
         }
         // CAMPO_SITUACAO_PROCESSAMENTO
         if (Validador.isDominioNumericoValido(parametro.getSituacaoProcessamento()))
         {
            sql.append(" AND PEDIDO." + CAMPO_SITUACAO_PROCESSAMENTO + " = ?");
         }
      }

      sql.append(" ORDER BY PEDIDO." + CAMPO_CODIGO_PEDIDO_RELATORIO + " ");
      return sql.toString();
   }

}
