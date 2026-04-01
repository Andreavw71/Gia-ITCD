<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManterGIAITCDAbaBeneficiarios.jsp
* Criação : Outubro de 2007 / Rogério Sanches de Oliveira
* Revisão : Fevereiro de 2008/ Rogério Sanches de Oliveira
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page import=" br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoContribuinte"%>
<%
pageContext.setAttribute("INVENTARIO_ARROLAMENTO", new Integer(DomnTipoProcesso.INVENTARIO_ARROLAMENTO));
pageContext.setAttribute("DOACAO", new Integer(DomnTipoProcesso.DOACAO));
%>
<script type="text/javascript" language="javascript">
	function validaFormulario()
	{
		return true;
	}
	function consultarBeneficiario()
	{
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.PARAMETRO_SOLICITAR_PESQUISAR_BENEFICIARIOS+"=1"%>';
		document.form.submit();
	}
	function excluirBeneficiarios(indiceBeneficiario)
	{
		if(confirm('Deseja realmente excluir o Beneficiários?'))
		{
			//					desabilitarBotoes(obterArrayBotoes());
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_EXCLUIR_BENEFICIARIO+"="%>'+indiceBeneficiario;
			document.form.submit();
			return true;
		}
		else
		{
			return false
		}
	}	
	
	<c:if test="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == INVENTARIO_ARROLAMENTO}">
	//Valida Formulario caso o tipoProcesso da GIA for Inventario Arrolamento
	function validaFormulario()
	{
		return true;
	}
	</c:if>
	function salvarBeneficiarios()
	{
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.PARAMETRO_SALVAR_BENEFICIARIOS+"=1"%>';
		document.form.submit();
		return true;
	}
	function adicionarBeneficiarios(percentualCadastrado)
	{
		<c:if test="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == DOACAO}">
			var valorCadastrado = 0;
			if(percentualCadastrado != null) 
								valorCadastrado = percentualCadastrado;
			var vlrNovo = document.form.<%=Form.CAMPO_PERCENTUAL_ATRIBUIDO%>.value;
			if(vlrNovo == '')
			{
				alert('<%=MensagemErro.VALIDAR_BENEFICIARIO_VALOR_PERCENTUAL_RECEBIDO%>');
				return false;
			}
			else
			{
			vlrNovo = parseFloat(replaceAll(vlrNovo,",","."));
			}
			if ( vlrNovo <=0)
			{
					alert('<%=MensagemErro.VALIDAR_BENEFICIARIO_VALOR_PERCENTUAL_RECEBIDO_ZERO%>');
					return false;
			}
			var tot = parseFloat(valorCadastrado + vlrNovo);
			if(tot > 100)
			{
				alert('<%=MensagemErro.VALIDAR_BENEFICIARIO_VALOR_PERCENTUAL_RECEBIDO_MAIOR%>');
				return false;
			}
		</c:if>
      
      var exibicaoFlagDoacaoAnteriorManualEprocess = <%= request.getAttribute("exibicaoFlagDoacaoAnteriorManualEprocess") %>;
      if(!exibicaoFlagDoacaoAnteriorManualEprocess){
         var flagDoacaoAnterior = document.querySelector('input[name="flagDoacaoAnteriorManualEprocess"]:checked');      
            if (!flagDoacaoAnterior) {
               alert('Por favor, informe se houve doação anterior neste ano-calendário.');
               return false;
            }else if(flagDoacaoAnterior.value == 1){
               alert('Caso o contribuinte tenha realizado, neste ano-calendário, doação para o mesmo beneficiário, com a utilização de e-Process do tipo Declaração Manual, Denúncia Espontânea ou Requerimento para Outros Assuntos deverá realizar o protocolo da doação utilizando o mesmo tipo anterior, não sendo possível a realização de cálculo automático.');
               return false;
            } 
      }
			document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.BOTAO_ADICIONAR_BENEFICIARIO+"=1"%>';
			document.form.submit();
			return true;		
	}
	function desabilitaBotaoInclui()
	{
		document.getElementById('idPesquisar').style.display = 'none';
		document.getElementById('idPesquisar1').style.display = 'none';
	}
	
	function localizar(campo)
	{
		if(campo == 'cpf')
		{
			if(navigator.appName != 'Microsoft Internet Explorer')
				document.getElementById('tblInventariante').style.display = 'table';
			else
				document.getElementById('tblInventariante').style.display = 'block';
		}
		}
	
