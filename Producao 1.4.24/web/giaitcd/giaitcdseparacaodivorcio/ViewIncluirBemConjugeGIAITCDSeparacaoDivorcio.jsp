<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewIncluirBemConjugeGIAITCDSeparacaoDivorcio.jsp
* Criação : Janeiro de 2008 / João Batista Padilha e Silva
* Revisão : Abril de 2008/ Elizabeth Barbosa Moreira
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@ page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoContribuinte"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo"%>
<%
	pageContext.setAttribute("SIM", new Integer(DomnSimNao.SIM));
	pageContext.setAttribute("NAO", new Integer(DomnSimNao.NAO));
	
	pageContext.setAttribute("CONJUGE1", new Integer(DomnTipoContribuinte.CONJUGE1));
	pageContext.setAttribute("CONJUGE2", new Integer(DomnTipoContribuinte.CONJUGE2));	
	
	pageContext.setAttribute("PROTOCOLO_MANUAL",  new Integer(DomnTipoProtocolo.PROTOCOLO_MANUAL));
	pageContext.setAttribute("PROTOCOLO_AUTOMATICO", new Integer(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO));
	
%>

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

	function habilidaCampo(campoCheck,campoDiv)
	{
		var tipo = buscarTipoNavegador();
		if(campoCheck.checked == true)
		{
			document.getElementById(campoDiv).style.display = tipo;
		}
		else 
		{
			document.getElementById(campoDiv).style.display = 'none';
		}
	}
	
	function valida()
	{
		box = document.getElementsByTagName('input');
		for(x = 0; x < box.length; x++) 
		{
			if(box[x].checked) 
			{
				if(box[x+1].value == "0" || box[x+1].value == "0,00")
				{
					alert(<%="'"+MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_VALOR_BEM_CONJUGE_ZERO_OU_MENOR+"'"%>);
					return false;
				}
				if (box[x+1].value == "")
				{
					alert(<%="'"+MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_VALOR_BEM_CONJUGE_NAO_INFORMADO+"'"%>);
					return false;
				}
				valorBox = box[x+1].value;
				for(var i=0; i<valorBox.length; i++)
				{
					valorBox = valorBox.replace(".","");
				}
				valorBox = parseFloat(valorBox.replace(",","."));
				if(valorBox <= 0)
				{
					alert(<%="'"+MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_VALOR_BEM_CONJUGE_ZERO_OU_MENOR+"'"%>);
					return false;
				}
			}	
		}
		if(box.length == 0)
		{
			alert("Selecione uma opção");
			return false;
		}
		return true;
	}

	function formataNumero(campo, evento)
	{
			tecla = !isNaN(evento.keyCode)? evento.keyCode: evento.which;
			if ( tecla == 0)
				tecla = evento.which;
			if ((tecla != 0) &&  (tecla != 16) && (tecla != 17) && (tecla != 48) && (tecla != 49) && (tecla != 50) && (tecla != 51) && (tecla != 52) && (tecla != 53) && (tecla != 54) && (tecla != 55) && (tecla != 56) && (tecla != 57) && (tecla != 9) && (tecla != 13) && (tecla != 8))
			{
				return false;	
			}
	}
	
	function validaFormulario()
	{
		return true;
	}
	
		/**
	Função formata o valor para valor Double
	**/
	function floatTomoeda(valor) 
		{	
			x = 0;
			
			if(valor<0)
			{
				valor = Math.abs(valor);
				x = 1;
			}  
			if(isNaN(valor)) valor = "0";
				cents = Math.floor((valor*100+0.5)%100);		
				valor = Math.floor((valor*100+0.5)/100).toString();		
			if(cents < 10) 
				cents = "0" + cents;
			for (var i = 0; i < Math.floor((valor.length-(1+i))/3); i++)
				valor = valor.substring(0,valor.length-(4*i+3))+'.'+valor.substring(valor.length-(4*i+3));   
			ret = valor + ',' + cents;   
			if (x == 1)
				ret = ' - ' + ret;
			return ret;	
		}	

	function confirmaBemConjuge()
	{
		if(valida())
		{
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_INCLUIR_BEM_CONJUGE+"=1"%>';
			document.form.submit();
		}
	}
