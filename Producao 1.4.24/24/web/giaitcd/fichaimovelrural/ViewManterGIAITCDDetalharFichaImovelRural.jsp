<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManterGIAITCDDetalharFichaImovelRural.jsp
* Criação : Janeiro de 2008 / João Batista Padilha e Silva / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnEstadoConservacao"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoLogradouro"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%@page import ="br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo"%>
<%@page import ="br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo"%>
<%@page import ="br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo"%>
<%@page import ="br.gov.mt.sefaz.itc.model.generico.bemtributavel.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<%
FichaImovelRuralVo ficha =	(FichaImovelRuralVo) request.getAttribute("fichaImovelRuralVo");
String uf  = "";
if(ficha.getBemTributavelVo().getGiaITCDVo().getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
{
	uf = ((GIAITCDInventarioArrolamentoVo) ficha.getBemTributavelVo().getGiaITCDVo()).getUfAbertura().getSiglUf();
	pageContext.setAttribute("uf",uf);
}
%>
<c:set var="estadoAbertura" value="${uf}"></c:set>
<c:set var="tipoFicha" value="${bemTributavelVo.giaITCDVo.naturezaOperacaoVo.tipoGIA.valorCorrente}"></c:set>
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
	
	//JAVASCRIPT  FUNÇÕES CULTURA
	function validaCultura()
	{
		if (document.form.<%=FormITC.CAMPO_SELECT_CULTURA%>.options[document.form.<%=FormITC.CAMPO_SELECT_CULTURA%>.selectedIndex].value == "")
		{
				if(!verificaCamposW3c(document.form.<%=FormITC.CAMPO_SELECT_CULTURA%>,<%="'"+MensagemErro.VALIDAR_CULTURA_PARAMETRO_TIPO_SELECAO+"'"%>))
				return false;
		}
		if(document.form.<%=FormITC.CAMPO_AREA_CULTIVADA%>.value == "")
		{
			alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_AREA_CULTIVADA+"'"%>);
			return false;
		}
		else
		{		
			areaCultivada = replaceAll(document.form.<%=FormITC.CAMPO_AREA_CULTIVADA%>.value,".","");
			areaCultivada = replaceAll(areaCultivada,",",".");
			if(areaCultivada <= 0)
			{
				alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_AREA_CULTIVADA_ZERO+"'"%>);
				return false;			
			}
		}
		if(document.form.<%=FormITC.CAMPO_VALOR_HECTARE%>.value =="")
		{
			alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_VALOR_HECTARE+"'"%>);
			return false;
		}
		else
		{
			valorHectare = replaceAll(document.form.<%=FormITC.CAMPO_VALOR_HECTARE%>.value,".","");
			valorHectare = parseFloat(replaceAll(valorHectare,",","."));
			if(valorHectare <= 0 )
			{
				alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_VALOR_HECTARE_ZERO+"'"%>);
				return false;
			}
		}
		return true;
	}
	function calculaValorMercado()
	{
		areaCultivada = replaceAll(document.form.<%=FormITC.CAMPO_AREA_CULTIVADA%>.value,".","");
		areaCultivada = replaceAll(areaCultivada,",",".");
		valorHectare = replaceAll(document.form.<%=FormITC.CAMPO_VALOR_HECTARE%>.value,",","");
		valorHectare = replaceAll(valorHectare,",",".");
		if(areaCultivada != "" && valorHectare != "")
		{
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.PARAMETRO_CALCULAR_VALOR_MERCADO_CULTURA+"=1"%>';
			document.form.submit();
			return true;
		}
	}
	function adicionarCultura()
	{
		if(validaCultura())
		 {						
			 indiceCultura = document.form.<%=FormITC.CAMPO_SELECT_CULTURA%>.options[document.form.<%=FormITC.CAMPO_SELECT_CULTURA%>.selectedIndex].value;
			 document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_ADICIONAR_CULTURA_IMOVEL_RURAL+"="%>'+indiceCultura;
			 document.form.submit();
			 return true;
		 }
		 else
		 {						 
			return false;
		 }
	}
	function solicitarAlterarCultura(indiceCultura)
	{
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_SOLICITAR_ALTERAR_CULTURA_IMOVEL_RURAL+"="%>'+indiceCultura;
			document.form.submit();
			return true;
	}
	function alterarCultura(indiceCultura)
	{
		if(validaCultura())
		{
				indiceCultura = document.form.<%=FormITC.CAMPO_SELECT_CULTURA%>.options[document.form.<%=FormITC.CAMPO_SELECT_CULTURA%>.selectedIndex].value;
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_ALTERAR_CULTURA+"="%>'+indiceCultura;
				document.form.submit();
				return true;
		}
		else
		{
			return false;
		}
	}
	function excluirCultura(indiceCultura)
	{
		if(confirm('Deseja realmente excluir essa cultura?')){
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_EXCLUIR_CULTURA+"="%>'+indiceCultura;
				document.form.submit();
				return true;
		}
		else
		{
			return false;
		}
	}
	function adicionarDescricaoCultura()
	{
		var descricaoCultura = document.form.<%=FormITC.CAMPO_SELECT_CULTURA%>.options[document.form.<%=FormITC.CAMPO_SELECT_CULTURA%>.selectedIndex].text;					
		document.form.<%=FormITC.CAMPO_HIDDEN_DESCRICAO_CULTURA%>.value = descricaoCultura;
	}
	
	//JAVASCRIPT  FUNÇÕES REBANHO
	function validaRebanho()
	{
		if(document.form.<%=FormITC.CAMPO_SELECT_TIPO_REBANHO%>.options[document.form.<%=FormITC.CAMPO_SELECT_TIPO_REBANHO%>.selectedIndex].value == "")
		{	
			if(!verificaCamposW3c(document.form.<%=FormITC.CAMPO_SELECT_TIPO_REBANHO%>,<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_TIPO+"'"%>))
			return false;
		}
		if(document.form.<%=FormITC.CAMPO_QUANTIDADE_REBANHO%>.value == "")
		{
			alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_QUANTIDADE+"'"%>);
			return false;
		}
		else
		{
			quantidadeRebanho = replaceAll(document.form.<%=FormITC.CAMPO_QUANTIDADE_REBANHO%>.value,".","");
			quantidadeRebanho = parseFloat(replaceAll(quantidadeRebanho,",","."));
			if(quantidadeRebanho <= 0)
			{
				alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_QUANTIDADE_MAIOR_ZERO+"'"%>);
				return false;
			}
		}
		
		if(document.form.<%=FormITC.CAMPO_VALOR_MERCADO_REBANHO%>.value == "")
		{
			alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_VALOR_MERCADO+"'"%>);
			return false;
		}
		else
		{
			valorRebanho = replaceAll(document.form.<%=FormITC.CAMPO_VALOR_MERCADO_REBANHO%>.value,".","");
			valorRebanho = parseFloat(replaceAll(valorRebanho,",","."));
			if(valorRebanho <= 0)
			{
				alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_VALOR_MERCADO_ZERO+"'"%>);
				return false;
			}
		}
		
		return true;
	}
	function adicionarRebanho()
	{			
		if(!validaRebanho())
		{
			 return false;
		}
		else
		{
			indiceRebanho = document.form.<%=FormITC.CAMPO_SELECT_TIPO_REBANHO%>.options[document.form.<%=FormITC.CAMPO_SELECT_TIPO_REBANHO%>.selectedIndex].value;
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_ADICIONAR_REBANHO_IMOVEL_RURAL+"="%>'+indiceRebanho;
			document.form.submit();
			return true;
		}
	}
	function solicitarAlterarRebanho(indiceRebanho)
	{
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_SOLICITAR_ALTERAR_REBANHO_IMOVEL_RURAL+"="%>'+indiceRebanho;
			document.form.submit();
			return true;
	}
	function alterarRebanho(indiceRebanho)
	{
		if(!validaRebanho())
		{
			return false;
		}
		else
		{
		indiceRebanho = document.form.<%=FormITC.CAMPO_SELECT_TIPO_REBANHO%>.options[document.form.<%=FormITC.CAMPO_SELECT_TIPO_REBANHO%>.selectedIndex].value;
		document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_ALTERAR_REBANHO_IMOVEL_RURAL+"="%>'+indiceRebanho;
		document.form.submit();
		return true;
		}
	}		
	function excluirRebanho(indiceRebanho)
	{
		if(confirm('Deseja realmente excluir esse rebanho?')){
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_EXCLUIR_REBANHO_IMOVEL_RURAL+"="%>'+indiceRebanho;
				document.form.submit();
				return true;
		}
		else
		{
			return false;
		}
	}
	function adicionarDescricaoRebanho()
	{
		var descricaoRebanho = document.form.<%=FormITC.CAMPO_SELECT_TIPO_REBANHO%>.options[document.form.<%=FormITC.CAMPO_SELECT_TIPO_REBANHO%>.selectedIndex].text;					
		document.form.<%=FormITC.CAMPO_HIDDEN_DESCRICAO_REBANHO%>.value = descricaoRebanho;
	}	
	//JAVASCRIPT  FUNÇÕES CONSTRUÇÕES
	function validaConstrucao()
	{
		if(document.form.<%=FormITC.CAMPO_SELECT_TIPO_CONSTRUCAO%>.options[document.form.<%=FormITC.CAMPO_SELECT_TIPO_CONSTRUCAO%>.selectedIndex].value == '')
		{
			if(!verificaCamposW3c(document.form.<%=FormITC.CAMPO_SELECT_TIPO_CONSTRUCAO%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_TIPO_CONSTRUCAO+"'"%>))
				return false;
		}
		if(document.form.<%=FormITC.CAMPO_SELECT_ESTADO_CONSERVACAO%>.options[document.form.<%=FormITC.CAMPO_SELECT_ESTADO_CONSERVACAO%>.selectedIndex].value == "")
		{
			alert(<%="'"+MensagemErro.VALIDAR_PARAMETRO_ESTADO_CONSERVACAO+"'"%>)
			return false;
		}
		if(!verificaCamposW3c(document.form.<%=FormITC.CAMPO_VALOR_MERCADO_CONSTRUCAO%>,<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_VALOR_MERCADO+"'"%>))
		{
				return false;
		}
		if(document.form.<%=FormITC.CAMPO_VALOR_MERCADO_CONSTRUCAO%>.value != '')
		{
			valorMercadoConstrucao = replaceAll(document.form.<%=FormITC.CAMPO_VALOR_MERCADO_CONSTRUCAO%>.value,".","");
			valorMercadoConstrucao = parseFloat(replaceAll(valorMercadoConstrucao,",","."));
			if(valorMercadoConstrucao <= 0 )
			{
				alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_VALOR_MERCADO_ZERO+"'"%>)
				return false;
			}
		}
		
		return true;
	}
	function adicionarConstrucao()
	{					
		if(!validaConstrucao())
		{
			return false;
		}
		else
		{
			indiceConstrucao = document.form.<%=FormITC.CAMPO_SELECT_TIPO_CONSTRUCAO%>.options[document.form.<%=FormITC.CAMPO_SELECT_TIPO_CONSTRUCAO%>.selectedIndex].value;
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_ADICIONAR_CONSTRUCOES_IMOVEL_RURAL+"="%>'+indiceConstrucao;
			document.form.submit();
			return true;
		}
	}
	function solicitarAlterarConstrucao(indiceConstrucao)
	{
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_SOLICITAR_ALTERAR_CONSTRUCOES_IMOVEL_RURAL+"="%>'+indiceConstrucao;
			document.form.submit();
			return true;
	}
	function alterarConstrucao(indiceConstrucao)
	{
		if(!validaConstrucao())
		{
			return false;
		}
		else
		{
			indiceConstrucao = document.form.<%=FormITC.CAMPO_SELECT_TIPO_CONSTRUCAO%>.options[document.form.<%=FormITC.CAMPO_SELECT_TIPO_CONSTRUCAO%>.selectedIndex].value;
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_ALTERAR_CONSTRUCOES_IMOVEL_RURAL+"="%>'+indiceConstrucao;
			document.form.submit();
			return true;
		}
	}
	function excluirConstrucao(indiceConstrucao)
	{
		if(confirm('Deseja realmente excluir a construção?')){
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_EXCLUIR_CONSTRUCOES_IMOVEL_RURAL+"="%>'+indiceConstrucao;
				document.form.submit();
				return true;
		}
		else
		{
			return false;
		}
	}
	function adicionarDescricaoConstrucao()
	{
		var descricaoConstrucao = document.form.<%=FormITC.CAMPO_SELECT_TIPO_CONSTRUCAO%>[document.form.<%=FormITC.CAMPO_SELECT_TIPO_CONSTRUCAO%>.selectedIndex].text;					
		document.form.<%=FormITC.CAMPO_HIDDEN_DESCRICAO_CONSTRUCAO%>.value = descricaoConstrucao;
	}	
	//JAVASCRIPT  FUNÇÕES BENFEITORIA
	function adicionarBenfeitoria()
	{
		if(!verificaCamposW3c(document.form.<%=FormITC.CAMPO_SELECT_BENFEITORIA%>,<%="'"+MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL_BENFEITORIA+"'"%>))
		 {						
			 return false;
		 }
		 else
		 {						 
			 indiceBenfeitoria = document.form.<%=FormITC.CAMPO_SELECT_BENFEITORIA%>.options[document.form.<%=FormITC.CAMPO_SELECT_BENFEITORIA%>.selectedIndex].value;
			 document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_ADICIONAR_BENFEITORIA_IMOVEL_RURAL+"="%>'+indiceBenfeitoria;
			 document.form.submit();
			 return true;
		 }
	}
	function excluirBenfeitoria(indiceBenfeitoria)
	{
		if(confirm('Deseja realmente excluir essa Benfeitoria?')){
						document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_EXCLUIR_BENFEITORIA_IMOVEL_RURAL+"="%>'+indiceBenfeitoria;
						document.form.submit();
						return true;
		}
		else
		{
			return false;
		}
	}
	function adicionarDescricaoBenfeitoria()
	{
		var descricaoBenfeitoria = document.form.<%=FormITC.CAMPO_SELECT_BENFEITORIA%>[document.form.<%=FormITC.CAMPO_SELECT_BENFEITORIA%>.selectedIndex].text;					
		document.form.<%=FormITC.CAMPO_HIDDEN_DESCRICAO_BENFEITORIA%>.value = descricaoBenfeitoria;
	}	
	
	
	
	function pesquisarCep()
	{
		if(!verificaCamposW3c(document.form.<%=FormITC.CAMPO_CEP%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_CEP+"'"%>))
		{	
			return false;
		}
		else
		{
			cep = document.form.<%=FormITC.CAMPO_CEP%>.value;
			consultaCep = parseFloat(cep);
			if(consultaCep <= 0 || cep.length < 8)
			{
				alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CEP_VALIDO+"'"%>)
				return false;
			}
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_PESQUISAR_CEP_IMOVEL_RURAL+"="%>'+cep;
			document.form.submit();
			return true;
		}
	}
	
	function calculaValorTotalRebanho()
	{
		quantidade = document.form.<%=FormITC.CAMPO_QUANTIDADE_REBANHO%>.value;
		valorMercado = replaceAll(document.form.<%=FormITC.CAMPO_VALOR_MERCADO_REBANHO%>.value,".","");
		valorMercado = replaceAll(valorMercado,",",".");
		if (quantidade != "" && valorMercado != "")
		{
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.PARAMETRO_CALCULAR_VALOR_TOTAL_REBANHO+"=1"%>';
			document.form.submit();
			return true;		
		}
	 }
		 
	//VALIDAÇÕES GENÉRICAS
	function chamaCalculoGeral()
	{
		document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_FICHA_IMOVEL_RURAL_CALCULO_GERAL+"=1"%>';
		document.form.submit();	
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
	
	function escreveVlrTot(valor)
	{
		vlr = floatTomoeda(valor);
		document.write(vlr);
	}
	
	function verificaJazidas()
	{
		tipo = buscarTipoNavegador();
		selectPossuiJazidas = document.form.<%=FormITC.CAMPO_SELECT_POSSUI_JAZIDAS%>;
		if(selectPossuiJazidas.options[selectPossuiJazidas.selectedIndex].value == "2")
		{
			document.getElementById("idOcultaJazidas").style.display = 'none';
			document.form.<%=FormITC.CAMPO_VALOR_ACESSOES_NATURAIS%>.value = '';
		}
		else
		{
			document.getElementById("idOcultaJazidas").style.display = tipo;
		}
	}
	
	function verificaPastagens()
	{
		tipo = buscarTipoNavegador();
		selectPossuiPastagens = document.form.<%=FormITC.CAMPO_SELECT_POSSUI_PASTAGENS_NATURAIS_ARTIFICIAIS%>;
		if (selectPossuiPastagens.options[selectPossuiPastagens.selectedIndex].value == 2)
		{
			document.form.<%=FormITC.CAMPO_TAMANHO_PASTAGENS%>.value = '';
			document.form.<%=FormITC.CAMPO_VALOR_PASTAGENS%>.value = '';
			document.getElementById("idOcultaCamposPastagens").style.display = 'none';
			document.getElementById("idOcultaCamposPastagens1").style.display = 'none';
		}
		else
		{	
			document.getElementById("idOcultaCamposPastagens").style.display = tipo;
			document.getElementById("idOcultaCamposPastagens1").style.display = tipo;
		}
	}
	
   function setaDefaultStyleDisplayPastagem()
   {
      document.getElementById("idOcultaCamposPastagens").style.display = buscarTipoNavegador();
      document.getElementById("idOcultaCamposPastagens1").style.display = buscarTipoNavegador();
   }
   
	function verificaAreaTotalImovel()
	{
		areaTotalImovel = document.form.<%=FormITC.CAMPO_AREA_TOTAL_IMOVEL%>.value;
		areaTotalCultura = document.form.<%=FormITC.CAMPO_AREA_CULTIVADA%>.value;
		areaTotalPastagens = document.form.<%=FormITC.CAMPO_TAMANHO_PASTAGENS%>.value;
		
		if(areaTotalImovel != '')
		{
			areaTotalImovel = replaceAll(areaTotalImovel,".","");
			areaTotalImovel = parseFloat(replaceAll(areaTotalImovel,",","."));
		}
		else
		{
			areaTotalImovel = 0;
		}
		
		if(areaTotalCultura != '')
		{
			areaTotalCultura = replaceAll(areaTotalCultura,".","");
			areaTotalCultura = parseFloat(replaceAll(areaTotalCultura,",","."));
		}
		else
		{
			areaTotalCultura = 0;
		}
		
		if(areaTotalPastagens != '')
		{
			areaTotalPastagens = replaceAll(areaTotalPastagens,".","");
			areaTotalPastagens = parseFloat(replaceAll(areaTotalPastagens,",","."));
		}
		else
		{
			areaTotalPastagens = 0;
		}
		
		
		if((areaTotalCultura) > areaTotalImovel)
		{
			alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_AREA_CULTIVADA_MAIOR_AREA_TOTAL+"'"%>);
			return false;
		}
		else if(areaTotalPastagens > areaTotalImovel)
		{
			alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_AREA_PASTAGENS_MAIOR_AREA_TOTAL+"'"%>);
			return false;
		}
		else if((areaTotalCultura + areaTotalPastagens) > areaTotalImovel)
		{
			alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_AREA_TOTAL_MENOR_SOMA_AREAS+"'"%>);
			return false;
		}
		else
		{
			return true;
		}
		
	}
	
	function voltarBemTributavel()
		{
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_VOLTAR_IMOVEL_RURAL+"=1"%>';
			document.form.submit();	
		}
		
	function validaFormulario()
	{		
		formulario = document.form;
		tipo = buscarTipoNavegador();
		if(!verificaCamposW3c(formulario.<%=FormITC.CAMPO_DENOMINACAO_IMOVEL%>,<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_DENOMINACAO_VALIDA+"'"%>))
		{
			return false;
		}
		if (!verificaCamposW3c(formulario.<%=FormITC.CAMPO_LOGRADOURO%>, <%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_LOGRADOURO_VALIDO+"'"%>))
		{
			return false;
		}
		if(!verificaCamposW3c(formulario.<%=FormITC.CAMPO_PONTO_REFERENCIA%>,<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_PONTO_REFERENCIA_VALIDO+"'"%>))
		{
			return false;
		}
		
      if (!verificaCamposW3c(formulario.<%=FormITC.CAMPO_KM_PERIMETRO_URBANO%>,<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_QUANTIDADE_DISTANCIA+"'"%>))
		{
			return false;
		}
      if (!verificaCamposW3c(formulario.<%=FormITC.CAMPO_KM_VIA_PAVIMENTADA%>,<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_QUANTIDADE_DISTANCIA_ASFALTO+"'"%>))
		{
			return false;
		}
      
      /*
		if(formulario.<%=FormITC.CAMPO_KM_PERIMETRO_URBANO%>.value != '')
		{	
			campoKm = replaceAll(formulario.<%=FormITC.CAMPO_KM_PERIMETRO_URBANO%>.value,".","");
			campoKm = parseFloat(replaceAll(campoKm,",","."));
			if(campoKm < 0 )
			{
				alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_QUANTIDADE_DISTANCIA_ZERO+"'"%>);
				return false;
			}
		}
      */
		if(!verificaCamposW3c(formulario.<%=FormITC.CAMPO_AREA_TOTAL_IMOVEL%>,<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_AREA_TOTAL+"'"%>))
		{
			return false;
		}
		if(formulario.<%=FormITC.CAMPO_AREA_TOTAL_IMOVEL%>.value != '')
		{
			campoAreaTotal = replaceAll(formulario.<%=FormITC.CAMPO_AREA_TOTAL_IMOVEL%>.value,".","");
			campoAreaTotal = parseFloat(replaceAll(campoAreaTotal,",","."));
			if(campoAreaTotal <= 0 )
			{
				alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_AREA_TOTAL_ZERO+"'"%>);
				return false;
			}
		}
		if(!verificaAreaTotalImovel())
		{
			return false;
		}
		if (document.getElementById("idOcultaCamposPastagens").style.display == tipo)
		{
			if(!verificaCamposW3c(formulario.<%=FormITC.CAMPO_TAMANHO_PASTAGENS%>,<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_AREA_PASTAGENS+"'"%>))
			{
				return false;
			}
			if(formulario.<%=FormITC.CAMPO_TAMANHO_PASTAGENS%>.value != '')
			{
				tamanhoPastagens = replaceAll(formulario.<%=FormITC.CAMPO_TAMANHO_PASTAGENS%>.value,".","");
				tamanhoPastagens = parseFloat(replaceAll(tamanhoPastagens,",","."));
				if(tamanhoPastagens <= 0)
				{
					alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_AREA_PASTAGENS_ZERO+"'"%>);
					return false;
				}
			}
			if(!verificaCamposW3c(formulario.<%=FormITC.CAMPO_VALOR_PASTAGENS%>,<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_VALOR_PASTAGENS+"'"%>))
		  {
			return false;
		  }
			var valorPastagens = formulario.<%=FormITC.CAMPO_VALOR_PASTAGENS%>.value;
			if(valorPastagens != '')
			{
				valorPastagens = parseFloat(replaceAll(valorPastagens,",","."));
			}
			if ( valorPastagens <=0)
			{
				alert('<%=MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_VALOR_PASTAGENS_MAIOR_ZERO%>');
			return false;
			}
		}
		if (document.getElementById("idOcultaJazidas").style.display == tipo)
		{
			if(!verificaCamposW3c(formulario.<%=FormITC.CAMPO_VALOR_ACESSOES_NATURAIS%>,<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_VALOR_ACESSAO_NATURAL+"'"%>))
			{
				return false;
			}
			var valorAcessoes = formulario.<%=FormITC.CAMPO_VALOR_ACESSOES_NATURAIS%>.value;
			if(valorAcessoes != "")
			{
				valorAcessoes = parseFloat(replaceAll(valorAcessoes,",","."));
			}
			if ( valorAcessoes <=0)
			{
				alert('<%=MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_VALOR_DA_ACESSOES_NATURAIS_MAIOR_ZERO%>');
			return false;
			}
		}
		if(!verificaCamposW3c(formulario.<%=FormITC.CAMPO_VALOR_IMOVEL%>,<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_VALOR_IMOVEL+"'"%>))
		{
			return false;
		}
		if(formulario.<%=FormITC.CAMPO_VALOR_IMOVEL%>.value != '')
		{
			valorImovel = replaceAll(formulario.<%=FormITC.CAMPO_VALOR_IMOVEL%>.value,".","");
			valorImovel = parseFloat(replaceAll(valorImovel,",","."));
			if(valorImovel <= 0)
			{
				alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_VALOR_IMOVEL_ZERO+"'"%>);
				return false;
			}
		}
