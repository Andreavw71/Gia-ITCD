<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsulta"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%
	pageContext.setAttribute("DOACAO", new Integer(DomnTipoProcesso.DOACAO));
   pageContext.setAttribute("INVENTARIO_ARROLAMENTO", new Integer(DomnTipoProcesso.INVENTARIO_ARROLAMENTO));
	pageContext.setAttribute("SEPARACAO_DIVORCIO_PARTILHA", new Integer(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA));
%>
<%@ page import="sefaz.mt.arrecadacao.integracao.IntegracaoArrecadacao"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
   <head>
      <title><abaco:tituloSistema></abaco:tituloSistema></title>
         <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
         <META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
         <META HTTP-EQUIV=Cache-Control content=no-store>
         <META HTTP-EQUIV=Pragma content=no-cache>
         <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
         <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script> 
         <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
         <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
         <style rel="stylesheet" type="text/css">
               .SEFAZ-TD-RotuloSaidaCenter , .SEFAZ-TD-RotuloSaida {
                  background: white !important;
               }
         </style>
   </head>
   <body class="SEFAZ-Body" onload=" verificaErro();" >
      <!-- padrao sefaz - cabeçalho e página de erro -->
      <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
      <jsp:include page="/util/ViewVerificaErro.jsp"/>
      <!-- padrao sefaz - cabeçalho e página de erro -->
     
      <form name="form" method="POST" action="">
          <br/>
          <br/>
          <jsp:include page="/giaitcd/util/ViewMensagemDemonstrativoCalculo.jsp"/>
          <br/>
          <br/>
          <abaco:botaoVoltar nomeContadorSubmit="incluirGIA"></abaco:botaoVoltar>
      </form>
         <c:if test="${ giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == DOACAO}">
            <script type="text/javascript" language="javascript">        
               function gerarDAR()
               {
                  // existe uma definição que no ITCD todos os pdf's deverão ser abertos em popup
                  document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.disabled = true;		
                  document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.className = "SEFAZ-INPUT-Botao-Disabled";
                  document.form.action='<%=FormAcesso.getUrlServletOriginal( FormAcesso.CODIGO_PESQUISAR_GIAITCD_DOACAO , request)+"?"+Form.BOTAO_IMPRIMIR_DAR_MOD_ABERTO+"=1"%>';
                  document.form.submit();
               }
            
               function imprimirDAR()
               {
                  document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.disabled = true;
                  document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.className = 'SEFAZ-INPUT-Botao-Disabled';
                  document.form.action='<%=IntegracaoArrecadacao.EMISSAO_DAR_URL%>?<%=IntegracaoArrecadacao.EMISSAO_DAR_PARAMETRO%>='+'<c:out value="${giaITCDVo.giaITCDDarVo.darEmitido.numrDarSeqc}" />'+'&'+'<%=IntegracaoArrecadacao.EMISSAO_DAR_JANELA%>'+'=1';		
                  window.open('','darITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
                  document.form.target = 'darITCD';
                  document.form.submit();
                  document.form.target = '_self';
               }           
               
               function imprimirDeclaracaoIsencao()
               {
                  // existe uma definição que no ITCD todos os pdf's deverão ser abertos em popup
                  document.form.<%=Form.BOTAO_IMPRIMIR%>.disabled = true;
                  document.form.<%=Form.BOTAO_IMPRIMIR%>.className = "SEFAZ-INPUT-Botao-Disabled";
                  window.open('','relatorioGIAITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
                  document.form.target = 'relatorioGIAITCD';
                  document.form.action='<%=FormAcesso.getUrlServletOriginal( FormAcesso.CODIGO_PESQUISAR_GIAITCD_DOACAO , request)+"?"+Form.BOTAO_IMPRIMIR_DECLARACAO_ISENCAO+"=1"%>';
                  document.form.<%=Form.BOTAO_IMPRIMIR%>.disabled = false;
                  document.form.<%=Form.BOTAO_IMPRIMIR%>.className = "SEFAZ-INPUT-Botao";
                  document.form.submit();
                  document.form.target = '_self';
               }
         </script>
      </c:if>
      <c:if test="${ giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == INVENTARIO_ARROLAMENTO}">
            <script type="text/javascript" language="javascript">      
                  function gerarDAR()
                  {
                     // existe uma definição que no ITCD todos os pdf's deverão ser abertos em popup
                     document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.disabled = true;
                     document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.className = "SEFAZ-INPUT-Botao-Disabled";
                     document.form.action='<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_PESQUISAR_GIAITCD_INVENTARIO_ARROLAMENTO, request)+"?"+Form.BOTAO_IMPRIMIR_DAR_MOD_ABERTO+"=1"%>';
                     document.form.submit();		
                  }
                  
                  function imprimirDAR()
                  {
                     document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.disabled = true;
                     document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.className = 'SEFAZ-INPUT-Botao-Disabled';
                  
                     document.form.action='<%=IntegracaoArrecadacao.EMISSAO_DAR_URL%>?<%=IntegracaoArrecadacao.EMISSAO_DAR_PARAMETRO%>='+'<c:out value="${giaITCDVo.giaITCDDarVo.darEmitido.numrDarSeqc}" />'+'&'+'<%=IntegracaoArrecadacao.EMISSAO_DAR_JANELA%>'+'=1';		
                     window.open('','darITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
                     document.form.target = 'darITCD';
                     document.form.submit();
                     document.form.target = '_self';
                  }
                  
                  function imprimirDeclaracaoIsencao()
                  {
                     // existe uma definição que no ITCD todos os pdf's deverão ser abertos em popup
                     document.form.<%=Form.BOTAO_IMPRIMIR%>.disabled = true;
                     document.form.<%=Form.BOTAO_IMPRIMIR%>.className = "SEFAZ-INPUT-Botao-Disabled";
                     window.open('','relatorioGIAITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
                     document.form.target = 'relatorioGIAITCD';
                     document.form.action='<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_PESQUISAR_GIAITCD_INVENTARIO_ARROLAMENTO, request)+"?"+Form.BOTAO_IMPRIMIR_DECLARACAO_ISENCAO+"=1"%>';
                     document.form.<%=Form.BOTAO_IMPRIMIR%>.disabled = false;
                     document.form.<%=Form.BOTAO_IMPRIMIR%>.className = "SEFAZ-INPUT-Botao";
                     document.form.submit();
                     document.form.target = '_self';
                  } 
            </script>
      </c:if>
      <c:if test="${ giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == SEPARACAO_DIVORCIO_PARTILHA}">
            <script type="text/javascript" language="javascript">
                     function gerarDAR()
                     {                       
                        // existe uma definição que no ITCD todos os pdf's deverão ser abertos em popup 
                        document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.disabled = true;
                        document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.className = "SEFAZ-INPUT-Botao-Disabled";
                        document.form.action='<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_PESQUISAR_GIAITCD_SEPARACAO_DIVORCIO, request)+"?"+Form.BOTAO_IMPRIMIR_DAR_MOD_ABERTO+"=1"%>';
                        document.form.submit();
                     }
                     
                     function imprimirDAR()
                     {
                        document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.disabled = true;
                        document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.className = 'SEFAZ-INPUT-Botao-Disabled';
                  
                        document.form.action='<%=IntegracaoArrecadacao.EMISSAO_DAR_URL%>?<%=IntegracaoArrecadacao.EMISSAO_DAR_PARAMETRO%>='+'<c:out value="${giaITCDVo.giaITCDDarVo.darEmitido.numrDarSeqc}" />'+'&'+'<%=IntegracaoArrecadacao.EMISSAO_DAR_JANELA%>'+'=1';		
                        window.open('','darITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
                        document.form.target = 'darITCD';
                        document.form.submit();
                        document.form.target = '_self';
                     }                   
                     
                     function imprimirDeclaracaoIsencao()
                     {
                        // existe uma definição que no ITCD todos os pdf's deverão ser abertos em popup
                        document.form.<%=Form.BOTAO_IMPRIMIR%>.disabled = true;
                        document.form.<%=Form.BOTAO_IMPRIMIR%>.className = "SEFAZ-INPUT-Botao-Disabled";
                        window.open('','relatorioGIAITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
                        document.form.target = 'relatorioGIAITCD';
                        document.form.action='<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_PESQUISAR_GIAITCD_SEPARACAO_DIVORCIO, request)+"?"+Form.BOTAO_IMPRIMIR_DECLARACAO_ISENCAO+"=1"%>';
                        document.form.<%=Form.BOTAO_IMPRIMIR%>.disabled = false;
                        document.form.<%=Form.BOTAO_IMPRIMIR%>.className = "SEFAZ-INPUT-Botao";
                        document.form.submit();
                        document.form.target = '_self';
                     }
                     
                     function imprimirDeclaracaoFatoGerador()
                     {
                        // existe uma definição que no ITCD todos os pdf's deverão ser abertos em popup
                        document.form.<%=Form.BOTAO_IMPRIMIR%>.disabled = true;
                        document.form.<%=Form.BOTAO_IMPRIMIR%>.className = "SEFAZ-INPUT-Botao-Disabled";
                        window.open('','relatorioGIAITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
                        document.form.target = 'relatorioGIAITCD';
                        document.form.action='<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_PESQUISAR_GIAITCD_SEPARACAO_DIVORCIO, request)+"?"+Form.BOTAO_IMPRIMIR_DECLARACAO_FATO_GERADOR+"=1"%>';
                        document.form.<%=Form.BOTAO_IMPRIMIR%>.disabled = false;
                        document.form.<%=Form.BOTAO_IMPRIMIR%>.className = "SEFAZ-INPUT-Botao";
                        document.form.submit();
                        document.form.target = '_self';
                     }
            </script>
      </c:if>
      
      <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<c:if test="${giaITCDVo.imprimirDar}">
			<script type="text/javascript" language="javascript">
				imprimirDAR();
			</script>
		</c:if>
      </table>
      <!-- FIM: botões de ações -->
      <br/>
      <br/>
      <!-- bloco de mensagem -->
      <abaco:mensagemAguardeCarregando></abaco:mensagemAguardeCarregando>
      <!-- FIM: bloco de mensagem -->
      <g:mostrarRodape/>
   </body>
</html>