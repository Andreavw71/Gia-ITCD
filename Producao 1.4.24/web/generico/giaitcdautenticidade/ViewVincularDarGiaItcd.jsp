<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%--
/* Vincular DAR a GIA-ITCD
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewVincularDarGiaItcd.jsp
* Criação : Abril de 2008 / Wendell Pereira de Farias
* Revisão : 
* Log : 
*/
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.giaitcd.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD"%>
<%
	pageContext.setAttribute("PENDENTE_DE_PROTOCOLO", new Integer(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO));
	pageContext.setAttribute("PROTOCOLADO", new Integer(DomnStatusGIAITCD.PROTOCOLADO));
	pageContext.setAttribute("QUITADO", new Integer(DomnStatusGIAITCD.QUITADO));
	pageContext.setAttribute("INATIVO", new Integer(DomnStatusGIAITCD.INATIVO));
	pageContext.setAttribute("AVALIADO", new Integer(DomnStatusGIAITCD.AVALIADO));
	pageContext.setAttribute("ISENTO", new Integer(DomnStatusGIAITCD.ISENTO));
	pageContext.setAttribute("NAO_OCORRENCIA_DE_FATO_GERADOR", new Integer(DomnStatusGIAITCD.NAO_OCORRENCIA_DE_FATO_GERADOR));
	pageContext.setAttribute("PROTOCOLO_AUTORIZADO_PELA_GIOR", new Integer(DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR));
	pageContext.setAttribute("NOTIFICADO", new Integer(DomnStatusGIAITCD.NOTIFICADO));
	pageContext.setAttribute("RETIFICADO", new Integer(DomnStatusGIAITCD.RETIFICADO));
	pageContext.setAttribute("IMPUGNADO", new Integer(DomnStatusGIAITCD.IMPUGNADO));
	pageContext.setAttribute("PARCELADO", new Integer(DomnStatusGIAITCD.PARCELADO));
	pageContext.setAttribute("SEGUNDA_RETIFICACAO", new Integer(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO));
	pageContext.setAttribute("RATIFICADO", new Integer(DomnStatusGIAITCD.RATIFICADO));
	pageContext.setAttribute("PARA_INSCRICAO_EM_DIVIDA_ATIVA", new Integer(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA));
	pageContext.setAttribute("REMETIDO_PARA_DIVIDA_ATIVA", new Integer(DomnStatusGIAITCD.REMETIDO_PARA_DIVIDA_ATIVA));
