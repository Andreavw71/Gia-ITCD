<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarGIAITCDDoacaoAbaBeneficiarios.jsp
* Criação : Outubro de 2007 / Rogério Sanches de Oliveira
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form"%>
<form method="POST" name="form" action="#">
  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
  <c:forEach var="beneficiarios" items="${giaITCDVo.beneficiarioVo.collVO}">
    <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"></c:set>
	  <tr>
		   <td colspan="4" bgcolor="#336699" class="SEFAZ-TR-Titulo"><div align="center">Beneficiário</div></td>
    </tr>
    <tr> 
			<td class="SEFAZ-TD-RotuloSaida" >Nome:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiarios.pessoaBeneficiaria.nomeContribuinte}"/> </td>
			<td class="SEFAZ-TD-RotuloSaida" >CPF/CNPJ:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiarios.pessoaBeneficiaria.numrDocumento}"/></td>
    </tr>
    <tr> 
			<td class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${beneficiarios.pessoaBeneficiaria.endereco}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">N&uacute;mero:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${beneficiarios.pessoaBeneficiaria.enderecoNumero}"/></td>
    </tr>
    <tr> 
			<td class="SEFAZ-TD-RotuloSaida" >Complemento:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${beneficiarios.pessoaBeneficiaria.enderecoComplemento}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">Ponto de refer&ecirc;ncia:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${beneficiarios.pessoaBeneficiaria.pontoReferencia}"/></td>
	 </tr>
    <tr> 
			<td class="SEFAZ-TD-RotuloSaida" >Bairro:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${beneficiarios.pessoaBeneficiaria.enderecoBairro}"/></td>
			<td class="SEFAZ-TD-RotuloSaida" >CEP:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${beneficiarios.pessoaBeneficiaria.enderecoCEP}"/></td>
	 </tr>
    <tr> 
			<td class="SEFAZ-TD-RotuloSaida" >Munic&iacute;pio:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${beneficiarios.pessoaBeneficiaria.municipio}"/></td>
			<td class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiarios.pessoaBeneficiaria.siglaUF}"/></td>
    </tr>
    <tr> 
			<td class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${beneficiarios.pessoaBeneficiaria.numrDdd}"/></td>
			<td class="SEFAZ-TD-RotuloSaida" >Telefone:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${beneficiarios.pessoaBeneficiaria.numrTelefone}"/></td>
    </tr>
    <tr> 
			<td class="SEFAZ-TD-RotuloSaida" >E-mail:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida"><c:out value="${beneficiarios.pessoaBeneficiaria.email}"/></td>
			<td class="SEFAZ-TD-RotuloSaida" >Percentual Atribu&iacute;do:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" ><c:out value="${beneficiarios.percentualBemRecebidoFormatado}"/>%</td>
    </tr>	
    <tr>
			<td class="SEFAZ-TD-RotuloSaida" >Valor bens recebidos:&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" >R$<c:out value="${beneficiarios.valorRecebidoSemDoacaoSucessivaFormatado}"/></td>
			<td class="SEFAZ-TD-RotuloSaida" >&nbsp;</td>
			<td class="SEFAZ-TD-CampoSaida" >&nbsp;</td>
    </tr>
    
    <c:if test="${beneficiarios.flagDoacaoAnteriorManualEprocess == 1 || beneficiarios.flagDoacaoAnteriorManualEprocess == 2}">
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
                              <c:if test="${beneficiarios.flagDoacaoAnteriorManualEprocess == 1}">checked="checked"</c:if> disabled="disabled" /> Sim 
                      </label>
                      <label>
                          <input type="radio"
                              <c:if test="${beneficiarios.flagDoacaoAnteriorManualEprocess == 2}">checked="checked"</c:if> disabled="disabled" /> Não
                      </label>
                  </div>
              </td>
          </tr>
      </c:if>
      
	 </c:forEach>
  </table>
  <br/>
  <table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
    <tr> 
      <td align="center"> 
			<input type="button" value="<%=Form.TEXTO_BOTAO_ANTERIOR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_ANTERIOR_CLONE%>" onClick="solicitarAbaBensTributaveis();">
			<abaco:botaoCancelar/>
         <input type="button" value="<%=Form.TEXTO_BOTAO_PROXIMO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PROXIMO_CLONE%>" onClick="solicitarAbaDoacaoSucessiva();">
	  </td>
	</tr>
	</table>
</form>
