package br.gov.mt.sefaz.itc.modulo.tabelabasica.distancia;

import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.tabelabasica.distancia.DistanciaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.distancia.DistanciaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.distancia.Form;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DistanciaPesquisar extends AbstractAbacoServlet implements Flow, Form
{

   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      switch (redirecionar(request))
      {
         case REQUISICAO_VAZIA:
            {
               solicitarConsultarDistancia(request, response);
               break;
            }
      }
   }

   protected int redirecionar(HttpServletRequest request)
   {
      return REQUISICAO_VAZIA;
   }

   private void solicitarConsultarDistancia(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      removeBuffer(request);
      DistanciaBe distanciaBe = null;
      DistanciaVo distanciaVo = null;
      try
      {
         distanciaBe = new DistanciaBe();
         distanciaVo = new DistanciaVo();
         distanciaBe.listarDistanciaAtiva(distanciaVo);
         distanciaBe.ordernarListaParaExibirNaView(distanciaVo);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }

      getBuffer(request).setAttribute(DISTANCIA_VO, distanciaVo, request);
      processFlow(VIEW_CONSULTAR_DISTANCIA, request, response, FORWARD);
   }
}
