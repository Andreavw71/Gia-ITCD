<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewIncluirParametroLegislacao.jsp
* Criação : Outubro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : Março de 2008 / Wendell Farias
* Log : 
*/
--%>
<%@ page contentType="text/html;charset=iso-8859-1"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.Form"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<%
    pageContext.setAttribute("SIM", new Integer(DomnSimNao.SIM));
    pageContext.setAttribute("NAO", new Integer(DomnSimNao.NAO));
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
		  
            function validaFormulario()
            {					
                //Verifica Número da Legislação.
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_NUMERO_LEGISLACAO%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_NUMERO_ANO+"'"%>))
                {
                    return false;
                }
                
                //Verifica Ano da Legislação.
                 if(!verificaCamposW3c(document.form.<%=Form.CAMPO_ANO_LEGISLACAO%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_NUMERO_ANO+"'"%>))
                {
                    return false;
                }

                //Verifica Data da Virgencia.
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_VIGENCIA_INICIAL%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DATA_VIGENCIA_INICIAL+"'"%>))
                {
                        return false;
                }
                desabilitarBotoes(obterArrayBotoes());
                document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=1"%>';
                document.form.submit();
            }
	
            //Valida botoes
            function obterArrayBotoes()
            {
                var botao = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
                return new Array(botao);
            }
				
            function validarNumeroLegislacao()
            {
		//Valida numero legislacao
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_NUMERO_LEGISLACAO%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_NUMERO_ANO+"'"%>))
                {
                    return false;
                }					
		
                //Valida ano legislacao
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_ANO_LEGISLACAO%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_NUMERO_ANO+"'"%>))
                {
                    return false;
                }
                
                //Valida ano legislacao
                if(document.form.<%=Form.CAMPO_ANO_LEGISLACAO%>.value.length < 4)
                {
                    alert(<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_ANO_CONTER_QUATRO_DIGITOS+"'"%>);
                    document.form.<%=Form.CAMPO_ANO_LEGISLACAO%>.value="";
                    document.form.<%=Form.CAMPO_ANO_LEGISLACAO%>.focus();
                }
                else
                {
                    desabilitarBotoes(obterArrayBotoes());
                    document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_VALIDAR_NUMERO_LEGISLACAO+"=1"%>';
                    document.form.submit();
                }					  
            }
			
            function verificaDatas()
            {
                dataInicial = document.form.<%=Form.CAMPO_DATA_VIGENCIA_INICIAL%>.value;
                dataFinal = document.form.<%=Form.CAMPO_DATA_VIGENCIA_FINAL%>.value;
                if((trimW3c(dataInicial) != "") && (trimW3c(dataFinal) != ""))
                {
                    if(!comparaDatasPeriodoMenorW3c(dataInicial, dataFinal, <%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DATA_VIGENCIA_FINAL_MAIOR_INICIAL+"'"%>))
                    {
                        document.form.<%=Form.CAMPO_DATA_VIGENCIA_FINAL%>.focus();
                    }
                }					 
            }
				
            function verificaDataVigenciaFinal()
            {
                if((trimW3c(dataFinal) == ""))
                 {
                        desabilitarBotoes(obterArrayBotoes());
                        document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CONSULTAR_DATA_FINAL_ULTIMA_LEI+"=1"%>';
                        document.form.submit();
                 }
            }
			
            function adicionaAliquota()
            {
                //Valida Fator Gerador
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_FATO_GERADOR%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_FATO_GERADOR+"'"%>))
                {
                    return false;
                }

                if(!document.form.<%=Form.CAMPO_CHECK_ISENCAO%>.checked)
                {
                         if(!verificaCamposW3c(document.form.<%=Form.CAMPO_PERCENTUAL%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_ALIQUOTA+"'"%>))
                        {
                            return false;
                        }			
                        if(!verificaCamposW3cByValor(document.form.<%=Form.CAMPO_PERCENTUAL%>,0,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_ALIQUOTA_INVALIDO+"'"%>))
                        {
                            return false;
                        }
                        if(!verificaPercentualAliquota(document.form.<%=Form.CAMPO_PERCENTUAL%>))
                        {
                            return false;
                        }
                  
                }
                else
                {
                    document.form.<%=Form.CAMPO_PERCENTUAL%>.value = 0;
                }

                //Validar a UPF inicial
                if(!verificaCamposW3cByValor(document.form.<%=Form.CAMPO_QUANTIDADE_UPF_INICIAL%>,0,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_UPF_INICIAL+"'"%>))
                {
                     document.form.<%=Form.CAMPO_QUANTIDADE_UPF_INICIAL%>.focus();
                    return false;
                }
                
                //Validar a UPF inicial é menor que zero.
                if(!verificaCamposW3cByValor(document.form.<%=Form.CAMPO_QUANTIDADE_UPF_INICIAL%>,0,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_UPF_INICIAL_MENOR_ZERO+"'"%>))
                {
                     document.form.<%=Form.CAMPO_QUANTIDADE_UPF_INICIAL%>.focus();
                    return false;
                }
                
                //Validar a UPF final é maior que a UPF inicial.
                if(document.form.<%=Form.CAMPO_QUANTIDADE_UPF_FINAL%>.value!="")
                {
                    if(!(Math.abs(document.form.<%=Form.CAMPO_QUANTIDADE_UPF_FINAL%>.value)>= Math.abs(document.form.<%=Form.CAMPO_QUANTIDADE_UPF_INICIAL%>.value)))
                    {
                        alert(<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_UPF_FINAL_MAIOR_UPF_INICIAL+"'"%>);
                        document.form.<%=Form.CAMPO_QUANTIDADE_UPF_FINAL%>.focus();
                        return false;
                     }
                }

                desabilitarBotoes(obterArrayBotoes());
                document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ADICIONAR_ALIQUOTA+"=1"%>';
                document.form.submit();
                
                return true;			
            }
			
            function adicionaMulta()
            {
               //Valida quantidade de dias inicial
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_QUANTIDADE_DIAS_INICIAL%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DIAS_INICIAL+"'"%>))
                {
                    document.form.<%=Form.CAMPO_QUANTIDADE_DIAS_INICIAL%>.focus();
                    return false;
                }
                
                //Valida quantidade de dias inicial menor que zero.
                if(!verificaCamposW3cByValor(document.form.<%=Form.CAMPO_QUANTIDADE_DIAS_INICIAL%>,0,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DIAS_INICIAL_MENOR_ZERO+"'"%>))
                {
                    document.form.<%=Form.CAMPO_QUANTIDADE_DIAS_INICIAL%>.focus();
                    return false;
                }   
                
                if (document.form.<%=Form.CAMPO_QUANTIDADE_DIAS_FINAL%>.value!="")
                {
                      //Valida quantidade de dias final menor que zero.
                    if(!verificaCamposW3cByValor(document.form.<%=Form.CAMPO_QUANTIDADE_DIAS_FINAL%>,0,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DIAS_FINAL_MENOR_ZERO+"'"%>))
                    {
                        document.form.<%=Form.CAMPO_QUANTIDADE_DIAS_FINAL%>.focus();
                        return false;
                    }   
                    
                    //Validar a Quantidade dias  final é maior que a Quantidade dias inicial.
                    if((Math.abs(document.form.<%=Form.CAMPO_QUANTIDADE_DIAS_FINAL%>.value)<=Math.abs(document.form.<%=Form.CAMPO_QUANTIDADE_DIAS_INICIAL%>.value)))
                    {
                        alert(<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DIAS_FINAL_MAIOR_DIAS_INICIAL+"'"%>);
                        document.form.<%=Form.CAMPO_QUANTIDADE_DIAS_FINAL%>.focus();
                        return false;
                     }
                }                
                //Valida o percentual da multa.
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_PERCENTUAL_MULTA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_MULTA+"'"%>))
                {
                    return false;
                }
                                
                if(!verificaCamposW3cByValor(document.form.<%=Form.CAMPO_PERCENTUAL_MULTA%>,0,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_MULTA_INVALIDO+"'"%>))
                {
                    return false;
                }

                desabilitarBotoes(obterArrayBotoes());
                document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ADICIONAR_MULTA+"=1"%>';
                document.form.submit();
            }
					
            function habilitaPercentual()
            {

                campoIsencao = document.form.<%=Form.CAMPO_CHECK_ISENCAO%>;
                campoPercentual = "<%=Form.CAMPO_PERCENTUAL%>";

                if(campoIsencao.checked == true)
                {
                    document.getElementById(campoPercentual).disabled = true;
                    document.getElementById(campoPercentual).value = 0;
                }
                else
                {
                    document.getElementById(campoPercentual).disabled = false;
                    document.getElementById(campoPercentual).value = '';                    
                }
            }
            
            /*
            Função responsável por comparar se um valor está entre o intervalo de 0 a 100
            autor: Maxwell
            @return: true ou false
            */
            function comparaValorPercentual(campoPercentual)
            {				
					var campoMonetario1 = campoPercentual.value ; 			
					var resultado = 0;		
					var campoMonetario1Float = 0;
					if(campoMonetario1 == "")
					{
						campoMonetario1 = "0,00";
					}
                
					if(campoMonetario1.length > 2)
					{
					  for(var i=0; i<campoMonetario1.length; i++)
					  {
							if(campoMonetario1.charAt(i)==".")
							{
								 campoMonetario1 = campoMonetario1.replace(".","");				
							}			
					  }
					  campoMonetario1Float = campoMonetario1.replace(",",".");	
					}
					else
					{
					  campoMonetario1Float = 0;
					}
                
					resultado = parseFloat(campoMonetario1Float);
					
					if(resultado > 0 & resultado <= 100)
					  return true;
					else
					  return false;
            }
            
            //Valida percentual da aliquota.		
            function verificaPercentualAliquota(campoPercentual)
            {
                if(!comparaValorPercentual(campoPercentual))
                {
                    alert('<%=MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_ALIQUOTA_INVALIDO%>');
                    return false;
                }	
                return true;
            }
            
            //Valida percentual da Multa
            function verificaPercentualMulta(campoPercentual)
            {
                if(!comparaValorPercentual(campoPercentual))
                {
                    alert('<%=MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_MULTA_INVALIDO%>');
                    return false;
                }	
                return true;
            }

            function excluirAliquota(indiceAliquota)
            {
                if(confirm('<%=MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_EXCLUIR_ALIQUOTA%>'))
                {
                    desabilitarBotoes(obterArrayBotoes());
                    document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_EXCLUIR_ALIQUOTA+"="%>'+indiceAliquota;
                    document.form.submit();
                    return true;
                }
                else
                {
                    return false;
                }
            }

            function excluirMulta(indiceMulta)
            {
                if(confirm('<%=MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_EXCLUIR_MULTA%>'))
                {
                    desabilitarBotoes(obterArrayBotoes());
                    document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_EXCLUIR_MULTA+"="%>'+indiceMulta;
                    document.form.submit();
                }
                else
                {
                    return false;
                }
            }
				
        </script>
        <jsp:include page="/util/ViewVerificaErro.jsp"/>
        <title><abaco:tituloSistema></abaco:tituloSistema></title>
    </head>
    <body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>'); habilitaPercentual();verificaErro();log();">
        <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
            <center>
                <form method="POST" action="" name="form">
                    <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">                    
                     <tr align="right">
                            <td colspan="2"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                        </tr>
                        <tr class="SEFAZ-TR-Titulo">
                            <td colspan="2">Legislação</td>
                        </tr>
                        <tr> 
                            <td class="SEFAZ-TD-RotuloEntrada" width="354">Número e ano da Legislação:&nbsp;</td>
                            <td class="SEFAZ-TD-CampoEntrada" width="386">
										<abaco:campoApenasNumero 	
													maxlength="8" 
													name="<%=Form.CAMPO_NUMERO_LEGISLACAO%>" 
													size="8" 
													idCampo="numLegislacao"	 
													value="${parametroLegislacaoVo.numeroLegislacaoFormatado}"/>
													 /
										<abaco:campoApenasNumero 	
													maxlength="4" 
													name="<%=Form.CAMPO_ANO_LEGISLACAO%>" 
													size="4" 
													idCampo="anoLegislacao"	 
													value="${parametroLegislacaoVo.anoLegislacaoFormatado}" 
													onBlur="validarNumeroLegislacao();"/><font color="red">*</font>
									</td>
                        </tr>
                        <tr>
                            <td class="SEFAZ-TD-RotuloEntrada" width="354">Data de Vigência Inicial:&nbsp;</td>
                            <td class="SEFAZ-TD-CampoEntrada" width="386">
                                <input	type="text" 
                                                name="<%=Form.CAMPO_DATA_VIGENCIA_INICIAL%>" 
                                                class="SEFAZ-INPUT-Text" 
                                                id="<%=Form.CAMPO_DATA_VIGENCIA_INICIAL%>" 
                                                value="<c:out value="${parametroLegislacaoVo.dataInicioVigenciaFormatada}"/>" 
                                                size="10" 
                                                maxlength="10" 
                                                onkeypress="return formataNumeroW3c(form.data,event)" 
                                                onblur="formataDataW3c(this); verificaDatas();"><font color="red">*</font>
                            </td>
                        </tr>
                        <tr> 
                            <td class="SEFAZ-TD-RotuloEntrada" width="354">Data de Vigência Final:&nbsp;</td>					
                            <td class="SEFAZ-TD-CampoEntrada" width="386">
                                <input 	type="text" 
                                                            name="<%=Form.CAMPO_DATA_VIGENCIA_FINAL%>" 
                                                            class="SEFAZ-INPUT-Text" id="<%=Form.CAMPO_DATA_VIGENCIA_FINAL%>" 
                                                            value="<c:out value="${parametroLegislacaoVo.dataFimVigenciaFormatada}"/>" 
                                                            size="10" 
                                                            maxlength="10" 
                                                            onkeypress="return formataNumeroW3c(form.data,event)"
                                                            onblur="formataDataW3c(this); verificaDatas();">
                            </td>
                        </tr>
								<tr> 
                            <td class="SEFAZ-TD-RotuloEntrada" width="354">Número e ano da Legislação Principal:&nbsp;</td>
                            <td class="SEFAZ-TD-CampoEntrada" width="386">
											<abaco:campoApenasNumero 	
													maxlength="8" 
													name="<%=Form.CAMPO_NUMERO_LEGISLACAO_PRINCIPAL%>" 
													size="8" 
													idCampo="numLegislacaoPrincipal"	 
													value="${parametroLegislacaoVo.numeroLegislacaoPrincipalFormatado}"/>
													 
													 
													 /
											<abaco:campoApenasNumero 	
													maxlength="4" 
													name="<%=Form.CAMPO_ANO_LEGISLACAO_PRINCIPAL%>" 
													size="4" 
													idCampo="anoLegislacaoPrincipal"	 
													value="${parametroLegislacaoVo.anoLegislacaoPrincipalFormatado}"/><font color="red">*</font>
									</td>
                        </tr>
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="353">Cálculo em Cascata:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada" width="387">
										<abaco:campoSelectDominio 
											ajuda="" 
											classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" 
											name="<%=Form.CAMPO_SELECT_CALCULO_CASCATA%>" 
											tabIndex="" 
											mostrarSelecione="true" 
											opcaoSelecionada="${parametroLegislacaoVo.calculoCascata.valorCorrente}">
										</abaco:campoSelectDominio>
									 </td>
								</tr>
                        <tr class="SEFAZ-TR-Titulo">
                            <td colspan="2">Alíquota</td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <table align="center" border="0" cellpadding="0" cellspacing="1" width="740">
                                    <tr>
                                        <td class="SEFAZ-TD-RotuloEntrada" width="353">Tipo de Fato Gerador:&nbsp;</td>
                                        <td class="SEFAZ-TD-CampoEntrada" width="387">
														<abaco:campoSelectDominio 
															ajuda="" 
															classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA" 
															name="<%=Form.CAMPO_SELECT_TIPO_FATO_GERADOR%>" 
															tabIndex="" 
															mostrarSelecione="true" 
															opcaoSelecionada="${aliquotaVo.tipoFatoGerador.valorCorrente}"
															idCampo="<%=Form.CAMPO_SELECT_TIPO_FATO_GERADOR%>">
														</abaco:campoSelectDominio><font color="red">*</font>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="SEFAZ-TD-RotuloEntrada" width="353">Isenção:&nbsp;</td>
                                        <td class="SEFAZ-TD-CampoEntrada" width="387">
                                            <input  type="checkbox" 
                                                                        name="<%=Form.CAMPO_CHECK_ISENCAO%>" 
                                                                        id="<%=Form.CAMPO_CHECK_ISENCAO%>"
                                                                        value="1"
                                                                        <c:if  test="${aliquotaVo.tipoIsencao.valorCorrente == SIM}">checked</c:if> 
                                                                        onclick="habilitaPercentual();">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="SEFAZ-TD-RotuloEntrada" width="353">Percentual:&nbsp;</td>
                                        <td class="SEFAZ-TD-CampoEntrada" width="387">
                                                <input  type="text" 
                                                                        name="<%=Form.CAMPO_PERCENTUAL%>" 
                                                                        class="SEFAZ-INPUT-Text" 
                                                                        id="<%=Form.CAMPO_PERCENTUAL%>" 
                                                                        value="<c:out value="${aliquotaVo.percentualLegislacaoAliquotaFormatado}"></c:out>" 
                                                                        size="8" maxlength="6" 
                                                                        onkeydown="mascaraValorMoedaW3c('form',this.name,2,15, true, event);"
                                                                        onkeyup="mascaraValorMoedaW3c('form',this.name,2,15, true, event); "
                                                                        onblur="mascaraValorMoedaW3c('form',this.name,2,15, true, event);verificaPercentualAliquota(this); ">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="SEFAZ-TD-RotuloEntrada" width="353">Quantidade UPF Inicial:&nbsp;</td>
                                        <td class="SEFAZ-TD-CampoEntrada" width="387"><input type="text" name="<%=Form.CAMPO_QUANTIDADE_UPF_INICIAL%>" class="SEFAZ-INPUT-Text"  value="<c:out value="${aliquotaVo.quantidadeUPFInicialFormatada}"></c:out>" size="10" maxlength="10" <%=JspUtil.getCampoApenasNumero()%>/><font color="red">*</font></td>
                                    </tr>
                                    <tr>
                                        <td class="SEFAZ-TD-RotuloEntrada" width="353">Quantidade UPF Final:&nbsp;</td>
                                        <td class="SEFAZ-TD-CampoEntrada" width="387">
                                                <input 	type="text" 
                                                                                        name="<%=Form.CAMPO_QUANTIDADE_UPF_FINAL%>" 
                                                                                        class="SEFAZ-INPUT-Text" 
                                                                                        value="<c:out value="${aliquotaVo.quantidadeUPFFinalFormatada}"></c:out>"  
                                                                                        size="10" 
                                                                                        maxlength="10" <%=JspUtil.getCampoApenasNumero()%>/>
                                                <input 	type="button" 
                                                                                        name="<%=Form.BOTAO_ADICIONAR_ALIQUOTA%>" 
                                                                                        class="SEFAZ-INPUT-Botao" 
                                                                                        value="<%=Form.TEXTO_BOTAO_ADICIONAR%>" 
                                                                                        onclick="adicionaAliquota();"/>
                                        </td>
                                    </tr>
                                </table></td>							
                                    <c:if test="${not empty parametroLegislacaoVo.aliquotaVo.collVO}">
												<tr><td colspan="5">
                                        <table align="center" border="0" cellpadding="0" cellspacing="1" width="740">
                                            <tr class="SEFAZ-TR-SubTitulo">
                                                <td width="262">Tipo de fato gerador</td>
                                                <td align="center" width="186">Percentual da Alíquota</td>
                                                <td align="center" width="108">Quantidade UPF Inicial</td>
                                                <td align="center">Quantidade UPF Final</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <c:forEach var="aliquotaVo" items="${parametroLegislacaoVo.aliquotaVo.collVO}" varStatus="contador">
                                                <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
                                                <c:if test="${contador.count % 2 != 0}">
                                                    <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
                                                </c:if>
                                                <tr class="<c:out value="${linhaEstilo}"></c:out>">
                                                    <td ><c:out value="${aliquotaVo.tipoFatoGerador.textoCorrente}"></c:out></td>
                                                    <td ><c:out value="${aliquotaVo.percentualLegislacaoAliquotaFormatado}"></c:out></td>
                                                    <td ><c:out value="${aliquotaVo.quantidadeUPFInicial}"></c:out></td>
                                                    <td ><c:if test="${aliquotaVo.quantidadeUPFFinal !=0}"><c:out value="${aliquotaVo.quantidadeUPFFinal}"></c:out></c:if></td>
                                                    <td ><a href="javascript:void(excluirAliquota(<c:out value="${contador.index}"></c:out>));">Excluir</a></td>
                                                </tr>
                                            </c:forEach>								
                                        </table>
												</td></tr>
												</c:if>
												</tr>
												</table>
												<table align="center" border="0" cellpadding="0" cellspacing="1" width="740" Id="multa">
													<tr class="SEFAZ-TR-Titulo">
														 <td colspan="2">Multa</td>
													</tr>
													<tr>
												
																		 <td class="SEFAZ-TD-RotuloEntrada" width="353">Quantidade de Dias Inicial:&nbsp;</td>
																		 <td class="SEFAZ-TD-CampoEntrada" width="376">
																			  <input 	type="text" 
																									name="<%=Form.CAMPO_QUANTIDADE_DIAS_INICIAL%>" 
																									class="SEFAZ-INPUT-Text"
																									value="<c:out value="${multaVo.quantidadeDiasInicialFormatada}"></c:out>"
																									size="5" 
																									maxlength="5" <%=JspUtil.getCampoApenasNumero()%>><font color="red">*</font>
																		 </td>
																	</tr>
																	<tr>
																		 <td class="SEFAZ-TD-RotuloEntrada" width="353">Quantidade de Dias Final:&nbsp;</td>
																		 <td class="SEFAZ-TD-CampoEntrada" width="376">
																			  <input 	type="text" 
																									name="<%=Form.CAMPO_QUANTIDADE_DIAS_FINAL%>" 
																									class="SEFAZ-INPUT-Text" 
																									value="<c:out value="${multaVo.quantidadeDiasFinalFormatada}"></c:out>" 
																									size="5" 
																									maxlength="5" <%=JspUtil.getCampoApenasNumero()%>>
																		 </td>
																	</tr>
																		 <tr>
																			  <td class="SEFAZ-TD-RotuloEntrada" width="353">Percentual da Multa:&nbsp;</td>
																			  <td class="SEFAZ-TD-CampoEntrada" width="376">
																					<input 	type="text" 
																																		 name="<%=Form.CAMPO_PERCENTUAL_MULTA%>" 
																																		 class="SEFAZ-INPUT-Text"  
																																		 value="<c:out value="${multaVo.percentualMultaFormatado}"></c:out>"
																																		 size="8" maxlength="6"
																																		 onkeydown="mascaraValorMoedaW3c('form',this.name,2,15, true, event);"
																																		 onkeyup="mascaraValorMoedaW3c('form',this.name,2,15, true, event);"
																																		 onblur="mascaraValorMoedaW3c('form',this.name,2,15, true, event);"/>&nbsp;
																					<input 	type="button" 
																																		 name="<%=Form.BOTAO_ADICIONAR_MULTA%>" 
																																		 class="SEFAZ-INPUT-Botao" 
																																		 value=" <%=Form.TEXTO_BOTAO_ADICIONAR%>" 
																																		 onclick="adicionaMulta();">
																			  </td>
																		 </tr>
                                                </table>
                                                    <c:if test="${not empty parametroLegislacaoVo.multaVo.collVO}">
                                                        <table align="center" border="0" cellpadding="0" cellspacing="1" width="740">
                                                                <tr class="SEFAZ-TR-SubTitulo">
                                                                    <td align="center" width="213">Quantidade de Dias Inicial </td>
                                                                    <td align="center" width="205">Quantidade de Dias Final </td>
                                                                    <td align="center">Percentual da Multa </td>
                                                                    <td>&nbsp;</td>
                                                                </tr>
                                                                <c:forEach var="multaVo" items="${parametroLegislacaoVo.multaVo.collVO}" varStatus="contador">
                                                                    <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
                                                                        <c:if test="${contador.count % 2 != 0}">
                                                                            <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
                                                                        </c:if>
                                                                        <tr class="<c:out value="${linhaEstilo}"/>">
                                                                            <td><c:out value="${multaVo.quantidadeDiasInicial}"></c:out></td>
                                                                            <td><c:if test="${multaVo.quantidadeDiasFinal != 0}"><c:out value="${multaVo.quantidadeDiasFinal}"></c:out></c:if></td>
                                                                            <td><c:out value="${multaVo.percentualMultaFormatado}"></c:out></td>
                                                                            <td><a href="javascript:void(excluirMulta(<c:out value="${contador.index}"></c:out>));">Excluir</a></td>
                                                                        </tr>
                                                                </c:forEach>
                                    </table>
												</c:if>
							<table align="center">
								<tr> 
									 <td colspan="4">&nbsp;</td>
								</tr>
								<tr> 
									 <td colspan="4" align="center"> 
										  <input type="button" value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR_CLONE%>" onClick="return validaFormulario();"/>
										  <abaco:botaoCancelar/>
									 </td>
								</tr>
							</table>
							<table width="740" border="0">
								<tr> 
									<td><font color="red"><b>* Campos Obrigatórios</b></font></td>
								</tr>						  
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
