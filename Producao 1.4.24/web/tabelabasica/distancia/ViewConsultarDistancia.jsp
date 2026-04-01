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
                var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
                return new Array(botao1);
            }
            
            function adicionarDistancia()
            {
               if(true)
               {
                  //desabilitarBotoes(obterArrayBotoes());
                  document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ADICIONAR_DISTANCIA+"=1"%>';
                  document.form.submit();
               }
            }
            
            function solicitarAlterarDistancia(codigoDistancia)
            {
               desabilitarBotoes(obterArrayBotoes());
               document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ALTERAR_DISTANCIA+"="%>'+codigoDistancia;
               document.form.submit();
            }
            
            function excluirDistancia(codigoDistancia)
            {
               if(confirm('Deseja realmente excluir a Distância?'))
               {
                  desabilitarBotoes(obterArrayBotoes());
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
               if(verificaCamposW3c(document.form.<%=Form.CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO%>,<%="'"+MensagemErro.VALIDAR_DISTANCIA_INICIAL_PERIMETRO_URBANO+"'"%>))
               {
                  distanciaInicial = getValorElementById('<%=Form.CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO%>');
                  if(distanciaInicial < 0){
                     alert(<%="'"+MensagemErro.VALIDAR_DISTANCIA_INICIAL_PERIMETRO_URBANO_NAO_PODE_SER_MENOR_QUE_ZERO+"'"%>);
                     document.getElementById('<%=Form.CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO%>').focus();
                     return false;
                  }          
               }else{
                  return false;
               }
              if(verificaCamposW3c(document.form.<%=Form.CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO%>,<%="'"+MensagemErro.VALIDAR_DISTANCIA_INICIAL_PERIMETRO_URBANO+"'"%>))
              {
                  if( campoXmenorQueCampoY('<%=Form.CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO%>', '<%=Form.CAMPO_DISTANCIA_INICIAL_PERIMETRO_URBANO%>' ) ){
                      alert(<%="'"+MensagemErro.VALIDAR_DISTANCIA_FINAL_MENOR_QUE_INICIAL_PERIMETRO_URBANO+"'"%>);
                      document.getElementById('<%=Form.CAMPO_DISTANCIA_FINAL_PERIMETRO_URBANO%>').focus();
                      return false;
                  }               
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
                        <td colspan="3"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                    </tr>
                    <tr class="SEFAZ-TR-Titulo" align="center"> 
                        <td colspan="3">Consultar Distância</td>
                    </tr>
                     <tr> 
                        <td colspan="3">&nbsp;</td>
                    </tr>                
                    <tr> 
                         <td colspan="3">
                              <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">                            
                                 <c:forEach items="${distanciaVo.collVO}" var="dist" varStatus="contador">
                                 <tr>
                                    <td colspan="2" align="center">                         
                                       <table width="100%" ><tr><td class="SEFAZ-TR-Titulo" colspan="2" >Perímetro <br/> Urbano</td></tr><tr><td class="SEFAZ-TR-Titulo" >Inicial</td><td class="SEFAZ-TR-Titulo" >Final</td></tr></table>
                                    </td>
                                    <td colspan="2" align="center">                         
                                       <table width="100%" ><tr><td class="SEFAZ-TR-Titulo" colspan="2" >Rodovia <br/> pavimentada</td></tr><tr><td class="SEFAZ-TR-Titulo" >Inicial</td><td class="SEFAZ-TR-Titulo" >Final</td></tr></table>
                                    </td>
                                    <td class="SEFAZ-TR-Titulo" colspan="1">Distância a Utilizar</td>
                                 </tr>
                                    <c:forEach items="${dist.collVO}" var="distancia" varStatus="contador">                                   
                                          <abaco:linhaTabela>
                                             <td align="center" width="80" ><c:out value="${distancia.distanciaInicialPerimetroUrbano}"/></td>
                                             <td align="center" width="80" ><c:out value="${distancia.distanciaFinalPerimetroUrbanoFormatada}"/></td>
                                             <td align="center" width="80" ><c:out value="${distancia.distanciaInicialRodoviaPavimentada}"/></td>
                                             <td align="center" width="80" ><c:out value="${distancia.distanciaFinalRodoviaPavimentadaFormatada}"/></td>
                                             <td align="center" width="220" ><c:out value="${distancia.tipoDistancia.textoCorrente}"/></td>
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