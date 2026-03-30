<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarContribuinteTipoDocumento.jsp
* Criação : fevereiro 2008 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.giaitcd.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<meta http-equiv=Expires content="Thu, 01 Jan 1970 00:00:00 GMT">
		<meta http-equiv=Cache-Control content=no-store>
		<meta http-equiv=Pragma content=no-cache>
		<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
		<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
		<script type="text/javascript" src="/javascript/funcoesGenericas.js"></script>
		<script type="text/javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
		<script type="text/javascript">
			
			/**
			*	Função utilizada para disparar para a servlet ContribuintePesquisar e consultar o contribuinte de acordo com os
			*	parametros informado na view de Pesquisa.
			*/
			function consultarContribuinteTipoDocumento()
			{
				if(!verificaCamposW3c(document.form.<%=Form.COMBO_PESQUISA_OPCAO_CONTRIBUINTE%>,"Favor selecionar um tipo de documento."))
				{
					return false;
				}
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DESCRICAO_DOCUMENTO%>,"Favor informar o número documento."))
				{
					return false;
				}
				desabilitarBotoes(obterArrayBotoes());
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR_CONTRIBUINTE_TIPO_DOCUMENTO+"=1"%>';
				document.form.submit();
				return true;
			}
			
			/**
			*	Função utilizada para capturar o botão que será desabilitado ao enviar uma requisição para o servidor.
			*/
			function obterArrayBotoes()
			{
				var botao1 = document.form.<%=JspUtil.BOTAO_LOCALIZAR_CONTRIBUINTE%>;
				return new Array(botao1);
			}
	
		</script>
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<title><abaco:tituloSistema/></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
			<form name="form" method="POST" action="#">
				<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
				<tr align="right"> 
               <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
             </tr> 
            	<tr class="SEFAZ-TR-Titulo">
						<td colspan="3">Pesquisar Contribuinte Por:</td>		
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloEntrada">Opção de Consulta:&nbsp;</td>
						<td class="SEFAZ-TD-ComboBox" align="center">
							<select name="<%=Form.COMBO_PESQUISA_OPCAO_CONTRIBUINTE%>">
								<option value=""><%=Form.SELECIONE%></option>
								<c:forEach var="tipoDocumento" items="${colecaoTipoDocumento}">
									<option value="<c:out value="${tipoDocumento.codgDocumento}"></c:out>">
									<c:out value="${tipoDocumento.descDocumento}"></c:out>
								</option>
							</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloEntrada">Número do Documento:&nbsp;</td>
						<td class="SEFAZ-TD-CampoEntrada">
						<input type="text" name="<%=Form.CAMPO_DESCRICAO_DOCUMENTO%>" class="SEFAZ-INPUT-Text" id="<%=Form.CAMPO_DESCRICAO_DOCUMENTO%>" size="50" maxlength="50"</input>
						</td>
					</tr>
				</table>
				<br>
				<br>
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
					<tr> 
						<td colspan="4" align="center"> 
							<abaco:botaoVoltar nomeContadorSubmit="pesquisarContribuinte"/>						
							<abaco:botaoCancelar/>
							<input type="button" value="Localizar" class="SEFAZ-INPUT-Botao" name="<%=JspUtil.BOTAO_LOCALIZAR_CONTRIBUINTE%>" onClick="consultarContribuinteTipoDocumento();"></input>
						</td>
					</tr>
				</table>
				<abaco:mensagemAguardeCarregando/>
			</form>
		<g:mostrarRodape/>
	</body>
</html>
