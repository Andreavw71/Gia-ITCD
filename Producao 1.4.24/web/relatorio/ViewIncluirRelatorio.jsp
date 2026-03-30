<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.relatorio.pedidorelatorio.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoRelatorio"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
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
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_RELATORIO%>,<%="'"+MensagemErro.VALIDAR_SELECIONAR_TIPO_RELATORIO+"'"%>))
				{
					return false;
				}
				//desabilitarBotoes(obterArrayBotoes());
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=1"%>';
				document.form.submit();
				
				return true;
			}
         
         function obterArrayBotoes()
			{
				var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
				return new Array(botao1);
			}
            
        </script>
        <jsp:include page="/util/ViewVerificaErro.jsp"/>
	<title><abaco:tituloSistema></abaco:tituloSistema></title>
  </head>
   <body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro();">
   <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
       <form method="POST" name="form"  action="<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>">
      <table class="SEFAZ-TABLE-Moldura" cellspacing="1" cellpadding="0" border="0" width="740" align="center">
        <tr align="right">
          <td colspan="4">
            <div id="<%=Form.BOTAO_AJUDA%>"></div>
          </td>
        </tr>
        <tr class="SEFAZ-TR-Titulo" align="center">
          <td colspan="4">Solicitar Relat&oacute;rio</td>
        </tr>
        <tr>
          <td colspan="4">&nbsp;</td>
        </tr>
        <tr align="center">
          <td colspan="1" class="SEFAZ-TD-RotuloEntrada" width="25%">Tipo Relat&oacute;rio:&nbsp;</td>
          <td colspan="3" class="SEFAZ-TD-CampoEntrada" width="75%">
            <abaco:campoSelectDominio ajuda="" classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoRelatorio" name="<%=Form.CAMPO_SELECT_TIPO_RELATORIO%>"
                                      tabIndex="" mostrarSelecione="true" idCampo="<%=Form.CAMPO_SELECT_TIPO_RELATORIO%>"
                                      opcaoSelecionada="${pedidoRelatorioVo.tipoRelatorio.valorCorrente}"></abaco:campoSelectDominio>
            <font color="red">*</font>
          </td>
        </tr>
        <tr>
          <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4" align="center">
            <input type="button" value="<%=Form.TEXTO_BOTAO_SOLICITAR_RELATORIO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR_CLONE%>"
                   onclick="return validaFormulario();"/>
            <abaco:botaoCancelar/>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <font color="red">
              <b>* Campos Obrigat&oacute;rios</b>
            </font>
          </td>
        </tr>
      </table>
       </form>  
   <g:mostrarRodape/>
  </body>
</html>