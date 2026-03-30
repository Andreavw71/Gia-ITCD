<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/* Vincular DAR a GIA-ITCD
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewVincularDarGiaItcd.jsp
* Criação : Abril de 2008 / Wendell Pereira de Farias
* Revisão : 
* Log : 
*/
--%>
<%@taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page contentType="text/html;charset=windows-1252"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<%@page import="br.gov.mt.sefaz.itc.model.generico.giaitcd.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import="br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD"%>
<%@page import="br.gov.mt.sefaz.itc.util.dominio.DomnStatDar"%>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
	<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
	<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
	<script type="text/javascript">
	
		/*Funcionalidade responsável por validar o formulário.
		Implementado por Wendell Farias
		*/                
		function validarFormulario()
		{
		  if(!verificaCamposW3c(document.form.<%=Form.PARAMETRO_CODIGO_GIA%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_CODIGO_GIA+"'"%>))
		  {
				return false;
		  }
		  
		  if(!verificaCamposW3c(document.form.<%=Form.PARAMETRO_CODIGO_DAR%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_NUMERO+"'"%>))
		  {
				return false;
		  }
		  document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>?<%= Form.BOTAO_CONFIRMAR%>=true';
		  document.form.submit();
		}                
		
		
		function obterArrayBotoes()
		{
			var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR%>;
			return new Array(botao1);
		}
                
                
	</script>
	<jsp:include page="/util/ViewVerificaErro.jsp"/>
	<title><abaco:tituloSistema/></title>
	</head>
	<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>'); verificaErro();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
			<form method="POST" name="form"  action="#" >
			  <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
			   <tr align="right"> 
					<td colspan="2"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
				</tr> 
            <tr class="SEFAZ-TR-Titulo" align="center"> 
					<td colspan="2">Dados da GIA-ITCD</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
            </tr>
				 <tr>
					<td  width="50%" class="SEFAZ-TD-RotuloEntrada">Número da GIA-ITCD:&nbsp;</td>
					<td  width="50%" class="SEFAZ-TD-CampoEntrada">
						  <input name="<%=Form.PARAMETRO_CODIGO_GIA%>" id="<%=Form.PARAMETRO_CODIGO_GIA%>" type="Text" value="<c:out value='${giaITCDDARVo.gia.codigoFormatado}'/>" size="20" maxlength="10"   onBlur=""/><font color="red">*</font>
					</td>
				 </tr>
             <tr>
					<td width="50%" class="SEFAZ-TD-RotuloEntrada">Número do DAR:&nbsp;</td>
               <td width="50%" class="SEFAZ-TD-CampoEntrada">
						<input name="<%=Form.PARAMETRO_CODIGO_DAR%>"  id="<%=Form.PARAMETRO_CODIGO_DAR%>" type="Text" value="<c:out value='${giaITCDDARVo.codigoUofSequencial}'/>" size="20" maxlength="13"   onBlur=""/><font color="red">*</font>
					</td>
				 </tr>             
				<c:if test="${giaITCDDARVo.numeroParcela != 0 && giaITCDDARVo.gia.codigo > 0 }">
					<tr id="<%=Form.PARAMETRO_NUMERO_PARCELA_DAR%>x">
                  <td width="50%" class="SEFAZ-TD-RotuloEntrada">Número da Parcela:&nbsp;</td>
                  <td width="50%" class="SEFAZ-TD-CampoEntrada">
								<input name="<%=Form.PARAMETRO_NUMERO_PARCELA_DAR%>"  id="<%=Form.PARAMETRO_NUMERO_PARCELA_DAR%>" type="Text" value="<c:if test ="${giaITCDDARVo.numeroParcela > 0}"><c:out value='${giaITCDDARVo.numeroParcela}'/></c:if>" size="20" maxlength="10"   onBlur=""/><font color="red">*</font>
						</td>
					</tr>
            </c:if>
				 
				<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
					<tr> 
						<td align="center">&nbsp;</td>
               </tr>  
               <tr>
						<td >
							<div align="center">
								<input name="<%=Form.BOTAO_CONFIRMAR%>"  type="button"  value="Confirmar"  class="SEFAZ-INPUT-Botao"  id="<%=Form.BOTAO_CONFIRMAR%>" onclick="validarFormulario();"/>
								<abaco:botaoCancelarSemMensagem/>
							</div>
						</td>
					</tr>
					<tr>
						<td ><font color="red"><b>* Campos Obrigatórios</b></font></td>
					</tr>
				</table>
                                 
			  </table>    
			  
		</form>
	<g:mostrarRodape/>
  </body>
</html>