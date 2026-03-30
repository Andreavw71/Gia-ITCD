<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarStatusGIAITCD.jsp
* Criação : Janeiro de 2008 / Elizabeth Barbosa Moreira
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
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<title><abaco:tituloSistema/></title>
	<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
	<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
	<script type="text/javascript">
		function validaFormulario()
		{
			//Validação para código GIA.
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_CODIGO_GIA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_CODIGO_GIA+"'"%>))
			{
				return false;
			}
			//Validação para verificar se o foi localizado combribuinte.
			if(!validaDoc())
			{
				alert ("É obrigado localizar contribuinte");
				return false;
			}
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR+"=1"%>';
			document.form.submit();
			return true;
		}
			
		function obterArrayBotoes()
		{
			var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
			return new Array(botao1);
		}
		
		function consultarContribuinte()
		{
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_PESQUISAR_CONTRIBUINTE+"=1"%>';
			document.form.submit();
		}
		
		function validaDoc()
		{
			if(document.getElementById("idNumrDoc").innerHTML.length==0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	</script>
	<jsp:include page="/util/ViewVerificaErro.jsp"/>
	<title><abaco:tituloSistema/></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>'); 
																		 verificaErro();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
			<center>
			<form method="POST" name="form"  action="#" >
			  <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
			  <tr align="right"> 
              <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
           </tr> 
          		<tr class="SEFAZ-TR-Titulo" align="center"> 
						<td colspan="2">Filtros da  Pesquisa</td>
					</tr>
					<tr>
						<td colspan="2">
								<c:choose>
									<c:when test="${empty giaITCDVo.responsavelVo.numrDocumento}">
										<c:set var="naoOculta" value='true' scope="request"/>													
									</c:when>
									<c:otherwise>
										<c:set var="naoOculta" value='false' scope="request"/>																				
									</c:otherwise>
								</c:choose>
								<c:set var="larguraRotuloPadrao" value="370" scope="request"/>
								<c:set var="nomeTdLocalizarContribuinte" value="CPF / CNPJ" scope="request"/>
								<c:set var="ocultaLinkCadastrar" value="true" scope="request"/>					
								<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteIncludeNova.jsp"/>
								<c:remove var="naoOculta" scope="request"/>
								<c:remove var="nomeTdLocalizarContribuinte" scope="request"/>
								<c:remove var="larguraRotuloPadrao" scope="request"/>
								<c:remove var="ocultaLinkCadastrar" scope="request"/>						
						</td>
					</tr>
					<tr>
						<td  class="SEFAZ-TD-RotuloSaida" width="50%">Número do Documento:&nbsp;</td>
						<td class="SEFAZ-TD-CampoSaida"><div id="idNumrDoc"><c:out value="${giaITCDVo.responsavelVo.numrDocumento}" /></div></td>
					</tr>
					<tr>
						<td  class="SEFAZ-TD-RotuloSaida" width="50%">Nome do Contribuinte:&nbsp;</td>
						<td  class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.nomeContribuinte}"  /></td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloEntrada" width="50%">Número da GIA:&nbsp;</td>
						<td ><abaco:campoApenasNumero maxlength="12" name="<%=Form.CAMPO_CODIGO_GIA%>" size="15" value="${giaITCDVo.codigoFormatado}"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
					<tr> 
						<td colspan="3"> 
							<div align="center">
							<input name="<%=Form.BOTAO_PESQUISAR_CLONE%>"  type="button"  value="<%=Form.TEXTO_BOTAO_PESQUISAR%>"  class="SEFAZ-INPUT-Botao"  id="btnPesquisar"   onClick="validaFormulario();"/>
							<abaco:botaoCancelar/>
							</div>
						</td>
					</tr>
			</table>
			<abaco:mensagemAguardeCarregando/>
			</form>
			</center>
		<g:mostrarRodape/>
	</body>
</html>
