<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewImprimirGarantia.jsp
* Criação : Janeiro de 2008 / Elizabeth Barbosa Moreira
* Revisão : 
* Log : 
*/
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<%@ page import="br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD"%>

<%pageContext.setAttribute("giaitcdVo", request.getAttribute(Form.GIAITCD_VO));%>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<title><abaco:tituloSistema/></title>
	<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
	<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>


	<script type="text/javascript" language="javascript">
	
	function validaFormulario()
	{
		 var valorSelect = document.form.<%=Form.CAMPO_SELECT_STATUS%>.value;
		 if(valorSelect == '' || valorSelect == '0')
		 {
			  alert('Favor selecionar um status.');
			  return false;
		 }
		 if(!validaCampos(valorSelect))
		 {
			return false;
		 }
		 desabilitarBotoes(obterArrayBotoes());
		 document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=1"%>';
		 document.form.submit();
		 return true;
	}
	
	function validaCampos(valorSelect)
	{
		if(valorSelect == '<%=DomnStatusGIAITCD.PROTOCOLADO%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_NUMERO_PROTOCOLO%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_PROTOCOLO%>'))
			{
				return false;
			}
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_PROTOCOLO%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_PROTOCOLO%>'))
			{
				return false;
			}
		}
		else if(valorSelect == '<%=DomnStatusGIAITCD.NOTIFICADO_CIENTE%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_CIENCIA_NOTIFICACAO%>, '<%=MensagemErro.DATA_CIENCIA_NOTIFICACAO_DEVE_SER_INFORMADA%>'))
			{
				return false;
			}			
		}
		else if(valorSelect == '<%=DomnStatusGIAITCD.PARCELADO%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_NUMERO_PROTOCOLO_PARCELAMENTO%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_PROTOCOLO_PARCELAMENTO%>'))
			{
				return false;
			}
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_PARCELAMENTO%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_PARCELAMENTO%>'))
			{
				return false;
			}		
			if(document.getElementById('<%=Form.TD_CAMPO+Form.CAMPO_JUSTIFICATIVA_RETORNO_PARCELAMENTO%>') != null)
			{
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_JUSTIFICATIVA_RETORNO_PARCELAMENTO%>, '<%=MensagemErro.JUSTIFICATIVA_REPARCELAMENTO_DEVE_SER_INFORMADO%>'))
				{
					return false;
				}			
			}			
		}
		else if(valorSelect == '<%=DomnStatusGIAITCD.QUITADO_MANUALMENTE%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_QUITACAO%>, '<%=MensagemErro.DATA_QUITACAO_DEVE_SER_INFORMADA%>'))
			{
				return false;
			}			
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_OBSERVACAO%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_OBSERVACAO%>'))
			{
				return false;
			}			
		}
		else if(valorSelect == '<%=DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_ENVIO_INSCRICAO_DIVIDA_ATIVA%>, '<%=MensagemErro.DATA_ENVIO_INSCRICAO_DIVIDA_ATIVA_DEVE_SER_INFORMADA%>'))
			{
				return false;
			}			
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_JUSTIFICATIVA_ENVIO_INSCRICAO_DIVIDA_ATIVA%>, '<%=MensagemErro.JUSTIFICATIVA_ENVIO_INSCRICAO_DIVIDA_ATIVA_DEVE_SER_INFORMADA%>'))
			{
				return false;
			}			
		}
		else if(valorSelect == '<%=DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_ENVIO_DIVIDA_ATIVA%>, '<%=MensagemErro.DATA_ENVIO_DIVIDA_ATIVA_DEVE_SER_INFORMADA%>'))
			{
				return false;
			}			
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_JUSTIFICATIVA_ENVIO_DIVIDA_ATIVA%>, '<%=MensagemErro.JUSTIFICATIVA_ENVIO_DIVIDA_ATIVA_DEVE_SER_INFORMADA%>'))
			{
				return false;
			}			
		}
		else if(valorSelect == '<%=DomnStatusGIAITCD.RETIFICADO_CIENTE%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_CIENCIA_RETIFICACAO%>, '<%=MensagemErro.DATA_CIENCIA_RETIFICACAO_DEVE_SER_INFORMADA%>'))
			{
				return false;
			}			
		}
		else if(valorSelect == '<%=DomnStatusGIAITCD.IMPUGNADO%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_IMPUGNACAO%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_IMPUGNADO%>'))
			{
				return false;
			}			
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_NUMERO_PROTOCOLO_IMPUGNACAO%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_PROTOCOLO_IMPUGNADO%>'))
			{
				return false;
			}			
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_MOTIVO_IMPUGNACAO%>, '<%=MensagemErro.MOTIVO_IMPUGNACAO_DEVE_SER_INFORMADO%>'))
			{
				return false;
			}
		}
		else if(valorSelect == '<%=DomnStatusGIAITCD.SEGUNDA_RETIFICACAO%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_SEGUNDA_RETIFICACAO%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_EMISSAO_SEGUNDA_RETIFICACAO%>'))
			{
				return false;
			}			
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_VALOR_IMPOSTO_SEGUNDA_RETIFICACAO%>, '<%=MensagemErro.VALOR_IMPOSTO_DEVE_SER_INFORMADO_E_MAIOR_ZERO%>'))
			{
				return false;
			}			
			
		}
		else if(valorSelect == '<%=DomnStatusGIAITCD.RATIFICADO%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_RATIFICACAO%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_EMISSAO_RATIFICACAO%>'))
			{
				return false;
			}			
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_VALOR_IMPOSTO_RATIFICACAO%>, '<%=MensagemErro.VALOR_IMPOSTO_DEVE_SER_INFORMADO_E_MAIOR_ZERO%>'))
			{
				return false;
			}						
		}
		else if(valorSelect == '<%=DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_CIENCIA_SEGUNDA_RETIFICACAO%>, '<%=MensagemErro.DATA_CIENCIA_SEGUNDA_RETIFICACAO_DEVE_SER_INFORMADA%>'))
			{
				return false;
			}			
		}
		else if(valorSelect == '<%=DomnStatusGIAITCD.RATIFICADO_CIENTE%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_CIENCIA_RATIFICACAO%>, '<%=MensagemErro.DATA_CIENCIA_RATIFICACAO_DEVE_SER_INFORMADA%>'))
			{
				return false;
			}			
		}	 
		else if(valorSelect == '<%=DomnStatusGIAITCD.ISENTO%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_OBSERVACAO_ISENTO%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_OBSERVACAO%>'))
			{
				return false;
			}	         
		}	      
		else if(valorSelect == '<%=DomnStatusGIAITCD.ENVIADO_CCF%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_NUMERO_PROTOCOLO_CCF%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_PROTOCOLO%>'))
			{
				return false;
			}
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_ENVIO_CCF%>, '<%=MensagemErro.FAVOR_INFORMAR_DATA_ENVIO_CCF%>'))
			{
				return false;
			}	
