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
         
         <script>
         
            function incluirGiaDoacao()
            {
               document.form.action='<%=JspUtil.getContexto(request)%>giaitcd/giaitcddoacao/incluir';
               document.form.submit();
            }
            
            function validarFormularioOpcao1() {
               exibirBotaoSubmit();
               if(document.getElementById("BTN_RADIO_QUESTAO_1_1").checked){
                  document.getElementById("mensagem_bloqueio_1").style.display = "block";
               }else{            
                  document.getElementById("mensagem_bloqueio_1").style.display = "none";
                  
               }             
            }
            
            function validarFormularioOpcao2() {
               exibirBotaoSubmit();
               if(document.getElementById("BTN_RADIO_QUESTAO_2_1").checked){
                  document.getElementById("mensagem_bloqueio_2").style.display = "block";
               }else{            
                  document.getElementById("mensagem_bloqueio_2").style.display = "none";
               }             
            }
            
            function exibirBotaoSubmit(){
                 if(document.getElementById("BTN_RADIO_QUESTAO_1_2").checked && document.getElementById("BTN_RADIO_QUESTAO_2_2").checked ){
                     document.getElementById("btn_submit_form").style.display = "inline-table";
                 }else{
                     document.getElementById("btn_submit_form").style.display = "none";
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
             <td colspan="3" >Senhor Contribuinte, responda aos questionamentos abaixo, quanto a GIA-ITCD Doação que irá preencher:</td>
         </tr>
         
         <tr><td colspan="3" ><br/></td></tr>        
         
         <tr>
            <td width="80%" style="text-align: justify">
              Na Renúncia de Usufruto, se houver grandes melhorias feitas no bem pelo usufrutuário, a transmissão das mesmas ao nu-proprietário corresponderá doação plena (base de cálculo 100%), devendo ser apresentado o Anexo VI, da Portaria nº 177/2018, em substituição à GIA ITCD, pois a base de cálculo do imposto total devido deverá ser de 70% (setenta por cento) para o que já existia na instituição, somado a 100% (cem por cento) das grandes melhorias feitas.
            </td>
            <td width="20%">
                <input type="radio" name="BTN_RADIO_QUESTAO_1" id="BTN_RADIO_QUESTAO_1_1" onchange="validarFormularioOpcao1();" value="1">SIM
                <input type="radio" name="BTN_RADIO_QUESTAO_1" id="BTN_RADIO_QUESTAO_1_2" onchange="validarFormularioOpcao1();" value="2">NÃO<br>
            </td>
         </tr>
         <tr>
            <td colspan="3" style="color:red;">
               <br/>
               <div id="mensagem_bloqueio_1"  style="display:none;font-weight: bold;">
                      Neste caso favor preencher em substituição à GIA-ITCD-e o formulário "Declaração Manual do ITCD", Anexo IV, da Portaria 177/2018, realizando o protocolo da mesma via e-process (DECLARAÇÃO MANUAL DO ITCD - ANEXO IV DA PORTARIA 177_2018).
               </div>                
            </td>
         </tr>
         <tr><td colspan="3" ><br/></td></tr>           
         <tr> 
             <td colspan="3" >Com relação a débitos de cruzamento de dados SEFAZ x Receita Federal:</td>
         </tr>        
         <tr><td colspan="3" ><br/></td></tr>  
         <tr>
            <td width="80%" style="text-align: justify" >
               Se a doação recebida até dezembro do ano passado for de bens móveis (dinheiro em espécie, por exemplo) e ainda não houve a emissão de Aviso de Cobrança Fazendária, faça a denúncia espontânea, conforme exposto no capítulo 18 das "OrientaçõesGerais" disponíveis na página da SEFAZ/MT, Menu Serviços/ITCD.
            </td>
            <td width="20%">
                <input type="radio" name="BTN_RADIO_QUESTAO_2" id="BTN_RADIO_QUESTAO_2_1" onchange="validarFormularioOpcao2();" value="1">SIM
                <input type="radio" name="BTN_RADIO_QUESTAO_2" id="BTN_RADIO_QUESTAO_2_2" onchange="validarFormularioOpcao2();" value="2">NÃO<br>
            </td>
         </tr>
         <tr>
            <td colspan="3" style="color:red;">
               <br/>
               <div id="mensagem_bloqueio_2"  style="display:none;font-weight: bold;">
                     Neste caso favor preencher e-process de "Denúncia espontânea"(conforme capítulo 16, pág 28 das orientações gerais>ITCD).
               </div>                
            </td>
         </tr>
         <tr><td colspan="3" ><br/></td></tr>
         <tr>
            <td colspan="3" align="center" >
                  <abaco:botaoVoltar nomeContadorSubmit="incluirGIA"></abaco:botaoVoltar>                
                  <input type="button" id="btn_submit_form" style="display:none;"  value="Continuar" class="SEFAZ-INPUT-Botao" name="Continuar" onClick="incluirGiaDoacao();"/>         
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