<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
	/*
	* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
	* Arquivo : ViewEnviarSenha.jsp
	* Criação : Abril de 2008 / João Batista Padilha e Silva
	* Revisão : 
	* Log : 
	*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.giaitcd.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
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
		
		function obterArrayBotoes()
		{
			var botao1 = document.form.<%=Form.BOTAO_ENVIAR_SENHA%>;
			return new Array(botao1);
		}
		
	
		function consultarContribuinte()
		{
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_PESQUISAR_CONTRIBUINTE+"=1"%>';
			document.form.submit();
		}
	
		function validaFormulario()
		{
			<c:if test="${empty giaITCDVo.responsavelVo.numrDocumento}">
				alert(<%="'"+MensagemErro.VALIDAR_GIA_ITCD_VALIDA_CPF+"'"%>);
				return false;
			</c:if>
			if(!verificaCamposW3c(document.form.<%=Form.PARAMETRO_CODIGO_GIA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_CODIGO_GIA+"'"%>))
			{
				return false;
			}
			desabilitarBotoes(obterArrayBotoes());
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ENVIAR_SENHA+"=1"%>';
			document.form.submit();
			return true;
		}		
		</script>
		<title><abaco:tituloSistema></abaco:tituloSistema></title>
	</head>
	<body class="SEFAZ-Body" onload="verificaErro();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<form name="form" method="POST" action="#">
			<table width="740" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr class="SEFAZ-TR-Titulo" align="center"> 
						<td colspan="2">Filtro para Pesquisa</td>
				</tr>
				<tr>
					<td colspan="2">	
						<c:set var="enviarSenha"><%=JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_ALTERAR_PESQUISAR_GIAITCD_ENVIAR_SENHA_PESQUISAR_CONTRIBUINTE)%></c:set>					
						<c:choose>
							<c:when test="${enviarSenha && empty giaITCDVo.responsavelVo.numrDocumento}">
								<c:set var="naoOculta" value='true' scope="request"/>													
							</c:when>
							<c:otherwise>
								<c:set var="naoOculta" value='false' scope="request"/>																				
							</c:otherwise>
						</c:choose>
						<c:set var="larguraRotuloPadrao" value="50%" scope="request"/>										
						<c:set var="nomeTdLocalizarContribuinte" value="CPF Inventariante" scope="request"/>						
						<c:choose>
							<c:when test="${not empty exceptionCadastro}">
								<c:set var="ocultaLinkCadastrar" value="false" scope="request"/>
							</c:when>
							<c:otherwise>
								<c:set var="ocultaLinkCadastrar" value="true" scope="request"/>					
							</c:otherwise>
						</c:choose>										
						<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteIncludeNova.jsp"/>
						<c:remove var="naoOculta" scope="request"/>
						<c:remove var="larguraRotuloPadrao" scope="request"/>
						<c:remove var="nomeTdLocalizarContribuinte" scope="request"/>						
						<c:remove var="ocultaLinkCadastrar" scope="request"/>
						<c:remove var="enviarSenha"/>
					</td>
				</tr>
				<c:if test="${not empty giaITCDVo.responsavelVo.numrDocumento}">
					<tr>
						<td width="50%" class="SEFAZ-TD-RotuloSaida">Número do Documento:&nbsp;</td>
						<td width="50%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDocumento}" /></td>
					</tr>
					<tr>
						<td width="50%" class="SEFAZ-TD-RotuloSaida">Nome do Contribuinte:&nbsp;</td>
						<td width="50%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.nomeContribuinte}"  /></td>
					</tr>
				</c:if>
				<tr>
					<td width="50%"  class="SEFAZ-TD-RotuloEntrada">GIA:&nbsp; </td>
					<td width="50%"  class="SEFAZ-TD-CampoEntrada">
						<input type="text" name="<%=Form.CAMPO_CODIGO_GIA%>"  onkeyup="toUpperCaseW3c(this);"		class="SEFAZ-INPUT-Text" 	value="<c:out value="${giaITCDVo.codigoFormatado}"/>"	size="18" maxlength="15">
					</td>
				</tr>
				<tr> 
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr> 
					<td colspan="2"> 
						<div align="center">
							<input name="<%=Form.BOTAO_ENVIAR_SENHA%>"  type="button"  value="<%=Form.TEXTO_BOTAO_ENVIAR_SENHA%>"  class="SEFAZ-INPUT-Botao" onClick="return validaFormulario();"/>
							<abaco:botaoCancelar/>
						</div>
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
		</form>
	</body>
</html>
