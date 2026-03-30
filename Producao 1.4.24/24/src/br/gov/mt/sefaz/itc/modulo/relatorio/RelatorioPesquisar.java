package br.gov.mt.sefaz.itc.modulo.relatorio;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.PedidoRelatorioBe;
import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.PedidoRelatorioVo;
import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.bem.BemVo;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import br.gov.mt.sefaz.itc.util.integracao.castor.CastorBe;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RelatorioPesquisar extends AbstractAbacoServlet implements Flow, Form
{
   private static final int REQUISICAO_PESQUISAR_RELATORIO = 2;
   private static final int REQUISICAO_DOWNLLOAD_ARQUIVO_CASTOR = 3;

   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
         ParametroObrigatorioException
   {
      switch (redirecionar(request))
      {
         case REQUISICAO_VAZIA:
            {
               solicitarConsultarRelatorio(request, response);
               break;
            }
         case REQUISICAO_DOWNLLOAD_ARQUIVO_CASTOR:
            {
               downloadArquivoCastor(request, response);
               break;
            }
      }
   }

   protected int redirecionar(HttpServletRequest request)
   {
      if (Validador.isStringValida(request.getParameter(PARAMETRO_CODIGO_ARQUIVO_CASTOR)))
      {
         return REQUISICAO_DOWNLLOAD_ARQUIVO_CASTOR;
      }
      return REQUISICAO_VAZIA;
   }

   private void downloadArquivoCastor(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
         ParametroObrigatorioException
   {
      PedidoRelatorioVo pedidoRelatorioVo = controladorInterativoJSP(request);
      long codigoRelarorio = StringUtil.toLong(request.getParameter(PARAMETRO_CODIGO_ARQUIVO_CASTOR));

      CastorBe castor;
      try
      {
         if (Validador.isNumericoValido(codigoRelarorio))
         {
            for (PedidoRelatorioVo pedido: pedidoRelatorioVo.getListVo())
            {
               if (pedido.getCodigo() == codigoRelarorio && Validador.isNumericoValido( pedido.getCastorObjetoIntegracaoVo().getCodigo() ))
               {
                  castor = new CastorBe();
                  castor.downloadArquivo(pedido.getCastorObjetoIntegracaoVo().getCodigo(), response);
                  break;
               }
            }
         }
      }
      catch(SQLException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

      getBuffer(request).setAttribute(PEDIDO_RELATORIO_VO, pedidoRelatorioVo, request);
      processFlow(VIEW_PESQUIAR_RELATORIO, request, response, FORWARD);
   }

   private void solicitarConsultarRelatorio(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
         ParametroObrigatorioException
   {
      removeBuffer(request);
      PedidoRelatorioVo pedidoRelatorioVo;
      PedidoRelatorioBe pedidoRelatorioBe;
      try
      {
         pedidoRelatorioVo = new PedidoRelatorioVo();

         pedidoRelatorioBe = new PedidoRelatorioBe();
         pedidoRelatorioBe.listarPedidoRelatorio(pedidoRelatorioVo);

      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }

      getBuffer(request).setAttribute(PEDIDO_RELATORIO_VO, pedidoRelatorioVo, request);
      processFlow(VIEW_PESQUIAR_RELATORIO, request, response, FORWARD);
   }

   private PedidoRelatorioVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
   {
      PedidoRelatorioVo pedidoRelatorioVo = iniciaPedidoRelatorioVo(request);
      return pedidoRelatorioVo;
   }

   private PedidoRelatorioVo iniciaPedidoRelatorioVo(HttpServletRequest request)
   {
      PedidoRelatorioVo pedidoRelatorioVo = (PedidoRelatorioVo) getBuffer(request).getAttribute(PEDIDO_RELATORIO_VO);

      return pedidoRelatorioVo;

   }
}
