package net.unit8.sastruts.oauth.example.action;

import net.unit8.sastruts.oauth.example.LoginRequired;
import net.unit8.sastruts.oauth.example.dto.UserDto;
import net.unit8.sastruts.oauth.example.entity.User;
import net.unit8.sastruts.oauth.example.form.UserForm;
import net.unit8.sastruts.oauth.example.service.UserService;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.util.MessageDigestUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.RequestUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UU034251
 * Date: 13/01/08
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public class UserAction {
    @ActionForm
    @Resource
    protected UserForm userForm;

    @Resource
    protected UserService userService;

    public UserDto userDto;
    public List<User> users;
    public User user;

    @Execute(validator = false)
    public String index() {
        users = userService.findAll();
        return "index.jsp";
    }

    @Execute(validator = false, urlPattern = "show/{id}")
    public String show() {
        user =  userService.find(userForm.id);
        return "show.jsp";
    }

    @LoginRequired
    @Execute(validator = false, urlPattern = "showMore/{id}")
    public String showMore() {
        user =  userService.find(userForm.id);
        return "showMore.jsp";
    }

    @Execute(validator = false, urlPattern = "new")
    public String newUser() {
        user = new User();
        return "new.jsp";
    }

    @Execute(validator = true, input = "new.jsp")
    public String create() {
        user = Beans.createAndCopy(User.class, userForm).execute();
        byte[] buf = MessageDigestUtil.getInstance("SHA-256").digest(user.password.getBytes());
        StringBuilder hex = new StringBuilder();
        for (byte b : buf) {
            hex.append(String.format("%02x", b));
        }
        user.password = hex.toString();
        userService.insert(user);
        return String.format("/user/show/%s?redirect=true", user.id);
    }

    @Execute(validator = false, urlPattern = "destroy/{id}")
    public String destroy() {
        User user = userService.find(userForm.id);
        userService.delete(user);
        return "index?redirect=true";
    }

    @Execute(validator = false)
    public String login() {
        HttpServletRequest request = RequestUtil.getRequest();
        if (StringUtil.equals(request.getMethod(), "POST")) {
            User loginUser = userService.login(userForm.account, userForm.password);
            UserDto userDto = Beans.createAndCopy(UserDto.class, loginUser).execute();
            request.getSession().setAttribute("userDto", userDto);
            return userForm.redirectUrl +
                    (userForm.redirectUrl.indexOf('?') < 0 ? "?" : "&") +
                    "redirect=true";
        } else {
            return "login.jsp";
        }
    }
}
