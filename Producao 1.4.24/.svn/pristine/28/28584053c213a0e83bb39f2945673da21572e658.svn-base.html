<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewIncluirAvaliacaoBem.jsp
* Criação : Janeiro de 2008 / Elizabeth Barbosa Moreira
* Revisão : 
* Log : 
*/
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" %>
<%--AcessoWEB Valida--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.avaliacao.Form"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<%
    pageContext.setAttribute("SIM", new Integer(DomnSimNao.SIM));
    pageContext.setAttribute("NAO", new Integer(DomnSimNao.NAO));
	 pageContext.setAttribute("INVENTARIO_ARROLAMENTO", new Integer(DomnTipoProcesso.INVENTARIO_ARROLAMENTO));
	 pageContext.setAttribute("DOACAO", new Integer(DomnTipoProcesso.DOACAO));
	 String permissaoAtual = JspUtil.getCodigoPermissaoOriginal(request);
	 String permissaoAlterar = FormAcesso.CODIGO_ALTERAR_AVALIACAO_GIAITCD;
	 String msgDataAvaliacao = null;
	 if(permissaoAtual.equals(permissaoAlterar))
	 {
	   msgDataAvaliacao = "alteração";
	 }
	 else
	 {
	   msgDataAvaliacao = "cadastro";
	 }
	 pageContext.setAttribute("msgDataAvaliacao", msgDataAvaliacao);
