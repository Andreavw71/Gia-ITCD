<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManterGIAITCDAbaDoacaoSucessiva.jsp
* Criação : Outubro de 2007 / Rogério Sanches de Oliveira
* Revisão : Fevereiro de 2008/ Rogério Sanches de Oliveira
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page import=" br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoContribuinte"%>
<%
pageContext.setAttribute("INVENTARIO_ARROLAMENTO", new Integer(DomnTipoProcesso.INVENTARIO_ARROLAMENTO));
pageContext.setAttribute("DOACAO", new Integer(DomnTipoProcesso.DOACAO));
%>
<script type="text/javascript" language="javascript">
	function validaFormulario()
	{
		return true;
	}
	
	function salvarBeneficiarios()
	{
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.PARAMETRO_SALVAR_BENEFICIARIOS+"=1"%>';
		document.form.submit();
		return true;
	}
	
</script>
<form method="POST" name="form" action="">
   <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
        <tr class="SEFAZ-TR-Titulo">
            <td colspan="7">Resumo Doações - Ano: <c:out value="${giaITCDVo.anoCriacaoFormatado}"></c:out>
        </td>
     </tr>
   </table>
   <br/>
   <table cellspacing="0" cellpadding="0" border="0" width="100%" align="center">
      <c:forEach var="beneficiario" items="${giaITCDVo.beneficiarioVo.collVO}" varStatus="contador">		
         <c:if test="${not empty beneficiario.giaitcdDoacaoSucessivaVo.collVO}">    
            <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"></c:set>
            <c:if test="${contador.count % 2 != 0}">
               <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
            </c:if>
            <c:if test="${contador.index == 0}">
               <tr class="SEFAZ-TR-Titulo">
                  <td colspan="7">Beneficiários</td>
               </tr>
            </c:if>
            <tr class="<c:out value="${linhaEstilo}"></c:out>">
               <td width="23%" class="SEFAZ-TD-RotuloSaida">Nome:&nbsp;</td>
               <td width="24%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.nomeContribuinte}"></c:out></td>
               <td width="21%" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
               <td width="16%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.numrDocumento}"></c:out></td>
            </tr>		
            <tr>
               <td colspan="7">
                  <table cellspacing="0" cellpadding="0" border="1" width="100%">
                     <tr class="SEFAZ-TR-Titulo">
                        <th style="text-align:left;">GIA ITCD</th>
                        <th style="text-align:left;">E-process</th>
                        <th style="text-align:left;">Mês</th>
                        <th style="text-align:left;">Valor Base de Cálculo</th>
                        <th style="text-align:left;">Valor ITCD</th>
                     </tr>
                     <c:forEach var="doacao" items="${beneficiario.giaitcdDoacaoSucessivaVo.collVO}" varStatus="dst">
                        <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"></c:set>
                     <c:if test="${contador.count % 2 != 0}">
                        <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
                     </c:if>
                        <tr class="<c:out value="${linhaEstilo}"></c:out>">
                           <td class="SEFAZ-TD-CampoSaida"><c:out value="${doacao.giaITCDVo.codigo}" /></td>
                           <td class="SEFAZ-TD-CampoSaida"><c:out value="${doacao.numeroEprocess}" /></td>
                           <td class="SEFAZ-TD-CampoSaida"><c:out value="${doacao.mesGIAITCDAnterior}" /></td>  
                           <td class="SEFAZ-TD-CampoSaida"><c:out value="${doacao.valorBaseDeCalcFormatado}" /></td>
                           <c:choose>
                              <c:when test="${doacao.valorITCDBeneficiario == 0.0}">
                                    <td class="SEFAZ-TD-CampoSaida"><div align="right"><c:if test="${doacao.isIsentoUpf}">**</c:if><c:out value="${doacao.valorITCDFormatado}"/></div></td>
                              </c:when>
                              <c:otherwise>
                                 <td class="SEFAZ-TD-CampoSaida"><div align="right"><c:if test="${doacao.isIsentoUpf}">**</c:if><c:out value="${doacao.valorITCDBeneficiarioFormatado}"/></div></td>
                              </c:otherwise>
                           </c:choose>                             
                        </tr>
                     </c:forEach>
                  </table>
               </td>
            </tr>
            <tr class="SEFAZ-TR-Titulo">
               <td colspan="5">&nbsp;</td>
            </tr>
         </c:if> 
      </c:forEach>
   </table>      
	<br/>
   <c:if test="${quantidadeDoacaoSucessivaMaiorQueParametro}">
         <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
                  <tr>
                     <td><font color="red"><b>* Preenchimento não permitido! Existem duas ou mais GIA-ITCD-e de doação incluídas em data anterior a 06/08/2025. O Contribuinte deverá realizar a declaração utilizando o Sistema e-Process com o tipo de processo "ITCD - Denúncia Espontânea".</b></font></td>
                  </tr>
         </table>
   </c:if>
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr> 
			<td align="center">
			<input type="button" value="<%=Form.TEXTO_BOTAO_ANTERIOR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_ANTERIOR_CLONE%>" onClick="solicitarAbaBeneficiarios();">
         <abaco:botaoCancelar/>
         <input type="button" value="<%=Form.TEXTO_BOTAO_PROXIMO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PROXIMO_CLONE%>" onClick="solicitarAbaDemonstrativoDeCalculo();">
			</td>
		</tr>
		<tr>
			<td><font color="red"><b>* Campos Obrigatórios</b></font></td>
		</tr>
	</table>
	<abaco:log/>
</form>
