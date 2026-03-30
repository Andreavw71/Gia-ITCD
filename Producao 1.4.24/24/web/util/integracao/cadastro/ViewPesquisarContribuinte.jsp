<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarContribuinte.jsp
* Criação : Rogério Sanches de Oliveira
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.dominio.DomnOpcaoPesquisaContribuinte"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import= " br.gov.mt.sefaz.itc.model.generico.giaitcd.Form"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.Flow"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<meta http-equiv=Expires content="Thu, 01 Jan 1970 00:00:00 GMT">
		<meta http-equiv=Cache-Control content=no-store>
		<meta http-equiv=Pragma content=no-cache>
		<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
		<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script> 
		<script type="text/javascript" src="/javascript/funcoesGenericas.js"></script>
		<script type="text/javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
		<script type="text/javascript">
		/**
		*	Função que habilita os campos da view de pesquisa do contribuinte. Dependendo da opção selecionada pelo 
		*	usuário, o campo para entrada de dados é criado em tempo de execução.
		*/
			
			function habilitarCampo(comboOpcao)
			{
				ocultarLocalizarContribuinte();
				if(comboOpcao == <%=DomnOpcaoPesquisaContribuinte.CPF%>)
				{
					document.getElementById('rotuloDocumento').innerHTML = 'CPF:';
					document.getElementById('campoDocumento').innerHTML = '<%=JspUtil.getCampoCPF(Form.CAMPO_CPF, Form.CAMPO_CPF, "")%>';
				}
				else if(comboOpcao == <%=DomnOpcaoPesquisaContribuinte.CNPJ%>)
				{
					document.getElementById('rotuloDocumento').innerHTML = 'CNPJ:';
					document.getElementById('campoDocumento').innerHTML = '<%=JspUtil.getCampoCNPJ(Form.CAMPO_CNPJ, Form.CAMPO_CNPJ, "")%>';
				}
				else if(comboOpcao == <%=DomnOpcaoPesquisaContribuinte.INSCRICAO_ESTADUAL%>)
				{
					document.getElementById('rotuloDocumento').innerHTML = 'Inscrição Estadual:';
					document.getElementById('campoDocumento').innerHTML = '<%=JspUtil.getCampoInscricaoEstadual(Form.CAMPO_CNPJ, Form.CAMPO_CNPJ, "")%>';
				}
				else if(comboOpcao == <%=DomnOpcaoPesquisaContribuinte.NOME_RAZAO_SOCIAL%>)
				{
					document.getElementById('rotuloDocumento').innerHTML = 'Nome / Razão Social:';
					document.getElementById('campoDocumento').innerHTML = '<%=JspUtil.getCampoNomeRazaoSocial(Form.CAMPO_CNPJ, Form.CAMPO_CNPJ, "")%>';
				}
				else
				{
					document.getElementById('rotuloDocumento').innerHTML = 'Selecione uma opção.';
					document.getElementById('campoDocumento').innerHTML = '';
				}
			}


			
			
			/**
			*	Função utilizada para disparar para a servlet ContribuintePesquisar e consultar o contribuinte de acordo com os
			*	parametros informado na view de Pesquisa.
			*/
			function consultarContribuinte()
			{
				desabilitarBotoes(obterArrayBotoes());
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>';
				document.form.submit();
				return true;
			}
			
			/**
			*	Função utilizada para capturar o botão que será desabilitado ao enviar uma requisição para o servidor.
			*/
			function obterArrayBotoes()
			{
				var botao1 = document.form.<%=JspUtil.BOTAO_LOCALIZAR_CONTRIBUINTE%>;
				return new Array(botao1);
			}
			
			/**
			*	Função que abre um popup para efetuar o cadastro da Pessoa Jurídica.
			*/
			function cadastrarPessoaJuridica()
			{
				window.open("<%=Flow.SERVLET_CADASTRAR_CONTRIBUINTE_PESSOA_JURIDICA_MODABERTO%>","cadastroContribuinte","location=no,toolbar=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,height=650,width=760");
			}
			
			/**
			*	Função que abre um popup para efetuar o cadastro da Pessoa Física.
			*/
			function cadastrarPessoaFisica()
			{
				window.open("<%=Flow.SERVLET_CADASTRAR_CONTRIBUINTE_PESSOA_FISICA_MODABERTO%>","cadastroContribuinte","location=no,toolbar=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,height=650,width=760");
			}
		</script>
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<title><abaco:tituloSistema/></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro(); ocultarLocalizarContribuinte();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
			<form name="form" method="POST" action="#">
				<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
				<tr align="right"> 
                <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
            </tr> 
               <tr class="SEFAZ-TR-Titulo">
						<td colspan="3">Pesquisar Contribuinte Por:</td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloEntrada">Op&ccedil;&atilde;o de Consulta:</td>
						<td class="SEFAZ-TD-ComboBox" align="center">
							<select name="comboPesquisaOpcaoContribuinte" onchange="javascript: habilitarCampo(this.value)">
								<option value=""><%=Form.SELECIONE%></option>
								<option value="<%=DomnOpcaoPesquisaContribuinte.CPF%>"><%=DomnOpcaoPesquisaContribuinte.DESCRICAO_CPF%></option>
								<c:if test="${empty contribuinteApenasCPF}">
								<option value="<%=DomnOpcaoPesquisaContribuinte.CNPJ%>"><%=DomnOpcaoPesquisaContribuinte.DESCRICAO_CNPJ%></option>
								</c:if>
							</select>
						</td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloEntrada"><div id="rotuloDocumento"></div></td>
						<td class="SEFAZ-TD-CampoEntrada"><div id="campoDocumento"></div></td>
					</tr>
				</table>
				<%if(request.getAttribute("exception")!=null){%>
					<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
						<tr> 
							<td colspan="4" align="center">
								<a href='javascript: cadastrarPessoaJuridica();'>Inclusão de Pessoa Jurídica</a>
							</td>
							<td colspan="4" align="center">
								<a href='javascript: cadastrarPessoaFisica();'>Inclusão de Pessoa Física</a>
							</td>
						</tr>
					</table>
				<%}%>
				<br>
				<br>
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
					<tr> 
						<td colspan="4" align="center"> 
							<abaco:botaoVoltar nomeContadorSubmit="pesquisarContribuinte" nomeBotao="Voltar"/>
							<abaco:botaoCancelar/>
							<%=JspUtil.getBotaoLocalizarContribuinte("Localizar", "consultarContribuinte();")%>
						</td>
					</tr>
				</table>
				<abaco:mensagemAguardeCarregando/>
			</form>
		<g:mostrarRodape/>
	</body>
</html>
