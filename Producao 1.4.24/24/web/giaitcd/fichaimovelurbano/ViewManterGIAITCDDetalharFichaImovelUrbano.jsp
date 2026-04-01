<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManterGIAITCDDetalharFichaImovelUrbano.jsp
* Criação : dezembro de 2007 / João Batista Padilha e Silva
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro"%>
<%@page import ="br.gov.mt.sefaz.itc.model.generico.bemtributavel.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoIPTU"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<%
	pageContext.setAttribute("ATIVO", new Integer(DomnStatusRegistro.ATIVO));
	pageContext.setAttribute("INATIVO", new Integer(DomnStatusRegistro.INATIVO));
   pageContext.setAttribute("IPTU_ESTIMATIVA", new Integer(DomnTipoIPTU.ESTIMATIVA));
   pageContext.setAttribute("IPTU_INTEGRADO", new Integer(DomnTipoIPTU.INTEGRADO));   
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
<META HTTP-EQUIV=Cache-Control content=no-store>
<META HTTP-EQUIV=Pragma content=no-cache>
<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}"/>/javascript/itcd.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript" language="javascript">				

   /**
   * Funcao utilizada para calcular
   * o valor do imovel quando o IPTU for INTEGRADO.
   *
   */
   function calcularValorTributavelIPTUIntegrado(){
   
   	 document.getElementById('concordaValorAbitrado').style.display = "none";
       document.getElementById('ValorIgual').style.display = "none";
       document.getElementById('ValorCalculado').style.display = "none";
      var valorTotal = document.form.<%=Form.CAMPO_VALOR_TOTAL%>.value;
      //var vv = document.form.campo_hidden.value;
      var valorVenal = document.form.<%=Form.CAMPO_VALOR_VENAL%>.value;
      
      var valorPercentual = document.form.<%=Form.CAMPO_PERCENTUAL_TRANSMITIDO_IMOVEL%>.value;
      
      valorTotal = replaceAll(valorTotal, ".", "");
      valorTotal = replaceAll(valorTotal, ",", ".");
      valorVenal = replaceAll(valorVenal, ".", "");
      valorVenal = replaceAll(valorVenal, ",", ".");
      if((trimW3c(valorVenal) != "") & (trimW3c(valorTotal) != "") )
      {
         if(valorPercentual == 0)
         {
            alert('Favor informar o percentual transmitido do imóvel.');
            document.form.<%=Form.CAMPO_PERCENTUAL_TRANSMITIDO_IMOVEL%>.value = "100";
            document.getElementById('tdVlrTributavel').innerHTML ="";
            return false;
         }
         if(valorPercentual > 100)
         {
            alert('Percentual informado inválido.');             
            document.form.<%=Form.CAMPO_PERCENTUAL_TRANSMITIDO_IMOVEL%>.value = "100";   
            document.getElementById('tdVlrTributavel').innerHTML ="";
            return false;
         }
         
         var valorPercentualVenal =  ((parseFloat(valorVenal)) * valorPercentual)/ 100;
      
         if(parseFloat(valorTotal) > parseFloat(valorPercentualVenal)){         
            document.getElementById('tdVlrTributavel').innerHTML = floatTomoeda(valorTotal);
            document.getElementById('mensagemValorTributavel').innerHTML = "";            
			
         }else{         
            document.getElementById('tdVlrTributavel').innerHTML = floatTomoeda(valorPercentualVenal);
            document.getElementById('mensagemValorTributavel').innerHTML = '<img src="/imagens/warning2.png" width="16" height="16"><img><font color="red">Valor Calculado pelo Sistema<font>';   
            }
            
            if(document.getElementById('tdVlrTributavel').innerHTML == floatTomoeda(valorTotal)){
               document.getElementById('concordaValorAbitrado').style.display = "";
               document.getElementById('ValorIgual').style.display = "";
               document.form.<%=Form.CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE%>.value = 'SIM';
            }else{
               document.getElementById('concordaValorAbitrado').style.display = "";
               document.getElementById('ValorCalculado').style.display = "";
            }                  		
      }
   }

