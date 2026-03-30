<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManterGIAITCDDetalharFichaImovelUrbano.jsp
* Cria??o : dezembro de 2007 / Jo?o Batista Padilha e Silva
* Revis?o : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro"%>
<%@page import ="br.gov.mt.sefaz.itc.model.giaitcd.fichabem.ficharebanholpm.FichaRebanhoLPMVo"%>
<%@page import ="br.gov.mt.sefaz.itc.model.giaitcd.fichabem.ficharebanholpm.Form"%>
<%@page import ="br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<jsp:useBean id="sefazDataHora" class="sefaz.mt.util.SefazDataHora" scope="page" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
<META HTTP-EQUIV=Cache-Control content=no-store>
<META HTTP-EQUIV=Pragma content=no-cache>

<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}"/>/estilos/bootstrap.min.css"/> 
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}"/>/estilos/chosen.min.css">

<script src="<c:out value="${pageContext.request.contextPath}"/>/javascript/jquery.min.js" type="text/javascript"></script>
<script src="<c:out value="${pageContext.request.contextPath}"/>/javascript/chosen.jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
<%--<script src="<c:out value="${pageContext.request.contextPath}"/>/javascript/itcd.js" language="javascript" type="text/javascript"></script>--%>
<script type="text/javascript">
   $(document).ready(function(){
		$("select").chosen({allow_single_deselect:true});
		document.getElementById("campoConcordaComValorArbiradoSelecionadoContrituinte_chosen").style.width = "20%";		
   });
</script>
<script type="text/javascript" language="javascript">	


/*
		Fun??o que complementa a fun??o calcular() (acima) formatando o resultado do c?lculo.
		Esta fun??o ? utilizada para formatar o resultado do c?lculo Valor Unit?rio x Quantidade.
		@param: valor - n?mero a ser formatado.
		@return: valorFormatado (moeda real)
		@autor: Maxwell
*/

