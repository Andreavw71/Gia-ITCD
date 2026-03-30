<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<%@page import ="br.gov.mt.sefaz.itc.model.giaitcd.fichabem.fichaveiculo.FichaVeiculoVo"%>
<%@page import ="br.gov.mt.sefaz.itc.model.giaitcd.fichabem.fichaveiculo.Form"%>
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
 
 
       function pesquisarVeiculoMT(){   
          if(!verificaCamposW3c(document.form.<%=Form.CAMPO_RENAVAN_VEICULO%>,<%="'"+MensagemErro.FAVOR_INFORMA_O_RENAVAN+"'"%>))
          {
               return false;
          }
		  
          document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR_VEICULO+"=39"%>';
          document.form.submit();		
       }
 
 
   function pesquisarVeiculoOutrosEstados()
   {   
      if(!verificaCamposW3c(document.form.<%=Form.CAMPO_MARCA_VEICULO%>,<%="'"+MensagemErro.FAVOR_INFORMAR_MARCA+"'"%>))
      {
          return false;    
      }
	
	
      if(!verificaCamposW3c(document.form.<%=Form.CAMPO_UF_VEICULO_OUTROS_ESTADOS%>,<%="'"+MensagemErro.FAVOR_INFORMAR_MODELO+"'"%>))
      {
          return false;    
      }
	
       document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR_VEICULO+"=39"%>';
       document.form.submit();		
   }
   
   function validaFormVeiculo(){
		var MT = "MT"
		var estadoVeiculo = document.getElementById('<%=Form.CAMPO_UF_VEICULO%>');      
		var esttadoSelecionado = estadoVeiculo.options[estadoVeiculo.selectedIndex].text
         
		
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_UF_VEICULO%>,<%="'"+MensagemErro.FAVOR_INFORMA_UF_VEICULO+"'"%>))
		{
		  return false;
		}
		if(esttadoSelecionado == MT)
		{
		  if(!verificaCamposW3c(document.form.<%=Form.CAMPO_RENAVAN_VEICULO%>,<%="'"+MensagemErro.FAVOR_INFORMA_O_RENAVAN+"'"%>))
		  {
			   return false;
		  }         
		}
		else 
		{
		
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_MARCA_VEICULO%>,<%="'"+MensagemErro.FAVOR_INFORMAR_MARCA+"'"%>))
			{
			  return false;    
			}
			
			
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_UF_VEICULO_OUTROS_ESTADOS%>,<%="'"+MensagemErro.FAVOR_INFORMAR_MODELO+"'"%>))
			{
				return false;    
			}
		}
		
		
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_VALOR_DECLARADO%>,<%="'"+MensagemErro.FAVOR_INFORMA_O_VALOR_DECLARADO+"'"%>))
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
      
      var valorVeiculoDeclarado = document.form.<%=Form.CAMPO_VALOR_DECLARADO%>.value;
      var valorVenalCalculado = '<c:out value="${fichaVeiculoVo.valorVenalFormatado}"/>';

      if(valorVeiculoDeclarado == '0,00'){
         alert('Valor declarado não pode ser zero.');   
         return false;
      }
      
      if(parseFloat(valorVeiculoDeclarado) < parseFloat(valorVenalCalculado)){
         alert('Valor declarado não pode ser menor que o valor venal do veículo.');   
         return false;
      }
      
		return true;
   }
      
  function processarFichaVeiculo(){  
  
   if(validaFormVeiculo()){
	 document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.CAMPO_ADICIONAR_VEICULO+"=40"%>';
    document.form.submit();		
   }
     
 }
 
 function voltarBemTributavel()
 {
    document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_VOLTAR_FICHA_VEICULO+"=41"%>';
    document.form.submit();	
 }
 
  function alterarFichaVeiculo()
 {      
	if(validaFormVeiculo()){
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.CAMPO_ALTERAR_VEICULO+"=42"%>';
		document.form.submit();	
	}
 }
 
 function calcularValorTributavelIpva(){
      var valorDeclarado = document.form.<%=Form.CAMPO_VALOR_DECLARADO%>.value;     
      var valorVenal = '<c:out value="${fichaVeiculoVo.valorVenalFormatado}"/>';
      valorDeclarado = replaceAll(valorDeclarado, ".", "");
      valorDeclarado = replaceAll(valorDeclarado, ",", ".");
      valorVenal = replaceAll(valorVenal, ".", "");
      valorVenal = replaceAll(valorVenal, ",", ".");
	  
	 
	  var elementos = document.getElementsByClassName("radioConcordaPagamento");
	  var i;
	  for (i = 0; i < elementos.length; i++)
	  {
		 elementos[i].checked = ""; 
	  }
	  
	  
      if((trimW3c(valorDeclarado) != "") && (trimW3c(valorVenal) != ""))
      {
         if(parseFloat(valorDeclarado) >= parseFloat(valorVenal)){
            document.getElementById('<%=Form.CAMPO_VALOR_TRIBUTAVEL%>').innerHTML = floatTomoeda(valorDeclarado);   
            document.getElementById('campoValor').innerHTML = parseFloat(valorDeclarado);
            document.getElementById('tableValorArbitrado').style.display  = "";
            document.getElementById('mensagemValorTributavel').innerHTML = "";				
            document.getElementById('concordaValorAbitrado').style.display = "";    
            document.getElementById('ValorCalculado').style.display = "none";
            document.getElementById('ValorIgual').style.display = "";                 
           
         }else{
            document.getElementById('<%=Form.CAMPO_VALOR_TRIBUTAVEL%>').innerHTML = floatTomoeda(valorVenal);
            document.getElementById('tableValorArbitrado').style.display  = "";
            document.getElementById('campoValor').innerHTML = parseFloat(valorVenal);
            document.getElementById('mensagemValorTributavel').innerHTML = '<img src="/imagens/warning2.png" width="16" height="16"><img><font color="red">Valor Calculado pelo Sistema<font>';   
            document.getElementById('concordaValorAbitrado').style.display = "";  
            document.getElementById('ValorIgual').style.display = "none";
            document.getElementById('ValorCalculado').style.display = "";           
            
         }
      }
   }
   
   function validarCampoIpva()
   {
      var MT = "MT";    
      var SELECIONE = "Selecione";
      var estadoVeiculo = document.getElementById('<%=Form.CAMPO_UF_VEICULO%>');      
      var esttadoSelecionado = estadoVeiculo.options[estadoVeiculo.selectedIndex].text    
   
        if(esttadoSelecionado != MT)
        {
         document.getElementById('tableDadosMtPlacaRenavan').style.display = "none";
         document.getElementById('campoIptuOutrosEstados').style.display = "";
         document.getElementById('tableMarcaProprietarioMT').style.display = "none";
         document.getElementById('campoAnoVeiculo').style.display = "";
         
         //limpando valores   
         document.form.<%=Form.CAMPO_RENAVAN_VEICULO%>.value = "";
        }
        else
        {      
          document.getElementById('tableDadosMtPlacaRenavan').style.display = "";
          document.getElementById('campoIptuOutrosEstados').style.display = "none";  
          document.getElementById('campoAnoVeiculo').style.display = "none";
      }
    }
    
    function buscarModeloVeiculo(){
      document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.CAMPO_CATEGORIA_VEICULO+"=43"%>';
      document.form.submit(); 
   }
    
