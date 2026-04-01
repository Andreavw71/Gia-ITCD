<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
	/**
	* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
	* Arquivo : ViewPesquisarGIAITCDDetalharFichaImovelRural.jsp
	* Criação 		Dezembro de 2007 / Rogério Sanches de Oliveira
	* Revisão :  Fevereiro de 2008 / João Batista Padilha e Silva
	* Log : 
	*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@page import ="br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnEstadoConservacao"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoLogradouro"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%@page import ="br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo"%>
<%@page import ="br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT"> 
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
		<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
		<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
		<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
		<script type="text/javascript" language="javascript">
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
		function escreveVlrTributavel(valorImovel,ValorVenal)
		{
			if(valorImovel > ValorVenal)
				document.getElementById("valorTribMostra").innerHTML = "R$ " + floatTomoeda(valorImovel);
			else
				document.getElementById("valorTribMostra").innerHTML = "R$ " + floatTomoeda(ValorVenal);
		}
		function preencheValorTotalImovel(estadoAbertura,tipoFicha,totalRebanho,totalConstrucao,totalCultura)
		{
			var valorImovel = replaceAll('<c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorMercadoImovelFormatado}"></c:out>',".","");
			valorImovel = (valorImovel == "") ? 0 : parseFloat(replaceAll(valorImovel,",","."));
			
			var valorMaquinas = replaceAll('<c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorMaquinaEquipamentoFormatado}"></c:out>',".","");
			valorMaquinas =  (valorMaquinas == "") ? 0 : parseFloat(replaceAll(valorMaquinas,",","."));
			
			var valorOutros = replaceAll('<c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorOutroFormatado}"></c:out>',".","");
			valorOutros =  (valorOutros == "") ? 0 : parseFloat(replaceAll(valorOutros,",","."));
			
			var valorAcessaoNatural = replaceAll('<c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorAcessaoNaturalFormatado}"></c:out>',".","");
			valorAcessaoNatural =  (valorAcessaoNatural == "") ? 0 : parseFloat(replaceAll(valorAcessaoNatural,",","."));
			
			var valorPastagens = replaceAll('<c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorPastagemFormatado}"></c:out>',".","");
			valorPastagens =  (valorPastagens == "") ? 0 : parseFloat(replaceAll(valorPastagens,",","."));
			
			total = 0;
			
			if(!(tipoFicha == <%=DomnTipoGIA.CAUSA_MORTIS%> && estadoAbertura != "MT"))
			{
				totalRebanho =  (totalRebanho == "") ? 0 : parseFloat(replaceAll(totalRebanho,",","."));
				total = parseFloat(totalRebanho + valorMaquinas + valorOutros);
			}
				totalConstrucao =  (totalConstrucao == "") ? 0 : parseFloat(replaceAll(totalConstrucao,",","."));
				totalCultura =  (totalCultura == "") ? 0 : parseFloat(replaceAll(totalCultura,",","."));
				total = parseFloat(total + totalConstrucao + totalCultura + valorAcessaoNatural + valorPastagens + valorImovel);
				
				return floatTomoeda(total);
		}
		</script>
	<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
	</head>
	<body class="SEFAZ-Body" onload="verificaErro();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
		<center>		
<form method="POST" name="form" action="">
  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr>
			<td colspan="4" bgcolor="#336699" class="SEFAZ-TR-Titulo"><div align="center" class="style3">Dados referentes ao Im&oacute;vel Rural </div></td>
		 </tr>
		<tr>
		  <td width="35%" class="SEFAZ-TD-RotuloSaida">Denomina&ccedil;&atilde;o:&nbsp;</td>
		  <td class="SEFAZ-TD-CampoSaida" colspan="3"><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.descricaoDenominacao}" /></td>
		</tr>
		<tr>
		  <td class="SEFAZ-TD-RotuloSaida" >Endereço do Imóvel:&nbsp;</td>
		  <td class="SEFAZ-TD-CampoSaida" colspan="3"><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.enderecoVo.logradouro}" /></td>		
		</tr>
		<tr>
		  <td class="SEFAZ-TD-RotuloSaida" width="35%">Ponto de referencia:&nbsp;</td>
		  <td class="SEFAZ-TD-CampoSaida" width="15%"><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.enderecoVo.pontoReferencia}"/></td>		
		  <td class="SEFAZ-TD-RotuloSaida" width="26%">Localidade:&nbsp;</td>
		  <td class="SEFAZ-TD-CampoSaida" width="24%"><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.enderecoVo.cep.localidade.nomeLocalidade}" /></td>
		</tr>
		<tr>
		  <td class="SEFAZ-TD-RotuloSaida" >UF:&nbsp;</td>
		  <td class="SEFAZ-TD-CampoSaida" ><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.enderecoVo.cep.localidade.uf.siglUf}" /></td>
		  <td class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
		  <td class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.enderecoVo.cep.codgCep}" /></td>
		</tr>
		<tr>
		  <td class="SEFAZ-TD-RotuloSaida" >Distância em KM do perímetro urbano:&nbsp;</td>
		  <td class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.quantidadeDistanciaFormatado}"/> km</td>
		  <td class="SEFAZ-TD-RotuloSaida" >Área total:&nbsp;</td>
		  <td class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.areaTotalFormatado}"/> ha </td>
		</tr>
      <tr>
		  <td class="SEFAZ-TD-RotuloSaida" >Distância em KM até a rodovia pavimentada:&nbsp;</td>
		  <td class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.distanciaAsfaltoFormatado}"/> km</td>
		  <td class="SEFAZ-TD-RotuloSaida" >Código na Receita Federal:&nbsp;</td>
		  <td class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.codigoReceitaFederalFormatado}" /></td>
		</tr>
		<tr>
		  <td class="SEFAZ-TD-RotuloSaida" >Número do INDEA/MT:&nbsp;</td>
		  <td class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.numericoIndeaFormatado}"/></td>
		  <td class="SEFAZ-TD-RotuloSaida" ></td>
		  <td class="SEFAZ-TD-CampoSaida"></td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
	</table>
	<c:if test="${not empty fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.fichaImovelRuralCulturaVo.collVO}">
		<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
			 <tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="5">Cultura</td>
			 </tr>
			 <tr class="SEFAZ-TR-SubTituloEsq"> 
					<td width="20%" align="left">Cultura</td>
					<td width="25" align="left">Descrição da Cultura</td>
					<td width="17%" align="center">Área Cultivada</td>
					<td width="17%" align="right">Valor do Hectare</td>
					<td width="21%" align="right">Valor de Mercado</td>
			 </tr>
			 <c:forEach var="fichaImovelRuralCulturaVo" items="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.fichaImovelRuralCulturaVo.collVO}" varStatus="contador">
				<c:set var="totalCultura" value="${fichaImovelRuralCulturaVo.valorMercado + totalCultura}"></c:set>
				<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
				<c:if test="${contador.count % 2 != 0}">
					<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
				</c:if>
				 <tr class="<c:out value="${linhaEstilo}"></c:out>">
						<td align="left"><c:out value="${fichaImovelRuralCulturaVo.culturaVo.descricaoCultura}"></c:out></td>
						<td align="left"><c:out value="${fichaImovelRuralCulturaVo.descricaoComplementarCultura}"/></td>
						<td align="center"><c:out value="${fichaImovelRuralCulturaVo.areaCultivadaFormatado}" />ha</td>
						<td align="right">R$ <c:out value="${fichaImovelRuralCulturaVo.valorHectareFormatado}"/></td>
						<td align="right">R$ <c:out value="${fichaImovelRuralCulturaVo.valorMercadoFormatado}"></c:out></td>
				 </tr>
			 </c:forEach>
			 <tr>
				<td colspan="3">&nbsp;</td>
			 </tr>
		  </table>
	  </c:if>
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr>
		  <td colspan="4" class="SEFAZ-TR-Titulo" align="center">Pastagens/Acessões</td>
	  </tr>
		<tr>
		  <td colspan="2" width="50%" class="SEFAZ-TD-RotuloSaida">Possui pastagens&nbsp; naturais e/ou cultivadas (artificiais):&nbsp; </td>
		  <td colspan="2" width="50%" class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.situacaoPastagem.textoCorrente}" /></td>
		</tr>
		<c:set var="SIM"><%=DomnSimNao.SIM%></c:set>
		<c:if test = "${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.situacaoPastagem.valorCorrente == SIM}">
			<tr>
			  <td colspan="2" width="50%" class="SEFAZ-TD-RotuloSaida">Tamanho das Pastagens:&nbsp;</td>
			  <td colspan="2" width="50%" class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.areaPastagemFormatado}"></c:out></td>
			</tr>
			<tr>
			  <td colspan="2"  width="50%" class="SEFAZ-TD-RotuloSaida">Valor das pastagens:&nbsp; </td>
			  <td colspan="2"  width="50%" class="SEFAZ-TD-CampoSaida">R$ <c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorPastagemFormatado}" /></td>
			</tr>

		</c:if>
		<tr>
			<td class="SEFAZ-TD-RotuloSaida" colspan="2">Possui jazidas minerais, fontes de água radioativas, térmicas e/ou minerais:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" colspan="2"><c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.situacaoAcessaoNatural.textoCorrente}" /></td>
		</tr>
		<c:if test = "${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.situacaoAcessaoNatural.valorCorrente == SIM}">
			<tr>
				<td colspan="2" width="50%" class="SEFAZ-TD-RotuloSaida">Valor das Acessões Naturais:&nbsp;</td>
				<td colspan="2" width="50%" class="SEFAZ-TD-CampoSaida">R$ <c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorAcessaoNaturalFormatado}" /></td>
			</tr>
		</c:if>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	<c:if test="${not empty fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.fichaImovelRuralRebanhoVo.collVO}">
		<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
			 <tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="5">Rebanho</td>
			 </tr>
			 <tr class="SEFAZ-TR-SubTituloEsq"> 
						<td width="23%" align="left">Tipo</td>
						<td width="23%" align="left">Descri&ccedil;&atilde;o do Rebanho</td>
						<td width="10%" align="center">Quantidade</td>
						<td width="22%" align="right">Valor por Cabeça</td>
						<td width="22%" align="right">Valor Total</td>
			</tr>
			<c:forEach var="fichaImovelRuralRebanhoVo" items="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.fichaImovelRuralRebanhoVo.collVO}" varStatus="contator">		
			<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
			<c:if test="${contador.count % 2 != 0}">
			<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
			</c:if>
			 <tr class="<c:out value="${linhaEstilo}"></c:out>">
					<td align="left"><c:out value="${fichaImovelRuralRebanhoVo.rebanhoVo.descricaoRebanho}" ></c:out></td>
					<td align="left"><c:out value="${fichaImovelRuralRebanhoVo.descricaoRebanho}" ></c:out></td>
					<td align="center"><c:out value="${fichaImovelRuralRebanhoVo.quantidadeRebanho}" ></c:out></td>
					<td align="right">R$ <c:out value="${fichaImovelRuralRebanhoVo.valorMercadoFormatado}"></c:out></td>
					<td align="right">R$ <c:out value="${fichaImovelRuralRebanhoVo.valorTotalFormatado}"></c:out></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5">&nbsp;</td>
			</tr>
	  </table>	
  </c:if>
  <c:if test="${not empty fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.fichaImovelRuralConstrucaoVo.collVO}">
		<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
			 <tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="4">Construção</td>
			 </tr>
			 <tr class="SEFAZ-TR-SubTitulo">
						<td width="24%" align="left">Construção</td>
						<td width="29%" align="left">Descrição da Construção</td>
						<td width="18%" align="center">Estado de Conservação</td>
						<td width="17%" align="right">Valor de Mercado</td>
			</tr>
			<c:forEach var="fichaImovelRuralConstrucaoVo" items="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.fichaImovelRuralConstrucaoVo.collVO}" varStatus="contador">
			<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
			<c:if test="${contador.count % 2 != 0}">
			<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
			</c:if>
			<c:set var="totalConstrucao" value="${totalConstrucao + fichaImovelRuralConstrucaoVo.valorMercado}"></c:set>
			<tr class="<c:out value="${linhaEstilo}"></c:out>">
						<td align="left"><c:out value="${fichaImovelRuralConstrucaoVo.construcaoVo.descricaoConstrucao}" ></c:out></td>
						<td align="left"><c:out value="${fichaImovelRuralConstrucaoVo.descricaoConstrucao}" ></c:out></td>
						<td align="center"><c:out value="${fichaImovelRuralConstrucaoVo.situacaoEstadoConservacao.textoCorrente}" ></c:out></td>
						<td align="right">R$ <c:out value="${fichaImovelRuralConstrucaoVo.valorMercadoFormatado}" ></c:out></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
	  </table> 
	</c:if>  
	<c:if test="${not empty fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.fichaImovelRuralBenfeitoriaVo.collVO}">
		<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
			 <tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="2">Benfeitoria</td>
			 </tr>
				<tr class="SEFAZ-TR-SubTitulo">
					<td colspan="1" align="left" width="50%">Benfeitoria</td>
					<td colspan="1" align="left" width="50%">Descrição da Benfeitoria</td>
				</tr>	
			<c:forEach var="fichaImovelRuralBenfeitoriaVo" items="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.fichaImovelRuralBenfeitoriaVo.collVO}" varStatus="contador">
			<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
			<c:if test="${contador.count % 2 != 0}">
				<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
			</c:if>
			<tr class="<c:out value="${linhaEstilo}"></c:out>">
					<td align="left"><c:out value="${fichaImovelRuralBenfeitoriaVo.benfeitoriaVo.descricaoBenfeitoria}" ></c:out></td>	
					<td align="left"><c:out value="${fichaImovelRuralBenfeitoriaVo.descricaoComplementarBenfeitoria}"/></td>
			</tr>
			</c:forEach>
			<tr>
				<td>&nbsp;</td>
			</tr>
	  </table>
	</c:if>  
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
	  <tr class="SEFAZ-TR-Titulo" >
	    <td colspan="4">Valores Gerais </td>
		</tr>
	  <tr>
	    <td  width="55%" class="SEFAZ-TD-RotuloSaida" colspan="2">Valor da Terra Nua:&nbsp;</td>
	    <td  width="45%"  colspan="2" class="SEFAZ-TD-CampoSaida">R$ <c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorMercadoImovelFormatado}"></c:out></td>
	   </tr>
	  <tr> 
			<td class="SEFAZ-TD-RotuloSaida" colspan="2">Valor de Mercado das Máquinas e Implementos Agr&iacute;colas:&nbsp;</td>
			<td colspan="2" class="SEFAZ-TD-CampoSaida">R$ <c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorMaquinaEquipamentoFormatado}"></c:out></td>
		</tr>
	  <tr> 
			<td class="SEFAZ-TD-RotuloSaida" colspan="2">Valor Total de Culturas:&nbsp;</td>
			<td colspan="2" class="SEFAZ-TD-CampoSaida">R$ 
			<c:if test="${not empty totalCultura}">
					<script type="text/javascript" language="javascript">escreveVlrTot(<c:out value="${totalCultura}"></c:out>);</script>
			</c:if>
			</td>
		</tr>
		<tr> 
			<td class="SEFAZ-TD-RotuloSaida" colspan="2">Valor das Acessões:&nbsp;</td>
			<td colspan="2" class="SEFAZ-TD-CampoSaida">R$ <c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorAcessaoNaturalFormatado}"></c:out></td>
		</tr>
		<tr> 
			<td class="SEFAZ-TD-RotuloSaida" colspan="2">Valor das Pastagens:&nbsp;</td>
			<td colspan="2" class="SEFAZ-TD-CampoSaida">R$ <c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorPastagemFormatado}"></c:out></td>
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloSaida" colspan="2">Valor Total de Rebanho:&nbsp;</td>
			<td colspan="2" class="SEFAZ-TD-CampoSaida">R$ <c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.fichaImovelRuralRebanhoVo.valorTotalRebanhosFormatado}"/>
			</td>
		 </tr>
	  <tr>
			<td class="SEFAZ-TD-RotuloSaida" colspan="2">Outros Valores:&nbsp;</td>
			<td colspan="2" class="SEFAZ-TD-CampoSaida">R$ <c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorOutroFormatado}"></c:out></td>
		</tr>
		<tr> 
			<td class="SEFAZ-TD-RotuloSaida" colspan="2">Valor Total de Construções Existentes no  Im&oacute;vel:&nbsp;</td>
			<td colspan="2" class="SEFAZ-TD-CampoSaida">R$ 
			<c:if test="${not empty totalConstrucao}">
			<script type="text/javascript" language="javascript">escreveVlrTot(<c:out value="${totalConstrucao}"></c:out>);</script>
			</c:if>
			</td>
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloSaida" colspan="2">Valor de Mercado:&nbsp;</td>
			<%
			FichaImovelRuralVo ficha =	(FichaImovelRuralVo) request.getAttribute("fichaImovelRuralVo");
			String uf  = "";
			if(ficha.getBemTributavelVo().getGiaITCDVo().getNaturezaOperacaoVo().getTipoProcesso().is(DomnTipoProcesso.INVENTARIO_ARROLAMENTO))
			{
				uf = ((GIAITCDInventarioArrolamentoVo) ficha.getBemTributavelVo().getGiaITCDVo()).getUfAbertura().getSiglUf();
			}
			%>
			<c:set var="estadoAbertura" value="${uf}"></c:set>
			<c:set var="tipoFicha" value="${bemTributavelVo.giaITCDVo.naturezaOperacaoVo.tipoGIA.valorCorrente}"></c:set>
			<td colspan="2" class="SEFAZ-TD-CampoSaida" id="valorTotalmovel">R$ <script type="text/javascript" language="javascript">document.write(preencheValorTotalImovel('<c:out value="${estadoAbertura}"></c:out>','<c:out value="${tipoFicha}"></c:out>','<c:out value="${totalRebanho}"></c:out>','<c:out value="${totalConstrucao}"></c:out>','<c:out value="${totalCultura}"></c:out>'));</script></td>
		</tr>
		<c:if test="${not empty uf}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" colspan="2">Valor Isento / Não Incidência:&nbsp;</td>
				<td colspan="2" class="SEFAZ-TD-CampoSaida">R$ <script type="text/javascript" language="javascript">valorTotalIsento('<c:out value="${totalRebanho}"></c:out>');</script></td>
			</tr>
		</c:if>			
		<tr>
			<td class="SEFAZ-TD-RotuloSaida" colspan="2">Valor Venal do ITR:&nbsp;</td>
			<td colspan="23" class="SEFAZ-TD-CampoSaida">R$ <c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorITRFormatado}"></c:out></td>
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloSaida" colspan="2">Valor Tributável:&nbsp;</td>
			<td colspan="2" class="SEFAZ-TD-CampoSaida" id="valorTribMostra">R$ <c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorTributavelFormatado}"></c:out> </td>
		</tr>   
      
      <c:if test="${fichaImovelRuralVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || fichaImovelRuralVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4'}">
      <tr>
			<td class="SEFAZ-TD-RotuloSaida" colspan="2">Concorda com pagamento do Imposto sobre o Valor Arbitrado(Sim/Não)?:&nbsp;&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" colspan="2"><c:out value="${fichaImovelRuralVo.bemTributavelVo.concordaComValorArbitrado.textoCorrente}"></c:out> </td>
		</tr>   
      </c:if>
      
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr> 
			<td colspan="4" align="center"> 							
				<%=JspUtil.getBotaoVoltar(request, "Retornar","Retornar")%>
			</td>
		</tr>	  
  </table>
  <br/>
  <script type="text/javascript">
		function chamaCalculaValorTotalMercado()
		{
			document.getElementById("valorTotalmovel").innerHTML = preencheValorTotalImovel('<c:out value="${estadoAbertura}"></c:out>','<c:out value="${tipoFicha}"></c:out>','<c:out value="${totalRebanho}"></c:out>','<c:out value="${totalConstrucao}"></c:out>','<c:out value="${totalCultura}"></c:out>');
		}
		function escreveVlrTributavel()
		{
			valorTotalImovel = preencheValorTotalImovel('<c:out value="${estadoAbertura}"></c:out>','<c:out value="${tipoFicha}"></c:out>','<c:out value="${totalRebanho}"></c:out>','<c:out value="${totalConstrucao}"></c:out>','<c:out value="${totalCultura}"></c:out>');
			valorVenal = <c:out value="${fichaImovelRuralVo.bemTributavelVo.fichaImovelVo.valorITRFormatado}"></c:out>;
			
			if((trimW3c(valorTotalImovel) != "") || (trimW3c(valorVenal) != ""))
			{
								
				valorVenal = replaceAll(valorVenal,".","");
				
				if(trimW3c(valorVenal) != "")
				{
					valorVenal = parseFloat(valorVenal.replace(",","."));
				}
				valorTotalImovel = replaceAll(valorTotalImovel,".","");
				valorTotalImovel = parseFloat(valorTotalImovel.replace(",","."));
				
				if(valorTotalImovel > valorVenal)
				{
					document.getElementById("valorTribMostra").innerHTML = floatTomoeda(valorTotalImovel);
				}
				else
				{
					document.getElementById("valorTribMostra").innerHTML = floatTomoeda(valorVenal);
				}
			}
			else
			{
				document.getElementById("valorTribMostra").innerHTML = "";
			}
		}
		</script>
</form>
		</center>
		<g:mostrarRodape/>
	</body>
</html>	
