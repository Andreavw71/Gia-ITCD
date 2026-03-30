<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManterGIAITCDDoacaoAbaDadosGerais.jsp
* Criação : Novembro de 2007 / Rogério Sanches de Oliveira 
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoContribuinte"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>

<%
	pageContext.setAttribute("isIncluir",""+JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_INCLUIR_GIAITCD_DOACAO));
%>

    <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
    <script type="text/javascript" language="javascript">
		
		function consultarDeclarante()
			{
				document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_PESQUISAR_DECLARANTE+"=1"%>';
				document.form.submit();
			}
		function consultarProcurador()
		  {
				document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_PESQUISAR_PROCURADOR+"=1"%>';
				document.form.submit();
			}
		function salvarDadosGerais()
		{
			if(validaFormulario())
			{         
            verificarSelecaoNaturezaOperacao();
				document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SALVAR_DADOS_GERAIS+"=1"%>';
				document.form.submit();
			}
		}
		
		function excluirProcurador()
		{
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_EXCLUIR_PROCURADOR+"=1"%>';
			document.form.submit();
		}
		
      function setFocus()
	    {
         document.forms[0].elements[0].focus();
		 }
		 
		 function validaFracaoIdeal()
		 {
			var campo = document.form.<%=Form.CAMPO_FRACAO_IDEAL%>.value;
			campo = parseFloat(replaceAll(campo,",","."));
			if(campo <= 0)
			{
				alert('<%=MensagemErro.GIA_ITCD_DOACAO_VALIDAR_FRACAO_IDEAL_MENOR_ZERO%>');
				return false;
			}
			if(campo >100)
			{
				alert('<%=MensagemErro.GIA_ITCD_DOACAO_VALIDAR_FRACAO_IDEAL_MENOR_CEM%>');
				document.form.<%=Form.CAMPO_FRACAO_IDEAL%>.value == '';
				return false;
			}
			return true;
		 }
	  function validaFormulario()
      {
		<c:if test="${empty giaITCDVo.responsavelVo.numrDocumento}">
			alert(<%="'"+MensagemErro.VALIDAR_GIA_ITCD_VALIDA_CPF_DECLARANTE+"'"%>);
			return false;
		</c:if>
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_NATUREZA_OPERACAO%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_VALIDA_NATUREZA_OPERACAO+"'"%>))
		{
			return false;
		}
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_FRACAO_IDEAL%>,<%="'"+MensagemErro.GIA_ITCD_DOACAO_VALIDAR_FRACAO_IDEAL+"'"%>))
		{
			return false;
		}
		if(!validaFracaoIdeal())
		{
			return false;
		}
		<c:if test="${giaITCDVo.usuarioServidor}">
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_JUSTIFICATIVA_ALTERACAO%>, <%="'"+MensagemErro.VALIDAR_GIA_ITCD_CAMPO_JUSTIFICATIVA_ALTERACAO+"'"%>))
			{
				return false;
			}
		</c:if>
		return true;
		}
		
		
	function localizar(campo)
	{
		if(campo == 'cpf')
		{
			if(navigator.appName != 'Microsoft Internet Explorer')
				document.getElementById('tblInventariante').style.display = 'table';
			else
				document.getElementById('tblInventariante').style.display = 'block';
		}
		else if(campo == 'cpf_deCujus')
		{
			if(navigator.appName != 'Microsoft Internet Explorer')
				document.getElementById('tbldeCujus').style.display = 'table';
			else
				document.getElementById('tbldeCujus').style.display = 'block';
		}
		else if(campo == 'cpf_meeiro')
		{
			if(navigator.appName != 'Microsoft Internet Explorer')
				document.getElementById('tblMeeiro').style.display = 'table';
			else
				document.getElementById('tblMeeiro').style.display = 'block';
		}
		else if(campo == 'cpf_procuradorVo')
		{
			if(navigator.appName != 'Microsoft Internet Explorer')
				document.getElementById('tblProcurador').style.display = 'table';
			else
				document.getElementById('tblProcurador').style.display = 'block';
		}
	}
   
   function verificarSelecaoNaturezaOperacao(){
      var selectElement = document.getElementById('<%=Form.CAMPO_SELECT_NATUREZA_OPERACAO%>');
      var selectedValue = selectElement.value;
      var additionalRow = document.getElementById('additionalRow');
      var isNatOpSelecDoacaoReservaUsufruto = false;

       // Substitua 'valor_especifico' pelo valor que você quer verificar
     if (selectedValue === '23') {
         // Exibe a linha
         additionalRow.style.display = 'table-row';
         isNatOpSelecDoacaoReservaUsufruto = true
         
      } else {
         additionalRow.style.display = 'none';

      }
    }
   
