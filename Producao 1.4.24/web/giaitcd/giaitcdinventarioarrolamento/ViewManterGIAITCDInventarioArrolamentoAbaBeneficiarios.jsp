<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManterGIAITCDAbaBeneficiarios.jsp
* Criação : Janeiro de 2009 / Ricardo Vitor de Oliveira Moraes
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page import=" br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoContribuinte"%>
<%
	pageContext.setAttribute("SIM", new Integer(DomnSimNao.SIM));
	pageContext.setAttribute("NAO", new Integer(DomnSimNao.NAO));
%>
<script type="text/javascript" language="javascript">
	function consultarBeneficiario()
	{
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.PARAMETRO_SOLICITAR_PESQUISAR_BENEFICIARIOS+"=1"%>';
		document.form.submit();
	}
	function excluirBeneficiarios(indiceBeneficiario)
	{
		if(confirm('Deseja realmente excluir o Beneficiários?'))
		{
			//					desabilitarBotoes(obterArrayBotoes());
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_EXCLUIR_BENEFICIARIO+"="%>'+indiceBeneficiario;
			document.form.submit();
			return true;
		}
		else
		{
			return false
		}
	}	
	
	function solicitaExibirDadosMeeiro()
	{
		if(validaFormulario())
		{
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.SELECT_MEEIRO_BENEFICIARIO+"=1"%>';
			document.form.submit();
			return true;
		}
		return false;
	}
	
	function desabilitaSelectMeeiroBeneficiario()
	{
		document.getElementById('<%=FormITC.CAMPO_SELECT_MEEIRO_BENEFICIARIO%>').disabled = true;
	}
	
	function habilitaSelectMeeiroBeneficiario()
	{
		document.getElementById('<%=FormITC.CAMPO_SELECT_MEEIRO_BENEFICIARIO%>').disabled = false;		
	}
	
	//Valida Formulario caso o tipoProcesso da GIA for Inventario Arrolamento
	function validaFormulario()
	{
		return true;
	}

	function salvarBeneficiarios()
	{
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.PARAMETRO_SALVAR_BENEFICIARIOS+"=1"%>';
		document.form.submit();
		return true;
	}
	function adicionarBeneficiarios(percentualCadastrado)
	{
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_ADICIONAR_BENEFICIARIO+"=1"%>';
			document.form.submit();
			return true;		
	}
	function desabilitaBotaoInclui()
	{
		document.getElementById('idPesquisar').style.display = 'none';
		document.getElementById('idPesquisar1').style.display = 'none';
	}
	
</script>
<form method="POST" name="form" action="">
<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
	<tr class="SEFAZ-TR-Titulo">
		<td colspan="4">Bens</td>
	</tr>
	<tr>
		<td width="20%" class="SEFAZ-TD-RotuloSaida">Valor Bens:&nbsp;</td>
		<td width="25%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.valorTotalBensDeclaradosFormatado}"></c:out></td>
		<td width="30%" class="SEFAZ-TD-RotuloSaida">Base de Cálculo Tributável:&nbsp;</td>
		<td width="25%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.valorBaseCalculoTributavelFormatado}"></c:out></td>
	</tr>
</table>
<c:if test="${giaITCDVo.exibeMeeiroBeneficiario}">
<!--
	<c:if test="${giaITCDVo.regimeCasamentoComunhaoParcialBens}">
		<c:if test="${giaITCDVo.bemTributavelVo.existeBemParticular}">
			<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">	
				<tr>
					<td class="SEFAZ-TD-RotuloEntrada" width="370">Cônjuge Sobrevivente (Meeiro/Outro) Beneficiário:&nbsp;</td>
					<td class="SEFAZ-TD-CampoEntrada">
						<abaco:campoSelectDominio ajuda="Selecione se meeiro também é beneficiário." classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" name="<%=FormITC.CAMPO_SELECT_MEEIRO_BENEFICIARIO%>" idCampo="<%=FormITC.CAMPO_SELECT_MEEIRO_BENEFICIARIO%>" tabIndex="" onChange="solicitaExibirDadosMeeiro();" opcaoSelecionada="${giaITCDVo.flagMeeiroBeneficiario.valorCorrente}" mostrarSelecione="true" disabled="false"/>
					</td>
				</tr>			
			</table>	
			<c:if test="${not giaITCDVo.existeHerdeiros}">
				<script type="text/javascript" language="javascript">	
					desabilitaSelectMeeiroBeneficiario();		
				</script>
			</c:if>		
			<c:if test="${giaITCDVo.existeHerdeiros and not giaITCDVo.existeHerdeirosAscendentes}">
				<script type="text/javascript" language="javascript">	
					habilitaSelectMeeiroBeneficiario();		
				</script>
			</c:if>						
		</c:if>
	</c:if>
