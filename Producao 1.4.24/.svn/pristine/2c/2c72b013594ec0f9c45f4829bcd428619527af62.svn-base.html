<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewAlterarConfiguracaoGerencialParametros.jsp
* Criação : Outubro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : Novembro de 2007 / Março de 2008 /  Wendell Pereira de Farias
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
	pageContext.setAttribute("DATA", new Integer(DomnTipoConfiguracaoGerencialParametros.DATA));
	pageContext.setAttribute("TEXTO", new Integer(DomnTipoConfiguracaoGerencialParametros.TEXTO));
	pageContext.setAttribute("NUMERO", new Integer(DomnTipoConfiguracaoGerencialParametros.NUMERO));	 
	pageContext.setAttribute("VALOR", new Integer(DomnTipoConfiguracaoGerencialParametros.VALOR));	 	
	pageContext.setAttribute("DECIMAL", new Integer(DomnTipoConfiguracaoGerencialParametros.DECIMAL));	 
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
            ////////////////////////////////////////////////
            // Wendell Pereira de Farias
            //  Variaveis auxiliares.
            //  Descri??o Funcionalidade: variaveis utilizadas
            //  para controlar os componentes dinamicos.
            //
            var campoValor;
            var campoValidaItens =0;
            var estado=0;
            var rotuloValor ;
            var botao ;
				var codigoCampo;
            
            ////////////////////////////////////////////////
            //  Descrição Funcionalidade: Metodo utilizado
            //  para controlar o número de itens iseridos,
            //  altera o valor atual.  
            function setCampoValidaItens(valor)
            {
                var tipo = buscarTipoNavegador();
                campoValidaItens = campoValidaItens+valor;
                document.getElementById('colecaoItens').style.display=tipo;
                document.getElementById('colecaoIten').style.display=tipo;
                document.getElementById('colecaoItem').style.display=tipo;
            }
            
            ////////////////////////////////////////////////
            //  Descrição Funcionalidade: Metodo utilizado
            //  para controlar o número de itens iseridos,
            // retorna o valor atual.
            function getCampoValidaItens()
            {
                return campoValidaItens;
            }
            
            ////////////////////////////////////////////////
            //  Descrição Funcionalidade: Metodo utilizado
            //  para alterar a nomeclatura do componente
            //  rotulo.
            function setRotuloValor(codigo)
            {
                rotuloValor = '<%=Form.CONFIGURACAO_ROTULO%>'+ codigo; //r é utilizado para identificar o rotulo do campo.
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
                botao = "<%=Form.BOTAO_ALTERAR%>" + codigo;
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
                campoValor = '<%=Form.CONFIGURACAO_PARAMETRO%>'+valor;
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
				
				function setCodigoCampo(codigo)
				{
					codigoCampo = codigo;
				}
				
				function getCodigoCampo()
				{
					return codigoCampo;
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
            Funcionalidade utilizada para adicionar itens alterados.
            */
            function adicionaConfiguracao()
            {	        
                var auxCampoValor = '<%=Form.CAMPO_VALOR_CAMPO%>'+getCodigoCampo();//Atualiza nome campo

                if (validaCampos(document.getElementById(auxCampoValor))) //Valida o valor informado 
                {		
                    desabilitarBotoes(obterArrayBotoes());
                    document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ALTERAR+"="%>'+getCodigoCampo();
                    document.form.submit();
                }                    
            }
				
            /*
            Wendell Pereira de Farias
            Funcionalidade utilizada para validar os campos.
            */
            function validaCampos(campoAtual)
            {						
                 var auxCampoValor = campoAtual.name;

                if (getEstado() != 0)
                {
                     if(retornaTipoItem(getCodigoCampo()) == '<%=DomnTipoConfiguracaoGerencialParametros.DATA%>')
                    {
                        if(!verificaCamposW3c(document.getElementById(auxCampoValor) ,<%="'"+MensagemErro.VALIDAR_ITEMS_CONFIGURACAO_GERENCIAL_PARAMETROS+"'"%>))
                        {
                            return false;
                        }
                    }
                    else if(retornaTipoItem(getCodigoCampo()) == '<%=DomnTipoConfiguracaoGerencialParametros.VALOR%>')
                    {
                        if(!verificaCamposW3c(document.getElementById(auxCampoValor),<%="'"+MensagemErro.VALIDAR_VALOR_CONFIGURACAO_GERENCIAL_PARAMETROS+"'"%>))
                        {
                            return false;
                        }                        
                    }
                    else if(retornaTipoItem(getCodigoCampo()) == '<%=DomnTipoConfiguracaoGerencialParametros.NUMERO%>')
                    {
                        if(!verificaCamposW3c(document.getElementById(auxCampoValor),<%="'"+MensagemErro.VALIDAR_NUMERICO_CONFIGURACAO_GERENCIAL_PARAMETROS+"'"%>))
                        {
                            return false;
                        }
                    }
                    else if(retornaTipoItem(getCodigoCampo()) == '<%=DomnTipoConfiguracaoGerencialParametros.DECIMAL%>')
                    {
                        if(!verificaCamposW3c(document.getElementById(auxCampoValor),<%="'"+MensagemErro.VALIDAR_NUMERICO_CONFIGURACAO_GERENCIAL_PARAMETROS+"'"%>))
                        {
                            return false;
                        }
                    }						  
                   else  if(retornaTipoItem(getCodigoCampo()) == '<%=DomnTipoConfiguracaoGerencialParametros.TEXTO%>')
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

                //Verifica se todo o formulario foi percorrido e se foi inserido algum iten alterado.
                if(( posicaoCampo==(document.form.elements.length)) && getCampoValidaItens()!=0)
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
            Funcionalidade utilizada para habilitar os botões.
            */
            function obterArrayBotoes()
            {
                var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
                return new Array(botao1);
            } 
            
            /*
            Wendell Pereira de Farias
            Funcionalidade utilizada para habilitar campos do formulario dinamico.
            */
            function habilitarCampo(codigo)
            {			
                //Como o estado é o valor codigo anterior
                //nesta situação é verificado se o código e estado são diferentes
                //se for então atualiza os componentes da tela, utilizando o valor do estado.
                //
                //Senão for é utilizado o valor do código para utualizar os componentes da tela.
                if((getEstado() != "x") & (getEstado() != 0))
                {
                    setRotuloValor(getEstado());
                    setBotao(getEstado());
                    setCampoValor(getEstado());
						  setCodigoCampo(getEstado());
                }
                else
                {
                    setRotuloValor(codigo);
                    setBotao(codigo);
                    setCampoValor(codigo);
						  setCodigoCampo(codigo);
                }
                
                //Condição utilizada para esconder campo anterior
                //Se o código for diferente de zero                
                if ((getEstado() != "x") & (getEstado() != 0))
                {  
                    if ((document.getElementById(rotuloValor).style.display =="inline")) 
                    {   
                        document.getElementById(rotuloValor).style.display="none";                
                        document.getElementById(campoValor).style.display="none";
                        //document.getElementById(botao).style.display="none";
                    }            
                }
                /*
                Inicializando campos do formulario dinamico
                */
                setRotuloValor(codigo);
                setBotao(codigo);
                setCampoValor(codigo);
					 setCodigoCampo(codigo);               
                /* 
                Tipo de Item DATA.
                */
                if (getEstado()!=codigo)
                {
                    if ((document.getElementById(rotuloValor).style.display =="inline")) 
                    {   
                        document.getElementById(rotuloValor).style.display="none";
                        document.getElementById(campoValor).style.display="none";
                       // document.getElementById(botao).style.display="none";
                    }
                    else
                    {
                        document.getElementById(rotuloValor).style.display="inline";
                        document.getElementById(campoValor).style.display="inline";
                        //document.getElementById(getBotao()).style.display="inline";
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
            Funcionalidade utilizada para retornar o tipo de itens de cada registro,
            */
            function retornaTipoItem(codigo)
            {					
                <c:forEach var="configuracaoGerencialParametrosTempVo" items="${configuracaoGerencialParametrosVo.collVO}">
                    if(codigo == <c:out value="${configuracaoGerencialParametrosTempVo.codigo}"></c:out>)
                    {
                        return "<c:out value="${configuracaoGerencialParametrosTempVo.tipoItem.valorCorrente}"></c:out>";
                    }
                </c:forEach>				
            }
        </script>
        <jsp:include page="/util/ViewVerificaErro.jsp"/>
	<title><abaco:tituloSistema></abaco:tituloSistema></title>
    </head>
    <body class="SEFAZ-Body" onload="botaoAjuda(this.document, '<%=Form.BOTAO_AJUDA%>', '<%=Form.TEXTO_BOTAO_AJUDA%>', '<c:out value="${requestScope.atributoTituloFuncionalidade}" />', '<abaco:contexto/>'); verificaErro();log();">
        <g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
            <center> 
                <form method="POST" action="" name="form">
                    <table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
                        <tr align="right">
                            <td colspan="2"><div id="<%=Form.BOTAO_AJUDA%>"></div></td>
                        </tr>
                        <tr class="SEFAZ-TR-Titulo">
                            <td colspan="2">Dados do registro</td>
                        </tr>
                        <tr class="SEFAZ-TR-SubTitulo"> 
                            <td align="center"> Código </td>
                            <td align="left"> Item para preenchimento</td>                                      
                        </tr>                                                                            
                        <c:forEach var="configuracaoGerencialParametrosTempVo" items="${configuracaoGerencialParametrosVo.collVO}" varStatus="status">
                            <c:if test="${configuracaoGerencialParametrosTempVo.statusCadastrado.valorCorrente==SIM}">
                                <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar" />                            
                                <c:if test="${status.count % 2 != 0}">
                                    <c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"/>
                                </c:if>                    
                                <tr class="<c:out value="${linhaEstilo}"/>"> 
                                    <td>
                                        <a href="javascript: habilitarCampo('<c:out value="${configuracaoGerencialParametrosTempVo.codigo}"/>')">
                                        <c:out value="${configuracaoGerencialParametrosTempVo.codigo}"/></a>
                                    </td>                                                                
                                    <td align="left">
                                        <c:out value="${configuracaoGerencialParametrosTempVo.descricaoItem}"/>
                                    </td>
                                </tr>
                                <tr>
											<td colspan="2">
												<table cellspacing="1" cellpadding="0" border="0" width="740" align="center"> 
													<tr>
														<td class="SEFAZ-TD-RotuloEntrada" id="<%=Form.CONFIGURACAO_ROTULO%><c:out value="${configuracaoGerencialParametrosTempVo.codigo}"/>" style="display:none">
															<c:if test="${configuracaoGerencialParametrosTempVo.tipoItem.valorCorrente == DATA}">
																Data:&nbsp;
															</c:if>
															<c:if test="${configuracaoGerencialParametrosTempVo.tipoItem.valorCorrente == VALOR}">
																Valor:&nbsp;
															</c:if>
															<c:if test="${configuracaoGerencialParametrosTempVo.tipoItem.valorCorrente == NUMERO}">
																Numérico:&nbsp;
															</c:if>
															<c:if test="${configuracaoGerencialParametrosTempVo.tipoItem.valorCorrente == TEXTO}">
																Texto:&nbsp;
															</c:if>
															<c:if test="${configuracaoGerencialParametrosTempVo.tipoItem.valorCorrente == DECIMAL}">
																Decimal:&nbsp;
															</c:if>													
														</td>
														<td class="SEFAZ-TD-CampoEntrada" id='<%=Form.CONFIGURACAO_PARAMETRO%><c:out value="${configuracaoGerencialParametrosTempVo.codigo}"/>' style="display:none">
															<c:if test="${configuracaoGerencialParametrosTempVo.tipoItem.valorCorrente == DATA}">
																<input type="text" class="SEFAZ-INPUT-Text" name="<%=Form.CAMPO_VALOR_CAMPO%><c:out value='${configuracaoGerencialParametrosTempVo.codigo}'/>" id="<%=Form.CAMPO_VALOR_CAMPO%><c:out value='${configuracaoGerencialParametrosTempVo.codigo}'/>" value="<c:out value='${configuracaoGerencialParametrosTempVo.valorItemFormatado}'/>" size="10" maxlength="10" <%=JspUtil.getCampoApenasNumero()%>></input><font color="red">*</font>
															</c:if>
															<c:if test="${configuracaoGerencialParametrosTempVo.tipoItem.valorCorrente == VALOR}">
																<input  type="text" class="SEFAZ-INPUT-Text" name="<%=Form.CAMPO_VALOR_CAMPO%><c:out value='${configuracaoGerencialParametrosTempVo.codigo}'/>" id="<%=Form.CAMPO_VALOR_CAMPO%><c:out value='${configuracaoGerencialParametrosTempVo.codigo}'/>" value="<c:out value="${configuracaoGerencialParametrosTempVo.valorItemFormatado}"/>" size="15" maxlength="15" onKeyPress="return mascaraValorMoedaW3c(document.form.name,this.name,2,15, true, event);" onkeydown="return mascaraValorMoedaW3c(document.form.name,this.name,2,15, true, event);" onkeyup="return mascaraValorMoedaW3c(document.form.name,this.name,2,15, true, event);" onblur="return mascaraValorMoedaW3c(document.form.name,this.name,2,15, true, event);" /><font color="red">*</font>
															</c:if>
															<c:if test="${configuracaoGerencialParametrosTempVo.tipoItem.valorCorrente == NUMERO}">
																<input type="text" name="<%=Form.CAMPO_VALOR_CAMPO%><c:out value='${configuracaoGerencialParametrosTempVo.codigo}'/>" id="<%=Form.CAMPO_VALOR_CAMPO%><c:out value='${configuracaoGerencialParametrosTempVo.codigo}'/>" class="SEFAZ-INPUT-Text" value="<c:out value='${configuracaoGerencialParametrosTempVo.valorItemFormatado}'/>" size="10" maxlength="50" <%=JspUtil.getCampoApenasNumero()%>></input><font color="red">*</font>
															</c:if>
															<c:if test="${configuracaoGerencialParametrosTempVo.tipoItem.valorCorrente == TEXTO}">
																
																<textarea name='<%=Form.CAMPO_VALOR_CAMPO%><c:out value="${configuracaoGerencialParametrosTempVo.codigo}"/>' id="<%=Form.CAMPO_VALOR_CAMPO%><c:out value='${configuracaoGerencialParametrosTempVo.codigo}'/>" class="SEFAZ-INPUT-Text" rows="10" cols="80" onfocus="return cortaPalavrasW3c(this,3999);" onblur="return cortaPalavrasW3c(this,3999);"  onkeyup="return cortaPalavrasW3c(this,3999);"><c:out value="${configuracaoGerencialParametrosTempVo.valorItemFormatado}"></c:out></textarea><font color="red">*</font>																
															</c:if>
															<c:if test="${configuracaoGerencialParametrosTempVo.tipoItem.valorCorrente == DECIMAL}">
																<input  type="text" class="SEFAZ-INPUT-Text" name="<%=Form.CAMPO_VALOR_CAMPO%><c:out value='${configuracaoGerencialParametrosTempVo.codigo}'/>" id="<%=Form.CAMPO_VALOR_CAMPO%><c:out value='${configuracaoGerencialParametrosTempVo.codigo}'/>" value="<c:out value="${configuracaoGerencialParametrosTempVo.valorItemFormatado}"/>" size="16" maxlength="16" onKeyPress="return mascaraValorMoedaW3c(document.form.name,this.name,4,16, true, event);" onkeydown="return mascaraValorMoedaW3c(document.form.name,this.name,4,16, true, event);" onkeyup="return mascaraValorMoedaW3c(document.form.name,this.name,4,16, true, event);" onblur="return mascaraValorMoedaW3c(document.form.name,this.name,4,16, true, event);" /><font color="red">*</font>
															</c:if>
															<input type="button" value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" class="SEFAZ-INPUT-Botao" name='<%=Form.BOTAO_ALTERAR%><c:out value="${configuracaoGerencialParametrosTempVo.codigo}"/>' onClick="adicionaConfiguracao();"/>															
														</td>
													</tr>
												</table>
											</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        <!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        //   Tabela utilizada para informar ao usuario os itens selecionados//
                        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        -->
                        <tr>                             
                            <td colspan="2">&nbsp;</td>                            
                        </tr>
                     
                        <tr id="colecaoItens" style="display: none">                         
                            <tr id="colecaoIten" style="display: none" class="SEFAZ-TR-Titulo">
                                    <td colspan="2">Dado(s) Selecionado(s)</td>
                            </tr>
                            <tr id="colecaoItem" style="display: none" class="SEFAZ-TR-SubTitulo"> 
                                 <td colspan="2"> Item(s) preenchido(s):&nbsp;</td>
                            </tr>
                            <c:forEach var="configuracaoGerencialParametrosListaVo" items="${configuracaoGerencialParametrosVo.collVO}" varStatus="contador">
                                    <c:set var="linhaEstilo" value="SEFAZ-TD-ExibicaoPar"></c:set>
                                    <c:if test="${contador.count%2!=0}">
                                            <c:set var="linhaEstilo" value="SEFAZ-TD-ExibicaoImpar"></c:set>                                                          
                                    </c:if>
                                    <c:if test="${configuracaoGerencialParametrosListaVo.statusCadastrado.valorCorrente==NAO}">		                                   
                                            <script type="text/javascript" language="javascript">setCampoValidaItens(1);</script>
                                            <tr class="<c:out value="${linhaEstilo}"/>">
                                                    <td align="left" colspan="2"><c:out value="${configuracaoGerencialParametrosListaVo.descricaoItem}"></c:out>&nbsp;:</td>
                                            </tr>
                                            <tr class="<c:out value="${linhaEstilo}"/>">
                                                    <td align="left" colspan="2">Valor:&nbsp;<c:out value="${configuracaoGerencialParametrosListaVo.valorItemFormatado}"></c:out></td>
                                            </tr>                                    
                                    </c:if>
                            </c:forEach>
                    </tr>
                </table>
                    <table  cellspacing="1" cellpadding="0" border="0" width="640" align="center">
                        <tr> 
                            <td>&nbsp;</td>
                        </tr>
                        <tr> 
                            <td colspan="3" align="center"> 
                                <input type="button" value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR_CLONE%>" onClick="return validaFormulario();"/>
                                <abaco:botaoCancelar/>
                            </td>
                        </tr>
								<tr> 
									<td colspan="3"><font color="red"><b>* Campos Obrigatórios</b></font></td>
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
