<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
	/*
	* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
	* Arquivo : ViewListarContribuinte.jsp
	* Criação : Outubro de 2007 / Rogério Sanches de Oliveira
	* Revisão : 
	* Log : 
	*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import= " br.gov.mt.sefaz.itc.model.generico.giaitcd.Form"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
		<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
		<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script> 
		<script type="text/javascript" language="javascript">
				function consultarContribuinte(numContribuinte)
				{
					document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.NUMERO_CONTRIBUINTE+"="%>'+numContribuinte;
					document.form.submit();
				}
		</script>
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<title><abaco:tituloSistema></abaco:tituloSistema></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro();" >
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
		<form name="form" action="#" method="POST">
			<table width="740" border="0" cellspacing="1" cellpadding="0" align="center">
			<tr align="right"> 
            <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
         </tr> 
      		<tr class="SEFAZ-TR-Titulo">
					<td width="20%" align="center">Nº Documento</td>
					<td width="50%" align="center">Nome Contribuinte</td>
					<td align="20%">Inscrição Estadual</td>
					<td align="10%">Situação da IE</td>
				</tr>
				<c:forEach var="vo" items="${contribuinteIntegracaoVo.collVO}">
				 <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
					 <c:if test="${status.count % 2 != 0}">
						  <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
					 </c:if>
				  <tr class="<c:out value="${linhaEstilo}"/>"> 
						<td align="center"><a href="javascript:consultarContribuinte(<c:out value="${vo.numrContribuinte}"/>)"><c:out value="${vo.numrDocumento}"/></a></td>						
						<td><c:out value="${vo.nomeContribuinte}"/></td>
						<td><c:out value="${vo.numrInscEstadual}"/></td>
						<td><c:out value="${vo.statInscricaoEstadual}"/></td>
					</tr>
				</c:forEach>
		   </table>
			<br>
			<br>
			<table width="140" border="0" cellspacing="1" cellpadding="1" align="center">
				<tr>
					<td align="center">
						<abaco:botaoVoltar nomeContadorSubmit="pesquisarContribuinte" nomeBotao="Voltar"/>
					</td>
					<td align="center">
						<abaco:botaoCancelar/>
					</td>
				</tr>
			</table>
		</form>
		<g:mostrarRodape/>
	</body>
</html>
