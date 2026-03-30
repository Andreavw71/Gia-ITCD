<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">

<%-- 
* * Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT 
* Arquivo : ViewPesquisarContribuinte.jsp 
* Criação :Dezembro de 2008 / Ricardo Vitor de Oliveira Moraes * Revisão : 
* Log : 
*
--%>

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.gov.mt.sefaz.itc.util.integracao.cadastro.Form"%>
<%@ page import="br.com.abaco.util.dominio.DomnOpcaoPesquisaContribuinte"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.Flow"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<%!  boolean primeiraVez; %>
<%   
		int qtde = 0;
		if(request.getAttribute("QtdeIncludePesquisaContribuinteTipoDocumento") != null ){
			qtde = 	((Integer) request.getAttribute("QtdeIncludePesquisaContribuinteTipoDocumento")).intValue()  ;				
			request.setAttribute("QtdeIncludePesquisaContribuinteTipoDocumento" , new Integer(++qtde)) ;
			primeiraVez = true;
		}else{
				request.setAttribute("QtdeIncludePesquisaContribuinteTipoDocumento" , new Integer(0)) ;
				qtde = 	0;
		}
		%>
<script type="text/javascript">
			
			function validaCombo(comboBox)
			{
                if(!verificaCamposW3c(document.getElementById(comboBox),"Selecione uma opção de consulta."))
                {
                    return false;
                }
					 return true;
			}

			function consultaContribuinteOutrosDocumentos(campo, comboBox, origem)
			{
				if(validaCombo(comboBox))
				{
					var numrDocumento = document.getElementById(campo).value;
					var tipoDocumento = document.getElementById(comboBox).value;
					if(validaCampo(campo))
					{
						parametro = "<%=Form.PARAMETRO_OUTROS_DOCUMENTOS%>="+numrDocumento;
						parametro = parametro + "&<%=Form.COMBO_PESQUISA_OPCAO_CONTRIBUINTE_OUTROS_DOCUMENTOS%>="+tipoDocumento;
						if(origem != '')
						{
							parametro = parametro+"&ORIGEM="+origem;
						}
						document.form.action =  '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR_CONTRIBUINTE_OUTROS_DOCUMENTOS+"=1&"%>'+parametro;
						document.form.submit();			
					}
				}
			}
			
			function validaCampo(campo)
			{
				 if(!verificaCamposW3c(document.getElementById(campo),"O Número do documento do contribuinte deve ser informado."))
				 {
					  return false;
				 }
				return true;
			}
			
			function exibeCamposOutrosDocumentos(qtde)
			{
				navegador = buscarTipoNavegador();
				document.getElementById("linkLocalizar"+qtde).style.display='none';
				document.getElementById("selectOutrosDocumentos"+qtde).style.display = navegador;				
				document.getElementById("campoDocumentoOutrosTipos"+qtde).style.display = navegador;
				document.getElementById("botaoLocalizarOutrosDocumentos"+qtde).style.display = navegador;
				document.getElementById("espacoBranco"+qtde).style.display = navegador;
			}

			function submitaConsultaContribuinteOutrosDocumentos(campo, combo, evento)
			{
				tecla = !isNaN(evento.keyCode)? evento.keyCode: evento.which;
				if(tecla == 0)
				{
					tecla = evento.which;
				}
				if(tecla == 13)
				{
					limparLetrasW3c(campo,evento);
					return consultaContribuinteOutrosDocumentos(campo.id, combo);
				}
			}					

			function cadastrarPessoaJuridica()
			{
				window.open("<%=Flow.SERVLET_CADASTRAR_CONTRIBUINTE_PESSOA_JURIDICA_MODABERTO%>","cadastroContribuinte","location=no,toolbar=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,height=650,width=760");
			}
			
			function cadastrarPessoaFisica()
			{
				window.open("<%=Flow.SERVLET_CADASTRAR_CONTRIBUINTE_PESSOA_FISICA_MODABERTO%>","cadastroContribuinte","location=no,toolbar=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,height=650,width=760");
			}
			
</script>
<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
  <tr id="linkCadastrarContribuinte<%out.print(qtde);%>"
      style='<c:if test="${ocultaLinkCadastrar}">display:none</c:if>'>
    <td align="center" width="360" class="SEFAZ-TD-RotuloEntrada">
      <div align="rigth">
        <a href='javascript: cadastrarPessoaJuridica();'>Inclus&atilde;o de Pessoa Jur&iacute;dica</a>
      </div>
    </td>
    <td width="20">&nbsp;</td>
    <td align="center" width="360" class="SEFAZ-TD-RotuloEntrada">
      <div align="left">
        <a href='javascript: cadastrarPessoaFisica();'>Inclus&atilde;o de Pessoa F&iacute;sica</a>
      </div>
    </td>
  </tr>
  <tr style='<c:if test="${ocultaLinkCadastrar}">display:none</c:if>'>
    <td>&nbsp;</td>
  </tr>
