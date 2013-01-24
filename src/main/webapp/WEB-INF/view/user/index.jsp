<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="The list of users"/>
<tiles:put name="content" type="string">
<h3>Application Developers</h3>
${users}
<c:choose>
  <c:when test="${fn:length(users) gt 0}">
    <c:forEach var="user" items="${users}">
      <div>
        <s:link href="show/${user.id}">${f:h(user.account)}</s:link>
        <s:link href="edit/${user.id}">Edit</s:link>
        <s:link href="destroy/${user.id}">Delete</s:link>
      </div>
    </c:forEach>
  </c:when>
  <c:otherwise>
    <p>No user were found.</p>
  </c:otherwise>
</c:choose>
<br/>
<h3><s:link href="new">Register user</s:link></h3>
</tiles:put>
</tiles:insert>