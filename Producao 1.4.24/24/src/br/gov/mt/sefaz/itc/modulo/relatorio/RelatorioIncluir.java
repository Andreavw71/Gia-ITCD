package br.gov.mt.sefaz.itc.modulo.relatorio;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.relatorio.parametrorelatorio.ParametroRelatorioVo;
import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.Form;
import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.PedidoRelatorioBe;
import br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.PedidoRelatorioVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoRelatorio;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import br.gov.mt.sefaz.itc.util.facade.relatorio.CampoRelatorioValorAposAvaliacao;
import br.gov.mt.sefaz.itc.util.integracao.acessoweb.UsuarioIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sefaz.mt.gestaopessoas.integracao.ServidorSefazVO;
import sefaz.mt.util.SefazData;
import sefaz.mt.util.SefazDataHora;
import sefaz.mt.util.SefazString;

public class RelatorioIncluir extends AbstractAbacoServlet implements Flow, Form
{
   private static final int SOLICITAR_FORMULARIO_PARAMETRO = 2;
   private static final int REQUISICAO_INCLUIR_VALOR_POR_BENEFICIARIO_APOS_AVALIACAO = 3;

   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
         ParametroObrigatorioException
   {
      switch (redirecionar(request))
      {
         case REQUISICAO_VAZIA:
            {
               solicitarIncluirRelatorio(request, response);
               break;
            }
         case SOLICITAR_FORMULARIO_PARAMETRO:
            {
               solicitarFormualarioParametro(request, response);
               break;
            }
            case REQUISICAO_INCLUIR_VALOR_POR_BENEFICIARIO_APOS_AVALIACAO:
            {
               incluirParametroValorPorBeneficiarioAposAvaliacao(request, response);
            }
      }
   }

