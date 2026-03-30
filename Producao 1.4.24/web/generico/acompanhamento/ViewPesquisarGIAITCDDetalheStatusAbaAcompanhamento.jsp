<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%
	pageContext.setAttribute("INVENTARIO", DomnTipoProcesso.INVENTARIO_ARROLAMENTO);
	pageContext.setAttribute("EM_ELABORACAO", DomnStatusGIAITCD.EM_ELABORACAO);
	pageContext.setAttribute("PENDENTE_PROTOCOLO", DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO);
	pageContext.setAttribute("INATIVADO_AUTOMATICAMENTE", DomnStatusGIAITCD.INATIVADO_AUTOMATICAMENTE);
	pageContext.setAttribute("PROTOCOLO_AUTORIZADO_GIOR", DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR);
	pageContext.setAttribute("PROTOCOLADO", DomnStatusGIAITCD.PROTOCOLADO);
	pageContext.setAttribute("AVALIADO", DomnStatusGIAITCD.AVALIADO);
	pageContext.setAttribute("ALTERADO_PELO_SERVIDOR", DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR);
	pageContext.setAttribute("NOTIFICADO", DomnStatusGIAITCD.NOTIFICADO);
	pageContext.setAttribute("NOTIFICADO_CIENTE", DomnStatusGIAITCD.NOTIFICADO_CIENTE);
	pageContext.setAttribute("PARCELADO", DomnStatusGIAITCD.PARCELADO);
	pageContext.setAttribute("QUITADO_MANUALMENTE", DomnStatusGIAITCD.QUITADO_MANUALMENTE);
	pageContext.setAttribute("PARA_INSCRICAO_EM_DIVIDA_ATIVA", DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA);
	pageContext.setAttribute("REMETIDO_DIVIDA_ATIVA", DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA);
	pageContext.setAttribute("RETIFICADO", DomnStatusGIAITCD.RETIFICADO);
	pageContext.setAttribute("RETIFICADO_CIENTE", DomnStatusGIAITCD.RETIFICADO_CIENTE);
	pageContext.setAttribute("IMPUGNADO", DomnStatusGIAITCD.IMPUGNADO);
	pageContext.setAttribute("SEGUNDA_RETIFICACAO", DomnStatusGIAITCD.SEGUNDA_RETIFICACAO);
	pageContext.setAttribute("SEGUNDA_RETIFICACAO_CIENTE", DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE);
	pageContext.setAttribute("RATIFICADO", DomnStatusGIAITCD.RATIFICADO);
	pageContext.setAttribute("RATIFICADO_CIENTE", DomnStatusGIAITCD.RATIFICADO_CIENTE);
	pageContext.setAttribute("ISENTO", DomnStatusGIAITCD.ISENTO);
	pageContext.setAttribute("NAO_OCORRENCIA_DE_FATO_GERADOR", DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR);
	pageContext.setAttribute("INATIVO", DomnStatusGIAITCD.INATIVO);
	pageContext.setAttribute("REATIVADO", DomnStatusGIAITCD.REATIVADO);
	pageContext.setAttribute("QUITADO", DomnStatusGIAITCD.QUITADO);
   pageContext.setAttribute("ENVIADO_CCF", DomnStatusGIAITCD.ENVIADO_CCF);
   pageContext.setAttribute("QUITADO_CCF", DomnStatusGIAITCD.QUITADO_CCF);
   pageContext.setAttribute("ALTERADO_PELA_GIOR", DomnStatusGIAITCD.ALTERADO_PELA_GIOR);
   pageContext.setAttribute("AVALIACAO_EXCLUIDA", DomnStatusGIAITCD.AVALIACAO_EXCLUIDA);
   pageContext.setAttribute("AVALIACAO_REABERTA", DomnStatusGIAITCD.AVALIACAO_REABERTA);
   pageContext.setAttribute("QUITADO_PELA_GIOR", DomnStatusGIAITCD.QUITADO_PELA_GIOR);
   pageContext.setAttribute("ISENTO_IMPOSTO_ATE_UMA_UPF", DomnStatusGIAITCD.ISENTO_IMPOSTO_ATE_1_UPF);
   