</script>
<form method="POST" name="form"  action="#">
  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
  	<tr class="SEFAZ-TR-Titulo">
		<td colspan="2">Doador</td>
	</tr>   
  <!-- Com o codigo abaixo comentado permite ao servidor alterar o contribuite.-->

		<tr>
			<td colspan="2">	
				<c:choose>
					<c:when test="${empty giaITCDVo.responsavelVo.numrDocumento}">
						<c:set var="naoOculta" value='true' scope="request"/>													
					</c:when>
					<c:otherwise>
						<c:set var="naoOculta" value='false' scope="request"/>																				
					</c:otherwise>
				</c:choose>
				<c:set var="larguraRotuloPadrao" value="370" scope="request"/>
				<c:set var="nomeTdLocalizarContribuinte" value="CPF / CNPJ Doador Declarante" scope="request"/>
				<c:set var="origemPesquisaContribuinte" scope="request"><%=DomnTipoContribuinte.DECLARANTE%></c:set>
				<c:set var="campoObrigatorio" scope="request" value="true"></c:set>
				<c:choose>
					<c:when test="${not empty exceptionCadastro && giaITCDVo.origem == origemPesquisaContribuinte}">
						<c:set var="ocultaLinkCadastrar" value="false" scope="request"/>
					</c:when>
					<c:otherwise>
						<c:set var="ocultaLinkCadastrar" value="true" scope="request"/>					
					</c:otherwise>
				</c:choose>

				<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteIncludeNova.jsp"/>

				<c:remove var="naoOculta" scope="request"/>
				<c:remove var="nomeTdLocalizarContribuinte" scope="request"/>
				<c:remove var="larguraRotuloPadrao" scope="request"/>
				<c:remove var="origemPesquisaContribuinte" scope="request"/>
				<c:remove var="ocultaLinkCadastrar" scope="request"/>
				<c:remove var="campoObrigatorio" scope="request"/>
			</td>
		</tr>

		<tr>
			<td colspan="2">
			<c:if test="${not empty giaITCDVo.responsavelVo.numrDocumento}">
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center" id="tblInventariante" >             
             <c:if test="${giaITCDVo.codigo > 0}">
                  <tr>
                     <td align="left" class="SEFAZ-TD-RotuloSaida">&nbsp; </td>
                     <td align="left" class="SEFAZ-TD-CampoSaida">&nbsp; </td>
                     <td align="left" class="SEFAZ-TD-RotuloSaida">Nº da GIA:&nbsp;</td>
                     <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.codigo}"/></td>
                  </tr>
            </c:if>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Inventariante:&nbsp; </td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.nomeContribuinte}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDocumento}"></c:out></td>
					 </tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.endereco}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">N&uacute;mero:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoNumero}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Complemento:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoComplemento}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Refer&ecirc;ncia:&nbsp; </td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.pontoReferencia}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoBairro}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoCEP}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.municipio}"></c:out></td>						
						<td align="left" class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.siglaUF}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDdd}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrTelefone}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">E-mail:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.email}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida">&nbsp;</td>
					</tr>					
				</table>	
				</c:if>
			</td>
		</tr>

		<c:if test="${not empty giaITCDVo.procuradorVo.numrDocumento or not giaITCDVo.usuarioServidor}">
			<tr class="SEFAZ-TR-Titulo">
				<td colspan="2">Dados Procurador</td>
			</tr>				
		</c:if>
		<c:if test="${not giaITCDVo.usuarioServidor}">
			<tr>
				<td colspan="2">	
					<c:choose>
						<c:when test="${empty giaITCDVo.procuradorVo.numrDocumento}">
							<c:set var="naoOculta" value='true' scope="request"/>													
						</c:when>
						<c:otherwise>
							<c:set var="naoOculta" value='false' scope="request"/>																				
						</c:otherwise>
					</c:choose>
					<c:set var="nomeTdLocalizarContribuinte" value="CPF Procurador" scope="request"/>
					<c:set var="larguraRotuloPadrao" value="370" scope="request"/>	
					<c:set var="origemPesquisaContribuinte" scope="request"><%=DomnTipoContribuinte.PROCURADOR%></c:set>
					<c:choose>
						<c:when test="${not empty exceptionCadastro && giaITCDVo.origem == origemPesquisaContribuinte}">
							<c:set var="ocultaLinkCadastrar" value="false" scope="request"/>
						</c:when>
						<c:otherwise>
							<c:set var="ocultaLinkCadastrar" value="true" scope="request"/>					
						</c:otherwise>
					</c:choose>								
					<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteIncludeNova.jsp"/>
					<c:remove var="naoOculta" scope="request"/>
					<c:remove var="nomeTdLocalizarContribuinte" scope="request"/>
					<c:remove var="larguraRotuloPadrao" scope="request"/>
					<c:remove var="origemPesquisaContribuinte" scope="request"/>
					<c:remove var="ocultaLinkCadastrar" scope="request"/>
				</td>
			</tr>						
		</c:if>
		<tr>
			<td colspan="2">
			<c:if test="${not empty giaITCDVo.procuradorVo.numrDocumento}">
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center" id="tblProcurador"> 
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Nome:&nbsp;</td>
						<td colspan="2" valign="middle" class="SEFAZ-TD-CampoSaida">
							<c:out value="${giaITCDVo.procuradorVo.nomeContribuinte}"></c:out>					
						</td>
						<c:choose>
							<c:when test="${not giaITCDVo.usuarioServidor}">
								<td class="SEFAZ-TD-RotuloSaida"><div align="right"><a href="javascript: excluirProcurador();">Excluir</a></div></td>	
							</c:when>
							<c:otherwise>
								<td class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
							</c:otherwise>
						</c:choose>						
					</tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
						 <td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.numrDocumento}"></c:out></td>
					</tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
						 <td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.endereco}"></c:out></td>
						 <td width="12%" class="SEFAZ-TD-RotuloSaida">Número:&nbsp;</td>
						 <td width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoNumero}"></c:out></td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloSaida">Complemento:&nbsp;</td>
						 <td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoComplemento}"></c:out></td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloSaida">Ponto de Referência:&nbsp;</td>
						<td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.pontoReferencia}"></c:out></td>
					</tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
						<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoBairro}"></c:out></td>
						<td width="12%" class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
						<td width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoCEP}"></c:out></td>
					</tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
						 <td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.municipio}"></c:out></td>
						 <td width="12%" class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
						 <td width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.siglaUF}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.numrDdd}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.numrTelefone}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">E-mail:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.email}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
				</table>	
			</c:if>
			</td>
		</tr>

   <tr class="SEFAZ-TR-Titulo">
      <td colspan="2">Natureza e percentual de Doação</td>
   </tr>
   
	<tr>
			<td width="370" class="SEFAZ-TD-RotuloEntrada" >Natureza Operação:&nbsp;</td>
			<td width="370" colspan="2" class="SEFAZ-TD-ComboBox" >
				<select name="<%=Form.CAMPO_SELECT_NATUREZA_OPERACAO%>" id="<%=Form.CAMPO_SELECT_NATUREZA_OPERACAO%>" onchange="verificarSelecaoNaturezaOperacao()">
					<option value="" selected><%=Form.SELECIONE%></option>
					<c:forEach var="naturezaOperacaoVo" items="${giaITCDVo.naturezaOperacaoVo.collVO}">
					<c:if test="${naturezaOperacaoVo.codigo != giaITCDVo.naturezaOperacaoVo.codigo}">
					<option value="<c:out value="${naturezaOperacaoVo.codigo}"></c:out>"><c:out value="${naturezaOperacaoVo.descricaoNaturezaOperacao}"></c:out></option>
					</c:if>
					<c:if test="${naturezaOperacaoVo.codigo == giaITCDVo.naturezaOperacaoVo.codigo}">
					<option value="<c:out value="${naturezaOperacaoVo.codigo}"></c:out>" selected><c:out value="${naturezaOperacaoVo.descricaoNaturezaOperacao}"></c:out></option>
					</c:if>					
				</c:forEach>
				</select><font color="red">*</font>
	  </td>
	</tr>
	<tr> 
		<td class="SEFAZ-TD-RotuloEntrada">Percentual da Doação / Transmissão (%):&nbsp;</td>
		<td class="SEFAZ-TD-CampoEntrada">
			<abaco:campoMonetario 
				name="<%=Form.CAMPO_FRACAO_IDEAL%>" 
				quantidadeDigitosInteiros="3" 
				size="16" 
				value="${giaITCDVo.fracaoIdealFormatada}" 
				idCampo="<%=Form.CAMPO_FRACAO_IDEAL%>" 
				mostrarZero="false" 
				quantidadeCasasDecimais="${giaITCDVo.quantidadeCasasDecimais}">
			</abaco:campoMonetario><font color="red">*</font>
		</td>
	</tr>   
   
