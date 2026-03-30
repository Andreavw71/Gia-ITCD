<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarGIAITCD.jsp
* Criação : Outubro de 2007 / Rogério Sanches de Oliveira
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsulta"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.giaitcd.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>

<html>
    <head>
        <title><abaco:tituloSistema></abaco:tituloSistema></title>
        <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
        <META HTTP-EQUIV=Cache-Control content=no-store>
        <META HTTP-EQUIV=Pragma content=no-cache>
        
        <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
		  <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script> 
        <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
        <script type="text/javascript" language="javascript">
            
            function obterArrayBotoes()
            {
                    var botao1 = document.form.<%=Form.BOTAO_PESQUISAR_CLONE%>;
                    return new Array(botao1);
            }
            
            function mostrarGIAITCD(codigoGia)
            {
               document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR_GIASITCD+"="%>'+codigoGia;
               document.form.submit();
               return true;
            }
            
            function alertAndMostrarGIAITCD(codigoGia) {
               alert('GIA utilizada no cálculo de Doação Sucessiva. Deseja continuar?');
               mostrarGIAITCD(codigoGia);
            }

            function pesquisarGias()
            {					 
                <c:if test="${empty giaITCDVo.responsavelVo.numrDocumento}">
                    alert(<%="'"+MensagemErro.VALIDAR_CONTRIBUINTE_PESQUISA_GIA_ITCD+"'"%>);
                    return false;
                </c:if>
					 desabilitarBotoes(obterArrayBotoes());
					 document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_LISTAR_GIAITCD+"=1"%>';
					 document.form.submit();
            }
            function consultarContribuinte()
            {
					 document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_PESQUISAR_CONTRIBUINTE+"=1"%>';
					 document.form.submit();
            }
    
            function validaFormulario()
            {
                if(!verificaCamposW3c(document.form.<%=Form.PARAMETRO_CODIGO_GIA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_CODIGO_GIA+"'"%>))
                {
                    return false;
                }
					 <c:if test="${not giaITCDVo.usuarioServidor}">
						 if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SENHA%>,<%="'"+MensagemErro.VALIDAR_SENHA_GIA+"'"%>))
						 {
							  return false;
						 }													 
					 </c:if>
					 desabilitarBotoes(obterArrayBotoes());
                document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR+"=1"%>';
                document.form.submit();
                return true;
            }
            
            function enviarNovaSenha()
            {
                document.form.action='<%=FormAcesso.getUrlServletOriginal(FormAcesso.CODIGO_ALTERAR_PESQUISAR_GIAITCD_ENVIAR_SENHA, request)%>';
                document.form.submit();
                return true;
            }
				
				function trocaOpcaoConsulta(valorSelecionado)
				{
					navegador = buscarTipoNavegador();				
					<c:if test="${giaITCDVo.usuarioServidor}">
							if(valorSelecionado == <%=DomnTipoConsulta.CONTRIBUINTE_RESPONSAVEL%>)
							{
								document.getElementById('opcaoConsultaPorContribuinte').style.display = navegador;
								document.getElementById('exibeDadosContribuinteResponsavel').style.display=navegador;
								document.getElementById('opcaConsultaNumeroGia').style.display = 'none';
								document.getElementById('botaoListarGias').style.display = navegador;
								document.getElementById('botaoPesquisarGia').style.display = 'none';
								document.getElementById('listaGiasEncontradas').style.display = navegador;
							}
							else if(valorSelecionado == <%=DomnTipoConsulta.NUMERO_GIA%>)
							{
								document.getElementById('opcaoConsultaPorContribuinte').style.display = 'none';
								document.getElementById('exibeDadosContribuinteResponsavel').style.display='none';						
								document.getElementById('opcaConsultaNumeroGia').style.display = navegador;	
								document.getElementById('botaoListarGias').style.display = 'none';
								document.getElementById('botaoPesquisarGia').style.display = navegador;	
								document.getElementById('listaGiasEncontradas').style.display = 'none';								
							}
							else
							{
								document.getElementById('opcaoConsultaPorContribuinte').style.display = 'none';		
								document.getElementById('exibeDadosContribuinteResponsavel').style.display='none';												
								document.getElementById('opcaConsultaNumeroGia').style.display = 'none';					
								document.getElementById('botaoPesquisarGia').style.display = 'none';
								document.getElementById('botaoListarGias').style.display = 'none';								
								document.getElementById('listaGiasEncontradas').style.display = 'none';																
							}					
					</c:if>
					<c:if test="${not giaITCDVo.usuarioServidor}">
								document.getElementById('opcaConsultaNumeroGia').style.display = navegador;								
					</c:if>
				}
        </script>
</head>

