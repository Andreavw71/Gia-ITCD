package br.gov.mt.sefaz.itc.modulo.tabelabasica.distancia;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.tabelabasica.distancia.DistanciaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.distancia.DistanciaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.distancia.Form;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDistancia;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DistanciaIncluir extends AbstractAbacoServlet implements Flow, Form
{

   private static final int REQUISICAO_INCLUIR_DISTANCIA = 2;
   private static final int REQUISICAO_CONFIRMAR_DISTANCIA = 3;
   private static final int REQUISICAO_INATIVAR_DISTANCIA = 4;

   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConexaoException, LogException, AnotacaoException, 
         PersistenciaException, ParametroObrigatorioException, RegistroExistenteException, ConsultaException
   {
      switch (redirecionar(request))
      {
         case REQUISICAO_VAZIA:
            {
               solicitarIncluirDistancia(request, response);
               break;
            }
         case REQUISICAO_INCLUIR_DISTANCIA:
            {
               incluirDistancia(request, response);
               break;
            }
         case REQUISICAO_INATIVAR_DISTANCIA:
            {
               inativarDistancia(request, response);
               break;
            }
      }
   }

   protected int redirecionar(HttpServletRequest request)
   {
      if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
      {
         return REQUISICAO_CONFIRMAR_DISTANCIA;
      }
      else if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_DISTANCIA)))
      {
         return REQUISICAO_INCLUIR_DISTANCIA;
      }
      else if (Validador.isStringValida(request.getParameter(BOTAO_EXCLUIR_DISTANCIA)))
      {
         return REQUISICAO_INATIVAR_DISTANCIA;
      }
      else
      {
         return REQUISICAO_VAZIA;
      }
   }

   private void solicitarIncluirDistancia(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
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
      processFlow(VIEW_INCLUIR_DISTANCIA, request, response, FORWARD);
   }

   private void incluirDistancia(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
         PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, LogException, 
         AnotacaoException, PersistenciaException, ParametroObrigatorioException, RegistroExistenteException, 
         ConsultaException
   {
      DistanciaVo distanciaVo = controladorInterativoJSP(request);
      Validador.validaObjeto(distanciaVo);
      DistanciaBe distanciaBe = null;
      try
      {
         distanciaBe = new DistanciaBe();
         obterInformacoesLogSefaz(request, distanciaVo);
         distanciaBe.listarDistanciaAtiva(distanciaVo);
         distanciaBe.ordernarListaParaExibirNaView(distanciaVo);
         distanciaBe.incluirDistancia(distanciaVo);
         
         distanciaVo = new DistanciaVo();
         distanciaBe.listarDistanciaAtiva(distanciaVo);
         distanciaBe.ordernarListaParaExibirNaView(distanciaVo);
         request.setAttribute(DISTANCIA_VO, distanciaVo);
         processFlow(VIEW_INCLUIR_DISTANCIA, request, response, FORWARD);
      }
      catch (ParametroObrigatorioException e)
      {
         //distanciaVo = new DistanciaVo();
         distanciaBe.listarDistanciaAtiva(distanciaVo);
         distanciaBe.ordernarListaParaExibirNaView(distanciaVo);
         
         getBuffer(request).setAttribute(DISTANCIA_VO, distanciaVo, request);
         request.setAttribute(EXCEPTION, e);
         processFlow(VIEW_INCLUIR_DISTANCIA, request, response, FORWARD);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (distanciaBe != null)
         {
            distanciaBe.close();
            distanciaBe = null;
         }
      }
   }

   private DistanciaVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      DistanciaVo distanciaVo = iniciaDistanciaVo(request);
      return distanciaVo;
   }

   private DistanciaVo iniciaDistanciaVo(HttpServletRequest request) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      DistanciaVo distanciaVo = (DistanciaVo) getBuffer(request).getAttribute(DISTANCIA_VO);
      Validador.validaObjeto(distanciaVo);
      if (Validador.isStringValida(request.getParameter(CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO)))
      {
         distanciaVo.setDistanciaInicialPerimetroUrbano((Integer.parseInt(request.getParameter(CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO))));
      }
      if (Validador.isStringValida(request.getParameter(CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO)))
      {
         distanciaVo.setDistanciaFinalPerimetroUrbano((Integer.parseInt(request.getParameter(CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO))));
      }
      if (Validador.isStringValida(request.getParameter(CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA)))
      {
         distanciaVo.setDistanciaInicialRodoviaPavimentada((Integer.parseInt(request.getParameter(CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA))));
      }
      if (Validador.isStringValida(request.getParameter(CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA)))
      {
         distanciaVo.setDistanciaFinalRodoviaPavimentada((Integer.parseInt(request.getParameter(CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA))));
      }
      Integer tipoDistancia = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_DISTANCIA));
      if (Validador.isObjetoValido(tipoDistancia))
      {
         distanciaVo.setTipoDistancia(new DomnTipoDistancia(tipoDistancia));
      }
      return distanciaVo;
   }

   private void inativarDistancia(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
         RegistroExistenteException, LogException, AnotacaoException, PersistenciaException
   {
      DistanciaBe distanciaBe = null;
      DistanciaVo distanciaVo = null;
      try
      {

         Long codigo = Long.parseLong(request.getParameter(BOTAO_EXCLUIR_DISTANCIA));
         DistanciaVo distanciaInativar = new DistanciaVo(codigo);
         distanciaBe = new DistanciaBe();
         distanciaBe.inativarDistancia(distanciaInativar);
         distanciaVo = new DistanciaVo();
         obterInformacoesLogSefaz(request, distanciaVo);
         distanciaBe.listarDistanciaAtiva(distanciaVo);
         distanciaBe.ordernarListaParaExibirNaView(distanciaVo);
         request.setAttribute(DISTANCIA_VO, distanciaVo);
         processFlow(VIEW_INCLUIR_DISTANCIA, request, response, FORWARD);
      }
      catch (ParametroObrigatorioException e)
      {
         //getBuffer(request).setAttribute(DISTANCIA_VO, distanciaVo, request);
         request.setAttribute(EXCEPTION, e);
         processFlow(VIEW_INCLUIR_DISTANCIA, request, response, FORWARD);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (distanciaBe != null)
         {
            distanciaBe.close();
            distanciaBe = null;
         }
      }
   }
}