/*    Trecho de código removido conforme solicitação 000033914/2015-85      
		if(formulario.<%=FormITC.CAMPO_VALOR_MAQUINAS_IMPLEMENTOS_AGRICOLAS%>.value != '')
		{
			valorMaquinas = replaceAll(formulario.<%=FormITC.CAMPO_VALOR_MAQUINAS_IMPLEMENTOS_AGRICOLAS%>.value,".","");
			valorMaquinas = parseFloat(replaceAll(valorMaquinas,",","."));
			if(valorMaquinas <= 0)
			{
				alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_VALOR_MAQUINAS_IMPLEMENTOS_AGRICOLAS_ZERO+"'"%>);
				return false;
			}
		}*/
		if(formulario.<%=FormITC.CAMPO_OUTROS_VALORES%>.value != '')
		{
			valorOutros = replaceAll(formulario.<%=FormITC.CAMPO_OUTROS_VALORES%>.value,".","");
			valorOutros = parseFloat(replaceAll(valorOutros,",","."));
			if(valorOutros <= 0)
			{
				alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_VALOR_OUTROS_VALORES_ZERO+"'"%>);
				return false;
			}
		}
		if(formulario.<%=FormITC.CAMPO_VALOR_VENAL%>.value != '')
		{
			valorVenal = replaceAll(formulario.<%=FormITC.CAMPO_VALOR_VENAL%>.value,".","");
			valorVenal = parseFloat(replaceAll(valorVenal,",","."));
			if(valorVenal <= 0)
			{
				alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_VALOR_ITR_ZERO+"'"%>);
				return false;
			}
		}
		
      <c:if test="${fichaImovelRuralVo.valorTributavelFormatado ne fichaImovelRuralVo.valorTotalMercadoFormatado}">
		 var campoConcorda = document.getElementById('<%=Form.CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE%>'); 
		 if(campoConcorda.length > 0)
		 {	 
			var campoConcordaSelecionado = campoConcorda.options[campoConcorda.selectedIndex].text	
			
			  if(campoConcordaSelecionado.includes('Selecione')){
				alert('Favor informar se concorda com o valor arbitrado.');   
				return false;
			 }
		 }
		</c:if>
		
		<c:if test="${empty fichaImovelRuralVo.enderecoVo.cep.localidade.nomeLocalidade}">
			alert(<%="'"+MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_CEP+"'"%>);
			return false;
		</c:if>
			
		<c:if test="${fichaImovelRuralVo.bemTributavelVo.alterar}">
			if (confirm("Confirma a alteração da Ficha de Imóvel Rural?"))
			{
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_INCLUIR_IMOVEL_RURAL+"=1"%>';
				document.form.submit();	
			}
		</c:if>
		
		<c:if test="${!fichaImovelRuralVo.bemTributavelVo.alterar}">
			if (confirm("Confirma a inclusão da Ficha de Imóvel Rural?"))
			{
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_INCLUIR_IMOVEL_RURAL+"=1"%>';
				document.form.submit();	
			}
		</c:if>
	 }
	</script>
	<jsp:include page="/util/ViewVerificaErro.jsp"/>
	<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro();">
	<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
	<CENTER>
	<form method="POST" name="form"  action="#">
	<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
		<tr>
			<td colspan="5"><div align="center"><font face="Verdana, Arial, Helvetica, sans-serif" size="3"><b>Ficha Imóvel Rural</b></font></div></td>
		</tr>
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>
		<tr align="right">
			<td colspan="5"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
		</tr>		
		<tr class="SEFAZ-TR-Titulo" align="center"> 
			<td colspan="5">Dados do registro</td>
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloEntrada">Denomina&ccedil;&atilde;o do Im&oacute;vel:&nbsp;</td>
			<td colspan="3" class="SEFAZ-TD-ComboBox">
				<input type="text" id="<%=FormITC.CAMPO_DENOMINACAO_IMOVEL%>" name="<%=FormITC.CAMPO_DENOMINACAO_IMOVEL%>" value="<c:out value="${fichaImovelRuralVo.descricaoDenominacao}"></c:out>" maxlength="50" onblur="toUpperCaseW3c(this)"></input><font color="red">*</font>
			</td>
		</tr>
		<tr> 
			<td class="SEFAZ-TD-RotuloEntrada" width="348">Endereço do Imóvel:&nbsp;</td>
			<td colspan="3" class="SEFAZ-TD-CampoEntrada"> 
				 <input type="text" class="SEFAZ-INPUT-Text" id="<%=FormITC.CAMPO_LOGRADOURO%>" name="<%=FormITC.CAMPO_LOGRADOURO%>" value="<c:out value="${fichaImovelRuralVo.enderecoVo.logradouro}"></c:out>" size="40" onblur="toUpperCaseW3c(this)" maxlength="80"></input><font color="red">*</font>
			</td>
		</tr>
		<tr> 
			<td class="SEFAZ-TD-RotuloEntrada" width="348">Ponto de Referencia:</td>
			<td colspan="3" class="SEFAZ-TD-CampoEntrada"><input type="text" class="SEFAZ-INPUT-Text" id="<%=FormITC.CAMPO_PONTO_REFERENCIA%>" name="<%=FormITC.CAMPO_PONTO_REFERENCIA%>" size="40" maxlength="100" value="<c:out value="${fichaImovelRuralVo.enderecoVo.pontoReferencia}"></c:out>" onblur="toUpperCaseW3c(this)"></input><font color="red">*</font></td>
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloEntrada">CEP:&nbsp;</td>
			<td colspan="3" class="SEFAZ-TD-CampoEntrada"><abaco:campoApenasNumero maxlength="8" name="<%=FormITC.CAMPO_CEP%>" size="12" value="" onKeyPress="return formataNumero(this,event);" onBlur="javascript: pesquisarCep();"></abaco:campoApenasNumero><font color="red">*</font></td>
		</tr>
		<c:if test="${not empty fichaImovelRuralVo.enderecoVo.cep.localidade.uf.siglUf}"> 
			<tr> 
				<td class="SEFAZ-TD-RotuloSaida" width="348">Localidade:&nbsp;</td>
				<td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelRuralVo.enderecoVo.cep.localidade.nomeLocalidade}"></c:out></td>
			</tr>
			<tr> 
				<td class="SEFAZ-TD-RotuloSaida" width="348">UF:&nbsp;</td>
				<td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelRuralVo.enderecoVo.cep.localidade.uf.siglUf}"></c:out></td>
			</tr>
			<tr> 
				<td class="SEFAZ-TD-RotuloSaida" width="348">CEP:&nbsp;</td>
				<td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelRuralVo.enderecoVo.cep.codgCep}"></c:out></td>
			</tr>
		</c:if>
		<tr> 
			<td class="SEFAZ-TD-RotuloEntrada" width="348">Distância em KM do Perímetro Urbano:&nbsp;</td>			
			 <td colspan="3" class="SEFAZ-TD-CampoEntrada"><abaco:campoMonetario name="<%=FormITC.CAMPO_KM_PERIMETRO_URBANO%>" quantidadeDigitosInteiros="8" size="15" value="${fichaImovelRuralVo.quantidadeDistanciaFormatado}" quantidadeCasasDecimais="4"/><font color="red">*</font></td>
		</tr>
      <tr> 
			<td class="SEFAZ-TD-RotuloEntrada" width="348">Distância em KM até a rodovia pavimentada:&nbsp;</td>			
			 <td colspan="3" class="SEFAZ-TD-CampoEntrada"><abaco:campoMonetario name="<%=FormITC.CAMPO_KM_VIA_PAVIMENTADA%>" quantidadeDigitosInteiros="8" size="15" value="${fichaImovelRuralVo.distanciaAsfaltoFormatado}" quantidadeCasasDecimais="4"/><font color="red">*</font></td>
		</tr>
		<tr> 
			<td class="SEFAZ-TD-RotuloEntrada" width="348">Área Total do Imóvel (hectares):&nbsp;</td>
			 <td colspan="3" class="SEFAZ-TD-CampoEntrada"><abaco:campoMonetario name="<%=FormITC.CAMPO_AREA_TOTAL_IMOVEL%>" quantidadeDigitosInteiros="8" size="15" value="${fichaImovelRuralVo.areaTotalFormatado}" quantidadeCasasDecimais="4" onBlur="verificaAreaTotalImovel();"/><font color="red">*</font></td>
		</tr>
		<tr> 
			<td class="SEFAZ-TD-RotuloEntrada" width="348">Número INDEA/MT:&nbsp;</td>
			<td colspan="3" class="SEFAZ-TD-CampoEntrada"><input type="text" class="SEFAZ-INPUT-Text" id="<%=FormITC.CAMPO_NUMERO_INDEA%>" name="<%=FormITC.CAMPO_NUMERO_INDEA%>"  size="20" value="<c:out value="${fichaImovelRuralVo.numericoIndeaFormatado}"></c:out>" onkeypress="return formataNumero(this,event);" maxlength="10"></input></td>
		</tr>
		<tr> 
			<td class="SEFAZ-TD-RotuloEntrada" width="348">Código na Receita Federal:&nbsp;</td>
			<td colspan="3" class="SEFAZ-TD-CampoEntrada"><input type="text" class="SEFAZ-INPUT-Text" id="<%=FormITC.CAMPO_CODIGO_RECEITA_FEDERAL%>" name="<%=FormITC.CAMPO_CODIGO_RECEITA_FEDERAL%>"  size="20" value="<c:out value="${fichaImovelRuralVo.codigoReceitaFederalFormatado}"></c:out>" onkeypress="return formataNumero(this,event);" maxlength="10"></input></td>
		</tr>
		
		<tr class="SEFAZ-TR-Titulo" title="Para exibir ou ocultar Dados Cultura clique imagem ao lado">
			<td colspan="4">Dados Cultura</td>
			<td width="7" bgcolor="White"><img id="cultura" src="/imagens/expand.gif" alt="Exibir Cultura" width="11" border="0" onClick="exibirOcultarInformacoesW3c('idCultura',this);" style="cursor:pointer"/></td>
		</tr>
		</table>
		
			<div id="idCultura" style="display:none">
			<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
				<tr class="SEFAZ-TR-Titulo">
					<td class="SEFAZ-TD-RotuloEntrada"  width="348">Selecione a Cultura:&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-ComboBox">
					<select name="<%=FormITC.CAMPO_SELECT_CULTURA%>" id ="<%=FormITC.CAMPO_SELECT_CULTURA%>" onchange="adicionarDescricaoCultura();">
						<option value=""><%=FormITC.SELECIONE%></option>
						<c:forEach var="culturaVo" items="${fichaImovelRuralVo.fichaImovelRuralCulturaVo.culturaVo.collVO}">
						<c:if test="${fichaImovelRuralVo.fichaImovelRuralCulturaVo.culturaVo.codigo == culturaVo.codigo}">
						<option value="<c:out value="${culturaVo.codigo}"></c:out>" selected><c:out value="${culturaVo.descricaoCultura}"></c:out></option>
						</c:if>
						<c:if test="${fichaImovelRuralVo.fichaImovelRuralCulturaVo.culturaVo.codigo != culturaVo.codigo}">
						<option value="<c:out value="${culturaVo.codigo}"></c:out>"><c:out value="${culturaVo.descricaoCultura}"></c:out></option>
						</c:if>							
						</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
						<td class="SEFAZ-TD-RotuloEntrada" width="348">Descrição da Cultura:&nbsp;</td>
						<td colspan="3" class="SEFAZ-TD-CampoEntrada"><abaco:campoStringMaiuscula maxlength="50" name="<%=FormITC.CAMPO_DESCRICAO_COMPLEMENTAR_CULTURA%>" size="40" value="${fichaImovelRuralVo.fichaImovelRuralCulturaVo.descricaoComplementarCultura}"/> </td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloEntrada" width="348">Área Cultivada (em hectares):&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-ComboBox"><abaco:campoMonetario name="<%=FormITC.CAMPO_AREA_CULTIVADA%>" quantidadeDigitosInteiros="8" size="15" value="${fichaImovelRuralVo.fichaImovelRuralCulturaVo.areaCultivadaFormatado}" quantidadeCasasDecimais="4" onBlur="verificaAreaTotalImovel();calculaValorMercado();"/></td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor do Hectare:&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-CampoEntrada"><abaco:campoMonetario name="<%=FormITC.CAMPO_VALOR_HECTARE%>" quantidadeDigitosInteiros="9" size="15" value="${fichaImovelRuralVo.fichaImovelRuralCulturaVo.valorHectareFormatado}" quantidadeCasasDecimais="2" onBlur="calculaValorMercado();"/> </td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor de Mercado:&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-CampoEntrada">R$ <c:out value="${fichaImovelRuralVo.fichaImovelRuralCulturaVo.valorMercadoFormatado}"/></td>
				<tr>
				<tr>
					<td colspan="4" align="center">
					<input type="hidden" name="<%=FormITC.CAMPO_HIDDEN_DESCRICAO_CULTURA%>" value="<c:out value="${fichaImovelRuralVo.fichaImovelRuralCulturaVo.culturaVo.descricaoCultura}"></c:out>">
					<c:if test="${alterarCultura == null}">
					<input type="button" name="<%=FormITC.BOTAO_ADICIONAR%>" value="<%=FormITC.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" onClick="adicionarCultura();"></input>
					</c:if>
					<c:if test="${alterarCultura != null}">
					<input type="button" name="<%=FormITC.BOTAO_ALTERAR%>" value="<%=FormITC.TEXTO_BOTAO_ALTERAR%>" class="SEFAZ-INPUT-Botao" onClick="alterarCultura();"></input>
					</c:if>
					</td>
				</tr>
				<c:if test="${not empty fichaImovelRuralVo.fichaImovelRuralCulturaVo.collVO}">
				<tr>
					<td colspan="5">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center">
							<tr class="SEFAZ-TR-Titulo">
								<td colspan="7">Cultura</td>
							</tr>
							<tr class="SEFAZ-TR-SubTitulo">
								<td width="20%" align="left">Cultura</td>
								<td width="20" align="left">Descrição da Cultura</td>
								<td width="17%" align="center">Área Cultivada</td>
								<td width="10%" align="right">Valor do Hectare</td>
								<td width="15%" align="right">Valor de Mercado</td>
								<td width="9%" align="center">&nbsp;</td>
								<td width="9%" align="center">&nbsp;</td>
							</tr>
							<c:forEach var="fichaImovelRuralCulturaVo" items="${fichaImovelRuralVo.fichaImovelRuralCulturaVo.collVO}" varStatus="contador">
							<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
							<c:if test="${contador.count % 2 != 0}">
							<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
							</c:if>
							<tr class="<c:out value="${linhaEstilo}"></c:out>">
								<td width="20%" align="left"><c:out value="${fichaImovelRuralCulturaVo.culturaVo.descricaoCultura}"></c:out></td>
								<td width="20%" align="left"><c:out value="${fichaImovelRuralCulturaVo.descricaoComplementarCultura}"/></td>
								<td width="17%" align="center"><c:out value="${fichaImovelRuralCulturaVo.areaCultivadaFormatado}" />ha</td>
								<td width="10%" align="right">R$ <c:out value="${fichaImovelRuralCulturaVo.valorHectareFormatado}"/></td>
								<td width="15%" align="right">R$ <c:out value="${fichaImovelRuralCulturaVo.valorMercadoFormatado}"></c:out></td>
								<td width="9%" align="center"><a href="javascript:void(solicitarAlterarCultura(<c:out value="${contador.index}"></c:out>));">Alterar</a></td>
								<td width="9%" align="center"><a href="javascript:void(excluirCultura(<c:out value="${contador.index}"></c:out>));">Excluir</a></td>
							</tr>
							</c:forEach>
							<tr>
							<td colspan="5">&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
				</c:if>
				</table>
			</div>
		<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
		
      <tr class="SEFAZ-TR-Titulo">
			<td colspan="5">Pastagens/Acess&otilde;es</td>
		</tr>
		<tr>
            <td class="SEFAZ-TD-RotuloEntrada" width="348">Possui pastagens&nbsp; naturais e/ou cultivadas:&nbsp;</td>
            <td colspan="4" class="SEFAZ-TD-ComboBox">
					<abaco:campoSelectDominio 
						ajuda="" 
						classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" 
						name="<%=FormITC.CAMPO_SELECT_POSSUI_PASTAGENS_NATURAIS_ARTIFICIAIS%>" 
						tabIndex="" 
						idCampo="<%=FormITC.CAMPO_SELECT_POSSUI_PASTAGENS_NATURAIS_ARTIFICIAIS%>" 
						onChange="verificaPastagens();" 
						mostrarSelecione="false" 
						opcaoSelecionada="${fichaImovelRuralVo.situacaoPastagem.valorCorrente}" 
						valorDefault="<%=DomnSimNao.SIM%>">
					</abaco:campoSelectDominio>
				</td>
      </tr>

		<tr id="idOcultaCamposPastagens">
			<td class="SEFAZ-TD-RotuloEntrada" width="348">Tamanho das Pastagens:&nbsp;</td>
			<td colspan="4" class="SEFAZ-TD-ComboBox"><abaco:campoMonetario name="<%=FormITC.CAMPO_TAMANHO_PASTAGENS%>" quantidadeDigitosInteiros="8" size="15" value='${fichaImovelRuralVo.areaPastagemFormatado}' quantidadeCasasDecimais="4" onBlur="verificaAreaTotalImovel();"/>	hectares<font color="red">*</font></td>
		</tr>
		<tr id="idOcultaCamposPastagens1">
			<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor das Pastagens:&nbsp;</td>
			<td colspan="4" class="SEFAZ-TD-ComboBox"><abaco:campoMonetario  name="<%=FormITC.CAMPO_VALOR_PASTAGENS%>" size="15" value="${fichaImovelRuralVo.valorPastagemFormatado}" quantidadeDigitosInteiros="9" quantidadeCasasDecimais="2" onBlur="chamaCalculoGeral();"></abaco:campoMonetario><font color="red">*</font></td>
		</tr>
		<c:if test="${not empty fichaImovelRuralVo.situacaoPastagem.textoCorrente}">
		<script type="text/javascript">verificaPastagens();</script>
		</c:if>
		<tr>
			<td class="SEFAZ-TD-RotuloEntrada" width="348">Possui jazidas minerais, fontes de &aacute;gua radioativa, t&eacute;rmicas e/ou minerais ?&nbsp;</td>
			<td colspan="4" class="SEFAZ-TD-ComboBox">
				<abaco:campoSelectDominio 
					ajuda="" 
					classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" 
					name="<%=FormITC.CAMPO_SELECT_POSSUI_JAZIDAS%>" 
					tabIndex="" 
					idCampo="<%=FormITC.CAMPO_SELECT_POSSUI_JAZIDAS%>" 
					mostrarSelecione="false" 
					onChange="verificaJazidas()" 
					opcaoSelecionada="${fichaImovelRuralVo.situacaoAcessaoNatural.valorCorrente}" valorDefault="<%=DomnSimNao.NAO%>">
				</abaco:campoSelectDominio>
			</td>
		</tr>
		<tr id="idOcultaJazidas" style="display:none;">
			<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor das Acess&otilde;es Naturais:&nbsp;</td>
			<td colspan="4" class="SEFAZ-TD-ComboBox"><abaco:campoMonetario  name="<%=FormITC.CAMPO_VALOR_ACESSOES_NATURAIS%>" size="15" value="${fichaImovelRuralVo.valorAcessaoNaturalFormatado}" quantidadeDigitosInteiros="9" quantidadeCasasDecimais="2" onBlur="chamaCalculoGeral();"></abaco:campoMonetario><font color="red">*</font></td>
		</tr>
		<c:if test="${not empty fichaImovelRuralVo.situacaoAcessaoNatural.textoCorrente}">
				<script type="text/javascript">verificaJazidas();</script>
		</c:if>
		</table>
