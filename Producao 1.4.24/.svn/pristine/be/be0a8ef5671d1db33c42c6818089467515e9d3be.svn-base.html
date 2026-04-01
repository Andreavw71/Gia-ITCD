<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewAvaliacaoListarBem.jsp
* Criação : Janeiro de 2008 / Elizabeth Barbosa Moreira
* Revisão : 
* Log : 
*/
--%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<%@page import="br.gov.mt.sefaz.itc.model.generico.avaliacao.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%@page import="br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento"%>
<%@page import="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem"%>
<%@page import="br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo"%>
<%
    pageContext.setAttribute("SEPARACAO_DIVORCIO_PARTILHA", new Integer(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA));
	 pageContext.setAttribute("INVENTARIO_ARROLAMENTO", new Integer(DomnTipoProcesso.INVENTARIO_ARROLAMENTO));
    pageContext.setAttribute("COMUNHAO_PARCIAL_DE_BENS", new Integer(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS));	 
    pageContext.setAttribute("SIM", new Integer(DomnSimNao.SIM));	 	
	 pageContext.setAttribute("OUTROS_BENS", new Integer(DomnTipoBem.OUTROS_BENS));
	 pageContext.setAttribute("MT", "MT");
	 
	 GIAITCDVo gia = (GIAITCDVo) request.getAttribute(Form.GIAITCD_VO);
	 if(gia instanceof GIAITCDInventarioArrolamentoVo)
	 {
		GIAITCDInventarioArrolamentoVo inventario = (GIAITCDInventarioArrolamentoVo) gia;
		pageContext.setAttribute("UF_ABERTURA",inventario.getUfAbertura().getSiglUf());
	 }
	 else
	 {
		pageContext.setAttribute("UF_ABERTURA","MT");
	 }	 
%>
	 
<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
	<tr>
		<td colspan="7" class="SEFAZ-TR-Titulo" align="center">Bens</td>
	</tr>
	<tr class="SEFAZ-TR-SubTituloEsq"> 
		<td>Tipo do Bem </td>
		<td>Descri&ccedil;&atilde;o do Bem </td>
		<td>Valor Declarado </td>	
      <td>Valor Arbitrado </td>      
		<td>Valor da avalia&ccedil;&atilde;o </td>
		<td>Data da avalia&ccedil;&atilde;o </td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<c:forEach var="bemTributavelVo" items="${giaITCDVo.bemTributavelVo.collVO}"  varStatus="indice">
		<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
		<c:if test="${indice.count % 2 != 0}">
			<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
		</c:if>
		<tr class="<c:out value="${linhaEstilo}"/>"> 
			<td>
				<div align="left">
					<c:out value="${bemTributavelVo.bemVo.classificacaoTipoBem.textoCorrente}"/>
				</div>
			</td>
			<td>
				<c:out value="${bemTributavelVo.bemVo.descricaoTipoBem}"/>
				<div align="left"></div>
			</td>
			<c:choose>
            <c:when test="${bemTributavelVo.concordaComValorArbitrado.textoCorrente == 'SIM'}">
                     <td ><c:out value="${bemTributavelVo.valorInformadoFormatado}"></c:out></td>
                     <td ><b><c:out value="${bemTributavelVo.valorMercadoInformadoFormatado}"></c:out></b></td>
            </c:when>
            <c:otherwise>
                  <td ><b><c:out value="${bemTributavelVo.valorInformadoFormatado}"></c:out></b></td>
                  <td ><c:out value="${bemTributavelVo.valorMercadoInformadoFormatado}"></c:out></td>
               </c:otherwise>
         </c:choose>		         
			<td>
				<c:out value="${bemTributavelVo.avaliacaoBemTributavelVo.valorAvaliacaoFormatado}"/>
				<div align="left"></div>
			</td>
			<td>
				<c:out value="${bemTributavelVo.avaliacaoBemTributavelVo.dataAvaliacaoFormatado}"/>
				<div align="left"></div>
			</td>
			<c:set var="exibeLinkIncluirAlterar" value="${bemTributavelVo.bemPassivelAvaliacao}" scope="request"/>
			<c:choose>
				<c:when test="${not empty consultarAvaliacao}">
					<c:choose>
						<c:when test="${exibeLinkIncluirAlterar}">
							<td><a href="javascript:detalharAvaliacaoBem(<c:out value="${indice.index}"/>);">detalhar</a></td>
						</c:when>
						<c:otherwise>
							<td><c:out value="${bemTributavelVo.motivoBemNaoPodeSerAvaliado}"/></td>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${empty bemTributavelVo.avaliacaoBemTributavelVo.dataAvaliacao && exibeLinkIncluirAlterar}">
							<td><a href="javascript:incluirAvaliacaoBem(<c:out value="${bemTributavelVo.codigo}"/>);">Incluir</a><font color="red">*</font></td>							
						</c:when>
						<c:otherwise>							
							<c:choose>
								<c:when test="${exibeLinkIncluirAlterar}">
									<td><a href="javascript:alterarAvaliacaoBem(<c:out value="${bemTributavelVo.codigo}"/>);">
                           <% if(request.getAttribute(Form.PARAMETRO_REABRIR_AVALIACAO_BEM) == null & request.getAttribute(Form.PARAMETRO_EXCLUIR_AVALIACAO_BEM) == null  ){%>
                                 Alterar
                           <%	} else {%>
                                 Detalhar
                            <% }%> 
                           
                           </a></td>
								</c:when>
								<c:otherwise>									
									<td><c:out value="${bemTributavelVo.motivoBemNaoPodeSerAvaliado}"/></td>
								</c:otherwise>
							</c:choose>
						</c:otherwise>									
					</c:choose>											
				</c:otherwise>
			</c:choose>
		</tr>
	</c:forEach>		
