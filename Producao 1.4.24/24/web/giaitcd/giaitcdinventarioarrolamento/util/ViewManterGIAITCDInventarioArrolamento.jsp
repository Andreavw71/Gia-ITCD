<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManteGIAITCDInventarioArrolamento.jsp
* Criação : Novembro de 2007 / Rogério Sanches de Oliveira / Anderson Boehler Iglesias Araujo / Fev2008 Wendell Pereira de Farias (Ajuda)
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>

<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.Form"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.Flow"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="java.lang.String"%>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
	<jsp:include page="/giaitcd/util/ViewControleDeAbasGIAITCDUtil.jsp"/>
<!--#################################
		#		Variáveis de Controle											#
		#################################-->
<%
	boolean abaDadosGerais = false;
	boolean abaBensTributaveis = false;
	boolean abaBeneficiarios = false;
	boolean abaDemCalculo = false;
	boolean abaAcompanhamento = false;
	
	/*
	* Verifica se a aba atual é diferente de null, o atributo Form.ABA_ATUAL é enviado para o request na servlet responsável pela solicitação
	" executa-se o método 	processFlow(" ONDE TODA VIEW QUE ENVIADA, SERÁ SEMPRE A MESMA "ViewEstruturaGIAITCDInventarioArrolamento" ,"
	* request, response, FORWARD);
	*	request.setAttribute(Form.ABA_ATUAL , Form.ABA_DADOS_GERAIS)selecionada, se for, habilita a imagem azul
	*/
	
	if(request.getAttribute(Form.ABA_ATUAL) != null &&( request.getAttribute(Form.ABA_ATUAL) ==(Form.ABA_DADOS_GERAIS)))
	{
		abaDadosGerais = true;
	}
	else if(request.getAttribute(Form.ABA_ATUAL) != null &&( request.getAttribute(Form.ABA_ATUAL) ==(Form.ABA_BENS_TRIBUTAVEIS)))
	{
		abaBensTributaveis = true;
	}
	else if(request.getAttribute(Form.ABA_ATUAL) != null &&( request.getAttribute(Form.ABA_ATUAL) ==(Form.ABA_BENEFICIARIOS)))
	{
		abaBeneficiarios = true;
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
		if (<%=JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_INVENTARIO_ARROLAMENTO)%>)
		{
				carregaAbasConsultarInventarioArrolamento();
		}
		else
		{
				carregaAbasAlterarIncluirInventarioArrolamento();
		}
	}
	
	function selecionaRegimeCasamento(opcaoSelecionada)
	{
		document.form.<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>.value = opcaoSelecionada;
	}
	
	function habilitarCampo()
	{
		<%if (!JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_INVENTARIO_ARROLAMENTO))
		{
			if(abaBensTributaveis)	
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
               <c:if test="${!bemTributavelVo.habilitarClassificacaoBem}">
                  document.form.<%=Form.CAMPO_SELECT_CLASSIFICACAO_BEM%>.disabled = true;
               </c:if>               
               <c:if test="${!bemTributavelVo.habilitarTipoBem}">
                  document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>.disabled = true;
               </c:if>   
					<c:if test="${bemTributavelVo.giaITCDVo.usuarioServidor}">
                  // Codigo comentado para permitir que os campos fiquem habilitados para o servidor poder fazer alteracoes.
						//document.form.<%=Form.CAMPO_DESCRICAO%>.disabled = true;
						//document.form.<%=Form.CAMPO_VALOR_MERCADO%>.disabled = true;
					</c:if>				
				</c:if>
            mensagemBemIsento();
	<%}
			if(abaDadosGerais )
			{%>
				habilitarCampoEstadoCivil();
				habilitaSelectHerdeirosAscendentes();
				habilitaCampoOutrosHerdeiros();
				habilitaCampoRenuncia();
	<%}
		}%>		
	}
	
	function carregaAbasAlterarIncluirInventarioArrolamento()
	{
   Abas.registraAba(solicitarAbaDadosGerais, "Dados Gerais", <%=String.valueOf(abaDadosGerais)%>, 'abaDadosGerais', true);
	Abas.registraAba(solicitarAbaBensTributaveis, "Bens Tributáveis", <%=String.valueOf(abaBensTributaveis)%>, 'abaBensTributaveis', true);
	Abas.registraAba(solicitarAbaBeneficiarios, "Beneficiários", <%=String.valueOf(abaBeneficiarios)%>,'abaBeneficiarios', true);
	Abas.registraAba(solicitarAbaDemonstrativoDeCalculo, "Demonstrativo Cálculo", <%=String.valueOf(abaDemCalculo)%>,'abaDemCalculo', true);
	Abas.registraAba(solicitarAjuda, "Ajuda",  false,'link-Ajuda', false, ['<c:out value="${requestScope.atributoTituloFuncionalidade}" ></c:out>','<abaco:contexto></abaco:contexto>']);
	<%--Abas.registraAba(solicitarAbrirManualUsuario, "Manual",  false, 'pdf', false);--%>
	Abas.render();
	}
        
	function carregaAbasConsultarInventarioArrolamento()
	{
   Abas.registraAba(solicitarAbaDadosGerais, "Dados Gerais", <%=String.valueOf(abaDadosGerais)%>, 'abaDadosGerais', true);
	Abas.registraAba(solicitarAbaBensTributaveis, "Bens Tributáveis", <%=String.valueOf(abaBensTributaveis)%>, 'abaBensTributaveis', true);
	Abas.registraAba(solicitarAbaBeneficiarios, "Beneficiários", <%=String.valueOf(abaBeneficiarios)%>,'abaBeneficiarios', true);
	Abas.registraAba(solicitarAbaDemonstrativoDeCalculo, "Demonstrativo Cálculo", <%=String.valueOf(abaDemCalculo)%>,'abaDemCalculo', true);
	Abas.registraAba(solicitarAbaAcompanhamento, "Acompanhamento", <%=String.valueOf(abaAcompanhamento)%>,'abaAcompanhamento', true);
	Abas.registraAba(solicitarAjuda, "Ajuda",  false,'link-Ajuda', false, ['<c:out value="${requestScope.atributoTituloFuncionalidade}" ></c:out>','<abaco:contexto></abaco:contexto>']);
	Abas.render();
	}
	
