<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="Authorize access to your account"/>
<tiles:put name="content" type="string">
<h1>Authorize access to your account</h1>
<p>Would you like to authorize <s:link href="${clientApplication.url}">${f:h(clientApplication.name)}</s:link>
(<s:link href="${f:h(clientApplication.url)}">${f:h(clientApplication.url)}</s:link>) to access your account?</p>

<s:form>
  <html:hidden property="response_type"/>
  <html:hidden property="client_id"/>
  <html:hidden property="redirect_uri"/>
  <html:hidden property="state"/>
  <html:hidden property="scope"/>
<p>
  <html:checkbox property="isAuthorized"/> authorize access
</p>
<p>
  <s:submit property="authorize" value="authorize"/>
</p>
</s:form>
</tiles:put>
</tiles:insert>