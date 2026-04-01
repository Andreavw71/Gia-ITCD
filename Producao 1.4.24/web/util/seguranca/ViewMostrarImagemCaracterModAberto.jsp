<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso ? Sefaz-MT
* Arquivo : ViewMostrarImagemCaracterModAberto.jsp
* Criação : 
* Revisão : 
* Log : 
*/
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<c:if test="${not empty giaITCDVo}">
	<c:set var="logSefazVo" value="${giaITCDVo.logSefazVo}" scope="page"/>
</c:if>
<c:if test="${not empty giaITCDSeparacaoDivorcioVo}">
	<c:set var="logSefazVo" value="${giaITCDSeparacaoDivorcioVo.logSefazVo}" scope="page"/>
</c:if>
<c:if test="${not empty giaITCDInventarioArrolamentoVo}">
	<c:set var="logSefazVo" value="${giaITCDInventarioArrolamentoVo.logSefazVo}" scope="page"/>
</c:if>
<c:if test="${not empty giaITCDDoacaoVo}">
	<c:set var="logSefazVo" value="${giaITCDInventarioArrolamentoVo.logSefazVo}" scope="page"/>
</c:if>

<c:if test="${ empty logSefazVo || empty logSefazVo.usuario || logSefazVo.usuario.codigo == 0 }">
	<jsp:include page="/util/seguranca/ViewMostrarImagemCaracter.jsp"/>  
</c:if>