<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
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
            
            function validaFormulario()
            {
               if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DESCRICAO_PROCESSO%>,<%="'"+MensagemErro.FAVOR_INFORMAR_DESCRICAO_PROCESSO+"'"%>))
               {
                  return false;
               }
               if(!verificaCamposW3c(document.form.<%=Form.CAMPO_TIPOS_DE_PROCESSOS%>,<%="'"+MensagemErro.FAVOR_SELECIONAR_TIPO_PROCESSO+"'"%>))
               {
                  return false;
               }
                  if(!verificaCamposW3c(document.form.<%=Form.TIPO_PROCESSO_EPROCESS%>,<%="'"+MensagemErro.FAVOR_SELECIONAR_TIPO_PROCESSO_E_PROCESS+"'"%>))
               {
                  return false;
               }
               return true;
            }
            
            function adicionarTipoProcesso()
            {
               if(validaFormulario())
               {
                  //desabilitarBotoes(obterArrayBotoes());
                  document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_INCLUIR_PROCESSO_EPROCESS+"=1"%>';
                  document.form.submit();
               }
            }
            </script>
            <jsp:include page="/util/ViewVerificaErro.jsp"/>
            <title>
                  <abaco:tituloSistema></abaco:tituloSistema>
            </title>
      </head>
      <body>
         <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
          <form method="POST" name="form"  action="<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>">
          
           
          
             <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                  <tr class="SEFAZ-TR-Titulo" align="center">
                        <td colspan="4">Dados do registro</td>
                  </tr>
               
                  <tr> 
                        <td class="SEFAZ-TD-RotuloEntrada" width="278">Tipo de Processo E-Process:&nbsp;</td>
                        <td class="SEFAZ-TD-CampoEntrada" width="462">
                              <select name="<%=Form.TIPO_PROCESSO_EPROCESS%>" id="<%=Form.TIPO_PROCESSO_EPROCESS%>"  class="chzn-select"  tabindex="2" >
                                   <option  value="" selected>Selecione</option><%--<%=Form.SELECIONE%>--%>
                                   <c:forEach var="tipoProcessoIntegracaoVo" items="${tipoProcessoVo.tipoProcessoIntegracaoVo.collVO}">
                                       <option value="<c:out value="${tipoProcessoIntegracaoVo.codgTipoProcesso}"></c:out>" selected="selected"><c:out value="${tipoProcessoIntegracaoVo.descricao}"></c:out></option>	
                                   </c:forEach>
                              </select><font color="red">*</font>
                        </td>
                  </tr>
                  
                  <tr> 
                        <td class="SEFAZ-TD-RotuloEntrada" width="278">Tipo de Processo:&nbsp;</td>
                        <td class="SEFAZ-TD-CampoEntrada" width="462">
                              <abaco:campoSelectDominio 
                                 ajuda="" 
                                 classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoEprocess" 
                                 name="<%=Form.CAMPO_TIPOS_DE_PROCESSOS%>" 
                                 tabIndex=""
                                 mostrarSelecione="true"
                                 idCampo="<%=Form.CAMPO_TIPOS_DE_PROCESSOS%>"
                                 opcaoSelecionada="${bemVo.classificacaoTipoBem.valorCorrente}">
                              </abaco:campoSelectDominio>
                              <font color="red">*</font>
                        </td>
                   </tr>
                   
                   <tr> 
                        <td class="SEFAZ-TD-RotuloEntrada" width="278">Descrição do Processo:&nbsp;</td>
                        <td class="SEFAZ-TD-CampoEntrada" width="462">
                              <input name="<%=Form.CAMPO_DESCRICAO_PROCESSO%>" id="<%=Form.CAMPO_DESCRICAO_PROCESSO%>"  type="text" class="SEFAZ-INPUT-Text" size="50" maxlength="50"   onblur="toUpperCaseW3c(this);" /><font color="red">*</font>
                        </td>
                   </tr>
                   <tr> 
                        <td colspan="4" align="center"> 
                           <input type="button"  value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_INCLUIR_PROCESSO_EPROCESS%>" onClick="return adicionarTipoProcesso();"/>
                           <abaco:botaoCancelar/>
                        </td>
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