%>

<style type="text/css" >
.popup{
   display:none;
   position:fixed;
   left:0px;
   top:0px;
   padding:0px;
   width:100%;
   height:100%;
   border:1px solid #d0d0d0;
   overflow: hidden;
}

.pop-content{
   margin-top:200px;
   width:740px;
   height:350px;
   padding: 15px;
   background-color: white;
   border:1px solid #d0d0d0;
   overflow-y: scroll;
   overflow-x: hidden;
   box-shadow: 10px 10px 10px #888888 ;
   z-index:0;
}

.pop-transparent{
	position:absolute; top:0; left:0;
	width:100%; height:100%;
	background-color: silver;
	opacity: 0.4;
   -moz-opacity:0.4;
   filter: alpha(opacity=40)
   -webkit-opacity:0.4;
	z-index:-1;
}

.pop-link{
	border:1px solid #d0d0d0;
	border-top:none;
	background-color: white;
	width:60px;
	box-shadow: 5px 5px 5px #888888;
	border-radius: 0px 0px 10px 10px;
	color:blue;
}
.pop-link a:link, a:visited, a:active {
	text-decoration: none
}
.pop-link:active{ 
	background-color: #d0d0d0;
	border-radius: 0px 0px 12px 12px;
	border:1px solid #d0d0d3;
}
</style>

