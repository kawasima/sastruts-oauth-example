<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="Register a new application"/>
<tiles:put name="content" type="string">
<h1>Register a new application</h1>
<s:form>
  <jsp:include page="form.jsp"/>
  <input type="submit" name="create" value="Register"/>
</s:form>
</tiles:put>
</tiles:insert>