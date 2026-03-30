<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewAlterarAjuda.jsp
* Criação : Fevereiro de 2008 / Wendell Pereira de Farias 
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<%
    pageContext.setAttribute("ATIVO", new Integer(DomnStatusRegistro.ATIVO));
    pageContext.setAttribute("INATIVO", new Integer(DomnStatusRegistro.INATIVO));
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
    <META HTTP-EQUIV=Cache-Control content=no-store>
    <META HTTP-EQUIV=Pragma content=no-cache>
    <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
    <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
    <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
    <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
    <script type="text/javascript" language="javascript">   
        var valorEnviado="";
        
        /*
        Função responsável por atualizar(limpar e escrever os registros dos combobox dinamicamente).
        Autor: Maxwell Rocha 
        */
        function atualizaLista(objlista1)
        {                   
             //fazer submit para a servlet e retornar a esta página com os registro do combo a ser atualizado.
            if(objlista1.name=="<%=Form.CAMPO_SELECT_MODULO_AJUDA%>")
            {
                if(objlista1.selectedIndex != 0)
                {
                    solicitarListarSubModulo();
                     for(var i=2; i<arguments.length; i++)
                    {	
                        limparCombo(arguments[i]);
                    }
                }
            }
            
            if(objlista1.name=="<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>")
            {
                if(objlista1.selectedIndex != 0)
                {
                    solicitarListarFuncionalidade();
                     for(var i=3; i<arguments.length; i++)
                    {	
                        limparCombo(arguments[i]);
                    }
                }
            }
            
            if(objlista1.name=="<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>")
            {
                if(objlista1.selectedIndex != 0)
                {
                    solicitarListarTelaAjuda();
                     for(var i=4; i<arguments.length; i++)
                    {	
                        limparCombo(arguments[i]);
                    }
                }
            }
            
            if(objlista1.name=="<%=Form.CAMPO_SELECT_TELA_AJUDA%>")
            {
                if(objlista1.selectedIndex != 0)
                {
                    solicitarListarTelaCampoAjuda();
                }
            }
            
            if(objlista1.name=="<%=Form.CAMPO_SELECT_ROTULO_AJUDA%>")
            {
                if(objlista1.selectedIndex != 0)
                {
                    solicitarListarDescricaoAjudaCampo();
                    for(var i=4; i<arguments.length; i++)
                    {	
                        limparCombo(arguments[i]);
                    }
                }
            }  
        }
            
        /*
        Função responsável por limpar um combobox passado por parâmetro através da função atualizaLista(objlista1).
        Autor: Maxwell Rocha
        */	
        function limparCombo(campoAtualizacao)
        {
            var listaDestino = campoAtualizacao.length;
            for(var i=1; i < listaDestino; i++){
                    campoAtualizacao.options[1] = null;
            }
        }
        
        /*
        Função responsável por limpar os campos texto "titulo da tela" e "descrição ajuda"
        Autor: Wendell Farias
        */
        function limpaDescricao()
        {
            document.form.<%=Form.CAMPO_DESCRICAO_AJUDA%>.value = "";
        }
        
        function validaFormulario()
        {	
				if ( document.getElementById("itensInseridos") && !document.getElementById("teste") )
				{
					alert("Não foi informado ajuda para os campos da tela");
				}
				
            if( ! verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_MODULO_AJUDA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_MODULO_AJUDA+"'"%>))
            {
                return false;
            }
            if( ! verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_SUBMODULO_AJUDA+"'"%>))
            {
                return false;
            }
            if( ! verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_FUNCIONALIDADE_AJUDA+"'"%>))
            {
                return false;
            }
            if( ! verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_TELA_AJUDA+"'"%>))
            {
                return false;
            }
 
            codigoTelaAjuda = document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>.value;
            desabilitarBotoes(obterArrayBotoes());                       
            document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR_CLONE+ "=1"+"?"+Form.CAMPO_CHECK_HABILITAR_AJUDA+ "="%>' +document.form.<%=Form.CAMPO_CHECK_HABILITAR_AJUDA%>.value;
            document.form.submit();				
        }
        
        function obterArrayBotoes()
        {
            var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR%>;
            var botao2 = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
            return new Array(botao1, botao2);
        }
        
        /*
        Função responsável por solicitar a lista Sub Modulos
        Autor: Wendell Farias
        */
        function solicitarListarSubModulo()
        {			
            desabilitarBotoes(obterArrayBotoes()); 
            limpaDescricao();
            document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_MODULO+ "="%>'+document.form.<%=Form.CAMPO_SELECT_MODULO_AJUDA%>.value;
            document.form.submit();
        }
        
        /*
        Função responsável por solicitar a lista de Funcionalidades
        Autor: Wendell Farias
        */
        function solicitarListarFuncionalidade()
        {
            desabilitarBotoes(obterArrayBotoes());
            limpaDescricao();
           if(document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>.value=="")
            {
                document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_MODULO+ "="%>'+document.form.<%=Form.CAMPO_SELECT_MODULO_AJUDA%>.value;                
            } 
            else
            {
                document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_SUB_MODULO+ "="%>'+document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>.value;                
            }
            document.form.submit();
        }
        
        /*
        Função responsável por solicitar a lista de TelaAjuda
        Autor: Wendell Farias
        */
        function solicitarListarTelaAjuda()
        {
            desabilitarBotoes(obterArrayBotoes());
            limpaDescricao();
            document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_FUNCIONALIDADE+ "="%>'+document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>.value;
            document.form.submit();
        }
        
        /*
        Função responsável por solicitar a lista TelaCampoAjuda
        Autor: Wendell Farias
        */
        function solicitarListarTelaCampoAjuda()
        {
            desabilitarBotoes(obterArrayBotoes());
            limpaDescricao();
            document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_TELA+ "="%>'+document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>.value;
            document.form.submit();
        }

        /*
        Função responsável por solicitar o "titulo da tela" e "descrição ajuda"
        Autor: Wendell Farias
        */
        function solicitarListarDescricaoAjudaCampo()
        {
            document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_PESQUISAR_DESCRICAO_AJUDA_CAMPO+ "="%>'+document.form.<%=Form.CAMPO_SELECT_ROTULO_AJUDA%>.value;
            document.form.submit();
        }            

        /*
        Função responsável por incluir os campos da tela
        Autor: Wendell Farias
        */
        function incluir(valor)
        {
           			
            if( ! verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_ROTULO_AJUDA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_ROTULO_AJUDA+"'"%>))
            {
                return false;
            }
           if( ! verificaCamposW3c(document.form.<%=Form.CAMPO_DESCRICAO_AJUDA_ROTULO_AJUDA%>,<%="'"+MensagemErro.VALIDAR_PARAMETRO_AJUDA_PARA_ROTULO_AJUDA+"'"%>))
           {
                return false;
            }
            desabilitarBotoes(obterArrayBotoes());
            document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+ "="%>'+valor;            
            document.form.submit();           
        }
        
        function redimensiona()
        {
           document.form.<%=Form.CAMPO_TITULO_TELA_AJUDA%>.size=document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>.size;
           alert("valor tela:"+ document.form.<%=Form.CAMPO_TITULO_TELA_AJUDA%>.size+ "  valor fun:"+document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>.size);
        }
        /*
        Função responsável por alterar os campos da tela
        Autor: Wendell Farias
        */        
        function alterar(valor)
        {
            document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_AJUDA_ALTERAR+ "="%>'+valor;
            document.form.submit();
        }     

        /*
        Função responsável por excluir os campos da tela
        Autor: Wendell Farias
        */        
        function excluir(valor)
        {
            desabilitarBotoes(obterArrayBotoes());
            if(confirm(<%="'"+MensagemErro.EXCLUIR_CAMPO_AJUDA+"'"%>))
            {
                document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_CODIGO_AJUDA_EXCLUIR+ "="%>'+valor;
                document.form.submit();
            }
            else
            {
                return false;
            }                    
        }                           
    </script>
    <jsp:include page="/util/ViewVerificaErro.jsp"/>
    <title><abaco:tituloSistema></abaco:tituloSistema></title>
   </head>
  <body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>'); log();">
    <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"></g:mostrarCabecalho>
        <center>
            <form method="POST" action="" name="form">
              <table id="camposTela" cellspacing="1" cellpadding="0" border="0" width="740" align="center" style="display:block">              
                     <tr align="right">
                            <td colspan="5"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                     </tr>
                    <tr class="SEFAZ-TR-Titulo" align="center">
                        <td colspan="5">Dados do Registro</td>
                    </tr>
                    <tr>                        
                        <td colspan="2" class="SEFAZ-TD-RotuloEntrada">Módulo:&nbsp;</td>          
                        <td colspan="3" class="SEFAZ-TD-CampoEntrada" align="left">
                            <select  id="<%=Form.CAMPO_SELECT_MODULO_AJUDA%>" name="<%=Form.CAMPO_SELECT_MODULO_AJUDA%>" onchange="atualizaLista(this,document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_ROTULO_AJUDA%>);">
                                <option value="" selected ><%=Form.SELECIONE%></option>
                                <c:set var="modVo" value="${telaFuncionalidadeVo.funcionalidadeVo.moduloAjudaVo}"  scope="request"></c:set>
                                <c:forEach var="moduloAjudaTempVo" items="${telaFuncionalidadeVo.funcionalidadeVo.moduloAjudaVo.collVO}">                                                 
                                    <option value="<c:out value="${moduloAjudaTempVo.codigo}"></c:out>" <c:if test="${moduloAjudaTempVo.codigo == modVo.codigo}">selected</c:if>> <c:out value="${moduloAjudaTempVo.descricaoModuloAjuda}"></c:out></option>
                                </c:forEach>
                            </select><font color="red">*</font>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="SEFAZ-TD-RotuloEntrada">Submodulo:&nbsp;</td>
                        <td colspan="3" class="SEFAZ-TD-CampoEntrada"  align="left">
                            <select id=="<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>" name="<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>" onchange="atualizaLista(this,document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_ROTULO_AJUDA%>);">
                                <option value="" selected><%=Form.SELECIONE%></option>
                                <c:set var="subModVo" value="${telaFuncionalidadeVo.funcionalidadeVo.moduloAjudaVo.subModuloAjuda}"  scope="request"></c:set>
                                <c:forEach var="subModuloAjudaTempVo" items="${telaFuncionalidadeVo.funcionalidadeVo.moduloAjudaVo.subModuloAjuda.collVO}">
                                    <option value="<c:out value="${subModuloAjudaTempVo.codigo}"></c:out>" <c:if test="${subModuloAjudaTempVo.codigo == subModVo.codigo}">selected</c:if>><c:out value="${subModuloAjudaTempVo.descricaoModuloAjuda}"></c:out></option>
                                </c:forEach>
                            </select><font color="red">*</font>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="SEFAZ-TD-RotuloEntrada">Funcionalidade:&nbsp;</td>
                        <td colspan="3" class="SEFAZ-TD-CampoEntrada"  align="left">
                            <select  id="<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>" name="<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>" onchange="atualizaLista(this,document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_ROTULO_AJUDA%>);">
                                <option value="" selected><%=Form.SELECIONE%></option>
                                <c:set var="funcionalidade" value="${telaFuncionalidadeVo.funcionalidadeVo}"  scope="request"></c:set>
                                <c:forEach var="funcionalidadeTempVo" items="${funcionalidade.collVO}">                                                 
                                    <option value="<c:out value="${funcionalidadeTempVo.codigo}"></c:out>" <c:if test="${funcionalidadeTempVo.codigo == funcionalidade.codigo}">selected</c:if>><c:out value="${funcionalidadeTempVo.descricaoFuncionalidade}"></c:out></option>
                                </c:forEach>
                            </select><font color="red">*</font>	  
                        </td>
                    </tr>
                        <tr>
                            <td colspan="2" class="SEFAZ-TD-RotuloEntrada">Tela:&nbsp;</td>
                            <td colspan="3" class="SEFAZ-TD-CampoEntrada" align="left">
                                <select name="<%=Form.CAMPO_SELECT_TELA_AJUDA%>" onchange="atualizaLista(this,document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_ROTULO_AJUDA%>);">
                                    <option value="" selected><%=Form.SELECIONE%></option>
                                    <c:set var="telaFuncionalidade" value="${telaFuncionalidadeVo}"  scope="request"></c:set>
                                    <c:forEach var="telaFuncionalidadeTemp" items="${telaFuncionalidade.collVO}">                                                 
                                        <option value="<c:out value="${telaFuncionalidadeTemp.telaAjudaVo.codigo}"></c:out>" <c:if test="${telaFuncionalidadeTemp.telaAjudaVo.codigo == telaFuncionalidade.telaAjudaVo.codigo}">selected</c:if>><c:out value="${telaFuncionalidadeTemp.informacaoTituloTelaFuncionalidade}"></c:out></option>
                                    </c:forEach>
                                </select><font color="red">*</font> 
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="SEFAZ-TD-RotuloSaida">Título Tela:&nbsp;</td>
                            <td colspan="3" class="SEFAZ-TD-CampoSaida">
                                <c:out value="${telaFuncionalidadeVo.informacaoTituloTelaFuncionalidade}"></c:out>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="SEFAZ-TD-RotuloEntrada">Descrição Ajuda Tela:&nbsp;</td>
                            <td colspan="3" class="SEFAZ-TD-CampoEntrada">
										<textarea name="<%=Form.CAMPO_DESCRICAO_AJUDA%>" cols="50" rows="5"><c:out value="${telaFuncionalidadeVo.descricaoTelaFuncionalidade}"></c:out></textarea><font color="red">*</font></td>
                        </tr>
                        <tr>
                            <td colspan="2" class="SEFAZ-TD-RotuloEntrada">Habilitar:&nbsp;</td>
                            <td colspan="3" class="SEFAZ-TD-CampoEntrada">
                                <input type="checkbox" name="<%=Form.CAMPO_CHECK_HABILITAR_AJUDA%>" <c:if test="${telaFuncionalidadeVo.statusTelaFuncionalidade.valorCorrente!=INATIVO}">checked</c:if>></input>
                            </td>
                        </tr>					
                        <tr>
                            <td colspan="5"> </td>
                        </tr>
                            <tr class="SEFAZ-TR-Titulo" align="center">
											<td colspan="5">Campos</td>
                            </tr>
                            <tr>
                               <td colspan="2" class="SEFAZ-TD-RotuloEntrada">Selecionar R&oacute;tulo:&nbsp;</td>
                              <td colspan="3" class="SEFAZ-TD-CampoEntrada">
                                <c:set var="telaCampVo" value="${telaFuncionalidadeVo.telaAjudaVo.telaCampoAjudaVo}" scope="request"></c:set>
                                    <select id="<%=Form.CAMPO_SELECT_ROTULO_AJUDA%>" name="<%=Form.CAMPO_SELECT_ROTULO_AJUDA%>" onchange="atualizaLista(this,document.form.<%=Form.CAMPO_SELECT_SUBMODULO_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_FUNCIONALIDADE_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_TELA_AJUDA%>,document.form.<%=Form.CAMPO_SELECT_ROTULO_AJUDA%>);">
                                        <option value="" selected="selected"><%=Form.SELECIONE_AJUDA%></option>
                                          <c:if test="${telaFuncionalidadeVo.telaAjudaVo.telaCampoAjudaVo.existe}">
                                            <c:forEach var="telaCampoAjudaVoTemp"  items="${telaCampVo.collVO}">
                                              <c:if test="${telaCampoAjudaVoTemp.statusTelaCampo.valorCorrente==INATIVO}">
                                                <option value='<c:out value="${telaCampoAjudaVoTemp.codigo}"></c:out>'
                                                        <c:if test="${telaCampoAjudaVoTemp.codigo == telaCampVo.codigo}">selected</c:if>>
                                                  <c:out value="${telaCampoAjudaVoTemp.campoAjudaVo.descricaoRotulo}"></c:out>
                                            </option>
                                          </c:if>
                                        </c:forEach>
                                      </c:if>
                                    </select><font color="red">*</font>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="SEFAZ-TD-RotuloEntrada">Descrição Ajuda Rótulo:&nbsp;</td>
                                <td colspan="3" class="SEFAZ-TD-CampoEntrada">
                                   <textarea name="<%=Form.CAMPO_DESCRICAO_AJUDA_ROTULO_AJUDA%>" cols="50" rows="5"><c:out value="${telaCampVo.descricaoAjudaCampo}"></c:out></textarea><font color="red">*</font>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="SEFAZ-TD-RotuloEntrada">&nbsp;</td>
                                <td colspan="3" class="SEFAZ-TD-CampoEntrada">							
                                    <input name="<%=Form.BOTAO_CONFIRMAR%>" type="button"   value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" class="SEFAZ-INPUT-Botao" id="btnIncluir" onClick="incluir(document.form.<%=Form.CAMPO_SELECT_ROTULO_AJUDA%>.value)"></input>
                                </td>       
                          </tr>
                          <tr><td colspan="5" class="SEFAZ-TD-RotuloEntrada">&nbsp;</td></tr>
                          <tr>    
								  </tr>
                        </tr>
                    </table>                                     
                <c:if test="${telaFuncionalidadeVo.telaAjudaVo.telaCampoAjudaVo.existe}">
                    <table id="itensInseridos" cellspacing="1" cellpadding="0" border="0" width="740" align="center" style="display:block">
                        <tr class="SEFAZ-TR-Titulo" align="center">
                            <td colspan="4">Campos da Tela/Funcionalidade</td>
                        </tr>
                        <tr class="SEFAZ-TR-Titulo">
                            <td width="15%">Código</td>
                            <td align="left" width="20%">Rótulo</td>
                            <td align="left" width="45%">Descrição do Rótulo</td>
                            <td width="20%"></td>
                        </tr>
                        <c:forEach var="telaCampoAjudaVotemp" items="${telaFuncionalidadeVo.telaAjudaVo.telaCampoAjudaVo.collVO}" varStatus="indice">                                                 
                            <c:if test="${telaCampoAjudaVotemp.statusTelaCampo.valorCorrente==ATIVO}">
                                <c:if test="${indice.count % 2 != 0}">
                                    <tr id="teste" class="SEFAZ-TR-ExibicaoImpar">
                                </c:if>
                                <c:if test="${indice.count % 2 == 0}">
                                    <tr id="teste" class="SEFAZ-TR-ExibicaoPar">
                                </c:if>
                                <td width="15%"><c:out value="${telaCampoAjudaVotemp.codigo}"></c:out></td>
                                <td align="left" width="20%"><c:out value="${telaCampoAjudaVotemp.campoAjudaVo.descricaoRotulo}"></c:out></td>
                                <td align="left" width="45%" ><c:out value="${telaCampoAjudaVotemp.descricaoAjudaCampo}"></c:out></td>
                                <!--td align="center" width="10%">                                            
                                        <a href="javascript: alterar(<c:out value="${telaCampoAjudaVotemp.codigo}"></c:out>)">alterar</a>
                                </td-->
                                <td align="center" width="20%">
                                    <a href="javascript: excluir(<c:out value="${telaCampoAjudaVotemp.codigo}"></c:out>)">excluir</a>
                                </td>                                                
                            </tr>
                        </c:if>
                        </c:forEach>
                    <c:forEach var="telaCampoAjudaVotemp" items="${telaFuncionalidadeVo.telaAjudaVo.telaCampoAjudaVo.collVO}" varStatus="indice">                      
                        <c:if test="${telaCampoAjudaVotemp.statusTelaCampo.valorCorrente<ATIVO}">
                            <c:if test="${indice.count % 2 != 0}">
                                <tr id="teste" class="SEFAZ-TR-ExibicaoImpar">
                            </c:if>
                            <c:if test="${indice.count % 2 == 0}">
                                <tr id="teste" class="SEFAZ-TR-ExibicaoPar">
                            </c:if>
                                <td width="15%"><c:out value="${telaCampoAjudaVotemp.codigo}"></c:out></td>
                                <td align="left" width="20%"><c:out value="${telaCampoAjudaVotemp.campoAjudaVo.descricaoRotulo}"></c:out></td>
                                <td align="left" width="45%" ><c:out value="${telaCampoAjudaVotemp.descricaoAjudaCampo}"></c:out></td>
                                <!--td align="center" width="10%">                                            
                                    <!a href="javascript: alterar(<c:out value="${telaCampoAjudaVotemp.codigo}"></c:out>)"></a>
                                </td-->
                                <td align="center" width="20%">
                                    <a href="javascript: excluir(<c:out value="${telaCampoAjudaVotemp.codigo}"></c:out>)"></a>
                                </td>                                                
                            </tr>
                        </c:if>                      
                    </c:forEach>
                        <tr>
                            <td colspan="4">&nbsp;</td>
                        </tr> 
                  </table>
        </c:if>
             <table width="740" border="0">
                <tr>
                    <td colspan="3" align="center">						
                        <input name="<%=Form.BOTAO_CONFIRMAR_CLONE%>" type="button"   value="<%=Form.TEXTO_BOTAO_SALVAR%>" class="SEFAZ-INPUT-Botao" id="btnSalvar" onClick="validaFormulario()"></input>
                        <abaco:botaoCancelar></abaco:botaoCancelar>
                    </td>
                </tr>
					 <tr>
						<td><font color="red"><b>* Campos Obrigatórios</b></font></td>
					 </tr>
            </table>                                                                                                                          
				<abaco:mensagemAguardeCarregando></abaco:mensagemAguardeCarregando>
				 <abaco:log/>
        </form>
    </center>
    <g:mostrarRodape></g:mostrarRodape>
  </body>
</html>