//			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_OBSERVACAO_ENVIADO_CCF%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_OBSERVACAO%>'))
//			{
//				return false;
//			}	         
		}
      else if(valorSelect == '<%=DomnStatusGIAITCD.QUITADO_PELA_GIOR%>')
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_JUSTIFICATIVA_ALTERACAO%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_CAMPO_JUSTIFICATIVA_ALTERACAO%>'))
			{
				return false;
			}			
		}
//		else if(valorSelect == '<%=DomnStatusGIAITCD.QUITADO_CCF%>') VALIDAR_GIA_ITCD_CAMPO_JUSTIFICATIVA_ALTERACAO
//		{
//			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_OBSERVACAO_QUITADO_CCF%>, '<%=MensagemErro.VALIDAR_GIA_ITCD_ALTERAR_STATUS_OBSERVACAO%>'))
//			{
//				return false;
//			}			
//		}	          
		return true;
	}
			
	function obterArrayBotoes()
	{
		 var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
		 return new Array(botao1);
	}
	
	function limpaCampos(idElemento)
	{
		var divCampos = document.getElementById(idElemento).childNodes;
		for(var i = 0; i < divCampos.length ; i++)
		{
			if(divCampos[i].id != null && divCampos[i].id != 'NaN')
			{
				var id = divCampos[i].id;
				if(id.indexOf('<%=Form.TD_CAMPO%>') == 0)
				{
					id.value = '';
				}
			}
		}
	}
	
	function desabilitaCampos(elementoExcluido)
	{
		var tdTodosCampos = document.getElementById('<%=Form.TD_CAMPOS%>').childNodes
		for(var i = 0; i < tdTodosCampos.length ; i++)
		{
			if(tdTodosCampos[i].id != null && tdTodosCampos[i].id != 'NaN')
			{
				var id = tdTodosCampos[i].id;
				if(id != elementoExcluido && id.indexOf('<%=Form.DIV%>') == 0)
				{
					limpaCampos(id);
					document.getElementById(id).style.display = "none";
				}
			}
		}
	}
	
	function habilitarCampos()
	{
		 var comboOpcao = document.getElementById('<%=Form.CAMPO_SELECT_STATUS%>').value;
		 var idDiv = '<%=Form.DIV%>'+comboOpcao;
		 if(comboOpcao == '' || comboOpcao == '0')
		 {
			desabilitaCampos(idDiv+'0');
		 }
		 else
		 {
			document.getElementById(idDiv).style.display = '';
			desabilitaCampos(idDiv);
		 }
	}
	
	function adicionarDar()
	{
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_NUMERO_DAR_QUITACAO%>, '<%=MensagemErro.NUMERO_DAR_DEVE_SER_INFORMADO%>'))
		{
			return false;
		}
		 desabilitarBotoes(obterArrayBotoes());
		 document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ADICIONAR_DAR+"=1"%>';
		 document.form.submit();
		 return true;
		
	}
   
   function excluirDar(indiceDar)
			{
				if(confirm('Deseja realmente excluir o Dar?'))
				{
					desabilitarBotoes(obterArrayBotoes());
					document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_EXCLUIR_DAR+"="%>'+indiceDar;
					document.form.submit();
					return true;
				}
				else
				{
					return false
				}
			}
   		
	</script>
	<jsp:include page="/util/ViewVerificaErro.jsp"/>
	<title><abaco:tituloSistema/></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document,'<%=Form.BOTAO_AJUDA%>','<%=Form.TEXTO_BOTAO_AJUDA%>','<c:out value="${requestScope.atributoTituloFuncionalidade}" />','<abaco:contexto/>');verificaErro();log();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
			<center>
			<form method="POST" name="form"  action="#" >
			  <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
			     <tr align="right"> 
                  <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
              </tr> 
            <tr class="SEFAZ-TR-Titulo" align="center"> 
					<td colspan="2">Dados da GIA - ITCD </td>
				</tr>
				<tr> 
					<td   width="50%"  class="SEFAZ-TD-RotuloSaida">Número:&nbsp;</td>
					<td class="SEFAZ-TD-CampoSaida" width="462"><c:out value="${giaITCDVo.codigo}"/></td>
				</tr>
				<tr> 
					<td width="50%" class="SEFAZ-TD-RotuloSaida">Tipo de Processo:&nbsp;</td>
					<td   class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.textoCorrente}"/></td>
				</tr>
				<tr>
					<td  width="50%" class="SEFAZ-TD-RotuloSaida">CPF/CNPJ do declarante:&nbsp;</td>
					<td  class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDocumento}" /></td>
				</tr>
				<tr>
					<td   width="50%" class="SEFAZ-TD-RotuloSaida">Situa&ccedil;&atilde;o:&nbsp;</td>
					<td  class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.statusAnterior.statusGIAITCD.textoCorrente}"/></td>
				</tr>
				<tr>
					<td   width="50%" class="SEFAZ-TD-RotuloSaida">Status:&nbsp;</td>
					<td  class="SEFAZ-TD-CampoSaida">
						<abaco:campoSelectCollection 
								ajuda="Selecione uma opção." 
								name="<%=Form.CAMPO_SELECT_STATUS%>" 
								nomeCollectionNoRequest="listaStatus" 
								nomeMetodoCodigo="getValorCorrente" 
								nomeMetodoValor="getTextoCorrente" 
								tabIndex="" 
								idCampo="<%=Form.CAMPO_SELECT_STATUS%>" 
								mostrarSelecione="true" 
								onChange="habilitarCampos();" 
								opcaoSelecionada="${giaITCDVo.statusVo.statusGIAITCD.valorCorrente}">
							</abaco:campoSelectCollection><font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td colspan="2" id="<%=Form.TD_CAMPOS%>">
						<div id="<%=Form.DIV+DomnStatusGIAITCD.PROTOCOLADO%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloSaida" valign="top" width="370">Agenfa de Protoloco:&nbsp;</td>
									<td class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.agenciaProtocolo.dadosUnidadeFormatado}"/></td>
								</tr>							
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Número de Protocolo:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoApenasNumero 
											maxlength="10" 
											name="<%=Form.CAMPO_NUMERO_PROTOCOLO%>" 
											size="20" 
											value="${giaITCDVo.statusVo.protocoloFormatado}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_NUMERO_PROTOCOLO%>" 
											mostrarZero="false">
										</abaco:campoApenasNumero><font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Data de Protocolo:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoData 
											name="<%=Form.CAMPO_DATA_PROTOCOLO%>" 
											size="10" 
											value="${giaITCDVo.statusVo.dataProtocoloFormatada}"
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_DATA_PROTOCOLO%>" >
										</abaco:campoData><font color="red">*</font>
									</td>
								</tr>

							</table>						
						</div>
						<div id="<%=Form.DIV+DomnStatusGIAITCD.NOTIFICADO_CIENTE%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Data de Ciência da Notificação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoData 
											name="<%=Form.CAMPO_DATA_CIENCIA_NOTIFICACAO%>" 
											size="10" 
											value="${giaITCDVo.statusVo.dataCienciaNotificacaoFormatada}"
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_DATA_CIENCIA_NOTIFICACAO%>" >
										</abaco:campoData><font color="red">*</font>
									</td>
								</tr>	
							</table>						
						</div>
						<div id="<%=Form.DIV+DomnStatusGIAITCD.PARCELADO%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Número de Protocolo do Parcelamento:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoApenasNumero 
											maxlength="10" 
											name="<%=Form.CAMPO_NUMERO_PROTOCOLO_PARCELAMENTO%>" 
											size="50" 
											value="${giaITCDVo.statusVo.protocoloParcelamentoFormatado}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_NUMERO_PROTOCOLO_PARCELAMENTO%>" 
											mostrarZero="false">
										</abaco:campoApenasNumero><font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Data do Parcelamento:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoData 
											name="<%=Form.CAMPO_DATA_PARCELAMENTO%>" 
											size="10" 
											value="${giaITCDVo.statusVo.dataParcelamentoFormatado}"
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_DATA_PARCELAMENTO%>" >
										</abaco:campoData><font color="red">*</font>					
									</td>
								</tr>	
								<c:if test="${giaITCDVo.statusVo.exibeCampoJustificativaRetornoParcelamento}">
									<tr>
										<td class="SEFAZ-TD-RotuloEntrada" width="370">Justificativa Retorno Parcelamento:&nbsp;</td>
										<td class="SEFAZ-TD-CampoEntrada">
											<abaco:campoTextArea 
												ajuda="Digite uma observação." 
												altura="3" 
												largura="40" 
												maxlength="400" 
												name="<%=Form.CAMPO_JUSTIFICATIVA_RETORNO_PARCELAMENTO%>" 
												tabIndex="" 
												value="${giaITCDVo.statusVo.justificativaReparcelamento}" 
												idCampo="<%=Form.TD_CAMPO+Form.CAMPO_JUSTIFICATIVA_RETORNO_PARCELAMENTO%>">
											</abaco:campoTextArea><font color="red">*</font>
										</td>
									</tr>									
								</c:if>
							</table>						
						</div>
						<div id="<%=Form.DIV+DomnStatusGIAITCD.QUITADO_MANUALMENTE%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Data de Quitação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoData 
											name="<%=Form.CAMPO_DATA_QUITACAO%>" 
											size="10" 
											value="${giaITCDVo.statusVo.dataQuitacaoFormatada}"
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_DATA_QUITACAO%>" >
										</abaco:campoData><font color="red">*</font>					
									</td>
								</tr>	
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Observação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoTextArea 
											ajuda="Digite uma observação." 
											altura="3" 
											largura="40" 
											maxlength="400" 
											name="<%=Form.CAMPO_OBSERVACAO%>" 
											tabIndex="" 
											value="${giaITCDVo.statusVo.observacao}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_OBSERVACAO%>">
										</abaco:campoTextArea><font color="red">*</font>
									</td>
								</tr>	
								<c:if test="${giaITCDVo.giaParcelada}">
									<tr>
										<td class="SEFAZ-TD-RotuloEntrada" width="370">Quantidade de Parcelas:&nbsp;</td>
										<td class="SEFAZ-TD-CampoEntrada">
											<abaco:campoApenasNumero 
												maxlength="2" 
												name="<%=Form.CAMPO_QUANTIDADE_PARCELAS%>" 
												size="5" 
												value="${giaITCDVo.statusVo.numeroParcelasFormatado}" 
												mostrarZero="false" 
												idCampo="<%=Form.TD_CAMPO+Form.CAMPO_QUANTIDADE_PARCELAS%>">
											</abaco:campoApenasNumero><font color="red">*</font>
										</td>
									</tr>								
								</c:if>
								<tr id="numrDarRatificacao">
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Número do DAR:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoApenasNumero 
											maxlength="13" 
											name="<%=Form.CAMPO_NUMERO_DAR_QUITACAO%>" 
											size="16" 
											value="${giaITCDVo.statusVo.numeroDARQuitacaoFormatado}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_NUMERO_DAR_QUITACAO%>" 
											mostrarZero="false">
										</abaco:campoApenasNumero><font color="red">*</font>&nbsp;
										<abaco:botaoConfirmar 
											nomeBotao="<%=Form.BOTAO_ADICIONAR_DAR%>" 
											onClick="adicionarDar();" 
											textoBotao="Adicionar DAR">
										</abaco:botaoConfirmar>
									</td>
								</tr>	
								<c:if test="${not empty giaITCDVo.statusVo.giaITCDDarVo.collVO}">
									<tr><td colspan="2">&nbsp;</td></tr>
									<tr>
										<td colspan="2">
											<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
												<tr class="SEFAZ-TR-SubTitulo">
													<td align="center">Número do DAR</td>
													<td align="center">Valor do DAR</td>
                                       <td align="center">&nbsp;&nbsp;&nbsp;&nbsp;</td>
												</tr>
												<c:forEach var="dar" items="${giaITCDVo.statusVo.giaITCDDarVo.collVO}" varStatus="contador" >
													<abaco:linhaTabela>
														<td align="center"><c:out value="${dar.numeroDarFormatado}"/></td>
														<td align="right"><c:out value="${dar.valorTotalDarFormatado}"/></td>
                                          <td align="center"><a href="javascript:void(excluirDar(<c:out value="${contador.index}"></c:out>));">Excluir</a>&nbsp;&nbsp;</td>
													</abaco:linhaTabela>
												</c:forEach>
											</table>
										</td>
									</tr>									
								</c:if>
							</table>						
						</div>
						<div id="<%=Form.DIV+DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Data de Envio para Inscrição em Dívida Ativa:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoData 
											name="<%=Form.CAMPO_DATA_ENVIO_INSCRICAO_DIVIDA_ATIVA%>" 
											size="10" 
											value="${giaITCDVo.statusVo.dataEnvioInscricaoDividaAtivaFormatada}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_DATA_ENVIO_INSCRICAO_DIVIDA_ATIVA%>">
										</abaco:campoData>
									</td>
								</tr>
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Justificativa de Envio para Inscrição em Dívida Ativa:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoTextArea 
											ajuda="Digite uma justificativa." 
											altura="3" 
											largura="40" 
											maxlength="400" 
											name="<%=Form.CAMPO_JUSTIFICATIVA_ENVIO_INSCRICAO_DIVIDA_ATIVA%>" 
											tabIndex="" 
											value="${giaITCDVo.statusVo.justificativaEnvioInscricaoDividaAtiva}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_JUSTIFICATIVA_ENVIO_INSCRICAO_DIVIDA_ATIVA%>">
										</abaco:campoTextArea><font color="red">*</font>
									</td>
								</tr>		
							</table>						
						</div>
						<div id="<%=Form.DIV+DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Data de Envio para Dívida Ativa:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoData 
											name="<%=Form.CAMPO_DATA_ENVIO_DIVIDA_ATIVA%>" 
											size="10" 
											value="${giaITCDVo.statusVo.dataEnvioDividaAtivaFormatado}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_DATA_ENVIO_DIVIDA_ATIVA%>">
										</abaco:campoData><font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Justificativa de Envio para Dívida Ativa:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoTextArea 
											ajuda="Digite uma justificativa." 
											altura="3" 
											largura="40" 
											maxlength="400" 
											name="<%=Form.CAMPO_JUSTIFICATIVA_ENVIO_DIVIDA_ATIVA%>" 
											tabIndex="" 
											value="${giaITCDVo.statusVo.justificativaEnvioDividaAtiva}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_JUSTIFICATIVA_ENVIO_DIVIDA_ATIVA%>">
										</abaco:campoTextArea><font color="red">*</font>
									</td>
								</tr>						
							</table>						
						</div>
						<div id="<%=Form.DIV+DomnStatusGIAITCD.RETIFICADO_CIENTE%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Data de Ciência da Retificação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoData 
											name="<%=Form.CAMPO_DATA_CIENCIA_RETIFICACAO%>" 
											size="10" 
											value="${giaITCDVo.statusVo.dataCienciaRetificacaoFormatado}"
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_DATA_CIENCIA_RETIFICACAO%>" >
										</abaco:campoData><font color="red">*</font>
									</td>
								</tr>						
							</table>						
						</div>
						<div id="<%=Form.DIV+DomnStatusGIAITCD.IMPUGNADO%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Data da Impugnação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoData 
											name="<%=Form.CAMPO_DATA_IMPUGNACAO%>" 
											size="10" 
											value="${giaITCDVo.statusVo.dataImpugnacaoFormatado}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_DATA_IMPUGNACAO%>">
										</abaco:campoData><font color="red">*</font>
									</td>
								</tr>			
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Número de Protocolo de Impugnação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoApenasNumero 
											maxlength="10" 
											name="<%=Form.CAMPO_NUMERO_PROTOCOLO_IMPUGNACAO%>" 
											size="50" 
											value="${giaITCDVo.statusVo.protocoloImpugnacaoFormatado}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_NUMERO_PROTOCOLO_IMPUGNACAO%>" 
											mostrarZero="false">
										</abaco:campoApenasNumero><font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Motivo da Impugnação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoTextArea 
											ajuda="Digite o motivo." 
											altura="3" 
											largura="40" 
											maxlength="400" 
											name="<%=Form.CAMPO_MOTIVO_IMPUGNACAO%>" 
											tabIndex="" 
											value="${giaITCDVo.statusVo.motivoImpugnacao}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_MOTIVO_IMPUGNACAO%>">
										</abaco:campoTextArea><font color="red">*</font>
									</td>
								</tr>	
							</table>						
						</div>
                  
                  
                  <!-- Campos Exibidos quando selecionando a opcao QUIATADO_GIOR-->
                  
                  <div id="<%=Form.DIV+DomnStatusGIAITCD.QUITADO_PELA_GIOR%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Justificativa de Alteração:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<textarea cols="40" rows="4" class="SEFAZ-INPUT-TEXT" id="<%=Form.CAMPO_JUSTIFICATIVA_ALTERACAO%>" name="<%=Form.CAMPO_JUSTIFICATIVA_ALTERACAO%>"><c:out value="${giaITCDVo.statusVo.justificativaAlteracao}"/></textarea>
                              <font color="red">*</font>
									</td>
								</tr>
                        <!--
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Valor do Imposto:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoMonetario 
											name="<%=Form.CAMPO_VALOR_IMPOSTO_SEGUNDA_RETIFICACAO%>" 
											quantidadeDigitosInteiros="13" 
											size="25" 
											value="${giaITCDVo.statusVo.valorImpostoFormatado}" 
											mostrarZero="false" 
											quantidadeCasasDecimais="2" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_VALOR_IMPOSTO_SEGUNDA_RETIFICACAO%>">
										</abaco:campoMonetario><font color="red">*</font>
									</td>
								</tr>
                        -->
							</table>
						</div>
                  
                  <!-- -->
                  
						<div id="<%=Form.DIV+DomnStatusGIAITCD.SEGUNDA_RETIFICACAO%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Data da Segunda Retificação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoData 
											name="<%=Form.CAMPO_DATA_SEGUNDA_RETIFICACAO%>" 
											size="10" 
											value="${giaITCDVo.statusVo.dataEmissaoSegundaRetificacaoFormatado}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_DATA_SEGUNDA_RETIFICACAO%>">
										</abaco:campoData><font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Valor do Imposto:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoMonetario 
											name="<%=Form.CAMPO_VALOR_IMPOSTO_SEGUNDA_RETIFICACAO%>" 
											quantidadeDigitosInteiros="13" 
											size="25" 
											value="${giaITCDVo.statusVo.valorImpostoFormatado}" 
											mostrarZero="false" 
											quantidadeCasasDecimais="2" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_VALOR_IMPOSTO_SEGUNDA_RETIFICACAO%>">
										</abaco:campoMonetario><font color="red">*</font>
									</td>
								</tr>
							</table>
						</div>
						<div id="<%=Form.DIV+DomnStatusGIAITCD.RATIFICADO%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Data da Ratificação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoData 
											name="<%=Form.CAMPO_DATA_RATIFICACAO%>" 
											size="10" 
											value="${giaITCDVo.statusVo.dataRatificacaoFormatado}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_DATA_RATIFICACAO%>">
										</abaco:campoData><font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Valor do Imposto:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoMonetario 
											name="<%=Form.CAMPO_VALOR_IMPOSTO_RATIFICACAO%>" 
											quantidadeDigitosInteiros="13" 
											size="25" 
											value="${giaITCDVo.statusVo.valorImpostoFormatado}" 
											mostrarZero="false" 
											quantidadeCasasDecimais="2" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_VALOR_IMPOSTO_RATIFICACAO%>">
										</abaco:campoMonetario><font color="red">*</font>
									</td>
								</tr>
							</table>						
						</div>
						<div id="<%=Form.DIV+DomnStatusGIAITCD.SEGUNDA_RETIFICACAO_CIENTE%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Data de Ciência da Segunda Retificação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoData 
											name="<%=Form.CAMPO_DATA_CIENCIA_SEGUNDA_RETIFICACAO%>" 
											size="10" 
											value="${giaITCDVo.statusVo.dataCienciaSegundaRetificacaoFormatado}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_DATA_CIENCIA_SEGUNDA_RETIFICACAO%>">
										</abaco:campoData><font color="red">*</font>
									</td>
								</tr>	
							</table>						
						</div>
						<div id="<%=Form.DIV+DomnStatusGIAITCD.RATIFICADO_CIENTE%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Data de Ciência da Ratificação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoData 
											name="<%=Form.CAMPO_DATA_CIENCIA_RATIFICACAO%>" 
											size="10" 
											value="${giaITCDVo.statusVo.dataCienciaRatificacaoFormatada}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_DATA_CIENCIA_RATIFICACAO%>">
										</abaco:campoData><font color="red">*</font>									
									</td>
								</tr>						
							</table>						
						</div>
                         
                  <div id="<%=Form.DIV+DomnStatusGIAITCD.ISENTO%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Observação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoTextArea 
											ajuda="Digite uma observação." 
											altura="3" 
											largura="40" 
											maxlength="400" 
											name="<%=Form.CAMPO_OBSERVACAO_ISENTO%>" 
											tabIndex="" 
											value="${giaITCDVo.statusVo.observacao}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_OBSERVACAO_ISENTO%>">
										</abaco:campoTextArea><font color="red">*</font>
									</td>
								</tr>	
							</table>						
						</div>                                      
						<div id="<%=Form.DIV+DomnStatusGIAITCD.ENVIADO_CCF%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">							
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Número de Protocolo:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoApenasNumero 
											maxlength="10" 
											name="<%=Form.CAMPO_NUMERO_PROTOCOLO_CCF%>" 
											size="20" 
											value="${giaITCDVo.statusVo.protocoloCcfFormatado}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_NUMERO_PROTOCOLO_CCF%>" 
											mostrarZero="false">
										</abaco:campoApenasNumero><font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Data do envio ao CCF:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoData 
											name="<%=Form.CAMPO_DATA_ENVIO_CCF%>" 
											size="10" 
											value="${giaITCDVo.statusVo.dataEnvioCCFFormatada}"
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_DATA_ENVIO_CCF%>" >
										</abaco:campoData><font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Observação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoTextArea 
											ajuda="Digite uma observação." 
											altura="3" 
											largura="40" 
											maxlength="400" 
											name="<%=Form.CAMPO_OBSERVACAO_ENVIADO_CCF%>" 
											tabIndex="" 
											value="${giaITCDVo.statusVo.observacao}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_OBSERVACAO_ENVIADO_CCF%>">
										</abaco:campoTextArea><font color="red">*</font>
									</td>
								</tr>	
							</table>						
						</div>                  
                  <div id="<%=Form.DIV+DomnStatusGIAITCD.QUITADO_CCF%>" style="display:none">
							<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
								<tr>
									<td class="SEFAZ-TD-RotuloEntrada" width="370">Observação:&nbsp;</td>
									<td class="SEFAZ-TD-CampoEntrada">
										<abaco:campoTextArea 
											ajuda="Digite uma observação." 
											altura="3" 
											largura="40" 
											maxlength="400" 
											name="<%=Form.CAMPO_OBSERVACAO_QUITADO_CCF%>" 
											tabIndex="" 
											value="${giaITCDVo.statusVo.observacao}" 
											idCampo="<%=Form.TD_CAMPO+Form.CAMPO_OBSERVACAO_QUITADO_CCF%>">
										</abaco:campoTextArea><font color="red">*</font>
									</td>
								</tr>	
							</table>						
						</div>                  
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>		
				<script type="text/javascript" language="javascript">
					habilitarCampos();
				</script>
			</table>
				
			<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
				<tr> 
					<td colspan="2" align="center"><input type="button" value="<%=Form.TEXTO_BOTAO_ALTERAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR_CLONE%>" onClick="validaFormulario()"></input>
					<abaco:botaoCancelar/>
					</td>
				</tr>
				<tr>
					<td colspan="2"><font color="red"><b>* Campos Obrigatórios</b></font></td>
				</tr>
			 </table>
        <abaco:mensagemAguardeCarregando/>
		  <abaco:log/>
      </form>
			</center>
		<g:mostrarRodape/>
	</body>
</html>
