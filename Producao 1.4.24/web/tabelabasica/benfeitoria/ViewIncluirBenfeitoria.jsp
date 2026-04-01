<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewIncluirBenfeitoria.jsp
* Criação : Setembro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : Marlo Eichenberg Motta / MAR2008 - Wendell Pereira de Farias
* Log : 17/10/2007
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
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
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DESCRICAO_BENFEITORIA%>,<%="'"+MensagemErro.VALIDAR_BENFEITORIA_PARAMETRO_DESCRICAO+"'"%>))
                {
                    return false;
                }
                desabilitarBotoes(obterArrayBotoes());
                document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=1"%>';
                document.form.submit();
                return true;
            }
            function obterArrayBotoes()
            {
                    var botaoConfirmar = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
                    return new Array(botaoConfirmar);
            }
        </script>
        <jsp:include page="/util/ViewVerificaErro.jsp"/>
        <title><abaco:tituloSistema></abaco:tituloSistema></title>
    </head>
    <body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro(); log();">
        <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
            <center>
                <form method="POST" action="" name="form">
                    <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                      <tr align="right">
                            <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                        </tr>
                        <tr class="SEFAZ-TR-Titulo" align="center">
                            <td colspan="4">Dados do registro</td>
                        </tr>
                        <tr> 
                            <td class="SEFAZ-TD-RotuloEntrada" width="278">Descrição:&nbsp;</td>
                            <td class="SEFAZ-TD-CampoEntrada" width="462" >
                                    <input name="<%=Form.CAMPO_DESCRICAO_BENFEITORIA%>" 
                                                                      class="SEFAZ-INPUT-Text" 
                                                                      type="text" 
                                                                      size="50" 
                                                                      maxlength="50" 
                                                                      onblur="toUpperCaseW3c(this);"
                                                                      value="<c:out value="${benfeitoriaVo.descricaoBenfeitoria}"/>"><font color="red">*</font>
                            </td>
                        </tr>	
                        <tr> 
                            <td colspan="4">&nbsp;</td>
                        </tr>
                        <tr> 
                            <td colspan="4" align="center"> 
                                <input type="button" value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR_CLONE%>" onClick="return validaFormulario();"/>
                                <abaco:botaoCancelar/>
                            </td>
                        </tr>
							<tr> 
								<td colspan="4"><font color="red"><b>* Campos Obrigatórios</b></font></td>
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
                </center>
            <g:mostrarRodape/>
    </body>
</html>