</script>
<%
	
	// controle da funcionalidade consultar
	if (JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_PESQUISAR_GIAITCD_INVENTARIO_ARROLAMENTO))
	{
		if(abaDadosGerais)
		{ 
			pageContext.include(Flow.VIEW_PESQUISAR_GIAITCD_INVENTARIO_ARROLAMENTO_ABA_DADOS_GERAIS);
		}
		else if(abaBensTributaveis)
		{ 
			pageContext.include(Flow.VIEW_PESQUISAR_GIAITCD_ABA_BENS_TRIBUTAVEIS);
		}
		else if(abaBeneficiarios)
		{
			pageContext.include(Flow.VIEW_PESQUISAR_GIAITCD_INVENTARIO_ARROLAMENTO_ABA_BENEFICIARIOS);
		}  
		else if(abaDemCalculo)
		{
			pageContext.include(Flow.VIEW_MANTER_GIAITCD_INVENTARIO_ARROLAMENTO_ABA_DEMONSTRATIVO_DE_CALCULO);
		}  
		else if(abaAcompanhamento)
		{
			pageContext.include(Flow.VIEW_PESQUISAR_GIAITCD_INVENTARIO_ARROLAMENTO_ABA_ACOMPANHAMENTO);
		}	
	}
	// controle da funcionalidade incluir e alterar
	else
	{
		if(abaDadosGerais)
		{ 
			pageContext.include(Flow.VIEW_MANTER_GIAITCD_INVENTARIO_ARROLAMENTO_ABA_DADOS_GERAIS);
		}
		else if(abaBensTributaveis)
		{ 
			pageContext.include(Flow.VIEW_MANTER_GIAITCD_ABA_BENS_TRIBUTAVEIS);
		}
		else if(abaBeneficiarios)
		{
			pageContext.include(Flow.VIEW_MANTER_GIAITCD_INVENTARIO_ARROLAMENTO_ABA_BENEFICIARIOS);
		}  
		else if(abaDemCalculo)
		{
		pageContext.include(Flow.VIEW_MANTER_GIAITCD_INVENTARIO_ARROLAMENTO_ABA_DEMONSTRATIVO_DE_CALCULO);
		} 
	}
%>
