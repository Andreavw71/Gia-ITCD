<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManterGIAITCDDoacaoAbaDemonstrativoDeCalculo.jsp
* Criação : Novembro de 2007 / Rogério Sanches de Oliveira
* Revisão : Anderson Boehler Iglesias Araujo
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="sefaz.mt.arrecadacao.integracao.IntegracaoArrecadacao"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD"%>
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
	pageContext.setAttribute("NOTIFICADO_CIENTE", new Integer(DomnStatusGIAITCD.NOTIFICADO_CIENTE));
	pageContext.setAttribute("RETIFICADO_CIENTE", new Integer(DomnStatusGIAITCD.RETIFICADO_CIENTE));
	pageContext.setAttribute("EM_ELABORACAO", new Integer(DomnStatusGIAITCD.EM_ELABORACAO));
	pageContext.setAttribute("SIM", new Integer(DomnSimNao.SIM));
   
   pageContext.setAttribute("PROTOCOLO_MANUAL", new Integer(DomnTipoProtocolo.PROTOCOLO_MANUAL));
	pageContext.setAttribute("PROTOCOLO_AUTOMATICO", new Integer(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO));
%>
<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
<script type="text/javascript" language="javascript">
	function validaFormulario()
	{
      /*
      var campoTipoProtocoloSelecionado = document.getElementsByName( '<%=Form.CAMPO_TIPO_PROTOCOLO_SELECIONADO_CONTRIBUINTE%>' );
      var isTipoProtocoloSelecionado = false;
      for (i = 0; i < campoTipoProtocoloSelecionado.length; i++) {
          if (campoTipoProtocoloSelecionado[i].checked) {
             isTipoProtocoloSelecionado = true;
          }
      }
      if( campoTipoProtocoloSelecionado.length > 0 ){
         if(isTipoProtocoloSelecionado){
            document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=1"%>';
            document.form.submit();
         }
         else
         {
            alert('Por favor senhor contribuinte, selecione se está de acordo com o(s) Valor(es) Arbitrado(s) ? ');
            return false;
         }
      }
      else
      {
      */
          document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=1"%>';
          document.form.submit();
      
      return false;
	}
	function gerarDAR()
	{
		// existe uma definição que no ITCD todos os pdf's deverão ser abertos em popup
		document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.disabled = true;		
		document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.className = "SEFAZ-INPUT-Botao-Disabled";
      window.open('','darITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
      document.form.target = 'darITCD';
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_IMPRIMIR_DAR+"=1"%>';
		document.form.submit();
      document.form.target = '_self';
	}

	function imprimirDAR()
	{
		document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.disabled = true;
		document.form.<%=Form.BOTAO_IMPRIMIR_DAR%>.className = 'SEFAZ-INPUT-Botao-Disabled';
      window.open('','darITCD', 'resizable=yes,scrollbars=yes,toolbar=no,menubar=no,height=600,width=800');
      document.form.target = 'darITCD';
		document.form.action='<%=IntegracaoArrecadacao.EMISSAO_DAR_URL%>?<%=IntegracaoArrecadacao.EMISSAO_DAR_PARAMETRO%>='+'<c:out value="${giaITCDVo.giaITCDDarVo.darEmitido.numrDarSeqc}" />'+'&'+'<%=IntegracaoArrecadacao.EMISSAO_DAR_JANELA%>'+'=1';		
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
      
</script>
<form method="POST" name="form" action="">
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr class="SEFAZ-TR-Titulo">
			<td colspan="4">Demonstrativo de Cálculo</td>			
		<tr>
			<td class="SEFAZ-TD-RotuloSaida" width="240">Tipo de GIA:&nbsp;</td>     
			<td class="SEFAZ-TD-CampoSaida" width="130"><c:out value="${giaITCDVo.tipoGIA.textoCorrente}"></c:out></td>
			<td class="SEFAZ-TD-RotuloSaida" width="240">Data da GIA:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" width="130"><c:out value="${giaITCDVo.dataCriacaoFormatada}"></c:out></td>
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloSaida">Percentual Transmitido:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.fracaoIdealFormatada}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">Doação / Outros:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.tipoDoacao.textoCorrente}"/></td>
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloSaida">Percentual de Redução da base de cálculo:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.baseCalculoReduzidaFormatado}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">Lei:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.parametroLegislacaoVo.numeroLegislacao}"/>/<c:out value="${giaITCDVo.parametroLegislacaoVo.anoLegislacao}"/></td>
		</tr>		
		<tr>
         <td class="SEFAZ-TD-RotuloSaida">Valor para cálculo Tributável:&nbsp;</td>
         <td class="SEFAZ-TD-CampoSaida" colspan="1">R$ <c:out value="${giaITCDVo.valorCalculoDemonstrativoFormatado}"></c:out></td> 
         
         <c:choose>
               <c:when test="${!giaITCDVo.giaAvaliada}">
                  <c:if test="${giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4'}">
                     <td class="SEFAZ-TD-RotuloSaida">Valor total bens arbitrados:&nbsp;</td>
                     <td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.totalSomaValoresArbitradoAvaliacaoFormatado}"></c:out></td>
                  </c:if>
               </c:when>
               <c:otherwise>
                  <td class="SEFAZ-TD-RotuloSaida">Valor total bens informados contribuinte:&nbsp;</td>
                  <td class="SEFAZ-TD-CampoSaida" colspan="1">R$ <c:out value="${giaITCDVo.bemTributavelVo.valorTotalInformadoContribuinteFormatado}"></c:out></td>
               </c:otherwise>
         </c:choose>     
         
		</tr>
      
		<c:choose>
			<c:when test="${giaITCDVo.giaAvaliada}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">Valor total bens Avaliados:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.totalSomaValoresArbitradoAvaliacaoFormatado}"/></td>
					<td class="SEFAZ-TD-RotuloSaida">Valor da UPF:&nbsp;</td>
               <td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.valorUPFFormatado}"/></td>
				</tr>
			
			</c:when>
			<c:otherwise>
				<tr>
               <td class="SEFAZ-TD-RotuloSaida">Valor total bens informados contribuinte:&nbsp;</td>
               <td class="SEFAZ-TD-CampoSaida" colspan="1">R$ <c:out value="${giaITCDVo.bemTributavelVo.valorTotalInformadoContribuinteFormatado}"></c:out></td>
					<td class="SEFAZ-TD-RotuloSaida">Valor da UPF:&nbsp;</td>
               <td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.valorUPFFormatado}"/></td>             
				</tr>			
			</c:otherwise>         
		</c:choose>
      <c:if test="${giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4'}">
         <c:if test="${giaITCDVo.calculoPercentualValoresArbitradoConcordado >= giaITCDVo.porcentagemAconsiderar}">
            <tr>
               <td class="SEFAZ-TD-RotuloSaida">Valor total Bens Acordados:&nbsp;</td>
               <td class="SEFAZ-TD-CampoSaida">R$ <c:out value="${giaITCDVo.totalSomaValoresConcordadoFormatado}"/></td>
               <td class="SEFAZ-TD-RotuloSaida" colspan="2">&nbsp;&nbsp;</td>		
            </tr>		
      </c:if> 
         
      <c:if test="${exibirValorBaseCalculoDoacaoSucessiva && giaITCDVo.numeroVersaoGIAITCD.textoCorrente != 'GIA Versão 1.3' && giaITCDVo.somaValorRecebidoDoacaoSucessivaFormatado != '0,00'}">
             <tr>
               <td class="SEFAZ-TD-RotuloSaida">Valor da Base de cálculo Doação Sucessiva:&nbsp;</td>
               <td class="SEFAZ-TD-CampoSaida" colspan="1">R$ <c:out value="${giaITCDVo.somaValorRecebidoDoacaoSucessivaFormatado}"></c:out></td>
               <td class="SEFAZ-TD-RotuloSaida"></td>
               <td class="SEFAZ-TD-CampoSaida"></td>         
             </tr>	
      </c:if>
      
   </c:if>
      
