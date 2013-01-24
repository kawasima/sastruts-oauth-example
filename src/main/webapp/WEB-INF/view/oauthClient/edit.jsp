<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="Edit your application"/>
<tiles:put name="content" type="string">
<h1>Edit your application</h1>
<s:form>
  <html:hidden property="id"/>
  <jsp:include page="form.jsp"/>
  <input type="submit" name="update" value="Edit"/>
</s:form>
</tiles:put>
</tiles:insert>