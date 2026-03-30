package br.gov.mt.sefaz.itc.modulo.giaitcd.giaitcdprotocolo;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ImpossivelCriptografarException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;
import br.gov.mt.sefaz.eprocess.integracao.IntegracaoEProcess;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.TipoProcessoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.TipoProcessoVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnFuncionalidadeOrigem;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.http.HttpUtil;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sefaz.mt.util.excecao.ValidacaoException;


public class GIAITCDProcessoValidar extends AbstractAbacoServlet implements Form, Flow
{
   
   private static final int REQUISICAO_SOLICITAR_MOSTRAR_CONTRIBUINTE = 2;
   private static final int REQUISICAO_INCLUIR_PROCESSO_GIAITCD = 3;
   private static final String NOME_ARQUIVO_ITCD = "/usr/appl/lib/ITCDS.properties";

   protected void processRequest(HttpServletRequest request, 
                                 HttpServletResponse response)
      throws PaginaNaoEncontradaException, 
             TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, 
             IntegracaoException, ConexaoException, ConsultaException, 
             ImpossivelCriptografarException, ValidacaoException, Exception
   {
      switch (redirecionar(request))
      {
         case REQUISICAO_VAZIA:
         {
            solicitarConsultarGIAITCD(request, response);
            break;
         }
         case REQUISICAO_SOLICITAR_MOSTRAR_CONTRIBUINTE:
         {
            consultarGIAITCD(request, response);
            break;
         }
         /*
         case REQUISICAO_INCLUIR_PROCESSO_GIAITCD:
         {  
            //Opção não utilizada neste funcionalidade.
            incluirProtocoloGiaITCD(request, response);
            break;
         }
         */
      }
   
   
   }

   protected int redirecionar(HttpServletRequest request)
   {
      if (Validador.isObjetoValido(request.getParameter(BOTAO_PESQUISAR_GIA_ITCD)))
      {
         return REQUISICAO_SOLICITAR_MOSTRAR_CONTRIBUINTE;
      }
      else if(Validador.isObjetoValido(request.getParameter(BOTAO_INCLUIR_PROCESSO_EPROCESS)))
      {
         return REQUISICAO_INCLUIR_PROCESSO_GIAITCD;
      }
      return REQUISICAO_VAZIA;
   }

   
   /**
    *  Método que recupera do request os dados informados pelo usuário
    * @param request
    * @return GIAITCDVo
    */
   private GIAITCDVo controladorInterativoJSP(HttpServletRequest request)
   {
      GIAITCDVo giaITCDVo = (GIAITCDVo) getBuffer(request).getAttribute(GIAITCD_VO);
      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
      if (giaITCDVo == null)
      {
         giaITCDVo = new GIAITCDVo();
         giaITCDVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
      }
      if (Validador.isStringValida(request.getParameter(CAMPO_SENHA)))
      {
         giaITCDVo.setSenha(request.getParameter(CAMPO_SENHA));
      }
      if (Validador.isStringValida(request.getParameter(CAMPO_CODIGO_GIA)))
      {
         giaITCDVo.setCodigo(StringUtil.toLong(request.getParameter(CAMPO_CODIGO_GIA)));
      }
      return giaITCDVo;
   }
   
