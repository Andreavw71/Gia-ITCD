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
            function incluirGiaSeparacaoPartilha()
            {
               document.form.action='<%=JspUtil.getContexto(request)%>giaitcd/giaitcdseparacaodivorcio/incluir';
               document.form.submit();
            }
         
         
         function validarFormularioOpcao1() {
               if(document.getElementById("BTN_RADIO_QUESTAO_1_1").checked ||
                  document.getElementById("BTN_RADIO_QUESTAO_2_1").checked){
                  document.getElementById("mensagem_bloqueio_1").style.display = "block";
                  document.getElementById("btn_submit_form").style.display = "none";
               }else{
                  document.getElementById("mensagem_bloqueio_1").style.display = "none";
               }
               
               if(document.getElementById("BTN_RADIO_QUESTAO_1_2").checked && 
                  document.getElementById("BTN_RADIO_QUESTAO_2_2").checked){            
                  document.getElementById("mensagem_bloqueio_1").style.display = "none";
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
             <td colspan="3" >Senhor Contribuinte, responda aos questionamentos abaixo, quanto a GIA-ITCD Separação/Divórcio/Partilha que irá preencher:</td>
         </tr>
         
         <tr><td colspan="3" ><br/></td></tr>        
         
         <tr>
            <td width="80%">
             Houver partilha desigual de bens, com excesso de meação para um dos cônjuges ou não, a ser verificado após a avaliação administrativa da SEFAZ/MT, e no patrimônio do casal existirem bens cuja competência para cobrança do imposto de transmissão sejam de outras Unidades da Federação - bens imóveis ; bens móveis e cônjuge doador domiciliado em outra Unidade da Federação.
            </td>
            <td width="20%">
                <input type="radio" name="BTN_RADIO_QUESTAO_1" id="BTN_RADIO_QUESTAO_1_1" onchange="validarFormularioOpcao1();" value="1">SIM
                <input type="radio" name="BTN_RADIO_QUESTAO_1" id="BTN_RADIO_QUESTAO_1_2" onchange="validarFormularioOpcao1();" value="2">NÃO<br>
            </td>
         </tr>
         
         <tr><td colspan="3" ><br/></td></tr>
         
         <tr>
            <td width="80%">
             O regime de casamento é separação total de  bens ou separação obrigatória de bens?
            </td>
            <td width="20%">
                <input type="radio" name="BTN_RADIO_QUESTAO_2" id="BTN_RADIO_QUESTAO_2_1" onchange="validarFormularioOpcao1();" value="1">SIM
                <input type="radio" name="BTN_RADIO_QUESTAO_2" id="BTN_RADIO_QUESTAO_2_2" onchange="validarFormularioOpcao1();" value="2">NÃO<br>
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
            <td colspan="3" align="center" >
                  <abaco:botaoVoltar nomeContadorSubmit="incluirGIA"></abaco:botaoVoltar>                
                  <input type="button" id="btn_submit_form" style="display:none;"  value="Continuar" class="SEFAZ-INPUT-Botao" name="Continuar" onClick="incluirGiaSeparacaoPartilha();"/>
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