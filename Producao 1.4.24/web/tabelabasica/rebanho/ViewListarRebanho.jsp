<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewListarRebanho.jsp
* Criação : Outubro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
		<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
                <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
		<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
		<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
		<script type="text/javascript" language="javascript">
			function consultarRebanho(codigoRebanho)
			{
				document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_REBANHO+"="%>'+codigoRebanho;
				document.form.submit();
			}

			function obterArrayBotoes()
			{
				var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
				return new Array(botao1);
			}
		
		</script>
		
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<title><abaco:tituloSistema></abaco:tituloSistema></title>
	</head>
	<body onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');">
		<c:if test="${not empty rebanhoVo.collVO}">
			<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">                        
                             <tr align="right">
                                    <td colspan="3"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                                </tr>
                                <tr></tr>
				<tr class="SEFAZ-TR-Titulo"  align="center"> 
					<td colspan="3">Resultados da Pesquisa</td>
				</tr>
				<tr class="SEFAZ-TR-SubTituloEsq"> 
					<td width="136" align="center">Código</td>
					<td>Descrição do Rebanho </td>
					<td width="64" align="center">Status</td>
				</tr>
				<c:set var="funcionalidadePesquisarRebanho" ><%=JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_REBANHO)%></c:set>
				<c:forEach var="rebanhoTempVo" items="${rebanhoVo.collVO}" varStatus="status">
					<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
						<c:if test="${status.count % 2 != 0}">
								<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
						</c:if>
					<tr class="<c:out value="${linhaEstilo}"/>">
						<td width="<c:if test="${funcionalidadePesquisarRebanho}">10%</c:if><c:if test="${!funcionalidadePesquisarRebanho}">136</c:if>" align="center">
							<c:if test="${funcionalidadePesquisarRebanho}">
								<c:out value="${rebanhoTempVo.codigo}"/>
							</c:if>
							<c:if test="${!funcionalidadePesquisarRebanho}">
								<a href="javascript: consultarRebanho(<c:out value="${rebanhoTempVo.codigo}"/>)"><c:out value="${rebanhoTempVo.codigo}"/></a>
							</c:if>
						</td>
						<td align="left"><c:out value="${rebanhoTempVo.descricaoRebanho}"/></td>
						<td align="left"><c:out value="${rebanhoTempVo.statusRebanho.textoCorrente}"/></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
  </body>
</html>