<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio.Form"%>
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
               if(!verificaCamposW3c(document.form.<%=Form.CAMPO_VALOR_MINIMO%>,<%="'"+MensagemErro.VALIDAR_CRITERIO_MUNICIPIO_VALOR_MINIMO+"'"%>))
               {
                  return false;
               }
               if(!verificaCamposW3c(document.form.<%=Form.CAMPO_VALOR_MEDIO%>,<%="'"+MensagemErro.VALIDAR_CRITERIO_MUNICIPIO_VALOR_MEDIO+"'"%>))
               {
                  return false;
               }
               if(!verificaCamposW3c(document.form.<%=Form.CAMPO_VALOR_MAXIMO%>,<%="'"+MensagemErro.VALIDAR_CRITERIO_MUNICIPIO_VALOR_MAXIMO+"'"%>))
               {
                  return false;
               }
               return true;
            }
            
            function adicionarCriterioMunicipio(codigoMunicipio)
            {
               if(validaFormulario())
               {
                  //desabilitarBotoes(obterArrayBotoes());
                  document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ADICIONAR_CRITERIO_MUNICIPIO+"="%>'+codigoMunicipio;
                  document.form.submit();
               }
            }
            
            function solicitarExcluirCriterioMunicipio(codigoCriterioMunicipio)
            {
               //desabilitarBotoes(obterArrayBotoes());
               document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_EXCLUIR_CRITERIO_MUNICIPIO+"="%>'+codigoCriterioMunicipio;
               document.form.submit();
               return true;
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
          <table class="SEFAZ-TABLE-Moldura" cellspacing="1" cellpadding="0" border="0" width="740" align="center">
             <c:if test="${criterioMunicipioVo.municipio.codgMunicipio != null}">
             <tr class="SEFAZ-TR-Titulo" align="center"> 
               <td colspan="3" >Incluir Critério Município</td>
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
                        <td class="SEFAZ-TR-Titulo" width="20%" align="center">&nbsp;</td>                 
                        <td class="SEFAZ-TR-Titulo" width="20%" align="center">Minímo</td>
                        <td class="SEFAZ-TR-Titulo" width="20%" align="center" >Médio</td>
                        <td class="SEFAZ-TR-Titulo" width="20%" align="center">Máximo</td>
                        <td class="SEFAZ-TR-Titulo" width="20%" align="center">&nbsp;</td>
                     </tr>
                     <tr>
                        <td  width="20%" align="center"><input disabled="true" maxlength="20" name="" size="20" value="<c:out value="${criterioMunicipioVo.municipio.nomeMunicipio}"/>"></input></td>                 
                        <td  width="20%" align="center"><abaco:campoMonetario quantidadeDigitosInteiros="8" name="<%=Form.CAMPO_VALOR_MINIMO%>" idCampo="<%=Form.CAMPO_VALOR_MINIMO%>" value="${criterioMunicipioVo.valorTotalMinimoFormatado}" size="12" /></td>
                        <td  width="20%" align="center"><abaco:campoMonetario  quantidadeDigitosInteiros="8" name="<%=Form.CAMPO_VALOR_MEDIO%>" idCampo="<%=Form.CAMPO_VALOR_MEDIO%>" value="${criterioMunicipioVo.valorTotalMedioFormatado}" size="12" /></td>
                        <td  width="20%" align="center"><abaco:campoMonetario quantidadeDigitosInteiros="8" name="<%=Form.CAMPO_VALOR_MAXIMO%>" idCampo="<%=Form.CAMPO_VALOR_MAXIMO%>" value="${criterioMunicipioVo.valorTotalMaximoFormatado}" size="12" /></td>
                        <td  width="20%" align="center"><input type="button"  value="<%=Form.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" name="Form.BOTAO_ADICIONAR_CRITERIO_MUNICIPIO" id="Form.BOTAO_ADICIONAR_CRITERIO_MUNICIPIO" onClick="javascript:void(adicionarCriterioMunicipio(<c:out value="${criterioMunicipioVo.municipio.codgMunicipio}"></c:out>));"/></td>
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
                  <td class="SEFAZ-TR-Titulo" colspan="1" width="20%" >Município</td>
                  <td class="SEFAZ-TR-Titulo" colspan="1" width="60%" >Valor Total do Imóvel/ha</td>
                  <td class="SEFAZ-TR-Titulo" colspan="1" width="20%" >&nbsp;</td>
            </tr>
            <tr>
               <td colspan="3">
                  <table class="SEFAZ-TABLE-Moldura" cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                     <tr >
                        <td class="SEFAZ-TR-Titulo" width="20%" align="center">&nbsp;</td>                 
                        <td class="SEFAZ-TR-Titulo" width="20%" align="center">Mínimo VTI/ha</td>
                        <td class="SEFAZ-TR-Titulo" width="20%" align="center" >Médio VTI/ha</td>
                        <td class="SEFAZ-TR-Titulo" width="20%" align="center">Máximo VTI/ha</td>
                        <td class="SEFAZ-TR-Titulo" width="20%" align="center">&nbsp;</td>
                     <tr>
                     <c:forEach items="${criterioMunicipioVo.collVO}" var="muni" varStatus="contador">
                           <abaco:linhaTabela>
                              <td width="20%" align="center"><c:out value="${muni.municipio.nomeMunicipio}"/></td>                 
                              <td width="20%" align="center"><c:out value="${muni.valorTotalMinimoFormatado}"/></td>
                              <td width="20%" align="center" ><c:out value="${muni.valorTotalMedioFormatado}"/></td>
                              <td width="20%" align="center"><c:out value="${muni.valorTotalMaximoFormatado}"/></td>
                              <td width="20%" align="center">
                                 <c:if test="${muni.codigo == 0}" >
                                     <a href="javascript:void(solicitarExcluirCriterioMunicipio(<c:out value="${muni.municipio.codgMunicipio}"></c:out>));">Inlcuir</a>&nbsp;&nbsp;
                                 </c:if>
                                 <c:if test="${muni.codigo != 0}" >
                                     <a href="javascript:void(solicitarExcluirCriterioMunicipio(<c:out value="${muni.municipio.codgMunicipio}"></c:out>));">Alterar</a>&nbsp;&nbsp;
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
      </body>
</html>