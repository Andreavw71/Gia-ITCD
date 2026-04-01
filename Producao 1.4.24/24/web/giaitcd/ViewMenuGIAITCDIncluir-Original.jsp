<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%--
/*
* Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
* Arquivo : ViewMenuGIAITCDIncluir.jsp
* Criação : Outubro de 2007 / Anderson Boehler Iglesias Araujo
* Revisão : 
* Log : 
*/
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" %>
<%--AcessoWEB Valida--%>
<%@ taglib uri="http://www.sefaz.mt.gov.br/jstl/generico" prefix="g"%>
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page errorPage="/mensagem/ViewErro.jsp"%>
<html>
	<head>
		<META HTTP-EQUIV=Expires CONTENT="Thu, 01 Jan 1970 00:00:00 GMT">
		<META HTTP-EQUIV=Cache-Control content=no-store>
		<META HTTP-EQUIV=Pragma content=no-cache>
		<link rel="stylesheet" href="/estilos/SefazEstilos.css" type="text/css">
      <script type="text/javascript" language="javascript" src="<abaco:contexto/>javascript/overlib-4.21.js"></script>
		<title><%=JspUtil.getMensagemBarraTitulo(request)%></title>
      
      <style type="text/css" >
.popup{
   display:none;
   position:fixed;
   left:0px;
   top:0px;
   padding:0px;
   width:100%;
   height:100%;
   border:1px solid #d0d0d0;
   overflow: hidden;
}

.pop-content{
   margin-top:200px;
   width:740px;
   height:350px;
   padding: 15px;
   background-color: white;
   border:1px solid #d0d0d0;
   overflow-y: scroll;
   overflow-x: hidden;
   box-shadow: 10px 10px 10px #888888 ;
   z-index:0;
}

.pop-transparent{
	position:absolute; top:0; left:0;
	width:100%; height:100%;
	background-color: silver;
	opacity: 0.4;
   -moz-opacity:0.4;
   filter: alpha(opacity=40)
   -webkit-opacity:0.4;
	z-index:-1;
}

.pop-link{
	border:1px solid #d0d0d0;
	border-top:none;
	background-color: white;
	width:60px;
	box-shadow: 5px 5px 5px #888888;
	border-radius: 0px 0px 10px 10px;
	color:blue;
}
.pop-link a:link, a:visited, a:active {
	text-decoration: none
}
.pop-link:active{ 
	background-color: #d0d0d0;
	border-radius: 0px 0px 12px 12px;
	border:1px solid #d0d0d3;
}

p.texto {
    margin: 10px;
}