</table>


 <!-- INICIO-B01 - Campos Exibidos quando for excluir ou reabrir uma avaliacao-->
  <%
     if(request.getAttribute(Form.PARAMETRO_REABRIR_AVALIACAO_BEM) != null || request.getAttribute(Form.PARAMETRO_EXCLUIR_AVALIACAO_BEM) != null ){
   %>                  
      <div id="id-justificativa" style="display:block">
         </br>
         <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
            <tr>
               <td class="SEFAZ-TD-RotuloEntrada" width="370">Justificativa p/ Exclus&atilde;o ou Reabertura :&nbsp;</td>
               <td class="SEFAZ-TD-CampoEntrada">
                  <textarea cols="40" rows="4" class="SEFAZ-INPUT-TEXT" id="<%=Form.CAMPO_JUSTIFICATIVA_ALTERACAO%>" name="<%=Form.CAMPO_JUSTIFICATIVA_ALTERACAO%>"><c:out value="${giaITCDVo.statusVo.justificativaAlteracao}"/></textarea>
                  <font color="red">*</font>
               </td>
            </tr>
         </table>
      </div>
  <%
   }
  %>            

<!-- FIM-B01-->

<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
	<tr> 
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr> 
		<td colspan="2"> 
			<div align="center">
				<abaco:botaoVoltar nomeContadorSubmit="btnvoltar"/>
           
            <%
            if(request.getAttribute(Form.PARAMETRO_REABRIR_AVALIACAO_BEM) != null || request.getAttribute(Form.PARAMETRO_EXCLUIR_AVALIACAO_BEM) != null ){
            %>
            
                     <% if(request.getAttribute(Form.PARAMETRO_REABRIR_AVALIACAO_BEM) != null){ %>  
                           <input type="button" value="<%=Form.TEXTO_BOTAO_REABRIR_AVALIACAO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_REABRIR_AVALIACAO%>" id="btnReabrirAvaliacao" onclick="return reabrirAvaliacaoBem();"></input>
                     <% } %>
                     
                     <% if(request.getAttribute(Form.PARAMETRO_EXCLUIR_AVALIACAO_BEM) != null){ %>
                           <input type="button" value="<%=Form.TEXTO_BOTAO_EXCLUIR_AVALIACAO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_EXCLUIR_AVALIACAO%>" id="btnExcluirAvaliacao" onclick="return excluirAvaliacaoBem();"></input>     
                     <% } %>
            
            <%
            } else {
				%>
                     <%
                     if(request.getAttribute(Form.PARAMETRO_PESQUISAR_GIAITCD_POR_NUMERO) == null && request.getAttribute(Form.BOTAO_PESQUISAR_GIASITCD) == null && request.getAttribute(Form.CONSULTAR_AVALIACAO) == null  )
                     {
                     %>
                        <input type="button" value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR%>" id="btnConfirmar" onclick="return validaFormulario();"></input>             
                     <%
                     }
                     %>
       
           <%
           }
           %>
            
				<%if(request.getAttribute(Form.CONSULTAR_AVALIACAO) != null)
				{
				%>
					<abaco:botaoCancelarSemMensagem/>
				<% 
				} 
				else
				{
				%>
					<abaco:botaoCancelar/>
				<%
				}
				%>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="2"><c:if test="${empty consultarAvaliacao}"><font color="red"><b>* Campos Obrigat&oacute;rios</b></font></c:if></td>
	</tr>
</table>