</script>
<form method="POST" name="form" action="">
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr class="SEFAZ-TR-Titulo">
			<td colspan="4">Bens</td>
		</tr>
		<tr>
			<td width="15%" class="SEFAZ-TD-RotuloSaida">Valor Bens:&nbsp;</td>
			<td width="30%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.valorTotalBensDeclaradosFormatado}"></c:out></td>
			<td width="30%" class="SEFAZ-TD-RotuloSaida">Base de Cálculo Tributável:&nbsp;</td>
			<td width="25%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.valorFracaoIdealFormatado}"></c:out></td>
		</tr>
		<c:if test="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == DOACAO}">
		<tr>
			<td colspan="4">&nbsp;
				<c:forEach var="beneficiario" items="${giaITCDVo.beneficiarioVo.collVO}">
					<c:set var="percentualTotal" value="${beneficiario.percentualBemRecebido + percentualTotal}"></c:set>
				</c:forEach>
			</td>
		</tr>
		</c:if>
	</table>
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">		
			<tr class="SEFAZ-TR-Titulo" id="idPesquisar">
				<td colspan="2">Beneficiários</td>
			</tr>
			<tr id="idPesquisar1">
				<td colspan="2">	
					<c:choose>
						<c:when test="${empty giaITCDVo.responsavelVo.numrDocumento}">
							<c:set var="naoOculta" value='true' scope="request"/>													
						</c:when>
						<c:otherwise>
							<c:set var="naoOculta" value='false' scope="request"/>																				
						</c:otherwise>
					</c:choose>
					<c:set var="larguraRotuloPadrao" value="370" scope="request"/>
					<c:set var="nomeTdLocalizarContribuinte" value="CPF Beneficiario" scope="request"/>
					<c:set var="origemPesquisaContribuinte" scope="request"><%=DomnTipoContribuinte.BENEFICIARIOS%></c:set>
					<c:set var="campoObrigatorio" scope="request" value="true"></c:set>
					<c:choose>
						<c:when test="${not empty exceptionCadastro && giaITCDVo.origem == origemPesquisaContribuinte}">
							<c:set var="ocultaLinkCadastrar" value="false" scope="request"/>
						</c:when>
						<c:otherwise>
							<c:set var="ocultaLinkCadastrar" value="true" scope="request"/>					
						</c:otherwise>
					</c:choose>				
					<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteIncludeNova.jsp"/>
					<c:remove var="naoOculta" scope="request"/>
					<c:remove var="nomeTdLocalizarContribuinte" scope="request"/>
					<c:remove var="larguraRotuloPadrao" scope="request"/>
					<c:remove var="origemPesquisaContribuinte" scope="request"/>
					<c:remove var="ocultaLinkCadastrar" scope="request"/>
					<c:remove var="campoObrigatorio" scope="request"/>
				</td>
			</tr>
			<c:if test="${not empty giaITCDVo.beneficiarioVo.pessoaBeneficiaria.numrDocumento}">
				<tr>
					<td colspan="2">
						<table cellspacing="0" cellpadding="0" border="0" width="740" align="center" id="tblInventariante" > 
							<tr>
								<td align="left" class="SEFAZ-TD-RotuloSaida">Beneficiário:&nbsp; </td>
								<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.nomeContribuinte}"></c:out></td>
								<td align="left" class="SEFAZ-TD-RotuloSaida">CPF/CNPJ:&nbsp;</td>
								<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.numrDocumento}"></c:out></td>
							 </tr>
							<tr>
								<td align="left" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
								<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.endereco}"></c:out></td>
								<td align="left" class="SEFAZ-TD-RotuloSaida">N&uacute;mero:&nbsp;</td>
								<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.enderecoNumero}"></c:out></td>
							</tr>
							<tr>
								<td align="left" class="SEFAZ-TD-RotuloSaida">Complemento:&nbsp;</td>
								<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.enderecoComplemento}"></c:out></td>
								<td align="left" class="SEFAZ-TD-RotuloSaida">refer&ecirc;ncia:&nbsp; </td>
								<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.pontoReferencia}"></c:out></td>
							</tr>
							<tr>
								<td align="left" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
								<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.enderecoBairro}"></c:out></td>
								<td align="left" class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
								<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.enderecoCEP}"></c:out></td>
							</tr>
							<tr>
								<td align="left" class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
								<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.municipio}"></c:out></td>						
								<td align="left" class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
								<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.siglaUF}"></c:out></td>
							</tr>
							<tr>
								<td align="left" class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
								<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.numrDdd}"></c:out></td>
								<td align="left" class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
								<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.numrTelefone}"></c:out></td>
							</tr>
							<tr>
								<td align="left" class="SEFAZ-TD-RotuloSaida">E-mail:&nbsp;</td>
								<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.beneficiarioVo.pessoaBeneficiaria.email}"></c:out></td>
								<td align="left" class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
								<td align="left" class="SEFAZ-TD-CampoSaida">&nbsp;</td>
							</tr>					
						</table>	
					</td>
				</tr>	
				<tr>
					<td class="SEFAZ-TD-RotuloEntrada">Informar Percentual Transmitido ao Beneficiário:&nbsp;</td>
					<td class="SEFAZ-TD-CampoEntrada">
						<c:if test="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == DOACAO}">
								<abaco:campoMonetario 
									name="<%=Form.CAMPO_PERCENTUAL_ATRIBUIDO%>" 
									quantidadeDigitosInteiros="3" 
									size="7" 
									value="" 
									quantidadeCasasDecimais="${giaITCDVo.beneficiarioVo.quantidadeCasasDecimaisPercentualRecebido}" 
									mostrarZero="false">
								</abaco:campoMonetario><font color="red">*</font>					
						</c:if>
					</td>
				</tr>
            <c:choose>
               <c:when test="${not exibicaoFlagDoacaoAnteriorManualEprocess}">            
                  <tr>
                      <td class="SEFAZ-TD-RotuloEntrada" colspan="5" style="padding: 5px; position: relative;">
                          <div style="width: 68%; text-align: right; font-weight: bold;">
                              Neste ano-calendário, já foram realizadas duas ou mais doações anteriores, entre mesmo doador e beneficiário, seja por meio do Protocolo Automático da GIA-ITCD-e ou por qualquer outro tipo de e-Process (como Declaração Manual, Denúncia Espontânea, Requerimento para Outros Assuntos etc.)
                          </div>
                          <div style="position: absolute; right: 0%; top: 50%; transform: translateY(-50%); display: inline-block;">
                              <label style="margin-right: 10px;">
                                  <input type="radio" name="flagDoacaoAnteriorManualEprocess" value="1" 
                                      <c:if test="${giaITCDVo.beneficiarioVo.flagDoacaoAnteriorManualEprocess == 1}">checked</c:if>/> Sim 
                              </label>
                              <label>
                                  <input type="radio" name="flagDoacaoAnteriorManualEprocess" value="2"
                                      <c:if test="${giaITCDVo.beneficiarioVo.flagDoacaoAnteriorManualEprocess == 2}">checked</c:if>/> Não
                              </label>
                              <font color="red">*&nbsp;</font>
                              <c:if test="${alterar == null}">
                                 <input type="button" name="<%=Form.BOTAO_ADICIONAR%>" id="<%=Form.BOTAO_ADICIONAR%>" value="<%=Form.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" onClick="adicionarBeneficiarios(<c:out value="${percTot}"></c:out>);"></input>					
                              </c:if>
                              <c:if test="${alterar != null}">
                                    <input type="button" name="<%=Form.BOTAO_ALTERAR%>" value="<%=Form.TEXTO_BOTAO_ALTERAR%>" class="SEFAZ-INPUT-Botao" onClick="adicionarBeneficiarios(<c:out value="${percTot}"></c:out>);"></input>					
                              </c:if>
                          </div>                    
                      </td>
                  </tr>
            </c:when>
            <c:otherwise>
                  <tr>
                      <td class="SEFAZ-TD-RotuloEntrada" colspan="5" style="padding: 5px; position: relative;">                         
                          <div style="position: absolute; right: 0%; top: 50%; transform: translateY(-50%); display: inline-block;">
                              <br>
                              <c:if test="${alterar == null}">
                                 <input type="button" name="<%=Form.BOTAO_ADICIONAR%>" id="<%=Form.BOTAO_ADICIONAR%>" value="<%=Form.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" onClick="adicionarBeneficiarios(<c:out value="${percTot}"></c:out>);"></input>					
                              </c:if>
                              <c:if test="${alterar != null}">
                                    <input type="button" name="<%=Form.BOTAO_ALTERAR%>" value="<%=Form.TEXTO_BOTAO_ALTERAR%>" class="SEFAZ-INPUT-Botao" onClick="adicionarBeneficiarios(<c:out value="${percTot}"></c:out>);"></input>					
                              </c:if>
                          </div>                    
                      </td>
                  </tr>
            </c:otherwise>
            </c:choose>
            
				<tr>
					<td>&nbsp;</td>
				</tr>
			</c:if>
			<c:if test="${giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == DOACAO}">
				<c:if test="${percentualTotal == '100'}">
					<script type="text/javascript">desabilitaBotaoInclui();</script>
				</c:if>
			</c:if>
		<tr>
			<td colspan="2">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center">
					<c:forEach var="beneficiario" items="${giaITCDVo.beneficiarioVo.collVO}" varStatus="contador">				
						<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"></c:set>
						<c:if test="${contador.count % 2 != 0}">
							<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
						</c:if>
						<c:if test="${contador.index == 0}">
							<tr class="SEFAZ-TR-Titulo">
								<td colspan="7">Beneficiários Cadastrados</td>
							</tr>
						</c:if>
						<tr class="<c:out value="${linhaEstilo}"></c:out>">
							<td width="23%" class="SEFAZ-TD-RotuloSaida">Nome:&nbsp;</td>
							<td width="24%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.nomeContribuinte}"></c:out></td>
							<td width="21%" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
							<td width="16%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.numrDocumento}"></c:out></td>
							<td width="16%" class="SEFAZ-TD-RotuloSaida"><a href="javascript:void(excluirBeneficiarios(<c:out value="${contador.index}"></c:out>));">Excluir</a>&nbsp;&nbsp;</td>
						</tr>
						<tr class="<c:out value="${linhaEstilo}"></c:out>">
							<td width="25%" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
							<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.endereco}"/></td>
							<td width="12%" class="SEFAZ-TD-RotuloSaida">Número:&nbsp;</td>
							<td colspan="2" width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.enderecoNumero}"/></td>
						</tr>
						<tr class="<c:out value="${linhaEstilo}"></c:out>">
							<td width="25%" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
							<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.enderecoBairro}"/></td>
							<td width="12%" class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
							<td colspan="2" width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.enderecoCEP}"/></td>
						</tr>
						<tr class="<c:out value="${linhaEstilo}"></c:out>">
							<td width="25%" class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
							<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.municipio}"/></td>
							<td width="12%" class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
							<td colspan="2" width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.siglaUF}"/></td>
						</tr>
						<tr>
							<td align="left" class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
							<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.numrDdd}"/></td>
							<td align="left" class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
							<td align="left" colspan="2" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.pessoaBeneficiaria.numrTelefone}"/></td>
						</tr>
						<tr class="<c:out value="${linhaEstilo}"></c:out>">
							<td class="SEFAZ-TD-RotuloSaida">Percentual Atribuído:&nbsp;</td>
								<td class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.percentualBemRecebidoFormatado}"/>%</td>
							<td class="SEFAZ-TD-RotuloSaida">Valor Bens Recebido:&nbsp;</td>
							<td colspan="2" class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiario.valorRecebidoSemDoacaoSucessivaFormatado}"/></td>
						</tr>
                  
                  <c:if test="${not exibicaoFlagDoacaoAnteriorManualEprocess}">
                     <c:if test="${beneficiario.flagDoacaoAnteriorManualEprocess == 1 || beneficiario.flagDoacaoAnteriorManualEprocess == 2}">
                         <tr class="<c:out value="${linhaEstilo}"></c:out>">
                             <td colspan="5" style="padding-top: 5px; padding-bottom: 15px; font-weight: bold; background-color: #dddddd;">
                                 <div style="width: 90%;">
                                     <span style=" margin-left: 10%;">
                                       Neste ano-calendário, já foram realizadas duas ou mais doações anteriores, entre mesmo doador
                                     </span>  
                                     <span style=" margin-left: 10%;">  
                                       beneficiário, seja por meio do Protocolo Automático da GIA-ITCD-e ou por qualquer outro tipo de e-Process
                                     </span> 
                                     <span style=" margin-left: 10%;">
                                       (como Declaração Manual, Denúncia Espontânea, Requerimento para Outros Assuntos etc.)
                                     </span>                                
                                     <label style="margin-left: 9px; margin-right: 9px;">
                                         <input type="radio"
                                             <c:if test="${beneficiario.flagDoacaoAnteriorManualEprocess == 1}">checked="checked"</c:if> disabled="disabled" /> Sim 
                                     </label>
                                     <label>
                                         <input type="radio"
                                             <c:if test="${beneficiario.flagDoacaoAnteriorManualEprocess == 2}">checked="checked"</c:if> disabled="disabled" /> Não
                                     </label>
                                 </div>
                             </td>
                         </tr>
                     </c:if>
                  </c:if>
                  
						<tr class="SEFAZ-TR-Titulo">
							<td colspan="5">&nbsp;</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>
	<br/>
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr> 
			<td align="center">
			<input type="button" value="<%=Form.TEXTO_BOTAO_ANTERIOR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_ANTERIOR_CLONE%>" onClick="solicitarAbaBensTributaveis();">
				<abaco:botaoCancelar/>
				<input type="button" value="<%=FormITC.TEXTO_BOTAO_SALVAR%>" class="SEFAZ-INPUT-Botao" name="<%=FormITC.BOTAO_CONFIRMAR_CLONE%>" id="btnConfirmar" onClick="javascript: salvarBeneficiarios(<c:out value="${percentualTotal}"></c:out>);"></input>
            <input type="button" value="<%=Form.TEXTO_BOTAO_PROXIMO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PROXIMO_CLONE%>" onClick="solicitarAbaDoacaoSucessiva();">
			</td>
		</tr>
		<tr>
			<td><font color="red"><b>* Campos Obrigatórios</b></font></td>
		</tr>
	</table>
	<abaco:log/>
</form>
