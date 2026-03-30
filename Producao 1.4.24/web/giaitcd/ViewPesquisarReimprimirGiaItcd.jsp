<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
	/*
	* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
	* Arquivo : ViewPesquisarReimprimirGiaItcd.jsp
	* Criação : Dezembro de 2007 / Maxwell Rocha
	* Revisão : 
	* Log : 
	*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.giaitcd.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnProcessoReimpressao"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
	<head>
	<title><abaco:tituloSistema></abaco:tituloSistema></title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
		<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
		<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
		<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
		<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
		<script type="text/javascript" language="javascript">

		function obterArrayBotoes()
		{
			var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
			return new Array(botao1);
		}
		function consultarContribuinte()
		{
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_PESQUISAR_CONTRIBUINTE+"=1"%>';
			document.form.submit();
		}
		
		function habilitarCampo()
		{
			comboOpcao = document.form.<%=Form.CAMPO_SELECT_PROCESSO_IMPRESSAO%>.value;
			if(comboOpcao == "<%=DomnProcessoReimpressao.CODIGO_OCORRENCIA_FATO_GERADOR%>")
			{					
				document.getElementById('rotuloDescricao').innerHTML = 'Número da declaração:&nbsp;';
				document.getElementById('campoDescricao').innerHTML = '<abaco:campoApenasNumero maxlength="12" name="<%=Form.CAMPO_CODIGO_GIA%>" size="15" value="${giaITCDVo.numrDeclaracaoFatoGeradorFormatado}"/><font color="red">*</font>';
																																										
			}
			else if(comboOpcao == "<%=DomnProcessoReimpressao.CODIGO_ISENCAO_VALORES%>")
			{
				document.getElementById('rotuloDescricao').innerHTML = 'Número da declaração:&nbsp;';
				document.getElementById('campoDescricao').innerHTML = '<abaco:campoApenasNumero maxlength="12" name="<%=Form.CAMPO_CODIGO_GIA%>" size="15" value="${giaITCDVo.numrDeclaracaoIsencaoFormatado}"/><font color="red">*</font>';
			}																																								
			else
			{
				document.getElementById('rotuloDescricao').innerHTML = 'Número da GIA:&nbsp;';
				document.getElementById('campoDescricao').innerHTML = '<abaco:campoApenasNumero maxlength="12" name="<%=Form.CAMPO_CODIGO_GIA%>" size="15" value="${giaITCDVo.codigoFormatado}"/><font color="red">*</font>';
			}
		}
		function validaFormulario()
		{
			var campoSelectProcessoImpressao = document.form.<%=Form.CAMPO_SELECT_PROCESSO_IMPRESSAO%>;
			if(!verificaCamposW3c(campoSelectProcessoImpressao,<%="'"+MensagemErro.VALIDAR_REIMPRIMIR_TIPO_PROCESSO_NAO_INFORMADO+"'"%>))
			{
				return false;
			}
			if (campoSelectProcessoImpressao.options[campoSelectProcessoImpressao.selectedIndex].value == "<%=DomnProcessoReimpressao.CODIGO_DOACAO%>"
					|| campoSelectProcessoImpressao.options[campoSelectProcessoImpressao.selectedIndex].value == "<%=DomnProcessoReimpressao.CODIGO_INVENTARIO_ARROLAMENTO%>"
					|| campoSelectProcessoImpressao.options[campoSelectProcessoImpressao.selectedIndex].value == "<%=DomnProcessoReimpressao.CODIGO_SEPARACAO_DIVORCIO_PARTILHA%>")
			{
				//Validação para código GIA.
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_CODIGO_GIA%>,<%="'"+MensagemErro.VALIDAR_REIMPRIMIR_NUMERO_GIAITCD_NAO_INFORMADO+"'"%>))
				{
					return false;
				}
			}
			else
			{
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_CODIGO_GIA%>,<%="'"+MensagemErro.VALIDAR_REIMPRIMIR_NUMERO_DECLARACAO_NAO_INFORMADO+"'"%>))
				{
					return false;
				}
			}
			//Validação CPF/CNPJ
			<c:if test="${empty giaITCDVo.responsavelVo.numrDocumento}">
			alert(<%="'"+MensagemErro.VALIDAR_REIMPRIMIR_CONTRIBUINTE_NAO_INFORMADO+"'"%>);
			return false;
			</c:if>			
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR+"=1"%>';
			document.form.submit();
			return true;
		}
						
		</script>
		<jsp:include page="/util/ViewVerificaErro.jsp"></jsp:include>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');habilitarCampo();verificaErro();" >
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
		<form name="form" method="POST" action="">
		<table width="740" border="0" cellspacing="1" cellpadding="0" align="center" >
			<tr align="right"> 
				<td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
			</tr> 
			<tr class="SEFAZ-TR-Titulo" align="center"> 
					<td colspan="3">Filtro para Pesquisa</td>
			</tr>			
			<tr>
				<td>
					<table align="center" border="0" width="100%" cellspacing="0" cellpadding="0">		
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
									<c:set var="larguraRotuloPadrao" value="30%" scope="request"/>
									<c:set var="nomeTdLocalizarContribuinte" value="CPF / CNPJ" scope="request"/>
									<c:set var="ocultaLinkCadastrar" value="true" scope="request"/>					
									<c:set var="campoObrigatorio" value="true" scope="request"></c:set>
									<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteIncludeNova.jsp"/>
									<c:remove var="naoOculta" scope="request"/>
									<c:remove var="nomeTdLocalizarContribuinte" scope="request"/>
									<c:remove var="larguraRotuloPadrao" scope="request"/>
									<c:remove var="ocultaLinkCadastrar" scope="request"/>	
									<c:remove var="campoObrigatorio" scope="request"/>
							</td>
						</tr>							
						<c:if test="${not empty giaITCDVo.responsavelVo.numrDocumento}">
							<tr>
									<td  class="SEFAZ-TD-RotuloSaida" width="30%">Número do Documento:&nbsp;</td>
									<td class="SEFAZ-TD-CampoSaida"><div id="idNumrCpf"><c:out value="${giaITCDVo.responsavelVo.numrDocumento}"></c:out></div></td>
							</tr>
							<tr>
									<td  class="SEFAZ-TD-RotuloSaida" width="30%">Nome do Contribuinte:&nbsp;</td>
									<td  class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.nomeContribuinte}"></c:out></td>
							</tr>
						</c:if>
						<tr>
							<td class="SEFAZ-TD-RotuloEntrada"  width="30%" >Processo para Reimpressão:&nbsp;</td>							
							<td>
								<c:choose>
									<c:when test="${giaITCDVo.usuarioServidor}">
										<abaco:campoSelectDominio ajuda="Selecione o qual processo da GIA que deseja reimprimir." classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnProcessoReimpressao" name="<%=Form.CAMPO_SELECT_PROCESSO_IMPRESSAO%>" tabIndex="" onChange="habilitarCampo();" opcaoSelecionada="${codigoSelectProcessoImpressao}"/><font color="red">*</font>	
									</c:when>
									<c:otherwise>
										<abaco:campoSelectDominio ajuda="Selecione o qual processo da GIA que deseja reimprimir." classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnProcessoReimpressao" name="<%=Form.CAMPO_SELECT_PROCESSO_IMPRESSAO%>" tabIndex="" onChange="habilitarCampo();" opcaoSelecionada="${codigoSelectProcessoImpressao}" naoMostrar='<%=""+DomnProcessoReimpressao.CODIGO_ISENCAO_VALORES+","+DomnProcessoReimpressao.CODIGO_OCORRENCIA_FATO_GERADOR+""%>'/><font color="red">*</font>	
									</c:otherwise>
								</c:choose>
								
							</td>
						</tr>
						<tr>
							<td class="SEFAZ-TD-RotuloEntrada"><div id="rotuloDescricao"></div></td>
							 <td class="SEFAZ-TD-CampoEntrada"><div id="campoDescricao"></div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
  
	<br>
  <jsp:include page="/util/seguranca/ViewMostrarImagemCaracterModAberto.jsp"/>  
  
  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
    <tr> 
      <td colspan="3"> 
  	    <div align="center">
		 	<input name="<%=Form.BOTAO_PESQUISAR_CLONE%>"  type="button"  value="<%=Form.TEXTO_BOTAO_PESQUISAR%>"  class="SEFAZ-INPUT-Botao"  id="btnPesquisar"   onClick="validaFormulario();"></input>
			<abaco:botaoCancelar></abaco:botaoCancelar>
		</div>
		</td>
    </tr>
	 <tr>
		<td colspan="3"><font color="red"><b>* Campos Obrigatórios</b></font></td>
	 </tr>
  </table>
  <abaco:mensagemAguardeCarregando></abaco:mensagemAguardeCarregando>
</form>
<g:mostrarRodape></g:mostrarRodape>
</body>
</html>