<tr id="additionalRow" style="display: none;">
    <td class="SEFAZ-TD-CampoEntrada" width="100%" colspan="2">
        <div class="SEFAZ-TR-Titulo" style="margin-bottom: 10px; width: 100%; margin-top: 8px">Base de Cálculo</div>
        <table width="100%">     
            <tr>
                <td>
                    <div style="margin-bottom: 15px;  margin-left: 200px;">
                        <input type="radio" 
                               name="<%=Form.CAMPO_CHECK_RECOLH_SOBRE_BASE_CALC%>" 
                               value="<%=70.0%>" 
                               <c:if test="${giaITCDVo.baseCalculoReduzida != 100}">checked="checked"</c:if>>
                               
                        70% - Sem encerramento da tributação com recolhimento na renúncia ou extinção, conforme o caso, nos termos do inciso I, § 2º, do Art. 11 do Decreto nº 2.125/2003.
                    </div>
                    
                    <div  style="margin-left: 200px;">
                        <input type="radio" 
                               name="<%=Form.CAMPO_CHECK_RECOLH_SOBRE_BASE_CALC%>" 
                               value="<%=100.0%>" 
                               <c:if test="${giaITCDVo.baseCalculoReduzida == 100}">checked="checked"</c:if>>
                        100% - Com encerramento da tributação, opcional, nos termos do inciso III, § 3º do Art. 28 - Decreto nº 2125/2003.
                    </div>
                </td>
            </tr>
        </table>
    </td>
