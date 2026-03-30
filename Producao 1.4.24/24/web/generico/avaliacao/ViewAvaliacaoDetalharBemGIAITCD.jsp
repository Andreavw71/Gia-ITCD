<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewAvaliacaoDetalharBemGIAITCD.jsp
* Criação : Janeiro de 2008 / Elizabeth Barbosa Moreira
* Revisão : 
* Log : 
*/
--%>
<%@ page contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.avaliacao.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import="br.gov.mt.sefaz.itc.util.dominio.DomnTipoAvaliacao"%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	
	<link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
	<script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
		<script type="text/javascript" language="javascript">		
		
		function validaFormulario()
		{
		/*
        if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_UNIDADE_SEFAZ%>, <%="'"+MensagemErro.GERENCIA_EXECUCAO_SERVICO_DEVE_SER_INFORMADO+"'"%>))
        {
            return false;
        }
		*/
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=1"%>';
			document.form.submit();
		}
		
		function obterArrayBotoes()
		{
			 var botao = document.form.<%=Form.BOTAO_CONFIRMAR%>;
			 return new Array(botao);
		}



		// específico para a listagem de bens
		function incluirAvaliacaoBem(indice)
		{	
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_INCLUIR_AVALIACAO_BEM+"="%>'+indice;
			document.form.submit();
		}
			function alterarAvaliacaoBem(indice)
		{	
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_ALTERAR_AVALIACAO_BEM+"="%>'+indice;
			document.form.submit();
		}
		function detalharAvaliacaoBem(indice)
		{	
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_DETALHAR_AVALIACAO_BEM+"="%>'+indice;
			document.form.submit();
		}		
		
      // específico para excluir avaliacao dos bens.
		function excluirAvaliacaoBem()
		{
      if(confirm('Deseja realmente excluir avaliação?'))
				{
               document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_EXCLUIR_AVALIACAO+"=15"%>';
               document.form.submit();
               return true;
				}
            else
				{
					return false
				}
		}
      
       // específico para Reabrir Avaliacacao..
		function reabrirAvaliacaoBem()
		{
          if(confirm('Deseja realmente reabrir avaliação?'))
				{
               document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_REABRIR_AVALIACAO+"=16"%>';
               document.form.submit();
                return true;
             }
            else
				{
					return false
				}
		}
      
      
	</script>
	<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
	</head>
	<jsp:include page="/util/ViewVerificaErro.jsp"/>
	<body class="SEFAZ-Body" onload="verificaErro();">
		<form method="POST" name="form"  action="#" >	
	<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
	<center>
		<table>
			<tr> 
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr> 
				<td colspan="2">
					<jsp:include page= "/generico/avaliacao/ViewAvaliacaoDetalharGIAITCD.jsp"/>
				</td>
			</tr>
		</table>	
		<table>
			<tr> 
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr> 
				<td colspan="2">
					<jsp:include page="/generico/avaliacao/ViewAvalicaoListarBem.jsp"/>
				</td>
			</tr>
		</table>
	</center>
	<g:mostrarRodape/>
	</form>
	</body>
</html>