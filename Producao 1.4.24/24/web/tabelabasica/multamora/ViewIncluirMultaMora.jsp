<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewAlterarCultura.jsp
* Criação : Outubro de 2007 / Elizabeth Barbosa Moreira e Maxwell Rocha
* Revisão : MAR2008 - Wendell Pereira de Farias
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.multademora.Form"%>
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
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_TIPO_MULTA_MORA%>,<%="'"+MensagemErro.VALIDAR_MULTA_MORA+"'"%>))
                {
                    return false;
                }
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_PERCENTUAL_MULTA_MORA%>,<%="'"+MensagemErro.VALIDAR_PERCENTUAL_MULTA_MORA+"'"%>))
                {
                    return false;
                }
                if(verificarValorPercentual()==false)
                {
                    return false;
                }
                desabilitarBotoes(obterArrayBotoes());
                document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=1"%>';
                document.form.submit();					
                return true;
            }
					
            function verificarValorPercentual()
            {
                var multaMora = document.form.<%=Form.CAMPO_TIPO_MULTA_MORA%>.value.replace(".","").replace(",","");
                var percentualMaximoMultaMora = document.form.<%=Form.CAMPO_PERCENTUAL_MULTA_MORA%>.value.replace(".","").replace(",","");
                multaMora = parseInt(multaMora);
                percentualMaximoMultaMora = parseInt(percentualMaximoMultaMora);
                if (multaMora == 0 )
                {
                    alert("<%=MensagemErro.VALIDAR_MENOR_ZERO_MULTA_MORA%>");
                    document.form.<%=Form.CAMPO_TIPO_MULTA_MORA%>.focus();
                    return false ;
                }
                
                if(percentualMaximoMultaMora == 0)
                {
                    alert("<%=MensagemErro.VALIDAR_PERCENTUAL_MULTA_MORA_MENOR_ZERO%>");
                    document.form.<%=Form.CAMPO_PERCENTUAL_MULTA_MORA%>.focus();
                    return false;
                }

                if(multaMora > percentualMaximoMultaMora)
                {
                    alert("<%=MensagemErro.VALIDAR_MULTA_MORA_MENOR_PERCENTUAL%>");
                    document.form.<%=Form.CAMPO_TIPO_MULTA_MORA%>.focus();
                    return false;
                }
                
                return true;                                                            
            }
					
            function obterArrayBotoes()
            {
                var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
                return new Array(botao1);
            }	
	</script>
		<title><abaco:tituloSistema></abaco:tituloSistema></title>
        <%@ page="/util/ViewVerificaErro.jsp"%>
    </head>
    <body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');log();">
        <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
            <center>
                <form method="POST" action="" name="form">
                    <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                     <tr align="right">
                        <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                        </tr>
                        <tr class="SEFAZ-TR-Titulo" align="center"> 
                            <td colspan="2">Dados do registro</td>
                        </tr>							
                        <tr> 
                            <td class="SEFAZ-TD-RotuloEntrada" width="375">Percentual da Multa de Mora:&nbsp;</td>
                            <td class="SEFAZ-TD-CampoEntrada" width="375"> 
										<abaco:campoMonetario 
											name="<%=Form.CAMPO_TIPO_MULTA_MORA%>" 
											idCampo="<%=Form.CAMPO_TIPO_MULTA_MORA%>"
											quantidadeDigitosInteiros="3" 
											size="8" 
											value="${multaDeMoraVo.percentualMultaMora}" 
											quantidadeCasasDecimais="${multaDeMoraVo.quantidadeCasasDecimais}" 
											mostrarZero="false" >
										</abaco:campoMonetario><font color="red">*</font>
                            </td>
                        </tr>
                        <tr> 
                            <td class="SEFAZ-TD-RotuloEntrada" width="375">Percentual Máximo da Multa de Mora:&nbsp;</td>
                            <td class="SEFAZ-TD-CampoEntrada" width="375"> 
										<abaco:campoMonetario 
											name="<%=Form.CAMPO_PERCENTUAL_MULTA_MORA%>" 
											idCampo="<%=Form.CAMPO_PERCENTUAL_MULTA_MORA%>"
											quantidadeDigitosInteiros="3"
											size="8" 
											value="${multaDeMoraVo.percentualMaximoMultaMora}" 
											quantidadeCasasDecimais="${multaDeMoraVo.quantidadeCasasDecimais}"
											mostrarZero="false" >
										</abaco:campoMonetario><font color="red">*</font>
                            </td>
                        </tr>
                        <tr> 
                            <td width="278">&nbsp;</td>
                        </tr>
                        <tr> 
                            <td colspan="4" align="center"> 
											<abaco:botaoConfirmar nomeBotao="<%=Form.BOTAO_CONFIRMAR_CLONE%>" onClick="validaFormulario();" textoBotao="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" />
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
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
						  <abaco:log/>
                </form>
            </center>
        <g:mostrarRodape/>
    </body>
</html>
