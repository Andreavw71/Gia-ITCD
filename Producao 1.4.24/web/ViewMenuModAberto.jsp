<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewMenuModAberto.jsp
* Criação : Setembro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log : 
*/
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" %>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>

<script language="javascript" type="text/javascript">

	function solicitarAbrirManualArrolamento()
	{
		window.open('https://www5.sefaz.mt.gov.br/documents/d/sefaz/manual-da-gia-itcd-e-inventario_arrolamento-1');
		document.form.submit();				
		document.form.target = '_self';
	}
	
	function solicitarAbrirManualDoacao()
	{
		window.open('https://www5.sefaz.mt.gov.br/documents/d/sefaz/manual-da-gia-itcd-e-doacao_outros-1');
		document.form.submit();				
		document.form.target = '_self';
	}	

	function solicitarAbrirManualSeparacaoDivorcio()
	{
		window.open('https://www5.sefaz.mt.gov.br/documents/d/sefaz/manual-da-gia-itcd-e-separacao_divorcio_partilha-1');
		document.form.submit();				
		document.form.target = '_self';
	}	
	function solicitarAbrirPortalConhecimento()
	{
		window.open('http://www.portaldoconhecimento.mt.gov.br/sobre-o-itcd');
		document.form.submit();				
		document.form.target = '_self';
	}	
</script>

<style>
   li{margin-top: 8px;}
   ul.sem_marcador {list-style-type: none;}
   ol.sem_marcador {list-style-type: none;}
</style>

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
			<table width="381" border="0">
				<tr>
					<td width="375" align="left" valign="top">
                  <table width="352" border="0" cellpadding="0" cellspacing="0">
							<tr class="SEFAZ-TR-Titulo"><td height="22">Manuais</td></tr>
							<tr><td height="22"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="javascript: solicitarAbrirManualArrolamento();">Manual da GIA-ITCD Inventário Arrolamento</a></font></div></td></tr>
							<tr><td height="22"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="javascript: solicitarAbrirManualDoacao();">Manual da GIA-ITCD Doação / Outros</a></font></div></td></tr>
							<tr><td height="22"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="javascript: solicitarAbrirManualSeparacaoDivorcio();">Manual da GIA-ITCD Separação/Divórcio/Partilha</a></font></div></td></tr>
                    <!--
                     <tr><td height="22"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="javascript: solicitarAbrirPortalConhecimento();">Portal do conhecimento - ITCD</a></font></div></td></tr>
                    --> 
                  </table>	
						<table width="353" border="0" cellpadding="0" cellspacing="0">
							<tr class="SEFAZ-TR-Titulo">
								<td height="22">GIA-ITCD</td>
							</tr>
                     <tr>
                        <td>
                            <ol>
                              <li>
                                 <font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="/itc/giaitcd/ViewMenuGIAITCDIncluir.jsp">Incluir GIA-ITCD</a></font>
                                 
                                 <ul class="sem_marcador">
                                    <li>
                                       <font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=JspUtil.getContexto(request)%>generico/giaitcd/alterar">Alterar GIA-ITCD</a></font>
                                    </li>
                                     <li>
                                      <font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=JspUtil.getContexto(request)%>generico/giaitcd/pesquisar">Consulta GIA-ITCD </a></font>
                                    </li>
                                 </ul>
                                 
                              </li>
                               <li>
                                 <font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=JspUtil.getContexto(request)%>generico/giaitcd/pesquisarreimprimir">Imprimir GIA-ITCD</a></font>
                              </li>
                               <li>
                                 <font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=JspUtil.getContexto(request)%>generico/giaitcd/protocolo">Preencher Processo</a></font>
                              </li>
                               <li>
                                 <font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=JspUtil.getContexto(request)%>generico/giaitcd/processo/validar">Validar Processo</a></font>
                              </li>
                              <li>
                                 <font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="/eprocess/processo/consulta/andamentoprocesso/consultar">Consultar Processo</a></font>
                              </li>
                               <li>
                                 <font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=JspUtil.getContexto(request)%>generico/giaitcd/imprimirdardeclaracao">Imprimir DAR/Declarações</a></font>
                              </li>
                           </ol>
                        </td>
                     </tr>
						</table>
                  
                  <table width="352" border="0" cellpadding="0" cellspacing="0">
							<tr class="SEFAZ-TR-Titulo"><td height="22">Autenticidade</td></tr>
							<tr><td height="22"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=JspUtil.getContexto(request)%>giaitcd/autenticidade/imprimir">Autenticidade</a></font></div></td></tr>
                  </table>											
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</center>
		<g:mostrarRodape />
	</body>
</html>