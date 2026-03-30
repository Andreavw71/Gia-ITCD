package br.gov.mt.sefaz.itc.modulo.tabelabasica.basecalculoimovelrural;

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

import br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural.BaseCalculoImovelRuralBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural.BaseCalculoImovelRuralVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural.Form;
import br.gov.mt.sefaz.itc.util.dominio.DomnCriterioBaseCalculo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAtividade;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDistancia;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BaseCalculoImovelRuralIncluir extends AbstractAbacoServlet implements Flow, Form
{

   private static final int REQUISICAO_INCLUIR_BASE_CALCULO_IMOVEL_RURAL = 2;
   private static final int REQUISICAO_INATIVAR_BASE_CALCULO_IMOVEL_RURAL = 3;

   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, LogException, AnotacaoException, 
         PersistenciaException, RegistroExistenteException, ConsultaException, ParametroObrigatorioException
   {
      switch (redirecionar(request))
      {
         case REQUISICAO_VAZIA:
            {
               solicitarIncluirBaseCalculoImovelRural(request, response);
               break;
            }
         case REQUISICAO_INCLUIR_BASE_CALCULO_IMOVEL_RURAL:
            {
               incluirBaseCalculoImovelRural(request, response);
               break;
            }
         case REQUISICAO_INATIVAR_BASE_CALCULO_IMOVEL_RURAL:
            {
               inativarBaseCalculoImovelRural(request, response);
               break;
            }
      }
   }

   protected int redirecionar(HttpServletRequest request)
   {
      if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_BASE_CALCULO_IMOVEL_RURAL)))
      {
         return REQUISICAO_INCLUIR_BASE_CALCULO_IMOVEL_RURAL;
      }
      if (Validador.isStringValida(request.getParameter(BOTAO_INATIVAR_BASE_CALCULO_IMOVEL_RURAL)))
      {
         return REQUISICAO_INATIVAR_BASE_CALCULO_IMOVEL_RURAL;
      }
      else
      {
         return REQUISICAO_VAZIA;
      }
   }

   private void solicitarIncluirBaseCalculoImovelRural(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
         ParametroObrigatorioException
   {
      removeBuffer(request);
      BaseCalculoImovelRuralVo baseCalculoImovelRuralVo = null;
      BaseCalculoImovelRuralBe baseCalculoImovelRuralBe = null;

      try
      {
         baseCalculoImovelRuralVo = new BaseCalculoImovelRuralVo();
         baseCalculoImovelRuralBe = new BaseCalculoImovelRuralBe();
         baseCalculoImovelRuralBe.listarBaseCalculoImovelRuralAtiva(baseCalculoImovelRuralVo);
         baseCalculoImovelRuralBe.ordernarListaParaExibirNaView(baseCalculoImovelRuralVo);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      getBuffer(request).setAttribute(BASE_CALCULO_IMOVEL_RURAL_VO, baseCalculoImovelRuralVo, request);
      processFlow(VIEW_INCLUIR_BASE_CALCULO_IMOVEL_RURAL, request, response, FORWARD);
   }

   private void incluirBaseCalculoImovelRural(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
         ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, LogException, 
         AnotacaoException, PersistenciaException, RegistroExistenteException, ConsultaException, 
         ParametroObrigatorioException
   {
      BaseCalculoImovelRuralVo baseCalculoImovelRuralVo = controladorInterativoJSP(request);
      Validador.validaObjeto(baseCalculoImovelRuralVo);
      BaseCalculoImovelRuralBe baseCalculoImovelRuralBe = null;

      try
      {
         baseCalculoImovelRuralBe = new BaseCalculoImovelRuralBe();
         obterInformacoesLogSefaz(request, baseCalculoImovelRuralVo);

         baseCalculoImovelRuralBe.incluirBaseCalculoImovelRuralVo(baseCalculoImovelRuralVo);
         
         // Objeto recriado para remover todos os dados existentes no VO.
         baseCalculoImovelRuralVo = new BaseCalculoImovelRuralVo();
         baseCalculoImovelRuralBe.listarBaseCalculoImovelRuralAtiva(baseCalculoImovelRuralVo);
         baseCalculoImovelRuralBe.ordernarListaParaExibirNaView(baseCalculoImovelRuralVo);
         
         request.setAttribute(BASE_CALCULO_IMOVEL_RURAL_VO, baseCalculoImovelRuralVo);
         processFlow(VIEW_INCLUIR_BASE_CALCULO_IMOVEL_RURAL, request, response, FORWARD);
      }
      catch (ParametroObrigatorioException e)
      {
         baseCalculoImovelRuralBe.listarBaseCalculoImovelRuralAtiva(baseCalculoImovelRuralVo);
         baseCalculoImovelRuralBe.ordernarListaParaExibirNaView(baseCalculoImovelRuralVo);
         getBuffer(request).setAttribute(BASE_CALCULO_IMOVEL_RURAL_VO, baseCalculoImovelRuralVo, request);
         request.setAttribute(EXCEPTION, e);
         processFlow(VIEW_INCLUIR_BASE_CALCULO_IMOVEL_RURAL, request, response, FORWARD);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (baseCalculoImovelRuralBe != null)
         {
            baseCalculoImovelRuralBe.close();
            baseCalculoImovelRuralBe = null;
         }
      }
   }

   private BaseCalculoImovelRuralVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException, 
         ConexaoException
   {
      BaseCalculoImovelRuralVo baseCalculoImovelRuralVo = 
         (BaseCalculoImovelRuralVo) getBuffer(request).getAttribute(BASE_CALCULO_IMOVEL_RURAL_VO);
      Validador.validaObjeto(baseCalculoImovelRuralVo);

      int tipoDistancia = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_DISTANCIA));
      if (Validador.isNumericoValido(tipoDistancia))
      {
         baseCalculoImovelRuralVo.setTipoDistancia(new DomnTipoDistancia(tipoDistancia));
      }
      int tipoAtividade = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_ATIVIDADE));
      if (Validador.isNumericoValido(tipoAtividade))
      {
         baseCalculoImovelRuralVo.setTipoAtividade(new DomnTipoAtividade(tipoAtividade));
      }
      int quantidadeDistanciaInicial = StringUtil.toInt(request.getParameter(CAMPO_QUANTIDADE_DISTANCIA_INICIAL));
      if (Validador.isNumericoValido(quantidadeDistanciaInicial))
      {
         baseCalculoImovelRuralVo.setQuantidadeDistanciaInicial(quantidadeDistanciaInicial);
      }else
      {
         baseCalculoImovelRuralVo.setQuantidadeDistanciaInicial(0);
      }
      int quantidadeDistanciaFinal = StringUtil.toInt(request.getParameter(CAMPO_QUANTIDADE_DISTANCIA_FINAL));
      if (Validador.isNumericoValido(quantidadeDistanciaFinal))
      {
         baseCalculoImovelRuralVo.setQuantidadeDistanciaFinal(quantidadeDistanciaFinal);
      }else
      {
         baseCalculoImovelRuralVo.setQuantidadeDistanciaFinal(0);
      }
      int percentualAtividadeInicial = StringUtil.toInt(request.getParameter(CAMPO_PERCENTUAL_ATIVIDADE_INICAL));
      if (Validador.isNumericoValido(percentualAtividadeInicial))
      {
         baseCalculoImovelRuralVo.setPercentualAtividadeInicial(percentualAtividadeInicial);
      }else
      {
         baseCalculoImovelRuralVo.setPercentualAtividadeInicial(0);
      }
      
      int percentualAtividadeFinal = StringUtil.toInt(request.getParameter(CAMPO_PERCENTUAL_ATIVIDADE_FINAL));
      if (Validador.isNumericoValido(percentualAtividadeFinal))
      {
         baseCalculoImovelRuralVo.setPercentualAtividadeFinal(percentualAtividadeFinal);
      }else
      {
         baseCalculoImovelRuralVo.setPercentualAtividadeFinal(0);
      }
      int percentualAreaExploradaInical = StringUtil.toInt(request.getParameter(CAMPO_PERCENTUAL_AREA_EXPLORA_INICAL));
      if (Validador.isNumericoValido(percentualAreaExploradaInical))
      {
         baseCalculoImovelRuralVo.setPercentualAreaExploradaInical(percentualAreaExploradaInical);
      }else
      {
         baseCalculoImovelRuralVo.setPercentualAreaExploradaInical(0);
      }
      int percentualAreaExploradaFinal = StringUtil.toInt(request.getParameter(CAMPO_PERCENTUAL_AREA_EXPLORA_FINAL));
      if (Validador.isNumericoValido(percentualAreaExploradaFinal))
      {
         baseCalculoImovelRuralVo.setPercentualAreaExploradaFinal(percentualAreaExploradaFinal);
      }else
      {
         baseCalculoImovelRuralVo.setPercentualAreaExploradaFinal(0);
      }
      int numeroItem = StringUtil.toInt(request.getParameter(CAMPO_NUMERO_ITEM));
      if (Validador.isNumericoValido(numeroItem))
      {
         baseCalculoImovelRuralVo.setNumeroItem(numeroItem);
      }
      double percentualBaseCalculoMinimo = 
         StringUtil.monetarioToDouble(request.getParameter(CAMPO_PERCENTUAL_BASE_CALCULO_MINIMO));
      if (Validador.isNumericoValido(percentualBaseCalculoMinimo))
      {
         baseCalculoImovelRuralVo.setPercentualBaseCalculoMinimo(percentualBaseCalculoMinimo);
      }
      int criterioBaseCalculo = StringUtil.toInt(request.getParameter(CAMPO_SELECT_CRITERIO_BASE_CALCULO));
      if (Validador.isNumericoValido(criterioBaseCalculo))
      {
         baseCalculoImovelRuralVo.setCriterioBaseCalculo(new DomnCriterioBaseCalculo(criterioBaseCalculo));
      }

      return baseCalculoImovelRuralVo;
   }

   private void inativarBaseCalculoImovelRural(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, 
         RegistroExistenteException, LogException, AnotacaoException, PersistenciaException
   {
   
      BaseCalculoImovelRuralBe baseCalculoImovelRuralBe = null;
      BaseCalculoImovelRuralVo baseCalculoImovelRuralVo = null;
      try
      {

         Long codigo = Long.parseLong(request.getParameter(BOTAO_INATIVAR_BASE_CALCULO_IMOVEL_RURAL));
         BaseCalculoImovelRuralVo baseCalculoImovelRuralVoInativar = new BaseCalculoImovelRuralVo(codigo);
         baseCalculoImovelRuralBe = new BaseCalculoImovelRuralBe();
         baseCalculoImovelRuralBe.inativarBaseCalculoImovelRural(baseCalculoImovelRuralVoInativar);
         
         baseCalculoImovelRuralVo = new BaseCalculoImovelRuralVo();
         obterInformacoesLogSefaz(request, baseCalculoImovelRuralVo);
         baseCalculoImovelRuralBe.listarBaseCalculoImovelRuralAtiva(baseCalculoImovelRuralVo);
         baseCalculoImovelRuralBe.ordernarListaParaExibirNaView(baseCalculoImovelRuralVo);
         request.setAttribute(BASE_CALCULO_IMOVEL_RURAL_VO, baseCalculoImovelRuralVo);
         processFlow(VIEW_INCLUIR_BASE_CALCULO_IMOVEL_RURAL, request, response, FORWARD);
      }
      catch (ParametroObrigatorioException e)
      {
         //getBuffer(request).setAttribute(DISTANCIA_VO, distanciaVo, request);
         request.setAttribute(EXCEPTION, e);
         processFlow(VIEW_INCLUIR_BASE_CALCULO_IMOVEL_RURAL, request, response, FORWARD);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (baseCalculoImovelRuralBe != null)
         {
            baseCalculoImovelRuralBe.close();
            baseCalculoImovelRuralBe = null;
         }
      }
   }
}
