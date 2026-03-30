<jsp:useBean id="sefazDataHora" class="sefaz.mt.util.SefazDataHora" scope="page" />
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu.Form"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.MensagemErro"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>

<html>
    <head>
        <title><abaco:tituloSistema></abaco:tituloSistema></title>
        <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
        <META HTTP-EQUIV=Cache-Control content=no-store>
        <META HTTP-EQUIV=Pragma content=no-cache>
        <link rel="StyleSheet" href="/estilos/SefazEstilos.css" type="text/css">
   
 
   <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/itcd.js"></script>
   <script type="text/javascript" language="javascript" src="/javascript/miscelanea/desabilitarBotoes.js"></script>
   <script type="text/javascript" language="javascript" src="/javascript/funcoesGenericas.js"></script>
   <script type="text/javascript" language="javascript">
  
   function obterArrayBotoes(){
			
      var botao1 = document.form.<%=Form.BOTAO_CONFIRMAR%>;
		return new Array(botao1);
	}
   
   function validarFormatoArquivo(){
      var tamanhoArquivo = 45;
      var arquivo = document.form.<%=Form.CAMPO_UPLOAD_ARQUIVO%>.value;
      //Função para validar o tamanmho do arquivo
      if(arquivo.length > tamanhoArquivo )
      {
        alert(<%="'"+MensagemErro.VALIDAR_TAMANHO_ARQUIVO+"'"%>);
        document.form.<%=Form.CAMPO_UPLOAD_ARQUIVO%>.value = "";
        return false;
      }
      var extensoes, ext, valido;
          extensoes = new Array('.xls');
          
      ext = arquivo.substring(arquivo.lastIndexOf(".")).toLowerCase();
      valido = false;
  
      for(var i = 0; i <= arquivo.length; i++){
          if(extensoes[i] == ext){
              valido = true;
              break;
          }
      }  
      if(valido){        
          return true;
      }
      else {
         alert(<%="'"+MensagemErro.VALIDAR_FORMATO_ARQUIVO+"'"%>);
         document.form.<%=Form.CAMPO_UPLOAD_ARQUIVO%>.value = "";
      }
    return false;
  }
   
   function solicitarProcessarArquivoIPTU() {        
        
         if(!verificaCamposW3c(document.form.<%=Form.CAMPO_MUNICIPIOS_ATIVOS%>,<%="'"+MensagemErro.VALIDAR_SELECIONAR_MUNICIPIO+"'"%>)){                   
                   return false;
              } 
          if(!verificaCamposW3c(document.form.<%=Form.CAMPO_UPLOAD_ARQUIVO%>,<%="'"+MensagemErro.VALIDAR_ARQUIVO_PREFEITURA+"'"%>)) {                   
                   return false;
              } 
        
            desabilitarBotoes(obterArrayBotoes());
            document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?" +Form.BOTAO_CONFIRMAR+"=2"%>';
            document.form.submit();
            return true;
         }
          
          
   </script>
   </head>
   
   <jsp:include page="/util/ViewVerificaErro.jsp"/>
   <body class="SEFAZ-Body" onload="verificaErro()";>
 <!-- padrao sefaz - cabeçalho e página de erro --> 
<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="${requestScope.atributoTituloFuncionalidade}"/>
<form method="POST" action="" name="form" enctype="multipart/form-data">

<table cellspacing="1" cellpadding="0" border="0" width="726" align="center">
          <tr class="SEFAZ-TR-Titulo">
                 <td align="center">Upload Arquivo Prefeitura</td>		
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
            <td class="SEFAZ-TD-RotuloEntrada" width="370">*Arquivo Excel:</td>
            <td> <input type="file" name="<%=Form.CAMPO_UPLOAD_ARQUIVO%>" onchange=" validarFormatoArquivo()"/>
            <input type="hidden" name="<%=Form.CAMPO_ARQUIVO_PROCESSAMENTO%>" value="campoArquivoProcessamento"/><td>
         </tr>
         <tr>
         
        <td colspan="2">
            <div align="center">		 
            <br>
               <input name="<%=Form.BOTAO_CONFIRMAR%>" value="<%=Form.TEXTO_BOTAO_CONFIRMAR%>" type="button" class="SEFAZ-INPUT-Botao" id="<%=Form.BOTAO_CONFIRMAR%>" onClick="solicitarProcessarArquivoIPTU();" />
                  <abaco:botaoRetornoMulti nomeBotao="  Cancelar  " />
               </div>          
      
       </tr>
       <tr>
        <td colspan="4"><h5><font color="red">* Campos Obrigatórios</font></h5></td>
      </tr>		
	</table>
   <abaco:mensagemAguardeCarregando/>
</form>
</body>
<g:mostrarRodape/>
</html>

