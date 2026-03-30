<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManterGIAITCDSeparacaoDivorcioAbaDadosGerais.jsp
* Criação : Dezembro de 2007 / João Batista Padilha e Silva
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoContribuinte"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>

<%
	pageContext.setAttribute("isIncluir",""+JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_INCLUIR_GIAITCD_SEPARACAO_DIVORCIO));
%>

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

function excluirProcurador()
{
	document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_EXCLUIR_PROCURADOR+"=1"%>';
	document.form.submit();
}

function salvarDadosGerais()
{
	if(validaFormulario())
	{
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SALVAR_DADOS_GERAIS+"=1"%>';
		document.form.submit();
	}
} 
function solicitarAbaBensTributaveisDadosGerais()
{
	if(validaFormulario())
	{
		solicitarAbaBensTributaveis();
	}
	return false;
}
function validaFormulario()
{
	<c:if test="${empty giaITCDVo.responsavelVo.numrDocumento}">
		alert(<%="'"+MensagemErro.DADOS_DECLARANTE_DEVE_SER_INFORMADO+"'"%>);
		return false;
	</c:if>
	if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_CASAMENTO%>,<%="'"+MensagemErro.VALIDAR_DATA_CASAMENTO_NAO_INFORMADA+"'"%>))
	{
		return false;
	}
	if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>,<%="'"+MensagemErro.VALIDAR_REGIME_CASAMENTO+"'"%>))
	{
		return false;
	}
	if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_SEPARACAO_DIVORCIO%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_DATA_SEPARACAO+"'"%>))
	{
		return false;
	}
	if(document.form.<%=Form.CAMPO_DATA_SEPARACAO_DIVORCIO%>.value == '')
	{
		alert(<%="'"+MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_DATA_SEPARACAO+"'"%>);
		return false;
	}
	if(document.form.<%=Form.CAMPO_NUMERO_PROCESSO_SEPARACAO_DIVORCIO%>.value != "")
	{
		if(document.form.<%=Form.CAMPO_NUMERO_PROCESSO_SEPARACAO_DIVORCIO%>.value <= 0)
		{
			alert(<%="'"+MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_VALIDA_NUMERO_PROCESSO_MAIOR_ZERO+"'"%>);
			return false;
		}
	}
	if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_NATUREZA_OPERACAO%>,<%="'"+MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_NATUREZA_OPERACAO+"'"%>))
	{
		return false;			 
	}
	<c:if test="${giaITCDVo.usuarioServidor}">
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_JUSTIFICATIVA_ALTERACAO%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_CAMPO_JUSTIFICATIVA_ALTERACAO+"'"%>))
		{
			return false;
		}
	</c:if>
	<c:if test="${empty giaITCDVo.responsavelVo.numrDocumento}">
		alert(<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_MEEIRO_BRANCO+"'"%>);
		return false;
	</c:if>
	return true;
}
function setFocus()
{
	document.forms[0].elements[0].focus();
}

