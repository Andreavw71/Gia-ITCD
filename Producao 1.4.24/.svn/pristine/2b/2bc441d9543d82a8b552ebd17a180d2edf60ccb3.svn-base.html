<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewAvaliacaoListarAgenfa.jsp
* Criação : Janeiro de 2008 / Elizabeth Barbosa Moreira
* Revisão : 
* Log : 
*/
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.avaliacao.Form"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
	<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script> 
	<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
		<script type="text/javascript">	
		function consultarAgenfa(pCodigoAgenfa)
		{
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_PESQUISAR_AVALIACAO_POR_AGENFA+"=1"%>';
			document.form.<%=Form.CAMPO_CODIGO_UNIDADE_SEFAZ%>.value = pCodigoAgenfa;
			document.form.submit();
		}
		</script>
	<jsp:include page="/util/ViewVerificaErro.jsp"/>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro();">
	<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
	<center>
		<form method="POST" name="form"  action="javascript:actionForm()">
		<input type="hidden" name="<%=Form.CAMPO_CODIGO_UNIDADE_SEFAZ%>" value="">
		
  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
    <tr align="right"> 
      <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
   </tr> 
   <tr></tr> 

    <tr class="SEFAZ-TR-Titulo" align="center"> 
    	<td colspan="3">Resultados da Pesquisa</td>
    </tr>
    <tr class="SEFAZ-TR-SubTituloEsq"> 
    	<td width="60%" align="center">Nome</td>
    	<td width="25%">Município</td>
    	<td width="15%" align="center">&nbsp;</td>
    </tr>
			<c:set var="linhaEstiloAnterior" value=""/>
			<c:forEach var="agenciaProtocolo" items="${giaITCDVo.agenciaProtocolo.collVO}"  varStatus="status">
				<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
				<c:if test="${status.count % 2 != 0}">
					<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
				</c:if>
				<tr class="<c:out value="${linhaEstilo}"></c:out>">
					<td align="left"><c:out value="${agenciaProtocolo.nomeUnidade}"/></td>
					<td align="left"><c:out value="${agenciaProtocolo.municipio.nomeMunicipio}"/></td>
					<td><a href="javascript:consultarAgenfa(<c:out value="${agenciaProtocolo.codgUnidade}"/>);">consultar</a></td>
				</tr>
				<c:set var="linhaEstiloAnterior" value="${linhaEstilo}"/>
			</c:forEach>
   </table>
    <abaco:botaoCancelar/>
   </form>
	</center>
	<g:mostrarRodape/>
	</body>
</html>
