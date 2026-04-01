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
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.bem.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
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
			function consultarBem(codigoBem)
			{
				document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_BEM+"="%>'+codigoBem;
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
		<c:if test="${not empty bemVo.collVO}">
		<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
			<tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="6">Resultados da Pesquisa</td>
			</tr>
			<tr class="SEFAZ-TR-SubTituloEsq"> 
				<td width="136" align="center">Código</td>
				<td>Descrição do Bem </td>
				<td>Possui Construção / Edificação</td>
				<td width="64" align="center">Status</td>
            <td>Tipo Verificação</td>
				<td>Tipo Protocolo</td>
			</tr>
			<c:set var="funcionalidadePesquisarBem" ><%=JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_BEM)%></c:set>
			<c:forEach var="bemTempVo" items="${bemVo.collVO}"  varStatus="status">
				<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
						<c:if test="${status.count % 2 != 0}">
								<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
						</c:if>
				<tr class="<c:out value="${linhaEstilo}"/>"> 
					<td width="<c:if test="${funcionalidadePesquisarBem}">10%</c:if><c:if test="${!funcionalidadePesquisarBem}">136</c:if>" align="center">
						<c:if test="${funcionalidadePesquisarBem}">
							<c:out value="${bemTempVo.codigo}"/>
						</c:if>
						<c:if test="${!funcionalidadePesquisarBem}">
							<a href="javascript: consultarBem(<c:out value="${bemTempVo.codigo}"/>)"><c:out value="${bemTempVo.codigo}"/></a>
						</c:if>
					</td>
					<td align="left"><c:out value="${bemTempVo.descricaoTipoBem}"/></td>
					<td align="center"><c:out value="${bemTempVo.possuiConstrucao.textoCorrente}"/></td>
					<td align="left"><c:out value="${bemTempVo.statusBem.textoCorrente}"/></td>
               <td align="left"><c:out value="${bemTempVo.tipoVerificacao.textoCorrente}"/></td>
               <td align="left"><c:out value="${bemTempVo.tipoProtocoloGIA.textoCorrente}"/></td>
			 </tr>
			 </c:forEach>		
			<tr> 
				<td colspan="6">&nbsp;</td>
			</tr>
		 </table>
	  </c:if>
  </body>
  </html>