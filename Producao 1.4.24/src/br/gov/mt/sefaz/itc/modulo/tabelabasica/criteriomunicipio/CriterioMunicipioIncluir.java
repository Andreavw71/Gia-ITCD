package br.gov.mt.sefaz.itc.modulo.tabelabasica.criteriomunicipio;

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

import br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio.CriterioMunicipioBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio.CriterioMunicipioVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio.Form;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CriterioMunicipioIncluir extends AbstractAbacoServlet implements Flow, Form
{
   private static final int REQUISICAO_INCLUIR_CRITERIO_MUNICIPIO = 2;
   private static final int REQUISICAO_INATIVAR_CRITERIO_MUNICIPIO = 3;

   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, LogException, 
         AnotacaoException, PersistenciaException, RegistroExistenteException, ConsultaException, IntegracaoException
   {
      switch (redirecionar(request))
      {
         case REQUISICAO_VAZIA:
            {
               solicitarIncluirCriterioMunicipio(request, response);
               break;
            }
         case REQUISICAO_INCLUIR_CRITERIO_MUNICIPIO:
            {
               incluirCriterioMunicipio(request, response);
               break;
            }
         case REQUISICAO_INATIVAR_CRITERIO_MUNICIPIO:
            {
               //inativarCriterioMunicipio(request, response);
               alterarCriterioMunicipio(request, response);
               break;
            }
      }
   }

   protected int redirecionar(HttpServletRequest request)
   {
      if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_CRITERIO_MUNICIPIO)))
      {
         return REQUISICAO_INCLUIR_CRITERIO_MUNICIPIO;
      }
      else if (Validador.isStringValida(request.getParameter(BOTAO_EXCLUIR_CRITERIO_MUNICIPIO)))
      {
         return REQUISICAO_INATIVAR_CRITERIO_MUNICIPIO;
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
    */
   private void solicitarIncluirCriterioMunicipio(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
   {
      // Obejto criado para evitar o excesso de consulta no sistema de CADASTRO.
      MunicipioIntegracaoVo mi = (MunicipioIntegracaoVo)  getBuffer(request).getAttribute(MUNICIPIO_INTEGRACAO_VO);
      removeBuffer(request);
      CriterioMunicipioVo criterioMunicipioVo = null;
      CriterioMunicipioBe criterioMunicipioBe = null;
      try
      {

         criterioMunicipioVo = new CriterioMunicipioVo();
         if (Validador.isObjetoValido(mi))         {
            criterioMunicipioVo.getMunicipio().setCollVO(mi.getCollVO());
         }
         criterioMunicipioBe = new CriterioMunicipioBe();
         criterioMunicipioBe.listarCriterioMunicipioAtiva(criterioMunicipioVo);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (criterioMunicipioBe != null)
         {
            criterioMunicipioBe.close();
            criterioMunicipioBe = null;
         }
      }
      getBuffer(request).setAttribute(CRITERIO_MUNICIPIO_VO, criterioMunicipioVo, request);
      getBuffer(request).setAttribute(MUNICIPIO_INTEGRACAO_VO, criterioMunicipioVo.getMunicipio(), request);
      processFlow(VIEW_INCLUIR_CRITERIO_MUNICIPIO, request, response, FORWARD);
   }

   /**
    * Este método tem por objetivo iniciar o processo
    * de inclusão de registro no BD e retornar a página.
    * 
    * @param request
    * @param response
    */
   private void incluirCriterioMunicipio(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, LogException, 
         AnotacaoException, PersistenciaException, RegistroExistenteException, ConsultaException, IntegracaoException
   {
      CriterioMunicipioVo criterioMunicipioVo = controladorInterativoJSP(request);
      Validador.isObjetoValido(criterioMunicipioVo);
      CriterioMunicipioBe criterioMunicipioBe = null;
      CriterioMunicipioVo criterioMunicipioVoConsulta = null;

      try
      {
         int codigoMunicipio = Integer.parseInt(request.getParameter(BOTAO_ADICIONAR_CRITERIO_MUNICIPIO));
         criterioMunicipioBe = new CriterioMunicipioBe();
         criterioMunicipioVoConsulta = new CriterioMunicipioVo();
         obterInformacoesLogSefaz(request, criterioMunicipioVo);
         criterioMunicipioVoConsulta.getMunicipio().setCodgMunicipio(codigoMunicipio);
         criterioMunicipioVo.setParametroConsulta(criterioMunicipioVoConsulta);
         criterioMunicipioBe.incluirCriterioMunicipio(criterioMunicipioVo);

         criterioMunicipioVo = new CriterioMunicipioVo();

         criterioMunicipioBe.listarCriterioMunicipioAtiva(criterioMunicipioVo);
         request.setAttribute(CRITERIO_MUNICIPIO_VO, criterioMunicipioVo);
         processFlow(VIEW_INCLUIR_CRITERIO_MUNICIPIO, request, response, FORWARD);
      }
      catch (ParametroObrigatorioException e)
      {
         getBuffer(request).setAttribute(CRITERIO_MUNICIPIO_VO, criterioMunicipioVo, request);
         request.setAttribute(EXCEPTION, e);
         processFlow(VIEW_INCLUIR_CRITERIO_MUNICIPIO, request, response, FORWARD);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (criterioMunicipioBe != null)
         {
            criterioMunicipioBe.close();
            criterioMunicipioBe = null;
         }
      }
   }

   private CriterioMunicipioVo controladorInterativoJSP(HttpServletRequest request)
   {
      CriterioMunicipioVo criterioMunicipioVo = (CriterioMunicipioVo) getBuffer(request).getAttribute(CRITERIO_MUNICIPIO_VO);
      Validador.isObjetoValido(criterioMunicipioVo);
      double valorTotalMinimo = StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_MINIMO));
      if (Validador.isNumericoValido(valorTotalMinimo))
      {
         criterioMunicipioVo.setValorTotalMinimo(valorTotalMinimo);
      }
      double valorTotalMedio = StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_MEDIO));
      if (Validador.isNumericoValido(valorTotalMedio))
      {
         criterioMunicipioVo.setValorTotalMedio(valorTotalMedio);
      }
      double valorTotalMaximo = StringUtil.monetarioToDouble(request.getParameter(CAMPO_VALOR_MAXIMO));
      if (Validador.isNumericoValido(valorTotalMaximo))
      {
         criterioMunicipioVo.setValorTotalMaximo(valorTotalMaximo);
      }
      return criterioMunicipioVo;
   }

   private void inativarCriterioMunicipio(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, LogException, 
         AnotacaoException, PersistenciaException, ConsultaException, IntegracaoException
   {
      CriterioMunicipioBe criterioMunicipioBe = null;
      CriterioMunicipioVo criterioMunicipioVo = null;
      CriterioMunicipioVo criterioMunicipioVoInativar = null;
      try
      {
         Long codigo = Long.parseLong(request.getParameter(BOTAO_EXCLUIR_CRITERIO_MUNICIPIO));
         criterioMunicipioVoInativar = new CriterioMunicipioVo(codigo);
         criterioMunicipioBe = new CriterioMunicipioBe();
         criterioMunicipioBe.inativarCriterioMunicipio(criterioMunicipioVoInativar);

         //criterioMunicipioVo = new CriterioMunicipioVo();
         criterioMunicipioBe.listarCriterioMunicipioAtiva(criterioMunicipioVo);

         request.setAttribute(CRITERIO_MUNICIPIO_VO, criterioMunicipioVo);
         processFlow(VIEW_INCLUIR_CRITERIO_MUNICIPIO, request, response, FORWARD);
      }
      catch (ParametroObrigatorioException e)
      {
         getBuffer(request).setAttribute(CRITERIO_MUNICIPIO_VO, criterioMunicipioVo, request);
         request.setAttribute(EXCEPTION, e);
         processFlow(VIEW_INCLUIR_CRITERIO_MUNICIPIO, request, response, FORWARD);
      }
      catch (SQLException e)
      {
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (criterioMunicipioBe != null)
         {
            criterioMunicipioBe.close();
            criterioMunicipioBe = null;
         }
      }
   }

   private void alterarCriterioMunicipio(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         ObjetoObrigatorioException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, LogException, 
         AnotacaoException, PersistenciaException, ConsultaException, IntegracaoException
   {
      CriterioMunicipioBe criterioMunicipioBe = null;
      CriterioMunicipioVo criterioMunicipioVo = null;
      CriterioMunicipioVo criterioMunicipioVoConsulta = null;
      try
      {
         int codigoMunicipio = Integer.parseInt(request.getParameter(BOTAO_EXCLUIR_CRITERIO_MUNICIPIO));
         criterioMunicipioVoConsulta = new CriterioMunicipioVo();
         criterioMunicipioVoConsulta.setStatusResgistroCriterioMunicipioVo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
         criterioMunicipioVoConsulta.getMunicipio().setCodgMunicipio(codigoMunicipio);
         criterioMunicipioBe = new CriterioMunicipioBe();
         criterioMunicipioVo = new CriterioMunicipioVo(criterioMunicipioVoConsulta);
         criterioMunicipioBe.prepararParaAlterarCriterioMunicipio(criterioMunicipioVo);

         //criterioMunicipioVo = new CriterioMunicipioVo();
         criterioMunicipioBe.listarCriterioMunicipioAtiva(criterioMunicipioVo);

         request.setAttribute(CRITERIO_MUNICIPIO_VO, criterioMunicipioVo);
         processFlow(VIEW_INCLUIR_CRITERIO_MUNICIPIO, request, response, FORWARD);
      }
      catch (ParametroObrigatorioException e)
      {
         getBuffer(request).setAttribute(CRITERIO_MUNICIPIO_VO, criterioMunicipioVo, request);
         request.setAttribute(EXCEPTION, e);
         processFlow(VIEW_INCLUIR_CRITERIO_MUNICIPIO, request, response, FORWARD);
      }
      catch (SQLException e)
      {
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      finally
      {
         if (criterioMunicipioBe != null)
         {
            criterioMunicipioBe.close();
            criterioMunicipioBe = null;
         }
      }
   }

}
