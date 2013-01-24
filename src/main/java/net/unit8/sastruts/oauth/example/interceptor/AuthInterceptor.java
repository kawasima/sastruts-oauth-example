package net.unit8.sastruts.oauth.example.interceptor;

import net.unit8.sastruts.oauth.example.LoginRequired;
import net.unit8.sastruts.oauth.example.dto.UserDto;
import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.RequestUtil;
import org.seasar.struts.util.URLEncoderUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: UU034251
 * Date: 13/01/08
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
public class AuthInterceptor extends AbstractInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (isExecuteMethod(invocation) && isRequiredLogin(invocation)) {
            Object action = invocation.getThis();
            HttpServletRequest request = RequestUtil.getRequest();
            UserDto userDto = (UserDto) request.getSession().getAttribute("userDto");
            if (userDto == null) {
                StringBuilder redirectUrl = new StringBuilder();
                String servletPath = (String) request.getAttribute("javax.servlet.forward.servlet_path");
                if (servletPath == null)
                    servletPath = request.getServletPath() == null ? "" : request.getServletPath();
                redirectUrl.append(servletPath);

                String queryString = (String) request.getAttribute("javax.servlet.forward.query_string");
                if (StringUtil.isNotEmpty(queryString))
                    redirectUrl.append("?").append(queryString);
                return "/user/login?redirectUrl=" +
                        URLEncoderUtil.encode(redirectUrl.toString()) +
                        "&redirect=true";
            }
            BeanDesc actionDesc = BeanDescFactory.getBeanDesc(action.getClass());
            for (int i=0; i < actionDesc.getPropertyDescSize(); i++) {
                PropertyDesc propDesc = actionDesc.getPropertyDesc(i);
                if (UserDto.class.isAssignableFrom(propDesc.getPropertyType())) {
                    propDesc.setValue(action, userDto);
                }
            }
        }
        return invocation.proceed();
    }

    private boolean isExecuteMethod(MethodInvocation invocation) {
        return invocation.getMethod().isAnnotationPresent(Execute.class);
    }

    private boolean isRequiredLogin(MethodInvocation invocation) {
        return invocation.getMethod().isAnnotationPresent(LoginRequired.class);
    }
}