</script>
<c:set var="giaSeparacaoDivorcioPartilha" value="true" scope="request"/>
<form method="POST" name="form"  action="#">
<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
	<tr class="SEFAZ-TR-Titulo">
		<td colspan="2">Dados do Declarante</td>
	</tr>

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
				<c:set var="larguraRotuloPadrao" value="50%" scope="request"/>
				<c:set var="nomeTdLocalizarContribuinte" value="CPF Inventariante" scope="request"/>
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
				<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center" id="tblDeclarante">
            <c:if test="${giaITCDVo.codigo > 0}">
                 <tr>
                     <td align="left" class="SEFAZ-TD-RotuloSaida">&nbsp; </td>
                     <td align="left" class="SEFAZ-TD-CampoSaida">&nbsp; </td>
                     <td align="left" class="SEFAZ-TD-RotuloSaida">Nº da GIA:&nbsp;</td>
                     <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.codigo}"/></td>
                  </tr>
            </c:if>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Nome:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.nomeContribuinte}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDocumento}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.endereco}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Número:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoNumero}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Complemento:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoComplemento}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Ponto de Referencia:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.pontoReferencia}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoBairro}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoCEP}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Munic&iacute;pio:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.municipio}"/></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.siglaUF}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDdd}"/></td>
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
	<tr class="SEFAZ-TR-Titulo">
		<td colspan="2">Outros Dados</td>
	</tr>
	<tr>
		<td class="SEFAZ-TD-RotuloEntrada">Data de Casamento:&nbsp;</td>
		<td class="SEFAZ-TD-CampoEntrada">
			<abaco:campoData name="<%=Form.CAMPO_DATA_CASAMENTO%>" size="10" value="${giaITCDVo.dataCasamentoFormatada}"/><font color="red">*</font>
		</td>
	</tr>	
	<tr>
		<td class="SEFAZ-TD-RotuloEntrada">Regime de Casamento:&nbsp;</td>
		<td class="SEFAZ-TD-ComboBox" >
			<abaco:campoSelectDominio 
				ajuda="" 
				classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento" 
				name="<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>" 
				tabIndex="" 
				mostrarSelecione="true" 
				idCampo="<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>" 
				opcaoSelecionada="${giaITCDVo.regimeCasamento.valorCorrente}">
			</abaco:campoSelectDominio><font color="red">*</font>	
		</td>
	</tr>
	<tr>
		<td class="SEFAZ-TD-RotuloEntrada">Número do Processo:&nbsp;</td>
		<td class="SEFAZ-TD-CampoEntrada"><abaco:campoApenasNumero maxlength="10"  name="<%=Form.CAMPO_NUMERO_PROCESSO_SEPARACAO_DIVORCIO%>" size="10" value="${giaITCDVo.numeroProcessoFormatado}"></abaco:campoApenasNumero></td>
	</tr>
	<tr>
		<td class="SEFAZ-TD-RotuloEntrada">Data da Separação/Divorcio/Partilha:&nbsp;</td>
		<td class="SEFAZ-TD-CampoEntrada">
			<input  type="text" name="<%=Form.CAMPO_DATA_SEPARACAO_DIVORCIO%>" class="SEFAZ-INPUT-Text"  size="12" maxlength="10" value="<c:out value="${giaITCDVo.dataSeparacaoFormatada}"></c:out>" onBlur="formataDataW3c(this)"></input><font color="red">*</font>
		</td>
	</tr>
	<tr>
		<td class="SEFAZ-TD-RotuloEntrada">Natureza da Operação:&nbsp;</td>
		<td class="SEFAZ-TD-ComboBox" >
			<select name="<%=Form.CAMPO_SELECT_NATUREZA_OPERACAO%>" id="<%=Form.CAMPO_SELECT_NATUREZA_OPERACAO%>">
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
	<c:if test="${not giaITCDVo.usuarioServidor}">
			<tr class="SEFAZ-TR-Titulo">
				<td colspan="2">Procurador</td>
			</tr>
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
					<c:set var="larguraRotuloPadrao" value="50%" scope="request"/>	
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
		<td colspan="3">
			<c:if test="${not empty giaITCDVo.procuradorVo.numrDocumento}">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center" id="tblProcurador">
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Nome:&nbsp;</td>
						<td colspan="2" valign="middle" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.nomeContribuinte}"></c:out></td>
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
						<td width="12%" class="SEFAZ-TD-RotuloSaida">N&uacute;mero do Logradouro:&nbsp;</td>
						<td width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoNumero}"></c:out></td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloSaida">Complemento:&nbsp;</td>
						<td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoComplemento}"></c:out></td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloSaida">Ponto de Referencia:&nbsp;</td>
						<td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.pontoReferencia}"></c:out></td>
					</tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
						<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoBairro}"></c:out></td>
						<td width="12%" class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
						<td width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoCEP}"></c:out></td>
					</tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Munic&iacute;pio:&nbsp;</td>
						<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.municipio}"></c:out></td>
						<td width="12%" class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
						<td width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.siglaUF}"></c:out></td>
					</tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
						<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.numrDdd}"></c:out></td>
						<td width="12%" class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
						<td width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.numrTelefone}"></c:out></td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloSaida">E-mail:&nbsp;</td>
						<td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.email}"></c:out></td>
					</tr>
				</table>
			</c:if>
		</td>
	</tr>
	<c:if test="${giaITCDVo.usuarioServidor}">
		<tr>
			<td colspan="3">&nbsp;</td>
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

<div align="center">
	<abaco:botaoCancelar></abaco:botaoCancelar>
	<input type="button" value="<%=Form.TEXTO_BOTAO_SALVAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR_CLONE%>" id="btnConfirmar" onClick="salvarDadosGerais();" />
	<input type="button" value="<%=Form.TEXTO_BOTAO_PROXIMO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PROXIMO_CLONE%>" onClick="solicitarAbaBensTributaveisDadosGerais();">
</div>
<div align="left">
	<font color="red"><b>* Campos Obrigatórios</b></font>
</div>
<abaco:log/>
</form>
<c:remove var="giaSeparacaoDivorcioPartilha" scope="request"/>
