<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewManterGIAITCDInventarioArrolamentoAbaDadosGerais.jsp
* Criação : Outubro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log : 
*/
--%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@page import="br.com.abaco.util.http.JspUtil"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.Form"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnEstadoCivil"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoRenuncia"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoInventario"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoContribuinte"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>

<%
	pageContext.setAttribute("isIncluir",""+JspUtil.getCodigoPermissaoOriginal(request).equals(FormAcesso.CODIGO_INCLUIR_GIAITCD_INVENTARIO_ARROLAMENTO));
%>

<script type="text/javascript" language="javascript">
	
   function habilitarCampo(){
      habilitaCamposTipoProcesso();
      habilitarCampoEstadoCivil();
   }
   	
	function obterArrayBotoes()
	{
		var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR_CLONE%>;
		var botao2 = document.form.<%=Form.BOTAO_PROXIMO_CLONE%>;
		return new Array(botao1, botao2);
	}
	
	function consultarInventariante()
	{
		desabilitarBotoes(obterArrayBotoes());
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_PESQUISAR_INVENTARIANTE+"=1"%>';
		document.form.submit();
	}
	
	function consultarDeCujus()
	{
		desabilitarBotoes(obterArrayBotoes());
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_PESQUISAR_DE_CUJUS+"=1"%>';
		document.form.submit();
	}
	function consultarDeCujusSemCPF()
	{
		desabilitarBotoes(obterArrayBotoes());
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_PESQUISAR_DE_CUJUS_SEM_CPF+"=1"%>';
		document.form.submit();
	}
	
	function consultarMeeiro()
	{
		desabilitarBotoes(obterArrayBotoes());
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_PESQUISAR_MEEIRO+"=1"%>';
		document.form.submit();
	}
	
	function consultarProcurador()
	{
		desabilitarBotoes(obterArrayBotoes());
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SOLICITAR_PESQUISAR_PROCURADOR+"=1"%>';
		document.form.submit();
	}
	
	function salvarDadosGerais()
	{
		if(validaFormulario())
		{
			desabilitarBotoes(obterArrayBotoes());
			document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_SALVAR_DADOS_GERAIS+"=1"%>';
			document.form.submit();
		}
	}

	function excluirProcurador()
	{
		document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+Form.PARAMETRO_EXCLUIR_PROCURADOR+"=1"%>';
		document.form.submit();
	}
	
	function habilitarCampoEstadoCivil()
	{
		 valor = document.form.<%=Form.CAMPO_SELECT_ESTADO_CIVIL%>.value;			
		tipo = buscarTipoNavegador();
		
		if(valor==<%=DomnEstadoCivil.CASADO%>)
		{
			document.getElementById('<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>').disabled = false;
			document.getElementById('cpf_meeiro').style.display = tipo;
			if(document.getElementById('<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>').value == "")
			{
				selecionaRegimeCasamento("");
			}			
			document.getElementById('idTableMeeiro').style.display = tipo;
			document.getElementById('tituloMeeiro').style.display = tipo;
			document.getElementById('selectRegimeCasamento').style.display = tipo;
		}
		else if(valor==<%=DomnEstadoCivil.CONVIVENTE%>)
		{
			document.getElementById('<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>').disabled = true;
			selecionaRegimeCasamento(<%=DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS%>);
			document.getElementById('cpf_meeiro').style.display = tipo;
			document.getElementById('idTableMeeiro').style.display = tipo;
			document.getElementById('tituloMeeiro').style.display = tipo;		
			document.getElementById('selectRegimeCasamento').style.display = tipo;
		}
		else 
		{
			document.form.<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>.value = "";
			document.getElementById('<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>').disabled = true;
			document.getElementById('cpf_meeiro').style.display = 'none';
			document.getElementById('idTableMeeiro').style.display = 'none';
			document.getElementById('tituloMeeiro').style.display = 'none';					
			document.getElementById('selectRegimeCasamento').style.display = 'none';					
			selecionaRegimeCasamento("");
		}
		habilitaCampoOutrosHerdeiros();		
	}
	
   function habilitaCamposTipoProcesso(){
      navegador = buscarTipoNavegador();
		valor = document.form.<%=Form.CAMPO_SELECT_TIPO_PROCESSO_INVENTARIO%>.value;
		if( valor == <%=DomnTipoProcessoInventario.PROCESO_JUDICIAL%>)
		{
			document.getElementById('trDataIventarioArrolamento').style.display = navegador;
			document.getElementById('trNumeroProcesso').style.display = navegador;
         document.getElementById('trJuizoComarca').style.display = navegador;
		}
		else if( valor == <%=DomnTipoProcessoInventario.PROCESO_ADMINISTRATIVO%>)
		{
			document.getElementById('trDataIventarioArrolamento').style.display = 'none';
			document.getElementById('trNumeroProcesso').style.display = 'none';
         document.getElementById('trJuizoComarca').style.display = 'none';
		}
   }
   
	function habilitaSelectHerdeirosAscendentes()
	{
		navegador = buscarTipoNavegador();
		valor = document.form.<%=Form.CAMPO_SELECT_HERDEIROS_DESCENDENTES%>.value;
		if( valor == <%=DomnSimNao.SIM%>)
		{
			document.getElementById('quantidadeHerdeirosDescendentes').style.display = navegador;
			document.getElementById('selectHerdeirosAscendentes').style.display = 'none';			
			document.form.<%=Form.CAMPO_SELECT_HERDEIROS_ASCENDENTES%>.selectedIndex='0';
			habilitaCampoOutrosHerdeiros();
		}
		else if( valor == <%=DomnSimNao.NAO%>)
		{
			document.getElementById('quantidadeHerdeirosDescendentes').style.display = 'none';
			document.getElementById('selectHerdeirosAscendentes').style.display = navegador;
		}
		else
		{
			document.getElementById('quantidadeHerdeirosDescendentes').style.display = 'none';			
			document.getElementById('selectHerdeirosAscendentes').style.display = 'none';						
			document.form.<%=Form.CAMPO_SELECT_HERDEIROS_ASCENDENTES%>.selectedIndex='0';
			habilitaCampoOutrosHerdeiros();			
		}
	}
	
	function habilitaCampoOutrosHerdeiros()
	{
		navegador = buscarTipoNavegador();
		valor = document.form.<%=Form.CAMPO_SELECT_HERDEIROS_ASCENDENTES%>.value;
		if( valor == <%=DomnSimNao.SIM%>)
		{
			document.getElementById('quantidadeHerdeirosAscendentes').style.display = navegador;
			document.getElementById('quantidadeOutrosHerdeiros').style.display = 'none';						
		}
		else if( valor == <%=DomnSimNao.NAO%>)
		{
			document.getElementById('quantidadeHerdeirosAscendentes').style.display = 'none';
			if((document.form.<%=Form.CAMPO_SELECT_ESTADO_CIVIL%>.value != "<%=DomnEstadoCivil.CASADO%>") && (document.form.<%=Form.CAMPO_SELECT_ESTADO_CIVIL%>.value != "<%=DomnEstadoCivil.CONVIVENTE%>"))
			{
				document.getElementById('quantidadeOutrosHerdeiros').style.display = navegador;						
			}
			else
			{
				document.getElementById('quantidadeOutrosHerdeiros').style.display = 'none';									
			}
		}
		else
		{
			document.getElementById('quantidadeHerdeirosAscendentes').style.display = 'none';			
			document.getElementById('quantidadeOutrosHerdeiros').style.display = 'none';						
		}
	}
	
	function habilitaCampoRenuncia()
	{
		navegador = buscarTipoNavegador();
		valor = document.form.<%=Form.CAMPO_SELECT_RENUNCIA%>.value;
		if(valor == <%=DomnSimNao.SIM%>)
		{
			document.getElementById('selectTipoRenuncia').style.display = navegador;
		}
		else if(valor == <%=DomnSimNao.NAO%>)
		{
			document.form.<%=Form.CAMPO_SELECT_TIPO_RENUNCIA%>.selectedIndex = 0;				
			document.getElementById('selectTipoRenuncia').style.display = 'none';	
		}
		else
		{
			document.form.<%=Form.CAMPO_SELECT_TIPO_RENUNCIA%>.selectedIndex = 0;
			document.getElementById('selectTipoRenuncia').style.display = 'none';				
		}	
	}

	function localizar(campo)
	{
		if(campo == 'cpf')
		{
			if(navigator.appName != 'Microsoft Internet Explorer')
				document.getElementById('tblInventariante').style.display = 'table';
			else
				document.getElementById('tblInventariante').style.display = 'block';
		}
		else if(campo == 'cpf_deCujus')
		{
			if(navigator.appName != 'Microsoft Internet Explorer')
				document.getElementById('tbldeCujus').style.display = 'table';
			else
				document.getElementById('tbldeCujus').style.display = 'block';
		}
		else if(campo == 'cpf_meeiro')
		{
			if(navigator.appName != 'Microsoft Internet Explorer')
				document.getElementById('tblMeeiro').style.display = 'table';
			else
				document.getElementById('tblMeeiro').style.display = 'block';
		}
		else if(campo == 'cpf_procuradorVo')
		{
			if(navigator.appName != 'Microsoft Internet Explorer')
				document.getElementById('tblProcurador').style.display = 'table';
			else
				document.getElementById('tblProcurador').style.display = 'block';
		}
	}
	
	function excluirMeeiro()
	{
		document.getElementById('tblMeeiro').style.display = 'none';
	}
	
	function desabilitarCampos(valor)
	{
		tipo = buscarTipoNavegador();
		if(valor!=0)
		{
			if(valor==<%=DomnEstadoCivil.CASADO%>)
			{
				document.getElementById('<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>').disabled = false;
				document.getElementById('cpf_meeiro').style.display = tipo;
				document.getElementById('tituloMeeiro').style.display = tipo;
			}
			else if(valor==<%=DomnEstadoCivil.CONVIVENTE%>)
			{
				document.getElementById('<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>').disabled = true;
				document.getElementById('cpf_meeiro').style.display = tipo;
				document.getElementById('tituloMeeiro').style.display = tipo;			
			}
			else if(valor==<%=DomnEstadoCivil.DIVORCIADO%> || valor==<%=DomnEstadoCivil.SEPARADO%> || valor==<%=DomnEstadoCivil.SOLTEIRO%> || valor==<%=DomnEstadoCivil.VIUVO%>)
			{
				document.getElementById('<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>').disabled = true;
				document.getElementById('cpf_meeiro').style.display = 'none';
				document.getElementById('tituloMeeiro').style.display = 'none';			
			}
		}
	}
	
	function selecionaRenuncia()
	{
		valor = document.form.<%=Form.CAMPO_SELECT_TIPO_RENUNCIA%>.value;
		if(valor == <%=DomnTipoRenuncia.ABDICATIVA%>)
		{
			alert('<%=MensagemErro.INFORME_QUANTIDADE_CORRETA_DE_HERDEIROS%>');
		}
		else if(valor == <%=DomnTipoRenuncia.TRANSLATIVA%>)
		{
			alert('<%=MensagemErro.MENSAGEM_RENUNCIA_TRANSLATIVA%>');
		}		
	}
	
	function mudaTipoDocumentoConsulta()
	{
				var navegador = buscarTipoNavegador();
				comboOpcao = document.form.<%=Form.CAMPO_SELECT_POSSUI_CPF%>.value;
				if(comboOpcao == <%=DomnSimNao.SIM%>)
				{
					document.getElementById('deCujusComCPF').style.display = navegador;
					document.getElementById('deCujusSemCPF').style.display='none';
				}
				else if(comboOpcao == <%=DomnSimNao.NAO%>)
				{
					document.getElementById('deCujusComCPF').style.display = 'none';
					document.getElementById('deCujusSemCPF').style.display=navegador;
				}	
	}
	
	function validaFormulario()
	{ 
		<c:if test="${empty giaITCDVo.responsavelVo.numrDocumento}">
			alert(<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_INVENTARIANTE+"'"%>);
			return false;
		</c:if>
		<c:if test="${empty giaITCDVo.pessoaDeCujus.numrDocumento}">
			if(document.form.<%=Form.CAMPO_SELECT_POSSUI_CPF%>.value == "<%=DomnSimNao.SIM%>" )
			{
				alert(<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_DE_CUJUS+"'"%>);
			}
			else
			{
				alert(<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_DE_CUJUS_DOCUMENTO+"'"%>);
			}
			return false;
		</c:if>
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_ESTADO_CIVIL%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_ESTADO_CIVIL+"'"%>))
		{
			return false;
		}
		if(document.form.<%=Form.CAMPO_SELECT_ESTADO_CIVIL%>.value == "<%=DomnEstadoCivil.CASADO%>")
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_REGIME_CASAMENTO+"'"%>))
			{
				return false;
			}		
		}
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_FALECIMENTO%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_FALECIMENTO+"'"%>))
		{
			return false;
		}
      <c:if test="${ isIncluir or giaITCDVo.usuarioLogado == 0}">
         if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_PROCESSO_INVENTARIO%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_TIPO_PROCESSO+"'"%>))
         {
            return false;
         }
      </c:if>
      if(document.form.<%=Form.CAMPO_SELECT_TIPO_PROCESSO_INVENTARIO%>.value == <%=DomnTipoProcessoInventario.PROCESO_JUDICIAL%>)
		{
         if(!verificaCamposW3c(document.form.<%=Form.CAMPO_DATA_INVENTARIO_ARROLAMENTO%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_INVENTARIO+"'"%>))
         {
            return false;
         }
         if(!verificaCamposW3c(document.form.<%=Form.CAMPO_NUMERO_PROCESSO_INVENTARIO_ARROLAMENTO%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_NUMERO_PROCESSO+"'"%>))
         {
            return false;
         }
         if(!verificaCamposW3c(document.form.<%=Form.CAMPO_JUIZO_COMARCA%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_JUIZO_COMARCA+"'"%>))
         {
            return false;
         }
      }
      
      if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_UF_ABERTURA_INVENTARIO_ARROLAMENTO%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_UF_ABERTURA+"'"%>))
		{
			return false;
		}
      
      
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_RENUNCIA%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_RENUNCIA+"'"%>))
		{
			return false;
		}		
		if(document.form.<%=Form.CAMPO_SELECT_RENUNCIA%>.value == <%=DomnSimNao.SIM%>)
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_TIPO_RENUNCIA%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_TIPO_RENUNCIA+"'"%>))
			{
				return false;
			}
		}
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_HERDEIROS_DESCENDENTES%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_HERDEIROS_DESCENDENTES+"'"%>))
		{
			return false;
		}
		if(document.form.<%=Form.CAMPO_SELECT_HERDEIROS_DESCENDENTES%>.value == <%=DomnSimNao.SIM%>)
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES+"'"%>))
			{
				return false;
			}
			if(trimW3c(document.form.<%=Form.CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES%>.value) <= "0")
			{
				alert(<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES_MAIOR_QUE_ZERO+"'"%>);
				return false;
			}			
		}
		else
		{
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_HERDEIROS_ASCENDENTES%>, <%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_HERDEIROS_ASCENDENTES+"'"%>))
			{
				return false;
			}
			if(document.form.<%=Form.CAMPO_SELECT_HERDEIROS_ASCENDENTES%>.value == <%=DomnSimNao.SIM%>)
			{
				if(!verificaCamposW3c(document.form.<%=Form.CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES+"'"%>))
				{
					return false;
				}				
				if(trimW3c(document.form.<%=Form.CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES%>.value) <= "0")
				{
					alert(<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES_MAIOR_QUE_ZERO+"'"%>);
					return false;
				}
				if(trimW3c(document.form.<%=Form.CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES%>.value) > "2")
				{
					alert(<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES_MAIOR_QUE_DOIS+"'"%>);
					return false;
				}								
			}
			else
			{
				if((document.form.<%=Form.CAMPO_SELECT_ESTADO_CIVIL%>.value != "<%=DomnEstadoCivil.CASADO%>") && (document.form.<%=Form.CAMPO_SELECT_ESTADO_CIVIL%>.value != "<%=DomnEstadoCivil.CONVIVENTE%>"))
				{
					if(!verificaCamposW3c(document.form.<%=Form.CAMPO_QUANTIDADE_OUTROS_HERDEIROS%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_OUTROS_HERDEIROS+"'"%>))
					{
						return false;
					}
					if(trimW3c(document.form.<%=Form.CAMPO_QUANTIDADE_OUTROS_HERDEIROS%>.value) <= "0")
					{
						alert(<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_OUTROS_HERDEIROS_MAIOR_QUE_ZERO+"'"%>);
						return false;
					}						
				}
			}
		}
		if((document.form.<%=Form.CAMPO_SELECT_ESTADO_CIVIL%>.value == "<%=DomnEstadoCivil.CASADO%>")||(document.form.<%=Form.CAMPO_SELECT_ESTADO_CIVIL%>.value == "<%=DomnEstadoCivil.CONVIVENTE%>"))
		{
			<c:if test="${empty giaITCDVo.pessoaMeeiro.numrDocumento}">
				alert(<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_MEEIRO_BRANCO+"'"%>);
				return false;
			</c:if>
		}
      
      
		if(!verificaCamposW3c(document.form.<%=Form.CAMPO_SELECT_NATUREZA_OPERACAO%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_NATUREZA_OPERACAO+"'"%>))
		{
			return false;
		}
		<c:if test="${giaITCDVo.usuarioServidor}">
			if(!verificaCamposW3c(document.form.<%=Form.CAMPO_JUSTIFICATIVA_ALTERACAO%>,<%="'"+MensagemErro.VALIDAR_GIA_ITCD_CAMPO_JUSTIFICATIVA_ALTERACAO+"'"%>))
			{
				return false;
			}
		</c:if>
		return true;
	}