</script>
<c:set var="valorConjuge1" value="0"></c:set>
<c:set var="valorConjuge2" value="0"></c:set>
<c:set var="qtdeBemTributavel" value="0"></c:set>
<c:set var="existeBemParticular" value="${giaITCDVo.bemTributavelVo.existeBemParticular}"/>
<jsp:include page="/util/ViewVerificaErro.jsp"/>
<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
</head>
<body class="SEFAZ-Body" onload="verificaErro();">
<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
<CENTER>
<form method="POST" name="form" action="#">
		<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
			<tr class="SEFAZ-TR-Titulo">
				<td>Resultados da Pesquisa</td>
			</tr>
			<c:if test="${existeBemParticular}">
				<tr>
					<td>
						<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
							<tr class="SEFAZ-TR-Titulo">
								<td colspan="4">Bens Particulares</td>
							</tr>
							<tr class="SEFAZ-TR-SubTitulo">
									<td>Classificação Tipo Bem</td>
									<td>Descrição do Bem</td>
									<td>Tipo do Bem</td>                        
                           <td>Valor do Bem</td>                            
							</tr>							
							<c:forEach items="${giaITCDVo.bemTributavelVo.collVO}" var="bemTributavel" varStatus="contador">
								<c:if test="${bemTributavel.bemParticular.valorCorrente == SIM}">
									<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
									<c:if test="${contador.count % 2 != 0}">
										<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
									</c:if>							
									<tr class="<c:out value="${linhaEstilo}"/>">
										<td class="SEFAZ-TD-CampoSaida"><div align="center"><c:out value="${bemTributavel.bemVo.classificacaoTipoBem.textoCorrente}"/></div></td>								
										<td class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavel.descricaoBemTributavel}"/></td>
										<td class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavel.bemVo.descricaoTipoBem}"/></td>
                              
                              <c:choose>
                                    <c:when test="${bemTributavelVo.concordaComValorArbitrado.textoCorrente == 'SIM'}">
                                       <td align="center" valign="top" colspan="1">R$ <c:out value="${bemTributavelVo.valorInformadoFormatado}"/></td>                                       
                                    </c:when>
                                    <c:otherwise>
                                       <td align="center" valign="top" colspan="1">R$ <b><c:out value="${bemTributavelVo.valorMercadoFormatado}"></c:out></b></td>
                                    </c:otherwise>
                              </c:choose>
                              
									</tr>		
									<c:set var="qtdeBemParticular" value="${contador}"/>
								</c:if>
								<c:if test="${bemTributavel.bemParticular.valorCorrente == NAO}">
									<c:set var="qtdeBemTributavel" value="1"/>
								</c:if>
							</c:forEach>							
						</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>								
			</c:if>
			<c:if test="${not existeBemParticular or qtdeBemTributavel != 0}">
				<tr>
					<td>
						<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
							<tr class="SEFAZ-TR-Titulo">
								<td colspan="4">Bens em comum</td>
							</tr>
							<tr class="SEFAZ-TR-Titulo">
								<td>&nbsp;</td>
								<td>Descrição do Bem</td>
								<td id="valorConjuge">Valor para o Conjuge</td>
								<td>Valor Total do Bem</td>
							</tr>
							<c:set var="flagNaoMostraBotaoConfirmar" value="1"></c:set>							
							<c:forEach var="bemTributavelVo" items="${giaITCDVo.bemTributavelVo.collVO}" varStatus="contador">
								<c:if test="${bemTributavelVo.bemParticular.valorCorrente == NAO}">	
									<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
									<c:if test="${contador.count % 2 != 0}">
										<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
									</c:if>
									<c:set var="valorConjuge1" value="0"></c:set>
									<c:forEach var="conjuge1" items="${giaITCDVo.conjuge1.collVO}">
										<c:if test="${bemTributavelVo.descricaoBemTributavel == conjuge1.bemTributavelVo.descricaoBemTributavel }">
											<c:if test="${bemTributavelVo.bemVo.classificacaoTipoBem.valorCorrente == conjuge1.bemTributavelVo.bemVo.classificacaoTipoBem.valorCorrente}">
												<c:if test="${bemTributavelVo.bemVo.descricaoTipoBem == conjuge1.bemTributavelVo.bemVo.descricaoTipoBem}">
													<c:set var="valorConjuge1" value="${conjuge1.valorConjuge}"></c:set>
												</c:if>
											</c:if>
										</c:if>
									</c:forEach>
									<c:set var="valorConjuge2" value="0"></c:set>
									<c:forEach var="conjuge2" items="${giaITCDVo.conjuge2.collVO}">
										<c:if test="${bemTributavelVo.descricaoBemTributavel == conjuge2.bemTributavelVo.descricaoBemTributavel}">
											<c:if test="${bemTributavelVo.bemVo.classificacaoTipoBem.valorCorrente == conjuge2.bemTributavelVo.bemVo.classificacaoTipoBem.valorCorrente}">
												<c:if test="${bemTributavelVo.bemVo.descricaoTipoBem == conjuge2.bemTributavelVo.bemVo.descricaoTipoBem}">
													<c:set var="valorConjuge2" value="${conjuge2.valorConjuge}"></c:set>	
												</c:if>
											</c:if>
										</c:if>
									</c:forEach>
									<c:set var="totalValorAtribuidoConjuges" value="${valorConjuge1 + valorConjuge2}"></c:set>
									<c:set var="flagNaoMostra" value="0"></c:set>									
									<c:choose>
										<c:when test="${totalValorAtribuidoConjuges == bemTributavelVo.valorMercado || totalValorAtribuidoConjuges== bemTributavelVo.valorInformadoContribuinte}">
												<c:set var="flagNaoMostra" value="1"></c:set>										
										</c:when>
										<c:otherwise>
												<c:if test="${giaITCDVo.origem == CONJUGE1 and valorConjuge1 != 0}">
													<c:set var="flagNaoMostra" value="1"></c:set>																						
												</c:if>
												<c:if test="${giaITCDVo.origem == CONJUGE2 and valorConjuge2 != 0}">
													<c:set var="flagNaoMostra" value="1"></c:set>																																		
												</c:if>
										</c:otherwise>
									</c:choose>									
									<c:if test="${flagNaoMostra == 0}">
										<c:set var="flagNaoMostraBotaoConfirmar" value="0"></c:set>							
										<tr class="<c:out value="${linhaEstilo}"></c:out>">
											<td>	<input name="<%=Form.CAMPO_CHECK_BEM_CONJUGE%><c:out value="${contador.index}"></c:out>" id="<%=Form.CAMPO_CHECK_BEM_CONJUGE%><c:out value="${contador.index}"></c:out>"  type="checkbox" value="<c:out value="${bemTributavelVo.codigo}"></c:out>" onClick="habilidaCampo(this,'idCont<c:out value="${contador.index}"></c:out>')"/></td>
											<td><c:out value="${bemTributavelVo.descricaoBemTributavel}"></c:out></td>
											<td><div id="idCont<c:out value="${contador.index}"></c:out>" style="display:none;">R$ 
											<c:set var="a"><%=Form.CAMPO_VALOR_BEM_CONJUGE%></c:set>
											<c:set var="b" value="${contador.index}"></c:set>
											<abaco:campoMonetario name="${a}${b}" quantidadeDigitosInteiros="13" size="20" value="" quantidadeCasasDecimais="2"></abaco:campoMonetario><font color="red">*</font></div></td>
											<td>R$
												<c:choose>
													<c:when test="${giaITCDVo.tipoProtocoloGIA.valorCorrente == PROTOCOLO_AUTOMATICO}">
														<c:choose>
															<c:when test="${bemTributavelVo.concordaComValorArbitrado.valorCorrente == SIM}">
																<c:out value="${bemTributavelVo.valorMercadoFormatado}"/>
															</c:when>
															<c:otherwise>
																<c:out value="${bemTributavelVo.valorInformadoFormatado}"/>
															</c:otherwise>
														</c:choose>
													</c:when>
													<c:otherwise>
														<c:out value="${bemTributavelVo.valorMercadoFormatado}"/>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>	
									</c:if>					
									<c:if test="${flagNaoMostra != 0}">
										<tr class="<c:out value='${linhaEstilo}'/>">
											<td>	<input name="<%=Form.CAMPO_CHECK_BEM_CONJUGE%><c:out value="${contador.index}"></c:out>" id="<%=Form.CAMPO_CHECK_BEM_CONJUGE%><c:out value="${contador.index}"></c:out>"  type="checkbox" value="<c:out value="${bemTributavelVo.codigo}"></c:out>" onClick="habilidaCampo(this,'idCont<c:out value="${contador.index}"></c:out>')" disabled="true"/></td>
											<td><c:out value="${bemTributavelVo.descricaoBemTributavel}"></c:out></td>			
											<c:choose>
												<c:when test="${totalValorAtribuidoConjuges == bemTributavelVo.valorMercado || totalValorAtribuidoConjuges == bemTributavelVo.valorInformadoContribuinte}">						
													<c:choose>
														<c:when test="${valorConjuge1 == totalValorAtribuidoConjuges}">
															<c:choose>
																<c:when test="${giaITCDVo.origem == CONJUGE1}">
																	<td><c:out value="Bem já atribuído para este Cônjuge."/></td>														
																</c:when>
																<c:otherwise>
																	<td><c:out value="Valor do bem atribuído totalmente ao Cônjuge 1"/></td>																											
																</c:otherwise>
															</c:choose>														
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${valorConjuge2 == totalValorAtribuidoConjuges}">
																	<c:choose>
																		<c:when test="${giaITCDVo.origem != CONJUGE2}">
																			<td><c:out value="Valor do bem atribuído totalmente ao Cônjuge 2"/></td>																											
																		</c:when>
																		<c:otherwise>
																			<td><c:out value="Bem já atribuído para este Cônjuge."/></td>														
																		</c:otherwise>
																	</c:choose>																
																</c:when>
																<c:otherwise>
																	<c:if test="${valorConjuge1 + valorConjuge2 == totalValorAtribuidoConjuges}">
																			<td><c:out value="Bem já atribuído para este cônjuge."/></td>													
																	</c:if>																
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<c:if test="${valorConjuge1 != 0 and giaITCDVo.origem == CONJUGE1}">
														<td><c:out value="Bem já atribuído para este cônjuge."/></td>													
													</c:if>
													<c:if test="${valorConjuge2 != 0  and giaITCDVo.origem == CONJUGE2}">
															<td><c:out value="Bem já atribuído para este Cônjuge."/></td>																											
													</c:if>
												</c:otherwise>
											</c:choose>
											<td>R$												
												<c:choose>
													<c:when test="${giaITCDVo.tipoProtocoloGIA.valorCorrente == PROTOCOLO_AUTOMATICO}">
														<c:choose>
															<c:when test="${bemTributavelVo.concordaComValorArbitrado.valorCorrente == SIM}">
																<c:out value="${bemTributavelVo.valorMercadoFormatado}"/>
															</c:when>
															<c:otherwise>
																<c:out value="${bemTributavelVo.valorInformadoFormatado}"/>
															</c:otherwise>
														</c:choose>
													</c:when>
													<c:otherwise>
														<c:out value="${bemTributavelVo.valorMercadoFormatado}"/>
													</c:otherwise>
												</c:choose>											
											</td>
										</tr>	
									</c:if>													
								</c:if>
							</c:forEach>												
						</table>				
					</td>
				</tr>			
			</c:if>
		</table>
		<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr> 
				<td align="center">
					<abaco:botaoCancelar/>
					<abaco:botaoVoltar nomeContadorSubmit="1"></abaco:botaoVoltar>
					<c:if test="${flagNaoMostraBotaoConfirmar == 0}">
						<input type="button" value="   Confirmar   " class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR%>" id="<%=Form.BOTAO_CONFIRMAR%>" onClick="confirmaBemConjuge();">					
					</c:if>
				</td>
			</tr>
			<tr>
				<td><font color="red"><b>* Campos Obrigatórios</b></font></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
</form>
</CENTER>
<g:mostrarRodape/>
</body>
</html>
