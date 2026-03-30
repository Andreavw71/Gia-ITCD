<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewEstruturaGIAITCDSeparacaoDivorcioUtil.jsp
* Criação : Novembro de 2007 / Rogério Sanches de Oliveira
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ page language="java" contentType="text/html;charset=iso-8859-1"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<title> <abaco:tituloSistema></abaco:tituloSistema> </title>
                <script src="<c:out value="${pageContext.request.contextPath}"/>/javascript/itcd.js" language="javascript" type="text/javascript"></script>
                <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
                <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
                <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
                <link href="<c:out value="${pageContext.request.contextPath}"/>/estilos/itcd.css" rel="stylesheet" type="text/css"  media="screen,print"/>
  </head>
  <body onload="selecioneFuncionalidade(); habilitarCampo(); verificaErro();log();">
  <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
  <jsp:include page="/util/ViewVerificaErro.jsp"/>
  <table width="740" cellspacing="0" cellpadding="0" border="0">
   <tr align="center">
		<td>
			 <table width="740" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<div id="abas"></div>
						</td>
					</tr>
					<tr>
						<td>
								<jsp:include page="/giaitcd/giaitcdseparacaodivorcio/util/ViewManterGIAITCDSeparacaoDivorcio.jsp"/>			
						</td>
					</tr>
			 </table>
		</td>
	</tr>
  </table>
  <table width="740" border="0">
		<tr>
			<td>
				<div align="center">
					<abaco:mensagemAguardeCarregando/>
				</div>
			<td>
		</tr>
	</table>
	<g:mostrarRodape/>	
	<abaco:log/>	
	</body>
</html>
