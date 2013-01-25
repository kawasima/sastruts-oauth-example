<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="Consumer Example"/>
<tiles:put name="content" type="string">
<h3>Congratulations!</h3>
access_token=${tokenDto.accessToken}

<p>
  Using this access token, access an API.
</p>
<s:form>
  <select name="userId">
    <c:forEach var="user" items="${users}">
      <option value="${user.id}">${user.nickname}</option>
    </c:forEach>
  </select>
  <s:submit property="showUser">Show</s:submit>
</s:form>
</tiles:put>
</tiles:insert>