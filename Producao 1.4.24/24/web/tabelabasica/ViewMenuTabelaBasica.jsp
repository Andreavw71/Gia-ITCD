<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso ? Sefaz-MT
* Arquivo : ViewMenuTabelaBasica.jsp
* Criação : Setembro de 2807 / Anderson Boehler Iglesias Araujo e Elizabeth Barbosa
* Revisão : 
* Log : 
*/
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" %>
<%--AcessoWEB Valida--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<HTML>
	<HEAD>
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
		<link rel="stylesheet" href="/estilos/SefazEstilos.css" type="text/css">
		<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
	</HEAD>
	<BODY class="SEFAZ-Body">
	  <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="Tabelas Básicas"/>
		<CENTER>
				<table border="0" cellspacing="0" align="center">
					<tr> 
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt=""></td>
						<td align="left" width="280"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="/itc/tabelabasica/bem/ViewMenuBem.jsp">Bens</a></font></td>
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt=""></td>
						<td align="left" width="280"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="/itc/tabelabasica/construcao/ViewMenuConstrucao.jsp">Construção</a></font></td>
					</tr>
					<tr> 
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt=""></td>
						<td align="left" width="280"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="/itc/tabelabasica/cultura/ViewMenuCultura.jsp">Cultura</a></font></td>
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt=""></td>
						<td align="left" width="280"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_INCLUIR_MULTA_DE_MORA, request)%>">Incluir Multa de Mora</a></font></td>
					</tr>
					<tr> 
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt=""></td>
						<td align="left" width="280"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="/itc/tabelabasica/rebanho/ViewMenuRebanho.jsp">Rebanho</a></font></td>
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt=""></td>
						<td align="left" width="280"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="/itc/tabelabasica/parametrosdalegislacao/ViewMenuParametrosLegislacao.jsp">Parâmetros de Legislação</a></font></td>
					</tr>
					<tr> 
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt=""></td>
						<td align="left" width="280"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="/itc/tabelabasica/naturezadaoperacao/ViewMenuNaturezaOperacao.jsp">Natureza da Operação</a></font></td>
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt=""></td>
						<td align="left" width="280"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="/itc/tabelabasica/ajuda/ViewMenuAjuda.jsp"-->Ajuda</a></font></td>
					</tr>
					<tr> 
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt=""></td>
						<td align="left" width="280"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="/itc/tabelabasica/configuracaogerencialparametros/ViewMenuConfiguracaoGerencialParametros.jsp">Configuração Gerencial de Parâmetros</a></font></td>
					</tr>
					<tr> 
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt=""></td>
						<td align="left" width="280"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="/itc/tabelabasica/benfeitoria/ViewMenuBenfeitoria.jsp">Benfeitorias</a></font></td>
					</tr>
               <tr> 
						<td><img src="/imagens/ponto.gif" width="10" height="10" alt=""></td>
						<td align="left" width="280"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="/itc/tabelabasica/iptuprefeitura/ViewMenuIptuPrefeitura.jsp">IPTU Prefeitura</a></font></td>
					</tr>
				</table>
				<br/>
				<table width="740" border="0">
					<tr>
						<td>
							<div align="center"> 
								<jsp:expression>JspUtil.getBotaoRetornoMenu(request, "Voltar")</jsp:expression>
							</div>
						<td>
					</tr>
				</table>
		</CENTER>
		<br/>
		<g:mostrarRodape />
	</BODY>
</HTML>