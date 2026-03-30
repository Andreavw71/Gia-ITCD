<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewMensagemDemonstrativoCalculo.jsp
* Criação : abril de 2008 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log :
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
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
	pageContext.setAttribute("QUITADO_MANUALMENTE", new Integer(DomnStatusGIAITCD.QUITADO_MANUALMENTE));
	pageContext.setAttribute("AVALIADO", new Integer(DomnStatusGIAITCD.AVALIADO));
	pageContext.setAttribute("ISENTO", new Integer(DomnStatusGIAITCD.ISENTO));
   pageContext.setAttribute("DISPENSADO_RECOLHIMENTO", new Integer(DomnStatusGIAITCD.DISPENSADO_RECOLHIMENTO));
   pageContext.setAttribute("ISENTO_IMPOSTO_ATE_UMA_UPF", new Integer(DomnStatusGIAITCD.ISENTO_IMPOSTO_ATE_1_UPF));
	pageContext.setAttribute("NAO_OCORRENCIA_DE_FATO_GERADOR", new Integer(DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR));
	pageContext.setAttribute("ALTERADO_SERVIDOR", new Integer(DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR));
	pageContext.setAttribute("NOTIFICADO_CIENTE", new Integer(DomnStatusGIAITCD.NOTIFICADO_CIENTE));
	pageContext.setAttribute("RETIFICADO_CIENTE", new Integer(DomnStatusGIAITCD.RETIFICADO_CIENTE));
	pageContext.setAttribute("EM_ELABORACAO", new Integer(DomnStatusGIAITCD.EM_ELABORACAO));
	//pageContext.setAttribute("NAO", new Integer(DomnSimNao.NAO));
	//pageContext.setAttribute("SIM", new Integer(DomnSimNao.SIM));
%>

