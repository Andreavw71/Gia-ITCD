<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.iptu.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoIPTU"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
      <head>
            <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></meta>
            <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css"></link>
            <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
            <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
            <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
            <script type="text/javascript" language="javascript">
            
            function selectTipoIPTU(){
              var opacaoCombo = document.form.<%=Form.CAMPO_SELECT_TIPO_IPTU%>.value;
              
              // DomnTipoIPTU.INTEGRADO os valores NAO podem ser inseridos manualmente.
              if(opacaoCombo == '<%=DomnTipoIPTU.INTEGRADO%>'){
                  // Remove qualquer valor informado.
                  document.getElementById('<%=Form.CAMPO_VALOR_PERCENTUAL_ESTIMADO%>').value = "";
                  document.getElementById('<%=Form.CAMPO_VALOR_METRO_TERRITORIAL%>').value = "";
                  document.getElementById('<%=Form.CAMPO_VALOR_METRO_PREDIAL%>').value = "";
                  // Desabilita os campos para evitar que usuario tente informar algum valor.
                  document.getElementById('<%=Form.CAMPO_VALOR_PERCENTUAL_ESTIMADO%>').disabled = true;
                  document.getElementById('<%=Form.CAMPO_VALOR_METRO_TERRITORIAL%>').disabled = true;
                  document.getElementById('<%=Form.CAMPO_VALOR_METRO_PREDIAL%>').disabled = true;
              }else{
                  // Habilita os campos para serem inseridos manualmente.
                  document.getElementById('<%=Form.CAMPO_VALOR_PERCENTUAL_ESTIMADO%>').disabled = false;
                  document.getElementById('<%=Form.CAMPO_VALOR_METRO_TERRITORIAL%>').disabled = false;
                  document.getElementById('<%=Form.CAMPO_VALOR_METRO_PREDIAL%>').disabled = false;
              }
            }
            
            function validaFormulario()
            {
               if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_IPTU%>,<%="'"+MensagemErro.VALIDAR_SELECIONE_TIPO+"'"%>))
                  {                   
                     return false;
                  }
                  var opacaoCombo = document.form.<%=Form.CAMPO_SELECT_TIPO_IPTU%>.value;
                  if(opacaoCombo == '<%=DomnTipoIPTU.ESTIMATIVA%>'){
                        if(!verificaCamposW3c(document.form.<%=Form.CAMPO_VALOR_PERCENTUAL_ESTIMADO%>,<%="'"+MensagemErro.VALIDAR_INFORMAR_PERCENTUAL_ESTIMADO+"'"%>))
                        {
                           return false;
                        }
                        if(!verificaCamposW3c(document.form.<%=Form.CAMPO_VALOR_METRO_TERRITORIAL%>,<%="'"+MensagemErro.VALIDAR_INFORMAR_VALOR_TERRITORIAL+"'"%>))
                        {
                           return false;
                        }
                        if(!verificaCamposW3c(document.form.<%=Form.CAMPO_VALOR_METRO_PREDIAL%>,<%="'"+MensagemErro.VALIDAR_INFORMAR_VALOR_TERRITORIAL+"'"%>))
                        {
                           return false;
                        }
                  }
                   
               return true;
            }
            
            function adicionarIPTU(codigoMunicipio)
            {
               if(validaFormulario())
               {
                  document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ADICIONAR_IPTU+"="%>'+codigoMunicipio;
                  document.form.submit();
               }
            }
            
            function solicitarIncluirIPTU(codigoMunicipio)
            {
               // desabilitarBotoes(obterArrayBotoes());
               document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ALTERAR_IPTU+"="%>'+codigoMunicipio;
               document.form.submit();
               return true;
            }            
            
            </script>
            <jsp:include page="/util/ViewVerificaErro.jsp"/>
            <title>
                  <abaco:tituloSistema></abaco:tituloSistema>
            </title>
      </head>
      <body class="SEFAZ-Body" onload="funcoesDeCarregamentoDaPagina(); ">      
         <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
          <form method="POST" name="form"  action="<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>">
          <table class="SEFAZ-TABLE-Moldura" cellspacing="1" cellpadding="0" border="0" width="740" align="center">
            <c:if test="${iptuVo.municipio.codgMunicipio != null}">
             <tr class="SEFAZ-TR-Titulo" align="center"> 
               <td colspan="3" >Incluir IPTU</td>
            </tr>
            <tr> 
               <td colspan="3" >&nbsp;</td>
            </tr>         
            <tr align="center"> 
               <td class="SEFAZ-TR-Titulo" colspan="1" width="20%" >Município</td>
               <td class="SEFAZ-TR-Titulo" colspan="1" width="60%" >Valor Total do Imóvel/ha</td>
               <td class="SEFAZ-TR-Titulo" colspan="1" width="20%" >&nbsp;</td>
            </tr>
            <tr>
               <td colspan="3">
                  <table class="SEFAZ-TABLE-Moldura" cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                     <tr>
                         <td class="SEFAZ-TR-Titulo" width="20%" align="center">Cidades</td>    
                        <td class="SEFAZ-TR-Titulo" width="15%" align="center">Tipo</td>
                        <td class="SEFAZ-TR-Titulo" width="15%" align="center">Percentutal Estimado</td>
                        <td class="SEFAZ-TR-Titulo" width="15%" align="center" >Valor m² Territorial</td>
                        <td class="SEFAZ-TR-Titulo" width="15%" align="center">Valor m² Predial</td>
                        <td class="SEFAZ-TR-Titulo" width="20%" align="center">&nbsp;</td>
                     </tr>
                     <tr>
                        <td  width="20%" align="center"><input disabled="true" maxlength="20" name="" size="20" value="<c:out value="${iptuVo.municipio.nomeMunicipio}"/>"></input></td>
                        <td >
                            <abaco:campoSelectDominio 
                              ajuda="" 
                              classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoIPTU"
                              name="<%=Form.CAMPO_SELECT_TIPO_IPTU%>" 
                              tabIndex="" 
                              mostrarSelecione="true"
                              idCampo="<%=Form.CAMPO_SELECT_TIPO_IPTU%>" 
                              opcaoSelecionada="${iptuVo.tipoITPU.valorCorrente}"
                              onChange="selectTipoIPTU();">
                           </abaco:campoSelectDominio>
                        </td>
                        <td  width="20%" align="center"><abaco:campoMonetario quantidadeDigitosInteiros="8" quantidadeCasasDecimais="4" name="<%=Form.CAMPO_VALOR_PERCENTUAL_ESTIMADO%>" idCampo="<%=Form.CAMPO_VALOR_PERCENTUAL_ESTIMADO%>" value="${iptuVo.valorPercentualEstimadoFormatado}" size="12" /></td>
                        <td  width="20%" align="center"><abaco:campoMonetario  quantidadeDigitosInteiros="8" name="<%=Form.CAMPO_VALOR_METRO_TERRITORIAL%>" idCampo="<%=Form.CAMPO_VALOR_METRO_TERRITORIAL%>" value="${iptuVo.valorMetroTerritorialFormatado}" size="12" /></td>
                        <td  width="20%" align="center"><abaco:campoMonetario quantidadeDigitosInteiros="8" name="<%=Form.CAMPO_VALOR_METRO_PREDIAL%>" idCampo="<%=Form.CAMPO_VALOR_METRO_PREDIAL%>" value="${iptuVo.valorMetroPredialFormatado}" size="12" /></td>
                        <td  width="20%" align="center"><input type="button"  value="<%=Form.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" name="Form.BOTAO_ALTERAR_IPTU" id="Form.BOTAO_ALTERAR_IPTU" onClick="javascript:void(adicionarIPTU(<c:out value="${iptuVo.municipio.codgMunicipio}"></c:out>));"/></td>
                     </tr>
                  </table>
               </td>
            </tr>
            </c:if>
            <tr> 
               <td colspan="3" >&nbsp;</td>
            </tr>
            <tr>
            <tr  align="center"> 
               <td colspan="3" class="SEFAZ-TR-Titulo" width="20%" >Incluir IPTU Estimativa</td>
            </tr>
            <tr>
               <td colspan="3">
                  <table class="SEFAZ-TABLE-Moldura" cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                     <tr >
                        <td class="SEFAZ-TR-Titulo" width="20%" align="center">Cidades</td>    
                        <td class="SEFAZ-TR-Titulo" width="15%" align="center">Tipo</td>
                        <td class="SEFAZ-TR-Titulo" width="15%" align="center">Percentutal Estimado</td>
                        <td class="SEFAZ-TR-Titulo" width="15%" align="center" >Valor m² Territorial</td>
                        <td class="SEFAZ-TR-Titulo" width="15%" align="center">Valor m² Predial</td>
                        <td class="SEFAZ-TR-Titulo" width="20%" align="center">&nbsp;</td>
                     <tr>
                     <c:forEach items="${iptuVo.collVO}" var="iptu" varStatus="contador">
                           <abaco:linhaTabela>
                              <td width="20%" align="center"><c:out value="${iptu.municipio.nomeMunicipio}"/></td>
                              <td width="15%" align="center"><c:out value="${iptu.tipoITPU.textoCorrente}"/></td>
                              <td width="15%" align="center"><c:out value="${iptu.valorPercentualEstimadoFormatado}"/></td>
                              <td width="15%" align="center" ><c:out value="${iptu.valorMetroTerritorialFormatado}"/></td>
                              <td width="15%" align="center"><c:out value="${iptu.valorMetroPredialFormatado}"/></td>
                              <td width="20%" align="center">
                                 <c:if test="${iptu.codigo == 0}" >
                                     <a href="javascript:void(solicitarIncluirIPTU(<c:out value="${iptu.municipio.codgMunicipio}"></c:out>));">Inlcuir</a>&nbsp;&nbsp;
                                 </c:if>
                                 <c:if test="${iptu.codigo != 0}" >
                                     <a href="javascript:void(solicitarIncluirIPTU(<c:out value="${iptu.municipio.codgMunicipio}"></c:out>));">Alterar</a>&nbsp;&nbsp;
                                 </c:if>
                                
                              </td>
                           </abaco:linhaTabela>
                     </c:forEach>
                  </table>
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
          </table>
          </form>
         <g:mostrarRodape/>
         
         <script type="text/javascript" language="javascript">
            // Conjunto de funcoes que devem ser excutadas durante o carregamento da pagina.
            function funcoesDeCarregamentoDaPagina(){
               // funcoes que devem ser excutadas baseadas em estado da pagina.
               <c:if test="${iptuVo.municipio.codgMunicipio != null}">
                  selectTipoIPTU();               
               </c:if>
               verificaErro(); 
               log();
               botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');
            }
         </script>
         
      </body>
</html>