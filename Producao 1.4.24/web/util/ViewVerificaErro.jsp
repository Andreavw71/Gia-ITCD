<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewVerificaErro.jsp
* Criação : Setembro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<%@ page import="br.com.abaco.util.facade.FormAbaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
		<script type="text/javascript" language="javascript">
			function verificaErro()
			{
				<c:if test="${not empty exception}">
					alert('<c:out value="${exception.message}"/>');
					<c:if test="${not empty campoQueReceberaFoco}">
						document.form.<c:out value="${campoQueReceberaFoco}"/>.focus();
					</c:if>
				</c:if>

				<c:if test="${empty exception}">
					<c:if test="${not empty campoQueReceberaFoco}">
						document.form.<c:out value="${campoQueReceberaFoco}"/>.focus();
					</c:if>
				</c:if>
				<c:if test="${not empty confirmacao}">
					if(confirm('<c:out value="${confirmacao.message}"/>'))
					{
						document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormAbaco.PARAMETRO_CONFIRMACAO_EXCEPTION+"=1"%>';
						document.form.submit();
					}
					else
					{
						if(executarCancelarConfirmacao())
						{
							return true;
						}
						return false;
					}
				</c:if>				
			}
		</script>
	</head>
</html>