/**
* Funcao utilizada para calcular
* o valor do imovel quando o IPTU for ESTIMADO.
*
*/
function calcularValorTributavelIPTUEstimado(){
	document.getElementById('concordaValorAbitrado').style.display = "none";
   document.getElementById('ValorIgual').style.display = "none";
   document.getElementById('ValorCalculado').style.display = "none";
   var valorTotal = document.form.<%=Form.CAMPO_VALOR_TOTAL%>.value;
	var valorVenal = document.form.<%=Form.CAMPO_VALOR_VENAL%>.value;
   // Calculo C2 = Área Total do Imóvel X valor metro territorial
   var areaTotal = document.form.<%=Form.CAMPO_AREA_TOTAL_IMOVEL%>.value;
   var areaConstruida = '0,0';
   <c:if test="${fichaImovelUrbanoVo.existeConstrucao}">
      areaConstruida = document.form.<%=Form.CAMPO_AREA_CONSTRUIDA%>.value;
   </c:if>
   var valorMetroPredial = '<c:out value="${fichaImovelUrbanoVo.iptuVo.valorMetroPredialFormatado}"/>';
   var valorMetroTerritorial = '<c:out value="${fichaImovelUrbanoVo.iptuVo.valorMetroTerritorialFormatado}"/>';
   var valorPercentualEstimado = '<c:out value="${fichaImovelUrbanoVo.iptuVo.valorPercentualEstimadoFormatado}"/>';
	// ------ Formatação de valores ----------------------------------
   valorTotal = replaceAll(valorTotal, ".", "");
	valorTotal = replaceAll(valorTotal, ",", ".");
	valorVenal = replaceAll(valorVenal, ".", "");
	valorVenal = replaceAll(valorVenal, ",", ".");
   areaTotal = replaceAll(areaTotal, ".", "");
	areaTotal = replaceAll(areaTotal, ",", ".");
   valorMetroPredial = replaceAll(valorMetroPredial, ".", "");
	valorMetroPredial = replaceAll(valorMetroPredial, ",", ".");
   valorMetroTerritorial = replaceAll(valorMetroTerritorial, ".", "");
	valorMetroTerritorial = replaceAll(valorMetroTerritorial, ",", ".");
   areaConstruida = replaceAll(areaConstruida, ".", "");
	areaConstruida = replaceAll(areaConstruida, ",", ".");
   valorPercentualEstimado = replaceAll(valorPercentualEstimado, ".", "");
	valorPercentualEstimado = replaceAll(valorPercentualEstimado, ",", ".");
	if((trimW3c(valorTotal) != "") & (trimW3c(valorVenal) != "") & ((trimW3c(areaTotal) != "") & (trimW3c(areaConstruida) != "")))
	{  
      
      var maiorValorTributavel = parseFloat(0);
      // Por padrao o sistema nao exibe a mensagem (Valor Calculado pelo Sistema).
      // Somente quando for detectado que o valor calculado e menor que o informado a mensagem sera exibida.
      document.getElementById('mensagemValorTributavel').innerHTML = "";
      
      if(trimW3c(areaTotal) != "" & trimW3c(areaConstruida) != "")
		{
         // Calculo Area do Imovel
         var calculoValorAreaImovel = parseFloat(areaTotal) * parseFloat(valorMetroTerritorial);        
         // Calculo Area Contruida
         var calculoValorAreaConstruida = parseFloat(areaConstruida) * parseFloat(valorMetroPredial);
         
         var calculoTotalAreaImovel = calculoValorAreaImovel + calculoValorAreaConstruida;
         maiorValorTributavel = parseFloat(calculoTotalAreaImovel);
		}
      
      if(trimW3c(valorVenal) != ""){
         // Calculo Valor Venal
         var calculoC1 = parseFloat(valorVenal) + ( (parseFloat(valorVenal) * valorPercentualEstimado) / 100  );
         if(maiorValorTributavel < calculoC1 ){
             maiorValorTributavel = calculoC1;
         }
      }
     
      if(trimW3c(valorTotal) != "")
		{
			valorTotal = parseFloat(valorTotal);
         if(maiorValorTributavel < valorTotal ){
            maiorValorTributavel = valorTotal; 
			   
		}
		else{
		 document.getElementById('mensagemValorTributavel').innerHTML = '<img src="/imagens/warning2.png" width="16" height="16"><img><font color="red">Valor Calculado pelo Sistema<font>';
		 	   
		}

      if(maiorValorTributavel > 0){
         if(trimW3c(valorVenal) != ""){
            document.getElementById('tdVlrTributavel').innerHTML = floatTomoeda(maiorValorTributavel);  
         }
      }else{
         document.getElementById('tdVlrTributavel').innerHTML = "";
      }
	}
	else
	{
      document.getElementById('tdVlrTributavel').innerHTML = "";
      document.getElementById('mensagemValorTributavel').innerHTML = "";               
      
      }
   if(floatTomoeda(valorTotal) == floatTomoeda(maiorValorTributavel)){
            document.getElementById('concordaValorAbitrado').style.display = "";
            document.getElementById('ValorIgual').style.display = "";
            document.form.<%=Form.CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE%>.value = 'SIM';
      }else{
            document.getElementById('concordaValorAbitrado').style.display = "";	
            document.getElementById('ValorCalculado').style.display = "";
      }      
   }
}

function calculaValorTributavel()
{
  document.getElementById('concordaValorAbitrado').style.display = "none"; 
   <c:choose>
		<c:when test="${fichaImovelUrbanoVo.iptuVo.tipoITPU.valorCorrente == IPTU_ESTIMATIVA}">
         calcularValorTributavelIPTUEstimado();
      </c:when>
		<c:when test="${fichaImovelUrbanoVo.iptuVo.tipoITPU.valorCorrente == IPTU_INTEGRADO}">
         calcularValorTributavelIPTUIntegrado();
		</c:when>
   </c:choose>
}

