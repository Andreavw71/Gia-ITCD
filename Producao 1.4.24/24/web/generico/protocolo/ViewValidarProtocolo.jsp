<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsulta"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
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
   </head>
   <body class="SEFAZ-Body">
      <!-- padrao sefaz - cabeçalho e página de erro -->
      <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
      <jsp:include page="/util/ViewVerificaErro.jsp"/>
      <!-- padrao sefaz - cabeçalho e página de erro -->
      
      <script type="text/javascript" language="javascript">
         function submeterProtocoloGIA()
         {
            document.form.submit();
         }
      </script>
      <form name="form" method="POST" action="<c:out value="${urlValidar}"/>">
         <br/>
         <abaco:botaoConfirmar onClick="submeterProtocoloGIA()" nomeBotao="Form.BOTAO_VALIDAR_PROCESSO_EPROCESS" textoBotao="Validar Protocolo GIA-ITCD"/>
      </form>
      <br/>
   </body>
   <g:mostrarRodape/>
</html>