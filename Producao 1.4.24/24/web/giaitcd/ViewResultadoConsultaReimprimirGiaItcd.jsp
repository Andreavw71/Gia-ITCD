<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
	/*
	* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
	* Arquivo : ViewResultadoConsultaReimprimirGiaItcd.jsp
	* Criação : Dezembro de 2007 / Maxwell Rocha
	* Revisão : Rogério Sanches de Oliveira
	* Log : 
	*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.giaitcd.Form"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
		<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
		<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
		<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
		<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
		<script type="text/javascript" language="javascript">
      var existeDoacao = <c:out value="${giaITCDVo.existeDoacaoSucessivaNaoRegParaBenef}" />;

		function validaFormulario()
		{	
      
         if (existeDoacao) {
            alert(
            "Constatada entrega de GIA ITCD do Doador para o(s) beneficiário(s) " +
            "desde a última alteração desta GIA. Por gentileza, utilize o módulo " +
            "'Alterar GIA ITCD' para revalidação dos cálculos antes da finalização " +
            "da GIA ITCD."
            );
            return false;
         }	
         if(confirm('Após a impressão da GIA não sera possível alterar novamente. Deseja realmente imprimir ?'))
         {
               document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_REIMPRIMIR+"=1"  %>';
               window.open('','relatorioGIAITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
               document.form.target = 'relatorioGIAITCD';
               document.form.submit();
         }
         else
         {
            return false;
         }
			
		}
		function obterArrayBotoes()
		{
			var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
			return new Array(botao1);
		}
		</script>
		<jsp:include page="/util/ViewVerificaErro.jsp"></jsp:include>
		<title><abaco:tituloSistema/></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro();" >
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
		<form name="form" method="POST" action="">

		<table width="740" border="0" cellspacing="1" cellpadding="0" align="center">
		<tr align="right"> 
         <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
      </tr> 
   		 <tr class="SEFAZ-TR-Titulo" align="center"> 
						<td colspan="2">GIA-ITCD </td>
			 </tr>
			 <tr> 
				<td class="SEFAZ-TD-RotuloSaida" width="278">N&uacute;mero da GIA:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="462"><c:out value="${giaITCDVo.codigo}" /></td>
			 </tr>
			 <tr> 
				<td class="SEFAZ-TD-RotuloSaida" width="278">Tipo de Processo:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="462"><c:out value="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.textoCorrente}" /></td>
			 </tr>
			 <tr>
				<td class="SEFAZ-TD-RotuloSaida">Declarante/Representante:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.nomeContribuinte}"  /></td>
			 </tr>
			 <tr>
				<td class="SEFAZ-TD-RotuloSaida">CPF/CNPJ:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDocumento}"  /></td>
			 </tr>
          <c:choose>
            <c:when test="${giaITCDVo.isGiaDoacao and quantidadeDoacaoSucessivaMaiorQueParametro}">
                  <tr>
                     <td colspan="2" ><center><font color="red">* Impressão não permitida! Existem duas ou mais GIA-ITCD-e de doação incluídas em data anterior a 06/08/2025. O Contribuinte deverá realizar a declaração utilizando o Sistema e-Process com o tipo de processo "ITCD - Denúncia Espontânea".</font></center></td>
                  </tr>
            </c:when>
            <c:otherwise>
               <tr>
                  <td colspan="2" ><center><font color="red">*Após a impressão da GIA não sera possível alterar novamente.<br/> Caso precise alterar os dados deverá fazer uma nova GIA.</font></center></td>
               </tr>
            </c:otherwise>
         </c:choose>          
	</table>
	<br>
  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
    <tr> 
      <td colspan="3"> 
  	    <div align="center">
         <c:choose>
            <c:when test="${giaITCDVo.isGiaDoacao and quantidadeDoacaoSucessivaMaiorQueParametro}">                  
            </c:when>
            <c:otherwise>
               <input name="<%=Form.BOTAO_REIMPRIMIR%>"  type="button"  value="<%=Form.TEXTO_BOTAO_IMPRIMIR%>"  class="SEFAZ-INPUT-Botao"   onClick="validaFormulario();"/>
            </c:otherwise>
         </c:choose>   
			<abaco:botaoVoltar nomeContadorSubmit="reimprimirGIAITCD" />
			<abaco:botaoCancelar/>
		</div>
		</td>
    </tr>
  </table>
</form>
<g:mostrarRodape/>
<center/>
</body>
</html>
