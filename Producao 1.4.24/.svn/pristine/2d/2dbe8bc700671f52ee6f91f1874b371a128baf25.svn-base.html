<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManterGIAITCDAbaBensTributaveis.jsp
* Criação : Dezembro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.generico.bemtributavel.Form"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoUsuario"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%
	pageContext.setAttribute("SIM", new Integer(DomnSimNao.SIM));
	pageContext.setAttribute("NAO", new Integer(DomnSimNao.NAO));
   
   pageContext.setAttribute("PROTOCOLO_MANUAL", new Integer(DomnTipoProtocolo.PROTOCOLO_MANUAL));
	pageContext.setAttribute("PROTOCOLO_AUTOMATICO", new Integer(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO));
   
	pageContext.setAttribute("SERVIDOR", new Integer(DomnTipoUsuario.SERVIDOR));
	pageContext.setAttribute("CONTRIBUINTE", new Integer(DomnTipoUsuario.CONTRIBUINTE));	
	
	pageContext.setAttribute("IMOVEL_URBANO", new Integer(DomnTipoBem.IMOVEL_URBANO));
	pageContext.setAttribute("IMOVEL_RURAL", new Integer(DomnTipoBem.IMOVEL_RURAL));
	pageContext.setAttribute("OUTROS_BENS", new Integer(DomnTipoBem.OUTROS_BENS));
	
	pageContext.setAttribute("PENDENTE_DE_PROTOCOLO", new Integer(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO));	
	pageContext.setAttribute("EM_ELABORACAO", new Integer(DomnStatusGIAITCD.EM_ELABORACAO));
	pageContext.setAttribute("PROTOCOLADO", new Integer(DomnStatusGIAITCD.PROTOCOLADO));		
	pageContext.setAttribute("PROTOCOLO_AUTORIZADO_PELA_GIOR", new Integer(DomnStatusGIAITCD.PROTOCOLO_AUTORIZADO_PELA_GIOR));	
	pageContext.setAttribute("ALTERADO_PELO_SERVIDOR", new Integer(DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR));
   pageContext.setAttribute("ALTERADO_PELA_GIOR", new Integer(DomnStatusGIAITCD.ALTERADO_PELA_GIOR));
   pageContext.setAttribute("AVALIACAO_EXCLUIDA", new Integer(DomnStatusGIAITCD.AVALIACAO_EXCLUIDA));
	
	
	pageContext.setAttribute("SEPARACAO_DIVORCIO_PARTILHA", new Integer(DomnTipoProcesso.SEPARACAO_DIVORCIO_PARTILHA));
	pageContext.setAttribute("DOACAO", new Integer(DomnTipoProcesso.DOACAO));
