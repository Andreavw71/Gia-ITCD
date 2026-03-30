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
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.avaliacao.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsultaAvaliacao"%>
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
               document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_DETALHAR_GIASITCD+"="%>'+codigoGia;
               document.form.submit();
               return true;
            }

				function trocaOpcaoConsulta()
				{	 
					navegador = buscarTipoNavegador();				
					valorSelecionado = document.form.<%=Form.CAMPO_SELECT_TIPO_CONSULTA%>.value;
					if(valorSelecionado == <%=DomnTipoConsultaAvaliacao.NUMERO_GIA_ITCD%>)
					{			
						document.getElementById('opcaoConsultaPorContribuinte').style.display = 'none';
						document.getElementById('exibirDadosContribuinte').style.display = 'none';
						document.getElementById('opcaoConsultaUnidadeFazendaria').style.display='none';	
						document.getElementById('comboAgenfa').style.display= 'none';		
						document.getElementById('comboGerencia').style.display= 'none';		
						document.getElementById('comboUnidadeGeral').style.display = 'none';
						document.getElementById('opcaConsultaNumeroGia').style.display = navegador;							
						document.getElementById('botaoPesquisarGia').style.display = navegador;	
						document.getElementById('botaoListarGias').style.display = 'none';										
					}
					else if(valorSelecionado == <%=DomnTipoConsultaAvaliacao.AVALIACAO_POR_AGENFA%>)
					{
						document.getElementById('opcaoConsultaPorContribuinte').style.display = 'none';
						document.getElementById('exibirDadosContribuinte').style.display = 'none';						
						document.getElementById('opcaoConsultaUnidadeFazendaria').style.display= navegador;		
						document.getElementById('comboAgenfa').style.display= navegador;		
						document.getElementById('comboGerencia').style.display= 'none';	
						document.getElementById('comboUnidadeGeral').style.display = 'none';
						document.getElementById('opcaConsultaNumeroGia').style.display = 'none';							
						document.getElementById('botaoPesquisarGia').style.display = 'none';	
						document.getElementById('botaoListarGias').style.display = navegador;										
					}					
					else if(valorSelecionado == <%=DomnTipoConsultaAvaliacao.AVALIACAO_POR_GERENCIA_DE_EXECUCAO%>)
					{
						document.getElementById('opcaoConsultaPorContribuinte').style.display = 'none';
						document.getElementById('exibirDadosContribuinte').style.display = 'none';						
						document.getElementById('opcaoConsultaUnidadeFazendaria').style.display= navegador;
						document.getElementById('comboAgenfa').style.display= 'none';		
						document.getElementById('comboGerencia').style.display= navegador;	
						document.getElementById('comboUnidadeGeral').style.display = 'none';
						document.getElementById('opcaConsultaNumeroGia').style.display = 'none';
						document.getElementById('botaoListarGias').style.display = navegador;													
						document.getElementById('botaoPesquisarGia').style.display = 'none';
					}
					else if(valorSelecionado == <%=DomnTipoConsultaAvaliacao.AVALIACAO_POR_UNIDADE_FAZENDARIA%>)
					{
						document.getElementById('opcaoConsultaPorContribuinte').style.display = 'none';
						document.getElementById('exibirDadosContribuinte').style.display = 'none';						
						document.getElementById('opcaoConsultaUnidadeFazendaria').style.display= navegador;
						document.getElementById('comboAgenfa').style.display= 'none';		
						document.getElementById('comboGerencia').style.display= 'none';	
						document.getElementById('comboUnidadeGeral').style.display = navegador;
						document.getElementById('opcaConsultaNumeroGia').style.display = 'none';
						document.getElementById('botaoListarGias').style.display = navegador;													
						document.getElementById('botaoPesquisarGia').style.display = 'none';
					}					
					else if(valorSelecionado == <%=DomnTipoConsultaAvaliacao.AVALIACAO_POR_CONTRIBUINTE%>)
					{
						document.getElementById('opcaoConsultaPorContribuinte').style.display = navegador;
						document.getElementById('exibirDadosContribuinte').style.display = navegador;						
						document.getElementById('opcaoConsultaUnidadeFazendaria').style.display= 'none';
						document.getElementById('comboAgenfa').style.display= 'none';		
						document.getElementById('comboGerencia').style.display= 'none';		
						document.getElementById('comboUnidadeGeral').style.display = 'none';
						document.getElementById('opcaConsultaNumeroGia').style.display = 'none';
						document.getElementById('botaoListarGias').style.display = navegador;													
						document.getElementById('botaoPesquisarGia').style.display = 'none';					
					}
					else
					{
						document.getElementById('opcaoConsultaPorContribuinte').style.display = 'none';
						document.getElementById('exibirDadosContribuinte').style.display = 'none';						
						document.getElementById('opcaoConsultaUnidadeFazendaria').style.display= 'none';
						document.getElementById('comboAgenfa').style.display= 'none';		
						document.getElementById('comboGerencia').style.display= 'none';	
						document.getElementById('comboUnidadeGeral').style.display = 'none';
						document.getElementById('opcaConsultaNumeroGia').style.display = 'none';
						document.getElementById('botaoListarGias').style.display = 'none';													
						document.getElementById('botaoPesquisarGia').style.display = 'none';										
					}
				}

            function validaFormulario()
            {
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_CODIGO_GIA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_CODIGO_GIA+"'"%>))
                {
                    return false;
                }
										 
					 desabilitarBotoes(obterArrayBotoes());
                document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_PESQUISAR_GIAITCD_POR_NUMERO+"=1"%>';
                document.form.submit();
                return true;
            }            
				

            function pesquisarGias()
            {		
					valorSelecionado = document.form.<%=Form.CAMPO_SELECT_TIPO_CONSULTA%>.value;					
					if(valorSelecionado == <%=DomnTipoConsultaAvaliacao.AVALIACAO_POR_AGENFA%>)
					{
						if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_AGENFA%>,<%="'"+MensagemErro.VALIDAR_CAMPO_SELECT_AGENFA+"'"%>))
						{
							return false;
						}
					}
					if(valorSelecionado == <%=DomnTipoConsultaAvaliacao.AVALIACAO_POR_GERENCIA_DE_EXECUCAO%>)
					{
						if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_GERENCIA_EXECUCAO%>,<%="'"+MensagemErro.VALIDAR_CAMPO_SELECT_GERENCIA_EXECUCAO+"'"%>))
						{
							return false;
						}
					}	
					if(valorSelecionado == <%=DomnTipoConsultaAvaliacao.AVALIACAO_POR_UNIDADE_FAZENDARIA%>)
					{
						if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_UNIDADE_AVALIACAO%>,<%="'"+MensagemErro.VALIDAR_CAMPO_SELECT_UNIDADE_FAZENDARIA+"'"%>))
						{
							return false;
						}
					}						
					if((valorSelecionado == <%=DomnTipoConsultaAvaliacao.AVALIACAO_POR_AGENFA%>) || (valorSelecionado == <%=DomnTipoConsultaAvaliacao.AVALIACAO_POR_GERENCIA_DE_EXECUCAO%>) || (valorSelecionado == <%=DomnTipoConsultaAvaliacao.AVALIACAO_POR_UNIDADE_FAZENDARIA%>))
					{
						if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_INICIAL%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_CAMPO_DATA_INICIAL+"'"%>))
						{
							return false;
						}
						if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_FINAL%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_CAMPO_DATA_FINAL+"'"%>))
						{
							return false;
						}						
					}
					if(valorSelecionado == <%=DomnTipoConsultaAvaliacao.AVALIACAO_POR_CONTRIBUINTE%>)
					{
						 <c:if test="${empty avaliacaoBemTributavelVo.bemTributavel.giaITCDVo.responsavelVo.numrDocumento}">
							  alert(<%="'"+MensagemErro.VALIDAR_CONTRIBUINTE_PESQUISA_GIA_ITCD+"'"%>);
							  return false;
						 </c:if>					
					}
					 desabilitarBotoes(obterArrayBotoes());
					 document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_LISTAR_GIAITCD+"=1"%>';
					 document.form.submit();
            }
    
        </script>
