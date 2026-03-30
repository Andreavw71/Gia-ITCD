package br.gov.mt.sefaz.itc.modulo.tabelabasica.iptu;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.tabelabasica.iptu.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptu.IPTUBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptu.IPTUVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoIPTU;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IPTUIncluir extends AbstractAbacoServlet implements Flow, Form
{

   private static final int REQUISICAO_ALTERAR_IPTU = 2;
   private static final int REQUISICAO_INCLUIR_IPTU = 3;

   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
         IntegracaoException, ParametroObrigatorioException, PersistenciaException, AnotacaoException, LogException
   {
      switch (redirecionar(request))
      {
         case REQUISICAO_VAZIA:
            {
               solicitarIPTU(request, response);
               break;
            }
         case REQUISICAO_ALTERAR_IPTU:
            {
               solicitarAlterarIPTU(request, response);
               break;
            }
         case REQUISICAO_INCLUIR_IPTU:
            {
               solicitarIncluirIPTU(request, response);
               break;
            }
      }
   }

   protected int redirecionar(HttpServletRequest request)
   {
      if (Validador.isStringValida(request.getParameter(BOTAO_ALTERAR_IPTU)))
      {
         return REQUISICAO_ALTERAR_IPTU;
      }
      if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_IPTU)))
      {
         return REQUISICAO_INCLUIR_IPTU;
      }
      else
      {
         return REQUISICAO_VAZIA;
      }
   }

   private void solicitarAlterarIPTU(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         ObjetoObrigatorioException, ConsultaException, ParametroObrigatorioException, PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, IntegracaoException
   {
      int codigoMunicipio = Integer.parseInt(request.getParameter(BOTAO_ALTERAR_IPTU));
      IPTUVo iptuVoConsulta = new IPTUVo();
      iptuVoConsulta.setStatusResgistroIPTU(new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
      iptuVoConsulta.getMunicipio().setCodgMunicipio(codigoMunicipio);
      IPTUVo iptuVo = new IPTUVo(iptuVoConsulta);
      IPTUBe iptuBe = null;
      try
      {
         iptuBe = new IPTUBe();
         
         iptuBe.prepararParaAlterarIPTU(iptuVo);
         
         iptuVoConsulta  = null; 
         iptuVoConsulta = new IPTUVo();
         iptuVoConsulta.setStatusResgistroIPTU( new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
         iptuVo.setParametroConsulta(iptuVoConsulta);
         iptuBe.listarIPTU(iptuVo);
      }
      catch (SQLException e)
      {
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (iptuBe != null)
         {
            iptuBe.close();
            iptuBe = null;
         }
      }
      getBuffer(request).setAttribute(IPTU_VO, iptuVo, request);
      processFlow(VIEW_INCLUIR_IPTU, request, response, FORWARD);
   }

   private void solicitarIPTU(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
         IntegracaoException
   {
      removeBuffer(request);
      IPTUVo iptuVo = null;
      IPTUVo iptuVoConsulta = null;
      IPTUBe iptuBe = null;
      try
      {
         iptuVo = new IPTUVo();
         iptuBe = new IPTUBe();
         
         iptuVoConsulta = null;
         iptuVoConsulta = new IPTUVo();
         iptuVoConsulta.setStatusResgistroIPTU( new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
         iptuVo.setParametroConsulta(iptuVoConsulta);
         iptuBe.listarIPTU(iptuVo);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (iptuBe != null)
         {
            iptuBe.close();
            iptuBe = null;
         }
      }
      getBuffer(request).setAttribute(IPTU_VO, iptuVo, request);
      processFlow(VIEW_INCLUIR_IPTU, request, response, FORWARD);
   }

   private void solicitarIncluirIPTU(HttpServletRequest request, HttpServletResponse response) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException, ConexaoException, PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, PersistenciaException, AnotacaoException, LogException, IntegracaoException
   {
      IPTUVo iptuVo = controladorInterativoJSP(request);
      int codigoMunicipio = Integer.parseInt(request.getParameter(BOTAO_ADICIONAR_IPTU));
      IPTUVo iptuVoConsulta = new IPTUVo();
      iptuVoConsulta.getMunicipio().setCodgMunicipio(codigoMunicipio);
      iptuVo.setParametroConsulta(iptuVoConsulta);
      obterInformacoesLogSefaz(request, iptuVo);
      IPTUBe iptuBe = null;
      try
      {
         iptuBe = new IPTUBe();
         iptuBe.incluirIPTU(iptuVo);
         
         // Preparação para listar os dados e exibir na VIEW.
         iptuVoConsulta = null;
         iptuVo = new IPTUVo();
         iptuVoConsulta = new IPTUVo();
         iptuVoConsulta.setStatusResgistroIPTU( new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
         iptuVo.setParametroConsulta(iptuVoConsulta);
         iptuBe.listarIPTU(iptuVo);
         getBuffer(request).setAttribute(IPTU_VO, iptuVo, request);
         processFlow(VIEW_INCLUIR_IPTU, request, response, FORWARD);
      }
      catch (ParametroObrigatorioException e)
      {
         getBuffer(request).setAttribute(IPTU_VO, iptuVo, request);
         request.setAttribute(EXCEPTION, e);
         processFlow(VIEW_INCLUIR_IPTU, request, response, FORWARD);
      }
      catch (SQLException e)
      {
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (iptuBe != null)
         {
            iptuBe.close();
            iptuBe = null;
         }
      }
   }

   private IPTUVo controladorInterativoJSP(HttpServletRequest request)
   {

      IPTUVo iptuVo = (IPTUVo) getBuffer(request).getAttribute(IPTU_VO);
      Validador.isObjetoValido(iptuVo);

      double valorPercentualEstimado = StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_PERCENTUAL_ESTIMADO));
      //if (Validador.isNumericoValido(valorPercentualEstimado))
     // {
         iptuVo.setValorPercentualEstimado(valorPercentualEstimado);
     // }
      double valorMetroTerritorial = StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_METRO_TERRITORIAL));
     // if (Validador.isNumericoValido(valorMetroTerritorial))
     // {
         iptuVo.setValorMetroTerritorial(valorMetroTerritorial);
     // }
      double valorMetroPredial = StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_METRO_PREDIAL));
    //  if (Validador.isNumericoValido(valorMetroPredial))
     // {
         iptuVo.setValorMetroPredial(valorMetroPredial);
    //  }
      
     int tipoITPU = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_IPTU));
      if (Validador.isNumericoValido(tipoITPU))
      {
         iptuVo.setTipoITPU(new DomnTipoIPTU(tipoITPU) );
      }
      return iptuVo;

   }
}