function excluirBenfeitoriaImovelUrbano(indice)
{
	if(confirm(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_BENFEITORIA_DESEJA_EXCLUIR+"'"%>))
	{
		document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_EXCLUIR_BENFEITORIA_IMOVEL_URBANO+"="%>'+indice;
		document.form.submit();						 
		return true;
	}
	else
	{
		return false
	}
}

   function adicionarImovelUrbanoIntegrado(){
      var numeroInscricao = document.form.<%=Form.CAMPO_NUMERO_MATRICULA_INSCRICAO_IMOVEL%>.value;
      if(!verificaCamposW3c(document.form.<%=Form.CAMPO_NUMERO_MATRICULA_INSCRICAO_IMOVEL%>,<%="'"+MensagemErro.VALIDAR_NUMERO_INSCRICAO_IMOBILIARIA+"'"%>))
      {						
         return false;
      }
      else
      {						 
         document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ADICIONAR_IMOVEL_URBANO_INTEGRADO+"="%>'+numeroInscricao;
         document.form.submit();
         return true;
      }
   }

function adicionarBenfeitoria()
{			
	indice = document.form.<%=Form.CAMPO_SELECT_BENFEITORIA%>.value;
	if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_BENFEITORIA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_BENFEITORIA+"'"%>))
	{						
		return false;
	}
	else
	{						 
		document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ADICIONAR_BENFEITORIA_IMOVEL_URBANO+"="%>'+indice;
		document.form.submit();
		return true;
	}
}

function pesquisarCep()
{
	var mensagem = <%="'"+MensagemErro.VALIDAR_PARAMETRO_CEP+"'"%>
	if(trimW3c(document.form.<%=Form.CAMPO_CEP%>.value)=='')
	{
		alert(mensagem);
		return false;
	}
	else
	{
		cep = document.form.<%=Form.CAMPO_CEP%>.value;
		consultaCep = parseFloat(cep);
		if(consultaCep <= 0 || cep.length < 8)
		{
			alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CEP_VALIDO+"'"%>)
			return false;
		}
		document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR_CEP_IMOVEL_URBANO+"=1"%>';
		document.form.submit();
		return true;
	}
}
function formataNumero(campo, evt)
	{
		tecla = !isNaN(evt.keyCode)? evt.keyCode: evt.which;
		if ( tecla == 0)
			tecla = evt.which;
		if ((tecla != 0) &&  (tecla != 16) && (tecla != 17) && (tecla!=44) && (tecla != 48) && (tecla != 49) && (tecla != 50) && (tecla != 51) && (tecla != 52) && (tecla != 53) && (tecla != 54) && (tecla != 55) && (tecla != 56) && (tecla != 57) && (tecla != 9) && (tecla != 13) && (tecla != 8))
		{
			return false;	
		}
	}
