<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarGIAITCDSeparacaoDivorcioAbaBensTributaveis.jsp
* Criação : Outubro de 2007 / Rogério Sanches de Oliveira
* Revisão :  Novembro 2007 / João Batista Padilha e Silva
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem"%>
<form method="POST" name="form"  action="#">
	<table cellspacing="0" cellpadding="0" border="0" width="800" align="center">
		 <tr class="SEFAZ-TR-SubTituloEsq"> 
			<td align="center">Classificação</td>
			<td align="center">Tipo</td>
			<td align="center">Descrição</td>
			<c:if test="${giaITCDVo.giaRetificada}">
				<td align="center">Valor Avaliado</td>
			</c:if>
			<td align="center">Bem Particular <br> (Anterior Casamento <br> / Herança / Doação)</td>		
			<td align="center">Valor Declarado</td>
			<td align="center">Valor Arbitrado</td>
			<c:if test="${giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4'}">
            <td align="center">Concorda Valor Arbitrado?</td>
         </c:if>			
			<td colspan="1">&nbsp;</td>
		 </tr>
		 <c:set scope="page" var="IMOVEL_URBANO"><%=DomnTipoBem.IMOVEL_URBANO%></c:set>	 
		 <c:set scope="page" var="IMOVEL_RURAL"><%=DomnTipoBem.IMOVEL_RURAL%></c:set>
		<c:forEach var="bemTributavelVo" items="${giaITCDVo.bemTributavelVo.collVO}" varStatus="contador">
			<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"></c:set>
			<c:if test="${contador.count % 2 != 0}">
				<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
			</c:if>		 
			<tr class="<c:out value="${linhaEstilo}"></c:out>"> 			
				<td align="left" valign="top"><c:out value="${bemTributavelVo.bemVo.classificacaoTipoBem.textoCorrente}"/></td>		
				<td align="left" valign="top"><c:out value="${bemTributavelVo.bemVo.descricaoTipoBem}"/></td>
				<td align="center" valign="top"><c:out value="${bemTributavelVo.descricaoBemTributavel}"/></td>					
				<c:if test="${giaITCDVo.giaRetificada}">
					<td align="center" valign="top" >R$ <c:out value="${bemTributavelVo.valorMercadoFormatado}"/></td>
				</c:if>				
				<td align="left" valign="top" ><c:out value="${bemTributavelVo.bemParticular.textoCorrente}"/></td>
            
             <c:choose>
               <c:when test="${giaITCDVo.numeroVersaoGIAITCD.textoCorrente != 'GIA Versão 1.3' && giaITCDVo.numeroVersaoGIAITCD.textoCorrente != 'GIA Versão 1.4'}">
                  <td align="center" valign="top" colspan="1">R$ <c:out value="${bemTributavelVo.valorInformadoFormatado}"/></td>
                  <td align="center" valign="top" colspan="1">R$ <c:out value="${bemTributavelVo.valorMercadoFormatado}"></c:out></td>            
               </c:when>
               <c:otherwise>
               
            <c:choose>
               <c:when test="${giaITCDVo.giaRetificada}">
               
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
				<td align="left" valign="top">
					<c:choose>
						<c:when test="${bemTributavelVo.bemVo.classificacaoTipoBem.valorCorrente == IMOVEL_URBANO && not giaITCDVo.giaRetificada}">
							<a href="javascript: solicitarViewConsultaFichaImovelUrbano(<c:out value="${contador.index}"></c:out>);">Detalhe</a>&nbsp;
						</c:when>
						<c:when test="${bemTributavelVo.bemVo.classificacaoTipoBem.valorCorrente == IMOVEL_RURAL && not giaITCDVo.giaRetificada}">
							<a href="javascript: solicitarViewConsultaFichaImovelRural(<c:out value="${contador.index}"></c:out>);">Detalhe</a>&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;&nbsp;
						</c:otherwise>
					</c:choose>
				</td>
			</tr>		
		</c:forEach>
		</table>		
		<br/>
		<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
			<tr> 
				<td align="center"> 
					<input type="button" value=" << Anterior " class="SEFAZ-INPUT-Botao" name="btnAnterior" id="btnAnterior" onClick="solicitarAbaDadosGerais();">
					<abaco:botaoCancelarSemMensagem></abaco:botaoCancelarSemMensagem>
					<input type="button" value="  Próximo >> " class="SEFAZ-INPUT-Botao" name="btnProximo" id="btnProximo" onClick="solicitarAbaConjuge();"> 
				</td>
			</tr>
	</table>
<br/>
</form>