</script> 

 
<jsp:include page="/util/ViewVerificaErro.jsp"/>
<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
</head>

<body class="SEFAZ-Body" onload="validarCampoIpva();verificaErro();calcularValorTributavelIpva();">
	<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
	<CENTER>
	<FORM method="POST" action="<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>" name="form">		
   
		<table cellspacing="0"  cellpadding="0" border="0" width="740" align="center">
			<tr>
				<td colspan="4"><div align="center"><font face="Verdana, Arial, Helvetica, sans-serif" size="3"><b>Ficha Veiculo</b></font></div></td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>	
         <%--
			 <tr align="right">
					<td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
               
			 </tr>--%>          
        
			<tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="2">Dados do Veiculo</td>			
			<tr> 
         
        <tr>
       <td></br></td>
       </tr>
       <tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="278">UF do Veiculo :&nbsp;</td>
				<td class="SEFAZ-TD-ComboBox" width="462">              
            <select name="<%=Form.CAMPO_UF_VEICULO%>" id="<%=Form.CAMPO_UF_VEICULO%>" onchange="validarCampoIpva(this);" class="chzn-select" style="width:150px;" tabindex="2" >
          
               <option  value="" selected>Selecione</option><%--<%=Form.SELECIONE%>--%>
                    <c:forEach var="ufIntegracaoVo" items="${fichaVeiculoVo.ufsVO.collVO}">                    
                      <c:if test="${fichaVeiculoVo.siglaUfVeiculo == ufIntegracaoVo.siglUf}">
                        <option value="<c:out value="${ufIntegracaoVo.siglUf}"></c:out>" selected="selected"><c:out value="${ufIntegracaoVo.siglUf}"></c:out></option>	
                      </c:if>
                      <c:if test="${fichaVeiculoVo.siglaUfVeiculo != ufIntegracaoVo.siglUf}">
                        <option value="<c:out value="${ufIntegracaoVo.siglUf}"></c:out>"><c:out value="${ufIntegracaoVo.siglUf}"></c:out></option>	
                      </c:if>
                  </c:forEach>
            </select><font color="red">*</font>
				<td> 
         </tr>
         </table>
         <br>
        <div id="tableDadosMtPlacaRenavan" style="display:none">    
           <table cellspacing="0" cellpadding="0" border="0" width="740" align="center"> 
            <tr> 
               <td class="SEFAZ-TD-RotuloEntrada" width="278">RENAVAM :&nbsp;</td>
                  <td class="SEFAZ-TD-CampoEntrada" width="462">
                  <abaco:campoApenasNumero mostrarZero="false"  maxlength="12" name="<%=Form.CAMPO_RENAVAN_VEICULO%>" size="20" value="${fichaVeiculoVo.valorRenavanFormatado}" onBlur="toUpperCaseW3c(this)"></abaco:campoApenasNumero><font color="red">*</font>   
                  <input type="button" value="<%=Form.CAMPO_PESQUISAR_VEICULO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PESQUISAR_VEICULO%>" id="btnPesquisar" onClick="return pesquisarVeiculoMT()"></input>
               </td>
            </tr>                     
          </table>
       </div>
       <br>
      <div id="campoIptuOutrosEstados">   
       <table cellspacing="0"  cellpadding="0" border="0" width="740" align="center">  
        <tr>
        <td class="SEFAZ-TD-RotuloEntrada" width="278">Selecione a Marca:&nbsp;</td>        
           <td class="SEFAZ-TD-ComboBox" width="462">                 
         <select name="<%=Form.CAMPO_MARCA_VEICULO%>" id="<%=Form.CAMPO_MARCA_VEICULO%>" onchange="buscarModeloVeiculo(this);"  class="chzn-select" style="width:150px;" tabindex="2" >
         <option  value="" selected>Selecione</option><%--<%=Form.SELECIONE%>--%>
                    <c:forEach var="marcaVo" items="${fichaVeiculoVo.marcaVo.collVO}">                      
                    
                      <c:if test="${fichaVeiculoVo.marcaVo.codMarca == marcaVo.codMarca}">
                        <option value="<c:out value="${marcaVo.codMarca}"></c:out>" selected="selected"><c:out value="${marcaVo.nomeMarca}"></c:out></option>	
                      </c:if>
                      <c:if test="${fichaVeiculoVo.marcaVo.nomeMarca !=  marcaVo.nomeMarca}">
                      
                        <option value="<c:out value="${marcaVo.codMarca}"></c:out>"><c:out value="${marcaVo.nomeMarca}"></c:out></option>	
                        </c:if>
                  </c:forEach>
            </select><font color="red">*</font>
          </td> 
        </tr>  
       <tr>
       <tr>
       <td></br></td>
       </tr>
       <tr>
        <td class="SEFAZ-TD-RotuloEntrada" width="278">Selecione o Veiculo:&nbsp;</td>
          <td class="SEFAZ-TD-ComboBox" width="462">         
           
          
             <select name="<%=Form.CAMPO_UF_VEICULO_OUTROS_ESTADOS%>" id="<%=Form.CAMPO_UF_VEICULO_OUTROS_ESTADOS%>" class="chzn-select" style="width:300px;" tabindex="2" >             
             <option  value="" selected>Selecione</option><%--<%=Form.SELECIONE%>--%>
                    <c:forEach var="ficha" items="${fichaVeiculoVo.categoria.collVO}">                      
                    
                      <c:if test="${fichaVeiculoVo.categoria.codgCategoria == ficha.codgCategoria}">
                        <option value="<c:out value="${ficha.codgCategoria}"></c:out>" selected="selected"><c:out value="${ficha.descCategoria}"></c:out></option>	
                      </c:if>
                      <c:if test="${fichaVeiculoVo.categoria.descCategoria != ficha.descCategoria}">
                      
                        <option value="<c:out value="${ficha.codgCategoria}"></c:out>"><c:out value="${ficha.descCategoria}"></c:out></option>	
                        </c:if>
                  </c:forEach>
            </select><font color="red">*</font>
           
        </td>
        
        </tr>
      </table>      
      </div>      
      </br>  
       <div id="campoAnoVeiculo">   
      <table cellspacing="0"  cellpadding="0" border="0" width="740" align="center">
          <tr>
							<td class="SEFAZ-TD-RotuloEntrada" width="270">Ano Fabricação do Veiculo:&nbsp;</td>
							<td class="SEFAZ-TD-CampoEntrada" width="470"> 
								<select id="<%=Form.CAMPO_ANO_FABRICACAO_VEICULO%>" name="<%=Form.CAMPO_ANO_FABRICACAO_VEICULO%>"   class="chzn-select" style="width:150px;" tabindex="2" >
									<c:forEach  varStatus="indice"  begin="${sefazDataHora.ano - 30}" end="${sefazDataHora.ano}" var="ano">
                            <c:if test="${fichaVeiculoVo.anoFabricacao == ano}">
                              <option value="<c:out value="${fichaVeiculoVo.anoFabricacao}"/>" selected><c:out value="${fichaVeiculoVo.anoFabricacao}" /></option>
                           </c:if>
                           <c:if test="${fichaVeiculoVo.anoFabricacao != ano}">
                              <option value="<c:out value="${ano}" />" ><c:out value="${ano}" /></option>
                           </c:if>
									</c:forEach>
								</select><font color="red">*</font>  <input type="button" value="<%=Form.CAMPO_PESQUISAR_VEICULO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PESQUISAR_VEICULO%>" id="btnPesquisar" onClick="return pesquisarVeiculoOutrosEstados();"></input>
							</td>
						</tr>
         </table> 
       </div>
       </BR>       
       
       
      <c:if test="${fichaVeiculoVo.valorVenal == 0.0}">
	  <div id="tableMarcaProprietarioMT">
		  <table cellspacing="0"  cellpadding="0" border="0" width="740" align="center">
		   <tr align="center"> 
			 <td ><h5><font color="red"><%=MensagemErro.NAO_EXISTE_VALORES_PLACA_RENAVAN%></font></h5></td>
		   </tr>
		   </table>
	   </div>
      </c:if>
      
       <c:if test="${fichaVeiculoVo.existeValorVeiculo eq false }">
      <table cellspacing="0" id="tableMarcaModelo" cellpadding="0" border="0" width="740" align="center">
       <tr align="center"> 
         <td><h5><font color="red"><%=MensagemErro.NAO_EXISTE_VALORES_MODELO_VEICULO%></font></h5></td>
       </tr>
       </table>
      </c:if>
       
      <c:if test="${not empty fichaVeiculoVo.numrPlaca && fichaVeiculoVo.valorVenal != 0.0}">
		  <div id="tableMarcaProprietarioMT">
			<table cellspacing="0"  cellpadding="0" border="0" width="740" align="center">
             <tr> 
				   <td class="SEFAZ-TD-RotuloEntrada" width="278">Placa:&nbsp;</td>  
					 <td class="SEFAZ-TD-CampoEntrada" width="462"><input readonly="true" name="<%=Form.CAMPO_PLACA_VEICULO%>" size="20" value="<c:out value="${fichaVeiculoVo.numrPlaca}"></c:out>" onblur="toUpperCaseW3c(this)"></input></td>
				  </tr>	
				 <tr> 
				   <td class="SEFAZ-TD-RotuloEntrada" width="278">Marca&nbsp;Modelo:&nbsp;</td>  
					 <td class="SEFAZ-TD-CampoEntrada" width="462"><input readonly="true" name="<%=Form.CAMPO_MARCA_MODELO_VEICULO%>" size="40" value="<c:out value="${fichaVeiculoVo.categoria.descCategoria}"></c:out>" onblur="toUpperCaseW3c(this)"></input></td>
				  </tr>	 
				  <tr>
					 <td class="SEFAZ-TD-RotuloEntrada" width="278">Ano&nbsp;Fabricação:&nbsp;</td>  
					 <td class="SEFAZ-TD-CampoEntrada" width="462"><input readonly="true" name="<%=Form.CAMPO_ANO_FABRICACAO_VEICULO%>" size="8" value="<c:out value="${fichaVeiculoVo.anoFabricacao}"></c:out>" onblur="toUpperCaseW3c(this)"></input></td>			
				  <tr/>
				  <tr>
					<td class="SEFAZ-TD-RotuloEntrada" width="278">Proprietario:&nbsp;</td>  
					 <td class="SEFAZ-TD-CampoEntrada" width="462"><input readonly="true" name="<%=Form.CAMPO_NOME_PROPRIETARIO_VEICULO%>" size="40" value="<c:out value="${fichaVeiculoVo.nomeProprietario}"></c:out>" onblur="toUpperCaseW3c(this)"></input></td>
				 </tr> 
			</table>
		  </div>
      </c:if>
       </br>
      <c:if test="${fichaVeiculoVo.valorVenal > 0.0}">
		  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
			  <tr> 
				 <td class="SEFAZ-TD-RotuloEntrada" width="278">Valor do veículo:&nbsp;</td>  
				 <td><abaco:campoMonetario name="<%=Form.CAMPO_VALOR_DECLARADO%>" idCampo="<%=Form.CAMPO_VALOR_DECLARADO%>" quantidadeDigitosInteiros="8" size="15" value="${fichaVeiculoVo.valorInformadoFormatado}" quantidadeCasasDecimais="2" onBlur="calcularValorTributavelIpva();"/><font color="red">*&nbsp;</font></td>
			  </tr>   
			 <tr>
			   <td></br></td>
			 </tr>
		  </table>
       <input type="hidden" id="campoValor" value="" />
		 <table cellspacing="0" cellpadding="0" border="0" width="740" align="center" style="display:none" id="tableValorArbitrado">
			<tr>		
			   <td class="SEFAZ-TD-RotuloEntrada" width="2100" >Valor&nbsp;Arbitrado&nbsp;para&nbsp;Veículo:&nbsp;</td>  
			   <td class="SEFAZ-TD-CampoEntrada"  width="100" id="<%=Form.CAMPO_VALOR_TRIBUTAVEL%>"></td><td class="SEFAZ-TD-CampoEntrada" id="mensagemValorTributavel" width="3000"></td>
			</tr>		   		
		 </table>
       
		 <div style="display:none" id="concordaValorAbitrado">
         </br>  
         
            <div style="display:none" id="ValorIgual">            
               <jsp:include page="/giaitcd/util/ViewIncludeValoresIguais.jsp"/>            
            </div>
            
            <div style="display:none" id="ValorCalculado">
               <jsp:include page="/giaitcd/util/ViewIncludeConcordaPagamento.jsp"/>               
            </div> 
            
		 </div>       
       
      </c:if>
      </br>
      <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
     <tr>
     	<td  colspan="2" align="center"  width="370">
			<input type="button" value="<%=Form.TEXTO_BOTAO_VOLTAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_VOLTAR_FICHA_VEICULO%>" id="<%=Form.BOTAO_VOLTAR_FICHA_VEICULO%>" onClick="voltarBemTributavel()"></input>
				<abaco:botaoCancelar></abaco:botaoCancelar>
            <c:if test="${not fichaVeiculoVo.bemTributavelVo.alterar}">              
         	<input  name="<%=Form.CAMPO_ADICIONAR_VEICULO%>" align="center" value="<%=Form.CAMPO_ADICIONAR_VEICULO%>" type="button" class="SEFAZ-INPUT-Botao" id="<%=Form.CAMPO_ADICIONAR_VEICULO%>" onClick="processarFichaVeiculo();" />
				</c:if>
				<c:if test="${fichaVeiculoVo.bemTributavelVo.alterar}">             
					 <input  name="<%=Form.CAMPO_ALTERAR_VEICULO%>" align="center" value="<%=Form.TEXTO_BOTAO_ALTERAR%>" type="button" class="SEFAZ-INPUT-Botao" id="<%=Form.CAMPO_ALTERAR_VEICULO%>" onClick="alterarFichaVeiculo();" />
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
