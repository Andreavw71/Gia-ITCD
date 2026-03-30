<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewApresentarAjuda.jsp
* Criação : Janeiro de 2008 / Wendell Pereira de Farias
* Revisão :  
* Adaptações: 
* Log : 
*/
--%>
<%--<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import="br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.Form"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<jsp:include page="/util/ViewVerificaErro.jsp"/>

<html>
  <head>
	<title><abaco:tituloSistema/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
	<META HTTP-EQUIV=Cache-Control content=no-store>
	<META HTTP-EQUIV=Pragma content=no-cache>
	<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
        <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>        
	<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
	<script type="text/javascript" language="javascript">
        
        
        function redirecionarAction()
        {
            if (('<%= request.getAttribute(Form.EXISTE)%>')!=('<%=Form.FALSO%>'))
            {               
               var auxTituloFuncionalidade = replaceAll(window.name, "_", " ");
               auxTituloFuncionalidade = replaceAll(auxTituloFuncionalidade, "11", "/"); 
               auxTituloFuncionalidade = replaceAll(auxTituloFuncionalidade, "22", "-"); 
                url = '<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_SOLICITAR_AJUDA, request)+"?"+Form.PARAMETRO_SOLICITAR_AJUDA+"="%>' +auxTituloFuncionalidade;
                document.form.action = url;
                document.form.submit();
            }
        }
        
        function solicitaAbaAnterior()
        {
            url = '<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_SOLICITAR_AJUDA, request)+"?"+Form.BOTAO_ANTERIOR_CLONE+"=3"%>' ;
            document.form.action = url;
            document.form.submit();     
        }
        
        function solicitaProximaAba()
        {
            url = '<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_SOLICITAR_AJUDA, request)+"?"+Form.BOTAO_PROXIMO_CLONE+"=4"%>';
            document.form.action = url;
            document.form.submit();     
        }
                
 	</script>
 </head>        
    <body class="SEFAZ-Body" onload="redirecionarAction();">
        <form method="POST" name="form" action="" >
            <center>
            <table cellspacing="1" cellpadding="0" border="0" width="420" align="left">
                    <tr>
                        <td colspan="4" align="center"></td>
                    </tr>
                    <tr class="SEFAZ-TR-ExibicaoImpar">
                        <td colspan="4" align="left" width="20%"
                            style="font-size:small; color:rgb(0,78,152); font-style:normal; font-weight:bold;">
                            <c:out value="${telaFuncionalidadeVo.informacaoTituloTelaFuncionalidade}"></c:out>
                        </td>
                    </tr>
                    <tr class="SEFAZ-TR-Titulo" align="left">                         
                         <td colspan="4">Orientações</td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left">
								    <textarea name="<%=Form.CAMPO_DESCRICAO_AJUDA%>" cols="50" rows="15"  readonly="enabled" ><c:out value="${telaFuncionalidadeVo.descricaoTelaFuncionalidade}"></c:out></textarea>
                        </td>
                    </tr>
                    <tr class="SEFAZ-TR-Titulo" align="left">
                         <td colspan="4">Campos para preenchimento</td>
                    </tr>
                    <c:forEach var="telaCampoAjudaVo" items="${telaFuncionalidadeVo.telaAjudaVo.telaCampoAjudaVo.collVO}" varStatus="indice">                                                 
                        <c:if test="${indice.count % 2 != 0}">
                            <tr class="SEFAZ-TR-ExibicaoImpar">
                        </c:if>
                        <c:if test="${indice.count % 2 == 0}">
                            <tr class="SEFAZ-TR-ExibicaoPar">
                        </c:if>
                            <td align="left" width="20%"><c:out value="${telaCampoAjudaVo.campoAjudaVo.descricaoRotulo}"></c:out></td>
                            <td align="left" width="65%" ><c:out value="${telaCampoAjudaVo.descricaoAjudaCampo}"></c:out></td>
                            </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr> 
                    <tr>
                        <td colspan="4" align="center"> 
                            <input type="hidden"   id="<%=Form.POSICAO_ATUAL_REGISTRO%>" name="<%=Form.POSICAO_ATUAL_REGISTRO%>" />
                            <input type="button" 	value="<%=Form.TEXTO_BOTAO_ANTERIOR%>" 	class="SEFAZ-INPUT-Botao" 	name="<%=Form.BOTAO_ANTERIOR_CLONE%>" onClick="return solicitaAbaAnterior();"/>
                            <input type="button" 	value="<%=Form.TEXTO_BOTAO_PROXIMO%>" 	class="SEFAZ-INPUT-Botao" 	name="<%=Form.BOTAO_PROXIMO_CLONE%>" onClick="return solicitaProximaAba();"/>
                        </td>
                    </tr>
            </table>
            </center>
        </form>           		
  </body>
</html>