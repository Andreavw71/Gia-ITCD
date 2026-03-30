<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarGIAITCDSeparacaoDivorcioAbaAcompanhamento.jsp
* Criação : Outubro de 2007 / Rogério Sanches de Oliveira 
* Revisão : Novembro de 2007 / João Batista Padilha e Silva
* Revisão : Janeiro de 2008 / Rogério Sanches de Oliveira
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<form method="POST" name="form" action="#">
	<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
		<tr class="SEFAZ-TR-SubTitulo">
			<td align="center" width="200">Data da Alteração</td>
			<td align="center" width="500">Status</td>
			<td align="center" width="40">&nbsp;</td>
		</tr>
		<c:forEach items="${giaITCDVo.statusVo.collVO}" var="atual" varStatus="contador">
			<abaco:linhaTabela>
				<td align="center"><c:out value="${atual.dataAtualizacaoFormatadaCompleta}"/></td>
				<td align="left"><c:out value="${atual.statusGIAITCD.textoCorrente}"/></td>
				<td align="center">
					<img 
						id="status<c:out value="${contador.count}"/>" 
						src="/imagens/expand.gif" 
						alt="Detalhe Status" 
						width="11" 
						border="0" 
						onClick="exibirOcultarInformacoesW3c('detalheStatus<c:out value="${contador.count}"/>',this);" 
						style="cursor:pointer"/>
				</td>
			</abaco:linhaTabela>
			<tr id="detalheStatus<c:out value="${contador.count}"/>" style="display:none">
				<td colspan="3">
					<c:set var="status" value="${atual}" scope="request"/>
						<jsp:include page="/generico/acompanhamento/ViewPesquisarGIAITCDDetalheStatusAbaAcompanhamento.jsp"/>
					<c:remove var="status" scope="request"/>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr> 
			<td align="center"> 
				<input type="button" value=" << Anterior " class="SEFAZ-INPUT-Botao" name="btnAnterior" id="btnAnterior" onClick="solicitarAbaDemonstrativoDeCalculo();">  
				<abaco:botaoCancelarSemMensagem></abaco:botaoCancelarSemMensagem>
			</td>
		</tr>
	</table>
</form>