%>
<HTML>
	<HEAD>
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
			<script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
			<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
			<script type="text/javascript">
			
			/**
			  * Esta função valida o formulário no momento da envio dos dados
			  */
			function validaFormulario()
			{
				var formAction = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_INCLUIR+"=1"%>';
			
				var msgValidaValorAvaliacao = null;
				var msgValidaValorAvaliacaoZero = null;
				var msgValidaDataAvaliacao = null;
				var msgValidaObservacao = <%="'"+MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_OBSERVACAO+"'"%>;
			
				var campoValorAvaliacao = document.form.<%=Form.CAMPO_VALOR_AVALIACAO%>;
				var campoDataAvaliacao = document.form.<%=Form.CAMPO_DATA_AVALIACAO%>;
				var campoObservacao = document.form.<%=Form.CAMPO_OBSERVACAO%>;
            
            var valorDeclarado = '<c:out value="${bemTributavelVo.valorMercadoInformadoFormatado}"/>';
            var valorAvaliacao = document.form.<%=Form.CAMPO_VALOR_AVALIACAO%>.value;
            
				/** é uma avalição judicial? */
				if(document.form.<%=Form.CHECKEBOX_AVALIACAO_JUDICIAL%>.value == '<%=DomnSimNao.SIM%>')
				{
					msgValidaValorAvaliacao = <%="'"+MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_VALOR_AVALIACAO+"'"%>;
					msgValidaValorAvaliacaoZero = <%="'"+MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_JUDICIAL_VALOR_MAIOR_ZERO+"'"%>;
					msgValidaDataAvaliacao = <%="'"+MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_ALTERAR_DATA_JUDICIAL+"'"%>;
				}
				else
				{/** não é uma avaliação judicial */
					msgValidaValorAvaliacao = <%="'"+MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_VALOR+"'"%>;
					msgValidaValorAvaliacaoZero = <%="'"+MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_VALOR_MAIOR_ZERO+"'"%>;
					msgValidaDataAvaliacao = <%="'"+MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_ALTERAR_DATA+"'"%>;
				}

				/**<-- A partir daqui sem scriplets no meu jscript*/

				/** o valor da avaliação judicial foi informada? */
				if(!verificaCamposW3c(campoValorAvaliacao, msgValidaValorAvaliacao))
				 {
					  campoValorAvaliacao.focus();
					  return false;
				 }
				/** o valor da avaliação judicial é maior que zero? */
				if(!verificaCamposW3cByValor(campoValorAvaliacao, '0,00', msgValidaValorAvaliacaoZero))
				 {
					  campoValorAvaliacao.focus();
					  return false;
				 }

				/** a data da avaliação foi informada? */				
				if(!verificaCamposW3c(campoDataAvaliacao, msgValidaDataAvaliacao))
				{
						campoDataAvaliacao.focus();
						return false;
				}
				
            valorAvaliacao = replaceAll(valorAvaliacao, ".", "");
            valorAvaliacao = replaceAll(valorAvaliacao, ",", ".");
            
            valorDeclarado = replaceAll(valorDeclarado, ".", "");
            valorDeclarado = replaceAll(valorDeclarado, ",", ".");
            
            if( parseFloat(valorAvaliacao) < parseFloat(valorDeclarado)  ){
               if( !confirm("Valor da Avaliação menor que o Valor Arbitrado. Deseja continuar?") ){
                  return false
               }
            }
            
				desabilitarBotoes(obterArrayBotoes());
				document.form.action = formAction;
				document.form.submit();
			}
		
			/**
			  * esta função executa um submit para a servlet de pesquisa de Servidor Sefaz
			  */
			function incluirServidor()
			{	
				document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_INCLUIR_SERVIDOR+"=1"%>';
				document.form.submit();
			}
			
			function habilitarIsencao()
			{
				document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_GIA_ITCD_ISENTA+"=1"%>';
				document.form.submit();
			}
			
			/**
			  *  Esta função executa um submit para a servlet de controle da funcionalidade
			  *  indicando que um servidor deve ser removido da lista de "servidores avaliadores"
			  */
			function excluirServidor(indice)
			{	
				if(confirm('<%=MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_EXCLUIR_SERVIDOR%>'))
				 {
					  desabilitarBotoes(obterArrayBotoes());
					  document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_EXCLUIR_SERVIDOR+"="%>'+indice;
						document.form.submit();
						return true;
					}
					else
               {
                    return false;
               }
           }
			
			function obterArrayBotoes()
			{
				 var botao = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
				 return new Array(botao);
			}
			
		  function habilitarCampo()
			{
				campo = document.form.<%=Form.CHECKEBOX_AVALIACAO_JUDICIAL%>;
				if(campo.value == '<%=DomnSimNao.SIM%>')
				{
				  document.getElementById("idValorAvaliacao").innerHTML = "Valor da Avaliação Judicial:&nbsp; ";
				  document.getElementById("idDataAvaliacao").innerHTML = "Data da Avaliação Judicial:&nbsp; ";
				  
				}
				else
				{
				  document.getElementById("idValorAvaliacao").innerHTML = "Valor da Avaliação:&nbsp; ";
				  document.getElementById("idDataAvaliacao").innerHTML = "Data da Avaliação:&nbsp; ";
				}
			}
			
			function avaliacaoIsenta()
			{
				<c:if test="${bemTributavelVo.giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == INVENTARIO_ARROLAMENTO}" >
					<c:choose>
						<c:when test="${bemTributavelVo.isencaoPrevista.valorCorrente == NAO}">
							document.getElementById('trIsencaoPrevista').style.display = 'none';
						</c:when>
						<c:otherwise>
							document.getElementById('trIsencaoPrevista').style.display = '';
							<c:choose>
								<c:when test="${avaliacaoBemTributavelVo.avaliacaoIsenta}">
									document.getElementById('<%=Form.CAMPO_VALOR_AVALIACAO%>').disabled = 'disabled';
									document.getElementById('tdValorAvaliacao').className = 'SEFAZ-TD-CampoSaida';									
									document.getElementById('tdLabelValorAvaliacao').className = 'SEFAZ-TD-RotuloSaida';
								</c:when>
								<c:otherwise>
									document.getElementById('<%=Form.CAMPO_VALOR_AVALIACAO%>').disabled = '';
									document.getElementById('tdValorAvaliacao').className = 'SEFAZ-TD-CampoEntrada';
									document.getElementById('tdLabelValorAvaliacao').className = 'SEFAZ-TD-RotuloEntrada';
								</c:otherwise>
							</c:choose>								
						</c:otherwise>
					</c:choose>				
				</c:if>
				<c:if test="${bemTributavelVo.giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == DOACAO}">
					<c:if test="${bemTributavelVo.giaITCDVo.naturezaOperacaoVo.flagIsencaoPrevistaLei.valorCorrente == SIM}">
						<c:choose>
							<c:when test="${bemTributavelVo.isencaoPrevista.valorCorrente == NAO}">
								document.getElementById('trIsencaoPrevista').style.display = 'none';
							</c:when>
							<c:otherwise>
								document.getElementById('trIsencaoPrevista').style.display = '';
								<c:choose>
									<c:when test="${avaliacaoBemTributavelVo.avaliacaoIsenta}">
										document.getElementById('<%=Form.CAMPO_VALOR_AVALIACAO%>').disabled = 'disabled';
										document.getElementById('tdValorAvaliacao').className = 'SEFAZ-TD-CampoSaida';									
										document.getElementById('tdLabelValorAvaliacao').className = 'SEFAZ-TD-RotuloSaida';
									</c:when>
									<c:otherwise>
										document.getElementById('<%=Form.CAMPO_VALOR_AVALIACAO%>').disabled = '';
										document.getElementById('tdValorAvaliacao').className = 'SEFAZ-TD-CampoEntrada';
										document.getElementById('tdLabelValorAvaliacao').className = 'SEFAZ-TD-RotuloEntrada';
									</c:otherwise>
								</c:choose>								
							</c:otherwise>
						</c:choose>								
					</c:if>
				</c:if>
			}
			
			function desabilitarCampo()
			{
				document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_PESQUISAR_VALOR_AVALIACAO+"=1"%>';
				document.form.submit();
			}
			
		</script>
		<link rel="stylesheet" href="/estilos/SefazEstilos.css" type="text/css">
		<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
		<jsp:include page="/util/ViewVerificaErro.jsp"/>
		<BODY class="SEFAZ-Body" onload="habilitarCampo();verificaErro();avaliacaoIsenta();">
	  <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
	  <CENTER>
		<form method="POST" name="form"  onsubmit="javascript:return validaForm(this)" action="#">
			<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
			<tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="4">Dados da Avalia&ccedil;&atilde;o do Bem </td>
			</tr>
         <c:if test="${bemTributavelVo.bemVo.classificacaoTipoBem.valorCorrente == 1 && bemTributavelVo.fichaImovelVo.valorPercentualTransmitido > 0}">
            <tr>
                <td class="SEFAZ-TD-CampoSaida" colspan="4">
                   <div align="center">
                     <img src="/imagens/warning2.png" alt="" width="16" height="16"/><font color="red">Valor da Avaliação corresponde o Valor do Percentual transmitido do Imóvel.</font>
                   </div>
                </td>
            </tr>
         </c:if>
			<tr>
				<td width="337" class="SEFAZ-TD-RotuloEntrada" colspan="2">Avaliação Judicial:&nbsp;</td>
				<td width="180" class="SEFAZ-TD-CampoEntrada" colspan="2">
					<abaco:campoSelectDominio ajuda="" classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" name="<%=Form.CHECKEBOX_AVALIACAO_JUDICIAL%>" tabIndex="" idCampo="<%=Form.CHECKEBOX_AVALIACAO_JUDICIAL%>" mostrarSelecione="false" valorDefault="<%=DomnSimNao.NAO%>" onChange="habilitarCampo();" opcaoSelecionada="${avaliacaoBemTributavelVo.avaliacaoJudicial.valorCorrente}"></abaco:campoSelectDominio>
				</td>
			</tr>
          <c:if test="${bemTributavelVo.bemVo.classificacaoTipoBem.valorCorrente == 1 && bemTributavelVo.fichaImovelVo.valorPercentualTransmitido > 0}">
            <tr>
               <td class="SEFAZ-TD-RotuloSaida" colspan="2">Percentual transmitido do Imóvel:&nbsp;</td>
               <td class="SEFAZ-TD-CampoSaida" colspan="2"><c:out value="${bemTributavelVo.fichaImovelVo.valorPercentualTransmitidoFormatado}"/>&nbsp;%</td>  
            </tr>
         </c:if>
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" colspan="2" >Valor Arbitrado:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" colspan="2" ><c:out value="${bemTributavelVo.valorMercadoInformadoFormatado}"/></td>
			</tr>   
         <tr>
				<td class="SEFAZ-TD-RotuloSaida" colspan="2" >Valor Declarado:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" colspan="2" ><c:out value="${bemTributavelVo.valorInformadoFormatado}"/></td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" colspan="2" >Tipo do Bem:&nbsp;</td>		
				<td class="SEFAZ-TD-CampoSaida" colspan="2" ><c:out value="${bemTributavelVo.bemVo.classificacaoTipoBem.textoCorrente}"/></td>										
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" colspan="2" >Descrição do Bem:&nbsp;</td>		
				<td class="SEFAZ-TD-CampoSaida" colspan="2" ><c:out value="${bemTributavelVo.bemVo.descricaoTipoBem}"/></td>										
			</tr>			
			<tr style="display:none" id="trIsencaoPrevista">
				<td class="SEFAZ-TD-RotuloEntrada" colspan="2" >Isenção Prevista em Lei:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" colspan="2" >
					<abaco:campoSelectDominio ajuda="" classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" name="<%=Form.CHECKEBOX_ISENCAO_PREVISTA_EM_LEI%>" tabIndex="" mostrarSelecione="false" valorDefault="<%=DomnSimNao.NAO%>" opcaoSelecionada="${avaliacaoBemTributavelVo.isento.valorCorrente}" onChange="habilitarIsencao();"></abaco:campoSelectDominio>
				</td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="337" colspan="2" id="tdLabelValorAvaliacao"> <div id="idValorAvaliacao"/> </td>
				<td class="SEFAZ-TD-CampoEntrada" width="180" colspan="2" id="tdValorAvaliacao">
					<abaco:campoMonetario name="<%=Form.CAMPO_VALOR_AVALIACAO%>" idCampo="<%=Form.CAMPO_VALOR_AVALIACAO%>" size="15" value="${avaliacaoBemTributavelVo.valorAvaliacaoFormatado}" quantidadeDigitosInteiros="13" quantidadeCasasDecimais="2" ajuda="Digite aqui o seu número monetário."/><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="337" colspan="2"><div id="idDataAvaliacao"></div></td>
				<td class="SEFAZ-TD-CampoEntrada" width="180" colspan="2">
					<input name="<%=Form.CAMPO_DATA_AVALIACAO%>" type="text" class="SEFAZ-INPUT-Text"  value="<c:out value="${avaliacaoBemTributavelVo.dataAvaliacaoFormatado}"/>"  size="13" maxlength="60" onkeypress="return formataNumeroW3c(this,event)" onblur="formataDataW3c(this);"/><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloSaida" width="337" colspan="2">Data de <c:out value="${msgDataAvaliacao}"/> da Avaliação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoSaida" width="180" colspan="2"><c:out value="${avaliacaoBemTributavelVo.dataCadastroFormatado}"></c:out> </td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>   
			<tr>
				<td class="SEFAZ-TR-Titulo"  width="337"  colspan="4">Servidores:&nbsp;</td>
			</tr>
			<tr>
				<td width="180"  colspan="4" align="center">&nbsp;<a href="javascript:incluirServidor();">Incluir novo Servidor</a><font color="red">*</font></td>
			</tr>
			<c:forEach var="avaliacaoBemTributavelServidorVo" items="${avaliacaoBemTributavelVo.avaliacaoBemTributavelServidorVo.collVO}"  varStatus="contador">
				<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
				<c:if test="${contador.count % 2 != 0}">
					<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
				</c:if>
				<tr class="<c:out value="${linhaEstilo}"/>"> 
					<td width="180"  colspan="2"><c:out value="${avaliacaoBemTributavelServidorVo.servidorSefazVo.nomeServidor}"/></td>
					<c:choose>
						<c:when test="${avaliacaoBemTributavelServidorVo.servidorSefazVo.numrMatricula == giaITCDVo.servidorSefazIntegracaoVo.numrMatricula}">
							<td width="180"  colspan="2">&nbsp;</td>												
						</c:when>
						<c:otherwise>
							<td width="180"  colspan="2">&nbsp;<a href='javascript:excluirServidor(<c:out value="${contador.index}"></c:out>);'>Excluir</a></td>							
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
			<tr>
				<td  colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="337">Observação:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" colspan="3">
					<textarea name="<%=Form.CAMPO_OBSERVACAO%>" cols="25" rows="2" onKeyUp="cortaPalavrasW3c(this,4000)"  onblur="toUpperCaseW3c(this);" ><c:out value="${avaliacaoBemTributavelVo.observacao}"></c:out></textarea>
				</td>
			</tr>  	
			<tr> 
				<td colspan="4">&nbsp;</td>
			</tr>
			</table>
			<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
				<tr> 
					<td align="center"> 
						<input type="button" 	value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" 	class="SEFAZ-INPUT-Botao" 	name="<%=Form.BOTAO_CONFIRMAR_CLONE%>" onClick="return validaFormulario();"/>
						<abaco:botaoVoltar nomeContadorSubmit="incluirAvaliacaoBem" tabIndex="1" />
						<abaco:botaoCancelar/>
					</td>
				</tr>
				<tr>
					<td><font color="red"><b>* Campos Obrigatórios</b></font></td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<script type="text/javascript" language="javascript">gerarSpanMesgBotao();</script>
						</div>
					<td>&nbsp;</tr>
			</table>
		<br/>
		</form>
		</CENTER>
	<g:mostrarRodape />
	</BODY>
</HTML>