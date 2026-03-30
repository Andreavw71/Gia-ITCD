<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewListarGIAITCDAvaliadas.jsp
* Criação : Fevereiro de 2008 / Elizabeth Barbosa Moreira
* Revisão : 
* Log : 
*/
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.avaliacao.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
	<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
	<script type="text/javascript">	
		function consultarGIAITCDAvaliada()
		{
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_PESQUISAR_GIAITCD_AVALIADA+"=1"%>';
			document.form.submit();
		}
	</script>
	<jsp:include page="/util/ViewVerificaErro.jsp"/>
	<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
    </head>
	<body class="SEFAZ-Body">
        <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
           <center>
			 <form method="POST" action="" name="form">
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
					<tr class="SEFAZ-TR-Titulo" align="center"> 
						<td colspan="3">Resultados da Pesquisa</td>
					</tr>
					<tr class="SEFAZ-TR-SubTituloEsq"> 
						<td width="231" align="center">Número</td>
						<td width="286">GIA</td>
						<td width="223" align="center">&nbsp;</td>
					</tr>
					<tr class="SEFAZ-TR-ExibicaoImpar"> 
						<td width="231" align="center">00001</td>
						<td align="left">GIA-ITCD 1 </td>
						<td align="left"><a href="javascript:consultarGIAITCDAvaliada();" >Consultar</a></td>
					</tr>
					<tr class="SEFAZ-TR-ExibicaoPar">
						<td align="center">00002</td>
						<td align="left">GIA-ITCD 2 </td>
						<td align="left"><a href="#">Consultar</a></td>
					</tr>
				</table>
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
					<tr> 
						<td colspan="5"> 
							<div align="center">
								<input type="button" value="   Cancelar  " class="SEFAZ-INPUT-Botao" onclick="javascript: history.back();" name="btnCancelar"/>
							</div>
						</td>
					</tr>
				</table>
			 </form>
			 </center>
		<g:mostrarRodape/>
	</body>
</html>