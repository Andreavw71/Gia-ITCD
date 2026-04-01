<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewImprimirGarantia.jsp
* Criação : Dezembro de 2007 / Elizabeth Barbosa Moreira
* Revisão : 
* Log : 
*/
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.giaitcd.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%
	pageContext.setAttribute("PENDENTE_DE_PROTOCOLO", new Integer(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO));
	pageContext.setAttribute("PROTOCOLADO", new Integer(DomnStatusGIAITCD.PROTOCOLADO));
	pageContext.setAttribute("QUITADO", new Integer(DomnStatusGIAITCD.QUITADO));
	pageContext.setAttribute("QUITADO_MANUALMENTE", new Integer(DomnStatusGIAITCD.QUITADO_MANUALMENTE));
   pageContext.setAttribute("QUITADO_GIOR", new Integer(DomnStatusGIAITCD.QUITADO_PELA_GIOR));
   pageContext.setAttribute("QUITADO_CCF", new Integer(DomnStatusGIAITCD.QUITADO_CCF));
	pageContext.setAttribute("INATIVO", new Integer(DomnStatusGIAITCD.INATIVO));
	pageContext.setAttribute("INATIVADO_AUTOMATICAMENTE", new Integer(DomnStatusGIAITCD.INATIVADO_AUTOMATICAMENTE));
	pageContext.setAttribute("AVALIADO", new Integer(DomnStatusGIAITCD.AVALIADO));
	pageContext.setAttribute("ISENTO", new Integer(DomnStatusGIAITCD.ISENTO));
	pageContext.setAttribute("NAO_OCORRENCIA_DE_FATO_GERADOR", new Integer(DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR));
	pageContext.setAttribute("PROTOCOLO_AUTORIZADO_PELA_GIOR", new Integer(DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR));
	pageContext.setAttribute("NOTIFICADO", new Integer(DomnStatusGIAITCD.NOTIFICADO));
	pageContext.setAttribute("RETIFICADO", new Integer(DomnStatusGIAITCD.RETIFICADO));
	pageContext.setAttribute("IMPUGNADO", new Integer(DomnStatusGIAITCD.IMPUGNADO));
	pageContext.setAttribute("PARCELADO", new Integer(DomnStatusGIAITCD.PARCELADO));
	pageContext.setAttribute("SEGUNDA_RETIFICACAO", new Integer(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO));
	pageContext.setAttribute("RATIFICADO", new Integer(DomnStatusGIAITCD.RATIFICADO));
	pageContext.setAttribute("PARA_INSCRICAO_EM_DIVIDA_ATIVA", new Integer(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA));
	pageContext.setAttribute("REMETIDO_PARA_DIVIDA_ATIVA", new Integer(DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA));
	pageContext.setAttribute("ISENTO_IMPOSTO_ATE_1_UPF", new Integer(DomnStatusGIAITCD.ISENTO_IMPOSTO_ATE_1_UPF));
	pageContext.setAttribute("INVENTARIO_ARROLAMENTO", new Integer(DomnTipoProcesso.INVENTARIO_ARROLAMENTO));
	
