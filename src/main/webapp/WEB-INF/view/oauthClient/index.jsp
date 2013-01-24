<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="OAuth Client Applications"/>
<tiles:put name="content" type="string">
<h1>OAuth Client Applications<h1>
<c:if test="${fn:length(oauthTokens) gt 0}">
  <p>The following tokens have been issued to applications in your name</p>
  <table>
    <tr>
      <th>Application</th>
      <th>Issued</th>
      <th>&nbsp;</th>
    </tr>
    <c:forEach var="token" items="${oauthTokens}">
      <tr>
        <td><a href="${f:h(token.clientApplication.url)}">${f:h(token.clientApplication.name)}</a></td>
        <td>${f:h(token.authorizedAt)}</td>
        <td>
          <form>
            <input type="hidden" name="token" value="${f:h(token.token)}"
            <button type="submit">Revoke!</button>
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<h3>Application Developers</h3>
<c:choose>
  <c:when test="${fn:length(clientApplications) gt 0}">
    <c:forEach var="client" items="${clientApplications}">
      <div>
        <s:link href="show/${client.id}">${f:h(client.name)}</s:link>
        <s:link href="edit/${client.id}">Edit</s:link>
        <s:link href="destroy/${client.id}">Delete</s:link>
      </div>
    </c:forEach>
  </c:when>
  <c:otherwise>
    <p> Do you have an application you would like to register for use with us using the <a href="http://oauth.net">OAuth</a> standard?
           You must register your web application before it can make OAuth requests to this service</p>
  </c:otherwise>
</c:choose>
<br/>
<h3><s:link href="new">Register your application</s:link></h3>
</tiles:put>
</tiles:insert>