<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarGIAITCDSeparacaoDivorcioAbaConjuge.jsp
* Criação : Outubro de 2007 / Rogério Sanches de Oliveira 
* Revisão : Novembro de 2007 / João Batista Padilha e Silva
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<form method="POST" name="form" action="#">
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr> 
			<td colspan="4" class="SEFAZ-TR-Titulo">Dados Declarante C&ocirc;njuge 1</td>
		</tr>
		<tr class="SEFAZ-TR-ExibicaoImpar">
			<td width="140" class="SEFAZ-TD-RotuloSaida">Nome:</td>
			<td width="300" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.conjuge1.pessoaConjuge.nomeContribuinte}"/></td>
			<td width="100" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" width="200"><c:out value="${giaITCDVo.conjuge1.pessoaConjuge.numrDocumento}"/></td>
		</tr>
		<c:if test="${not empty giaITCDVo.conjuge1.collVO}">
			<c:set var="conjugeAtual" value="${giaITCDVo.conjuge1}" scope="request"/>			
			<tr>
				<td colspan="4">
					<jsp:include page="/giaitcd/giaitcdseparacaodivorcio/util/ViewPesquisarGIAITCDSeparacaoDivorcioAbaConjugeBensRecebidosInclude.jsp"/>
				</td>
			</tr>
			<c:remove var="conjugeAtual" scope="request"/>			
		</c:if>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr> 
			<td colspan="4" class="SEFAZ-TR-Titulo">Dados C&ocirc;njuge 2</td>
		</tr>
		<tr class="SEFAZ-TR-ExibicaoImpar">
			<td align="left" class="SEFAZ-TD-RotuloSaida">Nome:</td>
			<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.conjuge2.pessoaConjuge.nomeContribuinte}"/></td>
			<td align="left" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
			<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.conjuge2.pessoaConjuge.numrDocumento}"/></td>
		</tr>
		<tr class="SEFAZ-TR-ExibicaoImpar">
			<td align="left" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
			<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.conjuge2.pessoaConjuge.endereco}"/></td>
			<td align="left" class="SEFAZ-TD-RotuloSaida">N&ordm; Logradouro:&nbsp;</td>
			<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.conjuge2.pessoaConjuge.enderecoNumero}"/></td>
		</tr>
		<tr class="SEFAZ-TR-ExibicaoImpar">
			<td align="left" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
			<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.conjuge2.pessoaConjuge.enderecoBairro}"/></td>
			<td align="left" class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
			<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.conjuge2.pessoaConjuge.municipio}"/></td>
		</tr>
		<tr class="SEFAZ-TR-ExibicaoImpar">
			<td align="left" class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
			<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.conjuge2.pessoaConjuge.siglaUF}"/></td>
			<td align="left" class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
			<td align="left" class="SEFAZ-TD-CampoSaida">&nbsp;</td>
		</tr>
		<c:if test="${not empty giaITCDVo.conjuge2.collVO}">
			<c:set var="conjugeAtual" value="${giaITCDVo.conjuge2}" scope="request"/>
			<tr>
				<td colspan="4">
					<jsp:include page="/giaitcd/giaitcdseparacaodivorcio/util/ViewPesquisarGIAITCDSeparacaoDivorcioAbaConjugeBensRecebidosInclude.jsp"/>
				</td>
			</tr>		
			<c:remove var="conjugeAtual" scope="request"/>
		</c:if>
	</table>
<br/>
   <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
    <tr> 
      <td align="center"> 
        <input type="button" value=" << Anterior " class="SEFAZ-INPUT-Botao" name="btnAnterior" id="btnAnterior" onClick="solicitarAbaBensTributaveis();">
			<abaco:botaoCancelarSemMensagem></abaco:botaoCancelarSemMensagem>
        <input type="button" value="  Próximo >> " class="SEFAZ-INPUT-Botao" name="btnProximo" id="btnProximo" onClick="solicitarAbaDemonstrativoDeCalculo();"> 
      </td>
    </tr>
  </table>
</form>
