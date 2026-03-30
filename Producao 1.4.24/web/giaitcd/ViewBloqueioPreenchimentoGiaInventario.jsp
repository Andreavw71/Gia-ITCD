<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsulta"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%
	pageContext.setAttribute("DOACAO", new Integer(DomnTipoProcesso.DOACAO));
   pageContext.setAttribute("INVENTARIO_ARROLAMENTO", new Integer(DomnTipoProcesso.INVENTARIO_ARROLAMENTO));
	pageContext.setAttribute("SEPARACAO_DIVORCIO_PARTILHA", new Integer(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA));
%>
<%@ page import="sefaz.mt.arrecadacao.integracao.IntegracaoArrecadacao"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
   <head>
      <title><abaco:tituloSistema></abaco:tituloSistema></title>
         <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
         <META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
         <META HTTP-EQUIV=Cache-Control content=no-store>
         <META HTTP-EQUIV=Pragma content=no-cache>
         <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
         
         <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script> 
         <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
         <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
                
         <script type="text/javascript">
         function incluirGiaInventario()
         {
            document.form.action='<%=JspUtil.getContexto(request)%>giaitcd/giaitcdinventarioarrolamento/incluir';
            document.form.submit();
         }
            function validarFormulario() { 
            
               if(document.getElementById("BTN_RADIO_QUESTAO_8_2").checked){               
                  document.getElementById("mensagem_importante").style.display = "block";      
                  document.getElementById("mensagem_bloqueio").style.display = "none";
               }
            
               if(document.getElementById("BTN_RADIO_QUESTAO_1_1").checked ||
                  //document.getElementById("BTN_RADIO_QUESTAO_2_1").checked ||
                  document.getElementById("BTN_RADIO_QUESTAO_3_1").checked ||
                  document.getElementById("BTN_RADIO_QUESTAO_4_1").checked ||
                  document.getElementById("BTN_RADIO_QUESTAO_5_1").checked ||
                  document.getElementById("BTN_RADIO_QUESTAO_6_1").checked ||
                  document.getElementById("BTN_RADIO_QUESTAO_7_1").checked ||
                  document.getElementById("BTN_RADIO_QUESTAO_8_1").checked){
                                                     
                  document.getElementById("mensagem_bloqueio").style.display = "block";    
                  document.getElementById("mensagem_importante").style.display = "none";
                  document.getElementById("btn_submit_form").style.display = "none";                                  
                  
               }else{            
                  document.getElementById("mensagem_bloqueio").style.display = "none";                  
               }
                                            
               
               if(document.getElementById("BTN_RADIO_QUESTAO_1_2").checked &&
                  //document.getElementById("BTN_RADIO_QUESTAO_2_2").checked &&
                  document.getElementById("BTN_RADIO_QUESTAO_3_2").checked &&
                  document.getElementById("BTN_RADIO_QUESTAO_4_2").checked &&
                  document.getElementById("BTN_RADIO_QUESTAO_5_2").checked &&
                  document.getElementById("BTN_RADIO_QUESTAO_6_2").checked &&
                  document.getElementById("BTN_RADIO_QUESTAO_7_2").checked &&
                  document.getElementById("BTN_RADIO_QUESTAO_8_2").checked ){
                                                     
                  document.getElementById("mensagem_bloqueio").style.display = "none";         
                  document.getElementById("mensagem_importante").style.display = "block";
                  document.getElementById("btn_submit_form").style.display = "inline-table";                                   
                    
               }   
                                                                
                                         
            }
            
                             
         </script>       
   </head>
   <body class="SEFAZ-Body" onload=" verificaErro();" >
      <!-- padrao sefaz - cabeçalho e página de erro -->
      <g:mostrarCabecalho tituloSistema="ITCD" tituloFuncionalidade=""/>
      <jsp:include page="/util/ViewVerificaErro.jsp"/>
      <!-- padrao sefaz - cabeçalho e página de erro -->
     
     <form name="form" method="POST" action="#"> 
      <table class="SEFAZ-TABLE-Moldura" cellspacing="0" cellpadding="0" border="0" width="740" align="center">
         <tr class="SEFAZ-TR-Titulo" align="center"> 
             <td colspan="3" >Tipo de GIA-ITCD</td>
         </tr>
         
         <tr><td colspan="3" ><br/></td></tr>    
         
         <tr> 
             <td colspan="3" >Senhor Contribuinte, a fim de saber se o ITCD deve ser declarado de forma manual, responda aos questionamentos abaixo, quanto à GIA-ITCD que irá preencher:</td>
         </tr>
         
         <tr><td colspan="3" ><br/></td></tr>        
         
         <tr>
            <td width="80%">
               O falecido deixou testamento?
            </td>
            <td width="20%">
                <input type="radio" name="BTN_RADIO_QUESTAO_1" id="BTN_RADIO_QUESTAO_1_1" onchange="validarFormulario();" value="1">SIM
                <input type="radio" name="BTN_RADIO_QUESTAO_1" id="BTN_RADIO_QUESTAO_1_2" onchange="validarFormulario();" value="2">NÃO<br>
            </td>
         </tr>
         <%--
         <tr><td colspan="3" ><br/></td></tr>
         <tr>
            <td width="80%">
              Existe companheiro (a)/convivente (cuja união estável deverá ser comprovada através de declaração em cartório ou declaração dos herdeiros legítimos) ?
            </td>
            <td width="20%">
                <input type="radio" name="BTN_RADIO_QUESTAO_2" id="BTN_RADIO_QUESTAO_2_1" onchange="validarFormulario();" value="1">SIM
                <input type="radio" name="BTN_RADIO_QUESTAO_2" id="BTN_RADIO_QUESTAO_2_2" onchange="validarFormulario();" value="2">NÃO<br>
            </td>
         </tr>
         --%>
         <tr><td colspan="3" ><br/></td></tr>
         <tr>
            <td width="80%">
              A data de falecimento é anterior a 11 de janeiro de 2003, ele era casado no regime de comunhão parcial de bens e tinha bens particulares?
            </td>
            <td width="20%">
                <input type="radio" name="BTN_RADIO_QUESTAO_3" id="BTN_RADIO_QUESTAO_3_1" onchange="validarFormulario();" value="1">SIM
                <input type="radio" name="BTN_RADIO_QUESTAO_3" id="BTN_RADIO_QUESTAO_3_2" onchange="validarFormulario();" value="2">NÃO<br>
            </td>
         </tr>
         
         <tr><td colspan="3" ><br/></td></tr>
         <tr>
            <td width="80%">
              A data de falecimento é igual ou posterior a 11 de janeiro de 2003, ele era casado no regime de comunhão parcial de bens, tinha bens particulares e 4 (quatro) ou mais herdeiros?
            </td>
            <td width="20%">
                <input type="radio" name="BTN_RADIO_QUESTAO_4" id="BTN_RADIO_QUESTAO_4_1" onchange="validarFormulario();" value="1">SIM
                <input type="radio" name="BTN_RADIO_QUESTAO_4" id="BTN_RADIO_QUESTAO_4_2" onchange="validarFormulario();" value="2">NÃO<br>
            </td>
         </tr>
         
         <tr><td colspan="3" ><br/></td></tr>
         <tr>
            <td width="80%">
              A data de falecimento é igual ou posterior a 11 de janeiro de 2003, ele era casado no regime de comunhão parcial de bens, tinha bens particulares e o cônjuge sobrevivente deseja renunciar abdicativamente (sem indicar beneficiário específico) à herança (parte a que teria direito dos bens particulares do de cujus)?
            </td>
            <td width="20%">
                <input type="radio" name="BTN_RADIO_QUESTAO_5" id="BTN_RADIO_QUESTAO_5_1" onchange="validarFormulario();" value="1">SIM
                <input type="radio" name="BTN_RADIO_QUESTAO_5" id="BTN_RADIO_QUESTAO_5_2" onchange="validarFormulario();" value="2">NÃO<br>
            </td>
         </tr>
         
         <tr><td colspan="3" ><br/></td></tr>
         <tr>
            <td width="80%">
              Há sobrepartilha e já houve recolhimento parcial do ITCD?
            </td>
            <td width="20%">
                <input type="radio" name="BTN_RADIO_QUESTAO_6" id="BTN_RADIO_QUESTAO_6_1" onchange="validarFormulario();" value="1">SIM
                <input type="radio" name="BTN_RADIO_QUESTAO_6" id="BTN_RADIO_QUESTAO_6_2" onchange="validarFormulario();" value="2">NÃO<br>
            </td>
         </tr>
         
         <tr><td colspan="3" ><br/></td></tr>
         <tr>
            <td width="80%">
              O falecido era casado no regime de separação total de bens ou separação obrigatória de bens?
            </td>
            <td width="20%">
                <input type="radio" name="BTN_RADIO_QUESTAO_7" id="BTN_RADIO_QUESTAO_7_1" onchange="validarFormulario();" value="1">SIM
                <input type="radio" name="BTN_RADIO_QUESTAO_7" id="BTN_RADIO_QUESTAO_7_2" onchange="validarFormulario();" value="2">NÃO<br>
            </td>
         </tr>
         
         <tr><td colspan="3" ><br/></td></tr>
         <tr>
            <td width="80%">
              Existe herdeiro falecido em data anterior ao "de cujus" e que tenha MAIS DE UM beneficiário por representação?
            </td>
            <td width="20%">
                <input type="radio" name="BTN_RADIO_QUESTAO_8" id="BTN_RADIO_QUESTAO_8_1" onchange="validarFormulario();" value="1">SIM
                <input type="radio" name="BTN_RADIO_QUESTAO_8" id="BTN_RADIO_QUESTAO_8_2" onchange="validarFormulario();" value="2">NÃO<br>
            </td>
         </tr>                 
         
         <tr><td colspan="3" ><br/></td></tr>
         <tr>
            <td colspan="3" style="color:red;">
               <div id="mensagem_bloqueio"  style="display:none;">
                      Neste caso, em substituição à GIA-ITCD-e, será necessário preencher o formulário "Declaração Manual do ITCD? e realizar o protocolo da Declaração pelo Sistema e-Process (DECLARAÇÃO MANUAL DO ITCD).
               </div>                
            </td>
         </tr>
         
         <tr><td colspan="3" ><br/></td></tr>
         <tr>
            <td colspan="3" style="color:blue;">
               <div id="mensagem_importante"  style="display:none;">
                      <b>Observação:</b> Se um herdeiro, ou herdeiros, tiver falecido antes do "de cujus" e possuír apenas um sucessor, inclua as informações desse sucessor no lugar do herdeiro original.
               </div>                
            </td>
         </tr>

         <tr><td colspan="3" ><br/></td></tr>
         <tr>
            <td colspan="3" align="center" >
                  <abaco:botaoVoltar nomeContadorSubmit="incluirGIA"></abaco:botaoVoltar>
                  <input type="button" id="btn_submit_form" style="display:none;"  value="Continuar" class="SEFAZ-INPUT-Botao" name="Continuar" onClick="incluirGiaInventario();"/>

            </td>
         </tr>
         
      </table>
      <!-- FIM: botões de ações -->
      <br/>
      <!-- bloco de mensagem -->
      <abaco:mensagemAguardeCarregando></abaco:mensagemAguardeCarregando>
      <!-- FIM: bloco de mensagem -->
      <g:mostrarRodape/>
      </form>
   </body>
</html>