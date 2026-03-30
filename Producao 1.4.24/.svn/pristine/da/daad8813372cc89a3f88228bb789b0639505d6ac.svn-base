<%@ page import="br.gov.mt.sefaz.itc.util.FormAcesso"%>
<%@ page import="br.com.abaco.util.http.JspUtil"%>
<%@ page import="br.gov.mt.sefaz.itc.util.facade.FormITC"%>
<script language="javascript" type="text/javascript">
		document.form.action = '<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+FormITC.PARAMETRO_REDIRECIONA_VIEW_ERRO+"=1"%>';
		document.form.submit();	
</script>
