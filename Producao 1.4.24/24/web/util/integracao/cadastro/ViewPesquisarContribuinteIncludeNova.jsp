<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewPesquisarContribuinte.jsp
* Criação :Dezembro de 2008 / Ricardo Vitor de Oliveira Moraes
* Revisão :
* Log : 
*/
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.gov.mt.sefaz.itc.util.integracao.cadastro.Form"%>
<%@ page import="br.com.abaco.util.dominio.DomnOpcaoPesquisaContribuinte"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.Flow"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
	<%!  boolean primeiraVez; %>
		<%   
		int qtde = 0;
		if(request.getAttribute("QtdeIncludePesquisaContribuinte") != null ){
			qtde = 	((Integer) request.getAttribute("QtdeIncludePesquisaContribuinte")).intValue()  ;				
			request.setAttribute("QtdeIncludePesquisaContribuinte" , new Integer(++qtde)) ;
			primeiraVez = true;
		}else{
				request.setAttribute("QtdeIncludePesquisaContribuinte" , new Integer(0)) ;
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

			function alterarNomeInput(campo, comboBox, origem)
			{
				if(validaCombo(comboBox))
				{
					var numrDocumento = document.getElementById(campo).value;
					var tipoDocumento = document.getElementById(comboBox).value;
					var parametro = obtemParametroTipoDocumento(tipoDocumento);
					if(validaCampo(campo, parametro))
					{
						parametro = "PARAMETRO_"+parametro+"="+numrDocumento;
						if(origem != '')
						{
							parametro = parametro+"&ORIGEM="+origem;
						}
						document.form.action =  '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_PESQUISAR_CONTRIBUINTE+"=1&"%>'+parametro;
						document.form.submit();			
					}
				}
			}
			
			function validaCampo(campo,parametro)
			{
				if(parametro == "<%=DomnOpcaoPesquisaContribuinte.DESCRICAO_CPF%>")
				{
					if(!verificaCamposW3c(document.getElementById(campo), 'Informe o número do CPF'))
					{
						return false;
					}
					if(!(validaCpfW3c(document.getElementById(campo)) != false))
					{
						return false;
					}
					return true;
				}
				else if(parametro == "<%=DomnOpcaoPesquisaContribuinte.DESCRICAO_CNPJ%>")
				{
					if(!verificaCamposW3c(document.getElementById(campo),'Informe o número do CNPJ'))
					{
						return false;
					}
					addZeroW3c(document.getElementById(campo), 14);					
					return validaCnpjW3c(document.getElementById(campo));
				}
				else if(parametro == "IE")
				{
					if(!verificaCamposW3c(document.getElementById(campo),'Informe o número da Inscrição Estadual'))
					{
						return false;
					}
					addZeroW3c(document.getElementById(campo), 11);									
					return validaInscEstdW3c(document.getElementById(campo).value)
				}
				return true;
			}
			
			
			function obtemParametroTipoDocumento(tipoDocumento)
			{
				var descricao = "";
				if(tipoDocumento != 0)
				{
					if(tipoDocumento == <%=DomnOpcaoPesquisaContribuinte.CPF%>)
					{
						descricao += "<%=DomnOpcaoPesquisaContribuinte.DESCRICAO_CPF%>";
					}
					else if(tipoDocumento == <%=DomnOpcaoPesquisaContribuinte.CNPJ%>)
					{
						descricao += "<%=DomnOpcaoPesquisaContribuinte.DESCRICAO_CNPJ%>";
					}
					else if(tipoDocumento == <%=DomnOpcaoPesquisaContribuinte.INSCRICAO_ESTADUAL%>)
					{
						descricao += "IE";
					}
				}
				return descricao;			
			}
			
			function exibeCampos(qtde)
			{
				navegador = buscarTipoNavegador();
				document.getElementById("linkLocalizar"+qtde).style.display='none';
				document.getElementById("selectDocumento"+qtde).style.display = navegador;
				document.getElementById("campoDocumento"+qtde).style.display = navegador;				
				document.getElementById("botaoLocalizar"+qtde).style.display = navegador;						
				document.getElementById("espacoBranco"+qtde).style.display = navegador;
			}

			function submitaConsultaContribuinte(campo, combo, evento)
			{
				tecla = !isNaN(evento.keyCode)? evento.keyCode: evento.which;
				if(tecla == 0)
				{
					tecla = evento.which;
				}
				if(tecla == 13)
				{
					limparLetrasW3c(campo,evento);
					return alterarNomeInput(campo.id, combo);
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
			<tr id="linkCadastrarContribuinte<%out.print(qtde);%>" style='<c:if test="${ocultaLinkCadastrar}">display:none</c:if>'> 
				<td align="center" width="360" class="SEFAZ-TD-RotuloEntrada">
					<div align="rigth" ><a href='javascript: cadastrarPessoaJuridica();'>Inclusão de Pessoa Jurídica</a></div>
				</td>
				<td width="20">&nbsp;</td>
				<td align="center" width="360" class="SEFAZ-TD-RotuloEntrada">
					<div align="left"><a href='javascript: cadastrarPessoaFisica();'>Inclusão de Pessoa Física</a></div>
				</td>
			</tr>
			<tr style='<c:if test="${ocultaLinkCadastrar}">display:none</c:if>'>
				<td>&nbsp;</td>
			</tr>
		</table>
	<table cellspacing="1" cellpadding="0" border="0" width="740" align="center" >	
		<tr id="linkLocalizar<%out.print(qtde);%>" style='<c:if test="${naoOculta}">display:none</c:if>'>
				<td class="SEFAZ-TD-RotuloEntrada" width='<c:out value="${larguraRotuloPadrao}"/>'>
						<c:if test="${not empty nomeTdLocalizarContribuinte}"><c:out value="${nomeTdLocalizarContribuinte}"/>:&nbsp;</c:if>
						<c:if test="${empty nomeTdLocalizarContribuinte}">Pesquisar Contribuinte:&nbsp;</c:if>						
				</td>
				<td class="SEFAZ-TD-CampoEntrada">
					<div align="left">
						<a href='javascript: exibeCampos(<%out.print(qtde);%>)'>Localizar Contribuinte</a>
					</div>
				</td>
		</tr>
		
      <tr id="selectDocumento<%out.print(qtde);%>" style='<c:if test="${not naoOculta}">display:none</c:if>'>
			<td class="SEFAZ-TD-RotuloEntrada" width='<c:out value="${larguraRotuloPadrao}"/>'>Op&ccedil;&atilde;o de Consulta:&nbsp;</td>
			<td class="SEFAZ-TD-ComboBox" align="center">
				<select name="<%=Form.COMBO_PESQUISA_OPCAO_CONTRIBUINTE%><%out.print(qtde);%>" id="<%=Form.COMBO_PESQUISA_OPCAO_CONTRIBUINTE%><%out.print(qtde);%>" >
					<option value=""><%=Form.SELECIONE%></option>					
					<option value="<%=DomnOpcaoPesquisaContribuinte.CPF%>"><%=DomnOpcaoPesquisaContribuinte.DESCRICAO_CPF%></option>
					<c:if test="${not giaInventarioArrolamento && not giaSeparacaoDivorcioPartilha}">
						<option value="<%=DomnOpcaoPesquisaContribuinte.CNPJ%>"><%=DomnOpcaoPesquisaContribuinte.DESCRICAO_CNPJ%></option>					
					</c:if>
				</select><c:if test="${campoObrigatorio}"><font color="red">*</font></c:if>
			</td>
		</tr>
      
		<tr id="campoDocumento<%out.print(qtde);%>" style='<c:if test="${not naoOculta}">display:none</c:if>'>
			<td class="SEFAZ-TD-RotuloEntrada" width='<c:out value="${larguraRotuloPadrao}"/>'>Contribuinte:&nbsp;</td>
			<td  class="SEFAZ-TD-CampoEntrada">
				<input type="text" class="SEFAZ-INPUT-Text" name="<%=Form.CAMPO_NUMR_DOCUMENTO+qtde%>" size="25" id="<%=Form.CAMPO_NUMR_DOCUMENTO+qtde%>" onblur="return limparLetrasW3c(this, event);" onKeyPress="apenasNumeroW3c(this,event);" onkeyup="return apenasNumeroW3c(this,event);"/><c:if test="${campoObrigatorio}"><font color="red">*</font></c:if>
		</tr>		
		<tr id="botaoLocalizar<%out.print(qtde);%>" style='<c:if test="${not naoOculta}">display:none</c:if>'>
			<td class="SEFAZ-TD-CampoEntrada" colspan="2">
				<div align="center">
					<input type="button" value="   Localizar   "  class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PESQUISAR_CONTRIBUINTE%><%out.print(qtde);%>" onclick="alterarNomeInput('<%=Form.CAMPO_NUMR_DOCUMENTO%><% out.print(qtde); %>','<%=Form.COMBO_PESQUISA_OPCAO_CONTRIBUINTE%><%out.print(qtde);%>', '<c:out value="${origemPesquisaContribuinte}"/>');" />
				</div>
			</td>
		</tr>				
		<tr id="espacoBranco<%out.print(qtde);%>" style="<c:if test="${not naoOculta}">display:none</c:if>">
				<td>&nbsp;</td>
		</tr>
	</table>