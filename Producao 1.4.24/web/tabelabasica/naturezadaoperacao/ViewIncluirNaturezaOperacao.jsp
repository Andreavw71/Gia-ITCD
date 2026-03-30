<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewIncluirNaturezaOperacao.jsp
* Criação : Outubro de 2007 / Rogério Sanches de Oliveira
* Revisão :  MAR2008 - Wendell Pereira de Farias
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import =" br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.Form"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <meta HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
        <meta HTTP-EQUIV=Cache-Control content=no-store>
        <meta HTTP-EQUIV=Pragma content=no-cache>
        <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
        <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>		
        <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
        <script type="text/javascript" language="javascript">
        
            function obterArrayBotoes()
            {
                var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
                return new Array(botao1);
            }
            
            /**
            * Funcionalidade que valida o valor informado.
            * Wendell Pereira de Farias - MAR2008
            */
            function validaFormulario()
            {
                //Se o ator não selecionar nenhuma opção, o sistema apresenta a mensagem de erro ?Favor informar o tipo de GIA? e retorna ao item FP2.
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_GIA%>, <%="'"+MensagemErro.VALIDAR_NATUREZA_OPERACAO_TIPO_GIA+"'"%>))
                {
                    return false;
                }
                                                
                //Se o ator não selecionar nenhuma opção, o sistema apresenta a mensagem de erro ?Favor informar o tipo de processo? e retorna ao item FP3.
                if(document.getElementById("linhaCausaMortis").style.display != "none")
                {
                    if(!verificaCamposW3c(document.getElementById("idNaturezaCausaMortis"), <%="'"+MensagemErro.VALIDAR_NATUREZA_OPERACAO_TIPO_PROCESSO+"'"%>))
                    {
                        return false;
                    }		
                }				
                if(document.getElementById("linhaInterVivos").style.display != "none")
                {
                    if(!verificaCamposW3c(document.getElementById("idNaturezaInterVivos"), <%="'"+MensagemErro.VALIDAR_NATUREZA_OPERACAO_TIPO_PROCESSO+"'"%>))
                    {
                        return false;
                    }
						  if(document.getElementById('idNaturezaInterVivos').value == <%=DomnTipoProcesso.DOACAO%>)
						  {
								if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_ISENCAO_PREVISTA_LEI%>, '<%=MensagemErro.VALIDAR_NATUREZA_OPERCAO_FLAG_ISENCAO_PREVISTA%>'))
								{
									return false;
								}
						  }
                }
                //Se o ator não preencher o campo descrição, o sistema apresenta a mensagem de erro ?Favor informar a descrição da natureza da operação? e retornar ao item FP4.
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DESCRICAO_NATUREZA_OPERACAO%>, <%="'"+MensagemErro.VALIDAR_NATUREZA_OPERACAO_DESCRICAO+"'"%>))
                {
                    return false;
                }
                //Se o ator selecionar a opção ?SIM? na base de cálculo reduzido e não informar o percentual de base de cálculo reduzido, o sistema apresenta a seguinte mensagem ?Favor Informar percentual de base de cálculo reduzido? e retorna para item FP6. 
                    if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_BASE_CALCULO_NATUREZA_OPERACAO%>, <%="'"+MensagemErro.VALIDAR_NATUREZA_OPERACAO_BASE_CALCULO_REDUZIDO+"'"%>))
                    {
                        return false;
                    }
                //Se o ator selecionar a opção ?SIM? na base de cálculo reduzido e não informar o percentual de base de cálculo reduzido, o sistema apresenta a seguinte mensagem ?Favor Informar percentual de base de cálculo reduzido? e retorna para item FP6.           
                if(document.form.<%=Form.CAMPO_SELECT_TIPO_BASE_CALCULO_NATUREZA_OPERACAO%>.value == 1)
                {
                    if(document.form.<%=Form.CAMPO_PERCENTUAL_NATUREZA_OPERACAO%>.value == '')
                   {
                      if(!verificaCamposW3c(document.form.<%=Form.CAMPO_PERCENTUAL_NATUREZA_OPERACAO%>, <%="'"+MensagemErro.VALIDAR_NATUREZA_OPERACAO_PERCENTUAL_BASE_CALCULO+"'"%>))
                      {
                        return false;
                      }
                    }
                }
                    desabilitarBotoes(obterArrayBotoes());
                    document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=1"%>';
                    document.form.submit();
                    return true;
            }

            /**
            * Funcionalidade que habilita o valor percentual.
            * Wendell Pereira de Farias - MAR2008
            */            
            function habilitaPercentual(campo,campo_destino)
            {
              var tipo = buscarTipoNavegador();
                if(campo.value != 1)
                {
                    document.getElementById('<%=Form.CAMPO_PERCENTUAL_NATUREZA_OPERACAO%>x').style.display = "none";//o caracter x é utilizado para diferenciar a <tr> de <td>.
                    document.getElementById('<%=Form.CAMPO_PERCENTUAL_NATUREZA_OPERACAO%>').value ="0.0";
                }
                else
                {                  
                    document.getElementById('<%=Form.CAMPO_PERCENTUAL_NATUREZA_OPERACAO%>x').style.display = tipo;   //o caracter x é utilizado para diferenciar a <tr> de <td>.             
                }
            }

            /**
            * Funcionalidade que define quais  os campos serão apresentados.
            * Wendell Pereira de Farias - MAR2008
            */		  
            function atualizarNaturezaOperacao()
            {
                var tipo = buscarTipoNavegador();
                if(document.form.<%=Form.CAMPO_SELECT_TIPO_GIA%>.value == <%=DomnTipoGIA.CAUSA_MORTIS%> )
                {
                    limparCampos();
                    document.getElementById('linhaCausaMortis').style.display = tipo;
                }
                else if(document.form.<%=Form.CAMPO_SELECT_TIPO_GIA%>.value == <%=DomnTipoGIA.INTER_VIVOS%> )
                {
                    limparCampos();
                    document.getElementById('linhaInterVivos').style.display = tipo;
                }
               else
              {
                    limparCampos();
                 }
            }

            /**
            * Funcionalidade auxiliar que atualiza os campos na tela.
            * Wendell Pereira de Farias - MAR2008
            */			
            function limparCampos()
            {
                document.getElementById('linhaInterVivos').style.display = "none";
                document.getElementById('linhaCausaMortis').style.display = "none";
            }        
			
            function obterArrayBotoes()
            {
                var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
                return new Array(botao1);
            }
				
				function habilitaCamposIsencaoPrevista(valor)
				{
					var campoIsencao = document.getElementById('campoIsencaoPrevista');
					if(valor == <%=DomnTipoProcesso.DOACAO%>)
					{
						campoIsencao.style.display = '';
					}
					else
					{
						campoIsencao.value = 0;
						campoIsencao.style.display = 'none';						
					}
				}
            
            /**
            * Funcionalidade que habilita os campos no retorno da Servlet.
            * Wendell Pereira de Farias - MAR2008
            */            
            function habilitaCampoPercentualRetornoServlet()
            {
                var tipo = buscarTipoNavegador();
                if(document.form.<%=Form.CAMPO_SELECT_TIPO_BASE_CALCULO_NATUREZA_OPERACAO%>.value != 1)
                {                
                    document.getElementById('<%=Form.CAMPO_PERCENTUAL_NATUREZA_OPERACAO%>x').style.display = "none";//o caracter x é utilizado para diferenciar a <tr> de <td>.
                }
                else
                {
                    tipo = document.getElementById('<%=Form.CAMPO_PERCENTUAL_NATUREZA_OPERACAO%>x').style.display = tipo;  //o caracter x é utilizado para diferenciar a <tr> de <td>.                 
                }
                 atualizarNaturezaOperacao();
            }
            
        </script>
        <jsp:include page="/util/ViewVerificaErro.jsp"/>
        <title><abaco:tituloSistema></abaco:tituloSistema></title>
    </head>
    <body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');habilitaCampoPercentualRetornoServlet();atualizarNaturezaOperacao();verificaErro();log();">
        <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
        <center>
            <form method="POST" name="form"  action="#">
                <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                     <tr align="right">
                            <td colspan="2"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                     </tr>
                    <tr class="SEFAZ-TR-Titulo" align="center"> 
                        <td colspan="2">Dados do registro</td>
                    </tr>
                    <tr> 
                        <td class="SEFAZ-TD-RotuloEntrada" width="278">Tipo GIA:&nbsp;</td>
                        <td class="SEFAZ-TD-ComboBox" width="462">
									<abaco:campoSelectDominio 
										ajuda="" 
										classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA" 
										name="<%=Form.CAMPO_SELECT_TIPO_GIA%>" 
										tabIndex="" 
										idCampo="idTipoGia" 
										mostrarSelecione="true" 
										opcaoSelecionada="${naturezaOperacaoVo.tipoGIA.valorCorrente}" 
										onChange="atualizarNaturezaOperacao()">
									</abaco:campoSelectDominio><font color="red">*</font>
                        </td>
                    </tr>
                    <tr id="linhaCausaMortis" style="display:none"> 
                        <td class="SEFAZ-TD-RotuloEntrada" width="278">Tipo Processo:&nbsp;</td>
                        <td class="SEFAZ-TD-ComboBox" width="462">
                            <div id="idSelectTipoNaturezaCausaMortis">
										<abaco:campoSelectDominio 
											ajuda="" 
											classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoCausaMortis" 
											name="<%=Form.CAMPO_SELECT_TIPO_PROCESSO_CAUSA_MORTIS%>" 
											tabIndex="" 
											idCampo="idNaturezaCausaMortis" 
											mostrarSelecione="true" 
											opcaoSelecionada="${naturezaOperacaoVo.tipoProcesso.valorCorrente}">
										</abaco:campoSelectDominio><font color="red">*</font>
                            </div>
                        </td>
                    </tr>
                    <tr id="linhaInterVivos" style="display:none"> 
                        <td class="SEFAZ-TD-RotuloEntrada" width="278">Tipo Processo:&nbsp;</td>
                        <td class="SEFAZ-TD-ComboBox" width="462">
                            <div id="idSelectTipoNaturezaInterVivos">
										<abaco:campoSelectDominio 
											ajuda="" 
											classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoInterVivos" 
											name="<%=Form.CAMPO_SELECT_TIPO_PROCESSO_INTER_VIVOS%>" 
											tabIndex="" 
											idCampo="idNaturezaInterVivos" 
											mostrarSelecione="" 
											opcaoSelecionada="${naturezaOperacaoVo.tipoProcesso.valorCorrente}"
											onChange="habilitaCamposIsencaoPrevista(this.value);">
										</abaco:campoSelectDominio><font color="red">*</font>
                            </div>
                        </td>
                    </tr>
						  <tr id="campoIsencaoPrevista" style="display:none">
							<td class="SEFAZ-TD-RotuloEntrada" width="278">Isenção Prevista em Lei:&nbsp;</td>
							<td class="SEFAZ-TD-CampoEntrada" width="462">
								<abaco:campoSelectDominio 
									ajuda="Selecione uma opção." 
									classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" 
									name="<%=Form.CAMPO_SELECT_ISENCAO_PREVISTA_LEI%>" 
									tabIndex="" 
									idCampo="<%=Form.CAMPO_SELECT_ISENCAO_PREVISTA_LEI%>" 
									mostrarSelecione="true" 
									opcaoSelecionada="${naturezaOperacaoVo.flagIsencaoPrevistaLei.valorCorrente}">
								</abaco:campoSelectDominio><font color="red">*</font>
							</td>
						  </tr>
                    <tr> 
                        <td class="SEFAZ-TD-RotuloEntrada" width="278">Descri&ccedil;&atilde;o:&nbsp;</td>
                        <td class="SEFAZ-TD-CampoEntrada" width="462">
									<abaco:campoStringMaiuscula 
										maxlength="50" 
										name="<%=Form.CAMPO_DESCRICAO_NATUREZA_OPERACAO%>" 
										size="31" 
										value="${naturezaOperacaoVo.descricaoNaturezaOperacao}" >
									</abaco:campoStringMaiuscula><font color="red">*</font>
                        </td>
                    </tr>
                    <tr>
                        <td class="SEFAZ-TD-RotuloEntrada" width="278">Base de C&aacute;lculo Reduzido:&nbsp;</td>
                        <td class="SEFAZ-TD-ComboBox" width="462">
									<abaco:campoSelectDominio 
										ajuda="" 
										classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" 
										name="<%=Form.CAMPO_SELECT_TIPO_BASE_CALCULO_NATUREZA_OPERACAO%>" 
										tabIndex="" 
										mostrarSelecione="true" 
										idCampo="<%=Form.CAMPO_SELECT_TIPO_BASE_CALCULO_NATUREZA_OPERACAO%>" 
										opcaoSelecionada="${naturezaOperacaoVo.tipoBaseCalculo.valorCorrente}" 
										onChange="habilitaPercentual(this,'<%=Form.CAMPO_PERCENTUAL_NATUREZA_OPERACAO%>')">
									</abaco:campoSelectDominio><font color="red">*</font>
                        </td>
                    </tr>
                    <tr id="<%=Form.CAMPO_PERCENTUAL_NATUREZA_OPERACAO%>x">
                        <td class="SEFAZ-TD-RotuloEntrada" width="278">
                            Percentual Base de C&aacute;lculo:&nbsp;
                        </td>
                        <td class="SEFAZ-TD-CampoEntrada" width="462">
									<abaco:campoMonetario 
										name="<%=Form.CAMPO_PERCENTUAL_NATUREZA_OPERACAO%>" 
										quantidadeDigitosInteiros="3" 
										size="8" 
										value="${naturezaOperacaoVo.percentualBaseCalculoFormatada}" 
										idCampo="<%=Form.CAMPO_PERCENTUAL_NATUREZA_OPERACAO%>" 
										mostrarZero="false" 
										quantidadeCasasDecimais="${naturezaOperacaoVo.quantidadeCasasDecimais}">
									</abaco:campoMonetario><font color="red">*</font>
                        </td>
                    </tr>
                    <tr> 
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr> 
                        <td width="278">&nbsp;</td>
                        <td colspan="4" align="left"> 
                            <input type="button" value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR_CLONE%>" onClick="return validaFormulario();"></input>
                            <abaco:botaoCancelar></abaco:botaoCancelar>
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
    <g:mostrarRodape></g:mostrarRodape>
</body>
</html>
