<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
	/*
	* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
	* Arquivo : ViewPesquisarGIAITCDDetalharFichaImovelUrbano.jsp
	* Criação : Dezembro de 2007 / Rogério Sanches de Oliveira
	* Revisão : Fevereiro de 2008 / João Batista Padilha e Silva
	* Log : 
	*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" %>
<%@ page import="br.com.abaco.util.http.JspUtil" %>
<%@ page errorPage="/mensagem/ViewErro.jsp" %>
<%@page import=" br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form"%>
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
   	</script>
	<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
	</head>
	<body class="SEFAZ-Body" onload="verificaErro();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
		<center>		
<form name="form"   method="POST" action="">
  
  <br/>
        <table cellspacing="0" cellpadding="0" border="0" width="740"
               align="center">
          <tr>
            <td colspan="4">
              <div align="center">
                <font face="Verdana, Arial, Helvetica, sans-serif" size="3">
                  <b>Ficha Im&oacute;vel Urbano</b>
                </font>
              </div>
            </td>
          </tr>
          <tr>
            <td colspan="4" bgcolor="#336699" class="SEFAZ-TR-Titulo">
              <div align="center" class="style3">
                Dados referentes ao Im&oacute;vel Urbano
              </div>
            </td>
          </tr>
          <tr>
            <td width="25%" class="SEFAZ-TD-RotuloSaida">Tipo de logradouro:&nbsp; </td>
            <td width="21%" class="SEFAZ-TD-CampoSaida">
            <!--
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.enderecoVo.tipoLogr}"/>
             -->
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.enderecoVo.domnTipoLogradouro.textoCorrente}"/>
            </td>
            <td width="27%" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
            <td width="27%" class="SEFAZ-TD-CampoSaida">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.enderecoVo.logradouro}"/>
            </td>
          </tr>
          <tr>
            <td class="SEFAZ-TD-RotuloSaida">N&uacute;mero:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.enderecoVo.numrLogradouro}"/>
            </td>
            <td class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.enderecoVo.bairro}"/>
            </td>
          </tr>
          <tr>
            <td class="SEFAZ-TD-RotuloSaida">Complemento:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.enderecoVo.complemento}"/>
            </td>
            <td class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.enderecoVo.cep.codgCep}"/>
            </td>
          </tr>
          <tr>
            <td class="SEFAZ-TD-RotuloSaida">Ponto de refer&ecirc;ncia:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida" colspan="3">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.enderecoVo.pontoReferencia}"/>
            </td>
          </tr>
          <tr>
            <td class="SEFAZ-TD-RotuloSaida">Localidade:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.enderecoVo.cep.localidade.nomeLocalidade}"/>
            </td>
            <td class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.enderecoVo.cep.localidade.uf.siglUf}"/>
            </td>
          </tr>
          <tr>
            <td class="SEFAZ-TD-RotuloSaida">Tipo de Imóvel:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.bemVo.descricaoTipoBem}"/>
            </td>
				
            <td class="SEFAZ-TD-RotuloSaida">Estado de
                                             conserva&ccedil;&atilde;o:&nbsp; </td>
            <td class="SEFAZ-TD-CampoSaida">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.estadoConservacao.textoCorrente}"/>
            </td>
          </tr>
          <tr>
            <td class="SEFAZ-TD-RotuloSaida">&Aacute;rea Total do Im&oacute;vel:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.quantidadeAreaTotalFormatado}"/>
            </td>
				
            <td class="SEFAZ-TD-RotuloSaida">&Aacute;rea constru&iacute;da:&nbsp; </td>
            <td class="SEFAZ-TD-CampoSaida">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.quantidadeAreaConstruidaFormatado}"/>
            </td>

          </tr>
          <tr>
            <td class="SEFAZ-TD-RotuloSaida">Tipo Constru&ccedil;&atilde;o:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.construcaoVo.descricaoConstrucao}"/>
            </td>
				
            <td class="SEFAZ-TD-RotuloSaida" >Tipo de acesso:&nbsp; </td>
            <td class="SEFAZ-TD-CampoSaida" colspan="3">
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.tipoAcesso.textoCorrente}"/>
            </td>
          </tr>
          <tr>
            <td class="SEFAZ-TD-RotuloSaida">Valor de mercado:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida">
              R$ 
              <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.valorTotalFormatado}"/>
            </td>
           <td class="SEFAZ-TD-RotuloSaida">Valor venal (IPTU):&nbsp; </td>
           <td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.valorVenalIptuFormatado}" /></td>				
          </tr>
          <tr>
            <td class="SEFAZ-TD-RotuloSaida">Valor tribut&aacute;vel:&nbsp; </td>
              <td class="SEFAZ-TD-CampoSaida" >
                R$ 
                <c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.valorMercadoTotalFormatado}"/>
              </td>
               <td class="SEFAZ-TD-RotuloSaida">Percentual Transmitido do Imóvel:&nbsp; </td>
               <td class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.valorPercentualTransmitidoFormatado}" />%</td>				

         </tr>            
         
         <c:if test="${fichaImovelUrbanoVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || fichaImovelUrbanoVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4'}">
         <tr>
            <td class="SEFAZ-TD-RotuloSaida" colspan="3">Concorda com pagamento do Imposto sobre o Valor Arbitrado(Sim/Não)?:&nbsp; </td>
            <td class="SEFAZ-TD-CampoSaida"><c:out value="${fichaImovelUrbanoVo.bemTributavelVo.concordaComValorArbitrado.textoCorrente}" /></td>				
         </tr>  
         
         </c:if>
        </table>
		  
		  			<c:if test="${not empty fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.fichaImovelUrbanoBenfeitoriaVo.collVO}">
		<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
			 <tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="2">Benfeitoria</td>
			 </tr>
				<tr class="SEFAZ-TR-SubTitulo">
					<td colspan="1" align="left" width="50%">Benfeitoria</td>
					<td colspan="1" align="left" width="50%">Descrição da Benfeitoria</td>
				</tr>	
			<c:forEach var="fichaImovelUrbanoBenfeitoriaVo" items="${fichaImovelUrbanoVo.bemTributavelVo.fichaImovelVo.fichaImovelUrbanoBenfeitoriaVo.collVO}" varStatus="contador">
			<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
			<c:if test="${contador.count % 2 != 0}">
				<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
			</c:if>
			<tr class="<c:out value="${linhaEstilo}"></c:out>">
					<td align="left"><c:out value="${fichaImovelUrbanoBenfeitoriaVo.benfeitoriaVo.descricaoBenfeitoria}" ></c:out></td>	
					<td align="left"><c:out value="${fichaImovelUrbanoBenfeitoriaVo.descricaoComplementarBenfeitoria}"/></td>
			</tr>
			</c:forEach>
			<tr>
				<td>&nbsp;</td>
			</tr>
	  </table>
	</c:if>  
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr> 
			<td colspan="4" align="center"> 							
				<%=JspUtil.getBotaoVoltar(request, "Retornar","Retornar")%>        
			</td>
		</tr>			
  </table>
  
      </form>
		</center>
		<g:mostrarRodape/>
	</body>
</html>	
