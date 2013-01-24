<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="The list of users"/>
<tiles:put name="content" type="string">
<s:form>
  <html:hidden property="redirectUrl"/>
  <p>
    <strong>Account:</strong>
    <code><html:text property="account"/></code>
  </p>
  <p>
    <strong>Password:</strong>
    <code><html:password property="password"/></code>
  </p>
  <s:submit property="login">Login</s:submit>
</s:form>
</tiles:put>
</tiles:insert>
