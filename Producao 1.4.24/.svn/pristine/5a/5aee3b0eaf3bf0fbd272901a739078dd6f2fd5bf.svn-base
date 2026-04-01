package br.gov.mt.sefaz.itc.modulo.tabelabasica.iptuprefeitura;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.cadastro.model.municipio.MunicipioVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu.ImportacaoIPTUBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu.ImportacaoIPTUVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptu.IPTUBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptu.IPTUVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptuprefeitura.Form;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnGeracaoServico;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoIPTU;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.integracao.acessoweb.AcessoWebBe;
import br.gov.mt.sefaz.itc.util.integracao.acessoweb.UsuarioIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;

import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.GestaoPessoasBe;

import java.io.File;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sefaz.mt.acessoweb.integracao.IntegracaoAcessoWeb;


public class IPTUPrefeituraArquivoPesquisar   extends AbstractAbacoServlet implements Flow, Form
{

   private static final int REQUISICAO_CONSULTAR_ARQUIVO_PREFEITURA = 2;
   private static final int REQUISICAO_PROCESSAR_ARQUIVO_PREFEITURA = 3;
   private static final String XLSX = ".xlsx";
   private static final String XLS = ".xls";
   
   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException, 
             SQLException, IntegracaoException
   {
      switch (redirecionar(request))
      {
         case REQUISICAO_VAZIA:
            {
               solicitarListarrArquivoIptu(request, response);
               break;
            }
         case REQUISICAO_CONSULTAR_ARQUIVO_PREFEITURA:
            {
               solicitaConsultarArquivoIptu(request, response);
               break;
            }
         case REQUISICAO_PROCESSAR_ARQUIVO_PREFEITURA:
            {
               solicitarArquivoIptu(request, response);
               break;
            }
      }
   }

   protected int redirecionar(HttpServletRequest request)
   {


      if (Validador.isStringValida(request.getParameter(BOTAO_PESQUISAR)))
      {
         return REQUISICAO_CONSULTAR_ARQUIVO_PREFEITURA;
      }      
      if (Validador.isStringValida(request.getParameter(CAMPO_HIDDEN_OBTER_ARQUIVO_PREFEITURA)))
      {
         return REQUISICAO_PROCESSAR_ARQUIVO_PREFEITURA;
      }

      return REQUISICAO_VAZIA;
   }

