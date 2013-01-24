<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="The list of users"/>
<tiles:put name="content" type="string">
<h1>User details for ${f:h(user.account)}</h1>
<p>
  <strong>Account:</strong>
  <code>${f:h(user.account)}</code>
</p>

<p>
  <strong>Nickname:</strong>
  <code>${f:h(user.nickname)}</code>
</p>

<p>
  <strong>address:</strong>
  <code>${f:h(user.address)}</code>
</p>
</tiles:put>
</tiles:insert>