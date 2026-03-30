<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewAlterarNaturezaOperacao.jsp
* Criação : Outubro de 2007 / Rogério Sanches de Oliveira
* Revisão : MAR2008 - Wendell Pereira de Farias.
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import =" br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%@page import="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>

<%
	pageContext.setAttribute("DOACAO",new Integer(DomnTipoProcesso.DOACAO));
	pageContext.setAttribute("SIM", new Integer(DomnSimNao.SIM));
%>

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
                var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR%>;
                return new Array(botao1);
            }
 
             function validaFormulario()
            {
               //Se o ator não preencher o campo descrição, o sistema apresenta a mensagem de erro ?Favor informar a descrição da natureza da operação? e retornar ao item FP4.
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DESCRICAO_NATUREZA_OPERACAO%>, <%="'"+MensagemErro.VALIDAR_NATUREZA_OPERACAO_DESCRICAO+"'"%>))
                {
                    return false;
                }
					  desabilitarBotoes(obterArrayBotoes());
					  document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=1"%>';
					  document.form.submit();
					  return true;
               
            }
		</script>
        <jsp:include page="/util/ViewVerificaErro.jsp"/>
        <title><abaco:tituloSistema></abaco:tituloSistema></title>
    </head>
  <body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro();log();">
    <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
        <form method="POST" action="#" name="form">
            <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                 <tr align="right">
                        <td colspan="2"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                 </tr>
                <tr class="SEFAZ-TR-Titulo" align="center"> 
                    <td colspan="2">Dados do registro</td>
                </tr>
                <tr> 
                   <td class="SEFAZ-TD-RotuloSaida" width="278">Tipo GIA:&nbsp;</td>
                   <td class="SEFAZ-TD-CampoSaida" width="462"><c:out value="${naturezaOperacaoVo.tipoGIA.textoCorrente}"/></td>
                </tr>
                <tr>
                   <td class="SEFAZ-TD-RotuloSaida" width="278">Tipo Processo:&nbsp;</td>
                   <td class="SEFAZ-TD-CampoSaida" width="462"><c:out value="${naturezaOperacaoVo.tipoProcesso.textoCorrente}"/></td>
                </tr>
					 <c:if test="${naturezaOperacaoVo.tipoProcesso.valorCorrente == DOACAO}"> 
						<td class="SEFAZ-TD-RotuloEntrada" width="278">Isenção Prevista em Lei:&nbsp;</td>
						<td class="SEFAZ-TD-CampoEntrada" width="462">
							<abaco:campoSelectDominio ajuda="Selecione uma opção."
								classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" 
								name="<%=Form.CAMPO_SELECT_ISENCAO_PREVISTA_LEI%>" 
								tabIndex="" 
								idCampo="<%=Form.CAMPO_SELECT_ISENCAO_PREVISTA_LEI%>" 
								mostrarSelecione="false" 
								valorDefault="${naturezaOperacaoVo.flagIsencaoPrevistaLei.valorCorrente}"/>
						</td>
					 </c:if>
                <tr> 
                  <td class="SEFAZ-TD-RotuloEntrada" width="278">Descri&ccedil;&atilde;o:&nbsp;</td>
                  <td class="SEFAZ-TD-CampoEntrada" width="462">
							<abaco:campoStringMaiuscula 
								maxlength="50" 
								name="<%=Form.CAMPO_DESCRICAO_NATUREZA_OPERACAO%>" 
								size="50"
								value="${naturezaOperacaoVo.descricaoNaturezaOperacao}" 
								idCampo="<%=Form.CAMPO_DESCRICAO_NATUREZA_OPERACAO%>">
							</abaco:campoStringMaiuscula><font color="red">*</font>
                </td>
            </tr>
            <tr> 
                <td class="SEFAZ-TD-RotuloSaida" width="278">Base de C&aacute;lculo Reduzido:&nbsp;</td>
                <td class="SEFAZ-TD-CampoSaida" width="462"><c:out value="${naturezaOperacaoVo.tipoBaseCalculo.textoCorrente}"/></td>
            </tr>    
				<c:if test="${naturezaOperacaoVo.tipoBaseCalculo.valorCorrente == SIM}">
					<tr id="<%=Form.CAMPO_PERCENTUAL_NATUREZA_OPERACAO%>x"> 
						<td class="SEFAZ-TD-RotuloSaida" width="278">Percentual Base de C&aacute;lculo:&nbsp;</td>
						<td class="SEFAZ-TD-CampoSaida" width="462"><c:out value="${naturezaOperacaoVo.percentualBaseCalculoFormatada}"/></td>
					</tr>
				</c:if>
				 <tr> 
					  <td class="SEFAZ-TD-RotuloEntrada" width="278">Status:&nbsp;</td>
					  <td class="SEFAZ-TD-CampoEntrada"width="462">
						<abaco:campoSelectDominio 
							ajuda="Selecione uma opção." 
							classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro" 
							name="<%=Form.CAMPO_SELECT_STATUS%>" 
							tabIndex="" 
							idCampo="<%=Form.CAMPO_SELECT_STATUS%>" 
							mostrarSelecione="false" 
							opcaoSelecionada="${naturezaOperacaoVo.statusNaturezaOperacao.valorCorrente}">
						</abaco:campoSelectDominio>
					  </td>
				 </tr>
				<tr> 
					<td colspan="2">&nbsp;</td>
				</tr>
					<tr> 
						 <td colspan="2" align="center"> 
							  <input type="button" value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR%>" onClick="return validaFormulario();"></input>
							  <abaco:botaoCancelar></abaco:botaoCancelar>
						 </td>
					</tr>
					<tr> 
						<td colspan="2"><font color="red"><b>* Campos Obrigatórios</b></font></td>
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
		  <abaco:log/>
    </form>
    <g:mostrarRodape></g:mostrarRodape>
    </body>
</html>
