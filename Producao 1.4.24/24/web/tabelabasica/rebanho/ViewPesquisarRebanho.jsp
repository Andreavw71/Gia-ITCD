<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarRebanho.jsp
* Criação : Outubro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão: Marlo Einchenberg Motta
* Data revisão: 03/11/2007
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.Form"%>
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
			function validaFormulario()
			{				
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_PESQUISA%>,<%="'"+MensagemErro.VALIDAR_REBANHO_PARAMETRO_TIPO_SELECAO+"'"%>))
				{
					return false;
				}
				if(document.getElementById("<%=Form.CAMPO_DESCRICAO_REBANHO%>")!=null)
				{
					if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DESCRICAO_REBANHO%>,<%="'"+MensagemErro.VALIDAR_REBANHO_PARAMETRO_DESCRICAO+"'"%>))
					{
						return false;
					}
				}				
				if(document.getElementById("<%=Form.CAMPO_CODIGO_REBANHO%>")!=null)
				{
					if(!verificaCamposW3c(document.form.<%=Form.CAMPO_CODIGO_REBANHO%>,<%="'"+MensagemErro.VALIDAR_REBANHO_PARAMETRO_CODIGO+"'"%>))
					{
						return false;
					}
					if(!verificaCamposW3cByValor(document.form.<%=Form.CAMPO_CODIGO_REBANHO%>,0,<%="'"+MensagemErro.VALIDAR_REBANHO_LISTAR_CODIGO+"'"%>))
					{
						return false;
					}
				}
				desabilitarBotoes(obterArrayBotoes());
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR+"=1"%>';
				document.form.submit();
				return true;
			}
		
			function obterArrayBotoes()
			{
				var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
				return new Array(botao1);
			}
			
				/*
			 * Método responsável por habilitar os campos do formulário.
			 */
			
			
			function habilitarCampo()
			{
					comboOpcao = document.form.<%=Form.CAMPO_SELECT_TIPO_PESQUISA%>.value;
				
					if(comboOpcao == "")
					{					
						document.getElementById('rotuloTipoPesquisa').innerHTML = '';
						document.getElementById('campoTipoPesquisa').innerHTML = '';
						codigo = "";
						descricao = "";						
					}
					if(comboOpcao == "1")
					{
						document.getElementById('rotuloTipoPesquisa').innerHTML = 'Código:&nbsp;';
						document.getElementById('campoTipoPesquisa').innerHTML = '<input name="<%=Form.CAMPO_CODIGO_REBANHO%>" id="<%=Form.CAMPO_CODIGO_REBANHO%>" class="SEFAZ-INPUT-Text" type="text"  value="<c:out value="${rebanhoVo.parametroConsulta.codigoFormatado}"/>"  size="5" maxlength="5" <%=JspUtil.getCampoApenasNumero()%>><font color="red">*</font>';
						descricao = "";
					}
					if(comboOpcao == "2")
					{
						document.getElementById('rotuloTipoPesquisa').innerHTML = 'Descrição:&nbsp;';
						document.getElementById('campoTipoPesquisa').innerHTML = '<input name="<%=Form.CAMPO_DESCRICAO_REBANHO%>" id="<%=Form.CAMPO_DESCRICAO_REBANHO%>" class="SEFAZ-INPUT-Text" type="text" size="50" maxlength="50" onblur="toUpperCaseW3c(this);"  value="<c:out value='${rebanhoVo.parametroConsulta.descricaoRebanho}'/>"/><font color="red">*</font>';
						codigo = "";	
					}
			}
		</script>		
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<title><abaco:tituloSistema></abaco:tituloSistema></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');habilitarCampo(); verificaErro();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
		<center>
			<form method="POST" name="form" onsubmit="return validaFormulario()">
				<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">                                
                 <tr align="right">
                     <td colspan="2"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                 </tr>
            	<tr class="SEFAZ-TR-Titulo" align="center">
						<td colspan="2">Filtro para Pesquisa</td>
					</tr>
					<tr> 
						<td class="SEFAZ-TD-RotuloEntrada" width="278">Tipo de Pesquisa:&nbsp;</td>
						<td class="SEFAZ-TD-CampoEntrada" width="462">
							<select name="<%=Form.CAMPO_SELECT_TIPO_PESQUISA%>" onChange="habilitarCampo()">
								<option value="" selected><%=Form.SELECIONE%></option>			
								<option value="1" <c:if test="${(rebanhoVo!=null) && (param.campoSelectTipoPesquisa==1)}"><c:out value="selected"></c:out></c:if>>CÓDIGO</option>
								<option value="2" <c:if test="${(rebanhoVo!=null) && (param.campoSelectTipoPesquisa==2)}"><c:out value="selected"></c:out></c:if>>DESCRIÇÃO</option>
							</select><font color="red">*</font>
						</td>
					</tr>
					<tr> 
					<td class="SEFAZ-TD-RotuloEntrada"><div id="rotuloTipoPesquisa"></div></td>
					<td class="SEFAZ-TD-CampoEntrada"><div id="campoTipoPesquisa"></div></td>
					</tr>	
					<tr> 
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr> 
						<td colspan="2" align="center"> 
							<input name="<%=Form.BOTAO_PESQUISAR_CLONE%>" type="button"   value="<%=Form.TEXTO_BOTAO_PESQUISAR%>" class="SEFAZ-INPUT-Botao" id="btnPesquisar" onClick="return validaFormulario();"/>
							<abaco:botaoCancelarSemMensagem></abaco:botaoCancelarSemMensagem>
					 </td>
					</tr>
					<tr> 
						<td colspan="2"><font color="red"><b>* Campos Obrigatórios</b></font></td>
					</tr>					
					</table>
					<table>
					<tr>
					<td colspan="5">
						<div align="center">
							<abaco:mensagemAguardeCarregando></abaco:mensagemAguardeCarregando>
						</div>
					<td>
				</tr>
				<tr> 
					<td colspan="2">
						<jsp:include page="/tabelabasica/rebanho/ViewListarRebanho.jsp"></jsp:include>				
					</td>
				</tr>
			</table>				
			</form>
		</center>
		<g:mostrarRodape></g:mostrarRodape>
	</body>
</html>
