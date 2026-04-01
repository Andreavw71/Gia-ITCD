<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso ? Sefaz-MT
* Arquivo : ViewMostrarImagemCaracter.jsp
* Criação : 
* Revisão : 
* Log : 
*/
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" %>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>

<table width="740" border="0" cellpadding="0" cellspacing="0">	
	<tr align="center">
		<td width="50%" class="SEFAZ-TD-RotuloEntrada">Código da imagem:&nbsp;
			<abaco:campoString maxlength="10" size="10" value="" 
				name="<%=FormITC.CAMPO_IMAGEM%>"
				ajuda="Digite o código da imagem"
				tabIndex="3"/>
			<font color="red">*</font>
		</td>
		<td align="left" width="50%" >
			<table>
				<tr>
					<td>
						<img  align="left" src="<%=request.getContextPath()%>/imagem/geradorcaracteres" id="kaptchaImage"/>
						<script type="text/javascript" >
							function recarregarImagem()
							{ 
								document.getElementById("kaptchaImage").src = '<%=request.getContextPath()%>/imagem/geradorcaracteres?' + Math.floor(Math.random()*100);
							}
						</script>		
					</td>
				</tr>
				<tr >
					<td>
						<a href="javascript:recarregarImagem();" style="font: 7pt Verdana, Arial, Helvetica, sans-serif;">Clique aqui se não visualizar a imagem.</a>
					</td>
				</tr>
			 </table>
		</td>
	</tr>
</table>
<br>