<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarCultura.jsp
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
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.cultura.CulturaVo"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
        <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
        <script type="text/javascript" language="javascript">
        
            function limpaCampos()
            {
                document.form.<%=Form.CAMPO_DESCRICAO_CULTURA%>.value = '';
                document.form.<%=Form.CAMPO_CODIGO_CULTURA%>.value = '';
            }

            function habilitarCampo()
            {
                var comboOpcao = document.form.<%=Form.CAMPO_SELECT_TIPO_PESQUISA%>.value;			
                var tipo = buscarTipoNavegador();
                                
                if(comboOpcao == '') // ------- Selecione -------
                {
                    document.getElementById('linha_codigo').style.display='none';
                    document.getElementById('linha_descricao').style.display='none'; 
                    limpaCampos();
                }
                if(comboOpcao == 1)
                {
                    document.getElementById('linha_codigo').style.display=tipo;
                    document.getElementById('linha_descricao').style.display='none'; 
                    document.form.<%=Form.CAMPO_DESCRICAO_CULTURA%>.value = '';
                }
                if(comboOpcao == 2)
                {
                    document.getElementById('linha_codigo').style.display='none';
                    document.getElementById('linha_descricao').style.display=tipo; 
                    document.form.<%=Form.CAMPO_CODIGO_CULTURA%>.value = '';
                }	
            } 			
            function validaFormulario()
            {
                //Campo SELECT 
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_PESQUISA%>,<%="'"+MensagemErro.VALIDAR_CULTURA_PARAMETRO_TIPO_SELECAO+"'"%>))
                {
                    return false;
                }
                if(document.getElementById('linha_codigo').style.display != 'none')
                {
                    if( ! verificaCamposW3c(document.form.<%=Form.CAMPO_CODIGO_CULTURA%>,<%="'"+MensagemErro.VALIDAR_CULTURA_PARAMETRO_CODIGO+"'"%>))
                    {
                        return false;
                    }
                    if(!verificaCamposW3cByValor(document.form.<%=Form.CAMPO_CODIGO_CULTURA%>,0,<%="'"+MensagemErro.VALIDAR_CULTURA_LISTAR_CODIGO+"'"%>))
                    {
                        return false;
                    }
                }
                if(document.getElementById('linha_descricao').style.display != 'none')
                {
                    if( ! verificaCamposW3c(document.form.<%=Form.CAMPO_DESCRICAO_CULTURA%>,<%="'"+MensagemErro.VALIDAR_CULTURA_PARAMETRO_DESCRICAO+"'"%>))
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
	</script>
	<jsp:include page="/util/ViewVerificaErro.jsp"/>
	<title><abaco:tituloSistema></abaco:tituloSistema></title>
    </head>
    <body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');habilitarCampo(); verificaErro();">
        <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
            <center>
                <form method="POST" action="" name="form" onsubmit="return validaFormulario();">
                    <table  class="SEFAZ-TABLE-Moldura" cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                     <tr align="right">
                            <td colspan="2"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                        </tr>
                        <tr class="SEFAZ-TR-Titulo" align="center"> 
                            <td colspan="2">Filtro para Pesquisa</td>
                        </tr>
                        <tr> 
                            <td class="SEFAZ-TD-RotuloEntrada" width="300">Tipo de Pesquisa:</td>
                            <td class="SEFAZ-TD-CampoEntrada">
                                <select name="<%=Form.CAMPO_SELECT_TIPO_PESQUISA%>" onChange="habilitarCampo()">
                                    <option value=""><%=Form.SELECIONE%></option>
                                    <option value="1" <c:if test="${(culturaVo!=null) && (param.campoSelectTipoPesquisa==1)}"><c:out value="selected"></c:out></c:if>>CÓDIGO</option>
                                    <option value="2" <c:if test="${(culturaVo!=null) && (param.campoSelectTipoPesquisa==2)}"><c:out value="selected"></c:out></c:if>>DESCRIÇÃO</option>
                                </select><font color="red">*</font>
                            </td>
                        </tr>
                        <tr id="linha_codigo" style="display: none" > 
                            <td class="SEFAZ-TD-RotuloEntrada">C&oacute;digo:&nbsp;</td>
                            <td class="SEFAZ-TD-CampoEntrada">
                                <input name="<%=Form.CAMPO_CODIGO_CULTURA%>" 
                                id="<%=Form.CAMPO_CODIGO_CULTURA%>" 
                                class="SEFAZ-INPUT-Text" type="text" size="5" maxlength="5<%=JspUtil.getCampoApenasNumero()%>" value="<c:out value='${culturaVo.parametroConsulta.codigo}'></c:out>"></input><font color="red">*</font>
                            </td>
                        </tr>
                        <tr id="linha_descricao" style="display: none"> 
                            <td class="SEFAZ-TD-RotuloEntrada">Descri&ccedil;&atilde;o:&nbsp;</td>
                            <td class="SEFAZ-TD-CampoEntrada">
                                    <input name="<%=Form.CAMPO_DESCRICAO_CULTURA%>" id="<%=Form.CAMPO_DESCRICAO_CULTURA%>" class="SEFAZ-INPUT-Text" type="text" size="50" maxlength="50" onblur="toUpperCaseW3c(this);"  value="<c:out value='${culturaVo.parametroConsulta.descricaoCultura}'></c:out>" ></input><font color="red">*</font>
                            </td>
                        </tr>
                        <tr> 
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr> 
                            <td colspan="2" align="center"> 
                                <input name="<%=Form.BOTAO_PESQUISAR_CLONE%>" type="button"   value="<%=Form.TEXTO_BOTAO_PESQUISAR%>" class="SEFAZ-INPUT-Botao" id="btnPesquisar" onClick="return validaFormulario();"/>
											<abaco:botaoCancelarSemMensagem/>
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
                    <table>
                        <tr> 
                            <td colspan="2">
                                <jsp:include page="/tabelabasica/cultura/ViewListarCultura.jsp"></jsp:include>
                            </td>
                        </tr>
                    </table>
                </form>
            </center>
        <g:mostrarRodape></g:mostrarRodape>
    </body>
</html>