<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsulta"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsulta"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>


<html>
    <head>
        <title><abaco:tituloSistema></abaco:tituloSistema></title>
        <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
        <META HTTP-EQUIV=Cache-Control content=no-store>
        <META HTTP-EQUIV=Pragma content=no-cache>
        <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
        <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>        
        <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
        <script type="text/javascript" language="javascript">
                 
         function obterArrayBotoes()
		   {
			  var botaoConfirmar = document.form.<%=Form.BOTAO_INCLUIR_PROCESSO_EPROCESS%>;			
			  return new Array(botaoConfirmar);
		   }
         
         function validaFormulario()
         {
            if(!verificaCamposW3c(document.form.<%=Form.TIPO_PROCESSO_EPROCESS%>,<%="'"+MensagemErro.FAVOR_SELECIONAR_TIPO_PROCESSO+"'"%>))
            {
                 return false;
            }
               return true;
         }
         
         function validarProtocoloGIA()
         {
            if(validaFormulario())
            {
               desabilitarBotoes(obterArrayBotoes());
               document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_INCLUIR_PROCESSO_EPROCESS+"=1"%>';
               document.form.submit();
            }
         }
      </script>
   </head>
   <body class="SEFAZ-Body">
      <!-- padrao sefaz - cabeçalho e página de erro -->
      <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
      <!-- padrao sefaz - cabeçalho e página de erro -->
      <form name="form" method="POST" action="#">

         <table cellspacing="1" cellpadding="0" border="0" width="726" align="center">
            <tr class="SEFAZ-TR-Titulo">
               <td>Dados do Processo</td>
            </tr>            
          </table>
          <br>
          <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">    
          
               <tr>
                  <td class="SEFAZ-TD-RotuloEntrada" width="370">Assunto:</td>
                     <td>
                        <select name="<%=Form.CAMPO_TIPOS_DE_PROCESSOS%>">
                            <c:forEach var="tipoProcessoVo" items="${tipoProcessoVo.collVO}" begin="0" end="0">
                                <option value="<c:out value='${tipoProcessoVo.codigo}'/>" selected>
                                    <c:out value="${tipoProcessoVo.descTipoProcesso}"/>
                                </option>
                            </c:forEach>
                        </select>    
                     </td>
               </tr>         
            
            <tr>
               <td class="SEFAZ-TD-RotuloEntrada" width="370"><div align="right">Tipo de Processo: </div> </td>
               
               <td class="SEFAZ-TD-CampoEntrada">     
                   <abaco:campoSelectDominio ajuda="" mostrarSelecione="false"
															classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoEprocess" 
															name="<%=Form.TIPO_PROCESSO_EPROCESS%>" 
															tabIndex="1" opcaoSelecionada="${tipoProcessoVo.domnTipoEprocess.valorCorrente}" /><font color="red">*</font>
               </td>   
            </tr>
            </table>
            <br>
             <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
             <tr>
               <td align="center">
                  <input type="button" onClick="javascript: validarProtocoloGIA();" name="<%=Form.BOTAO_INCLUIR_PROCESSO_EPROCESS%>"  id="<%=Form.BOTAO_INCLUIR_PROCESSO_EPROCESS%>"  value="Incluir Processo E-Process" class="SEFAZ-INPUT-Botao"/>
                  <abaco:botaoCancelar nomeBotao="  Cancelar  " />
               <td>
            </tr>
            <tr>
        <td colspan="4"><h4><font color="red">* Campos Obrigatórios</font></h4></td>
        </tr>
         </table>     
         <abaco:mensagemAguardeCarregando/>
      </form>
   </body>
   <g:mostrarRodape/>
</html>