</head>

<body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>'); verificaErro(); trocaOpcaoConsulta();">
   <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
   <jsp:include page="/util/ViewVerificaErro.jsp"/>
	<form name="form" method="POST" action="#">
		<table width="740" border="0" cellspacing="1" cellpadding="0" align="center">
			<tr align="right"> 
				<td colspan="2"><div id="<%=Form.BOTAO_AJUDA%>"></div></td> 
			</tr> 
			<tr class="SEFAZ-TR-Titulo" align="center"> 
				<td colspan="2">Filtro para Pesquisa</td>
			</tr>
			<tr>
				<td width="50%" class="SEFAZ-TD-RotuloEntrada">Tipo de Consulta:&nbsp; </td>
				<td width="50%" class="SEFAZ-TD-CampoEntrada">
					<abaco:campoSelectDominio ajuda="Selecione uma opçao." classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoConsultaAvaliacao" name="<%=Form.CAMPO_SELECT_TIPO_CONSULTA%>" tabIndex="" mostrarSelecione="true" opcaoSelecionada="${avaliacaoBemTributavelVo.tipoConsultaAvaliacao.valorCorrente}" onChange="trocaOpcaoConsulta();"/><font color="red">*</font>
				</td>
			</tr>			
			<tr id="opcaoConsultaPorContribuinte" style="display:none">
				<td colspan="2">
					<table width="740" border="0" cellspacing="1" cellpadding="0" align="center">
						<tr>
							<td>	
								<c:choose>
									<c:when test="${empty avaliacaoBemTributavelVo.bemTributavel.giaITCDVo.responsavelVo.numrDocumento}">
										<c:set var="naoOculta" value='true' scope="request"/>													
									</c:when>
									<c:otherwise>
										<c:set var="naoOculta" value='false' scope="request"/>																				
									</c:otherwise>
								</c:choose>
								<c:set var="larguraRotuloPadrao" value="50%" scope="request"/>										
								<c:set var="ocultaLinkCadastrar" value="true" scope="request"/>
								<c:set var="nomeTdLocalizarContribuinte" value="CPF Responsável" scope="request"/>
								<c:set var="campoObrigatorio" scope="request" value="true"></c:set>
								<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteIncludeNova.jsp"/>
								<c:remove var="naoOculta" scope="request"/>
								<c:remove var="larguraRotuloPadrao" scope="request"/>
								<c:remove var="nomeTdLocalizarContribuinte" scope="request"/>
								<c:remove var="campoObrigatorio" scope="request"/>
							</td>						
						</tr>
					</table>
				</td>
			</tr>
			<tr id="exibirDadosContribuinte" style="display:none">
				<td colspan="2">
					<c:if test="${ not empty avaliacaoBemTributavelVo.bemTributavel.giaITCDVo.responsavelVo.numrDocumento}">				
						<table width="740" border="0" cellspacing="1" cellpadding="0" align="center">
							<tr>
								<td width="50%" class="SEFAZ-TD-RotuloSaida">Número do Documento:&nbsp;</td>
								<td width="50%" class="SEFAZ-TD-CampoSaida"><c:out value="${avaliacaoBemTributavelVo.bemTributavel.giaITCDVo.responsavelVo.numrDocumento}" /></td>
							</tr>
							<tr>
								<td width="50%" class="SEFAZ-TD-RotuloSaida">Nome do Contribuinte:&nbsp;</td>
								<td width="50%" class="SEFAZ-TD-CampoSaida"><c:out value="${avaliacaoBemTributavelVo.bemTributavel.giaITCDVo.responsavelVo.nomeContribuinte}"  /></td>
							</tr>					
						</table>
					</c:if>									 						
				</td>
			</tr>
			<tr id="opcaConsultaNumeroGia" style="display:none">
			  <td class="SEFAZ-TD-RotuloEntrada" width="50%">GIA:&nbsp;</td>
			  <td  class="SEFAZ-TD-CampoEntrada" width="50%">
					<abaco:campoApenasNumero maxlength="12" name="<%=Form.CAMPO_CODIGO_GIA%>" size="18" value="${avaliacaoBemTributavelVo.bemTributavel.giaITCDVo.codigoFormatado}"/><font color="red">*</font>
			  </td>
			</tr>
			<tr id="opcaoConsultaUnidadeFazendaria" style="display:none">
				<td colspan="2">
					<table width="740" border="0" cellspacing="1" cellpadding="0" align="center">
						<tr id="comboAgenfa" style="display:none">
							<td class="SEFAZ-TD-RotuloEntrada" width="50%">Agenfa de Avaliação:&nbsp;</td>
							<td class="SEFAZ-TD-CampoEntrada" width="50%">
								<abaco:selectCollection ajuda="Selecione uma opção." collectionVo="${avaliacaoBemTributavelVo.listaAgenfa.collVO}" name="<%=Form.CAMPO_SELECT_AGENFA%>" nomeAtributoDescricao="nomeUnidade" nomeAtributoValor="codgUnidade" tabIndex="" mostrarSelecione="true" opcaoSelecionada="${avaliacaoBemTributavelVo.listaAgenfa.codgUnidade}"/><font color="red">*</font>
							</td>														
						</tr>
						<tr id="comboGerencia" style="display:none">
							<td class="SEFAZ-TD-RotuloEntrada" width="50%">Gerência de Avaliação:&nbsp;</td>
							<td class="SEFAZ-TD-CampoEntrada" width="50%">
								<abaco:selectCollection ajuda="Selecione uma opção." collectionVo="${avaliacaoBemTributavelVo.listaGerencia.collVO}" name="<%=Form.CAMPO_SELECT_GERENCIA_EXECUCAO%>" nomeAtributoDescricao="nomeUnidade" nomeAtributoValor="codgUnidade" tabIndex="" mostrarSelecione="true" opcaoSelecionada="${avaliacaoBemTributavelVo.listaGerencia.codgUnidade}"/><font color="red">*</font>
							</td>														
						</tr>
						<tr id="comboUnidadeGeral" style="display:none">
							<td class="SEFAZ-TD-RotuloEntrada" width="50%">Unidade Fazendária de Avaliação:&nbsp;</td>
							<td class="SEFAZ-TD-CampoEntrada" width="50%">
								<abaco:selectCollection ajuda="Selecione uma opção." collectionVo="${avaliacaoBemTributavelVo.listaUnidadesFazendarias.collVO}" name="<%=Form.CAMPO_SELECT_UNIDADE_AVALIACAO%>" nomeAtributoDescricao="nomeUnidade" nomeAtributoValor="codgUnidade" tabIndex="" mostrarSelecione="true" opcaoSelecionada="${avaliacaoBemTributavelVo.listaUnidadesFazendarias.codgUnidade}"/><font color="red">*</font>
							</td>														
						</tr>							
						
						<tr>
							<td class="SEFAZ-TD-RotuloEntrada" width="50%">Data Inicial:&nbsp;</td>
							<td class="SEFAZ-TD-CampoEntrada" width="50%">				
								<abaco:campoData name="<%=Form.CAMPO_DATA_INICIAL%>" size="10" value="${avaliacaoBemTributavelVo.dataInicialFormatada}"/><font color="red">*</font>
							</td>											
						</tr>
						<tr>
							<td class="SEFAZ-TD-RotuloEntrada" width="50%">Data Final:&nbsp;</td>
							<td class="SEFAZ-TD-CampoEntrada" width="50%">				
								<abaco:campoData name="<%=Form.CAMPO_DATA_FINAL%>" size="10" value="${avaliacaoBemTributavelVo.dataFinalFormatada}"/><font color="red">*</font>
							</td>															
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br/>
		<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		  <tr> 
				<td> 
					<div align="center">
						<input name="<%=Form.BOTAO_PESQUISAR_CLONE%>"  type="button"  value="<%=Form.TEXTO_BOTAO_PESQUISAR%>"  
										  class="SEFAZ-INPUT-Botao" onclick="return pesquisarGias();" id="botaoListarGias" style="display:none"/>
							  
						<input name="<%=Form.BOTAO_PESQUISAR_CLONE%>"  type="button"  value="<%=Form.TEXTO_BOTAO_PESQUISAR%>"  
										  class="SEFAZ-INPUT-Botao" onclick="return validaFormulario();" id="botaoPesquisarGia" style="display:none"/>										  
						<abaco:botaoCancelarSemMensagem/>
					</div>
				</td>
		  </tr>
		  <tr>
			<td><font color="red"><b>* Campos Obrigatórios</b></font></td>
		  </tr>
		</table>
		<c:if test="${not empty avaliacaoBemTributavelVo.bemTributavel.giaITCDVo.paginador.listaExibicao}">
			<br></br>
			<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
				<tr class="SEFAZ-TR-Titulo" align="center"> 
					<td colspan="7">Resultados da Pesquisa</td>
				</tr>
				<tr>
					<td colspan="7">
						<div>
							<abaco:paginador 
								cabecalho="GIA-ITCD" 
								objetoPaginador="${avaliacaoBemTributavelVo.bemTributavel.giaITCDVo.paginador}" 
								utilizarRequisicaoSimples="true" 
								parametroTrocaPagina="<%=Form.PARAMETRO_TROCAR_PAGINA%>"  >
							</abaco:paginador>						
						</div>
					</td>
				</tr>
				<tr class="SEFAZ-TR-SubTituloEsq"> 
					<td width="6%" align="center">Nº da GIA</td>
					<td width="15%" align="center">Tipo de GIA</td>
					<td width="21%" align="center">Tipo de Processo</td>
					<td width="21%" align="center">Nome do Contribuinte</td>
					<td width="10%" align="center">Data da Avaliação</td>
					<td width="20%" align="center">Status da GIA-ITCD</td>
               <td width="7%" align="center">Status Última Avaliação</td>
				</tr>	
				<c:forEach var="gia" items="${avaliacaoBemTributavelVo.bemTributavel.giaITCDVo.paginador.listaExibicao}" varStatus="status">
					<abaco:linhaTabela>
						 <td align="center" ><a href="javascript:mostrarGIAITCD(<c:out value="${gia.codigo}"/>);"><c:out value="${gia.codigo}"/></a></td>
						 <td align="center" > <c:out value="${gia.tipoGIA.textoCorrente}"/></td>
						 <td align="center" ><c:out value="${gia.naturezaOperacaoVo.tipoProcesso.textoCorrente}"/></td>
						 <td align="center" ><c:out value="${gia.responsavelVo.nomeContribuinte}"/></td>
						 <td align="center" ><c:out value="${gia.statusVo.dataCadastroAvaliacaoFormatada}"/></td>
						 <td align="center" ><c:out value="${gia.statusVo.statusGIAITCD.textoCorrente}"/></td>
                   <td align="center" ><c:out value="${gia.statusUltimaAvaliacao.textoCorrente}"/></td>	
					</abaco:linhaTabela>
				</c:forEach>
			</table>		
		</c:if>
		<br/>
		<abaco:mensagemAguardeCarregando></abaco:mensagemAguardeCarregando>
		<g:mostrarRodape/>
	</form>
</body>
</html>
