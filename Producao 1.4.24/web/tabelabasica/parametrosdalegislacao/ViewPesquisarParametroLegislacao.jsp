<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarParametroLegislacao.jsp
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
<%@page import =" br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.Form"%>
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
				
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<title><abaco:tituloSistema></abaco:tituloSistema></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
		<center>
		<form method="POST" action="" name="form">
                    <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                     <tr align="right">
                            <td colspan="5"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                     </tr>
               <tr class="SEFAZ-TR-Titulo">
						<td colspan="5">Legislação</td>
					</tr>					
					<tr> 
						<td class="SEFAZ-TD-RotuloSaida" width="354">Número da Legislação:&nbsp;</td>
						<td class="SEFAZ-TD-CampoSaida" width="386"><c:out value="${parametroLegislacaoVo.numeroLegislacao}"/>	/<c:out value="${parametroLegislacaoVo.anoLegislacao}"/></td>
					</tr>
					<tr> 
						<td class="SEFAZ-TD-RotuloSaida" width="354">Legislação Principal:&nbsp;</td>
						<td class="SEFAZ-TD-CampoSaida" width="386"><c:if test="${parametroLegislacaoVo.numeroLegislacaoPrincipal != 0}"><c:out value="${parametroLegislacaoVo.numeroLegislacaoPrincipal}"/>/<c:out value="${parametroLegislacaoVo.anoLegislacaoPrincipal}"/></c:if></td>
					</tr>
					<tr> 
						<td class="SEFAZ-TD-RotuloSaida" width="354">Cálculo em cascata:&nbsp;</td>
						<td class="SEFAZ-TD-CampoSaida" width="386"><c:out value="${parametroLegislacaoVo.calculoCascataFormatado}"/></td>
					</tr>
					<tr> 
						<td class="SEFAZ-TD-RotuloSaida" width="354">Data de Vigência Inicial:&nbsp;</td>
						<td class="SEFAZ-TD-CampoSaida" width="386"><c:out value="${parametroLegislacaoVo.dataInicioVigenciaFormatada}"/></td>
					</tr>
					<tr> 
						<td class="SEFAZ-TD-RotuloSaida" width="354">Data de Vigência Final:&nbsp;</td>
						<td class="SEFAZ-TD-CampoSaida" width="386"><c:out value="${parametroLegislacaoVo.dataFimVigenciaFormatada}"/></td>
					</tr>
					<tr> 
						<td class="SEFAZ-TD-RotuloSaida" width="354">Cálculo em Cascata:&nbsp;</td>					
						<td class="SEFAZ-TD-CampoSaida" width="386"><c:out value="${parametroLegislacaoVo.calculoCascataFormatado}"/></td>
					</tr>
					<tr> 
						<td class="SEFAZ-TD-RotuloSaida" width="354">Status:&nbsp;</td>					
						<td class="SEFAZ-TD-CampoSaida" width="386"><c:out value="${parametroLegislacaoVo.statusLegislacao.textoCorrente}"/></td>
					</tr>
					<tr> 
						<td class="SEFAZ-TD-CampoSaida" colspan="4">&nbsp;</td>
					</tr>
					<tr class="SEFAZ-TR-Titulo">
						<td colspan="4">Alíquota</td>
					</tr>
					<c:if test="${not empty parametroLegislacaoVo.aliquotaVo.collVO}">
						<table align="center" border="0" cellpadding="0" cellspacing="1" width="740">
							<tr  class="SEFAZ-TR-SubTitulo">
								<td width="262">Fato Gerador</td>
								<td align="center" width="186">Percentual Alíquota</td>
								<td align="center" width="108">UPF Inicial</td>
								<td align="center">UPF Final</td>								
							</tr>
							<c:forEach var="aliquotaVo" items="${parametroLegislacaoVo.aliquotaVo.collVO}" varStatus="contador">
								<c:set var="linhaEstilo" value="SEFAZ-TD-ExibicaoPar"></c:set>
									<c:if test="${contador.count%2!=0}">
										<c:set var="linhaEstilo" value="SEFAZ-TD-ExibicaoImpar"></c:set>
									</c:if>
								<tr class="<c:out value="${linhaEstilo}"/>">
									<td align="center"> <c:out value="${aliquotaVo.tipoFatoGerador.textoCorrente}"></c:out></td>
									<td align="center"><c:out value="${aliquotaVo.percentualLegislacaoAliquota}"></c:out></td>
									<td align="center"><c:out value="${aliquotaVo.quantidadeUPFInicial}"></c:out></td>
									<td align="center" width="103"><c:if test="${aliquotaVo.quantidadeUPFFinal !=0}"><c:out value="${aliquotaVo.quantidadeUPFFinal}"></c:out></c:if></td>
								</tr>
							</c:forEach>							
						</table>
					</c:if>
					<tr> 
						<td colspan="4">&nbsp;</td>
					</tr>
					</table>
					<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
						<tr class="SEFAZ-TR-Titulo">
							<td colspan="3">Multa</td>
						</tr>
						<c:if test="${not empty parametroLegislacaoVo.multaVo.collVO}">
							<table align="center" border="0" cellpadding="0" cellspacing="1" width="740">
								<tr class="SEFAZ-TR-SubTitulo">
									<td align="center" width="213">Quantidade de Dias Inicial </td>
									<td align="center" width="205">Quantidade de Dias Final </td>
									<td align="center">Percentual da Multa </td>									
								</tr>
								<c:forEach var="multaVo" items="${parametroLegislacaoVo.multaVo.collVO}" varStatus="contador">
									<c:set var="linhaEstilo" value="SEFAZ-TD-ExibicaoPar"></c:set>
									<c:if test="${contador.count%2!=0}">
										<c:set var="linhaEstilo" value="SEFAZ-TD-ExibicaoImpar"></c:set>
									</c:if>
									<tr class="<c:out value="${linhaEstilo}"/>">
										<td align="center"><c:out value="${multaVo.quantidadeDiasInicial}"></c:out></td>
										<td align="center"><c:if test="${multaVo.quantidadeDiasFinal != 0}"><c:out value="${multaVo.quantidadeDiasFinal}"></c:out></c:if></td>
										<td align="center"><c:out value="${multaVo.percentualMulta}"></c:out></td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
					</table>
					<table width="740" border="0">
						<tr> 
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr>
							<td>
								<div align="center"> 
									<abaco:botaoCancelarSemMensagem/>
								</div>
							<td>
						</tr>
					</table>
			</form>
		</center>
		<g:mostrarRodape/>
	</body>
</html>	
