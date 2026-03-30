<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewDetalharAvaliacaoBem.jsp
* Criação : Março de 2007 / Elizabeth Barbosa Moreira
* Revisão: 
* Data revisão: 
* Log : 
*/
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.avaliacao.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
        <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
        <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
        <script type="text/javascript" language="javascript">
		  
		   </script>
			<jsp:include page="/util/ViewVerificaErro.jsp"/>
			<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
		</head>
			<body class="SEFAZ-Body">
				<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
                <center>
                    <form method="POST" action="" name="form">
                        <table class="SEFAZ-TABLE-Moldura" cellspacing="0" cellpadding="0" border="0" width="740" align="center">
									<tr class="SEFAZ-TR-Titulo" align="center"> 
										<td colspan="2">Dados da avalia&ccedil;&atilde;o do Bem </td>
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
										<td class="SEFAZ-TD-RotuloSaida" width="50%" >Avaliação Judicial:&nbsp;</td>
										<td class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavelVo.avaliacaoBemTributavelVo.avaliacaoJudicial.textoCorrente}"/> </td>
									</tr>
									<tr>
										<td class="SEFAZ-TD-RotuloSaida">Isenção prevista em Lei:&nbsp;</td>
										<td class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavelVo.avaliacaoBemTributavelVo.isento.textoCorrente}"/></td>
									</tr>
                           <c:if test="${bemTributavelVo.bemVo.classificacaoTipoBem.valorCorrente == 1 && bemTributavelVo.fichaImovelVo.valorPercentualTransmitido > 0}">
                              <tr>
                                 <td class="SEFAZ-TD-RotuloSaida">Percentual transmitido do Imóvel:&nbsp;</td>
                                 <td class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavelVo.fichaImovelVo.valorPercentualTransmitidoFormatado}"/>&nbsp;%</td>
                              </tr>
                           </c:if>
									<tr>
										<td class="SEFAZ-TD-RotuloSaida">Valor declarado:&nbsp;</td>
										<td class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavelVo.valorMercadoInformadoFormatado}"/></td>
									</tr>
									<tr>
										<td class="SEFAZ-TD-RotuloSaida">Valor da Avaliação:&nbsp;</td>
										<td class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavelVo.avaliacaoBemTributavelVo.valorAvaliacaoFormatado}"/></td>
										</tr>
									<tr>
										<td class="SEFAZ-TD-RotuloSaida">Data da Avalia&ccedil;&atilde;o:&nbsp;</td>
										<td class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavelVo.avaliacaoBemTributavelVo.dataAvaliacaoFormatado}"/></td>
									</tr>
									<tr>
										<td class="SEFAZ-TD-RotuloSaida">Data de cadastro da Avalia&ccedil;&atilde;o:&nbsp;</td>
										<td class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavelVo.avaliacaoBemTributavelVo.dataCadastroFormatado}"/></td>
									</tr>
									<tr>
										<td class="SEFAZ-TR-Titulo" colspan="2">Servidores</td>
									</tr>
									<tr>
										<c:forEach var="avaliacaoBemTributavelServidorVo" items="${bemTributavelVo.avaliacaoBemTributavelVo.avaliacaoBemTributavelServidorVo.collVO}"  varStatus="contador">
											<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
											<c:if test="${contador.count % 2 != 0}">
											<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"/>
											</c:if>
											<tr class="<c:out value="${linhaEstilo}"/>">
											<td colspan="2"><c:out value="${avaliacaoBemTributavelServidorVo.servidorSefazVo.nomeServidor}"/></td></br>
											</tr>
										</c:forEach>
									</tr>
									<tr>
										<td class="SEFAZ-TD-RotuloSaida">Observação:&nbsp;</td>
										<td class="SEFAZ-TD-CampoSaida"><c:out value="${bemTributavelVo.avaliacaoBemTributavelVo.observacao}"/></td>
									</tr>  	
									<tr>
										<td>&nbsp;&nbsp;</td>
									</tr>
								</table>
								<abaco:botaoVoltar nomeContadorSubmit="btnVoltarDetalharAvaliacaoBem"/>
								<abaco:botaoCancelarSemMensagem/>
								<abaco:mensagemAguardeCarregando/>
							</form>
					</center>
				<g:mostrarRodape></g:mostrarRodape>
			</body>
</html>			