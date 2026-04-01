<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarAvaliacaoPorAgenfa.jsp
* Criação : Fevereiro de 2008 / Elizabeth Barbosa Moreira
* Revisão : 
* Log : 
*/
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.avaliacao.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsultaAvaliacao"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
			<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script> 
			<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
			<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
	<script type="text/javascript">	
		 function validaFormulario()
		{
			 if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_PESQUISA%>,<%="'"+MensagemErro.VALIDAR_AVALIACAO_PARAMETRO_TIPO_SELECAO+"'"%>))
			 {
				  return false;
			 }
			  if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_INICIAL%>,<%="'"+MensagemErro.VALIDAR_AVALIACAO_DATA_INICIAL+"'"%>))
			 {
				  return false;
			 }
		desabilitarBotoes(obterArrayBotoes());
		document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_PESQUISAR_AVALIACAO_POR_AGENFA+"=1"%>';
		document.form.submit();
		return true;
		}	 
		 function obterArrayBotoes()
		{
			 var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
			return new Array(botao1);
		}
	</script>
		<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro();">
	<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
	<center>
		 <form method="POST" name="form"   action="<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>">
			<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
			<tr align="right"> 
					<td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
		   </tr> 
				<tr class="SEFAZ-TR-Titulo" align="center"> 
					<td colspan="2"><c:out value='${giaITCDVo.bemTributavelVo.avaliacaoBemTributavelVo.unidadeSefazIntegracaoVo.nomeUnidade}'/></td>
				</tr>
				<tr>
					<td width="305" class="SEFAZ-TD-RotuloEntrada">Tipo de consulta:&nbsp;</td>
					<td width="435" class="SEFAZ-TD-CampoEntrada">
							<select name="<%=Form.CAMPO_SELECT_TIPO_PESQUISA%>" >
<%
					long valorTipoConsulta = request.getAttribute(Form.CAMPO_SELECT_TIPO_PESQUISA) != null ? Long.parseLong(request.getAttribute(Form.CAMPO_SELECT_TIPO_PESQUISA).toString()) : -1;
					String selected = "";
					if(valorTipoConsulta == -1)
						selected = "selected";
					out.println("<option value='-1' "+ selected +">" + Form.SELECIONE + "</option>");
					for(int i = 0; i < DomnTipoConsultaAvaliacao.domnIndice.length; i++){
					  DomnTipoConsultaAvaliacao atual = new DomnTipoConsultaAvaliacao(DomnTipoConsultaAvaliacao.domnIndice[i]);
					  if(atual.getValorCorrente() == valorTipoConsulta)
						selected = "selected";
					  else
						selected = "";
					  out.println("<option  value='"+ atual.getValorCorrente() +"' "+selected+" >"+ atual.getTextoCorrente() +"</option>");
					}
%>
						</select>
					</td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloEntrada">Data Inicial:&nbsp; </td>
					<td class="SEFAZ-TD-CampoEntrada">
					<input name="<%=Form.CAMPO_DATA_INICIAL%>" 
							type="text" 
							class="SEFAZ-INPUT-Text" 
							id="dataInicial" 
							onBlur="formataDataW3c(this)" 
							onKeyPress="checaDataW3c(this, event)" 
							size="20" 
							maxlength="60" 
							value="<c:out value='${avaliacaoBemTributavelVo.periodoConsulta.dataInicioFormatada}'/>"/></td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloEntrada">Data Final:&nbsp; </td>
					<td class="SEFAZ-TD-CampoEntrada">
					<input name="<%=Form.CAMPO_DATA_FINAL%>" 
						type="text" 
						class="SEFAZ-INPUT-Text" 
						id="dataFinal" 
						onBlur="formataDataW3c(this)" 
						onKeyPress="checaDataW3c(this, event)" 
						size="20" 
						maxlength="60"
						value="<c:out value='${avaliacaoBemTributavelVo.periodoConsulta.dataFimFormatada}'/>"/></td>
				</tr>    
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
				<tr> 
					<td colspan="5"> 
						<div align="center">
							 <input name="<%=Form.BOTAO_PESQUISAR_CLONE%>" type="button"   value="<%=Form.TEXTO_BOTAO_PESQUISAR%>" class="SEFAZ-INPUT-Botao" id="btnPesquisar" onClick="return validaFormulario();"/>
							<abaco:botaoCancelarSemMensagem/>
						</div>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>
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