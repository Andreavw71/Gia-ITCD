<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewImprimirAvaliacao.jsp
* Criação : janeiro de 2008 / João Batista Padilha e Silva
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
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<html>
	<head>
      <title><abaco:tituloSistema/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
		<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
		<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script> 
      <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
      <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
      <script type="text/javascript">                               		
		function obterArrayBotoes()
		{
			var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
			return new Array(botao1);
		}
		
		function imprimirDocumentoAvaliacao()
		{
			document.form.<%=Form.BOTAO_IMPRIMIR%>.disabled = true;
			document.form.<%=Form.BOTAO_IMPRIMIR%>.className = "SEFAZ-INPUT-Botao-Disabled";
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_IMPRIMIR_DOCUMENTO_AVALIACAO+"=5"%>';
			window.open('','relatorioGIAITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
			document.form.target = 'relatorioGIAITCD';			
			document.form.submit();
			
			//document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_IMPRIMIR_DOCUMENTO_ARRECADACAO+"=6"%>';
			//window.open('','relatorioGIAITCDDAR', 'fullscreen=yes');
			//document.form.target = 'relatorioGIAITCDDAR';			
			//document.form.submit();
		}
		
				
      </script>
      <jsp:include page="/util/ViewVerificaErro.jsp"/>
      <title><abaco:tituloSistema/></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>'); verificaErro();">
   <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
	<center>
	<form method="POST" name="form"  action="#" >
		<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">			
		   <tr align="right"> 
           <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
         </tr> 
         <tr></tr>
			<tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="4">Dados da GIA </td>
			</tr>
			<tr>
				<td width="200" class="SEFAZ-TD-RotuloSaida">N&uacute;mero da GIA:&nbsp;</td>
				<td width="540" class="SEFAZ-TD-CampoSaida" colspan="3"><c:out value="${giaITCDVo.codigo}" /></td>
			</tr>
			<tr>
				<td width="200" class="SEFAZ-TD-RotuloSaida">Nome do Declarante:&nbsp;</td>
				<td width="260" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.nomeContribuinte}" /> </td>				
				<td width="170" class="SEFAZ-TD-RotuloSaida">CPF/CNPJ Declarante:&nbsp;</td>
				<td width="110" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDocumento}" /></td>
			</tr>
			<tr>
				<td width="200" class="SEFAZ-TD-RotuloSaida">Nome do Procurador:&nbsp;</td>
				<td width="260" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.nomeContribuinte}" /></td>
				<td width="170" class="SEFAZ-TD-RotuloSaida">CPF do Procurador:&nbsp;</td>
				<td width="110" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.numrDocumento}" /></td>
			</tr>
			<tr>
				<td width="200" class="SEFAZ-TD-RotuloSaida">AGENFA que protocolou:&nbsp;</td>
				<td width="540" class="SEFAZ-TD-CampoSaida" colspan="3"><c:out value="${giaITCDVo.agenciaProtocolo.codgUnidade}" />  -  <c:out value="${giaITCDVo.agenciaProtocolo.nomeUnidade}" /></td>
			</tr>
		</table>
		<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
			<tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="4">Bens</td>
			</tr>
			<c:forEach var="bemTributavelVo" items="${giaITCDVo.bemTributavelVo.collVO}" varStatus="contadorBem">
				<c:if test="${not contadorBem.first}">
					<tr class="SEFAZ-TR-SubTitulo" align="center"> 
						<td colspan="4">&nbsp;</td>
					</tr>							
				</c:if>
				<tr> 
					<td width="20%" class="SEFAZ-TD-RotuloSaida">Tipo de Bens:&nbsp;</td>
					<td width="30%" class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavelVo.bemVo.classificacaoTipoBem.textoCorrente}"></c:out></td>					
					<td width="20%" class="SEFAZ-TD-RotuloSaida">Descrição dos Bens:&nbsp;</td>
					<td width="30%" class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavelVo.descricaoBemTributavel}"></c:out></td>
				</tr>
				<tr>
					<td width="20%" class="SEFAZ-TD-RotuloSaida">Valor Arbitrado:&nbsp;</td>
					<td width="30%" class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavelVo.valorMercadoInformadoFormatado}"></c:out></td>					
					<td width="20%" class="SEFAZ-TD-RotuloSaida">Valor da Avaliação:&nbsp;</td>
					<td width="30%" class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavelVo.avaliacaoBemTributavelVo.valorAvaliacaoFormatado}"></c:out></td>
				</tr>
				<tr>
					<td width="20%" class="SEFAZ-TD-RotuloSaida">Servidores:&nbsp;</td>
					<td width="80%" class="SEFAZ-TD-CampoSaida" colspan="3">
						<c:forEach var="avaliacaoBemTributavelServidorVo" items="${bemTributavelVo.avaliacaoBemTributavelVo.avaliacaoBemTributavelServidorVo.collVO}" varStatus="contador">
							<c:out value="${avaliacaoBemTributavelServidorVo.servidorSefazVo.nomeServidor}"></c:out><c:if test="${not contador.last}">,&nbsp;</c:if>
						</c:forEach>
					</td>
				</tr>	
			</c:forEach>
		</table>
		<br/>
		<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
			<tr> 
				<td align="center"> 
					<input name="<%=Form.BOTAO_IMPRIMIR%>" type="button" class="SEFAZ-INPUT-Botao" id="btnImprimir" value="<%=Form.TEXTO_BOTAO_IMPRIMIR%>" onClick="imprimirDocumentoAvaliacao();"/>
					<abaco:botaoVoltar nomeContadorSubmit="Voltar" tabIndex="1"></abaco:botaoVoltar>
					<abaco:botaoCancelarSemMensagem/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<script type="text/javascript" language="javascript">gerarSpanMesgBotao();</script>
					</div>
				<td>
			</tr>
		</table>
	</form>
	</center>
	<g:mostrarRodape/>
	</body>
</html>