</table>     
	<c:set var="exibeDemonstrativo" value="true" scope="request"/>
	<%
	if(JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_DOACAO))
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
                  or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == DISPENSADO_RECOLHIMENTO
                  or giaITCDVo.statusVo.statusGIAITCD.valorCorrente == ISENTO_IMPOSTO_ATE_1_UPF
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
                     <c:if test="${!giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">
                        <tr class="SEFAZ-TD-RotuloSaida">
                           <td width="26%" align="center">Nome</td>  
                           <td width="25%" align="center">Valor recebido</td>
                           <td width="15%" align="center">Alíquota</td>
                           <td width="34%" align="center">Valor à recolher devido</td>
                        </tr>
                        <c:forEach var="beneficiarioVo" items="${giaITCDVo.beneficiarioVo.collVO}">
                           <c:set var="valortotalPercentualRebecibo" value="${beneficiarioVo.percentualBemRecebido + valortotalPercentualRebecibo}"></c:set>
                           <tr>
                              <td class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiarioVo.pessoaBeneficiaria.nomeContribuinte}"></c:out></td>
                              <td class="SEFAZ-TD-CampoSaida"><div align="right">R$ <c:out value="${beneficiarioVo.valorRecebidoFormatado}"></c:out></div></td>
                              <td class="SEFAZ-TD-CampoSaida"><div align="center"><c:out value="${beneficiarioVo.percentualAliquotaFormatado}"></c:out>%</div></td>
                              <td class="SEFAZ-TD-CampoSaida"><div align="right">R$ <c:out value="${beneficiarioVo.valorRecolherFormatado}"></c:out></div></td>
                           </tr>
                        </c:forEach>
                     </c:if>
            
            
                     <c:if test="${giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">
								<tr>
									<td width="115" class="SEFAZ-TD-RotuloSaida"><div align="center">Nome</div></td>   
                           <c:choose>
                              <c:when test="${exibirValorBaseCalculoDoacaoSucessiva && giaITCDVo.numeroVersaoGIAITCD.textoCorrente != 'GIA Versão 1.3' && giaITCDVo.somaValorRecebidoDoacaoSucessivaFormatado != '0,00'}">
                                 <td width="115" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor Base de Cálculo Doação Sucessiva</div></td>  
                              </c:when>                              
                              <c:otherwise>
                                 <td width="115" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor recebido</div></td>  
                              </c:otherwise>
                           </c:choose>
									<c:set var="tamanhoTd" value="2"></c:set>	
                           <c:set var="beneficiarioVoComMaisAliquotas" value=""/>
                           
									<c:if test="${giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">															
                              <c:if test="${not empty giaITCDVo.beneficiarioVo.collVO}">          
                                 <c:forEach var="giaItcdDoacaoBeneficiarioAliquotaVo" items="${giaITCDVo.beneficiarioVo.giaItcdDoacaoBeneficiarioAliquotaVo.collVO}" varStatus="contador">  
                                     <c:if test="${contador.index lt qtdeColunasAliquota}">                                     
                                        <c:if test="${giaItcdDoacaoBeneficiarioAliquotaVo.percentualLegislacaoAliquota == 0}">
                                             <td width="70" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor da Isenção</div></td>	
                                        </c:if>                                          
                                        <c:if test="${giaItcdDoacaoBeneficiarioAliquotaVo.percentualLegislacaoAliquota != 0}">
                                             <td width="70" class="SEFAZ-TD-RotuloSaida"><div align="center">Fração BC Sujeita à Alíquota de <c:out value="${giaItcdDoacaoBeneficiarioAliquotaVo.percentualLegislacaoAliquotaFormatado}"></c:out> %</div></td>	
                                        </c:if>                                         
                                     </c:if>
                                 </c:forEach> 
                              </c:if>
                              
                              <c:set var="tamanhoTd" value="${tamanhoTd+qtdeColunasAliquota}"/>									
									</c:if>
									<td width="100" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor do ITCD</div></td>  
                           
                           <c:set var="apresentaValorItcdAnterior" value="${2}"/>
                           
                           <c:if test="${exibirValorBaseCalculoDoacaoSucessiva && giaITCDVo.numeroVersaoGIAITCD.textoCorrente != 'GIA Versão 1.3' && giaITCDVo.somaValorRecebidoDoacaoSucessivaFormatado != '0,00'}">
                              <c:set var="apresentaValorItcdAnterior" value="${1}"/>
                              <td width="100" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor do ITCD Anterior</div></td>
                           </c:if>
                           
									<td width="100" class="SEFAZ-TD-RotuloSaida"><div align="center">Valor a Recolher Devido</div></td>
									<c:set var="tamanhoTd" value="${tamanhoTd+3}"/>
								</tr>										
							</c:if>
                     
                     
                     <c:if test="${not empty giaITCDVo.beneficiarioVo.collVO}">
								<c:forEach var="beneficiarioVo" items="${giaITCDVo.beneficiarioVo.collVO}">								
                            <c:if test="${giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">
                                 <tr>
                                    <td class="SEFAZ-TD-CampoSaida"><div align="left"><c:out value="${beneficiarioVo.pessoaBeneficiaria.nomeContribuinte}"></c:out></div></td>
                                    <td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${beneficiarioVo.valorRecebidoFormatado}"></c:out></div></td>
                                    <c:set var="qtdeColunasAliquotaInseridas" value="0"/>								
                                    <c:forEach var="giaItcdDoacaoBeneficiarioAliquotaVo" items="${beneficiarioVo.giaItcdDoacaoBeneficiarioAliquotaVo.collVO}" varStatus="contador">
                                       <td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${giaItcdDoacaoBeneficiarioAliquotaVo.valorBaseCalculoFormatado}"></c:out></div></td>
                                       <c:set var="qtdeColunasAliquotaInseridas" value="${qtdeColunasAliquotaInseridas + 1}"/>										
                                    </c:forEach>
                                    <c:if test="${qtdeColunasAliquotaInseridas < qtdeColunasAliquota}">
                                       <c:forEach begin="${qtdeColunasAliquotaInseridas + 1}" end="${qtdeColunasAliquota}" >
                                          <td class="SEFAZ-TD-CampoSaida"><div align="right">0,00</div></td>
                                    </c:forEach>
                                    </c:if>                                      
                                    <c:choose>                                    
                                       <c:when test="${empty beneficiarioVo.valorItcdBeneficiario}">
                                          <td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${giaITCDVo.valorITCDFormatado}"/></div></td>
                                       </c:when>
                                       <c:otherwise>
                                          <td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${beneficiarioVo.valorItcdBeneficiarioFormatado}"/></div></td>
                                       </c:otherwise>
                                    </c:choose> 
                                       <c:if test="${apresentaValorItcdAnterior == 1}">
                                          <td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${beneficiarioVo.valorItcdDoacaoSucessivaFormatado}"/></div></td>
                                       </c:if> 
                                    <td class="SEFAZ-TD-CampoSaida"><div align="right"><c:out value="${beneficiarioVo.valorRecolherFormatado}"/></div></td>
                                    </tr>
                              </c:if>								
										<c:if test="${not giaITCDVo.parametroLegislacaoVo.legislacaoCascata}">
											<c:forEach var="giaItcdDoacaoBeneficiarioAliquotaVo" items="${beneficiarioVo.giaItcdDoacaoBeneficiarioAliquotaVo.collVO}">
												<td class="SEFAZ-TD-CampoSaida"><div align="center"><c:out value="${giaItcdDoacaoBeneficiarioAliquotaVo.percentualAliquotaFormatado}"></c:out>%</div></td>
											</c:forEach>
										</c:if>
								</c:forEach>						
							</c:if>
            
							</table>
							<table cellspacing="2" cellpadding="2" border="1" width="100%" align="center">			
							<tr>
								<td width="66%" class="SEFAZ-TD-RotuloSaida">Valor do ITCD:&nbsp;</td>
								<td width="34%" class="SEFAZ-TD-CampoSaida"><div align="right">R$ 
							  <c:out value="${giaITCDVo.valorITCDFormatado}"/></div></td>
							</tr>		
							</table>
							<table cellspacing="2" cellpadding="2" border="1" width="100%" align="center">							
							<tr>
								<td width="66%" class="SEFAZ-TD-RotuloSaida">Valor total a recolher:&nbsp;</td>
								<td width="34%" class="SEFAZ-TD-CampoSaida"><div align="right">R$ <c:out value="${giaITCDVo.valorRecolhimentoFormatado}"/></div></td>
							</tr>
						</table>       
                  
                  <%if(JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_DOACAO))
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
                     <c:if test="${exibirDispensaRecolhimento}">
                        <table cellspacing="2" cellpadding="2" border="1" width="100%" align="center">
                           <tr class="SEFAZ-TR-Titulo">
                              <td colspan="4">Observações</td>			
                           </tr>
                        </table>                  
                        <table cellspacing="2" cellpadding="2" border="1" width="100%" align="left">  
                           <tr>                        
                              <td width="100%" align="left" class="SEFAZ-TD-RotuloSaida">- Os valores acima especificados referem-se exclusivamente à doação ora informada, sendo que os cálculos relativos à apuração dos valores de ITCD devidos nas doações anteriores constam expressos nas correspondentes GIAS.</td>
                           </tr>
                           <tr>                        
                              <td width="100%" align="left" class="SEFAZ-TD-RotuloSaida">- Nos termos do artigo 19, inciso II, da Lei 7.850/2002, combinado com o inciso II do § 2º do artigo 7º do Decreto nº. 2.125/2003, o fracionamento da Base de Cálculo nas faixas de isenção e de alíquotas aplicáveis é cumulativo com os valores transmitidos anteriormente.</td>
                           </tr>
                           <tr>                        
                              <td width="100%" align="left" class="SEFAZ-TD-RotuloSaida">- Quando, em razão da determinação do 19, § 6º, da Lei 7.850/2002, houver valor de ITCD devido e não recolhido em doação anterior, ele estará incluso no valor total do ITCD a recolher, acima apresentado, sempre que o somatório do valor do ITCD devido nas sucessivas doações superar o valor de 1 (uma) UPFMT.</td>
                           </tr>
                        </table>
                     </c:if>
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
	<br>
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr> 
			<td align="center">
			<input type="button" value="<%=Form.TEXTO_BOTAO_ANTERIOR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_ANTERIOR_CLONE%>" onClick="solicitarAbaDoacaoSucessiva();">
			<abaco:botaoCancelar/>
			<%if(JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_DOACAO))
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
