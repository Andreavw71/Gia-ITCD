<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
	* Arquivo : ViewManterGIAITCDInventarioArrolamentoAbaDemonstrativoDeCalculo.jsp
* Criação : Outubro de 2007 / Rogério Sanches de Oliveira - Anderson Boehler Iglesias Araujo - João Batista Padilha e Silva
* Revisão : Anderson Boehler Iglesias Araujo
* Revisão : Janeiro de 2009 / Ricardo Vitor de Oliveira Moraes
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.Form"%>
<%@ page import="sefaz.mt.arrecadacao.integracao.IntegracaoArrecadacao"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo"%>
<%
	//Dependendo do status da GIA-ITCD, o sistema vai ou não mostrar o demonstrativo de cálculo
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
	pageContext.setAttribute("NOTIFICADO_CIENTE", new Integer(DomnStatusGIAITCD.NOTIFICADO_CIENTE));
	pageContext.setAttribute("RETIFICADO_CIENTE", new Integer(DomnStatusGIAITCD.RETIFICADO_CIENTE));
	pageContext.setAttribute("EM_ELABORACAO", new Integer(DomnStatusGIAITCD.EM_ELABORACAO));
	pageContext.setAttribute("SIM", new Integer(DomnSimNao.SIM));
   
   pageContext.setAttribute("PROTOCOLO_MANUAL",  new Integer(DomnTipoProtocolo.PROTOCOLO_MANUAL));
	pageContext.setAttribute("PROTOCOLO_AUTOMATICO", new Integer(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO));
%>

<script type="text/javascript" language="javascript">

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

 /*
		Função que complementa a função calcular() (acima) formatando o resultado do cálculo.
		Esta função é utilizada para formatar o resultado do cálculo Valor Unitário x Quantidade.
		@param: valor - número a ser formatado.
		@return: valorFormatado (moeda real)
		@autor: Maxwell
		*/
		function floatTomoeda(valor) 
		{	
			x = 0;
			
			if(valor<0)
			{
				valor = Math.abs(valor);
				x = 1;
			}  
			if(isNaN(valor)) valor = "0";
				cents = Math.floor((valor*100+0.5)%100);		
				valor = Math.floor((valor*100+0.5)/100).toString();		
			if(cents < 10) 
				cents = "0" + cents;
			for (var i = 0; i < Math.floor((valor.length-(1+i))/3); i++)
				valor = valor.substring(0,valor.length-(4*i+3))+'.'+valor.substring(valor.length-(4*i+3));   
			ret = valor + ',' + cents;   
			if (x == 1)
				ret = ' - ' + ret;
			return ret;	
		}
		
		function exibeTituloMeeiroBeneficiario(valorColspan)
		{
			document.getElementById('tituloMeeiroBeneficiario').colSpan = valorColspan;
		}
