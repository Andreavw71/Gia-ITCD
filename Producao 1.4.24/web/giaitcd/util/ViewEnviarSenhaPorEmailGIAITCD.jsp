<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewEnviarSenhaPorEmailGIAITCD.jsp
* Criação : Janeiro de 2008 / Rogério Sanches de Oliveira 
* Log : 
*/
--%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.giaitcd.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>

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
			var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
			return new Array(botao1);
		}
		function validaFormulario()
			{		
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.TEXTO_BOTAO_CONFIRMAR+"=1"%>';
				document.form.submit();
				return true;
			}
				</script>
		<title><abaco:tituloSistema/></title>
	</head>
	<body class="SEFAZ-Body" onload="verificaErro();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
	<form method="POST" name="form"  action="#">
	<table align="center" border="0" width="740" cellspacing="0" cellpadding="0">
	<tr>
		<td align="right">A sua senha foi enviada para o e-mail:</td>
		<td><b><c:out value="${giaITCDVo.responsavelVo.email}"/></b></td>
	</tr>
    <tr> 
      <td colspan="3"> 
  	    <div align="center">
			<abaco:botaoRetornoMenu/>
		</div>
		</td>
    </tr>
  </table>
  	<table width="740" border="0">
		<tr>
			<td>
				<div align="center">
					<abaco:mensagemAguardeCarregando></abaco:mensagemAguardeCarregando>
				</div>
			<td>
		</tr>
	</table>
<g:mostrarRodape/>
</form>
</body>
</html>
