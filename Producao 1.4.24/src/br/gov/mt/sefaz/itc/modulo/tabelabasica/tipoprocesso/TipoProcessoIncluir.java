package br.gov.mt.sefaz.itc.modulo.tabelabasica.tipoprocesso;

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
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.TipoProcessoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.TipoProcessoVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoEprocess;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import br.gov.mt.sefaz.itc.util.integracao.eprocess.TipoProcessoIntegracaoVo;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Dherkyan Ribeiro da Silva
 */
public class TipoProcessoIncluir extends AbstractAbacoServlet implements Flow, Form
{

   private static final int REQUISICAO_INCLUIR_TIPO_PROCESSO = 2;
   
   /**
    * 
    * @param request
    * @param response
    * @throws ConexaoException
    * @throws ObjetoObrigatorioException
    * @throws PaginaNaoEncontradaException
    * @throws TipoRedirecionamentoInvalidoException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @throws RegistroExistenteException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @implemented by Dherkyan Ribeiro
    */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, LogException, 
         AnotacaoException, PersistenciaException, RegistroExistenteException, ConsultaException, IntegracaoException
   {
      switch (redirecionar(request))
      {
         case REQUISICAO_VAZIA:
            {
               solicitarIncluirTipoProcesso(request, response);
               break;
            }
         case REQUISICAO_INCLUIR_TIPO_PROCESSO:
            {
               incluirTipoProcesso(request, response);
               break;
            }   
      }
   }

   /**
    * 
    * @param request
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   protected int redirecionar(HttpServletRequest request)
   {
      if (Validador.isStringValida(request.getParameter(BOTAO_INCLUIR_PROCESSO_EPROCESS)))
      {
         return REQUISICAO_INCLUIR_TIPO_PROCESSO;
      }
      else
      {
         return REQUISICAO_VAZIA;
      }
   }
   
   
   /**
    * Este método tem por objeivo preparar os dados
    * para primeira exibição do formulário
    * quando o usuário acessa a funcionalidade.
    * 
    * 
    * @param request
    * @param response
    * @implemented by Dherkyan Ribeiro
    */
   private void solicitarIncluirTipoProcesso(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
   {
      removeBuffer(request);
      TipoProcessoVo tipoProcessoVo = null;
      TipoProcessoBe tipoProcessoBe = null;
      TipoProcessoIntegracaoVo tipoProcessoIntegracaoVo = null;
      
      try
      {
         tipoProcessoVo = new TipoProcessoVo();
         tipoProcessoBe = new TipoProcessoBe();
         tipoProcessoIntegracaoVo = new TipoProcessoIntegracaoVo();
         tipoProcessoBe.listarTipoProcessoAtivoEprocessComITCD(tipoProcessoIntegracaoVo);
         
         tipoProcessoVo.setTipoProcessoIntegracaoVo(tipoProcessoIntegracaoVo);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (tipoProcessoBe != null)
         {
            tipoProcessoBe.close();
            tipoProcessoBe = null;
         }
      }
      getBuffer(request).setAttribute(TIPO_PROCESSO_VO, tipoProcessoVo, request);
      processFlow(VIEW_INCLUIR_TIPO_PROCESSO, request, response, FORWARD);
   }
   
   
   /**
    * Este método tem por objetivo iniciar o processo
    * de inclusão de registro no BD e retornar a página.
    * 
    * @param request
    * @param response
    * @implemented by Dherkyan Ribeiro
    */
   private void incluirTipoProcesso(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, LogException, 
         AnotacaoException, PersistenciaException, RegistroExistenteException, ConsultaException, IntegracaoException
   {
      TipoProcessoVo tipoProcessoVo = controladorInterativoJSP(request);
      Validador.isObjetoValido(tipoProcessoVo);
      TipoProcessoBe tipoProcessoBe = null;
      TipoProcessoIntegracaoVo tipoProcessoIntegracaoVo = null;

      try
      {
         tipoProcessoBe = new TipoProcessoBe();
         obterInformacoesLogSefaz(request, tipoProcessoVo);
         tipoProcessoBe.incluirTipoProcesso(tipoProcessoVo);
         
         tipoProcessoIntegracaoVo = new TipoProcessoIntegracaoVo();
         tipoProcessoBe.listarTipoProcessoAtivoEprocessComITCD(tipoProcessoIntegracaoVo);
         
         tipoProcessoVo.setTipoProcessoIntegracaoVo(tipoProcessoIntegracaoVo);
         request.setAttribute(TIPO_PROCESSO_VO, tipoProcessoVo);
         //processFlow(VIEW_INCLUIR_TIPO_PROCESSO, request, response, FORWARD);
         processFlow(VIEW_SUCESSO, request, response, FORWARD);
      }
      catch (ParametroObrigatorioException e)
      {
         getBuffer(request).setAttribute(TIPO_PROCESSO_VO, tipoProcessoVo, request);
         request.setAttribute(EXCEPTION, e);
         processFlow(VIEW_INCLUIR_TIPO_PROCESSO, request, response, FORWARD);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (tipoProcessoBe != null)
         {
            tipoProcessoBe.close();
            tipoProcessoBe = null;
         }
      }
   }
   
   /**
    * 
    * @param request
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   private TipoProcessoVo controladorInterativoJSP(HttpServletRequest request)
   {
      TipoProcessoVo tipoProcessoVo = (TipoProcessoVo) getBuffer(request).getAttribute(TIPO_PROCESSO_VO);
      Validador.isObjetoValido(tipoProcessoVo);
      if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_PROCESSO)))
      {
         tipoProcessoVo.setDescTipoProcesso(request.getParameter(CAMPO_DESCRICAO_PROCESSO));
      }
      Integer tipoProcessoEprocess = StringUtil.toInt(request.getParameter(CAMPO_TIPOS_DE_PROCESSOS));
      if (Validador.isNumericoValido(tipoProcessoEprocess))
      {
         tipoProcessoVo.setDomnTipoEprocess(new  DomnTipoProcessoEprocess(tipoProcessoEprocess) );
      }
      long codigoTipoProcessoEprocess = StringUtil.toLong(request.getParameter(TIPO_PROCESSO_EPROCESS));
      if (Validador.isNumericoValido(codigoTipoProcessoEprocess))
      {
         TipoProcessoIntegracaoVo tipoProcessoIntegracaoVo = new  TipoProcessoIntegracaoVo();
         tipoProcessoIntegracaoVo.setCodgTipoProcesso(codigoTipoProcessoEprocess);
         tipoProcessoVo.setTipoProcessoIntegracaoVo(tipoProcessoIntegracaoVo);
      }
      return tipoProcessoVo;
   }
   
}
