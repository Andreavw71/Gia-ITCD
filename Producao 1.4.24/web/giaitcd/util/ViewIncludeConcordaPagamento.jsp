<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center">
	<tr>   
   </br>
	  <td class="SEFAZ-FONT-MensagemErro" width="60%" align="right">Senhor contribuinte, concorda com o pagamento do Imposto sobre o Valor Arbitrado?&nbsp;&nbsp;</td>	  
	  <td width="40%" align="left">
		<abaco:campoSelectDominioNumericoAbaco
            estilo="SEFAZ-TD-ComboBox"
				ajuda="" 
				classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"
				name="<%=Form.CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE%>" 
				tabIndex=""  
				idCampo="<%=Form.CAMPO_CONCORDA_COM_VALOR_ARBITRADO_SELECIONADO_CONTRIBUINTE%>" 
				opcaoSelecionada="${flagConcordaValorArbitrado.valorCorrente}">		            
			</abaco:campoSelectDominioNumericoAbaco><font color="red">*</font>
	  </td>
	</tr>
</table>