function adicionarDescricaoBenfeitoria()
{
	var descricaoBenfeitoria = document.form.<%=Form.CAMPO_SELECT_BENFEITORIA%>[document.form.<%=Form.CAMPO_SELECT_BENFEITORIA%>.selectedIndex].text;					
	document.form.<%=Form.CAMPO_HIDDEN_DESCRICAO_BENFEITORIA%>.value = descricaoBenfeitoria;
}	
function estadoConservacao()
{
	selectEstadoConservacao = document.form.<%=Form.CAMPO_SELECT_ESTADO_CONSERVACAO%>;
	if (selectEstadoConservacao.options[selectEstadoConservacao.selectedIndex].value != 1 || selectEstadoConservacao.options[selectEstadoConservacao.selectedIndex].value != "")
	{
		document.form.<%=Form.CAMPO_AREA_TOTAL_IMOVEL%>.focus();
	}
}

 /*
		Função que complementa a função calcular() (acima) formatando o resultado do cálculo.
		Esta função é utilizada para formatar o resultado do cálculo Valor Unitário x Quantidade.
		@param: valor - número a ser formatado.
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
		
		function verificaAreaTotalAreaConstruida()
		{
			areaTotal = replaceAll(document.form.<%=Form.CAMPO_AREA_TOTAL_IMOVEL%>.value,".","");
			if(areaTotal != '')
			{
				areaTotal = parseFloat(replaceAll(areaTotal,",","."));
			}
			else
			{
				areaTotal = 0;	
			}
			if(areaTotal <= 0)
			{
				alert('<%=MensagemErro.VALIDAR_AREA_TOTAL_IMOVEL_MAIOR_QUE_ZERO%>');
			}
			areaConstruida = 0;
			<c:if test="${fichaImovelUrbanoVo.existeConstrucao}">
				areaConstruida = replaceAll(document.form.<%=Form.CAMPO_AREA_CONSTRUIDA%>.value,".","");
				if(areaConstruida != '')
				{
					areaConstruida = parseFloat(replaceAll(areaConstruida,",","."));
				}
				else
				{
					return false;
				}			
				if(areaConstruida <= 0)
				{
					alert('<%=MensagemErro.VALIDAR_AREA_CONSTRUIDA_MAIOR_QUE_ZERO%>');
					areaConstruida = '';
					return false;
				}				
			</c:if>			
			if(	areaConstruida > areaTotal)
			{
				return true;
			}
			else
			{
				return true;
			}

		}
		
		function voltarBemTributavel()
		{
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_VOLTAR_IMOVEL_URBANO+"=1"%>';
			document.form.submit();	
		}
		
	function validaFormulario()
	{
		
		<c:if test="${empty fichaImovelUrbanoVo.enderecoVo.cep.localidade.nomeLocalidade}">
					alert(<%="'"+MensagemErro.VALIDAR_PARAMETRO_CEP+"'"%>);
					return false;
		</c:if>
		
		var valorTotal = document.form.<%=Form.CAMPO_VALOR_TOTAL%>.value;
		var valorVenal = document.form.<%=Form.CAMPO_VALOR_VENAL%>.value; 
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_LOGRADOURO%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_TIPO_LOGRADOURO+"'"%>))
		{
			return false;
		}
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_LOGRADOURO%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_LOGRADOURO+"'"%>))
		{
			return false;
		}
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_NUMERO%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_NUMERO+"'"%>))
		{
			return false;
		}
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_BAIRRO%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_BAIRRO+"'"%>))
		{
			return false;
		}
		
		<c:if test="${fichaImovelUrbanoVo.existeConstrucao}">
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_ESTADO_CONSERVACAO%>,<%="'"+MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_INFORMAR_ESTADO_CONSERVACAO+"'"%>))
				{
					return false;
				}		
		</c:if>
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_AREA_TOTAL_IMOVEL%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_AREA_TOTAL_IMOVEL+"'"%>))
				{
					return false;
				}
		<c:if test="${fichaImovelUrbanoVo.existeConstrucao}">
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_AREA_CONSTRUIDA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_AREA_CONSTRUIDA+"'"%>))
				{
					return false;
				}	
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_CONSTRUCAO%>,<%="'"+MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_TIPO_CONSTRUCAO+"'"%>))
				{
					return false;
				}			
				if(!verificaAreaTotalAreaConstruida())
				{
					return false;
				}			
		</c:if>
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_ACESSO%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_TIPO_ACESSO+"'"%>))
		{
			return false;
		}
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_VALOR_TOTAL%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_VALOR_TOTAL+"'"%>))
		{
			return false;
		}
		if(valorTotal != '')
		{
			valorTotal = parseFloat(replaceAll(valorTotal,",","."));
		}
		if ( valorTotal <=0)
		{
			alert('<%=MensagemErro.VALIDAR_VALOR_TOTAL_DE_MERCADO_MAIOR_QUE_ZERO%>');
			return false;
		}
	   if(!verificaCamposW3c(document.form.<%=Form.CAMPO_VALOR_VENAL%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_VALOR_VENAL+"'"%>))
	   {
		  return false;
	   }
		if(valorVenal != '')
		{
			valorVenal = parseFloat(replaceAll(valorVenal,",","."));
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
				
		return true;
	}
	
	function confirmarImovelUrbano(){
	
		if(validaFormulario()){
			if(confirm("Deseja confirmar a inclusão da Ficha de Imóvel Urbano"))
			{
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_INCLUIR_IMOVEL_URBANO+"=1"%>';
				document.form.submit();	
				return true;
			}
		}		
	}  

</script>
<jsp:include page="/util/ViewVerificaErro.jsp"/>
<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
</head>
<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');calculaValorTributavel();verificaErro();">
	<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
	<CENTER>
	<FORM method="POST" action="<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>" name="form">			
		<table cellspacing="1" cellpadding="0" border="0" width="1000" align="center">
			<tr>
				<td colspan="4"><div align="center"><font face="Verdana, Arial, Helvetica, sans-serif" size="3"><b>Ficha Imóvel Urbano</b></font></div></td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>			
			 <tr align="right">
					<td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
			 </tr>
			<tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="4">Dados do registro</td>
			</tr>
			<tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="278">Tipo Logradouro:&nbsp;</td>
				<td class="SEFAZ-TD-ComboBox" width="462">
					<abaco:campoSelectDominio 
						ajuda="" 
						classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoLogradouro" 
						name="<%=Form.CAMPO_SELECT_TIPO_LOGRADOURO%>" 
						tabIndex="" 
						idCampo="<%=Form.CAMPO_SELECT_TIPO_LOGRADOURO%>" 
						mostrarSelecione="true" 
						opcaoSelecionada="${fichaImovelUrbanoVo.enderecoVo.tipoLogr}">
					</abaco:campoSelectDominio><font color="red">*</font>
				</td>
			</tr>
			<tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="278">Logradouro:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" width="462"> 
				 <input name="<%=Form.CAMPO_LOGRADOURO%>" type="text" class="SEFAZ-INPUT-Text" id="<%=Form.CAMPO_LOGRADOURO%>" value="<c:out value="${fichaImovelUrbanoVo.enderecoVo.logradouro}"></c:out>" maxlength="80" size="40" onblur="toUpperCaseW3c(this)"></input><font color="red">*</font>
				</td>
			</tr>
			<tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="278">Número:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" width="462">
				<input name="<%=Form.CAMPO_NUMERO%>" type="text" class="SEFAZ-INPUT-Text" id="<%=Form.CAMPO_NUMERO%>" value="<c:out value="${fichaImovelUrbanoVo.enderecoVo.numrLogradouro}"></c:out>" maxlength="7" size="6" onkeypress="return formataNumero(this,event);"></input><font color="red">*</font>
				</td>
			</tr>
			<tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="278">Complemento:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" width="462">
				<input name="<%=Form.CAMPO_COMPLEMENTO%>" type="text" class="SEFAZ-INPUT-Text" id="<%=Form.CAMPO_COMPLEMENTO%>" value="<c:out value="${fichaImovelUrbanoVo.enderecoVo.complemento}"></c:out>" maxlength="100" size="40" onblur="toUpperCaseW3c(this)"></input>
				</td>
			</tr>
			<tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="278">Ponto de Referência:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" width="462"> 
				<input name="<%=Form.CAMPO_PONTO_REFERENCIA%>" type="text" class="SEFAZ-INPUT-Text" id="<%=Form.CAMPO_PONTO_REFERENCIA%>" value="<c:out value="${fichaImovelUrbanoVo.enderecoVo.pontoReferencia}"></c:out>" maxlength="100" size="40" onblur="toUpperCaseW3c(this)"></input>
				</td>
			</tr>
			<tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="278">Bairro:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" width="462">
				<input name="<%=Form.CAMPO_BAIRRO%>" type="text" class="SEFAZ-INPUT-Text" id="<%=Form.CAMPO_BAIRRO%>" value="<c:out value="${fichaImovelUrbanoVo.enderecoVo.bairro}"></c:out>" maxlength="60" size="40" onblur="toUpperCaseW3c(this)"></input><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada">CEP:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada"><abaco:campoApenasNumero maxlength="8" name="<%=Form.CAMPO_CEP%>" size="12" value="" onKeyPress="return formataNumero(this,event);" onBlur="javascript: pesquisarCep();"></abaco:campoApenasNumero><font color="red">*</font></td>
			</tr>							
			<c:if test="${not empty fichaImovelUrbanoVo.enderecoVo.cep.localidade.nomeLocalidade}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">Localidade:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelUrbanoVo.enderecoVo.cep.localidade.nomeLocalidade}"></c:out></td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelUrbanoVo.enderecoVo.cep.localidade.uf.siglUf}"></c:out></td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelUrbanoVo.enderecoVo.cep.codgCep}"></c:out></td>
				</tr>
			</c:if>
			<c:if test="${fichaImovelUrbanoVo.existeConstrucao}">
					<tr> 
						<td class="SEFAZ-TD-RotuloEntrada" width="278">Estado de Conservação:&nbsp;</td>
						<td class="SEFAZ-TD-ComboBox" width="462">
							<abaco:campoSelectDominio 
								ajuda="" 
								classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnEstadoConservacao" 
								name="<%=Form.CAMPO_SELECT_ESTADO_CONSERVACAO%>" 
								tabIndex="" 
								idCampo="<%=Form.CAMPO_SELECT_ESTADO_CONSERVACAO%>" 
								mostrarSelecione="true" 
								onChange="estadoConservacao();" 
								opcaoSelecionada="${fichaImovelUrbanoVo.estadoConservacao.valorCorrente}">
							</abaco:campoSelectDominio><font color="red">*</font>
						</td>
					</tr>			
			</c:if>
			<tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="278">Área Total do Imóvel:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" width="462">
              <%-- <input type="text" class="SEFAZ-INPUT-Text" name="<%=Form.CAMPO_AREA_TOTAL_IMOVEL%>" onKeyPress="return formataNumeroDecW3c(this,event)"; value="<c:out value='${fichaImovelUrbanoVo.quantidadeAreaTotalFormatado}'/>" size="12" id="<%=Form.CAMPO_AREA_TOTAL_IMOVEL%>" onBlur="verificaAreaTotalAreaConstruida();calculaValorTributavel();"/>M&sup2;<font color="red">*</font> --%>
					<abaco:campoMonetario name="<%=Form.CAMPO_AREA_TOTAL_IMOVEL%>" idCampo="<%=Form.CAMPO_AREA_TOTAL_IMOVEL%>" quantidadeDigitosInteiros="8" size="12" value="${fichaImovelUrbanoVo.quantidadeAreaTotalFormatado}" quantidadeCasasDecimais="4" onBlur="verificaAreaTotalAreaConstruida();calculaValorTributavel();"/>M&sup2;<font color="red">*</font>
				</td>
			</tr>
			<c:if test="${fichaImovelUrbanoVo.existeConstrucao}">
					<tr> 
						<td class="SEFAZ-TD-RotuloEntrada" width="278">Área Construída:&nbsp;</td>
						<td class="SEFAZ-TD-CampoEntrada" width="462">
                     <%-- <input type="text" class="SEFAZ-INPUT-Text"  name="<%=Form.CAMPO_AREA_CONSTRUIDA%>" onKeyPress="return formataNumeroDecW3c(this,event)"; value="<c:out value='${fichaImovelUrbanoVo.quantidadeAreaConstruidaFormatado}'/>" size="12" id="<%=Form.CAMPO_AREA_CONSTRUIDA%>" onBlur="calculaValorTributavel();"/>M&sup2;<font color="red">*</font> --%>
							<abaco:campoMonetario name="<%=Form.CAMPO_AREA_CONSTRUIDA%>" quantidadeDigitosInteiros="8" size="12" value="${fichaImovelUrbanoVo.quantidadeAreaConstruidaFormatado}" quantidadeCasasDecimais="4" idCampo="<%=Form.CAMPO_AREA_CONSTRUIDA%>" onBlur="calculaValorTributavel();"/>M&sup2;<font color="red">*</font>
						</td>
					</tr>
					<tr> 
						<td class="SEFAZ-TD-RotuloEntrada" width="278">Tipo Construção:&nbsp;</td>
						<td class="SEFAZ-TD-ComboBox" width="462">
							<select name="<%=Form.CAMPO_SELECT_TIPO_CONSTRUCAO%>" id="<%=Form.CAMPO_SELECT_TIPO_CONSTRUCAO%>">
								<option value="" selected><%=Form.SELECIONE%></option>
								<c:forEach var="tipoConstrucaoVo" items="${fichaImovelUrbanoVo.construcaoVo.collVO}">
									<c:if test="${tipoConstrucaoVo.codigo == fichaImovelUrbanoVo.construcaoVo.codigo}">
										<option value="<c:out value="${tipoConstrucaoVo.codigo}"></c:out>" selected="selected"><c:out value="${tipoConstrucaoVo.descricaoConstrucao}"></c:out></option>
									</c:if>
									<c:if test="${tipoConstrucaoVo.codigo != fichaImovelUrbanoVo.construcaoVo.codigo}">
										<option value="<c:out value="${tipoConstrucaoVo.codigo}"></c:out>"><c:out value="${tipoConstrucaoVo.descricaoConstrucao}"></c:out></option>
									</c:if>
								</c:forEach>
							</select><font color="red">*</font>
						</td>
					</tr>			
			</c:if>
			<tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="278">Tipo Acesso:&nbsp;</td>
				<td class="SEFAZ-TD-ComboBox" width="462">
					<abaco:campoSelectDominio 
						ajuda="" 
						classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoAcesso" 
						name="<%=Form.CAMPO_SELECT_TIPO_ACESSO%>" 
						tabIndex="" 
						idCampo="<%=Form.CAMPO_SELECT_TIPO_ACESSO%>" 
						mostrarSelecione="true" 
						opcaoSelecionada="${fichaImovelUrbanoVo.tipoAcesso.valorCorrente}">
					</abaco:campoSelectDominio><font color="red">*</font>
				</td>
			</tr>
			<tr class="SEFAZ-TR-Titulo">
				<td colspan="4">Dados Benfeitoria</td>
			</tr>
			<tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="278">Benfeitoria:&nbsp;</td>
				<td class="SEFAZ-TD-ComboBox" width="462">
					<select name="<%=Form.CAMPO_SELECT_BENFEITORIA%>" id="<%=Form.CAMPO_SELECT_BENFEITORIA%>" onchange="adicionarDescricaoBenfeitoria();">
						<option value="" selected><%=Form.SELECIONE%></option>
						<c:forEach var="benfeitoriaVo" items="${fichaImovelUrbanoVo.fichaImovelUrbanoBenfeitoriaVo.benfeitoriaVo.collVO}">							
							<option value="<c:out value="${benfeitoriaVo.codigo}"></c:out>"><c:out value="${benfeitoriaVo.descricaoBenfeitoria}"></c:out></option>
						</c:forEach>
					</select>					
				</td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="278">Descrição Benfeitoria:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" width="462"><abaco:campoStringMaiuscula maxlength="50" name="<%=Form.CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA_URBANO%>" size="50" value=""/></td> 
			</tr>
			<tr>
				<td><input type="hidden" name="<%=Form.CAMPO_HIDDEN_DESCRICAO_BENFEITORIA%>" value="<c:out value="${fichaImovelUrbanoVo.fichaImovelUrbanoBenfeitoriaVo.benfeitoriaVo.descricaoBenfeitoria}"></c:out>">&nbsp;</td>			
				<td colspan="2"><input type="button" name="<%=Form.BOTAO_ADICIONAR_BENFEITORIA_IMOVEL_URBANO%>" id = "<%=Form.BOTAO_ADICIONAR_BENFEITORIA_IMOVEL_URBANO%>" value="<%=Form.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" onClick="adicionarBenfeitoria();"></input></td>				
			</tr>
			<c:if test="${not empty fichaImovelUrbanoVo.fichaImovelUrbanoBenfeitoriaVo.collVO}">
			<tr>
				<td width="278">&nbsp;</td>
				<td width="462">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="5">
					<table align="center" border="0" cellpadding="0" cellspacing="1" width="1000">
						<tr class="SEFAZ-TR-SubTitulo">
							<td colspan="3">Descrição da Benfeitoria</td>
						</tr>
						<tr class="SEFAZ-TR-SubTitulo">
							<td width="365">Benfeitoria</td>
							<td width="370" colspan="2">Descrição da Benfeitoria</td>
						</tr>
						<c:forEach var="fichaImovelUrbanoBenfeitoriaVo" items="${fichaImovelUrbanoVo.fichaImovelUrbanoBenfeitoriaVo.collVO}" varStatus="contador">
						<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
						<c:if test="${contador.count % 2 != 0}">
						<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
						</c:if>																	
						<tr class="<c:out value="${linhaEstilo}"></c:out>">
							<td align="left" width="365"><c:out value="${fichaImovelUrbanoBenfeitoriaVo.benfeitoriaVo.descricaoBenfeitoria}"></c:out></td>
							<td align="left" width="365"><c:out value="${fichaImovelUrbanoBenfeitoriaVo.descricaoComplementarBenfeitoria}"/> </td>
							<td align="center" width="10"><a href="javascript:void(excluirBenfeitoriaImovelUrbano(<c:out value="${contador.index}"></c:out>));">Excluir</a></td>
						</tr>
						</c:forEach>
						</table>
				</td>
			</tr>
			<tr> 
				<td width="278">&nbsp;</td>
				<td width="462">&nbsp;</td>
			</tr>			 
			</c:if>
			<tr>
				<td width="278" colspan="2">&nbsp;</td>
			</tr>
         <c:if test="${fichaImovelUrbanoVo.iptuVo.tipoITPU.valorCorrente == IPTU_INTEGRADO}">      
          <tr> 
            <td colspan="4" align="center">
               <table align="center" border="0" cellpadding="0" cellspacing="1" width="1000">
                  <tr>               
                     <td class="SEFAZ-TD-RotuloEntrada" width="580">Número Inscrição Imobiliária do IPTU:&nbsp;</td>
                     <td class="SEFAZ-TD-CampoEntrada">
                        <abaco:campoStringMaiuscula value="${fichaImovelUrbanoVo.iptuPrefeituraVo.numrInscricaoImovel}" maxlength="30" idCampo="<%=Form.CAMPO_NUMERO_MATRICULA_INSCRICAO_IMOVEL%>" name="<%=Form.CAMPO_NUMERO_MATRICULA_INSCRICAO_IMOVEL%>" size="32" > </abaco:campoStringMaiuscula><font color="red">*</font>
                     </td>
                     <td colspan="2"><input type="button" value="<%=Form.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_ADICIONAR_IMOVEL_URBANO_INTEGRADO%>" id="<%=Form.BOTAO_ADICIONAR_IMOVEL_URBANO_INTEGRADO%>" onClick="return adicionarImovelUrbanoIntegrado()"></input></td>
                  </tr>
                  <tr> 
                     <td class="SEFAZ-TD-RotuloEntrada" width="278">Nome Proprietário:&nbsp;</td>
                     <td class="SEFAZ-TD-CampoEntrada" colspan="3" >
                        <label ><c:out value="${fichaImovelUrbanoVo.iptuPrefeituraVo.nomeContribuinte}" ></c:out></label>
                        <input type="hidden" name="campo_hidden" value="<c:out value="${fichaImovelUrbanoVo.iptuPrefeituraVo.valrVenal}"></c:out>">
                     </td>
                  </tr>
				   <tr> 
					<td colspan="4">
						<div align="center">	
							</br>
						</div>
					</td>                    
                  </tr>
				  <tr> 
					<td colspan="4">
						<div align="center">
							<font color="red"><b>Obs.:"Valor Transmitido do Imóvel" corresponde ao valor "Percentual Transmitido do Imóvel"</b></font>
						</div>
						</br>
					</td>                    
                  </tr>
                  <tr> 
                     <td class="SEFAZ-TD-RotuloEntrada" width="278">
                        Valor Transmitido do Imóvel:&nbsp;     
                    </td>
                     <td class="SEFAZ-TD-CampoEntrada" width="300">
                         <abaco:campoMonetario name="<%=Form.CAMPO_VALOR_TOTAL%>" size="32" value="${fichaImovelUrbanoVo.valorMercadoTotalFormatado}" quantidadeDigitosInteiros="9" quantidadeCasasDecimais="2" onBlur="calculaValorTributavel()"></abaco:campoMonetario><font color="red">*</font>
                     </td>
                    <td class="SEFAZ-TD-RotuloEntrada" width="550">
                        <div align="left">
                          Percentual Transmitido do Imóvel(%):&nbsp;
                          </div>
                    </td>
                    <td class="SEFAZ-TD-CampoEntrada" width="100">
                        <abaco:campoMonetario  name="<%=Form.CAMPO_PERCENTUAL_TRANSMITIDO_IMOVEL%>"  quantidadeDigitosInteiros="3" quantidadeCasasDecimais="0" size="5" value="${fichaImovelUrbanoVo.valorPercentualTransmitidoFormatado}" onBlur="calculaValorTributavel();"/><font color="red">*</font>
                    </td>
                  </tr>
               </table>
             </td>              
           </tr>
         </c:if>
         <c:if test="${fichaImovelUrbanoVo.iptuVo.tipoITPU.valorCorrente == IPTU_ESTIMATIVA}">
            <tr> 
               <td class="SEFAZ-TD-RotuloEntrada" width="278">
                 Valor do Imóvel:&nbsp;     
              </td>
               <td class="SEFAZ-TD-CampoEntrada" width="300">
                   <abaco:campoMonetario name="<%=Form.CAMPO_VALOR_TOTAL%>" size="14" value="${fichaImovelUrbanoVo.valorMercadoTotalFormatado}" quantidadeDigitosInteiros="9" quantidadeCasasDecimais="2" onBlur="calculaValorTributavel()"></abaco:campoMonetario><font color="red">*</font>
               </td>
            </tr>
            <tr > 
               <td class="SEFAZ-TD-RotuloEntrada" width="278">Valor Venal (IPTU):&nbsp;</td>
               <td class="SEFAZ-TD-CampoEntrada" width="462">
                  <abaco:campoMonetario name="<%=Form.CAMPO_VALOR_VENAL%>" size="14" value="${fichaImovelUrbanoVo.valorVenalIptuFormatado}" quantidadeDigitosInteiros="9" quantidadeCasasDecimais="2" onBlur="calculaValorTributavel()" ></abaco:campoMonetario><font color="red">*</font>
               </td>
            </tr>
         </c:if>
          <c:if test="${fichaImovelUrbanoVo.iptuVo.tipoITPU.valorCorrente == IPTU_INTEGRADO}">
            <tr> 
               <td></td>
               <td>
                  <input type="hidden" name="<%=Form.CAMPO_VALOR_VENAL%>" id="<%=Form.CAMPO_VALOR_VENAL%>" value="<c:out value="${fichaImovelUrbanoVo.iptuPrefeituraVo.valorVenalFormatado}"></c:out>">
               </td>
            </tr>
         </c:if>
         </table>
         <table cellspacing="2" cellpadding="0" border="0" width="1000" align="center">
			 <tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="1450">Valor Arbitrado Transmitido do Imóvel:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" width="1000" id="tdVlrTributavel"></td><td class="SEFAZ-TD-CampoEntrada" width="2000" id="mensagemValorTributavel"></td>
			 </tr>
		 </table>       
		 <div style="display:block;" id="concordaValorAbitrado">			
            <div style="display:block" id="ValorIgual">            
               <jsp:include page="/giaitcd/util/ViewIncludeValoresIguais.jsp"/>               
            </div>
            <div style="display:block" id="ValorCalculado">
               <jsp:include page="/giaitcd/util/ViewIncludeConcordaPagamento.jsp"/>               
            </div>  
		 </div>
       </br>
		 <table cellspacing="2" cellpadding="0" border="0" width="1000" align="center">
         <tr> 
			<td colspan="4">
				<div align="center">	
					</br>
				</div>
			</td>                    
			</tr>
			<tr> 
				<td colspan="4" align="center">
				<c:if test="${not fichaImovelUrbanoVo.bemTributavelVo.alterar}">
					<input type="button" value="<%=Form.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_INCLUIR_IMOVEL_URBANO%>" id="btnConfirmar" onClick="confirmarImovelUrbano();"></input>
				</c:if>
				<c:if test="${fichaImovelUrbanoVo.bemTributavelVo.alterar}">
					<input type="button" value="<%=Form.TEXTO_BOTAO_ALTERAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_INCLUIR_IMOVEL_URBANO%>" id="btnConfirmar" onClick="confirmarImovelUrbano();"></input>
				</c:if>
				<input type="button" value="<%=Form.TEXTO_BOTAO_VOLTAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_VOLTAR_IMOVEL_URBANO%>" id="<%=Form.BOTAO_VOLTAR_IMOVEL_URBANO%>" onClick="voltarBemTributavel();"></input>
				<abaco:botaoCancelar></abaco:botaoCancelar>
				</td>
			</tr>
			<tr>
				<td colspan="4"><font color="red"><b>* Campos Obrigatórios</b></font></td>
			</tr>
			<tr> 
				<td colspan="4">&nbsp;</td>
			</tr>
		</table>
	</form>
	</CENTER>
	<g:mostrarRodape></g:mostrarRodape>
</body>
</html>
