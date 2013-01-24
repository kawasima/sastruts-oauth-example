<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="Consumer Example"/>
<tiles:put name="content" type="string">
<h3>Fetch your personal infomation from sastruts-oauth-example application.</h3>

<c:choose>
  <c:when test="${fn:length(clientApplications) > 0}">
    <s:form action="/provider/authorize" method="GET">
      <p>
        <strong>Response type:</strong>
        <select name="response_type">
          <option>Select response type...</option>
          <option value="code">code</option>
          <option value="token">token</option>
        </select>
      </p>
      <p>
        <strong>Application:</strong>
        <select name="client_id">
          <option>Select application...</option>
          <c:forEach var="clientApplication" items="${clientApplications}">
            <option value="${f:h(clientApplication.key)}">${f:h(clientApplication.name)}</option>
          </c:forEach>
        </select>
      </p>
      <p>
        <s:submit>Authenticate & Authorize</s:submit>
      </p>
    </s:form>
  </c:when>
  <c:otherwise>
    You should register a client application, first.
    <s:link href="/oauthClient/">Here...</s:link>
  </c:otherwise>
</c:choose>
</tiles:put>
</tiles:insert>