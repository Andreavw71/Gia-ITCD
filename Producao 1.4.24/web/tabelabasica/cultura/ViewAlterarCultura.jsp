<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewAlterarCultura.jsp
* Criação : Outubro de 2007 / Elizabeth Barbosa Moreira
* Revisão: Marlo Einchenberg Motta
* Data revisão: 02/11/2007
* Log : 
*/
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.cultura.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
        <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
        <script type="text/javascript" language="javascript">
            function validaFormulario()
            {
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DESCRICAO_CULTURA%>,<%="'"+MensagemErro.VALIDAR_CULTURA_PARAMETRO_DESCRICAO+"'"%>))
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
                var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
                return new Array(botao1);
            }        
        </script>
   	<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<title><abaco:tituloSistema></abaco:tituloSistema></title>
        </head>
        <body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>'); verificaErro();log();">
            <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
                <center>
                    <form method="POST" action="" name="form">
                        <table class="SEFAZ-TABLE-Moldura" cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                            <tr align="right">
                                <td colspan="2"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                            </tr>
                            <tr class="SEFAZ-TR-Titulo" align="center">
                                <td colspan="2">Dados do registro</td>
                            </tr>					
                            <tr> 
                                <td class="SEFAZ-TD-RotuloEntrada" width="300">Descrição:&nbsp;</td>
                                <td class="SEFAZ-TD-CampoEntrada">
                                    <input name="<%=Form.CAMPO_DESCRICAO_CULTURA%>" class="SEFAZ-INPUT-Text" type="text" size="50" maxlength="50" onblur="toUpperCaseW3c(this);"  value="<c:out value="${culturaVo.descricaoCultura}"></c:out>"/><font color="red">*</font>
                                </td>
                            </tr>
                            <tr>
                                <td class="SEFAZ-TD-RotuloEntrada" width="300">Status:&nbsp;</td>
                                <td class="SEFAZ-TD-CampoEntrada">
												<abaco:campoSelectDominio 
													ajuda="" 
													classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro" 
													name="<%=Form.CAMPO_SELECT_STATUS%>" 
													tabIndex="" 
													mostrarSelecione="true" 
													opcaoSelecionada="${culturaVo.statusCultura.valorCorrente}">
												</abaco:campoSelectDominio>
                                </td>
                            </tr>
                            <tr> 
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr> 
                                <td colspan="2" align="center"> 
                                    <input type="button" value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR_CLONE%>" onClick="return validaFormulario();"/>
                                    <abaco:botaoCancelar/>
                                </td>
                            </tr>
									<tr> 
										<td colspan="2"><font color="red"><b>* Campos Obrigatórios</b></font></td>
									</tr>
                        </table>
                        <table>
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
                <g:mostrarRodape></g:mostrarRodape>
        </body>
</html>
