<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.abaco.com.br/jstl/abaco" prefix="abaco"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page import ="br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo"%>
<%@page import="br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.Form"%>
<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>
<table>
   <tr>
      <td>Senhor contribuinte, est&aacute; de acordo com o(s) Valor(es) Arbitrado(s) ?</td>
   </tr>
   <tr>
      <td>
         <input type="radio" name="<%=Form.CAMPO_TIPO_PROTOCOLO_SELECIONADO_CONTRIBUINTE%>" value="<%=DomnTipoProtocolo.PROTOCOLO_AUTOMATICO%>"></input>
         SIM (Protocolo automático - prazo para avaliação e liberação do Documento de Arrecadação: 30 minutos)
      </td>
   </tr>
   <tr>
      <td>
         <input type="radio" name="<%=Form.CAMPO_TIPO_PROTOCOLO_SELECIONADO_CONTRIBUINTE%>" value="<%=DomnTipoProtocolo.PROTOCOLO_MANUAL%>"></input>
         Não (Protocolo Manual - prazo para avaliação e liberação do Documento de Arrecadação: Acima de 30 dias)
      </td>
   </tr>
</table>