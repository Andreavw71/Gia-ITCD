<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewErro.jsp
* Criação : Setembro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.EntidadeVo"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.com.abaco.util.MsgException"%>
<%@ page import="br.com.abaco.util.Validador"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page isErrorPage="true"%>
<%
	EntidadeVo entidade = (EntidadeVo) request.getAttribute("vo");
   Exception ex = (Exception) request.getAttribute("exceptionDoacaoSucessiva");
   
   MsgException message = null;
   
   if (ex != null) {
       if (Validador.isStringValida(ex.getMessage())) {
           message = new MsgException(ex.getMessage());
       } else {
           message = new MsgException(
               "Preenchimento não permitido! Existem duas ou mais GIA-ITCD-e de doação " +
               "incluídas em data anterior a 06/08/2025. O Contribuinte deverá realizar a declaração " +
               "utilizando o Sistema e-Process com o tipo de processo \"ITCD - Denúncia Espontânea\"."
           );
       }
   
   } else if (request.getAttribute(MsgException.MSG_EXCEPTION) != null) {
       message = (MsgException) request.getAttribute(MsgException.MSG_EXCEPTION);
   
   
   } else if (entidade != null && Validador.isStringValida(entidade.getMensagem())) {
       message = new MsgException(entidade.getMensagem());
   
   
   } else {
       message = new MsgException("Não foi possível executar esta funcionalidade.");
   }
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	 <title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
<link rel="stylesheet" href="/estilos/SefazEstilos.css" type="text/css">
</head>
<body class="SEFAZ-Body">
  <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
<center>
	<form name="form1" method="POST" action="" onSubmit="" enctype="multipart/form-data">
    <table border="0" width="451" cellspacing="0" cellpadding="0">
      <tr valign="top" bgcolor="#FFFFFF"> 
        <td colspan="2" height="20" align="center"> 
		  <font class="SEFAZ-FONT-MensagemErro">
		  <br><br><br><br><br><br>
          <b><%=message.getMessage()%></b>
		  <br><br><br><br><br><br>
			 </font>
        </td>
      </tr>
    </table>
    <br>
    <table width="400" border="0" cellspacing="0">
      <tr> 
        <td colspan="2" align="center">
				<%
					if(message.isRedirecionarLogin())
					{
				%>
						<input type="button" name="teste" value="Login" class="SEFAZ-INPUT-Botao" onclick="Javascript:location='/acessoweb/login/LoginUsuario.jsp';" >
				<%
					}
					else
					{
				%>
						<c:if test="${botaoFecharJanela != null}" >
								<input type="button" class="SEFAZ-INPUT-Botao"  name="button" value="    OK    " onclick="window.close();" />
						</c:if>
						<c:if test="${botaoFecharJanela == null}" >
								<abaco:botaoRetornoMenu></abaco:botaoRetornoMenu>
						</c:if>
				<%
					}
				%>
        </td>
      </tr>
    </table>
  </form>
  <g:mostrarRodape/>
  </center>
</body>
</html>


