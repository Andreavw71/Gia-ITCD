<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManterGIAITCDSeparacaoDivorcioAbaDemonstrativoDeCalculo.jsp
* Criação : Dezembro de 2007 / João Batista Padilha e Silva
* Revisão : Anderson Boehler Iglesias Araujo
* Log : 
*/
--%>
<%@taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="sefaz.mt.arrecadacao.integracao.IntegracaoArrecadacao"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo"%>
<%
	pageContext.setAttribute("PENDENTE_DE_PROTOCOLO", new Integer(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO));
   pageContext.setAttribute("PROTOCOLADO", new Integer(DomnStatusGIAITCD.PROTOCOLADO));
	pageContext.setAttribute("PROTOCOLO_AUTORIZADO_PELA_GIOR", new Integer(DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR));
	pageContext.setAttribute("NOTIFICADO", new Integer(DomnStatusGIAITCD.NOTIFICADO));
	pageContext.setAttribute("RETIFICADO", new Integer(DomnStatusGIAITCD.RETIFICADO));
	pageContext.setAttribute("IMPUGNADO", new Integer(DomnStatusGIAITCD.IMPUGNADO));
	pageContext.setAttribute("PARCELADO", new Integer(DomnStatusGIAITCD.PARCELADO));
	pageContext.setAttribute("SEGUNDA_RETIFICACAO", new Integer(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO));
	pageContext.setAttribute("SEGUNDA_RETIFICACAO_CIENTE", new Integer(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE));
	pageContext.setAttribute("RATIFICADO", new Integer(DomnStatusGIAITCD.RATIFICADO));
	pageContext.setAttribute("RATIFICADO_CIENTE", new Integer(DomnStatusGIAITCD.RATIFICADO_CIENTE));
	pageContext.setAttribute("PARA_INSCRICAO_EM_DIVIDA_ATIVA", new Integer(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA));
	pageContext.setAttribute("REMETIDO_PARA_DIVIDA_ATIVA", new Integer(DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA));
	pageContext.setAttribute("QUITADO", new Integer(DomnStatusGIAITCD.QUITADO));
	pageContext.setAttribute("QUITADO_MANUALMENTE", new Integer(DomnStatusGIAITCD.QUITADO));
	pageContext.setAttribute("AVALIADO", new Integer(DomnStatusGIAITCD.AVALIADO));
	pageContext.setAttribute("ISENTO", new Integer(DomnStatusGIAITCD.ISENTO));
	pageContext.setAttribute("NAO_OCORRENCIA_DE_FATO_GERADOR", new Integer(DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR));
	pageContext.setAttribute("ALTERADO_SERVIDOR", new Integer(DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR));
   pageContext.setAttribute("ALTERADO_PELA_GIOR", new Integer(DomnStatusGIAITCD.ALTERADO_PELA_GIOR));
	pageContext.setAttribute("NOTIFICADO_CIENTE", new Integer(DomnStatusGIAITCD.NOTIFICADO_CIENTE));
	pageContext.setAttribute("RETIFICADO_CIENTE", new Integer(DomnStatusGIAITCD.RETIFICADO_CIENTE));
	pageContext.setAttribute("EM_ELABORACAO", new Integer(DomnStatusGIAITCD.EM_ELABORACAO));
	pageContext.setAttribute("SIM", new Integer(DomnSimNao.SIM));
   
   pageContext.setAttribute("PROTOCOLO_MANUAL",  new Integer(DomnTipoProtocolo.PROTOCOLO_MANUAL));
	pageContext.setAttribute("PROTOCOLO_AUTOMATICO", new Integer(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO));