-->
	<c:if  test="${giaITCDVo.flagMeeiroBeneficiario.valorCorrente == SIM}">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center">
			<c:if test="${!giaITCDVo.regimeCasamentoComunhaoParcialBens}">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</c:if>
			<tr class="SEFAZ-TR-Titulo">
				<td colspan="4">Dados Cônjuge Sobrevivente (Meeiro/Outro) Beneficiário</td>
			</tr>
			<tr>
				<td width="20%" class="SEFAZ-TD-RotuloSaida">Nome:&nbsp;</td>
				<td width="30%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.meeiroBeneficiario.pessoaBeneficiaria.nomeContribuinte}"></c:out></td>
				<td width="20%" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
				<td width="30%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.meeiroBeneficiario.pessoaBeneficiaria.numrDocumento}"></c:out></td>
			</tr>
			<tr>
				<td width="25%" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
				<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.meeiroBeneficiario.pessoaBeneficiaria.endereco}"/></td>
				<td width="12%" class="SEFAZ-TD-RotuloSaida">Número:&nbsp;</td>
				<td colspan="2" width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.meeiroBeneficiario.pessoaBeneficiaria.enderecoNumero}"/></td>
			</tr>
			<tr>
				<td width="25%" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
				<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.meeiroBeneficiario.pessoaBeneficiaria.enderecoBairro}"/></td>
				<td width="12%" class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
				<td colspan="2" width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.meeiroBeneficiario.pessoaBeneficiaria.enderecoCEP}"/></td>
			</tr>
			<tr>
				<td width="25%" class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
				<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.meeiroBeneficiario.pessoaBeneficiaria.municipio}"/></td>
				<td width="12%" class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
				<td colspan="2" width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.meeiroBeneficiario.pessoaBeneficiaria.siglaUF}"/></td>
			</tr>
			<tr>
				<td align="left" class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
				<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.meeiroBeneficiario.pessoaBeneficiaria.numrDdd}"/></td>
				<td align="left" class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
				<td align="left" colspan="2" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.meeiroBeneficiario.pessoaBeneficiaria.numrTelefone}"/></td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloSaida">Valor Bens Recebidos Meação :&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.valorMeacaoFormatado}"/></td>
				<td class="SEFAZ-TD-RotuloSaida">Valor Bens Recebidos Herança:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.meeiroBeneficiario.valorRecebidoFormatado}"/></td>
			</tr>
		</table>
	</c:if>
</c:if>
<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">		
	<tr class="SEFAZ-TR-Titulo" id="idPesquisar">
		<td colspan="2">Beneficiários</td>
	</tr>
	<tr id="idPesquisar1">
		<td colspan="2">	
			<c:set var="naoOculta" value='false' scope="request"/>																				
			<c:set var="larguraRotuloPadrao" value="365" scope="request"/>
			<c:set var="nomeTdLocalizarContribuinte" value="CPF Beneficiário" scope="request"/>
			<c:set var="origemPesquisaContribuinte" scope="request"><%=DomnTipoContribuinte.BENEFICIARIOS%></c:set>
			<c:set var="giaInventarioArrolamento" value="true" scope="request"/>
			<c:set var="campoObrigatorio" value="true" scope="request"/>
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
			<c:remove var="giaInventarioArrolamento" scope="request"/>
			<c:remove var="campoObrigatorio" scope="request"/>
		</td>
	</tr>	