</table>
<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
  <tr id="linkLocalizar<%out.print(qtde);%>" style='<c:if test="${naoOculta}">display:none</c:if>'> 
  <td class="SEFAZ-TD-RotuloEntrada" width='<c:out value="${larguraRotuloPadrao}"/>'>
      <c:if test="${not empty nomeTdLocalizarContribuinte}">
        <c:out value="${nomeTdLocalizarContribuinte}"/>:&nbsp;
      </c:if>
      <c:if test="${empty nomeTdLocalizarContribuinte}">Pesquisar Contribuinte:&nbsp;</c:if>
    </td>
    <td class="SEFAZ-TD-CampoEntrada">
      <div align="left">
        <a href='javascript: exibeCamposOutrosDocumentos(<%out.print(qtde);%>)'>Localizar Contribuinte</a>
      </div>
    </td>
  </tr>
  
  <tr id="selectOutrosDocumentos<%out.print(qtde);%>" style='<c:if test="${not naoOculta}">display:none</c:if>'>
    <td class="SEFAZ-TD-RotuloEntrada" width='<c:out value="${larguraRotuloPadrao}"/>'>Op&ccedil;&atilde;o de Consulta:&nbsp;</td>
    <td class="SEFAZ-TD-ComboBox" align="center">
      <select name="<%=Form.COMBO_PESQUISA_OPCAO_CONTRIBUINTE_OUTROS_DOCUMENTOS%><%out.print(qtde);%>" id="<%=Form.COMBO_PESQUISA_OPCAO_CONTRIBUINTE_OUTROS_DOCUMENTOS%><%out.print(qtde);%>">
        <option value=""><%=Form.SELECIONE%></option>
        <c:forEach items="${giaITCDVo.contribuinteIntegracaoVo.colecaoTipoDocumento}" var="tipoDocumento">
			  <c:if test="${not (tipoDocumento.descDocumento eq 'CPF') && not (tipoDocumento.descDocumento eq 'CNPJ')}">
	          <option value='<c:out value="${tipoDocumento.codgDocumento}"/>'><c:out value="${tipoDocumento.descDocumento}"/></option>			  			  
			  </c:if>
        </c:forEach>
      </select>
    </td>
  </tr>
  
  <tr id="campoDocumentoOutrosTipos<%out.print(qtde);%>" style='<c:if test="${not naoOculta}">display:none</c:if>'>
    <td class="SEFAZ-TD-RotuloEntrada"  width='<c:out value="${larguraRotuloPadrao}"/>'>Contribuinte:&nbsp;</td>
    <td class="SEFAZ-TD-CampoEntrada">
      <input type="text" class="SEFAZ-INPUT-Text" name="<%=Form.CAMPO_NUMR_DOCUMENTO_OUTROS_DOCUMENTOS+qtde%>" size="25" id="<%=Form.CAMPO_NUMR_DOCUMENTO_OUTROS_DOCUMENTOS+qtde%>" onblur="return limparLetrasW3c(this, event);" onkeypress="apenasNumeroW3c(this,event);" onkeyup="return apenasNumeroW3c(this,event);"/>
    </td>
  </tr>
  <tr id="botaoLocalizarOutrosDocumentos<%out.print(qtde);%>" style='<c:if test="${not naoOculta}">display:none</c:if>'>
    <td class="SEFAZ-TD-CampoEntrada" colspan="2">
      <div align="center">
        <input type="button" value="   Localizar   " class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PESQUISAR_CONTRIBUINTE_OUTROS_DOCUMENTOS%><%out.print(qtde);%>"
               onclick="consultaContribuinteOutrosDocumentos('<%=Form.CAMPO_NUMR_DOCUMENTO_OUTROS_DOCUMENTOS%><% out.print(qtde); %>','<%=Form.COMBO_PESQUISA_OPCAO_CONTRIBUINTE_OUTROS_DOCUMENTOS%><%out.print(qtde);%>', '<c:out value="${origemPesquisaContribuinte}"/>');"/>
      </div>
    </td>
  </tr>
  <tr id="espacoBranco<%out.print(qtde);%>"
      style='<c:if test="${not naoOculta}">display:none</c:if>'>
    <td>&nbsp;</td>
  </tr>
</table>