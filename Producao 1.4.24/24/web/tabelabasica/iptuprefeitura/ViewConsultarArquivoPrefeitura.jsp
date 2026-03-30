<jsp:useBean id="sefazDataHora" class="sefaz.mt.util.SefazDataHora" scope="page" />
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>

<html>
    <head>
        <title><abaco:tituloSistema></abaco:tituloSistema></title>
        <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
        <META HTTP-EQUIV=Cache-Control content=no-store>
        <META HTTP-EQUIV=Pragma content=no-cache>
        <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
   
   <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
   <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
   <script type="text/javascript" language="javascript">
  
  function obterArquivo(codgArquivo) {            
                     
           
            document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?" +Form.CAMPO_HIDDEN_OBTER_ARQUIVO_PREFEITURA+"=3&"+Form.CAMPO_CONSULTAR_ARQUIVO_IPTU+"="%>'+codgArquivo;  
            document.form.submit();
            return true;
         }
   
   function solicitarConsultarArquivoIPTU() {        
        
         if(!verificaCamposW3c(document.form.<%=Form.CAMPO_MUNICIPIOS_ATIVOS%>,<%="'"+MensagemErro.VALIDAR_SELECIONAR_MUNICIPIO+"'"%>)){                   
                   return false;
              } 
            cleanMensagem();                   
            document.getElementById("tableDadosRelatorio").style.display = "none";
            document.getElementById("MensagemConsulta").style.visibility = "visible";
            document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?" +Form.BOTAO_PESQUISAR+"=2"%>';
            document.form.submit();
            return true;
         }
         
    function cleanMensagem() {
         var mensagem = document.getElementById("mensagemArquivo");
         if(mensagem != null)
         {
           document.getElementById("mensagemArquivo").innerHTML="";         
         }        
      
      }   
              
          
   </script>
   </head>

    <body class="SEFAZ-Body" onload="verificaErro()">
 <!-- padrao sefaz - cabeçalho e página de erro -->
<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
<form method="POST" action="" name="form">
<table cellspacing="1" cellpadding="0" border="0" width="726" align="center">
          <tr class="SEFAZ-TR-Titulo">
                 <td align="center">Consultar Arquivo Prefeitura</td>		
          </tr>  
      </table> 
      <br>
	<table cellspacing="1" cellpadding="0" border="0" width="740" align="center">
		<tr>
         <td class="SEFAZ-TD-RotuloEntrada" width="370"><div align="right">*Selecione o Municipio:</div> </td>
         <td class="SEFAZ-TD-CampoEntrada">          
              <abaco:campoSelectCollection mostrarSelecione="true" ajuda="Lista de Municipios Ativos" name="<%=Form.CAMPO_MUNICIPIOS_ATIVOS%>" nomeCollectionNoRequest="<%=Form.CAMPO_MUNICIPIOS_ATIVOS%>" nomeMetodoCodigo="getCodgMunicipio" nomeMetodoValor="getNomeMunicipio" tabIndex="" />
          <br>
           </td>
         </tr>	        
         
         <tr>
         
        <td colspan="2">
            <div align="center">		 
            <br>
               <input name="<%=Form.BOTAO_PESQUISAR%>" value="<%=Form.TEXTO_BOTAO_CONSULTAR%>" type="button" class="SEFAZ-INPUT-Botao" id="<%=Form.BOTAO_CONFIRMAR%>" onClick="solicitarConsultarArquivoIPTU();" />
                  <abaco:botaoRetornoMulti nomeBotao="  Cancelar  " />
               </div>          
      
       </tr>
       <tr>
        <td colspan="4"><h5><font color="red">* Campos Obrigatórios</font></h5></td>
      </tr>		
	</table>
    <div id="tableDadosRelatorio">
    <c:if test="${not empty importacaoIPTUVo.collVO && importacaoIPTUVo.collVO != null }">            
               <table width="950" align="center" border="0" cellspacing="0" cellpadding="0">       
                  <tr class="SEFAZ-TR-SubTitulo" > 
                     <td width="8%" >Código</td>
                     <td width="15%" >Agendando pelo Servidor</td>
                     <td width="8%" >Municipio</td>
                     <td width="35%" >Nome do Arquivo</td>                   
                     <td width="15%" >Status do Processamento</td>
                     <td width="10%" >Data do Upload</td>
                     <td width="10%" >Aqruivo Upload</td>
                  </tr>
                  <c:forEach var="importacaoIPTUVo" items="${importacaoIPTUVo.collVO}" >
                     <abaco:linhaTabela>
                        <td width="8%" ><c:out value="${importacaoIPTUVo.codigo}"></c:out> </td> 
                        <td width="15%" ><c:out value="${importacaoIPTUVo.servidorSefazIntegracaoVo.nomeServidor}"></c:out> </td> 
                        <td width="8%" ><c:out value="${importacaoIPTUVo.municipioVo.nomeMunicipio}"></c:out> </td> 
                        <td width="8%" ><c:out value="${importacaoIPTUVo.descNomeArquivoAntigo}"></c:out> </td> 
                        <td width="8%" ><c:out value="${importacaoIPTUVo.statusImportacao.textoCorrente}"></c:out> </td> 
                        <td width="8%" ><fmt:formatDate value="${importacaoIPTUVo.dataHoraUpload}" pattern="dd/MM/yyyy"/></td>                         
                        <td width="15%" > 
                          <c:if test="${importacaoIPTUVo.statusImportacao.valorCorrente == 1 || importacaoIPTUVo.statusImportacao.valorCorrente == 2 || importacaoIPTUVo.statusImportacao.valorCorrente == 4}">
                           Obter Arquivo Excel
                          </c:if>
                         <c:if test="${importacaoIPTUVo.statusImportacao.valorCorrente == 3}">
                            <a href="javascript:obterArquivo(<c:out value="${importacaoIPTUVo.codigo}"></c:out>)">Obter Arquivo Excel</a>
                          </c:if>
                           <input type="hidden" name="<%=Form.CAMPO_UPLOAD_ARQUIVO%>" >
                        </td>
                        
                     </abaco:linhaTabela>                                           
                  </c:forEach>                  
               </table>              
            </c:if>
          </div>
            <c:if test="${empty importacaoIPTUVo.collVO && importacaoIPTUVo.municipioVo.nomeMunicipio != null }">
             <table width="950" align="center" border="0" cellspacing="0" cellpadding="0">
             <tr align="center"> 
             <td><h5 id="mensagemArquivo"><font color="red">Não Existe Arquivo Processado para o Municipio <c:out value="${importacaoIPTUVo.municipioVo.nomeMunicipio}"></c:out></font></h5></td>
             </tr>
             </table>
            </c:if> 
            <div id ="MensagemConsulta" style="visibility:hidden"> 
            <table width="950" align="center" border="0" cellspacing="0" cellpadding="0">
             <tr align="center"> 
             <td><font color="Blue"><h5>Aguarde Consultando Arquivo Excel&nbsp;<img src="/imagens/wait16trans.gif"></h5></font></td>
             </tr>
            </table>
            </div>
     
      
</form>
</body>
<g:mostrarRodape/>
</html>

