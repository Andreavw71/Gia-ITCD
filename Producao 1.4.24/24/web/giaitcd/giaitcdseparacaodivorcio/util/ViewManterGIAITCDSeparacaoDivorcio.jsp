<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManteGIAITCDSeparacaoDivorcio.jsp
* Criação : Novembro de 2007 / Rogério Sanches de Oliveira
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.Form"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.Flow"%>
<%@page import="java.lang.String"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
	<jsp:include page="/giaitcd/util/ViewControleDeAbasGIAITCDUtil.jsp"/>	
<!--#################################
		#		Variáveis de Controle											#
		#################################-->
<%
	boolean abaDadosGerais = false;
	boolean abaBensTributaveis = false;
	boolean abaConjuge = false;
	boolean abaDemCalculo = false;
	boolean abaAcompanhamento = false;
	
	
	if(request.getAttribute(Form.ABA_ATUAL) != null &&( request.getAttribute(Form.ABA_ATUAL) ==(Form.ABA_DADOS_GERAIS)))
	{
		abaDadosGerais = true;
	}
	else if(request.getAttribute(Form.ABA_ATUAL) != null &&( request.getAttribute(Form.ABA_ATUAL) ==(Form.ABA_BENS_TRIBUTAVEIS)))
	{
		abaBensTributaveis = true;
	}
	else if(request.getAttribute(Form.ABA_ATUAL) != null &&( request.getAttribute(Form.ABA_ATUAL) ==(Form.ABA_CONJUGE)))
	{
		abaConjuge = true;
	}
	else if(request.getAttribute(Form.ABA_ATUAL) != null &&( request.getAttribute(Form.ABA_ATUAL) ==(Form.ABA_DEMONSTRATIVO_CALCULO)))
	{
		abaDemCalculo = true;
	}
	else if(request.getAttribute(Form.ABA_ATUAL) != null &&( request.getAttribute(Form.ABA_ATUAL) ==(Form.ABA_ACOMPANHAMENTO)))
	{
		abaAcompanhamento = true;
	}
%>
<script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
<script language="javascript" type="text/javascript">
	function selecioneFuncionalidade()
	{
		if (<%=JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_SEPARACAO_DIVORCIO)%>)
		{
				carregaAbasConsultarSeparacaoDivorcio();
		}
		else
		{
				carregaAbasAlterarSeparacaoDivorcio();
		}
	}
	function habilitarCampo()
	{
		if (!<%=JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_SEPARACAO_DIVORCIO)%>)
		{
			<%if(abaBensTributaveis)	
			{%>
			tipo = buscarTipoNavegador();
          var IPVA = 4;    
          var REBANHO = 5;
          //var descricaoBem  =  document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>[document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>.selectedIndex].text;
          var verificacaoIdBem =  document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>[document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>.selectedIndex].id;  
          
			if(document.form.<%=Form.CAMPO_SELECT_CLASSIFICACAO_BEM%>.value == <%=DomnTipoBem.OUTROS_BENS%>)
			{
				
            if(verificacaoIdBem == IPVA || verificacaoIdBem == REBANHO)
            {            
               document.getElementById('idValorMercado').style.display = 'none';             
            }
            else
            {
              document.getElementById('idValorMercado').style.display = tipo;
            }
            
			}
			else
			{
				document.getElementById('idValorMercado').style.display = 'none';
			}
				<c:if test="${bemTributavelVo.alterar}">
					document.form.<%=Form.CAMPO_SELECT_CLASSIFICACAO_BEM%>.disabled = true;
					document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>.disabled = true;
					<c:if test="${bemTributavelVo.giaITCDVo.usuarioServidor}">
						document.form.<%=Form.CAMPO_DESCRICAO%>.disabled = false; //true; permite que servidor altere informações dos bens tributaveis.
						document.form.<%=Form.CAMPO_VALOR_MERCADO%>.disabled = false; // true;
					</c:if>									
				</c:if>
	<%}%>
		}
	}
	function carregaAbasAlterarSeparacaoDivorcio()
	{
		Abas.registraAba(solicitarAbaDadosGerais, "Dados Gerais", <%=String.valueOf(abaDadosGerais)%>, 'abaDadosGerais', true);
		Abas.registraAba(solicitarAbaBensTributaveis, "Bens Tributáveis", <%=String.valueOf(abaBensTributaveis)%>, 'abaBensTributaveis', true);
		Abas.registraAba(solicitarAbaConjuge, "Cônjuge", <%=String.valueOf(abaConjuge)%>,'abaConjuge', true);
		Abas.registraAba(solicitarAbaDemonstrativoDeCalculo, "Demonstrativo Cálculo", <%=String.valueOf(abaDemCalculo)%>,'abaDemCalculo', true);
		Abas.registraAba(solicitarAjuda, "Ajuda",  false,'link-Ajuda', false, ['<c:out value="${requestScope.atributoTituloFuncionalidade}" ></c:out>','<abaco:contexto></abaco:contexto>']);
		<%--Abas.registraAba(solicitarAbrirManualUsuario, "Manual",  false, 'pdf', false);--%>
		Abas.render();
	}
		// Funcionalidade IncluirAlterar
	function carregaAbasConsultarSeparacaoDivorcio()
	{
		Abas.registraAba(solicitarAbaDadosGerais, "Dados Gerais", <%=String.valueOf(abaDadosGerais)%>, 'abaDadosGerais', true);
		Abas.registraAba(solicitarAbaBensTributaveis, "Bens Tributáveis", <%=String.valueOf(abaBensTributaveis)%>, 'abaBensTributaveis', true);
		Abas.registraAba(solicitarAbaConjuge, "Cônjuge", <%=String.valueOf(abaConjuge)%>,'abaConjuge', true);
		Abas.registraAba(solicitarAbaDemonstrativoDeCalculo, "Demonstrativo Cálculo", <%=String.valueOf(abaDemCalculo)%>,'abaDemCalculo', true);
		Abas.registraAba(solicitarAbaAcompanhamento, "Acompanhamento", <%=String.valueOf(abaAcompanhamento)%>,'abaAcompanhamento', true);
		Abas.registraAba(solicitarAjuda, "Ajuda",  false,'link-Ajuda', false, ['<c:out value="${requestScope.atributoTituloFuncionalidade}" ></c:out>','<abaco:contexto></abaco:contexto>']);
		Abas.render();
	}
        
