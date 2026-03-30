<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.distancia.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoDistancia"%>
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
            
            function campoXmenorQueCampoY(idCampoX , idCampoY){
               var campoX = getValorElementById(idCampoX);
               var campoY = getValorElementById(idCampoY);
               if(campoX < campoY){
                  return true;
               }
               return false;
            }
            
            function getValorElementById(idCampo){
              return  document.getElementById(idCampo).value;
            }
            
            function obterArrayBotoes()
            {
                var botao1 = document.form.<%=Form.BOTAO_ADICIONAR%>;
                return new Array(botao1);
            }
            
            function adicionarDistancia()
            {
               if(validaFormulario())
               {
                  //desabilitarBotoes(obterArrayBotoes());
                  document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ADICIONAR_DISTANCIA+"=1"%>';
                  document.form.submit();
               }
            }
            
            function solicitarAlterarDistancia(codigoDistancia)
            {
               //desabilitarBotoes(obterArrayBotoes());
               document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ALTERAR_DISTANCIA+"="%>'+codigoDistancia;
               document.form.submit();
            }
            
            function excluirDistancia(codigoDistancia)
            {
               if(confirm('Deseja realmente excluir a Distância?'))
               {
                  //desabilitarBotoes(obterArrayBotoes());
                  document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_EXCLUIR_DISTANCIA+"="%>'+codigoDistancia;
                  document.form.submit();
                  return true;
               }
               else
               {
                  return false
               }
            }            
            
            function validaFormulario()
            {
               // O campo distancia inicial Perímetro Urbano deve sempre ser informado.
               if(verificaCamposW3c(document.form.<%=Form.CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO%>,<%="'"+MensagemErro.VALIDAR_DISTANCIA_INICIAL_PERIMETRO_URBANO+"'"%>))
               {
                  // O campo distancia inicial Perímetro Urbano deve ser maior ou igual zero.
                  var distanciaInicial = getValorElementById('<%=Form.CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO%>');
                  if(distanciaInicial < 0){
                     alert(<%="'"+MensagemErro.VALIDAR_DISTANCIA_INICIAL_PERIMETRO_URBANO_NAO_PODE_SER_MENOR_QUE_ZERO+"'"%>);
                     document.getElementById('<%=Form.CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO%>').focus();
                     return false;
                  }
                  var distanciafinalPerimetroUrbano = getValorElementById('<%=Form.CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO%>');
                  var valorDistanciafinalPerimetroUrbano  = String(distanciafinalPerimetroUrbano);
                  if( valorDistanciafinalPerimetroUrbano != ""){
                     if( parseInt(distanciafinalPerimetroUrbano) <= parseInt( distanciaInicial) ){
                          alert(<%="'"+MensagemErro.VALIDAR_DISTANCIA_FINAL_MENOR_QUE_INICIAL_PERIMETRO_URBANO+"'"%>);
                          document.getElementById('<%=Form.CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO%>').focus();
                        return false;
                     }
                  }
               }else{
                  return false;
               }
              if(verificaCamposW3c(document.form.<%=Form.CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA%>,<%="'"+MensagemErro.VALIDAR_DISTANCIA_INICIAL_PERIMETRO_RODOVIA_PAVIMENTADA+"'"%>))
              {
                  //if( campoXmenorQueCampoY('<%=Form.CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA%>', '<%=Form.CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA%>' ) ){
                   //   alert(<%="'"+MensagemErro.VALIDAR_DISTANCIA_INICIAL_PERIMETRO_RODOVIA_PAVIMENTADA+"'"%>);
                   //   document.getElementById('<%=Form.CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA%>').focus();
                   //   return false;
                 // }               
               }else{
                  return false;
               }
              
               if(verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_DISTANCIA%>,<%="'"+MensagemErro.VALIDAR_SELECIONAR_TIPO_DISTANCIA+"'"%>)){
                 
               }else{
                   return false;
               }
               return true;
            }
         </script>
        <jsp:include page="/util/ViewVerificaErro.jsp"/>
	<title><abaco:tituloSistema></abaco:tituloSistema></title>
  </head>
   <body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro();log();">
   <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
       <form method="POST" name="form"  action="<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>">
        <table class="SEFAZ-TABLE-Moldura" cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                    <tr align="right">
                        <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                    </tr>
                    <tr class="SEFAZ-TR-Titulo" align="center"> 
                        <td colspan="4">Incluir Distância</td>
                    </tr>
                     <tr> 
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr  align="center"> 
                        <td class="SEFAZ-TR-Titulo" colspan="1" width="25%" >Perímetro Urbano</td>
                        <td class="SEFAZ-TR-Titulo" colspan="1" width="25%" >Rodovia pavimentada</td>
                        <td class="SEFAZ-TR-Titulo" colspan="1" width="25%" >Distância a Utilizar</td>
                        <td class="SEFAZ-TR-Titulo" colspan="1" width="25%" ></td>
                    </tr>
                    <tr  align="center"> 
                        <td colspan="1">
                           <table>
                              <tr ><td align="center">Inicial</td><td align="center" >Final</td></tr>
                              <tr>
                                 <td align="center"><abaco:campoApenasNumero  name="<%=Form.CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO%>" idCampo="<%=Form.CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO%>" mostrarZero="false" value="${distanciaVo.distanciaInicialPerimetroUrbano}" size="5" maxlength="5"   onBlur="return limparLetrasW3c(this, event);" onKeyPress="apenasNumeroW3c(this,event);" onKeyUp="return apenasNumeroW3c(this,event);" /></td>
                                 <td align="center" ><abaco:campoApenasNumero name="<%=Form.CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO%>"  idCampo="<%=Form.CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO%>" mostrarZero="false"  value="${distanciaVo.distanciaFinalPerimetroUrbano}"  size="5" maxlength="5"   onBlur="return limparLetrasW3c(this, event);" onKeyPress="apenasNumeroW3c(this,event);" onKeyUp="return apenasNumeroW3c(this,event);"></abaco:campoApenasNumero></td>
                              </tr>
                              <tr><td align="center" >&gt; <br/><small>Maior que</small></td><td align="center" >&le; <br/><small>Menor ou igual a</small></td></tr>
                           </table>
                        </td>
                       <td colspan="1">
                           <table>
                              <tr ><td align="center">Inicial</td><td align="center" >Final</td></tr>
                              <tr>
                                 <td align="center"><abaco:campoApenasNumero name="<%=Form.CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA%>" idCampo="<%=Form.CAMPO_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA%>" mostrarZero="false" value="${distanciaVo.distanciaInicialRodoviaPavimentada}"  size="5" maxlength="5"   onBlur="return limparLetrasW3c(this, event);" onKeyPress="apenasNumeroW3c(this,event);" onKeyUp="return apenasNumeroW3c(this,event);" /></td>
                                 <td align="center" ><abaco:campoApenasNumero name="<%=Form.CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA%>" idCampo="<%=Form.CAMPO_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA%>" mostrarZero="false" value="${distanciaVo.distanciaFinalRodoviaPavimentada}" size="5" maxlength="5"   onBlur="return limparLetrasW3c(this, event);" onKeyPress="apenasNumeroW3c(this,event);" onKeyUp="return apenasNumeroW3c(this,event);" /></td>
                              </tr>
                              <tr><td align="center" >&gt; <br/><small>Maior que</small></td><td align="center" >&le; <br/><small>Menor ou igual a</small></td></tr>
                           </table>
                        </td>
                        <td colspan="1">
                           <abaco:campoSelectDominio 
                              ajuda="" 
                              classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoDistancia"
                              name="<%=Form.CAMPO_SELECT_TIPO_DISTANCIA%>" 
                              tabIndex="" 
                              mostrarSelecione="true"
                              idCampo="<%=Form.CAMPO_SELECT_TIPO_DISTANCIA%>" 
                              opcaoSelecionada="${distanciaVo.tipoDistancia.valorCorrente}">
                           </abaco:campoSelectDominio><font color="red">*</font>
                        </td>
                        <td colspan="1">
                            <input type="button"  value="<%=Form.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" name="Form.BOTAO_ADICIONAR" onClick="adicionarDistancia();"/>
                        </td>
                    </tr>                
                    <tr> 
                         <td colspan="4">&nbsp;</td>
                    </tr>
                    
                    <tr> 
                         <td colspan="4">
                              <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">                            
                                 <c:forEach items="${distanciaVo.collVO}" var="dist" varStatus="contador">
                                 <tr>
                                    <td colspan="2" align="center" class="SEFAZ-TR-Titulo">                         
                                       <table width="100%" ><tr><td class="SEFAZ-TR-Titulo" colspan="2" >Perímetro <br/> Urbano</td></tr><tr><td class="SEFAZ-TR-Titulo" >Inicial</td><td class="SEFAZ-TR-Titulo" >Final</td></tr></table>
                                    </td>
                                    <td colspan="2" align="center" class="SEFAZ-TR-Titulo" >                         
                                       <table width="100%" ><tr><td class="SEFAZ-TR-Titulo" colspan="2" >Rodovia pavimentada</td></tr><tr><td class="SEFAZ-TR-Titulo" >Inicial</td><td class="SEFAZ-TR-Titulo" >Final</td></tr></table>
                                    </td>
                                    <td class="SEFAZ-TR-Titulo" colspan="1">Distância a Utilizar</td>
                                    <td class="SEFAZ-TR-Titulo" colspan="1">&nbsp;</td>
                                 </tr>
                                    <c:forEach items="${dist.collVO}" var="distancia" varStatus="contador">                                   
                                          <abaco:linhaTabela>
                                             <td align="center" width="80" ><c:out value="${distancia.distanciaInicialPerimetroUrbano}"/></td>
                                             <td align="center" width="80" > <c:if test="${distancia.distanciaFinalPerimetroUrbano != 0}"> <c:out value="${distancia.distanciaFinalPerimetroUrbano}"/> </c:if> </td>
                                             <td align="center" width="80" ><c:out value="${distancia.distanciaInicialRodoviaPavimentada}"/></td>
                                             <td align="center" width="80" ><c:if test="${distancia.distanciaFinalRodoviaPavimentada != 0}"> <c:out value="${distancia.distanciaFinalRodoviaPavimentada}"/> </c:if></td>
                                             <td align="center" width="220" ><c:out value="${distancia.tipoDistancia.textoCorrente}"/></td>
                                             <td align="center" width="200" >
                                               &nbsp;&nbsp;
                                                <c:if test="${distancia.isMaiorDistanciaFinalPerimetroUrbano}">
                                                   <c:if test="${false}">
                                                      <a href="javascript:void(solicitarAlterarDistancia(<c:out value="${distancia.codigo}"></c:out>));">Alterar</a>&nbsp;&nbsp;
                                                   </c:if>
                                                   <a href="javascript:void(excluirDistancia(<c:out value="${distancia.codigo}"></c:out>));">Excluir</a>
                                                </c:if>
                                             </td>
                                          </abaco:linhaTabela>
                                       </c:forEach>
                                    </c:forEach>
                              </table>     
                         </td>
                    </tr>
                     <tr> 
                         <td colspan="4">&nbsp;</td>
                    </tr>          
                    <tr> 
                        <td colspan="4" align="center">
                           <abaco:botaoRetornoMenu />
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
   <g:mostrarRodape/>
  </body>
</html>