%>
		<script type="text/javascript" language="javascript" >      
			function obterArrayBotoes()
			{
				var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
				var botao2 = document.form.<%=Form.BOTAO_ANTERIOR_CLONE%>;
				var botao3 = document.form.<%=Form.BOTAO_PROXIMO_CLONE%>;
				return new Array(botao1, botao2, botao3);
			}
							
			function adicionarBemTributavel()
			{
				if(validaCampos())
				{
					desabilitarBotoes(obterArrayBotoes());
					document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ADICIONAR_BEM_TRIBUTAVEL+"=1"%>';
					document.form.submit();
				}
			}
			function alterarBemTributavel()
			{
				if(validaCampos())
				{
					desabilitarBotoes(obterArrayBotoes());
					document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_ALTERAR_BEM_TRIBUTAVEL+"=1"%>';
					document.form.submit();
				}
			}
			function validaCampos()
			{
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_CLASSIFICACAO_BEM%>,<%="'"+MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_CLASSIFICACAO+"'"%>))
				{
					return false;
				}
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>,<%="'"+MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_TIPO+"'"%>))
				{
					return false;
				}
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DESCRICAO%>,<%="'"+MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_DESCRICAO+"'"%>))
				{
					return false;
				}
				if(document.form.<%=Form.CAMPO_SELECT_CLASSIFICACAO_BEM%>.value == '<%=DomnTipoBem.OUTROS_BENS%>')
				{
              if(validarCampoMercado())
              {
                
              }
              else
              {
                if(!verificaCamposW3c(document.form.<%=Form.CAMPO_VALOR_MERCADO%>,<%="'"+MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_VALOR_MERCADO+"'"%>))
					{
						return false;
					}
					else
					{
						var valorMercado = document.form.<%=Form.CAMPO_VALOR_MERCADO%>.value;
						valorMercado = replaceAll(valorMercado, ".", "");
                  valorMercado = replaceAll(valorMercado, ",", ".");
						if(parseFloat(valorMercado) <= 0)
						{
							alert(<%="'"+MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_VALOR_MERCADO_MAIOR_ZERO+"'"%>);
							return false;
						}
					}
              }
					
				}
				return true;
			}
			
			function listarBem()
			{
				if(document.form.<%=Form.CAMPO_SELECT_CLASSIFICACAO_BEM%>.value == "")
				{
					var listaDestino = document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>.length;
					for(var i=0; i < listaDestino; i++)
					{
						document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>.options[1] = null;
					}
					return false;
				}
				desabilitarBotoes(obterArrayBotoes());
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_LISTAR_BEM+"=1"%>';
				document.form.submit();
			}
					
			function solicitarAlterarBem(indiceBem)
			{
				desabilitarBotoes(obterArrayBotoes());
				document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_SOLICITAR_ALTERAR_BEM+"="%>'+indiceBem;
				document.form.submit();
			}
			
			function excluirBem(indiceBem)
			{
				if(confirm('Deseja realmente excluir o bem?'))
				{
					desabilitarBotoes(obterArrayBotoes());
					document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_EXCLUIR_BEM+"="%>'+indiceBem;
					document.form.submit();
					return true;
				}
				else
				{
					return false
				}
			}			

			function adicionarDescricaoBem()
			{                   
            var descricaoBem = document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>[document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>.selectedIndex].text; 
            var tipoProtocolo = document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>[document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>.selectedIndex].getAttribute("data-tipoprotocolo");
            
            if(tipoProtocolo == <%=DomnTipoProtocolo.PROTOCOLO_MANUAL%> ){
               document.getElementById('painel-msg-bem-manual').style.display = "table-cell";
            }else{
               document.getElementById('painel-msg-bem-manual').style.display = "none";
            }
            validarCampoMercado(descricaoBem);
          	document.form.<%=Form.CAMPO_HIDDEN_DESCRICAO_TIPO_BEM%>.value = descricaoBem;
			}
        			
			function salvarBensTributaveis()
			{
				if(validaFormulario())
				{
					desabilitarBotoes(obterArrayBotoes());
					document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.BOTAO_SALVAR_BENS_TRIBUTAVEIS+"=1"%>';
					document.form.submit();
				}
			}
			
			function validaFormulario()
			{
				if ((document.getElementById('idBemTributavel') == null))
				{
				  alert(<%="'"+MensagemErro.VALIDAR_BEM_TRIBUTAVEL+"'"%>);
				  return false;
				}
				return true
			}
         
         function mensagemBemIsento(){
            //<img src="/imagens/warning2.png" width="16" height="16"><img>
            if( document.form.<%=Form.CAMPO_CHECK_ISENCAO_PREVISTA_LEI%>.value == '<%=DomnSimNao.SIM%>'  ){
               document.getElementById('mensagemBemIsento').innerHTML = '<img src="/imagens/warning2.png" width="16" height="16"><img><font color="red">Quando a GIA ITCD possuir bens marcados com o campo "isenção prevista em lei", seu protocolo não será automático.<font>';           
            }else{
               document.getElementById('mensagemBemIsento').innerHTML = "";        
            }
         }
         
         function validarCampoMercado(descricaoBem)
         {         
          var IPVA = 4;  
          var REBANHO = 5;
          //var descricaoBem  =  document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>[document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>.selectedIndex].text ;
          var verificacaoIdBem =  document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>[document.form.<%=Form.CAMPO_SELECT_TIPO_BEM%>.selectedIndex].getAttribute("data-tipoverificacao");;  
          if(verificacaoIdBem == IPVA || verificacaoIdBem == REBANHO)
            {            
               document.getElementById('idValorMercado').style.visibility = "hidden";
               return true;
            }
            else{             
               document.getElementById('idValorMercado').style.visibility = "visible";
               return false;
            }
         }
		</script>