</script>
<%	// controle da funcionalidade incluir / Alterar
		 if (JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_SEPARACAO_DIVORCIO)) 
		 {
			if(abaDadosGerais)
			{ 
				pageContext.include(Flow.VIEW_PESQUISAR_GIAITCD_SEPARACAO_DIVORCIO_ABA_DADOS_GERAIS);
			}
		  else if(abaBensTributaveis)
		  { 
			  pageContext.include(Flow.VIEW_PESQUISAR_GIAITCD_ABA_BENS_TRIBUTAVEIS);
			}
		else if(abaConjuge)
			{
				pageContext.include(Flow.VIEW_PESQUISAR_GIAITCD_SEPARACAO_DIVORCIO_ABA_CONJUGE);
			}  
		else if(abaDemCalculo)
			{
			pageContext.include(Flow.VIEW_MANTER_GIAITCD_SEPARACAO_DIVORCIO_ABA_DEMONSTRATIVO_DE_CALCULO);
			}  
		else if(abaAcompanhamento)
			{
			pageContext.include(Flow.VIEW_PESQUISAR_GIAITCD_SEPARACAO_DIVORCIO_ABA_ACOMPANHAMENTO);
			}
		 }
		 else // controle da funcionalidade pesquisar
		 {
			if(abaDadosGerais)
			{ 
				pageContext.include(Flow.VIEW_MANTER_GIAITCD_SEPARACAO_DIVORCIO_ABA_DADOS_GERAIS);
			}
  	     else  if(abaBensTributaveis)
		  { 
			  pageContext.include(Flow.VIEW_MANTER_GIAITCD_ABA_BENS_TRIBUTAVEIS);
			}
			else if(abaConjuge)
			{
				pageContext.include(Flow.VIEW_MANTER_GIAITCD_SEPARACAO_DIVORCIO_ABA_CONJUGE);
			}  
			else if(abaDemCalculo)
			{
			pageContext.include(Flow.VIEW_MANTER_GIAITCD_SEPARACAO_DIVORCIO_ABA_DEMONSTRATIVO_DE_CALCULO);
			}  
		 }
%>
