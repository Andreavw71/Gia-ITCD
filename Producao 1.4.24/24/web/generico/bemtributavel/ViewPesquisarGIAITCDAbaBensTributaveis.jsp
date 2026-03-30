<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarGIAITCDAbaBensTributaveis.jsp
* Criação : Dezembro de 2007 / Rogério Sanches de Oliveira
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%
	pageContext.setAttribute("SEPARACAO_DIVORCIO_PARTILHA", new Integer(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA));
	pageContext.setAttribute("INVENTARIO_ARROLAMENTO",new Integer(DomnTipoProcesso.INVENTARIO_ARROLAMENTO));
	pageContext.setAttribute("DOACAO", new Integer(DomnTipoProcesso.DOACAO));
%>
<c:if test="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == DOACAO}">
	<jsp:include page="/giaitcd/giaitcddoacao/ViewPesquisarGIAITCDDoacaoAbaBensTributaveis.jsp" />
</c:if>
<c:if test="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == INVENTARIO_ARROLAMENTO}">
	<jsp:include page="/giaitcd/giaitcdinventarioarrolamento/ViewPesquisarGIAITCDInventarioArrolamentoAbaBensTributaveis.jsp"/>
</c:if>
<c:if test="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == SEPARACAO_DIVORCIO_PARTILHA}">
	<jsp:include page="/giaitcd/giaitcdseparacaodivorcio/ViewPesquisarGIAITCDSeparacaoDivorcioAbaBensTributaveis.jsp"/>
</c:if>