<form method="POST" name="form" action="" >
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr> 
			<td class="SEFAZ-TD-RotuloEntrada" width="293">Classificação do Bem:&nbsp;</td>
			<td class="SEFAZ-TD-CampoEntrada">
				<c:choose>
					<c:when test="${bemTributavelVo.exibirTipoOutrosBens}">
						<abaco:campoSelectDominio 
							ajuda="" 
							classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem"
							name="<%=Form.CAMPO_SELECT_CLASSIFICACAO_BEM%>" 
							tabIndex="" 
							mostrarSelecione="true" 
							onChange="listarBem()" 
							idCampo="<%=Form.CAMPO_SELECT_CLASSIFICACAO_BEM%>" 
							opcaoSelecionada="${bemTributavelVo.bemVo.classificacaoTipoBem.valorCorrente}">
						</abaco:campoSelectDominio><font color="red">*</font>					
					</c:when>
					<c:otherwise>
						<abaco:campoSelectDominio 
							ajuda="" 
							classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem"
							name="<%=Form.CAMPO_SELECT_CLASSIFICACAO_BEM%>" 
							tabIndex="" 
							mostrarSelecione="true" 
							onChange="listarBem()" 
							idCampo="<%=Form.CAMPO_SELECT_CLASSIFICACAO_BEM%>" 
							naoMostrar="<%=DomnTipoBem.OUTROS_BENS%>"
							opcaoSelecionada="${bemTributavelVo.bemVo.classificacaoTipoBem.valorCorrente}">
						</abaco:campoSelectDominio><font color="red">*</font>					
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr> 
			<td class="SEFAZ-TD-RotuloEntrada" width="293">Tipo do Bem:&nbsp;</td>
			<td class="SEFAZ-TD-CampoEntrada">
				<select name="<%=Form.CAMPO_SELECT_TIPO_BEM%>" id="<%=Form.CAMPO_SELECT_TIPO_BEM%>" onchange="adicionarDescricaoBem(); ">
					<option value="" selected><%=Form.SELECIONE%></option>
					<c:forEach var="bemAuxVo" items="${bemTributavelVo.bemVo.collVO}">
						<option id="<c:out value="${bemAuxVo.tipoVerificacao.valorCorrente}"></c:out>" data-tipoverificacao="<c:out value="${bemAuxVo.tipoVerificacao.valorCorrente}"></c:out>" data-tipoprotocolo="<c:out value="${bemAuxVo.tipoProtocoloGIA.valorCorrente}"></c:out>" value="<c:out value="${bemAuxVo.codigo}"></c:out>" <c:if test="${bemAuxVo.codigo == bemTributavelVo.bemVo.codigo}">selected</c:if>>
							<c:out value="${bemAuxVo.descricaoTipoBem}"></c:out>                     
						</option>                
					</c:forEach>
				</select><font color="red">*</font>
			</td>
		  <td><input type="hidden" name="<%=Form.CAMPO_HIDDEN_DESCRICAO_TIPO_BEM%>" value="<c:out value="${bemTributavelVo.bemVo.descricaoTipoBem}"></c:out>"></td>      
		</tr>
		<tr> 
			<td class="SEFAZ-TD-RotuloEntrada" width="293">Descrição:&nbsp;</td>
			<td class="SEFAZ-TD-CampoEntrada">
				<input type="text" name="<%=Form.CAMPO_DESCRICAO%>" class="SEFAZ-INPUT-Text" id="<%=Form.CAMPO_DESCRICAO%>" size="48" maxlength="100" value="<c:out value="${bemTributavelVo.descricaoBemTributavel}"></c:out>" onblur="toUpperCaseW3c(this);"></input><font color="red">*</font>		
			</td>
		</tr>
		<tr id="idValorMercado"> 
			<td class="SEFAZ-TD-RotuloEntrada" width="293">Valor de Mercado ou Valor Tributável:&nbsp;</td>
			<td class="SEFAZ-TD-CampoEntrada">
				<abaco:campoMonetario name="<%=Form.CAMPO_VALOR_MERCADO%>" quantidadeDigitosInteiros="13" size="16" value="${bemTributavelVo.valorMercadoFormatado}"></abaco:campoMonetario><font color="red">*</font>
			</td>
		</tr>
		<c:if test="${bemTributavelVo.exibirBemParticular}">
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="370">O Bem é Particular (Anterior Casamento / Herança / Doação):&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada">
					<abaco:campoSelectDominio ajuda="Selecione se o bem é particular ou não." classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" name="<%=Form.CAMPO_CHECK_BEM_PARTICULAR%>" tabIndex="" idCampo="<%=Form.CAMPO_CHECK_BEM_PARTICULAR%>" opcaoSelecionada="${bemTributavelVo.isencaoPrevista.valorCorrente}" valorDefault="<%=DomnSimNao.NAO%>" mostrarSelecione="false"></abaco:campoSelectDominio>					
				</td>
			</tr>		
		</c:if>            
      
      <c:if test="${bemTributavelVo.exibirIsencaoPrevistaEmLei && bemTributavelVo.giaITCDVo.exibirIsencaoBemTributavel}">
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="293">Tem Isenção Prevista em Lei:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada" >
               <table>
                  <tr>
                     <td>
                        <abaco:campoSelectDominio ajuda="Selecione se o bem é insento ou não." classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" name="<%=Form.CAMPO_CHECK_ISENCAO_PREVISTA_LEI%>" tabIndex="" idCampo="<%=Form.CAMPO_CHECK_ISENCAO_PREVISTA_LEI%>" opcaoSelecionada="${bemTributavelVo.isencaoPrevista.valorCorrente}" onChange="mensagemBemIsento();" valorDefault="<%=DomnSimNao.NAO%>"  mostrarSelecione="false"></abaco:campoSelectDominio>
                     </td>
                     <td>
                         <div id="mensagemBemIsento" ></div>
                     </td>
                  </tr>
               </table>
            </td>				
			</tr>
         <tr>
				<td colspan="2"   >
               <div id="mensagemBemIsentoz" ></div>
            </td>				
			</tr>
		</c:if>
      
		<tr> 
				<td  colspan="2" width="370" align="center">
					<c:if test="${not bemTributavelVo.alterar}">
						<input type="button" align="center" name="<%=Form.BOTAO_ADICIONAR%>" value="<%=Form.TEXTO_BOTAO_ADICIONAR%>" class="SEFAZ-INPUT-Botao" onClick="adicionarBemTributavel();"></input>
					</c:if>
					<c:if test="${bemTributavelVo.alterar}">
						<input type="button" align="center" name="<%=Form.BOTAO_ALTERAR%>" value="<%=Form.TEXTO_BOTAO_ALTERAR%>" class="SEFAZ-INPUT-Botao" onClick="alterarBemTributavel();"></input>
					</c:if>
				</td>					
		</tr>
      <tr>
         <td align="center" id="painel-msg-bem-manual" style="display: none" class="SEFAZ-FONT-MensagemErro" colspan="4">Informativo: Este tipo de Bem ter por obrigatoriedade ter Avaliação Manual. Portanto essa GIA-ITCD não poderá ter protocolo Automático.</td>
      </tr>
		<tr> 
			<td colspan="2">&nbsp;</td>
		</tr>
		<c:if test="${empty bemTributavelVo.collVO}">
			<c:if test="${not bemTributavelVo.alterar}">
				<tr>
					<td align="center" class="SEFAZ-FONT-MensagemErro" colspan="4">
						Nenhum Bem Tributável foi cadastrado.
					</td>
				</tr>
			</c:if>
		</c:if>
		<c:if test="${not empty bemTributavelVo.collVO}">
			<tr class="SEFAZ-TR-Titulo">
				<td colspan="7">Bens Cadastrados</td>
			</tr>
			<table id="idBemTributavel" align="center" border="0" cellpadding="0" cellspacing="1" width="740">
				<tr class="SEFAZ-TR-SubTitulo">
					<td align="center" width="100">Classificação</td>
					<td align="center" width="108">Tipo</td>
					<td align="center" width="100">Descrição</td>	
					<td align="center" >Valor Declarado</td>
					<td align="center" >Valor Arbitrado</td>
               
					<c:if test="${bemTributavelVo.giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || bemTributavelVo.giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4'}">
                  <td align="center">Concorda Valor Arbitrado?</td>
              </c:if>
               
					<c:if test="${bemTributavelVo.exibirIsencaoPrevistaEmLei && bemTributavelVo.giaITCDVo.exibirIsencaoBemTributavel}">
                  <td align="center">Isenção Prevista</td>
               </c:if>
					<c:if test="${bemTributavelVo.exibirBemParticular}">
						<td align="center">Bem Particular </td>					
					</c:if>
					<td>&nbsp;</td>
				</tr>				
				<c:forEach var="bemTributavelAuxVo" items="${bemTributavelVo.collVO}" varStatus="contador">
					<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoPar"></c:set>
					<c:if test="${contador.count % 2 != 0}">
						<c:set var="linhaEstilo" value="SEFAZ-TR-ExibicaoImpar"></c:set>
					</c:if>
					<tr class="<c:out value='${linhaEstilo}'></c:out>">
						<td ><c:out value="${bemTributavelAuxVo.bemVo.classificacaoTipoBem.textoCorrente}"></c:out></td>
						<td ><c:out value="${bemTributavelAuxVo.bemVo.descricaoTipoBem}"></c:out></td>
						<td ><c:out value="${bemTributavelAuxVo.descricaoBemTributavel}"></c:out></td>	                  
                           <c:choose>
                              <c:when test="${bemTributavelAuxVo.concordaComValorArbitrado.textoCorrente == 'SIM'}">
                                 <td align="center" valign="top" colspan="1">R$ <c:out value="${bemTributavelAuxVo.valorInformadoFormatado}"/></td>
                                 <td align="center" valign="top" colspan="1">R$ <b><c:out value="${bemTributavelAuxVo.valorMercadoFormatado}"></c:out></b></td>
                              </c:when>
                              <c:otherwise>
                                 <td align="center" valign="top" colspan="1">R$ <b><c:out value="${bemTributavelAuxVo.valorInformadoFormatado}"></c:out></b></td>
                                 <td align="center" valign="top" colspan="1">R$ <c:out value="${bemTributavelAuxVo.valorMercadoFormatado}"></c:out></td>
                              </c:otherwise>
                           </c:choose>
                  <c:if test="${bemTributavelVo.giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.3' || bemTributavelVo.giaITCDVo.numeroVersaoGIAITCD.textoCorrente == 'GIA Versão 1.4'}">
                           <td align="center" valign="top"><c:out value="${bemTributavelAuxVo.concordaComValorArbitrado.textoCorrente}"/></td> 
                  </c:if>		
                  
                <c:if test="${bemTributavelVo.exibirIsencaoPrevistaEmLei && bemTributavelVo.giaITCDVo.exibirIsencaoBemTributavel}">						
							<td ><c:out value="${bemTributavelAuxVo.isencaoPrevista.textoCorrente}"></c:out></td>						
						</c:if>
                  
						<c:if test="${bemTributavelVo.exibirBemParticular}">  
							<td> <c:out value="${bemTributavelAuxVo.bemParticular.textoCorrente}"></c:out></td>						
						</c:if>
						<td >
						<c:if test="${not bemTributavelVo.alterar}">
							
                     <!-- Contribuinte-->
							<c:if test="${(bemTributavelVo.giaITCDVo.statusVo.statusGIAITCD.valorCorrente == PENDENTE_DE_PROTOCOLO || bemTributavelVo.giaITCDVo.statusVo.statusGIAITCD.valorCorrente == EM_ELABORACAO) && bemTributavelAuxVo.tipoUsuario.valorCorrente == CONTRIBUINTE}">						
								<a href="javascript:void(solicitarAlterarBem(<c:out value="${contador.index}"></c:out>));">Alterar</a>&nbsp;&nbsp;
								<a href="javascript:void(excluirBem(<c:out value="${contador.index}"></c:out>));">Excluir</a>&nbsp;&nbsp;							
							</c:if>
                     
							<!-- Servidor GIA PROTOCOLADO-->
                     <!-- Codigo alterado para permitir exibir os links ALTERAR e EXCLUIR para um servidor mesmo que tenha sido inserido pelo contribuinte-->
                     <!--   && (bemTributavelAuxVo.tipoUsuario.valorCorrente == SERVIDOR || bemTributavelAuxVo.tipoUsuario.valorCorrente == CONTRIBUINTE)  -->
							<c:if test="${(bemTributavelVo.giaITCDVo.statusVo.statusGIAITCD.valorCorrente != PENDENTE_DE_PROTOCOLO && bemTributavelVo.giaITCDVo.statusVo.statusGIAITCD.valorCorrente != EM_ELABORACAO)}">						
								<a href="javascript:void(solicitarAlterarBem(<c:out value="${contador.index}"></c:out>));">Alterar</a>&nbsp;&nbsp;
								<a href="javascript:void(excluirBem(<c:out value="${contador.index}"></c:out>));">Excluir</a>&nbsp;&nbsp;							
							</c:if>								
						</c:if>
						</td>
					</tr>
				</c:forEach>								
			</table>
		</c:if>
	</table>  
	
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr> 
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr> 
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td align="center">
				<input type="button" value="<%=Form.TEXTO_BOTAO_ANTERIOR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_ANTERIOR_CLONE%>" onclick="solicitarAbaDadosGerais();">
				<abaco:botaoCancelar/>
				<input type="button" value="<%=Form.TEXTO_BOTAO_SALVAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR_CLONE%>" id="btnConfirmar" onclick="javascript: salvarBensTributaveis();"></input>
				<c:if test="${bemTributavelVo.giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente == SEPARACAO_DIVORCIO_PARTILHA}">
					<input type="button" value="<%=Form.TEXTO_BOTAO_PROXIMO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PROXIMO_CLONE%>" onclick="solicitarAbaConjuge();">
				</c:if>
				<c:if test="${bemTributavelVo.giaITCDVo.naturezaOperacaoVo.tipoProcesso.valorCorrente != SEPARACAO_DIVORCIO_PARTILHA}">
					<input type="button" value="<%=Form.TEXTO_BOTAO_PROXIMO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PROXIMO_CLONE%>" onclick="solicitarAbaBeneficiarios();">
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="4"><font color="red"><b>* Campos Obrigatórios</b></font></td>
		</tr>
	</table>
	<abaco:log/>
</form>


