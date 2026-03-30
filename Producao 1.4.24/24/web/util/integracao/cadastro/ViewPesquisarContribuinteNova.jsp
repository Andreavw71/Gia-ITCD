<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarContribuinteNova.jsp
* Autor: Ricardo Vitor de Oliveira Moraes
* Criação : Dezembro de 2008
* Revisão :
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.dominio.DomnOpcaoPesquisaContribuinte"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.Flow"%>
<%@ page import="br.gov.mt.sefaz.itc.util.integracao.cadastro.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<meta http-equiv=Expires content="Thu, 01 Jan 1970 00:00:00 GMT">
		<meta http-equiv=Cache-Control content=no-store>
		<meta http-equiv=Pragma content=no-cache>
		<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
		<script type="text/javascript" src="/javascript/funcoesGenericas.js"></script>
		<script type="text/javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<title><abaco:tituloSistema/></title>	
	</head>
	<body class="SEFAZ-Body" onload="verificaErro(); ocultarLocalizarContribuinte();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>	
			<form name="form" method="POST" action="#">
				<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
				<tr class="SEFAZ-TR-Titulo">
					<td colspan="4">Pesquisar Contribuinte:</td>
				</tr>
				<tr>
					<td colspan="4">
						<c:choose>
							<c:when test="${empty ctaVo.contribuinteIntegracaoRemetenteVo.nomeContribuinte}">
								<c:set var="naoOculta" value='true' scope="request"/>													
							</c:when>
							<c:otherwise>
								<c:set var="naoOculta" value='false' scope="request"/>																				
							</c:otherwise>
						</c:choose>					
						<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteIncludeNova.jsp" />
					</td>
				</tr>
				</table>
				<abaco:mensagemAguardeCarregando/>
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
					<tr> 
						<td colspan="4" align="center"> 
							<abaco:botaoVoltar nomeContadorSubmit="pesquisarContribuinte" />
							<abaco:botaoCancelar/>							
						</td>
					</tr>
				</table>				
			</form>
	</body>
</html>
