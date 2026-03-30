<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewListarCultura.jsp
* Criação : Outubro de 2007 / Elizabeth Barbosa Moreira
* Revisão: Marlo Einchenberg Motta
* Data revisão: 02/11/2007
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.cultura.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
        <META HTTP-EQUIV=Cache-Control content=no-store>
        <META HTTP-EQUIV=Pragma content=no-cache>
        <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
        <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
        <script type="text/javascript" language="javascript">
            function consultarCultura(codigoCultura)
            {
                document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.CAMPO_CODIGO_CULTURA+"="%>'+codigoCultura;
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
    <body>
        <c:if test="${not empty culturaVo.collVO}">
          <table  class="SEFAZ-TABLE-Moldura" cellspacing="1" cellpadding="0" border="0" width="740" align="center">
             <tr>
                <td colspan="3">&nbsp;</td>
             </tr>
             <tr class="SEFAZ-TR-Titulo" align="center"> 
                <td colspan="3">Resultados da Pesquisa</td>
             </tr>
             <tr class="SEFAZ-TR-SubTituloEsq"> 
                <td width="136" align="center">Código</td>
                <td>Descrição do Cultura</td>
                <td width="64" align="center">Status</td>
             </tr>
             <%
                if(JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_CULTURA))
                {
                %>				
                    <c:forEach var="culturaTempVo" items="${culturaVo.collVO}"  varStatus="status">
                         <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
                            <c:if test="${status.count % 2 != 0}">
                                <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
                            </c:if>
                             <tr class="<c:out value="${linhaEstilo}"/>"> 
                                <td width="136" align="center"><c:out value="${culturaTempVo.codigo}"/></td>
                                <td align="left"><c:out value="${culturaTempVo.descricaoCultura}"/></td>
                                <td align="center"><c:out value="${culturaTempVo.statusCultura.textoCorrente}"/></td>
                             </tr>
                     </c:forEach>
                 <%
                }
                else
                {
                %>				
                    <c:forEach var="culturaTempVo" items="${culturaVo.collVO}"  varStatus="status">
                         <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
                         <c:if test="${status.count % 2 != 0}">
                            <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
                        </c:if>
                         <tr class="<c:out value="${linhaEstilo}"/>"> 
                            <td width="136" align="center"><a href="javascript: consultarCultura(<c:out value="${culturaTempVo.codigo}"/>)"><c:out value="${culturaTempVo.codigo}"/></a></td>
                            <td align="left"><c:out value="${culturaTempVo.descricaoCultura}"/></td>
                            <td align="center"><c:out value="${culturaTempVo.statusCultura.textoCorrente}"/></td>
                         </tr>
                     </c:forEach>			 
                <%
                }			
                %>
            </table>
        </c:if>
  </body>
</html>