</script>
<form method="POST" name="form" action="">
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr class="SEFAZ-TR-Titulo">
			<td colspan="4">Demonstrativo de Cálculo</td>			
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloSaida" >Tipo de GIA:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${giaITCDVo.tipoGIA.textoCorrente}"/></td>
			<td class="SEFAZ-TD-RotuloSaida" >Data falecimento:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${giaITCDVo.dataFalecimentoFormatada}"/></td>			
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloSaida">Data do Inventário:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${giaITCDVo.dataInventarioFormatada}"/></td>
			<td class="SEFAZ-TD-RotuloSaida" >Fração Ideal:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${giaITCDVo.fracaoIdealFormatada}"/>%</td>
		</tr>			
		<!--
		<c:if test="${giaITCDVo.exibeFracaoIdealBensParticulares}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" >Fração Ideal Bens Particulares:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" >100%</td>
				<td class="SEFAZ-TD-RotuloSaida" >Data da GIA:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" ><c:out value="${giaITCDVo.dataCriacaoFormatada}"/></td>				
			</tr>		
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" >Lei:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" ><c:out value="${giaITCDVo.parametroLegislacaoVo.numeroLegislacao}"/></td>
				<td class="SEFAZ-TD-RotuloSaida">Percentual multa:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.percentualMultaFormatado}"></c:out>%</td>			
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloSaida">Valor UPF:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorUPFFormatado}"/></td>
				<td class="SEFAZ-TD-RotuloSaida" >Valor total bens declarados:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorTotalBensDeclaradosFormatado}"></c:out></td>			
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" >Valor para cálculo demonstrativo:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" colspan="3">R$ <c:out value="${giaITCDVo.valorBaseCalculoTributavelFormatado}"></c:out></td>			
			</tr>
		</c:if>
		<c:if test="${not giaITCDVo.exibeFracaoIdealBensParticulares}">
			<tr>		
				<td class="SEFAZ-TD-RotuloSaida" >Data da GIA:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" ><c:out value="${giaITCDVo.dataCriacaoFormatada}"/></td>		
				<td class="SEFAZ-TD-RotuloSaida" >Lei:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" ><c:out value="${giaITCDVo.parametroLegislacaoVo.numeroLegislacao}"/></td>
			</tr>			
			<tr>
				<td class="SEFAZ-TD-RotuloSaida">Percentual multa:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.percentualMultaFormatado}"></c:out>%</td>
				<td class="SEFAZ-TD-RotuloSaida">Valor UPF:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorUPFFormatado}"/></td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" >Valor total bens declarados:&nbsp;</td>
				<td width="143" class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorTotalBensDeclaradosFormatado}"></c:out></td>
				<td class="SEFAZ-TD-RotuloSaida" >Valor para cálculo demonstrativo:&nbsp;</td>
				<td width="143" class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorBaseCalculoTributavelFormatado}"></c:out></td>
			</tr>		
		</c:if>
		-->
			<tr>		
				<td class="SEFAZ-TD-RotuloSaida" >Data da GIA:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" ><c:out value="${giaITCDVo.dataCriacaoFormatada}"/></td>		
				<td class="SEFAZ-TD-RotuloSaida" >Lei:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" ><c:out value="${giaITCDVo.parametroLegislacaoVo.numeroLegislacao}"/></td>
			</tr>			
			<tr>
				<td class="SEFAZ-TD-RotuloSaida">Percentual multa:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.percentualMultaFormatado}"></c:out>%</td>
				<td class="SEFAZ-TD-RotuloSaida">Valor UPF:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorUPFFormatado}"/></td>
			</tr>
			<tr>
            <td class="SEFAZ-TD-RotuloSaida" >Valor de Incidência ITCD:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorTotalBensDeclaradosFormatado}"></c:out></td>
				<c:choose>
                  <c:when test="${giaITCDVo.giaAvaliada}">
                     <td class="SEFAZ-TD-RotuloSaida">Valor total bens Avaliados:&nbsp;</td>
                  </c:when>
                  <c:otherwise>
                     <td class="SEFAZ-TD-RotuloSaida">Valor total bens arbitrados:&nbsp;</td>
                  </c:otherwise>
               </c:choose>              
            <td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.totalSomaValoresArbitradoAvaliacaoFormatado}"/></td>	
			</tr>	
         
		<tr>
         <td class="SEFAZ-TD-RotuloSaida">Valor total bens informados contribuinte:&nbsp;</td>
         <td class="SEFAZ-TD-CampoSaida" colspan="1">R$ <c:out value="${giaITCDVo.bemTributavelVo.valorTotalInformadoContribuinteFormatado}"></c:out></td>
			<td class="SEFAZ-TD-RotuloSaida" >Valor para cálculo demonstrativo:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.valorBaseCalculoTributavelFormatado}"></c:out></td>				
		</tr>
      <c:if test="${giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4'}">
         <c:if test="${giaITCDVo.calculoPercentualValoresArbitradoConcordado >= giaITCDVo.porcentagemAconsiderar}">
            <tr>         
               <td class="SEFAZ-TD-RotuloSaida">Valor total Bens Acordados:&nbsp;</td>
               <td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.totalSomaValoresConcordadoFormatado}"/></td>
               <td class="SEFAZ-TD-RotuloSaida" colspan="2">&nbsp;</td>		
            </tr>
         </c:if>
      </c:if>
	</table>
   
	<c:set var="exibeDemonstrativo" value="true" scope="request"/>
	<%
	if(JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_INVENTARIO_ARROLAMENTO))
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
					}">
		<c:if test="${exibeDemonstrativo}">
			<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
				<tr>
					<td colspan="2">
						<table cellspacing="2" cellpadding="2" border="1" width="100%" align="center">
							<c:if test="${empty giaITCDVo.beneficiarioVo.collVO}">
								<tr class="SEFAZ-TD-RotuloSaida">
									<td id="tituloMeeiroBeneficiario" colspan=""><div align="center">Dados do Cônjuge sobrevivente (Meeiro) beneficiário</div> </td>
								</tr>							
							</c:if>					
							<c:if test="${giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">
								<tr>
									<td width="115" class="SEFAZ-TD-RotuloSaida"><div align="center">Nome</div></td>
									<td width="115" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor do Quinhão / Recebido</div></td>
									<c:set var="tamanhoTd" value="2"></c:set>					
									<c:set var="qtdeColunasAliquota" value="0"	/>
									<c:if test="${giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">															
											 <c:if test="${not empty giaITCDVo.beneficiarioVo.collVO}">
												<c:forEach var="beneficiarioVo" items="${giaITCDVo.beneficiarioVo.collVO}" varStatus="contadorBeneficiarios">
															<c:if test="${contadorBeneficiarios.index == 0}">
																		<c:forEach var="giaITCDInventarioArrolamentoBeneficiarioAliquotaVo" items="${beneficiarioVo.giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.collVO}" varStatus="contador">
																				<c:set var="qtdeColunasAliquota" value="${contador.index +1}"/>																																					
																				<c:if test="${giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.percentualAliquota == 0}">
																							<td width="70" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor da Isenção</div></td>	
																				</c:if>
																				<c:if test="${giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.percentualAliquota != 0}">
																							<td width="70" class="SEFAZ-TD-RotuloSaida"><div align="center"><c:out value="${giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.percentualAliquotaFormatado}"></c:out> %</div></td>	
																				</c:if>
																		</c:forEach>
															</c:if>
												</c:forEach>																		 
											 </c:if>
											 <c:if test="${empty giaITCDVo.beneficiarioVo.collVO && giaITCDVo.meeiroBeneficiario.pessoaBeneficiaria.numrContribuinte != 0}">
												<c:forEach var="giaITCDInventarioArrolamentoBeneficiarioAliquotaVo" items="${giaITCDVo.meeiroBeneficiario.giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.collVO}" varStatus="contador">
														<c:set var="qtdeColunasAliquota" value="${contador.index +1}"/>																																					
														<c:if test="${giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.percentualAliquota == 0}">
																	<td width="70" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor da Isenção</div></td>	
														</c:if>
														<c:if test="${giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.percentualAliquota != 0}">
																	<td width="70" class="SEFAZ-TD-RotuloSaida"><div align="center"><c:out value="${giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.percentualAliquotaFormatado}"></c:out> %</div></td>	
														</c:if>
												</c:forEach>
											 </c:if>
											<c:set var="tamanhoTd" value="${tamanhoTd+qtdeColunasAliquota}"/>									
									</c:if>
									<td width="100" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor do ITCD</div></td>
									<td width="100" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor da Multa</div></td>
									<td width="100" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor a Recolher Devido</div></td>
									<c:set var="tamanhoTd" value="${tamanhoTd+3}"/>
								</tr>										
							</c:if>
							<c:if test="${not giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">
								<tr>
									<td width="140" class="SEFAZ-TD-RotuloSaida"><div align="center">Nome</div></td>
									<td width="125" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor do Quinhão / Recebido</div></td>
									<c:set var="qtdeColunasAliquota" value="0"	/>
									<td width="100" class="SEFAZ-TD-RotuloSaida"><div align="center">Aliquota</div></td>
									<td width="125" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor do ITCD</div></td>
									<td width="125" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor da Multa</div></td>
									<td width="125" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor a Recolher Devido</div></td>
									<c:set var="tamanhoTd" value="6"/>
								</tr>																
							</c:if>
							<c:if test="${empty giaITCDVo.beneficiarioVo.collVO}" >
								<script type="text/javascript" language="javascript">javascript: exibeTituloMeeiroBeneficiario('<c:out value="${tamanhoTd}"/>');</script>
							</c:if>
							<c:if test="${not empty giaITCDVo.beneficiarioVo.collVO}">
								<c:forEach var="beneficiarioVo" items="${giaITCDVo.beneficiarioVo.collVO}">
									<tr>
										<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${beneficiarioVo.pessoaBeneficiaria.nomeContribuinte}"></c:out></div></td>
										<td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${beneficiarioVo.valorRecebidoFormatado}"></c:out></div></td>								
										<c:if test="${giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">
											<c:set var="qtdeColunasAliquotaInseridas" value=""/>								
											<c:forEach var="giaITCDInventarioArrolamentoBeneficiarioAliquotaVo" items="${beneficiarioVo.giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.collVO}" varStatus="contador">
												<td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.valorBaseCalculoFormatado}"></c:out></div></td>
												<c:set var="qtdeColunasAliquotaInseridas" value="${qtdeColunasAliquotaInseridas + 1}"/>										
											</c:forEach>
											<c:forEach begin="${qtdeColunasAliquotaInseridas}" end="${qtdeColunasAliquota}" var="contador">
												<c:if test="${contador < qtdeColunasAliquota}">
													<td class="SEFAZ-TD-CampoSaida"><div align="right">0,00</div></td>
												</c:if>
											</c:forEach>									
										</c:if>								
										<c:if test="${not giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">
											<c:forEach var="giaITCDInventarioArrolamentoBeneficiarioAliquotaVo" items="${beneficiarioVo.giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.collVO}">
												<td class="SEFAZ-TD-CampoSaida"><div align="center"><c:out value="${giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.percentualAliquotaFormatado}"></c:out>%</div></td>
											</c:forEach>
										</c:if>								
										<td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${beneficiarioVo.valorITCDBeneficiarioFormatado}"/></div></td>
										<td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${beneficiarioVo.valorMultaRecolherFormatado}"/></div></td>
										<td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${beneficiarioVo.valorRecolherFormatado}"/></div></td>
									</tr>
								</c:forEach>						
							</c:if>
							<c:if test="${giaITCDVo.meeiroBeneficiario.pessoaBeneficiaria.numrContribuinte != 0}">
								<c:if test="${not empty giaITCDVo.beneficiarioVo.collVO}">
									<tr class="SEFAZ-TD-RotuloSaida">
										<td colspan="<c:out value='${tamanhoTd}'></c:out>"><div align="center">Dados do Cônjuge sobrevivente (Meeiro) beneficiário</div> </td>
									</tr>							
								</c:if>
								<tr>
									<td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${giaITCDVo.meeiroBeneficiario.pessoaBeneficiaria.nomeContribuinte}"></c:out></div></td>
									<td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${giaITCDVo.meeiroBeneficiario.valorRecebidoFormatado}"></c:out></div></td>								
									<c:if test="${giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">
										<c:set var="qtdeColunasAliquotaInseridas" value=""/>
										<c:forEach var="giaITCDInventarioArrolamentoBeneficiarioAliquotaVo" items="${giaITCDVo.meeiroBeneficiario.giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.collVO}" varStatus="contador">
											<td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.valorBaseCalculoFormatado}"></c:out></div></td>
											<c:set var="qtdeColunasAliquotaInseridas" value="${qtdeColunasAliquotaInseridas + 1}"/>
										</c:forEach>
										<c:forEach begin="${qtdeColunasAliquotaInseridas}" end="${qtdeColunasAliquota}" var="contador">
											<c:if test="${contador < qtdeColunasAliquota}">
												<td class="SEFAZ-TD-CampoSaida"><div align="right">0,00</div></td>
											</c:if>
										</c:forEach>									
									</c:if>								
									<c:if test="${not giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">
										<c:forEach var="giaITCDInventarioArrolamentoBeneficiarioAliquotaVo" items="${giaITCDVo.meeiroBeneficiario.giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.collVO}">
											<td class="SEFAZ-TD-CampoSaida"><div align="center"><c:out value="${giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.percentualAliquotaFormatado}"></c:out>%</div></td>
										</c:forEach>
									</c:if>								
									<td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${giaITCDVo.meeiroBeneficiario.valorITCDBeneficiarioFormatado}"/></div></td>
									<td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${giaITCDVo.meeiroBeneficiario.valorMultaRecolherFormatado}"/></div></td>
									<td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${giaITCDVo.meeiroBeneficiario.valorRecolherFormatado}"/></div></td>
								</tr>						
							</c:if>
							<tr align="left">
								<td class="SEFAZ-TD-RotuloSaida" align="left" >Total Geral:&nbsp;R$</td>
								<td class="SEFAZ-TD-RotuloSaida" ><div align="right"><c:out value="${giaITCDVo.valorBaseCalculoTributavelFormatado}"/></div></td>
								<td class="SEFAZ-TD-RotuloSaida" colspan="<c:out value='${tamanhoTd-2}'></c:out>">&nbsp;</td>
							</tr>
							<tr>
								<td class="SEFAZ-TD-RotuloSaida" colspan="<c:out value='${tamanhoTd}'></c:out>"><div align="center">Resumo Explicativo</div></td>
							</tr>
							<tr>							
								<td class="SEFAZ-TD-RotuloSaida" colspan="<c:out value='${tamanhoTd-2}'></c:out>">Valor ITCD:&nbsp;R$</td>
								<td class="SEFAZ-TD-RotuloSaida" colspan="2"><div align="right"><c:out value="${giaITCDVo.valorITCDFormatado}"/></div></td>								
							</tr>
							<tr>
								<td class="SEFAZ-TD-RotuloSaida" colspan="<c:out value='${tamanhoTd-2}'></c:out>">Multa:&nbsp;R$</td>
								<td class="SEFAZ-TD-RotuloSaida" colspan="2"><div align="right"><c:out value="${giaITCDVo.valorMultaFormatado}"/></div></td>
							</tr>
							<tr>
								<td class="SEFAZ-TD-RotuloSaida" colspan="<c:out value='${tamanhoTd-2}'></c:out>">Valor total à recolher:&nbsp;R$</td>
								<td class="SEFAZ-TD-RotuloSaida" colspan="2"><div align="right"><c:out value="${giaITCDVo.valorRecolhimentoFormatado}"/></div></td>
							</tr>												
							</table>
                     
                  <%if(JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_INVENTARIO_ARROLAMENTO))
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

	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr>
				<td>&nbsp;</td>
		</tr>
		<tr>
				<td>&nbsp;</td>
		</tr>		
		<tr> 
			<td align="center">
				<input type="button" value="<%=Form.TEXTO_BOTAO_ANTERIOR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_ANTERIOR_CLONE%>" onClick="solicitarAbaBeneficiarios();">
				<abaco:botaoCancelar/>
				
				<%if(JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_INVENTARIO_ARROLAMENTO))
				{%>
					<input type="button" value="<%=Form.TEXTO_BOTAO_PROXIMO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PROXIMO_CLONE%>" onClick="solicitarAbaAcompanhamento();">
				<%}
				else
				{%>
					<input type="button" value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR%>" onClick="validaFormulario()"/>
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