%>
<script type="text/javascript" language="javascript">
	function confirmaGia()
	{
      var campoTipoProtocoloSelecionado = document.getElementsByName( '<%=Form.CAMPO_TIPO_PROTOCOLO_SELECIONADO_CONTRIBUINTE%>' );
      var isTipoProtocoloSelecionado = false;
      for (i = 0; i < campoTipoProtocoloSelecionado.length; i++) {
          if (campoTipoProtocoloSelecionado[i].checked) {
             isTipoProtocoloSelecionado = true;
          }
      }
      
      if(campoTipoProtocoloSelecionado != null ){
         if(isTipoProtocoloSelecionado){
            document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=1"%>';
            document.form.submit();
         }
         else
         {
            alert('Por favor senhor contribuinte, selecione se está de acordo com o(s) Valor(es) Arbitrado(s) ? ');
         }
      }
      return false;
	}
	function validaFormulario()
	{
      document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=1"%>';
		document.form.submit();
	}
	function gerarDAR()
	{
		
		// existe uma definição que no ITCD todos os pdf's deverão ser abertos em popup
		document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.disabled = true;
		document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.className = "SEFAZ-INPUT-Botao-Disabled";
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_IMPRIMIR_DAR+"=1"%>';
		document.form.submit();
	}
	
	function imprimirDAR()
	{
		document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.disabled = true;
		document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.className = 'SEFAZ-INPUT-Botao-Disabled';

		document.form.action='<%=IntegracaoArrecadacao.EMISSAO_DAR_URL%>?<%=IntegracaoArrecadacao.EMISSAO_DAR_PARAMETRO%>='+'<c:out value="${giaITCDVo.giaITCDDarVo.darEmitido.numrDarSeqc}" />'+'&'+'<%=IntegracaoArrecadacao.EMISSAO_DAR_JANELA%>'+'=1';		
		window.open('','darITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
		document.form.target = 'darITCD';
		document.form.submit();
		document.form.target = '_self';
	}
	
	
	function imprimirDeclaracaoIsencao()
	{
		// existe uma definição que no ITCD todos os pdf's deverão ser abertos em popup
		document.form.<%=Form.BOTAO_IMPRIMIR%>.disabled = true;
		document.form.<%=Form.BOTAO_IMPRIMIR%>.className = "SEFAZ-INPUT-Botao-Disabled";
		window.open('','relatorioGIAITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
		document.form.target = 'relatorioGIAITCD';
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_IMPRIMIR_DECLARACAO_ISENCAO+"=1"%>';
		document.form.<%=Form.BOTAO_IMPRIMIR%>.disabled = false;
		document.form.<%=Form.BOTAO_IMPRIMIR%>.className = "SEFAZ-INPUT-Botao";
		document.form.submit();
		document.form.target = '_self';
	}
	function imprimirDeclaracaoFatoGerador()
	{
		// existe uma definição que no ITCD todos os pdf's deverão ser abertos em popup
		document.form.<%=Form.BOTAO_IMPRIMIR%>.disabled = true;
		document.form.<%=Form.BOTAO_IMPRIMIR%>.className = "SEFAZ-INPUT-Botao-Disabled";
		window.open('','relatorioGIAITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
		document.form.target = 'relatorioGIAITCD';
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_IMPRIMIR_DECLARACAO_FATO_GERADOR+"=1"%>';
		document.form.<%=Form.BOTAO_IMPRIMIR%>.disabled = false;
		document.form.<%=Form.BOTAO_IMPRIMIR%>.className = "SEFAZ-INPUT-Botao";
		document.form.submit();
		document.form.target = '_self';
	}
</script>
<form method="POST" name="form" action="">
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr class="SEFAZ-TR-Titulo">
			<td colspan="4">Demonstrativo de Cálculo</td>			
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloSaida" width="230">Tipo de GIA:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" width="100"><c:out value="${giaITCDVo.tipoGIA.textoCorrente}"></c:out></td>
			<td class="SEFAZ-TD-RotuloSaida" width="270">Data da GIA:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" width="140"><c:out value="${giaITCDVo.dataCriacaoFormatada}"/></td>
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloSaida">Lei:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.parametroLegislacaoVo.numeroLegislacao}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">Valor da UPF:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorUPFFormatado}"/></td>
		</tr>
		<c:choose>
			<c:when test="${giaITCDVo.giaAvaliada}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">Valor total bens Avaliados:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.totalSomaValoresArbitradoAvaliacaoFormatado}"/></td>			
					<td class="SEFAZ-TD-CampoSaida" colspan="2">&nbsp;</td>
				</tr>
				<c:choose>
					<c:when test="${giaITCDVo.bemTributavelVo.exibirBemParticular}">
						<tr>
							<td class="SEFAZ-TD-RotuloSaida">Base Cálculo Tributável:&nbsp;</td>
							<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorBaseCalculoTributavelFormatado}"></c:out></td>			
							<td class="SEFAZ-TD-RotuloSaida">Valor total bens particulares:&nbsp;</td>
							<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorTotalBensDeclaradosAnteriorCasamentoFormatado}"></c:out></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td class="SEFAZ-TD-RotuloSaida">Base Cálculo Tributável:&nbsp;</td>
							<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorBaseCalculoTributavelFormatado}"></c:out></td>	
							<td class="SEFAZ-TD-CampoSaida" colspan="2">&nbsp;</td>
						</tr>
					</c:otherwise>
				</c:choose>				
			</c:when>
			<c:otherwise>
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">Valor total bens arbitrados:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorTotalInformadoBensDeclaradosFormatado}"/></td>	
               <td class="SEFAZ-TD-RotuloSaida" colspan="2">&nbsp;&nbsp;</td>	
				</tr>
            
            <tr>
            <td class="SEFAZ-TD-RotuloSaida">Valor total bens informados contribuinte:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida" colspan="1">R$ <c:out value="${giaITCDVo.bemTributavelVo.valorTotalInformadoContribuinteFormatado}"></c:out></td>	
            <c:choose>               
					<c:when test="${giaITCDVo.bemTributavelVo.exibirBemParticular}">														
							<td class="SEFAZ-TD-RotuloSaida">Valor total bens particulares:&nbsp;</td>
							<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorTotalBensDeclaradosAnteriorCasamentoFormatado}"></c:out></td>  
					</c:when>     
               <c:otherwise>                         
                  <td class="SEFAZ-TD-RotuloSaida">                     </td>
                  <td class="SEFAZ-TD-CampoSaida" colspan="2">&nbsp;</td>               
               </c:otherwise>
               </c:choose>
            </tr>                   
            <tr>                       
                  <td class="SEFAZ-TD-RotuloSaida">Base Cálculo Tributável:&nbsp;</td>
                  <td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorBaseCalculoTributavelFormatado}"></c:out></td>	
                  <c:choose>
                     <c:when test="${giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4'}">                  
                        <c:if test="${giaITCDVo.calculoPercentualValoresArbitradoConcordado >= giaITCDVo.porcentagemAconsiderar}">                                
                              <td class="SEFAZ-TD-RotuloSaida">Valor total Bens Acordados:&nbsp;</td>
                              <td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.totalSomaValoresConcordadoFormatado}"/></td>                           		                        
                        </c:if>
                     </c:when>
                     <c:otherwise>
                              <td class="SEFAZ-TD-RotuloSaida">                     </td>
                              <td class="SEFAZ-TD-CampoSaida" colspan="2">&nbsp;</td>   
                     </c:otherwise>
                  </c:choose>
            </tr>      
		<tr>
			<td colspan="4" class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
		</tr>
      
   </c:otherwise>
   </c:choose>
      <c:if test="${giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4'}">
         <c:if test="${giaITCDVo.giaAvaliada}">
            <c:if test="${giaITCDVo.calculoPercentualValoresArbitradoConcordado >= 70}">     
            <tr>
                  <td class="SEFAZ-TD-RotuloSaida">Valor total Bens Acordados:&nbsp;</td>
                  <td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.totalSomaValoresConcordadoFormatado}"/></td>  
                  <td class="SEFAZ-TD-CampoSaida" colspan="2">&nbsp;</td>
            </tr>
            </c:if>
         </c:if>
      </c:if>      
	</table>
	<c:set var="exibeDemonstrativo" value="true" scope="request"/>
	<%
	if(JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_SEPARACAO_DIVORCIO))
	{
	%>
		<c:if test="${giaITCDVo.giaConfirmada.valorCorrente != SIM}">
			<c:set var="exibeDemonstrativo" value="false" scope="request"/>
		</c:if>		
	<%
	}
	%>		
	<jsp:include page="/giaitcd/util/ViewMensagemDemonstrativoCalculo.jsp"/>
	<c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == PENDENTE_DE_PROTOCOLO
						or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == PROTOCOLADO 
						or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == PROTOCOLO_AUTORIZADO_PELA_GIOR 
						or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == NOTIFICADO 
						or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == NOTIFICADO_CIENTE
						or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == RETIFICADO
						or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == RETIFICADO_CIENTE
						or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == ISENTO
						or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == QUITADO
						or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == QUITADO_MANUALMENTE
						or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == IMPUGNADO
						or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == NAO_OCORRENCIA_DE_FATO_GERADOR
						or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == ALTERADO_SERVIDOR 
						or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == EM_ELABORACAO
                  or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == ALTERADO_PELA_GIOR
					}">
		<c:if test="${exibeDemonstrativo}">
			<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
				<tr>
					<td colspan="2">
						<table cellspacing="2" cellpadding="2" border="1" width="100%" align="center">
							<tr class="SEFAZ-TD-RotuloSaida">
								<td align="center" width="25%">Valor do Bens em Comum</td>						
								<td align="center" width="43%" >Nome</td>
								<td align="center" width="32%">Valor por C&ocirc;njuge</td>
							</tr>
							<tr>
								<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorCalculoDemonstrativoFormatado}"/></td>
								<td class="SEFAZ-TD-CampoSaida" ><c:out value="${giaITCDVo.conjuge1.pessoaConjuge.nomeContribuinte}"></c:out></td>
								<td class="SEFAZ-TD-CampoSaida">R$<div align="right"><c:out value="${giaITCDVo.valorTotalConjuge1Formatado}"></c:out></div></td>
							</tr>
							<tr>
								<td class="SEFAZ-TD-CampoSaida">&nbsp;</td>
								<td class="SEFAZ-TD-CampoSaida" ><c:out value="${giaITCDVo.conjuge2.pessoaConjuge.nomeContribuinte}"></c:out></td>
								<td class="SEFAZ-TD-CampoSaida">R$<div align="right"><c:out value="${giaITCDVo.valorTotalConjuge2Formatado}"></c:out></div></td>
							</tr>
							<tr class="SEFAZ-TD-RotuloSaida">
								<td colspan="3" align="center" >RESUMO EXPLICATIVO</td>
							</tr>
                     </table>
                     <table cellspacing="2" cellpadding="2" border="2" width="100%" align="center">
                     <c:choose>
                         <c:when test = "${!giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">
                              <tr>
                                 <td colspan="2" class="SEFAZ-TD-RotuloSaida" >Contribuinte (Cônjuge)</td>
                                 <td class="SEFAZ-TD-CampoSaida" ><c:out value="${giaITCDVo.conjugeExcesso.pessoaConjuge.nomeContribuinte}"/></td>
                              </tr>
                              <tr>
                                 <td colspan="2" class="SEFAZ-TD-RotuloSaida">Base de Cálculo Tributável</td>
                                 <td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${giaITCDVo.valorBaseCalculoTributavelFormatado}"/></div></td>							
                              </tr>
                              <tr>
                                 <td colspan="2" class="SEFAZ-TD-RotuloSaida">Alíquota</td>
                                 <td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${giaITCDVo.valorAliquotaFormatado}"></c:out> %</div></td>
                              </tr>
                         </c:when>
                         <c:otherwise>
                                       <tr>
                                          <td width="115" class="SEFAZ-TD-RotuloSaida"><div align="center">Nome</div></td>
                                          <td width="115" class="SEFAZ-TD-RotuloSaida"><div align="center">Base de Cálculo Tributável</div></td>
                                          <c:set var="tamanhoTd" value="2"></c:set>					
                                          <c:set var="qtdeColunasAliquota" value="0"	/>
                                          <c:if test="${giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">															
                                                 <c:if test="${not empty giaITCDVo.giaItcdSeparacaoDivorcioAliquotaVo.collVO}">
                                                                     <c:forEach var="giaItcdSeparacaoAliquotaVo" items="${giaITCDVo.giaItcdSeparacaoDivorcioAliquotaVo.collVO}" varStatus="contador">
                                                                           <c:set var="qtdeColunasAliquota" value="${contador.index +1}"/>																																					
                                                                           <c:if test="${giaItcdSeparacaoAliquotaVo.percentualAliquota == 0}">
                                                                                    <td width="70" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor da Isenção</div></td>	
                                                                           </c:if>
                                                                           <c:if test="${giaItcdSeparacaoAliquotaVo.percentualAliquota != 0}">
                                                                                    <td width="70" class="SEFAZ-TD-RotuloSaida"><div align="center"><c:out value="${giaItcdSeparacaoAliquotaVo.percentualAliquotaFormatado}"></c:out> %</div></td>	
                                                                           </c:if>
                                                                     </c:forEach>																	 
                                                 </c:if>
                                                <c:set var="tamanhoTd" value="${tamanhoTd+qtdeColunasAliquota}"/>									
                                          </c:if>
                                          <td width="100" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor do ITCD</div></td>
                                          <td width="100" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor a Recolher Devido</div></td>
                                          <c:set var="tamanhoTd" value="${tamanhoTd+3}"/>
                                       </tr>
                                       <tr>
                                          <td width="115" class="SEFAZ-TD-CampoSaida"><div align="center"><c:out value="${giaITCDVo.conjugeExcesso.pessoaConjuge.nomeContribuinte}"/></div></td>
                                          <td width="115" class="SEFAZ-TD-CampoSaida"><div align="center"><c:out value="${giaITCDVo.valorBaseCalculoTributavelFormatado}"/></div></td>
                                          <c:set var="tamanhoTd" value="2"></c:set>					
                                          <c:set var="qtdeColunasAliquota" value="0"	/>
                                          <c:if test="${giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">															
                                                 <c:if test="${not empty giaITCDVo.giaItcdSeparacaoDivorcioAliquotaVo.collVO}">
                                                                     <c:forEach var="giaItcdSeparacaoAliquotaVo" items="${giaITCDVo.giaItcdSeparacaoDivorcioAliquotaVo.collVO}" varStatus="contador">
                                                                           <c:set var="qtdeColunasAliquota" value="${contador.index +1}"/>
                                                                           <td width="70" class="SEFAZ-TD-CampoSaida"><div align="center"><c:out value="${giaItcdSeparacaoAliquotaVo.valorBaseCalculoFormatado}"></c:out></div></td>	
                                                                     </c:forEach>																	 
                                                 </c:if>
                                                <c:set var="tamanhoTd" value="${tamanhoTd+qtdeColunasAliquota}"/>									
                                          </c:if>
                                          <td width="100" class="SEFAZ-TD-CampoSaida"><div align="center"><c:out value="${giaITCDVo.valorITCDFormatado}"/></div></td>
                                          <td width="100" class="SEFAZ-TD-CampoSaida"><div align="center"><c:out value="${giaITCDVo.valorRecolhimentoFormatado}"/></div></td>
                                          <c:set var="tamanhoTd" value="${tamanhoTd+3}"/>
                                       </tr>
                         </c:otherwise>
                     </c:choose>
                     </table>
                      <table cellspacing="2" cellpadding="2" border="2" width="100%" align="center">
							<tr>
								<td colspan="2" class="SEFAZ-TD-RotuloSaida">Valor ITCD</td>
								<td class="SEFAZ-TD-CampoSaida">R$<div align="right"><c:out value="${giaITCDVo.valorITCDFormatado}"/></div></td>
							</tr>
							<tr>
								<td colspan="2" class="SEFAZ-TD-RotuloSaida">Valor à recolher devido</td>
								<td class="SEFAZ-TD-CampoSaida">R$<div align="right"><c:out value="${giaITCDVo.valorRecolhimentoFormatado}"/></div></td>
							</tr>												
						</table>
                  
                  <%if(JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_SEPARACAO_DIVORCIO))
                  {%>
                  
                  <%}
                  else
                  {%>
                  <c:choose>                  
                  <c:when test="${giaITCDVo.numeroVersaoGIAITCD.textoCorrente != 'GIA Versão 1.3' && giaITCDVo.numeroVersaoGIAITCD.textoCorrente != 'GIA Versão 1.4'}">                  
                        </br>
                  </c:when>
                  <c:otherwise>
                  <table cellspacing="2" cellpadding="2" border="1" width="100%" align="center">
                     <tr class="SEFAZ-TR-Titulo">
                        <td colspan="4">Resumo de Cálculo</td>			
                     </tr>
                  </table>                   
                  <table cellspacing="2" cellpadding="2" border="1" width="100%" align="center">  
							<tr>                        
								<td width="66%" class="SEFAZ-TD-RotuloSaida">Soma da base de cálculo apurada pelo Fisco de valores arbitrados aceitos e da base de cálculo declarada pelo interessado de valores arbitrados e refutados(A):&nbsp;</td>
								<td width="34%" class="SEFAZ-TD-CampoSaida"><div align="right">R$
                        <c:out value="${giaITCDVo.totalSomaValoresConcordadoFormatado}"/></div></td>   
							</tr>
						</table>                  
                  <table cellspacing="2" cellpadding="2" border="1" width="100%" align="center">    
							<tr>      
								<td width="66%" class="SEFAZ-TD-RotuloSaida">Total da Base de Cálculo apurada pelo Fisco(B):&nbsp;</td>
								<td width="34%" class="SEFAZ-TD-CampoSaida"><div align="right">R$ 
                        <c:out value="${giaITCDVo.totalSomaValoresArbitradoAvaliacaoFormatado}"/></div></td>							                        
							</tr>
                  </table>          
                  <table cellspacing="2" cellpadding="2" border="1" width="100%" align="center">                                 
							<tr>                        
								<td width="66%" class="SEFAZ-TD-RotuloSaida">Índice Percentual da relação entre os itens A e B, em conformidade com a Portaria-SEFAZ/MT em vigor:&nbsp;</td>
								<td width="34%" class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${giaITCDVo.calculoPercentualValoresArbitradoConcordadoFormatado}"/>%</div></td>                        
							</tr>
                  </table> 
                  </c:otherwise>
                  </c:choose>
                  <%}%> 
					</td>
				</tr>
			</table>		
		</c:if>
	</c:if>
	<c:remove var="exibeDemonstrativo" scope="request"/>
    <c:if test="${giaITCDVo.tipoProtocoloGIAOpcional}">    
      <c:choose>
         <c:when test="${giaITCDVo.numeroVersaoGIAITCD.textoCorrente != 'GIA Versão 1.3' && giaITCDVo.numeroVersaoGIAITCD.textoCorrente != 'GIA Versão 1.4'}">
               <jsp:include  page="/generico/util/ViewSelecionarTipoProtocolo.jsp"/>
         </c:when>
         <c:otherwise>
         <br>
            <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
               <tr>
                  <td>
                     <c:choose>
                        <c:when test="${giaITCDVo.tipoProtocoloGIA.valorCorrente == PROTOCOLO_AUTOMATICO}">
                           <input type="hidden" name="<%=Form.CAMPO_TIPO_PROTOCOLO_SELECIONADO_CONTRIBUINTE%>" value="<%=DomnTipoProtocolo.PROTOCOLO_AUTOMATICO%>"/>
                           <b>
                           <c:out value="${giaITCDVo.tipoProtocoloGIA.textoCorrente}"/> prazo para avaliação e liberação do Documento de Arrecadação: 30 minutos.
                           </b>
                        </c:when>
                        <c:otherwise>
                           <input type="hidden" name="<%=Form.CAMPO_TIPO_PROTOCOLO_SELECIONADO_CONTRIBUINTE%>" value="<%=DomnTipoProtocolo.PROTOCOLO_MANUAL%>">
                           <b>
                           <c:out value="${giaITCDVo.tipoProtocoloGIA.textoCorrente}"/> prazo para avaliação e liberação do Documento de Arrecadação: Acima de 90 dias.
                           </b>
                        </c:otherwise>
                     </c:choose>			
                  </td>
               </tr>   
            </table>            
         </c:otherwise>
      </c:choose>      
   </c:if>
	<br/>
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr> 
			<td align="center"> 
			<input type="button" value="<%=Form.TEXTO_BOTAO_ANTERIOR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_ANTERIOR_CLONE%>" onclick="solicitarAbaConjuge();">
         <abaco:botaoCancelar/>
			<%if(JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_SEPARACAO_DIVORCIO))
			{%>			   
				<input type="button" value="<%=Form.TEXTO_BOTAO_PROXIMO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PROXIMO_CLONE%>" onclick="solicitarAbaAcompanhamento();">
			<%}
			else
			{%>			
				<input type="button" value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR%>" onclick="validaFormulario()"/>
			<%}%>
			</td>
		</tr>
	</table>
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<c:if test="${giaITCDVo.imprimirDar}">
			<script type="text/javascript" language="javascript">
				imprimirDAR();
			</script>
		</c:if>
	</table>	
	<abaco:log/>
</form>
