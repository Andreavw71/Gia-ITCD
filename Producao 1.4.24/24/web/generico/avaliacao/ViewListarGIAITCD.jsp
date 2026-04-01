<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>

<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>

<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.avaliacao.Form"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
   <title><abaco:tituloSistema></abaco:tituloSistema></title>
	<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
	<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
	<script type="text/javascript" language="javascript">
		function mostrarAvaliacao(pCodigoGIAITCD)
		{
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_PESQUISAR_GIAITCD_POR_NUMERO+"=1"%>';
			document.form.<%=Form.CAMPO_CODIGO_GIA%>.value = pCodigoGIAITCD;
			document.form.submit();
		}
	</script>
  </head>
  <body class="SEFAZ-Body">
  	<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
	<center>
	<form name="form" action="" method="POST">
	<input type="hidden" name="<%=Form.CAMPO_CODIGO_GIA%>" id="<%=Form.CAMPO_CODIGO_GIA%>" value="">
	 <!-- resultado da pesquisa -->
    <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
    <c:if test="${not empty avaliacaoBemTributavelVo.collVO}">
        <tr class="SEFAZ-TR-Titulo" align="center"> 
            <td colspan="6">Resultados da Pesquisa</td>
        </tr>
        <tr class="SEFAZ-TR-SubTituloEsq"> 
            <td width="5%" align="center">Nº da GIA</td>
            <td width="20%"  align="center">Tipo de GIA</td>
            <td width="20%" align="center">Tipo de Processo&nbsp;</td>
            <td width="20%" align="center">Nome do Contribuinte </td>
            <td width="20%" align="center">Data  da Avalia&ccedil;&atilde;o</td>
				<td width="15%" align="center">Status da GIA-ITCD</td>
        </tr>	
        <c:forEach var="atual" items="${ avaliacaoBemTributavelVo.collVO}" varStatus="status">
            <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
            <c:if test="${status.count % 2 != 0}">
            <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
            </c:if>
            <tr class="<c:out value="${linhaEstilo}"/>">
                <td align="center" >
						<a href="javascript:mostrarAvaliacao(<c:out value="${atual.bemTributavel.giaITCDVo.codigo}"/>);">
							<c:out value="${atual.bemTributavel.giaITCDVo.codigo}"/></a>
						</td>
                <td align="center" class="SEFAZ-TD-CampoSaida"> <c:out value="${atual.bemTributavel.giaITCDVo.tipoGIA.textoCorrente}"/></td>
                <td align="center" class="SEFAZ-TD-CampoSaida"><c:out value="${atual.bemTributavel.giaITCDVo.naturezaOperacaoVo.tipoProcesso.textoCorrente}"/></td>
                <td align="center" class="SEFAZ-TD-CampoSaida"><c:out value="${atual.bemTributavel.giaITCDVo.responsavelVo.nomeContribuinte}"/></td>
                <td align="center" class="SEFAZ-TD-CampoSaida"><c:out value="${atual.dataAvaliacaoFormatado}"/></td>
					 <td align="center" class="SEFAZ-TD-CampoSaida"><c:out value="${atual.bemTributavel.giaITCDVo.status.statusGIAITCD.textoCorrente}"/></td>
            </tr>
        </c:forEach>
    </c:if>
    </table>
    <!-- FIM: resultado da pesquisa -->  
	 
	<abaco:botaoCancelar/>
	<g:mostrarRodape/>
	</form>
  </center>
  </body>
</html>