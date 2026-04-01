<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarBem.jsp
* Criação : Setembro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : Marlo Eichenberg Motta 
* Log : 08/10/2007
*/
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.bem.Form"%>
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
            /*
             * Método responsável por realizar a validação dos campos do formulário.
             */
            function validaFormulario()
            {
                if (!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>,<%="'"+MensagemErro.VALIDAR_BEM_PARAMETRO_TIPO+"'"%>))
                {
                    return false;
                }
                if (!verificaCamposW3c(document.form.<%=Form.CAMPO_DESCRICAO_BEM%>,<%="'"+MensagemErro.VALIDAR_BEM_PARAMETRO_DESCRICAO+"'"%>))
                {
                    return false;
                }
                desabilitarBotoes(obterArrayBotoes());
                document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR+"=1"%>';
                document.form.submit();
                return true;
            }
            /*
             * Método responsável pela criação e exibição de botões em tempo de execução.
             */
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
                comboOpcao = document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>.value;					
                if(comboOpcao == "")
                {					
                    document.getElementById('rotuloDescricao').innerHTML = '';
                    document.getElementById('campoDescricao').innerHTML = '';
                }
                else 
                {
                    document.getElementById('rotuloDescricao').innerHTML = 'Descrição:&nbsp;';
                    document.getElementById('campoDescricao').innerHTML = '<input name="<%=Form.CAMPO_DESCRICAO_BEM%>" type="text" class="SEFAZ-INPUT-Text" size="50" maxlength="50" onblur="toUpperCaseW3c(this);" value="<c:out value="${bemVo.parametroConsulta.descricaoTipoBem}"/>" ><font color="red">*</font>';
                    document.form.<%=Form.CAMPO_DESCRICAO_BEM%>.focus();
                }
            }
                        
        </script>
        <jsp:include page="/util/ViewVerificaErro.jsp"/>
        <title><abaco:tituloSistema></abaco:tituloSistema></title>
    </head>
    <body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');habilitarCampo(); verificaErro();">
        <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
            <center>
                <form method="POST" name="form"  onsubmit="javascript:return false;"  action="<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>">
                    <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                        <tr align="right">
                            <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                        </tr>
                        <tr class="SEFAZ-TR-Titulo" align="center">
                            <td colspan="4">Filtro para Pesquisa</td>
                        </tr>
                        <tr> 
                            <td class="SEFAZ-TD-RotuloEntrada" width="278">Tipo de Bem:&nbsp;</td>
                            <td class="SEFAZ-TD-CampoEntrada" width="462">
										<abaco:campoSelectDominio 
											ajuda="" 
											classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem" 
											name="<%=Form.CAMPO_SELECT_TIPO_BEM%>" 
											tabIndex="" 
											mostrarSelecione="true" 
											onChange="habilitarCampo();" 
											opcaoSelecionada="${bemVo.parametroConsulta.classificacaoTipoBem.valorCorrente}">
										</abaco:campoSelectDominio><font color="red">*</font>
                            </td>
                        </tr>
                        <tr> 
                            <td class="SEFAZ-TD-RotuloEntrada"><div id="rotuloDescricao"></div></td>
                            <td class="SEFAZ-TD-CampoEntrada"><div id="campoDescricao"></div></td>
                        </tr>	
                        <tr> 
                            <td colspan="4">&nbsp;</td>
                        </tr>
                        <tr> 
                            <td colspan="4" align="center"> 
                                <input name="<%=Form.BOTAO_PESQUISAR_CLONE%>" type="button" value="<%=Form.TEXTO_BOTAO_PESQUISAR%>" class="SEFAZ-INPUT-Botao" id="btnPesquisar" onClick="return validaFormulario();"></input>
										  <abaco:botaoCancelarSemMensagem></abaco:botaoCancelarSemMensagem>
										</td>
                        </tr>
								<tr> 
									<td colspan="2"><font color="red"><b>* Campos Obrigatórios</b></font></td>
								</tr>								
                    </table>
                    <table>
                        <tr>
                            <td >
                                <div align="center"> 
                                    <abaco:mensagemAguardeCarregando></abaco:mensagemAguardeCarregando>
                                </div>
                            <td>
                        </tr>
                    </table>
                    <table>
                        <tr> 
                            <td colspan="4">&nbsp;</td>
                        </tr>
                        <tr> 
                            <td colspan="4">
                                <jsp:include page="/tabelabasica/bem/ViewListarBem.jsp"></jsp:include>
                            </td>
                        </tr>
                    </table>
                </form>
            </center>
        <g:mostrarRodape></g:mostrarRodape>
    </body>
</html>