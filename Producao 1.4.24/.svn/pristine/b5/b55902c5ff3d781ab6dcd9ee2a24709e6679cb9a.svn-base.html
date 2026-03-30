<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarAjuda.jsp
* Criação : Outubro de 2007 / Elizabeth Barbosa Moreira
* Revisão : Marlo Eichenberg Motta 
* Adaptações: Janeiro de 2008 / Wendell Farias 
* Log : 27/11/2007
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import="br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.Form"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<jsp:include page="/util/ViewVerificaErro.jsp"/>
<html>
	<head>
	<title><abaco:tituloSistema/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
	<META HTTP-EQUIV=Cache-Control content=no-store>
	<META HTTP-EQUIV=Pragma content=no-cache>
	<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
        <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
	<script type="text/javascript" language="javascript">
        
                 /*
                Função responsável por atualizar(limpar e escrever os registros dos combobox dinamicamente).
                Autor: Maxwell Rocha 
                */
                function atualizaLista(objlista1)
                { 
                     //fazer submit para a servlet e retornar a esta página com os registro do combo a ser atualizado.
                    if(objlista1.name=="<%=Form.CAMPO_SELECT_MODULO_AJUDA%>")
                    {
                        if(objlista1.selectedIndex != 0)
                        {
                            solicitarListarSubModulo();
                             for(var i=1; i<arguments.length; i++)
                            {	
                                limparCombo(arguments[i]);
                            }
                        }
                    }
                    
                    if(objlista1.name=="<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>")
                    {
                        if(objlista1.selectedIndex != 0)
                        {
                            solicitarListarFuncionalidade();
                             for(var i=2; i<arguments.length; i++)
                            {	
                                limparCombo(arguments[i]);
                            }
                        }
                    }
                    
                    if(objlista1.name=="<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>")
                    {
                        if(objlista1.selectedIndex != 0)
                        {                            
                             for(var i=3; i<arguments.length; i++)
                            {	
                                limparCombo(arguments[i]);
                            }
                        
                            solicitarListarTelaAjuda();
                        }
                    }
                    
                
             }
                    
                /*
                Função responsável por limpar um combobox passado por parâmetro através da função atualizaLista(objlista1).
                Autor: Maxwell Rocha
                */	
                function limparCombo(campoAtualizacao)
                {
                    var listaDestino = campoAtualizacao.length;
                    for(var i=1; i < listaDestino; i++){
                            campoAtualizacao.options[1] = null;
                    }
                     campoAtualizacao.options[1] = null;
                }
                
		function validaFormulario()
		{			
			if( ! verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_MODULO_AJUDA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_MODULO_AJUDA+"'"%>))
			{
				return false;
			}
			if( ! verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_SUBMODULO_AJUDA+"'"%>))
			{
				return false;
			}
			if( ! verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_FUNCIONALIDADE_AJUDA+"'"%>))
			{
				return false;
			}
			if( ! verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_TELA_AJUDA+"'"%>))
			{
				return false;
			}		
			codigoTelaAjuda = document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>.value;
			desabilitarBotoes(obterArrayBotoes());
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR+ "=1"%>';
			document.form.submit();				
		}

		function obterArrayBotoes()
		{
			var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
			return new Array(botao1);
		}
	
		function solicitarListarSubModulo()
		{			
                    desabilitarBotoes(obterArrayBotoes());
                    for(var i=0; i < document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>.length; i++)
                    {
                        document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>.options[1] = null;
                    }
                    for(var i=0; i < document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>.length; i++)
                    {
                        document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>.options[1] = null;
                    }
                    document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_MODULO+ "="%>'+document.form.<%=Form.CAMPO_SELECT_MODULO_AJUDA%>.value;
                    document.form.submit();
		}
		
		function solicitarListarFuncionalidade()
		{
			desabilitarBotoes(obterArrayBotoes())
                        for(var i=0; i < document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>.length; i++)
                        {
                            document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>.options[1] = null;
                        }
                        for(var i=0; i < document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>.length; i++)
                        {
                            document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>.options[1] = null;
                        }
			if(document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>.value == "")
			{
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_MODULO+ "="%>'+document.form.<%=Form.CAMPO_SELECT_MODULO_AJUDA%>.value;
				document.form.submit();
			}
			else
			{
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_SUB_MODULO+ "="%>'+document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>.value;
				document.form.submit();
			}
		}
			
		function solicitarListarTelaAjuda()
		{
			desabilitarBotoes(obterArrayBotoes())
			if(document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>.value =="")
			{
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_SUB_MODULO+ "="%>'+document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>.value;
				document.form.submit();
			}
			else
			{
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_FUNCIONALIDADE+ "="%>'+document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>.value;
				document.form.submit();
			}
		}

		function solicitarListarTelaCampoAjuda()
		{
			desabilitarBotoes(obterArrayBotoes())
			if(document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>.value =="")
			{
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_FUNCIONALIDADE+ "="%>'+document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>.value;
				document.form.submit();
			}
			else
			{
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_TELA+ "="%>'+document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>.value;
				document.form.submit();
			}
		}
	</script>
	</head>
		<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
		<center>
			<form method="POST" name="form" action="">
				<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">                                
					<tr align="right">
						<td colspan="2"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
					</tr>
      			<tr class="SEFAZ-TR-Titulo" align="center">
						<td colspan="2">Dados do Registro</td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloEntrada" width="340">Módulo:&nbsp;</td>
						<td class="SEFAZ-TD-CampoEntrada">
							<select name="<%=Form.CAMPO_SELECT_MODULO_AJUDA%>" onchange="atualizaLista(this,document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>);">
								<option value="" selected><%=Form.SELECIONE%></option>
								<c:set var="modVo" value="${telaFuncionalidadeVo.funcionalidadeVo.moduloAjudaVo}"  scope="request"></c:set>
								<c:forEach var="moduloAjudaTempVo" items="${telaFuncionalidadeVo.funcionalidadeVo.moduloAjudaVo.collVO}">                                                 
										<option value="<c:out value="${moduloAjudaTempVo.codigo}"></c:out>" <c:if test="${moduloAjudaTempVo.codigo == modVo.codigo}">selected</c:if>> <c:out value="${moduloAjudaTempVo.descricaoModuloAjuda}"></c:out></option>
								</c:forEach>
							</select><font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td  class="SEFAZ-TD-RotuloEntrada" width="340">Submodulo:&nbsp;</td>
						<td  class="SEFAZ-TD-CampoEntrada" id="tdTipoProcesso">
							<select name="<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>" onchange="atualizaLista(this,document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>);">
								<option value="" selected><%=Form.SELECIONE%></option>
									<c:set var="subModVo" value="${telaFuncionalidadeVo.funcionalidadeVo.moduloAjudaVo.subModuloAjuda}"  scope="request"></c:set>
									<c:forEach var="subModuloAjudaTempVo" items="${subModVo.collVO}">
									   <option value="<c:out value="${subModuloAjudaTempVo.codigo}"></c:out>" <c:if test="${subModuloAjudaTempVo.codigo == subModVo.codigo}">selected</c:if>><c:out value="${subModuloAjudaTempVo.descricaoModuloAjuda}"></c:out></option>
									</c:forEach>
							</select><font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloEntrada" width="340">Funcionalidade:&nbsp;</td>
						<td class="SEFAZ-TD-CampoEntrada" id="tdTipoProcesso">
							<select name="<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>" onchange="atualizaLista(this,document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>);  ">
								<option value="" selected><%=Form.SELECIONE%></option>
								<c:set var="funcVo" value="${telaFuncionalidadeVo.funcionalidadeVo}"  scope="request"></c:set>
									<c:forEach var="funcionalidadeTempVo" items="${funcVo.collVO}">                                                 
										<option value="<c:out value="${funcionalidadeTempVo.codigo}"></c:out>" <c:if test="${funcionalidadeTempVo.codigo == funcVo.codigo}">selected</c:if>><c:out value="${funcionalidadeTempVo.descricaoFuncionalidade}"></c:out></option>
									</c:forEach>
							</select><font color="red">*</font>	  
						</td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloEntrada" width="340">Tela:&nbsp;</td>
						<td  class="SEFAZ-TD-CampoEntrada">
							<select name="<%=Form.CAMPO_SELECT_TELA_AJUDA%>">
								<option value="" selected><%=Form.SELECIONE%></option>
								<c:set var="telaFuncionalidade" value="${telaFuncionalidadeVo}"  scope="request"></c:set>
									<c:forEach var="telaFuncionalidadeTempVo" items="${telaFuncionalidade.collVO}">                                                 
										<option value="<c:out value="${telaFuncionalidadeTempVo.telaAjudaVo.codigo}"></c:out>" <c:if test="${telaFuncionalidadeTempVo.telaAjudaVo.codigo == telaFuncionalidade.telaAjudaVo.codigo}">selected</c:if>><c:out value="${telaFuncionalidadeTempVo.informacaoTituloTelaFuncionalidade}"></c:out></option>
									</c:forEach>
							</select><font color="red">*</font>	  
						</td>
					</tr>	
					<tr> 
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2"><font color="red"><b>* Campos Obrigatórios</b></font></td>
					</tr>
					<tr> 
						<td  colspan="2" align="center"> 
							<input name="<%=Form.BOTAO_PESQUISAR_CLONE%>" type="button"   value="<%=Form.TEXTO_BOTAO_PESQUISAR%>" class="SEFAZ-INPUT-Botao" id="btnPesquisar" onClick="return validaFormulario();"/>
							<abaco:botaoCancelarSemMensagem/>
						</td>
					</tr>
					<tr> 
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
				<c:if test="${telaFuncionalidadeVo.telaAjudaVo.existe}">
					<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
						<tr class="SEFAZ-TR-Titulo" align="center">
							<td colspan="3">Dados da Pesquisa</td>
						</tr>
						<tr>
							<td  colspan="2" class="SEFAZ-TD-RotuloSaida" width="340">Título da Tela:&nbsp;</td>
							<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${telaFuncionalidadeVo.informacaoTituloTelaFuncionalidade}"></c:out></td>
						</tr>
						<tr>
							<td  colspan="2" class="SEFAZ-TD-RotuloSaida" width="340">Descrição da Tela/Funcionalidade:&nbsp;</td>
							<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${telaFuncionalidadeVo.descricaoTelaFuncionalidade}"></c:out></td>
						</tr>
						<tr> 
							<td colspan="2">&nbsp;</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${telaFuncionalidadeVo.telaAjudaVo.telaCampoAjudaVo.existe}">
					<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
						<tr class="SEFAZ-TR-Titulo" align="center">
							<td colspan="3">Campos da Tela/Funcionalidade</td>
						</tr>
						<tr class="SEFAZ-TR-Titulo">
							<td width="15%">Código</td>
							<td width="20%">Rótulo</td>
							<td width="65%">Descrição do Rótulo</td>
						</tr>
						<c:forEach var="telaCampoAjudaVo" items="${telaFuncionalidadeVo.telaAjudaVo.telaCampoAjudaVo.collVO}" varStatus="indice">                                                 
							<c:if test="${indice.count % 2 != 0}">
								<tr class="SEFAZ-TR-ExibicaoImpar">
							</c:if>
							<c:if test="${indice.count % 2 == 0}">
								<tr class="SEFAZ-TR-ExibicaoPar">
							</c:if>
									<td width="15%"><c:out value="${telaCampoAjudaVo.campoAjudaVo.codigo}"></c:out></td>
									<td align="left" width="20%"><c:out value="${telaCampoAjudaVo.campoAjudaVo.descricaoRotulo}"></c:out></td>
									<td align="left" width="65%" ><c:out value="${telaCampoAjudaVo.descricaoAjudaCampo}"></c:out></td>
								</tr>
						</c:forEach>
					</table>				
				</c:if>
				<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
					<tr>
						<td  colspan="2" align="center"> 
							<abaco:mensagemAguardeCarregando></abaco:mensagemAguardeCarregando>
						</td>
					</tr>
				</table>
		</form>
      <g:mostrarRodape/>
  </body>
</html>