<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="Consumer Example"/>
<tiles:put name="content" type="string">
<h3>Congratulations!</h3>
access_token=${tokenDto.accessToken}
</tiles:put>
</tiles:insert>