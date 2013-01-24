<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="The example of SAStruts OAuth provider"/>
<tiles:put name="content" type="string">
</tiles:put>
  <ul>
    <li><s:link href="/oauthClient/">Management for OAuth client applications.</s:link></li>
    <li><s:link href="/user/">OAuth Provieder example application</s:link></li>
    <li><s:link href="/consumer/">OAuth Consumer example application</s:link></li>
  </ul>
</tiles:insert>