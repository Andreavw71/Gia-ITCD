<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewCadastrarGIAITCDDoacao.jsp
* Criação : Dezembro de 2007 / Rogério Sanches de Oliveira
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
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
		
			 function validaFormulario()
            {
					var	campoSenha = document.getElementById('<%=Form.CAMPO_SENHA%>').value;
					var campoConfirmaSenha = document.getElementById('<%=Form.CAMPO_CONFIRMACAO_SENHA%>').value;

                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SENHA%>,<%="'"+MensagemErro.VALIDAR_SENHA_GIA+"'"%>))
                {
                    return false;
                }
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_CONFIRMACAO_SENHA%>, <%="'"+MensagemErro.VALIDAR_CONFIRMACAO_SENHA+"'"%>))
                {
                    return false;
                 }
					  if(campoSenha != campoConfirmaSenha)
					  {
							alert(<%="'"+MensagemErro.VALIDAR_SENHA_INFORMADA_INVALIDA+"'"%>);
							return false;
					  }
//	               desabilitarBotoes(obterArrayBotoes());
						document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CADASTRAR_SENHA+"=1"%>';
						document.form.submit();
					   return true;
            }

		</script>
		<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
	</head>
	<body class="SEFAZ-Body" onload="verificaErro();log();">
	<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
	<jsp:include page="/util/ViewVerificaErro.jsp"/>
	<form method="POST" name="form"  action="#">
  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
	    <tr class="SEFAZ-TR-Titulo" align="center"> 
            <td colspan="2">Cadastrar Senha </td>
    </tr>
    <tr id="ocultarSenha"> 
      <td class="SEFAZ-TD-RotuloEntrada" width="278">Senha:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" width="462">
								<input type="password" name="<%=Form.CAMPO_SENHA%>" 
										id="<%=Form.CAMPO_SENHA%>"
										class="SEFAZ-INPUT-Text" 
										maxlength="25"
										size="25"
										value="<c:out value="${giaITCDVo.senha}"/>"><font color="red">*</font>
				</td>
    </tr>
	<tr> 
      <td  class="SEFAZ-TD-RotuloEntrada" width="278">Digite a Senha Novamente:&nbsp;</td>
      <td class="SEFAZ-TD-CampoEntrada" width="462"> 
					<input type="password" name="<%=Form.CAMPO_CONFIRMACAO_SENHA%>" 
										id="<%=Form.CAMPO_CONFIRMACAO_SENHA%>"
										class="SEFAZ-INPUT-Text" 
										maxlength="25"
										size="25"
										value="<c:out value="${giaITCDVo.senha}"/>"><font color="red">*</font>
   	</td>
    </tr>
    <tr> 
      <td width="278">&nbsp;</td>
      <td width="462">&nbsp;</td>
    </tr>
    <tr> 
      <td colspan="2" align="center"> 
         <input type="button" value="   Confirmar   " class="SEFAZ-INPUT-Botao" name="btnIncluir" onClick="return validaFormulario();"/>
			<abaco:botaoCancelar></abaco:botaoCancelar>
		</td>	
    </tr>
	 <tr>
		<td><font color="red"><b>* Campos Obrigatórios</b></font></td>
	 </tr>
    <tr> 
      <td colspan="2">&nbsp;</td>
    </tr>
	</table>
	<table width="740" border="0">
            <tr>
                <td>
                    <div align="center"> 
                        <script type="text/javascript" language="javascript">gerarSpanMesgBotao();</script>
                    </div>
                <td>
            </tr>
   </table>
	<abaco:log/>
 </form>
	 <center></center>
    <g:mostrarRodape/>
    </body>
</html>

