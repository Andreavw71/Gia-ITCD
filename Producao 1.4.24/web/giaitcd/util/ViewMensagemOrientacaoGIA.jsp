<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewMensagemOrientacaoGIA.jsp
* Criação : Janeiro de 2008 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="br.com.abaco.util.EntidadeVo"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<%@page import="br.com.abaco.util.Validador"%>
<%
	EntidadeVo entidade = new EntidadeVo();
	if(request.getAttribute("vo")!=null)
	{
		entidade = (EntidadeVo) request.getAttribute("vo");
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
		<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
		<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
		<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
		<script type="text/javascript" language="javascript">
			function obterArrayBotoes()
			{
				var botao1 = document.form.<%=FormITC.BOTAO_IMPRIMIR%>;
				return new Array(botao1);
			}
			function solicitaImprimirGIAITCD()
			{
				desabilitarBotoes(obterArrayBotoes());
				// existe uma definição que no ITCD todos os pdf's deverão ser abertos em popup
				window.open('','relatorioGIAITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
				document.form.target = 'relatorioGIAITCD';
				document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_IMPRIMIR+"=1"%>';
				document.form.submit();
				return true;
			}
			
		</script>
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<title><abaco:tituloSistema></abaco:tituloSistema></title>
	</head>
	<body class="SEFAZ-Body" onload=" verificaErro(); ">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
		<center>
			<form method="POST" name="form" action="#">
			<table border="0" width="451" cellspacing="0" cellpadding="0">
				<tr valign="top" bgcolor="#FFFFFF"> 
					<td colspan="2" height="20" align="center"> 
						<font class="SEFAZ-FONT-MensagemSucesso">
							<c:if test="${not giaITCDVo.usuarioServidor}">
								<br><br><br>							
							</c:if>
							<c:if test="${giaITCDVo.usuarioServidor}">
								<br><br><br><br><br><br>							
							</c:if>
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
            <tr align="center" valign="bottom">            
               <td class="SEFAZ-FONT-MensagemSucesso">GIA ITCD nº: <c:out value="${giaITCDVo.codigo}"></c:out></td>    
            </tr>
			</table>
			<c:if test="${not giaITCDVo.usuarioServidor}">
				<br><br><br>							
			</c:if>
			<c:if test="${giaITCDVo.usuarioServidor}">
				<br><br><br><br><br><br>							
			</c:if>
				<c:if test="${not giaITCDVo.usuarioServidor}">
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
					<tr class="SEFAZ-TR-Titulo" align="center"> 
						<td colspan="2">Orientação</td>
					 </tr>
					<tr> 
						<td colspan="2" align="center" class="SEFAZ-TD-RotuloSaida"><div align="center"><c:out value="${giaITCDVo.mensagemConfirmarGia}"  escapeXml="false"/></div></td>
					</tr>
				</table>
			  <br/>
			  <br/>				
				</c:if>
			  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
				 <tr> 
					<td align="center">
                  <c:if test="${false}">
                     <c:if test="${not giaITCDVo.usuarioServidor}">
                        <input name="<%=FormITC.BOTAO_IMPRIMIR%>" type="button" class="SEFAZ-INPUT-Botao" value="<%=FormITC.TEXTO_BOTAO_IMPRIMIR%>" onClick="return solicitaImprimirGIAITCD();"/>
                     </c:if>
                  </c:if>
						<abaco:botaoRetornoMenu nomeBotao="Finalizar"/>
					</td>
				 </tr>
			  </table>
			  <table width="740" border="0">
				<abaco:mensagemAguardeCarregando></abaco:mensagemAguardeCarregando>
			  </table>
			</form>
		</center>
		<g:mostrarRodape/>
  </body>
</html>