</table>
<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">		
	<tr>
		<td colspan="2">&nbsp;
		<c:if test="${not empty giaITCDVo.beneficiarioVo.pessoaBeneficiaria.numrDocumento}">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center">
				<tr class="SEFAZ-TR-Titulo">
					<td colspan="4">Dados Beneficiário</td>
				</tr>
				<tr>
					<td width="20%" class="SEFAZ-TD-RotuloSaida">Nome:&nbsp;</td>
					<td width="30%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.nomeContribuinte}"></c:out></td>
					<td width="20%" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
					<td width="30%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.numrDocumento}"></c:out></td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.endereco}"/></td>
					<td class="SEFAZ-TD-RotuloSaida">Número:&nbsp;</td>
					<td  class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.enderecoNumero}"/></td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">Complemento:&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.enderecoComplemento}"/></td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">Ponto de Referencia:&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.pontoReferencia}"/></td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.enderecoBairro}"/></td>
					<td class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.enderecoCEP}"/></td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.municipio}"/></td>
					<td class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
					<td  class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.siglaUF}"/></td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.numrDdd}"/></td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.numrTelefone}"/></td>
				</tr>
				<tr>
					<td class="SEFAZ-TD-RotuloSaida">E-mail:&nbsp;</td>
					<td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.email}"/></td>
				</tr>	
				<tr> 
					<td colspan="2" class="SEFAZ-TD-RotuloEntrada"><c:if test="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == DOACAO}">Percentual Atribuído &nbsp;</c:if></td>
					<td colspan="2" class="SEFAZ-TD-CampoEntrada">&nbsp;
						<c:if test="${alterar == null}">
						<input type="button" name="<%=Form.BOTAO_ADICIONAR%>" id="<%=Form.BOTAO_ADICIONAR%>" value="<%=Form.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" onClick="adicionarBeneficiarios(<c:out value="${percTot}"></c:out>);"></input>
						</c:if>
						<c:if test="${alterar != null}">
						<input type="button" name="<%=Form.BOTAO_ALTERAR%>" value="<%=Form.TEXTO_BOTAO_ALTERAR%>" class="SEFAZ-INPUT-Botao" onClick="adicionarBeneficiarios(<c:out value="${percTot}"></c:out>);"></input>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${giaITCDVo.beneficiarioVo.numeroBeneficiariosInseridos == giaITCDVo.numeroHerdeiros}">
			<script type="text/javascript">desabilitaBotaoInclui();</script>
		</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="2">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center">
					<c:forEach var="beneficiario" items="${giaITCDVo.beneficiarioVo.collVO}" varStatus="contador">				
					<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"></c:set>
					<c:if test="${contador.count % 2 != 0}">
					<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
					</c:if>
					<c:if test="${contador.index == 0}">
					<tr class="SEFAZ-TR-Titulo">
						<td colspan="7">Beneficiários Cadastrados</td>
					</tr>
					</c:if>
					<tr class="<c:out value="${linhaEstilo}"></c:out>">
						<td width="23%" class="SEFAZ-TD-RotuloSaida">Nome:&nbsp;</td>
						<td width="24%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.nomeContribuinte}"></c:out></td>
						<td width="21%" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
						<td width="16%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.numrDocumento}"></c:out></td>
						<td width="16%" class="SEFAZ-TD-RotuloSaida"><a href="javascript:void(excluirBeneficiarios(<c:out value="${contador.index}"></c:out>));">Excluir</a>&nbsp;&nbsp;</td>
					</tr>
					<tr class="<c:out value="${linhaEstilo}"></c:out>">
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
						<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.endereco}"/></td>
						<td width="12%" class="SEFAZ-TD-RotuloSaida">Número:&nbsp;</td>
						<td colspan="2" width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.enderecoNumero}"/></td>
					</tr>
					<tr class="<c:out value="${linhaEstilo}"></c:out>">
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
						<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.enderecoBairro}"/></td>
						<td width="12%" class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
						<td colspan="2" width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.enderecoCEP}"/></td>
					</tr>
					<tr class="<c:out value="${linhaEstilo}"></c:out>">
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
						<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.municipio}"/></td>
						<td width="12%" class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
						<td colspan="2" width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.siglaUF}"/></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.numrDdd}"/></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
						<td align="left" colspan="2" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.numrTelefone}"/></td>
					</tr>
					<tr class="<c:out value="${linhaEstilo}"></c:out>">
						<td class="SEFAZ-TD-RotuloSaida">Valor Bens Recebido:&nbsp;</td>
						<td class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.valorRecebidoFormatado}"/></td>
						<td colspan="3" class="SEFAZ-TD-RotuloSaida">&nbsp;</td>						
					</tr>
					<tr class="SEFAZ-TR-Titulo">
						<td colspan="5">&nbsp;</td>
					</tr>
			</c:forEach>
			</table>
		</td>
	</tr>
</table>
<br/>
<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
	<tr> 
		<td align="center">
		<input type="button" value="<%=Form.TEXTO_BOTAO_ANTERIOR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_ANTERIOR_CLONE%>" onClick="solicitarAbaBensTributaveis();">
			<abaco:botaoCancelar/>
			<input type="button" value="<%=FormITC.TEXTO_BOTAO_SALVAR%>" class="SEFAZ-INPUT-Botao" name="<%=FormITC.BOTAO_CONFIRMAR_CLONE%>" id="btnConfirmar" onClick="javascript: salvarBeneficiarios(<c:out value="${percentualTotal}"></c:out>);"></input>
			<input type="button" value="<%=Form.TEXTO_BOTAO_PROXIMO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PROXIMO_CLONE%>" onClick="solicitarAbaDemonstrativoDeCalculo();">
		</td>
	</tr>
</table>
<abaco:log/>
</form>
