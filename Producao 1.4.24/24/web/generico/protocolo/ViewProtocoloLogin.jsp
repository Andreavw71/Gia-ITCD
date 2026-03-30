<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarGIAITCD.jsp
* Criação : Outubro de 2007 / Rogério Sanches de Oliveira
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsulta"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>

<html>
    <head>
        <title><abaco:tituloSistema></abaco:tituloSistema></title>
        <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
        <META HTTP-EQUIV=Cache-Control content=no-store>
        <META HTTP-EQUIV=Pragma content=no-cache>
        
        <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
		  <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script> 
        <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
        <script type="text/javascript" language="javascript">
            
            function pesquisar() {
                document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR_GIA_ITCD+"=1"%>';
                document.form.submit();            
            }
            
            function enviarNovaSenha()
            {
                document.form.action='<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_ALTERAR_PESQUISAR_GIAITCD_ENVIAR_SENHA, request)%>';
                document.form.submit();
                return true;
            }
				
        </script>
</head>

<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>'); verificaErro(); trocaOpcaoConsulta(<c:out value='${giaITCDVo.tipoConsultaGia.valorCorrente}'/>);">
    <!-- padrao sefaz - cabeçalho e página de erro -->
    <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
    <jsp:include page="/util/ViewVerificaErro.jsp"/>
    <!-- FIM: padrao sefaz - cabeçalho e página de erro -->
    
<form name="form" method="POST" action="#">
<c:set var="pesquisar"><%=JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD)%></c:set>
<c:set var="alterarGia"><%=JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_ALTERAR_GIAITCD_PESQUISAR_GIAITCD)%></c:set>
    <!-- cabeçalho da pesquisa -->
    <table width="740" border="0" cellspacing="1" cellpadding="0" align="center">
			<tr align="right"> 
				<td colspan="2"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
			</tr> 
			<tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="2">Filtro para Pesquisa</td>
			</tr>
			<c:if test="${giaITCDVo.usuarioServidor}">
				<tr>
						<td class="SEFAZ-TD-RotuloEntrada" width="50%">Tipo de Consulta:&nbsp;</td>
						<td class="SEFAZ-TD-ComboBox" width="50%">
								<abaco:campoSelectDominio ajuda="Selecione uma opção de Consulta" 
									classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsulta" 
									name="<%=Form.CAMPO_SELECT_TIPO_CONSULTA%>" tabIndex="" mostrarSelecione="true" 
									opcaoSelecionada="${giaITCDVo.tipoConsultaGia.valorCorrente}" 
									onChange="trocaOpcaoConsulta(this.value);"/>
									<font color="red">*</font>
						</td>
				</tr>			
			</c:if>
    <!-- FIM: cabeçalho da pesquisa -->
				 <tr id="opcaConsultaNumeroGia">
					  <td class="SEFAZ-TD-RotuloEntrada" width="50%">GIA:&nbsp;</td>
					  <td  class="SEFAZ-TD-CampoEntrada" width="50%">
							<abaco:campoApenasNumero maxlength="12" name="<%=Form.CAMPO_CODIGO_GIA%>" size="18" value="${giaITCDVo.codigoFormatado}"/><font color="red">*</font>
					  </td>
				 </tr>
				<c:if test="${not giaITCDVo.usuarioServidor}">
                <tr>
                    <td class="SEFAZ-TD-RotuloEntrada" width="50%">Senha:&nbsp;</td>
                    <td  class="SEFAZ-TD-CampoEntrada" width="50%">
                        <input type="password" name="<%=Form.CAMPO_SENHA%>"  class="SEFAZ-INPUT-Text" value="" size="25" maxlength="25"><font color="red">*</font>
                    </td>
                </tr>
                <tr>
                    <td class="SEFAZ-TD-CampoEntrada" colspan="2">
                        <div align="center"><a href="javascript:enviarNovaSenha();" class="SEFAZ-TD-CampoEntrada" >Esqueci minha senha</a></div>
                    </td>
                </tr>
            </c:if>
            <!-- FIM: para usuários externos -->
    </table>
    <!-- FIM: parametros da pesquisa -->

	<br>
	<jsp:include page="/util/seguranca/ViewMostrarImagemCaracterModAberto.jsp"/>	
	 
    <!-- botões de ações -->
    <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
        <tr> 
            <td> 
					<div align="center">
                        <input name="<%=Form.BOTAO_PESQUISAR_GIA_ITCD%>"  type="button"  value="<%=Form.TEXTO_BOTAO_PESQUISAR%>"  
                                class="SEFAZ-INPUT-Botao" onClick="pesquisar();" id="<%=Form.BOTAO_PESQUISAR_GIA_ITCD%>"/>
                <abaco:botaoCancelarSemMensagem/>
					</div>
            </td>
        </tr>
		  <tr>
			<td><font color="red"><b>* Campos Obrigatórios</b></font></td>
		  </tr>
    </table>
    <!-- FIM: botões de ações -->
    <br></br>
    <br/>
    <!-- bloco de mensagem -->
	<abaco:mensagemAguardeCarregando></abaco:mensagemAguardeCarregando>
    <!-- FIM: bloco de mensagem -->
<g:mostrarRodape/>
</form>
</body>
</html>