<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
	<c:choose>
		<c:when test="${status.statusGIAITCD.valorCorrente == EM_ELABORACAO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data da Criação da GIA-ITCD:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${giaITCDVo.dataCriacaoFormatada}"/></td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data de Vencimento para Protocolar:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${giaITCDVo.temporarioVo.prazoProtocolarFormatado}"/></td>
			</tr>			
		</c:when>
		<c:when test="${status.statusGIAITCD.valorCorrente == PENDENTE_PROTOCOLO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data da Criação da GIA-ITCD:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${giaITCDVo.dataCriacaoFormatada}"/></td>
			</tr>	
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data de Vencimento para Protocolar:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${giaITCDVo.temporarioVo.prazoProtocolarFormatado}"/></td>
			</tr>			
		</c:when>
		<c:when test="${status.statusGIAITCD.valorCorrente == INATIVADO_AUTOMATICAMENTE}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data de Vencimento para Protocolar:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${giaITCDVo.temporarioVo.prazoProtocolarFormatado}"/></td>
			</tr>	
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Usuário:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370">Serviço Automático</td>
			</tr>	
		</c:when>		
		<c:when test="${status.statusGIAITCD.valorCorrente == PROTOCOLO_AUTORIZADO_GIOR}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Agenfa de Protocolo:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${giaITCDVo.agenciaProtocolo.dadosUnidadeFormatado}"/></td>
			</tr>	
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data de Permissão:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataPermissaoFormatada}"/></td>
			</tr>	
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Observação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.observacao}"/></td>
			</tr>	
		</c:when>
		<c:when test="${status.statusGIAITCD.valorCorrente == PROTOCOLADO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Número do Protocolo:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.protocoloFormatado}"/></td>
			</tr>	
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data de Protocolo:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataProtocoloFormatada}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Agenfa de Protocolo:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${giaITCDVo.agenciaProtocolo.dadosUnidadeFormatado}"/></td>
			</tr>	
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>
      
      
		<c:when test="${status.statusGIAITCD.valorCorrente == AVALIADO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="350">Data da Avaliação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="350"><c:out value="${status.dataCadastroAvaliacaoFormatada}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="350">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="350"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
            
         
            
            <td align="center" width="40" style="display:none" >
            <a href="#" onclick="document.getElementById('popup<c:out value="${status.codigo}"/>').style.display='block';">
                     <img src="/imagens/view_details-26.png" width="14" height="14"/>
                     </a>
                     <div id="popup<c:out value="${status.codigo}"/>" class="popup" style="display:none;" >
                        <div class="pop-transparent"></div>
                        <center>
                           <div id="pop-content" class="pop-content">                          
                              <table cellspacing="1" cellpadding="0" border="0" width="740" align="center" style="margin-bottom: 15px;" >
                                    <tr class="SEFAZ-TR-SubTitulo">
                                       <td align="center">Operação</td>
                                       <td align="center">Atributo</td>
                                       <td align="center">Propriedade</td>
                                       <td align="center">Valor Anterior</td>
                                       <td align="center">Valor Atual</td>
                                    </tr>
                                    <c:forEach items="${status.logITCDVo.historicoLogVo.collVO}" var="logTemp" varStatus="contador">
                                       <abaco:linhaTabela>
                                          <td align="center"><c:out value="${logTemp.domnTipoOperacao.textoCorrente}"/></td>
                                          <td align="center"><c:out value="${logTemp.nomeAmigavel}"/></td>
                                          <td  colspan="3" >
                                          <table cellspacing="1" cellpadding="0" border="0" width="450" align="center">
                                             <c:forEach items="${logTemp.itemLog.collVO}" var="item" varStatus="contador">
                                                <tr >
                                                   <td align="left" style=" width:150px;" ><c:out value="${item.nomeAmigavel}"/></td>
                                                   <td align="center" style=" width:150px;" ><c:out value="${item.valorAnterior}"/></td>
                                                   <td align="right" style=" width:150px;" ><c:out value="${item.valorAtual}"/></td>
                                                </tr>
                                             </c:forEach>
                                          </table>
                                          </td>
                                       </abaco:linhaTabela>
                                    </c:forEach>
                              </table>                      
                           </div>
                           <div id="pop-link" class="pop-link" >
                              <a href="#" onclick="document.getElementById('popup<c:out value="${status.codigo}"/>').style.display='none';">Fechar</a>
                           </div>
                        </center>
                     </div>
         </td>
            
			</tr>	
			<c:if test="${status.servidorSefazResponsavelAlteracao.numrMatricula != 0}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaida" width="350">Data da Alteração da Avaliação:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida" width="350"><c:out value="${status.dataAtualizacaoFormatada}"/></td>
				</tr>				
				<tr>
					<td class="SEFAZ-TD-RotuloSaida" width="350">Matrícula / Nome do Servidor da Alteração:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida" width="350"><c:out value="${status.servidorSefazResponsavelAlteracao.dadosServidorFormatado}"/></td>
				</tr>	
			</c:if>
		</c:when>
      
      
		<c:when test="${status.statusGIAITCD.valorCorrente == ALTERADO_PELO_SERVIDOR}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="350">Justificativa da Alteração:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida" width="350"><c:out value="${status.justificativaAlteracao}"/></td>
            <!--
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${giaITCDVo.justificativaAlteracao}"/></td>
            -->
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="350">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="350">
					<c:choose>
						<c:when test="${not empty status.servidorSefazResponsavelAlteracao.numrMatricula}">
							<c:out value="${status.servidorSefazResponsavelAlteracao.dadosServidorFormatado}"/>           
						</c:when>
						<c:otherwise>
							<c:out value="${status.servidor.dadosServidorFormatado}"/>
						</c:otherwise>
					</c:choose>
				</td>
            <td align="center" width="40" >
            <a href="#" onclick="document.getElementById('popup<c:out value="${status.codigo}"/>').style.display='block';">
                     <img src="/imagens/view_details-26.png" width="14" height="14"/>
                     </a>
                     <div id="popup<c:out value="${status.codigo}"/>" class="popup" style="display:none;" >
                        <div class="pop-transparent"></div>
                        <center>
                           <div id="pop-content" class="pop-content">                          
                              <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                                    <tr class="SEFAZ-TR-SubTitulo">
                                       <td align="center">Operação</td>
                                       <td align="center">Atributo</td>
                                       <td align="center">propriedade</td>
                                       <td align="center">Valor Anterior</td>
                                       <td align="center">Valor Atual</td>
                                    </tr>
                                    <c:forEach items="${status.logITCDVo.historicoLogVo.collVO}" var="logTemp" varStatus="contador">
                                       <abaco:linhaTabela>
                                          <td align="center"><c:out value="${logTemp.domnTipoOperacao.textoCorrente}"/></td>
                                          <td align="center"><c:out value="${logTemp.nomeAmigavel}"/></td>
                                          <td  colspan="3" >
                                          <table cellspacing="1" cellpadding="0" border="0" width="450" align="center">
                                             <c:forEach items="${logTemp.itemLog.collVO}" var="item" varStatus="contador">
                                                <tr >
                                                   <td align="left" style=" width:150px;" ><c:out value="${item.nomeAmigavel}"/></td>
                                                   <td align="center" style=" width:150px;" ><c:out value="${item.valorAnterior}"/></td>
                                                   <td align="right" style=" width:150px;" ><c:out value="${item.valorAtual}"/></td>
                                                </tr>
                                             </c:forEach>
                                          </table>
                                          </td>
                                       </abaco:linhaTabela>
                                    </c:forEach>
                              </table>                      
                           </div>
                           <div id="pop-link" class="pop-link" >
                              <a href="#" onclick="document.getElementById('popup<c:out value="${status.codigo}"/>').style.display='none';">Fechar</a>
                           </div>
                        </center>
                     </div>
         </td>
			</tr>
		</c:when>
      <c:when test="${status.statusGIAITCD.valorCorrente == ALTERADO_PELA_GIOR}">
			<tr>
         
				<td class="SEFAZ-TD-RotuloSaida" width="350">Justificativa da Alteração:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida" width="350"><c:out value="${status.justificativaAlteracao}"/></td>
         <!--
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${giaITCDVo.justificativaAlteracao}"/></td>
         --> 
				
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="350">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="350">
					<c:choose>
						<c:when test="${not empty status.servidorSefazResponsavelAlteracao.numrMatricula}">
							<c:out value="${status.servidorSefazResponsavelAlteracao.dadosServidorFormatado}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${status.servidor.dadosServidorFormatado}"/>
						</c:otherwise>
					</c:choose>
				</td>
            <td align="center" width="40" >
            <a href="#" onclick="document.getElementById('popup<c:out value="${status.codigo}"/>').style.display='block';">
                     <img src="/imagens/view_details-26.png" width="14" height="14"/>
                     </a>
                     <div id="popup<c:out value="${status.codigo}"/>" class="popup" style="display:none;" >
                        <div class="pop-transparent"></div>
                        <center>
                           <div id="pop-content" class="pop-content">                          
                              <table cellspacing="1" cellpaddinew LogITCDBe(conn).rotinaProcessamentoLOG(giaITCDVo);ng="0" border="0" width="740" align="center" style="margin-bottom: 15px;" >
                                    <tr class="SEFAZ-TR-SubTitulo">
                                       <td align="center">Operação</td>
                                       <td align="center">Atributo</td>
                                       <td align="center">Propriedade</td>
                                       <td align="center">Valor Anterior</td>
                                       <td align="center">Valor Atual</td>
                                    </tr>
                                    <c:forEach items="${status.logITCDVo.historicoLogVo.collVO}" var="logTemp" varStatus="contador">
                                       <abaco:linhaTabela>
                                          <td align="center"><c:out value="${logTemp.domnTipoOperacao.textoCorrente}"/></td>
                                          <td align="center"><c:out value="${logTemp.nomeAmigavel}"/></td>
                                          <td  colspan="3" >
                                          <table cellspacing="1" cellpadding="0" border="0" width="450" align="center">
                                             <c:forEach items="${logTemp.itemLog.collVO}" var="item" varStatus="contador">
                                                <tr >
                                                   <td align="left" style=" width:150px;" ><c:out value="${item.nomeAmigavel}"/></td>
                                                   <td align="center" style=" width:150px;" ><c:out value="${item.valorAnterior}"/></td>
                                                   <td align="right" style=" width:150px;" ><c:out value="${item.valorAtual}"/></td>
                                                </tr>
                                             </c:forEach>
                                          </table>
                                          </td>
                                       </abaco:linhaTabela>
                                    </c:forEach>
                              </table>                      
                           </div>
                           <div id="pop-link" class="pop-link" >
                              <a href="#" onclick="document.getElementById('popup<c:out value="${status.codigo}"/>').style.display='none';">Fechar</a>
                           </div>
                        </center>
                     </div>
         </td>
			</tr>
		</c:when>
      
          
      <c:when test="${status.statusGIAITCD.valorCorrente == QUITADO_PELA_GIOR}"  >
			<tr>       
				<td class="SEFAZ-TD-RotuloSaida" width="370">Justificativa da Alteração:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.justificativaAlteracao}"/></td>				
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>
		</c:when>
      
      
       <c:when test="${status.statusGIAITCD.valorCorrente == AVALIACAO_REABERTA}">
			<tr>      
				<td class="SEFAZ-TD-RotuloSaida" width="370">Justificativa da Alteração:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.justificativaAlteracao}"/></td>
			</tr>				
         <tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>
      
      
      <c:when test="${status.statusGIAITCD.valorCorrente == AVALIACAO_EXCLUIDA}">
			<tr>      
				<td class="SEFAZ-TD-RotuloSaida" width="370">Justificativa da Alteração:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.justificativaAlteracao}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>		
		</c:when>
      
      
		<c:when test="${status.statusGIAITCD.valorCorrente == NOTIFICADO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data da Notificação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataNotificacaoFormatado}"/></td>
			</tr>				
		</c:when>
		<c:when test="${status.statusGIAITCD.valorCorrente == NOTIFICADO_CIENTE}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data de Ciência da Notificação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataCienciaNotificacaoFormatada}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>		
		<c:when test="${status.statusGIAITCD.valorCorrente == PARCELADO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data do Parcelamento:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataParcelamentoFormatado}"/></td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Número do Protocolo de Parcelamento:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.protocoloParcelamentoFormatado}"/></td>
			</tr>							
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
			<c:if test="${not empty status.justificativaReparcelamento}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaida" width="370">Justificativa Reparcelamento:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.justificativaReparcelamento}"/></td>
				</tr>						
			</c:if>
		</c:when>
		<c:when test="${status.statusGIAITCD.valorCorrente == QUITADO_MANUALMENTE}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data da Quitação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataQuitacaoFormatada}"/></td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Observação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.observacao}"/></td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Quantidade de Parcelas:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.numeroParcelas}"/></td>
			</tr>										
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
			<c:if test="${not empty status.justificativaReparcelamento}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaida" width="370">Justificativa Reparcelamento:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.justificativaReparcelamento}"/></td>
				</tr>						
			</c:if>
			<c:if test="${not empty status.giaITCDDarVo.collVO}">
				<tr>
					<td colspan="2">
						<table cellspacing="1" cellpadding="0" border="0" width="340" align="center">
							<tr class="SEFAZ-TR-SubTitulo">
								<td align="center">Número do DAR</td>
							</tr>
							<c:forEach items="${status.giaITCDDarVo.collVO}" var="dar" varStatus="contador">
								<abaco:linhaTabela>
									<td align="center"><c:out value="${dar.numeroDarFormatado}"/></td>
								</abaco:linhaTabela>
							</c:forEach>
						</table>
					</td>
				</tr>			
			</c:if>
		</c:when>
		<c:when test="${status.statusGIAITCD.valorCorrente == QUITADO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
			<tr>
				<td colspan="2">
					<table cellspacing="1" cellpadding="0" border="0" width="340" align="center">
						<tr class="SEFAZ-TR-SubTitulo">
							<td align="center">Número do DAR</td>
						</tr>
						<c:forEach items="${status.giaITCDDarVo.collVO}" var="dar" varStatus="contador">
							<abaco:linhaTabela>
								<td align="center"><c:out value="${dar.numeroDarFormatado}"/></td>
							</abaco:linhaTabela>
						</c:forEach>
					</table>
				</td>
			</tr>
		</c:when>			
		<c:when test="${status.statusGIAITCD.valorCorrente == PARA_INSCRICAO_EM_DIVIDA_ATIVA}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data de Envio para Inscrição em Divida Ativa:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataEnvioInscricaoDividaAtivaFormatada}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Justificativa de Envio para Inscrição em Divida Ativa:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.justificativaEnvioInscricaoDividaAtiva}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>
		<c:when test="${status.statusGIAITCD.valorCorrente == REMETIDO_DIVIDA_ATIVA}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data de Envio para Inscrição em Divida Ativa:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataEnvioDividaAtivaFormatado}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Justificativa de Envio para Inscrição em Divida Ativa:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.justificativaEnvioDividaAtiva}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>	
		<c:when test="${status.statusGIAITCD.valorCorrente == RETIFICADO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data da Retificação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataEmissaoRetificacaoFormatado}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>	
		<c:when test="${status.statusGIAITCD.valorCorrente == RETIFICADO_CIENTE}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data de Ciência da Retificação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataCienciaRetificacaoFormatado}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>	
		<c:when test="${status.statusGIAITCD.valorCorrente == IMPUGNADO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data da Impugnação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataImpugnacaoFormatado}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Número de Protocolo da Impugnação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.protocoloImpugnacaoFormatado}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Motivo da Impugnação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.motivoImpugnacao}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>	
		<c:when test="${status.statusGIAITCD.valorCorrente == SEGUNDA_RETIFICACAO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data da Segunda Retificação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataEmissaoSegundaRetificacaoFormatado}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Valor do Imposto:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.valorImpostoFormatado}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>
		<c:when test="${status.statusGIAITCD.valorCorrente == SEGUNDA_RETIFICACAO_CIENTE}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data de Ciência da Segunda Retificação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataCienciaSegundaRetificacaoFormatado}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>	
		<c:when test="${status.statusGIAITCD.valorCorrente == RATIFICADO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data da Ratificação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataRatificacaoFormatado}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Valor do Imposto:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.valorImpostoFormatado}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>
		<c:when test="${status.statusGIAITCD.valorCorrente == RATIFICADO_CIENTE}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data de Ciência da Ratificação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataCienciaRatificacaoFormatada}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>	
		<c:when test="${status.statusGIAITCD.valorCorrente == ISENTO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>
      <c:when test="${status.statusGIAITCD.valorCorrente == ISENTO_IMPOSTO_ATE_UMA_UPF}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>
		<c:when test="${status.statusGIAITCD.valorCorrente == NAO_OCORRENCIA_DE_FATO_GERADOR}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>
		<c:when test="${status.statusGIAITCD.valorCorrente == INATIVO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Número do Protocolo de Inativação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.protocoloDesistenciaFormatado}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data da Inativação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataDesistenciaFormatada}"/></td>
			</tr>			
			<c:if test="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == INVENTARIO}">
				<tr>
					<td class="SEFAZ-TD-RotuloSaida" width="370">Número da GIA Substituta:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.numeroGiaSubstituta}"/></td>
				</tr>			
			</c:if>
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Observação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.observacao}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>	
		<c:when test="${status.statusGIAITCD.valorCorrente == REATIVADO}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Justificativa da Reativação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.justificativaReativacao}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>	
		</c:when>

		<c:when test="${status.statusGIAITCD.valorCorrente == ENVIADO_CCF}">
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Número do Protocolo:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.protocoloCcfFormatado}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Data do Envio ao CCF:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataEnvioCCFFormatada}"/></td>
			</tr>			
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Observação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.observacao}"/></td>
			</tr>				
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>				
		</c:when>

		<c:when test="${status.statusGIAITCD.valorCorrente == QUITADO_CCF}">
         <tr>
            <td class="SEFAZ-TD-RotuloSaida" width="370">Data da Atualização:&nbsp;</td>
            <td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.dataAtualizacaoFormatada}"/></td>
         </tr>		      
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="370">Matrícula / Nome do Servidor:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="370"><c:out value="${status.servidor.dadosServidorFormatado}"/></td>
			</tr>		
		</c:when>
      
	</c:choose>
	<tr><td>&nbsp;</td></tr>
</table>