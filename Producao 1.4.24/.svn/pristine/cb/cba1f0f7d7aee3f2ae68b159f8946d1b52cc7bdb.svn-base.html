<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewListarParametroLegislacao.jsp
* Criação : Outubro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
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
			function consultarPesquisarParametroLegislacao(codigoParametroLegislacao)
			{
				document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_PARAMETRO_LEGISLACAO_PESQUISAR+"="%>'+codigoParametroLegislacao;
				document.form.submit();
			}
			function consultarAlterarParametroLegislacao(codigoParametroLegislacao)
			{
				document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_PARAMETRO_LEGISLACAO_ALTERAR+"="%>'+codigoParametroLegislacao;
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
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
		<form method="POST" name="form" action="">
                    <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">                        
                     <tr align="right">
                            <td colspan="5"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                     </tr>
             <tr class="SEFAZ-TR-Titulo" align="center"> 
					<td colspan="5">Resultados da Pesquisa</td>
				</tr>
				<tr class="SEFAZ-TR-SubTituloEsq"> 
					<td width="136" align="center">Código</td>
					<td>Número da Legislação </td>
					<td>Data de Vigência Inicial </td>
					<td>Data de Vigência Final </td>
					<td width="64" align="center">Status</td>
				 </tr>		 
				<c:set var="funcionalidadePesquisarParametroLegislacao" ><%=JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_PARAMETROS_DA_LEGISLACAO)%></c:set>		
				<c:if test="${not empty parametroLegislacaoVo.collVO}">
					<c:forEach var="parametroLegislacaoTempVo" items="${parametroLegislacaoVo.collVO}" varStatus="status">
						<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
						<c:if test="${status.count % 2 != 0}">
								<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
						</c:if>
					 <tr class="<c:out value="${linhaEstilo}"/>"> 
							<td width="<c:if test="${funcionalidadePesquisarParametroLegislacao}">10%</c:if><c:if test="${!funcionalidadePesquisarParametroLegislacao}">136</c:if>"  align="center">
								<c:if test="${funcionalidadePesquisarParametroLegislacao}">
									<a href="javascript: consultarPesquisarParametroLegislacao(<c:out value="${parametroLegislacaoTempVo.codigo}"/>)"><c:out value="${parametroLegislacaoTempVo.codigo}"/></a>
								</c:if>
								<c:if test="${!funcionalidadePesquisarParametroLegislacao}">
									<a href="javascript: consultarAlterarParametroLegislacao(<c:out value="${parametroLegislacaoTempVo.codigo}"/>)"><c:out value="${parametroLegislacaoTempVo.codigo}"/></a>
								</c:if>								
							</td>
							<td align="left"><c:out value="${parametroLegislacaoTempVo.numeroLegislacao}"/>/<c:out value="${parametroLegislacaoTempVo.anoLegislacao}"/></td>
							<td align="left"><c:out value="${parametroLegislacaoTempVo.dataInicioVigenciaFormatada}"/></td>
							<td align="left"><c:out value="${parametroLegislacaoTempVo.dataFimVigenciaFormatada}"/></td>
							<td align="center"><c:out value="${parametroLegislacaoTempVo.statusLegislacao.textoCorrente}"/></td>
						</tr>
					</c:forEach>
				</c:if>
				<tr> 
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr> 
					<td colspan="5" align="center">
					<abaco:botaoCancelarSemMensagem/>
				 </td>
				</tr>
		  </table>
	  </form>
	  <g:mostrarRodape/>
  </body>
</html>