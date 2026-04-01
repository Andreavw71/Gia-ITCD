<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
	/*
	* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
	* Arquivo : ViewPesquisarGIAITCDSeparcaoDivorcioAbaDadosGerais.jsp
	* Criação : Outubro de 2007 / Rogério Sanches de Oliveira
	* Revisão : Novembro de 2007 / João Batista Padilha e Silva
	* Log : 
	*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo"%>
<%
   pageContext.setAttribute("VERSAO_1", new Integer(DomnVersaoGIAITCD.VERSAO_1));
	pageContext.setAttribute("VERSAO_2", new Integer(DomnVersaoGIAITCD.VERSAO_1_2));
   pageContext.setAttribute("PROTOCOLO_MANUAL", new Integer(DomnTipoProtocolo.PROTOCOLO_MANUAL));
	pageContext.setAttribute("PROTOCOLO_AUTOMATICO", new Integer(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO));	
%>
<form method="POST" name="form"  action="#">
  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
	 <tr>
		<td colspan="4" bgcolor="#336699" class="SEFAZ-TR-Titulo"><div align="center" class="style3">Dados da GIA-ITCD</div></td>
	 </tr>
	 <tr>
      <td width="174" align="left" class="SEFAZ-TD-RotuloSaida">Tipo de Declara&ccedil;&atilde;o:&nbsp; </td>
      <td width="162" align="left" class="SEFAZ-TD-CampoSaida"> <div align="left"><c:out value="${giaITCDVo.tipoGIA.textoCorrente}"/></div></td>
      <td width="205" align="left" class="SEFAZ-TD-RotuloSaida">N&ordm; Declara&ccedil;&atilde;o:&nbsp;</td>
      <td width="199" align="left" class="SEFAZ-TD-CampoSaida"><c:if test="${giaITCDVo.tipoProtocoloGIA.textoCorrente == 'Protocolo Automático' and (giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.2')}"><b>&nbsp;A</b></c:if> <c:out value="${giaITCDVo.codigo}"/> </td>
    </tr>	 
    <tr>
      <td class="SEFAZ-TD-RotuloSaida">Natureza da opera&ccedil;&atilde;o:&nbsp; </td>
      <td class="SEFAZ-TD-CampoSaida">
		<c:forEach var="naturezaOperacaoVo" items="${giaITCDVo.naturezaOperacaoVo.collVO}">
			<c:if test="${naturezaOperacaoVo.codigo == giaITCDVo.naturezaOperacaoVo.codigo}">
				<c:out value="${naturezaOperacaoVo.descricaoNaturezaOperacao}"/>
			</c:if>
		</c:forEach>
		</td>
      <td class="SEFAZ-TD-RotuloSaida">Agência Protocolo:&nbsp;</td>
      <td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.agenciaProtocolo.nomeUnidade}"/></td>
    </tr>
	 
    <tr>
      <td class="SEFAZ-TD-RotuloSaida">Agência Avaliação:&nbsp;</td>
      <td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.agenciaAvaliacao.nomeUnidade}"/></td>
      <c:choose>
         <c:when test="${giaITCDVo.tipoProtocoloGIA.textoCorrente == 'Protocolo Automático' and (giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.2')}">
               <td align="left" class="SEFAZ-TD-RotuloSaida">Situação do protocolo automático:&nbsp; </td>
               <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.exibirStatusProtocolo}"/></div></td>
         </c:when>
         <c:otherwise>
              <td class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida">&nbsp;</td>
         </c:otherwise>
    </c:choose>
	 </tr>
    
      <c:if test="${giaITCDVo.tipoProtocoloGIA.textoCorrente == 'Protocolo Automático' and (giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.2')}">
         <c:if test="${ not empty giaITCDVo.numeroProcessoEprocess}">
            <tr>
               <td width="217" align="left" class="SEFAZ-TD-RotuloSaida">Número do Processo:&nbsp; </td>
               <td width="153" align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.numeroProcessoEprocess}"/></div></td>
               <td width="178" align="left" class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
               <td width="192" align="left" class="SEFAZ-TD-CampoSaida"><div align="left">&nbsp;</div></td>
            </tr>
         </c:if>
      </c:if>
    
	</table>
  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
	<tr>
      <td colspan="4" bgcolor="#336699" class="SEFAZ-TR-Titulo"><div align="center" class="style3">Dados do Declarante </div></td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Declarante:&nbsp; </td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.nomeContribuinte}"/></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDocumento}"/></td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.endereco}"/></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Número do Logradouro:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoNumero}"/></td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Complemento:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoComplemento}"/></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Ponto de Refer&ecirc;ncia:&nbsp; </td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.pontoReferencia}"/></td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoBairro}"/></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoCEP}"/></td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.municipio}"/> </div></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.siglaUF}"/></td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDdd}"/></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrTelefone}"/></td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">E-mail:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.email}"/></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida">&nbsp;</td>
    </tr>
	<c:if test="${not empty giaITCDVo.procuradorVo.numrDocumento}">
		 <tr>
			<td colspan="4" bgcolor="#336699" class="SEFAZ-TR-Titulo"><div align="center" class="style3">Dados do Procurador </div></td>
		 </tr>
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">Procurador:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.nomeContribuinte}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.numrDocumento}"/></td>
		 </tr>
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.endereco}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">Número do Logradouro:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoNumero}"/></td>
		 </tr>
		 
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">Complemento:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoComplemento}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">Ponto de refer&ecirc;ncia:&nbsp; </td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.pontoReferencia}"/></td>
		 </tr>
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoBairro}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoCEP}"/></td>
		 </tr>
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">Munic&iacute;pio:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.municipio}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.siglaUF}"/></td>
		 </tr>
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.numrDdd}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.numrTelefone}"/></td>
		 </tr>
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">E-mail:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.email}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida">&nbsp;</td>
		 </tr>
	 </c:if>
  </table>  
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr>
			<td colspan="4" bgcolor="#336699" class="SEFAZ-TR-Titulo"><div align="center" class="style3">Dados do Processo Separação / Divórcio / Partilha </div></td>
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloSaida">Regime de casamento :&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.regimeCasamento.textoCorrente}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">Data de Casamento:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.dataCasamentoFormatada}"/></td>
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloSaida">N&ordm; do Processo:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.numeroProcesso}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">Data da Separa&ccedil;&atilde;o/ Div&oacute;rcio/Partilha:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.dataSeparacaoFormatada}"/></td>
		</tr>     
  </table>
  <br/>
   <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
    <tr> 
      <td align="center"> 
        <!--input type="submit" value=" << Anterior " disabled class="SEFAZ-INPUT-Botao-Disabled" name="btnAnterior" id="btnAnterior"-->
		<abaco:botaoCancelarSemMensagem/>
	    <input type="button" value="  Próximo >> " class="SEFAZ-INPUT-Botao" name="btnProximo" id="btnProximo" onClick="solicitarAbaBensTributaveis();"> 
		 </td>
    </tr>
	</table>
</form>