</style>
      
	</head>
	<body class="SEFAZ-Body">
		<g:mostrarCabecalho tituloSistema="${initParam.tituloSistema}" tituloFuncionalidade="Incluir GIA-ITCD"/>
		<center>
			<table width="381" border="0">
				<tr>
					<td width="375" align="left" valign="top">		
						<table width="353" border="0" cellpadding="0" cellspacing="0">
							<tr class="SEFAZ-TR-Titulo">
								<td height="22">Causa Mortis</td>
							</tr>
							<tr>
								<td height="22" width="250"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=JspUtil.getContexto(request)%>giaitcd/giaitcdinventarioarrolamento/incluir?parametroTelaBloqueio=1">Inventário/Arrolamento</a></font></div></td>
                                                <td>
                           <!--                     
                           <a href="#" onclick="document.getElementById('popupInventario').style.display='block';" onMouseOver="javascript:overlib('CASOS EM QUE NÃO SE DEVE UTILIZAR A GIA ITCD INVENTÁRIO' , CAPTION, 'GIA-ITCD INVENTÁRIO/ARROLAMENTO: ', WIDTH, '400', FGCOLOR, '#FFFFFF')"  onMouseOut="javascript:nd()"  >
                              <img src="/imagens/ajuda5.gif" height="9" width="9" alt=""></img>
                           </a>
                           -->
                           <div id="popupInventario" class="popup" style="display:none;" >
                           <div class="pop-transparent"></div>
                              <center>
                                    <div id="pop-content" class="pop-content">                          
                                       <table cellspacing="1" cellpadding="0" border="0" width="740" align="center" style="margin-bottom: 15px;" >
                                             <tr class="SEFAZ-TR-SubTitulo" >
                                                <td align="center">CASOS EM QUE NÃO SE DEVE UTILIZAR A GIA ITCD INVENTÁRIO</td>
                                             </tr>
                                             <tr>
                                                <td>&nbsp;</td>
                                             </tr>
                                             <tr>
                                                <td>
                                                   <p align="justify" class="texto" >
                                                      Não deve  ser  utilizada  a  GIA  ITCD  para  apuração  do  imposto  devido  nas 
                                                      transmissões  causa mortis, uma vez que o Sistema não comporta a forma de divisão dos bens 
                                                      (meações, quinhões hereditários e/ou legados), nos casos em que o de cujus:
                                                   </p>
                                                </td>
                                             </tr>                                            
                                             <tr>
                                                <td>
                                                      <ul>
                                                         <li><p align="justify" class="texto" >houver deixado testamento;</p></li>
                                                         <li><p align="justify" class="texto" >tinha  companheiro (a)/convivente  (cuja  união estável  deverá ser comprovada através de declaração em cartório ou declaração dos herdeiros legítimos);</p></li>
                                                         <li><p align="justify" class="texto" >a data de falecimento do  de cujus  é  anterior  a 11 de janeiro de 2003, o mesmo era casado  no  regime  da  comunhão  parcial  de  bens  e  tinha  bens  particulares  -  nesta situação,  o  cônjuge  não  participa  da  sucessão,  de  acordo  com  o  antigo  Código  Civil (1916);</p></li>
                                                         <li><p align="justify" class="texto" >a  data  de  falecimento  do  de  cujus  é  igual  ou  posterior  a  11  de  janeiro  de  2003,  o mesmo era casado no regime da comunhão parcial de bens, tinha  bens particulares  e 4 (quatro) ou mais descendentes - nesta situação, o cônjuge terá por direito 25% (vinte cinco por cento) dos bens particulares do  de cujus, de acordo com o atual Código Civil (2002);</p></li>
                                                         <li><p align="justify" class="texto" >a  data  de  falecimento  do  de  cujus  é  igual  ou  posterior  a  11  de  janeiro  de  2003,  o mesmo era casado no regime da comunhão parcial de bens, tinha  bens particulares  e o cônjuge sobrevivo desejar  renunciar abdicativamente  à herança (parte a que teria direito dos bens particulares do de cujus); e</p></li>
                                                         <li><p align="justify" class="texto" >nos casos de sobrepartilha, em que já houve recolhimento parcial do ITCD.</p></li>
                                                      </ul>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td>
                                                   <p align="justify" class="texto" >
                                                      Deve-se, para os casos acima elencados, preencher em substituição à GIA ITCD  a  &ldquo;Declaração  de  ITCD&rdquo;,  Anexo  VI,  da  Portaria nº 177/2018,  realizando  o  protocolo  da  mesma através de e-process &ldquo;PROTOCOLO DE  GIA ITCD  (E  ANEXO VI DA PORTARIA 177/2018)&rdquo;.                             
                                                   </p>
                                                </td>
                                             </tr>
                                       </table>                      
                                    </div>
                                    <div id="pop-link" class="pop-link" >
                                       <a href="#" onclick="document.getElementById('popupInventario').style.display='none';"><b><small>Fechar</small></b></a>
                                    </div>
                              </center>
                           </div>
                        </td>
							</tr>
							<tr class="SEFAZ-TR-Titulo">
								<td height="22">Inter Vivos</td>
							</tr>
							<tr>
								<td height="22" width="250"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=JspUtil.getContexto(request)%>giaitcd/giaitcddoacao/incluir?parametroTelaBloqueio=1">Doação/Outros</a></font></div></td>
                        <td>
                        <!--
                           <a href="#" onclick="document.getElementById('popupDoacao').style.display='block';" onMouseOver="javascript:overlib('CASOS  EM  QUE  NÃO  SE  DEVE  UTILIZAR  A GIA-ITCD DOAÇÃO/OUTROS:' , CAPTION, 'GIA-ITCD DOAÇÃO/OUTROS:', WIDTH, '400', FGCOLOR, '#FFFFFF')"  onMouseOut="javascript:nd()"  >
                              <img src="/imagens/ajuda5.gif" height="9" width="9" alt=""></img>
                           </a>
                           -->
                           <div id="popupDoacao" class="popup" style="display:none;" >
                           <div class="pop-transparent"></div>
                              <center>
                                    <div id="pop-content" class="pop-content">                          
                                       <table cellspacing="1" cellpadding="0" border="0" width="740" align="center" style="margin-bottom: 15px;" >
                                             <tr class="SEFAZ-TR-SubTitulo">
                                                <td align="center">CASOS  EM  QUE  NÃO  SE  DEVE  UTILIZAR  A  GIA  ITCD GIA-ITCD DOAÇÃO/OUTROS</td>
                                             </tr>
                                             <tr>
                                                <td>&nbsp;</td>
                                             </tr>
                                             <tr>
                                                <td>
                                                   <p align="justify" class="texto" >
                                                      Na  Renúncia  de  Usufruto,  se  houver  grandes  melhorias feitas  no  bem  pelo usufrutuário,
                                                      a transmissão das mesmas ao nu-proprietário corresponderá doação plena (base de cálculo 100%),
                                                      devendo ser apresentado o Anexo VI, da Portaria nº 177/2018, em substituição à GIA ITCD,
                                                      pois a base de cálculo do imposto total devido deverá ser de 70% (setenta por cento)
                                                      para  o  que  já  existia  na  instituição,  somado  a  100%  (cem  por  cento)  das  grandes  melhorias feitas.
                                                   </p>
                                                </td>
                                             </tr>                                            
                                             <tr>
                                                <td>
                                                      <p align="justify" class="texto" >Com relação a débitos de cruzamento de dados SEFAZ x Receita Federal:</p>
                                                      <ul>
                                                         <li><p align="justify" class="texto" >
                                                              Se a doação recebida até dezembro do ano passado  for de bens móveis (dinheiro em espécie, por exemplo)
                                                              e ainda não houve a emissão de Aviso de Cobrança Fazendária, faça  a  denúncia  espontânea,  conforme
                                                              exposto  no  capítulo  18  das  &ldquo;OrientaçõesGerais&rdquo; disponíveis na página da SEFAZ/MT, Menu Serviços/ITCD.
                                                               </p>
                                                         </li>
                                                      </ul>
                                                </td>
                                             </tr>
                                       </table>                      
                                    </div>
                                    <div id="pop-link" class="pop-link" >
                                       <a href="#" onclick="document.getElementById('popupDoacao').style.display='none';"><b><small>Fechar</small></b></a>
                                    </div>
                              </center>
                           </div>
                        </td>
							</tr>
							<tr>
								<td height="22"><div align="left"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><img src="/imagens/ponto.gif" height="9" width="9"> <a href="<%=JspUtil.getContexto(request)%>giaitcd/giaitcdseparacaodivorcio/incluir?parametroTelaBloqueio=1">Separação/Divórcio/Partilha</a></font></div></td>
                        <td>
                           <!--
                           <a href="#" onclick="document.getElementById('popupSeparacao').style.display='block';" onMouseOver="javascript:overlib('CASOS  EM  QUE  NÃO  SE  DEVE  UTILIZAR  A  GIA  ITCD SEPARAÇÃO/DIVÓRCIO PARTILHA' , CAPTION, 'GIA-ITCD SEPARAÇÃO/DIVÓRCIO PARTILHA:', WIDTH, '400', FGCOLOR, '#FFFFFF')"  onMouseOut="javascript:nd()"  >
                              <img src="/imagens/ajuda5.gif" height="9" width="9" alt=""></img>
                           </a>
                           -->
                           <div id="popupSeparacao" class="popup" style="display:none;" >
                           <div class="pop-transparent"></div>
                              <center>
                                    <div id="pop-content" class="pop-content">                          
                                       <table cellspacing="1" cellpadding="0" border="0" width="740" align="center" style="margin-bottom: 15px;" >
                                             <tr class="SEFAZ-TR-SubTitulo">
                                                <td align="center">CASOS  EM  QUE  NÃO  SE  DEVE  UTILIZAR  A  GIA  ITCD SEPARAÇÃO/DIVÓRCIO PARTILHA</td>
                                             </tr>
                                             <tr>
                                                <td>&nbsp;</td>
                                             </tr>
                                             <tr>
                                                <td>
                                                   <p align="justify" class="texto" >
                                                      Não  deve  ser  utilizada  a  GIA  ITCD  para  apuração  do  imposto  devido  nas transmissões  decorrentes  da  dissolução  da  sociedade  conjugal,  uma  vez  que  o  Sistema  não comporta a forma de partilha dos bens, nos casos em que:
                                                   </p>
                                                </td>
                                             </tr>                                            
                                             <tr>
                                                <td>
                                                      <ul>
                                                         <li><p align="justify" class="texto" >
                                                                  houver partilha desigual de bens, com excesso de meação para um dos cônjuges ou não,  a ser verificado
                                                                  após a avaliação administrativa da SEFAZ/MT, e no patrimônio do  casal  existirem  bens  cuja
                                                                  competência  para  cobrança  do  imposto  de  transmissão sejam  de  outras  Unidades
                                                                  da  Federação  -  bens  imóveis ;  bens  móveis  e  cônjuge  doador  domiciliado  em  outra Unidade da Federação.
                                                               </p>
                                                         </li>
                                                      </ul>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td>
                                                   <p align="justify" class="texto" >
                                                      Deve-se, para os casos acima elencado, preencher em substituição à GIA ITCD a  &ldquo;Declaração  de  ITCD&rdquo;,
                                                      Anexo  VI,  da  Portaria 177/2009,  realizando  o  protocolo  da  mesma através de e-process
                                                      &ldquo;PROTOCOLO DE GIA ITCD (E  ANEXO VI DA PORTARIA 177/2018)&rdquo;.                                                     
                                                   </p>
                                                </td>
                                             </tr>
                                       </table>                      
                                    </div>
                                    <div id="pop-link" class="pop-link" >
                                       <a href="#" onclick="document.getElementById('popupSeparacao').style.display='none';"><b><small>Fechar</small></b></a>
                                    </div>
                              </center>
                           </div>
                        </td>
							</tr>							
						</table>
					</td>
				</tr>
			</table>
		</center>
		<br></br>
		<br></br>
		<abaco:botaoVoltar nomeContadorSubmit="incluirGIA"></abaco:botaoVoltar>
		<abaco:botaoCancelarSemMensagem></abaco:botaoCancelarSemMensagem>
		<br></br>
		<br></br>
		<g:mostrarRodape />
	</body>
</html>