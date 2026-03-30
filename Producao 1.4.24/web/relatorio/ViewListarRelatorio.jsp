<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewListarBem.jsp
* Criação : Setembro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : Marlo Eichenberg Motta 
* Log : 08/10/2007
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnSituacaoProcessamento"%>
<%
	pageContext.setAttribute("RELATORIO_PROCESSADO", DomnSituacaoProcessamento.PROCESSADO);
   pageContext.setAttribute("RELATORIO_PROCESSADO_COM_ERRO", DomnSituacaoProcessamento.PROCESSADO_COM_ERRRO);
   pageContext.setAttribute("RELATORIO_NAO_PROCESSADO", DomnSituacaoProcessamento.NAO_PROCESSADO);
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
			function downloadArquivo(codigoArquivo)
			{
				document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_ARQUIVO_CASTOR+"="%>'+codigoArquivo;
				document.form.submit();
			}

			function obterArrayBotoes()
			{
				var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
				return new Array(botao1);
			}
		</script>
		<%@ page="/util/ViewVerificaErro.jsp"%>
		<title><abaco:tituloSistema></abaco:tituloSistema></title>
	</head>
	<body>
		<c:if test="${not empty pedidoRelatorioVo.collVO}">
		<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
			<tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="8">Resultados da Pesquisa</td>
			</tr>
			<tr class="SEFAZ-TR-SubTituloEsq"> 
				<td width="40" align="center">Código</td>
				<td align="center" >Servidor Solicitante </td>
            <td align="center">Relatório</td>
				<td align="center">Status do processamento</td>
				<td align="center">Data solicitação</td>
            <td align="center">Data processamento</td>
				<td align="center" >Arquivo</td>
			</tr>
			<c:set var="funcionalidadePesquisarBem" ><%=JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_BEM)%></c:set>
			<c:forEach var="pedidoRelatorioTempVo" items="${pedidoRelatorioVo.collVO}"  varStatus="status">
				<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
						<c:if test="${status.count % 2 != 0}">
								<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
						</c:if>
				<tr class="<c:out value="${linhaEstilo}"/>"> 
					<td align="center"><c:out value="${pedidoRelatorioTempVo.codigo}"/></td>
					<td align="left"><c:out value="${pedidoRelatorioTempVo.usuarioSolicitante.nome}"/></td>        
               <td align="left"><c:out value="${pedidoRelatorioTempVo.tipoRelatorio.textoCorrente}"/></td>     
                  <!-- Inicio code cor status -->
                  <c:choose>
                     <c:when test="${pedidoRelatorioTempVo.situacaoProcessamento.valorCorrente == RELATORIO_PROCESSADO}">
                           <c:set var="corTextoStatus" >green</c:set>
                     </c:when>
                     <c:when test="${pedidoRelatorioTempVo.situacaoProcessamento.valorCorrente == RELATORIO_PROCESSADO_COM_ERRO}">
                           <c:set var="corTextoStatus" >tomato</c:set>
                     </c:when>
                     <c:otherwise>
                           <c:set var="corTextoStatus" >grey</c:set>
                     </c:otherwise>   
                  </c:choose>
                  <!-- Fim code cor status -->
               <td align="center" style="color: <c:out value="${corTextoStatus}"/>" ><c:out value="${pedidoRelatorioTempVo.situacaoProcessamento.textoCorrente}"/></td>
               <c:remove var="corTextoStatus" />
					<td align="left"><c:out value="${pedidoRelatorioTempVo.dataSolicitacaoFormatada}"/></td>
               <td align="left"><c:out value="${pedidoRelatorioTempVo.dataProcessamentoFormatada}"/></td>
               <td align="center">
                  <c:if test="${pedidoRelatorioTempVo.situacaoProcessamento.valorCorrente == RELATORIO_PROCESSADO}">
                        <a href="javascript: downloadArquivo(<c:out value="${pedidoRelatorioTempVo.codigo}"/>)"><img src="/imagens/view_details-26.png" width="14" height="14"/></a>
                  </c:if>
               </td>
			 </tr>
			 </c:forEach>		
			<tr> 
				<td colspan="6">&nbsp;</td>
			</tr>
		 </table>
	  </c:if>
  </body>
  </html>