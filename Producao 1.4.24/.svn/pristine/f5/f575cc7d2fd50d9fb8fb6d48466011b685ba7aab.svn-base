package br.gov.mt.sefaz.itc.modulo.tabelabasica.importacaoiptu;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.generico.excecao.TamanhoAnexoException;
import br.gov.mt.sefaz.generico.util.http.MultipartRequest;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu.ImportacaoIPTUBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu.ImportacaoIPTUVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptu.IPTUBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.iptu.IPTUVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoIPTU;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;

import java.io.File;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;


public class ImportacaoIPTU
     extends AbstractAbacoServlet implements Flow, Form
{
   private static final int REQUISICAO_IMPORTAR_ARQUIVO_IPTU = 2;
  

   public void init(ServletConfig config)
      throws ServletException
   {
      super.init(config);
   }

 
      protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
            TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
            IntegracaoException, ParametroObrigatorioException, PersistenciaException, AnotacaoException, LogException, 
             TamanhoAnexoException, FileUploadException, Exception
   {
         switch (redirecionar(request))
         {
            case REQUISICAO_VAZIA:
               {
                  solicitarImportarArquivoIPTU(request, response);
                  break;
               }
            case REQUISICAO_IMPORTAR_ARQUIVO_IPTU:
               {
                  solicitarIncluirArquivoIPTU(request, response);
                  break;
               }
         }
   }
   
   protected int redirecionar(HttpServletRequest request)
   {
      if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
      {
         return REQUISICAO_IMPORTAR_ARQUIVO_IPTU;
      }

      else
      {
         return REQUISICAO_VAZIA;
      }
   }
   
   private void solicitarImportarArquivoIPTU(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
         TipoRedirecionamentoInvalidoException, ConexaoException, ObjetoObrigatorioException, ConsultaException, 
         IntegracaoException
   {
      removeBuffer(request);
      CadastroBe cadastroBe = null;     
      Collection listaMunicipios = new ArrayList();

      try
      {
         cadastroBe = new CadastroBe();        
         selecionarMunicipioIntegrado(listaMunicipios);          
         getBuffer(request).setAttribute(IMPORTACAO_IPTU_VO, new ImportacaoIPTUVo(), request);
         getBuffer(request).setAttribute(CAMPO_MUNICIPIOS_ATIVOS, listaMunicipios,request);
         processFlow(VIEW_INCLUIR_ARQUIVO_IPTU_PREFEITURA, request, response,FORWARD);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
      }
      catch(Exception erro)
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
   
   private void solicitarIncluirArquivoIPTU(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, ObjetoObrigatorioException, 
             ParametroObrigatorioException, TamanhoAnexoException, 
             FileUploadException, Exception
   {
      ImportacaoIPTUBe importacaoIPTUBe = null;
      ImportacaoIPTUVo importacaoIPTUVo = controladorInterativoJSP(request);
      obterInformacoesLogSefaz(request, importacaoIPTUVo);
      Validador.isObjetoValido(importacaoIPTUVo);
      
      try
      {
         importacaoIPTUBe = new ImportacaoIPTUBe();
         importacaoIPTUBe.preparaIncluirArquivoIPTU(importacaoIPTUVo);        
         importacaoIPTUBe.buscarArquivoIPTUdiretorio(importacaoIPTUVo);
         importacaoIPTUBe.incluirArquivoIPTU(importacaoIPTUVo);
         
         importacaoIPTUVo.setMensagem(MensagemSucesso.INCLUIR);          
         getBuffer(request).setAttribute(IMPORTACAO_IPTU_VO, new ImportacaoIPTUVo(), request);
         processFlow(VIEW_SUCESSO, request, response, FORWARD);
      }
      catch(Exception erro)
      {  
         importacaoIPTUVo.getArquivoIPTU().delete();
         request.setAttribute(EXCEPTION, erro);
         processFlow(VIEW_ERRO, request, response, FORWARD);
         throw erro;
      }
   }

   private ImportacaoIPTUVo controladorInterativoJSP(HttpServletRequest request)
      throws ObjetoObrigatorioException, ParametroObrigatorioException, 
             TamanhoAnexoException, FileUploadException, Exception
   {
      MultipartRequest multipartRequest;
      CadastroBe cadastroBe = null;
      MunicipioIntegracaoVo municipioIntegracaoVo = null;      
      File arquivoIPTU = null;      
      
      ImportacaoIPTUVo importacaoIPTUVo = (ImportacaoIPTUVo) getBuffer(request).getAttribute(IMPORTACAO_IPTU_VO);
      Validador.validaObjeto(importacaoIPTUVo);
      try
      {
         cadastroBe = new CadastroBe();
         multipartRequest = new MultipartRequest(request, MultipartRequest.INFINITO);
         
         if (Validador.isStringValida(multipartRequest.getParameter(CAMPO_MUNICIPIOS_ATIVOS)))
         {  
            municipioIntegracaoVo = new MunicipioIntegracaoVo();
            municipioIntegracaoVo.setCodgMunicipio((Integer.parseInt(multipartRequest.getParameter(CAMPO_MUNICIPIOS_ATIVOS))));
            municipioIntegracaoVo = cadastroBe.obterMunicipioPorCodigo(municipioIntegracaoVo);    
            importacaoIPTUVo.setMunicipioVo(municipioIntegracaoVo);
         }
         if (Validador.isStringValida(multipartRequest.getParameter(CAMPO_ARQUIVO_PROCESSAMENTO)))
         {
            arquivoIPTU = gravarArquivoIPTUdiretorio(multipartRequest);
            importacaoIPTUVo.setArquivoIPTU(arquivoIPTU);

         }
      }          
      catch (TamanhoAnexoException tamanhoException)
      {
         throw tamanhoException;
      }
      catch (FileUploadException uploadException)
      {
         throw uploadException;
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

   public File gravarArquivoIPTUdiretorio(MultipartRequest multipartRequest)
      throws Exception
   {
      
     
      String nomeDiretorioTemp = "/usr/appl/servicos/itc/ArquivosTemporarios/";
      ArrayList<String> nomesArquivos = new ArrayList<String>();
      FileItem[] todosOsArquivos = multipartRequest.getTodosArquivos();
      File arquivo = null;
      try
      {
         for (FileItem fi: todosOsArquivos)
         {
              //Coloca o nome do arquivo na lista
               nomesArquivos.add(fi.getName());
               //Cria um arquivo com o mesmo nome
               arquivo = new File(nomeDiretorioTemp + fi.getName());
               //Escreve o conteúdo no local informado
               //Caso o arquivo já exista no diretório, sobrescreve
               fi.write(arquivo);
               //Libera o espaço temporário ocupado pelo arquivo
               fi.delete();
         }       
       }
      catch (Exception e)
      {
         throw e;
      }

      return arquivo;
   }


}
