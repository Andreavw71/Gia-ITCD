<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso ? Sefaz-MT
* Arquivo : ViewMenuBem.jsp
* Criação : Setembro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : Marlo Eichenberg Motta 
* Log : 08/10/2007
*/
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" %>
<%--AcessoWEB Valida--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<HTML>
	<HEAD>
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
		<link rel="stylesheet" href="/estilos/SefazEstilos.css" type="text/css">
		<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
	</HEAD>
	<BODY class="SEFAZ-Body">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="Bem"/>
		<br/>
		<CENTER>
				<table border="0" cellspacing="0" align="center">
					<tr align="left"> 
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt=""></td>
						<td><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_INCLUIR_BEM, request)%>">Incluir</a></font></td>
					</tr>
					<tr align="left"> 
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt="">
						<td><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_ALTERAR_BEM, request)%>">Alterar</a></font></td>
					</tr>
					<tr align="left"> 
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt=""></td>
						<td><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_PESQUISAR_BEM, request)%>">Consultar</a></font></td>
					</tr>
				</table>
				<br/>
				<table width="740" border="0">
					<tr>
						<td>
							<div align="center"> 
								<%=JspUtil.getBotaoRetornoMenu(request, "Voltar")%>
							</div>
						<td>
					</tr>
				</table>
		</CENTER>
		<br/>
		<g:mostrarRodape />
	</BODY>
</HTML>