function floatTomoeda(valor) 
		{	
			x = 0;
			
			if(valor<0)
			{
				valor = Math.abs(valor);
				x = 1;
			}  
			if(isNaN(valor)) valor = "0";
				cents = Math.floor((valor*100+0.5)%100);		
				valor = Math.floor((valor*100+0.5)/100).toString();		
			if(cents < 10) 
				cents = "0" + cents;
			for (var i = 0; i < Math.floor((valor.length-(1+i))/3); i++)
				valor = valor.substring(0,valor.length-(4*i+3))+'.'+valor.substring(valor.length-(4*i+3));   
			ret = valor + ',' + cents;   
			if (x == 1)
				ret = ' - ' + ret;
			return ret;	
}
 function buscarValorRebanho()
 {   
   var TEXTO_SELECIONE = "Selecione";
   var campoListaPreco = document.getElementById('<%=Form.CAMPO_LISTA_PRECO_MINIMO%>');      
   var SELECIONE = campoListaPreco.options[campoListaPreco.selectedIndex].text;
   
   if(SELECIONE == TEXTO_SELECIONE)
   {
    alert(<%="'"+MensagemErro.FAVOR_SELECIONE_UM_REBANHO+"'"%>);
    return false;
   }
   
   if(!verificaCamposW3c(document.form.<%=Form.CAMPO_LISTA_PRECO_MINIMO%> ,<%="'"+MensagemErro.FAVOR_SELECIONE_UM_REBANHO+"'"%>))
 	{
		return false;
	}  
    document.getElementById('tableValorTributavel').style.display = "none";
    document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR_REBANHO+"=44"%>';
    document.form.submit();	
   
 }
 function processarFichaRebanho()
 {  
    if(!verificaCamposW3c(document.form.<%=Form.CAMPO_LISTA_PRECO_MINIMO%> ,<%="'"+MensagemErro.FAVOR_SELECIONE_UM_REBANHO+"'"%>))
 	{ 
		return false;
	}
   if(!verificaCamposW3c(document.form.<%=Form.QUANTIDADE_DE_CABECA%> ,<%="'"+MensagemErro.FAVOR_IFORME_QUANTIDADE+"'"%>))
 	{ 
		return false;
	}
    if(!verificaCamposW3c(document.form.<%=Form.VALOR_DECLARADO_REBANHO%> ,<%="'"+MensagemErro.FAVOR_IFORMAR_VALOR_DECLARADO+"'"%>))
 	{
		return false;
	}   	
    document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.CAMPO_ADICIONAR_REBANHO+"=45"%>';
    document.form.submit();		 
 }
  function alterarFichaRebanho()
 {   
  if(!verificaCamposW3c(document.form.<%=Form.QUANTIDADE_DE_CABECA%> ,<%="'"+MensagemErro.FAVOR_IFORME_QUANTIDADE+"'"%>))
 	{ 
		return false;
	}
    if(!verificaCamposW3c(document.form.<%=Form.VALOR_DECLARADO_REBANHO%> ,<%="'"+MensagemErro.FAVOR_IFORMAR_VALOR_DECLARADO+"'"%>))
 	{
		return false;
	}   
	if(document.getElementById('concordaValorAbitrado').style.display == "")
		{		
      if(document.getElementById('ValorIgual').style.display == ""){
         
      }else{
      var campoConcorda = document.getElementById('<%=Form.CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE%>');      
      var campoConcordaSelecionado = campoConcorda.options[campoConcorda.selectedIndex].text	
		
            if(campoConcordaSelecionado.includes('Selecione')){
               alert('Favor informar se concorda com o valor arbitrado.');   
               return false;
            }
        }
		}
      
    document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.CAMPO_ALTERAR_REBANHO+"=46"%>';
    document.form.submit();		
 }
 
 function calcularValorTributavelRebanho()
 {
 
      var valorDeclarado = document.form.<%=Form.VALOR_DECLARADO_REBANHO%>.value;     
      var valorLpm = '<c:out value="${fichaRebanhoLPMVo.produtoNcmIntegracaoVo.valorProdutoUnitarioFormatado}"/>';
      //document.form.<%=Form.VALOR_IPM_REBANHO%>.value = valorLpm;
      var quantidadeRebanho = document.form.<%=Form.QUANTIDADE_DE_CABECA%>.value;
      
      valorDeclarado = replaceAll(valorDeclarado, ".", "");
      valorDeclarado = replaceAll(valorDeclarado, ",", ".");
      valorLpm = replaceAll(valorLpm, ".", "");
      valorLpm = replaceAll(valorLpm, ",", ".");
      
      var valorCalculado = valorLpm * quantidadeRebanho;           
      // se o valor maior declarado for maior que o calculado o preço vai no que foi declarado e nao calculado
      if((trimW3c(valorDeclarado) != "") || (trimW3c(valorCalculado) != ""))
      {
         if(parseFloat(valorDeclarado) >= parseFloat(valorCalculado)){
            document.getElementById('<%=Form.VALOR_TRIBUTAVEL_REBANHO%>').innerHTML = floatTomoeda(valorDeclarado);      
            document.getElementById('mensagemValorTributavel').innerHTML = "";
            document.getElementById('concordaValorAbitrado').style.display = "";  
            document.getElementById('ValorCalculado').style.display = "none";
            document.getElementById('ValorIgual').style.display = "";
            document.form.<%=Form.CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE%>.value = 'SIM';

         }else{
            document.getElementById('<%=Form.VALOR_TRIBUTAVEL_REBANHO%>').innerHTML = floatTomoeda(valorCalculado); 
            document.getElementById('mensagemValorTributavel').innerHTML = '<img src="/imagens/warning2.png" width="16" height="16"><img><font color="red">Valor Calculado pelo Sistema<font>';            
            document.getElementById('concordaValorAbitrado').style.display = "";   
            document.getElementById('ValorIgual').style.display = "none";
            document.getElementById('ValorCalculado').style.display = ""; 
            
		 }
          //document.getElementById('tableValorTributavel').style.display = "";
      }
    }   
   
    
</script> 

 
<jsp:include page="/util/ViewVerificaErro.jsp"/>
<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
</head>