%>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
	<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
	<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
	<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
	<script type="text/javascript">
	
                function validarFormulario()
                {
                    /*FE1 Valida o número da GIAITCD */
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
                
        	function solicitarExibirPDF()
		{					
					//desabilitarBotoes(obterArrayBotoes());
					document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)%>?<%= Form.BOTAO_IMPRIMIR%>=true';
					document.form.target = '_blank';
					document.form.submit();
					return true;
		}		
		function obterArrayBotoes()
		{
			var botao1 = document.form.<%=Form.BOTAO_IMPRIMIR%>;
			return new Array(botao1);
		}
                
                function habilitaCampoNumeroParcelasRetornoServlet()
                {
                    var tipo = buscarTipoNavegador();
                    var controle=parseInt(<c:out value='${giaITCDDARVo.darEmitido.numrParcela}'/>);
                    if(parseInt(controle)<0)
                    {
                        document.getElementById('<%=Form.PARAMETRO_NUMERO_PARCELA_DAR%>x').style.display = tipo;//o caracter x é utilizado para diferenciar a <tr> de <td>.
                        document.getElementById('<%=Form.PARAMETRO_NUMERO_PARCELA_DAR%>').focus();
                    }
                    else
                    {
                        document.getElementById('<%=Form.PARAMETRO_NUMERO_PARCELA_DAR%>x').style.display = "none";//o caracter x é utilizado para diferenciar a <tr> de <td>.
                    }
                }
	</script>
	<jsp:include page="/util/ViewVerificaErro.jsp"/>
	<title><abaco:tituloSistema/></title>
	</head>
	<body class="SEFAZ-Body" onload="habilitaCampoNumeroParcelasRetornoServlet();verificaErro();">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
			<form method="POST" name="form"  action="#" >
			  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
				 <tr class="SEFAZ-TR-Titulo" align="center"> 
					<td colspan="4">Dados da GIA-ITCD</td>
				 </tr>
                                 <tr>
                                         <td colspan="2" align="right" class="SEFAZ-TD-RotuloEntrada">&nbsp;</td>
										 <td width="135" class="SEFAZ-TD-RotuloEntrada">&nbsp;</td>
										 <td width="302" class="SEFAZ-TD-RotuloEntrada">&nbsp;</td>
                                </tr>
				 <tr>
                                         <td  colspan="2" align="right" class="SEFAZ-TD-RotuloEntrada">Número da GIA-ITCD:&nbsp;</td>
					<td  class="SEFAZ-TD-RotuloEntrada"><span class="SEFAZ-TD-CampoEntrada">
					  <input name="<%=Form.PARAMETRO_CODIGO_GIA%>" id==&quot;<%=Form.PARAMETRO_CODIGO_GIA%>&quot; type="Text" value="<c:out value='${giaITCDDARVo.gia.codigoFormatado}'/>" size="20" maxlength="10"   onBlur=""/>
					</span></td>
					<td  class="SEFAZ-TD-CampoEntrada">&nbsp;</td>
				 </tr>
                                <tr>
                                        <td colspan="2" align="right" class="SEFAZ-TD-RotuloEntrada">Número do DAR:&nbsp;</td>
                                        <td  class="SEFAZ-TD-RotuloEntrada"><span class="SEFAZ-TD-CampoEntrada">
                                          <input name="<%=Form.PARAMETRO_CODIGO_DAR%>"  id="<%=Form.PARAMETRO_CODIGO_DAR%>" type="Text" value="<c:out value='${giaITCDDARVo.codigoFormatado}'/>" size="20" maxlength="10"   onBlur=""/>
                                        </span></td>
					<td  class="SEFAZ-TD-CampoEntrada">&nbsp;</td>
				 </tr>
                                 <tr id="<%=Form.PARAMETRO_NUMERO_PARCELA_DAR%>x">
                                        <td colspan="2" align="right" class="SEFAZ-TD-RotuloEntrada">Número de Parcelas:&nbsp;</td>
                                        <td class="SEFAZ-TD-RotuloEntrada"><span class="SEFAZ-TD-CampoEntrada">
                                          <input name="<%=Form.PARAMETRO_NUMERO_PARCELA_DAR%>"  id="<%=Form.PARAMETRO_NUMERO_PARCELA_DAR%>" type="Text" value="<c:if test ="${giaITCDDARVo.darEmitido.numrParcela}>0"> <c:out value='${giaITCDDARVo.darEmitido.numrParcela}'/> </c:if>" size="20" maxlength="10"   onBlur=""/>
                                        </span></td>
					<td class="SEFAZ-TD-CampoEntrada">&nbsp;</td>
				 </tr>
                                 <tr>                                     
                                     <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
                                     <tr> 
                                            <td align="center">&nbsp;</td>
                                     </tr>  
                                            <tr> 
                                                    <td colspan="3" >
                                                            <div align="center">
                                                              <input name="<%=Form.BOTAO_CONFIRMAR%>"  type="button"  value="Confirmar"  class="SEFAZ-INPUT-Botao"  id="<%=Form.BOTAO_CONFIRMAR%>" onclick="validarFormulario();"/>
                                                                    <abaco:botaoCancelarSemMensagem/>
                                                            </div>                                                    </td>
                                            </tr>			
                                   </table>
                                 </tr>
			  </table>    
			  
		</form>
	<g:mostrarRodape/>
  </body>
</html>