%>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
	<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
	<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script> 
	<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
	<script type="text/javascript">
		function solicitarExibirPDF()
		{					
			//desabilitarBotoes(obterArrayBotoes());
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>?<%= Form.BOTAO_IMPRIMIR%>=true';
			window.open('','relatorioGIAITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
			document.form.target = 'relatorioGIAITCD';
			document.form.submit();
			return true;
		}		
		function obterArrayBotoes()
		{
			var botao1 = document.form.<%=Form.BOTAO_IMPRIMIR%>;
			return new Array(botao1);
		}
	</script>
	<jsp:include page="/util/ViewVerificaErro.jsp"/>
	<title><abaco:tituloSistema/></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>'); verificaErro();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
			<form method="POST" name="form"  action="#" >
			  <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
				  <tr align="right"> 
					<td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
				  </tr> 
      		 <tr class="SEFAZ-TR-Titulo" align="center"> 
					<td colspan="4">Dados da GIA-ITCD</td>
				 </tr>
				 <tr>
					<td width="198" class="SEFAZ-TD-RotuloSaida">N&uacute;mero da GIA-ITCD:&nbsp;</td>
					<td width="163" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.codigo}"/></td>
					<td width="119" class="SEFAZ-TD-RotuloSaida">Tipo da GIA:&nbsp;</td>
					<td width="260" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.tipoGIA.textoCorrente}"/></td>
				 </tr>
				 <tr>
					<td class="SEFAZ-TD-RotuloSaida">Nome do declarante:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.nomeContribuinte}"/></td>
					<td class="SEFAZ-TD-RotuloSaida">CPF/CNPJ :&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDocumento}"/></td>
				 </tr>		
				<c:set var="statusGia" value="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente}" scope="page"/>
				<tr>
					<td class="SEFAZ-TD-RotuloSaida" colspan="4">&nbsp;</td>
				</tr>					  											
				 <c:choose>
					<c:when test="${statusGia == QUITADO}">
						<c:set var="isExibeValores" value="true" scope="page"/>
						<tr>
							<td class="SEFAZ-TD-RotuloSaida" colspan="4"><div align="center">GIA-ITCD Quitada</div></td>
						</tr>
					</c:when>
					<c:when test="${statusGia == QUITADO_MANUALMENTE}">
						<tr>
							<td class="SEFAZ-TD-RotuloSaida" colspan="4"><div align="center">GIA-ITCD Quitada</div></td>
						</tr>
					</c:when>
               <c:when test="${statusGia == QUITADO_GIOR}">
						<tr>
							<td class="SEFAZ-TD-RotuloSaida" colspan="4"><div align="center">GIA-ITCD Quitada</div></td>
						</tr>
					</c:when>
               <c:when test="${statusGia == QUITADO_CCF}">
						<tr>
							<td class="SEFAZ-TD-RotuloSaida" colspan="4"><div align="center">GIA-ITCD Quitada</div></td>
						</tr>
					</c:when>
					<c:when test="${statusGia == NAO_OCORRENCIA_DE_FATO_GERADOR }">
						<tr>
							<td class="SEFAZ-TD-RotuloSaida" colspan="4"><div align="center">Não há ocorrência de fato gerador</div></td>
						</tr>					
					</c:when>
					<c:when test="${statusGia == ISENTO}">
						<tr>
							<td class="SEFAZ-TD-RotuloSaida" colspan="4"><div align="center">Contribuinte Isento ao Imposto ITCD</div></td>
						</tr>											
					</c:when>
					<c:when test="${statusGia == ISENTO_IMPOSTO_ATE_1_UPF}">
						<tr>
							<td class="SEFAZ-TD-RotuloSaida" colspan="4"><div align="center">Contribuinte Isento do Imposto até 1 UPF</div></td>
						</tr>											
					</c:when>
					<c:when test="${statusGia == PENDENTE_DE_PROTOCOLO || statusGia == PROTOCOLO_AUTORIZADO_PELA_GIOR}">
						<tr>
							<td class="SEFAZ-TD-RotuloSaida" colspan="4"><div align="center">GIA-ITCD Pendente de Protocolo</div></td>
						</tr>											
					</c:when>
					<c:when test="${statusGia == INATIVADO_AUTOMATICAMENTE || statusGia == INATIVO}">
						<tr>
							<td class="SEFAZ-TD-RotuloSaida" colspan="4"><div align="center">GIA-ITCD se encontra Inativa ou Inativada automaticamente</div></td>
						</tr>																
					</c:when>
					<c:otherwise>
						<c:set var="isExibeValores" value="true" scope="page"/>
						<tr>
							<td class="SEFAZ-TD-RotuloSaida" colspan="4" ><div align="center">GIA-ITCD Pendente - de Recolhimento</div></td>
						</tr>
					</c:otherwise>
				 </c:choose>
				 <c:if test="${isExibeValores}">
					<tr>
						<td class="SEFAZ-TD-RotuloSaida" colspan="4">&nbsp;</td>
					</tr>	
					<tr>
						<td class="SEFAZ-TD-RotuloSaida" colspan="2">Valor do Imposto :&nbsp;</td>
						<td class="SEFAZ-TD-CampoSaida" colspan="2">R$ <c:out value="${giaITCDVo.valorITCDFormatado}"/></td>							
					</tr>
					<c:if test="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == INVENTARIO_ARROLAMENTO}">
						<tr>
							<td class="SEFAZ-TD-RotuloSaida" colspan="2">Valor da Multa :&nbsp;</td>
							<td class="SEFAZ-TD-CampoSaida" colspan="2">R$ <c:out value="${giaITCDVo.valorMultaFormatado}"/></td>								
						</tr>
						<tr>
							<td class="SEFAZ-TD-RotuloSaida" colspan="2">Valor a Recolher :&nbsp;</td>
							<td class="SEFAZ-TD-CampoSaida" colspan="2">R$ <c:out value="${giaITCDVo.valorRecolhimentoFormatado}"/></td>																	
						</tr>
					</c:if>				 
				 </c:if>
				 <tr>
					<td class="SEFAZ-TD-RotuloSaida" colspan="4">&nbsp;</td>
				 </tr> 
			  </table>    
			  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
				 <tr> 
					<td align="center">&nbsp;</td>
				 </tr>  
					<tr> 
						<td colspan="5" >
							<div align="center">
							  <input name="<%=Form.BOTAO_IMPRIMIR%>"  type="button"  value="Imprimir"  class="SEFAZ-INPUT-Botao"  id="btnImprimir" onclick="solicitarExibirPDF();"/>
								<abaco:botaoCancelarSemMensagem/>
							</div>
						</td>
					</tr>			
			  </table>
		</form>
	<g:mostrarRodape/>
  </body>
</html>