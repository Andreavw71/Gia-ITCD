<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewIncluirConfiguracaoGerencialParametros.jsp
* Criação : Outubro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : Marlo Eichenberg Motta / Mar 2008 Wendell Pereira de Farias
* Alterações : Fevereiro de 2008 / Março de 2008 / Wendell Pereira de Farias
* Log : 
*/
--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoConfiguracaoGerencialParametros"%>
<%@page import="br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.Form"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page errorPage="/mensagem/ViewErro.jsp"%>
<%
    pageContext.setAttribute("SIM", new Integer(DomnSimNao.SIM));
    pageContext.setAttribute("NAO", new Integer(DomnSimNao.NAO));
    
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
				<%
					DomnTipoConfiguracaoGerencialParametros domnTipoConfiguracao = new DomnTipoConfiguracaoGerencialParametros();
				%>
            ////////////////////////////////////////////////
            // Wendell Pereira de Farias
            //  Variaveis auxiliares.
            //  Descrição Funcionalidade: variaveis utilizadas
            //  para controlar os componentes dinamicos.
            //
            var campoValor;
            var estado=0;
            var rotuloValor ;
            var botao = "<%=Form.BOTAO_ADICIONAR%>"+ getCampoValor();
            ////////////////////////////////////////////////
            
            ////////////////////////////////////////////////
            //  Descrição Funcionalidade: Metodo utilizado
            //  para alterar a nomeclatura do componente
            //  rotulo.
            function setRotuloValor(codigo)
            {
                rotuloValor = 'r'+ codigo; //r é utilizado para representar o rotúlo dos campos.
            }
            ////////////////////////////////////////////////
            
            ////////////////////////////////////////////////
            //  Descrição Funcionalidade: Metodo utilizado
            //  para retorna o nome atual do componente
            //  rotulo.
            function getRotuloValor()
            {
                return rotuloValor;
            }
            ////////////////////////////////////////////////
            
            ////////////////////////////////////////////////
            //  Descrição Funcionalidade: Metodo utilizado
            //  para alterar a nomeclatura do componente
            //  botão adicionar. 
            function setBotao(codigo)
            {
                botao = "<%=Form.BOTAO_ADICIONAR%>" + codigo;
            }
            ////////////////////////////////////////////////
            
            ////////////////////////////////////////////////
            //  Descrição Funcionalidade: Metodo utilizado
            //  para retorna o nome atual do componente
            //  botao adicionar.
            function getBotao()
            {
                return botao;
            }
            ////////////////////////////////////////////////
            
            ////////////////////////////////////////////////
            //  Descrição Funcionalidade: Metodo utilizado
            //  para alterar o valor do componente
            //  campoValor.
            function setCampoValor(valor)
            {
                campoValor = valor;
            }
            ////////////////////////////////////////////////
            
            ////////////////////////////////////////////////
            //  Descrição Funcionalidade: Metodo utilizado
            //  para retorna o valor atual do componente
            //  campoValor.
            function getCampoValor()
            {
                return campoValor;
            }
            ////////////////////////////////////////////////
            
            ////////////////////////////////////////////////
            //  Descrição Funcionalidade: Metodo utilizado
            //  para alterar o valor do flag que controla
            //  a existencia de um campo que foi acessado,
            //  alterado ou outros.
            function setEstado(valor)
            {
                estado = valor;
            }
            ////////////////////////////////////////////////
            
            ////////////////////////////////////////////////
            //  Descrição Funcionalidade: Metodo utilizado
            //  para retorna o valor atual do flag estado.
            function getEstado()
            {
                return estado;
            }
            
            /*
            Wendell Pereira de Farias
            Funcionalidade responsavel por adicionar o valor do item.
            */
            function adicionaConfiguracao()
            {	                 
                 var auxCampoValor = '<%=Form.CAMPO_VALOR_CAMPO%>'+ getCampoValor(); //Atualiza nome campo

                 if (validaCampos(document.getElementById(auxCampoValor))) //Valida o valor informado 
                {			
                    desabilitarBotoes(obterArrayBotoes());
                    document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ADICIONAR+"="%>'+campoValor;
                    document.form.submit();	
                }
            }
            
            /*
            Wendell Pereira de Farias
            Funcionalidade responsavel por validar o campo informado
            */
            function validaCampos(campoAtual)
            {						
                var auxCampoValor = campoAtual.name;//Atualiza nome campo
                 
                if (getEstado() != 0)//Verifica estado do campo e o tipo do valor campo.
                {
                    /*
                    Tipo de Item: DATA
                    */
                     if(retornaTipoItem(getCampoValor()) == '<%=domnTipoConfiguracao.getTexto(DomnTipoConfiguracaoGerencialParametros.DATA)%>')
                    {
                        if(!verificaCamposW3c(document.getElementById(auxCampoValor) ,<%="'"+MensagemErro.VALIDAR_ITEMS_CONFIGURACAO_GERENCIAL_PARAMETROS+"'"%>))
                        {                        
                            return false;
                        }                        
                    }
                    /*
                    Tipo de Item: VALOR
                    */
                    else if(retornaTipoItem(getCampoValor()) == '<%=domnTipoConfiguracao.getTexto(DomnTipoConfiguracaoGerencialParametros.VALOR)%>')
                    {
                        //Troca virgula por ponto
                        if((document.getElementById(auxCampoValor).value).length > 2)
                        {					
                            document.getElementById(auxCampoValor).value = (document.getElementById(auxCampoValor).value).replace(",",".");
                        }
                       
//                        if((parseFloat (document.getElementById(auxCampoValor).value)==0) || (parseFloat (document.getElementById(auxCampoValor).value)>100))
//                        {
//                            alert(<%="'"+MensagemErro.VALIDAR_VALOR_CONFIGURACAO_GERENCIAL_PARAMETROS+"'"%>);
//                            return false;
//                        }
                        
                        if(!verificaCamposW3c(document.getElementById(auxCampoValor),<%="'"+MensagemErro.VALIDAR_VALOR_CONFIGURACAO_GERENCIAL_PARAMETROS+"'"%>))
                        {
                            return false;
                        }
                    }
                    /*
                    Tipo de Item: NUMERO
                    */
                    else if(retornaTipoItem(getCampoValor()) == '<%=domnTipoConfiguracao.getTexto(DomnTipoConfiguracaoGerencialParametros.NUMERO)%>')
                    {
                        if(!verificaCamposW3c(document.getElementById(auxCampoValor),<%="'"+MensagemErro.VALIDAR_NUMERICO_CONFIGURACAO_GERENCIAL_PARAMETROS+"'"%>))
                        {
                            return false;
                        }                        
                        //O valor deve ser maior que zero.
                        if((document.getElementById(auxCampoValor).value==0))
                        {
                            alert(<%="'"+MensagemErro.VALIDAR_NUMERICO_CONFIGURACAO_GERENCIAL_PARAMETROS+"'"%>);
                            return false;
                        }                        
                    }
                    /*
                    Tipo de Item: NUMERO
                    */
                    else if(retornaTipoItem(getCampoValor()) == '<%=domnTipoConfiguracao.getTexto(DomnTipoConfiguracaoGerencialParametros.DECIMAL)%>')
                    {
                        if(!verificaCamposW3c(document.getElementById(auxCampoValor),<%="'"+MensagemErro.VALIDAR_NUMERICO_CONFIGURACAO_GERENCIAL_PARAMETROS+"'"%>))
                        {
                            return false;
                        }                        
                        //O valor deve ser maior que zero.
                        if((document.getElementById(auxCampoValor).value=="0,0000"))
                        {
                            alert(<%="'"+MensagemErro.VALIDAR_NUMERICO_CONFIGURACAO_GERENCIAL_PARAMETROS+"'"%>);
                            return false;
                        }                        
                    }
                    /*
                    Tipo de Item: TEXTO
                    */
                    if(retornaTipoItem(getCampoValor()) == '<%=domnTipoConfiguracao.getTexto(DomnTipoConfiguracaoGerencialParametros.TEXTO)%>')
                    {
                        if(!verificaCamposW3c(document.getElementById(auxCampoValor),<%="'"+MensagemErro.VALIDAR_ITEMS_CONFIGURACAO_GERENCIAL_PARAMETROS+"'"%>))
                        {
                            return false;
                        }
                    }
               }              
                 return true;              
             }         
             
            //Valida o formulario e direciona para servlet
            //Implementado por Wendell Farias
             function validaFormulario()
             {
                 var posicaoCampo=0;
                //percorre todos os campos do formulario
                for(posicaoCampo=0; posicaoCampo<document.form.elements.length; posicaoCampo++)
                {
                   //Valida apenas campo do tipo texto
                   if(document.form.elements[posicaoCampo].type=="text")
                   {
                       //valida todos os campos do formulario
                        if(!validaCampos(document.form.elements[posicaoCampo]))
                        {
                            return false;
                        }
                    }
                }                                
                //Verifica se todo o formulario foi percorrido.
                if( posicaoCampo==(document.form.elements.length))
                {
                    desabilitarBotoes(obterArrayBotoes());
                    document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_CONFIRMAR+"=3"%>';
                    document.form.submit();
                    return true;
                }
                else
                {
                    alert(<%="'"+MensagemErro.VALIDAR_COLECAO_CONFIGURACAO_GERENCIAL_PARAMETROS+"'"%>);
                    return false;
                }                
            }
            /*		
            Função utilizada para desabilitar os botões
            */
            function obterArrayBotoes()
            {
                var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
                return new Array(botao1);
            }
           /*
           Wendell Pereira de Farias
           Funcionalidade utilizada para habilitar os campos, apresentar e esconder.
           */
            function habilitarCampo(codigo)
            {			
                var tipoItem = retornaTipoItem(codigo);
                
                //Como o estado é o valor codigo anterior
                //nesta situação é verificado se o código e estado são diferentes
                //se for então atualiza os componentes da tela, utilizando o valor do estado.
                //
                //Senão for é utilizado o valor do código para utualizar os componentes da tela.
                if ((getEstado() != "x") & (getEstado() != 0))
                {
                    setRotuloValor(getEstado());
                    setBotao(getEstado());
                    setCampoValor(getEstado());
                }
                else
                {
                    setRotuloValor(codigo);
                    setBotao(codigo);
                    setCampoValor(codigo);
                }
                
                //Condição utilizada para esconder campo anterior
                //Se o código for diferente de zero                
                if ((getEstado() != "x") & (getEstado() != 0))
                {  
                    if ((document.getElementById(rotuloValor).style.display =="inline")) 
                    {   
                        document.getElementById(rotuloValor).style.display="none";

                        document.getElementById(campoValor).style.display="none";
                        document.getElementById(botao).style.display="none";
                    }                                    
                }
                /*
                Inicializa as variaveis
                */
                setRotuloValor(codigo);
                setBotao(codigo);
                setCampoValor(codigo);
                /*
                Tipo de Item: DATA
                */
                 if(tipoItem == '<%=domnTipoConfiguracao.getTexto(DomnTipoConfiguracaoGerencialParametros.DATA)%>')
                {
                    document.getElementById(rotuloValor).innerHTML = 'Data:';
                    document.getElementById(campoValor).innerHTML = '<input type="text"  class="SEFAZ-INPUT-Text" '+ 'name="<%=Form.CAMPO_VALOR_CAMPO%>' + getCampoValor() +'" '+ 'id="<%=Form.CAMPO_VALOR_CAMPO%>' + getCampoValor() + '" '+  'value="" size="10" maxlength="10"  <%=JspUtil.getCampoApenasNumero()%>/>';
                    document.getElementById(botao).innerHTML = '<input type="button" name="<%=Form.BOTAO_ADICIONAR%>'+ getBotao() +'" class="SEFAZ-INPUT-Botao" value="<%=Form.TEXTO_BOTAO_INCLUIR%>" onclick="adicionaConfiguracao();">';
                }
                /*
                Tipo de Item: VALOR
                */
                else if(tipoItem == '<%=domnTipoConfiguracao.getTexto(DomnTipoConfiguracaoGerencialParametros.VALOR)%>')
                {
                    document.getElementById(rotuloValor).innerHTML = 'Valor:';
                    document.getElementById(campoValor).innerHTML = '<input  type="text" class="SEFAZ-INPUT-Text" '+'name= "<%=Form.CAMPO_VALOR_CAMPO%>' + getCampoValor() + '"  '+'id="<%=Form.CAMPO_VALOR_CAMPO%>' + getCampoValor() + '" '+' value="" size="8" maxlength="13" onKeyPress="return mascaraValorMoedaW3c(document.form.name,this.name,2,15, true, event);" onkeydown="return mascaraValorMoedaW3c(document.form.name,this.name,2,15, true, event);" onkeyup="return mascaraValorMoedaW3c(document.form.name,this.name,2,15, true, event);" onblur="return mascaraValorMoedaW3c(document.form.name,this.name,2,15, true, event);" />';
                    document.getElementById(botao).innerHTML = '<input type="button" name="<%=Form.BOTAO_ADICIONAR%>'+ getBotao() +'" class="SEFAZ-INPUT-Botao" value="<%=Form.TEXTO_BOTAO_INCLUIR%>" onclick="adicionaConfiguracao();">';
                }
                /*
                Tipo de Item: NUMERO
                */
                else if(tipoItem == '<%=domnTipoConfiguracao.getTexto(DomnTipoConfiguracaoGerencialParametros.NUMERO)%>')
                {
                    document.getElementById(rotuloValor).innerHTML = 'Númerico:';
                    document.getElementById(campoValor).innerHTML = '<input type="text" class="SEFAZ-INPUT-Text" '+ 'name= "<%=Form.CAMPO_VALOR_CAMPO%>' + getCampoValor() + '" '+'id="<%=Form.CAMPO_VALOR_CAMPO%>' + getCampoValor() + '" '+'value="" size="10" maxlength="50" <%=JspUtil.getCampoApenasNumero()%>/>';
                    document.getElementById(botao).innerHTML = '<input type="button" name="<%=Form.BOTAO_ADICIONAR%>'+ getBotao() +'" class="SEFAZ-INPUT-Botao" value="<%=Form.TEXTO_BOTAO_INCLUIR%>" onclick="adicionaConfiguracao();">';
                }
                /*
                Tipo de Item: DECIMAL
                */
                else if(tipoItem == '<%=domnTipoConfiguracao.getTexto(DomnTipoConfiguracaoGerencialParametros.DECIMAL)%>')
                {
                    document.getElementById(rotuloValor).innerHTML = 'Númerico:';
						  document.getElementById(campoValor).innerHTML = '<input type="text" name="<%=Form.CAMPO_VALOR_CAMPO%>' + getCampoValor() + '" class="SEFAZ-INPUT-Text" id="<%=Form.CAMPO_VALOR_CAMPO%>' + getCampoValor() + '" value="" size="16" onKeyDown="mascaraValorMoedaW3c(document.form.name,this.name,4,16,true,event);" onKeyUp="mascaraValorMoedaW3c(document.form.name,this.name,4,16,true,event);" onBlur="mascaraValorMoedaW3c(document.form.name,this.name,4,16,true,event);"/>';						  
                    document.getElementById(botao).innerHTML = '<input type="button" name="<%=Form.BOTAO_ADICIONAR%>'+ getBotao() +'" class="SEFAZ-INPUT-Botao" value="<%=Form.TEXTO_BOTAO_INCLUIR%>" onclick="adicionaConfiguracao();">';
                }
                /*
                Tipo de Item: TEXTO
                */
                if(tipoItem == '<%=domnTipoConfiguracao.getTexto(DomnTipoConfiguracaoGerencialParametros.TEXTO)%>')
                {
                    var auxTexto='';
                    auxTexto ='<textarea name="<%=Form.CAMPO_VALOR_CAMPO%>' + getCampoValor() + '" '+'class="SEFAZ-INPUT-Text" '+'id= "<%=Form.CAMPO_VALOR_CAMPO%>';
                    auxTexto =auxTexto + getCampoValor()+'"';
                    auxTexto =auxTexto +  ' rows="10" cols="80" onKeyUp="cortaPalavrasW3c(this,3999);">';
                    auxTexto =auxTexto + '</textarea>';
                    
                    document.getElementById(rotuloValor).innerHTML = 'Texto:';
                    document.getElementById(campoValor).innerHTML =  auxTexto;
                    document.getElementById(botao).innerHTML = '<input type="button" name="<%=Form.BOTAO_ADICIONAR%>'+ getBotao() +'" class="SEFAZ-INPUT-Botao" value="<%=Form.TEXTO_BOTAO_INCLUIR%>" onclick="adicionaConfiguracao();">';
                }
                /*
                O metodo getEstado representa o campo que está sendo manipulado ou o ultimo a ser manipulado.
                */
                if (getEstado()!=codigo)
                {
                    if ((document.getElementById(rotuloValor).style.display =="inline")) 
                    {   
                        document.getElementById(rotuloValor).style.display="none";
                        document.getElementById(campoValor).style.display="none";
                        document.getElementById(botao).style.display="none";
                    }
                    else
                    {
                        document.getElementById(rotuloValor).style.display="inline";
                        document.getElementById(campoValor).style.display="inline";
                        document.getElementById(getBotao()).style.display="inline";
                    }
                    setEstado(codigo);
                }
                else
                {
                    setEstado("x");
                }
            }
            /*
            Wendell Pereira de Farias
            Funcionalidade que retorna o tipo de item.
            */
            function retornaTipoItem(codigo)
            {					
                <c:forEach var="configuracaoGerencialParametrosTempVo" items="${configuracaoGerencialParametrosVo.collVO}">
                    if(codigo == <c:out value='${configuracaoGerencialParametrosTempVo.codigo}'/>)
                    {
                        return "<c:out value='${configuracaoGerencialParametrosTempVo.tipoItem.textoCorrente}'/>";
                    }
                </c:forEach>				
            }
        </script>
         <jsp:include page="/util/ViewVerificaErro.jsp"/>
	<title><abaco:tituloSistema></abaco:tituloSistema></title>
	</head>
    <body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>');verificaErro(); log();">
        <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
            <center> 
                <form method="POST" action="" name="form">
                    <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                      <tr align="right">
                            <td colspan="4"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                        </tr>
                        <tr class="SEFAZ-TR-Titulo">
                            <td colspan="2">Dados do registro</td>
                        </tr>
                        <tr class="SEFAZ-TR-SubTitulo"> 
                            <td aling="center"> Código </td>
                            <td align="left"> Item para preenchimento</td>                                      
                        </tr>                                        
                        <c:forEach var="configuracaoGerencialParametrosTempVo" items="${configuracaoGerencialParametrosVo.collVO}" varStatus="status">
                            <c:if test="${configuracaoGerencialParametrosTempVo.statusCadastrado.valorCorrente==NAO}">
                                <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar" />                                   
                                <c:if test="${status.count % 2 != 0}">
                                    <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
                                </c:if>       
                                <tr class="<c:out value="${linhaEstilo}"/>"> 
                                    <td>
                                        <a href="javascript: habilitarCampo(<c:out value="${configuracaoGerencialParametrosTempVo.codigo}"/>)"><c:out value="${configuracaoGerencialParametrosTempVo.codigo}"/></a>
                                    </td>                                                                
                                    <td align="left">
                                        <c:out value="${configuracaoGerencialParametrosTempVo.descricaoItem}"/>
                                    </td>
                                </tr>      
                                <tr> 
                                    <td class="SEFAZ-TD-RotuloEntrada">
                                        <div id="r<c:out value="${configuracaoGerencialParametrosTempVo.codigo}"/>"></div>
                                    </td>
                                    <td class="SEFAZ-TD-CampoEntrada">
                                        <div id="<c:out value="${configuracaoGerencialParametrosTempVo.codigo}"/>"></div>
                                        <div id="<%=Form.BOTAO_ADICIONAR%><c:out value="${configuracaoGerencialParametrosTempVo.codigo}"/>"></div>                      
                                    </td>                                        
                                </tr>
                            </c:if>
                        </c:forEach>
                                    
                        <!-- //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        //   Tabela utilizada para informar ao usuario os itens selecionados
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        -->
                        <tr> 
                            <td>&nbsp;</td>
                        </tr>
                        <tr> 
                            <td>&nbsp;</td>
                        </tr>
                        <c:if test="${Existe == 'true'}">
                            <table name="colecaoItens" id="colecaoItens" cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                                <tr class="SEFAZ-TR-Titulo">
                                    <td colspan="2">Dado(s) Selecionado(s)</td>
                                </tr>
                                <tr class="SEFAZ-TR-SubTitulo"> 
                                    <td align="left">Item(s) preenchido(s):&nbsp;</td>
                                </tr>
                                <c:forEach var="configuracaoGerencialParametrosListaVo" items="${configuracaoGerencialParametrosVo.collVO}" varStatus="contador">
                                    <c:set var="linhaEstilo" value="SEFAZ-TD-ExibicaoPar"></c:set>
                                    <c:if test="${contador.count%2!=0}">
                                        <c:set var="linhaEstilo" value="SEFAZ-TD-ExibicaoImpar"></c:set>
                                    </c:if>
                                    <c:if test="${configuracaoGerencialParametrosListaVo.statusCadastrado.valorCorrente==SIM}">		
                                        <tr class="<c:out value="${linhaEstilo}"/>">
                                            
                                            <td align="left"><c:out value="${configuracaoGerencialParametrosListaVo.descricaoItem}"></c:out>&nbsp;:</td>
                                        </tr>
                                        <tr class="<c:out value="${linhaEstilo}"/>">
                                            <td align="left">Valor:&nbsp;<c:out value="${configuracaoGerencialParametrosListaVo.valorItem}"></c:out></td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </table>
                        </c:if>
                    <table>
                        <tr> 
                            <td>&nbsp;</td>
                        </tr>
                        <tr> 
                            <td colspan="3" align="center"> 
                                <input type="button" 	value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" 	class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR_CLONE%>" onClick=" validaFormulario();"/>
                                <abaco:botaoCancelar/>
                            </td>
                        </tr>
                        <tr> 
                            <td colspan="3">&nbsp;</td>
                        </tr>
                        <tr> 
                            <td colspan="3">&nbsp;</td>
                        </tr>
                    </table>
                        <abaco:mensagemAguardeCarregando/>
								<abaco:log/>
                </form>
            </center>
        <g:mostrarRodape/>
    </body>
</html>
