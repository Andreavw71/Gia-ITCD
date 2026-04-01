<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : SimuladorAcessoWeb.jsp
* Criação : Setembro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log : 
*/
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" %>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>

<script language="javascript" type="text/javascript">

	function solicitarAbrirManualArrolamento()
	{          
		window.open('http://www.sefaz.mt.gov.br/portal/download/arquivos/Manual_GIA_ITCD_Inventario_set_2011.pdf');
		document.form.submit();				
		document.form.target = '_self';
	}
   
   function acessoLogado()
   {
      window.location = "/acessoweb/login/LoginUsuario.jsp?tipoUsuario=2";
   }
	
	function solicitarAbrirManualDoacao()
	{
		window.open('http://www.sefaz.mt.gov.br/portal/download/arquivos/Manual_ITCD_Doacao_Outros_set_2011.pdf');
		document.form.submit();				
		document.form.target = '_self';
	}	

	function solicitarAbrirManualSeparacaoDivorcio()
	{
		window.open('http://www.sefaz.mt.gov.br/portal/download/arquivos/Manual_da_GIA_ITCD_Separacao_divorcio_partilha_set_2011.pdf');
		document.form.submit();				
		document.form.target = '_self';
	}	
	
</script>

<html>
	<head>
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
		<link rel="stylesheet" href="/estilos/SefazEstilos.css" type="text/css">
		<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
	</head>
	<body class="SEFAZ-Body">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="Menu Principal"/>
		<center>
      <b><a href="javascript: acessoLogado();">Acesso Logado</a></b>
      <br>
			<table width="381" border="0">
				<tr>
					<td width="375" align="left" valign="top">
						<table width="353" border="0" cellpadding="0" cellspacing="0">
							<tr class="SEFAZ-TR-Titulo">
								<td height="22">Tabelas B&aacute;sicas</td>
							</tr>
							<tr>
								<td height="22" width="250"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="/itc/tabelabasica/ViewMenuTabelaBasica.jsp">Tabelas B&aacute;sicas</a></font></div></td>
							</tr>
						</table>
						<table width="353" border="0" cellpadding="0" cellspacing="0">
							<tr class="SEFAZ-TR-Titulo">
								<td height="22">GIA-ITCD</td>
							</tr>                     
							<tr>
								<td height="22" width="250"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="/itc/giaitcd/ViewMenuGIAITCDIncluir.jsp">Incluir GIA-ITCD</a></font></div></td>
							</tr>
							<tr>
								<td height="22" width="250"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_PESQUISAR_GIAITCD, request)%>">Consulta GIA-ITCD </a></font></div></td>
							</tr>
							<tr>
								<td height="22"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_ALTERAR_GIAITCD, request)%>">Alterar GIA-ITCD</a></font></div></td>
							</tr>
							<tr>
								<td height="22" width="250"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_INATIVAR_GIAITCD, request)%>">Inativar GIA-ITCD</a></font></div></td>
							</tr>
							<tr>
								<td height="22" width="250"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_REATIVAR_GIAITCD, request)%>">Reativar - GIA-ITCD</a></font></div></td>
							</tr>
						</table>
						<table width="352" border="0" cellpadding="0" cellspacing="0">
							<tr class="SEFAZ-TR-Titulo">
								<td height="22">Autenticidade</td>
							</tr>
							<tr>
								<td height="22"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_IMPRIMIR_AUTENTICIDADE_GIAITCD, request)%>">Autenticidade</a></font></div></td>
							</tr>
							<tr class="SEFAZ-TR-Titulo">
								<td height="22">Avalia&ccedil;&atilde;o</td>
							</tr>
							<tr>
								<td height="22" width="250">
                        <div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="/itc/generico/avaliacao/ViewMenuAvaliacao.jsp">Avalia&ccedil;&atilde;o</a></font></div>                      </td>
							</tr>
                  </table>
						<table width="351" border="0" cellpadding="0" cellspacing="0">
							<tr class="SEFAZ-TR-Titulo">
								<td height="22">Status</td>
							</tr>
							<tr>
								<td height="22" width="250">
								<div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_ALTERAR_STATUS_GIAITCD, request)%>">Alterar Status manual</a></font></div>                      </td>
							</tr>
						</table>
                                                <table width="351" border="0" cellpadding="0" cellspacing="0">
							<tr class="SEFAZ-TR-Titulo">
								<td height="22">DAR</td>
							</tr>
							<tr>
								<td height="22" width="250">
								<div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_VINCULAR_DAR_GIAITCD, request)%>">Vincular DAR a GIA-ITCD</a></font></div>                      </td>
							</tr>
						</table>
                  <table width="351" border="0" cellpadding="0" cellspacing="0">
							<tr class="SEFAZ-TR-Titulo">
								<td height="22">Reimprimir</td>
							</tr>
							<tr>
								<td height="22" width="250"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_REIMPRIMIR_GIAITCD, request)%>">Reimprimir - GIA-ITCD</a></font></div></td>
							</tr>
							<tr>
								<td height="22" width="250"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_REIMPRIMIR_NOTIFICACAO_RETIFICACAO_GIAITCD, request)%>">Reimprimir - Notifica&ccedil;&atilde;o/Retifica&ccedil;&atilde;o</a></font></div></td>
							</tr>
						</table>                  
                  <table width="351" border="0" cellpadding="0" cellspacing="0">
							<tr class="SEFAZ-TR-Titulo">
								<td height="22">Funcionalidades MODABERTO</td>
							</tr>
							<tr>
								<td height="22" width="250"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="/itc/ViewMenuModAberto.jsp">Menu MODABERTO</a></font></div></td>
							</tr>
						</table>                  						
					</td>
				</tr>
			</table>
			<table width="352" border="0" cellpadding="0" cellspacing="0">
				<tr class="SEFAZ-TR-Titulo"><td height="22">Manuais GIA ITCD</td></tr>
				<tr><td height="22"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="javascript: solicitarAbrirManualArrolamento();">Manual da GIA ITCD Inventário Arrolamento</a></font></div></td></tr>
				<tr><td height="22"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="javascript: solicitarAbrirManualDoacao();">Manual da GIA ITCD Doação / Outros</a></font></div></td></tr>
				<tr><td height="22"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="javascript: solicitarAbrirManualSeparacaoDivorcio();">Manual da GIA ITCD Separação/Divórcio/Partilha</a></font></div></td></tr>
			</table>						
			<p>&nbsp;</p>
		</center>
		<g:mostrarRodape />
	</body>
</html>