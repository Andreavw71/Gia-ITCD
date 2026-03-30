<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewReativarGIAITCD.jsp
* Criação : fevereiro de 2008 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log :
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.giaitcd.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
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
			function obterArrayBotoes()
			{
				var botao1 = document.form.<%=Form.BOTAO_REATIVAR_CLONE%>;
				return new Array(botao1);
			}
			function validaFormulario()
			{
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_JUSTIFICATIVA%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_STATUS_JUSTIFICATIVA_REATIVACAO+"'"%>))
				{
					return false;
				}
				desabilitarBotoes(obterArrayBotoes());
				document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR +"=1"%>';
				document.form.submit();
			}
		</script>
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<title><abaco:tituloSistema></abaco:tituloSistema></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>'); verificaErro();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
		<center>
			<form method="POST" name="form"  action="#">
				<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
					<tr align="right">
						<td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
					</tr>
					<tr class="SEFAZ-TR-Titulo" align="center"> 
						<td colspan="4">Dados da GIA </td>
					</tr>
					<tr>
						<td width="211" class="SEFAZ-TD-RotuloSaida">Número da GIA :&nbsp;</td>
						<td width="183" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.codigo}"/></td>
					</tr>
					<tr>
						<td width="162" class="SEFAZ-TD-RotuloSaida">Tipo de GIA:&nbsp;</td>
						<td width="184" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.tipoGIA.textoCorrente}"/></td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloSaida">Tipo de processo:&nbsp;</td>
						<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.textoCorrente}"/></td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloSaida">Nome:&nbsp;</td>
						<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.nomeContribuinte}"/></td>
					</tr>
					<tr>
						<td height="21" class="SEFAZ-TD-RotuloSaida">Status da GIA:&nbsp;</td>
						<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.statusVo.statusGIAITCD.textoCorrente}"/></td>
					</tr>
					<tr align="left">
						<td colspan="1" class="SEFAZ-TD-RotuloEntrada">Justificativa:&nbsp;</td>
						<td colspan="3" class="SEFAZ-TD-CampoEntrada">
							<textarea name="<%=Form.CAMPO_JUSTIFICATIVA%>" class="SEFAZ-INPUT-Text" rows="4" cols="50" onKeyUp="cortaPalavrasW3c(this,400)"></textarea><font color="red">*</font>
						</td>
					</tr>
					<tr> 
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr> 
						<td colspan="4" align="center"> 					 
						<input type="button" value="<%=Form.TEXTO_BOTAO_REATIVAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_REATIVAR_CLONE%>" onClick="validaFormulario()"></input>
						<abaco:botaoVoltar nomeContadorSubmit="1"></abaco:botaoVoltar>
						<abaco:botaoCancelar></abaco:botaoCancelar>
						</td>
					</tr>
					<tr>
						<td colspan="4"><font color="red"><b>* Campos Obrigatórios</b></font></td>
					</tr>
					<tr> 
						<td colspan="4">&nbsp;</td>
					</tr>
				</table>
				<abaco:mensagemAguardeCarregando></abaco:mensagemAguardeCarregando>
			</form>
		</center>
		<g:mostrarRodape></g:mostrarRodape>
	</body>
</html>