</script>
<c:set var="giaInventarioArrolamento" value="true" scope="request"/>
<form method="POST" name="form" action="">
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr class="SEFAZ-TR-Titulo">
			<td colspan="2">Dados do Inventariante</td>
		</tr>


			<tr>
				<td colspan="2">	
					<c:choose>
						<c:when test="${empty giaITCDVo.responsavelVo.numrDocumento}">
							<c:set var="naoOculta" value='true' scope="request"/>													
						</c:when>
						<c:otherwise>
							<c:set var="naoOculta" value='false' scope="request"/>																				
						</c:otherwise>
					</c:choose>
					<c:set var="larguraRotuloPadrao" value="298" scope="request"/>
					<c:set var="nomeTdLocalizarContribuinte" value="CPF Inventariante" scope="request"/>
					<c:set var="origemPesquisaContribuinte" scope="request"><%=DomnTipoContribuinte.DECLARANTE%></c:set>
					<c:set var="campoObrigatorio" scope="request" value="true"/>
					<c:choose>
						<c:when test="${not empty exceptionCadastro && giaITCDVo.origem == origemPesquisaContribuinte}">
							<c:set var="ocultaLinkCadastrar" value="false" scope="request"/>
						</c:when>
						<c:otherwise>
							<c:set var="ocultaLinkCadastrar" value="true" scope="request"/>					
						</c:otherwise>
					</c:choose>				
					<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteIncludeNova.jsp"/>
					<c:remove var="naoOculta" scope="request"/>
					<c:remove var="nomeTdLocalizarContribuinte" scope="request"/>
					<c:remove var="larguraRotuloPadrao" scope="request"/>
					<c:remove var="origemPesquisaContribuinte" scope="request"/>
					<c:remove var="ocultaLinkCadastrar" scope="request"/>
					<c:remove var="campoObrigatorio" scope="request"/>
				</td>
			</tr>		


		<tr>
			<td colspan="2">
			<c:if test="${not empty giaITCDVo.responsavelVo.numrDocumento}">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center" id="tblInventariante" > 
            <c:if test="${giaITCDVo.codigo > 0}">
                  <tr>
                     <td align="left" class="SEFAZ-TD-RotuloSaida">&nbsp; </td>
                     <td align="left" class="SEFAZ-TD-CampoSaida">&nbsp; </td>
                     <td align="left" class="SEFAZ-TD-RotuloSaida">Nº da GIA:&nbsp;</td>
                     <td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.codigo}"/></td>
                  </tr>
            </c:if>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Inventariante:&nbsp; </td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.nomeContribuinte}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDocumento}"></c:out></td>
					 </tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.endereco}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">N&uacute;mero:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoNumero}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Complemento:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoComplemento}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Refer&ecirc;ncia:&nbsp; </td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.pontoReferencia}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoBairro}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.enderecoCEP}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.municipio}"/></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.siglaUF}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrDdd}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.numrTelefone}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">E-mail:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.responsavelVo.email}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida">&nbsp;</td>
					</tr>					
				</table>	
				</c:if>
			</td>
		</tr>
		<tr class="SEFAZ-TR-Titulo">
			<td colspan="2">Dados de Cujus</td>
		</tr>
		<tr>
			<td colspan="2" class="SEFAZ-TD-ExibicaoPar">
				Para de cujus que não possui CPF, favor se dirigir a AGENFA mais próxima para efetuar o cadastro antes de incluir a GIA-ITCD.		
			</td>
		</tr>		


			<tr>
			  <td class="SEFAZ-TD-RotuloEntrada">De Cujus Possui CPF?&nbsp;</td>
				<td class="SEFAZ-TD-ComboBox" colspan="2">
					<abaco:campoSelectDominio ajuda="Selecione se De Cujus possui ou não CPF" classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" name="<%=Form.CAMPO_SELECT_POSSUI_CPF%>" tabIndex="" mostrarSelecione="false" opcaoSelecionada="${giaITCDVo.possuiCPF.valorCorrente}" onChange="mudaTipoDocumentoConsulta()" idCampo="<%=Form.CAMPO_SELECT_POSSUI_CPF%>"/><font color="red">*</font>
				</td>
			</tr>
			<tr id="deCujusComCPF" style='<c:if test="${isDeCujusSemCPF}">display:none</c:if>'>
				<td colspan="2">	
					<c:choose>
						<c:when test="${empty giaITCDVo.pessoaDeCujus.numrDocumento}">
							<c:set var="naoOculta" value='true' scope="request"/>													
						</c:when>
						<c:otherwise>
							<c:set var="naoOculta" value='false' scope="request"/>																				
						</c:otherwise>
					</c:choose>
					<c:set var="larguraRotuloPadrao" value="298" scope="request"/>
					<c:set var="nomeTdLocalizarContribuinte" value="CPF De Cujus" scope="request"/>
					<c:set var="origemPesquisaContribuinte" scope="request"><%=DomnTipoContribuinte.DE_CUJUS%></c:set>
					<c:set var="campoObrigatorio" scope="request" value="true"/>
					<c:choose>
						<c:when test="${not empty exceptionCadastro && giaITCDVo.origem == origemPesquisaContribuinte}">
							<c:set var="ocultaLinkCadastrar" value="false" scope="request"/>
						</c:when>
						<c:otherwise>
							<c:set var="ocultaLinkCadastrar" value="true" scope="request"/>					
						</c:otherwise>
					</c:choose>				
					<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteIncludeNova.jsp"/>
					<c:remove var="naoOculta" scope="request"/>
					<c:remove var="nomeTdLocalizarContribuinte" scope="request"/>
					<c:remove var="larguraRotuloPadrao" scope="request"/>
					<c:remove var="origemPesquisaContribuinte" scope="request"/>
					<c:remove var="ocultaLinkCadastrar" scope="request"/>
					<c:remove var="campoObrigatorio" scope="request"/>
				</td>
			</tr>		
			<tr id="deCujusSemCPF" style='<c:if test="${not isDeCujusSemCPF}">display:none</c:if>'>
				<td colspan="2">	
					<c:choose>
						<c:when test="${empty giaITCDVo.pessoaDeCujus.numrDocumento}">
							<c:set var="naoOculta" value='true' scope="request"/>													
						</c:when>
						<c:otherwise>
							<c:set var="naoOculta" value='false' scope="request"/>																				
						</c:otherwise>
					</c:choose>
					<c:set var="larguraRotuloPadrao" value="298" scope="request"/>
					<c:set var="nomeTdLocalizarContribuinte" value="Documento De Cujus" scope="request"/>
					<c:set var="origemPesquisaContribuinte" scope="request"><%=DomnTipoContribuinte.DE_CUJUS%></c:set>
					<c:set var="campoObrigatorio" scope="request" value="true"/>
					<c:choose>
						<c:when test="${not empty exceptionCadastro && giaITCDVo.origem == origemPesquisaContribuinte}">
							<c:set var="ocultaLinkCadastrar" value="false" scope="request"/>
						</c:when>
						<c:otherwise>
							<c:set var="ocultaLinkCadastrar" value="true" scope="request"/>					
						</c:otherwise>
					</c:choose>				
					<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteTipoDocumentoIncludeNova.jsp"/>
					<c:remove var="naoOculta" scope="request"/>
					<c:remove var="nomeTdLocalizarContribuinte" scope="request"/>
					<c:remove var="larguraRotuloPadrao" scope="request"/>
					<c:remove var="origemPesquisaContribuinte" scope="request"/>
					<c:remove var="ocultaLinkCadastrar" scope="request"/>
					<c:remove var="campoObrigatorio" scope="request"/>
				</td>
			</tr>				
		

		<tr>
			<td colspan="2">
			<c:if test="${not empty giaITCDVo.pessoaDeCujus.numrDocumento}">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center" id="tbldeCujus"> 
					<tr class="SEFAZ-TR-SubTitulo">
						<td width="25%">Número do documento</td>
						<td align="left">Nome</td>
					</tr>
					<tr class="SEFAZ-TR-ExibicaoPar">
						<td><c:out value="${giaITCDVo.pessoaDeCujus.numrDocumento}"></c:out></td>
						<td align="left"><c:out value="${giaITCDVo.pessoaDeCujus.nomeContribuinte}"></c:out></td>
					</tr>
				</table>
			</c:if>
			</td>
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloEntrada">Estado Civil:&nbsp;</td>
			<td class="SEFAZ-TD-ComboBox" colspan="2">
				<abaco:campoSelectDominio 
					ajuda="" 
					classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnEstadoCivil" 
					name="<%=Form.CAMPO_SELECT_ESTADO_CIVIL%>" 
					tabIndex="" 
					idCampo="<%=Form.CAMPO_SELECT_ESTADO_CIVIL%>" 
					mostrarSelecione="true" naoMostrar="<%=DomnEstadoCivil.CONVIVENTE%>"
					onChange="habilitarCampoEstadoCivil()" 
					opcaoSelecionada="${giaITCDVo.estadoCivilDeCujus.valorCorrente}">
				</abaco:campoSelectDominio><font color="red">*</font>
			</td>
		</tr>
		<tr id="selectRegimeCasamento" style="display:none">
			<td class="SEFAZ-TD-RotuloEntrada">Regime de Casamento:&nbsp;</td>
			<td class="SEFAZ-TD-ComboBox" colspan="2">
				<abaco:campoSelectDominio 
					ajuda="" 
					classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento" 
					name="<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>" 
					tabIndex="" 
					idCampo="<%=Form.CAMPO_SELECT_REGIME_CASAMENTO%>" 
					mostrarSelecione="true" 
					disabled="disabled" 
					opcaoSelecionada="${giaITCDVo.regimeCasamento.valorCorrente}">
				</abaco:campoSelectDominio><font color="red">*</font>
			</td>
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloEntrada" width="298">Data Falecimento:&nbsp;</td>
			<td width="442" class="SEFAZ-TD-CampoEntrada">
				<input name="<%=Form.CAMPO_DATA_FALECIMENTO%>" type="text" class="SEFAZ-INPUT-Text" id="<%=Form.CAMPO_DATA_FALECIMENTO%>" maxlength="10" size="12" value="<c:out value="${giaITCDVo.dataFalecimentoFormatada}"></c:out>" onBlur="formataDataW3c(this);"></input><font color="red">*</font>
			</td>
		</tr>
		<tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="298">UF de Abertura Inv./Arrolamento:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada">
						  <select  name="<%=Form.CAMPO_SELECT_UF_ABERTURA_INVENTARIO_ARROLAMENTO%>">  
                          <option value="" selected="selected"><%=Form.SELECIONE%></option>
                          <c:forEach var="uf" items="${giaITCDVo.ufIntegracaoVO.collVO}">  
                          <!--  <option  value="<c:out  value='${uf.siglUf}'/>" > <c:out  value="${uf.siglUf}"/></option>  
                           <option  value="<c:out  value='${uf.siglUf}'/>" > <c:out  value="${uf.siglUf}"/></option>-->
                              <option value="<c:out value="${uf.siglUf}"></c:out>"
                                 <c:if test="${uf.siglUf eq giaITCDVo.ufAbertura.siglUf}">selected</c:if>  > <c:out value="${uf.siglUf}"></c:out>                           
                              </option>
                          </c:forEach> 
                  </select><font color="red">*</font>		       
				</td>                                                                
		</tr>	
  <!--
		<tr>
			<td class="SEFAZ-TD-RotuloSaida" width="298">UF de Abertura Inv./Arrolamento:&nbsp;</td>
			<td width="442" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.ufAbertura.siglUf}"/></td>
		</tr>
      -->
      
      	
      
		<tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="298">Tem Ren&uacute;ncia:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada">
					<abaco:campoSelectDominio ajuda="Selecione se houve ou não renuncia." classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" name="<%=Form.CAMPO_SELECT_RENUNCIA%>" tabIndex="" mostrarSelecione="true" opcaoSelecionada="${giaITCDVo.renuncia.valorCorrente}" onChange="habilitaCampoRenuncia();"/><font color="red">*</font>				
				</td>
		</tr>		
		<tr id='selectTipoRenuncia' style="display:none">
				<td class="SEFAZ-TD-RotuloEntrada" width="298">Selecione tipo de ren&uacute;ncia:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada">
					<abaco:campoSelectDominio ajuda="Selecione o tipo de renuncia." classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoRenuncia" name="<%=Form.CAMPO_SELECT_TIPO_RENUNCIA%>" tabIndex="" mostrarSelecione="true" opcaoSelecionada="${giaITCDVo.tipoRenuncia.valorCorrente}" onChange="selecionaRenuncia();"/><font color="red">*</font>
				</td>
		</tr>
		<tr>
				<td class="SEFAZ-TD-RotuloEntrada" width="298">Tem herdeiros descendentes:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada">
					<abaco:campoSelectDominio ajuda="Selecione se De Cujus possui herdeiros descendentes." classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" name="<%=Form.CAMPO_SELECT_HERDEIROS_DESCENDENTES%>" tabIndex="" mostrarSelecione="true" opcaoSelecionada="${giaITCDVo.herdeirosDescendentes.valorCorrente}" onChange="habilitaSelectHerdeirosAscendentes();"/><font color="red">*</font>				
				</td>
		</tr>		
		<tr id="selectHerdeirosAscendentes" style="display:none">
				<td class="SEFAZ-TD-RotuloEntrada" width="298">Tem herdeiros ascendentes:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada">
					<abaco:campoSelectDominio ajuda="Selecione se De Cujus possui herdeiros descendentes." classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnSimNao" name="<%=Form.CAMPO_SELECT_HERDEIROS_ASCENDENTES%>" tabIndex="" mostrarSelecione="true" opcaoSelecionada="${giaITCDVo.herdeirosAscendentes.valorCorrente}" onChange="habilitaCampoOutrosHerdeiros();"/><font color="red">*</font>				
				</td>
		</tr>		
		<tr id="quantidadeHerdeirosDescendentes" style="display:none">
			<td class="SEFAZ-TD-RotuloEntrada" width="298">Quantidade de herdeiros descendentes:&nbsp;</td>
			<td width="442" class="SEFAZ-TD-CampoEntrada">
				<abaco:campoApenasNumero maxlength="5" name="<%=Form.CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES%>" size="5" value="${giaITCDVo.numeroHerdeirosDescendentesFormatado}"></abaco:campoApenasNumero><font color="red">*</font>
			</td>
		</tr>
		<tr id="quantidadeHerdeirosAscendentes" style="display:none">
			<td class="SEFAZ-TD-RotuloEntrada" width="298">Quantidade de herdeiros ascendentes:&nbsp;</td>
			<td width="442" class="SEFAZ-TD-CampoEntrada">
				<abaco:campoApenasNumero maxlength="5" name="<%=Form.CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES%>" size="5" value="${giaITCDVo.numeroHerdeirosAscendentesFormatado}"></abaco:campoApenasNumero><font color="red">*</font>
			</td>
		</tr>
		<tr id="quantidadeOutrosHerdeiros" style="display:none">
			<td class="SEFAZ-TD-RotuloEntrada" width="298">Quantidade de outros herdeiros:&nbsp;</td>
			<td width="442" class="SEFAZ-TD-CampoEntrada">
				<abaco:campoApenasNumero maxlength="5" name="<%=Form.CAMPO_QUANTIDADE_OUTROS_HERDEIROS%>" size="5" value="${giaITCDVo.numeroOutrosHerdeirosFormatado}"></abaco:campoApenasNumero><font color="red">*</font>
			</td>
		</tr>
            <c:choose>
               <c:when test="${ !isIncluir and giaITCDVo.tipoProcessoInventario.valorCorrente < 0 and giaITCDVo.usuarioLogado > 0 }">
                  <div style="display:none;">
                     <abaco:campoSelectDominio 
                        ajuda=""
                        disabled="true"
                        classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoInventario" 
                        name="<%=Form.CAMPO_SELECT_TIPO_PROCESSO_INVENTARIO%>" 
                        tabIndex="" 
                        idCampo="<%=Form.CAMPO_SELECT_TIPO_PROCESSO_INVENTARIO%>" 
                        mostrarSelecione="true"
                        onChange="habilitaCamposTipoProcesso()" 
                        opcaoSelecionada="${giaITCDVo.tipoProcessoInventario.valorCorrente}">
                     </abaco:campoSelectDominio><font color="red">*</font>
                  </div>
               </c:when>
               <c:otherwise>
               <tr>
                     <td class="SEFAZ-TD-RotuloEntrada">Tipo Processo:&nbsp;</td>
                     <td class="SEFAZ-TD-ComboBox" colspan="2">
                        <abaco:campoSelectDominio 
                           ajuda=""
                           classeDominio="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoInventario" 
                           name="<%=Form.CAMPO_SELECT_TIPO_PROCESSO_INVENTARIO%>" 
                           tabIndex="" 
                           idCampo="<%=Form.CAMPO_SELECT_TIPO_PROCESSO_INVENTARIO%>" 
                           mostrarSelecione="true"
                           onChange="habilitaCamposTipoProcesso()" 
                           opcaoSelecionada="${giaITCDVo.tipoProcessoInventario.valorCorrente}">
                        </abaco:campoSelectDominio><font color="red">*</font>
                        </td>
                  </tr>
             </c:otherwise>
            </c:choose>

      
      <tr id="trDataIventarioArrolamento">
			<td class="SEFAZ-TD-RotuloEntrada" width="298">Data Inv./Arrolamento:&nbsp;</td>
			<td width="442" class="SEFAZ-TD-CampoEntrada">
				<input  type="text" name="<%=Form.CAMPO_DATA_INVENTARIO_ARROLAMENTO%>" class="SEFAZ-INPUT-Text"  size="12" maxlength="10" value="<c:out value="${giaITCDVo.dataInventarioFormatada}"></c:out>" onBlur="formataDataW3c(this);"></input><font color="red">*</font>
			</td>
		</tr>
      
		<tr id="trNumeroProcesso">
			<td class="SEFAZ-TD-RotuloEntrada" width="298">Número Processo Inv./Arrolamento/Cartório:&nbsp;</td>
			<td width="442" class="SEFAZ-TD-CampoEntrada">
				<abaco:campoApenasNumero maxlength="10" name="<%=Form.CAMPO_NUMERO_PROCESSO_INVENTARIO_ARROLAMENTO%>" size="10" value="${giaITCDVo.numeroProcessoFormatado}"></abaco:campoApenasNumero><font color="red">*</font>
			</td>
		</tr>
      
		<tr id="trJuizoComarca">
			<td class="SEFAZ-TD-RotuloEntrada" width="298">Juízo/Comarca:&nbsp;</td>
			<td width="442" class="SEFAZ-TD-CampoEntrada">
				<input type="text" name="<%=Form.CAMPO_JUIZO_COMARCA%>" class="SEFAZ-INPUT-Text" id="<%=Form.CAMPO_JUIZO_COMARCA%>" size="50" maxlength="50" value="<c:out value="${giaITCDVo.descricaoJuizoComarca}"></c:out>" onblur="toUpperCaseW3c(this);"></input><font color="red">*</font>
			</td>
		</tr>		
		<tr id="tituloMeeiro" style="display:none">
			<td colspan="2" class="SEFAZ-TR-Titulo" >Dados Cônjuge Sobrevivente (Meeiro/Outro)</td>
		</tr>	
		<tr id="cpf_meeiro" style="display:none">
			<td colspan="2">	
				
					<c:choose>
						<c:when test="${empty giaITCDVo.pessoaMeeiro.numrDocumento}">
							<c:set var="naoOculta" value='true' scope="request"/>													
						</c:when>
						<c:otherwise>
							<c:set var="naoOculta" value='false' scope="request"/>																				
						</c:otherwise>
					</c:choose>
					<c:set var="nomeTdLocalizarContribuinte" value="CPF Cônjuge Sobrevivente (Meeiro/Outro)" scope="request"/>			
					<c:set var="larguraRotuloPadrao" value="298" scope="request"/>
					<c:set var="origemPesquisaContribuinte" scope="request"><%=DomnTipoContribuinte.MEEIRO%></c:set>			
					<c:set var="campoObrigatorio" scope="request" value="true"/>
					<c:choose>
						<c:when test="${not empty exceptionCadastro && giaITCDVo.origem == origemPesquisaContribuinte}">
							<c:set var="ocultaLinkCadastrar" value="false" scope="request"/>
						</c:when>
						<c:otherwise>
							<c:set var="ocultaLinkCadastrar" value="true" scope="request"/>					
						</c:otherwise>
					</c:choose>								
					<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteIncludeNova.jsp"/>
					<c:remove var="naoOculta" scope="request"/>
					<c:remove var="nomeTdLocalizarContribuinte" scope="request"/>
					<c:remove var="larguraRotuloPadrao" scope="request"/>
					<c:remove var="origemPesquisaContribuinte" scope="request"/>
					<c:remove var="ocultaLinkCadastrar" scope="request"/>
					<c:remove var="campoObrigatorio" scope="request"/>			
				
			</td>
		</tr>		
		<tr id="idTableMeeiro">
			<td colspan="2">
			<c:if test="${not empty giaITCDVo.pessoaMeeiro.numrDocumento}">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center"> 
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Nome:&nbsp;</td>
							<td colspan="3" valign="middle" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.pessoaMeeiro.nomeContribuinte}"></c:out>	
						</td>
					  </tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
						 <td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.pessoaMeeiro.numrDocumento}"></c:out></td>
					</tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
						 <td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.pessoaMeeiro.endereco}"></c:out></td>
						 <td width="12%" class="SEFAZ-TD-RotuloSaida">Número:&nbsp;</td>
						 <td width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.pessoaMeeiro.enderecoNumero}"></c:out></td>
					</tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
						 <td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.pessoaMeeiro.enderecoBairro}"></c:out></td>
						 <td width="12%" class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
						 <td width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.pessoaMeeiro.municipio}"></c:out></td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
						 <td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.pessoaMeeiro.siglaUF}"></c:out></td>
					</tr>
				</table>		
			</c:if>
			</td>
		</tr>
		<c:if test="${not empty giaITCDVo.procuradorVo.numrDocumento or not giaITCDVo.usuarioServidor}">
			<tr class="SEFAZ-TR-Titulo">
				<td colspan="2">Dados Procurador</td>
			</tr>				
		</c:if>
		<c:if test="${not giaITCDVo.usuarioServidor}">
			<tr>
				<td colspan="2">	
					<c:choose>
						<c:when test="${empty giaITCDVo.procuradorVo.numrDocumento}">
							<c:set var="naoOculta" value='true' scope="request"/>													
						</c:when>
						<c:otherwise>
							<c:set var="naoOculta" value='false' scope="request"/>																				
						</c:otherwise>
					</c:choose>
					<c:set var="nomeTdLocalizarContribuinte" value="CPF Procurador" scope="request"/>
					<c:set var="larguraRotuloPadrao" value="298" scope="request"/>	
					<c:set var="origemPesquisaContribuinte" scope="request"><%=DomnTipoContribuinte.PROCURADOR%></c:set>
					<c:choose>
						<c:when test="${not empty exceptionCadastro && giaITCDVo.origem == origemPesquisaContribuinte}">
							<c:set var="ocultaLinkCadastrar" value="false" scope="request"/>
						</c:when>
						<c:otherwise>
							<c:set var="ocultaLinkCadastrar" value="true" scope="request"/>					
						</c:otherwise>
					</c:choose>								
					<jsp:include page="/util/integracao/cadastro/ViewPesquisarContribuinteIncludeNova.jsp"/>
					<c:remove var="naoOculta" scope="request"/>
					<c:remove var="nomeTdLocalizarContribuinte" scope="request"/>
					<c:remove var="larguraRotuloPadrao" scope="request"/>
					<c:remove var="origemPesquisaContribuinte" scope="request"/>
					<c:remove var="ocultaLinkCadastrar" scope="request"/>
				</td>
			</tr>						
		</c:if>
		<tr>
			<td colspan="2">
			<c:if test="${not empty giaITCDVo.procuradorVo.numrDocumento}">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" align="center" id="tblProcurador"> 
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Nome:&nbsp;</td>
						<td colspan="2" valign="middle" class="SEFAZ-TD-CampoSaida">
							<c:out value="${giaITCDVo.procuradorVo.nomeContribuinte}"></c:out>							
						</td>
						<c:choose>
							<c:when test="${not giaITCDVo.usuarioServidor}">
								<td class="SEFAZ-TD-RotuloSaida"><div align="right"><a href="javascript: excluirProcurador();">Excluir</a></div></td>							
							</c:when>
							<c:otherwise>
								<td class="SEFAZ-TDRotuloSaida">&nbsp;</td>
							</c:otherwise>
						</c:choose>
						
					</tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">CPF:&nbsp;</td>
						 <td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.numrDocumento}"></c:out></td>
					</tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Logradouro:&nbsp;</td>
						 <td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.endereco}"></c:out></td>
						 <td width="12%" class="SEFAZ-TD-RotuloSaida">Número:&nbsp;</td>
						 <td width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoNumero}"></c:out></td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloSaida">Complemento:&nbsp;</td>
						 <td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoComplemento}"></c:out></td>
					</tr>
					<tr>
						<td class="SEFAZ-TD-RotuloSaida">Ponto de Referência:&nbsp;</td>
						<td colspan="3" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.pontoReferencia}"></c:out></td>
					</tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Bairro:&nbsp;</td>
						<td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoBairro}"></c:out></td>
						<td width="12%" class="SEFAZ-TD-RotuloSaida">CEP:&nbsp;</td>
						<td width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.enderecoCEP}"></c:out></td>
					</tr>
					<tr>
						<td width="25%" class="SEFAZ-TD-RotuloSaida">Município:&nbsp;</td>
						 <td width="35%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.municipio}"></c:out></td>
						 <td width="12%" class="SEFAZ-TD-RotuloSaida">UF:&nbsp;</td>
						 <td width="28%" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.siglaUF}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">DDD:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.numrDdd}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">Telefone:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.numrTelefone}"></c:out></td>
					</tr>
					<tr>
						<td align="left" class="SEFAZ-TD-RotuloSaida">E-mail:&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida"><c:out value="${giaITCDVo.procuradorVo.email}"></c:out></td>
						<td align="left" class="SEFAZ-TD-RotuloSaida">&nbsp;</td>
						<td align="left" class="SEFAZ-TD-CampoSaida">&nbsp;</td>
					</tr>
				</table>	
			</c:if>
			</td>
		</tr>
		<tr class="SEFAZ-TR-Titulo">
			<td colspan="3">Natureza</td>
		</tr>
		<tr>
			<td class="SEFAZ-TD-RotuloEntrada">Natureza Operação:&nbsp;</td>
			<td class="SEFAZ-TD-ComboBox" colspan="2">
				<select name="<%=Form.CAMPO_SELECT_NATUREZA_OPERACAO%>" id="<%=Form.CAMPO_SELECT_NATUREZA_OPERACAO%>">
					<option value="" selected><%=Form.SELECIONE%></option>
					<c:forEach var="naturezaOperacaoVo" items="${giaITCDVo.naturezaOperacaoVo.collVO}">
						<option value="<c:out value="${naturezaOperacaoVo.codigo}"></c:out>"
                  <c:if test="${naturezaOperacaoVo.codigo == giaITCDVo.naturezaOperacaoVo.codigo}">
                  selected </c:if>><c:out value="${naturezaOperacaoVo.descricaoNaturezaOperacao}"></c:out></option>
					</c:forEach>
				</select><font color="red">*</font>
			</td>
		</tr>
		<c:if test="${giaITCDVo.usuarioServidor}">
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr>
				<td class="SEFAZ-TD-RotuloEntrada">Justificativa da Alteração:&nbsp;</td>
				<td class="SEFAZ-TD-CampoEntrada">
					<textarea cols="50" rows="4" class="SEFAZ-INPUT-TEXT" id="<%=Form.CAMPO_JUSTIFICATIVA_ALTERACAO%>" name="<%=Form.CAMPO_JUSTIFICATIVA_ALTERACAO%>"><c:out value="${giaITCDVo.justificativaAlteracao}"/></textarea><font color="red">*</font>
				</td>	
			</tr>	
		</c:if>
	</table>	
	
  <br>
  <c:if test="${isIncluir && giaITCDVo.codigo == 0}">
	  <jsp:include page="/util/seguranca/ViewMostrarImagemCaracterModAberto.jsp"/>  
	</c:if>
	
	<table cellspacing="0" cellpadding="0" border="0" width="740" align="center">
		<tr>
			<td align="center">
				<abaco:botaoCancelar/>
				<input type="button" value="<%=Form.TEXTO_BOTAO_SALVAR%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_CONFIRMAR_CLONE%>" id="btnConfirmar" onClick="salvarDadosGerais();"/>
				<input type="button" value="<%=Form.TEXTO_BOTAO_PROXIMO%>" class="SEFAZ-INPUT-Botao" name="<%=Form.BOTAO_PROXIMO_CLONE%>" onClick="solicitarAbaBensTributaveis();"/>
			</td>
		</tr>
		<tr>
			<td colspan="2"><font color="red"><b>* Campos Obrigatórios</b></font></td>
		</tr>		
	</table>
	<abaco:log/>
</form>
<c:remove var="giaInventarioArrolamento" scope="request"/>