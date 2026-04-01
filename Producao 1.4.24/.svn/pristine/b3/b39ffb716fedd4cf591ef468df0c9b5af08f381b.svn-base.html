<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoDistancia"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoAtividade"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnCriterioBaseCalculo"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<%
    pageContext.setAttribute("PERIMETRO_URBANO", new Integer(DomnTipoDistancia.PERIMETRO_URBANO));
    pageContext.setAttribute("RODOVIA_PAVIMENTADA", new Integer(DomnTipoDistancia.RODOVIA_PAVIMENTADA));
%>
<html>
  <head>
       <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
        <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
        <script type="text/javascript" language="javascript">           
            
            function getValorElementById(idCampo){
              return  document.getElementById(idCampo).value;
            }
            
            function valorAMenorQueB(campoA, campoB , mensagemErro){
               var valorInicial = getValorElementById(campoA);
               var valorFinal = getValorElementById(campoB);
               var valorFinalString  = String(valorInicial);
               if( valorFinalString != ""){
                     if( parseInt(valorFinal) <= parseInt( valorInicial) ){
                           alert( mensagemErro );
                           document.getElementById(campoB).focus();
                           return false;
                        }
                }
                return true;
            }
            
            function obterArrayBotoes()
            {
                var botao1 = document.form.<%=Form.BOTAO_ADICIONAR%>;
                return new Array(botao1);
            }
            
            function adicionarBaseCalculoImovelRural()
            {
               if(validaFormulario())
               {
                  //desabilitarBotoes(obterArrayBotoes());
                  document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ADICIONAR_BASE_CALCULO_IMOVEL_RURAL+"=1"%>';
                  document.form.submit();
               }
            }
            
            function solicitarBaseCalculoImovelRural(codigoBaseCalculo)
            {
                  //desabilitarBotoes(obterArrayBotoes());
                  document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_SOLICITAR_ALTERAR_BASE_CALCULO_IMOVEL_RURAL+"="%>'+codigoBaseCalculo;
                  document.form.submit();
            }
            
            function voltar()
            {
                  //desabilitarBotoes(obterArrayBotoes());
                  document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_VOLTAR+"=1"%>';
                  document.form.submit();
            }
            
            function validaFormulario(){

               if(verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_DISTANCIA%>,<%="'"+MensagemErro.VALIDAR_SELECIONAR_TIPO_DISTANCIA+"'"%>)){            
               
               }else{
                   return false;
               }

               if(verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_ATIVIDADE%>,<%="'"+MensagemErro.VALIDAR_SELECIONAR_TIPO_ATIVIDADE+"'"%>)){
                 
               }else{
                   return false;
               }

               if(verificaCamposW3c(document.form.<%=Form.CAMPO_QUANTIDADE_DISTANCIA_INICIAL%>,<%="'"+MensagemErro.VALIDAR_INFORMAR_DISTANCIA_INICIAL+"'"%>)){
                     var distanciaInicial = getValorElementById('<%=Form.CAMPO_QUANTIDADE_DISTANCIA_INICIAL%>');
                     if(distanciaInicial < 0){
                        alert(<%="'"+MensagemErro.VALIDAR_DISTANCIA_INICIAL_MAIOR_QUE_ZERO+"'"%>);
                        document.getElementById('<%=Form.CAMPO_QUANTIDADE_DISTANCIA_INICIAL%>').focus();
                        return false;
                     }
                     if(!valorAMenorQueB('<%=Form.CAMPO_QUANTIDADE_DISTANCIA_INICIAL%>', '<%=Form.CAMPO_QUANTIDADE_DISTANCIA_FINAL%>' , "Distância Final deve ser maior que a Distância Inicial")){
                        return false;
                     }
               }else{
                   return false;
               }
               
               if(verificaCamposW3c(document.form.<%=Form.CAMPO_PERCENTUAL_ATIVIDADE_INICAL%>,<%="'"+MensagemErro.VALIDAR_INFORMAR_ATIVIDADE_INICIAL+"'"%>)){   
                     if(!valorAMenorQueB('<%=Form.CAMPO_PERCENTUAL_ATIVIDADE_INICAL%>', '<%=Form.CAMPO_PERCENTUAL_ATIVIDADE_FINAL%>' , "Percentual da Atividade Final deve ser maior que o Percentual da Atividade Inicial")){
                        return false;
                     }
               }else{
                   return false;
               }
               
               if(verificaCamposW3c(document.form.<%=Form.CAMPO_PERCENTUAL_AREA_EXPLORA_INICAL%>,<%="'"+MensagemErro.VALIDAR_INFORMAR_AREA_EXPLORADA_INICIAL+"'"%>)){
                      if(!valorAMenorQueB('<%=Form.CAMPO_PERCENTUAL_AREA_EXPLORA_INICAL%>', '<%=Form.CAMPO_PERCENTUAL_AREA_EXPLORA_FINAL%>' , "Percentual da Área Explorada Final deve ser maior que o Percentual da Área Explorada Inicial")){
                        return false;
                     }
               }else{
                   return false;
               }
               
               if(verificaCamposW3c(document.form.<%=Form.CAMPO_NUMERO_ITEM%>,<%="'"+MensagemErro.VALIDAR_INFORMAR_NUMERO_ITEM+"'"%>)){
                 
               }else{
                   return false;
               }
               
               if(verificaCamposW3c(document.form.<%=Form.CAMPO_PERCENTUAL_BASE_CALCULO_MINIMO%>,<%="'"+MensagemErro.VALIDAR_INFORMAR_BASE_CALCULO_MINIMO+"'"%>)){
                 
               }else{
                   return false;
               }
               
                if(verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_CRITERIO_BASE_CALCULO%>,<%="'"+MensagemErro.VALIDAR_INFORMAR_CRITERIO_BASE_CALCULO+"'"%>)){
                 
               }else{
                   return false;
               }
                              
               return true;
            }
            
            function inativarBaseCalculoImovelRural(codigoBaseCalculo)
            {
               if(confirm('Deseja realmente excluir o Registro da Base de Cáclculo Imóvel Rural?')){
                  document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_INATIVAR_BASE_CALCULO_IMOVEL_RURAL+"="%>'+codigoBaseCalculo;
                  document.form.submit();
                  return true;
               }else{
                  return false;
               }    
            }
            
         </script>
        <jsp:include page="/util/ViewVerificaErro.jsp"/>
	<title><abaco:tituloSistema></abaco:tituloSistema></title>
  </head>
   <body class="SEFAZ-Body" onload="funcoesDeCarregamentoDaPagina(); ">
   <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
       <form method="POST" name="form"  action="<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>">
        <table class="SEFAZ-TABLE-Moldura" cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                    <tr align="right">
                        <td colspan="9"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                    </tr>
                    <tr class="SEFAZ-TR-Titulo" align="center"> 
                        <td colspan="9">Incluir Base de Cálculo Imóvel Rural</td>
                    </tr>
                     <tr> 
                        <td colspan="9">&nbsp;</td>
                    </tr>
                    <tr  align="center">
                        <td class="SEFAZ-TR-Titulo" colspan="1" width="15%" >Tipo Distância</td>
                        <td class="SEFAZ-TR-Titulo" colspan="1" width="15%" >Atividade</td>
                        <td class="SEFAZ-TR-Titulo" colspan="1" width="15%" >Distância</td>
                        <td class="SEFAZ-TR-Titulo" colspan="1" width="15%" >% Atividade</td>
                        <td class="SEFAZ-TR-Titulo" colspan="1" width="15%" >% Área explorada</td>
                        <td class="SEFAZ-TR-Titulo" colspan="1" width="15%" >Item</td>
                        <td class="SEFAZ-TR-Titulo" colspan="1" width="15%" >Base de cálculo Mínima</td>
                        <td class="SEFAZ-TR-Titulo" colspan="1" width="15%" >Critério Base de Cálculo</td>
                        <td class="SEFAZ-TR-Titulo" colspan="1" width="25%" ></td>
                    </tr>
                    <tr  align="center">
                        <td>
                           <abaco:campoSelectDominio 
                              ajuda="" 
                              classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoDistancia"
                              name="<%=Form.CAMPO_SELECT_TIPO_DISTANCIA%>" 
                              tabIndex="" 
                              mostrarSelecione="true"
                              idCampo="<%=Form.CAMPO_SELECT_TIPO_DISTANCIA%>" 
                              opcaoSelecionada="${baseCalculoImovelRuralVo.tipoDistancia.valorCorrente}" >
                           </abaco:campoSelectDominio>
                        </td>
                        <td>
                         <table>
                           <tr><td> &nbsp; </td></tr>
                           <tr><td>
                              <abaco:campoSelectDominio 
                                 ajuda="" 
                                 classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoAtividade"
                                 name="<%=Form.CAMPO_SELECT_TIPO_ATIVIDADE%>" 
                                 tabIndex="" 
                                 mostrarSelecione="true"
                                 idCampo="<%=Form.CAMPO_SELECT_TIPO_ATIVIDADE%>" 
                                 opcaoSelecionada="${baseCalculoImovelRuralVo.tipoAtividade.valorCorrente}">
                              </abaco:campoSelectDominio>
                           </td></tr>
                           <tr><td> &nbsp; </td></tr>
                        </table>
                        </td>
                        <td colspan="1">
                           <table>
                              <tr ><td align="center">Inicial</td><td align="center" >Final</td></tr>
                              <tr>
                                 <td align="center"><abaco:campoApenasNumero  name="<%=Form.CAMPO_QUANTIDADE_DISTANCIA_INICIAL%>" idCampo="<%=Form.CAMPO_QUANTIDADE_DISTANCIA_INICIAL%>" mostrarZero="false" value="${baseCalculoImovelRuralVo.quantidadeDistanciaInicialFormatada}" size="5" maxlength="5"   onBlur="return limparLetrasW3c(this, event);" onKeyPress="apenasNumeroW3c(this,event);" onKeyUp="return apenasNumeroW3c(this,event);" /></td>
                                 <td align="center" ><abaco:campoApenasNumero name="<%=Form.CAMPO_QUANTIDADE_DISTANCIA_FINAL%>"  idCampo="<%=Form.CAMPO_QUANTIDADE_DISTANCIA_FINAL%>" mostrarZero="false"  value="${baseCalculoImovelRuralVo.quantidadeDistanciaFinalFormatada}"  size="5" maxlength="5"   onBlur="return limparLetrasW3c(this, event);" onKeyPress="apenasNumeroW3c(this,event);" onKeyUp="return apenasNumeroW3c(this,event);"></abaco:campoApenasNumero></td>
                              </tr>
                              <tr><td align="center" >&gt; <br/><small>Maior que</small></td><td align="center" >&le; <br/><small>Menor ou igual a</small></td></tr>
                           </table>
                        </td>
                       <td colspan="1">
                           <table>
                              <tr ><td align="center">Inicial</td><td align="center" >Final</td></tr>
                              <tr>
                                 <td align="center"><abaco:campoApenasNumero name="<%=Form.CAMPO_PERCENTUAL_ATIVIDADE_INICAL%>" idCampo="<%=Form.CAMPO_PERCENTUAL_ATIVIDADE_INICAL%>" mostrarZero="false" value="${baseCalculoImovelRuralVo.percentualAtividadeInicialFormatada}"  size="5" maxlength="3"   onBlur="return limparLetrasW3c(this, event);" onKeyPress="apenasNumeroW3c(this,event);" onKeyUp="return apenasNumeroW3c(this,event);" /></td>
                                 <td align="center" ><abaco:campoApenasNumero name="<%=Form.CAMPO_PERCENTUAL_ATIVIDADE_FINAL%>" idCampo="<%=Form.CAMPO_PERCENTUAL_ATIVIDADE_FINAL%>" mostrarZero="false" value="${baseCalculoImovelRuralVo.percentualAtividadeFinalFormatada}" size="5" maxlength="3"   onBlur="return limparLetrasW3c(this, event);" onKeyPress="apenasNumeroW3c(this,event);" onKeyUp="return apenasNumeroW3c(this,event);" /></td>
                              </tr>
                              <tr><td align="center" >&gt; <br/><small>Maior que</small></td><td align="center" >&le; <br/><small>Menor ou igual a</small></td></tr>
                           </table>
                        </td>
                        <td>
                             <table>
                              <tr ><td align="center">Inicial</td><td align="center" >Final</td></tr>
                              <tr>
                                 <td align="center"><abaco:campoApenasNumero  name="<%=Form.CAMPO_PERCENTUAL_AREA_EXPLORA_INICAL%>" idCampo="<%=Form.CAMPO_PERCENTUAL_AREA_EXPLORA_INICAL%>" mostrarZero="false" value="${baseCalculoImovelRuralVo.percentualAreaExploradaInicalFormatada}" size="5" maxlength="3"   onBlur="return limparLetrasW3c(this, event);" onKeyPress="apenasNumeroW3c(this,event);" onKeyUp="return apenasNumeroW3c(this,event);" /></td>
                                 <td align="center" ><abaco:campoApenasNumero name="<%=Form.CAMPO_PERCENTUAL_AREA_EXPLORA_FINAL%>"  idCampo="<%=Form.CAMPO_PERCENTUAL_AREA_EXPLORA_FINAL%>" mostrarZero="false"  value="${baseCalculoImovelRuralVo.percentualAreaExploradaFinalFormatada}"  size="5" maxlength="3"   onBlur="return limparLetrasW3c(this, event);" onKeyPress="apenasNumeroW3c(this,event);" onKeyUp="return apenasNumeroW3c(this,event);"></abaco:campoApenasNumero></td>
                              </tr>
                              <tr><td align="center" >&gt; <br/><small>Maior que</small></td><td align="center" >&le; <br/><small>Menor ou igual a</small></td></tr>
                           </table>
                        </td>
                        <td>
                           <table>
                              <tr ><td align="center">&nbsp;</td></tr>
                              <tr>
                                 <td align="center"><abaco:campoApenasNumero  name="<%=Form.CAMPO_NUMERO_ITEM%>" idCampo="<%=Form.CAMPO_NUMERO_ITEM%>" mostrarZero="false" value="${baseCalculoImovelRuralVo.numeroItemFormatada}" size="5" maxlength="5"   onBlur="return limparLetrasW3c(this, event);" onKeyPress="apenasNumeroW3c(this,event);" onKeyUp="return apenasNumeroW3c(this,event);" /></td>                               
                              </tr>
                              <tr><td align="center" >&nbsp;</td></tr>
                           </table>
                        </td>
                        <td>
                            <table>
                              <tr ><td align="center">&nbsp;</td></tr>
                              <tr>
                                 <td align="center"><abaco:campoMonetario quantidadeDigitosInteiros="3" quantidadeCasasDecimais="2"  name="<%=Form.CAMPO_PERCENTUAL_BASE_CALCULO_MINIMO%>" idCampo="<%=Form.CAMPO_PERCENTUAL_BASE_CALCULO_MINIMO%>" mostrarZero="false" value="${baseCalculoImovelRuralVo.percentualBaseCalculoMinimoFormatada}" size="8" /></td>                               
                              </tr>
                              <tr><td align="center" >&nbsp;</td></tr>
                           </table>                            
                        </td>
                        <td colspan="1">
                           <abaco:campoSelectDominio 
                              ajuda="" 
                              classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnCriterioBaseCalculo"
                              name="<%=Form.CAMPO_SELECT_CRITERIO_BASE_CALCULO%>" 
                              tabIndex="" 
                              mostrarSelecione="true"
                              idCampo="<%=Form.CAMPO_SELECT_CRITERIO_BASE_CALCULO%>" 
                              opcaoSelecionada="${baseCalculoImovelRuralVo.criterioBaseCalculo.valorCorrente}">
                           </abaco:campoSelectDominio>
                        </td>
                        <td colspan="1">
                            <input type="button" value="Continuar" class="SEFAZ-INPUT-Botao" name="Cancelar" onClick="gerarCodigoGia();"/>
                        </td>
                    </tr>                
         
                    <tr> 
                         <td colspan="9">&nbsp;</td>
                    </tr>
                    
                     <tr> 
                         <td colspan="9">
                          <c:forEach items="${baseCalculoImovelRuralVo.collVO}" var="baseCalc" varStatus="contador">
                           &nbsp;&nbsp;
                           <c:forEach items="${baseCalc.collVO}" var="baseCalculo" varStatus="contador">                       
                              <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">                                                                             
                                 <tr>
                                 <td class="SEFAZ-TR-Titulo" colspan="1">Tipo Distância</td>
                                 <td class="SEFAZ-TR-Titulo" colspan="1">Atividade</td>
                                    <td colspan="2" align="center" class="SEFAZ-TR-Titulo" >                         
                                       <table width="100%" ><tr><td class="SEFAZ-TR-Titulo" colspan="2" >Distância</td></tr><tr><td class="SEFAZ-TR-Titulo" >Inicial</td><td class="SEFAZ-TR-Titulo" >Final</td></tr></table>
                                    </td>
                                    <td colspan="2" align="center" class="SEFAZ-TR-Titulo" >                         
                                       <table width="100%" ><tr><td class="SEFAZ-TR-Titulo" colspan="2" >% Atividade</td></tr><tr><td class="SEFAZ-TR-Titulo" >Inicial</td><td class="SEFAZ-TR-Titulo" >Final</td></tr></table>
                                    </td>
                                    <td class="SEFAZ-TR-Titulo" colspan="2" align="center">                         
                                       <table width="100%" ><tr><td class="SEFAZ-TR-Titulo" colspan="2" >% Área Explorada</td></tr><tr><td class="SEFAZ-TR-Titulo" >Inicial</td><td class="SEFAZ-TR-Titulo" >Final</td></tr></table>
                                    </td>
                                    <td class="SEFAZ-TR-Titulo" colspan="1">Item</td>
                                    <td class="SEFAZ-TR-Titulo" colspan="1"><small>Base de cálculo Mínima</small></td>
                                    <td class="SEFAZ-TR-Titulo" colspan="1"><small>Critério Base de Cálculo</small></td>
                                    <td class="SEFAZ-TR-Titulo" colspan="1">&nbsp;</td>
                                 </tr>
                                    <c:forEach items="${baseCalculo.collVO}" var="base" > 
                                          <abaco:linhaTabela>
                                             <td align="center" width="80" ><c:out value="${base.tipoDistancia.textoCorrente}"/></td>
                                             <td align="center" width="80" ><c:out value="${base.tipoAtividade.textoCorrente}"/></td>
                                             <td align="center" width="80" ><c:out value="${base.quantidadeDistanciaInicialFormatada}"/></td>
                                             <td align="center" width="80" ><c:out value="${base.quantidadeDistanciaFinalFormatada}"/></td>
                                             <td align="center" width="80" ><c:out value="${base.percentualAtividadeInicialFormatada}"/></td>
                                             <td align="center" width="80" ><c:out value="${base.percentualAtividadeFinalFormatadaNaoMostrarZero}"/></td>
                                             <td align="center" width="80" ><c:out value="${base.percentualAreaExploradaInicalFormatada}"/></td>
                                             <td align="center" width="80" ><c:out value="${base.percentualAreaExploradaFinalFormatadaNaoMostrarZero}"/></td>
                                             <td align="center" width="220" ><c:out value="${base.numeroItemFormatada}"/></td>
                                             <td align="center" width="220" ><c:out value="${base.percentualBaseCalculoMinimoFormatada}"/></td>
                                             <td align="center" width="220" ><c:out value="${base.criterioBaseCalculo.textoCorrente}"/></td>
                                             <td align="center" width="200" >
                                               &nbsp;&nbsp;
                                                <c:if test="${base.isMaiorDistanciaFinalPerimetroUrbano}">
                                                   <c:if test="${false}">
                                                      <a href="javascript:void(solicitarBaseCalculoImovelRural(<c:out value="${base.codigo}"></c:out>));">Alterar</a>&nbsp;&nbsp;
                                                   </c:if>
                                                   <a href="javascript:void(inativarBaseCalculoImovelRural(<c:out value="${base.codigo}"></c:out>));">Excluir</a>
                                                </c:if>
                                                 <c:if test="${!base.isMaiorDistanciaFinalPerimetroUrbano}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                                             </td>
                                          </abaco:linhaTabela>
                                          <tr>
                                              <td colspan="9"> <!-- separa cada linha de dados &nbsp;&nbsp; --></td>
                                          </tr>
                                       </c:forEach>
                                    <tr>
                                       <td colspan="9"> <!-- separa cada cavecalho &nbsp;&nbsp; --></td>
                                    </tr>                                                       
                              </table>
                            </c:forEach>   
                           </c:forEach>     
                         </td>
                     </tr>
                    
                     <tr> 
                         <td colspan="9">&nbsp;</td>
                    </tr>          
                    <tr> 
                        <td colspan="9" align="center">
                           <abaco:botaoCancelar/>
                        </td>
                    </tr>
							<tr> 
								<td colspan="9"><font color="red"><b>* Campos Obrigatórios</b></font></td>
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
            <g:mostrarRodape/>
   
             <script type="text/javascript" language="javascript">
                  // Conjunto de funcoes que devem ser excutadas durante o carregamento da pagina.
                  function funcoesDeCarregamentoDaPagina(){
                     // funcoes que devem ser excutadas baseadas em estado da pagina.
                     <c:if test="${false}">
         
                     </c:if>
                     verificaErro(); 
                     log();
                     botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');
                  }
            </script>
  </body>
</html>