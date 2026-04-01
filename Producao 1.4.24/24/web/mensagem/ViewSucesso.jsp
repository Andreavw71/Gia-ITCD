<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewSucesso.jsp
* Criação : Setembro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.EntidadeVo"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.com.abaco.util.Validador"%>
<%
	EntidadeVo entidade = new EntidadeVo();
	if(request.getAttribute("vo")!=null)
	{
		entidade = (EntidadeVo) request.getAttribute("vo");
	}
%>
<HTML>
<HEAD>
   <META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
   <META HTTP-EQUIV=Cache-Control content=no-store>
   <META HTTP-EQUIV=Pragma content=no-cache>
   <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
   <LINK rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
   <title><abaco:tituloSistema></abaco:tituloSistema></title>
</HEAD>

<BODY class="SEFAZ-Body">
	<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
	<center>
		<form name="form1" method="POST" action="" onSubmit="" enctype="multipart/form-data">
			<table border="0" width="451" cellspacing="0" cellpadding="0">
				<tr valign="top" bgcolor="#FFFFFF"> 
					<td colspan="2" height="20" align="center"> 
						<font class="SEFAZ-FONT-MensagemSucesso">
							<br><br><br><br><br><br>
							<%
							if(entidade!=null && Validador.isStringValida(entidade.getMensagem()))
							{
							%>
								<b><%=entidade.getMensagem()%></b>
							<%
							}
							else
							{
							%>
								<b>Operação efetuada com sucesso!</b>
							<%
							}
							%>
						</font>
					</td>
				</tr>
			</table>
			<br><br><br><br><br><br>
			<table width="400" border="0" cellspacing="0">
				<tr> 
					<td colspan="2" align="center">
						<abaco:botaoRetornoMenu></abaco:botaoRetornoMenu>
					</td>
				</tr>
			</table>
		</form>
		<g:mostrarRodape/>
	</center>
</body>
</html>