   protected int redirecionar(HttpServletRequest request)
   {
      if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_RELATORIO)))
      {
         DomnTipoRelatorio tipoRelatorio =  new DomnTipoRelatorio(Integer.valueOf(request.getParameter(CAMPO_SELECT_TIPO_RELATORIO)));
         PedidoRelatorioVo pedidoRelatorioVo = (PedidoRelatorioVo) getBuffer(request).getAttribute(PEDIDO_RELATORIO_VO);
         pedidoRelatorioVo.setTipoRelatorio(tipoRelatorio);
         
         if(Validador.isDominioNumericoValido(tipoRelatorio) && tipoRelatorio.getValorCorrente() == DomnTipoRelatorio.ESTOQUE_EM_ABERTO )
         {
            ParametroRelatorioVo pr = null;          
            pr = new ParametroRelatorioVo();
            pr.setValorParametro("01/01/2009");
            pr.setNomeParametro(CampoRelatorioValorAposAvaliacao.CAMPO_DATA_INICIAL_CRIACAO_GIA);            
            pedidoRelatorioVo.getParametroRelatorioVo().getCollVO().add(pr);
        
            pr = new ParametroRelatorioVo();
            pr.setValorParametro(new SefazData().formata());
            pr.setNomeParametro(CampoRelatorioValorAposAvaliacao.CAMPO_DATA_FINAL_CRIACAO_GIA);
            pedidoRelatorioVo.getParametroRelatorioVo().getCollVO().add(pr);
            
            return REQUISICAO_INCLUIR_VALOR_POR_BENEFICIARIO_APOS_AVALIACAO;
         }else
         {
            return SOLICITAR_FORMULARIO_PARAMETRO;
         }

         
      }
      else if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR_PARAMETRO_RELATORIO_VALOR_POR_BENEFICIARIO_APOS_AVALIACAO)))
      {
         return REQUISICAO_INCLUIR_VALOR_POR_BENEFICIARIO_APOS_AVALIACAO;
      }     
      return REQUISICAO_VAZIA;
   }
   
   
   /**
    * <b>Objetivo: </b> este método tem por objetivo redirecionar para
    * o formulário de inserção de parametros de acordo com o tipo
    * de relatório escolhido.
    * 
    * 
    */
   private void solicitarFormualarioParametro(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
         ParametroObrigatorioException
   {
      PedidoRelatorioVo pedidoRelatorioVo = controladorInterativoJSP(request);

      switch (pedidoRelatorioVo.getTipoRelatorio().getValorCorrente())
      {
         case DomnTipoRelatorio.VALOR_POR_BENEFICIARIO_APOS_AVALIACAO:
         case DomnTipoRelatorio.CREDITO_CONSTITUIDO:
         case DomnTipoRelatorio.ESTOQUE_EM_ABERTO:
            processFlow(VIEW_FORM_VALOR_BENEFICIARIO_APOS_AVALIACAO, request, response, FORWARD);
            break;
         default:
            processFlow(VIEW_INCLUIR_RELATORIO, request, response, FORWARD);
      }

   }
   
   

   private void solicitarIncluirRelatorio(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
         ParametroObrigatorioException
   {
      removeBuffer(request);
      PedidoRelatorioVo pedidoRelatorioVo;
      try
      {
         pedidoRelatorioVo = new PedidoRelatorioVo();
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }

      getBuffer(request).setAttribute(PEDIDO_RELATORIO_VO, pedidoRelatorioVo, request);
      processFlow(VIEW_INCLUIR_RELATORIO, request, response, FORWARD);
   }

   /**
    * 
    * @implmented by Dherkyan Ribeiro
    */
   private void incluirParametroValorPorBeneficiarioAposAvaliacao(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
         ParametroObrigatorioException
   {
      PedidoRelatorioVo pedidoRelatorioVo = controladorInterativoJSP(request);
      PedidoRelatorioBe pedidoRelatorioBe = null;
      try
      {         
         
         pedidoRelatorioBe = new PedidoRelatorioBe();
         obterInformacoesLogSefaz(request, pedidoRelatorioVo);
         
         pedidoRelatorioVo.setUsuarioSolicitante( new UsuarioIntegracaoVo( pedidoRelatorioVo.getLogSefazVo().getUsuario().getCodigo()));
         
         pedidoRelatorioBe.incluirPedidoRelatorio(pedidoRelatorioVo);
         processFlow(VIEW_SUCESSO, request, response, FORWARD);
         
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }

     // getBuffer(request).setAttribute(PEDIDO_RELATORIO_VO, pedidoRelatorioVo, request);
     // processFlow(VIEW_FORM_VALOR_BENEFICIARIO_APOS_AVALIACAO, request, response, FORWARD);
   }

   /**
    * 
    * @implmented by Dherkyan Ribeiro
    */
   private PedidoRelatorioVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      PedidoRelatorioVo pedidoRelatorioVo = iniciaPedidoRelatorioVo(request);
      return pedidoRelatorioVo;
   }

   /**
    * 
    * @implmented by Dherkyan Ribeiro
    */
   private PedidoRelatorioVo iniciaPedidoRelatorioVo(HttpServletRequest request) throws ObjetoObrigatorioException
   {
      PedidoRelatorioVo pedidoRelatorioVo = (PedidoRelatorioVo) getBuffer(request).getAttribute(PEDIDO_RELATORIO_VO);
      Validador.validaObjeto(pedidoRelatorioVo);
      pedidoRelatorioVo.getParametroRelatorioVo().setCollVO(null);
      ParametroRelatorioVo pr = null;
      
      if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_RELATORIO)))
      {      
         pedidoRelatorioVo.setTipoRelatorio(new DomnTipoRelatorio(Integer.valueOf(request.getParameter(CAMPO_SELECT_TIPO_RELATORIO))) );
      }

      if (pedidoRelatorioVo.getTipoRelatorio().is(DomnTipoRelatorio.VALOR_POR_BENEFICIARIO_APOS_AVALIACAO) ||
            pedidoRelatorioVo.getTipoRelatorio().is(DomnTipoRelatorio.CREDITO_CONSTITUIDO))
           
      {
         if (Validador.isStringValida(request.getParameter(CAMPO_DATA_INICIAL_RELATORIO)))
         {
            pr = new ParametroRelatorioVo();
            pr.setValorParametro( request.getParameter(CAMPO_DATA_INICIAL_RELATORIO) );
            pr.setNomeParametro(CampoRelatorioValorAposAvaliacao.CAMPO_DATA_INICIAL_CRIACAO_GIA);
            
            pedidoRelatorioVo.getParametroRelatorioVo().getCollVO().add(pr);
         }
         if (Validador.isStringValida(request.getParameter(CAMPO_DATA_FINAL_RELATORIO)))
         {
            pr = new ParametroRelatorioVo();
            pr.setValorParametro( request.getParameter(CAMPO_DATA_FINAL_RELATORIO) );
            pr.setNomeParametro(CampoRelatorioValorAposAvaliacao.CAMPO_DATA_FINAL_CRIACAO_GIA);
            pedidoRelatorioVo.getParametroRelatorioVo().getCollVO().add(pr);
         }
      }
      return pedidoRelatorioVo;
   }
}
