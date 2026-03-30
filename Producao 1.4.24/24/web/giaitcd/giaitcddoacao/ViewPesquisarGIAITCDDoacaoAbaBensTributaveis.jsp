<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewConsultarGIAITCDDoacaoAbaBensTributaveis.jsp
* Criação : Novembro de 2007 / Rogério Sanches de Oliveira
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%
	pageContext.setAttribute("SIM",DomnSimNao.SIM);
%>

<form method="POST" name="form" action="#">
		<c:set scope="page" var="IMOVEL_RURAL"><%=DomnTipoBem.IMOVEL_RURAL%></c:set>
		<c:set scope="page" var="IMOVEL_URBANO"><%=DomnTipoBem.IMOVEL_URBANO%></c:set>
		<c:set scope="page" var="OUTROS_BENS"><%=DomnTipoBem.OUTROS_BENS%></c:set>
		<c:set scope="page" var="exibeIsencaoPrevista" value="${giaITCDVo.naturezaOperacaoVo.flagIsencaoPrevistaLei.valorCorrente == SIM}"/> 
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
	<c:if  test="${not empty giaITCDVo.bemTributavelVo.collVO}">
    <tr class="SEFAZ-TR-SubTituloEsq"> 
    	<td align="center">Classificação</td>
    	<td align="center">Tipo</td>
    	<td align="center">Descrição</td>
		<c:if test="${exibeIsencaoPrevista}">
			<td align="center">Isenção prevista em lei</td>
		</c:if>
		<c:if test="${giaITCDVo.giaRetificada}">
			<td align="center">Valor Avaliado</td>
		</c:if>	
		<td align="center">Valor Declarado</td>
    	<td align="center">Valor Arbitrado</td>
		<c:if test="${giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4'}">
		<td align="center">Concorda Valor Arbitrado?</td>
      </c:if>
    	<td align="center">&nbsp;</td>
    </tr>
	 <c:forEach var="bemTributavelVo" items="${giaITCDVo.bemTributavelVo.collVO}" varStatus="status">
		<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
			 <c:if test="${status.count % 2 != 0}">
	  <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
			 </c:if>
          <tr class="<c:out value="${linhaEstilo}"/>">
			<td align="left" valign="top"><c:out value="${bemTributavelVo.bemVo.classificacaoTipoBem.textoCorrente}"/></td>						
			<td align="left" valign="top"><c:out value="${bemTributavelVo.bemVo.descricaoTipoBem}"></c:out></td>
			<td align="left" valign="top"><c:out value="${bemTributavelVo.descricaoBemTributavel}"/></td>
			<c:if test="${exibeIsencaoPrevista}">
				<td align="center" valign="top"><c:out value="${bemTributavelVo.isencaoPrevistaFormatada.textoCorrente}"/></td>
			</c:if>			
			<c:if test="${giaITCDVo.giaRetificada}">
				<td align="center">R$ <c:out value="${bemTributavelVo.valorMercadoFormatado}"/></td>
			</c:if>
         <c:choose>
            <c:when test="${giaITCDVo.numeroVersaoGIAITCD.textoCorrente != 'GIA Versão 1.3' && giaITCDVo.numeroVersaoGIAITCD.textoCorrente != 'GIA Versão 1.4'}">
               <td align="center" valign="top" colspan="1">R$ <c:out value="${bemTributavelVo.valorInformadoFormatado}"/></td>
               <td align="center" valign="top" colspan="1">R$ <c:out value="${bemTributavelVo.valorMercadoFormatado}"></c:out></td>            
            </c:when>
         <c:otherwise>
            <c:choose>
               <c:when test="${giaITCDVo.giaAvaliada}">
               
                     <c:choose>
                        <c:when test="${bemTributavelVo.concordaComValorArbitrado.textoCorrente == 'SIM'}">
                           <td align="center" valign="top" colspan="1">R$ <c:out value="${bemTributavelVo.valorInformadoFormatado}"/></td>
                           <td align="center" valign="top" colspan="1">R$ <b><c:out value="${bemTributavelVo.valorArbitradoAuxFormatado}"></c:out></b></td>
                        </c:when>
                        <c:otherwise>
                           <td align="center" valign="top" colspan="1">R$ <b><c:out value="${bemTributavelVo.valorInformadoFormatado}"></c:out></b></td>
                           <td align="center" valign="top" colspan="1">R$ <c:out value="${bemTributavelVo.valorArbitradoAuxFormatado}"></c:out></td>
                        </c:otherwise>
                     </c:choose>
                  <td align="center" valign="top"><c:out value="${bemTributavelVo.concordaComValorArbitrado.textoCorrente}"/></td>   
                  
               </c:when>
               <c:otherwise>
               
                  <c:choose>
                     <c:when test="${bemTributavelVo.concordaComValorArbitrado.textoCorrente == 'SIM'}">
                        <td align="center" valign="top" colspan="1">R$ <c:out value="${bemTributavelVo.valorInformadoFormatado}"/></td>
                        <td align="center" valign="top" colspan="1">R$ <b><c:out value="${bemTributavelVo.valorMercadoFormatado}"></c:out></b></td>
                     </c:when>
                     <c:otherwise>
                        <td align="center" valign="top" colspan="1">R$ <b><c:out value="${bemTributavelVo.valorInformadoFormatado}"></c:out></b></td>
                        <td align="center" valign="top" colspan="1">R$ <c:out value="${bemTributavelVo.valorMercadoFormatado}"></c:out></td>
                     </c:otherwise>
                  </c:choose>
                  <td align="center" valign="top"><c:out value="${bemTributavelVo.concordaComValorArbitrado.textoCorrente}"/></td>
                  
               </c:otherwise>
            </c:choose>
         </c:otherwise>			
         </c:choose>
         
			<c:choose>
				<c:when test="${bemTributavelVo.bemVo.classificacaoTipoBem.valorCorrente == IMOVEL_URBANO && not giaITCDVo.giaRetificada}">
					<td align="left" valign="top"><a href="javascript: solicitarViewConsultaFichaImovelUrbano(<c:out value="${status.index}"></c:out>);">Detalhe</a>&nbsp;</td>
				</c:when>
				<c:when test="${bemTributavelVo.bemVo.classificacaoTipoBem.valorCorrente == IMOVEL_RURAL && not giaITCDVo.giaRetificada}">
					<td align="left" valign="top"><a href="javascript: solicitarViewConsultaFichaImovelRural(<c:out value="${status.index}"></c:out>);">Detalhe</a>&nbsp;</td>
				</c:when>
				<c:otherwise>
					<td align="right" valign="middle">&nbsp;&nbsp;</td>
				</c:otherwise>
			</c:choose>
		 </tr>
	 </c:forEach>
	 </c:if>
  </table>				
  <br/>
  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
    <tr> 
      <td align="center"> 
        <input type="button" value=" << Anterior " class="SEFAZ-INPUT-Botao" name="btnAnterior" id="btnAnterior" onClick="solicitarAbaDadosGerais();">
			<abaco:botaoCancelarSemMensagem></abaco:botaoCancelarSemMensagem>
        <input type="button" value="  Próximo >> " class="SEFAZ-INPUT-Botao" name="btnProximo" id="btnProximo" onClick="solicitarAbaBeneficiarios();"> 
	  </td>
    </tr>
  </table>
  </form>