<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');calculaValorTributavel();verificaErro();calcularValorTributavelRebanho();">
	<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
	<CENTER>
	<FORM method="POST" action="<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>" name="form">		
   
		<table cellspacing="0"  cellpadding="0" border="0" width="740" align="center">
			<tr>
				<td colspan="4"><div align="center"><font face="Verdana, Arial, Helvetica, sans-serif" size="3"><b>Ficha Rebanho</b></font></div></td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>	
         <%--
			 <tr align="right">
					<td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
               
			 </tr>--%>          
        
			<tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="2">Dados do Rebanho</td>			
			<tr>
       <td>&nbsp;</td>
       </tr>
       <tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="278">Descrição Lista Preço Minimo :&nbsp;</td>
				<td class="SEFAZ-TD-ComboBox" width="462">              
            <select name="<%=Form.CAMPO_LISTA_PRECO_MINIMO%>" id="<%=Form.CAMPO_LISTA_PRECO_MINIMO%>" onchange="buscarValorRebanho();" class="chzn-select" style="width:350px;" tabindex="2" >
          
               <option  value="" selected>Selecione</option><%--<%=Form.SELECIONE%>--%>
                    <c:forEach var="produtoNcmVo" items="${fichaRebanhoLPMVo.produtoNcmIntegracaoVo.collVO}">           
                       <c:if test="${fichaRebanhoLPMVo.descricao == produtoNcmVo.descProduto}">
                        <option value="<c:out value="${produtoNcmVo.codgProdutoSeqc}"></c:out>" selected="selected"><c:out value="${produtoNcmVo.descProduto}"></c:out></option>	
                      </c:if>
                      <c:if test="${fichaRebanhoLPMVo.descricao != produtoNcmVo.descProduto}">
                        <option value="<c:out value="${produtoNcmVo.codgProdutoSeqc}"></c:out>"><c:out value="${produtoNcmVo.descProduto}"></c:out></option>
                      </c:if>
                  </c:forEach>
            </select><font color="red">*</font>
				<td>&nbsp;</tr>
         <tr>
           <td>&nbsp;</td>        
         
          <tr>
            <td class="SEFAZ-TD-RotuloEntrada" width="278">Quantidade de Cabe&ccedil;a :&nbsp;</td>
            <td class="SEFAZ-TD-CampoEntrada" width="200">
              <abaco:campoApenasNumero mostrarZero="false" maxlength="12"
                                       name="<%=Form.QUANTIDADE_DE_CABECA%>"
                                       size="20"
                                       value="${fichaRebanhoLPMVo.valorQuantidadeFormatado}"
                                       onBlur="toUpperCaseW3c(this),calcularValorTributavelRebanho();"></abaco:campoApenasNumero>
              <font color="red">*</font>
              <input type="hidden" name="<%=Form.VALOR_IPM_REBANHO%>" value='<c:out value="${fichaRebanhoLPMVo.produtoNcmIntegracaoVo.valorProdutoUnitario}"/>'/>              
            </td>
          </tr>
          <tr>
           <td>&nbsp;</td>
         
          <tr>
            <td class="SEFAZ-TD-RotuloEntrada" width="278">Valor total do Rebanho:&nbsp;</td>
            <td>
              <abaco:campoMonetario name="<%=Form.VALOR_DECLARADO_REBANHO%>"
                                    idCampo="<%=Form.VALOR_DECLARADO_REBANHO%>"
                                    quantidadeDigitosInteiros="8" size="15"
                                    value="${fichaRebanhoLPMVo.valorInformadoFormatado}"
                                    quantidadeCasasDecimais="2"
                                    onBlur="toUpperCaseW3c(this),calcularValorTributavelRebanho();"/>
              <font color="red">*&nbsp;</font>
            </td>
          </tr>
          <tr>
           <td>&nbsp;</td>
         </table>
          <%--<div id="tableValorTributavel" style="display:none">--%>   
			<table cellspacing="0"  cellpadding="0" border="0" width="740" align="center">
			  <tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="278">Valor arbitrado para o Rebanho:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" width="100" id="<%=Form.VALOR_TRIBUTAVEL_REBANHO%>">&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" id="mensagemValorTributavel"></td>          
			  </tr>
			  <tr>
			   <td>&nbsp;</td>         
			</table>
         <%--</div>--%>   
         
		 <div style="display:none;" id="concordaValorAbitrado">    
       
            <div style="display:none" id="ValorIgual">            
               <jsp:include page="/giaitcd/util/ViewIncludeValoresIguais.jsp"/>            
            </div>
            
            <div style="display:none" id="ValorCalculado">
               <jsp:include page="/giaitcd/util/ViewIncludeConcordaPagamento.jsp"/>               
            </div> 
            
            </br>
		 </div>
       
       <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
     <tr>
     	<td  colspan="2" align="center"  width="370">
				<abaco:botaoCancelar></abaco:botaoCancelar>
            <c:if test="${not fichaRebanhoLPMVo.bemTributavelVo.alterar}">              
         	<input  name="<%=Form.CAMPO_ADICIONAR_REBANHO%>" align="center" value="<%=Form.CAMPO_ADICIONAR_REBANHO%>" type="button" class="SEFAZ-INPUT-Botao" id="<%=Form.CAMPO_ADICIONAR_REBANHO%>" onClick="processarFichaRebanho();" />
				</c:if>
				<c:if test="${fichaRebanhoLPMVo.bemTributavelVo.alterar}">             
					 <input  name="<%=Form.CAMPO_ALTERAR_REBANHO%>" align="center" value="<%=Form.TEXTO_BOTAO_ALTERAR%>" type="button" class="SEFAZ-INPUT-Botao" id="<%=Form.CAMPO_ALTERAR_REBANHO%>" onClick="alterarFichaRebanho();" />
				</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2"><font color="red"><b>* Campos Obrigatorios</b></font></td>
			</tr>
			<tr> 
				<td colspan="2">&nbsp;</td>
			</tr>
         </table>
     	</form>
	</CENTER>
	<g:mostrarRodape></g:mostrarRodape>
</body>
</html>