   /**
    * Este método é acionado para verificar se o usuário é servidor sefaz, lotado na  AGENFA, CGED, GIOR
    * @param request
    * @param response
    * @implemented by Rogério Sanches de Oliveira
    */
   private void solicitarConsultarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
           TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, IntegracaoException, ConexaoException
   {
      removeBuffer(request);
      GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
      GIAITCDBe giaITCDBe = null;
      try
      {  
         giaITCDBe = new GIAITCDBe();
         try
         {
            obterInformacoesLogSefaz(request, giaITCDVo);
            giaITCDVo.setOrigem(DomnFuncionalidadeOrigem.CONSULTAR_GIA_ITCD);
            giaITCDBe.verificaUsuarioAptoAcessoFuncionalidade(giaITCDVo);
         }
         catch (RegistroNaoPodeSerUtilizadoException e)
         {
            giaITCDVo.setMensagem(e.getMessage());
            request.setAttribute(ENTIDADE_VO, giaITCDVo);
            processFlow(VIEW_ERRO, request, response, FORWARD);
            return;
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (giaITCDBe != null)
         {
            giaITCDBe.close();
            giaITCDBe = null;
         }
      }
      getBuffer(request).setAttribute(ORIGEM, ""+giaITCDVo.getOrigem(),request);
      getBuffer(request).setAttribute(GIAITCD_VO, giaITCDVo, request);
      processFlow(VIEW_PROTOCOLO_LOGIN, request, response, FORWARD);
   }
   
   /**
    * Método responsável por solicitar uma consulta da GIA-ITCD
    * @param request
    * @param response
    */
   private void consultarGIAITCD(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
           TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, IntegracaoException, 
           ConexaoException, ImpossivelCriptografarException, ValidacaoException, Exception
   {
      GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
      GIAITCDBe giaITCDBe = null;
      TipoProcessoBe tipoProcessoBe = null;
      TipoProcessoVo tipoProcessoVo = null;
      try
      {
         giaITCDBe = new GIAITCDBe();
         try
         {
            HttpUtil.validarCaracteresInformadosModAberto(getBuffer(request) , request.getParameter(Form.CAMPO_IMAGEM), giaITCDVo.getLogSefazVo());
            giaITCDVo = giaITCDBe.solicitarConsultarGIAITCDAtiva(giaITCDVo);
            if(giaITCDBe.verificarGiaITCDParaProtocolo(giaITCDVo))
            {
               throw new ValidacaoException("GIA-ITCD sem processo preenchido.");
            }
            else
            {
               //Retornar View de validação
               getBuffer(request).setAttribute(URL_VALIDAR , montarParametroValidarProtocoloGiaITCD(giaITCDVo, request), request);
               processFlow(VIEW_VALIDAR_PROTOCOLO_GIA_ITCD, request, response, FORWARD);
              
            }
         }
         catch(ParametroObrigatorioException e)
         {
            throw new ConsultaException(e.getMessage());
         }
         catch (ValidacaoException e)
         {
            throw new ConsultaException(e.getMessage());
         }
         catch (Exception e)
         {
            throw new ConsultaException(e.getMessage());
         }
      }
      catch(SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if(giaITCDBe != null)
         {
            giaITCDBe.close();
            giaITCDBe = null;
         }
         if(tipoProcessoBe != null) 
         {
            tipoProcessoBe.close();
            tipoProcessoBe = null;
         }
      }
   }

/*
 * Opção não utilizada neste funcionalidade.
   private void incluirProtocoloGiaITCD(HttpServletRequest request, HttpServletResponse response)
   {
      GIAITCDVo giaITCDVo = controladorInterativoJSP(request);
      TipoProcessoBe tipoProcessoBe = null;
      TipoProcessoVo tipoProcessoVo = null;
      try
      {
         tipoProcessoBe = new TipoProcessoBe();
         long codgTipoProcesso = Long.valueOf(request.getParameter(Form.CAMPO_TIPOS_DE_PROCESSOS));
         tipoProcessoVo = new TipoProcessoVo(codgTipoProcesso);
         tipoProcessoBe.buscarTipoProcesso(tipoProcessoVo);
         tipoProcessoBe.retornarTipoProcessoPorCodigo(tipoProcessoVo);
         response.sendRedirect(montarParametroProtocolarProtocoloGiaITCD(giaITCDVo, tipoProcessoVo, request));
         removeBuffer(request);
      }
      
      catch (SQLException e)
      {
         e.printStackTrace();
      }
      catch (ConsultaException e)
      {
          e.printStackTrace();
      }
      catch (ObjetoObrigatorioException e)
      {
          e.printStackTrace();
      }
      catch (IOException e)
      {
          e.printStackTrace();
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
      
      finally 
      {
         if(tipoProcessoBe != null) 
         {
            tipoProcessoBe.close();
            tipoProcessoBe = null;
         }
      }
   }
   */
   private String montarParametroProtocolarProtocoloGiaITCD(GIAITCDVo giaITCDVo, TipoProcessoVo tipoProcessoVo, HttpServletRequest request)
      throws Exception
   {
      String url = "/eprocess/processo/applet/incluir?";
      String acao = "acaoIncProcIntg=1&";
      String parm = "parm=";
      StringBuilder params = new StringBuilder();
      params.append("npessoa=")
            .append(giaITCDVo.getResponsavelVo().getNumrContribuinte())
            .append("&")
            .append("cassunto=")
            .append(tipoProcessoVo.getTipoProcessoIntegracaoVo().getCodgTipoAssunto())
            .append("&")
            .append("ctprocesso=")
            .append(tipoProcessoVo.getTipoProcessoIntegracaoVo().getCodgTipoProcesso())
            .append("&")
            .append("ngiaitcd=")
            .append(giaITCDVo.getCodigo());
      
      return url + acao + parm + IntegracaoEProcess.criptografarParametroUrl(params.toString());
   }
   
   private String montarParametroValidarProtocoloGiaITCD(GIAITCDVo giaITCDVo, HttpServletRequest request)
      throws Exception
   {
      String url = "/eprocess/processo/transmissao/validar?";
      String acao = "acaoValTranIntg=1&";
      String parm = "parm=";
      StringBuilder params = new StringBuilder();
      params.append("ngiaitcd=")
            .append(giaITCDVo.getCodigo());
      
      return url + acao + parm + IntegracaoEProcess.criptografarParametroUrl(params.toString());
   }
}
