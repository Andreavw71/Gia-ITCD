<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewIncluirBem.jsp
* Criação : Setembro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : Marlo Eichenberg Motta 
* Log : 08/10/2007
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.bem.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem"%>
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
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>,<%="'"+MensagemErro.VALIDAR_BEM_PARAMETRO_TIPO+"'"%>))
				{
					return false;
				}
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DESCRICAO_BEM%>,<%="'"+MensagemErro.VALIDAR_BEM_PARAMETRO_DESCRICAO+"'"%>))
				{
					return false;
				}
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_POSSUI_CONSTRUCAO%>,<%="'"+MensagemErro.FLAG_POSSUI_CONTRUCAO_DEVE_SER_INFORMADO+"'"%>))
				{
					return false;
				}
            if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_VERIFICACAO%>,<%="'"+MensagemErro.VALIDAR_BEM_PARAMETRO_TIPO_VERIFICACAO+"'"%>))
				{
					return false;
				}
            if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_VERIFICACAO%>,<%="'"+MensagemErro.VALIDAR_BEM_PARAMETRO_TIPO_PROTOCOLO+"'"%>))
				{
					return false;
				}
				desabilitarBotoes(obterArrayBotoes());
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=1"%>';
				document.form.submit();
				
				return true;
			}

				function PegaBrowser()
				{
						var browser = navigator.appName;
						if(browser == 'Microsoft Internet Explorer')
						{
								return 'inline';
						}
						else
						{
								return 'table-row';
						}
				}			
			
			function obterArrayBotoes()
			{
				var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
				return new Array(botao1);
			}
			
         /*
         * Esta funcao tem por objetivo remover algumas opcoes
         * do combobox Tipo Verificacao de acordo com o tipo de bem escolhido.
         *
         */
         function selecionarTipoVerificaoPorTipoDeBem(){
            var campoTipoBem = document.getElementById('<%=Form.CAMPO_SELECT_TIPO_BEM%>');
            var campoTipoVerificacao = document.getElementById('<%=Form.CAMPO_SELECT_TIPO_VERIFICACAO%>');

            var tipoBemSelecionado = campoTipoBem[campoTipoBem.selectedIndex].value;
            
            // Esconde todos os campos do combobox.
            for (i = 1; i < 6; i++) {
               campoTipoVerificacao[i].style.display="none";
            }

            // Opcoes a serem exibidas de acordo com opcao selecionada
            if( tipoBemSelecionado == <%=DomnTipoBem.IMOVEL_URBANO%>){
                  // Exibir Opcoes
                  campoTipoVerificacao[1].style.display="block";
                  campoTipoVerificacao[3].style.display="block";
            }
            if( tipoBemSelecionado == <%=DomnTipoBem.IMOVEL_RURAL%>){
                   // Exibir Opcoes
                  campoTipoVerificacao[1].style.display="block";
                  campoTipoVerificacao[2].style.display="block";
            }
            if( tipoBemSelecionado == <%=DomnTipoBem.OUTROS_BENS%>){
                   // Exibir Opcoes
                  campoTipoVerificacao[1].style.display="block";
                  campoTipoVerificacao[4].style.display="block";
                  campoTipoVerificacao[5].style.display="block";
            }            
         }
         
         
			/*
			* Função criada para habilitar os campos na JSP de pesquisa, de acordo com a opção selecionada pelo usuário.
			*/	
			var descricao = "<c:out value="${bemVo.descricaoTipoBem}"/>";
			function habilitarCampo()
			{
					comboOpcao = document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>.value;
					if(comboOpcao == "")
					{					
						document.getElementById('rotuloDescricao').innerHTML = '';
						document.getElementById('campoDescricao').innerHTML = '';
						document.getElementById('flagConstrucao').style.display='none';
					}
					else
					{
						document.getElementById('rotuloDescricao').innerHTML = 'Descrição:&nbsp;';
						document.getElementById('campoDescricao').innerHTML = '<input name="<%=Form.CAMPO_DESCRICAO_BEM%>" type="text"  class="SEFAZ-INPUT-Text" size="50" maxlength="50" onblur="toUpperCaseW3c(this);"  value="'+descricao+'" ><font color="red">*</font>';
						document.getElementById('flagConstrucao').style.display= PegaBrowser();
						document.getElementById('campoSelectPossuiConstrucao').selectedIndex = 0;
					}
					descricao = "";
               selecionarTipoVerificaoPorTipoDeBem();
			}
		</script>
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<title><abaco:tituloSistema></abaco:tituloSistema></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');habilitarCampo();  verificaErro(); log();">
            <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
            <center>
                <form method="POST" name="form"  action="<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>">
                        <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                            <tr align="right">
                                <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                            </tr>
                            <tr class="SEFAZ-TR-Titulo" align="center">
                                    <td colspan="4">Dados do registro</td>
                            </tr>
                            <tr> 
                                <td class="SEFAZ-TD-RotuloEntrada" width="278">Tipo de Bem:&nbsp;</td>
                                <td class="SEFAZ-TD-CampoEntrada" width="462">
												<abaco:campoSelectDominio 
													ajuda="" 
													classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem" 
													name="<%=Form.CAMPO_SELECT_TIPO_BEM%>" 
													tabIndex="" 
													onChange="habilitarCampo()" 
													mostrarSelecione="true"
                                       idCampo="<%=Form.CAMPO_SELECT_TIPO_BEM%>"
													opcaoSelecionada="${bemVo.classificacaoTipoBem.valorCorrente}">
												</abaco:campoSelectDominio><font color="red">*</font>
                                </td>
                            </tr>
                            <tr> 
                                        <td class="SEFAZ-TD-RotuloEntrada" width="278"><div id="rotuloDescricao"></div></td>
                                        <td class="SEFAZ-TD-CampoEntrada" width="462"><div id="campoDescricao"></div></td>
                            </tr>
                            <tr id="flagConstrucao" style="display.none">
														<td class="SEFAZ-TD-RotuloEntrada" width="278">Possui Construção / Edificação:&nbsp; </td>
														<td class="SEFAZ-TD-ComboBox" width="462">
																<abaco:campoSelectDominio ajuda="Selecione se o bem possui ou não construção." classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" name="<%=Form.CAMPO_POSSUI_CONSTRUCAO%>" tabIndex="" opcaoSelecionada="${bemVo.possuiConstrucao.valorCorrente}" valorDefault="0" mostrarSelecione="true" idCampo="campoSelectPossuiConstrucao"/>
																<font color="red">*</font>
														</td>
                            </tr>
                            <tr>
														<td class="SEFAZ-TD-RotuloEntrada" width="278">Tipo Verificação :&nbsp; </td>
														<td class="SEFAZ-TD-ComboBox" width="462">
																<abaco:campoSelectDominio ajuda="Selecione se o bem possui ou não construção." classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoVerificacao" name="<%=Form.CAMPO_SELECT_TIPO_VERIFICACAO%>" tabIndex="" opcaoSelecionada="${bemVo.tipoVerificacao.valorCorrente}" valorDefault="0" mostrarSelecione="true" idCampo="<%=Form.CAMPO_SELECT_TIPO_VERIFICACAO%>"/>
																<font color="red">*</font>
														</td>
                            </tr>
                            <tr>
														<td class="SEFAZ-TD-RotuloEntrada" width="278">Tipo Protocolo :&nbsp; </td>
														<td class="SEFAZ-TD-ComboBox" width="462">
																<abaco:campoSelectDominio ajuda="Selecione se o bem possui ou não construção." classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo" name="<%=Form.CAMPO_SELECT_TIPO_PROTOCOLO%>" tabIndex="" opcaoSelecionada="${bemVo.tipoProtocoloGIA.valorCorrente}" valorDefault="0" mostrarSelecione="true" idCampo="<%=Form.CAMPO_SELECT_TIPO_PROTOCOLO%>"/>
																<font color="red">*</font>
														</td>
                            </tr>
                                <tr> 
                                        <td colspan="4">&nbsp;</td>
                                </tr>
                                <tr> 
                                    <td colspan="4" align="center"> 
                                            <input type="button" value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" class="SEFAZ-INPUT-Botao"  name="<%=Form.BOTAO_CONFIRMAR_CLONE%>"  onClick="return validaFormulario();"/>
														  <abaco:botaoCancelar/>
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
                    </center>
                <g:mostrarRodape/>
	</body>
</html>
