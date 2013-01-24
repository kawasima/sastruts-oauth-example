package net.unit8.sastruts.oauth.example.service;

import net.unit8.sastruts.oauth.example.AuthenticationException;
import net.unit8.sastruts.oauth.example.entity.User;
import org.seasar.extension.jdbc.service.S2AbstractService;
import org.seasar.extension.jdbc.where.SimpleWhere;

/**
 * Created with IntelliJ IDEA.
 * User: UU034251
 * Date: 13/01/08
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
public class UserService extends S2AbstractService<User>{
    public User find(Long id) {
        return select().id(id).getSingleResult();
    }

    public User login(String account, String password) {
        User user = select()
                .where(new SimpleWhere().eq("account", account))
                .getSingleResult();
        if (user == null) {
            throw new AuthenticationException();
        }
        return user;
    }
}
