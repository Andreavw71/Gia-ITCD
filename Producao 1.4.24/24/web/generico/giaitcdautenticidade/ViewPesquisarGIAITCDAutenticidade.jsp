<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarCultura.jsp
* Criação : Dezembro de 2007 / Elizabeth Barbosa Moreira
* Revisão: 
* Data revisão: 
* Log : 
*/
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.giaitcd.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnAvaliacao"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<%
	pageContext.setAttribute("CODIGO_GIAITCD", new Integer(DomnAvaliacao.CODIGO_GIAITCD));
	pageContext.setAttribute("CODIGO_ISENCAO_VALORES", new Integer(DomnAvaliacao.CODIGO_ISENCAO_VALORES));
	pageContext.setAttribute("CODIGO_OCORRENCIA_FATO_GERADOR", new Integer(DomnAvaliacao.CODIGO_OCORRENCIA_FATO_GERADOR));
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
		  <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script> 
        <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
        <script type="text/javascript" language="javascript">
					
					function validaFormulario()
					{
						var opcaoPesquisa = document.form.<%=Form.CAMPO_SELECT_STATUS%>.value;

						//solicita ao usuário que selecione  uma opção de consulta
						if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_STATUS%>,<%="'"+MensagemErro.VALIDAR_CAMPO_SELECT_STATUS+"'"%>))
						{
							return false;
						}
						//solicita que o usuário preencha a opção de consulta
						if(opcaoPesquisa == <%=DomnAvaliacao.CODIGO_GIAITCD%>)
						{
							if(!verificaCamposW3c(document.form.<%=Form.CAMPO_CODIGO_GIA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_PESQUISA+"'"%>))
							{
								return false;
							}						
						}
						//solicita que o usuário preencha a opção de consulta
						else if(opcaoPesquisa == <%=DomnAvaliacao.CODIGO_ISENCAO_VALORES%>)
						{
							if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_DECLARACAO_DE_ISENCAO_DE_VALORES%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_PESQUISA+"'"%>))
							{
								return false;
							}
						}
						//solicita que o usuário preencha a opção de consulta
						else if(opcaoPesquisa == <%=DomnAvaliacao.CODIGO_OCORRENCIA_FATO_GERADOR%>)
						{
							if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_DECLARACAO_DE_NAO_OCORRENCIA_DE_FATO_GERADOR%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_PESQUISA+"'"%>))
							{
								return false;
							}						
						}
						//solicita que o usuário preencha o código de autenticidade
						if(!verificaCamposW3c(document.form.<%=Form.CAMPO_CODIGO_AUTENTICIDADE%>,<%="'"+MensagemErro.VALIDAR_CAMPO_CODIGO_AUTENTICIDADE+"'"%>))
						{
							return false;
						}
						
					desabilitarBotoes(obterArrayBotoes());
					document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR+"=1"%>';
					document.form.submit();
					return true;
					}
					
				function habilitarCampo()
				{
					comboOpcao = document.form.<%=Form.CAMPO_SELECT_STATUS%>.options[document.form.<%=Form.CAMPO_SELECT_STATUS%>.selectedIndex].value;					
					//limpar campos se uruário selecionar ----Selecone-----
					if(comboOpcao == "")
					 {					
						  document.getElementById('rotuloProtocolado').innerHTML = '';
						  document.getElementById('campoProtocolado').innerHTML = '';	
						  document.getElementById('espaco').innerHTML = '';
					 }
					//montar campo para digitar Código GIA
					 else if(comboOpcao == <%=DomnAvaliacao.CODIGO_GIAITCD%>) 
					 {
						  document.getElementById('rotuloProtocolado').innerHTML = 'Número da GIA-ITCD:&nbsp;';
						  document.getElementById('campoProtocolado').innerHTML = '<input name="<%=Form.CAMPO_CODIGO_GIA%>" type="text" id="numero"  value="<c:out value="${giaITCDVo.codigo}"/>" onKeyPress="return formataNumeroW3c(numero, event)" size="13" maxlength="15"/><font color="red">*</font>';
						  document.getElementById('espaco').innerHTML = '';
						  
					 }
					//montar campo para digitar Código Isenção de Valores
					else if(comboOpcao == <%=DomnAvaliacao.CODIGO_ISENCAO_VALORES%>) 
					 {
						  document.getElementById('rotuloProtocolado').innerHTML = 'Número da Declaração de Isenção de Valores:&nbsp;';
						  document.getElementById('campoProtocolado').innerHTML = '<input name="<%=Form.CAMPO_SELECT_DECLARACAO_DE_ISENCAO_DE_VALORES%>" type="text" id="numero" value="<c:out value="${giaITCDVo.numrDeclaracaoIsencao}"/>"  onKeyPress="return formataNumeroW3c(numero, event)" size="13" maxlength="15"/><font color="red">*</font>';
						  document.getElementById('espaco').innerHTML = '';
						  
					 }
					 //montar campo para digitar Código de ocorrencia de fator gerador
					else if(comboOpcao == <%=DomnAvaliacao.CODIGO_OCORRENCIA_FATO_GERADOR%>) 
					 {
						  document.getElementById('rotuloProtocolado').innerHTML = 'Número  da Declaração de não Ocorrencia de&nbsp; Fator Gerador:&nbsp;';
						  document.getElementById('campoProtocolado').innerHTML = '<input name="<%=Form.CAMPO_SELECT_DECLARACAO_DE_NAO_OCORRENCIA_DE_FATO_GERADOR%>" type="text" class="SEFAZ-INPUT-Numero" id="numero" value="<c:out value="${giaITCDVo.numrDeclaracaoFatoGerador}"/>"  onKeyPress="return formataNumeroW3c(numero, event)" size="13" maxlength="15"/><font color="red">*</font>';
						  document.getElementById('espaco').innerHTML = '&nbsp;';
					 }
						 
				 }
				 
				 function obterArrayBotoes()
				{
					 var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
					 return new Array(botao1);
				}
				
				
			</script>
			<jsp:include page="/util/ViewVerificaErro.jsp"/>
  <title><abaco:tituloSistema/></title>
			</head>
			<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>'); 
									 habilitarCampo();verificaErro();">
			<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
			<center>
		 		<form method="POST" name="form"  action="#"><br/>
					<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
					<tr align="right"> 
                  <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
               </tr> 
      				<tr class="SEFAZ-TR-Titulo" align="center"> 
							<td colspan="2">Filtro para Pesquisa</td>
						</tr>
						<tr>
							<td   width="48%" class="SEFAZ-TD-RotuloEntrada">Selecione:&nbsp;</td>
							<td  class="SEFAZ-TD-CampoEntrada" width="52%">
								<abaco:campoSelectDominio 
									ajuda="" 
									classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnAvaliacao" 
									name="<%=Form.CAMPO_SELECT_STATUS%>" 
									tabIndex="" 
									onChange="habilitarCampo()" 
									mostrarSelecione="true" 
									naoMostrar="<%=DomnAvaliacao.AVALIACAO_POR_AGENFA%>" 
									opcaoSelecionada="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente}">
								</abaco:campoSelectDominio><font color="red">*</font>
						  </td>
						</tr>
						<tr > 
						  <td class="SEFAZ-TD-RotuloEntrada" width="48%"><div id="rotuloProtocolado"></div></td>
							<td class="SEFAZ-TD-CampoEntrada"><div id="campoProtocolado"></div></td>
						</tr>
						<tr> 
							<td colspan="2"><div id="espaco">&nbsp;</div></td>
						</tr>
						<tr>
							<td class="SEFAZ-TD-RotuloEntrada" width="48%">Código de Autenticidade:&nbsp;</td>
							<td class="SEFAZ-TD-CampoEntrada" ><input name="<%=Form.CAMPO_CODIGO_AUTENTICIDADE%>" type="text" id="codigo" size="20" maxlength="16" onblur="javascript:toUpperCaseW3c(this);" value="<c:out value='${giaITCDVo.codigoAutenticidade}'/>"></input><font color="red">*</font></td>
						</tr>
					</table>
					
					<br>
					<jsp:include page="/util/seguranca/ViewMostrarImagemCaracterModAberto.jsp"/>  
					
					<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
						<tr> 
							<td> 
								<div align="center">
									<input name="<%=Form.BOTAO_PESQUISAR_CLONE%>"  type="button"  value="<%=Form.TEXTO_BOTAO_PESQUISAR%>"  class="SEFAZ-INPUT-Botao"  id="btnPesquisar"   onClick=" validaFormulario();"></input>
									<abaco:botaoCancelarSemMensagem></abaco:botaoCancelarSemMensagem>
								</div>
							</td>
						</tr>
						<tr>
							<td><font color="red"><b>* Campos Obrigatórios</b></font></td>
						</tr>
					</table>
					<table>
						<tr>
							 <td>
								  <div align="center"> 
									 <script type="text/javascript" language="javascript">gerarSpanMesgBotao();</script>
								  </div>
							 <td>
						</tr>
					</table>
				</form>
			</center>
			<g:mostrarRodape/>
		  </body>
</html>