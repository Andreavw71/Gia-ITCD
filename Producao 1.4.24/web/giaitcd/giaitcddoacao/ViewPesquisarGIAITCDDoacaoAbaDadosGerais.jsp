<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarGIAITCDDoacaoAbaDadosGerais.jsp
* Criação : Novembro de 2007 / Rogério Sanches de Oliveira
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form" %>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%
   pageContext.setAttribute("VERSAO_1", new Integer(DomnVersaoGIAITCD.VERSAO_1));
	pageContext.setAttribute("VERSAO_2", new Integer(DomnVersaoGIAITCD.VERSAO_1_2));
   pageContext.setAttribute("PROTOCOLO_MANUAL", new Integer(DomnTipoProtocolo.PROTOCOLO_MANUAL));
	pageContext.setAttribute("PROTOCOLO_AUTOMATICO", new Integer(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO));	
%>
<form name="form"   method="POST" action="#">
	  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
	<tr>
      <td colspan="4" bgcolor="#336699" class="SEFAZ-TR-Titulo"><div align="center">Dados do Doador Declarante</div></td>
    </tr>
    <tr>
      <td width="217" align="left" class="SEFAZ-TD-RotuloSaida">Tipo GIA:&nbsp; </td>
      <td width="153" align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.tipoGIA.textoCorrente}"/> </div></td>
      <td width="178" align="left" class="SEFAZ-TD-RotuloSaida">Nº da GIA:&nbsp;</td>
      <td width="192" align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:if test="${giaITCDVo.tipoProtocoloGIA.textoCorrente == 'Protocolo Automático' and (giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.2')}"><b>A&nbsp;</b></c:if><c:out value="${giaITCDVo.codigo}"/></div></td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Declarante:&nbsp; </td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.nomeContribuinte}"/> </div></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">CPF/CNPJ:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.numrDocumento}"/></div></td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.endereco}"/> </div></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Número:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.enderecoNumero}"/> </div></td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Complemento:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.enderecoComplemento}"/> </div></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Ponto de referência:&nbsp; </td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.pontoReferencia}"/> </div> </td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.enderecoBairro}"/> </div></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.enderecoCEP}"/> </div></td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.municipio}"/> </div></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.siglaUF}"/> </div></td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.numrDdd}"/> </div></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.numrTelefone}"/> </div></td>
    </tr>
    <tr>
      <td align="left" class="SEFAZ-TD-RotuloSaida">E-mail:&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.responsavelVo.email}"/> </div></td>
      <td align="left" class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
      <td align="left" class="SEFAZ-TD-CampoSaida">&nbsp;</td>

    </tr>
    
    <c:if test="${not empty giaITCDVo.procuradorVo.numrDocumento}">
		<tr>
			<td colspan="4" >&nbsp;</td>
		</tr>
		 <tr>
			<td colspan="4" bgcolor="#336699" class="SEFAZ-TR-Titulo"><div align="center">Dados do Procurador </div></td>
		 </tr>
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">Procurador:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.procuradorVo.nomeContribuinte}"/> </div></td>
			<td class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.procuradorVo.numrDocumento}"/> </div></td>
		 </tr>
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.procuradorVo.endereco}"/> </div></td>
			<td class="SEFAZ-TD-RotuloSaida">Número:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.procuradorVo.enderecoNumero}"/> </div></td>
		 </tr>
		 
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">Complemento:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.procuradorVo.enderecoComplemento}"/> </div></td>
			<td class="SEFAZ-TD-RotuloSaida">Ponto de refer&ecirc;ncia:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.procuradorVo.pontoReferencia}"/> </div></td>
		 </tr>
		 
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.procuradorVo.enderecoBairro}"/> </div></td>
			<td class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.procuradorVo.enderecoCEP}"/> </div></td>
		 </tr>
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.procuradorVo.municipio}"/> </div></td>
			<td class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.procuradorVo.siglaUF}"/> </div></td>
		 </tr>
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.procuradorVo.numrDdd}"/> </div></td>
			<td class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.procuradorVo.numrTelefone}"/> </div></td>
		 </tr>
		 <tr>
			<td class="SEFAZ-TD-RotuloSaida">E-mail:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.procuradorVo.email}"/> </div></td>
			<td class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida">&nbsp;</td>
		 </tr>
    </c:if>
	 <tr>
			<td colspan="4" >&nbsp;</td>
	</tr>
	<tr>
		<td colspan="4" bgcolor="#336699" class="SEFAZ-TR-Titulo"><div align="center" class="style3">Dados da GIA-ITCD</div></td>
	 </tr>
    <tr>
      <td class="SEFAZ-TD-RotuloSaida">Natureza da operação:&nbsp; </td>
      <td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.naturezaOperacaoVo.descricaoNaturezaOperacao}"/> </div></td>
      <td class="SEFAZ-TD-RotuloSaida">Percentual da Doação / Transmissão:&nbsp; </td>
      <td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.fracaoIdealFormatada}"/>%</div></td>
    </tr>
    
    <tr>
      <td class="SEFAZ-TD-RotuloSaida">Agência Protocolo:&nbsp;</td>
      <td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.agenciaProtocolo.nomeUnidade}"/></td>
      <td class="SEFAZ-TD-RotuloSaida">Agência Avaliação:&nbsp;</td>
      <td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.agenciaAvaliacao.nomeUnidade}"/></td>
    </tr>
    
      <c:if test="${giaITCDVo.tipoProtocoloGIA.textoCorrente == 'Protocolo Automático' and (giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.2')}">
         <tr>
            <td width="217" align="left" class="SEFAZ-TD-RotuloSaida">Situação do protocolo automático:&nbsp; </td>
            <td width="153" align="left" class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.exibirStatusProtocolo}"/></div></td>
            <td width="178" align="left" class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
            <td width="192" align="left" class="SEFAZ-TD-CampoSaida"><div align="left">&nbsp;</div></td>
         </tr>
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
  <br/>
</form>