<!-- Trecho de código removido conforme solicitação 000033914/2015-85 -->      
<!--		<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
			
			<tr class="SEFAZ-TR-Titulo" title="Para exibir ou ocultar Dados Rebanho clique imagem ao lado">
				<td colspan="4">Dados Rebanho</td>
				<td width="7" bgcolor="White"><img id="rebanho" src="/imagens/expand.gif" alt="Exibir Rebanho" width="11" border="0" onClick="exibirOcultarInformacoesW3c('idRebanho',this);" style="cursor:pointer"/></td>
			</tr>
		</table>-->
		<!--<c:if test="${Exibir == rebanhoVo}">
		</c:if>
			<div id="idRebanho" style="display:none">
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
					<tr id="trCadRebanho">
						 <td class="SEFAZ-TD-RotuloEntrada" width="348">Tipo de Rebanho:&nbsp;&nbsp;</td>
						 <td colspan="4" class="SEFAZ-TD-ComboBox">
							 <select name="<%=FormITC.CAMPO_SELECT_TIPO_REBANHO%>" id ="<%=FormITC.CAMPO_SELECT_TIPO_REBANHO%>" onchange="adicionarDescricaoRebanho()">
								<option value=""><%=FormITC.SELECIONE%></option>
								<c:forEach var="rebanhoVo" items="${fichaImovelRuralVo.fichaImovelRuralRebanhoVo.rebanhoVo.collVO}">
								<c:if test="${fichaImovelRuralVo.fichaImovelRuralRebanhoVo.rebanhoVo.codigo == rebanhoVo.codigo}">
								<option value="<c:out value="${rebanhoVo.codigo}"></c:out>" selected="selected"><c:out value="${rebanhoVo.descricaoRebanho}"></c:out></option>
								</c:if>
								<c:if test="${fichaImovelRuralVo.fichaImovelRuralRebanhoVo.rebanhoVo.codigo != rebanhoVo.codigo}">
								<option value="<c:out value="${rebanhoVo.codigo}"></c:out>"><c:out value="${rebanhoVo.descricaoRebanho}"></c:out></option>
								</c:if>
								</c:forEach>
							</select>
						 </td>
					</tr>
					<tr>
						 <td class="SEFAZ-TD-RotuloEntrada" width="348">Descrição do Rebanho:&nbsp;</td>
						 <td colspan="4" class="SEFAZ-TD-CampoEntrada"><input name="<%=FormITC.CAMPO_DESCRICAO_REBANHO%>" type="text" id="<%=FormITC.CAMPO_DESCRICAO_REBANHO%>" size="35" value="<c:out value="${fichaImovelRuralVo.fichaImovelRuralRebanhoVo.descricaoRebanho}"></c:out>" maxlength="100" onblur="toUpperCaseW3c(this)"></input></td>
					</tr>
					<tr>
						 <td class="SEFAZ-TD-RotuloEntrada" width="348">Quantidade:&nbsp;</td>
						<td colspan="4" class="SEFAZ-TD-CampoEntrada"><input name="<%=FormITC.CAMPO_QUANTIDADE_REBANHO%>" type="text" id="<%=FormITC.CAMPO_QUANTIDADE_REBANHO%>" size="15" value="<c:out value="${fichaImovelRuralVo.fichaImovelRuralRebanhoVo.quantidadeRebanhoFormatado}"></c:out>" onBlur="calculaValorTotalRebanho()" onkeypress="return formataNumero(this,event);" maxlength="10"></input></td>
					</tr>
					<tr>
						 <td class="SEFAZ-TD-RotuloEntrada" width="348">Valor por Cabeça:&nbsp;</td>
						 <td colspan="4" class="SEFAZ-TD-CampoEntrada"><abaco:campoMonetario  name="<%=FormITC.CAMPO_VALOR_MERCADO_REBANHO%>" size="15" value="${fichaImovelRuralVo.fichaImovelRuralRebanhoVo.valorMercadoFormatado}" quantidadeDigitosInteiros="9" quantidadeCasasDecimais="2" onBlur="calculaValorTotalRebanho()"></abaco:campoMonetario></td>
					</tr>
					<tr>
						 <td class="SEFAZ-TD-RotuloEntrada" width="348">Valor Total:&nbsp;</td>
						 <td colspan="3" class="SEFAZ-TD-CampoEntrada" id="vlrTotalRebanho" width="100">R$ <c:out value="${fichaImovelRuralVo.fichaImovelRuralRebanhoVo.valorTotalFormatado}"/></td>
						 <td class="SEFAZ-TD-ComboBox"><input type="hidden" name="<%=FormITC.CAMPO_HIDDEN_DESCRICAO_REBANHO%>" value="<c:out value="${fichaImovelRuralVo.fichaImovelRuralRebanhoVo.rebanhoVo.descricaoRebanho}"></c:out>">
							<c:if test="${alterarRebanho == null}">
							<input type="button" name="<%=FormITC.BOTAO_ADICIONAR_REBANHO_IMOVEL_RURAL%>" value="<%=FormITC.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" onClick="adicionarRebanho();"></input>
							</c:if>
							<c:if test="${alterarRebanho != null}">
							<input type="button" name="<%=FormITC.BOTAO_ALTERAR_REBANHO_IMOVEL_RURAL%>" value="<%=FormITC.TEXTO_BOTAO_ALTERAR%>" class="SEFAZ-INPUT-Botao" onClick="alterarRebanho();"></input>
							</c:if>
						</td>
					</tr>
					<c:if test="${not empty fichaImovelRuralVo.fichaImovelRuralRebanhoVo.collVO}">
					<tr>
						 <td colspan="5" class="SEFAZ-TD-ComboBox">
							<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center">
								<tr class="SEFAZ-TR-Titulo">
									<td colspan="7">Rebanho</td>
								</tr>
								<tr class="SEFAZ-TR-SubTitulo">
									<td width="17%" align="left">Tipo</td>
									<td width="17%" align="left">Descri&ccedil;&atilde;o do Rebanho</td>
									<td width="10%" align="center">Quantidade</td>
									<td width="16%" align="right">Valor por Cabeça</td>
									<td width="16%" align="right">Valor Total</td>
									<td width="12%" align="center">&nbsp;</td>
									<td width="12%" align="center">&nbsp;</td>
								</tr>
								<c:forEach var="fichaImovelRuralRebanhoVo" items="${fichaImovelRuralVo.fichaImovelRuralRebanhoVo.collVO}" varStatus="contador">
								<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
								<c:if test="${contador.count % 2 != 0}">
								<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
								</c:if>
								<tr class="<c:out value="${linhaEstilo}"></c:out>">
									<td width="19%" align="left"><c:out value="${fichaImovelRuralRebanhoVo.rebanhoVo.descricaoRebanho}" ></c:out>
                           </td>
									<td width="19%" align="left"><c:out value="${fichaImovelRuralRebanhoVo.descricaoRebanho}" ></c:out></td>
									<td width="10%" align="center"><c:out value="${fichaImovelRuralRebanhoVo.quantidadeRebanho}" ></c:out></td>
									<td width="16%" align="right">R$ <c:out value="${fichaImovelRuralRebanhoVo.valorMercadoFormatado}"></c:out></td>
									<td width="16%" align="right">R$ <c:out value="${fichaImovelRuralRebanhoVo.valorTotalFormatado}"></c:out></td>
									<td width="10%" align="center"><a href="javascript:void(solicitarAlterarRebanho(<c:out value="${contador.index}"></c:out>));">Alterar</a></td>
									<td width="10%" align="center"><a href="javascript:void(excluirRebanho(<c:out value="${contador.index}"></c:out>));">Excluir</a></td>
								</tr>
								</c:forEach>
								<tr>
									<td colspan="7">&nbsp;</td>
								</tr>
							</table>
						 </td>
					</tr>
					</c:if>
				</table>
			</div>-->
     
		<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
		
		<tr class="SEFAZ-TR-Titulo" title="Para exibir ou ocultar Dados Construção clique imagem ao lado">
			<td colspan="5">Construções  existentes no imóvel</td>
			<td width="7" bgcolor="White"><img id="construcao" src="/imagens/expand.gif" alt="Exibir Construcao" width="11" border="0" onClick="exibirOcultarInformacoesW3c('idConstrucao',this);" style="cursor:pointer"/></td>
		</tr>
		</table>
		<c:if test="${Exibir == construcaoVo}">
		</c:if>
			<div id="idConstrucao" style="display:none">
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
				<tr>
					<td class="SEFAZ-TD-RotuloEntrada" width="348">Selecione a Construção:&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-ComboBox">
						<select name="<%=FormITC.CAMPO_SELECT_TIPO_CONSTRUCAO%>" id ="<%=FormITC.CAMPO_SELECT_TIPO_CONSTRUCAO%>" onchange="adicionarDescricaoConstrucao()">
							<option value=""><%=FormITC.SELECIONE%></option>
							<c:forEach var="construcaoVo" items="${fichaImovelRuralVo.fichaImovelRuralConstrucaoVo.construcaoVo.collVO}">
									<c:if test="${construcaoVo.codigo == fichaImovelRuralVo.fichaImovelRuralConstrucaoVo.construcaoVo.codigo}">
										<option value="<c:out value="${construcaoVo.codigo}"></c:out>" selected="selected"><c:out value="${construcaoVo.descricaoConstrucao}"></c:out></option>
									</c:if>
									<c:if test="${construcaoVo.codigo != fichaImovelRuralVo.fichaImovelRuralConstrucaoVo.construcaoVo.codigo}">
										<option value="<c:out value="${construcaoVo.codigo}"></c:out>"><c:out value="${construcaoVo.descricaoConstrucao}"></c:out></option>
									</c:if>
								</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloEntrada" width="348">Descrição da Construção:&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-CampoEntrada"><input name="<%=FormITC.CAMPO_DESCRICAO_CONSTRUCAO%>" type="text" id="<%=FormITC.CAMPO_DESCRICAO_CONSTRUCAO%>" size="35" value="<c:out value="${fichaImovelRuralVo.fichaImovelRuralConstrucaoVo.descricaoConstrucao}"></c:out>" maxlength="100" onblur="toUpperCaseW3c(this)"></input></td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloEntrada" width="348">Estado de Conservação:&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-ComboBox">
						<abaco:campoSelectDominio 
							ajuda="" 
							classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnEstadoConservacao" 
							name="<%=FormITC.CAMPO_SELECT_ESTADO_CONSERVACAO%>" 
							tabIndex="" 
							idCampo="<%=FormITC.CAMPO_SELECT_ESTADO_CONSERVACAO%>" 
							mostrarSelecione="true" 
							opcaoSelecionada="${fichaImovelRuralVo.fichaImovelRuralConstrucaoVo.situacaoEstadoConservacao.valorCorrente}">
						</abaco:campoSelectDominio>
					</td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor de Mercado:&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-CampoEntrada">
						<abaco:campoMonetario  name="<%=FormITC.CAMPO_VALOR_MERCADO_CONSTRUCAO%>" size="15" value="${fichaImovelRuralVo.fichaImovelRuralConstrucaoVo.valorMercadoFormatado}" quantidadeDigitosInteiros="9" quantidadeCasasDecimais="2"></abaco:campoMonetario><font color="red">*</font>
						<input type="hidden" name="<%=FormITC.CAMPO_HIDDEN_DESCRICAO_CONSTRUCAO%>" value="<c:out value="${fichaImovelRuralVo.fichaImovelRuralConstrucaoVo.construcaoVo.descricaoConstrucao}"></c:out>">
						<c:if test="${alterarConstrucao == null}">
							<input type="button" name="<%=FormITC.BOTAO_ADICIONAR_CONSTRUCOES_IMOVEL_RURAL%>" value="<%=FormITC.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" onClick="adicionarConstrucao();"></input>
						</c:if>
						<c:if test="${alterarConstrucao != null}">
							<input type="button" name="<%=FormITC.BOTAO_ALTERAR_CONSTRUCOES_IMOVEL_RURAL%>" value="<%=FormITC.TEXTO_BOTAO_ALTERAR%>" class="SEFAZ-INPUT-Botao" onClick="alterarConstrucao();"></input>
						</c:if>
					</td>
				</tr>
				<c:if test="${not empty fichaImovelRuralVo.fichaImovelRuralConstrucaoVo.collVO}">
				<tr>
					<td colspan="4" class="SEFAZ-TD-ComboBox">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center">
							<tr class="SEFAZ-TR-Titulo">
								<td colspan="7">Construções</td>
							</tr>
							<tr class="SEFAZ-TR-SubTitulo">
								<td width="25%" align="left">Construção</td>
								<td width="24%" align="left">Descrição da Construção</td>
								<td width="11%" align="center">Estado de Conservação</td>
								<td width="13%" align="right">Valor de Mercado</td>
								<td width="8%" align="center">&nbsp;</td>
								<td width="7%" align="center">&nbsp;</td>
							</tr>
							<c:forEach var="fichaImovelRuralConstrucaoVo" items="${fichaImovelRuralVo.fichaImovelRuralConstrucaoVo.collVO}" varStatus="contador">
							<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
							<c:if test="${contador.count % 2 != 0}">
							<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
							</c:if>
							<tr class="<c:out value="${linhaEstilo}"></c:out>">
								<td width="25%" align="left"><c:out value="${fichaImovelRuralConstrucaoVo.construcaoVo.descricaoConstrucao}" ></c:out></td>
								<td width="24%" align="left"><c:out value="${fichaImovelRuralConstrucaoVo.descricaoConstrucao}" ></c:out></td>
								<td width="11%" align="center"><c:out value="${fichaImovelRuralConstrucaoVo.situacaoEstadoConservacao.textoCorrente}" ></c:out></td>
								<td width="13%" align="right"><c:out value="${fichaImovelRuralConstrucaoVo.valorMercadoFormatado}" ></c:out></td>
								<td width="8%" align="center"><a href="javascript:void(solicitarAlterarConstrucao(<c:out value="${contador.index}"></c:out>));">Alterar</a></td>
								<td width="7%" align="center"><a href="javascript:void(excluirConstrucao(<c:out value="${contador.index}"></c:out>));">Excluir</a></td>
							</tr>
							</c:forEach>
							<tr>
								<td colspan="7">&nbsp;</td>
							</tr>
						</table>
				</td>
			</tr>
			</c:if>
			</table>
			</div>
		<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
		
		<tr class="SEFAZ-TR-Titulo" title="Para exibir ou ocultar Dados Benfeitoria clique imagem ao lado">
			<td colspan="4">Dados Benfeitoria</td>
			<td width="7" bgcolor="White"><img id="benfeitoria" src="/imagens/expand.gif" alt="Exibir Benfeitoria" width="11" border="0" onClick="exibirOcultarInformacoesW3c('idBenfeitoria',this);" style="cursor:pointer"/></td>
		</tr>
		</table>
		<c:if test="${Exibir == benfeitoriaVo}">
		</c:if>
			<div id="idBenfeitoria" style="display:none">
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
				<tr>
					<td class="SEFAZ-TD-RotuloEntrada" width="348">Benfeitoria:&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-ComboBox">
					<select name="<%=FormITC.CAMPO_SELECT_BENFEITORIA%>" id ="<%=FormITC.CAMPO_SELECT_BENFEITORIA%>" onchange="adicionarDescricaoBenfeitoria()">
						<option value=""><%=FormITC.SELECIONE%></option>
						<c:forEach var="benfeitoriaVo" items="${fichaImovelRuralVo.fichaImovelRuralBenfeitoriaVo.benfeitoriaVo.collVO}">
						<c:if test="${fichaImovelRuralVo.fichaImovelRuralBenfeitoriaVo.benfeitoriaVo.codigo == benfeitoriaVo.codigo}">
						<option value="<c:out value="${benfeitoriaVo.codigo}"></c:out>" selected="selected"><c:out value="${benfeitoriaVo.descricaoBenfeitoria}"></c:out></option>
						</c:if>
						<c:if test="${fichaImovelRuralVo.fichaImovelRuralBenfeitoriaVo.benfeitoriaVo.codigo != benfeitoriaVo.codigo}">
						<option value="<c:out value="${benfeitoriaVo.codigo}"></c:out>"><c:out value="${benfeitoriaVo.descricaoBenfeitoria}"></c:out></option>
						</c:if>			
						</c:forEach>
					</select>
					</td>
				</tr>	
				<tr>
						<td class="SEFAZ-TD-RotuloEntrada" width="348">Descrição da Benfeitoria:&nbsp;</td>
						<td class="SEFAZ-TD-CampoEntrada" colspan="3"><abaco:campoStringMaiuscula maxlength="50" name="<%=FormITC.CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA%>" size="50" value="${fichaImovelRuralVo.fichaImovelRuralBenfeitoriaVo.descricaoComplementarBenfeitoria}"/> 
						</td>
				</tr>
				<tr>
						<td colspan="4" align="center">
							<input type="hidden" name="<%=FormITC.CAMPO_HIDDEN_DESCRICAO_BENFEITORIA%>" value="<c:out value="${fichaImovelRuralVo.fichaImovelRuralBenfeitoriaVo.benfeitoriaVo.descricaoBenfeitoria}"></c:out>"></input>
							<input type="button" name="<%=FormITC.BOTAO_ADICIONAR_BENFEITORIA_IMOVEL_RURAL%>" value="<%=FormITC.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" onClick="adicionarBenfeitoria();"></input>						
						</td>
				</tr>
				<c:if test="${not empty fichaImovelRuralVo.fichaImovelRuralBenfeitoriaVo.collVO}">	  
				<tr>
					<td colspan="4">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center">
							<tr class="SEFAZ-TR-Titulo">
								<td colspan="3">Benfeitorias</td>
							</tr>
							<tr class="SEFAZ-TR-Titulo">
								<td colspan="1" align="left" width="35%">Benfeitoria</td>
								<td colspan="1" align="left" width="50%">Descrição Benfeitoria</td>
								<td colspan="1" width="15%">&nbsp;</td>
							</tr>	
							<c:forEach var="fichaImovelRuralBenfeitoriaVo" items="${fichaImovelRuralVo.fichaImovelRuralBenfeitoriaVo.collVO}" varStatus="contador">
							<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
							<c:if test="${contador.count % 2 != 0}">
							<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
							</c:if>
							<tr class="<c:out value="${linhaEstilo}"></c:out>">
								<td align="left" colspan="1"><c:out value="${fichaImovelRuralBenfeitoriaVo.benfeitoriaVo.descricaoBenfeitoria}" ></c:out></td>	
								<td align="left" colspan="1"><c:out value="${fichaImovelRuralBenfeitoriaVo.descricaoComplementarBenfeitoria}"/></td>
								<td align="center"><a href="javascript:void(excluirBenfeitoria(<c:out value="${contador.index}"></c:out>));">Excluir</a></td>
							</tr>
							</c:forEach>
							<tr>
								<td colspan="3">&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
				</c:if>
				</table>
			</div>
		<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
			
			<tr class="SEFAZ-TR-Titulo">
				<td colspan="5">Valores Gerais</td>
			</tr>
			<tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor da Terra Nua:&nbsp;</td>
				<td colspan="4" class="SEFAZ-TD-CampoEntrada">
				<abaco:campoMonetario  name="<%=FormITC.CAMPO_VALOR_IMOVEL%>"  size="15" value="${fichaImovelRuralVo.valorMercadoImovelFormatado}" quantidadeDigitosInteiros="9" quantidadeCasasDecimais="2" onBlur="chamaCalculoGeral();"></abaco:campoMonetario><font color="red">*</font>
				</td>
			</tr>