<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">

			<c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == EM_ELABORACAO}">
					<tr>
						<td class="SEFAZ-TD-RotuloSaidaCenter">	GIA-ITCD ainda não foi avaliada.</td>
					</tr>
               <!--
                  Numero da O.S.:  	000000648/2016-31 
					<tr>
						<td class="SEFAZ-TD-RotuloSaidaCenter">	Os valores apresentados são calculados de acordo com os dados declarados pelo contribuinte.</td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloSaidaCenter"> E poderão sofrer alterações depois de passarem pela avaliação da Secretaria da Fazenda.</td>
					</tr>
               -->
					<tr><td class="SEFAZ-TD-RotuloSaidaCenter">&nbsp;</td></tr>
					<tr>
						<td class="SEFAZ-TD-RotuloSaidaCenter">	A GIA-ITCD-e está incompleta ou não foi confirmada. </td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloSaidaCenter">	Favor concluir seu preenchimento e confirmá-la para dar continuidade ao processo.</td>
					</tr>
			</c:if>
			<c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == PENDENTE_DE_PROTOCOLO or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == PROTOCOLADO or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == PROTOCOLO_AUTORIZADO_PELA_GIOR or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == ALTERADO_SERVIDOR or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == ALTERADO_PELA_GIOR}">
					
            <c:choose>
                  <c:when test="${giaITCDVo.situacaoProcessamento.textoCorrente == 'PROCESSADO COM ERRO'}">                  
                     <tr>
                        <td class="SEFAZ-TD-RotuloSaida" style="padding: 20px;">Erro ao Processar a GIA ITCD - </td>   
                        <td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.descricaoMensagemSituacaoErrro}"/></td> 
                     </tr>
                  </c:when>
               <c:otherwise>
                  <tr>
                     <td class="SEFAZ-TD-RotuloSaidaCenter">	GIA-ITCD ainda não foi avaliada.</td>
                  </tr>
               </c:otherwise>                            
           </c:choose>
           
			</c:if>         
			<c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == NOTIFICADO 
								or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == NOTIFICADO_CIENTE 
								or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == RETIFICADO
								or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == RETIFICADO_CIENTE
							}">              
                     
               <c:if test="${not giaITCDVo.giaITCDDarVo.darQuitado}"> 
                  <tr> 
                     <td colspan="4" align="center" class="SEFAZ-TD-RotuloSaidaCenter"> 
                           <input type="button" value="<%=FormITC.TEXTO_BOTAO_GERAR_DAR%>" class="SEFAZ-INPUT-Botao" name="<%=FormITC.BOTAO_IMPRIMIR_DAR%>" onClick="gerarDAR()"></input>
                     </td>
                  </tr>				
               </c:if>     
                 
				<c:if test="${giaITCDVo.giaITCDDarVo.darQuitado}">
            <c:if test="${ not giaITCDVo.statusVo.isPossuiStatusQuitadoeAvaliacaoExcluida}">
					<tr>
						<td class="SEFAZ-TD-RotuloSaidaCenter">	DAR referente a esta GIA-ITCD encontra-se quitado. --- </td>
					</tr>
               </c:if>
               <c:if test="${giaITCDVo.statusVo.isPossuiStatusQuitadoeAvaliacaoExcluida}">
               <tr>
                  <td class="SEFAZ-TD-RotuloSaidaCenter">
                     Favor dirigir-se a uma Agência Fazendária de Protocolo da GIA-ITCD para emissão do DAR e recolhimento do ITCD.
                  </td>
               </tr>
               </c:if>
				</c:if>
			</c:if>
			<c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == ISENTO}">
				<tr>
					<td colspan="2" class="SEFAZ-TD-RotuloSaidaCenter">Imprimir Declaração de Isenção por valores</td>
				</tr>
				<tr>
					<td colspan="2" align="center" class="SEFAZ-TD-RotuloSaidaCenter"> 	
							<input type="button" value="<%=FormITC.TEXTO_BOTAO_IMPRIMIR%>" class="SEFAZ-INPUT-Botao" name="<%=FormITC.BOTAO_IMPRIMIR%>" onClick="imprimirDeclaracaoIsencao()"></input>
					</td>
				</tr>
			</c:if>
         <c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == ISENTO_IMPOSTO_ATE_UMA_UPF}">
				<tr>
					<td colspan="2" class="SEFAZ-TD-RotuloSaidaCenter">Imprimir Declaração de Isenção</td>
				</tr>
				<tr>
					<td colspan="2" align="center" class="SEFAZ-TD-RotuloSaidaCenter"> 	
							<input type="button" value="<%=FormITC.TEXTO_BOTAO_IMPRIMIR%>" class="SEFAZ-INPUT-Botao" name="<%=FormITC.BOTAO_IMPRIMIR%>" onClick="imprimirDeclaracaoIsencao()"></input>
					</td>
				</tr>
			</c:if>
         
         <c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == DISPENSADO_RECOLHIMENTO}">
				<tr>
					<td colspan="2" class="SEFAZ-TD-RotuloSaidaCenter">Imprimir Declaração de Dispensa de Recolhimento</td>
				</tr>
				<tr>
					<td colspan="2" align="center" class="SEFAZ-TD-RotuloSaidaCenter"> 	
							<input type="button" value="<%=FormITC.TEXTO_BOTAO_IMPRIMIR%>" class="SEFAZ-INPUT-Botao" name="<%=FormITC.BOTAO_IMPRIMIR%>" onClick="imprimirDeclaracaoIsencao()"></input>
					</td>
				</tr>
			</c:if>
         
         <c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == DISPENSA_E_ISENCAO}">
				<tr>
					<td colspan="2" class="SEFAZ-TD-RotuloSaidaCenter">GIA ITCD com donatários/beneficiários nos casos de Isenção de valores e dispensa por valor inferior a 1 UPF. (temporário).</td>
				</tr>
			</c:if>
         
			<c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == AVALIADO}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaidaCenter">	GIA-ITCD em processo de Avaliação.</td>
				</tr>
			</c:if>
			<c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == PARCELADO}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaidaCenter">	Favor dirigir-se a Agência Fazendária de Protocolo da GIA-ITCD para emissão do DAR e recolhimento do ITCD.</td>
				</tr>
			</c:if>
			<c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == SEGUNDA_RETIFICACAO 
							or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == RATIFICADO
							or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == SEGUNDA_RETIFICACAO_CIENTE
							or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == RATIFICADO_CIENTE
						}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaidaCenter">	Para informações, favor procurar a Agência Fazendária de protocolo da GIA-ITCD.</td>
				</tr>								
			</c:if>
         
         <c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == PARA_INSCRICAO_EM_DIVIDA_ATIVA}"> 
                  <tr> 
                     <td colspan="4" align="center" class="SEFAZ-TD-RotuloSaidaCenter"> 
                           <input type="button" value="<%=FormITC.TEXTO_BOTAO_GERAR_DAR%>" class="SEFAZ-INPUT-Botao" name="<%=FormITC.BOTAO_IMPRIMIR_DAR%>" onClick="gerarDAR()"></input>
                     </td>
                  </tr>				
               </c:if>   
               
			<c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == QUITADO}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaidaCenter">	GIA-ITCD Quitada.</td>
				</tr>
			</c:if>
			<c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == QUITADO_MANUALMENTE}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaidaCenter">	GIA-ITCD Quitada Manualmente.</td>
				</tr>
			</c:if>  
			<c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == IMPUGNADO}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaidaCenter">	GIA-ITCD Impugnada, Aguardando parecer da reavaliação da Secretaria da fazenda. </td>
				</tr>				
			</c:if>
			<c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == REMETIDO_PARA_DIVIDA_ATIVA}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaidaCenter">	Para informações, favor procurar a Agência Fazendária de protocolo da GIA-ITCD.</td>
				</tr>
			</c:if>
			<c:if test="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente == NAO_OCORRENCIA_DE_FATO_GERADOR}">
				<tr>
					<td colspan="2" class="SEFAZ-TD-RotuloSaidaCenter">Imprimir Declaração de Não Ocorrência de Fato Gerador</td>
				</tr>
				<tr><td><br></br></td></tr>
				<tr>
					<td colspan="2" align="center" class="SEFAZ-TD-RotuloSaidaCenter">
							<input type="button" value="<%=FormITC.TEXTO_BOTAO_IMPRIMIR%>" class="SEFAZ-INPUT-Botao" name="<%=FormITC.BOTAO_IMPRIMIR%>" onClick="imprimirDeclaracaoFatoGerador()"></input>
					</td>
				</tr>
			</c:if>
	</table>
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">	
			<tr>
				<td class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
			</tr>	
	</table>