</tr>
        
	<c:if test="${giaITCDVo.usuarioServidor}">
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>	
		<tr>
			<td class="SEFAZ-TD-RotuloEntrada">Justificativa da Alteração:&nbsp;</td>
			<td class="SEFAZ-TD-CampoEntrada">
				<textarea cols="50" rows="4" class="SEFAZ-INPUT-TEXT" id="<%=Form.CAMPO_JUSTIFICATIVA_ALTERACAO%>" name="<%=Form.CAMPO_JUSTIFICATIVA_ALTERACAO%>"><c:out value="${giaITCDVo.justificativaAlteracao}"/></textarea><font color="red">*</font>
			</td>	
		</tr>	
	</c:if>
  </table>
  
  <br>
  <c:if test="${isIncluir && giaITCDVo.codigo==0}">
	  <jsp:include page="/util/seguranca/ViewMostrarImagemCaracterModAberto.jsp"/>  
	</c:if>
	
  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr>
			<td align="center">
				<abaco:botaoCancelar></abaco:botaoCancelar>
				<input type="button" value="<%=Form.TEXTO_BOTAO_SALVAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR_CLONE%>" id="btnConfirmar" onClick="salvarDadosGerais();"></input>
				<input type="button" value="<%=Form.TEXTO_BOTAO_PROXIMO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PROXIMO_CLONE%>" onClick="solicitarAbaBensTributaveis();">
			</td>
		</tr>
		<tr>
			<td><font color="red"><b>* Campos Obrigatórios</b></font></td>
		</tr>
	</table>
	<abaco:log/>
</form>
<script type="text/javascript">
   verificarSelecaoNaturezaOperacao();
</script>