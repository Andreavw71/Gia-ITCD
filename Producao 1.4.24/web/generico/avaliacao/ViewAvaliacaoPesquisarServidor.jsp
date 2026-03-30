<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewIncluirAvaliacaoBem.jsp
* Criação : Janeiro de 2008 / Elizabeth Barbosa Moreira
* Revisão : 
* Log : 
*/
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" %>
<%--AcessoWEB Valida--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.avaliacao.Form"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<HTML>
	<HEAD>
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
			<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
			<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
			<script type="text/javascript">
				function validaFormulario()
				{				
					if(!verificaCamposW3c(document.form.<%=Form.CAMPO_MATRICULA_SERVIDOR%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_SERVIDOR+"'"%>))
					{
						return false;
					}
									
					desabilitarBotoes(obterArrayBotoes());
					document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_INCLUIR_SERVIDOR+"=1"%>';
					document.form.submit();
				}
				function obterArrayBotoes()
				{
					var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
					return new Array(botao1);
				}
			</script>
			<link rel="stylesheet" href="/estilos/SefazEstilos.css" type="text/css">
			<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
					<jsp:include page="/util/ViewVerificaErro.jsp"/>
			<BODY class="SEFAZ-Body" onload="verificaErro();">
			<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
			<CENTER>
				<form method="POST" name="form"  onsubmit="javascript:return validaForm(this)" action="#"><br/>
					<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
						<tr class="SEFAZ-TR-Titulo" align="center"> 
							<td colspan="2">Filtro para Pesquisa</td>
						</tr>
						<tr> 
							<td class="SEFAZ-TD-RotuloEntrada" width="315">Matr&iacute;cula:&nbsp;</td>
							<td class="SEFAZ-TD-CampoEntrada" width="425"> 
								<input name="<%=Form.CAMPO_MATRICULA_SERVIDOR%>" type="text"  size="13" maxlength="20" <%=JspUtil.getCampoApenasNumero()%>/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="SEFAZ-TD-CampoEntrada">&nbsp;</td>
						</tr>
						<tr> 
							<td colspan="2" class="SEFAZ-TD-CampoEntrada"> 
								<div align="center">
									<abaco:botaoVoltar nomeContadorSubmit="pesquisarServidor" nomeBotao="Voltar"/>
									<input type="hidden" name="incluirAvaliacaoBem" value="<%=Integer.parseInt(request.getParameter("incluirAvaliacaoBem")) + 1%>">
									<input name="<%=Form.BOTAO_PESQUISAR_CLONE%>" type="button"   value="<%=Form.TEXTO_BOTAO_PESQUISAR%>" class="SEFAZ-INPUT-Botao" id="btnPesquisar" onClick=" validaFormulario();"/>
									<abaco:botaoCancelar/>
								</div>
							</td>
							<tr>
								<td><font color="red"><b>* Campo Obrigatório</b></font></td>
							</tr>
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
			</CENTER>
			<g:mostrarRodape />
		</BODY>
</HTML>