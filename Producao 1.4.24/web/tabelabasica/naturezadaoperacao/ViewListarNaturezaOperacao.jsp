<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewListarNaturezaOperacao.jsp
* Criação : Outubro de 2007 / Rogério Sanches de Oliveira
* Revisão : MAR2008 - Wendell Pereira de Farias
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
        <META HTTP-EQUIV=Cache-Control content=no-store>
        <META HTTP-EQUIV=Pragma content=no-cache>
        <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
        <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
        <script type="text/javascript" language="javascript">
			
            function consultarNaturezaOperacao(codigoNaturezaoperacao)
            {
                document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_NATUREZA_OPERACAO+"="%>'+codigoNaturezaoperacao;
                document.form.submit();
            }
    
            function obterArrayBotoes()
            {
                var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
                return new Array(botao1);
            }
        </script>
		
        <jsp:include page="/util/ViewVerificaErro.jsp"/>
        <title><abaco:tituloSistema></abaco:tituloSistema></title>
    </head>
      <body class="SEFAZ-Body"  onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro();">
        <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
            <form method="POST" action="#" name="form">
              <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                     <tr align="right">
                            <td colspan="8"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                     </tr>
                     <tr class="SEFAZ-TR-Titulo" align="center"> 
                        <td colspan="8">Resultados da Pesquisa</td>
                     </tr>
                     <tr class="SEFAZ-TR-SubTituloEsq">  
                        <td width="2%" align="center">C&oacute;digo</td>
                        <td width="15%">Tipo GIA </td>
                        <td width="15%">Tipo Processo </td>
                        <td width="20%">Natureza Opera&ccedil;&atilde;o </td>
								<td width="12%" align="center">Isenção em Lei</td>
                        <td width="20%" align="center">Base de C&aacute;lculo Reduzido </td>
                        <td width="15%" align="center">Percentual</td>
                        <td width="5%" align="center">Status</td>
                     </tr>
                    <c:set var="funcionalidadePesquisarNatureza" ><%=JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_NATUREZA_DA_OPERACAO)%></c:set>
                    <c:if test="${not empty naturezaOperacaoVo.collVO}" >
                        <c:forEach var="naturezaOperacaoVo" items="${naturezaOperacaoVo.collVO}" varStatus="status">
                            <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
                            <c:if test="${status.count % 2 != 0}">
                                <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
                            </c:if>
                            <tr class="<c:out value="${linhaEstilo}"/>">
                                <td width="<c:if test="${funcionalidadePesquisarNatureza}">10%</c:if><c:if test="${!funcionalidadePesquisarNatureza}">10%</c:if>" align="center">
                                    <c:if test="${funcionalidadePesquisarNatureza}">
                                        <c:out value="${naturezaOperacaoVo.codigo}"/>
                                    </c:if>
                                    <c:if test="${!funcionalidadePesquisarNatureza}">
                                        <a href="javascript: consultarNaturezaOperacao(<c:out value="${naturezaOperacaoVo.codigo}"/>)"><c:out value="${naturezaOperacaoVo.codigo}"/></a>
                                    </c:if>
                                </td>
                                <td align="left"	><c:out value="${naturezaOperacaoVo.tipoGIA.textoCorrente}"/></td>
                                <td align="left"><c:out value="${naturezaOperacaoVo.tipoProcesso.textoCorrente}"/></td>
                                <td align="left"><c:out value="${naturezaOperacaoVo.descricaoNaturezaOperacao}"/></td>
										  <td align="center"><c:out value="${naturezaOperacaoVo.flagIsencaoPrevistaLeiFormatada}"/></td>
                                <td align="center"><c:out value="${naturezaOperacaoVo.tipoBaseCalculo.textoCorrente}"/></td>
                                <td align="center"><c:out value="${naturezaOperacaoVo.percentualBaseCalculoFormatada}"/></td>
                                <td align="left"><c:out value="${naturezaOperacaoVo.statusNaturezaOperacao.textoCorrente}"/></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <tr> 
                        <td colspan="7">&nbsp;</td>
                    </tr>
                    <tr> 
                        <td colspan="7" align="center"> 							
                            <abaco:botaoCancelarSemMensagem/>
                        </td>
                    </tr>
              </table>
	  </form>
      <g:mostrarRodape/>
  </body>
</html>