   private void solicitarListarrArquivoIptu(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      removeBuffer(request);
      CadastroBe cadastroBe = null;
      Collection listaMunicipios = new ArrayList();

      try
      {
         selecionarMunicipioIntegrado(listaMunicipios);
         getBuffer(request).setAttribute(IMPORTACAO_IPTU_VO, new ImportacaoIPTUVo(), request);
         getBuffer(request).setAttribute(CAMPO_MUNICIPIOS_ATIVOS, listaMunicipios, request);
         processFlow(VIEW_CONSULTAR_ARQUIVO_IPTU_PREFEITURA, request, response, FORWARD);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      catch (Exception erro)
      {
         request.setAttribute(EXCEPTION, erro);
         processFlow(VIEW_ERRO, request, response, FORWARD);
      }
      finally
      {
         if (cadastroBe != null)
         {
            cadastroBe.close();
            cadastroBe = null;
         }
      }
   }

   private Collection selecionarMunicipioIntegrado(Collection listaMunicipio)
      throws Exception
   {
      IPTUBe iPTUBe = null;
      IPTUVo iptuVo = null;
      CadastroBe cadastroBe = null;
      
      MunicipioIntegracaoVo municipioIntegracaoVo = null;

      try
      {
         iPTUBe = new IPTUBe();
         iptuVo = new IPTUVo();
         iptuVo.setTipoITPU(new DomnTipoIPTU(DomnTipoIPTU.INTEGRADO));
         iptuVo.setStatusResgistroIPTU(new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
         iptuVo.setParametroConsulta(iptuVo);
         iPTUBe.listarIPTUfind(iptuVo);
         cadastroBe = new CadastroBe();

         Iterator ite = iptuVo.getCollVO().iterator();
         while (ite.hasNext())
         {
            IPTUVo iptuVoAtual = (IPTUVo) ite.next();
            municipioIntegracaoVo = new MunicipioIntegracaoVo();
            municipioIntegracaoVo.setCodgMunicipio(iptuVoAtual.getMunicipio().getCodgMunicipio());          
            municipioIntegracaoVo = cadastroBe.obterMunicipioPorCodigo(municipioIntegracaoVo);
            listaMunicipio.add(municipioIntegracaoVo);            
         }
      }

      catch (Exception e)
      {
         throw e;
      }
      finally
      {
         if(cadastroBe != null)
         {
            cadastroBe.close();
         }
      }
      return listaMunicipio;
   }

   private void solicitaConsultarArquivoIptu(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException, 
             SQLException, IntegracaoException
   {
     
      ImportacaoIPTUBe importacaoIPTUBe = null;
      CadastroBe cadastroBe = null;
      Collection listaMunicipios = new ArrayList();
      ImportacaoIPTUVo importacaoIPTUVo = null;

      try
      {         
         importacaoIPTUVo = controladorInterativoJSP(request);
         Validador.isObjetoValido(importacaoIPTUVo);
         obterInformacoesLogSefaz(request, importacaoIPTUVo);
         importacaoIPTUBe =  new ImportacaoIPTUBe();          
         //Prepara as informações para a consulta 
         //importacaoIPTUVo.setStatusImportacao(new DomnGeracaoServico(DomnGeracaoServico.PROCESSADO));
         importacaoIPTUVo.setParametroConsulta(importacaoIPTUVo);
         //Consultando os Relatorios por Municipio
         importacaoIPTUBe.listImportacao(importacaoIPTUVo);
         validarImportacao(importacaoIPTUVo);
         //Consultando dados do Municipio
         obterDadosMunicipio(importacaoIPTUVo);
         //Consultando dados do Servidor
         obterDadosServidor(importacaoIPTUVo);
         cadastroBe = new CadastroBe();
         //listaMunicipios = cadastroBe.listarMunicipiosAtivos();         
         selecionarMunicipioIntegrado(listaMunicipios);
         getBuffer(request).setAttribute(CAMPO_MUNICIPIOS_ATIVOS, listaMunicipios, request);
         getBuffer(request).setAttribute(IMPORTACAO_IPTU_VO,importacaoIPTUVo , request);
         processFlow(VIEW_CONSULTAR_ARQUIVO_IPTU_PREFEITURA, request, response,FORWARD);
      }
      
      catch (ParametroObrigatorioException e)
      {
         cadastroBe = new CadastroBe();         
         listaMunicipios = cadastroBe.listarMunicipiosAtivos();
         request.setAttribute(EXCEPTION, e);
         getBuffer(request).setAttribute(IMPORTACAO_IPTU_VO,importacaoIPTUVo , request);
         getBuffer(request).setAttribute(CAMPO_MUNICIPIOS_ATIVOS, listaMunicipios, request);
         processFlow(VIEW_CONSULTAR_ARQUIVO_IPTU_PREFEITURA, request, response,FORWARD);
      }
     
      
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      catch (Exception erro)
      {
         request.setAttribute(EXCEPTION, erro);
         processFlow(VIEW_ERRO, request, response, FORWARD);
      }
      finally
      {
         if (importacaoIPTUBe != null)
         {
            importacaoIPTUBe.close();
            importacaoIPTUBe = null;
         }
      }
   }   
   
   private void solicitarArquivoIptu(HttpServletRequest request, HttpServletResponse response) throws ConexaoException, 
         PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException, 
             SQLException, IntegracaoException
   {
     
      ImportacaoIPTUBe importacaoIPTUBe = null;         
      try
      {  
        
         ImportacaoIPTUVo importacaoIPTUVo = controladorInterativoJSP(request);
         Validador.isObjetoValido(importacaoIPTUVo);
         obterInformacoesLogSefaz(request, importacaoIPTUVo);
         importacaoIPTUBe =  new ImportacaoIPTUBe();          
         //Prepara as informações para a consulta 
         importacaoIPTUVo.setStatusImportacao(new DomnGeracaoServico(DomnGeracaoServico.PROCESSADO));
         importacaoIPTUVo.setParametroConsulta(importacaoIPTUVo);
         //Consultando os Relatorios por Municipio
         importacaoIPTUBe.consultarImportacao(importacaoIPTUVo);
         
         if (verificarExtensao(importacaoIPTUVo.getDescNomeArquivoAntigo()).equals(XLSX))
         {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition","attachment; filename=" + importacaoIPTUVo.getDescNomeArquivoAntigo());
         }
         if (verificarExtensao(importacaoIPTUVo.getDescNomeArquivoAntigo()).equals(XLS))
         {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition","attachment; filename=" + importacaoIPTUVo.getDescNomeArquivoAntigo());
         }
         //response.setHeader("Content-Disposition", "attachment; filename=Relatório-De-Omissos-");
         response.getOutputStream().write(importacaoIPTUVo.getArqvIptu());
         response.getOutputStream().flush();
         response.getOutputStream().close();   
         
         
        
      }         
      
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      catch (Exception erro)
      {
         request.setAttribute(EXCEPTION, erro);
         processFlow(VIEW_ERRO, request, response, FORWARD);
      }
      finally
      {
         if (importacaoIPTUBe != null)
         {
            importacaoIPTUBe.close();
            importacaoIPTUBe = null;
         }
      }
   }   
   /**
    * 
    * @param importacaoIPTUVo
    * @return
    * @Função Busca dados do Municipio
    */
   private ImportacaoIPTUVo obterDadosMunicipio(ImportacaoIPTUVo importacaoIPTUVo)
      throws Exception
   {
      CadastroBe cadastroBe = null;      
      Validador.isObjetoValido(importacaoIPTUVo);
      try
      {
         Iterator ite = importacaoIPTUVo.getCollVO().iterator();
         cadastroBe = new CadastroBe();        
         while (ite.hasNext())
         {
            ImportacaoIPTUVo importacaoIPTUVoAtual = (ImportacaoIPTUVo) ite.next();
            importacaoIPTUVoAtual.setMunicipioVo(cadastroBe.obterMunicipioPorCodigo(importacaoIPTUVoAtual.getMunicipioVo()));            
         }
      }
      catch (Exception erro)
      {
         throw erro;
      }
      finally
      {
         if (cadastroBe != null)
         {
            cadastroBe.close();
         }
      }
      return importacaoIPTUVo;
   }
   /**
    * 
    * @param importacaoIPTUVo
    * @return
    * @Função Busca dados do Servidor
    */
   private ImportacaoIPTUVo obterDadosServidor(ImportacaoIPTUVo importacaoIPTUVo)
      throws Exception
   {
      CadastroBe cadastroBe = null;
      GestaoPessoasBe gestaoPessoasBe = null;
      AcessoWebBe acessoWebBe = null;
      UsuarioIntegracaoVo usuarioIntegracaoVo = null;
      Validador.isObjetoValido(importacaoIPTUVo);
      try
      {
         Iterator ite = importacaoIPTUVo.getCollVO().iterator();        
        // cadastroBe = new CadastroBe();
         gestaoPessoasBe = new GestaoPessoasBe();
         while (ite.hasNext())
         {
            ImportacaoIPTUVo importacaoIPTUVoAtual = (ImportacaoIPTUVo) ite.next();        
            //Preparando Dados Para Consulta::..
            usuarioIntegracaoVo = new UsuarioIntegracaoVo();
            usuarioIntegracaoVo.setCodigo((int)importacaoIPTUVoAtual.getCodgUsuarioSeq());
            usuarioIntegracaoVo.setParametroConsulta(usuarioIntegracaoVo);
            //Consultando Dados do Contribuinte no Acesso WEB::..
            acessoWebBe = new AcessoWebBe();
            usuarioIntegracaoVo = acessoWebBe.obterUsuarioPorCodigo(usuarioIntegracaoVo);         
            importacaoIPTUVoAtual.getServidorSefazIntegracaoVo().setNomeServidor(usuarioIntegracaoVo.getNome());
           
         }
      }
      catch (Exception erro)
      {
         throw erro;
      }
      finally
      {
         if (cadastroBe != null)
         {
            cadastroBe.close();
         }
         if (acessoWebBe != null)
         {
            acessoWebBe.close();
         }
      }
      return importacaoIPTUVo;
   }

   private ImportacaoIPTUVo controladorInterativoJSP(HttpServletRequest request)
      throws ObjetoObrigatorioException, ParametroObrigatorioException, 
             Exception
   {

      CadastroBe cadastroBe = null;
      MunicipioIntegracaoVo municipioIntegracaoVo = null;

      ImportacaoIPTUVo importacaoIPTUVo = (ImportacaoIPTUVo) getBuffer(request).getAttribute(IMPORTACAO_IPTU_VO);
      Validador.validaObjeto(importacaoIPTUVo);
      try
      {
         cadastroBe = new CadastroBe();

         if (Validador.isStringValida(request.getParameter(CAMPO_MUNICIPIOS_ATIVOS)))
         {
            municipioIntegracaoVo = new MunicipioIntegracaoVo();
            municipioIntegracaoVo.setCodgMunicipio((Integer.parseInt(request.getParameter(CAMPO_MUNICIPIOS_ATIVOS))));
            municipioIntegracaoVo = cadastroBe.obterMunicipioPorCodigo(municipioIntegracaoVo);
            importacaoIPTUVo.setMunicipioVo(municipioIntegracaoVo);
            importacaoIPTUVo.setCollVO(null);
         }
         if(Validador.isStringValida((request.getParameter(CAMPO_HIDDEN_OBTER_ARQUIVO_PREFEITURA))))
         {
            importacaoIPTUVo = new  ImportacaoIPTUVo();
            importacaoIPTUVo.setCodigo(Integer.parseInt(request.getParameter(CAMPO_CONSULTAR_ARQUIVO_IPTU)));
         }
       
      }

      finally
      {
         if (cadastroBe != null)
         {
            cadastroBe.close();
            municipioIntegracaoVo = null;
         }
      }
      
      return importacaoIPTUVo;
   }
   
   private void validarImportacao(ImportacaoIPTUVo importacaoIPTUVo)
      throws ObjetoObrigatorioException, ParametroObrigatorioException

   {
    if(!Validador.isCollectionValida(importacaoIPTUVo.getCollVO()))
    {
       
       throw new ParametroObrigatorioException("Não Existe Arquivo Processado para este Municipio....");
    }
   }
   public static String verificarExtensao(String nomeArquivo) {  
       
           if (nomeArquivo.length() > 1 && nomeArquivo.contains(".")) {  
               return nomeArquivo.substring(nomeArquivo.lastIndexOf("."));  
           }  
       
       return null;  
   }
 
}