<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>'); verificaErro(); trocaOpcaoConsulta(<c:out value='${giaITCDVo.tipoConsultaGia.valorCorrente}'/>);">
    <!-- padrao sefaz - cabeçalho e página de erro -->
    <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
    <jsp:include page="/util/ViewVerificaErro.jsp"/>
    <!-- FIM: padrao sefaz - cabeçalho e página de erro -->
    
<form name="form" method="POST" action="#">
<c:set var="pesquisar"><%=JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD)%></c:set>
<c:set var="alterarGia"><%=JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_ALTERAR_GIAITCD_PESQUISAR_GIAITCD)%></c:set>
    <!-- cabeçalho da pesquisa -->
    <table width="740" border="0" cellspacing="1" cellpadding="0" align="center">
			<tr align="right"> 
				<td colspan="2"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
			</tr> 
			<tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="2">Filtro para Pesquisa</td>
			</tr>
			<c:if test="${giaITCDVo.usuarioServidor}">
				<tr>
						<td class="SEFAZ-TD-RotuloEntrada" width="50%">Tipo de Consulta:&nbsp;</td>
						<td class="SEFAZ-TD-ComboBox" width="50%">
								<abaco:campoSelectDominio ajuda="Selecione uma opção de Consulta" 
									classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsulta" 
									name="<%=Form.CAMPO_SELECT_TIPO_CONSULTA%>" tabIndex="" mostrarSelecione="true" 
									opcaoSelecionada="${giaITCDVo.tipoConsultaGia.valorCorrente}" 
									onChange="trocaOpcaoConsulta(this.value);"/>
									<font color="red">*</font>
						</td>
				</tr>			
			</c:if>
    <!-- FIM: cabeçalho da pesquisa -->

    <!-- parametros da pesquisa -->
				<tr id="opcaoConsultaPorContribuinte" style="display:none">
					<td colspan="2">	
						<c:choose>
							<c:when test="${empty giaITCDVo.responsavelVo.numrDocumento}">
								<c:set var="naoOculta" value='true' scope="request"/>													
							</c:when>
							<c:otherwise>
								<c:set var="naoOculta" value='false' scope="request"/>																				
							</c:otherwise>
						</c:choose>
						<c:set var="larguraRotuloPadrao" value="50%" scope="request"/>										
						<c:choose>
							<c:when test="${not empty exceptionCadastro}">
								<c:set var="ocultaLinkCadastrar" value="false" scope="request"/>
							</c:when>
							<c:otherwise>
								<c:set var="ocultaLinkCadastrar" value="true" scope="request"/>					
							</c:otherwise>
						</c:choose>		
						<c:set value="true" var="campoObrigatorio" scope="request"></c:set>
						<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteIncludeNova.jsp"/>
						<c:remove var="naoOculta" scope="request"/>
						<c:remove var="larguraRotuloPadrao" scope="request"/>
					</td>
				</tr>
				<tr id="exibeDadosContribuinteResponsavel" style="display:none">
					<td colspan="2">
				    <table width="740" border="0" cellspacing="1" cellpadding="0" align="center">
						<c:if test="${ not empty giaITCDVo.responsavelVo.numrDocumento}">
							<tr>
								<td width="50%" class="SEFAZ-TD-RotuloSaida">Número do Documento:&nbsp;</td>
								<td width="50%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDocumento}" /></td>
							</tr>
							<tr>
								<td width="50%" class="SEFAZ-TD-RotuloSaida">Nome do Contribuinte:&nbsp;</td>
								<td width="50%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.nomeContribuinte}"  /></td>
							</tr>
						</c:if>									 
					 </table>
					</td>
				</tr>
            <!-- para usuários externos -->
				 <tr id="opcaConsultaNumeroGia" style="display:none">
					  <td class="SEFAZ-TD-RotuloEntrada" width="50%">GIA:&nbsp;</td>
					  <td  class="SEFAZ-TD-CampoEntrada" width="50%">
							<abaco:campoApenasNumero maxlength="12" name="<%=Form.CAMPO_CODIGO_GIA%>" size="18" value="${giaITCDVo.codigoFormatado}"/><font color="red">*</font>
					  </td>
				 </tr>
				<c:if test="${not giaITCDVo.usuarioServidor}">
                <tr>
                    <td class="SEFAZ-TD-RotuloEntrada" width="50%">Senha:&nbsp;</td>
                    <td  class="SEFAZ-TD-CampoEntrada" width="50%">
                        <input type="password" name="<%=Form.CAMPO_SENHA%>"  class="SEFAZ-INPUT-Text" value="" size="25" maxlength="25"><font color="red">*</font>
                    </td>
                </tr>
                <tr>
                    <td class="SEFAZ-TD-CampoEntrada" colspan="2">
                        <div align="center"><a href="javascript:enviarNovaSenha();" class="SEFAZ-TD-CampoEntrada" >Esqueci minha senha</a></div>
                    </td>
                </tr>
            </c:if>
            <!-- FIM: para usuários externos -->
    </table>
    <!-- FIM: parametros da pesquisa -->

	<br>
	<jsp:include page="/util/seguranca/ViewMostrarImagemCaracterModAberto.jsp"/>	
	 
    <!-- botões de ações -->
    <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
        <tr> 
            <td> 
					<div align="center">
                    <c:if test="${giaITCDVo.usuarioServidor}">
                        <input name="<%=Form.BOTAO_PESQUISAR_CLONE%>"  type="button"  value="<%=Form.TEXTO_BOTAO_PESQUISAR%>"  
                        class="SEFAZ-INPUT-Botao" onclick="return pesquisarGias();" id="botaoListarGias" style="display:none"/> 

                        <input name="<%=Form.BOTAO_PESQUISAR_CLONE%>"  type="button"  value="<%=Form.TEXTO_BOTAO_PESQUISAR%>"  
                        class="SEFAZ-INPUT-Botao" onclick="return validaFormulario();" id="botaoPesquisarGia" style="display:none"/>
                        
							</c:if>
                     
                    <c:if test="${not giaITCDVo.usuarioServidor}">
                        <input name="<%=Form.BOTAO_PESQUISAR_CLONE%>"  type="button"  value="<%=Form.TEXTO_BOTAO_PESQUISAR%>"  
                                class="SEFAZ-INPUT-Botao" onClick="return validaFormulario();" id="botaoPesquisarGia"/>
                    </c:if>
                <abaco:botaoCancelarSemMensagem/>
					</div>
            </td>
        </tr>
		  <tr>
			<td><font color="red"><b>* Campos Obrigatórios</b></font></td>
		  </tr>
    </table>
    <!-- FIM: botões de ações -->
    <br></br>
	 <!-- resultado da pesquisa -->
    <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
	 <tr id="listaGiasEncontradas" style="display:none">
			<td>
			    <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">			
						 <c:if test="${not empty giaITCDVo.collVO}">
							  <tr class="SEFAZ-TR-Titulo" align="center"> 
									<td colspan="6">Resultados da Pesquisa</td>
							  </tr>
							  <tr>
								<td colspan="6">
									<div>
									  <abaco:paginador 
											cabecalho="GIA-ITCD" 
											objetoPaginador="${giaITCDVo.paginador}" 
											parametroTrocaPagina="<%=Form.PARAMETRO_TROCAR_PAGINA%>" 
											utilizarRequisicaoSimples="true">
									  </abaco:paginador>									
									</div>
								</td>
							  </tr>
							  
							  <tr class="SEFAZ-TR-SubTitulo"> 
									<td width="5%" align="center">Nº da GIA:</td>
									<td width="15%" align="center">Tipo de GIA</td>
									<td width="20%" align="center">Tipo de Processo:&nbsp;</td>
									<td width="20%" align="center">Nome do Contribuinte:&nbsp; </td>
									<td width="20%" align="center">Data  de criação da GIA-ITCD</td>
									<td width="20%" align="center">Status da GIA-ITCD</td>
							  </tr>	

							  <c:forEach var="gia" items="${ giaITCDVo.paginador.listaExibicao}" varStatus="status">
								  <abaco:linhaTabela>
                           <c:choose>
                              <c:when test="${gia.giaUtilizadaParaDoacaoSucessiva}">
                                 <td align="center"><a href="javascript:alertAndMostrarGIAITCD(<c:out value="${gia.codigo}"/>);"><c:out value="${gia.codigo}"/></a></td>
                              </c:when>
                              <c:otherwise>
                                 <td align="center"><a href="javascript:mostrarGIAITCD(<c:out value="${gia.codigo}"/>);"><c:out value="${gia.codigo}"/></a></td>
                              </c:otherwise>
                            </c:choose>  									 
									 <td align="center" > <c:out value="${gia.tipoGIA.textoCorrente}"/></td>
									 <td align="center" ><c:out value="${gia.naturezaOperacaoVo.tipoProcesso.textoCorrente}"/></td>
									 <td align="center" ><c:out value="${gia.responsavelVo.nomeContribuinte}"/></td>
									 <td align="center" ><c:out value="${gia.dataCriacaoFormatada}"/></td>
									 <td align="center" ><c:out value="${gia.statusVo.statusGIAITCD.textoCorrente}"/></td>
								  </abaco:linhaTabela>							  
							  </c:forEach>
						 </c:if>
				 </table>
			</td>
	 </tr>
    </table>
    <!-- FIM: resultado da pesquisa -->
    
    <br/>
    

    
    <!-- bloco de mensagem -->
	<abaco:mensagemAguardeCarregando></abaco:mensagemAguardeCarregando>
    <!-- FIM: bloco de mensagem -->
<g:mostrarRodape/>
</form>
</body>
</html>