<!-- Trecho de código removido conforme solicitação 000033914/2015-85
			<tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor de Mercado das Máquinas e Implementos Agr&iacute;colas:&nbsp;</td>
				<td colspan="4" class="SEFAZ-TD-CampoEntrada">
				 <abaco:campoMonetario  name="<%=FormITC.CAMPO_VALOR_MAQUINAS_IMPLEMENTOS_AGRICOLAS%>" size="15" value="${fichaImovelRuralVo.valorMaquinaEquipamentoFormatado}" quantidadeDigitosInteiros="9" quantidadeCasasDecimais="2" onBlur="chamaCalculoGeral();"></abaco:campoMonetario>
				</td>
			</tr>
-->         
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada">Outros Valores do Imóvel Rural:&nbsp;</td>
				<td colspan="3" class="SEFAZ-TD-CampoEntrada">
				<abaco:campoMonetario  name="<%=FormITC.CAMPO_OUTROS_VALORES%>" size="15" value="${fichaImovelRuralVo.valorOutroFormatado}" quantidadeDigitosInteiros="9" quantidadeCasasDecimais="2" onBlur="chamaCalculoGeral();"></abaco:campoMonetario>
				</td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada">Valor Venal do ITR:&nbsp;</td>
				<td colspan="4" class="SEFAZ-TD-CampoEntrada">
				<abaco:campoMonetario  name="<%=FormITC.CAMPO_VALOR_VENAL%>" size="15" value="${fichaImovelRuralVo.valorITRFormatado}" quantidadeDigitosInteiros="9" quantidadeCasasDecimais="2" onBlur="chamaCalculoGeral();"></abaco:campoMonetario>
				</td>
			</tr>
			<tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor Total de Culturas:&nbsp;</td>
				<td colspan="4" class="SEFAZ-TD-CampoEntrada">R$ <c:out value="${fichaImovelRuralVo.fichaImovelRuralCulturaVo.valorTotalCulturasFormatado}"></c:out></td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor das Acessões:&nbsp;</td>
				<td colspan="4" class="SEFAZ-TD-CampoEntrada" id="valorAcessoes">R$ <script type="text/javascript" language="javascript">document.write(document.form.<%=FormITC.CAMPO_VALOR_ACESSOES_NATURAIS%>.value);</script></td>
			</tr>
			<tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor das Pastagens:&nbsp;</td>
				<td colspan="4" class="SEFAZ-TD-CampoEntrada" id="valorPastagens">R$ <script type="text/javascript" language="javascript">document.write(document.form.<%=FormITC.CAMPO_VALOR_PASTAGENS%>.value);</script></td>
			</tr>
				 <tr>
					<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor Total de Rebanho:&nbsp;</td>
					<td colspan="4" class="SEFAZ-TD-CampoEntrada">R$ <c:out value="${fichaImovelRuralVo.fichaImovelRuralRebanhoVo.valorTotalRebanhosFormatado}"></c:out></td>
				 </tr>
				 <tr> 
				<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor Total de Cons. Existentes no  Im&oacute;vel:&nbsp;</td>
				<td colspan="3" class="SEFAZ-TD-CampoEntrada">R$ <c:out value="${fichaImovelRuralVo.fichaImovelRuralConstrucaoVo.valorTotalBensFormatado}"></c:out></td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor Total de Mercado:&nbsp;</td>
            <c:choose>
               <c:when test="${fichaImovelRuralVo.valorITR gt fichaImovelRuralVo.valorTotalMercado}">
                  <td colspan="4" class="SEFAZ-TD-CampoEntrada" id="valorTotalmovel">R$ <c:out value="${fichaImovelRuralVo.valorITRFormatado}"></c:out></td>
               </c:when>
               <c:otherwise>
                  <td colspan="4" class="SEFAZ-TD-CampoEntrada" id="valorTotalmovel">R$ <c:out value="${fichaImovelRuralVo.valorTotalMercadoFormatado}"></c:out></td>
               </c:otherwise>
            </c:choose>
			</tr>
			<c:if test="${not empty estadoAbertura}">
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor Isento / Não Incidência:&nbsp;</td>
				<td colspan="4" class="SEFAZ-TD-CampoEntrada" id="valorIsento">R$ <c:out value="${fichaImovelRuralVo.valorTotalIsentosFormatado}"></c:out></td>
			</tr>
			</c:if>
			
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="348">Valor Tributável:&nbsp;</td>
				<td colspan="4" class="SEFAZ-TD-CampoEntrada" id="valorTribMostra">R$ <c:out value="${fichaImovelRuralVo.valorTributavelFormatado}"></c:out>
				<c:if test="${fichaImovelRuralVo.vlrTributavelCalculado}">
					<img src="/imagens/warning2.png" width="16" height="16"></img><font color="red">Valor Calculado pelo Sistema</font>
				</c:if>
				</td>
			</tr>         
         
         <c:if test="${fichaImovelRuralVo.vlrTributavelCalculado}">         
         <c:choose>
            <c:when test="${fichaImovelRuralVo.valorTributavelFormatado eq fichaImovelRuralVo.valorTotalMercadoFormatado}">
               <tr>
                  <td colspan="6">                     
                     <jsp:include page="/giaitcd/util/ViewIncludeValoresIguais.jsp"/>                         
                  </td>
               </tr> 
            </c:when>      
            <c:otherwise>
               <tr>
                  <td colspan="6">                     
                     <jsp:include page="/giaitcd/util/ViewIncludeConcordaPagamento.jsp"/>   
                  </td>
               </tr>            
            </c:otherwise>
            </c:choose>           
         </c:if>         
			<tr>
				<td colspan="5">&nbsp;</td>
			</tr>
			<tr> 
				<td colspan="5" align="center">
				<c:if test="${not fichaImovelRuralVo.bemTributavelVo.alterar}">
					<input type="button" value="<%=FormITC.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" name="<%=FormITC.BOTAO_INCLUIR_IMOVEL_RURAL%>" id="<%=FormITC.BOTAO_INCLUIR_IMOVEL_RURAL%>" onClick="validaFormulario()"></input>
				</c:if>
				<c:if test="${fichaImovelRuralVo.bemTributavelVo.alterar}">
					<input type="button" value="<%=FormITC.TEXTO_BOTAO_ALTERAR%>" class="SEFAZ-INPUT-Botao" name="<%=FormITC.BOTAO_INCLUIR_IMOVEL_RURAL%>" id="<%=FormITC.BOTAO_INCLUIR_IMOVEL_RURAL%>" onClick="validaFormulario()"></input>
				</c:if>
				<input type="button" value="<%=FormITC.TEXTO_BOTAO_VOLTAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_VOLTAR_IMOVEL_RURAL%>" id="<%=Form.BOTAO_VOLTAR_IMOVEL_RURAL%>" onClick="voltarBemTributavel()"></input>
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
		<c:if test="${Exibir == 'culturaVo'}">
			<script type="text/javascript">exibirOcultarInformacoesW3c('idCultura','cultura');</script>
		</c:if>
		<!-- Trecho de código removido conforme solicitação 000033914/2015-85
      <c:if test="${Exibir == 'rebanhoVo'}">
			<script type="text/javascript">exibirOcultarInformacoesW3c('idRebanho','rebanho');</script>
		</c:if>-->
		<c:if test="${Exibir == 'construcaoVo'}">
			<script type="text/javascript">exibirOcultarInformacoesW3c('idConstrucao','construcao');</script>
		</c:if>
		<c:if test="${Exibir == 'benfeitoriaVo'}">
			<script type="text/javascript">exibirOcultarInformacoesW3c('idBenfeitoria','benfeitoria');</script>
		</c:if>
	</form>
	</CENTER>
	<g:mostrarRodape></g:mostrarRodape>
	</body>
</html>
